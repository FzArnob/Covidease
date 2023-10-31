package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class CameraUpdateFactory {
    private static ICameraUpdateFactoryDelegate zzf;

    private CameraUpdateFactory() {
    }

    private static ICameraUpdateFactoryDelegate zzc() {
        return (ICameraUpdateFactoryDelegate) Preconditions.checkNotNull(zzf, "CameraUpdateFactory is not initialized");
    }

    public static void zza(ICameraUpdateFactoryDelegate iCameraUpdateFactoryDelegate) {
        zzf = (ICameraUpdateFactoryDelegate) Preconditions.checkNotNull(iCameraUpdateFactoryDelegate);
    }

    public static CameraUpdate zoomIn() {
        Throwable th;
        CameraUpdate cameraUpdate;
        try {
            CameraUpdate cameraUpdate2 = cameraUpdate;
            new CameraUpdate(zzc().zoomIn());
            return cameraUpdate2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static CameraUpdate zoomOut() {
        Throwable th;
        CameraUpdate cameraUpdate;
        try {
            CameraUpdate cameraUpdate2 = cameraUpdate;
            new CameraUpdate(zzc().zoomOut());
            return cameraUpdate2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static CameraUpdate scrollBy(float f, float f2) {
        Throwable th;
        CameraUpdate cameraUpdate;
        try {
            CameraUpdate cameraUpdate2 = cameraUpdate;
            new CameraUpdate(zzc().scrollBy(f, f2));
            return cameraUpdate2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static CameraUpdate zoomTo(float f) {
        Throwable th;
        CameraUpdate cameraUpdate;
        try {
            CameraUpdate cameraUpdate2 = cameraUpdate;
            new CameraUpdate(zzc().zoomTo(f));
            return cameraUpdate2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static CameraUpdate zoomBy(float f) {
        Throwable th;
        CameraUpdate cameraUpdate;
        try {
            CameraUpdate cameraUpdate2 = cameraUpdate;
            new CameraUpdate(zzc().zoomBy(f));
            return cameraUpdate2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static CameraUpdate zoomBy(float f, Point point) {
        Throwable th;
        CameraUpdate cameraUpdate;
        Point point2 = point;
        try {
            CameraUpdate cameraUpdate2 = cameraUpdate;
            new CameraUpdate(zzc().zoomByWithFocus(f, point2.x, point2.y));
            return cameraUpdate2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static CameraUpdate newCameraPosition(CameraPosition cameraPosition) {
        Throwable th;
        CameraUpdate cameraUpdate;
        try {
            CameraUpdate cameraUpdate2 = cameraUpdate;
            new CameraUpdate(zzc().newCameraPosition(cameraPosition));
            return cameraUpdate2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static CameraUpdate newLatLng(LatLng latLng) {
        Throwable th;
        CameraUpdate cameraUpdate;
        try {
            CameraUpdate cameraUpdate2 = cameraUpdate;
            new CameraUpdate(zzc().newLatLng(latLng));
            return cameraUpdate2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static CameraUpdate newLatLngZoom(LatLng latLng, float f) {
        Throwable th;
        CameraUpdate cameraUpdate;
        try {
            CameraUpdate cameraUpdate2 = cameraUpdate;
            new CameraUpdate(zzc().newLatLngZoom(latLng, f));
            return cameraUpdate2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i) {
        Throwable th;
        CameraUpdate cameraUpdate;
        try {
            CameraUpdate cameraUpdate2 = cameraUpdate;
            new CameraUpdate(zzc().newLatLngBounds(latLngBounds, i));
            return cameraUpdate2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i, int i2, int i3) {
        Throwable th;
        CameraUpdate cameraUpdate;
        try {
            CameraUpdate cameraUpdate2 = cameraUpdate;
            new CameraUpdate(zzc().newLatLngBoundsWithSize(latLngBounds, i, i2, i3));
            return cameraUpdate2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }
}
