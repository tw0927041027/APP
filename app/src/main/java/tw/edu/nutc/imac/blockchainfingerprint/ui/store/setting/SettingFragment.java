package tw.edu.nutc.imac.blockchainfingerprint.ui.store.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.databinding.FragmentSettingBinding;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.StoreActivity;

/**
 * Created by 依杰 on 2018/7/10.
 */

public class SettingFragment extends BaseFragment<SettingContract.Presenter> implements SettingContract.View {
    public static final String TAG = SettingFragment.class.getSimpleName();

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    private FragmentSettingBinding mFragmentSettingBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentSettingBinding = FragmentSettingBinding.inflate(inflater, container, false);

        mFragmentSettingBinding.setView(this);

        return mFragmentSettingBinding.getRoot();
    }

    @Override
    public void setModel(SettingModel lockModel) {
        mFragmentSettingBinding.setData(lockModel);
    }

    @Override
    public void onFragmentResume(String tag, Bundle arg) {

    }

    @Override
    protected void initDagger() {
        ((StoreActivity) getBaseActivity()).getStoreComponent().inject(this);
    }

    @Override
    public void onPause() {
        getPresenter().onSetIsLockEvent(mFragmentSettingBinding.getData().getIsLock());
        super.onPause();
    }

    @Override
    public void onDestroy() {
        getPresenter().onDetach();
        super.onDestroy();
    }
}
