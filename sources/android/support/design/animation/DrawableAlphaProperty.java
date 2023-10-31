package android.support.design.animation;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Property;
import java.util.WeakHashMap;

public class DrawableAlphaProperty extends Property<Drawable, Integer> {
    public static final Property<Drawable, Integer> DRAWABLE_ALPHA_COMPAT;
    private final WeakHashMap<Drawable, Integer> alphaCache;

    static {
        Property<Drawable, Integer> property;
        new DrawableAlphaProperty();
        DRAWABLE_ALPHA_COMPAT = property;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private DrawableAlphaProperty() {
        super(Integer.class, "drawableAlphaCompat");
        WeakHashMap<Drawable, Integer> weakHashMap;
        new WeakHashMap<>();
        this.alphaCache = weakHashMap;
    }

    public Integer get(Drawable drawable) {
        Drawable object = drawable;
        if (Build.VERSION.SDK_INT >= 19) {
            return Integer.valueOf(object.getAlpha());
        }
        if (this.alphaCache.containsKey(object)) {
            return this.alphaCache.get(object);
        }
        return 255;
    }

    public void set(Drawable drawable, Integer num) {
        Drawable object = drawable;
        Integer value = num;
        if (Build.VERSION.SDK_INT < 19) {
            Integer put = this.alphaCache.put(object, value);
        }
        object.setAlpha(value.intValue());
    }
}
