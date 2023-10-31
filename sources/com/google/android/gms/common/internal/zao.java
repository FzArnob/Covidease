package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zao implements Parcelable.Creator<SignInButtonConfig> {
    public zao() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new SignInButtonConfig[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        Scope[] scopeArr = null;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i4 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel3, i4);
                    break;
                case 2:
                    i2 = SafeParcelReader.readInt(parcel3, i4);
                    break;
                case 3:
                    i3 = SafeParcelReader.readInt(parcel3, i4);
                    break;
                case 4:
                    scopeArr = (Scope[]) SafeParcelReader.createTypedArray(parcel3, i4, Scope.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i4);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new SignInButtonConfig(i, i2, i3, scopeArr);
        return obj;
    }
}
