package android.support.design.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.design.C0064R;
import android.support.design.animation.AnimationUtils;
import android.support.design.widget.AppBarLayout;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.math.MathUtils;
import android.support.p000v4.util.ObjectsCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.WindowInsetsCompat;
import android.support.p003v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import gnu.expr.Declaration;

public class CollapsingToolbarLayout extends FrameLayout {
    private static final int DEFAULT_SCRIM_ANIMATION_DURATION = 600;
    final CollapsingTextHelper collapsingTextHelper;
    private boolean collapsingTitleEnabled;
    private Drawable contentScrim;
    int currentOffset;
    private boolean drawCollapsingTitle;
    private View dummyView;
    private int expandedMarginBottom;
    private int expandedMarginEnd;
    private int expandedMarginStart;
    private int expandedMarginTop;
    WindowInsetsCompat lastInsets;
    private AppBarLayout.OnOffsetChangedListener onOffsetChangedListener;
    private boolean refreshToolbar;
    private int scrimAlpha;
    private long scrimAnimationDuration;
    private ValueAnimator scrimAnimator;
    private int scrimVisibleHeightTrigger;
    private boolean scrimsAreShown;
    Drawable statusBarScrim;
    private final Rect tmpRect;
    private Toolbar toolbar;
    private View toolbarDirectChild;
    private int toolbarId;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CollapsingToolbarLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CollapsingToolbarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CollapsingToolbarLayout(android.content.Context r15, android.util.AttributeSet r16, int r17) {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            r5.<init>(r6, r7, r8)
            r5 = r0
            r6 = 1
            r5.refreshToolbar = r6
            r5 = r0
            android.graphics.Rect r6 = new android.graphics.Rect
            r12 = r6
            r6 = r12
            r7 = r12
            r7.<init>()
            r5.tmpRect = r6
            r5 = r0
            r6 = -1
            r5.scrimVisibleHeightTrigger = r6
            r5 = r0
            android.support.design.widget.CollapsingTextHelper r6 = new android.support.design.widget.CollapsingTextHelper
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = r0
            r7.<init>(r8)
            r5.collapsingTextHelper = r6
            r5 = r0
            android.support.design.widget.CollapsingTextHelper r5 = r5.collapsingTextHelper
            android.animation.TimeInterpolator r6 = android.support.design.animation.AnimationUtils.DECELERATE_INTERPOLATOR
            r5.setTextSizeInterpolator(r6)
            r5 = r1
            r6 = r2
            int[] r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout
            r8 = r3
            int r9 = android.support.design.C0064R.C0068style.Widget_Design_CollapsingToolbar
            r10 = 0
            int[] r10 = new int[r10]
            android.content.res.TypedArray r5 = android.support.design.internal.ThemeEnforcement.obtainStyledAttributes(r5, r6, r7, r8, r9, r10)
            r4 = r5
            r5 = r0
            android.support.design.widget.CollapsingTextHelper r5 = r5.collapsingTextHelper
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_expandedTitleGravity
            r8 = 8388691(0x800053, float:1.175506E-38)
            int r6 = r6.getInt(r7, r8)
            r5.setExpandedTextGravity(r6)
            r5 = r0
            android.support.design.widget.CollapsingTextHelper r5 = r5.collapsingTextHelper
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_collapsedTitleGravity
            r8 = 8388627(0x800013, float:1.175497E-38)
            int r6 = r6.getInt(r7, r8)
            r5.setCollapsedTextGravity(r6)
            r5 = r0
            r6 = r0
            r7 = r0
            r8 = r0
            r9 = r4
            int r10 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_expandedTitleMargin
            r11 = 0
            int r9 = r9.getDimensionPixelSize(r10, r11)
            r12 = r8
            r13 = r9
            r8 = r13
            r9 = r12
            r10 = r13
            r9.expandedMarginBottom = r10
            r12 = r7
            r13 = r8
            r7 = r13
            r8 = r12
            r9 = r13
            r8.expandedMarginEnd = r9
            r12 = r6
            r13 = r7
            r6 = r13
            r7 = r12
            r8 = r13
            r7.expandedMarginTop = r8
            r5.expandedMarginStart = r6
            r5 = r4
            int r6 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart
            boolean r5 = r5.hasValue(r6)
            if (r5 == 0) goto L_0x009a
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart
            r8 = 0
            int r6 = r6.getDimensionPixelSize(r7, r8)
            r5.expandedMarginStart = r6
        L_0x009a:
            r5 = r4
            int r6 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd
            boolean r5 = r5.hasValue(r6)
            if (r5 == 0) goto L_0x00ae
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd
            r8 = 0
            int r6 = r6.getDimensionPixelSize(r7, r8)
            r5.expandedMarginEnd = r6
        L_0x00ae:
            r5 = r4
            int r6 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop
            boolean r5 = r5.hasValue(r6)
            if (r5 == 0) goto L_0x00c2
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop
            r8 = 0
            int r6 = r6.getDimensionPixelSize(r7, r8)
            r5.expandedMarginTop = r6
        L_0x00c2:
            r5 = r4
            int r6 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom
            boolean r5 = r5.hasValue(r6)
            if (r5 == 0) goto L_0x00d6
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom
            r8 = 0
            int r6 = r6.getDimensionPixelSize(r7, r8)
            r5.expandedMarginBottom = r6
        L_0x00d6:
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_titleEnabled
            r8 = 1
            boolean r6 = r6.getBoolean(r7, r8)
            r5.collapsingTitleEnabled = r6
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_title
            java.lang.CharSequence r6 = r6.getText(r7)
            r5.setTitle(r6)
            r5 = r0
            android.support.design.widget.CollapsingTextHelper r5 = r5.collapsingTextHelper
            int r6 = android.support.design.C0064R.C0068style.TextAppearance_Design_CollapsingToolbar_Expanded
            r5.setExpandedTextAppearance(r6)
            r5 = r0
            android.support.design.widget.CollapsingTextHelper r5 = r5.collapsingTextHelper
            int r6 = android.support.p003v7.appcompat.C0425R.C0428style.TextAppearance_AppCompat_Widget_ActionBar_Title
            r5.setCollapsedTextAppearance(r6)
            r5 = r4
            int r6 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance
            boolean r5 = r5.hasValue(r6)
            if (r5 == 0) goto L_0x0113
            r5 = r0
            android.support.design.widget.CollapsingTextHelper r5 = r5.collapsingTextHelper
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance
            r8 = 0
            int r6 = r6.getResourceId(r7, r8)
            r5.setExpandedTextAppearance(r6)
        L_0x0113:
            r5 = r4
            int r6 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance
            boolean r5 = r5.hasValue(r6)
            if (r5 == 0) goto L_0x012a
            r5 = r0
            android.support.design.widget.CollapsingTextHelper r5 = r5.collapsingTextHelper
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance
            r8 = 0
            int r6 = r6.getResourceId(r7, r8)
            r5.setCollapsedTextAppearance(r6)
        L_0x012a:
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger
            r8 = -1
            int r6 = r6.getDimensionPixelSize(r7, r8)
            r5.scrimVisibleHeightTrigger = r6
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_scrimAnimationDuration
            r8 = 600(0x258, float:8.41E-43)
            int r6 = r6.getInt(r7, r8)
            long r6 = (long) r6
            r5.scrimAnimationDuration = r6
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_contentScrim
            android.graphics.drawable.Drawable r6 = r6.getDrawable(r7)
            r5.setContentScrim(r6)
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_statusBarScrim
            android.graphics.drawable.Drawable r6 = r6.getDrawable(r7)
            r5.setStatusBarScrim(r6)
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_toolbarId
            r8 = -1
            int r6 = r6.getResourceId(r7, r8)
            r5.toolbarId = r6
            r5 = r4
            r5.recycle()
            r5 = r0
            r6 = 0
            r5.setWillNotDraw(r6)
            r5 = r0
            android.support.design.widget.CollapsingToolbarLayout$1 r6 = new android.support.design.widget.CollapsingToolbarLayout$1
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = r0
            r7.<init>(r8)
            android.support.p000v4.view.ViewCompat.setOnApplyWindowInsetsListener(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.CollapsingToolbarLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        AppBarLayout.OnOffsetChangedListener onOffsetChangedListener2;
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            ViewCompat.setFitsSystemWindows(this, ViewCompat.getFitsSystemWindows((View) parent));
            if (this.onOffsetChangedListener == null) {
                new OffsetUpdateListener(this);
                this.onOffsetChangedListener = onOffsetChangedListener2;
            }
            ((AppBarLayout) parent).addOnOffsetChangedListener(this.onOffsetChangedListener);
            ViewCompat.requestApplyInsets(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        ViewParent parent = getParent();
        if (this.onOffsetChangedListener != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).removeOnOffsetChangedListener(this.onOffsetChangedListener);
        }
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: package-private */
    public WindowInsetsCompat onWindowInsetChanged(WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat insets = windowInsetsCompat;
        WindowInsetsCompat newInsets = null;
        if (ViewCompat.getFitsSystemWindows(this)) {
            newInsets = insets;
        }
        if (!ObjectsCompat.equals(this.lastInsets, newInsets)) {
            this.lastInsets = newInsets;
            requestLayout();
        }
        return insets.consumeSystemWindowInsets();
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        super.draw(canvas2);
        ensureToolbar();
        if (this.toolbar == null && this.contentScrim != null && this.scrimAlpha > 0) {
            this.contentScrim.mutate().setAlpha(this.scrimAlpha);
            this.contentScrim.draw(canvas2);
        }
        if (this.collapsingTitleEnabled && this.drawCollapsingTitle) {
            this.collapsingTextHelper.draw(canvas2);
        }
        if (this.statusBarScrim != null && this.scrimAlpha > 0) {
            int topInset = this.lastInsets != null ? this.lastInsets.getSystemWindowInsetTop() : 0;
            if (topInset > 0) {
                this.statusBarScrim.setBounds(0, -this.currentOffset, getWidth(), topInset - this.currentOffset);
                this.statusBarScrim.mutate().setAlpha(this.scrimAlpha);
                this.statusBarScrim.draw(canvas2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        Canvas canvas2 = canvas;
        View child = view;
        long drawingTime = j;
        boolean invalidated = false;
        if (this.contentScrim != null && this.scrimAlpha > 0 && isToolbarChild(child)) {
            this.contentScrim.mutate().setAlpha(this.scrimAlpha);
            this.contentScrim.draw(canvas2);
            invalidated = true;
        }
        return super.drawChild(canvas2, child, drawingTime) || invalidated;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int oldw, int oldh) {
        int w = i;
        int h = i2;
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.contentScrim != null) {
            this.contentScrim.setBounds(0, 0, w, h);
        }
    }

    private void ensureToolbar() {
        if (this.refreshToolbar) {
            this.toolbar = null;
            this.toolbarDirectChild = null;
            if (this.toolbarId != -1) {
                this.toolbar = (Toolbar) findViewById(this.toolbarId);
                if (this.toolbar != null) {
                    this.toolbarDirectChild = findDirectChild(this.toolbar);
                }
            }
            if (this.toolbar == null) {
                Toolbar toolbar2 = null;
                int i = 0;
                int count = getChildCount();
                while (true) {
                    if (i >= count) {
                        break;
                    }
                    View child = getChildAt(i);
                    if (child instanceof Toolbar) {
                        toolbar2 = (Toolbar) child;
                        break;
                    }
                    i++;
                }
                this.toolbar = toolbar2;
            }
            updateDummyView();
            this.refreshToolbar = false;
        }
    }

    private boolean isToolbarChild(View view) {
        View child = view;
        return (this.toolbarDirectChild == null || this.toolbarDirectChild == this) ? child == this.toolbar : child == this.toolbarDirectChild;
    }

    private View findDirectChild(View view) {
        View descendant = view;
        View directChild = descendant;
        ViewParent parent = descendant.getParent();
        while (true) {
            ViewParent p = parent;
            if (p != this && p != null) {
                if (p instanceof View) {
                    directChild = (View) p;
                }
                parent = p.getParent();
            }
        }
        return directChild;
    }

    private void updateDummyView() {
        View view;
        if (!this.collapsingTitleEnabled && this.dummyView != null) {
            ViewParent parent = this.dummyView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.dummyView);
            }
        }
        if (this.collapsingTitleEnabled && this.toolbar != null) {
            if (this.dummyView == null) {
                new View(getContext());
                this.dummyView = view;
            }
            if (this.dummyView.getParent() == null) {
                this.toolbar.addView(this.dummyView, -1, -1);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        ensureToolbar();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = View.MeasureSpec.getMode(heightMeasureSpec);
        int topInset = this.lastInsets != null ? this.lastInsets.getSystemWindowInsetTop() : 0;
        if (mode == 0 && topInset > 0) {
            super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + topInset, Declaration.MODULE_REFERENCE));
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int left = i;
        int top = i2;
        int right = i3;
        int bottom = i4;
        super.onLayout(changed, left, top, right, bottom);
        if (this.lastInsets != null) {
            int insetTop = this.lastInsets.getSystemWindowInsetTop();
            int z = getChildCount();
            for (int i7 = 0; i7 < z; i7++) {
                View child = getChildAt(i7);
                if (!ViewCompat.getFitsSystemWindows(child) && child.getTop() < insetTop) {
                    ViewCompat.offsetTopAndBottom(child, insetTop);
                }
            }
        }
        if (this.collapsingTitleEnabled && this.dummyView != null) {
            this.drawCollapsingTitle = ViewCompat.isAttachedToWindow(this.dummyView) && this.dummyView.getVisibility() == 0;
            if (this.drawCollapsingTitle) {
                boolean isRtl = ViewCompat.getLayoutDirection(this) == 1;
                int maxOffset = getMaxOffsetForPinChild(this.toolbarDirectChild != null ? this.toolbarDirectChild : this.toolbar);
                DescendantOffsetUtils.getDescendantRect(this, this.dummyView, this.tmpRect);
                this.collapsingTextHelper.setCollapsedBounds(this.tmpRect.left + (isRtl ? this.toolbar.getTitleMarginEnd() : this.toolbar.getTitleMarginStart()), this.tmpRect.top + maxOffset + this.toolbar.getTitleMarginTop(), this.tmpRect.right + (isRtl ? this.toolbar.getTitleMarginStart() : this.toolbar.getTitleMarginEnd()), (this.tmpRect.bottom + maxOffset) - this.toolbar.getTitleMarginBottom());
                CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
                if (isRtl) {
                    i5 = this.expandedMarginEnd;
                } else {
                    i5 = this.expandedMarginStart;
                }
                int i8 = this.tmpRect.top + this.expandedMarginTop;
                int i9 = right - left;
                if (isRtl) {
                    i6 = this.expandedMarginStart;
                } else {
                    i6 = this.expandedMarginEnd;
                }
                collapsingTextHelper2.setExpandedBounds(i5, i8, i9 - i6, (bottom - top) - this.expandedMarginBottom);
                this.collapsingTextHelper.recalculate();
            }
        }
        int z2 = getChildCount();
        for (int i10 = 0; i10 < z2; i10++) {
            getViewOffsetHelper(getChildAt(i10)).onViewLayout();
        }
        if (this.toolbar != null) {
            if (this.collapsingTitleEnabled && TextUtils.isEmpty(this.collapsingTextHelper.getText())) {
                setTitle(this.toolbar.getTitle());
            }
            if (this.toolbarDirectChild == null || this.toolbarDirectChild == this) {
                setMinimumHeight(getHeightWithMargins(this.toolbar));
            } else {
                setMinimumHeight(getHeightWithMargins(this.toolbarDirectChild));
            }
        }
        updateScrimVisibility();
    }

    private static int getHeightWithMargins(@NonNull View view) {
        View view2 = view;
        ViewGroup.LayoutParams lp = view2.getLayoutParams();
        if (!(lp instanceof ViewGroup.MarginLayoutParams)) {
            return view2.getHeight();
        }
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
        return view2.getHeight() + mlp.topMargin + mlp.bottomMargin;
    }

    static ViewOffsetHelper getViewOffsetHelper(View view) {
        ViewOffsetHelper viewOffsetHelper;
        View view2 = view;
        ViewOffsetHelper offsetHelper = (ViewOffsetHelper) view2.getTag(C0064R.C0066id.view_offset_helper);
        if (offsetHelper == null) {
            new ViewOffsetHelper(view2);
            offsetHelper = viewOffsetHelper;
            view2.setTag(C0064R.C0066id.view_offset_helper, offsetHelper);
        }
        return offsetHelper;
    }

    public void setTitle(@Nullable CharSequence title) {
        this.collapsingTextHelper.setText(title);
        updateContentDescriptionFromTitle();
    }

    @Nullable
    public CharSequence getTitle() {
        return this.collapsingTitleEnabled ? this.collapsingTextHelper.getText() : null;
    }

    public void setTitleEnabled(boolean z) {
        boolean enabled = z;
        if (enabled != this.collapsingTitleEnabled) {
            this.collapsingTitleEnabled = enabled;
            updateContentDescriptionFromTitle();
            updateDummyView();
            requestLayout();
        }
    }

    public boolean isTitleEnabled() {
        return this.collapsingTitleEnabled;
    }

    public void setScrimsShown(boolean shown) {
        setScrimsShown(shown, ViewCompat.isLaidOut(this) && !isInEditMode());
    }

    public void setScrimsShown(boolean z, boolean z2) {
        boolean shown = z;
        boolean animate = z2;
        if (this.scrimsAreShown != shown) {
            if (animate) {
                animateScrim(shown ? 255 : 0);
            } else {
                setScrimAlpha(shown ? 255 : 0);
            }
            this.scrimsAreShown = shown;
        }
    }

    private void animateScrim(int i) {
        ValueAnimator valueAnimator;
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
        int targetAlpha = i;
        ensureToolbar();
        if (this.scrimAnimator == null) {
            new ValueAnimator();
            this.scrimAnimator = valueAnimator;
            ValueAnimator duration = this.scrimAnimator.setDuration(this.scrimAnimationDuration);
            this.scrimAnimator.setInterpolator(targetAlpha > this.scrimAlpha ? AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR : AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
            new ValueAnimator.AnimatorUpdateListener(this) {
                final /* synthetic */ CollapsingToolbarLayout this$0;

                {
                    this.this$0 = this$0;
                }

                public void onAnimationUpdate(ValueAnimator animator) {
                    this.this$0.setScrimAlpha(((Integer) animator.getAnimatedValue()).intValue());
                }
            };
            this.scrimAnimator.addUpdateListener(animatorUpdateListener);
        } else if (this.scrimAnimator.isRunning()) {
            this.scrimAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.scrimAnimator;
        int[] iArr = new int[2];
        iArr[0] = this.scrimAlpha;
        int[] iArr2 = iArr;
        iArr2[1] = targetAlpha;
        valueAnimator2.setIntValues(iArr2);
        this.scrimAnimator.start();
    }

    /* access modifiers changed from: package-private */
    public void setScrimAlpha(int i) {
        int alpha = i;
        if (alpha != this.scrimAlpha) {
            if (!(this.contentScrim == null || this.toolbar == null)) {
                ViewCompat.postInvalidateOnAnimation(this.toolbar);
            }
            this.scrimAlpha = alpha;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: package-private */
    public int getScrimAlpha() {
        return this.scrimAlpha;
    }

    public void setContentScrim(@Nullable Drawable drawable) {
        Drawable drawable2 = drawable;
        if (this.contentScrim != drawable2) {
            if (this.contentScrim != null) {
                this.contentScrim.setCallback((Drawable.Callback) null);
            }
            this.contentScrim = drawable2 != null ? drawable2.mutate() : null;
            if (this.contentScrim != null) {
                this.contentScrim.setBounds(0, 0, getWidth(), getHeight());
                this.contentScrim.setCallback(this);
                this.contentScrim.setAlpha(this.scrimAlpha);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setContentScrimColor(@ColorInt int color) {
        Drawable drawable;
        new ColorDrawable(color);
        setContentScrim(drawable);
    }

    public void setContentScrimResource(@DrawableRes int resId) {
        setContentScrim(ContextCompat.getDrawable(getContext(), resId));
    }

    @Nullable
    public Drawable getContentScrim() {
        return this.contentScrim;
    }

    public void setStatusBarScrim(@Nullable Drawable drawable) {
        Drawable drawable2 = drawable;
        if (this.statusBarScrim != drawable2) {
            if (this.statusBarScrim != null) {
                this.statusBarScrim.setCallback((Drawable.Callback) null);
            }
            this.statusBarScrim = drawable2 != null ? drawable2.mutate() : null;
            if (this.statusBarScrim != null) {
                if (this.statusBarScrim.isStateful()) {
                    boolean state = this.statusBarScrim.setState(getDrawableState());
                }
                boolean layoutDirection = DrawableCompat.setLayoutDirection(this.statusBarScrim, ViewCompat.getLayoutDirection(this));
                boolean visible = this.statusBarScrim.setVisible(getVisibility() == 0, false);
                this.statusBarScrim.setCallback(this);
                this.statusBarScrim.setAlpha(this.scrimAlpha);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] state = getDrawableState();
        boolean changed = false;
        Drawable d = this.statusBarScrim;
        if (d != null && d.isStateful()) {
            changed = false | d.setState(state);
        }
        Drawable d2 = this.contentScrim;
        if (d2 != null && d2.isStateful()) {
            changed |= d2.setState(state);
        }
        if (this.collapsingTextHelper != null) {
            changed |= this.collapsingTextHelper.setState(state);
        }
        if (changed) {
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        Drawable who = drawable;
        return super.verifyDrawable(who) || who == this.contentScrim || who == this.statusBarScrim;
    }

    public void setVisibility(int i) {
        int visibility = i;
        super.setVisibility(visibility);
        boolean visible = visibility == 0;
        if (!(this.statusBarScrim == null || this.statusBarScrim.isVisible() == visible)) {
            boolean visible2 = this.statusBarScrim.setVisible(visible, false);
        }
        if (this.contentScrim != null && this.contentScrim.isVisible() != visible) {
            boolean visible3 = this.contentScrim.setVisible(visible, false);
        }
    }

    public void setStatusBarScrimColor(@ColorInt int color) {
        Drawable drawable;
        new ColorDrawable(color);
        setStatusBarScrim(drawable);
    }

    public void setStatusBarScrimResource(@DrawableRes int resId) {
        setStatusBarScrim(ContextCompat.getDrawable(getContext(), resId));
    }

    @Nullable
    public Drawable getStatusBarScrim() {
        return this.statusBarScrim;
    }

    public void setCollapsedTitleTextAppearance(@StyleRes int resId) {
        this.collapsingTextHelper.setCollapsedTextAppearance(resId);
    }

    public void setCollapsedTitleTextColor(@ColorInt int color) {
        setCollapsedTitleTextColor(ColorStateList.valueOf(color));
    }

    public void setCollapsedTitleTextColor(@NonNull ColorStateList colors) {
        this.collapsingTextHelper.setCollapsedTextColor(colors);
    }

    public void setCollapsedTitleGravity(int gravity) {
        this.collapsingTextHelper.setCollapsedTextGravity(gravity);
    }

    public int getCollapsedTitleGravity() {
        return this.collapsingTextHelper.getCollapsedTextGravity();
    }

    public void setExpandedTitleTextAppearance(@StyleRes int resId) {
        this.collapsingTextHelper.setExpandedTextAppearance(resId);
    }

    public void setExpandedTitleColor(@ColorInt int color) {
        setExpandedTitleTextColor(ColorStateList.valueOf(color));
    }

    public void setExpandedTitleTextColor(@NonNull ColorStateList colors) {
        this.collapsingTextHelper.setExpandedTextColor(colors);
    }

    public void setExpandedTitleGravity(int gravity) {
        this.collapsingTextHelper.setExpandedTextGravity(gravity);
    }

    public int getExpandedTitleGravity() {
        return this.collapsingTextHelper.getExpandedTextGravity();
    }

    public void setCollapsedTitleTypeface(@Nullable Typeface typeface) {
        this.collapsingTextHelper.setCollapsedTypeface(typeface);
    }

    @NonNull
    public Typeface getCollapsedTitleTypeface() {
        return this.collapsingTextHelper.getCollapsedTypeface();
    }

    public void setExpandedTitleTypeface(@Nullable Typeface typeface) {
        this.collapsingTextHelper.setExpandedTypeface(typeface);
    }

    @NonNull
    public Typeface getExpandedTitleTypeface() {
        return this.collapsingTextHelper.getExpandedTypeface();
    }

    public void setExpandedTitleMargin(int start, int top, int end, int bottom) {
        this.expandedMarginStart = start;
        this.expandedMarginTop = top;
        this.expandedMarginEnd = end;
        this.expandedMarginBottom = bottom;
        requestLayout();
    }

    public int getExpandedTitleMarginStart() {
        return this.expandedMarginStart;
    }

    public void setExpandedTitleMarginStart(int margin) {
        this.expandedMarginStart = margin;
        requestLayout();
    }

    public int getExpandedTitleMarginTop() {
        return this.expandedMarginTop;
    }

    public void setExpandedTitleMarginTop(int margin) {
        this.expandedMarginTop = margin;
        requestLayout();
    }

    public int getExpandedTitleMarginEnd() {
        return this.expandedMarginEnd;
    }

    public void setExpandedTitleMarginEnd(int margin) {
        this.expandedMarginEnd = margin;
        requestLayout();
    }

    public int getExpandedTitleMarginBottom() {
        return this.expandedMarginBottom;
    }

    public void setExpandedTitleMarginBottom(int margin) {
        this.expandedMarginBottom = margin;
        requestLayout();
    }

    public void setScrimVisibleHeightTrigger(@IntRange(from = 0) int i) {
        int height = i;
        if (this.scrimVisibleHeightTrigger != height) {
            this.scrimVisibleHeightTrigger = height;
            updateScrimVisibility();
        }
    }

    public int getScrimVisibleHeightTrigger() {
        if (this.scrimVisibleHeightTrigger >= 0) {
            return this.scrimVisibleHeightTrigger;
        }
        int insetTop = this.lastInsets != null ? this.lastInsets.getSystemWindowInsetTop() : 0;
        int minHeight = ViewCompat.getMinimumHeight(this);
        if (minHeight > 0) {
            return Math.min((minHeight * 2) + insetTop, getHeight());
        }
        return getHeight() / 3;
    }

    public void setScrimAnimationDuration(@IntRange(from = 0) long duration) {
        long j = duration;
        this.scrimAnimationDuration = j;
    }

    public long getScrimAnimationDuration() {
        return this.scrimAnimationDuration;
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams;
        new LayoutParams(-1, -1);
        return layoutParams;
    }

    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        FrameLayout.LayoutParams layoutParams;
        new LayoutParams(getContext(), attrs);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public FrameLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        FrameLayout.LayoutParams layoutParams;
        new LayoutParams(p);
        return layoutParams;
    }

    public static class LayoutParams extends FrameLayout.LayoutParams {
        public static final int COLLAPSE_MODE_OFF = 0;
        public static final int COLLAPSE_MODE_PARALLAX = 2;
        public static final int COLLAPSE_MODE_PIN = 1;
        private static final float DEFAULT_PARALLAX_MULTIPLIER = 0.5f;
        int collapseMode = 0;
        float parallaxMult = DEFAULT_PARALLAX_MULTIPLIER;

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
                r5 = 0
                r4.collapseMode = r5
                r4 = r0
                r5 = 1056964608(0x3f000000, float:0.5)
                r4.parallaxMult = r5
                r4 = r1
                r5 = r2
                int[] r6 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_Layout
                android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r6)
                r3 = r4
                r4 = r0
                r5 = r3
                int r6 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_Layout_layout_collapseMode
                r7 = 0
                int r5 = r5.getInt(r6, r7)
                r4.collapseMode = r5
                r4 = r0
                r5 = r3
                int r6 = android.support.design.C0064R.styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier
                r7 = 1056964608(0x3f000000, float:0.5)
                float r5 = r5.getFloat(r6, r7)
                r4.setParallaxMultiplier(r5)
                r4 = r3
                r4.recycle()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.CollapsingToolbarLayout.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height) {
            super(width, height);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height, int gravity) {
            super(width, height, gravity);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        @RequiresApi(19)
        public LayoutParams(FrameLayout.LayoutParams source) {
            super(source);
        }

        public void setCollapseMode(int collapseMode2) {
            int i = collapseMode2;
            this.collapseMode = i;
        }

        public int getCollapseMode() {
            return this.collapseMode;
        }

        public void setParallaxMultiplier(float multiplier) {
            float f = multiplier;
            this.parallaxMult = f;
        }

        public float getParallaxMultiplier() {
            return this.parallaxMult;
        }
    }

    /* access modifiers changed from: package-private */
    public final void updateScrimVisibility() {
        if (this.contentScrim != null || this.statusBarScrim != null) {
            setScrimsShown(getHeight() + this.currentOffset < getScrimVisibleHeightTrigger());
        }
    }

    /* access modifiers changed from: package-private */
    public final int getMaxOffsetForPinChild(View view) {
        View child = view;
        return ((getHeight() - getViewOffsetHelper(child).getLayoutTop()) - child.getHeight()) - ((LayoutParams) child.getLayoutParams()).bottomMargin;
    }

    private void updateContentDescriptionFromTitle() {
        setContentDescription(getTitle());
    }

    private class OffsetUpdateListener implements AppBarLayout.OnOffsetChangedListener {
        final /* synthetic */ CollapsingToolbarLayout this$0;

        OffsetUpdateListener(CollapsingToolbarLayout collapsingToolbarLayout) {
            this.this$0 = collapsingToolbarLayout;
        }

        public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            AppBarLayout appBarLayout2 = appBarLayout;
            int verticalOffset = i;
            this.this$0.currentOffset = verticalOffset;
            int insetTop = this.this$0.lastInsets != null ? this.this$0.lastInsets.getSystemWindowInsetTop() : 0;
            int z = this.this$0.getChildCount();
            for (int i2 = 0; i2 < z; i2++) {
                View child = this.this$0.getChildAt(i2);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                ViewOffsetHelper offsetHelper = CollapsingToolbarLayout.getViewOffsetHelper(child);
                switch (lp.collapseMode) {
                    case 1:
                        boolean topAndBottomOffset = offsetHelper.setTopAndBottomOffset(MathUtils.clamp(-verticalOffset, 0, this.this$0.getMaxOffsetForPinChild(child)));
                        break;
                    case 2:
                        boolean topAndBottomOffset2 = offsetHelper.setTopAndBottomOffset(Math.round(((float) (-verticalOffset)) * lp.parallaxMult));
                        break;
                }
            }
            this.this$0.updateScrimVisibility();
            if (this.this$0.statusBarScrim != null && insetTop > 0) {
                ViewCompat.postInvalidateOnAnimation(this.this$0);
            }
            this.this$0.collapsingTextHelper.setExpansionFraction(((float) Math.abs(verticalOffset)) / ((float) ((this.this$0.getHeight() - ViewCompat.getMinimumHeight(this.this$0)) - insetTop)));
        }
    }
}
