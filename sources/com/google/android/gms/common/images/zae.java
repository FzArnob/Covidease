package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zae implements Parcelable.Creator<WebImage> {
    public zae() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new WebImage[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        int i = 0;
        Uri uri = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i4 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel3, i4);
                    break;
                case 2:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel3, i4, Uri.CREATOR);
                    break;
                case 3:
                    i2 = SafeParcelReader.readInt(parcel3, i4);
                    break;
                case 4:
                    i3 = SafeParcelReader.readInt(parcel3, i4);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i4);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new WebImage(i, uri, i2, i3);
        return obj;
    }
}
