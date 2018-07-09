package tw.edu.nutc.imac.blockchainfingerprint.di;

import javax.inject.Inject;
import javax.inject.Singleton;

import tw.edu.nutc.imac.blockchainfingerprint.BlockChainApplication;
import tw.edu.nutc.imac.blockchainfingerprint.data.entity.User;
import tw.edu.nutc.imac.blockchainfingerprint.ui.user.DaggerUserComponent;
import tw.edu.nutc.imac.blockchainfingerprint.ui.user.UserComponent;

/**
 * Created by 依杰 on 2018/6/12.
 */

@Singleton
public class UserManager {

    public UserComponent getUserComponent() {
        return userComponent;
    }

    private UserComponent userComponent;

    @Inject
    public UserManager() {

    }

    public void createUser(User user) {
        userComponent = DaggerUserComponent.builder()
                .appComponent(BlockChainApplication.getApp().getAppComponent())
                .user(user)
                .build();
    }

    public boolean isLoggedIn() {
        return userComponent != null;
    }

    public void logOut() {
    }


}