package com.google.android.gms.common.stats;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.zza;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
public class WakeLockTracker {
    private static WakeLockTracker zzgc;
    private static Boolean zzgd;
    @VisibleForTesting
    private static boolean zzge = false;

    public WakeLockTracker() {
    }

    @KeepForSdk
    public static WakeLockTracker getInstance() {
        return zzgc;
    }

    @KeepForSdk
    public void registerAcquireEvent(Context context, Intent intent, String str, String str2, String str3, int i, String str4) {
        String str5 = str3;
        String str6 = str2;
        Context context2 = context;
        Context context3 = context2;
        registerEvent(context2, intent.getStringExtra(LoggingConstants.EXTRA_WAKE_LOCK_KEY), 7, str, str6, str5, i, Arrays.asList(new String[]{str4}));
    }

    @KeepForSdk
    public void registerReleaseEvent(Context context, Intent intent) {
        registerEvent(context, intent.getStringExtra(LoggingConstants.EXTRA_WAKE_LOCK_KEY), 8, (String) null, (String) null, (String) null, 0, (List<String>) null);
    }

    @KeepForSdk
    public void registerEvent(Context context, String str, int i, String str2, String str3, String str4, int i2, List<String> list) {
        registerEvent(context, str, i, str2, str3, str4, i2, list, 0);
    }

    @KeepForSdk
    public void registerEvent(Context context, String str, int i, String str2, String str3, String str4, int i2, List<String> list, long j) {
        WakeLockEvent wakeLockEvent;
        String str5;
        String str6;
        Context context2 = context;
        String str7 = str;
        int i3 = i;
        String str8 = str2;
        String str9 = str3;
        String str10 = str4;
        int i4 = i2;
        List<String> list2 = list;
        long j2 = j;
        if (zzw()) {
            if (TextUtils.isEmpty(str7)) {
                String valueOf = String.valueOf(str7);
                String str11 = valueOf;
                if (valueOf.length() != 0) {
                    str6 = "missing wakeLock key. ".concat(str11);
                } else {
                    str6 = str5;
                    new String("missing wakeLock key. ");
                }
                int e = Log.e("WakeLockTracker", str6);
            } else if (7 == i3 || 8 == i3 || 10 == i3 || 11 == i3) {
                new WakeLockEvent(System.currentTimeMillis(), i3, str8, i4, StatsUtils.zza(list2), str7, SystemClock.elapsedRealtime(), zza.zzg(context2), str9, StatsUtils.zzi(context2.getPackageName()), zza.zzh(context2), j2, str10, false);
                zza(context2, wakeLockEvent);
            }
        }
    }

    @KeepForSdk
    public void registerDeadlineEvent(Context context, String str, String str2, String str3, int i, List<String> list, boolean z, long j) {
        WakeLockEvent wakeLockEvent;
        Context context2 = context;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        int i2 = i;
        List<String> list2 = list;
        boolean z2 = z;
        long j2 = j;
        if (zzw()) {
            new WakeLockEvent(System.currentTimeMillis(), 16, str4, i2, StatsUtils.zza(list2), (String) null, j2, zza.zzg(context2), str5, StatsUtils.zzi(context2.getPackageName()), zza.zzh(context2), 0, str6, z2);
            zza(context2, wakeLockEvent);
        }
    }

    private static void zza(Context context, WakeLockEvent wakeLockEvent) {
        Intent intent;
        WakeLockEvent wakeLockEvent2 = wakeLockEvent;
        Context context2 = context;
        try {
            new Intent();
            ComponentName startService = context2.startService(intent.setComponent(LoggingConstants.zzfg).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", wakeLockEvent2));
        } catch (Exception e) {
            int wtf = Log.wtf("WakeLockTracker", e);
        }
    }

    private static boolean zzw() {
        if (zzgd == null) {
            zzgd = false;
        }
        return zzgd.booleanValue();
    }

    static {
        WakeLockTracker wakeLockTracker;
        new WakeLockTracker();
        zzgc = wakeLockTracker;
    }
}
