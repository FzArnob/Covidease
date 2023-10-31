package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

@SafeParcelable.Class(creator = "ParcelableGeofenceCreator")
@SafeParcelable.Reserved({1000})
@VisibleForTesting
public final class zzbh extends AbstractSafeParcelable implements Geofence {
    public static final Parcelable.Creator<zzbh> CREATOR;
    @SafeParcelable.Field(getter = "getRequestId", mo25277id = 1)
    private final String zzad;
    @SafeParcelable.Field(getter = "getTransitionTypes", mo25277id = 7)
    private final int zzae;
    @SafeParcelable.Field(getter = "getType", mo25277id = 3)
    private final short zzag;
    @SafeParcelable.Field(getter = "getLatitude", mo25277id = 4)
    private final double zzah;
    @SafeParcelable.Field(getter = "getLongitude", mo25277id = 5)
    private final double zzai;
    @SafeParcelable.Field(getter = "getRadius", mo25277id = 6)
    private final float zzaj;
    @SafeParcelable.Field(defaultValue = "0", getter = "getNotificationResponsiveness", mo25277id = 8)
    private final int zzak;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getLoiteringDelay", mo25277id = 9)
    private final int zzal;
    @SafeParcelable.Field(getter = "getExpirationTime", mo25277id = 2)
    private final long zzdo;

    static {
        Parcelable.Creator<zzbh> creator;
        new zzbi();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    public zzbh(@SafeParcelable.Param(mo25280id = 1) String str, @SafeParcelable.Param(mo25280id = 7) int i, @SafeParcelable.Param(mo25280id = 3) short s, @SafeParcelable.Param(mo25280id = 4) double d, @SafeParcelable.Param(mo25280id = 5) double d2, @SafeParcelable.Param(mo25280id = 6) float f, @SafeParcelable.Param(mo25280id = 2) long j, @SafeParcelable.Param(mo25280id = 8) int i2, @SafeParcelable.Param(mo25280id = 9) int i3) {
        String str2;
        String str3;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Throwable th4;
        StringBuilder sb4;
        String str4 = str;
        int i4 = i;
        short s2 = s;
        double d3 = d;
        double d4 = d2;
        float f2 = f;
        long j2 = j;
        int i5 = i2;
        int i6 = i3;
        String str5 = str4;
        String str6 = str5;
        if (str5 == null || str6.length() > 100) {
            IllegalArgumentException illegalArgumentException = r25;
            String valueOf = String.valueOf(str6);
            String str7 = valueOf;
            if (valueOf.length() != 0) {
                str3 = "requestId is null or too long: ".concat(str7);
            } else {
                str3 = str2;
                new String("requestId is null or too long: ");
            }
            IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException(str3);
            throw illegalArgumentException;
        }
        float f3 = f2;
        float f4 = f3;
        if (f3 <= 0.0f) {
            Throwable th5 = th4;
            new StringBuilder(31);
            new IllegalArgumentException(sb4.append("invalid radius: ").append(f4).toString());
            throw th5;
        }
        double d5 = d4;
        double d6 = d3;
        double d7 = d6;
        if (d6 > 90.0d || d7 < -90.0d) {
            Throwable th6 = th;
            new StringBuilder(42);
            new IllegalArgumentException(sb.append("invalid latitude: ").append(d7).toString());
            throw th6;
        } else if (d5 > 180.0d || d5 < -180.0d) {
            Throwable th7 = th2;
            new StringBuilder(43);
            new IllegalArgumentException(sb2.append("invalid longitude: ").append(d5).toString());
            throw th7;
        } else {
            int i7 = i4;
            int i8 = i7;
            int i9 = i7 & 7;
            int i10 = i9;
            if (i9 == 0) {
                Throwable th8 = th3;
                new StringBuilder(46);
                new IllegalArgumentException(sb3.append("No supported transition specified: ").append(i8).toString());
                throw th8;
            }
            this.zzag = s2;
            this.zzad = str4;
            this.zzah = d3;
            this.zzai = d4;
            this.zzaj = f2;
            this.zzdo = j2;
            this.zzae = i10;
            this.zzak = i5;
            this.zzal = i6;
        }
    }

    public static zzbh zza(byte[] bArr) {
        byte[] bArr2 = bArr;
        Parcel obtain = Parcel.obtain();
        Parcel parcel = obtain;
        obtain.unmarshall(bArr2, 0, bArr2.length);
        parcel.setDataPosition(0);
        zzbh createFromParcel = CREATOR.createFromParcel(parcel);
        parcel.recycle();
        return createFromParcel;
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r9) {
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
            if (r4 != 0) goto L_0x000f
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x000f:
            r4 = r2
            boolean r4 = r4 instanceof com.google.android.gms.internal.location.zzbh
            if (r4 != 0) goto L_0x0017
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x0017:
            r4 = r2
            com.google.android.gms.internal.location.zzbh r4 = (com.google.android.gms.internal.location.zzbh) r4
            r3 = r4
            r4 = r1
            float r4 = r4.zzaj
            r5 = r3
            float r5 = r5.zzaj
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 == 0) goto L_0x0028
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x0028:
            r4 = r1
            double r4 = r4.zzah
            r6 = r3
            double r6 = r6.zzah
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x0035
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x0035:
            r4 = r1
            double r4 = r4.zzai
            r6 = r3
            double r6 = r6.zzai
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x0042
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x0042:
            r4 = r1
            short r4 = r4.zzag
            r5 = r3
            short r5 = r5.zzag
            if (r4 == r5) goto L_0x004d
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x004d:
            r4 = 1
            r1 = r4
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzbh.equals(java.lang.Object):boolean");
    }

