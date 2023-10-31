package android.support.p000v4.p002os;

import android.content.res.Configuration;
import android.os.Build;

/* renamed from: android.support.v4.os.ConfigurationCompat */
public final class ConfigurationCompat {
    private ConfigurationCompat() {
    }

    public static LocaleListCompat getLocales(Configuration configuration) {
        Configuration configuration2 = configuration;
        if (Build.VERSION.SDK_INT >= 24) {
            return LocaleListCompat.wrap(configuration2.getLocales());
        }
        return LocaleListCompat.create(configuration2.locale);
    }
}
