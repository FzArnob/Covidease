package com.google.android.gms.common.api.internal;

import android.support.annotation.Nullable;
import android.support.p000v4.util.C1642ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
import java.util.Set;

public final class zak {
    private final C1642ArrayMap<zai<?>, ConnectionResult> zaay;
    private final C1642ArrayMap<zai<?>, String> zadb;
    private final TaskCompletionSource<Map<zai<?>, String>> zadc;
    private int zadd;
    private boolean zade = false;

    public zak(Iterable<? extends GoogleApi<?>> iterable) {
        C1642ArrayMap<zai<?>, String> arrayMap;
        TaskCompletionSource<Map<zai<?>, String>> taskCompletionSource;
        C1642ArrayMap<zai<?>, ConnectionResult> arrayMap2;
        new C1642ArrayMap<>();
        this.zadb = arrayMap;
        new TaskCompletionSource<>();
        this.zadc = taskCompletionSource;
        new C1642ArrayMap<>();
        this.zaay = arrayMap2;
        for (GoogleApi zak : iterable) {
            ConnectionResult put = this.zaay.put(zak.zak(), null);
        }
        this.zadd = this.zaay.keySet().size();
    }

    public final Set<zai<?>> zap() {
        return this.zaay.keySet();
    }

    public final Task<Map<zai<?>, String>> getTask() {
        return this.zadc.getTask();
    }

    public final void zaa(zai<?> zai, ConnectionResult connectionResult, @Nullable String str) {
        Exception exc;
        zai<?> zai2 = zai;
        ConnectionResult connectionResult2 = connectionResult;
        ConnectionResult put = this.zaay.put(zai2, connectionResult2);
        String put2 = this.zadb.put(zai2, str);
        this.zadd--;
        if (!connectionResult2.isSuccess()) {
            this.zade = true;
        }
        if (this.zadd != 0) {
            return;
        }
        if (this.zade) {
            new AvailabilityException(this.zaay);
            this.zadc.setException(exc);
            return;
        }
        this.zadc.setResult(this.zadb);
    }
}
