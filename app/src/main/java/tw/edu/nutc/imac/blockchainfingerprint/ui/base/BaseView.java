package tw.edu.nutc.imac.blockchainfingerprint.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by 依杰 on 2017/8/9.
 */

public interface BaseView {

    void showLoading();

    void hideLoading();

    void hideKeyboard();

    void onHint(@StringRes int message);
}
