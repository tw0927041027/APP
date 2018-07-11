package tw.edu.nutc.imac.blockchainfingerprint.ui.login.register;

import javax.inject.Inject;

import rx.Subscriber;
import tw.edu.nutc.imac.blockchainfingerprint.R;
import tw.edu.nutc.imac.blockchainfingerprint.data.network.RemoteSource;
import tw.edu.nutc.imac.blockchainfingerprint.data.network.Result;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenterImp;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProvider;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProviderImp;

/**
 * Created by 依杰 on 2018/7/2.
 */
public class RegisterPresenter extends BasePresenterImp<RegisterContract.View> implements RegisterContract.Presenter {
    private SchedulerProvider mSchedulerProvider;

    private RemoteSource mRemoteSource;

    @Inject
    public RegisterPresenter(SchedulerProviderImp schedulerProviderImp, RemoteSource remoteSource) {
        mSchedulerProvider = schedulerProviderImp;

        mRemoteSource = remoteSource;
    }

    @Override
    public void onAttach(RegisterContract.View view) {
        super.onAttach(view);
        getView().setModel(new RegisterModel());
    }

    @Override
    public void onRegisterEvent(RegisterModel model) {
        if (!model.isSubmitEnabled()) {
            getView().onHint(R.string.error_account);
            return;
        }

        long testData = System.currentTimeMillis() / 1000;
        getView().showLoading();
        mCompositeSubscription.add(
                mRemoteSource.register(model.getAccount(), model.getPassword(),
                        String.valueOf(testData), String.valueOf(testData),
                        String.valueOf(testData), String.valueOf(testData))
                        .subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .subscribe(new Subscriber<Result>() {
                            @Override
                            public void onCompleted() {
                                getView().hideLoading();
                            }

                            @Override
                            public void onError(Throwable e) {
                                getView().hideLoading();
                                getView().onHint(R.string.error_hint_net);
                            }

                            @Override
                            public void onNext(Result result) {
                                if (result.getResult() == 0) {
                                    getView().onHint(R.string.register_hint_success);
                                    getView().showLoginPage();
                                }else if (result.getMessage().contains("email")&&result.getMessage().contains("taken")){
                                    getView().onHint(R.string.register_error_email);
                                }
                            }
                        }));
    }
}
