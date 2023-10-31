package com.google.firebase;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;

@KeepForSdk
public class FirebaseExceptionMapper implements StatusExceptionMapper {
    public FirebaseExceptionMapper() {
    }

    public Exception getException(Status status) {
        Exception exc;
        Exception exc2;
        Status status2 = status;
        if (status2.getStatusCode() == 8) {
            new FirebaseException(status2.zzg());
            return exc2;
        }
        new FirebaseApiNotAvailableException(status2.zzg());
        return exc;
    }
}
