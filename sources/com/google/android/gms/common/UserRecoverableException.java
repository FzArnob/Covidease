package com.google.android.gms.common;

import android.content.Intent;

public class UserRecoverableException extends Exception {
    private final Intent mIntent;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UserRecoverableException(String str, Intent intent) {
        super(str);
        this.mIntent = intent;
    }

    public Intent getIntent() {
        Intent intent;
        new Intent(this.mIntent);
        return intent;
    }
}
