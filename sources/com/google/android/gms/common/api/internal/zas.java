package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.C1642ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import gnu.expr.Declaration;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

final class zas implements zabs {
    private final Context mContext;
    private final Looper zabj;
    private final zaaw zaee;
    /* access modifiers changed from: private */
    public final zabe zaef;
    /* access modifiers changed from: private */
    public final zabe zaeg;
    private final Map<Api.AnyClientKey<?>, zabe> zaeh;
    private final Set<SignInConnectionListener> zaei;
    private final Api.Client zaej;
    private Bundle zaek;
    private ConnectionResult zael = null;
    /* access modifiers changed from: private */
    public ConnectionResult zaem = null;
    /* access modifiers changed from: private */
    public boolean zaen = false;
    /* access modifiers changed from: private */
    public final Lock zaeo;
    @GuardedBy("mLock")
    private int zaep = 0;

    public static zas zaa(Context context, zaaw zaaw, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, ArrayList<zaq> arrayList) {
        Map map3;
        Map map4;
        Map map5;
        Map map6;
        ArrayList arrayList2;
        ArrayList arrayList3;
        zas zas;
        Throwable th;
        Throwable th2;
        Context context2 = context;
        zaaw zaaw2 = zaaw;
        Lock lock2 = lock;
        Looper looper2 = looper;
        GoogleApiAvailabilityLight googleApiAvailabilityLight2 = googleApiAvailabilityLight;
        ClientSettings clientSettings2 = clientSettings;
        Map<Api<?>, Boolean> map7 = map2;
        Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder2 = abstractClientBuilder;
        ArrayList<zaq> arrayList4 = arrayList;
        Api.Client client = null;
        new C1642ArrayMap();
        Map map8 = map3;
        new C1642ArrayMap();
        Map map9 = map4;
        for (Map.Entry next : map.entrySet()) {
            Map.Entry entry = next;
            Api.Client client2 = (Api.Client) next.getValue();
            Api.Client client3 = client2;
            if (client2.providesSignIn()) {
                client = client3;
            }
            if (client3.requiresSignIn()) {
                Object put = map8.put((Api.AnyClientKey) entry.getKey(), client3);
            } else {
                Object put2 = map9.put((Api.AnyClientKey) entry.getKey(), client3);
            }
        }
        Preconditions.checkState(!map8.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        new C1642ArrayMap();
        Map map10 = map5;
        new C1642ArrayMap();
        Map map11 = map6;
        for (Api next2 : map7.keySet()) {
            Api api = next2;
            Api.AnyClientKey<?> clientKey = next2.getClientKey();
            if (map8.containsKey(clientKey)) {
                Object put3 = map10.put(api, map7.get(api));
            } else if (map9.containsKey(clientKey)) {
                Object put4 = map11.put(api, map7.get(api));
            } else {
                Throwable th3 = th2;
                new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
                throw th3;
            }
        }
        new ArrayList();
        ArrayList arrayList5 = arrayList2;
        new ArrayList();
        ArrayList arrayList6 = arrayList3;
        ArrayList arrayList7 = arrayList4;
        ArrayList arrayList8 = arrayList7;
        int size = arrayList7.size();
        int i = 0;
        while (i < size) {
            i++;
            zaq zaq = (zaq) arrayList8.get(i);
            if (map10.containsKey(zaq.mApi)) {
                boolean add = arrayList5.add(zaq);
            } else if (map11.containsKey(zaq.mApi)) {
                boolean add2 = arrayList6.add(zaq);
            } else {
                Throwable th4 = th;
                new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
                throw th4;
            }
        }
        new zas(context2, zaaw2, lock2, looper2, googleApiAvailabilityLight2, map8, map9, clientSettings2, abstractClientBuilder2, client, arrayList5, arrayList6, map10, map11);
        return zas;
    }

    private zas(Context context, zaaw zaaw, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, Map<Api.AnyClientKey<?>, Api.Client> map2, ClientSettings clientSettings, Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, Api.Client client, ArrayList<zaq> arrayList, ArrayList<zaq> arrayList2, Map<Api<?>, Boolean> map3, Map<Api<?>, Boolean> map4) {
        Map map5;
        zabe zabe;
        zabt zabt;
        zabe zabe2;
        zabt zabt2;
        C1642ArrayMap arrayMap;
        Context context2 = context;
        Lock lock2 = lock;
        Looper looper2 = looper;
        GoogleApiAvailabilityLight googleApiAvailabilityLight2 = googleApiAvailabilityLight;
        Map<Api.AnyClientKey<?>, Api.Client> map6 = map;
        Map<Api.AnyClientKey<?>, Api.Client> map7 = map2;
        new WeakHashMap();
        this.zaei = Collections.newSetFromMap(map5);
        this.mContext = context2;
        this.zaee = zaaw;
        this.zaeo = lock2;
        this.zabj = looper2;
        this.zaej = client;
        new zau(this, (zat) null);
        new zabe(context2, this.zaee, lock2, looper2, googleApiAvailabilityLight2, map7, (ClientSettings) null, map4, (Api.AbstractClientBuilder<? extends zad, SignInOptions>) null, arrayList2, zabt);
        this.zaef = zabe;
        new zav(this, (zat) null);
        new zabe(context2, this.zaee, lock2, looper2, googleApiAvailabilityLight2, map6, clientSettings, map3, abstractClientBuilder, arrayList, zabt2);
        this.zaeg = zabe2;
        new C1642ArrayMap();
        C1642ArrayMap arrayMap2 = arrayMap;
        for (Api.AnyClientKey put : map7.keySet()) {
            Object put2 = arrayMap2.put(put, this.zaef);
        }
        for (Api.AnyClientKey put3 : map6.keySet()) {
            Object put4 = arrayMap2.put(put3, this.zaeg);
        }
        this.zaeh = Collections.unmodifiableMap(arrayMap2);
    }

    @GuardedBy("mLock")
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        Status status;
        T t2 = t;
        if (!zaa((BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>) t2)) {
            return this.zaef.enqueue(t2);
        }
        if (!zaz()) {
            return this.zaeg.enqueue(t2);
        }
        new Status(4, (String) null, zaaa());
        t2.setFailedResult(status);
        return t2;
    }

