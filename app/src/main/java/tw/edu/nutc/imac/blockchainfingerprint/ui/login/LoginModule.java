package tw.edu.nutc.imac.blockchainfingerprint.ui.login;

import dagger.Module;
import dagger.Provides;
import tw.edu.nutc.imac.blockchainfingerprint.ui.login.register.RegisterContract;
import tw.edu.nutc.imac.blockchainfingerprint.ui.login.register.RegisterPresenter;

/**
 * Created by 依杰 on 2018/6/11.
 */
@Module
public class LoginModule {

    @Provides
    public LoginContract.Presenter provideLoginContract(LoginPresenter presenter) {
        return presenter;
    }

    @Provides
    public RegisterContract.Presenter provideRegisterContract(RegisterPresenter presenter) {
        return presenter;
    }
}