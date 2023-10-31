package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzk implements Parcelable.Creator<PolygonOptions> {
    public zzk() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PolygonOptions[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        List list;
        Object obj;
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        ArrayList<LatLng> arrayList = null;
        new ArrayList();
        List list2 = list;
        float f = 0.0f;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i3 = 0;
        ArrayList<PatternItem> arrayList2 = null;
        while (parcel2.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel2);
            int i4 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    arrayList = SafeParcelReader.createTypedList(parcel2, i4, LatLng.CREATOR);
                    break;
                case 3:
                    SafeParcelReader.readList(parcel2, i4, list2, getClass().getClassLoader());
                    break;
                case 4:
                    f = SafeParcelReader.readFloat(parcel2, i4);
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel2, i4);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel2, i4);
                    break;
                case 7:
                    f2 = SafeParcelReader.readFloat(parcel2, i4);
                    break;
                case 8:
                    z = SafeParcelReader.readBoolean(parcel2, i4);
                    break;
                case 9:
                    z2 = SafeParcelReader.readBoolean(parcel2, i4);
                    break;
                case 10:
                    z3 = SafeParcelReader.readBoolean(parcel2, i4);
                    break;
                case 11:
                    i3 = SafeParcelReader.readInt(parcel2, i4);
                    break;
                case 12:
                    arrayList2 = SafeParcelReader.createTypedList(parcel2, i4, PatternItem.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, i4);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        new PolygonOptions(arrayList, list2, f, i, i2, f2, z, z2, z3, i3, arrayList2);
        return obj;
    }
}
