package com.google.android.gms.common.stats;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.List;

@KeepForSdk
public class ConnectionTracker {
    private static final Object zzdp;
    private static volatile ConnectionTracker zzfa;
    @VisibleForTesting
    private static boolean zzfb = false;
    private final List<String> zzfc = Collections.EMPTY_LIST;
    private final List<String> zzfd = Collections.EMPTY_LIST;
    private final List<String> zzfe = Collections.EMPTY_LIST;
    private final List<String> zzff = Collections.EMPTY_LIST;

    @KeepForSdk
    public static ConnectionTracker getInstance() {
        ConnectionTracker connectionTracker;
        if (zzfa == null) {
            Object obj = zzdp;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    if (zzfa == null) {
                        new ConnectionTracker();
                        zzfa = connectionTracker;
                    }
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        Object obj3 = obj2;
                        throw th2;
                    }
                }
            }
        }
        return zzfa;
    }

    private ConnectionTracker() {
    }

    public final boolean zza(Context context, String str, Intent intent, ServiceConnection serviceConnection, int i) {
        boolean zzc;
        String str2 = str;
        int i2 = i;
        ServiceConnection serviceConnection2 = serviceConnection;
        Intent intent2 = intent;
        Context context2 = context;
        Context context3 = context2;
        Context context4 = context2;
        ComponentName component = intent2.getComponent();
        ComponentName componentName = component;
        if (component == null) {
            zzc = false;
        } else {
            zzc = ClientLibraryUtils.zzc(context4, componentName.getPackageName());
        }
        if (!zzc) {
            return context3.bindService(intent2, serviceConnection2, i2);
        }
        int w = Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
        return false;
    }

    @KeepForSdk
    public boolean bindService(Context context, Intent intent, ServiceConnection serviceConnection, int i) {
        Context context2 = context;
        return zza(context2, context2.getClass().getName(), intent, serviceConnection, i);
    }

    @SuppressLint({"UntrackedBindService"})
    @KeepForSdk
    public void unbindService(Context context, ServiceConnection serviceConnection) {
        context.unbindService(serviceConnection);
    }

    static {
        Object obj;
        new Object();
        zzdp = obj;
    }
}
