package tw.edu.nutc.imac.blockchainfingerprint.ui.store;

import android.app.KeyguardManager;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.security.keystore.UserNotAuthenticatedException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.R;
import tw.edu.nutc.imac.blockchainfingerprint.databinding.ActivityStoreBinding;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.list.ListFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.lock.LockFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.point.PointFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.setting.SettingFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.user.BaseUserActivity;
import tw.edu.nutc.imac.blockchainfingerprint.ui.user.UserComponent;
import tw.edu.nutc.imac.blockchainfingerprint.util.CommonUtils;

/**
 * Created by 依杰 on 2018/6/12.
 */

public class StoreActivity extends BaseUserActivity<StoreContract.Presenter> implements StoreContract.View {

    private StoreComponent itemsComponent;

    private ActivityStoreBinding mActivityStoreBinding;

    private static final byte[] SECRET_BYTE_ARRAY = new byte[]{1, 2, 3, 4, 5, 6};

    private static final int AUTHENTICATION_DURATION_SECONDS = 1;

    private static final int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 1;

    @Inject
    public KeyGenerator mKeyGenerator;
    @Inject
    public KeyStore mKeyStore;
    @Inject
    public KeyguardManager mKeyguardManager;
    @Inject
    public Cipher mCipher;

    public StoreComponent getStoreComponent() {
        return itemsComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mActivityStoreBinding = DataBindingUtil.setContentView(this, R.layout.activity_store);
        super.onCreate(savedInstanceState);
        mActivityStoreBinding.setView(this);
    }

    @Override
    protected void inject(UserComponent userComponent) {
        itemsComponent = userComponent.storeComponentBuilder()
                .activity(this)
                .build();
        itemsComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getPresenter().getLockState()) {
            createKey();
            tryEncrypt();
        }
    }

    private void createKey() {
        // Generate a key to decrypt payment credentials, tokens, etc.
        // This will most likely be a registration step for the user when they are setting up your app.
        try {
            mKeyStore.load(null);
            // Set the alias of the entry in Android KeyStore where the key will appear
            // and the constrains (purposes) in the constructor of the Builder

            mKeyGenerator.init(new KeyGenParameterSpec.Builder("Key_Name",
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    // Require that the user has unlocked in the last 30 seconds
                    .setUserAuthenticationValidityDurationSeconds(AUTHENTICATION_DURATION_SECONDS)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            mKeyGenerator.generateKey();
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | CertificateException | IOException e) {
            throw new RuntimeException("Failed to create a symmetric key", e);
        }
    }

    private boolean tryEncrypt() {
        try {
            mKeyStore.load(null);

            // Try encrypting something, it will only work if the user authenticated within
            // the last AUTHENTICATION_DURATION_SECONDS seconds.
            mCipher.init(Cipher.ENCRYPT_MODE, (SecretKey) mKeyStore.getKey("Key_Name", null));
            mCipher.doFinal(SECRET_BYTE_ARRAY);

            return true;
        } catch (UserNotAuthenticatedException e) {
            Intent intent = mKeyguardManager.createConfirmDeviceCredentialIntent(null, null);
            if (intent != null) {
                startActivityForResult(intent, REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS);
            }
            return false;
        } catch (KeyPermanentlyInvalidatedException e) {
            // This happens if the lock screen has been disabled or reset after the key was
            // generated after the key was generated.
            Toast.makeText(this, "Keys are invalidated after created. Retry the purchase\n"
                            + e.getMessage(),
                    Toast.LENGTH_LONG).show();
            return false;
        } catch (BadPaddingException | IllegalBlockSizeException |
                CertificateException | IOException | KeyStoreException | UnrecoverableKeyException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            // Challenge completed, proceed with using cipher
            if (resultCode == RESULT_OK) {
                if (tryEncrypt()) {
                }
            } else {
                // The user canceled or didn’t complete the lock screen
                // operation. Go to error/cancellation flow.
                Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFragmentDetached(Bundle arg, String... tag) {
        super.onFragmentDetached(arg, tag);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag[0]);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (null != fragmentManager.findFragmentByTag(PointFragment.TAG)) {
            onFragmentDetached(null, PointFragment.TAG);
            return;
        }

        if (null != fragmentManager.findFragmentByTag(ListFragment.TAG)) {
            onFragmentDetached(null, ListFragment.TAG);
            return;
        }

        if (null != fragmentManager.findFragmentByTag(LockFragment.TAG)) {
            onFragmentDetached(null, LockFragment.TAG);
        } else if (null != fragmentManager.findFragmentByTag(SettingFragment.TAG)) {
            onFragmentDetached(null, SettingFragment.TAG);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void setModel(StoreModel model) {
        mActivityStoreBinding.setModel(model);
    }

    @Override
    public void onPointClick() {
        CommonUtils.TransFragment(this, R.id.main_content, LockFragment.newInstance(), LockFragment.TAG, null);
    }

    @Override
    public void onSettingClick() {
        CommonUtils.TransFragment(this, R.id.main_content, SettingFragment.newInstance(), SettingFragment.TAG, null);
    }
}
