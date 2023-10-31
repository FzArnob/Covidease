package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "StreetViewSourceCreator")
@SafeParcelable.Reserved({1})
public final class StreetViewSource extends AbstractSafeParcelable {
    public static final Parcelable.Creator<StreetViewSource> CREATOR;
    public static final StreetViewSource DEFAULT;
    public static final StreetViewSource OUTDOOR;
    private static final String TAG = StreetViewSource.class.getSimpleName();
    @SafeParcelable.Field(getter = "getType", mo25277id = 2)
    private final int type;

    @SafeParcelable.Constructor
    public StreetViewSource(@SafeParcelable.Param(mo25280id = 2) int i) {
        this.type = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 2, this.type);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.type));
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r6) {
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
            boolean r3 = r3 instanceof com.google.android.gms.maps.model.StreetViewSource
            if (r3 != 0) goto L_0x0011
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0011:
            r3 = r1
            com.google.android.gms.maps.model.StreetViewSource r3 = (com.google.android.gms.maps.model.StreetViewSource) r3
            r2 = r3
            r3 = r0
            int r3 = r3.type
            r4 = r2
            int r4 = r4.type
            if (r3 != r4) goto L_0x0020
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x0020:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.StreetViewSource.equals(java.lang.Object):boolean");
    }

    public final String toString() {
        String str;
        switch (this.type) {
            case 0:
                str = "DEFAULT";
                break;
            case 1:
                str = "OUTDOOR";
                break;
            default:
                str = String.format("UNKNOWN(%s)", new Object[]{Integer.valueOf(this.type)});
                break;
        }
        return String.format("StreetViewSource:%s", new Object[]{str});
    }

    static {
        Parcelable.Creator<StreetViewSource> creator;
        StreetViewSource streetViewSource;
        StreetViewSource streetViewSource2;
        new zzq();
        CREATOR = creator;
        new StreetViewSource(0);
        DEFAULT = streetViewSource;
        new StreetViewSource(1);
        OUTDOOR = streetViewSource2;
    }
}
