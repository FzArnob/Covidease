package android.support.p000v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.C0015Px;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import gnu.expr.Declaration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: android.support.v4.view.ViewPager */
public class ViewPager extends ViewGroup {
    private static final int CLOSE_ENOUGH = 2;
    private static final Comparator<ItemInfo> COMPARATOR;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    static final int[] LAYOUT_ATTRS = {16842931};
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE = false;
    private static final Interpolator sInterpolator;
    private static final ViewPositionComparator sPositionComparator;
    private int mActivePointerId = -1;
    PagerAdapter mAdapter;
    private List<OnAdapterChangeListener> mAdapterChangeListeners;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout = true;
    private float mFirstOffset = -3.4028235E38f;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsScrollStarted;
    private boolean mIsUnableToDrag;
    private final ArrayList<ItemInfo> mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset = Float.MAX_VALUE;
    private EdgeEffect mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets = false;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit = 1;
    private OnPageChangeListener mOnPageChangeListener;
    private List<OnPageChangeListener> mOnPageChangeListeners;
    private int mPageMargin;
    private PageTransformer mPageTransformer;
    private int mPageTransformerLayerType;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState = null;
    private ClassLoader mRestoredClassLoader = null;
    private int mRestoredCurItem = -1;
    private EdgeEffect mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private final ItemInfo mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    @Inherited
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    /* renamed from: android.support.v4.view.ViewPager$DecorView */
    public @interface DecorView {
    }

