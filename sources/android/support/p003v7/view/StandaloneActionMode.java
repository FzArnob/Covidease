package android.support.p003v7.view;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.p003v7.view.ActionMode;
import android.support.p003v7.view.menu.MenuBuilder;
import android.support.p003v7.view.menu.MenuPopupHelper;
import android.support.p003v7.view.menu.SubMenuBuilder;
import android.support.p003v7.widget.ActionBarContextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.lang.ref.WeakReference;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.view.StandaloneActionMode */
public class StandaloneActionMode extends ActionMode implements MenuBuilder.Callback {
    private ActionMode.Callback mCallback;
    private Context mContext;
    private ActionBarContextView mContextView;
    private WeakReference<View> mCustomView;
    private boolean mFinished;
    private boolean mFocusable;
    private MenuBuilder mMenu;

    public StandaloneActionMode(Context context, ActionBarContextView actionBarContextView, ActionMode.Callback callback, boolean isFocusable) {
        MenuBuilder menuBuilder;
        ActionBarContextView view = actionBarContextView;
        this.mContext = context;
        this.mContextView = view;
        this.mCallback = callback;
        new MenuBuilder(view.getContext());
        this.mMenu = menuBuilder.setDefaultShowAsAction(1);
        this.mMenu.setCallback(this);
        this.mFocusable = isFocusable;
    }

    public void setTitle(CharSequence title) {
        this.mContextView.setTitle(title);
    }

    public void setSubtitle(CharSequence subtitle) {
        this.mContextView.setSubtitle(subtitle);
    }

    public void setTitle(int resId) {
        setTitle((CharSequence) this.mContext.getString(resId));
    }

    public void setSubtitle(int resId) {
        setSubtitle((CharSequence) this.mContext.getString(resId));
    }

    public void setTitleOptionalHint(boolean z) {
        boolean titleOptional = z;
        super.setTitleOptionalHint(titleOptional);
        this.mContextView.setTitleOptional(titleOptional);
    }

    public boolean isTitleOptional() {
        return this.mContextView.isTitleOptional();
    }

    public void setCustomView(View view) {
        WeakReference<View> weakReference;
        WeakReference<View> weakReference2;
        View view2 = view;
        this.mContextView.setCustomView(view2);
        if (view2 != null) {
            weakReference = weakReference2;
            new WeakReference<>(view2);
        } else {
            weakReference = null;
        }
        this.mCustomView = weakReference;
    }

    public void invalidate() {
        boolean onPrepareActionMode = this.mCallback.onPrepareActionMode(this, this.mMenu);
    }

    public void finish() {
        if (!this.mFinished) {
            this.mFinished = true;
            this.mContextView.sendAccessibilityEvent(32);
            this.mCallback.onDestroyActionMode(this);
        }
    }

    public Menu getMenu() {
        return this.mMenu;
    }

    public CharSequence getTitle() {
        return this.mContextView.getTitle();
    }

    public CharSequence getSubtitle() {
        return this.mContextView.getSubtitle();
    }

    public View getCustomView() {
        return this.mCustomView != null ? (View) this.mCustomView.get() : null;
    }

    public MenuInflater getMenuInflater() {
        MenuInflater menuInflater;
        new SupportMenuInflater(this.mContextView.getContext());
        return menuInflater;
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem item) {
        MenuBuilder menuBuilder2 = menuBuilder;
        return this.mCallback.onActionItemClicked(this, item);
    }

    public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        MenuPopupHelper menuPopupHelper;
        SubMenuBuilder subMenu = subMenuBuilder;
        if (!subMenu.hasVisibleItems()) {
            return true;
        }
        new MenuPopupHelper(this.mContextView.getContext(), subMenu);
        menuPopupHelper.show();
        return true;
    }

    public void onCloseSubMenu(SubMenuBuilder menu) {
    }

    public void onMenuModeChange(MenuBuilder menuBuilder) {
        MenuBuilder menuBuilder2 = menuBuilder;
        invalidate();
        boolean showOverflowMenu = this.mContextView.showOverflowMenu();
    }

    public boolean isUiFocusable() {
        return this.mFocusable;
    }
}
