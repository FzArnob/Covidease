package android.support.design.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.annotation.VisibleForTesting;
import android.support.design.C0064R;
import android.support.design.animation.AnimationUtils;
import android.support.design.internal.ViewUtils;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.view.AbsSavedState;
import android.support.p000v4.view.AccessibilityDelegateCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p000v4.widget.TextViewCompat;
import android.support.p003v7.content.res.AppCompatResources;
import android.support.p003v7.widget.AppCompatDrawableManager;
import android.support.p003v7.widget.AppCompatTextView;
import android.support.p003v7.widget.DrawableUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TextInputLayout extends LinearLayout {
    public static final int BOX_BACKGROUND_FILLED = 1;
    public static final int BOX_BACKGROUND_NONE = 0;
    public static final int BOX_BACKGROUND_OUTLINE = 2;
    private static final int INVALID_MAX_LENGTH = -1;
    private static final int LABEL_SCALE_ANIMATION_DURATION = 167;
    private static final String LOG_TAG = "TextInputLayout";
    private ValueAnimator animator;
    private GradientDrawable boxBackground;
    @ColorInt
    private int boxBackgroundColor;
    private int boxBackgroundMode;
    private final int boxBottomOffsetPx;
    private final int boxCollapsedPaddingTopPx;
    private float boxCornerRadiusBottomEnd;
    private float boxCornerRadiusBottomStart;
    private float boxCornerRadiusTopEnd;
    private float boxCornerRadiusTopStart;
    private final int boxLabelCutoutPaddingPx;
    @ColorInt
    private int boxStrokeColor;
    private final int boxStrokeWidthDefaultPx;
    private final int boxStrokeWidthFocusedPx;
    private int boxStrokeWidthPx;
    final CollapsingTextHelper collapsingTextHelper;
    boolean counterEnabled;
    private int counterMaxLength;
    private final int counterOverflowTextAppearance;
    private boolean counterOverflowed;
    private final int counterTextAppearance;
    private TextView counterView;
    private ColorStateList defaultHintTextColor;
    @ColorInt
    private final int defaultStrokeColor;
    @ColorInt
    private final int disabledColor;
    EditText editText;
    private Drawable editTextOriginalDrawable;
    @ColorInt
    private int focusedStrokeColor;
    private ColorStateList focusedTextColor;
    private boolean hasPasswordToggleTintList;
    private boolean hasPasswordToggleTintMode;
    private boolean hasReconstructedEditTextBackground;
    private CharSequence hint;
    private boolean hintAnimationEnabled;
    private boolean hintEnabled;
    private boolean hintExpanded;
    @ColorInt
    private final int hoveredStrokeColor;
    private boolean inDrawableStateChanged;
    private final IndicatorViewController indicatorViewController;
    private final FrameLayout inputFrame;
    private boolean isProvidingHint;
    private Drawable originalEditTextEndDrawable;
    private CharSequence originalHint;
    private CharSequence passwordToggleContentDesc;
    private Drawable passwordToggleDrawable;
    private Drawable passwordToggleDummyDrawable;
    private boolean passwordToggleEnabled;
    private ColorStateList passwordToggleTintList;
    private PorterDuff.Mode passwordToggleTintMode;
    private CheckableImageButton passwordToggleView;
    private boolean passwordToggledVisible;
    /* access modifiers changed from: private */
    public boolean restoringSavedState;
    private final Rect tmpRect;
    private final RectF tmpRectF;
    private Typeface typeface;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BoxBackgroundMode {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TextInputLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TextInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, C0064R.attr.textInputStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TextInputLayout(android.content.Context r24, android.util.AttributeSet r25, int r26) {
        /*
            r23 = this;
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r15 = r2
            r16 = r3
            r17 = r4
            r18 = r5
            r15.<init>(r16, r17, r18)
            r15 = r2
            android.support.design.widget.IndicatorViewController r16 = new android.support.design.widget.IndicatorViewController
            r21 = r16
            r16 = r21
            r17 = r21
            r18 = r2
            r17.<init>(r18)
            r0 = r16
            r15.indicatorViewController = r0
            r15 = r2
            android.graphics.Rect r16 = new android.graphics.Rect
            r21 = r16
            r16 = r21
            r17 = r21
            r17.<init>()
            r0 = r16
            r15.tmpRect = r0
            r15 = r2
            android.graphics.RectF r16 = new android.graphics.RectF
            r21 = r16
            r16 = r21
            r17 = r21
            r17.<init>()
            r0 = r16
            r15.tmpRectF = r0
            r15 = r2
            android.support.design.widget.CollapsingTextHelper r16 = new android.support.design.widget.CollapsingTextHelper
            r21 = r16
            r16 = r21
            r17 = r21
            r18 = r2
            r17.<init>(r18)
            r0 = r16
            r15.collapsingTextHelper = r0
            r15 = r2
            r16 = 1
            r15.setOrientation(r16)
            r15 = r2
            r16 = 0
            r15.setWillNotDraw(r16)
            r15 = r2
            r16 = 1
            r15.setAddStatesFromChildren(r16)
            r15 = r2
            android.widget.FrameLayout r16 = new android.widget.FrameLayout
            r21 = r16
            r16 = r21
            r17 = r21
            r18 = r3
            r17.<init>(r18)
            r0 = r16
            r15.inputFrame = r0
            r15 = r2
            android.widget.FrameLayout r15 = r15.inputFrame
            r16 = 1
            r15.setAddStatesFromChildren(r16)
            r15 = r2
            r16 = r2
            r0 = r16
            android.widget.FrameLayout r0 = r0.inputFrame
            r16 = r0
            r15.addView(r16)
            r15 = r2
            android.support.design.widget.CollapsingTextHelper r15 = r15.collapsingTextHelper
            android.animation.TimeInterpolator r16 = android.support.design.animation.AnimationUtils.LINEAR_INTERPOLATOR
            r15.setTextSizeInterpolator(r16)
            r15 = r2
            android.support.design.widget.CollapsingTextHelper r15 = r15.collapsingTextHelper
            android.animation.TimeInterpolator r16 = android.support.design.animation.AnimationUtils.LINEAR_INTERPOLATOR
            r15.setPositionInterpolator(r16)
            r15 = r2
            android.support.design.widget.CollapsingTextHelper r15 = r15.collapsingTextHelper
            r16 = 8388659(0x800033, float:1.1755015E-38)
            r15.setCollapsedTextGravity(r16)
            r15 = r3
            r16 = r4
            int[] r17 = android.support.design.C0064R.styleable.TextInputLayout
            r18 = r5
            int r19 = android.support.design.C0064R.C0068style.Widget_Design_TextInputLayout
            r20 = 0
            r0 = r20
            int[] r0 = new int[r0]
            r20 = r0
            android.support.v7.widget.TintTypedArray r15 = android.support.design.internal.ThemeEnforcement.obtainTintedStyledAttributes(r15, r16, r17, r18, r19, r20)
            r6 = r15
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_hintEnabled
            r18 = 1
            boolean r16 = r16.getBoolean(r17, r18)
            r0 = r16
            r15.hintEnabled = r0
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_android_hint
            java.lang.CharSequence r16 = r16.getText(r17)
            r15.setHint(r16)
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_hintAnimationEnabled
            r18 = 1
            boolean r16 = r16.getBoolean(r17, r18)
            r0 = r16
            r15.hintAnimationEnabled = r0
            r15 = r2
            r16 = r3
            android.content.res.Resources r16 = r16.getResources()
            int r17 = android.support.design.C0064R.dimen.mtrl_textinput_box_bottom_offset
            int r16 = r16.getDimensionPixelOffset(r17)
            r0 = r16
            r15.boxBottomOffsetPx = r0
            r15 = r2
            r16 = r3
            android.content.res.Resources r16 = r16.getResources()
            int r17 = android.support.design.C0064R.dimen.mtrl_textinput_box_label_cutout_padding
            int r16 = r16.getDimensionPixelOffset(r17)
            r0 = r16
            r15.boxLabelCutoutPaddingPx = r0
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_boxCollapsedPaddingTop
            r18 = 0
            int r16 = r16.getDimensionPixelOffset(r17, r18)
            r0 = r16
            r15.boxCollapsedPaddingTopPx = r0
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_boxCornerRadiusTopStart
            r18 = 0
            float r16 = r16.getDimension(r17, r18)
            r0 = r16
            r15.boxCornerRadiusTopStart = r0
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_boxCornerRadiusTopEnd
            r18 = 0
            float r16 = r16.getDimension(r17, r18)
            r0 = r16
            r15.boxCornerRadiusTopEnd = r0
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_boxCornerRadiusBottomEnd
            r18 = 0
            float r16 = r16.getDimension(r17, r18)
            r0 = r16
            r15.boxCornerRadiusBottomEnd = r0
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_boxCornerRadiusBottomStart
            r18 = 0
            float r16 = r16.getDimension(r17, r18)
            r0 = r16
            r15.boxCornerRadiusBottomStart = r0
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_boxBackgroundColor
            r18 = 0
            int r16 = r16.getColor(r17, r18)
            r0 = r16
            r15.boxBackgroundColor = r0
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_boxStrokeColor
            r18 = 0
            int r16 = r16.getColor(r17, r18)
            r0 = r16
            r15.focusedStrokeColor = r0
            r15 = r2
            r16 = r3
            android.content.res.Resources r16 = r16.getResources()
            int r17 = android.support.design.C0064R.dimen.mtrl_textinput_box_stroke_width_default
            int r16 = r16.getDimensionPixelSize(r17)
            r0 = r16
            r15.boxStrokeWidthDefaultPx = r0
            r15 = r2
            r16 = r3
            android.content.res.Resources r16 = r16.getResources()
            int r17 = android.support.design.C0064R.dimen.mtrl_textinput_box_stroke_width_focused
            int r16 = r16.getDimensionPixelSize(r17)
            r0 = r16
            r15.boxStrokeWidthFocusedPx = r0
            r15 = r2
            r16 = r2
            r0 = r16
            int r0 = r0.boxStrokeWidthDefaultPx
            r16 = r0
            r0 = r16
            r15.boxStrokeWidthPx = r0
            r15 = r6
            int r16 = android.support.design.C0064R.styleable.TextInputLayout_boxBackgroundMode
            r17 = 0
            int r15 = r15.getInt(r16, r17)
            r7 = r15
            r15 = r2
            r16 = r7
            r15.setBoxBackgroundMode(r16)
            r15 = r6
            int r16 = android.support.design.C0064R.styleable.TextInputLayout_android_textColorHint
            boolean r15 = r15.hasValue(r16)
            if (r15 == 0) goto L_0x01d9
            r15 = r2
            r16 = r2
            r17 = r6
            int r18 = android.support.design.C0064R.styleable.TextInputLayout_android_textColorHint
            android.content.res.ColorStateList r17 = r17.getColorStateList(r18)
            r21 = r16
            r22 = r17
            r16 = r22
            r17 = r21
            r18 = r22
            r0 = r18
            r1 = r17
            r1.focusedTextColor = r0
            r0 = r16
            r15.defaultHintTextColor = r0
        L_0x01d9:
            r15 = r2
            r16 = r3
            int r17 = android.support.design.C0064R.color.mtrl_textinput_default_box_stroke_color
            int r16 = android.support.p000v4.content.ContextCompat.getColor(r16, r17)
            r0 = r16
            r15.defaultStrokeColor = r0
            r15 = r2
            r16 = r3
            int r17 = android.support.design.C0064R.color.mtrl_textinput_disabled_color
            int r16 = android.support.p000v4.content.ContextCompat.getColor(r16, r17)
            r0 = r16
            r15.disabledColor = r0
            r15 = r2
            r16 = r3
            int r17 = android.support.design.C0064R.color.mtrl_textinput_hovered_box_stroke_color
            int r16 = android.support.p000v4.content.ContextCompat.getColor(r16, r17)
            r0 = r16
            r15.hoveredStrokeColor = r0
            r15 = r6
            int r16 = android.support.design.C0064R.styleable.TextInputLayout_hintTextAppearance
            r17 = -1
            int r15 = r15.getResourceId(r16, r17)
            r8 = r15
            r15 = r8
            r16 = -1
            r0 = r16
            if (r15 == r0) goto L_0x021f
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_hintTextAppearance
            r18 = 0
            int r16 = r16.getResourceId(r17, r18)
            r15.setHintTextAppearance(r16)
        L_0x021f:
            r15 = r6
            int r16 = android.support.design.C0064R.styleable.TextInputLayout_errorTextAppearance
            r17 = 0
            int r15 = r15.getResourceId(r16, r17)
            r9 = r15
            r15 = r6
            int r16 = android.support.design.C0064R.styleable.TextInputLayout_errorEnabled
            r17 = 0
            boolean r15 = r15.getBoolean(r16, r17)
            r10 = r15
            r15 = r6
            int r16 = android.support.design.C0064R.styleable.TextInputLayout_helperTextTextAppearance
            r17 = 0
            int r15 = r15.getResourceId(r16, r17)
            r11 = r15
            r15 = r6
            int r16 = android.support.design.C0064R.styleable.TextInputLayout_helperTextEnabled
            r17 = 0
            boolean r15 = r15.getBoolean(r16, r17)
            r12 = r15
            r15 = r6
            int r16 = android.support.design.C0064R.styleable.TextInputLayout_helperText
            java.lang.CharSequence r15 = r15.getText(r16)
            r13 = r15
            r15 = r6
            int r16 = android.support.design.C0064R.styleable.TextInputLayout_counterEnabled
            r17 = 0
            boolean r15 = r15.getBoolean(r16, r17)
            r14 = r15
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_counterMaxLength
            r18 = -1
            int r16 = r16.getInt(r17, r18)
            r15.setCounterMaxLength(r16)
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_counterTextAppearance
            r18 = 0
            int r16 = r16.getResourceId(r17, r18)
            r0 = r16
            r15.counterTextAppearance = r0
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_counterOverflowTextAppearance
            r18 = 0
            int r16 = r16.getResourceId(r17, r18)
            r0 = r16
            r15.counterOverflowTextAppearance = r0
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_passwordToggleEnabled
            r18 = 0
            boolean r16 = r16.getBoolean(r17, r18)
            r0 = r16
            r15.passwordToggleEnabled = r0
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_passwordToggleDrawable
            android.graphics.drawable.Drawable r16 = r16.getDrawable(r17)
            r0 = r16
            r15.passwordToggleDrawable = r0
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_passwordToggleContentDescription
            java.lang.CharSequence r16 = r16.getText(r17)
            r0 = r16
            r15.passwordToggleContentDesc = r0
            r15 = r6
            int r16 = android.support.design.C0064R.styleable.TextInputLayout_passwordToggleTint
            boolean r15 = r15.hasValue(r16)
            if (r15 == 0) goto L_0x02cb
            r15 = r2
            r16 = 1
            r0 = r16
            r15.hasPasswordToggleTintList = r0
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_passwordToggleTint
            android.content.res.ColorStateList r16 = r16.getColorStateList(r17)
            r0 = r16
            r15.passwordToggleTintList = r0
        L_0x02cb:
            r15 = r6
            int r16 = android.support.design.C0064R.styleable.TextInputLayout_passwordToggleTintMode
            boolean r15 = r15.hasValue(r16)
            if (r15 == 0) goto L_0x02f0
            r15 = r2
            r16 = 1
            r0 = r16
            r15.hasPasswordToggleTintMode = r0
            r15 = r2
            r16 = r6
            int r17 = android.support.design.C0064R.styleable.TextInputLayout_passwordToggleTintMode
            r18 = -1
            int r16 = r16.getInt(r17, r18)
            r17 = 0
            android.graphics.PorterDuff$Mode r16 = android.support.design.internal.ViewUtils.parseTintMode(r16, r17)
            r0 = r16
            r15.passwordToggleTintMode = r0
        L_0x02f0:
            r15 = r6
            r15.recycle()
            r15 = r2
            r16 = r12
            r15.setHelperTextEnabled(r16)
            r15 = r2
            r16 = r13
            r15.setHelperText(r16)
            r15 = r2
            r16 = r11
            r15.setHelperTextTextAppearance(r16)
            r15 = r2
            r16 = r10
            r15.setErrorEnabled(r16)
            r15 = r2
            r16 = r9
            r15.setErrorTextAppearance(r16)
            r15 = r2
            r16 = r14
            r15.setCounterEnabled(r16)
            r15 = r2
            r15.applyPasswordToggleTint()
            r15 = r2
            r16 = 2
            android.support.p000v4.view.ViewCompat.setImportantForAccessibility(r15, r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.TextInputLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        FrameLayout.LayoutParams layoutParams2;
        View child = view;
        int index = i;
        ViewGroup.LayoutParams params = layoutParams;
        if (child instanceof EditText) {
            new FrameLayout.LayoutParams(params);
            FrameLayout.LayoutParams flp = layoutParams2;
            flp.gravity = 16 | (flp.gravity & -113);
            this.inputFrame.addView(child, flp);
            this.inputFrame.setLayoutParams(params);
            updateInputLayoutMargins();
            setEditText((EditText) child);
            return;
        }
        super.addView(child, index, params);
    }

    @NonNull
    private Drawable getBoxBackground() {
        Throwable th;
        if (this.boxBackgroundMode == 1 || this.boxBackgroundMode == 2) {
            return this.boxBackground;
        }
        Throwable th2 = th;
        new IllegalStateException();
        throw th2;
    }

    public void setBoxBackgroundMode(int i) {
        int boxBackgroundMode2 = i;
        if (boxBackgroundMode2 != this.boxBackgroundMode) {
            this.boxBackgroundMode = boxBackgroundMode2;
            onApplyBoxBackgroundMode();
        }
    }

    private void onApplyBoxBackgroundMode() {
        assignBoxBackgroundByMode();
        if (this.boxBackgroundMode != 0) {
            updateInputLayoutMargins();
        }
        updateTextInputBoxBounds();
    }

    private void assignBoxBackgroundByMode() {
        GradientDrawable gradientDrawable;
        GradientDrawable gradientDrawable2;
        if (this.boxBackgroundMode == 0) {
            this.boxBackground = null;
        } else if (this.boxBackgroundMode == 2 && this.hintEnabled && !(this.boxBackground instanceof CutoutDrawable)) {
            new CutoutDrawable();
            this.boxBackground = gradientDrawable2;
        } else if (!(this.boxBackground instanceof GradientDrawable)) {
            new GradientDrawable();
            this.boxBackground = gradientDrawable;
        }
    }

    public void setBoxStrokeColor(@ColorInt int i) {
        int boxStrokeColor2 = i;
        if (this.focusedStrokeColor != boxStrokeColor2) {
            this.focusedStrokeColor = boxStrokeColor2;
            updateTextInputBoxState();
        }
    }

    public int getBoxStrokeColor() {
        return this.focusedStrokeColor;
    }

    public void setBoxBackgroundColorResource(@ColorRes int boxBackgroundColorId) {
        setBoxBackgroundColor(ContextCompat.getColor(getContext(), boxBackgroundColorId));
    }

    public void setBoxBackgroundColor(@ColorInt int i) {
        int boxBackgroundColor2 = i;
        if (this.boxBackgroundColor != boxBackgroundColor2) {
            this.boxBackgroundColor = boxBackgroundColor2;
            applyBoxAttributes();
        }
    }

    public int getBoxBackgroundColor() {
        return this.boxBackgroundColor;
    }

    public void setBoxCornerRadiiResources(@DimenRes int boxCornerRadiusTopStartId, @DimenRes int boxCornerRadiusTopEndId, @DimenRes int boxCornerRadiusBottomEndId, @DimenRes int boxCornerRadiusBottomStartId) {
        setBoxCornerRadii(getContext().getResources().getDimension(boxCornerRadiusTopStartId), getContext().getResources().getDimension(boxCornerRadiusTopEndId), getContext().getResources().getDimension(boxCornerRadiusBottomEndId), getContext().getResources().getDimension(boxCornerRadiusBottomStartId));
    }

    public void setBoxCornerRadii(float f, float f2, float f3, float f4) {
        float boxCornerRadiusTopStart2 = f;
        float boxCornerRadiusTopEnd2 = f2;
        float boxCornerRadiusBottomStart2 = f3;
        float boxCornerRadiusBottomEnd2 = f4;
        if (this.boxCornerRadiusTopStart != boxCornerRadiusTopStart2 || this.boxCornerRadiusTopEnd != boxCornerRadiusTopEnd2 || this.boxCornerRadiusBottomEnd != boxCornerRadiusBottomEnd2 || this.boxCornerRadiusBottomStart != boxCornerRadiusBottomStart2) {
            this.boxCornerRadiusTopStart = boxCornerRadiusTopStart2;
            this.boxCornerRadiusTopEnd = boxCornerRadiusTopEnd2;
            this.boxCornerRadiusBottomEnd = boxCornerRadiusBottomEnd2;
            this.boxCornerRadiusBottomStart = boxCornerRadiusBottomStart2;
            applyBoxAttributes();
        }
    }

    public float getBoxCornerRadiusTopStart() {
        return this.boxCornerRadiusTopStart;
    }

    public float getBoxCornerRadiusTopEnd() {
        return this.boxCornerRadiusTopEnd;
    }

    public float getBoxCornerRadiusBottomEnd() {
        return this.boxCornerRadiusBottomEnd;
    }

    public float getBoxCornerRadiusBottomStart() {
        return this.boxCornerRadiusBottomStart;
    }

    private float[] getCornerRadiiAsArray() {
        if (!ViewUtils.isLayoutRtl(this)) {
            float[] fArr = new float[8];
            fArr[0] = this.boxCornerRadiusTopStart;
            float[] fArr2 = fArr;
            fArr2[1] = this.boxCornerRadiusTopStart;
            float[] fArr3 = fArr2;
            fArr3[2] = this.boxCornerRadiusTopEnd;
            float[] fArr4 = fArr3;
            fArr4[3] = this.boxCornerRadiusTopEnd;
            float[] fArr5 = fArr4;
            fArr5[4] = this.boxCornerRadiusBottomEnd;
            float[] fArr6 = fArr5;
            fArr6[5] = this.boxCornerRadiusBottomEnd;
            float[] fArr7 = fArr6;
            fArr7[6] = this.boxCornerRadiusBottomStart;
            float[] fArr8 = fArr7;
            fArr8[7] = this.boxCornerRadiusBottomStart;
            return fArr8;
        }
        float[] fArr9 = new float[8];
        fArr9[0] = this.boxCornerRadiusTopEnd;
        float[] fArr10 = fArr9;
        fArr10[1] = this.boxCornerRadiusTopEnd;
        float[] fArr11 = fArr10;
        fArr11[2] = this.boxCornerRadiusTopStart;
        float[] fArr12 = fArr11;
        fArr12[3] = this.boxCornerRadiusTopStart;
        float[] fArr13 = fArr12;
        fArr13[4] = this.boxCornerRadiusBottomStart;
        float[] fArr14 = fArr13;
        fArr14[5] = this.boxCornerRadiusBottomStart;
        float[] fArr15 = fArr14;
        fArr15[6] = this.boxCornerRadiusBottomEnd;
        float[] fArr16 = fArr15;
        fArr16[7] = this.boxCornerRadiusBottomEnd;
        return fArr16;
    }

    public void setTypeface(@Nullable Typeface typeface2) {
        Typeface typeface3 = typeface2;
        if (typeface3 != this.typeface) {
            this.typeface = typeface3;
            this.collapsingTextHelper.setTypefaces(typeface3);
            this.indicatorViewController.setTypefaces(typeface3);
            if (this.counterView != null) {
                this.counterView.setTypeface(typeface3);
            }
        }
    }

    @Nullable
    public Typeface getTypeface() {
        return this.typeface;
    }

    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        ViewStructure structure = viewStructure;
        int flags = i;
        if (this.originalHint == null || this.editText == null) {
            super.dispatchProvideAutofillStructure(structure, flags);
            return;
        }
        boolean wasProvidingHint = this.isProvidingHint;
        this.isProvidingHint = false;
        CharSequence hint2 = this.editText.getHint();
        this.editText.setHint(this.originalHint);
        try {
            super.dispatchProvideAutofillStructure(structure, flags);
            this.editText.setHint(hint2);
            this.isProvidingHint = wasProvidingHint;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.editText.setHint(hint2);
            this.isProvidingHint = wasProvidingHint;
            throw th2;
        }
    }

    private void setEditText(EditText editText2) {
        AccessibilityDelegate accessibilityDelegate;
        TextWatcher textWatcher;
        Throwable th;
        EditText editText3 = editText2;
        if (this.editText != null) {
            Throwable th2 = th;
            new IllegalArgumentException("We already have an EditText, can only have one");
            throw th2;
        }
        if (!(editText3 instanceof TextInputEditText)) {
            int i = Log.i(LOG_TAG, "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.editText = editText3;
        onApplyBoxBackgroundMode();
        new AccessibilityDelegate(this);
        setTextInputAccessibilityDelegate(accessibilityDelegate);
        if (!hasPasswordTransformation()) {
            this.collapsingTextHelper.setTypefaces(this.editText.getTypeface());
        }
        this.collapsingTextHelper.setExpandedTextSize(this.editText.getTextSize());
        int editTextGravity = this.editText.getGravity();
        this.collapsingTextHelper.setCollapsedTextGravity(48 | (editTextGravity & -113));
        this.collapsingTextHelper.setExpandedTextGravity(editTextGravity);
        new TextWatcher(this) {
            final /* synthetic */ TextInputLayout this$0;

            {
                this.this$0 = this$0;
            }

            public void afterTextChanged(Editable editable) {
                Editable s = editable;
                this.this$0.updateLabelState(!this.this$0.restoringSavedState);
                if (this.this$0.counterEnabled) {
                    this.this$0.updateCounter(s.length());
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        };
        this.editText.addTextChangedListener(textWatcher);
        if (this.defaultHintTextColor == null) {
            this.defaultHintTextColor = this.editText.getHintTextColors();
        }
        if (this.hintEnabled) {
            if (TextUtils.isEmpty(this.hint)) {
                this.originalHint = this.editText.getHint();
                setHint(this.originalHint);
                this.editText.setHint((CharSequence) null);
            }
            this.isProvidingHint = true;
        }
        if (this.counterView != null) {
            updateCounter(this.editText.getText().length());
        }
        this.indicatorViewController.adjustIndicatorPadding();
        updatePasswordToggleView();
        updateLabelState(false, true);
    }

    private void updateInputLayoutMargins() {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.inputFrame.getLayoutParams();
        int newTopMargin = calculateLabelMarginTop();
        if (newTopMargin != lp.topMargin) {
            lp.topMargin = newTopMargin;
            this.inputFrame.requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateLabelState(boolean animate) {
        updateLabelState(animate, false);
    }

    private void updateLabelState(boolean z, boolean z2) {
        boolean animate = z;
        boolean force = z2;
        boolean isEnabled = isEnabled();
        boolean hasText = this.editText != null && !TextUtils.isEmpty(this.editText.getText());
        boolean hasFocus = this.editText != null && this.editText.hasFocus();
        boolean errorShouldBeShown = this.indicatorViewController.errorShouldBeShown();
        if (this.defaultHintTextColor != null) {
            this.collapsingTextHelper.setCollapsedTextColor(this.defaultHintTextColor);
            this.collapsingTextHelper.setExpandedTextColor(this.defaultHintTextColor);
        }
        if (!isEnabled) {
            this.collapsingTextHelper.setCollapsedTextColor(ColorStateList.valueOf(this.disabledColor));
            this.collapsingTextHelper.setExpandedTextColor(ColorStateList.valueOf(this.disabledColor));
        } else if (errorShouldBeShown) {
            this.collapsingTextHelper.setCollapsedTextColor(this.indicatorViewController.getErrorViewTextColors());
        } else if (this.counterOverflowed && this.counterView != null) {
            this.collapsingTextHelper.setCollapsedTextColor(this.counterView.getTextColors());
        } else if (hasFocus && this.focusedTextColor != null) {
            this.collapsingTextHelper.setCollapsedTextColor(this.focusedTextColor);
        }
        if (hasText || (isEnabled() && (hasFocus || errorShouldBeShown))) {
            if (force || this.hintExpanded) {
                collapseHint(animate);
            }
        } else if (force || !this.hintExpanded) {
            expandHint(animate);
        }
    }

    @Nullable
    public EditText getEditText() {
        return this.editText;
    }

    public void setHint(@Nullable CharSequence charSequence) {
        CharSequence hint2 = charSequence;
        if (this.hintEnabled) {
            setHintInternal(hint2);
            sendAccessibilityEvent(2048);
        }
    }

    private void setHintInternal(CharSequence charSequence) {
        CharSequence hint2 = charSequence;
        if (!TextUtils.equals(hint2, this.hint)) {
            this.hint = hint2;
            this.collapsingTextHelper.setText(hint2);
            if (!this.hintExpanded) {
                openCutout();
            }
        }
    }

    @Nullable
    public CharSequence getHint() {
        return this.hintEnabled ? this.hint : null;
    }

    public void setHintEnabled(boolean z) {
        boolean enabled = z;
        if (enabled != this.hintEnabled) {
            this.hintEnabled = enabled;
            if (!this.hintEnabled) {
                this.isProvidingHint = false;
                if (!TextUtils.isEmpty(this.hint) && TextUtils.isEmpty(this.editText.getHint())) {
                    this.editText.setHint(this.hint);
                }
                setHintInternal((CharSequence) null);
            } else {
                CharSequence editTextHint = this.editText.getHint();
                if (!TextUtils.isEmpty(editTextHint)) {
                    if (TextUtils.isEmpty(this.hint)) {
                        setHint(editTextHint);
                    }
                    this.editText.setHint((CharSequence) null);
                }
                this.isProvidingHint = true;
            }
            if (this.editText != null) {
                updateInputLayoutMargins();
            }
        }
    }

    public boolean isHintEnabled() {
        return this.hintEnabled;
    }

    /* access modifiers changed from: package-private */
    public boolean isProvidingHint() {
        return this.isProvidingHint;
    }

    public void setHintTextAppearance(@StyleRes int resId) {
        this.collapsingTextHelper.setCollapsedTextAppearance(resId);
        this.focusedTextColor = this.collapsingTextHelper.getCollapsedTextColor();
        if (this.editText != null) {
            updateLabelState(false);
            updateInputLayoutMargins();
        }
    }

    public void setDefaultHintTextColor(@Nullable ColorStateList colorStateList) {
        ColorStateList textColor = colorStateList;
        this.defaultHintTextColor = textColor;
        this.focusedTextColor = textColor;
        if (this.editText != null) {
            updateLabelState(false);
        }
    }

    @Nullable
    public ColorStateList getDefaultHintTextColor() {
        return this.defaultHintTextColor;
    }

    public void setErrorEnabled(boolean enabled) {
        this.indicatorViewController.setErrorEnabled(enabled);
    }

    public void setErrorTextAppearance(@StyleRes int resId) {
        this.indicatorViewController.setErrorTextAppearance(resId);
    }

    public void setErrorTextColor(@Nullable ColorStateList textColors) {
        this.indicatorViewController.setErrorViewTextColor(textColors);
    }

    @ColorInt
    public int getErrorCurrentTextColors() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }

    public void setHelperTextTextAppearance(@StyleRes int resId) {
        this.indicatorViewController.setHelperTextAppearance(resId);
    }

    public boolean isErrorEnabled() {
        return this.indicatorViewController.isErrorEnabled();
    }

    public void setHelperTextEnabled(boolean enabled) {
        this.indicatorViewController.setHelperTextEnabled(enabled);
    }

    public void setHelperText(@Nullable CharSequence charSequence) {
        CharSequence helperText = charSequence;
        if (!TextUtils.isEmpty(helperText)) {
            if (!isHelperTextEnabled()) {
                setHelperTextEnabled(true);
            }
            this.indicatorViewController.showHelper(helperText);
        } else if (isHelperTextEnabled()) {
            setHelperTextEnabled(false);
        }
    }

    public boolean isHelperTextEnabled() {
        return this.indicatorViewController.isHelperTextEnabled();
    }

    public void setHelperTextColor(@Nullable ColorStateList textColors) {
        this.indicatorViewController.setHelperTextViewTextColor(textColors);
    }

    @ColorInt
    public int getHelperTextCurrentTextColor() {
        return this.indicatorViewController.getHelperTextViewCurrentTextColor();
    }

    public void setError(@Nullable CharSequence charSequence) {
        CharSequence errorText = charSequence;
        if (!this.indicatorViewController.isErrorEnabled()) {
            if (!TextUtils.isEmpty(errorText)) {
                setErrorEnabled(true);
            } else {
                return;
            }
        }
        if (!TextUtils.isEmpty(errorText)) {
            this.indicatorViewController.showError(errorText);
        } else {
            this.indicatorViewController.hideError();
        }
    }

    public void setCounterEnabled(boolean z) {
        TextView textView;
        boolean enabled = z;
        if (this.counterEnabled != enabled) {
            if (enabled) {
                new AppCompatTextView(getContext());
                this.counterView = textView;
                this.counterView.setId(C0064R.C0066id.textinput_counter);
                if (this.typeface != null) {
                    this.counterView.setTypeface(this.typeface);
                }
                this.counterView.setMaxLines(1);
                setTextAppearanceCompatWithErrorFallback(this.counterView, this.counterTextAppearance);
                this.indicatorViewController.addIndicator(this.counterView, 2);
                if (this.editText == null) {
                    updateCounter(0);
                } else {
                    updateCounter(this.editText.getText().length());
                }
            } else {
                this.indicatorViewController.removeIndicator(this.counterView, 2);
                this.counterView = null;
            }
            this.counterEnabled = enabled;
        }
    }

    public boolean isCounterEnabled() {
        return this.counterEnabled;
    }

    public void setCounterMaxLength(int i) {
        int maxLength = i;
        if (this.counterMaxLength != maxLength) {
            if (maxLength > 0) {
                this.counterMaxLength = maxLength;
            } else {
                this.counterMaxLength = -1;
            }
            if (this.counterEnabled) {
                updateCounter(this.editText == null ? 0 : this.editText.getText().length());
            }
        }
    }

    public void setEnabled(boolean z) {
        boolean enabled = z;
        recursiveSetEnabled(this, enabled);
        super.setEnabled(enabled);
    }

    private static void recursiveSetEnabled(ViewGroup viewGroup, boolean z) {
        ViewGroup vg = viewGroup;
        boolean enabled = z;
        int count = vg.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = vg.getChildAt(i);
            child.setEnabled(enabled);
            if (child instanceof ViewGroup) {
                recursiveSetEnabled((ViewGroup) child, enabled);
            }
        }
    }

    public int getCounterMaxLength() {
        return this.counterMaxLength;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public CharSequence getCounterOverflowDescription() {
        if (!this.counterEnabled || !this.counterOverflowed || this.counterView == null) {
            return null;
        }
        return this.counterView.getContentDescription();
    }

    /* access modifiers changed from: package-private */
    public void updateCounter(int i) {
        int length = i;
        boolean wasCounterOverflowed = this.counterOverflowed;
        if (this.counterMaxLength == -1) {
            this.counterView.setText(String.valueOf(length));
            this.counterView.setContentDescription((CharSequence) null);
            this.counterOverflowed = false;
        } else {
            if (ViewCompat.getAccessibilityLiveRegion(this.counterView) == 1) {
                ViewCompat.setAccessibilityLiveRegion(this.counterView, 0);
            }
            this.counterOverflowed = length > this.counterMaxLength;
            if (wasCounterOverflowed != this.counterOverflowed) {
                setTextAppearanceCompatWithErrorFallback(this.counterView, this.counterOverflowed ? this.counterOverflowTextAppearance : this.counterTextAppearance);
                if (this.counterOverflowed) {
                    ViewCompat.setAccessibilityLiveRegion(this.counterView, 1);
                }
            }
            TextView textView = this.counterView;
            Context context = getContext();
            int i2 = C0064R.string.character_counter_pattern;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(length);
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(this.counterMaxLength);
            textView.setText(context.getString(i2, objArr2));
            TextView textView2 = this.counterView;
            Context context2 = getContext();
            int i3 = C0064R.string.character_counter_content_description;
            Object[] objArr3 = new Object[2];
            objArr3[0] = Integer.valueOf(length);
            Object[] objArr4 = objArr3;
            objArr4[1] = Integer.valueOf(this.counterMaxLength);
            textView2.setContentDescription(context2.getString(i3, objArr4));
        }
        if (this.editText != null && wasCounterOverflowed != this.counterOverflowed) {
            updateLabelState(false);
            updateTextInputBoxState();
            updateEditTextBackground();
        }
    }

    /* access modifiers changed from: package-private */
    public void setTextAppearanceCompatWithErrorFallback(TextView textView, @StyleRes int textAppearance) {
        TextView textView2 = textView;
        boolean useDefaultColor = false;
        try {
            TextViewCompat.setTextAppearance(textView2, textAppearance);
            if (Build.VERSION.SDK_INT >= 23 && textView2.getTextColors().getDefaultColor() == -65281) {
                useDefaultColor = true;
            }
        } catch (Exception e) {
            Exception exc = e;
            useDefaultColor = true;
        }
        if (useDefaultColor) {
            TextViewCompat.setTextAppearance(textView2, C0064R.C0068style.TextAppearance_AppCompat_Caption);
            textView2.setTextColor(ContextCompat.getColor(getContext(), C0064R.color.design_error));
        }
    }

    private void updateTextInputBoxBounds() {
        if (this.boxBackgroundMode != 0 && this.boxBackground != null && this.editText != null && getRight() != 0) {
            int left = this.editText.getLeft();
            int top = calculateBoxBackgroundTop();
            int right = this.editText.getRight();
            int bottom = this.editText.getBottom() + this.boxBottomOffsetPx;
            if (this.boxBackgroundMode == 2) {
                left += this.boxStrokeWidthFocusedPx / 2;
                top -= this.boxStrokeWidthFocusedPx / 2;
                right -= this.boxStrokeWidthFocusedPx / 2;
                bottom += this.boxStrokeWidthFocusedPx / 2;
            }
            this.boxBackground.setBounds(left, top, right, bottom);
            applyBoxAttributes();
            updateEditTextBackgroundBounds();
        }
    }

    private int calculateBoxBackgroundTop() {
        if (this.editText == null) {
            return 0;
        }
        switch (this.boxBackgroundMode) {
            case 1:
                return this.editText.getTop();
            case 2:
                return this.editText.getTop() + calculateLabelMarginTop();
            default:
                return 0;
        }
    }

    private int calculateLabelMarginTop() {
        if (!this.hintEnabled) {
            return 0;
        }
        switch (this.boxBackgroundMode) {
            case 0:
            case 1:
                return (int) this.collapsingTextHelper.getCollapsedTextHeight();
            case 2:
                return (int) (this.collapsingTextHelper.getCollapsedTextHeight() / 2.0f);
            default:
                return 0;
        }
    }

    private int calculateCollapsedTextTopBounds() {
        switch (this.boxBackgroundMode) {
            case 1:
                return getBoxBackground().getBounds().top + this.boxCollapsedPaddingTopPx;
            case 2:
                return getBoxBackground().getBounds().top - calculateLabelMarginTop();
            default:
                return getPaddingTop();
        }
    }

    private void updateEditTextBackgroundBounds() {
        Rect editTextBounds;
        Rect rect;
        if (this.editText != null) {
            Drawable editTextBackground = this.editText.getBackground();
            if (editTextBackground != null) {
                if (DrawableUtils.canSafelyMutateDrawable(editTextBackground)) {
                    editTextBackground = editTextBackground.mutate();
                }
                new Rect();
                DescendantOffsetUtils.getDescendantRect(this, this.editText, editTextBounds);
                Rect editTextBackgroundBounds = editTextBackground.getBounds();
                if (editTextBackgroundBounds.left != editTextBackgroundBounds.right) {
                    new Rect();
                    Rect editTextBackgroundPadding = rect;
                    boolean padding = editTextBackground.getPadding(editTextBackgroundPadding);
                    editTextBackground.setBounds(editTextBackgroundBounds.left - editTextBackgroundPadding.left, editTextBackgroundBounds.top, editTextBackgroundBounds.right + (editTextBackgroundPadding.right * 2), this.editText.getBottom());
                }
            }
        }
    }

    private void setBoxAttributes() {
        switch (this.boxBackgroundMode) {
            case 1:
                this.boxStrokeWidthPx = 0;
                return;
            case 2:
                if (this.focusedStrokeColor == 0) {
                    this.focusedStrokeColor = this.focusedTextColor.getColorForState(getDrawableState(), this.focusedTextColor.getDefaultColor());
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void applyBoxAttributes() {
        if (this.boxBackground != null) {
            setBoxAttributes();
            if (this.editText != null && this.boxBackgroundMode == 2) {
                if (this.editText.getBackground() != null) {
                    this.editTextOriginalDrawable = this.editText.getBackground();
                }
                ViewCompat.setBackground(this.editText, (Drawable) null);
            }
            if (!(this.editText == null || this.boxBackgroundMode != 1 || this.editTextOriginalDrawable == null)) {
                ViewCompat.setBackground(this.editText, this.editTextOriginalDrawable);
            }
            if (this.boxStrokeWidthPx > -1 && this.boxStrokeColor != 0) {
                this.boxBackground.setStroke(this.boxStrokeWidthPx, this.boxStrokeColor);
            }
            this.boxBackground.setCornerRadii(getCornerRadiiAsArray());
            this.boxBackground.setColor(this.boxBackgroundColor);
            invalidate();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateEditTextBackground() {
        if (this.editText != null) {
            Drawable editTextBackground = this.editText.getBackground();
            if (editTextBackground != null) {
                ensureBackgroundDrawableStateWorkaround();
                if (DrawableUtils.canSafelyMutateDrawable(editTextBackground)) {
                    editTextBackground = editTextBackground.mutate();
                }
                if (this.indicatorViewController.errorShouldBeShown()) {
                    editTextBackground.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(this.indicatorViewController.getErrorViewCurrentTextColor(), PorterDuff.Mode.SRC_IN));
                } else if (!this.counterOverflowed || this.counterView == null) {
                    DrawableCompat.clearColorFilter(editTextBackground);
                    this.editText.refreshDrawableState();
                } else {
                    editTextBackground.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(this.counterView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
                }
            }
        }
    }

    private void ensureBackgroundDrawableStateWorkaround() {
        Drawable bg;
        int sdk = Build.VERSION.SDK_INT;
        if ((sdk == 21 || sdk == 22) && (bg = this.editText.getBackground()) != null && !this.hasReconstructedEditTextBackground) {
            Drawable newBg = bg.getConstantState().newDrawable();
            if (bg instanceof DrawableContainer) {
                this.hasReconstructedEditTextBackground = DrawableUtils.setContainerConstantState((DrawableContainer) bg, newBg.getConstantState());
            }
            if (!this.hasReconstructedEditTextBackground) {
                ViewCompat.setBackground(this.editText, newBg);
                this.hasReconstructedEditTextBackground = true;
                onApplyBoxBackgroundMode();
            }
        }
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR;
        CharSequence error;
        boolean isPasswordToggledVisible;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        SavedState(Parcelable superState) {
            super(superState);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        SavedState(android.os.Parcel r7, java.lang.ClassLoader r8) {
            /*
                r6 = this;
                r0 = r6
                r1 = r7
                r2 = r8
                r3 = r0
                r4 = r1
                r5 = r2
                r3.<init>(r4, r5)
                r3 = r0
                android.os.Parcelable$Creator r4 = android.text.TextUtils.CHAR_SEQUENCE_CREATOR
                r5 = r1
                java.lang.Object r4 = r4.createFromParcel(r5)
                java.lang.CharSequence r4 = (java.lang.CharSequence) r4
                r3.error = r4
                r3 = r0
                r4 = r1
                int r4 = r4.readInt()
                r5 = 1
                if (r4 != r5) goto L_0x0022
                r4 = 1
            L_0x001f:
                r3.isPasswordToggledVisible = r4
                return
            L_0x0022:
                r4 = 0
                goto L_0x001f
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.TextInputLayout.SavedState.<init>(android.os.Parcel, java.lang.ClassLoader):void");
        }

        public void writeToParcel(Parcel parcel, int i) {
            Parcel dest = parcel;
            int flags = i;
            super.writeToParcel(dest, flags);
            TextUtils.writeToParcel(this.error, dest, flags);
            dest.writeInt(this.isPasswordToggledVisible ? 1 : 0);
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append("TextInputLayout.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" error=").append(this.error).append("}").toString();
        }

        static {
            Parcelable.Creator<SavedState> creator;
            new Parcelable.ClassLoaderCreator<SavedState>() {
                public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                    SavedState savedState;
                    new SavedState(in, loader);
                    return savedState;
                }

                public SavedState createFromParcel(Parcel in) {
                    SavedState savedState;
                    new SavedState(in, (ClassLoader) null);
                    return savedState;
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
            CREATOR = creator;
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        new SavedState(super.onSaveInstanceState());
        SavedState ss = savedState;
        if (this.indicatorViewController.errorShouldBeShown()) {
            ss.error = getError();
        }
        ss.isPasswordToggledVisible = this.passwordToggledVisible;
        return ss;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable state = parcelable;
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setError(ss.error);
        if (ss.isPasswordToggledVisible) {
            passwordVisibilityToggleRequested(true);
        }
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        this.restoringSavedState = true;
        super.dispatchRestoreInstanceState(container);
        this.restoringSavedState = false;
    }

    @Nullable
    public CharSequence getError() {
        return this.indicatorViewController.isErrorEnabled() ? this.indicatorViewController.getErrorText() : null;
    }

    @Nullable
    public CharSequence getHelperText() {
        return this.indicatorViewController.isHelperTextEnabled() ? this.indicatorViewController.getHelperText() : null;
    }

    public boolean isHintAnimationEnabled() {
        return this.hintAnimationEnabled;
    }

    public void setHintAnimationEnabled(boolean enabled) {
        boolean z = enabled;
        this.hintAnimationEnabled = z;
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        if (this.boxBackground != null) {
            this.boxBackground.draw(canvas2);
        }
        super.draw(canvas2);
        if (this.hintEnabled) {
            this.collapsingTextHelper.draw(canvas2);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        updatePasswordToggleView();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void updatePasswordToggleView() {
        Drawable drawable;
        View.OnClickListener onClickListener;
        if (this.editText != null) {
            if (shouldShowPasswordIcon()) {
                if (this.passwordToggleView == null) {
                    this.passwordToggleView = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(C0064R.layout.design_text_input_password_icon, this.inputFrame, false);
                    this.passwordToggleView.setImageDrawable(this.passwordToggleDrawable);
                    this.passwordToggleView.setContentDescription(this.passwordToggleContentDesc);
                    this.inputFrame.addView(this.passwordToggleView);
                    new View.OnClickListener(this) {
                        final /* synthetic */ TextInputLayout this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public void onClick(View view) {
                            View view2 = view;
                            this.this$0.passwordVisibilityToggleRequested(false);
                        }
                    };
                    this.passwordToggleView.setOnClickListener(onClickListener);
                }
                if (this.editText != null && ViewCompat.getMinimumHeight(this.editText) <= 0) {
                    this.editText.setMinimumHeight(ViewCompat.getMinimumHeight(this.passwordToggleView));
                }
                this.passwordToggleView.setVisibility(0);
                this.passwordToggleView.setChecked(this.passwordToggledVisible);
                if (this.passwordToggleDummyDrawable == null) {
                    new ColorDrawable();
                    this.passwordToggleDummyDrawable = drawable;
                }
                this.passwordToggleDummyDrawable.setBounds(0, 0, this.passwordToggleView.getMeasuredWidth(), 1);
                Drawable[] compounds = TextViewCompat.getCompoundDrawablesRelative(this.editText);
                if (compounds[2] != this.passwordToggleDummyDrawable) {
                    this.originalEditTextEndDrawable = compounds[2];
                }
                TextViewCompat.setCompoundDrawablesRelative(this.editText, compounds[0], compounds[1], this.passwordToggleDummyDrawable, compounds[3]);
                this.passwordToggleView.setPadding(this.editText.getPaddingLeft(), this.editText.getPaddingTop(), this.editText.getPaddingRight(), this.editText.getPaddingBottom());
                return;
            }
            if (this.passwordToggleView != null && this.passwordToggleView.getVisibility() == 0) {
                this.passwordToggleView.setVisibility(8);
            }
            if (this.passwordToggleDummyDrawable != null) {
                Drawable[] compounds2 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
                if (compounds2[2] == this.passwordToggleDummyDrawable) {
                    TextViewCompat.setCompoundDrawablesRelative(this.editText, compounds2[0], compounds2[1], this.originalEditTextEndDrawable, compounds2[3]);
                    this.passwordToggleDummyDrawable = null;
                }
            }
        }
    }

    public void setPasswordVisibilityToggleDrawable(@DrawableRes int i) {
        int resId = i;
        setPasswordVisibilityToggleDrawable(resId != 0 ? AppCompatResources.getDrawable(getContext(), resId) : null);
    }

    public void setPasswordVisibilityToggleDrawable(@Nullable Drawable drawable) {
        Drawable icon = drawable;
        this.passwordToggleDrawable = icon;
        if (this.passwordToggleView != null) {
            this.passwordToggleView.setImageDrawable(icon);
        }
    }

    public void setPasswordVisibilityToggleContentDescription(@StringRes int i) {
        int resId = i;
        setPasswordVisibilityToggleContentDescription(resId != 0 ? getResources().getText(resId) : null);
    }

    public void setPasswordVisibilityToggleContentDescription(@Nullable CharSequence charSequence) {
        CharSequence description = charSequence;
        this.passwordToggleContentDesc = description;
        if (this.passwordToggleView != null) {
            this.passwordToggleView.setContentDescription(description);
        }
    }

    @Nullable
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.passwordToggleDrawable;
    }

    @Nullable
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.passwordToggleContentDesc;
    }

    public boolean isPasswordVisibilityToggleEnabled() {
        return this.passwordToggleEnabled;
    }

    public void setPasswordVisibilityToggleEnabled(boolean z) {
        boolean enabled = z;
        if (this.passwordToggleEnabled != enabled) {
            this.passwordToggleEnabled = enabled;
            if (!enabled && this.passwordToggledVisible && this.editText != null) {
                this.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            this.passwordToggledVisible = false;
            updatePasswordToggleView();
        }
    }

    public void setPasswordVisibilityToggleTintList(@Nullable ColorStateList tintList) {
        this.passwordToggleTintList = tintList;
        this.hasPasswordToggleTintList = true;
        applyPasswordToggleTint();
    }

    public void setPasswordVisibilityToggleTintMode(@Nullable PorterDuff.Mode mode) {
        this.passwordToggleTintMode = mode;
        this.hasPasswordToggleTintMode = true;
        applyPasswordToggleTint();
    }

    public void passwordVisibilityToggleRequested(boolean z) {
        boolean shouldSkipAnimations = z;
        if (this.passwordToggleEnabled) {
            int selection = this.editText.getSelectionEnd();
            if (hasPasswordTransformation()) {
                this.editText.setTransformationMethod((TransformationMethod) null);
                this.passwordToggledVisible = true;
            } else {
                this.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                this.passwordToggledVisible = false;
            }
            this.passwordToggleView.setChecked(this.passwordToggledVisible);
            if (shouldSkipAnimations) {
                this.passwordToggleView.jumpDrawablesToCurrentState();
            }
            this.editText.setSelection(selection);
        }
    }

    public void setTextInputAccessibilityDelegate(AccessibilityDelegate accessibilityDelegate) {
        AccessibilityDelegate delegate = accessibilityDelegate;
        if (this.editText != null) {
            ViewCompat.setAccessibilityDelegate(this.editText, delegate);
        }
    }

    private boolean hasPasswordTransformation() {
        return this.editText != null && (this.editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    private boolean shouldShowPasswordIcon() {
        return this.passwordToggleEnabled && (hasPasswordTransformation() || this.passwordToggledVisible);
    }

    private void applyPasswordToggleTint() {
        if (this.passwordToggleDrawable == null) {
            return;
        }
        if (this.hasPasswordToggleTintList || this.hasPasswordToggleTintMode) {
            this.passwordToggleDrawable = DrawableCompat.wrap(this.passwordToggleDrawable).mutate();
            if (this.hasPasswordToggleTintList) {
                DrawableCompat.setTintList(this.passwordToggleDrawable, this.passwordToggleTintList);
            }
            if (this.hasPasswordToggleTintMode) {
                DrawableCompat.setTintMode(this.passwordToggleDrawable, this.passwordToggleTintMode);
            }
            if (this.passwordToggleView != null && this.passwordToggleView.getDrawable() != this.passwordToggleDrawable) {
                this.passwordToggleView.setImageDrawable(this.passwordToggleDrawable);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int i, int right, int i2) {
        int top = i;
        int bottom = i2;
        super.onLayout(changed, left, top, right, bottom);
        if (this.boxBackground != null) {
            updateTextInputBoxBounds();
        }
        if (this.hintEnabled && this.editText != null) {
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(this, this.editText, rect);
            int l = rect.left + this.editText.getCompoundPaddingLeft();
            int r = rect.right - this.editText.getCompoundPaddingRight();
            int t = calculateCollapsedTextTopBounds();
            this.collapsingTextHelper.setExpandedBounds(l, rect.top + this.editText.getCompoundPaddingTop(), r, rect.bottom - this.editText.getCompoundPaddingBottom());
            this.collapsingTextHelper.setCollapsedBounds(l, t, r, (bottom - top) - getPaddingBottom());
            this.collapsingTextHelper.recalculate();
            if (cutoutEnabled() && !this.hintExpanded) {
                openCutout();
            }
        }
    }

    private void collapseHint(boolean z) {
        boolean animate = z;
        if (this.animator != null && this.animator.isRunning()) {
            this.animator.cancel();
        }
        if (!animate || !this.hintAnimationEnabled) {
            this.collapsingTextHelper.setExpansionFraction(1.0f);
        } else {
            animateToExpansionFraction(1.0f);
        }
        this.hintExpanded = false;
        if (cutoutEnabled()) {
            openCutout();
        }
    }

    private boolean cutoutEnabled() {
        return this.hintEnabled && !TextUtils.isEmpty(this.hint) && (this.boxBackground instanceof CutoutDrawable);
    }

    private void openCutout() {
        if (cutoutEnabled()) {
            RectF cutoutBounds = this.tmpRectF;
            this.collapsingTextHelper.getCollapsedTextActualBounds(cutoutBounds);
            applyCutoutPadding(cutoutBounds);
            ((CutoutDrawable) this.boxBackground).setCutout(cutoutBounds);
        }
    }

    private void closeCutout() {
        if (cutoutEnabled()) {
            ((CutoutDrawable) this.boxBackground).removeCutout();
        }
    }

    private void applyCutoutPadding(RectF rectF) {
        RectF cutoutBounds = rectF;
        cutoutBounds.left -= (float) this.boxLabelCutoutPaddingPx;
        cutoutBounds.top -= (float) this.boxLabelCutoutPaddingPx;
        cutoutBounds.right += (float) this.boxLabelCutoutPaddingPx;
        cutoutBounds.bottom += (float) this.boxLabelCutoutPaddingPx;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean cutoutIsOpen() {
        return cutoutEnabled() && ((CutoutDrawable) this.boxBackground).hasCutout();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        if (!this.inDrawableStateChanged) {
            this.inDrawableStateChanged = true;
            super.drawableStateChanged();
            int[] state = getDrawableState();
            boolean changed = false;
            updateLabelState(ViewCompat.isLaidOut(this) && isEnabled());
            updateEditTextBackground();
            updateTextInputBoxBounds();
            updateTextInputBoxState();
            if (this.collapsingTextHelper != null) {
                changed = false | this.collapsingTextHelper.setState(state);
            }
            if (changed) {
                invalidate();
            }
            this.inDrawableStateChanged = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateTextInputBoxState() {
        if (this.boxBackground != null && this.boxBackgroundMode != 0) {
            boolean hasFocus = this.editText != null && this.editText.hasFocus();
            boolean isHovered = this.editText != null && this.editText.isHovered();
            if (this.boxBackgroundMode == 2) {
                if (!isEnabled()) {
                    this.boxStrokeColor = this.disabledColor;
                } else if (this.indicatorViewController.errorShouldBeShown()) {
                    this.boxStrokeColor = this.indicatorViewController.getErrorViewCurrentTextColor();
                } else if (this.counterOverflowed && this.counterView != null) {
                    this.boxStrokeColor = this.counterView.getCurrentTextColor();
                } else if (hasFocus) {
                    this.boxStrokeColor = this.focusedStrokeColor;
                } else if (isHovered) {
                    this.boxStrokeColor = this.hoveredStrokeColor;
                } else {
                    this.boxStrokeColor = this.defaultStrokeColor;
                }
                if ((isHovered || hasFocus) && isEnabled()) {
                    this.boxStrokeWidthPx = this.boxStrokeWidthFocusedPx;
                } else {
                    this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
                }
                applyBoxAttributes();
            }
        }
    }

    private void expandHint(boolean z) {
        boolean animate = z;
        if (this.animator != null && this.animator.isRunning()) {
            this.animator.cancel();
        }
        if (!animate || !this.hintAnimationEnabled) {
            this.collapsingTextHelper.setExpansionFraction(0.0f);
        } else {
            animateToExpansionFraction(0.0f);
        }
        if (cutoutEnabled() && ((CutoutDrawable) this.boxBackground).hasCutout()) {
            closeCutout();
        }
        this.hintExpanded = true;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void animateToExpansionFraction(float f) {
        ValueAnimator valueAnimator;
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
        float target = f;
        if (this.collapsingTextHelper.getExpansionFraction() != target) {
            if (this.animator == null) {
                new ValueAnimator();
                this.animator = valueAnimator;
                this.animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                ValueAnimator duration = this.animator.setDuration(167);
                new ValueAnimator.AnimatorUpdateListener(this) {
                    final /* synthetic */ TextInputLayout this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onAnimationUpdate(ValueAnimator animator) {
                        this.this$0.collapsingTextHelper.setExpansionFraction(((Float) animator.getAnimatedValue()).floatValue());
                    }
                };
                this.animator.addUpdateListener(animatorUpdateListener);
            }
            ValueAnimator valueAnimator2 = this.animator;
            float[] fArr = new float[2];
            fArr[0] = this.collapsingTextHelper.getExpansionFraction();
            float[] fArr2 = fArr;
            fArr2[1] = target;
            valueAnimator2.setFloatValues(fArr2);
            this.animator.start();
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final boolean isHintExpanded() {
        return this.hintExpanded;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final boolean isHelperTextDisplayed() {
        return this.indicatorViewController.helperTextIsDisplayed();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final int getHintCurrentCollapsedTextColor() {
        return this.collapsingTextHelper.getCurrentCollapsedTextColor();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final float getHintCollapsedTextHeight() {
        return this.collapsingTextHelper.getCollapsedTextHeight();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final int getErrorTextCurrentColor() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }

    public static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final TextInputLayout layout;

        public AccessibilityDelegate(TextInputLayout layout2) {
            this.layout = layout2;
        }

        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            AccessibilityNodeInfoCompat info = accessibilityNodeInfoCompat;
            super.onInitializeAccessibilityNodeInfo(host, info);
            EditText editText = this.layout.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            CharSequence hintText = this.layout.getHint();
            CharSequence errorText = this.layout.getError();
            CharSequence counterDesc = this.layout.getCounterOverflowDescription();
            boolean showingText = !TextUtils.isEmpty(text);
            boolean hasHint = !TextUtils.isEmpty(hintText);
            boolean showingError = !TextUtils.isEmpty(errorText);
            boolean contentInvalid = showingError || !TextUtils.isEmpty(counterDesc);
            if (showingText) {
                info.setText(text);
            } else if (hasHint) {
                info.setText(hintText);
            }
            if (hasHint) {
                info.setHintText(hintText);
                info.setShowingHintText(!showingText && hasHint);
            }
            if (contentInvalid) {
                info.setError(showingError ? errorText : counterDesc);
                info.setContentInvalid(true);
            }
        }

        public void onPopulateAccessibilityEvent(View host, AccessibilityEvent accessibilityEvent) {
            AccessibilityEvent event = accessibilityEvent;
            super.onPopulateAccessibilityEvent(host, event);
            EditText editText = this.layout.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            CharSequence eventText = TextUtils.isEmpty(text) ? this.layout.getHint() : text;
            if (!TextUtils.isEmpty(eventText)) {
                boolean add = event.getText().add(eventText);
            }
        }
    }
}
