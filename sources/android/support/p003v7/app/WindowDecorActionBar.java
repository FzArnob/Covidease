package android.support.p003v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.ViewPropertyAnimatorCompat;
import android.support.p000v4.view.ViewPropertyAnimatorListener;
import android.support.p000v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.p000v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.p003v7.app.ActionBar;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.content.res.AppCompatResources;
import android.support.p003v7.view.ActionBarPolicy;
import android.support.p003v7.view.ActionMode;
import android.support.p003v7.view.SupportMenuInflater;
import android.support.p003v7.view.ViewPropertyAnimatorCompatSet;
import android.support.p003v7.view.menu.MenuBuilder;
import android.support.p003v7.view.menu.MenuPopupHelper;
import android.support.p003v7.view.menu.SubMenuBuilder;
import android.support.p003v7.widget.ActionBarContainer;
import android.support.p003v7.widget.ActionBarContextView;
import android.support.p003v7.widget.ActionBarOverlayLayout;
import android.support.p003v7.widget.DecorToolbar;
import android.support.p003v7.widget.ScrollingTabContainerView;
import android.support.p003v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.app.WindowDecorActionBar */
public class WindowDecorActionBar extends ActionBar implements ActionBarOverlayLayout.ActionBarVisibilityCallback {
    static final /* synthetic */ boolean $assertionsDisabled = (!WindowDecorActionBar.class.desiredAssertionStatus());
    private static final long FADE_IN_DURATION_MS = 200;
    private static final long FADE_OUT_DURATION_MS = 100;
    private static final int INVALID_POSITION = -1;
    private static final String TAG = "WindowDecorActionBar";
    private static final Interpolator sHideInterpolator;
    private static final Interpolator sShowInterpolator;
    ActionModeImpl mActionMode;
    private Activity mActivity;
    ActionBarContainer mContainerView;
    boolean mContentAnimations;
    View mContentView;
    Context mContext;
    ActionBarContextView mContextView;
    private int mCurWindowVisibility;
    ViewPropertyAnimatorCompatSet mCurrentShowAnim;
    DecorToolbar mDecorToolbar;
    ActionMode mDeferredDestroyActionMode;
    ActionMode.Callback mDeferredModeDestroyCallback;
    private Dialog mDialog;
    private boolean mDisplayHomeAsUpSet;
    private boolean mHasEmbeddedTabs;
    boolean mHiddenByApp;
    boolean mHiddenBySystem;
    final ViewPropertyAnimatorListener mHideListener;
    boolean mHideOnContentScroll;
    private boolean mLastMenuVisibility;
    private ArrayList<ActionBar.OnMenuVisibilityListener> mMenuVisibilityListeners;
    private boolean mNowShowing;
    ActionBarOverlayLayout mOverlayLayout;
    private int mSavedTabPosition = -1;
    private TabImpl mSelectedTab;
    private boolean mShowHideAnimationEnabled;
    final ViewPropertyAnimatorListener mShowListener;
    private boolean mShowingForMode;
    ScrollingTabContainerView mTabScrollView;
    private ArrayList<TabImpl> mTabs;
    private Context mThemedContext;
    final ViewPropertyAnimatorUpdateListener mUpdateListener;

    static {
        Interpolator interpolator;
        Interpolator interpolator2;
        new AccelerateInterpolator();
        sHideInterpolator = interpolator;
        new DecelerateInterpolator();
        sShowInterpolator = interpolator2;
    }

    public WindowDecorActionBar(Activity activity, boolean overlayMode) {
        ArrayList<TabImpl> arrayList;
        ArrayList<ActionBar.OnMenuVisibilityListener> arrayList2;
        ViewPropertyAnimatorListener viewPropertyAnimatorListener;
        ViewPropertyAnimatorListener viewPropertyAnimatorListener2;
        ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener;
        Activity activity2 = activity;
        new ArrayList<>();
        this.mTabs = arrayList;
        new ArrayList<>();
        this.mMenuVisibilityListeners = arrayList2;
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        new ViewPropertyAnimatorListenerAdapter(this) {
            final /* synthetic */ WindowDecorActionBar this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationEnd(View view) {
                View view2 = view;
                if (this.this$0.mContentAnimations && this.this$0.mContentView != null) {
                    this.this$0.mContentView.setTranslationY(0.0f);
                    this.this$0.mContainerView.setTranslationY(0.0f);
                }
                this.this$0.mContainerView.setVisibility(8);
                this.this$0.mContainerView.setTransitioning(false);
                this.this$0.mCurrentShowAnim = null;
                this.this$0.completeDeferredDestroyActionMode();
                if (this.this$0.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets(this.this$0.mOverlayLayout);
                }
            }
        };
        this.mHideListener = viewPropertyAnimatorListener;
        new ViewPropertyAnimatorListenerAdapter(this) {
            final /* synthetic */ WindowDecorActionBar this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationEnd(View view) {
                View view2 = view;
                this.this$0.mCurrentShowAnim = null;
                this.this$0.mContainerView.requestLayout();
            }
        };
        this.mShowListener = viewPropertyAnimatorListener2;
        new ViewPropertyAnimatorUpdateListener(this) {
            final /* synthetic */ WindowDecorActionBar this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationUpdate(View view) {
                View view2 = view;
                ((View) this.this$0.mContainerView.getParent()).invalidate();
            }
        };
        this.mUpdateListener = viewPropertyAnimatorUpdateListener;
        this.mActivity = activity2;
        View decor = activity2.getWindow().getDecorView();
        init(decor);
        if (!overlayMode) {
            this.mContentView = decor.findViewById(16908290);
        }
    }

