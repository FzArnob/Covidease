package android.support.p000v4.text;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.Locale;

/* renamed from: android.support.v4.text.TextUtilsCompat */
public final class TextUtilsCompat {
    private static final String ARAB_SCRIPT_SUBTAG = "Arab";
    private static final String HEBR_SCRIPT_SUBTAG = "Hebr";
    private static final Locale ROOT;

    static {
        Locale locale;
        new Locale("", "");
        ROOT = locale;
    }

    @NonNull
    public static String htmlEncode(@NonNull String str) {
        StringBuilder sb;
        String s = str;
        if (Build.VERSION.SDK_INT >= 17) {
            return TextUtils.htmlEncode(s);
        }
        new StringBuilder();
        StringBuilder sb2 = sb;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\"':
                    StringBuilder append = sb2.append("&quot;");
                    break;
                case '&':
                    StringBuilder append2 = sb2.append("&amp;");
                    break;
                case '\'':
                    StringBuilder append3 = sb2.append("&#39;");
                    break;
                case '<':
                    StringBuilder append4 = sb2.append("&lt;");
                    break;
                case '>':
                    StringBuilder append5 = sb2.append("&gt;");
                    break;
                default:
                    StringBuilder append6 = sb2.append(c);
                    break;
            }
        }
        return sb2.toString();
    }

    public static int getLayoutDirectionFromLocale(@Nullable Locale locale) {
        Locale locale2 = locale;
        if (Build.VERSION.SDK_INT >= 17) {
            return TextUtils.getLayoutDirectionFromLocale(locale2);
        }
        if (locale2 != null && !locale2.equals(ROOT)) {
            String scriptSubtag = ICUCompat.maximizeAndGetScript(locale2);
            if (scriptSubtag == null) {
                return getLayoutDirectionFromFirstChar(locale2);
            }
            if (scriptSubtag.equalsIgnoreCase(ARAB_SCRIPT_SUBTAG) || scriptSubtag.equalsIgnoreCase(HEBR_SCRIPT_SUBTAG)) {
                return 1;
            }
        }
        return 0;
    }

    private static int getLayoutDirectionFromFirstChar(@NonNull Locale locale) {
        Locale locale2 = locale;
        switch (Character.getDirectionality(locale2.getDisplayName(locale2).charAt(0))) {
            case 1:
            case 2:
                return 1;
            default:
                return 0;
        }
    }

    private TextUtilsCompat() {
    }
}
