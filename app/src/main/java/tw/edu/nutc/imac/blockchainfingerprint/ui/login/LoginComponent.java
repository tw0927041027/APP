package tw.edu.nutc.imac.blockchainfingerprint.ui.login;

import dagger.BindsInstance;
import dagger.Subcomponent;
import tw.edu.nutc.imac.blockchainfingerprint.di.ActivityScope;
import tw.edu.nutc.imac.blockchainfingerprint.di.FingerprintModule;
import tw.edu.nutc.imac.blockchainfingerprint.ui.login.register.RegisterFragment;

/**
 * Created by 依杰 on 2018/6/11.
 */
@ActivityScope
@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent {

    LoginActivity inject(LoginActivity loginActivity);

    void inject(RegisterFragment registerFragment);

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        Builder loginActivity(LoginActivity loginActivity);

        LoginComponent build();
    }
}
