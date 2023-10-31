package android.support.p003v7.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.p000v4.view.ViewCompat;
import android.support.p003v7.app.ActionBar;
import android.support.p003v7.view.WindowCallbackWrapper;
import android.support.p003v7.view.menu.MenuBuilder;
import android.support.p003v7.view.menu.MenuPresenter;
import android.support.p003v7.widget.DecorToolbar;
import android.support.p003v7.widget.Toolbar;
import android.support.p003v7.widget.ToolbarWidgetWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;
import java.util.ArrayList;

/* renamed from: android.support.v7.app.ToolbarActionBar */
class ToolbarActionBar extends ActionBar {
    DecorToolbar mDecorToolbar;
    private boolean mLastMenuVisibility;
    private boolean mMenuCallbackSet;
    private final Toolbar.OnMenuItemClickListener mMenuClicker;
    private final Runnable mMenuInvalidator;
    private ArrayList<ActionBar.OnMenuVisibilityListener> mMenuVisibilityListeners;
    boolean mToolbarMenuPrepared;
    Window.Callback mWindowCallback;

    ToolbarActionBar(Toolbar toolbar, CharSequence title, Window.Callback windowCallback) {
        ArrayList<ActionBar.OnMenuVisibilityListener> arrayList;
        Runnable runnable;
        Toolbar.OnMenuItemClickListener onMenuItemClickListener;
        DecorToolbar decorToolbar;
        Window.Callback callback;
        Toolbar toolbar2 = toolbar;
        new ArrayList<>();
        this.mMenuVisibilityListeners = arrayList;
        new Runnable(this) {
            final /* synthetic */ ToolbarActionBar this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                this.this$0.populateOptionsMenu();
            }
        };
        this.mMenuInvalidator = runnable;
        new Toolbar.OnMenuItemClickListener(this) {
            final /* synthetic */ ToolbarActionBar this$0;

            {
                this.this$0 = this$0;
            }

            public boolean onMenuItemClick(MenuItem item) {
                return this.this$0.mWindowCallback.onMenuItemSelected(0, item);
            }
        };
        this.mMenuClicker = onMenuItemClickListener;
        new ToolbarWidgetWrapper(toolbar2, false);
        this.mDecorToolbar = decorToolbar;
        new ToolbarCallbackWrapper(this, windowCallback);
        this.mWindowCallback = callback;
        this.mDecorToolbar.setWindowCallback(this.mWindowCallback);
        toolbar2.setOnMenuItemClickListener(this.mMenuClicker);
        this.mDecorToolbar.setWindowTitle(title);
    }

    public Window.Callback getWrappedWindowCallback() {
        return this.mWindowCallback;
    }

    public void setCustomView(View view) {
        ActionBar.LayoutParams layoutParams;
        new ActionBar.LayoutParams(-2, -2);
        setCustomView(view, layoutParams);
    }

    public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
        View view2 = view;
        ActionBar.LayoutParams layoutParams2 = layoutParams;
        if (view2 != null) {
            view2.setLayoutParams(layoutParams2);
        }
        this.mDecorToolbar.setCustomView(view2);
    }

    public void setCustomView(int resId) {
        setCustomView(LayoutInflater.from(this.mDecorToolbar.getContext()).inflate(resId, this.mDecorToolbar.getViewGroup(), false));
    }

    public void setIcon(int resId) {
        this.mDecorToolbar.setIcon(resId);
    }

    public void setIcon(Drawable icon) {
        this.mDecorToolbar.setIcon(icon);
    }

    public void setLogo(int resId) {
        this.mDecorToolbar.setLogo(resId);
    }

    public void setLogo(Drawable logo) {
        this.mDecorToolbar.setLogo(logo);
    }

    public void setStackedBackgroundDrawable(Drawable d) {
    }

    public void setSplitBackgroundDrawable(Drawable d) {
    }

    public void setHomeButtonEnabled(boolean enabled) {
    }

    public void setElevation(float elevation) {
        ViewCompat.setElevation(this.mDecorToolbar.getViewGroup(), elevation);
    }

    public float getElevation() {
        return ViewCompat.getElevation(this.mDecorToolbar.getViewGroup());
    }

    public Context getThemedContext() {
        return this.mDecorToolbar.getContext();
    }

    public boolean isTitleTruncated() {
        return super.isTitleTruncated();
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

    public void setDefaultDisplayHomeAsUpEnabled(boolean enabled) {
    }

    public void setHomeActionContentDescription(int resId) {
        this.mDecorToolbar.setNavigationContentDescription(resId);
    }

    public void setShowHideAnimationEnabled(boolean enabled) {
    }

    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
    }

    public void setListNavigationCallbacks(SpinnerAdapter adapter, ActionBar.OnNavigationListener callback) {
        AdapterView.OnItemSelectedListener onItemSelectedListener;
        new NavItemSelectedListener(callback);
        this.mDecorToolbar.setDropdownParams(adapter, onItemSelectedListener);
    }

    public void setSelectedNavigationItem(int i) {
        Throwable th;
        int position = i;
        switch (this.mDecorToolbar.getNavigationMode()) {
            case 1:
                this.mDecorToolbar.setDropdownSelectedPosition(position);
                return;
            default:
                Throwable th2 = th;
                new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
                throw th2;
        }
    }

    public int getSelectedNavigationIndex() {
        return -1;
    }

    public int getNavigationItemCount() {
        return 0;
    }

    public void setTitle(CharSequence title) {
        this.mDecorToolbar.setTitle(title);
    }

    public void setTitle(int i) {
        int resId = i;
        this.mDecorToolbar.setTitle(resId != 0 ? this.mDecorToolbar.getContext().getText(resId) : null);
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

    public void setSubtitle(int i) {
        int resId = i;
        this.mDecorToolbar.setSubtitle(resId != 0 ? this.mDecorToolbar.getContext().getText(resId) : null);
    }

    @SuppressLint({"WrongConstant"})
    public void setDisplayOptions(int options) {
        setDisplayOptions(options, -1);
    }

    public void setDisplayOptions(int options, int i) {
        int mask = i;
        this.mDecorToolbar.setDisplayOptions((options & mask) | (this.mDecorToolbar.getDisplayOptions() & (mask ^ -1)));
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

    public void setBackgroundDrawable(@Nullable Drawable d) {
        this.mDecorToolbar.setBackgroundDrawable(d);
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
        return 0;
    }

    public void setNavigationMode(int i) {
        Throwable th;
        int mode = i;
        if (mode == 2) {
            Throwable th2 = th;
            new IllegalArgumentException("Tabs not supported in this configuration");
            throw th2;
        }
        this.mDecorToolbar.setNavigationMode(mode);
    }

    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }

    public ActionBar.Tab newTab() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
        throw th2;
    }

    public void addTab(ActionBar.Tab tab) {
        Throwable th;
        ActionBar.Tab tab2 = tab;
        Throwable th2 = th;
        new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
        throw th2;
    }

    public void addTab(ActionBar.Tab tab, boolean z) {
        Throwable th;
        ActionBar.Tab tab2 = tab;
        boolean z2 = z;
        Throwable th2 = th;
        new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
        throw th2;
    }

    public void addTab(ActionBar.Tab tab, int i) {
        Throwable th;
        ActionBar.Tab tab2 = tab;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
        throw th2;
    }

    public void addTab(ActionBar.Tab tab, int i, boolean z) {
        Throwable th;
        ActionBar.Tab tab2 = tab;
        int i2 = i;
        boolean z2 = z;
        Throwable th2 = th;
        new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
        throw th2;
    }

    public void removeTab(ActionBar.Tab tab) {
        Throwable th;
        ActionBar.Tab tab2 = tab;
        Throwable th2 = th;
        new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
        throw th2;
    }

    public void removeTabAt(int i) {
        Throwable th;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
        throw th2;
    }

    public void removeAllTabs() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
        throw th2;
    }

    public void selectTab(ActionBar.Tab tab) {
        Throwable th;
        ActionBar.Tab tab2 = tab;
        Throwable th2 = th;
        new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
        throw th2;
    }

    public ActionBar.Tab getSelectedTab() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
        throw th2;
    }

    public ActionBar.Tab getTabAt(int i) {
        Throwable th;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
        throw th2;
    }

    public int getTabCount() {
        return 0;
    }

    public int getHeight() {
        return this.mDecorToolbar.getHeight();
    }

    public void show() {
        this.mDecorToolbar.setVisibility(0);
    }

    public void hide() {
        this.mDecorToolbar.setVisibility(8);
    }

    public boolean isShowing() {
        return this.mDecorToolbar.getVisibility() == 0;
    }

    public boolean openOptionsMenu() {
        return this.mDecorToolbar.showOverflowMenu();
    }

    public boolean closeOptionsMenu() {
        return this.mDecorToolbar.hideOverflowMenu();
    }

    public boolean invalidateOptionsMenu() {
        boolean removeCallbacks = this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
        ViewCompat.postOnAnimation(this.mDecorToolbar.getViewGroup(), this.mMenuInvalidator);
        return true;
    }

    public boolean collapseActionView() {
        if (!this.mDecorToolbar.hasExpandedActionView()) {
            return false;
        }
        this.mDecorToolbar.collapseActionView();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void populateOptionsMenu() {
        Menu menu = getMenu();
        MenuBuilder mb = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
        if (mb != null) {
            mb.stopDispatchingItemsChanged();
        }
        try {
            menu.clear();
            if (!this.mWindowCallback.onCreatePanelMenu(0, menu) || !this.mWindowCallback.onPreparePanel(0, (View) null, menu)) {
                menu.clear();
            }
            if (mb != null) {
                mb.startDispatchingItemsChanged();
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            if (mb != null) {
                mb.startDispatchingItemsChanged();
            }
            throw th2;
        }
    }

    public boolean onMenuKeyEvent(KeyEvent event) {
        if (event.getAction() == 1) {
            boolean openOptionsMenu = openOptionsMenu();
        }
        return true;
    }

    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        int keyCode = i;
        KeyEvent ev = keyEvent;
        Menu menu = getMenu();
        if (menu == null) {
            return false;
        }
        menu.setQwertyMode(KeyCharacterMap.load(ev != null ? ev.getDeviceId() : -1).getKeyboardType() != 1);
        return menu.performShortcut(keyCode, ev, 0);
    }

    /* access modifiers changed from: package-private */
    public void onDestroy() {
        boolean removeCallbacks = this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
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

    /* renamed from: android.support.v7.app.ToolbarActionBar$ToolbarCallbackWrapper */
    private class ToolbarCallbackWrapper extends WindowCallbackWrapper {
        final /* synthetic */ ToolbarActionBar this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ToolbarCallbackWrapper(ToolbarActionBar toolbarActionBar, Window.Callback wrapped) {
            super(wrapped);
            this.this$0 = toolbarActionBar;
        }

        public boolean onPreparePanel(int featureId, View view, Menu menu) {
            boolean result = super.onPreparePanel(featureId, view, menu);
            if (result && !this.this$0.mToolbarMenuPrepared) {
                this.this$0.mDecorToolbar.setMenuPrepared();
                this.this$0.mToolbarMenuPrepared = true;
            }
            return result;
        }

        public View onCreatePanelView(int i) {
            View view;
            int featureId = i;
            if (featureId != 0) {
                return super.onCreatePanelView(featureId);
            }
            new View(this.this$0.mDecorToolbar.getContext());
            return view;
        }
    }

    private Menu getMenu() {
        MenuPresenter.Callback callback;
        MenuBuilder.Callback callback2;
        if (!this.mMenuCallbackSet) {
            new ActionMenuPresenterCallback(this);
            new MenuBuilderCallback(this);
            this.mDecorToolbar.setMenuCallbacks(callback, callback2);
            this.mMenuCallbackSet = true;
        }
        return this.mDecorToolbar.getMenu();
    }

    /* renamed from: android.support.v7.app.ToolbarActionBar$ActionMenuPresenterCallback */
    private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        private boolean mClosingActionMenu;
        final /* synthetic */ ToolbarActionBar this$0;

        ActionMenuPresenterCallback(ToolbarActionBar toolbarActionBar) {
            this.this$0 = toolbarActionBar;
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            MenuBuilder subMenu = menuBuilder;
            if (this.this$0.mWindowCallback == null) {
                return false;
            }
            boolean onMenuOpened = this.this$0.mWindowCallback.onMenuOpened(108, subMenu);
            return true;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            MenuBuilder menu = menuBuilder;
            boolean z2 = z;
            if (!this.mClosingActionMenu) {
                this.mClosingActionMenu = true;
                this.this$0.mDecorToolbar.dismissPopupMenus();
                if (this.this$0.mWindowCallback != null) {
                    this.this$0.mWindowCallback.onPanelClosed(108, menu);
                }
                this.mClosingActionMenu = false;
            }
        }
    }

    /* renamed from: android.support.v7.app.ToolbarActionBar$MenuBuilderCallback */
    private final class MenuBuilderCallback implements MenuBuilder.Callback {
        final /* synthetic */ ToolbarActionBar this$0;

        MenuBuilderCallback(ToolbarActionBar toolbarActionBar) {
            this.this$0 = toolbarActionBar;
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            MenuBuilder menuBuilder2 = menuBuilder;
            MenuItem menuItem2 = menuItem;
            return false;
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
            MenuBuilder menu = menuBuilder;
            if (this.this$0.mWindowCallback == null) {
                return;
            }
            if (this.this$0.mDecorToolbar.isOverflowMenuShowing()) {
                this.this$0.mWindowCallback.onPanelClosed(108, menu);
            } else if (this.this$0.mWindowCallback.onPreparePanel(0, (View) null, menu)) {
                boolean onMenuOpened = this.this$0.mWindowCallback.onMenuOpened(108, menu);
            }
        }
    }
}
