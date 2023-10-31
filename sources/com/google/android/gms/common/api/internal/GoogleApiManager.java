package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.p000v4.util.C1642ArrayMap;
import android.support.p000v4.util.C1643ArraySet;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.signin.zad;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import gnu.expr.Declaration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public class GoogleApiManager implements Handler.Callback {
    /* access modifiers changed from: private */
    public static final Object lock;
    public static final Status zahx;
    /* access modifiers changed from: private */
    public static final Status zahy;
    @GuardedBy("lock")
    private static GoogleApiManager zaic;
    /* access modifiers changed from: private */
    public final Handler handler;
    /* access modifiers changed from: private */
    public long zahz = 5000;
    /* access modifiers changed from: private */
    public long zaia = 120000;
    /* access modifiers changed from: private */
    public long zaib = 10000;
    /* access modifiers changed from: private */
    public final Context zaid;
    /* access modifiers changed from: private */
    public final GoogleApiAvailability zaie;
    /* access modifiers changed from: private */
    public final GoogleApiAvailabilityCache zaif;
    private final AtomicInteger zaig;
    private final AtomicInteger zaih;
    /* access modifiers changed from: private */
    public final Map<zai<?>, zaa<?>> zaii;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public zaae zaij;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public final Set<zai<?>> zaik;
    private final Set<zai<?>> zail;

    /* JADX INFO: finally extract failed */
    public static GoogleApiManager zab(Context context) {
        HandlerThread handlerThread;
        GoogleApiManager googleApiManager;
        Context context2 = context;
        Object obj = lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (zaic == null) {
                    new HandlerThread("GoogleApiHandler", 9);
                    HandlerThread handlerThread2 = handlerThread;
                    handlerThread2.start();
                    new GoogleApiManager(context2.getApplicationContext(), handlerThread2.getLooper(), GoogleApiAvailability.getInstance());
                    zaic = googleApiManager;
                }
                GoogleApiManager googleApiManager2 = zaic;
                return googleApiManager2;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    private static class zab {
        /* access modifiers changed from: private */
        public final zai<?> zajb;
        /* access modifiers changed from: private */
        public final Feature zajc;

        private zab(zai<?> zai, Feature feature) {
            this.zajb = zai;
            this.zajc = feature;
        }

        public final boolean equals(Object obj) {
            Object obj2 = obj;
            if (obj2 == null || !(obj2 instanceof zab)) {
                return false;
            }
            zab zab = (zab) obj2;
            if (!Objects.equal(this.zajb, zab.zajb) || !Objects.equal(this.zajc, zab.zajc)) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            Object[] objArr = new Object[2];
            objArr[0] = this.zajb;
            Object[] objArr2 = objArr;
            objArr2[1] = this.zajc;
            return Objects.hashCode(objArr2);
        }

        public final String toString() {
            return Objects.toStringHelper(this).add("key", this.zajb).add("feature", this.zajc).toString();
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ zab(zai zai, Feature feature, zabi zabi) {
            this(zai, feature);
            zabi zabi2 = zabi;
        }
    }

    private class zac implements zach, BaseGmsClient.ConnectionProgressReportCallbacks {
        /* access modifiers changed from: private */
        public final zai<?> zafq;
        final /* synthetic */ GoogleApiManager zaim;
        /* access modifiers changed from: private */
        public final Api.Client zaio;
        private IAccountAccessor zajd = null;
        private Set<Scope> zaje = null;
        private boolean zajf = false;

        public zac(GoogleApiManager googleApiManager, Api.Client client, zai<?> zai) {
            this.zaim = googleApiManager;
            this.zaio = client;
            this.zafq = zai;
        }

        public final void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
            Runnable runnable;
            new zabo(this, connectionResult);
            boolean post = this.zaim.handler.post(runnable);
        }

        @WorkerThread
        public final void zag(ConnectionResult connectionResult) {
            ((zaa) this.zaim.zaii.get(this.zafq)).zag(connectionResult);
        }

        @WorkerThread
        public final void zaa(IAccountAccessor iAccountAccessor, Set<Scope> set) {
            Throwable th;
            ConnectionResult connectionResult;
            IAccountAccessor iAccountAccessor2 = iAccountAccessor;
            Set<Scope> set2 = set;
            if (iAccountAccessor2 == null || set2 == null) {
                new Exception();
                int wtf = Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", th);
                new ConnectionResult(4);
                zag(connectionResult);
                return;
            }
            this.zajd = iAccountAccessor2;
            this.zaje = set2;
            zabr();
        }

        /* access modifiers changed from: private */
        @WorkerThread
        public final void zabr() {
            if (this.zajf && this.zajd != null) {
                this.zaio.getRemoteService(this.zajd, this.zaje);
            }
        }

        static /* synthetic */ boolean zaa(zac zac, boolean z) {
            boolean z2 = z;
            zac.zajf = true;
            return true;
        }
    }

    public static GoogleApiManager zabc() {
        Object obj = lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                Object checkNotNull = Preconditions.checkNotNull(zaic, "Must guarantee manager is non-null before using getInstance");
                GoogleApiManager googleApiManager = zaic;
                return googleApiManager;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    @KeepForSdk
    public static void reportSignOut() {
        Object obj = lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (zaic != null) {
                    GoogleApiManager googleApiManager = zaic;
                    GoogleApiManager googleApiManager2 = googleApiManager;
                    int incrementAndGet = googleApiManager.zaih.incrementAndGet();
                    boolean sendMessageAtFrontOfQueue = googleApiManager2.handler.sendMessageAtFrontOfQueue(googleApiManager2.handler.obtainMessage(10));
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public class zaa<O extends Api.ApiOptions> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zar {
        private final zai<O> zafq;
        final /* synthetic */ GoogleApiManager zaim;
        private final Queue<zab> zain;
        /* access modifiers changed from: private */
        public final Api.Client zaio;
        private final Api.AnyClient zaip;
        private final zaab zaiq;
        private final Set<zak> zair;
        private final Map<ListenerHolder.ListenerKey<?>, zabw> zais;
        private final int zait;
        private final zace zaiu;
        private boolean zaiv;
        private final List<zab> zaiw;
        private ConnectionResult zaix = null;

        @WorkerThread
        public zaa(GoogleApiManager googleApiManager, GoogleApi<O> googleApi) {
            Queue<zab> queue;
            Set<zak> set;
            Map<ListenerHolder.ListenerKey<?>, zabw> map;
            List<zab> list;
            zaab zaab;
            GoogleApiManager googleApiManager2 = googleApiManager;
            GoogleApi<O> googleApi2 = googleApi;
            this.zaim = googleApiManager2;
            new LinkedList();
            this.zain = queue;
            new HashSet();
            this.zair = set;
            new HashMap();
            this.zais = map;
            new ArrayList();
            this.zaiw = list;
            this.zaio = googleApi2.zaa(googleApiManager2.handler.getLooper(), (zaa<O>) this);
            if (this.zaio instanceof SimpleClientAdapter) {
                this.zaip = ((SimpleClientAdapter) this.zaio).getClient();
            } else {
                this.zaip = this.zaio;
            }
            this.zafq = googleApi2.zak();
            new zaab();
            this.zaiq = zaab;
            this.zait = googleApi2.getInstanceId();
            if (this.zaio.requiresSignIn()) {
                this.zaiu = googleApi2.zaa(googleApiManager2.zaid, googleApiManager2.handler);
                return;
            }
            this.zaiu = null;
        }

        public final void onConnected(@Nullable Bundle bundle) {
            Runnable runnable;
            Bundle bundle2 = bundle;
            if (Looper.myLooper() == this.zaim.handler.getLooper()) {
                zabg();
                return;
            }
            new zabj(this);
            boolean post = this.zaim.handler.post(runnable);
        }

        /* access modifiers changed from: private */
        @WorkerThread
        public final void zabg() {
            TaskCompletionSource taskCompletionSource;
            zabl();
            zai(ConnectionResult.RESULT_SUCCESS);
            zabn();
            Iterator<zabw> it = this.zais.values().iterator();
            while (it.hasNext()) {
                zabw next = it.next();
                if (zaa(next.zajx.getRequiredFeatures()) != null) {
                    it.remove();
                } else {
                    try {
                        new TaskCompletionSource();
                        next.zajx.registerListener(this.zaip, taskCompletionSource);
                    } catch (DeadObjectException e) {
                        onConnectionSuspended(1);
                        this.zaio.disconnect();
                    } catch (RemoteException e2) {
                        it.remove();
                    }
                }
            }
            zabi();
            zabo();
        }

        public final void onConnectionSuspended(int i) {
            Runnable runnable;
            int i2 = i;
            if (Looper.myLooper() == this.zaim.handler.getLooper()) {
                zabh();
                return;
            }
            new zabk(this);
            boolean post = this.zaim.handler.post(runnable);
        }

        /* access modifiers changed from: private */
        @WorkerThread
        public final void zabh() {
            zabl();
            this.zaiv = true;
            this.zaiq.zaai();
            boolean sendMessageDelayed = this.zaim.handler.sendMessageDelayed(Message.obtain(this.zaim.handler, 9, this.zafq), this.zaim.zahz);
            boolean sendMessageDelayed2 = this.zaim.handler.sendMessageDelayed(Message.obtain(this.zaim.handler, 11, this.zafq), this.zaim.zaia);
            this.zaim.zaif.flush();
        }

        @WorkerThread
        public final void zag(@NonNull ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(this.zaim.handler);
            this.zaio.disconnect();
            onConnectionFailed(connectionResult);
        }

        /* JADX INFO: finally extract failed */
        @WorkerThread
        private final boolean zah(@NonNull ConnectionResult connectionResult) {
            ConnectionResult connectionResult2 = connectionResult;
            Object zabe = GoogleApiManager.lock;
            Object obj = zabe;
            synchronized (zabe) {
                try {
                    if (this.zaim.zaij == null || !this.zaim.zaik.contains(this.zafq)) {
                        return false;
                    }
                    this.zaim.zaij.zab(connectionResult2, this.zait);
                    return true;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj2 = obj;
                    throw th2;
                }
            }
        }

        public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
            Runnable runnable;
            ConnectionResult connectionResult2 = connectionResult;
            Api<?> api2 = api;
            boolean z2 = z;
            if (Looper.myLooper() == this.zaim.handler.getLooper()) {
                onConnectionFailed(connectionResult2);
                return;
            }
            new zabl(this, connectionResult2);
            boolean post = this.zaim.handler.post(runnable);
        }

        @WorkerThread
        public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Status status;
            StringBuilder sb;
            ConnectionResult connectionResult2 = connectionResult;
            Preconditions.checkHandlerThread(this.zaim.handler);
            if (this.zaiu != null) {
                this.zaiu.zabs();
            }
            zabl();
            this.zaim.zaif.flush();
            zai(connectionResult2);
            if (connectionResult2.getErrorCode() == 4) {
                zac(GoogleApiManager.zahy);
            } else if (this.zain.isEmpty()) {
                this.zaix = connectionResult2;
            } else if (!zah(connectionResult2) && !this.zaim.zac(connectionResult2, this.zait)) {
                if (connectionResult2.getErrorCode() == 18) {
                    this.zaiv = true;
                }
                if (this.zaiv) {
                    boolean sendMessageDelayed = this.zaim.handler.sendMessageDelayed(Message.obtain(this.zaim.handler, 9, this.zafq), this.zaim.zahz);
                    return;
                }
                Status status2 = status;
                String zan = this.zafq.zan();
                new StringBuilder(38 + String.valueOf(zan).length());
                new Status(17, sb.append("API: ").append(zan).append(" is not available on this device.").toString());
                zac(status2);
            }
        }

        @WorkerThread
        private final void zabi() {
            ArrayList arrayList;
            new ArrayList(this.zain);
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = arrayList2;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                i++;
                zab zab = (zab) arrayList3.get(i);
                if (!this.zaio.isConnected()) {
                    return;
                }
                if (zab(zab)) {
                    boolean remove = this.zain.remove(zab);
                }
            }
        }

        @WorkerThread
        public final void zaa(zab zab) {
            zab zab2 = zab;
            Preconditions.checkHandlerThread(this.zaim.handler);
            if (!this.zaio.isConnected()) {
                boolean add = this.zain.add(zab2);
                if (this.zaix == null || !this.zaix.hasResolution()) {
                    connect();
                } else {
                    onConnectionFailed(this.zaix);
                }
            } else if (zab(zab2)) {
                zabo();
            } else {
                boolean add2 = this.zain.add(zab2);
            }
        }

        @WorkerThread
        public final void zabj() {
            ConnectionResult connectionResult;
            BaseGmsClient.SignOutCallbacks signOutCallbacks;
            zab zab;
            TaskCompletionSource taskCompletionSource;
            Preconditions.checkHandlerThread(this.zaim.handler);
            zac(GoogleApiManager.zahx);
            this.zaiq.zaah();
            ListenerHolder.ListenerKey[] listenerKeyArr = (ListenerHolder.ListenerKey[]) this.zais.keySet().toArray(new ListenerHolder.ListenerKey[this.zais.size()]);
            ListenerHolder.ListenerKey[] listenerKeyArr2 = listenerKeyArr;
            int length = listenerKeyArr.length;
            for (int i = 0; i < length; i++) {
                ListenerHolder.ListenerKey listenerKey = listenerKeyArr2[i];
                new TaskCompletionSource();
                new zah(listenerKey, taskCompletionSource);
                zaa(zab);
            }
            new ConnectionResult(4);
            zai(connectionResult);
            if (this.zaio.isConnected()) {
                new zabm(this);
                this.zaio.onUserSignOut(signOutCallbacks);
            }
        }

        public final Api.Client zaab() {
            return this.zaio;
        }

        public final Map<ListenerHolder.ListenerKey<?>, zabw> zabk() {
            return this.zais;
        }

        @WorkerThread
        public final void zabl() {
            Preconditions.checkHandlerThread(this.zaim.handler);
            this.zaix = null;
        }

        @WorkerThread
        public final ConnectionResult zabm() {
            Preconditions.checkHandlerThread(this.zaim.handler);
            return this.zaix;
        }

        @WorkerThread
        private final boolean zab(zab zab) {
            RuntimeException runtimeException;
            Object obj;
            ConnectionResult connectionResult;
            zab zab2 = zab;
            if (!(zab2 instanceof zac)) {
                zac(zab2);
                return true;
            }
            zac zac = (zac) zab2;
            Feature zaa = zaa(zac.zab(this));
            Feature feature = zaa;
            if (zaa == null) {
                zac(zab2);
                return true;
            }
            if (zac.zac(this)) {
                new zab(this.zafq, feature, (zabi) null);
                Object obj2 = obj;
                int indexOf = this.zaiw.indexOf(obj2);
                int i = indexOf;
                if (indexOf >= 0) {
                    zab zab3 = this.zaiw.get(i);
                    this.zaim.handler.removeMessages(15, zab3);
                    boolean sendMessageDelayed = this.zaim.handler.sendMessageDelayed(Message.obtain(this.zaim.handler, 15, zab3), this.zaim.zahz);
                } else {
                    boolean add = this.zaiw.add(obj2);
                    boolean sendMessageDelayed2 = this.zaim.handler.sendMessageDelayed(Message.obtain(this.zaim.handler, 15, obj2), this.zaim.zahz);
                    boolean sendMessageDelayed3 = this.zaim.handler.sendMessageDelayed(Message.obtain(this.zaim.handler, 16, obj2), this.zaim.zaia);
                    new ConnectionResult(2, (PendingIntent) null);
                    ConnectionResult connectionResult2 = connectionResult;
                    if (!zah(connectionResult2)) {
                        boolean zac2 = this.zaim.zac(connectionResult2, this.zait);
                    }
                }
            } else {
                new UnsupportedApiCallException(feature);
                zac.zaa(runtimeException);
            }
            return false;
        }

        @WorkerThread
        private final void zac(zab zab) {
            zab zab2 = zab;
            zab2.zaa(this.zaiq, requiresSignIn());
            try {
                zab2.zaa((zaa<?>) this);
            } catch (DeadObjectException e) {
                onConnectionSuspended(1);
                this.zaio.disconnect();
            }
        }

        @WorkerThread
        public final void zac(Status status) {
            Status status2 = status;
            Preconditions.checkHandlerThread(this.zaim.handler);
            for (zab zaa : this.zain) {
                zaa.zaa(status2);
            }
            this.zain.clear();
        }

        @WorkerThread
        public final void resume() {
            Preconditions.checkHandlerThread(this.zaim.handler);
            if (this.zaiv) {
                connect();
            }
        }

        @WorkerThread
        private final void zabn() {
            if (this.zaiv) {
                this.zaim.handler.removeMessages(11, this.zafq);
                this.zaim.handler.removeMessages(9, this.zafq);
                this.zaiv = false;
            }
        }

        @WorkerThread
        public final void zaav() {
            Status status;
            Status status2;
            Status status3;
            Preconditions.checkHandlerThread(this.zaim.handler);
            if (this.zaiv) {
                zabn();
                if (this.zaim.zaie.isGooglePlayServicesAvailable(this.zaim.zaid) == 18) {
                    new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
                    status2 = status3;
                } else {
                    new Status(8, "API failed to connect while resuming due to an unknown error.");
                    status2 = status;
                }
                zac(status2);
                this.zaio.disconnect();
            }
        }

        private final void zabo() {
            this.zaim.handler.removeMessages(12, this.zafq);
            boolean sendMessageDelayed = this.zaim.handler.sendMessageDelayed(this.zaim.handler.obtainMessage(12, this.zafq), this.zaim.zaib);
        }

        @WorkerThread
        public final boolean zabp() {
            return zac(true);
        }

        @WorkerThread
        private final boolean zac(boolean z) {
            boolean z2 = z;
            Preconditions.checkHandlerThread(this.zaim.handler);
            if (!this.zaio.isConnected() || this.zais.size() != 0) {
                return false;
            }
            if (this.zaiq.zaag()) {
                if (z2) {
                    zabo();
                }
                return false;
            }
            this.zaio.disconnect();
            return true;
        }

        /* JADX WARNING: type inference failed for: r4v6, types: [com.google.android.gms.common.api.internal.zach] */
        /* JADX WARNING: Multi-variable type inference failed */
        @android.support.annotation.WorkerThread
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void connect() {
            /*
                r9 = this;
                r0 = r9
                r3 = r0
                com.google.android.gms.common.api.internal.GoogleApiManager r3 = r3.zaim
                android.os.Handler r3 = r3.handler
                com.google.android.gms.common.internal.Preconditions.checkHandlerThread(r3)
                r3 = r0
                com.google.android.gms.common.api.Api$Client r3 = r3.zaio
                boolean r3 = r3.isConnected()
                if (r3 != 0) goto L_0x001d
                r3 = r0
                com.google.android.gms.common.api.Api$Client r3 = r3.zaio
                boolean r3 = r3.isConnecting()
                if (r3 == 0) goto L_0x001e
            L_0x001d:
                return
            L_0x001e:
                r3 = r0
                com.google.android.gms.common.api.internal.GoogleApiManager r3 = r3.zaim
                com.google.android.gms.common.internal.GoogleApiAvailabilityCache r3 = r3.zaif
                r4 = r0
                com.google.android.gms.common.api.internal.GoogleApiManager r4 = r4.zaim
                android.content.Context r4 = r4.zaid
                r5 = r0
                com.google.android.gms.common.api.Api$Client r5 = r5.zaio
                int r3 = r3.getClientAvailability(r4, r5)
                r8 = r3
                r3 = r8
                r4 = r8
                r1 = r4
                if (r3 == 0) goto L_0x004a
                com.google.android.gms.common.ConnectionResult r3 = new com.google.android.gms.common.ConnectionResult
                r8 = r3
                r3 = r8
                r4 = r8
                r5 = r1
                r6 = 0
                r4.<init>(r5, r6)
                r2 = r3
                r3 = r0
                r4 = r2
                r3.onConnectionFailed(r4)
                goto L_0x001d
            L_0x004a:
                com.google.android.gms.common.api.internal.GoogleApiManager$zac r3 = new com.google.android.gms.common.api.internal.GoogleApiManager$zac
                r8 = r3
                r3 = r8
                r4 = r8
                r5 = r0
                com.google.android.gms.common.api.internal.GoogleApiManager r5 = r5.zaim
                r6 = r0
                com.google.android.gms.common.api.Api$Client r6 = r6.zaio
                r7 = r0
                com.google.android.gms.common.api.internal.zai<O> r7 = r7.zafq
                r4.<init>(r5, r6, r7)
                r2 = r3
                r3 = r0
                com.google.android.gms.common.api.Api$Client r3 = r3.zaio
                boolean r3 = r3.requiresSignIn()
                if (r3 == 0) goto L_0x006c
                r3 = r0
                com.google.android.gms.common.api.internal.zace r3 = r3.zaiu
                r4 = r2
                r3.zaa((com.google.android.gms.common.api.internal.zach) r4)
            L_0x006c:
                r3 = r0
                com.google.android.gms.common.api.Api$Client r3 = r3.zaio
                r4 = r2
                r3.connect(r4)
                goto L_0x001d
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.GoogleApiManager.zaa.connect():void");
        }

        @WorkerThread
        public final void zaa(zak zak) {
            Preconditions.checkHandlerThread(this.zaim.handler);
            boolean add = this.zair.add(zak);
        }

        @WorkerThread
        private final void zai(ConnectionResult connectionResult) {
            ConnectionResult connectionResult2 = connectionResult;
            for (zak next : this.zair) {
                String str = null;
                if (Objects.equal(connectionResult2, ConnectionResult.RESULT_SUCCESS)) {
                    str = this.zaio.getEndpointPackageName();
                }
                next.zaa(this.zafq, connectionResult2, str);
            }
            this.zair.clear();
        }

        /* access modifiers changed from: package-private */
        public final boolean isConnected() {
            return this.zaio.isConnected();
        }

        public final boolean requiresSignIn() {
            return this.zaio.requiresSignIn();
        }

        public final int getInstanceId() {
            return this.zait;
        }

        /* access modifiers changed from: package-private */
        public final zad zabq() {
            if (this.zaiu == null) {
                return null;
            }
            return this.zaiu.zabq();
        }

        @Nullable
        @WorkerThread
        private final Feature zaa(@Nullable Feature[] featureArr) {
            Map map;
            Feature[] featureArr2 = featureArr;
            if (featureArr2 == null || featureArr2.length == 0) {
                return null;
            }
            Feature[] availableFeatures = this.zaio.getAvailableFeatures();
            Feature[] featureArr3 = availableFeatures;
            if (availableFeatures == null) {
                featureArr3 = new Feature[0];
            }
            new C1642ArrayMap(featureArr3.length);
            Map map2 = map;
            Feature[] featureArr4 = featureArr3;
            Feature[] featureArr5 = featureArr4;
            int length = featureArr4.length;
            for (int i = 0; i < length; i++) {
                Feature feature = featureArr5[i];
                Object put = map2.put(feature.getName(), Long.valueOf(feature.getVersion()));
            }
            Feature[] featureArr6 = featureArr2;
            Feature[] featureArr7 = featureArr6;
            int length2 = featureArr6.length;
            for (int i2 = 0; i2 < length2; i2++) {
                Feature feature2 = featureArr7[i2];
                if (!map2.containsKey(feature2.getName()) || ((Long) map2.get(feature2.getName())).longValue() < feature2.getVersion()) {
                    return feature2;
                }
            }
            return null;
        }

        /* access modifiers changed from: private */
        @WorkerThread
        public final void zaa(zab zab) {
            if (!this.zaiw.contains(zab) || this.zaiv) {
                return;
            }
            if (!this.zaio.isConnected()) {
                connect();
            } else {
                zabi();
            }
        }

        /* access modifiers changed from: private */
        @WorkerThread
        public final void zab(zab zab) {
            ArrayList arrayList;
            RuntimeException runtimeException;
            zab zab2 = zab;
            if (this.zaiw.remove(zab2)) {
                this.zaim.handler.removeMessages(15, zab2);
                this.zaim.handler.removeMessages(16, zab2);
                Feature zad = zab2.zajc;
                new ArrayList(this.zain.size());
                ArrayList arrayList2 = arrayList;
                for (zab zab3 : this.zain) {
                    zab zab4 = zab3;
                    if (zab3 instanceof zac) {
                        Feature[] zab5 = ((zac) zab4).zab(this);
                        Feature[] featureArr = zab5;
                        if (zab5 != null && ArrayUtils.contains((T[]) featureArr, zad)) {
                            boolean add = arrayList2.add(zab4);
                        }
                    }
                }
                ArrayList arrayList3 = arrayList2;
                ArrayList arrayList4 = arrayList3;
                int size = arrayList3.size();
                int i = 0;
                while (i < size) {
                    i++;
                    zab zab6 = (zab) arrayList4.get(i);
                    boolean remove = this.zain.remove(zab6);
                    new UnsupportedApiCallException(zad);
                    zab6.zaa(runtimeException);
                }
            }
        }

        static /* synthetic */ boolean zaa(zaa zaa, boolean z) {
            boolean z2 = z;
            return zaa.zac(false);
        }
    }

    @KeepForSdk
    private GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        AtomicInteger atomicInteger;
        AtomicInteger atomicInteger2;
        Map<zai<?>, zaa<?>> map;
        Set<zai<?>> set;
        Set<zai<?>> set2;
        Handler handler2;
        GoogleApiAvailabilityCache googleApiAvailabilityCache;
        GoogleApiAvailability googleApiAvailability2 = googleApiAvailability;
        new AtomicInteger(1);
        this.zaig = atomicInteger;
        new AtomicInteger(0);
        this.zaih = atomicInteger2;
        new ConcurrentHashMap(5, 0.75f, 1);
        this.zaii = map;
        this.zaij = null;
        new C1643ArraySet();
        this.zaik = set;
        new C1643ArraySet();
        this.zail = set2;
        this.zaid = context;
        new zap(looper, this);
        this.handler = handler2;
        this.zaie = googleApiAvailability2;
        new GoogleApiAvailabilityCache(googleApiAvailability2);
        this.zaif = googleApiAvailabilityCache;
        boolean sendMessage = this.handler.sendMessage(this.handler.obtainMessage(6));
    }

    public final int zabd() {
        return this.zaig.getAndIncrement();
    }

    public final void zaa(GoogleApi<?> googleApi) {
        boolean sendMessage = this.handler.sendMessage(this.handler.obtainMessage(7, googleApi));
    }

    @WorkerThread
    private final void zab(GoogleApi<?> googleApi) {
        zaa zaa2;
        GoogleApi<?> googleApi2 = googleApi;
        zai<?> zak = googleApi2.zak();
        zaa zaa3 = this.zaii.get(zak);
        zaa zaa4 = zaa3;
        if (zaa3 == null) {
            new zaa(this, googleApi2);
            zaa4 = zaa2;
            zaa<?> put = this.zaii.put(zak, zaa4);
        }
        if (zaa4.requiresSignIn()) {
            boolean add = this.zail.add(zak);
        }
        zaa4.connect();
    }

    /* JADX INFO: finally extract failed */
    public final void zaa(@NonNull zaae zaae) {
        zaae zaae2 = zaae;
        Object obj = lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.zaij != zaae2) {
                    this.zaij = zaae2;
                    this.zaik.clear();
                }
                boolean addAll = this.zaik.addAll(zaae2.zaaj());
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zab(@NonNull zaae zaae) {
        zaae zaae2 = zaae;
        Object obj = lock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.zaij == zaae2) {
                    this.zaij = null;
                    this.zaik.clear();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public final Task<Map<zai<?>, String>> zaa(Iterable<? extends GoogleApi<?>> iterable) {
        zak zak;
        new zak(iterable);
        zak zak2 = zak;
        boolean sendMessage = this.handler.sendMessage(this.handler.obtainMessage(2, zak2));
        return zak2.getTask();
    }

    public final void zao() {
        boolean sendMessage = this.handler.sendMessage(this.handler.obtainMessage(3));
    }

    /* access modifiers changed from: package-private */
    public final void maybeSignOut() {
        int incrementAndGet = this.zaih.incrementAndGet();
        boolean sendMessage = this.handler.sendMessage(this.handler.obtainMessage(10));
    }

    public final Task<Boolean> zac(GoogleApi<?> googleApi) {
        zaaf zaaf;
        new zaaf(googleApi.zak());
        zaaf zaaf2 = zaaf;
        boolean sendMessage = this.handler.sendMessage(this.handler.obtainMessage(14, zaaf2));
        return zaaf2.zaal().getTask();
    }

    public final <O extends Api.ApiOptions> void zaa(GoogleApi<O> googleApi, int i, BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient> apiMethodImpl) {
        zab zab2;
        Object obj;
        new zae(i, apiMethodImpl);
        Handler handler2 = this.handler;
        new zabv(zab2, this.zaih.get(), googleApi);
        boolean sendMessage = handler2.sendMessage(this.handler.obtainMessage(4, obj));
    }

    public final <O extends Api.ApiOptions, ResultT> void zaa(GoogleApi<O> googleApi, int i, TaskApiCall<Api.AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        zab zab2;
        Object obj;
        new zag(i, taskApiCall, taskCompletionSource, statusExceptionMapper);
        Handler handler2 = this.handler;
        new zabv(zab2, this.zaih.get(), googleApi);
        boolean sendMessage = handler2.sendMessage(this.handler.obtainMessage(4, obj));
    }

    public final <O extends Api.ApiOptions> Task<Void> zaa(@NonNull GoogleApi<O> googleApi, @NonNull RegisterListenerMethod<Api.AnyClient, ?> registerListenerMethod, @NonNull UnregisterListenerMethod<Api.AnyClient, ?> unregisterListenerMethod) {
        TaskCompletionSource taskCompletionSource;
        zab zab2;
        zabw zabw;
        Object obj;
        new TaskCompletionSource();
        TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
        new zabw(registerListenerMethod, unregisterListenerMethod);
        new zaf(zabw, taskCompletionSource2);
        Handler handler2 = this.handler;
        new zabv(zab2, this.zaih.get(), googleApi);
        boolean sendMessage = handler2.sendMessage(this.handler.obtainMessage(8, obj));
        return taskCompletionSource2.getTask();
    }

    public final <O extends Api.ApiOptions> Task<Boolean> zaa(@NonNull GoogleApi<O> googleApi, @NonNull ListenerHolder.ListenerKey<?> listenerKey) {
        TaskCompletionSource taskCompletionSource;
        zab zab2;
        Object obj;
        new TaskCompletionSource();
        TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
        new zah(listenerKey, taskCompletionSource2);
        Handler handler2 = this.handler;
        new zabv(zab2, this.zaih.get(), googleApi);
        boolean sendMessage = handler2.sendMessage(this.handler.obtainMessage(13, obj));
        return taskCompletionSource2.getTask();
    }

    @WorkerThread
    public boolean handleMessage(Message message) {
        BackgroundDetector.BackgroundStateChangeListener backgroundStateChangeListener;
        StringBuilder sb;
        Throwable th;
        Status status;
        StringBuilder sb2;
        ConnectionResult connectionResult;
        long j;
        StringBuilder sb3;
        Message message2 = message;
        switch (message2.what) {
            case 1:
                if (((Boolean) message2.obj).booleanValue()) {
                    j = 10000;
                } else {
                    j = 300000;
                }
                this.zaib = j;
                this.handler.removeMessages(12);
                for (zai obtainMessage : this.zaii.keySet()) {
                    boolean sendMessageDelayed = this.handler.sendMessageDelayed(this.handler.obtainMessage(12, obtainMessage), this.zaib);
                }
                break;
            case 2:
                zak zak = (zak) message2.obj;
                Iterator<zai<?>> it = zak.zap().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else {
                        zai next = it.next();
                        zaa zaa2 = this.zaii.get(next);
                        zaa zaa3 = zaa2;
                        if (zaa2 == null) {
                            new ConnectionResult(13);
                            zak.zaa(next, connectionResult, (String) null);
                            break;
                        } else if (zaa3.isConnected()) {
                            zak.zaa(next, ConnectionResult.RESULT_SUCCESS, zaa3.zaab().getEndpointPackageName());
                        } else if (zaa3.zabm() != null) {
                            zak.zaa(next, zaa3.zabm(), (String) null);
                        } else {
                            zaa3.zaa(zak);
                            zaa3.connect();
                        }
                    }
                }
            case 3:
                for (zaa next2 : this.zaii.values()) {
                    next2.zabl();
                    next2.connect();
                }
                break;
            case 4:
            case 8:
            case 13:
                zabv zabv = (zabv) message2.obj;
                zaa zaa4 = this.zaii.get(zabv.zajt.zak());
                zaa zaa5 = zaa4;
                if (zaa4 == null) {
                    zab(zabv.zajt);
                    zaa5 = this.zaii.get(zabv.zajt.zak());
                }
                if (zaa5.requiresSignIn() && this.zaih.get() != zabv.zajs) {
                    zabv.zajr.zaa(zahx);
                    zaa5.zabj();
                    break;
                } else {
                    zaa5.zaa(zabv.zajr);
                    break;
                }
            case 5:
                int i = message2.arg1;
                ConnectionResult connectionResult2 = (ConnectionResult) message2.obj;
                int i2 = i;
                zaa zaa6 = null;
                Iterator<zaa<?>> it2 = this.zaii.values().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        zaa next3 = it2.next();
                        zaa zaa7 = next3;
                        if (next3.getInstanceId() == i2) {
                            zaa6 = zaa7;
                        }
                    }
                }
                if (zaa6 == null) {
                    new StringBuilder(76);
                    new Exception();
                    int wtf = Log.wtf("GoogleApiManager", sb.append("Could not find API instance ").append(i2).append(" while trying to fail enqueued calls.").toString(), th);
                    break;
                } else {
                    String errorString = this.zaie.getErrorString(connectionResult2.getErrorCode());
                    String errorMessage = connectionResult2.getErrorMessage();
                    new StringBuilder(69 + String.valueOf(errorString).length() + String.valueOf(errorMessage).length());
                    new Status(17, sb2.append("Error resolution was canceled by the user, original error message: ").append(errorString).append(": ").append(errorMessage).toString());
                    zaa6.zac(status);
                    break;
                }
            case 6:
                if (PlatformVersion.isAtLeastIceCreamSandwich() && (this.zaid.getApplicationContext() instanceof Application)) {
                    BackgroundDetector.initialize((Application) this.zaid.getApplicationContext());
                    new zabi(this);
                    BackgroundDetector.getInstance().addListener(backgroundStateChangeListener);
                    if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                        this.zaib = 300000;
                        break;
                    }
                }
                break;
            case 7:
                zab((GoogleApi<?>) (GoogleApi) message2.obj);
                break;
            case 9:
                if (this.zaii.containsKey(message2.obj)) {
                    this.zaii.get(message2.obj).resume();
                    break;
                }
                break;
            case 10:
                for (zai remove : this.zail) {
                    this.zaii.remove(remove).zabj();
                }
                this.zail.clear();
                break;
            case 11:
                if (this.zaii.containsKey(message2.obj)) {
                    this.zaii.get(message2.obj).zaav();
                    break;
                }
                break;
            case 12:
                if (this.zaii.containsKey(message2.obj)) {
                    boolean zabp = this.zaii.get(message2.obj).zabp();
                    break;
                }
                break;
            case 14:
                zaaf zaaf = (zaaf) message2.obj;
                zai<?> zak2 = zaaf.zak();
                if (this.zaii.containsKey(zak2)) {
                    zaaf.zaal().setResult(Boolean.valueOf(zaa.zaa((zaa) this.zaii.get(zak2), false)));
                    break;
                } else {
                    zaaf.zaal().setResult(false);
                    break;
                }
            case 15:
                zab zab2 = (zab) message2.obj;
                if (this.zaii.containsKey(zab2.zajb)) {
                    this.zaii.get(zab2.zajb).zaa(zab2);
                    break;
                }
                break;
            case 16:
                zab zab3 = (zab) message2.obj;
                if (this.zaii.containsKey(zab3.zajb)) {
                    this.zaii.get(zab3.zajb).zab(zab3);
                    break;
                }
                break;
            default:
                int i3 = message2.what;
                new StringBuilder(31);
                int w = Log.w("GoogleApiManager", sb3.append("Unknown message id: ").append(i3).toString());
                return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final PendingIntent zaa(zai<?> zai, int i) {
        int i2 = i;
        zaa zaa2 = this.zaii.get(zai);
        zaa zaa3 = zaa2;
        if (zaa2 == null) {
            return null;
        }
        zad zabq = zaa3.zabq();
        zad zad = zabq;
        if (zabq == null) {
            return null;
        }
        return PendingIntent.getActivity(this.zaid, i2, zad.getSignInIntent(), Declaration.PACKAGE_ACCESS);
    }

    /* access modifiers changed from: package-private */
    public final boolean zac(ConnectionResult connectionResult, int i) {
        return this.zaie.zaa(this.zaid, connectionResult, i);
    }

    public final void zaa(ConnectionResult connectionResult, int i) {
        ConnectionResult connectionResult2 = connectionResult;
        int i2 = i;
        if (!zac(connectionResult2, i2)) {
            boolean sendMessage = this.handler.sendMessage(this.handler.obtainMessage(5, i2, 0, connectionResult2));
        }
    }

    static {
        Status status;
        Status status2;
        Object obj;
        new Status(4, "Sign-out occurred while this API call was in progress.");
        zahx = status;
        new Status(4, "The user must be signed in to make this API call.");
        zahy = status2;
        new Object();
        lock = obj;
    }
}
