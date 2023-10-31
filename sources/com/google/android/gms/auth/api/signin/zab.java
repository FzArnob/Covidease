package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zab implements Parcelable.Creator<GoogleSignInAccount> {
    public zab() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new GoogleSignInAccount[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Uri uri = null;
        String str5 = null;
        long j = 0;
        String str6 = null;
        ArrayList<Scope> arrayList = null;
        String str7 = null;
        String str8 = null;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i2 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel3, i2);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel3, i2);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel3, i2);
                    break;
                case 4:
                    str3 = SafeParcelReader.createString(parcel3, i2);
                    break;
                case 5:
                    str4 = SafeParcelReader.createString(parcel3, i2);
                    break;
                case 6:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel3, i2, Uri.CREATOR);
                    break;
                case 7:
                    str5 = SafeParcelReader.createString(parcel3, i2);
                    break;
                case 8:
                    j = SafeParcelReader.readLong(parcel3, i2);
                    break;
                case 9:
                    str6 = SafeParcelReader.createString(parcel3, i2);
                    break;
                case 10:
                    arrayList = SafeParcelReader.createTypedList(parcel3, i2, Scope.CREATOR);
                    break;
                case 11:
                    str7 = SafeParcelReader.createString(parcel3, i2);
                    break;
                case 12:
                    str8 = SafeParcelReader.createString(parcel3, i2);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i2);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new GoogleSignInAccount(i, str, str2, str3, str4, uri, str5, j, str6, arrayList, str7, str8);
        return obj;
    }
}
