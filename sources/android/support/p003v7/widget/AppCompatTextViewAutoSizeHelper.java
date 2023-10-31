package android.support.p003v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p003v7.appcompat.C0425R;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: android.support.v7.widget.AppCompatTextViewAutoSizeHelper */
class AppCompatTextViewAutoSizeHelper {
    private static final int DEFAULT_AUTO_SIZE_GRANULARITY_IN_PX = 1;
    private static final int DEFAULT_AUTO_SIZE_MAX_TEXT_SIZE_IN_SP = 112;
    private static final int DEFAULT_AUTO_SIZE_MIN_TEXT_SIZE_IN_SP = 12;
    private static final String TAG = "ACTVAutoSizeHelper";
    private static final RectF TEMP_RECTF;
    static final float UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE = -1.0f;
    private static final int VERY_WIDE = 1048576;
    private static ConcurrentHashMap<String, Method> sTextViewMethodByNameCache;
    private float mAutoSizeMaxTextSizeInPx = -1.0f;
    private float mAutoSizeMinTextSizeInPx = -1.0f;
    private float mAutoSizeStepGranularityInPx = -1.0f;
    private int[] mAutoSizeTextSizesInPx = new int[0];
    private int mAutoSizeTextType = 0;
    private final Context mContext;
    private boolean mHasPresetAutoSizeValues = false;
    private boolean mNeedsAutoSizeText = false;
    private TextPaint mTempTextPaint;
    private final TextView mTextView;

    static {
        RectF rectF;
        ConcurrentHashMap<String, Method> concurrentHashMap;
        new RectF();
        TEMP_RECTF = rectF;
        new ConcurrentHashMap<>();
        sTextViewMethodByNameCache = concurrentHashMap;
    }

    AppCompatTextViewAutoSizeHelper(TextView textView) {
        this.mTextView = textView;
        this.mContext = this.mTextView.getContext();
    }

