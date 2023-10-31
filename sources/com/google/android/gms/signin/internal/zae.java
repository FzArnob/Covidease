package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.base.zab;
import com.google.android.gms.internal.base.zac;

public abstract class zae extends zab implements zad {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zae() {
        super("com.google.android.gms.signin.internal.ISignInCallbacks");
    }

    /* access modifiers changed from: protected */
    public boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        switch (i) {
            case 3:
                zaa((ConnectionResult) zac.zaa(parcel3, ConnectionResult.CREATOR), (zaa) zac.zaa(parcel3, zaa.CREATOR));
                break;
            case 4:
                zag((Status) zac.zaa(parcel3, Status.CREATOR));
                break;
            case 6:
                zah((Status) zac.zaa(parcel3, Status.CREATOR));
                break;
            case 7:
                zaa((Status) zac.zaa(parcel3, Status.CREATOR), (GoogleSignInAccount) zac.zaa(parcel3, GoogleSignInAccount.CREATOR));
                break;
            case 8:
                zab((zaj) zac.zaa(parcel3, zaj.CREATOR));
                break;
            default:
                return false;
        }
        parcel4.writeNoException();
        return true;
    }
}
