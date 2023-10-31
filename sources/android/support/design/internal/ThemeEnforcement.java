package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.annotation.StyleableRes;
import android.support.design.C0064R;
import android.support.p003v7.widget.TintTypedArray;
import android.util.AttributeSet;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ThemeEnforcement {
    private static final int[] APPCOMPAT_CHECK_ATTRS = {C0064R.attr.colorPrimary};
    private static final String APPCOMPAT_THEME_NAME = "Theme.AppCompat";
    private static final int[] MATERIAL_CHECK_ATTRS = {C0064R.attr.colorSecondary};
    private static final String MATERIAL_THEME_NAME = "Theme.MaterialComponents";

    private ThemeEnforcement() {
    }

    public static TypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, @StyleableRes int[] iArr, @AttrRes int i, @StyleRes int i2, @StyleableRes int... textAppearanceResIndices) {
        Context context2 = context;
        AttributeSet set = attributeSet;
        int[] attrs = iArr;
        int defStyleAttr = i;
        int defStyleRes = i2;
        checkCompatibleTheme(context2, set, defStyleAttr, defStyleRes);
        checkTextAppearance(context2, set, attrs, defStyleAttr, defStyleRes, textAppearanceResIndices);
        return context2.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes);
    }

    public static TintTypedArray obtainTintedStyledAttributes(Context context, AttributeSet attributeSet, @StyleableRes int[] iArr, @AttrRes int i, @StyleRes int i2, @StyleableRes int... textAppearanceResIndices) {
        Context context2 = context;
        AttributeSet set = attributeSet;
        int[] attrs = iArr;
        int defStyleAttr = i;
        int defStyleRes = i2;
        checkCompatibleTheme(context2, set, defStyleAttr, defStyleRes);
        checkTextAppearance(context2, set, attrs, defStyleAttr, defStyleRes, textAppearanceResIndices);
        return TintTypedArray.obtainStyledAttributes(context2, set, attrs, defStyleAttr, defStyleRes);
    }

    private static void checkCompatibleTheme(Context context, AttributeSet set, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        Context context2 = context;
        TypedArray a = context2.obtainStyledAttributes(set, C0064R.styleable.ThemeEnforcement, defStyleAttr, defStyleRes);
        boolean enforceMaterialTheme = a.getBoolean(C0064R.styleable.ThemeEnforcement_enforceMaterialTheme, false);
        a.recycle();
        if (enforceMaterialTheme) {
            checkMaterialTheme(context2);
        }
        checkAppCompatTheme(context2);
    }

    private static void checkTextAppearance(Context context, AttributeSet attributeSet, @StyleableRes int[] iArr, @AttrRes int i, @StyleRes int i2, @StyleableRes int... iArr2) {
        boolean validTextAppearance;
        Throwable th;
        Context context2 = context;
        AttributeSet set = attributeSet;
        int[] attrs = iArr;
        int defStyleAttr = i;
        int defStyleRes = i2;
        int[] textAppearanceResIndices = iArr2;
        TypedArray themeEnforcementAttrs = context2.obtainStyledAttributes(set, C0064R.styleable.ThemeEnforcement, defStyleAttr, defStyleRes);
        if (!themeEnforcementAttrs.getBoolean(C0064R.styleable.ThemeEnforcement_enforceTextAppearance, false)) {
            themeEnforcementAttrs.recycle();
            return;
        }
        if (textAppearanceResIndices == null || textAppearanceResIndices.length == 0) {
            validTextAppearance = themeEnforcementAttrs.getResourceId(C0064R.styleable.ThemeEnforcement_android_textAppearance, -1) != -1;
        } else {
            validTextAppearance = isCustomTextAppearanceValid(context2, set, attrs, defStyleAttr, defStyleRes, textAppearanceResIndices);
        }
        themeEnforcementAttrs.recycle();
        if (!validTextAppearance) {
            Throwable th2 = th;
            new IllegalArgumentException("This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant).");
            throw th2;
        }
    }

    private static boolean isCustomTextAppearanceValid(Context context, AttributeSet set, @StyleableRes int[] attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes, @StyleableRes int... textAppearanceResIndices) {
        TypedArray componentAttrs = context.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes);
        int[] iArr = textAppearanceResIndices;
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            if (componentAttrs.getResourceId(iArr[i], -1) == -1) {
                componentAttrs.recycle();
                return false;
            }
        }
        componentAttrs.recycle();
        return true;
    }

    public static void checkAppCompatTheme(Context context) {
        checkTheme(context, APPCOMPAT_CHECK_ATTRS, APPCOMPAT_THEME_NAME);
    }

    public static void checkMaterialTheme(Context context) {
        checkTheme(context, MATERIAL_CHECK_ATTRS, MATERIAL_THEME_NAME);
    }

    public static boolean isAppCompatTheme(Context context) {
        return isTheme(context, APPCOMPAT_CHECK_ATTRS);
    }

    public static boolean isMaterialTheme(Context context) {
        return isTheme(context, MATERIAL_CHECK_ATTRS);
    }

    private static boolean isTheme(Context context, int[] themeAttributes) {
        TypedArray a = context.obtainStyledAttributes(themeAttributes);
        boolean success = a.hasValue(0);
        a.recycle();
        return success;
    }

    private static void checkTheme(Context context, int[] themeAttributes, String str) {
        Throwable th;
        StringBuilder sb;
        String themeName = str;
        if (!isTheme(context, themeAttributes)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("The style on this component requires your app theme to be ").append(themeName).append(" (or a descendant).").toString());
            throw th2;
        }
    }
}