    public WindowDecorActionBar(Dialog dialog) {
        ArrayList<TabImpl> arrayList;
        ArrayList<ActionBar.OnMenuVisibilityListener> arrayList2;
        ViewPropertyAnimatorListener viewPropertyAnimatorListener;
        ViewPropertyAnimatorListener viewPropertyAnimatorListener2;
        ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener;
        Dialog dialog2 = dialog;
        new ArrayList<>();
        this.mTabs = arrayList;
        new ArrayList<>();
        this.mMenuVisibilityListeners = arrayList2;
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        new ViewPropertyAnimatorListenerAdapter(this) {
            final /* synthetic */ WindowDecorActionBar this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationEnd(View view) {
                View view2 = view;
                if (this.this$0.mContentAnimations && this.this$0.mContentView != null) {
                    this.this$0.mContentView.setTranslationY(0.0f);
                    this.this$0.mContainerView.setTranslationY(0.0f);
                }
                this.this$0.mContainerView.setVisibility(8);
                this.this$0.mContainerView.setTransitioning(false);
                this.this$0.mCurrentShowAnim = null;
                this.this$0.completeDeferredDestroyActionMode();
                if (this.this$0.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets(this.this$0.mOverlayLayout);
                }
            }
        };
        this.mHideListener = viewPropertyAnimatorListener;
        new ViewPropertyAnimatorListenerAdapter(this) {
            final /* synthetic */ WindowDecorActionBar this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationEnd(View view) {
                View view2 = view;
                this.this$0.mCurrentShowAnim = null;
                this.this$0.mContainerView.requestLayout();
            }
        };
        this.mShowListener = viewPropertyAnimatorListener2;
        new ViewPropertyAnimatorUpdateListener(this) {
            final /* synthetic */ WindowDecorActionBar this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationUpdate(View view) {
                View view2 = view;
                ((View) this.this$0.mContainerView.getParent()).invalidate();
            }
        };
        this.mUpdateListener = viewPropertyAnimatorUpdateListener;
        this.mDialog = dialog2;
        init(dialog2.getWindow().getDecorView());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WindowDecorActionBar(View view) {
        ArrayList<TabImpl> arrayList;
        ArrayList<ActionBar.OnMenuVisibilityListener> arrayList2;
        ViewPropertyAnimatorListener viewPropertyAnimatorListener;
        ViewPropertyAnimatorListener viewPropertyAnimatorListener2;
        ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener;
        Throwable th;
        View layout = view;
        new ArrayList<>();
        this.mTabs = arrayList;
        new ArrayList<>();
        this.mMenuVisibilityListeners = arrayList2;
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        new ViewPropertyAnimatorListenerAdapter(this) {
            final /* synthetic */ WindowDecorActionBar this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationEnd(View view) {
                View view2 = view;
                if (this.this$0.mContentAnimations && this.this$0.mContentView != null) {
                    this.this$0.mContentView.setTranslationY(0.0f);
                    this.this$0.mContainerView.setTranslationY(0.0f);
                }
                this.this$0.mContainerView.setVisibility(8);
                this.this$0.mContainerView.setTransitioning(false);
                this.this$0.mCurrentShowAnim = null;
                this.this$0.completeDeferredDestroyActionMode();
                if (this.this$0.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets(this.this$0.mOverlayLayout);
                }
            }
        };
        this.mHideListener = viewPropertyAnimatorListener;
        new ViewPropertyAnimatorListenerAdapter(this) {
            final /* synthetic */ WindowDecorActionBar this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationEnd(View view) {
                View view2 = view;
                this.this$0.mCurrentShowAnim = null;
                this.this$0.mContainerView.requestLayout();
            }
        };
        this.mShowListener = viewPropertyAnimatorListener2;
        new ViewPropertyAnimatorUpdateListener(this) {
            final /* synthetic */ WindowDecorActionBar this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationUpdate(View view) {
                View view2 = view;
                ((View) this.this$0.mContainerView.getParent()).invalidate();
            }
        };
        this.mUpdateListener = viewPropertyAnimatorUpdateListener;
        if ($assertionsDisabled || layout.isInEditMode()) {
            init(layout);
            return;
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    private void init(View view) {
        Throwable th;
        StringBuilder sb;
        View decor = view;
        this.mOverlayLayout = (ActionBarOverlayLayout) decor.findViewById(C0425R.C0427id.decor_content_parent);
        if (this.mOverlayLayout != null) {
            this.mOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.mDecorToolbar = getDecorToolbar(decor.findViewById(C0425R.C0427id.action_bar));
        this.mContextView = (ActionBarContextView) decor.findViewById(C0425R.C0427id.action_context_bar);
        this.mContainerView = (ActionBarContainer) decor.findViewById(C0425R.C0427id.action_bar_container);
        if (this.mDecorToolbar == null || this.mContextView == null || this.mContainerView == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append(getClass().getSimpleName()).append(" can only be used ").append("with a compatible window decor layout").toString());
            throw th2;
        }
        this.mContext = this.mDecorToolbar.getContext();
        boolean homeAsUp = (this.mDecorToolbar.getDisplayOptions() & 4) != 0;
        if (homeAsUp) {
            this.mDisplayHomeAsUpSet = true;
        }
        ActionBarPolicy abp = ActionBarPolicy.get(this.mContext);
        setHomeButtonEnabled(abp.enableHomeButtonByDefault() || homeAsUp);
        setHasEmbeddedTabs(abp.hasEmbeddedTabs());
        TypedArray a = this.mContext.obtainStyledAttributes((AttributeSet) null, C0425R.styleable.ActionBar, C0425R.attr.actionBarStyle, 0);
        if (a.getBoolean(C0425R.styleable.ActionBar_hideOnContentScroll, false)) {
            setHideOnContentScrollEnabled(true);
        }
        int elevation = a.getDimensionPixelSize(C0425R.styleable.ActionBar_elevation, 0);
        if (elevation != 0) {
            setElevation((float) elevation);
        }
        a.recycle();
    }

    private DecorToolbar getDecorToolbar(View view) {
        StringBuilder sb;
        View view2 = view;
        if (view2 instanceof DecorToolbar) {
            return (DecorToolbar) view2;
        }
        if (view2 instanceof Toolbar) {
            return ((Toolbar) view2).getWrapper();
        }
        IllegalStateException illegalStateException = r6;
        new StringBuilder();
        IllegalStateException illegalStateException2 = new IllegalStateException(sb.append("Can't make a decor toolbar out of ").append(view2 != null ? view2.getClass().getSimpleName() : "null").toString());
        throw illegalStateException;
    }

    public void setElevation(float elevation) {
        ViewCompat.setElevation(this.mContainerView, elevation);
    }

    public float getElevation() {
        return ViewCompat.getElevation(this.mContainerView);
    }

    public void onConfigurationChanged(Configuration configuration) {
        Configuration configuration2 = configuration;
        setHasEmbeddedTabs(ActionBarPolicy.get(this.mContext).hasEmbeddedTabs());
    }

    private void setHasEmbeddedTabs(boolean hasEmbeddedTabs) {
        this.mHasEmbeddedTabs = hasEmbeddedTabs;
        if (!this.mHasEmbeddedTabs) {
            this.mDecorToolbar.setEmbeddedTabView((ScrollingTabContainerView) null);
            this.mContainerView.setTabContainer(this.mTabScrollView);
        } else {
            this.mContainerView.setTabContainer((ScrollingTabContainerView) null);
            this.mDecorToolbar.setEmbeddedTabView(this.mTabScrollView);
        }
        boolean isInTabMode = getNavigationMode() == 2;
        if (this.mTabScrollView != null) {
            if (isInTabMode) {
                this.mTabScrollView.setVisibility(0);
                if (this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets(this.mOverlayLayout);
                }
            } else {
                this.mTabScrollView.setVisibility(8);
            }
        }
        this.mDecorToolbar.setCollapsible(!this.mHasEmbeddedTabs && isInTabMode);
        this.mOverlayLayout.setHasNonEmbeddedTabs(!this.mHasEmbeddedTabs && isInTabMode);
    }

    private void ensureTabsExist() {
        ScrollingTabContainerView scrollingTabContainerView;
        if (this.mTabScrollView == null) {
            new ScrollingTabContainerView(this.mContext);
            ScrollingTabContainerView tabScroller = scrollingTabContainerView;
            if (this.mHasEmbeddedTabs) {
                tabScroller.setVisibility(0);
                this.mDecorToolbar.setEmbeddedTabView(tabScroller);
            } else {
                if (getNavigationMode() == 2) {
                    tabScroller.setVisibility(0);
                    if (this.mOverlayLayout != null) {
                        ViewCompat.requestApplyInsets(this.mOverlayLayout);
                    }
                } else {
                    tabScroller.setVisibility(8);
                }
                this.mContainerView.setTabContainer(tabScroller);
            }
            this.mTabScrollView = tabScroller;
        }
    }

    /* access modifiers changed from: package-private */
    public void completeDeferredDestroyActionMode() {
        if (this.mDeferredModeDestroyCallback != null) {
            this.mDeferredModeDestroyCallback.onDestroyActionMode(this.mDeferredDestroyActionMode);
            this.mDeferredDestroyActionMode = null;
            this.mDeferredModeDestroyCallback = null;
        }
    }

    public void onWindowVisibilityChanged(int visibility) {
        int i = visibility;
        this.mCurWindowVisibility = i;
    }

    public void setShowHideAnimationEnabled(boolean z) {
        boolean enabled = z;
        this.mShowHideAnimationEnabled = enabled;
        if (!enabled && this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
        }
    }

    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener listener) {
        boolean add = this.mMenuVisibilityListeners.add(listener);
    }

    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener listener) {
        boolean remove = this.mMenuVisibilityListeners.remove(listener);
    }

    public void dispatchMenuVisibilityChanged(boolean z) {
        boolean isVisible = z;
        if (isVisible != this.mLastMenuVisibility) {
            this.mLastMenuVisibility = isVisible;
            int count = this.mMenuVisibilityListeners.size();
            for (int i = 0; i < count; i++) {
                this.mMenuVisibilityListeners.get(i).onMenuVisibilityChanged(isVisible);
            }
        }
    }

    public void setCustomView(int resId) {
        setCustomView(LayoutInflater.from(getThemedContext()).inflate(resId, this.mDecorToolbar.getViewGroup(), false));
    }

    public void setDisplayUseLogoEnabled(boolean useLogo) {
        setDisplayOptions(useLogo ? 1 : 0, 1);
    }

    public void setDisplayShowHomeEnabled(boolean showHome) {
        setDisplayOptions(showHome ? 2 : 0, 2);
    }

    public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
        setDisplayOptions(showHomeAsUp ? 4 : 0, 4);
    }

