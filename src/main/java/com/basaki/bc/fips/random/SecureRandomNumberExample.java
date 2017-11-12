package com.basaki.bc.fips.random;

import com.basaki.bc.fips.common.ExValues;
import java.security.SecureRandom;
import org.bouncycastle.crypto.EntropySourceProvider;
import org.bouncycastle.crypto.fips.FipsDRBG;
import org.bouncycastle.crypto.util.BasicEntropySourceProvider;

@SuppressWarnings({"squid:S1118"})
public class SecureRandomNumberExample {

    public static SecureRandom buildDrbg() {
        EntropySourceProvider entSource =
                new BasicEntropySourceProvider(new SecureRandom(), true);
        FipsDRBG.Builder drgbBldr =
                FipsDRBG.SHA512_HMAC.fromEntropySource(entSource)
                        .setSecurityStrength(256)
                        .setEntropyBitsRequired(256);
        return drgbBldr.build(ExValues.Nonce, false);
    }

    public static SecureRandom buildDrbgForKeys() {
        EntropySourceProvider entSource =
                new BasicEntropySourceProvider(new SecureRandom(), true);
        FipsDRBG.Builder drgbBldr =
                FipsDRBG.SHA512_HMAC.fromEntropySource(entSource)
                        .setSecurityStrength(256)
                        .setEntropyBitsRequired(256)
                        .setPersonalizationString(
                                ExValues.PersonalizationString);
        return drgbBldr.build(ExValues.Nonce, true);
    }
}
