package com.google.android.gms.maps.model;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zze;

public final class BitmapDescriptorFactory {
    public static final float HUE_AZURE = 210.0f;
    public static final float HUE_BLUE = 240.0f;
    public static final float HUE_CYAN = 180.0f;
    public static final float HUE_GREEN = 120.0f;
    public static final float HUE_MAGENTA = 300.0f;
    public static final float HUE_ORANGE = 30.0f;
    public static final float HUE_RED = 0.0f;
    public static final float HUE_ROSE = 330.0f;
    public static final float HUE_VIOLET = 270.0f;
    public static final float HUE_YELLOW = 60.0f;
    private static zze zzcm;

    private BitmapDescriptorFactory() {
    }

    private static zze zzg() {
        return (zze) Preconditions.checkNotNull(zzcm, "IBitmapDescriptorFactory is not initialized");
    }

    public static void zza(zze zze) {
        zze zze2 = zze;
        if (zzcm == null) {
            zzcm = (zze) Preconditions.checkNotNull(zze2);
        }
    }

    public static BitmapDescriptor fromResource(int i) {
        Throwable th;
        BitmapDescriptor bitmapDescriptor;
        try {
            BitmapDescriptor bitmapDescriptor2 = bitmapDescriptor;
            new BitmapDescriptor(zzg().zza(i));
            return bitmapDescriptor2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static BitmapDescriptor fromAsset(String str) {
        Throwable th;
        BitmapDescriptor bitmapDescriptor;
        try {
            BitmapDescriptor bitmapDescriptor2 = bitmapDescriptor;
            new BitmapDescriptor(zzg().zza(str));
            return bitmapDescriptor2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static BitmapDescriptor fromFile(String str) {
        Throwable th;
        BitmapDescriptor bitmapDescriptor;
        try {
            BitmapDescriptor bitmapDescriptor2 = bitmapDescriptor;
            new BitmapDescriptor(zzg().zzb(str));
            return bitmapDescriptor2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static BitmapDescriptor fromPath(String str) {
        Throwable th;
        BitmapDescriptor bitmapDescriptor;
        try {
            BitmapDescriptor bitmapDescriptor2 = bitmapDescriptor;
            new BitmapDescriptor(zzg().zzc(str));
            return bitmapDescriptor2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static BitmapDescriptor defaultMarker() {
        Throwable th;
        BitmapDescriptor bitmapDescriptor;
        try {
            BitmapDescriptor bitmapDescriptor2 = bitmapDescriptor;
            new BitmapDescriptor(zzg().zzi());
            return bitmapDescriptor2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static BitmapDescriptor defaultMarker(float f) {
        Throwable th;
        BitmapDescriptor bitmapDescriptor;
        try {
            BitmapDescriptor bitmapDescriptor2 = bitmapDescriptor;
            new BitmapDescriptor(zzg().zza(f));
            return bitmapDescriptor2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static BitmapDescriptor fromBitmap(Bitmap bitmap) {
        Throwable th;
        BitmapDescriptor bitmapDescriptor;
        try {
            BitmapDescriptor bitmapDescriptor2 = bitmapDescriptor;
            new BitmapDescriptor(zzg().zza(bitmap));
            return bitmapDescriptor2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }
}
