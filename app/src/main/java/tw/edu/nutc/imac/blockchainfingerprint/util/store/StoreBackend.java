package tw.edu.nutc.imac.blockchainfingerprint.util.store;

import java.security.PublicKey;

/**
 * Created by 依杰 on 2018/6/11.
 */

public interface StoreBackend {
    boolean verify(Transaction transaction, byte[] transactionSignature);

    boolean verify(Transaction transaction, String password);

    boolean enroll(String userId, String password, PublicKey publicKey);
}
