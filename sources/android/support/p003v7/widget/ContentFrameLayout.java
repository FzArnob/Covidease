package android.support.p003v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import gnu.expr.Declaration;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* renamed from: android.support.v7.widget.ContentFrameLayout */
public class ContentFrameLayout extends FrameLayout {
    private OnAttachListener mAttachListener;
    private final Rect mDecorPadding;
    private TypedValue mFixedHeightMajor;
    private TypedValue mFixedHeightMinor;
    private TypedValue mFixedWidthMajor;
    private TypedValue mFixedWidthMinor;
    private TypedValue mMinWidthMajor;
    private TypedValue mMinWidthMinor;

    /* renamed from: android.support.v7.widget.ContentFrameLayout$OnAttachListener */
    public interface OnAttachListener {
        void onAttachedFromWindow();

        void onDetachedFromWindow();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ContentFrameLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ContentFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContentFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Rect rect;
        new Rect();
        this.mDecorPadding = rect;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void dispatchFitSystemWindows(Rect insets) {
        boolean fitSystemWindows = fitSystemWindows(insets);
    }

    public void setAttachListener(OnAttachListener attachListener) {
        OnAttachListener onAttachListener = attachListener;
        this.mAttachListener = onAttachListener;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setDecorPadding(int left, int top, int right, int bottom) {
        this.mDecorPadding.set(left, top, right, bottom);
        if (ViewCompat.isLaidOut(this)) {
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        boolean isPortrait = metrics.widthPixels < metrics.heightPixels;
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        boolean fixedWidth = false;
        if (widthMode == Integer.MIN_VALUE) {
            TypedValue tvw = isPortrait ? this.mFixedWidthMinor : this.mFixedWidthMajor;
            if (!(tvw == null || tvw.type == 0)) {
                int w = 0;
                if (tvw.type == 5) {
                    w = (int) tvw.getDimension(metrics);
                } else if (tvw.type == 6) {
                    w = (int) tvw.getFraction((float) metrics.widthPixels, (float) metrics.widthPixels);
                }
                if (w > 0) {
                    widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(w - (this.mDecorPadding.left + this.mDecorPadding.right), View.MeasureSpec.getSize(widthMeasureSpec)), Declaration.MODULE_REFERENCE);
                    fixedWidth = true;
                }
            }
        }
        if (heightMode == Integer.MIN_VALUE) {
            TypedValue tvh = isPortrait ? this.mFixedHeightMajor : this.mFixedHeightMinor;
            if (!(tvh == null || tvh.type == 0)) {
                int h = 0;
                if (tvh.type == 5) {
                    h = (int) tvh.getDimension(metrics);
                } else if (tvh.type == 6) {
                    h = (int) tvh.getFraction((float) metrics.heightPixels, (float) metrics.heightPixels);
                }
                if (h > 0) {
                    heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(h - (this.mDecorPadding.top + this.mDecorPadding.bottom), View.MeasureSpec.getSize(heightMeasureSpec)), Declaration.MODULE_REFERENCE);
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        boolean measure = false;
        int widthMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(width, Declaration.MODULE_REFERENCE);
        if (!fixedWidth && widthMode == Integer.MIN_VALUE) {
            TypedValue tv = isPortrait ? this.mMinWidthMinor : this.mMinWidthMajor;
            if (!(tv == null || tv.type == 0)) {
                int min = 0;
                if (tv.type == 5) {
                    min = (int) tv.getDimension(metrics);
                } else if (tv.type == 6) {
                    min = (int) tv.getFraction((float) metrics.widthPixels, (float) metrics.widthPixels);
                }
                if (min > 0) {
                    min -= this.mDecorPadding.left + this.mDecorPadding.right;
                }
                if (width < min) {
                    widthMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, Declaration.MODULE_REFERENCE);
                    measure = true;
                }
            }
        }
        if (measure) {
            super.onMeasure(widthMeasureSpec2, heightMeasureSpec);
        }
    }

    public TypedValue getMinWidthMajor() {
        TypedValue typedValue;
        if (this.mMinWidthMajor == null) {
            new TypedValue();
            this.mMinWidthMajor = typedValue;
        }
        return this.mMinWidthMajor;
    }

    public TypedValue getMinWidthMinor() {
        TypedValue typedValue;
        if (this.mMinWidthMinor == null) {
            new TypedValue();
            this.mMinWidthMinor = typedValue;
        }
        return this.mMinWidthMinor;
    }

    public TypedValue getFixedWidthMajor() {
        TypedValue typedValue;
        if (this.mFixedWidthMajor == null) {
            new TypedValue();
            this.mFixedWidthMajor = typedValue;
        }
        return this.mFixedWidthMajor;
    }

    public TypedValue getFixedWidthMinor() {
        TypedValue typedValue;
        if (this.mFixedWidthMinor == null) {
            new TypedValue();
            this.mFixedWidthMinor = typedValue;
        }
        return this.mFixedWidthMinor;
    }

    public TypedValue getFixedHeightMajor() {
        TypedValue typedValue;
        if (this.mFixedHeightMajor == null) {
            new TypedValue();
            this.mFixedHeightMajor = typedValue;
        }
        return this.mFixedHeightMajor;
    }

    public TypedValue getFixedHeightMinor() {
        TypedValue typedValue;
        if (this.mFixedHeightMinor == null) {
            new TypedValue();
            this.mFixedHeightMinor = typedValue;
        }
        return this.mFixedHeightMinor;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mAttachListener != null) {
            this.mAttachListener.onAttachedFromWindow();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAttachListener != null) {
            this.mAttachListener.onDetachedFromWindow();
        }
    }
}
