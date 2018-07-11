package tw.edu.nutc.imac.blockchainfingerprint.ui.store.lock;

import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.R;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenterImp;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProvider;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProviderImp;

/**
 * Created by 依杰 on 2018/7/6.
 */

public class LockPresenter extends BasePresenterImp<LockContract.View> implements LockContract.Presenter {
    private SchedulerProvider mSchedulerProvider;

    @Inject
    public LockPresenter(SchedulerProviderImp schedulerProviderImp) {
        mSchedulerProvider = schedulerProviderImp;
    }

    @Override
    public void onAttach(LockContract.View view) {
        super.onAttach(view);
        getView().setModel(new LockModel());
    }

    @Override
    public void onConnectLDAP(String account) {
        if (account == null || account.isEmpty()) {
            getView().onHint(R.string.lock_error_account_empty);
            return;
        }
        getView().showStoreList();
    }

    @Override
    public void onCreateLDAP() {
        getView().showStoreList();
    }
}
