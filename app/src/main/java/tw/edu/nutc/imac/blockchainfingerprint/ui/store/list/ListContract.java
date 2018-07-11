package tw.edu.nutc.imac.blockchainfingerprint.ui.store.list;

import android.os.Bundle;

import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenter;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BaseView;

/**
 * Created by 依杰 on 2018/6/12.
 */

public interface ListContract {

    interface View extends BaseView {
        void setModel(ListModel listModel);

        void showChangePointPage(Bundle args);
    }

    interface Presenter extends BasePresenter<View> {
        void onChangePointClickEvent(ListModel listModel);
    }

    interface Adapt {
        void onItemClick(ListModel listModel);
    }
}
