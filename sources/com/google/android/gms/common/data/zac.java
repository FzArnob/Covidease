package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zac implements Parcelable.Creator<DataHolder> {
    public zac() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new DataHolder[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        DataHolder dataHolder;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        int i = 0;
        String[] strArr = null;
        CursorWindow[] cursorWindowArr = null;
        int i2 = 0;
        Bundle bundle = null;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i3 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    strArr = SafeParcelReader.createStringArray(parcel3, i3);
                    break;
                case 2:
                    cursorWindowArr = (CursorWindow[]) SafeParcelReader.createTypedArray(parcel3, i3, CursorWindow.CREATOR);
                    break;
                case 3:
                    i2 = SafeParcelReader.readInt(parcel3, i3);
                    break;
                case 4:
                    bundle = SafeParcelReader.createBundle(parcel3, i3);
                    break;
                case 1000:
                    i = SafeParcelReader.readInt(parcel3, i3);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i3);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new DataHolder(i, strArr, cursorWindowArr, i2, bundle);
        DataHolder dataHolder2 = dataHolder;
        dataHolder2.zaca();
        return dataHolder2;
    }
}
