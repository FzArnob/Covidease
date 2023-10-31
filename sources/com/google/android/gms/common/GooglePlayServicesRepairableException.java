package com.google.android.gms.common;

import android.content.Intent;

public class GooglePlayServicesRepairableException extends UserRecoverableException {
    private final int zzag;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GooglePlayServicesRepairableException(int i, String str, Intent intent) {
        super(str, intent);
        this.zzag = i;
    }

    public int getConnectionStatusCode() {
        return this.zzag;
    }
}
