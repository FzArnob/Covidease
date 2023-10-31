package android.support.design.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.coordinatorlayout.C0031R;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.util.ObjectsCompat;
import android.support.p000v4.util.Pools;
import android.support.p000v4.view.AbsSavedState;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.NestedScrollingParent2;
import android.support.p000v4.view.NestedScrollingParentHelper;
import android.support.p000v4.view.OnApplyWindowInsetsListener;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.WindowInsetsCompat;
import android.support.p000v4.widget.DirectedAcyclicGraph;
import android.support.p000v4.widget.ViewGroupUtils;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinatorLayout extends ViewGroup implements NestedScrollingParent2 {
    static final Class<?>[] CONSTRUCTOR_PARAMS;
    static final int EVENT_NESTED_SCROLL = 1;
    static final int EVENT_PRE_DRAW = 0;
    static final int EVENT_VIEW_REMOVED = 2;
    static final String TAG = "CoordinatorLayout";
    static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR;
    private static final int TYPE_ON_INTERCEPT = 0;
    private static final int TYPE_ON_TOUCH = 1;
    static final String WIDGET_PACKAGE_NAME;
    static final ThreadLocal<Map<String, Constructor<Behavior>>> sConstructors;
    private static final Pools.Pool<Rect> sRectPool;
    private OnApplyWindowInsetsListener mApplyWindowInsetsListener;
    private View mBehaviorTouchView;
    private final DirectedAcyclicGraph<View> mChildDag;
    private final List<View> mDependencySortedChildren;
    private boolean mDisallowInterceptReset;
    private boolean mDrawStatusBarBackground;
    private boolean mIsAttachedToWindow;
    private int[] mKeylines;
    private WindowInsetsCompat mLastInsets;
    private boolean mNeedsPreDrawListener;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private View mNestedScrollingTarget;
    ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
    private OnPreDrawListener mOnPreDrawListener;
    private Paint mScrimPaint;
    private Drawable mStatusBarBackground;
    private final List<View> mTempDependenciesList;
    private final int[] mTempIntPair;
    private final List<View> mTempList1;

    public interface AttachedBehavior {
        @NonNull
        Behavior getBehavior();
    }

    @Deprecated
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DefaultBehavior {
        Class<? extends Behavior> value();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DispatchChangeEvent {
    }

    static {
        ThreadLocal<Map<String, Constructor<Behavior>>> threadLocal;
        Pools.Pool<Rect> pool;
        Comparator<View> comparator;
        Package pkg = CoordinatorLayout.class.getPackage();
        WIDGET_PACKAGE_NAME = pkg != null ? pkg.getName() : null;
        if (Build.VERSION.SDK_INT >= 21) {
            new ViewElevationComparator();
            TOP_SORTED_CHILDREN_COMPARATOR = comparator;
        } else {
            TOP_SORTED_CHILDREN_COMPARATOR = null;
        }
        Class<?>[] clsArr = new Class[2];
        clsArr[0] = Context.class;
        Class<?>[] clsArr2 = clsArr;
        clsArr2[1] = AttributeSet.class;
        CONSTRUCTOR_PARAMS = clsArr2;
        new ThreadLocal<>();
        sConstructors = threadLocal;
        new Pools.SynchronizedPool(12);
        sRectPool = pool;
    }

    @NonNull
    private static Rect acquireTempRect() {
        Rect rect;
        Rect rect2 = sRectPool.acquire();
        if (rect2 == null) {
            new Rect();
            rect2 = rect;
        }
        return rect2;
    }

    private static void releaseTempRect(@NonNull Rect rect) {
        Rect rect2 = rect;
        rect2.setEmpty();
        boolean release = sRectPool.release(rect2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CoordinatorLayout(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, C0031R.attr.coordinatorLayoutStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CoordinatorLayout(@android.support.annotation.NonNull android.content.Context r17, @android.support.annotation.Nullable android.util.AttributeSet r18, @android.support.annotation.AttrRes int r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            r10 = r0
            r11 = r1
            r12 = r2
            r13 = r3
            r10.<init>(r11, r12, r13)
            r10 = r0
            java.util.ArrayList r11 = new java.util.ArrayList
            r15 = r11
            r11 = r15
            r12 = r15
            r12.<init>()
            r10.mDependencySortedChildren = r11
            r10 = r0
            android.support.v4.widget.DirectedAcyclicGraph r11 = new android.support.v4.widget.DirectedAcyclicGraph
            r15 = r11
            r11 = r15
            r12 = r15
            r12.<init>()
            r10.mChildDag = r11
            r10 = r0
            java.util.ArrayList r11 = new java.util.ArrayList
            r15 = r11
            r11 = r15
            r12 = r15
            r12.<init>()
            r10.mTempList1 = r11
            r10 = r0
            java.util.ArrayList r11 = new java.util.ArrayList
            r15 = r11
            r11 = r15
            r12 = r15
            r12.<init>()
            r10.mTempDependenciesList = r11
            r10 = r0
            r11 = 2
            int[] r11 = new int[r11]
            r10.mTempIntPair = r11
            r10 = r0
            android.support.v4.view.NestedScrollingParentHelper r11 = new android.support.v4.view.NestedScrollingParentHelper
            r15 = r11
            r11 = r15
            r12 = r15
            r13 = r0
            r12.<init>(r13)
            r10.mNestedScrollingParentHelper = r11
            r10 = r3
            if (r10 != 0) goto L_0x009d
            r10 = r1
            r11 = r2
            int[] r12 = android.support.coordinatorlayout.C0031R.styleable.CoordinatorLayout
            r13 = 0
            int r14 = android.support.coordinatorlayout.C0031R.C0034style.Widget_Support_CoordinatorLayout
            android.content.res.TypedArray r10 = r10.obtainStyledAttributes(r11, r12, r13, r14)
        L_0x005b:
            r4 = r10
            r10 = r4
            int r11 = android.support.coordinatorlayout.C0031R.styleable.CoordinatorLayout_keylines
            r12 = 0
            int r10 = r10.getResourceId(r11, r12)
            r5 = r10
            r10 = r5
            if (r10 == 0) goto L_0x00a8
            r10 = r1
            android.content.res.Resources r10 = r10.getResources()
            r6 = r10
            r10 = r0
            r11 = r6
            r12 = r5
            int[] r11 = r11.getIntArray(r12)
            r10.mKeylines = r11
            r10 = r6
            android.util.DisplayMetrics r10 = r10.getDisplayMetrics()
            float r10 = r10.density
            r7 = r10
            r10 = r0
            int[] r10 = r10.mKeylines
            int r10 = r10.length
            r8 = r10
            r10 = 0
            r9 = r10
        L_0x0086:
            r10 = r9
            r11 = r8
            if (r10 >= r11) goto L_0x00a8
            r10 = r0
            int[] r10 = r10.mKeylines
            r11 = r9
            r12 = r0
            int[] r12 = r12.mKeylines
            r13 = r9
            r12 = r12[r13]
            float r12 = (float) r12
            r13 = r7
            float r12 = r12 * r13
            int r12 = (int) r12
            r10[r11] = r12
            int r9 = r9 + 1
            goto L_0x0086
        L_0x009d:
            r10 = r1
            r11 = r2
            int[] r12 = android.support.coordinatorlayout.C0031R.styleable.CoordinatorLayout
            r13 = r3
            r14 = 0
            android.content.res.TypedArray r10 = r10.obtainStyledAttributes(r11, r12, r13, r14)
            goto L_0x005b
        L_0x00a8:
            r10 = r0
            r11 = r4
            int r12 = android.support.coordinatorlayout.C0031R.styleable.CoordinatorLayout_statusBarBackground
            android.graphics.drawable.Drawable r11 = r11.getDrawable(r12)
            r10.mStatusBarBackground = r11
            r10 = r4
            r10.recycle()
            r10 = r0
            r10.setupForInsets()
            r10 = r0
            android.support.design.widget.CoordinatorLayout$HierarchyChangeListener r11 = new android.support.design.widget.CoordinatorLayout$HierarchyChangeListener
            r15 = r11
            r11 = r15
            r12 = r15
            r13 = r0
            r12.<init>(r13)
            super.setOnHierarchyChangeListener(r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.CoordinatorLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener2 = onHierarchyChangeListener;
        this.mOnHierarchyChangeListener = onHierarchyChangeListener2;
    }

    public void onAttachedToWindow() {
        OnPreDrawListener onPreDrawListener;
        super.onAttachedToWindow();
        resetTouchBehaviors(false);
        if (this.mNeedsPreDrawListener) {
            if (this.mOnPreDrawListener == null) {
                new OnPreDrawListener(this);
                this.mOnPreDrawListener = onPreDrawListener;
            }
            getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        if (this.mLastInsets == null && ViewCompat.getFitsSystemWindows(this)) {
            ViewCompat.requestApplyInsets(this);
        }
        this.mIsAttachedToWindow = true;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        resetTouchBehaviors(false);
        if (this.mNeedsPreDrawListener && this.mOnPreDrawListener != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        if (this.mNestedScrollingTarget != null) {
            onStopNestedScroll(this.mNestedScrollingTarget);
        }
        this.mIsAttachedToWindow = false;
    }

    public void setStatusBarBackground(@Nullable Drawable drawable) {
        Drawable bg = drawable;
        if (this.mStatusBarBackground != bg) {
            if (this.mStatusBarBackground != null) {
                this.mStatusBarBackground.setCallback((Drawable.Callback) null);
            }
            this.mStatusBarBackground = bg != null ? bg.mutate() : null;
            if (this.mStatusBarBackground != null) {
                if (this.mStatusBarBackground.isStateful()) {
                    boolean state = this.mStatusBarBackground.setState(getDrawableState());
                }
                boolean layoutDirection = DrawableCompat.setLayoutDirection(this.mStatusBarBackground, ViewCompat.getLayoutDirection(this));
                boolean visible = this.mStatusBarBackground.setVisible(getVisibility() == 0, false);
                this.mStatusBarBackground.setCallback(this);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Nullable
    public Drawable getStatusBarBackground() {
        return this.mStatusBarBackground;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] state = getDrawableState();
        boolean changed = false;
        Drawable d = this.mStatusBarBackground;
        if (d != null && d.isStateful()) {
            changed = false | d.setState(state);
        }
        if (changed) {
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        Drawable who = drawable;
        return super.verifyDrawable(who) || who == this.mStatusBarBackground;
    }

    public void setVisibility(int i) {
        int visibility = i;
        super.setVisibility(visibility);
        boolean visible = visibility == 0;
        if (this.mStatusBarBackground != null && this.mStatusBarBackground.isVisible() != visible) {
            boolean visible2 = this.mStatusBarBackground.setVisible(visible, false);
        }
    }

    public void setStatusBarBackgroundResource(@DrawableRes int i) {
        int resId = i;
        setStatusBarBackground(resId != 0 ? ContextCompat.getDrawable(getContext(), resId) : null);
    }

    public void setStatusBarBackgroundColor(@ColorInt int color) {
        Drawable drawable;
        new ColorDrawable(color);
        setStatusBarBackground(drawable);
    }

    /* access modifiers changed from: package-private */
    public final WindowInsetsCompat setWindowInsets(WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat insets = windowInsetsCompat;
        if (!ObjectsCompat.equals(this.mLastInsets, insets)) {
            this.mLastInsets = insets;
            this.mDrawStatusBarBackground = insets != null && insets.getSystemWindowInsetTop() > 0;
            setWillNotDraw(!this.mDrawStatusBarBackground && getBackground() == null);
            insets = dispatchApplyWindowInsetsToBehaviors(insets);
            requestLayout();
        }
        return insets;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final WindowInsetsCompat getLastWindowInsets() {
        return this.mLastInsets;
    }

    private void resetTouchBehaviors(boolean z) {
        boolean notifyOnInterceptTouchEvent = z;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            Behavior b = ((LayoutParams) child.getLayoutParams()).getBehavior();
            if (b != null) {
                long now = SystemClock.uptimeMillis();
                MotionEvent cancelEvent = MotionEvent.obtain(now, now, 3, 0.0f, 0.0f, 0);
                if (notifyOnInterceptTouchEvent) {
                    boolean onInterceptTouchEvent = b.onInterceptTouchEvent(this, child, cancelEvent);
                } else {
                    boolean onTouchEvent = b.onTouchEvent(this, child, cancelEvent);
                }
                cancelEvent.recycle();
            }
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            ((LayoutParams) getChildAt(i2).getLayoutParams()).resetTouchBehaviorTracking();
        }
        this.mBehaviorTouchView = null;
        this.mDisallowInterceptReset = false;
    }

    private void getTopSortedChildren(List<View> list) {
        List<View> out = list;
        out.clear();
        boolean useCustomOrder = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            boolean add = out.add(getChildAt(useCustomOrder ? getChildDrawingOrder(childCount, i) : i));
        }
        if (TOP_SORTED_CHILDREN_COMPARATOR != null) {
            Collections.sort(out, TOP_SORTED_CHILDREN_COMPARATOR);
        }
    }

    private boolean performIntercept(MotionEvent motionEvent, int i) {
        MotionEvent ev = motionEvent;
        int type = i;
        boolean intercepted = false;
        boolean newBlock = false;
        MotionEvent cancelEvent = null;
        int action = ev.getActionMasked();
        List<View> topmostChildList = this.mTempList1;
        getTopSortedChildren(topmostChildList);
        int childCount = topmostChildList.size();
        for (int i2 = 0; i2 < childCount; i2++) {
            View child = topmostChildList.get(i2);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            Behavior b = lp.getBehavior();
            if ((intercepted || newBlock) && action != 0) {
                if (b != null) {
                    if (cancelEvent == null) {
                        long now = SystemClock.uptimeMillis();
                        cancelEvent = MotionEvent.obtain(now, now, 3, 0.0f, 0.0f, 0);
                    }
                    switch (type) {
                        case 0:
                            boolean onInterceptTouchEvent = b.onInterceptTouchEvent(this, child, cancelEvent);
                            break;
                        case 1:
                            boolean onTouchEvent = b.onTouchEvent(this, child, cancelEvent);
                            break;
                    }
                }
            } else {
                if (!intercepted && b != null) {
                    switch (type) {
                        case 0:
                            intercepted = b.onInterceptTouchEvent(this, child, ev);
                            break;
                        case 1:
                            intercepted = b.onTouchEvent(this, child, ev);
                            break;
                    }
                    if (intercepted) {
                        this.mBehaviorTouchView = child;
                    }
                }
                boolean wasBlocking = lp.didBlockInteraction();
                boolean isBlocking = lp.isBlockingInteractionBelow(this, child);
                newBlock = isBlocking && !wasBlocking;
                if (isBlocking && !newBlock) {
                    topmostChildList.clear();
                    return intercepted;
                }
            }
        }
        topmostChildList.clear();
        return intercepted;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        int action = ev.getActionMasked();
        if (action == 0) {
            resetTouchBehaviors(true);
        }
        boolean intercepted = performIntercept(ev, 0);
        if (action == 1 || action == 3) {
            resetTouchBehaviors(true);
        }
        return intercepted;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0023, code lost:
        if (r16 != false) goto L_0x0025;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r8 = 0
            r2 = r8
            r8 = 0
            r3 = r8
            r8 = 0
            r4 = r8
            r8 = r1
            int r8 = r8.getActionMasked()
            r5 = r8
            r8 = r0
            android.view.View r8 = r8.mBehaviorTouchView
            if (r8 != 0) goto L_0x0025
            r8 = r0
            r9 = r1
            r10 = 1
            boolean r8 = r8.performIntercept(r9, r10)
            r16 = r8
            r8 = r16
            r9 = r16
            r3 = r9
            if (r8 == 0) goto L_0x0043
        L_0x0025:
            r8 = r0
            android.view.View r8 = r8.mBehaviorTouchView
            android.view.ViewGroup$LayoutParams r8 = r8.getLayoutParams()
            android.support.design.widget.CoordinatorLayout$LayoutParams r8 = (android.support.design.widget.CoordinatorLayout.LayoutParams) r8
            r6 = r8
            r8 = r6
            android.support.design.widget.CoordinatorLayout$Behavior r8 = r8.getBehavior()
            r7 = r8
            r8 = r7
            if (r8 == 0) goto L_0x0043
            r8 = r7
            r9 = r0
            r10 = r0
            android.view.View r10 = r10.mBehaviorTouchView
            r11 = r1
            boolean r8 = r8.onTouchEvent(r9, r10, r11)
            r2 = r8
        L_0x0043:
            r8 = r0
            android.view.View r8 = r8.mBehaviorTouchView
            if (r8 != 0) goto L_0x006e
            r8 = r2
            r9 = r0
            r10 = r1
            boolean r9 = super.onTouchEvent(r10)
            r8 = r8 | r9
            r2 = r8
        L_0x0051:
            r8 = r2
            if (r8 != 0) goto L_0x0057
            r8 = r5
            if (r8 != 0) goto L_0x0057
        L_0x0057:
            r8 = r4
            if (r8 == 0) goto L_0x005e
            r8 = r4
            r8.recycle()
        L_0x005e:
            r8 = r5
            r9 = 1
            if (r8 == r9) goto L_0x0066
            r8 = r5
            r9 = 3
            if (r8 != r9) goto L_0x006b
        L_0x0066:
            r8 = r0
            r9 = 0
            r8.resetTouchBehaviors(r9)
        L_0x006b:
            r8 = r2
            r0 = r8
            return r0
        L_0x006e:
            r8 = r3
            if (r8 == 0) goto L_0x0051
            r8 = r4
            if (r8 != 0) goto L_0x0084
            long r8 = android.os.SystemClock.uptimeMillis()
            r6 = r8
            r8 = r6
            r10 = r6
            r12 = 3
            r13 = 0
            r14 = 0
            r15 = 0
            android.view.MotionEvent r8 = android.view.MotionEvent.obtain(r8, r10, r12, r13, r14, r15)
            r4 = r8
        L_0x0084:
            r8 = r0
            r9 = r4
            boolean r8 = super.onTouchEvent(r9)
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.CoordinatorLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        boolean disallowIntercept = z;
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
        if (disallowIntercept && !this.mDisallowInterceptReset) {
            resetTouchBehaviors(false);
            this.mDisallowInterceptReset = true;
        }
    }

    private int getKeyline(int i) {
        StringBuilder sb;
        StringBuilder sb2;
        int index = i;
        if (this.mKeylines == null) {
            new StringBuilder();
            int e = Log.e(TAG, sb2.append("No keylines defined for ").append(this).append(" - attempted index lookup ").append(index).toString());
            return 0;
        } else if (index >= 0 && index < this.mKeylines.length) {
            return this.mKeylines[index];
        } else {
            new StringBuilder();
            int e2 = Log.e(TAG, sb.append("Keyline index ").append(index).append(" out of range for ").append(this).toString());
            return 0;
        }
    }

    static Behavior parseBehavior(Context context, AttributeSet attributeSet, String str) {
        String str2;
        String fullName;
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        Map map;
        StringBuilder sb3;
        Context context2 = context;
        AttributeSet attrs = attributeSet;
        String name = str;
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        if (name.startsWith(".")) {
            new StringBuilder();
            fullName = sb3.append(context2.getPackageName()).append(name).toString();
        } else if (name.indexOf(46) >= 0) {
            fullName = name;
        } else {
            if (!TextUtils.isEmpty(WIDGET_PACKAGE_NAME)) {
                new StringBuilder();
                str2 = sb.append(WIDGET_PACKAGE_NAME).append('.').append(name).toString();
            } else {
                str2 = name;
            }
            fullName = str2;
        }
        try {
            Map map2 = sConstructors.get();
            if (map2 == null) {
                new HashMap();
                map2 = map;
                sConstructors.set(map2);
            }
            Constructor<?> constructor = (Constructor) map2.get(fullName);
            if (constructor == null) {
                constructor = context2.getClassLoader().loadClass(fullName).getConstructor(CONSTRUCTOR_PARAMS);
                constructor.setAccessible(true);
                Object put = map2.put(fullName, constructor);
            }
            Object[] objArr = new Object[2];
            objArr[0] = context2;
            Object[] objArr2 = objArr;
            objArr2[1] = attrs;
            return (Behavior) constructor.newInstance(objArr2);
        } catch (Exception e) {
            Exception e2 = e;
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb2.append("Could not inflate Behavior subclass ").append(fullName).toString(), e2);
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public LayoutParams getResolvedLayoutParams(View view) {
        StringBuilder sb;
        View child = view;
        LayoutParams result = (LayoutParams) child.getLayoutParams();
        if (!result.mBehaviorResolved) {
            if (child instanceof AttachedBehavior) {
                Behavior attachedBehavior = ((AttachedBehavior) child).getBehavior();
                if (attachedBehavior == null) {
                    int e = Log.e(TAG, "Attached behavior class is null");
                }
                result.setBehavior(attachedBehavior);
                result.mBehaviorResolved = true;
            } else {
                DefaultBehavior defaultBehavior = null;
                for (Class<? super Object> childClass = child.getClass(); childClass != null; childClass = childClass.getSuperclass()) {
                    DefaultBehavior defaultBehavior2 = (DefaultBehavior) childClass.getAnnotation(DefaultBehavior.class);
                    defaultBehavior = defaultBehavior2;
                    if (defaultBehavior2 != null) {
                        break;
                    }
                }
                if (defaultBehavior != null) {
                    try {
                        result.setBehavior((Behavior) defaultBehavior.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                    } catch (Exception e2) {
                        new StringBuilder();
                        int e3 = Log.e(TAG, sb.append("Default behavior class ").append(defaultBehavior.value().getName()).append(" could not be instantiated. Did you forget").append(" a default constructor?").toString(), e2);
                    }
                }
                result.mBehaviorResolved = true;
            }
        }
        return result;
    }

    private void prepareChildren() {
        this.mDependencySortedChildren.clear();
        this.mChildDag.clear();
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            LayoutParams lp = getResolvedLayoutParams(view);
            View findAnchorView = lp.findAnchorView(this, view);
            this.mChildDag.addNode(view);
            for (int j = 0; j < count; j++) {
                if (j != i) {
                    View other = getChildAt(j);
                    if (lp.dependsOn(this, view, other)) {
                        if (!this.mChildDag.contains(other)) {
                            this.mChildDag.addNode(other);
                        }
                        this.mChildDag.addEdge(other, view);
                    }
                }
            }
        }
        boolean addAll = this.mDependencySortedChildren.addAll(this.mChildDag.getSortedList());
        Collections.reverse(this.mDependencySortedChildren);
    }

    /* access modifiers changed from: package-private */
    public void getDescendantRect(View descendant, Rect out) {
        ViewGroupUtils.getDescendantRect(this, descendant, out);
    }

    /* access modifiers changed from: protected */
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    /* access modifiers changed from: protected */
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    public void onMeasureChild(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        prepareChildren();
        ensurePreDrawListener();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        boolean isRtl = layoutDirection == 1;
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        int widthPadding = paddingLeft + paddingRight;
        int heightPadding = paddingTop + paddingBottom;
        int widthUsed = getSuggestedMinimumWidth();
        int heightUsed = getSuggestedMinimumHeight();
        int childState = 0;
        boolean applyInsets = this.mLastInsets != null && ViewCompat.getFitsSystemWindows(this);
        int childCount = this.mDependencySortedChildren.size();
        for (int i3 = 0; i3 < childCount; i3++) {
            View child = this.mDependencySortedChildren.get(i3);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                int keylineWidthUsed = 0;
                if (lp.keyline >= 0 && widthMode != 0) {
                    int keylinePos = getKeyline(lp.keyline);
                    int keylineGravity = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(lp.gravity), layoutDirection) & 7;
                    if ((keylineGravity == 3 && !isRtl) || (keylineGravity == 5 && isRtl)) {
                        keylineWidthUsed = Math.max(0, (widthSize - paddingRight) - keylinePos);
                    } else if ((keylineGravity == 5 && !isRtl) || (keylineGravity == 3 && isRtl)) {
                        keylineWidthUsed = Math.max(0, keylinePos - paddingLeft);
                    }
                }
                int childWidthMeasureSpec = widthMeasureSpec;
                int childHeightMeasureSpec = heightMeasureSpec;
                if (applyInsets && !ViewCompat.getFitsSystemWindows(child)) {
                    int horizInsets = this.mLastInsets.getSystemWindowInsetLeft() + this.mLastInsets.getSystemWindowInsetRight();
                    int vertInsets = this.mLastInsets.getSystemWindowInsetTop() + this.mLastInsets.getSystemWindowInsetBottom();
                    childWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(widthSize - horizInsets, widthMode);
                    childHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(heightSize - vertInsets, heightMode);
                }
                Behavior b = lp.getBehavior();
                if (b == null || !b.onMeasureChild(this, child, childWidthMeasureSpec, keylineWidthUsed, childHeightMeasureSpec, 0)) {
                    onMeasureChild(child, childWidthMeasureSpec, keylineWidthUsed, childHeightMeasureSpec, 0);
                }
                widthUsed = Math.max(widthUsed, widthPadding + child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
                heightUsed = Math.max(heightUsed, heightPadding + child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
                childState = View.combineMeasuredStates(childState, child.getMeasuredState());
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(widthUsed, widthMeasureSpec, childState & -16777216), View.resolveSizeAndState(heightUsed, heightMeasureSpec, childState << 16));
    }

    private WindowInsetsCompat dispatchApplyWindowInsetsToBehaviors(WindowInsetsCompat windowInsetsCompat) {
        Behavior b;
        WindowInsetsCompat insets = windowInsetsCompat;
        if (insets.isConsumed()) {
            return insets;
        }
        int z = getChildCount();
        for (int i = 0; i < z; i++) {
            View child = getChildAt(i);
            if (ViewCompat.getFitsSystemWindows(child) && (b = ((LayoutParams) child.getLayoutParams()).getBehavior()) != null) {
                insets = b.onApplyWindowInsets(this, child, insets);
                if (insets.isConsumed()) {
                    break;
                }
            }
        }
        return insets;
    }

    public void onLayoutChild(@NonNull View view, int i) {
        Throwable th;
        View child = view;
        int layoutDirection = i;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (lp.checkAnchorChanged()) {
            Throwable th2 = th;
            new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
            throw th2;
        } else if (lp.mAnchorView != null) {
            layoutChildWithAnchor(child, lp.mAnchorView, layoutDirection);
        } else if (lp.keyline >= 0) {
            layoutChildWithKeyline(child, lp.keyline, layoutDirection);
        } else {
            layoutChild(child, layoutDirection);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Behavior behavior;
        boolean z2 = z;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int childCount = this.mDependencySortedChildren.size();
        for (int i9 = 0; i9 < childCount; i9++) {
            View child = this.mDependencySortedChildren.get(i9);
            if (child.getVisibility() != 8 && ((behavior = ((LayoutParams) child.getLayoutParams()).getBehavior()) == null || !behavior.onLayoutChild(this, child, layoutDirection))) {
                onLayoutChild(child, layoutDirection);
            }
        }
    }

    public void onDraw(Canvas canvas) {
        Canvas c = canvas;
        super.onDraw(c);
        if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
            int inset = this.mLastInsets != null ? this.mLastInsets.getSystemWindowInsetTop() : 0;
            if (inset > 0) {
                this.mStatusBarBackground.setBounds(0, 0, getWidth(), inset);
                this.mStatusBarBackground.draw(c);
            }
        }
    }

    public void setFitsSystemWindows(boolean fitSystemWindows) {
        super.setFitsSystemWindows(fitSystemWindows);
        setupForInsets();
    }

    /* access modifiers changed from: package-private */
    public void recordLastChildRect(View child, Rect r) {
        ((LayoutParams) child.getLayoutParams()).setLastChildRect(r);
    }

    /* access modifiers changed from: package-private */
    public void getLastChildRect(View child, Rect out) {
        out.set(((LayoutParams) child.getLayoutParams()).getLastChildRect());
    }

    /* access modifiers changed from: package-private */
    public void getChildRect(View view, boolean z, Rect rect) {
        View child = view;
        boolean transform = z;
        Rect out = rect;
        if (child.isLayoutRequested() || child.getVisibility() == 8) {
            out.setEmpty();
        } else if (transform) {
            getDescendantRect(child, out);
        } else {
            out.set(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
        }
    }

    private void getDesiredAnchoredChildRectWithoutConstraints(View view, int i, Rect rect, Rect rect2, LayoutParams layoutParams, int i2, int i3) {
        int left;
        int top;
        View view2 = view;
        int layoutDirection = i;
        Rect anchorRect = rect;
        Rect out = rect2;
        LayoutParams lp = layoutParams;
        int childWidth = i2;
        int childHeight = i3;
        int absGravity = GravityCompat.getAbsoluteGravity(resolveAnchoredChildGravity(lp.gravity), layoutDirection);
        int absAnchorGravity = GravityCompat.getAbsoluteGravity(resolveGravity(lp.anchorGravity), layoutDirection);
        int hgrav = absGravity & 7;
        int vgrav = absGravity & 112;
        int anchorVgrav = absAnchorGravity & 112;
        switch (absAnchorGravity & 7) {
            case 1:
                left = anchorRect.left + (anchorRect.width() / 2);
                break;
            case 5:
                left = anchorRect.right;
                break;
            default:
                left = anchorRect.left;
                break;
        }
        switch (anchorVgrav) {
            case 16:
                top = anchorRect.top + (anchorRect.height() / 2);
                break;
            case 80:
                top = anchorRect.bottom;
                break;
            default:
                top = anchorRect.top;
                break;
        }
        switch (hgrav) {
            case 1:
                left -= childWidth / 2;
                break;
            case 5:
                break;
            default:
                left -= childWidth;
                break;
        }
        switch (vgrav) {
            case 16:
                top -= childHeight / 2;
                break;
            case 80:
                break;
            default:
                top -= childHeight;
                break;
        }
        out.set(left, top, left + childWidth, top + childHeight);
    }

    private void constrainChildRect(LayoutParams layoutParams, Rect rect, int i, int i2) {
        LayoutParams lp = layoutParams;
        Rect out = rect;
        int childWidth = i;
        int childHeight = i2;
        int width = getWidth();
        int height = getHeight();
        int left = Math.max(getPaddingLeft() + lp.leftMargin, Math.min(out.left, ((width - getPaddingRight()) - childWidth) - lp.rightMargin));
        int top = Math.max(getPaddingTop() + lp.topMargin, Math.min(out.top, ((height - getPaddingBottom()) - childHeight) - lp.bottomMargin));
        out.set(left, top, left + childWidth, top + childHeight);
    }

    /* access modifiers changed from: package-private */
    public void getDesiredAnchoredChildRect(View view, int layoutDirection, Rect anchorRect, Rect rect) {
        View child = view;
        Rect out = rect;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int childWidth = child.getMeasuredWidth();
        int childHeight = child.getMeasuredHeight();
        getDesiredAnchoredChildRectWithoutConstraints(child, layoutDirection, anchorRect, out, lp, childWidth, childHeight);
        constrainChildRect(lp, out, childWidth, childHeight);
    }

    private void layoutChildWithAnchor(View view, View anchor, int i) {
        View child = view;
        int layoutDirection = i;
        Rect anchorRect = acquireTempRect();
        Rect childRect = acquireTempRect();
        try {
            getDescendantRect(anchor, anchorRect);
            getDesiredAnchoredChildRect(child, layoutDirection, anchorRect, childRect);
            child.layout(childRect.left, childRect.top, childRect.right, childRect.bottom);
            releaseTempRect(anchorRect);
            releaseTempRect(childRect);
        } catch (Throwable th) {
            Throwable th2 = th;
            releaseTempRect(anchorRect);
            releaseTempRect(childRect);
            throw th2;
        }
    }

    private void layoutChildWithKeyline(View view, int i, int i2) {
        View child = view;
        int keyline = i;
        int layoutDirection = i2;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int absGravity = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(lp.gravity), layoutDirection);
        int hgrav = absGravity & 7;
        int vgrav = absGravity & 112;
        int width = getWidth();
        int height = getHeight();
        int childWidth = child.getMeasuredWidth();
        int childHeight = child.getMeasuredHeight();
        if (layoutDirection == 1) {
            keyline = width - keyline;
        }
        int left = getKeyline(keyline) - childWidth;
        int top = 0;
        switch (hgrav) {
            case 1:
                left += childWidth / 2;
                break;
            case 5:
                left += childWidth;
                break;
        }
        switch (vgrav) {
            case 16:
                top = 0 + (childHeight / 2);
                break;
            case 80:
                top = 0 + childHeight;
                break;
        }
        int left2 = Math.max(getPaddingLeft() + lp.leftMargin, Math.min(left, ((width - getPaddingRight()) - childWidth) - lp.rightMargin));
        int top2 = Math.max(getPaddingTop() + lp.topMargin, Math.min(top, ((height - getPaddingBottom()) - childHeight) - lp.bottomMargin));
        child.layout(left2, top2, left2 + childWidth, top2 + childHeight);
    }

    private void layoutChild(View view, int i) {
        View child = view;
        int layoutDirection = i;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        Rect parent = acquireTempRect();
        parent.set(getPaddingLeft() + lp.leftMargin, getPaddingTop() + lp.topMargin, (getWidth() - getPaddingRight()) - lp.rightMargin, (getHeight() - getPaddingBottom()) - lp.bottomMargin);
        if (this.mLastInsets != null && ViewCompat.getFitsSystemWindows(this) && !ViewCompat.getFitsSystemWindows(child)) {
            parent.left += this.mLastInsets.getSystemWindowInsetLeft();
            parent.top += this.mLastInsets.getSystemWindowInsetTop();
            parent.right -= this.mLastInsets.getSystemWindowInsetRight();
            parent.bottom -= this.mLastInsets.getSystemWindowInsetBottom();
        }
        Rect out = acquireTempRect();
        GravityCompat.apply(resolveGravity(lp.gravity), child.getMeasuredWidth(), child.getMeasuredHeight(), parent, out, layoutDirection);
        child.layout(out.left, out.top, out.right, out.bottom);
        releaseTempRect(parent);
        releaseTempRect(out);
    }

    private static int resolveGravity(int i) {
        int gravity = i;
        if ((gravity & 7) == 0) {
            gravity |= GravityCompat.START;
        }
        if ((gravity & 112) == 0) {
            gravity |= 48;
        }
        return gravity;
    }

    private static int resolveKeylineGravity(int i) {
        int gravity = i;
        return gravity == 0 ? 8388661 : gravity;
    }

    private static int resolveAnchoredChildGravity(int i) {
        int gravity = i;
        return gravity == 0 ? 17 : gravity;
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        Paint paint;
        Canvas canvas2 = canvas;
        View child = view;
        long drawingTime = j;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (lp.mBehavior != null) {
            float scrimAlpha = lp.mBehavior.getScrimOpacity(this, child);
            if (scrimAlpha > 0.0f) {
                if (this.mScrimPaint == null) {
                    new Paint();
                    this.mScrimPaint = paint;
                }
                this.mScrimPaint.setColor(lp.mBehavior.getScrimColor(this, child));
                this.mScrimPaint.setAlpha(clamp(Math.round(255.0f * scrimAlpha), 0, 255));
                int saved = canvas2.save();
                if (child.isOpaque()) {
                    boolean clipRect = canvas2.clipRect((float) child.getLeft(), (float) child.getTop(), (float) child.getRight(), (float) child.getBottom(), Region.Op.DIFFERENCE);
                }
                canvas2.drawRect((float) getPaddingLeft(), (float) getPaddingTop(), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - getPaddingBottom()), this.mScrimPaint);
                canvas2.restoreToCount(saved);
            }
        }
        return super.drawChild(canvas2, child, drawingTime);
    }

    private static int clamp(int i, int i2, int i3) {
        int value = i;
        int min = i2;
        int max = i3;
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    /* access modifiers changed from: package-private */
    public final void onChildViewsChanged(int i) {
        boolean handled;
        int type = i;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int childCount = this.mDependencySortedChildren.size();
        Rect inset = acquireTempRect();
        Rect drawRect = acquireTempRect();
        Rect lastDrawRect = acquireTempRect();
        for (int i2 = 0; i2 < childCount; i2++) {
            View child = this.mDependencySortedChildren.get(i2);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (type != 0 || child.getVisibility() != 8) {
                for (int j = 0; j < i2; j++) {
                    if (lp.mAnchorDirectChild == this.mDependencySortedChildren.get(j)) {
                        offsetChildToAnchor(child, layoutDirection);
                    }
                }
                getChildRect(child, true, drawRect);
                if (lp.insetEdge != 0 && !drawRect.isEmpty()) {
                    int absInsetEdge = GravityCompat.getAbsoluteGravity(lp.insetEdge, layoutDirection);
                    switch (absInsetEdge & 112) {
                        case 48:
                            inset.top = Math.max(inset.top, drawRect.bottom);
                            break;
                        case 80:
                            inset.bottom = Math.max(inset.bottom, getHeight() - drawRect.top);
                            break;
                    }
                    switch (absInsetEdge & 7) {
                        case 3:
                            inset.left = Math.max(inset.left, drawRect.right);
                            break;
                        case 5:
                            inset.right = Math.max(inset.right, getWidth() - drawRect.left);
                            break;
                    }
                }
                if (lp.dodgeInsetEdges != 0 && child.getVisibility() == 0) {
                    offsetChildByInset(child, inset, layoutDirection);
                }
                if (type != 2) {
                    getLastChildRect(child, lastDrawRect);
                    if (!lastDrawRect.equals(drawRect)) {
                        recordLastChildRect(child, drawRect);
                    }
                }
                for (int j2 = i2 + 1; j2 < childCount; j2++) {
                    View checkChild = this.mDependencySortedChildren.get(j2);
                    LayoutParams checkLp = (LayoutParams) checkChild.getLayoutParams();
                    Behavior b = checkLp.getBehavior();
                    if (b != null && b.layoutDependsOn(this, checkChild, child)) {
                        if (type != 0 || !checkLp.getChangedAfterNestedScroll()) {
                            switch (type) {
                                case 2:
                                    b.onDependentViewRemoved(this, checkChild, child);
                                    handled = true;
                                    break;
                                default:
                                    handled = b.onDependentViewChanged(this, checkChild, child);
                                    break;
                            }
                            if (type == 1) {
                                checkLp.setChangedAfterNestedScroll(handled);
                            }
                        } else {
                            checkLp.resetChangedAfterNestedScroll();
                        }
                    }
                }
            }
        }
        releaseTempRect(inset);
        releaseTempRect(drawRect);
        releaseTempRect(lastDrawRect);
    }

    private void offsetChildByInset(View view, Rect rect, int i) {
        int distance;
        int distance2;
        int distance3;
        int distance4;
        Throwable th;
        StringBuilder sb;
        View child = view;
        Rect inset = rect;
        int layoutDirection = i;
        if (ViewCompat.isLaidOut(child) && child.getWidth() > 0 && child.getHeight() > 0) {
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            Behavior behavior = lp.getBehavior();
            Rect dodgeRect = acquireTempRect();
            Rect bounds = acquireTempRect();
            bounds.set(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
            if (behavior == null || !behavior.getInsetDodgeRect(this, child, dodgeRect)) {
                dodgeRect.set(bounds);
            } else if (!bounds.contains(dodgeRect)) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Rect should be within the child's bounds. Rect:").append(dodgeRect.toShortString()).append(" | Bounds:").append(bounds.toShortString()).toString());
                throw th2;
            }
            releaseTempRect(bounds);
            if (dodgeRect.isEmpty()) {
                releaseTempRect(dodgeRect);
                return;
            }
            int absDodgeInsetEdges = GravityCompat.getAbsoluteGravity(lp.dodgeInsetEdges, layoutDirection);
            boolean offsetY = false;
            if ((absDodgeInsetEdges & 48) == 48 && (distance4 = (dodgeRect.top - lp.topMargin) - lp.mInsetOffsetY) < inset.top) {
                setInsetOffsetY(child, inset.top - distance4);
                offsetY = true;
            }
            if ((absDodgeInsetEdges & 80) == 80 && (distance3 = ((getHeight() - dodgeRect.bottom) - lp.bottomMargin) + lp.mInsetOffsetY) < inset.bottom) {
                setInsetOffsetY(child, distance3 - inset.bottom);
                offsetY = true;
            }
            if (!offsetY) {
                setInsetOffsetY(child, 0);
            }
            boolean offsetX = false;
            if ((absDodgeInsetEdges & 3) == 3 && (distance2 = (dodgeRect.left - lp.leftMargin) - lp.mInsetOffsetX) < inset.left) {
                setInsetOffsetX(child, inset.left - distance2);
                offsetX = true;
            }
            if ((absDodgeInsetEdges & 5) == 5 && (distance = ((getWidth() - dodgeRect.right) - lp.rightMargin) + lp.mInsetOffsetX) < inset.right) {
                setInsetOffsetX(child, distance - inset.right);
                offsetX = true;
            }
            if (!offsetX) {
                setInsetOffsetX(child, 0);
            }
            releaseTempRect(dodgeRect);
        }
    }

    private void setInsetOffsetX(View view, int i) {
        View child = view;
        int offsetX = i;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (lp.mInsetOffsetX != offsetX) {
            ViewCompat.offsetLeftAndRight(child, offsetX - lp.mInsetOffsetX);
            lp.mInsetOffsetX = offsetX;
        }
    }

    private void setInsetOffsetY(View view, int i) {
        View child = view;
        int offsetY = i;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (lp.mInsetOffsetY != offsetY) {
            ViewCompat.offsetTopAndBottom(child, offsetY - lp.mInsetOffsetY);
            lp.mInsetOffsetY = offsetY;
        }
    }

    public void dispatchDependentViewsChanged(@NonNull View view) {
        View view2 = view;
        List<View> dependents = this.mChildDag.getIncomingEdges(view2);
        if (dependents != null && !dependents.isEmpty()) {
            for (int i = 0; i < dependents.size(); i++) {
                View child = dependents.get(i);
                Behavior b = ((LayoutParams) child.getLayoutParams()).getBehavior();
                if (b != null) {
                    boolean onDependentViewChanged = b.onDependentViewChanged(this, child, view2);
                }
            }
        }
    }

    @NonNull
    public List<View> getDependencies(@NonNull View child) {
        List<View> dependencies = this.mChildDag.getOutgoingEdges(child);
        this.mTempDependenciesList.clear();
        if (dependencies != null) {
            boolean addAll = this.mTempDependenciesList.addAll(dependencies);
        }
        return this.mTempDependenciesList;
    }

    @NonNull
    public List<View> getDependents(@NonNull View child) {
        List<View> edges = this.mChildDag.getIncomingEdges(child);
        this.mTempDependenciesList.clear();
        if (edges != null) {
            boolean addAll = this.mTempDependenciesList.addAll(edges);
        }
        return this.mTempDependenciesList;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final List<View> getDependencySortedChildren() {
        prepareChildren();
        return Collections.unmodifiableList(this.mDependencySortedChildren);
    }

    /* access modifiers changed from: package-private */
    public void ensurePreDrawListener() {
        boolean hasDependencies = false;
        int childCount = getChildCount();
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            }
            if (hasDependencies(getChildAt(i))) {
                hasDependencies = true;
                break;
            }
            i++;
        }
        if (hasDependencies == this.mNeedsPreDrawListener) {
            return;
        }
        if (hasDependencies) {
            addPreDrawListener();
        } else {
            removePreDrawListener();
        }
    }

    private boolean hasDependencies(View child) {
        return this.mChildDag.hasOutgoingEdges(child);
    }

    /* access modifiers changed from: package-private */
    public void addPreDrawListener() {
        OnPreDrawListener onPreDrawListener;
        if (this.mIsAttachedToWindow) {
            if (this.mOnPreDrawListener == null) {
                new OnPreDrawListener(this);
                this.mOnPreDrawListener = onPreDrawListener;
            }
            getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = true;
    }

    /* access modifiers changed from: package-private */
    public void removePreDrawListener() {
        if (this.mIsAttachedToWindow && this.mOnPreDrawListener != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = false;
    }

    /* access modifiers changed from: package-private */
    public void offsetChildToAnchor(View view, int i) {
        Behavior b;
        View child = view;
        int layoutDirection = i;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (lp.mAnchorView != null) {
            Rect anchorRect = acquireTempRect();
            Rect childRect = acquireTempRect();
            Rect desiredChildRect = acquireTempRect();
            getDescendantRect(lp.mAnchorView, anchorRect);
            getChildRect(child, false, childRect);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            getDesiredAnchoredChildRectWithoutConstraints(child, layoutDirection, anchorRect, desiredChildRect, lp, childWidth, childHeight);
            boolean changed = (desiredChildRect.left == childRect.left && desiredChildRect.top == childRect.top) ? false : true;
            constrainChildRect(lp, desiredChildRect, childWidth, childHeight);
            int dx = desiredChildRect.left - childRect.left;
            int dy = desiredChildRect.top - childRect.top;
            if (dx != 0) {
                ViewCompat.offsetLeftAndRight(child, dx);
            }
            if (dy != 0) {
                ViewCompat.offsetTopAndBottom(child, dy);
            }
            if (changed && (b = lp.getBehavior()) != null) {
                boolean onDependentViewChanged = b.onDependentViewChanged(this, child, lp.mAnchorView);
            }
            releaseTempRect(anchorRect);
            releaseTempRect(childRect);
            releaseTempRect(desiredChildRect);
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean isPointInChildBounds(@NonNull View child, int x, int y) {
        Rect r = acquireTempRect();
        getDescendantRect(child, r);
        try {
            boolean contains = r.contains(x, y);
            releaseTempRect(r);
            return contains;
        } catch (Throwable th) {
            Throwable th2 = th;
            releaseTempRect(r);
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean doViewsOverlap(@NonNull View view, @NonNull View view2) {
        View first = view;
        View second = view2;
        if (first.getVisibility() != 0 || second.getVisibility() != 0) {
            return false;
        }
        Rect firstRect = acquireTempRect();
        getChildRect(first, first.getParent() != this, firstRect);
        Rect secondRect = acquireTempRect();
        getChildRect(second, second.getParent() != this, secondRect);
        try {
            boolean z = firstRect.left <= secondRect.right && firstRect.top <= secondRect.bottom && firstRect.right >= secondRect.left && firstRect.bottom >= secondRect.top;
            releaseTempRect(firstRect);
            releaseTempRect(secondRect);
            return z;
        } catch (Throwable th) {
            Throwable th2 = th;
            releaseTempRect(firstRect);
            releaseTempRect(secondRect);
            throw th2;
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        LayoutParams layoutParams;
        new LayoutParams(getContext(), attrs);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutParams layoutParams2;
        LayoutParams layoutParams3;
        LayoutParams layoutParams4;
        ViewGroup.LayoutParams p = layoutParams;
        if (p instanceof LayoutParams) {
            new LayoutParams((LayoutParams) p);
            return layoutParams4;
        } else if (p instanceof ViewGroup.MarginLayoutParams) {
            new LayoutParams((ViewGroup.MarginLayoutParams) p);
            return layoutParams3;
        } else {
            new LayoutParams(p);
            return layoutParams2;
        }
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams;
        new LayoutParams(-2, -2);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        ViewGroup.LayoutParams p = layoutParams;
        return (p instanceof LayoutParams) && super.checkLayoutParams(p);
    }

    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return onStartNestedScroll(child, target, nestedScrollAxes, 0);
    }

    public boolean onStartNestedScroll(View view, View view2, int i, int i2) {
        View child = view;
        View target = view2;
        int axes = i;
        int type = i2;
        boolean handled = false;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View view3 = getChildAt(i3);
            if (view3.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) view3.getLayoutParams();
                Behavior viewBehavior = lp.getBehavior();
                if (viewBehavior != null) {
                    boolean accepted = viewBehavior.onStartNestedScroll(this, view3, child, target, axes, type);
                    handled |= accepted;
                    lp.setNestedScrollAccepted(type, accepted);
                } else {
                    lp.setNestedScrollAccepted(type, false);
                }
            }
        }
        return handled;
    }

    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        onNestedScrollAccepted(child, target, nestedScrollAxes, 0);
    }

    public void onNestedScrollAccepted(View view, View view2, int i, int i2) {
        Behavior viewBehavior;
        View child = view;
        View target = view2;
        int nestedScrollAxes = i;
        int type = i2;
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes, type);
        this.mNestedScrollingTarget = target;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View view3 = getChildAt(i3);
            LayoutParams lp = (LayoutParams) view3.getLayoutParams();
            if (lp.isNestedScrollAccepted(type) && (viewBehavior = lp.getBehavior()) != null) {
                viewBehavior.onNestedScrollAccepted(this, view3, child, target, nestedScrollAxes, type);
            }
        }
    }

    public void onStopNestedScroll(View target) {
        onStopNestedScroll(target, 0);
    }

    public void onStopNestedScroll(View view, int i) {
        View target = view;
        int type = i;
        this.mNestedScrollingParentHelper.onStopNestedScroll(target, type);
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View view2 = getChildAt(i2);
            LayoutParams lp = (LayoutParams) view2.getLayoutParams();
            if (lp.isNestedScrollAccepted(type)) {
                Behavior viewBehavior = lp.getBehavior();
                if (viewBehavior != null) {
                    viewBehavior.onStopNestedScroll(this, view2, target, type);
                }
                lp.resetNestedScroll(type);
                lp.resetChangedAfterNestedScroll();
            }
        }
        this.mNestedScrollingTarget = null;
    }

    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, 0);
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5) {
        Behavior viewBehavior;
        View target = view;
        int dxConsumed = i;
        int dyConsumed = i2;
        int dxUnconsumed = i3;
        int dyUnconsumed = i4;
        int type = i5;
        int childCount = getChildCount();
        boolean accepted = false;
        for (int i6 = 0; i6 < childCount; i6++) {
            View view2 = getChildAt(i6);
            if (view2.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) view2.getLayoutParams();
                if (lp.isNestedScrollAccepted(type) && (viewBehavior = lp.getBehavior()) != null) {
                    viewBehavior.onNestedScroll(this, view2, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
                    accepted = true;
                }
            }
        }
        if (accepted) {
            onChildViewsChanged(1);
        }
    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        onNestedPreScroll(target, dx, dy, consumed, 0);
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr, int i3) {
        Behavior viewBehavior;
        int min;
        int min2;
        View target = view;
        int dx = i;
        int dy = i2;
        int[] consumed = iArr;
        int type = i3;
        int xConsumed = 0;
        int yConsumed = 0;
        boolean accepted = false;
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View view2 = getChildAt(i4);
            if (view2.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) view2.getLayoutParams();
                if (lp.isNestedScrollAccepted(type) && (viewBehavior = lp.getBehavior()) != null) {
                    int[] iArr2 = this.mTempIntPair;
                    this.mTempIntPair[1] = 0;
                    iArr2[0] = 0;
                    viewBehavior.onNestedPreScroll(this, view2, target, dx, dy, this.mTempIntPair, type);
                    if (dx > 0) {
                        min = Math.max(xConsumed, this.mTempIntPair[0]);
                    } else {
                        min = Math.min(xConsumed, this.mTempIntPair[0]);
                    }
                    xConsumed = min;
                    if (dy > 0) {
                        min2 = Math.max(yConsumed, this.mTempIntPair[1]);
                    } else {
                        min2 = Math.min(yConsumed, this.mTempIntPair[1]);
                    }
                    yConsumed = min2;
                    accepted = true;
                }
            }
        }
        consumed[0] = xConsumed;
        consumed[1] = yConsumed;
        if (accepted) {
            onChildViewsChanged(1);
        }
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        Behavior viewBehavior;
        View target = view;
        float velocityX = f;
        float velocityY = f2;
        boolean consumed = z;
        boolean handled = false;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view2 = getChildAt(i);
            if (view2.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) view2.getLayoutParams();
                if (lp.isNestedScrollAccepted(0) && (viewBehavior = lp.getBehavior()) != null) {
                    handled |= viewBehavior.onNestedFling(this, view2, target, velocityX, velocityY, consumed);
                }
            }
        }
        if (handled) {
            onChildViewsChanged(1);
        }
        return handled;
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        Behavior viewBehavior;
        View target = view;
        float velocityX = f;
        float velocityY = f2;
        boolean handled = false;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view2 = getChildAt(i);
            if (view2.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) view2.getLayoutParams();
                if (lp.isNestedScrollAccepted(0) && (viewBehavior = lp.getBehavior()) != null) {
                    handled |= viewBehavior.onNestedPreFling(this, view2, target, velocityX, velocityY);
                }
            }
        }
        return handled;
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    class OnPreDrawListener implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ CoordinatorLayout this$0;

        OnPreDrawListener(CoordinatorLayout this$02) {
            this.this$0 = this$02;
        }

        public boolean onPreDraw() {
            this.this$0.onChildViewsChanged(0);
            return true;
        }
    }

    static class ViewElevationComparator implements Comparator<View> {
        ViewElevationComparator() {
        }

        public int compare(View lhs, View rhs) {
            float lz = ViewCompat.getZ(lhs);
            float rz = ViewCompat.getZ(rhs);
            if (lz > rz) {
                return -1;
            }
            if (lz < rz) {
                return 1;
            }
            return 0;
        }
    }

    public static abstract class Behavior<V extends View> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            Context context2 = context;
            AttributeSet attributeSet2 = attributeSet;
        }

        public void onAttachedToLayoutParams(@NonNull LayoutParams params) {
        }

        public void onDetachedFromLayoutParams() {
        }

        public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull MotionEvent motionEvent) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V v2 = v;
            MotionEvent motionEvent2 = motionEvent;
            return false;
        }

        public boolean onTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull MotionEvent motionEvent) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V v2 = v;
            MotionEvent motionEvent2 = motionEvent;
            return false;
        }

        @ColorInt
        public int getScrimColor(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V v2 = v;
            return -16777216;
        }

        @FloatRange(from = 0.0d, mo103to = 1.0d)
        public float getScrimOpacity(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V v2 = v;
            return 0.0f;
        }

        public boolean blocksInteractionBelow(@NonNull CoordinatorLayout parent, @NonNull V child) {
            return getScrimOpacity(parent, child) > 0.0f;
        }

        public boolean layoutDependsOn(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V v2 = v;
            View view2 = view;
            return false;
        }

        public boolean onDependentViewChanged(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V v2 = v;
            View view2 = view;
            return false;
        }

        public void onDependentViewRemoved(@NonNull CoordinatorLayout parent, @NonNull V v, @NonNull View dependency) {
        }

        public boolean onMeasureChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, int i, int i2, int i3, int i4) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V v2 = v;
            int i5 = i;
            int i6 = i2;
            int i7 = i3;
            int i8 = i4;
            return false;
        }

        public boolean onLayoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, int i) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V v2 = v;
            int i2 = i;
            return false;
        }

        public static void setTag(@NonNull View child, @Nullable Object tag) {
            ((LayoutParams) child.getLayoutParams()).mBehaviorTag = tag;
        }

        @Nullable
        public static Object getTag(@NonNull View child) {
            return ((LayoutParams) child.getLayoutParams()).mBehaviorTag;
        }

        @Deprecated
        public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, @NonNull View view2, int i) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V v2 = v;
            View view3 = view;
            View view4 = view2;
            int i2 = i;
            return false;
        }

        public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, @NonNull View view2, int i, int type) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V child = v;
            View directTargetChild = view;
            View target = view2;
            int axes = i;
            if (type == 0) {
                return onStartNestedScroll(coordinatorLayout2, child, directTargetChild, target, axes);
            }
            return false;
        }

        @Deprecated
        public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View directTargetChild, @NonNull View target, int axes) {
        }

        public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, @NonNull View view2, int i, int type) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V child = v;
            View directTargetChild = view;
            View target = view2;
            int axes = i;
            if (type == 0) {
                onNestedScrollAccepted(coordinatorLayout2, child, directTargetChild, target, axes);
            }
        }

        @Deprecated
        public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View target) {
        }

        public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, int type) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V child = v;
            View target = view;
            if (type == 0) {
                onStopNestedScroll(coordinatorLayout2, child, target);
            }
        }

        @Deprecated
        public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        }

        public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, int i, int i2, int i3, int i4, int type) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V child = v;
            View target = view;
            int dxConsumed = i;
            int dyConsumed = i2;
            int dxUnconsumed = i3;
            int dyUnconsumed = i4;
            if (type == 0) {
                onNestedScroll(coordinatorLayout2, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
            }
        }

        @Deprecated
        public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        }

        public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, int i, int i2, @NonNull int[] iArr, int type) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V child = v;
            View target = view;
            int dx = i;
            int dy = i2;
            int[] consumed = iArr;
            if (type == 0) {
                onNestedPreScroll(coordinatorLayout2, child, target, dx, dy, consumed);
            }
        }

        public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, float f, float f2, boolean z) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V v2 = v;
            View view2 = view;
            float f3 = f;
            float f4 = f2;
            boolean z2 = z;
            return false;
        }

        public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, float f, float f2) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V v2 = v;
            View view2 = view;
            float f3 = f;
            float f4 = f2;
            return false;
        }

        @NonNull
        public WindowInsetsCompat onApplyWindowInsets(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull WindowInsetsCompat insets) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V v2 = v;
            return insets;
        }

        public boolean onRequestChildRectangleOnScreen(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull Rect rect, boolean z) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V v2 = v;
            Rect rect2 = rect;
            boolean z2 = z;
            return false;
        }

        public void onRestoreInstanceState(@NonNull CoordinatorLayout parent, @NonNull V v, @NonNull Parcelable state) {
        }

        @Nullable
        public Parcelable onSaveInstanceState(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V v2 = v;
            return View.BaseSavedState.EMPTY_STATE;
        }

        public boolean getInsetDodgeRect(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull Rect rect) {
            CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
            V v2 = v;
            Rect rect2 = rect;
            return false;
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int anchorGravity = 0;
        public int dodgeInsetEdges = 0;
        public int gravity = 0;
        public int insetEdge = 0;
        public int keyline = -1;
        View mAnchorDirectChild;
        int mAnchorId = -1;
        View mAnchorView;
        Behavior mBehavior;
        boolean mBehaviorResolved = false;
        Object mBehaviorTag;
        private boolean mDidAcceptNestedScrollNonTouch;
        private boolean mDidAcceptNestedScrollTouch;
        private boolean mDidBlockInteraction;
        private boolean mDidChangeAfterNestedScroll;
        int mInsetOffsetX;
        int mInsetOffsetY;
        final Rect mLastChildRect;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height) {
            super(width, height);
            Rect rect;
            new Rect();
            this.mLastChildRect = rect;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        LayoutParams(@android.support.annotation.NonNull android.content.Context r11, @android.support.annotation.Nullable android.util.AttributeSet r12) {
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
                r5 = 0
                r4.mBehaviorResolved = r5
                r4 = r0
                r5 = 0
                r4.gravity = r5
                r4 = r0
                r5 = 0
                r4.anchorGravity = r5
                r4 = r0
                r5 = -1
                r4.keyline = r5
                r4 = r0
                r5 = -1
                r4.mAnchorId = r5
                r4 = r0
                r5 = 0
                r4.insetEdge = r5
                r4 = r0
                r5 = 0
                r4.dodgeInsetEdges = r5
                r4 = r0
                android.graphics.Rect r5 = new android.graphics.Rect
                r9 = r5
                r5 = r9
                r6 = r9
                r6.<init>()
                r4.mLastChildRect = r5
                r4 = r1
                r5 = r2
                int[] r6 = android.support.coordinatorlayout.C0031R.styleable.CoordinatorLayout_Layout
                android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r6)
                r3 = r4
                r4 = r0
                r5 = r3
                int r6 = android.support.coordinatorlayout.C0031R.styleable.CoordinatorLayout_Layout_android_layout_gravity
                r7 = 0
                int r5 = r5.getInteger(r6, r7)
                r4.gravity = r5
                r4 = r0
                r5 = r3
                int r6 = android.support.coordinatorlayout.C0031R.styleable.CoordinatorLayout_Layout_layout_anchor
                r7 = -1
                int r5 = r5.getResourceId(r6, r7)
                r4.mAnchorId = r5
                r4 = r0
                r5 = r3
                int r6 = android.support.coordinatorlayout.C0031R.styleable.CoordinatorLayout_Layout_layout_anchorGravity
                r7 = 0
                int r5 = r5.getInteger(r6, r7)
                r4.anchorGravity = r5
                r4 = r0
                r5 = r3
                int r6 = android.support.coordinatorlayout.C0031R.styleable.CoordinatorLayout_Layout_layout_keyline
                r7 = -1
                int r5 = r5.getInteger(r6, r7)
                r4.keyline = r5
                r4 = r0
                r5 = r3
                int r6 = android.support.coordinatorlayout.C0031R.styleable.CoordinatorLayout_Layout_layout_insetEdge
                r7 = 0
                int r5 = r5.getInt(r6, r7)
                r4.insetEdge = r5
                r4 = r0
                r5 = r3
                int r6 = android.support.coordinatorlayout.C0031R.styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges
                r7 = 0
                int r5 = r5.getInt(r6, r7)
                r4.dodgeInsetEdges = r5
                r4 = r0
                r5 = r3
                int r6 = android.support.coordinatorlayout.C0031R.styleable.CoordinatorLayout_Layout_layout_behavior
                boolean r5 = r5.hasValue(r6)
                r4.mBehaviorResolved = r5
                r4 = r0
                boolean r4 = r4.mBehaviorResolved
                if (r4 == 0) goto L_0x009a
                r4 = r0
                r5 = r1
                r6 = r2
                r7 = r3
                int r8 = android.support.coordinatorlayout.C0031R.styleable.CoordinatorLayout_Layout_layout_behavior
                java.lang.String r7 = r7.getString(r8)
                android.support.design.widget.CoordinatorLayout$Behavior r5 = android.support.design.widget.CoordinatorLayout.parseBehavior(r5, r6, r7)
                r4.mBehavior = r5
            L_0x009a:
                r4 = r3
                r4.recycle()
                r4 = r0
                android.support.design.widget.CoordinatorLayout$Behavior r4 = r4.mBehavior
                if (r4 == 0) goto L_0x00aa
                r4 = r0
                android.support.design.widget.CoordinatorLayout$Behavior r4 = r4.mBehavior
                r5 = r0
                r4.onAttachedToLayoutParams(r5)
            L_0x00aa:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.CoordinatorLayout.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(LayoutParams p) {
            super(p);
            Rect rect;
            new Rect();
            this.mLastChildRect = rect;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.MarginLayoutParams p) {
            super(p);
            Rect rect;
            new Rect();
            this.mLastChildRect = rect;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
            Rect rect;
            new Rect();
            this.mLastChildRect = rect;
        }

        @IdRes
        public int getAnchorId() {
            return this.mAnchorId;
        }

        public void setAnchorId(@IdRes int id) {
            invalidateAnchor();
            this.mAnchorId = id;
        }

        @Nullable
        public Behavior getBehavior() {
            return this.mBehavior;
        }

        public void setBehavior(@Nullable Behavior behavior) {
            Behavior behavior2 = behavior;
            if (this.mBehavior != behavior2) {
                if (this.mBehavior != null) {
                    this.mBehavior.onDetachedFromLayoutParams();
                }
                this.mBehavior = behavior2;
                this.mBehaviorTag = null;
                this.mBehaviorResolved = true;
                if (behavior2 != null) {
                    behavior2.onAttachedToLayoutParams(this);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void setLastChildRect(Rect r) {
            this.mLastChildRect.set(r);
        }

        /* access modifiers changed from: package-private */
        public Rect getLastChildRect() {
            return this.mLastChildRect;
        }

        /* access modifiers changed from: package-private */
        public boolean checkAnchorChanged() {
            return this.mAnchorView == null && this.mAnchorId != -1;
        }

        /* access modifiers changed from: package-private */
        public boolean didBlockInteraction() {
            if (this.mBehavior == null) {
                this.mDidBlockInteraction = false;
            }
            return this.mDidBlockInteraction;
        }

        /* access modifiers changed from: package-private */
        public boolean isBlockingInteractionBelow(CoordinatorLayout coordinatorLayout, View view) {
            CoordinatorLayout parent = coordinatorLayout;
            View child = view;
            if (this.mDidBlockInteraction) {
                return true;
            }
            boolean blocksInteractionBelow = this.mDidBlockInteraction | (this.mBehavior != null ? this.mBehavior.blocksInteractionBelow(parent, child) : false);
            this.mDidBlockInteraction = blocksInteractionBelow;
            return blocksInteractionBelow;
        }

        /* access modifiers changed from: package-private */
        public void resetTouchBehaviorTracking() {
            this.mDidBlockInteraction = false;
        }

        /* access modifiers changed from: package-private */
        public void resetNestedScroll(int type) {
            setNestedScrollAccepted(type, false);
        }

        /* access modifiers changed from: package-private */
        public void setNestedScrollAccepted(int type, boolean z) {
            boolean accept = z;
            switch (type) {
                case 0:
                    this.mDidAcceptNestedScrollTouch = accept;
                    return;
                case 1:
                    this.mDidAcceptNestedScrollNonTouch = accept;
                    return;
                default:
                    return;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isNestedScrollAccepted(int type) {
            switch (type) {
                case 0:
                    return this.mDidAcceptNestedScrollTouch;
                case 1:
                    return this.mDidAcceptNestedScrollNonTouch;
                default:
                    return false;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean getChangedAfterNestedScroll() {
            return this.mDidChangeAfterNestedScroll;
        }

        /* access modifiers changed from: package-private */
        public void setChangedAfterNestedScroll(boolean changed) {
            boolean z = changed;
            this.mDidChangeAfterNestedScroll = z;
        }

        /* access modifiers changed from: package-private */
        public void resetChangedAfterNestedScroll() {
            this.mDidChangeAfterNestedScroll = false;
        }

        /* access modifiers changed from: package-private */
        public boolean dependsOn(CoordinatorLayout coordinatorLayout, View child, View view) {
            CoordinatorLayout parent = coordinatorLayout;
            View dependency = view;
            return dependency == this.mAnchorDirectChild || shouldDodge(dependency, ViewCompat.getLayoutDirection(parent)) || (this.mBehavior != null && this.mBehavior.layoutDependsOn(parent, child, dependency));
        }

        /* access modifiers changed from: package-private */
        public void invalidateAnchor() {
            this.mAnchorDirectChild = null;
            this.mAnchorView = null;
        }

        /* access modifiers changed from: package-private */
        public View findAnchorView(CoordinatorLayout coordinatorLayout, View view) {
            CoordinatorLayout parent = coordinatorLayout;
            View forChild = view;
            if (this.mAnchorId == -1) {
                this.mAnchorDirectChild = null;
                this.mAnchorView = null;
                return null;
            }
            if (this.mAnchorView == null || !verifyAnchorView(forChild, parent)) {
                resolveAnchorView(forChild, parent);
            }
            return this.mAnchorView;
        }

        private void resolveAnchorView(View view, CoordinatorLayout coordinatorLayout) {
            Throwable th;
            StringBuilder sb;
            Throwable th2;
            Throwable th3;
            View forChild = view;
            CoordinatorLayout parent = coordinatorLayout;
            this.mAnchorView = parent.findViewById(this.mAnchorId);
            if (this.mAnchorView != null) {
                if (this.mAnchorView != parent) {
                    View directChild = this.mAnchorView;
                    ViewParent parent2 = this.mAnchorView.getParent();
                    while (true) {
                        ViewParent p = parent2;
                        if (p == parent || p == null) {
                            this.mAnchorDirectChild = directChild;
                        } else if (p != forChild) {
                            if (p instanceof View) {
                                directChild = (View) p;
                            }
                            parent2 = p.getParent();
                        } else if (parent.isInEditMode()) {
                            this.mAnchorDirectChild = null;
                            this.mAnchorView = null;
                            return;
                        } else {
                            Throwable th4 = th2;
                            new IllegalStateException("Anchor must not be a descendant of the anchored view");
                            throw th4;
                        }
                    }
                    this.mAnchorDirectChild = directChild;
                } else if (parent.isInEditMode()) {
                    this.mAnchorDirectChild = null;
                    this.mAnchorView = null;
                } else {
                    Throwable th5 = th3;
                    new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
                    throw th5;
                }
            } else if (parent.isInEditMode()) {
                this.mAnchorDirectChild = null;
                this.mAnchorView = null;
            } else {
                Throwable th6 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("Could not find CoordinatorLayout descendant view with id ").append(parent.getResources().getResourceName(this.mAnchorId)).append(" to anchor view ").append(forChild).toString());
                throw th6;
            }
        }

        private boolean verifyAnchorView(View view, CoordinatorLayout coordinatorLayout) {
            View forChild = view;
            CoordinatorLayout parent = coordinatorLayout;
            if (this.mAnchorView.getId() != this.mAnchorId) {
                return false;
            }
            View directChild = this.mAnchorView;
            ViewParent parent2 = this.mAnchorView.getParent();
            while (true) {
                ViewParent p = parent2;
                if (p == parent) {
                    this.mAnchorDirectChild = directChild;
                    return true;
                } else if (p == null || p == forChild) {
                    this.mAnchorDirectChild = null;
                    this.mAnchorView = null;
                } else {
                    if (p instanceof View) {
                        directChild = (View) p;
                    }
                    parent2 = p.getParent();
                }
            }
            this.mAnchorDirectChild = null;
            this.mAnchorView = null;
            return false;
        }

        private boolean shouldDodge(View other, int i) {
            int layoutDirection = i;
            int absInset = GravityCompat.getAbsoluteGravity(((LayoutParams) other.getLayoutParams()).insetEdge, layoutDirection);
            return absInset != 0 && (absInset & GravityCompat.getAbsoluteGravity(this.dodgeInsetEdges, layoutDirection)) == absInset;
        }
    }

    private class HierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        final /* synthetic */ CoordinatorLayout this$0;

        HierarchyChangeListener(CoordinatorLayout coordinatorLayout) {
            this.this$0 = coordinatorLayout;
        }

        public void onChildViewAdded(View view, View view2) {
            View parent = view;
            View child = view2;
            if (this.this$0.mOnHierarchyChangeListener != null) {
                this.this$0.mOnHierarchyChangeListener.onChildViewAdded(parent, child);
            }
        }

        public void onChildViewRemoved(View view, View view2) {
            View parent = view;
            View child = view2;
            this.this$0.onChildViewsChanged(2);
            if (this.this$0.mOnHierarchyChangeListener != null) {
                this.this$0.mOnHierarchyChangeListener.onChildViewRemoved(parent, child);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable savedState;
        Parcelable state = parcelable;
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        SparseArray<Parcelable> behaviorStates = ss.behaviorStates;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int childId = child.getId();
            Behavior b = getResolvedLayoutParams(child).getBehavior();
            if (!(childId == -1 || b == null || (savedState = behaviorStates.get(childId)) == null)) {
                b.onRestoreInstanceState(this, child, savedState);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Parcelable onSaveInstanceState() {
        /*
            r14 = this;
            r0 = r14
            android.support.design.widget.CoordinatorLayout$SavedState r10 = new android.support.design.widget.CoordinatorLayout$SavedState
            r13 = r10
            r10 = r13
            r11 = r13
            r12 = r0
            android.os.Parcelable r12 = super.onSaveInstanceState()
            r11.<init>(r12)
            r1 = r10
            android.util.SparseArray r10 = new android.util.SparseArray
            r13 = r10
            r10 = r13
            r11 = r13
            r11.<init>()
            r2 = r10
            r10 = 0
            r3 = r10
            r10 = r0
            int r10 = r10.getChildCount()
            r4 = r10
        L_0x0020:
            r10 = r3
            r11 = r4
            if (r10 >= r11) goto L_0x005a
            r10 = r0
            r11 = r3
            android.view.View r10 = r10.getChildAt(r11)
            r5 = r10
            r10 = r5
            int r10 = r10.getId()
            r6 = r10
            r10 = r5
            android.view.ViewGroup$LayoutParams r10 = r10.getLayoutParams()
            android.support.design.widget.CoordinatorLayout$LayoutParams r10 = (android.support.design.widget.CoordinatorLayout.LayoutParams) r10
            r7 = r10
            r10 = r7
            android.support.design.widget.CoordinatorLayout$Behavior r10 = r10.getBehavior()
            r8 = r10
            r10 = r6
            r11 = -1
            if (r10 == r11) goto L_0x0057
            r10 = r8
            if (r10 == 0) goto L_0x0057
            r10 = r8
            r11 = r0
            r12 = r5
            android.os.Parcelable r10 = r10.onSaveInstanceState(r11, r12)
            r9 = r10
            r10 = r9
            if (r10 == 0) goto L_0x0057
            r10 = r2
            r11 = r6
            r12 = r9
            r10.append(r11, r12)
        L_0x0057:
            int r3 = r3 + 1
            goto L_0x0020
        L_0x005a:
            r10 = r1
            r11 = r2
            r10.behaviorStates = r11
            r10 = r1
            r0 = r10
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.CoordinatorLayout.onSaveInstanceState():android.os.Parcelable");
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        View child = view;
        Rect rectangle = rect;
        boolean immediate = z;
        Behavior behavior = ((LayoutParams) child.getLayoutParams()).getBehavior();
        if (behavior == null || !behavior.onRequestChildRectangleOnScreen(this, child, rectangle, immediate)) {
            return super.requestChildRectangleOnScreen(child, rectangle, immediate);
        }
        return true;
    }

    private void setupForInsets() {
        OnApplyWindowInsetsListener onApplyWindowInsetsListener;
        if (Build.VERSION.SDK_INT >= 21) {
            if (ViewCompat.getFitsSystemWindows(this)) {
                if (this.mApplyWindowInsetsListener == null) {
                    new OnApplyWindowInsetsListener(this) {
                        final /* synthetic */ CoordinatorLayout this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat insets) {
                            View view2 = view;
                            return this.this$0.setWindowInsets(insets);
                        }
                    };
                    this.mApplyWindowInsetsListener = onApplyWindowInsetsListener;
                }
                ViewCompat.setOnApplyWindowInsetsListener(this, this.mApplyWindowInsetsListener);
                setSystemUiVisibility(1280);
                return;
            }
            ViewCompat.setOnApplyWindowInsetsListener(this, (OnApplyWindowInsetsListener) null);
        }
    }

    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR;
        SparseArray<Parcelable> behaviorStates;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SavedState(android.os.Parcel r13, java.lang.ClassLoader r14) {
            /*
                r12 = this;
                r0 = r12
                r1 = r13
                r2 = r14
                r7 = r0
                r8 = r1
                r9 = r2
                r7.<init>(r8, r9)
                r7 = r1
                int r7 = r7.readInt()
                r3 = r7
                r7 = r3
                int[] r7 = new int[r7]
                r4 = r7
                r7 = r1
                r8 = r4
                r7.readIntArray(r8)
                r7 = r1
                r8 = r2
                android.os.Parcelable[] r7 = r7.readParcelableArray(r8)
                r5 = r7
                r7 = r0
                android.util.SparseArray r8 = new android.util.SparseArray
                r11 = r8
                r8 = r11
                r9 = r11
                r10 = r3
                r9.<init>(r10)
                r7.behaviorStates = r8
                r7 = 0
                r6 = r7
            L_0x002d:
                r7 = r6
                r8 = r3
                if (r7 >= r8) goto L_0x0042
                r7 = r0
                android.util.SparseArray<android.os.Parcelable> r7 = r7.behaviorStates
                r8 = r4
                r9 = r6
                r8 = r8[r9]
                r9 = r5
                r10 = r6
                r9 = r9[r10]
                r7.append(r8, r9)
                int r6 = r6 + 1
                goto L_0x002d
            L_0x0042:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.CoordinatorLayout.SavedState.<init>(android.os.Parcel, java.lang.ClassLoader):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel parcel, int i) {
            Parcel dest = parcel;
            int flags = i;
            super.writeToParcel(dest, flags);
            int size = this.behaviorStates != null ? this.behaviorStates.size() : 0;
            dest.writeInt(size);
            int[] ids = new int[size];
            Parcelable[] states = new Parcelable[size];
            for (int i2 = 0; i2 < size; i2++) {
                ids[i2] = this.behaviorStates.keyAt(i2);
                states[i2] = this.behaviorStates.valueAt(i2);
            }
            dest.writeIntArray(ids);
            dest.writeParcelableArray(states, flags);
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
