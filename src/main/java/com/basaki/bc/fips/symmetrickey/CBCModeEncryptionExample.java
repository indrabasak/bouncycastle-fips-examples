package com.basaki.bc.fips.symmetrickey;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * {@code CBCModeEncryptionExample} contains examples of encrypting and
 * decrypting in CBC (Cipher Block Chaining) mode. Padding needs to be
 * specified as the CBC mode is block aligned. CBC mode has an extra
 * parameter, the initialization vector (IV), which is used with the mode to
 * prevent any similarities in two plain texts from showing up in the
 * encrypted results. Make sure the IV is reliably random or unique.
 *
 * @author Indra Basak
 * @since 11/18/2017
 */
@SuppressWarnings({"squid:S1118"})
public class CBCModeEncryptionExample {

    /**
     * Encrypts data in CBC (Cipher Block Chaining) mode.
     * <p>
     * Example 7a
     *
     * @param key  the secret key used for encryption
     * @param data the plaintext to be encrypted
     * @return an encrypted ciphertext as a byte array and the initialization
     * vector
     * @throws GeneralSecurityException
     */
    public static byte[][] cbcEncrypt(SecretKey key, byte[] data)
            throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BCFIPS");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // CBC mode also has an extra parameter, the initialization
        // vector (IV), which is used with the mode to prevent any
        // obvious similarities that might have existed in two plain texts
        // from showing up in the encrypted results
        return new byte[][]{cipher.getIV(), cipher.doFinal(data)};
    }

    /**
     * Decrypts the data in CBC (Cipher Block Chaining) mode.
     * <p>
     * Example 7b
     *
     * @param key        the secret key used for decryption
     * @param iv         initialization vector
     * @param cipherText an encrypted ciphertext
     * @return
     * @throws GeneralSecurityException
     */
    public static byte[] cbcDecrypt(SecretKey key, byte[] iv, byte[] cipherText)
            throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BCFIPS");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal(cipherText);
    }
}
