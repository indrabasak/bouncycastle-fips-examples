package com.basaki.bc.fips.random;

import com.basaki.bc.fips.common.ExValues;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.EntropySourceProvider;
import org.bouncycastle.crypto.fips.FipsDRBG;
import org.bouncycastle.crypto.util.BasicEntropySourceProvider;

/**
 * {@code SecureRandomNumberExample} contains examples of generating secure
 * random numbers. Secured random is very important in cryptography as random
 * values are used in key and IV generation.
 * <p>
 * National Institute of Standards and Technology(NIST)
 * Special Publication 800-90A describes three different deterministic
 * random bit generators (DRBGs).
 *
 * @author Indra Basak
 * @since 11/12/2017
 */
@SuppressWarnings({"squid:S1118"})
public class SecureRandomNumberExample {


    /**
     * An example of creating DRBG suitable for creation of initialization
     * vectors (IVs) or other similar random data (nonces).
     *
     * @return a secure random suitable for IVs
     */
    public static SecureRandom buildDrbg() {
        // The generateSeed() method of a regular SecureRandom is used as the
        // entropy source. This makes use of any entropy source the running
        // JVM is configured for.
        EntropySourceProvider entSource =
                new BasicEntropySourceProvider(new SecureRandom(), true);

        FipsDRBG.Builder drgbBldr =
                FipsDRBG.SHA512_HMAC.fromEntropySource(entSource)
                        .setSecurityStrength(256)
                        .setEntropyBitsRequired(256);

        // 1. The returned SecureRandom is not created via the Java provider.
        // It returns FipsSecureRandom which extends SecureRandom.
        // The extension class is necessary as a NIST DRBG requires methods
        // such as FipsSecureRandom.reseed()

        // 2. The “prediction resistance” parameter on the FipsDRBG.Builder.build()
        // method is set to false. It's set to false, as it's assumed that
        // the DRBG function will do a good job in producing a random stream.
        // In the case of keys or components of keys, a higher standard
        // is needed. It is achieved by setting to be “prediction resistance”
        // to true.
        return drgbBldr.build(ExValues.Nonce, false);
    }

    /**
     * An example of creating a FIPS approved {@code SecureRandom} for keys.
     *
     * @return a secure random suitable for key
     */
    public static SecureRandom buildDrbgForKeys() {
        // The generateSeed() method of a regular SecureRandom is used as the
        // entropy source. This makes use of any entropy source the running
        // JVM is configured for.
        EntropySourceProvider entSource =
                new BasicEntropySourceProvider(new SecureRandom(), true);

        // "setPersonalizationString" is used to reduce the likelihood of
        // two DRBGs somehow producing the same key stream where the entropy
        // source turns out to be similar.
        // The the personalization string can be a secret or not but it needs
        // to be unique.
        FipsDRBG.Builder drgbBldr =
                FipsDRBG.SHA512_HMAC.fromEntropySource(entSource)
                        .setSecurityStrength(256)
                        .setEntropyBitsRequired(256)
                        .setPersonalizationString(
                                ExValues.PersonalizationString);

        // The “prediction resistance” is set to to true as higer
        // standards are needed for keys or components of keys.
        return drgbBldr.build(ExValues.Nonce, true);
    }

    /**
     * Setting up default DRBG (deterministic random bit generator) where
     * JCA/JCE provider is not used. Examples of where unexpected randomness
     * requirements can come up includes RSA blinding.
     * In the case of the BC FIPS API, rather than relying on “new
     * SecureRandom()” the API executes a call to
     * CryptoServicesRegistrar.getSecureRandom() which will
     * throw an exception if a default has not been provided.
     */
    public static void setDefaultDrbg() {
        EntropySourceProvider entSource =
                new BasicEntropySourceProvider(new SecureRandom(), true);
        FipsDRBG.Builder drgbBldr = FipsDRBG.SHA512.fromEntropySource(entSource)
                .setSecurityStrength(256)
                .setEntropyBitsRequired(256);

        // A default SecureRandom can be set using
        // CryptoServicesRegistrar.setSecureRandom.
        CryptoServicesRegistrar.setSecureRandom(
                drgbBldr.build(ExValues.Nonce, true));
    }
}
