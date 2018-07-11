package tw.edu.nutc.imac.blockchainfingerprint.data.prefs;

/**
 * Created by 依杰 on 2017/8/16.
 */

public interface PreferencesHelper {

    boolean gethasLogin();

    void sethasLogin(boolean hasLogin);

    String getToken();

    void setToken(String token);

    void setAccount(String account);

    String getAccount();

    void setPassword(String password);

    String getPassword();

    void setIsLock(boolean isLock);

    boolean getLock();

    void clearSharedPreferences();
}
