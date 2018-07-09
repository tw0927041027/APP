package tw.edu.nutc.imac.blockchainfingerprint.ui.login;

import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.R;
import tw.edu.nutc.imac.blockchainfingerprint.data.entity.User;
import tw.edu.nutc.imac.blockchainfingerprint.di.ActivityScope;
import tw.edu.nutc.imac.blockchainfingerprint.di.UserManager;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenterImp;

/**
 * Created by 依杰 on 2018/6/11.
 */
@ActivityScope
public class LoginPresenter extends BasePresenterImp<LoginContract.View> implements LoginContract.Presenter {
    private LoginModel mLoginModel;
    private UserManager mUserManager;

    @Inject
    public LoginPresenter(UserManager userManager) {
        mUserManager = userManager;
    }

    @Override
    public void onAttach(LoginContract.View view) {
        super.onAttach(view);
        getView().setModel(mLoginModel = new LoginModel());
    }

    @Override
    public void onLoginEvent(LoginModel loginModel) {
        if (!loginModel.isSubmitEnabled()){
            getView().onHint(R.string.error_account);
            return;
        }
        User user = new User();
        user.setAccount("aaa");
        mUserManager.createUser(user);
        getView().showStoreListPage();
    }
}
