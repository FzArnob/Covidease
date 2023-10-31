package com.google.appinventor.components.runtime;

import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import org.json.JSONException;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MAPS, description = "A FeatureColletion contains one or more map features as a group. Any events fired on a feature in the collection will also trigger the corresponding event on the collection object. FeatureCollections can be loaded from external resources as a means of populating a Map with content.", version = 2)
public class FeatureCollection extends MapFeatureContainerBase implements MapFactory.MapFeatureCollection {
    private Map map;
    private String opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR = "";

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FeatureCollection(com.google.appinventor.components.runtime.util.MapFactory.MapFeatureContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Map r3 = r3.getMap()
            r2.map = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.FeatureCollection.<init>(com.google.appinventor.components.runtime.util.MapFactory$MapFeatureContainer):void");
    }

    @DesignerProperty
    @SimpleProperty(description = "Loads a collection of features from the given string. If the string is not valid GeoJSON, the ErrorLoadingFeatureCollection error will be run with url = <string>.")
    public void FeaturesFromGeoJSON(String str) {
        StringBuilder sb;
        try {
            processGeoJSON("<string>", str);
        } catch (JSONException e) {
            new StringBuilder();
            $form().dispatchErrorOccurredEvent(this, "FeaturesFromGeoJSON", ErrorMessages.ERROR_INVALID_GEOJSON, sb.append(e.getMessage()).toString());
        }
    }

    @SimpleEvent(description = "A GeoJSON document was successfully read from url. The features specified in the document are provided as a list in features.")
    public void GotFeatures(String str, YailList yailList) {
        String str2 = str;
        this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR = str2;
        super.GotFeatures(str2, yailList);
    }

    @DesignerProperty(editorType = "geojson_type")
    public void Source(String str) {
        String str2 = str;
        this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets or sets the source URL used to populate the feature collection. If the feature collection was not loaded from a URL, this will be the empty string.")
    public String Source() {
        return this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean Visible() {
        return getMap().getController().isFeatureCollectionVisible(this);
    }

    @DesignerProperty(defaultValue = "True", editorType = "visibility")
    @SimpleProperty(description = "Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.")
    public void Visible(boolean z) {
        getMap().getController().setFeatureCollectionVisible(this, z);
    }

    public View getView() {
        return null;
    }

    public Map getMap() {
        return this.map;
    }
}
