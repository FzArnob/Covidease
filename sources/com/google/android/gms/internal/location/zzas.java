package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzu;
import com.google.android.gms.location.zzx;
import java.util.HashMap;
import java.util.Map;

public final class zzas {
    private final zzbj<zzao> zzcb;
    private final Context zzcu;
    private ContentProviderClient zzcv = null;
    private boolean zzcw = false;
    private final Map<ListenerHolder.ListenerKey<LocationListener>, zzax> zzcx;
    private final Map<ListenerHolder.ListenerKey<Object>, zzaw> zzcy;
    private final Map<ListenerHolder.ListenerKey<LocationCallback>, zzat> zzcz;

    public zzas(Context context, zzbj<zzao> zzbj) {
        Map<ListenerHolder.ListenerKey<LocationListener>, zzax> map;
        Map<ListenerHolder.ListenerKey<Object>, zzaw> map2;
        Map<ListenerHolder.ListenerKey<LocationCallback>, zzat> map3;
        new HashMap();
        this.zzcx = map;
        new HashMap();
        this.zzcy = map2;
        new HashMap();
        this.zzcz = map3;
        this.zzcu = context;
        this.zzcb = zzbj;
    }

    private final zzax zza(ListenerHolder<LocationListener> listenerHolder) {
        zzax zzax;
        ListenerHolder<LocationListener> listenerHolder2 = listenerHolder;
        Map<ListenerHolder.ListenerKey<LocationListener>, zzax> map = this.zzcx;
        Map<ListenerHolder.ListenerKey<LocationListener>, zzax> map2 = map;
        synchronized (map) {
            try {
                zzax zzax2 = this.zzcx.get(listenerHolder2.getListenerKey());
                zzax zzax3 = zzax2;
                if (zzax2 == null) {
                    new zzax(listenerHolder2);
                    zzax3 = zzax;
                }
                zzax put = this.zzcx.put(listenerHolder2.getListenerKey(), zzax3);
                zzax zzax4 = zzax3;
                return zzax4;
            } catch (Throwable th) {
                Throwable th2 = th;
                Map<ListenerHolder.ListenerKey<LocationListener>, zzax> map3 = map2;
                throw th2;
            }
        }
    }

    private final zzat zzb(ListenerHolder<LocationCallback> listenerHolder) {
        zzat zzat;
        ListenerHolder<LocationCallback> listenerHolder2 = listenerHolder;
        Map<ListenerHolder.ListenerKey<LocationCallback>, zzat> map = this.zzcz;
        Map<ListenerHolder.ListenerKey<LocationCallback>, zzat> map2 = map;
        synchronized (map) {
            try {
                zzat zzat2 = this.zzcz.get(listenerHolder2.getListenerKey());
                zzat zzat3 = zzat2;
                if (zzat2 == null) {
                    new zzat(listenerHolder2);
                    zzat3 = zzat;
                }
                zzat put = this.zzcz.put(listenerHolder2.getListenerKey(), zzat3);
                zzat zzat4 = zzat3;
                return zzat4;
            } catch (Throwable th) {
                Throwable th2 = th;
                Map<ListenerHolder.ListenerKey<LocationCallback>, zzat> map3 = map2;
                throw th2;
            }
        }
    }

    public final Location getLastLocation() throws RemoteException {
        this.zzcb.checkConnected();
        return this.zzcb.getService().zza(this.zzcu.getPackageName());
    }

    /* JADX INFO: finally extract failed */
    public final void removeAllListeners() throws RemoteException {
        zzo zzo;
        Map<ListenerHolder.ListenerKey<LocationListener>, zzax> map = this.zzcx;
        Map<ListenerHolder.ListenerKey<LocationListener>, zzax> map2 = map;
        synchronized (map) {
            try {
                for (zzax next : this.zzcx.values()) {
                    zzax zzax = next;
                    if (next != null) {
                        this.zzcb.getService().zza(zzbf.zza((zzx) zzax, (zzaj) null));
                    }
                }
                this.zzcx.clear();
                Map<ListenerHolder.ListenerKey<LocationCallback>, zzat> map3 = this.zzcz;
                Map<ListenerHolder.ListenerKey<LocationCallback>, zzat> map4 = map3;
                synchronized (map3) {
                    try {
                        for (zzat next2 : this.zzcz.values()) {
                            zzat zzat = next2;
                            if (next2 != null) {
                                this.zzcb.getService().zza(zzbf.zza((zzu) zzat, (zzaj) null));
                            }
                        }
                        this.zzcz.clear();
                        Map<ListenerHolder.ListenerKey<Object>, zzaw> map5 = this.zzcy;
                        Map<ListenerHolder.ListenerKey<Object>, zzaw> map6 = map5;
                        synchronized (map5) {
                            try {
                                for (zzaw next3 : this.zzcy.values()) {
                                    zzaw zzaw = next3;
                                    if (next3 != null) {
                                        new zzo(2, (zzm) null, zzaw.asBinder(), (IBinder) null);
                                        this.zzcb.getService().zza(zzo);
                                    }
                                }
                                this.zzcy.clear();
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                Map<ListenerHolder.ListenerKey<Object>, zzaw> map7 = map6;
                                throw th2;
                            }
                        }
                    } catch (Throwable th3) {
                        while (true) {
                            Throwable th4 = th3;
                            Map<ListenerHolder.ListenerKey<LocationCallback>, zzat> map8 = map4;
                            throw th4;
                        }
                    }
                }
            } catch (Throwable th5) {
                while (true) {
                    Throwable th6 = th5;
                    Map<ListenerHolder.ListenerKey<LocationListener>, zzax> map9 = map2;
                    throw th6;
                }
            }
        }
    }

