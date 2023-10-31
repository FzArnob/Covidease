package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzac;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import com.google.android.gms.maps.internal.zzab;
import com.google.android.gms.maps.internal.zzad;
import com.google.android.gms.maps.internal.zzaf;
import com.google.android.gms.maps.internal.zzaj;
import com.google.android.gms.maps.internal.zzal;
import com.google.android.gms.maps.internal.zzan;
import com.google.android.gms.maps.internal.zzar;
import com.google.android.gms.maps.internal.zzat;
import com.google.android.gms.maps.internal.zzav;
import com.google.android.gms.maps.internal.zzax;
import com.google.android.gms.maps.internal.zzaz;
import com.google.android.gms.maps.internal.zzbb;
import com.google.android.gms.maps.internal.zzbd;
import com.google.android.gms.maps.internal.zzbf;
import com.google.android.gms.maps.internal.zzbs;
import com.google.android.gms.maps.internal.zzc;
import com.google.android.gms.maps.internal.zzd;
import com.google.android.gms.maps.internal.zzh;
import com.google.android.gms.maps.internal.zzl;
import com.google.android.gms.maps.internal.zzp;
import com.google.android.gms.maps.internal.zzr;
import com.google.android.gms.maps.internal.zzv;
import com.google.android.gms.maps.internal.zzx;
import com.google.android.gms.maps.internal.zzz;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;

public final class GoogleMap {
    public static final int MAP_TYPE_HYBRID = 4;
    public static final int MAP_TYPE_NONE = 0;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    public static final int MAP_TYPE_TERRAIN = 3;
    private final IGoogleMapDelegate zzg;
    private UiSettings zzh;

    public interface CancelableCallback {
        void onCancel();

        void onFinish();
    }

    public interface InfoWindowAdapter {
        View getInfoContents(Marker marker);

        View getInfoWindow(Marker marker);
    }

    @Deprecated
    public interface OnCameraChangeListener {
        void onCameraChange(CameraPosition cameraPosition);
    }

    public interface OnCameraIdleListener {
        void onCameraIdle();
    }

    public interface OnCameraMoveCanceledListener {
        void onCameraMoveCanceled();
    }

    public interface OnCameraMoveListener {
        void onCameraMove();
    }

    public interface OnCameraMoveStartedListener {
        public static final int REASON_API_ANIMATION = 2;
        public static final int REASON_DEVELOPER_ANIMATION = 3;
        public static final int REASON_GESTURE = 1;

        void onCameraMoveStarted(int i);
    }

    public interface OnCircleClickListener {
        void onCircleClick(Circle circle);
    }

    public interface OnGroundOverlayClickListener {
        void onGroundOverlayClick(GroundOverlay groundOverlay);
    }

    public interface OnIndoorStateChangeListener {
        void onIndoorBuildingFocused();

