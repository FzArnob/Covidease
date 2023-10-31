package android.support.p000v4.content.res;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AnyRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.content.res.TypedArrayUtils */
public class TypedArrayUtils {
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";

    public static boolean hasAttribute(@NonNull XmlPullParser parser, @NonNull String attrName) {
        return parser.getAttributeValue(NAMESPACE, attrName) != null;
    }

    public static float getNamedFloat(@NonNull TypedArray typedArray, @NonNull XmlPullParser parser, @NonNull String attrName, @StyleableRes int i, float f) {
        TypedArray a = typedArray;
        int resId = i;
        float defaultValue = f;
        if (!hasAttribute(parser, attrName)) {
            return defaultValue;
        }
        return a.getFloat(resId, defaultValue);
    }

    public static boolean getNamedBoolean(@NonNull TypedArray typedArray, @NonNull XmlPullParser parser, @NonNull String attrName, @StyleableRes int i, boolean z) {
        TypedArray a = typedArray;
        int resId = i;
        boolean defaultValue = z;
        if (!hasAttribute(parser, attrName)) {
            return defaultValue;
        }
        return a.getBoolean(resId, defaultValue);
    }

    public static int getNamedInt(@NonNull TypedArray typedArray, @NonNull XmlPullParser parser, @NonNull String attrName, @StyleableRes int i, int i2) {
        TypedArray a = typedArray;
        int resId = i;
        int defaultValue = i2;
        if (!hasAttribute(parser, attrName)) {
            return defaultValue;
        }
        return a.getInt(resId, defaultValue);
    }

    @ColorInt
    public static int getNamedColor(@NonNull TypedArray typedArray, @NonNull XmlPullParser parser, @NonNull String attrName, @StyleableRes int i, @ColorInt int i2) {
        TypedArray a = typedArray;
        int resId = i;
        int defaultValue = i2;
        if (!hasAttribute(parser, attrName)) {
            return defaultValue;
        }
        return a.getColor(resId, defaultValue);
    }

    public static ComplexColorCompat getNamedComplexColor(@NonNull TypedArray typedArray, @NonNull XmlPullParser parser, @Nullable Resources.Theme theme, @NonNull String attrName, @StyleableRes int i, @ColorInt int i2) {
        TypedValue typedValue;
        TypedArray a = typedArray;
        Resources.Theme theme2 = theme;
        int resId = i;
        int defaultValue = i2;
        if (hasAttribute(parser, attrName)) {
            new TypedValue();
            TypedValue value = typedValue;
            boolean value2 = a.getValue(resId, value);
            if (value.type >= 28 && value.type <= 31) {
                return ComplexColorCompat.from(value.data);
            }
            ComplexColorCompat complexColor = ComplexColorCompat.inflate(a.getResources(), a.getResourceId(resId, 0), theme2);
            if (complexColor != null) {
                return complexColor;
            }
        }
        return ComplexColorCompat.from(defaultValue);
    }

    @AnyRes
    public static int getNamedResourceId(@NonNull TypedArray typedArray, @NonNull XmlPullParser parser, @NonNull String attrName, @StyleableRes int i, @AnyRes int i2) {
        TypedArray a = typedArray;
        int resId = i;
        int defaultValue = i2;
        if (!hasAttribute(parser, attrName)) {
            return defaultValue;
        }
        return a.getResourceId(resId, defaultValue);
    }

    @Nullable
    public static String getNamedString(@NonNull TypedArray typedArray, @NonNull XmlPullParser parser, @NonNull String attrName, @StyleableRes int i) {
        TypedArray a = typedArray;
        int resId = i;
        if (!hasAttribute(parser, attrName)) {
            return null;
        }
        return a.getString(resId);
    }

    @Nullable
    public static TypedValue peekNamedValue(@NonNull TypedArray typedArray, @NonNull XmlPullParser parser, @NonNull String attrName, int i) {
        TypedArray a = typedArray;
        int resId = i;
        if (!hasAttribute(parser, attrName)) {
            return null;
        }
        return a.peekValue(resId);
    }

    @NonNull
    public static TypedArray obtainAttributes(@NonNull Resources resources, @Nullable Resources.Theme theme, @NonNull AttributeSet attributeSet, @NonNull int[] iArr) {
        Resources res = resources;
        Resources.Theme theme2 = theme;
        AttributeSet set = attributeSet;
        int[] attrs = iArr;
        if (theme2 == null) {
            return res.obtainAttributes(set, attrs);
        }
        return theme2.obtainStyledAttributes(set, attrs, 0, 0);
    }

    public static boolean getBoolean(@NonNull TypedArray typedArray, @StyleableRes int index, @StyleableRes int fallbackIndex, boolean defaultValue) {
        TypedArray a = typedArray;
        return a.getBoolean(index, a.getBoolean(fallbackIndex, defaultValue));
    }

    @Nullable
    public static Drawable getDrawable(@NonNull TypedArray typedArray, @StyleableRes int index, @StyleableRes int i) {
        TypedArray a = typedArray;
        int fallbackIndex = i;
        Drawable val = a.getDrawable(index);
        if (val == null) {
            val = a.getDrawable(fallbackIndex);
        }
        return val;
    }

    public static int getInt(@NonNull TypedArray typedArray, @StyleableRes int index, @StyleableRes int fallbackIndex, int defaultValue) {
        TypedArray a = typedArray;
        return a.getInt(index, a.getInt(fallbackIndex, defaultValue));
    }

    @AnyRes
    public static int getResourceId(@NonNull TypedArray typedArray, @StyleableRes int index, @StyleableRes int fallbackIndex, @AnyRes int defaultValue) {
        TypedArray a = typedArray;
        return a.getResourceId(index, a.getResourceId(fallbackIndex, defaultValue));
    }

    @Nullable
    public static String getString(@NonNull TypedArray typedArray, @StyleableRes int index, @StyleableRes int i) {
        TypedArray a = typedArray;
        int fallbackIndex = i;
        String val = a.getString(index);
        if (val == null) {
            val = a.getString(fallbackIndex);
        }
        return val;
    }

    @Nullable
    public static CharSequence getText(@NonNull TypedArray typedArray, @StyleableRes int index, @StyleableRes int i) {
        TypedArray a = typedArray;
        int fallbackIndex = i;
        CharSequence val = a.getText(index);
        if (val == null) {
            val = a.getText(fallbackIndex);
        }
        return val;
    }

    @Nullable
    public static CharSequence[] getTextArray(@NonNull TypedArray typedArray, @StyleableRes int index, @StyleableRes int i) {
        TypedArray a = typedArray;
        int fallbackIndex = i;
        CharSequence[] val = a.getTextArray(index);
        if (val == null) {
            val = a.getTextArray(fallbackIndex);
        }
        return val;
    }

    public static int getAttr(@NonNull Context context, int i, int i2) {
        TypedValue typedValue;
        int attr = i;
        int fallbackAttr = i2;
        new TypedValue();
        TypedValue value = typedValue;
        boolean resolveAttribute = context.getTheme().resolveAttribute(attr, value, true);
        if (value.resourceId != 0) {
            return attr;
        }
        return fallbackAttr;
    }

    private TypedArrayUtils() {
    }
}
