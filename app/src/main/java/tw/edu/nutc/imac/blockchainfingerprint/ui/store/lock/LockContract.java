package tw.edu.nutc.imac.blockchainfingerprint.ui.store.lock;

import android.widget.EditText;

import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenter;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseView;

/**
 * Created by 依杰 on 2018/7/6.
 */

public interface LockContract {
    interface View extends BaseView {
        void setModel(LockModel lockModel);

        void onSubmitClicked();

        void onCreateClick();

        EditText.OnFocusChangeListener focusChangeListener();

        void showStoreList();
    }


    interface Presenter extends BasePresenter<View> {
        void onConnectLDAP(String account);

        void onCreateLDAP();
    }
}