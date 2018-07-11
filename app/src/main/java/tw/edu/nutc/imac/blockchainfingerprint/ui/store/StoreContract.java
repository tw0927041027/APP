package tw.edu.nutc.imac.blockchainfingerprint.ui.store;

import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenter;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseView;

/**
 * Created by 依杰 on 2018/7/10.
 */

public interface StoreContract {
    interface View extends BaseView {
        void setModel(StoreModel storeModel);

        void onPointClick();

        void onSettingClick();
    }

    interface Presenter extends BasePresenter<View> {
        boolean getLockState();
    }
}
