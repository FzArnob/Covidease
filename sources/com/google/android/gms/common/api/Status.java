package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;

@KeepForSdk
@SafeParcelable.Class(creator = "StatusCreator")
public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {
    public static final Parcelable.Creator<Status> CREATOR;
    @KeepForSdk
    public static final Status RESULT_CANCELED;
    @KeepForSdk
    public static final Status RESULT_DEAD_CLIENT;
    @KeepForSdk
    public static final Status RESULT_INTERNAL_ERROR;
    @KeepForSdk
    public static final Status RESULT_INTERRUPTED;
    @KeepForSdk
    @VisibleForTesting
    public static final Status RESULT_SUCCESS;
    @KeepForSdk
    public static final Status RESULT_TIMEOUT;
    private static final Status zzar;
    @SafeParcelable.VersionField(mo25283id = 1000)
    private final int zzg;
    @SafeParcelable.Field(getter = "getStatusCode", mo25277id = 1)
    private final int zzh;
    @Nullable
    @SafeParcelable.Field(getter = "getPendingIntent", mo25277id = 3)
    private final PendingIntent zzi;
    @Nullable
    @SafeParcelable.Field(getter = "getStatusMessage", mo25277id = 2)
    private final String zzj;

    @SafeParcelable.Constructor
    @KeepForSdk
    Status(@SafeParcelable.Param(mo25280id = 1000) int i, @SafeParcelable.Param(mo25280id = 1) int i2, @Nullable @SafeParcelable.Param(mo25280id = 2) String str, @Nullable @SafeParcelable.Param(mo25280id = 3) PendingIntent pendingIntent) {
        this.zzg = i;
        this.zzh = i2;
        this.zzj = str;
        this.zzi = pendingIntent;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @KeepForSdk
    public Status(int i) {
        this(i, (String) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @KeepForSdk
    public Status(int i, @Nullable String str) {
        this(1, i, str, (PendingIntent) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @KeepForSdk
    public Status(int i, @Nullable String str, @Nullable PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent);
    }

    public final void startResolutionForResult(Activity activity, int i) throws IntentSender.SendIntentException {
        Activity activity2 = activity;
        int i2 = i;
        if (hasResolution()) {
            activity2.startIntentSenderForResult(this.zzi.getIntentSender(), i2, (Intent) null, 0, 0, 0);
        }
    }

    @Nullable
    public final String getStatusMessage() {
        return this.zzj;
    }

    @VisibleForTesting
    public final boolean hasResolution() {
        return this.zzi != null;
    }

    public final boolean isSuccess() {
        return this.zzh <= 0;
    }

    public final boolean isCanceled() {
        return this.zzh == 16;
    }

    public final boolean isInterrupted() {
        return this.zzh == 14;
    }

    public final int getStatusCode() {
        return this.zzh;
    }

    public final PendingIntent getResolution() {
        return this.zzi;
    }

    public final int hashCode() {
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(this.zzg);
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(this.zzh);
        Object[] objArr3 = objArr2;
        objArr3[2] = this.zzj;
        Object[] objArr4 = objArr3;
        objArr4[3] = this.zzi;
        return Objects.hashCode(objArr4);
    }

    public final boolean equals(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof Status)) {
            return false;
        }
        Status status = (Status) obj2;
        if (this.zzg != status.zzg || this.zzh != status.zzh || !Objects.equal(this.zzj, status.zzj) || !Objects.equal(this.zzi, status.zzi)) {
            return false;
        }
        return true;
    }

    public final String zzg() {
        if (this.zzj != null) {
            return this.zzj;
        }
        return CommonStatusCodes.getStatusCodeString(this.zzh);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("statusCode", zzg()).add("resolution", this.zzi).toString();
    }

    @KeepForSdk
    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, getStatusCode());
        SafeParcelWriter.writeString(parcel2, 2, getStatusMessage(), false);
        SafeParcelWriter.writeParcelable(parcel2, 3, this.zzi, i, false);
        SafeParcelWriter.writeInt(parcel2, 1000, this.zzg);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    @KeepForSdk
    public final Status getStatus() {
        return this;
    }

    static {
        Status status;
        Status status2;
        Status status3;
        Status status4;
        Status status5;
        Status status6;
        Status status7;
        Parcelable.Creator<Status> creator;
        new Status(0);
        RESULT_SUCCESS = status;
        new Status(14);
        RESULT_INTERRUPTED = status2;
        new Status(8);
        RESULT_INTERNAL_ERROR = status3;
        new Status(15);
        RESULT_TIMEOUT = status4;
        new Status(16);
        RESULT_CANCELED = status5;
        new Status(17);
        zzar = status6;
        new Status(18);
        RESULT_DEAD_CLIENT = status7;
        new zzb();
        CREATOR = creator;
    }
}
