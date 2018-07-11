package tw.edu.nutc.imac.blockchainfingerprint.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;

import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.util.CommonUtils;

/**
 * Created by 依杰 on 2017/8/10.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    public T getPresenter() {
        return presenter;
    }

    @Inject
    T presenter;

    private BaseActivity mActivity;

    private ProgressDialog mProgressDialog;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDagger();
        presenter.onAttach(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        presenter.onDetach();
        super.onDetach();
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(mActivity);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    @Override
    public void onHint(@StringRes int message) {
        if (mActivity != null) {
            mActivity.onHint(message);
        }
    }

    public abstract void onFragmentResume(String tag, Bundle arg);

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public interface Callback {

        void onFragmentDetached(Bundle arg, String... tag);
    }

    protected abstract void initDagger();
}
