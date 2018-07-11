package tw.edu.nutc.imac.blockchainfingerprint.ui.store.setting;

import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenter;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseView;

/**
 * Created by 依杰 on 2018/7/10.
 */

public interface SettingContract {
    interface View extends BaseView {
        void setModel(SettingModel lockModel);

    }


    interface Presenter extends BasePresenter<View> {
        void onSetIsLockEvent(boolean isLock);
    }
}
