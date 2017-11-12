package com.basaki.bc.fips.random;

import java.security.SecureRandom;
import java.security.Security;
import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * {@code SecureRandomNumberExampleTest} contains unit tests related to
 * {@code SecureRandomNumberExample}.
 *
 * @author Indra Basak
 * @since 11/12/2017
 */
public class SecureRandomNumberExampleTest {

    @Before
    public void setUp() {
        Security.addProvider(new BouncyCastleFipsProvider());
    }

    @Test
    public void testBuildDrbg() {
        SecureRandom random = SecureRandomNumberExample.buildDrbg();
        assertNotNull(random);

        byte[] seed = random.generateSeed(10);
        assertEquals(10, seed.length);
    }

    @Test
    public void testBuildDrbgForKeys() {
        SecureRandom random = SecureRandomNumberExample.buildDrbgForKeys();
        assertNotNull(random);

        byte[] seed = random.generateSeed(10);
        assertEquals(10, seed.length);
    }
}
