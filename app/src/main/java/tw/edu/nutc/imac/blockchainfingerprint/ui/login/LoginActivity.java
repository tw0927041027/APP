package tw.edu.nutc.imac.blockchainfingerprint.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.EditText;

import tw.edu.nutc.imac.blockchainfingerprint.R;
import tw.edu.nutc.imac.blockchainfingerprint.databinding.ActivityLoginBinding;
import tw.edu.nutc.imac.blockchainfingerprint.di.ApplicationComponent;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseActivity;
import tw.edu.nutc.imac.blockchainfingerprint.ui.login.register.RegisterFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.StoreActivity;
import tw.edu.nutc.imac.blockchainfingerprint.util.CommonUtils;
import tw.edu.nutc.imac.blockchainfingerprint.util.view.CustomEditContainer;

/**
 * Created by 依杰 on 2018/6/11.
 */

public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {
    private LoginComponent loginComponent;
    private ActivityLoginBinding mActivityLoginBinding;

    private CustomEditContainer mAccountCustomEditContainer;

    private CustomEditContainer mPasswordCustomEditContainer;

    public LoginComponent getLoginComponent() {
        return loginComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        super.onCreate(savedInstanceState);
        mActivityLoginBinding.setView(this);

        initView();
    }

    private void initView() {
        mAccountCustomEditContainer = mActivityLoginBinding.containerAccount;

        mPasswordCustomEditContainer = mActivityLoginBinding.containerPassword;

    }

    @Override
    protected void initDagger(ApplicationComponent appComponent) {
        loginComponent = appComponent
                .loginBuilder()
                .loginActivity(this)
                .build();
        loginComponent.inject(this);
    }

    @Override
    public void setModel(LoginModel loginModel) {
        mActivityLoginBinding.setModel(loginModel);
    }

    @Override
    public void onSubmitClicked() {
        getPresenter().onLoginEvent(mActivityLoginBinding.getModel());
    }

    @Override
    public void onRegisterClick() {
        CommonUtils.TransFragment(this, R.id.main_content, RegisterFragment.newInstance(), RegisterFragment.TAG, null);
    }

    @Override
    public EditText.OnFocusChangeListener focusChangeListener() {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mAccountCustomEditContainer.setFocus(false);
                mPasswordCustomEditContainer.setFocus(false);
                if (((view.getId() == R.id.edit_account) && b)) {
                    mAccountCustomEditContainer.setFocus(true);
                } else if (((view.getId() == R.id.edit_password) && b)) {
                    mPasswordCustomEditContainer.setFocus(true);
                }
            }
        };
    }

    @Override
    public void onAccountTextChanged(CharSequence s, int start, int before, int count) {
        if (CommonUtils.isEmailValid(String.valueOf(s)) || s.length() == 0) {
            mAccountCustomEditContainer.setError(false);
            mActivityLoginBinding.getModel().setSubmitEnabled(!(mActivityLoginBinding.getModel().getPassword() == null) && !mPasswordCustomEditContainer.getIsError() && !mAccountCustomEditContainer.getIsError());
        } else {
            mActivityLoginBinding.getModel().setSubmitEnabled(false);
            mAccountCustomEditContainer.setError(true);
        }
    }

    @Override
    public void onPasswordTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 5 || s.length() == 0) {
            mPasswordCustomEditContainer.setError(false);
            mActivityLoginBinding.getModel().setSubmitEnabled(!(mActivityLoginBinding.getModel().getAccount() == null) && !mPasswordCustomEditContainer.getIsError() && !mAccountCustomEditContainer.getIsError());
        } else {
            mActivityLoginBinding.getModel().setSubmitEnabled(false);
            mPasswordCustomEditContainer.setError(true);
        }
    }

    @Override
    public void showStoreListPage() {
        Intent intent = new Intent(this, StoreActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFragmentDetached(Bundle arg, String... tag) {
        super.onFragmentDetached(arg, tag);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag[0]);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (null != fragmentManager.findFragmentByTag(RegisterFragment.TAG)) {
            onFragmentDetached(null, RegisterFragment.TAG);
        } else {
            super.onBackPressed();
        }
    }
}
