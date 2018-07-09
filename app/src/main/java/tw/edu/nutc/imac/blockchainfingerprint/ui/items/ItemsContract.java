package tw.edu.nutc.imac.blockchainfingerprint.ui.items;

import android.os.Bundle;

import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenter;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseView;

/**
 * Created by 依杰 on 2018/6/12.
 */

public interface ItemsContract {

    interface View extends BaseView {
        void showChangePointPage(Bundle args);

        void showUnLockPage(Bundle args);
    }

    interface Presenter extends BasePresenter<View> {
        void onChangePointClickEvent(ItemModel itemModel);

        void onUnlockClickEvent(ItemModel itemModel);
    }

    interface Adapt {
        void onItemClick(ItemModel itemModel);
    }
}
