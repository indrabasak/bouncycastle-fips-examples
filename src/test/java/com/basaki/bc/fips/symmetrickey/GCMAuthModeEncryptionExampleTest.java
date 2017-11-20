package com.basaki.bc.fips.symmetrickey;

import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.Security;
import javax.crypto.SecretKey;
import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * {@code GCMAuthModeEncryptionExampleTest} contains unit tests
 * related to {@code GCMAuthModeEncryptionExample}.
 *
 * @author Indra Basak
 * @since 11/19/2017
 */
public class GCMAuthModeEncryptionExampleTest {

    @Before
    public void setUp() {
        Security.addProvider(new BouncyCastleFipsProvider());
    }

    @Test
    public void testCbcEncryptDecrypt() throws GeneralSecurityException {
        SecretKey key = KeyCreationExample.generateKey();
        byte[] plaintext = "Indra".getBytes();

        Object[] gcmParamsCiphertext =
                GCMAuthModeEncryptionExample.gcmEncrypt(key,
                        plaintext);

        AlgorithmParameters gcmParams =
                (AlgorithmParameters) gcmParamsCiphertext[0];
        assertNotNull(gcmParams);
        byte[] ciphertext = (byte[]) gcmParamsCiphertext[1];
        assertNotNull(ciphertext);

        byte[] derivedPlainText =
                GCMAuthModeEncryptionExample.gcmDecrypt(key, gcmParams,
                        ciphertext);
        assertNotNull(derivedPlainText);
        assertArrayEquals(plaintext, derivedPlainText);
    }
}
