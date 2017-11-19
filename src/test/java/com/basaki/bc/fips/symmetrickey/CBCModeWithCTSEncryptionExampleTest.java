package com.basaki.bc.fips.symmetrickey;

import java.security.GeneralSecurityException;
import java.security.Security;
import javax.crypto.SecretKey;
import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * {@code CBCModeWithCTSEncryptionExampleTest} contains unit tests
 * related to {@code CBCModeWithCTSEncryptionExample}.
 *
 * @author Indra Basak
 * @since 11/19/2017
 */
@Ignore
public class CBCModeWithCTSEncryptionExampleTest {

    @Before
    public void setUp() {
        Security.addProvider(new BouncyCastleFipsProvider());
    }

    @Test
    public void testCbcEncryptDecrypt() throws GeneralSecurityException {
        SecretKey key = KeyCreationExample.generateKey();
        byte[] plaintext = "Indra".getBytes();

        byte[][] ivCiphertext =
                CBCModeWithCTSEncryptionExample.ctsEncrypt(key,
                        plaintext);

        byte[] iv = ivCiphertext[0];
        assertNotNull(iv);
        byte[] ciphertext = ivCiphertext[1];
        assertNotNull(ciphertext);

        byte[] derivedPlainText =
                CBCModeWithCTSEncryptionExample.ctsDecrypt(key, iv,
                        ciphertext);
        assertNotNull(derivedPlainText);
        assertArrayEquals(plaintext, derivedPlainText);
    }
}
