package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Comparator;

@SafeParcelable.Class(creator = "DetectedActivityCreator")
@SafeParcelable.Reserved({1000})
public class DetectedActivity extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DetectedActivity> CREATOR;
    public static final int IN_VEHICLE = 0;
    public static final int ON_BICYCLE = 1;
    public static final int ON_FOOT = 2;
    public static final int RUNNING = 8;
    public static final int STILL = 3;
    public static final int TILTING = 5;
    public static final int UNKNOWN = 4;
    public static final int WALKING = 7;
    private static final Comparator<DetectedActivity> zzo;
    private static final int[] zzp = {9, 10};
    private static final int[] zzq = {0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 16, 17, 18, 19};
    private static final int[] zzr = {0, 1, 2, 3, 7, 8, 16, 17};
    @SafeParcelable.Field(mo25277id = 1)
    private int zzi;
    @SafeParcelable.Field(mo25277id = 2)
    private int zzs;

    static {
        Comparator<DetectedActivity> comparator;
        Parcelable.Creator<DetectedActivity> creator;
        new zzh();
        zzo = comparator;
        new zzi();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    public DetectedActivity(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) int i2) {
        this.zzi = i;
        this.zzs = i2;
    }

    public static void zzb(int i) {
        StringBuilder sb;
        int i2 = i;
        int i3 = i2;
        boolean z = false;
        int[] iArr = zzr;
        int[] iArr2 = iArr;
        int length = iArr.length;
        for (int i4 = 0; i4 < length; i4++) {
            if (iArr2[i4] == i3) {
                z = true;
            }
        }
        if (!z) {
            new StringBuilder(81);
            int w = Log.w("DetectedActivity", sb.append(i2).append(" is not a valid DetectedActivity supported by Activity Transition API.").toString());
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r0
            r4 = r1
            if (r3 != r4) goto L_0x0009
            r3 = 1
            r0 = r3
        L_0x0008:
            return r0
        L_0x0009:
            r3 = r1
            if (r3 == 0) goto L_0x0018
            r3 = r0
            java.lang.Class r3 = r3.getClass()
            r4 = r1
            java.lang.Class r4 = r4.getClass()
            if (r3 == r4) goto L_0x001b
        L_0x0018:
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x001b:
            r3 = r1
            com.google.android.gms.location.DetectedActivity r3 = (com.google.android.gms.location.DetectedActivity) r3
            r2 = r3
            r3 = r0
            int r3 = r3.zzi
            r4 = r2
            int r4 = r4.zzi
            if (r3 != r4) goto L_0x0032
            r3 = r0
            int r3 = r3.zzs
            r4 = r2
            int r4 = r4.zzs
            if (r3 != r4) goto L_0x0032
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x0032:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.DetectedActivity.equals(java.lang.Object):boolean");
    }

    public int getConfidence() {
        return this.zzs;
    }

    public int getType() {
        int i = this.zzi;
        int i2 = i;
        if (i > 19 || i2 < 0) {
            return 4;
        }
        return i2;
    }

    public int hashCode() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(this.zzi);
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(this.zzs);
        return Objects.hashCode(objArr2);
    }

    public String toString() {
        String str;
        StringBuilder sb;
        int type = getType();
        int i = type;
        switch (type) {
            case 0:
                str = "IN_VEHICLE";
                break;
            case 1:
                str = "ON_BICYCLE";
                break;
            case 2:
                str = "ON_FOOT";
                break;
            case 3:
                str = "STILL";
                break;
            case 4:
                str = "UNKNOWN";
                break;
            case 5:
                str = "TILTING";
                break;
            case 7:
                str = "WALKING";
                break;
            case 8:
                str = "RUNNING";
                break;
            case 16:
                str = "IN_ROAD_VEHICLE";
                break;
            case 17:
                str = "IN_RAIL_VEHICLE";
                break;
            case 18:
                str = "IN_TWO_WHEELER_VEHICLE";
                break;
            case 19:
                str = "IN_FOUR_WHEELER_VEHICLE";
                break;
            default:
                str = Integer.toString(i);
                break;
        }
        String str2 = str;
        int i2 = this.zzs;
        new StringBuilder(48 + String.valueOf(str2).length());
        return sb.append("DetectedActivity [type=").append(str2).append(", confidence=").append(i2).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zzi);
        SafeParcelWriter.writeInt(parcel2, 2, this.zzs);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}
