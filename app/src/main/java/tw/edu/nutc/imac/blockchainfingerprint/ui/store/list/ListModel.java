package tw.edu.nutc.imac.blockchainfingerprint.ui.store.list;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by 依杰 on 2018/7/6.
 */

public class ListModel extends BaseObservable {
    private int mPosition;

    private String mStoreName;

    private boolean mIsWhiteBackground;

    public ListModel() {
    }

    @Bindable
    public String getStoreName() {
        return mStoreName;
    }

    public void setStoreName(String storeName) {
        mStoreName = storeName;
        notifyPropertyChanged(BR.storeName);
    }

    @Bindable
    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
        notifyPropertyChanged(BR.position);
    }

    @Bindable
    public boolean getIsWhiteBackground() {
        return mIsWhiteBackground;
    }

    public void setIsWhiteBackground(boolean isWhiteBackground) {
        mIsWhiteBackground = isWhiteBackground;
        notifyPropertyChanged(BR.isWhiteBackground);
    }
}
