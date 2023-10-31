package com.google.appinventor.components.runtime;

import android.text.TextUtils;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.DispatchableError;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.util.GeoPoint;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MAPS, description = "Polygon", version = 2)
public class Polygon extends PolygonBase implements MapFactory.MapPolygon {
    private static final String TAG = Polygon.class.getSimpleName();
    private static final MapFactory.MapFeatureVisitor<Double> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private List<List<GeoPoint>> KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    private boolean boZncMwfbKhn9wEZVXOlCQaCALViR3x19GsnEC1EZxndIE2ufazY5HxCE0U58Zvt = false;
    private boolean initialized = false;
    private List<List<List<GeoPoint>>> sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb;

    static {
        MapFactory.MapFeatureVisitor<Double> mapFeatureVisitor;
        new MapFactory.MapFeatureVisitor<Double>() {
            public final /* synthetic */ Object visit(MapFactory.MapRectangle mapRectangle, Object[] objArr) {
                MapFactory.MapRectangle mapRectangle2 = mapRectangle;
                Object[] objArr2 = objArr;
                if (((Boolean) objArr2[1]).booleanValue()) {
                    return Double.valueOf(GeometryUtil.distanceBetweenCentroids((MapFactory.MapPolygon) (Polygon) objArr2[0], mapRectangle2));
                }
                return Double.valueOf(GeometryUtil.distanceBetweenEdges((MapFactory.MapPolygon) (Polygon) objArr2[0], mapRectangle2));
            }

            public final /* synthetic */ Object visit(MapFactory.MapCircle mapCircle, Object[] objArr) {
                MapFactory.MapCircle mapCircle2 = mapCircle;
                Object[] objArr2 = objArr;
                if (((Boolean) objArr2[1]).booleanValue()) {
                    return Double.valueOf(GeometryUtil.distanceBetweenCentroids((MapFactory.MapPolygon) (Polygon) objArr2[0], mapCircle2));
                }
                return Double.valueOf(GeometryUtil.distanceBetweenEdges((MapFactory.MapPolygon) (Polygon) objArr2[0], mapCircle2));
            }

            public final /* synthetic */ Object visit(MapFactory.MapPolygon mapPolygon, Object[] objArr) {
                MapFactory.MapPolygon mapPolygon2 = mapPolygon;
                Object[] objArr2 = objArr;
                if (((Boolean) objArr2[1]).booleanValue()) {
                    return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapPolygon2, (MapFactory.MapPolygon) (Polygon) objArr2[0]));
                }
                return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapPolygon2, (MapFactory.MapPolygon) (Polygon) objArr2[0]));
            }

            public final /* synthetic */ Object visit(MapFactory.MapLineString mapLineString, Object[] objArr) {
                MapFactory.MapLineString mapLineString2 = mapLineString;
                Object[] objArr2 = objArr;
                if (((Boolean) objArr2[1]).booleanValue()) {
                    return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapLineString2, (MapFactory.MapPolygon) (Polygon) objArr2[0]));
                }
                return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapLineString2, (MapFactory.MapPolygon) (Polygon) objArr2[0]));
            }

            public final /* synthetic */ Object visit(MapFactory.MapMarker mapMarker, Object[] objArr) {
                MapFactory.MapMarker mapMarker2 = mapMarker;
                Object[] objArr2 = objArr;
                if (((Boolean) objArr2[1]).booleanValue()) {
                    return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapMarker2, (MapFactory.MapPolygon) (Polygon) objArr2[0]));
                }
                return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapMarker2, (MapFactory.MapPolygon) (Polygon) objArr2[0]));
            }
        };
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = mapFeatureVisitor;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Polygon(com.google.appinventor.components.runtime.util.MapFactory.MapFeatureContainer r7) {
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
            java.util.ArrayList r3 = new java.util.ArrayList
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = r3
            r2 = r0
            r3 = 0
            r2.boZncMwfbKhn9wEZVXOlCQaCALViR3x19GsnEC1EZxndIE2ufazY5HxCE0U58Zvt = r3
            r2 = r0
            r3 = 0
            r2.initialized = r3
            r2 = r1
            r3 = r0
            r2.addFeature(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Polygon.<init>(com.google.appinventor.components.runtime.util.MapFactory$MapFeatureContainer):void");
    }

    public void Initialize() {
        this.initialized = true;
        clearGeometry();
        this.map.getController().updateFeaturePosition((MapFactory.MapPolygon) this);
        this.map.getController().updateFeatureHoles(this);
        this.map.getController().updateFeatureText(this);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The type of the feature. For polygons, this returns the text \"Polygon\".")
    public String Type() {
        return MapFactory.MapFeatureType.TYPE_POLYGON;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets or sets the sequence of points used to draw the polygon.")
    public YailList Points() {
        List list;
        if (this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.isEmpty()) {
            return YailList.makeEmptyList();
        }
        if (!this.boZncMwfbKhn9wEZVXOlCQaCALViR3x19GsnEC1EZxndIE2ufazY5HxCE0U58Zvt) {
            return GeometryUtil.pointsListToYailList(this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.get(0));
        }
        new LinkedList();
        List list2 = list;
        for (List pointsListToYailList : this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH) {
            boolean add = list2.add(GeometryUtil.pointsListToYailList(pointsListToYailList));
        }
        return YailList.makeList(list2);
    }

    @SimpleProperty
    public void Points(YailList yailList) {
        Throwable th;
        YailList yailList2 = yailList;
        try {
            if (GeometryUtil.isPolygon(yailList2)) {
                this.boZncMwfbKhn9wEZVXOlCQaCALViR3x19GsnEC1EZxndIE2ufazY5HxCE0U58Zvt = false;
                this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.clear();
                boolean add = this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.add(GeometryUtil.pointsFromYailList(yailList2));
            } else if (GeometryUtil.isMultiPolygon(yailList2)) {
                this.boZncMwfbKhn9wEZVXOlCQaCALViR3x19GsnEC1EZxndIE2ufazY5HxCE0U58Zvt = true;
                this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = GeometryUtil.multiPolygonFromYailList(yailList2);
            } else {
                Throwable th2 = th;
                new DispatchableError(ErrorMessages.ERROR_POLYGON_PARSE_ERROR, "Unable to determine the structure of the points argument.");
                throw th2;
            }
            if (this.initialized) {
                clearGeometry();
                this.map.getController().updateFeaturePosition((MapFactory.MapPolygon) this);
            }
        } catch (DispatchableError e) {
            DispatchableError dispatchableError = e;
            this.container.$form().dispatchErrorOccurredEvent(this, "Points", dispatchableError.getErrorCode(), dispatchableError.getArguments());
        }
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(description = "Constructs a polygon from the given list of coordinates.")
    public void PointsFromString(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        JSONArray jSONArray;
        List<List<GeoPoint>> list;
        List<List<GeoPoint>> list2;
        String str2 = str;
        if (TextUtils.isEmpty(str2)) {
            new ArrayList();
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = list2;
            this.map.getController().updateFeaturePosition((MapFactory.MapPolygon) this);
            return;
        }
        try {
            new JSONArray(str2);
            JSONArray jSONArray2 = jSONArray;
            JSONArray jSONArray3 = jSONArray2;
            if (jSONArray2.length() == 0) {
                new ArrayList();
                this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = list;
                this.boZncMwfbKhn9wEZVXOlCQaCALViR3x19GsnEC1EZxndIE2ufazY5HxCE0U58Zvt = false;
                this.map.getController().updateFeaturePosition((MapFactory.MapPolygon) this);
                return;
            }
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = GeometryUtil.multiPolygonToList(jSONArray3);
            this.boZncMwfbKhn9wEZVXOlCQaCALViR3x19GsnEC1EZxndIE2ufazY5HxCE0U58Zvt = this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.size() > 1;
            if (this.initialized) {
                clearGeometry();
                this.map.getController().updateFeaturePosition((MapFactory.MapPolygon) this);
            }
        } catch (JSONException e) {
            new StringBuilder();
            this.container.$form().dispatchErrorOccurredEvent(this, "PointsFromString", ErrorMessages.ERROR_POLYGON_PARSE_ERROR, sb2.append(e.getMessage()).toString());
        } catch (DispatchableError e2) {
            DispatchableError dispatchableError = e2;
            new StringBuilder();
            getDispatchDelegate().dispatchErrorOccurredEvent(this, "PointsFromString", dispatchableError.getErrorCode(), sb.append(dispatchableError.getArguments()).toString());
        }
    }

    @SimpleProperty
    public YailList HolePoints() {
        List list;
        if (this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb.isEmpty()) {
            return YailList.makeEmptyList();
        }
        if (!this.boZncMwfbKhn9wEZVXOlCQaCALViR3x19GsnEC1EZxndIE2ufazY5HxCE0U58Zvt) {
            return GeometryUtil.multiPolygonToYailList(this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb.get(0));
        }
        new LinkedList();
        List list2 = list;
        for (List multiPolygonToYailList : this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb) {
            boolean add = list2.add(GeometryUtil.multiPolygonToYailList(multiPolygonToYailList));
        }
        return YailList.makeList(list2);
    }

    @SimpleProperty
    public void HolePoints(YailList yailList) {
        Throwable th;
        List list;
        List<List<List<GeoPoint>>> list2;
        YailList yailList2 = yailList;
        try {
            if (yailList2.size() == 0) {
                new ArrayList();
                this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = list2;
            } else if (this.boZncMwfbKhn9wEZVXOlCQaCALViR3x19GsnEC1EZxndIE2ufazY5HxCE0U58Zvt) {
                this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = GeometryUtil.multiPolygonHolesFromYailList(yailList2);
            } else if (GeometryUtil.isMultiPolygon(yailList2)) {
                new ArrayList();
                List list3 = list;
                boolean add = list3.add(GeometryUtil.multiPolygonFromYailList(yailList2));
                this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = list3;
            } else {
                Throwable th2 = th;
                new DispatchableError(ErrorMessages.ERROR_POLYGON_PARSE_ERROR, "Unable to determine the structure of the points argument.");
                throw th2;
            }
            if (this.initialized) {
                clearGeometry();
                this.map.getController().updateFeatureHoles(this);
            }
        } catch (DispatchableError e) {
            DispatchableError dispatchableError = e;
            this.container.$form().dispatchErrorOccurredEvent(this, "HolePoints", dispatchableError.getErrorCode(), dispatchableError.getArguments());
        }
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(description = "Constructs holes in a polygon from a given list of coordinates per hole.")
    public void HolePointsFromString(String str) {
        StringBuilder sb;
        JSONArray jSONArray;
        StringBuilder sb2;
        List<List<List<GeoPoint>>> list;
        List<List<List<GeoPoint>>> list2;
        String str2 = str;
        if (TextUtils.isEmpty(str2)) {
            new ArrayList();
            this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = list2;
            this.map.getController().updateFeatureHoles(this);
            return;
        }
        try {
            new JSONArray(str2);
            JSONArray jSONArray2 = jSONArray;
            JSONArray jSONArray3 = jSONArray2;
            if (jSONArray2.length() == 0) {
                new ArrayList();
                this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = list;
                this.map.getController().updateFeatureHoles(this);
                return;
            }
            this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = GeometryUtil.multiPolygonHolesToList(jSONArray3);
            if (this.initialized) {
                clearGeometry();
                this.map.getController().updateFeatureHoles(this);
            }
            String str3 = TAG;
            new StringBuilder("Points: ");
            int d = Log.d(str3, sb2.append(this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH).toString());
        } catch (JSONException e) {
            JSONException jSONException = e;
            int e2 = Log.e(TAG, "Unable to parse point string", jSONException);
            new StringBuilder();
            this.container.$form().dispatchErrorOccurredEvent(this, "HolePointsFromString", ErrorMessages.ERROR_POLYGON_PARSE_ERROR, sb.append(jSONException.getMessage()).toString());
        }
    }

    @SimpleFunction(description = "Returns the centroid of the Polygon as a (latitude, longitude) pair.")
    public YailList Centroid() {
        return super.Centroid();
    }

    public List<List<GeoPoint>> getPoints() {
        return this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    }

    public List<List<List<GeoPoint>>> getHolePoints() {
        return this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb;
    }

    public <T> T accept(MapFactory.MapFeatureVisitor<T> mapFeatureVisitor, Object... objArr) {
        return mapFeatureVisitor.visit((MapFactory.MapPolygon) this, objArr);
    }

    /* access modifiers changed from: protected */
    public Geometry computeGeometry() {
        return GeometryUtil.createGeometry(this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH, this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb);
    }

    public void updatePoints(List<List<GeoPoint>> list) {
        this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.clear();
        boolean addAll = this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.addAll(list);
        clearGeometry();
    }

    public void updateHolePoints(List<List<List<GeoPoint>>> list) {
        this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb.clear();
        boolean addAll = this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb.addAll(list);
        clearGeometry();
    }
}
