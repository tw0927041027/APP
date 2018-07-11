package tw.edu.nutc.imac.blockchainfingerprint.ui.store.point;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by 依杰 on 2018/7/6.
 */

public class PointModel extends BaseObservable {
    private String mPoint;
    private String mRate = "2";
    private String mStoreName;
    private String mResultPoint = "0";
    private boolean mSubmitEnabled;

    public PointModel() {
    }

    @Bindable
    public String getPoint() {
        return mPoint;
    }

    public void setPoint(String point) {
        mPoint = point;
        notifyPropertyChanged(BR.point);
    }

    @Bindable
    public String getRate() {
        return mRate;
    }

    public void setRate(String rate) {
        this.mRate = rate;
        notifyPropertyChanged(BR.rate);
    }

    @Bindable
    public String getStoreName() {
        return mStoreName;
    }

    public void setStoreName(String storeName) {
        this.mStoreName = storeName;
        notifyPropertyChanged(BR.storeName);
    }

    @Bindable
    public String getResultPoint() {
        return mResultPoint;
    }

    public void setResultPoint(String resultPoint) {
        this.mResultPoint = resultPoint;
        notifyPropertyChanged(BR.resultPoint);
    }

    @Bindable
    public boolean isSubmitEnabled() {
        return mSubmitEnabled;
    }

    public void setSubmitEnabled(boolean submitEnabled) {
        this.mSubmitEnabled = submitEnabled;
        notifyPropertyChanged(BR.submitEnabled);
    }

}
