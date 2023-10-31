package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.base.zap;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class GmsClientEventManager implements Handler.Callback {
    private final Handler mHandler;
    private final Object mLock;
    private final GmsClientEventState zaol;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zaom;
    @VisibleForTesting
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zaon;
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zaoo;
    private volatile boolean zaop = false;
    private final AtomicInteger zaoq;
    private boolean zaor;

    @VisibleForTesting
    public interface GmsClientEventState {
        Bundle getConnectionHint();

        boolean isConnected();
    }

    public GmsClientEventManager(Looper looper, GmsClientEventState gmsClientEventState) {
        ArrayList<GoogleApiClient.ConnectionCallbacks> arrayList;
        ArrayList<GoogleApiClient.ConnectionCallbacks> arrayList2;
        ArrayList<GoogleApiClient.OnConnectionFailedListener> arrayList3;
        AtomicInteger atomicInteger;
        Object obj;
        Handler handler;
        new ArrayList<>();
        this.zaom = arrayList;
        new ArrayList<>();
        this.zaon = arrayList2;
        new ArrayList<>();
        this.zaoo = arrayList3;
        new AtomicInteger(0);
        this.zaoq = atomicInteger;
        this.zaor = false;
        new Object();
        this.mLock = obj;
        this.zaol = gmsClientEventState;
        new zap(looper, this);
        this.mHandler = handler;
    }

    public final void disableCallbacks() {
        this.zaop = false;
        int incrementAndGet = this.zaoq.incrementAndGet();
    }

    public final void enableCallbacks() {
        this.zaop = true;
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final void onConnectionSuccess() {
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                onConnectionSuccess(this.zaol.getConnectionHint());
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    @VisibleForTesting
    public final void onConnectionSuccess(Bundle bundle) {
        ArrayList arrayList;
        Bundle bundle2 = bundle;
        Preconditions.checkHandlerThread(this.mHandler, "onConnectionSuccess must only be called on the Handler thread");
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                Preconditions.checkState(!this.zaor);
                this.mHandler.removeMessages(1);
                this.zaor = true;
                Preconditions.checkState(this.zaon.size() == 0);
                new ArrayList(this.zaom);
                int i = this.zaoq.get();
                ArrayList arrayList2 = arrayList;
                ArrayList arrayList3 = arrayList2;
                int size = arrayList2.size();
                int i2 = 0;
                while (i2 < size) {
                    i2++;
                    GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) arrayList3.get(i2);
                    if (!this.zaop || !this.zaol.isConnected() || this.zaoq.get() != i) {
                        break;
                    } else if (!this.zaon.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnected(bundle2);
                    }
                }
                this.zaon.clear();
                this.zaor = false;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    @VisibleForTesting
    public final void onUnintentionalDisconnection(int i) {
        ArrayList arrayList;
        int i2 = i;
        Preconditions.checkHandlerThread(this.mHandler, "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                this.zaor = true;
                new ArrayList(this.zaom);
                int i3 = this.zaoq.get();
                ArrayList arrayList2 = arrayList;
                ArrayList arrayList3 = arrayList2;
                int size = arrayList2.size();
                int i4 = 0;
                while (i4 < size) {
                    i4++;
                    GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) arrayList3.get(i4);
                    if (!this.zaop || this.zaoq.get() != i3) {
                        break;
                    } else if (this.zaom.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnectionSuspended(i2);
                    }
                }
                this.zaon.clear();
                this.zaor = false;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    @VisibleForTesting
    public final void onConnectionFailure(ConnectionResult connectionResult) {
        ArrayList arrayList;
        ConnectionResult connectionResult2 = connectionResult;
        Preconditions.checkHandlerThread(this.mHandler, "onConnectionFailure must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                new ArrayList(this.zaoo);
                int i = this.zaoq.get();
                ArrayList arrayList2 = arrayList;
                ArrayList arrayList3 = arrayList2;
                int size = arrayList2.size();
                int i2 = 0;
                while (i2 < size) {
                    i2++;
                    GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener) arrayList3.get(i2);
                    if (!this.zaop || this.zaoq.get() != i) {
                        return;
                    } else if (this.zaoo.contains(onConnectionFailedListener)) {
                        onConnectionFailedListener.onConnectionFailed(connectionResult2);
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        StringBuilder sb;
        GoogleApiClient.ConnectionCallbacks connectionCallbacks2 = connectionCallbacks;
        Object checkNotNull = Preconditions.checkNotNull(connectionCallbacks2);
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.zaom.contains(connectionCallbacks2)) {
                    String valueOf = String.valueOf(connectionCallbacks2);
                    new StringBuilder(62 + String.valueOf(valueOf).length());
                    int w = Log.w("GmsClientEvents", sb.append("registerConnectionCallbacks(): listener ").append(valueOf).append(" is already registered").toString());
                } else {
                    boolean add = this.zaom.add(connectionCallbacks2);
                }
                if (this.zaol.isConnected()) {
                    boolean sendMessage = this.mHandler.sendMessage(this.mHandler.obtainMessage(1, connectionCallbacks2));
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public final boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        GoogleApiClient.ConnectionCallbacks connectionCallbacks2 = connectionCallbacks;
        Object checkNotNull = Preconditions.checkNotNull(connectionCallbacks2);
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                boolean contains = this.zaom.contains(connectionCallbacks2);
                return contains;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        StringBuilder sb;
        GoogleApiClient.ConnectionCallbacks connectionCallbacks2 = connectionCallbacks;
        Object checkNotNull = Preconditions.checkNotNull(connectionCallbacks2);
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (!this.zaom.remove(connectionCallbacks2)) {
                    String valueOf = String.valueOf(connectionCallbacks2);
                    new StringBuilder(52 + String.valueOf(valueOf).length());
                    int w = Log.w("GmsClientEvents", sb.append("unregisterConnectionCallbacks(): listener ").append(valueOf).append(" not found").toString());
                } else if (this.zaor) {
                    boolean add = this.zaon.add(connectionCallbacks2);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        StringBuilder sb;
        GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener2 = onConnectionFailedListener;
        Object checkNotNull = Preconditions.checkNotNull(onConnectionFailedListener2);
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.zaoo.contains(onConnectionFailedListener2)) {
                    String valueOf = String.valueOf(onConnectionFailedListener2);
                    new StringBuilder(67 + String.valueOf(valueOf).length());
                    int w = Log.w("GmsClientEvents", sb.append("registerConnectionFailedListener(): listener ").append(valueOf).append(" is already registered").toString());
                } else {
                    boolean add = this.zaoo.add(onConnectionFailedListener2);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    public final boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener2 = onConnectionFailedListener;
        Object checkNotNull = Preconditions.checkNotNull(onConnectionFailedListener2);
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                boolean contains = this.zaoo.contains(onConnectionFailedListener2);
                return contains;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        StringBuilder sb;
        GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener2 = onConnectionFailedListener;
        Object checkNotNull = Preconditions.checkNotNull(onConnectionFailedListener2);
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (!this.zaoo.remove(onConnectionFailedListener2)) {
                    String valueOf = String.valueOf(onConnectionFailedListener2);
                    new StringBuilder(57 + String.valueOf(valueOf).length());
                    int w = Log.w("GmsClientEvents", sb.append("unregisterConnectionFailedListener(): listener ").append(valueOf).append(" not found").toString());
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final boolean handleMessage(Message message) {
        StringBuilder sb;
        Throwable th;
        Message message2 = message;
        if (message2.what == 1) {
            GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) message2.obj;
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    if (this.zaop && this.zaol.isConnected() && this.zaom.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnected(this.zaol.getConnectionHint());
                    }
                    return true;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    Object obj3 = obj2;
                    throw th3;
                }
            }
        } else {
            int i = message2.what;
            new StringBuilder(45);
            new Exception();
            int wtf = Log.wtf("GmsClientEvents", sb.append("Don't know how to handle message: ").append(i).toString(), th);
            return false;
        }
    }

    public final boolean areCallbacksEnabled() {
        return this.zaop;
    }
}
