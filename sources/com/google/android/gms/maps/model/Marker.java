package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzt;

public final class Marker {
    private final zzt zzdm;

    public Marker(zzt zzt) {
        this.zzdm = (zzt) Preconditions.checkNotNull(zzt);
    }

    public final void remove() {
        Throwable th;
        try {
            this.zzdm.remove();
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
            return this.zzdm.getId();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setPosition(@NonNull LatLng latLng) {
        Throwable th;
        Throwable th2;
        LatLng latLng2 = latLng;
        if (latLng2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("latlng cannot be null - a position is required.");
            throw th3;
        }
        try {
            this.zzdm.setPosition(latLng2);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th4 = th;
            new RuntimeRemoteException(remoteException);
            throw th4;
        }
    }

    public final LatLng getPosition() {
        Throwable th;
        try {
            return this.zzdm.getPosition();
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
            this.zzdm.setZIndex(f);
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
            return this.zzdm.getZIndex();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setIcon(@Nullable BitmapDescriptor bitmapDescriptor) {
        Throwable th;
        BitmapDescriptor bitmapDescriptor2 = bitmapDescriptor;
        if (bitmapDescriptor2 == null) {
            try {
                this.zzdm.zzg((IObjectWrapper) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            this.zzdm.zzg(bitmapDescriptor2.zzb());
        }
    }

    public final void setAnchor(float f, float f2) {
        Throwable th;
        try {
            this.zzdm.setAnchor(f, f2);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setInfoWindowAnchor(float f, float f2) {
        Throwable th;
        try {
            this.zzdm.setInfoWindowAnchor(f, f2);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setTitle(@Nullable String str) {
        Throwable th;
        try {
            this.zzdm.setTitle(str);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final String getTitle() {
        Throwable th;
        try {
            return this.zzdm.getTitle();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setSnippet(@Nullable String str) {
        Throwable th;
        try {
            this.zzdm.setSnippet(str);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final String getSnippet() {
        Throwable th;
        try {
            return this.zzdm.getSnippet();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setDraggable(boolean z) {
        Throwable th;
        try {
            this.zzdm.setDraggable(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isDraggable() {
        Throwable th;
        try {
            return this.zzdm.isDraggable();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void showInfoWindow() {
        Throwable th;
        try {
            this.zzdm.showInfoWindow();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void hideInfoWindow() {
        Throwable th;
        try {
            this.zzdm.hideInfoWindow();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isInfoWindowShown() {
        Throwable th;
        try {
            return this.zzdm.isInfoWindowShown();
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
            this.zzdm.setVisible(z);
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
            return this.zzdm.isVisible();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setFlat(boolean z) {
        Throwable th;
        try {
            this.zzdm.setFlat(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isFlat() {
        Throwable th;
        try {
            return this.zzdm.isFlat();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setRotation(float f) {
        Throwable th;
        try {
            this.zzdm.setRotation(f);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final float getRotation() {
        Throwable th;
        try {
            return this.zzdm.getRotation();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setAlpha(float f) {
        Throwable th;
        try {
            this.zzdm.setAlpha(f);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final float getAlpha() {
        Throwable th;
        try {
            return this.zzdm.getAlpha();
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
            this.zzdm.zze(ObjectWrapper.wrap(obj));
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
            return ObjectWrapper.unwrap(this.zzdm.zzk());
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
        if (!(obj2 instanceof Marker)) {
            return false;
        }
        try {
            return this.zzdm.zzj(((Marker) obj2).zzdm);
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
            return this.zzdm.zzj();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }
}
