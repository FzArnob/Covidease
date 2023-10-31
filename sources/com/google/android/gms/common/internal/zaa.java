package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zaa implements Parcelable.Creator<AuthAccountRequest> {
    public zaa() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new AuthAccountRequest[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Object obj;
        Parcel parcel2 = parcel;
        Parcel parcel3 = parcel2;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel2);
        int i = 0;
        IBinder iBinder = null;
        Scope[] scopeArr = null;
        Integer num = null;
        Integer num2 = null;
        Account account = null;
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
                    scopeArr = (Scope[]) SafeParcelReader.createTypedArray(parcel3, i2, Scope.CREATOR);
                    break;
                case 4:
                    num = SafeParcelReader.readIntegerObject(parcel3, i2);
                    break;
                case 5:
                    num2 = SafeParcelReader.readIntegerObject(parcel3, i2);
                    break;
                case 6:
                    account = (Account) SafeParcelReader.createParcelable(parcel3, i2, Account.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel3, i2);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel3, validateObjectHeader);
        new AuthAccountRequest(i, iBinder, scopeArr, num, num2, account);
        return obj;
    }
}
