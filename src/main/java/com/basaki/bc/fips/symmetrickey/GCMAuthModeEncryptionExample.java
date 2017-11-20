package com.basaki.bc.fips.symmetrickey;

import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import org.bouncycastle.util.encoders.Hex;

/**
 * {@code GCMAuthModeEncryptionExample} contains examples of encrypting and
 * decrypting in authenticated GCM (Galois/Counter Mode) mode. It is based on
 * CTR (Counter) mode and has its own hashing function.
 *
 * @author Indra Basak
 * @since 11/119/2017
 */
@SuppressWarnings({"squid:S1118"})
public class GCMAuthModeEncryptionExample {

    /**
     * Encrypts data in authenticated  GCM (Galois/Counter Mode) mode.
     * <p>
     * Example 11a
     *
     * @param key  the secret key used for encryption
     * @param data the plaintext to be encrypted
     * @return an array containing algorithm parameters ([0]) and encrypted
     * ciphertext ([1]).
     * @throws GeneralSecurityException
     */
    public static Object[] gcmEncrypt(SecretKey key, byte[] data)
            throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BCFIPS");
        cipher.init(Cipher.ENCRYPT_MODE, key, // taglength, nonce
                new GCMParameterSpec(128,
                        Hex.decode("000102030405060708090a0b")));
        return new Object[]{cipher.getParameters(), cipher.doFinal(data),};
    }

    /**
     * Decrypts the data in in authenticated  GCM (Galois/Counter Mode) mode.
     * <p>
     * Example 11b
     *
     * @param key           the secret key used for decryption
     * @param gcmParameters algorithm parameters containing checksum for
     *                      validating decryption
     * @param cipherText    an encrypted ciphertext
     * @return
     * @throws GeneralSecurityException
     */
    public static byte[] gcmDecrypt(SecretKey key,
            AlgorithmParameters gcmParameters,
            byte[] cipherText) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BCFIPS");
        cipher.init(Cipher.DECRYPT_MODE, key, gcmParameters);
        return cipher.doFinal(cipherText);
    }
}