    /* access modifiers changed from: package-private */
    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        int autoSizeStepSizeArrayResId;
        float autoSizeMinTextSizeInPx = -1.0f;
        float autoSizeMaxTextSizeInPx = -1.0f;
        float autoSizeStepGranularityInPx = -1.0f;
        TypedArray a = this.mContext.obtainStyledAttributes(attrs, C0425R.styleable.AppCompatTextView, defStyleAttr, 0);
        if (a.hasValue(C0425R.styleable.AppCompatTextView_autoSizeTextType)) {
            this.mAutoSizeTextType = a.getInt(C0425R.styleable.AppCompatTextView_autoSizeTextType, 0);
        }
        if (a.hasValue(C0425R.styleable.AppCompatTextView_autoSizeStepGranularity)) {
            autoSizeStepGranularityInPx = a.getDimension(C0425R.styleable.AppCompatTextView_autoSizeStepGranularity, -1.0f);
        }
        if (a.hasValue(C0425R.styleable.AppCompatTextView_autoSizeMinTextSize)) {
            autoSizeMinTextSizeInPx = a.getDimension(C0425R.styleable.AppCompatTextView_autoSizeMinTextSize, -1.0f);
        }
        if (a.hasValue(C0425R.styleable.AppCompatTextView_autoSizeMaxTextSize)) {
            autoSizeMaxTextSizeInPx = a.getDimension(C0425R.styleable.AppCompatTextView_autoSizeMaxTextSize, -1.0f);
        }
        if (a.hasValue(C0425R.styleable.AppCompatTextView_autoSizePresetSizes) && (autoSizeStepSizeArrayResId = a.getResourceId(C0425R.styleable.AppCompatTextView_autoSizePresetSizes, 0)) > 0) {
            TypedArray autoSizePreDefTextSizes = a.getResources().obtainTypedArray(autoSizeStepSizeArrayResId);
            setupAutoSizeUniformPresetSizes(autoSizePreDefTextSizes);
            autoSizePreDefTextSizes.recycle();
        }
        a.recycle();
        if (!supportsAutoSizeText()) {
            this.mAutoSizeTextType = 0;
        } else if (this.mAutoSizeTextType == 1) {
            if (!this.mHasPresetAutoSizeValues) {
                DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
                if (autoSizeMinTextSizeInPx == -1.0f) {
                    autoSizeMinTextSizeInPx = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (autoSizeMaxTextSizeInPx == -1.0f) {
                    autoSizeMaxTextSizeInPx = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (autoSizeStepGranularityInPx == -1.0f) {
                    autoSizeStepGranularityInPx = 1.0f;
                }
                validateAndSetAutoSizeTextTypeUniformConfiguration(autoSizeMinTextSizeInPx, autoSizeMaxTextSizeInPx, autoSizeStepGranularityInPx);
            }
            boolean z = setupAutoSizeText();
        }
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setAutoSizeTextTypeWithDefaults(int i) {
        Throwable th;
        StringBuilder sb;
        int autoSizeTextType = i;
        if (supportsAutoSizeText()) {
            switch (autoSizeTextType) {
                case 0:
                    clearAutoSizeConfiguration();
                    return;
                case 1:
                    DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
                    validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
                    if (setupAutoSizeText()) {
                        autoSizeText();
                        return;
                    }
                    return;
                default:
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Unknown auto-size text type: ").append(autoSizeTextType).toString());
                    throw th2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        int autoSizeMinTextSize = i;
        int autoSizeMaxTextSize = i2;
        int autoSizeStepGranularity = i3;
        int unit = i4;
        if (supportsAutoSizeText()) {
            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
            validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(unit, (float) autoSizeMinTextSize, displayMetrics), TypedValue.applyDimension(unit, (float) autoSizeMaxTextSize, displayMetrics), TypedValue.applyDimension(unit, (float) autoSizeStepGranularity, displayMetrics));
            if (setupAutoSizeText()) {
                autoSizeText();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] iArr, int i) throws IllegalArgumentException {
        Throwable th;
        StringBuilder sb;
        int[] presetSizes = iArr;
        int unit = i;
        if (supportsAutoSizeText()) {
            int presetSizesLength = presetSizes.length;
            if (presetSizesLength > 0) {
                int[] presetSizesInPx = new int[presetSizesLength];
                if (unit == 0) {
                    presetSizesInPx = Arrays.copyOf(presetSizes, presetSizesLength);
                } else {
                    DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
                    for (int i2 = 0; i2 < presetSizesLength; i2++) {
                        presetSizesInPx[i2] = Math.round(TypedValue.applyDimension(unit, (float) presetSizes[i2], displayMetrics));
                    }
                }
                this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(presetSizesInPx);
                if (!setupAutoSizeUniformPresetSizesConfiguration()) {
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("None of the preset sizes is valid: ").append(Arrays.toString(presetSizes)).toString());
                    throw th2;
                }
            } else {
                this.mHasPresetAutoSizeValues = false;
            }
            if (setupAutoSizeText()) {
                autoSizeText();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getAutoSizeTextType() {
        return this.mAutoSizeTextType;
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getAutoSizeStepGranularity() {
        return Math.round(this.mAutoSizeStepGranularityInPx);
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getAutoSizeMinTextSize() {
        return Math.round(this.mAutoSizeMinTextSizeInPx);
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getAutoSizeMaxTextSize() {
        return Math.round(this.mAutoSizeMaxTextSizeInPx);
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextSizesInPx;
    }

    private void setupAutoSizeUniformPresetSizes(TypedArray typedArray) {
        TypedArray textSizes = typedArray;
        int textSizesLength = textSizes.length();
        int[] parsedSizes = new int[textSizesLength];
        if (textSizesLength > 0) {
            for (int i = 0; i < textSizesLength; i++) {
                parsedSizes[i] = textSizes.getDimensionPixelSize(i, -1);
            }
            this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(parsedSizes);
            boolean z = setupAutoSizeUniformPresetSizesConfiguration();
        }
    }

    private boolean setupAutoSizeUniformPresetSizesConfiguration() {
        int sizesLength = this.mAutoSizeTextSizesInPx.length;
        this.mHasPresetAutoSizeValues = sizesLength > 0;
        if (this.mHasPresetAutoSizeValues) {
            this.mAutoSizeTextType = 1;
            this.mAutoSizeMinTextSizeInPx = (float) this.mAutoSizeTextSizesInPx[0];
            this.mAutoSizeMaxTextSizeInPx = (float) this.mAutoSizeTextSizesInPx[sizesLength - 1];
            this.mAutoSizeStepGranularityInPx = -1.0f;
        }
        return this.mHasPresetAutoSizeValues;
    }

    private int[] cleanupAutoSizePresetSizes(int[] iArr) {
        List<Integer> list;
        int[] presetValues = iArr;
        int presetValuesLength = presetValues.length;
        if (presetValuesLength == 0) {
            return presetValues;
        }
        Arrays.sort(presetValues);
        new ArrayList<>();
        List<Integer> uniqueValidSizes = list;
        for (int i = 0; i < presetValuesLength; i++) {
            int currentPresetValue = presetValues[i];
            if (currentPresetValue > 0 && Collections.binarySearch(uniqueValidSizes, Integer.valueOf(currentPresetValue)) < 0) {
                boolean add = uniqueValidSizes.add(Integer.valueOf(currentPresetValue));
            }
        }
        if (presetValuesLength == uniqueValidSizes.size()) {
            return presetValues;
        }
        int i2 = uniqueValidSizes.size();
        int[] cleanedUpSizes = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            cleanedUpSizes[i3] = uniqueValidSizes.get(i3).intValue();
        }
        return cleanedUpSizes;
    }

    private void validateAndSetAutoSizeTextTypeUniformConfiguration(float f, float f2, float f3) throws IllegalArgumentException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        float autoSizeMinTextSizeInPx = f;
        float autoSizeMaxTextSizeInPx = f2;
        float autoSizeStepGranularityInPx = f3;
        if (autoSizeMinTextSizeInPx <= 0.0f) {
            Throwable th4 = th3;
            new StringBuilder();
            new IllegalArgumentException(sb3.append("Minimum auto-size text size (").append(autoSizeMinTextSizeInPx).append("px) is less or equal to (0px)").toString());
            throw th4;
        } else if (autoSizeMaxTextSizeInPx <= autoSizeMinTextSizeInPx) {
            Throwable th5 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Maximum auto-size text size (").append(autoSizeMaxTextSizeInPx).append("px) is less or equal to minimum auto-size ").append("text size (").append(autoSizeMinTextSizeInPx).append("px)").toString());
            throw th5;
        } else if (autoSizeStepGranularityInPx <= 0.0f) {
            Throwable th6 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("The auto-size step granularity (").append(autoSizeStepGranularityInPx).append("px) is less or equal to (0px)").toString());
            throw th6;
        } else {
            this.mAutoSizeTextType = 1;
            this.mAutoSizeMinTextSizeInPx = autoSizeMinTextSizeInPx;
            this.mAutoSizeMaxTextSizeInPx = autoSizeMaxTextSizeInPx;
            this.mAutoSizeStepGranularityInPx = autoSizeStepGranularityInPx;
            this.mHasPresetAutoSizeValues = false;
        }
    }

    private boolean setupAutoSizeText() {
        if (!supportsAutoSizeText() || this.mAutoSizeTextType != 1) {
            this.mNeedsAutoSizeText = false;
        } else {
            if (!this.mHasPresetAutoSizeValues || this.mAutoSizeTextSizesInPx.length == 0) {
                int autoSizeValuesLength = 1;
                float round = (float) Math.round(this.mAutoSizeMinTextSizeInPx);
                while (true) {
                    float currentSize = round;
                    if (Math.round(currentSize + this.mAutoSizeStepGranularityInPx) > Math.round(this.mAutoSizeMaxTextSizeInPx)) {
                        break;
                    }
                    autoSizeValuesLength++;
                    round = currentSize + this.mAutoSizeStepGranularityInPx;
                }
                int[] autoSizeTextSizesInPx = new int[autoSizeValuesLength];
                float sizeToAdd = this.mAutoSizeMinTextSizeInPx;
                for (int i = 0; i < autoSizeValuesLength; i++) {
                    autoSizeTextSizesInPx[i] = Math.round(sizeToAdd);
                    sizeToAdd += this.mAutoSizeStepGranularityInPx;
                }
                this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(autoSizeTextSizesInPx);
            }
            this.mNeedsAutoSizeText = true;
        }
        return this.mNeedsAutoSizeText;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void autoSizeText() {
        int measuredWidth;
        if (isAutoSizeEnabled()) {
            if (this.mNeedsAutoSizeText) {
                if (this.mTextView.getMeasuredHeight() > 0 && this.mTextView.getMeasuredWidth() > 0) {
                    if (((Boolean) invokeAndReturnWithDefault(this.mTextView, "getHorizontallyScrolling", false)).booleanValue()) {
                        measuredWidth = 1048576;
                    } else {
                        measuredWidth = (this.mTextView.getMeasuredWidth() - this.mTextView.getTotalPaddingLeft()) - this.mTextView.getTotalPaddingRight();
                    }
                    int availableWidth = measuredWidth;
                    int availableHeight = (this.mTextView.getHeight() - this.mTextView.getCompoundPaddingBottom()) - this.mTextView.getCompoundPaddingTop();
                    if (availableWidth > 0 && availableHeight > 0) {
                        RectF rectF = TEMP_RECTF;
                        RectF rectF2 = rectF;
                        synchronized (rectF) {
                            try {
                                TEMP_RECTF.setEmpty();
                                TEMP_RECTF.right = (float) availableWidth;
                                TEMP_RECTF.bottom = (float) availableHeight;
                                float optimalTextSize = (float) findLargestTextSizeWhichFits(TEMP_RECTF);
                                if (optimalTextSize != this.mTextView.getTextSize()) {
                                    setTextSizeInternal(0, optimalTextSize);
                                }
                            } catch (Throwable th) {
                                while (true) {
                                    Throwable th2 = th;
                                    RectF rectF3 = rectF2;
                                    throw th2;
                                }
                            }
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.mNeedsAutoSizeText = true;
        }
    }

    private void clearAutoSizeConfiguration() {
        this.mAutoSizeTextType = 0;
        this.mAutoSizeMinTextSizeInPx = -1.0f;
        this.mAutoSizeMaxTextSizeInPx = -1.0f;
        this.mAutoSizeStepGranularityInPx = -1.0f;
        this.mAutoSizeTextSizesInPx = new int[0];
        this.mNeedsAutoSizeText = false;
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setTextSizeInternal(int i, float f) {
        Resources res;
        int unit = i;
        float size = f;
        if (this.mContext == null) {
            res = Resources.getSystem();
        } else {
            res = this.mContext.getResources();
        }
        setRawTextSize(TypedValue.applyDimension(unit, size, res.getDisplayMetrics()));
    }

    private void setRawTextSize(float f) {
        float size = f;
        if (size != this.mTextView.getPaint().getTextSize()) {
            this.mTextView.getPaint().setTextSize(size);
            boolean isInLayout = false;
            if (Build.VERSION.SDK_INT >= 18) {
                isInLayout = this.mTextView.isInLayout();
            }
            if (this.mTextView.getLayout() != null) {
                this.mNeedsAutoSizeText = false;
                Object obj = "nullLayouts";
                try {
                    Method method = getTextViewMethod("nullLayouts");
                    if (method != null) {
                        Object invoke = method.invoke(this.mTextView, new Object[0]);
                    }
                } catch (Exception e) {
                    int w = Log.w(TAG, "Failed to invoke TextView#nullLayouts() method", e);
                }
                if (!isInLayout) {
                    this.mTextView.requestLayout();
                } else {
                    this.mTextView.forceLayout();
                }
                this.mTextView.invalidate();
            }
        }
    }

    private int findLargestTextSizeWhichFits(RectF rectF) {
        Throwable th;
        RectF availableSpace = rectF;
        int sizesCount = this.mAutoSizeTextSizesInPx.length;
        if (sizesCount == 0) {
            Throwable th2 = th;
            new IllegalStateException("No available text sizes to choose from.");
            throw th2;
        }
        int bestSizeIndex = 0;
        int lowIndex = 0 + 1;
        int highIndex = sizesCount - 1;
        while (lowIndex <= highIndex) {
            int sizeToTryIndex = (lowIndex + highIndex) / 2;
            if (suggestedSizeFitsInSpace(this.mAutoSizeTextSizesInPx[sizeToTryIndex], availableSpace)) {
                bestSizeIndex = lowIndex;
                lowIndex = sizeToTryIndex + 1;
            } else {
                highIndex = sizeToTryIndex - 1;
                bestSizeIndex = highIndex;
            }
        }
        return this.mAutoSizeTextSizesInPx[bestSizeIndex];
    }

    private boolean suggestedSizeFitsInSpace(int i, RectF rectF) {
        StaticLayout createStaticLayoutForMeasuringPre23;
        TextPaint textPaint;
        CharSequence transformedText;
        int suggestedSizeInPx = i;
        RectF availableSpace = rectF;
        CharSequence text = this.mTextView.getText();
        TransformationMethod transformationMethod = this.mTextView.getTransformationMethod();
        if (!(transformationMethod == null || (transformedText = transformationMethod.getTransformation(text, this.mTextView)) == null)) {
            text = transformedText;
        }
        int maxLines = Build.VERSION.SDK_INT >= 16 ? this.mTextView.getMaxLines() : -1;
        if (this.mTempTextPaint == null) {
            new TextPaint();
            this.mTempTextPaint = textPaint;
        } else {
            this.mTempTextPaint.reset();
        }
        this.mTempTextPaint.set(this.mTextView.getPaint());
        this.mTempTextPaint.setTextSize((float) suggestedSizeInPx);
        Layout.Alignment alignment = (Layout.Alignment) invokeAndReturnWithDefault(this.mTextView, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL);
        if (Build.VERSION.SDK_INT >= 23) {
            createStaticLayoutForMeasuringPre23 = createStaticLayoutForMeasuring(text, alignment, Math.round(availableSpace.right), maxLines);
        } else {
            createStaticLayoutForMeasuringPre23 = createStaticLayoutForMeasuringPre23(text, alignment, Math.round(availableSpace.right));
        }
        StaticLayout layout = createStaticLayoutForMeasuringPre23;
        if (maxLines != -1 && (layout.getLineCount() > maxLines || layout.getLineEnd(layout.getLineCount() - 1) != text.length())) {
            return false;
        }
        if (((float) layout.getHeight()) > availableSpace.bottom) {
            return false;
        }
        return true;
    }

    @RequiresApi(23)
    private StaticLayout createStaticLayoutForMeasuring(CharSequence charSequence, Layout.Alignment alignment, int availableWidth, int i) {
        CharSequence text = charSequence;
        int maxLines = i;
        return StaticLayout.Builder.obtain(text, 0, text.length(), this.mTempTextPaint, availableWidth).setAlignment(alignment).setLineSpacing(this.mTextView.getLineSpacingExtra(), this.mTextView.getLineSpacingMultiplier()).setIncludePad(this.mTextView.getIncludeFontPadding()).setBreakStrategy(this.mTextView.getBreakStrategy()).setHyphenationFrequency(this.mTextView.getHyphenationFrequency()).setMaxLines(maxLines == -1 ? Integer.MAX_VALUE : maxLines).setTextDirection((TextDirectionHeuristic) invokeAndReturnWithDefault(this.mTextView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR)).build();
    }

    private StaticLayout createStaticLayoutForMeasuringPre23(CharSequence charSequence, Layout.Alignment alignment, int i) {
        float lineSpacingMultiplier;
        float lineSpacingAdd;
        boolean includePad;
        StaticLayout staticLayout;
        CharSequence text = charSequence;
        Layout.Alignment alignment2 = alignment;
        int availableWidth = i;
        if (Build.VERSION.SDK_INT >= 16) {
            lineSpacingMultiplier = this.mTextView.getLineSpacingMultiplier();
            lineSpacingAdd = this.mTextView.getLineSpacingExtra();
            includePad = this.mTextView.getIncludeFontPadding();
        } else {
            lineSpacingMultiplier = ((Float) invokeAndReturnWithDefault(this.mTextView, "getLineSpacingMultiplier", Float.valueOf(1.0f))).floatValue();
            lineSpacingAdd = ((Float) invokeAndReturnWithDefault(this.mTextView, "getLineSpacingExtra", Float.valueOf(0.0f))).floatValue();
            includePad = ((Boolean) invokeAndReturnWithDefault(this.mTextView, "getIncludeFontPadding", true)).booleanValue();
        }
        new StaticLayout(text, this.mTempTextPaint, availableWidth, alignment2, lineSpacingMultiplier, lineSpacingAdd, includePad);
        return staticLayout;
    }

    private <T> T invokeAndReturnWithDefault(@NonNull Object object, @NonNull String str, @NonNull T t) {
        StringBuilder sb;
        String methodName = str;
        T defaultValue = t;
        T result = null;
        try {
            result = getTextViewMethod(methodName).invoke(object, new Object[0]);
            if (result == null && 0 != 0) {
                result = defaultValue;
            }
        } catch (Exception e) {
            Exception ex = e;
            new StringBuilder();
            int w = Log.w(TAG, sb.append("Failed to invoke TextView#").append(methodName).append("() method").toString(), ex);
            if (0 == 0 && 1 != 0) {
                result = defaultValue;
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            if (0 == 0 && 1 != 0) {
                T result2 = defaultValue;
            }
            throw th2;
        }
        return result;
    }

    @Nullable
    private Method getTextViewMethod(@NonNull String str) {
        StringBuilder sb;
        String methodName = str;
        try {
            Method method = sTextViewMethodByNameCache.get(methodName);
            if (method == null) {
                method = TextView.class.getDeclaredMethod(methodName, new Class[0]);
                if (method != null) {
                    method.setAccessible(true);
                    Method put = sTextViewMethodByNameCache.put(methodName, method);
                }
            }
            return method;
        } catch (Exception e) {
            new StringBuilder();
            int w = Log.w(TAG, sb.append("Failed to retrieve TextView#").append(methodName).append("() method").toString(), e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isAutoSizeEnabled() {
        return supportsAutoSizeText() && this.mAutoSizeTextType != 0;
    }

    private boolean supportsAutoSizeText() {
        return !(this.mTextView instanceof AppCompatEditText);
    }
}
