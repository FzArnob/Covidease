package android.support.p000v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.C0015Px;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.AbsSavedState;
import android.support.p000v4.view.AccessibilityDelegateCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p000v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import gnu.expr.Declaration;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* renamed from: android.support.v4.widget.SlidingPaneLayout */
public class SlidingPaneLayout extends ViewGroup {
    private static final int DEFAULT_FADE_COLOR = -858993460;
    private static final int DEFAULT_OVERHANG_SIZE = 32;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final String TAG = "SlidingPaneLayout";
    private boolean mCanSlide;
    private int mCoveredFadeColor;
    private boolean mDisplayListReflectionLoaded;
    final ViewDragHelper mDragHelper;
    private boolean mFirstLayout;
    private Method mGetDisplayList;
    private float mInitialMotionX;
    private float mInitialMotionY;
    boolean mIsUnableToDrag;
    private final int mOverhangSize;
    private PanelSlideListener mPanelSlideListener;
    private int mParallaxBy;
    private float mParallaxOffset;
    final ArrayList<DisableLayerRunnable> mPostedRunnables;
    boolean mPreservedOpenState;
    private Field mRecreateDisplayList;
    private Drawable mShadowDrawableLeft;
    private Drawable mShadowDrawableRight;
    float mSlideOffset;
    int mSlideRange;
    View mSlideableView;
    private int mSliderFadeColor;
    private final Rect mTmpRect;

    /* renamed from: android.support.v4.widget.SlidingPaneLayout$PanelSlideListener */
    public interface PanelSlideListener {
        void onPanelClosed(@NonNull View view);

        void onPanelOpened(@NonNull View view);

        void onPanelSlide(@NonNull View view, float f);
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout$SimplePanelSlideListener */
    public static class SimplePanelSlideListener implements PanelSlideListener {
        public SimplePanelSlideListener() {
        }

        public void onPanelSlide(View panel, float slideOffset) {
        }

        public void onPanelOpened(View panel) {
        }

