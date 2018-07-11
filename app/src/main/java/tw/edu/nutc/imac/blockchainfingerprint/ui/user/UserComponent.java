package tw.edu.nutc.imac.blockchainfingerprint.ui.user;

import dagger.BindsInstance;
import dagger.Component;
import tw.edu.nutc.imac.blockchainfingerprint.data.entity.User;
import tw.edu.nutc.imac.blockchainfingerprint.di.ApplicationComponent;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.StoreComponent;

/**
 * Created by 依杰 on 2018/6/12.
 */

@Component(dependencies = {ApplicationComponent.class})
@UserScope
public interface UserComponent {

    StoreComponent.Builder storeComponentBuilder();


    @Component.Builder
    interface Builder {

        Builder appComponent(ApplicationComponent appComponent);

        @BindsInstance
        Builder user(User user);

        UserComponent build();

    }

}