package tw.edu.nutc.imac.blockchainfingerprint.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.BlockChainApplication;
import tw.edu.nutc.imac.blockchainfingerprint.di.ApplicationComponent;
import tw.edu.nutc.imac.blockchainfingerprint.util.CommonUtils;

/**
 * Created by 依杰 on 2017/8/9.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView, BaseFragment.Callback {

    private ProgressDialog mProgressDialog;

    public T getPresenter() {
        return presenter;
    }

    @Inject
    T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDagger(((BlockChainApplication) getApplication()).getAppComponent());
        presenter.onAttach(this);
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onHint(int message) {
        CommonUtils.showErrorDialog(this, message).show();
    }

    @Override
    public void onFragmentDetached(Bundle arg, String... tag) {

    }

    protected abstract void initDagger(ApplicationComponent appComponent);
}
