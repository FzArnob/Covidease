package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.zzbh;
import com.google.android.gms.maps.internal.zzbj;
import com.google.android.gms.maps.internal.zzbl;
import com.google.android.gms.maps.internal.zzbn;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

public class StreetViewPanorama {
    private final IStreetViewPanoramaDelegate zzbo;

    public interface OnStreetViewPanoramaCameraChangeListener {
        void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera streetViewPanoramaCamera);
    }

    public interface OnStreetViewPanoramaChangeListener {
        void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetViewPanoramaLocation);
    }

    public interface OnStreetViewPanoramaClickListener {
        void onStreetViewPanoramaClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation);
    }

    public interface OnStreetViewPanoramaLongClickListener {
        void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation);
    }

    public StreetViewPanorama(@NonNull IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) {
        this.zzbo = (IStreetViewPanoramaDelegate) Preconditions.checkNotNull(iStreetViewPanoramaDelegate, "delegate");
    }

    public boolean isZoomGesturesEnabled() {
        Throwable th;
        try {
            return this.zzbo.isZoomGesturesEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public void setZoomGesturesEnabled(boolean z) {
        Throwable th;
        try {
            this.zzbo.enableZoom(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public boolean isPanningGesturesEnabled() {
        Throwable th;
        try {
            return this.zzbo.isPanningGesturesEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public void setPanningGesturesEnabled(boolean z) {
        Throwable th;
        try {
            this.zzbo.enablePanning(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public boolean isUserNavigationEnabled() {
        Throwable th;
        try {
            return this.zzbo.isUserNavigationEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public void setUserNavigationEnabled(boolean z) {
        Throwable th;
        try {
            this.zzbo.enableUserNavigation(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public boolean isStreetNamesEnabled() {
        Throwable th;
        try {
            return this.zzbo.isStreetNamesEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public void setStreetNamesEnabled(boolean z) {
        Throwable th;
        try {
            this.zzbo.enableStreetNames(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public void animateTo(StreetViewPanoramaCamera streetViewPanoramaCamera, long j) {
        Throwable th;
        try {
            this.zzbo.animateTo(streetViewPanoramaCamera, j);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public StreetViewPanoramaCamera getPanoramaCamera() {
        Throwable th;
        try {
            return this.zzbo.getPanoramaCamera();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public void setPosition(String str) {
        Throwable th;
        try {
            this.zzbo.setPositionWithID(str);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public void setPosition(LatLng latLng) {
        Throwable th;
        try {
            this.zzbo.setPosition(latLng);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public void setPosition(LatLng latLng, int i) {
        Throwable th;
        try {
            this.zzbo.setPositionWithRadius(latLng, i);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public void setPosition(LatLng latLng, StreetViewSource streetViewSource) {
        Throwable th;
        try {
            this.zzbo.setPositionWithSource(latLng, streetViewSource);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public void setPosition(LatLng latLng, int i, StreetViewSource streetViewSource) {
        Throwable th;
        try {
            this.zzbo.setPositionWithRadiusAndSource(latLng, i, streetViewSource);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public StreetViewPanoramaLocation getLocation() {
        Throwable th;
        try {
            return this.zzbo.getStreetViewPanoramaLocation();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public StreetViewPanoramaOrientation pointToOrientation(Point point) {
        Throwable th;
        try {
            return this.zzbo.pointToOrientation(ObjectWrapper.wrap(point));
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public Point orientationToPoint(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        Throwable th;
        try {
            IObjectWrapper orientationToPoint = this.zzbo.orientationToPoint(streetViewPanoramaOrientation);
            IObjectWrapper iObjectWrapper = orientationToPoint;
            if (orientationToPoint == null) {
                return null;
            }
            return (Point) ObjectWrapper.unwrap(iObjectWrapper);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setOnStreetViewPanoramaChangeListener(OnStreetViewPanoramaChangeListener onStreetViewPanoramaChangeListener) {
        zzbj zzbj;
        Throwable th;
        OnStreetViewPanoramaChangeListener onStreetViewPanoramaChangeListener2 = onStreetViewPanoramaChangeListener;
        if (onStreetViewPanoramaChangeListener2 == null) {
            try {
                this.zzbo.setOnStreetViewPanoramaChangeListener((zzbj) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzad(this, onStreetViewPanoramaChangeListener2);
            this.zzbo.setOnStreetViewPanoramaChangeListener(zzbj);
        }
    }

    public final void setOnStreetViewPanoramaCameraChangeListener(OnStreetViewPanoramaCameraChangeListener onStreetViewPanoramaCameraChangeListener) {
        zzbh zzbh;
        Throwable th;
        OnStreetViewPanoramaCameraChangeListener onStreetViewPanoramaCameraChangeListener2 = onStreetViewPanoramaCameraChangeListener;
        if (onStreetViewPanoramaCameraChangeListener2 == null) {
            try {
                this.zzbo.setOnStreetViewPanoramaCameraChangeListener((zzbh) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzae(this, onStreetViewPanoramaCameraChangeListener2);
            this.zzbo.setOnStreetViewPanoramaCameraChangeListener(zzbh);
        }
    }

    public final void setOnStreetViewPanoramaClickListener(OnStreetViewPanoramaClickListener onStreetViewPanoramaClickListener) {
        zzbl zzbl;
        Throwable th;
        OnStreetViewPanoramaClickListener onStreetViewPanoramaClickListener2 = onStreetViewPanoramaClickListener;
        if (onStreetViewPanoramaClickListener2 == null) {
            try {
                this.zzbo.setOnStreetViewPanoramaClickListener((zzbl) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzaf(this, onStreetViewPanoramaClickListener2);
            this.zzbo.setOnStreetViewPanoramaClickListener(zzbl);
        }
    }

    public final void setOnStreetViewPanoramaLongClickListener(OnStreetViewPanoramaLongClickListener onStreetViewPanoramaLongClickListener) {
        zzbn zzbn;
        Throwable th;
        OnStreetViewPanoramaLongClickListener onStreetViewPanoramaLongClickListener2 = onStreetViewPanoramaLongClickListener;
        if (onStreetViewPanoramaLongClickListener2 == null) {
            try {
                this.zzbo.setOnStreetViewPanoramaLongClickListener((zzbn) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzag(this, onStreetViewPanoramaLongClickListener2);
            this.zzbo.setOnStreetViewPanoramaLongClickListener(zzbn);
        }
    }
}
