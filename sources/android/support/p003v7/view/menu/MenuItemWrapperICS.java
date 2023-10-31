package android.support.p003v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.internal.view.SupportMenuItem;
import android.util.Log;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.view.menu.MenuItemWrapperICS */
public class MenuItemWrapperICS extends BaseMenuWrapper<SupportMenuItem> implements MenuItem {
    static final String LOG_TAG = "MenuItemWrapper";
    private Method mSetExclusiveCheckableMethod;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MenuItemWrapperICS(Context context, SupportMenuItem object) {
        super(context, object);
    }

    public int getItemId() {
        return ((SupportMenuItem) this.mWrappedObject).getItemId();
    }

    public int getGroupId() {
        return ((SupportMenuItem) this.mWrappedObject).getGroupId();
    }

    public int getOrder() {
        return ((SupportMenuItem) this.mWrappedObject).getOrder();
    }

    public MenuItem setTitle(CharSequence title) {
        MenuItem title2 = ((SupportMenuItem) this.mWrappedObject).setTitle(title);
        return this;
    }

    public MenuItem setTitle(int title) {
        MenuItem title2 = ((SupportMenuItem) this.mWrappedObject).setTitle(title);
        return this;
    }

    public CharSequence getTitle() {
        return ((SupportMenuItem) this.mWrappedObject).getTitle();
    }

    public MenuItem setTitleCondensed(CharSequence title) {
        MenuItem titleCondensed = ((SupportMenuItem) this.mWrappedObject).setTitleCondensed(title);
        return this;
    }

    public CharSequence getTitleCondensed() {
        return ((SupportMenuItem) this.mWrappedObject).getTitleCondensed();
    }

    public MenuItem setIcon(Drawable icon) {
        MenuItem icon2 = ((SupportMenuItem) this.mWrappedObject).setIcon(icon);
        return this;
    }

    public MenuItem setIcon(int iconRes) {
        MenuItem icon = ((SupportMenuItem) this.mWrappedObject).setIcon(iconRes);
        return this;
    }

    public Drawable getIcon() {
        return ((SupportMenuItem) this.mWrappedObject).getIcon();
    }

    public MenuItem setIntent(Intent intent) {
        MenuItem intent2 = ((SupportMenuItem) this.mWrappedObject).setIntent(intent);
        return this;
    }

    public Intent getIntent() {
        return ((SupportMenuItem) this.mWrappedObject).getIntent();
    }

    public MenuItem setShortcut(char numericChar, char alphaChar) {
        MenuItem shortcut = ((SupportMenuItem) this.mWrappedObject).setShortcut(numericChar, alphaChar);
        return this;
    }

    public MenuItem setShortcut(char numericChar, char alphaChar, int numericModifiers, int alphaModifiers) {
        MenuItem shortcut = ((SupportMenuItem) this.mWrappedObject).setShortcut(numericChar, alphaChar, numericModifiers, alphaModifiers);
        return this;
    }

    public MenuItem setNumericShortcut(char numericChar) {
        MenuItem numericShortcut = ((SupportMenuItem) this.mWrappedObject).setNumericShortcut(numericChar);
        return this;
    }

    public MenuItem setNumericShortcut(char numericChar, int numericModifiers) {
        MenuItem numericShortcut = ((SupportMenuItem) this.mWrappedObject).setNumericShortcut(numericChar, numericModifiers);
        return this;
    }

    public char getNumericShortcut() {
        return ((SupportMenuItem) this.mWrappedObject).getNumericShortcut();
    }

    public int getNumericModifiers() {
        return ((SupportMenuItem) this.mWrappedObject).getNumericModifiers();
    }

    public MenuItem setAlphabeticShortcut(char alphaChar) {
        MenuItem alphabeticShortcut = ((SupportMenuItem) this.mWrappedObject).setAlphabeticShortcut(alphaChar);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char alphaChar, int alphaModifiers) {
        MenuItem alphabeticShortcut = ((SupportMenuItem) this.mWrappedObject).setAlphabeticShortcut(alphaChar, alphaModifiers);
        return this;
    }

