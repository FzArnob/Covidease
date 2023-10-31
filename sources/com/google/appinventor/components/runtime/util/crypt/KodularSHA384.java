package com.google.appinventor.components.runtime.util.crypt;

import android.util.Log;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KodularSHA384 {
    public KodularSHA384() {
    }

    public static String sha384(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        try {
            byte[] digest = MessageDigest.getInstance("SHA-384").digest(str.getBytes("UTF-8"));
            new StringBuilder();
            StringBuilder sb4 = sb3;
            for (int i = 0; i < digest.length; i++) {
                StringBuilder append = sb4.append(Integer.toHexString((digest[i] & Ev3Constants.Opcode.TST) | 256).substring(1, 3));
            }
            return sb4.toString();
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
