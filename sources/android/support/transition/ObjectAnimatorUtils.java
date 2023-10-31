package android.support.transition;

import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.Property;

class ObjectAnimatorUtils {
    static <T> ObjectAnimator ofPointF(T t, Property<T, PointF> property, Path path) {
        Property property2;
        T target = t;
        Property<T, PointF> property3 = property;
        Path path2 = path;
        if (Build.VERSION.SDK_INT >= 21) {
            return ObjectAnimator.ofObject(target, property3, (TypeConverter) null, path2);
        }
        new PathProperty(property3, path2);
        return ObjectAnimator.ofFloat(target, property2, new float[]{0.0f, 1.0f});
    }

    private ObjectAnimatorUtils() {
    }
}
