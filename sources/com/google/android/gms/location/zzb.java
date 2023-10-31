package com.google.android.gms.location;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzb implements Parcelable.Creator<ActivityRecognitionResult> {
    public zzb() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        ArrayList<DetectedActivity> arrayList = null;
        long j = 0;
        long j2 = 0;
        int i = 0;
        Bundle bundle = null;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i2 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    arrayList = SafeParcelReader.createTypedList(parcel3, i2, DetectedActivity.CREATOR);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel3, i2);
                    break;
                case 3:
                    j2 = SafeParcelReader.readLong(parcel3, i2);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel3, i2);
                    break;
                case 5:
                    bundle = SafeParcelReader.createBundle(parcel3, i2);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i2);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new ActivityRecognitionResult((List<DetectedActivity>) arrayList, j, j2, i, bundle);
        return obj;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ActivityRecognitionResult[i];
    }
}
