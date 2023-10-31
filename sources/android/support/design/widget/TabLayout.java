package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.BoolRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.design.C0064R;
import android.support.design.animation.AnimationUtils;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.util.Pools;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.MarginLayoutParamsCompat;
import android.support.p000v4.view.PagerAdapter;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.ViewPager;
import android.support.p000v4.widget.TextViewCompat;
import android.support.p003v7.app.ActionBar;
import android.support.p003v7.content.res.AppCompatResources;
import android.support.p003v7.widget.TooltipCompat;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import gnu.expr.Declaration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

@ViewPager.DecorView
public class TabLayout extends HorizontalScrollView {
    private static final int ANIMATION_DURATION = 300;
    @Dimension(unit = 0)
    static final int DEFAULT_GAP_TEXT_ICON = 8;
    @Dimension(unit = 0)
    private static final int DEFAULT_HEIGHT = 48;
    @Dimension(unit = 0)
    private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
    @Dimension(unit = 0)
    static final int FIXED_WRAP_GUTTER_MIN = 16;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    public static final int INDICATOR_GRAVITY_BOTTOM = 0;
    public static final int INDICATOR_GRAVITY_CENTER = 1;
    public static final int INDICATOR_GRAVITY_STRETCH = 3;
    public static final int INDICATOR_GRAVITY_TOP = 2;
    private static final int INVALID_WIDTH = -1;
    @Dimension(unit = 0)
    private static final int MIN_INDICATOR_WIDTH = 24;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    @Dimension(unit = 0)
    private static final int TAB_MIN_WIDTH_MARGIN = 56;
    private static final Pools.Pool<Tab> tabPool;
    private AdapterChangeListener adapterChangeListener;
    private int contentInsetStart;
    private BaseOnTabSelectedListener currentVpSelectedListener;
    boolean inlineLabel;
    int mode;
    private TabLayoutOnPageChangeListener pageChangeListener;
    private PagerAdapter pagerAdapter;
    private DataSetObserver pagerAdapterObserver;
    private final int requestedTabMaxWidth;
    private final int requestedTabMinWidth;
    private ValueAnimator scrollAnimator;
    private final int scrollableTabMinWidth;
    private BaseOnTabSelectedListener selectedListener;
    private final ArrayList<BaseOnTabSelectedListener> selectedListeners;
    private Tab selectedTab;
    private boolean setupViewPagerImplicitly;
    private final SlidingTabIndicator slidingTabIndicator;
    final int tabBackgroundResId;
    int tabGravity;
    ColorStateList tabIconTint;
    PorterDuff.Mode tabIconTintMode;
    int tabIndicatorAnimationDuration;
    boolean tabIndicatorFullWidth;
    int tabIndicatorGravity;
    int tabMaxWidth;
    int tabPaddingBottom;
    int tabPaddingEnd;
    int tabPaddingStart;
    int tabPaddingTop;
    ColorStateList tabRippleColorStateList;
    @Nullable
    Drawable tabSelectedIndicator;
    int tabTextAppearance;
    ColorStateList tabTextColors;
    float tabTextMultiLineSize;
    float tabTextSize;
    /* access modifiers changed from: private */
    public final RectF tabViewContentBounds;
    private final Pools.Pool<TabView> tabViewPool;
    private final ArrayList<Tab> tabs;
    boolean unboundedRipple;
    ViewPager viewPager;

    public interface BaseOnTabSelectedListener<T extends Tab> {
        void onTabReselected(T t);

        void onTabSelected(T t);

