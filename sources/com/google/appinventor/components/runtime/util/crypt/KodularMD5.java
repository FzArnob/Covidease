package com.google.appinventor.components.runtime.util.crypt;

import android.util.Log;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KodularMD5 {
    public KodularMD5() {
    }

    public static String createHash(String str) {
        StringBuilder sb;
        BigInteger bigInteger;
        try {
            new BigInteger(1, MessageDigest.getInstance("MD5").digest(str.getBytes()));
            String bigInteger2 = bigInteger.toString(16);
            while (true) {
                String str2 = bigInteger2;
                if (str2.length() >= 32) {
                    return str2;
                }
                bigInteger2 = "0".concat(String.valueOf(str2));
            }
        } catch (NoSuchAlgorithmException e) {
            new StringBuilder();
            int e2 = Log.e("MakeroidCrypt", sb.append(e.getMessage()).toString());
            return "";
        }
    }
}
