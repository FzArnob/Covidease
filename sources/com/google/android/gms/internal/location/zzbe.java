package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;

public final class zzbe implements Parcelable.Creator<zzbd> {
    public zzbe() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        LocationRequest locationRequest = null;
        ArrayList<ClientIdentity> arrayList = zzbd.zzcd;
        String str = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        String str2 = null;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    locationRequest = (LocationRequest) SafeParcelReader.createParcelable(parcel3, i, LocationRequest.CREATOR);
                    break;
                case 5:
                    arrayList = SafeParcelReader.createTypedList(parcel3, i, ClientIdentity.CREATOR);
                    break;
                case 6:
                    str = SafeParcelReader.createString(parcel3, i);
                    break;
                case 7:
                    z = SafeParcelReader.readBoolean(parcel3, i);
                    break;
                case 8:
                    z2 = SafeParcelReader.readBoolean(parcel3, i);
                    break;
                case 9:
                    z3 = SafeParcelReader.readBoolean(parcel3, i);
                    break;
                case 10:
                    str2 = SafeParcelReader.createString(parcel3, i);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new zzbd(locationRequest, arrayList, str, z, z2, z3, str2);
        return obj;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbd[i];
    }
}
