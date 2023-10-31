package android.support.p000v4.p002os;

import android.support.annotation.RestrictTo;
import java.util.Locale;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.os.LocaleHelper */
final class LocaleHelper {
    static Locale forLanguageTag(String str) {
        Locale locale;
        Locale locale2;
        Locale locale3;
        Locale locale4;
        Throwable th;
        StringBuilder sb;
        Locale locale5;
        Locale locale6;
        Locale locale7;
        String str2 = str;
        if (str2.contains("-")) {
            String[] args = str2.split("-", -1);
            if (args.length > 2) {
                new Locale(args[0], args[1], args[2]);
                return locale7;
            } else if (args.length > 1) {
                new Locale(args[0], args[1]);
                return locale6;
            } else if (args.length == 1) {
                new Locale(args[0]);
                return locale5;
            }
        } else if (str2.contains("_")) {
            String[] args2 = str2.split("_", -1);
            if (args2.length > 2) {
                new Locale(args2[0], args2[1], args2[2]);
                return locale4;
            } else if (args2.length > 1) {
                new Locale(args2[0], args2[1]);
                return locale3;
            } else if (args2.length == 1) {
                new Locale(args2[0]);
                return locale2;
            }
        } else {
            new Locale(str2);
            return locale;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Can not parse language tag: [").append(str2).append("]").toString());
        throw th2;
    }

    static String toLanguageTag(Locale locale) {
        StringBuilder sb;
        Locale locale2 = locale;
        new StringBuilder();
        StringBuilder buf = sb;
        StringBuilder append = buf.append(locale2.getLanguage());
        String country = locale2.getCountry();
        if (country != null && !country.isEmpty()) {
            StringBuilder append2 = buf.append("-");
            StringBuilder append3 = buf.append(locale2.getCountry());
        }
        return buf.toString();
    }

    private LocaleHelper() {
    }
}
