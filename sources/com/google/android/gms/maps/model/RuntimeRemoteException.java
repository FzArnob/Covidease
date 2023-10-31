package com.google.android.gms.maps.model;

import android.os.RemoteException;

public final class RuntimeRemoteException extends RuntimeException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RuntimeRemoteException(RemoteException remoteException) {
        super(remoteException);
    }
}
