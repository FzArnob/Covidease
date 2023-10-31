package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzaa implements Parcelable.Creator<GoogleMapOptions> {
    public zzaa() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new GoogleMapOptions[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        byte b = -1;
        byte b2 = -1;
        int i = 0;
        CameraPosition cameraPosition = null;
        byte b3 = -1;
        byte b4 = -1;
        byte b5 = -1;
        byte b6 = -1;
        byte b7 = -1;
        byte b8 = -1;
        byte b9 = -1;
        byte b10 = -1;
        byte b11 = -1;
        Float f = null;
        Float f2 = null;
        LatLngBounds latLngBounds = null;
        byte b12 = -1;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i2 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    b = SafeParcelReader.readByte(parcel3, i2);
                    break;
                case 3:
                    b2 = SafeParcelReader.readByte(parcel3, i2);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel3, i2);
                    break;
                case 5:
                    cameraPosition = (CameraPosition) SafeParcelReader.createParcelable(parcel3, i2, CameraPosition.CREATOR);
                    break;
                case 6:
                    b3 = SafeParcelReader.readByte(parcel3, i2);
                    break;
                case 7:
                    b4 = SafeParcelReader.readByte(parcel3, i2);
                    break;
                case 8:
                    b5 = SafeParcelReader.readByte(parcel3, i2);
                    break;
                case 9:
                    b6 = SafeParcelReader.readByte(parcel3, i2);
                    break;
                case 10:
                    b7 = SafeParcelReader.readByte(parcel3, i2);
                    break;
                case 11:
                    b8 = SafeParcelReader.readByte(parcel3, i2);
                    break;
                case 12:
                    b9 = SafeParcelReader.readByte(parcel3, i2);
                    break;
                case 14:
                    b10 = SafeParcelReader.readByte(parcel3, i2);
                    break;
                case 15:
                    b11 = SafeParcelReader.readByte(parcel3, i2);
                    break;
                case 16:
                    f = SafeParcelReader.readFloatObject(parcel3, i2);
                    break;
                case 17:
                    f2 = SafeParcelReader.readFloatObject(parcel3, i2);
                    break;
                case 18:
                    latLngBounds = (LatLngBounds) SafeParcelReader.createParcelable(parcel3, i2, LatLngBounds.CREATOR);
                    break;
                case 19:
                    b12 = SafeParcelReader.readByte(parcel3, i2);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i2);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new GoogleMapOptions(b, b2, i, cameraPosition, b3, b4, b5, b6, b7, b8, b9, b10, b11, f, f2, latLngBounds, b12);
        return obj;
    }
}
