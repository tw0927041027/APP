package tw.edu.nutc.imac.blockchainfingerprint.ui.login;

import javax.inject.Inject;

import rx.Subscriber;
import tw.edu.nutc.imac.blockchainfingerprint.R;
import tw.edu.nutc.imac.blockchainfingerprint.data.entity.User;
import tw.edu.nutc.imac.blockchainfingerprint.data.network.LoginResult;
import tw.edu.nutc.imac.blockchainfingerprint.data.network.RemoteSource;
import tw.edu.nutc.imac.blockchainfingerprint.data.prefs.PreferencesHelper;
import tw.edu.nutc.imac.blockchainfingerprint.data.prefs.PreferencesHelperImp;
import tw.edu.nutc.imac.blockchainfingerprint.di.ActivityScope;
import tw.edu.nutc.imac.blockchainfingerprint.di.UserManager;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenterImp;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProvider;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProviderImp;

/**
 * Created by 依杰 on 2018/6/11.
 */
@ActivityScope
public class LoginPresenter extends BasePresenterImp<LoginContract.View> implements LoginContract.Presenter {
    private LoginModel mLoginModel;

    private UserManager mUserManager;

    private RemoteSource mRemoteSource;

    private SchedulerProvider mSchedulerProvider;

    private PreferencesHelper mPreferencesHelper;

    @Inject
    public LoginPresenter(SchedulerProviderImp schedulerProviderImp, UserManager userManager,
                          RemoteSource remoteSource, PreferencesHelperImp preferencesHelperImp) {
        mUserManager = userManager;

        mRemoteSource = remoteSource;

        mSchedulerProvider = schedulerProviderImp;

        mPreferencesHelper = preferencesHelperImp;
    }

    @Override
    public void onAttach(LoginContract.View view) {
        super.onAttach(view);
        getView().setModel(mLoginModel = new LoginModel());
    }

    @Override
    public void onLoginEvent(final LoginModel loginModel) {
        if (!loginModel.isSubmitEnabled()) {
            getView().onHint(R.string.error_account);
            return;
        }

        getView().showLoading();
        mCompositeSubscription.add(
                mRemoteSource.login(loginModel.getAccount(), loginModel.getPassword())
                        .subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .subscribe(new Subscriber<LoginResult>() {
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
                            public void onNext(LoginResult result) {
                                if (result.getResult() == 0) {
                                    getView().onHint(R.string.login_hint_success);
                                    mPreferencesHelper.setAccount(loginModel.getAccount());
                                    mPreferencesHelper.setPassword(loginModel.getPassword());
                                    mPreferencesHelper.setToken(result.getMessage().getToken());
                                    User user = new User();
                                    user.setToken(result.getMessage().getToken());
                                    mUserManager.createUser(user);
                                    getView().showStoreListPage();
                                } else {
                                    getView().onHint(R.string.login_hint_error);
                                }
                            }
                        }));
    }
}
