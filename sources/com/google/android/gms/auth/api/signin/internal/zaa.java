package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zaa implements Parcelable.Creator<GoogleSignInOptionsExtensionParcelable> {
    public zaa() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new GoogleSignInOptionsExtensionParcelable[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        int i = 0;
        int i2 = 0;
        Bundle bundle = null;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i3 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel3, i3);
                    break;
                case 2:
                    i2 = SafeParcelReader.readInt(parcel3, i3);
                    break;
                case 3:
                    bundle = SafeParcelReader.createBundle(parcel3, i3);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i3);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new GoogleSignInOptionsExtensionParcelable(i, i2, bundle);
        return obj;
    }
}
