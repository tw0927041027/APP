package tw.edu.nutc.imac.blockchainfingerprint.ui.items;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by 依杰 on 2018/7/6.
 */

public class ItemModel extends BaseObservable {
    private int mPosition;

    private String mStoreName;

    private boolean mIsLock = true;

    private boolean mIsWhiteBackground;

    public ItemModel() {
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
    public boolean getIsLock() {
        return mIsLock;
    }

    public void setIsLock(boolean isLock) {
        mIsLock = isLock;
        notifyPropertyChanged(BR.isLock);
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
