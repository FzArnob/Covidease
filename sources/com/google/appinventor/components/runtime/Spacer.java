package com.google.appinventor.components.runtime;

import android.view.View;
import android.widget.Space;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DEPRECATED, description = "", version = 1)
public class Spacer extends AndroidViewComponent implements Component {
    private final Space B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Spacer(com.google.appinventor.components.runtime.ComponentContainer r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            android.widget.Space r3 = new android.widget.Space
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r4.<init>(r5)
            r2.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r3
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r0
            r3 = -3355444(0xffffffffffcccccc, float:NaN)
            r2.BackgroundColor(r3)
            r2 = r0
            r3 = 10
            r2.Height(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Spacer.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color")
    @SimpleProperty(userVisible = false)
    public void BackgroundColor(int i) {
        getView().setBackgroundColor(i);
    }

    public View getView() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }
}
