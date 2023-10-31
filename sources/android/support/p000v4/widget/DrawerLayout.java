package android.support.p000v4.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.view.AbsSavedState;
import android.support.p000v4.view.AccessibilityDelegateCompat;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p000v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import gnu.expr.Declaration;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v4.widget.DrawerLayout */
public class DrawerLayout extends ViewGroup {
    private static final boolean ALLOW_EDGE_LOCK = false;
    static final boolean CAN_HIDE_DESCENDANTS = (Build.VERSION.SDK_INT >= 19);
    private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
    private static final int DEFAULT_SCRIM_COLOR = -1728053248;
    private static final int DRAWER_ELEVATION = 10;
    static final int[] LAYOUT_ATTRS = {16842931};
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNDEFINED = 3;
    public static final int LOCK_MODE_UNLOCKED = 0;
    private static final int MIN_DRAWER_MARGIN = 64;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final int PEEK_DELAY = 160;
    private static final boolean SET_DRAWER_SHADOW_FROM_ELEVATION = (Build.VERSION.SDK_INT >= 21);
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "DrawerLayout";
    private static final int[] THEME_ATTRS = {16843828};
    private static final float TOUCH_SLOP_SENSITIVITY = 1.0f;
    private final ChildAccessibilityDelegate mChildAccessibilityDelegate;
    private Rect mChildHitRect;
    private Matrix mChildInvertedMatrix;
    private boolean mChildrenCanceledTouch;
    private boolean mDisallowInterceptRequested;
    private boolean mDrawStatusBarBackground;
    private float mDrawerElevation;
    private int mDrawerState;
    private boolean mFirstLayout;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private Object mLastInsets;
    private final ViewDragCallback mLeftCallback;
    private final ViewDragHelper mLeftDragger;
    @Nullable
    private DrawerListener mListener;
    private List<DrawerListener> mListeners;
    private int mLockModeEnd;
    private int mLockModeLeft;
    private int mLockModeRight;
    private int mLockModeStart;
    private int mMinDrawerMargin;
    private final ArrayList<View> mNonDrawerViews;
    private final ViewDragCallback mRightCallback;
    private final ViewDragHelper mRightDragger;
    private int mScrimColor;
    private float mScrimOpacity;
    private Paint mScrimPaint;
    private Drawable mShadowEnd;
    private Drawable mShadowLeft;
    private Drawable mShadowLeftResolved;
    private Drawable mShadowRight;
    private Drawable mShadowRightResolved;
    private Drawable mShadowStart;
    private Drawable mStatusBarBackground;
    private CharSequence mTitleLeft;
    private CharSequence mTitleRight;

    /* renamed from: android.support.v4.widget.DrawerLayout$DrawerListener */
    public interface DrawerListener {
        void onDrawerClosed(@NonNull View view);

        void onDrawerOpened(@NonNull View view);

        void onDrawerSlide(@NonNull View view, float f);

        void onDrawerStateChanged(int i);
    }

    /* renamed from: android.support.v4.widget.DrawerLayout$SimpleDrawerListener */
    public static abstract class SimpleDrawerListener implements DrawerListener {
        public SimpleDrawerListener() {
        }

        public void onDrawerSlide(View drawerView, float slideOffset) {
        }

        public void onDrawerOpened(View drawerView) {
        }

        public void onDrawerClosed(View drawerView) {
        }

