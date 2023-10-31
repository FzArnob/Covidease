package com.google.android.gms.common.util;

import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@KeepForSdk
@VisibleForTesting
public final class JsonUtils {
    private static final Pattern zzhd = Pattern.compile("\\\\.");
    private static final Pattern zzhe = Pattern.compile("[\\\\\"/\b\f\n\r\t]");

    private JsonUtils() {
    }

    @KeepForSdk
    public static String unescapeString(String str) {
        Throwable th;
        StringBuffer stringBuffer;
        String str2 = str;
        if (TextUtils.isEmpty(str2)) {
            return str2;
        }
        String unescape = zzd.unescape(str2);
        Matcher matcher = zzhd.matcher(unescape);
        StringBuffer stringBuffer2 = null;
        while (matcher.find()) {
            if (stringBuffer2 == null) {
                new StringBuffer();
                stringBuffer2 = stringBuffer;
            }
            switch (matcher.group().charAt(1)) {
                case '\"':
                    Matcher appendReplacement = matcher.appendReplacement(stringBuffer2, "\"");
                    break;
                case '/':
                    Matcher appendReplacement2 = matcher.appendReplacement(stringBuffer2, "/");
                    break;
                case '\\':
                    Matcher appendReplacement3 = matcher.appendReplacement(stringBuffer2, "\\\\");
                    break;
                case 'b':
                    Matcher appendReplacement4 = matcher.appendReplacement(stringBuffer2, "\b");
                    break;
                case 'f':
                    Matcher appendReplacement5 = matcher.appendReplacement(stringBuffer2, "\f");
                    break;
                case 'n':
                    Matcher appendReplacement6 = matcher.appendReplacement(stringBuffer2, "\n");
                    break;
                case 'r':
                    Matcher appendReplacement7 = matcher.appendReplacement(stringBuffer2, "\r");
                    break;
                case 't':
                    Matcher appendReplacement8 = matcher.appendReplacement(stringBuffer2, "\t");
                    break;
                default:
                    Throwable th2 = th;
                    new IllegalStateException("Found an escaped character that should never be.");
                    throw th2;
            }
        }
        if (stringBuffer2 == null) {
            return unescape;
        }
        StringBuffer appendTail = matcher.appendTail(stringBuffer2);
        return stringBuffer2.toString();
    }

    @KeepForSdk
    public static String escapeString(String str) {
        StringBuffer stringBuffer;
        String str2 = str;
        if (TextUtils.isEmpty(str2)) {
            return str2;
        }
        Matcher matcher = zzhe.matcher(str2);
        StringBuffer stringBuffer2 = null;
        while (matcher.find()) {
            if (stringBuffer2 == null) {
                new StringBuffer();
                stringBuffer2 = stringBuffer;
            }
            switch (matcher.group().charAt(0)) {
                case 8:
                    Matcher appendReplacement = matcher.appendReplacement(stringBuffer2, "\\\\b");
                    break;
                case 9:
                    Matcher appendReplacement2 = matcher.appendReplacement(stringBuffer2, "\\\\t");
                    break;
                case 10:
                    Matcher appendReplacement3 = matcher.appendReplacement(stringBuffer2, "\\\\n");
                    break;
                case 12:
                    Matcher appendReplacement4 = matcher.appendReplacement(stringBuffer2, "\\\\f");
                    break;
                case 13:
                    Matcher appendReplacement5 = matcher.appendReplacement(stringBuffer2, "\\\\r");
                    break;
                case '\"':
                    Matcher appendReplacement6 = matcher.appendReplacement(stringBuffer2, "\\\\\\\"");
                    break;
                case '/':
                    Matcher appendReplacement7 = matcher.appendReplacement(stringBuffer2, "\\\\/");
                    break;
                case '\\':
                    Matcher appendReplacement8 = matcher.appendReplacement(stringBuffer2, "\\\\\\\\");
                    break;
            }
        }
        if (stringBuffer2 == null) {
            return str2;
        }
        StringBuffer appendTail = matcher.appendTail(stringBuffer2);
        return stringBuffer2.toString();
    }

    @KeepForSdk
    public static boolean areJsonValuesEquivalent(Object obj, Object obj2) {
        Object obj3 = obj;
        Object obj4 = obj2;
        if (obj3 == null && obj4 == null) {
            return true;
        }
        if (obj3 == null || obj4 == null) {
            return false;
        }
        if ((obj3 instanceof JSONObject) && (obj4 instanceof JSONObject)) {
            JSONObject jSONObject = (JSONObject) obj3;
            JSONObject jSONObject2 = (JSONObject) obj4;
            if (jSONObject.length() != jSONObject2.length()) {
                return false;
            }
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!jSONObject2.has(next)) {
                    return false;
                }
                try {
                    if (!areJsonValuesEquivalent(jSONObject.get(next), jSONObject2.get(next))) {
                        return false;
                    }
                } catch (JSONException e) {
                    return false;
                }
            }
            return true;
        } else if (!(obj3 instanceof JSONArray) || !(obj4 instanceof JSONArray)) {
            return obj3.equals(obj4);
        } else {
            JSONArray jSONArray = (JSONArray) obj3;
            JSONArray jSONArray2 = (JSONArray) obj4;
            if (jSONArray.length() != jSONArray2.length()) {
                return false;
            }
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    if (!areJsonValuesEquivalent(jSONArray.get(i), jSONArray2.get(i))) {
                        return false;
                    }
                    i++;
                } catch (JSONException e2) {
                    return false;
                }
            }
            return true;
        }
    }
}
