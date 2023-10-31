package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

@SafeParcelable.Class(creator = "GoogleMapOptionsCreator")
@SafeParcelable.Reserved({1})
public final class GoogleMapOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleMapOptions> CREATOR;
    @SafeParcelable.Field(getter = "getMapType", mo25277id = 4)
    private int mapType = -1;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getZOrderOnTopForParcel", mo25277id = 2, type = "byte")
    private Boolean zzaj;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getUseViewLifecycleInFragmentForParcel", mo25277id = 3, type = "byte")
    private Boolean zzak;
    @SafeParcelable.Field(getter = "getCamera", mo25277id = 5)
    private CameraPosition zzal;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getZoomControlsEnabledForParcel", mo25277id = 6, type = "byte")
    private Boolean zzam;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getCompassEnabledForParcel", mo25277id = 7, type = "byte")
    private Boolean zzan;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getScrollGesturesEnabledForParcel", mo25277id = 8, type = "byte")
    private Boolean zzao;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getZoomGesturesEnabledForParcel", mo25277id = 9, type = "byte")
    private Boolean zzap;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getTiltGesturesEnabledForParcel", mo25277id = 10, type = "byte")
    private Boolean zzaq;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getRotateGesturesEnabledForParcel", mo25277id = 11, type = "byte")
    private Boolean zzar;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getLiteModeForParcel", mo25277id = 12, type = "byte")
    private Boolean zzas;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getMapToolbarEnabledForParcel", mo25277id = 14, type = "byte")
    private Boolean zzat;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getAmbientEnabledForParcel", mo25277id = 15, type = "byte")
    private Boolean zzau;
    @SafeParcelable.Field(getter = "getMinZoomPreference", mo25277id = 16)
    private Float zzav = null;
    @SafeParcelable.Field(getter = "getMaxZoomPreference", mo25277id = 17)
    private Float zzaw = null;
    @SafeParcelable.Field(getter = "getLatLngBoundsForCameraTarget", mo25277id = 18)
    private LatLngBounds zzax = null;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getScrollGesturesEnabledDuringRotateOrZoomForParcel", mo25277id = 19, type = "byte")
    private Boolean zzay;

    @SafeParcelable.Constructor
    GoogleMapOptions(@SafeParcelable.Param(mo25280id = 2) byte b, @SafeParcelable.Param(mo25280id = 3) byte b2, @SafeParcelable.Param(mo25280id = 4) int i, @SafeParcelable.Param(mo25280id = 5) CameraPosition cameraPosition, @SafeParcelable.Param(mo25280id = 6) byte b3, @SafeParcelable.Param(mo25280id = 7) byte b4, @SafeParcelable.Param(mo25280id = 8) byte b5, @SafeParcelable.Param(mo25280id = 9) byte b6, @SafeParcelable.Param(mo25280id = 10) byte b7, @SafeParcelable.Param(mo25280id = 11) byte b8, @SafeParcelable.Param(mo25280id = 12) byte b9, @SafeParcelable.Param(mo25280id = 14) byte b10, @SafeParcelable.Param(mo25280id = 15) byte b11, @SafeParcelable.Param(mo25280id = 16) Float f, @SafeParcelable.Param(mo25280id = 17) Float f2, @SafeParcelable.Param(mo25280id = 18) LatLngBounds latLngBounds, @SafeParcelable.Param(mo25280id = 19) byte b12) {
        this.zzaj = zza.zza(b);
        this.zzak = zza.zza(b2);
        this.mapType = i;
        this.zzal = cameraPosition;
        this.zzam = zza.zza(b3);
        this.zzan = zza.zza(b4);
        this.zzao = zza.zza(b5);
        this.zzap = zza.zza(b6);
        this.zzaq = zza.zza(b7);
        this.zzar = zza.zza(b8);
        this.zzas = zza.zza(b9);
        this.zzat = zza.zza(b10);
        this.zzau = zza.zza(b11);
        this.zzav = f;
        this.zzaw = f2;
        this.zzax = latLngBounds;
        this.zzay = zza.zza(b12);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeByte(parcel2, 2, zza.zza(this.zzaj));
        SafeParcelWriter.writeByte(parcel2, 3, zza.zza(this.zzak));
        SafeParcelWriter.writeInt(parcel2, 4, getMapType());
        SafeParcelWriter.writeParcelable(parcel2, 5, getCamera(), i2, false);
        SafeParcelWriter.writeByte(parcel2, 6, zza.zza(this.zzam));
        SafeParcelWriter.writeByte(parcel2, 7, zza.zza(this.zzan));
        SafeParcelWriter.writeByte(parcel2, 8, zza.zza(this.zzao));
        SafeParcelWriter.writeByte(parcel2, 9, zza.zza(this.zzap));
        SafeParcelWriter.writeByte(parcel2, 10, zza.zza(this.zzaq));
        SafeParcelWriter.writeByte(parcel2, 11, zza.zza(this.zzar));
        SafeParcelWriter.writeByte(parcel2, 12, zza.zza(this.zzas));
        SafeParcelWriter.writeByte(parcel2, 14, zza.zza(this.zzat));
        SafeParcelWriter.writeByte(parcel2, 15, zza.zza(this.zzau));
        SafeParcelWriter.writeFloatObject(parcel2, 16, getMinZoomPreference(), false);
        SafeParcelWriter.writeFloatObject(parcel2, 17, getMaxZoomPreference(), false);
        SafeParcelWriter.writeParcelable(parcel2, 18, getLatLngBoundsForCameraTarget(), i2, false);
        SafeParcelWriter.writeByte(parcel2, 19, zza.zza(this.zzay));
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public GoogleMapOptions() {
    }

    public final GoogleMapOptions zOrderOnTop(boolean z) {
        this.zzaj = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions useViewLifecycleInFragment(boolean z) {
        this.zzak = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions mapType(int i) {
        this.mapType = i;
        return this;
    }

    public final GoogleMapOptions camera(CameraPosition cameraPosition) {
        this.zzal = cameraPosition;
        return this;
    }

    public final GoogleMapOptions zoomControlsEnabled(boolean z) {
        this.zzam = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions compassEnabled(boolean z) {
        this.zzan = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions scrollGesturesEnabled(boolean z) {
        this.zzao = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions zoomGesturesEnabled(boolean z) {
        this.zzap = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions tiltGesturesEnabled(boolean z) {
        this.zzaq = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions rotateGesturesEnabled(boolean z) {
        this.zzar = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions scrollGesturesEnabledDuringRotateOrZoom(boolean z) {
        this.zzay = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions liteMode(boolean z) {
        this.zzas = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions mapToolbarEnabled(boolean z) {
        this.zzat = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions ambientEnabled(boolean z) {
        this.zzau = Boolean.valueOf(z);
        return this;
    }

    public final GoogleMapOptions minZoomPreference(float f) {
        this.zzav = Float.valueOf(f);
        return this;
    }

    public final GoogleMapOptions maxZoomPreference(float f) {
        this.zzaw = Float.valueOf(f);
        return this;
    }

    public final GoogleMapOptions latLngBoundsForCameraTarget(LatLngBounds latLngBounds) {
        this.zzax = latLngBounds;
        return this;
    }

    public final Boolean getZOrderOnTop() {
        return this.zzaj;
    }

    public final Boolean getUseViewLifecycleInFragment() {
        return this.zzak;
    }

    public final int getMapType() {
        return this.mapType;
    }

    public final CameraPosition getCamera() {
        return this.zzal;
    }

    public final Boolean getZoomControlsEnabled() {
        return this.zzam;
    }

    public final Boolean getCompassEnabled() {
        return this.zzan;
    }

    public final Boolean getScrollGesturesEnabled() {
        return this.zzao;
    }

    public final Boolean getZoomGesturesEnabled() {
        return this.zzap;
    }

    public final Boolean getTiltGesturesEnabled() {
        return this.zzaq;
    }

    public final Boolean getRotateGesturesEnabled() {
        return this.zzar;
    }

    public final Boolean getScrollGesturesEnabledDuringRotateOrZoom() {
        return this.zzay;
    }

    public final Boolean getLiteMode() {
        return this.zzas;
    }

    public final Boolean getMapToolbarEnabled() {
        return this.zzat;
    }

    public final Boolean getAmbientEnabled() {
        return this.zzau;
    }

    public final Float getMinZoomPreference() {
        return this.zzav;
    }

    public final Float getMaxZoomPreference() {
        return this.zzaw;
    }

    public final LatLngBounds getLatLngBoundsForCameraTarget() {
        return this.zzax;
    }

    public static GoogleMapOptions createFromAttributes(Context context, AttributeSet attributeSet) {
        GoogleMapOptions googleMapOptions;
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        if (context2 == null || attributeSet2 == null) {
            return null;
        }
        TypedArray obtainAttributes = context2.getResources().obtainAttributes(attributeSet2, C0556R.styleable.MapAttrs);
        new GoogleMapOptions();
        GoogleMapOptions googleMapOptions2 = googleMapOptions;
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_mapType)) {
            GoogleMapOptions mapType2 = googleMapOptions2.mapType(obtainAttributes.getInt(C0556R.styleable.MapAttrs_mapType, -1));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_zOrderOnTop)) {
            GoogleMapOptions zOrderOnTop = googleMapOptions2.zOrderOnTop(obtainAttributes.getBoolean(C0556R.styleable.MapAttrs_zOrderOnTop, false));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_useViewLifecycle)) {
            GoogleMapOptions useViewLifecycleInFragment = googleMapOptions2.useViewLifecycleInFragment(obtainAttributes.getBoolean(C0556R.styleable.MapAttrs_useViewLifecycle, false));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_uiCompass)) {
            GoogleMapOptions compassEnabled = googleMapOptions2.compassEnabled(obtainAttributes.getBoolean(C0556R.styleable.MapAttrs_uiCompass, true));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_uiRotateGestures)) {
            GoogleMapOptions rotateGesturesEnabled = googleMapOptions2.rotateGesturesEnabled(obtainAttributes.getBoolean(C0556R.styleable.MapAttrs_uiRotateGestures, true));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_uiScrollGesturesDuringRotateOrZoom)) {
            GoogleMapOptions scrollGesturesEnabledDuringRotateOrZoom = googleMapOptions2.scrollGesturesEnabledDuringRotateOrZoom(obtainAttributes.getBoolean(C0556R.styleable.MapAttrs_uiScrollGesturesDuringRotateOrZoom, true));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_uiScrollGestures)) {
            GoogleMapOptions scrollGesturesEnabled = googleMapOptions2.scrollGesturesEnabled(obtainAttributes.getBoolean(C0556R.styleable.MapAttrs_uiScrollGestures, true));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_uiTiltGestures)) {
            GoogleMapOptions tiltGesturesEnabled = googleMapOptions2.tiltGesturesEnabled(obtainAttributes.getBoolean(C0556R.styleable.MapAttrs_uiTiltGestures, true));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_uiZoomGestures)) {
            GoogleMapOptions zoomGesturesEnabled = googleMapOptions2.zoomGesturesEnabled(obtainAttributes.getBoolean(C0556R.styleable.MapAttrs_uiZoomGestures, true));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_uiZoomControls)) {
            GoogleMapOptions zoomControlsEnabled = googleMapOptions2.zoomControlsEnabled(obtainAttributes.getBoolean(C0556R.styleable.MapAttrs_uiZoomControls, true));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_liteMode)) {
            GoogleMapOptions liteMode = googleMapOptions2.liteMode(obtainAttributes.getBoolean(C0556R.styleable.MapAttrs_liteMode, false));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_uiMapToolbar)) {
            GoogleMapOptions mapToolbarEnabled = googleMapOptions2.mapToolbarEnabled(obtainAttributes.getBoolean(C0556R.styleable.MapAttrs_uiMapToolbar, true));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_ambientEnabled)) {
            GoogleMapOptions ambientEnabled = googleMapOptions2.ambientEnabled(obtainAttributes.getBoolean(C0556R.styleable.MapAttrs_ambientEnabled, false));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_cameraMinZoomPreference)) {
            GoogleMapOptions minZoomPreference = googleMapOptions2.minZoomPreference(obtainAttributes.getFloat(C0556R.styleable.MapAttrs_cameraMinZoomPreference, Float.NEGATIVE_INFINITY));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_cameraMinZoomPreference)) {
            GoogleMapOptions maxZoomPreference = googleMapOptions2.maxZoomPreference(obtainAttributes.getFloat(C0556R.styleable.MapAttrs_cameraMaxZoomPreference, Float.POSITIVE_INFINITY));
        }
        GoogleMapOptions latLngBoundsForCameraTarget = googleMapOptions2.latLngBoundsForCameraTarget(zza(context2, attributeSet2));
        GoogleMapOptions camera = googleMapOptions2.camera(zzb(context2, attributeSet2));
        obtainAttributes.recycle();
        return googleMapOptions2;
    }

    public static LatLngBounds zza(Context context, AttributeSet attributeSet) {
        LatLng latLng;
        LatLng latLng2;
        LatLngBounds latLngBounds;
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        if (context2 == null || attributeSet2 == null) {
            return null;
        }
        TypedArray obtainAttributes = context2.getResources().obtainAttributes(attributeSet2, C0556R.styleable.MapAttrs);
        Float f = null;
        Float f2 = null;
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_latLngBoundsSouthWestLatitude)) {
            f = Float.valueOf(obtainAttributes.getFloat(C0556R.styleable.MapAttrs_latLngBoundsSouthWestLatitude, 0.0f));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_latLngBoundsSouthWestLongitude)) {
            f2 = Float.valueOf(obtainAttributes.getFloat(C0556R.styleable.MapAttrs_latLngBoundsSouthWestLongitude, 0.0f));
        }
        Float f3 = null;
        Float f4 = null;
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_latLngBoundsNorthEastLatitude)) {
            f3 = Float.valueOf(obtainAttributes.getFloat(C0556R.styleable.MapAttrs_latLngBoundsNorthEastLatitude, 0.0f));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_latLngBoundsNorthEastLongitude)) {
            f4 = Float.valueOf(obtainAttributes.getFloat(C0556R.styleable.MapAttrs_latLngBoundsNorthEastLongitude, 0.0f));
        }
        obtainAttributes.recycle();
        if (f == null || f2 == null || f3 == null || f4 == null) {
            return null;
        }
        new LatLng((double) f.floatValue(), (double) f2.floatValue());
        new LatLng((double) f3.floatValue(), (double) f4.floatValue());
        new LatLngBounds(latLng, latLng2);
        return latLngBounds;
    }

    public static CameraPosition zzb(Context context, AttributeSet attributeSet) {
        LatLng latLng;
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        if (context2 == null || attributeSet2 == null) {
            return null;
        }
        TypedArray obtainAttributes = context2.getResources().obtainAttributes(attributeSet2, C0556R.styleable.MapAttrs);
        float f = 0.0f;
        float f2 = 0.0f;
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_cameraTargetLat)) {
            f = obtainAttributes.getFloat(C0556R.styleable.MapAttrs_cameraTargetLat, 0.0f);
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_cameraTargetLng)) {
            f2 = obtainAttributes.getFloat(C0556R.styleable.MapAttrs_cameraTargetLng, 0.0f);
        }
        new LatLng((double) f, (double) f2);
        LatLng latLng2 = latLng;
        CameraPosition.Builder builder = CameraPosition.builder();
        CameraPosition.Builder builder2 = builder;
        CameraPosition.Builder target = builder.target(latLng2);
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_cameraZoom)) {
            CameraPosition.Builder zoom = builder2.zoom(obtainAttributes.getFloat(C0556R.styleable.MapAttrs_cameraZoom, 0.0f));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_cameraBearing)) {
            CameraPosition.Builder bearing = builder2.bearing(obtainAttributes.getFloat(C0556R.styleable.MapAttrs_cameraBearing, 0.0f));
        }
        if (obtainAttributes.hasValue(C0556R.styleable.MapAttrs_cameraTilt)) {
            CameraPosition.Builder tilt = builder2.tilt(obtainAttributes.getFloat(C0556R.styleable.MapAttrs_cameraTilt, 0.0f));
        }
        obtainAttributes.recycle();
        return builder2.build();
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("MapType", Integer.valueOf(this.mapType)).add("LiteMode", this.zzas).add("Camera", this.zzal).add("CompassEnabled", this.zzan).add("ZoomControlsEnabled", this.zzam).add("ScrollGesturesEnabled", this.zzao).add("ZoomGesturesEnabled", this.zzap).add("TiltGesturesEnabled", this.zzaq).add("RotateGesturesEnabled", this.zzar).add("ScrollGesturesEnabledDuringRotateOrZoom", this.zzay).add("MapToolbarEnabled", this.zzat).add("AmbientEnabled", this.zzau).add("MinZoomPreference", this.zzav).add("MaxZoomPreference", this.zzaw).add("LatLngBoundsForCameraTarget", this.zzax).add("ZOrderOnTop", this.zzaj).add("UseViewLifecycleInFragment", this.zzak).toString();
    }

    static {
        Parcelable.Creator<GoogleMapOptions> creator;
        new zzaa();
        CREATOR = creator;
    }
}