        public void onDrawerStateChanged(int newState) {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DrawerLayout(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DrawerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DrawerLayout(@android.support.annotation.NonNull android.content.Context r15, @android.support.annotation.Nullable android.util.AttributeSet r16, int r17) {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r8.<init>(r9, r10, r11)
            r8 = r0
            android.support.v4.widget.DrawerLayout$ChildAccessibilityDelegate r9 = new android.support.v4.widget.DrawerLayout$ChildAccessibilityDelegate
            r13 = r9
            r9 = r13
            r10 = r13
            r10.<init>()
            r8.mChildAccessibilityDelegate = r9
            r8 = r0
            r9 = -1728053248(0xffffffff99000000, float:-6.617445E-24)
            r8.mScrimColor = r9
            r8 = r0
            android.graphics.Paint r9 = new android.graphics.Paint
            r13 = r9
            r9 = r13
            r10 = r13
            r10.<init>()
            r8.mScrimPaint = r9
            r8 = r0
            r9 = 1
            r8.mFirstLayout = r9
            r8 = r0
            r9 = 3
            r8.mLockModeLeft = r9
            r8 = r0
            r9 = 3
            r8.mLockModeRight = r9
            r8 = r0
            r9 = 3
            r8.mLockModeStart = r9
            r8 = r0
            r9 = 3
            r8.mLockModeEnd = r9
            r8 = r0
            r9 = 0
            r8.mShadowStart = r9
            r8 = r0
            r9 = 0
            r8.mShadowEnd = r9
            r8 = r0
            r9 = 0
            r8.mShadowLeft = r9
            r8 = r0
            r9 = 0
            r8.mShadowRight = r9
            r8 = r0
            r9 = 262144(0x40000, float:3.67342E-40)
            r8.setDescendantFocusability(r9)
            r8 = r0
            android.content.res.Resources r8 = r8.getResources()
            android.util.DisplayMetrics r8 = r8.getDisplayMetrics()
            float r8 = r8.density
            r4 = r8
            r8 = r0
            r9 = 1115684864(0x42800000, float:64.0)
            r10 = r4
            float r9 = r9 * r10
            r10 = 1056964608(0x3f000000, float:0.5)
            float r9 = r9 + r10
            int r9 = (int) r9
            r8.mMinDrawerMargin = r9
            r8 = 1137180672(0x43c80000, float:400.0)
            r9 = r4
            float r8 = r8 * r9
            r5 = r8
            r8 = r0
            android.support.v4.widget.DrawerLayout$ViewDragCallback r9 = new android.support.v4.widget.DrawerLayout$ViewDragCallback
            r13 = r9
            r9 = r13
            r10 = r13
            r11 = r0
            r12 = 3
            r10.<init>(r11, r12)
            r8.mLeftCallback = r9
            r8 = r0
            android.support.v4.widget.DrawerLayout$ViewDragCallback r9 = new android.support.v4.widget.DrawerLayout$ViewDragCallback
            r13 = r9
            r9 = r13
            r10 = r13
            r11 = r0
            r12 = 5
            r10.<init>(r11, r12)
            r8.mRightCallback = r9
            r8 = r0
            r9 = r0
            r10 = 1065353216(0x3f800000, float:1.0)
            r11 = r0
            android.support.v4.widget.DrawerLayout$ViewDragCallback r11 = r11.mLeftCallback
            android.support.v4.widget.ViewDragHelper r9 = android.support.p000v4.widget.ViewDragHelper.create(r9, r10, r11)
            r8.mLeftDragger = r9
            r8 = r0
            android.support.v4.widget.ViewDragHelper r8 = r8.mLeftDragger
            r9 = 1
            r8.setEdgeTrackingEnabled(r9)
            r8 = r0
            android.support.v4.widget.ViewDragHelper r8 = r8.mLeftDragger
            r9 = r5
            r8.setMinVelocity(r9)
            r8 = r0
            android.support.v4.widget.DrawerLayout$ViewDragCallback r8 = r8.mLeftCallback
            r9 = r0
            android.support.v4.widget.ViewDragHelper r9 = r9.mLeftDragger
            r8.setDragger(r9)
            r8 = r0
            r9 = r0
            r10 = 1065353216(0x3f800000, float:1.0)
            r11 = r0
            android.support.v4.widget.DrawerLayout$ViewDragCallback r11 = r11.mRightCallback
            android.support.v4.widget.ViewDragHelper r9 = android.support.p000v4.widget.ViewDragHelper.create(r9, r10, r11)
            r8.mRightDragger = r9
            r8 = r0
            android.support.v4.widget.ViewDragHelper r8 = r8.mRightDragger
            r9 = 2
            r8.setEdgeTrackingEnabled(r9)
            r8 = r0
            android.support.v4.widget.ViewDragHelper r8 = r8.mRightDragger
            r9 = r5
            r8.setMinVelocity(r9)
            r8 = r0
            android.support.v4.widget.DrawerLayout$ViewDragCallback r8 = r8.mRightCallback
            r9 = r0
            android.support.v4.widget.ViewDragHelper r9 = r9.mRightDragger
            r8.setDragger(r9)
            r8 = r0
            r9 = 1
            r8.setFocusableInTouchMode(r9)
            r8 = r0
            r9 = 1
            android.support.p000v4.view.ViewCompat.setImportantForAccessibility(r8, r9)
            r8 = r0
            android.support.v4.widget.DrawerLayout$AccessibilityDelegate r9 = new android.support.v4.widget.DrawerLayout$AccessibilityDelegate
            r13 = r9
            r9 = r13
            r10 = r13
            r11 = r0
            r10.<init>(r11)
            android.support.p000v4.view.ViewCompat.setAccessibilityDelegate(r8, r9)
            r8 = r0
            r9 = 0
            r8.setMotionEventSplittingEnabled(r9)
            r8 = r0
            boolean r8 = android.support.p000v4.view.ViewCompat.getFitsSystemWindows(r8)
            if (r8 == 0) goto L_0x0121
            int r8 = android.os.Build.VERSION.SDK_INT
            r9 = 21
            if (r8 < r9) goto L_0x013c
            r8 = r0
            android.support.v4.widget.DrawerLayout$1 r9 = new android.support.v4.widget.DrawerLayout$1
            r13 = r9
            r9 = r13
            r10 = r13
            r11 = r0
            r10.<init>(r11)
            r8.setOnApplyWindowInsetsListener(r9)
            r8 = r0
            r9 = 1280(0x500, float:1.794E-42)
            r8.setSystemUiVisibility(r9)
            r8 = r1
            int[] r9 = THEME_ATTRS
            android.content.res.TypedArray r8 = r8.obtainStyledAttributes(r9)
            r6 = r8
            r8 = r0
            r9 = r6
            r10 = 0
            android.graphics.drawable.Drawable r9 = r9.getDrawable(r10)     // Catch:{ all -> 0x0134 }
            r8.mStatusBarBackground = r9     // Catch:{ all -> 0x0134 }
            r8 = r6
            r8.recycle()
        L_0x0121:
            r8 = r0
            r9 = 1092616192(0x41200000, float:10.0)
            r10 = r4
            float r9 = r9 * r10
            r8.mDrawerElevation = r9
            r8 = r0
            java.util.ArrayList r9 = new java.util.ArrayList
            r13 = r9
            r9 = r13
            r10 = r13
            r10.<init>()
            r8.mNonDrawerViews = r9
            return
        L_0x0134:
            r8 = move-exception
            r7 = r8
            r8 = r6
            r8.recycle()
            r8 = r7
            throw r8
        L_0x013c:
            r8 = r0
            r9 = 0
            r8.mStatusBarBackground = r9
            goto L_0x0121
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.DrawerLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setDrawerElevation(float elevation) {
        this.mDrawerElevation = elevation;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (isDrawerView(child)) {
                ViewCompat.setElevation(child, this.mDrawerElevation);
            }
        }
    }

    public float getDrawerElevation() {
        if (SET_DRAWER_SHADOW_FROM_ELEVATION) {
            return this.mDrawerElevation;
        }
        return 0.0f;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setChildInsets(Object insets, boolean z) {
        boolean draw = z;
        this.mLastInsets = insets;
        this.mDrawStatusBarBackground = draw;
        setWillNotDraw(!draw && getBackground() == null);
        requestLayout();
    }

    public void setDrawerShadow(Drawable drawable, int i) {
        Drawable shadowDrawable = drawable;
        int gravity = i;
        if (!SET_DRAWER_SHADOW_FROM_ELEVATION) {
            if ((gravity & GravityCompat.START) == 8388611) {
                this.mShadowStart = shadowDrawable;
            } else if ((gravity & GravityCompat.END) == 8388613) {
                this.mShadowEnd = shadowDrawable;
            } else if ((gravity & 3) == 3) {
                this.mShadowLeft = shadowDrawable;
            } else if ((gravity & 5) == 5) {
                this.mShadowRight = shadowDrawable;
            } else {
                return;
            }
            resolveShadowDrawables();
            invalidate();
        }
    }

    public void setDrawerShadow(@DrawableRes int resId, int gravity) {
        setDrawerShadow(ContextCompat.getDrawable(getContext(), resId), gravity);
    }

    public void setScrimColor(@ColorInt int color) {
        this.mScrimColor = color;
        invalidate();
    }

    @Deprecated
    public void setDrawerListener(DrawerListener drawerListener) {
        DrawerListener listener = drawerListener;
        if (this.mListener != null) {
            removeDrawerListener(this.mListener);
        }
        if (listener != null) {
            addDrawerListener(listener);
        }
        this.mListener = listener;
    }

    public void addDrawerListener(@NonNull DrawerListener drawerListener) {
        List<DrawerListener> list;
        DrawerListener listener = drawerListener;
        if (listener != null) {
            if (this.mListeners == null) {
                new ArrayList();
                this.mListeners = list;
            }
            boolean add = this.mListeners.add(listener);
        }
    }

    public void removeDrawerListener(@NonNull DrawerListener drawerListener) {
        DrawerListener listener = drawerListener;
        if (listener != null && this.mListeners != null) {
            boolean remove = this.mListeners.remove(listener);
        }
    }

    public void setDrawerLockMode(int i) {
        int lockMode = i;
        setDrawerLockMode(lockMode, 3);
        setDrawerLockMode(lockMode, 5);
    }

    public void setDrawerLockMode(int i, int i2) {
        int lockMode = i;
        int edgeGravity = i2;
        int absGravity = GravityCompat.getAbsoluteGravity(edgeGravity, ViewCompat.getLayoutDirection(this));
        switch (edgeGravity) {
            case 3:
                this.mLockModeLeft = lockMode;
                break;
            case 5:
                this.mLockModeRight = lockMode;
                break;
            case GravityCompat.START:
                this.mLockModeStart = lockMode;
                break;
            case GravityCompat.END:
                this.mLockModeEnd = lockMode;
                break;
        }
        if (lockMode != 0) {
            (absGravity == 3 ? this.mLeftDragger : this.mRightDragger).cancel();
        }
        switch (lockMode) {
            case 1:
                View toClose = findDrawerWithGravity(absGravity);
                if (toClose != null) {
                    closeDrawer(toClose);
                    return;
                }
                return;
            case 2:
                View toOpen = findDrawerWithGravity(absGravity);
                if (toOpen != null) {
                    openDrawer(toOpen);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void setDrawerLockMode(int i, @NonNull View view) {
        Throwable th;
        StringBuilder sb;
        int lockMode = i;
        View drawerView = view;
        if (!isDrawerView(drawerView)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("View ").append(drawerView).append(" is not a ").append("drawer with appropriate layout_gravity").toString());
            throw th2;
        }
        setDrawerLockMode(lockMode, ((LayoutParams) drawerView.getLayoutParams()).gravity);
    }

    public int getDrawerLockMode(int edgeGravity) {
        int i;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        switch (edgeGravity) {
            case 3:
                if (this.mLockModeLeft != 3) {
                    return this.mLockModeLeft;
                }
                int leftLockMode = layoutDirection == 0 ? this.mLockModeStart : this.mLockModeEnd;
                if (leftLockMode != 3) {
                    return leftLockMode;
                }
                break;
            case 5:
                if (this.mLockModeRight != 3) {
                    return this.mLockModeRight;
                }
                int rightLockMode = layoutDirection == 0 ? this.mLockModeEnd : this.mLockModeStart;
                if (rightLockMode != 3) {
                    return rightLockMode;
                }
                break;
            case GravityCompat.START:
                if (this.mLockModeStart != 3) {
                    return this.mLockModeStart;
                }
                int startLockMode = layoutDirection == 0 ? this.mLockModeLeft : this.mLockModeRight;
                if (startLockMode != 3) {
                    return startLockMode;
                }
                break;
            case GravityCompat.END:
                if (this.mLockModeEnd != 3) {
                    return this.mLockModeEnd;
                }
                if (layoutDirection == 0) {
                    i = this.mLockModeRight;
                } else {
                    i = this.mLockModeLeft;
                }
                int endLockMode = i;
                if (endLockMode != 3) {
                    return endLockMode;
                }
                break;
        }
        return 0;
    }

    public int getDrawerLockMode(@NonNull View view) {
        Throwable th;
        StringBuilder sb;
        View drawerView = view;
        if (!isDrawerView(drawerView)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("View ").append(drawerView).append(" is not a drawer").toString());
            throw th2;
        }
        return getDrawerLockMode(((LayoutParams) drawerView.getLayoutParams()).gravity);
    }

    public void setDrawerTitle(int edgeGravity, @Nullable CharSequence charSequence) {
        CharSequence title = charSequence;
        int absGravity = GravityCompat.getAbsoluteGravity(edgeGravity, ViewCompat.getLayoutDirection(this));
        if (absGravity == 3) {
            this.mTitleLeft = title;
        } else if (absGravity == 5) {
            this.mTitleRight = title;
        }
    }

    @Nullable
    public CharSequence getDrawerTitle(int edgeGravity) {
        int absGravity = GravityCompat.getAbsoluteGravity(edgeGravity, ViewCompat.getLayoutDirection(this));
        if (absGravity == 3) {
            return this.mTitleLeft;
        }
        if (absGravity == 5) {
            return this.mTitleRight;
        }
        return null;
    }

    private boolean isInBoundsOfChild(float f, float f2, View view) {
        Rect rect;
        float x = f;
        float y = f2;
        View child = view;
        if (this.mChildHitRect == null) {
            new Rect();
            this.mChildHitRect = rect;
        }
        child.getHitRect(this.mChildHitRect);
        return this.mChildHitRect.contains((int) x, (int) y);
    }

    private boolean dispatchTransformedGenericPointerEvent(MotionEvent motionEvent, View view) {
        boolean handled;
        MotionEvent event = motionEvent;
        View child = view;
        if (!child.getMatrix().isIdentity()) {
            MotionEvent transformedEvent = getTransformedMotionEvent(event, child);
            handled = child.dispatchGenericMotionEvent(transformedEvent);
            transformedEvent.recycle();
        } else {
            float offsetX = (float) (getScrollX() - child.getLeft());
            float offsetY = (float) (getScrollY() - child.getTop());
            event.offsetLocation(offsetX, offsetY);
            handled = child.dispatchGenericMotionEvent(event);
            event.offsetLocation(-offsetX, -offsetY);
        }
        return handled;
    }

    private MotionEvent getTransformedMotionEvent(MotionEvent event, View view) {
        Matrix matrix;
        View child = view;
        float offsetX = (float) (getScrollX() - child.getLeft());
        float offsetY = (float) (getScrollY() - child.getTop());
        MotionEvent transformedEvent = MotionEvent.obtain(event);
        transformedEvent.offsetLocation(offsetX, offsetY);
        Matrix childMatrix = child.getMatrix();
        if (!childMatrix.isIdentity()) {
            if (this.mChildInvertedMatrix == null) {
                new Matrix();
                this.mChildInvertedMatrix = matrix;
            }
            boolean invert = childMatrix.invert(this.mChildInvertedMatrix);
            transformedEvent.transform(this.mChildInvertedMatrix);
        }
        return transformedEvent;
    }

    /* access modifiers changed from: package-private */
    public void updateDrawerState(int i, int i2, View view) {
        int state;
        int i3 = i;
        int activeState = i2;
        View activeDrawer = view;
        int leftState = this.mLeftDragger.getViewDragState();
        int rightState = this.mRightDragger.getViewDragState();
        if (leftState == 1 || rightState == 1) {
            state = 1;
        } else if (leftState == 2 || rightState == 2) {
            state = 2;
        } else {
            state = 0;
        }
        if (activeDrawer != null && activeState == 0) {
            LayoutParams lp = (LayoutParams) activeDrawer.getLayoutParams();
            if (lp.onScreen == 0.0f) {
                dispatchOnDrawerClosed(activeDrawer);
            } else if (lp.onScreen == TOUCH_SLOP_SENSITIVITY) {
                dispatchOnDrawerOpened(activeDrawer);
            }
        }
        if (state != this.mDrawerState) {
            this.mDrawerState = state;
            if (this.mListeners != null) {
                for (int i4 = this.mListeners.size() - 1; i4 >= 0; i4--) {
                    this.mListeners.get(i4).onDrawerStateChanged(state);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnDrawerClosed(View view) {
        View rootView;
        View drawerView = view;
        LayoutParams lp = (LayoutParams) drawerView.getLayoutParams();
        if ((lp.openState & 1) == 1) {
            lp.openState = 0;
            if (this.mListeners != null) {
                for (int i = this.mListeners.size() - 1; i >= 0; i--) {
                    this.mListeners.get(i).onDrawerClosed(drawerView);
                }
            }
            updateChildrenImportantForAccessibility(drawerView, false);
            if (hasWindowFocus() && (rootView = getRootView()) != null) {
                rootView.sendAccessibilityEvent(32);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnDrawerOpened(View view) {
        View drawerView = view;
        LayoutParams lp = (LayoutParams) drawerView.getLayoutParams();
        if ((lp.openState & 1) == 0) {
            lp.openState = 1;
            if (this.mListeners != null) {
                for (int i = this.mListeners.size() - 1; i >= 0; i--) {
                    this.mListeners.get(i).onDrawerOpened(drawerView);
                }
            }
            updateChildrenImportantForAccessibility(drawerView, true);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
        }
    }

    private void updateChildrenImportantForAccessibility(View view, boolean z) {
        View drawerView = view;
        boolean isDrawerOpen = z;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if ((isDrawerOpen || isDrawerView(child)) && (!isDrawerOpen || child != drawerView)) {
                ViewCompat.setImportantForAccessibility(child, 4);
            } else {
                ViewCompat.setImportantForAccessibility(child, 1);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnDrawerSlide(View view, float f) {
        View drawerView = view;
        float slideOffset = f;
        if (this.mListeners != null) {
            for (int i = this.mListeners.size() - 1; i >= 0; i--) {
                this.mListeners.get(i).onDrawerSlide(drawerView, slideOffset);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setDrawerViewOffset(View view, float f) {
        View drawerView = view;
        float slideOffset = f;
        LayoutParams lp = (LayoutParams) drawerView.getLayoutParams();
        if (slideOffset != lp.onScreen) {
            lp.onScreen = slideOffset;
            dispatchOnDrawerSlide(drawerView, slideOffset);
        }
    }

    /* access modifiers changed from: package-private */
    public float getDrawerViewOffset(View drawerView) {
        return ((LayoutParams) drawerView.getLayoutParams()).onScreen;
    }

    /* access modifiers changed from: package-private */
    public int getDrawerViewAbsoluteGravity(View drawerView) {
        return GravityCompat.getAbsoluteGravity(((LayoutParams) drawerView.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this));
    }

    /* access modifiers changed from: package-private */
    public boolean checkDrawerViewAbsoluteGravity(View drawerView, int i) {
        int checkFor = i;
        return (getDrawerViewAbsoluteGravity(drawerView) & checkFor) == checkFor;
    }

    /* access modifiers changed from: package-private */
    public View findOpenDrawer() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if ((((LayoutParams) child.getLayoutParams()).openState & 1) == 1) {
                return child;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void moveDrawerToOffset(View view, float f) {
        View drawerView = view;
        float slideOffset = f;
        float oldOffset = getDrawerViewOffset(drawerView);
        int width = drawerView.getWidth();
        int dx = ((int) (((float) width) * slideOffset)) - ((int) (((float) width) * oldOffset));
        drawerView.offsetLeftAndRight(checkDrawerViewAbsoluteGravity(drawerView, 3) ? dx : -dx);
        setDrawerViewOffset(drawerView, slideOffset);
    }

    /* access modifiers changed from: package-private */
    public View findDrawerWithGravity(int gravity) {
        int absHorizGravity = GravityCompat.getAbsoluteGravity(gravity, ViewCompat.getLayoutDirection(this)) & 7;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if ((getDrawerViewAbsoluteGravity(child) & 7) == absHorizGravity) {
                return child;
            }
        }
        return null;
    }

    static String gravityToString(int i) {
        int gravity = i;
        if ((gravity & 3) == 3) {
            return "LEFT";
        }
        if ((gravity & 5) == 5) {
            return "RIGHT";
        }
        return Integer.toHexString(gravity);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"WrongConstant"})
    public void onMeasure(int i, int i2) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        if (!(widthMode == 1073741824 && heightMode == 1073741824)) {
            if (isInEditMode()) {
                if (widthMode != Integer.MIN_VALUE) {
                    if (widthMode == 0) {
                        widthSize = 300;
                    }
                }
                if (heightMode != Integer.MIN_VALUE) {
                    if (heightMode == 0) {
                        heightSize = 300;
                    }
                }
            } else {
                Throwable th4 = th3;
                new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
                throw th4;
            }
        }
        setMeasuredDimension(widthSize, heightSize);
        boolean applyInsets = this.mLastInsets != null && ViewCompat.getFitsSystemWindows(this);
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        boolean hasDrawerOnLeftEdge = false;
        boolean hasDrawerOnRightEdge = false;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View child = getChildAt(i3);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (applyInsets) {
                    int cgrav = GravityCompat.getAbsoluteGravity(lp.gravity, layoutDirection);
                    if (ViewCompat.getFitsSystemWindows(child)) {
                        if (Build.VERSION.SDK_INT >= 21) {
                            WindowInsets wi = (WindowInsets) this.mLastInsets;
                            if (cgrav == 3) {
                                wi = wi.replaceSystemWindowInsets(wi.getSystemWindowInsetLeft(), wi.getSystemWindowInsetTop(), 0, wi.getSystemWindowInsetBottom());
                            } else if (cgrav == 5) {
                                wi = wi.replaceSystemWindowInsets(0, wi.getSystemWindowInsetTop(), wi.getSystemWindowInsetRight(), wi.getSystemWindowInsetBottom());
                            }
                            WindowInsets dispatchApplyWindowInsets = child.dispatchApplyWindowInsets(wi);
                        }
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        WindowInsets wi2 = (WindowInsets) this.mLastInsets;
                        if (cgrav == 3) {
                            wi2 = wi2.replaceSystemWindowInsets(wi2.getSystemWindowInsetLeft(), wi2.getSystemWindowInsetTop(), 0, wi2.getSystemWindowInsetBottom());
                        } else if (cgrav == 5) {
                            wi2 = wi2.replaceSystemWindowInsets(0, wi2.getSystemWindowInsetTop(), wi2.getSystemWindowInsetRight(), wi2.getSystemWindowInsetBottom());
                        }
                        lp.leftMargin = wi2.getSystemWindowInsetLeft();
                        lp.topMargin = wi2.getSystemWindowInsetTop();
                        lp.rightMargin = wi2.getSystemWindowInsetRight();
                        lp.bottomMargin = wi2.getSystemWindowInsetBottom();
                    }
                }
                if (isContentView(child)) {
                    child.measure(View.MeasureSpec.makeMeasureSpec((widthSize - lp.leftMargin) - lp.rightMargin, Declaration.MODULE_REFERENCE), View.MeasureSpec.makeMeasureSpec((heightSize - lp.topMargin) - lp.bottomMargin, Declaration.MODULE_REFERENCE));
                } else if (isDrawerView(child)) {
                    if (SET_DRAWER_SHADOW_FROM_ELEVATION) {
                        if (ViewCompat.getElevation(child) != this.mDrawerElevation) {
                            ViewCompat.setElevation(child, this.mDrawerElevation);
                        }
                    }
                    int childGravity = getDrawerViewAbsoluteGravity(child) & 7;
                    boolean isLeftEdgeDrawer = childGravity == 3;
                    if ((!isLeftEdgeDrawer || !hasDrawerOnLeftEdge) && (isLeftEdgeDrawer || !hasDrawerOnRightEdge)) {
                        if (isLeftEdgeDrawer) {
                            hasDrawerOnLeftEdge = true;
                        } else {
                            hasDrawerOnRightEdge = true;
                        }
                        child.measure(getChildMeasureSpec(widthMeasureSpec, this.mMinDrawerMargin + lp.leftMargin + lp.rightMargin, lp.width), getChildMeasureSpec(heightMeasureSpec, lp.topMargin + lp.bottomMargin, lp.height));
                    } else {
                        Throwable th5 = th2;
                        new StringBuilder();
                        new IllegalStateException(sb2.append("Child drawer has absolute gravity ").append(gravityToString(childGravity)).append(" but this ").append(TAG).append(" already has a ").append("drawer view along that edge").toString());
                        throw th5;
                    }
                } else {
                    Throwable th6 = th;
                    new StringBuilder();
                    new IllegalStateException(sb.append("Child ").append(child).append(" at index ").append(i3).append(" does not have a valid layout_gravity - must be Gravity.LEFT, ").append("Gravity.RIGHT or Gravity.NO_GRAVITY").toString());
                    throw th6;
                }
            }
        }
    }

    private void resolveShadowDrawables() {
        if (!SET_DRAWER_SHADOW_FROM_ELEVATION) {
            this.mShadowLeftResolved = resolveLeftShadow();
            this.mShadowRightResolved = resolveRightShadow();
        }
    }

    private Drawable resolveLeftShadow() {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        if (layoutDirection == 0) {
            if (this.mShadowStart != null) {
                boolean mirror = mirror(this.mShadowStart, layoutDirection);
                return this.mShadowStart;
            }
        } else if (this.mShadowEnd != null) {
            boolean mirror2 = mirror(this.mShadowEnd, layoutDirection);
            return this.mShadowEnd;
        }
        return this.mShadowLeft;
    }

    private Drawable resolveRightShadow() {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        if (layoutDirection == 0) {
            if (this.mShadowEnd != null) {
                boolean mirror = mirror(this.mShadowEnd, layoutDirection);
                return this.mShadowEnd;
            }
        } else if (this.mShadowStart != null) {
            boolean mirror2 = mirror(this.mShadowStart, layoutDirection);
            return this.mShadowStart;
        }
        return this.mShadowRight;
    }

    private boolean mirror(Drawable drawable, int i) {
        Drawable drawable2 = drawable;
        int layoutDirection = i;
        if (drawable2 == null || !DrawableCompat.isAutoMirrored(drawable2)) {
            return false;
        }
        boolean layoutDirection2 = DrawableCompat.setLayoutDirection(drawable2, layoutDirection);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int l, int i, int r, int i2) {
        int childLeft;
        float newOffset;
        boolean z2 = z;
        int t = i;
        int b = i2;
        this.mInLayout = true;
        int width = r - l;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View child = getChildAt(i3);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (isContentView(child)) {
                    child.layout(lp.leftMargin, lp.topMargin, lp.leftMargin + child.getMeasuredWidth(), lp.topMargin + child.getMeasuredHeight());
                } else {
                    int childWidth = child.getMeasuredWidth();
                    int childHeight = child.getMeasuredHeight();
                    if (checkDrawerViewAbsoluteGravity(child, 3)) {
                        childLeft = (-childWidth) + ((int) (((float) childWidth) * lp.onScreen));
                        newOffset = ((float) (childWidth + childLeft)) / ((float) childWidth);
                    } else {
                        childLeft = width - ((int) (((float) childWidth) * lp.onScreen));
                        newOffset = ((float) (width - childLeft)) / ((float) childWidth);
                    }
                    boolean changeOffset = newOffset != lp.onScreen;
                    switch (lp.gravity & 112) {
                        case 16:
                            int height = b - t;
                            int childTop = (height - childHeight) / 2;
                            if (childTop < lp.topMargin) {
                                childTop = lp.topMargin;
                            } else {
                                if (childTop + childHeight > height - lp.bottomMargin) {
                                    childTop = (height - lp.bottomMargin) - childHeight;
                                }
                            }
                            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
                            break;
                        case 80:
                            int height2 = b - t;
                            child.layout(childLeft, (height2 - lp.bottomMargin) - child.getMeasuredHeight(), childLeft + childWidth, height2 - lp.bottomMargin);
                            break;
                        default:
                            child.layout(childLeft, lp.topMargin, childLeft + childWidth, lp.topMargin + childHeight);
                            break;
                    }
                    if (changeOffset) {
                        setDrawerViewOffset(child, newOffset);
                    }
                    int newVisibility = lp.onScreen > 0.0f ? 0 : 4;
                    if (child.getVisibility() != newVisibility) {
                        child.setVisibility(newVisibility);
                    }
                }
            }
        }
        this.mInLayout = false;
        this.mFirstLayout = false;
    }

    public void requestLayout() {
        if (!this.mInLayout) {
            super.requestLayout();
        }
    }

    public void computeScroll() {
        int childCount = getChildCount();
        float scrimOpacity = 0.0f;
        for (int i = 0; i < childCount; i++) {
            scrimOpacity = Math.max(scrimOpacity, ((LayoutParams) getChildAt(i).getLayoutParams()).onScreen);
        }
        this.mScrimOpacity = scrimOpacity;
        boolean leftDraggerSettling = this.mLeftDragger.continueSettling(true);
        boolean rightDraggerSettling = this.mRightDragger.continueSettling(true);
        if (leftDraggerSettling || rightDraggerSettling) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private static boolean hasOpaqueBackground(View v) {
        Drawable bg = v.getBackground();
        if (bg == null) {
            return false;
        }
        return bg.getOpacity() == -1;
    }

    public void setStatusBarBackground(@Nullable Drawable bg) {
        this.mStatusBarBackground = bg;
        invalidate();
    }

    @Nullable
    public Drawable getStatusBarBackgroundDrawable() {
        return this.mStatusBarBackground;
    }

    public void setStatusBarBackground(int i) {
        int resId = i;
        this.mStatusBarBackground = resId != 0 ? ContextCompat.getDrawable(getContext(), resId) : null;
        invalidate();
    }

    public void setStatusBarBackgroundColor(@ColorInt int color) {
        Drawable drawable;
        new ColorDrawable(color);
        this.mStatusBarBackground = drawable;
        invalidate();
    }

    public void onRtlPropertiesChanged(int i) {
        int i2 = i;
        resolveShadowDrawables();
    }

    public void onDraw(Canvas canvas) {
        int inset;
        Canvas c = canvas;
        super.onDraw(c);
        if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                inset = this.mLastInsets != null ? ((WindowInsets) this.mLastInsets).getSystemWindowInsetTop() : 0;
            } else {
                inset = 0;
            }
            if (inset > 0) {
                this.mStatusBarBackground.setBounds(0, 0, getWidth(), inset);
                this.mStatusBarBackground.draw(c);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        Canvas canvas2 = canvas;
        View child = view;
        long drawingTime = j;
        int height = getHeight();
        boolean drawingContent = isContentView(child);
        int clipLeft = 0;
        int clipRight = getWidth();
        int restoreCount = canvas2.save();
        if (drawingContent) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View v = getChildAt(i);
                if (v != child && v.getVisibility() == 0 && hasOpaqueBackground(v) && isDrawerView(v) && v.getHeight() >= height) {
                    if (checkDrawerViewAbsoluteGravity(v, 3)) {
                        int vright = v.getRight();
                        if (vright > clipLeft) {
                            clipLeft = vright;
                        }
                    } else {
                        int vleft = v.getLeft();
                        if (vleft < clipRight) {
                            clipRight = vleft;
                        }
                    }
                }
            }
            boolean clipRect = canvas2.clipRect(clipLeft, 0, clipRight, getHeight());
        }
        boolean result = super.drawChild(canvas2, child, drawingTime);
        canvas2.restoreToCount(restoreCount);
        if (this.mScrimOpacity <= 0.0f || !drawingContent) {
            if (this.mShadowLeftResolved == null || !checkDrawerViewAbsoluteGravity(child, 3)) {
                if (this.mShadowRightResolved != null && checkDrawerViewAbsoluteGravity(child, 5)) {
                    int shadowWidth = this.mShadowRightResolved.getIntrinsicWidth();
                    int childLeft = child.getLeft();
                    float alpha = Math.max(0.0f, Math.min(((float) (getWidth() - childLeft)) / ((float) this.mRightDragger.getEdgeSize()), TOUCH_SLOP_SENSITIVITY));
                    this.mShadowRightResolved.setBounds(childLeft - shadowWidth, child.getTop(), childLeft, child.getBottom());
                    this.mShadowRightResolved.setAlpha((int) (255.0f * alpha));
                    this.mShadowRightResolved.draw(canvas2);
                }
            } else {
                int shadowWidth2 = this.mShadowLeftResolved.getIntrinsicWidth();
                int childRight = child.getRight();
                float alpha2 = Math.max(0.0f, Math.min(((float) childRight) / ((float) this.mLeftDragger.getEdgeSize()), TOUCH_SLOP_SENSITIVITY));
                this.mShadowLeftResolved.setBounds(childRight, child.getTop(), childRight + shadowWidth2, child.getBottom());
                this.mShadowLeftResolved.setAlpha((int) (255.0f * alpha2));
                this.mShadowLeftResolved.draw(canvas2);
            }
        } else {
            this.mScrimPaint.setColor((((int) (((float) ((this.mScrimColor & -16777216) >>> 24)) * this.mScrimOpacity)) << 24) | (this.mScrimColor & 16777215));
            canvas2.drawRect((float) clipLeft, 0.0f, (float) clipRight, (float) getHeight(), this.mScrimPaint);
        }
        return result;
    }

    /* access modifiers changed from: package-private */
    public boolean isContentView(View child) {
        return ((LayoutParams) child.getLayoutParams()).gravity == 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isDrawerView(View view) {
        View child = view;
        int absGravity = GravityCompat.getAbsoluteGravity(((LayoutParams) child.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(child));
        if ((absGravity & 3) != 0) {
            return true;
        }
        if ((absGravity & 5) != 0) {
            return true;
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        View child;
        MotionEvent ev = motionEvent;
        int action = ev.getActionMasked();
        boolean interceptForDrag = this.mLeftDragger.shouldInterceptTouchEvent(ev) | this.mRightDragger.shouldInterceptTouchEvent(ev);
        boolean interceptForTap = false;
        switch (action) {
            case 0:
                float x = ev.getX();
                float y = ev.getY();
                this.mInitialMotionX = x;
                this.mInitialMotionY = y;
                if (this.mScrimOpacity > 0.0f && (child = this.mLeftDragger.findTopChildUnder((int) x, (int) y)) != null && isContentView(child)) {
                    interceptForTap = true;
                }
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                break;
            case 1:
            case 3:
                closeDrawers(true);
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                break;
            case 2:
                if (this.mLeftDragger.checkTouchSlop(3)) {
                    this.mLeftCallback.removeCallbacks();
                    this.mRightCallback.removeCallbacks();
                    break;
                }
                break;
        }
        return interceptForDrag || interceptForTap || hasPeekingDrawer() || this.mChildrenCanceledTouch;
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        MotionEvent event = motionEvent;
        if ((event.getSource() & 2) == 0 || event.getAction() == 10 || this.mScrimOpacity <= 0.0f) {
            return super.dispatchGenericMotionEvent(event);
        }
        int childrenCount = getChildCount();
        if (childrenCount != 0) {
            float x = event.getX();
            float y = event.getY();
            for (int i = childrenCount - 1; i >= 0; i--) {
                View child = getChildAt(i);
                if (isInBoundsOfChild(x, y, child) && !isContentView(child) && dispatchTransformedGenericPointerEvent(event, child)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        View openDrawer;
        MotionEvent ev = motionEvent;
        this.mLeftDragger.processTouchEvent(ev);
        this.mRightDragger.processTouchEvent(ev);
        switch (ev.getAction() & 255) {
            case 0:
                float x = ev.getX();
                float y = ev.getY();
                this.mInitialMotionX = x;
                this.mInitialMotionY = y;
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                break;
            case 1:
                float x2 = ev.getX();
                float y2 = ev.getY();
                boolean peekingOnly = true;
                View touchedView = this.mLeftDragger.findTopChildUnder((int) x2, (int) y2);
                if (touchedView != null && isContentView(touchedView)) {
                    float dx = x2 - this.mInitialMotionX;
                    float dy = y2 - this.mInitialMotionY;
                    int slop = this.mLeftDragger.getTouchSlop();
                    if ((dx * dx) + (dy * dy) < ((float) (slop * slop)) && (openDrawer = findOpenDrawer()) != null) {
                        peekingOnly = getDrawerLockMode(openDrawer) == 2;
                    }
                }
                closeDrawers(peekingOnly);
                this.mDisallowInterceptRequested = false;
                break;
            case 3:
                closeDrawers(true);
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                break;
        }
        return true;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        boolean disallowIntercept = z;
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
        this.mDisallowInterceptRequested = disallowIntercept;
        if (disallowIntercept) {
            closeDrawers(true);
        }
    }

    public void closeDrawers() {
        closeDrawers(false);
    }

    /* access modifiers changed from: package-private */
    public void closeDrawers(boolean z) {
        boolean peekingOnly = z;
        boolean needsInvalidate = false;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (isDrawerView(child) && (!peekingOnly || lp.isPeeking)) {
                int childWidth = child.getWidth();
                if (checkDrawerViewAbsoluteGravity(child, 3)) {
                    needsInvalidate |= this.mLeftDragger.smoothSlideViewTo(child, -childWidth, child.getTop());
                } else {
                    needsInvalidate |= this.mRightDragger.smoothSlideViewTo(child, getWidth(), child.getTop());
                }
                lp.isPeeking = false;
            }
        }
        this.mLeftCallback.removeCallbacks();
        this.mRightCallback.removeCallbacks();
        if (needsInvalidate) {
            invalidate();
        }
    }

    public void openDrawer(@NonNull View drawerView) {
        openDrawer(drawerView, true);
    }

    public void openDrawer(@NonNull View view, boolean z) {
        Throwable th;
        StringBuilder sb;
        View drawerView = view;
        boolean animate = z;
        if (!isDrawerView(drawerView)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("View ").append(drawerView).append(" is not a sliding drawer").toString());
            throw th2;
        }
        LayoutParams lp = (LayoutParams) drawerView.getLayoutParams();
        if (this.mFirstLayout) {
            lp.onScreen = TOUCH_SLOP_SENSITIVITY;
            lp.openState = 1;
            updateChildrenImportantForAccessibility(drawerView, true);
        } else if (animate) {
            lp.openState |= 2;
            if (checkDrawerViewAbsoluteGravity(drawerView, 3)) {
                boolean smoothSlideViewTo = this.mLeftDragger.smoothSlideViewTo(drawerView, 0, drawerView.getTop());
            } else {
                boolean smoothSlideViewTo2 = this.mRightDragger.smoothSlideViewTo(drawerView, getWidth() - drawerView.getWidth(), drawerView.getTop());
            }
        } else {
            moveDrawerToOffset(drawerView, TOUCH_SLOP_SENSITIVITY);
            updateDrawerState(lp.gravity, 0, drawerView);
            drawerView.setVisibility(0);
        }
        invalidate();
    }

    public void openDrawer(int gravity) {
        openDrawer(gravity, true);
    }

    public void openDrawer(int i, boolean z) {
        Throwable th;
        StringBuilder sb;
        int gravity = i;
        boolean animate = z;
        View drawerView = findDrawerWithGravity(gravity);
        if (drawerView == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("No drawer view found with gravity ").append(gravityToString(gravity)).toString());
            throw th2;
        }
        openDrawer(drawerView, animate);
    }

    public void closeDrawer(@NonNull View drawerView) {
        closeDrawer(drawerView, true);
    }

    public void closeDrawer(@NonNull View view, boolean z) {
        Throwable th;
        StringBuilder sb;
        View drawerView = view;
        boolean animate = z;
        if (!isDrawerView(drawerView)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("View ").append(drawerView).append(" is not a sliding drawer").toString());
            throw th2;
        }
        LayoutParams lp = (LayoutParams) drawerView.getLayoutParams();
        if (this.mFirstLayout) {
            lp.onScreen = 0.0f;
            lp.openState = 0;
        } else if (animate) {
            lp.openState |= 4;
            if (checkDrawerViewAbsoluteGravity(drawerView, 3)) {
                boolean smoothSlideViewTo = this.mLeftDragger.smoothSlideViewTo(drawerView, -drawerView.getWidth(), drawerView.getTop());
            } else {
                boolean smoothSlideViewTo2 = this.mRightDragger.smoothSlideViewTo(drawerView, getWidth(), drawerView.getTop());
            }
        } else {
            moveDrawerToOffset(drawerView, 0.0f);
            updateDrawerState(lp.gravity, 0, drawerView);
            drawerView.setVisibility(4);
        }
        invalidate();
    }

    public void closeDrawer(int gravity) {
        closeDrawer(gravity, true);
    }

    public void closeDrawer(int i, boolean z) {
        Throwable th;
        StringBuilder sb;
        int gravity = i;
        boolean animate = z;
        View drawerView = findDrawerWithGravity(gravity);
        if (drawerView == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("No drawer view found with gravity ").append(gravityToString(gravity)).toString());
            throw th2;
        }
        closeDrawer(drawerView, animate);
    }

    public boolean isDrawerOpen(@NonNull View view) {
        Throwable th;
        StringBuilder sb;
        View drawer = view;
        if (!isDrawerView(drawer)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("View ").append(drawer).append(" is not a drawer").toString());
            throw th2;
        }
        return (((LayoutParams) drawer.getLayoutParams()).openState & 1) == 1;
    }

    public boolean isDrawerOpen(int drawerGravity) {
        View drawerView = findDrawerWithGravity(drawerGravity);
        if (drawerView != null) {
            return isDrawerOpen(drawerView);
        }
        return false;
    }

    public boolean isDrawerVisible(@NonNull View view) {
        Throwable th;
        StringBuilder sb;
        View drawer = view;
        if (!isDrawerView(drawer)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("View ").append(drawer).append(" is not a drawer").toString());
            throw th2;
        }
        return ((LayoutParams) drawer.getLayoutParams()).onScreen > 0.0f;
    }

    public boolean isDrawerVisible(int drawerGravity) {
        View drawerView = findDrawerWithGravity(drawerGravity);
        if (drawerView != null) {
            return isDrawerVisible(drawerView);
        }
        return false;
    }

    private boolean hasPeekingDrawer() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (((LayoutParams) getChildAt(i).getLayoutParams()).isPeeking) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        ViewGroup.LayoutParams layoutParams;
        new LayoutParams(-1, -1);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        ViewGroup.LayoutParams layoutParams2;
        ViewGroup.LayoutParams layoutParams3;
        ViewGroup.LayoutParams layoutParams4;
        ViewGroup.LayoutParams layoutParams5;
        ViewGroup.LayoutParams p = layoutParams;
        if (p instanceof LayoutParams) {
            layoutParams3 = layoutParams5;
            new LayoutParams((LayoutParams) p);
        } else if (p instanceof ViewGroup.MarginLayoutParams) {
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

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        ArrayList<View> views = arrayList;
        int direction = i;
        int focusableMode = i2;
        if (getDescendantFocusability() != 393216) {
            int childCount = getChildCount();
            boolean isDrawerOpen = false;
            for (int i3 = 0; i3 < childCount; i3++) {
                View child = getChildAt(i3);
                if (!isDrawerView(child)) {
                    boolean add = this.mNonDrawerViews.add(child);
                } else if (isDrawerOpen(child)) {
                    isDrawerOpen = true;
                    child.addFocusables(views, direction, focusableMode);
                }
            }
            if (!isDrawerOpen) {
                int i4 = this.mNonDrawerViews.size();
                for (int i5 = 0; i5 < i4; i5++) {
                    View child2 = this.mNonDrawerViews.get(i5);
                    if (child2.getVisibility() == 0) {
                        child2.addFocusables(views, direction, focusableMode);
                    }
                }
            }
            this.mNonDrawerViews.clear();
        }
    }

    private boolean hasVisibleDrawer() {
        return findVisibleDrawer() != null;
    }

    /* access modifiers changed from: package-private */
    public View findVisibleDrawer() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (isDrawerView(child) && isDrawerVisible(child)) {
                return child;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void cancelChildViewTouch() {
        if (!this.mChildrenCanceledTouch) {
            long now = SystemClock.uptimeMillis();
            MotionEvent cancelEvent = MotionEvent.obtain(now, now, 3, 0.0f, 0.0f, 0);
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                boolean dispatchTouchEvent = getChildAt(i).dispatchTouchEvent(cancelEvent);
            }
            cancelEvent.recycle();
            this.mChildrenCanceledTouch = true;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        int keyCode = i;
        KeyEvent event = keyEvent;
        if (keyCode != 4 || !hasVisibleDrawer()) {
            return super.onKeyDown(keyCode, event);
        }
        event.startTracking();
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        int keyCode = i;
        KeyEvent event = keyEvent;
        if (keyCode != 4) {
            return super.onKeyUp(keyCode, event);
        }
        View visibleDrawer = findVisibleDrawer();
        if (visibleDrawer != null && getDrawerLockMode(visibleDrawer) == 0) {
            closeDrawers();
        }
        return visibleDrawer != null;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        View toOpen;
        Parcelable state = parcelable;
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        if (!(ss.openDrawerGravity == 0 || (toOpen = findDrawerWithGravity(ss.openDrawerGravity)) == null)) {
            openDrawer(toOpen);
        }
        if (ss.lockModeLeft != 3) {
            setDrawerLockMode(ss.lockModeLeft, 3);
        }
        if (ss.lockModeRight != 3) {
            setDrawerLockMode(ss.lockModeRight, 5);
        }
        if (ss.lockModeStart != 3) {
            setDrawerLockMode(ss.lockModeStart, (int) GravityCompat.START);
        }
        if (ss.lockModeEnd != 3) {
            setDrawerLockMode(ss.lockModeEnd, (int) GravityCompat.END);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        LayoutParams lp;
        new SavedState(super.onSaveInstanceState());
        SavedState ss = savedState;
        int childCount = getChildCount();
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            }
            lp = (LayoutParams) getChildAt(i).getLayoutParams();
            boolean isOpenedAndNotClosing = lp.openState == 1;
            boolean isClosedAndOpening = lp.openState == 2;
            if (isOpenedAndNotClosing || isClosedAndOpening) {
                ss.openDrawerGravity = lp.gravity;
            } else {
                i++;
            }
        }
        ss.openDrawerGravity = lp.gravity;
        ss.lockModeLeft = this.mLockModeLeft;
        ss.lockModeRight = this.mLockModeRight;
        ss.lockModeStart = this.mLockModeStart;
        ss.lockModeEnd = this.mLockModeEnd;
        return ss;
    }

    public void addView(View view, int index, ViewGroup.LayoutParams params) {
        View child = view;
        super.addView(child, index, params);
        if (findOpenDrawer() != null || isDrawerView(child)) {
            ViewCompat.setImportantForAccessibility(child, 4);
        } else {
            ViewCompat.setImportantForAccessibility(child, 1);
        }
        if (!CAN_HIDE_DESCENDANTS) {
            ViewCompat.setAccessibilityDelegate(child, this.mChildAccessibilityDelegate);
        }
    }

    static boolean includeChildForAccessibility(View view) {
        View child = view;
        return (ViewCompat.getImportantForAccessibility(child) == 4 || ViewCompat.getImportantForAccessibility(child) == 2) ? false : true;
    }

    /* renamed from: android.support.v4.widget.DrawerLayout$SavedState */
    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR;
        int lockModeEnd;
        int lockModeLeft;
        int lockModeRight;
        int lockModeStart;
        int openDrawerGravity = 0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SavedState(@android.support.annotation.NonNull android.os.Parcel r7, @android.support.annotation.Nullable java.lang.ClassLoader r8) {
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
                r4 = 0
                r3.openDrawerGravity = r4
                r3 = r0
                r4 = r1
                int r4 = r4.readInt()
                r3.openDrawerGravity = r4
                r3 = r0
                r4 = r1
                int r4 = r4.readInt()
                r3.lockModeLeft = r4
                r3 = r0
                r4 = r1
                int r4 = r4.readInt()
                r3.lockModeRight = r4
                r3 = r0
                r4 = r1
                int r4 = r4.readInt()
                r3.lockModeStart = r4
                r3 = r0
                r4 = r1
                int r4 = r4.readInt()
                r3.lockModeEnd = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.DrawerLayout.SavedState.<init>(android.os.Parcel, java.lang.ClassLoader):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SavedState(@NonNull Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel parcel, int flags) {
            Parcel dest = parcel;
            super.writeToParcel(dest, flags);
            dest.writeInt(this.openDrawerGravity);
            dest.writeInt(this.lockModeLeft);
            dest.writeInt(this.lockModeRight);
            dest.writeInt(this.lockModeStart);
            dest.writeInt(this.lockModeEnd);
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

    /* renamed from: android.support.v4.widget.DrawerLayout$ViewDragCallback */
    private class ViewDragCallback extends ViewDragHelper.Callback {
        private final int mAbsGravity;
        private ViewDragHelper mDragger;
        private final Runnable mPeekRunnable;
        final /* synthetic */ DrawerLayout this$0;

        ViewDragCallback(DrawerLayout drawerLayout, int gravity) {
            Runnable runnable;
            this.this$0 = drawerLayout;
            new Runnable(this) {
                final /* synthetic */ ViewDragCallback this$1;

                {
                    this.this$1 = this$1;
                }

                public void run() {
                    this.this$1.peekDrawer();
                }
            };
            this.mPeekRunnable = runnable;
            this.mAbsGravity = gravity;
        }

        public void setDragger(ViewDragHelper dragger) {
            ViewDragHelper viewDragHelper = dragger;
            this.mDragger = viewDragHelper;
        }

        public void removeCallbacks() {
            boolean removeCallbacks = this.this$0.removeCallbacks(this.mPeekRunnable);
        }

        public boolean tryCaptureView(View view, int i) {
            View child = view;
            int i2 = i;
            return this.this$0.isDrawerView(child) && this.this$0.checkDrawerViewAbsoluteGravity(child, this.mAbsGravity) && this.this$0.getDrawerLockMode(child) == 0;
        }

        public void onViewDragStateChanged(int state) {
            this.this$0.updateDrawerState(this.mAbsGravity, state, this.mDragger.getCapturedView());
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            float offset;
            View changedView = view;
            int left = i;
            int i5 = i2;
            int i6 = i3;
            int i7 = i4;
            int childWidth = changedView.getWidth();
            if (this.this$0.checkDrawerViewAbsoluteGravity(changedView, 3)) {
                offset = ((float) (childWidth + left)) / ((float) childWidth);
            } else {
                offset = ((float) (this.this$0.getWidth() - left)) / ((float) childWidth);
            }
            this.this$0.setDrawerViewOffset(changedView, offset);
            changedView.setVisibility(offset == 0.0f ? 4 : 0);
            this.this$0.invalidate();
        }

        public void onViewCaptured(View capturedChild, int i) {
            int i2 = i;
            ((LayoutParams) capturedChild.getLayoutParams()).isPeeking = false;
            closeOtherDrawer();
        }

        private void closeOtherDrawer() {
            View toClose = this.this$0.findDrawerWithGravity(this.mAbsGravity == 3 ? 5 : 3);
            if (toClose != null) {
                this.this$0.closeDrawer(toClose);
            }
        }

        public void onViewReleased(View view, float f, float f2) {
            int left;
            View releasedChild = view;
            float xvel = f;
            float f3 = f2;
            float offset = this.this$0.getDrawerViewOffset(releasedChild);
            int childWidth = releasedChild.getWidth();
            if (this.this$0.checkDrawerViewAbsoluteGravity(releasedChild, 3)) {
                left = (xvel > 0.0f || (xvel == 0.0f && offset > 0.5f)) ? 0 : -childWidth;
            } else {
                int width = this.this$0.getWidth();
                left = (xvel < 0.0f || (xvel == 0.0f && offset > 0.5f)) ? width - childWidth : width;
            }
            boolean z = this.mDragger.settleCapturedViewAt(left, releasedChild.getTop());
            this.this$0.invalidate();
        }

        public void onEdgeTouched(int i, int i2) {
            int i3 = i;
            int i4 = i2;
            boolean postDelayed = this.this$0.postDelayed(this.mPeekRunnable, 160);
        }

        /* access modifiers changed from: package-private */
        public void peekDrawer() {
            View toCapture;
            int childLeft;
            int peekDistance = this.mDragger.getEdgeSize();
            boolean leftEdge = this.mAbsGravity == 3;
            if (leftEdge) {
                toCapture = this.this$0.findDrawerWithGravity(3);
                childLeft = (toCapture != null ? -toCapture.getWidth() : 0) + peekDistance;
            } else {
                toCapture = this.this$0.findDrawerWithGravity(5);
                childLeft = this.this$0.getWidth() - peekDistance;
            }
            if (toCapture == null) {
                return;
            }
            if (((leftEdge && toCapture.getLeft() < childLeft) || (!leftEdge && toCapture.getLeft() > childLeft)) && this.this$0.getDrawerLockMode(toCapture) == 0) {
                LayoutParams lp = (LayoutParams) toCapture.getLayoutParams();
                boolean smoothSlideViewTo = this.mDragger.smoothSlideViewTo(toCapture, childLeft, toCapture.getTop());
                lp.isPeeking = true;
                this.this$0.invalidate();
                closeOtherDrawer();
                this.this$0.cancelChildViewTouch();
            }
        }

        public boolean onEdgeLock(int i) {
            int i2 = i;
            return false;
        }

        public void onEdgeDragStarted(int edgeFlags, int i) {
            View toCapture;
            int pointerId = i;
            if ((edgeFlags & 1) == 1) {
                toCapture = this.this$0.findDrawerWithGravity(3);
            } else {
                toCapture = this.this$0.findDrawerWithGravity(5);
            }
            if (toCapture != null && this.this$0.getDrawerLockMode(toCapture) == 0) {
                this.mDragger.captureChildView(toCapture, pointerId);
            }
        }

        public int getViewHorizontalDragRange(View view) {
            View child = view;
            return this.this$0.isDrawerView(child) ? child.getWidth() : 0;
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            View child = view;
            int left = i;
            int i3 = i2;
            if (this.this$0.checkDrawerViewAbsoluteGravity(child, 3)) {
                return Math.max(-child.getWidth(), Math.min(left, 0));
            }
            int width = this.this$0.getWidth();
            return Math.max(width - child.getWidth(), Math.min(left, width));
        }

        public int clampViewPositionVertical(View child, int i, int i2) {
            int i3 = i;
            int i4 = i2;
            return child.getTop();
        }
    }

    /* renamed from: android.support.v4.widget.DrawerLayout$LayoutParams */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static final int FLAG_IS_CLOSING = 4;
        private static final int FLAG_IS_OPENED = 1;
        private static final int FLAG_IS_OPENING = 2;
        public int gravity;
        boolean isPeeking;
        float onScreen;
        int openState;

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
                r4.gravity = r5
                r4 = r1
                r5 = r2
                int[] r6 = android.support.p000v4.widget.DrawerLayout.LAYOUT_ATTRS
                android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r6)
                r3 = r4
                r4 = r0
                r5 = r3
                r6 = 0
                r7 = 0
                int r5 = r5.getInt(r6, r7)
                r4.gravity = r5
                r4 = r3
                r4.recycle()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.DrawerLayout.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height) {
            super(width, height);
            this.gravity = 0;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height, int gravity2) {
            this(width, height);
            this.gravity = gravity2;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(@android.support.annotation.NonNull android.support.p000v4.widget.DrawerLayout.LayoutParams r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                r2.<init>(r3)
                r2 = r0
                r3 = 0
                r2.gravity = r3
                r2 = r0
                r3 = r1
                int r3 = r3.gravity
                r2.gravity = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.DrawerLayout.LayoutParams.<init>(android.support.v4.widget.DrawerLayout$LayoutParams):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(@NonNull ViewGroup.LayoutParams source) {
            super(source);
            this.gravity = 0;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(@NonNull ViewGroup.MarginLayoutParams source) {
            super(source);
            this.gravity = 0;
        }
    }

    /* renamed from: android.support.v4.widget.DrawerLayout$AccessibilityDelegate */
    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final Rect mTmpRect;
        final /* synthetic */ DrawerLayout this$0;

        AccessibilityDelegate(DrawerLayout this$02) {
            Rect rect;
            this.this$0 = this$02;
            new Rect();
            this.mTmpRect = rect;
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            View host = view;
            AccessibilityNodeInfoCompat info = accessibilityNodeInfoCompat;
            if (DrawerLayout.CAN_HIDE_DESCENDANTS) {
                super.onInitializeAccessibilityNodeInfo(host, info);
            } else {
                AccessibilityNodeInfoCompat superNode = AccessibilityNodeInfoCompat.obtain(info);
                super.onInitializeAccessibilityNodeInfo(host, superNode);
                info.setSource(host);
                ViewParent parent = ViewCompat.getParentForAccessibility(host);
                if (parent instanceof View) {
                    info.setParent((View) parent);
                }
                copyNodeInfoNoChildren(info, superNode);
                superNode.recycle();
                addChildrenForAccessibility(info, (ViewGroup) host);
            }
            info.setClassName(DrawerLayout.class.getName());
            info.setFocusable(false);
            info.setFocused(false);
            boolean removeAction = info.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_FOCUS);
            boolean removeAction2 = info.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_FOCUS);
        }

        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent accessibilityEvent) {
            AccessibilityEvent event = accessibilityEvent;
            super.onInitializeAccessibilityEvent(host, event);
            event.setClassName(DrawerLayout.class.getName());
        }

        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            CharSequence title;
            View host = view;
            AccessibilityEvent event = accessibilityEvent;
            if (event.getEventType() != 32) {
                return super.dispatchPopulateAccessibilityEvent(host, event);
            }
            List text = event.getText();
            View visibleDrawer = this.this$0.findVisibleDrawer();
            if (!(visibleDrawer == null || (title = this.this$0.getDrawerTitle(this.this$0.getDrawerViewAbsoluteGravity(visibleDrawer))) == null)) {
                boolean add = text.add(title);
            }
            return true;
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            ViewGroup host = viewGroup;
            View child = view;
            AccessibilityEvent event = accessibilityEvent;
            if (DrawerLayout.CAN_HIDE_DESCENDANTS || DrawerLayout.includeChildForAccessibility(child)) {
                return super.onRequestSendAccessibilityEvent(host, child, event);
            }
            return false;
        }

        private void addChildrenForAccessibility(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, ViewGroup viewGroup) {
            AccessibilityNodeInfoCompat info = accessibilityNodeInfoCompat;
            ViewGroup v = viewGroup;
            int childCount = v.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = v.getChildAt(i);
                if (DrawerLayout.includeChildForAccessibility(child)) {
                    info.addChild(child);
                }
            }
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
        }
    }

    /* renamed from: android.support.v4.widget.DrawerLayout$ChildAccessibilityDelegate */
    static final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat {
        ChildAccessibilityDelegate() {
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            View child = view;
            AccessibilityNodeInfoCompat info = accessibilityNodeInfoCompat;
            super.onInitializeAccessibilityNodeInfo(child, info);
            if (!DrawerLayout.includeChildForAccessibility(child)) {
                info.setParent((View) null);
            }
        }
    }
}
