package com.basaki.bc.fips.symmetrickey;

import java.security.GeneralSecurityException;
import java.security.Security;
import javax.crypto.SecretKey;
import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * {@code CTREncryptionDecryptionExampleTest} contains unit tests
 * related to {@code CTREncryptionDecryptionExample}.
 *
 * @author Indra Basak
 * @since 11/18/2017
 */
public class CTREncryptionDecryptionExampleTest {

    @Before
    public void setUp() {
        Security.addProvider(new BouncyCastleFipsProvider());
    }

    @Test
    public void testCtrEncryptDecrypt() throws GeneralSecurityException {
        SecretKey key = KeyCreationExample.generateKey();
        byte[] plaintext = "Indra".getBytes();

        byte[][] ivCiphertext =
                CTREncryptionDecryptionExample.ctrEncrypt(key,
                        plaintext);

        byte[] iv = ivCiphertext[0];
        assertNotNull(iv);
        byte[] ciphertext = ivCiphertext[1];
        assertNotNull(ciphertext);

        byte[] derivedPlainText =
                CTREncryptionDecryptionExample.ctrDecrypt(key, iv,
                        ciphertext);
        assertNotNull(derivedPlainText);
        assertArrayEquals(plaintext, derivedPlainText);
    }
}
