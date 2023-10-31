package com.google.appinventor.components.runtime;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.DispatchableError;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.util.GeoPoint;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MAPS, description = "LineString", version = 2)
public class LineString extends MapFeatureBase implements MapFactory.MapLineString {
    private static final String TAG = LineString.class.getSimpleName();
    private static final MapFactory.MapFeatureVisitor<Double> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private List<GeoPoint> KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;

    static {
        MapFactory.MapFeatureVisitor<Double> mapFeatureVisitor;
        new MapFactory.MapFeatureVisitor<Double>() {
            public final /* synthetic */ Object visit(MapFactory.MapRectangle mapRectangle, Object[] objArr) {
                MapFactory.MapRectangle mapRectangle2 = mapRectangle;
                Object[] objArr2 = objArr;
                if (((Boolean) objArr2[1]).booleanValue()) {
                    return Double.valueOf(GeometryUtil.distanceBetweenCentroids((MapFactory.MapLineString) (LineString) objArr2[0], mapRectangle2));
                }
                return Double.valueOf(GeometryUtil.distanceBetweenEdges((MapFactory.MapLineString) (LineString) objArr2[0], mapRectangle2));
            }

            public final /* synthetic */ Object visit(MapFactory.MapCircle mapCircle, Object[] objArr) {
                MapFactory.MapCircle mapCircle2 = mapCircle;
                Object[] objArr2 = objArr;
                if (((Boolean) objArr2[1]).booleanValue()) {
                    return Double.valueOf(GeometryUtil.distanceBetweenCentroids((MapFactory.MapLineString) (LineString) objArr2[0], mapCircle2));
                }
                return Double.valueOf(GeometryUtil.distanceBetweenEdges((MapFactory.MapLineString) (LineString) objArr2[0], mapCircle2));
            }

            public final /* synthetic */ Object visit(MapFactory.MapPolygon mapPolygon, Object[] objArr) {
                MapFactory.MapPolygon mapPolygon2 = mapPolygon;
                Object[] objArr2 = objArr;
                if (((Boolean) objArr2[1]).booleanValue()) {
                    return Double.valueOf(GeometryUtil.distanceBetweenCentroids((MapFactory.MapLineString) (LineString) objArr2[0], mapPolygon2));
                }
                return Double.valueOf(GeometryUtil.distanceBetweenEdges((MapFactory.MapLineString) (LineString) objArr2[0], mapPolygon2));
            }

            public final /* synthetic */ Object visit(MapFactory.MapLineString mapLineString, Object[] objArr) {
                MapFactory.MapLineString mapLineString2 = mapLineString;
                Object[] objArr2 = objArr;
                if (((Boolean) objArr2[1]).booleanValue()) {
                    return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapLineString2, (MapFactory.MapLineString) (LineString) objArr2[0]));
                }
                return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapLineString2, (MapFactory.MapLineString) (LineString) objArr2[0]));
            }

            public final /* synthetic */ Object visit(MapFactory.MapMarker mapMarker, Object[] objArr) {
                MapFactory.MapMarker mapMarker2 = mapMarker;
                Object[] objArr2 = objArr;
                if (((Boolean) objArr2[1]).booleanValue()) {
                    return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapMarker2, (MapFactory.MapLineString) (LineString) objArr2[0]));
                }
                return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapMarker2, (MapFactory.MapLineString) (LineString) objArr2[0]));
            }
        };
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = mapFeatureVisitor;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LineString(com.google.appinventor.components.runtime.util.MapFactory.MapFeatureContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.util.MapFactory$MapFeatureVisitor<java.lang.Double> r4 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r2.<init>(r3, r4)
            r2 = r0
            java.util.ArrayList r3 = new java.util.ArrayList
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = r3
            r2 = r0
            r3 = 3
            r2.StrokeWidth(r3)
            r2 = r1
            r3 = r0
            r2.addFeature(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.LineString.<init>(com.google.appinventor.components.runtime.util.MapFactory$MapFeatureContainer):void");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The type of the map feature.")
    public String Type() {
        return MapFactory.MapFeatureType.TYPE_LINESTRING;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A list of latitude and longitude pairs that represent the line segments of the polyline.")
    public YailList Points() {
        return GeometryUtil.pointsListToYailList(this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH);
    }

    @SimpleProperty
    public void Points(@NonNull YailList yailList) {
        YailList yailList2 = yailList;
        if (yailList2.size() < 2) {
            this.container.$form().dispatchErrorOccurredEvent(this, "Points", ErrorMessages.ERROR_LINESTRING_TOO_FEW_POINTS, Integer.valueOf(yailList2.length() - 1));
            return;
        }
        try {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = GeometryUtil.pointsFromYailList(yailList2);
            clearGeometry();
            this.map.getController().updateFeaturePosition((MapFactory.MapLineString) this);
        } catch (DispatchableError e) {
            DispatchableError dispatchableError = e;
            this.container.$form().dispatchErrorOccurredEvent(this, "Points", dispatchableError.getErrorCode(), dispatchableError.getArguments());
        }
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty
    public void PointsFromString(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        List<GeoPoint> list;
        JSONArray jSONArray;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Object obj;
        Throwable th5;
        String str2 = str;
        try {
            new ArrayList();
            List<GeoPoint> list2 = list;
            new JSONArray(str2);
            JSONArray jSONArray2 = jSONArray;
            JSONArray jSONArray3 = jSONArray2;
            if (jSONArray2.length() < 2) {
                Throwable th6 = th5;
                new DispatchableError(ErrorMessages.ERROR_LINESTRING_TOO_FEW_POINTS, Integer.valueOf(jSONArray3.length()));
                throw th6;
            }
            int length = jSONArray3.length();
            int i = 0;
            while (i < length) {
                JSONArray optJSONArray = jSONArray3.optJSONArray(i);
                JSONArray jSONArray4 = optJSONArray;
                if (optJSONArray == null) {
                    Throwable th7 = th;
                    Object[] objArr = new Object[2];
                    objArr[0] = Integer.valueOf(i);
                    Object[] objArr2 = objArr;
                    objArr2[1] = jSONArray3.get(i).toString();
                    new DispatchableError(ErrorMessages.ERROR_EXPECTED_ARRAY_AT_INDEX, objArr2);
                    throw th7;
                } else if (jSONArray4.length() < 2) {
                    Throwable th8 = th2;
                    Object[] objArr3 = new Object[2];
                    objArr3[0] = Integer.valueOf(i);
                    Object[] objArr4 = objArr3;
                    objArr4[1] = Integer.valueOf(str2.length());
                    new DispatchableError(ErrorMessages.ERROR_LINESTRING_TOO_FEW_FIELDS, objArr4);
                    throw th8;
                } else {
                    double optDouble = jSONArray4.optDouble(0, Double.NaN);
                    double optDouble2 = jSONArray4.optDouble(1, Double.NaN);
                    if (!GeometryUtil.isValidLatitude(optDouble)) {
                        Throwable th9 = th3;
                        Object[] objArr5 = new Object[2];
                        objArr5[0] = Integer.valueOf(i);
                        Object[] objArr6 = objArr5;
                        objArr6[1] = jSONArray3.get(0).toString();
                        new DispatchableError(ErrorMessages.ERROR_INVALID_LATITUDE_IN_POINT_AT_INDEX, objArr6);
                        throw th9;
                    } else if (!GeometryUtil.isValidLongitude(optDouble2)) {
                        Throwable th10 = th4;
                        Object[] objArr7 = new Object[2];
                        objArr7[0] = Integer.valueOf(i);
                        Object[] objArr8 = objArr7;
                        objArr8[1] = jSONArray3.get(1).toString();
                        new DispatchableError(ErrorMessages.ERROR_INVALID_LONGITUDE_IN_POINT_AT_INDEX, objArr8);
                        throw th10;
                    } else {
                        new GeoPoint(optDouble, optDouble2);
                        boolean add = list2.add(obj);
                        i++;
                    }
                }
            }
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = list2;
            clearGeometry();
            this.map.getController().updateFeaturePosition((MapFactory.MapLineString) this);
        } catch (JSONException e) {
            JSONException jSONException = e;
            int e2 = Log.e(TAG, "Malformed string to LineString.PointsFromString", jSONException);
            new StringBuilder();
            this.container.$form().dispatchErrorOccurredEvent(this, "PointsFromString", ErrorMessages.ERROR_LINESTRING_PARSE_ERROR, sb2.append(jSONException.getMessage()).toString());
        } catch (DispatchableError e3) {
            DispatchableError dispatchableError = e3;
            new StringBuilder();
            this.container.$form().dispatchErrorOccurredEvent(this, "PointsFromString", dispatchableError.getErrorCode(), sb.append(dispatchableError.getArguments()).toString());
        }
    }

    @DesignerProperty(defaultValue = "3")
    @SimpleProperty
    public void StrokeWidth(int i) {
        super.StrokeWidth(i);
    }

    public List<GeoPoint> getPoints() {
        return this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    }

    public <T> T accept(MapFactory.MapFeatureVisitor<T> mapFeatureVisitor, Object... objArr) {
        return mapFeatureVisitor.visit((MapFactory.MapLineString) this, objArr);
    }

    /* access modifiers changed from: protected */
    public Geometry computeGeometry() {
        return GeometryUtil.createGeometry(this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH);
    }

    public void updatePoints(List<GeoPoint> list) {
        this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = list;
        clearGeometry();
    }
}
