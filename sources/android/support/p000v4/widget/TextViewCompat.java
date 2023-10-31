package android.support.p000v4.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.support.annotation.C0015Px;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.p000v4.text.PrecomputedTextCompat;
import android.support.p000v4.util.Preconditions;
import android.text.Editable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v4.widget.TextViewCompat */
public final class TextViewCompat {
    public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
    public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;
    private static final int LINES = 1;
    private static final String LOG_TAG = "TextViewCompat";
    private static Field sMaxModeField;
    private static boolean sMaxModeFieldFetched;
    private static Field sMaximumField;
    private static boolean sMaximumFieldFetched;
    private static Field sMinModeField;
    private static boolean sMinModeFieldFetched;
    private static Field sMinimumField;
    private static boolean sMinimumFieldFetched;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.widget.TextViewCompat$AutoSizeTextType */
    public @interface AutoSizeTextType {
    }

    private TextViewCompat() {
    }

    private static Field retrieveField(String str) {
        StringBuilder sb;
        String fieldName = str;
        Field field = null;
        try {
            field = TextView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            NoSuchFieldException noSuchFieldException = e;
            new StringBuilder();
            int e2 = Log.e(LOG_TAG, sb.append("Could not retrieve ").append(fieldName).append(" field.").toString());
        }
        return field;
    }

    private static int retrieveIntFromField(Field field, TextView textView) {
        StringBuilder sb;
        Field field2 = field;
        try {
            return field2.getInt(textView);
        } catch (IllegalAccessException e) {
            IllegalAccessException illegalAccessException = e;
            new StringBuilder();
            int d = Log.d(LOG_TAG, sb.append("Could not retrieve value of ").append(field2.getName()).append(" field.").toString());
            return -1;
        }
    }