    public char getAlphabeticShortcut() {
        return ((SupportMenuItem) this.mWrappedObject).getAlphabeticShortcut();
    }

    public int getAlphabeticModifiers() {
        return ((SupportMenuItem) this.mWrappedObject).getAlphabeticModifiers();
    }

    public MenuItem setCheckable(boolean checkable) {
        MenuItem checkable2 = ((SupportMenuItem) this.mWrappedObject).setCheckable(checkable);
        return this;
    }

    public boolean isCheckable() {
        return ((SupportMenuItem) this.mWrappedObject).isCheckable();
    }

    public MenuItem setChecked(boolean checked) {
        MenuItem checked2 = ((SupportMenuItem) this.mWrappedObject).setChecked(checked);
        return this;
    }

    public boolean isChecked() {
        return ((SupportMenuItem) this.mWrappedObject).isChecked();
    }

    public MenuItem setVisible(boolean visible) {
        return ((SupportMenuItem) this.mWrappedObject).setVisible(visible);
    }

    public boolean isVisible() {
        return ((SupportMenuItem) this.mWrappedObject).isVisible();
    }

    public MenuItem setEnabled(boolean enabled) {
        MenuItem enabled2 = ((SupportMenuItem) this.mWrappedObject).setEnabled(enabled);
        return this;
    }

    public boolean isEnabled() {
        return ((SupportMenuItem) this.mWrappedObject).isEnabled();
    }

    public boolean hasSubMenu() {
        return ((SupportMenuItem) this.mWrappedObject).hasSubMenu();
    }

    public SubMenu getSubMenu() {
        return getSubMenuWrapper(((SupportMenuItem) this.mWrappedObject).getSubMenu());
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener2;
        MenuItem.OnMenuItemClickListener onMenuItemClickListener3;
        MenuItem.OnMenuItemClickListener menuItemClickListener = onMenuItemClickListener;
        SupportMenuItem supportMenuItem = (SupportMenuItem) this.mWrappedObject;
        if (menuItemClickListener != null) {
            onMenuItemClickListener2 = onMenuItemClickListener3;
            new OnMenuItemClickListenerWrapper(this, menuItemClickListener);
        } else {
            onMenuItemClickListener2 = null;
        }
        MenuItem onMenuItemClickListener4 = supportMenuItem.setOnMenuItemClickListener(onMenuItemClickListener2);
        return this;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return ((SupportMenuItem) this.mWrappedObject).getMenuInfo();
    }

    public void setShowAsAction(int actionEnum) {
        ((SupportMenuItem) this.mWrappedObject).setShowAsAction(actionEnum);
    }

    public MenuItem setShowAsActionFlags(int actionEnum) {
        MenuItem showAsActionFlags = ((SupportMenuItem) this.mWrappedObject).setShowAsActionFlags(actionEnum);
        return this;
    }

    public MenuItem setActionView(View view) {
        View view2;
        View view3 = view;
        if (view3 instanceof CollapsibleActionView) {
            new CollapsibleActionViewWrapper(view3);
            view3 = view2;
        }
        MenuItem actionView = ((SupportMenuItem) this.mWrappedObject).setActionView(view3);
        return this;
    }

    public MenuItem setActionView(int resId) {
        View view;
        MenuItem actionView = ((SupportMenuItem) this.mWrappedObject).setActionView(resId);
        View actionView2 = ((SupportMenuItem) this.mWrappedObject).getActionView();
        if (actionView2 instanceof CollapsibleActionView) {
            new CollapsibleActionViewWrapper(actionView2);
            MenuItem actionView3 = ((SupportMenuItem) this.mWrappedObject).setActionView(view);
        }
        return this;
    }

