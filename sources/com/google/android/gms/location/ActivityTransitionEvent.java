package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;

@SafeParcelable.Class(creator = "ActivityTransitionEventCreator")
@SafeParcelable.Reserved({1000})
public class ActivityTransitionEvent extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ActivityTransitionEvent> CREATOR;
    @SafeParcelable.Field(getter = "getActivityType", mo25277id = 1)
    private final int zzi;
    @SafeParcelable.Field(getter = "getTransitionType", mo25277id = 2)
    private final int zzj;
    @SafeParcelable.Field(getter = "getElapsedRealTimeNanos", mo25277id = 3)
    private final long zzk;

    static {
        Parcelable.Creator<ActivityTransitionEvent> creator;
        new zzd();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    public ActivityTransitionEvent(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) int i2, @SafeParcelable.Param(mo25280id = 3) long j) {
        int i3 = i;
        int i4 = i2;
        DetectedActivity.zzb(i3);
        ActivityTransition.zza(i4);
        this.zzi = i3;
        this.zzj = i4;
        this.zzk = j;
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            r1 = r8
            r2 = r9
            r4 = r1
            r5 = r2
            if (r4 != r5) goto L_0x0009
            r4 = 1
            r1 = r4
        L_0x0008:
            return r1
        L_0x0009:
            r4 = r2
            boolean r4 = r4 instanceof com.google.android.gms.location.ActivityTransitionEvent
            if (r4 != 0) goto L_0x0011
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x0011:
            r4 = r2
            com.google.android.gms.location.ActivityTransitionEvent r4 = (com.google.android.gms.location.ActivityTransitionEvent) r4
            r3 = r4
            r4 = r1
            int r4 = r4.zzi
            r5 = r3
            int r5 = r5.zzi
            if (r4 != r5) goto L_0x0032
            r4 = r1
            int r4 = r4.zzj
            r5 = r3
            int r5 = r5.zzj
            if (r4 != r5) goto L_0x0032
            r4 = r1
            long r4 = r4.zzk
            r6 = r3
            long r6 = r6.zzk
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0032
            r4 = 1
            r1 = r4
            goto L_0x0008
        L_0x0032:
            r4 = 0
            r1 = r4
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.ActivityTransitionEvent.equals(java.lang.Object):boolean");
    }

    public int getActivityType() {
        return this.zzi;
    }

    public long getElapsedRealTimeNanos() {
        return this.zzk;
    }

    public int getTransitionType() {
        return this.zzj;
    }

    public int hashCode() {
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(this.zzi);
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(this.zzj);
        Object[] objArr3 = objArr2;
        objArr3[2] = Long.valueOf(this.zzk);
        return Objects.hashCode(objArr3);
    }

    public String toString() {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        new StringBuilder();
        StringBuilder sb5 = sb;
        StringBuilder sb6 = sb5;
        int i = this.zzi;
        new StringBuilder(24);
        StringBuilder append = sb5.append(sb2.append("ActivityType ").append(i).toString());
        StringBuilder append2 = sb6.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        int i2 = this.zzj;
        new StringBuilder(26);
        StringBuilder append3 = sb6.append(sb3.append("TransitionType ").append(i2).toString());
        StringBuilder append4 = sb6.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        long j = this.zzk;
        new StringBuilder(41);
        StringBuilder append5 = sb6.append(sb4.append("ElapsedRealTimeNanos ").append(j).toString());
        return sb6.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, getActivityType());
        SafeParcelWriter.writeInt(parcel2, 2, getTransitionType());
        SafeParcelWriter.writeLong(parcel2, 3, getElapsedRealTimeNanos());
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}
