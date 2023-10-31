package com.google.android.gms.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ConnectionResultCreator")
public final class ConnectionResult extends AbstractSafeParcelable {
    public static final int API_UNAVAILABLE = 16;
    public static final int CANCELED = 13;
    public static final Parcelable.Creator<ConnectionResult> CREATOR;
    public static final int DEVELOPER_ERROR = 10;
    @Deprecated
    public static final int DRIVE_EXTERNAL_STORAGE_REQUIRED = 1500;
    public static final int INTERNAL_ERROR = 8;
    public static final int INTERRUPTED = 15;
    public static final int INVALID_ACCOUNT = 5;
    public static final int LICENSE_CHECK_FAILED = 11;
    public static final int NETWORK_ERROR = 7;
    public static final int RESOLUTION_REQUIRED = 6;
    public static final int RESTRICTED_PROFILE = 20;
    @KeepForSdk
    public static final ConnectionResult RESULT_SUCCESS;
    public static final int SERVICE_DISABLED = 3;
    public static final int SERVICE_INVALID = 9;
    public static final int SERVICE_MISSING = 1;
    public static final int SERVICE_MISSING_PERMISSION = 19;
    public static final int SERVICE_UPDATING = 18;
    public static final int SERVICE_VERSION_UPDATE_REQUIRED = 2;
    public static final int SIGN_IN_FAILED = 17;
    public static final int SIGN_IN_REQUIRED = 4;
    public static final int SUCCESS = 0;
    public static final int TIMEOUT = 14;
    @KeepForSdk
    public static final int UNKNOWN = -1;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int zzg;
    @SafeParcelable.Field(getter = "getErrorCode", mo25277id = 2)
    private final int zzh;
    @SafeParcelable.Field(getter = "getResolution", mo25277id = 3)
    private final PendingIntent zzi;
    @SafeParcelable.Field(getter = "getErrorMessage", mo25277id = 4)
    private final String zzj;

    @SafeParcelable.Constructor
    ConnectionResult(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) int i2, @SafeParcelable.Param(mo25280id = 3) PendingIntent pendingIntent, @SafeParcelable.Param(mo25280id = 4) String str) {
        this.zzg = i;
        this.zzh = i2;
        this.zzi = pendingIntent;
        this.zzj = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ConnectionResult(int i) {
        this(i, (PendingIntent) null, (String) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ConnectionResult(int i, PendingIntent pendingIntent) {
        this(i, pendingIntent, (String) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ConnectionResult(int i, PendingIntent pendingIntent, String str) {
        this(1, i, pendingIntent, str);
    }

    public final void startResolutionForResult(Activity activity, int i) throws IntentSender.SendIntentException {
        Activity activity2 = activity;
        int i2 = i;
        if (hasResolution()) {
            activity2.startIntentSenderForResult(this.zzi.getIntentSender(), i2, (Intent) null, 0, 0, 0);
        }
    }

    public final boolean hasResolution() {
        return (this.zzh == 0 || this.zzi == null) ? false : true;
    }

    public final boolean isSuccess() {
        return this.zzh == 0;
    }

    public final int getErrorCode() {
        return this.zzh;
    }

    @Nullable
    public final PendingIntent getResolution() {
        return this.zzi;
    }

    @Nullable
    public final String getErrorMessage() {
        return this.zzj;
    }

    static String zza(int i) {
        StringBuilder sb;
        int i2 = i;
        switch (i2) {
            case -1:
                return "UNKNOWN";
            case 0:
                return "SUCCESS";
            case 1:
                return "SERVICE_MISSING";
            case 2:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case 3:
                return "SERVICE_DISABLED";
            case 4:
                return "SIGN_IN_REQUIRED";
            case 5:
                return "INVALID_ACCOUNT";
            case 6:
                return "RESOLUTION_REQUIRED";
            case 7:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case 9:
                return "SERVICE_INVALID";
            case 10:
                return "DEVELOPER_ERROR";
            case 11:
                return "LICENSE_CHECK_FAILED";
            case 13:
                return "CANCELED";
            case 14:
                return "TIMEOUT";
            case 15:
                return "INTERRUPTED";
            case 16:
                return "API_UNAVAILABLE";
            case 17:
                return "SIGN_IN_FAILED";
            case 18:
                return "SERVICE_UPDATING";
            case 19:
                return "SERVICE_MISSING_PERMISSION";
            case 20:
                return "RESTRICTED_PROFILE";
            case 21:
                return "API_VERSION_UPDATE_REQUIRED";
            case 99:
                return "UNFINISHED";
            case DRIVE_EXTERNAL_STORAGE_REQUIRED /*1500*/:
                return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
            default:
                new StringBuilder(31);
                return sb.append("UNKNOWN_ERROR_CODE(").append(i2).append(")").toString();
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r1
            r4 = r0
            if (r3 != r4) goto L_0x0009
            r3 = 1
            r0 = r3
        L_0x0008:
            return r0
        L_0x0009:
            r3 = r1
            boolean r3 = r3 instanceof com.google.android.gms.common.ConnectionResult
            if (r3 != 0) goto L_0x0011
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0011:
            r3 = r1
            com.google.android.gms.common.ConnectionResult r3 = (com.google.android.gms.common.ConnectionResult) r3
            r2 = r3
            r3 = r0
            int r3 = r3.zzh
            r4 = r2
            int r4 = r4.zzh
            if (r3 != r4) goto L_0x0038
            r3 = r0
            android.app.PendingIntent r3 = r3.zzi
            r4 = r2
            android.app.PendingIntent r4 = r4.zzi
            boolean r3 = com.google.android.gms.common.internal.Objects.equal(r3, r4)
            if (r3 == 0) goto L_0x0038
            r3 = r0
            java.lang.String r3 = r3.zzj
            r4 = r2
            java.lang.String r4 = r4.zzj
            boolean r3 = com.google.android.gms.common.internal.Objects.equal(r3, r4)
            if (r3 == 0) goto L_0x0038
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x0038:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.ConnectionResult.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(this.zzh);
        Object[] objArr2 = objArr;
        objArr2[1] = this.zzi;
        Object[] objArr3 = objArr2;
        objArr3[2] = this.zzj;
        return Objects.hashCode(objArr3);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("statusCode", zza(this.zzh)).add("resolution", this.zzi).add("message", this.zzj).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zzg);
        SafeParcelWriter.writeInt(parcel2, 2, getErrorCode());
        SafeParcelWriter.writeParcelable(parcel2, 3, getResolution(), i, false);
        SafeParcelWriter.writeString(parcel2, 4, getErrorMessage(), false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        ConnectionResult connectionResult;
        Parcelable.Creator<ConnectionResult> creator;
        new ConnectionResult(0);
        RESULT_SUCCESS = connectionResult;
        new zza();
        CREATOR = creator;
    }
}
