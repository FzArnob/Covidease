package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.util.Log;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.GeoJSONUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SimpleObject
public abstract class MapFeatureContainerBase extends AndroidViewComponent implements MapFactory.MapFeatureContainer {
    private static final int ERROR_CODE_IO_EXCEPTION = -2;
    private static final int ERROR_CODE_MALFORMED_GEOJSON = -3;
    private static final int ERROR_CODE_MALFORMED_URL = -1;
    private static final int ERROR_CODE_UNKNOWN_TYPE = -4;
    private static final String ERROR_IO_EXCEPTION = "Unable to download content from URL";
    private static final String ERROR_MALFORMED_GEOJSON = "Malformed GeoJSON response. Expected FeatureCollection as root element.";
    private static final String ERROR_MALFORMED_URL = "The URL is malformed";
    private static final String ERROR_UNKNOWN_TYPE = "Unrecognized/invalid type in JSON object";
    private static final String GEOJSON_FEATURECOLLECTION = "FeatureCollection";
    private static final String GEOJSON_FEATURES = "features";
    private static final String GEOJSON_GEOMETRYCOLLECTION = "GeometryCollection";
    private static final String GEOJSON_TYPE = "type";
    private static final String TAG = MapFeatureContainerBase.class.getSimpleName();
    private final MapFactory.MapFeatureVisitor<Void> featureAdder;
    protected List<MapFactory.MapFeature> features;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected MapFeatureContainerBase(ComponentContainer componentContainer) {
        super(componentContainer);
        List<MapFactory.MapFeature> list;
        MapFactory.MapFeatureVisitor<Void> mapFeatureVisitor;
        new CopyOnWriteArrayList();
        this.features = list;
        new MapFactory.MapFeatureVisitor<Void>(this) {
            private /* synthetic */ MapFeatureContainerBase hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final /* synthetic */ Object visit(MapFactory.MapCircle mapCircle, Object[] objArr) {
                Object[] objArr2 = objArr;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addFeature(mapCircle);
                return null;
            }

            public final /* synthetic */ Object visit(MapFactory.MapLineString mapLineString, Object[] objArr) {
                Object[] objArr2 = objArr;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addFeature(mapLineString);
                return null;
            }

            public final /* synthetic */ Object visit(MapFactory.MapMarker mapMarker, Object[] objArr) {
                Object[] objArr2 = objArr;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addFeature(mapMarker);
                return null;
            }

            public final /* synthetic */ Object visit(MapFactory.MapPolygon mapPolygon, Object[] objArr) {
                Object[] objArr2 = objArr;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addFeature(mapPolygon);
                return null;
            }

            public final /* synthetic */ Object visit(MapFactory.MapRectangle mapRectangle, Object[] objArr) {
                Object[] objArr2 = objArr;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addFeature(mapRectangle);
                return null;
            }
        };
        this.featureAdder = mapFeatureVisitor;
    }

    @SimpleProperty
    public void Features(YailList yailList) {
        YailList yailList2 = yailList;
        for (MapFactory.MapFeature removeFromMap : this.features) {
            removeFromMap.removeFromMap();
        }
        this.features.clear();
        ListIterator listIterator = yailList2.listIterator(1);
        while (listIterator.hasNext()) {
            Object next = listIterator.next();
            Object obj = next;
            if (next instanceof MapFactory.MapFeature) {
                addFeature((MapFactory.MapFeature) obj);
            }
        }
        getMap().getView().invalidate();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The list of features placed on this map. This list also includes any features created by calls to FeatureFromDescription")
    public YailList Features() {
        return YailList.makeList((List) this.features);
    }

    @SimpleEvent(description = "The user clicked on a map feature.")
    public void FeatureClick(MapFactory.MapFeature mapFeature) {
        MapFactory.MapFeature mapFeature2 = mapFeature;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "FeatureClick", mapFeature2);
        if (getMap() != this) {
            getMap().FeatureClick(mapFeature2);
        }
    }

