package android.support.p003v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.ViewPropertyAnimatorCompat;
import android.support.p000v4.view.ViewPropertyAnimatorListener;
import android.support.p000v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.content.res.AppCompatResources;
import android.support.p003v7.view.menu.ActionMenuItem;
import android.support.p003v7.view.menu.MenuBuilder;
import android.support.p003v7.view.menu.MenuPresenter;
import android.support.p003v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.ToolbarWidgetWrapper */
public class ToolbarWidgetWrapper implements DecorToolbar {
    private static final int AFFECTS_LOGO_MASK = 3;
    private static final long DEFAULT_FADE_DURATION_MS = 200;
    private static final String TAG = "ToolbarWidgetWrapper";
    private ActionMenuPresenter mActionMenuPresenter;
    private View mCustomView;
    private int mDefaultNavigationContentDescription;
    private Drawable mDefaultNavigationIcon;
    private int mDisplayOpts;
    private CharSequence mHomeDescription;
    private Drawable mIcon;
    private Drawable mLogo;
    boolean mMenuPrepared;
    private Drawable mNavIcon;
    private int mNavigationMode;
    private Spinner mSpinner;
    private CharSequence mSubtitle;
    private View mTabView;
    CharSequence mTitle;
    private boolean mTitleSet;
    Toolbar mToolbar;
    Window.Callback mWindowCallback;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ToolbarWidgetWrapper(Toolbar toolbar, boolean style) {
        this(toolbar, style, C0425R.string.abc_action_bar_up_description, C0425R.C0426drawable.abc_ic_ab_back_material);
    }

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean z, int i, int i2) {
        View.OnClickListener onClickListener;
        Toolbar toolbar2 = toolbar;
        boolean style = z;
        int defaultNavigationContentDescription = i;
        int i3 = i2;
        this.mNavigationMode = 0;
        this.mDefaultNavigationContentDescription = 0;
        this.mToolbar = toolbar2;
        this.mTitle = toolbar2.getTitle();
        this.mSubtitle = toolbar2.getSubtitle();
        this.mTitleSet = this.mTitle != null;
        this.mNavIcon = toolbar2.getNavigationIcon();
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(toolbar2.getContext(), (AttributeSet) null, C0425R.styleable.ActionBar, C0425R.attr.actionBarStyle, 0);
        this.mDefaultNavigationIcon = a.getDrawable(C0425R.styleable.ActionBar_homeAsUpIndicator);
        if (style) {
            CharSequence title = a.getText(C0425R.styleable.ActionBar_title);
            if (!TextUtils.isEmpty(title)) {
                setTitle(title);
            }
            CharSequence subtitle = a.getText(C0425R.styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(subtitle)) {
                setSubtitle(subtitle);
            }
            Drawable logo = a.getDrawable(C0425R.styleable.ActionBar_logo);
            if (logo != null) {
                setLogo(logo);
            }
            Drawable icon = a.getDrawable(C0425R.styleable.ActionBar_icon);
            if (icon != null) {
                setIcon(icon);
            }
            if (this.mNavIcon == null) {
                if (this.mDefaultNavigationIcon != null) {
                    setNavigationIcon(this.mDefaultNavigationIcon);
                }
            }
            setDisplayOptions(a.getInt(C0425R.styleable.ActionBar_displayOptions, 0));
            int customNavId = a.getResourceId(C0425R.styleable.ActionBar_customNavigationLayout, 0);
            if (customNavId != 0) {
                setCustomView(LayoutInflater.from(this.mToolbar.getContext()).inflate(customNavId, this.mToolbar, false));
                setDisplayOptions(this.mDisplayOpts | 16);
            }
            int height = a.getLayoutDimension(C0425R.styleable.ActionBar_height, 0);
            if (height > 0) {
                ViewGroup.LayoutParams lp = this.mToolbar.getLayoutParams();
                lp.height = height;
                this.mToolbar.setLayoutParams(lp);
            }
            int contentInsetStart = a.getDimensionPixelOffset(C0425R.styleable.ActionBar_contentInsetStart, -1);
            int contentInsetEnd = a.getDimensionPixelOffset(C0425R.styleable.ActionBar_contentInsetEnd, -1);
            if (contentInsetStart >= 0 || contentInsetEnd >= 0) {
                this.mToolbar.setContentInsetsRelative(Math.max(contentInsetStart, 0), Math.max(contentInsetEnd, 0));
            }
            int titleTextStyle = a.getResourceId(C0425R.styleable.ActionBar_titleTextStyle, 0);
            if (titleTextStyle != 0) {
                this.mToolbar.setTitleTextAppearance(this.mToolbar.getContext(), titleTextStyle);
            }
            int subtitleTextStyle = a.getResourceId(C0425R.styleable.ActionBar_subtitleTextStyle, 0);
            if (subtitleTextStyle != 0) {
                this.mToolbar.setSubtitleTextAppearance(this.mToolbar.getContext(), subtitleTextStyle);
            }
            int popupTheme = a.getResourceId(C0425R.styleable.ActionBar_popupTheme, 0);
            if (popupTheme != 0) {
                this.mToolbar.setPopupTheme(popupTheme);
            }
        } else {
            this.mDisplayOpts = detectDisplayOptions();
        }
        a.recycle();
        setDefaultNavigationContentDescription(defaultNavigationContentDescription);
        this.mHomeDescription = this.mToolbar.getNavigationContentDescription();
        new View.OnClickListener(this) {
            final ActionMenuItem mNavItem;
            final /* synthetic */ ToolbarWidgetWrapper this$0;

            {
                ActionMenuItem actionMenuItem;
                this.this$0 = this$0;
                new ActionMenuItem(this.this$0.mToolbar.getContext(), 0, 16908332, 0, 0, this.this$0.mTitle);
                this.mNavItem = actionMenuItem;
            }

            public void onClick(View view) {
                View view2 = view;
                if (this.this$0.mWindowCallback != null && this.this$0.mMenuPrepared) {
                    boolean onMenuItemSelected = this.this$0.mWindowCallback.onMenuItemSelected(0, this.mNavItem);
                }
            }
        };
        this.mToolbar.setNavigationOnClickListener(onClickListener);
    }

    public void setDefaultNavigationContentDescription(int i) {
        int defaultNavigationContentDescription = i;
        if (defaultNavigationContentDescription != this.mDefaultNavigationContentDescription) {
            this.mDefaultNavigationContentDescription = defaultNavigationContentDescription;
            if (TextUtils.isEmpty(this.mToolbar.getNavigationContentDescription())) {
                setNavigationContentDescription(this.mDefaultNavigationContentDescription);
            }
        }
    }

    private int detectDisplayOptions() {
        int opts = 11;
        if (this.mToolbar.getNavigationIcon() != null) {
            opts = 11 | 4;
            this.mDefaultNavigationIcon = this.mToolbar.getNavigationIcon();
        }
        return opts;
    }

    public ViewGroup getViewGroup() {
        return this.mToolbar;
    }

    public Context getContext() {
        return this.mToolbar.getContext();
    }

    public boolean hasExpandedActionView() {
        return this.mToolbar.hasExpandedActionView();
    }

    public void collapseActionView() {
        this.mToolbar.collapseActionView();
    }

    public void setWindowCallback(Window.Callback cb) {
        Window.Callback callback = cb;
        this.mWindowCallback = callback;
    }

    public void setWindowTitle(CharSequence charSequence) {
        CharSequence title = charSequence;
        if (!this.mTitleSet) {
            setTitleInt(title);
        }
    }

    public CharSequence getTitle() {
        return this.mToolbar.getTitle();
    }

    public void setTitle(CharSequence title) {
        this.mTitleSet = true;
        setTitleInt(title);
    }

    private void setTitleInt(CharSequence charSequence) {
        CharSequence title = charSequence;
        this.mTitle = title;
        if ((this.mDisplayOpts & 8) != 0) {
            this.mToolbar.setTitle(title);
        }
    }

    public CharSequence getSubtitle() {
        return this.mToolbar.getSubtitle();
    }

    public void setSubtitle(CharSequence charSequence) {
        CharSequence subtitle = charSequence;
        this.mSubtitle = subtitle;
        if ((this.mDisplayOpts & 8) != 0) {
            this.mToolbar.setSubtitle(subtitle);
        }
    }

    public void initProgress() {
        int i = Log.i(TAG, "Progress display unsupported");
    }

    public void initIndeterminateProgress() {
        int i = Log.i(TAG, "Progress display unsupported");
    }

    public boolean hasIcon() {
        return this.mIcon != null;
    }

    public boolean hasLogo() {
        return this.mLogo != null;
    }

    public void setIcon(int i) {
        int resId = i;
        setIcon(resId != 0 ? AppCompatResources.getDrawable(getContext(), resId) : null);
    }

    public void setIcon(Drawable d) {
        this.mIcon = d;
        updateToolbarLogo();
    }

    public void setLogo(int i) {
        int resId = i;
        setLogo(resId != 0 ? AppCompatResources.getDrawable(getContext(), resId) : null);
    }

    public void setLogo(Drawable d) {
        this.mLogo = d;
        updateToolbarLogo();
    }

    private void updateToolbarLogo() {
        Drawable logo = null;
        if ((this.mDisplayOpts & 2) != 0) {
            if ((this.mDisplayOpts & 1) != 0) {
                logo = this.mLogo != null ? this.mLogo : this.mIcon;
            } else {
                logo = this.mIcon;
            }
        }
        this.mToolbar.setLogo(logo);
    }

    public boolean canShowOverflowMenu() {
        return this.mToolbar.canShowOverflowMenu();
    }

    public boolean isOverflowMenuShowing() {
        return this.mToolbar.isOverflowMenuShowing();
    }

    public boolean isOverflowMenuShowPending() {
        return this.mToolbar.isOverflowMenuShowPending();
    }

    public boolean showOverflowMenu() {
        return this.mToolbar.showOverflowMenu();
    }

    public boolean hideOverflowMenu() {
        return this.mToolbar.hideOverflowMenu();
    }

    public void setMenuPrepared() {
        this.mMenuPrepared = true;
    }

    public void setMenu(Menu menu, MenuPresenter.Callback callback) {
        ActionMenuPresenter actionMenuPresenter;
        Menu menu2 = menu;
        MenuPresenter.Callback cb = callback;
        if (this.mActionMenuPresenter == null) {
            new ActionMenuPresenter(this.mToolbar.getContext());
            this.mActionMenuPresenter = actionMenuPresenter;
            this.mActionMenuPresenter.setId(C0425R.C0427id.action_menu_presenter);
        }
        this.mActionMenuPresenter.setCallback(cb);
        this.mToolbar.setMenu((MenuBuilder) menu2, this.mActionMenuPresenter);
    }

    public void dismissPopupMenus() {
        this.mToolbar.dismissPopupMenus();
    }

    public int getDisplayOptions() {
        return this.mDisplayOpts;
    }

    public void setDisplayOptions(int i) {
        int newOpts = i;
        int changed = this.mDisplayOpts ^ newOpts;
        this.mDisplayOpts = newOpts;
        if (changed != 0) {
            if ((changed & 4) != 0) {
                if ((newOpts & 4) != 0) {
                    updateHomeAccessibility();
                }
                updateNavigationIcon();
            }
            if ((changed & 3) != 0) {
                updateToolbarLogo();
            }
            if ((changed & 8) != 0) {
                if ((newOpts & 8) != 0) {
                    this.mToolbar.setTitle(this.mTitle);
                    this.mToolbar.setSubtitle(this.mSubtitle);
                } else {
                    this.mToolbar.setTitle((CharSequence) null);
                    this.mToolbar.setSubtitle((CharSequence) null);
                }
            }
            if ((changed & 16) != 0 && this.mCustomView != null) {
                if ((newOpts & 16) != 0) {
                    this.mToolbar.addView(this.mCustomView);
                } else {
                    this.mToolbar.removeView(this.mCustomView);
                }
            }
        }
    }

    public void setEmbeddedTabView(ScrollingTabContainerView scrollingTabContainerView) {
        ScrollingTabContainerView tabView = scrollingTabContainerView;
        if (this.mTabView != null && this.mTabView.getParent() == this.mToolbar) {
            this.mToolbar.removeView(this.mTabView);
        }
        this.mTabView = tabView;
        if (tabView != null && this.mNavigationMode == 2) {
            this.mToolbar.addView(this.mTabView, 0);
            Toolbar.LayoutParams lp = (Toolbar.LayoutParams) this.mTabView.getLayoutParams();
            lp.width = -2;
            lp.height = -2;
            lp.gravity = 8388691;
            tabView.setAllowCollapse(true);
        }
    }

    public boolean hasEmbeddedTabs() {
        return this.mTabView != null;
    }

    public boolean isTitleTruncated() {
        return this.mToolbar.isTitleTruncated();
    }

    public void setCollapsible(boolean collapsible) {
        this.mToolbar.setCollapsible(collapsible);
    }

    public void setHomeButtonEnabled(boolean enable) {
    }

    public int getNavigationMode() {
        return this.mNavigationMode;
    }

    public void setNavigationMode(int i) {
        Throwable th;
        StringBuilder sb;
        int mode = i;
        int oldMode = this.mNavigationMode;
        if (mode != oldMode) {
            switch (oldMode) {
                case 1:
                    if (this.mSpinner != null && this.mSpinner.getParent() == this.mToolbar) {
                        this.mToolbar.removeView(this.mSpinner);
                        break;
                    }
                case 2:
                    if (this.mTabView != null && this.mTabView.getParent() == this.mToolbar) {
                        this.mToolbar.removeView(this.mTabView);
                        break;
                    }
            }
            this.mNavigationMode = mode;
            switch (mode) {
                case 0:
                    return;
                case 1:
                    ensureSpinner();
                    this.mToolbar.addView(this.mSpinner, 0);
                    return;
                case 2:
                    if (this.mTabView != null) {
                        this.mToolbar.addView(this.mTabView, 0);
                        Toolbar.LayoutParams lp = (Toolbar.LayoutParams) this.mTabView.getLayoutParams();
                        lp.width = -2;
                        lp.height = -2;
                        lp.gravity = 8388691;
                        return;
                    }
                    return;
                default:
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Invalid navigation mode ").append(mode).toString());
                    throw th2;
            }
        }
    }

    private void ensureSpinner() {
        Spinner spinner;
        ViewGroup.LayoutParams layoutParams;
        if (this.mSpinner == null) {
            new AppCompatSpinner(getContext(), (AttributeSet) null, C0425R.attr.actionDropDownStyle);
            this.mSpinner = spinner;
            new Toolbar.LayoutParams(-2, -2, 8388627);
            this.mSpinner.setLayoutParams(layoutParams);
        }
    }

    public void setDropdownParams(SpinnerAdapter adapter, AdapterView.OnItemSelectedListener listener) {
        ensureSpinner();
        this.mSpinner.setAdapter(adapter);
        this.mSpinner.setOnItemSelectedListener(listener);
    }

    public void setDropdownSelectedPosition(int i) {
        Throwable th;
        int position = i;
        if (this.mSpinner == null) {
            Throwable th2 = th;
            new IllegalStateException("Can't set dropdown selected position without an adapter");
            throw th2;
        }
        this.mSpinner.setSelection(position);
    }

    public int getDropdownSelectedPosition() {
        return this.mSpinner != null ? this.mSpinner.getSelectedItemPosition() : 0;
    }

    public int getDropdownItemCount() {
        return this.mSpinner != null ? this.mSpinner.getCount() : 0;
    }

    public void setCustomView(View view) {
        View view2 = view;
        if (!(this.mCustomView == null || (this.mDisplayOpts & 16) == 0)) {
            this.mToolbar.removeView(this.mCustomView);
        }
        this.mCustomView = view2;
        if (view2 != null && (this.mDisplayOpts & 16) != 0) {
            this.mToolbar.addView(this.mCustomView);
        }
    }

    public View getCustomView() {
        return this.mCustomView;
    }

    public void animateToVisibility(int visibility) {
        ViewPropertyAnimatorCompat anim = setupAnimatorToVisibility(visibility, DEFAULT_FADE_DURATION_MS);
        if (anim != null) {
            anim.start();
        }
    }

    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i, long j) {
        ViewPropertyAnimatorListener viewPropertyAnimatorListener;
        int visibility = i;
        long duration = j;
        ViewPropertyAnimatorCompat animate = ViewCompat.animate(this.mToolbar);
        float f = visibility == 0 ? 1.0f : 0.0f;
        final int i2 = visibility;
        new ViewPropertyAnimatorListenerAdapter(this) {
            private boolean mCanceled = false;
            final /* synthetic */ ToolbarWidgetWrapper this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationStart(View view) {
                View view2 = view;
                this.this$0.mToolbar.setVisibility(0);
            }

            public void onAnimationEnd(View view) {
                View view2 = view;
                if (!this.mCanceled) {
                    this.this$0.mToolbar.setVisibility(i2);
                }
            }

            public void onAnimationCancel(View view) {
                View view2 = view;
                this.mCanceled = true;
            }
        };
        return animate.alpha(f).setDuration(duration).setListener(viewPropertyAnimatorListener);
    }

    public void setNavigationIcon(Drawable icon) {
        this.mNavIcon = icon;
        updateNavigationIcon();
    }

    public void setNavigationIcon(int i) {
        int resId = i;
        setNavigationIcon(resId != 0 ? AppCompatResources.getDrawable(getContext(), resId) : null);
    }

    public void setDefaultNavigationIcon(Drawable drawable) {
        Drawable defaultNavigationIcon = drawable;
        if (this.mDefaultNavigationIcon != defaultNavigationIcon) {
            this.mDefaultNavigationIcon = defaultNavigationIcon;
            updateNavigationIcon();
        }
    }

    private void updateNavigationIcon() {
        if ((this.mDisplayOpts & 4) != 0) {
            this.mToolbar.setNavigationIcon(this.mNavIcon != null ? this.mNavIcon : this.mDefaultNavigationIcon);
        } else {
            this.mToolbar.setNavigationIcon((Drawable) null);
        }
    }

    public void setNavigationContentDescription(CharSequence description) {
        this.mHomeDescription = description;
        updateHomeAccessibility();
    }

    public void setNavigationContentDescription(int i) {
        int resId = i;
        setNavigationContentDescription((CharSequence) resId == 0 ? null : getContext().getString(resId));
    }

    private void updateHomeAccessibility() {
        if ((this.mDisplayOpts & 4) == 0) {
            return;
        }
        if (TextUtils.isEmpty(this.mHomeDescription)) {
            this.mToolbar.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
        } else {
            this.mToolbar.setNavigationContentDescription(this.mHomeDescription);
        }
    }

    public void saveHierarchyState(SparseArray<Parcelable> toolbarStates) {
        this.mToolbar.saveHierarchyState(toolbarStates);
    }

    public void restoreHierarchyState(SparseArray<Parcelable> toolbarStates) {
        this.mToolbar.restoreHierarchyState(toolbarStates);
    }

    public void setBackgroundDrawable(Drawable d) {
        ViewCompat.setBackground(this.mToolbar, d);
    }

    public int getHeight() {
        return this.mToolbar.getHeight();
    }

    public void setVisibility(int visible) {
        this.mToolbar.setVisibility(visible);
    }

    public int getVisibility() {
        return this.mToolbar.getVisibility();
    }

    public void setMenuCallbacks(MenuPresenter.Callback actionMenuPresenterCallback, MenuBuilder.Callback menuBuilderCallback) {
        this.mToolbar.setMenuCallbacks(actionMenuPresenterCallback, menuBuilderCallback);
    }

    public Menu getMenu() {
        return this.mToolbar.getMenu();
    }
}
