package android.support.p003v7.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.widget.TextViewCompat;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.content.res.AppCompatResources;
import android.support.p003v7.text.AllCapsTransformationMethod;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;

/* renamed from: android.support.v7.widget.SwitchCompat */
public class SwitchCompat extends CompoundButton {
    private static final String ACCESSIBILITY_EVENT_CLASS_NAME = "android.widget.Switch";
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int THUMB_ANIMATION_DURATION = 250;
    private static final Property<SwitchCompat, Float> THUMB_POS;
    private static final int TOUCH_MODE_DOWN = 1;
    private static final int TOUCH_MODE_DRAGGING = 2;
    private static final int TOUCH_MODE_IDLE = 0;
    private boolean mHasThumbTint;
    private boolean mHasThumbTintMode;
    private boolean mHasTrackTint;
    private boolean mHasTrackTintMode;
    private int mMinFlingVelocity;
    private Layout mOffLayout;
    private Layout mOnLayout;
    ObjectAnimator mPositionAnimator;
    private boolean mShowText;
    private boolean mSplitTrack;
    private int mSwitchBottom;
    private int mSwitchHeight;
    private int mSwitchLeft;
    private int mSwitchMinWidth;
    private int mSwitchPadding;
    private int mSwitchRight;
    private int mSwitchTop;
    private TransformationMethod mSwitchTransformationMethod;
    private int mSwitchWidth;
    private final Rect mTempRect;
    private ColorStateList mTextColors;
    private CharSequence mTextOff;
    private CharSequence mTextOn;
    private final TextPaint mTextPaint;
    private Drawable mThumbDrawable;
    float mThumbPosition;
    private int mThumbTextPadding;
    private ColorStateList mThumbTintList;
    private PorterDuff.Mode mThumbTintMode;
    private int mThumbWidth;
    private int mTouchMode;
    private int mTouchSlop;
    private float mTouchX;
    private float mTouchY;
    private Drawable mTrackDrawable;
    private ColorStateList mTrackTintList;
    private PorterDuff.Mode mTrackTintMode;
    private VelocityTracker mVelocityTracker;

