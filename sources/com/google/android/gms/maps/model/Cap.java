package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "CapCreator")
@SafeParcelable.Reserved({1})
public class Cap extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Cap> CREATOR;
    private static final String TAG = Cap.class.getSimpleName();
    @Nullable
    @SafeParcelable.Field(getter = "getWrappedBitmapDescriptorImplBinder", mo25277id = 3, type = "android.os.IBinder")
    private final BitmapDescriptor bitmapDescriptor;
    @SafeParcelable.Field(getter = "getType", mo25277id = 2)
    private final int type;
    @Nullable
    @SafeParcelable.Field(getter = "getBitmapRefWidth", mo25277id = 4)
    private final Float zzcn;

    private Cap(int i, @Nullable BitmapDescriptor bitmapDescriptor2, @Nullable Float f) {
        int i2 = i;
        BitmapDescriptor bitmapDescriptor3 = bitmapDescriptor2;
        Float f2 = f;
        boolean z = i2 != 3 || (bitmapDescriptor3 != null && (f2 != null && (f2.floatValue() > 0.0f ? 1 : (f2.floatValue() == 0.0f ? 0 : -1)) > 0));
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(i2);
        Object[] objArr2 = objArr;
        objArr2[1] = bitmapDescriptor3;
        Object[] objArr3 = objArr2;
        objArr3[2] = f2;
        Preconditions.checkArgument(z, String.format("Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", objArr3));
        this.type = i2;
        this.bitmapDescriptor = bitmapDescriptor3;
        this.zzcn = f2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
    /* Code decompiled incorrectly, please refer to instructions dump. */
    Cap(@com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo25280id = 2) int r13, @android.support.annotation.Nullable @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo25280id = 3) android.os.IBinder r14, @android.support.annotation.Nullable @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo25280id = 4) java.lang.Float r15) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r6 = r0
            r7 = r1
            r8 = r2
            r11 = r8
            r8 = r11
            r9 = r11
            r4 = r9
            if (r8 != 0) goto L_0x0013
            r8 = 0
        L_0x000e:
            r9 = r3
            r6.<init>((int) r7, (com.google.android.gms.maps.model.BitmapDescriptor) r8, (java.lang.Float) r9)
            return
        L_0x0013:
            r8 = r4
            com.google.android.gms.dynamic.IObjectWrapper r8 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r8)
            r5 = r8
            com.google.android.gms.maps.model.BitmapDescriptor r8 = new com.google.android.gms.maps.model.BitmapDescriptor
            r11 = r8
            r8 = r11
            r9 = r11
            r10 = r5
            r9.<init>(r10)
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.Cap.<init>(int, android.os.IBinder, java.lang.Float):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected Cap(@NonNull BitmapDescriptor bitmapDescriptor2, float f) {
        this(3, bitmapDescriptor2, Float.valueOf(f));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected Cap(int i) {
        this(i, (BitmapDescriptor) null, (Float) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        IBinder asBinder;
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 2, this.type);
        Parcel parcel3 = parcel2;
        if (this.bitmapDescriptor == null) {
            asBinder = null;
        } else {
            asBinder = this.bitmapDescriptor.zzb().asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel3, 3, asBinder, false);
        SafeParcelWriter.writeFloatObject(parcel2, 4, this.zzcn, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public int hashCode() {
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(this.type);
        Object[] objArr2 = objArr;
        objArr2[1] = this.bitmapDescriptor;
        Object[] objArr3 = objArr2;
        objArr3[2] = this.zzcn;
        return Objects.hashCode(objArr3);
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
            boolean r3 = r3 instanceof com.google.android.gms.maps.model.Cap
            if (r3 != 0) goto L_0x0011
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0011:
            r3 = r1
            com.google.android.gms.maps.model.Cap r3 = (com.google.android.gms.maps.model.Cap) r3
            r2 = r3
            r3 = r0
            int r3 = r3.type
            r4 = r2
            int r4 = r4.type
            if (r3 != r4) goto L_0x0038
            r3 = r0
            com.google.android.gms.maps.model.BitmapDescriptor r3 = r3.bitmapDescriptor
            r4 = r2
            com.google.android.gms.maps.model.BitmapDescriptor r4 = r4.bitmapDescriptor
            boolean r3 = com.google.android.gms.common.internal.Objects.equal(r3, r4)
            if (r3 == 0) goto L_0x0038
            r3 = r0
            java.lang.Float r3 = r3.zzcn
            r4 = r2
            java.lang.Float r4 = r4.zzcn
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.Cap.equals(java.lang.Object):boolean");
    }

    public String toString() {
        StringBuilder sb;
        int i = this.type;
        new StringBuilder(23);
        return sb.append("[Cap: type=").append(i).append("]").toString();
    }

    /* access modifiers changed from: package-private */
    public final Cap zzh() {
        Cap cap;
        Cap cap2;
        Cap cap3;
        Cap cap4;
        StringBuilder sb;
        switch (this.type) {
            case 0:
                new ButtCap();
                return cap4;
            case 1:
                new SquareCap();
                return cap3;
            case 2:
                new RoundCap();
                return cap2;
            case 3:
                new CustomCap(this.bitmapDescriptor, this.zzcn.floatValue());
                return cap;
            default:
                String str = TAG;
                int i = this.type;
                new StringBuilder(29);
                int w = Log.w(str, sb.append("Unknown Cap type: ").append(i).toString());
                return this;
        }
    }

    static {
        Parcelable.Creator<Cap> creator;
        new zzb();
        CREATOR = creator;
    }
}
