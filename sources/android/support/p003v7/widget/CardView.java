package android.support.p003v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.C0015Px;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v7.cardview.C0429R;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import gnu.expr.Declaration;

/* renamed from: android.support.v7.widget.CardView */
public class CardView extends FrameLayout {
    private static final int[] COLOR_BACKGROUND_ATTR = {16842801};
    private static final CardViewImpl IMPL;
    private final CardViewDelegate mCardViewDelegate;
    private boolean mCompatPadding;
    final Rect mContentPadding;
    private boolean mPreventCornerOverlap;
    final Rect mShadowBounds;
    int mUserSetMinHeight;
    int mUserSetMinWidth;

    static {
        CardViewImpl cardViewImpl;
        CardViewImpl cardViewImpl2;
        CardViewImpl cardViewImpl3;
        if (Build.VERSION.SDK_INT >= 21) {
            new CardViewApi21Impl();
            IMPL = cardViewImpl3;
        } else if (Build.VERSION.SDK_INT >= 17) {
            new CardViewApi17Impl();
            IMPL = cardViewImpl2;
        } else {
            new CardViewBaseImpl();
            IMPL = cardViewImpl;
        }
        IMPL.initStatic();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CardView(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, C0429R.attr.cardViewStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CardView(@android.support.annotation.NonNull android.content.Context r19, @android.support.annotation.Nullable android.util.AttributeSet r20, int r21) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r10 = r0
            r11 = r1
            r12 = r2
            r13 = r3
            r10.<init>(r11, r12, r13)
            r10 = r0
            android.graphics.Rect r11 = new android.graphics.Rect
            r17 = r11
            r11 = r17
            r12 = r17
            r12.<init>()
            r10.mContentPadding = r11
            r10 = r0
            android.graphics.Rect r11 = new android.graphics.Rect
            r17 = r11
            r11 = r17
            r12 = r17
            r12.<init>()
            r10.mShadowBounds = r11
            r10 = r0
            android.support.v7.widget.CardView$1 r11 = new android.support.v7.widget.CardView$1
            r17 = r11
            r11 = r17
            r12 = r17
            r13 = r0
            r12.<init>(r13)
            r10.mCardViewDelegate = r11
            r10 = r1
            r11 = r2
            int[] r12 = android.support.p003v7.cardview.C0429R.styleable.CardView
            r13 = r3
            int r14 = android.support.p003v7.cardview.C0429R.C0430style.CardView
            android.content.res.TypedArray r10 = r10.obtainStyledAttributes(r11, r12, r13, r14)
            r4 = r10
            r10 = r4
            int r11 = android.support.p003v7.cardview.C0429R.styleable.CardView_cardBackgroundColor
            boolean r10 = r10.hasValue(r11)
            if (r10 == 0) goto L_0x00f6
            r10 = r4
            int r11 = android.support.p003v7.cardview.C0429R.styleable.CardView_cardBackgroundColor
            android.content.res.ColorStateList r10 = r10.getColorStateList(r11)
            r5 = r10
        L_0x0057:
            r10 = r4
            int r11 = android.support.p003v7.cardview.C0429R.styleable.CardView_cardCornerRadius
            r12 = 0
            float r10 = r10.getDimension(r11, r12)
            r6 = r10
            r10 = r4
            int r11 = android.support.p003v7.cardview.C0429R.styleable.CardView_cardElevation
            r12 = 0
            float r10 = r10.getDimension(r11, r12)
            r7 = r10
            r10 = r4
            int r11 = android.support.p003v7.cardview.C0429R.styleable.CardView_cardMaxElevation
            r12 = 0
            float r10 = r10.getDimension(r11, r12)
            r8 = r10
            r10 = r0
            r11 = r4
            int r12 = android.support.p003v7.cardview.C0429R.styleable.CardView_cardUseCompatPadding
            r13 = 0
            boolean r11 = r11.getBoolean(r12, r13)
            r10.mCompatPadding = r11
            r10 = r0
            r11 = r4
            int r12 = android.support.p003v7.cardview.C0429R.styleable.CardView_cardPreventCornerOverlap
            r13 = 1
            boolean r11 = r11.getBoolean(r12, r13)
            r10.mPreventCornerOverlap = r11
            r10 = r4
            int r11 = android.support.p003v7.cardview.C0429R.styleable.CardView_contentPadding
            r12 = 0
            int r10 = r10.getDimensionPixelSize(r11, r12)
            r9 = r10
            r10 = r0
            android.graphics.Rect r10 = r10.mContentPadding
            r11 = r4
            int r12 = android.support.p003v7.cardview.C0429R.styleable.CardView_contentPaddingLeft
            r13 = r9
            int r11 = r11.getDimensionPixelSize(r12, r13)
            r10.left = r11
            r10 = r0
            android.graphics.Rect r10 = r10.mContentPadding
            r11 = r4
            int r12 = android.support.p003v7.cardview.C0429R.styleable.CardView_contentPaddingTop
            r13 = r9
            int r11 = r11.getDimensionPixelSize(r12, r13)
            r10.top = r11
            r10 = r0
            android.graphics.Rect r10 = r10.mContentPadding
            r11 = r4
            int r12 = android.support.p003v7.cardview.C0429R.styleable.CardView_contentPaddingRight
            r13 = r9
            int r11 = r11.getDimensionPixelSize(r12, r13)
            r10.right = r11
            r10 = r0
            android.graphics.Rect r10 = r10.mContentPadding
            r11 = r4
            int r12 = android.support.p003v7.cardview.C0429R.styleable.CardView_contentPaddingBottom
            r13 = r9
            int r11 = r11.getDimensionPixelSize(r12, r13)
            r10.bottom = r11
            r10 = r7
            r11 = r8
            int r10 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r10 <= 0) goto L_0x00cd
            r10 = r7
            r8 = r10
        L_0x00cd:
            r10 = r0
            r11 = r4
            int r12 = android.support.p003v7.cardview.C0429R.styleable.CardView_android_minWidth
            r13 = 0
            int r11 = r11.getDimensionPixelSize(r12, r13)
            r10.mUserSetMinWidth = r11
            r10 = r0
            r11 = r4
            int r12 = android.support.p003v7.cardview.C0429R.styleable.CardView_android_minHeight
            r13 = 0
            int r11 = r11.getDimensionPixelSize(r12, r13)
            r10.mUserSetMinHeight = r11
            r10 = r4
            r10.recycle()
            android.support.v7.widget.CardViewImpl r10 = IMPL
            r11 = r0
            android.support.v7.widget.CardViewDelegate r11 = r11.mCardViewDelegate
            r12 = r1
            r13 = r5
            r14 = r6
            r15 = r7
            r16 = r8
            r10.initialize(r11, r12, r13, r14, r15, r16)
            return
        L_0x00f6:
            r10 = r0
            android.content.Context r10 = r10.getContext()
            int[] r11 = COLOR_BACKGROUND_ATTR
            android.content.res.TypedArray r10 = r10.obtainStyledAttributes(r11)
            r6 = r10
            r10 = r6
            r11 = 0
            r12 = 0
            int r10 = r10.getColor(r11, r12)
            r7 = r10
            r10 = r6
            r10.recycle()
            r10 = 3
            float[] r10 = new float[r10]
            r8 = r10
            r10 = r7
            r11 = r8
            android.graphics.Color.colorToHSV(r10, r11)
            r10 = r8
            r11 = 2
            r10 = r10[r11]
            r11 = 1056964608(0x3f000000, float:0.5)
            int r10 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r10 <= 0) goto L_0x0133
            r10 = r0
            android.content.res.Resources r10 = r10.getResources()
            int r11 = android.support.p003v7.cardview.C0429R.color.cardview_light_background
            int r10 = r10.getColor(r11)
        L_0x012c:
            android.content.res.ColorStateList r10 = android.content.res.ColorStateList.valueOf(r10)
            r5 = r10
            goto L_0x0057
        L_0x0133:
            r10 = r0
            android.content.res.Resources r10 = r10.getResources()
            int r11 = android.support.p003v7.cardview.C0429R.color.cardview_dark_background
            int r10 = r10.getColor(r11)
            goto L_0x012c
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.CardView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setPadding(int left, int top, int right, int bottom) {
    }