        void onTabUnselected(T t);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public interface OnTabSelectedListener extends BaseOnTabSelectedListener<Tab> {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TabGravity {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TabIndicatorGravity {
    }

    static {
        Pools.Pool<Tab> pool;
        new Pools.SynchronizedPool(16);
        tabPool = pool;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TabLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, C0064R.attr.tabStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TabLayout(android.content.Context r19, android.util.AttributeSet r20, int r21) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r7.<init>(r8, r9, r10)
            r7 = r0
            java.util.ArrayList r8 = new java.util.ArrayList
            r16 = r8
            r8 = r16
            r9 = r16
            r9.<init>()
            r7.tabs = r8
            r7 = r0
            android.graphics.RectF r8 = new android.graphics.RectF
            r16 = r8
            r8 = r16
            r9 = r16
            r9.<init>()
            r7.tabViewContentBounds = r8
            r7 = r0
            r8 = 2147483647(0x7fffffff, float:NaN)
            r7.tabMaxWidth = r8
            r7 = r0
            java.util.ArrayList r8 = new java.util.ArrayList
            r16 = r8
            r8 = r16
            r9 = r16
            r9.<init>()
            r7.selectedListeners = r8
            r7 = r0
            android.support.v4.util.Pools$SimplePool r8 = new android.support.v4.util.Pools$SimplePool
            r16 = r8
            r8 = r16
            r9 = r16
            r10 = 12
            r9.<init>(r10)
            r7.tabViewPool = r8
            r7 = r0
            r8 = 0
            r7.setHorizontalScrollBarEnabled(r8)
            r7 = r0
            android.support.design.widget.TabLayout$SlidingTabIndicator r8 = new android.support.design.widget.TabLayout$SlidingTabIndicator
            r16 = r8
            r8 = r16
            r9 = r16
            r10 = r0
            r11 = r1
            r9.<init>(r10, r11)
            r7.slidingTabIndicator = r8
            r7 = r0
            r8 = r0
            android.support.design.widget.TabLayout$SlidingTabIndicator r8 = r8.slidingTabIndicator
            r9 = 0
            android.widget.FrameLayout$LayoutParams r10 = new android.widget.FrameLayout$LayoutParams
            r16 = r10
            r10 = r16
            r11 = r16
            r12 = -2
            r13 = -1
            r11.<init>(r12, r13)
            super.addView(r8, r9, r10)
            r7 = r1
            r8 = r2
            int[] r9 = android.support.design.C0064R.styleable.TabLayout
            r10 = r3
            int r11 = android.support.design.C0064R.C0068style.Widget_Design_TabLayout
            r12 = 1
            int[] r12 = new int[r12]
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = 0
            int r15 = android.support.design.C0064R.styleable.TabLayout_tabTextAppearance
            r13[r14] = r15
            android.content.res.TypedArray r7 = android.support.design.internal.ThemeEnforcement.obtainStyledAttributes(r7, r8, r9, r10, r11, r12)
            r4 = r7
            r7 = r0
            android.support.design.widget.TabLayout$SlidingTabIndicator r7 = r7.slidingTabIndicator
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabIndicatorHeight
            r10 = -1
            int r8 = r8.getDimensionPixelSize(r9, r10)
            r7.setSelectedIndicatorHeight(r8)
            r7 = r0
            android.support.design.widget.TabLayout$SlidingTabIndicator r7 = r7.slidingTabIndicator
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabIndicatorColor
            r10 = 0
            int r8 = r8.getColor(r9, r10)
            r7.setSelectedIndicatorColor(r8)
            r7 = r0
            r8 = r1
            r9 = r4
            int r10 = android.support.design.C0064R.styleable.TabLayout_tabIndicator
            android.graphics.drawable.Drawable r8 = android.support.design.resources.MaterialResources.getDrawable(r8, r9, r10)
            r7.setSelectedTabIndicator((android.graphics.drawable.Drawable) r8)
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabIndicatorGravity
            r10 = 0
            int r8 = r8.getInt(r9, r10)
            r7.setSelectedTabIndicatorGravity(r8)
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabIndicatorFullWidth
            r10 = 1
            boolean r8 = r8.getBoolean(r9, r10)
            r7.setTabIndicatorFullWidth(r8)
            r7 = r0
            r8 = r0
            r9 = r0
            r10 = r0
            r11 = r4
            int r12 = android.support.design.C0064R.styleable.TabLayout_tabPadding
            r13 = 0
            int r11 = r11.getDimensionPixelSize(r12, r13)
            r16 = r10
            r17 = r11
            r10 = r17
            r11 = r16
            r12 = r17
            r11.tabPaddingBottom = r12
            r16 = r9
            r17 = r10
            r9 = r17
            r10 = r16
            r11 = r17
            r10.tabPaddingEnd = r11
            r16 = r8
            r17 = r9
            r8 = r17
            r9 = r16
            r10 = r17
            r9.tabPaddingTop = r10
            r7.tabPaddingStart = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabPaddingStart
            r10 = r0
            int r10 = r10.tabPaddingStart
            int r8 = r8.getDimensionPixelSize(r9, r10)
            r7.tabPaddingStart = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabPaddingTop
            r10 = r0
            int r10 = r10.tabPaddingTop
            int r8 = r8.getDimensionPixelSize(r9, r10)
            r7.tabPaddingTop = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabPaddingEnd
            r10 = r0
            int r10 = r10.tabPaddingEnd
            int r8 = r8.getDimensionPixelSize(r9, r10)
            r7.tabPaddingEnd = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabPaddingBottom
            r10 = r0
            int r10 = r10.tabPaddingBottom
            int r8 = r8.getDimensionPixelSize(r9, r10)
            r7.tabPaddingBottom = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabTextAppearance
            int r10 = android.support.design.C0064R.C0068style.TextAppearance_Design_Tab
            int r8 = r8.getResourceId(r9, r10)
            r7.tabTextAppearance = r8
            r7 = r1
            r8 = r0
            int r8 = r8.tabTextAppearance
            int[] r9 = android.support.p003v7.appcompat.C0425R.styleable.TextAppearance
            android.content.res.TypedArray r7 = r7.obtainStyledAttributes(r8, r9)
            r5 = r7
            r7 = r0
            r8 = r5
            int r9 = android.support.p003v7.appcompat.C0425R.styleable.TextAppearance_android_textSize     // Catch:{ all -> 0x024e }
            r10 = 0
            int r8 = r8.getDimensionPixelSize(r9, r10)     // Catch:{ all -> 0x024e }
            float r8 = (float) r8     // Catch:{ all -> 0x024e }
            r7.tabTextSize = r8     // Catch:{ all -> 0x024e }
            r7 = r0
            r8 = r1
            r9 = r5
            int r10 = android.support.p003v7.appcompat.C0425R.styleable.TextAppearance_android_textColor     // Catch:{ all -> 0x024e }
            android.content.res.ColorStateList r8 = android.support.design.resources.MaterialResources.getColorStateList(r8, r9, r10)     // Catch:{ all -> 0x024e }
            r7.tabTextColors = r8     // Catch:{ all -> 0x024e }
            r7 = r5
            r7.recycle()
            r7 = r4
            int r8 = android.support.design.C0064R.styleable.TabLayout_tabTextColor
            boolean r7 = r7.hasValue(r8)
            if (r7 == 0) goto L_0x017f
            r7 = r0
            r8 = r1
            r9 = r4
            int r10 = android.support.design.C0064R.styleable.TabLayout_tabTextColor
            android.content.res.ColorStateList r8 = android.support.design.resources.MaterialResources.getColorStateList(r8, r9, r10)
            r7.tabTextColors = r8
        L_0x017f:
            r7 = r4
            int r8 = android.support.design.C0064R.styleable.TabLayout_tabSelectedTextColor
            boolean r7 = r7.hasValue(r8)
            if (r7 == 0) goto L_0x01a0
            r7 = r4
            int r8 = android.support.design.C0064R.styleable.TabLayout_tabSelectedTextColor
            r9 = 0
            int r7 = r7.getColor(r8, r9)
            r6 = r7
            r7 = r0
            r8 = r0
            android.content.res.ColorStateList r8 = r8.tabTextColors
            int r8 = r8.getDefaultColor()
            r9 = r6
            android.content.res.ColorStateList r8 = createColorStateList(r8, r9)
            r7.tabTextColors = r8
        L_0x01a0:
            r7 = r0
            r8 = r1
            r9 = r4
            int r10 = android.support.design.C0064R.styleable.TabLayout_tabIconTint
            android.content.res.ColorStateList r8 = android.support.design.resources.MaterialResources.getColorStateList(r8, r9, r10)
            r7.tabIconTint = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabIconTintMode
            r10 = -1
            int r8 = r8.getInt(r9, r10)
            r9 = 0
            android.graphics.PorterDuff$Mode r8 = android.support.design.internal.ViewUtils.parseTintMode(r8, r9)
            r7.tabIconTintMode = r8
            r7 = r0
            r8 = r1
            r9 = r4
            int r10 = android.support.design.C0064R.styleable.TabLayout_tabRippleColor
            android.content.res.ColorStateList r8 = android.support.design.resources.MaterialResources.getColorStateList(r8, r9, r10)
            r7.tabRippleColorStateList = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabIndicatorAnimationDuration
            r10 = 300(0x12c, float:4.2E-43)
            int r8 = r8.getInt(r9, r10)
            r7.tabIndicatorAnimationDuration = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabMinWidth
            r10 = -1
            int r8 = r8.getDimensionPixelSize(r9, r10)
            r7.requestedTabMinWidth = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabMaxWidth
            r10 = -1
            int r8 = r8.getDimensionPixelSize(r9, r10)
            r7.requestedTabMaxWidth = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabBackground
            r10 = 0
            int r8 = r8.getResourceId(r9, r10)
            r7.tabBackgroundResId = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabContentStart
            r10 = 0
            int r8 = r8.getDimensionPixelSize(r9, r10)
            r7.contentInsetStart = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabMode
            r10 = 1
            int r8 = r8.getInt(r9, r10)
            r7.mode = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabGravity
            r10 = 0
            int r8 = r8.getInt(r9, r10)
            r7.tabGravity = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabInlineLabel
            r10 = 0
            boolean r8 = r8.getBoolean(r9, r10)
            r7.inlineLabel = r8
            r7 = r0
            r8 = r4
            int r9 = android.support.design.C0064R.styleable.TabLayout_tabUnboundedRipple
            r10 = 0
            boolean r8 = r8.getBoolean(r9, r10)
            r7.unboundedRipple = r8
            r7 = r4
            r7.recycle()
            r7 = r0
            android.content.res.Resources r7 = r7.getResources()
            r6 = r7
            r7 = r0
            r8 = r6
            int r9 = android.support.design.C0064R.dimen.design_tab_text_size_2line
            int r8 = r8.getDimensionPixelSize(r9)
            float r8 = (float) r8
            r7.tabTextMultiLineSize = r8
            r7 = r0
            r8 = r6
            int r9 = android.support.design.C0064R.dimen.design_tab_scrollable_min_width
            int r8 = r8.getDimensionPixelSize(r9)
            r7.scrollableTabMinWidth = r8
            r7 = r0
            r7.applyModeAndGravity()
            return
        L_0x024e:
            r7 = move-exception
            r6 = r7
            r7 = r5
            r7.recycle()
            r7 = r6
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.TabLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setSelectedTabIndicatorColor(@ColorInt int color) {
        this.slidingTabIndicator.setSelectedIndicatorColor(color);
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int height) {
        this.slidingTabIndicator.setSelectedIndicatorHeight(height);
    }

    public void setScrollPosition(int position, float positionOffset, boolean updateSelectedText) {
        setScrollPosition(position, positionOffset, updateSelectedText, true);
    }

    /* access modifiers changed from: package-private */
    public void setScrollPosition(int i, float f, boolean z, boolean z2) {
        int position = i;
        float positionOffset = f;
        boolean updateSelectedText = z;
        boolean updateIndicatorPosition = z2;
        int roundedPosition = Math.round(((float) position) + positionOffset);
        if (roundedPosition >= 0 && roundedPosition < this.slidingTabIndicator.getChildCount()) {
            if (updateIndicatorPosition) {
                this.slidingTabIndicator.setIndicatorPositionFromTabPosition(position, positionOffset);
            }
            if (this.scrollAnimator != null && this.scrollAnimator.isRunning()) {
                this.scrollAnimator.cancel();
            }
            scrollTo(calculateScrollXForTab(position, positionOffset), 0);
            if (updateSelectedText) {
                setSelectedTabView(roundedPosition);
            }
        }
    }

    public void addTab(@NonNull Tab tab) {
        addTab(tab, this.tabs.isEmpty());
    }

    public void addTab(@NonNull Tab tab, int position) {
        addTab(tab, position, this.tabs.isEmpty());
    }

    public void addTab(@NonNull Tab tab, boolean setSelected) {
        addTab(tab, this.tabs.size(), setSelected);
    }

    public void addTab(@NonNull Tab tab, int i, boolean z) {
        Throwable th;
        Tab tab2 = tab;
        int position = i;
        boolean setSelected = z;
        if (tab2.parent != this) {
            Throwable th2 = th;
            new IllegalArgumentException("Tab belongs to a different TabLayout.");
            throw th2;
        }
        configureTab(tab2, position);
        addTabView(tab2);
        if (setSelected) {
            tab2.select();
        }
    }

    private void addTabFromItemView(@NonNull TabItem tabItem) {
        TabItem item = tabItem;
        Tab tab = newTab();
        if (item.text != null) {
            Tab text = tab.setText(item.text);
        }
        if (item.icon != null) {
            Tab icon = tab.setIcon(item.icon);
        }
        if (item.customLayout != 0) {
            Tab customView = tab.setCustomView(item.customLayout);
        }
        if (!TextUtils.isEmpty(item.getContentDescription())) {
            Tab contentDescription = tab.setContentDescription(item.getContentDescription());
        }
        addTab(tab);
    }

    @Deprecated
    public void setOnTabSelectedListener(@Nullable BaseOnTabSelectedListener baseOnTabSelectedListener) {
        BaseOnTabSelectedListener listener = baseOnTabSelectedListener;
        if (this.selectedListener != null) {
            removeOnTabSelectedListener(this.selectedListener);
        }
        this.selectedListener = listener;
        if (listener != null) {
            addOnTabSelectedListener(listener);
        }
    }

    public void addOnTabSelectedListener(@NonNull BaseOnTabSelectedListener baseOnTabSelectedListener) {
        BaseOnTabSelectedListener listener = baseOnTabSelectedListener;
        if (!this.selectedListeners.contains(listener)) {
            boolean add = this.selectedListeners.add(listener);
        }
    }

    public void removeOnTabSelectedListener(@NonNull BaseOnTabSelectedListener listener) {
        boolean remove = this.selectedListeners.remove(listener);
    }

    public void clearOnTabSelectedListeners() {
        this.selectedListeners.clear();
    }

    @NonNull
    public Tab newTab() {
        Tab tab = createTabFromPool();
        tab.parent = this;
        tab.view = createTabView(tab);
        return tab;
    }

    /* access modifiers changed from: protected */
    public Tab createTabFromPool() {
        Tab tab;
        Tab tab2 = tabPool.acquire();
        if (tab2 == null) {
            new Tab();
            tab2 = tab;
        }
        return tab2;
    }

    /* access modifiers changed from: protected */
    public boolean releaseFromTabPool(Tab tab) {
        return tabPool.release(tab);
    }

    public int getTabCount() {
        return this.tabs.size();
    }

    @Nullable
    public Tab getTabAt(int i) {
        int index = i;
        return (index < 0 || index >= getTabCount()) ? null : this.tabs.get(index);
    }

    public int getSelectedTabPosition() {
        return this.selectedTab != null ? this.selectedTab.getPosition() : -1;
    }

    public void removeTab(Tab tab) {
        Throwable th;
        Tab tab2 = tab;
        if (tab2.parent != this) {
            Throwable th2 = th;
            new IllegalArgumentException("Tab does not belong to this TabLayout.");
            throw th2;
        }
        removeTabAt(tab2.getPosition());
    }

    public void removeTabAt(int i) {
        int position = i;
        int selectedTabPosition = this.selectedTab != null ? this.selectedTab.getPosition() : 0;
        removeTabViewAt(position);
        Tab removedTab = this.tabs.remove(position);
        if (removedTab != null) {
            removedTab.reset();
            boolean releaseFromTabPool = releaseFromTabPool(removedTab);
        }
        int newTabCount = this.tabs.size();
        for (int i2 = position; i2 < newTabCount; i2++) {
            this.tabs.get(i2).setPosition(i2);
        }
        if (selectedTabPosition == position) {
            selectTab(this.tabs.isEmpty() ? null : this.tabs.get(Math.max(0, position - 1)));
        }
    }

    public void removeAllTabs() {
        for (int i = this.slidingTabIndicator.getChildCount() - 1; i >= 0; i--) {
            removeTabViewAt(i);
        }
        Iterator<Tab> i2 = this.tabs.iterator();
        while (i2.hasNext()) {
            Tab tab = i2.next();
            i2.remove();
            tab.reset();
            boolean releaseFromTabPool = releaseFromTabPool(tab);
        }
        this.selectedTab = null;
    }

    public void setTabMode(int i) {
        int mode2 = i;
        if (mode2 != this.mode) {
            this.mode = mode2;
            applyModeAndGravity();
        }
    }

    public int getTabMode() {
        return this.mode;
    }

    public void setTabGravity(int i) {
        int gravity = i;
        if (this.tabGravity != gravity) {
            this.tabGravity = gravity;
            applyModeAndGravity();
        }
    }

    public int getTabGravity() {
        return this.tabGravity;
    }

    public void setSelectedTabIndicatorGravity(int i) {
        int indicatorGravity = i;
        if (this.tabIndicatorGravity != indicatorGravity) {
            this.tabIndicatorGravity = indicatorGravity;
            ViewCompat.postInvalidateOnAnimation(this.slidingTabIndicator);
        }
    }

    public int getTabIndicatorGravity() {
        return this.tabIndicatorGravity;
    }

    public void setTabIndicatorFullWidth(boolean tabIndicatorFullWidth2) {
        this.tabIndicatorFullWidth = tabIndicatorFullWidth2;
        ViewCompat.postInvalidateOnAnimation(this.slidingTabIndicator);
    }

    public boolean isTabIndicatorFullWidth() {
        return this.tabIndicatorFullWidth;
    }

    public void setInlineLabel(boolean z) {
        boolean inline = z;
        if (this.inlineLabel != inline) {
            this.inlineLabel = inline;
            for (int i = 0; i < this.slidingTabIndicator.getChildCount(); i++) {
                View child = this.slidingTabIndicator.getChildAt(i);
                if (child instanceof TabView) {
                    ((TabView) child).updateOrientation();
                }
            }
            applyModeAndGravity();
        }
    }

    public void setInlineLabelResource(@BoolRes int inlineResourceId) {
        setInlineLabel(getResources().getBoolean(inlineResourceId));
    }

    public boolean isInlineLabel() {
        return this.inlineLabel;
    }

    public void setUnboundedRipple(boolean z) {
        boolean unboundedRipple2 = z;
        if (this.unboundedRipple != unboundedRipple2) {
            this.unboundedRipple = unboundedRipple2;
            for (int i = 0; i < this.slidingTabIndicator.getChildCount(); i++) {
                View child = this.slidingTabIndicator.getChildAt(i);
                if (child instanceof TabView) {
                    ((TabView) child).updateBackgroundDrawable(getContext());
                }
            }
        }
    }

    public void setUnboundedRippleResource(@BoolRes int unboundedRippleResourceId) {
        setUnboundedRipple(getResources().getBoolean(unboundedRippleResourceId));
    }

    public boolean hasUnboundedRipple() {
        return this.unboundedRipple;
    }

    public void setTabTextColors(@Nullable ColorStateList colorStateList) {
        ColorStateList textColor = colorStateList;
        if (this.tabTextColors != textColor) {
            this.tabTextColors = textColor;
            updateAllTabs();
        }
    }

    @Nullable
    public ColorStateList getTabTextColors() {
        return this.tabTextColors;
    }

    public void setTabTextColors(int normalColor, int selectedColor) {
        setTabTextColors(createColorStateList(normalColor, selectedColor));
    }

    public void setTabIconTint(@Nullable ColorStateList colorStateList) {
        ColorStateList iconTint = colorStateList;
        if (this.tabIconTint != iconTint) {
            this.tabIconTint = iconTint;
            updateAllTabs();
        }
    }

    public void setTabIconTintResource(@ColorRes int iconTintResourceId) {
        setTabIconTint(AppCompatResources.getColorStateList(getContext(), iconTintResourceId));
    }

    @Nullable
    public ColorStateList getTabIconTint() {
        return this.tabIconTint;
    }

    @Nullable
    public ColorStateList getTabRippleColor() {
        return this.tabRippleColorStateList;
    }

    public void setTabRippleColor(@Nullable ColorStateList colorStateList) {
        ColorStateList color = colorStateList;
        if (this.tabRippleColorStateList != color) {
            this.tabRippleColorStateList = color;
            for (int i = 0; i < this.slidingTabIndicator.getChildCount(); i++) {
                View child = this.slidingTabIndicator.getChildAt(i);
                if (child instanceof TabView) {
                    ((TabView) child).updateBackgroundDrawable(getContext());
                }
            }
        }
    }

    public void setTabRippleColorResource(@ColorRes int tabRippleColorResourceId) {
        setTabRippleColor(AppCompatResources.getColorStateList(getContext(), tabRippleColorResourceId));
    }

    @Nullable
    public Drawable getTabSelectedIndicator() {
        return this.tabSelectedIndicator;
    }

    public void setSelectedTabIndicator(@Nullable Drawable drawable) {
        Drawable tabSelectedIndicator2 = drawable;
        if (this.tabSelectedIndicator != tabSelectedIndicator2) {
            this.tabSelectedIndicator = tabSelectedIndicator2;
            ViewCompat.postInvalidateOnAnimation(this.slidingTabIndicator);
        }
    }

    public void setSelectedTabIndicator(@DrawableRes int i) {
        int tabSelectedIndicatorResourceId = i;
        if (tabSelectedIndicatorResourceId != 0) {
            setSelectedTabIndicator(AppCompatResources.getDrawable(getContext(), tabSelectedIndicatorResourceId));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }

    public void setupWithViewPager(@Nullable ViewPager viewPager2) {
        setupWithViewPager(viewPager2, true);
    }

    public void setupWithViewPager(@Nullable ViewPager viewPager2, boolean autoRefresh) {
        setupWithViewPager(viewPager2, autoRefresh, false);
    }

    private void setupWithViewPager(@Nullable ViewPager viewPager2, boolean z, boolean z2) {
        BaseOnTabSelectedListener baseOnTabSelectedListener;
        AdapterChangeListener adapterChangeListener2;
        TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;
        ViewPager viewPager3 = viewPager2;
        boolean autoRefresh = z;
        boolean implicitSetup = z2;
        if (this.viewPager != null) {
            if (this.pageChangeListener != null) {
                this.viewPager.removeOnPageChangeListener(this.pageChangeListener);
            }
            if (this.adapterChangeListener != null) {
                this.viewPager.removeOnAdapterChangeListener(this.adapterChangeListener);
            }
        }
        if (this.currentVpSelectedListener != null) {
            removeOnTabSelectedListener(this.currentVpSelectedListener);
            this.currentVpSelectedListener = null;
        }
        if (viewPager3 != null) {
            this.viewPager = viewPager3;
            if (this.pageChangeListener == null) {
                new TabLayoutOnPageChangeListener(this);
                this.pageChangeListener = tabLayoutOnPageChangeListener;
            }
            this.pageChangeListener.reset();
            viewPager3.addOnPageChangeListener(this.pageChangeListener);
            new ViewPagerOnTabSelectedListener(viewPager3);
            this.currentVpSelectedListener = baseOnTabSelectedListener;
            addOnTabSelectedListener(this.currentVpSelectedListener);
            PagerAdapter adapter = viewPager3.getAdapter();
            if (adapter != null) {
                setPagerAdapter(adapter, autoRefresh);
            }
            if (this.adapterChangeListener == null) {
                new AdapterChangeListener(this);
                this.adapterChangeListener = adapterChangeListener2;
            }
            this.adapterChangeListener.setAutoRefresh(autoRefresh);
            viewPager3.addOnAdapterChangeListener(this.adapterChangeListener);
            setScrollPosition(viewPager3.getCurrentItem(), 0.0f, true);
        } else {
            this.viewPager = null;
            setPagerAdapter((PagerAdapter) null, false);
        }
        this.setupViewPagerImplicitly = implicitSetup;
    }

    @Deprecated
    public void setTabsFromPagerAdapter(@Nullable PagerAdapter adapter) {
        setPagerAdapter(adapter, false);
    }

    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.viewPager == null) {
            ViewParent vp = getParent();
            if (vp instanceof ViewPager) {
                setupWithViewPager((ViewPager) vp, true, true);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.setupViewPagerImplicitly) {
            setupWithViewPager((ViewPager) null);
            this.setupViewPagerImplicitly = false;
        }
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.slidingTabIndicator.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    /* access modifiers changed from: package-private */
    public void setPagerAdapter(@Nullable PagerAdapter pagerAdapter2, boolean z) {
        DataSetObserver dataSetObserver;
        PagerAdapter adapter = pagerAdapter2;
        boolean addObserver = z;
        if (!(this.pagerAdapter == null || this.pagerAdapterObserver == null)) {
            this.pagerAdapter.unregisterDataSetObserver(this.pagerAdapterObserver);
        }
        this.pagerAdapter = adapter;
        if (addObserver && adapter != null) {
            if (this.pagerAdapterObserver == null) {
                new PagerAdapterObserver(this);
                this.pagerAdapterObserver = dataSetObserver;
            }
            adapter.registerDataSetObserver(this.pagerAdapterObserver);
        }
        populateFromPagerAdapter();
    }

    /* access modifiers changed from: package-private */
    public void populateFromPagerAdapter() {
        int i;
        removeAllTabs();
        if (this.pagerAdapter != null) {
            int adapterCount = this.pagerAdapter.getCount();
            for (int i2 = 0; i2 < adapterCount; i2++) {
                addTab(newTab().setText(this.pagerAdapter.getPageTitle(i2)), false);
            }
            if (this.viewPager != null && adapterCount > 0 && (i = this.viewPager.getCurrentItem()) != getSelectedTabPosition() && i < getTabCount()) {
                selectTab(getTabAt(i));
            }
        }
    }

    private void updateAllTabs() {
        int z = this.tabs.size();
        for (int i = 0; i < z; i++) {
            this.tabs.get(i).updateView();
        }
    }

    private TabView createTabView(@NonNull Tab tab) {
        TabView tabView;
        Tab tab2 = tab;
        TabView tabView2 = this.tabViewPool != null ? this.tabViewPool.acquire() : null;
        if (tabView2 == null) {
            new TabView(this, getContext());
            tabView2 = tabView;
        }
        tabView2.setTab(tab2);
        tabView2.setFocusable(true);
        tabView2.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(tab2.contentDesc)) {
            tabView2.setContentDescription(tab2.text);
        } else {
            tabView2.setContentDescription(tab2.contentDesc);
        }
        return tabView2;
    }

    private void configureTab(Tab tab, int i) {
        Tab tab2 = tab;
        int position = i;
        tab2.setPosition(position);
        this.tabs.add(position, tab2);
        int count = this.tabs.size();
        for (int i2 = position + 1; i2 < count; i2++) {
            this.tabs.get(i2).setPosition(i2);
        }
    }

    private void addTabView(Tab tab) {
        Tab tab2 = tab;
        this.slidingTabIndicator.addView(tab2.view, tab2.getPosition(), createLayoutParamsForTabs());
    }

    public void addView(View child) {
        addViewInternal(child);
    }

    public void addView(View child, int i) {
        int i2 = i;
        addViewInternal(child);
    }

    public void addView(View child, ViewGroup.LayoutParams layoutParams) {
        ViewGroup.LayoutParams layoutParams2 = layoutParams;
        addViewInternal(child);
    }

    public void addView(View child, int i, ViewGroup.LayoutParams layoutParams) {
        int i2 = i;
        ViewGroup.LayoutParams layoutParams2 = layoutParams;
        addViewInternal(child);
    }

    private void addViewInternal(View view) {
        Throwable th;
        View child = view;
        if (child instanceof TabItem) {
            addTabFromItemView((TabItem) child);
            return;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        throw th2;
    }

    private LinearLayout.LayoutParams createLayoutParamsForTabs() {
        LinearLayout.LayoutParams layoutParams;
        new LinearLayout.LayoutParams(-2, -1);
        LinearLayout.LayoutParams lp = layoutParams;
        updateTabViewLayoutParams(lp);
        return lp;
    }

    private void updateTabViewLayoutParams(LinearLayout.LayoutParams layoutParams) {
        LinearLayout.LayoutParams lp = layoutParams;
        if (this.mode == 1 && this.tabGravity == 0) {
            lp.width = 0;
            lp.weight = 1.0f;
            return;
        }
        lp.width = -2;
        lp.weight = 0.0f;
    }

    /* access modifiers changed from: package-private */
    @Dimension(unit = 1)
    public int dpToPx(@Dimension(unit = 0) int dps) {
        return Math.round(getResources().getDisplayMetrics().density * ((float) dps));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        for (int i = 0; i < this.slidingTabIndicator.getChildCount(); i++) {
            View tabView = this.slidingTabIndicator.getChildAt(i);
            if (tabView instanceof TabView) {
                ((TabView) tabView).drawBackground(canvas2);
            }
        }
        super.onDraw(canvas2);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int dpToPx;
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        int idealHeight = dpToPx(getDefaultHeight()) + getPaddingTop() + getPaddingBottom();
        switch (View.MeasureSpec.getMode(heightMeasureSpec)) {
            case Integer.MIN_VALUE:
                heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(idealHeight, View.MeasureSpec.getSize(heightMeasureSpec)), Declaration.MODULE_REFERENCE);
                break;
            case 0:
                heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(idealHeight, Declaration.MODULE_REFERENCE);
                break;
        }
        int specWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        if (View.MeasureSpec.getMode(widthMeasureSpec) != 0) {
            if (this.requestedTabMaxWidth > 0) {
                dpToPx = this.requestedTabMaxWidth;
            } else {
                dpToPx = specWidth - dpToPx(56);
            }
            this.tabMaxWidth = dpToPx;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() == 1) {
            View child = getChildAt(0);
            boolean remeasure = false;
            switch (this.mode) {
                case 0:
                    remeasure = child.getMeasuredWidth() < getMeasuredWidth();
                    break;
                case 1:
                    remeasure = child.getMeasuredWidth() != getMeasuredWidth();
                    break;
            }
            if (remeasure) {
                child.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), Declaration.MODULE_REFERENCE), getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom(), child.getLayoutParams().height));
            }
        }
    }

