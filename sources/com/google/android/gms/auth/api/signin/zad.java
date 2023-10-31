package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zad implements Parcelable.Creator<GoogleSignInOptions> {
    public zad() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new GoogleSignInOptions[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        int i = 0;
        ArrayList<Scope> arrayList = null;
        Account account = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        String str = null;
        String str2 = null;
        ArrayList<GoogleSignInOptionsExtensionParcelable> arrayList2 = null;
        while (parcel3.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel3);
            int i2 = readHeader;
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel3, i2);
                    break;
                case 2:
                    arrayList = SafeParcelReader.createTypedList(parcel3, i2, Scope.CREATOR);
                    break;
                case 3:
                    account = (Account) SafeParcelReader.createParcelable(parcel3, i2, Account.CREATOR);
                    break;
                case 4:
                    z = SafeParcelReader.readBoolean(parcel3, i2);
                    break;
                case 5:
                    z2 = SafeParcelReader.readBoolean(parcel3, i2);
                    break;
                case 6:
                    z3 = SafeParcelReader.readBoolean(parcel3, i2);
                    break;
                case 7:
                    str = SafeParcelReader.createString(parcel3, i2);
                    break;
                case 8:
                    str2 = SafeParcelReader.createString(parcel3, i2);
                    break;
                case 9:
                    arrayList2 = SafeParcelReader.createTypedList(parcel3, i2, GoogleSignInOptionsExtensionParcelable.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i2);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new GoogleSignInOptions(i, arrayList, account, z, z2, z3, str, str2, arrayList2);
        return obj;
    }
}