    public void setPaddingRelative(int start, int top, int end, int bottom) {
    }

    public boolean getUseCompatPadding() {
        return this.mCompatPadding;
    }

    public void setUseCompatPadding(boolean z) {
        boolean useCompatPadding = z;
        if (this.mCompatPadding != useCompatPadding) {
            this.mCompatPadding = useCompatPadding;
            IMPL.onCompatPaddingChanged(this.mCardViewDelegate);
        }
    }

    public void setContentPadding(@C0015Px int left, @C0015Px int top, @C0015Px int right, @C0015Px int bottom) {
        this.mContentPadding.set(left, top, right, bottom);
        IMPL.updatePadding(this.mCardViewDelegate);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        if (!(IMPL instanceof CardViewApi21Impl)) {
            int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
            switch (widthMode) {
                case Integer.MIN_VALUE:
                case Declaration.MODULE_REFERENCE /*1073741824*/:
                    widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil((double) IMPL.getMinWidth(this.mCardViewDelegate)), View.MeasureSpec.getSize(widthMeasureSpec)), widthMode);
                    break;
            }
            int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
            switch (heightMode) {
                case Integer.MIN_VALUE:
                case Declaration.MODULE_REFERENCE /*1073741824*/:
                    heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil((double) IMPL.getMinHeight(this.mCardViewDelegate)), View.MeasureSpec.getSize(heightMeasureSpec)), heightMode);
                    break;
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setMinimumWidth(int i) {
        int minWidth = i;
        this.mUserSetMinWidth = minWidth;
        super.setMinimumWidth(minWidth);
    }

    public void setMinimumHeight(int i) {
        int minHeight = i;
        this.mUserSetMinHeight = minHeight;
        super.setMinimumHeight(minHeight);
    }

    public void setCardBackgroundColor(@ColorInt int color) {
        IMPL.setBackgroundColor(this.mCardViewDelegate, ColorStateList.valueOf(color));
    }

    public void setCardBackgroundColor(@Nullable ColorStateList color) {
        IMPL.setBackgroundColor(this.mCardViewDelegate, color);
    }

    @NonNull
    public ColorStateList getCardBackgroundColor() {
        return IMPL.getBackgroundColor(this.mCardViewDelegate);
    }

    @C0015Px
    public int getContentPaddingLeft() {
        return this.mContentPadding.left;
    }

    @C0015Px
    public int getContentPaddingRight() {
        return this.mContentPadding.right;
    }

    @C0015Px
    public int getContentPaddingTop() {
        return this.mContentPadding.top;
    }

    @C0015Px
    public int getContentPaddingBottom() {
        return this.mContentPadding.bottom;
    }

    public void setRadius(float radius) {
        IMPL.setRadius(this.mCardViewDelegate, radius);
    }

    public float getRadius() {
        return IMPL.getRadius(this.mCardViewDelegate);
    }

    public void setCardElevation(float elevation) {
        IMPL.setElevation(this.mCardViewDelegate, elevation);
    }

    public float getCardElevation() {
        return IMPL.getElevation(this.mCardViewDelegate);
    }

    public void setMaxCardElevation(float maxElevation) {
        IMPL.setMaxElevation(this.mCardViewDelegate, maxElevation);
    }

    public float getMaxCardElevation() {
        return IMPL.getMaxElevation(this.mCardViewDelegate);
    }

    public boolean getPreventCornerOverlap() {
        return this.mPreventCornerOverlap;
    }

    public void setPreventCornerOverlap(boolean z) {
        boolean preventCornerOverlap = z;
        if (preventCornerOverlap != this.mPreventCornerOverlap) {
            this.mPreventCornerOverlap = preventCornerOverlap;
            IMPL.onPreventCornerOverlapChanged(this.mCardViewDelegate);
        }
    }
}
