package android.support.p000v4.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* renamed from: android.support.v4.view.PagerTabStrip */
public class PagerTabStrip extends PagerTitleStrip {
    private static final int FULL_UNDERLINE_HEIGHT = 1;
    private static final int INDICATOR_HEIGHT = 3;
    private static final int MIN_PADDING_BOTTOM = 6;
    private static final int MIN_STRIP_HEIGHT = 32;
    private static final int MIN_TEXT_SPACING = 64;
    private static final int TAB_PADDING = 16;
    private static final int TAB_SPACING = 32;
    private static final String TAG = "PagerTabStrip";
    private boolean mDrawFullUnderline;
    private boolean mDrawFullUnderlineSet;
    private int mFullUnderlineHeight;
    private boolean mIgnoreTap;
    private int mIndicatorColor;
    private int mIndicatorHeight;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private int mMinPaddingBottom;
    private int mMinStripHeight;
    private int mMinTextSpacing;
    private int mTabAlpha;
    private int mTabPadding;
    private final Paint mTabPaint;
    private final Rect mTempRect;
    private int mTouchSlop;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PagerTabStrip(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PagerTabStrip(@android.support.annotation.NonNull android.content.Context r11, @android.support.annotation.Nullable android.util.AttributeSet r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r4 = r0
            r5 = r1
            r6 = r2
            r4.<init>(r5, r6)
            r4 = r0
            android.graphics.Paint r5 = new android.graphics.Paint
            r9 = r5
            r5 = r9
            r6 = r9
            r6.<init>()
            r4.mTabPaint = r5
            r4 = r0
            android.graphics.Rect r5 = new android.graphics.Rect
            r9 = r5
            r5 = r9
            r6 = r9
            r6.<init>()
            r4.mTempRect = r5
            r4 = r0
            r5 = 255(0xff, float:3.57E-43)
            r4.mTabAlpha = r5
            r4 = r0
            r5 = 0
            r4.mDrawFullUnderline = r5
            r4 = r0
            r5 = 0
            r4.mDrawFullUnderlineSet = r5
            r4 = r0
            r5 = r0
            int r5 = r5.mTextColor
            r4.mIndicatorColor = r5
            r4 = r0
            android.graphics.Paint r4 = r4.mTabPaint
            r5 = r0
            int r5 = r5.mIndicatorColor
            r4.setColor(r5)
            r4 = r1
            android.content.res.Resources r4 = r4.getResources()
            android.util.DisplayMetrics r4 = r4.getDisplayMetrics()
            float r4 = r4.density
            r3 = r4
            r4 = r0
            r5 = 1077936128(0x40400000, float:3.0)
            r6 = r3
            float r5 = r5 * r6
            r6 = 1056964608(0x3f000000, float:0.5)
            float r5 = r5 + r6
            int r5 = (int) r5
            r4.mIndicatorHeight = r5
            r4 = r0
            r5 = 1086324736(0x40c00000, float:6.0)
            r6 = r3
            float r5 = r5 * r6
            r6 = 1056964608(0x3f000000, float:0.5)
            float r5 = r5 + r6
            int r5 = (int) r5
            r4.mMinPaddingBottom = r5
            r4 = r0
            r5 = 1115684864(0x42800000, float:64.0)
            r6 = r3
            float r5 = r5 * r6
            int r5 = (int) r5
            r4.mMinTextSpacing = r5
            r4 = r0
            r5 = 1098907648(0x41800000, float:16.0)
            r6 = r3
            float r5 = r5 * r6
            r6 = 1056964608(0x3f000000, float:0.5)
            float r5 = r5 + r6
            int r5 = (int) r5
            r4.mTabPadding = r5
            r4 = r0
            r5 = 1065353216(0x3f800000, float:1.0)
            r6 = r3
            float r5 = r5 * r6
            r6 = 1056964608(0x3f000000, float:0.5)
            float r5 = r5 + r6
            int r5 = (int) r5
            r4.mFullUnderlineHeight = r5
            r4 = r0
            r5 = 1107296256(0x42000000, float:32.0)
            r6 = r3
            float r5 = r5 * r6
            r6 = 1056964608(0x3f000000, float:0.5)
            float r5 = r5 + r6
            int r5 = (int) r5
            r4.mMinStripHeight = r5
            r4 = r0
            r5 = r1
            android.view.ViewConfiguration r5 = android.view.ViewConfiguration.get(r5)
            int r5 = r5.getScaledTouchSlop()
            r4.mTouchSlop = r5
            r4 = r0
            r5 = r0
            int r5 = r5.getPaddingLeft()
            r6 = r0
            int r6 = r6.getPaddingTop()
            r7 = r0
            int r7 = r7.getPaddingRight()
            r8 = r0
            int r8 = r8.getPaddingBottom()
            r4.setPadding(r5, r6, r7, r8)
            r4 = r0
            r5 = r0
            int r5 = r5.getTextSpacing()
            r4.setTextSpacing(r5)
            r4 = r0
            r5 = 0
            r4.setWillNotDraw(r5)
            r4 = r0
            android.widget.TextView r4 = r4.mPrevText
            r5 = 1
            r4.setFocusable(r5)
            r4 = r0
            android.widget.TextView r4 = r4.mPrevText
            android.support.v4.view.PagerTabStrip$1 r5 = new android.support.v4.view.PagerTabStrip$1
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = r0
            r6.<init>(r7)
            r4.setOnClickListener(r5)
            r4 = r0
            android.widget.TextView r4 = r4.mNextText
            r5 = 1
            r4.setFocusable(r5)
            r4 = r0
            android.widget.TextView r4 = r4.mNextText
            android.support.v4.view.PagerTabStrip$2 r5 = new android.support.v4.view.PagerTabStrip$2
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = r0
            r6.<init>(r7)
            r4.setOnClickListener(r5)
            r4 = r0
            android.graphics.drawable.Drawable r4 = r4.getBackground()
            if (r4 != 0) goto L_0x00ef
            r4 = r0
            r5 = 1
            r4.mDrawFullUnderline = r5
        L_0x00ef:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.view.PagerTabStrip.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public void setTabIndicatorColor(@ColorInt int color) {
        this.mIndicatorColor = color;
        this.mTabPaint.setColor(this.mIndicatorColor);
        invalidate();
    }

    public void setTabIndicatorColorResource(@ColorRes int resId) {
        setTabIndicatorColor(ContextCompat.getColor(getContext(), resId));
    }

    @ColorInt
    public int getTabIndicatorColor() {
        return this.mIndicatorColor;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        int left = i;
        int top = i2;
        int right = i3;
        int bottom = i4;
        if (bottom < this.mMinPaddingBottom) {
            bottom = this.mMinPaddingBottom;
        }
        super.setPadding(left, top, right, bottom);
    }

    public void setTextSpacing(int i) {
        int textSpacing = i;
        if (textSpacing < this.mMinTextSpacing) {
            textSpacing = this.mMinTextSpacing;
        }
        super.setTextSpacing(textSpacing);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        Drawable d = drawable;
        super.setBackgroundDrawable(d);
        if (!this.mDrawFullUnderlineSet) {
            this.mDrawFullUnderline = d == null;
        }
    }

    public void setBackgroundColor(@ColorInt int i) {
        int color = i;
        super.setBackgroundColor(color);
        if (!this.mDrawFullUnderlineSet) {
            this.mDrawFullUnderline = (color & -16777216) == 0;
        }
    }

    public void setBackgroundResource(@DrawableRes int i) {
        int resId = i;
        super.setBackgroundResource(resId);
        if (!this.mDrawFullUnderlineSet) {
            this.mDrawFullUnderline = resId == 0;
        }
    }

    public void setDrawFullUnderline(boolean drawFull) {
        this.mDrawFullUnderline = drawFull;
        this.mDrawFullUnderlineSet = true;
        invalidate();
    }

    public boolean getDrawFullUnderline() {
        return this.mDrawFullUnderline;
    }

    /* access modifiers changed from: package-private */
    public int getMinHeight() {
        return Math.max(super.getMinHeight(), this.mMinStripHeight);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        int action = ev.getAction();
        if (action != 0 && this.mIgnoreTap) {
            return false;
        }
        float x = ev.getX();
        float y = ev.getY();
        switch (action) {
            case 0:
                this.mInitialMotionX = x;
                this.mInitialMotionY = y;
                this.mIgnoreTap = false;
                break;
            case 1:
                if (x >= ((float) (this.mCurrText.getLeft() - this.mTabPadding))) {
                    if (x > ((float) (this.mCurrText.getRight() + this.mTabPadding))) {
                        this.mPager.setCurrentItem(this.mPager.getCurrentItem() + 1);
                        break;
                    }
                } else {
                    this.mPager.setCurrentItem(this.mPager.getCurrentItem() - 1);
                    break;
                }
                break;
            case 2:
                if (Math.abs(x - this.mInitialMotionX) > ((float) this.mTouchSlop) || Math.abs(y - this.mInitialMotionY) > ((float) this.mTouchSlop)) {
                    this.mIgnoreTap = true;
                    break;
                }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        super.onDraw(canvas2);
        int height = getHeight();
        int bottom = height;
        int left = this.mCurrText.getLeft() - this.mTabPadding;
        int right = this.mCurrText.getRight() + this.mTabPadding;
        int top = bottom - this.mIndicatorHeight;
        this.mTabPaint.setColor((this.mTabAlpha << 24) | (this.mIndicatorColor & 16777215));
        canvas2.drawRect((float) left, (float) top, (float) right, (float) bottom, this.mTabPaint);
        if (this.mDrawFullUnderline) {
            this.mTabPaint.setColor(-16777216 | (this.mIndicatorColor & 16777215));
            canvas2.drawRect((float) getPaddingLeft(), (float) (height - this.mFullUnderlineHeight), (float) (getWidth() - getPaddingRight()), (float) height, this.mTabPaint);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateTextPositions(int position, float f, boolean force) {
        float positionOffset = f;
        Rect r = this.mTempRect;
        int bottom = getHeight();
        int left = this.mCurrText.getLeft() - this.mTabPadding;
        int right = this.mCurrText.getRight() + this.mTabPadding;
        int top = bottom - this.mIndicatorHeight;
        r.set(left, top, right, bottom);
        super.updateTextPositions(position, positionOffset, force);
        this.mTabAlpha = (int) (Math.abs(positionOffset - 0.5f) * 2.0f * 255.0f);
        r.union(this.mCurrText.getLeft() - this.mTabPadding, top, this.mCurrText.getRight() + this.mTabPadding, bottom);
        invalidate(r);
    }
}
