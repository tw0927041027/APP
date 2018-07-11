package tw.edu.nutc.imac.blockchainfingerprint.ui.store;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by 依杰 on 2018/7/10.
 */

public class StoreModel extends BaseObservable {
    private boolean mIsPointClick;

    private boolean mIsSettingClick;

    @Bindable
    public boolean getIsPointClick() {
        return mIsPointClick;
    }

    public void setIsPointClick(boolean isPointClick) {
        mIsPointClick = isPointClick;
        notifyPropertyChanged(BR.isPointClick);
    }

    @Bindable
    public boolean getIsSettingClick() {
        return mIsSettingClick;
    }

    public void setIsSettingClick(boolean isSettingClick) {
        mIsSettingClick = isSettingClick;
        notifyPropertyChanged(BR.isSettingClick);
    }
}