    private void removeTabViewAt(int i) {
        int position = i;
        TabView view = (TabView) this.slidingTabIndicator.getChildAt(position);
        this.slidingTabIndicator.removeViewAt(position);
        if (view != null) {
            view.reset();
            boolean release = this.tabViewPool.release(view);
        }
        requestLayout();
    }

    private void animateToTab(int i) {
        int newPosition = i;
        if (newPosition != -1) {
            if (getWindowToken() == null || !ViewCompat.isLaidOut(this) || this.slidingTabIndicator.childrenNeedLayout()) {
                setScrollPosition(newPosition, 0.0f, true);
                return;
            }
            int startScrollX = getScrollX();
            int targetScrollX = calculateScrollXForTab(newPosition, 0.0f);
            if (startScrollX != targetScrollX) {
                ensureScrollAnimator();
                ValueAnimator valueAnimator = this.scrollAnimator;
                int[] iArr = new int[2];
                iArr[0] = startScrollX;
                int[] iArr2 = iArr;
                iArr2[1] = targetScrollX;
                valueAnimator.setIntValues(iArr2);
                this.scrollAnimator.start();
            }
            this.slidingTabIndicator.animateIndicatorToPosition(newPosition, this.tabIndicatorAnimationDuration);
        }
    }

    private void ensureScrollAnimator() {
        ValueAnimator valueAnimator;
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
        if (this.scrollAnimator == null) {
            new ValueAnimator();
            this.scrollAnimator = valueAnimator;
            this.scrollAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            ValueAnimator duration = this.scrollAnimator.setDuration((long) this.tabIndicatorAnimationDuration);
            new ValueAnimator.AnimatorUpdateListener(this) {
                final /* synthetic */ TabLayout this$0;

                {
                    this.this$0 = this$0;
                }

                public void onAnimationUpdate(ValueAnimator animator) {
                    this.this$0.scrollTo(((Integer) animator.getAnimatedValue()).intValue(), 0);
                }
            };
            this.scrollAnimator.addUpdateListener(animatorUpdateListener);
        }
    }

