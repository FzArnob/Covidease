package android.support.design.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.C0064R;
import android.support.design.animation.AnimationUtils;
import android.support.design.animation.AnimatorSetCompat;
import android.support.design.animation.ArgbEvaluatorCompat;
import android.support.design.animation.ChildrenAlphaProperty;
import android.support.design.animation.DrawableAlphaProperty;
import android.support.design.animation.MotionSpec;
import android.support.design.animation.MotionTiming;
import android.support.design.animation.Positioning;
import android.support.design.circularreveal.CircularRevealCompat;
import android.support.design.circularreveal.CircularRevealHelper;
import android.support.design.circularreveal.CircularRevealWidget;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.MathUtils;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.appinventor.components.common.PropertyTypeConstants;
import java.util.ArrayList;
import java.util.List;

public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    private final int[] tmpArray = new int[2];
    private final Rect tmpRect;
    private final RectF tmpRectF1;
    private final RectF tmpRectF2;

    /* access modifiers changed from: protected */
    public abstract FabTransformationSpec onCreateMotionSpec(Context context, boolean z);

    public FabTransformationBehavior() {
        Rect rect;
        RectF rectF;
        RectF rectF2;
        new Rect();
        this.tmpRect = rect;
        new RectF();
        this.tmpRectF1 = rectF;
        new RectF();
        this.tmpRectF2 = rectF2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FabTransformationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        Rect rect;
        RectF rectF;
        RectF rectF2;
        new Rect();
        this.tmpRect = rect;
        new RectF();
        this.tmpRectF1 = rectF;
        new RectF();
        this.tmpRectF2 = rectF2;
    }

    @CallSuper
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        Throwable th;
        CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
        View child = view;
        View dependency = view2;
        if (child.getVisibility() == 8) {
            Throwable th2 = th;
            new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
            throw th2;
        } else if (!(dependency instanceof FloatingActionButton)) {
            return false;
        } else {
            int expandedComponentIdHint = ((FloatingActionButton) dependency).getExpandedComponentIdHint();
            return expandedComponentIdHint == 0 || expandedComponentIdHint == child.getId();
        }
    }

    @CallSuper
    public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams layoutParams) {
        CoordinatorLayout.LayoutParams lp = layoutParams;
        if (lp.dodgeInsetEdges == 0) {
            lp.dodgeInsetEdges = 80;
        }
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AnimatorSet onCreateExpandedStateChangeAnimation(View view, View view2, boolean z, boolean z2) {
        List<Animator> list;
        List<Animator.AnimatorListener> list2;
        AnimatorSet animatorSet;
        Animator.AnimatorListener animatorListener;
        View dependency = view;
        View child = view2;
        boolean expanded = z;
        boolean isAnimating = z2;
        FabTransformationSpec spec = onCreateMotionSpec(child.getContext(), expanded);
        new ArrayList<>();
        List<Animator> animations = list;
        new ArrayList<>();
        List<Animator.AnimatorListener> listeners = list2;
        if (Build.VERSION.SDK_INT >= 21) {
            createElevationAnimation(dependency, child, expanded, isAnimating, spec, animations, listeners);
        }
        RectF childBounds = this.tmpRectF1;
        createTranslationAnimation(dependency, child, expanded, isAnimating, spec, animations, listeners, childBounds);
        float childWidth = childBounds.width();
        float childHeight = childBounds.height();
        createIconFadeAnimation(dependency, child, expanded, isAnimating, spec, animations, listeners);
        createExpansionAnimation(dependency, child, expanded, isAnimating, spec, childWidth, childHeight, animations, listeners);
        createColorAnimation(dependency, child, expanded, isAnimating, spec, animations, listeners);
        createChildrenFadeAnimation(dependency, child, expanded, isAnimating, spec, animations, listeners);
        new AnimatorSet();
        AnimatorSet set = animatorSet;
        AnimatorSetCompat.playTogether(set, animations);
        final boolean z3 = expanded;
        final View view3 = child;
        final View view4 = dependency;
        new AnimatorListenerAdapter(this) {
            final /* synthetic */ FabTransformationBehavior this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationStart(Animator animator) {
                Animator animator2 = animator;
                if (z3) {
                    view3.setVisibility(0);
                    view4.setAlpha(0.0f);
                    view4.setVisibility(4);
                }
            }

            public void onAnimationEnd(Animator animator) {
                Animator animator2 = animator;
                if (!z3) {
                    view3.setVisibility(4);
                    view4.setAlpha(1.0f);
                    view4.setVisibility(0);
                }
            }
        };
        set.addListener(animatorListener);
        int count = listeners.size();
        for (int i = 0; i < count; i++) {
            set.addListener(listeners.get(i));
        }
        return set;
    }

    @TargetApi(21)
    private void createElevationAnimation(View dependency, View view, boolean expanded, boolean z, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<Animator.AnimatorListener> list2) {
        Animator animator;
        View child = view;
        boolean currentlyAnimating = z;
        FabTransformationSpec spec = fabTransformationSpec;
        List<Animator> animations = list;
        List<Animator.AnimatorListener> list3 = list2;
        float translationZ = ViewCompat.getElevation(child) - ViewCompat.getElevation(dependency);
        if (expanded) {
            if (!currentlyAnimating) {
                child.setTranslationZ(-translationZ);
            }
            animator = ObjectAnimator.ofFloat(child, View.TRANSLATION_Z, new float[]{0.0f});
        } else {
            animator = ObjectAnimator.ofFloat(child, View.TRANSLATION_Z, new float[]{-translationZ});
        }
        spec.timings.getTiming("elevation").apply(animator);
        boolean add = animations.add(animator);
    }

    private void createTranslationAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<Animator.AnimatorListener> list2, RectF rectF) {
        MotionTiming translationYTiming;
        MotionTiming translationXTiming;
        ValueAnimator translationXAnimator;
        ValueAnimator translationYAnimator;
        View dependency = view;
        View child = view2;
        boolean expanded = z;
        boolean currentlyAnimating = z2;
        FabTransformationSpec spec = fabTransformationSpec;
        List<Animator> animations = list;
        List<Animator.AnimatorListener> list3 = list2;
        RectF childBounds = rectF;
        float translationX = calculateTranslationX(dependency, child, spec.positioning);
        float translationY = calculateTranslationY(dependency, child, spec.positioning);
        if (translationX == 0.0f || translationY == 0.0f) {
            translationXTiming = spec.timings.getTiming("translationXLinear");
            translationYTiming = spec.timings.getTiming("translationYLinear");
        } else if ((!expanded || translationY >= 0.0f) && (expanded || translationY <= 0.0f)) {
            translationXTiming = spec.timings.getTiming("translationXCurveDownwards");
            translationYTiming = spec.timings.getTiming("translationYCurveDownwards");
        } else {
            translationXTiming = spec.timings.getTiming("translationXCurveUpwards");
            translationYTiming = spec.timings.getTiming("translationYCurveUpwards");
        }
        if (expanded) {
            if (!currentlyAnimating) {
                child.setTranslationX(-translationX);
                child.setTranslationY(-translationY);
            }
            translationXAnimator = ObjectAnimator.ofFloat(child, View.TRANSLATION_X, new float[]{0.0f});
            translationYAnimator = ObjectAnimator.ofFloat(child, View.TRANSLATION_Y, new float[]{0.0f});
            calculateChildVisibleBoundsAtEndOfExpansion(child, spec, translationXTiming, translationYTiming, -translationX, -translationY, 0.0f, 0.0f, childBounds);
        } else {
            translationXAnimator = ObjectAnimator.ofFloat(child, View.TRANSLATION_X, new float[]{-translationX});
            translationYAnimator = ObjectAnimator.ofFloat(child, View.TRANSLATION_Y, new float[]{-translationY});
        }
        translationXTiming.apply(translationXAnimator);
        translationYTiming.apply(translationYAnimator);
        boolean add = animations.add(translationXAnimator);
        boolean add2 = animations.add(translationYAnimator);
    }

    private void createIconFadeAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator animator;
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
        Object obj;
        View dependency = view;
        View child = view2;
        boolean expanded = z;
        boolean currentlyAnimating = z2;
        FabTransformationSpec spec = fabTransformationSpec;
        List<Animator> animations = list;
        List<Animator.AnimatorListener> listeners = list2;
        if ((child instanceof CircularRevealWidget) && (dependency instanceof ImageView)) {
            CircularRevealWidget circularRevealChild = (CircularRevealWidget) child;
            Drawable icon = ((ImageView) dependency).getDrawable();
            if (icon != null) {
                Drawable mutate = icon.mutate();
                if (expanded) {
                    if (!currentlyAnimating) {
                        icon.setAlpha(255);
                    }
                    animator = ObjectAnimator.ofInt(icon, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, new int[]{0});
                } else {
                    animator = ObjectAnimator.ofInt(icon, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, new int[]{255});
                }
                final View view3 = child;
                new ValueAnimator.AnimatorUpdateListener(this) {
                    final /* synthetic */ FabTransformationBehavior this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        ValueAnimator valueAnimator2 = valueAnimator;
                        view3.invalidate();
                    }
                };
                animator.addUpdateListener(animatorUpdateListener);
                spec.timings.getTiming("iconFade").apply(animator);
                boolean add = animations.add(animator);
                final CircularRevealWidget circularRevealWidget = circularRevealChild;
                final Drawable drawable = icon;
                new AnimatorListenerAdapter(this) {
                    final /* synthetic */ FabTransformationBehavior this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onAnimationStart(Animator animator) {
                        Animator animator2 = animator;
                        circularRevealWidget.setCircularRevealOverlayDrawable(drawable);
                    }

                    public void onAnimationEnd(Animator animator) {
                        Animator animator2 = animator;
                        circularRevealWidget.setCircularRevealOverlayDrawable((Drawable) null);
                    }
                };
                boolean add2 = listeners.add(obj);
            }
        }
    }

    private void createExpansionAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, float f, float f2, List<Animator> list, List<Animator.AnimatorListener> list2) {
        Animator animator;
        Animator.AnimatorListener animatorListener;
        CircularRevealWidget.RevealInfo revealInfo;
        View dependency = view;
        View child = view2;
        boolean expanded = z;
        boolean currentlyAnimating = z2;
        FabTransformationSpec spec = fabTransformationSpec;
        float childWidth = f;
        float childHeight = f2;
        List<Animator> animations = list;
        List<Animator.AnimatorListener> listeners = list2;
        if (child instanceof CircularRevealWidget) {
            CircularRevealWidget circularRevealChild = (CircularRevealWidget) child;
            float revealCenterX = calculateRevealCenterX(dependency, child, spec.positioning);
            float revealCenterY = calculateRevealCenterY(dependency, child, spec.positioning);
            boolean contentRect = ((FloatingActionButton) dependency).getContentRect(this.tmpRect);
            float dependencyRadius = ((float) this.tmpRect.width()) / 2.0f;
            MotionTiming timing = spec.timings.getTiming("expansion");
            if (expanded) {
                if (!currentlyAnimating) {
                    new CircularRevealWidget.RevealInfo(revealCenterX, revealCenterY, dependencyRadius);
                    circularRevealChild.setRevealInfo(revealInfo);
                }
                float fromRadius = currentlyAnimating ? circularRevealChild.getRevealInfo().radius : dependencyRadius;
                animator = CircularRevealCompat.createCircularReveal(circularRevealChild, revealCenterX, revealCenterY, MathUtils.distanceToFurthestCorner(revealCenterX, revealCenterY, 0.0f, 0.0f, childWidth, childHeight));
                final CircularRevealWidget circularRevealWidget = circularRevealChild;
                new AnimatorListenerAdapter(this) {
                    final /* synthetic */ FabTransformationBehavior this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onAnimationEnd(Animator animator) {
                        Animator animator2 = animator;
                        CircularRevealWidget.RevealInfo revealInfo = circularRevealWidget.getRevealInfo();
                        revealInfo.radius = Float.MAX_VALUE;
                        circularRevealWidget.setRevealInfo(revealInfo);
                    }
                };
                animator.addListener(animatorListener);
                createPreFillRadialExpansion(child, timing.getDelay(), (int) revealCenterX, (int) revealCenterY, fromRadius, animations);
            } else {
                float fromRadius2 = circularRevealChild.getRevealInfo().radius;
                float toRadius = dependencyRadius;
                animator = CircularRevealCompat.createCircularReveal(circularRevealChild, revealCenterX, revealCenterY, toRadius);
                createPreFillRadialExpansion(child, timing.getDelay(), (int) revealCenterX, (int) revealCenterY, fromRadius2, animations);
                createPostFillRadialExpansion(child, timing.getDelay(), timing.getDuration(), spec.timings.getTotalDuration(), (int) revealCenterX, (int) revealCenterY, toRadius, animations);
            }
            timing.apply(animator);
            boolean add = animations.add(animator);
            boolean add2 = listeners.add(CircularRevealCompat.createCircularRevealListener(circularRevealChild));
        }
    }

    private void createColorAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator animator;
        View dependency = view;
        View child = view2;
        boolean expanded = z;
        boolean currentlyAnimating = z2;
        FabTransformationSpec spec = fabTransformationSpec;
        List<Animator> animations = list;
        List<Animator.AnimatorListener> list3 = list2;
        if (child instanceof CircularRevealWidget) {
            CircularRevealWidget circularRevealChild = (CircularRevealWidget) child;
            int tint = getBackgroundTint(dependency);
            int transparent = tint & 16777215;
            if (expanded) {
                if (!currentlyAnimating) {
                    circularRevealChild.setCircularRevealScrimColor(tint);
                }
                animator = ObjectAnimator.ofInt(circularRevealChild, CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, new int[]{transparent});
            } else {
                animator = ObjectAnimator.ofInt(circularRevealChild, CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, new int[]{tint});
            }
            animator.setEvaluator(ArgbEvaluatorCompat.getInstance());
            spec.timings.getTiming(PropertyTypeConstants.PROPERTY_TYPE_COLOR).apply(animator);
            boolean add = animations.add(animator);
        }
    }

    private void createChildrenFadeAnimation(View view, View view2, boolean z, boolean z2, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ViewGroup childContentContainer;
        Animator animator;
        View view3 = view;
        View child = view2;
        boolean expanded = z;
        boolean currentlyAnimating = z2;
        FabTransformationSpec spec = fabTransformationSpec;
        List<Animator> animations = list;
        List<Animator.AnimatorListener> list3 = list2;
        if (child instanceof ViewGroup) {
            if ((!(child instanceof CircularRevealWidget) || CircularRevealHelper.STRATEGY != 0) && (childContentContainer = calculateChildContentContainer(child)) != null) {
                if (expanded) {
                    if (!currentlyAnimating) {
                        ChildrenAlphaProperty.CHILDREN_ALPHA.set(childContentContainer, Float.valueOf(0.0f));
                    }
                    animator = ObjectAnimator.ofFloat(childContentContainer, ChildrenAlphaProperty.CHILDREN_ALPHA, new float[]{1.0f});
                } else {
                    animator = ObjectAnimator.ofFloat(childContentContainer, ChildrenAlphaProperty.CHILDREN_ALPHA, new float[]{0.0f});
                }
                spec.timings.getTiming("contentFade").apply(animator);
                boolean add = animations.add(animator);
            }
        }
    }

    private float calculateTranslationX(View dependency, View child, Positioning positioning) {
        Positioning positioning2 = positioning;
        RectF dependencyBounds = this.tmpRectF1;
        RectF childBounds = this.tmpRectF2;
        calculateWindowBounds(dependency, dependencyBounds);
        calculateWindowBounds(child, childBounds);
        float translationX = 0.0f;
        switch (positioning2.gravity & 7) {
            case 1:
                translationX = childBounds.centerX() - dependencyBounds.centerX();
                break;
            case 3:
                translationX = childBounds.left - dependencyBounds.left;
                break;
            case 5:
                translationX = childBounds.right - dependencyBounds.right;
                break;
        }
        return translationX + positioning2.xAdjustment;
    }

    private float calculateTranslationY(View dependency, View child, Positioning positioning) {
        Positioning positioning2 = positioning;
        RectF dependencyBounds = this.tmpRectF1;
        RectF childBounds = this.tmpRectF2;
        calculateWindowBounds(dependency, dependencyBounds);
        calculateWindowBounds(child, childBounds);
        float translationY = 0.0f;
        switch (positioning2.gravity & 112) {
            case 16:
                translationY = childBounds.centerY() - dependencyBounds.centerY();
                break;
            case 48:
                translationY = childBounds.top - dependencyBounds.top;
                break;
            case 80:
                translationY = childBounds.bottom - dependencyBounds.bottom;
                break;
        }
        return translationY + positioning2.yAdjustment;
    }

    private void calculateWindowBounds(View view, RectF rect) {
        View view2 = view;
        RectF windowBounds = rect;
        windowBounds.set(0.0f, 0.0f, (float) view2.getWidth(), (float) view2.getHeight());
        int[] windowLocation = this.tmpArray;
        view2.getLocationInWindow(windowLocation);
        windowBounds.offsetTo((float) windowLocation[0], (float) windowLocation[1]);
        windowBounds.offset((float) ((int) (-view2.getTranslationX())), (float) ((int) (-view2.getTranslationY())));
    }

    private float calculateRevealCenterX(View view, View view2, Positioning positioning) {
        View dependency = view;
        View child = view2;
        RectF dependencyBounds = this.tmpRectF1;
        RectF childBounds = this.tmpRectF2;
        calculateWindowBounds(dependency, dependencyBounds);
        calculateWindowBounds(child, childBounds);
        childBounds.offset(-calculateTranslationX(dependency, child, positioning), 0.0f);
        return dependencyBounds.centerX() - childBounds.left;
    }

    private float calculateRevealCenterY(View view, View view2, Positioning positioning) {
        View dependency = view;
        View child = view2;
        RectF dependencyBounds = this.tmpRectF1;
        RectF childBounds = this.tmpRectF2;
        calculateWindowBounds(dependency, dependencyBounds);
        calculateWindowBounds(child, childBounds);
        childBounds.offset(0.0f, -calculateTranslationY(dependency, child, positioning));
        return dependencyBounds.centerY() - childBounds.top;
    }

    private void calculateChildVisibleBoundsAtEndOfExpansion(View view, FabTransformationSpec fabTransformationSpec, MotionTiming translationXTiming, MotionTiming translationYTiming, float fromX, float fromY, float toX, float toY, RectF childBounds) {
        View child = view;
        FabTransformationSpec spec = fabTransformationSpec;
        float translationX = calculateValueOfAnimationAtEndOfExpansion(spec, translationXTiming, fromX, toX);
        float translationY = calculateValueOfAnimationAtEndOfExpansion(spec, translationYTiming, fromY, toY);
        Rect window = this.tmpRect;
        child.getWindowVisibleDisplayFrame(window);
        RectF windowF = this.tmpRectF1;
        windowF.set(window);
        RectF childVisibleBounds = this.tmpRectF2;
        calculateWindowBounds(child, childVisibleBounds);
        childVisibleBounds.offset(translationX, translationY);
        boolean intersect = childVisibleBounds.intersect(windowF);
        childBounds.set(childVisibleBounds);
    }

    private float calculateValueOfAnimationAtEndOfExpansion(FabTransformationSpec spec, MotionTiming motionTiming, float from, float to) {
        MotionTiming timing = motionTiming;
        long delay = timing.getDelay();
        long duration = timing.getDuration();
        MotionTiming expansionTiming = spec.timings.getTiming("expansion");
        return AnimationUtils.lerp(from, to, timing.getInterpolator().getInterpolation(((float) (((expansionTiming.getDelay() + expansionTiming.getDuration()) + 17) - delay)) / ((float) duration)));
    }

    @Nullable
    private ViewGroup calculateChildContentContainer(View view) {
        View view2 = view;
        View childContentContainer = view2.findViewById(C0064R.C0066id.mtrl_child_content_container);
        if (childContentContainer != null) {
            return toViewGroupOrNull(childContentContainer);
        }
        if (!(view2 instanceof TransformationChildLayout) && !(view2 instanceof TransformationChildCard)) {
            return toViewGroupOrNull(view2);
        }
        return toViewGroupOrNull(((ViewGroup) view2).getChildAt(0));
    }

    @Nullable
    private ViewGroup toViewGroupOrNull(View view) {
        View view2 = view;
        if (view2 instanceof ViewGroup) {
            return (ViewGroup) view2;
        }
        return null;
    }

    private int getBackgroundTint(View view) {
        View view2 = view;
        ColorStateList tintList = ViewCompat.getBackgroundTintList(view2);
        if (tintList != null) {
            return tintList.getColorForState(view2.getDrawableState(), tintList.getDefaultColor());
        }
        return 0;
    }

    private void createPreFillRadialExpansion(View view, long j, int i, int i2, float f, List<Animator> list) {
        View child = view;
        long delay = j;
        int revealCenterX = i;
        int revealCenterY = i2;
        float fromRadius = f;
        List<Animator> animations = list;
        if (Build.VERSION.SDK_INT >= 21 && delay > 0) {
            Animator animator = ViewAnimationUtils.createCircularReveal(child, revealCenterX, revealCenterY, fromRadius, fromRadius);
            animator.setStartDelay(0);
            Animator duration = animator.setDuration(delay);
            boolean add = animations.add(animator);
        }
    }

    private void createPostFillRadialExpansion(View view, long j, long j2, long j3, int i, int i2, float f, List<Animator> list) {
        View child = view;
        long delay = j;
        long duration = j2;
        long totalDuration = j3;
        int revealCenterX = i;
        int revealCenterY = i2;
        float toRadius = f;
        List<Animator> animations = list;
        if (Build.VERSION.SDK_INT >= 21 && delay + duration < totalDuration) {
            Animator animator = ViewAnimationUtils.createCircularReveal(child, revealCenterX, revealCenterY, toRadius, toRadius);
            animator.setStartDelay(delay + duration);
            Animator duration2 = animator.setDuration(totalDuration - (delay + duration));
            boolean add = animations.add(animator);
        }
    }

    protected static class FabTransformationSpec {
        public Positioning positioning;
        public MotionSpec timings;

        protected FabTransformationSpec() {
        }
    }
}
