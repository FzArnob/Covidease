package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.DispatchableError;
import com.google.appinventor.components.runtime.errors.IterationError;
import com.google.appinventor.components.runtime.util.MapFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;

public final class GeometryUtil {
    public static final double EARTH_RADIUS = 6378137.0d;
    public static final double ONE_DEG_IN_METERS = 111319.49079327358d;
    public static final int WEB_MERCATOR_SRID = 4326;
    private static final GeometryFactory hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    static {
        GeometryFactory geometryFactory;
        PrecisionModel precisionModel;
        new PrecisionModel();
        new GeometryFactory(precisionModel, WEB_MERCATOR_SRID);
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = geometryFactory;
    }

    private GeometryUtil() {
    }

    public static double coerceToDouble(Object obj) {
        Object obj2 = obj;
        if (obj2 instanceof Number) {
            return ((Number) obj2).doubleValue();
        }
        try {
            return Double.parseDouble(obj2.toString());
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    public static GeoPoint coerceToPoint(Object obj, Object obj2) {
        Throwable th;
        Throwable th2;
        GeoPoint geoPoint;
        Throwable th3;
        Throwable th4;
        double coerceToDouble = coerceToDouble(obj);
        double coerceToDouble2 = coerceToDouble(obj2);
        if (Double.isNaN(coerceToDouble)) {
            Throwable th5 = th4;
            new IllegalArgumentException("Latitude must be a numeric.");
            throw th5;
        } else if (Double.isNaN(coerceToDouble2)) {
            Throwable th6 = th3;
            new IllegalArgumentException("Longitude must be a numeric.");
            throw th6;
        } else if (coerceToDouble < -90.0d || coerceToDouble > 90.0d) {
            Throwable th7 = th;
            new IllegalArgumentException("Latitude must be between -90 and 90.");
            throw th7;
        } else if (coerceToDouble2 < -180.0d || coerceToDouble2 > 180.0d) {
            Throwable th8 = th2;
            new IllegalArgumentException("Longitude must be between -180 and 180.");
            throw th8;
        } else {
            new GeoPoint(coerceToDouble, coerceToDouble2);
            return geoPoint;
        }
    }

    public static YailList asYailList(IGeoPoint iGeoPoint) {
        IGeoPoint iGeoPoint2 = iGeoPoint;
        Object[] objArr = new Object[2];
        objArr[0] = Double.valueOf(iGeoPoint2.getLatitude());
        Object[] objArr2 = objArr;
        objArr2[1] = Double.valueOf(iGeoPoint2.getLongitude());
        return YailList.makeList(objArr2);
    }

    public static YailList pointsListToYailList(List<? extends IGeoPoint> list) {
        List list2;
        new ArrayList();
        List list3 = list2;
        for (IGeoPoint asYailList : list) {
            boolean add = list3.add(asYailList(asYailList));
        }
        return YailList.makeList(list3);
    }

    public static GeoPoint pointFromYailList(YailList yailList) {
        Throwable th;
        Throwable th2;
        YailList yailList2 = yailList;
        if (yailList2.length() < 3) {
            Throwable th3 = th2;
            Object[] objArr = new Object[2];
            objArr[0] = 2;
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(yailList2.length() - 1);
            new DispatchableError(ErrorMessages.ERROR_INVALID_NUMBER_OF_VALUES_IN_POINT, objArr2);
            throw th3;
        }
        try {
            return coerceToPoint(yailList2.get(1), yailList2.get(2));
        } catch (IllegalArgumentException e) {
            Throwable th4 = th;
            Object[] objArr3 = new Object[2];
            objArr3[0] = yailList2.get(1);
            Object[] objArr4 = objArr3;
            objArr4[1] = yailList2.get(2);
            new DispatchableError(ErrorMessages.ERROR_INVALID_POINT, objArr4);
            throw th4;
        }
    }

    public static List<GeoPoint> pointsFromYailList(YailList yailList) {
        List<GeoPoint> list;
        new ArrayList();
        List<GeoPoint> list2 = list;
        Iterator it = yailList.iterator();
        int i = 1;
        Object next = it.next();
        while (it.hasNext()) {
            try {
                boolean add = list2.add(pointFromYailList((YailList) TypeUtil.castNotNull(it.next(), YailList.class, "list")));
                i++;
            } catch (DispatchableError e) {
                throw IterationError.fromError(i, e);
            }
        }
        return list2;
    }

    public static Geometry createGeometry(GeoPoint geoPoint) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createPoint(geoPointToCoordinate(geoPoint));
    }

    public static Geometry createGeometry(List<GeoPoint> list) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createLineString(pointsToCoordinates(list));
    }

