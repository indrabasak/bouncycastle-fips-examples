package com.basaki.bc.fips.symmetrickey;

import java.security.GeneralSecurityException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * {@code KeyCreationExample} contains examples related to creation of
 * symmetric key generations.
 * <p>
 * BC FIPS API offers both approved mode symmetric ciphers, AES
 * and TripleDES, and also a number of other symmetric ciphers that appear
 * in IETF and ISO standards such as ARC4, Blowfish, Camellia, CAST5, DES,
 * GOST28147, IDEA, RC2, SEED, Serpent, SHACAL2, and Twofish.
 * <p>
 * A key's strength is related to its bit length.
 *
 * @author Indra Basak
 * @since 11/12/2017
 */
@SuppressWarnings({"squid:S1118"})
public class KeyCreationExample {

    /**
     * Generates an AES key by providing a key size in bits.
     * <p>
     * Example 4
     *
     * @return a symmetric key
     * @throws GeneralSecurityException
     */
    public static SecretKey generateKey()
            throws GeneralSecurityException {
        // specifies the symmetric block cipher and the BC provider (BCFIPS)
        // In this case, Advanced Encryption Standard (AES) symmetric block
        // cipher is used
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "BCFIPS");

        // uses default SecureRandom
        // KeyGenerator.init() method can also be passed a SecureRandom if
        // one wishes to specify the source of randomness  to be used.
        // Java API also provides provision for the use of
        // AlgorithmParameterSpec classes to initialize key generators.
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    /**
     * Constructs an AES key from a provided byte array by using a
     * SecretKeySpec.
     * <p>
     * Example 5
     *
     * @param keyBytes a byte array used as seed data for creating the key
     * @return a symmetric key
     */
    public static SecretKey defineKey(byte[] keyBytes) {
        if (keyBytes.length != 16 && keyBytes.length != 24 && keyBytes.length != 32) {
            throw new IllegalArgumentException(
                    "keyBytes wrong length for AES key");
        }
        return new SecretKeySpec(keyBytes, "AES");
    }
}
