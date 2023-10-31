package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

@KeepForSdk
public class HttpUtils {
    private static final Pattern zzha = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
    private static final Pattern zzhb = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
    private static final Pattern zzhc = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

    @KeepForSdk
    public static Map<String, String> parse(URI uri, String str) {
        Map map;
        Scanner scanner;
        Throwable th;
        String str2 = str;
        Map emptyMap = Collections.emptyMap();
        String rawQuery = uri.getRawQuery();
        String str3 = rawQuery;
        if (rawQuery != null && str3.length() > 0) {
            new HashMap();
            emptyMap = map;
            new Scanner(str3);
            Scanner scanner2 = scanner;
            Scanner scanner3 = scanner2;
            Scanner useDelimiter = scanner2.useDelimiter("&");
            while (scanner3.hasNext()) {
                String[] split = scanner3.next().split("=");
                String[] strArr = split;
                if (split.length == 0 || strArr.length > 2) {
                    Throwable th2 = th;
                    new IllegalArgumentException("bad parameter");
                    throw th2;
                }
                String decode = decode(strArr[0], str2);
                String str4 = null;
                if (strArr.length == 2) {
                    str4 = decode(strArr[1], str2);
                }
                Object put = emptyMap.put(decode, str4);
            }
        }
        return emptyMap;
    }

    private static String decode(String str, String str2) {
        Throwable th;
        String str3 = str2;
        try {
            return URLDecoder.decode(str, str3 != null ? str3 : "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            Throwable th2 = th;
            new IllegalArgumentException(unsupportedEncodingException);
            throw th2;
        }
    }

    private HttpUtils() {
    }
}
