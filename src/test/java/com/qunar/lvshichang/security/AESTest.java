package com.qunar.lvshichang.security;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Date: 18/03/04
 * User: lvshi
 */
public class AESTest {
    //32 byte
    public static final byte[] DEFAULT_KEY = {(byte) 0xF3, (byte) 0x62,
            (byte) 0x12, (byte) 0x05, (byte) 0x13, (byte) 0xE3, (byte) 0x89,
            (byte) 0xFF, (byte) 0x23, (byte) 0x11, (byte) 0xD7, (byte) 0x36,
            (byte) 0x01, (byte) 0x23, (byte) 0x10, (byte) 0x07, (byte) 0x05,
            (byte) 0xA2, (byte) 0x10, (byte) 0x00, (byte) 0x7A, (byte) 0xCC,
            (byte) 0x02, (byte) 0x3C, (byte) 0x39, (byte) 0x01, (byte) 0xDA,
            (byte) 0x2E, (byte) 0xCB, (byte) 0x12, (byte) 0x44, (byte) 0x8B};
    private static final byte[] AES_IV = {0x15, (byte) 0xFF, 0x01, 0x00, 0x34,
            (byte) 0xAB, 0x4C, (byte) 0xD3, 0x55, (byte) 0xFE, (byte) 0xA1,
            0x22, 0x08, 0x4F, 0x13, 0x07};

    @Test
    public void AES256Test() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        SecretKeySpec secretKeySpec = new SecretKeySpec(DEFAULT_KEY, "AES");
        IvParameterSpec paramSpec = new IvParameterSpec(AES_IV);
        Cipher ecipher;
        ecipher = Cipher.getInstance("AES/CFB/NOPADDING");
        ecipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, paramSpec);
        byte[] result = ecipher.doFinal("abc".getBytes());
        System.out.println(Arrays.toString(result));
        ecipher.init(Cipher.DECRYPT_MODE, secretKeySpec, paramSpec);
        byte[] bytes = ecipher.doFinal(result);
        System.out.println(Arrays.toString(bytes));
    }
}
