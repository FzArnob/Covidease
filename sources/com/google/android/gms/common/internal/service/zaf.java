package com.google.android.gms.common.internal.service;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

final class zaf extends zaa {
    private final BaseImplementation.ResultHolder<Status> mResultHolder;

    public zaf(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.mResultHolder = resultHolder;
    }

    public final void zaj(int i) throws RemoteException {
        Object obj;
        new Status(i);
        this.mResultHolder.setResult(obj);
    }
}
