package com.google.appinventor.components.runtime.util.crypt;

import android.util.Base64;
import android.util.Log;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class KodularAES128 {
    public KodularAES128() {
    }

    public static String encode(String str, String str2) {
        StringBuilder sb;
        Key key;
        AlgorithmParameterSpec algorithmParameterSpec;
        String str3 = str;
        Object[] objArr = new Object[1];
        Object[] objArr2 = objArr;
        objArr[0] = String.format("%.16s", new Object[]{str2});
        String format = String.format("%16s", objArr2);
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            byte[] bytes = format.getBytes("UTF-8");
            new SecretKeySpec(bytes, "AES");
            new IvParameterSpec(bytes);
            instance.init(1, key, algorithmParameterSpec);
            return Base64.encodeToString(instance.doFinal(str3.getBytes("UTF8")), 0);
        } catch (Exception e) {
            new StringBuilder("Encrypt Exception : ");
            int d = Log.d("KodularAES128", sb.append(e.getMessage()).toString());
            return "";
        }
    }

    public static String decode(String str, String str2) {
        StringBuilder sb;
        Key key;
        AlgorithmParameterSpec algorithmParameterSpec;
        String str3;
        String str4 = str;
        Object[] objArr = new Object[1];
        Object[] objArr2 = objArr;
        objArr[0] = String.format("%.16s", new Object[]{str2});
        String format = String.format("%16s", objArr2);
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            byte[] bytes = format.getBytes("UTF-8");
            new SecretKeySpec(bytes, "AES");
            new IvParameterSpec(bytes);
            instance.init(2, key, algorithmParameterSpec);
            String str5 = str3;
            new String(instance.doFinal(Base64.decode(str4.getBytes("UTF8"), 0)), "UTF-8");
            return str5;
        } catch (Exception e) {
            new StringBuilder("decrypt Exception : ");
            int d = Log.d("KodularAES128", sb.append(e.getMessage()).toString());
            return "";
        }
    }

    public static String generateKey() {
        StringBuilder sb;
        try {
            KeyGenerator instance = KeyGenerator.getInstance("AES");
            instance.init(128);
            return Base64.encodeToString(instance.generateKey().getEncoded(), 0);
        } catch (NoSuchAlgorithmException e) {
            new StringBuilder("generateKey: ");
            int d = Log.d("KodularAES128", sb.append(e.getMessage()).toString());
            return "";
        }
    }
}
