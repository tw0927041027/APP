package tw.edu.nutc.imac.blockchainfingerprint.ui.store;

import dagger.Module;
import dagger.Provides;
import tw.edu.nutc.imac.blockchainfingerprint.di.ActivityScope;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.list.ListContract;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.list.ListPresenter;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.lock.LockContract;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.lock.LockPresenter;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.point.PointContract;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.point.PointPresenter;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.setting.SettingContract;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.setting.SettingPresenter;

/**
 * Created by 依杰 on 2018/6/12.
 */
@Module
public class StoreModule {

    @ActivityScope
    @Provides
    public StoreContract.Presenter provideStoreContract(StorePresenter presenter) {
        return presenter;
    }

    @ActivityScope
    @Provides
    public ListContract.Presenter provideListContract(ListPresenter presenter) {
        return presenter;
    }

    @ActivityScope
    @Provides
    public PointContract.Presenter providePointContract(PointPresenter presenter) {
        return presenter;
    }

    @ActivityScope
    @Provides
    public LockContract.Presenter provideLockContract(LockPresenter presenter) {
        return presenter;
    }

    @ActivityScope
    @Provides
    public SettingContract.Presenter provideSettingContract(SettingPresenter presenter) {
        return presenter;
    }

}
