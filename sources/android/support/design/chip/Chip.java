package android.support.design.chip;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.AnimatorRes;
import android.support.annotation.BoolRes;
import android.support.annotation.C0015Px;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.design.C0064R;
import android.support.design.animation.MotionSpec;
import android.support.design.chip.ChipDrawable;
import android.support.design.resources.TextAppearance;
import android.support.design.ripple.RippleUtils;
import android.support.p000v4.content.res.ResourcesCompat;
import android.support.p000v4.text.BidiFormatter;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p000v4.widget.ExploreByTouchHelper;
import android.support.p003v7.widget.AppCompatCheckBox;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Chip extends AppCompatCheckBox implements ChipDrawable.Delegate {
    private static final int CLOSE_ICON_VIRTUAL_ID = 0;
    /* access modifiers changed from: private */
    public static final Rect EMPTY_BOUNDS;
    private static final String NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";
    private static final int[] SELECTED_STATE = {16842913};
    private static final String TAG = "Chip";
    /* access modifiers changed from: private */
    @Nullable
    public ChipDrawable chipDrawable;
    private boolean closeIconFocused;
    private boolean closeIconHovered;
    private boolean closeIconPressed;
    private boolean deferredCheckedValue;
    private int focusedVirtualView;
    private final ResourcesCompat.FontCallback fontCallback;
    @Nullable
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListenerInternal;
    @Nullable
    private View.OnClickListener onCloseIconClickListener;
    private final Rect rect;
    private final RectF rectF;
    @Nullable
    private RippleDrawable ripple;
    private final ChipTouchHelper touchHelper;

    static {
        Rect rect2;
        new Rect();
        EMPTY_BOUNDS = rect2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Chip(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Chip(Context context, AttributeSet attrs) {
        this(context, attrs, C0064R.attr.chipStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Chip(android.content.Context r12, android.util.AttributeSet r13, int r14) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            r5.<init>(r6, r7, r8)
            r5 = r0
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r5.focusedVirtualView = r6
            r5 = r0
            android.graphics.Rect r6 = new android.graphics.Rect
            r10 = r6
            r6 = r10
            r7 = r10
            r7.<init>()
            r5.rect = r6
            r5 = r0
            android.graphics.RectF r6 = new android.graphics.RectF
            r10 = r6
            r6 = r10
            r7 = r10
            r7.<init>()
            r5.rectF = r6
            r5 = r0
            android.support.design.chip.Chip$1 r6 = new android.support.design.chip.Chip$1
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = r0
            r7.<init>(r8)
            r5.fontCallback = r6
            r5 = r0
            r6 = r2
            r5.validateAttributes(r6)
            r5 = r1
            r6 = r2
            r7 = r3
            int r8 = android.support.design.C0064R.C0068style.Widget_MaterialComponents_Chip_Action
            android.support.design.chip.ChipDrawable r5 = android.support.design.chip.ChipDrawable.createFromAttributes(r5, r6, r7, r8)
            r4 = r5
            r5 = r0
            r6 = r4
            r5.setChipDrawable(r6)
            r5 = r0
            android.support.design.chip.Chip$ChipTouchHelper r6 = new android.support.design.chip.Chip$ChipTouchHelper
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = r0
            r9 = r0
            r7.<init>(r8, r9)
            r5.touchHelper = r6
            r5 = r0
            r6 = r0
            android.support.design.chip.Chip$ChipTouchHelper r6 = r6.touchHelper
            android.support.p000v4.view.ViewCompat.setAccessibilityDelegate(r5, r6)
            r5 = r0
            r5.initOutlineProvider()
            r5 = r0
            r6 = r0
            boolean r6 = r6.deferredCheckedValue
            r5.setChecked(r6)
            r5 = r4
            r6 = 0
            r5.setShouldDrawText(r6)
            r5 = r0
            r6 = r4
            java.lang.CharSequence r6 = r6.getText()
            r5.setText(r6)
            r5 = r0
            r6 = r4
            android.text.TextUtils$TruncateAt r6 = r6.getEllipsize()
            r5.setEllipsize(r6)
            r5 = r0
            r6 = 0
            r5.setIncludeFontPadding(r6)
            r5 = r0
            android.support.design.resources.TextAppearance r5 = r5.getTextAppearance()
            if (r5 == 0) goto L_0x0091
            r5 = r0
            r6 = r0
            android.support.design.resources.TextAppearance r6 = r6.getTextAppearance()
            r5.updateTextPaintDrawState(r6)
        L_0x0091:
            r5 = r0
            r5.setSingleLine()
            r5 = r0
            r6 = 8388627(0x800013, float:1.175497E-38)
            r5.setGravity(r6)
            r5 = r0
            r5.updatePaddingInternal()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.chip.Chip.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private void updatePaddingInternal() {
        if (!TextUtils.isEmpty(getText()) && this.chipDrawable != null) {
            float paddingEnd = this.chipDrawable.getChipStartPadding() + this.chipDrawable.getChipEndPadding() + this.chipDrawable.getTextStartPadding() + this.chipDrawable.getTextEndPadding();
            if ((this.chipDrawable.isChipIconVisible() && this.chipDrawable.getChipIcon() != null) || (this.chipDrawable.getCheckedIcon() != null && this.chipDrawable.isCheckedIconVisible() && isChecked())) {
                paddingEnd += this.chipDrawable.getIconStartPadding() + this.chipDrawable.getIconEndPadding() + this.chipDrawable.getChipIconSize();
            }
            if (this.chipDrawable.isCloseIconVisible() && this.chipDrawable.getCloseIcon() != null) {
                paddingEnd += this.chipDrawable.getCloseIconStartPadding() + this.chipDrawable.getCloseIconEndPadding() + this.chipDrawable.getCloseIconSize();
            }
            if (((float) ViewCompat.getPaddingEnd(this)) != paddingEnd) {
                ViewCompat.setPaddingRelative(this, ViewCompat.getPaddingStart(this), getPaddingTop(), (int) paddingEnd, getPaddingBottom());
            }
        }
    }

    private void validateAttributes(@Nullable AttributeSet attributeSet) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        AttributeSet attributeSet2 = attributeSet;
        if (attributeSet2 != null) {
            if (attributeSet2.getAttributeValue(NAMESPACE_ANDROID, "background") != null) {
                Throwable th7 = th6;
                new UnsupportedOperationException("Do not set the background; Chip manages its own background drawable.");
                throw th7;
            } else if (attributeSet2.getAttributeValue(NAMESPACE_ANDROID, "drawableLeft") != null) {
                Throwable th8 = th5;
                new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
                throw th8;
            } else if (attributeSet2.getAttributeValue(NAMESPACE_ANDROID, "drawableStart") != null) {
                Throwable th9 = th4;
                new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
                throw th9;
            } else if (attributeSet2.getAttributeValue(NAMESPACE_ANDROID, "drawableEnd") != null) {
                Throwable th10 = th3;
                new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
                throw th10;
            } else if (attributeSet2.getAttributeValue(NAMESPACE_ANDROID, "drawableRight") != null) {
                Throwable th11 = th2;
                new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
                throw th11;
            } else if (!attributeSet2.getAttributeBooleanValue(NAMESPACE_ANDROID, "singleLine", true) || attributeSet2.getAttributeIntValue(NAMESPACE_ANDROID, "lines", 1) != 1 || attributeSet2.getAttributeIntValue(NAMESPACE_ANDROID, "minLines", 1) != 1 || attributeSet2.getAttributeIntValue(NAMESPACE_ANDROID, "maxLines", 1) != 1) {
                Throwable th12 = th;
                new UnsupportedOperationException("Chip does not support multi-line text");
                throw th12;
            } else if (attributeSet2.getAttributeIntValue(NAMESPACE_ANDROID, "gravity", 8388627) != 8388627) {
                int w = Log.w(TAG, "Chip text must be vertically center and start aligned");
            }
        }
    }

    private void initOutlineProvider() {
        ViewOutlineProvider viewOutlineProvider;
        if (Build.VERSION.SDK_INT >= 21) {
            new ViewOutlineProvider(this) {
                final /* synthetic */ Chip this$0;

                {
                    this.this$0 = this$0;
                }

                @TargetApi(21)
                public void getOutline(View view, Outline outline) {
                    View view2 = view;
                    Outline outline2 = outline;
                    if (this.this$0.chipDrawable != null) {
                        this.this$0.chipDrawable.getOutline(outline2);
                    } else {
                        outline2.setAlpha(0.0f);
                    }
                }
            };
            setOutlineProvider(viewOutlineProvider);
        }
    }

    public Drawable getChipDrawable() {
        return this.chipDrawable;
    }

    public void setChipDrawable(@NonNull ChipDrawable chipDrawable2) {
        RippleDrawable rippleDrawable;
        ChipDrawable drawable = chipDrawable2;
        if (this.chipDrawable != drawable) {
            unapplyChipDrawable(this.chipDrawable);
            this.chipDrawable = drawable;
            applyChipDrawable(this.chipDrawable);
            if (RippleUtils.USE_FRAMEWORK_RIPPLE) {
                new RippleDrawable(RippleUtils.convertToRippleDrawableColor(this.chipDrawable.getRippleColor()), this.chipDrawable, (Drawable) null);
                this.ripple = rippleDrawable;
                this.chipDrawable.setUseCompatRipple(false);
                ViewCompat.setBackground(this, this.ripple);
                return;
            }
            this.chipDrawable.setUseCompatRipple(true);
            ViewCompat.setBackground(this, this.chipDrawable);
        }
    }

    private void unapplyChipDrawable(@Nullable ChipDrawable chipDrawable2) {
        ChipDrawable chipDrawable3 = chipDrawable2;
        if (chipDrawable3 != null) {
            chipDrawable3.setDelegate((ChipDrawable.Delegate) null);
        }
    }

    private void applyChipDrawable(@NonNull ChipDrawable chipDrawable2) {
        chipDrawable2.setDelegate(this);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int extraSpace) {
        int[] state = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            int[] mergeDrawableStates = mergeDrawableStates(state, SELECTED_STATE);
        }
        return state;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        if (TextUtils.isEmpty(getText()) || this.chipDrawable == null || this.chipDrawable.shouldDrawText()) {
            super.onDraw(canvas2);
            return;
        }
        int saveCount = canvas2.save();
        canvas2.translate(calculateTextOffsetFromStart(this.chipDrawable), 0.0f);
        super.onDraw(canvas2);
        canvas2.restoreToCount(saveCount);
    }

    public void setGravity(int i) {
        int gravity = i;
        if (gravity != 8388627) {
            int w = Log.w(TAG, "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(gravity);
        }
    }

    private float calculateTextOffsetFromStart(@NonNull ChipDrawable chipDrawable2) {
        float offsetFromStart = getChipStartPadding() + chipDrawable2.calculateChipIconWidth() + getTextStartPadding();
        if (ViewCompat.getLayoutDirection(this) == 0) {
            return offsetFromStart;
        }
        return -offsetFromStart;
    }

    public void setBackgroundTintList(@Nullable ColorStateList colorStateList) {
        Throwable th;
        ColorStateList colorStateList2 = colorStateList;
        Throwable th2 = th;
        new UnsupportedOperationException("Do not set the background tint list; Chip manages its own background drawable.");
        throw th2;
    }

    public void setBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        Throwable th;
        PorterDuff.Mode mode2 = mode;
        Throwable th2 = th;
        new UnsupportedOperationException("Do not set the background tint mode; Chip manages its own background drawable.");
        throw th2;
    }

    public void setBackgroundColor(int i) {
        Throwable th;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException("Do not set the background color; Chip manages its own background drawable.");
        throw th2;
    }

    public void setBackgroundResource(int i) {
        Throwable th;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException("Do not set the background resource; Chip manages its own background drawable.");
        throw th2;
    }

    public void setBackground(Drawable drawable) {
        Throwable th;
        Drawable background = drawable;
        if (background == this.chipDrawable || background == this.ripple) {
            super.setBackground(background);
            return;
        }
        Throwable th2 = th;
        new UnsupportedOperationException("Do not set the background; Chip manages its own background drawable.");
        throw th2;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        Throwable th;
        Drawable background = drawable;
        if (background == this.chipDrawable || background == this.ripple) {
            super.setBackgroundDrawable(background);
            return;
        }
        Throwable th2 = th;
        new UnsupportedOperationException("Do not set the background drawable; Chip manages its own background drawable.");
        throw th2;
    }

    public void setCompoundDrawables(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        Throwable th;
        Throwable th2;
        Drawable left = drawable;
        Drawable top = drawable2;
        Drawable right = drawable3;
        Drawable bottom = drawable4;
        if (left != null) {
            Throwable th3 = th2;
            new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
            throw th3;
        } else if (right != null) {
            Throwable th4 = th;
            new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            throw th4;
        } else {
            super.setCompoundDrawables(left, top, right, bottom);
        }
    }

    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Throwable th;
        Throwable th2;
        int left = i;
        int top = i2;
        int right = i3;
        int bottom = i4;
        if (left != 0) {
            Throwable th3 = th2;
            new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
            throw th3;
        } else if (right != 0) {
            Throwable th4 = th;
            new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            throw th4;
        } else {
            super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        }
    }

    public void setCompoundDrawablesWithIntrinsicBounds(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        Throwable th;
        Throwable th2;
        Drawable left = drawable;
        Drawable top = drawable2;
        Drawable right = drawable3;
        Drawable bottom = drawable4;
        if (left != null) {
            Throwable th3 = th2;
            new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
            throw th3;
        } else if (right != null) {
            Throwable th4 = th;
            new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
            throw th4;
        } else {
            super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        }
    }

    public void setCompoundDrawablesRelative(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        Throwable th;
        Throwable th2;
        Drawable start = drawable;
        Drawable top = drawable2;
        Drawable end = drawable3;
        Drawable bottom = drawable4;
        if (start != null) {
            Throwable th3 = th2;
            new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
            throw th3;
        } else if (end != null) {
            Throwable th4 = th;
            new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            throw th4;
        } else {
            super.setCompoundDrawablesRelative(start, top, end, bottom);
        }
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Throwable th;
        Throwable th2;
        int start = i;
        int top = i2;
        int end = i3;
        int bottom = i4;
        if (start != 0) {
            Throwable th3 = th2;
            new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
            throw th3;
        } else if (end != 0) {
            Throwable th4 = th;
            new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            throw th4;
        } else {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        }
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        Throwable th;
        Throwable th2;
        Drawable start = drawable;
        Drawable top = drawable2;
        Drawable end = drawable3;
        Drawable bottom = drawable4;
        if (start != null) {
            Throwable th3 = th2;
            new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
            throw th3;
        } else if (end != null) {
            Throwable th4 = th;
            new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            throw th4;
        } else {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        }
    }

    public TextUtils.TruncateAt getEllipsize() {
        return this.chipDrawable != null ? this.chipDrawable.getEllipsize() : null;
    }

    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        Throwable th;
        TextUtils.TruncateAt where = truncateAt;
        if (this.chipDrawable != null) {
            if (where == TextUtils.TruncateAt.MARQUEE) {
                Throwable th2 = th;
                new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
                throw th2;
            }
            super.setEllipsize(where);
            if (this.chipDrawable != null) {
                this.chipDrawable.setEllipsize(where);
            }
        }
    }

    public void setSingleLine(boolean z) {
        Throwable th;
        boolean singleLine = z;
        if (!singleLine) {
            Throwable th2 = th;
            new UnsupportedOperationException("Chip does not support multi-line text");
            throw th2;
        }
        super.setSingleLine(singleLine);
    }

    public void setLines(int i) {
        Throwable th;
        int lines = i;
        if (lines > 1) {
            Throwable th2 = th;
            new UnsupportedOperationException("Chip does not support multi-line text");
            throw th2;
        }
        super.setLines(lines);
    }

    public void setMinLines(int i) {
        Throwable th;
        int minLines = i;
        if (minLines > 1) {
            Throwable th2 = th;
            new UnsupportedOperationException("Chip does not support multi-line text");
            throw th2;
        }
        super.setMinLines(minLines);
    }

    public void setMaxLines(int i) {
        Throwable th;
        int maxLines = i;
        if (maxLines > 1) {
            Throwable th2 = th;
            new UnsupportedOperationException("Chip does not support multi-line text");
            throw th2;
        }
        super.setMaxLines(maxLines);
    }

    public void setMaxWidth(@C0015Px int i) {
        int maxWidth = i;
        super.setMaxWidth(maxWidth);
        if (this.chipDrawable != null) {
            this.chipDrawable.setMaxWidth(maxWidth);
        }
    }

    public void onChipDrawableSizeChange() {
        updatePaddingInternal();
        requestLayout();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setChecked(boolean z) {
        boolean checked = z;
        if (this.chipDrawable == null) {
            this.deferredCheckedValue = checked;
        } else if (this.chipDrawable.isCheckable()) {
            boolean wasChecked = isChecked();
            super.setChecked(checked);
            if (wasChecked != checked && this.onCheckedChangeListenerInternal != null) {
                this.onCheckedChangeListenerInternal.onCheckedChanged(this, checked);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setOnCheckedChangeListenerInternal(CompoundButton.OnCheckedChangeListener listener) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = listener;
        this.onCheckedChangeListenerInternal = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener listener) {
        View.OnClickListener onClickListener = listener;
        this.onCloseIconClickListener = onClickListener;
    }

    @CallSuper
    public boolean performCloseIconClick() {
        boolean result;
        playSoundEffect(0);
        if (this.onCloseIconClickListener != null) {
            this.onCloseIconClickListener.onClick(this);
            result = true;
        } else {
            result = false;
        }
        boolean sendEventForVirtualView = this.touchHelper.sendEventForVirtualView(0, 1);
        return result;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent event = motionEvent;
        boolean handled = false;
        int action = event.getActionMasked();
        boolean eventInCloseIcon = getCloseIconTouchBounds().contains(event.getX(), event.getY());
        switch (action) {
            case 0:
                if (eventInCloseIcon) {
                    setCloseIconPressed(true);
                    handled = true;
                    break;
                }
                break;
            case 1:
                if (this.closeIconPressed) {
                    boolean performCloseIconClick = performCloseIconClick();
                    handled = true;
                    break;
                }
                break;
            case 2:
                if (this.closeIconPressed) {
                    if (!eventInCloseIcon) {
                        setCloseIconPressed(false);
                    }
                    handled = true;
                    break;
                }
                break;
            case 3:
                break;
        }
        setCloseIconPressed(false);
        return handled || super.onTouchEvent(event);
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        MotionEvent event = motionEvent;
        switch (event.getActionMasked()) {
            case 7:
                setCloseIconHovered(getCloseIconTouchBounds().contains(event.getX(), event.getY()));
                break;
            case 10:
                setCloseIconHovered(false);
                break;
        }
        return super.onHoverEvent(event);
    }

    @SuppressLint({"PrivateApi"})
    private boolean handleAccessibilityExit(MotionEvent event) {
        if (event.getAction() == 10) {
            try {
                Field f = ExploreByTouchHelper.class.getDeclaredField("mHoveredVirtualViewId");
                f.setAccessible(true);
                if (((Integer) f.get(this.touchHelper)).intValue() != Integer.MIN_VALUE) {
                    Method m = ExploreByTouchHelper.class.getDeclaredMethod("updateHoveredVirtualView", new Class[]{Integer.TYPE});
                    m.setAccessible(true);
                    Object invoke = m.invoke(this.touchHelper, new Object[]{Integer.MIN_VALUE});
                    return true;
                }
            } catch (NoSuchMethodException e) {
                int e2 = Log.e(TAG, "Unable to send Accessibility Exit event", e);
            } catch (IllegalAccessException e3) {
                int e4 = Log.e(TAG, "Unable to send Accessibility Exit event", e3);
            } catch (InvocationTargetException e5) {
                int e6 = Log.e(TAG, "Unable to send Accessibility Exit event", e5);
            } catch (NoSuchFieldException e7) {
                int e8 = Log.e(TAG, "Unable to send Accessibility Exit event", e7);
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        MotionEvent event = motionEvent;
        return handleAccessibilityExit(event) || this.touchHelper.dispatchHoverEvent(event) || super.dispatchHoverEvent(event);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        KeyEvent event = keyEvent;
        return this.touchHelper.dispatchKeyEvent(event) || super.dispatchKeyEvent(event);
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i, Rect rect2) {
        boolean focused = z;
        int direction = i;
        Rect previouslyFocusedRect = rect2;
        if (focused) {
            setFocusedVirtualView(-1);
        } else {
            setFocusedVirtualView(Integer.MIN_VALUE);
        }
        invalidate();
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        this.touchHelper.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0088  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onKeyDown(int r11, android.view.KeyEvent r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r7 = 0
            r3 = r7
            r7 = r2
            int r7 = r7.getKeyCode()
            switch(r7) {
                case 21: goto L_0x0017;
                case 22: goto L_0x002a;
                case 23: goto L_0x0042;
                case 61: goto L_0x0059;
                case 66: goto L_0x0042;
                default: goto L_0x000d;
            }
        L_0x000d:
            r7 = r3
            if (r7 == 0) goto L_0x009d
            r7 = r0
            r7.invalidate()
            r7 = 1
            r0 = r7
        L_0x0016:
            return r0
        L_0x0017:
            r7 = r2
            boolean r7 = r7.hasNoModifiers()
            if (r7 == 0) goto L_0x000d
            r7 = r0
            r8 = r0
            boolean r8 = android.support.design.internal.ViewUtils.isLayoutRtl(r8)
            boolean r7 = r7.moveFocus(r8)
            r3 = r7
            goto L_0x000d
        L_0x002a:
            r7 = r2
            boolean r7 = r7.hasNoModifiers()
            if (r7 == 0) goto L_0x000d
            r7 = r0
            r8 = r0
            boolean r8 = android.support.design.internal.ViewUtils.isLayoutRtl(r8)
            if (r8 != 0) goto L_0x0040
            r8 = 1
        L_0x003a:
            boolean r7 = r7.moveFocus(r8)
            r3 = r7
            goto L_0x000d
        L_0x0040:
            r8 = 0
            goto L_0x003a
        L_0x0042:
            r7 = r0
            int r7 = r7.focusedVirtualView
            switch(r7) {
                case -1: goto L_0x0049;
                case 0: goto L_0x0051;
                default: goto L_0x0048;
            }
        L_0x0048:
            goto L_0x000d
        L_0x0049:
            r7 = r0
            boolean r7 = r7.performClick()
            r7 = 1
            r0 = r7
            goto L_0x0016
        L_0x0051:
            r7 = r0
            boolean r7 = r7.performCloseIconClick()
            r7 = 1
            r0 = r7
            goto L_0x0016
        L_0x0059:
            r7 = 0
            r4 = r7
            r7 = r2
            boolean r7 = r7.hasNoModifiers()
            if (r7 == 0) goto L_0x0090
            r7 = 2
            r4 = r7
        L_0x0064:
            r7 = r4
            if (r7 == 0) goto L_0x000d
            r7 = r0
            android.view.ViewParent r7 = r7.getParent()
            r5 = r7
            r7 = r0
            r6 = r7
        L_0x006f:
            r7 = r6
            r8 = r4
            android.view.View r7 = r7.focusSearch(r8)
            r6 = r7
            r7 = r6
            if (r7 == 0) goto L_0x0085
            r7 = r6
            r8 = r0
            if (r7 == r8) goto L_0x0085
            r7 = r6
            android.view.ViewParent r7 = r7.getParent()
            r8 = r5
            if (r7 == r8) goto L_0x006f
        L_0x0085:
            r7 = r6
            if (r7 == 0) goto L_0x009b
            r7 = r6
            boolean r7 = r7.requestFocus()
            r7 = 1
            r0 = r7
            goto L_0x0016
        L_0x0090:
            r7 = r2
            r8 = 1
            boolean r7 = r7.hasModifiers(r8)
            if (r7 == 0) goto L_0x0064
            r7 = 1
            r4 = r7
            goto L_0x0064
        L_0x009b:
            goto L_0x000d
        L_0x009d:
            r7 = r0
            r8 = r1
            r9 = r2
            boolean r7 = super.onKeyDown(r8, r9)
            r0 = r7
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.chip.Chip.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    private boolean moveFocus(boolean positive) {
        ensureFocus();
        boolean focusChanged = false;
        if (positive) {
            if (this.focusedVirtualView == -1) {
                setFocusedVirtualView(0);
                focusChanged = true;
            }
        } else if (this.focusedVirtualView == 0) {
            setFocusedVirtualView(-1);
            focusChanged = true;
        }
        return focusChanged;
    }

    private void ensureFocus() {
        if (this.focusedVirtualView == Integer.MIN_VALUE) {
            setFocusedVirtualView(-1);
        }
    }

    public void getFocusedRect(Rect rect2) {
        Rect r = rect2;
        if (this.focusedVirtualView == 0) {
            r.set(getCloseIconTouchBoundsInt());
        } else {
            super.getFocusedRect(r);
        }
    }

    private void setFocusedVirtualView(int i) {
        int virtualView = i;
        if (this.focusedVirtualView != virtualView) {
            if (this.focusedVirtualView == 0) {
                setCloseIconFocused(false);
            }
            this.focusedVirtualView = virtualView;
            if (virtualView == 0) {
                setCloseIconFocused(true);
            }
        }
    }

    private void setCloseIconPressed(boolean z) {
        boolean pressed = z;
        if (this.closeIconPressed != pressed) {
            this.closeIconPressed = pressed;
            refreshDrawableState();
        }
    }

    private void setCloseIconHovered(boolean z) {
        boolean hovered = z;
        if (this.closeIconHovered != hovered) {
            this.closeIconHovered = hovered;
            refreshDrawableState();
        }
    }

    private void setCloseIconFocused(boolean z) {
        boolean focused = z;
        if (this.closeIconFocused != focused) {
            this.closeIconFocused = focused;
            refreshDrawableState();
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        boolean changed = false;
        if (this.chipDrawable != null && this.chipDrawable.isCloseIconStateful()) {
            changed = this.chipDrawable.setCloseIconState(createCloseIconDrawableState());
        }
        if (changed) {
            invalidate();
        }
    }

    private int[] createCloseIconDrawableState() {
        int count = 0;
        if (isEnabled()) {
            count = 0 + 1;
        }
        if (this.closeIconFocused) {
            count++;
        }
        if (this.closeIconHovered) {
            count++;
        }
        if (this.closeIconPressed) {
            count++;
        }
        if (isChecked()) {
            count++;
        }
        int[] stateSet = new int[count];
        int i = 0;
        if (isEnabled()) {
            stateSet[0] = 16842910;
            i = 0 + 1;
        }
        if (this.closeIconFocused) {
            stateSet[i] = 16842908;
            i++;
        }
        if (this.closeIconHovered) {
            stateSet[i] = 16843623;
            i++;
        }
        if (this.closeIconPressed) {
            stateSet[i] = 16842919;
            i++;
        }
        if (isChecked()) {
            stateSet[i] = 16842913;
            int i2 = i + 1;
        }
        return stateSet;
    }

    /* access modifiers changed from: private */
    public boolean hasCloseIcon() {
        return (this.chipDrawable == null || this.chipDrawable.getCloseIcon() == null) ? false : true;
    }

    /* access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        this.rectF.setEmpty();
        if (hasCloseIcon()) {
            this.chipDrawable.getCloseIconTouchBounds(this.rectF);
        }
        return this.rectF;
    }

    /* access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF bounds = getCloseIconTouchBounds();
        this.rect.set((int) bounds.left, (int) bounds.top, (int) bounds.right, (int) bounds.bottom);
        return this.rect;
    }

    @TargetApi(24)
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        MotionEvent event = motionEvent;
        int i2 = i;
        if (!getCloseIconTouchBounds().contains(event.getX(), event.getY()) || !isEnabled()) {
            return null;
        }
        return PointerIcon.getSystemIcon(getContext(), 1002);
    }

    private class ChipTouchHelper extends ExploreByTouchHelper {
        final /* synthetic */ Chip this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        ChipTouchHelper(Chip chip, Chip view) {
            super(view);
            this.this$0 = chip;
        }

        /* access modifiers changed from: protected */
        public int getVirtualViewAt(float x, float y) {
            return (!this.this$0.hasCloseIcon() || !this.this$0.getCloseIconTouchBounds().contains(x, y)) ? -1 : 0;
        }

        /* access modifiers changed from: protected */
        public void getVisibleVirtualViews(List<Integer> list) {
            List<Integer> virtualViewIds = list;
            if (this.this$0.hasCloseIcon()) {
                boolean add = virtualViewIds.add(0);
            }
        }

        /* access modifiers changed from: protected */
        public void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int i2 = i;
            AccessibilityNodeInfoCompat node = accessibilityNodeInfoCompat;
            if (this.this$0.hasCloseIcon()) {
                CharSequence closeIconContentDescription = this.this$0.getCloseIconContentDescription();
                if (closeIconContentDescription != null) {
                    node.setContentDescription(closeIconContentDescription);
                } else {
                    CharSequence chipText = this.this$0.getText();
                    AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = node;
                    Context context = this.this$0.getContext();
                    int i3 = C0064R.string.mtrl_chip_close_icon_content_description;
                    Object[] objArr = new Object[1];
                    Object[] objArr2 = objArr;
                    objArr[0] = !TextUtils.isEmpty(chipText) ? chipText : "";
                    accessibilityNodeInfoCompat2.setContentDescription(context.getString(i3, objArr2).trim());
                }
                node.setBoundsInParent(this.this$0.getCloseIconTouchBoundsInt());
                node.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
                node.setEnabled(this.this$0.isEnabled());
                return;
            }
            node.setContentDescription("");
            node.setBoundsInParent(Chip.EMPTY_BOUNDS);
        }

        /* access modifiers changed from: protected */
        public void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            AccessibilityNodeInfoCompat node = accessibilityNodeInfoCompat;
            node.setCheckable(this.this$0.chipDrawable != null && this.this$0.chipDrawable.isCheckable());
            node.setClassName(Chip.class.getName());
            CharSequence chipText = this.this$0.getText();
            if (Build.VERSION.SDK_INT >= 23) {
                node.setText(chipText);
            } else {
                node.setContentDescription(chipText);
            }
        }

        /* access modifiers changed from: protected */
        public boolean onPerformActionForVirtualView(int i, int action, Bundle bundle) {
            int virtualViewId = i;
            Bundle bundle2 = bundle;
            if (action == 16 && virtualViewId == 0) {
                return this.this$0.performCloseIconClick();
            }
            return false;
        }
    }

    @Nullable
    public ColorStateList getChipBackgroundColor() {
        return this.chipDrawable != null ? this.chipDrawable.getChipBackgroundColor() : null;
    }

    public void setChipBackgroundColorResource(@ColorRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipBackgroundColorResource(id);
        }
    }

    public void setChipBackgroundColor(@Nullable ColorStateList colorStateList) {
        ColorStateList chipBackgroundColor = colorStateList;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipBackgroundColor(chipBackgroundColor);
        }
    }

    public float getChipMinHeight() {
        return this.chipDrawable != null ? this.chipDrawable.getChipMinHeight() : 0.0f;
    }

    public void setChipMinHeightResource(@DimenRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipMinHeightResource(id);
        }
    }

    public void setChipMinHeight(float f) {
        float minHeight = f;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipMinHeight(minHeight);
        }
    }

    public float getChipCornerRadius() {
        return this.chipDrawable != null ? this.chipDrawable.getChipCornerRadius() : 0.0f;
    }

    public void setChipCornerRadiusResource(@DimenRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipCornerRadiusResource(id);
        }
    }

    public void setChipCornerRadius(float f) {
        float chipCornerRadius = f;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipCornerRadius(chipCornerRadius);
        }
    }

    @Nullable
    public ColorStateList getChipStrokeColor() {
        return this.chipDrawable != null ? this.chipDrawable.getChipStrokeColor() : null;
    }

    public void setChipStrokeColorResource(@ColorRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipStrokeColorResource(id);
        }
    }

    public void setChipStrokeColor(@Nullable ColorStateList colorStateList) {
        ColorStateList chipStrokeColor = colorStateList;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipStrokeColor(chipStrokeColor);
        }
    }

    public float getChipStrokeWidth() {
        return this.chipDrawable != null ? this.chipDrawable.getChipStrokeWidth() : 0.0f;
    }

    public void setChipStrokeWidthResource(@DimenRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipStrokeWidthResource(id);
        }
    }

    public void setChipStrokeWidth(float f) {
        float chipStrokeWidth = f;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipStrokeWidth(chipStrokeWidth);
        }
    }

    @Nullable
    public ColorStateList getRippleColor() {
        return this.chipDrawable != null ? this.chipDrawable.getRippleColor() : null;
    }

    public void setRippleColorResource(@ColorRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setRippleColorResource(id);
        }
    }

    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        ColorStateList rippleColor = colorStateList;
        if (this.chipDrawable != null) {
            this.chipDrawable.setRippleColor(rippleColor);
        }
    }

    public CharSequence getText() {
        return this.chipDrawable != null ? this.chipDrawable.getText() : "";
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        CharSequence text = charSequence;
        TextView.BufferType type = bufferType;
        if (this.chipDrawable != null) {
            if (text == null) {
                text = "";
            }
            super.setText(this.chipDrawable.shouldDrawText() ? null : BidiFormatter.getInstance().unicodeWrap(text), type);
            if (this.chipDrawable != null) {
                this.chipDrawable.setText(text);
            }
        }
    }

    @Deprecated
    public void setChipTextResource(@StringRes int id) {
        setText(getResources().getString(id));
    }

    @Deprecated
    public void setChipText(@Nullable CharSequence chipText) {
        setText(chipText);
    }

    @Nullable
    private TextAppearance getTextAppearance() {
        return this.chipDrawable != null ? this.chipDrawable.getTextAppearance() : null;
    }

    private void updateTextPaintDrawState(TextAppearance textAppearance) {
        TextPaint textPaint = getPaint();
        textPaint.drawableState = this.chipDrawable.getState();
        textAppearance.updateDrawState(getContext(), textPaint, this.fontCallback);
    }

    public void setTextAppearanceResource(@StyleRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setTextAppearanceResource(id);
        }
        setTextAppearance(getContext(), id);
    }

    public void setTextAppearance(@Nullable TextAppearance textAppearance) {
        TextAppearance textAppearance2 = textAppearance;
        if (this.chipDrawable != null) {
            this.chipDrawable.setTextAppearance(textAppearance2);
        }
        if (getTextAppearance() != null) {
            getTextAppearance().updateMeasureState(getContext(), getPaint(), this.fontCallback);
            updateTextPaintDrawState(textAppearance2);
        }
    }

    public void setTextAppearance(Context context, int i) {
        Context context2 = context;
        int resId = i;
        super.setTextAppearance(context2, resId);
        if (this.chipDrawable != null) {
            this.chipDrawable.setTextAppearanceResource(resId);
        }
        if (getTextAppearance() != null) {
            getTextAppearance().updateMeasureState(context2, getPaint(), this.fontCallback);
            updateTextPaintDrawState(getTextAppearance());
        }
    }

    public void setTextAppearance(int i) {
        int resId = i;
        super.setTextAppearance(resId);
        if (this.chipDrawable != null) {
            this.chipDrawable.setTextAppearanceResource(resId);
        }
        if (getTextAppearance() != null) {
            getTextAppearance().updateMeasureState(getContext(), getPaint(), this.fontCallback);
            updateTextPaintDrawState(getTextAppearance());
        }
    }

    public boolean isChipIconVisible() {
        return this.chipDrawable != null && this.chipDrawable.isChipIconVisible();
    }

    @Deprecated
    public boolean isChipIconEnabled() {
        return isChipIconVisible();
    }

    public void setChipIconVisible(@BoolRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipIconVisible(id);
        }
    }

    public void setChipIconVisible(boolean z) {
        boolean chipIconVisible = z;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipIconVisible(chipIconVisible);
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
        return this.chipDrawable != null ? this.chipDrawable.getChipIcon() : null;
    }

    public void setChipIconResource(@DrawableRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipIconResource(id);
        }
    }

    public void setChipIcon(@Nullable Drawable drawable) {
        Drawable chipIcon = drawable;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipIcon(chipIcon);
        }
    }

    @Nullable
    public ColorStateList getChipIconTint() {
        return this.chipDrawable != null ? this.chipDrawable.getChipIconTint() : null;
    }

    public void setChipIconTintResource(@ColorRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipIconTintResource(id);
        }
    }

    public void setChipIconTint(@Nullable ColorStateList colorStateList) {
        ColorStateList chipIconTint = colorStateList;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipIconTint(chipIconTint);
        }
    }

    public float getChipIconSize() {
        return this.chipDrawable != null ? this.chipDrawable.getChipIconSize() : 0.0f;
    }

    public void setChipIconSizeResource(@DimenRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipIconSizeResource(id);
        }
    }

    public void setChipIconSize(float f) {
        float chipIconSize = f;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipIconSize(chipIconSize);
        }
    }

    public boolean isCloseIconVisible() {
        return this.chipDrawable != null && this.chipDrawable.isCloseIconVisible();
    }

    @Deprecated
    public boolean isCloseIconEnabled() {
        return isCloseIconVisible();
    }

    public void setCloseIconVisible(@BoolRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCloseIconVisible(id);
        }
    }

    public void setCloseIconVisible(boolean z) {
        boolean closeIconVisible = z;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCloseIconVisible(closeIconVisible);
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
        return this.chipDrawable != null ? this.chipDrawable.getCloseIcon() : null;
    }

    public void setCloseIconResource(@DrawableRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCloseIconResource(id);
        }
    }

    public void setCloseIcon(@Nullable Drawable drawable) {
        Drawable closeIcon = drawable;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCloseIcon(closeIcon);
        }
    }

    @Nullable
    public ColorStateList getCloseIconTint() {
        return this.chipDrawable != null ? this.chipDrawable.getCloseIconTint() : null;
    }

    public void setCloseIconTintResource(@ColorRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCloseIconTintResource(id);
        }
    }

    public void setCloseIconTint(@Nullable ColorStateList colorStateList) {
        ColorStateList closeIconTint = colorStateList;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCloseIconTint(closeIconTint);
        }
    }

    public float getCloseIconSize() {
        return this.chipDrawable != null ? this.chipDrawable.getCloseIconSize() : 0.0f;
    }

    public void setCloseIconSizeResource(@DimenRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCloseIconSizeResource(id);
        }
    }

    public void setCloseIconSize(float f) {
        float closeIconSize = f;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCloseIconSize(closeIconSize);
        }
    }

    public void setCloseIconContentDescription(@Nullable CharSequence charSequence) {
        CharSequence closeIconContentDescription = charSequence;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCloseIconContentDescription(closeIconContentDescription);
        }
    }

    @Nullable
    public CharSequence getCloseIconContentDescription() {
        return this.chipDrawable != null ? this.chipDrawable.getCloseIconContentDescription() : null;
    }

    public boolean isCheckable() {
        return this.chipDrawable != null && this.chipDrawable.isCheckable();
    }

    public void setCheckableResource(@BoolRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCheckableResource(id);
        }
    }

    public void setCheckable(boolean z) {
        boolean checkable = z;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCheckable(checkable);
        }
    }

    public boolean isCheckedIconVisible() {
        return this.chipDrawable != null && this.chipDrawable.isCheckedIconVisible();
    }

    @Deprecated
    public boolean isCheckedIconEnabled() {
        return isCheckedIconVisible();
    }

    public void setCheckedIconVisible(@BoolRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCheckedIconVisible(id);
        }
    }

    public void setCheckedIconVisible(boolean z) {
        boolean checkedIconVisible = z;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCheckedIconVisible(checkedIconVisible);
        }
    }

    @Deprecated
    public void setCheckedIconEnabledResource(@BoolRes int id) {
        setCheckedIconVisible(id);
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean checkedIconEnabled) {
        setCheckedIconVisible(checkedIconEnabled);
    }

    @Nullable
    public Drawable getCheckedIcon() {
        return this.chipDrawable != null ? this.chipDrawable.getCheckedIcon() : null;
    }

    public void setCheckedIconResource(@DrawableRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCheckedIconResource(id);
        }
    }

    public void setCheckedIcon(@Nullable Drawable drawable) {
        Drawable checkedIcon = drawable;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCheckedIcon(checkedIcon);
        }
    }

    @Nullable
    public MotionSpec getShowMotionSpec() {
        return this.chipDrawable != null ? this.chipDrawable.getShowMotionSpec() : null;
    }

    public void setShowMotionSpecResource(@AnimatorRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setShowMotionSpecResource(id);
        }
    }

    public void setShowMotionSpec(@Nullable MotionSpec motionSpec) {
        MotionSpec showMotionSpec = motionSpec;
        if (this.chipDrawable != null) {
            this.chipDrawable.setShowMotionSpec(showMotionSpec);
        }
    }

    @Nullable
    public MotionSpec getHideMotionSpec() {
        return this.chipDrawable != null ? this.chipDrawable.getHideMotionSpec() : null;
    }

    public void setHideMotionSpecResource(@AnimatorRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setHideMotionSpecResource(id);
        }
    }

    public void setHideMotionSpec(@Nullable MotionSpec motionSpec) {
        MotionSpec hideMotionSpec = motionSpec;
        if (this.chipDrawable != null) {
            this.chipDrawable.setHideMotionSpec(hideMotionSpec);
        }
    }

    public float getChipStartPadding() {
        return this.chipDrawable != null ? this.chipDrawable.getChipStartPadding() : 0.0f;
    }

    public void setChipStartPaddingResource(@DimenRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipStartPaddingResource(id);
        }
    }

    public void setChipStartPadding(float f) {
        float chipStartPadding = f;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipStartPadding(chipStartPadding);
        }
    }

    public float getIconStartPadding() {
        return this.chipDrawable != null ? this.chipDrawable.getIconStartPadding() : 0.0f;
    }

    public void setIconStartPaddingResource(@DimenRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setIconStartPaddingResource(id);
        }
    }

    public void setIconStartPadding(float f) {
        float iconStartPadding = f;
        if (this.chipDrawable != null) {
            this.chipDrawable.setIconStartPadding(iconStartPadding);
        }
    }

    public float getIconEndPadding() {
        return this.chipDrawable != null ? this.chipDrawable.getIconEndPadding() : 0.0f;
    }

    public void setIconEndPaddingResource(@DimenRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setIconEndPaddingResource(id);
        }
    }

    public void setIconEndPadding(float f) {
        float iconEndPadding = f;
        if (this.chipDrawable != null) {
            this.chipDrawable.setIconEndPadding(iconEndPadding);
        }
    }

    public float getTextStartPadding() {
        return this.chipDrawable != null ? this.chipDrawable.getTextStartPadding() : 0.0f;
    }

    public void setTextStartPaddingResource(@DimenRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setTextStartPaddingResource(id);
        }
    }

    public void setTextStartPadding(float f) {
        float textStartPadding = f;
        if (this.chipDrawable != null) {
            this.chipDrawable.setTextStartPadding(textStartPadding);
        }
    }

    public float getTextEndPadding() {
        return this.chipDrawable != null ? this.chipDrawable.getTextEndPadding() : 0.0f;
    }

    public void setTextEndPaddingResource(@DimenRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setTextEndPaddingResource(id);
        }
    }

    public void setTextEndPadding(float f) {
        float textEndPadding = f;
        if (this.chipDrawable != null) {
            this.chipDrawable.setTextEndPadding(textEndPadding);
        }
    }

    public float getCloseIconStartPadding() {
        return this.chipDrawable != null ? this.chipDrawable.getCloseIconStartPadding() : 0.0f;
    }

    public void setCloseIconStartPaddingResource(@DimenRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCloseIconStartPaddingResource(id);
        }
    }

    public void setCloseIconStartPadding(float f) {
        float closeIconStartPadding = f;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCloseIconStartPadding(closeIconStartPadding);
        }
    }

    public float getCloseIconEndPadding() {
        return this.chipDrawable != null ? this.chipDrawable.getCloseIconEndPadding() : 0.0f;
    }

    public void setCloseIconEndPaddingResource(@DimenRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCloseIconEndPaddingResource(id);
        }
    }

    public void setCloseIconEndPadding(float f) {
        float closeIconEndPadding = f;
        if (this.chipDrawable != null) {
            this.chipDrawable.setCloseIconEndPadding(closeIconEndPadding);
        }
    }

    public float getChipEndPadding() {
        return this.chipDrawable != null ? this.chipDrawable.getChipEndPadding() : 0.0f;
    }

    public void setChipEndPaddingResource(@DimenRes int i) {
        int id = i;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipEndPaddingResource(id);
        }
    }

    public void setChipEndPadding(float f) {
        float chipEndPadding = f;
        if (this.chipDrawable != null) {
            this.chipDrawable.setChipEndPadding(chipEndPadding);
        }
    }
}
