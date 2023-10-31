package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzh;
import java.util.List;

public final class Circle {
    private final zzh zzco;

    public Circle(zzh zzh) {
        this.zzco = (zzh) Preconditions.checkNotNull(zzh);
    }

    public final void remove() {
        Throwable th;
        try {
            this.zzco.remove();
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
            return this.zzco.getId();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setCenter(LatLng latLng) {
        Throwable th;
        try {
            this.zzco.setCenter(latLng);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final LatLng getCenter() {
        Throwable th;
        try {
            return this.zzco.getCenter();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setRadius(double d) {
        Throwable th;
        try {
            this.zzco.setRadius(d);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final double getRadius() {
        Throwable th;
        try {
            return this.zzco.getRadius();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setStrokeWidth(float f) {
        Throwable th;
        try {
            this.zzco.setStrokeWidth(f);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final float getStrokeWidth() {
        Throwable th;
        try {
            return this.zzco.getStrokeWidth();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setStrokeColor(int i) {
        Throwable th;
        try {
            this.zzco.setStrokeColor(i);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final int getStrokeColor() {
        Throwable th;
        try {
            return this.zzco.getStrokeColor();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setStrokePattern(@Nullable List<PatternItem> list) {
        Throwable th;
        try {
            this.zzco.setStrokePattern(list);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    @Nullable
    public final List<PatternItem> getStrokePattern() {
        Throwable th;
        try {
            return PatternItem.zza(this.zzco.getStrokePattern());
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setFillColor(int i) {
        Throwable th;
        try {
            this.zzco.setFillColor(i);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final int getFillColor() {
        Throwable th;
        try {
            return this.zzco.getFillColor();
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
            this.zzco.setZIndex(f);
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
            return this.zzco.getZIndex();
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
            this.zzco.setVisible(z);
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
            return this.zzco.isVisible();
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
            this.zzco.setClickable(z);
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
            return this.zzco.isClickable();
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
            this.zzco.zze(ObjectWrapper.wrap(obj));
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
            return ObjectWrapper.unwrap(this.zzco.zzk());
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
        if (!(obj2 instanceof Circle)) {
            return false;
        }
        try {
            return this.zzco.zzb(((Circle) obj2).zzco);
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
            return this.zzco.zzj();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }
}
