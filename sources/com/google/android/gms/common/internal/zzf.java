package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

final class zzf implements ServiceConnection {
    private ComponentName mComponentName;
    private int mState = 2;
    private IBinder zzcz;
    private final Set<ServiceConnection> zzdz;
    private boolean zzea;
    private final GmsClientSupervisor.zza zzeb;
    private final /* synthetic */ zze zzec;

    public zzf(zze zze, GmsClientSupervisor.zza zza) {
        Set<ServiceConnection> set;
        this.zzec = zze;
        this.zzeb = zza;
        new HashSet();
        this.zzdz = set;
    }

    /* JADX INFO: finally extract failed */
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ComponentName componentName2 = componentName;
        IBinder iBinder2 = iBinder;
        HashMap zza = this.zzec.zzdu;
        HashMap hashMap = zza;
        synchronized (zza) {
            try {
                this.zzec.mHandler.removeMessages(1, this.zzeb);
                this.zzcz = iBinder2;
                this.mComponentName = componentName2;
                for (ServiceConnection onServiceConnected : this.zzdz) {
                    onServiceConnected.onServiceConnected(componentName2, iBinder2);
                }
                this.mState = 1;
            } catch (Throwable th) {
                Throwable th2 = th;
                HashMap hashMap2 = hashMap;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void onServiceDisconnected(ComponentName componentName) {
        ComponentName componentName2 = componentName;
        HashMap zza = this.zzec.zzdu;
        HashMap hashMap = zza;
        synchronized (zza) {
            try {
                this.zzec.mHandler.removeMessages(1, this.zzeb);
                this.zzcz = null;
                this.mComponentName = componentName2;
                for (ServiceConnection onServiceDisconnected : this.zzdz) {
                    onServiceDisconnected.onServiceDisconnected(componentName2);
                }
                this.mState = 2;
            } catch (Throwable th) {
                Throwable th2 = th;
                HashMap hashMap2 = hashMap;
                throw th2;
            }
        }
    }

    public final void zze(String str) {
        this.mState = 3;
        this.zzea = this.zzec.zzdw.zza(this.zzec.zzdv, str, this.zzeb.zzb(this.zzec.zzdv), this, this.zzeb.zzq());
        if (this.zzea) {
            boolean sendMessageDelayed = this.zzec.mHandler.sendMessageDelayed(this.zzec.mHandler.obtainMessage(1, this.zzeb), this.zzec.zzdy);
            return;
        }
        this.mState = 2;
        try {
            this.zzec.zzdw.unbindService(this.zzec.zzdv, this);
        } catch (IllegalArgumentException e) {
        }
    }

    public final void zzf(String str) {
        String str2 = str;
        this.zzec.mHandler.removeMessages(1, this.zzeb);
        this.zzec.zzdw.unbindService(this.zzec.zzdv, this);
        this.zzea = false;
        this.mState = 2;
    }

    public final void zza(ServiceConnection serviceConnection, String str) {
        String str2 = str;
        ConnectionTracker zzd = this.zzec.zzdw;
        Context zzc = this.zzec.zzdv;
        Intent zzb = this.zzeb.zzb(this.zzec.zzdv);
        boolean add = this.zzdz.add(serviceConnection);
    }

    public final void zzb(ServiceConnection serviceConnection, String str) {
        String str2 = str;
        ConnectionTracker zzd = this.zzec.zzdw;
        Context zzc = this.zzec.zzdv;
        boolean remove = this.zzdz.remove(serviceConnection);
    }

    public final boolean isBound() {
        return this.zzea;
    }

    public final int getState() {
        return this.mState;
    }

    public final boolean zza(ServiceConnection serviceConnection) {
        return this.zzdz.contains(serviceConnection);
    }

    public final boolean zzr() {
        return this.zzdz.isEmpty();
    }

    public final IBinder getBinder() {
        return this.zzcz;
    }

    public final ComponentName getComponentName() {
        return this.mComponentName;
    }
}
