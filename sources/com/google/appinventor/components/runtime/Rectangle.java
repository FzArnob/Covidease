package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import org.locationtech.jts.geom.Geometry;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MAPS, description = "Rectangle", version = 2)
public class Rectangle extends PolygonBase implements MapFactory.MapRectangle {
    private static final MapFactory.MapFeatureVisitor<Double> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private double ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = 0.0d;
    private double moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = 0.0d;
    private double tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = 0.0d;
    private double wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0 = 0.0d;

    static {
        MapFactory.MapFeatureVisitor<Double> mapFeatureVisitor;
        new MapFactory.MapFeatureVisitor<Double>() {
            public final /* synthetic */ Object visit(MapFactory.MapRectangle mapRectangle, Object[] objArr) {
                MapFactory.MapRectangle mapRectangle2 = mapRectangle;
                Object[] objArr2 = objArr;
                if (((Boolean) objArr2[1]).booleanValue()) {
                    return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapRectangle2, (MapFactory.MapRectangle) (Rectangle) objArr2[0]));
                }
                return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapRectangle2, (MapFactory.MapRectangle) (Rectangle) objArr2[0]));
            }

            public final /* synthetic */ Object visit(MapFactory.MapCircle mapCircle, Object[] objArr) {
                MapFactory.MapCircle mapCircle2 = mapCircle;
                Object[] objArr2 = objArr;
                if (((Boolean) objArr2[1]).booleanValue()) {
                    return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapCircle2, (MapFactory.MapRectangle) (Rectangle) objArr2[0]));
                }
                return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapCircle2, (MapFactory.MapRectangle) (Rectangle) objArr2[0]));
            }

            public final /* synthetic */ Object visit(MapFactory.MapPolygon mapPolygon, Object[] objArr) {
                MapFactory.MapPolygon mapPolygon2 = mapPolygon;
                Object[] objArr2 = objArr;
                if (((Boolean) objArr2[1]).booleanValue()) {
                    return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapPolygon2, (MapFactory.MapRectangle) (Rectangle) objArr2[0]));
                }
                return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapPolygon2, (MapFactory.MapRectangle) (Rectangle) objArr2[0]));
            }

            public final /* synthetic */ Object visit(MapFactory.MapLineString mapLineString, Object[] objArr) {
                MapFactory.MapLineString mapLineString2 = mapLineString;
                Object[] objArr2 = objArr;
                if (((Boolean) objArr2[1]).booleanValue()) {
                    return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapLineString2, (MapFactory.MapRectangle) (Rectangle) objArr2[0]));
                }
                return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapLineString2, (MapFactory.MapRectangle) (Rectangle) objArr2[0]));
            }

            public final /* synthetic */ Object visit(MapFactory.MapMarker mapMarker, Object[] objArr) {
                MapFactory.MapMarker mapMarker2 = mapMarker;
                Object[] objArr2 = objArr;
                if (((Boolean) objArr2[1]).booleanValue()) {
                    return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapMarker2, (MapFactory.MapRectangle) (Rectangle) objArr2[0]));
                }
                return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapMarker2, (MapFactory.MapRectangle) (Rectangle) objArr2[0]));
            }
        };
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = mapFeatureVisitor;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Rectangle(com.google.appinventor.components.runtime.util.MapFactory.MapFeatureContainer r7) {
        /*
            r6 = this;
            r1 = r6
            r2 = r7
            r3 = r1
            r4 = r2
            com.google.appinventor.components.runtime.util.MapFactory$MapFeatureVisitor<java.lang.Double> r5 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3.<init>(r4, r5)
            r3 = r1
            r4 = 0
            r3.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = r4
            r3 = r1
            r4 = 0
            r3.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = r4
            r3 = r1
            r4 = 0
            r3.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = r4
            r3 = r1
            r4 = 0
            r3.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0 = r4
            r3 = r2
            r4 = r1
            r3.addFeature(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Rectangle.<init>(com.google.appinventor.components.runtime.util.MapFactory$MapFeatureContainer):void");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The type of the feature. For rectangles, this returns the text \"Rectangle\".")
    public String Type() {
        return MapFactory.MapFeatureType.TYPE_RECTANGLE;
    }

    @DesignerProperty(defaultValue = "0", editorType = "float")
    @SimpleProperty
    public void EastLongitude(double d) {
        this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = d;
        clearGeometry();
        this.map.getController().updateFeaturePosition((MapFactory.MapRectangle) this);
    }

    @SimpleProperty
    public double EastLongitude() {
        return this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0;
    }

    @DesignerProperty(defaultValue = "0", editorType = "float")
    @SimpleProperty
    public void NorthLatitude(double d) {
        this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = d;
        clearGeometry();
        this.map.getController().updateFeaturePosition((MapFactory.MapRectangle) this);
    }

    @SimpleProperty
    public double NorthLatitude() {
        return this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud;
    }

    @DesignerProperty(defaultValue = "0", editorType = "float")
    @SimpleProperty
    public void SouthLatitude(double d) {
        this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0 = d;
        clearGeometry();
        this.map.getController().updateFeaturePosition((MapFactory.MapRectangle) this);
    }

    @SimpleProperty
    public double SouthLatitude() {
        return this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0;
    }

    @DesignerProperty(defaultValue = "0", editorType = "float")
    @SimpleProperty
    public void WestLongitude(double d) {
        this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = d;
        clearGeometry();
        this.map.getController().updateFeaturePosition((MapFactory.MapRectangle) this);
    }

    @SimpleProperty
    public double WestLongitude() {
        return this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE;
    }

    @SimpleFunction(description = "Returns the center of the Rectangle as a list of the form (Latitude Longitude).")
    public YailList Center() {
        return GeometryUtil.asYailList(getCentroid());
    }

    @SimpleFunction(description = "Returns the bounding box of the Rectangle in the format ((North West) (South East)).")
    public YailList Bounds() {
        Double[] dArr = new Double[2];
        dArr[0] = Double.valueOf(this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud);
        Double[] dArr2 = dArr;
        dArr2[1] = Double.valueOf(this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE);
        YailList makeList = YailList.makeList((Object[]) dArr2);
        Double[] dArr3 = new Double[2];
        dArr3[0] = Double.valueOf(this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0);
        Double[] dArr4 = dArr3;
        dArr4[1] = Double.valueOf(this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0);
        YailList makeList2 = YailList.makeList((Object[]) dArr4);
        YailList[] yailListArr = new YailList[2];
        yailListArr[0] = makeList;
        YailList[] yailListArr2 = yailListArr;
        yailListArr2[1] = makeList2;
        return YailList.makeList((Object[]) yailListArr2);
    }

    /* JADX WARNING: Multi-variable type inference failed */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Moves the Rectangle so that it is centered on the given latitude and longitude while attempting to maintain the width and height of the Rectangle as measured from the center to the edges.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void SetCenter(double r30, double r32) {
        /*
            r29 = this;
            r4 = r29
            r5 = r30
            r7 = r32
            r18 = r5
            r20 = -4587338432941916160(0xc056800000000000, double:-90.0)
            int r18 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r18 < 0) goto L_0x001c
            r18 = r5
            r20 = 4636033603912859648(0x4056800000000000, double:90.0)
            int r18 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r18 <= 0) goto L_0x005b
        L_0x001c:
            r18 = r4
            r0 = r18
            com.google.appinventor.components.runtime.util.MapFactory$MapFeatureContainer r0 = r0.container
            r18 = r0
            com.google.appinventor.components.runtime.Form r18 = r18.$form()
            r19 = r4
            java.lang.String r20 = "SetCenter"
            r21 = 3405(0xd4d, float:4.771E-42)
            r22 = 2
            r0 = r22
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r22 = r0
            r27 = r22
            r22 = r27
            r23 = r27
            r24 = 0
            r25 = r5
            java.lang.Double r25 = java.lang.Double.valueOf(r25)
            r23[r24] = r25
            r27 = r22
            r22 = r27
            r23 = r27
            r24 = 1
            r25 = r7
            java.lang.Double r25 = java.lang.Double.valueOf(r25)
            r23[r24] = r25
            r18.dispatchErrorOccurredEvent(r19, r20, r21, r22)
        L_0x005a:
            return
        L_0x005b:
            r18 = r7
            r20 = -4582834833314545664(0xc066800000000000, double:-180.0)
            int r18 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r18 < 0) goto L_0x0071
            r18 = r7
            r20 = 4640537203540230144(0x4066800000000000, double:180.0)
            int r18 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r18 <= 0) goto L_0x00b0
        L_0x0071:
            r18 = r4
            r0 = r18
            com.google.appinventor.components.runtime.util.MapFactory$MapFeatureContainer r0 = r0.container
            r18 = r0
            com.google.appinventor.components.runtime.Form r18 = r18.$form()
            r19 = r4
            java.lang.String r20 = "SetCenter"
            r21 = 3405(0xd4d, float:4.771E-42)
            r22 = 2
            r0 = r22
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r22 = r0
            r27 = r22
            r22 = r27
            r23 = r27
            r24 = 0
            r25 = r5
            java.lang.Double r25 = java.lang.Double.valueOf(r25)
            r23[r24] = r25
            r27 = r22
            r22 = r27
            r23 = r27
            r24 = 1
            r25 = r7
            java.lang.Double r25 = java.lang.Double.valueOf(r25)
            r23[r24] = r25
            r18.dispatchErrorOccurredEvent(r19, r20, r21, r22)
            goto L_0x005a
        L_0x00b0:
            r18 = r4
            org.osmdroid.util.GeoPoint r18 = r18.getCentroid()
            r9 = r18
            org.osmdroid.util.GeoPoint r18 = new org.osmdroid.util.GeoPoint
            r27 = r18
            r18 = r27
            r19 = r27
            r20 = r4
            r0 = r20
            double r0 = r0.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud
            r20 = r0
            r22 = r9
            double r22 = r22.getLongitude()
            r19.<init>(r20, r22)
            r10 = r18
            org.osmdroid.util.GeoPoint r18 = new org.osmdroid.util.GeoPoint
            r27 = r18
            r18 = r27
            r19 = r27
            r20 = r4
            r0 = r20
            double r0 = r0.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0
            r20 = r0
            r22 = r9
            double r22 = r22.getLongitude()
            r19.<init>(r20, r22)
            r11 = r18
            org.osmdroid.util.GeoPoint r18 = new org.osmdroid.util.GeoPoint
            r27 = r18
            r18 = r27
            r19 = r27
            r20 = r9
            double r20 = r20.getLatitude()
            r22 = r4
            r0 = r22
            double r0 = r0.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0
            r22 = r0
            r19.<init>(r20, r22)
            r12 = r18
            org.osmdroid.util.GeoPoint r18 = new org.osmdroid.util.GeoPoint
            r27 = r18
            r18 = r27
            r19 = r27
            r20 = r9
            double r20 = r20.getLatitude()
            r22 = r4
            r0 = r22
            double r0 = r0.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE
            r22 = r0
            r19.<init>(r20, r22)
            r13 = r18
            r18 = r10
            r19 = r11
            double r18 = com.google.appinventor.components.runtime.util.GeometryUtil.distanceBetween((org.osmdroid.api.IGeoPoint) r18, (org.osmdroid.api.IGeoPoint) r19)
            r20 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r18 = r18 / r20
            r14 = r18
            r18 = r12
            r19 = r13
            double r18 = com.google.appinventor.components.runtime.util.GeometryUtil.distanceBetween((org.osmdroid.api.IGeoPoint) r18, (org.osmdroid.api.IGeoPoint) r19)
            r20 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r18 = r18 / r20
            r16 = r18
            r18 = r9
            r19 = r5
            r21 = r7
            r18.setCoords(r19, r21)
            r18 = r4
            r19 = r9
            r20 = r14
            r22 = 0
            org.osmdroid.util.GeoPoint r19 = r19.destinationPoint(r20, r22)
            double r19 = r19.getLatitude()
            r0 = r19
            r2 = r18
            r2.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = r0
            r18 = r4
            r19 = r9
            r20 = r14
            r22 = 1127481344(0x43340000, float:180.0)
            org.osmdroid.util.GeoPoint r19 = r19.destinationPoint(r20, r22)
            double r19 = r19.getLatitude()
            r0 = r19
            r2 = r18
            r2.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0 = r0
            r18 = r4
            r19 = r9
            r20 = r16
            r22 = 1119092736(0x42b40000, float:90.0)
            org.osmdroid.util.GeoPoint r19 = r19.destinationPoint(r20, r22)
            double r19 = r19.getLongitude()
            r0 = r19
            r2 = r18
            r2.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = r0
            r18 = r4
            r19 = r9
            r20 = r16
            r22 = 1132920832(0x43870000, float:270.0)
            org.osmdroid.util.GeoPoint r19 = r19.destinationPoint(r20, r22)
            double r19 = r19.getLongitude()
            r0 = r19
            r2 = r18
            r2.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = r0
            r18 = r4
            r18.clearGeometry()
            r18 = r4
            r0 = r18
            com.google.appinventor.components.runtime.Map r0 = r0.map
            r18 = r0
            com.google.appinventor.components.runtime.util.MapFactory$MapController r18 = r18.getController()
            r19 = r4
            r18.updateFeaturePosition((com.google.appinventor.components.runtime.util.MapFactory.MapRectangle) r19)
            goto L_0x005a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Rectangle.SetCenter(double, double):void");
    }

    public <T> T accept(MapFactory.MapFeatureVisitor<T> mapFeatureVisitor, Object... objArr) {
        return mapFeatureVisitor.visit((MapFactory.MapRectangle) this, objArr);
    }

    /* access modifiers changed from: protected */
    public Geometry computeGeometry() {
        return GeometryUtil.createGeometry(this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud, this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0, this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0, this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE);
    }

    public void updateBounds(double d, double d2, double d3, double d4) {
        this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = d;
        this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = d2;
        this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0 = d3;
        this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = d4;
        clearGeometry();
    }
}