    public final LocationAvailability zza() throws RemoteException {
        this.zzcb.checkConnected();
        return this.zzcb.getService().zzb(this.zzcu.getPackageName());
    }

    public final void zza(PendingIntent pendingIntent, zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        zzao service = this.zzcb.getService();
        zzaj zzaj2 = zzaj;
        zzbf zzbf = r14;
        zzbf zzbf2 = new zzbf(2, (zzbd) null, (IBinder) null, pendingIntent, (IBinder) null, zzaj2 != null ? zzaj2.asBinder() : null);
        service.zza(zzbf);
    }

    public final void zza(Location location) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(location);
    }

    /* JADX INFO: finally extract failed */
    public final void zza(ListenerHolder.ListenerKey<LocationListener> listenerKey, zzaj zzaj) throws RemoteException {
        ListenerHolder.ListenerKey<LocationListener> listenerKey2 = listenerKey;
        zzaj zzaj2 = zzaj;
        this.zzcb.checkConnected();
        Object checkNotNull = Preconditions.checkNotNull(listenerKey2, "Invalid null listener key");
        Map<ListenerHolder.ListenerKey<LocationListener>, zzax> map = this.zzcx;
        Map<ListenerHolder.ListenerKey<LocationListener>, zzax> map2 = map;
        synchronized (map) {
            try {
                zzax remove = this.zzcx.remove(listenerKey2);
                zzax zzax = remove;
                if (remove != null) {
                    zzax.release();
                    this.zzcb.getService().zza(zzbf.zza((zzx) zzax, zzaj2));
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Map<ListenerHolder.ListenerKey<LocationListener>, zzax> map3 = map2;
                throw th2;
            }
        }
    }

    public final void zza(zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(zzaj);
    }

    public final void zza(zzbd zzbd, ListenerHolder<LocationCallback> listenerHolder, zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        zzat zzb = zzb(listenerHolder);
        zzao service = this.zzcb.getService();
        zzaj zzaj2 = zzaj;
        zzbf zzbf = r17;
        zzbf zzbf2 = new zzbf(1, zzbd, (IBinder) null, (PendingIntent) null, zzb.asBinder(), zzaj2 != null ? zzaj2.asBinder() : null);
        service.zza(zzbf);
    }

    public final void zza(LocationRequest locationRequest, PendingIntent pendingIntent, zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        zzao service = this.zzcb.getService();
        zzaj zzaj2 = zzaj;
        zzbf zzbf = r16;
        zzbf zzbf2 = new zzbf(1, zzbd.zza(locationRequest), (IBinder) null, pendingIntent, (IBinder) null, zzaj2 != null ? zzaj2.asBinder() : null);
        service.zza(zzbf);
    }

    public final void zza(LocationRequest locationRequest, ListenerHolder<LocationListener> listenerHolder, zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        zzax zza = zza(listenerHolder);
        zzao service = this.zzcb.getService();
        zzaj zzaj2 = zzaj;
        zzbf zzbf = r17;
        zzbf zzbf2 = new zzbf(1, zzbd.zza(locationRequest), zza.asBinder(), (PendingIntent) null, (IBinder) null, zzaj2 != null ? zzaj2.asBinder() : null);
        service.zza(zzbf);
    }

    public final void zza(boolean z) throws RemoteException {
        boolean z2 = z;
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(z2);
        this.zzcw = z2;
    }

    public final void zzb() throws RemoteException {
        if (this.zzcw) {
            zza(false);
        }
    }

    /* JADX INFO: finally extract failed */
    public final void zzb(ListenerHolder.ListenerKey<LocationCallback> listenerKey, zzaj zzaj) throws RemoteException {
        ListenerHolder.ListenerKey<LocationCallback> listenerKey2 = listenerKey;
        zzaj zzaj2 = zzaj;
        this.zzcb.checkConnected();
        Object checkNotNull = Preconditions.checkNotNull(listenerKey2, "Invalid null listener key");
        Map<ListenerHolder.ListenerKey<LocationCallback>, zzat> map = this.zzcz;
        Map<ListenerHolder.ListenerKey<LocationCallback>, zzat> map2 = map;
        synchronized (map) {
            try {
                zzat remove = this.zzcz.remove(listenerKey2);
                zzat zzat = remove;
                if (remove != null) {
                    zzat.release();
                    this.zzcb.getService().zza(zzbf.zza((zzu) zzat, zzaj2));
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Map<ListenerHolder.ListenerKey<LocationCallback>, zzat> map3 = map2;
                throw th2;
            }
        }
    }
}
