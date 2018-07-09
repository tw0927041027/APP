package tw.edu.nutc.imac.blockchainfingerprint.util.scheduler;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 依杰 on 2017/8/10.
 */
@Singleton
public class SchedulerProviderImp implements SchedulerProvider {
    @Inject
    public SchedulerProviderImp() {
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

}
