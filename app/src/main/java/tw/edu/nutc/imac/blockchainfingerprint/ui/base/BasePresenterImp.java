package tw.edu.nutc.imac.blockchainfingerprint.ui.base;

import java.lang.ref.WeakReference;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by 依杰 on 2017/8/9.
 */

public class BasePresenterImp<V extends BaseView> implements BasePresenter<V> {

    private WeakReference<V> mView;

    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();


    @Override
    public void onAttach(V view) {
        if (mView == null) mView = new WeakReference<>(view);
    }

    @Override
    public void onDetach() {
        mCompositeSubscription.clear();
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    public V getView() {
        return mView.get();
    }
}
