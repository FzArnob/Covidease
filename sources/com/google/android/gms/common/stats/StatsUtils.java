package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.List;

@KeepForSdk
public class StatsUtils {
    public StatsUtils() {
    }

    @KeepForSdk
    public static String getEventKey(Context context, Intent intent) {
        return String.valueOf((((long) System.identityHashCode(context)) << 32) | ((long) System.identityHashCode(intent)));
    }

    @KeepForSdk
    public static String getEventKey(PowerManager.WakeLock wakeLock, String str) {
        String str2;
        String str3 = str;
        String valueOf = String.valueOf(String.valueOf((((long) Process.myPid()) << 32) | ((long) System.identityHashCode(wakeLock))));
        String valueOf2 = String.valueOf(TextUtils.isEmpty(str3) ? "" : str3);
        String str4 = valueOf2;
        if (valueOf2.length() != 0) {
            return valueOf.concat(str4);
        }
        new String(valueOf);
        return str2;
    }

    @Nullable
    static List<String> zza(@Nullable List<String> list) {
        List<String> list2;
        List<String> list3 = list;
        List<String> list4 = list3;
        if (list3 != null && list3.size() == 1) {
            if ("com.google.android.gms".equals(list3.get(0))) {
                list2 = null;
            } else {
                list2 = list3;
            }
            list4 = list2;
        }
        return list4;
    }

    @Nullable
    static String zzi(String str) {
        String str2 = str;
        if ("com.google.android.gms".equals(str2)) {
            return null;
        }
        return str2;
    }
}
