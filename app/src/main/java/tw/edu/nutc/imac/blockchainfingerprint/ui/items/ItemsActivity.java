package tw.edu.nutc.imac.blockchainfingerprint.ui.items;

import android.databinding.DataBindingUtil;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.ECGenParameterSpec;

import javax.inject.Inject;

import tw.edu.nutc.imac.blockchainfingerprint.R;
import tw.edu.nutc.imac.blockchainfingerprint.databinding.ActivityItemBinding;
import tw.edu.nutc.imac.blockchainfingerprint.ui.items.lock.LockFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.items.point.PointFragment;
import tw.edu.nutc.imac.blockchainfingerprint.ui.user.BaseUserActivity;
import tw.edu.nutc.imac.blockchainfingerprint.ui.user.UserComponent;
import tw.edu.nutc.imac.blockchainfingerprint.util.CommonUtils;
import tw.edu.nutc.imac.blockchainfingerprint.util.fingerprint.FingerprintAuthenticationDialogFragment;
import tw.edu.nutc.imac.blockchainfingerprint.util.scheduler.SchedulerProviderImp;

/**
 * Created by 依杰 on 2018/6/12.
 */

public class ItemsActivity extends BaseUserActivity<ItemsContract.Presenter> implements ItemsContract.View {

    private ItemsComponent itemsComponent;

    private ActivityItemBinding mActivityItemBinding;

    public static final String KEY_NAME = "my_key";

    private final String DIALOG_FRAGMENT_TAG = "myFragment";

    @Inject
    public LinearLayoutManager mLinearLayoutManager;
    @Inject
    public SchedulerProviderImp schedulerProviderImp;
    @Inject
    public KeyPairGenerator mKeyPairGenerator;
    @Inject
    public FingerprintAuthenticationDialogFragment mFragment;
    @Inject
    public KeyStore mKeyStore;
    @Inject
    public Signature mSignature;
    @Inject
    public ItemsAdapt mItemsAdapt;

    public ItemsComponent getItemsComponent() {
        return itemsComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mActivityItemBinding = DataBindingUtil.setContentView(this, R.layout.activity_item);
        super.onCreate(savedInstanceState);
        mActivityItemBinding.setView(this);

        createKeyPair();
        init();
    }

    private void init() {
        RecyclerView list = mActivityItemBinding.recyclerView;

        list.setLayoutManager(mLinearLayoutManager);

        mItemsAdapt.setHasStableIds(true);

        list.setAdapter(mItemsAdapt);

        //fake data
        ItemModel itemModel = new ItemModel();
        itemModel.setPosition(0);
        itemModel.setIsLock(true);
        itemModel.setStoreName("店家二");
        mItemsAdapt.addItem(itemModel);

        ItemModel itemModel1 = new ItemModel();
        itemModel1.setPosition(1);
        itemModel1.setStoreName("店家三");
        itemModel1.setIsLock(false);
        mItemsAdapt.addItem(itemModel1);
    }

    @Override
    protected void inject(UserComponent userComponent) {
        itemsComponent = userComponent.itemsComponentBuilder()
                .activity(this)
                .build();
        itemsComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (initSignature()) {
            mFragment.setCryptoObject(new FingerprintManager.CryptoObject(mSignature));
            mFragment.show(getFragmentManager(), DIALOG_FRAGMENT_TAG);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFragment.dismiss();
    }

    public void createKeyPair() {
        // The enrolling flow for fingerprint. This is where you ask the user to set up fingerprint
        // for your flow. Use of keys is necessary if you need to know if the set of
        // enrolled fingerprints has changed.
        try {
            // Set the alias of the entry in Android KeyStore where the key will appear
            // and the constrains (purposes) in the constructor of the Builder
            mKeyPairGenerator.initialize(
                    new KeyGenParameterSpec.Builder(KEY_NAME,
                            KeyProperties.PURPOSE_SIGN)
                            .setDigests(KeyProperties.DIGEST_SHA256)
                            .setAlgorithmParameterSpec(new ECGenParameterSpec("secp256r1"))
                            // Require the user to authenticate with a fingerprint to authorize
                            // every use of the private key
                            .setUserAuthenticationRequired(true)
                            .build());
            mKeyPairGenerator.generateKeyPair();
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean initSignature() {
        try {
            mKeyStore.load(null);
            PrivateKey key = (PrivateKey) mKeyStore.getKey(KEY_NAME, null);
            mSignature.initSign(key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    public void onPurchased() {
//    getPresenter().onLoginEvent();
//    Log.e("onPurchased", "asd");
//    Intent intent = new Intent(this, ItemsActivity.class);
//    startActivity(intent);
//    finish();
    }

    @Override
    public void showChangePointPage(Bundle args) {
        CommonUtils.TransFragment(this, R.id.main_content, PointFragment.newInstance(), PointFragment.TAG, args);
    }

    @Override
    public void showUnLockPage(Bundle args) {
        CommonUtils.TransFragment(this, R.id.main_content, LockFragment.newInstance(), LockFragment.TAG, args);
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

        if (null != fragmentManager.findFragmentByTag(LockFragment.TAG)) {
            onFragmentDetached(null, LockFragment.TAG);
        } else if (null != fragmentManager.findFragmentByTag(PointFragment.TAG)) {
            onFragmentDetached(null, PointFragment.TAG);
        } else {
            super.onBackPressed();
        }
    }
}
