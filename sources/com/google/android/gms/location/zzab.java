package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzab implements Parcelable.Creator<LocationRequest> {
    public zzab() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        int i = 102;
        long j = 3600000;
        long j2 = 600000;
        boolean z = false;
        long j3 = Long.MAX_VALUE;
        int i2 = Integer.MAX_VALUE;
        float f = 0.0f;
        long j4 = 0;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i3 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel3, i3);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel3, i3);
                    break;
                case 3:
                    j2 = SafeParcelReader.readLong(parcel3, i3);
                    break;
                case 4:
                    z = SafeParcelReader.readBoolean(parcel3, i3);
                    break;
                case 5:
                    j3 = SafeParcelReader.readLong(parcel3, i3);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel3, i3);
                    break;
                case 7:
                    f = SafeParcelReader.readFloat(parcel3, i3);
                    break;
                case 8:
                    j4 = SafeParcelReader.readLong(parcel3, i3);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i3);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new LocationRequest(i, j, j2, z, j3, i2, f, j4);
        return obj;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new LocationRequest[i];
    }
}
