package android.support.design.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleableRes;
import android.support.p003v7.content.res.AppCompatResources;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class MaterialResources {
    private MaterialResources() {
    }

    @Nullable
    public static ColorStateList getColorStateList(Context context, TypedArray typedArray, @StyleableRes int i) {
        int resourceId;
        ColorStateList value;
        Context context2 = context;
        TypedArray attributes = typedArray;
        int index = i;
        if (!attributes.hasValue(index) || (resourceId = attributes.getResourceId(index, 0)) == 0 || (value = AppCompatResources.getColorStateList(context2, resourceId)) == null) {
            return attributes.getColorStateList(index);
        }
        return value;
    }

    @Nullable
    public static Drawable getDrawable(Context context, TypedArray typedArray, @StyleableRes int i) {
        int resourceId;
        Drawable value;
        Context context2 = context;
        TypedArray attributes = typedArray;
        int index = i;
        if (!attributes.hasValue(index) || (resourceId = attributes.getResourceId(index, 0)) == 0 || (value = AppCompatResources.getDrawable(context2, resourceId)) == null) {
            return attributes.getDrawable(index);
        }
        return value;
    }

    @Nullable
    public static TextAppearance getTextAppearance(Context context, TypedArray typedArray, @StyleableRes int i) {
        int resourceId;
        TextAppearance textAppearance;
        Context context2 = context;
        TypedArray attributes = typedArray;
        int index = i;
        if (!attributes.hasValue(index) || (resourceId = attributes.getResourceId(index, 0)) == 0) {
            return null;
        }
        new TextAppearance(context2, resourceId);
        return textAppearance;
    }

    @StyleableRes
    static int getIndexWithValue(TypedArray attributes, @StyleableRes int i, @StyleableRes int i2) {
        int a = i;
        int b = i2;
        if (attributes.hasValue(a)) {
            return a;
        }
        return b;
    }
}
