package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzw;
import java.util.List;

public final class Polygon {
    private final zzw zzdw;

    public Polygon(zzw zzw) {
        this.zzdw = (zzw) Preconditions.checkNotNull(zzw);
    }

    public final void remove() {
        Throwable th;
        try {
            this.zzdw.remove();
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
            return this.zzdw.getId();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setPoints(List<LatLng> list) {
        Throwable th;
        try {
            this.zzdw.setPoints(list);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final List<LatLng> getPoints() {
        Throwable th;
        try {
            return this.zzdw.getPoints();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setHoles(List<? extends List<LatLng>> list) {
        Throwable th;
        try {
            this.zzdw.setHoles(list);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final List<List<LatLng>> getHoles() {
        Throwable th;
        try {
            return this.zzdw.getHoles();
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
            this.zzdw.setStrokeWidth(f);
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
            return this.zzdw.getStrokeWidth();
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
            this.zzdw.setStrokeColor(i);
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
            return this.zzdw.getStrokeColor();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setStrokeJointType(int i) {
        Throwable th;
        try {
            this.zzdw.setStrokeJointType(i);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final int getStrokeJointType() {
        Throwable th;
        try {
            return this.zzdw.getStrokeJointType();
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
            this.zzdw.setStrokePattern(list);
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
            return PatternItem.zza(this.zzdw.getStrokePattern());
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
            this.zzdw.setFillColor(i);
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
            return this.zzdw.getFillColor();
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
            this.zzdw.setZIndex(f);
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
            return this.zzdw.getZIndex();
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
            this.zzdw.setVisible(z);
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
            return this.zzdw.isVisible();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setGeodesic(boolean z) {
        Throwable th;
        try {
            this.zzdw.setGeodesic(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isGeodesic() {
        Throwable th;
        try {
            return this.zzdw.isGeodesic();
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
            this.zzdw.setClickable(z);
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
            return this.zzdw.isClickable();
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
            this.zzdw.zze(ObjectWrapper.wrap(obj));
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
            return ObjectWrapper.unwrap(this.zzdw.zzk());
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
        if (!(obj2 instanceof Polygon)) {
            return false;
        }
        try {
            return this.zzdw.zzb(((Polygon) obj2).zzdw);
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
            return this.zzdw.zzj();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }
}