    public void setDisplayShowTitleEnabled(boolean showTitle) {
        setDisplayOptions(showTitle ? 8 : 0, 8);
    }

    public void setDisplayShowCustomEnabled(boolean showCustom) {
        setDisplayOptions(showCustom ? 16 : 0, 16);
    }

    public void setHomeButtonEnabled(boolean enable) {
        this.mDecorToolbar.setHomeButtonEnabled(enable);
    }

    public void setTitle(int resId) {
        setTitle((CharSequence) this.mContext.getString(resId));
    }

    public void setSubtitle(int resId) {
        setSubtitle((CharSequence) this.mContext.getString(resId));
    }

    public void setSelectedNavigationItem(int i) {
        Throwable th;
        int position = i;
        switch (this.mDecorToolbar.getNavigationMode()) {
            case 1:
                this.mDecorToolbar.setDropdownSelectedPosition(position);
                return;
            case 2:
                selectTab(this.mTabs.get(position));
                return;
            default:
                Throwable th2 = th;
                new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
                throw th2;
        }
    }

    public void removeAllTabs() {
        cleanupTabs();
    }

    private void cleanupTabs() {
        if (this.mSelectedTab != null) {
            selectTab((ActionBar.Tab) null);
        }
        this.mTabs.clear();
        if (this.mTabScrollView != null) {
            this.mTabScrollView.removeAllTabs();
        }
        this.mSavedTabPosition = -1;
    }

