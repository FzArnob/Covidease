package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.zzb;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.appinventor.components.runtime.util.ScreenDensityUtil;
import java.util.concurrent.atomic.AtomicBoolean;

@ShowFirstParty
@KeepForSdk
public class GooglePlayServicesUtilLight {
    @KeepForSdk
    static final int GMS_AVAILABILITY_NOTIFICATION_ID = 10436;
    @KeepForSdk
    static final int GMS_GENERAL_ERROR_NOTIFICATION_ID = 39789;
    @KeepForSdk
    public static final String GOOGLE_PLAY_GAMES_PACKAGE = "com.google.android.play.games";
    @KeepForSdk
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @KeepForSdk
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 12451000;
    @KeepForSdk
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    @KeepForSdk
    @VisibleForTesting
    static final AtomicBoolean sCanceledAvailabilityNotification;
    @VisibleForTesting
    private static boolean zzah = false;
    @VisibleForTesting
    private static boolean zzai = false;
    private static boolean zzaj = false;
    @VisibleForTesting
    private static boolean zzak = false;
    private static final AtomicBoolean zzal;

    @ShowFirstParty
    @KeepForSdk
    public static void enableUsingApkIndependentContext() {
        zzal.set(true);
    }

    @KeepForSdk
    GooglePlayServicesUtilLight() {
    }

    @KeepForSdk
    @Deprecated
    @VisibleForTesting
    public static String getErrorString(int i) {
        return ConnectionResult.zza(i);
    }

    @KeepForSdk
    @Deprecated
    @HideFirstParty
    public static int isGooglePlayServicesAvailable(Context context) {
        return isGooglePlayServicesAvailable(context, GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @KeepForSdk
    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context, int i) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Context context2 = context;
        int i2 = i;
        try {
            String string = context2.getResources().getString(C0554R.string.common_google_play_services_unknown_issue);
        } catch (Throwable th3) {
            int e = Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if (!"com.google.android.gms".equals(context2.getPackageName())) {
            Context context3 = context2;
            if (!zzal.get()) {
                int zzd = zzp.zzd(context3);
                int i3 = zzd;
                if (zzd == 0) {
                    Throwable th4 = th2;
                    new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
                    throw th4;
                } else if (i3 != GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                    Throwable th5 = th;
                    int i4 = GOOGLE_PLAY_SERVICES_VERSION_CODE;
                    new StringBuilder(ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION);
                    new IllegalStateException(sb.append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ").append(i4).append(" but found ").append(i3).append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />").toString());
                    throw th5;
                }
            }
        }
        return zza(context2, !DeviceProperties.isWearableWithoutPlayStore(context2) && !DeviceProperties.zzf(context2), i2);
    }

