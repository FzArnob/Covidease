package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzh implements Parcelable.Creator<MarkerOptions> {
    public zzh() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new MarkerOptions[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        LatLng latLng = null;
        String str = null;
        String str2 = null;
        IBinder iBinder = null;
        float f = 0.0f;
        float f2 = 0.0f;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        float f3 = 0.0f;
        float f4 = 0.5f;
        float f5 = 0.0f;
        float f6 = 1.0f;
        float f7 = 0.0f;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    latLng = (LatLng) SafeParcelReader.createParcelable(parcel3, i, LatLng.CREATOR);
                    break;
                case 3:
                    str = SafeParcelReader.createString(parcel3, i);
                    break;
                case 4:
                    str2 = SafeParcelReader.createString(parcel3, i);
                    break;
                case 5:
                    iBinder = SafeParcelReader.readIBinder(parcel3, i);
                    break;
                case 6:
                    f = SafeParcelReader.readFloat(parcel3, i);
                    break;
                case 7:
                    f2 = SafeParcelReader.readFloat(parcel3, i);
                    break;
                case 8:
                    z = SafeParcelReader.readBoolean(parcel3, i);
                    break;
                case 9:
                    z2 = SafeParcelReader.readBoolean(parcel3, i);
                    break;
                case 10:
                    z3 = SafeParcelReader.readBoolean(parcel3, i);
                    break;
                case 11:
                    f3 = SafeParcelReader.readFloat(parcel3, i);
                    break;
                case 12:
                    f4 = SafeParcelReader.readFloat(parcel3, i);
                    break;
                case 13:
                    f5 = SafeParcelReader.readFloat(parcel3, i);
                    break;
                case 14:
                    f6 = SafeParcelReader.readFloat(parcel3, i);
                    break;
                case 15:
                    f7 = SafeParcelReader.readFloat(parcel3, i);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new MarkerOptions(latLng, str, str2, iBinder, f, f2, z, z2, z3, f3, f4, f5, f6, f7);
        return obj;
    }
}
