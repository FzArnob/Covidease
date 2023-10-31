package android.support.customtabs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.p000v4.app.BundleCompat;

public class TrustedWebUtils {
    public static final String EXTRA_LAUNCH_AS_TRUSTED_WEB_ACTIVITY = "android.support.customtabs.extra.LAUNCH_AS_TRUSTED_WEB_ACTIVITY";

    private TrustedWebUtils() {
    }

    public static void launchAsTrustedWebActivity(@NonNull Context context, @NonNull CustomTabsIntent customTabsIntent, @NonNull Uri uri) {
        Throwable th;
        Context context2 = context;
        CustomTabsIntent customTabsIntent2 = customTabsIntent;
        Uri uri2 = uri;
        if (BundleCompat.getBinder(customTabsIntent2.intent.getExtras(), CustomTabsIntent.EXTRA_SESSION) == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Given CustomTabsIntent should be associated with a valid CustomTabsSession");
            throw th2;
        }
        Intent putExtra = customTabsIntent2.intent.putExtra(EXTRA_LAUNCH_AS_TRUSTED_WEB_ACTIVITY, true);
        customTabsIntent2.launchUrl(context2, uri2);
    }
}
