package android.support.p003v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.C0015Px;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.p000v4.p002os.TraceCompat;
import android.support.p000v4.util.Preconditions;
import android.support.p000v4.view.AbsSavedState;
import android.support.p000v4.view.AccessibilityDelegateCompat;
import android.support.p000v4.view.MotionEventCompat;
import android.support.p000v4.view.NestedScrollingChild2;
import android.support.p000v4.view.NestedScrollingChildHelper;
import android.support.p000v4.view.ScrollingView;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.accessibility.AccessibilityEventCompat;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p000v4.widget.EdgeEffectCompat;
import android.support.p003v7.recyclerview.C0433R;
import android.support.p003v7.widget.AdapterHelper;
import android.support.p003v7.widget.ChildHelper;
import android.support.p003v7.widget.GapWorker;
import android.support.p003v7.widget.ViewBoundsCheck;
import android.support.p003v7.widget.ViewInfoStore;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import gnu.expr.Declaration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: android.support.v7.widget.RecyclerView */
public class RecyclerView extends ViewGroup implements ScrollingView, NestedScrollingChild2 {
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC = (Build.VERSION.SDK_INT >= 23);
    static final boolean ALLOW_THREAD_GAP_WORK = (Build.VERSION.SDK_INT >= 21);
    private static final int[] CLIP_TO_PADDING_ATTR = {16842987};
    static final boolean DEBUG = false;
    static final int DEFAULT_ORIENTATION = 1;
    static final boolean DISPATCH_TEMP_DETACH = false;
    private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION = (Build.VERSION.SDK_INT <= 15);
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST = (Build.VERSION.SDK_INT == 18 || Build.VERSION.SDK_INT == 19 || Build.VERSION.SDK_INT == 20);
    static final long FOREVER_NS = Long.MAX_VALUE;
    public static final int HORIZONTAL = 0;
    private static final boolean IGNORE_DETACHED_FOCUSED_CHILD;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    static final int MAX_SCROLL_DURATION = 2000;
    private static final int[] NESTED_SCROLLING_ATTRS = {16843830};
    public static final long NO_ID = -1;
    public static final int NO_POSITION = -1;
    static final boolean POST_UPDATES_ON_ANIMATION = (Build.VERSION.SDK_INT >= 16);
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    static final String TAG = "RecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    static final String TRACE_PREFETCH_TAG = "RV Prefetch";
    static final String TRACE_SCROLL_TAG = "RV Scroll";
    static final boolean VERBOSE_TRACING = false;
    public static final int VERTICAL = 1;
    static final Interpolator sQuinticInterpolator;
    RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    private OnItemTouchListener mActiveOnItemTouchListener;
    Adapter mAdapter;
    AdapterHelper mAdapterHelper;
    boolean mAdapterUpdateDuringMeasure;
    private EdgeEffect mBottomGlow;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    ChildHelper mChildHelper;
    boolean mClipToPadding;
    boolean mDataSetHasChangedAfterLayout;
    boolean mDispatchItemsChangedEvent;
    private int mDispatchScrollCounter;
    private int mEatenAccessibilityChangeFlags;
    @NonNull
    private EdgeEffectFactory mEdgeEffectFactory;
    boolean mEnableFastScroller;
    @VisibleForTesting
    boolean mFirstLayoutComplete;
    GapWorker mGapWorker;
    boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mInterceptRequestLayoutDepth;
    boolean mIsAttached;
    ItemAnimator mItemAnimator;
    private ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    final ArrayList<ItemDecoration> mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;
    @VisibleForTesting
    LayoutManager mLayout;
    boolean mLayoutFrozen;
    private int mLayoutOrScrollCounter;
    boolean mLayoutWasDefered;
    private EdgeEffect mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int[] mNestedOffsets;
    private final RecyclerViewDataObserver mObserver;
    private List<OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
    private OnFlingListener mOnFlingListener;
    private final ArrayList<OnItemTouchListener> mOnItemTouchListeners;
    @VisibleForTesting
    final List<ViewHolder> mPendingAccessibilityImportanceChange;
    private SavedState mPendingSavedState;
    boolean mPostedAnimatorRunner;
    GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
    private boolean mPreserveFocusAfterLayout;
    final Recycler mRecycler;
    RecyclerListener mRecyclerListener;
    private EdgeEffect mRightGlow;
    private float mScaledHorizontalScrollFactor;
    private float mScaledVerticalScrollFactor;
    final int[] mScrollConsumed;
    private OnScrollListener mScrollListener;
    private List<OnScrollListener> mScrollListeners;
    private final int[] mScrollOffset;
    private int mScrollPointerId;
    private int mScrollState;
    final int[] mScrollStepConsumed;
    private NestedScrollingChildHelper mScrollingChildHelper;
    final State mState;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    private EdgeEffect mTopGlow;
    private int mTouchSlop;
    final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    final ViewFlinger mViewFlinger;
    private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
    final ViewInfoStore mViewInfoStore;

    /* renamed from: android.support.v7.widget.RecyclerView$ChildDrawingOrderCallback */
    public interface ChildDrawingOrderCallback {
        int onGetChildDrawingOrder(int i, int i2);
    }

    /* renamed from: android.support.v7.widget.RecyclerView$OnChildAttachStateChangeListener */
    public interface OnChildAttachStateChangeListener {
        void onChildViewAttachedToWindow(@NonNull View view);

        void onChildViewDetachedFromWindow(@NonNull View view);
    }

    /* renamed from: android.support.v7.widget.RecyclerView$OnItemTouchListener */
    public interface OnItemTouchListener {
        boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent);

        void onRequestDisallowInterceptTouchEvent(boolean z);

        void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v7.widget.RecyclerView$Orientation */
    public @interface Orientation {
    }

    /* renamed from: android.support.v7.widget.RecyclerView$RecyclerListener */
    public interface RecyclerListener {
        void onViewRecycled(@NonNull ViewHolder viewHolder);
    }

    static {
        boolean z;
        Interpolator interpolator;
        if (Build.VERSION.SDK_INT <= 15) {
            z = true;
        } else {
            z = false;
        }
        IGNORE_DETACHED_FOCUSED_CHILD = z;
        Class<?>[] clsArr = new Class[4];
        clsArr[0] = Context.class;
        Class<?>[] clsArr2 = clsArr;
        clsArr2[1] = AttributeSet.class;
        Class<?>[] clsArr3 = clsArr2;
        clsArr3[2] = Integer.TYPE;
        Class<?>[] clsArr4 = clsArr3;
        clsArr4[3] = Integer.TYPE;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = clsArr4;
        new Interpolator() {
            public float getInterpolation(float t) {
                float t2 = t - 1.0f;
                return (t2 * t2 * t2 * t2 * t2) + 1.0f;
            }
        };
        sQuinticInterpolator = interpolator;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RecyclerView(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RecyclerView(@android.support.annotation.NonNull android.content.Context r24, @android.support.annotation.Nullable android.util.AttributeSet r25, int r26) {
        /*
            r23 = this;
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r16 = r2
            r17 = r3
            r18 = r4
            r19 = r5
            r16.<init>(r17, r18, r19)
            r16 = r2
            android.support.v7.widget.RecyclerView$RecyclerViewDataObserver r17 = new android.support.v7.widget.RecyclerView$RecyclerViewDataObserver
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r2
            r18.<init>(r19)
            r0 = r17
            r1 = r16
            r1.mObserver = r0
            r16 = r2
            android.support.v7.widget.RecyclerView$Recycler r17 = new android.support.v7.widget.RecyclerView$Recycler
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r2
            r18.<init>(r19)
            r0 = r17
            r1 = r16
            r1.mRecycler = r0
            r16 = r2
            android.support.v7.widget.ViewInfoStore r17 = new android.support.v7.widget.ViewInfoStore
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r0 = r17
            r1 = r16
            r1.mViewInfoStore = r0
            r16 = r2
            android.support.v7.widget.RecyclerView$1 r17 = new android.support.v7.widget.RecyclerView$1
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r2
            r18.<init>(r19)
            r0 = r17
            r1 = r16
            r1.mUpdateChildViewsRunnable = r0
            r16 = r2
            android.graphics.Rect r17 = new android.graphics.Rect
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r0 = r17
            r1 = r16
            r1.mTempRect = r0
            r16 = r2
            android.graphics.Rect r17 = new android.graphics.Rect
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r0 = r17
            r1 = r16
            r1.mTempRect2 = r0
            r16 = r2
            android.graphics.RectF r17 = new android.graphics.RectF
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r0 = r17
            r1 = r16
            r1.mTempRectF = r0
            r16 = r2
            java.util.ArrayList r17 = new java.util.ArrayList
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r0 = r17
            r1 = r16
            r1.mItemDecorations = r0
            r16 = r2
            java.util.ArrayList r17 = new java.util.ArrayList
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r0 = r17
            r1 = r16
            r1.mOnItemTouchListeners = r0
            r16 = r2
            r17 = 0
            r0 = r17
            r1 = r16
            r1.mInterceptRequestLayoutDepth = r0
            r16 = r2
            r17 = 0
            r0 = r17
            r1 = r16
            r1.mDataSetHasChangedAfterLayout = r0
            r16 = r2
            r17 = 0
            r0 = r17
            r1 = r16
            r1.mDispatchItemsChangedEvent = r0
            r16 = r2
            r17 = 0
            r0 = r17
            r1 = r16
            r1.mLayoutOrScrollCounter = r0
            r16 = r2
            r17 = 0
            r0 = r17
            r1 = r16
            r1.mDispatchScrollCounter = r0
            r16 = r2
            android.support.v7.widget.RecyclerView$EdgeEffectFactory r17 = new android.support.v7.widget.RecyclerView$EdgeEffectFactory
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r0 = r17
            r1 = r16
            r1.mEdgeEffectFactory = r0
            r16 = r2
            android.support.v7.widget.DefaultItemAnimator r17 = new android.support.v7.widget.DefaultItemAnimator
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r0 = r17
            r1 = r16
            r1.mItemAnimator = r0
            r16 = r2
            r17 = 0
            r0 = r17
            r1 = r16
            r1.mScrollState = r0
            r16 = r2
            r17 = -1
            r0 = r17
            r1 = r16
            r1.mScrollPointerId = r0
            r16 = r2
            r17 = 1
            r0 = r17
            r1 = r16
            r1.mScaledHorizontalScrollFactor = r0
            r16 = r2
            r17 = 1
            r0 = r17
            r1 = r16
            r1.mScaledVerticalScrollFactor = r0
            r16 = r2
            r17 = 1
            r0 = r17
            r1 = r16
            r1.mPreserveFocusAfterLayout = r0
            r16 = r2
            android.support.v7.widget.RecyclerView$ViewFlinger r17 = new android.support.v7.widget.RecyclerView$ViewFlinger
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r2
            r18.<init>(r19)
            r0 = r17
            r1 = r16
            r1.mViewFlinger = r0
            r16 = r2
            boolean r17 = ALLOW_THREAD_GAP_WORK
            if (r17 == 0) goto L_0x0426
            android.support.v7.widget.GapWorker$LayoutPrefetchRegistryImpl r17 = new android.support.v7.widget.GapWorker$LayoutPrefetchRegistryImpl
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
        L_0x0174:
            r0 = r17
            r1 = r16
            r1.mPrefetchRegistry = r0
            r16 = r2
            android.support.v7.widget.RecyclerView$State r17 = new android.support.v7.widget.RecyclerView$State
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r0 = r17
            r1 = r16
            r1.mState = r0
            r16 = r2
            r17 = 0
            r0 = r17
            r1 = r16
            r1.mItemsAddedOrRemoved = r0
            r16 = r2
            r17 = 0
            r0 = r17
            r1 = r16
            r1.mItemsChanged = r0
            r16 = r2
            android.support.v7.widget.RecyclerView$ItemAnimatorRestoreListener r17 = new android.support.v7.widget.RecyclerView$ItemAnimatorRestoreListener
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r2
            r18.<init>(r19)
            r0 = r17
            r1 = r16
            r1.mItemAnimatorListener = r0
            r16 = r2
            r17 = 0
            r0 = r17
            r1 = r16
            r1.mPostedAnimatorRunner = r0
            r16 = r2
            r17 = 2
            r0 = r17
            int[] r0 = new int[r0]
            r17 = r0
            r0 = r17
            r1 = r16
            r1.mMinMaxLayoutPositions = r0
            r16 = r2
            r17 = 2
            r0 = r17
            int[] r0 = new int[r0]
            r17 = r0
            r0 = r17
            r1 = r16
            r1.mScrollOffset = r0
            r16 = r2
            r17 = 2
            r0 = r17
            int[] r0 = new int[r0]
            r17 = r0
            r0 = r17
            r1 = r16
            r1.mScrollConsumed = r0
            r16 = r2
            r17 = 2
            r0 = r17
            int[] r0 = new int[r0]
            r17 = r0
            r0 = r17
            r1 = r16
            r1.mNestedOffsets = r0
            r16 = r2
            r17 = 2
            r0 = r17
            int[] r0 = new int[r0]
            r17 = r0
            r0 = r17
            r1 = r16
            r1.mScrollStepConsumed = r0
            r16 = r2
            java.util.ArrayList r17 = new java.util.ArrayList
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r0 = r17
            r1 = r16
            r1.mPendingAccessibilityImportanceChange = r0
            r16 = r2
            android.support.v7.widget.RecyclerView$2 r17 = new android.support.v7.widget.RecyclerView$2
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r2
            r18.<init>(r19)
            r0 = r17
            r1 = r16
            r1.mItemAnimatorRunner = r0
            r16 = r2
            android.support.v7.widget.RecyclerView$4 r17 = new android.support.v7.widget.RecyclerView$4
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r2
            r18.<init>(r19)
            r0 = r17
            r1 = r16
            r1.mViewInfoProcessCallback = r0
            r16 = r4
            if (r16 == 0) goto L_0x042a
            r16 = r3
            r17 = r4
            int[] r18 = CLIP_TO_PADDING_ATTR
            r19 = r5
            r20 = 0
            android.content.res.TypedArray r16 = r16.obtainStyledAttributes(r17, r18, r19, r20)
            r6 = r16
            r16 = r2
            r17 = r6
            r18 = 0
            r19 = 1
            boolean r17 = r17.getBoolean(r18, r19)
            r0 = r17
            r1 = r16
            r1.mClipToPadding = r0
            r16 = r6
            r16.recycle()
        L_0x0278:
            r16 = r2
            r17 = 1
            r16.setScrollContainer(r17)
            r16 = r2
            r17 = 1
            r16.setFocusableInTouchMode(r17)
            r16 = r3
            android.view.ViewConfiguration r16 = android.view.ViewConfiguration.get(r16)
            r6 = r16
            r16 = r2
            r17 = r6
            int r17 = r17.getScaledTouchSlop()
            r0 = r17
            r1 = r16
            r1.mTouchSlop = r0
            r16 = r2
            r17 = r6
            r18 = r3
            float r17 = android.support.p000v4.view.ViewConfigurationCompat.getScaledHorizontalScrollFactor(r17, r18)
            r0 = r17
            r1 = r16
            r1.mScaledHorizontalScrollFactor = r0
            r16 = r2
            r17 = r6
            r18 = r3
            float r17 = android.support.p000v4.view.ViewConfigurationCompat.getScaledVerticalScrollFactor(r17, r18)
            r0 = r17
            r1 = r16
            r1.mScaledVerticalScrollFactor = r0
            r16 = r2
            r17 = r6
            int r17 = r17.getScaledMinimumFlingVelocity()
            r0 = r17
            r1 = r16
            r1.mMinFlingVelocity = r0
            r16 = r2
            r17 = r6
            int r17 = r17.getScaledMaximumFlingVelocity()
            r0 = r17
            r1 = r16
            r1.mMaxFlingVelocity = r0
            r16 = r2
            r17 = r2
            int r17 = r17.getOverScrollMode()
            r18 = 2
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0436
            r17 = 1
        L_0x02ea:
            r16.setWillNotDraw(r17)
            r16 = r2
            r0 = r16
            android.support.v7.widget.RecyclerView$ItemAnimator r0 = r0.mItemAnimator
            r16 = r0
            r17 = r2
            r0 = r17
            android.support.v7.widget.RecyclerView$ItemAnimator$ItemAnimatorListener r0 = r0.mItemAnimatorListener
            r17 = r0
            r16.setListener(r17)
            r16 = r2
            r16.initAdapterManager()
            r16 = r2
            r16.initChildrenHelper()
            r16 = r2
            r16.initAutofill()
            r16 = r2
            int r16 = android.support.p000v4.view.ViewCompat.getImportantForAccessibility(r16)
            if (r16 != 0) goto L_0x031e
            r16 = r2
            r17 = 1
            android.support.p000v4.view.ViewCompat.setImportantForAccessibility(r16, r17)
        L_0x031e:
            r16 = r2
            r17 = r2
            android.content.Context r17 = r17.getContext()
            java.lang.String r18 = "accessibility"
            java.lang.Object r17 = r17.getSystemService(r18)
            android.view.accessibility.AccessibilityManager r17 = (android.view.accessibility.AccessibilityManager) r17
            r0 = r17
            r1 = r16
            r1.mAccessibilityManager = r0
            r16 = r2
            android.support.v7.widget.RecyclerViewAccessibilityDelegate r17 = new android.support.v7.widget.RecyclerViewAccessibilityDelegate
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r2
            r18.<init>(r19)
            r16.setAccessibilityDelegateCompat(r17)
            r16 = 1
            r7 = r16
            r16 = r4
            if (r16 == 0) goto L_0x043a
            r16 = 0
            r8 = r16
            r16 = r3
            r17 = r4
            int[] r18 = android.support.p003v7.recyclerview.C0433R.styleable.RecyclerView
            r19 = r5
            r20 = r8
            android.content.res.TypedArray r16 = r16.obtainStyledAttributes(r17, r18, r19, r20)
            r9 = r16
            r16 = r9
            int r17 = android.support.p003v7.recyclerview.C0433R.styleable.RecyclerView_layoutManager
            java.lang.String r16 = r16.getString(r17)
            r10 = r16
            r16 = r9
            int r17 = android.support.p003v7.recyclerview.C0433R.styleable.RecyclerView_android_descendantFocusability
            r18 = -1
            int r16 = r16.getInt(r17, r18)
            r11 = r16
            r16 = r11
            r17 = -1
            r0 = r16
            r1 = r17
            if (r0 != r1) goto L_0x038a
            r16 = r2
            r17 = 262144(0x40000, float:3.67342E-40)
            r16.setDescendantFocusability(r17)
        L_0x038a:
            r16 = r2
            r17 = r9
            int r18 = android.support.p003v7.recyclerview.C0433R.styleable.RecyclerView_fastScrollEnabled
            r19 = 0
            boolean r17 = r17.getBoolean(r18, r19)
            r0 = r17
            r1 = r16
            r1.mEnableFastScroller = r0
            r16 = r2
            r0 = r16
            boolean r0 = r0.mEnableFastScroller
            r16 = r0
            if (r16 == 0) goto L_0x03df
            r16 = r9
            int r17 = android.support.p003v7.recyclerview.C0433R.styleable.RecyclerView_fastScrollVerticalThumbDrawable
            android.graphics.drawable.Drawable r16 = r16.getDrawable(r17)
            android.graphics.drawable.StateListDrawable r16 = (android.graphics.drawable.StateListDrawable) r16
            r12 = r16
            r16 = r9
            int r17 = android.support.p003v7.recyclerview.C0433R.styleable.RecyclerView_fastScrollVerticalTrackDrawable
            android.graphics.drawable.Drawable r16 = r16.getDrawable(r17)
            r13 = r16
            r16 = r9
            int r17 = android.support.p003v7.recyclerview.C0433R.styleable.RecyclerView_fastScrollHorizontalThumbDrawable
            android.graphics.drawable.Drawable r16 = r16.getDrawable(r17)
            android.graphics.drawable.StateListDrawable r16 = (android.graphics.drawable.StateListDrawable) r16
            r14 = r16
            r16 = r9
            int r17 = android.support.p003v7.recyclerview.C0433R.styleable.RecyclerView_fastScrollHorizontalTrackDrawable
            android.graphics.drawable.Drawable r16 = r16.getDrawable(r17)
            r15 = r16
            r16 = r2
            r17 = r12
            r18 = r13
            r19 = r14
            r20 = r15
            r16.initFastScroller(r17, r18, r19, r20)
        L_0x03df:
            r16 = r9
            r16.recycle()
            r16 = r2
            r17 = r3
            r18 = r10
            r19 = r4
            r20 = r5
            r21 = r8
            r16.createLayoutManager(r17, r18, r19, r20, r21)
            int r16 = android.os.Build.VERSION.SDK_INT
            r17 = 21
            r0 = r16
            r1 = r17
            if (r0 < r1) goto L_0x041e
            r16 = r3
            r17 = r4
            int[] r18 = NESTED_SCROLLING_ATTRS
            r19 = r5
            r20 = r8
            android.content.res.TypedArray r16 = r16.obtainStyledAttributes(r17, r18, r19, r20)
            r9 = r16
            r16 = r9
            r17 = 0
            r18 = 1
            boolean r16 = r16.getBoolean(r17, r18)
            r7 = r16
            r16 = r9
            r16.recycle()
        L_0x041e:
            r16 = r2
            r17 = r7
            r16.setNestedScrollingEnabled(r17)
            return
        L_0x0426:
            r17 = 0
            goto L_0x0174
        L_0x042a:
            r16 = r2
            r17 = 1
            r0 = r17
            r1 = r16
            r1.mClipToPadding = r0
            goto L_0x0278
        L_0x0436:
            r17 = 0
            goto L_0x02ea
        L_0x043a:
            r16 = r2
            r17 = 262144(0x40000, float:3.67342E-40)
            r16.setDescendantFocusability(r17)
            goto L_0x041e
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.RecyclerView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* access modifiers changed from: package-private */
    public String exceptionLabel() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(super.toString()).append(", adapter:").append(this.mAdapter).append(", layout:").append(this.mLayout).append(", context:").append(getContext()).toString();
    }

    @SuppressLint({"InlinedApi"})
    private void initAutofill() {
        if (ViewCompat.getImportantForAutofill(this) == 0) {
            ViewCompat.setImportantForAutofill(this, 8);
        }
    }

    @Nullable
    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    public void setAccessibilityDelegateCompat(@Nullable RecyclerViewAccessibilityDelegate accessibilityDelegate) {
        this.mAccessibilityDelegate = accessibilityDelegate;
        ViewCompat.setAccessibilityDelegate(this, this.mAccessibilityDelegate);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void createLayoutManager(android.content.Context r18, java.lang.String r19, android.util.AttributeSet r20, int r21, int r22) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            r5 = r22
            r12 = r2
            if (r12 == 0) goto L_0x0091
            r12 = r2
            java.lang.String r12 = r12.trim()
            r2 = r12
            r12 = r2
            boolean r12 = r12.isEmpty()
            if (r12 != 0) goto L_0x0091
            r12 = r0
            r13 = r1
            r14 = r2
            java.lang.String r12 = r12.getFullClassName(r13, r14)
            r2 = r12
            r12 = r0
            boolean r12 = r12.isInEditMode()     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            if (r12 == 0) goto L_0x0092
            r12 = r0
            java.lang.Class r12 = r12.getClass()     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            java.lang.ClassLoader r12 = r12.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            r6 = r12
        L_0x0035:
            r12 = r6
            r13 = r2
            java.lang.Class r12 = r12.loadClass(r13)     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            java.lang.Class<android.support.v7.widget.RecyclerView$LayoutManager> r13 = android.support.p003v7.widget.RecyclerView.LayoutManager.class
            java.lang.Class r12 = r12.asSubclass(r13)     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            r7 = r12
            r12 = 0
            r9 = r12
            r12 = r7
            java.lang.Class<?>[] r13 = LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE     // Catch:{ NoSuchMethodException -> 0x0099 }
            java.lang.reflect.Constructor r12 = r12.getConstructor(r13)     // Catch:{ NoSuchMethodException -> 0x0099 }
            r8 = r12
            r12 = 4
            java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch:{ NoSuchMethodException -> 0x0099 }
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = 0
            r15 = r1
            r13[r14] = r15     // Catch:{ NoSuchMethodException -> 0x0099 }
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = 1
            r15 = r3
            r13[r14] = r15     // Catch:{ NoSuchMethodException -> 0x0099 }
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = 2
            r15 = r4
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ NoSuchMethodException -> 0x0099 }
            r13[r14] = r15     // Catch:{ NoSuchMethodException -> 0x0099 }
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = 3
            r15 = r5
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ NoSuchMethodException -> 0x0099 }
            r13[r14] = r15     // Catch:{ NoSuchMethodException -> 0x0099 }
            r9 = r12
        L_0x0080:
            r12 = r8
            r13 = 1
            r12.setAccessible(r13)     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            r12 = r0
            r13 = r8
            r14 = r9
            java.lang.Object r13 = r13.newInstance(r14)     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            android.support.v7.widget.RecyclerView$LayoutManager r13 = (android.support.p003v7.widget.RecyclerView.LayoutManager) r13     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            r12.setLayoutManager(r13)     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
        L_0x0091:
            return
        L_0x0092:
            r12 = r1
            java.lang.ClassLoader r12 = r12.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            r6 = r12
            goto L_0x0035
        L_0x0099:
            r12 = move-exception
            r10 = r12
            r12 = r7
            r13 = 0
            java.lang.Class[] r13 = new java.lang.Class[r13]     // Catch:{ NoSuchMethodException -> 0x00a5 }
            java.lang.reflect.Constructor r12 = r12.getConstructor(r13)     // Catch:{ NoSuchMethodException -> 0x00a5 }
            r8 = r12
            goto L_0x0080
        L_0x00a5:
            r12 = move-exception
            r11 = r12
            r12 = r11
            r13 = r10
            java.lang.Throwable r12 = r12.initCause(r13)     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            r16 = r12
            r12 = r16
            r13 = r16
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            r15 = r3
            java.lang.String r15 = r15.getPositionDescription()     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            java.lang.String r15 = ": Error creating LayoutManager "
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            r15 = r2
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            java.lang.String r14 = r14.toString()     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            r15 = r11
            r13.<init>(r14, r15)     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
            throw r12     // Catch:{ ClassNotFoundException -> 0x00de, InvocationTargetException -> 0x0111, InstantiationException -> 0x0144, IllegalAccessException -> 0x0177, ClassCastException -> 0x01aa }
        L_0x00de:
            r12 = move-exception
            r6 = r12
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            r16 = r12
            r12 = r16
            r13 = r16
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()
            r15 = r3
            java.lang.String r15 = r15.getPositionDescription()
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = ": Unable to find LayoutManager "
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r2
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            r15 = r6
            r13.<init>(r14, r15)
            throw r12
        L_0x0111:
            r12 = move-exception
            r6 = r12
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            r16 = r12
            r12 = r16
            r13 = r16
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()
            r15 = r3
            java.lang.String r15 = r15.getPositionDescription()
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = ": Could not instantiate the LayoutManager: "
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r2
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            r15 = r6
            r13.<init>(r14, r15)
            throw r12
        L_0x0144:
            r12 = move-exception
            r6 = r12
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            r16 = r12
            r12 = r16
            r13 = r16
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()
            r15 = r3
            java.lang.String r15 = r15.getPositionDescription()
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = ": Could not instantiate the LayoutManager: "
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r2
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            r15 = r6
            r13.<init>(r14, r15)
            throw r12
        L_0x0177:
            r12 = move-exception
            r6 = r12
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            r16 = r12
            r12 = r16
            r13 = r16
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()
            r15 = r3
            java.lang.String r15 = r15.getPositionDescription()
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = ": Cannot access non-public constructor "
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r2
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            r15 = r6
            r13.<init>(r14, r15)
            throw r12
        L_0x01aa:
            r12 = move-exception
            r6 = r12
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            r16 = r12
            r12 = r16
            r13 = r16
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()
            r15 = r3
            java.lang.String r15 = r15.getPositionDescription()
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = ": Class is not a LayoutManager "
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r2
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            r15 = r6
            r13.<init>(r14, r15)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.RecyclerView.createLayoutManager(android.content.Context, java.lang.String, android.util.AttributeSet, int, int):void");
    }

    private String getFullClassName(Context context, String str) {
        StringBuilder sb;
        StringBuilder sb2;
        Context context2 = context;
        String className = str;
        if (className.charAt(0) == '.') {
            new StringBuilder();
            return sb2.append(context2.getPackageName()).append(className).toString();
        } else if (className.contains(".")) {
            return className;
        } else {
            new StringBuilder();
            return sb.append(RecyclerView.class.getPackage().getName()).append('.').append(className).toString();
        }
    }

    private void initChildrenHelper() {
        ChildHelper childHelper;
        ChildHelper.Callback callback;
        new ChildHelper.Callback(this) {
            final /* synthetic */ RecyclerView this$0;

            {
                this.this$0 = this$0;
            }

            public int getChildCount() {
                return this.this$0.getChildCount();
            }

            public void addView(View view, int index) {
                View child = view;
                this.this$0.addView(child, index);
                this.this$0.dispatchChildAttached(child);
            }

            public int indexOfChild(View view) {
                return this.this$0.indexOfChild(view);
            }

            public void removeViewAt(int i) {
                int index = i;
                View child = this.this$0.getChildAt(index);
                if (child != null) {
                    this.this$0.dispatchChildDetached(child);
                    child.clearAnimation();
                }
                this.this$0.removeViewAt(index);
            }

            public View getChildAt(int offset) {
                return this.this$0.getChildAt(offset);
            }

            public void removeAllViews() {
                int count = getChildCount();
                for (int i = 0; i < count; i++) {
                    View child = getChildAt(i);
                    this.this$0.dispatchChildDetached(child);
                    child.clearAnimation();
                }
                this.this$0.removeAllViews();
            }

            public ViewHolder getChildViewHolder(View view) {
                return RecyclerView.getChildViewHolderInt(view);
            }

            public void attachViewToParent(View view, int i, ViewGroup.LayoutParams layoutParams) {
                Throwable th;
                StringBuilder sb;
                View child = view;
                int index = i;
                ViewGroup.LayoutParams layoutParams2 = layoutParams;
                ViewHolder vh = RecyclerView.getChildViewHolderInt(child);
                if (vh != null) {
                    if (vh.isTmpDetached() || vh.shouldIgnore()) {
                        vh.clearTmpDetachFlag();
                    } else {
                        Throwable th2 = th;
                        new StringBuilder();
                        new IllegalArgumentException(sb.append("Called attach on a child which is not detached: ").append(vh).append(this.this$0.exceptionLabel()).toString());
                        throw th2;
                    }
                }
                this.this$0.attachViewToParent(child, index, layoutParams2);
            }

            public void detachViewFromParent(int i) {
                ViewHolder vh;
                Throwable th;
                StringBuilder sb;
                int offset = i;
                View view = getChildAt(offset);
                if (!(view == null || (vh = RecyclerView.getChildViewHolderInt(view)) == null)) {
                    if (!vh.isTmpDetached() || vh.shouldIgnore()) {
                        vh.addFlags(256);
                    } else {
                        Throwable th2 = th;
                        new StringBuilder();
                        new IllegalArgumentException(sb.append("called detach on an already detached child ").append(vh).append(this.this$0.exceptionLabel()).toString());
                        throw th2;
                    }
                }
                this.this$0.detachViewFromParent(offset);
            }

            public void onEnteredHiddenState(View child) {
                ViewHolder vh = RecyclerView.getChildViewHolderInt(child);
                if (vh != null) {
                    vh.onEnteredHiddenState(this.this$0);
                }
            }

            public void onLeftHiddenState(View child) {
                ViewHolder vh = RecyclerView.getChildViewHolderInt(child);
                if (vh != null) {
                    vh.onLeftHiddenState(this.this$0);
                }
            }
        };
        new ChildHelper(callback);
        this.mChildHelper = childHelper;
    }

    /* access modifiers changed from: package-private */
    public void initAdapterManager() {
        AdapterHelper adapterHelper;
        AdapterHelper.Callback callback;
        new AdapterHelper.Callback(this) {
            final /* synthetic */ RecyclerView this$0;

            {
                this.this$0 = this$0;
            }

            public ViewHolder findViewHolder(int position) {
                ViewHolder vh = this.this$0.findViewHolderForPosition(position, true);
                if (vh == null) {
                    return null;
                }
                if (this.this$0.mChildHelper.isHidden(vh.itemView)) {
                    return null;
                }
                return vh;
            }

            public void offsetPositionsForRemovingInvisible(int start, int i) {
                int count = i;
                this.this$0.offsetPositionRecordsForRemove(start, count, true);
                this.this$0.mItemsAddedOrRemoved = true;
                this.this$0.mState.mDeletedInvisibleItemCountSincePreviousLayout += count;
            }

            public void offsetPositionsForRemovingLaidOutOrNewView(int positionStart, int itemCount) {
                this.this$0.offsetPositionRecordsForRemove(positionStart, itemCount, false);
                this.this$0.mItemsAddedOrRemoved = true;
            }

            public void markViewHoldersUpdated(int positionStart, int itemCount, Object payload) {
                this.this$0.viewRangeUpdate(positionStart, itemCount, payload);
                this.this$0.mItemsChanged = true;
            }

            public void onDispatchFirstPass(AdapterHelper.UpdateOp op) {
                dispatchUpdate(op);
            }

            /* access modifiers changed from: package-private */
            public void dispatchUpdate(AdapterHelper.UpdateOp updateOp) {
                AdapterHelper.UpdateOp op = updateOp;
                switch (op.cmd) {
                    case 1:
                        this.this$0.mLayout.onItemsAdded(this.this$0, op.positionStart, op.itemCount);
                        return;
                    case 2:
                        this.this$0.mLayout.onItemsRemoved(this.this$0, op.positionStart, op.itemCount);
                        return;
                    case 4:
                        this.this$0.mLayout.onItemsUpdated(this.this$0, op.positionStart, op.itemCount, op.payload);
                        return;
                    case 8:
                        this.this$0.mLayout.onItemsMoved(this.this$0, op.positionStart, op.itemCount, 1);
                        return;
                    default:
                        return;
                }
            }

            public void onDispatchSecondPass(AdapterHelper.UpdateOp op) {
                dispatchUpdate(op);
            }

            public void offsetPositionsForAdd(int positionStart, int itemCount) {
                this.this$0.offsetPositionRecordsForInsert(positionStart, itemCount);
                this.this$0.mItemsAddedOrRemoved = true;
            }

            public void offsetPositionsForMove(int from, int to) {
                this.this$0.offsetPositionRecordsForMove(from, to);
                this.this$0.mItemsAddedOrRemoved = true;
            }
        };
        new AdapterHelper(callback);
        this.mAdapterHelper = adapterHelper;
    }

