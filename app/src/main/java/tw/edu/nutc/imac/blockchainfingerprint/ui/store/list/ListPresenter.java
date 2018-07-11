package tw.edu.nutc.imac.blockchainfingerprint.ui.store.list;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.data.entity.User;
import tw.edu.nutc.imac.blockchainfingerprint.ui.base.BasePresenterImp;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.list.ListContract;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.list.ListModel;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.lock.LockContract;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.lock.LockModel;

/**
 * Created by 依杰 on 2018/6/12.
 */

public class ListPresenter extends BasePresenterImp<ListContract.View> implements ListContract.Presenter {
    public static String BUNDLE_STORENAME_KEY = "BUNDLE_STORENAME";

    @Inject
    public ListPresenter(User user) {
    }

    @Override
    public void onAttach(ListContract.View view) {
        super.onAttach(view);
        getView().setModel(new ListModel());
    }

    @Override
    public void onChangePointClickEvent(ListModel listModel) {
        Bundle arg = new Bundle();
        arg.putString(BUNDLE_STORENAME_KEY, listModel.getStoreName());
        getView().showChangePointPage(arg);
    }

}