        void onIndoorLevelActivated(IndoorBuilding indoorBuilding);
    }

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick(Marker marker);
    }

    public interface OnInfoWindowCloseListener {
        void onInfoWindowClose(Marker marker);
    }

    public interface OnInfoWindowLongClickListener {
        void onInfoWindowLongClick(Marker marker);
    }

    public interface OnMapClickListener {
        void onMapClick(LatLng latLng);
    }

    public interface OnMapLoadedCallback {
        void onMapLoaded();
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng latLng);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }

    public interface OnMarkerDragListener {
        void onMarkerDrag(Marker marker);

        void onMarkerDragEnd(Marker marker);

        void onMarkerDragStart(Marker marker);
    }

    public interface OnMyLocationButtonClickListener {
        boolean onMyLocationButtonClick();
    }

    @Deprecated
    public interface OnMyLocationChangeListener {
        void onMyLocationChange(Location location);
    }

    public interface OnMyLocationClickListener {
        void onMyLocationClick(@NonNull Location location);
    }

    public interface OnPoiClickListener {
        void onPoiClick(PointOfInterest pointOfInterest);
    }

    public interface OnPolygonClickListener {
        void onPolygonClick(Polygon polygon);
    }

    public interface OnPolylineClickListener {
        void onPolylineClick(Polyline polyline);
    }

    public interface SnapshotReadyCallback {
        void onSnapshotReady(Bitmap bitmap);
    }

    public GoogleMap(IGoogleMapDelegate iGoogleMapDelegate) {
        this.zzg = (IGoogleMapDelegate) Preconditions.checkNotNull(iGoogleMapDelegate);
    }

    private static final class zza extends zzd {
        private final CancelableCallback zzai;

        zza(CancelableCallback cancelableCallback) {
            this.zzai = cancelableCallback;
        }

        public final void onFinish() {
            this.zzai.onFinish();
        }

        public final void onCancel() {
            this.zzai.onCancel();
        }
    }

    public final CameraPosition getCameraPosition() {
        Throwable th;
        try {
            return this.zzg.getCameraPosition();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final float getMaxZoomLevel() {
        Throwable th;
        try {
            return this.zzg.getMaxZoomLevel();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final float getMinZoomLevel() {
        Throwable th;
        try {
            return this.zzg.getMinZoomLevel();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void moveCamera(CameraUpdate cameraUpdate) {
        Throwable th;
        try {
            this.zzg.moveCamera(cameraUpdate.zzb());
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate) {
        Throwable th;
        try {
            this.zzg.animateCamera(cameraUpdate.zzb());
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate, CancelableCallback cancelableCallback) {
        Throwable th;
        zzc zzc;
        zzc zzc2;
        CameraUpdate cameraUpdate2 = cameraUpdate;
        CancelableCallback cancelableCallback2 = cancelableCallback;
        try {
            IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
            IObjectWrapper zzb = cameraUpdate2.zzb();
            if (cancelableCallback2 == null) {
                zzc2 = null;
            } else {
                zzc2 = zzc;
                new zza(cancelableCallback2);
            }
            iGoogleMapDelegate.animateCameraWithCallback(zzb, zzc2);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate, int i, CancelableCallback cancelableCallback) {
        Throwable th;
        zzc zzc;
        zzc zzc2;
        CameraUpdate cameraUpdate2 = cameraUpdate;
        int i2 = i;
        CancelableCallback cancelableCallback2 = cancelableCallback;
        try {
            IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
            IObjectWrapper zzb = cameraUpdate2.zzb();
            int i3 = i2;
            if (cancelableCallback2 == null) {
                zzc2 = null;
            } else {
                zzc2 = zzc;
                new zza(cancelableCallback2);
            }
            iGoogleMapDelegate.animateCameraWithDurationAndCallback(zzb, i3, zzc2);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void stopAnimation() {
        Throwable th;
        try {
            this.zzg.stopAnimation();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final Polyline addPolyline(PolylineOptions polylineOptions) {
        Throwable th;
        Polyline polyline;
        try {
            Polyline polyline2 = polyline;
            new Polyline(this.zzg.addPolyline(polylineOptions));
            return polyline2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final Polygon addPolygon(PolygonOptions polygonOptions) {
        Throwable th;
        Polygon polygon;
        try {
            Polygon polygon2 = polygon;
            new Polygon(this.zzg.addPolygon(polygonOptions));
            return polygon2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final Circle addCircle(CircleOptions circleOptions) {
        Throwable th;
        Circle circle;
        try {
            Circle circle2 = circle;
            new Circle(this.zzg.addCircle(circleOptions));
            return circle2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final Marker addMarker(MarkerOptions markerOptions) {
        Throwable th;
        Marker marker;
        try {
            zzt addMarker = this.zzg.addMarker(markerOptions);
            zzt zzt = addMarker;
            if (addMarker == null) {
                return null;
            }
            Marker marker2 = marker;
            new Marker(zzt);
            return marker2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        Throwable th;
        GroundOverlay groundOverlay;
        try {
            zzk addGroundOverlay = this.zzg.addGroundOverlay(groundOverlayOptions);
            zzk zzk = addGroundOverlay;
            if (addGroundOverlay == null) {
                return null;
            }
            GroundOverlay groundOverlay2 = groundOverlay;
            new GroundOverlay(zzk);
            return groundOverlay2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) {
        Throwable th;
        TileOverlay tileOverlay;
        try {
            zzac addTileOverlay = this.zzg.addTileOverlay(tileOverlayOptions);
            zzac zzac = addTileOverlay;
            if (addTileOverlay == null) {
                return null;
            }
            TileOverlay tileOverlay2 = tileOverlay;
            new TileOverlay(zzac);
            return tileOverlay2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void clear() {
        Throwable th;
        try {
            this.zzg.clear();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final IndoorBuilding getFocusedBuilding() {
        Throwable th;
        IndoorBuilding indoorBuilding;
        try {
            zzn focusedBuilding = this.zzg.getFocusedBuilding();
            zzn zzn = focusedBuilding;
            if (focusedBuilding == null) {
                return null;
            }
            IndoorBuilding indoorBuilding2 = indoorBuilding;
            new IndoorBuilding(zzn);
            return indoorBuilding2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setOnIndoorStateChangeListener(OnIndoorStateChangeListener onIndoorStateChangeListener) {
        zzz zzz;
        Throwable th;
        OnIndoorStateChangeListener onIndoorStateChangeListener2 = onIndoorStateChangeListener;
        if (onIndoorStateChangeListener2 == null) {
            try {
                this.zzg.setOnIndoorStateChangeListener((zzz) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zza(this, onIndoorStateChangeListener2);
            this.zzg.setOnIndoorStateChangeListener(zzz);
        }
    }

    public final int getMapType() {
        Throwable th;
        try {
            return this.zzg.getMapType();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setMapType(int i) {
        Throwable th;
        try {
            this.zzg.setMapType(i);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isTrafficEnabled() {
        Throwable th;
        try {
            return this.zzg.isTrafficEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setTrafficEnabled(boolean z) {
        Throwable th;
        try {
            this.zzg.setTrafficEnabled(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isIndoorEnabled() {
        Throwable th;
        try {
            return this.zzg.isIndoorEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean setIndoorEnabled(boolean z) {
        Throwable th;
        try {
            return this.zzg.setIndoorEnabled(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isBuildingsEnabled() {
        Throwable th;
        try {
            return this.zzg.isBuildingsEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setBuildingsEnabled(boolean z) {
        Throwable th;
        try {
            this.zzg.setBuildingsEnabled(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final boolean isMyLocationEnabled() {
        Throwable th;
        try {
            return this.zzg.isMyLocationEnabled();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public final void setMyLocationEnabled(boolean z) {
        Throwable th;
        try {
            this.zzg.setMyLocationEnabled(z);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    @Deprecated
    public final Location getMyLocation() {
        Throwable th;
        try {
            return this.zzg.getMyLocation();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setLocationSource(LocationSource locationSource) {
        ILocationSourceDelegate iLocationSourceDelegate;
        Throwable th;
        LocationSource locationSource2 = locationSource;
        if (locationSource2 == null) {
            try {
                this.zzg.setLocationSource((ILocationSourceDelegate) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzl(this, locationSource2);
            this.zzg.setLocationSource(iLocationSourceDelegate);
        }
    }

    public final UiSettings getUiSettings() {
        Throwable th;
        UiSettings uiSettings;
        try {
            if (this.zzh == null) {
                new UiSettings(this.zzg.getUiSettings());
                this.zzh = uiSettings;
            }
            return this.zzh;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final Projection getProjection() {
        Throwable th;
        Projection projection;
        try {
            Projection projection2 = projection;
            new Projection(this.zzg.getProjection());
            return projection2;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    @Deprecated
    public final void setOnCameraChangeListener(@Nullable OnCameraChangeListener onCameraChangeListener) {
        zzl zzl;
        Throwable th;
        OnCameraChangeListener onCameraChangeListener2 = onCameraChangeListener;
        if (onCameraChangeListener2 == null) {
            try {
                this.zzg.setOnCameraChangeListener((zzl) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzt(this, onCameraChangeListener2);
            this.zzg.setOnCameraChangeListener(zzl);
        }
    }

    public final void setOnCameraMoveStartedListener(@Nullable OnCameraMoveStartedListener onCameraMoveStartedListener) {
        com.google.android.gms.maps.internal.zzt zzt;
        Throwable th;
        OnCameraMoveStartedListener onCameraMoveStartedListener2 = onCameraMoveStartedListener;
        if (onCameraMoveStartedListener2 == null) {
            try {
                this.zzg.setOnCameraMoveStartedListener((com.google.android.gms.maps.internal.zzt) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzu(this, onCameraMoveStartedListener2);
            this.zzg.setOnCameraMoveStartedListener(zzt);
        }
    }

    public final void setOnCameraMoveListener(@Nullable OnCameraMoveListener onCameraMoveListener) {
        zzr zzr;
        Throwable th;
        OnCameraMoveListener onCameraMoveListener2 = onCameraMoveListener;
        if (onCameraMoveListener2 == null) {
            try {
                this.zzg.setOnCameraMoveListener((zzr) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzv(this, onCameraMoveListener2);
            this.zzg.setOnCameraMoveListener(zzr);
        }
    }

    public final void setOnCameraMoveCanceledListener(@Nullable OnCameraMoveCanceledListener onCameraMoveCanceledListener) {
        zzp zzp;
        Throwable th;
        OnCameraMoveCanceledListener onCameraMoveCanceledListener2 = onCameraMoveCanceledListener;
        if (onCameraMoveCanceledListener2 == null) {
            try {
                this.zzg.setOnCameraMoveCanceledListener((zzp) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzw(this, onCameraMoveCanceledListener2);
            this.zzg.setOnCameraMoveCanceledListener(zzp);
        }
    }

    public final void setOnCameraIdleListener(@Nullable OnCameraIdleListener onCameraIdleListener) {
        com.google.android.gms.maps.internal.zzn zzn;
        Throwable th;
        OnCameraIdleListener onCameraIdleListener2 = onCameraIdleListener;
        if (onCameraIdleListener2 == null) {
            try {
                this.zzg.setOnCameraIdleListener((com.google.android.gms.maps.internal.zzn) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzx(this, onCameraIdleListener2);
            this.zzg.setOnCameraIdleListener(zzn);
        }
    }

    public final void setOnMapClickListener(@Nullable OnMapClickListener onMapClickListener) {
        zzaj zzaj;
        Throwable th;
        OnMapClickListener onMapClickListener2 = onMapClickListener;
        if (onMapClickListener2 == null) {
            try {
                this.zzg.setOnMapClickListener((zzaj) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzy(this, onMapClickListener2);
            this.zzg.setOnMapClickListener(zzaj);
        }
    }

    public final void setOnMapLongClickListener(@Nullable OnMapLongClickListener onMapLongClickListener) {
        zzan zzan;
        Throwable th;
        OnMapLongClickListener onMapLongClickListener2 = onMapLongClickListener;
        if (onMapLongClickListener2 == null) {
            try {
                this.zzg.setOnMapLongClickListener((zzan) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzz(this, onMapLongClickListener2);
            this.zzg.setOnMapLongClickListener(zzan);
        }
    }

    public final void setOnMarkerClickListener(@Nullable OnMarkerClickListener onMarkerClickListener) {
        zzar zzar;
        Throwable th;
        OnMarkerClickListener onMarkerClickListener2 = onMarkerClickListener;
        if (onMarkerClickListener2 == null) {
            try {
                this.zzg.setOnMarkerClickListener((zzar) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzb(this, onMarkerClickListener2);
            this.zzg.setOnMarkerClickListener(zzar);
        }
    }

    public final void setOnMarkerDragListener(@Nullable OnMarkerDragListener onMarkerDragListener) {
        zzat zzat;
        Throwable th;
        OnMarkerDragListener onMarkerDragListener2 = onMarkerDragListener;
        if (onMarkerDragListener2 == null) {
            try {
                this.zzg.setOnMarkerDragListener((zzat) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzc(this, onMarkerDragListener2);
            this.zzg.setOnMarkerDragListener(zzat);
        }
    }

    public final void setOnInfoWindowClickListener(@Nullable OnInfoWindowClickListener onInfoWindowClickListener) {
        zzab zzab;
        Throwable th;
        OnInfoWindowClickListener onInfoWindowClickListener2 = onInfoWindowClickListener;
        if (onInfoWindowClickListener2 == null) {
            try {
                this.zzg.setOnInfoWindowClickListener((zzab) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzd(this, onInfoWindowClickListener2);
            this.zzg.setOnInfoWindowClickListener(zzab);
        }
    }

    public final void setOnInfoWindowLongClickListener(@Nullable OnInfoWindowLongClickListener onInfoWindowLongClickListener) {
        zzaf zzaf;
        Throwable th;
        OnInfoWindowLongClickListener onInfoWindowLongClickListener2 = onInfoWindowLongClickListener;
        if (onInfoWindowLongClickListener2 == null) {
            try {
                this.zzg.setOnInfoWindowLongClickListener((zzaf) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zze(this, onInfoWindowLongClickListener2);
            this.zzg.setOnInfoWindowLongClickListener(zzaf);
        }
    }

    public final void setOnInfoWindowCloseListener(@Nullable OnInfoWindowCloseListener onInfoWindowCloseListener) {
        zzad zzad;
        Throwable th;
        OnInfoWindowCloseListener onInfoWindowCloseListener2 = onInfoWindowCloseListener;
        if (onInfoWindowCloseListener2 == null) {
            try {
                this.zzg.setOnInfoWindowCloseListener((zzad) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzf(this, onInfoWindowCloseListener2);
            this.zzg.setOnInfoWindowCloseListener(zzad);
        }
    }

    public final void setInfoWindowAdapter(InfoWindowAdapter infoWindowAdapter) {
        zzh zzh2;
        Throwable th;
        InfoWindowAdapter infoWindowAdapter2 = infoWindowAdapter;
        if (infoWindowAdapter2 == null) {
            try {
                this.zzg.setInfoWindowAdapter((zzh) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzg(this, infoWindowAdapter2);
            this.zzg.setInfoWindowAdapter(zzh2);
        }
    }

    @Deprecated
    public final void setOnMyLocationChangeListener(@Nullable OnMyLocationChangeListener onMyLocationChangeListener) {
        zzax zzax;
        Throwable th;
        OnMyLocationChangeListener onMyLocationChangeListener2 = onMyLocationChangeListener;
        if (onMyLocationChangeListener2 == null) {
            try {
                this.zzg.setOnMyLocationChangeListener((zzax) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzh(this, onMyLocationChangeListener2);
            this.zzg.setOnMyLocationChangeListener(zzax);
        }
    }

    public final void setOnMyLocationButtonClickListener(@Nullable OnMyLocationButtonClickListener onMyLocationButtonClickListener) {
        zzav zzav;
        Throwable th;
        OnMyLocationButtonClickListener onMyLocationButtonClickListener2 = onMyLocationButtonClickListener;
        if (onMyLocationButtonClickListener2 == null) {
            try {
                this.zzg.setOnMyLocationButtonClickListener((zzav) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzi(this, onMyLocationButtonClickListener2);
            this.zzg.setOnMyLocationButtonClickListener(zzav);
        }
    }

    public final void setOnMyLocationClickListener(@Nullable OnMyLocationClickListener onMyLocationClickListener) {
        zzaz zzaz;
        Throwable th;
        OnMyLocationClickListener onMyLocationClickListener2 = onMyLocationClickListener;
        if (onMyLocationClickListener2 == null) {
            try {
                this.zzg.setOnMyLocationClickListener((zzaz) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzj(this, onMyLocationClickListener2);
            this.zzg.setOnMyLocationClickListener(zzaz);
        }
    }

    public final void setOnMapLoadedCallback(OnMapLoadedCallback onMapLoadedCallback) {
        zzal zzal;
        Throwable th;
        OnMapLoadedCallback onMapLoadedCallback2 = onMapLoadedCallback;
        if (onMapLoadedCallback2 == null) {
            try {
                this.zzg.setOnMapLoadedCallback((zzal) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzk(this, onMapLoadedCallback2);
            this.zzg.setOnMapLoadedCallback(zzal);
        }
    }

    public final void setOnGroundOverlayClickListener(OnGroundOverlayClickListener onGroundOverlayClickListener) {
        zzx zzx;
        Throwable th;
        OnGroundOverlayClickListener onGroundOverlayClickListener2 = onGroundOverlayClickListener;
        if (onGroundOverlayClickListener2 == null) {
            try {
                this.zzg.setOnGroundOverlayClickListener((zzx) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzn(this, onGroundOverlayClickListener2);
            this.zzg.setOnGroundOverlayClickListener(zzx);
        }
    }

    public final void setOnCircleClickListener(OnCircleClickListener onCircleClickListener) {
        zzv zzv;
        Throwable th;
        OnCircleClickListener onCircleClickListener2 = onCircleClickListener;
        if (onCircleClickListener2 == null) {
            try {
                this.zzg.setOnCircleClickListener((zzv) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzo(this, onCircleClickListener2);
            this.zzg.setOnCircleClickListener(zzv);
        }
    }

    public final void setOnPolygonClickListener(OnPolygonClickListener onPolygonClickListener) {
        zzbd zzbd;
        Throwable th;
        OnPolygonClickListener onPolygonClickListener2 = onPolygonClickListener;
        if (onPolygonClickListener2 == null) {
            try {
                this.zzg.setOnPolygonClickListener((zzbd) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzp(this, onPolygonClickListener2);
            this.zzg.setOnPolygonClickListener(zzbd);
        }
    }

    public final void setOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener) {
        zzbf zzbf;
        Throwable th;
        OnPolylineClickListener onPolylineClickListener2 = onPolylineClickListener;
        if (onPolylineClickListener2 == null) {
            try {
                this.zzg.setOnPolylineClickListener((zzbf) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzq(this, onPolylineClickListener2);
            this.zzg.setOnPolylineClickListener(zzbf);
        }
    }

    public final void snapshot(SnapshotReadyCallback snapshotReadyCallback) {
        snapshot(snapshotReadyCallback, (Bitmap) null);
    }

    public final void snapshot(SnapshotReadyCallback snapshotReadyCallback, Bitmap bitmap) {
        Throwable th;
        zzbs zzbs;
        SnapshotReadyCallback snapshotReadyCallback2 = snapshotReadyCallback;
        Bitmap bitmap2 = bitmap;
        ObjectWrapper objectWrapper = (ObjectWrapper) (bitmap2 != null ? ObjectWrapper.wrap(bitmap2) : null);
        try {
            new zzr(this, snapshotReadyCallback2);
            this.zzg.snapshot(zzbs, objectWrapper);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setPadding(int i, int i2, int i3, int i4) {
        Throwable th;
        try {
            this.zzg.setPadding(i, i2, i3, i4);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setContentDescription(String str) {
        Throwable th;
        try {
            this.zzg.setContentDescription(str);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setOnPoiClickListener(OnPoiClickListener onPoiClickListener) {
        zzbb zzbb;
        Throwable th;
        OnPoiClickListener onPoiClickListener2 = onPoiClickListener;
        if (onPoiClickListener2 == null) {
            try {
                this.zzg.setOnPoiClickListener((zzbb) null);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Throwable th2 = th;
                new RuntimeRemoteException(remoteException);
                throw th2;
            }
        } else {
            new zzs(this, onPoiClickListener2);
            this.zzg.setOnPoiClickListener(zzbb);
        }
    }

    public final boolean setMapStyle(@Nullable MapStyleOptions mapStyleOptions) {
        Throwable th;
        try {
            return this.zzg.setMapStyle(mapStyleOptions);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setMinZoomPreference(float f) {
        Throwable th;
        try {
            this.zzg.setMinZoomPreference(f);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setMaxZoomPreference(float f) {
        Throwable th;
        try {
            this.zzg.setMaxZoomPreference(f);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void resetMinMaxZoomPreference() {
        Throwable th;
        try {
            this.zzg.resetMinMaxZoomPreference();
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }

    public final void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) {
        Throwable th;
        try {
            this.zzg.setLatLngBoundsForCameraTarget(latLngBounds);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            Throwable th2 = th;
            new RuntimeRemoteException(remoteException);
            throw th2;
        }
    }
}
