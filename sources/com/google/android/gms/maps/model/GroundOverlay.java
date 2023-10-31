package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzk;

public final class GroundOverlay {
    private final zzk zzcw;

    public GroundOverlay(zzk zzk) {
        this.zzcw = (zzk) Preconditions.checkNotNull(zzk);
    }

    public final void remove() {
        Throwable th;
        try {
            this.zzcw.remove();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final String getId() {
        Throwable th;
        try {
            return this.zzcw.getId();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setPosition(LatLng latLng) {
        Throwable th;
        try {
            this.zzcw.setPosition(latLng);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final LatLng getPosition() {
        Throwable th;
        try {
            return this.zzcw.getPosition();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setImage(@NonNull BitmapDescriptor bitmapDescriptor) {
        Throwable th;
        BitmapDescriptor bitmapDescriptor2 = bitmapDescriptor;
        Object checkNotNull = Preconditions.checkNotNull(bitmapDescriptor2, "imageDescriptor must not be null");
        try {
            this.zzcw.zzf(bitmapDescriptor2.zzb());
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setDimensions(float f) {
        Throwable th;
        try {
            this.zzcw.setDimensions(f);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setDimensions(float f, float f2) {
        Throwable th;
        try {
            this.zzcw.zza(f, f2);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final float getWidth() {
        Throwable th;
        try {
            return this.zzcw.getWidth();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final float getHeight() {
        Throwable th;
        try {
            return this.zzcw.getHeight();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setPositionFromBounds(LatLngBounds latLngBounds) {
        Throwable th;
        try {
            this.zzcw.setPositionFromBounds(latLngBounds);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final LatLngBounds getBounds() {
        Throwable th;
        try {
            return this.zzcw.getBounds();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setBearing(float f) {
        Throwable th;
        try {
            this.zzcw.setBearing(f);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final float getBearing() {
        Throwable th;
        try {
            return this.zzcw.getBearing();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setZIndex(float f) {
        Throwable th;
        try {
            this.zzcw.setZIndex(f);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final float getZIndex() {
        Throwable th;
        try {
            return this.zzcw.getZIndex();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setVisible(boolean z) {
        Throwable th;
        try {
            this.zzcw.setVisible(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isVisible() {
        Throwable th;
        try {
            return this.zzcw.isVisible();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setClickable(boolean z) {
        Throwable th;
        try {
            this.zzcw.setClickable(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isClickable() {
        Throwable th;
        try {
            return this.zzcw.isClickable();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setTransparency(float f) {
        Throwable th;
        try {
            this.zzcw.setTransparency(f);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final float getTransparency() {
        Throwable th;
        try {
            return this.zzcw.getTransparency();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setTag(@Nullable Object obj) {
        Throwable th;
        try {
            this.zzcw.zze(ObjectWrapper.wrap(obj));
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    @Nullable
    public final Object getTag() {
        Throwable th;
        try {
            return ObjectWrapper.unwrap(this.zzcw.zzk());
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean equals(Object obj) {
        Throwable th;
        Object obj2 = obj;
        if (!(obj2 instanceof GroundOverlay)) {
            return false;
        }
        try {
            return this.zzcw.zzb(((GroundOverlay) obj2).zzcw);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final int hashCode() {
        Throwable th;
        try {
            return this.zzcw.zzj();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }
}
