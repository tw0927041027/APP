package tw.edu.nutc.imac.blockchainfingerprint.ui.base;

/**
 * Created by 依杰 on 2017/8/9.
 */

public interface BasePresenter<V extends BaseView> {
    void onAttach(V view);

    void onDetach();
}
