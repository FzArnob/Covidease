package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zzc implements Parcelable.Creator<CircleOptions> {
    public zzc() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new CircleOptions[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        LatLng latLng = null;
        double d = 0.0d;
        float f = 0.0f;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        boolean z = false;
        boolean z2 = false;
        ArrayList<PatternItem> arrayList = null;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i3 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    latLng = (LatLng) SafeParcelReader.createParcelable(parcel3, i3, LatLng.CREATOR);
                    break;
                case 3:
                    d = SafeParcelReader.readDouble(parcel3, i3);
                    break;
                case 4:
                    f = SafeParcelReader.readFloat(parcel3, i3);
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel3, i3);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel3, i3);
                    break;
                case 7:
                    f2 = SafeParcelReader.readFloat(parcel3, i3);
                    break;
                case 8:
                    z = SafeParcelReader.readBoolean(parcel3, i3);
                    break;
                case 9:
                    z2 = SafeParcelReader.readBoolean(parcel3, i3);
                    break;
                case 10:
                    arrayList = SafeParcelReader.createTypedList(parcel3, i3, PatternItem.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i3);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new CircleOptions(latLng, d, f, i, i2, f2, z, z2, arrayList);
        return obj;
    }
}
