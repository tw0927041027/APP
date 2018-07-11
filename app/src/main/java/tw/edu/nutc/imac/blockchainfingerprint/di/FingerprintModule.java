/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package tw.edu.nutc.imac.blockchainfingerprint.di;

import android.app.KeyguardManager;
import android.content.Context;
import android.security.keystore.KeyProperties;
import android.view.inputmethod.InputMethodManager;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import dagger.Module;
import dagger.Provides;
import tw.edu.nutc.imac.blockchainfingerprint.ui.store.StoreActivity;
import tw.edu.nutc.imac.blockchainfingerprint.util.store.StoreBackend;
import tw.edu.nutc.imac.blockchainfingerprint.util.store.StoreBackendImpl;

@Module
public class FingerprintModule {

    @Provides
    public KeyguardManager provideKeyguardManager(StoreActivity activity) {
        return activity.getSystemService(KeyguardManager.class);
    }

    @Provides
    public KeyStore provideKeystore() {
        try {
            return KeyStore.getInstance("AndroidKeyStore");
        } catch (KeyStoreException e) {
            throw new RuntimeException("Failed to get an instance of KeyStore", e);
        }
    }

    @Provides
    public KeyGenerator provideKeyGenerator() {
        try {
            return KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException("Failed to get an instance of KeyGenerator", e);
        }
    }

    @Provides
    public SecretKey provideSecretKey(KeyStore keyStore) {
        try {
            return (SecretKey) keyStore.getKey("Key_Name", null);
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new RuntimeException("Failed to get an instance of SecretKey", e);
        }
    }

    @Provides
    public Cipher provideCipher() {
        try {
            return Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get an instance of Cipher", e);
        }
    }

    @Provides
    public InputMethodManager provideInputMethodManager(StoreActivity context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Provides
    public StoreBackend provideStoreBackend() {
        return new StoreBackendImpl();
    }

}