    /* renamed from: android.support.v4.view.ViewPager$OnAdapterChangeListener */
    public interface OnAdapterChangeListener {
        void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter pagerAdapter, @Nullable PagerAdapter pagerAdapter2);
    }

    /* renamed from: android.support.v4.view.ViewPager$OnPageChangeListener */
    public interface OnPageChangeListener {
        void onPageScrollStateChanged(int i);

        void onPageScrolled(int i, float f, @C0015Px int i2);

        void onPageSelected(int i);
    }

    /* renamed from: android.support.v4.view.ViewPager$PageTransformer */
    public interface PageTransformer {
        void transformPage(@NonNull View view, float f);
    }

    static {
        Comparator<ItemInfo> comparator;
        Interpolator interpolator;
        ViewPositionComparator viewPositionComparator;
        new Comparator<ItemInfo>() {
            public int compare(ItemInfo lhs, ItemInfo rhs) {
                return lhs.position - rhs.position;
            }
        };
        COMPARATOR = comparator;
        new Interpolator() {
            public float getInterpolation(float t) {
                float t2 = t - 1.0f;
                return (t2 * t2 * t2 * t2 * t2) + 1.0f;
            }
        };
        sInterpolator = interpolator;
        new ViewPositionComparator();
        sPositionComparator = viewPositionComparator;
    }

    /* renamed from: android.support.v4.view.ViewPager$ItemInfo */
    static class ItemInfo {
        Object object;
        float offset;
        int position;
        boolean scrolling;
        float widthFactor;

        ItemInfo() {
        }
    }

    /* renamed from: android.support.v4.view.ViewPager$SimpleOnPageChangeListener */
    public static class SimpleOnPageChangeListener implements OnPageChangeListener {
        public SimpleOnPageChangeListener() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
        }

        public void onPageScrollStateChanged(int state) {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ViewPager(@NonNull Context context) {
        super(context);
        ArrayList<ItemInfo> arrayList;
        ItemInfo itemInfo;
        Rect rect;
        Runnable runnable;
        new ArrayList<>();
        this.mItems = arrayList;
        new ItemInfo();
        this.mTempItem = itemInfo;
        new Rect();
        this.mTempRect = rect;
        new Runnable(this) {
            final /* synthetic */ ViewPager this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                this.this$0.setScrollState(0);
                this.this$0.populate();
            }
        };
        this.mEndScrollRunnable = runnable;
        this.mScrollState = 0;
        initViewPager();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ArrayList<ItemInfo> arrayList;
        ItemInfo itemInfo;
        Rect rect;
        Runnable runnable;
        new ArrayList<>();
        this.mItems = arrayList;
        new ItemInfo();
        this.mTempItem = itemInfo;
        new Rect();
        this.mTempRect = rect;
        new Runnable(this) {
            final /* synthetic */ ViewPager this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                this.this$0.setScrollState(0);
                this.this$0.populate();
            }
        };
        this.mEndScrollRunnable = runnable;
        this.mScrollState = 0;
        initViewPager();
    }

    /* access modifiers changed from: package-private */
    public void initViewPager() {
        Scroller scroller;
        EdgeEffect edgeEffect;
        EdgeEffect edgeEffect2;
        AccessibilityDelegateCompat accessibilityDelegateCompat;
        OnApplyWindowInsetsListener onApplyWindowInsetsListener;
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        new Scroller(context, sInterpolator);
        this.mScroller = scroller;
        ViewConfiguration configuration = ViewConfiguration.get(context);
        float density = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = configuration.getScaledPagingTouchSlop();
        this.mMinimumVelocity = (int) (400.0f * density);
        this.mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
        new EdgeEffect(context);
        this.mLeftEdge = edgeEffect;
        new EdgeEffect(context);
        this.mRightEdge = edgeEffect2;
        this.mFlingDistance = (int) (25.0f * density);
        this.mCloseEnough = (int) (2.0f * density);
        this.mDefaultGutterSize = (int) (16.0f * density);
        new MyAccessibilityDelegate(this);
        ViewCompat.setAccessibilityDelegate(this, accessibilityDelegateCompat);
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        new OnApplyWindowInsetsListener(this) {
            private final Rect mTempRect;
            final /* synthetic */ ViewPager this$0;

            {
                Rect rect;
                this.this$0 = this$0;
                new Rect();
                this.mTempRect = rect;
            }

            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat originalInsets) {
                WindowInsetsCompat applied = ViewCompat.onApplyWindowInsets(v, originalInsets);
                if (applied.isConsumed()) {
                    return applied;
                }
                Rect res = this.mTempRect;
                res.left = applied.getSystemWindowInsetLeft();
                res.top = applied.getSystemWindowInsetTop();
                res.right = applied.getSystemWindowInsetRight();
                res.bottom = applied.getSystemWindowInsetBottom();
                int count = this.this$0.getChildCount();
                for (int i = 0; i < count; i++) {
                    WindowInsetsCompat childInsets = ViewCompat.dispatchApplyWindowInsets(this.this$0.getChildAt(i), applied);
                    res.left = Math.min(childInsets.getSystemWindowInsetLeft(), res.left);
                    res.top = Math.min(childInsets.getSystemWindowInsetTop(), res.top);
                    res.right = Math.min(childInsets.getSystemWindowInsetRight(), res.right);
                    res.bottom = Math.min(childInsets.getSystemWindowInsetBottom(), res.bottom);
                }
                return applied.replaceSystemWindowInsets(res.left, res.top, res.right, res.bottom);
            }
        };
        ViewCompat.setOnApplyWindowInsetsListener(this, onApplyWindowInsetsListener);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        boolean removeCallbacks = removeCallbacks(this.mEndScrollRunnable);
        if (this.mScroller != null && !this.mScroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: package-private */
    public void setScrollState(int i) {
        int newState = i;
        if (this.mScrollState != newState) {
            this.mScrollState = newState;
            if (this.mPageTransformer != null) {
                enableLayers(newState != 0);
            }
            dispatchOnScrollStateChanged(newState);
        }
    }

    public void setAdapter(@Nullable PagerAdapter pagerAdapter) {
        PagerObserver pagerObserver;
        PagerAdapter adapter = pagerAdapter;
        if (this.mAdapter != null) {
            this.mAdapter.setViewPagerObserver((DataSetObserver) null);
            this.mAdapter.startUpdate((ViewGroup) this);
            for (int i = 0; i < this.mItems.size(); i++) {
                ItemInfo ii = this.mItems.get(i);
                this.mAdapter.destroyItem((ViewGroup) this, ii.position, ii.object);
            }
            this.mAdapter.finishUpdate((ViewGroup) this);
            this.mItems.clear();
            removeNonDecorViews();
            this.mCurItem = 0;
            scrollTo(0, 0);
        }
        PagerAdapter oldAdapter = this.mAdapter;
        this.mAdapter = adapter;
        this.mExpectedAdapterCount = 0;
        if (this.mAdapter != null) {
            if (this.mObserver == null) {
                new PagerObserver(this);
                this.mObserver = pagerObserver;
            }
            this.mAdapter.setViewPagerObserver(this.mObserver);
            this.mPopulatePending = false;
            boolean wasFirstLayout = this.mFirstLayout;
            this.mFirstLayout = true;
            this.mExpectedAdapterCount = this.mAdapter.getCount();
            if (this.mRestoredCurItem >= 0) {
                this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
                setCurrentItemInternal(this.mRestoredCurItem, false, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = null;
                this.mRestoredClassLoader = null;
            } else if (!wasFirstLayout) {
                populate();
            } else {
                requestLayout();
            }
        }
        if (this.mAdapterChangeListeners != null && !this.mAdapterChangeListeners.isEmpty()) {
            int count = this.mAdapterChangeListeners.size();
            for (int i2 = 0; i2 < count; i2++) {
                this.mAdapterChangeListeners.get(i2).onAdapterChanged(this, oldAdapter, adapter);
            }
        }
    }

    private void removeNonDecorViews() {
        int i = 0;
        while (i < getChildCount()) {
            if (!((LayoutParams) getChildAt(i).getLayoutParams()).isDecor) {
                removeViewAt(i);
                i--;
            }
            i++;
        }
    }

    @Nullable
    public PagerAdapter getAdapter() {
        return this.mAdapter;
    }

    public void addOnAdapterChangeListener(@NonNull OnAdapterChangeListener onAdapterChangeListener) {
        List<OnAdapterChangeListener> list;
        OnAdapterChangeListener listener = onAdapterChangeListener;
        if (this.mAdapterChangeListeners == null) {
            new ArrayList();
            this.mAdapterChangeListeners = list;
        }
        boolean add = this.mAdapterChangeListeners.add(listener);
    }

    public void removeOnAdapterChangeListener(@NonNull OnAdapterChangeListener onAdapterChangeListener) {
        OnAdapterChangeListener listener = onAdapterChangeListener;
        if (this.mAdapterChangeListeners != null) {
            boolean remove = this.mAdapterChangeListeners.remove(listener);
        }
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public void setCurrentItem(int item) {
        this.mPopulatePending = false;
        setCurrentItemInternal(item, !this.mFirstLayout, false);
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        this.mPopulatePending = false;
        setCurrentItemInternal(item, smoothScroll, false);
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int item, boolean smoothScroll, boolean always) {
        setCurrentItemInternal(item, smoothScroll, always, 0);
    }

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int i, boolean z, boolean z2, int i2) {
        int item = i;
        boolean smoothScroll = z;
        boolean always = z2;
        int velocity = i2;
        if (this.mAdapter == null || this.mAdapter.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (always || this.mCurItem != item || this.mItems.size() == 0) {
            if (item < 0) {
                item = 0;
            } else if (item >= this.mAdapter.getCount()) {
                item = this.mAdapter.getCount() - 1;
            }
            int pageLimit = this.mOffscreenPageLimit;
            if (item > this.mCurItem + pageLimit || item < this.mCurItem - pageLimit) {
                for (int i3 = 0; i3 < this.mItems.size(); i3++) {
                    this.mItems.get(i3).scrolling = true;
                }
            }
            boolean dispatchSelected = this.mCurItem != item;
            if (this.mFirstLayout) {
                this.mCurItem = item;
                if (dispatchSelected) {
                    dispatchOnPageSelected(item);
                }
                requestLayout();
                return;
            }
            populate(item);
            scrollToItem(item, smoothScroll, velocity, dispatchSelected);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    private void scrollToItem(int i, boolean z, int i2, boolean z2) {
        int item = i;
        boolean smoothScroll = z;
        int velocity = i2;
        boolean dispatchSelected = z2;
        ItemInfo curInfo = infoForPosition(item);
        int destX = 0;
        if (curInfo != null) {
            destX = (int) (((float) getClientWidth()) * Math.max(this.mFirstOffset, Math.min(curInfo.offset, this.mLastOffset)));
        }
        if (smoothScroll) {
            smoothScrollTo(destX, 0, velocity);
            if (dispatchSelected) {
                dispatchOnPageSelected(item);
                return;
            }
            return;
        }
        if (dispatchSelected) {
            dispatchOnPageSelected(item);
        }
        completeScroll(false);
        scrollTo(destX, 0);
        boolean pageScrolled = pageScrolled(destX);
    }

    @Deprecated
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        OnPageChangeListener onPageChangeListener = listener;
        this.mOnPageChangeListener = onPageChangeListener;
    }

    public void addOnPageChangeListener(@NonNull OnPageChangeListener onPageChangeListener) {
        List<OnPageChangeListener> list;
        OnPageChangeListener listener = onPageChangeListener;
        if (this.mOnPageChangeListeners == null) {
            new ArrayList();
            this.mOnPageChangeListeners = list;
        }
        boolean add = this.mOnPageChangeListeners.add(listener);
    }

    public void removeOnPageChangeListener(@NonNull OnPageChangeListener onPageChangeListener) {
        OnPageChangeListener listener = onPageChangeListener;
        if (this.mOnPageChangeListeners != null) {
            boolean remove = this.mOnPageChangeListeners.remove(listener);
        }
    }

    public void clearOnPageChangeListeners() {
        if (this.mOnPageChangeListeners != null) {
            this.mOnPageChangeListeners.clear();
        }
    }

    public void setPageTransformer(boolean reverseDrawingOrder, @Nullable PageTransformer transformer) {
        setPageTransformer(reverseDrawingOrder, transformer, 2);
    }

    public void setPageTransformer(boolean z, @Nullable PageTransformer pageTransformer, int i) {
        boolean reverseDrawingOrder = z;
        PageTransformer transformer = pageTransformer;
        int pageLayerType = i;
        boolean hasTransformer = transformer != null;
        boolean needsPopulate = hasTransformer != (this.mPageTransformer != null);
        this.mPageTransformer = transformer;
        setChildrenDrawingOrderEnabled(hasTransformer);
        if (hasTransformer) {
            this.mDrawingOrder = reverseDrawingOrder ? 2 : 1;
            this.mPageTransformerLayerType = pageLayerType;
        } else {
            this.mDrawingOrder = 0;
        }
        if (needsPopulate) {
            populate();
        }
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int childCount, int i) {
        int i2 = i;
        return ((LayoutParams) this.mDrawingOrderedChildren.get(this.mDrawingOrder == 2 ? (childCount - 1) - i2 : i2).getLayoutParams()).childIndex;
    }

    /* access modifiers changed from: package-private */
    public OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener listener) {
        OnPageChangeListener oldListener = this.mInternalPageChangeListener;
        this.mInternalPageChangeListener = listener;
        return oldListener;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public void setOffscreenPageLimit(int i) {
        StringBuilder sb;
        int limit = i;
        if (limit < 1) {
            new StringBuilder();
            int w = Log.w(TAG, sb.append("Requested offscreen page limit ").append(limit).append(" too small; defaulting to ").append(1).toString());
            limit = 1;
        }
        if (limit != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = limit;
            populate();
        }
    }

    public void setPageMargin(int i) {
        int marginPixels = i;
        int oldMargin = this.mPageMargin;
        this.mPageMargin = marginPixels;
        int width = getWidth();
        recomputeScrollPosition(width, width, marginPixels, oldMargin);
        requestLayout();
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    public void setPageMarginDrawable(@Nullable Drawable drawable) {
        Drawable d = drawable;
        this.mMarginDrawable = d;
        if (d != null) {
            refreshDrawableState();
        }
        setWillNotDraw(d == null);
        invalidate();
    }

    public void setPageMarginDrawable(@DrawableRes int resId) {
        setPageMarginDrawable(ContextCompat.getDrawable(getContext(), resId));
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        Drawable who = drawable;
        return super.verifyDrawable(who) || who == this.mMarginDrawable;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable d = this.mMarginDrawable;
        if (d != null && d.isStateful()) {
            boolean state = d.setState(getDrawableState());
        }
    }

    /* access modifiers changed from: package-private */
    public float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((double) ((f - 0.5f) * 0.47123894f));
    }

    /* access modifiers changed from: package-private */
    public void smoothScrollTo(int x, int y) {
        smoothScrollTo(x, y, 0);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00af  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void smoothScrollTo(int r25, int r26, int r27) {
        /*
            r24 = this;
            r2 = r24
            r3 = r25
            r4 = r26
            r5 = r27
            r18 = r2
            int r18 = r18.getChildCount()
            if (r18 != 0) goto L_0x0018
            r18 = r2
            r19 = 0
            r18.setScrollingCacheEnabled(r19)
        L_0x0017:
            return
        L_0x0018:
            r18 = r2
            r0 = r18
            android.widget.Scroller r0 = r0.mScroller
            r18 = r0
            if (r18 == 0) goto L_0x0096
            r18 = r2
            r0 = r18
            android.widget.Scroller r0 = r0.mScroller
            r18 = r0
            boolean r18 = r18.isFinished()
            if (r18 != 0) goto L_0x0096
            r18 = 1
        L_0x0032:
            r7 = r18
            r18 = r7
            if (r18 == 0) goto L_0x00a6
            r18 = r2
            r0 = r18
            boolean r0 = r0.mIsScrollStarted
            r18 = r0
            if (r18 == 0) goto L_0x0099
            r18 = r2
            r0 = r18
            android.widget.Scroller r0 = r0.mScroller
            r18 = r0
            int r18 = r18.getCurrX()
        L_0x004e:
            r6 = r18
            r18 = r2
            r0 = r18
            android.widget.Scroller r0 = r0.mScroller
            r18 = r0
            r18.abortAnimation()
            r18 = r2
            r19 = 0
            r18.setScrollingCacheEnabled(r19)
        L_0x0062:
            r18 = r2
            int r18 = r18.getScrollY()
            r8 = r18
            r18 = r3
            r19 = r6
            int r18 = r18 - r19
            r9 = r18
            r18 = r4
            r19 = r8
            int r18 = r18 - r19
            r10 = r18
            r18 = r9
            if (r18 != 0) goto L_0x00af
            r18 = r10
            if (r18 != 0) goto L_0x00af
            r18 = r2
            r19 = 0
            r18.completeScroll(r19)
            r18 = r2
            r18.populate()
            r18 = r2
            r19 = 0
            r18.setScrollState(r19)
            goto L_0x0017
        L_0x0096:
            r18 = 0
            goto L_0x0032
        L_0x0099:
            r18 = r2
            r0 = r18
            android.widget.Scroller r0 = r0.mScroller
            r18 = r0
            int r18 = r18.getStartX()
            goto L_0x004e
        L_0x00a6:
            r18 = r2
            int r18 = r18.getScrollX()
            r6 = r18
            goto L_0x0062
        L_0x00af:
            r18 = r2
            r19 = 1
            r18.setScrollingCacheEnabled(r19)
            r18 = r2
            r19 = 2
            r18.setScrollState(r19)
            r18 = r2
            int r18 = r18.getClientWidth()
            r11 = r18
            r18 = r11
            r19 = 2
            int r18 = r18 / 2
            r12 = r18
            r18 = 1065353216(0x3f800000, float:1.0)
            r19 = 1065353216(0x3f800000, float:1.0)
            r20 = r9
            int r20 = java.lang.Math.abs(r20)
            r0 = r20
            float r0 = (float) r0
            r20 = r0
            float r19 = r19 * r20
            r20 = r11
            r0 = r20
            float r0 = (float) r0
            r20 = r0
            float r19 = r19 / r20
            float r18 = java.lang.Math.min(r18, r19)
            r13 = r18
            r18 = r12
            r0 = r18
            float r0 = (float) r0
            r18 = r0
            r19 = r12
            r0 = r19
            float r0 = (float) r0
            r19 = r0
            r20 = r2
            r21 = r13
            float r20 = r20.distanceInfluenceForSnapDuration(r21)
            float r19 = r19 * r20
            float r18 = r18 + r19
            r14 = r18
            r18 = r5
            int r18 = java.lang.Math.abs(r18)
            r5 = r18
            r18 = r5
            if (r18 <= 0) goto L_0x0162
            r18 = 4
            r19 = 1148846080(0x447a0000, float:1000.0)
            r20 = r14
            r21 = r5
            r0 = r21
            float r0 = (float) r0
            r21 = r0
            float r20 = r20 / r21
            float r20 = java.lang.Math.abs(r20)
            float r19 = r19 * r20
            int r19 = java.lang.Math.round(r19)
            int r18 = r18 * r19
            r15 = r18
        L_0x0132:
            r18 = r15
            r19 = 600(0x258, float:8.41E-43)
            int r18 = java.lang.Math.min(r18, r19)
            r15 = r18
            r18 = r2
            r19 = 0
            r0 = r19
            r1 = r18
            r1.mIsScrollStarted = r0
            r18 = r2
            r0 = r18
            android.widget.Scroller r0 = r0.mScroller
            r18 = r0
            r19 = r6
            r20 = r8
            r21 = r9
            r22 = r10
            r23 = r15
            r18.startScroll(r19, r20, r21, r22, r23)
            r18 = r2
            android.support.p000v4.view.ViewCompat.postInvalidateOnAnimation(r18)
            goto L_0x0017
        L_0x0162:
            r18 = r11
            r0 = r18
            float r0 = (float) r0
            r18 = r0
            r19 = r2
            r0 = r19
            android.support.v4.view.PagerAdapter r0 = r0.mAdapter
            r19 = r0
            r20 = r2
            r0 = r20
            int r0 = r0.mCurItem
            r20 = r0
            float r19 = r19.getPageWidth(r20)
            float r18 = r18 * r19
            r16 = r18
            r18 = r9
            int r18 = java.lang.Math.abs(r18)
            r0 = r18
            float r0 = (float) r0
            r18 = r0
            r19 = r16
            r20 = r2
            r0 = r20
            int r0 = r0.mPageMargin
            r20 = r0
            r0 = r20
            float r0 = (float) r0
            r20 = r0
            float r19 = r19 + r20
            float r18 = r18 / r19
            r17 = r18
            r18 = r17
            r19 = 1065353216(0x3f800000, float:1.0)
            float r18 = r18 + r19
            r19 = 1120403456(0x42c80000, float:100.0)
            float r18 = r18 * r19
            r0 = r18
            int r0 = (int) r0
            r18 = r0
            r15 = r18
            goto L_0x0132
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.view.ViewPager.smoothScrollTo(int, int, int):void");
    }

    /* access modifiers changed from: package-private */
    public ItemInfo addNewItem(int i, int i2) {
        ItemInfo itemInfo;
        int position = i;
        int index = i2;
        new ItemInfo();
        ItemInfo ii = itemInfo;
        ii.position = position;
        ii.object = this.mAdapter.instantiateItem((ViewGroup) this, position);
        ii.widthFactor = this.mAdapter.getPageWidth(position);
        if (index < 0 || index >= this.mItems.size()) {
            boolean add = this.mItems.add(ii);
        } else {
            this.mItems.add(index, ii);
        }
        return ii;
    }

    /* access modifiers changed from: package-private */
    public void dataSetChanged() {
        int adapterCount = this.mAdapter.getCount();
        this.mExpectedAdapterCount = adapterCount;
        boolean needPopulate = this.mItems.size() < (this.mOffscreenPageLimit * 2) + 1 && this.mItems.size() < adapterCount;
        int newCurrItem = this.mCurItem;
        boolean isUpdating = false;
        int i = 0;
        while (i < this.mItems.size()) {
            ItemInfo ii = this.mItems.get(i);
            int newPos = this.mAdapter.getItemPosition(ii.object);
            if (newPos != -1) {
                if (newPos == -2) {
                    ItemInfo remove = this.mItems.remove(i);
                    i--;
                    if (!isUpdating) {
                        this.mAdapter.startUpdate((ViewGroup) this);
                        isUpdating = true;
                    }
                    this.mAdapter.destroyItem((ViewGroup) this, ii.position, ii.object);
                    needPopulate = true;
                    if (this.mCurItem == ii.position) {
                        newCurrItem = Math.max(0, Math.min(this.mCurItem, adapterCount - 1));
                        needPopulate = true;
                    }
                } else if (ii.position != newPos) {
                    if (ii.position == this.mCurItem) {
                        newCurrItem = newPos;
                    }
                    ii.position = newPos;
                    needPopulate = true;
                }
            }
            i++;
        }
        if (isUpdating) {
            this.mAdapter.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.mItems, COMPARATOR);
        if (needPopulate) {
            int i2 = getChildCount();
            for (int i3 = 0; i3 < i2; i3++) {
                LayoutParams lp = (LayoutParams) getChildAt(i3).getLayoutParams();
                if (!lp.isDecor) {
                    lp.widthFactor = 0.0f;
                }
            }
            setCurrentItemInternal(newCurrItem, false, true);
            requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void populate() {
        populate(this.mCurItem);
    }

    /* access modifiers changed from: package-private */
    public void populate(int i) {
        ItemInfo ii;
        float paddingLeft;
        float paddingRight;
        ItemInfo itemInfo;
        ItemInfo itemInfo2;
        String resName;
        Throwable th;
        StringBuilder sb;
        int newCurrentItem = i;
        ItemInfo oldCurInfo = null;
        if (this.mCurItem != newCurrentItem) {
            oldCurInfo = infoForPosition(this.mCurItem);
            this.mCurItem = newCurrentItem;
        }
        if (this.mAdapter == null) {
            sortChildDrawingOrder();
            return;
        }
        if (this.mPopulatePending) {
            sortChildDrawingOrder();
        } else if (getWindowToken() != null) {
            this.mAdapter.startUpdate((ViewGroup) this);
            int pageLimit = this.mOffscreenPageLimit;
            int startPos = Math.max(0, this.mCurItem - pageLimit);
            int N = this.mAdapter.getCount();
            int endPos = Math.min(N - 1, this.mCurItem + pageLimit);
            if (N != this.mExpectedAdapterCount) {
                try {
                    resName = getResources().getResourceName(getId());
                } catch (Resources.NotFoundException e) {
                    Resources.NotFoundException notFoundException = e;
                    resName = Integer.toHexString(getId());
                }
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ").append(this.mExpectedAdapterCount).append(", found: ").append(N).append(" Pager id: ").append(resName).append(" Pager class: ").append(getClass()).append(" Problematic adapter: ").append(this.mAdapter.getClass()).toString());
                throw th2;
            }
            ItemInfo curItem = null;
            int curIndex = 0;
            while (true) {
                if (curIndex >= this.mItems.size()) {
                    break;
                }
                ItemInfo ii2 = this.mItems.get(curIndex);
                if (ii2.position >= this.mCurItem) {
                    if (ii2.position == this.mCurItem) {
                        curItem = ii2;
                    }
                } else {
                    curIndex++;
                }
            }
            if (curItem == null && N > 0) {
                curItem = addNewItem(this.mCurItem, curIndex);
            }
            if (curItem != null) {
                float extraWidthLeft = 0.0f;
                int itemIndex = curIndex - 1;
                ItemInfo ii3 = itemIndex >= 0 ? this.mItems.get(itemIndex) : null;
                int clientWidth = getClientWidth();
                if (clientWidth <= 0) {
                    paddingLeft = 0.0f;
                } else {
                    paddingLeft = (2.0f - curItem.widthFactor) + (((float) getPaddingLeft()) / ((float) clientWidth));
                }
                float leftWidthNeeded = paddingLeft;
                for (int pos = this.mCurItem - 1; pos >= 0; pos--) {
                    if (extraWidthLeft < leftWidthNeeded || pos >= startPos) {
                        if (ii3 == null || pos != ii3.position) {
                            extraWidthLeft += addNewItem(pos, itemIndex + 1).widthFactor;
                            curIndex++;
                            ii3 = itemIndex >= 0 ? this.mItems.get(itemIndex) : null;
                        } else {
                            extraWidthLeft += ii3.widthFactor;
                            itemIndex--;
                            ii3 = itemIndex >= 0 ? this.mItems.get(itemIndex) : null;
                        }
                    } else if (ii3 == null) {
                        break;
                    } else {
                        if (pos == ii3.position && !ii3.scrolling) {
                            ItemInfo remove = this.mItems.remove(itemIndex);
                            this.mAdapter.destroyItem((ViewGroup) this, pos, ii3.object);
                            itemIndex--;
                            curIndex--;
                            if (itemIndex >= 0) {
                                itemInfo2 = this.mItems.get(itemIndex);
                            } else {
                                itemInfo2 = null;
                            }
                            ii3 = itemInfo2;
                        }
                    }
                }
                float extraWidthRight = curItem.widthFactor;
                int itemIndex2 = curIndex + 1;
                if (extraWidthRight < 2.0f) {
                    ItemInfo ii4 = itemIndex2 < this.mItems.size() ? this.mItems.get(itemIndex2) : null;
                    if (clientWidth <= 0) {
                        paddingRight = 0.0f;
                    } else {
                        paddingRight = (((float) getPaddingRight()) / ((float) clientWidth)) + 2.0f;
                    }
                    float rightWidthNeeded = paddingRight;
                    for (int pos2 = this.mCurItem + 1; pos2 < N; pos2++) {
                        if (extraWidthRight < rightWidthNeeded || pos2 <= endPos) {
                            if (ii4 == null || pos2 != ii4.position) {
                                ItemInfo ii5 = addNewItem(pos2, itemIndex2);
                                itemIndex2++;
                                extraWidthRight += ii5.widthFactor;
                                ii4 = itemIndex2 < this.mItems.size() ? this.mItems.get(itemIndex2) : null;
                            } else {
                                extraWidthRight += ii4.widthFactor;
                                itemIndex2++;
                                ii4 = itemIndex2 < this.mItems.size() ? this.mItems.get(itemIndex2) : null;
                            }
                        } else if (ii4 == null) {
                            break;
                        } else {
                            if (pos2 == ii4.position && !ii4.scrolling) {
                                ItemInfo remove2 = this.mItems.remove(itemIndex2);
                                this.mAdapter.destroyItem((ViewGroup) this, pos2, ii4.object);
                                if (itemIndex2 < this.mItems.size()) {
                                    itemInfo = this.mItems.get(itemIndex2);
                                } else {
                                    itemInfo = null;
                                }
                                ii4 = itemInfo;
                            }
                        }
                    }
                }
                calculatePageOffsets(curItem, curIndex, oldCurInfo);
                this.mAdapter.setPrimaryItem((ViewGroup) this, this.mCurItem, curItem.object);
            }
            this.mAdapter.finishUpdate((ViewGroup) this);
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View child = getChildAt(i2);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                lp.childIndex = i2;
                if (!lp.isDecor && lp.widthFactor == 0.0f && (ii = infoForChild(child)) != null) {
                    lp.widthFactor = ii.widthFactor;
                    lp.position = ii.position;
                }
            }
            sortChildDrawingOrder();
            if (hasFocus()) {
                View currentFocused = findFocus();
                ItemInfo ii6 = currentFocused != null ? infoForAnyChild(currentFocused) : null;
                if (ii6 == null || ii6.position != this.mCurItem) {
                    int i3 = 0;
                    while (i3 < getChildCount()) {
                        View child2 = getChildAt(i3);
                        ItemInfo ii7 = infoForChild(child2);
                        if (ii7 == null || ii7.position != this.mCurItem || !child2.requestFocus(2)) {
                            i3++;
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    private void sortChildDrawingOrder() {
        ArrayList<View> arrayList;
        if (this.mDrawingOrder != 0) {
            if (this.mDrawingOrderedChildren == null) {
                new ArrayList<>();
                this.mDrawingOrderedChildren = arrayList;
            } else {
                this.mDrawingOrderedChildren.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                boolean add = this.mDrawingOrderedChildren.add(getChildAt(i));
            }
            Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
        }
    }

    private void calculatePageOffsets(ItemInfo itemInfo, int i, ItemInfo itemInfo2) {
        ItemInfo ii;
        ItemInfo ii2;
        ItemInfo curItem = itemInfo;
        int curIndex = i;
        ItemInfo oldCurInfo = itemInfo2;
        int N = this.mAdapter.getCount();
        int width = getClientWidth();
        float marginOffset = width > 0 ? ((float) this.mPageMargin) / ((float) width) : 0.0f;
        if (oldCurInfo != null) {
            int oldCurPosition = oldCurInfo.position;
            if (oldCurPosition < curItem.position) {
                int itemIndex = 0;
                float offset = oldCurInfo.offset + oldCurInfo.widthFactor + marginOffset;
                int pos = oldCurPosition + 1;
                while (pos <= curItem.position && itemIndex < this.mItems.size()) {
                    Object obj = this.mItems.get(itemIndex);
                    while (true) {
                        ii2 = (ItemInfo) obj;
                        if (pos > ii2.position && itemIndex < this.mItems.size() - 1) {
                            itemIndex++;
                            obj = this.mItems.get(itemIndex);
                        }
                    }
                    while (pos < ii2.position) {
                        offset += this.mAdapter.getPageWidth(pos) + marginOffset;
                        pos++;
                    }
                    ii2.offset = offset;
                    offset += ii2.widthFactor + marginOffset;
                    pos++;
                }
            } else if (oldCurPosition > curItem.position) {
                int itemIndex2 = this.mItems.size() - 1;
                float offset2 = oldCurInfo.offset;
                int pos2 = oldCurPosition - 1;
                while (pos2 >= curItem.position && itemIndex2 >= 0) {
                    Object obj2 = this.mItems.get(itemIndex2);
                    while (true) {
                        ii = (ItemInfo) obj2;
                        if (pos2 < ii.position && itemIndex2 > 0) {
                            itemIndex2--;
                            obj2 = this.mItems.get(itemIndex2);
                        }
                    }
                    while (pos2 > ii.position) {
                        offset2 -= this.mAdapter.getPageWidth(pos2) + marginOffset;
                        pos2--;
                    }
                    offset2 -= ii.widthFactor + marginOffset;
                    ii.offset = offset2;
                    pos2--;
                }
            }
        }
        int itemCount = this.mItems.size();
        float offset3 = curItem.offset;
        int pos3 = curItem.position - 1;
        this.mFirstOffset = curItem.position == 0 ? curItem.offset : -3.4028235E38f;
        this.mLastOffset = curItem.position == N + -1 ? (curItem.offset + curItem.widthFactor) - 1.0f : Float.MAX_VALUE;
        int i2 = curIndex - 1;
        while (i2 >= 0) {
            ItemInfo ii3 = this.mItems.get(i2);
            while (pos3 > ii3.position) {
                int i3 = pos3;
                pos3--;
                offset3 -= this.mAdapter.getPageWidth(i3) + marginOffset;
            }
            offset3 -= ii3.widthFactor + marginOffset;
            ii3.offset = offset3;
            if (ii3.position == 0) {
                this.mFirstOffset = offset3;
            }
            i2--;
            pos3--;
        }
        float offset4 = curItem.offset + curItem.widthFactor + marginOffset;
        int pos4 = curItem.position + 1;
        int i4 = curIndex + 1;
        while (i4 < itemCount) {
            ItemInfo ii4 = this.mItems.get(i4);
            while (pos4 < ii4.position) {
                int i5 = pos4;
                pos4++;
                offset4 += this.mAdapter.getPageWidth(i5) + marginOffset;
            }
            if (ii4.position == N - 1) {
                this.mLastOffset = (offset4 + ii4.widthFactor) - 1.0f;
            }
            ii4.offset = offset4;
            offset4 += ii4.widthFactor + marginOffset;
            i4++;
            pos4++;
        }
        this.mNeedCalculatePageOffsets = false;
    }

    /* renamed from: android.support.v4.view.ViewPager$SavedState */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR;
        Parcelable adapterState;
        ClassLoader loader;
        int position;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SavedState(@NonNull Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel parcel, int i) {
            Parcel out = parcel;
            int flags = i;
            super.writeToParcel(out, flags);
            out.writeInt(this.position);
            out.writeParcelable(this.adapterState, flags);
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append("FragmentPager.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" position=").append(this.position).append("}").toString();
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
                r3 = r2
                if (r3 != 0) goto L_0x0016
                r3 = r0
                java.lang.Class r3 = r3.getClass()
                java.lang.ClassLoader r3 = r3.getClassLoader()
                r2 = r3
            L_0x0016:
                r3 = r0
                r4 = r1
                int r4 = r4.readInt()
                r3.position = r4
                r3 = r0
                r4 = r1
                r5 = r2
                android.os.Parcelable r4 = r4.readParcelable(r5)
                r3.adapterState = r4
                r3 = r0
                r4 = r2
                r3.loader = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.view.ViewPager.SavedState.<init>(android.os.Parcel, java.lang.ClassLoader):void");
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        new SavedState(super.onSaveInstanceState());
        SavedState ss = savedState;
        ss.position = this.mCurItem;
        if (this.mAdapter != null) {
            ss.adapterState = this.mAdapter.saveState();
        }
        return ss;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable state = parcelable;
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        if (this.mAdapter != null) {
            this.mAdapter.restoreState(ss.adapterState, ss.loader);
            setCurrentItemInternal(ss.position, false, true);
            return;
        }
        this.mRestoredCurItem = ss.position;
        this.mRestoredAdapterState = ss.adapterState;
        this.mRestoredClassLoader = ss.loader;
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        Throwable th;
        View child = view;
        int index = i;
        ViewGroup.LayoutParams params = layoutParams;
        if (!checkLayoutParams(params)) {
            params = generateLayoutParams(params);
        }
        LayoutParams lp = (LayoutParams) params;
        lp.isDecor |= isDecorView(child);
        if (!this.mInLayout) {
            super.addView(child, index, params);
        } else if (lp == null || !lp.isDecor) {
            lp.needsMeasure = true;
            boolean addViewInLayout = addViewInLayout(child, index, params);
        } else {
            Throwable th2 = th;
            new IllegalStateException("Cannot add pager decor view during layout");
            throw th2;
        }
    }

    private static boolean isDecorView(@NonNull View view) {
        return view.getClass().getAnnotation(DecorView.class) != null;
    }

    public void removeView(View view) {
        View view2 = view;
        if (this.mInLayout) {
            removeViewInLayout(view2);
        } else {
            super.removeView(view2);
        }
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForChild(View view) {
        View child = view;
        for (int i = 0; i < this.mItems.size(); i++) {
            ItemInfo ii = this.mItems.get(i);
            if (this.mAdapter.isViewFromObject(child, ii.object)) {
                return ii;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForAnyChild(View view) {
        View child = view;
        while (true) {
            ViewParent parent = child.getParent();
            ViewParent parent2 = parent;
            if (parent == this) {
                return infoForChild(child);
            }
            if (parent2 != null && (parent2 instanceof View)) {
                child = (View) parent2;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ItemInfo infoForPosition(int i) {
        int position = i;
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            ItemInfo ii = this.mItems.get(i2);
            if (ii.position == position) {
                return ii;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LayoutParams lp;
        LayoutParams lp2;
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int measuredWidth = getMeasuredWidth();
        this.mGutterSize = Math.min(measuredWidth / 10, this.mDefaultGutterSize);
        int childWidthSize = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int childHeightSize = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int size = getChildCount();
        for (int i = 0; i < size; i++) {
            View child = getChildAt(i);
            if (!(child.getVisibility() == 8 || (lp2 = (LayoutParams) child.getLayoutParams()) == null || !lp2.isDecor)) {
                int hgrav = lp2.gravity & 7;
                int vgrav = lp2.gravity & 112;
                int widthMode = Integer.MIN_VALUE;
                int heightMode = Integer.MIN_VALUE;
                boolean consumeVertical = vgrav == 48 || vgrav == 80;
                boolean consumeHorizontal = hgrav == 3 || hgrav == 5;
                if (consumeVertical) {
                    widthMode = 1073741824;
                } else if (consumeHorizontal) {
                    heightMode = 1073741824;
                }
                int widthSize = childWidthSize;
                int heightSize = childHeightSize;
                if (lp2.width != -2) {
                    widthMode = 1073741824;
                    if (lp2.width != -1) {
                        widthSize = lp2.width;
                    }
                }
                if (lp2.height != -2) {
                    heightMode = 1073741824;
                    if (lp2.height != -1) {
                        heightSize = lp2.height;
                    }
                }
                child.measure(View.MeasureSpec.makeMeasureSpec(widthSize, widthMode), View.MeasureSpec.makeMeasureSpec(heightSize, heightMode));
                if (consumeVertical) {
                    childHeightSize -= child.getMeasuredHeight();
                } else if (consumeHorizontal) {
                    childWidthSize -= child.getMeasuredWidth();
                }
            }
        }
        this.mChildWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(childWidthSize, Declaration.MODULE_REFERENCE);
        this.mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(childHeightSize, Declaration.MODULE_REFERENCE);
        this.mInLayout = true;
        populate();
        this.mInLayout = false;
        int size2 = getChildCount();
        for (int i2 = 0; i2 < size2; i2++) {
            View child2 = getChildAt(i2);
            if (child2.getVisibility() != 8 && ((lp = (LayoutParams) child2.getLayoutParams()) == null || !lp.isDecor)) {
                child2.measure(View.MeasureSpec.makeMeasureSpec((int) (((float) childWidthSize) * lp.widthFactor), Declaration.MODULE_REFERENCE), this.mChildHeightMeasureSpec);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int h, int i2, int oldh) {
        int w = i;
        int oldw = i2;
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw) {
            recomputeScrollPosition(w, oldw, this.mPageMargin, this.mPageMargin);
        }
    }

    private void recomputeScrollPosition(int i, int i2, int i3, int i4) {
        int width = i;
        int oldWidth = i2;
        int margin = i3;
        int oldMargin = i4;
        if (oldWidth <= 0 || this.mItems.isEmpty()) {
            ItemInfo ii = infoForPosition(this.mCurItem);
            int scrollPos = (int) ((ii != null ? Math.min(ii.offset, this.mLastOffset) : 0.0f) * ((float) ((width - getPaddingLeft()) - getPaddingRight())));
            if (scrollPos != getScrollX()) {
                completeScroll(false);
                scrollTo(scrollPos, getScrollY());
            }
        } else if (!this.mScroller.isFinished()) {
            this.mScroller.setFinalX(getCurrentItem() * getClientWidth());
        } else {
            scrollTo((int) ((((float) getScrollX()) / ((float) (((oldWidth - getPaddingLeft()) - getPaddingRight()) + oldMargin))) * ((float) (((width - getPaddingLeft()) - getPaddingRight()) + margin))), getScrollY());
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int l, int t, int r, int b) {
        int childLeft;
        int childTop;
        boolean z2 = z;
        int count = getChildCount();
        int width = r - l;
        int height = b - t;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int decorCount = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.isDecor) {
                    int hgrav = lp.gravity & 7;
                    int vgrav = lp.gravity & 112;
                    switch (hgrav) {
                        case 1:
                            childLeft = Math.max((width - child.getMeasuredWidth()) / 2, paddingLeft);
                            break;
                        case 3:
                            childLeft = paddingLeft;
                            paddingLeft += child.getMeasuredWidth();
                            break;
                        case 5:
                            childLeft = (width - paddingRight) - child.getMeasuredWidth();
                            paddingRight += child.getMeasuredWidth();
                            break;
                        default:
                            childLeft = paddingLeft;
                            break;
                    }
                    switch (vgrav) {
                        case 16:
                            childTop = Math.max((height - child.getMeasuredHeight()) / 2, paddingTop);
                            break;
                        case 48:
                            childTop = paddingTop;
                            paddingTop += child.getMeasuredHeight();
                            break;
                        case 80:
                            childTop = (height - paddingBottom) - child.getMeasuredHeight();
                            paddingBottom += child.getMeasuredHeight();
                            break;
                        default:
                            childTop = paddingTop;
                            break;
                    }
                    int childLeft2 = childLeft + scrollX;
                    child.layout(childLeft2, childTop, childLeft2 + child.getMeasuredWidth(), childTop + child.getMeasuredHeight());
                    decorCount++;
                }
            }
        }
        int i2 = (width - paddingLeft) - paddingRight;
        for (int i3 = 0; i3 < count; i3++) {
            View child2 = getChildAt(i3);
            if (child2.getVisibility() != 8) {
                LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
                if (!lp2.isDecor) {
                    ItemInfo infoForChild = infoForChild(child2);
                    ItemInfo ii = infoForChild;
                    if (infoForChild != null) {
                        int childLeft3 = paddingLeft + ((int) (((float) i2) * ii.offset));
                        int childTop2 = paddingTop;
                        if (lp2.needsMeasure) {
                            lp2.needsMeasure = false;
                            child2.measure(View.MeasureSpec.makeMeasureSpec((int) (((float) i2) * lp2.widthFactor), Declaration.MODULE_REFERENCE), View.MeasureSpec.makeMeasureSpec((height - paddingTop) - paddingBottom, Declaration.MODULE_REFERENCE));
                        }
                        child2.layout(childLeft3, childTop2, childLeft3 + child2.getMeasuredWidth(), childTop2 + child2.getMeasuredHeight());
                    }
                }
            }
        }
        this.mTopPageBounds = paddingTop;
        this.mBottomPageBounds = height - paddingBottom;
        this.mDecorChildCount = decorCount;
        if (this.mFirstLayout) {
            scrollToItem(this.mCurItem, false, 0, false);
        }
        this.mFirstLayout = false;
    }

    public void computeScroll() {
        this.mIsScrollStarted = true;
        if (this.mScroller.isFinished() || !this.mScroller.computeScrollOffset()) {
            completeScroll(true);
            return;
        }
        int oldX = getScrollX();
        int oldY = getScrollY();
        int x = this.mScroller.getCurrX();
        int y = this.mScroller.getCurrY();
        if (!(oldX == x && oldY == y)) {
            scrollTo(x, y);
            if (!pageScrolled(x)) {
                this.mScroller.abortAnimation();
                scrollTo(0, y);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private boolean pageScrolled(int i) {
        Throwable th;
        Throwable th2;
        int xpos = i;
        if (this.mItems.size() != 0) {
            ItemInfo ii = infoForCurrentScrollPosition();
            int width = getClientWidth();
            int widthWithMargin = width + this.mPageMargin;
            float marginOffset = ((float) this.mPageMargin) / ((float) width);
            int currentPage = ii.position;
            float pageOffset = ((((float) xpos) / ((float) width)) - ii.offset) / (ii.widthFactor + marginOffset);
            this.mCalledSuper = false;
            onPageScrolled(currentPage, pageOffset, (int) (pageOffset * ((float) widthWithMargin)));
            if (this.mCalledSuper) {
                return true;
            }
            Throwable th3 = th;
            new IllegalStateException("onPageScrolled did not call superclass implementation");
            throw th3;
        } else if (this.mFirstLayout) {
            return false;
        } else {
            this.mCalledSuper = false;
            onPageScrolled(0, 0.0f, 0);
            if (this.mCalledSuper) {
                return false;
            }
            Throwable th4 = th2;
            new IllegalStateException("onPageScrolled did not call superclass implementation");
            throw th4;
        }
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onPageScrolled(int i, float f, int i2) {
        int childLeft;
        int position = i;
        float offset = f;
        int offsetPixels = i2;
        if (this.mDecorChildCount > 0) {
            int scrollX = getScrollX();
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int width = getWidth();
            int childCount = getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View child = getChildAt(i3);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.isDecor) {
                    switch (lp.gravity & 7) {
                        case 1:
                            childLeft = Math.max((width - child.getMeasuredWidth()) / 2, paddingLeft);
                            break;
                        case 3:
                            childLeft = paddingLeft;
                            paddingLeft += child.getWidth();
                            break;
                        case 5:
                            childLeft = (width - paddingRight) - child.getMeasuredWidth();
                            paddingRight += child.getMeasuredWidth();
                            break;
                        default:
                            childLeft = paddingLeft;
                            break;
                    }
                    int childOffset = (childLeft + scrollX) - child.getLeft();
                    if (childOffset != 0) {
                        child.offsetLeftAndRight(childOffset);
                    }
                }
            }
        }
        dispatchOnPageScrolled(position, offset, offsetPixels);
        if (this.mPageTransformer != null) {
            int scrollX2 = getScrollX();
            int childCount2 = getChildCount();
            for (int i4 = 0; i4 < childCount2; i4++) {
                View child2 = getChildAt(i4);
                if (!((LayoutParams) child2.getLayoutParams()).isDecor) {
                    this.mPageTransformer.transformPage(child2, ((float) (child2.getLeft() - scrollX2)) / ((float) getClientWidth()));
                }
            }
        }
        this.mCalledSuper = true;
    }

    private void dispatchOnPageScrolled(int i, float f, int i2) {
        int position = i;
        float offset = f;
        int offsetPixels = i2;
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrolled(position, offset, offsetPixels);
        }
        if (this.mOnPageChangeListeners != null) {
            int z = this.mOnPageChangeListeners.size();
            for (int i3 = 0; i3 < z; i3++) {
                OnPageChangeListener listener = this.mOnPageChangeListeners.get(i3);
                if (listener != null) {
                    listener.onPageScrolled(position, offset, offsetPixels);
                }
            }
        }
        if (this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageScrolled(position, offset, offsetPixels);
        }
    }

    private void dispatchOnPageSelected(int i) {
        int position = i;
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageSelected(position);
        }
        if (this.mOnPageChangeListeners != null) {
            int z = this.mOnPageChangeListeners.size();
            for (int i2 = 0; i2 < z; i2++) {
                OnPageChangeListener listener = this.mOnPageChangeListeners.get(i2);
                if (listener != null) {
                    listener.onPageSelected(position);
                }
            }
        }
        if (this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageSelected(position);
        }
    }

    private void dispatchOnScrollStateChanged(int i) {
        int state = i;
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrollStateChanged(state);
        }
        if (this.mOnPageChangeListeners != null) {
            int z = this.mOnPageChangeListeners.size();
            for (int i2 = 0; i2 < z; i2++) {
                OnPageChangeListener listener = this.mOnPageChangeListeners.get(i2);
                if (listener != null) {
                    listener.onPageScrollStateChanged(state);
                }
            }
        }
        if (this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageScrollStateChanged(state);
        }
    }

    private void completeScroll(boolean z) {
        boolean postEvents = z;
        boolean needPopulate = this.mScrollState == 2;
        if (needPopulate) {
            setScrollingCacheEnabled(false);
            if (!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
                int oldX = getScrollX();
                int oldY = getScrollY();
                int x = this.mScroller.getCurrX();
                int y = this.mScroller.getCurrY();
                if (!(oldX == x && oldY == y)) {
                    scrollTo(x, y);
                    if (x != oldX) {
                        boolean pageScrolled = pageScrolled(x);
                    }
                }
            }
        }
        this.mPopulatePending = false;
        for (int i = 0; i < this.mItems.size(); i++) {
            ItemInfo ii = this.mItems.get(i);
            if (ii.scrolling) {
                needPopulate = true;
                ii.scrolling = false;
            }
        }
        if (!needPopulate) {
            return;
        }
        if (postEvents) {
            ViewCompat.postOnAnimation(this, this.mEndScrollRunnable);
        } else {
            this.mEndScrollRunnable.run();
        }
    }

    private boolean isGutterDrag(float f, float f2) {
        float x = f;
        float dx = f2;
        return (x < ((float) this.mGutterSize) && dx > 0.0f) || (x > ((float) (getWidth() - this.mGutterSize)) && dx < 0.0f);
    }

    private void enableLayers(boolean z) {
        boolean enable = z;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setLayerType(enable ? this.mPageTransformerLayerType : 0, (Paint) null);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        int action = ev.getAction() & 255;
        if (action == 3 || action == 1) {
            boolean resetTouch = resetTouch();
            return false;
        }
        if (action != 0) {
            if (this.mIsBeingDragged) {
                return true;
            }
            if (this.mIsUnableToDrag) {
                return false;
            }
        }
        switch (action) {
            case 0:
                float x = ev.getX();
                this.mInitialMotionX = x;
                this.mLastMotionX = x;
                float y = ev.getY();
                this.mInitialMotionY = y;
                this.mLastMotionY = y;
                this.mActivePointerId = ev.getPointerId(0);
                this.mIsUnableToDrag = false;
                this.mIsScrollStarted = true;
                boolean computeScrollOffset = this.mScroller.computeScrollOffset();
                if (this.mScrollState == 2 && Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) > this.mCloseEnough) {
                    this.mScroller.abortAnimation();
                    this.mPopulatePending = false;
                    populate();
                    this.mIsBeingDragged = true;
                    requestParentDisallowInterceptTouchEvent(true);
                    setScrollState(1);
                    break;
                } else {
                    completeScroll(false);
                    this.mIsBeingDragged = false;
                    break;
                }
            case 2:
                int activePointerId = this.mActivePointerId;
                if (activePointerId != -1) {
                    int pointerIndex = ev.findPointerIndex(activePointerId);
                    float x2 = ev.getX(pointerIndex);
                    float dx = x2 - this.mLastMotionX;
                    float xDiff = Math.abs(dx);
                    float y2 = ev.getY(pointerIndex);
                    float yDiff = Math.abs(y2 - this.mInitialMotionY);
                    if (dx == 0.0f || isGutterDrag(this.mLastMotionX, dx) || !canScroll(this, false, (int) dx, (int) x2, (int) y2)) {
                        if (xDiff > ((float) this.mTouchSlop) && xDiff * 0.5f > yDiff) {
                            this.mIsBeingDragged = true;
                            requestParentDisallowInterceptTouchEvent(true);
                            setScrollState(1);
                            this.mLastMotionX = dx > 0.0f ? this.mInitialMotionX + ((float) this.mTouchSlop) : this.mInitialMotionX - ((float) this.mTouchSlop);
                            this.mLastMotionY = y2;
                            setScrollingCacheEnabled(true);
                        } else if (yDiff > ((float) this.mTouchSlop)) {
                            this.mIsUnableToDrag = true;
                        }
                        if (this.mIsBeingDragged && performDrag(x2)) {
                            ViewCompat.postInvalidateOnAnimation(this);
                            break;
                        }
                    } else {
                        this.mLastMotionX = x2;
                        this.mLastMotionY = y2;
                        this.mIsUnableToDrag = true;
                        return false;
                    }
                }
                break;
            case 6:
                onSecondaryPointerUp(ev);
                break;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(ev);
        return this.mIsBeingDragged;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float f;
        MotionEvent ev = motionEvent;
        if (this.mFakeDragging) {
            return true;
        }
        if (ev.getAction() == 0 && ev.getEdgeFlags() != 0) {
            return false;
        }
        if (this.mAdapter != null) {
            if (this.mAdapter.getCount() != 0) {
                if (this.mVelocityTracker == null) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                }
                this.mVelocityTracker.addMovement(ev);
                boolean needsInvalidate = false;
                switch (ev.getAction() & 255) {
                    case 0:
                        this.mScroller.abortAnimation();
                        this.mPopulatePending = false;
                        populate();
                        float x = ev.getX();
                        this.mInitialMotionX = x;
                        this.mLastMotionX = x;
                        float y = ev.getY();
                        this.mInitialMotionY = y;
                        this.mLastMotionY = y;
                        this.mActivePointerId = ev.getPointerId(0);
                        break;
                    case 1:
                        if (this.mIsBeingDragged) {
                            VelocityTracker velocityTracker = this.mVelocityTracker;
                            velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
                            int initialVelocity = (int) velocityTracker.getXVelocity(this.mActivePointerId);
                            this.mPopulatePending = true;
                            int width = getClientWidth();
                            int scrollX = getScrollX();
                            ItemInfo ii = infoForCurrentScrollPosition();
                            setCurrentItemInternal(determineTargetPage(ii.position, ((((float) scrollX) / ((float) width)) - ii.offset) / (ii.widthFactor + (((float) this.mPageMargin) / ((float) width))), initialVelocity, (int) (ev.getX(ev.findPointerIndex(this.mActivePointerId)) - this.mInitialMotionX)), true, true, initialVelocity);
                            needsInvalidate = resetTouch();
                            break;
                        }
                        break;
                    case 2:
                        if (!this.mIsBeingDragged) {
                            int pointerIndex = ev.findPointerIndex(this.mActivePointerId);
                            if (pointerIndex == -1) {
                                needsInvalidate = resetTouch();
                                break;
                            } else {
                                float x2 = ev.getX(pointerIndex);
                                float xDiff = Math.abs(x2 - this.mLastMotionX);
                                float y2 = ev.getY(pointerIndex);
                                float yDiff = Math.abs(y2 - this.mLastMotionY);
                                if (xDiff > ((float) this.mTouchSlop) && xDiff > yDiff) {
                                    this.mIsBeingDragged = true;
                                    requestParentDisallowInterceptTouchEvent(true);
                                    if (x2 - this.mInitialMotionX > 0.0f) {
                                        f = this.mInitialMotionX + ((float) this.mTouchSlop);
                                    } else {
                                        f = this.mInitialMotionX - ((float) this.mTouchSlop);
                                    }
                                    this.mLastMotionX = f;
                                    this.mLastMotionY = y2;
                                    setScrollState(1);
                                    setScrollingCacheEnabled(true);
                                    ViewParent parent = getParent();
                                    if (parent != null) {
                                        parent.requestDisallowInterceptTouchEvent(true);
                                    }
                                }
                            }
                        }
                        if (this.mIsBeingDragged) {
                            needsInvalidate = false | performDrag(ev.getX(ev.findPointerIndex(this.mActivePointerId)));
                            break;
                        }
                        break;
                    case 3:
                        if (this.mIsBeingDragged) {
                            scrollToItem(this.mCurItem, true, 0, false);
                            needsInvalidate = resetTouch();
                            break;
                        }
                        break;
                    case 5:
                        int index = ev.getActionIndex();
                        this.mLastMotionX = ev.getX(index);
                        this.mActivePointerId = ev.getPointerId(index);
                        break;
                    case 6:
                        onSecondaryPointerUp(ev);
                        this.mLastMotionX = ev.getX(ev.findPointerIndex(this.mActivePointerId));
                        break;
                }
                if (needsInvalidate) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                return true;
            }
        }
        return false;
    }

    private boolean resetTouch() {
        this.mActivePointerId = -1;
        endDrag();
        this.mLeftEdge.onRelease();
        this.mRightEdge.onRelease();
        return this.mLeftEdge.isFinished() || this.mRightEdge.isFinished();
    }

    private void requestParentDisallowInterceptTouchEvent(boolean z) {
        boolean disallowIntercept = z;
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }

    private boolean performDrag(float f) {
        float x = f;
        boolean needsInvalidate = false;
        float deltaX = this.mLastMotionX - x;
        this.mLastMotionX = x;
        float scrollX = ((float) getScrollX()) + deltaX;
        int width = getClientWidth();
        float leftBound = ((float) width) * this.mFirstOffset;
        float rightBound = ((float) width) * this.mLastOffset;
        boolean leftAbsolute = true;
        boolean rightAbsolute = true;
        ItemInfo firstItem = this.mItems.get(0);
        ItemInfo lastItem = this.mItems.get(this.mItems.size() - 1);
        if (firstItem.position != 0) {
            leftAbsolute = false;
            leftBound = firstItem.offset * ((float) width);
        }
        if (lastItem.position != this.mAdapter.getCount() - 1) {
            rightAbsolute = false;
            rightBound = lastItem.offset * ((float) width);
        }
        if (scrollX < leftBound) {
            if (leftAbsolute) {
                this.mLeftEdge.onPull(Math.abs(leftBound - scrollX) / ((float) width));
                needsInvalidate = true;
            }
            scrollX = leftBound;
        } else if (scrollX > rightBound) {
            if (rightAbsolute) {
                this.mRightEdge.onPull(Math.abs(scrollX - rightBound) / ((float) width));
                needsInvalidate = true;
            }
            scrollX = rightBound;
        }
        this.mLastMotionX += scrollX - ((float) ((int) scrollX));
        scrollTo((int) scrollX, getScrollY());
        boolean pageScrolled = pageScrolled((int) scrollX);
        return needsInvalidate;
    }

    private ItemInfo infoForCurrentScrollPosition() {
        ItemInfo ii;
        int width = getClientWidth();
        float scrollOffset = width > 0 ? ((float) getScrollX()) / ((float) width) : 0.0f;
        float marginOffset = width > 0 ? ((float) this.mPageMargin) / ((float) width) : 0.0f;
        int lastPos = -1;
        float lastOffset = 0.0f;
        float lastWidth = 0.0f;
        boolean first = true;
        ItemInfo lastItem = null;
        int i = 0;
        while (true) {
            if (i >= this.mItems.size()) {
                return lastItem;
            }
            ii = this.mItems.get(i);
            if (!first && ii.position != lastPos + 1) {
                ii = this.mTempItem;
                ii.offset = lastOffset + lastWidth + marginOffset;
                ii.position = lastPos + 1;
                ii.widthFactor = this.mAdapter.getPageWidth(ii.position);
                i--;
            }
            float offset = ii.offset;
            float leftBound = offset;
            float rightBound = offset + ii.widthFactor + marginOffset;
            if (first || scrollOffset >= leftBound) {
                if (scrollOffset < rightBound) {
                    break;
                }
                if (i == this.mItems.size() - 1) {
                    break;
                }
                first = false;
                lastPos = ii.position;
                lastOffset = offset;
                lastWidth = ii.widthFactor;
                lastItem = ii;
                i++;
            } else {
                return lastItem;
            }
        }
        return ii;
    }

    private int determineTargetPage(int i, float f, int i2, int deltaX) {
        int targetPage;
        int currentPage = i;
        float pageOffset = f;
        int velocity = i2;
        if (Math.abs(deltaX) <= this.mFlingDistance || Math.abs(velocity) <= this.mMinimumVelocity) {
            targetPage = currentPage + ((int) (pageOffset + (currentPage >= this.mCurItem ? 0.4f : 0.6f)));
        } else {
            targetPage = velocity > 0 ? currentPage : currentPage + 1;
        }
        if (this.mItems.size() > 0) {
            targetPage = Math.max(this.mItems.get(0).position, Math.min(targetPage, this.mItems.get(this.mItems.size() - 1).position));
        }
        return targetPage;
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        super.draw(canvas2);
        boolean needsInvalidate = false;
        int overScrollMode = getOverScrollMode();
        if (overScrollMode == 0 || (overScrollMode == 1 && this.mAdapter != null && this.mAdapter.getCount() > 1)) {
            if (!this.mLeftEdge.isFinished()) {
                int restoreCount = canvas2.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas2.rotate(270.0f);
                canvas2.translate((float) ((-height) + getPaddingTop()), this.mFirstOffset * ((float) width));
                this.mLeftEdge.setSize(height, width);
                needsInvalidate = false | this.mLeftEdge.draw(canvas2);
                canvas2.restoreToCount(restoreCount);
            }
            if (!this.mRightEdge.isFinished()) {
                int restoreCount2 = canvas2.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas2.rotate(90.0f);
                canvas2.translate((float) (-getPaddingTop()), (-(this.mLastOffset + 1.0f)) * ((float) width2));
                this.mRightEdge.setSize(height2, width2);
                needsInvalidate |= this.mRightEdge.draw(canvas2);
                canvas2.restoreToCount(restoreCount2);
            }
        } else {
            this.mLeftEdge.finish();
            this.mRightEdge.finish();
        }
        if (needsInvalidate) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float drawAt;
        Canvas canvas2 = canvas;
        super.onDraw(canvas2);
        if (this.mPageMargin > 0 && this.mMarginDrawable != null && this.mItems.size() > 0 && this.mAdapter != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float marginOffset = ((float) this.mPageMargin) / ((float) width);
            int itemIndex = 0;
            ItemInfo ii = this.mItems.get(0);
            float offset = ii.offset;
            int itemCount = this.mItems.size();
            int firstPos = ii.position;
            int lastPos = this.mItems.get(itemCount - 1).position;
            int pos = firstPos;
            while (pos < lastPos) {
                while (pos > ii.position && itemIndex < itemCount) {
                    itemIndex++;
                    ii = this.mItems.get(itemIndex);
                }
                if (pos == ii.position) {
                    drawAt = (ii.offset + ii.widthFactor) * ((float) width);
                    offset = ii.offset + ii.widthFactor + marginOffset;
                } else {
                    float widthFactor = this.mAdapter.getPageWidth(pos);
                    drawAt = (offset + widthFactor) * ((float) width);
                    offset += widthFactor + marginOffset;
                }
                if (drawAt + ((float) this.mPageMargin) > ((float) scrollX)) {
                    this.mMarginDrawable.setBounds(Math.round(drawAt), this.mTopPageBounds, Math.round(drawAt + ((float) this.mPageMargin)), this.mBottomPageBounds);
                    this.mMarginDrawable.draw(canvas2);
                }
                if (drawAt <= ((float) (scrollX + width))) {
                    pos++;
                } else {
                    return;
                }
            }
        }
    }

    public boolean beginFakeDrag() {
        if (this.mIsBeingDragged) {
            return false;
        }
        this.mFakeDragging = true;
        setScrollState(1);
        this.mLastMotionX = 0.0f;
        this.mInitialMotionX = 0.0f;
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            this.mVelocityTracker.clear();
        }
        long time = SystemClock.uptimeMillis();
        MotionEvent ev = MotionEvent.obtain(time, time, 0, 0.0f, 0.0f, 0);
        this.mVelocityTracker.addMovement(ev);
        ev.recycle();
        this.mFakeDragBeginTime = time;
        return true;
    }

    public void endFakeDrag() {
        Throwable th;
        if (!this.mFakeDragging) {
            Throwable th2 = th;
            new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
            throw th2;
        }
        if (this.mAdapter != null) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
            int initialVelocity = (int) velocityTracker.getXVelocity(this.mActivePointerId);
            this.mPopulatePending = true;
            int width = getClientWidth();
            int scrollX = getScrollX();
            ItemInfo ii = infoForCurrentScrollPosition();
            setCurrentItemInternal(determineTargetPage(ii.position, ((((float) scrollX) / ((float) width)) - ii.offset) / ii.widthFactor, initialVelocity, (int) (this.mLastMotionX - this.mInitialMotionX)), true, true, initialVelocity);
        }
        endDrag();
        this.mFakeDragging = false;
    }

    public void fakeDragBy(float f) {
        Throwable th;
        float xOffset = f;
        if (!this.mFakeDragging) {
            Throwable th2 = th;
            new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
            throw th2;
        } else if (this.mAdapter != null) {
            this.mLastMotionX += xOffset;
            float scrollX = ((float) getScrollX()) - xOffset;
            int width = getClientWidth();
            float leftBound = ((float) width) * this.mFirstOffset;
            float rightBound = ((float) width) * this.mLastOffset;
            ItemInfo firstItem = this.mItems.get(0);
            ItemInfo lastItem = this.mItems.get(this.mItems.size() - 1);
            if (firstItem.position != 0) {
                leftBound = firstItem.offset * ((float) width);
            }
            if (lastItem.position != this.mAdapter.getCount() - 1) {
                rightBound = lastItem.offset * ((float) width);
            }
            if (scrollX < leftBound) {
                scrollX = leftBound;
            } else if (scrollX > rightBound) {
                scrollX = rightBound;
            }
            this.mLastMotionX += scrollX - ((float) ((int) scrollX));
            scrollTo((int) scrollX, getScrollY());
            boolean pageScrolled = pageScrolled((int) scrollX);
            MotionEvent ev = MotionEvent.obtain(this.mFakeDragBeginTime, SystemClock.uptimeMillis(), 2, this.mLastMotionX, 0.0f, 0);
            this.mVelocityTracker.addMovement(ev);
            ev.recycle();
        }
    }

    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        int pointerIndex = ev.getActionIndex();
        if (ev.getPointerId(pointerIndex) == this.mActivePointerId) {
            int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            this.mLastMotionX = ev.getX(newPointerIndex);
            this.mActivePointerId = ev.getPointerId(newPointerIndex);
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.clear();
            }
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        boolean enabled = z;
        if (this.mScrollingCacheEnabled != enabled) {
            this.mScrollingCacheEnabled = enabled;
        }
    }

    public boolean canScrollHorizontally(int i) {
        int direction = i;
        if (this.mAdapter == null) {
            return false;
        }
        int width = getClientWidth();
        int scrollX = getScrollX();
        if (direction < 0) {
            return scrollX > ((int) (((float) width) * this.mFirstOffset));
        } else if (direction <= 0) {
            return false;
        } else {
            return scrollX < ((int) (((float) width) * this.mLastOffset));
        }
    }

    /* access modifiers changed from: protected */
    public boolean canScroll(View view, boolean z, int i, int i2, int i3) {
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
        return checkV && v.canScrollHorizontally(-dx);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        KeyEvent event = keyEvent;
        return super.dispatchKeyEvent(event) || executeKeyEvent(event);
    }

    public boolean executeKeyEvent(@NonNull KeyEvent keyEvent) {
        KeyEvent event = keyEvent;
        boolean handled = false;
        if (event.getAction() == 0) {
            switch (event.getKeyCode()) {
                case 21:
                    if (!event.hasModifiers(2)) {
                        handled = arrowScroll(17);
                        break;
                    } else {
                        handled = pageLeft();
                        break;
                    }
                case 22:
                    if (!event.hasModifiers(2)) {
                        handled = arrowScroll(66);
                        break;
                    } else {
                        handled = pageRight();
                        break;
                    }
                case 61:
                    if (!event.hasNoModifiers()) {
                        if (event.hasModifiers(1)) {
                            handled = arrowScroll(1);
                            break;
                        }
                    } else {
                        handled = arrowScroll(2);
                        break;
                    }
                    break;
            }
        }
        return handled;
    }

    public boolean arrowScroll(int i) {
        StringBuilder sb;
        StringBuilder sb2;
        int direction = i;
        View currentFocused = findFocus();
        if (currentFocused == this) {
            currentFocused = null;
        } else if (currentFocused != null) {
            boolean isChild = false;
            ViewParent parent = currentFocused.getParent();
            while (true) {
                ViewParent parent2 = parent;
                if (!(parent2 instanceof ViewGroup)) {
                    break;
                } else if (parent2 == this) {
                    isChild = true;
                    break;
                } else {
                    parent = parent2.getParent();
                }
            }
            if (!isChild) {
                new StringBuilder();
                StringBuilder sb3 = sb;
                StringBuilder append = sb3.append(currentFocused.getClass().getSimpleName());
                ViewParent parent3 = currentFocused.getParent();
                while (true) {
                    ViewParent parent4 = parent3;
                    if (!(parent4 instanceof ViewGroup)) {
                        break;
                    }
                    StringBuilder append2 = sb3.append(" => ").append(parent4.getClass().getSimpleName());
                    parent3 = parent4.getParent();
                }
                new StringBuilder();
                int e = Log.e(TAG, sb2.append("arrowScroll tried to find focus based on non-child current focused view ").append(sb3.toString()).toString());
                currentFocused = null;
            }
        }
        boolean handled = false;
        View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, direction);
        if (nextFocused == null || nextFocused == currentFocused) {
            if (direction == 17 || direction == 1) {
                handled = pageLeft();
            } else if (direction == 66 || direction == 2) {
                handled = pageRight();
            }
        } else if (direction == 17) {
            handled = (currentFocused == null || getChildRectInPagerCoordinates(this.mTempRect, nextFocused).left < getChildRectInPagerCoordinates(this.mTempRect, currentFocused).left) ? nextFocused.requestFocus() : pageLeft();
        } else if (direction == 66) {
            handled = (currentFocused == null || getChildRectInPagerCoordinates(this.mTempRect, nextFocused).left > getChildRectInPagerCoordinates(this.mTempRect, currentFocused).left) ? nextFocused.requestFocus() : pageRight();
        }
        if (handled) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(direction));
        }
        return handled;
    }

    private Rect getChildRectInPagerCoordinates(Rect rect, View view) {
        Rect rect2;
        Rect outRect = rect;
        View child = view;
        if (outRect == null) {
            new Rect();
            outRect = rect2;
        }
        if (child == null) {
            outRect.set(0, 0, 0, 0);
            return outRect;
        }
        outRect.left = child.getLeft();
        outRect.right = child.getRight();
        outRect.top = child.getTop();
        outRect.bottom = child.getBottom();
        ViewParent parent = child.getParent();
        while (true) {
            ViewParent parent2 = parent;
            if ((parent2 instanceof ViewGroup) && parent2 != this) {
                ViewGroup group = (ViewGroup) parent2;
                outRect.left += group.getLeft();
                outRect.right += group.getRight();
                outRect.top += group.getTop();
                outRect.bottom += group.getBottom();
                parent = group.getParent();
            }
        }
        return outRect;
    }

    /* access modifiers changed from: package-private */
    public boolean pageLeft() {
        if (this.mCurItem <= 0) {
            return false;
        }
        setCurrentItem(this.mCurItem - 1, true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean pageRight() {
        if (this.mAdapter == null || this.mCurItem >= this.mAdapter.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.mCurItem + 1, true);
        return true;
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        ItemInfo ii;
        ArrayList<View> views = arrayList;
        int direction = i;
        int focusableMode = i2;
        int focusableCount = views.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View child = getChildAt(i3);
                if (child.getVisibility() == 0 && (ii = infoForChild(child)) != null && ii.position == this.mCurItem) {
                    child.addFocusables(views, direction, focusableMode);
                }
            }
        }
        if ((descendantFocusability == 262144 && focusableCount != views.size()) || !isFocusable()) {
            return;
        }
        if (((focusableMode & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && views != null) {
            boolean add = views.add(this);
        }
    }

    public void addTouchables(ArrayList<View> arrayList) {
        ItemInfo ii;
        ArrayList<View> views = arrayList;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0 && (ii = infoForChild(child)) != null && ii.position == this.mCurItem) {
                child.addTouchables(views);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        int index;
        int increment;
        int end;
        ItemInfo ii;
        int direction = i;
        Rect previouslyFocusedRect = rect;
        int count = getChildCount();
        if ((direction & 2) != 0) {
            index = 0;
            increment = 1;
            end = count;
        } else {
            index = count - 1;
            increment = -1;
            end = -1;
        }
        int i2 = index;
        while (true) {
            int i3 = i2;
            if (i3 == end) {
                return false;
            }
            View child = getChildAt(i3);
            if (child.getVisibility() == 0 && (ii = infoForChild(child)) != null && ii.position == this.mCurItem && child.requestFocus(direction, previouslyFocusedRect)) {
                return true;
            }
            i2 = i3 + increment;
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        ItemInfo ii;
        AccessibilityEvent event = accessibilityEvent;
        if (event.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(event);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0 && (ii = infoForChild(child)) != null && ii.position == this.mCurItem && child.dispatchPopulateAccessibilityEvent(event)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        ViewGroup.LayoutParams layoutParams;
        new LayoutParams();
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        ViewGroup.LayoutParams layoutParams2 = layoutParams;
        return generateDefaultLayoutParams();
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

    /* renamed from: android.support.v4.view.ViewPager$MyAccessibilityDelegate */
    class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
        final /* synthetic */ ViewPager this$0;

        MyAccessibilityDelegate(ViewPager this$02) {
            this.this$0 = this$02;
        }

        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent accessibilityEvent) {
            AccessibilityEvent event = accessibilityEvent;
            super.onInitializeAccessibilityEvent(host, event);
            event.setClassName(ViewPager.class.getName());
            event.setScrollable(canScroll());
            if (event.getEventType() == 4096 && this.this$0.mAdapter != null) {
                event.setItemCount(this.this$0.mAdapter.getCount());
                event.setFromIndex(this.this$0.mCurItem);
                event.setToIndex(this.this$0.mCurItem);
            }
        }

        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            AccessibilityNodeInfoCompat info = accessibilityNodeInfoCompat;
            super.onInitializeAccessibilityNodeInfo(host, info);
            info.setClassName(ViewPager.class.getName());
            info.setScrollable(canScroll());
            if (this.this$0.canScrollHorizontally(1)) {
                info.addAction(4096);
            }
            if (this.this$0.canScrollHorizontally(-1)) {
                info.addAction(8192);
            }
        }

        public boolean performAccessibilityAction(View host, int i, Bundle args) {
            int action = i;
            if (super.performAccessibilityAction(host, action, args)) {
                return true;
            }
            switch (action) {
                case 4096:
                    if (!this.this$0.canScrollHorizontally(1)) {
                        return false;
                    }
                    this.this$0.setCurrentItem(this.this$0.mCurItem + 1);
                    return true;
                case 8192:
                    if (!this.this$0.canScrollHorizontally(-1)) {
                        return false;
                    }
                    this.this$0.setCurrentItem(this.this$0.mCurItem - 1);
                    return true;
                default:
                    return false;
            }
        }

        private boolean canScroll() {
            return this.this$0.mAdapter != null && this.this$0.mAdapter.getCount() > 1;
        }
    }

    /* renamed from: android.support.v4.view.ViewPager$PagerObserver */
    private class PagerObserver extends DataSetObserver {
        final /* synthetic */ ViewPager this$0;

        PagerObserver(ViewPager viewPager) {
            this.this$0 = viewPager;
        }

        public void onChanged() {
            this.this$0.dataSetChanged();
        }

        public void onInvalidated() {
            this.this$0.dataSetChanged();
        }
    }

    /* renamed from: android.support.v4.view.ViewPager$LayoutParams */
    public static class LayoutParams extends ViewGroup.LayoutParams {
        int childIndex;
        public int gravity;
        public boolean isDecor;
        boolean needsMeasure;
        int position;
        float widthFactor = 0.0f;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams() {
            super(-1, -1);
        }

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
                r4.widthFactor = r5
                r4 = r1
                r5 = r2
                int[] r6 = android.support.p000v4.view.ViewPager.LAYOUT_ATTRS
                android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r6)
                r3 = r4
                r4 = r0
                r5 = r3
                r6 = 0
                r7 = 48
                int r5 = r5.getInteger(r6, r7)
                r4.gravity = r5
                r4 = r3
                r4.recycle()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.view.ViewPager.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void");
        }
    }

    /* renamed from: android.support.v4.view.ViewPager$ViewPositionComparator */
    static class ViewPositionComparator implements Comparator<View> {
        ViewPositionComparator() {
        }

        public int compare(View lhs, View rhs) {
            LayoutParams llp = (LayoutParams) lhs.getLayoutParams();
            LayoutParams rlp = (LayoutParams) rhs.getLayoutParams();
            if (llp.isDecor == rlp.isDecor) {
                return llp.position - rlp.position;
            }
            return llp.isDecor ? 1 : -1;
        }
    }
}
