package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zzl implements Parcelable.Creator<PolylineOptions> {
    public zzl() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PolylineOptions[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        ArrayList<LatLng> arrayList = null;
        float f = 0.0f;
        int i = 0;
        float f2 = 0.0f;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        Cap cap = null;
        Cap cap2 = null;
        int i2 = 0;
        ArrayList<PatternItem> arrayList2 = null;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i3 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    arrayList = SafeParcelReader.createTypedList(parcel3, i3, LatLng.CREATOR);
                    break;
                case 3:
                    f = SafeParcelReader.readFloat(parcel3, i3);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel3, i3);
                    break;
                case 5:
                    f2 = SafeParcelReader.readFloat(parcel3, i3);
                    break;
                case 6:
                    z = SafeParcelReader.readBoolean(parcel3, i3);
                    break;
                case 7:
                    z2 = SafeParcelReader.readBoolean(parcel3, i3);
                    break;
                case 8:
                    z3 = SafeParcelReader.readBoolean(parcel3, i3);
                    break;
                case 9:
                    cap = (Cap) SafeParcelReader.createParcelable(parcel3, i3, Cap.CREATOR);
                    break;
                case 10:
                    cap2 = (Cap) SafeParcelReader.createParcelable(parcel3, i3, Cap.CREATOR);
                    break;
                case 11:
                    i2 = SafeParcelReader.readInt(parcel3, i3);
                    break;
                case 12:
                    arrayList2 = SafeParcelReader.createTypedList(parcel3, i3, PatternItem.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i3);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new PolylineOptions(arrayList, f, i, f2, z, z2, z3, cap, cap2, i2, arrayList2);
        return obj;
    }
}
