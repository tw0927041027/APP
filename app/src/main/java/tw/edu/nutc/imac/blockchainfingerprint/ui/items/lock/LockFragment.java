package tw.edu.nutc.imac.blockchainfingerprint.ui.items.lock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import tw.edu.nutc.imac.blockchainfingerprint.R;
import tw.edu.nutc.imac.blockchainfingerprint.databinding.FragmentLockBinding;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.items.ItemsActivity;
import tw.edu.nutc.imac.blockchainfingerprint.util.CommonUtils;
import tw.edu.nutc.imac.blockchainfingerprint.util.view.CustomEditContainer;

import static tw.edu.nutc.imac.blockchainfingerprint.ui.items.ItemsPresenter.BUNDLE_STORENAME_KEY;

/**
 * Created by 依杰 on 2018/7/6.
 */

public class LockFragment extends BaseFragment<LockContract.Presenter> implements LockContract.View {
    public static final String TAG = LockFragment.class.getSimpleName();

    public static LockFragment newInstance() {
        return new LockFragment();
    }

    private FragmentLockBinding mFragmentLockBinding;

    private CustomEditContainer mAccountCustomEditContainer;

    private CustomEditContainer mPasswordCustomEditContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentLockBinding = FragmentLockBinding.inflate(inflater, container, false);

        mFragmentLockBinding.setView(this);

        return mFragmentLockBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAccountCustomEditContainer = mFragmentLockBinding.containerAccount;

        mPasswordCustomEditContainer = mFragmentLockBinding.containerPassword;
    }

    @Override
    public void onFragmentResume(String tag, Bundle arg) {

    }

    @Override
    protected void initDagger() {
        ((ItemsActivity) getBaseActivity()).getItemsComponent().inject(this);
    }

    @Override
    public void setModel(LockModel lockModel) {
        if (getArguments() != null && null != getArguments().getString(BUNDLE_STORENAME_KEY)) {
            lockModel.setStoreName(getArguments().getString(BUNDLE_STORENAME_KEY));
        }
        mFragmentLockBinding.setData(lockModel);
    }

    @Override
    public void onSubmitClicked() {
        getPresenter().onUnlockEvent(mFragmentLockBinding.getData());
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
            mFragmentLockBinding.getData().setSubmitEnabled(!mPasswordCustomEditContainer.getIsError() && !mAccountCustomEditContainer.getIsError());
        } else {
            mFragmentLockBinding.getData().setSubmitEnabled(false);
            mAccountCustomEditContainer.setError(true);
        }
    }

    @Override
    public void onPasswordTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 5 || s.length() == 0) {
            mPasswordCustomEditContainer.setError(false);
            mFragmentLockBinding.getData().setSubmitEnabled(!mPasswordCustomEditContainer.getIsError() && !mAccountCustomEditContainer.getIsError());
        } else {
            mFragmentLockBinding.getData().setSubmitEnabled(false);
            mPasswordCustomEditContainer.setError(true);
        }
    }
}