    public static Geometry createGeometry(double d, double d2, double d3, double d4) {
        Coordinate coordinate;
        Coordinate coordinate2;
        Coordinate coordinate3;
        Coordinate coordinate4;
        Coordinate coordinate5;
        double d5 = d;
        double d6 = d2;
        double d7 = d3;
        double d8 = d4;
        GeometryFactory geometryFactory = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        Coordinate[] coordinateArr = new Coordinate[5];
        new Coordinate(d6, d5);
        coordinateArr[0] = coordinate;
        Coordinate[] coordinateArr2 = coordinateArr;
        new Coordinate(d6, d7);
        coordinateArr2[1] = coordinate2;
        Coordinate[] coordinateArr3 = coordinateArr2;
        new Coordinate(d8, d7);
        coordinateArr3[2] = coordinate3;
        Coordinate[] coordinateArr4 = coordinateArr3;
        new Coordinate(d8, d5);
        coordinateArr4[3] = coordinate4;
        Coordinate[] coordinateArr5 = coordinateArr4;
        new Coordinate(d6, d5);
        coordinateArr5[4] = coordinate5;
        return geometryFactory.createPolygon(coordinateArr5);
    }

    public static Geometry createGeometry(List<List<GeoPoint>> list, List<List<List<GeoPoint>>> list2) {
        Throwable th;
        Throwable th2;
        List<List<GeoPoint>> list3 = list;
        List<List<List<GeoPoint>>> list4 = list2;
        if (list3 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("points must not be null.");
            throw th3;
        } else if (list4 == null || list4.isEmpty() || list4.size() == list3.size()) {
            Polygon[] polygonArr = new Polygon[list3.size()];
            int i = 0;
            if (list4 == null || list4.isEmpty()) {
                for (List ringToPolygon : list3) {
                    int i2 = i;
                    i++;
                    polygonArr[i2] = ringToPolygon(ringToPolygon);
                }
            } else {
                Iterator<List<List<GeoPoint>>> it = list4.iterator();
                for (List<GeoPoint> ringToPolygon2 : list3) {
                    int i3 = i;
                    i++;
                    polygonArr[i3] = ringToPolygon(ringToPolygon2, it.next());
                }
            }
            if (polygonArr.length == 1) {
                return polygonArr[0];
            }
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createMultiPolygon(polygonArr);
        } else {
            Throwable th4 = th;
            new IllegalArgumentException("holes must either be null or the same length as points.");
            throw th4;
        }
    }

    public static GeoPoint getMidpoint(List<GeoPoint> list) {
        GeoPoint geoPoint;
        GeoPoint geoPoint2;
        List<GeoPoint> list2 = list;
        if (list2.isEmpty()) {
            new GeoPoint(0.0d, 0.0d);
            return geoPoint2;
        } else if (list2.size() != 1) {
            return jtsPointToGeoPoint(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createLineString(pointsToCoordinates(list2)).getCentroid());
        } else {
            new GeoPoint(list2.get(0));
            return geoPoint;
        }
    }

    public static GeoPoint getCentroid(List<List<GeoPoint>> list, List<List<List<GeoPoint>>> list2) {
        return jtsPointToGeoPoint(createGeometry(list, list2).getCentroid());
    }

