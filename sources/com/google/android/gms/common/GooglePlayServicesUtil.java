package com.google.android.gms.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.p000v4.app.Fragment;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.DialogRedirect;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;

public final class GooglePlayServicesUtil extends GooglePlayServicesUtilLight {
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";

    private GooglePlayServicesUtil() {
    }

    @Deprecated
    public static Dialog getErrorDialog(int i, Activity activity, int i2) {
        return getErrorDialog(i, activity, i2, (DialogInterface.OnCancelListener) null);
    }

    @Deprecated
    public static Dialog getErrorDialog(int i, Activity activity, int i2, DialogInterface.OnCancelListener onCancelListener) {
        int i3 = i;
        Activity activity2 = activity;
        int i4 = i2;
        DialogInterface.OnCancelListener onCancelListener2 = onCancelListener;
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(activity2, i3)) {
            i3 = 18;
        }
        return GoogleApiAvailability.getInstance().getErrorDialog(activity2, i3, i4, onCancelListener2);
    }

    @Deprecated
    public static boolean showErrorDialogFragment(int i, Activity activity, int i2, DialogInterface.OnCancelListener onCancelListener) {
        return showErrorDialogFragment(i, activity, (Fragment) null, i2, onCancelListener);
    }

    public static boolean showErrorDialogFragment(int i, Activity activity, Fragment fragment, int i2, DialogInterface.OnCancelListener onCancelListener) {
        int i3 = i;
        Activity activity2 = activity;
        Fragment fragment2 = fragment;
        int i4 = i2;
        DialogInterface.OnCancelListener onCancelListener2 = onCancelListener;
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(activity2, i3)) {
            i3 = 18;
        }
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        if (fragment2 == null) {
            return instance.showErrorDialogFragment(activity2, i3, i4, onCancelListener2);
        }
        Dialog zaa = GoogleApiAvailability.zaa((Context) activity2, i3, DialogRedirect.getInstance(fragment2, GoogleApiAvailability.getInstance().getErrorResolutionIntent(activity2, i3, "d"), i4), onCancelListener2);
        Dialog dialog = zaa;
        if (zaa == null) {
            return false;
        }
        GoogleApiAvailability.zaa(activity2, dialog, GMS_ERROR_DIALOG, onCancelListener2);
        return true;
    }

    @Deprecated
    public static void showErrorNotification(int i, Context context) {
        int i2 = i;
        Context context2 = context;
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context2, i2) || GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(context2, i2)) {
            instance.zaa(context2);
        } else {
            instance.showErrorNotification(context2, i2);
        }
    }

    @Deprecated
    public static boolean showErrorDialogFragment(int i, Activity activity, int i2) {
        return showErrorDialogFragment(i, activity, i2, (DialogInterface.OnCancelListener) null);
    }

    @Deprecated
    @VisibleForTesting
    public static String getErrorString(int i) {
        return GooglePlayServicesUtilLight.getErrorString(i);
    }

    @Deprecated
    @HideFirstParty
    public static int isGooglePlayServicesAvailable(Context context) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context);
    }

    @KeepForSdk
    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context, int i) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context, i);
    }

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int i, Context context, int i2) {
        return GooglePlayServicesUtilLight.getErrorPendingIntent(i, context, i2);
    }

    @Deprecated
    public static boolean isUserRecoverableError(int i) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(i);
    }

    public static Resources getRemoteResource(Context context) {
        return GooglePlayServicesUtilLight.getRemoteResource(context);
    }

    public static Context getRemoteContext(Context context) {
        return GooglePlayServicesUtilLight.getRemoteContext(context);
    }
}
