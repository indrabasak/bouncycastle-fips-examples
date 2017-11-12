package com.basaki.bc.fips.symmetrickey;

import java.security.GeneralSecurityException;
import java.security.Security;
import javax.crypto.SecretKey;
import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * {@code KeyCreationExampleTest} contains unit tests related to
 * {@code KeyCreationExample}.
 *
 * @author Indra Basak
 * @since 11/12/2017
 */
public class KeyCreationExampleTest {

    @Before
    public void setUp() {
        Security.addProvider(new BouncyCastleFipsProvider());
    }

    @Test
    public void testGenerateKey() throws GeneralSecurityException {
        SecretKey key = KeyCreationExample.generateKey();
        assertNotNull(key);
    }

    @Test
    public void testDefineKey() throws GeneralSecurityException {
        //AES key length is 16
        byte[] keyBytes = {(byte) 0x1, (byte) 0x2, (byte) 0x3, (byte) 0x4,
                (byte) 0x5, (byte) 0x6, (byte) 0x7, (byte) 0x8,
                (byte) 0x9, (byte) 0x10, (byte) 0x11, (byte) 0x12,
                (byte) 0x13, (byte) 0x14, (byte) 0x15, (byte) 0x16};

        SecretKey key = KeyCreationExample.defineKey(keyBytes);
        assertNotNull(key);
    }
}
