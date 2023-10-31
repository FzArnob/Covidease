package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.Arrays;

public class KodularGDPRUtil {
    private static String LOG_TAG = "Kodular GDPR Util";
    private static String[] sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt;

    static {
        String[] strArr = new String[28];
        strArr[0] = "at";
        String[] strArr2 = strArr;
        strArr2[1] = "be";
        String[] strArr3 = strArr2;
        strArr3[2] = "bg";
        String[] strArr4 = strArr3;
        strArr4[3] = "cy";
        String[] strArr5 = strArr4;
        strArr5[4] = "cz";
        String[] strArr6 = strArr5;
        strArr6[5] = "de";
        String[] strArr7 = strArr6;
        strArr7[6] = "dk";
        String[] strArr8 = strArr7;
        strArr8[7] = "ee";
        String[] strArr9 = strArr8;
        strArr9[8] = "es";
        String[] strArr10 = strArr9;
        strArr10[9] = "fi";
        String[] strArr11 = strArr10;
        strArr11[10] = "fr";
        String[] strArr12 = strArr11;
        strArr12[11] = "gb";
        String[] strArr13 = strArr12;
        strArr13[12] = "gr";
        String[] strArr14 = strArr13;
        strArr14[13] = "hr";
        String[] strArr15 = strArr14;
        strArr15[14] = "hu";
        String[] strArr16 = strArr15;
        strArr16[15] = "ie";
        String[] strArr17 = strArr16;
        strArr17[16] = "it";
        String[] strArr18 = strArr17;
        strArr18[17] = "lt";
        String[] strArr19 = strArr18;
        strArr19[18] = "lu";
        String[] strArr20 = strArr19;
        strArr20[19] = "lv";
        String[] strArr21 = strArr20;
        strArr21[20] = "mt";
        String[] strArr22 = strArr21;
        strArr22[21] = "nl";
        String[] strArr23 = strArr22;
        strArr23[22] = "pl";
        String[] strArr24 = strArr23;
        strArr24[23] = "pt";
        String[] strArr25 = strArr24;
        strArr25[24] = "ro";
        String[] strArr26 = strArr25;
        strArr26[25] = "se";
        String[] strArr27 = strArr26;
        strArr27[26] = "si";
        String[] strArr28 = strArr27;
        strArr28[27] = "sk";
        sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = strArr28;
    }

    private KodularGDPRUtil() {
    }

    public static boolean isRequestLocationInEurope(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        TelephonyManager telephonyManager2 = telephonyManager;
        if (telephonyManager.getNetworkCountryIso() != null) {
            String networkCountryIso = telephonyManager2.getNetworkCountryIso();
            int i = Log.i(LOG_TAG, "Current user country is: ".concat(String.valueOf(networkCountryIso)));
            if (networkCountryIso != null) {
                boolean contains = Arrays.asList(sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt).contains(networkCountryIso);
                int i2 = Log.i(LOG_TAG, "Current user is in EUROPE: ".concat(String.valueOf(contains)));
                return contains;
            }
            int w = Log.w(LOG_TAG, "There was a error to retrieve the current user location.");
            return true;
        }
        int e = Log.e(LOG_TAG, "It was not possible to get the country code. We returned the value true as default to cover the EU privacy policy.");
        return true;
    }
}
