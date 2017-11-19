package com.basaki.bc.fips.symmetrickey;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.util.encoders.Hex;

/**
 * {@code CTREncryptionDecryptionExample} contains examples of encrypting and
 * decrypting in CTR (Counter) mode. It is a block streaming mode with more
 * control than CFB (Cipher Feedback) mode. The IV (initialization vector) is
 * broken up into two parts: a random nonce, and a counter.
 * <p>
 * It differs from CFB mode in the way cipher stream is gernerated
 * by encrypting the nonce and counter. The use of the nonce and counter
 * means that the cipher stream can be generated in a random access
 * fashion.
 *
 * @author Indra Basak
 * @since 11/18/2017
 */
public class CTREncryptionDecryptionExample {


    /**
     * Encrypts data in CTR (Counter) mode.
     * <p>
     * Example 9a
     *
     * @param key  the secret key used for encryption
     * @param data the plaintext to be encrypted
     * @return
     * @throws GeneralSecurityException
     */
    public static byte[][] ctrEncrypt(SecretKey key, byte[] data)
            throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding", "BCFIPS");
        cipher.init(Cipher.ENCRYPT_MODE, key,
                new IvParameterSpec(Hex.decode("000102030405060708090a0b")));
        return new byte[][]{cipher.getIV(), cipher.doFinal(data)};
    }

    /**
     * Decrypts the data in CTR (Counter) mode.
     * <p>
     * Example 9b
     *
     * @param key        the secret key used for decryption
     * @param iv         initialization vector
     * @param cipherText an encrypted ciphertext
     * @return
     * @throws GeneralSecurityException
     */
    public static byte[] ctrDecrypt(SecretKey key, byte[] iv,
            byte[] cipherText) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding", "BCFIPS");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal(cipherText);
    }
}
