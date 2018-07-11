package tw.edu.nutc.imac.blockchainfingerprint.ui.store.setting;

import android.app.KeyguardManager;
import android.widget.Toast;

import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.R;
import tw.edu.nutc.imac.blockchainfingerprint.data.prefs.PreferencesHelper;
import tw.edu.nutc.imac.blockchainfingerprint.data.prefs.PreferencesHelperImp;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenterImp;

/**
 * Created by 依杰 on 2018/7/10.
 */

public class SettingPresenter extends BasePresenterImp<SettingContract.View> implements SettingContract.Presenter {
    private PreferencesHelper mPreferencesHelper;

    private KeyguardManager mKeyguardManager;

    @Inject
    public SettingPresenter(PreferencesHelperImp preferencesHelperImp, KeyguardManager keyguardManager) {
        mPreferencesHelper = preferencesHelperImp;

        mKeyguardManager = keyguardManager;
    }

    @Override
    public void onAttach(SettingContract.View view) {
        super.onAttach(view);
        SettingModel settingModel = new SettingModel();
        settingModel.setIsLock(mPreferencesHelper.getLock());
        getView().setModel(settingModel);
    }

    @Override
    public void onSetIsLockEvent(boolean isLock) {
        if (!mKeyguardManager.isKeyguardSecure()) {
            getView().onHint(R.string.setting_error_lock);
            return;
        }
        mPreferencesHelper.setIsLock(isLock);
    }
}
