package com.google.appinventor.components.runtime.util.crypt;

import android.util.Base64;
import java.io.UnsupportedEncodingException;

public class KodularBase64 {
    public KodularBase64() {
    }

    public static String encode(String str) {
        return Base64.encodeToString(str.getBytes(), 0);
    }

    public static String decode(String str) {
        String str2;
        String str3 = str;
        try {
            String str4 = str2;
            new String(Base64.decode(str3, 0), "UTF-8");
            return str4;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str3;
        }
    }
}
