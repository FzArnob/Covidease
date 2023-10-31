package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzbi implements Parcelable.Creator<zzbh> {
    public zzbi() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        String str = null;
        int i = 0;
        short s = 0;
        double d = 0.0d;
        double d2 = 0.0d;
        float f = 0.0f;
        long j = 0;
        int i2 = 0;
        int i3 = -1;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i4 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    str = SafeParcelReader.createString(parcel3, i4);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel3, i4);
                    break;
                case 3:
                    s = SafeParcelReader.readShort(parcel3, i4);
                    break;
                case 4:
                    d = SafeParcelReader.readDouble(parcel3, i4);
                    break;
                case 5:
                    d2 = SafeParcelReader.readDouble(parcel3, i4);
                    break;
                case 6:
                    f = SafeParcelReader.readFloat(parcel3, i4);
                    break;
                case 7:
                    i = SafeParcelReader.readInt(parcel3, i4);
                    break;
                case 8:
                    i2 = SafeParcelReader.readInt(parcel3, i4);
                    break;
                case 9:
                    i3 = SafeParcelReader.readInt(parcel3, i4);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i4);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new zzbh(str, i, s, d, d2, f, j, i2, i3);
        return obj;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbh[i];
    }
}
