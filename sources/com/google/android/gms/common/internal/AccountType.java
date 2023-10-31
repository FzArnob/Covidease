package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class AccountType {
    @KeepForSdk
    public static final String GOOGLE = "com.google";
    private static final String[] zzbs;

    private AccountType() {
    }

    static {
        String[] strArr = new String[3];
        strArr[0] = GOOGLE;
        String[] strArr2 = strArr;
        strArr2[1] = "com.google.work";
        String[] strArr3 = strArr2;
        strArr3[2] = "cn.google";
        zzbs = strArr3;
    }
}
