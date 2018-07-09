package tw.edu.nutc.imac.blockchainfingerprint;

import android.app.Application;
import android.databinding.DataBindingUtil;

import tw.edu.nutc.imac.blockchainfingerprint.di.ApplicationComponent;
import tw.edu.nutc.imac.blockchainfingerprint.di.DaggerApplicationComponent;

/**
 * Created by 依杰 on 2018/6/11.
 */

public class BlockChainApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    public ApplicationComponent getAppComponent() {
        return mApplicationComponent;
    }

    static BlockChainApplication app;

    public static BlockChainApplication getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        mApplicationComponent = DaggerApplicationComponent.builder().application(this).build();
        DataBindingUtil.setDefaultComponent(mApplicationComponent);
    }
}
