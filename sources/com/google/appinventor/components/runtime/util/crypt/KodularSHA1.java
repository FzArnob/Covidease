package com.google.appinventor.components.runtime.util.crypt;

import android.util.Log;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KodularSHA1 {
    public KodularSHA1() {
    }

    public static String sha1(String str) throws NoSuchAlgorithmException {
        StringBuilder sb;
        StringBuffer stringBuffer;
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(str.getBytes());
            new StringBuffer();
            StringBuffer stringBuffer2 = stringBuffer;
            for (int i = 0; i < digest.length; i++) {
                StringBuffer append = stringBuffer2.append(Integer.toString((digest[i] & Ev3Constants.Opcode.TST) + 256, 16).substring(1));
            }
            return stringBuffer2.toString();
        } catch (NoSuchAlgorithmException e) {
            new StringBuilder();
            int e2 = Log.e("MakeroidCrypt", sb.append(e.getMessage()).toString());
            return "";
        }
    }
}