    static {
        Property<SwitchCompat, Float> property;
        new Property<SwitchCompat, Float>(Float.class, "thumbPos") {
            public Float get(SwitchCompat object) {
                return Float.valueOf(object.mThumbPosition);
            }

            public void set(SwitchCompat object, Float value) {
                object.setThumbPosition(value.floatValue());
            }
        };
        THUMB_POS = property;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SwitchCompat(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SwitchCompat(Context context, AttributeSet attrs) {
        this(context, attrs, C0425R.attr.switchStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SwitchCompat(android.content.Context r19, android.util.AttributeSet r20, int r21) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r12 = r0
            r13 = r1
            r14 = r2
            r15 = r3
            r12.<init>(r13, r14, r15)
            r12 = r0
            r13 = 0
            r12.mThumbTintList = r13
            r12 = r0
            r13 = 0
            r12.mThumbTintMode = r13
            r12 = r0
            r13 = 0
            r12.mHasThumbTint = r13
            r12 = r0
            r13 = 0
            r12.mHasThumbTintMode = r13
            r12 = r0
            r13 = 0
            r12.mTrackTintList = r13
            r12 = r0
            r13 = 0
            r12.mTrackTintMode = r13
            r12 = r0
            r13 = 0
            r12.mHasTrackTint = r13
            r12 = r0
            r13 = 0
            r12.mHasTrackTintMode = r13
            r12 = r0
            android.view.VelocityTracker r13 = android.view.VelocityTracker.obtain()
            r12.mVelocityTracker = r13
            r12 = r0
            android.graphics.Rect r13 = new android.graphics.Rect
            r17 = r13
            r13 = r17
            r14 = r17
            r14.<init>()
            r12.mTempRect = r13
            r12 = r0
            android.text.TextPaint r13 = new android.text.TextPaint
            r17 = r13
            r13 = r17
            r14 = r17
            r15 = 1
            r14.<init>(r15)
            r12.mTextPaint = r13
            r12 = r0
            android.content.res.Resources r12 = r12.getResources()
            r4 = r12
            r12 = r0
            android.text.TextPaint r12 = r12.mTextPaint
            r13 = r4
            android.util.DisplayMetrics r13 = r13.getDisplayMetrics()
            float r13 = r13.density
            r12.density = r13
            r12 = r1
            r13 = r2
            int[] r14 = android.support.p003v7.appcompat.C0425R.styleable.SwitchCompat
            r15 = r3
            r16 = 0
            android.support.v7.widget.TintTypedArray r12 = android.support.p003v7.widget.TintTypedArray.obtainStyledAttributes(r12, r13, r14, r15, r16)
            r5 = r12
            r12 = r0
            r13 = r5
            int r14 = android.support.p003v7.appcompat.C0425R.styleable.SwitchCompat_android_thumb
            android.graphics.drawable.Drawable r13 = r13.getDrawable(r14)
            r12.mThumbDrawable = r13
            r12 = r0
            android.graphics.drawable.Drawable r12 = r12.mThumbDrawable
            if (r12 == 0) goto L_0x0087
            r12 = r0
            android.graphics.drawable.Drawable r12 = r12.mThumbDrawable
            r13 = r0
            r12.setCallback(r13)
        L_0x0087:
            r12 = r0
            r13 = r5
            int r14 = android.support.p003v7.appcompat.C0425R.styleable.SwitchCompat_track
            android.graphics.drawable.Drawable r13 = r13.getDrawable(r14)
            r12.mTrackDrawable = r13
            r12 = r0
            android.graphics.drawable.Drawable r12 = r12.mTrackDrawable
            if (r12 == 0) goto L_0x009d
            r12 = r0
            android.graphics.drawable.Drawable r12 = r12.mTrackDrawable
            r13 = r0
            r12.setCallback(r13)
        L_0x009d:
            r12 = r0
            r13 = r5
            int r14 = android.support.p003v7.appcompat.C0425R.styleable.SwitchCompat_android_textOn
            java.lang.CharSequence r13 = r13.getText(r14)
            r12.mTextOn = r13
            r12 = r0
            r13 = r5
            int r14 = android.support.p003v7.appcompat.C0425R.styleable.SwitchCompat_android_textOff
            java.lang.CharSequence r13 = r13.getText(r14)
            r12.mTextOff = r13
            r12 = r0
            r13 = r5
            int r14 = android.support.p003v7.appcompat.C0425R.styleable.SwitchCompat_showText
            r15 = 1
            boolean r13 = r13.getBoolean(r14, r15)
            r12.mShowText = r13
            r12 = r0
            r13 = r5
            int r14 = android.support.p003v7.appcompat.C0425R.styleable.SwitchCompat_thumbTextPadding
            r15 = 0
            int r13 = r13.getDimensionPixelSize(r14, r15)
            r12.mThumbTextPadding = r13
            r12 = r0
            r13 = r5
            int r14 = android.support.p003v7.appcompat.C0425R.styleable.SwitchCompat_switchMinWidth
            r15 = 0
            int r13 = r13.getDimensionPixelSize(r14, r15)
            r12.mSwitchMinWidth = r13
            r12 = r0
            r13 = r5
            int r14 = android.support.p003v7.appcompat.C0425R.styleable.SwitchCompat_switchPadding
            r15 = 0
            int r13 = r13.getDimensionPixelSize(r14, r15)
            r12.mSwitchPadding = r13
            r12 = r0
            r13 = r5
            int r14 = android.support.p003v7.appcompat.C0425R.styleable.SwitchCompat_splitTrack
            r15 = 0
            boolean r13 = r13.getBoolean(r14, r15)
            r12.mSplitTrack = r13
            r12 = r5
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SwitchCompat_thumbTint
            android.content.res.ColorStateList r12 = r12.getColorStateList(r13)
            r6 = r12
            r12 = r6
            if (r12 == 0) goto L_0x00fb
            r12 = r0
            r13 = r6
            r12.mThumbTintList = r13
            r12 = r0
            r13 = 1
            r12.mHasThumbTint = r13
        L_0x00fb:
            r12 = r5
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SwitchCompat_thumbTintMode
            r14 = -1
            int r12 = r12.getInt(r13, r14)
            r13 = 0
            android.graphics.PorterDuff$Mode r12 = android.support.p003v7.widget.DrawableUtils.parseTintMode(r12, r13)
            r7 = r12
            r12 = r0
            android.graphics.PorterDuff$Mode r12 = r12.mThumbTintMode
            r13 = r7
            if (r12 == r13) goto L_0x0117
            r12 = r0
            r13 = r7
            r12.mThumbTintMode = r13
            r12 = r0
            r13 = 1
            r12.mHasThumbTintMode = r13
        L_0x0117:
            r12 = r0
            boolean r12 = r12.mHasThumbTint
            if (r12 != 0) goto L_0x0121
            r12 = r0
            boolean r12 = r12.mHasThumbTintMode
            if (r12 == 0) goto L_0x0125
        L_0x0121:
            r12 = r0
            r12.applyThumbTint()
        L_0x0125:
            r12 = r5
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SwitchCompat_trackTint
            android.content.res.ColorStateList r12 = r12.getColorStateList(r13)
            r8 = r12
            r12 = r8
            if (r12 == 0) goto L_0x0138
            r12 = r0
            r13 = r8
            r12.mTrackTintList = r13
            r12 = r0
            r13 = 1
            r12.mHasTrackTint = r13
        L_0x0138:
            r12 = r5
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SwitchCompat_trackTintMode
            r14 = -1
            int r12 = r12.getInt(r13, r14)
            r13 = 0
            android.graphics.PorterDuff$Mode r12 = android.support.p003v7.widget.DrawableUtils.parseTintMode(r12, r13)
            r9 = r12
            r12 = r0
            android.graphics.PorterDuff$Mode r12 = r12.mTrackTintMode
            r13 = r9
            if (r12 == r13) goto L_0x0154
            r12 = r0
            r13 = r9
            r12.mTrackTintMode = r13
            r12 = r0
            r13 = 1
            r12.mHasTrackTintMode = r13
        L_0x0154:
            r12 = r0
            boolean r12 = r12.mHasTrackTint
            if (r12 != 0) goto L_0x015e
            r12 = r0
            boolean r12 = r12.mHasTrackTintMode
            if (r12 == 0) goto L_0x0162
        L_0x015e:
            r12 = r0
            r12.applyTrackTint()
        L_0x0162:
            r12 = r5
            int r13 = android.support.p003v7.appcompat.C0425R.styleable.SwitchCompat_switchTextAppearance
            r14 = 0
            int r12 = r12.getResourceId(r13, r14)
            r10 = r12
            r12 = r10
            if (r12 == 0) goto L_0x0174
            r12 = r0
            r13 = r1
            r14 = r10
            r12.setSwitchTextAppearance(r13, r14)
        L_0x0174:
            r12 = r5
            r12.recycle()
            r12 = r1
            android.view.ViewConfiguration r12 = android.view.ViewConfiguration.get(r12)
            r11 = r12
            r12 = r0
            r13 = r11
            int r13 = r13.getScaledTouchSlop()
            r12.mTouchSlop = r13
            r12 = r0
            r13 = r11
            int r13 = r13.getScaledMinimumFlingVelocity()
            r12.mMinFlingVelocity = r13
            r12 = r0
            r12.refreshDrawableState()
            r12 = r0
            r13 = r0
            boolean r13 = r13.isChecked()
            r12.setChecked(r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.SwitchCompat.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setSwitchTextAppearance(Context context, int resid) {
        TransformationMethod transformationMethod;
        TintTypedArray appearance = TintTypedArray.obtainStyledAttributes(context, resid, C0425R.styleable.TextAppearance);
        ColorStateList colors = appearance.getColorStateList(C0425R.styleable.TextAppearance_android_textColor);
        if (colors != null) {
            this.mTextColors = colors;
        } else {
            this.mTextColors = getTextColors();
        }
        int ts = appearance.getDimensionPixelSize(C0425R.styleable.TextAppearance_android_textSize, 0);
        if (!(ts == 0 || ((float) ts) == this.mTextPaint.getTextSize())) {
            this.mTextPaint.setTextSize((float) ts);
            requestLayout();
        }
        setSwitchTypefaceByIndex(appearance.getInt(C0425R.styleable.TextAppearance_android_typeface, -1), appearance.getInt(C0425R.styleable.TextAppearance_android_textStyle, -1));
        if (appearance.getBoolean(C0425R.styleable.TextAppearance_textAllCaps, false)) {
            new AllCapsTransformationMethod(getContext());
            this.mSwitchTransformationMethod = transformationMethod;
        } else {
            this.mSwitchTransformationMethod = null;
        }
        appearance.recycle();
    }

    private void setSwitchTypefaceByIndex(int typefaceIndex, int i) {
        int styleIndex = i;
        Typeface tf = null;
        switch (typefaceIndex) {
            case 1:
                tf = Typeface.SANS_SERIF;
                break;
            case 2:
                tf = Typeface.SERIF;
                break;
            case 3:
                tf = Typeface.MONOSPACE;
                break;
        }
        setSwitchTypeface(tf, styleIndex);
    }

    public void setSwitchTypeface(Typeface typeface, int i) {
        Typeface tf;
        Typeface tf2 = typeface;
        int style = i;
        if (style > 0) {
            if (tf2 == null) {
                tf = Typeface.defaultFromStyle(style);
            } else {
                tf = Typeface.create(tf2, style);
            }
            setSwitchTypeface(tf);
            int need = style & ((tf != null ? tf.getStyle() : 0) ^ -1);
            this.mTextPaint.setFakeBoldText((need & 1) != 0);
            this.mTextPaint.setTextSkewX((need & 2) != 0 ? -0.25f : 0.0f);
            return;
        }
        this.mTextPaint.setFakeBoldText(false);
        this.mTextPaint.setTextSkewX(0.0f);
        setSwitchTypeface(tf2);
    }

    public void setSwitchTypeface(Typeface typeface) {
        Typeface typeface2 = typeface;
        if ((this.mTextPaint.getTypeface() != null && !this.mTextPaint.getTypeface().equals(typeface2)) || (this.mTextPaint.getTypeface() == null && typeface2 != null)) {
            Typeface typeface3 = this.mTextPaint.setTypeface(typeface2);
            requestLayout();
            invalidate();
        }
    }

    public void setSwitchPadding(int pixels) {
        this.mSwitchPadding = pixels;
        requestLayout();
    }

    public int getSwitchPadding() {
        return this.mSwitchPadding;
    }

    public void setSwitchMinWidth(int pixels) {
        this.mSwitchMinWidth = pixels;
        requestLayout();
    }

    public int getSwitchMinWidth() {
        return this.mSwitchMinWidth;
    }

    public void setThumbTextPadding(int pixels) {
        this.mThumbTextPadding = pixels;
        requestLayout();
    }

    public int getThumbTextPadding() {
        return this.mThumbTextPadding;
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable track = drawable;
        if (this.mTrackDrawable != null) {
            this.mTrackDrawable.setCallback((Drawable.Callback) null);
        }
        this.mTrackDrawable = track;
        if (track != null) {
            track.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int resId) {
        setTrackDrawable(AppCompatResources.getDrawable(getContext(), resId));
    }

    public Drawable getTrackDrawable() {
        return this.mTrackDrawable;
    }

    public void setTrackTintList(@Nullable ColorStateList tint) {
        this.mTrackTintList = tint;
        this.mHasTrackTint = true;
        applyTrackTint();
    }

    @Nullable
    public ColorStateList getTrackTintList() {
        return this.mTrackTintList;
    }

    public void setTrackTintMode(@Nullable PorterDuff.Mode tintMode) {
        this.mTrackTintMode = tintMode;
        this.mHasTrackTintMode = true;
        applyTrackTint();
    }

    @Nullable
    public PorterDuff.Mode getTrackTintMode() {
        return this.mTrackTintMode;
    }

    private void applyTrackTint() {
        if (this.mTrackDrawable == null) {
            return;
        }
        if (this.mHasTrackTint || this.mHasTrackTintMode) {
            this.mTrackDrawable = this.mTrackDrawable.mutate();
            if (this.mHasTrackTint) {
                DrawableCompat.setTintList(this.mTrackDrawable, this.mTrackTintList);
            }
            if (this.mHasTrackTintMode) {
                DrawableCompat.setTintMode(this.mTrackDrawable, this.mTrackTintMode);
            }
            if (this.mTrackDrawable.isStateful()) {
                boolean state = this.mTrackDrawable.setState(getDrawableState());
            }
        }
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable thumb = drawable;
        if (this.mThumbDrawable != null) {
            this.mThumbDrawable.setCallback((Drawable.Callback) null);
        }
        this.mThumbDrawable = thumb;
        if (thumb != null) {
            thumb.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbResource(int resId) {
        setThumbDrawable(AppCompatResources.getDrawable(getContext(), resId));
    }

    public Drawable getThumbDrawable() {
        return this.mThumbDrawable;
    }

    public void setThumbTintList(@Nullable ColorStateList tint) {
        this.mThumbTintList = tint;
        this.mHasThumbTint = true;
        applyThumbTint();
    }

    @Nullable
    public ColorStateList getThumbTintList() {
        return this.mThumbTintList;
    }

    public void setThumbTintMode(@Nullable PorterDuff.Mode tintMode) {
        this.mThumbTintMode = tintMode;
        this.mHasThumbTintMode = true;
        applyThumbTint();
    }

    @Nullable
    public PorterDuff.Mode getThumbTintMode() {
        return this.mThumbTintMode;
    }

    private void applyThumbTint() {
        if (this.mThumbDrawable == null) {
            return;
        }
        if (this.mHasThumbTint || this.mHasThumbTintMode) {
            this.mThumbDrawable = this.mThumbDrawable.mutate();
            if (this.mHasThumbTint) {
                DrawableCompat.setTintList(this.mThumbDrawable, this.mThumbTintList);
            }
            if (this.mHasThumbTintMode) {
                DrawableCompat.setTintMode(this.mThumbDrawable, this.mThumbTintMode);
            }
            if (this.mThumbDrawable.isStateful()) {
                boolean state = this.mThumbDrawable.setState(getDrawableState());
            }
        }
    }

    public void setSplitTrack(boolean splitTrack) {
        this.mSplitTrack = splitTrack;
        invalidate();
    }

    public boolean getSplitTrack() {
        return this.mSplitTrack;
    }

    public CharSequence getTextOn() {
        return this.mTextOn;
    }

    public void setTextOn(CharSequence textOn) {
        this.mTextOn = textOn;
        requestLayout();
    }

    public CharSequence getTextOff() {
        return this.mTextOff;
    }

    public void setTextOff(CharSequence textOff) {
        this.mTextOff = textOff;
        requestLayout();
    }

    public void setShowText(boolean z) {
        boolean showText = z;
        if (this.mShowText != showText) {
            this.mShowText = showText;
            requestLayout();
        }
    }

    public boolean getShowText() {
        return this.mShowText;
    }

    public void onMeasure(int i, int i2) {
        int thumbWidth;
        int thumbHeight;
        int maxTextWidth;
        int trackHeight;
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        if (this.mShowText) {
            if (this.mOnLayout == null) {
                this.mOnLayout = makeLayout(this.mTextOn);
            }
            if (this.mOffLayout == null) {
                this.mOffLayout = makeLayout(this.mTextOff);
            }
        }
        Rect padding = this.mTempRect;
        if (this.mThumbDrawable != null) {
            boolean padding2 = this.mThumbDrawable.getPadding(padding);
            thumbWidth = (this.mThumbDrawable.getIntrinsicWidth() - padding.left) - padding.right;
            thumbHeight = this.mThumbDrawable.getIntrinsicHeight();
        } else {
            thumbWidth = 0;
            thumbHeight = 0;
        }
        if (this.mShowText) {
            maxTextWidth = Math.max(this.mOnLayout.getWidth(), this.mOffLayout.getWidth()) + (this.mThumbTextPadding * 2);
        } else {
            maxTextWidth = 0;
        }
        this.mThumbWidth = Math.max(maxTextWidth, thumbWidth);
        if (this.mTrackDrawable != null) {
            boolean padding3 = this.mTrackDrawable.getPadding(padding);
            trackHeight = this.mTrackDrawable.getIntrinsicHeight();
        } else {
            padding.setEmpty();
            trackHeight = 0;
        }
        int paddingLeft = padding.left;
        int paddingRight = padding.right;
        if (this.mThumbDrawable != null) {
            Rect inset = DrawableUtils.getOpticalBounds(this.mThumbDrawable);
            paddingLeft = Math.max(paddingLeft, inset.left);
            paddingRight = Math.max(paddingRight, inset.right);
        }
        int switchWidth = Math.max(this.mSwitchMinWidth, (2 * this.mThumbWidth) + paddingLeft + paddingRight);
        int switchHeight = Math.max(trackHeight, thumbHeight);
        this.mSwitchWidth = switchWidth;
        this.mSwitchHeight = switchHeight;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getMeasuredHeight() < switchHeight) {
            setMeasuredDimension(getMeasuredWidthAndState(), switchHeight);
        }
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        AccessibilityEvent event = accessibilityEvent;
        super.onPopulateAccessibilityEvent(event);
        CharSequence text = isChecked() ? this.mTextOn : this.mTextOff;
        if (text != null) {
            boolean add = event.getText().add(text);
        }
    }

    private Layout makeLayout(CharSequence charSequence) {
        CharSequence text = charSequence;
        CharSequence transformed = this.mSwitchTransformationMethod != null ? this.mSwitchTransformationMethod.getTransformation(text, this) : text;
        StaticLayout staticLayout = r13;
        StaticLayout staticLayout2 = new StaticLayout(transformed, this.mTextPaint, transformed != null ? (int) Math.ceil((double) Layout.getDesiredWidth(transformed, this.mTextPaint)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        return staticLayout;
    }

    private boolean hitThumb(float f, float f2) {
        float x = f;
        float y = f2;
        if (this.mThumbDrawable == null) {
            return false;
        }
        int thumbOffset = getThumbOffset();
        boolean padding = this.mThumbDrawable.getPadding(this.mTempRect);
        int thumbTop = this.mSwitchTop - this.mTouchSlop;
        int thumbLeft = (this.mSwitchLeft + thumbOffset) - this.mTouchSlop;
        return x > ((float) thumbLeft) && x < ((float) ((((thumbLeft + this.mThumbWidth) + this.mTempRect.left) + this.mTempRect.right) + this.mTouchSlop)) && y > ((float) thumbTop) && y < ((float) (this.mSwitchBottom + this.mTouchSlop));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float dPos;
        MotionEvent ev = motionEvent;
        this.mVelocityTracker.addMovement(ev);
        switch (ev.getActionMasked()) {
            case 0:
                float x = ev.getX();
                float y = ev.getY();
                if (isEnabled() && hitThumb(x, y)) {
                    this.mTouchMode = 1;
                    this.mTouchX = x;
                    this.mTouchY = y;
                    break;
                }
            case 1:
            case 3:
                if (this.mTouchMode != 2) {
                    this.mTouchMode = 0;
                    this.mVelocityTracker.clear();
                    break;
                } else {
                    stopDrag(ev);
                    boolean onTouchEvent = super.onTouchEvent(ev);
                    return true;
                }
            case 2:
                switch (this.mTouchMode) {
                    case 1:
                        float x2 = ev.getX();
                        float y2 = ev.getY();
                        if (Math.abs(x2 - this.mTouchX) > ((float) this.mTouchSlop) || Math.abs(y2 - this.mTouchY) > ((float) this.mTouchSlop)) {
                            this.mTouchMode = 2;
                            getParent().requestDisallowInterceptTouchEvent(true);
                            this.mTouchX = x2;
                            this.mTouchY = y2;
                            return true;
                        }
                    case 2:
                        float x3 = ev.getX();
                        int thumbScrollRange = getThumbScrollRange();
                        float thumbScrollOffset = x3 - this.mTouchX;
                        if (thumbScrollRange != 0) {
                            dPos = thumbScrollOffset / ((float) thumbScrollRange);
                        } else {
                            dPos = thumbScrollOffset > 0.0f ? 1.0f : -1.0f;
                        }
                        if (ViewUtils.isLayoutRtl(this)) {
                            dPos = -dPos;
                        }
                        float newPos = constrain(this.mThumbPosition + dPos, 0.0f, 1.0f);
                        if (newPos != this.mThumbPosition) {
                            this.mTouchX = x3;
                            setThumbPosition(newPos);
                        }
                        return true;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void cancelSuperTouch(MotionEvent ev) {
        MotionEvent cancel = MotionEvent.obtain(ev);
        cancel.setAction(3);
        boolean onTouchEvent = super.onTouchEvent(cancel);
        cancel.recycle();
    }

    private void stopDrag(MotionEvent motionEvent) {
        boolean newState;
        MotionEvent ev = motionEvent;
        this.mTouchMode = 0;
        boolean commitChange = ev.getAction() == 1 && isEnabled();
        boolean oldState = isChecked();
        if (commitChange) {
            this.mVelocityTracker.computeCurrentVelocity(1000);
            float xvel = this.mVelocityTracker.getXVelocity();
            if (Math.abs(xvel) > ((float) this.mMinFlingVelocity)) {
                newState = ViewUtils.isLayoutRtl(this) ? xvel < 0.0f : xvel > 0.0f;
            } else {
                newState = getTargetCheckedState();
            }
        } else {
            newState = oldState;
        }
        if (newState != oldState) {
            playSoundEffect(0);
        }
        setChecked(newState);
        cancelSuperTouch(ev);
    }

    private void animateThumbToCheckedState(boolean newCheckedState) {
        this.mPositionAnimator = ObjectAnimator.ofFloat(this, THUMB_POS, new float[]{newCheckedState ? 1.0f : 0.0f});
        ObjectAnimator duration = this.mPositionAnimator.setDuration(250);
        if (Build.VERSION.SDK_INT >= 18) {
            this.mPositionAnimator.setAutoCancel(true);
        }
        this.mPositionAnimator.start();
    }

    private void cancelPositionAnimator() {
        if (this.mPositionAnimator != null) {
            this.mPositionAnimator.cancel();
        }
    }

    private boolean getTargetCheckedState() {
        return this.mThumbPosition > 0.5f;
    }

    /* access modifiers changed from: package-private */
    public void setThumbPosition(float position) {
        this.mThumbPosition = position;
        invalidate();
    }

    public void toggle() {
        setChecked(!isChecked());
    }

    public void setChecked(boolean checked) {
        super.setChecked(checked);
        boolean checked2 = isChecked();
        if (getWindowToken() == null || !ViewCompat.isLaidOut(this)) {
            cancelPositionAnimator();
            setThumbPosition(checked2 ? 1.0f : 0.0f);
            return;
        }
        animateThumbToCheckedState(checked2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int switchRight;
        int switchLeft;
        int switchBottom;
        int switchTop;
        super.onLayout(changed, left, top, right, bottom);
        int opticalInsetLeft = 0;
        int opticalInsetRight = 0;
        if (this.mThumbDrawable != null) {
            Rect trackPadding = this.mTempRect;
            if (this.mTrackDrawable != null) {
                boolean padding = this.mTrackDrawable.getPadding(trackPadding);
            } else {
                trackPadding.setEmpty();
            }
            Rect insets = DrawableUtils.getOpticalBounds(this.mThumbDrawable);
            opticalInsetLeft = Math.max(0, insets.left - trackPadding.left);
            opticalInsetRight = Math.max(0, insets.right - trackPadding.right);
        }
        if (ViewUtils.isLayoutRtl(this)) {
            switchLeft = getPaddingLeft() + opticalInsetLeft;
            switchRight = ((switchLeft + this.mSwitchWidth) - opticalInsetLeft) - opticalInsetRight;
        } else {
            switchRight = (getWidth() - getPaddingRight()) - opticalInsetRight;
            switchLeft = (switchRight - this.mSwitchWidth) + opticalInsetLeft + opticalInsetRight;
        }
        switch (getGravity() & 112) {
            case 16:
                switchTop = (((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2) - (this.mSwitchHeight / 2);
                switchBottom = switchTop + this.mSwitchHeight;
                break;
            case 80:
                switchBottom = getHeight() - getPaddingBottom();
                switchTop = switchBottom - this.mSwitchHeight;
                break;
            default:
                switchTop = getPaddingTop();
                switchBottom = switchTop + this.mSwitchHeight;
                break;
        }
        this.mSwitchLeft = switchLeft;
        this.mSwitchTop = switchTop;
        this.mSwitchBottom = switchBottom;
        this.mSwitchRight = switchRight;
    }

    public void draw(Canvas canvas) {
        Rect thumbInsets;
        Canvas c = canvas;
        Rect padding = this.mTempRect;
        int switchLeft = this.mSwitchLeft;
        int switchTop = this.mSwitchTop;
        int switchRight = this.mSwitchRight;
        int switchBottom = this.mSwitchBottom;
        int thumbInitialLeft = switchLeft + getThumbOffset();
        if (this.mThumbDrawable != null) {
            thumbInsets = DrawableUtils.getOpticalBounds(this.mThumbDrawable);
        } else {
            thumbInsets = DrawableUtils.INSETS_NONE;
        }
        if (this.mTrackDrawable != null) {
            boolean padding2 = this.mTrackDrawable.getPadding(padding);
            thumbInitialLeft += padding.left;
            int trackLeft = switchLeft;
            int trackTop = switchTop;
            int trackRight = switchRight;
            int trackBottom = switchBottom;
            if (thumbInsets != null) {
                if (thumbInsets.left > padding.left) {
                    trackLeft += thumbInsets.left - padding.left;
                }
                if (thumbInsets.top > padding.top) {
                    trackTop += thumbInsets.top - padding.top;
                }
                if (thumbInsets.right > padding.right) {
                    trackRight -= thumbInsets.right - padding.right;
                }
                if (thumbInsets.bottom > padding.bottom) {
                    trackBottom -= thumbInsets.bottom - padding.bottom;
                }
            }
            this.mTrackDrawable.setBounds(trackLeft, trackTop, trackRight, trackBottom);
        }
        if (this.mThumbDrawable != null) {
            boolean padding3 = this.mThumbDrawable.getPadding(padding);
            int thumbLeft = thumbInitialLeft - padding.left;
            int thumbRight = thumbInitialLeft + this.mThumbWidth + padding.right;
            this.mThumbDrawable.setBounds(thumbLeft, switchTop, thumbRight, switchBottom);
            Drawable background = getBackground();
            if (background != null) {
                DrawableCompat.setHotspotBounds(background, thumbLeft, switchTop, thumbRight, switchBottom);
            }
        }
        super.draw(c);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Layout layout;
        int cX;
        Canvas canvas2 = canvas;
        super.onDraw(canvas2);
        Rect padding = this.mTempRect;
        Drawable trackDrawable = this.mTrackDrawable;
        if (trackDrawable != null) {
            boolean padding2 = trackDrawable.getPadding(padding);
        } else {
            padding.setEmpty();
        }
        int switchTop = this.mSwitchTop;
        int switchBottom = this.mSwitchBottom;
        int switchInnerTop = switchTop + padding.top;
        int switchInnerBottom = switchBottom - padding.bottom;
        Drawable thumbDrawable = this.mThumbDrawable;
        if (trackDrawable != null) {
            if (!this.mSplitTrack || thumbDrawable == null) {
                trackDrawable.draw(canvas2);
            } else {
                Rect insets = DrawableUtils.getOpticalBounds(thumbDrawable);
                thumbDrawable.copyBounds(padding);
                padding.left += insets.left;
                padding.right -= insets.right;
                int saveCount = canvas2.save();
                boolean clipRect = canvas2.clipRect(padding, Region.Op.DIFFERENCE);
                trackDrawable.draw(canvas2);
                canvas2.restoreToCount(saveCount);
            }
        }
        int saveCount2 = canvas2.save();
        if (thumbDrawable != null) {
            thumbDrawable.draw(canvas2);
        }
        if (getTargetCheckedState()) {
            layout = this.mOnLayout;
        } else {
            layout = this.mOffLayout;
        }
        Layout switchText = layout;
        if (switchText != null) {
            int[] drawableState = getDrawableState();
            if (this.mTextColors != null) {
                this.mTextPaint.setColor(this.mTextColors.getColorForState(drawableState, 0));
            }
            this.mTextPaint.drawableState = drawableState;
            if (thumbDrawable != null) {
                Rect bounds = thumbDrawable.getBounds();
                cX = bounds.left + bounds.right;
            } else {
                cX = getWidth();
            }
            canvas2.translate((float) ((cX / 2) - (switchText.getWidth() / 2)), (float) (((switchInnerTop + switchInnerBottom) / 2) - (switchText.getHeight() / 2)));
            switchText.draw(canvas2);
        }
        canvas2.restoreToCount(saveCount2);
    }

    public int getCompoundPaddingLeft() {
        if (!ViewUtils.isLayoutRtl(this)) {
            return super.getCompoundPaddingLeft();
        }
        int padding = super.getCompoundPaddingLeft() + this.mSwitchWidth;
        if (!TextUtils.isEmpty(getText())) {
            padding += this.mSwitchPadding;
        }
        return padding;
    }

    public int getCompoundPaddingRight() {
        if (ViewUtils.isLayoutRtl(this)) {
            return super.getCompoundPaddingRight();
        }
        int padding = super.getCompoundPaddingRight() + this.mSwitchWidth;
        if (!TextUtils.isEmpty(getText())) {
            padding += this.mSwitchPadding;
        }
        return padding;
    }

    private int getThumbOffset() {
        float thumbPosition;
        if (ViewUtils.isLayoutRtl(this)) {
            thumbPosition = 1.0f - this.mThumbPosition;
        } else {
            thumbPosition = this.mThumbPosition;
        }
        return (int) ((thumbPosition * ((float) getThumbScrollRange())) + 0.5f);
    }

    private int getThumbScrollRange() {
        Rect insets;
        if (this.mTrackDrawable == null) {
            return 0;
        }
        Rect padding = this.mTempRect;
        boolean padding2 = this.mTrackDrawable.getPadding(padding);
        if (this.mThumbDrawable != null) {
            insets = DrawableUtils.getOpticalBounds(this.mThumbDrawable);
        } else {
            insets = DrawableUtils.INSETS_NONE;
        }
        return ((((this.mSwitchWidth - this.mThumbWidth) - padding.left) - padding.right) - insets.left) - insets.right;
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            int[] mergeDrawableStates = mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] state = getDrawableState();
        boolean changed = false;
        Drawable thumbDrawable = this.mThumbDrawable;
        if (thumbDrawable != null && thumbDrawable.isStateful()) {
            changed = false | thumbDrawable.setState(state);
        }
        Drawable trackDrawable = this.mTrackDrawable;
        if (trackDrawable != null && trackDrawable.isStateful()) {
            changed |= trackDrawable.setState(state);
        }
        if (changed) {
            invalidate();
        }
    }

    public void drawableHotspotChanged(float f, float f2) {
        float x = f;
        float y = f2;
        if (Build.VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(x, y);
        }
        if (this.mThumbDrawable != null) {
            DrawableCompat.setHotspot(this.mThumbDrawable, x, y);
        }
        if (this.mTrackDrawable != null) {
            DrawableCompat.setHotspot(this.mTrackDrawable, x, y);
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        Drawable who = drawable;
        return super.verifyDrawable(who) || who == this.mThumbDrawable || who == this.mTrackDrawable;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.mThumbDrawable != null) {
            this.mThumbDrawable.jumpToCurrentState();
        }
        if (this.mTrackDrawable != null) {
            this.mTrackDrawable.jumpToCurrentState();
        }
        if (this.mPositionAnimator != null && this.mPositionAnimator.isStarted()) {
            this.mPositionAnimator.end();
            this.mPositionAnimator = null;
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        AccessibilityEvent event = accessibilityEvent;
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(ACCESSIBILITY_EVENT_CLASS_NAME);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        StringBuilder sb;
        AccessibilityNodeInfo info = accessibilityNodeInfo;
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(ACCESSIBILITY_EVENT_CLASS_NAME);
        CharSequence switchText = isChecked() ? this.mTextOn : this.mTextOff;
        if (!TextUtils.isEmpty(switchText)) {
            CharSequence oldText = info.getText();
            if (TextUtils.isEmpty(oldText)) {
                info.setText(switchText);
                return;
            }
            new StringBuilder();
            StringBuilder newText = sb;
            StringBuilder append = newText.append(oldText).append(' ').append(switchText);
            info.setText(newText);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback actionModeCallback) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, actionModeCallback));
    }

    private static float constrain(float f, float f2, float f3) {
        float amount = f;
        float low = f2;
        float high = f3;
        return amount < low ? low : amount > high ? high : amount;
    }
}
