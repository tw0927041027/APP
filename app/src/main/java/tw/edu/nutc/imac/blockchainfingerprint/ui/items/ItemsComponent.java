package tw.edu.nutc.imac.blockchainfingerprint.ui.items;

import dagger.BindsInstance;
import dagger.Subcomponent;
import tw.edu.nutc.imac.blockchainfingerprint.di.ActivityScope;
import tw.edu.nutc.imac.blockchainfingerprint.di.FingerprintModule;
import tw.edu.nutc.imac.blockchainfingerprint.ui.items.lock.LockFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.items.point.PointFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.login.LoginModule;
import tw.edu.nutc.imac.blockchainfingerprint.ui.login.register.RegisterFragment;

/**
 * Created by 依杰 on 2018/6/12.
 */
@ActivityScope
@Subcomponent(modules = {ItemsModule.class,FingerprintModule.class})
public interface ItemsComponent {

    void inject(ItemsActivity itemsActivity);

    void inject(LockFragment registerFragment);

    void inject(PointFragment pointFragment);

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        ItemsComponent.Builder activity(ItemsActivity homeActivity);
        ItemsComponent build();

    }
}
