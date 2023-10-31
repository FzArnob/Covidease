package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import gnu.expr.Declaration;

@ShowFirstParty
@KeepForSdk
public class GoogleApiAvailabilityLight {
    @KeepForSdk
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @KeepForSdk
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    @KeepForSdk
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    @KeepForSdk
    static final String TRACKING_SOURCE_DIALOG = "d";
    @KeepForSdk
    static final String TRACKING_SOURCE_NOTIFICATION = "n";
    private static final GoogleApiAvailabilityLight zzm;

    @KeepForSdk
    public static GoogleApiAvailabilityLight getInstance() {
        return zzm;
    }

    @KeepForSdk
    GoogleApiAvailabilityLight() {
    }

    @KeepForSdk
    @HideFirstParty
    public int isGooglePlayServicesAvailable(Context context) {
        return isGooglePlayServicesAvailable(context, GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @KeepForSdk
    public int isGooglePlayServicesAvailable(Context context, int i) {
        Context context2 = context;
        int isGooglePlayServicesAvailable = GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context2, i);
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context2, isGooglePlayServicesAvailable)) {
            isGooglePlayServicesAvailable = 18;
        }
        return isGooglePlayServicesAvailable;
    }

    @KeepForSdk
    public void verifyGooglePlayServicesIsAvailable(Context context, int i) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context, i);
    }

    @KeepForSdk
    public boolean isUserResolvableError(int i) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(i);
    }

    @ShowFirstParty
    @Nullable
    @KeepForSdk
    @Deprecated
    public Intent getErrorResolutionIntent(int i) {
        return getErrorResolutionIntent((Context) null, i, (String) null);
    }

    @Nullable
    @ShowFirstParty
    @KeepForSdk
    public Intent getErrorResolutionIntent(Context context, int i, @Nullable String str) {
        Context context2 = context;
        String str2 = str;
        switch (i) {
            case 1:
            case 2:
                if (context2 == null || !DeviceProperties.isWearableWithoutPlayStore(context2)) {
                    return zzg.zza("com.google.android.gms", zza(context2, str2));
                }
                return zzg.zzs();
            case 3:
                return zzg.zzg("com.google.android.gms");
            default:
                return null;
        }
    }

    @Nullable
    @KeepForSdk
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2) {
        return getErrorResolutionPendingIntent(context, i, i2, (String) null);
    }

    @Nullable
    @ShowFirstParty
    @KeepForSdk
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2, @Nullable String str) {
        Context context2 = context;
        int i3 = i2;
        Intent errorResolutionIntent = getErrorResolutionIntent(context2, i, str);
        Intent intent = errorResolutionIntent;
        if (errorResolutionIntent == null) {
            return null;
        }
        return PendingIntent.getActivity(context2, i3, intent, Declaration.PACKAGE_ACCESS);
    }

    @KeepForSdk
    public void cancelAvailabilityErrorNotifications(Context context) {
        GooglePlayServicesUtilLight.cancelAvailabilityErrorNotifications(context);
    }

    @ShowFirstParty
    @KeepForSdk
    public int getClientVersion(Context context) {
        return GooglePlayServicesUtilLight.getClientVersion(context);
    }

    @ShowFirstParty
    @KeepForSdk
    public int getApkVersion(Context context) {
        return GooglePlayServicesUtilLight.getApkVersion(context);
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPlayServicesPossiblyUpdating(Context context, int i) {
        return GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, i);
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPlayStorePossiblyUpdating(Context context, int i) {
        return GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(context, i);
    }

    @KeepForSdk
    public boolean isUninstalledAppPossiblyUpdating(Context context, String str) {
        return GooglePlayServicesUtilLight.isUninstalledAppPossiblyUpdating(context, str);
    }

    @KeepForSdk
    public String getErrorString(int i) {
        return GooglePlayServicesUtilLight.getErrorString(i);
    }

    @VisibleForTesting
    private static String zza(@Nullable Context context, @Nullable String str) {
        StringBuilder sb;
        Context context2 = context;
        String str2 = str;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder sb3 = sb2;
        StringBuilder append = sb2.append("gcore_");
        StringBuilder append2 = sb3.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
        StringBuilder append3 = sb3.append("-");
        if (!TextUtils.isEmpty(str2)) {
            StringBuilder append4 = sb3.append(str2);
        }
        StringBuilder append5 = sb3.append("-");
        if (context2 != null) {
            StringBuilder append6 = sb3.append(context2.getPackageName());
        }
        StringBuilder append7 = sb3.append("-");
        if (context2 != null) {
            try {
                StringBuilder append8 = sb3.append(Wrappers.packageManager(context2).getPackageInfo(context2.getPackageName(), 0).versionCode);
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        return sb3.toString();
    }

    static {
        GoogleApiAvailabilityLight googleApiAvailabilityLight;
        new GoogleApiAvailabilityLight();
        zzm = googleApiAvailabilityLight;
    }
}
