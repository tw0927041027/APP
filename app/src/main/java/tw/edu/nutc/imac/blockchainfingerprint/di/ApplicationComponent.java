package tw.edu.nutc.imac.blockchainfingerprint.di;


import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import tw.edu.nutc.imac.blockchainfingerprint.data.network.RemoteSource;
import tw.edu.nutc.imac.blockchainfingerprint.data.prefs.PreferencesHelperImp;
import tw.edu.nutc.imac.blockchainfingerprint.ui.login.LoginComponent;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProviderImp;

@Singleton
@Component(modules = {ApplicationModule.class, RemoteModule.class})
public interface ApplicationComponent extends android.databinding.DataBindingComponent {
    LoginComponent.Builder loginBuilder();

    UserManager getUserManager();

    SchedulerProviderImp getSchedulerProviderImp();

    RemoteSource getRemoteSource();

    PreferencesHelperImp getPreferencesHelperImp();

    @Component.Builder
    interface Builder {

        ApplicationComponent build();

        @BindsInstance
        Builder application(Application application);
    }
}