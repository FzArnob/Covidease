package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzac;

public final class TileOverlay {
    private final zzac zzeh;

    public TileOverlay(zzac zzac) {
        this.zzeh = (zzac) Preconditions.checkNotNull(zzac);
    }

    public final void remove() {
        Throwable th;
        try {
            this.zzeh.remove();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void clearTileCache() {
        Throwable th;
        try {
            this.zzeh.clearTileCache();
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
            return this.zzeh.getId();
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
            this.zzeh.setZIndex(f);
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
            return this.zzeh.getZIndex();
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
            this.zzeh.setVisible(z);
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
            return this.zzeh.isVisible();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setFadeIn(boolean z) {
        Throwable th;
        try {
            this.zzeh.setFadeIn(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean getFadeIn() {
        Throwable th;
        try {
            return this.zzeh.getFadeIn();
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
            this.zzeh.setTransparency(f);
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
            return this.zzeh.getTransparency();
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
        if (!(obj2 instanceof TileOverlay)) {
            return false;
        }
        try {
            return this.zzeh.zza(((TileOverlay) obj2).zzeh);
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
            return this.zzeh.zzj();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }
}
