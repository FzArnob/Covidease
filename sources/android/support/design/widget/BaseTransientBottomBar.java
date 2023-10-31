package android.support.design.widget;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.design.C0064R;
import android.support.design.animation.AnimationUtils;
import android.support.design.internal.ThemeEnforcement;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SnackbarManager;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.p000v4.view.AccessibilityDelegateCompat;
import android.support.p000v4.view.OnApplyWindowInsetsListener;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.WindowInsetsCompat;
import android.support.p000v4.view.accessibility.AccessibilityManagerCompat;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {
    static final int ANIMATION_DURATION = 250;
    static final int ANIMATION_FADE_DURATION = 180;
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    static final int MSG_DISMISS = 1;
    static final int MSG_SHOW = 0;
    private static final int[] SNACKBAR_STYLE_ATTR = {C0064R.attr.snackbarStyle};
    /* access modifiers changed from: private */
    public static final boolean USE_OFFSET_API = (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT <= 19);
    static final Handler handler;
    private final AccessibilityManager accessibilityManager;
    private Behavior behavior;
    private List<BaseCallback<B>> callbacks;
    /* access modifiers changed from: private */
    public final android.support.design.snackbar.ContentViewCallback contentViewCallback;
    private final Context context;
    private int duration;
    final SnackbarManager.Callback managerCallback;
    private final ViewGroup targetParent;
    protected final SnackbarBaseLayout view;

    @Deprecated
    public interface ContentViewCallback extends android.support.design.snackbar.ContentViewCallback {
    }

    @IntRange(from = 1)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected interface OnAttachStateChangeListener {
        void onViewAttachedToWindow(View view);

        void onViewDetachedFromWindow(View view);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected interface OnLayoutChangeListener {
        void onLayoutChange(View view, int i, int i2, int i3, int i4);
    }

    public static abstract class BaseCallback<B> {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        public @interface DismissEvent {
        }

        public BaseCallback() {
        }

        public void onDismissed(B b, int event) {
        }

        public void onShown(B b) {
        }
    }

    static {
        Handler handler2;
        Handler.Callback callback;
        new Handler.Callback() {
            public boolean handleMessage(Message message) {
                Message message2 = message;
                switch (message2.what) {
                    case 0:
                        ((BaseTransientBottomBar) message2.obj).showView();
                        return true;
                    case 1:
                        ((BaseTransientBottomBar) message2.obj).hideView(message2.arg1);
                        return true;
                    default:
                        return false;
                }
            }
        };
        new Handler(Looper.getMainLooper(), callback);
        handler = handler2;
    }

    protected BaseTransientBottomBar(@NonNull ViewGroup viewGroup, @NonNull View view2, @NonNull android.support.design.snackbar.ContentViewCallback contentViewCallback2) {
        SnackbarManager.Callback callback;
        OnApplyWindowInsetsListener onApplyWindowInsetsListener;
        AccessibilityDelegateCompat accessibilityDelegateCompat;
        Throwable th;
        Throwable th2;
        Throwable th3;
        ViewGroup parent = viewGroup;
        View content = view2;
        android.support.design.snackbar.ContentViewCallback contentViewCallback3 = contentViewCallback2;
        new SnackbarManager.Callback(this) {
            final /* synthetic */ BaseTransientBottomBar this$0;

            {
                this.this$0 = this$0;
            }

            public void show() {
                boolean sendMessage = BaseTransientBottomBar.handler.sendMessage(BaseTransientBottomBar.handler.obtainMessage(0, this.this$0));
            }

            public void dismiss(int event) {
                boolean sendMessage = BaseTransientBottomBar.handler.sendMessage(BaseTransientBottomBar.handler.obtainMessage(1, event, 0, this.this$0));
            }
        };
        this.managerCallback = callback;
        if (parent == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Transient bottom bar must have non-null parent");
            throw th4;
        } else if (content == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("Transient bottom bar must have non-null content");
            throw th5;
        } else if (contentViewCallback3 == null) {
            Throwable th6 = th;
            new IllegalArgumentException("Transient bottom bar must have non-null callback");
            throw th6;
        } else {
            this.targetParent = parent;
            this.contentViewCallback = contentViewCallback3;
            this.context = parent.getContext();
            ThemeEnforcement.checkAppCompatTheme(this.context);
            this.view = (SnackbarBaseLayout) LayoutInflater.from(this.context).inflate(getSnackbarBaseLayoutResId(), this.targetParent, false);
            this.view.addView(content);
            ViewCompat.setAccessibilityLiveRegion(this.view, 1);
            ViewCompat.setImportantForAccessibility(this.view, 1);
            ViewCompat.setFitsSystemWindows(this.view, true);
            new OnApplyWindowInsetsListener(this) {
                final /* synthetic */ BaseTransientBottomBar this$0;

                {
                    this.this$0 = this$0;
                }

                public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                    View v = view;
                    WindowInsetsCompat insets = windowInsetsCompat;
                    v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), insets.getSystemWindowInsetBottom());
                    return insets;
                }
            };
            ViewCompat.setOnApplyWindowInsetsListener(this.view, onApplyWindowInsetsListener);
            new AccessibilityDelegateCompat(this) {
                final /* synthetic */ BaseTransientBottomBar this$0;

                {
                    this.this$0 = this$0;
                }

                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    AccessibilityNodeInfoCompat info = accessibilityNodeInfoCompat;
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.addAction(1048576);
                    info.setDismissable(true);
                }

                public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                    View host = view;
                    int action = i;
                    Bundle args = bundle;
                    if (action != 1048576) {
                        return super.performAccessibilityAction(host, action, args);
                    }
                    this.this$0.dismiss();
                    return true;
                }
            };
            ViewCompat.setAccessibilityDelegate(this.view, accessibilityDelegateCompat);
            this.accessibilityManager = (AccessibilityManager) this.context.getSystemService("accessibility");
        }
    }

    /* access modifiers changed from: protected */
    @LayoutRes
    public int getSnackbarBaseLayoutResId() {
        return hasSnackbarStyleAttr() ? C0064R.layout.mtrl_layout_snackbar : C0064R.layout.design_layout_snackbar;
    }

    /* access modifiers changed from: protected */
    public boolean hasSnackbarStyleAttr() {
        TypedArray a = this.context.obtainStyledAttributes(SNACKBAR_STYLE_ATTR);
        int snackbarStyleResId = a.getResourceId(0, -1);
        a.recycle();
        return snackbarStyleResId != -1;
    }

    @NonNull
    public B setDuration(int duration2) {
        this.duration = duration2;
        return this;
    }

    public int getDuration() {
        return this.duration;
    }

    public B setBehavior(Behavior behavior2) {
        this.behavior = behavior2;
        return this;
    }

    public Behavior getBehavior() {
        return this.behavior;
    }

    @NonNull
    public Context getContext() {
        return this.context;
    }

    @NonNull
    public View getView() {
        return this.view;
    }

    public void show() {
        SnackbarManager.getInstance().show(getDuration(), this.managerCallback);
    }

    public void dismiss() {
        dispatchDismiss(3);
    }

    /* access modifiers changed from: protected */
    public void dispatchDismiss(int event) {
        SnackbarManager.getInstance().dismiss(this.managerCallback, event);
    }

    @NonNull
    public B addCallback(@NonNull BaseCallback<B> baseCallback) {
        List<BaseCallback<B>> list;
        BaseCallback<B> callback = baseCallback;
        if (callback == null) {
            return this;
        }
        if (this.callbacks == null) {
            new ArrayList();
            this.callbacks = list;
        }
        boolean add = this.callbacks.add(callback);
        return this;
    }

    @NonNull
    public B removeCallback(@NonNull BaseCallback<B> baseCallback) {
        BaseCallback<B> callback = baseCallback;
        if (callback == null) {
            return this;
        } else if (this.callbacks == null) {
            return this;
        } else {
            boolean remove = this.callbacks.remove(callback);
            return this;
        }
    }

    public boolean isShown() {
        return SnackbarManager.getInstance().isCurrent(this.managerCallback);
    }

    public boolean isShownOrQueued() {
        return SnackbarManager.getInstance().isCurrentOrNext(this.managerCallback);
    }

    /* access modifiers changed from: protected */
    public SwipeDismissBehavior<? extends View> getNewBehavior() {
        SwipeDismissBehavior<? extends View> swipeDismissBehavior;
        new Behavior();
        return swipeDismissBehavior;
    }

    /* access modifiers changed from: package-private */
    public final void showView() {
        OnAttachStateChangeListener onAttachStateChangeListener;
        OnLayoutChangeListener onLayoutChangeListener;
        SwipeDismissBehavior<? extends View> swipeDismissBehavior;
        SwipeDismissBehavior.OnDismissListener onDismissListener;
        if (this.view.getParent() == null) {
            ViewGroup.LayoutParams lp = this.view.getLayoutParams();
            if (lp instanceof CoordinatorLayout.LayoutParams) {
                CoordinatorLayout.LayoutParams clp = (CoordinatorLayout.LayoutParams) lp;
                if (this.behavior == null) {
                    swipeDismissBehavior = getNewBehavior();
                } else {
                    swipeDismissBehavior = this.behavior;
                }
                SwipeDismissBehavior<? extends View> behavior2 = swipeDismissBehavior;
                if (behavior2 instanceof Behavior) {
                    ((Behavior) behavior2).setBaseTransientBottomBar(this);
                }
                new SwipeDismissBehavior.OnDismissListener(this) {
                    final /* synthetic */ BaseTransientBottomBar this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onDismiss(View view) {
                        view.setVisibility(8);
                        this.this$0.dispatchDismiss(0);
                    }

                    public void onDragStateChanged(int state) {
                        switch (state) {
                            case 0:
                                SnackbarManager.getInstance().restoreTimeoutIfPaused(this.this$0.managerCallback);
                                return;
                            case 1:
                            case 2:
                                SnackbarManager.getInstance().pauseTimeout(this.this$0.managerCallback);
                                return;
                            default:
                                return;
                        }
                    }
                };
                behavior2.setListener(onDismissListener);
                clp.setBehavior(behavior2);
                clp.insetEdge = 80;
            }
            this.targetParent.addView(this.view);
        }
        new OnAttachStateChangeListener(this) {
            final /* synthetic */ BaseTransientBottomBar this$0;

            {
                this.this$0 = this$0;
            }

            public void onViewAttachedToWindow(View v) {
            }

            public void onViewDetachedFromWindow(View view) {
                Runnable runnable;
                View view2 = view;
                if (this.this$0.isShownOrQueued()) {
                    new Runnable(this) {
                        final /* synthetic */ C01116 this$1;

                        {
                            this.this$1 = this$1;
                        }

                        public void run() {
                            this.this$1.this$0.onViewHidden(3);
                        }
                    };
                    boolean post = BaseTransientBottomBar.handler.post(runnable);
                }
            }
        };
        this.view.setOnAttachStateChangeListener(onAttachStateChangeListener);
        if (!ViewCompat.isLaidOut(this.view)) {
            new OnLayoutChangeListener(this) {
                final /* synthetic */ BaseTransientBottomBar this$0;

                {
                    this.this$0 = this$0;
                }

                public void onLayoutChange(View view, int i, int i2, int i3, int i4) {
                    View view2 = view;
                    int i5 = i;
                    int i6 = i2;
                    int i7 = i3;
                    int i8 = i4;
                    this.this$0.view.setOnLayoutChangeListener((OnLayoutChangeListener) null);
                    if (this.this$0.shouldAnimate()) {
                        this.this$0.animateViewIn();
                    } else {
                        this.this$0.onViewShown();
                    }
                }
            };
            this.view.setOnLayoutChangeListener(onLayoutChangeListener);
        } else if (shouldAnimate()) {
            animateViewIn();
        } else {
            onViewShown();
        }
    }

    /* access modifiers changed from: package-private */
    public void animateViewIn() {
        ValueAnimator valueAnimator;
        Animator.AnimatorListener animatorListener;
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
        int translationYBottom = getTranslationYBottom();
        if (USE_OFFSET_API) {
            ViewCompat.offsetTopAndBottom(this.view, translationYBottom);
        } else {
            this.view.setTranslationY((float) translationYBottom);
        }
        new ValueAnimator();
        ValueAnimator animator = valueAnimator;
        int[] iArr = new int[2];
        iArr[0] = translationYBottom;
        int[] iArr2 = iArr;
        iArr2[1] = 0;
        animator.setIntValues(iArr2);
        animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        ValueAnimator duration2 = animator.setDuration(250);
        new AnimatorListenerAdapter(this) {
            final /* synthetic */ BaseTransientBottomBar this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationStart(Animator animator) {
                Animator animator2 = animator;
                this.this$0.contentViewCallback.animateContentIn(70, BaseTransientBottomBar.ANIMATION_FADE_DURATION);
            }

            public void onAnimationEnd(Animator animator) {
                Animator animator2 = animator;
                this.this$0.onViewShown();
            }
        };
        animator.addListener(animatorListener);
        final int i = translationYBottom;
        new ValueAnimator.AnimatorUpdateListener(this) {
            private int previousAnimatedIntValue = i;
            final /* synthetic */ BaseTransientBottomBar this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationUpdate(ValueAnimator animator) {
                int currentAnimatedIntValue = ((Integer) animator.getAnimatedValue()).intValue();
                if (BaseTransientBottomBar.USE_OFFSET_API) {
                    ViewCompat.offsetTopAndBottom(this.this$0.view, currentAnimatedIntValue - this.previousAnimatedIntValue);
                } else {
                    this.this$0.view.setTranslationY((float) currentAnimatedIntValue);
                }
                this.previousAnimatedIntValue = currentAnimatedIntValue;
            }
        };
        animator.addUpdateListener(animatorUpdateListener);
        animator.start();
    }

    private void animateViewOut(int event) {
        ValueAnimator valueAnimator;
        Animator.AnimatorListener animatorListener;
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
        new ValueAnimator();
        ValueAnimator animator = valueAnimator;
        int[] iArr = new int[2];
        iArr[0] = 0;
        int[] iArr2 = iArr;
        iArr2[1] = getTranslationYBottom();
        animator.setIntValues(iArr2);
        animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        ValueAnimator duration2 = animator.setDuration(250);
        final int i = event;
        new AnimatorListenerAdapter(this) {
            final /* synthetic */ BaseTransientBottomBar this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationStart(Animator animator) {
                Animator animator2 = animator;
                this.this$0.contentViewCallback.animateContentOut(0, BaseTransientBottomBar.ANIMATION_FADE_DURATION);
            }

            public void onAnimationEnd(Animator animator) {
                Animator animator2 = animator;
                this.this$0.onViewHidden(i);
            }
        };
        animator.addListener(animatorListener);
        new ValueAnimator.AnimatorUpdateListener(this) {
            private int previousAnimatedIntValue = 0;
            final /* synthetic */ BaseTransientBottomBar this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationUpdate(ValueAnimator animator) {
                int currentAnimatedIntValue = ((Integer) animator.getAnimatedValue()).intValue();
                if (BaseTransientBottomBar.USE_OFFSET_API) {
                    ViewCompat.offsetTopAndBottom(this.this$0.view, currentAnimatedIntValue - this.previousAnimatedIntValue);
                } else {
                    this.this$0.view.setTranslationY((float) currentAnimatedIntValue);
                }
                this.previousAnimatedIntValue = currentAnimatedIntValue;
            }
        };
        animator.addUpdateListener(animatorUpdateListener);
        animator.start();
    }

    private int getTranslationYBottom() {
        int translationY = this.view.getHeight();
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            translationY += ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return translationY;
    }

    /* access modifiers changed from: package-private */
    public final void hideView(int i) {
        int event = i;
        if (!shouldAnimate() || this.view.getVisibility() != 0) {
            onViewHidden(event);
        } else {
            animateViewOut(event);
        }
    }

    /* access modifiers changed from: package-private */
    public void onViewShown() {
        SnackbarManager.getInstance().onShown(this.managerCallback);
        if (this.callbacks != null) {
            for (int i = this.callbacks.size() - 1; i >= 0; i--) {
                this.callbacks.get(i).onShown(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onViewHidden(int i) {
        int event = i;
        SnackbarManager.getInstance().onDismissed(this.managerCallback);
        if (this.callbacks != null) {
            for (int i2 = this.callbacks.size() - 1; i2 >= 0; i2--) {
                this.callbacks.get(i2).onDismissed(this, event);
            }
        }
        ViewParent parent = this.view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.view);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldAnimate() {
        List<AccessibilityServiceInfo> serviceList = this.accessibilityManager.getEnabledAccessibilityServiceList(1);
        return serviceList != null && serviceList.isEmpty();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected static class SnackbarBaseLayout extends FrameLayout {
        private final AccessibilityManager accessibilityManager;
        private OnAttachStateChangeListener onAttachStateChangeListener;
        private OnLayoutChangeListener onLayoutChangeListener;
        private final AccessibilityManagerCompat.TouchExplorationStateChangeListener touchExplorationStateChangeListener;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        protected SnackbarBaseLayout(Context context) {
            this(context, (AttributeSet) null);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected SnackbarBaseLayout(android.content.Context r10, android.util.AttributeSet r11) {
            /*
                r9 = this;
                r0 = r9
                r1 = r10
                r2 = r11
                r4 = r0
                r5 = r1
                r6 = r2
                r4.<init>(r5, r6)
                r4 = r1
                r5 = r2
                int[] r6 = android.support.design.C0064R.styleable.SnackbarLayout
                android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r6)
                r3 = r4
                r4 = r3
                int r5 = android.support.design.C0064R.styleable.SnackbarLayout_elevation
                boolean r4 = r4.hasValue(r5)
                if (r4 == 0) goto L_0x0028
                r4 = r0
                r5 = r3
                int r6 = android.support.design.C0064R.styleable.SnackbarLayout_elevation
                r7 = 0
                int r5 = r5.getDimensionPixelSize(r6, r7)
                float r5 = (float) r5
                android.support.p000v4.view.ViewCompat.setElevation(r4, r5)
            L_0x0028:
                r4 = r3
                r4.recycle()
                r4 = r0
                r5 = r1
                java.lang.String r6 = "accessibility"
                java.lang.Object r5 = r5.getSystemService(r6)
                android.view.accessibility.AccessibilityManager r5 = (android.view.accessibility.AccessibilityManager) r5
                r4.accessibilityManager = r5
                r4 = r0
                android.support.design.widget.BaseTransientBottomBar$SnackbarBaseLayout$1 r5 = new android.support.design.widget.BaseTransientBottomBar$SnackbarBaseLayout$1
                r8 = r5
                r5 = r8
                r6 = r8
                r7 = r0
                r6.<init>(r7)
                r4.touchExplorationStateChangeListener = r5
                r4 = r0
                android.view.accessibility.AccessibilityManager r4 = r4.accessibilityManager
                r5 = r0
                android.support.v4.view.accessibility.AccessibilityManagerCompat$TouchExplorationStateChangeListener r5 = r5.touchExplorationStateChangeListener
                boolean r4 = android.support.p000v4.view.accessibility.AccessibilityManagerCompat.addTouchExplorationStateChangeListener(r4, r5)
                r4 = r0
                r5 = r0
                android.view.accessibility.AccessibilityManager r5 = r5.accessibilityManager
                boolean r5 = r5.isTouchExplorationEnabled()
                r4.setClickableOrFocusableBasedOnAccessibility(r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.BaseTransientBottomBar.SnackbarBaseLayout.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        /* access modifiers changed from: private */
        public void setClickableOrFocusableBasedOnAccessibility(boolean z) {
            boolean touchExplorationEnabled = z;
            setClickable(!touchExplorationEnabled);
            setFocusable(touchExplorationEnabled);
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean changed, int i, int i2, int i3, int i4) {
            int l = i;
            int t = i2;
            int r = i3;
            int b = i4;
            super.onLayout(changed, l, t, r, b);
            if (this.onLayoutChangeListener != null) {
                this.onLayoutChangeListener.onLayoutChange(this, l, t, r, b);
            }
        }

        /* access modifiers changed from: protected */
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (this.onAttachStateChangeListener != null) {
                this.onAttachStateChangeListener.onViewAttachedToWindow(this);
            }
            ViewCompat.requestApplyInsets(this);
        }

        /* access modifiers changed from: protected */
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (this.onAttachStateChangeListener != null) {
                this.onAttachStateChangeListener.onViewDetachedFromWindow(this);
            }
            boolean removeTouchExplorationStateChangeListener = AccessibilityManagerCompat.removeTouchExplorationStateChangeListener(this.accessibilityManager, this.touchExplorationStateChangeListener);
        }

        /* access modifiers changed from: package-private */
        public void setOnLayoutChangeListener(OnLayoutChangeListener onLayoutChangeListener2) {
            OnLayoutChangeListener onLayoutChangeListener3 = onLayoutChangeListener2;
            this.onLayoutChangeListener = onLayoutChangeListener3;
        }

        /* access modifiers changed from: package-private */
        public void setOnAttachStateChangeListener(OnAttachStateChangeListener listener) {
            OnAttachStateChangeListener onAttachStateChangeListener2 = listener;
            this.onAttachStateChangeListener = onAttachStateChangeListener2;
        }
    }

    public static class Behavior extends SwipeDismissBehavior<View> {
        private final BehaviorDelegate delegate;

        public Behavior() {
            BehaviorDelegate behaviorDelegate;
            new BehaviorDelegate(this);
            this.delegate = behaviorDelegate;
        }

        /* access modifiers changed from: private */
        public void setBaseTransientBottomBar(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.delegate.setBaseTransientBottomBar(baseTransientBottomBar);
        }

        public boolean canSwipeDismissView(View child) {
            return this.delegate.canSwipeDismissView(child);
        }

        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            CoordinatorLayout parent = coordinatorLayout;
            View child = view;
            MotionEvent event = motionEvent;
            this.delegate.onInterceptTouchEvent(parent, child, event);
            return super.onInterceptTouchEvent(parent, child, event);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class BehaviorDelegate {
        private SnackbarManager.Callback managerCallback;

        public BehaviorDelegate(SwipeDismissBehavior<?> swipeDismissBehavior) {
            SwipeDismissBehavior<?> behavior = swipeDismissBehavior;
            behavior.setStartAlphaSwipeDistance(0.1f);
            behavior.setEndAlphaSwipeDistance(0.6f);
            behavior.setSwipeDirection(0);
        }

        public void setBaseTransientBottomBar(BaseTransientBottomBar<?> baseTransientBottomBar) {
            SnackbarManager.Callback callback = baseTransientBottomBar.managerCallback;
            this.managerCallback = callback;
        }

        public boolean canSwipeDismissView(View child) {
            return child instanceof SnackbarBaseLayout;
        }

        public void onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            CoordinatorLayout parent = coordinatorLayout;
            View child = view;
            MotionEvent event = motionEvent;
            switch (event.getActionMasked()) {
                case 0:
                    if (parent.isPointInChildBounds(child, (int) event.getX(), (int) event.getY())) {
                        SnackbarManager.getInstance().pauseTimeout(this.managerCallback);
                        return;
                    }
                    return;
                case 1:
                case 3:
                    SnackbarManager.getInstance().restoreTimeoutIfPaused(this.managerCallback);
                    return;
                default:
                    return;
            }
        }
    }
}
