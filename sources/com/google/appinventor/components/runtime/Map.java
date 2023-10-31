package com.google.appinventor.components.runtime;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesAssets;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.LocationSensor;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.GeoJSONUtil;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.osmdroid.util.BoundingBox;

@DesignerComponent(category = ComponentCategory.MAPS, description = "<p>A two-dimensional container that renders map tiles in the background and allows for multiple Marker elements to identify points on the map. Map tiles are supplied by OpenStreetMap contributors and the United States Geological Survey.</p><p>The Map component provides three utilities for manipulating its boundaries within App Inventor. First, a locking mechanism is provided to allow the map to be moved relative to other components on the Screen. Second, when unlocked, the user can pan the Map to any location. At this new location, the &quot;Set Initial Boundary&quot; button can be pressed to save the current Map coordinates to its properties. Lastly, if the Map is moved to a different location, for example to add Markers off-screen, then the &quot;Reset Map to Initial Bounds&quot; button can be used to re-center the Map at the starting location.</p>", version = 5)
@UsesLibraries(libraries = "osmdroid.aar, osmdroid.jar, androidsvg.jar, jts.jar")
@SimpleObject
@UsesAssets(fileNames = "location.png, marker.svg")
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_COARSE_LOCATION, android.permission.ACCESS_WIFI_STATE, android.permission.ACCESS_NETWORK_STATE, android.permission.WRITE_EXTERNAL_STORAGE, android.permission.READ_EXTERNAL_STORAGE")
public class Map extends MapFeatureContainerBase implements MapFactory.MapEventListener {
    private static final String TAG = Map.class.getSimpleName();
    private final Handler androidUIHandler;
    /* access modifiers changed from: private */
    public Form form;
    private boolean havePermission = false;
    private MapFactory.MapController hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
    private LocationSensor wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = null;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Map(com.google.appinventor.components.runtime.ComponentContainer r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            r3 = 0
            r2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = r3
            r2 = r0
            r3 = 0
            r2.havePermission = r3
            r2 = r0
            android.os.Handler r3 = new android.os.Handler
            r7 = r3
            r3 = r7
            r4 = r7
            r4.<init>()
            r2.androidUIHandler = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            java.lang.String r2 = TAG
            java.lang.String r3 = "Map.<init>"
            int r2 = android.util.Log.d(r2, r3)
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r0
            r3 = 176(0xb0, float:2.47E-43)
            r2.Width(r3)
            r2 = r0
            r3 = 144(0x90, float:2.02E-43)
            r2.Height(r3)
            r2 = r0
            java.lang.String r3 = "42.359144, -71.093612"
            r2.CenterFromString(r3)
            r2 = r0
            r3 = 13
            r2.ZoomLevel(r3)
            r2 = r0
            r3 = 1
            r2.EnableZoom(r3)
            r2 = r0
            r3 = 1
            r2.EnablePan(r3)
            r2 = r0
            r3 = 1
            r2.MapType(r3)
            r2 = r0
            r3 = 0
            r2.ShowCompass(r3)
            r2 = r0
            com.google.appinventor.components.runtime.LocationSensor r3 = new com.google.appinventor.components.runtime.LocationSensor
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r1
            com.google.appinventor.components.runtime.Form r5 = r5.$form()
            r6 = 0
            r4.<init>(r5, r6)
            r2.LocationSensor(r3)
            r2 = r0
            r3 = 0
            r2.ShowUser(r3)
            r2 = r0
            r3 = 0
            r2.ShowZoom(r3)
            r2 = r0
            r3 = 0
            r2.EnableRotation(r3)
            r2 = r0
            r3 = 0
            r2.ShowScale(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Map.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public View getView() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = MapFactory.newMap(this.container.$form());
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addEventListener(this);
        }
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getView();
    }

