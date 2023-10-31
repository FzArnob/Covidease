package android.support.p003v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.InputDeviceCompat;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import gnu.expr.Declaration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: android.support.v7.widget.LinearLayoutCompat */
public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v7.widget.LinearLayoutCompat$DividerMode */
    public @interface DividerMode {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v7.widget.LinearLayoutCompat$OrientationMode */
    public @interface OrientationMode {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LinearLayoutCompat(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LinearLayoutCompat(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LinearLayoutCompat(android.content.Context r13, android.util.AttributeSet r14, int r15) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r7.<init>(r8, r9, r10)
            r7 = r0
            r8 = 1
            r7.mBaselineAligned = r8
            r7 = r0
            r8 = -1
            r7.mBaselineAlignedChildIndex = r8
            r7 = r0
            r8 = 0
            r7.mBaselineChildTop = r8
            r7 = r0
            r8 = 8388659(0x800033, float:1.1755015E-38)
            r7.mGravity = r8
            r7 = r1
            r8 = r2
            int[] r9 = android.support.p003v7.appcompat.C0425R.styleable.LinearLayoutCompat
            r10 = r3
            r11 = 0
            android.support.v7.widget.TintTypedArray r7 = android.support.p003v7.widget.TintTypedArray.obtainStyledAttributes(r7, r8, r9, r10, r11)
            r4 = r7
            r7 = r4
            int r8 = android.support.p003v7.appcompat.C0425R.styleable.LinearLayoutCompat_android_orientation
            r9 = -1
            int r7 = r7.getInt(r8, r9)
            r5 = r7
            r7 = r5
            if (r7 < 0) goto L_0x0039
            r7 = r0
            r8 = r5
            r7.setOrientation(r8)
        L_0x0039:
            r7 = r4
            int r8 = android.support.p003v7.appcompat.C0425R.styleable.LinearLayoutCompat_android_gravity
            r9 = -1
            int r7 = r7.getInt(r8, r9)
            r5 = r7
            r7 = r5
            if (r7 < 0) goto L_0x004a
            r7 = r0
            r8 = r5
            r7.setGravity(r8)
        L_0x004a:
            r7 = r4
            int r8 = android.support.p003v7.appcompat.C0425R.styleable.LinearLayoutCompat_android_baselineAligned
            r9 = 1
            boolean r7 = r7.getBoolean(r8, r9)
            r6 = r7
            r7 = r6
            if (r7 != 0) goto L_0x005b
            r7 = r0
            r8 = r6
            r7.setBaselineAligned(r8)
        L_0x005b:
            r7 = r0
            r8 = r4
            int r9 = android.support.p003v7.appcompat.C0425R.styleable.LinearLayoutCompat_android_weightSum
            r10 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r8 = r8.getFloat(r9, r10)
            r7.mWeightSum = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.p003v7.appcompat.C0425R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex
            r10 = -1
            int r8 = r8.getInt(r9, r10)
            r7.mBaselineAlignedChildIndex = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.p003v7.appcompat.C0425R.styleable.LinearLayoutCompat_measureWithLargestChild
            r10 = 0
            boolean r8 = r8.getBoolean(r9, r10)
            r7.mUseLargestChild = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.p003v7.appcompat.C0425R.styleable.LinearLayoutCompat_divider
            android.graphics.drawable.Drawable r8 = r8.getDrawable(r9)
            r7.setDividerDrawable(r8)
            r7 = r0
            r8 = r4
            int r9 = android.support.p003v7.appcompat.C0425R.styleable.LinearLayoutCompat_showDividers
            r10 = 0
            int r8 = r8.getInt(r9, r10)
            r7.mShowDividers = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.p003v7.appcompat.C0425R.styleable.LinearLayoutCompat_dividerPadding
            r10 = 0
            int r8 = r8.getDimensionPixelSize(r9, r10)
            r7.mDividerPadding = r8
            r7 = r4
            r7.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.LinearLayoutCompat.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setShowDividers(int i) {
        int showDividers = i;
        if (showDividers != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = showDividers;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable drawable) {
        Drawable divider = drawable;
        if (divider != this.mDivider) {
            this.mDivider = divider;
            if (divider != null) {
                this.mDividerWidth = divider.getIntrinsicWidth();
                this.mDividerHeight = divider.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
            }
            setWillNotDraw(divider == null);
            requestLayout();
        }
    }

    public void setDividerPadding(int padding) {
        int i = padding;
        this.mDividerPadding = i;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        if (this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawDividersVertical(canvas2);
            } else {
                drawDividersHorizontal(canvas2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void drawDividersVertical(Canvas canvas) {
        int bottom;
        Canvas canvas2 = canvas;
        int count = getVirtualChildCount();
        for (int i = 0; i < count; i++) {
            View child = getVirtualChildAt(i);
            if (!(child == null || child.getVisibility() == 8 || !hasDividerBeforeChildAt(i))) {
                drawHorizontalDivider(canvas2, (child.getTop() - ((LayoutParams) child.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(count)) {
            View child2 = getVirtualChildAt(count - 1);
            if (child2 == null) {
                bottom = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                bottom = child2.getBottom() + ((LayoutParams) child2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas2, bottom);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawDividersHorizontal(Canvas canvas) {
        int position;
        int position2;
        Canvas canvas2 = canvas;
        int count = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i = 0; i < count; i++) {
            View child = getVirtualChildAt(i);
            if (!(child == null || child.getVisibility() == 8 || !hasDividerBeforeChildAt(i))) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (isLayoutRtl) {
                    position2 = child.getRight() + lp.rightMargin;
                } else {
                    position2 = (child.getLeft() - lp.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas2, position2);
            }
        }
        if (hasDividerBeforeChildAt(count)) {
            View child2 = getVirtualChildAt(count - 1);
            if (child2 != null) {
                LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
                if (isLayoutRtl) {
                    position = (child2.getLeft() - lp2.leftMargin) - this.mDividerWidth;
                } else {
                    position = child2.getRight() + lp2.rightMargin;
                }
            } else if (isLayoutRtl) {
                position = getPaddingLeft();
            } else {
                position = (getWidth() - getPaddingRight()) - this.mDividerWidth;
            }
            drawVerticalDivider(canvas2, position);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawHorizontalDivider(Canvas canvas, int i) {
        int top = i;
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, top, (getWidth() - getPaddingRight()) - this.mDividerPadding, top + this.mDividerHeight);
        this.mDivider.draw(canvas);
    }

    /* access modifiers changed from: package-private */
    public void drawVerticalDivider(Canvas canvas, int i) {
        int left = i;
        this.mDivider.setBounds(left, getPaddingTop() + this.mDividerPadding, left + this.mDividerWidth, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean baselineAligned) {
        boolean z = baselineAligned;
        this.mBaselineAligned = z;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean enabled) {
        boolean z = enabled;
        this.mUseLargestChild = z;
    }

    public int getBaseline() {
        int majorGravity;
        Throwable th;
        Throwable th2;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        if (getChildCount() <= this.mBaselineAlignedChildIndex) {
            Throwable th3 = th2;
            new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
            throw th3;
        }
        View child = getChildAt(this.mBaselineAlignedChildIndex);
        int childBaseline = child.getBaseline();
        if (childBaseline != -1) {
            int childTop = this.mBaselineChildTop;
            if (this.mOrientation == 1 && (majorGravity = this.mGravity & 112) != 48) {
                switch (majorGravity) {
                    case 16:
                        childTop += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                        break;
                    case 80:
                        childTop = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                        break;
                }
            }
            return childTop + ((LayoutParams) child.getLayoutParams()).topMargin + childBaseline;
        } else if (this.mBaselineAlignedChildIndex == 0) {
            return -1;
        } else {
            Throwable th4 = th;
            new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            throw th4;
        }
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        Throwable th;
        StringBuilder sb;
        int i2 = i;
        if (i2 < 0 || i2 >= getChildCount()) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("base aligned child index out of range (0, ").append(getChildCount()).append(")").toString());
            throw th2;
        }
        this.mBaselineAlignedChildIndex = i2;
    }

    /* access modifiers changed from: package-private */
    public View getVirtualChildAt(int index) {
        return getChildAt(index);
    }

    /* access modifiers changed from: package-private */
    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float weightSum) {
        float max = Math.max(0.0f, weightSum);
        this.mWeightSum = max;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        if (this.mOrientation == 1) {
            measureVertical(widthMeasureSpec, heightMeasureSpec);
        } else {
            measureHorizontal(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean hasDividerBeforeChildAt(int i) {
        boolean z;
        int childIndex = i;
        if (childIndex == 0) {
            if ((this.mShowDividers & 1) != 0) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } else if (childIndex == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        } else if ((this.mShowDividers & 2) == 0) {
            return false;
        } else {
            boolean hasVisibleViewBefore = false;
            int i2 = childIndex - 1;
            while (true) {
                if (i2 < 0) {
                    break;
                } else if (getChildAt(i2).getVisibility() != 8) {
                    hasVisibleViewBefore = true;
                    break;
                } else {
                    i2--;
                }
            }
            return hasVisibleViewBefore;
        }
    }

    /* access modifiers changed from: package-private */
    public void measureVertical(int i, int i2) {
        int i3;
        Throwable th;
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        this.mTotalLength = 0;
        int maxWidth = 0;
        int childState = 0;
        int alternativeMaxWidth = 0;
        int weightedMaxWidth = 0;
        boolean allFillParent = true;
        float totalWeight = 0.0f;
        int count = getVirtualChildCount();
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        boolean matchWidth = false;
        boolean skippedMeasure = false;
        int baselineChildIndex = this.mBaselineAlignedChildIndex;
        boolean useLargestChild = this.mUseLargestChild;
        int largestChildHeight = 0;
        int i4 = 0;
        while (i4 < count) {
            View child = getVirtualChildAt(i4);
            if (child == null) {
                this.mTotalLength += measureNullChild(i4);
            } else if (child.getVisibility() == 8) {
                i4 += getChildrenSkipCount(child, i4);
            } else {
                if (hasDividerBeforeChildAt(i4)) {
                    this.mTotalLength += this.mDividerHeight;
                }
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                totalWeight += lp.weight;
                if (heightMode == 1073741824 && lp.height == 0 && lp.weight > 0.0f) {
                    int totalLength = this.mTotalLength;
                    this.mTotalLength = Math.max(totalLength, totalLength + lp.topMargin + lp.bottomMargin);
                    skippedMeasure = true;
                } else {
                    int oldHeight = Integer.MIN_VALUE;
                    if (lp.height == 0 && lp.weight > 0.0f) {
                        oldHeight = 0;
                        lp.height = -2;
                    }
                    measureChildBeforeLayout(child, i4, widthMeasureSpec, 0, heightMeasureSpec, totalWeight == 0.0f ? this.mTotalLength : 0);
                    if (oldHeight != Integer.MIN_VALUE) {
                        lp.height = oldHeight;
                    }
                    int childHeight = child.getMeasuredHeight();
                    int totalLength2 = this.mTotalLength;
                    this.mTotalLength = Math.max(totalLength2, totalLength2 + childHeight + lp.topMargin + lp.bottomMargin + getNextLocationOffset(child));
                    if (useLargestChild) {
                        largestChildHeight = Math.max(childHeight, largestChildHeight);
                    }
                }
                if (baselineChildIndex >= 0 && baselineChildIndex == i4 + 1) {
                    this.mBaselineChildTop = this.mTotalLength;
                }
                if (i4 >= baselineChildIndex || lp.weight <= 0.0f) {
                    boolean matchWidthLocally = false;
                    if (widthMode != 1073741824 && lp.width == -1) {
                        matchWidth = true;
                        matchWidthLocally = true;
                    }
                    int margin = lp.leftMargin + lp.rightMargin;
                    int measuredWidth = child.getMeasuredWidth() + margin;
                    maxWidth = Math.max(maxWidth, measuredWidth);
                    childState = View.combineMeasuredStates(childState, child.getMeasuredState());
                    allFillParent = allFillParent && lp.width == -1;
                    if (lp.weight > 0.0f) {
                        weightedMaxWidth = Math.max(weightedMaxWidth, matchWidthLocally ? margin : measuredWidth);
                    } else {
                        alternativeMaxWidth = Math.max(alternativeMaxWidth, matchWidthLocally ? margin : measuredWidth);
                    }
                    i4 += getChildrenSkipCount(child, i4);
                } else {
                    Throwable th2 = th;
                    new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                    throw th2;
                }
            }
            i4++;
        }
        if (this.mTotalLength > 0 && hasDividerBeforeChildAt(count)) {
            this.mTotalLength += this.mDividerHeight;
        }
        if (useLargestChild && (heightMode == Integer.MIN_VALUE || heightMode == 0)) {
            this.mTotalLength = 0;
            int i5 = 0;
            while (i5 < count) {
                View child2 = getVirtualChildAt(i5);
                if (child2 == null) {
                    this.mTotalLength += measureNullChild(i5);
                } else if (child2.getVisibility() == 8) {
                    i5 += getChildrenSkipCount(child2, i5);
                } else {
                    LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
                    int totalLength3 = this.mTotalLength;
                    this.mTotalLength = Math.max(totalLength3, totalLength3 + largestChildHeight + lp2.topMargin + lp2.bottomMargin + getNextLocationOffset(child2));
                }
                i5++;
            }
        }
        this.mTotalLength += getPaddingTop() + getPaddingBottom();
        int heightSizeAndState = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumHeight()), heightMeasureSpec, 0);
        int delta = (heightSizeAndState & 16777215) - this.mTotalLength;
        if (skippedMeasure || (delta != 0 && totalWeight > 0.0f)) {
            float weightSum = this.mWeightSum > 0.0f ? this.mWeightSum : totalWeight;
            this.mTotalLength = 0;
            for (int i6 = 0; i6 < count; i6++) {
                View child3 = getVirtualChildAt(i6);
                if (child3.getVisibility() != 8) {
                    LayoutParams lp3 = (LayoutParams) child3.getLayoutParams();
                    float childExtra = lp3.weight;
                    if (childExtra > 0.0f) {
                        int share = (int) ((childExtra * ((float) delta)) / weightSum);
                        weightSum -= childExtra;
                        delta -= share;
                        int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight() + lp3.leftMargin + lp3.rightMargin, lp3.width);
                        if (lp3.height == 0 && heightMode == 1073741824) {
                            View view = child3;
                            int i7 = childWidthMeasureSpec;
                            if (share > 0) {
                                i3 = share;
                            } else {
                                i3 = 0;
                            }
                            view.measure(i7, View.MeasureSpec.makeMeasureSpec(i3, Declaration.MODULE_REFERENCE));
                        } else {
                            int childHeight2 = child3.getMeasuredHeight() + share;
                            if (childHeight2 < 0) {
                                childHeight2 = 0;
                            }
                            child3.measure(childWidthMeasureSpec, View.MeasureSpec.makeMeasureSpec(childHeight2, Declaration.MODULE_REFERENCE));
                        }
                        childState = View.combineMeasuredStates(childState, child3.getMeasuredState() & InputDeviceCompat.SOURCE_ANY);
                    }
                    int margin2 = lp3.leftMargin + lp3.rightMargin;
                    int measuredWidth2 = child3.getMeasuredWidth() + margin2;
                    maxWidth = Math.max(maxWidth, measuredWidth2);
                    alternativeMaxWidth = Math.max(alternativeMaxWidth, widthMode != 1073741824 && lp3.width == -1 ? margin2 : measuredWidth2);
                    allFillParent = allFillParent && lp3.width == -1;
                    int totalLength4 = this.mTotalLength;
                    this.mTotalLength = Math.max(totalLength4, totalLength4 + child3.getMeasuredHeight() + lp3.topMargin + lp3.bottomMargin + getNextLocationOffset(child3));
                }
            }
            this.mTotalLength += getPaddingTop() + getPaddingBottom();
        } else {
            alternativeMaxWidth = Math.max(alternativeMaxWidth, weightedMaxWidth);
            if (useLargestChild && heightMode != 1073741824) {
                for (int i8 = 0; i8 < count; i8++) {
                    View child4 = getVirtualChildAt(i8);
                    if (!(child4 == null || child4.getVisibility() == 8 || ((LayoutParams) child4.getLayoutParams()).weight <= 0.0f)) {
                        child4.measure(View.MeasureSpec.makeMeasureSpec(child4.getMeasuredWidth(), Declaration.MODULE_REFERENCE), View.MeasureSpec.makeMeasureSpec(largestChildHeight, Declaration.MODULE_REFERENCE));
                    }
                }
            }
        }
        if (!allFillParent && widthMode != 1073741824) {
            maxWidth = alternativeMaxWidth;
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(maxWidth + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), widthMeasureSpec, childState), heightSizeAndState);
        if (matchWidth) {
            forceUniformWidth(count, heightMeasureSpec);
        }
    }

    private void forceUniformWidth(int i, int i2) {
        int count = i;
        int heightMeasureSpec = i2;
        int uniformMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), Declaration.MODULE_REFERENCE);
        for (int i3 = 0; i3 < count; i3++) {
            View child = getVirtualChildAt(i3);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.width == -1) {
                    int oldHeight = lp.height;
                    lp.height = child.getMeasuredHeight();
                    measureChildWithMargins(child, uniformMeasureSpec, 0, heightMeasureSpec, 0);
                    lp.height = oldHeight;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x005a, code lost:
        if (r2.mMaxDescent == null) goto L_0x005c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void measureHorizontal(int r50, int r51) {
        /*
            r49 = this;
            r2 = r49
            r3 = r50
            r4 = r51
            r36 = r2
            r37 = 0
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
            r36 = 0
            r5 = r36
            r36 = 0
            r6 = r36
            r36 = 0
            r7 = r36
            r36 = 0
            r8 = r36
            r36 = 1
            r9 = r36
            r36 = 0
            r10 = r36
            r36 = r2
            int r36 = r36.getVirtualChildCount()
            r11 = r36
            r36 = r3
            int r36 = android.view.View.MeasureSpec.getMode(r36)
            r12 = r36
            r36 = r4
            int r36 = android.view.View.MeasureSpec.getMode(r36)
            r13 = r36
            r36 = 0
            r14 = r36
            r36 = 0
            r15 = r36
            r36 = r2
            r0 = r36
            int[] r0 = r0.mMaxAscent
            r36 = r0
            if (r36 == 0) goto L_0x005c
            r36 = r2
            r0 = r36
            int[] r0 = r0.mMaxDescent
            r36 = r0
            if (r36 != 0) goto L_0x007c
        L_0x005c:
            r36 = r2
            r37 = 4
            r0 = r37
            int[] r0 = new int[r0]
            r37 = r0
            r0 = r37
            r1 = r36
            r1.mMaxAscent = r0
            r36 = r2
            r37 = 4
            r0 = r37
            int[] r0 = new int[r0]
            r37 = r0
            r0 = r37
            r1 = r36
            r1.mMaxDescent = r0
        L_0x007c:
            r36 = r2
            r0 = r36
            int[] r0 = r0.mMaxAscent
            r36 = r0
            r16 = r36
            r36 = r2
            r0 = r36
            int[] r0 = r0.mMaxDescent
            r36 = r0
            r17 = r36
            r36 = r16
            r37 = 0
            r38 = r16
            r39 = 1
            r40 = r16
            r41 = 2
            r42 = r16
            r43 = 3
            r44 = -1
            r46 = r42
            r47 = r43
            r48 = r44
            r42 = r48
            r43 = r46
            r44 = r47
            r45 = r48
            r43[r44] = r45
            r46 = r40
            r47 = r41
            r48 = r42
            r40 = r48
            r41 = r46
            r42 = r47
            r43 = r48
            r41[r42] = r43
            r46 = r38
            r47 = r39
            r48 = r40
            r38 = r48
            r39 = r46
            r40 = r47
            r41 = r48
            r39[r40] = r41
            r36[r37] = r38
            r36 = r17
            r37 = 0
            r38 = r17
            r39 = 1
            r40 = r17
            r41 = 2
            r42 = r17
            r43 = 3
            r44 = -1
            r46 = r42
            r47 = r43
            r48 = r44
            r42 = r48
            r43 = r46
            r44 = r47
            r45 = r48
            r43[r44] = r45
            r46 = r40
            r47 = r41
            r48 = r42
            r40 = r48
            r41 = r46
            r42 = r47
            r43 = r48
            r41[r42] = r43
            r46 = r38
            r47 = r39
            r48 = r40
            r38 = r48
            r39 = r46
            r40 = r47
            r41 = r48
            r39[r40] = r41
            r36[r37] = r38
            r36 = r2
            r0 = r36
            boolean r0 = r0.mBaselineAligned
            r36 = r0
            r18 = r36
            r36 = r2
            r0 = r36
            boolean r0 = r0.mUseLargestChild
            r36 = r0
            r19 = r36
            r36 = r12
            r37 = 1073741824(0x40000000, float:2.0)
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x017b
            r36 = 1
        L_0x0138:
            r20 = r36
            r36 = 0
            r21 = r36
            r36 = 0
            r22 = r36
        L_0x0142:
            r36 = r22
            r37 = r11
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x0496
            r36 = r2
            r37 = r22
            android.view.View r36 = r36.getVirtualChildAt(r37)
            r23 = r36
            r36 = r23
            if (r36 != 0) goto L_0x017e
            r36 = r2
            r46 = r36
            r36 = r46
            r37 = r46
            r0 = r37
            int r0 = r0.mTotalLength
            r37 = r0
            r38 = r2
            r39 = r22
            int r38 = r38.measureNullChild(r39)
            int r37 = r37 + r38
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
        L_0x0178:
            int r22 = r22 + 1
            goto L_0x0142
        L_0x017b:
            r36 = 0
            goto L_0x0138
        L_0x017e:
            r36 = r23
            int r36 = r36.getVisibility()
            r37 = 8
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x019d
            r36 = r22
            r37 = r2
            r38 = r23
            r39 = r22
            int r37 = r37.getChildrenSkipCount(r38, r39)
            int r36 = r36 + r37
            r22 = r36
            goto L_0x0178
        L_0x019d:
            r36 = r2
            r37 = r22
            boolean r36 = r36.hasDividerBeforeChildAt(r37)
            if (r36 == 0) goto L_0x01c5
            r36 = r2
            r46 = r36
            r36 = r46
            r37 = r46
            r0 = r37
            int r0 = r0.mTotalLength
            r37 = r0
            r38 = r2
            r0 = r38
            int r0 = r0.mDividerWidth
            r38 = r0
            int r37 = r37 + r38
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
        L_0x01c5:
            r36 = r23
            android.view.ViewGroup$LayoutParams r36 = r36.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r36 = (android.support.p003v7.widget.LinearLayoutCompat.LayoutParams) r36
            r24 = r36
            r36 = r10
            r37 = r24
            r0 = r37
            float r0 = r0.weight
            r37 = r0
            float r36 = r36 + r37
            r10 = r36
            r36 = r12
            r37 = 1073741824(0x40000000, float:2.0)
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0382
            r36 = r24
            r0 = r36
            int r0 = r0.width
            r36 = r0
            if (r36 != 0) goto L_0x0382
            r36 = r24
            r0 = r36
            float r0 = r0.weight
            r36 = r0
            r37 = 0
            int r36 = (r36 > r37 ? 1 : (r36 == r37 ? 0 : -1))
            if (r36 <= 0) goto L_0x0382
            r36 = r20
            if (r36 == 0) goto L_0x034c
            r36 = r2
            r46 = r36
            r36 = r46
            r37 = r46
            r0 = r37
            int r0 = r0.mTotalLength
            r37 = r0
            r38 = r24
            r0 = r38
            int r0 = r0.leftMargin
            r38 = r0
            r39 = r24
            r0 = r39
            int r0 = r0.rightMargin
            r39 = r0
            int r38 = r38 + r39
            int r37 = r37 + r38
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
        L_0x022b:
            r36 = r18
            if (r36 == 0) goto L_0x037c
            r36 = 0
            r37 = 0
            int r36 = android.view.View.MeasureSpec.makeMeasureSpec(r36, r37)
            r25 = r36
            r36 = r23
            r37 = r25
            r38 = r25
            r36.measure(r37, r38)
        L_0x0242:
            r36 = 0
            r25 = r36
            r36 = r13
            r37 = 1073741824(0x40000000, float:2.0)
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x0268
            r36 = r24
            r0 = r36
            int r0 = r0.height
            r36 = r0
            r37 = -1
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0268
            r36 = 1
            r14 = r36
            r36 = 1
            r25 = r36
        L_0x0268:
            r36 = r24
            r0 = r36
            int r0 = r0.topMargin
            r36 = r0
            r37 = r24
            r0 = r37
            int r0 = r0.bottomMargin
            r37 = r0
            int r36 = r36 + r37
            r26 = r36
            r36 = r23
            int r36 = r36.getMeasuredHeight()
            r37 = r26
            int r36 = r36 + r37
            r27 = r36
            r36 = r6
            r37 = r23
            int r37 = r37.getMeasuredState()
            int r36 = android.view.View.combineMeasuredStates(r36, r37)
            r6 = r36
            r36 = r18
            if (r36 == 0) goto L_0x02fc
            r36 = r23
            int r36 = r36.getBaseline()
            r28 = r36
            r36 = r28
            r37 = -1
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x02fc
            r36 = r24
            r0 = r36
            int r0 = r0.gravity
            r36 = r0
            if (r36 >= 0) goto L_0x0471
            r36 = r2
            r0 = r36
            int r0 = r0.mGravity
            r36 = r0
        L_0x02be:
            r37 = 112(0x70, float:1.57E-43)
            r36 = r36 & 112(0x70, float:1.57E-43)
            r29 = r36
            r36 = r29
            r37 = 4
            int r36 = r36 >> 4
            r37 = -2
            r36 = r36 & -2
            r37 = 1
            int r36 = r36 >> 1
            r30 = r36
            r36 = r16
            r37 = r30
            r38 = r16
            r39 = r30
            r38 = r38[r39]
            r39 = r28
            int r38 = java.lang.Math.max(r38, r39)
            r36[r37] = r38
            r36 = r17
            r37 = r30
            r38 = r17
            r39 = r30
            r38 = r38[r39]
            r39 = r27
            r40 = r28
            int r39 = r39 - r40
            int r38 = java.lang.Math.max(r38, r39)
            r36[r37] = r38
        L_0x02fc:
            r36 = r5
            r37 = r27
            int r36 = java.lang.Math.max(r36, r37)
            r5 = r36
            r36 = r9
            if (r36 == 0) goto L_0x047b
            r36 = r24
            r0 = r36
            int r0 = r0.height
            r36 = r0
            r37 = -1
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x047b
            r36 = 1
        L_0x031c:
            r9 = r36
            r36 = r24
            r0 = r36
            float r0 = r0.weight
            r36 = r0
            r37 = 0
            int r36 = (r36 > r37 ? 1 : (r36 == r37 ? 0 : -1))
            if (r36 <= 0) goto L_0x0483
            r36 = r8
            r37 = r25
            if (r37 == 0) goto L_0x047f
            r37 = r26
        L_0x0334:
            int r36 = java.lang.Math.max(r36, r37)
            r8 = r36
        L_0x033a:
            r36 = r22
            r37 = r2
            r38 = r23
            r39 = r22
            int r37 = r37.getChildrenSkipCount(r38, r39)
            int r36 = r36 + r37
            r22 = r36
            goto L_0x0178
        L_0x034c:
            r36 = r2
            r0 = r36
            int r0 = r0.mTotalLength
            r36 = r0
            r25 = r36
            r36 = r2
            r37 = r25
            r38 = r25
            r39 = r24
            r0 = r39
            int r0 = r0.leftMargin
            r39 = r0
            int r38 = r38 + r39
            r39 = r24
            r0 = r39
            int r0 = r0.rightMargin
            r39 = r0
            int r38 = r38 + r39
            int r37 = java.lang.Math.max(r37, r38)
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
            goto L_0x022b
        L_0x037c:
            r36 = 1
            r15 = r36
            goto L_0x0242
        L_0x0382:
            r36 = -2147483648(0xffffffff80000000, float:-0.0)
            r25 = r36
            r36 = r24
            r0 = r36
            int r0 = r0.width
            r36 = r0
            if (r36 != 0) goto L_0x03ac
            r36 = r24
            r0 = r36
            float r0 = r0.weight
            r36 = r0
            r37 = 0
            int r36 = (r36 > r37 ? 1 : (r36 == r37 ? 0 : -1))
            if (r36 <= 0) goto L_0x03ac
            r36 = 0
            r25 = r36
            r36 = r24
            r37 = -2
            r0 = r37
            r1 = r36
            r1.width = r0
        L_0x03ac:
            r36 = r2
            r37 = r23
            r38 = r22
            r39 = r3
            r40 = r10
            r41 = 0
            int r40 = (r40 > r41 ? 1 : (r40 == r41 ? 0 : -1))
            if (r40 != 0) goto L_0x0431
            r40 = r2
            r0 = r40
            int r0 = r0.mTotalLength
            r40 = r0
        L_0x03c4:
            r41 = r4
            r42 = 0
            r36.measureChildBeforeLayout(r37, r38, r39, r40, r41, r42)
            r36 = r25
            r37 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x03df
            r36 = r24
            r37 = r25
            r0 = r37
            r1 = r36
            r1.width = r0
        L_0x03df:
            r36 = r23
            int r36 = r36.getMeasuredWidth()
            r26 = r36
            r36 = r20
            if (r36 == 0) goto L_0x0434
            r36 = r2
            r46 = r36
            r36 = r46
            r37 = r46
            r0 = r37
            int r0 = r0.mTotalLength
            r37 = r0
            r38 = r26
            r39 = r24
            r0 = r39
            int r0 = r0.leftMargin
            r39 = r0
            int r38 = r38 + r39
            r39 = r24
            r0 = r39
            int r0 = r0.rightMargin
            r39 = r0
            int r38 = r38 + r39
            r39 = r2
            r40 = r23
            int r39 = r39.getNextLocationOffset(r40)
            int r38 = r38 + r39
            int r37 = r37 + r38
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
        L_0x0421:
            r36 = r19
            if (r36 == 0) goto L_0x0242
            r36 = r26
            r37 = r21
            int r36 = java.lang.Math.max(r36, r37)
            r21 = r36
            goto L_0x0242
        L_0x0431:
            r40 = 0
            goto L_0x03c4
        L_0x0434:
            r36 = r2
            r0 = r36
            int r0 = r0.mTotalLength
            r36 = r0
            r27 = r36
            r36 = r2
            r37 = r27
            r38 = r27
            r39 = r26
            int r38 = r38 + r39
            r39 = r24
            r0 = r39
            int r0 = r0.leftMargin
            r39 = r0
            int r38 = r38 + r39
            r39 = r24
            r0 = r39
            int r0 = r0.rightMargin
            r39 = r0
            int r38 = r38 + r39
            r39 = r2
            r40 = r23
            int r39 = r39.getNextLocationOffset(r40)
            int r38 = r38 + r39
            int r37 = java.lang.Math.max(r37, r38)
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
            goto L_0x0421
        L_0x0471:
            r36 = r24
            r0 = r36
            int r0 = r0.gravity
            r36 = r0
            goto L_0x02be
        L_0x047b:
            r36 = 0
            goto L_0x031c
        L_0x047f:
            r37 = r27
            goto L_0x0334
        L_0x0483:
            r36 = r7
            r37 = r25
            if (r37 == 0) goto L_0x0493
            r37 = r26
        L_0x048b:
            int r36 = java.lang.Math.max(r36, r37)
            r7 = r36
            goto L_0x033a
        L_0x0493:
            r37 = r27
            goto L_0x048b
        L_0x0496:
            r36 = r2
            r0 = r36
            int r0 = r0.mTotalLength
            r36 = r0
            if (r36 <= 0) goto L_0x04c8
            r36 = r2
            r37 = r11
            boolean r36 = r36.hasDividerBeforeChildAt(r37)
            if (r36 == 0) goto L_0x04c8
            r36 = r2
            r46 = r36
            r36 = r46
            r37 = r46
            r0 = r37
            int r0 = r0.mTotalLength
            r37 = r0
            r38 = r2
            r0 = r38
            int r0 = r0.mDividerWidth
            r38 = r0
            int r37 = r37 + r38
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
        L_0x04c8:
            r36 = r16
            r37 = 1
            r36 = r36[r37]
            r37 = -1
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0500
            r36 = r16
            r37 = 0
            r36 = r36[r37]
            r37 = -1
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0500
            r36 = r16
            r37 = 2
            r36 = r36[r37]
            r37 = -1
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0500
            r36 = r16
            r37 = 3
            r36 = r36[r37]
            r37 = -1
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x055a
        L_0x0500:
            r36 = r16
            r37 = 3
            r36 = r36[r37]
            r37 = r16
            r38 = 0
            r37 = r37[r38]
            r38 = r16
            r39 = 1
            r38 = r38[r39]
            r39 = r16
            r40 = 2
            r39 = r39[r40]
            int r38 = java.lang.Math.max(r38, r39)
            int r37 = java.lang.Math.max(r37, r38)
            int r36 = java.lang.Math.max(r36, r37)
            r22 = r36
            r36 = r17
            r37 = 3
            r36 = r36[r37]
            r37 = r17
            r38 = 0
            r37 = r37[r38]
            r38 = r17
            r39 = 1
            r38 = r38[r39]
            r39 = r17
            r40 = 2
            r39 = r39[r40]
            int r38 = java.lang.Math.max(r38, r39)
            int r37 = java.lang.Math.max(r37, r38)
            int r36 = java.lang.Math.max(r36, r37)
            r23 = r36
            r36 = r5
            r37 = r22
            r38 = r23
            int r37 = r37 + r38
            int r36 = java.lang.Math.max(r36, r37)
            r5 = r36
        L_0x055a:
            r36 = r19
            if (r36 == 0) goto L_0x0655
            r36 = r12
            r37 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x056c
            r36 = r12
            if (r36 != 0) goto L_0x0655
        L_0x056c:
            r36 = r2
            r37 = 0
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
            r36 = 0
            r22 = r36
        L_0x057a:
            r36 = r22
            r37 = r11
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x0655
            r36 = r2
            r37 = r22
            android.view.View r36 = r36.getVirtualChildAt(r37)
            r23 = r36
            r36 = r23
            if (r36 != 0) goto L_0x05b3
            r36 = r2
            r46 = r36
            r36 = r46
            r37 = r46
            r0 = r37
            int r0 = r0.mTotalLength
            r37 = r0
            r38 = r2
            r39 = r22
            int r38 = r38.measureNullChild(r39)
            int r37 = r37 + r38
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
        L_0x05b0:
            int r22 = r22 + 1
            goto L_0x057a
        L_0x05b3:
            r36 = r23
            int r36 = r36.getVisibility()
            r37 = 8
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x05d2
            r36 = r22
            r37 = r2
            r38 = r23
            r39 = r22
            int r37 = r37.getChildrenSkipCount(r38, r39)
            int r36 = r36 + r37
            r22 = r36
            goto L_0x05b0
        L_0x05d2:
            r36 = r23
            android.view.ViewGroup$LayoutParams r36 = r36.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r36 = (android.support.p003v7.widget.LinearLayoutCompat.LayoutParams) r36
            r24 = r36
            r36 = r20
            if (r36 == 0) goto L_0x0617
            r36 = r2
            r46 = r36
            r36 = r46
            r37 = r46
            r0 = r37
            int r0 = r0.mTotalLength
            r37 = r0
            r38 = r21
            r39 = r24
            r0 = r39
            int r0 = r0.leftMargin
            r39 = r0
            int r38 = r38 + r39
            r39 = r24
            r0 = r39
            int r0 = r0.rightMargin
            r39 = r0
            int r38 = r38 + r39
            r39 = r2
            r40 = r23
            int r39 = r39.getNextLocationOffset(r40)
            int r38 = r38 + r39
            int r37 = r37 + r38
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
            goto L_0x05b0
        L_0x0617:
            r36 = r2
            r0 = r36
            int r0 = r0.mTotalLength
            r36 = r0
            r25 = r36
            r36 = r2
            r37 = r25
            r38 = r25
            r39 = r21
            int r38 = r38 + r39
            r39 = r24
            r0 = r39
            int r0 = r0.leftMargin
            r39 = r0
            int r38 = r38 + r39
            r39 = r24
            r0 = r39
            int r0 = r0.rightMargin
            r39 = r0
            int r38 = r38 + r39
            r39 = r2
            r40 = r23
            int r39 = r39.getNextLocationOffset(r40)
            int r38 = r38 + r39
            int r37 = java.lang.Math.max(r37, r38)
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
            goto L_0x05b0
        L_0x0655:
            r36 = r2
            r46 = r36
            r36 = r46
            r37 = r46
            r0 = r37
            int r0 = r0.mTotalLength
            r37 = r0
            r38 = r2
            int r38 = r38.getPaddingLeft()
            r39 = r2
            int r39 = r39.getPaddingRight()
            int r38 = r38 + r39
            int r37 = r37 + r38
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
            r36 = r2
            r0 = r36
            int r0 = r0.mTotalLength
            r36 = r0
            r22 = r36
            r36 = r22
            r37 = r2
            int r37 = r37.getSuggestedMinimumWidth()
            int r36 = java.lang.Math.max(r36, r37)
            r22 = r36
            r36 = r22
            r37 = r3
            r38 = 0
            int r36 = android.view.View.resolveSizeAndState(r36, r37, r38)
            r23 = r36
            r36 = r23
            r37 = 16777215(0xffffff, float:2.3509886E-38)
            r36 = r36 & r37
            r22 = r36
            r36 = r22
            r37 = r2
            r0 = r37
            int r0 = r0.mTotalLength
            r37 = r0
            int r36 = r36 - r37
            r24 = r36
            r36 = r15
            if (r36 != 0) goto L_0x06c4
            r36 = r24
            if (r36 == 0) goto L_0x0af9
            r36 = r10
            r37 = 0
            int r36 = (r36 > r37 ? 1 : (r36 == r37 ? 0 : -1))
            if (r36 <= 0) goto L_0x0af9
        L_0x06c4:
            r36 = r2
            r0 = r36
            float r0 = r0.mWeightSum
            r36 = r0
            r37 = 0
            int r36 = (r36 > r37 ? 1 : (r36 == r37 ? 0 : -1))
            if (r36 <= 0) goto L_0x079f
            r36 = r2
            r0 = r36
            float r0 = r0.mWeightSum
            r36 = r0
        L_0x06da:
            r25 = r36
            r36 = r16
            r37 = 0
            r38 = r16
            r39 = 1
            r40 = r16
            r41 = 2
            r42 = r16
            r43 = 3
            r44 = -1
            r46 = r42
            r47 = r43
            r48 = r44
            r42 = r48
            r43 = r46
            r44 = r47
            r45 = r48
            r43[r44] = r45
            r46 = r40
            r47 = r41
            r48 = r42
            r40 = r48
            r41 = r46
            r42 = r47
            r43 = r48
            r41[r42] = r43
            r46 = r38
            r47 = r39
            r48 = r40
            r38 = r48
            r39 = r46
            r40 = r47
            r41 = r48
            r39[r40] = r41
            r36[r37] = r38
            r36 = r17
            r37 = 0
            r38 = r17
            r39 = 1
            r40 = r17
            r41 = 2
            r42 = r17
            r43 = 3
            r44 = -1
            r46 = r42
            r47 = r43
            r48 = r44
            r42 = r48
            r43 = r46
            r44 = r47
            r45 = r48
            r43[r44] = r45
            r46 = r40
            r47 = r41
            r48 = r42
            r40 = r48
            r41 = r46
            r42 = r47
            r43 = r48
            r41[r42] = r43
            r46 = r38
            r47 = r39
            r48 = r40
            r38 = r48
            r39 = r46
            r40 = r47
            r41 = r48
            r39[r40] = r41
            r36[r37] = r38
            r36 = -1
            r5 = r36
            r36 = r2
            r37 = 0
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
            r36 = 0
            r26 = r36
        L_0x0776:
            r36 = r26
            r37 = r11
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x09e4
            r36 = r2
            r37 = r26
            android.view.View r36 = r36.getVirtualChildAt(r37)
            r27 = r36
            r36 = r27
            if (r36 == 0) goto L_0x079c
            r36 = r27
            int r36 = r36.getVisibility()
            r37 = 8
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x07a3
        L_0x079c:
            int r26 = r26 + 1
            goto L_0x0776
        L_0x079f:
            r36 = r10
            goto L_0x06da
        L_0x07a3:
            r36 = r27
            android.view.ViewGroup$LayoutParams r36 = r36.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r36 = (android.support.p003v7.widget.LinearLayoutCompat.LayoutParams) r36
            r28 = r36
            r36 = r28
            r0 = r36
            float r0 = r0.weight
            r36 = r0
            r29 = r36
            r36 = r29
            r37 = 0
            int r36 = (r36 > r37 ? 1 : (r36 == r37 ? 0 : -1))
            if (r36 <= 0) goto L_0x0860
            r36 = r29
            r37 = r24
            r0 = r37
            float r0 = (float) r0
            r37 = r0
            float r36 = r36 * r37
            r37 = r25
            float r36 = r36 / r37
            r0 = r36
            int r0 = (int) r0
            r36 = r0
            r30 = r36
            r36 = r25
            r37 = r29
            float r36 = r36 - r37
            r25 = r36
            r36 = r24
            r37 = r30
            int r36 = r36 - r37
            r24 = r36
            r36 = r4
            r37 = r2
            int r37 = r37.getPaddingTop()
            r38 = r2
            int r38 = r38.getPaddingBottom()
            int r37 = r37 + r38
            r38 = r28
            r0 = r38
            int r0 = r0.topMargin
            r38 = r0
            int r37 = r37 + r38
            r38 = r28
            r0 = r38
            int r0 = r0.bottomMargin
            r38 = r0
            int r37 = r37 + r38
            r38 = r28
            r0 = r38
            int r0 = r0.height
            r38 = r0
            int r36 = getChildMeasureSpec(r36, r37, r38)
            r31 = r36
            r36 = r28
            r0 = r36
            int r0 = r0.width
            r36 = r0
            if (r36 != 0) goto L_0x082b
            r36 = r12
            r37 = 1073741824(0x40000000, float:2.0)
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x0974
        L_0x082b:
            r36 = r27
            int r36 = r36.getMeasuredWidth()
            r37 = r30
            int r36 = r36 + r37
            r32 = r36
            r36 = r32
            if (r36 >= 0) goto L_0x083f
            r36 = 0
            r32 = r36
        L_0x083f:
            r36 = r27
            r37 = r32
            r38 = 1073741824(0x40000000, float:2.0)
            int r37 = android.view.View.MeasureSpec.makeMeasureSpec(r37, r38)
            r38 = r31
            r36.measure(r37, r38)
        L_0x084e:
            r36 = r6
            r37 = r27
            int r37 = r37.getMeasuredState()
            r38 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r37 = r37 & r38
            int r36 = android.view.View.combineMeasuredStates(r36, r37)
            r6 = r36
        L_0x0860:
            r36 = r20
            if (r36 == 0) goto L_0x098c
            r36 = r2
            r46 = r36
            r36 = r46
            r37 = r46
            r0 = r37
            int r0 = r0.mTotalLength
            r37 = r0
            r38 = r27
            int r38 = r38.getMeasuredWidth()
            r39 = r28
            r0 = r39
            int r0 = r0.leftMargin
            r39 = r0
            int r38 = r38 + r39
            r39 = r28
            r0 = r39
            int r0 = r0.rightMargin
            r39 = r0
            int r38 = r38 + r39
            r39 = r2
            r40 = r27
            int r39 = r39.getNextLocationOffset(r40)
            int r38 = r38 + r39
            int r37 = r37 + r38
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
        L_0x089e:
            r36 = r13
            r37 = 1073741824(0x40000000, float:2.0)
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x09ce
            r36 = r28
            r0 = r36
            int r0 = r0.height
            r36 = r0
            r37 = -1
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x09ce
            r36 = 1
        L_0x08ba:
            r30 = r36
            r36 = r28
            r0 = r36
            int r0 = r0.topMargin
            r36 = r0
            r37 = r28
            r0 = r37
            int r0 = r0.bottomMargin
            r37 = r0
            int r36 = r36 + r37
            r31 = r36
            r36 = r27
            int r36 = r36.getMeasuredHeight()
            r37 = r31
            int r36 = r36 + r37
            r32 = r36
            r36 = r5
            r37 = r32
            int r36 = java.lang.Math.max(r36, r37)
            r5 = r36
            r36 = r7
            r37 = r30
            if (r37 == 0) goto L_0x09d2
            r37 = r31
        L_0x08ee:
            int r36 = java.lang.Math.max(r36, r37)
            r7 = r36
            r36 = r9
            if (r36 == 0) goto L_0x09d6
            r36 = r28
            r0 = r36
            int r0 = r0.height
            r36 = r0
            r37 = -1
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x09d6
            r36 = 1
        L_0x090a:
            r9 = r36
            r36 = r18
            if (r36 == 0) goto L_0x079c
            r36 = r27
            int r36 = r36.getBaseline()
            r33 = r36
            r36 = r33
            r37 = -1
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x079c
            r36 = r28
            r0 = r36
            int r0 = r0.gravity
            r36 = r0
            if (r36 >= 0) goto L_0x09da
            r36 = r2
            r0 = r36
            int r0 = r0.mGravity
            r36 = r0
        L_0x0934:
            r37 = 112(0x70, float:1.57E-43)
            r36 = r36 & 112(0x70, float:1.57E-43)
            r34 = r36
            r36 = r34
            r37 = 4
            int r36 = r36 >> 4
            r37 = -2
            r36 = r36 & -2
            r37 = 1
            int r36 = r36 >> 1
            r35 = r36
            r36 = r16
            r37 = r35
            r38 = r16
            r39 = r35
            r38 = r38[r39]
            r39 = r33
            int r38 = java.lang.Math.max(r38, r39)
            r36[r37] = r38
            r36 = r17
            r37 = r35
            r38 = r17
            r39 = r35
            r38 = r38[r39]
            r39 = r32
            r40 = r33
            int r39 = r39 - r40
            int r38 = java.lang.Math.max(r38, r39)
            r36[r37] = r38
            goto L_0x079c
        L_0x0974:
            r36 = r27
            r37 = r30
            if (r37 <= 0) goto L_0x0989
            r37 = r30
        L_0x097c:
            r38 = 1073741824(0x40000000, float:2.0)
            int r37 = android.view.View.MeasureSpec.makeMeasureSpec(r37, r38)
            r38 = r31
            r36.measure(r37, r38)
            goto L_0x084e
        L_0x0989:
            r37 = 0
            goto L_0x097c
        L_0x098c:
            r36 = r2
            r0 = r36
            int r0 = r0.mTotalLength
            r36 = r0
            r30 = r36
            r36 = r2
            r37 = r30
            r38 = r30
            r39 = r27
            int r39 = r39.getMeasuredWidth()
            int r38 = r38 + r39
            r39 = r28
            r0 = r39
            int r0 = r0.leftMargin
            r39 = r0
            int r38 = r38 + r39
            r39 = r28
            r0 = r39
            int r0 = r0.rightMargin
            r39 = r0
            int r38 = r38 + r39
            r39 = r2
            r40 = r27
            int r39 = r39.getNextLocationOffset(r40)
            int r38 = r38 + r39
            int r37 = java.lang.Math.max(r37, r38)
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
            goto L_0x089e
        L_0x09ce:
            r36 = 0
            goto L_0x08ba
        L_0x09d2:
            r37 = r32
            goto L_0x08ee
        L_0x09d6:
            r36 = 0
            goto L_0x090a
        L_0x09da:
            r36 = r28
            r0 = r36
            int r0 = r0.gravity
            r36 = r0
            goto L_0x0934
        L_0x09e4:
            r36 = r2
            r46 = r36
            r36 = r46
            r37 = r46
            r0 = r37
            int r0 = r0.mTotalLength
            r37 = r0
            r38 = r2
            int r38 = r38.getPaddingLeft()
            r39 = r2
            int r39 = r39.getPaddingRight()
            int r38 = r38 + r39
            int r37 = r37 + r38
            r0 = r37
            r1 = r36
            r1.mTotalLength = r0
            r36 = r16
            r37 = 1
            r36 = r36[r37]
            r37 = -1
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0a40
            r36 = r16
            r37 = 0
            r36 = r36[r37]
            r37 = -1
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0a40
            r36 = r16
            r37 = 2
            r36 = r36[r37]
            r37 = -1
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0a40
            r36 = r16
            r37 = 3
            r36 = r36[r37]
            r37 = -1
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x0a9a
        L_0x0a40:
            r36 = r16
            r37 = 3
            r36 = r36[r37]
            r37 = r16
            r38 = 0
            r37 = r37[r38]
            r38 = r16
            r39 = 1
            r38 = r38[r39]
            r39 = r16
            r40 = 2
            r39 = r39[r40]
            int r38 = java.lang.Math.max(r38, r39)
            int r37 = java.lang.Math.max(r37, r38)
            int r36 = java.lang.Math.max(r36, r37)
            r26 = r36
            r36 = r17
            r37 = 3
            r36 = r36[r37]
            r37 = r17
            r38 = 0
            r37 = r37[r38]
            r38 = r17
            r39 = 1
            r38 = r38[r39]
            r39 = r17
            r40 = 2
            r39 = r39[r40]
            int r38 = java.lang.Math.max(r38, r39)
            int r37 = java.lang.Math.max(r37, r38)
            int r36 = java.lang.Math.max(r36, r37)
            r27 = r36
            r36 = r5
            r37 = r26
            r38 = r27
            int r37 = r37 + r38
            int r36 = java.lang.Math.max(r36, r37)
            r5 = r36
        L_0x0a9a:
            r36 = r9
            if (r36 != 0) goto L_0x0aac
            r36 = r13
            r37 = 1073741824(0x40000000, float:2.0)
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x0aac
            r36 = r7
            r5 = r36
        L_0x0aac:
            r36 = r5
            r37 = r2
            int r37 = r37.getPaddingTop()
            r38 = r2
            int r38 = r38.getPaddingBottom()
            int r37 = r37 + r38
            int r36 = r36 + r37
            r5 = r36
            r36 = r5
            r37 = r2
            int r37 = r37.getSuggestedMinimumHeight()
            int r36 = java.lang.Math.max(r36, r37)
            r5 = r36
            r36 = r2
            r37 = r23
            r38 = r6
            r39 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r38 = r38 & r39
            r37 = r37 | r38
            r38 = r5
            r39 = r4
            r40 = r6
            r41 = 16
            int r40 = r40 << 16
            int r38 = android.view.View.resolveSizeAndState(r38, r39, r40)
            r36.setMeasuredDimension(r37, r38)
            r36 = r14
            if (r36 == 0) goto L_0x0af8
            r36 = r2
            r37 = r11
            r38 = r3
            r36.forceUniformHeight(r37, r38)
        L_0x0af8:
            return
        L_0x0af9:
            r36 = r7
            r37 = r8
            int r36 = java.lang.Math.max(r36, r37)
            r7 = r36
            r36 = r19
            if (r36 == 0) goto L_0x0a9a
            r36 = r12
            r37 = 1073741824(0x40000000, float:2.0)
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x0a9a
            r36 = 0
            r25 = r36
        L_0x0b15:
            r36 = r25
            r37 = r11
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x0a9a
            r36 = r2
            r37 = r25
            android.view.View r36 = r36.getVirtualChildAt(r37)
            r26 = r36
            r36 = r26
            if (r36 == 0) goto L_0x0b3b
            r36 = r26
            int r36 = r36.getVisibility()
            r37 = 8
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0b3e
        L_0x0b3b:
            int r25 = r25 + 1
            goto L_0x0b15
        L_0x0b3e:
            r36 = r26
            android.view.ViewGroup$LayoutParams r36 = r36.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r36 = (android.support.p003v7.widget.LinearLayoutCompat.LayoutParams) r36
            r27 = r36
            r36 = r27
            r0 = r36
            float r0 = r0.weight
            r36 = r0
            r28 = r36
            r36 = r28
            r37 = 0
            int r36 = (r36 > r37 ? 1 : (r36 == r37 ? 0 : -1))
            if (r36 <= 0) goto L_0x0b3b
            r36 = r26
            r37 = r21
            r38 = 1073741824(0x40000000, float:2.0)
            int r37 = android.view.View.MeasureSpec.makeMeasureSpec(r37, r38)
            r38 = r26
            int r38 = r38.getMeasuredHeight()
            r39 = 1073741824(0x40000000, float:2.0)
            int r38 = android.view.View.MeasureSpec.makeMeasureSpec(r38, r39)
            r36.measure(r37, r38)
            goto L_0x0b3b
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.LinearLayoutCompat.measureHorizontal(int, int):void");
    }

    private void forceUniformHeight(int i, int i2) {
        int count = i;
        int widthMeasureSpec = i2;
        int uniformMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), Declaration.MODULE_REFERENCE);
        for (int i3 = 0; i3 < count; i3++) {
            View child = getVirtualChildAt(i3);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.height == -1) {
                    int oldWidth = lp.width;
                    lp.width = child.getMeasuredWidth();
                    measureChildWithMargins(child, widthMeasureSpec, 0, uniformMeasureSpec, 0);
                    lp.width = oldWidth;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getChildrenSkipCount(View view, int i) {
        View view2 = view;
        int i2 = i;
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int measureNullChild(int i) {
        int i2 = i;
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void measureChildBeforeLayout(View child, int i, int widthMeasureSpec, int totalWidth, int heightMeasureSpec, int totalHeight) {
        int i2 = i;
        measureChildWithMargins(child, widthMeasureSpec, totalWidth, heightMeasureSpec, totalHeight);
    }

    /* access modifiers changed from: package-private */
    public int getLocationOffset(View view) {
        View view2 = view;
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getNextLocationOffset(View view) {
        View view2 = view;
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2 = z;
        int l = i;
        int t = i2;
        int r = i3;
        int b = i4;
        if (this.mOrientation == 1) {
            layoutVertical(l, t, r, b);
        } else {
            layoutHorizontal(l, t, r, b);
        }
    }

    /* access modifiers changed from: package-private */
    public void layoutVertical(int left, int i, int right, int i2) {
        int childTop;
        int childLeft;
        int top = i;
        int bottom = i2;
        int paddingLeft = getPaddingLeft();
        int width = right - left;
        int childRight = width - getPaddingRight();
        int childSpace = (width - paddingLeft) - getPaddingRight();
        int count = getVirtualChildCount();
        int majorGravity = this.mGravity & 112;
        int minorGravity = this.mGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        switch (majorGravity) {
            case 16:
                childTop = getPaddingTop() + (((bottom - top) - this.mTotalLength) / 2);
                break;
            case 80:
                childTop = ((getPaddingTop() + bottom) - top) - this.mTotalLength;
                break;
            default:
                childTop = getPaddingTop();
                break;
        }
        int i3 = 0;
        while (i3 < count) {
            View child = getVirtualChildAt(i3);
            if (child == null) {
                childTop += measureNullChild(i3);
            } else if (child.getVisibility() != 8) {
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                int gravity = lp.gravity;
                if (gravity < 0) {
                    gravity = minorGravity;
                }
                switch (GravityCompat.getAbsoluteGravity(gravity, ViewCompat.getLayoutDirection(this)) & 7) {
                    case 1:
                        childLeft = ((paddingLeft + ((childSpace - childWidth) / 2)) + lp.leftMargin) - lp.rightMargin;
                        break;
                    case 5:
                        childLeft = (childRight - childWidth) - lp.rightMargin;
                        break;
                    default:
                        childLeft = paddingLeft + lp.leftMargin;
                        break;
                }
                if (hasDividerBeforeChildAt(i3)) {
                    childTop += this.mDividerHeight;
                }
                int childTop2 = childTop + lp.topMargin;
                setChildFrame(child, childLeft, childTop2 + getLocationOffset(child), childWidth, childHeight);
                childTop = childTop2 + childHeight + lp.bottomMargin + getNextLocationOffset(child);
                i3 += getChildrenSkipCount(child, i3);
            }
            i3++;
        }
    }

    /* access modifiers changed from: package-private */
    public void layoutHorizontal(int i, int top, int i2, int bottom) {
        int childLeft;
        int childTop;
        int left = i;
        int right = i2;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int paddingTop = getPaddingTop();
        int height = bottom - top;
        int childBottom = height - getPaddingBottom();
        int childSpace = (height - paddingTop) - getPaddingBottom();
        int count = getVirtualChildCount();
        int majorGravity = this.mGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int minorGravity = this.mGravity & 112;
        boolean baselineAligned = this.mBaselineAligned;
        int[] maxAscent = this.mMaxAscent;
        int[] maxDescent = this.mMaxDescent;
        switch (GravityCompat.getAbsoluteGravity(majorGravity, ViewCompat.getLayoutDirection(this))) {
            case 1:
                childLeft = getPaddingLeft() + (((right - left) - this.mTotalLength) / 2);
                break;
            case 5:
                childLeft = ((getPaddingLeft() + right) - left) - this.mTotalLength;
                break;
            default:
                childLeft = getPaddingLeft();
                break;
        }
        int start = 0;
        int dir = 1;
        if (isLayoutRtl) {
            start = count - 1;
            dir = -1;
        }
        int i3 = 0;
        while (i3 < count) {
            int childIndex = start + (dir * i3);
            View child = getVirtualChildAt(childIndex);
            if (child == null) {
                childLeft += measureNullChild(childIndex);
            } else if (child.getVisibility() != 8) {
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                int childBaseline = -1;
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (baselineAligned && lp.height != -1) {
                    childBaseline = child.getBaseline();
                }
                int gravity = lp.gravity;
                if (gravity < 0) {
                    gravity = minorGravity;
                }
                switch (gravity & 112) {
                    case 16:
                        childTop = ((paddingTop + ((childSpace - childHeight) / 2)) + lp.topMargin) - lp.bottomMargin;
                        break;
                    case 48:
                        childTop = paddingTop + lp.topMargin;
                        if (childBaseline != -1) {
                            childTop += maxAscent[1] - childBaseline;
                            break;
                        }
                        break;
                    case 80:
                        childTop = (childBottom - childHeight) - lp.bottomMargin;
                        if (childBaseline != -1) {
                            childTop -= maxDescent[2] - (child.getMeasuredHeight() - childBaseline);
                            break;
                        }
                        break;
                    default:
                        childTop = paddingTop;
                        break;
                }
                if (hasDividerBeforeChildAt(childIndex)) {
                    childLeft += this.mDividerWidth;
                }
                int childLeft2 = childLeft + lp.leftMargin;
                setChildFrame(child, childLeft2 + getLocationOffset(child), childTop, childWidth, childHeight);
                childLeft = childLeft2 + childWidth + lp.rightMargin + getNextLocationOffset(child);
                i3 += getChildrenSkipCount(child, childIndex);
            }
            i3++;
        }
    }

    private void setChildFrame(View child, int i, int i2, int width, int height) {
        int left = i;
        int top = i2;
        child.layout(left, top, left + width, top + height);
    }

    public void setOrientation(int i) {
        int orientation = i;
        if (this.mOrientation != orientation) {
            this.mOrientation = orientation;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int i) {
        int gravity = i;
        if (this.mGravity != gravity) {
            if ((gravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) == 0) {
                gravity |= GravityCompat.START;
            }
            if ((gravity & 112) == 0) {
                gravity |= 48;
            }
            this.mGravity = gravity;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int horizontalGravity) {
        int gravity = horizontalGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if ((this.mGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) != gravity) {
            this.mGravity = (this.mGravity & -8388616) | gravity;
            requestLayout();
        }
    }

    public void setVerticalGravity(int verticalGravity) {
        int gravity = verticalGravity & 112;
        if ((this.mGravity & 112) != gravity) {
            this.mGravity = (this.mGravity & -113) | gravity;
            requestLayout();
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        LayoutParams layoutParams;
        new LayoutParams(getContext(), attrs);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams;
        LayoutParams layoutParams2;
        if (this.mOrientation == 0) {
            new LayoutParams(-2, -2);
            return layoutParams2;
        } else if (this.mOrientation != 1) {
            return null;
        } else {
            new LayoutParams(-1, -2);
            return layoutParams;
        }
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        LayoutParams layoutParams;
        new LayoutParams(p);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        AccessibilityEvent event = accessibilityEvent;
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(LinearLayoutCompat.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        AccessibilityNodeInfo info = accessibilityNodeInfo;
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(LinearLayoutCompat.class.getName());
    }

    /* renamed from: android.support.v7.widget.LinearLayoutCompat$LayoutParams */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;
        public float weight;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(android.content.Context r9, android.util.AttributeSet r10) {
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
                r5 = -1
                r4.gravity = r5
                r4 = r1
                r5 = r2
                int[] r6 = android.support.p003v7.appcompat.C0425R.styleable.LinearLayoutCompat_Layout
                android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r6)
                r3 = r4
                r4 = r0
                r5 = r3
                int r6 = android.support.p003v7.appcompat.C0425R.styleable.LinearLayoutCompat_Layout_android_layout_weight
                r7 = 0
                float r5 = r5.getFloat(r6, r7)
                r4.weight = r5
                r4 = r0
                r5 = r3
                int r6 = android.support.p003v7.appcompat.C0425R.styleable.LinearLayoutCompat_Layout_android_layout_gravity
                r7 = -1
                int r5 = r5.getInt(r6, r7)
                r4.gravity = r5
                r4 = r3
                r4.recycle()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.LinearLayoutCompat.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height) {
            super(width, height);
            this.gravity = -1;
            this.weight = 0.0f;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height, float weight2) {
            super(width, height);
            this.gravity = -1;
            this.weight = weight2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
            this.gravity = -1;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
            this.gravity = -1;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(android.support.p003v7.widget.LinearLayoutCompat.LayoutParams r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                r2.<init>(r3)
                r2 = r0
                r3 = -1
                r2.gravity = r3
                r2 = r0
                r3 = r1
                float r3 = r3.weight
                r2.weight = r3
                r2 = r0
                r3 = r1
                int r3 = r3.gravity
                r2.gravity = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.LinearLayoutCompat.LayoutParams.<init>(android.support.v7.widget.LinearLayoutCompat$LayoutParams):void");
        }
    }
}
