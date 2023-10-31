package android.support.p000v4.content.res;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;

/* renamed from: android.support.v4.content.res.ConfigurationHelper */
public final class ConfigurationHelper {
    private ConfigurationHelper() {
    }

    public static int getDensityDpi(@NonNull Resources resources) {
        Resources resources2 = resources;
        if (Build.VERSION.SDK_INT >= 17) {
            return resources2.getConfiguration().densityDpi;
        }
        return resources2.getDisplayMetrics().densityDpi;
    }
}
