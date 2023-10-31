package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.server.converter.zaa;
import com.google.android.gms.common.server.response.FastJsonResponse;

public final class zai implements Parcelable.Creator<FastJsonResponse.Field> {
    public zai() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new FastJsonResponse.Field[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        int i = 0;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        String str = null;
        int i4 = 0;
        String str2 = null;
        zaa zaa = null;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i5 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel3, i5);
                    break;
                case 2:
                    i2 = SafeParcelReader.readInt(parcel3, i5);
                    break;
                case 3:
                    z = SafeParcelReader.readBoolean(parcel3, i5);
                    break;
                case 4:
                    i3 = SafeParcelReader.readInt(parcel3, i5);
                    break;
                case 5:
                    z2 = SafeParcelReader.readBoolean(parcel3, i5);
                    break;
                case 6:
                    str = SafeParcelReader.createString(parcel3, i5);
                    break;
                case 7:
                    i4 = SafeParcelReader.readInt(parcel3, i5);
                    break;
                case 8:
                    str2 = SafeParcelReader.createString(parcel3, i5);
                    break;
                case 9:
                    zaa = (zaa) SafeParcelReader.createParcelable(parcel3, i5, zaa.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i5);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new FastJsonResponse.Field(i, i2, z, i3, z2, str, i4, str2, zaa);
        return obj;
    }
}
