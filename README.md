[![Build Status][travis-badge]][travis-badge-url]

![](./img/bouncyjava.gif)

Bouncy Castle FIPS Java API Examples
================================================
FIPS (Federal Information Processing Standards) are a set of standards 
for describing document processing and encryption algorithms. Any application 
involved in transmission of sensistive data in US government departments
and agencies must adhere to FIPS 140-2 standards.

Examples here relate to Bouncy Castle implementation of 
[Java FIPS API](https://www.bouncycastle.org/fips_faq.html) and are stated in
[The Bouncy Castle FIPS Java API in 100 Examples](https://www.bouncycastle.org/fips-java/BCFipsIn100.pdf).

- `SecureRandomNumberExample.java` contains examples of generating secure
random numbers. Secured random is very important in cryptography as random 
values are used in key and IV generation.

### Build
Execute the following command from the parent directory:
```
mvn clean install
```


[travis-badge]: https://travis-ci.org/indrabasak/bouncycastle-fips-examples.svg?branch=master
[travis-badge-url]: https://travis-ci.org/indrabasak/bouncycastle-fips-examples/