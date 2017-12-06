package com.qunar.lvshichang.security;

import com.google.common.base.Charsets;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.*;
import java.util.Arrays;
import java.util.Map;

public class MD5Test {


    @Test
    public void printProvider(){
        for (Provider provider : Security.getProviders()) {
            System.out.println(provider.getName());
            for (Map.Entry<Object, Object> entry : provider.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    @Test
    public void md5Test() throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String s = "hello";
        byte[] digest = md5.digest(s.getBytes(Charsets.UTF_8));
        md5.update(s.getBytes(Charsets.UTF_8));

        System.out.println(Arrays.toString(digest));

        MessageDigest md51 = MessageDigest.getInstance("MD5");
        md51.update(s.getBytes(Charsets.UTF_8));
        byte[] digest1 = md51.digest();
        System.out.println(MessageDigest.isEqual(digest,digest1));
    }

    @Test
    public void shaTest() throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        String s = "hello";
        byte[] digest = sha.digest(s.getBytes(Charsets.UTF_8));
        System.out.println(Arrays.toString(digest));

    }


    @Test
    public void  streamTest() throws IOException, NoSuchAlgorithmException {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\lvshi\\IdeaProjects\\campus\\src\\test\\resources\\logback.xml");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        DigestInputStream digestInputStream = new DigestInputStream(inputStream, md5);
        byte[] bytes = IOUtils.readFully(digestInputStream, 500);
        byte[] digest = md5.digest();
        System.out.println(Arrays.toString(digest));
        System.out.println(new String(digest,Charsets.UTF_8));
        char[] chars = Hex.encodeHex(digest);
        System.out.println(Hex.encodeHexString(digest));
        System.out.println(new String(chars));
    }

}
