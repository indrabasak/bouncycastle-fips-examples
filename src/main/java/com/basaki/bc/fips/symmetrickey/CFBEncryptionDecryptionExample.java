package com.basaki.bc.fips.symmetrickey;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * {@code CFBEncryptionDecryptionExample} contains examples of encrypting and
 * decrypting in CFB (Cipher Feedback) mode. It is similar to CBC while
 * using a streaming block mode. However, padding is no longer required
 * as the cipher generates a stream of "noise" which is XOR'd with the data
 * to be encrypted.
 *
 * @author Indra Basak
 * @since 11/18/2017
 */
public class CFBEncryptionDecryptionExample {

    /**
     * Encrypts data in CFB (Cipher Feedback) mode.
     * <p>
     * Example 8a
     *
     * @param key  the secret key used for encryption
     * @param data the plaintext to be encrypted
     * @return
     * @throws GeneralSecurityException
     */
    public static byte[][] cfbEncrypt(SecretKey key, byte[] data)
            throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding", "BCFIPS");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return new byte[][]{cipher.getIV(), cipher.doFinal(data)};
    }

    /**
     * Decrypts the data in CFB (Cipher Feedback) mode.
     * <p>
     * Example 8b
     *
     * @param key        the secret key used for decryption
     * @param iv         initialization vector
     * @param cipherText an encrypted ciphertext
     * @return
     * @throws GeneralSecurityException
     */
    public static byte[] cfbDecrypt(SecretKey key, byte[] iv,
            byte[] cipherText) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding", "BCFIPS");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal(cipherText);
    }
}
