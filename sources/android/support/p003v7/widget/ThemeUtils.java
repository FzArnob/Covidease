package android.support.p003v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.p000v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

/* renamed from: android.support.v7.widget.ThemeUtils */
class ThemeUtils {
    static final int[] ACTIVATED_STATE_SET = {16843518};
    static final int[] CHECKED_STATE_SET = {16842912};
    static final int[] DISABLED_STATE_SET = {-16842910};
    static final int[] EMPTY_STATE_SET = new int[0];
    static final int[] FOCUSED_STATE_SET = {16842908};
    static final int[] NOT_PRESSED_OR_FOCUSED_STATE_SET = {-16842919, -16842908};
    static final int[] PRESSED_STATE_SET = {16842919};
    static final int[] SELECTED_STATE_SET = {16842913};
    private static final int[] TEMP_ARRAY = new int[1];
    private static final ThreadLocal<TypedValue> TL_TYPED_VALUE;

    static {
        ThreadLocal<TypedValue> threadLocal;
        new ThreadLocal<>();
        TL_TYPED_VALUE = threadLocal;
    }

    public static ColorStateList createDisabledStateList(int textColor, int disabledTextColor) {
        ColorStateList colorStateList;
        int[][] states = new int[2][];
        int[] colors = new int[2];
        states[0] = DISABLED_STATE_SET;
        colors[0] = disabledTextColor;
        int i = 0 + 1;
        states[i] = EMPTY_STATE_SET;
        colors[i] = textColor;
        int i2 = i + 1;
        new ColorStateList(states, colors);
        return colorStateList;
    }

    /* JADX INFO: finally extract failed */
    public static int getThemeAttrColor(Context context, int attr) {
        TEMP_ARRAY[0] = attr;
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, (AttributeSet) null, TEMP_ARRAY);
        try {
            int color = a.getColor(0, 0);
            a.recycle();
            return color;
        } catch (Throwable th) {
            Throwable th2 = th;
            a.recycle();
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    public static ColorStateList getThemeAttrColorStateList(Context context, int attr) {
        TEMP_ARRAY[0] = attr;
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, (AttributeSet) null, TEMP_ARRAY);
        try {
            ColorStateList colorStateList = a.getColorStateList(0);
            a.recycle();
            return colorStateList;
        } catch (Throwable th) {
            Throwable th2 = th;
            a.recycle();
            throw th2;
        }
    }

    public static int getDisabledThemeAttrColor(Context context, int i) {
        Context context2 = context;
        int attr = i;
        ColorStateList csl = getThemeAttrColorStateList(context2, attr);
        if (csl != null && csl.isStateful()) {
            return csl.getColorForState(DISABLED_STATE_SET, csl.getDefaultColor());
        }
        TypedValue tv = getTypedValue();
        boolean resolveAttribute = context2.getTheme().resolveAttribute(16842803, tv, true);
        return getThemeAttrColor(context2, attr, tv.getFloat());
    }

    private static TypedValue getTypedValue() {
        TypedValue typedValue;
        TypedValue typedValue2 = TL_TYPED_VALUE.get();
        if (typedValue2 == null) {
            new TypedValue();
            typedValue2 = typedValue;
            TL_TYPED_VALUE.set(typedValue2);
        }
        return typedValue2;
    }

    static int getThemeAttrColor(Context context, int attr, float alpha) {
        int color = getThemeAttrColor(context, attr);
        return ColorUtils.setAlphaComponent(color, Math.round(((float) Color.alpha(color)) * alpha));
    }

    private ThemeUtils() {
    }
}
