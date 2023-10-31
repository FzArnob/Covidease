package android.support.design.widget;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.design.animation.AnimationUtils;
import android.support.p000v4.math.MathUtils;
import android.support.p000v4.text.TextDirectionHeuristicsCompat;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.widget.TintTypedArray;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class CollapsingTextHelper {
    private static final boolean DEBUG_DRAW = false;
    private static final Paint DEBUG_DRAW_PAINT = null;
    private static final boolean USE_SCALING_TEXTURE = (Build.VERSION.SDK_INT < 18);
    private boolean boundsChanged;
    private final Rect collapsedBounds;
    private float collapsedDrawX;
    private float collapsedDrawY;
    private int collapsedShadowColor;
    private float collapsedShadowDx;
    private float collapsedShadowDy;
    private float collapsedShadowRadius;
    private ColorStateList collapsedTextColor;
    private int collapsedTextGravity = 16;
    private float collapsedTextSize = 15.0f;
    private Typeface collapsedTypeface;
    private final RectF currentBounds;
    private float currentDrawX;
    private float currentDrawY;
    private float currentTextSize;
    private Typeface currentTypeface;
    private boolean drawTitle;
    private final Rect expandedBounds;
    private float expandedDrawX;
    private float expandedDrawY;
    private float expandedFraction;
    private int expandedShadowColor;
    private float expandedShadowDx;
    private float expandedShadowDy;
    private float expandedShadowRadius;
    private ColorStateList expandedTextColor;
    private int expandedTextGravity = 16;
    private float expandedTextSize = 15.0f;
    private Bitmap expandedTitleTexture;
    private Typeface expandedTypeface;
    private boolean isRtl;
    private TimeInterpolator positionInterpolator;
    private float scale;
    private int[] state;
    private CharSequence text;
    private final TextPaint textPaint;
    private TimeInterpolator textSizeInterpolator;
    private CharSequence textToDraw;
    private float textureAscent;
    private float textureDescent;
    private Paint texturePaint;
    private final TextPaint tmpPaint;
    private boolean useTexture;
    private final View view;

    static {
        if (DEBUG_DRAW_PAINT != null) {
            DEBUG_DRAW_PAINT.setAntiAlias(true);
            DEBUG_DRAW_PAINT.setColor(-65281);
        }
    }

    public CollapsingTextHelper(View view2) {
        TextPaint textPaint2;
        TextPaint textPaint3;
        Rect rect;
        Rect rect2;
        RectF rectF;
        this.view = view2;
        new TextPaint(129);
        this.textPaint = textPaint2;
        new TextPaint(this.textPaint);
        this.tmpPaint = textPaint3;
        new Rect();
        this.collapsedBounds = rect;
        new Rect();
        this.expandedBounds = rect2;
        new RectF();
        this.currentBounds = rectF;
    }

    public void setTextSizeInterpolator(TimeInterpolator interpolator) {
        this.textSizeInterpolator = interpolator;
        recalculate();
    }

    public void setPositionInterpolator(TimeInterpolator interpolator) {
        this.positionInterpolator = interpolator;
        recalculate();
    }

    public void setExpandedTextSize(float f) {
        float textSize = f;
        if (this.expandedTextSize != textSize) {
            this.expandedTextSize = textSize;
            recalculate();
        }
    }

    public void setCollapsedTextSize(float f) {
        float textSize = f;
        if (this.collapsedTextSize != textSize) {
            this.collapsedTextSize = textSize;
            recalculate();
        }
    }

    public void setCollapsedTextColor(ColorStateList colorStateList) {
        ColorStateList textColor = colorStateList;
        if (this.collapsedTextColor != textColor) {
            this.collapsedTextColor = textColor;
            recalculate();
        }
    }

    public void setExpandedTextColor(ColorStateList colorStateList) {
        ColorStateList textColor = colorStateList;
        if (this.expandedTextColor != textColor) {
            this.expandedTextColor = textColor;
            recalculate();
        }
    }

    public void setExpandedBounds(int i, int i2, int i3, int i4) {
        int left = i;
        int top = i2;
        int right = i3;
        int bottom = i4;
        if (!rectEquals(this.expandedBounds, left, top, right, bottom)) {
            this.expandedBounds.set(left, top, right, bottom);
            this.boundsChanged = true;
            onBoundsChanged();
        }
    }

    public void setCollapsedBounds(int i, int i2, int i3, int i4) {
        int left = i;
        int top = i2;
        int right = i3;
        int bottom = i4;
        if (!rectEquals(this.collapsedBounds, left, top, right, bottom)) {
            this.collapsedBounds.set(left, top, right, bottom);
            this.boundsChanged = true;
            onBoundsChanged();
        }
    }

    public float calculateCollapsedTextWidth() {
        if (this.text == null) {
            return 0.0f;
        }
        getTextPaintCollapsed(this.tmpPaint);
        return this.tmpPaint.measureText(this.text, 0, this.text.length());
    }

    public float getCollapsedTextHeight() {
        getTextPaintCollapsed(this.tmpPaint);
        return -this.tmpPaint.ascent();
    }

    public void getCollapsedTextActualBounds(RectF rectF) {
        float calculateCollapsedTextWidth;
        RectF bounds = rectF;
        boolean isRtl2 = calculateIsRtl(this.text);
        RectF rectF2 = bounds;
        if (!isRtl2) {
            calculateCollapsedTextWidth = (float) this.collapsedBounds.left;
        } else {
            calculateCollapsedTextWidth = ((float) this.collapsedBounds.right) - calculateCollapsedTextWidth();
        }
        rectF2.left = calculateCollapsedTextWidth;
        bounds.top = (float) this.collapsedBounds.top;
        bounds.right = !isRtl2 ? bounds.left + calculateCollapsedTextWidth() : (float) this.collapsedBounds.right;
        bounds.bottom = ((float) this.collapsedBounds.top) + getCollapsedTextHeight();
    }

    private void getTextPaintCollapsed(TextPaint textPaint2) {
        TextPaint textPaint3 = textPaint2;
        textPaint3.setTextSize(this.collapsedTextSize);
        Typeface typeface = textPaint3.setTypeface(this.collapsedTypeface);
    }

    /* access modifiers changed from: package-private */
    public void onBoundsChanged() {
        this.drawTitle = this.collapsedBounds.width() > 0 && this.collapsedBounds.height() > 0 && this.expandedBounds.width() > 0 && this.expandedBounds.height() > 0;
    }

    public void setExpandedTextGravity(int i) {
        int gravity = i;
        if (this.expandedTextGravity != gravity) {
            this.expandedTextGravity = gravity;
            recalculate();
        }
    }

    public int getExpandedTextGravity() {
        return this.expandedTextGravity;
    }

    public void setCollapsedTextGravity(int i) {
        int gravity = i;
        if (this.collapsedTextGravity != gravity) {
            this.collapsedTextGravity = gravity;
            recalculate();
        }
    }

    public int getCollapsedTextGravity() {
        return this.collapsedTextGravity;
    }

    public void setCollapsedTextAppearance(int i) {
        int resId = i;
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(this.view.getContext(), resId, C0425R.styleable.TextAppearance);
        if (a.hasValue(C0425R.styleable.TextAppearance_android_textColor)) {
            this.collapsedTextColor = a.getColorStateList(C0425R.styleable.TextAppearance_android_textColor);
        }
        if (a.hasValue(C0425R.styleable.TextAppearance_android_textSize)) {
            this.collapsedTextSize = (float) a.getDimensionPixelSize(C0425R.styleable.TextAppearance_android_textSize, (int) this.collapsedTextSize);
        }
        this.collapsedShadowColor = a.getInt(C0425R.styleable.TextAppearance_android_shadowColor, 0);
        this.collapsedShadowDx = a.getFloat(C0425R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.collapsedShadowDy = a.getFloat(C0425R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.collapsedShadowRadius = a.getFloat(C0425R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        a.recycle();
        if (Build.VERSION.SDK_INT >= 16) {
            this.collapsedTypeface = readFontFamilyTypeface(resId);
        }
        recalculate();
    }

    public void setExpandedTextAppearance(int i) {
        int resId = i;
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(this.view.getContext(), resId, C0425R.styleable.TextAppearance);
        if (a.hasValue(C0425R.styleable.TextAppearance_android_textColor)) {
            this.expandedTextColor = a.getColorStateList(C0425R.styleable.TextAppearance_android_textColor);
        }
        if (a.hasValue(C0425R.styleable.TextAppearance_android_textSize)) {
            this.expandedTextSize = (float) a.getDimensionPixelSize(C0425R.styleable.TextAppearance_android_textSize, (int) this.expandedTextSize);
        }
        this.expandedShadowColor = a.getInt(C0425R.styleable.TextAppearance_android_shadowColor, 0);
        this.expandedShadowDx = a.getFloat(C0425R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.expandedShadowDy = a.getFloat(C0425R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.expandedShadowRadius = a.getFloat(C0425R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        a.recycle();
        if (Build.VERSION.SDK_INT >= 16) {
            this.expandedTypeface = readFontFamilyTypeface(resId);
        }
        recalculate();
    }

    /* JADX INFO: finally extract failed */
    private Typeface readFontFamilyTypeface(int resId) {
        int[] iArr = {16843692};
        TypedArray a = this.view.getContext().obtainStyledAttributes(resId, iArr);
        try {
            String family = a.getString(0);
            if (family != null) {
                Typeface create = Typeface.create(family, 0);
                a.recycle();
                return create;
            }
            a.recycle();
            return null;
        } catch (Throwable th) {
            Throwable th2 = th;
            a.recycle();
            throw th2;
        }
    }

    public void setCollapsedTypeface(Typeface typeface) {
        Typeface typeface2 = typeface;
        if (this.collapsedTypeface != typeface2) {
            this.collapsedTypeface = typeface2;
            recalculate();
        }
    }

    public void setExpandedTypeface(Typeface typeface) {
        Typeface typeface2 = typeface;
        if (this.expandedTypeface != typeface2) {
            this.expandedTypeface = typeface2;
            recalculate();
        }
    }

    public void setTypefaces(Typeface typeface) {
        Typeface typeface2 = typeface;
        this.expandedTypeface = typeface2;
        this.collapsedTypeface = typeface2;
        recalculate();
    }

    public Typeface getCollapsedTypeface() {
        return this.collapsedTypeface != null ? this.collapsedTypeface : Typeface.DEFAULT;
    }

    public Typeface getExpandedTypeface() {
        return this.expandedTypeface != null ? this.expandedTypeface : Typeface.DEFAULT;
    }

    public void setExpansionFraction(float fraction) {
        float fraction2 = MathUtils.clamp(fraction, 0.0f, 1.0f);
        if (fraction2 != this.expandedFraction) {
            this.expandedFraction = fraction2;
            calculateCurrentOffsets();
        }
    }

    public final boolean setState(int[] state2) {
        this.state = state2;
        if (!isStateful()) {
            return false;
        }
        recalculate();
        return true;
    }

    public final boolean isStateful() {
        return (this.collapsedTextColor != null && this.collapsedTextColor.isStateful()) || (this.expandedTextColor != null && this.expandedTextColor.isStateful());
    }

    public float getExpansionFraction() {
        return this.expandedFraction;
    }

    public float getCollapsedTextSize() {
        return this.collapsedTextSize;
    }

    public float getExpandedTextSize() {
        return this.expandedTextSize;
    }

    private void calculateCurrentOffsets() {
        calculateOffsets(this.expandedFraction);
    }

    private void calculateOffsets(float f) {
        float fraction = f;
        interpolateBounds(fraction);
        this.currentDrawX = lerp(this.expandedDrawX, this.collapsedDrawX, fraction, this.positionInterpolator);
        this.currentDrawY = lerp(this.expandedDrawY, this.collapsedDrawY, fraction, this.positionInterpolator);
        setInterpolatedTextSize(lerp(this.expandedTextSize, this.collapsedTextSize, fraction, this.textSizeInterpolator));
        if (this.collapsedTextColor != this.expandedTextColor) {
            this.textPaint.setColor(blendColors(getCurrentExpandedTextColor(), getCurrentCollapsedTextColor(), fraction));
        } else {
            this.textPaint.setColor(getCurrentCollapsedTextColor());
        }
        this.textPaint.setShadowLayer(lerp(this.expandedShadowRadius, this.collapsedShadowRadius, fraction, (TimeInterpolator) null), lerp(this.expandedShadowDx, this.collapsedShadowDx, fraction, (TimeInterpolator) null), lerp(this.expandedShadowDy, this.collapsedShadowDy, fraction, (TimeInterpolator) null), blendColors(this.expandedShadowColor, this.collapsedShadowColor, fraction));
        ViewCompat.postInvalidateOnAnimation(this.view);
    }

    @ColorInt
    private int getCurrentExpandedTextColor() {
        if (this.state != null) {
            return this.expandedTextColor.getColorForState(this.state, 0);
        }
        return this.expandedTextColor.getDefaultColor();
    }

    @ColorInt
    @VisibleForTesting
    public int getCurrentCollapsedTextColor() {
        if (this.state != null) {
            return this.collapsedTextColor.getColorForState(this.state, 0);
        }
        return this.collapsedTextColor.getDefaultColor();
    }

    private void calculateBaseOffsets() {
        float currentTextSize2 = this.currentTextSize;
        calculateUsingTextSize(this.collapsedTextSize);
        float width = this.textToDraw != null ? this.textPaint.measureText(this.textToDraw, 0, this.textToDraw.length()) : 0.0f;
        int collapsedAbsGravity = GravityCompat.getAbsoluteGravity(this.collapsedTextGravity, this.isRtl ? 1 : 0);
        switch (collapsedAbsGravity & 112) {
            case 48:
                this.collapsedDrawY = ((float) this.collapsedBounds.top) - this.textPaint.ascent();
                break;
            case 80:
                this.collapsedDrawY = (float) this.collapsedBounds.bottom;
                break;
            default:
                this.collapsedDrawY = ((float) this.collapsedBounds.centerY()) + (((this.textPaint.descent() - this.textPaint.ascent()) / 2.0f) - this.textPaint.descent());
                break;
        }
        switch (collapsedAbsGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
            case 1:
                this.collapsedDrawX = ((float) this.collapsedBounds.centerX()) - (width / 2.0f);
                break;
            case 5:
                this.collapsedDrawX = ((float) this.collapsedBounds.right) - width;
                break;
            default:
                this.collapsedDrawX = (float) this.collapsedBounds.left;
                break;
        }
        calculateUsingTextSize(this.expandedTextSize);
        float width2 = this.textToDraw != null ? this.textPaint.measureText(this.textToDraw, 0, this.textToDraw.length()) : 0.0f;
        int expandedAbsGravity = GravityCompat.getAbsoluteGravity(this.expandedTextGravity, this.isRtl ? 1 : 0);
        switch (expandedAbsGravity & 112) {
            case 48:
                this.expandedDrawY = ((float) this.expandedBounds.top) - this.textPaint.ascent();
                break;
            case 80:
                this.expandedDrawY = (float) this.expandedBounds.bottom;
                break;
            default:
                this.expandedDrawY = ((float) this.expandedBounds.centerY()) + (((this.textPaint.descent() - this.textPaint.ascent()) / 2.0f) - this.textPaint.descent());
                break;
        }
        switch (expandedAbsGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
            case 1:
                this.expandedDrawX = ((float) this.expandedBounds.centerX()) - (width2 / 2.0f);
                break;
            case 5:
                this.expandedDrawX = ((float) this.expandedBounds.right) - width2;
                break;
            default:
                this.expandedDrawX = (float) this.expandedBounds.left;
                break;
        }
        clearTexture();
        setInterpolatedTextSize(currentTextSize2);
    }

    private void interpolateBounds(float f) {
        float fraction = f;
        this.currentBounds.left = lerp((float) this.expandedBounds.left, (float) this.collapsedBounds.left, fraction, this.positionInterpolator);
        this.currentBounds.top = lerp(this.expandedDrawY, this.collapsedDrawY, fraction, this.positionInterpolator);
        this.currentBounds.right = lerp((float) this.expandedBounds.right, (float) this.collapsedBounds.right, fraction, this.positionInterpolator);
        this.currentBounds.bottom = lerp((float) this.expandedBounds.bottom, (float) this.collapsedBounds.bottom, fraction, this.positionInterpolator);
    }

    public void draw(Canvas canvas) {
        float ascent;
        Canvas canvas2 = canvas;
        int saveCount = canvas2.save();
        if (this.textToDraw != null && this.drawTitle) {
            float x = this.currentDrawX;
            float y = this.currentDrawY;
            boolean drawTexture = this.useTexture && this.expandedTitleTexture != null;
            if (drawTexture) {
                ascent = this.textureAscent * this.scale;
                float f = this.textureDescent * this.scale;
            } else {
                ascent = this.textPaint.ascent() * this.scale;
                float descent = this.textPaint.descent() * this.scale;
            }
            if (drawTexture) {
                y += ascent;
            }
            if (this.scale != 1.0f) {
                canvas2.scale(this.scale, this.scale, x, y);
            }
            if (drawTexture) {
                canvas2.drawBitmap(this.expandedTitleTexture, x, y, this.texturePaint);
            } else {
                canvas2.drawText(this.textToDraw, 0, this.textToDraw.length(), x, y, this.textPaint);
            }
        }
        canvas2.restoreToCount(saveCount);
    }

    private boolean calculateIsRtl(CharSequence charSequence) {
        CharSequence text2 = charSequence;
        return (ViewCompat.getLayoutDirection(this.view) == 1 ? TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL : TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR).isRtl(text2, 0, text2.length());
    }

    private void setInterpolatedTextSize(float textSize) {
        calculateUsingTextSize(textSize);
        this.useTexture = USE_SCALING_TEXTURE && this.scale != 1.0f;
        if (this.useTexture) {
            ensureExpandedTexture();
        }
        ViewCompat.postInvalidateOnAnimation(this.view);
    }

    private void calculateUsingTextSize(float f) {
        float newTextSize;
        float availableWidth;
        float textSize = f;
        if (this.text != null) {
            float collapsedWidth = (float) this.collapsedBounds.width();
            float expandedWidth = (float) this.expandedBounds.width();
            boolean updateDrawText = false;
            if (isClose(textSize, this.collapsedTextSize)) {
                newTextSize = this.collapsedTextSize;
                this.scale = 1.0f;
                if (this.currentTypeface != this.collapsedTypeface) {
                    this.currentTypeface = this.collapsedTypeface;
                    updateDrawText = true;
                }
                availableWidth = collapsedWidth;
            } else {
                newTextSize = this.expandedTextSize;
                if (this.currentTypeface != this.expandedTypeface) {
                    this.currentTypeface = this.expandedTypeface;
                    updateDrawText = true;
                }
                if (isClose(textSize, this.expandedTextSize)) {
                    this.scale = 1.0f;
                } else {
                    this.scale = textSize / this.expandedTextSize;
                }
                float textSizeRatio = this.collapsedTextSize / this.expandedTextSize;
                if (expandedWidth * textSizeRatio > collapsedWidth) {
                    availableWidth = Math.min(collapsedWidth / textSizeRatio, expandedWidth);
                } else {
                    availableWidth = expandedWidth;
                }
            }
            if (availableWidth > 0.0f) {
                updateDrawText = this.currentTextSize != newTextSize || this.boundsChanged || updateDrawText;
                this.currentTextSize = newTextSize;
                this.boundsChanged = false;
            }
            if (this.textToDraw == null || updateDrawText) {
                this.textPaint.setTextSize(this.currentTextSize);
                Typeface typeface = this.textPaint.setTypeface(this.currentTypeface);
                this.textPaint.setLinearText(this.scale != 1.0f);
                CharSequence title = TextUtils.ellipsize(this.text, this.textPaint, availableWidth, TextUtils.TruncateAt.END);
                if (!TextUtils.equals(title, this.textToDraw)) {
                    this.textToDraw = title;
                    this.isRtl = calculateIsRtl(this.textToDraw);
                }
            }
        }
    }

    private void ensureExpandedTexture() {
        Canvas c;
        Paint paint;
        if (this.expandedTitleTexture == null && !this.expandedBounds.isEmpty() && !TextUtils.isEmpty(this.textToDraw)) {
            calculateOffsets(0.0f);
            this.textureAscent = this.textPaint.ascent();
            this.textureDescent = this.textPaint.descent();
            int w = Math.round(this.textPaint.measureText(this.textToDraw, 0, this.textToDraw.length()));
            int h = Math.round(this.textureDescent - this.textureAscent);
            if (w > 0 && h > 0) {
                this.expandedTitleTexture = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                new Canvas(this.expandedTitleTexture);
                c.drawText(this.textToDraw, 0, this.textToDraw.length(), 0.0f, ((float) h) - this.textPaint.descent(), this.textPaint);
                if (this.texturePaint == null) {
                    new Paint(3);
                    this.texturePaint = paint;
                }
            }
        }
    }

    public void recalculate() {
        if (this.view.getHeight() > 0 && this.view.getWidth() > 0) {
            calculateBaseOffsets();
            calculateCurrentOffsets();
        }
    }

    public void setText(CharSequence charSequence) {
        CharSequence text2 = charSequence;
        if (text2 == null || !text2.equals(this.text)) {
            this.text = text2;
            this.textToDraw = null;
            clearTexture();
            recalculate();
        }
    }

    public CharSequence getText() {
        return this.text;
    }

    private void clearTexture() {
        if (this.expandedTitleTexture != null) {
            this.expandedTitleTexture.recycle();
            this.expandedTitleTexture = null;
        }
    }

    private static boolean isClose(float value, float targetValue) {
        return Math.abs(value - targetValue) < 0.001f;
    }

    public ColorStateList getExpandedTextColor() {
        return this.expandedTextColor;
    }

    public ColorStateList getCollapsedTextColor() {
        return this.collapsedTextColor;
    }

    private static int blendColors(int i, int i2, float f) {
        int color1 = i;
        int color2 = i2;
        float ratio = f;
        float inverseRatio = 1.0f - ratio;
        return Color.argb((int) ((((float) Color.alpha(color1)) * inverseRatio) + (((float) Color.alpha(color2)) * ratio)), (int) ((((float) Color.red(color1)) * inverseRatio) + (((float) Color.red(color2)) * ratio)), (int) ((((float) Color.green(color1)) * inverseRatio) + (((float) Color.green(color2)) * ratio)), (int) ((((float) Color.blue(color1)) * inverseRatio) + (((float) Color.blue(color2)) * ratio)));
    }

    private static float lerp(float f, float f2, float f3, TimeInterpolator timeInterpolator) {
        float startValue = f;
        float endValue = f2;
        float fraction = f3;
        TimeInterpolator interpolator = timeInterpolator;
        if (interpolator != null) {
            fraction = interpolator.getInterpolation(fraction);
        }
        return AnimationUtils.lerp(startValue, endValue, fraction);
    }

    private static boolean rectEquals(Rect rect, int left, int top, int right, int bottom) {
        Rect r = rect;
        return r.left == left && r.top == top && r.right == right && r.bottom == bottom;
    }
}
