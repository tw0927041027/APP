package tw.edu.nutc.imac.blockchainfingerprint.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;

import tw.edu.nutc.imac.blockchainfingerprint.di.ApplicationComponent;
import tw.edu.nutc.imac.blockchainfingerprint.di.UserManager;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseActivity;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenter;

/**
 * Created by 依杰 on 2018/6/12.
 */

public abstract class BaseUserActivity<T extends BasePresenter> extends BaseActivity<T> {

    UserManager userManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!userManager.isLoggedIn()) {
        }
    }

    @Override
    protected void initDagger(ApplicationComponent appComponent) {
        userManager = appComponent.getUserManager();
        inject(userManager.getUserComponent());
    }

    protected void logoutUser() {
        userManager.logOut();
    }

    protected abstract void inject(UserComponent userComponent);
}