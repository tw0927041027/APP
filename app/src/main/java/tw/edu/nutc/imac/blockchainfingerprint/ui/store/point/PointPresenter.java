package tw.edu.nutc.imac.blockchainfingerprint.ui.store.point;

import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenterImp;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProvider;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProviderImp;

/**
 * Created by 依杰 on 2018/7/6.
 */

public class PointPresenter extends BasePresenterImp<PointContract.View> implements PointContract.Presenter {
    private SchedulerProvider mSchedulerProvider;

    @Inject
    public PointPresenter(SchedulerProviderImp schedulerProviderImp) {
        mSchedulerProvider = schedulerProviderImp;
    }

    @Override
    public void onAttach(PointContract.View view) {
        super.onAttach(view);
        getView().setModel(new PointModel());
    }

    @Override
    public void onPointChangeEvent(String Point) {

    }
}
