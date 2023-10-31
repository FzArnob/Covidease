package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.location.zzj;
import java.util.ArrayList;

public final class zzn implements Parcelable.Creator<zzm> {
    public zzn() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        zzj zzj = zzm.zzce;
        ArrayList<ClientIdentity> arrayList = zzm.zzcd;
        String str = null;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    zzj = (zzj) SafeParcelReader.createParcelable(parcel3, i, zzj.CREATOR);
                    break;
                case 2:
                    arrayList = SafeParcelReader.createTypedList(parcel3, i, ClientIdentity.CREATOR);
                    break;
                case 3:
                    str = SafeParcelReader.createString(parcel3, i);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new zzm(zzj, arrayList, str);
        return obj;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzm[i];
    }
}
