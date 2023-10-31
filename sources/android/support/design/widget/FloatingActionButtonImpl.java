package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.C0064R;
import android.support.design.animation.AnimationUtils;
import android.support.design.animation.AnimatorSetCompat;
import android.support.design.animation.ImageMatrixProperty;
import android.support.design.animation.MatrixEvaluator;
import android.support.design.animation.MotionSpec;
import android.support.design.ripple.RippleUtils;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.view.ViewCompat;
import android.util.Property;
import android.view.View;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class FloatingActionButtonImpl {
    static final int ANIM_STATE_HIDING = 1;
    static final int ANIM_STATE_NONE = 0;
    static final int ANIM_STATE_SHOWING = 2;
    static final long ELEVATION_ANIM_DELAY = 100;
    static final long ELEVATION_ANIM_DURATION = 100;
    static final TimeInterpolator ELEVATION_ANIM_INTERPOLATOR = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
    static final int[] EMPTY_STATE_SET = new int[0];
    static final int[] ENABLED_STATE_SET = {16842910};
    static final int[] FOCUSED_ENABLED_STATE_SET = {16842908, 16842910};
    private static final float HIDE_ICON_SCALE = 0.0f;
    private static final float HIDE_OPACITY = 0.0f;
    private static final float HIDE_SCALE = 0.0f;
    static final int[] HOVERED_ENABLED_STATE_SET = {16843623, 16842910};
    static final int[] HOVERED_FOCUSED_ENABLED_STATE_SET = {16843623, 16842908, 16842910};
    static final int[] PRESSED_ENABLED_STATE_SET = {16842919, 16842910};
    private static final float SHOW_ICON_SCALE = 1.0f;
    private static final float SHOW_OPACITY = 1.0f;
    private static final float SHOW_SCALE = 1.0f;
    int animState = 0;
    CircularBorderDrawable borderDrawable;
    Drawable contentBackground;
    @Nullable
    Animator currentAnimator;
    @Nullable
    private MotionSpec defaultHideMotionSpec;
    @Nullable
    private MotionSpec defaultShowMotionSpec;
    float elevation;
    private ArrayList<Animator.AnimatorListener> hideListeners;
    @Nullable
    MotionSpec hideMotionSpec;
    float hoveredFocusedTranslationZ;
    float imageMatrixScale = 1.0f;
    int maxImageSize;
    private ViewTreeObserver.OnPreDrawListener preDrawListener;
    float pressedTranslationZ;
    Drawable rippleDrawable;
    private float rotation;
    ShadowDrawableWrapper shadowDrawable;
    final ShadowViewDelegate shadowViewDelegate;
    Drawable shapeDrawable;
    private ArrayList<Animator.AnimatorListener> showListeners;
    @Nullable
    MotionSpec showMotionSpec;
    private final StateListAnimator stateListAnimator;
    private final Matrix tmpMatrix;
    private final Rect tmpRect;
    private final RectF tmpRectF1;
    private final RectF tmpRectF2;
    final VisibilityAwareImageButton view;

    interface InternalVisibilityChangedListener {
        void onHidden();

        void onShown();
    }

    FloatingActionButtonImpl(VisibilityAwareImageButton view2, ShadowViewDelegate shadowViewDelegate2) {
        Rect rect;
        RectF rectF;
        RectF rectF2;
        Matrix matrix;
        StateListAnimator stateListAnimator2;
        ShadowAnimatorImpl shadowAnimatorImpl;
        ShadowAnimatorImpl shadowAnimatorImpl2;
        ShadowAnimatorImpl shadowAnimatorImpl3;
        ShadowAnimatorImpl shadowAnimatorImpl4;
        ShadowAnimatorImpl shadowAnimatorImpl5;
        ShadowAnimatorImpl shadowAnimatorImpl6;
        new Rect();
        this.tmpRect = rect;
        new RectF();
        this.tmpRectF1 = rectF;
        new RectF();
        this.tmpRectF2 = rectF2;
        new Matrix();
        this.tmpMatrix = matrix;
        this.view = view2;
        this.shadowViewDelegate = shadowViewDelegate2;
        new StateListAnimator();
        this.stateListAnimator = stateListAnimator2;
        StateListAnimator stateListAnimator3 = this.stateListAnimator;
        int[] iArr = PRESSED_ENABLED_STATE_SET;
        new ElevateToPressedTranslationZAnimation(this);
        stateListAnimator3.addState(iArr, createElevationAnimator(shadowAnimatorImpl));
        StateListAnimator stateListAnimator4 = this.stateListAnimator;
        int[] iArr2 = HOVERED_FOCUSED_ENABLED_STATE_SET;
        new ElevateToHoveredFocusedTranslationZAnimation(this);
        stateListAnimator4.addState(iArr2, createElevationAnimator(shadowAnimatorImpl2));
        StateListAnimator stateListAnimator5 = this.stateListAnimator;
        int[] iArr3 = FOCUSED_ENABLED_STATE_SET;
        new ElevateToHoveredFocusedTranslationZAnimation(this);
        stateListAnimator5.addState(iArr3, createElevationAnimator(shadowAnimatorImpl3));
        StateListAnimator stateListAnimator6 = this.stateListAnimator;
        int[] iArr4 = HOVERED_ENABLED_STATE_SET;
        new ElevateToHoveredFocusedTranslationZAnimation(this);
        stateListAnimator6.addState(iArr4, createElevationAnimator(shadowAnimatorImpl4));
        StateListAnimator stateListAnimator7 = this.stateListAnimator;
        int[] iArr5 = ENABLED_STATE_SET;
        new ResetElevationAnimation(this);
        stateListAnimator7.addState(iArr5, createElevationAnimator(shadowAnimatorImpl5));
        StateListAnimator stateListAnimator8 = this.stateListAnimator;
        int[] iArr6 = EMPTY_STATE_SET;
        new DisabledElevationAnimation(this);
        stateListAnimator8.addState(iArr6, createElevationAnimator(shadowAnimatorImpl6));
        this.rotation = this.view.getRotation();
    }

    /* access modifiers changed from: package-private */
    public void setBackgroundDrawable(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i) {
        Drawable[] layers;
        Drawable drawable;
        ShadowDrawableWrapper shadowDrawableWrapper;
        ColorStateList backgroundTint = colorStateList;
        PorterDuff.Mode backgroundTintMode = mode;
        ColorStateList rippleColor = colorStateList2;
        int borderWidth = i;
        this.shapeDrawable = DrawableCompat.wrap(createShapeDrawable());
        DrawableCompat.setTintList(this.shapeDrawable, backgroundTint);
        if (backgroundTintMode != null) {
            DrawableCompat.setTintMode(this.shapeDrawable, backgroundTintMode);
        }
        this.rippleDrawable = DrawableCompat.wrap(createShapeDrawable());
        DrawableCompat.setTintList(this.rippleDrawable, RippleUtils.convertToRippleDrawableColor(rippleColor));
        if (borderWidth > 0) {
            this.borderDrawable = createBorderDrawable(borderWidth, backgroundTint);
            Drawable[] drawableArr = new Drawable[3];
            drawableArr[0] = this.borderDrawable;
            Drawable[] drawableArr2 = drawableArr;
            drawableArr2[1] = this.shapeDrawable;
            Drawable[] drawableArr3 = drawableArr2;
            drawableArr3[2] = this.rippleDrawable;
            layers = drawableArr3;
        } else {
            this.borderDrawable = null;
            Drawable[] drawableArr4 = new Drawable[2];
            drawableArr4[0] = this.shapeDrawable;
            Drawable[] drawableArr5 = drawableArr4;
            drawableArr5[1] = this.rippleDrawable;
            layers = drawableArr5;
        }
        new LayerDrawable(layers);
        this.contentBackground = drawable;
        new ShadowDrawableWrapper(this.view.getContext(), this.contentBackground, this.shadowViewDelegate.getRadius(), this.elevation, this.elevation + this.pressedTranslationZ);
        this.shadowDrawable = shadowDrawableWrapper;
        this.shadowDrawable.setAddPaddingForCorners(false);
        this.shadowViewDelegate.setBackgroundDrawable(this.shadowDrawable);
    }

    /* access modifiers changed from: package-private */
    public void setBackgroundTintList(ColorStateList colorStateList) {
        ColorStateList tint = colorStateList;
        if (this.shapeDrawable != null) {
            DrawableCompat.setTintList(this.shapeDrawable, tint);
        }
        if (this.borderDrawable != null) {
            this.borderDrawable.setBorderTint(tint);
        }
    }

    /* access modifiers changed from: package-private */
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        PorterDuff.Mode tintMode = mode;
        if (this.shapeDrawable != null) {
            DrawableCompat.setTintMode(this.shapeDrawable, tintMode);
        }
    }

    /* access modifiers changed from: package-private */
    public void setRippleColor(ColorStateList colorStateList) {
        ColorStateList rippleColor = colorStateList;
        if (this.rippleDrawable != null) {
            DrawableCompat.setTintList(this.rippleDrawable, RippleUtils.convertToRippleDrawableColor(rippleColor));
        }
    }

    /* access modifiers changed from: package-private */
    public final void setElevation(float f) {
        float elevation2 = f;
        if (this.elevation != elevation2) {
            this.elevation = elevation2;
            onElevationsChanged(this.elevation, this.hoveredFocusedTranslationZ, this.pressedTranslationZ);
        }
    }

    /* access modifiers changed from: package-private */
    public float getElevation() {
        return this.elevation;
    }

    /* access modifiers changed from: package-private */
    public float getHoveredFocusedTranslationZ() {
        return this.hoveredFocusedTranslationZ;
    }

    /* access modifiers changed from: package-private */
    public float getPressedTranslationZ() {
        return this.pressedTranslationZ;
    }

    /* access modifiers changed from: package-private */
    public final void setHoveredFocusedTranslationZ(float f) {
        float translationZ = f;
        if (this.hoveredFocusedTranslationZ != translationZ) {
            this.hoveredFocusedTranslationZ = translationZ;
            onElevationsChanged(this.elevation, this.hoveredFocusedTranslationZ, this.pressedTranslationZ);
        }
    }

    /* access modifiers changed from: package-private */
    public final void setPressedTranslationZ(float f) {
        float translationZ = f;
        if (this.pressedTranslationZ != translationZ) {
            this.pressedTranslationZ = translationZ;
            onElevationsChanged(this.elevation, this.hoveredFocusedTranslationZ, this.pressedTranslationZ);
        }
    }

    /* access modifiers changed from: package-private */
    public final void setMaxImageSize(int i) {
        int maxImageSize2 = i;
        if (this.maxImageSize != maxImageSize2) {
            this.maxImageSize = maxImageSize2;
            updateImageMatrixScale();
        }
    }

    /* access modifiers changed from: package-private */
    public final void updateImageMatrixScale() {
        setImageMatrixScale(this.imageMatrixScale);
    }

    /* access modifiers changed from: package-private */
    public final void setImageMatrixScale(float f) {
        float scale = f;
        this.imageMatrixScale = scale;
        Matrix matrix = this.tmpMatrix;
        calculateImageMatrixFromScale(scale, matrix);
        this.view.setImageMatrix(matrix);
    }

    private void calculateImageMatrixFromScale(float f, Matrix matrix) {
        float scale = f;
        Matrix matrix2 = matrix;
        matrix2.reset();
        Drawable drawable = this.view.getDrawable();
        if (drawable != null && this.maxImageSize != 0) {
            RectF drawableBounds = this.tmpRectF1;
            RectF imageBounds = this.tmpRectF2;
            drawableBounds.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
            imageBounds.set(0.0f, 0.0f, (float) this.maxImageSize, (float) this.maxImageSize);
            boolean rectToRect = matrix2.setRectToRect(drawableBounds, imageBounds, Matrix.ScaleToFit.CENTER);
            boolean postScale = matrix2.postScale(scale, scale, ((float) this.maxImageSize) / 2.0f, ((float) this.maxImageSize) / 2.0f);
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final MotionSpec getShowMotionSpec() {
        return this.showMotionSpec;
    }

    /* access modifiers changed from: package-private */
    public final void setShowMotionSpec(@Nullable MotionSpec spec) {
        MotionSpec motionSpec = spec;
        this.showMotionSpec = motionSpec;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final MotionSpec getHideMotionSpec() {
        return this.hideMotionSpec;
    }

    /* access modifiers changed from: package-private */
    public final void setHideMotionSpec(@Nullable MotionSpec spec) {
        MotionSpec motionSpec = spec;
        this.hideMotionSpec = motionSpec;
    }

    /* access modifiers changed from: package-private */
    public void onElevationsChanged(float f, float f2, float f3) {
        float elevation2 = f;
        float f4 = f2;
        float f5 = f3;
        if (this.shadowDrawable != null) {
            this.shadowDrawable.setShadowSize(elevation2, elevation2 + this.pressedTranslationZ);
            updatePadding();
        }
    }

    /* access modifiers changed from: package-private */
    public void onDrawableStateChanged(int[] state) {
        this.stateListAnimator.setState(state);
    }

    /* access modifiers changed from: package-private */
    public void jumpDrawableToCurrentState() {
        this.stateListAnimator.jumpToCurrentState();
    }

    /* access modifiers changed from: package-private */
    public void addOnShowAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        ArrayList<Animator.AnimatorListener> arrayList;
        Animator.AnimatorListener listener = animatorListener;
        if (this.showListeners == null) {
            new ArrayList<>();
            this.showListeners = arrayList;
        }
        boolean add = this.showListeners.add(listener);
    }

    /* access modifiers changed from: package-private */
    public void removeOnShowAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        Animator.AnimatorListener listener = animatorListener;
        if (this.showListeners != null) {
            boolean remove = this.showListeners.remove(listener);
        }
    }

    public void addOnHideAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        ArrayList<Animator.AnimatorListener> arrayList;
        Animator.AnimatorListener listener = animatorListener;
        if (this.hideListeners == null) {
            new ArrayList<>();
            this.hideListeners = arrayList;
        }
        boolean add = this.hideListeners.add(listener);
    }

    public void removeOnHideAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        Animator.AnimatorListener listener = animatorListener;
        if (this.hideListeners != null) {
            boolean remove = this.hideListeners.remove(listener);
        }
    }

    /* access modifiers changed from: package-private */
    public void hide(@Nullable InternalVisibilityChangedListener internalVisibilityChangedListener, boolean z) {
        MotionSpec defaultHideMotionSpec2;
        Animator.AnimatorListener animatorListener;
        InternalVisibilityChangedListener listener = internalVisibilityChangedListener;
        boolean fromUser = z;
        if (!isOrWillBeHidden()) {
            if (this.currentAnimator != null) {
                this.currentAnimator.cancel();
            }
            if (shouldAnimateVisibilityChange()) {
                if (this.hideMotionSpec != null) {
                    defaultHideMotionSpec2 = this.hideMotionSpec;
                } else {
                    defaultHideMotionSpec2 = getDefaultHideMotionSpec();
                }
                AnimatorSet set = createAnimator(defaultHideMotionSpec2, 0.0f, 0.0f, 0.0f);
                final boolean z2 = fromUser;
                final InternalVisibilityChangedListener internalVisibilityChangedListener2 = listener;
                new AnimatorListenerAdapter(this) {
                    private boolean cancelled;
                    final /* synthetic */ FloatingActionButtonImpl this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onAnimationStart(Animator animation) {
                        this.this$0.view.internalSetVisibility(0, z2);
                        this.this$0.animState = 1;
                        this.this$0.currentAnimator = animation;
                        this.cancelled = false;
                    }

                    public void onAnimationCancel(Animator animator) {
                        Animator animator2 = animator;
                        this.cancelled = true;
                    }

                    public void onAnimationEnd(Animator animator) {
                        Animator animator2 = animator;
                        this.this$0.animState = 0;
                        this.this$0.currentAnimator = null;
                        if (!this.cancelled) {
                            this.this$0.view.internalSetVisibility(z2 ? 8 : 4, z2);
                            if (internalVisibilityChangedListener2 != null) {
                                internalVisibilityChangedListener2.onHidden();
                            }
                        }
                    }
                };
                set.addListener(animatorListener);
                if (this.hideListeners != null) {
                    Iterator<Animator.AnimatorListener> it = this.hideListeners.iterator();
                    while (it.hasNext()) {
                        set.addListener(it.next());
                    }
                }
                set.start();
                return;
            }
            this.view.internalSetVisibility(fromUser ? 8 : 4, fromUser);
            if (listener != null) {
                listener.onHidden();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void show(@Nullable InternalVisibilityChangedListener internalVisibilityChangedListener, boolean z) {
        MotionSpec defaultShowMotionSpec2;
        Animator.AnimatorListener animatorListener;
        InternalVisibilityChangedListener listener = internalVisibilityChangedListener;
        boolean fromUser = z;
        if (!isOrWillBeShown()) {
            if (this.currentAnimator != null) {
                this.currentAnimator.cancel();
            }
            if (shouldAnimateVisibilityChange()) {
                if (this.view.getVisibility() != 0) {
                    this.view.setAlpha(0.0f);
                    this.view.setScaleY(0.0f);
                    this.view.setScaleX(0.0f);
                    setImageMatrixScale(0.0f);
                }
                if (this.showMotionSpec != null) {
                    defaultShowMotionSpec2 = this.showMotionSpec;
                } else {
                    defaultShowMotionSpec2 = getDefaultShowMotionSpec();
                }
                AnimatorSet set = createAnimator(defaultShowMotionSpec2, 1.0f, 1.0f, 1.0f);
                final boolean z2 = fromUser;
                final InternalVisibilityChangedListener internalVisibilityChangedListener2 = listener;
                new AnimatorListenerAdapter(this) {
                    final /* synthetic */ FloatingActionButtonImpl this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onAnimationStart(Animator animation) {
                        this.this$0.view.internalSetVisibility(0, z2);
                        this.this$0.animState = 2;
                        this.this$0.currentAnimator = animation;
                    }

                    public void onAnimationEnd(Animator animator) {
                        Animator animator2 = animator;
                        this.this$0.animState = 0;
                        this.this$0.currentAnimator = null;
                        if (internalVisibilityChangedListener2 != null) {
                            internalVisibilityChangedListener2.onShown();
                        }
                    }
                };
                set.addListener(animatorListener);
                if (this.showListeners != null) {
                    Iterator<Animator.AnimatorListener> it = this.showListeners.iterator();
                    while (it.hasNext()) {
                        set.addListener(it.next());
                    }
                }
                set.start();
                return;
            }
            this.view.internalSetVisibility(0, fromUser);
            this.view.setAlpha(1.0f);
            this.view.setScaleY(1.0f);
            this.view.setScaleX(1.0f);
            setImageMatrixScale(1.0f);
            if (listener != null) {
                listener.onShown();
            }
        }
    }

    private MotionSpec getDefaultShowMotionSpec() {
        if (this.defaultShowMotionSpec == null) {
            this.defaultShowMotionSpec = MotionSpec.createFromResource(this.view.getContext(), C0064R.animator.design_fab_show_motion_spec);
        }
        return this.defaultShowMotionSpec;
    }

    private MotionSpec getDefaultHideMotionSpec() {
        if (this.defaultHideMotionSpec == null) {
            this.defaultHideMotionSpec = MotionSpec.createFromResource(this.view.getContext(), C0064R.animator.design_fab_hide_motion_spec);
        }
        return this.defaultHideMotionSpec;
    }

    @NonNull
    private AnimatorSet createAnimator(@NonNull MotionSpec motionSpec, float opacity, float f, float iconScale) {
        List<Animator> list;
        Property property;
        TypeEvaluator typeEvaluator;
        Matrix matrix;
        AnimatorSet animatorSet;
        MotionSpec spec = motionSpec;
        float scale = f;
        new ArrayList<>();
        List<Animator> animators = list;
        Animator animator = ObjectAnimator.ofFloat(this.view, View.ALPHA, new float[]{opacity});
        spec.getTiming("opacity").apply(animator);
        boolean add = animators.add(animator);
        Animator animator2 = ObjectAnimator.ofFloat(this.view, View.SCALE_X, new float[]{scale});
        spec.getTiming("scale").apply(animator2);
        boolean add2 = animators.add(animator2);
        Animator animator3 = ObjectAnimator.ofFloat(this.view, View.SCALE_Y, new float[]{scale});
        spec.getTiming("scale").apply(animator3);
        boolean add3 = animators.add(animator3);
        calculateImageMatrixFromScale(iconScale, this.tmpMatrix);
        new ImageMatrixProperty();
        new MatrixEvaluator();
        new Matrix(this.tmpMatrix);
        Animator animator4 = ObjectAnimator.ofObject(this.view, property, typeEvaluator, new Matrix[]{matrix});
        spec.getTiming("iconScale").apply(animator4);
        boolean add4 = animators.add(animator4);
        new AnimatorSet();
        AnimatorSet set = animatorSet;
        AnimatorSetCompat.playTogether(set, animators);
        return set;
    }

    /* access modifiers changed from: package-private */
    public final Drawable getContentBackground() {
        return this.contentBackground;
    }

    /* access modifiers changed from: package-private */
    public void onCompatShadowChanged() {
    }

    /* access modifiers changed from: package-private */
    public final void updatePadding() {
        Rect rect = this.tmpRect;
        getPadding(rect);
        onPaddingUpdated(rect);
        this.shadowViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    /* access modifiers changed from: package-private */
    public void getPadding(Rect rect) {
        boolean padding = this.shadowDrawable.getPadding(rect);
    }

    /* access modifiers changed from: package-private */
    public void onPaddingUpdated(Rect padding) {
    }

    /* access modifiers changed from: package-private */
    public void onAttachedToWindow() {
        if (requirePreDrawListener()) {
            ensurePreDrawListener();
            this.view.getViewTreeObserver().addOnPreDrawListener(this.preDrawListener);
        }
    }

    /* access modifiers changed from: package-private */
    public void onDetachedFromWindow() {
        if (this.preDrawListener != null) {
            this.view.getViewTreeObserver().removeOnPreDrawListener(this.preDrawListener);
            this.preDrawListener = null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean requirePreDrawListener() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public CircularBorderDrawable createBorderDrawable(int borderWidth, ColorStateList backgroundTint) {
        Context context = this.view.getContext();
        CircularBorderDrawable borderDrawable2 = newCircularDrawable();
        borderDrawable2.setGradientColors(ContextCompat.getColor(context, C0064R.color.design_fab_stroke_top_outer_color), ContextCompat.getColor(context, C0064R.color.design_fab_stroke_top_inner_color), ContextCompat.getColor(context, C0064R.color.design_fab_stroke_end_inner_color), ContextCompat.getColor(context, C0064R.color.design_fab_stroke_end_outer_color));
        borderDrawable2.setBorderWidth((float) borderWidth);
        borderDrawable2.setBorderTint(backgroundTint);
        return borderDrawable2;
    }

    /* access modifiers changed from: package-private */
    public CircularBorderDrawable newCircularDrawable() {
        CircularBorderDrawable circularBorderDrawable;
        new CircularBorderDrawable();
        return circularBorderDrawable;
    }

    /* access modifiers changed from: package-private */
    public void onPreDraw() {
        float rotation2 = this.view.getRotation();
        if (this.rotation != rotation2) {
            this.rotation = rotation2;
            updateFromViewRotation();
        }
    }

    private void ensurePreDrawListener() {
        ViewTreeObserver.OnPreDrawListener onPreDrawListener;
        if (this.preDrawListener == null) {
            new ViewTreeObserver.OnPreDrawListener(this) {
                final /* synthetic */ FloatingActionButtonImpl this$0;

                {
                    this.this$0 = this$0;
                }

                public boolean onPreDraw() {
                    this.this$0.onPreDraw();
                    return true;
                }
            };
            this.preDrawListener = onPreDrawListener;
        }
    }

    /* access modifiers changed from: package-private */
    public GradientDrawable createShapeDrawable() {
        GradientDrawable d = newGradientDrawableForShape();
        d.setShape(1);
        d.setColor(-1);
        return d;
    }

    /* access modifiers changed from: package-private */
    public GradientDrawable newGradientDrawableForShape() {
        GradientDrawable gradientDrawable;
        new GradientDrawable();
        return gradientDrawable;
    }

    /* access modifiers changed from: package-private */
    public boolean isOrWillBeShown() {
        if (this.view.getVisibility() != 0) {
            return this.animState == 2;
        }
        return this.animState != 1;
    }

    /* access modifiers changed from: package-private */
    public boolean isOrWillBeHidden() {
        if (this.view.getVisibility() == 0) {
            return this.animState == 1;
        }
        return this.animState != 2;
    }

    private ValueAnimator createElevationAnimator(@NonNull ShadowAnimatorImpl shadowAnimatorImpl) {
        ValueAnimator valueAnimator;
        ShadowAnimatorImpl impl = shadowAnimatorImpl;
        new ValueAnimator();
        ValueAnimator animator = valueAnimator;
        animator.setInterpolator(ELEVATION_ANIM_INTERPOLATOR);
        ValueAnimator duration = animator.setDuration(100);
        animator.addListener(impl);
        animator.addUpdateListener(impl);
        animator.setFloatValues(new float[]{0.0f, 1.0f});
        return animator;
    }

    private abstract class ShadowAnimatorImpl extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        private float shadowSizeEnd;
        private float shadowSizeStart;
        final /* synthetic */ FloatingActionButtonImpl this$0;
        private boolean validValues;

        /* access modifiers changed from: protected */
        public abstract float getTargetShadowSize();

        private ShadowAnimatorImpl(FloatingActionButtonImpl floatingActionButtonImpl) {
            this.this$0 = floatingActionButtonImpl;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ ShadowAnimatorImpl(FloatingActionButtonImpl x0, C01331 r7) {
            this(x0);
            C01331 r2 = r7;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ValueAnimator animator = valueAnimator;
            if (!this.validValues) {
                this.shadowSizeStart = this.this$0.shadowDrawable.getShadowSize();
                this.shadowSizeEnd = getTargetShadowSize();
                this.validValues = true;
            }
            this.this$0.shadowDrawable.setShadowSize(this.shadowSizeStart + ((this.shadowSizeEnd - this.shadowSizeStart) * animator.getAnimatedFraction()));
        }

        public void onAnimationEnd(Animator animator) {
            Animator animator2 = animator;
            this.this$0.shadowDrawable.setShadowSize(this.shadowSizeEnd);
            this.validValues = false;
        }
    }

    private class ResetElevationAnimation extends ShadowAnimatorImpl {
        final /* synthetic */ FloatingActionButtonImpl this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        ResetElevationAnimation(android.support.design.widget.FloatingActionButtonImpl r6) {
            /*
                r5 = this;
                r0 = r5
                r1 = r6
                r2 = r0
                r3 = r1
                r2.this$0 = r3
                r2 = r0
                r3 = r1
                r4 = 0
                r2.<init>(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.FloatingActionButtonImpl.ResetElevationAnimation.<init>(android.support.design.widget.FloatingActionButtonImpl):void");
        }

        /* access modifiers changed from: protected */
        public float getTargetShadowSize() {
            return this.this$0.elevation;
        }
    }

    private class ElevateToHoveredFocusedTranslationZAnimation extends ShadowAnimatorImpl {
        final /* synthetic */ FloatingActionButtonImpl this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        ElevateToHoveredFocusedTranslationZAnimation(android.support.design.widget.FloatingActionButtonImpl r6) {
            /*
                r5 = this;
                r0 = r5
                r1 = r6
                r2 = r0
                r3 = r1
                r2.this$0 = r3
                r2 = r0
                r3 = r1
                r4 = 0
                r2.<init>(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.FloatingActionButtonImpl.ElevateToHoveredFocusedTranslationZAnimation.<init>(android.support.design.widget.FloatingActionButtonImpl):void");
        }

        /* access modifiers changed from: protected */
        public float getTargetShadowSize() {
            return this.this$0.elevation + this.this$0.hoveredFocusedTranslationZ;
        }
    }

    private class ElevateToPressedTranslationZAnimation extends ShadowAnimatorImpl {
        final /* synthetic */ FloatingActionButtonImpl this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        ElevateToPressedTranslationZAnimation(android.support.design.widget.FloatingActionButtonImpl r6) {
            /*
                r5 = this;
                r0 = r5
                r1 = r6
                r2 = r0
                r3 = r1
                r2.this$0 = r3
                r2 = r0
                r3 = r1
                r4 = 0
                r2.<init>(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.FloatingActionButtonImpl.ElevateToPressedTranslationZAnimation.<init>(android.support.design.widget.FloatingActionButtonImpl):void");
        }

        /* access modifiers changed from: protected */
        public float getTargetShadowSize() {
            return this.this$0.elevation + this.this$0.pressedTranslationZ;
        }
    }

    private class DisabledElevationAnimation extends ShadowAnimatorImpl {
        final /* synthetic */ FloatingActionButtonImpl this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        DisabledElevationAnimation(android.support.design.widget.FloatingActionButtonImpl r6) {
            /*
                r5 = this;
                r0 = r5
                r1 = r6
                r2 = r0
                r3 = r1
                r2.this$0 = r3
                r2 = r0
                r3 = r1
                r4 = 0
                r2.<init>(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.FloatingActionButtonImpl.DisabledElevationAnimation.<init>(android.support.design.widget.FloatingActionButtonImpl):void");
        }

        /* access modifiers changed from: protected */
        public float getTargetShadowSize() {
            return 0.0f;
        }
    }

    private boolean shouldAnimateVisibilityChange() {
        return ViewCompat.isLaidOut(this.view) && !this.view.isInEditMode();
    }

    private void updateFromViewRotation() {
        if (Build.VERSION.SDK_INT == 19) {
            if (this.rotation % 90.0f != 0.0f) {
                if (this.view.getLayerType() != 1) {
                    this.view.setLayerType(1, (Paint) null);
                }
            } else if (this.view.getLayerType() != 0) {
                this.view.setLayerType(0, (Paint) null);
            }
        }
        if (this.shadowDrawable != null) {
            this.shadowDrawable.setRotation(-this.rotation);
        }
        if (this.borderDrawable != null) {
            this.borderDrawable.setRotation(-this.rotation);
        }
    }
}
