package tw.edu.nutc.imac.blockchainfingerprint.di;

import dagger.Module;
import dagger.Provides;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProvider;

/**
 * Created by 依杰 on 2018/6/11.
 */
@Module
public class SchedulerModule {

    @Provides
    SchedulerProvider providerSchedulerProvider(SchedulerProvider provider) {
        return provider;
    }
}
