package tw.edu.nutc.imac.blockchainfingerprint.ui.store.setting;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by 依杰 on 2018/7/10.
 */

public class SettingModel extends BaseObservable {

    private boolean mIsLock;

    @Bindable
    public boolean getIsLock() {
        return mIsLock;
    }

    public void setIsLock(boolean isLock) {
        mIsLock = isLock;
        notifyPropertyChanged(BR.isPointClick);
    }
}
