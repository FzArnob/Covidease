package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.VisibleRegion;

public final class Projection {
    private final IProjectionDelegate zzbn;

    Projection(IProjectionDelegate iProjectionDelegate) {
        this.zzbn = iProjectionDelegate;
    }

    public final LatLng fromScreenLocation(Point point) {
        Throwable th;
        try {
            return this.zzbn.fromScreenLocation(ObjectWrapper.wrap(point));
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final Point toScreenLocation(LatLng latLng) {
        Throwable th;
        try {
            return (Point) ObjectWrapper.unwrap(this.zzbn.toScreenLocation(latLng));
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final VisibleRegion getVisibleRegion() {
        Throwable th;
        try {
            return this.zzbn.getVisibleRegion();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }
}
