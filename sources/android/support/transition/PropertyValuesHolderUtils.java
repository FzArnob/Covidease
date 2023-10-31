package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.Property;

class PropertyValuesHolderUtils {
    static PropertyValuesHolder ofPointF(Property<?, PointF> property, Path path) {
        Property property2;
        Property<?, PointF> property3 = property;
        Path path2 = path;
        if (Build.VERSION.SDK_INT >= 21) {
            return PropertyValuesHolder.ofObject(property3, (TypeConverter) null, path2);
        }
        new PathProperty(property3, path2);
        return PropertyValuesHolder.ofFloat(property2, new float[]{0.0f, 1.0f});
    }

    private PropertyValuesHolderUtils() {
    }
}
