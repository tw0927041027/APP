package tw.edu.nutc.imac.blockchainfingerprint.ui.login.register;

import android.widget.EditText;

import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenter;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseView;
/**
 * Created by 依杰 on 2018/7/2.
 */

public interface RegisterContract {
    interface View extends BaseView {
        void setModel(RegisterModel registerModel);

        void onSubmitClicked();

        EditText.OnFocusChangeListener focusChangeListener();

        void onAccountTextChanged(CharSequence s, int start, int before, int count);

        void onPasswordTextChanged(CharSequence s, int start, int before, int count);

        void showLoginPage();
    }

    interface Presenter extends BasePresenter<View> {
        void onRegisterEvent(RegisterModel model);
    }
}
