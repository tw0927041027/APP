package tw.edu.nutc.imac.blockchainfingerprint.data.prefs;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by 依杰 on 2017/8/16.
 */
@Singleton
public class PreferencesHelperImp implements PreferencesHelper {

    private String SharedPreferencesKey = "SharedPreferencesKey";

    private static final String PREF_KEY_ISLOGIN = "PREF_KEY_ISLOGIN";

    private final String PREF_KEY_USER_ACCOUNT = "PREF_KEY_USER_ACCOUNT";

    private final String PREF_KEY_USER_PASSWORD = "PREF_KEY_USER_PASSWORD";

    private final String PREF_KEY_USER_TOKEN = "PREF_KEY_USER_TOKEN";

    private final SharedPreferences mSharedPreferences;

    @Inject
    public PreferencesHelperImp(Application context) {
        mSharedPreferences = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
    }

    @Override
    public boolean gethasLogin() {
        return mSharedPreferences.getBoolean(PREF_KEY_ISLOGIN, false);
    }

    @Override
    public void sethasLogin(boolean hasLogin) {
        mSharedPreferences.edit().putBoolean(PREF_KEY_ISLOGIN, hasLogin).apply();
    }

    @Override
    public String getToken() {
        return mSharedPreferences.getString(PREF_KEY_USER_TOKEN, null);
    }

    @Override
    public void setToken(String token) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_TOKEN, token).apply();
    }

    @Override
    public void setAccount(String account) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_ACCOUNT, account).apply();
    }

    @Override
    public String getAccount() {
        return mSharedPreferences.getString(PREF_KEY_USER_ACCOUNT, null);
    }

    @Override
    public void setPassword(String password) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_PASSWORD, password).apply();
    }

    @Override
    public String getPassword() {
        return mSharedPreferences.getString(PREF_KEY_USER_PASSWORD, null);
    }

    @Override
    public void clearSharedPreferences() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