    @GuardedBy("mLock")
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        Status status;
        T t2 = t;
        if (!zaa((BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>) t2)) {
            return this.zaef.execute(t2);
        }
        if (!zaz()) {
            return this.zaeg.execute(t2);
        }
        new Status(4, (String) null, zaaa());
        t2.setFailedResult(status);
        return t2;
    }

    @Nullable
    @GuardedBy("mLock")
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        ConnectionResult connectionResult;
        Api<?> api2 = api;
        if (!this.zaeh.get(api2.getClientKey()).equals(this.zaeg)) {
            return this.zaef.getConnectionResult(api2);
        }
        if (!zaz()) {
            return this.zaeg.getConnectionResult(api2);
        }
        new ConnectionResult(4, zaaa());
        return connectionResult;
    }

    @GuardedBy("mLock")
    public final void connect() {
        this.zaep = 2;
        this.zaen = false;
        this.zaem = null;
        this.zael = null;
        this.zaef.connect();
        this.zaeg.connect();
    }

    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        Throwable th;
        long j2 = j;
        TimeUnit timeUnit2 = timeUnit;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    @GuardedBy("mLock")
    public final void disconnect() {
        this.zaem = null;
        this.zael = null;
        this.zaep = 0;
        this.zaef.disconnect();
        this.zaeg.disconnect();
        zay();
    }

    /* JADX INFO: finally extract failed */
    public final boolean isConnected() {
        this.zaeo.lock();
        try {
            boolean z = this.zaef.isConnected() && (this.zaeg.isConnected() || zaz() || this.zaep == 1);
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
            boolean z = this.zaep == 2;
            this.zaeo.unlock();
            return z;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        SignInConnectionListener signInConnectionListener2 = signInConnectionListener;
        this.zaeo.lock();
        try {
            if ((isConnecting() || isConnected()) && !this.zaeg.isConnected()) {
                boolean add = this.zaei.add(signInConnectionListener2);
                if (this.zaep == 0) {
                    this.zaep = 1;
                }
                this.zaem = null;
                this.zaeg.connect();
                this.zaeo.unlock();
                return true;
            }
            this.zaeo.unlock();
            return false;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    @GuardedBy("mLock")
    public final void zaw() {
        this.zaef.zaw();
        this.zaeg.zaw();
    }

    public final void maybeSignOut() {
        ConnectionResult connectionResult;
        Handler handler;
        Runnable runnable;
        this.zaeo.lock();
        try {
            boolean isConnecting = isConnecting();
            this.zaeg.disconnect();
            new ConnectionResult(4);
            this.zaem = connectionResult;
            if (isConnecting) {
                new zap(this.zabj);
                new zat(this);
                boolean post = handler.post(runnable);
            } else {
                zay();
            }
            this.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zax() {
        Throwable th;
        if (zab(this.zael)) {
            if (zab(this.zaem) || zaz()) {
                switch (this.zaep) {
                    case 1:
                        break;
                    case 2:
                        this.zaee.zab(this.zaek);
                        break;
                    default:
                        new AssertionError();
                        int wtf = Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", th);
                        break;
                }
                zay();
                this.zaep = 0;
            } else if (this.zaem == null) {
            } else {
                if (this.zaep == 1) {
                    zay();
                    return;
                }
                zaa(this.zaem);
                this.zaef.disconnect();
            }
        } else if (this.zael != null && zab(this.zaem)) {
            this.zaeg.disconnect();
            zaa(this.zael);
        } else if (this.zael != null && this.zaem != null) {
            ConnectionResult connectionResult = this.zael;
            if (this.zaeg.zahs < this.zaef.zahs) {
                connectionResult = this.zaem;
            }
            zaa(connectionResult);
        }
    }

    @GuardedBy("mLock")
    private final void zaa(ConnectionResult connectionResult) {
        Throwable th;
        ConnectionResult connectionResult2 = connectionResult;
        switch (this.zaep) {
            case 1:
                break;
            case 2:
                this.zaee.zac(connectionResult2);
                break;
            default:
                new Exception();
                int wtf = Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", th);
                break;
        }
        zay();
        this.zaep = 0;
    }

    @GuardedBy("mLock")
    private final void zay() {
        for (SignInConnectionListener onComplete : this.zaei) {
            onComplete.onComplete();
        }
        this.zaei.clear();
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaa(int i, boolean z) {
        this.zaee.zab(i, z);
        this.zaem = null;
        this.zael = null;
    }

    @GuardedBy("mLock")
    private final boolean zaz() {
        return this.zaem != null && this.zaem.getErrorCode() == 4;
    }

    private final boolean zaa(BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient> apiMethodImpl) {
        Api.AnyClientKey<? extends Api.AnyClient> clientKey = apiMethodImpl.getClientKey();
        Preconditions.checkArgument(this.zaeh.containsKey(clientKey), "GoogleApiClient is not configured to use the API required for this call.");
        return this.zaeh.get(clientKey).equals(this.zaeg);
    }

    @Nullable
    private final PendingIntent zaaa() {
        if (this.zaej == null) {
            return null;
        }
        return PendingIntent.getActivity(this.mContext, System.identityHashCode(this.zaee), this.zaej.getSignInIntent(), Declaration.PACKAGE_ACCESS);
    }

    /* access modifiers changed from: private */
    public final void zaa(Bundle bundle) {
        Bundle bundle2 = bundle;
        if (this.zaek == null) {
            this.zaek = bundle2;
        } else if (bundle2 != null) {
            this.zaek.putAll(bundle2);
        }
    }

    private static boolean zab(ConnectionResult connectionResult) {
        ConnectionResult connectionResult2 = connectionResult;
        return connectionResult2 != null && connectionResult2.isSuccess();
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2 = str;
        FileDescriptor fileDescriptor2 = fileDescriptor;
        PrintWriter printWriter2 = printWriter;
        String[] strArr2 = strArr;
        printWriter2.append(str2).append("authClient").println(":");
        this.zaeg.dump(String.valueOf(str2).concat("  "), fileDescriptor2, printWriter2, strArr2);
        printWriter2.append(str2).append("anonClient").println(":");
        this.zaef.dump(String.valueOf(str2).concat("  "), fileDescriptor2, printWriter2, strArr2);
    }

    static /* synthetic */ ConnectionResult zaa(zas zas, ConnectionResult connectionResult) {
        ConnectionResult connectionResult2 = connectionResult;
        ConnectionResult connectionResult3 = connectionResult2;
        zas.zael = connectionResult3;
        return connectionResult2;
    }

    static /* synthetic */ boolean zaa(zas zas, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        zas.zaen = z3;
        return z2;
    }

    static /* synthetic */ ConnectionResult zab(zas zas, ConnectionResult connectionResult) {
        ConnectionResult connectionResult2 = connectionResult;
        ConnectionResult connectionResult3 = connectionResult2;
        zas.zaem = connectionResult3;
        return connectionResult2;
    }
}
