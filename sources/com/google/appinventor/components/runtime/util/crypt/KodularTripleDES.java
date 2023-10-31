package com.google.appinventor.components.runtime.util.crypt;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class KodularTripleDES {
    public KodularTripleDES() {
    }

    public static String encode(String str, String str2) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Key key;
        String str3;
        String str4 = str;
        byte[] copyOf = Arrays.copyOf(MessageDigest.getInstance("md5").digest(str2.getBytes("utf-8")), 24);
        int i = 0;
        int i2 = 16;
        while (i < 8) {
            int i3 = i2;
            i2++;
            int i4 = i;
            i++;
            copyOf[i3] = copyOf[i4];
        }
        new SecretKeySpec(copyOf, "DESede");
        Key key2 = key;
        Cipher instance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        instance.init(1, key2);
        new String(Base64.encode(instance.doFinal(str4.getBytes("utf-8")), 0));
        return str3;
    }

    public static String decode(String str, String str2) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Key key;
        String str3;
        String str4 = str;
        String str5 = str2;
        if (str4 == null) {
            return "";
        }
        byte[] decode = Base64.decode(str4, 0);
        byte[] copyOf = Arrays.copyOf(MessageDigest.getInstance("MD5").digest(str5.getBytes("utf-8")), 24);
        int i = 0;
        int i2 = 16;
        while (i < 8) {
            int i3 = i2;
            i2++;
            int i4 = i;
            i++;
            copyOf[i3] = copyOf[i4];
        }
        new SecretKeySpec(copyOf, "DESede");
        Key key2 = key;
        Cipher instance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        instance.init(2, key2);
        new String(instance.doFinal(decode), "UTF-8");
        return str3;
    }
}
