package tw.edu.nutc.imac.blockchainfingerprint.ui.items;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.R;
import tw.edu.nutc.imac.blockchainfingerprint.data.entity.User;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenterImp;

/**
 * Created by 依杰 on 2018/6/12.
 */

public class ItemsPresenter extends BasePresenterImp<ItemsContract.View> implements ItemsContract.Presenter {
    public static String BUNDLE_STORENAME_KEY = "BUNDLE_STORENAME";

    @Inject
    public ItemsPresenter(User user) {
        Log.e("ItemsPresenter", "" + user.getAccount());
    }


    @Override
    public void onChangePointClickEvent(ItemModel itemModel) {
        Bundle arg = new Bundle();
        arg.putString(BUNDLE_STORENAME_KEY, itemModel.getStoreName());
        getView().showChangePointPage(arg);
    }

    @Override
    public void onUnlockClickEvent(ItemModel itemModel) {
        Bundle arg = new Bundle();
        arg.putString(BUNDLE_STORENAME_KEY, itemModel.getStoreName());
        getView().showUnLockPage(arg);
    }
}
