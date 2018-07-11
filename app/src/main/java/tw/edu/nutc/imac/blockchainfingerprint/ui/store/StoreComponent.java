package tw.edu.nutc.imac.blockchainfingerprint.ui.store;

import dagger.BindsInstance;
import dagger.Subcomponent;
import tw.edu.nutc.imac.blockchainfingerprint.di.ActivityScope;
import tw.edu.nutc.imac.blockchainfingerprint.di.FingerprintModule;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.list.ListFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.lock.LockFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.point.PointFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.setting.SettingFragment;

/**
 * Created by 依杰 on 2018/6/12.
 */
@ActivityScope
@Subcomponent(modules = {StoreModule.class, FingerprintModule.class})
public interface StoreComponent {

    void inject(StoreActivity storeActivity);

    void inject(LockFragment lockFragment);

    void inject(PointFragment pointFragment);

    void inject(SettingFragment settingFragment);

    void inject(ListFragment listFragment);

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        StoreComponent.Builder activity(StoreActivity storeActivity);

        StoreComponent build();

    }
}
