package android.support.design.chip;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.AnimatorRes;
import android.support.annotation.AttrRes;
import android.support.annotation.BoolRes;
import android.support.annotation.C0015Px;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.annotation.XmlRes;
import android.support.design.C0064R;
import android.support.design.animation.MotionSpec;
import android.support.design.canvas.CanvasCompat;
import android.support.design.drawable.DrawableUtils;
import android.support.design.internal.ThemeEnforcement;
import android.support.design.resources.MaterialResources;
import android.support.design.resources.TextAppearance;
import android.support.design.ripple.RippleUtils;
import android.support.p000v4.content.res.ResourcesCompat;
import android.support.p000v4.graphics.ColorUtils;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.graphics.drawable.TintAwareDrawable;
import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.text.BidiFormatter;
import android.support.p003v7.content.res.AppCompatResources;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ChipDrawable extends Drawable implements TintAwareDrawable, Drawable.Callback {
    private static final boolean DEBUG = false;
    private static final int[] DEFAULT_STATE = {16842910};
    private static final String NAMESPACE_APP = "http://schemas.android.com/apk/res-auto";
    private int alpha = 255;
    private boolean checkable;
    @Nullable
    private Drawable checkedIcon;
    private boolean checkedIconVisible;
    @Nullable
    private ColorStateList chipBackgroundColor;
    private float chipCornerRadius;
    private float chipEndPadding;
    @Nullable
    private Drawable chipIcon;
    private float chipIconSize;
    @Nullable
    private ColorStateList chipIconTint;
    private boolean chipIconVisible;
    private float chipMinHeight;
    private final Paint chipPaint;
    private float chipStartPadding;
    @Nullable
    private ColorStateList chipStrokeColor;
    private float chipStrokeWidth;
    @Nullable
    private Drawable closeIcon;
    @Nullable
    private CharSequence closeIconContentDescription;
    private float closeIconEndPadding;
    private float closeIconSize;
    private float closeIconStartPadding;
    private int[] closeIconStateSet;
    @Nullable
    private ColorStateList closeIconTint;
    private boolean closeIconVisible;
    @Nullable
    private ColorFilter colorFilter;
    @Nullable
    private ColorStateList compatRippleColor;
    private final Context context;
    private boolean currentChecked;
    @ColorInt
    private int currentChipBackgroundColor;
    @ColorInt
    private int currentChipStrokeColor;
    @ColorInt
    private int currentCompatRippleColor;
    @ColorInt
    private int currentTextColor;
    @ColorInt
    private int currentTint;
    @Nullable
    private final Paint debugPaint;
    private WeakReference<Delegate> delegate;
    private final ResourcesCompat.FontCallback fontCallback;
    private final Paint.FontMetrics fontMetrics;
    @Nullable
    private MotionSpec hideMotionSpec;
    private float iconEndPadding;
    private float iconStartPadding;
    private int maxWidth;
    private final PointF pointF;
    @Nullable
    private CharSequence rawText;
    private final RectF rectF;
    @Nullable
    private ColorStateList rippleColor;
    private boolean shouldDrawText;
    @Nullable
    private MotionSpec showMotionSpec;
    @Nullable
    private TextAppearance textAppearance;
    private float textEndPadding;
    private final TextPaint textPaint;
    private float textStartPadding;
    private float textWidth;
    private boolean textWidthDirty;
    @Nullable
    private ColorStateList tint;
    @Nullable
    private PorterDuffColorFilter tintFilter;
    @Nullable
    private PorterDuff.Mode tintMode = PorterDuff.Mode.SRC_IN;
    private TextUtils.TruncateAt truncateAt;
    @Nullable
    private CharSequence unicodeWrappedText;
    private boolean useCompatRipple;

    public interface Delegate {
        void onChipDrawableSizeChange();
    }

    static /* synthetic */ boolean access$002(ChipDrawable x0, boolean x1) {
        boolean z = x1;
        boolean z2 = z;
        x0.textWidthDirty = z2;
        return z;
    }

    public static ChipDrawable createFromAttributes(Context context2, AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        ChipDrawable chipDrawable;
        new ChipDrawable(context2);
        ChipDrawable chip = chipDrawable;
        chip.loadFromAttributes(attrs, defStyleAttr, defStyleRes);
        return chip;
    }

    public static ChipDrawable createFromResource(Context context2, @XmlRes int i) {
        Resources.NotFoundException notFoundException;
        StringBuilder sb;
        int type;
        Throwable th;
        Throwable th2;
        Context context3 = context2;
        int id = i;
        try {
            XmlPullParser parser = context3.getResources().getXml(id);
            do {
                type = parser.next();
                if (type == 2 || type == 1) {
                }
                type = parser.next();
                break;
            } while (type == 1);
            if (type != 2) {
                Throwable th3 = th2;
                new XmlPullParserException("No start tag found");
                throw th3;
            } else if (!TextUtils.equals(parser.getName(), "chip")) {
                Throwable th4 = th;
                new XmlPullParserException("Must have a <chip> start tag");
                throw th4;
            } else {
                AttributeSet attrs = Xml.asAttributeSet(parser);
                int style = attrs.getStyleAttribute();
                if (style == 0) {
                    style = C0064R.C0068style.Widget_MaterialComponents_Chip_Entry;
                }
                return createFromAttributes(context3, attrs, C0064R.attr.chipStandaloneStyle, style);
            }
        } catch (XmlPullParserException e) {
            e = e;
            new StringBuilder();
            new Resources.NotFoundException(sb.append("Can't load chip resource ID #0x").append(Integer.toHexString(id)).toString());
            Resources.NotFoundException exception = notFoundException;
            Throwable initCause = exception.initCause(e);
            throw exception;
        } catch (IOException e2) {
            e = e2;
            new StringBuilder();
            new Resources.NotFoundException(sb.append("Can't load chip resource ID #0x").append(Integer.toHexString(id)).toString());
            Resources.NotFoundException exception2 = notFoundException;
            Throwable initCause2 = exception2.initCause(e);
            throw exception2;
        }
    }

    private ChipDrawable(Context context2) {
        ResourcesCompat.FontCallback fontCallback2;
        TextPaint textPaint2;
        Paint paint;
        Paint.FontMetrics fontMetrics2;
        RectF rectF2;
        PointF pointF2;
        WeakReference<Delegate> weakReference;
        Context context3 = context2;
        new ResourcesCompat.FontCallback(this) {
            final /* synthetic */ ChipDrawable this$0;

            {
                this.this$0 = this$0;
            }

            public void onFontRetrieved(@NonNull Typeface typeface) {
                Typeface typeface2 = typeface;
                boolean access$002 = ChipDrawable.access$002(this.this$0, true);
                this.this$0.onSizeChange();
                this.this$0.invalidateSelf();
            }

            public void onFontRetrievalFailed(int reason) {
            }
        };
        this.fontCallback = fontCallback2;
        new TextPaint(1);
        this.textPaint = textPaint2;
        new Paint(1);
        this.chipPaint = paint;
        new Paint.FontMetrics();
        this.fontMetrics = fontMetrics2;
        new RectF();
        this.rectF = rectF2;
        new PointF();
        this.pointF = pointF2;
        new WeakReference<>((Object) null);
        this.delegate = weakReference;
        this.textWidthDirty = true;
        this.context = context3;
        this.rawText = "";
        this.textPaint.density = context3.getResources().getDisplayMetrics().density;
        this.debugPaint = null;
        if (this.debugPaint != null) {
            this.debugPaint.setStyle(Paint.Style.STROKE);
        }
        boolean state = setState(DEFAULT_STATE);
        boolean closeIconState = setCloseIconState(DEFAULT_STATE);
        this.shouldDrawText = true;
    }

    private void loadFromAttributes(AttributeSet attributeSet, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        AttributeSet attrs = attributeSet;
        TypedArray a = ThemeEnforcement.obtainStyledAttributes(this.context, attrs, C0064R.styleable.Chip, defStyleAttr, defStyleRes, new int[0]);
        setChipBackgroundColor(MaterialResources.getColorStateList(this.context, a, C0064R.styleable.Chip_chipBackgroundColor));
        setChipMinHeight(a.getDimension(C0064R.styleable.Chip_chipMinHeight, 0.0f));
        setChipCornerRadius(a.getDimension(C0064R.styleable.Chip_chipCornerRadius, 0.0f));
        setChipStrokeColor(MaterialResources.getColorStateList(this.context, a, C0064R.styleable.Chip_chipStrokeColor));
        setChipStrokeWidth(a.getDimension(C0064R.styleable.Chip_chipStrokeWidth, 0.0f));
        setRippleColor(MaterialResources.getColorStateList(this.context, a, C0064R.styleable.Chip_rippleColor));
        setText(a.getText(C0064R.styleable.Chip_android_text));
        setTextAppearance(MaterialResources.getTextAppearance(this.context, a, C0064R.styleable.Chip_android_textAppearance));
        switch (a.getInt(C0064R.styleable.Chip_android_ellipsize, 0)) {
            case 1:
                setEllipsize(TextUtils.TruncateAt.START);
                break;
            case 2:
                setEllipsize(TextUtils.TruncateAt.MIDDLE);
                break;
            case 3:
                setEllipsize(TextUtils.TruncateAt.END);
                break;
        }
        setChipIconVisible(a.getBoolean(C0064R.styleable.Chip_chipIconVisible, false));
        if (!(attrs == null || attrs.getAttributeValue(NAMESPACE_APP, "chipIconEnabled") == null || attrs.getAttributeValue(NAMESPACE_APP, "chipIconVisible") != null)) {
            setChipIconVisible(a.getBoolean(C0064R.styleable.Chip_chipIconEnabled, false));
        }
        setChipIcon(MaterialResources.getDrawable(this.context, a, C0064R.styleable.Chip_chipIcon));
        setChipIconTint(MaterialResources.getColorStateList(this.context, a, C0064R.styleable.Chip_chipIconTint));
        setChipIconSize(a.getDimension(C0064R.styleable.Chip_chipIconSize, 0.0f));
        setCloseIconVisible(a.getBoolean(C0064R.styleable.Chip_closeIconVisible, false));
        if (!(attrs == null || attrs.getAttributeValue(NAMESPACE_APP, "closeIconEnabled") == null || attrs.getAttributeValue(NAMESPACE_APP, "closeIconVisible") != null)) {
            setCloseIconVisible(a.getBoolean(C0064R.styleable.Chip_closeIconEnabled, false));
        }
        setCloseIcon(MaterialResources.getDrawable(this.context, a, C0064R.styleable.Chip_closeIcon));
        setCloseIconTint(MaterialResources.getColorStateList(this.context, a, C0064R.styleable.Chip_closeIconTint));
        setCloseIconSize(a.getDimension(C0064R.styleable.Chip_closeIconSize, 0.0f));
        setCheckable(a.getBoolean(C0064R.styleable.Chip_android_checkable, false));
        setCheckedIconVisible(a.getBoolean(C0064R.styleable.Chip_checkedIconVisible, false));
        if (!(attrs == null || attrs.getAttributeValue(NAMESPACE_APP, "checkedIconEnabled") == null || attrs.getAttributeValue(NAMESPACE_APP, "checkedIconVisible") != null)) {
            setCheckedIconVisible(a.getBoolean(C0064R.styleable.Chip_checkedIconEnabled, false));
        }
        setCheckedIcon(MaterialResources.getDrawable(this.context, a, C0064R.styleable.Chip_checkedIcon));
        setShowMotionSpec(MotionSpec.createFromAttribute(this.context, a, C0064R.styleable.Chip_showMotionSpec));
        setHideMotionSpec(MotionSpec.createFromAttribute(this.context, a, C0064R.styleable.Chip_hideMotionSpec));
        setChipStartPadding(a.getDimension(C0064R.styleable.Chip_chipStartPadding, 0.0f));
        setIconStartPadding(a.getDimension(C0064R.styleable.Chip_iconStartPadding, 0.0f));
        setIconEndPadding(a.getDimension(C0064R.styleable.Chip_iconEndPadding, 0.0f));
        setTextStartPadding(a.getDimension(C0064R.styleable.Chip_textStartPadding, 0.0f));
        setTextEndPadding(a.getDimension(C0064R.styleable.Chip_textEndPadding, 0.0f));
        setCloseIconStartPadding(a.getDimension(C0064R.styleable.Chip_closeIconStartPadding, 0.0f));
        setCloseIconEndPadding(a.getDimension(C0064R.styleable.Chip_closeIconEndPadding, 0.0f));
        setChipEndPadding(a.getDimension(C0064R.styleable.Chip_chipEndPadding, 0.0f));
        setMaxWidth(a.getDimensionPixelSize(C0064R.styleable.Chip_android_maxWidth, Integer.MAX_VALUE));
        a.recycle();
    }

    public void setUseCompatRipple(boolean z) {
        boolean useCompatRipple2 = z;
        if (this.useCompatRipple != useCompatRipple2) {
            this.useCompatRipple = useCompatRipple2;
            updateCompatRippleColor();
            boolean onStateChange = onStateChange(getState());
        }
    }

    public boolean getUseCompatRipple() {
        return this.useCompatRipple;
    }

    public void setDelegate(@Nullable Delegate delegate2) {
        WeakReference<Delegate> weakReference;
        WeakReference<Delegate> weakReference2 = weakReference;
        new WeakReference<>(delegate2);
        this.delegate = weakReference2;
    }

    /* access modifiers changed from: protected */
    public void onSizeChange() {
        Delegate delegate2 = (Delegate) this.delegate.get();
        if (delegate2 != null) {
            delegate2.onChipDrawableSizeChange();
        }
    }

    public void getChipTouchBounds(RectF bounds) {
        calculateChipTouchBounds(getBounds(), bounds);
    }

    public void getCloseIconTouchBounds(RectF bounds) {
        calculateCloseIconTouchBounds(getBounds(), bounds);
    }

    public int getIntrinsicWidth() {
        return Math.min(Math.round(this.chipStartPadding + calculateChipIconWidth() + this.textStartPadding + getTextWidth() + this.textEndPadding + calculateCloseIconWidth() + this.chipEndPadding), this.maxWidth);
    }

    public int getIntrinsicHeight() {
        return (int) this.chipMinHeight;
    }

    private boolean showsChipIcon() {
        return this.chipIconVisible && this.chipIcon != null;
    }

    private boolean showsCheckedIcon() {
        return this.checkedIconVisible && this.checkedIcon != null && this.currentChecked;
    }

    private boolean showsCloseIcon() {
        return this.closeIconVisible && this.closeIcon != null;
    }

    private boolean canShowCheckedIcon() {
        return this.checkedIconVisible && this.checkedIcon != null && this.checkable;
    }

    /* access modifiers changed from: package-private */
    public float calculateChipIconWidth() {
        if (showsChipIcon() || showsCheckedIcon()) {
            return this.iconStartPadding + this.chipIconSize + this.iconEndPadding;
        }
        return 0.0f;
    }

    private float getTextWidth() {
        if (!this.textWidthDirty) {
            return this.textWidth;
        }
        this.textWidth = calculateTextWidth(this.unicodeWrappedText);
        this.textWidthDirty = false;
        return this.textWidth;
    }

    private float calculateTextWidth(@Nullable CharSequence charSequence) {
        CharSequence charSequence2 = charSequence;
        if (charSequence2 == null) {
            return 0.0f;
        }
        return this.textPaint.measureText(charSequence2, 0, charSequence2.length());
    }

    private float calculateCloseIconWidth() {
        if (showsCloseIcon()) {
            return this.closeIconStartPadding + this.closeIconSize + this.closeIconEndPadding;
        }
        return 0.0f;
    }

    public void draw(@NonNull Canvas canvas) {
        Canvas canvas2 = canvas;
        Rect bounds = getBounds();
        if (!bounds.isEmpty() && getAlpha() != 0) {
            int saveCount = 0;
            if (this.alpha < 255) {
                saveCount = CanvasCompat.saveLayerAlpha(canvas2, (float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.alpha);
            }
            drawChipBackground(canvas2, bounds);
            drawChipStroke(canvas2, bounds);
            drawCompatRipple(canvas2, bounds);
            drawChipIcon(canvas2, bounds);
            drawCheckedIcon(canvas2, bounds);
            if (this.shouldDrawText) {
                drawText(canvas2, bounds);
            }
            drawCloseIcon(canvas2, bounds);
            drawDebug(canvas2, bounds);
            if (this.alpha < 255) {
                canvas2.restoreToCount(saveCount);
            }
        }
    }

    private void drawChipBackground(@NonNull Canvas canvas, Rect bounds) {
        this.chipPaint.setColor(this.currentChipBackgroundColor);
        this.chipPaint.setStyle(Paint.Style.FILL);
        ColorFilter colorFilter2 = this.chipPaint.setColorFilter(getTintColorFilter());
        this.rectF.set(bounds);
        canvas.drawRoundRect(this.rectF, this.chipCornerRadius, this.chipCornerRadius, this.chipPaint);
    }

    private void drawChipStroke(@NonNull Canvas canvas, Rect rect) {
        Canvas canvas2 = canvas;
        Rect bounds = rect;
        if (this.chipStrokeWidth > 0.0f) {
            this.chipPaint.setColor(this.currentChipStrokeColor);
            this.chipPaint.setStyle(Paint.Style.STROKE);
            ColorFilter colorFilter2 = this.chipPaint.setColorFilter(getTintColorFilter());
            this.rectF.set(((float) bounds.left) + (this.chipStrokeWidth / 2.0f), ((float) bounds.top) + (this.chipStrokeWidth / 2.0f), ((float) bounds.right) - (this.chipStrokeWidth / 2.0f), ((float) bounds.bottom) - (this.chipStrokeWidth / 2.0f));
            float strokeCornerRadius = this.chipCornerRadius - (this.chipStrokeWidth / 2.0f);
            canvas2.drawRoundRect(this.rectF, strokeCornerRadius, strokeCornerRadius, this.chipPaint);
        }
    }

    private void drawCompatRipple(@NonNull Canvas canvas, Rect bounds) {
        this.chipPaint.setColor(this.currentCompatRippleColor);
        this.chipPaint.setStyle(Paint.Style.FILL);
        this.rectF.set(bounds);
        canvas.drawRoundRect(this.rectF, this.chipCornerRadius, this.chipCornerRadius, this.chipPaint);
    }

    private void drawChipIcon(@NonNull Canvas canvas, Rect rect) {
        Canvas canvas2 = canvas;
        Rect bounds = rect;
        if (showsChipIcon()) {
            calculateChipIconBounds(bounds, this.rectF);
            float tx = this.rectF.left;
            float ty = this.rectF.top;
            canvas2.translate(tx, ty);
            this.chipIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            this.chipIcon.draw(canvas2);
            canvas2.translate(-tx, -ty);
        }
    }

    private void drawCheckedIcon(@NonNull Canvas canvas, Rect rect) {
        Canvas canvas2 = canvas;
        Rect bounds = rect;
        if (showsCheckedIcon()) {
            calculateChipIconBounds(bounds, this.rectF);
            float tx = this.rectF.left;
            float ty = this.rectF.top;
            canvas2.translate(tx, ty);
            this.checkedIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            this.checkedIcon.draw(canvas2);
            canvas2.translate(-tx, -ty);
        }
    }

    private void drawText(@NonNull Canvas canvas, Rect rect) {
        Canvas canvas2 = canvas;
        Rect bounds = rect;
        if (this.unicodeWrappedText != null) {
            Paint.Align align = calculateTextOriginAndAlignment(bounds, this.pointF);
            calculateTextBounds(bounds, this.rectF);
            if (this.textAppearance != null) {
                this.textPaint.drawableState = getState();
                this.textAppearance.updateDrawState(this.context, this.textPaint, this.fontCallback);
            }
            this.textPaint.setTextAlign(align);
            boolean clip = Math.round(getTextWidth()) > Math.round(this.rectF.width());
            int saveCount = 0;
            if (clip) {
                saveCount = canvas2.save();
                boolean clipRect = canvas2.clipRect(this.rectF);
            }
            CharSequence finalText = this.unicodeWrappedText;
            if (clip && this.truncateAt != null) {
                finalText = TextUtils.ellipsize(this.unicodeWrappedText, this.textPaint, this.rectF.width(), this.truncateAt);
            }
            canvas2.drawText(finalText, 0, finalText.length(), this.pointF.x, this.pointF.y, this.textPaint);
            if (clip) {
                canvas2.restoreToCount(saveCount);
            }
        }
    }

    private void drawCloseIcon(@NonNull Canvas canvas, Rect rect) {
        Canvas canvas2 = canvas;
        Rect bounds = rect;
        if (showsCloseIcon()) {
            calculateCloseIconBounds(bounds, this.rectF);
            float tx = this.rectF.left;
            float ty = this.rectF.top;
            canvas2.translate(tx, ty);
            this.closeIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            this.closeIcon.draw(canvas2);
            canvas2.translate(-tx, -ty);
        }
    }

    private void drawDebug(@NonNull Canvas canvas, Rect rect) {
        Canvas canvas2 = canvas;
        Rect bounds = rect;
        if (this.debugPaint != null) {
            this.debugPaint.setColor(ColorUtils.setAlphaComponent(-16777216, 127));
            canvas2.drawRect(bounds, this.debugPaint);
            if (showsChipIcon() || showsCheckedIcon()) {
                calculateChipIconBounds(bounds, this.rectF);
                canvas2.drawRect(this.rectF, this.debugPaint);
            }
            if (this.unicodeWrappedText != null) {
                canvas2.drawLine((float) bounds.left, bounds.exactCenterY(), (float) bounds.right, bounds.exactCenterY(), this.debugPaint);
            }
            if (showsCloseIcon()) {
                calculateCloseIconBounds(bounds, this.rectF);
                canvas2.drawRect(this.rectF, this.debugPaint);
            }
            this.debugPaint.setColor(ColorUtils.setAlphaComponent(SupportMenu.CATEGORY_MASK, 127));
            calculateChipTouchBounds(bounds, this.rectF);
            canvas2.drawRect(this.rectF, this.debugPaint);
            this.debugPaint.setColor(ColorUtils.setAlphaComponent(-16711936, 127));
            calculateCloseIconTouchBounds(bounds, this.rectF);
            canvas2.drawRect(this.rectF, this.debugPaint);
        }
    }

    private void calculateChipIconBounds(Rect rect, RectF rectF2) {
        Rect bounds = rect;
        RectF outBounds = rectF2;
        outBounds.setEmpty();
        if (showsChipIcon() || showsCheckedIcon()) {
            float offsetFromStart = this.chipStartPadding + this.iconStartPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                outBounds.left = ((float) bounds.left) + offsetFromStart;
                outBounds.right = outBounds.left + this.chipIconSize;
            } else {
                outBounds.right = ((float) bounds.right) - offsetFromStart;
                outBounds.left = outBounds.right - this.chipIconSize;
            }
            outBounds.top = bounds.exactCenterY() - (this.chipIconSize / 2.0f);
            outBounds.bottom = outBounds.top + this.chipIconSize;
        }
    }

    /* access modifiers changed from: package-private */
    public Paint.Align calculateTextOriginAndAlignment(Rect rect, PointF pointF2) {
        Rect bounds = rect;
        PointF pointF3 = pointF2;
        pointF3.set(0.0f, 0.0f);
        Paint.Align align = Paint.Align.LEFT;
        if (this.unicodeWrappedText != null) {
            float offsetFromStart = this.chipStartPadding + calculateChipIconWidth() + this.textStartPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                pointF3.x = ((float) bounds.left) + offsetFromStart;
                align = Paint.Align.LEFT;
            } else {
                pointF3.x = ((float) bounds.right) - offsetFromStart;
                align = Paint.Align.RIGHT;
            }
            pointF3.y = ((float) bounds.centerY()) - calculateTextCenterFromBaseline();
        }
        return align;
    }

    private float calculateTextCenterFromBaseline() {
        float fontMetrics2 = this.textPaint.getFontMetrics(this.fontMetrics);
        return (this.fontMetrics.descent + this.fontMetrics.ascent) / 2.0f;
    }

    private void calculateTextBounds(Rect rect, RectF rectF2) {
        Rect bounds = rect;
        RectF outBounds = rectF2;
        outBounds.setEmpty();
        if (this.unicodeWrappedText != null) {
            float offsetFromStart = this.chipStartPadding + calculateChipIconWidth() + this.textStartPadding;
            float offsetFromEnd = this.chipEndPadding + calculateCloseIconWidth() + this.textEndPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                outBounds.left = ((float) bounds.left) + offsetFromStart;
                outBounds.right = ((float) bounds.right) - offsetFromEnd;
            } else {
                outBounds.left = ((float) bounds.left) + offsetFromEnd;
                outBounds.right = ((float) bounds.right) - offsetFromStart;
            }
            outBounds.top = (float) bounds.top;
            outBounds.bottom = (float) bounds.bottom;
        }
    }

    private void calculateCloseIconBounds(Rect rect, RectF rectF2) {
        Rect bounds = rect;
        RectF outBounds = rectF2;
        outBounds.setEmpty();
        if (showsCloseIcon()) {
            float offsetFromEnd = this.chipEndPadding + this.closeIconEndPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                outBounds.right = ((float) bounds.right) - offsetFromEnd;
                outBounds.left = outBounds.right - this.closeIconSize;
            } else {
                outBounds.left = ((float) bounds.left) + offsetFromEnd;
                outBounds.right = outBounds.left + this.closeIconSize;
            }
            outBounds.top = bounds.exactCenterY() - (this.closeIconSize / 2.0f);
            outBounds.bottom = outBounds.top + this.closeIconSize;
        }
    }

    private void calculateChipTouchBounds(Rect rect, RectF rectF2) {
        Rect bounds = rect;
        RectF outBounds = rectF2;
        outBounds.set(bounds);
        if (showsCloseIcon()) {
            float offsetFromEnd = this.chipEndPadding + this.closeIconEndPadding + this.closeIconSize + this.closeIconStartPadding + this.textEndPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                outBounds.right = ((float) bounds.right) - offsetFromEnd;
                return;
            }
            outBounds.left = ((float) bounds.left) + offsetFromEnd;
        }
    }

    private void calculateCloseIconTouchBounds(Rect rect, RectF rectF2) {
        Rect bounds = rect;
        RectF outBounds = rectF2;
        outBounds.setEmpty();
        if (showsCloseIcon()) {
            float offsetFromEnd = this.chipEndPadding + this.closeIconEndPadding + this.closeIconSize + this.closeIconStartPadding + this.textEndPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                outBounds.right = (float) bounds.right;
                outBounds.left = outBounds.right - offsetFromEnd;
            } else {
                outBounds.left = (float) bounds.left;
                outBounds.right = ((float) bounds.left) + offsetFromEnd;
            }
            outBounds.top = (float) bounds.top;
            outBounds.bottom = (float) bounds.bottom;
        }
    }

    public boolean isStateful() {
        boolean z;
        if (isStateful(this.chipBackgroundColor) || isStateful(this.chipStrokeColor) || ((this.useCompatRipple && isStateful(this.compatRippleColor)) || isStateful(this.textAppearance) || canShowCheckedIcon() || isStateful(this.chipIcon) || isStateful(this.checkedIcon) || isStateful(this.tint))) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public boolean isCloseIconStateful() {
        return isStateful(this.closeIcon);
    }

    public boolean setCloseIconState(@NonNull int[] iArr) {
        int[] stateSet = iArr;
        if (!Arrays.equals(this.closeIconStateSet, stateSet)) {
            this.closeIconStateSet = stateSet;
            if (showsCloseIcon()) {
                return onStateChange(getState(), stateSet);
            }
        }
        return false;
    }

    @NonNull
    public int[] getCloseIconState() {
        return this.closeIconStateSet;
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] state) {
        return onStateChange(state, getCloseIconState());
    }

    private boolean onStateChange(int[] iArr, int[] iArr2) {
        int[] chipState = iArr;
        int[] closeIconState = iArr2;
        boolean invalidate = super.onStateChange(chipState);
        boolean sizeChanged = false;
        int newChipBackgroundColor = this.chipBackgroundColor != null ? this.chipBackgroundColor.getColorForState(chipState, this.currentChipBackgroundColor) : 0;
        if (this.currentChipBackgroundColor != newChipBackgroundColor) {
            this.currentChipBackgroundColor = newChipBackgroundColor;
            invalidate = true;
        }
        int newChipStrokeColor = this.chipStrokeColor != null ? this.chipStrokeColor.getColorForState(chipState, this.currentChipStrokeColor) : 0;
        if (this.currentChipStrokeColor != newChipStrokeColor) {
            this.currentChipStrokeColor = newChipStrokeColor;
            invalidate = true;
        }
        int newCompatRippleColor = this.compatRippleColor != null ? this.compatRippleColor.getColorForState(chipState, this.currentCompatRippleColor) : 0;
        if (this.currentCompatRippleColor != newCompatRippleColor) {
            this.currentCompatRippleColor = newCompatRippleColor;
            if (this.useCompatRipple) {
                invalidate = true;
            }
        }
        int newTextColor = (this.textAppearance == null || this.textAppearance.textColor == null) ? 0 : this.textAppearance.textColor.getColorForState(chipState, this.currentTextColor);
        if (this.currentTextColor != newTextColor) {
            this.currentTextColor = newTextColor;
            invalidate = true;
        }
        boolean newChecked = hasState(getState(), 16842912) && this.checkable;
        if (!(this.currentChecked == newChecked || this.checkedIcon == null)) {
            float oldChipIconWidth = calculateChipIconWidth();
            this.currentChecked = newChecked;
            invalidate = true;
            if (oldChipIconWidth != calculateChipIconWidth()) {
                sizeChanged = true;
            }
        }
        int newTint = this.tint != null ? this.tint.getColorForState(chipState, this.currentTint) : 0;
        if (this.currentTint != newTint) {
            this.currentTint = newTint;
            this.tintFilter = DrawableUtils.updateTintFilter(this, this.tint, this.tintMode);
            invalidate = true;
        }
        if (isStateful(this.chipIcon)) {
            invalidate |= this.chipIcon.setState(chipState);
        }
        if (isStateful(this.checkedIcon)) {
            invalidate |= this.checkedIcon.setState(chipState);
        }
        if (isStateful(this.closeIcon)) {
            invalidate |= this.closeIcon.setState(closeIconState);
        }
        if (invalidate) {
            invalidateSelf();
        }
        if (sizeChanged) {
            onSizeChange();
        }
        return invalidate;
    }

    private static boolean isStateful(@Nullable ColorStateList colorStateList) {
        ColorStateList colorStateList2 = colorStateList;
        return colorStateList2 != null && colorStateList2.isStateful();
    }

    private static boolean isStateful(@Nullable Drawable drawable) {
        Drawable drawable2 = drawable;
        return drawable2 != null && drawable2.isStateful();
    }

    private static boolean isStateful(@Nullable TextAppearance textAppearance2) {
        TextAppearance textAppearance3 = textAppearance2;
        return (textAppearance3 == null || textAppearance3.textColor == null || !textAppearance3.textColor.isStateful()) ? false : true;
    }

    @TargetApi(23)
    public boolean onLayoutDirectionChanged(int i) {
        int layoutDirection = i;
        boolean invalidate = super.onLayoutDirectionChanged(layoutDirection);
        if (showsChipIcon()) {
            invalidate |= this.chipIcon.setLayoutDirection(layoutDirection);
        }
        if (showsCheckedIcon()) {
            invalidate |= this.checkedIcon.setLayoutDirection(layoutDirection);
        }
        if (showsCloseIcon()) {
            invalidate |= this.closeIcon.setLayoutDirection(layoutDirection);
        }
        if (invalidate) {
            invalidateSelf();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        int level = i;
        boolean invalidate = super.onLevelChange(level);
        if (showsChipIcon()) {
            invalidate |= this.chipIcon.setLevel(level);
        }
        if (showsCheckedIcon()) {
            invalidate |= this.checkedIcon.setLevel(level);
        }
        if (showsCloseIcon()) {
            invalidate |= this.closeIcon.setLevel(level);
        }
        if (invalidate) {
            invalidateSelf();
        }
        return invalidate;
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = z;
        boolean restart = z2;
        boolean invalidate = super.setVisible(visible, restart);
        if (showsChipIcon()) {
            invalidate |= this.chipIcon.setVisible(visible, restart);
        }
        if (showsCheckedIcon()) {
            invalidate |= this.checkedIcon.setVisible(visible, restart);
        }
        if (showsCloseIcon()) {
            invalidate |= this.closeIcon.setVisible(visible, restart);
        }
        if (invalidate) {
            invalidateSelf();
        }
        return invalidate;
    }

    public void setAlpha(int i) {
        int alpha2 = i;
        if (this.alpha != alpha2) {
            this.alpha = alpha2;
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.alpha;
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter2) {
        ColorFilter colorFilter3 = colorFilter2;
        if (this.colorFilter != colorFilter3) {
            this.colorFilter = colorFilter3;
            invalidateSelf();
        }
    }

    @Nullable
    public ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    public void setTintList(@Nullable ColorStateList colorStateList) {
        ColorStateList tint2 = colorStateList;
        if (this.tint != tint2) {
            this.tint = tint2;
            boolean onStateChange = onStateChange(getState());
        }
    }

    public void setTintMode(@NonNull PorterDuff.Mode mode) {
        PorterDuff.Mode tintMode2 = mode;
        if (this.tintMode != tintMode2) {
            this.tintMode = tintMode2;
            this.tintFilter = DrawableUtils.updateTintFilter(this, this.tint, tintMode2);
            invalidateSelf();
        }
    }

    public int getOpacity() {
        return -3;
    }

    @TargetApi(21)
    public void getOutline(@NonNull Outline outline) {
        Outline outline2 = outline;
        Rect bounds = getBounds();
        if (!bounds.isEmpty()) {
            outline2.setRoundRect(bounds, this.chipCornerRadius);
        } else {
            outline2.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.chipCornerRadius);
        }
        outline2.setAlpha(((float) getAlpha()) / 255.0f);
    }

    public void invalidateDrawable(@NonNull Drawable drawable) {
        Drawable drawable2 = drawable;
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j) {
        Drawable drawable2 = drawable;
        Runnable what = runnable;
        long when = j;
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, what, when);
        }
    }

    public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
        Drawable drawable2 = drawable;
        Runnable what = runnable;
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, what);
        }
    }

    private void unapplyChildDrawable(@Nullable Drawable drawable) {
        Drawable drawable2 = drawable;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback) null);
        }
    }

    private void applyChildDrawable(@Nullable Drawable drawable) {
        Drawable drawable2 = drawable;
        if (drawable2 != null) {
            drawable2.setCallback(this);
            boolean layoutDirection = DrawableCompat.setLayoutDirection(drawable2, DrawableCompat.getLayoutDirection(this));
            boolean level = drawable2.setLevel(getLevel());
            boolean visible = drawable2.setVisible(isVisible(), false);
            if (drawable2 == this.closeIcon) {
                if (drawable2.isStateful()) {
                    boolean state = drawable2.setState(getCloseIconState());
                }
                DrawableCompat.setTintList(drawable2, this.closeIconTint);
            } else if (drawable2.isStateful()) {
                boolean state2 = drawable2.setState(getState());
            }
        }
    }

    @Nullable
    private ColorFilter getTintColorFilter() {
        return this.colorFilter != null ? this.colorFilter : this.tintFilter;
    }

    private void updateCompatRippleColor() {
        this.compatRippleColor = this.useCompatRipple ? RippleUtils.convertToRippleDrawableColor(this.rippleColor) : null;
    }

    private static boolean hasState(@Nullable int[] iArr, @AttrRes int i) {
        int[] stateSet = iArr;
        int state = i;
        if (stateSet == null) {
            return false;
        }
        int[] iArr2 = stateSet;
        int length = iArr2.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (iArr2[i2] == state) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public ColorStateList getChipBackgroundColor() {
        return this.chipBackgroundColor;
    }

    public void setChipBackgroundColorResource(@ColorRes int id) {
        setChipBackgroundColor(AppCompatResources.getColorStateList(this.context, id));
    }

    public void setChipBackgroundColor(@Nullable ColorStateList colorStateList) {
        ColorStateList chipBackgroundColor2 = colorStateList;
        if (this.chipBackgroundColor != chipBackgroundColor2) {
            this.chipBackgroundColor = chipBackgroundColor2;
            boolean onStateChange = onStateChange(getState());
        }
    }

    public float getChipMinHeight() {
        return this.chipMinHeight;
    }

    public void setChipMinHeightResource(@DimenRes int id) {
        setChipMinHeight(this.context.getResources().getDimension(id));
    }

    public void setChipMinHeight(float f) {
        float chipMinHeight2 = f;
        if (this.chipMinHeight != chipMinHeight2) {
            this.chipMinHeight = chipMinHeight2;
            invalidateSelf();
            onSizeChange();
        }
    }

    public float getChipCornerRadius() {
        return this.chipCornerRadius;
    }

    public void setChipCornerRadiusResource(@DimenRes int id) {
        setChipCornerRadius(this.context.getResources().getDimension(id));
    }

    public void setChipCornerRadius(float f) {
        float chipCornerRadius2 = f;
        if (this.chipCornerRadius != chipCornerRadius2) {
            this.chipCornerRadius = chipCornerRadius2;
            invalidateSelf();
        }
    }

    @Nullable
    public ColorStateList getChipStrokeColor() {
        return this.chipStrokeColor;
    }

    public void setChipStrokeColorResource(@ColorRes int id) {
        setChipStrokeColor(AppCompatResources.getColorStateList(this.context, id));
    }

    public void setChipStrokeColor(@Nullable ColorStateList colorStateList) {
        ColorStateList chipStrokeColor2 = colorStateList;
        if (this.chipStrokeColor != chipStrokeColor2) {
            this.chipStrokeColor = chipStrokeColor2;
            boolean onStateChange = onStateChange(getState());
        }
    }

    public float getChipStrokeWidth() {
        return this.chipStrokeWidth;
    }

    public void setChipStrokeWidthResource(@DimenRes int id) {
        setChipStrokeWidth(this.context.getResources().getDimension(id));
    }

    public void setChipStrokeWidth(float f) {
        float chipStrokeWidth2 = f;
        if (this.chipStrokeWidth != chipStrokeWidth2) {
            this.chipStrokeWidth = chipStrokeWidth2;
            this.chipPaint.setStrokeWidth(chipStrokeWidth2);
            invalidateSelf();
        }
    }

    @Nullable
    public ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    public void setRippleColorResource(@ColorRes int id) {
        setRippleColor(AppCompatResources.getColorStateList(this.context, id));
    }

    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        ColorStateList rippleColor2 = colorStateList;
        if (this.rippleColor != rippleColor2) {
            this.rippleColor = rippleColor2;
            updateCompatRippleColor();
            boolean onStateChange = onStateChange(getState());
        }
    }

    @NonNull
    public CharSequence getText() {
        return this.rawText;
    }

    public void setTextResource(@StringRes int id) {
        setText(this.context.getResources().getString(id));
    }

    public void setText(@Nullable CharSequence charSequence) {
        CharSequence text = charSequence;
        if (text == null) {
            text = "";
        }
        if (this.rawText != text) {
            this.rawText = text;
            this.unicodeWrappedText = BidiFormatter.getInstance().unicodeWrap(text);
            this.textWidthDirty = true;
            invalidateSelf();
            onSizeChange();
        }
    }

    @Nullable
    public TextAppearance getTextAppearance() {
        return this.textAppearance;
    }

    public void setTextAppearanceResource(@StyleRes int id) {
        TextAppearance textAppearance2;
        new TextAppearance(this.context, id);
        setTextAppearance(textAppearance2);
    }

    public void setTextAppearance(@Nullable TextAppearance textAppearance2) {
        TextAppearance textAppearance3 = textAppearance2;
        if (this.textAppearance != textAppearance3) {
            this.textAppearance = textAppearance3;
            if (textAppearance3 != null) {
                textAppearance3.updateMeasureState(this.context, this.textPaint, this.fontCallback);
                this.textWidthDirty = true;
            }
            boolean onStateChange = onStateChange(getState());
            onSizeChange();
        }
    }

    public TextUtils.TruncateAt getEllipsize() {
        return this.truncateAt;
    }

    public void setEllipsize(@Nullable TextUtils.TruncateAt truncateAt2) {
        TextUtils.TruncateAt truncateAt3 = truncateAt2;
        this.truncateAt = truncateAt3;
    }

    public boolean isChipIconVisible() {
        return this.chipIconVisible;
    }

    @Deprecated
    public boolean isChipIconEnabled() {
        return isChipIconVisible();
    }

    public void setChipIconVisible(@BoolRes int id) {
        setChipIconVisible(this.context.getResources().getBoolean(id));
    }

    public void setChipIconVisible(boolean z) {
        boolean chipIconVisible2 = z;
        if (this.chipIconVisible != chipIconVisible2) {
            boolean oldShowsChipIcon = showsChipIcon();
            this.chipIconVisible = chipIconVisible2;
            boolean newShowsChipIcon = showsChipIcon();
            if (oldShowsChipIcon != newShowsChipIcon) {
                if (newShowsChipIcon) {
                    applyChildDrawable(this.chipIcon);
                } else {
                    unapplyChildDrawable(this.chipIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    @Deprecated
    public void setChipIconEnabledResource(@BoolRes int id) {
        setChipIconVisible(id);
    }

    @Deprecated
    public void setChipIconEnabled(boolean chipIconEnabled) {
        setChipIconVisible(chipIconEnabled);
    }

    @Nullable
    public Drawable getChipIcon() {
        return this.chipIcon != null ? DrawableCompat.unwrap(this.chipIcon) : null;
    }

    public void setChipIconResource(@DrawableRes int id) {
        setChipIcon(AppCompatResources.getDrawable(this.context, id));
    }

    public void setChipIcon(@Nullable Drawable drawable) {
        Drawable chipIcon2 = drawable;
        Drawable oldChipIcon = getChipIcon();
        if (oldChipIcon != chipIcon2) {
            float oldChipIconWidth = calculateChipIconWidth();
            this.chipIcon = chipIcon2 != null ? DrawableCompat.wrap(chipIcon2).mutate() : null;
            float newChipIconWidth = calculateChipIconWidth();
            unapplyChildDrawable(oldChipIcon);
            if (showsChipIcon()) {
                applyChildDrawable(this.chipIcon);
            }
            invalidateSelf();
            if (oldChipIconWidth != newChipIconWidth) {
                onSizeChange();
            }
        }
    }

    @Nullable
    public ColorStateList getChipIconTint() {
        return this.chipIconTint;
    }

    public void setChipIconTintResource(@ColorRes int id) {
        setChipIconTint(AppCompatResources.getColorStateList(this.context, id));
    }

    public void setChipIconTint(@Nullable ColorStateList colorStateList) {
        ColorStateList chipIconTint2 = colorStateList;
        if (this.chipIconTint != chipIconTint2) {
            this.chipIconTint = chipIconTint2;
            if (showsChipIcon()) {
                DrawableCompat.setTintList(this.chipIcon, chipIconTint2);
            }
            boolean onStateChange = onStateChange(getState());
        }
    }

    public float getChipIconSize() {
        return this.chipIconSize;
    }

    public void setChipIconSizeResource(@DimenRes int id) {
        setChipIconSize(this.context.getResources().getDimension(id));
    }

    public void setChipIconSize(float f) {
        float chipIconSize2 = f;
        if (this.chipIconSize != chipIconSize2) {
            float oldChipIconWidth = calculateChipIconWidth();
            this.chipIconSize = chipIconSize2;
            float newChipIconWidth = calculateChipIconWidth();
            invalidateSelf();
            if (oldChipIconWidth != newChipIconWidth) {
                onSizeChange();
            }
        }
    }

    public boolean isCloseIconVisible() {
        return this.closeIconVisible;
    }

    @Deprecated
    public boolean isCloseIconEnabled() {
        return isCloseIconVisible();
    }

    public void setCloseIconVisible(@BoolRes int id) {
        setCloseIconVisible(this.context.getResources().getBoolean(id));
    }

    public void setCloseIconVisible(boolean z) {
        boolean closeIconVisible2 = z;
        if (this.closeIconVisible != closeIconVisible2) {
            boolean oldShowsCloseIcon = showsCloseIcon();
            this.closeIconVisible = closeIconVisible2;
            boolean newShowsCloseIcon = showsCloseIcon();
            if (oldShowsCloseIcon != newShowsCloseIcon) {
                if (newShowsCloseIcon) {
                    applyChildDrawable(this.closeIcon);
                } else {
                    unapplyChildDrawable(this.closeIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    @Deprecated
    public void setCloseIconEnabledResource(@BoolRes int id) {
        setCloseIconVisible(id);
    }

    @Deprecated
    public void setCloseIconEnabled(boolean closeIconEnabled) {
        setCloseIconVisible(closeIconEnabled);
    }

    @Nullable
    public Drawable getCloseIcon() {
        return this.closeIcon != null ? DrawableCompat.unwrap(this.closeIcon) : null;
    }

    public void setCloseIconResource(@DrawableRes int id) {
        setCloseIcon(AppCompatResources.getDrawable(this.context, id));
    }

    public void setCloseIcon(@Nullable Drawable drawable) {
        Drawable closeIcon2 = drawable;
        Drawable oldCloseIcon = getCloseIcon();
        if (oldCloseIcon != closeIcon2) {
            float oldCloseIconWidth = calculateCloseIconWidth();
            this.closeIcon = closeIcon2 != null ? DrawableCompat.wrap(closeIcon2).mutate() : null;
            float newCloseIconWidth = calculateCloseIconWidth();
            unapplyChildDrawable(oldCloseIcon);
            if (showsCloseIcon()) {
                applyChildDrawable(this.closeIcon);
            }
            invalidateSelf();
            if (oldCloseIconWidth != newCloseIconWidth) {
                onSizeChange();
            }
        }
    }

    @Nullable
    public ColorStateList getCloseIconTint() {
        return this.closeIconTint;
    }

    public void setCloseIconTintResource(@ColorRes int id) {
        setCloseIconTint(AppCompatResources.getColorStateList(this.context, id));
    }

    public void setCloseIconTint(@Nullable ColorStateList colorStateList) {
        ColorStateList closeIconTint2 = colorStateList;
        if (this.closeIconTint != closeIconTint2) {
            this.closeIconTint = closeIconTint2;
            if (showsCloseIcon()) {
                DrawableCompat.setTintList(this.closeIcon, closeIconTint2);
            }
            boolean onStateChange = onStateChange(getState());
        }
    }

    public float getCloseIconSize() {
        return this.closeIconSize;
    }

    public void setCloseIconSizeResource(@DimenRes int id) {
        setCloseIconSize(this.context.getResources().getDimension(id));
    }

    public void setCloseIconSize(float f) {
        float closeIconSize2 = f;
        if (this.closeIconSize != closeIconSize2) {
            this.closeIconSize = closeIconSize2;
            invalidateSelf();
            if (showsCloseIcon()) {
                onSizeChange();
            }
        }
    }

    public void setCloseIconContentDescription(@Nullable CharSequence charSequence) {
        CharSequence closeIconContentDescription2 = charSequence;
        if (this.closeIconContentDescription != closeIconContentDescription2) {
            this.closeIconContentDescription = BidiFormatter.getInstance().unicodeWrap(closeIconContentDescription2);
            invalidateSelf();
        }
    }

    @Nullable
    public CharSequence getCloseIconContentDescription() {
        return this.closeIconContentDescription;
    }

    public boolean isCheckable() {
        return this.checkable;
    }

    public void setCheckableResource(@BoolRes int id) {
        setCheckable(this.context.getResources().getBoolean(id));
    }

    public void setCheckable(boolean z) {
        boolean checkable2 = z;
        if (this.checkable != checkable2) {
            this.checkable = checkable2;
            float oldChipIconWidth = calculateChipIconWidth();
            if (!checkable2 && this.currentChecked) {
                this.currentChecked = false;
            }
            float newChipIconWidth = calculateChipIconWidth();
            invalidateSelf();
            if (oldChipIconWidth != newChipIconWidth) {
                onSizeChange();
            }
        }
    }

    public boolean isCheckedIconVisible() {
        return this.checkedIconVisible;
    }

    @Deprecated
    public boolean isCheckedIconEnabled() {
        return isCheckedIconVisible();
    }

    public void setCheckedIconVisible(@BoolRes int id) {
        setCheckedIconVisible(this.context.getResources().getBoolean(id));
    }

    public void setCheckedIconVisible(boolean z) {
        boolean checkedIconVisible2 = z;
        if (this.checkedIconVisible != checkedIconVisible2) {
            boolean oldShowsCheckedIcon = showsCheckedIcon();
            this.checkedIconVisible = checkedIconVisible2;
            boolean newShowsCheckedIcon = showsCheckedIcon();
            if (oldShowsCheckedIcon != newShowsCheckedIcon) {
                if (newShowsCheckedIcon) {
                    applyChildDrawable(this.checkedIcon);
                } else {
                    unapplyChildDrawable(this.checkedIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    @Deprecated
    public void setCheckedIconEnabledResource(@BoolRes int id) {
        setCheckedIconVisible(this.context.getResources().getBoolean(id));
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean checkedIconEnabled) {
        setCheckedIconVisible(checkedIconEnabled);
    }

    @Nullable
    public Drawable getCheckedIcon() {
        return this.checkedIcon;
    }

    public void setCheckedIconResource(@DrawableRes int id) {
        setCheckedIcon(AppCompatResources.getDrawable(this.context, id));
    }

    public void setCheckedIcon(@Nullable Drawable drawable) {
        Drawable checkedIcon2 = drawable;
        if (this.checkedIcon != checkedIcon2) {
            float oldChipIconWidth = calculateChipIconWidth();
            this.checkedIcon = checkedIcon2;
            float newChipIconWidth = calculateChipIconWidth();
            unapplyChildDrawable(this.checkedIcon);
            applyChildDrawable(this.checkedIcon);
            invalidateSelf();
            if (oldChipIconWidth != newChipIconWidth) {
                onSizeChange();
            }
        }
    }

    @Nullable
    public MotionSpec getShowMotionSpec() {
        return this.showMotionSpec;
    }

    public void setShowMotionSpecResource(@AnimatorRes int id) {
        setShowMotionSpec(MotionSpec.createFromResource(this.context, id));
    }

    public void setShowMotionSpec(@Nullable MotionSpec showMotionSpec2) {
        MotionSpec motionSpec = showMotionSpec2;
        this.showMotionSpec = motionSpec;
    }

    @Nullable
    public MotionSpec getHideMotionSpec() {
        return this.hideMotionSpec;
    }

    public void setHideMotionSpecResource(@AnimatorRes int id) {
        setHideMotionSpec(MotionSpec.createFromResource(this.context, id));
    }

    public void setHideMotionSpec(@Nullable MotionSpec hideMotionSpec2) {
        MotionSpec motionSpec = hideMotionSpec2;
        this.hideMotionSpec = motionSpec;
    }

    public float getChipStartPadding() {
        return this.chipStartPadding;
    }

    public void setChipStartPaddingResource(@DimenRes int id) {
        setChipStartPadding(this.context.getResources().getDimension(id));
    }

    public void setChipStartPadding(float f) {
        float chipStartPadding2 = f;
        if (this.chipStartPadding != chipStartPadding2) {
            this.chipStartPadding = chipStartPadding2;
            invalidateSelf();
            onSizeChange();
        }
    }

    public float getIconStartPadding() {
        return this.iconStartPadding;
    }

    public void setIconStartPaddingResource(@DimenRes int id) {
        setIconStartPadding(this.context.getResources().getDimension(id));
    }

    public void setIconStartPadding(float f) {
        float iconStartPadding2 = f;
        if (this.iconStartPadding != iconStartPadding2) {
            float oldChipIconWidth = calculateChipIconWidth();
            this.iconStartPadding = iconStartPadding2;
            float newChipIconWidth = calculateChipIconWidth();
            invalidateSelf();
            if (oldChipIconWidth != newChipIconWidth) {
                onSizeChange();
            }
        }
    }

    public float getIconEndPadding() {
        return this.iconEndPadding;
    }

    public void setIconEndPaddingResource(@DimenRes int id) {
        setIconEndPadding(this.context.getResources().getDimension(id));
    }

    public void setIconEndPadding(float f) {
        float iconEndPadding2 = f;
        if (this.iconEndPadding != iconEndPadding2) {
            float oldChipIconWidth = calculateChipIconWidth();
            this.iconEndPadding = iconEndPadding2;
            float newChipIconWidth = calculateChipIconWidth();
            invalidateSelf();
            if (oldChipIconWidth != newChipIconWidth) {
                onSizeChange();
            }
        }
    }

    public float getTextStartPadding() {
        return this.textStartPadding;
    }

    public void setTextStartPaddingResource(@DimenRes int id) {
        setTextStartPadding(this.context.getResources().getDimension(id));
    }

    public void setTextStartPadding(float f) {
        float textStartPadding2 = f;
        if (this.textStartPadding != textStartPadding2) {
            this.textStartPadding = textStartPadding2;
            invalidateSelf();
            onSizeChange();
        }
    }

    public float getTextEndPadding() {
        return this.textEndPadding;
    }

    public void setTextEndPaddingResource(@DimenRes int id) {
        setTextEndPadding(this.context.getResources().getDimension(id));
    }

    public void setTextEndPadding(float f) {
        float textEndPadding2 = f;
        if (this.textEndPadding != textEndPadding2) {
            this.textEndPadding = textEndPadding2;
            invalidateSelf();
            onSizeChange();
        }
    }

    public float getCloseIconStartPadding() {
        return this.closeIconStartPadding;
    }

    public void setCloseIconStartPaddingResource(@DimenRes int id) {
        setCloseIconStartPadding(this.context.getResources().getDimension(id));
    }

    public void setCloseIconStartPadding(float f) {
        float closeIconStartPadding2 = f;
        if (this.closeIconStartPadding != closeIconStartPadding2) {
            this.closeIconStartPadding = closeIconStartPadding2;
            invalidateSelf();
            if (showsCloseIcon()) {
                onSizeChange();
            }
        }
    }

    public float getCloseIconEndPadding() {
        return this.closeIconEndPadding;
    }

    public void setCloseIconEndPaddingResource(@DimenRes int id) {
        setCloseIconEndPadding(this.context.getResources().getDimension(id));
    }

    public void setCloseIconEndPadding(float f) {
        float closeIconEndPadding2 = f;
        if (this.closeIconEndPadding != closeIconEndPadding2) {
            this.closeIconEndPadding = closeIconEndPadding2;
            invalidateSelf();
            if (showsCloseIcon()) {
                onSizeChange();
            }
        }
    }

    public float getChipEndPadding() {
        return this.chipEndPadding;
    }

    public void setChipEndPaddingResource(@DimenRes int id) {
        setChipEndPadding(this.context.getResources().getDimension(id));
    }

    public void setChipEndPadding(float f) {
        float chipEndPadding2 = f;
        if (this.chipEndPadding != chipEndPadding2) {
            this.chipEndPadding = chipEndPadding2;
            invalidateSelf();
            onSizeChange();
        }
    }

    @C0015Px
    public int getMaxWidth() {
        return this.maxWidth;
    }

    public void setMaxWidth(@C0015Px int maxWidth2) {
        int i = maxWidth2;
        this.maxWidth = i;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldDrawText() {
        return this.shouldDrawText;
    }

    /* access modifiers changed from: package-private */
    public void setShouldDrawText(boolean shouldDrawText2) {
        boolean z = shouldDrawText2;
        this.shouldDrawText = z;
    }
}
