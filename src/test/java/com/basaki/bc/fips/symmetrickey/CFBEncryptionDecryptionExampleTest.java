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
 * {@code CFBEncryptionDecryptionExampleTest} contains unit tests
 * related to {@code CFBEncryptionDecryptionExample}.
 *
 * @author Indra Basak
 * @since 11/18/2017
 */
public class CFBEncryptionDecryptionExampleTest {

    @Before
    public void setUp() {
        Security.addProvider(new BouncyCastleFipsProvider());
    }

    @Test
    public void testCfbEncryptDecrypt() throws GeneralSecurityException {
        SecretKey key = KeyCreationExample.generateKey();
        byte[] plaintext = "Indra".getBytes();

        byte[][] ivCiphertext =
                CFBEncryptionDecryptionExample.cfbEncrypt(key,
                        plaintext);

        byte[] iv = ivCiphertext[0];
        assertNotNull(iv);
        byte[] ciphertext = ivCiphertext[1];
        assertNotNull(ciphertext);

        byte[] derivedPlainText =
                CFBEncryptionDecryptionExample.cfbDecrypt(key, iv,
                        ciphertext);
        assertNotNull(derivedPlainText);
        assertArrayEquals(plaintext, derivedPlainText);
    }
}
