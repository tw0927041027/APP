package tw.edu.nutc.imac.blockchainfingerprint.ui.login;

import android.widget.EditText;

import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenter;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseView;

/**
 * Created by 依杰 on 2018/6/11.
 */

public interface LoginContract {
    interface View extends BaseView {
        void setModel(LoginModel loginModel);

        void onSubmitClicked();

        void onRegisterClick();

        EditText.OnFocusChangeListener focusChangeListener();

        void onAccountTextChanged(CharSequence s, int start, int before, int count);

        void onPasswordTextChanged(CharSequence s, int start, int before, int count);

        void showStoreListPage();
    }

    interface Presenter extends BasePresenter<View> {
        void onLoginEvent(LoginModel loginModel);
    }
}
