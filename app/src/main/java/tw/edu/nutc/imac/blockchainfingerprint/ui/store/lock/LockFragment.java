package tw.edu.nutc.imac.blockchainfingerprint.ui.store.lock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import tw.edu.nutc.imac.blockchainfingerprint.R;
import tw.edu.nutc.imac.blockchainfingerprint.databinding.FragmentLockBinding;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.StoreActivity;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.list.ListFragment;
import tw.edu.nutc.imac.blockchainfingerprint.util.CommonUtils;
import tw.edu.nutc.imac.blockchainfingerprint.util.view.CustomEditContainer;


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

    }

    @Override
    public void onFragmentResume(String tag, Bundle arg) {

    }

    @Override
    protected void initDagger() {
        ((StoreActivity) getBaseActivity()).getStoreComponent().inject(this);
    }

    @Override
    public void setModel(LockModel lockModel) {
        mFragmentLockBinding.setData(lockModel);
    }

    @Override
    public void onSubmitClicked() {
        getPresenter().onConnectLDAP(mFragmentLockBinding.getData().getAccount());
    }

    @Override
    public void onCreateClick() {
        getPresenter().onCreateLDAP();
    }

    @Override
    public EditText.OnFocusChangeListener focusChangeListener() {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mAccountCustomEditContainer.setFocus(true);
            }
        };
    }

    @Override
    public void showStoreList() {
        CommonUtils.TransFragment(getBaseActivity(), R.id.main_content, ListFragment.newInstance(), ListFragment.TAG, null);
    }

    @Override
    public void onDestroy() {
        getPresenter().onDetach();
        super.onDestroy();
    }
}