    @DesignerProperty(defaultValue = "42.359144, -71.093612", editorType = "geographic_point")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "<p>Set the initial center coordinate of the map. The value is specified as a comma-separated pair of decimal latitude and longitude coordinates, for example, <code>42.359144, -71.093612</code>.</p><p>In blocks code, it is recommended for performance reasons to use SetCenter with numerical latitude and longitude rather than convert to the string representation for use with this property.</p>")
    public void CenterFromString(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        String str2 = str;
        String[] split = str2.split(",");
        String[] strArr = split;
        if (split.length != 2) {
            String str3 = TAG;
            new StringBuilder();
            int e = Log.e(str3, sb2.append(str2).append(" is not a valid point.").toString());
            new StringBuilder();
            InvalidPoint(sb3.append(str2).append(" is not a valid point.").toString());
            return;
        }
        try {
            double parseDouble = Double.parseDouble(strArr[0].trim());
            try {
                double parseDouble2 = Double.parseDouble(strArr[1].trim());
                if (parseDouble > 90.0d || parseDouble < -90.0d) {
                    InvalidPoint(String.format("Latitude %f is out of bounds.", new Object[]{Double.valueOf(parseDouble)}));
                } else if (parseDouble2 > 180.0d || parseDouble2 < -180.0d) {
                    InvalidPoint(String.format("Longitude %f is out of bounds.", new Object[]{Double.valueOf(parseDouble2)}));
                } else {
                    String str4 = TAG;
                    new StringBuilder("Setting center to ");
                    int i = Log.i(str4, sb.append(parseDouble).append(", ").append(parseDouble2).toString());
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCenter(parseDouble, parseDouble2);
                }
            } catch (NumberFormatException e2) {
                int e3 = Log.e(TAG, String.format("%s is not a valid number.", new Object[]{strArr[1]}));
                InvalidPoint(String.format("%s is not a valid number.", new Object[]{strArr[1]}));
            }
        } catch (NumberFormatException e4) {
            int e5 = Log.e(TAG, String.format("%s is not a valid number.", new Object[]{strArr[0]}));
            InvalidPoint(String.format("%s is not a valid number.", new Object[]{strArr[0]}));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The latitude of the center of the map.")
    public double Latitude() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getLatitude();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The longitude of the center of the map.")
    public double Longitude() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getLongitude();
    }

    @DesignerProperty(defaultValue = "13", editorType = "map_zoom")
    @SimpleProperty
    public void ZoomLevel(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setZoom(i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The zoom level of the map. Valid values of ZoomLevel are dependent on the tile provider and the latitude and longitude of the map. For example, zoom levels are more constrained over oceans than dense city centers to conserve space for storing tiles, so valid values may be 1-7 over ocean and 1-18 over cities. Tile providers may send warning or error tiles if the zoom level is too great for the server to support.")
    public int ZoomLevel() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getZoom();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void EnableZoom(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setZoomEnabled(z);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If this property is set to true, multitouch zoom gestures are allowed on the map. Otherwise, the map zoom cannot be changed by the user except via the zoom control buttons.")
    public boolean EnableZoom() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isZoomEnabled();
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "float")
    @SimpleProperty
    public void Rotation(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRotation(f);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Sets or gets the rotation of the map in decimal degrees if any")
    public float Rotation() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getRotation();
    }

    @DesignerProperty(defaultValue = "1", editorType = "map_type")
    @SimpleProperty
    public void MapType(int i) {
        MapFactory.MapType mapType = MapFactory.MapType.values()[i];
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMapType(mapType);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The type of tile layer to use as the base of the map. Valid values are: 1 (Roads), 2 (Aerial), 3 (Terrain)")
    public int MapType() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMapType().ordinal();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void ShowCompass(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCompassEnabled(z);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Show a compass icon rotated based on user orientation.")
    public boolean ShowCompass() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isCompassEnabled();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void ShowZoom(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setZoomControlEnabled(z);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Show zoom buttons on the map.")
    public boolean ShowZoom() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isZoomControlEnabled();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void ShowUser(boolean z) {
        Runnable runnable;
        boolean z2 = z;
        if (!this.havePermission) {
            final boolean z3 = z2;
            new Runnable(this) {
                final /* synthetic */ Map hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void run() {
                    PermissionResultHandler permissionResultHandler;
                    new PermissionResultHandler(this) {
                        private /* synthetic */ C09021 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void HandlePermissionResponse(String str, boolean z) {
                            String str2 = str;
                            boolean z2 = z;
                            if (z2) {
                                boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = Map.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, true);
                                Map.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, z3);
                                return;
                            }
                            boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = Map.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, false);
                            Map.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, z2);
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "ShowUser", "android.permission.ACCESS_FINE_LOCATION");
                        }
                    };
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.askPermission("android.permission.ACCESS_FINE_LOCATION", permissionResultHandler);
                }
            };
            boolean post = this.androidUIHandler.post(runnable);
            return;
        }
        vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(z2);
    }

    private void vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(boolean z) {
        StringBuilder sb;
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setShowUserEnabled(z);
        } catch (PermissionException e) {
            this.form.dispatchPermissionDeniedEvent((Component) this, "ShowUser", e);
        } catch (Exception e2) {
            Exception exc = e2;
            String str = TAG;
            new StringBuilder();
            int e3 = Log.e(str, sb.append(exc.getMessage()).toString());
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Show the user's location on the map.")
    public boolean ShowUser() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isShowUserEnabled();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void EnableRotation(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRotationEnabled(z);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If set to true, the user can use multitouch gestures to rotate the map around its current center.")
    public boolean EnableRotation() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isRotationEnabled();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void EnablePan(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPanEnabled(z);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Enable two-finger panning of the Map")
    public boolean EnablePan() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isPanEnabled();
    }

    @SimpleProperty
    public void BoundingBox(YailList yailList) {
        BoundingBox boundingBox;
        YailList yailList2 = yailList;
        double coerceToDouble = GeometryUtil.coerceToDouble(((YailList) yailList2.get(1)).get(1));
        double coerceToDouble2 = GeometryUtil.coerceToDouble(((YailList) yailList2.get(1)).get(2));
        double coerceToDouble3 = GeometryUtil.coerceToDouble(((YailList) yailList2.get(2)).get(1));
        new BoundingBox(coerceToDouble, GeometryUtil.coerceToDouble(((YailList) yailList2.get(2)).get(2)), coerceToDouble3, coerceToDouble2);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBoundingBox(boundingBox);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Bounding box for the map stored as [[North, West], [South, East]].")
    public YailList BoundingBox() {
        BoundingBox boundingBox = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBoundingBox();
        Double[] dArr = new Double[2];
        dArr[0] = Double.valueOf(boundingBox.getLatNorth());
        Double[] dArr2 = dArr;
        dArr2[1] = Double.valueOf(boundingBox.getLonWest());
        YailList makeList = YailList.makeList((Object[]) dArr2);
        Double[] dArr3 = new Double[2];
        dArr3[0] = Double.valueOf(boundingBox.getLatSouth());
        Double[] dArr4 = dArr3;
        dArr4[1] = Double.valueOf(boundingBox.getLonEast());
        YailList makeList2 = YailList.makeList((Object[]) dArr4);
        YailList[] yailListArr = new YailList[2];
        yailListArr[0] = makeList;
        YailList[] yailListArr2 = yailListArr;
        yailListArr2[1] = makeList2;
        return YailList.makeList((Object[]) yailListArr2);
    }

    @DesignerProperty(editorType = "component:com.google.appinventor.components.runtime.LocationSensor")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Uses the provided LocationSensor for user location data rather than the built-in location provider.")
    public void LocationSensor(LocationSensor locationSensor) {
        LocationSensor locationSensor2 = locationSensor;
        LocationSensor.LocationSensorListener locationListener = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getLocationListener();
        if (this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou != null) {
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.removeListener(locationListener);
        }
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = locationSensor2;
        if (this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou != null) {
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.addListener(locationListener);
        }
    }

    public LocationSensor LocationSensor() {
        return this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void ShowScale(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setScaleVisible(z);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Shows a scale reference on the map.")
    public boolean ShowScale() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isScaleVisible();
    }

    @DesignerProperty(defaultValue = "1", editorType = "map_unit_system")
    @SimpleProperty
    public void ScaleUnits(int i) {
        int i2 = i;
        if (i2 <= 0 || i2 >= MapFactory.MapScaleUnits.values().length) {
            $form().dispatchErrorOccurredEvent(this, "ScaleUnits", ErrorMessages.ERROR_INVALID_UNIT_SYSTEM, Integer.valueOf(i2));
            return;
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setScaleUnits(MapFactory.MapScaleUnits.values()[i2]);
    }

    @SimpleProperty
    public int ScaleUnits() {
        switch (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getScaleUnits()) {
            case METRIC:
                return 1;
            case IMPERIAL:
                return 2;
            default:
                return 0;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the user's latitude if ShowUser is enabled.")
    public double UserLatitude() {
        StringBuilder sb;
        try {
            if (this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou == null) {
                return -999.0d;
            }
            return this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.Latitude();
        } catch (PermissionException e) {
            this.form.dispatchPermissionDeniedEvent((Component) this, "UserLatitude", e);
            return -999.0d;
        } catch (Exception e2) {
            Exception exc = e2;
            String str = TAG;
            new StringBuilder();
            int e3 = Log.e(str, sb.append(exc.getMessage()).toString());
            return -999.0d;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the user's longitude if ShowUser is enabled.")
    public double UserLongitude() {
        StringBuilder sb;
        try {
            if (this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou == null) {
                return -999.0d;
            }
            return this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.Longitude();
        } catch (PermissionException e) {
            this.form.dispatchPermissionDeniedEvent((Component) this, "UserLongitude", e);
            return -999.0d;
        } catch (Exception e2) {
            Exception exc = e2;
            String str = TAG;
            new StringBuilder();
            int e3 = Log.e(str, sb.append(exc.getMessage()).toString());
            return -999.0d;
        }
    }

    @SimpleFunction(description = "Pan the map center to the given latitude and longitude and adjust the zoom level to the specified zoom.")
    public void PanTo(double d, double d2, int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.panTo(d, d2, i, 1.0d);
    }

    @SimpleFunction(description = "Create a new marker with default properties at the specified latitude and longitude.")
    public Marker CreateMarker(double d, double d2) {
        Marker marker;
        new Marker(this);
        Marker marker2 = marker;
        Marker marker3 = marker2;
        marker2.SetLocation(d, d2);
        return marker3;
    }

    @SimpleFunction(description = "Save the contents of the Map to the specified path.")
    public void Save(String str) {
        List list;
        Runnable runnable;
        new ArrayList(this.features);
        final List list2 = list;
        final String str2 = str;
        new Runnable(this) {
            final /* synthetic */ Map hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                Runnable runnable;
                Runnable runnable2;
                try {
                    GeoJSONUtil.writeFeaturesAsGeoJSON(list2, str2);
                } catch (PermissionException e) {
                    Form $form = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.$form();
                    final Form form = $form;
                    final PermissionException permissionException = e;
                    new Runnable(this) {
                        private /* synthetic */ C09147 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                        }

                        public final void run() {
                            form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Save", permissionException);
                        }
                    };
                    $form.runOnUiThread(runnable2);
                } catch (IOException e2) {
                    Form $form2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.$form();
                    final Form form2 = $form2;
                    final IOException iOException = e2;
                    new Runnable(this) {
                        private /* synthetic */ C09147 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                        }

                        public final void run() {
                            StringBuilder sb;
                            new StringBuilder();
                            form2.dispatchErrorOccurredEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Save", ErrorMessages.ERROR_EXCEPTION_DURING_MAP_SAVE, sb.append(iOException.getMessage()).toString());
                        }
                    };
                    $form2.runOnUiThread(runnable);
                }
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    @SimpleEvent(description = "Map has been initialized and is ready for user interaction.")
    public void Ready() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Ready", new Object[0]);
    }

    @SimpleEvent(description = "User has changed the map bounds by panning or zooming the map.")
    public void BoundsChange() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "BoundsChange", new Object[0]);
    }

    @SimpleEvent(description = "User has changed the zoom level of the map.")
    public void ZoomChange() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ZoomChange", new Object[0]);
    }

    @SimpleEvent(description = "An invalid coordinate was supplied during a maps operation. The message parameter will have more details about the issue.")
    public void InvalidPoint(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "InvalidPoint", str);
    }

    @SimpleEvent(description = "The user tapped at a point on the map.")
    public void TapAtPoint(double d, double d2) {
        Object[] objArr = new Object[2];
        objArr[0] = Double.valueOf(d);
        Object[] objArr2 = objArr;
        objArr2[1] = Double.valueOf(d2);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TapAtPoint", objArr2);
    }

    @SimpleEvent(description = "The user double-tapped at a point on the map. This event will be followed by a ZoomChanged event if zooming gestures are enabled and the map is not at the highest possible zoom level.")
    public void DoubleTapAtPoint(double d, double d2) {
        Object[] objArr = new Object[2];
        objArr[0] = Double.valueOf(d);
        Object[] objArr2 = objArr;
        objArr2[1] = Double.valueOf(d2);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "DoubleTapAtPoint", objArr2);
    }

    @SimpleEvent(description = "The user long-pressed at a point on the map.")
    public void LongPressAtPoint(double d, double d2) {
        Object[] objArr = new Object[2];
        objArr[0] = Double.valueOf(d);
        Object[] objArr2 = objArr;
        objArr2[1] = Double.valueOf(d2);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LongPressAtPoint", objArr2);
    }

    public MapFactory.MapController getController() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public void onReady(MapFactory.MapController mapController) {
        Runnable runnable;
        MapFactory.MapController mapController2 = mapController;
        new Runnable(this) {
            private /* synthetic */ Map hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Ready();
            }
        };
        this.container.$form().runOnUiThread(runnable);
    }

    public void onBoundsChanged() {
        Runnable runnable;
        new Runnable(this) {
            private /* synthetic */ Map hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.BoundsChange();
            }
        };
        this.container.$form().runOnUiThread(runnable);
    }

    public void onZoom() {
        Runnable runnable;
        new Runnable(this) {
            private /* synthetic */ Map hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ZoomChange();
            }
        };
        this.container.$form().runOnUiThread(runnable);
    }

    public void onSingleTap(double d, double d2) {
        Runnable runnable;
        final double d3 = d;
        final double d4 = d2;
        new Runnable(this) {
            private /* synthetic */ Map hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r11;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.TapAtPoint(d3, d4);
            }
        };
        this.container.$form().runOnUiThread(runnable);
    }

    public void onDoubleTap(double d, double d2) {
        Runnable runnable;
        final double d3 = d;
        final double d4 = d2;
        new Runnable(this) {
            private /* synthetic */ Map hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r11;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.DoubleTapAtPoint(d3, d4);
            }
        };
        this.container.$form().runOnUiThread(runnable);
    }

    public void onLongPress(double d, double d2) {
        Runnable runnable;
        final double d3 = d;
        final double d4 = d2;
        new Runnable(this) {
            private /* synthetic */ Map hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r11;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LongPressAtPoint(d3, d4);
            }
        };
        this.container.$form().runOnUiThread(runnable);
    }

    public void onFeatureClick(MapFactory.MapFeature mapFeature) {
        Runnable runnable;
        final MapFactory.MapFeature mapFeature2 = mapFeature;
        new Runnable(this) {
            private /* synthetic */ Map hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                mapFeature2.Click();
            }
        };
        this.container.$form().runOnUiThread(runnable);
    }

    public void onFeatureLongPress(MapFactory.MapFeature mapFeature) {
        Runnable runnable;
        final MapFactory.MapFeature mapFeature2 = mapFeature;
        new Runnable(this) {
            private /* synthetic */ Map hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                mapFeature2.LongClick();
            }
        };
        this.container.$form().runOnUiThread(runnable);
    }

    public void onFeatureStartDrag(MapFactory.MapFeature mapFeature) {
        Runnable runnable;
        final MapFactory.MapFeature mapFeature2 = mapFeature;
        new Runnable(this) {
            private /* synthetic */ Map hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                mapFeature2.StartDrag();
            }
        };
        this.container.$form().runOnUiThread(runnable);
    }

    public void onFeatureDrag(MapFactory.MapFeature mapFeature) {
        Runnable runnable;
        final MapFactory.MapFeature mapFeature2 = mapFeature;
        new Runnable(this) {
            private /* synthetic */ Map hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                mapFeature2.Drag();
            }
        };
        this.container.$form().runOnUiThread(runnable);
    }

    public void onFeatureStopDrag(MapFactory.MapFeature mapFeature) {
        Runnable runnable;
        final MapFactory.MapFeature mapFeature2 = mapFeature;
        new Runnable(this) {
            private /* synthetic */ Map hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                mapFeature2.StopDrag();
            }
        };
        this.container.$form().runOnUiThread(runnable);
    }

    public Map getMap() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapMarker mapMarker) {
        MapFactory.MapMarker mapMarker2 = mapMarker;
        boolean add = this.features.add(mapMarker2);
        mapMarker2.setMap(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addFeature(mapMarker2);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapLineString mapLineString) {
        MapFactory.MapLineString mapLineString2 = mapLineString;
        boolean add = this.features.add(mapLineString2);
        mapLineString2.setMap(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addFeature(mapLineString2);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapPolygon mapPolygon) {
        MapFactory.MapPolygon mapPolygon2 = mapPolygon;
        boolean add = this.features.add(mapPolygon2);
        mapPolygon2.setMap(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addFeature(mapPolygon2);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapRectangle mapRectangle) {
        MapFactory.MapRectangle mapRectangle2 = mapRectangle;
        boolean add = this.features.add(mapRectangle2);
        mapRectangle2.setMap(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addFeature(mapRectangle2);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapCircle mapCircle) {
        MapFactory.MapCircle mapCircle2 = mapCircle;
        boolean add = this.features.add(mapCircle2);
        mapCircle2.setMap(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addFeature(mapCircle2);
    }

    public void removeFeature(MapFactory.MapFeature mapFeature) {
        MapFactory.MapFeature mapFeature2 = mapFeature;
        boolean remove = this.features.remove(mapFeature2);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.removeFeature(mapFeature2);
    }
}
