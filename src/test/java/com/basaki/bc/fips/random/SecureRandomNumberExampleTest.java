package com.basaki.bc.fips.random;

import com.basaki.bc.fips.random.SecureRandomNumberExample;
import java.security.SecureRandom;
import java.security.Security;
import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SecureRandomNumberExampleTest {

    @Before
    public void setUp() {
        Security.addProvider(new BouncyCastleFipsProvider());
    }

    @Test
    public void testBuildDrbg() {
        SecureRandom random = SecureRandomNumberExample.buildDrbg();
        assertNotNull(random);
    }
}
