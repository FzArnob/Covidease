package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class UiSettings {
    private final IUiSettingsDelegate zzcj;

    UiSettings(IUiSettingsDelegate iUiSettingsDelegate) {
        this.zzcj = iUiSettingsDelegate;
    }

    public final void setZoomControlsEnabled(boolean z) {
        Throwable th;
        try {
            this.zzcj.setZoomControlsEnabled(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setCompassEnabled(boolean z) {
        Throwable th;
        try {
            this.zzcj.setCompassEnabled(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setMyLocationButtonEnabled(boolean z) {
        Throwable th;
        try {
            this.zzcj.setMyLocationButtonEnabled(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setIndoorLevelPickerEnabled(boolean z) {
        Throwable th;
        try {
            this.zzcj.setIndoorLevelPickerEnabled(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setScrollGesturesEnabled(boolean z) {
        Throwable th;
        try {
            this.zzcj.setScrollGesturesEnabled(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setZoomGesturesEnabled(boolean z) {
        Throwable th;
        try {
            this.zzcj.setZoomGesturesEnabled(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setTiltGesturesEnabled(boolean z) {
        Throwable th;
        try {
            this.zzcj.setTiltGesturesEnabled(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setRotateGesturesEnabled(boolean z) {
        Throwable th;
        try {
            this.zzcj.setRotateGesturesEnabled(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setScrollGesturesEnabledDuringRotateOrZoom(boolean z) {
        Throwable th;
        try {
            this.zzcj.setScrollGesturesEnabledDuringRotateOrZoom(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setAllGesturesEnabled(boolean z) {
        Throwable th;
        try {
            this.zzcj.setAllGesturesEnabled(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setMapToolbarEnabled(boolean z) {
        Throwable th;
        try {
            this.zzcj.setMapToolbarEnabled(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isZoomControlsEnabled() {
        Throwable th;
        try {
            return this.zzcj.isZoomControlsEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isCompassEnabled() {
        Throwable th;
        try {
            return this.zzcj.isCompassEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isMyLocationButtonEnabled() {
        Throwable th;
        try {
            return this.zzcj.isMyLocationButtonEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isIndoorLevelPickerEnabled() {
        Throwable th;
        try {
            return this.zzcj.isIndoorLevelPickerEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isScrollGesturesEnabled() {
        Throwable th;
        try {
            return this.zzcj.isScrollGesturesEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isScrollGesturesEnabledDuringRotateOrZoom() {
        Throwable th;
        try {
            return this.zzcj.isScrollGesturesEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isZoomGesturesEnabled() {
        Throwable th;
        try {
            return this.zzcj.isZoomGesturesEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isTiltGesturesEnabled() {
        Throwable th;
        try {
            return this.zzcj.isTiltGesturesEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isRotateGesturesEnabled() {
        Throwable th;
        try {
            return this.zzcj.isRotateGesturesEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isMapToolbarEnabled() {
        Throwable th;
        try {
            return this.zzcj.isMapToolbarEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }
}