    public void setHasFixedSize(boolean hasFixedSize) {
        boolean z = hasFixedSize;
        this.mHasFixedSize = z;
    }

    public boolean hasFixedSize() {
        return this.mHasFixedSize;
    }

    public void setClipToPadding(boolean z) {
        boolean clipToPadding = z;
        if (clipToPadding != this.mClipToPadding) {
            invalidateGlows();
        }
        this.mClipToPadding = clipToPadding;
        super.setClipToPadding(clipToPadding);
        if (this.mFirstLayoutComplete) {
            requestLayout();
        }
    }

    public boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    public void setScrollingTouchSlop(int i) {
        StringBuilder sb;
        int slopConstant = i;
        ViewConfiguration vc = ViewConfiguration.get(getContext());
        switch (slopConstant) {
            case 0:
                break;
            case 1:
                this.mTouchSlop = vc.getScaledPagingTouchSlop();
                return;
            default:
                new StringBuilder();
                int w = Log.w(TAG, sb.append("setScrollingTouchSlop(): bad argument constant ").append(slopConstant).append("; using default value").toString());
                break;
        }
        this.mTouchSlop = vc.getScaledTouchSlop();
    }

    public void swapAdapter(@Nullable Adapter adapter, boolean removeAndRecycleExistingViews) {
        setLayoutFrozen(false);
        setAdapterInternal(adapter, true, removeAndRecycleExistingViews);
        processDataSetCompletelyChanged(true);
        requestLayout();
    }

    public void setAdapter(@Nullable Adapter adapter) {
        setLayoutFrozen(false);
        setAdapterInternal(adapter, false, true);
        processDataSetCompletelyChanged(false);
        requestLayout();
    }

    /* access modifiers changed from: package-private */
    public void removeAndRecycleViews() {
        if (this.mItemAnimator != null) {
            this.mItemAnimator.endAnimations();
        }
        if (this.mLayout != null) {
            this.mLayout.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        }
        this.mRecycler.clear();
    }

    private void setAdapterInternal(@Nullable Adapter adapter, boolean z, boolean z2) {
        Adapter adapter2 = adapter;
        boolean compatibleWithPrevious = z;
        boolean removeAndRecycleViews = z2;
        if (this.mAdapter != null) {
            this.mAdapter.unregisterAdapterDataObserver(this.mObserver);
            this.mAdapter.onDetachedFromRecyclerView(this);
        }
        if (!compatibleWithPrevious || removeAndRecycleViews) {
            removeAndRecycleViews();
        }
        this.mAdapterHelper.reset();
        Adapter oldAdapter = this.mAdapter;
        this.mAdapter = adapter2;
        if (adapter2 != null) {
            adapter2.registerAdapterDataObserver(this.mObserver);
            adapter2.onAttachedToRecyclerView(this);
        }
        if (this.mLayout != null) {
            this.mLayout.onAdapterChanged(oldAdapter, this.mAdapter);
        }
        this.mRecycler.onAdapterChanged(oldAdapter, this.mAdapter, compatibleWithPrevious);
        this.mState.mStructureChanged = true;
    }

    @Nullable
    public Adapter getAdapter() {
        return this.mAdapter;
    }

    public void setRecyclerListener(@Nullable RecyclerListener listener) {
        RecyclerListener recyclerListener = listener;
        this.mRecyclerListener = recyclerListener;
    }

    public int getBaseline() {
        if (this.mLayout != null) {
            return this.mLayout.getBaseline();
        }
        return super.getBaseline();
    }

    public void addOnChildAttachStateChangeListener(@NonNull OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        List<OnChildAttachStateChangeListener> list;
        OnChildAttachStateChangeListener listener = onChildAttachStateChangeListener;
        if (this.mOnChildAttachStateListeners == null) {
            new ArrayList();
            this.mOnChildAttachStateListeners = list;
        }
        boolean add = this.mOnChildAttachStateListeners.add(listener);
    }

    public void removeOnChildAttachStateChangeListener(@NonNull OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        OnChildAttachStateChangeListener listener = onChildAttachStateChangeListener;
        if (this.mOnChildAttachStateListeners != null) {
            boolean remove = this.mOnChildAttachStateListeners.remove(listener);
        }
    }

    public void clearOnChildAttachStateChangeListeners() {
        if (this.mOnChildAttachStateListeners != null) {
            this.mOnChildAttachStateListeners.clear();
        }
    }

