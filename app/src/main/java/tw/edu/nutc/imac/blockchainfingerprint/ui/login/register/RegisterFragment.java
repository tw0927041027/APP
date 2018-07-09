package tw.edu.nutc.imac.blockchainfingerprint.ui.login.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import tw.edu.nutc.imac.blockchainfingerprint.R;
import tw.edu.nutc.imac.blockchainfingerprint.databinding.FragmentRegisterBinding;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.login.LoginActivity;
import tw.edu.nutc.imac.blockchainfingerprint.util.CommonUtils;
import tw.edu.nutc.imac.blockchainfingerprint.util.view.CustomEditContainer;

/**
 * Created by 依杰 on 2018/7/2.
 */

public class RegisterFragment extends BaseFragment<RegisterContract.Presenter> implements RegisterContract.View {
    public static final String TAG = RegisterFragment.class.getSimpleName();

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    private FragmentRegisterBinding mFragmentForgetBinding;

    private CustomEditContainer mAccountCustomEditContainer;

    private CustomEditContainer mPasswordCustomEditContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentForgetBinding = FragmentRegisterBinding.inflate(inflater, container, false);

        mFragmentForgetBinding.setView(this);

        return mFragmentForgetBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAccountCustomEditContainer = mFragmentForgetBinding.containerAccount;

        mPasswordCustomEditContainer = mFragmentForgetBinding.containerPassword;
    }

    @Override
    public void setModel(RegisterModel registerModel) {
        mFragmentForgetBinding.setModel(registerModel);
    }

    @Override
    public void onSubmitClicked() {
        getPresenter().onRegisterEvent(mFragmentForgetBinding.getModel());
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
            mFragmentForgetBinding.getModel().setSubmitEnabled(!mPasswordCustomEditContainer.getIsError() && !mAccountCustomEditContainer.getIsError());
        } else {
            mFragmentForgetBinding.getModel().setSubmitEnabled(false);
            mAccountCustomEditContainer.setError(true);
        }
    }

    @Override
    public void onPasswordTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 5 || s.length() == 0) {
            mPasswordCustomEditContainer.setError(false);
            mFragmentForgetBinding.getModel().setSubmitEnabled(!mPasswordCustomEditContainer.getIsError() && !mAccountCustomEditContainer.getIsError());
        } else {
            mFragmentForgetBinding.getModel().setSubmitEnabled(false);
            mPasswordCustomEditContainer.setError(true);
        }
    }

    @Override
    public void onFragmentResume(String tag, Bundle arg) {

    }

    @Override
    protected void initDagger() {
        ((LoginActivity) getBaseActivity()).getLoginComponent().inject(this);
    }
}
