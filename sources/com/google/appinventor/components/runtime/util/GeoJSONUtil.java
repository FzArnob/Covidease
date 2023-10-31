package com.google.appinventor.components.runtime.util;

import android.text.TextUtils;
import android.util.Log;
import com.google.appinventor.components.runtime.BluetoothConnectionBase;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.LineString;
import com.google.appinventor.components.runtime.Marker;
import com.google.appinventor.components.runtime.Polygon;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.common.annotations.VisibleForTesting;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.osmdroid.util.GeoPoint;

public final class GeoJSONUtil {
    private static final Map<String, Integer> F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho;
    private static final Map<String, PropertyApplication> LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn;

    public interface PropertyApplication {
        void afterConnect(BluetoothConnectionBase bluetoothConnectionBase);

        void apply(MapFactory.MapFeature mapFeature, Object obj);

        void beforeDisconnect(BluetoothConnectionBase bluetoothConnectionBase);
    }

    static {
        Map<String, Integer> map;
        Map<String, PropertyApplication> map2;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        Object obj10;
        Object obj11;
        Object obj12;
        Object obj13;
        Object obj14;
        Object obj15;
        new HashMap();
        Map<String, Integer> map3 = map;
        F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = map3;
        Integer put = map3.put("black", -16777216);
        Integer put2 = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho.put("blue", -14575886);
        Integer put3 = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho.put("cyan", Integer.valueOf(Component.COLOR_CYAN));
        Integer put4 = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho.put("darkgray", Integer.valueOf(Component.COLOR_DARK_GRAY));
        Integer put5 = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho.put("gray", Integer.valueOf(Component.COLOR_GRAY));
        Integer put6 = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho.put("green", Integer.valueOf(Component.COLOR_GREEN));
        Integer put7 = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho.put("lightgray", Integer.valueOf(Component.COLOR_LIGHT_GRAY));
        Integer put8 = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho.put("pink", Integer.valueOf(Component.COLOR_PINK));
        Integer put9 = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho.put("orange", Integer.valueOf(Component.COLOR_ORANGE));
        Integer put10 = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho.put("pink", Integer.valueOf(Component.COLOR_PINK));
        Integer put11 = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho.put("red", Integer.valueOf(Component.COLOR_RED));
        Integer put12 = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho.put("white", -1);
        Integer put13 = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho.put("yellow", Integer.valueOf(Component.COLOR_YELLOW));
        new HashMap();
        Map<String, PropertyApplication> map4 = map2;
        LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = map4;
        new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                MapFactory.MapFeature mapFeature2 = mapFeature;
                Object obj2 = obj;
                if (mapFeature2 instanceof MapFactory.MapMarker) {
                    ((MapFactory.MapMarker) mapFeature2).AnchorHorizontal(GeoJSONUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2));
                }
            }
        };
        PropertyApplication put14 = map4.put("anchorHorizontal".toLowerCase(), obj);
        new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                MapFactory.MapFeature mapFeature2 = mapFeature;
                Object obj2 = obj;
                if (mapFeature2 instanceof MapFactory.MapMarker) {
                    int AnchorHorizontal = ((MapFactory.MapMarker) mapFeature2).AnchorHorizontal();
                }
            }
        };
        PropertyApplication put15 = LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.put("anchorVertical".toLowerCase(), obj2);
        new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                mapFeature.Description(obj.toString());
            }
        };
        PropertyApplication put16 = LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.put("description", obj3);
        new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                mapFeature.Draggable(GeoJSONUtil.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(obj));
            }
        };
        PropertyApplication put17 = LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.put("draggable", obj4);
        new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                int wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
                MapFactory.MapFeature mapFeature2 = mapFeature;
                Object obj2 = obj;
                if (mapFeature2 instanceof MapFactory.HasFill) {
                    MapFactory.HasFill hasFill = (MapFactory.HasFill) mapFeature2;
                    if (obj2 instanceof Number) {
                        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = ((Number) obj2).intValue();
                    } else {
                        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = GeoJSONUtil.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(obj2.toString());
                    }
                    hasFill.FillColor(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
                }
            }
        };
        PropertyApplication put18 = LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.put("fill", obj5);
        new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                MapFactory.MapFeature mapFeature2 = mapFeature;
                Object obj2 = obj;
                if (mapFeature2 instanceof MapFactory.HasFill) {
                    ((MapFactory.HasFill) mapFeature2).FillOpacity(GeoJSONUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2));
                }
            }
        };
        PropertyApplication put19 = LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.put("fill-opacity", obj6);
        new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                MapFactory.MapFeature mapFeature2 = mapFeature;
                Object obj2 = obj;
                if (mapFeature2 instanceof MapFactory.MapMarker) {
                    ((MapFactory.MapMarker) mapFeature2).Height(GeoJSONUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2));
                }
            }
        };
        PropertyApplication put20 = LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.put("height", obj7);
        new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                MapFactory.MapFeature mapFeature2 = mapFeature;
                Object obj2 = obj;
                if (mapFeature2 instanceof MapFactory.MapMarker) {
                    ((MapFactory.MapMarker) mapFeature2).ImageAsset(obj2.toString());
                }
            }
        };
        PropertyApplication put21 = LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.put("image", obj8);
        new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                mapFeature.EnableInfobox(GeoJSONUtil.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(obj));
            }
        };
        PropertyApplication put22 = LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.put("infobox", obj9);
        new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                int wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
                MapFactory.MapFeature mapFeature2 = mapFeature;
                Object obj2 = obj;
                if (mapFeature2 instanceof MapFactory.HasStroke) {
                    MapFactory.HasStroke hasStroke = (MapFactory.HasStroke) mapFeature2;
                    if (obj2 instanceof Number) {
                        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = ((Number) obj2).intValue();
                    } else {
                        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = GeoJSONUtil.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(obj2.toString());
                    }
                    hasStroke.StrokeColor(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
                }
            }
        };
        PropertyApplication put23 = LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.put("stroke", obj10);
        new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                MapFactory.MapFeature mapFeature2 = mapFeature;
                Object obj2 = obj;
                if (mapFeature2 instanceof MapFactory.HasStroke) {
                    ((MapFactory.HasStroke) mapFeature2).StrokeOpacity(GeoJSONUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2));
                }
            }
        };
        PropertyApplication put24 = LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.put("stroke-opacity", obj11);
        new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                MapFactory.MapFeature mapFeature2 = mapFeature;
                Object obj2 = obj;
                if (mapFeature2 instanceof MapFactory.HasStroke) {
                    ((MapFactory.HasStroke) mapFeature2).StrokeWidth(GeoJSONUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2));
                }
            }
        };
        PropertyApplication put25 = LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.put("stroke-width", obj12);
        new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                mapFeature.Title(obj.toString());
            }
        };
        PropertyApplication put26 = LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.put("title", obj13);
        new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                MapFactory.MapFeature mapFeature2 = mapFeature;
                Object obj2 = obj;
                if (mapFeature2 instanceof MapFactory.MapMarker) {
                    ((MapFactory.MapMarker) mapFeature2).Width(GeoJSONUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj2));
                }
            }
        };
        PropertyApplication put27 = LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.put("width", obj14);
        new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                mapFeature.Visible(GeoJSONUtil.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(obj));
            }
        };
        PropertyApplication put28 = LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.put("visible", obj15);
    }

    private GeoJSONUtil() {
    }

    @VisibleForTesting
    static int wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(String str) {
        String lowerCase = str.toLowerCase();
        Integer num = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho.get(lowerCase);
        Integer num2 = num;
        if (num != null) {
            return num2.intValue();
        }
        if (lowerCase.startsWith("#")) {
            return vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(lowerCase.substring(1));
        }
        if (lowerCase.startsWith("&h")) {
            return vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(lowerCase.substring(2));
        }
        return Component.COLOR_RED;
    }

    @VisibleForTesting
    private static int vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(String str) {
        Throwable th;
        int i;
        String str2 = str;
        int i2 = 0;
        if (str2.length() == 3) {
            i = -16777216;
            for (int i3 = 0; i3 < str2.length(); i3++) {
                int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str2.charAt(i3));
                i |= ((hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME << 4) | hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) << ((2 - i3) << 3);
            }
        } else if (str2.length() == 6) {
            int i4 = -16777216;
            for (int i5 = 0; i5 < 3; i5++) {
                i4 = i | (((hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str2.charAt(2 * i5)) << 4) | hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str2.charAt((2 * i5) + 1))) << ((2 - i5) << 3));
            }
        } else if (str2.length() == 8) {
            for (int i6 = 0; i6 < 4; i6++) {
                i2 = i | (((hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str2.charAt(2 * i6)) << 4) | hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str2.charAt((2 * i6) + 1))) << ((3 - i6) << 3));
            }
        } else {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        return i;
    }

    @VisibleForTesting
    private static int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(char c) {
        Throwable th;
        char c2 = c;
        if ('0' <= c2 && c2 <= '9') {
            return c2 - '0';
        }
        if ('a' <= c2 && c2 <= 'f') {
            return (c2 - 'a') + 10;
        }
        if ('A' <= c2 && c2 <= 'F') {
            return (c2 - 'A') + 10;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Invalid hex character. Expected [0-9A-Fa-f].");
        throw th2;
    }

    public static MapFactory.MapFeature processGeoJSONFeature(String str, MapFactory.MapFeatureContainer mapFeatureContainer, YailList yailList) {
        Throwable th;
        Throwable th2;
        String str2 = str;
        MapFactory.MapFeatureContainer mapFeatureContainer2 = mapFeatureContainer;
        String str3 = null;
        YailList yailList2 = null;
        YailList yailList3 = null;
        Iterator it = ((LList) yailList.getCdr()).iterator();
        while (it.hasNext()) {
            YailList yailList4 = (YailList) it.next();
            String string = yailList4.getString(0);
            Object object = yailList4.getObject(1);
            if ("type".equals(string)) {
                str3 = (String) object;
            } else if ("geometry".equals(string)) {
                yailList2 = (YailList) object;
            } else if ("properties".equals(string)) {
                yailList3 = (YailList) object;
            } else {
                int w = Log.w(str2, String.format("Unsupported field \"%s\" in JSON format", new Object[]{string}));
            }
        }
        if (!"Feature".equals(str3)) {
            Throwable th3 = th2;
            new IllegalArgumentException(String.format("Unknown type \"%s\"", new Object[]{str3}));
            throw th3;
        } else if (yailList2 == null) {
            Throwable th4 = th;
            new IllegalArgumentException("No geometry defined for feature.");
            throw th4;
        } else {
            MapFactory.MapFeature hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str2, mapFeatureContainer2, yailList2);
            if (yailList3 != null) {
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str2, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, yailList3);
            }
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        }
    }

    private static MapFactory.MapFeature hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, MapFactory.MapFeatureContainer mapFeatureContainer, YailList yailList) {
        Throwable th;
        Polygon polygon;
        LineString lineString;
        Throwable th2;
        Marker marker;
        Throwable th3;
        Throwable th4;
        String str2 = str;
        MapFactory.MapFeatureContainer mapFeatureContainer2 = mapFeatureContainer;
        String str3 = null;
        YailList yailList2 = null;
        Iterator it = ((LList) yailList.getCdr()).iterator();
        while (it.hasNext()) {
            YailList yailList3 = (YailList) it.next();
            String string = yailList3.getString(0);
            Object object = yailList3.getObject(1);
            if ("type".equals(string)) {
                str3 = (String) object;
            } else if ("coordinates".equals(string)) {
                yailList2 = (YailList) object;
            } else {
                int w = Log.w(str2, String.format("Unsupported field \"%s\" in JSON format", new Object[]{string}));
            }
        }
        if (yailList2 == null) {
            Throwable th5 = th4;
            new IllegalArgumentException("No coordinates found in GeoJSON Feature");
            throw th5;
        }
        MapFactory.MapFeatureContainer mapFeatureContainer3 = mapFeatureContainer2;
        YailList yailList4 = yailList2;
        String str4 = str3;
        MapFactory.MapFeatureContainer mapFeatureContainer4 = mapFeatureContainer3;
        if (MapFactory.MapFeatureType.TYPE_POINT.equals(str4)) {
            YailList yailList5 = yailList4;
            MapFactory.MapFeatureContainer mapFeatureContainer5 = mapFeatureContainer4;
            if (yailList5.length() != 3) {
                Throwable th6 = th3;
                new IllegalArgumentException("Invalid coordinate supplied in GeoJSON");
                throw th6;
            }
            new Marker(mapFeatureContainer5);
            Marker marker2 = marker;
            Marker marker3 = marker2;
            marker2.Latitude(((Number) yailList5.get(2)).doubleValue());
            marker3.Longitude(((Number) yailList5.get(1)).doubleValue());
            return marker3;
        } else if (MapFactory.MapFeatureType.TYPE_LINESTRING.equals(str4)) {
            YailList yailList6 = yailList4;
            MapFactory.MapFeatureContainer mapFeatureContainer6 = mapFeatureContainer4;
            if (yailList6.size() < 2) {
                Throwable th7 = th2;
                new IllegalArgumentException("Too few coordinates supplied in GeoJSON");
                throw th7;
            }
            new LineString(mapFeatureContainer6);
            LineString lineString2 = lineString;
            LineString lineString3 = lineString2;
            lineString2.Points(swapCoordinates(yailList6));
            return lineString3;
        } else if (MapFactory.MapFeatureType.TYPE_POLYGON.equals(str4)) {
            YailList yailList7 = yailList4;
            new Polygon(mapFeatureContainer4);
            Polygon polygon2 = polygon;
            Iterator it2 = yailList7.iterator();
            Iterator it3 = it2;
            Object next = it2.next();
            polygon2.Points(swapCoordinates((YailList) it3.next()));
            if (it3.hasNext()) {
                polygon2.HolePoints(YailList.makeList((List) swapNestedCoordinates((LList) ((Pair) yailList7.getCdr()).getCdr())));
            }
            polygon2.Initialize();
            return polygon2;
        } else if (MapFactory.MapFeatureType.TYPE_MULTIPOLYGON.equals(str4)) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapFeatureContainer4, yailList4);
        } else {
            Throwable th8 = th;
            new IllegalArgumentException();
            throw th8;
        }
    }

    private static MapFactory.MapPolygon hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapFeatureContainer mapFeatureContainer, YailList yailList) {
        Polygon polygon;
        List list;
        List list2;
        new Polygon(mapFeatureContainer);
        Polygon polygon2 = polygon;
        new ArrayList();
        List list3 = list;
        new ArrayList();
        List list4 = list2;
        Iterator it = yailList.iterator();
        Iterator it2 = it;
        Object next = it.next();
        while (it2.hasNext()) {
            YailList yailList2 = (YailList) it2.next();
            boolean add = list3.add(swapCoordinates((YailList) yailList2.get(1)));
            boolean add2 = list4.add(YailList.makeList((List) swapNestedCoordinates((LList) ((Pair) yailList2.getCdr()).getCdr())));
        }
        polygon2.Points(YailList.makeList(list3));
        polygon2.HolePoints(YailList.makeList(list4));
        polygon2.Initialize();
        return polygon2;
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, MapFactory.MapFeature mapFeature, YailList yailList) {
        String str2 = str;
        MapFactory.MapFeature mapFeature2 = mapFeature;
        Iterator it = yailList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            Object obj = next;
            if (next instanceof YailList) {
                YailList yailList2 = (YailList) obj;
                YailList yailList3 = yailList2;
                String obj2 = yailList2.get(1).toString();
                PropertyApplication propertyApplication = LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.get(obj2.toLowerCase());
                PropertyApplication propertyApplication2 = propertyApplication;
                if (propertyApplication != null) {
                    propertyApplication2.apply(mapFeature2, yailList3.get(2));
                } else {
                    int i = Log.i(str2, String.format("Ignoring GeoJSON property \"%s\"", new Object[]{obj2}));
                }
            }
        }
    }

    @VisibleForTesting
    static boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Object obj) {
        Throwable th;
        String str = obj;
        while (!(str instanceof Boolean)) {
            if (str instanceof String) {
                return !"false".equalsIgnoreCase((String) str) && ((String) str).length() != 0;
            }
            if (str instanceof FString) {
                str = str.toString();
            } else {
                Throwable th2 = th;
                new IllegalArgumentException();
                throw th2;
            }
        }
        return ((Boolean) str).booleanValue();
    }

    @VisibleForTesting
    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    static int m67hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Object obj) {
        Throwable th;
        Object obj2 = obj;
        if (obj2 instanceof Number) {
            return ((Number) obj2).intValue();
        }
        if (obj2 instanceof String) {
            return Integer.parseInt((String) obj2);
        }
        if (obj2 instanceof FString) {
            return Integer.parseInt(obj2.toString());
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    @VisibleForTesting
    static float hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Object obj) {
        Throwable th;
        Object obj2 = obj;
        if (obj2 instanceof Number) {
            return ((Number) obj2).floatValue();
        }
        if (obj2 instanceof String) {
            return Float.parseFloat((String) obj2);
        }
        if (obj2 instanceof FString) {
            return Float.parseFloat(obj2.toString());
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    /* renamed from: com.google.appinventor.components.runtime.util.GeoJSONUtil$a */
    static final class C1147a implements MapFactory.MapFeatureVisitor<Void> {
        private final PrintStream hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C1147a(PrintStream printStream, byte b) {
            this(printStream);
            byte b2 = b;
        }

        public final /* synthetic */ Object visit(MapFactory.MapCircle mapCircle, Object[] objArr) {
            Object[] objArr2 = objArr;
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapCircle);
        }

        public final /* synthetic */ Object visit(MapFactory.MapLineString mapLineString, Object[] objArr) {
            Object[] objArr2 = objArr;
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapLineString);
        }

        public final /* synthetic */ Object visit(MapFactory.MapMarker mapMarker, Object[] objArr) {
            Object[] objArr2 = objArr;
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapMarker);
        }

        public final /* synthetic */ Object visit(MapFactory.MapPolygon mapPolygon, Object[] objArr) {
            Object[] objArr2 = objArr;
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapPolygon);
        }

        public final /* synthetic */ Object visit(MapFactory.MapRectangle mapRectangle, Object[] objArr) {
            Object[] objArr2 = objArr;
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapRectangle);
        }

        private C1147a(PrintStream printStream) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = printStream;
        }

        private void vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R(String str) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\"type\":\"");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(str);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\"");
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, Object obj) {
            StringBuilder sb;
            String str2 = str;
            try {
                String jsonRepresentation = JsonUtil.getJsonRepresentation(obj);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",\"");
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(str2);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\":");
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(jsonRepresentation);
            } catch (JSONException e) {
                new StringBuilder("Unable to serialize the value of \"");
                int w = Log.w("GeoJSONUtil", sb.append(str2).append("\" as JSON").toString(), e);
            }
        }

        private void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str, String str2) {
            String str3 = str;
            String str4 = str2;
            if (str4 != null && !TextUtils.isEmpty(str4)) {
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str3, str4);
            }
        }

        private void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str, int i) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",\"");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(str);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\":\"&H");
            String hexString = Integer.toHexString(i);
            for (int i2 = 8; i2 > hexString.length(); i2--) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("0");
            }
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(hexString);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\"");
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(GeoPoint geoPoint) {
            GeoPoint geoPoint2 = geoPoint;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\"geometry\":{\"type\":\"Point\",\"coordinates\":[");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(geoPoint2.getLongitude());
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(geoPoint2.getLatitude());
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(geoPoint2)) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",");
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(geoPoint2.getAltitude());
            }
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("]}");
        }

        private void qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE(String str) {
            StringBuilder sb;
            PrintStream printStream = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            new StringBuilder(",\"properties\":{\"$Type\":\"");
            printStream.print(sb.append(str).append("\"").toString());
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapFeature mapFeature) {
            MapFactory.MapFeature mapFeature2 = mapFeature;
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("description", mapFeature2.Description());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("draggable", Boolean.valueOf(mapFeature2.Draggable()));
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("infobox", Boolean.valueOf(mapFeature2.EnableInfobox()));
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("title", mapFeature2.Title());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("visible", Boolean.valueOf(mapFeature2.Visible()));
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.HasStroke hasStroke) {
            MapFactory.HasStroke hasStroke2 = hasStroke;
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("stroke", hasStroke2.StrokeColor());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("stroke-opacity", Float.valueOf(hasStroke2.StrokeOpacity()));
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("stroke-width", Integer.valueOf(hasStroke2.StrokeWidth()));
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.HasFill hasFill) {
            MapFactory.HasFill hasFill2 = hasFill;
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("fill", hasFill2.FillColor());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("fill-opacity", Float.valueOf(hasFill2.FillOpacity()));
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(List<GeoPoint> list) {
            boolean z = true;
            for (GeoPoint next : list) {
                if (!z) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(',');
                }
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("[");
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(next.getLongitude());
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",");
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(next.getLatitude());
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(next)) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",");
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(next.getAltitude());
                }
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("]");
                z = false;
            }
        }

        private Void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapMarker mapMarker) {
            MapFactory.MapMarker mapMarker2 = mapMarker;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("{");
            vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R("Feature");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(',');
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapMarker2.getCentroid());
            qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE(mapMarker2.getClass().getName());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.MapFeature) mapMarker2);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasStroke) mapMarker2);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasFill) mapMarker2);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("anchorHorizontal", Integer.valueOf(mapMarker2.AnchorHorizontal()));
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("anchorVertical", Integer.valueOf(mapMarker2.AnchorVertical()));
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("height", Integer.valueOf(mapMarker2.Height()));
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("image", mapMarker2.ImageAsset());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("width", Integer.valueOf(mapMarker2.Width()));
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("}}");
            return null;
        }

        private Void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapLineString mapLineString) {
            MapFactory.MapLineString mapLineString2 = mapLineString;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("{");
            vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R("Feature");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(',');
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\"geometry\":{\"type\":\"LineString\",\"coordinates\":[");
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapLineString2.getPoints());
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("]}");
            qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE(mapLineString2.getClass().getName());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.MapFeature) mapLineString2);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasStroke) mapLineString2);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("}}");
            return null;
        }

        private Void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapPolygon mapPolygon) {
            MapFactory.MapPolygon mapPolygon2 = mapPolygon;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("{");
            vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R("Feature");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(',');
            MapFactory.MapPolygon mapPolygon3 = mapPolygon2;
            if (mapPolygon3.getPoints().size() > 1) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\"geometry\":{\"type\":\"MultiPolygon\",\"coordinates\":[");
                Iterator<List<GeoPoint>> it = mapPolygon3.getPoints().iterator();
                Iterator<List<List<GeoPoint>>> it2 = mapPolygon3.getHolePoints().iterator();
                boolean z = true;
                while (true) {
                    boolean z2 = z;
                    if (!it.hasNext()) {
                        break;
                    }
                    if (!z2) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",");
                    }
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("[");
                    hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(it.next());
                    if (it2.hasNext()) {
                        for (List list : it2.next()) {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",");
                            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((List<GeoPoint>) list);
                        }
                    }
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("]");
                    z = false;
                }
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("]}");
            } else {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[");
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapPolygon3.getPoints().get(0));
                if (!mapPolygon3.getHolePoints().isEmpty()) {
                    for (List list2 : mapPolygon3.getHolePoints().get(0)) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",");
                        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((List<GeoPoint>) list2);
                    }
                }
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("]}");
            }
            qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE(mapPolygon2.getClass().getName());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.MapFeature) mapPolygon2);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasStroke) mapPolygon2);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasFill) mapPolygon2);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("}}");
            return null;
        }

        private Void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapCircle mapCircle) {
            MapFactory.MapCircle mapCircle2 = mapCircle;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("{");
            vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R("Feature");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(',');
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapCircle2.getCentroid());
            qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE(mapCircle2.getClass().getName());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.MapFeature) mapCircle2);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasStroke) mapCircle2);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasFill) mapCircle2);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("}}");
            return null;
        }

        private Void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapRectangle mapRectangle) {
            StringBuilder sb;
            StringBuilder sb2;
            StringBuilder sb3;
            StringBuilder sb4;
            StringBuilder sb5;
            MapFactory.MapRectangle mapRectangle2 = mapRectangle;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("{");
            vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R("Feature");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[");
            PrintStream printStream = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            new StringBuilder("[");
            printStream.print(sb.append(mapRectangle2.WestLongitude()).append(",").append(mapRectangle2.NorthLatitude()).append("],").toString());
            PrintStream printStream2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            new StringBuilder("[");
            printStream2.print(sb2.append(mapRectangle2.WestLongitude()).append(",").append(mapRectangle2.SouthLatitude()).append("],").toString());
            PrintStream printStream3 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            new StringBuilder("[");
            printStream3.print(sb3.append(mapRectangle2.EastLongitude()).append(",").append(mapRectangle2.SouthLatitude()).append("],").toString());
            PrintStream printStream4 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            new StringBuilder("[");
            printStream4.print(sb4.append(mapRectangle2.EastLongitude()).append(",").append(mapRectangle2.NorthLatitude()).append("],").toString());
            PrintStream printStream5 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            new StringBuilder("[");
            printStream5.print(sb5.append(mapRectangle2.WestLongitude()).append(",").append(mapRectangle2.NorthLatitude()).append("]]}").toString());
            qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE(mapRectangle2.getClass().getName());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.MapFeature) mapRectangle2);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasStroke) mapRectangle2);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasFill) mapRectangle2);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("NorthLatitude", Double.valueOf(mapRectangle2.NorthLatitude()));
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("WestLongitude", Double.valueOf(mapRectangle2.WestLongitude()));
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("SouthLatitude", Double.valueOf(mapRectangle2.SouthLatitude()));
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("EastLongitude", Double.valueOf(mapRectangle2.EastLongitude()));
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("}}");
            return null;
        }

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
        private static boolean m68hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(GeoPoint geoPoint) {
            return Double.compare(0.0d, geoPoint.getAltitude()) != 0;
        }
    }

    public static void writeFeaturesAsGeoJSON(List<MapFactory.MapFeature> list, String str) throws IOException {
        PrintStream printStream;
        OutputStream outputStream;
        MapFactory.MapFeatureVisitor mapFeatureVisitor;
        List<MapFactory.MapFeature> list2 = list;
        try {
            new FileOutputStream(str);
            new PrintStream(outputStream);
            PrintStream printStream2 = printStream;
            new C1147a(printStream2, (byte) 0);
            MapFactory.MapFeatureVisitor mapFeatureVisitor2 = mapFeatureVisitor;
            printStream2.print("{\"type\": \"FeatureCollection\", \"features\":[");
            Iterator<MapFactory.MapFeature> it = list2.iterator();
            Iterator<MapFactory.MapFeature> it2 = it;
            if (it.hasNext()) {
                Object accept = it2.next().accept(mapFeatureVisitor2, new Object[0]);
                while (it2.hasNext()) {
                    MapFactory.MapFeature next = it2.next();
                    printStream2.print(',');
                    Object accept2 = next.accept(mapFeatureVisitor2, new Object[0]);
                }
            }
            printStream2.print("]}");
            IOUtils.closeQuietly("GeoJSONUtil", printStream2);
        } catch (Throwable th) {
            IOUtils.closeQuietly("GeoJSONUtil", (Closeable) null);
            throw th;
        }
    }

    public static YailList swapCoordinates(YailList yailList) {
        YailList yailList2 = yailList;
        Iterator it = yailList2.iterator();
        Iterator it2 = it;
        Object next = it.next();
        while (it2.hasNext()) {
            YailList yailList3 = (YailList) it2.next();
            YailList yailList4 = yailList3;
            Object obj = yailList3.get(1);
            Pair pair = (Pair) yailList4.getCdr();
            pair.setCar(yailList4.get(2));
            ((Pair) pair.getCdr()).setCar(obj);
        }
        return yailList2;
    }

    public static LList swapNestedCoordinates(LList lList) {
        LList lList2 = lList;
        LList lList3 = lList2;
        while (true) {
            LList lList4 = lList3;
            if (lList4.isEmpty()) {
                return lList2;
            }
            YailList swapCoordinates = swapCoordinates((YailList) lList4.get(0));
            lList3 = (LList) ((Pair) lList4).getCdr();
        }
    }
}
