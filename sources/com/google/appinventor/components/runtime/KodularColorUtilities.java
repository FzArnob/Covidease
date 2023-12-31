package com.google.appinventor.components.runtime;

import android.graphics.Color;
import android.support.p000v4.graphics.ColorUtils;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "write in ode", iconName = "images/colorUtilities.png", nonVisible = true, version = 1)
public class KodularColorUtilities extends AndroidNonvisibleComponent implements Component {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KodularColorUtilities(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        int d = Log.d("Kodular Color Utilities", "Kodular Color Utilities Created");
    }

    @SimpleFunction(description = "Returns true if the color is dark, else it returns false, means the color is light. The result is returned as boolean.")
    public boolean IsDarkColor(int i) {
        return ColorUtils.calculateLuminance(i) < 0.5d;
    }

    @SimpleFunction(description = "Returns the luminance of a color as a float between 0.0 and 1.0. The result is returned as double.")
    public double GetLuminance(int i) {
        return ColorUtils.calculateLuminance(i);
    }

    @SimpleFunction(description = "Set a alpha value to a color. The result is returned as integer.")
    public int SetAlphaValue(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        if (i4 < 0 || i4 > 255) {
            return i3;
        }
        return ColorUtils.setAlphaComponent(i3, i4);
    }

    @SimpleFunction(description = "Convert a integer color to a hex color. The result is returned as string.")
    public String ConvertIntToHex(int i) {
        return Integer.toHexString(i).toUpperCase();
    }

    @SimpleFunction(description = "Convert a hex color to a integer color. The result is returned as integer.")
    public int ConvertHexToInt(String str) {
        return Color.parseColor(str);
    }
}
