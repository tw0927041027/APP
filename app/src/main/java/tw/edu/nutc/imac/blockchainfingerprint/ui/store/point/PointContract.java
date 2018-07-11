package tw.edu.nutc.imac.blockchainfingerprint.ui.store.point;

import android.widget.EditText;

import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenter;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseView;

/**
 * Created by 依杰 on 2018/7/6.
 */

public interface PointContract {
    interface View extends BaseView {
        void setModel(PointModel lockModel);

        void onSubmitClicked();

        void onPointTextChanged(CharSequence s, int start, int before, int count);

        EditText.OnFocusChangeListener focusChangeListener();
    }


    interface Presenter extends BasePresenter<View> {
        void onPointChangeEvent(String Point);
    }
}
