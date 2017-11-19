[![Build Status][travis-badge]][travis-badge-url]

![](./img/bouncyjava.gif)

Bouncy Castle FIPS Java API Examples
================================================
FIPS (Federal Information Processing Standards) are a set of standards 
for describing document processing and encryption algorithms. Any application 
involved in transmission of sensistive data in US government departments
and agencies must adhere to FIPS 140-2 standards.

## Bouncy Castle Provider Configuration
There are couple of diifferent ways to configure Bouncy Castle FIPS Java provider:

### JRE Security Changes
1. Place the `bc-fips-1.0.0.jar` in the `jre/lib/ext` folder.
1. Make the following changes to `jre/lib/security/java.security` file:
    1. Modify the line following line:
    
    `security.provider.4=com.sun.net.ssl.internal.ssl.Provider` 
    
    to 
    
    `security.provider.4=com.sun.net.ssl.internal.ssl.Provider BCFIPS`
    1. Add the following line: 
    
    `security.provider.11=org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider`
    
     `11` is the priority number for the Bouncy Castle FIPS Java provider.

Please make sure to you use right numbering as they should be consecutive. Here
is an example of list of providers in `jre/lib/security/java.security` file
after the changes:

```
# List of providers and their preference orders (see above):
#
security.provider.1=sun.security.provider.Sun
security.provider.2=sun.security.rsa.SunRsaSign
security.provider.3=sun.security.ec.SunEC
#security.provider.4=com.sun.net.ssl.internal.ssl.Provider
security.provider.4=com.sun.net.ssl.internal.ssl.Provider BCFIPS
security.provider.5=com.sun.crypto.provider.SunJCE
security.provider.6=sun.security.jgss.SunProvider
security.provider.7=com.sun.security.sasl.Provider
security.provider.8=org.jcp.xml.dsig.internal.dom.XMLDSigRI
security.provider.9=sun.security.smartcardio.SunPCSC
security.provider.10=apple.security.AppleProvider
security.provider.11=org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider
```

Once the provider is added, it can be referenced in your code 
using the provider name as `BCFIPS`.

### Application Runtime
By adding the provider during the application execution:

```
import java.security.Security
import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider

Security.addProvider(new BouncyCastleFipsProvider())
```

**In this project, the Bouncy Castle FIPS Java provider is added during
runtime. No changes needed in your JRE.**

## Examples

Examples here relate to Bouncy Castle implementation of 
[Java FIPS API](https://www.bouncycastle.org/fips_faq.html).

These examples can found in [The Bouncy Castle FIPS Java API in 100 Examples](https://www.bouncycastle.org/fips-java/BCFipsIn100.pdf).

### Random Numbers
Secured random is very important in cryptography as random values are used in
 key and IV generation.
 
- `SecureRandomNumberExample.java` contains examples of generating secure
random numbers. (_Example 1, 2, and 3_)

### Symmetric Key
BC FIPS API offers both approved mode symmetric ciphers, AES
and TripleDES, and also a number of other symmetric ciphers such as ARC4, 
Blowfish, Camellia, CAST5, DES, GOST28147, IDEA, RC2, SEED, Serpent, 
SHACAL2, and Twofish.

- `KeyCreationExample.java` contains examples related to creating symmetric 
keys. (_Example 4 and 5_)

- `ECBModeEncryptionExample.java`  contains examples of encrypting and
block cipher modes is unpadded. The input has to be aligned on the
block boundaries of the cipher - in this case 128 bits. (_Example 6_)

- `CBCModeEncryptionExample.java` contains examples of encrypting and
decrypting in CBC (Cipher Block Chaining) mode. Padding needs to be
specified as the CBC mode is block aligned. CBC mode has an extra
parameter, the initialization vector (IV), which is used with the mode to
prevent any similarities in two plain texts from showing up in the
encrypted results. Make sure the IV is reliably random or unique. (_Example 7_)

- `CFBModeEncryptionExample.java` contains examples of encrypting and
decrypting in CFB (Cipher Feedback) mode. It is similar to CBC while
using a streaming block mode. However, padding is no longer required
as the cipher generates a stream of "noise" which is XOR'd with the data
to be encrypted. (_Example 8_)

- `CTRModeEncryptionExample.java` contains examples of encrypting and
decrypting in CTR (Counter) mode. It is a block streaming mode with more
control than CFB (Cipher Feedback) mode. The IV (initialization vector) is
broken up into two parts: a random nonce, and a counter.
It differs from CFB mode in the way cipher stream is gernerated
by encrypting the nonce and counter. The use of the nonce and counter
means that the cipher stream can be generated in a random access fashion. 
(_Example 9_)

- `CBCModeWithCTSEncryptionExample.java` contains examples of encrypting and 
decrypting in CBC (Cipher Block Chaining) mode with CTS (Ciphertext Stealing).
CTS is used in conjunction with CBC mode and can be used where there is at
least 2 blocks of data. It requires no padding, as the “stealing” process
allows it to produce a cipher text which is the same length as the plain
text. The most popular one is CS3. (_Example 10_) Encountered the following 
exception while testing: `javax.crypto.BadPaddingException: Error closing stream`


## Build
Execute the following command from the parent directory:
```
mvn clean install
```


[travis-badge]: https://travis-ci.org/indrabasak/bouncycastle-fips-examples.svg?branch=master
[travis-badge-url]: https://travis-ci.org/indrabasak/bouncycastle-fips-examples/