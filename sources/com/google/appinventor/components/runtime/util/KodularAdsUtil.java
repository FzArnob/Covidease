package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KodularAdsUtil {
    public static final String ADMOB_BANNER_TEST_ID = "ca-app-pub-3940256099942544/6300978111";
    public static final String ADMOB_INTERSTITIAL_TEST_ID = "ca-app-pub-3940256099942544/1033173712";
    public static final String ADMOB_KODULAR_BANNER_ID = "ca-app-pub-9439286719204795/2208552876";
    public static final String ADMOB_KODULAR_INTERSTITIAL_ID = "ca-app-pub-9439286719204795/6225008878";
    public static final String ADMOB_KODULAR_REWARDED_VIDEO_ID = "ca-app-pub-9439286719204795/9992685881";
    public static final String ADMOB_REWARDED_VIDEO_TEST_ID = "ca-app-pub-3940256099942544/5224354917";
    public static final String AD_COLONY_APP_ID = "appb235d39ba30f401783";
    public static final String AD_COLONY_ZONE_ID = "vzc8fd72c1e5494edca6";
    public static final String APP_LOVIN_SDK_KEY = "EbEMcQnBzVuo9cK-oUIPKlMIiouhV1uxC-HlJeLJzP0TQG80dzkM3GgicsTXJvShe-8QJ6GrvmQcPeqltqgEkG";
    private static String LOG_TAG = "Kodular Ads Util";
    public static final String STARTAPP_APP_ID = "204085028";
    public static final String UNITY_ADS_GAME_ID = "3140736";

    private KodularAdsUtil() {
    }

    private static String Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB(String str) {
        StringBuilder sb;
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            new StringBuilder();
            StringBuilder sb2 = sb;
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                StringBuilder append = sb2.append(Integer.toHexString((digest[i] & Ev3Constants.Opcode.TST) | 256).substring(1, 3));
            }
            return sb2.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public static String guessSelfDeviceId(Context context) {
        String upperCase = Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB(Settings.Secure.getString(context.getContentResolver(), "android_id")).toUpperCase();
        int i = Log.i(LOG_TAG, "Device id: ".concat(String.valueOf(upperCase)));
        return upperCase;
    }
}
