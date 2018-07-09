package tw.edu.nutc.imac.blockchainfingerprint.ui.items.lock;

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
    public void onUnlockEvent(LockModel model) {
        if (!model.isSubmitEnabled()){
            getView().onHint(R.string.error_account);
            return;
        }
    }
}
