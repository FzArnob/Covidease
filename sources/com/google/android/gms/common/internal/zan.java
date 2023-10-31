package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zan implements Parcelable.Creator<ResolveAccountResponse> {
    public zan() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ResolveAccountResponse[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        int i = 0;
        IBinder iBinder = null;
        ConnectionResult connectionResult = null;
        boolean z = false;
        boolean z2 = false;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i2 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel3, i2);
                    break;
                case 2:
                    iBinder = SafeParcelReader.readIBinder(parcel3, i2);
                    break;
                case 3:
                    connectionResult = (ConnectionResult) SafeParcelReader.createParcelable(parcel3, i2, ConnectionResult.CREATOR);
                    break;
                case 4:
                    z = SafeParcelReader.readBoolean(parcel3, i2);
                    break;
                case 5:
                    z2 = SafeParcelReader.readBoolean(parcel3, i2);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i2);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new ResolveAccountResponse(i, iBinder, connectionResult, z, z2);
        return obj;
    }
}
