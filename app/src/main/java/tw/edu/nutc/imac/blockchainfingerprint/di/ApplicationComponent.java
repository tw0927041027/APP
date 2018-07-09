package tw.edu.nutc.imac.blockchainfingerprint.di;


import android.app.Application;
import android.hardware.fingerprint.FingerprintManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import tw.edu.nutc.imac.blockchainfingerprint.data.network.RemoteSource;
import tw.edu.nutc.imac.blockchainfingerprint.ui.login.LoginComponent;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProviderImp;

@Singleton
@Component(modules = {ApplicationModule.class, RemoteModule.class})
public interface ApplicationComponent extends android.databinding.DataBindingComponent {
    LoginComponent.Builder loginBuilder();

    UserManager getUserManager();

    SchedulerProviderImp getPreferencesHelperImp();

    RemoteSource getRemoteSource();

    @Component.Builder
    interface Builder {

        ApplicationComponent build();

        @BindsInstance
        Builder application(Application application);
    }
}