    @SimpleEvent(description = "The user long-pressed on a map feature.")
    public void FeatureLongClick(MapFactory.MapFeature mapFeature) {
        MapFactory.MapFeature mapFeature2 = mapFeature;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "FeatureLongClick", mapFeature2);
        if (getMap() != this) {
            getMap().FeatureLongClick(mapFeature2);
        }
    }

    @SimpleEvent(description = "The user started dragging a map feature.")
    public void FeatureStartDrag(MapFactory.MapFeature mapFeature) {
        MapFactory.MapFeature mapFeature2 = mapFeature;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "FeatureStartDrag", mapFeature2);
        if (getMap() != this) {
            getMap().FeatureStartDrag(mapFeature2);
        }
    }

    @SimpleEvent(description = "The user dragged a map feature.")
    public void FeatureDrag(MapFactory.MapFeature mapFeature) {
        MapFactory.MapFeature mapFeature2 = mapFeature;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "FeatureDrag", mapFeature2);
        if (getMap() != this) {
            getMap().FeatureDrag(mapFeature2);
        }
    }

    @SimpleEvent(description = "The user stopped dragging a map feature.")
    public void FeatureStopDrag(MapFactory.MapFeature mapFeature) {
        MapFactory.MapFeature mapFeature2 = mapFeature;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "FeatureStopDrag", mapFeature2);
        if (getMap() != this) {
            getMap().FeatureStopDrag(mapFeature2);
        }
    }

    @SimpleFunction(description = "<p>Load a feature collection in <a href=\"https://en.wikipedia.org/wiki/GeoJSON\">GeoJSON</a> format from the given url. On success, the event GotFeatures will be raised with the given url and a list of the features parsed from the GeoJSON as a list of (key, value) pairs. On failure, the LoadError event will be raised with any applicable HTTP response code and error message.</p>")
    public void LoadFromURL(String str) {
        Runnable runnable;
        final String str2 = str;
        new Runnable(this) {
            private /* synthetic */ MapFeatureContainerBase hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.performGet(str2);
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    @SimpleFunction
    public Object FeatureFromDescription(YailList yailList) {
        StringBuilder sb;
        StringBuilder sb2;
        try {
            return GeoJSONUtil.processGeoJSONFeature(TAG, this, yailList);
        } catch (IllegalArgumentException e) {
            IllegalArgumentException illegalArgumentException = e;
            new StringBuilder();
            $form().dispatchErrorOccurredEvent(this, "FeatureFromDescription", -3, sb.append(illegalArgumentException.getMessage()).toString());
            new StringBuilder();
            return sb2.append(illegalArgumentException.getMessage()).toString();
        }
    }

    @SimpleEvent(description = "A GeoJSON document was successfully read from url. The features specified in the document are provided as a list in features.")
    public void GotFeatures(String str, YailList yailList) {
        YailList yailList2 = yailList;
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = yailList2;
        if (!EventDispatcher.dispatchEvent(this, "GotFeatures", objArr2)) {
            Iterator it = yailList2.iterator();
            Iterator it2 = it;
            Object next = it.next();
            while (it2.hasNext()) {
                Object FeatureFromDescription = FeatureFromDescription((YailList) it2.next());
            }
        }
    }

    @SimpleEvent(description = "An error was encountered while processing a GeoJSON document at the given url. The responseCode parameter will contain an HTTP status code and the errorMessage parameter will contain a detailed error message.")
    public void LoadError(String str, int i, String str2) {
        String str3 = str;
        Object[] objArr = new Object[3];
        objArr[0] = str3;
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(i);
        Object[] objArr3 = objArr2;
        objArr3[2] = str2;
        if (EventDispatcher.dispatchEvent(this, "LoadError", objArr3)) {
            return;
        }
        if (str3.startsWith("file:")) {
            $form().dispatchErrorOccurredEvent(this, "LoadFromURL", ErrorMessages.ERROR_CANNOT_READ_FILE, str3);
            return;
        }
        $form().dispatchErrorOccurredEvent(this, "LoadFromURL", ErrorMessages.ERROR_WEB_UNABLE_TO_GET, str3);
    }

    public Activity $context() {
        return this.container.$context();
    }

    public Form $form() {
        return this.container.$form();
    }

    public void $add(AndroidViewComponent androidViewComponent) {
        Throwable th;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        Throwable th2 = th;
        new UnsupportedOperationException("Map.$add() called");
        throw th2;
    }

    public void setChildWidth(AndroidViewComponent androidViewComponent, int i) {
        Throwable th;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException("Map.setChildWidth called");
        throw th2;
    }

    public void setChildHeight(AndroidViewComponent androidViewComponent, int i) {
        Throwable th;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException("Map.setChildHeight called");
        throw th2;
    }

    public void removeFeature(MapFactory.MapFeature mapFeature) {
        MapFactory.MapFeature mapFeature2 = mapFeature;
        boolean remove = this.features.remove(mapFeature2);
        getMap().removeFeature(mapFeature2);
    }

    public Iterator<MapFactory.MapFeature> iterator() {
        return this.features.iterator();
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapMarker mapMarker) {
        MapFactory.MapMarker mapMarker2 = mapMarker;
        boolean add = this.features.add(mapMarker2);
        getMap().addFeature(mapMarker2);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapLineString mapLineString) {
        MapFactory.MapLineString mapLineString2 = mapLineString;
        boolean add = this.features.add(mapLineString2);
        getMap().addFeature(mapLineString2);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapPolygon mapPolygon) {
        MapFactory.MapPolygon mapPolygon2 = mapPolygon;
        boolean add = this.features.add(mapPolygon2);
        getMap().addFeature(mapPolygon2);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapCircle mapCircle) {
        MapFactory.MapCircle mapCircle2 = mapCircle;
        boolean add = this.features.add(mapCircle2);
        getMap().addFeature(mapCircle2);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapRectangle mapRectangle) {
        MapFactory.MapRectangle mapRectangle2 = mapRectangle;
        boolean add = this.features.add(mapRectangle2);
        getMap().addFeature(mapRectangle2);
    }

    public void addFeature(MapFactory.MapFeature mapFeature) {
        Object accept = mapFeature.accept(this.featureAdder, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void performGet(String str) {
        String str2 = str;
        try {
            String loadUrl = loadUrl(str2);
            String str3 = loadUrl;
            if (loadUrl != null) {
                processGeoJSON(str2, str3);
            }
        } catch (Exception e) {
            Exception exc = e;
            int e2 = Log.e(TAG, "Exception retreiving GeoJSON", exc);
            $form().dispatchErrorOccurredEvent(this, "LoadFromURL", -4, exc.toString());
        }
    }

    private String loadUrl(String str) {
        Runnable runnable;
        Runnable runnable2;
        URL url;
        BufferedReader bufferedReader;
        Reader reader;
        StringBuilder sb;
        Runnable runnable3;
        String str2 = str;
        try {
            new URL(str2);
            URLConnection openConnection = url.openConnection();
            URLConnection uRLConnection = openConnection;
            openConnection.connect();
            if (uRLConnection instanceof HttpURLConnection) {
                HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
                HttpURLConnection httpURLConnection2 = httpURLConnection;
                int responseCode = httpURLConnection.getResponseCode();
                String responseMessage = httpURLConnection2.getResponseMessage();
                if (responseCode != 200) {
                    final String str3 = str2;
                    final int i = responseCode;
                    final String str4 = responseMessage;
                    new Runnable(this) {
                        private /* synthetic */ MapFeatureContainerBase hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
                        }

                        public final void run() {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LoadError(str3, i, str4);
                        }
                    };
                    $form().runOnUiThread(runnable3);
                    httpURLConnection2.disconnect();
                    return null;
                }
            }
            new InputStreamReader(uRLConnection.getInputStream(), "UTF-8");
            new BufferedReader(reader);
            BufferedReader bufferedReader2 = bufferedReader;
            new StringBuilder();
            StringBuilder sb2 = sb;
            while (true) {
                String readLine = bufferedReader2.readLine();
                String str5 = readLine;
                if (readLine != null) {
                    StringBuilder append = sb2.append(str5);
                    StringBuilder append2 = sb2.append("\n");
                } else {
                    bufferedReader2.close();
                    return sb2.toString();
                }
            }
        } catch (MalformedURLException e) {
            final String str6 = str2;
            new Runnable(this) {
                private /* synthetic */ MapFeatureContainerBase hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void run() {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LoadError(str6, -1, MapFeatureContainerBase.ERROR_MALFORMED_URL);
                }
            };
            $form().runOnUiThread(runnable2);
            return null;
        } catch (IOException e2) {
            final String str7 = str2;
            new Runnable(this) {
                private /* synthetic */ MapFeatureContainerBase hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void run() {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LoadError(str7, -2, MapFeatureContainerBase.ERROR_IO_EXCEPTION);
                }
            };
            $form().runOnUiThread(runnable);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void processGeoJSON(String str, String str2) throws JSONException {
        JSONObject jSONObject;
        List list;
        Runnable runnable;
        Runnable runnable2;
        String str3 = str;
        new JSONObject(stripBOM(str2));
        JSONObject jSONObject2 = jSONObject;
        JSONObject jSONObject3 = jSONObject2;
        String optString = jSONObject2.optString(GEOJSON_TYPE);
        if (GEOJSON_FEATURECOLLECTION.equals(optString) || GEOJSON_GEOMETRYCOLLECTION.equals(optString)) {
            JSONArray jSONArray = jSONObject3.getJSONArray(GEOJSON_FEATURES);
            new ArrayList();
            List list2 = list;
            for (int i = 0; i < jSONArray.length(); i++) {
                boolean add = list2.add(jsonObjectToYail(jSONArray.getJSONObject(i)));
            }
            final String str4 = str3;
            final List list3 = list2;
            new Runnable(this) {
                private /* synthetic */ MapFeatureContainerBase hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                }

                public final void run() {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotFeatures(str4, YailList.makeList(list3));
                }
            };
            $form().runOnUiThread(runnable);
            return;
        }
        final String str5 = str3;
        new Runnable(this) {
            private /* synthetic */ MapFeatureContainerBase hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LoadError(str5, -3, MapFeatureContainerBase.ERROR_MALFORMED_GEOJSON);
            }
        };
        $form().runOnUiThread(runnable2);
    }

    private YailList jsonObjectToYail(JSONObject jSONObject) throws JSONException {
        List list;
        StringBuilder sb;
        Throwable th;
        JSONObject jSONObject2 = jSONObject;
        new ArrayList();
        List list2 = list;
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject2.get(next);
            Object obj2 = obj;
            if ((obj instanceof Boolean) || (obj2 instanceof Integer) || (obj2 instanceof Long) || (obj2 instanceof Double) || (obj2 instanceof String)) {
                Object[] objArr = new Object[2];
                objArr[0] = next;
                Object[] objArr2 = objArr;
                objArr2[1] = obj2;
                boolean add = list2.add(YailList.makeList(objArr2));
            } else if (obj2 instanceof JSONArray) {
                Object[] objArr3 = new Object[2];
                objArr3[0] = next;
                Object[] objArr4 = objArr3;
                objArr4[1] = jsonArrayToYail((JSONArray) obj2);
                boolean add2 = list2.add(YailList.makeList(objArr4));
            } else if (obj2 instanceof JSONObject) {
                Object[] objArr5 = new Object[2];
                objArr5[0] = next;
                Object[] objArr6 = objArr5;
                objArr6[1] = jsonObjectToYail((JSONObject) obj2);
                boolean add3 = list2.add(YailList.makeList(objArr6));
            } else if (!JSONObject.NULL.equals(obj2)) {
                String str = TAG;
                new StringBuilder("Unrecognized/invalid type in JSON object: ");
                int wtf = Log.wtf(str, sb.append(obj2.getClass()).toString());
                Throwable th2 = th;
                new IllegalArgumentException(ERROR_UNKNOWN_TYPE);
                throw th2;
            }
        }
        return YailList.makeList(list2);
    }

    private YailList jsonArrayToYail(JSONArray jSONArray) throws JSONException {
        List list;
        StringBuilder sb;
        Throwable th;
        JSONArray jSONArray2 = jSONArray;
        new ArrayList();
        List list2 = list;
        for (int i = 0; i < jSONArray2.length(); i++) {
            Object obj = jSONArray2.get(i);
            Object obj2 = obj;
            if ((obj instanceof Boolean) || (obj2 instanceof Integer) || (obj2 instanceof Long) || (obj2 instanceof Double) || (obj2 instanceof String)) {
                boolean add = list2.add(obj2);
            } else if (obj2 instanceof JSONArray) {
                boolean add2 = list2.add(jsonArrayToYail((JSONArray) obj2));
            } else if (obj2 instanceof JSONObject) {
                boolean add3 = list2.add(jsonObjectToYail((JSONObject) obj2));
            } else if (!JSONObject.NULL.equals(obj2)) {
                String str = TAG;
                new StringBuilder("Unrecognized/invalid type in JSON object: ");
                int wtf = Log.wtf(str, sb.append(obj2.getClass()).toString());
                Throwable th2 = th;
                new IllegalArgumentException(ERROR_UNKNOWN_TYPE);
                throw th2;
            }
        }
        return YailList.makeList(list2);
    }

    private static String stripBOM(String str) {
        String str2 = str;
        if (str2.charAt(0) == 65279) {
            return str2.substring(1);
        }
        return str2;
    }
}
