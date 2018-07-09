package tw.edu.nutc.imac.blockchainfingerprint.ui.login.register;

import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.R;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenterImp;
import tw.edu.nutc.imac.blockchainfingerprint.ui.login.LoginModel;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProvider;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProviderImp;

/**
 * Created by 依杰 on 2018/7/2.
 */
public class RegisterPresenter extends BasePresenterImp<RegisterContract.View> implements RegisterContract.Presenter {
    private SchedulerProvider mSchedulerProvider;

    @Inject
    public RegisterPresenter(SchedulerProviderImp schedulerProviderImp) {
        mSchedulerProvider = schedulerProviderImp;
    }

    @Override
    public void onAttach(RegisterContract.View view) {
        super.onAttach(view);
        getView().setModel(new RegisterModel());
    }

    @Override
    public void onRegisterEvent(RegisterModel model) {
        if (!model.isSubmitEnabled()){
            getView().onHint(R.string.error_account);
            return;
        }
    }
}
