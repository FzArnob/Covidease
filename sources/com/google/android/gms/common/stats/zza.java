package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zza implements Parcelable.Creator<WakeLockEvent> {
    public zza() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new WakeLockEvent[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        int i = 0;
        long j = 0;
        int i2 = 0;
        String str = null;
        int i3 = 0;
        ArrayList<String> arrayList = null;
        String str2 = null;
        long j2 = 0;
        int i4 = 0;
        String str3 = null;
        String str4 = null;
        float f = 0.0f;
        long j3 = 0;
        String str5 = null;
        boolean z = false;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i5 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel3, i5);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel3, i5);
                    break;
                case 4:
                    str = SafeParcelReader.createString(parcel3, i5);
                    break;
                case 5:
                    i3 = SafeParcelReader.readInt(parcel3, i5);
                    break;
                case 6:
                    arrayList = SafeParcelReader.createStringList(parcel3, i5);
                    break;
                case 8:
                    j2 = SafeParcelReader.readLong(parcel3, i5);
                    break;
                case 10:
                    str3 = SafeParcelReader.createString(parcel3, i5);
                    break;
                case 11:
                    i2 = SafeParcelReader.readInt(parcel3, i5);
                    break;
                case 12:
                    str2 = SafeParcelReader.createString(parcel3, i5);
                    break;
                case 13:
                    str4 = SafeParcelReader.createString(parcel3, i5);
                    break;
                case 14:
                    i4 = SafeParcelReader.readInt(parcel3, i5);
                    break;
                case 15:
                    f = SafeParcelReader.readFloat(parcel3, i5);
                    break;
                case 16:
                    j3 = SafeParcelReader.readLong(parcel3, i5);
                    break;
                case 17:
                    str5 = SafeParcelReader.createString(parcel3, i5);
                    break;
                case 18:
                    z = SafeParcelReader.readBoolean(parcel3, i5);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i5);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new WakeLockEvent(i, j, i2, str, i3, arrayList, str2, j2, i4, str3, str4, f, j3, str5, z);
        return obj;
    }
}
