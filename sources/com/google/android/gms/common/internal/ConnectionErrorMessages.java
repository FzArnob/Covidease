package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.C1650SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.base.C0551R;
import com.google.android.gms.common.C0554R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.appinventor.components.common.PropertyTypeConstants;
import javax.annotation.concurrent.GuardedBy;

public final class ConnectionErrorMessages {
    @GuardedBy("sCache")
    private static final C1650SimpleArrayMap<String, String> zaog;

    @Nullable
    public static String getErrorTitle(Context context, int i) {
        StringBuilder sb;
        Context context2 = context;
        int i2 = i;
        Resources resources = context2.getResources();
        switch (i2) {
            case 1:
                return resources.getString(C0551R.string.common_google_play_services_install_title);
            case 2:
                return resources.getString(C0551R.string.common_google_play_services_update_title);
            case 3:
                return resources.getString(C0551R.string.common_google_play_services_enable_title);
            case 4:
            case 6:
            case 18:
                return null;
            case 5:
                int e = Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return zaa(context2, "common_google_play_services_invalid_account_title");
            case 7:
                int e2 = Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return zaa(context2, "common_google_play_services_network_error_title");
            case 8:
                int e3 = Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case 9:
                int e4 = Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            case 10:
                int e5 = Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case 11:
                int e6 = Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case 16:
                int e7 = Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case 17:
                int e8 = Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return zaa(context2, "common_google_play_services_sign_in_failed_title");
            case 20:
                int e9 = Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return zaa(context2, "common_google_play_services_restricted_profile_title");
            default:
                new StringBuilder(33);
                int e10 = Log.e("GoogleApiAvailability", sb.append("Unexpected error code ").append(i2).toString());
                return null;
        }
    }

    @NonNull
    public static String getErrorNotificationTitle(Context context, int i) {
        String errorTitle;
        Context context2 = context;
        int i2 = i;
        if (i2 == 6) {
            errorTitle = zaa(context2, "common_google_play_services_resolution_required_title");
        } else {
            errorTitle = getErrorTitle(context2, i2);
        }
        if (errorTitle == null) {
            errorTitle = context2.getResources().getString(C0551R.string.common_google_play_services_notification_ticker);
        }
        return errorTitle;
    }

    @NonNull
    public static String getErrorMessage(Context context, int i) {
        Context context2 = context;
        Resources resources = context2.getResources();
        String appName = getAppName(context2);
        switch (i) {
            case 1:
                return resources.getString(C0551R.string.common_google_play_services_install_text, new Object[]{appName});
            case 2:
                if (DeviceProperties.isWearableWithoutPlayStore(context2)) {
                    return resources.getString(C0551R.string.common_google_play_services_wear_update_text);
                }
                return resources.getString(C0551R.string.common_google_play_services_update_text, new Object[]{appName});
            case 3:
                return resources.getString(C0551R.string.common_google_play_services_enable_text, new Object[]{appName});
            case 5:
                return zaa(context2, "common_google_play_services_invalid_account_text", appName);
            case 7:
                return zaa(context2, "common_google_play_services_network_error_text", appName);
            case 9:
                return resources.getString(C0551R.string.common_google_play_services_unsupported_text, new Object[]{appName});
            case 16:
                return zaa(context2, "common_google_play_services_api_unavailable_text", appName);
            case 17:
                return zaa(context2, "common_google_play_services_sign_in_failed_text", appName);
            case 18:
                return resources.getString(C0551R.string.common_google_play_services_updating_text, new Object[]{appName});
            case 20:
                return zaa(context2, "common_google_play_services_restricted_profile_text", appName);
            default:
                return resources.getString(C0554R.string.common_google_play_services_unknown_issue, new Object[]{appName});
        }
    }

    @NonNull
    public static String getErrorNotificationMessage(Context context, int i) {
        Context context2 = context;
        int i2 = i;
        if (i2 == 6) {
            return zaa(context2, "common_google_play_services_resolution_required_text", getAppName(context2));
        }
        return getErrorMessage(context2, i2);
    }

    @NonNull
    public static String getErrorDialogButtonMessage(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(C0551R.string.common_google_play_services_install_button);
            case 2:
                return resources.getString(C0551R.string.common_google_play_services_update_button);
            case 3:
                return resources.getString(C0551R.string.common_google_play_services_enable_button);
            default:
                return resources.getString(17039370);
        }
    }

    public static String getAppName(Context context) {
        Context context2 = context;
        String packageName = context2.getPackageName();
        try {
            return Wrappers.packageManager(context2).getApplicationLabel(packageName).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            String str = context2.getApplicationInfo().name;
            String str2 = str;
            if (TextUtils.isEmpty(str)) {
                return packageName;
            }
            return str2;
        }
    }

    private static String zaa(Context context, String str, String str2) {
        Context context2 = context;
        String str3 = str2;
        Resources resources = context2.getResources();
        String zaa = zaa(context2, str);
        String str4 = zaa;
        if (zaa == null) {
            str4 = resources.getString(C0554R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, str4, new Object[]{str3});
    }

    /* JADX INFO: finally extract failed */
    @Nullable
    private static String zaa(Context context, String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        Context context2 = context;
        String str6 = str;
        C1650SimpleArrayMap<String, String> simpleArrayMap = zaog;
        C1650SimpleArrayMap<String, String> simpleArrayMap2 = simpleArrayMap;
        synchronized (simpleArrayMap) {
            try {
                String str7 = zaog.get(str6);
                String str8 = str7;
                if (str7 != null) {
                    String str9 = str8;
                    return str9;
                }
                Resources remoteResource = GooglePlayServicesUtil.getRemoteResource(context2);
                Resources resources = remoteResource;
                if (remoteResource == null) {
                    return null;
                }
                int identifier = resources.getIdentifier(str6, PropertyTypeConstants.PROPERTY_TYPE_STRING, "com.google.android.gms");
                int i = identifier;
                if (identifier == 0) {
                    String valueOf = String.valueOf(str6);
                    String str10 = valueOf;
                    if (valueOf.length() != 0) {
                        str5 = "Missing resource: ".concat(str10);
                    } else {
                        str5 = str4;
                        new String("Missing resource: ");
                    }
                    int w = Log.w("GoogleApiAvailability", str5);
                    return null;
                }
                String string = resources.getString(i);
                String str11 = string;
                if (TextUtils.isEmpty(string)) {
                    String valueOf2 = String.valueOf(str6);
                    String str12 = valueOf2;
                    if (valueOf2.length() != 0) {
                        str3 = "Got empty resource: ".concat(str12);
                    } else {
                        str3 = str2;
                        new String("Got empty resource: ");
                    }
                    int w2 = Log.w("GoogleApiAvailability", str3);
                    return null;
                }
                String put = zaog.put(str6, str11);
                String str13 = str11;
                return str13;
            } catch (Throwable th) {
                Throwable th2 = th;
                C1650SimpleArrayMap<String, String> simpleArrayMap3 = simpleArrayMap2;
                throw th2;
            }
        }
    }

    public static String getDefaultNotificationChannelName(Context context) {
        return context.getResources().getString(C0551R.string.common_google_play_services_notification_channel_name);
    }

    private ConnectionErrorMessages() {
    }

    static {
        C1650SimpleArrayMap<String, String> simpleArrayMap;
        new C1650SimpleArrayMap<>();
        zaog = simpleArrayMap;
    }
}
