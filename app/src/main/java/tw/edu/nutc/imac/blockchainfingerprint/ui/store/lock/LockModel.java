package tw.edu.nutc.imac.blockchainfingerprint.ui.store.lock;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by 依杰 on 2018/7/6.
 */

public class LockModel extends BaseObservable {
    private String mAccount;
    private String mPassword;
    private boolean mSubmitEnabled;

    @Bindable
    public String getAccount() {
        return mAccount;
    }

    public void setAccount(String account) {
        mAccount = account;
        notifyPropertyChanged(BR.account);
    }

    @Bindable
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
        notifyPropertyChanged(BR.password);
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
