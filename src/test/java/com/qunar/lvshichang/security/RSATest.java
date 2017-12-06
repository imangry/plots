package com.qunar.lvshichang.security;

import org.junit.Test;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class RSATest {

    @Test
    public void rsaTest() throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        rsa.initialize(1024);
        KeyPair keyPair = rsa.generateKeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();
        byte[] encoded = aPrivate.getEncoded();
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(encoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

}