    public void setLayoutManager(@Nullable LayoutManager layoutManager) {
        Throwable th;
        StringBuilder sb;
        LayoutManager layout = layoutManager;
        if (layout != this.mLayout) {
            stopScroll();
            if (this.mLayout != null) {
                if (this.mItemAnimator != null) {
                    this.mItemAnimator.endAnimations();
                }
                this.mLayout.removeAndRecycleAllViews(this.mRecycler);
                this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
                this.mRecycler.clear();
                if (this.mIsAttached) {
                    this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
                }
                this.mLayout.setRecyclerView((RecyclerView) null);
                this.mLayout = null;
            } else {
                this.mRecycler.clear();
            }
            this.mChildHelper.removeAllViewsUnfiltered();
            this.mLayout = layout;
            if (layout != null) {
                if (layout.mRecyclerView != null) {
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("LayoutManager ").append(layout).append(" is already attached to a RecyclerView:").append(layout.mRecyclerView.exceptionLabel()).toString());
                    throw th2;
                }
                this.mLayout.setRecyclerView(this);
                if (this.mIsAttached) {
                    this.mLayout.dispatchAttachedToWindow(this);
                }
            }
            this.mRecycler.updateViewCacheSize();
            requestLayout();
        }
    }

    public void setOnFlingListener(@Nullable OnFlingListener onFlingListener) {
        OnFlingListener onFlingListener2 = onFlingListener;
        this.mOnFlingListener = onFlingListener2;
    }

    @Nullable
    public OnFlingListener getOnFlingListener() {
        return this.mOnFlingListener;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        new SavedState(super.onSaveInstanceState());
        SavedState state = savedState;
        if (this.mPendingSavedState != null) {
            state.copyFrom(this.mPendingSavedState);
        } else if (this.mLayout != null) {
            state.mLayoutState = this.mLayout.onSaveInstanceState();
        } else {
            state.mLayoutState = null;
        }
        return state;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable state = parcelable;
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        this.mPendingSavedState = (SavedState) state;
        super.onRestoreInstanceState(this.mPendingSavedState.getSuperState());
        if (this.mLayout != null && this.mPendingSavedState.mLayoutState != null) {
            this.mLayout.onRestoreInstanceState(this.mPendingSavedState.mLayoutState);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    private void addAnimatingView(ViewHolder viewHolder) {
        ViewHolder viewHolder2 = viewHolder;
        View view = viewHolder2.itemView;
        boolean alreadyParented = view.getParent() == this;
        this.mRecycler.unscrapView(getChildViewHolder(view));
        if (viewHolder2.isTmpDetached()) {
            this.mChildHelper.attachViewToParent(view, -1, view.getLayoutParams(), true);
        } else if (!alreadyParented) {
            this.mChildHelper.addView(view, true);
        } else {
            this.mChildHelper.hide(view);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean removeAnimatingView(View view) {
        View view2 = view;
        startInterceptRequestLayout();
        boolean removed = this.mChildHelper.removeViewIfHidden(view2);
        if (removed) {
            ViewHolder viewHolder = getChildViewHolderInt(view2);
            this.mRecycler.unscrapView(viewHolder);
            this.mRecycler.recycleViewHolderInternal(viewHolder);
        }
        stopInterceptRequestLayout(!removed);
        return removed;
    }

    @Nullable
    public LayoutManager getLayoutManager() {
        return this.mLayout;
    }

    @NonNull
    public RecycledViewPool getRecycledViewPool() {
        return this.mRecycler.getRecycledViewPool();
    }

    public void setRecycledViewPool(@Nullable RecycledViewPool pool) {
        this.mRecycler.setRecycledViewPool(pool);
    }

    public void setViewCacheExtension(@Nullable ViewCacheExtension extension) {
        this.mRecycler.setViewCacheExtension(extension);
    }

    public void setItemViewCacheSize(int size) {
        this.mRecycler.setViewCacheSize(size);
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    /* access modifiers changed from: package-private */
    public void setScrollState(int i) {
        int state = i;
        if (state != this.mScrollState) {
            this.mScrollState = state;
            if (state != 2) {
                stopScrollersInternal();
            }
            dispatchOnScrollStateChanged(state);
        }
    }

    public void addItemDecoration(@NonNull ItemDecoration itemDecoration, int i) {
        ItemDecoration decor = itemDecoration;
        int index = i;
        if (this.mLayout != null) {
            this.mLayout.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(false);
        }
        if (index < 0) {
            boolean add = this.mItemDecorations.add(decor);
        } else {
            this.mItemDecorations.add(index, decor);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void addItemDecoration(@NonNull ItemDecoration decor) {
        addItemDecoration(decor, -1);
    }

    @NonNull
    public ItemDecoration getItemDecorationAt(int i) {
        Throwable th;
        StringBuilder sb;
        int index = i;
        int size = getItemDecorationCount();
        if (index >= 0 && index < size) {
            return this.mItemDecorations.get(index);
        }
        Throwable th2 = th;
        new StringBuilder();
        new IndexOutOfBoundsException(sb.append(index).append(" is an invalid index for size ").append(size).toString());
        throw th2;
    }

    public int getItemDecorationCount() {
        return this.mItemDecorations.size();
    }

    public void removeItemDecorationAt(int i) {
        Throwable th;
        StringBuilder sb;
        int index = i;
        int size = getItemDecorationCount();
        if (index < 0 || index >= size) {
            Throwable th2 = th;
            new StringBuilder();
            new IndexOutOfBoundsException(sb.append(index).append(" is an invalid index for size ").append(size).toString());
            throw th2;
        }
        removeItemDecoration(getItemDecorationAt(index));
    }

    public void removeItemDecoration(@NonNull ItemDecoration itemDecoration) {
        ItemDecoration decor = itemDecoration;
        if (this.mLayout != null) {
            this.mLayout.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        boolean remove = this.mItemDecorations.remove(decor);
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void setChildDrawingOrderCallback(@Nullable ChildDrawingOrderCallback childDrawingOrderCallback) {
        ChildDrawingOrderCallback childDrawingOrderCallback2 = childDrawingOrderCallback;
        if (childDrawingOrderCallback2 != this.mChildDrawingOrderCallback) {
            this.mChildDrawingOrderCallback = childDrawingOrderCallback2;
            setChildrenDrawingOrderEnabled(this.mChildDrawingOrderCallback != null);
        }
    }

    @Deprecated
    public void setOnScrollListener(@Nullable OnScrollListener listener) {
        OnScrollListener onScrollListener = listener;
        this.mScrollListener = onScrollListener;
    }

    public void addOnScrollListener(@NonNull OnScrollListener onScrollListener) {
        List<OnScrollListener> list;
        OnScrollListener listener = onScrollListener;
        if (this.mScrollListeners == null) {
            new ArrayList();
            this.mScrollListeners = list;
        }
        boolean add = this.mScrollListeners.add(listener);
    }

    public void removeOnScrollListener(@NonNull OnScrollListener onScrollListener) {
        OnScrollListener listener = onScrollListener;
        if (this.mScrollListeners != null) {
            boolean remove = this.mScrollListeners.remove(listener);
        }
    }

    public void clearOnScrollListeners() {
        if (this.mScrollListeners != null) {
            this.mScrollListeners.clear();
        }
    }

    public void scrollToPosition(int i) {
        int position = i;
        if (!this.mLayoutFrozen) {
            stopScroll();
            if (this.mLayout == null) {
                int e = Log.e(TAG, "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
                return;
            }
            this.mLayout.scrollToPosition(position);
            boolean awakenScrollBars = awakenScrollBars();
        }
    }

    /* access modifiers changed from: package-private */
    public void jumpToPositionForSmoothScroller(int i) {
        int position = i;
        if (this.mLayout != null) {
            this.mLayout.scrollToPosition(position);
            boolean awakenScrollBars = awakenScrollBars();
        }
    }

    public void smoothScrollToPosition(int i) {
        int position = i;
        if (!this.mLayoutFrozen) {
            if (this.mLayout == null) {
                int e = Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            } else {
                this.mLayout.smoothScrollToPosition(this, this.mState, position);
            }
        }
    }

    public void scrollTo(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        int w = Log.w(TAG, "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollBy(int i, int i2) {
        int x = i;
        int y = i2;
        if (this.mLayout == null) {
            int e = Log.e(TAG, "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutFrozen) {
            boolean canScrollHorizontal = this.mLayout.canScrollHorizontally();
            boolean canScrollVertical = this.mLayout.canScrollVertically();
            if (canScrollHorizontal || canScrollVertical) {
                boolean scrollByInternal = scrollByInternal(canScrollHorizontal ? x : 0, canScrollVertical ? y : 0, (MotionEvent) null);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void scrollStep(int i, int i2, @Nullable int[] iArr) {
        int dx = i;
        int dy = i2;
        int[] consumed = iArr;
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        TraceCompat.beginSection(TRACE_SCROLL_TAG);
        fillRemainingScrollValues(this.mState);
        int consumedX = 0;
        int consumedY = 0;
        if (dx != 0) {
            consumedX = this.mLayout.scrollHorizontallyBy(dx, this.mRecycler, this.mState);
        }
        if (dy != 0) {
            consumedY = this.mLayout.scrollVerticallyBy(dy, this.mRecycler, this.mState);
        }
        TraceCompat.endSection();
        repositionShadowingViews();
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        if (consumed != null) {
            consumed[0] = consumedX;
            consumed[1] = consumedY;
        }
    }

    /* access modifiers changed from: package-private */
    public void consumePendingUpdateOperations() {
        if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout) {
            TraceCompat.beginSection(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
            dispatchLayout();
            TraceCompat.endSection();
        } else if (this.mAdapterHelper.hasPendingUpdates()) {
            if (this.mAdapterHelper.hasAnyUpdateTypes(4) && !this.mAdapterHelper.hasAnyUpdateTypes(11)) {
                TraceCompat.beginSection(TRACE_HANDLE_ADAPTER_UPDATES_TAG);
                startInterceptRequestLayout();
                onEnterLayoutOrScroll();
                this.mAdapterHelper.preProcess();
                if (!this.mLayoutWasDefered) {
                    if (hasUpdatedView()) {
                        dispatchLayout();
                    } else {
                        this.mAdapterHelper.consumePostponedUpdates();
                    }
                }
                stopInterceptRequestLayout(true);
                onExitLayoutOrScroll();
                TraceCompat.endSection();
            } else if (this.mAdapterHelper.hasPendingUpdates()) {
                TraceCompat.beginSection(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
                dispatchLayout();
                TraceCompat.endSection();
            }
        }
    }

    private boolean hasUpdatedView() {
        int childCount = this.mChildHelper.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
            if (holder != null && !holder.shouldIgnore() && holder.isUpdated()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean scrollByInternal(int i, int i2, MotionEvent motionEvent) {
        int x = i;
        int y = i2;
        MotionEvent ev = motionEvent;
        int unconsumedX = 0;
        int unconsumedY = 0;
        int consumedX = 0;
        int consumedY = 0;
        consumePendingUpdateOperations();
        if (this.mAdapter != null) {
            scrollStep(x, y, this.mScrollStepConsumed);
            consumedX = this.mScrollStepConsumed[0];
            consumedY = this.mScrollStepConsumed[1];
            unconsumedX = x - consumedX;
            unconsumedY = y - consumedY;
        }
        if (!this.mItemDecorations.isEmpty()) {
            invalidate();
        }
        if (dispatchNestedScroll(consumedX, consumedY, unconsumedX, unconsumedY, this.mScrollOffset, 0)) {
            this.mLastTouchX -= this.mScrollOffset[0];
            this.mLastTouchY -= this.mScrollOffset[1];
            if (ev != null) {
                ev.offsetLocation((float) this.mScrollOffset[0], (float) this.mScrollOffset[1]);
            }
            int[] iArr = this.mNestedOffsets;
            iArr[0] = iArr[0] + this.mScrollOffset[0];
            int[] iArr2 = this.mNestedOffsets;
            iArr2[1] = iArr2[1] + this.mScrollOffset[1];
        } else if (getOverScrollMode() != 2) {
            if (ev != null && !MotionEventCompat.isFromSource(ev, 8194)) {
                pullGlows(ev.getX(), (float) unconsumedX, ev.getY(), (float) unconsumedY);
            }
            considerReleasingGlowsOnScroll(x, y);
        }
        if (!(consumedX == 0 && consumedY == 0)) {
            dispatchOnScrolled(consumedX, consumedY);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (consumedX == 0 && consumedY == 0) ? false : true;
    }

    public int computeHorizontalScrollOffset() {
        if (this.mLayout == null) {
            return 0;
        }
        return this.mLayout.canScrollHorizontally() ? this.mLayout.computeHorizontalScrollOffset(this.mState) : 0;
    }

    public int computeHorizontalScrollExtent() {
        if (this.mLayout == null) {
            return 0;
        }
        return this.mLayout.canScrollHorizontally() ? this.mLayout.computeHorizontalScrollExtent(this.mState) : 0;
    }

    public int computeHorizontalScrollRange() {
        if (this.mLayout == null) {
            return 0;
        }
        return this.mLayout.canScrollHorizontally() ? this.mLayout.computeHorizontalScrollRange(this.mState) : 0;
    }

    public int computeVerticalScrollOffset() {
        if (this.mLayout == null) {
            return 0;
        }
        return this.mLayout.canScrollVertically() ? this.mLayout.computeVerticalScrollOffset(this.mState) : 0;
    }

    public int computeVerticalScrollExtent() {
        if (this.mLayout == null) {
            return 0;
        }
        return this.mLayout.canScrollVertically() ? this.mLayout.computeVerticalScrollExtent(this.mState) : 0;
    }

    public int computeVerticalScrollRange() {
        if (this.mLayout == null) {
            return 0;
        }
        return this.mLayout.canScrollVertically() ? this.mLayout.computeVerticalScrollRange(this.mState) : 0;
    }

    /* access modifiers changed from: package-private */
    public void startInterceptRequestLayout() {
        this.mInterceptRequestLayoutDepth++;
        if (this.mInterceptRequestLayoutDepth == 1 && !this.mLayoutFrozen) {
            this.mLayoutWasDefered = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void stopInterceptRequestLayout(boolean z) {
        boolean performLayoutChildren = z;
        if (this.mInterceptRequestLayoutDepth < 1) {
            this.mInterceptRequestLayoutDepth = 1;
        }
        if (!performLayoutChildren && !this.mLayoutFrozen) {
            this.mLayoutWasDefered = false;
        }
        if (this.mInterceptRequestLayoutDepth == 1) {
            if (performLayoutChildren && this.mLayoutWasDefered && !this.mLayoutFrozen && this.mLayout != null && this.mAdapter != null) {
                dispatchLayout();
            }
            if (!this.mLayoutFrozen) {
                this.mLayoutWasDefered = false;
            }
        }
        this.mInterceptRequestLayoutDepth--;
    }

    public void setLayoutFrozen(boolean z) {
        boolean frozen = z;
        if (frozen != this.mLayoutFrozen) {
            assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
            if (!frozen) {
                this.mLayoutFrozen = false;
                if (!(!this.mLayoutWasDefered || this.mLayout == null || this.mAdapter == null)) {
                    requestLayout();
                }
                this.mLayoutWasDefered = false;
                return;
            }
            long now = SystemClock.uptimeMillis();
            boolean onTouchEvent = onTouchEvent(MotionEvent.obtain(now, now, 3, 0.0f, 0.0f, 0));
            this.mLayoutFrozen = true;
            this.mIgnoreMotionEventTillDown = true;
            stopScroll();
        }
    }

    public boolean isLayoutFrozen() {
        return this.mLayoutFrozen;
    }

    public void smoothScrollBy(@C0015Px int dx, @C0015Px int dy) {
        smoothScrollBy(dx, dy, (Interpolator) null);
    }

    public void smoothScrollBy(@C0015Px int i, @C0015Px int i2, @Nullable Interpolator interpolator) {
        int dx = i;
        int dy = i2;
        Interpolator interpolator2 = interpolator;
        if (this.mLayout == null) {
            int e = Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutFrozen) {
            if (!this.mLayout.canScrollHorizontally()) {
                dx = 0;
            }
            if (!this.mLayout.canScrollVertically()) {
                dy = 0;
            }
            if (dx != 0 || dy != 0) {
                this.mViewFlinger.smoothScrollBy(dx, dy, interpolator2);
            }
        }
    }

    public boolean fling(int i, int i2) {
        int velocityX = i;
        int velocityY = i2;
        if (this.mLayout == null) {
            int e = Log.e(TAG, "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        } else if (this.mLayoutFrozen) {
            return false;
        } else {
            boolean canScrollHorizontal = this.mLayout.canScrollHorizontally();
            boolean canScrollVertical = this.mLayout.canScrollVertically();
            if (!canScrollHorizontal || Math.abs(velocityX) < this.mMinFlingVelocity) {
                velocityX = 0;
            }
            if (!canScrollVertical || Math.abs(velocityY) < this.mMinFlingVelocity) {
                velocityY = 0;
            }
            if (velocityX == 0 && velocityY == 0) {
                return false;
            }
            if (!dispatchNestedPreFling((float) velocityX, (float) velocityY)) {
                boolean canScroll = canScrollHorizontal || canScrollVertical;
                boolean dispatchNestedFling = dispatchNestedFling((float) velocityX, (float) velocityY, canScroll);
                if (this.mOnFlingListener != null && this.mOnFlingListener.onFling(velocityX, velocityY)) {
                    return true;
                }
                if (canScroll) {
                    int nestedScrollAxis = 0;
                    if (canScrollHorizontal) {
                        nestedScrollAxis = 0 | 1;
                    }
                    if (canScrollVertical) {
                        nestedScrollAxis |= 2;
                    }
                    boolean startNestedScroll = startNestedScroll(nestedScrollAxis, 1);
                    this.mViewFlinger.fling(Math.max(-this.mMaxFlingVelocity, Math.min(velocityX, this.mMaxFlingVelocity)), Math.max(-this.mMaxFlingVelocity, Math.min(velocityY, this.mMaxFlingVelocity)));
                    return true;
                }
            }
            return false;
        }
    }

    public void stopScroll() {
        setScrollState(0);
        stopScrollersInternal();
    }

    private void stopScrollersInternal() {
        this.mViewFlinger.stop();
        if (this.mLayout != null) {
            this.mLayout.stopSmoothScroller();
        }
    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }

    private void pullGlows(float f, float f2, float f3, float f4) {
        float x = f;
        float overscrollX = f2;
        float y = f3;
        float overscrollY = f4;
        boolean invalidate = false;
        if (overscrollX < 0.0f) {
            ensureLeftGlow();
            EdgeEffectCompat.onPull(this.mLeftGlow, (-overscrollX) / ((float) getWidth()), 1.0f - (y / ((float) getHeight())));
            invalidate = true;
        } else if (overscrollX > 0.0f) {
            ensureRightGlow();
            EdgeEffectCompat.onPull(this.mRightGlow, overscrollX / ((float) getWidth()), y / ((float) getHeight()));
            invalidate = true;
        }
        if (overscrollY < 0.0f) {
            ensureTopGlow();
            EdgeEffectCompat.onPull(this.mTopGlow, (-overscrollY) / ((float) getHeight()), x / ((float) getWidth()));
            invalidate = true;
        } else if (overscrollY > 0.0f) {
            ensureBottomGlow();
            EdgeEffectCompat.onPull(this.mBottomGlow, overscrollY / ((float) getHeight()), 1.0f - (x / ((float) getWidth())));
            invalidate = true;
        }
        if (invalidate || overscrollX != 0.0f || overscrollY != 0.0f) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void releaseGlows() {
        boolean needsInvalidate = false;
        if (this.mLeftGlow != null) {
            this.mLeftGlow.onRelease();
            needsInvalidate = this.mLeftGlow.isFinished();
        }
        if (this.mTopGlow != null) {
            this.mTopGlow.onRelease();
            needsInvalidate |= this.mTopGlow.isFinished();
        }
        if (this.mRightGlow != null) {
            this.mRightGlow.onRelease();
            needsInvalidate |= this.mRightGlow.isFinished();
        }
        if (this.mBottomGlow != null) {
            this.mBottomGlow.onRelease();
            needsInvalidate |= this.mBottomGlow.isFinished();
        }
        if (needsInvalidate) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void considerReleasingGlowsOnScroll(int i, int i2) {
        int dx = i;
        int dy = i2;
        boolean needsInvalidate = false;
        if (this.mLeftGlow != null && !this.mLeftGlow.isFinished() && dx > 0) {
            this.mLeftGlow.onRelease();
            needsInvalidate = this.mLeftGlow.isFinished();
        }
        if (this.mRightGlow != null && !this.mRightGlow.isFinished() && dx < 0) {
            this.mRightGlow.onRelease();
            needsInvalidate |= this.mRightGlow.isFinished();
        }
        if (this.mTopGlow != null && !this.mTopGlow.isFinished() && dy > 0) {
            this.mTopGlow.onRelease();
            needsInvalidate |= this.mTopGlow.isFinished();
        }
        if (this.mBottomGlow != null && !this.mBottomGlow.isFinished() && dy < 0) {
            this.mBottomGlow.onRelease();
            needsInvalidate |= this.mBottomGlow.isFinished();
        }
        if (needsInvalidate) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void absorbGlows(int i, int i2) {
        int velocityX = i;
        int velocityY = i2;
        if (velocityX < 0) {
            ensureLeftGlow();
            this.mLeftGlow.onAbsorb(-velocityX);
        } else if (velocityX > 0) {
            ensureRightGlow();
            this.mRightGlow.onAbsorb(velocityX);
        }
        if (velocityY < 0) {
            ensureTopGlow();
            this.mTopGlow.onAbsorb(-velocityY);
        } else if (velocityY > 0) {
            ensureBottomGlow();
            this.mBottomGlow.onAbsorb(velocityY);
        }
        if (velocityX != 0 || velocityY != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureLeftGlow() {
        if (this.mLeftGlow == null) {
            this.mLeftGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 0);
            if (this.mClipToPadding) {
                this.mLeftGlow.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.mLeftGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureRightGlow() {
        if (this.mRightGlow == null) {
            this.mRightGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 2);
            if (this.mClipToPadding) {
                this.mRightGlow.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.mRightGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureTopGlow() {
        if (this.mTopGlow == null) {
            this.mTopGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 1);
            if (this.mClipToPadding) {
                this.mTopGlow.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.mTopGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureBottomGlow() {
        if (this.mBottomGlow == null) {
            this.mBottomGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 3);
            if (this.mClipToPadding) {
                this.mBottomGlow.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.mBottomGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    public void setEdgeEffectFactory(@NonNull EdgeEffectFactory edgeEffectFactory) {
        EdgeEffectFactory edgeEffectFactory2 = edgeEffectFactory;
        Object checkNotNull = Preconditions.checkNotNull(edgeEffectFactory2);
        this.mEdgeEffectFactory = edgeEffectFactory2;
        invalidateGlows();
    }

    @NonNull
    public EdgeEffectFactory getEdgeEffectFactory() {
        return this.mEdgeEffectFactory;
    }

    public View focusSearch(View view, int i) {
        View result;
        View focusSearch;
        View focused = view;
        int direction = i;
        View result2 = this.mLayout.onInterceptFocusSearch(focused, direction);
        if (result2 != null) {
            return result2;
        }
        boolean canRunFocusFailure = this.mAdapter != null && this.mLayout != null && !isComputingLayout() && !this.mLayoutFrozen;
        FocusFinder ff = FocusFinder.getInstance();
        if (!canRunFocusFailure || !(direction == 2 || direction == 1)) {
            result = ff.findNextFocus(this, focused, direction);
            if (result == null && canRunFocusFailure) {
                consumePendingUpdateOperations();
                if (findContainingItemView(focused) == null) {
                    return null;
                }
                startInterceptRequestLayout();
                result = this.mLayout.onFocusSearchFailed(focused, direction, this.mRecycler, this.mState);
                stopInterceptRequestLayout(false);
            }
        } else {
            boolean needsFocusFailureLayout = false;
            if (this.mLayout.canScrollVertically()) {
                int absDir = direction == 2 ? 130 : 33;
                needsFocusFailureLayout = ff.findNextFocus(this, focused, absDir) == null;
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    direction = absDir;
                }
            }
            if (!needsFocusFailureLayout && this.mLayout.canScrollHorizontally()) {
                int absDir2 = (direction == 2) ^ (this.mLayout.getLayoutDirection() == 1) ? 66 : 17;
                needsFocusFailureLayout = ff.findNextFocus(this, focused, absDir2) == null;
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    direction = absDir2;
                }
            }
            if (needsFocusFailureLayout) {
                consumePendingUpdateOperations();
                if (findContainingItemView(focused) == null) {
                    return null;
                }
                startInterceptRequestLayout();
                View onFocusSearchFailed = this.mLayout.onFocusSearchFailed(focused, direction, this.mRecycler, this.mState);
                stopInterceptRequestLayout(false);
            }
            result = ff.findNextFocus(this, focused, direction);
        }
        if (result == null || result.hasFocusable()) {
            if (isPreferredNextFocus(focused, result, direction)) {
                focusSearch = result;
            } else {
                focusSearch = super.focusSearch(focused, direction);
            }
            return focusSearch;
        } else if (getFocusedChild() == null) {
            return super.focusSearch(focused, direction);
        } else {
            requestChildOnScreen(result, (View) null);
            return focused;
        }
    }

    private boolean isPreferredNextFocus(View view, View view2, int i) {
        Throwable th;
        StringBuilder sb;
        View focused = view;
        View next = view2;
        int direction = i;
        if (next == null || next == this) {
            return false;
        }
        if (findContainingItemView(next) == null) {
            return false;
        }
        if (focused == null) {
            return true;
        }
        if (findContainingItemView(focused) == null) {
            return true;
        }
        this.mTempRect.set(0, 0, focused.getWidth(), focused.getHeight());
        this.mTempRect2.set(0, 0, next.getWidth(), next.getHeight());
        offsetDescendantRectToMyCoords(focused, this.mTempRect);
        offsetDescendantRectToMyCoords(next, this.mTempRect2);
        int rtl = this.mLayout.getLayoutDirection() == 1 ? -1 : 1;
        int rightness = 0;
        if ((this.mTempRect.left < this.mTempRect2.left || this.mTempRect.right <= this.mTempRect2.left) && this.mTempRect.right < this.mTempRect2.right) {
            rightness = 1;
        } else if ((this.mTempRect.right > this.mTempRect2.right || this.mTempRect.left >= this.mTempRect2.right) && this.mTempRect.left > this.mTempRect2.left) {
            rightness = -1;
        }
        int downness = 0;
        if ((this.mTempRect.top < this.mTempRect2.top || this.mTempRect.bottom <= this.mTempRect2.top) && this.mTempRect.bottom < this.mTempRect2.bottom) {
            downness = 1;
        } else if ((this.mTempRect.bottom > this.mTempRect2.bottom || this.mTempRect.top >= this.mTempRect2.bottom) && this.mTempRect.top > this.mTempRect2.top) {
            downness = -1;
        }
        switch (direction) {
            case 1:
                return downness < 0 || (downness == 0 && rightness * rtl <= 0);
            case 2:
                return downness > 0 || (downness == 0 && rightness * rtl >= 0);
            case 17:
                return rightness < 0;
            case 33:
                return downness < 0;
            case 66:
                return rightness > 0;
            case 130:
                return downness > 0;
            default:
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Invalid direction: ").append(direction).append(exceptionLabel()).toString());
                throw th2;
        }
    }

    public void requestChildFocus(View view, View view2) {
        View child = view;
        View focused = view2;
        if (!this.mLayout.onRequestChildFocus(this, this.mState, child, focused) && focused != null) {
            requestChildOnScreen(child, focused);
        }
        super.requestChildFocus(child, focused);
    }

    private void requestChildOnScreen(@NonNull View view, @Nullable View view2) {
        View child = view;
        View focused = view2;
        View rectView = focused != null ? focused : child;
        this.mTempRect.set(0, 0, rectView.getWidth(), rectView.getHeight());
        ViewGroup.LayoutParams focusedLayoutParams = rectView.getLayoutParams();
        if (focusedLayoutParams instanceof LayoutParams) {
            LayoutParams lp = (LayoutParams) focusedLayoutParams;
            if (!lp.mInsetsDirty) {
                Rect insets = lp.mDecorInsets;
                this.mTempRect.left -= insets.left;
                this.mTempRect.right += insets.right;
                this.mTempRect.top -= insets.top;
                this.mTempRect.bottom += insets.bottom;
            }
        }
        if (focused != null) {
            offsetDescendantRectToMyCoords(focused, this.mTempRect);
            offsetRectIntoDescendantCoords(child, this.mTempRect);
        }
        boolean requestChildRectangleOnScreen = this.mLayout.requestChildRectangleOnScreen(this, child, this.mTempRect, !this.mFirstLayoutComplete, focused == null);
    }

    public boolean requestChildRectangleOnScreen(View child, Rect rect, boolean immediate) {
        return this.mLayout.requestChildRectangleOnScreen(this, child, rect, immediate);
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        ArrayList<View> views = arrayList;
        int direction = i;
        int focusableMode = i2;
        if (this.mLayout == null || !this.mLayout.onAddFocusables(this, views, direction, focusableMode)) {
            super.addFocusables(views, direction, focusableMode);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        int direction = i;
        Rect previouslyFocusedRect = rect;
        if (isComputingLayout()) {
            return false;
        }
        return super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        GapWorker gapWorker;
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        this.mIsAttached = true;
        this.mFirstLayoutComplete = this.mFirstLayoutComplete && !isLayoutRequested();
        if (this.mLayout != null) {
            this.mLayout.dispatchAttachedToWindow(this);
        }
        this.mPostedAnimatorRunner = false;
        if (ALLOW_THREAD_GAP_WORK) {
            this.mGapWorker = GapWorker.sGapWorker.get();
            if (this.mGapWorker == null) {
                new GapWorker();
                this.mGapWorker = gapWorker;
                Display display = ViewCompat.getDisplay(this);
                float refreshRate = 60.0f;
                if (!isInEditMode() && display != null) {
                    float displayRefreshRate = display.getRefreshRate();
                    if (displayRefreshRate >= 30.0f) {
                        refreshRate = displayRefreshRate;
                    }
                }
                this.mGapWorker.mFrameIntervalNs = (long) (1.0E9f / refreshRate);
                GapWorker.sGapWorker.set(this.mGapWorker);
            }
            this.mGapWorker.add(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mItemAnimator != null) {
            this.mItemAnimator.endAnimations();
        }
        stopScroll();
        this.mIsAttached = false;
        if (this.mLayout != null) {
            this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
        }
        this.mPendingAccessibilityImportanceChange.clear();
        boolean removeCallbacks = removeCallbacks(this.mItemAnimatorRunner);
        this.mViewInfoStore.onDetach();
        if (ALLOW_THREAD_GAP_WORK && this.mGapWorker != null) {
            this.mGapWorker.remove(this);
            this.mGapWorker = null;
        }
    }

    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    /* access modifiers changed from: package-private */
    public void assertInLayoutOrScroll(String str) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        String message = str;
        if (isComputingLayout()) {
            return;
        }
        if (message == null) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalStateException(sb2.append("Cannot call this method unless RecyclerView is computing a layout or scrolling").append(exceptionLabel()).toString());
            throw th3;
        }
        Throwable th4 = th;
        new StringBuilder();
        new IllegalStateException(sb.append(message).append(exceptionLabel()).toString());
        throw th4;
    }

    /* access modifiers changed from: package-private */
    public void assertNotInLayoutOrScroll(String str) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Throwable th3;
        StringBuilder sb2;
        String message = str;
        if (isComputingLayout()) {
            if (message == null) {
                Throwable th4 = th3;
                new StringBuilder();
                new IllegalStateException(sb2.append("Cannot call this method while RecyclerView is computing a layout or scrolling").append(exceptionLabel()).toString());
                throw th4;
            }
            Throwable th5 = th2;
            new IllegalStateException(message);
            throw th5;
        } else if (this.mDispatchScrollCounter > 0) {
            new StringBuilder();
            new IllegalStateException(sb.append("").append(exceptionLabel()).toString());
            int w = Log.w(TAG, "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", th);
        }
    }

    public void addOnItemTouchListener(@NonNull OnItemTouchListener listener) {
        boolean add = this.mOnItemTouchListeners.add(listener);
    }

    public void removeOnItemTouchListener(@NonNull OnItemTouchListener onItemTouchListener) {
        OnItemTouchListener listener = onItemTouchListener;
        boolean remove = this.mOnItemTouchListeners.remove(listener);
        if (this.mActiveOnItemTouchListener == listener) {
            this.mActiveOnItemTouchListener = null;
        }
    }

    private boolean dispatchOnItemTouchIntercept(MotionEvent motionEvent) {
        MotionEvent e = motionEvent;
        int action = e.getAction();
        if (action == 3 || action == 0) {
            this.mActiveOnItemTouchListener = null;
        }
        int listenerCount = this.mOnItemTouchListeners.size();
        int i = 0;
        while (i < listenerCount) {
            OnItemTouchListener listener = this.mOnItemTouchListeners.get(i);
            if (!listener.onInterceptTouchEvent(this, e) || action == 3) {
                i++;
            } else {
                this.mActiveOnItemTouchListener = listener;
                return true;
            }
        }
        return false;
    }

    private boolean dispatchOnItemTouch(MotionEvent motionEvent) {
        MotionEvent e = motionEvent;
        int action = e.getAction();
        if (this.mActiveOnItemTouchListener != null) {
            if (action == 0) {
                this.mActiveOnItemTouchListener = null;
            } else {
                this.mActiveOnItemTouchListener.onTouchEvent(this, e);
                if (action == 3 || action == 1) {
                    this.mActiveOnItemTouchListener = null;
                }
                return true;
            }
        }
        if (action != 0) {
            int listenerCount = this.mOnItemTouchListeners.size();
            for (int i = 0; i < listenerCount; i++) {
                OnItemTouchListener listener = this.mOnItemTouchListeners.get(i);
                if (listener.onInterceptTouchEvent(this, e)) {
                    this.mActiveOnItemTouchListener = listener;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        StringBuilder sb;
        boolean z;
        MotionEvent e = motionEvent;
        if (this.mLayoutFrozen) {
            return false;
        }
        if (dispatchOnItemTouchIntercept(e)) {
            cancelTouch();
            return true;
        } else if (this.mLayout == null) {
            return false;
        } else {
            boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
            boolean canScrollVertically = this.mLayout.canScrollVertically();
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(e);
            int action = e.getActionMasked();
            int actionIndex = e.getActionIndex();
            switch (action) {
                case 0:
                    if (this.mIgnoreMotionEventTillDown) {
                        this.mIgnoreMotionEventTillDown = false;
                    }
                    this.mScrollPointerId = e.getPointerId(0);
                    int x = (int) (e.getX() + 0.5f);
                    this.mLastTouchX = x;
                    this.mInitialTouchX = x;
                    int y = (int) (e.getY() + 0.5f);
                    this.mLastTouchY = y;
                    this.mInitialTouchY = y;
                    if (this.mScrollState == 2) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        setScrollState(1);
                    }
                    int[] iArr = this.mNestedOffsets;
                    this.mNestedOffsets[1] = 0;
                    iArr[0] = 0;
                    int nestedScrollAxis = 0;
                    if (canScrollHorizontally) {
                        nestedScrollAxis = 0 | 1;
                    }
                    if (canScrollVertically) {
                        nestedScrollAxis |= 2;
                    }
                    boolean startNestedScroll = startNestedScroll(nestedScrollAxis, 0);
                    break;
                case 1:
                    this.mVelocityTracker.clear();
                    stopNestedScroll(0);
                    break;
                case 2:
                    int index = e.findPointerIndex(this.mScrollPointerId);
                    if (index >= 0) {
                        int x2 = (int) (e.getX(index) + 0.5f);
                        int y2 = (int) (e.getY(index) + 0.5f);
                        if (this.mScrollState != 1) {
                            int dx = x2 - this.mInitialTouchX;
                            int dy = y2 - this.mInitialTouchY;
                            boolean startScroll = false;
                            if (canScrollHorizontally) {
                                if (Math.abs(dx) > this.mTouchSlop) {
                                    this.mLastTouchX = x2;
                                    startScroll = true;
                                }
                            }
                            if (canScrollVertically) {
                                if (Math.abs(dy) > this.mTouchSlop) {
                                    this.mLastTouchY = y2;
                                    startScroll = true;
                                }
                            }
                            if (startScroll) {
                                setScrollState(1);
                                break;
                            }
                        }
                    } else {
                        new StringBuilder();
                        int e2 = Log.e(TAG, sb.append("Error processing scroll; pointer index for id ").append(this.mScrollPointerId).append(" not found. Did any MotionEvents get skipped?").toString());
                        return false;
                    }
                    break;
                case 3:
                    cancelTouch();
                    break;
                case 5:
                    this.mScrollPointerId = e.getPointerId(actionIndex);
                    int x3 = (int) (e.getX(actionIndex) + 0.5f);
                    this.mLastTouchX = x3;
                    this.mInitialTouchX = x3;
                    int y3 = (int) (e.getY(actionIndex) + 0.5f);
                    this.mLastTouchY = y3;
                    this.mInitialTouchY = y3;
                    break;
                case 6:
                    onPointerUp(e);
                    break;
            }
            if (this.mScrollState == 1) {
                z = true;
            } else {
                z = false;
            }
            return z;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        boolean disallowIntercept = z;
        int listenerCount = this.mOnItemTouchListeners.size();
        for (int i = 0; i < listenerCount; i++) {
            this.mOnItemTouchListeners.get(i).onRequestDisallowInterceptTouchEvent(disallowIntercept);
        }
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int dy;
        int dx;
        int i;
        StringBuilder sb;
        MotionEvent e = motionEvent;
        if (!this.mLayoutFrozen) {
            if (!this.mIgnoreMotionEventTillDown) {
                if (dispatchOnItemTouch(e)) {
                    cancelTouch();
                    return true;
                }
                if (this.mLayout == null) {
                    return false;
                }
                boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
                boolean canScrollVertically = this.mLayout.canScrollVertically();
                if (this.mVelocityTracker == null) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                }
                boolean eventAddedToVelocityTracker = false;
                MotionEvent vtev = MotionEvent.obtain(e);
                int action = e.getActionMasked();
                int actionIndex = e.getActionIndex();
                if (action == 0) {
                    int[] iArr = this.mNestedOffsets;
                    this.mNestedOffsets[1] = 0;
                    iArr[0] = 0;
                }
                vtev.offsetLocation((float) this.mNestedOffsets[0], (float) this.mNestedOffsets[1]);
                switch (action) {
                    case 0:
                        this.mScrollPointerId = e.getPointerId(0);
                        int x = (int) (e.getX() + 0.5f);
                        this.mLastTouchX = x;
                        this.mInitialTouchX = x;
                        int y = (int) (e.getY() + 0.5f);
                        this.mLastTouchY = y;
                        this.mInitialTouchY = y;
                        int nestedScrollAxis = 0;
                        if (canScrollHorizontally) {
                            nestedScrollAxis = 0 | 1;
                        }
                        if (canScrollVertically) {
                            nestedScrollAxis |= 2;
                        }
                        boolean startNestedScroll = startNestedScroll(nestedScrollAxis, 0);
                        break;
                    case 1:
                        this.mVelocityTracker.addMovement(vtev);
                        eventAddedToVelocityTracker = true;
                        this.mVelocityTracker.computeCurrentVelocity(1000, (float) this.mMaxFlingVelocity);
                        float xvel = canScrollHorizontally ? -this.mVelocityTracker.getXVelocity(this.mScrollPointerId) : 0.0f;
                        float yvel = canScrollVertically ? -this.mVelocityTracker.getYVelocity(this.mScrollPointerId) : 0.0f;
                        if ((xvel == 0.0f && yvel == 0.0f) || !fling((int) xvel, (int) yvel)) {
                            setScrollState(0);
                        }
                        resetTouch();
                        break;
                    case 2:
                        int index = e.findPointerIndex(this.mScrollPointerId);
                        if (index >= 0) {
                            int x2 = (int) (e.getX(index) + 0.5f);
                            int y2 = (int) (e.getY(index) + 0.5f);
                            int dx2 = this.mLastTouchX - x2;
                            int dy2 = this.mLastTouchY - y2;
                            if (dispatchNestedPreScroll(dx2, dy2, this.mScrollConsumed, this.mScrollOffset, 0)) {
                                dx2 -= this.mScrollConsumed[0];
                                dy2 -= this.mScrollConsumed[1];
                                vtev.offsetLocation((float) this.mScrollOffset[0], (float) this.mScrollOffset[1]);
                                int[] iArr2 = this.mNestedOffsets;
                                iArr2[0] = iArr2[0] + this.mScrollOffset[0];
                                int[] iArr3 = this.mNestedOffsets;
                                iArr3[1] = iArr3[1] + this.mScrollOffset[1];
                            }
                            if (this.mScrollState != 1) {
                                boolean startScroll = false;
                                if (canScrollHorizontally && Math.abs(dx) > this.mTouchSlop) {
                                    if (dx > 0) {
                                        dx -= this.mTouchSlop;
                                    } else {
                                        dx += this.mTouchSlop;
                                    }
                                    startScroll = true;
                                }
                                if (canScrollVertically && Math.abs(dy) > this.mTouchSlop) {
                                    if (dy > 0) {
                                        dy -= this.mTouchSlop;
                                    } else {
                                        dy += this.mTouchSlop;
                                    }
                                    startScroll = true;
                                }
                                if (startScroll) {
                                    setScrollState(1);
                                }
                            }
                            if (this.mScrollState == 1) {
                                this.mLastTouchX = x2 - this.mScrollOffset[0];
                                this.mLastTouchY = y2 - this.mScrollOffset[1];
                                int i2 = canScrollHorizontally ? dx : 0;
                                if (canScrollVertically) {
                                    i = dy;
                                } else {
                                    i = 0;
                                }
                                if (scrollByInternal(i2, i, vtev)) {
                                    getParent().requestDisallowInterceptTouchEvent(true);
                                }
                                if (!(this.mGapWorker == null || (dx == 0 && dy == 0))) {
                                    this.mGapWorker.postFromTraversal(this, dx, dy);
                                    break;
                                }
                            }
                        } else {
                            new StringBuilder();
                            int e2 = Log.e(TAG, sb.append("Error processing scroll; pointer index for id ").append(this.mScrollPointerId).append(" not found. Did any MotionEvents get skipped?").toString());
                            return false;
                        }
                        break;
                    case 3:
                        cancelTouch();
                        break;
                    case 5:
                        this.mScrollPointerId = e.getPointerId(actionIndex);
                        int x3 = (int) (e.getX(actionIndex) + 0.5f);
                        this.mLastTouchX = x3;
                        this.mInitialTouchX = x3;
                        int y3 = (int) (e.getY(actionIndex) + 0.5f);
                        this.mLastTouchY = y3;
                        this.mInitialTouchY = y3;
                        break;
                    case 6:
                        onPointerUp(e);
                        break;
                }
                if (!eventAddedToVelocityTracker) {
                    this.mVelocityTracker.addMovement(vtev);
                }
                vtev.recycle();
                return true;
            }
        }
        return false;
    }

    private void resetTouch() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.clear();
        }
        stopNestedScroll(0);
        releaseGlows();
    }

    private void cancelTouch() {
        resetTouch();
        setScrollState(0);
    }

    private void onPointerUp(MotionEvent motionEvent) {
        MotionEvent e = motionEvent;
        int actionIndex = e.getActionIndex();
        if (e.getPointerId(actionIndex) == this.mScrollPointerId) {
            int newIndex = actionIndex == 0 ? 1 : 0;
            this.mScrollPointerId = e.getPointerId(newIndex);
            int x = (int) (e.getX(newIndex) + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (e.getY(newIndex) + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float vScroll;
        float hScroll;
        MotionEvent event = motionEvent;
        if (this.mLayout == null) {
            return false;
        }
        if (this.mLayoutFrozen) {
            return false;
        }
        if (event.getAction() == 8) {
            if ((event.getSource() & 2) != 0) {
                if (this.mLayout.canScrollVertically()) {
                    vScroll = -event.getAxisValue(9);
                } else {
                    vScroll = 0.0f;
                }
                if (this.mLayout.canScrollHorizontally()) {
                    hScroll = event.getAxisValue(10);
                } else {
                    hScroll = 0.0f;
                }
            } else if ((event.getSource() & 4194304) != 0) {
                float axisScroll = event.getAxisValue(26);
                if (this.mLayout.canScrollVertically()) {
                    vScroll = -axisScroll;
                    hScroll = 0.0f;
                } else if (this.mLayout.canScrollHorizontally()) {
                    vScroll = 0.0f;
                    hScroll = axisScroll;
                } else {
                    vScroll = 0.0f;
                    hScroll = 0.0f;
                }
            } else {
                vScroll = 0.0f;
                hScroll = 0.0f;
            }
            if (!(vScroll == 0.0f && hScroll == 0.0f)) {
                boolean scrollByInternal = scrollByInternal((int) (hScroll * this.mScaledHorizontalScrollFactor), (int) (vScroll * this.mScaledVerticalScrollFactor), event);
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthSpec = i;
        int heightSpec = i2;
        if (this.mLayout == null) {
            defaultOnMeasure(widthSpec, heightSpec);
        } else if (this.mLayout.isAutoMeasureEnabled()) {
            int widthMode = View.MeasureSpec.getMode(widthSpec);
            int heightMode = View.MeasureSpec.getMode(heightSpec);
            this.mLayout.onMeasure(this.mRecycler, this.mState, widthSpec, heightSpec);
            if (!(widthMode == 1073741824 && heightMode == 1073741824) && this.mAdapter != null) {
                if (this.mState.mLayoutStep == 1) {
                    dispatchLayoutStep1();
                }
                this.mLayout.setMeasureSpecs(widthSpec, heightSpec);
                this.mState.mIsMeasuring = true;
                dispatchLayoutStep2();
                this.mLayout.setMeasuredDimensionFromChildren(widthSpec, heightSpec);
                if (this.mLayout.shouldMeasureTwice()) {
                    this.mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), Declaration.MODULE_REFERENCE), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), Declaration.MODULE_REFERENCE));
                    this.mState.mIsMeasuring = true;
                    dispatchLayoutStep2();
                    this.mLayout.setMeasuredDimensionFromChildren(widthSpec, heightSpec);
                }
            }
        } else if (this.mHasFixedSize) {
            this.mLayout.onMeasure(this.mRecycler, this.mState, widthSpec, heightSpec);
        } else {
            if (this.mAdapterUpdateDuringMeasure) {
                startInterceptRequestLayout();
                onEnterLayoutOrScroll();
                processAdapterUpdatesAndSetAnimationFlags();
                onExitLayoutOrScroll();
                if (this.mState.mRunPredictiveAnimations) {
                    this.mState.mInPreLayout = true;
                } else {
                    this.mAdapterHelper.consumeUpdatesInOnePass();
                    this.mState.mInPreLayout = false;
                }
                this.mAdapterUpdateDuringMeasure = false;
                stopInterceptRequestLayout(false);
            } else if (this.mState.mRunPredictiveAnimations) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            if (this.mAdapter != null) {
                this.mState.mItemCount = this.mAdapter.getItemCount();
            } else {
                this.mState.mItemCount = 0;
            }
            startInterceptRequestLayout();
            this.mLayout.onMeasure(this.mRecycler, this.mState, widthSpec, heightSpec);
            stopInterceptRequestLayout(false);
            this.mState.mInPreLayout = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void defaultOnMeasure(int widthSpec, int heightSpec) {
        setMeasuredDimension(LayoutManager.chooseSize(widthSpec, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth(this)), LayoutManager.chooseSize(heightSpec, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight(this)));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        int w = i;
        int h = i2;
        int oldw = i3;
        int oldh = i4;
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            invalidateGlows();
        }
    }

    public void setItemAnimator(@Nullable ItemAnimator itemAnimator) {
        ItemAnimator animator = itemAnimator;
        if (this.mItemAnimator != null) {
            this.mItemAnimator.endAnimations();
            this.mItemAnimator.setListener((ItemAnimator.ItemAnimatorListener) null);
        }
        this.mItemAnimator = animator;
        if (this.mItemAnimator != null) {
            this.mItemAnimator.setListener(this.mItemAnimatorListener);
        }
    }

    /* access modifiers changed from: package-private */
    public void onEnterLayoutOrScroll() {
        this.mLayoutOrScrollCounter++;
    }

    /* access modifiers changed from: package-private */
    public void onExitLayoutOrScroll() {
        onExitLayoutOrScroll(true);
    }

    /* access modifiers changed from: package-private */
    public void onExitLayoutOrScroll(boolean z) {
        boolean enableChangeEvents = z;
        this.mLayoutOrScrollCounter--;
        if (this.mLayoutOrScrollCounter < 1) {
            this.mLayoutOrScrollCounter = 0;
            if (enableChangeEvents) {
                dispatchContentChangedIfNecessary();
                dispatchPendingImportantForAccessibilityChanges();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isAccessibilityEnabled() {
        return this.mAccessibilityManager != null && this.mAccessibilityManager.isEnabled();
    }

    private void dispatchContentChangedIfNecessary() {
        int flags = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if (flags != 0 && isAccessibilityEnabled()) {
            AccessibilityEvent event = AccessibilityEvent.obtain();
            event.setEventType(2048);
            AccessibilityEventCompat.setContentChangeTypes(event, flags);
            sendAccessibilityEventUnchecked(event);
        }
    }

    public boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldDeferAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        AccessibilityEvent event = accessibilityEvent;
        if (!isComputingLayout()) {
            return false;
        }
        int type = 0;
        if (event != null) {
            type = AccessibilityEventCompat.getContentChangeTypes(event);
        }
        if (type == 0) {
            type = 0;
        }
        this.mEatenAccessibilityChangeFlags |= type;
        return true;
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        AccessibilityEvent event = accessibilityEvent;
        if (!shouldDeferAccessibilityEvent(event)) {
            super.sendAccessibilityEventUnchecked(event);
        }
    }

    @Nullable
    public ItemAnimator getItemAnimator() {
        return this.mItemAnimator;
    }

    /* access modifiers changed from: package-private */
    public void postAnimationRunner() {
        if (!this.mPostedAnimatorRunner && this.mIsAttached) {
            ViewCompat.postOnAnimation(this, this.mItemAnimatorRunner);
            this.mPostedAnimatorRunner = true;
        }
    }

    private boolean predictiveItemAnimationsEnabled() {
        return this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations();
    }

    private void processAdapterUpdatesAndSetAnimationFlags() {
        if (this.mDataSetHasChangedAfterLayout) {
            this.mAdapterHelper.reset();
            if (this.mDispatchItemsChangedEvent) {
                this.mLayout.onItemsChanged(this);
            }
        }
        if (predictiveItemAnimationsEnabled()) {
            this.mAdapterHelper.preProcess();
        } else {
            this.mAdapterHelper.consumeUpdatesInOnePass();
        }
        boolean animationTypeSupported = this.mItemsAddedOrRemoved || this.mItemsChanged;
        this.mState.mRunSimpleAnimations = this.mFirstLayoutComplete && this.mItemAnimator != null && (this.mDataSetHasChangedAfterLayout || animationTypeSupported || this.mLayout.mRequestedSimpleAnimations) && (!this.mDataSetHasChangedAfterLayout || this.mAdapter.hasStableIds());
        this.mState.mRunPredictiveAnimations = this.mState.mRunSimpleAnimations && animationTypeSupported && !this.mDataSetHasChangedAfterLayout && predictiveItemAnimationsEnabled();
    }

    /* access modifiers changed from: package-private */
    public void dispatchLayout() {
        if (this.mAdapter == null) {
            int e = Log.e(TAG, "No adapter attached; skipping layout");
        } else if (this.mLayout == null) {
            int e2 = Log.e(TAG, "No layout manager attached; skipping layout");
        } else {
            this.mState.mIsMeasuring = false;
            if (this.mState.mLayoutStep == 1) {
                dispatchLayoutStep1();
                this.mLayout.setExactMeasureSpecsFrom(this);
                dispatchLayoutStep2();
            } else if (!this.mAdapterHelper.hasUpdates() && this.mLayout.getWidth() == getWidth() && this.mLayout.getHeight() == getHeight()) {
                this.mLayout.setExactMeasureSpecsFrom(this);
            } else {
                this.mLayout.setExactMeasureSpecsFrom(this);
                dispatchLayoutStep2();
            }
            dispatchLayoutStep3();
        }
    }

    private void saveFocusInfo() {
        int adapterPosition;
        View child = null;
        if (this.mPreserveFocusAfterLayout && hasFocus() && this.mAdapter != null) {
            child = getFocusedChild();
        }
        ViewHolder focusedVh = child == null ? null : findContainingViewHolder(child);
        if (focusedVh == null) {
            resetFocusInfo();
            return;
        }
        this.mState.mFocusedItemId = this.mAdapter.hasStableIds() ? focusedVh.getItemId() : -1;
        State state = this.mState;
        if (this.mDataSetHasChangedAfterLayout) {
            adapterPosition = -1;
        } else if (focusedVh.isRemoved()) {
            adapterPosition = focusedVh.mOldPosition;
        } else {
            adapterPosition = focusedVh.getAdapterPosition();
        }
        state.mFocusedItemPosition = adapterPosition;
        this.mState.mFocusedSubChildId = getDeepestFocusedViewWithId(focusedVh.itemView);
    }

    private void resetFocusInfo() {
        this.mState.mFocusedItemId = -1;
        this.mState.mFocusedItemPosition = -1;
        this.mState.mFocusedSubChildId = -1;
    }

    @Nullable
    private View findNextViewToFocus() {
        ViewHolder nextFocus;
        int startFocusSearchIndex = this.mState.mFocusedItemPosition != -1 ? this.mState.mFocusedItemPosition : 0;
        int itemCount = this.mState.getItemCount();
        int i = startFocusSearchIndex;
        while (i < itemCount && (nextFocus = findViewHolderForAdapterPosition(i)) != null) {
            if (nextFocus.itemView.hasFocusable()) {
                return nextFocus.itemView;
            }
            i++;
        }
        for (int i2 = Math.min(itemCount, startFocusSearchIndex) - 1; i2 >= 0; i2--) {
            ViewHolder nextFocus2 = findViewHolderForAdapterPosition(i2);
            if (nextFocus2 == null) {
                return null;
            }
            if (nextFocus2.itemView.hasFocusable()) {
                return nextFocus2.itemView;
            }
        }
        return null;
    }

    private void recoverFocusFromState() {
        View child;
        if (this.mPreserveFocusAfterLayout && this.mAdapter != null && hasFocus() && getDescendantFocusability() != 393216) {
            if (getDescendantFocusability() != 131072 || !isFocused()) {
                if (!isFocused()) {
                    View focusedChild = getFocusedChild();
                    if (!IGNORE_DETACHED_FOCUSED_CHILD || (focusedChild.getParent() != null && focusedChild.hasFocus())) {
                        if (!this.mChildHelper.isHidden(focusedChild)) {
                            return;
                        }
                    } else if (this.mChildHelper.getChildCount() == 0) {
                        boolean requestFocus = requestFocus();
                        return;
                    }
                }
                ViewHolder focusTarget = null;
                if (this.mState.mFocusedItemId != -1 && this.mAdapter.hasStableIds()) {
                    focusTarget = findViewHolderForItemId(this.mState.mFocusedItemId);
                }
                View viewToFocus = null;
                if (focusTarget != null && !this.mChildHelper.isHidden(focusTarget.itemView) && focusTarget.itemView.hasFocusable()) {
                    viewToFocus = focusTarget.itemView;
                } else if (this.mChildHelper.getChildCount() > 0) {
                    viewToFocus = findNextViewToFocus();
                }
                if (viewToFocus != null) {
                    if (!(((long) this.mState.mFocusedSubChildId) == -1 || (child = viewToFocus.findViewById(this.mState.mFocusedSubChildId)) == null || !child.isFocusable())) {
                        viewToFocus = child;
                    }
                    boolean requestFocus2 = viewToFocus.requestFocus();
                }
            }
        }
    }

    private int getDeepestFocusedViewWithId(View view) {
        View view2 = view;
        int lastKnownId = view2.getId();
        while (!view2.isFocused() && (view2 instanceof ViewGroup) && view2.hasFocus()) {
            view2 = ((ViewGroup) view2).getFocusedChild();
            if (view2.getId() != -1) {
                lastKnownId = view2.getId();
            }
        }
        return lastKnownId;
    }

    /* access modifiers changed from: package-private */
    public final void fillRemainingScrollValues(State state) {
        State state2 = state;
        if (getScrollState() == 2) {
            OverScroller scroller = this.mViewFlinger.mScroller;
            state2.mRemainingScrollHorizontal = scroller.getFinalX() - scroller.getCurrX();
            state2.mRemainingScrollVertical = scroller.getFinalY() - scroller.getCurrY();
            return;
        }
        state2.mRemainingScrollHorizontal = 0;
        state2.mRemainingScrollVertical = 0;
    }

    private void dispatchLayoutStep1() {
        this.mState.assertLayoutStep(1);
        fillRemainingScrollValues(this.mState);
        this.mState.mIsMeasuring = false;
        startInterceptRequestLayout();
        this.mViewInfoStore.clear();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        saveFocusInfo();
        this.mState.mTrackOldChangeHolders = this.mState.mRunSimpleAnimations && this.mItemsChanged;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        this.mState.mInPreLayout = this.mState.mRunPredictiveAnimations;
        this.mState.mItemCount = this.mAdapter.getItemCount();
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mState.mRunSimpleAnimations) {
            int count = this.mChildHelper.getChildCount();
            for (int i = 0; i < count; i++) {
                ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
                if (!holder.shouldIgnore() && (!holder.isInvalid() || this.mAdapter.hasStableIds())) {
                    this.mViewInfoStore.addToPreLayout(holder, this.mItemAnimator.recordPreLayoutInformation(this.mState, holder, ItemAnimator.buildAdapterChangeFlagsForAnimations(holder), holder.getUnmodifiedPayloads()));
                    if (this.mState.mTrackOldChangeHolders && holder.isUpdated() && !holder.isRemoved() && !holder.shouldIgnore() && !holder.isInvalid()) {
                        this.mViewInfoStore.addToOldChangeHolders(getChangedHolderKey(holder), holder);
                    }
                }
            }
        }
        if (this.mState.mRunPredictiveAnimations) {
            saveOldPositions();
            boolean didStructureChange = this.mState.mStructureChanged;
            this.mState.mStructureChanged = false;
            this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
            this.mState.mStructureChanged = didStructureChange;
            for (int i2 = 0; i2 < this.mChildHelper.getChildCount(); i2++) {
                ViewHolder viewHolder = getChildViewHolderInt(this.mChildHelper.getChildAt(i2));
                if (!viewHolder.shouldIgnore() && !this.mViewInfoStore.isInPreLayout(viewHolder)) {
                    int flags = ItemAnimator.buildAdapterChangeFlagsForAnimations(viewHolder);
                    boolean wasHidden = viewHolder.hasAnyOfTheFlags(8192);
                    if (!wasHidden) {
                        flags |= 4096;
                    }
                    ItemAnimator.ItemHolderInfo animationInfo = this.mItemAnimator.recordPreLayoutInformation(this.mState, viewHolder, flags, viewHolder.getUnmodifiedPayloads());
                    if (wasHidden) {
                        recordAnimationInfoIfBouncedHiddenView(viewHolder, animationInfo);
                    } else {
                        this.mViewInfoStore.addToAppearedInPreLayoutHolders(viewHolder, animationInfo);
                    }
                }
            }
            clearOldPositions();
        } else {
            clearOldPositions();
        }
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        this.mState.mLayoutStep = 2;
    }

    private void dispatchLayoutStep2() {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        this.mState.assertLayoutStep(6);
        this.mAdapterHelper.consumeUpdatesInOnePass();
        this.mState.mItemCount = this.mAdapter.getItemCount();
        this.mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        this.mState.mInPreLayout = false;
        this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
        this.mState.mStructureChanged = false;
        this.mPendingSavedState = null;
        this.mState.mRunSimpleAnimations = this.mState.mRunSimpleAnimations && this.mItemAnimator != null;
        this.mState.mLayoutStep = 4;
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
    }

    private void dispatchLayoutStep3() {
        this.mState.assertLayoutStep(4);
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        this.mState.mLayoutStep = 1;
        if (this.mState.mRunSimpleAnimations) {
            for (int i = this.mChildHelper.getChildCount() - 1; i >= 0; i--) {
                ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
                if (!holder.shouldIgnore()) {
                    long key = getChangedHolderKey(holder);
                    ItemAnimator.ItemHolderInfo animationInfo = this.mItemAnimator.recordPostLayoutInformation(this.mState, holder);
                    ViewHolder oldChangeViewHolder = this.mViewInfoStore.getFromOldChangeHolders(key);
                    if (oldChangeViewHolder == null || oldChangeViewHolder.shouldIgnore()) {
                        this.mViewInfoStore.addToPostLayout(holder, animationInfo);
                    } else {
                        boolean oldDisappearing = this.mViewInfoStore.isDisappearing(oldChangeViewHolder);
                        boolean newDisappearing = this.mViewInfoStore.isDisappearing(holder);
                        if (!oldDisappearing || oldChangeViewHolder != holder) {
                            ItemAnimator.ItemHolderInfo preInfo = this.mViewInfoStore.popFromPreLayout(oldChangeViewHolder);
                            this.mViewInfoStore.addToPostLayout(holder, animationInfo);
                            ItemAnimator.ItemHolderInfo postInfo = this.mViewInfoStore.popFromPostLayout(holder);
                            if (preInfo == null) {
                                handleMissingPreInfoForChangeError(key, holder, oldChangeViewHolder);
                            } else {
                                animateChange(oldChangeViewHolder, holder, preInfo, postInfo, oldDisappearing, newDisappearing);
                            }
                        } else {
                            this.mViewInfoStore.addToPostLayout(holder, animationInfo);
                        }
                    }
                }
            }
            this.mViewInfoStore.process(this.mViewInfoProcessCallback);
        }
        this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        this.mState.mPreviousLayoutItemCount = this.mState.mItemCount;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mState.mRunSimpleAnimations = false;
        this.mState.mRunPredictiveAnimations = false;
        this.mLayout.mRequestedSimpleAnimations = false;
        if (this.mRecycler.mChangedScrap != null) {
            this.mRecycler.mChangedScrap.clear();
        }
        if (this.mLayout.mPrefetchMaxObservedInInitialPrefetch) {
            this.mLayout.mPrefetchMaxCountObserved = 0;
            this.mLayout.mPrefetchMaxObservedInInitialPrefetch = false;
            this.mRecycler.updateViewCacheSize();
        }
        this.mLayout.onLayoutCompleted(this.mState);
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        this.mViewInfoStore.clear();
        if (didChildRangeChange(this.mMinMaxLayoutPositions[0], this.mMinMaxLayoutPositions[1])) {
            dispatchOnScrolled(0, 0);
        }
        recoverFocusFromState();
        resetFocusInfo();
    }

    private void handleMissingPreInfoForChangeError(long j, ViewHolder viewHolder, ViewHolder viewHolder2) {
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        Throwable th2;
        StringBuilder sb3;
        long key = j;
        ViewHolder holder = viewHolder;
        ViewHolder oldChangeViewHolder = viewHolder2;
        int childCount = this.mChildHelper.getChildCount();
        int i = 0;
        while (i < childCount) {
            ViewHolder other = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
            if (other == holder || getChangedHolderKey(other) != key) {
                i++;
            } else if (this.mAdapter == null || !this.mAdapter.hasStableIds()) {
                Throwable th3 = th;
                new StringBuilder();
                new IllegalStateException(sb2.append("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:").append(other).append(" \n View Holder 2:").append(holder).append(exceptionLabel()).toString());
                throw th3;
            } else {
                Throwable th4 = th2;
                new StringBuilder();
                new IllegalStateException(sb3.append("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:").append(other).append(" \n View Holder 2:").append(holder).append(exceptionLabel()).toString());
                throw th4;
            }
        }
        new StringBuilder();
        int e = Log.e(TAG, sb.append("Problem while matching changed view holders with the newones. The pre-layout information for the change holder ").append(oldChangeViewHolder).append(" cannot be found but it is necessary for ").append(holder).append(exceptionLabel()).toString());
    }

    /* access modifiers changed from: package-private */
    public void recordAnimationInfoIfBouncedHiddenView(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo) {
        ViewHolder viewHolder2 = viewHolder;
        ItemAnimator.ItemHolderInfo animationInfo = itemHolderInfo;
        viewHolder2.setFlags(0, 8192);
        if (this.mState.mTrackOldChangeHolders && viewHolder2.isUpdated() && !viewHolder2.isRemoved() && !viewHolder2.shouldIgnore()) {
            this.mViewInfoStore.addToOldChangeHolders(getChangedHolderKey(viewHolder2), viewHolder2);
        }
        this.mViewInfoStore.addToPreLayout(viewHolder2, animationInfo);
    }

    private void findMinMaxChildLayoutPositions(int[] iArr) {
        int[] into = iArr;
        int count = this.mChildHelper.getChildCount();
        if (count == 0) {
            into[0] = -1;
            into[1] = -1;
            return;
        }
        int minPositionPreLayout = Integer.MAX_VALUE;
        int maxPositionPreLayout = Integer.MIN_VALUE;
        for (int i = 0; i < count; i++) {
            ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
            if (!holder.shouldIgnore()) {
                int pos = holder.getLayoutPosition();
                if (pos < minPositionPreLayout) {
                    minPositionPreLayout = pos;
                }
                if (pos > maxPositionPreLayout) {
                    maxPositionPreLayout = pos;
                }
            }
        }
        into[0] = minPositionPreLayout;
        into[1] = maxPositionPreLayout;
    }

    private boolean didChildRangeChange(int minPositionPreLayout, int i) {
        int maxPositionPreLayout = i;
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        return (this.mMinMaxLayoutPositions[0] == minPositionPreLayout && this.mMinMaxLayoutPositions[1] == maxPositionPreLayout) ? false : true;
    }

    /* access modifiers changed from: protected */
    public void removeDetachedView(View view, boolean z) {
        Throwable th;
        StringBuilder sb;
        View child = view;
        boolean animate = z;
        ViewHolder vh = getChildViewHolderInt(child);
        if (vh != null) {
            if (vh.isTmpDetached()) {
                vh.clearTmpDetachFlag();
            } else if (!vh.shouldIgnore()) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Called removeDetachedView with a view which is not flagged as tmp detached.").append(vh).append(exceptionLabel()).toString());
                throw th2;
            }
        }
        child.clearAnimation();
        dispatchChildDetached(child);
        super.removeDetachedView(child, animate);
    }

    /* access modifiers changed from: package-private */
    public long getChangedHolderKey(ViewHolder viewHolder) {
        ViewHolder holder = viewHolder;
        return this.mAdapter.hasStableIds() ? holder.getItemId() : (long) holder.mPosition;
    }

    /* access modifiers changed from: package-private */
    public void animateAppearance(@NonNull ViewHolder viewHolder, @Nullable ItemAnimator.ItemHolderInfo preLayoutInfo, @NonNull ItemAnimator.ItemHolderInfo postLayoutInfo) {
        ViewHolder itemHolder = viewHolder;
        itemHolder.setIsRecyclable(false);
        if (this.mItemAnimator.animateAppearance(itemHolder, preLayoutInfo, postLayoutInfo)) {
            postAnimationRunner();
        }
    }

    /* access modifiers changed from: package-private */
    public void animateDisappearance(@NonNull ViewHolder viewHolder, @NonNull ItemAnimator.ItemHolderInfo preLayoutInfo, @Nullable ItemAnimator.ItemHolderInfo postLayoutInfo) {
        ViewHolder holder = viewHolder;
        addAnimatingView(holder);
        holder.setIsRecyclable(false);
        if (this.mItemAnimator.animateDisappearance(holder, preLayoutInfo, postLayoutInfo)) {
            postAnimationRunner();
        }
    }

    private void animateChange(@NonNull ViewHolder viewHolder, @NonNull ViewHolder viewHolder2, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull ItemAnimator.ItemHolderInfo itemHolderInfo2, boolean oldHolderDisappearing, boolean z) {
        ViewHolder oldHolder = viewHolder;
        ViewHolder newHolder = viewHolder2;
        ItemAnimator.ItemHolderInfo preInfo = itemHolderInfo;
        ItemAnimator.ItemHolderInfo postInfo = itemHolderInfo2;
        boolean newHolderDisappearing = z;
        oldHolder.setIsRecyclable(false);
        if (oldHolderDisappearing) {
            addAnimatingView(oldHolder);
        }
        if (oldHolder != newHolder) {
            if (newHolderDisappearing) {
                addAnimatingView(newHolder);
            }
            oldHolder.mShadowedHolder = newHolder;
            addAnimatingView(oldHolder);
            this.mRecycler.unscrapView(oldHolder);
            newHolder.setIsRecyclable(false);
            newHolder.mShadowingHolder = oldHolder;
        }
        if (this.mItemAnimator.animateChange(oldHolder, newHolder, preInfo, postInfo)) {
            postAnimationRunner();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2 = z;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        TraceCompat.beginSection(TRACE_ON_LAYOUT_TAG);
        dispatchLayout();
        TraceCompat.endSection();
        this.mFirstLayoutComplete = true;
    }

    public void requestLayout() {
        if (this.mInterceptRequestLayoutDepth != 0 || this.mLayoutFrozen) {
            this.mLayoutWasDefered = true;
        } else {
            super.requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void markItemDecorInsetsDirty() {
        int childCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < childCount; i++) {
            ((LayoutParams) this.mChildHelper.getUnfilteredChildAt(i).getLayoutParams()).mInsetsDirty = true;
        }
        this.mRecycler.markItemDecorInsetsDirty();
    }

    public void draw(Canvas canvas) {
        Canvas c = canvas;
        super.draw(c);
        int count = this.mItemDecorations.size();
        for (int i = 0; i < count; i++) {
            this.mItemDecorations.get(i).onDrawOver(c, this, this.mState);
        }
        boolean needsInvalidate = false;
        if (this.mLeftGlow != null && !this.mLeftGlow.isFinished()) {
            int restore = c.save();
            int padding = this.mClipToPadding ? getPaddingBottom() : 0;
            c.rotate(270.0f);
            c.translate((float) ((-getHeight()) + padding), 0.0f);
            needsInvalidate = this.mLeftGlow != null && this.mLeftGlow.draw(c);
            c.restoreToCount(restore);
        }
        if (this.mTopGlow != null && !this.mTopGlow.isFinished()) {
            int restore2 = c.save();
            if (this.mClipToPadding) {
                c.translate((float) getPaddingLeft(), (float) getPaddingTop());
            }
            needsInvalidate |= this.mTopGlow != null && this.mTopGlow.draw(c);
            c.restoreToCount(restore2);
        }
        if (this.mRightGlow != null && !this.mRightGlow.isFinished()) {
            int restore3 = c.save();
            int width = getWidth();
            int padding2 = this.mClipToPadding ? getPaddingTop() : 0;
            c.rotate(90.0f);
            c.translate((float) (-padding2), (float) (-width));
            needsInvalidate |= this.mRightGlow != null && this.mRightGlow.draw(c);
            c.restoreToCount(restore3);
        }
        if (this.mBottomGlow != null && !this.mBottomGlow.isFinished()) {
            int restore4 = c.save();
            c.rotate(180.0f);
            if (this.mClipToPadding) {
                c.translate((float) ((-getWidth()) + getPaddingRight()), (float) ((-getHeight()) + getPaddingBottom()));
            } else {
                c.translate((float) (-getWidth()), (float) (-getHeight()));
            }
            needsInvalidate |= this.mBottomGlow != null && this.mBottomGlow.draw(c);
            c.restoreToCount(restore4);
        }
        if (!needsInvalidate && this.mItemAnimator != null && this.mItemDecorations.size() > 0 && this.mItemAnimator.isRunning()) {
            needsInvalidate = true;
        }
        if (needsInvalidate) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void onDraw(Canvas canvas) {
        Canvas c = canvas;
        super.onDraw(c);
        int count = this.mItemDecorations.size();
        for (int i = 0; i < count; i++) {
            this.mItemDecorations.get(i).onDraw(c, this, this.mState);
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        ViewGroup.LayoutParams p = layoutParams;
        return (p instanceof LayoutParams) && this.mLayout.checkLayoutParams((LayoutParams) p);
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        Throwable th;
        StringBuilder sb;
        if (this.mLayout != null) {
            return this.mLayout.generateDefaultLayoutParams();
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("RecyclerView has no LayoutManager").append(exceptionLabel()).toString());
        throw th2;
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Throwable th;
        StringBuilder sb;
        AttributeSet attrs = attributeSet;
        if (this.mLayout != null) {
            return this.mLayout.generateLayoutParams(getContext(), attrs);
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("RecyclerView has no LayoutManager").append(exceptionLabel()).toString());
        throw th2;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        Throwable th;
        StringBuilder sb;
        ViewGroup.LayoutParams p = layoutParams;
        if (this.mLayout != null) {
            return this.mLayout.generateLayoutParams(p);
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("RecyclerView has no LayoutManager").append(exceptionLabel()).toString());
        throw th2;
    }

    public boolean isAnimating() {
        return this.mItemAnimator != null && this.mItemAnimator.isRunning();
    }

    /* access modifiers changed from: package-private */
    public void saveOldPositions() {
        int childCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!holder.shouldIgnore()) {
                holder.saveOldPosition();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void clearOldPositions() {
        int childCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!holder.shouldIgnore()) {
                holder.clearOldPosition();
            }
        }
        this.mRecycler.clearOldPositions();
    }

    /* access modifiers changed from: package-private */
    public void offsetPositionRecordsForMove(int i, int i2) {
        int start;
        int end;
        int inBetweenOffset;
        int from = i;
        int to = i2;
        int childCount = this.mChildHelper.getUnfilteredChildCount();
        if (from < to) {
            start = from;
            end = to;
            inBetweenOffset = -1;
        } else {
            start = to;
            end = from;
            inBetweenOffset = 1;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i3));
            if (holder != null && holder.mPosition >= start && holder.mPosition <= end) {
                if (holder.mPosition == from) {
                    holder.offsetPosition(to - from, false);
                } else {
                    holder.offsetPosition(inBetweenOffset, false);
                }
                this.mState.mStructureChanged = true;
            }
        }
        this.mRecycler.offsetPositionRecordsForMove(from, to);
        requestLayout();
    }

    /* access modifiers changed from: package-private */
    public void offsetPositionRecordsForInsert(int i, int i2) {
        int positionStart = i;
        int itemCount = i2;
        int childCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i3));
            if (holder != null && !holder.shouldIgnore() && holder.mPosition >= positionStart) {
                holder.offsetPosition(itemCount, false);
                this.mState.mStructureChanged = true;
            }
        }
        this.mRecycler.offsetPositionRecordsForInsert(positionStart, itemCount);
        requestLayout();
    }

    /* access modifiers changed from: package-private */
    public void offsetPositionRecordsForRemove(int i, int i2, boolean z) {
        int positionStart = i;
        int itemCount = i2;
        boolean applyToPreLayout = z;
        int positionEnd = positionStart + itemCount;
        int childCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i3));
            if (holder != null && !holder.shouldIgnore()) {
                if (holder.mPosition >= positionEnd) {
                    holder.offsetPosition(-itemCount, applyToPreLayout);
                    this.mState.mStructureChanged = true;
                } else if (holder.mPosition >= positionStart) {
                    holder.flagRemovedAndOffsetPosition(positionStart - 1, -itemCount, applyToPreLayout);
                    this.mState.mStructureChanged = true;
                }
            }
        }
        this.mRecycler.offsetPositionRecordsForRemove(positionStart, itemCount, applyToPreLayout);
        requestLayout();
    }

    /* access modifiers changed from: package-private */
    public void viewRangeUpdate(int i, int i2, Object obj) {
        int positionStart = i;
        int itemCount = i2;
        Object payload = obj;
        int childCount = this.mChildHelper.getUnfilteredChildCount();
        int positionEnd = positionStart + itemCount;
        for (int i3 = 0; i3 < childCount; i3++) {
            View child = this.mChildHelper.getUnfilteredChildAt(i3);
            ViewHolder holder = getChildViewHolderInt(child);
            if (holder != null && !holder.shouldIgnore() && holder.mPosition >= positionStart && holder.mPosition < positionEnd) {
                holder.addFlags(2);
                holder.addChangePayload(payload);
                ((LayoutParams) child.getLayoutParams()).mInsetsDirty = true;
            }
        }
        this.mRecycler.viewRangeUpdate(positionStart, itemCount);
    }

    /* access modifiers changed from: package-private */
    public boolean canReuseUpdatedViewHolder(ViewHolder viewHolder) {
        ViewHolder viewHolder2 = viewHolder;
        return this.mItemAnimator == null || this.mItemAnimator.canReuseUpdatedViewHolder(viewHolder2, viewHolder2.getUnmodifiedPayloads());
    }

    /* access modifiers changed from: package-private */
    public void processDataSetCompletelyChanged(boolean dispatchItemsChanged) {
        this.mDispatchItemsChangedEvent |= dispatchItemsChanged;
        this.mDataSetHasChangedAfterLayout = true;
        markKnownViewsInvalid();
    }

    /* access modifiers changed from: package-private */
    public void markKnownViewsInvalid() {
        int childCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (holder != null && !holder.shouldIgnore()) {
                holder.addFlags(6);
            }
        }
        markItemDecorInsetsDirty();
        this.mRecycler.markKnownViewsInvalid();
    }

    public void invalidateItemDecorations() {
        if (this.mItemDecorations.size() != 0) {
            if (this.mLayout != null) {
                this.mLayout.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
            }
            markItemDecorInsetsDirty();
            requestLayout();
        }
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
    }

    public void setPreserveFocusAfterLayout(boolean preserveFocusAfterLayout) {
        boolean z = preserveFocusAfterLayout;
        this.mPreserveFocusAfterLayout = z;
    }

    public ViewHolder getChildViewHolder(@NonNull View view) {
        Throwable th;
        StringBuilder sb;
        View child = view;
        ViewParent parent = child.getParent();
        if (parent == null || parent == this) {
            return getChildViewHolderInt(child);
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("View ").append(child).append(" is not a direct child of ").append(this).toString());
        throw th2;
    }

    @Nullable
    public View findContainingItemView(@NonNull View view) {
        ViewParent parent;
        View view2 = view;
        ViewParent parent2 = view2.getParent();
        while (true) {
            parent = parent2;
            if (parent != null && parent != this && (parent instanceof View)) {
                view2 = (View) parent;
                parent2 = view2.getParent();
            }
        }
        return parent == this ? view2 : null;
    }

    @Nullable
    public ViewHolder findContainingViewHolder(@NonNull View view) {
        View itemView = findContainingItemView(view);
        return itemView == null ? null : getChildViewHolder(itemView);
    }

    static ViewHolder getChildViewHolderInt(View view) {
        View child = view;
        if (child == null) {
            return null;
        }
        return ((LayoutParams) child.getLayoutParams()).mViewHolder;
    }

    @Deprecated
    public int getChildPosition(@NonNull View child) {
        return getChildAdapterPosition(child);
    }

    public int getChildAdapterPosition(@NonNull View child) {
        ViewHolder holder = getChildViewHolderInt(child);
        return holder != null ? holder.getAdapterPosition() : -1;
    }

    public int getChildLayoutPosition(@NonNull View child) {
        ViewHolder holder = getChildViewHolderInt(child);
        return holder != null ? holder.getLayoutPosition() : -1;
    }

    public long getChildItemId(@NonNull View view) {
        View child = view;
        if (this.mAdapter == null || !this.mAdapter.hasStableIds()) {
            return -1;
        }
        ViewHolder holder = getChildViewHolderInt(child);
        return holder != null ? holder.getItemId() : -1;
    }

    @Nullable
    @Deprecated
    public ViewHolder findViewHolderForPosition(int position) {
        return findViewHolderForPosition(position, false);
    }

    @Nullable
    public ViewHolder findViewHolderForLayoutPosition(int position) {
        return findViewHolderForPosition(position, false);
    }

    @Nullable
    public ViewHolder findViewHolderForAdapterPosition(int i) {
        int position = i;
        if (this.mDataSetHasChangedAfterLayout) {
            return null;
        }
        int childCount = this.mChildHelper.getUnfilteredChildCount();
        ViewHolder hidden = null;
        for (int i2 = 0; i2 < childCount; i2++) {
            ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i2));
            if (holder != null && !holder.isRemoved() && getAdapterPositionFor(holder) == position) {
                if (!this.mChildHelper.isHidden(holder.itemView)) {
                    return holder;
                }
                hidden = holder;
            }
        }
        return hidden;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public ViewHolder findViewHolderForPosition(int i, boolean z) {
        int position = i;
        boolean checkNewPosition = z;
        int childCount = this.mChildHelper.getUnfilteredChildCount();
        ViewHolder hidden = null;
        for (int i2 = 0; i2 < childCount; i2++) {
            ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i2));
            if (holder != null && !holder.isRemoved()) {
                if (checkNewPosition) {
                    if (holder.mPosition != position) {
                        continue;
                    }
                } else if (holder.getLayoutPosition() != position) {
                    continue;
                }
                if (!this.mChildHelper.isHidden(holder.itemView)) {
                    return holder;
                }
                hidden = holder;
            }
        }
        return hidden;
    }

    public ViewHolder findViewHolderForItemId(long j) {
        long id = j;
        if (this.mAdapter == null || !this.mAdapter.hasStableIds()) {
            return null;
        }
        int childCount = this.mChildHelper.getUnfilteredChildCount();
        ViewHolder hidden = null;
        for (int i = 0; i < childCount; i++) {
            ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (holder != null && !holder.isRemoved() && holder.getItemId() == id) {
                if (!this.mChildHelper.isHidden(holder.itemView)) {
                    return holder;
                }
                hidden = holder;
            }
        }
        return hidden;
    }

    @Nullable
    public View findChildViewUnder(float f, float f2) {
        float x = f;
        float y = f2;
        for (int i = this.mChildHelper.getChildCount() - 1; i >= 0; i--) {
            View child = this.mChildHelper.getChildAt(i);
            float translationX = child.getTranslationX();
            float translationY = child.getTranslationY();
            if (x >= ((float) child.getLeft()) + translationX && x <= ((float) child.getRight()) + translationX && y >= ((float) child.getTop()) + translationY && y <= ((float) child.getBottom()) + translationY) {
                return child;
            }
        }
        return null;
    }

    public boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return super.drawChild(canvas, child, drawingTime);
    }

    public void offsetChildrenVertical(@C0015Px int i) {
        int dy = i;
        int childCount = this.mChildHelper.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            this.mChildHelper.getChildAt(i2).offsetTopAndBottom(dy);
        }
    }

    public void onChildAttachedToWindow(@NonNull View child) {
    }

    public void onChildDetachedFromWindow(@NonNull View child) {
    }

    public void offsetChildrenHorizontal(@C0015Px int i) {
        int dx = i;
        int childCount = this.mChildHelper.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            this.mChildHelper.getChildAt(i2).offsetLeftAndRight(dx);
        }
    }

    public void getDecoratedBoundsWithMargins(@NonNull View view, @NonNull Rect outBounds) {
        getDecoratedBoundsWithMarginsInt(view, outBounds);
    }

    static void getDecoratedBoundsWithMarginsInt(View view, Rect outBounds) {
        View view2 = view;
        LayoutParams lp = (LayoutParams) view2.getLayoutParams();
        Rect insets = lp.mDecorInsets;
        outBounds.set((view2.getLeft() - insets.left) - lp.leftMargin, (view2.getTop() - insets.top) - lp.topMargin, view2.getRight() + insets.right + lp.rightMargin, view2.getBottom() + insets.bottom + lp.bottomMargin);
    }

    /* access modifiers changed from: package-private */
    public Rect getItemDecorInsetsForChild(View view) {
        View child = view;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (!lp.mInsetsDirty) {
            return lp.mDecorInsets;
        }
        if (this.mState.isPreLayout() && (lp.isItemChanged() || lp.isViewInvalid())) {
            return lp.mDecorInsets;
        }
        Rect insets = lp.mDecorInsets;
        insets.set(0, 0, 0, 0);
        int decorCount = this.mItemDecorations.size();
        for (int i = 0; i < decorCount; i++) {
            this.mTempRect.set(0, 0, 0, 0);
            this.mItemDecorations.get(i).getItemOffsets(this.mTempRect, child, this, this.mState);
            insets.left += this.mTempRect.left;
            insets.top += this.mTempRect.top;
            insets.right += this.mTempRect.right;
            insets.bottom += this.mTempRect.bottom;
        }
        lp.mInsetsDirty = false;
        return insets;
    }

    public void onScrolled(@C0015Px int dx, @C0015Px int dy) {
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnScrolled(int i, int i2) {
        int hresult = i;
        int vresult = i2;
        this.mDispatchScrollCounter++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        onScrolled(hresult, vresult);
        if (this.mScrollListener != null) {
            this.mScrollListener.onScrolled(this, hresult, vresult);
        }
        if (this.mScrollListeners != null) {
            for (int i3 = this.mScrollListeners.size() - 1; i3 >= 0; i3--) {
                this.mScrollListeners.get(i3).onScrolled(this, hresult, vresult);
            }
        }
        this.mDispatchScrollCounter--;
    }

    public void onScrollStateChanged(int state) {
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnScrollStateChanged(int i) {
        int state = i;
        if (this.mLayout != null) {
            this.mLayout.onScrollStateChanged(state);
        }
        onScrollStateChanged(state);
        if (this.mScrollListener != null) {
            this.mScrollListener.onScrollStateChanged(this, state);
        }
        if (this.mScrollListeners != null) {
            for (int i2 = this.mScrollListeners.size() - 1; i2 >= 0; i2--) {
                this.mScrollListeners.get(i2).onScrollStateChanged(this, state);
            }
        }
    }

    public boolean hasPendingAdapterUpdates() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.hasPendingUpdates();
    }

    /* renamed from: android.support.v7.widget.RecyclerView$ViewFlinger */
    class ViewFlinger implements Runnable {
        private boolean mEatRunOnAnimationRequest = false;
        Interpolator mInterpolator = RecyclerView.sQuinticInterpolator;
        private int mLastFlingX;
        private int mLastFlingY;
        private boolean mReSchedulePostAnimationCallback = false;
        OverScroller mScroller;
        final /* synthetic */ RecyclerView this$0;

        ViewFlinger(RecyclerView recyclerView) {
            OverScroller overScroller;
            RecyclerView this$02 = recyclerView;
            this.this$0 = this$02;
            new OverScroller(this$02.getContext(), RecyclerView.sQuinticInterpolator);
            this.mScroller = overScroller;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:95:0x031b, code lost:
            if (r2.this$0.hasNestedScrollingParent(1) == false) goto L_0x031d;
         */
        /* JADX WARNING: Removed duplicated region for block: B:123:0x03d6  */
        /* JADX WARNING: Removed duplicated region for block: B:76:0x02cb  */
        /* JADX WARNING: Removed duplicated region for block: B:89:0x02fd  */
        /* JADX WARNING: Removed duplicated region for block: B:92:0x0309  */
        /* JADX WARNING: Removed duplicated region for block: B:98:0x032e  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r24 = this;
                r2 = r24
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r0 = r17
                android.support.v7.widget.RecyclerView$LayoutManager r0 = r0.mLayout
                r17 = r0
                if (r17 != 0) goto L_0x0018
                r17 = r2
                r17.stop()
            L_0x0017:
                return
            L_0x0018:
                r17 = r2
                r17.disableRunOnAnimationRequests()
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r17.consumePendingUpdateOperations()
                r17 = r2
                r0 = r17
                android.widget.OverScroller r0 = r0.mScroller
                r17 = r0
                r3 = r17
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r0 = r17
                android.support.v7.widget.RecyclerView$LayoutManager r0 = r0.mLayout
                r17 = r0
                r0 = r17
                android.support.v7.widget.RecyclerView$SmoothScroller r0 = r0.mSmoothScroller
                r17 = r0
                r4 = r17
                r17 = r3
                boolean r17 = r17.computeScrollOffset()
                if (r17 == 0) goto L_0x034c
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r0 = r17
                int[] r0 = r0.mScrollConsumed
                r17 = r0
                r5 = r17
                r17 = r3
                int r17 = r17.getCurrX()
                r6 = r17
                r17 = r3
                int r17 = r17.getCurrY()
                r7 = r17
                r17 = r6
                r18 = r2
                r0 = r18
                int r0 = r0.mLastFlingX
                r18 = r0
                int r17 = r17 - r18
                r8 = r17
                r17 = r7
                r18 = r2
                r0 = r18
                int r0 = r0.mLastFlingY
                r18 = r0
                int r17 = r17 - r18
                r9 = r17
                r17 = 0
                r10 = r17
                r17 = 0
                r11 = r17
                r17 = r2
                r18 = r6
                r0 = r18
                r1 = r17
                r1.mLastFlingX = r0
                r17 = r2
                r18 = r7
                r0 = r18
                r1 = r17
                r1.mLastFlingY = r0
                r17 = 0
                r12 = r17
                r17 = 0
                r13 = r17
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r18 = r8
                r19 = r9
                r20 = r5
                r21 = 0
                r22 = 1
                boolean r17 = r17.dispatchNestedPreScroll(r18, r19, r20, r21, r22)
                if (r17 == 0) goto L_0x00e0
                r17 = r8
                r18 = r5
                r19 = 0
                r18 = r18[r19]
                int r17 = r17 - r18
                r8 = r17
                r17 = r9
                r18 = r5
                r19 = 1
                r18 = r18[r19]
                int r17 = r17 - r18
                r9 = r17
            L_0x00e0:
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r0 = r17
                android.support.v7.widget.RecyclerView$Adapter r0 = r0.mAdapter
                r17 = r0
                if (r17 == 0) goto L_0x0176
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r18 = r8
                r19 = r9
                r20 = r2
                r0 = r20
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r20 = r0
                r0 = r20
                int[] r0 = r0.mScrollStepConsumed
                r20 = r0
                r17.scrollStep(r18, r19, r20)
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r0 = r17
                int[] r0 = r0.mScrollStepConsumed
                r17 = r0
                r18 = 0
                r17 = r17[r18]
                r10 = r17
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r0 = r17
                int[] r0 = r0.mScrollStepConsumed
                r17 = r0
                r18 = 1
                r17 = r17[r18]
                r11 = r17
                r17 = r8
                r18 = r10
                int r17 = r17 - r18
                r12 = r17
                r17 = r9
                r18 = r11
                int r17 = r17 - r18
                r13 = r17
                r17 = r4
                if (r17 == 0) goto L_0x0176
                r17 = r4
                boolean r17 = r17.isPendingInitialRun()
                if (r17 != 0) goto L_0x0176
                r17 = r4
                boolean r17 = r17.isRunning()
                if (r17 == 0) goto L_0x0176
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r0 = r17
                android.support.v7.widget.RecyclerView$State r0 = r0.mState
                r17 = r0
                int r17 = r17.getItemCount()
                r14 = r17
                r17 = r14
                if (r17 != 0) goto L_0x0377
                r17 = r4
                r17.stop()
            L_0x0176:
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r0 = r17
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ItemDecoration> r0 = r0.mItemDecorations
                r17 = r0
                boolean r17 = r17.isEmpty()
                if (r17 != 0) goto L_0x0195
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r17.invalidate()
            L_0x0195:
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                int r17 = r17.getOverScrollMode()
                r18 = 2
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x01b8
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r18 = r8
                r19 = r9
                r17.considerReleasingGlowsOnScroll(r18, r19)
            L_0x01b8:
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r18 = r10
                r19 = r11
                r20 = r12
                r21 = r13
                r22 = 0
                r23 = 1
                boolean r17 = r17.dispatchNestedScroll(r18, r19, r20, r21, r22, r23)
                if (r17 != 0) goto L_0x0271
                r17 = r12
                if (r17 != 0) goto L_0x01da
                r17 = r13
                if (r17 == 0) goto L_0x0271
            L_0x01da:
                r17 = r3
                float r17 = r17.getCurrVelocity()
                r0 = r17
                int r0 = (int) r0
                r17 = r0
                r14 = r17
                r17 = 0
                r15 = r17
                r17 = r12
                r18 = r6
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x0202
                r17 = r12
                if (r17 >= 0) goto L_0x03b6
                r17 = r14
                r0 = r17
                int r0 = -r0
                r17 = r0
            L_0x0200:
                r15 = r17
            L_0x0202:
                r17 = 0
                r16 = r17
                r17 = r13
                r18 = r7
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x021d
                r17 = r13
                if (r17 >= 0) goto L_0x03c2
                r17 = r14
                r0 = r17
                int r0 = -r0
                r17 = r0
            L_0x021b:
                r16 = r17
            L_0x021d:
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                int r17 = r17.getOverScrollMode()
                r18 = 2
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x0240
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r18 = r15
                r19 = r16
                r17.absorbGlows(r18, r19)
            L_0x0240:
                r17 = r15
                if (r17 != 0) goto L_0x0256
                r17 = r12
                r18 = r6
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x0256
                r17 = r3
                int r17 = r17.getFinalX()
                if (r17 != 0) goto L_0x0271
            L_0x0256:
                r17 = r16
                if (r17 != 0) goto L_0x026c
                r17 = r13
                r18 = r7
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x026c
                r17 = r3
                int r17 = r17.getFinalY()
                if (r17 != 0) goto L_0x0271
            L_0x026c:
                r17 = r3
                r17.abortAnimation()
            L_0x0271:
                r17 = r10
                if (r17 != 0) goto L_0x0279
                r17 = r11
                if (r17 == 0) goto L_0x0288
            L_0x0279:
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r18 = r10
                r19 = r11
                r17.dispatchOnScrolled(r18, r19)
            L_0x0288:
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                boolean r17 = r17.awakenScrollBars()
                if (r17 != 0) goto L_0x02a1
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r17.invalidate()
            L_0x02a1:
                r17 = r9
                if (r17 == 0) goto L_0x03ce
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r0 = r17
                android.support.v7.widget.RecyclerView$LayoutManager r0 = r0.mLayout
                r17 = r0
                boolean r17 = r17.canScrollVertically()
                if (r17 == 0) goto L_0x03ce
                r17 = r11
                r18 = r9
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x03ce
                r17 = 1
            L_0x02c5:
                r14 = r17
                r17 = r8
                if (r17 == 0) goto L_0x03d2
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r0 = r17
                android.support.v7.widget.RecyclerView$LayoutManager r0 = r0.mLayout
                r17 = r0
                boolean r17 = r17.canScrollHorizontally()
                if (r17 == 0) goto L_0x03d2
                r17 = r10
                r18 = r8
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x03d2
                r17 = 1
            L_0x02eb:
                r15 = r17
                r17 = r8
                if (r17 != 0) goto L_0x02f5
                r17 = r9
                if (r17 == 0) goto L_0x02fd
            L_0x02f5:
                r17 = r15
                if (r17 != 0) goto L_0x02fd
                r17 = r14
                if (r17 == 0) goto L_0x03d6
            L_0x02fd:
                r17 = 1
            L_0x02ff:
                r16 = r17
                r17 = r3
                boolean r17 = r17.isFinished()
                if (r17 != 0) goto L_0x031d
                r17 = r16
                if (r17 != 0) goto L_0x03da
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r18 = 1
                boolean r17 = r17.hasNestedScrollingParent(r18)
                if (r17 != 0) goto L_0x03da
            L_0x031d:
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r18 = 0
                r17.setScrollState(r18)
                boolean r17 = android.support.p003v7.widget.RecyclerView.ALLOW_THREAD_GAP_WORK
                if (r17 == 0) goto L_0x033f
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r0 = r17
                android.support.v7.widget.GapWorker$LayoutPrefetchRegistryImpl r0 = r0.mPrefetchRegistry
                r17 = r0
                r17.clearPrefetchPositions()
            L_0x033f:
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r18 = 1
                r17.stopNestedScroll(r18)
            L_0x034c:
                r17 = r4
                if (r17 == 0) goto L_0x0370
                r17 = r4
                boolean r17 = r17.isPendingInitialRun()
                if (r17 == 0) goto L_0x0361
                r17 = r4
                r18 = 0
                r19 = 0
                r17.onAnimation(r18, r19)
            L_0x0361:
                r17 = r2
                r0 = r17
                boolean r0 = r0.mReSchedulePostAnimationCallback
                r17 = r0
                if (r17 != 0) goto L_0x0370
                r17 = r4
                r17.stop()
            L_0x0370:
                r17 = r2
                r17.enableRunOnAnimationRequests()
                goto L_0x0017
            L_0x0377:
                r17 = r4
                int r17 = r17.getTargetPosition()
                r18 = r14
                r0 = r17
                r1 = r18
                if (r0 < r1) goto L_0x03a3
                r17 = r4
                r18 = r14
                r19 = 1
                int r18 = r18 + -1
                r17.setTargetPosition(r18)
                r17 = r4
                r18 = r8
                r19 = r12
                int r18 = r18 - r19
                r19 = r9
                r20 = r13
                int r19 = r19 - r20
                r17.onAnimation(r18, r19)
                goto L_0x0176
            L_0x03a3:
                r17 = r4
                r18 = r8
                r19 = r12
                int r18 = r18 - r19
                r19 = r9
                r20 = r13
                int r19 = r19 - r20
                r17.onAnimation(r18, r19)
                goto L_0x0176
            L_0x03b6:
                r17 = r12
                if (r17 <= 0) goto L_0x03be
                r17 = r14
                goto L_0x0200
            L_0x03be:
                r17 = 0
                goto L_0x0200
            L_0x03c2:
                r17 = r13
                if (r17 <= 0) goto L_0x03ca
                r17 = r14
                goto L_0x021b
            L_0x03ca:
                r17 = 0
                goto L_0x021b
            L_0x03ce:
                r17 = 0
                goto L_0x02c5
            L_0x03d2:
                r17 = 0
                goto L_0x02eb
            L_0x03d6:
                r17 = 0
                goto L_0x02ff
            L_0x03da:
                r17 = r2
                r17.postOnAnimation()
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r0 = r17
                android.support.v7.widget.GapWorker r0 = r0.mGapWorker
                r17 = r0
                if (r17 == 0) goto L_0x034c
                r17 = r2
                r0 = r17
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r17 = r0
                r0 = r17
                android.support.v7.widget.GapWorker r0 = r0.mGapWorker
                r17 = r0
                r18 = r2
                r0 = r18
                android.support.v7.widget.RecyclerView r0 = r0.this$0
                r18 = r0
                r19 = r8
                r20 = r9
                r17.postFromTraversal(r18, r19, r20)
                goto L_0x034c
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.RecyclerView.ViewFlinger.run():void");
        }

        private void disableRunOnAnimationRequests() {
            this.mReSchedulePostAnimationCallback = false;
            this.mEatRunOnAnimationRequest = true;
        }

        private void enableRunOnAnimationRequests() {
            this.mEatRunOnAnimationRequest = false;
            if (this.mReSchedulePostAnimationCallback) {
                postOnAnimation();
            }
        }

        /* access modifiers changed from: package-private */
        public void postOnAnimation() {
            if (this.mEatRunOnAnimationRequest) {
                this.mReSchedulePostAnimationCallback = true;
                return;
            }
            boolean removeCallbacks = this.this$0.removeCallbacks(this);
            ViewCompat.postOnAnimation(this.this$0, this);
        }

        public void fling(int velocityX, int velocityY) {
            this.this$0.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            this.mScroller.fling(0, 0, velocityX, velocityY, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            postOnAnimation();
        }

        public void smoothScrollBy(int dx, int dy) {
            smoothScrollBy(dx, dy, 0, 0);
        }

        public void smoothScrollBy(int i, int i2, int vx, int vy) {
            int dx = i;
            int dy = i2;
            smoothScrollBy(dx, dy, computeScrollDuration(dx, dy, vx, vy));
        }

        private float distanceInfluenceForSnapDuration(float f) {
            return (float) Math.sin((double) ((f - 0.5f) * 0.47123894f));
        }

        private int computeScrollDuration(int i, int i2, int i3, int i4) {
            int duration;
            int dx = i;
            int dy = i2;
            int vx = i3;
            int vy = i4;
            int absDx = Math.abs(dx);
            int absDy = Math.abs(dy);
            boolean horizontal = absDx > absDy;
            int velocity = (int) Math.sqrt((double) ((vx * vx) + (vy * vy)));
            int delta = (int) Math.sqrt((double) ((dx * dx) + (dy * dy)));
            int containerSize = horizontal ? this.this$0.getWidth() : this.this$0.getHeight();
            int halfContainerSize = containerSize / 2;
            float distance = ((float) halfContainerSize) + (((float) halfContainerSize) * distanceInfluenceForSnapDuration(Math.min(1.0f, (1.0f * ((float) delta)) / ((float) containerSize))));
            if (velocity > 0) {
                duration = 4 * Math.round(1000.0f * Math.abs(distance / ((float) velocity)));
            } else {
                duration = (int) (((((float) (horizontal ? absDx : absDy)) / ((float) containerSize)) + 1.0f) * 300.0f);
            }
            return Math.min(duration, 2000);
        }

        public void smoothScrollBy(int dx, int dy, int duration) {
            smoothScrollBy(dx, dy, duration, RecyclerView.sQuinticInterpolator);
        }

        public void smoothScrollBy(int i, int i2, Interpolator interpolator) {
            int dx = i;
            int dy = i2;
            Interpolator interpolator2 = interpolator;
            smoothScrollBy(dx, dy, computeScrollDuration(dx, dy, 0, 0), interpolator2 == null ? RecyclerView.sQuinticInterpolator : interpolator2);
        }

        public void smoothScrollBy(int i, int i2, int i3, Interpolator interpolator) {
            OverScroller overScroller;
            int dx = i;
            int dy = i2;
            int duration = i3;
            Interpolator interpolator2 = interpolator;
            if (this.mInterpolator != interpolator2) {
                this.mInterpolator = interpolator2;
                new OverScroller(this.this$0.getContext(), interpolator2);
                this.mScroller = overScroller;
            }
            this.this$0.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            this.mScroller.startScroll(0, 0, dx, dy, duration);
            if (Build.VERSION.SDK_INT < 23) {
                boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            }
            postOnAnimation();
        }

        public void stop() {
            boolean removeCallbacks = this.this$0.removeCallbacks(this);
            this.mScroller.abortAnimation();
        }
    }

    /* access modifiers changed from: package-private */
    public void repositionShadowingViews() {
        int count = this.mChildHelper.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = this.mChildHelper.getChildAt(i);
            ViewHolder holder = getChildViewHolder(view);
            if (!(holder == null || holder.mShadowingHolder == null)) {
                View shadowingView = holder.mShadowingHolder.itemView;
                int left = view.getLeft();
                int top = view.getTop();
                if (left != shadowingView.getLeft() || top != shadowingView.getTop()) {
                    shadowingView.layout(left, top, left + shadowingView.getWidth(), top + shadowingView.getHeight());
                }
            }
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$RecyclerViewDataObserver */
    private class RecyclerViewDataObserver extends AdapterDataObserver {
        final /* synthetic */ RecyclerView this$0;

        RecyclerViewDataObserver(RecyclerView recyclerView) {
            this.this$0 = recyclerView;
        }

        public void onChanged() {
            this.this$0.assertNotInLayoutOrScroll((String) null);
            this.this$0.mState.mStructureChanged = true;
            this.this$0.processDataSetCompletelyChanged(true);
            if (!this.this$0.mAdapterHelper.hasPendingUpdates()) {
                this.this$0.requestLayout();
            }
        }

        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            this.this$0.assertNotInLayoutOrScroll((String) null);
            if (this.this$0.mAdapterHelper.onItemRangeChanged(positionStart, itemCount, payload)) {
                triggerUpdateProcessor();
            }
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            this.this$0.assertNotInLayoutOrScroll((String) null);
            if (this.this$0.mAdapterHelper.onItemRangeInserted(positionStart, itemCount)) {
                triggerUpdateProcessor();
            }
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            this.this$0.assertNotInLayoutOrScroll((String) null);
            if (this.this$0.mAdapterHelper.onItemRangeRemoved(positionStart, itemCount)) {
                triggerUpdateProcessor();
            }
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            this.this$0.assertNotInLayoutOrScroll((String) null);
            if (this.this$0.mAdapterHelper.onItemRangeMoved(fromPosition, toPosition, itemCount)) {
                triggerUpdateProcessor();
            }
        }

        /* access modifiers changed from: package-private */
        public void triggerUpdateProcessor() {
            if (!RecyclerView.POST_UPDATES_ON_ANIMATION || !this.this$0.mHasFixedSize || !this.this$0.mIsAttached) {
                this.this$0.mAdapterUpdateDuringMeasure = true;
                this.this$0.requestLayout();
                return;
            }
            ViewCompat.postOnAnimation(this.this$0, this.this$0.mUpdateChildViewsRunnable);
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$EdgeEffectFactory */
    public static class EdgeEffectFactory {
        public static final int DIRECTION_BOTTOM = 3;
        public static final int DIRECTION_LEFT = 0;
        public static final int DIRECTION_RIGHT = 2;
        public static final int DIRECTION_TOP = 1;

        @Retention(RetentionPolicy.SOURCE)
        /* renamed from: android.support.v7.widget.RecyclerView$EdgeEffectFactory$EdgeDirection */
        public @interface EdgeDirection {
        }

        public EdgeEffectFactory() {
        }

        /* access modifiers changed from: protected */
        @NonNull
        public EdgeEffect createEdgeEffect(@NonNull RecyclerView view, int i) {
            EdgeEffect edgeEffect;
            int i2 = i;
            new EdgeEffect(view.getContext());
            return edgeEffect;
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$RecycledViewPool */
    public static class RecycledViewPool {
        private static final int DEFAULT_MAX_SCRAP = 5;
        private int mAttachCount = 0;
        SparseArray<ScrapData> mScrap;

        public RecycledViewPool() {
            SparseArray<ScrapData> sparseArray;
            new SparseArray<>();
            this.mScrap = sparseArray;
        }

        /* renamed from: android.support.v7.widget.RecyclerView$RecycledViewPool$ScrapData */
        static class ScrapData {
            long mBindRunningAverageNs = 0;
            long mCreateRunningAverageNs = 0;
            int mMaxScrap = 5;
            final ArrayList<ViewHolder> mScrapHeap;

            ScrapData() {
                ArrayList<ViewHolder> arrayList;
                new ArrayList<>();
                this.mScrapHeap = arrayList;
            }
        }

        public void clear() {
            for (int i = 0; i < this.mScrap.size(); i++) {
                this.mScrap.valueAt(i).mScrapHeap.clear();
            }
        }

        public void setMaxRecycledViews(int viewType, int i) {
            int max = i;
            ScrapData scrapData = getScrapDataForType(viewType);
            scrapData.mMaxScrap = max;
            ArrayList<ViewHolder> scrapHeap = scrapData.mScrapHeap;
            while (scrapHeap.size() > max) {
                ViewHolder remove = scrapHeap.remove(scrapHeap.size() - 1);
            }
        }

        public int getRecycledViewCount(int viewType) {
            return getScrapDataForType(viewType).mScrapHeap.size();
        }

        @Nullable
        public ViewHolder getRecycledView(int viewType) {
            ScrapData scrapData = this.mScrap.get(viewType);
            if (scrapData == null || scrapData.mScrapHeap.isEmpty()) {
                return null;
            }
            ArrayList<ViewHolder> scrapHeap = scrapData.mScrapHeap;
            return scrapHeap.remove(scrapHeap.size() - 1);
        }

        /* access modifiers changed from: package-private */
        public int size() {
            int count = 0;
            for (int i = 0; i < this.mScrap.size(); i++) {
                ArrayList<ViewHolder> viewHolders = this.mScrap.valueAt(i).mScrapHeap;
                if (viewHolders != null) {
                    count += viewHolders.size();
                }
            }
            return count;
        }

        public void putRecycledView(ViewHolder viewHolder) {
            ViewHolder scrap = viewHolder;
            int viewType = scrap.getItemViewType();
            ArrayList<ViewHolder> scrapHeap = getScrapDataForType(viewType).mScrapHeap;
            if (this.mScrap.get(viewType).mMaxScrap > scrapHeap.size()) {
                scrap.resetInternal();
                boolean add = scrapHeap.add(scrap);
            }
        }

        /* access modifiers changed from: package-private */
        public long runningAverage(long j, long j2) {
            long oldAverage = j;
            long newValue = j2;
            if (oldAverage == 0) {
                return newValue;
            }
            return ((oldAverage / 4) * 3) + (newValue / 4);
        }

        /* access modifiers changed from: package-private */
        public void factorInCreateTime(int viewType, long createTimeNs) {
            ScrapData scrapData = getScrapDataForType(viewType);
            scrapData.mCreateRunningAverageNs = runningAverage(scrapData.mCreateRunningAverageNs, createTimeNs);
        }

        /* access modifiers changed from: package-private */
        public void factorInBindTime(int viewType, long bindTimeNs) {
            ScrapData scrapData = getScrapDataForType(viewType);
            scrapData.mBindRunningAverageNs = runningAverage(scrapData.mBindRunningAverageNs, bindTimeNs);
        }

        /* access modifiers changed from: package-private */
        public boolean willCreateInTime(int viewType, long j, long j2) {
            long approxCurrentNs = j;
            long deadlineNs = j2;
            long expectedDurationNs = getScrapDataForType(viewType).mCreateRunningAverageNs;
            return expectedDurationNs == 0 || approxCurrentNs + expectedDurationNs < deadlineNs;
        }

        /* access modifiers changed from: package-private */
        public boolean willBindInTime(int viewType, long j, long j2) {
            long approxCurrentNs = j;
            long deadlineNs = j2;
            long expectedDurationNs = getScrapDataForType(viewType).mBindRunningAverageNs;
            return expectedDurationNs == 0 || approxCurrentNs + expectedDurationNs < deadlineNs;
        }

        /* access modifiers changed from: package-private */
        public void attach() {
            this.mAttachCount++;
        }

        /* access modifiers changed from: package-private */
        public void detach() {
            this.mAttachCount--;
        }

        /* access modifiers changed from: package-private */
        public void onAdapterChanged(Adapter oldAdapter, Adapter adapter, boolean z) {
            Adapter newAdapter = adapter;
            boolean compatibleWithPrevious = z;
            if (oldAdapter != null) {
                detach();
            }
            if (!compatibleWithPrevious && this.mAttachCount == 0) {
                clear();
            }
            if (newAdapter != null) {
                attach();
            }
        }

        private ScrapData getScrapDataForType(int i) {
            ScrapData scrapData;
            int viewType = i;
            ScrapData scrapData2 = this.mScrap.get(viewType);
            if (scrapData2 == null) {
                new ScrapData();
                scrapData2 = scrapData;
                this.mScrap.put(viewType, scrapData2);
            }
            return scrapData2;
        }
    }

    @Nullable
    static RecyclerView findNestedRecyclerView(@NonNull View view) {
        View view2 = view;
        if (!(view2 instanceof ViewGroup)) {
            return null;
        }
        if (view2 instanceof RecyclerView) {
            return (RecyclerView) view2;
        }
        ViewGroup parent = (ViewGroup) view2;
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            RecyclerView descendant = findNestedRecyclerView(parent.getChildAt(i));
            if (descendant != null) {
                return descendant;
            }
        }
        return null;
    }

    static void clearNestedRecyclerViewIfNotNested(@NonNull ViewHolder viewHolder) {
        ViewHolder holder = viewHolder;
        if (holder.mNestedRecyclerView != null) {
            View view = (View) holder.mNestedRecyclerView.get();
            while (true) {
                View item = view;
                if (item == null) {
                    holder.mNestedRecyclerView = null;
                    return;
                } else if (item != holder.itemView) {
                    ViewParent parent = item.getParent();
                    if (parent instanceof View) {
                        view = (View) parent;
                    } else {
                        view = null;
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public long getNanoTime() {
        if (ALLOW_THREAD_GAP_WORK) {
            return System.nanoTime();
        }
        return 0;
    }

    /* renamed from: android.support.v7.widget.RecyclerView$Recycler */
    public final class Recycler {
        static final int DEFAULT_CACHE_SIZE = 2;
        final ArrayList<ViewHolder> mAttachedScrap;
        final ArrayList<ViewHolder> mCachedViews;
        ArrayList<ViewHolder> mChangedScrap = null;
        RecycledViewPool mRecyclerPool;
        private int mRequestedCacheMax;
        private final List<ViewHolder> mUnmodifiableAttachedScrap;
        private ViewCacheExtension mViewCacheExtension;
        int mViewCacheMax;
        final /* synthetic */ RecyclerView this$0;

        public Recycler(RecyclerView this$02) {
            ArrayList<ViewHolder> arrayList;
            ArrayList<ViewHolder> arrayList2;
            this.this$0 = this$02;
            new ArrayList<>();
            this.mAttachedScrap = arrayList;
            new ArrayList<>();
            this.mCachedViews = arrayList2;
            this.mUnmodifiableAttachedScrap = Collections.unmodifiableList(this.mAttachedScrap);
            this.mRequestedCacheMax = 2;
            this.mViewCacheMax = 2;
        }

        public void clear() {
            this.mAttachedScrap.clear();
            recycleAndClearCachedViews();
        }

        public void setViewCacheSize(int viewCount) {
            this.mRequestedCacheMax = viewCount;
            updateViewCacheSize();
        }

        /* access modifiers changed from: package-private */
        public void updateViewCacheSize() {
            this.mViewCacheMax = this.mRequestedCacheMax + (this.this$0.mLayout != null ? this.this$0.mLayout.mPrefetchMaxCountObserved : 0);
            for (int i = this.mCachedViews.size() - 1; i >= 0 && this.mCachedViews.size() > this.mViewCacheMax; i--) {
                recycleCachedViewAt(i);
            }
        }

        @NonNull
        public List<ViewHolder> getScrapList() {
            return this.mUnmodifiableAttachedScrap;
        }

        /* access modifiers changed from: package-private */
        public boolean validateViewHolderForOffsetPosition(ViewHolder viewHolder) {
            Throwable th;
            StringBuilder sb;
            ViewHolder holder = viewHolder;
            if (holder.isRemoved()) {
                return this.this$0.mState.isPreLayout();
            }
            if (holder.mPosition < 0 || holder.mPosition >= this.this$0.mAdapter.getItemCount()) {
                Throwable th2 = th;
                new StringBuilder();
                new IndexOutOfBoundsException(sb.append("Inconsistency detected. Invalid view holder adapter position").append(holder).append(this.this$0.exceptionLabel()).toString());
                throw th2;
            } else if (!this.this$0.mState.isPreLayout() && this.this$0.mAdapter.getItemViewType(holder.mPosition) != holder.getItemViewType()) {
                return false;
            } else {
                if (!this.this$0.mAdapter.hasStableIds()) {
                    return true;
                }
                return holder.getItemId() == this.this$0.mAdapter.getItemId(holder.mPosition);
            }
        }

        private boolean tryBindViewHolderByDeadline(@NonNull ViewHolder viewHolder, int i, int i2, long j) {
            ViewHolder holder = viewHolder;
            int offsetPosition = i;
            int position = i2;
            long deadlineNs = j;
            holder.mOwnerRecyclerView = this.this$0;
            int viewType = holder.getItemViewType();
            long startBindNs = this.this$0.getNanoTime();
            if (deadlineNs != RecyclerView.FOREVER_NS && !this.mRecyclerPool.willBindInTime(viewType, startBindNs, deadlineNs)) {
                return false;
            }
            this.this$0.mAdapter.bindViewHolder(holder, offsetPosition);
            this.mRecyclerPool.factorInBindTime(holder.getItemViewType(), this.this$0.getNanoTime() - startBindNs);
            attachAccessibilityDelegateOnBind(holder);
            if (this.this$0.mState.isPreLayout()) {
                holder.mPreLayoutPosition = position;
            }
            return true;
        }

        public void bindViewToPosition(@NonNull View view, int i) {
            Throwable th;
            StringBuilder sb;
            LayoutParams rvLayoutParams;
            Throwable th2;
            StringBuilder sb2;
            int position = i;
            ViewHolder holder = RecyclerView.getChildViewHolderInt(view);
            if (holder == null) {
                Throwable th3 = th2;
                new StringBuilder();
                new IllegalArgumentException(sb2.append("The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter").append(this.this$0.exceptionLabel()).toString());
                throw th3;
            }
            int offsetPosition = this.this$0.mAdapterHelper.findPositionOffset(position);
            if (offsetPosition < 0 || offsetPosition >= this.this$0.mAdapter.getItemCount()) {
                Throwable th4 = th;
                new StringBuilder();
                new IndexOutOfBoundsException(sb.append("Inconsistency detected. Invalid item position ").append(position).append("(offset:").append(offsetPosition).append(").").append("state:").append(this.this$0.mState.getItemCount()).append(this.this$0.exceptionLabel()).toString());
                throw th4;
            }
            boolean tryBindViewHolderByDeadline = tryBindViewHolderByDeadline(holder, offsetPosition, position, RecyclerView.FOREVER_NS);
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp == null) {
                rvLayoutParams = (LayoutParams) this.this$0.generateDefaultLayoutParams();
                holder.itemView.setLayoutParams(rvLayoutParams);
            } else if (!this.this$0.checkLayoutParams(lp)) {
                rvLayoutParams = (LayoutParams) this.this$0.generateLayoutParams(lp);
                holder.itemView.setLayoutParams(rvLayoutParams);
            } else {
                rvLayoutParams = (LayoutParams) lp;
            }
            rvLayoutParams.mInsetsDirty = true;
            rvLayoutParams.mViewHolder = holder;
            rvLayoutParams.mPendingInvalidate = holder.itemView.getParent() == null;
        }

        public int convertPreLayoutPositionToPostLayout(int i) {
            Throwable th;
            StringBuilder sb;
            int position = i;
            if (position < 0 || position >= this.this$0.mState.getItemCount()) {
                Throwable th2 = th;
                new StringBuilder();
                new IndexOutOfBoundsException(sb.append("invalid position ").append(position).append(". State ").append("item count is ").append(this.this$0.mState.getItemCount()).append(this.this$0.exceptionLabel()).toString());
                throw th2;
            } else if (!this.this$0.mState.isPreLayout()) {
                return position;
            } else {
                return this.this$0.mAdapterHelper.findPositionOffset(position);
            }
        }

        @NonNull
        public View getViewForPosition(int position) {
            return getViewForPosition(position, false);
        }

        /* access modifiers changed from: package-private */
        public View getViewForPosition(int position, boolean dryRun) {
            return tryGetViewHolderForPositionByDeadline(position, dryRun, RecyclerView.FOREVER_NS).itemView;
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public ViewHolder tryGetViewHolderForPositionByDeadline(int i, boolean z, long j) {
            Throwable th;
            StringBuilder sb;
            LayoutParams rvLayoutParams;
            Throwable th2;
            StringBuilder sb2;
            RecyclerView innerView;
            WeakReference<RecyclerView> weakReference;
            View view;
            Throwable th3;
            StringBuilder sb3;
            Throwable th4;
            StringBuilder sb4;
            int position = i;
            boolean dryRun = z;
            long deadlineNs = j;
            if (position < 0 || position >= this.this$0.mState.getItemCount()) {
                Throwable th5 = th;
                new StringBuilder();
                new IndexOutOfBoundsException(sb.append("Invalid item position ").append(position).append("(").append(position).append("). Item count:").append(this.this$0.mState.getItemCount()).append(this.this$0.exceptionLabel()).toString());
                throw th5;
            }
            boolean fromScrapOrHiddenOrCache = false;
            ViewHolder holder = null;
            if (this.this$0.mState.isPreLayout()) {
                holder = getChangedScrapViewForPosition(position);
                fromScrapOrHiddenOrCache = holder != null;
            }
            if (holder == null) {
                holder = getScrapOrHiddenOrCachedHolderForPosition(position, dryRun);
                if (holder != null) {
                    if (!validateViewHolderForOffsetPosition(holder)) {
                        if (!dryRun) {
                            holder.addFlags(4);
                            if (holder.isScrap()) {
                                this.this$0.removeDetachedView(holder.itemView, false);
                                holder.unScrap();
                            } else if (holder.wasReturnedFromScrap()) {
                                holder.clearReturnedFromScrapFlag();
                            }
                            recycleViewHolderInternal(holder);
                        }
                        holder = null;
                    } else {
                        fromScrapOrHiddenOrCache = true;
                    }
                }
            }
            if (holder == null) {
                int offsetPosition = this.this$0.mAdapterHelper.findPositionOffset(position);
                if (offsetPosition < 0 || offsetPosition >= this.this$0.mAdapter.getItemCount()) {
                    Throwable th6 = th2;
                    new StringBuilder();
                    new IndexOutOfBoundsException(sb2.append("Inconsistency detected. Invalid item position ").append(position).append("(offset:").append(offsetPosition).append(").").append("state:").append(this.this$0.mState.getItemCount()).append(this.this$0.exceptionLabel()).toString());
                    throw th6;
                }
                int type = this.this$0.mAdapter.getItemViewType(offsetPosition);
                if (this.this$0.mAdapter.hasStableIds()) {
                    holder = getScrapOrCachedViewForId(this.this$0.mAdapter.getItemId(offsetPosition), type, dryRun);
                    if (holder != null) {
                        holder.mPosition = offsetPosition;
                        fromScrapOrHiddenOrCache = true;
                    }
                }
                if (!(holder != null || this.mViewCacheExtension == null || (view = this.mViewCacheExtension.getViewForPositionAndType(this, position, type)) == null)) {
                    holder = this.this$0.getChildViewHolder(view);
                    if (holder == null) {
                        Throwable th7 = th4;
                        new StringBuilder();
                        new IllegalArgumentException(sb4.append("getViewForPositionAndType returned a view which does not have a ViewHolder").append(this.this$0.exceptionLabel()).toString());
                        throw th7;
                    } else if (holder.shouldIgnore()) {
                        Throwable th8 = th3;
                        new StringBuilder();
                        new IllegalArgumentException(sb3.append("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.").append(this.this$0.exceptionLabel()).toString());
                        throw th8;
                    }
                }
                if (holder == null) {
                    holder = getRecycledViewPool().getRecycledView(type);
                    if (holder != null) {
                        holder.resetInternal();
                        if (RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST) {
                            invalidateDisplayListInt(holder);
                        }
                    }
                }
                if (holder == null) {
                    long start = this.this$0.getNanoTime();
                    if (deadlineNs != RecyclerView.FOREVER_NS && !this.mRecyclerPool.willCreateInTime(type, start, deadlineNs)) {
                        return null;
                    }
                    holder = this.this$0.mAdapter.createViewHolder(this.this$0, type);
                    if (RecyclerView.ALLOW_THREAD_GAP_WORK && (innerView = RecyclerView.findNestedRecyclerView(holder.itemView)) != null) {
                        new WeakReference<>(innerView);
                        holder.mNestedRecyclerView = weakReference;
                    }
                    this.mRecyclerPool.factorInCreateTime(type, this.this$0.getNanoTime() - start);
                }
            }
            if (fromScrapOrHiddenOrCache && !this.this$0.mState.isPreLayout() && holder.hasAnyOfTheFlags(8192)) {
                holder.setFlags(0, 8192);
                if (this.this$0.mState.mRunSimpleAnimations) {
                    this.this$0.recordAnimationInfoIfBouncedHiddenView(holder, this.this$0.mItemAnimator.recordPreLayoutInformation(this.this$0.mState, holder, ItemAnimator.buildAdapterChangeFlagsForAnimations(holder) | 4096, holder.getUnmodifiedPayloads()));
                }
            }
            boolean bound = false;
            if (this.this$0.mState.isPreLayout() && holder.isBound()) {
                holder.mPreLayoutPosition = position;
            } else if (!holder.isBound() || holder.needsUpdate() || holder.isInvalid()) {
                bound = tryBindViewHolderByDeadline(holder, this.this$0.mAdapterHelper.findPositionOffset(position), position, deadlineNs);
            }
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp == null) {
                rvLayoutParams = (LayoutParams) this.this$0.generateDefaultLayoutParams();
                holder.itemView.setLayoutParams(rvLayoutParams);
            } else if (!this.this$0.checkLayoutParams(lp)) {
                rvLayoutParams = (LayoutParams) this.this$0.generateLayoutParams(lp);
                holder.itemView.setLayoutParams(rvLayoutParams);
            } else {
                rvLayoutParams = (LayoutParams) lp;
            }
            rvLayoutParams.mViewHolder = holder;
            rvLayoutParams.mPendingInvalidate = fromScrapOrHiddenOrCache && bound;
            return holder;
        }

        private void attachAccessibilityDelegateOnBind(ViewHolder viewHolder) {
            ViewHolder holder = viewHolder;
            if (this.this$0.isAccessibilityEnabled()) {
                View itemView = holder.itemView;
                if (ViewCompat.getImportantForAccessibility(itemView) == 0) {
                    ViewCompat.setImportantForAccessibility(itemView, 1);
                }
                if (!ViewCompat.hasAccessibilityDelegate(itemView)) {
                    holder.addFlags(16384);
                    ViewCompat.setAccessibilityDelegate(itemView, this.this$0.mAccessibilityDelegate.getItemDelegate());
                }
            }
        }

        private void invalidateDisplayListInt(ViewHolder viewHolder) {
            ViewHolder holder = viewHolder;
            if (holder.itemView instanceof ViewGroup) {
                invalidateDisplayListInt((ViewGroup) holder.itemView, false);
            }
        }

        private void invalidateDisplayListInt(ViewGroup viewGroup, boolean z) {
            ViewGroup viewGroup2 = viewGroup;
            boolean invalidateThis = z;
            for (int i = viewGroup2.getChildCount() - 1; i >= 0; i--) {
                View view = viewGroup2.getChildAt(i);
                if (view instanceof ViewGroup) {
                    invalidateDisplayListInt((ViewGroup) view, true);
                }
            }
            if (invalidateThis) {
                if (viewGroup2.getVisibility() == 4) {
                    viewGroup2.setVisibility(0);
                    viewGroup2.setVisibility(4);
                    return;
                }
                int visibility = viewGroup2.getVisibility();
                viewGroup2.setVisibility(4);
                viewGroup2.setVisibility(visibility);
            }
        }

        public void recycleView(@NonNull View view) {
            View view2 = view;
            ViewHolder holder = RecyclerView.getChildViewHolderInt(view2);
            if (holder.isTmpDetached()) {
                this.this$0.removeDetachedView(view2, false);
            }
            if (holder.isScrap()) {
                holder.unScrap();
            } else if (holder.wasReturnedFromScrap()) {
                holder.clearReturnedFromScrapFlag();
            }
            recycleViewHolderInternal(holder);
        }

        /* access modifiers changed from: package-private */
        public void recycleViewInternal(View view) {
            recycleViewHolderInternal(RecyclerView.getChildViewHolderInt(view));
        }

        /* access modifiers changed from: package-private */
        public void recycleAndClearCachedViews() {
            for (int i = this.mCachedViews.size() - 1; i >= 0; i--) {
                recycleCachedViewAt(i);
            }
            this.mCachedViews.clear();
            if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                this.this$0.mPrefetchRegistry.clearPrefetchPositions();
            }
        }

        /* access modifiers changed from: package-private */
        public void recycleCachedViewAt(int i) {
            int cachedViewIndex = i;
            addViewHolderToRecycledViewPool(this.mCachedViews.get(cachedViewIndex), true);
            ViewHolder remove = this.mCachedViews.remove(cachedViewIndex);
        }

        /* access modifiers changed from: package-private */
        public void recycleViewHolderInternal(ViewHolder viewHolder) {
            StringBuilder sb;
            Throwable th;
            StringBuilder sb2;
            Throwable th2;
            StringBuilder sb3;
            ViewHolder holder = viewHolder;
            if (holder.isScrap() || holder.itemView.getParent() != null) {
                IllegalArgumentException illegalArgumentException = r14;
                new StringBuilder();
                IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException(sb.append("Scrapped or attached views may not be recycled. isScrap:").append(holder.isScrap()).append(" isAttached:").append(holder.itemView.getParent() != null).append(this.this$0.exceptionLabel()).toString());
                throw illegalArgumentException;
            } else if (holder.isTmpDetached()) {
                Throwable th3 = th2;
                new StringBuilder();
                new IllegalArgumentException(sb3.append("Tmp detached view should be removed from RecyclerView before it can be recycled: ").append(holder).append(this.this$0.exceptionLabel()).toString());
                throw th3;
            } else if (holder.shouldIgnore()) {
                Throwable th4 = th;
                new StringBuilder();
                new IllegalArgumentException(sb2.append("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.").append(this.this$0.exceptionLabel()).toString());
                throw th4;
            } else {
                boolean transientStatePreventsRecycling = holder.doesTransientStatePreventRecycling();
                boolean cached = false;
                boolean recycled = false;
                if ((this.this$0.mAdapter != null && transientStatePreventsRecycling && this.this$0.mAdapter.onFailedToRecycleView(holder)) || holder.isRecyclable()) {
                    if (this.mViewCacheMax > 0 && !holder.hasAnyOfTheFlags(526)) {
                        int cachedViewSize = this.mCachedViews.size();
                        if (cachedViewSize >= this.mViewCacheMax && cachedViewSize > 0) {
                            recycleCachedViewAt(0);
                            cachedViewSize--;
                        }
                        int targetCacheIndex = cachedViewSize;
                        if (RecyclerView.ALLOW_THREAD_GAP_WORK && cachedViewSize > 0 && !this.this$0.mPrefetchRegistry.lastPrefetchIncludedPosition(holder.mPosition)) {
                            int cacheIndex = cachedViewSize - 1;
                            while (cacheIndex >= 0) {
                                if (!this.this$0.mPrefetchRegistry.lastPrefetchIncludedPosition(this.mCachedViews.get(cacheIndex).mPosition)) {
                                    break;
                                }
                                cacheIndex--;
                            }
                            targetCacheIndex = cacheIndex + 1;
                        }
                        this.mCachedViews.add(targetCacheIndex, holder);
                        cached = true;
                    }
                    if (!cached) {
                        addViewHolderToRecycledViewPool(holder, true);
                        recycled = true;
                    }
                }
                this.this$0.mViewInfoStore.removeViewHolder(holder);
                if (!cached && !recycled && transientStatePreventsRecycling) {
                    holder.mOwnerRecyclerView = null;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void addViewHolderToRecycledViewPool(@NonNull ViewHolder viewHolder, boolean z) {
            ViewHolder holder = viewHolder;
            boolean dispatchRecycled = z;
            RecyclerView.clearNestedRecyclerViewIfNotNested(holder);
            if (holder.hasAnyOfTheFlags(16384)) {
                holder.setFlags(0, 16384);
                ViewCompat.setAccessibilityDelegate(holder.itemView, (AccessibilityDelegateCompat) null);
            }
            if (dispatchRecycled) {
                dispatchViewRecycled(holder);
            }
            holder.mOwnerRecyclerView = null;
            getRecycledViewPool().putRecycledView(holder);
        }

        /* access modifiers changed from: package-private */
        public void quickRecycleScrapView(View view) {
            ViewHolder holder = RecyclerView.getChildViewHolderInt(view);
            holder.mScrapContainer = null;
            holder.mInChangeScrap = false;
            holder.clearReturnedFromScrapFlag();
            recycleViewHolderInternal(holder);
        }

        /* access modifiers changed from: package-private */
        public void scrapView(View view) {
            Throwable th;
            StringBuilder sb;
            ArrayList<ViewHolder> arrayList;
            ViewHolder holder = RecyclerView.getChildViewHolderInt(view);
            if (!holder.hasAnyOfTheFlags(12) && holder.isUpdated() && !this.this$0.canReuseUpdatedViewHolder(holder)) {
                if (this.mChangedScrap == null) {
                    new ArrayList<>();
                    this.mChangedScrap = arrayList;
                }
                holder.setScrapContainer(this, true);
                boolean add = this.mChangedScrap.add(holder);
            } else if (!holder.isInvalid() || holder.isRemoved() || this.this$0.mAdapter.hasStableIds()) {
                holder.setScrapContainer(this, false);
                boolean add2 = this.mAttachedScrap.add(holder);
            } else {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.").append(this.this$0.exceptionLabel()).toString());
                throw th2;
            }
        }

        /* access modifiers changed from: package-private */
        public void unscrapView(ViewHolder viewHolder) {
            ViewHolder holder = viewHolder;
            if (holder.mInChangeScrap) {
                boolean remove = this.mChangedScrap.remove(holder);
            } else {
                boolean remove2 = this.mAttachedScrap.remove(holder);
            }
            holder.mScrapContainer = null;
            holder.mInChangeScrap = false;
            holder.clearReturnedFromScrapFlag();
        }

        /* access modifiers changed from: package-private */
        public int getScrapCount() {
            return this.mAttachedScrap.size();
        }

        /* access modifiers changed from: package-private */
        public View getScrapViewAt(int index) {
            return this.mAttachedScrap.get(index).itemView;
        }

        /* access modifiers changed from: package-private */
        public void clearScrap() {
            this.mAttachedScrap.clear();
            if (this.mChangedScrap != null) {
                this.mChangedScrap.clear();
            }
        }

        /* access modifiers changed from: package-private */
        public ViewHolder getChangedScrapViewForPosition(int i) {
            int i2;
            int position = i;
            if (this.mChangedScrap != null) {
                int size = this.mChangedScrap.size();
                int changedScrapSize = size;
                if (size != 0) {
                    int i3 = 0;
                    while (i3 < changedScrapSize) {
                        ViewHolder holder = this.mChangedScrap.get(i3);
                        if (holder.wasReturnedFromScrap() || holder.getLayoutPosition() != position) {
                            i3++;
                        } else {
                            holder.addFlags(32);
                            return holder;
                        }
                    }
                    if (this.this$0.mAdapter.hasStableIds() && (i2 = this.this$0.mAdapterHelper.findPositionOffset(position)) > 0 && i2 < this.this$0.mAdapter.getItemCount()) {
                        long id = this.this$0.mAdapter.getItemId(i2);
                        int i4 = 0;
                        while (i4 < changedScrapSize) {
                            ViewHolder holder2 = this.mChangedScrap.get(i4);
                            if (holder2.wasReturnedFromScrap() || holder2.getItemId() != id) {
                                i4++;
                            } else {
                                holder2.addFlags(32);
                                return holder2;
                            }
                        }
                    }
                    return null;
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public ViewHolder getScrapOrHiddenOrCachedHolderForPosition(int i, boolean z) {
            View view;
            Throwable th;
            StringBuilder sb;
            int position = i;
            boolean dryRun = z;
            int scrapCount = this.mAttachedScrap.size();
            int i2 = 0;
            while (i2 < scrapCount) {
                ViewHolder holder = this.mAttachedScrap.get(i2);
                if (holder.wasReturnedFromScrap() || holder.getLayoutPosition() != position || holder.isInvalid() || (!this.this$0.mState.mInPreLayout && holder.isRemoved())) {
                    i2++;
                } else {
                    holder.addFlags(32);
                    return holder;
                }
            }
            if (dryRun || (view = this.this$0.mChildHelper.findHiddenNonRemovedView(position)) == null) {
                int cacheSize = this.mCachedViews.size();
                int i3 = 0;
                while (i3 < cacheSize) {
                    ViewHolder holder2 = this.mCachedViews.get(i3);
                    if (holder2.isInvalid() || holder2.getLayoutPosition() != position) {
                        i3++;
                    } else {
                        if (!dryRun) {
                            ViewHolder remove = this.mCachedViews.remove(i3);
                        }
                        return holder2;
                    }
                }
                return null;
            }
            ViewHolder vh = RecyclerView.getChildViewHolderInt(view);
            this.this$0.mChildHelper.unhide(view);
            int layoutIndex = this.this$0.mChildHelper.indexOfChild(view);
            if (layoutIndex == -1) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("layout index should not be -1 after unhiding a view:").append(vh).append(this.this$0.exceptionLabel()).toString());
                throw th2;
            }
            this.this$0.mChildHelper.detachViewFromParent(layoutIndex);
            scrapView(view);
            vh.addFlags(8224);
            return vh;
        }

        /* access modifiers changed from: package-private */
        public ViewHolder getScrapOrCachedViewForId(long j, int i, boolean z) {
            long id = j;
            int type = i;
            boolean dryRun = z;
            for (int i2 = this.mAttachedScrap.size() - 1; i2 >= 0; i2--) {
                ViewHolder holder = this.mAttachedScrap.get(i2);
                if (holder.getItemId() == id && !holder.wasReturnedFromScrap()) {
                    if (type == holder.getItemViewType()) {
                        holder.addFlags(32);
                        if (holder.isRemoved() && !this.this$0.mState.isPreLayout()) {
                            holder.setFlags(2, 14);
                        }
                        return holder;
                    } else if (!dryRun) {
                        ViewHolder remove = this.mAttachedScrap.remove(i2);
                        this.this$0.removeDetachedView(holder.itemView, false);
                        quickRecycleScrapView(holder.itemView);
                    }
                }
            }
            for (int i3 = this.mCachedViews.size() - 1; i3 >= 0; i3--) {
                ViewHolder holder2 = this.mCachedViews.get(i3);
                if (holder2.getItemId() == id) {
                    if (type == holder2.getItemViewType()) {
                        if (!dryRun) {
                            ViewHolder remove2 = this.mCachedViews.remove(i3);
                        }
                        return holder2;
                    } else if (!dryRun) {
                        recycleCachedViewAt(i3);
                        return null;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public void dispatchViewRecycled(@NonNull ViewHolder viewHolder) {
            ViewHolder holder = viewHolder;
            if (this.this$0.mRecyclerListener != null) {
                this.this$0.mRecyclerListener.onViewRecycled(holder);
            }
            if (this.this$0.mAdapter != null) {
                this.this$0.mAdapter.onViewRecycled(holder);
            }
            if (this.this$0.mState != null) {
                this.this$0.mViewInfoStore.removeViewHolder(holder);
            }
        }

        /* access modifiers changed from: package-private */
        public void onAdapterChanged(Adapter oldAdapter, Adapter newAdapter, boolean compatibleWithPrevious) {
            clear();
            getRecycledViewPool().onAdapterChanged(oldAdapter, newAdapter, compatibleWithPrevious);
        }

        /* access modifiers changed from: package-private */
        public void offsetPositionRecordsForMove(int i, int i2) {
            int start;
            int end;
            int inBetweenOffset;
            int from = i;
            int to = i2;
            if (from < to) {
                start = from;
                end = to;
                inBetweenOffset = -1;
            } else {
                start = to;
                end = from;
                inBetweenOffset = 1;
            }
            int cachedCount = this.mCachedViews.size();
            for (int i3 = 0; i3 < cachedCount; i3++) {
                ViewHolder holder = this.mCachedViews.get(i3);
                if (holder != null && holder.mPosition >= start && holder.mPosition <= end) {
                    if (holder.mPosition == from) {
                        holder.offsetPosition(to - from, false);
                    } else {
                        holder.offsetPosition(inBetweenOffset, false);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void offsetPositionRecordsForInsert(int i, int i2) {
            int insertedAt = i;
            int count = i2;
            int cachedCount = this.mCachedViews.size();
            for (int i3 = 0; i3 < cachedCount; i3++) {
                ViewHolder holder = this.mCachedViews.get(i3);
                if (holder != null && holder.mPosition >= insertedAt) {
                    holder.offsetPosition(count, true);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void offsetPositionRecordsForRemove(int i, int i2, boolean z) {
            int removedFrom = i;
            int count = i2;
            boolean applyToPreLayout = z;
            int removedEnd = removedFrom + count;
            for (int i3 = this.mCachedViews.size() - 1; i3 >= 0; i3--) {
                ViewHolder holder = this.mCachedViews.get(i3);
                if (holder != null) {
                    if (holder.mPosition >= removedEnd) {
                        holder.offsetPosition(-count, applyToPreLayout);
                    } else if (holder.mPosition >= removedFrom) {
                        holder.addFlags(8);
                        recycleCachedViewAt(i3);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void setViewCacheExtension(ViewCacheExtension extension) {
            ViewCacheExtension viewCacheExtension = extension;
            this.mViewCacheExtension = viewCacheExtension;
        }

        /* access modifiers changed from: package-private */
        public void setRecycledViewPool(RecycledViewPool recycledViewPool) {
            RecycledViewPool pool = recycledViewPool;
            if (this.mRecyclerPool != null) {
                this.mRecyclerPool.detach();
            }
            this.mRecyclerPool = pool;
            if (this.mRecyclerPool != null && this.this$0.getAdapter() != null) {
                this.mRecyclerPool.attach();
            }
        }

        /* access modifiers changed from: package-private */
        public RecycledViewPool getRecycledViewPool() {
            RecycledViewPool recycledViewPool;
            if (this.mRecyclerPool == null) {
                new RecycledViewPool();
                this.mRecyclerPool = recycledViewPool;
            }
            return this.mRecyclerPool;
        }

        /* access modifiers changed from: package-private */
        public void viewRangeUpdate(int i, int itemCount) {
            int pos;
            int positionStart = i;
            int positionEnd = positionStart + itemCount;
            for (int i2 = this.mCachedViews.size() - 1; i2 >= 0; i2--) {
                ViewHolder holder = this.mCachedViews.get(i2);
                if (holder != null && (pos = holder.mPosition) >= positionStart && pos < positionEnd) {
                    holder.addFlags(2);
                    recycleCachedViewAt(i2);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void markKnownViewsInvalid() {
            int cachedCount = this.mCachedViews.size();
            for (int i = 0; i < cachedCount; i++) {
                ViewHolder holder = this.mCachedViews.get(i);
                if (holder != null) {
                    holder.addFlags(6);
                    holder.addChangePayload((Object) null);
                }
            }
            if (this.this$0.mAdapter == null || !this.this$0.mAdapter.hasStableIds()) {
                recycleAndClearCachedViews();
            }
        }

        /* access modifiers changed from: package-private */
        public void clearOldPositions() {
            int cachedCount = this.mCachedViews.size();
            for (int i = 0; i < cachedCount; i++) {
                this.mCachedViews.get(i).clearOldPosition();
            }
            int i2 = this.mAttachedScrap.size();
            for (int i3 = 0; i3 < i2; i3++) {
                this.mAttachedScrap.get(i3).clearOldPosition();
            }
            if (this.mChangedScrap != null) {
                int changedScrapCount = this.mChangedScrap.size();
                for (int i4 = 0; i4 < changedScrapCount; i4++) {
                    this.mChangedScrap.get(i4).clearOldPosition();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void markItemDecorInsetsDirty() {
            int cachedCount = this.mCachedViews.size();
            for (int i = 0; i < cachedCount; i++) {
                LayoutParams layoutParams = (LayoutParams) this.mCachedViews.get(i).itemView.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.mInsetsDirty = true;
                }
            }
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$ViewCacheExtension */
    public static abstract class ViewCacheExtension {
        @Nullable
        public abstract View getViewForPositionAndType(@NonNull Recycler recycler, int i, int i2);

        public ViewCacheExtension() {
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$Adapter */
    public static abstract class Adapter<VH extends ViewHolder> {
        private boolean mHasStableIds = false;
        private final AdapterDataObservable mObservable;

        public abstract int getItemCount();

        public abstract void onBindViewHolder(@NonNull VH vh, int i);

        @NonNull
        public abstract VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i);

        public Adapter() {
            AdapterDataObservable adapterDataObservable;
            new AdapterDataObservable();
            this.mObservable = adapterDataObservable;
        }

        public void onBindViewHolder(@NonNull VH holder, int position, @NonNull List<Object> list) {
            List<Object> list2 = list;
            onBindViewHolder(holder, position);
        }

        /* JADX INFO: finally extract failed */
        @NonNull
        public final VH createViewHolder(@NonNull ViewGroup viewGroup, int i) {
            Throwable th;
            ViewGroup parent = viewGroup;
            int viewType = i;
            try {
                TraceCompat.beginSection(RecyclerView.TRACE_CREATE_VIEW_TAG);
                Adapter<VH> holder = onCreateViewHolder(parent, viewType);
                if (holder.itemView.getParent() != null) {
                    Throwable th2 = th;
                    new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
                    throw th2;
                }
                holder.mItemViewType = viewType;
                TraceCompat.endSection();
                return holder;
            } catch (Throwable th3) {
                TraceCompat.endSection();
                throw th3;
            }
        }

        public final void bindViewHolder(@NonNull VH vh, int i) {
            VH holder = vh;
            int position = i;
            holder.mPosition = position;
            if (hasStableIds()) {
                holder.mItemId = getItemId(position);
            }
            holder.setFlags(1, ErrorMessages.ERROR_BLUETOOTH_UNSUPPORTED_ENCODING);
            TraceCompat.beginSection(RecyclerView.TRACE_BIND_VIEW_TAG);
            onBindViewHolder(holder, position, holder.getUnmodifiedPayloads());
            holder.clearPayload();
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                ((LayoutParams) layoutParams).mInsetsDirty = true;
            }
            TraceCompat.endSection();
        }

        public int getItemViewType(int i) {
            int i2 = i;
            return 0;
        }

        public void setHasStableIds(boolean z) {
            Throwable th;
            boolean hasStableIds = z;
            if (hasObservers()) {
                Throwable th2 = th;
                new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
                throw th2;
            }
            this.mHasStableIds = hasStableIds;
        }

        public long getItemId(int i) {
            int i2 = i;
            return -1;
        }

        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }

        public void onViewRecycled(@NonNull VH vh) {
        }

        public boolean onFailedToRecycleView(@NonNull VH vh) {
            VH vh2 = vh;
            return false;
        }

        public void onViewAttachedToWindow(@NonNull VH vh) {
        }

        public void onViewDetachedFromWindow(@NonNull VH vh) {
        }

        public final boolean hasObservers() {
            return this.mObservable.hasObservers();
        }

        public void registerAdapterDataObserver(@NonNull AdapterDataObserver observer) {
            this.mObservable.registerObserver(observer);
        }

        public void unregisterAdapterDataObserver(@NonNull AdapterDataObserver observer) {
            this.mObservable.unregisterObserver(observer);
        }

        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        }

        public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        }

        public final void notifyDataSetChanged() {
            this.mObservable.notifyChanged();
        }

        public final void notifyItemChanged(int position) {
            this.mObservable.notifyItemRangeChanged(position, 1);
        }

        public final void notifyItemChanged(int position, @Nullable Object payload) {
            this.mObservable.notifyItemRangeChanged(position, 1, payload);
        }

        public final void notifyItemRangeChanged(int positionStart, int itemCount) {
            this.mObservable.notifyItemRangeChanged(positionStart, itemCount);
        }

        public final void notifyItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            this.mObservable.notifyItemRangeChanged(positionStart, itemCount, payload);
        }

        public final void notifyItemInserted(int position) {
            this.mObservable.notifyItemRangeInserted(position, 1);
        }

        public final void notifyItemMoved(int fromPosition, int toPosition) {
            this.mObservable.notifyItemMoved(fromPosition, toPosition);
        }

        public final void notifyItemRangeInserted(int positionStart, int itemCount) {
            this.mObservable.notifyItemRangeInserted(positionStart, itemCount);
        }

        public final void notifyItemRemoved(int position) {
            this.mObservable.notifyItemRangeRemoved(position, 1);
        }

        public final void notifyItemRangeRemoved(int positionStart, int itemCount) {
            this.mObservable.notifyItemRangeRemoved(positionStart, itemCount);
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchChildDetached(View view) {
        View child = view;
        ViewHolder viewHolder = getChildViewHolderInt(child);
        onChildDetachedFromWindow(child);
        if (!(this.mAdapter == null || viewHolder == null)) {
            this.mAdapter.onViewDetachedFromWindow(viewHolder);
        }
        if (this.mOnChildAttachStateListeners != null) {
            for (int i = this.mOnChildAttachStateListeners.size() - 1; i >= 0; i--) {
                this.mOnChildAttachStateListeners.get(i).onChildViewDetachedFromWindow(child);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchChildAttached(View view) {
        View child = view;
        ViewHolder viewHolder = getChildViewHolderInt(child);
        onChildAttachedToWindow(child);
        if (!(this.mAdapter == null || viewHolder == null)) {
            this.mAdapter.onViewAttachedToWindow(viewHolder);
        }
        if (this.mOnChildAttachStateListeners != null) {
            for (int i = this.mOnChildAttachStateListeners.size() - 1; i >= 0; i--) {
                this.mOnChildAttachStateListeners.get(i).onChildViewAttachedToWindow(child);
            }
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$LayoutManager */
    public static abstract class LayoutManager {
        boolean mAutoMeasure = false;
        ChildHelper mChildHelper;
        private int mHeight;
        private int mHeightMode;
        ViewBoundsCheck mHorizontalBoundCheck;
        private final ViewBoundsCheck.Callback mHorizontalBoundCheckCallback;
        boolean mIsAttachedToWindow = false;
        private boolean mItemPrefetchEnabled = true;
        private boolean mMeasurementCacheEnabled = true;
        int mPrefetchMaxCountObserved;
        boolean mPrefetchMaxObservedInInitialPrefetch;
        RecyclerView mRecyclerView;
        boolean mRequestedSimpleAnimations = false;
        @Nullable
        SmoothScroller mSmoothScroller;
        ViewBoundsCheck mVerticalBoundCheck;
        private final ViewBoundsCheck.Callback mVerticalBoundCheckCallback;
        private int mWidth;
        private int mWidthMode;

        /* renamed from: android.support.v7.widget.RecyclerView$LayoutManager$LayoutPrefetchRegistry */
        public interface LayoutPrefetchRegistry {
            void addPosition(int i, int i2);
        }

        public abstract LayoutParams generateDefaultLayoutParams();

        public LayoutManager() {
            ViewBoundsCheck.Callback callback;
            ViewBoundsCheck.Callback callback2;
            ViewBoundsCheck viewBoundsCheck;
            ViewBoundsCheck viewBoundsCheck2;
            new ViewBoundsCheck.Callback(this) {
                final /* synthetic */ LayoutManager this$0;

                {
                    this.this$0 = this$0;
                }

                public int getChildCount() {
                    return this.this$0.getChildCount();
                }

                public View getParent() {
                    return this.this$0.mRecyclerView;
                }

                public View getChildAt(int index) {
                    return this.this$0.getChildAt(index);
                }

                public int getParentStart() {
                    return this.this$0.getPaddingLeft();
                }

                public int getParentEnd() {
                    return this.this$0.getWidth() - this.this$0.getPaddingRight();
                }

                public int getChildStart(View view) {
                    View view2 = view;
                    return this.this$0.getDecoratedLeft(view2) - ((LayoutParams) view2.getLayoutParams()).leftMargin;
                }

                public int getChildEnd(View view) {
                    View view2 = view;
                    return this.this$0.getDecoratedRight(view2) + ((LayoutParams) view2.getLayoutParams()).rightMargin;
                }
            };
            this.mHorizontalBoundCheckCallback = callback;
            new ViewBoundsCheck.Callback(this) {
                final /* synthetic */ LayoutManager this$0;

                {
                    this.this$0 = this$0;
                }

                public int getChildCount() {
                    return this.this$0.getChildCount();
                }

                public View getParent() {
                    return this.this$0.mRecyclerView;
                }

                public View getChildAt(int index) {
                    return this.this$0.getChildAt(index);
                }

                public int getParentStart() {
                    return this.this$0.getPaddingTop();
                }

                public int getParentEnd() {
                    return this.this$0.getHeight() - this.this$0.getPaddingBottom();
                }

                public int getChildStart(View view) {
                    View view2 = view;
                    return this.this$0.getDecoratedTop(view2) - ((LayoutParams) view2.getLayoutParams()).topMargin;
                }

                public int getChildEnd(View view) {
                    View view2 = view;
                    return this.this$0.getDecoratedBottom(view2) + ((LayoutParams) view2.getLayoutParams()).bottomMargin;
                }
            };
            this.mVerticalBoundCheckCallback = callback2;
            new ViewBoundsCheck(this.mHorizontalBoundCheckCallback);
            this.mHorizontalBoundCheck = viewBoundsCheck;
            new ViewBoundsCheck(this.mVerticalBoundCheckCallback);
            this.mVerticalBoundCheck = viewBoundsCheck2;
        }

        /* access modifiers changed from: package-private */
        public void setRecyclerView(RecyclerView recyclerView) {
            RecyclerView recyclerView2 = recyclerView;
            if (recyclerView2 == null) {
                this.mRecyclerView = null;
                this.mChildHelper = null;
                this.mWidth = 0;
                this.mHeight = 0;
            } else {
                this.mRecyclerView = recyclerView2;
                this.mChildHelper = recyclerView2.mChildHelper;
                this.mWidth = recyclerView2.getWidth();
                this.mHeight = recyclerView2.getHeight();
            }
            this.mWidthMode = Declaration.MODULE_REFERENCE;
            this.mHeightMode = Declaration.MODULE_REFERENCE;
        }

        /* access modifiers changed from: package-private */
        public void setMeasureSpecs(int i, int i2) {
            int wSpec = i;
            int hSpec = i2;
            this.mWidth = View.MeasureSpec.getSize(wSpec);
            this.mWidthMode = View.MeasureSpec.getMode(wSpec);
            if (this.mWidthMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mWidth = 0;
            }
            this.mHeight = View.MeasureSpec.getSize(hSpec);
            this.mHeightMode = View.MeasureSpec.getMode(hSpec);
            if (this.mHeightMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mHeight = 0;
            }
        }

        /* access modifiers changed from: package-private */
        public void setMeasuredDimensionFromChildren(int i, int i2) {
            int widthSpec = i;
            int heightSpec = i2;
            int count = getChildCount();
            if (count == 0) {
                this.mRecyclerView.defaultOnMeasure(widthSpec, heightSpec);
                return;
            }
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;
            for (int i3 = 0; i3 < count; i3++) {
                View child = getChildAt(i3);
                Rect bounds = this.mRecyclerView.mTempRect;
                getDecoratedBoundsWithMargins(child, bounds);
                if (bounds.left < minX) {
                    minX = bounds.left;
                }
                if (bounds.right > maxX) {
                    maxX = bounds.right;
                }
                if (bounds.top < minY) {
                    minY = bounds.top;
                }
                if (bounds.bottom > maxY) {
                    maxY = bounds.bottom;
                }
            }
            this.mRecyclerView.mTempRect.set(minX, minY, maxX, maxY);
            setMeasuredDimension(this.mRecyclerView.mTempRect, widthSpec, heightSpec);
        }

        public void setMeasuredDimension(Rect rect, int wSpec, int hSpec) {
            Rect childrenBounds = rect;
            int usedWidth = childrenBounds.width() + getPaddingLeft() + getPaddingRight();
            int usedHeight = childrenBounds.height() + getPaddingTop() + getPaddingBottom();
            setMeasuredDimension(chooseSize(wSpec, usedWidth, getMinimumWidth()), chooseSize(hSpec, usedHeight, getMinimumHeight()));
        }

        public void requestLayout() {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.requestLayout();
            }
        }

        public void assertInLayoutOrScroll(String str) {
            String message = str;
            if (this.mRecyclerView != null) {
                this.mRecyclerView.assertInLayoutOrScroll(message);
            }
        }

        public static int chooseSize(int i, int i2, int i3) {
            int spec = i;
            int desired = i2;
            int min = i3;
            int mode = View.MeasureSpec.getMode(spec);
            int size = View.MeasureSpec.getSize(spec);
            switch (mode) {
                case Integer.MIN_VALUE:
                    return Math.min(size, Math.max(desired, min));
                case Declaration.MODULE_REFERENCE /*1073741824*/:
                    return size;
                default:
                    return Math.max(desired, min);
            }
        }

        public void assertNotInLayoutOrScroll(String str) {
            String message = str;
            if (this.mRecyclerView != null) {
                this.mRecyclerView.assertNotInLayoutOrScroll(message);
            }
        }

        @Deprecated
        public void setAutoMeasureEnabled(boolean enabled) {
            boolean z = enabled;
            this.mAutoMeasure = z;
        }

        public boolean isAutoMeasureEnabled() {
            return this.mAutoMeasure;
        }

        public boolean supportsPredictiveItemAnimations() {
            return false;
        }

        public final void setItemPrefetchEnabled(boolean z) {
            boolean enabled = z;
            if (enabled != this.mItemPrefetchEnabled) {
                this.mItemPrefetchEnabled = enabled;
                this.mPrefetchMaxCountObserved = 0;
                if (this.mRecyclerView != null) {
                    this.mRecyclerView.mRecycler.updateViewCacheSize();
                }
            }
        }

        public final boolean isItemPrefetchEnabled() {
            return this.mItemPrefetchEnabled;
        }

        public void collectAdjacentPrefetchPositions(int dx, int dy, State state, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public void collectInitialPrefetchPositions(int adapterItemCount, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        /* access modifiers changed from: package-private */
        public void dispatchAttachedToWindow(RecyclerView view) {
            this.mIsAttachedToWindow = true;
            onAttachedToWindow(view);
        }

        /* access modifiers changed from: package-private */
        public void dispatchDetachedFromWindow(RecyclerView view, Recycler recycler) {
            this.mIsAttachedToWindow = false;
            onDetachedFromWindow(view, recycler);
        }

        public boolean isAttachedToWindow() {
            return this.mIsAttachedToWindow;
        }

        public void postOnAnimation(Runnable runnable) {
            Runnable action = runnable;
            if (this.mRecyclerView != null) {
                ViewCompat.postOnAnimation(this.mRecyclerView, action);
            }
        }

        public boolean removeCallbacks(Runnable runnable) {
            Runnable action = runnable;
            if (this.mRecyclerView != null) {
                return this.mRecyclerView.removeCallbacks(action);
            }
            return false;
        }

        @CallSuper
        public void onAttachedToWindow(RecyclerView view) {
        }

        @Deprecated
        public void onDetachedFromWindow(RecyclerView view) {
        }

        @CallSuper
        public void onDetachedFromWindow(RecyclerView view, Recycler recycler) {
            Recycler recycler2 = recycler;
            onDetachedFromWindow(view);
        }

        public boolean getClipToPadding() {
            return this.mRecyclerView != null && this.mRecyclerView.mClipToPadding;
        }

        public void onLayoutChildren(Recycler recycler, State state) {
            Recycler recycler2 = recycler;
            State state2 = state;
            int e = Log.e(RecyclerView.TAG, "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public void onLayoutCompleted(State state) {
        }

        public boolean checkLayoutParams(LayoutParams lp) {
            return lp != null;
        }

        public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
            LayoutParams layoutParams2;
            LayoutParams layoutParams3;
            LayoutParams layoutParams4;
            ViewGroup.LayoutParams lp = layoutParams;
            if (lp instanceof LayoutParams) {
                new LayoutParams((LayoutParams) lp);
                return layoutParams4;
            } else if (lp instanceof ViewGroup.MarginLayoutParams) {
                new LayoutParams((ViewGroup.MarginLayoutParams) lp);
                return layoutParams3;
            } else {
                new LayoutParams(lp);
                return layoutParams2;
            }
        }

        public LayoutParams generateLayoutParams(Context c, AttributeSet attrs) {
            LayoutParams layoutParams;
            new LayoutParams(c, attrs);
            return layoutParams;
        }

        public int scrollHorizontallyBy(int i, Recycler recycler, State state) {
            int i2 = i;
            Recycler recycler2 = recycler;
            State state2 = state;
            return 0;
        }

        public int scrollVerticallyBy(int i, Recycler recycler, State state) {
            int i2 = i;
            Recycler recycler2 = recycler;
            State state2 = state;
            return 0;
        }

        public boolean canScrollHorizontally() {
            return false;
        }

        public boolean canScrollVertically() {
            return false;
        }

        public void scrollToPosition(int position) {
        }

        public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
            RecyclerView recyclerView2 = recyclerView;
            State state2 = state;
            int i2 = i;
            int e = Log.e(RecyclerView.TAG, "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public void startSmoothScroll(SmoothScroller smoothScroller) {
            SmoothScroller smoothScroller2 = smoothScroller;
            if (!(this.mSmoothScroller == null || smoothScroller2 == this.mSmoothScroller || !this.mSmoothScroller.isRunning())) {
                this.mSmoothScroller.stop();
            }
            this.mSmoothScroller = smoothScroller2;
            this.mSmoothScroller.start(this.mRecyclerView, this);
        }

        public boolean isSmoothScrolling() {
            return this.mSmoothScroller != null && this.mSmoothScroller.isRunning();
        }

        public int getLayoutDirection() {
            return ViewCompat.getLayoutDirection(this.mRecyclerView);
        }

        public void endAnimation(View view) {
            View view2 = view;
            if (this.mRecyclerView.mItemAnimator != null) {
                this.mRecyclerView.mItemAnimator.endAnimation(RecyclerView.getChildViewHolderInt(view2));
            }
        }

        public void addDisappearingView(View child) {
            addDisappearingView(child, -1);
        }

        public void addDisappearingView(View child, int index) {
            addViewInt(child, index, true);
        }

        public void addView(View child) {
            addView(child, -1);
        }

        public void addView(View child, int index) {
            addViewInt(child, index, false);
        }

        private void addViewInt(View view, int i, boolean disappearing) {
            Throwable th;
            StringBuilder sb;
            View child = view;
            int index = i;
            ViewHolder holder = RecyclerView.getChildViewHolderInt(child);
            if (disappearing || holder.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(holder);
            } else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(holder);
            }
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (holder.wasReturnedFromScrap() || holder.isScrap()) {
                if (holder.isScrap()) {
                    holder.unScrap();
                } else {
                    holder.clearReturnedFromScrapFlag();
                }
                this.mChildHelper.attachViewToParent(child, index, child.getLayoutParams(), false);
            } else if (child.getParent() == this.mRecyclerView) {
                int currentIndex = this.mChildHelper.indexOfChild(child);
                if (index == -1) {
                    index = this.mChildHelper.getChildCount();
                }
                if (currentIndex == -1) {
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalStateException(sb.append("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:").append(this.mRecyclerView.indexOfChild(child)).append(this.mRecyclerView.exceptionLabel()).toString());
                    throw th2;
                } else if (currentIndex != index) {
                    this.mRecyclerView.mLayout.moveView(currentIndex, index);
                }
            } else {
                this.mChildHelper.addView(child, index, false);
                lp.mInsetsDirty = true;
                if (this.mSmoothScroller != null && this.mSmoothScroller.isRunning()) {
                    this.mSmoothScroller.onChildAttachedToWindow(child);
                }
            }
            if (lp.mPendingInvalidate) {
                holder.itemView.invalidate();
                lp.mPendingInvalidate = false;
            }
        }

        public void removeView(View child) {
            this.mChildHelper.removeView(child);
        }

        public void removeViewAt(int i) {
            int index = i;
            if (getChildAt(index) != null) {
                this.mChildHelper.removeViewAt(index);
            }
        }

        public void removeAllViews() {
            for (int i = getChildCount() - 1; i >= 0; i--) {
                this.mChildHelper.removeViewAt(i);
            }
        }

        public int getBaseline() {
            return -1;
        }

        public int getPosition(@NonNull View view) {
            return ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        }

        public int getItemViewType(@NonNull View view) {
            return RecyclerView.getChildViewHolderInt(view).getItemViewType();
        }

        @Nullable
        public View findContainingItemView(@NonNull View view) {
            View view2 = view;
            if (this.mRecyclerView == null) {
                return null;
            }
            View found = this.mRecyclerView.findContainingItemView(view2);
            if (found == null) {
                return null;
            }
            if (this.mChildHelper.isHidden(found)) {
                return null;
            }
            return found;
        }

        @Nullable
        public View findViewByPosition(int i) {
            int position = i;
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View child = getChildAt(i2);
                ViewHolder vh = RecyclerView.getChildViewHolderInt(child);
                if (vh != null && vh.getLayoutPosition() == position && !vh.shouldIgnore() && (this.mRecyclerView.mState.isPreLayout() || !vh.isRemoved())) {
                    return child;
                }
            }
            return null;
        }

        public void detachView(@NonNull View view) {
            View child = view;
            int ind = this.mChildHelper.indexOfChild(child);
            if (ind >= 0) {
                detachViewInternal(ind, child);
            }
        }

        public void detachViewAt(int i) {
            int index = i;
            detachViewInternal(index, getChildAt(index));
        }

        private void detachViewInternal(int index, @NonNull View view) {
            View view2 = view;
            this.mChildHelper.detachViewFromParent(index);
        }

        public void attachView(@NonNull View view, int i, LayoutParams layoutParams) {
            View child = view;
            int index = i;
            LayoutParams lp = layoutParams;
            ViewHolder vh = RecyclerView.getChildViewHolderInt(child);
            if (vh.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(vh);
            } else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(vh);
            }
            this.mChildHelper.attachViewToParent(child, index, lp, vh.isRemoved());
        }

        public void attachView(@NonNull View view, int index) {
            View child = view;
            attachView(child, index, (LayoutParams) child.getLayoutParams());
        }

        public void attachView(@NonNull View child) {
            attachView(child, -1);
        }

        public void removeDetachedView(@NonNull View child) {
            this.mRecyclerView.removeDetachedView(child, false);
        }

        public void moveView(int i, int i2) {
            Throwable th;
            StringBuilder sb;
            int fromIndex = i;
            int toIndex = i2;
            View view = getChildAt(fromIndex);
            if (view == null) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Cannot move a child from non-existing index:").append(fromIndex).append(this.mRecyclerView.toString()).toString());
                throw th2;
            }
            detachViewAt(fromIndex);
            attachView(view, toIndex);
        }

        public void detachAndScrapView(@NonNull View view, @NonNull Recycler recycler) {
            View child = view;
            scrapOrRecycleView(recycler, this.mChildHelper.indexOfChild(child), child);
        }

        public void detachAndScrapViewAt(int i, @NonNull Recycler recycler) {
            int index = i;
            scrapOrRecycleView(recycler, index, getChildAt(index));
        }

        public void removeAndRecycleView(@NonNull View view, @NonNull Recycler recycler) {
            View child = view;
            removeView(child);
            recycler.recycleView(child);
        }

        public void removeAndRecycleViewAt(int i, @NonNull Recycler recycler) {
            int index = i;
            View view = getChildAt(index);
            removeViewAt(index);
            recycler.recycleView(view);
        }

        public int getChildCount() {
            return this.mChildHelper != null ? this.mChildHelper.getChildCount() : 0;
        }

        @Nullable
        public View getChildAt(int index) {
            return this.mChildHelper != null ? this.mChildHelper.getChildAt(index) : null;
        }

        public int getWidthMode() {
            return this.mWidthMode;
        }

        public int getHeightMode() {
            return this.mHeightMode;
        }

        @C0015Px
        public int getWidth() {
            return this.mWidth;
        }

        @C0015Px
        public int getHeight() {
            return this.mHeight;
        }

        @C0015Px
        public int getPaddingLeft() {
            return this.mRecyclerView != null ? this.mRecyclerView.getPaddingLeft() : 0;
        }

        @C0015Px
        public int getPaddingTop() {
            return this.mRecyclerView != null ? this.mRecyclerView.getPaddingTop() : 0;
        }

        @C0015Px
        public int getPaddingRight() {
            return this.mRecyclerView != null ? this.mRecyclerView.getPaddingRight() : 0;
        }

        @C0015Px
        public int getPaddingBottom() {
            return this.mRecyclerView != null ? this.mRecyclerView.getPaddingBottom() : 0;
        }

        @C0015Px
        public int getPaddingStart() {
            return this.mRecyclerView != null ? ViewCompat.getPaddingStart(this.mRecyclerView) : 0;
        }

        @C0015Px
        public int getPaddingEnd() {
            return this.mRecyclerView != null ? ViewCompat.getPaddingEnd(this.mRecyclerView) : 0;
        }

        public boolean isFocused() {
            return this.mRecyclerView != null && this.mRecyclerView.isFocused();
        }

        public boolean hasFocus() {
            return this.mRecyclerView != null && this.mRecyclerView.hasFocus();
        }

        @Nullable
        public View getFocusedChild() {
            if (this.mRecyclerView == null) {
                return null;
            }
            View focused = this.mRecyclerView.getFocusedChild();
            if (focused == null || this.mChildHelper.isHidden(focused)) {
                return null;
            }
            return focused;
        }

        public int getItemCount() {
            Adapter a = this.mRecyclerView != null ? this.mRecyclerView.getAdapter() : null;
            return a != null ? a.getItemCount() : 0;
        }

        public void offsetChildrenHorizontal(@C0015Px int i) {
            int dx = i;
            if (this.mRecyclerView != null) {
                this.mRecyclerView.offsetChildrenHorizontal(dx);
            }
        }

        public void offsetChildrenVertical(@C0015Px int i) {
            int dy = i;
            if (this.mRecyclerView != null) {
                this.mRecyclerView.offsetChildrenVertical(dy);
            }
        }

        public void ignoreView(@NonNull View view) {
            Throwable th;
            StringBuilder sb;
            View view2 = view;
            if (view2.getParent() != this.mRecyclerView || this.mRecyclerView.indexOfChild(view2) == -1) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("View should be fully attached to be ignored").append(this.mRecyclerView.exceptionLabel()).toString());
                throw th2;
            }
            ViewHolder vh = RecyclerView.getChildViewHolderInt(view2);
            vh.addFlags(128);
            this.mRecyclerView.mViewInfoStore.removeViewHolder(vh);
        }

        public void stopIgnoringView(@NonNull View view) {
            ViewHolder vh = RecyclerView.getChildViewHolderInt(view);
            vh.stopIgnoring();
            vh.resetInternal();
            vh.addFlags(4);
        }

        public void detachAndScrapAttachedViews(@NonNull Recycler recycler) {
            Recycler recycler2 = recycler;
            for (int i = getChildCount() - 1; i >= 0; i--) {
                scrapOrRecycleView(recycler2, i, getChildAt(i));
            }
        }

        private void scrapOrRecycleView(Recycler recycler, int i, View view) {
            Recycler recycler2 = recycler;
            int index = i;
            View view2 = view;
            ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(view2);
            if (!viewHolder.shouldIgnore()) {
                if (!viewHolder.isInvalid() || viewHolder.isRemoved() || this.mRecyclerView.mAdapter.hasStableIds()) {
                    detachViewAt(index);
                    recycler2.scrapView(view2);
                    this.mRecyclerView.mViewInfoStore.onViewDetached(viewHolder);
                    return;
                }
                removeViewAt(index);
                recycler2.recycleViewHolderInternal(viewHolder);
            }
        }

        /* access modifiers changed from: package-private */
        public void removeAndRecycleScrapInt(Recycler recycler) {
            Recycler recycler2 = recycler;
            int scrapCount = recycler2.getScrapCount();
            for (int i = scrapCount - 1; i >= 0; i--) {
                View scrap = recycler2.getScrapViewAt(i);
                ViewHolder vh = RecyclerView.getChildViewHolderInt(scrap);
                if (!vh.shouldIgnore()) {
                    vh.setIsRecyclable(false);
                    if (vh.isTmpDetached()) {
                        this.mRecyclerView.removeDetachedView(scrap, false);
                    }
                    if (this.mRecyclerView.mItemAnimator != null) {
                        this.mRecyclerView.mItemAnimator.endAnimation(vh);
                    }
                    vh.setIsRecyclable(true);
                    recycler2.quickRecycleScrapView(scrap);
                }
            }
            recycler2.clearScrap();
            if (scrapCount > 0) {
                this.mRecyclerView.invalidate();
            }
        }

        public void measureChild(@NonNull View view, int widthUsed, int heightUsed) {
            View child = view;
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            Rect insets = this.mRecyclerView.getItemDecorInsetsForChild(child);
            int widthUsed2 = widthUsed + insets.left + insets.right;
            int heightUsed2 = heightUsed + insets.top + insets.bottom;
            int widthSpec = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + widthUsed2, lp.width, canScrollHorizontally());
            int heightSpec = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + heightUsed2, lp.height, canScrollVertically());
            if (shouldMeasureChild(child, widthSpec, heightSpec, lp)) {
                child.measure(widthSpec, heightSpec);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean shouldReMeasureChild(View view, int widthSpec, int heightSpec, LayoutParams layoutParams) {
            View child = view;
            LayoutParams lp = layoutParams;
            return !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(child.getMeasuredWidth(), widthSpec, lp.width) || !isMeasurementUpToDate(child.getMeasuredHeight(), heightSpec, lp.height);
        }

        /* access modifiers changed from: package-private */
        public boolean shouldMeasureChild(View view, int widthSpec, int heightSpec, LayoutParams layoutParams) {
            View child = view;
            LayoutParams lp = layoutParams;
            return child.isLayoutRequested() || !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(child.getWidth(), widthSpec, lp.width) || !isMeasurementUpToDate(child.getHeight(), heightSpec, lp.height);
        }

        public boolean isMeasurementCacheEnabled() {
            return this.mMeasurementCacheEnabled;
        }

        public void setMeasurementCacheEnabled(boolean measurementCacheEnabled) {
            boolean z = measurementCacheEnabled;
            this.mMeasurementCacheEnabled = z;
        }

        private static boolean isMeasurementUpToDate(int i, int i2, int i3) {
            int childSize = i;
            int spec = i2;
            int dimension = i3;
            int specMode = View.MeasureSpec.getMode(spec);
            int specSize = View.MeasureSpec.getSize(spec);
            if (dimension > 0 && childSize != dimension) {
                return false;
            }
            switch (specMode) {
                case Integer.MIN_VALUE:
                    return specSize >= childSize;
                case 0:
                    return true;
                case Declaration.MODULE_REFERENCE /*1073741824*/:
                    return specSize == childSize;
                default:
                    return false;
            }
        }

        public void measureChildWithMargins(@NonNull View view, int widthUsed, int heightUsed) {
            View child = view;
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            Rect insets = this.mRecyclerView.getItemDecorInsetsForChild(child);
            int widthUsed2 = widthUsed + insets.left + insets.right;
            int heightUsed2 = heightUsed + insets.top + insets.bottom;
            int widthSpec = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin + widthUsed2, lp.width, canScrollHorizontally());
            int heightSpec = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin + heightUsed2, lp.height, canScrollVertically());
            if (shouldMeasureChild(child, widthSpec, heightSpec, lp)) {
                child.measure(widthSpec, heightSpec);
            }
        }

        @Deprecated
        public static int getChildMeasureSpec(int parentSize, int padding, int i, boolean canScroll) {
            int childDimension = i;
            int size = Math.max(0, parentSize - padding);
            int resultSize = 0;
            int resultMode = 0;
            if (canScroll) {
                if (childDimension >= 0) {
                    resultSize = childDimension;
                    resultMode = 1073741824;
                } else {
                    resultSize = 0;
                    resultMode = 0;
                }
            } else if (childDimension >= 0) {
                resultSize = childDimension;
                resultMode = 1073741824;
            } else if (childDimension == -1) {
                resultSize = size;
                resultMode = 1073741824;
            } else if (childDimension == -2) {
                resultSize = size;
                resultMode = Integer.MIN_VALUE;
            }
            return View.MeasureSpec.makeMeasureSpec(resultSize, resultMode);
        }

        public static int getChildMeasureSpec(int parentSize, int i, int padding, int i2, boolean canScroll) {
            int parentMode = i;
            int childDimension = i2;
            int size = Math.max(0, parentSize - padding);
            int resultSize = 0;
            int resultMode = 0;
            if (canScroll) {
                if (childDimension >= 0) {
                    resultSize = childDimension;
                    resultMode = 1073741824;
                } else if (childDimension == -1) {
                    switch (parentMode) {
                        case Integer.MIN_VALUE:
                        case Declaration.MODULE_REFERENCE /*1073741824*/:
                            resultSize = size;
                            resultMode = parentMode;
                            break;
                        case 0:
                            resultSize = 0;
                            resultMode = 0;
                            break;
                    }
                } else if (childDimension == -2) {
                    resultSize = 0;
                    resultMode = 0;
                }
            } else if (childDimension >= 0) {
                resultSize = childDimension;
                resultMode = 1073741824;
            } else if (childDimension == -1) {
                resultSize = size;
                resultMode = parentMode;
            } else if (childDimension == -2) {
                resultSize = size;
                resultMode = (parentMode == Integer.MIN_VALUE || parentMode == 1073741824) ? Integer.MIN_VALUE : 0;
            }
            return View.MeasureSpec.makeMeasureSpec(resultSize, resultMode);
        }

        public int getDecoratedMeasuredWidth(@NonNull View view) {
            View child = view;
            Rect insets = ((LayoutParams) child.getLayoutParams()).mDecorInsets;
            return child.getMeasuredWidth() + insets.left + insets.right;
        }

        public int getDecoratedMeasuredHeight(@NonNull View view) {
            View child = view;
            Rect insets = ((LayoutParams) child.getLayoutParams()).mDecorInsets;
            return child.getMeasuredHeight() + insets.top + insets.bottom;
        }

        public void layoutDecorated(@NonNull View view, int left, int top, int right, int bottom) {
            View child = view;
            Rect insets = ((LayoutParams) child.getLayoutParams()).mDecorInsets;
            child.layout(left + insets.left, top + insets.top, right - insets.right, bottom - insets.bottom);
        }

        public void layoutDecoratedWithMargins(@NonNull View view, int left, int top, int right, int bottom) {
            View child = view;
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            Rect insets = lp.mDecorInsets;
            child.layout(left + insets.left + lp.leftMargin, top + insets.top + lp.topMargin, (right - insets.right) - lp.rightMargin, (bottom - insets.bottom) - lp.bottomMargin);
        }

        public void getTransformedBoundingBox(@NonNull View view, boolean includeDecorInsets, @NonNull Rect rect) {
            Matrix childMatrix;
            View child = view;
            Rect out = rect;
            if (includeDecorInsets) {
                Rect insets = ((LayoutParams) child.getLayoutParams()).mDecorInsets;
                out.set(-insets.left, -insets.top, child.getWidth() + insets.right, child.getHeight() + insets.bottom);
            } else {
                out.set(0, 0, child.getWidth(), child.getHeight());
            }
            if (!(this.mRecyclerView == null || (childMatrix = child.getMatrix()) == null || childMatrix.isIdentity())) {
                RectF tempRectF = this.mRecyclerView.mTempRectF;
                tempRectF.set(out);
                boolean mapRect = childMatrix.mapRect(tempRectF);
                out.set((int) Math.floor((double) tempRectF.left), (int) Math.floor((double) tempRectF.top), (int) Math.ceil((double) tempRectF.right), (int) Math.ceil((double) tempRectF.bottom));
            }
            out.offset(child.getLeft(), child.getTop());
        }

        public void getDecoratedBoundsWithMargins(@NonNull View view, @NonNull Rect outBounds) {
            RecyclerView.getDecoratedBoundsWithMarginsInt(view, outBounds);
        }

        public int getDecoratedLeft(@NonNull View view) {
            View child = view;
            return child.getLeft() - getLeftDecorationWidth(child);
        }

        public int getDecoratedTop(@NonNull View view) {
            View child = view;
            return child.getTop() - getTopDecorationHeight(child);
        }

        public int getDecoratedRight(@NonNull View view) {
            View child = view;
            return child.getRight() + getRightDecorationWidth(child);
        }

        public int getDecoratedBottom(@NonNull View view) {
            View child = view;
            return child.getBottom() + getBottomDecorationHeight(child);
        }

        public void calculateItemDecorationsForChild(@NonNull View view, @NonNull Rect rect) {
            View child = view;
            Rect outRect = rect;
            if (this.mRecyclerView == null) {
                outRect.set(0, 0, 0, 0);
                return;
            }
            outRect.set(this.mRecyclerView.getItemDecorInsetsForChild(child));
        }

        public int getTopDecorationHeight(@NonNull View child) {
            return ((LayoutParams) child.getLayoutParams()).mDecorInsets.top;
        }

        public int getBottomDecorationHeight(@NonNull View child) {
            return ((LayoutParams) child.getLayoutParams()).mDecorInsets.bottom;
        }

        public int getLeftDecorationWidth(@NonNull View child) {
            return ((LayoutParams) child.getLayoutParams()).mDecorInsets.left;
        }

        public int getRightDecorationWidth(@NonNull View child) {
            return ((LayoutParams) child.getLayoutParams()).mDecorInsets.right;
        }

        @Nullable
        public View onFocusSearchFailed(@NonNull View view, int i, @NonNull Recycler recycler, @NonNull State state) {
            View view2 = view;
            int i2 = i;
            Recycler recycler2 = recycler;
            State state2 = state;
            return null;
        }

        @Nullable
        public View onInterceptFocusSearch(@NonNull View view, int i) {
            View view2 = view;
            int i2 = i;
            return null;
        }

        private int[] getChildRectangleOnScreenScrollAmount(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            int min;
            int dx;
            int dy;
            int max;
            RecyclerView recyclerView2 = recyclerView;
            View child = view;
            Rect rect2 = rect;
            boolean z2 = z;
            int[] out = new int[2];
            int parentLeft = getPaddingLeft();
            int parentTop = getPaddingTop();
            int parentRight = getWidth() - getPaddingRight();
            int parentBottom = getHeight() - getPaddingBottom();
            int childLeft = (child.getLeft() + rect2.left) - child.getScrollX();
            int childTop = (child.getTop() + rect2.top) - child.getScrollY();
            int childRight = childLeft + rect2.width();
            int childBottom = childTop + rect2.height();
            int offScreenLeft = Math.min(0, childLeft - parentLeft);
            int offScreenTop = Math.min(0, childTop - parentTop);
            int offScreenRight = Math.max(0, childRight - parentRight);
            int offScreenBottom = Math.max(0, childBottom - parentBottom);
            if (getLayoutDirection() == 1) {
                if (offScreenRight != 0) {
                    max = offScreenRight;
                } else {
                    max = Math.max(offScreenLeft, childRight - parentRight);
                }
                dx = max;
            } else {
                if (offScreenLeft != 0) {
                    min = offScreenLeft;
                } else {
                    min = Math.min(childLeft - parentLeft, offScreenRight);
                }
                dx = min;
            }
            if (offScreenTop != 0) {
                dy = offScreenTop;
            } else {
                dy = Math.min(childTop - parentTop, offScreenBottom);
            }
            out[0] = dx;
            out[1] = dy;
            return out;
        }

        public boolean requestChildRectangleOnScreen(@NonNull RecyclerView parent, @NonNull View child, @NonNull Rect rect, boolean immediate) {
            return requestChildRectangleOnScreen(parent, child, rect, immediate, false);
        }

        public boolean requestChildRectangleOnScreen(@NonNull RecyclerView recyclerView, @NonNull View child, @NonNull Rect rect, boolean z, boolean focusedChildVisible) {
            RecyclerView parent = recyclerView;
            boolean immediate = z;
            int[] scrollAmount = getChildRectangleOnScreenScrollAmount(parent, child, rect, immediate);
            int dx = scrollAmount[0];
            int dy = scrollAmount[1];
            if ((focusedChildVisible && !isFocusedChildVisibleAfterScrolling(parent, dx, dy)) || (dx == 0 && dy == 0)) {
                return false;
            }
            if (immediate) {
                parent.scrollBy(dx, dy);
            } else {
                parent.smoothScrollBy(dx, dy);
            }
            return true;
        }

        public boolean isViewPartiallyVisible(@NonNull View view, boolean z, boolean z2) {
            View child = view;
            boolean completelyVisible = z;
            boolean z3 = z2;
            boolean isViewFullyVisible = this.mHorizontalBoundCheck.isViewWithinBoundFlags(child, 24579) && this.mVerticalBoundCheck.isViewWithinBoundFlags(child, 24579);
            if (completelyVisible) {
                return isViewFullyVisible;
            }
            return !isViewFullyVisible;
        }

        private boolean isFocusedChildVisibleAfterScrolling(RecyclerView parent, int i, int i2) {
            int dx = i;
            int dy = i2;
            View focusedChild = parent.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int parentLeft = getPaddingLeft();
            int parentTop = getPaddingTop();
            int parentRight = getWidth() - getPaddingRight();
            int parentBottom = getHeight() - getPaddingBottom();
            Rect bounds = this.mRecyclerView.mTempRect;
            getDecoratedBoundsWithMargins(focusedChild, bounds);
            if (bounds.left - dx >= parentRight || bounds.right - dx <= parentLeft || bounds.top - dy >= parentBottom || bounds.bottom - dy <= parentTop) {
                return false;
            }
            return true;
        }

        @Deprecated
        public boolean onRequestChildFocus(@NonNull RecyclerView parent, @NonNull View view, @Nullable View view2) {
            View view3 = view;
            View view4 = view2;
            return isSmoothScrolling() || parent.isComputingLayout();
        }

        public boolean onRequestChildFocus(@NonNull RecyclerView parent, @NonNull State state, @NonNull View child, @Nullable View focused) {
            State state2 = state;
            return onRequestChildFocus(parent, child, focused);
        }

        public void onAdapterChanged(@Nullable Adapter oldAdapter, @Nullable Adapter newAdapter) {
        }

        public boolean onAddFocusables(@NonNull RecyclerView recyclerView, @NonNull ArrayList<View> arrayList, int i, int i2) {
            RecyclerView recyclerView2 = recyclerView;
            ArrayList<View> arrayList2 = arrayList;
            int i3 = i;
            int i4 = i2;
            return false;
        }

        public void onItemsChanged(@NonNull RecyclerView recyclerView) {
        }

        public void onItemsAdded(@NonNull RecyclerView recyclerView, int positionStart, int itemCount) {
        }

        public void onItemsRemoved(@NonNull RecyclerView recyclerView, int positionStart, int itemCount) {
        }

        public void onItemsUpdated(@NonNull RecyclerView recyclerView, int positionStart, int itemCount) {
        }

        public void onItemsUpdated(@NonNull RecyclerView recyclerView, int positionStart, int itemCount, @Nullable Object obj) {
            Object obj2 = obj;
            onItemsUpdated(recyclerView, positionStart, itemCount);
        }

        public void onItemsMoved(@NonNull RecyclerView recyclerView, int from, int to, int itemCount) {
        }

        public int computeHorizontalScrollExtent(@NonNull State state) {
            State state2 = state;
            return 0;
        }

        public int computeHorizontalScrollOffset(@NonNull State state) {
            State state2 = state;
            return 0;
        }

        public int computeHorizontalScrollRange(@NonNull State state) {
            State state2 = state;
            return 0;
        }

        public int computeVerticalScrollExtent(@NonNull State state) {
            State state2 = state;
            return 0;
        }

        public int computeVerticalScrollOffset(@NonNull State state) {
            State state2 = state;
            return 0;
        }

        public int computeVerticalScrollRange(@NonNull State state) {
            State state2 = state;
            return 0;
        }

        public void onMeasure(@NonNull Recycler recycler, @NonNull State state, int widthSpec, int heightSpec) {
            Recycler recycler2 = recycler;
            State state2 = state;
            this.mRecyclerView.defaultOnMeasure(widthSpec, heightSpec);
        }

        public void setMeasuredDimension(int widthSize, int heightSize) {
            this.mRecyclerView.setMeasuredDimension(widthSize, heightSize);
        }

        @C0015Px
        public int getMinimumWidth() {
            return ViewCompat.getMinimumWidth(this.mRecyclerView);
        }

        @C0015Px
        public int getMinimumHeight() {
            return ViewCompat.getMinimumHeight(this.mRecyclerView);
        }

        @Nullable
        public Parcelable onSaveInstanceState() {
            return null;
        }

        public void onRestoreInstanceState(Parcelable state) {
        }

        /* access modifiers changed from: package-private */
        public void stopSmoothScroller() {
            if (this.mSmoothScroller != null) {
                this.mSmoothScroller.stop();
            }
        }

        /* access modifiers changed from: package-private */
        public void onSmoothScrollerStopped(SmoothScroller smoothScroller) {
            if (this.mSmoothScroller == smoothScroller) {
                this.mSmoothScroller = null;
            }
        }

        public void onScrollStateChanged(int state) {
        }

        public void removeAndRecycleAllViews(@NonNull Recycler recycler) {
            Recycler recycler2 = recycler;
            for (int i = getChildCount() - 1; i >= 0; i--) {
                if (!RecyclerView.getChildViewHolderInt(getChildAt(i)).shouldIgnore()) {
                    removeAndRecycleViewAt(i, recycler2);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat info) {
            onInitializeAccessibilityNodeInfo(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, info);
        }

        public void onInitializeAccessibilityNodeInfo(@NonNull Recycler recycler, @NonNull State state, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            Recycler recycler2 = recycler;
            State state2 = state;
            AccessibilityNodeInfoCompat info = accessibilityNodeInfoCompat;
            if (this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1)) {
                info.addAction(8192);
                info.setScrollable(true);
            }
            if (this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollHorizontally(1)) {
                info.addAction(4096);
                info.setScrollable(true);
            }
            info.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(recycler2, state2), getColumnCountForAccessibility(recycler2, state2), isLayoutHierarchical(recycler2, state2), getSelectionModeForAccessibility(recycler2, state2)));
        }

        public void onInitializeAccessibilityEvent(@NonNull AccessibilityEvent event) {
            onInitializeAccessibilityEvent(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, event);
        }

        public void onInitializeAccessibilityEvent(@NonNull Recycler recycler, @NonNull State state, @NonNull AccessibilityEvent accessibilityEvent) {
            Recycler recycler2 = recycler;
            State state2 = state;
            AccessibilityEvent event = accessibilityEvent;
            if (this.mRecyclerView != null && event != null) {
                event.setScrollable(this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1) || this.mRecyclerView.canScrollHorizontally(1));
                if (this.mRecyclerView.mAdapter != null) {
                    event.setItemCount(this.mRecyclerView.mAdapter.getItemCount());
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void onInitializeAccessibilityNodeInfoForItem(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            View host = view;
            AccessibilityNodeInfoCompat info = accessibilityNodeInfoCompat;
            ViewHolder vh = RecyclerView.getChildViewHolderInt(host);
            if (vh != null && !vh.isRemoved() && !this.mChildHelper.isHidden(vh.itemView)) {
                onInitializeAccessibilityNodeInfoForItem(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, host, info);
            }
        }

        public void onInitializeAccessibilityNodeInfoForItem(@NonNull Recycler recycler, @NonNull State state, @NonNull View view, @NonNull AccessibilityNodeInfoCompat info) {
            Recycler recycler2 = recycler;
            State state2 = state;
            View host = view;
            info.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(canScrollVertically() ? getPosition(host) : 0, 1, canScrollHorizontally() ? getPosition(host) : 0, 1, false, false));
        }

        public void requestSimpleAnimationsInNextLayout() {
            this.mRequestedSimpleAnimations = true;
        }

        public int getSelectionModeForAccessibility(@NonNull Recycler recycler, @NonNull State state) {
            Recycler recycler2 = recycler;
            State state2 = state;
            return 0;
        }

        public int getRowCountForAccessibility(@NonNull Recycler recycler, @NonNull State state) {
            Recycler recycler2 = recycler;
            State state2 = state;
            if (this.mRecyclerView == null || this.mRecyclerView.mAdapter == null) {
                return 1;
            }
            return canScrollVertically() ? this.mRecyclerView.mAdapter.getItemCount() : 1;
        }

        public int getColumnCountForAccessibility(@NonNull Recycler recycler, @NonNull State state) {
            Recycler recycler2 = recycler;
            State state2 = state;
            if (this.mRecyclerView == null || this.mRecyclerView.mAdapter == null) {
                return 1;
            }
            return canScrollHorizontally() ? this.mRecyclerView.mAdapter.getItemCount() : 1;
        }

        public boolean isLayoutHierarchical(@NonNull Recycler recycler, @NonNull State state) {
            Recycler recycler2 = recycler;
            State state2 = state;
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean performAccessibilityAction(int action, @Nullable Bundle args) {
            return performAccessibilityAction(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, action, args);
        }

        public boolean performAccessibilityAction(@NonNull Recycler recycler, @NonNull State state, int i, @Nullable Bundle bundle) {
            Recycler recycler2 = recycler;
            State state2 = state;
            int action = i;
            Bundle bundle2 = bundle;
            if (this.mRecyclerView == null) {
                return false;
            }
            int vScroll = 0;
            int hScroll = 0;
            switch (action) {
                case 4096:
                    if (this.mRecyclerView.canScrollVertically(1)) {
                        vScroll = (getHeight() - getPaddingTop()) - getPaddingBottom();
                    }
                    if (this.mRecyclerView.canScrollHorizontally(1)) {
                        hScroll = (getWidth() - getPaddingLeft()) - getPaddingRight();
                        break;
                    }
                    break;
                case 8192:
                    if (this.mRecyclerView.canScrollVertically(-1)) {
                        vScroll = -((getHeight() - getPaddingTop()) - getPaddingBottom());
                    }
                    if (this.mRecyclerView.canScrollHorizontally(-1)) {
                        hScroll = -((getWidth() - getPaddingLeft()) - getPaddingRight());
                        break;
                    }
                    break;
            }
            if (vScroll == 0 && hScroll == 0) {
                return false;
            }
            this.mRecyclerView.smoothScrollBy(hScroll, vScroll);
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean performAccessibilityActionForItem(@NonNull View view, int action, @Nullable Bundle args) {
            return performAccessibilityActionForItem(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, view, action, args);
        }

        public boolean performAccessibilityActionForItem(@NonNull Recycler recycler, @NonNull State state, @NonNull View view, int i, @Nullable Bundle bundle) {
            Recycler recycler2 = recycler;
            State state2 = state;
            View view2 = view;
            int i2 = i;
            Bundle bundle2 = bundle;
            return false;
        }

        public static Properties getProperties(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            Properties properties;
            new Properties();
            Properties properties2 = properties;
            TypedArray a = context.obtainStyledAttributes(attrs, C0433R.styleable.RecyclerView, defStyleAttr, defStyleRes);
            properties2.orientation = a.getInt(C0433R.styleable.RecyclerView_android_orientation, 1);
            properties2.spanCount = a.getInt(C0433R.styleable.RecyclerView_spanCount, 1);
            properties2.reverseLayout = a.getBoolean(C0433R.styleable.RecyclerView_reverseLayout, false);
            properties2.stackFromEnd = a.getBoolean(C0433R.styleable.RecyclerView_stackFromEnd, false);
            a.recycle();
            return properties2;
        }

        /* access modifiers changed from: package-private */
        public void setExactMeasureSpecsFrom(RecyclerView recyclerView) {
            RecyclerView recyclerView2 = recyclerView;
            setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(recyclerView2.getWidth(), Declaration.MODULE_REFERENCE), View.MeasureSpec.makeMeasureSpec(recyclerView2.getHeight(), Declaration.MODULE_REFERENCE));
        }

        /* access modifiers changed from: package-private */
        public boolean shouldMeasureTwice() {
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean hasFlexibleChildInBothOrientations() {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                ViewGroup.LayoutParams lp = getChildAt(i).getLayoutParams();
                if (lp.width < 0 && lp.height < 0) {
                    return true;
                }
            }
            return false;
        }

        /* renamed from: android.support.v7.widget.RecyclerView$LayoutManager$Properties */
        public static class Properties {
            public int orientation;
            public boolean reverseLayout;
            public int spanCount;
            public boolean stackFromEnd;

            public Properties() {
            }
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$ItemDecoration */
    public static abstract class ItemDecoration {
        public ItemDecoration() {
        }

        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull State state) {
            State state2 = state;
            onDraw(c, parent);
        }

        @Deprecated
        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent) {
        }

        public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull State state) {
            State state2 = state;
            onDrawOver(c, parent);
        }

        @Deprecated
        public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent) {
        }

        @Deprecated
        public void getItemOffsets(@NonNull Rect outRect, int i, @NonNull RecyclerView recyclerView) {
            int i2 = i;
            RecyclerView recyclerView2 = recyclerView;
            outRect.set(0, 0, 0, 0);
        }

        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull State state) {
            State state2 = state;
            getItemOffsets(outRect, ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition(), parent);
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$SimpleOnItemTouchListener */
    public static class SimpleOnItemTouchListener implements OnItemTouchListener {
        public SimpleOnItemTouchListener() {
        }

        public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            RecyclerView recyclerView2 = recyclerView;
            MotionEvent motionEvent2 = motionEvent;
            return false;
        }

        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        }

        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$OnScrollListener */
    public static abstract class OnScrollListener {
        public OnScrollListener() {
        }

        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        }

        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$ViewHolder */
    public static abstract class ViewHolder {
        static final int FLAG_ADAPTER_FULLUPDATE = 1024;
        static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
        static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
        static final int FLAG_BOUND = 1;
        static final int FLAG_IGNORE = 128;
        static final int FLAG_INVALID = 4;
        static final int FLAG_MOVED = 2048;
        static final int FLAG_NOT_RECYCLABLE = 16;
        static final int FLAG_REMOVED = 8;
        static final int FLAG_RETURNED_FROM_SCRAP = 32;
        static final int FLAG_SET_A11Y_ITEM_DELEGATE = 16384;
        static final int FLAG_TMP_DETACHED = 256;
        static final int FLAG_UPDATE = 2;
        private static final List<Object> FULLUPDATE_PAYLOADS = Collections.emptyList();
        static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
        @NonNull
        public final View itemView;
        int mFlags;
        boolean mInChangeScrap = false;
        private int mIsRecyclableCount = 0;
        long mItemId = -1;
        int mItemViewType = -1;
        WeakReference<RecyclerView> mNestedRecyclerView;
        int mOldPosition = -1;
        RecyclerView mOwnerRecyclerView;
        List<Object> mPayloads = null;
        @VisibleForTesting
        int mPendingAccessibilityState = -1;
        int mPosition = -1;
        int mPreLayoutPosition = -1;
        Recycler mScrapContainer = null;
        ViewHolder mShadowedHolder = null;
        ViewHolder mShadowingHolder = null;
        List<Object> mUnmodifiedPayloads = null;
        private int mWasImportantForAccessibilityBeforeHidden = 0;

        public ViewHolder(@NonNull View view) {
            Throwable th;
            View itemView2 = view;
            if (itemView2 == null) {
                Throwable th2 = th;
                new IllegalArgumentException("itemView may not be null");
                throw th2;
            }
            this.itemView = itemView2;
        }

        /* access modifiers changed from: package-private */
        public void flagRemovedAndOffsetPosition(int mNewPosition, int offset, boolean applyToPreLayout) {
            addFlags(8);
            offsetPosition(offset, applyToPreLayout);
            this.mPosition = mNewPosition;
        }

        /* access modifiers changed from: package-private */
        public void offsetPosition(int i, boolean z) {
            int offset = i;
            boolean applyToPreLayout = z;
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            if (this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }
            if (applyToPreLayout) {
                this.mPreLayoutPosition += offset;
            }
            this.mPosition += offset;
            if (this.itemView.getLayoutParams() != null) {
                ((LayoutParams) this.itemView.getLayoutParams()).mInsetsDirty = true;
            }
        }

        /* access modifiers changed from: package-private */
        public void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        /* access modifiers changed from: package-private */
        public void saveOldPosition() {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean shouldIgnore() {
            return (this.mFlags & 128) != 0;
        }

        @Deprecated
        public final int getPosition() {
            return this.mPreLayoutPosition == -1 ? this.mPosition : this.mPreLayoutPosition;
        }

        public final int getLayoutPosition() {
            return this.mPreLayoutPosition == -1 ? this.mPosition : this.mPreLayoutPosition;
        }

        public final int getAdapterPosition() {
            if (this.mOwnerRecyclerView == null) {
                return -1;
            }
            return this.mOwnerRecyclerView.getAdapterPositionFor(this);
        }

        public final int getOldPosition() {
            return this.mOldPosition;
        }

        public final long getItemId() {
            return this.mItemId;
        }

        public final int getItemViewType() {
            return this.mItemViewType;
        }

        /* access modifiers changed from: package-private */
        public boolean isScrap() {
            return this.mScrapContainer != null;
        }

        /* access modifiers changed from: package-private */
        public void unScrap() {
            this.mScrapContainer.unscrapView(this);
        }

        /* access modifiers changed from: package-private */
        public boolean wasReturnedFromScrap() {
            return (this.mFlags & 32) != 0;
        }

        /* access modifiers changed from: package-private */
        public void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        /* access modifiers changed from: package-private */
        public void clearTmpDetachFlag() {
            this.mFlags &= -257;
        }

        /* access modifiers changed from: package-private */
        public void stopIgnoring() {
            this.mFlags &= -129;
        }

        /* access modifiers changed from: package-private */
        public void setScrapContainer(Recycler recycler, boolean isChangeScrap) {
            this.mScrapContainer = recycler;
            this.mInChangeScrap = isChangeScrap;
        }

        /* access modifiers changed from: package-private */
        public boolean isInvalid() {
            return (this.mFlags & 4) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean needsUpdate() {
            return (this.mFlags & 2) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isBound() {
            return (this.mFlags & 1) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isRemoved() {
            return (this.mFlags & 8) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean hasAnyOfTheFlags(int flags) {
            return (this.mFlags & flags) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isTmpDetached() {
            return (this.mFlags & 256) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isAdapterPositionUnknown() {
            return (this.mFlags & 512) != 0 || isInvalid();
        }

        /* access modifiers changed from: package-private */
        public void setFlags(int flags, int i) {
            int mask = i;
            this.mFlags = (this.mFlags & (mask ^ -1)) | (flags & mask);
        }

        /* access modifiers changed from: package-private */
        public void addFlags(int flags) {
            this.mFlags |= flags;
        }

        /* access modifiers changed from: package-private */
        public void addChangePayload(Object obj) {
            Object payload = obj;
            if (payload == null) {
                addFlags(1024);
            } else if ((this.mFlags & 1024) == 0) {
                createPayloadsIfNeeded();
                boolean add = this.mPayloads.add(payload);
            }
        }

        private void createPayloadsIfNeeded() {
            List<Object> list;
            if (this.mPayloads == null) {
                new ArrayList();
                this.mPayloads = list;
                this.mUnmodifiedPayloads = Collections.unmodifiableList(this.mPayloads);
            }
        }

        /* access modifiers changed from: package-private */
        public void clearPayload() {
            if (this.mPayloads != null) {
                this.mPayloads.clear();
            }
            this.mFlags &= -1025;
        }

        /* access modifiers changed from: package-private */
        public List<Object> getUnmodifiedPayloads() {
            if ((this.mFlags & 1024) != 0) {
                return FULLUPDATE_PAYLOADS;
            }
            if (this.mPayloads == null || this.mPayloads.size() == 0) {
                return FULLUPDATE_PAYLOADS;
            }
            return this.mUnmodifiedPayloads;
        }

        /* access modifiers changed from: package-private */
        public void resetInternal() {
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.clearNestedRecyclerViewIfNotNested(this);
        }

        /* access modifiers changed from: package-private */
        public void onEnteredHiddenState(RecyclerView recyclerView) {
            RecyclerView parent = recyclerView;
            if (this.mPendingAccessibilityState != -1) {
                this.mWasImportantForAccessibilityBeforeHidden = this.mPendingAccessibilityState;
            } else {
                this.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(this.itemView);
            }
            boolean childImportantForAccessibilityInternal = parent.setChildImportantForAccessibilityInternal(this, 4);
        }

        /* access modifiers changed from: package-private */
        public void onLeftHiddenState(RecyclerView parent) {
            boolean childImportantForAccessibilityInternal = parent.setChildImportantForAccessibilityInternal(this, this.mWasImportantForAccessibilityBeforeHidden);
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }

        public String toString() {
            StringBuilder sb;
            StringBuilder sb2;
            StringBuilder sb3;
            new StringBuilder();
            new StringBuilder(sb2.append("ViewHolder{").append(Integer.toHexString(hashCode())).append(" position=").append(this.mPosition).append(" id=").append(this.mItemId).append(", oldPos=").append(this.mOldPosition).append(", pLpos:").append(this.mPreLayoutPosition).toString());
            StringBuilder sb4 = sb;
            if (isScrap()) {
                StringBuilder append = sb4.append(" scrap ").append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
            }
            if (isInvalid()) {
                StringBuilder append2 = sb4.append(" invalid");
            }
            if (!isBound()) {
                StringBuilder append3 = sb4.append(" unbound");
            }
            if (needsUpdate()) {
                StringBuilder append4 = sb4.append(" update");
            }
            if (isRemoved()) {
                StringBuilder append5 = sb4.append(" removed");
            }
            if (shouldIgnore()) {
                StringBuilder append6 = sb4.append(" ignored");
            }
            if (isTmpDetached()) {
                StringBuilder append7 = sb4.append(" tmpDetached");
            }
            if (!isRecyclable()) {
                new StringBuilder();
                StringBuilder append8 = sb4.append(sb3.append(" not recyclable(").append(this.mIsRecyclableCount).append(")").toString());
            }
            if (isAdapterPositionUnknown()) {
                StringBuilder append9 = sb4.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                StringBuilder append10 = sb4.append(" no parent");
            }
            StringBuilder append11 = sb4.append("}");
            return sb4.toString();
        }

        public final void setIsRecyclable(boolean z) {
            StringBuilder sb;
            boolean recyclable = z;
            this.mIsRecyclableCount = recyclable ? this.mIsRecyclableCount - 1 : this.mIsRecyclableCount + 1;
            if (this.mIsRecyclableCount < 0) {
                this.mIsRecyclableCount = 0;
                new StringBuilder();
                int e = Log.e("View", sb.append("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ").append(this).toString());
            } else if (!recyclable && this.mIsRecyclableCount == 1) {
                this.mFlags |= 16;
            } else if (recyclable && this.mIsRecyclableCount == 0) {
                this.mFlags &= -17;
            }
        }

        public final boolean isRecyclable() {
            return (this.mFlags & 16) == 0 && !ViewCompat.hasTransientState(this.itemView);
        }

        /* access modifiers changed from: package-private */
        public boolean shouldBeKeptAsChild() {
            return (this.mFlags & 16) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean doesTransientStatePreventRecycling() {
            return (this.mFlags & 16) == 0 && ViewCompat.hasTransientState(this.itemView);
        }

        /* access modifiers changed from: package-private */
        public boolean isUpdated() {
            return (this.mFlags & 2) != 0;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean setChildImportantForAccessibilityInternal(ViewHolder viewHolder, int i) {
        ViewHolder viewHolder2 = viewHolder;
        int importantForAccessibility = i;
        if (isComputingLayout()) {
            viewHolder2.mPendingAccessibilityState = importantForAccessibility;
            boolean add = this.mPendingAccessibilityImportanceChange.add(viewHolder2);
            return false;
        }
        ViewCompat.setImportantForAccessibility(viewHolder2.itemView, importantForAccessibility);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void dispatchPendingImportantForAccessibilityChanges() {
        int state;
        for (int i = this.mPendingAccessibilityImportanceChange.size() - 1; i >= 0; i--) {
            ViewHolder viewHolder = this.mPendingAccessibilityImportanceChange.get(i);
            if (viewHolder.itemView.getParent() == this && !viewHolder.shouldIgnore() && (state = viewHolder.mPendingAccessibilityState) != -1) {
                ViewCompat.setImportantForAccessibility(viewHolder.itemView, state);
                viewHolder.mPendingAccessibilityState = -1;
            }
        }
        this.mPendingAccessibilityImportanceChange.clear();
    }

    /* access modifiers changed from: package-private */
    public int getAdapterPositionFor(ViewHolder viewHolder) {
        ViewHolder viewHolder2 = viewHolder;
        if (viewHolder2.hasAnyOfTheFlags(524) || !viewHolder2.isBound()) {
            return -1;
        }
        return this.mAdapterHelper.applyPendingUpdatesToPosition(viewHolder2.mPosition);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void initFastScroller(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        Throwable th;
        StringBuilder sb;
        Object obj;
        StateListDrawable verticalThumbDrawable = stateListDrawable;
        Drawable verticalTrackDrawable = drawable;
        StateListDrawable horizontalThumbDrawable = stateListDrawable2;
        Drawable horizontalTrackDrawable = drawable2;
        if (verticalThumbDrawable == null || verticalTrackDrawable == null || horizontalThumbDrawable == null || horizontalTrackDrawable == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Trying to set fast scroller without both required drawables.").append(exceptionLabel()).toString());
            throw th2;
        }
        Resources resources = getContext().getResources();
        Object obj2 = obj;
        new FastScroller(this, verticalThumbDrawable, verticalTrackDrawable, horizontalThumbDrawable, horizontalTrackDrawable, resources.getDimensionPixelSize(C0433R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(C0433R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(C0433R.dimen.fastscroll_margin));
    }

    public void setNestedScrollingEnabled(boolean enabled) {
        getScrollingChildHelper().setNestedScrollingEnabled(enabled);
    }

    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int axes) {
        return getScrollingChildHelper().startNestedScroll(axes);
    }

    public boolean startNestedScroll(int axes, int type) {
        return getScrollingChildHelper().startNestedScroll(axes, type);
    }

    public void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll();
    }

    public void stopNestedScroll(int type) {
        getScrollingChildHelper().stopNestedScroll(type);
    }

    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    public boolean hasNestedScrollingParent(int type) {
        return getScrollingChildHelper().hasNestedScrollingParent(type);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow, int type) {
        return getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return getScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow, int type) {
        return getScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return getScrollingChildHelper().dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return getScrollingChildHelper().dispatchNestedPreFling(velocityX, velocityY);
    }

    /* renamed from: android.support.v7.widget.RecyclerView$LayoutParams */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        final Rect mDecorInsets;
        boolean mInsetsDirty = true;
        boolean mPendingInvalidate = false;
        ViewHolder mViewHolder;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            Rect rect;
            new Rect();
            this.mDecorInsets = rect;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height) {
            super(width, height);
            Rect rect;
            new Rect();
            this.mDecorInsets = rect;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
            Rect rect;
            new Rect();
            this.mDecorInsets = rect;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            Rect rect;
            new Rect();
            this.mDecorInsets = rect;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(LayoutParams source) {
            super(source);
            Rect rect;
            new Rect();
            this.mDecorInsets = rect;
        }

        public boolean viewNeedsUpdate() {
            return this.mViewHolder.needsUpdate();
        }

        public boolean isViewInvalid() {
            return this.mViewHolder.isInvalid();
        }

        public boolean isItemRemoved() {
            return this.mViewHolder.isRemoved();
        }

        public boolean isItemChanged() {
            return this.mViewHolder.isUpdated();
        }

        @Deprecated
        public int getViewPosition() {
            return this.mViewHolder.getPosition();
        }

        public int getViewLayoutPosition() {
            return this.mViewHolder.getLayoutPosition();
        }

        public int getViewAdapterPosition() {
            return this.mViewHolder.getAdapterPosition();
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$AdapterDataObserver */
    public static abstract class AdapterDataObserver {
        public AdapterDataObserver() {
        }

        public void onChanged() {
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
        }

        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object obj) {
            Object obj2 = obj;
            onItemRangeChanged(positionStart, itemCount);
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$SmoothScroller */
    public static abstract class SmoothScroller {
        private LayoutManager mLayoutManager;
        private boolean mPendingInitialRun;
        private RecyclerView mRecyclerView;
        private final Action mRecyclingAction;
        private boolean mRunning;
        private boolean mStarted;
        private int mTargetPosition = -1;
        private View mTargetView;

        /* renamed from: android.support.v7.widget.RecyclerView$SmoothScroller$ScrollVectorProvider */
        public interface ScrollVectorProvider {
            @Nullable
            PointF computeScrollVectorForPosition(int i);
        }

        /* access modifiers changed from: protected */
        public abstract void onSeekTargetStep(@C0015Px int i, @C0015Px int i2, @NonNull State state, @NonNull Action action);

        /* access modifiers changed from: protected */
        public abstract void onStart();

        /* access modifiers changed from: protected */
        public abstract void onStop();

        /* access modifiers changed from: protected */
        public abstract void onTargetFound(@NonNull View view, @NonNull State state, @NonNull Action action);

        public SmoothScroller() {
            Action action;
            new Action(0, 0);
            this.mRecyclingAction = action;
        }

        /* access modifiers changed from: package-private */
        public void start(RecyclerView recyclerView, LayoutManager layoutManager) {
            Throwable th;
            StringBuilder sb;
            RecyclerView recyclerView2 = recyclerView;
            LayoutManager layoutManager2 = layoutManager;
            if (this.mStarted) {
                new StringBuilder();
                int w = Log.w(RecyclerView.TAG, sb.append("An instance of ").append(getClass().getSimpleName()).append(" was started ").append("more than once. Each instance of").append(getClass().getSimpleName()).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("is intended to only be used once. You should create a new instance for ").append("each use.").toString());
            }
            this.mRecyclerView = recyclerView2;
            this.mLayoutManager = layoutManager2;
            if (this.mTargetPosition == -1) {
                Throwable th2 = th;
                new IllegalArgumentException("Invalid target position");
                throw th2;
            }
            this.mRecyclerView.mState.mTargetPosition = this.mTargetPosition;
            this.mRunning = true;
            this.mPendingInitialRun = true;
            this.mTargetView = findViewByPosition(getTargetPosition());
            onStart();
            this.mRecyclerView.mViewFlinger.postOnAnimation();
            this.mStarted = true;
        }

        public void setTargetPosition(int targetPosition) {
            int i = targetPosition;
            this.mTargetPosition = i;
        }

        @Nullable
        public PointF computeScrollVectorForPosition(int i) {
            StringBuilder sb;
            int targetPosition = i;
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof ScrollVectorProvider) {
                return ((ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(targetPosition);
            }
            new StringBuilder();
            int w = Log.w(RecyclerView.TAG, sb.append("You should override computeScrollVectorForPosition when the LayoutManager does not implement ").append(ScrollVectorProvider.class.getCanonicalName()).toString());
            return null;
        }

        @Nullable
        public LayoutManager getLayoutManager() {
            return this.mLayoutManager;
        }

        /* access modifiers changed from: protected */
        public final void stop() {
            if (this.mRunning) {
                this.mRunning = false;
                onStop();
                this.mRecyclerView.mState.mTargetPosition = -1;
                this.mTargetView = null;
                this.mTargetPosition = -1;
                this.mPendingInitialRun = false;
                this.mLayoutManager.onSmoothScrollerStopped(this);
                this.mLayoutManager = null;
                this.mRecyclerView = null;
            }
        }

        public boolean isPendingInitialRun() {
            return this.mPendingInitialRun;
        }

        public boolean isRunning() {
            return this.mRunning;
        }

        public int getTargetPosition() {
            return this.mTargetPosition;
        }

        /* access modifiers changed from: package-private */
        public void onAnimation(int i, int i2) {
            PointF pointF;
            int dx = i;
            int dy = i2;
            RecyclerView recyclerView = this.mRecyclerView;
            if (!this.mRunning || this.mTargetPosition == -1 || recyclerView == null) {
                stop();
            }
            if (!(!this.mPendingInitialRun || this.mTargetView != null || this.mLayoutManager == null || (pointF = computeScrollVectorForPosition(this.mTargetPosition)) == null || (pointF.x == 0.0f && pointF.y == 0.0f))) {
                recyclerView.scrollStep((int) Math.signum(pointF.x), (int) Math.signum(pointF.y), (int[]) null);
            }
            this.mPendingInitialRun = false;
            if (this.mTargetView != null) {
                if (getChildPosition(this.mTargetView) == this.mTargetPosition) {
                    onTargetFound(this.mTargetView, recyclerView.mState, this.mRecyclingAction);
                    this.mRecyclingAction.runIfNecessary(recyclerView);
                    stop();
                } else {
                    int e = Log.e(RecyclerView.TAG, "Passed over target position while smooth scrolling.");
                    this.mTargetView = null;
                }
            }
            if (this.mRunning) {
                onSeekTargetStep(dx, dy, recyclerView.mState, this.mRecyclingAction);
                boolean hadJumpTarget = this.mRecyclingAction.hasJumpTarget();
                this.mRecyclingAction.runIfNecessary(recyclerView);
                if (!hadJumpTarget) {
                    return;
                }
                if (this.mRunning) {
                    this.mPendingInitialRun = true;
                    recyclerView.mViewFlinger.postOnAnimation();
                    return;
                }
                stop();
            }
        }

        public int getChildPosition(View view) {
            return this.mRecyclerView.getChildLayoutPosition(view);
        }

        public int getChildCount() {
            return this.mRecyclerView.mLayout.getChildCount();
        }

        public View findViewByPosition(int position) {
            return this.mRecyclerView.mLayout.findViewByPosition(position);
        }

        @Deprecated
        public void instantScrollToPosition(int position) {
            this.mRecyclerView.scrollToPosition(position);
        }

        /* access modifiers changed from: protected */
        public void onChildAttachedToWindow(View view) {
            View child = view;
            if (getChildPosition(child) == getTargetPosition()) {
                this.mTargetView = child;
            }
        }

        /* access modifiers changed from: protected */
        public void normalize(@NonNull PointF pointF) {
            PointF scrollVector = pointF;
            float magnitude = (float) Math.sqrt((double) ((scrollVector.x * scrollVector.x) + (scrollVector.y * scrollVector.y)));
            scrollVector.x /= magnitude;
            scrollVector.y /= magnitude;
        }

        /* renamed from: android.support.v7.widget.RecyclerView$SmoothScroller$Action */
        public static class Action {
            public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
            private boolean mChanged;
            private int mConsecutiveUpdates;
            private int mDuration;
            private int mDx;
            private int mDy;
            private Interpolator mInterpolator;
            private int mJumpToPosition;

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public Action(@C0015Px int dx, @C0015Px int dy) {
                this(dx, dy, Integer.MIN_VALUE, (Interpolator) null);
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public Action(@C0015Px int dx, @C0015Px int dy, int duration) {
                this(dx, dy, duration, (Interpolator) null);
            }

            public Action(@C0015Px int dx, @C0015Px int dy, int duration, @Nullable Interpolator interpolator) {
                this.mJumpToPosition = -1;
                this.mChanged = false;
                this.mConsecutiveUpdates = 0;
                this.mDx = dx;
                this.mDy = dy;
                this.mDuration = duration;
                this.mInterpolator = interpolator;
            }

            public void jumpTo(int targetPosition) {
                int i = targetPosition;
                this.mJumpToPosition = i;
            }

            /* access modifiers changed from: package-private */
            public boolean hasJumpTarget() {
                return this.mJumpToPosition >= 0;
            }

            /* access modifiers changed from: package-private */
            public void runIfNecessary(RecyclerView recyclerView) {
                RecyclerView recyclerView2 = recyclerView;
                if (this.mJumpToPosition >= 0) {
                    int position = this.mJumpToPosition;
                    this.mJumpToPosition = -1;
                    recyclerView2.jumpToPositionForSmoothScroller(position);
                    this.mChanged = false;
                } else if (this.mChanged) {
                    validate();
                    if (this.mInterpolator != null) {
                        recyclerView2.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration, this.mInterpolator);
                    } else if (this.mDuration == Integer.MIN_VALUE) {
                        recyclerView2.mViewFlinger.smoothScrollBy(this.mDx, this.mDy);
                    } else {
                        recyclerView2.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration);
                    }
                    this.mConsecutiveUpdates++;
                    if (this.mConsecutiveUpdates > 10) {
                        int e = Log.e(RecyclerView.TAG, "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.mChanged = false;
                } else {
                    this.mConsecutiveUpdates = 0;
                }
            }

            private void validate() {
                Throwable th;
                Throwable th2;
                if (this.mInterpolator != null && this.mDuration < 1) {
                    Throwable th3 = th2;
                    new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                    throw th3;
                } else if (this.mDuration < 1) {
                    Throwable th4 = th;
                    new IllegalStateException("Scroll duration must be a positive number");
                    throw th4;
                }
            }

            @C0015Px
            public int getDx() {
                return this.mDx;
            }

            public void setDx(@C0015Px int dx) {
                this.mChanged = true;
                this.mDx = dx;
            }

            @C0015Px
            public int getDy() {
                return this.mDy;
            }

            public void setDy(@C0015Px int dy) {
                this.mChanged = true;
                this.mDy = dy;
            }

            public int getDuration() {
                return this.mDuration;
            }

            public void setDuration(int duration) {
                this.mChanged = true;
                this.mDuration = duration;
            }

            @Nullable
            public Interpolator getInterpolator() {
                return this.mInterpolator;
            }

            public void setInterpolator(@Nullable Interpolator interpolator) {
                this.mChanged = true;
                this.mInterpolator = interpolator;
            }

            public void update(@C0015Px int dx, @C0015Px int dy, int duration, @Nullable Interpolator interpolator) {
                this.mDx = dx;
                this.mDy = dy;
                this.mDuration = duration;
                this.mInterpolator = interpolator;
                this.mChanged = true;
            }
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$AdapterDataObservable */
    static class AdapterDataObservable extends Observable<AdapterDataObserver> {
        AdapterDataObservable() {
        }

        public boolean hasObservers() {
            return !this.mObservers.isEmpty();
        }

        public void notifyChanged() {
            for (int i = this.mObservers.size() - 1; i >= 0; i--) {
                ((AdapterDataObserver) this.mObservers.get(i)).onChanged();
            }
        }

        public void notifyItemRangeChanged(int positionStart, int itemCount) {
            notifyItemRangeChanged(positionStart, itemCount, (Object) null);
        }

        public void notifyItemRangeChanged(int i, int i2, @Nullable Object obj) {
            int positionStart = i;
            int itemCount = i2;
            Object payload = obj;
            for (int i3 = this.mObservers.size() - 1; i3 >= 0; i3--) {
                ((AdapterDataObserver) this.mObservers.get(i3)).onItemRangeChanged(positionStart, itemCount, payload);
            }
        }

        public void notifyItemRangeInserted(int i, int i2) {
            int positionStart = i;
            int itemCount = i2;
            for (int i3 = this.mObservers.size() - 1; i3 >= 0; i3--) {
                ((AdapterDataObserver) this.mObservers.get(i3)).onItemRangeInserted(positionStart, itemCount);
            }
        }

        public void notifyItemRangeRemoved(int i, int i2) {
            int positionStart = i;
            int itemCount = i2;
            for (int i3 = this.mObservers.size() - 1; i3 >= 0; i3--) {
                ((AdapterDataObserver) this.mObservers.get(i3)).onItemRangeRemoved(positionStart, itemCount);
            }
        }

        public void notifyItemMoved(int i, int i2) {
            int fromPosition = i;
            int toPosition = i2;
            for (int i3 = this.mObservers.size() - 1; i3 >= 0; i3--) {
                ((AdapterDataObserver) this.mObservers.get(i3)).onItemRangeMoved(fromPosition, toPosition, 1);
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* renamed from: android.support.v7.widget.RecyclerView$SavedState */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR;
        Parcelable mLayoutState;

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
                r5 = r2
                if (r5 == 0) goto L_0x0016
                r5 = r2
            L_0x000f:
                android.os.Parcelable r4 = r4.readParcelable(r5)
                r3.mLayoutState = r4
                return
            L_0x0016:
                java.lang.Class<android.support.v7.widget.RecyclerView$LayoutManager> r5 = android.support.p003v7.widget.RecyclerView.LayoutManager.class
                java.lang.ClassLoader r5 = r5.getClassLoader()
                goto L_0x000f
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.RecyclerView.SavedState.<init>(android.os.Parcel, java.lang.ClassLoader):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel parcel, int flags) {
            Parcel dest = parcel;
            super.writeToParcel(dest, flags);
            dest.writeParcelable(this.mLayoutState, 0);
        }

        /* access modifiers changed from: package-private */
        public void copyFrom(SavedState other) {
            Parcelable parcelable = other.mLayoutState;
            this.mLayoutState = parcelable;
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

    /* renamed from: android.support.v7.widget.RecyclerView$State */
    public static class State {
        static final int STEP_ANIMATIONS = 4;
        static final int STEP_LAYOUT = 2;
        static final int STEP_START = 1;
        private SparseArray<Object> mData;
        int mDeletedInvisibleItemCountSincePreviousLayout = 0;
        long mFocusedItemId;
        int mFocusedItemPosition;
        int mFocusedSubChildId;
        boolean mInPreLayout = false;
        boolean mIsMeasuring = false;
        int mItemCount = 0;
        int mLayoutStep = 1;
        int mPreviousLayoutItemCount = 0;
        int mRemainingScrollHorizontal;
        int mRemainingScrollVertical;
        boolean mRunPredictiveAnimations = false;
        boolean mRunSimpleAnimations = false;
        boolean mStructureChanged = false;
        int mTargetPosition = -1;
        boolean mTrackOldChangeHolders = false;

        public State() {
        }

        /* access modifiers changed from: package-private */
        public void assertLayoutStep(int i) {
            Throwable th;
            StringBuilder sb;
            int accepted = i;
            if ((accepted & this.mLayoutStep) == 0) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("Layout state should be one of ").append(Integer.toBinaryString(accepted)).append(" but it is ").append(Integer.toBinaryString(this.mLayoutStep)).toString());
                throw th2;
            }
        }

        /* access modifiers changed from: package-private */
        public State reset() {
            this.mTargetPosition = -1;
            if (this.mData != null) {
                this.mData.clear();
            }
            this.mItemCount = 0;
            this.mStructureChanged = false;
            this.mIsMeasuring = false;
            return this;
        }

        /* access modifiers changed from: package-private */
        public void prepareForNestedPrefetch(Adapter adapter) {
            this.mLayoutStep = 1;
            this.mItemCount = adapter.getItemCount();
            this.mInPreLayout = false;
            this.mTrackOldChangeHolders = false;
            this.mIsMeasuring = false;
        }

        public boolean isMeasuring() {
            return this.mIsMeasuring;
        }

        public boolean isPreLayout() {
            return this.mInPreLayout;
        }

        public boolean willRunPredictiveAnimations() {
            return this.mRunPredictiveAnimations;
        }

        public boolean willRunSimpleAnimations() {
            return this.mRunSimpleAnimations;
        }

        public void remove(int i) {
            int resourceId = i;
            if (this.mData != null) {
                this.mData.remove(resourceId);
            }
        }

        public <T> T get(int i) {
            int resourceId = i;
            if (this.mData == null) {
                return null;
            }
            return this.mData.get(resourceId);
        }

        public void put(int i, Object obj) {
            SparseArray<Object> sparseArray;
            int resourceId = i;
            Object data = obj;
            if (this.mData == null) {
                new SparseArray<>();
                this.mData = sparseArray;
            }
            this.mData.put(resourceId, data);
        }

        public int getTargetScrollPosition() {
            return this.mTargetPosition;
        }

        public boolean hasTargetScrollPosition() {
            return this.mTargetPosition != -1;
        }

        public boolean didStructureChange() {
            return this.mStructureChanged;
        }

        public int getItemCount() {
            return this.mInPreLayout ? this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout : this.mItemCount;
        }

        public int getRemainingScrollHorizontal() {
            return this.mRemainingScrollHorizontal;
        }

        public int getRemainingScrollVertical() {
            return this.mRemainingScrollVertical;
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append("State{mTargetPosition=").append(this.mTargetPosition).append(", mData=").append(this.mData).append(", mItemCount=").append(this.mItemCount).append(", mIsMeasuring=").append(this.mIsMeasuring).append(", mPreviousLayoutItemCount=").append(this.mPreviousLayoutItemCount).append(", mDeletedInvisibleItemCountSincePreviousLayout=").append(this.mDeletedInvisibleItemCountSincePreviousLayout).append(", mStructureChanged=").append(this.mStructureChanged).append(", mInPreLayout=").append(this.mInPreLayout).append(", mRunSimpleAnimations=").append(this.mRunSimpleAnimations).append(", mRunPredictiveAnimations=").append(this.mRunPredictiveAnimations).append('}').toString();
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$OnFlingListener */
    public static abstract class OnFlingListener {
        public abstract boolean onFling(int i, int i2);

        public OnFlingListener() {
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$ItemAnimatorRestoreListener */
    private class ItemAnimatorRestoreListener implements ItemAnimator.ItemAnimatorListener {
        final /* synthetic */ RecyclerView this$0;

        ItemAnimatorRestoreListener(RecyclerView recyclerView) {
            this.this$0 = recyclerView;
        }

        public void onAnimationFinished(ViewHolder viewHolder) {
            ViewHolder item = viewHolder;
            item.setIsRecyclable(true);
            if (item.mShadowedHolder != null && item.mShadowingHolder == null) {
                item.mShadowedHolder = null;
            }
            item.mShadowingHolder = null;
            if (!item.shouldBeKeptAsChild() && !this.this$0.removeAnimatingView(item.itemView) && item.isTmpDetached()) {
                this.this$0.removeDetachedView(item.itemView, false);
            }
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$ItemAnimator */
    public static abstract class ItemAnimator {
        public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        public static final int FLAG_CHANGED = 2;
        public static final int FLAG_INVALIDATED = 4;
        public static final int FLAG_MOVED = 2048;
        public static final int FLAG_REMOVED = 8;
        private long mAddDuration;
        private long mChangeDuration;
        private ArrayList<ItemAnimatorFinishedListener> mFinishedListeners;
        private ItemAnimatorListener mListener = null;
        private long mMoveDuration;
        private long mRemoveDuration;

        @Retention(RetentionPolicy.SOURCE)
        /* renamed from: android.support.v7.widget.RecyclerView$ItemAnimator$AdapterChanges */
        public @interface AdapterChanges {
        }

        /* renamed from: android.support.v7.widget.RecyclerView$ItemAnimator$ItemAnimatorFinishedListener */
        public interface ItemAnimatorFinishedListener {
            void onAnimationsFinished();
        }

        /* renamed from: android.support.v7.widget.RecyclerView$ItemAnimator$ItemAnimatorListener */
        interface ItemAnimatorListener {
            void onAnimationFinished(@NonNull ViewHolder viewHolder);
        }

        public abstract boolean animateAppearance(@NonNull ViewHolder viewHolder, @Nullable ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        public abstract boolean animateChange(@NonNull ViewHolder viewHolder, @NonNull ViewHolder viewHolder2, @NonNull ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        public abstract boolean animateDisappearance(@NonNull ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @Nullable ItemHolderInfo itemHolderInfo2);

        public abstract boolean animatePersistence(@NonNull ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        public abstract void endAnimation(@NonNull ViewHolder viewHolder);

        public abstract void endAnimations();

        public abstract boolean isRunning();

        public abstract void runPendingAnimations();

        public ItemAnimator() {
            ArrayList<ItemAnimatorFinishedListener> arrayList;
            new ArrayList<>();
            this.mFinishedListeners = arrayList;
            this.mAddDuration = 120;
            this.mRemoveDuration = 120;
            this.mMoveDuration = 250;
            this.mChangeDuration = 250;
        }

        public long getMoveDuration() {
            return this.mMoveDuration;
        }

        public void setMoveDuration(long moveDuration) {
            long j = moveDuration;
            this.mMoveDuration = j;
        }

        public long getAddDuration() {
            return this.mAddDuration;
        }

        public void setAddDuration(long addDuration) {
            long j = addDuration;
            this.mAddDuration = j;
        }

        public long getRemoveDuration() {
            return this.mRemoveDuration;
        }

        public void setRemoveDuration(long removeDuration) {
            long j = removeDuration;
            this.mRemoveDuration = j;
        }

        public long getChangeDuration() {
            return this.mChangeDuration;
        }

        public void setChangeDuration(long changeDuration) {
            long j = changeDuration;
            this.mChangeDuration = j;
        }

        /* access modifiers changed from: package-private */
        public void setListener(ItemAnimatorListener listener) {
            ItemAnimatorListener itemAnimatorListener = listener;
            this.mListener = itemAnimatorListener;
        }

        @NonNull
        public ItemHolderInfo recordPreLayoutInformation(@NonNull State state, @NonNull ViewHolder viewHolder, int i, @NonNull List<Object> list) {
            State state2 = state;
            int i2 = i;
            List<Object> list2 = list;
            return obtainHolderInfo().setFrom(viewHolder);
        }

        @NonNull
        public ItemHolderInfo recordPostLayoutInformation(@NonNull State state, @NonNull ViewHolder viewHolder) {
            State state2 = state;
            return obtainHolderInfo().setFrom(viewHolder);
        }

        static int buildAdapterChangeFlagsForAnimations(ViewHolder viewHolder) {
            ViewHolder viewHolder2 = viewHolder;
            int flags = viewHolder2.mFlags & 14;
            if (viewHolder2.isInvalid()) {
                return 4;
            }
            if ((flags & 4) == 0) {
                int oldPos = viewHolder2.getOldPosition();
                int pos = viewHolder2.getAdapterPosition();
                if (!(oldPos == -1 || pos == -1 || oldPos == pos)) {
                    flags |= 2048;
                }
            }
            return flags;
        }

        public final void dispatchAnimationFinished(@NonNull ViewHolder viewHolder) {
            ViewHolder viewHolder2 = viewHolder;
            onAnimationFinished(viewHolder2);
            if (this.mListener != null) {
                this.mListener.onAnimationFinished(viewHolder2);
            }
        }

        public void onAnimationFinished(@NonNull ViewHolder viewHolder) {
        }

        public final void dispatchAnimationStarted(@NonNull ViewHolder viewHolder) {
            onAnimationStarted(viewHolder);
        }

        public void onAnimationStarted(@NonNull ViewHolder viewHolder) {
        }

        public final boolean isRunning(@Nullable ItemAnimatorFinishedListener itemAnimatorFinishedListener) {
            ItemAnimatorFinishedListener listener = itemAnimatorFinishedListener;
            boolean running = isRunning();
            if (listener != null) {
                if (!running) {
                    listener.onAnimationsFinished();
                } else {
                    boolean add = this.mFinishedListeners.add(listener);
                }
            }
            return running;
        }

        public boolean canReuseUpdatedViewHolder(@NonNull ViewHolder viewHolder) {
            ViewHolder viewHolder2 = viewHolder;
            return true;
        }

        public boolean canReuseUpdatedViewHolder(@NonNull ViewHolder viewHolder, @NonNull List<Object> list) {
            List<Object> list2 = list;
            return canReuseUpdatedViewHolder(viewHolder);
        }

        public final void dispatchAnimationsFinished() {
            int count = this.mFinishedListeners.size();
            for (int i = 0; i < count; i++) {
                this.mFinishedListeners.get(i).onAnimationsFinished();
            }
            this.mFinishedListeners.clear();
        }

        @NonNull
        public ItemHolderInfo obtainHolderInfo() {
            ItemHolderInfo itemHolderInfo;
            new ItemHolderInfo();
            return itemHolderInfo;
        }

        /* renamed from: android.support.v7.widget.RecyclerView$ItemAnimator$ItemHolderInfo */
        public static class ItemHolderInfo {
            public int bottom;
            public int changeFlags;
            public int left;
            public int right;
            public int top;

            public ItemHolderInfo() {
            }

            @NonNull
            public ItemHolderInfo setFrom(@NonNull ViewHolder holder) {
                return setFrom(holder, 0);
            }

            @NonNull
            public ItemHolderInfo setFrom(@NonNull ViewHolder holder, int i) {
                int i2 = i;
                View view = holder.itemView;
                this.left = view.getLeft();
                this.top = view.getTop();
                this.right = view.getRight();
                this.bottom = view.getBottom();
                return this;
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i, int i2) {
        int childCount = i;
        int i3 = i2;
        if (this.mChildDrawingOrderCallback == null) {
            return super.getChildDrawingOrder(childCount, i3);
        }
        return this.mChildDrawingOrderCallback.onGetChildDrawingOrder(childCount, i3);
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        NestedScrollingChildHelper nestedScrollingChildHelper;
        if (this.mScrollingChildHelper == null) {
            new NestedScrollingChildHelper(this);
            this.mScrollingChildHelper = nestedScrollingChildHelper;
        }
        return this.mScrollingChildHelper;
    }
}
