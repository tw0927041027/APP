package tw.edu.nutc.imac.blockchainfingerprint.ui.items;

import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import tw.edu.nutc.imac.blockchainfingerprint.di.ActivityScope;
import tw.edu.nutc.imac.blockchainfingerprint.ui.items.lock.LockContract;
import tw.edu.nutc.imac.blockchainfingerprint.ui.items.lock.LockPresenter;
import tw.edu.nutc.imac.blockchainfingerprint.ui.items.point.PointContract;
import tw.edu.nutc.imac.blockchainfingerprint.ui.items.point.PointPresenter;

/**
 * Created by 依杰 on 2018/6/12.
 */
@Module
public class ItemsModule {

    @ActivityScope
    @Provides
    public LinearLayoutManager provideLayoutManager(ItemsActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @ActivityScope
    @Provides
    public ItemsContract.Presenter provideItemsContract(ItemsPresenter presenter) {
        return presenter;
    }

    @ActivityScope
    @Provides
    public PointContract.Presenter providePointContract(PointPresenter presenter) {
        return presenter;
    }

    @Provides
    public LockContract.Presenter provideLockContract(LockPresenter presenter) {
        return presenter;
    }

    @ActivityScope
    @Provides
    public ItemsAdapt provideItemsAdapt(ItemsContract.Presenter presenter) {
        return new ItemsAdapt(presenter);
    }
}
