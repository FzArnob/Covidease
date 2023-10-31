package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@VisibleForTesting
public final class zzd {
    private static final Pattern zzhi = Pattern.compile("\\\\u[0-9a-fA-F]{4}");

    public static String unescape(String str) {
        String str2;
        StringBuffer stringBuffer;
        String str3 = str;
        if (TextUtils.isEmpty(str3)) {
            return str3;
        }
        Matcher matcher = zzhi.matcher(str3);
        StringBuffer stringBuffer2 = null;
        while (matcher.find()) {
            if (stringBuffer2 == null) {
                new StringBuffer();
                stringBuffer2 = stringBuffer;
            }
            new String(Character.toChars(Integer.parseInt(matcher.group().substring(2), 16)));
            Matcher appendReplacement = matcher.appendReplacement(stringBuffer2, str2);
        }
        if (stringBuffer2 == null) {
            return str3;
        }
        StringBuffer appendTail = matcher.appendTail(stringBuffer2);
        return stringBuffer2.toString();
    }
}