    public View getActionView() {
        View actionView = ((SupportMenuItem) this.mWrappedObject).getActionView();
        if (actionView instanceof CollapsibleActionViewWrapper) {
            return ((CollapsibleActionViewWrapper) actionView).getWrappedView();
        }
        return actionView;
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        ActionProvider provider = actionProvider;
        SupportMenuItem supportActionProvider = ((SupportMenuItem) this.mWrappedObject).setSupportActionProvider(provider != null ? createActionProviderWrapper(provider) : null);
        return this;
    }

    public ActionProvider getActionProvider() {
        android.support.p000v4.view.ActionProvider provider = ((SupportMenuItem) this.mWrappedObject).getSupportActionProvider();
        if (provider instanceof ActionProviderWrapper) {
            return ((ActionProviderWrapper) provider).mInner;
        }
        return null;
    }

    public boolean expandActionView() {
        return ((SupportMenuItem) this.mWrappedObject).expandActionView();
    }

    public boolean collapseActionView() {
        return ((SupportMenuItem) this.mWrappedObject).collapseActionView();
    }

    public boolean isActionViewExpanded() {
        return ((SupportMenuItem) this.mWrappedObject).isActionViewExpanded();
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        MenuItem.OnActionExpandListener onActionExpandListener2;
        MenuItem.OnActionExpandListener onActionExpandListener3;
        MenuItem.OnActionExpandListener listener = onActionExpandListener;
        SupportMenuItem supportMenuItem = (SupportMenuItem) this.mWrappedObject;
        if (listener != null) {
            onActionExpandListener2 = onActionExpandListener3;
            new OnActionExpandListenerWrapper(this, listener);
        } else {
            onActionExpandListener2 = null;
        }
        MenuItem onActionExpandListener4 = supportMenuItem.setOnActionExpandListener(onActionExpandListener2);
        return this;
    }

    public MenuItem setContentDescription(CharSequence contentDescription) {
        SupportMenuItem contentDescription2 = ((SupportMenuItem) this.mWrappedObject).setContentDescription(contentDescription);
        return this;
    }

    public CharSequence getContentDescription() {
        return ((SupportMenuItem) this.mWrappedObject).getContentDescription();
    }

    public MenuItem setTooltipText(CharSequence tooltipText) {
        SupportMenuItem tooltipText2 = ((SupportMenuItem) this.mWrappedObject).setTooltipText(tooltipText);
        return this;
    }

    public CharSequence getTooltipText() {
        return ((SupportMenuItem) this.mWrappedObject).getTooltipText();
    }

    public MenuItem setIconTintList(ColorStateList tint) {
        MenuItem iconTintList = ((SupportMenuItem) this.mWrappedObject).setIconTintList(tint);
        return this;
    }

    public ColorStateList getIconTintList() {
        return ((SupportMenuItem) this.mWrappedObject).getIconTintList();
    }

    public MenuItem setIconTintMode(PorterDuff.Mode tintMode) {
        MenuItem iconTintMode = ((SupportMenuItem) this.mWrappedObject).setIconTintMode(tintMode);
        return this;
    }

    public PorterDuff.Mode getIconTintMode() {
        return ((SupportMenuItem) this.mWrappedObject).getIconTintMode();
    }

    public void setExclusiveCheckable(boolean z) {
        boolean checkable = z;
        try {
            if (this.mSetExclusiveCheckableMethod == null) {
                this.mSetExclusiveCheckableMethod = ((SupportMenuItem) this.mWrappedObject).getClass().getDeclaredMethod("setExclusiveCheckable", new Class[]{Boolean.TYPE});
            }
            Object invoke = this.mSetExclusiveCheckableMethod.invoke(this.mWrappedObject, new Object[]{Boolean.valueOf(checkable)});
        } catch (Exception e) {
            int w = Log.w(LOG_TAG, "Error while calling setExclusiveCheckable", e);
        }
    }

    /* access modifiers changed from: package-private */
    public ActionProviderWrapper createActionProviderWrapper(ActionProvider provider) {
        ActionProviderWrapper actionProviderWrapper;
        new ActionProviderWrapper(this, this.mContext, provider);
        return actionProviderWrapper;
    }

