package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.IGmsCallbacks;
import com.google.android.gms.common.internal.IGmsServiceBroker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import gnu.kawa.functions.GetNamedPart;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public abstract class BaseGmsClient<T extends IInterface> {
    @KeepForSdk
    public static final int CONNECT_STATE_CONNECTED = 4;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    @KeepForSdk
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES;
    @KeepForSdk
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private static final Feature[] zzbt = new Feature[0];
    private final Context mContext;
    final Handler mHandler;
    private final Object mLock;
    private int zzbu;
    private long zzbv;
    private long zzbw;
    private int zzbx;
    private long zzby;
    @VisibleForTesting
    private zzh zzbz;
    private final Looper zzca;
    private final GmsClientSupervisor zzcb;
    private final GoogleApiAvailabilityLight zzcc;
    /* access modifiers changed from: private */
    public final Object zzcd;
    @GuardedBy("mServiceBrokerLock")
    private IGmsServiceBroker zzce;
    @VisibleForTesting
    protected ConnectionProgressReportCallbacks zzcf;
    @GuardedBy("mLock")
    private T zzcg;
    /* access modifiers changed from: private */
    public final ArrayList<zzc<?>> zzch;
    @GuardedBy("mLock")
    private zze zzci;
    @GuardedBy("mLock")
    private int zzcj;
    /* access modifiers changed from: private */
    public final BaseConnectionCallbacks zzck;
    /* access modifiers changed from: private */
    public final BaseOnConnectionFailedListener zzcl;
    private final int zzcm;
    private final String zzcn;
    /* access modifiers changed from: private */
    public ConnectionResult zzco;
    /* access modifiers changed from: private */
    public boolean zzcp;
    private volatile zzb zzcq;
    @VisibleForTesting
    protected AtomicInteger zzcr;

    @KeepForSdk
    public interface BaseConnectionCallbacks {
        @KeepForSdk
        void onConnected(@Nullable Bundle bundle);

        @KeepForSdk
        void onConnectionSuspended(int i);
    }

    @KeepForSdk
    public interface BaseOnConnectionFailedListener {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    @KeepForSdk
    public interface ConnectionProgressReportCallbacks {
        @KeepForSdk
        void onReportServiceBinding(@NonNull ConnectionResult connectionResult);
    }

    protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        private final /* synthetic */ BaseGmsClient zzct;

        @KeepForSdk
        public LegacyClientCallbackAdapter(BaseGmsClient baseGmsClient) {
            this.zzct = baseGmsClient;
        }

        public void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
            ConnectionResult connectionResult2 = connectionResult;
            if (connectionResult2.isSuccess()) {
                this.zzct.getRemoteService((IAccountAccessor) null, this.zzct.getScopes());
            } else if (this.zzct.zzcl != null) {
                this.zzct.zzcl.onConnectionFailed(connectionResult2);
            }
        }
    }

    @KeepForSdk
    public interface SignOutCallbacks {
        @KeepForSdk
        void onSignOutComplete();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BaseGmsClient(android.content.Context r17, android.os.Looper r18, int r19, com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks r20, com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener r21, java.lang.String r22) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            r4 = r20
            r5 = r21
            r6 = r22
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r1
            com.google.android.gms.common.internal.GmsClientSupervisor r10 = com.google.android.gms.common.internal.GmsClientSupervisor.getInstance(r10)
            com.google.android.gms.common.GoogleApiAvailabilityLight r11 = com.google.android.gms.common.GoogleApiAvailabilityLight.getInstance()
            r12 = r3
            r13 = r4
            java.lang.Object r13 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)
            com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks r13 = (com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks) r13
            r14 = r5
            java.lang.Object r14 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r14)
            com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener r14 = (com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener) r14
            r15 = r6
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.BaseGmsClient.<init>(android.content.Context, android.os.Looper, int, com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks, com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener, java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    @Nullable
    @KeepForSdk
    public abstract T createServiceInterface(IBinder iBinder);

    /* access modifiers changed from: protected */
    @KeepForSdk
    @NonNull
    public abstract String getServiceDescriptor();

    /* access modifiers changed from: protected */
    @KeepForSdk
    @NonNull
    public abstract String getStartServiceAction();

    final class zzb extends com.google.android.gms.internal.common.zze {
        private final /* synthetic */ BaseGmsClient zzct;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public zzb(BaseGmsClient baseGmsClient, Looper looper) {
            super(looper);
            this.zzct = baseGmsClient;
        }

        public final void handleMessage(Message message) {
            StringBuilder sb;
            Throwable th;
            ConnectionResult connectionResult;
            ConnectionResult connectionResult2;
            ConnectionResult connectionResult3;
            ConnectionResult connectionResult4;
            ConnectionResult connectionResult5;
            ConnectionResult connectionResult6;
            Message message2 = message;
            if (this.zzct.zzcr.get() != message2.arg1) {
                if (zzb(message2)) {
                    zza(message2);
                }
            } else if ((message2.what == 1 || message2.what == 7 || ((message2.what == 4 && !this.zzct.enableLocalFallback()) || message2.what == 5)) && !this.zzct.isConnecting()) {
                zza(message2);
            } else if (message2.what == 4) {
                new ConnectionResult(message2.arg2);
                ConnectionResult zza = BaseGmsClient.zza(this.zzct, connectionResult4);
                if (!this.zzct.zzl() || this.zzct.zzcp) {
                    if (this.zzct.zzco != null) {
                        connectionResult6 = this.zzct.zzco;
                    } else {
                        connectionResult6 = connectionResult5;
                        new ConnectionResult(8);
                    }
                    ConnectionResult connectionResult7 = connectionResult6;
                    this.zzct.zzcf.onReportServiceBinding(connectionResult7);
                    this.zzct.onConnectionFailed(connectionResult7);
                    return;
                }
                BaseGmsClient.zza(this.zzct, 3, (IInterface) null);
            } else if (message2.what == 5) {
                if (this.zzct.zzco != null) {
                    connectionResult3 = this.zzct.zzco;
                } else {
                    connectionResult3 = connectionResult2;
                    new ConnectionResult(8);
                }
                ConnectionResult connectionResult8 = connectionResult3;
                this.zzct.zzcf.onReportServiceBinding(connectionResult8);
                this.zzct.onConnectionFailed(connectionResult8);
            } else if (message2.what == 3) {
                new ConnectionResult(message2.arg2, message2.obj instanceof PendingIntent ? (PendingIntent) message2.obj : null);
                ConnectionResult connectionResult9 = connectionResult;
                this.zzct.zzcf.onReportServiceBinding(connectionResult9);
                this.zzct.onConnectionFailed(connectionResult9);
            } else if (message2.what == 6) {
                BaseGmsClient.zza(this.zzct, 5, (IInterface) null);
                if (this.zzct.zzck != null) {
                    this.zzct.zzck.onConnectionSuspended(message2.arg2);
                }
                this.zzct.onConnectionSuspended(message2.arg2);
                boolean zza2 = this.zzct.zza(5, 1, null);
            } else if (message2.what == 2 && !this.zzct.isConnected()) {
                zza(message2);
            } else if (zzb(message2)) {
                ((zzc) message2.obj).zzo();
            } else {
                int i = message2.what;
                new StringBuilder(45);
                new Exception();
                int wtf = Log.wtf("GmsClient", sb.append("Don't know how to handle message: ").append(i).toString(), th);
            }
        }

        private static void zza(Message message) {
            zzc zzc = (zzc) message.obj;
            zzc.zzn();
            zzc.unregister();
        }

        private static boolean zzb(Message message) {
            Message message2 = message;
            return message2.what == 2 || message2.what == 1 || message2.what == 7;
        }
    }

    @VisibleForTesting
    public final class zze implements ServiceConnection {
        private final /* synthetic */ BaseGmsClient zzct;
        private final int zzcx;

        public zze(BaseGmsClient baseGmsClient, int i) {
            this.zzct = baseGmsClient;
            this.zzcx = i;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IGmsServiceBroker iGmsServiceBroker;
            IGmsServiceBroker iGmsServiceBroker2;
            ComponentName componentName2 = componentName;
            IBinder iBinder2 = iBinder;
            if (iBinder2 == null) {
                BaseGmsClient.zza(this.zzct, 16);
                return;
            }
            Object zza = this.zzct.zzcd;
            Object obj = zza;
            synchronized (zza) {
                try {
                    BaseGmsClient baseGmsClient = this.zzct;
                    IBinder iBinder3 = iBinder2;
                    IBinder iBinder4 = iBinder3;
                    if (iBinder3 == null) {
                        iGmsServiceBroker2 = null;
                    } else {
                        IInterface queryLocalInterface = iBinder4.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                        IInterface iInterface = queryLocalInterface;
                        if (queryLocalInterface == null || !(iInterface instanceof IGmsServiceBroker)) {
                            iGmsServiceBroker2 = iGmsServiceBroker;
                            new IGmsServiceBroker.Stub.zza(iBinder4);
                        } else {
                            iGmsServiceBroker2 = (IGmsServiceBroker) iInterface;
                        }
                    }
                    IGmsServiceBroker zza2 = BaseGmsClient.zza(baseGmsClient, iGmsServiceBroker2);
                    this.zzct.zza(0, (Bundle) null, this.zzcx);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj2 = obj;
                    throw th2;
                }
            }
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            ComponentName componentName2 = componentName;
            Object zza = this.zzct.zzcd;
            Object obj = zza;
            synchronized (zza) {
                try {
                    IGmsServiceBroker zza2 = BaseGmsClient.zza(this.zzct, (IGmsServiceBroker) null);
                    boolean sendMessage = this.zzct.mHandler.sendMessage(this.zzct.mHandler.obtainMessage(6, this.zzcx, 1));
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        Object obj2 = obj;
                        throw th2;
                    }
                }
            }
        }
    }

    protected final class zzg extends zza {
        private final /* synthetic */ BaseGmsClient zzct;

        /* JADX WARNING: Illegal instructions before constructor call */
        @android.support.annotation.BinderThread
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public zzg(com.google.android.gms.common.internal.BaseGmsClient r9, @android.support.annotation.Nullable int r10, android.os.Bundle r11) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r2 = r10
                r3 = r11
                r4 = r0
                r5 = r1
                r4.zzct = r5
                r4 = r0
                r5 = r1
                r6 = r2
                r7 = 0
                r4.<init>(r5, r6, r7)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.BaseGmsClient.zzg.<init>(com.google.android.gms.common.internal.BaseGmsClient, int, android.os.Bundle):void");
        }

        /* access modifiers changed from: protected */
        public final void zza(ConnectionResult connectionResult) {
            ConnectionResult connectionResult2 = connectionResult;
            if (!this.zzct.enableLocalFallback() || !this.zzct.zzl()) {
                this.zzct.zzcf.onReportServiceBinding(connectionResult2);
                this.zzct.onConnectionFailed(connectionResult2);
                return;
            }
            BaseGmsClient.zza(this.zzct, 16);
        }

        /* access modifiers changed from: protected */
        public final boolean zzm() {
            this.zzct.zzcf.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return true;
        }
    }

    protected abstract class zzc<TListener> {
        private final /* synthetic */ BaseGmsClient zzct;
        private TListener zzcu;
        private boolean zzcv = false;

        public zzc(BaseGmsClient baseGmsClient, TListener tlistener) {
            this.zzct = baseGmsClient;
            this.zzcu = tlistener;
        }

        /* access modifiers changed from: protected */
        public abstract void zza(TListener tlistener);

        /* access modifiers changed from: protected */
        public abstract void zzn();

        /* JADX INFO: finally extract failed */
        public final void zzo() {
            StringBuilder sb;
            synchronized (this) {
                try {
                    TListener tlistener = this.zzcu;
                    if (this.zzcv) {
                        String valueOf = String.valueOf(this);
                        new StringBuilder(47 + String.valueOf(valueOf).length());
                        int w = Log.w("GmsClient", sb.append("Callback proxy ").append(valueOf).append(" being reused. This is not safe.").toString());
                    }
                    if (tlistener != null) {
                        try {
                            zza(tlistener);
                        } catch (RuntimeException e) {
                            RuntimeException runtimeException = e;
                            zzn();
                            throw runtimeException;
                        }
                    } else {
                        zzn();
                    }
                    synchronized (this) {
                        try {
                            this.zzcv = true;
                            unregister();
                        } catch (Throwable th) {
                            while (true) {
                                Throwable th2 = th;
                                throw th2;
                            }
                        }
                    }
                } catch (Throwable th3) {
                    while (true) {
                        Throwable th4 = th3;
                        throw th4;
                    }
                }
            }
        }

        public final void unregister() {
            removeListener();
            ArrayList zzf = this.zzct.zzch;
            ArrayList arrayList = zzf;
            synchronized (zzf) {
                try {
                    boolean remove = this.zzct.zzch.remove(this);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    ArrayList arrayList2 = arrayList;
                    throw th2;
                }
            }
        }

        public final void removeListener() {
            synchronized (this) {
                try {
                    this.zzcu = null;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    throw th2;
                }
            }
        }
    }

    @VisibleForTesting
    public static final class zzd extends IGmsCallbacks.zza {
        private BaseGmsClient zzcw;
        private final int zzcx;

        public zzd(@NonNull BaseGmsClient baseGmsClient, int i) {
            this.zzcw = baseGmsClient;
            this.zzcx = i;
        }

        @BinderThread
        public final void zza(int i, @Nullable Bundle bundle) {
            Throwable th;
            int i2 = i;
            Bundle bundle2 = bundle;
            new Exception();
            int wtf = Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", th);
        }

        @BinderThread
        public final void onPostInitComplete(int i, @NonNull IBinder iBinder, @Nullable Bundle bundle) {
            Object checkNotNull = Preconditions.checkNotNull(this.zzcw, "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzcw.onPostInitHandler(i, iBinder, bundle, this.zzcx);
            this.zzcw = null;
        }

        @BinderThread
        public final void zza(int i, @NonNull IBinder iBinder, @NonNull zzb zzb) {
            zzb zzb2 = zzb;
            Object checkNotNull = Preconditions.checkNotNull(this.zzcw, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Object checkNotNull2 = Preconditions.checkNotNull(zzb2);
            this.zzcw.zza(zzb2);
            onPostInitComplete(i, iBinder, zzb2.zzda);
        }
    }

    protected final class zzf extends zza {
        private final /* synthetic */ BaseGmsClient zzct;
        private final IBinder zzcy;

        /* JADX WARNING: Illegal instructions before constructor call */
        @android.support.annotation.BinderThread
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public zzf(com.google.android.gms.common.internal.BaseGmsClient r10, int r11, android.os.IBinder r12, android.os.Bundle r13) {
            /*
                r9 = this;
                r0 = r9
                r1 = r10
                r2 = r11
                r3 = r12
                r4 = r13
                r5 = r0
                r6 = r1
                r5.zzct = r6
                r5 = r0
                r6 = r1
                r7 = r2
                r8 = r4
                r5.<init>(r6, r7, r8)
                r5 = r0
                r6 = r3
                r5.zzcy = r6
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.BaseGmsClient.zzf.<init>(com.google.android.gms.common.internal.BaseGmsClient, int, android.os.IBinder, android.os.Bundle):void");
        }

        /* access modifiers changed from: protected */
        public final void zza(ConnectionResult connectionResult) {
            ConnectionResult connectionResult2 = connectionResult;
            if (this.zzct.zzcl != null) {
                this.zzct.zzcl.onConnectionFailed(connectionResult2);
            }
            this.zzct.onConnectionFailed(connectionResult2);
        }

        /* access modifiers changed from: protected */
        public final boolean zzm() {
            StringBuilder sb;
            try {
                String interfaceDescriptor = this.zzcy.getInterfaceDescriptor();
                if (!this.zzct.getServiceDescriptor().equals(interfaceDescriptor)) {
                    String serviceDescriptor = this.zzct.getServiceDescriptor();
                    new StringBuilder(34 + String.valueOf(serviceDescriptor).length() + String.valueOf(interfaceDescriptor).length());
                    int e = Log.e("GmsClient", sb.append("service descriptor mismatch: ").append(serviceDescriptor).append(" vs. ").append(interfaceDescriptor).toString());
                    return false;
                }
                IInterface createServiceInterface = this.zzct.createServiceInterface(this.zzcy);
                IInterface iInterface = createServiceInterface;
                if (createServiceInterface == null || (!this.zzct.zza(2, 4, iInterface) && !this.zzct.zza(3, 4, iInterface))) {
                    return false;
                }
                ConnectionResult zza = BaseGmsClient.zza(this.zzct, (ConnectionResult) null);
                Bundle connectionHint = this.zzct.getConnectionHint();
                if (this.zzct.zzck != null) {
                    this.zzct.zzck.onConnected(connectionHint);
                }
                return true;
            } catch (RemoteException e2) {
                int w = Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    private abstract class zza extends zzc<Boolean> {
        private final int statusCode;
        private final Bundle zzcs;
        private final /* synthetic */ BaseGmsClient zzct;

        /* JADX WARNING: Illegal instructions before constructor call */
        @android.support.annotation.BinderThread
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected zza(com.google.android.gms.common.internal.BaseGmsClient r8, int r9, android.os.Bundle r10) {
            /*
                r7 = this;
                r0 = r7
                r1 = r8
                r2 = r9
                r3 = r10
                r4 = r0
                r5 = r1
                r4.zzct = r5
                r4 = r0
                r5 = r1
                r6 = 1
                java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
                r4.<init>(r5, r6)
                r4 = r0
                r5 = r2
                r4.statusCode = r5
                r4 = r0
                r5 = r3
                r4.zzcs = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.BaseGmsClient.zza.<init>(com.google.android.gms.common.internal.BaseGmsClient, int, android.os.Bundle):void");
        }

        /* access modifiers changed from: protected */
        public abstract void zza(ConnectionResult connectionResult);

        /* access modifiers changed from: protected */
        public abstract boolean zzm();

        /* access modifiers changed from: protected */
        public final void zzn() {
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void zza(Object obj) {
            Throwable th;
            ConnectionResult connectionResult;
            ConnectionResult connectionResult2;
            if (((Boolean) obj) == null) {
                BaseGmsClient.zza(this.zzct, 1, (IInterface) null);
                return;
            }
            switch (this.statusCode) {
                case 0:
                    if (!zzm()) {
                        BaseGmsClient.zza(this.zzct, 1, (IInterface) null);
                        new ConnectionResult(8, (PendingIntent) null);
                        zza(connectionResult);
                        return;
                    }
                    return;
                case 10:
                    BaseGmsClient.zza(this.zzct, 1, (IInterface) null);
                    Throwable th2 = th;
                    Object[] objArr = new Object[3];
                    objArr[0] = getClass().getSimpleName();
                    Object[] objArr2 = objArr;
                    objArr2[1] = this.zzct.getStartServiceAction();
                    Object[] objArr3 = objArr2;
                    objArr3[2] = this.zzct.getServiceDescriptor();
                    new IllegalStateException(String.format("A fatal developer error has occurred. Class name: %s. Start service action: %s. Service Descriptor: %s. ", objArr3));
                    throw th2;
                default:
                    BaseGmsClient.zza(this.zzct, 1, (IInterface) null);
                    PendingIntent pendingIntent = null;
                    if (this.zzcs != null) {
                        pendingIntent = (PendingIntent) this.zzcs.getParcelable(BaseGmsClient.KEY_PENDING_INTENT);
                    }
                    new ConnectionResult(this.statusCode, pendingIntent);
                    zza(connectionResult2);
                    return;
            }
        }
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        Object obj;
        Object obj2;
        ArrayList<zzc<?>> arrayList;
        AtomicInteger atomicInteger;
        Handler handler;
        Looper looper2 = looper;
        new Object();
        this.mLock = obj;
        new Object();
        this.zzcd = obj2;
        new ArrayList<>();
        this.zzch = arrayList;
        this.zzcj = 1;
        this.zzco = null;
        this.zzcp = false;
        this.zzcq = null;
        new AtomicInteger(0);
        this.zzcr = atomicInteger;
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.zzca = (Looper) Preconditions.checkNotNull(looper2, "Looper must not be null");
        this.zzcb = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcc = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        new zzb(this, looper2);
        this.mHandler = handler;
        this.zzcm = i;
        this.zzck = baseConnectionCallbacks;
        this.zzcl = baseOnConnectionFailedListener;
        this.zzcn = str;
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        Object obj;
        Object obj2;
        ArrayList<zzc<?>> arrayList;
        AtomicInteger atomicInteger;
        Handler handler2 = handler;
        new Object();
        this.mLock = obj;
        new Object();
        this.zzcd = obj2;
        new ArrayList<>();
        this.zzch = arrayList;
        this.zzcj = 1;
        this.zzco = null;
        this.zzcp = false;
        this.zzcq = null;
        new AtomicInteger(0);
        this.zzcr = atomicInteger;
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.mHandler = (Handler) Preconditions.checkNotNull(handler2, "Handler must not be null");
        this.zzca = handler2.getLooper();
        this.zzcb = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcc = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.zzcm = i;
        this.zzck = baseConnectionCallbacks;
        this.zzcl = baseOnConnectionFailedListener;
        this.zzcn = null;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public String getStartServicePackage() {
        return "com.google.android.gms";
    }

    @Nullable
    private final String zzj() {
        return this.zzcn == null ? this.mContext.getClass().getName() : this.zzcn;
    }

    /* access modifiers changed from: protected */
    @Nullable
    @KeepForSdk
    public String getLocalStartServiceAction() {
        return null;
    }

    /* access modifiers changed from: private */
    public final void zza(zzb zzb2) {
        zzb zzb3 = zzb2;
        this.zzcq = zzb3;
    }

    @Nullable
    @KeepForSdk
    public final Feature[] getAvailableFeatures() {
        zzb zzb2 = this.zzcq;
        zzb zzb3 = zzb2;
        if (zzb2 == null) {
            return null;
        }
        return zzb3.zzdb;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    @CallSuper
    public void onConnectedLocked(@NonNull T t) {
        T t2 = t;
        long currentTimeMillis = System.currentTimeMillis();
        this.zzbw = currentTimeMillis;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    @CallSuper
    public void onConnectionSuspended(int i) {
        this.zzbu = i;
        this.zzbv = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    @CallSuper
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzbx = connectionResult.getErrorCode();
        this.zzby = System.currentTimeMillis();
    }

    /* JADX INFO: finally extract failed */
    private final void zza(int i, T t) {
        zze zze2;
        zzh zzh;
        zzh zzh2;
        GmsClientSupervisor.zza zza2;
        StringBuilder sb;
        zzh zzh3;
        StringBuilder sb2;
        int i2 = i;
        T t2 = t;
        Preconditions.checkArgument((i2 == 4) == (t2 != null));
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                this.zzcj = i2;
                this.zzcg = t2;
                onSetConnectState(i2, t2);
                switch (i2) {
                    case 1:
                        if (this.zzci != null) {
                            this.zzcb.zza(this.zzbz.zzt(), this.zzbz.getPackageName(), this.zzbz.zzq(), this.zzci, zzj());
                            this.zzci = null;
                            break;
                        }
                        break;
                    case 2:
                    case 3:
                        if (this.zzci != null) {
                            if (this.zzbz != null) {
                                String zzt = this.zzbz.zzt();
                                String packageName = this.zzbz.getPackageName();
                                new StringBuilder(70 + String.valueOf(zzt).length() + String.valueOf(packageName).length());
                                int e = Log.e("GmsClient", sb2.append("Calling connect() while still connected, missing disconnect() for ").append(zzt).append(" on ").append(packageName).toString());
                                this.zzcb.zza(this.zzbz.zzt(), this.zzbz.getPackageName(), this.zzbz.zzq(), this.zzci, zzj());
                                int incrementAndGet = this.zzcr.incrementAndGet();
                            }
                        }
                        new zze(this, this.zzcr.get());
                        this.zzci = zze2;
                        if (this.zzcj != 3 || getLocalStartServiceAction() == null) {
                            zzh2 = zzh;
                            new zzh(getStartServicePackage(), getStartServiceAction(), false, 129);
                        } else {
                            zzh2 = zzh3;
                            new zzh(getContext().getPackageName(), getLocalStartServiceAction(), true, 129);
                        }
                        this.zzbz = zzh2;
                        GmsClientSupervisor gmsClientSupervisor = this.zzcb;
                        String zzt2 = this.zzbz.zzt();
                        String packageName2 = this.zzbz.getPackageName();
                        int zzq = this.zzbz.zzq();
                        String zzj = zzj();
                        zze zze3 = this.zzci;
                        new GmsClientSupervisor.zza(zzt2, packageName2, zzq);
                        if (!gmsClientSupervisor.zza(zza2, zze3, zzj)) {
                            String zzt3 = this.zzbz.zzt();
                            String packageName3 = this.zzbz.getPackageName();
                            new StringBuilder(34 + String.valueOf(zzt3).length() + String.valueOf(packageName3).length());
                            int e2 = Log.e("GmsClient", sb.append("unable to connect to service: ").append(zzt3).append(" on ").append(packageName3).toString());
                            zza(16, (Bundle) null, this.zzcr.get());
                        }
                        break;
                    case 4:
                        onConnectedLocked(t2);
                        break;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @KeepForSdk
    public void onSetConnectState(int i, T t) {
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public final boolean zza(int i, int i2, T t) {
        int i3 = i;
        int i4 = i2;
        T t2 = t;
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.zzcj != i3) {
                    return false;
                }
                zza(i4, t2);
                return true;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    @KeepForSdk
    public void checkAvailabilityAndConnect() {
        ConnectionProgressReportCallbacks connectionProgressReportCallbacks;
        ConnectionProgressReportCallbacks connectionProgressReportCallbacks2;
        int isGooglePlayServicesAvailable = this.zzcc.isGooglePlayServicesAvailable(this.mContext, getMinApkVersion());
        int i = isGooglePlayServicesAvailable;
        if (isGooglePlayServicesAvailable != 0) {
            zza(1, (IInterface) null);
            new LegacyClientCallbackAdapter(this);
            triggerNotAvailable(connectionProgressReportCallbacks2, i, (PendingIntent) null);
            return;
        }
        new LegacyClientCallbackAdapter(this);
        connect(connectionProgressReportCallbacks);
    }

    @KeepForSdk
    public void connect(@NonNull ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        this.zzcf = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        zza(2, (IInterface) null);
    }

    @KeepForSdk
    public boolean isConnected() {
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                boolean z = this.zzcj == 4;
                return z;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    @KeepForSdk
    public boolean isConnecting() {
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                boolean z = this.zzcj == 2 || this.zzcj == 3;
                return z;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    private final boolean zzk() {
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                boolean z = this.zzcj == 3;
                return z;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    @KeepForSdk
    public void disconnect() {
        Throwable th;
        int incrementAndGet = this.zzcr.incrementAndGet();
        ArrayList<zzc<?>> arrayList = this.zzch;
        Object obj = arrayList;
        synchronized (arrayList) {
            try {
                int size = this.zzch.size();
                for (int i = 0; i < size; i++) {
                    this.zzch.get(i).removeListener();
                }
                this.zzch.clear();
                Object obj2 = this.zzcd;
                obj = obj2;
                synchronized (obj2) {
                    try {
                        this.zzce = null;
                        zza(1, (IInterface) null);
                    } catch (Throwable th2) {
                        while (true) {
                            throw th;
                        }
                    }
                }
            } finally {
                while (true) {
                    th = th2;
                    Object obj3 = obj;
                    Throwable th3 = th;
                }
            }
        }
    }

    @KeepForSdk
    public void triggerConnectionSuspended(int i) {
        boolean sendMessage = this.mHandler.sendMessage(this.mHandler.obtainMessage(6, this.zzcr.get(), i));
    }

    private final void zzb(int i) {
        int i2;
        int i3 = i;
        if (zzk()) {
            i2 = 5;
            this.zzcp = true;
        } else {
            i2 = 4;
        }
        boolean sendMessage = this.mHandler.sendMessage(this.mHandler.obtainMessage(i2, this.zzcr.get(), 16));
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    @VisibleForTesting
    public void triggerNotAvailable(@NonNull ConnectionProgressReportCallbacks connectionProgressReportCallbacks, int i, @Nullable PendingIntent pendingIntent) {
        this.zzcf = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        boolean sendMessage = this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzcr.get(), i, pendingIntent));
    }

    @KeepForSdk
    public final Context getContext() {
        return this.mContext;
    }

    @KeepForSdk
    public final Looper getLooper() {
        return this.zzca;
    }

    @KeepForSdk
    public Account getAccount() {
        return null;
    }

    @KeepForSdk
    public Feature[] getApiFeatures() {
        return zzbt;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Bundle getGetServiceRequestExtraArgs() {
        Bundle bundle;
        new Bundle();
        return bundle;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        Object obj;
        Handler handler = this.mHandler;
        new zzf(this, i, iBinder, bundle);
        boolean sendMessage = handler.sendMessage(this.mHandler.obtainMessage(1, i2, -1, obj));
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, @Nullable Bundle bundle, int i2) {
        Object obj;
        Bundle bundle2 = bundle;
        Handler handler = this.mHandler;
        new zzg(this, i, (Bundle) null);
        boolean sendMessage = handler.sendMessage(this.mHandler.obtainMessage(7, i2, -1, obj));
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public final void checkConnected() {
        Throwable th;
        if (!isConnected()) {
            Throwable th2 = th;
            new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
            throw th2;
        }
    }

    @KeepForSdk
    public Bundle getConnectionHint() {
        return null;
    }

    @KeepForSdk
    public final T getService() throws DeadObjectException {
        Throwable th;
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.zzcj == 5) {
                    Throwable th2 = th;
                    new DeadObjectException();
                    throw th2;
                }
                checkConnected();
                Preconditions.checkState(this.zzcg != null, "Client is connected but service is null");
                T t = this.zzcg;
                return t;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                Object obj3 = obj2;
                throw th4;
            }
        }
    }

    @WorkerThread
    @KeepForSdk
    public void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set) {
        GetServiceRequest getServiceRequest;
        Object obj;
        IGmsCallbacks iGmsCallbacks;
        Account account;
        Account account2;
        IAccountAccessor iAccountAccessor2 = iAccountAccessor;
        Set<Scope> set2 = set;
        Bundle getServiceRequestExtraArgs = getGetServiceRequestExtraArgs();
        new GetServiceRequest(this.zzcm);
        GetServiceRequest getServiceRequest2 = getServiceRequest;
        getServiceRequest2.zzy = this.mContext.getPackageName();
        GetServiceRequest getServiceRequest3 = getServiceRequest2;
        getServiceRequest3.zzdk = getServiceRequestExtraArgs;
        GetServiceRequest getServiceRequest4 = getServiceRequest3;
        if (set2 != null) {
            Set<Scope> set3 = set2;
            getServiceRequest4.zzdj = (Scope[]) set3.toArray(new Scope[set3.size()]);
        }
        if (requiresSignIn()) {
            GetServiceRequest getServiceRequest5 = getServiceRequest4;
            if (getAccount() != null) {
                account2 = getAccount();
            } else {
                account2 = account;
                new Account("<<default account>>", AccountType.GOOGLE);
            }
            GetServiceRequest getServiceRequest6 = getServiceRequest5;
            getServiceRequest6.zzdl = account2;
            IAccountAccessor iAccountAccessor3 = iAccountAccessor2;
            GetServiceRequest getServiceRequest7 = getServiceRequest6;
            if (iAccountAccessor3 != null) {
                getServiceRequest7.zzdi = iAccountAccessor3.asBinder();
            }
        } else if (requiresAccount()) {
            Account account3 = getAccount();
            Account account4 = account3;
            getServiceRequest4.zzdl = account3;
        }
        Feature[] featureArr = zzbt;
        Feature[] featureArr2 = featureArr;
        getServiceRequest4.zzdm = featureArr;
        Feature[] apiFeatures = getApiFeatures();
        Feature[] featureArr3 = apiFeatures;
        getServiceRequest4.zzdn = apiFeatures;
        try {
            Object obj2 = this.zzcd;
            obj = obj2;
            synchronized (obj2) {
                if (this.zzce != null) {
                    new zzd(this, this.zzcr.get());
                    this.zzce.getService(iGmsCallbacks, getServiceRequest4);
                } else {
                    int w = Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            int w2 = Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            triggerConnectionSuspended(1);
        } catch (SecurityException e2) {
            throw e2;
        } catch (RemoteException | RuntimeException e3) {
            int w3 = Log.w("GmsClient", "IGmsServiceBroker.getService failed", e3);
            onPostInitHandler(8, (IBinder) null, (Bundle) null, this.zzcr.get());
        } catch (Throwable th) {
            Throwable th2 = th;
            Object obj3 = obj;
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public boolean enableLocalFallback() {
        return false;
    }

    @KeepForSdk
    public boolean requiresSignIn() {
        return false;
    }

    @KeepForSdk
    public void onUserSignOut(@NonNull SignOutCallbacks signOutCallbacks) {
        signOutCallbacks.onSignOutComplete();
    }

    @KeepForSdk
    public boolean requiresAccount() {
        return false;
    }

    @KeepForSdk
    public boolean requiresGooglePlayServices() {
        return true;
    }

    @KeepForSdk
    public boolean providesSignIn() {
        return false;
    }

    @KeepForSdk
    public Intent getSignInIntent() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("Not a sign in API");
        throw th2;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Set<Scope> getScopes() {
        return Collections.EMPTY_SET;
    }

    /* JADX INFO: finally extract failed */
    @KeepForSdk
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        SimpleDateFormat simpleDateFormat;
        Date date;
        StringBuilder sb;
        Date date2;
        StringBuilder sb2;
        Date date3;
        StringBuilder sb3;
        String str2 = str;
        FileDescriptor fileDescriptor2 = fileDescriptor;
        PrintWriter printWriter2 = printWriter;
        String[] strArr2 = strArr;
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                int i = this.zzcj;
                T t = this.zzcg;
                Object obj3 = this.zzcd;
                Object obj4 = obj3;
                synchronized (obj3) {
                    try {
                        IGmsServiceBroker iGmsServiceBroker = this.zzce;
                        PrintWriter append = printWriter2.append(str2).append("mConnectState=");
                        switch (i) {
                            case 1:
                                printWriter2.print("DISCONNECTED");
                                break;
                            case 2:
                                printWriter2.print("REMOTE_CONNECTING");
                                break;
                            case 3:
                                printWriter2.print("LOCAL_CONNECTING");
                                break;
                            case 4:
                                printWriter2.print("CONNECTED");
                                break;
                            case 5:
                                printWriter2.print("DISCONNECTING");
                                break;
                            default:
                                printWriter2.print("UNKNOWN");
                                break;
                        }
                        PrintWriter append2 = printWriter2.append(" mService=");
                        if (t == null) {
                            PrintWriter append3 = printWriter2.append("null");
                        } else {
                            PrintWriter append4 = printWriter2.append(getServiceDescriptor()).append(GetNamedPart.CAST_METHOD_NAME).append(Integer.toHexString(System.identityHashCode(t.asBinder())));
                        }
                        PrintWriter append5 = printWriter2.append(" mServiceBroker=");
                        if (iGmsServiceBroker == null) {
                            printWriter2.println("null");
                        } else {
                            printWriter2.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker.asBinder())));
                        }
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
                        SimpleDateFormat simpleDateFormat2 = simpleDateFormat;
                        if (this.zzbw > 0) {
                            PrintWriter append6 = printWriter2.append(str2).append("lastConnectedTime=");
                            long j = this.zzbw;
                            new Date(this.zzbw);
                            String format = simpleDateFormat2.format(date3);
                            new StringBuilder(21 + String.valueOf(format).length());
                            append6.println(sb3.append(j).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(format).toString());
                        }
                        if (this.zzbv > 0) {
                            PrintWriter append7 = printWriter2.append(str2).append("lastSuspendedCause=");
                            switch (this.zzbu) {
                                case 1:
                                    PrintWriter append8 = printWriter2.append("CAUSE_SERVICE_DISCONNECTED");
                                    break;
                                case 2:
                                    PrintWriter append9 = printWriter2.append("CAUSE_NETWORK_LOST");
                                    break;
                                default:
                                    PrintWriter append10 = printWriter2.append(String.valueOf(this.zzbu));
                                    break;
                            }
                            PrintWriter append11 = printWriter2.append(" lastSuspendedTime=");
                            long j2 = this.zzbv;
                            new Date(this.zzbv);
                            String format2 = simpleDateFormat2.format(date2);
                            new StringBuilder(21 + String.valueOf(format2).length());
                            append11.println(sb2.append(j2).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(format2).toString());
                        }
                        if (this.zzby > 0) {
                            PrintWriter append12 = printWriter2.append(str2).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzbx));
                            PrintWriter append13 = printWriter2.append(" lastFailedTime=");
                            long j3 = this.zzby;
                            new Date(this.zzby);
                            String format3 = simpleDateFormat2.format(date);
                            new StringBuilder(21 + String.valueOf(format3).length());
                            append13.println(sb.append(j3).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(format3).toString());
                        }
                    } catch (Throwable th) {
                        while (true) {
                            Throwable th2 = th;
                            Object obj5 = obj4;
                            throw th2;
                        }
                    }
                }
            } catch (Throwable th3) {
                while (true) {
                    Throwable th4 = th3;
                    Object obj6 = obj2;
                    throw th4;
                }
            }
        }
    }

    @Nullable
    @KeepForSdk
    public IBinder getServiceBrokerBinder() {
        Object obj = this.zzcd;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.zzce == null) {
                    return null;
                }
                IBinder asBinder = this.zzce.asBinder();
                return asBinder;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzl() {
        if (this.zzcp) {
            return false;
        }
        if (TextUtils.isEmpty(getServiceDescriptor())) {
            return false;
        }
        if (TextUtils.isEmpty(getLocalStartServiceAction())) {
            return false;
        }
        try {
            Class<?> cls = Class.forName(getServiceDescriptor());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @KeepForSdk
    public String getEndpointPackageName() {
        Throwable th;
        if (isConnected() && this.zzbz != null) {
            return this.zzbz.getPackageName();
        }
        Throwable th2 = th;
        new RuntimeException("Failed to connect when checking package");
        throw th2;
    }

    @KeepForSdk
    public int getMinApkVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    static /* synthetic */ void zza(BaseGmsClient baseGmsClient, int i) {
        int i2 = i;
        baseGmsClient.zzb(16);
    }

    static /* synthetic */ IGmsServiceBroker zza(BaseGmsClient baseGmsClient, IGmsServiceBroker iGmsServiceBroker) {
        IGmsServiceBroker iGmsServiceBroker2 = iGmsServiceBroker;
        IGmsServiceBroker iGmsServiceBroker3 = iGmsServiceBroker2;
        baseGmsClient.zzce = iGmsServiceBroker3;
        return iGmsServiceBroker2;
    }

    static /* synthetic */ ConnectionResult zza(BaseGmsClient baseGmsClient, ConnectionResult connectionResult) {
        ConnectionResult connectionResult2 = connectionResult;
        ConnectionResult connectionResult3 = connectionResult2;
        baseGmsClient.zzco = connectionResult3;
        return connectionResult2;
    }

    static /* synthetic */ void zza(BaseGmsClient baseGmsClient, int i, IInterface iInterface) {
        IInterface iInterface2 = iInterface;
        baseGmsClient.zza(i, (IInterface) null);
    }

    static {
        String[] strArr = new String[2];
        strArr[0] = "service_esmobile";
        String[] strArr2 = strArr;
        strArr2[1] = "service_googleme";
        GOOGLE_PLUS_REQUIRED_FEATURES = strArr2;
    }
}
