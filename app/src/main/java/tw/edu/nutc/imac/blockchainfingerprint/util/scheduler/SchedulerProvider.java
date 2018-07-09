package tw.edu.nutc.imac.blockchainfingerprint.util.scheduler;

import rx.Scheduler;

/**
 * Created by 依杰 on 2017/8/10.
 */

public interface SchedulerProvider {
    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
