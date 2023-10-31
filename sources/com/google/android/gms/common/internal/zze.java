package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Message;
import android.support.p000v4.p002os.EnvironmentCompat;
import android.util.Log;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.HashMap;
import javax.annotation.concurrent.GuardedBy;

final class zze extends GmsClientSupervisor implements Handler.Callback {
    /* access modifiers changed from: private */
    public final Handler mHandler;
    /* access modifiers changed from: private */
    @GuardedBy("mConnectionStatus")
    public final HashMap<GmsClientSupervisor.zza, zzf> zzdu;
    /* access modifiers changed from: private */
    public final Context zzdv;
    /* access modifiers changed from: private */
    public final ConnectionTracker zzdw = ConnectionTracker.getInstance();
    private final long zzdx = 5000;
    /* access modifiers changed from: private */
    public final long zzdy = 300000;

    zze(Context context) {
        HashMap<GmsClientSupervisor.zza, zzf> hashMap;
        Handler handler;
        Context context2 = context;
        new HashMap<>();
        this.zzdu = hashMap;
        this.zzdv = context2.getApplicationContext();
        new com.google.android.gms.internal.common.zze(context2.getMainLooper(), this);
        this.mHandler = handler;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public final boolean zza(GmsClientSupervisor.zza zza, ServiceConnection serviceConnection, String str) {
        Throwable th;
        StringBuilder sb;
        zzf zzf;
        GmsClientSupervisor.zza zza2 = zza;
        ServiceConnection serviceConnection2 = serviceConnection;
        String str2 = str;
        Object checkNotNull = Preconditions.checkNotNull(serviceConnection2, "ServiceConnection must not be null");
        HashMap<GmsClientSupervisor.zza, zzf> hashMap = this.zzdu;
        HashMap<GmsClientSupervisor.zza, zzf> hashMap2 = hashMap;
        synchronized (hashMap) {
            try {
                zzf zzf2 = this.zzdu.get(zza2);
                zzf zzf3 = zzf2;
                if (zzf2 != null) {
                    this.mHandler.removeMessages(0, zza2);
                    if (!zzf3.zza(serviceConnection2)) {
                        zzf3.zza(serviceConnection2, str2);
                        switch (zzf3.getState()) {
                            case 1:
                                serviceConnection2.onServiceConnected(zzf3.getComponentName(), zzf3.getBinder());
                                break;
                            case 2:
                                zzf3.zze(str2);
                                break;
                        }
                    } else {
                        Throwable th2 = th;
                        String valueOf = String.valueOf(zza2);
                        new StringBuilder(81 + String.valueOf(valueOf).length());
                        new IllegalStateException(sb.append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(valueOf).toString());
                        throw th2;
                    }
                } else {
                    new zzf(this, zza2);
                    zzf zzf4 = zzf;
                    zzf3 = zzf4;
                    zzf4.zza(serviceConnection2, str2);
                    zzf3.zze(str2);
                    zzf put = this.zzdu.put(zza2, zzf3);
                }
                boolean isBound = zzf3.isBound();
                return isBound;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                HashMap<GmsClientSupervisor.zza, zzf> hashMap3 = hashMap2;
                throw th4;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public final void zzb(GmsClientSupervisor.zza zza, ServiceConnection serviceConnection, String str) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        GmsClientSupervisor.zza zza2 = zza;
        ServiceConnection serviceConnection2 = serviceConnection;
        String str2 = str;
        Object checkNotNull = Preconditions.checkNotNull(serviceConnection2, "ServiceConnection must not be null");
        HashMap<GmsClientSupervisor.zza, zzf> hashMap = this.zzdu;
        HashMap<GmsClientSupervisor.zza, zzf> hashMap2 = hashMap;
        synchronized (hashMap) {
            try {
                zzf zzf = this.zzdu.get(zza2);
                zzf zzf2 = zzf;
                if (zzf == null) {
                    Throwable th3 = th2;
                    String valueOf = String.valueOf(zza2);
                    new StringBuilder(50 + String.valueOf(valueOf).length());
                    new IllegalStateException(sb2.append("Nonexistent connection status for service config: ").append(valueOf).toString());
                    throw th3;
                } else if (!zzf2.zza(serviceConnection2)) {
                    Throwable th4 = th;
                    String valueOf2 = String.valueOf(zza2);
                    new StringBuilder(76 + String.valueOf(valueOf2).length());
                    new IllegalStateException(sb.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(valueOf2).toString());
                    throw th4;
                } else {
                    zzf2.zzb(serviceConnection2, str2);
                    if (zzf2.zzr()) {
                        boolean sendMessageDelayed = this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, zza2), this.zzdx);
                    }
                }
            } catch (Throwable th5) {
                Throwable th6 = th5;
                HashMap<GmsClientSupervisor.zza, zzf> hashMap3 = hashMap2;
                throw th6;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final boolean handleMessage(Message message) {
        StringBuilder sb;
        Throwable th;
        ComponentName componentName;
        Message message2 = message;
        switch (message2.what) {
            case 0:
                HashMap<GmsClientSupervisor.zza, zzf> hashMap = this.zzdu;
                HashMap<GmsClientSupervisor.zza, zzf> hashMap2 = hashMap;
                synchronized (hashMap) {
                    try {
                        GmsClientSupervisor.zza zza = (GmsClientSupervisor.zza) message2.obj;
                        zzf zzf = this.zzdu.get(zza);
                        zzf zzf2 = zzf;
                        if (zzf != null && zzf2.zzr()) {
                            if (zzf2.isBound()) {
                                zzf2.zzf("GmsClientSupervisor");
                            }
                            zzf remove = this.zzdu.remove(zza);
                        }
                        return true;
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        HashMap<GmsClientSupervisor.zza, zzf> hashMap3 = hashMap2;
                        throw th3;
                    }
                }
            case 1:
                HashMap<GmsClientSupervisor.zza, zzf> hashMap4 = this.zzdu;
                HashMap<GmsClientSupervisor.zza, zzf> hashMap5 = hashMap4;
                synchronized (hashMap4) {
                    try {
                        GmsClientSupervisor.zza zza2 = (GmsClientSupervisor.zza) message2.obj;
                        zzf zzf3 = this.zzdu.get(zza2);
                        zzf zzf4 = zzf3;
                        if (zzf3 != null && zzf4.getState() == 3) {
                            String valueOf = String.valueOf(zza2);
                            new StringBuilder(47 + String.valueOf(valueOf).length());
                            new Exception();
                            int e = Log.e("GmsClientSupervisor", sb.append("Timeout waiting for ServiceConnection callback ").append(valueOf).toString(), th);
                            ComponentName componentName2 = zzf4.getComponentName();
                            ComponentName componentName3 = componentName2;
                            if (componentName2 == null) {
                                componentName3 = zza2.getComponentName();
                            }
                            if (componentName3 == null) {
                                new ComponentName(zza2.getPackage(), EnvironmentCompat.MEDIA_UNKNOWN);
                                componentName3 = componentName;
                            }
                            zzf4.onServiceDisconnected(componentName3);
                        }
                        return true;
                    } catch (Throwable th4) {
                        Throwable th5 = th4;
                        HashMap<GmsClientSupervisor.zza, zzf> hashMap6 = hashMap5;
                        throw th5;
                    }
                }
            default:
                return false;
        }
    }
}
