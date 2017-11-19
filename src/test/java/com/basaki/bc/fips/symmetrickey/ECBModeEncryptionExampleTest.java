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
 * {@code ECBModeEncryptionExampleTest} contains unit tests
 * related to {@code ECBModeEncryptionExample}.
 *
 * @author Indra Basak
 * @since 11/12/2017
 */
public class ECBModeEncryptionExampleTest {

    @Before
    public void setUp() {
        Security.addProvider(new BouncyCastleFipsProvider());
    }

    @Test
    public void testEcbEncryptDecrypt() throws GeneralSecurityException {
        SecretKey key = KeyCreationExample.generateKey();
        byte[] plaintext = "Indra".getBytes();

        byte[] ciphertext =
                ECBModeEncryptionExample.ecbEncrypt(key,
                        plaintext);
        assertNotNull(ciphertext);

        byte[] derivedPlainText =
                ECBModeEncryptionExample.ecbDecrypt(key,
                        ciphertext);
        assertNotNull(derivedPlainText);
        assertArrayEquals(plaintext, derivedPlainText);
    }
}