    public static void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        TextView textView2 = textView;
        Drawable start = drawable;
        Drawable top = drawable2;
        Drawable end = drawable3;
        Drawable bottom = drawable4;
        if (Build.VERSION.SDK_INT >= 18) {
            textView2.setCompoundDrawablesRelative(start, top, end, bottom);
        } else if (Build.VERSION.SDK_INT >= 17) {
            boolean rtl = textView2.getLayoutDirection() == 1;
            textView2.setCompoundDrawables(rtl ? end : start, top, rtl ? start : end, bottom);
        } else {
            textView2.setCompoundDrawables(start, top, end, bottom);
        }
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        TextView textView2 = textView;
        Drawable start = drawable;
        Drawable top = drawable2;
        Drawable end = drawable3;
        Drawable bottom = drawable4;
        if (Build.VERSION.SDK_INT >= 18) {
            textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        } else if (Build.VERSION.SDK_INT >= 17) {
            boolean rtl = textView2.getLayoutDirection() == 1;
            textView2.setCompoundDrawablesWithIntrinsicBounds(rtl ? end : start, top, rtl ? start : end, bottom);
        } else {
            textView2.setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom);
        }
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
        TextView textView2 = textView;
        int start = i;
        int top = i2;
        int end = i3;
        int bottom = i4;
        if (Build.VERSION.SDK_INT >= 18) {
            textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        } else if (Build.VERSION.SDK_INT >= 17) {
            boolean rtl = textView2.getLayoutDirection() == 1;
            textView2.setCompoundDrawablesWithIntrinsicBounds(rtl ? end : start, top, rtl ? start : end, bottom);
        } else {
            textView2.setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom);
        }
    }

    public static int getMaxLines(@NonNull TextView textView) {
        TextView textView2 = textView;
        if (Build.VERSION.SDK_INT >= 16) {
            return textView2.getMaxLines();
        }
        if (!sMaxModeFieldFetched) {
            sMaxModeField = retrieveField("mMaxMode");
            sMaxModeFieldFetched = true;
        }
        if (sMaxModeField != null && retrieveIntFromField(sMaxModeField, textView2) == 1) {
            if (!sMaximumFieldFetched) {
                sMaximumField = retrieveField("mMaximum");
                sMaximumFieldFetched = true;
            }
            if (sMaximumField != null) {
                return retrieveIntFromField(sMaximumField, textView2);
            }
        }
        return -1;
    }

    public static int getMinLines(@NonNull TextView textView) {
        TextView textView2 = textView;
        if (Build.VERSION.SDK_INT >= 16) {
            return textView2.getMinLines();
        }
        if (!sMinModeFieldFetched) {
            sMinModeField = retrieveField("mMinMode");
            sMinModeFieldFetched = true;
        }
        if (sMinModeField != null && retrieveIntFromField(sMinModeField, textView2) == 1) {
            if (!sMinimumFieldFetched) {
                sMinimumField = retrieveField("mMinimum");
                sMinimumFieldFetched = true;
            }
            if (sMinimumField != null) {
                return retrieveIntFromField(sMinimumField, textView2);
            }
        }
        return -1;
    }

    public static void setTextAppearance(@NonNull TextView textView, @StyleRes int i) {
        TextView textView2 = textView;
        int resId = i;
        if (Build.VERSION.SDK_INT >= 23) {
            textView2.setTextAppearance(resId);
        } else {
            textView2.setTextAppearance(textView2.getContext(), resId);
        }
    }

    @NonNull
    public static Drawable[] getCompoundDrawablesRelative(@NonNull TextView textView) {
        TextView textView2 = textView;
        if (Build.VERSION.SDK_INT >= 18) {
            return textView2.getCompoundDrawablesRelative();
        }
        if (Build.VERSION.SDK_INT < 17) {
            return textView2.getCompoundDrawables();
        }
        boolean rtl = textView2.getLayoutDirection() == 1;
        Drawable[] compounds = textView2.getCompoundDrawables();
        if (rtl) {
            Drawable start = compounds[2];
            Drawable end = compounds[0];
            compounds[0] = start;
            compounds[2] = end;
        }
        return compounds;
    }

    public static void setAutoSizeTextTypeWithDefaults(@NonNull TextView textView, int i) {
        TextView textView2 = textView;
        int autoSizeTextType = i;
        if (Build.VERSION.SDK_INT >= 27) {
            textView2.setAutoSizeTextTypeWithDefaults(autoSizeTextType);
        } else if (textView2 instanceof AutoSizeableTextView) {
            ((AutoSizeableTextView) textView2).setAutoSizeTextTypeWithDefaults(autoSizeTextType);
        }
    }

    public static void setAutoSizeTextTypeUniformWithConfiguration(@NonNull TextView textView, int i, int i2, int i3, int i4) throws IllegalArgumentException {
        TextView textView2 = textView;
        int autoSizeMinTextSize = i;
        int autoSizeMaxTextSize = i2;
        int autoSizeStepGranularity = i3;
        int unit = i4;
        if (Build.VERSION.SDK_INT >= 27) {
            textView2.setAutoSizeTextTypeUniformWithConfiguration(autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);
        } else if (textView2 instanceof AutoSizeableTextView) {
            ((AutoSizeableTextView) textView2).setAutoSizeTextTypeUniformWithConfiguration(autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);
        }
    }

    public static void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull TextView textView, @NonNull int[] iArr, int i) throws IllegalArgumentException {
        TextView textView2 = textView;
        int[] presetSizes = iArr;
        int unit = i;
        if (Build.VERSION.SDK_INT >= 27) {
            textView2.setAutoSizeTextTypeUniformWithPresetSizes(presetSizes, unit);
        } else if (textView2 instanceof AutoSizeableTextView) {
            ((AutoSizeableTextView) textView2).setAutoSizeTextTypeUniformWithPresetSizes(presetSizes, unit);
        }
    }

    public static int getAutoSizeTextType(@NonNull TextView textView) {
        TextView textView2 = textView;
        if (Build.VERSION.SDK_INT >= 27) {
            return textView2.getAutoSizeTextType();
        }
        if (textView2 instanceof AutoSizeableTextView) {
            return ((AutoSizeableTextView) textView2).getAutoSizeTextType();
        }
        return 0;
    }

    public static int getAutoSizeStepGranularity(@NonNull TextView textView) {
        TextView textView2 = textView;
        if (Build.VERSION.SDK_INT >= 27) {
            return textView2.getAutoSizeStepGranularity();
        }
        if (textView2 instanceof AutoSizeableTextView) {
            return ((AutoSizeableTextView) textView2).getAutoSizeStepGranularity();
        }
        return -1;
    }

    public static int getAutoSizeMinTextSize(@NonNull TextView textView) {
        TextView textView2 = textView;
        if (Build.VERSION.SDK_INT >= 27) {
            return textView2.getAutoSizeMinTextSize();
        }
        if (textView2 instanceof AutoSizeableTextView) {
            return ((AutoSizeableTextView) textView2).getAutoSizeMinTextSize();
        }
        return -1;
    }

    public static int getAutoSizeMaxTextSize(@NonNull TextView textView) {
        TextView textView2 = textView;
        if (Build.VERSION.SDK_INT >= 27) {
            return textView2.getAutoSizeMaxTextSize();
        }
        if (textView2 instanceof AutoSizeableTextView) {
            return ((AutoSizeableTextView) textView2).getAutoSizeMaxTextSize();
        }
        return -1;
    }

    @NonNull
    public static int[] getAutoSizeTextAvailableSizes(@NonNull TextView textView) {
        TextView textView2 = textView;
        if (Build.VERSION.SDK_INT >= 27) {
            return textView2.getAutoSizeTextAvailableSizes();
        }
        if (textView2 instanceof AutoSizeableTextView) {
            return ((AutoSizeableTextView) textView2).getAutoSizeTextAvailableSizes();
        }
        return new int[0];
    }

    public static void setCustomSelectionActionModeCallback(@NonNull TextView textView, @NonNull ActionMode.Callback callback) {
        TextView textView2 = textView;
        textView2.setCustomSelectionActionModeCallback(wrapCustomSelectionActionModeCallback(textView2, callback));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NonNull
    public static ActionMode.Callback wrapCustomSelectionActionModeCallback(@NonNull TextView textView, @NonNull ActionMode.Callback callback) {
        ActionMode.Callback callback2;
        TextView textView2 = textView;
        ActionMode.Callback callback3 = callback;
        if (Build.VERSION.SDK_INT < 26 || Build.VERSION.SDK_INT > 27 || (callback3 instanceof OreoCallback)) {
            return callback3;
        }
        new OreoCallback(callback3, textView2);
        return callback2;
    }

    @RequiresApi(26)
    /* renamed from: android.support.v4.widget.TextViewCompat$OreoCallback */
    private static class OreoCallback implements ActionMode.Callback {
        private static final int MENU_ITEM_ORDER_PROCESS_TEXT_INTENT_ACTIONS_START = 100;
        private final ActionMode.Callback mCallback;
        private boolean mCanUseMenuBuilderReferences;
        private boolean mInitializedMenuBuilderReferences = false;
        private Class mMenuBuilderClass;
        private Method mMenuBuilderRemoveItemAtMethod;
        private final TextView mTextView;

        OreoCallback(ActionMode.Callback callback, TextView textView) {
            this.mCallback = callback;
            this.mTextView = textView;
        }

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return this.mCallback.onCreateActionMode(mode, menu);
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            Menu menu2 = menu;
            recomputeProcessTextMenuItems(menu2);
            return this.mCallback.onPrepareActionMode(mode, menu2);
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return this.mCallback.onActionItemClicked(mode, item);
        }

        public void onDestroyActionMode(ActionMode mode) {
            this.mCallback.onDestroyActionMode(mode);
        }

        private void recomputeProcessTextMenuItems(Menu menu) {
            Method declaredMethod;
            Menu menu2 = menu;
            Context context = this.mTextView.getContext();
            PackageManager packageManager = context.getPackageManager();
            if (!this.mInitializedMenuBuilderReferences) {
                this.mInitializedMenuBuilderReferences = true;
                try {
                    this.mMenuBuilderClass = Class.forName("com.android.internal.view.menu.MenuBuilder");
                    this.mMenuBuilderRemoveItemAtMethod = this.mMenuBuilderClass.getDeclaredMethod("removeItemAt", new Class[]{Integer.TYPE});
                    this.mCanUseMenuBuilderReferences = true;
                } catch (ClassNotFoundException | NoSuchMethodException e) {
                    Object obj = e;
                    this.mMenuBuilderClass = null;
                    this.mMenuBuilderRemoveItemAtMethod = null;
                    this.mCanUseMenuBuilderReferences = false;
                }
            }
            try {
                if (!this.mCanUseMenuBuilderReferences || !this.mMenuBuilderClass.isInstance(menu2)) {
                    declaredMethod = menu2.getClass().getDeclaredMethod("removeItemAt", new Class[]{Integer.TYPE});
                } else {
                    declaredMethod = this.mMenuBuilderRemoveItemAtMethod;
                }
                Method removeItemAtMethod = declaredMethod;
                for (int i = menu2.size() - 1; i >= 0; i--) {
                    MenuItem item = menu2.getItem(i);
                    if (item.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(item.getIntent().getAction())) {
                        Object invoke = removeItemAtMethod.invoke(menu2, new Object[]{Integer.valueOf(i)});
                    }
                }
                List<ResolveInfo> supportedActivities = getSupportedActivities(context, packageManager);
                for (int i2 = 0; i2 < supportedActivities.size(); i2++) {
                    ResolveInfo info = supportedActivities.get(i2);
                    menu2.add(0, 0, 100 + i2, info.loadLabel(packageManager)).setIntent(createProcessTextIntentForResolveInfo(info, this.mTextView)).setShowAsAction(1);
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                Object obj2 = e2;
            }
        }

        private List<ResolveInfo> getSupportedActivities(Context context, PackageManager packageManager) {
            List<ResolveInfo> list;
            Context context2 = context;
            PackageManager packageManager2 = packageManager;
            new ArrayList();
            List<ResolveInfo> supportedActivities = list;
            if (!(context2 instanceof Activity)) {
                return supportedActivities;
            }
            for (ResolveInfo info : packageManager2.queryIntentActivities(createProcessTextIntent(), 0)) {
                if (isSupportedActivity(info, context2)) {
                    boolean add = supportedActivities.add(info);
                }
            }
            return supportedActivities;
        }

        private boolean isSupportedActivity(ResolveInfo resolveInfo, Context context) {
            ResolveInfo info = resolveInfo;
            Context context2 = context;
            if (context2.getPackageName().equals(info.activityInfo.packageName)) {
                return true;
            }
            if (!info.activityInfo.exported) {
                return false;
            }
            return info.activityInfo.permission == null || context2.checkSelfPermission(info.activityInfo.permission) == 0;
        }

        private Intent createProcessTextIntentForResolveInfo(ResolveInfo resolveInfo, TextView textView11) {
            ResolveInfo info = resolveInfo;
            return createProcessTextIntent().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !isEditable(textView11)).setClassName(info.activityInfo.packageName, info.activityInfo.name);
        }

        private boolean isEditable(TextView textView) {
            TextView textView11 = textView;
            return (textView11 instanceof Editable) && textView11.onCheckIsTextEditor() && textView11.isEnabled();
        }

        private Intent createProcessTextIntent() {
            Intent intent;
            new Intent();
            return intent.setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
        }
    }

    public static void setFirstBaselineToTopHeight(@NonNull TextView textView, @C0015Px @IntRange(from = 0) int i) {
        int fontMetricsTop;
        TextView textView2 = textView;
        int firstBaselineToTopHeight = i;
        int checkArgumentNonnegative = Preconditions.checkArgumentNonnegative(firstBaselineToTopHeight);
        if (Build.VERSION.SDK_INT >= 28) {
            textView2.setFirstBaselineToTopHeight(firstBaselineToTopHeight);
            return;
        }
        Paint.FontMetricsInt fontMetrics = textView2.getPaint().getFontMetricsInt();
        if (Build.VERSION.SDK_INT < 16 || textView2.getIncludeFontPadding()) {
            fontMetricsTop = fontMetrics.top;
        } else {
            fontMetricsTop = fontMetrics.ascent;
        }
        if (firstBaselineToTopHeight > Math.abs(fontMetricsTop)) {
            textView2.setPadding(textView2.getPaddingLeft(), firstBaselineToTopHeight - (-fontMetricsTop), textView2.getPaddingRight(), textView2.getPaddingBottom());
        }
    }

    public static void setLastBaselineToBottomHeight(@NonNull TextView textView, @C0015Px @IntRange(from = 0) int i) {
        int fontMetricsBottom;
        TextView textView2 = textView;
        int lastBaselineToBottomHeight = i;
        int checkArgumentNonnegative = Preconditions.checkArgumentNonnegative(lastBaselineToBottomHeight);
        Paint.FontMetricsInt fontMetrics = textView2.getPaint().getFontMetricsInt();
        if (Build.VERSION.SDK_INT < 16 || textView2.getIncludeFontPadding()) {
            fontMetricsBottom = fontMetrics.bottom;
        } else {
            fontMetricsBottom = fontMetrics.descent;
        }
        if (lastBaselineToBottomHeight > Math.abs(fontMetricsBottom)) {
            textView2.setPadding(textView2.getPaddingLeft(), textView2.getPaddingTop(), textView2.getPaddingRight(), lastBaselineToBottomHeight - fontMetricsBottom);
        }
    }

    public static int getFirstBaselineToTopHeight(@NonNull TextView textView) {
        TextView textView2 = textView;
        return textView2.getPaddingTop() - textView2.getPaint().getFontMetricsInt().top;
    }

    public static int getLastBaselineToBottomHeight(@NonNull TextView textView) {
        TextView textView2 = textView;
        return textView2.getPaddingBottom() + textView2.getPaint().getFontMetricsInt().bottom;
    }

    public static void setLineHeight(@NonNull TextView textView, @C0015Px @IntRange(from = 0) int i) {
        TextView textView2 = textView;
        int lineHeight = i;
        int checkArgumentNonnegative = Preconditions.checkArgumentNonnegative(lineHeight);
        int fontHeight = textView2.getPaint().getFontMetricsInt((Paint.FontMetricsInt) null);
        if (lineHeight != fontHeight) {
            textView2.setLineSpacing((float) (lineHeight - fontHeight), 1.0f);
        }
    }

    @NonNull
    public static PrecomputedTextCompat.Params getTextMetricsParams(@NonNull TextView textView) {
        PrecomputedTextCompat.Params.Builder builder;
        TextPaint textPaint;
        PrecomputedTextCompat.Params params;
        TextView textView2 = textView;
        if (Build.VERSION.SDK_INT >= 28) {
            new PrecomputedTextCompat.Params(textView2.getTextMetricsParams());
            return params;
        }
        new TextPaint(textView2.getPaint());
        new PrecomputedTextCompat.Params.Builder(textPaint);
        PrecomputedTextCompat.Params.Builder builder2 = builder;
        if (Build.VERSION.SDK_INT >= 23) {
            PrecomputedTextCompat.Params.Builder breakStrategy = builder2.setBreakStrategy(textView2.getBreakStrategy());
            PrecomputedTextCompat.Params.Builder hyphenationFrequency = builder2.setHyphenationFrequency(textView2.getHyphenationFrequency());
        }
        if (Build.VERSION.SDK_INT >= 18) {
            PrecomputedTextCompat.Params.Builder textDirection = builder2.setTextDirection(getTextDirectionHeuristic(textView2));
        }
        return builder2.build();
    }

    public static void setTextMetricsParams(@NonNull TextView textView, @NonNull PrecomputedTextCompat.Params params) {
        TextView textView2 = textView;
        PrecomputedTextCompat.Params params2 = params;
        if (Build.VERSION.SDK_INT >= 18) {
            textView2.setTextDirection(getTextDirection(params2.getTextDirection()));
        }
        if (Build.VERSION.SDK_INT < 23) {
            float paintTextScaleX = params2.getTextPaint().getTextScaleX();
            textView2.getPaint().set(params2.getTextPaint());
            if (paintTextScaleX == textView2.getTextScaleX()) {
                textView2.setTextScaleX((paintTextScaleX / 2.0f) + 1.0f);
            }
            textView2.setTextScaleX(paintTextScaleX);
            return;
        }
        textView2.getPaint().set(params2.getTextPaint());
        textView2.setBreakStrategy(params2.getBreakStrategy());
        textView2.setHyphenationFrequency(params2.getHyphenationFrequency());
    }

    public static void setPrecomputedText(@NonNull TextView textView, @NonNull PrecomputedTextCompat precomputedTextCompat) {
        Throwable th;
        TextView textView2 = textView;
        PrecomputedTextCompat precomputed = precomputedTextCompat;
        if (Build.VERSION.SDK_INT >= 28) {
            textView2.setText(precomputed.getPrecomputedText());
        } else if (!getTextMetricsParams(textView2).equals(precomputed.getParams())) {
            Throwable th2 = th;
            new IllegalArgumentException("Given text can not be applied to TextView.");
            throw th2;
        } else {
            textView2.setText(precomputed);
        }
    }

    @RequiresApi(18)
    private static TextDirectionHeuristic getTextDirectionHeuristic(@NonNull TextView textView) {
        TextDirectionHeuristic textDirectionHeuristic;
        TextView textView2 = textView;
        if (textView2.getTransformationMethod() instanceof PasswordTransformationMethod) {
            return TextDirectionHeuristics.LTR;
        }
        if (Build.VERSION.SDK_INT < 28 || (textView2.getInputType() & 15) != 3) {
            boolean defaultIsRtl = textView2.getLayoutDirection() == 1;
            switch (textView2.getTextDirection()) {
                case 2:
                    return TextDirectionHeuristics.ANYRTL_LTR;
                case 3:
                    return TextDirectionHeuristics.LTR;
                case 4:
                    return TextDirectionHeuristics.RTL;
                case 5:
                    return TextDirectionHeuristics.LOCALE;
                case 6:
                    return TextDirectionHeuristics.FIRSTSTRONG_LTR;
                case 7:
                    return TextDirectionHeuristics.FIRSTSTRONG_RTL;
                default:
                    if (defaultIsRtl) {
                        textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    } else {
                        textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                    }
                    return textDirectionHeuristic;
            }
        } else {
            byte digitDirection = Character.getDirectionality(DecimalFormatSymbols.getInstance(textView2.getTextLocale()).getDigitStrings()[0].codePointAt(0));
            if (digitDirection == 1 || digitDirection == 2) {
                return TextDirectionHeuristics.RTL;
            }
            return TextDirectionHeuristics.LTR;
        }
    }

    @RequiresApi(18)
    private static int getTextDirection(@NonNull TextDirectionHeuristic textDirectionHeuristic) {
        TextDirectionHeuristic heuristic = textDirectionHeuristic;
        if (heuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL) {
            return 1;
        }
        if (heuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            return 1;
        }
        if (heuristic == TextDirectionHeuristics.ANYRTL_LTR) {
            return 2;
        }
        if (heuristic == TextDirectionHeuristics.LTR) {
            return 3;
        }
        if (heuristic == TextDirectionHeuristics.RTL) {
            return 4;
        }
        if (heuristic == TextDirectionHeuristics.LOCALE) {
            return 5;
        }
        if (heuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            return 6;
        }
        if (heuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL) {
            return 7;
        }
        return 1;
    }
}