    public final String getRequestId() {
        return this.zzad;
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.zzah);
        int i = 31 + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        long doubleToLongBits2 = Double.doubleToLongBits(this.zzai);
        int i2 = (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        int i3 = i2;
        int floatToIntBits = (i2 * 31) + Float.floatToIntBits(this.zzaj);
        int i4 = floatToIntBits;
        int i5 = (floatToIntBits * 31) + this.zzag;
        int i6 = i5;
        return (i5 * 31) + this.zzae;
    }

    public final String toString() {
        String str;
        Locale locale = Locale.US;
        Object[] objArr = new Object[9];
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr;
        switch (this.zzag) {
            case 1:
                str = "CIRCLE";
                break;
            default:
                str = null;
                break;
        }
        objArr3[0] = str;
        Object[] objArr4 = objArr2;
        objArr4[1] = this.zzad.replaceAll("\\p{C}", "?");
        Object[] objArr5 = objArr4;
        objArr5[2] = Integer.valueOf(this.zzae);
        Object[] objArr6 = objArr5;
        objArr6[3] = Double.valueOf(this.zzah);
        Object[] objArr7 = objArr6;
        objArr7[4] = Double.valueOf(this.zzai);
        Object[] objArr8 = objArr7;
        objArr8[5] = Float.valueOf(this.zzaj);
        Object[] objArr9 = objArr8;
        objArr9[6] = Integer.valueOf(this.zzak / 1000);
        Object[] objArr10 = objArr9;
        objArr10[7] = Integer.valueOf(this.zzal);
        Object[] objArr11 = objArr10;
        objArr11[8] = Long.valueOf(this.zzdo);
        return String.format(locale, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", objArr11);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeString(parcel2, 1, getRequestId(), false);
        SafeParcelWriter.writeLong(parcel2, 2, this.zzdo);
        SafeParcelWriter.writeShort(parcel2, 3, this.zzag);
        SafeParcelWriter.writeDouble(parcel2, 4, this.zzah);
        SafeParcelWriter.writeDouble(parcel2, 5, this.zzai);
        SafeParcelWriter.writeFloat(parcel2, 6, this.zzaj);
        SafeParcelWriter.writeInt(parcel2, 7, this.zzae);
        SafeParcelWriter.writeInt(parcel2, 8, this.zzak);
        SafeParcelWriter.writeInt(parcel2, 9, this.zzal);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}
