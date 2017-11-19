package com.basaki.bc.fips.symmetrickey;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

/**
 * {@code ECBEncryptionDecryptionExample} contains examples of encrypting and
 * decrypting in Electronic Code Book (ECB) mode. ECB like many other
 * block cipher modes is unpadded. The input has to be aligned on the
 * block boundaries of the cipher - in this case 128 bits.
 *
 * @author Indra Basak
 * @since 11/12/2017
 */
@SuppressWarnings({"squid:S1118"})
public class ECBEncryptionDecryptionExample {

    /**
     * Encrypts data in Electronic Code Book (ECB) mode.
     * <p>
     * Example 6a
     *
     * @param key  the secret key used for encryption
     * @param data the plaintext to be encrypted
     * @return an encrypted ciphertext as a byte array
     * @throws GeneralSecurityException
     */
    public static byte[] ecbEncrypt(SecretKey key, byte[] data)
            throws GeneralSecurityException {
        // since many block cipher modes is unpadded so the input has to be
        // aligned on the block boundaries of the cipher - in this case 128 bits.
        // To get around this restriction, “PKCS7Padding” is specified instead
        // of "NoPadding" to allow for non-block aligned data
        // PKCS7Padding is often also referred to as PKCS5Padding and the
        // BC APIs provide a number of other padding mechanisms such as
        // ISO10126-2, X9.23, ISO7816-4, and TBC (trailing bit compliment)
        // padding.
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BCFIPS");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * Decrypts the data in in Electronic Code Book (ECB) mode.
     * <p>
     * Example 6b
     *
     * @param key        the secret key used for encryption
     * @param cipherText the ciphertext to be decrypted
     * @return a decrypted plaintext as a byte array
     * @throws GeneralSecurityException
     */
    public static byte[] ecbDecrypt(SecretKey key, byte[] cipherText)
            throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BCFIPS");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }


}