    public static Polygon ringToPolygon(List<GeoPoint> list) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createPolygon(geoPointsToLinearRing(list));
    }

    public static Coordinate[] pointsToCoordinates(List<GeoPoint> list) {
        List<GeoPoint> list2 = list;
        List<GeoPoint> list3 = list2;
        boolean equals = list2.get(0).equals(list3.get(list3.size() - 1));
        Coordinate[] coordinateArr = new Coordinate[(list2.size() + (equals ? 0 : 1))];
        int i = 0;
        for (GeoPoint geoPointToCoordinate : list2) {
            int i2 = i;
            i++;
            coordinateArr[i2] = geoPointToCoordinate(geoPointToCoordinate);
        }
        if (!equals) {
            coordinateArr[i] = coordinateArr[0];
        }
        return coordinateArr;
    }

    public static LinearRing geoPointsToLinearRing(List<GeoPoint> list) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createLinearRing(pointsToCoordinates(list));
    }

    public static Polygon ringToPolygon(List<GeoPoint> list, List<List<GeoPoint>> list2) {
        List<List<GeoPoint>> list3 = list2;
        LinearRing geoPointsToLinearRing = geoPointsToLinearRing(list);
        LinearRing[] linearRingArr = new LinearRing[list3.size()];
        int i = 0;
        for (List geoPointsToLinearRing2 : list3) {
            int i2 = i;
            i++;
            linearRingArr[i2] = geoPointsToLinearRing(geoPointsToLinearRing2);
        }
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createPolygon(geoPointsToLinearRing, linearRingArr);
    }

    public static GeoPoint jtsPointToGeoPoint(Point point) {
        GeoPoint geoPoint;
        Point point2 = point;
        new GeoPoint(point2.getY(), point2.getX());
        return geoPoint;
    }

    public static Coordinate geoPointToCoordinate(GeoPoint geoPoint) {
        Coordinate coordinate;
        GeoPoint geoPoint2 = geoPoint;
        new Coordinate(geoPoint2.getLongitude(), geoPoint2.getLatitude());
        return coordinate;
    }

    public static double distanceBetween(IGeoPoint iGeoPoint, IGeoPoint iGeoPoint2) {
        IGeoPoint iGeoPoint3 = iGeoPoint;
        IGeoPoint iGeoPoint4 = iGeoPoint2;
        double radians = Math.toRadians(iGeoPoint3.getLatitude());
        double radians2 = Math.toRadians(iGeoPoint3.getLongitude());
        double radians3 = Math.toRadians(iGeoPoint4.getLatitude());
        double pow = Math.pow(Math.sin((radians3 - radians) / 2.0d), 2.0d) + (Math.cos(radians) * Math.cos(radians3) * Math.pow(Math.sin((Math.toRadians(iGeoPoint4.getLongitude()) - radians2) / 2.0d), 2.0d));
        return 2.0d * Math.atan2(Math.sqrt(pow), Math.sqrt(1.0d - pow)) * 6378137.0d;
    }

    public static double distanceBetween(MapFactory.MapMarker mapMarker, GeoPoint geoPoint) {
        return distanceBetween(mapMarker.getLocation(), (IGeoPoint) geoPoint);
    }

    public static double distanceBetween(MapFactory.MapMarker mapMarker, MapFactory.MapMarker mapMarker2) {
        return distanceBetween(mapMarker.getLocation(), mapMarker2.getLocation());
    }

    public static double distanceBetweenEdges(MapFactory.MapMarker mapMarker, MapFactory.MapLineString mapLineString) {
        return 111319.49079327358d * mapMarker.getGeometry().distance(mapLineString.getGeometry());
    }

    public static double distanceBetweenEdges(MapFactory.MapMarker mapMarker, MapFactory.MapPolygon mapPolygon) {
        return 111319.49079327358d * mapMarker.getGeometry().distance(mapPolygon.getGeometry());
    }

    public static double distanceBetweenEdges(MapFactory.MapMarker mapMarker, MapFactory.MapCircle mapCircle) {
        MapFactory.MapCircle mapCircle2 = mapCircle;
        double distanceTo = ((double) mapMarker.getCentroid().distanceTo(mapCircle2.getCentroid())) - mapCircle2.Radius();
        double d = distanceTo;
        if (distanceTo < 0.0d) {
            return 0.0d;
        }
        return d;
    }

    public static double distanceBetweenEdges(MapFactory.MapMarker mapMarker, MapFactory.MapRectangle mapRectangle) {
        return 111319.49079327358d * mapMarker.getGeometry().distance(mapRectangle.getGeometry());
    }

    public static double distanceBetweenEdges(MapFactory.MapLineString mapLineString, GeoPoint geoPoint) {
        return 111319.49079327358d * mapLineString.getGeometry().distance(createGeometry(geoPoint));
    }

    public static double distanceBetweenEdges(MapFactory.MapLineString mapLineString, MapFactory.MapLineString mapLineString2) {
        return 111319.49079327358d * mapLineString.getGeometry().distance(mapLineString2.getGeometry());
    }

    public static double distanceBetweenEdges(MapFactory.MapLineString mapLineString, MapFactory.MapPolygon mapPolygon) {
        return 111319.49079327358d * mapLineString.getGeometry().distance(mapPolygon.getGeometry());
    }

    public static double distanceBetweenEdges(MapFactory.MapLineString mapLineString, MapFactory.MapCircle mapCircle) {
        MapFactory.MapCircle mapCircle2 = mapCircle;
        double distance = (111319.49079327358d * mapLineString.getGeometry().distance(createGeometry(mapCircle2.getCentroid()))) - mapCircle2.Radius();
        double d = distance;
        if (distance < 0.0d) {
            return 0.0d;
        }
        return d;
    }

    public static double distanceBetweenEdges(MapFactory.MapLineString mapLineString, MapFactory.MapRectangle mapRectangle) {
        return 111319.49079327358d * mapLineString.getGeometry().distance(mapRectangle.getGeometry());
    }

    public static double distanceBetweenEdges(MapFactory.MapPolygon mapPolygon, GeoPoint geoPoint) {
        return 111319.49079327358d * mapPolygon.getGeometry().distance(createGeometry(geoPoint));
    }

    public static double distanceBetweenEdges(MapFactory.MapPolygon mapPolygon, MapFactory.MapPolygon mapPolygon2) {
        return 111319.49079327358d * mapPolygon.getGeometry().distance(mapPolygon2.getGeometry());
    }

    public static double distanceBetweenEdges(MapFactory.MapPolygon mapPolygon, MapFactory.MapCircle mapCircle) {
        MapFactory.MapCircle mapCircle2 = mapCircle;
        double distance = (111319.49079327358d * mapPolygon.getGeometry().distance(createGeometry(mapCircle2.getCentroid()))) - mapCircle2.Radius();
        double d = distance;
        if (distance < 0.0d) {
            return 0.0d;
        }
        return d;
    }

    public static double distanceBetweenEdges(MapFactory.MapPolygon mapPolygon, MapFactory.MapRectangle mapRectangle) {
        return 111319.49079327358d * mapPolygon.getGeometry().distance(mapRectangle.getGeometry());
    }

    public static double distanceBetweenEdges(MapFactory.MapCircle mapCircle, GeoPoint geoPoint) {
        MapFactory.MapCircle mapCircle2 = mapCircle;
        double distanceBetween = distanceBetween((IGeoPoint) mapCircle2.getCentroid(), (IGeoPoint) geoPoint) - mapCircle2.Radius();
        double d = distanceBetween;
        if (distanceBetween < 0.0d) {
            return 0.0d;
        }
        return d;
    }

    public static double distanceBetweenEdges(MapFactory.MapCircle mapCircle, MapFactory.MapCircle mapCircle2) {
        MapFactory.MapCircle mapCircle3 = mapCircle;
        MapFactory.MapCircle mapCircle4 = mapCircle2;
        double distanceBetween = (distanceBetween((IGeoPoint) mapCircle3.getCentroid(), (IGeoPoint) mapCircle4.getCentroid()) - mapCircle3.Radius()) - mapCircle4.Radius();
        double d = distanceBetween;
        if (distanceBetween < 0.0d) {
            return 0.0d;
        }
        return d;
    }

    public static double distanceBetweenEdges(MapFactory.MapCircle mapCircle, MapFactory.MapRectangle mapRectangle) {
        MapFactory.MapCircle mapCircle2 = mapCircle;
        double distance = (111319.49079327358d * mapRectangle.getGeometry().distance(createGeometry(mapCircle2.getCentroid()))) - mapCircle2.Radius();
        double d = distance;
        if (distance < 0.0d) {
            return 0.0d;
        }
        return d;
    }

    public static double distanceBetweenEdges(MapFactory.MapRectangle mapRectangle, GeoPoint geoPoint) {
        return 111319.49079327358d * mapRectangle.getGeometry().distance(createGeometry(geoPoint));
    }

    public static double distanceBetweenEdges(MapFactory.MapRectangle mapRectangle, MapFactory.MapRectangle mapRectangle2) {
        return 111319.49079327358d * mapRectangle.getGeometry().distance(mapRectangle2.getGeometry());
    }

    public static double distanceBetweenCentroids(MapFactory.MapMarker mapMarker, MapFactory.MapLineString mapLineString) {
        return distanceBetween((IGeoPoint) mapMarker.getCentroid(), (IGeoPoint) mapLineString.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapMarker mapMarker, MapFactory.MapPolygon mapPolygon) {
        return distanceBetween((IGeoPoint) mapMarker.getCentroid(), (IGeoPoint) mapPolygon.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapMarker mapMarker, MapFactory.MapCircle mapCircle) {
        return distanceBetween((IGeoPoint) mapMarker.getCentroid(), (IGeoPoint) mapCircle.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapMarker mapMarker, MapFactory.MapRectangle mapRectangle) {
        return distanceBetween((IGeoPoint) mapMarker.getCentroid(), (IGeoPoint) mapRectangle.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapLineString mapLineString, GeoPoint geoPoint) {
        return distanceBetween((IGeoPoint) mapLineString.getCentroid(), (IGeoPoint) geoPoint);
    }

    public static double distanceBetweenCentroids(MapFactory.MapLineString mapLineString, MapFactory.MapLineString mapLineString2) {
        return distanceBetween((IGeoPoint) mapLineString.getCentroid(), (IGeoPoint) mapLineString2.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapLineString mapLineString, MapFactory.MapPolygon mapPolygon) {
        return distanceBetween((IGeoPoint) mapLineString.getCentroid(), (IGeoPoint) mapPolygon.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapLineString mapLineString, MapFactory.MapCircle mapCircle) {
        return distanceBetween((IGeoPoint) mapLineString.getCentroid(), (IGeoPoint) mapCircle.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapLineString mapLineString, MapFactory.MapRectangle mapRectangle) {
        return distanceBetween((IGeoPoint) mapLineString.getCentroid(), (IGeoPoint) mapRectangle.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapPolygon mapPolygon, GeoPoint geoPoint) {
        return distanceBetween((IGeoPoint) mapPolygon.getCentroid(), (IGeoPoint) geoPoint);
    }

    public static double distanceBetweenCentroids(MapFactory.MapPolygon mapPolygon, MapFactory.MapPolygon mapPolygon2) {
        return distanceBetween((IGeoPoint) mapPolygon.getCentroid(), (IGeoPoint) mapPolygon2.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapPolygon mapPolygon, MapFactory.MapCircle mapCircle) {
        return distanceBetween((IGeoPoint) mapPolygon.getCentroid(), (IGeoPoint) mapCircle.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapPolygon mapPolygon, MapFactory.MapRectangle mapRectangle) {
        return distanceBetween((IGeoPoint) mapPolygon.getCentroid(), (IGeoPoint) mapRectangle.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapCircle mapCircle, GeoPoint geoPoint) {
        return distanceBetween((IGeoPoint) mapCircle.getCentroid(), (IGeoPoint) geoPoint);
    }

    public static double distanceBetweenCentroids(MapFactory.MapCircle mapCircle, MapFactory.MapCircle mapCircle2) {
        return distanceBetween((IGeoPoint) mapCircle.getCentroid(), (IGeoPoint) mapCircle2.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapCircle mapCircle, MapFactory.MapRectangle mapRectangle) {
        return distanceBetween((IGeoPoint) mapCircle.getCentroid(), (IGeoPoint) mapRectangle.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapRectangle mapRectangle, GeoPoint geoPoint) {
        return distanceBetween((IGeoPoint) mapRectangle.getCentroid(), (IGeoPoint) geoPoint);
    }

    public static double distanceBetweenCentroids(MapFactory.MapRectangle mapRectangle, MapFactory.MapRectangle mapRectangle2) {
        return distanceBetween((IGeoPoint) mapRectangle.getCentroid(), (IGeoPoint) mapRectangle2.getCentroid());
    }

    public static double bearingTo(MapFactory.MapMarker mapMarker, MapFactory.MapMarker mapMarker2) {
        return mapMarker.getCentroid().bearingTo(mapMarker2.getCentroid());
    }

    public static double bearingToEdge(MapFactory.MapMarker mapMarker, MapFactory.MapLineString mapLineString) {
        return mapMarker.getCentroid().bearingTo(mapLineString.getCentroid());
    }

    public static double bearingToEdge(MapFactory.MapMarker mapMarker, MapFactory.MapPolygon mapPolygon) {
        return mapMarker.getCentroid().bearingTo(mapPolygon.getCentroid());
    }

    public static double bearingToEdge(MapFactory.MapMarker mapMarker, MapFactory.MapRectangle mapRectangle) {
        return mapMarker.getCentroid().bearingTo(mapRectangle.getCentroid());
    }

    public static double bearingToEdge(MapFactory.MapMarker mapMarker, MapFactory.MapCircle mapCircle) {
        return mapMarker.getCentroid().bearingTo(mapCircle.getCentroid());
    }

    public static double bearingToCentroid(MapFactory.MapMarker mapMarker, MapFactory.MapLineString mapLineString) {
        return mapMarker.getCentroid().bearingTo(mapLineString.getCentroid());
    }

    public static double bearingToCentroid(MapFactory.MapMarker mapMarker, MapFactory.MapPolygon mapPolygon) {
        return mapMarker.getCentroid().bearingTo(mapPolygon.getCentroid());
    }

    public static double bearingToCentroid(MapFactory.MapMarker mapMarker, MapFactory.MapRectangle mapRectangle) {
        return mapMarker.getCentroid().bearingTo(mapRectangle.getCentroid());
    }

    public static double bearingToCentroid(MapFactory.MapMarker mapMarker, MapFactory.MapCircle mapCircle) {
        return mapMarker.getCentroid().bearingTo(mapCircle.getCentroid());
    }

    public static boolean isValidLatitude(double d) {
        double d2 = d;
        return -90.0d <= d2 && d2 <= 90.0d;
    }

    public static boolean isValidLongitude(double d) {
        double d2 = d;
        return -180.0d <= d2 && d2 <= 180.0d;
    }

    public static List<GeoPoint> polygonToList(JSONArray jSONArray) throws JSONException {
        List<GeoPoint> list;
        Throwable th;
        Object obj;
        Object obj2;
        Throwable th2;
        JSONArray jSONArray2 = jSONArray;
        new ArrayList(jSONArray2.length());
        List<GeoPoint> list2 = list;
        if (jSONArray2.length() < 3) {
            Throwable th3 = th2;
            new DispatchableError(ErrorMessages.ERROR_POLYGON_PARSE_ERROR, "Too few points in Polygon, expected 3.");
            throw th3;
        }
        for (int i = 0; i < jSONArray2.length(); i++) {
            JSONArray jSONArray3 = jSONArray2.getJSONArray(i);
            JSONArray jSONArray4 = jSONArray3;
            if (jSONArray3.length() < 2) {
                Throwable th4 = th;
                new JSONException("Invalid number of dimensions in polygon, expected 2.");
                throw th4;
            }
            if (jSONArray4.length() == 2) {
                new GeoPoint(jSONArray4.getDouble(0), jSONArray4.getDouble(1));
                boolean add = list2.add(obj2);
            } else {
                new GeoPoint(jSONArray4.getDouble(0), jSONArray4.getDouble(1), jSONArray4.getDouble(2));
                boolean add2 = list2.add(obj);
            }
        }
        return list2;
    }

    public static List<List<GeoPoint>> multiPolygonToList(JSONArray jSONArray) throws JSONException {
        List<List<GeoPoint>> list;
        JSONArray jSONArray2 = jSONArray;
        new ArrayList();
        List<List<GeoPoint>> list2 = list;
        if (jSONArray2.length() == 0) {
            return list2;
        }
        if (jSONArray2.getJSONArray(0).optJSONArray(0) == null) {
            boolean add = list2.add(polygonToList(jSONArray2));
        } else {
            for (int i = 0; i < jSONArray2.length(); i++) {
                boolean add2 = list2.add(polygonToList(jSONArray2.getJSONArray(i)));
            }
        }
        return list2;
    }

    public static YailList multiPolygonToYailList(List<List<GeoPoint>> list) {
        List list2;
        new LinkedList();
        List list3 = list2;
        for (List pointsListToYailList : list) {
            boolean add = list3.add(pointsListToYailList(pointsListToYailList));
        }
        return YailList.makeList(list3);
    }

    public static List<List<GeoPoint>> multiPolygonFromYailList(YailList yailList) {
        List<List<GeoPoint>> list;
        new ArrayList();
        List<List<GeoPoint>> list2 = list;
        ListIterator listIterator = yailList.listIterator(1);
        while (listIterator.hasNext()) {
            boolean add = list2.add(pointsFromYailList((YailList) TypeUtil.castNotNull(listIterator.next(), YailList.class, "list")));
        }
        return list2;
    }

    public static List<List<List<GeoPoint>>> multiPolygonHolesFromYailList(YailList yailList) {
        List<List<List<GeoPoint>>> list;
        new ArrayList();
        List<List<List<GeoPoint>>> list2 = list;
        ListIterator listIterator = yailList.listIterator(1);
        int i = 1;
        while (listIterator.hasNext()) {
            try {
                boolean add = list2.add(multiPolygonFromYailList((YailList) TypeUtil.castNotNull(listIterator.next(), YailList.class, "list")));
                i++;
            } catch (DispatchableError e) {
                throw IterationError.fromError(i, e);
            }
        }
        return list2;
    }

    public static List<List<List<GeoPoint>>> multiPolygonHolesToList(JSONArray jSONArray) throws JSONException {
        List<List<List<GeoPoint>>> list;
        JSONArray jSONArray2 = jSONArray;
        new ArrayList();
        List<List<List<GeoPoint>>> list2 = list;
        if (jSONArray2.getJSONArray(0).getJSONArray(0).optJSONArray(0) == null) {
            boolean add = list2.add(multiPolygonToList(jSONArray2));
        } else {
            for (int i = 0; i < jSONArray2.length(); i++) {
                boolean add2 = list2.add(multiPolygonToList(jSONArray2.getJSONArray(i)));
            }
        }
        return list2;
    }

    public static boolean isPolygon(YailList yailList) {
        YailList yailList2 = yailList;
        if (yailList2.size() < 3) {
            return false;
        }
        try {
            GeoPoint pointFromYailList = pointFromYailList((YailList) TypeUtil.castNotNull(yailList2.get(1), YailList.class, "list"));
            return true;
        } catch (DispatchableError e) {
            return false;
        }
    }

    public static boolean isMultiPolygon(YailList yailList) {
        YailList yailList2 = yailList;
        if (yailList2.size() <= 0 || !isPolygon((YailList) TypeUtil.castNotNull(yailList2.get(1), YailList.class, "list"))) {
            return false;
        }
        return true;
    }
}
