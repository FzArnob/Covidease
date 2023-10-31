package android.support.design.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.C0015Px;
import android.support.annotation.Dimension;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.C0064R;
import android.support.design.animation.AnimationUtils;
import android.support.design.behavior.HideBottomViewOnScrollBehavior;
import android.support.design.shape.MaterialShapeDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.view.AbsSavedState;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p003v7.widget.ActionMenuView;
import android.support.p003v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class BottomAppBar extends Toolbar implements CoordinatorLayout.AttachedBehavior {
    private static final long ANIMATION_DURATION = 300;
    public static final int FAB_ALIGNMENT_MODE_CENTER = 0;
    public static final int FAB_ALIGNMENT_MODE_END = 1;
    @Nullable
    private Animator attachAnimator;
    /* access modifiers changed from: private */
    public int fabAlignmentMode;
    AnimatorListenerAdapter fabAnimationListener;
    /* access modifiers changed from: private */
    public boolean fabAttached;
    private final int fabOffsetEndMode;
    private boolean hideOnScroll;
    /* access modifiers changed from: private */
    public final MaterialShapeDrawable materialShapeDrawable;
    @Nullable
    private Animator menuAnimator;
    @Nullable
    private Animator modeAnimator;
    /* access modifiers changed from: private */
    public final BottomAppBarTopEdgeTreatment topEdgeTreatment;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FabAlignmentMode {
    }

    static /* synthetic */ Animator access$002(BottomAppBar x0, Animator x1) {
        Animator animator = x1;
        Animator animator2 = animator;
        x0.modeAnimator = animator2;
        return animator;
    }

    static /* synthetic */ Animator access$302(BottomAppBar x0, Animator x1) {
        Animator animator = x1;
        Animator animator2 = animator;
        x0.menuAnimator = animator2;
        return animator;
    }

    static /* synthetic */ Animator access$502(BottomAppBar x0, Animator x1) {
        Animator animator = x1;
        Animator animator2 = animator;
        x0.attachAnimator = animator2;
        return animator;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BottomAppBar(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BottomAppBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, C0064R.attr.bottomAppBarStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BottomAppBar(android.content.Context r18, @android.support.annotation.Nullable android.util.AttributeSet r19, int r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r10 = r0
            r11 = r1
            r12 = r2
            r13 = r3
            r10.<init>(r11, r12, r13)
            r10 = r0
            r11 = 1
            r10.fabAttached = r11
            r10 = r0
            android.support.design.bottomappbar.BottomAppBar$7 r11 = new android.support.design.bottomappbar.BottomAppBar$7
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r0
            r12.<init>(r13)
            r10.fabAnimationListener = r11
            r10 = r1
            r11 = r2
            int[] r12 = android.support.design.C0064R.styleable.BottomAppBar
            r13 = r3
            int r14 = android.support.design.C0064R.C0068style.Widget_MaterialComponents_BottomAppBar
            r15 = 0
            int[] r15 = new int[r15]
            android.content.res.TypedArray r10 = android.support.design.internal.ThemeEnforcement.obtainStyledAttributes(r10, r11, r12, r13, r14, r15)
            r4 = r10
            r10 = r1
            r11 = r4
            int r12 = android.support.design.C0064R.styleable.BottomAppBar_backgroundTint
            android.content.res.ColorStateList r10 = android.support.design.resources.MaterialResources.getColorStateList(r10, r11, r12)
            r5 = r10
            r10 = r4
            int r11 = android.support.design.C0064R.styleable.BottomAppBar_fabCradleMargin
            r12 = 0
            int r10 = r10.getDimensionPixelOffset(r11, r12)
            float r10 = (float) r10
            r6 = r10
            r10 = r4
            int r11 = android.support.design.C0064R.styleable.BottomAppBar_fabCradleRoundedCornerRadius
            r12 = 0
            int r10 = r10.getDimensionPixelOffset(r11, r12)
            float r10 = (float) r10
            r7 = r10
            r10 = r4
            int r11 = android.support.design.C0064R.styleable.BottomAppBar_fabCradleVerticalOffset
            r12 = 0
            int r10 = r10.getDimensionPixelOffset(r11, r12)
            float r10 = (float) r10
            r8 = r10
            r10 = r0
            r11 = r4
            int r12 = android.support.design.C0064R.styleable.BottomAppBar_fabAlignmentMode
            r13 = 0
            int r11 = r11.getInt(r12, r13)
            r10.fabAlignmentMode = r11
            r10 = r0
            r11 = r4
            int r12 = android.support.design.C0064R.styleable.BottomAppBar_hideOnScroll
            r13 = 0
            boolean r11 = r11.getBoolean(r12, r13)
            r10.hideOnScroll = r11
            r10 = r4
            r10.recycle()
            r10 = r0
            r11 = r0
            android.content.res.Resources r11 = r11.getResources()
            int r12 = android.support.design.C0064R.dimen.mtrl_bottomappbar_fabOffsetEndMode
            int r11 = r11.getDimensionPixelOffset(r12)
            r10.fabOffsetEndMode = r11
            r10 = r0
            android.support.design.bottomappbar.BottomAppBarTopEdgeTreatment r11 = new android.support.design.bottomappbar.BottomAppBarTopEdgeTreatment
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r6
            r14 = r7
            r15 = r8
            r12.<init>(r13, r14, r15)
            r10.topEdgeTreatment = r11
            android.support.design.shape.ShapePathModel r10 = new android.support.design.shape.ShapePathModel
            r16 = r10
            r10 = r16
            r11 = r16
            r11.<init>()
            r9 = r10
            r10 = r9
            r11 = r0
            android.support.design.bottomappbar.BottomAppBarTopEdgeTreatment r11 = r11.topEdgeTreatment
            r10.setTopEdge(r11)
            r10 = r0
            android.support.design.shape.MaterialShapeDrawable r11 = new android.support.design.shape.MaterialShapeDrawable
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r9
            r12.<init>(r13)
            r10.materialShapeDrawable = r11
            r10 = r0
            android.support.design.shape.MaterialShapeDrawable r10 = r10.materialShapeDrawable
            r11 = 1
            r10.setShadowEnabled(r11)
            r10 = r0
            android.support.design.shape.MaterialShapeDrawable r10 = r10.materialShapeDrawable
            android.graphics.Paint$Style r11 = android.graphics.Paint.Style.FILL
            r10.setPaintStyle(r11)
            r10 = r0
            android.support.design.shape.MaterialShapeDrawable r10 = r10.materialShapeDrawable
            r11 = r5
            android.support.p000v4.graphics.drawable.DrawableCompat.setTintList(r10, r11)
            r10 = r0
            r11 = r0
            android.support.design.shape.MaterialShapeDrawable r11 = r11.materialShapeDrawable
            android.support.p000v4.view.ViewCompat.setBackground(r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.bottomappbar.BottomAppBar.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public int getFabAlignmentMode() {
        return this.fabAlignmentMode;
    }

    public void setFabAlignmentMode(int i) {
        int fabAlignmentMode2 = i;
        maybeAnimateModeChange(fabAlignmentMode2);
        maybeAnimateMenuView(fabAlignmentMode2, this.fabAttached);
        this.fabAlignmentMode = fabAlignmentMode2;
    }

    public void setBackgroundTint(@Nullable ColorStateList backgroundTint) {
        DrawableCompat.setTintList(this.materialShapeDrawable, backgroundTint);
    }

    @Nullable
    public ColorStateList getBackgroundTint() {
        return this.materialShapeDrawable.getTintList();
    }

    public float getFabCradleMargin() {
        return this.topEdgeTreatment.getFabCradleMargin();
    }

    public void setFabCradleMargin(@Dimension float f) {
        float cradleMargin = f;
        if (cradleMargin != getFabCradleMargin()) {
            this.topEdgeTreatment.setFabCradleMargin(cradleMargin);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    @Dimension
    public float getFabCradleRoundedCornerRadius() {
        return this.topEdgeTreatment.getFabCradleRoundedCornerRadius();
    }

    public void setFabCradleRoundedCornerRadius(@Dimension float f) {
        float roundedCornerRadius = f;
        if (roundedCornerRadius != getFabCradleRoundedCornerRadius()) {
            this.topEdgeTreatment.setFabCradleRoundedCornerRadius(roundedCornerRadius);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    @Dimension
    public float getCradleVerticalOffset() {
        return this.topEdgeTreatment.getCradleVerticalOffset();
    }

    public void setCradleVerticalOffset(@Dimension float f) {
        float verticalOffset = f;
        if (verticalOffset != getCradleVerticalOffset()) {
            this.topEdgeTreatment.setCradleVerticalOffset(verticalOffset);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    public boolean getHideOnScroll() {
        return this.hideOnScroll;
    }

    public void setHideOnScroll(boolean hide) {
        boolean z = hide;
        this.hideOnScroll = z;
    }

    public void replaceMenu(@MenuRes int newMenu) {
        getMenu().clear();
        inflateMenu(newMenu);
    }

    /* access modifiers changed from: package-private */
    public void setFabDiameter(@C0015Px int i) {
        int diameter = i;
        if (((float) diameter) != this.topEdgeTreatment.getFabDiameter()) {
            this.topEdgeTreatment.setFabDiameter((float) diameter);
            this.materialShapeDrawable.invalidateSelf();
        }
    }

    private void maybeAnimateModeChange(int i) {
        List<Animator> list;
        AnimatorSet animatorSet;
        Animator.AnimatorListener animatorListener;
        int targetMode = i;
        if (this.fabAlignmentMode != targetMode && ViewCompat.isLaidOut(this)) {
            if (this.modeAnimator != null) {
                this.modeAnimator.cancel();
            }
            new ArrayList<>();
            List<Animator> animators = list;
            createCradleTranslationAnimation(targetMode, animators);
            createFabTranslationXAnimation(targetMode, animators);
            new AnimatorSet();
            AnimatorSet set = animatorSet;
            set.playTogether(animators);
            this.modeAnimator = set;
            new AnimatorListenerAdapter(this) {
                final /* synthetic */ BottomAppBar this$0;

                {
                    this.this$0 = this$0;
                }

                public void onAnimationEnd(Animator animator) {
                    Animator animator2 = animator;
                    Animator access$002 = BottomAppBar.access$002(this.this$0, (Animator) null);
                }
            };
            this.modeAnimator.addListener(animatorListener);
            this.modeAnimator.start();
        }
    }

    private void createCradleTranslationAnimation(int i, List<Animator> list) {
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
        int targetMode = i;
        List<Animator> animators = list;
        if (this.fabAttached) {
            float[] fArr = new float[2];
            fArr[0] = this.topEdgeTreatment.getHorizontalOffset();
            float[] fArr2 = fArr;
            fArr2[1] = (float) getFabTranslationX(targetMode);
            ValueAnimator animator = ValueAnimator.ofFloat(fArr2);
            new ValueAnimator.AnimatorUpdateListener(this) {
                final /* synthetic */ BottomAppBar this$0;

                {
                    this.this$0 = this$0;
                }

                public void onAnimationUpdate(ValueAnimator animation) {
                    this.this$0.topEdgeTreatment.setHorizontalOffset(((Float) animation.getAnimatedValue()).floatValue());
                    this.this$0.materialShapeDrawable.invalidateSelf();
                }
            };
            animator.addUpdateListener(animatorUpdateListener);
            ValueAnimator duration = animator.setDuration(ANIMATION_DURATION);
            boolean add = animators.add(animator);
        }
    }

    /* access modifiers changed from: private */
    @Nullable
    public FloatingActionButton findDependentFab() {
        if (!(getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        for (View v : ((CoordinatorLayout) getParent()).getDependents(this)) {
            if (v instanceof FloatingActionButton) {
                return (FloatingActionButton) v;
            }
        }
        return null;
    }

    private boolean isVisibleFab() {
        FloatingActionButton fab = findDependentFab();
        return fab != null && fab.isOrWillBeShown();
    }

    private void createFabTranslationXAnimation(int targetMode, List<Animator> animators) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(findDependentFab(), "translationX", new float[]{(float) getFabTranslationX(targetMode)});
        ObjectAnimator duration = animator.setDuration(ANIMATION_DURATION);
        boolean add = animators.add(animator);
    }

    /* access modifiers changed from: private */
    public void maybeAnimateMenuView(int i, boolean z) {
        List<Animator> list;
        AnimatorSet animatorSet;
        Animator.AnimatorListener animatorListener;
        int targetMode = i;
        boolean newFabAttached = z;
        if (ViewCompat.isLaidOut(this)) {
            if (this.menuAnimator != null) {
                this.menuAnimator.cancel();
            }
            new ArrayList<>();
            List<Animator> animators = list;
            if (!isVisibleFab()) {
                targetMode = 0;
                newFabAttached = false;
            }
            createMenuViewTranslationAnimation(targetMode, newFabAttached, animators);
            new AnimatorSet();
            AnimatorSet set = animatorSet;
            set.playTogether(animators);
            this.menuAnimator = set;
            new AnimatorListenerAdapter(this) {
                final /* synthetic */ BottomAppBar this$0;

                {
                    this.this$0 = this$0;
                }

                public void onAnimationEnd(Animator animator) {
                    Animator animator2 = animator;
                    Animator access$302 = BottomAppBar.access$302(this.this$0, (Animator) null);
                }
            };
            this.menuAnimator.addListener(animatorListener);
            this.menuAnimator.start();
        }
    }

    private void createMenuViewTranslationAnimation(int i, boolean z, List<Animator> list) {
        Animator.AnimatorListener animatorListener;
        AnimatorSet animatorSet;
        int targetMode = i;
        boolean targetAttached = z;
        List<Animator> animators = list;
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView != null) {
            Animator fadeIn = ObjectAnimator.ofFloat(actionMenuView, "alpha", new float[]{1.0f});
            if ((this.fabAttached || (targetAttached && isVisibleFab())) && (this.fabAlignmentMode == 1 || targetMode == 1)) {
                Animator fadeOut = ObjectAnimator.ofFloat(actionMenuView, "alpha", new float[]{0.0f});
                final ActionMenuView actionMenuView2 = actionMenuView;
                final int i2 = targetMode;
                final boolean z2 = targetAttached;
                new AnimatorListenerAdapter(this) {
                    public boolean cancelled;
                    final /* synthetic */ BottomAppBar this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onAnimationCancel(Animator animator) {
                        Animator animator2 = animator;
                        this.cancelled = true;
                    }

                    public void onAnimationEnd(Animator animator) {
                        Animator animator2 = animator;
                        if (!this.cancelled) {
                            this.this$0.translateActionMenuView(actionMenuView2, i2, z2);
                        }
                    }
                };
                fadeOut.addListener(animatorListener);
                new AnimatorSet();
                AnimatorSet set = animatorSet;
                AnimatorSet duration = set.setDuration(150);
                Animator[] animatorArr = new Animator[2];
                animatorArr[0] = fadeOut;
                Animator[] animatorArr2 = animatorArr;
                animatorArr2[1] = fadeIn;
                set.playSequentially(animatorArr2);
                boolean add = animators.add(set);
            } else if (actionMenuView.getAlpha() < 1.0f) {
                boolean add2 = animators.add(fadeIn);
            }
        }
    }

    /* access modifiers changed from: private */
    public void maybeAnimateAttachChange(boolean z) {
        List<Animator> list;
        AnimatorSet animatorSet;
        Animator.AnimatorListener animatorListener;
        boolean targetAttached = z;
        if (ViewCompat.isLaidOut(this)) {
            if (this.attachAnimator != null) {
                this.attachAnimator.cancel();
            }
            new ArrayList<>();
            List<Animator> animators = list;
            createCradleShapeAnimation(targetAttached && isVisibleFab(), animators);
            createFabTranslationYAnimation(targetAttached, animators);
            new AnimatorSet();
            AnimatorSet set = animatorSet;
            set.playTogether(animators);
            this.attachAnimator = set;
            new AnimatorListenerAdapter(this) {
                final /* synthetic */ BottomAppBar this$0;

                {
                    this.this$0 = this$0;
                }

                public void onAnimationEnd(Animator animator) {
                    Animator animator2 = animator;
                    Animator access$502 = BottomAppBar.access$502(this.this$0, (Animator) null);
                }
            };
            this.attachAnimator.addListener(animatorListener);
            this.attachAnimator.start();
        }
    }

    private void createCradleShapeAnimation(boolean z, List<Animator> list) {
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
        boolean showCradle = z;
        List<Animator> animators = list;
        if (showCradle) {
            this.topEdgeTreatment.setHorizontalOffset(getFabTranslationX());
        }
        float[] fArr = new float[2];
        fArr[0] = this.materialShapeDrawable.getInterpolation();
        float[] fArr2 = fArr;
        float[] fArr3 = fArr2;
        fArr2[1] = showCradle ? 1.0f : 0.0f;
        ValueAnimator animator = ValueAnimator.ofFloat(fArr3);
        new ValueAnimator.AnimatorUpdateListener(this) {
            final /* synthetic */ BottomAppBar this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationUpdate(ValueAnimator animation) {
                this.this$0.materialShapeDrawable.setInterpolation(((Float) animation.getAnimatedValue()).floatValue());
            }
        };
        animator.addUpdateListener(animatorUpdateListener);
        ValueAnimator duration = animator.setDuration(ANIMATION_DURATION);
        boolean add = animators.add(animator);
    }

    private void createFabTranslationYAnimation(boolean z, List<Animator> list) {
        boolean targetAttached = z;
        List<Animator> animators = list;
        FloatingActionButton fab = findDependentFab();
        if (fab != null) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(fab, "translationY", new float[]{getFabTranslationY(targetAttached)});
            ObjectAnimator duration = animator.setDuration(ANIMATION_DURATION);
            boolean add = animators.add(animator);
        }
    }

    private float getFabTranslationY(boolean z) {
        Rect rect;
        boolean targetAttached = z;
        FloatingActionButton fab = findDependentFab();
        if (fab == null) {
            return 0.0f;
        }
        new Rect();
        Rect fabContentRect = rect;
        boolean contentRect = fab.getContentRect(fabContentRect);
        float fabHeight = (float) fabContentRect.height();
        if (fabHeight == 0.0f) {
            fabHeight = (float) fab.getMeasuredHeight();
        }
        float fabBottomShadow = (float) (fab.getHeight() - fabContentRect.bottom);
        float fabVerticalShadowPadding = (float) (fab.getHeight() - fabContentRect.height());
        return ((float) (-getMeasuredHeight())) + (targetAttached ? (-getCradleVerticalOffset()) + (fabHeight / 2.0f) + fabBottomShadow : fabVerticalShadowPadding - ((float) fab.getPaddingBottom()));
    }

    /* access modifiers changed from: private */
    public float getFabTranslationY() {
        return getFabTranslationY(this.fabAttached);
    }

    private int getFabTranslationX(int i) {
        int i2;
        int fabAlignmentMode2 = i;
        boolean isRtl = ViewCompat.getLayoutDirection(this) == 1;
        if (fabAlignmentMode2 == 1) {
            i2 = ((getMeasuredWidth() / 2) - this.fabOffsetEndMode) * (isRtl ? -1 : 1);
        } else {
            i2 = 0;
        }
        return i2;
    }

    private float getFabTranslationX() {
        return (float) getFabTranslationX(this.fabAlignmentMode);
    }

    @Nullable
    private ActionMenuView getActionMenuView() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof ActionMenuView) {
                return (ActionMenuView) view;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void translateActionMenuView(ActionMenuView actionMenuView, int i, boolean z) {
        ActionMenuView actionMenuView2 = actionMenuView;
        int fabAlignmentMode2 = i;
        boolean fabAttached2 = z;
        int toolbarLeftContentEnd = 0;
        boolean isRtl = ViewCompat.getLayoutDirection(this) == 1;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View view = getChildAt(i2);
            if ((view.getLayoutParams() instanceof Toolbar.LayoutParams) && (((Toolbar.LayoutParams) view.getLayoutParams()).gravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) == 8388611) {
                toolbarLeftContentEnd = Math.max(toolbarLeftContentEnd, isRtl ? view.getLeft() : view.getRight());
            }
        }
        actionMenuView2.setTranslationX((fabAlignmentMode2 != 1 || !fabAttached2) ? 0.0f : (float) (toolbarLeftContentEnd - (isRtl ? actionMenuView2.getRight() : actionMenuView2.getLeft())));
    }

    private void cancelAnimations() {
        if (this.attachAnimator != null) {
            this.attachAnimator.cancel();
        }
        if (this.menuAnimator != null) {
            this.menuAnimator.cancel();
        }
        if (this.modeAnimator != null) {
            this.modeAnimator.cancel();
        }
    }

    /* access modifiers changed from: private */
    public boolean isAnimationRunning() {
        return (this.attachAnimator != null && this.attachAnimator.isRunning()) || (this.menuAnimator != null && this.menuAnimator.isRunning()) || (this.modeAnimator != null && this.modeAnimator.isRunning());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        cancelAnimations();
        setCutoutState();
    }

    /* access modifiers changed from: private */
    public void setCutoutState() {
        this.topEdgeTreatment.setHorizontalOffset(getFabTranslationX());
        FloatingActionButton fab = findDependentFab();
        this.materialShapeDrawable.setInterpolation((!this.fabAttached || !isVisibleFab()) ? 0.0f : 1.0f);
        if (fab != null) {
            fab.setTranslationY(getFabTranslationY());
            fab.setTranslationX(getFabTranslationX());
        }
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView != null) {
            actionMenuView.setAlpha(1.0f);
            if (!isVisibleFab()) {
                translateActionMenuView(actionMenuView, 0, false);
            } else {
                translateActionMenuView(actionMenuView, this.fabAlignmentMode, this.fabAttached);
            }
        }
    }

    /* access modifiers changed from: private */
    public void addFabAnimationListeners(@NonNull FloatingActionButton floatingActionButton) {
        FloatingActionButton fab = floatingActionButton;
        removeFabAnimationListeners(fab);
        fab.addOnHideAnimationListener(this.fabAnimationListener);
        fab.addOnShowAnimationListener(this.fabAnimationListener);
    }

    private void removeFabAnimationListeners(@NonNull FloatingActionButton floatingActionButton) {
        FloatingActionButton fab = floatingActionButton;
        fab.removeOnHideAnimationListener(this.fabAnimationListener);
        fab.removeOnShowAnimationListener(this.fabAnimationListener);
    }

    public void setTitle(CharSequence title) {
    }

    public void setSubtitle(CharSequence subtitle) {
    }

    @NonNull
    public CoordinatorLayout.Behavior<BottomAppBar> getBehavior() {
        CoordinatorLayout.Behavior<BottomAppBar> behavior;
        new Behavior();
        return behavior;
    }

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {
        private final Rect fabContentRect;

        public Behavior() {
            Rect rect;
            new Rect();
            this.fabContentRect = rect;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Behavior(Context context, AttributeSet attrs) {
            super(context, attrs);
            Rect rect;
            new Rect();
            this.fabContentRect = rect;
        }

        private boolean updateFabPositionAndVisibility(FloatingActionButton floatingActionButton, BottomAppBar child) {
            FloatingActionButton fab = floatingActionButton;
            ((CoordinatorLayout.LayoutParams) fab.getLayoutParams()).anchorGravity = 17;
            child.addFabAnimationListeners(fab);
            return true;
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, int i) {
            CoordinatorLayout parent = coordinatorLayout;
            BottomAppBar child = bottomAppBar;
            int layoutDirection = i;
            FloatingActionButton fab = child.findDependentFab();
            if (fab != null) {
                boolean updateFabPositionAndVisibility = updateFabPositionAndVisibility(fab, child);
                fab.getMeasuredContentRect(this.fabContentRect);
                child.setFabDiameter(this.fabContentRect.height());
            }
            if (!child.isAnimationRunning()) {
                child.setCutoutState();
            }
            parent.onLayoutChild(child, layoutDirection);
            return super.onLayoutChild(parent, child, layoutDirection);
        }

        public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomAppBar bottomAppBar, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
            BottomAppBar child = bottomAppBar;
            return child.getHideOnScroll() && super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
        }

        /* access modifiers changed from: protected */
        public void slideUp(BottomAppBar bottomAppBar) {
            BottomAppBar child = bottomAppBar;
            super.slideUp(child);
            FloatingActionButton fab = child.findDependentFab();
            if (fab != null) {
                fab.clearAnimation();
                ViewPropertyAnimator duration = fab.animate().translationY(child.getFabTranslationY()).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setDuration(225);
            }
        }

        /* access modifiers changed from: protected */
        public void slideDown(BottomAppBar bottomAppBar) {
            BottomAppBar child = bottomAppBar;
            super.slideDown(child);
            FloatingActionButton fab = child.findDependentFab();
            if (fab != null) {
                boolean contentRect = fab.getContentRect(this.fabContentRect);
                float fabShadowPadding = (float) (fab.getMeasuredHeight() - this.fabContentRect.height());
                fab.clearAnimation();
                ViewPropertyAnimator duration = fab.animate().translationY(((float) (-fab.getPaddingBottom())) + fabShadowPadding).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setDuration(175);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = savedState;
        savedState2.fabAlignmentMode = this.fabAlignmentMode;
        savedState2.fabAttached = this.fabAttached;
        return savedState2;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable state = parcelable;
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.fabAlignmentMode = savedState.fabAlignmentMode;
        this.fabAttached = savedState.fabAttached;
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR;
        int fabAlignmentMode;
        boolean fabAttached;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SavedState(Parcelable superState) {
            super(superState);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SavedState(android.os.Parcel r7, java.lang.ClassLoader r8) {
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
                r3.fabAlignmentMode = r4
                r3 = r0
                r4 = r1
                int r4 = r4.readInt()
                if (r4 == 0) goto L_0x001d
                r4 = 1
            L_0x001a:
                r3.fabAttached = r4
                return
            L_0x001d:
                r4 = 0
                goto L_0x001a
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.bottomappbar.BottomAppBar.SavedState.<init>(android.os.Parcel, java.lang.ClassLoader):void");
        }

        public void writeToParcel(@NonNull Parcel parcel, int flags) {
            Parcel out = parcel;
            super.writeToParcel(out, flags);
            out.writeInt(this.fabAlignmentMode);
            out.writeInt(this.fabAttached ? 1 : 0);
        }

        static {
            Parcelable.Creator<SavedState> creator;
            new Parcelable.ClassLoaderCreator<SavedState>() {
                public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                    SavedState savedState;
                    new SavedState(in, loader);
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
}
