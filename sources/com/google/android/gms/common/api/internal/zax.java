package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.C1642ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zax implements zabs {
    private final Looper zabj;
    private final GoogleApiManager zabm;
    /* access modifiers changed from: private */
    public final Lock zaeo;
    private final ClientSettings zaet;
    /* access modifiers changed from: private */
    public final Map<Api.AnyClientKey<?>, zaw<?>> zaeu;
    /* access modifiers changed from: private */
    public final Map<Api.AnyClientKey<?>, zaw<?>> zaev;
    private final Map<Api<?>, Boolean> zaew;
    /* access modifiers changed from: private */
    public final zaaw zaex;
    private final GoogleApiAvailabilityLight zaey;
    /* access modifiers changed from: private */
    public final Condition zaez;
    private final boolean zafa;
    /* access modifiers changed from: private */
    public final boolean zafb;
    private final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zafc;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public boolean zafd;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public Map<zai<?>, ConnectionResult> zafe;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public Map<zai<?>, ConnectionResult> zaff;
    @GuardedBy("mLock")
    private zaaa zafg;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public ConnectionResult zafh;

    public zax(Context context, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, ArrayList<zaq> arrayList, zaaw zaaw, boolean z) {
        Map<Api.AnyClientKey<?>, zaw<?>> map3;
        Map<Api.AnyClientKey<?>, zaw<?>> map4;
        Queue<BaseImplementation.ApiMethodImpl<?, ?>> queue;
        Map map5;
        Map map6;
        Object obj;
        Context context2 = context;
        Lock lock2 = lock;
        Looper looper2 = looper;
        Map<Api.AnyClientKey<?>, Api.Client> map7 = map;
        ClientSettings clientSettings2 = clientSettings;
        Map<Api<?>, Boolean> map8 = map2;
        Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder2 = abstractClientBuilder;
        ArrayList<zaq> arrayList2 = arrayList;
        new HashMap();
        this.zaeu = map3;
        new HashMap();
        this.zaev = map4;
        new LinkedList();
        this.zafc = queue;
        this.zaeo = lock2;
        this.zabj = looper2;
        this.zaez = lock2.newCondition();
        this.zaey = googleApiAvailabilityLight;
        this.zaex = zaaw;
        this.zaew = map8;
        this.zaet = clientSettings2;
        this.zafa = z;
        new HashMap();
        Map map9 = map5;
        for (Api next : map8.keySet()) {
            Object put = map9.put(next.getClientKey(), next);
        }
        new HashMap();
        Map map10 = map6;
        ArrayList arrayList3 = arrayList2;
        ArrayList arrayList4 = arrayList3;
        int size = arrayList3.size();
        int i = 0;
        while (i < size) {
            i++;
            zaq zaq = (zaq) arrayList4.get(i);
            Object put2 = map10.put(zaq.mApi, zaq);
        }
        boolean z2 = false;
        boolean z3 = true;
        boolean z4 = false;
        for (Map.Entry next2 : map7.entrySet()) {
            Api api = (Api) map9.get(next2.getKey());
            Api.Client client = (Api.Client) next2.getValue();
            Api.Client client2 = client;
            if (client.requiresGooglePlayServices()) {
                z4 = true;
                if (!this.zaew.get(api).booleanValue()) {
                    z2 = true;
                }
            } else {
                z3 = false;
            }
            new zaw(context2, api, looper2, client2, (zaq) map10.get(api), clientSettings2, abstractClientBuilder2);
            Object obj2 = obj;
            zaw<?> put3 = this.zaeu.put((Api.AnyClientKey) next2.getKey(), obj2);
            if (client2.requiresSignIn()) {
                zaw<?> put4 = this.zaev.put((Api.AnyClientKey) next2.getKey(), obj2);
            }
        }
        this.zafb = z4 && !z3 && !z2;
        this.zabm = GoogleApiManager.zabc();
    }

    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        T t2 = t;
        if (this.zafa && zab(t2)) {
            return t2;
        }
        if (!isConnected()) {
            boolean add = this.zafc.add(t2);
            return t2;
        }
        this.zaex.zahf.zab(t2);
        return this.zaeu.get(t2.getClientKey()).doRead(t2);
    }

    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        T t2 = t;
        Api.AnyClientKey clientKey = t2.getClientKey();
        if (this.zafa && zab(t2)) {
            return t2;
        }
        this.zaex.zahf.zab(t2);
        return this.zaeu.get(clientKey).doWrite(t2);
    }

    private final <T extends BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>> boolean zab(@NonNull T t) {
        Status status;
        T t2 = t;
        Api.AnyClientKey clientKey = t2.getClientKey();
        ConnectionResult zaa = zaa((Api.AnyClientKey<?>) clientKey);
        ConnectionResult connectionResult = zaa;
        if (zaa == null || connectionResult.getErrorCode() != 4) {
            return false;
        }
        new Status(4, (String) null, this.zabm.zaa((zai<?>) this.zaeu.get(clientKey).zak(), System.identityHashCode(this.zaex)));
        t2.setFailedResult(status);
        return true;
    }

    public final void connect() {
        Executor executor;
        OnCompleteListener onCompleteListener;
        this.zaeo.lock();
        try {
            if (this.zafd) {
                this.zaeo.unlock();
                return;
            }
            this.zafd = true;
            this.zafe = null;
            this.zaff = null;
            this.zafg = null;
            this.zafh = null;
            this.zabm.zao();
            new HandlerExecutor(this.zabj);
            new zaz(this, (zay) null);
            Task<Map<zai<?>, String>> addOnCompleteListener = this.zabm.zaa((Iterable<? extends GoogleApi<?>>) this.zaeu.values()).addOnCompleteListener(executor, onCompleteListener);
            this.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect() {
        ConnectionResult connectionResult;
        ConnectionResult connectionResult2;
        connect();
        while (isConnecting()) {
            try {
                this.zaez.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                new ConnectionResult(15, (PendingIntent) null);
                return connectionResult2;
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        if (this.zafh != null) {
            return this.zafh;
        }
        new ConnectionResult(13, (PendingIntent) null);
        return connectionResult;
    }

    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        ConnectionResult connectionResult;
        ConnectionResult connectionResult2;
        ConnectionResult connectionResult3;
        connect();
        long nanos = timeUnit.toNanos(j);
        while (true) {
            long j2 = nanos;
            if (isConnecting()) {
                if (j2 <= 0) {
                    try {
                        disconnect();
                        new ConnectionResult(14, (PendingIntent) null);
                        return connectionResult3;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        new ConnectionResult(15, (PendingIntent) null);
                        return connectionResult2;
                    }
                } else {
                    nanos = this.zaez.awaitNanos(j2);
                }
            } else if (isConnected()) {
                return ConnectionResult.RESULT_SUCCESS;
            } else {
                if (this.zafh != null) {
                    return this.zafh;
                }
                new ConnectionResult(13, (PendingIntent) null);
                return connectionResult;
            }
        }
    }

    public final void disconnect() {
        this.zaeo.lock();
        try {
            this.zafd = false;
            this.zafe = null;
            this.zaff = null;
            if (this.zafg != null) {
                this.zafg.cancel();
                this.zafg = null;
            }
            this.zafh = null;
            while (!this.zafc.isEmpty()) {
                BaseImplementation.ApiMethodImpl remove = this.zafc.remove();
                remove.zaa((zacs) null);
                remove.cancel();
            }
            this.zaez.signalAll();
            this.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    @Nullable
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return zaa(api.getClientKey());
    }

    /* JADX INFO: finally extract failed */
    @Nullable
    private final ConnectionResult zaa(@NonNull Api.AnyClientKey<?> anyClientKey) {
        Api.AnyClientKey<?> anyClientKey2 = anyClientKey;
        this.zaeo.lock();
        try {
            zaw zaw = this.zaeu.get(anyClientKey2);
            if (this.zafe == null || zaw == null) {
                this.zaeo.unlock();
                return null;
            }
            ConnectionResult connectionResult = this.zafe.get(zaw.zak());
            this.zaeo.unlock();
            return connectionResult;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    public final boolean isConnected() {
        this.zaeo.lock();
        try {
            boolean z = this.zafe != null && this.zafh == null;
            this.zaeo.unlock();
            return z;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    public final boolean isConnecting() {
        this.zaeo.lock();
        try {
            boolean z = this.zafe == null && this.zafd;
            this.zaeo.unlock();
            return z;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002d A[Catch:{ all -> 0x005b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zaac() {
        /*
            r8 = this;
            r0 = r8
            r5 = r0
            java.util.concurrent.locks.Lock r5 = r5.zaeo
            r5.lock()
            r5 = r0
            boolean r5 = r5.zafd     // Catch:{ all -> 0x005b }
            if (r5 == 0) goto L_0x0011
            r5 = r0
            boolean r5 = r5.zafa     // Catch:{ all -> 0x005b }
            if (r5 != 0) goto L_0x001a
        L_0x0011:
            r5 = r0
            java.util.concurrent.locks.Lock r5 = r5.zaeo
            r5.unlock()
            r5 = 0
            r0 = r5
        L_0x0019:
            return r0
        L_0x001a:
            r5 = r0
            java.util.Map<com.google.android.gms.common.api.Api$AnyClientKey<?>, com.google.android.gms.common.api.internal.zaw<?>> r5 = r5.zaev     // Catch:{ all -> 0x005b }
            java.util.Set r5 = r5.keySet()     // Catch:{ all -> 0x005b }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x005b }
            r1 = r5
        L_0x0026:
            r5 = r1
            boolean r5 = r5.hasNext()     // Catch:{ all -> 0x005b }
            if (r5 == 0) goto L_0x0052
            r5 = r1
            java.lang.Object r5 = r5.next()     // Catch:{ all -> 0x005b }
            com.google.android.gms.common.api.Api$AnyClientKey r5 = (com.google.android.gms.common.api.Api.AnyClientKey) r5     // Catch:{ all -> 0x005b }
            r2 = r5
            r5 = r0
            r6 = r2
            com.google.android.gms.common.ConnectionResult r5 = r5.zaa((com.google.android.gms.common.api.Api.AnyClientKey<?>) r6)     // Catch:{ all -> 0x005b }
            r7 = r5
            r5 = r7
            r6 = r7
            r3 = r6
            if (r5 == 0) goto L_0x0048
            r5 = r3
            boolean r5 = r5.isSuccess()     // Catch:{ all -> 0x005b }
            if (r5 != 0) goto L_0x0051
        L_0x0048:
            r5 = r0
            java.util.concurrent.locks.Lock r5 = r5.zaeo
            r5.unlock()
            r5 = 0
            r0 = r5
            goto L_0x0019
        L_0x0051:
            goto L_0x0026
        L_0x0052:
            r5 = r0
            java.util.concurrent.locks.Lock r5 = r5.zaeo
            r5.unlock()
            r5 = 1
            r0 = r5
            goto L_0x0019
        L_0x005b:
            r5 = move-exception
            r4 = r5
            r5 = r0
            java.util.concurrent.locks.Lock r5 = r5.zaeo
            r5.unlock()
            r5 = r4
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zax.zaac():boolean");
    }

    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        zaaa zaaa;
        Executor executor;
        SignInConnectionListener signInConnectionListener2 = signInConnectionListener;
        this.zaeo.lock();
        try {
            if (!this.zafd || zaac()) {
                this.zaeo.unlock();
                return false;
            }
            this.zabm.zao();
            new zaaa(this, signInConnectionListener2);
            this.zafg = zaaa;
            new HandlerExecutor(this.zabj);
            Task<Map<zai<?>, String>> addOnCompleteListener = this.zabm.zaa((Iterable<? extends GoogleApi<?>>) this.zaev.values()).addOnCompleteListener(executor, this.zafg);
            this.zaeo.unlock();
            return true;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    public final void maybeSignOut() {
        Object obj;
        Map<zai<?>, ConnectionResult> map;
        this.zaeo.lock();
        try {
            this.zabm.maybeSignOut();
            if (this.zafg != null) {
                this.zafg.cancel();
                this.zafg = null;
            }
            if (this.zaff == null) {
                new C1642ArrayMap(this.zaev.size());
                this.zaff = map;
            }
            new ConnectionResult(4);
            Object obj2 = obj;
            for (zaw zak : this.zaev.values()) {
                ConnectionResult put = this.zaff.put(zak.zak(), obj2);
            }
            if (this.zafe != null) {
                this.zafe.putAll(this.zaff);
            }
            this.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public final void zaw() {
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaad() {
        Set<Scope> set;
        if (this.zaet == null) {
            this.zaex.zaha = Collections.emptySet();
            return;
        }
        new HashSet(this.zaet.getRequiredScopes());
        Set<Scope> set2 = set;
        Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = this.zaet.getOptionalApiSettings();
        Map<Api<?>, ClientSettings.OptionalApiSettings> map = optionalApiSettings;
        for (Api next : optionalApiSettings.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(next);
            ConnectionResult connectionResult2 = connectionResult;
            if (connectionResult != null && connectionResult2.isSuccess()) {
                boolean addAll = set2.addAll(map.get(next).mScopes);
            }
        }
        this.zaex.zaha = set2;
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaae() {
        while (!this.zafc.isEmpty()) {
            BaseImplementation.ApiMethodImpl execute = execute(this.zafc.remove());
        }
        this.zaex.zab((Bundle) null);
    }

    /* access modifiers changed from: private */
    public final boolean zaa(zaw<?> zaw, ConnectionResult connectionResult) {
        zaw<?> zaw2 = zaw;
        ConnectionResult connectionResult2 = connectionResult;
        if (connectionResult2.isSuccess() || connectionResult2.hasResolution() || !this.zaew.get(zaw2.getApi()).booleanValue() || !zaw2.zaab().requiresGooglePlayServices() || !this.zaey.isUserResolvableError(connectionResult2.getErrorCode())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    @Nullable
    @GuardedBy("mLock")
    public final ConnectionResult zaaf() {
        ConnectionResult connectionResult = null;
        int i = 0;
        ConnectionResult connectionResult2 = null;
        int i2 = 0;
        for (zaw next : this.zaeu.values()) {
            Api api = next.getApi();
            ConnectionResult connectionResult3 = this.zafe.get(next.zak());
            ConnectionResult connectionResult4 = connectionResult3;
            if (!connectionResult3.isSuccess() && (!this.zaew.get(api).booleanValue() || connectionResult4.hasResolution() || this.zaey.isUserResolvableError(connectionResult4.getErrorCode()))) {
                if (connectionResult4.getErrorCode() != 4 || !this.zafa) {
                    int priority = api.zah().getPriority();
                    if (connectionResult == null || i > priority) {
                        connectionResult = connectionResult4;
                        i = priority;
                    }
                } else {
                    int priority2 = api.zah().getPriority();
                    if (connectionResult2 == null || i2 > priority2) {
                        connectionResult2 = connectionResult4;
                        i2 = priority2;
                    }
                }
            }
        }
        if (connectionResult == null || connectionResult2 == null || i <= i2) {
            return connectionResult;
        }
        return connectionResult2;
    }

    static /* synthetic */ Map zaa(zax zax, Map map) {
        Map map2 = map;
        Map map3 = map2;
        zax.zafe = map3;
        return map2;
    }

    static /* synthetic */ ConnectionResult zaa(zax zax, ConnectionResult connectionResult) {
        ConnectionResult connectionResult2 = connectionResult;
        ConnectionResult connectionResult3 = connectionResult2;
        zax.zafh = connectionResult3;
        return connectionResult2;
    }

    static /* synthetic */ boolean zaa(zax zax, boolean z) {
        boolean z2 = z;
        zax.zafd = false;
        return false;
    }

    static /* synthetic */ Map zab(zax zax, Map map) {
        Map map2 = map;
        Map map3 = map2;
        zax.zaff = map3;
        return map2;
    }
}
