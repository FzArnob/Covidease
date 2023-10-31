package com.google.android.gms.common.internal;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public final class zzg {
    private static final Uri zzed;
    private static final Uri zzee;

    public static Intent zzg(String str) {
        Intent intent;
        Uri fromParts = Uri.fromParts("package", str, (String) null);
        new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        Intent intent2 = intent;
        Intent intent3 = intent2;
        Intent data = intent2.setData(fromParts);
        return intent3;
    }

    public static Intent zza(String str, @Nullable String str2) {
        Intent intent;
        new Intent("android.intent.action.VIEW");
        Intent intent2 = intent;
        Intent intent3 = intent2;
        Intent intent4 = intent2;
        String str3 = str2;
        Uri.Builder appendQueryParameter = Uri.parse("market://details").buildUpon().appendQueryParameter("id", str);
        if (!TextUtils.isEmpty(str3)) {
            Uri.Builder appendQueryParameter2 = appendQueryParameter.appendQueryParameter("pcampaignid", str3);
        }
        Intent data = intent3.setData(appendQueryParameter.build());
        Intent intent5 = intent4.setPackage("com.android.vending");
        Intent addFlags = intent4.addFlags(524288);
        return intent4;
    }

    public static Intent zzs() {
        Intent intent;
        new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
        Intent intent2 = intent;
        Intent intent3 = intent2.setPackage("com.google.android.wearable.app");
        return intent2;
    }

    static {
        Uri parse = Uri.parse("https://plus.google.com/");
        zzed = parse;
        zzee = parse.buildUpon().appendPath("circles").appendPath("find").build();
    }
}
