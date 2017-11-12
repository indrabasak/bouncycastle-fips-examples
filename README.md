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

- `SecureRandomNumberExample.java` contains examples of generating secure
random numbers. Secured random is very important in cryptography as random 
values are used in key and IV generation.

## Build
Execute the following command from the parent directory:
```
mvn clean install
```


[travis-badge]: https://travis-ci.org/indrabasak/bouncycastle-fips-examples.svg?branch=master
[travis-badge-url]: https://travis-ci.org/indrabasak/bouncycastle-fips-examples/