    /* renamed from: android.support.v7.view.menu.MenuItemWrapperICS$OnMenuItemClickListenerWrapper */
    private class OnMenuItemClickListenerWrapper extends BaseWrapper<MenuItem.OnMenuItemClickListener> implements MenuItem.OnMenuItemClickListener {
        final /* synthetic */ MenuItemWrapperICS this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        OnMenuItemClickListenerWrapper(MenuItemWrapperICS menuItemWrapperICS, MenuItem.OnMenuItemClickListener object) {
            super(object);
            this.this$0 = menuItemWrapperICS;
        }

        public boolean onMenuItemClick(MenuItem item) {
            return ((MenuItem.OnMenuItemClickListener) this.mWrappedObject).onMenuItemClick(this.this$0.getMenuItemWrapper(item));
        }
    }

    /* renamed from: android.support.v7.view.menu.MenuItemWrapperICS$OnActionExpandListenerWrapper */
    private class OnActionExpandListenerWrapper extends BaseWrapper<MenuItem.OnActionExpandListener> implements MenuItem.OnActionExpandListener {
        final /* synthetic */ MenuItemWrapperICS this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        OnActionExpandListenerWrapper(MenuItemWrapperICS menuItemWrapperICS, MenuItem.OnActionExpandListener object) {
            super(object);
            this.this$0 = menuItemWrapperICS;
        }

        public boolean onMenuItemActionExpand(MenuItem item) {
            return ((MenuItem.OnActionExpandListener) this.mWrappedObject).onMenuItemActionExpand(this.this$0.getMenuItemWrapper(item));
        }

        public boolean onMenuItemActionCollapse(MenuItem item) {
            return ((MenuItem.OnActionExpandListener) this.mWrappedObject).onMenuItemActionCollapse(this.this$0.getMenuItemWrapper(item));
        }
    }

    /* renamed from: android.support.v7.view.menu.MenuItemWrapperICS$ActionProviderWrapper */
    class ActionProviderWrapper extends android.support.p000v4.view.ActionProvider {
        final ActionProvider mInner;
        final /* synthetic */ MenuItemWrapperICS this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ActionProviderWrapper(MenuItemWrapperICS this$02, Context context, ActionProvider inner) {
            super(context);
            this.this$0 = this$02;
            this.mInner = inner;
        }

        public View onCreateActionView() {
            return this.mInner.onCreateActionView();
        }

        public boolean onPerformDefaultAction() {
            return this.mInner.onPerformDefaultAction();
        }

        public boolean hasSubMenu() {
            return this.mInner.hasSubMenu();
        }

        public void onPrepareSubMenu(SubMenu subMenu) {
            this.mInner.onPrepareSubMenu(this.this$0.getSubMenuWrapper(subMenu));
        }
    }

    /* renamed from: android.support.v7.view.menu.MenuItemWrapperICS$CollapsibleActionViewWrapper */
    static class CollapsibleActionViewWrapper extends FrameLayout implements android.support.p003v7.view.CollapsibleActionView {
        final CollapsibleActionView mWrappedView;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        CollapsibleActionViewWrapper(android.view.View r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                android.content.Context r3 = r3.getContext()
                r2.<init>(r3)
                r2 = r0
                r3 = r1
                android.view.CollapsibleActionView r3 = (android.view.CollapsibleActionView) r3
                r2.mWrappedView = r3
                r2 = r0
                r3 = r1
                r2.addView(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.view.menu.MenuItemWrapperICS.CollapsibleActionViewWrapper.<init>(android.view.View):void");
        }

        public void onActionViewExpanded() {
            this.mWrappedView.onActionViewExpanded();
        }

        public void onActionViewCollapsed() {
            this.mWrappedView.onActionViewCollapsed();
        }

        /* access modifiers changed from: package-private */
        public View getWrappedView() {
            return (View) this.mWrappedView;
        }
    }
}
