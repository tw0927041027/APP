package tw.edu.nutc.imac.blockchainfingerprint.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tw.edu.nutc.imac.blockchainfingerprint.data.prefs.PreferencesHelper;
import tw.edu.nutc.imac.blockchainfingerprint.data.prefs.PreferencesHelperImp;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProvider;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProviderImp;

/**
 * Created by 依杰 on 2018/6/11.
 */
@Module
public final class ApplicationModule {

    @Provides
    @Singleton
    SchedulerProvider providerSchedulerProviderImp(SchedulerProviderImp provider) {
        return provider;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelperImp(PreferencesHelperImp preferencesHelperImp) {
        return preferencesHelperImp;
    }
}
