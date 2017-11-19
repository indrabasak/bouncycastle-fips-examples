package com.basaki.bc.fips.symmetrickey;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * {@code CBCModeWithCTSEncryptionExample} contains examples of
 * encrypting and decrypting in CBC (Cipher Block Chaining) mode with
 * CTS (Ciphertext Stealing).
 * <p>
 * CTS is used in conjunction with CBC mode and can be used where there is at
 * least 2 blocks of data. It requires no padding, as the “stealing” process
 * allows it to produce a cipher text which is the same length as the plain
 * text. The most popular one is CS3.
 *
 * @author Indra Basak
 * @since 11/19/2017
 */
@SuppressWarnings({"squid:S1118"})
public class CBCModeWithCTSEncryptionExample {

    /**
     * Encrypts data in CBC (Cipher Block Chaining) mode with CTS3 (Ciphertext
     * Stealing).
     * <p>
     * Example 10a
     *
     * @param key  the secret key used for encryption
     * @param data the plaintext to be encrypted
     * @return an encrypted ciphertext as a byte array and the initialization
     * vector
     * @throws GeneralSecurityException
     */
    public static byte[][] ctsEncrypt(SecretKey key, byte[] data)
            throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CBC/CS3Padding", "BCFIPS");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return new byte[][]{cipher.getIV(), cipher.doFinal(data)};
    }

    /**
     * Decrypts the data in CBC (Cipher Block Chaining) mode with
     * CTS3 (Ciphertext Stealing).
     * <p>
     * Example 10b
     *
     * @param key        the secret key used for decryption
     * @param iv         initialization vector
     * @param cipherText an encrypted ciphertext
     * @return
     * @throws GeneralSecurityException
     */
    public static byte[] ctsDecrypt(SecretKey key, byte[] iv,
            byte[] cipherText) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CBC/CS3Padding", "BCFIPS");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal(cipherText);
    }
}