    public void setTitle(CharSequence title) {
        this.mDecorToolbar.setTitle(title);
    }

    public void setWindowTitle(CharSequence title) {
        this.mDecorToolbar.setWindowTitle(title);
    }

    public boolean requestFocus() {
        ViewGroup viewGroup = this.mDecorToolbar.getViewGroup();
        if (viewGroup == null || viewGroup.hasFocus()) {
            return false;
        }
        boolean requestFocus = viewGroup.requestFocus();
        return true;
    }

    public void setSubtitle(CharSequence subtitle) {
        this.mDecorToolbar.setSubtitle(subtitle);
    }

    public void setDisplayOptions(int i) {
        int options = i;
        if ((options & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mDecorToolbar.setDisplayOptions(options);
    }

    public void setDisplayOptions(int i, int i2) {
        int options = i;
        int mask = i2;
        int current = this.mDecorToolbar.getDisplayOptions();
        if ((mask & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mDecorToolbar.setDisplayOptions((options & mask) | (current & (mask ^ -1)));
    }

    public void setBackgroundDrawable(Drawable d) {
        this.mContainerView.setPrimaryBackground(d);
    }

    public void setStackedBackgroundDrawable(Drawable d) {
        this.mContainerView.setStackedBackground(d);
    }

    public void setSplitBackgroundDrawable(Drawable d) {
    }

    public View getCustomView() {
        return this.mDecorToolbar.getCustomView();
    }

    public CharSequence getTitle() {
        return this.mDecorToolbar.getTitle();
    }

    public CharSequence getSubtitle() {
        return this.mDecorToolbar.getSubtitle();
    }

    public int getNavigationMode() {
        return this.mDecorToolbar.getNavigationMode();
    }

    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }

    public ActionMode startActionMode(ActionMode.Callback callback) {
        ActionModeImpl actionModeImpl;
        ActionMode.Callback callback2 = callback;
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        this.mOverlayLayout.setHideOnContentScrollEnabled(false);
        this.mContextView.killMode();
        new ActionModeImpl(this, this.mContextView.getContext(), callback2);
        ActionModeImpl mode = actionModeImpl;
        if (!mode.dispatchOnCreate()) {
            return null;
        }
        this.mActionMode = mode;
        mode.invalidate();
        this.mContextView.initForMode(mode);
        animateToMode(true);
        this.mContextView.sendAccessibilityEvent(32);
        return mode;
    }

    private void configureTab(ActionBar.Tab tab, int i) {
        Throwable th;
        int position = i;
        TabImpl tabi = (TabImpl) tab;
        if (tabi.getCallback() == null) {
            Throwable th2 = th;
            new IllegalStateException("Action Bar Tab must have a Callback");
            throw th2;
        }
        tabi.setPosition(position);
        this.mTabs.add(position, tabi);
        int count = this.mTabs.size();
        for (int i2 = position + 1; i2 < count; i2++) {
            this.mTabs.get(i2).setPosition(i2);
        }
    }

    public void addTab(ActionBar.Tab tab) {
        addTab(tab, this.mTabs.isEmpty());
    }

    public void addTab(ActionBar.Tab tab, int position) {
        addTab(tab, position, this.mTabs.isEmpty());
    }

    public void addTab(ActionBar.Tab tab, boolean z) {
        ActionBar.Tab tab2 = tab;
        boolean setSelected = z;
        ensureTabsExist();
        this.mTabScrollView.addTab(tab2, setSelected);
        configureTab(tab2, this.mTabs.size());
        if (setSelected) {
            selectTab(tab2);
        }
    }

    public void addTab(ActionBar.Tab tab, int i, boolean z) {
        ActionBar.Tab tab2 = tab;
        int position = i;
        boolean setSelected = z;
        ensureTabsExist();
        this.mTabScrollView.addTab(tab2, position, setSelected);
        configureTab(tab2, position);
        if (setSelected) {
            selectTab(tab2);
        }
    }

    public ActionBar.Tab newTab() {
        ActionBar.Tab tab;
        new TabImpl(this);
        return tab;
    }

    public void removeTab(ActionBar.Tab tab) {
        removeTabAt(tab.getPosition());
    }

    public void removeTabAt(int i) {
        int i2;
        int position = i;
        if (this.mTabScrollView != null) {
            if (this.mSelectedTab != null) {
                i2 = this.mSelectedTab.getPosition();
            } else {
                i2 = this.mSavedTabPosition;
            }
            int selectedTabPosition = i2;
            this.mTabScrollView.removeTabAt(position);
            TabImpl removedTab = this.mTabs.remove(position);
            if (removedTab != null) {
                removedTab.setPosition(-1);
            }
            int newTabCount = this.mTabs.size();
            for (int i3 = position; i3 < newTabCount; i3++) {
                this.mTabs.get(i3).setPosition(i3);
            }
            if (selectedTabPosition == position) {
                selectTab(this.mTabs.isEmpty() ? null : this.mTabs.get(Math.max(0, position - 1)));
            }
        }
    }

    public void selectTab(ActionBar.Tab tab) {
        FragmentTransaction trans;
        ActionBar.Tab tab2 = tab;
        if (getNavigationMode() != 2) {
            this.mSavedTabPosition = tab2 != null ? tab2.getPosition() : -1;
            return;
        }
        if (!(this.mActivity instanceof FragmentActivity) || this.mDecorToolbar.getViewGroup().isInEditMode()) {
            trans = null;
        } else {
            trans = ((FragmentActivity) this.mActivity).getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
        }
        if (this.mSelectedTab != tab2) {
            this.mTabScrollView.setTabSelected(tab2 != null ? tab2.getPosition() : -1);
            if (this.mSelectedTab != null) {
                this.mSelectedTab.getCallback().onTabUnselected(this.mSelectedTab, trans);
            }
            this.mSelectedTab = (TabImpl) tab2;
            if (this.mSelectedTab != null) {
                this.mSelectedTab.getCallback().onTabSelected(this.mSelectedTab, trans);
            }
        } else if (this.mSelectedTab != null) {
            this.mSelectedTab.getCallback().onTabReselected(this.mSelectedTab, trans);
            this.mTabScrollView.animateToTab(tab2.getPosition());
        }
        if (trans != null && !trans.isEmpty()) {
            int commit = trans.commit();
        }
    }

    public ActionBar.Tab getSelectedTab() {
        return this.mSelectedTab;
    }

    public int getHeight() {
        return this.mContainerView.getHeight();
    }

    public void enableContentAnimations(boolean enabled) {
        boolean z = enabled;
        this.mContentAnimations = z;
    }

    public void show() {
        if (this.mHiddenByApp) {
            this.mHiddenByApp = false;
            updateVisibility(false);
        }
    }

    private void showForActionMode() {
        if (!this.mShowingForMode) {
            this.mShowingForMode = true;
            if (this.mOverlayLayout != null) {
                this.mOverlayLayout.setShowingForActionMode(true);
            }
            updateVisibility(false);
        }
    }

    public void showForSystem() {
        if (this.mHiddenBySystem) {
            this.mHiddenBySystem = false;
            updateVisibility(true);
        }
    }

    public void hide() {
        if (!this.mHiddenByApp) {
            this.mHiddenByApp = true;
            updateVisibility(false);
        }
    }

    private void hideForActionMode() {
        if (this.mShowingForMode) {
            this.mShowingForMode = false;
            if (this.mOverlayLayout != null) {
                this.mOverlayLayout.setShowingForActionMode(false);
            }
            updateVisibility(false);
        }
    }

    public void hideForSystem() {
        if (!this.mHiddenBySystem) {
            this.mHiddenBySystem = true;
            updateVisibility(true);
        }
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        Throwable th;
        boolean hideOnContentScroll = z;
        if (!hideOnContentScroll || this.mOverlayLayout.isInOverlayMode()) {
            this.mHideOnContentScroll = hideOnContentScroll;
            this.mOverlayLayout.setHideOnContentScrollEnabled(hideOnContentScroll);
            return;
        }
        Throwable th2 = th;
        new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        throw th2;
    }

    public boolean isHideOnContentScrollEnabled() {
        return this.mOverlayLayout.isHideOnContentScrollEnabled();
    }

    public int getHideOffset() {
        return this.mOverlayLayout.getActionBarHideOffset();
    }

    public void setHideOffset(int i) {
        Throwable th;
        int offset = i;
        if (offset == 0 || this.mOverlayLayout.isInOverlayMode()) {
            this.mOverlayLayout.setActionBarHideOffset(offset);
            return;
        }
        Throwable th2 = th;
        new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to set a non-zero hide offset");
        throw th2;
    }

    static boolean checkShowingFlags(boolean z, boolean z2, boolean showingForMode) {
        boolean hiddenByApp = z;
        boolean hiddenBySystem = z2;
        if (showingForMode) {
            return true;
        }
        if (hiddenByApp || hiddenBySystem) {
            return false;
        }
        return true;
    }

    private void updateVisibility(boolean z) {
        boolean fromSystem = z;
        if (checkShowingFlags(this.mHiddenByApp, this.mHiddenBySystem, this.mShowingForMode)) {
            if (!this.mNowShowing) {
                this.mNowShowing = true;
                doShow(fromSystem);
            }
        } else if (this.mNowShowing) {
            this.mNowShowing = false;
            doHide(fromSystem);
        }
    }

    public void doShow(boolean z) {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet;
        boolean fromSystem = z;
        if (this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
        }
        this.mContainerView.setVisibility(0);
        if (this.mCurWindowVisibility != 0 || (!this.mShowHideAnimationEnabled && !fromSystem)) {
            this.mContainerView.setAlpha(1.0f);
            this.mContainerView.setTranslationY(0.0f);
            if (this.mContentAnimations && this.mContentView != null) {
                this.mContentView.setTranslationY(0.0f);
            }
            this.mShowListener.onAnimationEnd((View) null);
        } else {
            this.mContainerView.setTranslationY(0.0f);
            float startingY = (float) (-this.mContainerView.getHeight());
            if (fromSystem) {
                int[] topLeft = {0, 0};
                this.mContainerView.getLocationInWindow(topLeft);
                startingY -= (float) topLeft[1];
            }
            this.mContainerView.setTranslationY(startingY);
            new ViewPropertyAnimatorCompatSet();
            ViewPropertyAnimatorCompatSet anim = viewPropertyAnimatorCompatSet;
            ViewPropertyAnimatorCompat a = ViewCompat.animate(this.mContainerView).translationY(0.0f);
            ViewPropertyAnimatorCompat updateListener = a.setUpdateListener(this.mUpdateListener);
            ViewPropertyAnimatorCompatSet play = anim.play(a);
            if (this.mContentAnimations && this.mContentView != null) {
                this.mContentView.setTranslationY(startingY);
                ViewPropertyAnimatorCompatSet play2 = anim.play(ViewCompat.animate(this.mContentView).translationY(0.0f));
            }
            ViewPropertyAnimatorCompatSet interpolator = anim.setInterpolator(sShowInterpolator);
            ViewPropertyAnimatorCompatSet duration = anim.setDuration(250);
            ViewPropertyAnimatorCompatSet listener = anim.setListener(this.mShowListener);
            this.mCurrentShowAnim = anim;
            anim.start();
        }
        if (this.mOverlayLayout != null) {
            ViewCompat.requestApplyInsets(this.mOverlayLayout);
        }
    }

    public void doHide(boolean z) {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet;
        boolean fromSystem = z;
        if (this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
        }
        if (this.mCurWindowVisibility != 0 || (!this.mShowHideAnimationEnabled && !fromSystem)) {
            this.mHideListener.onAnimationEnd((View) null);
            return;
        }
        this.mContainerView.setAlpha(1.0f);
        this.mContainerView.setTransitioning(true);
        new ViewPropertyAnimatorCompatSet();
        ViewPropertyAnimatorCompatSet anim = viewPropertyAnimatorCompatSet;
        float endingY = (float) (-this.mContainerView.getHeight());
        if (fromSystem) {
            int[] topLeft = {0, 0};
            this.mContainerView.getLocationInWindow(topLeft);
            endingY -= (float) topLeft[1];
        }
        ViewPropertyAnimatorCompat a = ViewCompat.animate(this.mContainerView).translationY(endingY);
        ViewPropertyAnimatorCompat updateListener = a.setUpdateListener(this.mUpdateListener);
        ViewPropertyAnimatorCompatSet play = anim.play(a);
        if (this.mContentAnimations && this.mContentView != null) {
            ViewPropertyAnimatorCompatSet play2 = anim.play(ViewCompat.animate(this.mContentView).translationY(endingY));
        }
        ViewPropertyAnimatorCompatSet interpolator = anim.setInterpolator(sHideInterpolator);
        ViewPropertyAnimatorCompatSet duration = anim.setDuration(250);
        ViewPropertyAnimatorCompatSet listener = anim.setListener(this.mHideListener);
        this.mCurrentShowAnim = anim;
        anim.start();
    }

    public boolean isShowing() {
        int height = getHeight();
        return this.mNowShowing && (height == 0 || getHideOffset() < height);
    }

    public void animateToMode(boolean z) {
        ViewPropertyAnimatorCompat fadeIn;
        ViewPropertyAnimatorCompat fadeOut;
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet;
        boolean toActionMode = z;
        if (toActionMode) {
            showForActionMode();
        } else {
            hideForActionMode();
        }
        if (shouldAnimateContextView()) {
            if (toActionMode) {
                fadeOut = this.mDecorToolbar.setupAnimatorToVisibility(4, FADE_OUT_DURATION_MS);
                fadeIn = this.mContextView.setupAnimatorToVisibility(0, FADE_IN_DURATION_MS);
            } else {
                fadeIn = this.mDecorToolbar.setupAnimatorToVisibility(0, FADE_IN_DURATION_MS);
                fadeOut = this.mContextView.setupAnimatorToVisibility(8, FADE_OUT_DURATION_MS);
            }
            new ViewPropertyAnimatorCompatSet();
            ViewPropertyAnimatorCompatSet set = viewPropertyAnimatorCompatSet;
            ViewPropertyAnimatorCompatSet playSequentially = set.playSequentially(fadeOut, fadeIn);
            set.start();
        } else if (toActionMode) {
            this.mDecorToolbar.setVisibility(4);
            this.mContextView.setVisibility(0);
        } else {
            this.mDecorToolbar.setVisibility(0);
            this.mContextView.setVisibility(8);
        }
    }

    private boolean shouldAnimateContextView() {
        return ViewCompat.isLaidOut(this.mContainerView);
    }

    public Context getThemedContext() {
        TypedValue typedValue;
        Context context;
        if (this.mThemedContext == null) {
            new TypedValue();
            TypedValue outValue = typedValue;
            boolean resolveAttribute = this.mContext.getTheme().resolveAttribute(C0425R.attr.actionBarWidgetTheme, outValue, true);
            int targetThemeRes = outValue.resourceId;
            if (targetThemeRes != 0) {
                new ContextThemeWrapper(this.mContext, targetThemeRes);
                this.mThemedContext = context;
            } else {
                this.mThemedContext = this.mContext;
            }
        }
        return this.mThemedContext;
    }

    public boolean isTitleTruncated() {
        return this.mDecorToolbar != null && this.mDecorToolbar.isTitleTruncated();
    }

    public void setHomeAsUpIndicator(Drawable indicator) {
        this.mDecorToolbar.setNavigationIcon(indicator);
    }

    public void setHomeAsUpIndicator(int resId) {
        this.mDecorToolbar.setNavigationIcon(resId);
    }

    public void setHomeActionContentDescription(CharSequence description) {
        this.mDecorToolbar.setNavigationContentDescription(description);
    }

    public void setHomeActionContentDescription(int resId) {
        this.mDecorToolbar.setNavigationContentDescription(resId);
    }

    public void onContentScrollStarted() {
        if (this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
            this.mCurrentShowAnim = null;
        }
    }

    public void onContentScrollStopped() {
    }

    public boolean collapseActionView() {
        if (this.mDecorToolbar == null || !this.mDecorToolbar.hasExpandedActionView()) {
            return false;
        }
        this.mDecorToolbar.collapseActionView();
        return true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* renamed from: android.support.v7.app.WindowDecorActionBar$ActionModeImpl */
    public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {
        private final Context mActionModeContext;
        private ActionMode.Callback mCallback;
        private WeakReference<View> mCustomView;
        private final MenuBuilder mMenu;
        final /* synthetic */ WindowDecorActionBar this$0;

        public ActionModeImpl(WindowDecorActionBar this$02, Context context, ActionMode.Callback callback) {
            MenuBuilder menuBuilder;
            Context context2 = context;
            this.this$0 = this$02;
            this.mActionModeContext = context2;
            this.mCallback = callback;
            new MenuBuilder(context2);
            this.mMenu = menuBuilder.setDefaultShowAsAction(1);
            this.mMenu.setCallback(this);
        }

        public MenuInflater getMenuInflater() {
            MenuInflater menuInflater;
            new SupportMenuInflater(this.mActionModeContext);
            return menuInflater;
        }

        public Menu getMenu() {
            return this.mMenu;
        }

        public void finish() {
            if (this.this$0.mActionMode == this) {
                if (!WindowDecorActionBar.checkShowingFlags(this.this$0.mHiddenByApp, this.this$0.mHiddenBySystem, false)) {
                    this.this$0.mDeferredDestroyActionMode = this;
                    this.this$0.mDeferredModeDestroyCallback = this.mCallback;
                } else {
                    this.mCallback.onDestroyActionMode(this);
                }
                this.mCallback = null;
                this.this$0.animateToMode(false);
                this.this$0.mContextView.closeMode();
                this.this$0.mDecorToolbar.getViewGroup().sendAccessibilityEvent(32);
                this.this$0.mOverlayLayout.setHideOnContentScrollEnabled(this.this$0.mHideOnContentScroll);
                this.this$0.mActionMode = null;
            }
        }

        public void invalidate() {
            if (this.this$0.mActionMode == this) {
                this.mMenu.stopDispatchingItemsChanged();
                try {
                    boolean onPrepareActionMode = this.mCallback.onPrepareActionMode(this, this.mMenu);
                    this.mMenu.startDispatchingItemsChanged();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    this.mMenu.startDispatchingItemsChanged();
                    throw th2;
                }
            }
        }

        /* JADX INFO: finally extract failed */
        public boolean dispatchOnCreate() {
            this.mMenu.stopDispatchingItemsChanged();
            try {
                boolean onCreateActionMode = this.mCallback.onCreateActionMode(this, this.mMenu);
                this.mMenu.startDispatchingItemsChanged();
                return onCreateActionMode;
            } catch (Throwable th) {
                Throwable th2 = th;
                this.mMenu.startDispatchingItemsChanged();
                throw th2;
            }
        }

        public void setCustomView(View view) {
            WeakReference<View> weakReference;
            View view2 = view;
            this.this$0.mContextView.setCustomView(view2);
            new WeakReference<>(view2);
            this.mCustomView = weakReference;
        }

        public void setSubtitle(CharSequence subtitle) {
            this.this$0.mContextView.setSubtitle(subtitle);
        }

        public void setTitle(CharSequence title) {
            this.this$0.mContextView.setTitle(title);
        }

        public void setTitle(int resId) {
            setTitle((CharSequence) this.this$0.mContext.getResources().getString(resId));
        }

        public void setSubtitle(int resId) {
            setSubtitle((CharSequence) this.this$0.mContext.getResources().getString(resId));
        }

        public CharSequence getTitle() {
            return this.this$0.mContextView.getTitle();
        }

        public CharSequence getSubtitle() {
            return this.this$0.mContextView.getSubtitle();
        }

        public void setTitleOptionalHint(boolean z) {
            boolean titleOptional = z;
            super.setTitleOptionalHint(titleOptional);
            this.this$0.mContextView.setTitleOptional(titleOptional);
        }

        public boolean isTitleOptional() {
            return this.this$0.mContextView.isTitleOptional();
        }

        public View getCustomView() {
            return this.mCustomView != null ? (View) this.mCustomView.get() : null;
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            MenuBuilder menuBuilder2 = menuBuilder;
            MenuItem item = menuItem;
            if (this.mCallback != null) {
                return this.mCallback.onActionItemClicked(this, item);
            }
            return false;
        }

        public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
        }

        public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
            MenuPopupHelper menuPopupHelper;
            SubMenuBuilder subMenu = subMenuBuilder;
            if (this.mCallback == null) {
                return false;
            }
            if (!subMenu.hasVisibleItems()) {
                return true;
            }
            new MenuPopupHelper(this.this$0.getThemedContext(), subMenu);
            menuPopupHelper.show();
            return true;
        }

        public void onCloseSubMenu(SubMenuBuilder menu) {
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
            MenuBuilder menuBuilder2 = menuBuilder;
            if (this.mCallback != null) {
                invalidate();
                boolean showOverflowMenu = this.this$0.mContextView.showOverflowMenu();
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* renamed from: android.support.v7.app.WindowDecorActionBar$TabImpl */
    public class TabImpl extends ActionBar.Tab {
        private ActionBar.TabListener mCallback;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        private int mPosition = -1;
        private Object mTag;
        private CharSequence mText;
        final /* synthetic */ WindowDecorActionBar this$0;

        public TabImpl(WindowDecorActionBar this$02) {
            this.this$0 = this$02;
        }

        public Object getTag() {
            return this.mTag;
        }

        public ActionBar.Tab setTag(Object tag) {
            this.mTag = tag;
            return this;
        }

        public ActionBar.TabListener getCallback() {
            return this.mCallback;
        }

        public ActionBar.Tab setTabListener(ActionBar.TabListener callback) {
            this.mCallback = callback;
            return this;
        }

        public View getCustomView() {
            return this.mCustomView;
        }

        public ActionBar.Tab setCustomView(View view) {
            this.mCustomView = view;
            if (this.mPosition >= 0) {
                this.this$0.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        public ActionBar.Tab setCustomView(int layoutResId) {
            return setCustomView(LayoutInflater.from(this.this$0.getThemedContext()).inflate(layoutResId, (ViewGroup) null));
        }

        public Drawable getIcon() {
            return this.mIcon;
        }

        public int getPosition() {
            return this.mPosition;
        }

        public void setPosition(int position) {
            int i = position;
            this.mPosition = i;
        }

        public CharSequence getText() {
            return this.mText;
        }

        public ActionBar.Tab setIcon(Drawable icon) {
            this.mIcon = icon;
            if (this.mPosition >= 0) {
                this.this$0.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        public ActionBar.Tab setIcon(int resId) {
            return setIcon(AppCompatResources.getDrawable(this.this$0.mContext, resId));
        }

        public ActionBar.Tab setText(CharSequence text) {
            this.mText = text;
            if (this.mPosition >= 0) {
                this.this$0.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        public ActionBar.Tab setText(int resId) {
            return setText(this.this$0.mContext.getResources().getText(resId));
        }

        public void select() {
            this.this$0.selectTab(this);
        }

        public ActionBar.Tab setContentDescription(int resId) {
            return setContentDescription(this.this$0.mContext.getResources().getText(resId));
        }

        public ActionBar.Tab setContentDescription(CharSequence contentDesc) {
            this.mContentDesc = contentDesc;
            if (this.mPosition >= 0) {
                this.this$0.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        public CharSequence getContentDescription() {
            return this.mContentDesc;
        }
    }

    public void setCustomView(View view) {
        this.mDecorToolbar.setCustomView(view);
    }

    public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
        View view2 = view;
        view2.setLayoutParams(layoutParams);
        this.mDecorToolbar.setCustomView(view2);
    }

    public void setListNavigationCallbacks(SpinnerAdapter adapter, ActionBar.OnNavigationListener callback) {
        AdapterView.OnItemSelectedListener onItemSelectedListener;
        new NavItemSelectedListener(callback);
        this.mDecorToolbar.setDropdownParams(adapter, onItemSelectedListener);
    }

    public int getSelectedNavigationIndex() {
        switch (this.mDecorToolbar.getNavigationMode()) {
            case 1:
                return this.mDecorToolbar.getDropdownSelectedPosition();
            case 2:
                return this.mSelectedTab != null ? this.mSelectedTab.getPosition() : -1;
            default:
                return -1;
        }
    }

    public int getNavigationItemCount() {
        switch (this.mDecorToolbar.getNavigationMode()) {
            case 1:
                return this.mDecorToolbar.getDropdownItemCount();
            case 2:
                return this.mTabs.size();
            default:
                return 0;
        }
    }

    public int getTabCount() {
        return this.mTabs.size();
    }

    public void setNavigationMode(int i) {
        int mode = i;
        int oldMode = this.mDecorToolbar.getNavigationMode();
        switch (oldMode) {
            case 2:
                this.mSavedTabPosition = getSelectedNavigationIndex();
                selectTab((ActionBar.Tab) null);
                this.mTabScrollView.setVisibility(8);
                break;
        }
        if (!(oldMode == mode || this.mHasEmbeddedTabs || this.mOverlayLayout == null)) {
            ViewCompat.requestApplyInsets(this.mOverlayLayout);
        }
        this.mDecorToolbar.setNavigationMode(mode);
        switch (mode) {
            case 2:
                ensureTabsExist();
                this.mTabScrollView.setVisibility(0);
                if (this.mSavedTabPosition != -1) {
                    setSelectedNavigationItem(this.mSavedTabPosition);
                    this.mSavedTabPosition = -1;
                    break;
                }
                break;
        }
        this.mDecorToolbar.setCollapsible(mode == 2 && !this.mHasEmbeddedTabs);
        this.mOverlayLayout.setHasNonEmbeddedTabs(mode == 2 && !this.mHasEmbeddedTabs);
    }

    public ActionBar.Tab getTabAt(int index) {
        return this.mTabs.get(index);
    }

    public void setIcon(int resId) {
        this.mDecorToolbar.setIcon(resId);
    }

    public void setIcon(Drawable icon) {
        this.mDecorToolbar.setIcon(icon);
    }

    public boolean hasIcon() {
        return this.mDecorToolbar.hasIcon();
    }

    public void setLogo(int resId) {
        this.mDecorToolbar.setLogo(resId);
    }

    public void setLogo(Drawable logo) {
        this.mDecorToolbar.setLogo(logo);
    }

    public boolean hasLogo() {
        return this.mDecorToolbar.hasLogo();
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean z) {
        boolean enable = z;
        if (!this.mDisplayHomeAsUpSet) {
            setDisplayHomeAsUpEnabled(enable);
        }
    }

    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        int keyCode = i;
        KeyEvent event = keyEvent;
        if (this.mActionMode == null) {
            return false;
        }
        Menu menu = this.mActionMode.getMenu();
        if (menu == null) {
            return false;
        }
        menu.setQwertyMode(KeyCharacterMap.load(event != null ? event.getDeviceId() : -1).getKeyboardType() != 1);
        return menu.performShortcut(keyCode, event, 0);
    }
}
