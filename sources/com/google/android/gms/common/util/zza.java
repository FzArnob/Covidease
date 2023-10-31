package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;

public final class zza {
    private static final IntentFilter filter;
    private static long zzgv;
    private static float zzgw = Float.NaN;

    @TargetApi(20)
    public static int zzg(Context context) {
        boolean isScreenOn;
        Context context2 = context;
        if (context2 == null || context2.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context2.getApplicationContext().registerReceiver((BroadcastReceiver) null, filter);
        boolean z = (7 & (registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0))) != 0;
        PowerManager powerManager = (PowerManager) context2.getSystemService("power");
        PowerManager powerManager2 = powerManager;
        if (powerManager == null) {
            return -1;
        }
        PowerManager powerManager3 = powerManager2;
        if (PlatformVersion.isAtLeastKitKatWatch()) {
            isScreenOn = powerManager3.isInteractive();
        } else {
            isScreenOn = powerManager3.isScreenOn();
        }
        return (isScreenOn ? 2 : 0) | (z ? 1 : 0);
    }

    public static synchronized float zzh(Context context) {
        float f;
        Context context2 = context;
        synchronized (zza.class) {
            if (SystemClock.elapsedRealtime() - zzgv >= 60000 || Float.isNaN(zzgw)) {
                Intent registerReceiver = context2.getApplicationContext().registerReceiver((BroadcastReceiver) null, filter);
                Intent intent = registerReceiver;
                if (registerReceiver != null) {
                    zzgw = ((float) intent.getIntExtra("level", -1)) / ((float) intent.getIntExtra("scale", -1));
                }
                zzgv = SystemClock.elapsedRealtime();
                f = zzgw;
            } else {
                f = zzgw;
            }
        }
        return f;
    }

    static {
        IntentFilter intentFilter;
        new IntentFilter("android.intent.action.BATTERY_CHANGED");
        filter = intentFilter;
    }
}