        public void onPanelClosed(View panel) {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SlidingPaneLayout(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SlidingPaneLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SlidingPaneLayout(@android.support.annotation.NonNull android.content.Context r13, @android.support.annotation.Nullable android.util.AttributeSet r14, int r15) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            r5.<init>(r6, r7, r8)
            r5 = r0
            r6 = -858993460(0xffffffffcccccccc, float:-1.07374176E8)
            r5.mSliderFadeColor = r6
            r5 = r0
            r6 = 1
            r5.mFirstLayout = r6
            r5 = r0
            android.graphics.Rect r6 = new android.graphics.Rect
            r11 = r6
            r6 = r11
            r7 = r11
            r7.<init>()
            r5.mTmpRect = r6
            r5 = r0
            java.util.ArrayList r6 = new java.util.ArrayList
            r11 = r6
            r6 = r11
            r7 = r11
            r7.<init>()
            r5.mPostedRunnables = r6
            r5 = r1
            android.content.res.Resources r5 = r5.getResources()
            android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
            float r5 = r5.density
            r4 = r5
            r5 = r0
            r6 = 1107296256(0x42000000, float:32.0)
            r7 = r4
            float r6 = r6 * r7
            r7 = 1056964608(0x3f000000, float:0.5)
            float r6 = r6 + r7
            int r6 = (int) r6
            r5.mOverhangSize = r6
            r5 = r0
            r6 = 0
            r5.setWillNotDraw(r6)
            r5 = r0
            android.support.v4.widget.SlidingPaneLayout$AccessibilityDelegate r6 = new android.support.v4.widget.SlidingPaneLayout$AccessibilityDelegate
            r11 = r6
            r6 = r11
            r7 = r11
            r8 = r0
            r7.<init>(r8)
            android.support.p000v4.view.ViewCompat.setAccessibilityDelegate(r5, r6)
            r5 = r0
            r6 = 1
            android.support.p000v4.view.ViewCompat.setImportantForAccessibility(r5, r6)
            r5 = r0
            r6 = r0
            r7 = 1056964608(0x3f000000, float:0.5)
            android.support.v4.widget.SlidingPaneLayout$DragHelperCallback r8 = new android.support.v4.widget.SlidingPaneLayout$DragHelperCallback
            r11 = r8
            r8 = r11
            r9 = r11
            r10 = r0
            r9.<init>(r10)
            android.support.v4.widget.ViewDragHelper r6 = android.support.p000v4.widget.ViewDragHelper.create(r6, r7, r8)
            r5.mDragHelper = r6
            r5 = r0
            android.support.v4.widget.ViewDragHelper r5 = r5.mDragHelper
            r6 = 1137180672(0x43c80000, float:400.0)
            r7 = r4
            float r6 = r6 * r7
            r5.setMinVelocity(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.SlidingPaneLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setParallaxDistance(@C0015Px int parallaxBy) {
        this.mParallaxBy = parallaxBy;
        requestLayout();
    }

    @C0015Px
    public int getParallaxDistance() {
        return this.mParallaxBy;
    }

    public void setSliderFadeColor(@ColorInt int color) {
        int i = color;
        this.mSliderFadeColor = i;
    }

    @ColorInt
    public int getSliderFadeColor() {
        return this.mSliderFadeColor;
    }

    public void setCoveredFadeColor(@ColorInt int color) {
        int i = color;
        this.mCoveredFadeColor = i;
    }

    @ColorInt
    public int getCoveredFadeColor() {
        return this.mCoveredFadeColor;
    }

    public void setPanelSlideListener(@Nullable PanelSlideListener listener) {
        PanelSlideListener panelSlideListener = listener;
        this.mPanelSlideListener = panelSlideListener;
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnPanelSlide(View view) {
        View panel = view;
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelSlide(panel, this.mSlideOffset);
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnPanelOpened(View view) {
        View panel = view;
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelOpened(panel);
        }
        sendAccessibilityEvent(32);
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnPanelClosed(View view) {
        View panel = view;
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelClosed(panel);
        }
        sendAccessibilityEvent(32);
    }

    /* access modifiers changed from: package-private */
    public void updateObscuredViewsVisibility(View view) {
        int width;
        int bottom;
        int top;
        int right;
        int left;
        View child;
        int vis;
        View panel = view;
        boolean isLayoutRtl = isLayoutRtlSupport();
        int startBound = isLayoutRtl ? getWidth() - getPaddingRight() : getPaddingLeft();
        if (isLayoutRtl) {
            width = getPaddingLeft();
        } else {
            width = getWidth() - getPaddingRight();
        }
        int endBound = width;
        int topBound = getPaddingTop();
        int bottomBound = getHeight() - getPaddingBottom();
        if (panel == null || !viewIsOpaque(panel)) {
            bottom = 0;
            top = 0;
            right = 0;
            left = 0;
        } else {
            left = panel.getLeft();
            right = panel.getRight();
            top = panel.getTop();
            bottom = panel.getBottom();
        }
        int i = 0;
        int childCount = getChildCount();
        while (i < childCount && (child = getChildAt(i)) != panel) {
            if (child.getVisibility() != 8) {
                int clampedChildLeft = Math.max(isLayoutRtl ? endBound : startBound, child.getLeft());
                int clampedChildTop = Math.max(topBound, child.getTop());
                int clampedChildRight = Math.min(isLayoutRtl ? startBound : endBound, child.getRight());
                int clampedChildBottom = Math.min(bottomBound, child.getBottom());
                if (clampedChildLeft < left || clampedChildTop < top || clampedChildRight > right || clampedChildBottom > bottom) {
                    vis = 0;
                } else {
                    vis = 4;
                }
                child.setVisibility(vis);
            }
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    public void setAllChildrenVisible() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == 4) {
                child.setVisibility(0);
            }
        }
    }

    private static boolean viewIsOpaque(View view) {
        View v = view;
        if (v.isOpaque()) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            return false;
        }
        Drawable bg = v.getBackground();
        if (bg == null) {
            return false;
        }
        return bg.getOpacity() == -1;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
        int count = this.mPostedRunnables.size();
        for (int i = 0; i < count; i++) {
            this.mPostedRunnables.get(i).run();
        }
        this.mPostedRunnables.clear();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        Throwable th;
        int measuredWidth;
        int childHeightSpec;
        int childHeightSpec2;
        int childWidthSpec;
        int childHeightSpec3;
        Throwable th2;
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode != 1073741824) {
            if (!isInEditMode()) {
                Throwable th3 = th2;
                new IllegalStateException("Width must have an exact value or MATCH_PARENT");
                throw th3;
            } else if (widthMode != Integer.MIN_VALUE) {
                if (widthMode == 0) {
                    widthSize = 300;
                }
            }
        } else if (heightMode == 0) {
            if (!isInEditMode()) {
                Throwable th4 = th;
                new IllegalStateException("Height must not be UNSPECIFIED");
                throw th4;
            } else if (heightMode == 0) {
                heightMode = Integer.MIN_VALUE;
                heightSize = 300;
            }
        }
        int layoutHeight = 0;
        int maxLayoutHeight = 0;
        switch (heightMode) {
            case Integer.MIN_VALUE:
                maxLayoutHeight = (heightSize - getPaddingTop()) - getPaddingBottom();
                break;
            case Declaration.MODULE_REFERENCE /*1073741824*/:
                int paddingTop = (heightSize - getPaddingTop()) - getPaddingBottom();
                maxLayoutHeight = paddingTop;
                layoutHeight = paddingTop;
                break;
        }
        float weightSum = 0.0f;
        boolean canSlide = false;
        int widthAvailable = (widthSize - getPaddingLeft()) - getPaddingRight();
        int widthRemaining = widthAvailable;
        int childCount = getChildCount();
        if (childCount > 2) {
            int e = Log.e(TAG, "onMeasure: More than two child views are not supported.");
        }
        this.mSlideableView = null;
        for (int i3 = 0; i3 < childCount; i3++) {
            View child = getChildAt(i3);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (child.getVisibility() == 8) {
                lp.dimWhenOffset = false;
            } else {
                if (lp.weight > 0.0f) {
                    weightSum += lp.weight;
                    if (lp.width == 0) {
                    }
                }
                int horizontalMargin = lp.leftMargin + lp.rightMargin;
                if (lp.width == -2) {
                    childWidthSpec = View.MeasureSpec.makeMeasureSpec(widthAvailable - horizontalMargin, Integer.MIN_VALUE);
                } else if (lp.width == -1) {
                    childWidthSpec = View.MeasureSpec.makeMeasureSpec(widthAvailable - horizontalMargin, Declaration.MODULE_REFERENCE);
                } else {
                    childWidthSpec = View.MeasureSpec.makeMeasureSpec(lp.width, Declaration.MODULE_REFERENCE);
                }
                if (lp.height == -2) {
                    childHeightSpec3 = View.MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                } else if (lp.height == -1) {
                    childHeightSpec3 = View.MeasureSpec.makeMeasureSpec(maxLayoutHeight, Declaration.MODULE_REFERENCE);
                } else {
                    childHeightSpec3 = View.MeasureSpec.makeMeasureSpec(lp.height, Declaration.MODULE_REFERENCE);
                }
                child.measure(childWidthSpec, childHeightSpec3);
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                if (heightMode == Integer.MIN_VALUE && childHeight > layoutHeight) {
                    layoutHeight = Math.min(childHeight, maxLayoutHeight);
                }
                widthRemaining -= childWidth;
                boolean z = canSlide;
                LayoutParams layoutParams = lp;
                boolean z2 = widthRemaining < 0;
                layoutParams.slideable = z2;
                canSlide = z | z2;
                if (lp.slideable) {
                    this.mSlideableView = child;
                }
            }
        }
        if (canSlide || weightSum > 0.0f) {
            int fixedPanelWidthLimit = widthAvailable - this.mOverhangSize;
            for (int i4 = 0; i4 < childCount; i4++) {
                View child2 = getChildAt(i4);
                if (child2.getVisibility() != 8) {
                    LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
                    if (child2.getVisibility() != 8) {
                        boolean skippedFirstPass = lp2.width == 0 && lp2.weight > 0.0f;
                        if (skippedFirstPass) {
                            measuredWidth = 0;
                        } else {
                            measuredWidth = child2.getMeasuredWidth();
                        }
                        int measuredWidth2 = measuredWidth;
                        if (!canSlide || child2 == this.mSlideableView) {
                            if (lp2.weight > 0.0f) {
                                if (lp2.width != 0) {
                                    childHeightSpec = View.MeasureSpec.makeMeasureSpec(child2.getMeasuredHeight(), Declaration.MODULE_REFERENCE);
                                } else if (lp2.height == -2) {
                                    childHeightSpec = View.MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                                } else if (lp2.height == -1) {
                                    childHeightSpec = View.MeasureSpec.makeMeasureSpec(maxLayoutHeight, Declaration.MODULE_REFERENCE);
                                } else {
                                    childHeightSpec = View.MeasureSpec.makeMeasureSpec(lp2.height, Declaration.MODULE_REFERENCE);
                                }
                                if (canSlide) {
                                    int newWidth = widthAvailable - (lp2.leftMargin + lp2.rightMargin);
                                    int childWidthSpec2 = View.MeasureSpec.makeMeasureSpec(newWidth, Declaration.MODULE_REFERENCE);
                                    if (measuredWidth2 != newWidth) {
                                        child2.measure(childWidthSpec2, childHeightSpec);
                                    }
                                } else {
                                    child2.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2 + ((int) ((lp2.weight * ((float) Math.max(0, widthRemaining))) / weightSum)), Declaration.MODULE_REFERENCE), childHeightSpec);
                                }
                            }
                        } else if (lp2.width < 0 && (measuredWidth2 > fixedPanelWidthLimit || lp2.weight > 0.0f)) {
                            if (!skippedFirstPass) {
                                childHeightSpec2 = View.MeasureSpec.makeMeasureSpec(child2.getMeasuredHeight(), Declaration.MODULE_REFERENCE);
                            } else if (lp2.height == -2) {
                                childHeightSpec2 = View.MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                            } else if (lp2.height == -1) {
                                childHeightSpec2 = View.MeasureSpec.makeMeasureSpec(maxLayoutHeight, Declaration.MODULE_REFERENCE);
                            } else {
                                childHeightSpec2 = View.MeasureSpec.makeMeasureSpec(lp2.height, Declaration.MODULE_REFERENCE);
                            }
                            child2.measure(View.MeasureSpec.makeMeasureSpec(fixedPanelWidthLimit, Declaration.MODULE_REFERENCE), childHeightSpec2);
                        }
                    }
                }
            }
        }
        setMeasuredDimension(widthSize, layoutHeight + getPaddingTop() + getPaddingBottom());
        this.mCanSlide = canSlide;
        if (this.mDragHelper.getViewDragState() != 0 && !canSlide) {
            this.mDragHelper.abort();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childLeft;
        int childRight;
        float f;
        boolean z2 = z;
        int l = i;
        int i5 = i2;
        int r = i3;
        int i6 = i4;
        boolean isLayoutRtl = isLayoutRtlSupport();
        if (isLayoutRtl) {
            this.mDragHelper.setEdgeTrackingEnabled(2);
        } else {
            this.mDragHelper.setEdgeTrackingEnabled(1);
        }
        int width = r - l;
        int paddingStart = isLayoutRtl ? getPaddingRight() : getPaddingLeft();
        int paddingEnd = isLayoutRtl ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        int xStart = paddingStart;
        int nextXStart = xStart;
        if (this.mFirstLayout) {
            if (this.mCanSlide) {
                if (this.mPreservedOpenState) {
                    f = 1.0f;
                    this.mSlideOffset = f;
                }
            }
            f = 0.0f;
            this.mSlideOffset = f;
        }
        for (int i7 = 0; i7 < childCount; i7++) {
            View child = getChildAt(i7);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                int childWidth = child.getMeasuredWidth();
                int offset = 0;
                if (lp.slideable) {
                    int range = (Math.min(nextXStart, (width - paddingEnd) - this.mOverhangSize) - xStart) - (lp.leftMargin + lp.rightMargin);
                    this.mSlideRange = range;
                    int lpMargin = isLayoutRtl ? lp.rightMargin : lp.leftMargin;
                    lp.dimWhenOffset = ((xStart + lpMargin) + range) + (childWidth / 2) > width - paddingEnd;
                    int pos = (int) (((float) range) * this.mSlideOffset);
                    xStart += pos + lpMargin;
                    this.mSlideOffset = ((float) pos) / ((float) this.mSlideRange);
                } else {
                    if (this.mCanSlide) {
                        if (this.mParallaxBy != 0) {
                            offset = (int) ((1.0f - this.mSlideOffset) * ((float) this.mParallaxBy));
                            xStart = nextXStart;
                        }
                    }
                    xStart = nextXStart;
                }
                if (isLayoutRtl) {
                    childRight = (width - xStart) + offset;
                    childLeft = childRight - childWidth;
                } else {
                    childLeft = xStart - offset;
                    childRight = childLeft + childWidth;
                }
                child.layout(childLeft, paddingTop, childRight, paddingTop + child.getMeasuredHeight());
                nextXStart += child.getWidth();
            }
        }
        if (this.mFirstLayout) {
            if (this.mCanSlide) {
                if (this.mParallaxBy != 0) {
                    parallaxOtherViews(this.mSlideOffset);
                }
                if (((LayoutParams) this.mSlideableView.getLayoutParams()).dimWhenOffset) {
                    dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
                }
            } else {
                for (int i8 = 0; i8 < childCount; i8++) {
                    dimChildView(getChildAt(i8), 0.0f, this.mSliderFadeColor);
                }
            }
            updateObscuredViewsVisibility(this.mSlideableView);
        }
        this.mFirstLayout = false;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int h, int i2, int oldh) {
        int w = i;
        int oldw = i2;
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw) {
            this.mFirstLayout = true;
        }
    }

