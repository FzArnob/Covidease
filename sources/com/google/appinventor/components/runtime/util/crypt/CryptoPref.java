package com.google.appinventor.components.runtime.util.crypt;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Random;

public class CryptoPref {
    public CryptoPref() {
    }

    public static String getSalt(Context context) {
        Random random;
        StringBuilder sb;
        SharedPreferences sharedPreferences = context.getSharedPreferences("CRYPTO_PREF", 0);
        SharedPreferences sharedPreferences2 = sharedPreferences;
        String string = sharedPreferences.getString("TAG_SALT", (String) null);
        String str = string;
        if (string != null) {
            return str;
        }
        new Random();
        Random random2 = random;
        new StringBuilder(100);
        StringBuilder sb2 = sb;
        for (int i = 0; i < 100; i++) {
            StringBuilder append = sb2.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz|!£$%&/=@#".charAt(random2.nextInt(72)));
        }
        SharedPreferences.Editor edit = sharedPreferences2.edit();
        SharedPreferences.Editor putString = edit.putString("TAG_SALT", sb2.toString());
        boolean commit = edit.commit();
        return sb2.toString();
    }
}
