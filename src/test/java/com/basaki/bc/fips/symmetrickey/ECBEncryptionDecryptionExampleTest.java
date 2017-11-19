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
 * {@code ECBEncryptionDecryptionExampleTest} contains unit tests
 * related to {@code ECBEncryptionDecryptionExample}.
 *
 * @author Indra Basak
 * @since 11/12/2017
 */
public class ECBEncryptionDecryptionExampleTest {

    @Before
    public void setUp() {
        Security.addProvider(new BouncyCastleFipsProvider());
    }

    @Test
    public void testEcbEncryptDecrypt() throws GeneralSecurityException {
        SecretKey key = KeyCreationExample.generateKey();
        byte[] plaintext = "Indra".getBytes();

        byte[] ciphertext =
                ECBEncryptionDecryptionExample.ecbEncrypt(key,
                        plaintext);
        assertNotNull(ciphertext);

        byte[] derivedPlainText =
                ECBEncryptionDecryptionExample.ecbDecrypt(key,
                        ciphertext);
        assertNotNull(derivedPlainText);
        assertArrayEquals(plaintext, derivedPlainText);
    }
}
