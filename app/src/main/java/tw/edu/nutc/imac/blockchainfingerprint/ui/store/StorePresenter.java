package tw.edu.nutc.imac.blockchainfingerprint.ui.store;

import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.data.prefs.PreferencesHelper;
import tw.edu.nutc.imac.blockchainfingerprint.data.prefs.PreferencesHelperImp;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenterImp;

/**
 * Created by 依杰 on 2018/7/10.
 */

public class StorePresenter extends BasePresenterImp<StoreContract.View> implements StoreContract.Presenter {
    private PreferencesHelper mPreferencesHelper;

    @Inject
    public StorePresenter(PreferencesHelperImp preferencesHelperImp) {
        mPreferencesHelper = preferencesHelperImp;
    }

    @Override
    public void onAttach(StoreContract.View view) {
        super.onAttach(view);
        getView().setModel(new StoreModel());
    }

    @Override
    public boolean getLockState() {
        return mPreferencesHelper.getLock();
    }
}