    @VisibleForTesting
    private static int zza(Context context, boolean z, int i) {
        StringBuilder sb;
        Context context2 = context;
        boolean z2 = z;
        int i2 = i;
        Preconditions.checkArgument(i2 >= 0);
        PackageManager packageManager = context2.getPackageManager();
        PackageInfo packageInfo = null;
        if (z2) {
            try {
                packageInfo = packageManager.getPackageInfo("com.android.vending", 8256);
            } catch (PackageManager.NameNotFoundException e) {
                int w = Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
                return 9;
            }
        }
        try {
            PackageInfo packageInfo2 = packageManager.getPackageInfo("com.google.android.gms", 64);
            GoogleSignatureVerifier instance = GoogleSignatureVerifier.getInstance(context2);
            if (!GoogleSignatureVerifier.zza(packageInfo2, true)) {
                int w2 = Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            } else if (!z2 || (GoogleSignatureVerifier.zza(packageInfo, true) && packageInfo.signatures[0].equals(packageInfo2.signatures[0]))) {
                if (zzb.zzc(packageInfo2.versionCode) < zzb.zzc(i2)) {
                    int i3 = packageInfo2.versionCode;
                    new StringBuilder(77);
                    int w3 = Log.w("GooglePlayServicesUtil", sb.append("Google Play services out of date.  Requires ").append(i2).append(" but found ").append(i3).toString());
                    return 2;
                }
                ApplicationInfo applicationInfo = packageInfo2.applicationInfo;
                ApplicationInfo applicationInfo2 = applicationInfo;
                if (applicationInfo == null) {
                    try {
                        applicationInfo2 = packageManager.getApplicationInfo("com.google.android.gms", 0);
                    } catch (PackageManager.NameNotFoundException e2) {
                        int wtf = Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", e2);
                        return 1;
                    }
                }
                if (!applicationInfo2.enabled) {
                    return 3;
                }
                return 0;
            } else {
                int w4 = Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
                return 9;
            }
        } catch (PackageManager.NameNotFoundException e3) {
            int w5 = Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
    }

    @KeepForSdk
    @Deprecated
    public static void ensurePlayServicesAvailable(Context context, int i) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        StringBuilder sb;
        Throwable th;
        Throwable th2;
        Context context2 = context;
        int isGooglePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context2, i);
        int i2 = isGooglePlayServicesAvailable;
        if (isGooglePlayServicesAvailable != 0) {
            Intent errorResolutionIntent = GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(context2, i2, "e");
            new StringBuilder(57);
            int e = Log.e("GooglePlayServicesUtil", sb.append("GooglePlayServices not available due to error ").append(i2).toString());
            if (errorResolutionIntent == null) {
                Throwable th3 = th2;
                new GooglePlayServicesNotAvailableException(i2);
                throw th3;
            }
            Throwable th4 = th;
            new GooglePlayServicesRepairableException(i2, "Google Play Services not available", errorResolutionIntent);
            throw th4;
        }
    }

    @KeepForSdk
    @Deprecated
    public static boolean isGooglePlayServicesUid(Context context, int i) {
        return UidVerifier.isGooglePlayServicesUid(context, i);
    }

    @TargetApi(19)
    @KeepForSdk
    @Deprecated
    public static boolean uidHasPackageName(Context context, int i, String str) {
        return UidVerifier.uidHasPackageName(context, i, str);
    }

    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    public static Intent getGooglePlayServicesAvailabilityRecoveryIntent(int i) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent((Context) null, i, (String) null);
    }

    @ShowFirstParty
    @KeepForSdk
    public static boolean honorsDebugCertificates(Context context) {
        Context context2 = context;
        if (!zzak) {
            Context context3 = context2;
            try {
                PackageInfo packageInfo = Wrappers.packageManager(context3).getPackageInfo("com.google.android.gms", 64);
                GoogleSignatureVerifier instance = GoogleSignatureVerifier.getInstance(context3);
                if (packageInfo == null || GoogleSignatureVerifier.zza(packageInfo, false) || !GoogleSignatureVerifier.zza(packageInfo, true)) {
                    zzaj = false;
                } else {
                    zzaj = true;
                }
                zzak = true;
            } catch (PackageManager.NameNotFoundException e) {
                int w = Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", e);
                zzak = true;
            } catch (Throwable th) {
                zzak = true;
                throw th;
            }
        }
        if (zzaj || !DeviceProperties.isUserBuild()) {
            return true;
        }
        return false;
    }

    @KeepForSdk
    @Deprecated
    public static PendingIntent getErrorPendingIntent(int i, Context context, int i2) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionPendingIntent(context, i, i2);
    }

    @KeepForSdk
    @Deprecated
    public static void cancelAvailabilityErrorNotifications(Context context) {
        Context context2 = context;
        if (!sCanceledAvailabilityNotification.getAndSet(true)) {
            try {
                NotificationManager notificationManager = (NotificationManager) context2.getSystemService("notification");
                NotificationManager notificationManager2 = notificationManager;
                if (notificationManager != null) {
                    notificationManager2.cancel(GMS_AVAILABILITY_NOTIFICATION_ID);
                }
            } catch (SecurityException e) {
            }
        }
    }

    @KeepForSdk
    @Deprecated
    public static boolean isUserRecoverableError(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 9:
                return true;
            default:
                return false;
        }
    }

    @KeepForSdk
    public static Resources getRemoteResource(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    @KeepForSdk
    public static Context getRemoteContext(Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    public static int getClientVersion(Context context) {
        Context context2 = context;
        Preconditions.checkState(true);
        return ClientLibraryUtils.getClientVersion(context2, context2.getPackageName());
    }

    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    public static int getApkVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            int w = Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }

    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    @VisibleForTesting
    public static boolean isSidewinderDevice(Context context) {
        return DeviceProperties.isSidewinder(context);
    }

    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    public static boolean isPlayServicesPossiblyUpdating(Context context, int i) {
        Context context2 = context;
        int i2 = i;
        if (i2 == 18) {
            return true;
        }
        if (i2 == 1) {
            return isUninstalledAppPossiblyUpdating(context2, "com.google.android.gms");
        }
        return false;
    }

    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    public static boolean isPlayStorePossiblyUpdating(Context context, int i) {
        Context context2 = context;
        if (i == 9) {
            return isUninstalledAppPossiblyUpdating(context2, "com.android.vending");
        }
        return false;
    }

    @TargetApi(21)
    static boolean isUninstalledAppPossiblyUpdating(Context context, String str) {
        Context context2 = context;
        String str2 = str;
        boolean equals = str2.equals("com.google.android.gms");
        if (PlatformVersion.isAtLeastLollipop()) {
            try {
                for (PackageInstaller.SessionInfo appPackageName : context2.getPackageManager().getPackageInstaller().getAllSessions()) {
                    if (str2.equals(appPackageName.getAppPackageName())) {
                        return true;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        try {
            ApplicationInfo applicationInfo = context2.getPackageManager().getApplicationInfo(str2, 8192);
            if (equals) {
                return applicationInfo.enabled;
            }
            return applicationInfo.enabled && !isRestrictedUserProfile(context2);
        } catch (PackageManager.NameNotFoundException e2) {
            return false;
        }
    }

    @TargetApi(18)
    @KeepForSdk
    public static boolean isRestrictedUserProfile(Context context) {
        Context context2 = context;
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            Bundle applicationRestrictions = ((UserManager) context2.getSystemService("user")).getApplicationRestrictions(context2.getPackageName());
            Bundle bundle = applicationRestrictions;
            if (applicationRestrictions != null && "true".equals(bundle.getString("restricted_profile"))) {
                return true;
            }
        }
        return false;
    }

    static {
        AtomicBoolean atomicBoolean;
        AtomicBoolean atomicBoolean2;
        new AtomicBoolean();
        sCanceledAvailabilityNotification = atomicBoolean;
        new AtomicBoolean();
        zzal = atomicBoolean2;
    }
}
