package com.qunar.lvshichang.security;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Base64;

public class Base64Test {


    @Test
    public void base() throws UnsupportedEncodingException {
        byte[] encode = Base64.getEncoder().encode("hello".getBytes(Charsets.UTF_8));
        byte[] bytes = org.apache.commons.codec.binary.Base64.encodeBase64("hello".getBytes(Charsets.UTF_8));
        System.out.println(Arrays.toString(encode));
        String encodeStr = new String(encode, Charsets.UTF_8);
        System.out.println(encodeStr);
        System.out.println(Arrays.toString(bytes));
        System.out.println(new String(bytes, Charsets.UTF_8));
        byte[] decode = Base64.getDecoder().decode(encodeStr.getBytes(Charsets.UTF_8));
        System.out.println(new String(decode,Charsets.UTF_8));


        byte[] encode1 = Base64.getUrlEncoder().encode("https://zh.wikipedia.org/wiki/编码".getBytes(Charsets.UTF_8));
        System.out.println(new String(encode1,Charsets.UTF_8));
        String encode2 = URLEncoder.encode("https://zh.wikipedia.org/wiki/编码", "UTF-8");
        System.out.println(encode2);
    }
}
