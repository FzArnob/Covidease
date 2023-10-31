package com.google.appinventor.components.runtime.util.crypt;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KodularSHA512 {
    public KodularSHA512() {
    }

    public static String sha512(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuilder sb;
        StringBuilder sb2;
        Object obj;
        String str2 = str;
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-512");
            MessageDigest messageDigest = instance;
            instance.reset();
            messageDigest.update(str2.getBytes("utf8"));
            new BigInteger(1, messageDigest.digest());
            return String.format("%040x", new Object[]{obj});
        } catch (NoSuchAlgorithmException e) {
            new StringBuilder();
            int e2 = Log.e("MakeroidCrypt", sb2.append(e.getMessage()).toString());
            return "";
        } catch (UnsupportedEncodingException e3) {
            new StringBuilder();
            int e4 = Log.e("MakeroidCrypt", sb.append(e3.getMessage()).toString());
            return "";
        }
    }
}
