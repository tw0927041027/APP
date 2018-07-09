package tw.edu.nutc.imac.blockchainfingerprint.ui.items.lock;

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

        EditText.OnFocusChangeListener focusChangeListener();

        void onAccountTextChanged(CharSequence s, int start, int before, int count);

        void onPasswordTextChanged(CharSequence s, int start, int before, int count);
    }


    interface Presenter extends BasePresenter<View> {
        void onUnlockEvent(LockModel model);
    }
}