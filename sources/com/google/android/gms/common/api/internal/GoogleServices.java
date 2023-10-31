package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.C0554R;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.appinventor.components.common.PropertyTypeConstants;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
@Deprecated
public final class GoogleServices {
    private static final Object sLock;
    @GuardedBy("sLock")
    private static GoogleServices zzay;
    private final String zzaz;
    private final Status zzba;
    private final boolean zzbb;
    private final boolean zzbc;

    @KeepForSdk
    @VisibleForTesting
    GoogleServices(Context context) {
        Status status;
        StringResourceValueReader stringResourceValueReader;
        Context context2 = context;
        Resources resources = context2.getResources();
        Resources resources2 = resources;
        boolean z = true;
        int identifier = resources2.getIdentifier("google_app_measurement_enable", PropertyTypeConstants.PROPERTY_TYPE_INTEGER, resources.getResourcePackageName(C0554R.string.common_google_play_services_unknown_issue));
        int i = identifier;
        if (identifier != 0) {
            z = resources2.getInteger(i) != 0;
            this.zzbc = !z;
        } else {
            this.zzbc = false;
        }
        this.zzbb = z;
        String zzc = zzp.zzc(context2);
        String str = zzc;
        if (zzc == null) {
            new StringResourceValueReader(context2);
            str = stringResourceValueReader.getString("google_app_id");
        }
        if (TextUtils.isEmpty(str)) {
            new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzba = status;
            this.zzaz = null;
            return;
        }
        this.zzaz = str;
        this.zzba = Status.RESULT_SUCCESS;
    }

    @KeepForSdk
    @VisibleForTesting
    GoogleServices(String str, boolean z) {
        boolean z2 = z;
        this.zzaz = str;
        this.zzba = Status.RESULT_SUCCESS;
        this.zzbb = z2;
        this.zzbc = !z2;
    }

    /* JADX INFO: finally extract failed */
    @KeepForSdk
    public static Status initialize(Context context, String str, boolean z) {
        GoogleServices googleServices;
        String str2 = str;
        boolean z2 = z;
        Object checkNotNull = Preconditions.checkNotNull(context, "Context must not be null.");
        String checkNotEmpty = Preconditions.checkNotEmpty(str2, "App ID must be nonempty.");
        Object obj = sLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (zzay != null) {
                    Status checkGoogleAppId = zzay.checkGoogleAppId(str2);
                    return checkGoogleAppId;
                }
                new GoogleServices(str2, z2);
                GoogleServices googleServices2 = googleServices;
                zzay = googleServices2;
                Status status = googleServices2.zzba;
                return status;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @KeepForSdk
    @VisibleForTesting
    public final Status checkGoogleAppId(String str) {
        Status status;
        StringBuilder sb;
        String str2 = str;
        if (this.zzaz == null || this.zzaz.equals(str2)) {
            return Status.RESULT_SUCCESS;
        }
        Status status2 = status;
        String str3 = this.zzaz;
        new StringBuilder(97 + String.valueOf(str3).length());
        new Status(10, sb.append("Initialize was called with two different Google App IDs.  Only the first app ID will be used: '").append(str3).append("'.").toString());
        return status2;
    }

    @KeepForSdk
    public static Status initialize(Context context) {
        GoogleServices googleServices;
        Context context2 = context;
        Object checkNotNull = Preconditions.checkNotNull(context2, "Context must not be null.");
        Object obj = sLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (zzay == null) {
                    new GoogleServices(context2);
                    zzay = googleServices;
                }
                Status status = zzay.zzba;
                return status;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    @KeepForSdk
    public static String getGoogleAppId() {
        return checkInitialized("getGoogleAppId").zzaz;
    }

    @KeepForSdk
    public static boolean isMeasurementEnabled() {
        GoogleServices checkInitialized = checkInitialized("isMeasurementEnabled");
        return checkInitialized.zzba.isSuccess() && checkInitialized.zzbb;
    }

    @KeepForSdk
    public static boolean isMeasurementExplicitlyDisabled() {
        return checkInitialized("isMeasurementExplicitlyDisabled").zzbc;
    }

    @KeepForSdk
    @VisibleForTesting
    static void clearInstanceForTest() {
        Object obj = sLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                zzay = null;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    @KeepForSdk
    private static GoogleServices checkInitialized(String str) {
        Throwable th;
        StringBuilder sb;
        String str2 = str;
        Object obj = sLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (zzay == null) {
                    Throwable th2 = th;
                    new StringBuilder(34 + String.valueOf(str2).length());
                    new IllegalStateException(sb.append("Initialize must be called before ").append(str2).append(".").toString());
                    throw th2;
                }
                GoogleServices googleServices = zzay;
                return googleServices;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                Object obj3 = obj2;
                throw th4;
            }
        }
    }

    static {
        Object obj;
        new Object();
        sLock = obj;
    }
}