    public void requestChildFocus(View view, View focused) {
        View child = view;
        super.requestChildFocus(child, focused);
        if (!isInTouchMode() && !this.mCanSlide) {
            this.mPreservedOpenState = child == this.mSlideableView;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        View secondChild;
        MotionEvent ev = motionEvent;
        int action = ev.getActionMasked();
        if (!this.mCanSlide && action == 0 && getChildCount() > 1 && (secondChild = getChildAt(1)) != null) {
            this.mPreservedOpenState = !this.mDragHelper.isViewUnder(secondChild, (int) ev.getX(), (int) ev.getY());
        }
        if (!this.mCanSlide || (this.mIsUnableToDrag && action != 0)) {
            this.mDragHelper.cancel();
            return super.onInterceptTouchEvent(ev);
        } else if (action == 3 || action == 1) {
            this.mDragHelper.cancel();
            return false;
        } else {
            boolean interceptTap = false;
            switch (action) {
                case 0:
                    this.mIsUnableToDrag = false;
                    float x = ev.getX();
                    float y = ev.getY();
                    this.mInitialMotionX = x;
                    this.mInitialMotionY = y;
                    if (this.mDragHelper.isViewUnder(this.mSlideableView, (int) x, (int) y) && isDimmed(this.mSlideableView)) {
                        interceptTap = true;
                        break;
                    }
                case 2:
                    float x2 = ev.getX();
                    float y2 = ev.getY();
                    float adx = Math.abs(x2 - this.mInitialMotionX);
                    float ady = Math.abs(y2 - this.mInitialMotionY);
                    if (adx > ((float) this.mDragHelper.getTouchSlop()) && ady > adx) {
                        this.mDragHelper.cancel();
                        this.mIsUnableToDrag = true;
                        return false;
                    }
            }
            if (this.mDragHelper.shouldInterceptTouchEvent(ev) || interceptTap) {
                z = true;
            } else {
                z = false;
            }
            return z;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        if (!this.mCanSlide) {
            return super.onTouchEvent(ev);
        }
        this.mDragHelper.processTouchEvent(ev);
        switch (ev.getActionMasked()) {
            case 0:
                float x = ev.getX();
                float y = ev.getY();
                this.mInitialMotionX = x;
                this.mInitialMotionY = y;
                break;
            case 1:
                if (isDimmed(this.mSlideableView)) {
                    float x2 = ev.getX();
                    float y2 = ev.getY();
                    float dx = x2 - this.mInitialMotionX;
                    float dy = y2 - this.mInitialMotionY;
                    int slop = this.mDragHelper.getTouchSlop();
                    if ((dx * dx) + (dy * dy) < ((float) (slop * slop)) && this.mDragHelper.isViewUnder(this.mSlideableView, (int) x2, (int) y2)) {
                        boolean closePane = closePane(this.mSlideableView, 0);
                        break;
                    }
                }
                break;
        }
        return true;
    }

    private boolean closePane(View view, int i) {
        View view2 = view;
        int initialVelocity = i;
        if (!this.mFirstLayout && !smoothSlideTo(0.0f, initialVelocity)) {
            return false;
        }
        this.mPreservedOpenState = false;
        return true;
    }

    private boolean openPane(View view, int i) {
        View view2 = view;
        int initialVelocity = i;
        if (!this.mFirstLayout && !smoothSlideTo(1.0f, initialVelocity)) {
            return false;
        }
        this.mPreservedOpenState = true;
        return true;
    }

    @Deprecated
    public void smoothSlideOpen() {
        boolean openPane = openPane();
    }

    public boolean openPane() {
        return openPane(this.mSlideableView, 0);
    }

    @Deprecated
    public void smoothSlideClosed() {
        boolean closePane = closePane();
    }

    public boolean closePane() {
        return closePane(this.mSlideableView, 0);
    }

    public boolean isOpen() {
        return !this.mCanSlide || this.mSlideOffset == 1.0f;
    }

    @Deprecated
    public boolean canSlide() {
        return this.mCanSlide;
    }

    public boolean isSlideable() {
        return this.mCanSlide;
    }

    /* access modifiers changed from: package-private */
    public void onPanelDragged(int i) {
        int newLeft = i;
        if (this.mSlideableView == null) {
            this.mSlideOffset = 0.0f;
            return;
        }
        boolean isLayoutRtl = isLayoutRtlSupport();
        LayoutParams lp = (LayoutParams) this.mSlideableView.getLayoutParams();
        this.mSlideOffset = ((float) ((isLayoutRtl ? (getWidth() - newLeft) - this.mSlideableView.getWidth() : newLeft) - ((isLayoutRtl ? getPaddingRight() : getPaddingLeft()) + (isLayoutRtl ? lp.rightMargin : lp.leftMargin)))) / ((float) this.mSlideRange);
        if (this.mParallaxBy != 0) {
            parallaxOtherViews(this.mSlideOffset);
        }
        if (lp.dimWhenOffset) {
            dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
        }
        dispatchOnPanelSlide(this.mSlideableView);
    }

    private void dimChildView(View view, float f, int i) {
        DisableLayerRunnable disableLayerRunnable;
        ColorFilter colorFilter;
        Paint paint;
        View v = view;
        float mag = f;
        int fadeColor = i;
        LayoutParams lp = (LayoutParams) v.getLayoutParams();
        if (mag > 0.0f && fadeColor != 0) {
            int color = (((int) (((float) ((fadeColor & -16777216) >>> 24)) * mag)) << 24) | (fadeColor & 16777215);
            if (lp.dimPaint == null) {
                new Paint();
                lp.dimPaint = paint;
            }
            new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_OVER);
            ColorFilter colorFilter2 = lp.dimPaint.setColorFilter(colorFilter);
            if (v.getLayerType() != 2) {
                v.setLayerType(2, lp.dimPaint);
            }
            invalidateChildRegion(v);
        } else if (v.getLayerType() != 0) {
            if (lp.dimPaint != null) {
                ColorFilter colorFilter3 = lp.dimPaint.setColorFilter((ColorFilter) null);
            }
            new DisableLayerRunnable(this, v);
            DisableLayerRunnable dlr = disableLayerRunnable;
            boolean add = this.mPostedRunnables.add(dlr);
            ViewCompat.postOnAnimation(this, dlr);
        }
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        Canvas canvas2 = canvas;
        View child = view;
        long drawingTime = j;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int save = canvas2.save();
        if (this.mCanSlide && !lp.slideable && this.mSlideableView != null) {
            boolean clipBounds = canvas2.getClipBounds(this.mTmpRect);
            if (isLayoutRtlSupport()) {
                this.mTmpRect.left = Math.max(this.mTmpRect.left, this.mSlideableView.getRight());
            } else {
                this.mTmpRect.right = Math.min(this.mTmpRect.right, this.mSlideableView.getLeft());
            }
            boolean clipRect = canvas2.clipRect(this.mTmpRect);
        }
        boolean result = super.drawChild(canvas2, child, drawingTime);
        canvas2.restoreToCount(save);
        return result;
    }

    /* access modifiers changed from: package-private */
    public void invalidateChildRegion(View view) {
        View v = view;
        if (Build.VERSION.SDK_INT >= 17) {
            ViewCompat.setLayerPaint(v, ((LayoutParams) v.getLayoutParams()).dimPaint);
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            if (!this.mDisplayListReflectionLoaded) {
                try {
                    this.mGetDisplayList = View.class.getDeclaredMethod("getDisplayList", (Class[]) null);
                } catch (NoSuchMethodException e) {
                    int e2 = Log.e(TAG, "Couldn't fetch getDisplayList method; dimming won't work right.", e);
                }
                try {
                    this.mRecreateDisplayList = View.class.getDeclaredField("mRecreateDisplayList");
                    this.mRecreateDisplayList.setAccessible(true);
                } catch (NoSuchFieldException e3) {
                    int e4 = Log.e(TAG, "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", e3);
                }
                this.mDisplayListReflectionLoaded = true;
            }
            if (this.mGetDisplayList == null || this.mRecreateDisplayList == null) {
                v.invalidate();
                return;
            }
            try {
                this.mRecreateDisplayList.setBoolean(v, true);
                Object invoke = this.mGetDisplayList.invoke(v, (Object[]) null);
            } catch (Exception e5) {
                int e6 = Log.e(TAG, "Error refreshing display list state", e5);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this, v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
    }

    /* access modifiers changed from: package-private */
    public boolean smoothSlideTo(float f, int i) {
        int x;
        float slideOffset = f;
        int i2 = i;
        if (!this.mCanSlide) {
            return false;
        }
        boolean isLayoutRtl = isLayoutRtlSupport();
        LayoutParams lp = (LayoutParams) this.mSlideableView.getLayoutParams();
        if (isLayoutRtl) {
            x = (int) (((float) getWidth()) - ((((float) (getPaddingRight() + lp.rightMargin)) + (slideOffset * ((float) this.mSlideRange))) + ((float) this.mSlideableView.getWidth())));
        } else {
            x = (int) (((float) (getPaddingLeft() + lp.leftMargin)) + (slideOffset * ((float) this.mSlideRange)));
        }
        if (!this.mDragHelper.smoothSlideViewTo(this.mSlideableView, x, this.mSlideableView.getTop())) {
            return false;
        }
        setAllChildrenVisible();
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
    }

    public void computeScroll() {
        if (!this.mDragHelper.continueSettling(true)) {
            return;
        }
        if (!this.mCanSlide) {
            this.mDragHelper.abort();
        } else {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Deprecated
    public void setShadowDrawable(Drawable d) {
        setShadowDrawableLeft(d);
    }

    public void setShadowDrawableLeft(@Nullable Drawable d) {
        Drawable drawable = d;
        this.mShadowDrawableLeft = drawable;
    }

    public void setShadowDrawableRight(@Nullable Drawable d) {
        Drawable drawable = d;
        this.mShadowDrawableRight = drawable;
    }

    @Deprecated
    public void setShadowResource(@DrawableRes int resId) {
        setShadowDrawable(getResources().getDrawable(resId));
    }

    public void setShadowResourceLeft(int resId) {
        setShadowDrawableLeft(ContextCompat.getDrawable(getContext(), resId));
    }

    public void setShadowResourceRight(int resId) {
        setShadowDrawableRight(ContextCompat.getDrawable(getContext(), resId));
    }

    public void draw(Canvas canvas) {
        Drawable shadowDrawable;
        int right;
        int left;
        Canvas c = canvas;
        super.draw(c);
        if (isLayoutRtlSupport()) {
            shadowDrawable = this.mShadowDrawableRight;
        } else {
            shadowDrawable = this.mShadowDrawableLeft;
        }
        View shadowView = getChildCount() > 1 ? getChildAt(1) : null;
        if (shadowView != null && shadowDrawable != null) {
            int top = shadowView.getTop();
            int bottom = shadowView.getBottom();
            int shadowWidth = shadowDrawable.getIntrinsicWidth();
            if (isLayoutRtlSupport()) {
                left = shadowView.getRight();
                right = left + shadowWidth;
            } else {
                right = shadowView.getLeft();
                left = right - shadowWidth;
            }
            shadowDrawable.setBounds(left, top, right, bottom);
            shadowDrawable.draw(c);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parallaxOtherViews(float r16) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r11 = r0
            boolean r11 = r11.isLayoutRtlSupport()
            r2 = r11
            r11 = r0
            android.view.View r11 = r11.mSlideableView
            android.view.ViewGroup$LayoutParams r11 = r11.getLayoutParams()
            android.support.v4.widget.SlidingPaneLayout$LayoutParams r11 = (android.support.p000v4.widget.SlidingPaneLayout.LayoutParams) r11
            r3 = r11
            r11 = r3
            boolean r11 = r11.dimWhenOffset
            if (r11 == 0) goto L_0x0042
            r11 = r2
            if (r11 == 0) goto L_0x003e
            r11 = r3
            int r11 = r11.rightMargin
        L_0x001e:
            if (r11 > 0) goto L_0x0042
            r11 = 1
        L_0x0021:
            r4 = r11
            r11 = r0
            int r11 = r11.getChildCount()
            r5 = r11
            r11 = 0
            r6 = r11
        L_0x002a:
            r11 = r6
            r12 = r5
            if (r11 >= r12) goto L_0x008b
            r11 = r0
            r12 = r6
            android.view.View r11 = r11.getChildAt(r12)
            r7 = r11
            r11 = r7
            r12 = r0
            android.view.View r12 = r12.mSlideableView
            if (r11 != r12) goto L_0x0044
        L_0x003b:
            int r6 = r6 + 1
            goto L_0x002a
        L_0x003e:
            r11 = r3
            int r11 = r11.leftMargin
            goto L_0x001e
        L_0x0042:
            r11 = 0
            goto L_0x0021
        L_0x0044:
            r11 = 1065353216(0x3f800000, float:1.0)
            r12 = r0
            float r12 = r12.mParallaxOffset
            float r11 = r11 - r12
            r12 = r0
            int r12 = r12.mParallaxBy
            float r12 = (float) r12
            float r11 = r11 * r12
            int r11 = (int) r11
            r8 = r11
            r11 = r0
            r12 = r1
            r11.mParallaxOffset = r12
            r11 = 1065353216(0x3f800000, float:1.0)
            r12 = r1
            float r11 = r11 - r12
            r12 = r0
            int r12 = r12.mParallaxBy
            float r12 = (float) r12
            float r11 = r11 * r12
            int r11 = (int) r11
            r9 = r11
            r11 = r8
            r12 = r9
            int r11 = r11 - r12
            r10 = r11
            r11 = r7
            r12 = r2
            if (r12 == 0) goto L_0x0082
            r12 = r10
            int r12 = -r12
        L_0x006a:
            r11.offsetLeftAndRight(r12)
            r11 = r4
            if (r11 == 0) goto L_0x003b
            r11 = r0
            r12 = r7
            r13 = r2
            if (r13 == 0) goto L_0x0084
            r13 = r0
            float r13 = r13.mParallaxOffset
            r14 = 1065353216(0x3f800000, float:1.0)
            float r13 = r13 - r14
        L_0x007b:
            r14 = r0
            int r14 = r14.mCoveredFadeColor
            r11.dimChildView(r12, r13, r14)
            goto L_0x003b
        L_0x0082:
            r12 = r10
            goto L_0x006a
        L_0x0084:
            r13 = 1065353216(0x3f800000, float:1.0)
            r14 = r0
            float r14 = r14.mParallaxOffset
            float r13 = r13 - r14
            goto L_0x007b
        L_0x008b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.SlidingPaneLayout.parallaxOtherViews(float):void");
    }

    /* access modifiers changed from: protected */
    public boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        boolean z2;
        View v = view;
        boolean checkV = z;
        int dx = i;
        int x = i2;
        int y = i3;
        if (v instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) v;
            int scrollX = v.getScrollX();
            int scrollY = v.getScrollY();
            for (int i4 = group.getChildCount() - 1; i4 >= 0; i4--) {
                View child = group.getChildAt(i4);
                if (x + scrollX >= child.getLeft() && x + scrollX < child.getRight() && y + scrollY >= child.getTop() && y + scrollY < child.getBottom() && canScroll(child, true, dx, (x + scrollX) - child.getLeft(), (y + scrollY) - child.getTop())) {
                    return true;
                }
            }
        }
        if (checkV) {
            if (v.canScrollHorizontally(isLayoutRtlSupport() ? dx : -dx)) {
                z2 = true;
                return z2;
            }
        }
        z2 = false;
        return z2;
    }

    /* access modifiers changed from: package-private */
    public boolean isDimmed(View view) {
        View child = view;
        if (child == null) {
            return false;
        }
        return this.mCanSlide && ((LayoutParams) child.getLayoutParams()).dimWhenOffset && this.mSlideOffset > 0.0f;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        ViewGroup.LayoutParams layoutParams;
        new LayoutParams();
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        ViewGroup.LayoutParams layoutParams2;
        ViewGroup.LayoutParams layoutParams3;
        ViewGroup.LayoutParams layoutParams4;
        ViewGroup.LayoutParams p = layoutParams;
        if (p instanceof ViewGroup.MarginLayoutParams) {
            layoutParams3 = layoutParams4;
            new LayoutParams((ViewGroup.MarginLayoutParams) p);
        } else {
            layoutParams3 = layoutParams2;
            new LayoutParams(p);
        }
        return layoutParams3;
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        ViewGroup.LayoutParams p = layoutParams;
        return (p instanceof LayoutParams) && super.checkLayoutParams(p);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        ViewGroup.LayoutParams layoutParams;
        new LayoutParams(getContext(), attrs);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        new SavedState(super.onSaveInstanceState());
        SavedState ss = savedState;
        ss.isOpen = isSlideable() ? isOpen() : this.mPreservedOpenState;
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
        if (ss.isOpen) {
            boolean openPane = openPane();
        } else {
            boolean closePane = closePane();
        }
        this.mPreservedOpenState = ss.isOpen;
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout$DragHelperCallback */
    private class DragHelperCallback extends ViewDragHelper.Callback {
        final /* synthetic */ SlidingPaneLayout this$0;

        DragHelperCallback(SlidingPaneLayout slidingPaneLayout) {
            this.this$0 = slidingPaneLayout;
        }

        public boolean tryCaptureView(View view, int i) {
            View child = view;
            int i2 = i;
            if (this.this$0.mIsUnableToDrag) {
                return false;
            }
            return ((LayoutParams) child.getLayoutParams()).slideable;
        }

        public void onViewDragStateChanged(int i) {
            int i2 = i;
            if (this.this$0.mDragHelper.getViewDragState() != 0) {
                return;
            }
            if (this.this$0.mSlideOffset == 0.0f) {
                this.this$0.updateObscuredViewsVisibility(this.this$0.mSlideableView);
                this.this$0.dispatchOnPanelClosed(this.this$0.mSlideableView);
                this.this$0.mPreservedOpenState = false;
                return;
            }
            this.this$0.dispatchOnPanelOpened(this.this$0.mSlideableView);
            this.this$0.mPreservedOpenState = true;
        }

        public void onViewCaptured(View view, int i) {
            View view2 = view;
            int i2 = i;
            this.this$0.setAllChildrenVisible();
        }

        public void onViewPositionChanged(View view, int left, int i, int i2, int i3) {
            View view2 = view;
            int i4 = i;
            int i5 = i2;
            int i6 = i3;
            this.this$0.onPanelDragged(left);
            this.this$0.invalidate();
        }

        public void onViewReleased(View view, float f, float f2) {
            int left;
            View releasedChild = view;
            float xvel = f;
            float f3 = f2;
            LayoutParams lp = (LayoutParams) releasedChild.getLayoutParams();
            if (this.this$0.isLayoutRtlSupport()) {
                int startToRight = this.this$0.getPaddingRight() + lp.rightMargin;
                if (xvel < 0.0f || (xvel == 0.0f && this.this$0.mSlideOffset > 0.5f)) {
                    startToRight += this.this$0.mSlideRange;
                }
                left = (this.this$0.getWidth() - startToRight) - this.this$0.mSlideableView.getWidth();
            } else {
                left = this.this$0.getPaddingLeft() + lp.leftMargin;
                if (xvel > 0.0f || (xvel == 0.0f && this.this$0.mSlideOffset > 0.5f)) {
                    left += this.this$0.mSlideRange;
                }
            }
            boolean z = this.this$0.mDragHelper.settleCapturedViewAt(left, releasedChild.getTop());
            this.this$0.invalidate();
        }

        public int getViewHorizontalDragRange(View view) {
            View view2 = view;
            return this.this$0.mSlideRange;
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            int newLeft;
            View view2 = view;
            int left = i;
            int i3 = i2;
            LayoutParams lp = (LayoutParams) this.this$0.mSlideableView.getLayoutParams();
            if (this.this$0.isLayoutRtlSupport()) {
                int startBound = this.this$0.getWidth() - ((this.this$0.getPaddingRight() + lp.rightMargin) + this.this$0.mSlideableView.getWidth());
                newLeft = Math.max(Math.min(left, startBound), startBound - this.this$0.mSlideRange);
            } else {
                int startBound2 = this.this$0.getPaddingLeft() + lp.leftMargin;
                newLeft = Math.min(Math.max(left, startBound2), startBound2 + this.this$0.mSlideRange);
            }
            return newLeft;
        }

        public int clampViewPositionVertical(View child, int i, int i2) {
            int i3 = i;
            int i4 = i2;
            return child.getTop();
        }

        public void onEdgeDragStarted(int i, int pointerId) {
            int i2 = i;
            this.this$0.mDragHelper.captureChildView(this.this$0.mSlideableView, pointerId);
        }
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout$LayoutParams */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static final int[] ATTRS = {16843137};
        Paint dimPaint;
        boolean dimWhenOffset;
        boolean slideable;
        public float weight = 0.0f;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams() {
            super(-1, -1);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height) {
            super(width, height);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(@NonNull ViewGroup.LayoutParams source) {
            super(source);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(@NonNull ViewGroup.MarginLayoutParams source) {
            super(source);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(@android.support.annotation.NonNull android.support.p000v4.widget.SlidingPaneLayout.LayoutParams r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                r2.<init>(r3)
                r2 = r0
                r3 = 0
                r2.weight = r3
                r2 = r0
                r3 = r1
                float r3 = r3.weight
                r2.weight = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.SlidingPaneLayout.LayoutParams.<init>(android.support.v4.widget.SlidingPaneLayout$LayoutParams):void");
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(@android.support.annotation.NonNull android.content.Context r9, @android.support.annotation.Nullable android.util.AttributeSet r10) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r2 = r10
                r4 = r0
                r5 = r1
                r6 = r2
                r4.<init>(r5, r6)
                r4 = r0
                r5 = 0
                r4.weight = r5
                r4 = r1
                r5 = r2
                int[] r6 = ATTRS
                android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r6)
                r3 = r4
                r4 = r0
                r5 = r3
                r6 = 0
                r7 = 0
                float r5 = r5.getFloat(r6, r7)
                r4.weight = r5
                r4 = r3
                r4.recycle()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.SlidingPaneLayout.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void");
        }
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout$SavedState */
    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR;
        boolean isOpen;

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
                r4 = r1
                int r4 = r4.readInt()
                if (r4 == 0) goto L_0x0015
                r4 = 1
            L_0x0012:
                r3.isOpen = r4
                return
            L_0x0015:
                r4 = 0
                goto L_0x0012
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.SlidingPaneLayout.SavedState.<init>(android.os.Parcel, java.lang.ClassLoader):void");
        }

        public void writeToParcel(Parcel parcel, int flags) {
            Parcel out = parcel;
            super.writeToParcel(out, flags);
            out.writeInt(this.isOpen ? 1 : 0);
        }

        static {
            Parcelable.Creator<SavedState> creator;
            new Parcelable.ClassLoaderCreator<SavedState>() {
                public SavedState createFromParcel(Parcel in, ClassLoader classLoader) {
                    SavedState savedState;
                    ClassLoader classLoader2 = classLoader;
                    new SavedState(in, (ClassLoader) null);
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

    /* renamed from: android.support.v4.widget.SlidingPaneLayout$AccessibilityDelegate */
    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final Rect mTmpRect;
        final /* synthetic */ SlidingPaneLayout this$0;

        AccessibilityDelegate(SlidingPaneLayout this$02) {
            Rect rect;
            this.this$0 = this$02;
            new Rect();
            this.mTmpRect = rect;
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            View host = view;
            AccessibilityNodeInfoCompat info = accessibilityNodeInfoCompat;
            AccessibilityNodeInfoCompat superNode = AccessibilityNodeInfoCompat.obtain(info);
            super.onInitializeAccessibilityNodeInfo(host, superNode);
            copyNodeInfoNoChildren(info, superNode);
            superNode.recycle();
            info.setClassName(SlidingPaneLayout.class.getName());
            info.setSource(host);
            ViewParent parent = ViewCompat.getParentForAccessibility(host);
            if (parent instanceof View) {
                info.setParent((View) parent);
            }
            int childCount = this.this$0.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = this.this$0.getChildAt(i);
                if (!filter(child) && child.getVisibility() == 0) {
                    ViewCompat.setImportantForAccessibility(child, 1);
                    info.addChild(child);
                }
            }
        }

        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent accessibilityEvent) {
            AccessibilityEvent event = accessibilityEvent;
            super.onInitializeAccessibilityEvent(host, event);
            event.setClassName(SlidingPaneLayout.class.getName());
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            ViewGroup host = viewGroup;
            View child = view;
            AccessibilityEvent event = accessibilityEvent;
            if (!filter(child)) {
                return super.onRequestSendAccessibilityEvent(host, child, event);
            }
            return false;
        }

        public boolean filter(View child) {
            return this.this$0.isDimmed(child);
        }

        private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
            AccessibilityNodeInfoCompat dest = accessibilityNodeInfoCompat;
            AccessibilityNodeInfoCompat src = accessibilityNodeInfoCompat2;
            Rect rect = this.mTmpRect;
            src.getBoundsInParent(rect);
            dest.setBoundsInParent(rect);
            src.getBoundsInScreen(rect);
            dest.setBoundsInScreen(rect);
            dest.setVisibleToUser(src.isVisibleToUser());
            dest.setPackageName(src.getPackageName());
            dest.setClassName(src.getClassName());
            dest.setContentDescription(src.getContentDescription());
            dest.setEnabled(src.isEnabled());
            dest.setClickable(src.isClickable());
            dest.setFocusable(src.isFocusable());
            dest.setFocused(src.isFocused());
            dest.setAccessibilityFocused(src.isAccessibilityFocused());
            dest.setSelected(src.isSelected());
            dest.setLongClickable(src.isLongClickable());
            dest.addAction(src.getActions());
            dest.setMovementGranularities(src.getMovementGranularities());
        }
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout$DisableLayerRunnable */
    private class DisableLayerRunnable implements Runnable {
        final View mChildView;
        final /* synthetic */ SlidingPaneLayout this$0;

        DisableLayerRunnable(SlidingPaneLayout slidingPaneLayout, View childView) {
            this.this$0 = slidingPaneLayout;
            this.mChildView = childView;
        }

        public void run() {
            if (this.mChildView.getParent() == this.this$0) {
                this.mChildView.setLayerType(0, (Paint) null);
                this.this$0.invalidateChildRegion(this.mChildView);
            }
            boolean remove = this.this$0.mPostedRunnables.remove(this);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isLayoutRtlSupport() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }
}
