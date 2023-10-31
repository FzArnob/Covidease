package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zzag implements Parcelable.Creator<LocationSettingsRequest> {
    public zzag() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        ArrayList<LocationRequest> arrayList = null;
        boolean z = false;
        boolean z2 = false;
        zzae zzae = null;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    arrayList = SafeParcelReader.createTypedList(parcel3, i, LocationRequest.CREATOR);
                    break;
                case 2:
                    z = SafeParcelReader.readBoolean(parcel3, i);
                    break;
                case 3:
                    z2 = SafeParcelReader.readBoolean(parcel3, i);
                    break;
                case 5:
                    zzae = (zzae) SafeParcelReader.createParcelable(parcel3, i, zzae.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new LocationSettingsRequest(arrayList, z, z2, zzae);
        return obj;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new LocationSettingsRequest[i];
    }
}