    /* access modifiers changed from: package-private */
    public void setScrollAnimatorListener(Animator.AnimatorListener listener) {
        ensureScrollAnimator();
        this.scrollAnimator.addListener(listener);
    }

    private void setSelectedTabView(int i) {
        int position = i;
        int tabCount = this.slidingTabIndicator.getChildCount();
        if (position < tabCount) {
            int i2 = 0;
            while (i2 < tabCount) {
                View child = this.slidingTabIndicator.getChildAt(i2);
                child.setSelected(i2 == position);
                child.setActivated(i2 == position);
                i2++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void selectTab(Tab tab) {
        selectTab(tab, true);
    }

    /* access modifiers changed from: package-private */
    public void selectTab(Tab tab, boolean z) {
        Tab tab2 = tab;
        boolean updateIndicator = z;
        Tab currentTab = this.selectedTab;
        if (currentTab != tab2) {
            int newPosition = tab2 != null ? tab2.getPosition() : -1;
            if (updateIndicator) {
                if ((currentTab == null || currentTab.getPosition() == -1) && newPosition != -1) {
                    setScrollPosition(newPosition, 0.0f, true);
                } else {
                    animateToTab(newPosition);
                }
                if (newPosition != -1) {
                    setSelectedTabView(newPosition);
                }
            }
            this.selectedTab = tab2;
            if (currentTab != null) {
                dispatchTabUnselected(currentTab);
            }
            if (tab2 != null) {
                dispatchTabSelected(tab2);
            }
        } else if (currentTab != null) {
            dispatchTabReselected(tab2);
            animateToTab(tab2.getPosition());
        }
    }

    private void dispatchTabSelected(@NonNull Tab tab) {
        Tab tab2 = tab;
        for (int i = this.selectedListeners.size() - 1; i >= 0; i--) {
            this.selectedListeners.get(i).onTabSelected(tab2);
        }
    }

    private void dispatchTabUnselected(@NonNull Tab tab) {
        Tab tab2 = tab;
        for (int i = this.selectedListeners.size() - 1; i >= 0; i--) {
            this.selectedListeners.get(i).onTabUnselected(tab2);
        }
    }

    private void dispatchTabReselected(@NonNull Tab tab) {
        Tab tab2 = tab;
        for (int i = this.selectedListeners.size() - 1; i >= 0; i--) {
            this.selectedListeners.get(i).onTabReselected(tab2);
        }
    }

    private int calculateScrollXForTab(int i, float f) {
        int position = i;
        float positionOffset = f;
        if (this.mode != 0) {
            return 0;
        }
        View selectedChild = this.slidingTabIndicator.getChildAt(position);
        View nextChild = position + 1 < this.slidingTabIndicator.getChildCount() ? this.slidingTabIndicator.getChildAt(position + 1) : null;
        int selectedWidth = selectedChild != null ? selectedChild.getWidth() : 0;
        int nextWidth = nextChild != null ? nextChild.getWidth() : 0;
        int scrollBase = (selectedChild.getLeft() + (selectedWidth / 2)) - (getWidth() / 2);
        int scrollOffset = (int) (((float) (selectedWidth + nextWidth)) * 0.5f * positionOffset);
        return ViewCompat.getLayoutDirection(this) == 0 ? scrollBase + scrollOffset : scrollBase - scrollOffset;
    }

    private void applyModeAndGravity() {
        int paddingStart = 0;
        if (this.mode == 0) {
            paddingStart = Math.max(0, this.contentInsetStart - this.tabPaddingStart);
        }
        ViewCompat.setPaddingRelative(this.slidingTabIndicator, paddingStart, 0, 0, 0);
        switch (this.mode) {
            case 0:
                this.slidingTabIndicator.setGravity(GravityCompat.START);
                break;
            case 1:
                this.slidingTabIndicator.setGravity(1);
                break;
        }
        updateTabViews(true);
    }

    /* access modifiers changed from: package-private */
    public void updateTabViews(boolean z) {
        boolean requestLayout = z;
        for (int i = 0; i < this.slidingTabIndicator.getChildCount(); i++) {
            View child = this.slidingTabIndicator.getChildAt(i);
            child.setMinimumWidth(getTabMinWidth());
            updateTabViewLayoutParams((LinearLayout.LayoutParams) child.getLayoutParams());
            if (requestLayout) {
                child.requestLayout();
            }
        }
    }

    public static class Tab {
        public static final int INVALID_POSITION = -1;
        /* access modifiers changed from: private */
        public CharSequence contentDesc;
        private View customView;
        private Drawable icon;
        public TabLayout parent;
        private int position = -1;
        private Object tag;
        /* access modifiers changed from: private */
        public CharSequence text;
        public TabView view;

        public Tab() {
        }

        @Nullable
        public Object getTag() {
            return this.tag;
        }

        @NonNull
        public Tab setTag(@Nullable Object tag2) {
            this.tag = tag2;
            return this;
        }

        @Nullable
        public View getCustomView() {
            return this.customView;
        }

        @NonNull
        public Tab setCustomView(@Nullable View view2) {
            this.customView = view2;
            updateView();
            return this;
        }

        @NonNull
        public Tab setCustomView(@LayoutRes int resId) {
            return setCustomView(LayoutInflater.from(this.view.getContext()).inflate(resId, this.view, false));
        }

        @Nullable
        public Drawable getIcon() {
            return this.icon;
        }

        public int getPosition() {
            return this.position;
        }

        /* access modifiers changed from: package-private */
        public void setPosition(int position2) {
            int i = position2;
            this.position = i;
        }

        @Nullable
        public CharSequence getText() {
            return this.text;
        }

        @NonNull
        public Tab setIcon(@Nullable Drawable icon2) {
            this.icon = icon2;
            updateView();
            return this;
        }

        @NonNull
        public Tab setIcon(@DrawableRes int i) {
            Throwable th;
            int resId = i;
            if (this.parent != null) {
                return setIcon(AppCompatResources.getDrawable(this.parent.getContext(), resId));
            }
            Throwable th2 = th;
            new IllegalArgumentException("Tab not attached to a TabLayout");
            throw th2;
        }

        @NonNull
        public Tab setText(@Nullable CharSequence charSequence) {
            CharSequence text2 = charSequence;
            if (TextUtils.isEmpty(this.contentDesc) && !TextUtils.isEmpty(text2)) {
                this.view.setContentDescription(text2);
            }
            this.text = text2;
            updateView();
            return this;
        }

        @NonNull
        public Tab setText(@StringRes int i) {
            Throwable th;
            int resId = i;
            if (this.parent != null) {
                return setText(this.parent.getResources().getText(resId));
            }
            Throwable th2 = th;
            new IllegalArgumentException("Tab not attached to a TabLayout");
            throw th2;
        }

        public void select() {
            Throwable th;
            if (this.parent == null) {
                Throwable th2 = th;
                new IllegalArgumentException("Tab not attached to a TabLayout");
                throw th2;
            }
            this.parent.selectTab(this);
        }

        public boolean isSelected() {
            Throwable th;
            if (this.parent == null) {
                Throwable th2 = th;
                new IllegalArgumentException("Tab not attached to a TabLayout");
                throw th2;
            }
            return this.parent.getSelectedTabPosition() == this.position;
        }

        @NonNull
        public Tab setContentDescription(@StringRes int i) {
            Throwable th;
            int resId = i;
            if (this.parent != null) {
                return setContentDescription(this.parent.getResources().getText(resId));
            }
            Throwable th2 = th;
            new IllegalArgumentException("Tab not attached to a TabLayout");
            throw th2;
        }

        @NonNull
        public Tab setContentDescription(@Nullable CharSequence contentDesc2) {
            this.contentDesc = contentDesc2;
            updateView();
            return this;
        }

        @Nullable
        public CharSequence getContentDescription() {
            return this.view == null ? null : this.view.getContentDescription();
        }

        /* access modifiers changed from: package-private */
        public void updateView() {
            if (this.view != null) {
                this.view.update();
            }
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.parent = null;
            this.view = null;
            this.tag = null;
            this.icon = null;
            this.text = null;
            this.contentDesc = null;
            this.position = -1;
            this.customView = null;
        }
    }

    class TabView extends LinearLayout {
        @Nullable
        private Drawable baseBackgroundDrawable;
        private ImageView customIconView;
        private TextView customTextView;
        private View customView;
        private int defaultMaxLines = 2;
        private ImageView iconView;
        private Tab tab;
        private TextView textView;
        final /* synthetic */ TabLayout this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public TabView(android.support.design.widget.TabLayout r9, android.content.Context r10) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r2 = r10
                r3 = r0
                r4 = r1
                r3.this$0 = r4
                r3 = r0
                r4 = r2
                r3.<init>(r4)
                r3 = r0
                r4 = 2
                r3.defaultMaxLines = r4
                r3 = r0
                r4 = r2
                r3.updateBackgroundDrawable(r4)
                r3 = r0
                r4 = r1
                int r4 = r4.tabPaddingStart
                r5 = r1
                int r5 = r5.tabPaddingTop
                r6 = r1
                int r6 = r6.tabPaddingEnd
                r7 = r1
                int r7 = r7.tabPaddingBottom
                android.support.p000v4.view.ViewCompat.setPaddingRelative(r3, r4, r5, r6, r7)
                r3 = r0
                r4 = 17
                r3.setGravity(r4)
                r3 = r0
                r4 = r1
                boolean r4 = r4.inlineLabel
                if (r4 == 0) goto L_0x004a
                r4 = 0
            L_0x0032:
                r3.setOrientation(r4)
                r3 = r0
                r4 = 1
                r3.setClickable(r4)
                r3 = r0
                r4 = r0
                android.content.Context r4 = r4.getContext()
                r5 = 1002(0x3ea, float:1.404E-42)
                android.support.v4.view.PointerIconCompat r4 = android.support.p000v4.view.PointerIconCompat.getSystemIcon(r4, r5)
                android.support.p000v4.view.ViewCompat.setPointerIcon(r3, r4)
                return
            L_0x004a:
                r4 = 1
                goto L_0x0032
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.TabLayout.TabView.<init>(android.support.design.widget.TabLayout, android.content.Context):void");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: android.graphics.drawable.GradientDrawable} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v0, resolved type: android.graphics.drawable.GradientDrawable} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.graphics.drawable.GradientDrawable} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: android.graphics.drawable.GradientDrawable} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v30, resolved type: android.graphics.drawable.GradientDrawable} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: android.graphics.drawable.GradientDrawable} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: android.graphics.drawable.GradientDrawable} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.graphics.drawable.GradientDrawable} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: android.graphics.drawable.GradientDrawable} */
        /* access modifiers changed from: private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void updateBackgroundDrawable(android.content.Context r15) {
            /*
                r14 = this;
                r0 = r14
                r1 = r15
                r7 = r0
                android.support.design.widget.TabLayout r7 = r7.this$0
                int r7 = r7.tabBackgroundResId
                if (r7 == 0) goto L_0x0092
                r7 = r0
                r8 = r1
                r9 = r0
                android.support.design.widget.TabLayout r9 = r9.this$0
                int r9 = r9.tabBackgroundResId
                android.graphics.drawable.Drawable r8 = android.support.p003v7.content.res.AppCompatResources.getDrawable(r8, r9)
                r7.baseBackgroundDrawable = r8
                r7 = r0
                android.graphics.drawable.Drawable r7 = r7.baseBackgroundDrawable
                if (r7 == 0) goto L_0x0030
                r7 = r0
                android.graphics.drawable.Drawable r7 = r7.baseBackgroundDrawable
                boolean r7 = r7.isStateful()
                if (r7 == 0) goto L_0x0030
                r7 = r0
                android.graphics.drawable.Drawable r7 = r7.baseBackgroundDrawable
                r8 = r0
                int[] r8 = r8.getDrawableState()
                boolean r7 = r7.setState(r8)
            L_0x0030:
                android.graphics.drawable.GradientDrawable r7 = new android.graphics.drawable.GradientDrawable
                r13 = r7
                r7 = r13
                r8 = r13
                r8.<init>()
                r3 = r7
                r7 = r3
                android.graphics.drawable.GradientDrawable r7 = (android.graphics.drawable.GradientDrawable) r7
                r8 = 0
                r7.setColor(r8)
                r7 = r0
                android.support.design.widget.TabLayout r7 = r7.this$0
                android.content.res.ColorStateList r7 = r7.tabRippleColorStateList
                if (r7 == 0) goto L_0x00c1
                android.graphics.drawable.GradientDrawable r7 = new android.graphics.drawable.GradientDrawable
                r13 = r7
                r7 = r13
                r8 = r13
                r8.<init>()
                r4 = r7
                r7 = r4
                r8 = 925353388(0x3727c5ac, float:1.0E-5)
                r7.setCornerRadius(r8)
                r7 = r4
                r8 = -1
                r7.setColor(r8)
                r7 = r0
                android.support.design.widget.TabLayout r7 = r7.this$0
                android.content.res.ColorStateList r7 = r7.tabRippleColorStateList
                android.content.res.ColorStateList r7 = android.support.design.ripple.RippleUtils.convertToRippleDrawableColor(r7)
                r5 = r7
                int r7 = android.os.Build.VERSION.SDK_INT
                r8 = 21
                if (r7 < r8) goto L_0x009b
                android.graphics.drawable.RippleDrawable r7 = new android.graphics.drawable.RippleDrawable
                r13 = r7
                r7 = r13
                r8 = r13
                r9 = r5
                r10 = r0
                android.support.design.widget.TabLayout r10 = r10.this$0
                boolean r10 = r10.unboundedRipple
                if (r10 == 0) goto L_0x0097
                r10 = 0
            L_0x007a:
                r11 = r0
                android.support.design.widget.TabLayout r11 = r11.this$0
                boolean r11 = r11.unboundedRipple
                if (r11 == 0) goto L_0x0099
                r11 = 0
            L_0x0082:
                r8.<init>(r9, r10, r11)
                r2 = r7
            L_0x0086:
                r7 = r0
                r8 = r2
                android.support.p000v4.view.ViewCompat.setBackground(r7, r8)
                r7 = r0
                android.support.design.widget.TabLayout r7 = r7.this$0
                r7.invalidate()
                return
            L_0x0092:
                r7 = r0
                r8 = 0
                r7.baseBackgroundDrawable = r8
                goto L_0x0030
            L_0x0097:
                r10 = r3
                goto L_0x007a
            L_0x0099:
                r11 = r4
                goto L_0x0082
            L_0x009b:
                r7 = r4
                android.graphics.drawable.Drawable r7 = android.support.p000v4.graphics.drawable.DrawableCompat.wrap(r7)
                r6 = r7
                r7 = r6
                r8 = r5
                android.support.p000v4.graphics.drawable.DrawableCompat.setTintList(r7, r8)
                android.graphics.drawable.LayerDrawable r7 = new android.graphics.drawable.LayerDrawable
                r13 = r7
                r7 = r13
                r8 = r13
                r9 = 2
                android.graphics.drawable.Drawable[] r9 = new android.graphics.drawable.Drawable[r9]
                r13 = r9
                r9 = r13
                r10 = r13
                r11 = 0
                r12 = r3
                r10[r11] = r12
                r13 = r9
                r9 = r13
                r10 = r13
                r11 = 1
                r12 = r6
                r10[r11] = r12
                r8.<init>(r9)
                r2 = r7
                goto L_0x0086
            L_0x00c1:
                r7 = r3
                r2 = r7
                goto L_0x0086
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.TabLayout.TabView.updateBackgroundDrawable(android.content.Context):void");
        }

        /* access modifiers changed from: private */
        public void drawBackground(Canvas canvas) {
            Canvas canvas2 = canvas;
            if (this.baseBackgroundDrawable != null) {
                this.baseBackgroundDrawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
                this.baseBackgroundDrawable.draw(canvas2);
            }
        }

        /* access modifiers changed from: protected */
        public void drawableStateChanged() {
            super.drawableStateChanged();
            boolean changed = false;
            int[] state = getDrawableState();
            if (this.baseBackgroundDrawable != null && this.baseBackgroundDrawable.isStateful()) {
                changed = false | this.baseBackgroundDrawable.setState(state);
            }
            if (changed) {
                invalidate();
                this.this$0.invalidate();
            }
        }

        public boolean performClick() {
            boolean handled = super.performClick();
            if (this.tab == null) {
                return handled;
            }
            if (!handled) {
                playSoundEffect(0);
            }
            this.tab.select();
            return true;
        }

        public void setSelected(boolean z) {
            boolean selected = z;
            boolean changed = isSelected() != selected;
            super.setSelected(selected);
            if (changed && selected && Build.VERSION.SDK_INT < 16) {
                sendAccessibilityEvent(4);
            }
            if (this.textView != null) {
                this.textView.setSelected(selected);
            }
            if (this.iconView != null) {
                this.iconView.setSelected(selected);
            }
            if (this.customView != null) {
                this.customView.setSelected(selected);
            }
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            AccessibilityEvent event = accessibilityEvent;
            super.onInitializeAccessibilityEvent(event);
            event.setClassName(ActionBar.Tab.class.getName());
        }

        @TargetApi(14)
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            AccessibilityNodeInfo info = accessibilityNodeInfo;
            super.onInitializeAccessibilityNodeInfo(info);
            info.setClassName(ActionBar.Tab.class.getName());
        }

        /* JADX WARNING: Removed duplicated region for block: B:20:0x00d9  */
        /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onMeasure(int r22, int r23) {
            /*
                r21 = this;
                r2 = r21
                r3 = r22
                r4 = r23
                r17 = r3
                int r17 = android.view.View.MeasureSpec.getSize(r17)
                r5 = r17
                r17 = r3
                int r17 = android.view.View.MeasureSpec.getMode(r17)
                r6 = r17
                r17 = r2
                r0 = r17
                android.support.design.widget.TabLayout r0 = r0.this$0
                r17 = r0
                int r17 = r17.getTabMaxWidth()
                r7 = r17
                r17 = r4
                r9 = r17
                r17 = r7
                if (r17 <= 0) goto L_0x0170
                r17 = r6
                if (r17 == 0) goto L_0x003a
                r17 = r5
                r18 = r7
                r0 = r17
                r1 = r18
                if (r0 <= r1) goto L_0x0170
            L_0x003a:
                r17 = r2
                r0 = r17
                android.support.design.widget.TabLayout r0 = r0.this$0
                r17 = r0
                r0 = r17
                int r0 = r0.tabMaxWidth
                r17 = r0
                r18 = -2147483648(0xffffffff80000000, float:-0.0)
                int r17 = android.view.View.MeasureSpec.makeMeasureSpec(r17, r18)
                r8 = r17
            L_0x0050:
                r17 = r2
                r18 = r8
                r19 = r9
                super.onMeasure(r18, r19)
                r17 = r2
                r0 = r17
                android.widget.TextView r0 = r0.textView
                r17 = r0
                if (r17 == 0) goto L_0x016f
                r17 = r2
                r0 = r17
                android.support.design.widget.TabLayout r0 = r0.this$0
                r17 = r0
                r0 = r17
                float r0 = r0.tabTextSize
                r17 = r0
                r10 = r17
                r17 = r2
                r0 = r17
                int r0 = r0.defaultMaxLines
                r17 = r0
                r11 = r17
                r17 = r2
                r0 = r17
                android.widget.ImageView r0 = r0.iconView
                r17 = r0
                if (r17 == 0) goto L_0x0176
                r17 = r2
                r0 = r17
                android.widget.ImageView r0 = r0.iconView
                r17 = r0
                int r17 = r17.getVisibility()
                if (r17 != 0) goto L_0x0176
                r17 = 1
                r11 = r17
            L_0x0099:
                r17 = r2
                r0 = r17
                android.widget.TextView r0 = r0.textView
                r17 = r0
                float r17 = r17.getTextSize()
                r12 = r17
                r17 = r2
                r0 = r17
                android.widget.TextView r0 = r0.textView
                r17 = r0
                int r17 = r17.getLineCount()
                r13 = r17
                r17 = r2
                r0 = r17
                android.widget.TextView r0 = r0.textView
                r17 = r0
                int r17 = android.support.p000v4.widget.TextViewCompat.getMaxLines(r17)
                r14 = r17
                r17 = r10
                r18 = r12
                int r17 = (r17 > r18 ? 1 : (r17 == r18 ? 0 : -1))
                if (r17 != 0) goto L_0x00d9
                r17 = r14
                if (r17 < 0) goto L_0x016f
                r17 = r11
                r18 = r14
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x016f
            L_0x00d9:
                r17 = 1
                r15 = r17
                r17 = r2
                r0 = r17
                android.support.design.widget.TabLayout r0 = r0.this$0
                r17 = r0
                r0 = r17
                int r0 = r0.mode
                r17 = r0
                r18 = 1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x0146
                r17 = r10
                r18 = r12
                int r17 = (r17 > r18 ? 1 : (r17 == r18 ? 0 : -1))
                if (r17 <= 0) goto L_0x0146
                r17 = r13
                r18 = 1
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x0146
                r17 = r2
                r0 = r17
                android.widget.TextView r0 = r0.textView
                r17 = r0
                android.text.Layout r17 = r17.getLayout()
                r16 = r17
                r17 = r16
                if (r17 == 0) goto L_0x0142
                r17 = r2
                r18 = r16
                r19 = 0
                r20 = r10
                float r17 = r17.approximateLineWidth(r18, r19, r20)
                r18 = r2
                int r18 = r18.getMeasuredWidth()
                r19 = r2
                int r19 = r19.getPaddingLeft()
                int r18 = r18 - r19
                r19 = r2
                int r19 = r19.getPaddingRight()
                int r18 = r18 - r19
                r0 = r18
                float r0 = (float) r0
                r18 = r0
                int r17 = (r17 > r18 ? 1 : (r17 == r18 ? 0 : -1))
                if (r17 <= 0) goto L_0x0146
            L_0x0142:
                r17 = 0
                r15 = r17
            L_0x0146:
                r17 = r15
                if (r17 == 0) goto L_0x016f
                r17 = r2
                r0 = r17
                android.widget.TextView r0 = r0.textView
                r17 = r0
                r18 = 0
                r19 = r10
                r17.setTextSize(r18, r19)
                r17 = r2
                r0 = r17
                android.widget.TextView r0 = r0.textView
                r17 = r0
                r18 = r11
                r17.setMaxLines(r18)
                r17 = r2
                r18 = r8
                r19 = r9
                super.onMeasure(r18, r19)
            L_0x016f:
                return
            L_0x0170:
                r17 = r3
                r8 = r17
                goto L_0x0050
            L_0x0176:
                r17 = r2
                r0 = r17
                android.widget.TextView r0 = r0.textView
                r17 = r0
                if (r17 == 0) goto L_0x0099
                r17 = r2
                r0 = r17
                android.widget.TextView r0 = r0.textView
                r17 = r0
                int r17 = r17.getLineCount()
                r18 = 1
                r0 = r17
                r1 = r18
                if (r0 <= r1) goto L_0x0099
                r17 = r2
                r0 = r17
                android.support.design.widget.TabLayout r0 = r0.this$0
                r17 = r0
                r0 = r17
                float r0 = r0.tabTextMultiLineSize
                r17 = r0
                r10 = r17
                goto L_0x0099
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.TabLayout.TabView.onMeasure(int, int):void");
        }

        /* access modifiers changed from: package-private */
        public void setTab(@Nullable Tab tab2) {
            Tab tab3 = tab2;
            if (tab3 != this.tab) {
                this.tab = tab3;
                update();
            }
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            setTab((Tab) null);
            setSelected(false);
        }

        /* access modifiers changed from: package-private */
        public final void update() {
            Tab tab2 = this.tab;
            View custom = tab2 != null ? tab2.getCustomView() : null;
            if (custom != null) {
                ViewParent customParent = custom.getParent();
                if (customParent != this) {
                    if (customParent != null) {
                        ((ViewGroup) customParent).removeView(custom);
                    }
                    addView(custom);
                }
                this.customView = custom;
                if (this.textView != null) {
                    this.textView.setVisibility(8);
                }
                if (this.iconView != null) {
                    this.iconView.setVisibility(8);
                    this.iconView.setImageDrawable((Drawable) null);
                }
                this.customTextView = (TextView) custom.findViewById(16908308);
                if (this.customTextView != null) {
                    this.defaultMaxLines = TextViewCompat.getMaxLines(this.customTextView);
                }
                this.customIconView = (ImageView) custom.findViewById(16908294);
            } else {
                if (this.customView != null) {
                    removeView(this.customView);
                    this.customView = null;
                }
                this.customTextView = null;
                this.customIconView = null;
            }
            if (this.customView == null) {
                if (this.iconView == null) {
                    ImageView iconView2 = (ImageView) LayoutInflater.from(getContext()).inflate(C0064R.layout.design_layout_tab_icon, this, false);
                    addView(iconView2, 0);
                    this.iconView = iconView2;
                }
                Drawable icon = (tab2 == null || tab2.getIcon() == null) ? null : DrawableCompat.wrap(tab2.getIcon()).mutate();
                if (icon != null) {
                    DrawableCompat.setTintList(icon, this.this$0.tabIconTint);
                    if (this.this$0.tabIconTintMode != null) {
                        DrawableCompat.setTintMode(icon, this.this$0.tabIconTintMode);
                    }
                }
                if (this.textView == null) {
                    TextView textView2 = (TextView) LayoutInflater.from(getContext()).inflate(C0064R.layout.design_layout_tab_text, this, false);
                    addView(textView2);
                    this.textView = textView2;
                    this.defaultMaxLines = TextViewCompat.getMaxLines(this.textView);
                }
                TextViewCompat.setTextAppearance(this.textView, this.this$0.tabTextAppearance);
                if (this.this$0.tabTextColors != null) {
                    this.textView.setTextColor(this.this$0.tabTextColors);
                }
                updateTextAndIcon(this.textView, this.iconView);
            } else if (!(this.customTextView == null && this.customIconView == null)) {
                updateTextAndIcon(this.customTextView, this.customIconView);
            }
            if (tab2 != null && !TextUtils.isEmpty(tab2.contentDesc)) {
                setContentDescription(tab2.contentDesc);
            }
            setSelected(tab2 != null && tab2.isSelected());
        }

        /* access modifiers changed from: package-private */
        public final void updateOrientation() {
            setOrientation(this.this$0.inlineLabel ? 0 : 1);
            if (this.customTextView == null && this.customIconView == null) {
                updateTextAndIcon(this.textView, this.iconView);
            } else {
                updateTextAndIcon(this.customTextView, this.customIconView);
            }
        }

        private void updateTextAndIcon(@Nullable TextView textView2, @Nullable ImageView imageView) {
            TextView textView3 = textView2;
            ImageView iconView2 = imageView;
            Drawable icon = (this.tab == null || this.tab.getIcon() == null) ? null : DrawableCompat.wrap(this.tab.getIcon()).mutate();
            CharSequence text = this.tab != null ? this.tab.getText() : null;
            if (iconView2 != null) {
                if (icon != null) {
                    iconView2.setImageDrawable(icon);
                    iconView2.setVisibility(0);
                    setVisibility(0);
                } else {
                    iconView2.setVisibility(8);
                    iconView2.setImageDrawable((Drawable) null);
                }
            }
            boolean hasText = !TextUtils.isEmpty(text);
            if (textView3 != null) {
                if (hasText) {
                    textView3.setText(text);
                    textView3.setVisibility(0);
                    setVisibility(0);
                } else {
                    textView3.setVisibility(8);
                    textView3.setText((CharSequence) null);
                }
            }
            if (iconView2 != null) {
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) iconView2.getLayoutParams();
                int iconMargin = 0;
                if (hasText && iconView2.getVisibility() == 0) {
                    iconMargin = this.this$0.dpToPx(8);
                }
                if (this.this$0.inlineLabel) {
                    if (iconMargin != MarginLayoutParamsCompat.getMarginEnd(lp)) {
                        MarginLayoutParamsCompat.setMarginEnd(lp, iconMargin);
                        lp.bottomMargin = 0;
                        iconView2.setLayoutParams(lp);
                        iconView2.requestLayout();
                    }
                } else if (iconMargin != lp.bottomMargin) {
                    lp.bottomMargin = iconMargin;
                    MarginLayoutParamsCompat.setMarginEnd(lp, 0);
                    iconView2.setLayoutParams(lp);
                    iconView2.requestLayout();
                }
            }
            TooltipCompat.setTooltipText(this, hasText ? null : this.tab != null ? this.tab.contentDesc : null);
        }

        /* access modifiers changed from: private */
        public int getContentWidth() {
            boolean initialized = false;
            int left = 0;
            int right = 0;
            View[] viewArr = new View[3];
            viewArr[0] = this.textView;
            View[] viewArr2 = viewArr;
            viewArr2[1] = this.iconView;
            View[] viewArr3 = viewArr2;
            viewArr3[2] = this.customView;
            View[] viewArr4 = viewArr3;
            int length = viewArr4.length;
            for (int i = 0; i < length; i++) {
                View view = viewArr4[i];
                if (view != null && view.getVisibility() == 0) {
                    left = initialized ? Math.min(left, view.getLeft()) : view.getLeft();
                    right = initialized ? Math.max(right, view.getRight()) : view.getRight();
                    initialized = true;
                }
            }
            return right - left;
        }

        public Tab getTab() {
            return this.tab;
        }

        private float approximateLineWidth(Layout layout, int line, float textSize) {
            Layout layout2 = layout;
            return layout2.getLineWidth(line) * (textSize / layout2.getPaint().getTextSize());
        }
    }

    private class SlidingTabIndicator extends LinearLayout {
        private final GradientDrawable defaultSelectionIndicator;
        private ValueAnimator indicatorAnimator;
        private int indicatorLeft = -1;
        private int indicatorRight = -1;
        private int layoutDirection = -1;
        private int selectedIndicatorHeight;
        private final Paint selectedIndicatorPaint;
        int selectedPosition = -1;
        float selectionOffset;
        final /* synthetic */ TabLayout this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        SlidingTabIndicator(TabLayout tabLayout, Context context) {
            super(context);
            Paint paint;
            GradientDrawable gradientDrawable;
            this.this$0 = tabLayout;
            setWillNotDraw(false);
            new Paint();
            this.selectedIndicatorPaint = paint;
            new GradientDrawable();
            this.defaultSelectionIndicator = gradientDrawable;
        }

        /* access modifiers changed from: package-private */
        public void setSelectedIndicatorColor(int i) {
            int color = i;
            if (this.selectedIndicatorPaint.getColor() != color) {
                this.selectedIndicatorPaint.setColor(color);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        /* access modifiers changed from: package-private */
        public void setSelectedIndicatorHeight(int i) {
            int height = i;
            if (this.selectedIndicatorHeight != height) {
                this.selectedIndicatorHeight = height;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean childrenNeedLayout() {
            int z = getChildCount();
            for (int i = 0; i < z; i++) {
                if (getChildAt(i).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void setIndicatorPositionFromTabPosition(int i, float f) {
            int position = i;
            float positionOffset = f;
            if (this.indicatorAnimator != null && this.indicatorAnimator.isRunning()) {
                this.indicatorAnimator.cancel();
            }
            this.selectedPosition = position;
            this.selectionOffset = positionOffset;
            updateIndicatorPosition();
        }

        /* access modifiers changed from: package-private */
        public float getIndicatorPosition() {
            return ((float) this.selectedPosition) + this.selectionOffset;
        }

        public void onRtlPropertiesChanged(int i) {
            int layoutDirection2 = i;
            super.onRtlPropertiesChanged(layoutDirection2);
            if (Build.VERSION.SDK_INT < 23 && this.layoutDirection != layoutDirection2) {
                requestLayout();
                this.layoutDirection = layoutDirection2;
            }
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            int widthMeasureSpec = i;
            int heightMeasureSpec = i2;
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            if (View.MeasureSpec.getMode(widthMeasureSpec) == 1073741824 && this.this$0.mode == 1 && this.this$0.tabGravity == 1) {
                int count = getChildCount();
                int largestTabWidth = 0;
                int z = count;
                for (int i3 = 0; i3 < z; i3++) {
                    View child = getChildAt(i3);
                    if (child.getVisibility() == 0) {
                        largestTabWidth = Math.max(largestTabWidth, child.getMeasuredWidth());
                    }
                }
                if (largestTabWidth > 0) {
                    boolean remeasure = false;
                    if (largestTabWidth * count <= getMeasuredWidth() - (this.this$0.dpToPx(16) * 2)) {
                        for (int i4 = 0; i4 < count; i4++) {
                            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) getChildAt(i4).getLayoutParams();
                            if (lp.width != largestTabWidth || lp.weight != 0.0f) {
                                lp.width = largestTabWidth;
                                lp.weight = 0.0f;
                                remeasure = true;
                            }
                        }
                    } else {
                        this.this$0.tabGravity = 0;
                        this.this$0.updateTabViews(false);
                        remeasure = true;
                    }
                    if (remeasure) {
                        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                    }
                }
            }
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean changed, int l, int t, int r, int b) {
            super.onLayout(changed, l, t, r, b);
            if (this.indicatorAnimator == null || !this.indicatorAnimator.isRunning()) {
                updateIndicatorPosition();
                return;
            }
            this.indicatorAnimator.cancel();
            animateIndicatorToPosition(this.selectedPosition, Math.round((1.0f - this.indicatorAnimator.getAnimatedFraction()) * ((float) this.indicatorAnimator.getDuration())));
        }

        private void updateIndicatorPosition() {
            int right;
            int left;
            View selectedTitle = getChildAt(this.selectedPosition);
            if (selectedTitle == null || selectedTitle.getWidth() <= 0) {
                right = -1;
                left = -1;
            } else {
                left = selectedTitle.getLeft();
                right = selectedTitle.getRight();
                if (!this.this$0.tabIndicatorFullWidth && (selectedTitle instanceof TabView)) {
                    calculateTabViewContentBounds((TabView) selectedTitle, this.this$0.tabViewContentBounds);
                    left = (int) this.this$0.tabViewContentBounds.left;
                    right = (int) this.this$0.tabViewContentBounds.right;
                }
                if (this.selectionOffset > 0.0f && this.selectedPosition < getChildCount() - 1) {
                    View nextTitle = getChildAt(this.selectedPosition + 1);
                    int nextTitleLeft = nextTitle.getLeft();
                    int nextTitleRight = nextTitle.getRight();
                    if (!this.this$0.tabIndicatorFullWidth && (nextTitle instanceof TabView)) {
                        calculateTabViewContentBounds((TabView) nextTitle, this.this$0.tabViewContentBounds);
                        nextTitleLeft = (int) this.this$0.tabViewContentBounds.left;
                        nextTitleRight = (int) this.this$0.tabViewContentBounds.right;
                    }
                    left = (int) ((this.selectionOffset * ((float) nextTitleLeft)) + ((1.0f - this.selectionOffset) * ((float) left)));
                    right = (int) ((this.selectionOffset * ((float) nextTitleRight)) + ((1.0f - this.selectionOffset) * ((float) right)));
                }
            }
            setIndicatorPosition(left, right);
        }

        /* access modifiers changed from: package-private */
        public void setIndicatorPosition(int i, int i2) {
            int left = i;
            int right = i2;
            if (left != this.indicatorLeft || right != this.indicatorRight) {
                this.indicatorLeft = left;
                this.indicatorRight = right;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        /* access modifiers changed from: package-private */
        public void animateIndicatorToPosition(int i, int i2) {
            ValueAnimator valueAnimator;
            ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
            Animator.AnimatorListener animatorListener;
            int position = i;
            int duration = i2;
            if (this.indicatorAnimator != null && this.indicatorAnimator.isRunning()) {
                this.indicatorAnimator.cancel();
            }
            View targetView = getChildAt(position);
            if (targetView == null) {
                updateIndicatorPosition();
                return;
            }
            int targetLeft = targetView.getLeft();
            int targetRight = targetView.getRight();
            if (!this.this$0.tabIndicatorFullWidth && (targetView instanceof TabView)) {
                calculateTabViewContentBounds((TabView) targetView, this.this$0.tabViewContentBounds);
                targetLeft = (int) this.this$0.tabViewContentBounds.left;
                targetRight = (int) this.this$0.tabViewContentBounds.right;
            }
            int finalTargetLeft = targetLeft;
            int finalTargetRight = targetRight;
            int startLeft = this.indicatorLeft;
            int startRight = this.indicatorRight;
            if (startLeft != finalTargetLeft || startRight != finalTargetRight) {
                ValueAnimator valueAnimator2 = valueAnimator;
                new ValueAnimator();
                ValueAnimator valueAnimator3 = valueAnimator2;
                ValueAnimator valueAnimator4 = valueAnimator3;
                this.indicatorAnimator = valueAnimator4;
                ValueAnimator animator = valueAnimator3;
                animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                ValueAnimator duration2 = animator.setDuration((long) duration);
                animator.setFloatValues(new float[]{0.0f, 1.0f});
                final int i3 = startLeft;
                final int i4 = finalTargetLeft;
                final int i5 = startRight;
                final int i6 = finalTargetRight;
                new ValueAnimator.AnimatorUpdateListener(this) {
                    final /* synthetic */ SlidingTabIndicator this$1;

                    {
                        this.this$1 = this$1;
                    }

                    public void onAnimationUpdate(ValueAnimator animator) {
                        float fraction = animator.getAnimatedFraction();
                        this.this$1.setIndicatorPosition(AnimationUtils.lerp(i3, i4, fraction), AnimationUtils.lerp(i5, i6, fraction));
                    }
                };
                animator.addUpdateListener(animatorUpdateListener);
                final int i7 = position;
                new AnimatorListenerAdapter(this) {
                    final /* synthetic */ SlidingTabIndicator this$1;

                    {
                        this.this$1 = this$1;
                    }

                    public void onAnimationEnd(Animator animator) {
                        Animator animator2 = animator;
                        this.this$1.selectedPosition = i7;
                        this.this$1.selectionOffset = 0.0f;
                    }
                };
                animator.addListener(animatorListener);
                animator.start();
            }
        }

        private void calculateTabViewContentBounds(TabView tabView, RectF rectF) {
            TabView tabView2 = tabView;
            RectF contentBounds = rectF;
            int tabViewContentWidth = tabView2.getContentWidth();
            if (tabViewContentWidth < this.this$0.dpToPx(24)) {
                tabViewContentWidth = this.this$0.dpToPx(24);
            }
            int tabViewCenter = (tabView2.getLeft() + tabView2.getRight()) / 2;
            contentBounds.set((float) (tabViewCenter - (tabViewContentWidth / 2)), 0.0f, (float) (tabViewCenter + (tabViewContentWidth / 2)), 0.0f);
        }

        public void draw(Canvas canvas) {
            Canvas canvas2 = canvas;
            int indicatorHeight = 0;
            if (this.this$0.tabSelectedIndicator != null) {
                indicatorHeight = this.this$0.tabSelectedIndicator.getIntrinsicHeight();
            }
            if (this.selectedIndicatorHeight >= 0) {
                indicatorHeight = this.selectedIndicatorHeight;
            }
            int indicatorTop = 0;
            int indicatorBottom = 0;
            switch (this.this$0.tabIndicatorGravity) {
                case 0:
                    indicatorTop = getHeight() - indicatorHeight;
                    indicatorBottom = getHeight();
                    break;
                case 1:
                    indicatorTop = (getHeight() - indicatorHeight) / 2;
                    indicatorBottom = (getHeight() + indicatorHeight) / 2;
                    break;
                case 2:
                    indicatorTop = 0;
                    indicatorBottom = indicatorHeight;
                    break;
                case 3:
                    indicatorTop = 0;
                    indicatorBottom = getHeight();
                    break;
            }
            if (this.indicatorLeft >= 0 && this.indicatorRight > this.indicatorLeft) {
                Drawable selectedIndicator = DrawableCompat.wrap(this.this$0.tabSelectedIndicator != null ? this.this$0.tabSelectedIndicator : this.defaultSelectionIndicator);
                selectedIndicator.setBounds(this.indicatorLeft, indicatorTop, this.indicatorRight, indicatorBottom);
                if (this.selectedIndicatorPaint != null) {
                    if (Build.VERSION.SDK_INT == 21) {
                        selectedIndicator.setColorFilter(this.selectedIndicatorPaint.getColor(), PorterDuff.Mode.SRC_IN);
                    } else {
                        DrawableCompat.setTint(selectedIndicator, this.selectedIndicatorPaint.getColor());
                    }
                }
                selectedIndicator.draw(canvas2);
            }
            super.draw(canvas2);
        }
    }

    private static ColorStateList createColorStateList(int defaultColor, int selectedColor) {
        ColorStateList colorStateList;
        int[][] states = new int[2][];
        int[] colors = new int[2];
        states[0] = SELECTED_STATE_SET;
        colors[0] = selectedColor;
        int i = 0 + 1;
        states[i] = EMPTY_STATE_SET;
        colors[i] = defaultColor;
        int i2 = i + 1;
        new ColorStateList(states, colors);
        return colorStateList;
    }

    @Dimension(unit = 0)
    private int getDefaultHeight() {
        boolean hasIconAndText = false;
        int i = 0;
        int count = this.tabs.size();
        while (true) {
            if (i < count) {
                Tab tab = this.tabs.get(i);
                if (tab != null && tab.getIcon() != null && !TextUtils.isEmpty(tab.getText())) {
                    hasIconAndText = true;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        return (!hasIconAndText || this.inlineLabel) ? 48 : 72;
    }

    private int getTabMinWidth() {
        if (this.requestedTabMinWidth != -1) {
            return this.requestedTabMinWidth;
        }
        return this.mode == 0 ? this.scrollableTabMinWidth : 0;
    }

    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        AttributeSet attributeSet2 = attributeSet;
        return generateDefaultLayoutParams();
    }

    /* access modifiers changed from: package-private */
    public int getTabMaxWidth() {
        return this.tabMaxWidth;
    }

    public static class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private int previousScrollState;
        private int scrollState;
        private final WeakReference<TabLayout> tabLayoutRef;

        public TabLayoutOnPageChangeListener(TabLayout tabLayout) {
            WeakReference<TabLayout> weakReference;
            new WeakReference<>(tabLayout);
            this.tabLayoutRef = weakReference;
        }

        public void onPageScrollStateChanged(int state) {
            this.previousScrollState = this.scrollState;
            this.scrollState = state;
        }

        public void onPageScrolled(int i, float f, int i2) {
            int position = i;
            float positionOffset = f;
            int i3 = i2;
            TabLayout tabLayout = (TabLayout) this.tabLayoutRef.get();
            if (tabLayout != null) {
                tabLayout.setScrollPosition(position, positionOffset, this.scrollState != 2 || this.previousScrollState == 1, (this.scrollState == 2 && this.previousScrollState == 0) ? false : true);
            }
        }

        public void onPageSelected(int i) {
            int position = i;
            TabLayout tabLayout = (TabLayout) this.tabLayoutRef.get();
            if (tabLayout != null && tabLayout.getSelectedTabPosition() != position && position < tabLayout.getTabCount()) {
                tabLayout.selectTab(tabLayout.getTabAt(position), this.scrollState == 0 || (this.scrollState == 2 && this.previousScrollState == 0));
            }
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.scrollState = 0;
            this.previousScrollState = 0;
        }
    }

    public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
        private final ViewPager viewPager;

        public ViewPagerOnTabSelectedListener(ViewPager viewPager2) {
            this.viewPager = viewPager2;
        }

        public void onTabSelected(Tab tab) {
            this.viewPager.setCurrentItem(tab.getPosition());
        }

        public void onTabUnselected(Tab tab) {
        }

        public void onTabReselected(Tab tab) {
        }
    }

    private class PagerAdapterObserver extends DataSetObserver {
        final /* synthetic */ TabLayout this$0;

        PagerAdapterObserver(TabLayout tabLayout) {
            this.this$0 = tabLayout;
        }

        public void onChanged() {
            this.this$0.populateFromPagerAdapter();
        }

        public void onInvalidated() {
            this.this$0.populateFromPagerAdapter();
        }
    }

    private class AdapterChangeListener implements ViewPager.OnAdapterChangeListener {
        private boolean autoRefresh;
        final /* synthetic */ TabLayout this$0;

        AdapterChangeListener(TabLayout tabLayout) {
            this.this$0 = tabLayout;
        }

        public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter pagerAdapter, @Nullable PagerAdapter pagerAdapter2) {
            PagerAdapter pagerAdapter3 = pagerAdapter;
            PagerAdapter newAdapter = pagerAdapter2;
            if (this.this$0.viewPager == viewPager) {
                this.this$0.setPagerAdapter(newAdapter, this.autoRefresh);
            }
        }

        /* access modifiers changed from: package-private */
        public void setAutoRefresh(boolean autoRefresh2) {
            boolean z = autoRefresh2;
            this.autoRefresh = z;
        }
    }
}
