package android.support.p003v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.internal.view.SupportMenuItem;
import android.support.p000v4.view.ActionProvider;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.content.res.AppCompatResources;
import android.support.p003v7.view.menu.MenuView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.LinearLayout;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.view.menu.MenuItemImpl */
public final class MenuItemImpl implements SupportMenuItem {
    private static final int CHECKABLE = 1;
    private static final int CHECKED = 2;
    private static final int ENABLED = 16;
    private static final int EXCLUSIVE = 4;
    private static final int HIDDEN = 8;
    private static final int IS_ACTION = 32;
    static final int NO_ICON = 0;
    private static final int SHOW_AS_ACTION_MASK = 3;
    private static final String TAG = "MenuItemImpl";
    private ActionProvider mActionProvider;
    private View mActionView;
    private final int mCategoryOrder;
    private MenuItem.OnMenuItemClickListener mClickListener;
    private CharSequence mContentDescription;
    private int mFlags = 16;
    private final int mGroup;
    private boolean mHasIconTint = false;
    private boolean mHasIconTintMode = false;
    private Drawable mIconDrawable;
    private int mIconResId = 0;
    private ColorStateList mIconTintList = null;
    private PorterDuff.Mode mIconTintMode = null;
    private final int mId;
    private Intent mIntent;
    private boolean mIsActionViewExpanded = false;
    private Runnable mItemCallback;
    MenuBuilder mMenu;
    private ContextMenu.ContextMenuInfo mMenuInfo;
    private boolean mNeedToApplyIconTint = false;
    private MenuItem.OnActionExpandListener mOnActionExpandListener;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private int mShortcutAlphabeticModifiers = 4096;
    private char mShortcutNumericChar;
    private int mShortcutNumericModifiers = 4096;
    private int mShowAsAction = 0;
    private SubMenuBuilder mSubMenu;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    private CharSequence mTooltipText;

    MenuItemImpl(MenuBuilder menu, int group, int id, int categoryOrder, int ordering, CharSequence title, int showAsAction) {
        this.mMenu = menu;
        this.mId = id;
        this.mGroup = group;
        this.mCategoryOrder = categoryOrder;
        this.mOrdering = ordering;
        this.mTitle = title;
        this.mShowAsAction = showAsAction;
    }

    public boolean invoke() {
        if (this.mClickListener != null && this.mClickListener.onMenuItemClick(this)) {
            return true;
        }
        if (this.mMenu.dispatchMenuItemSelected(this.mMenu, this)) {
            return true;
        }
        if (this.mItemCallback != null) {
            this.mItemCallback.run();
            return true;
        }
        if (this.mIntent != null) {
            try {
                this.mMenu.getContext().startActivity(this.mIntent);
                return true;
            } catch (ActivityNotFoundException e) {
                int e2 = Log.e(TAG, "Can't find activity to handle intent; ignoring", e);
            }
        }
        if (this.mActionProvider == null || !this.mActionProvider.onPerformDefaultAction()) {
            return false;
        }
        return true;
    }

    public boolean isEnabled() {
        return (this.mFlags & 16) != 0;
    }

    public MenuItem setEnabled(boolean enabled) {
        if (enabled) {
            this.mFlags |= 16;
        } else {
            this.mFlags &= -17;
        }
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public int getGroupId() {
        return this.mGroup;
    }

    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.mId;
    }

    public int getOrder() {
        return this.mCategoryOrder;
    }

    public int getOrdering() {
        return this.mOrdering;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Runnable getCallback() {
        return this.mItemCallback;
    }

    public MenuItem setCallback(Runnable callback) {
        this.mItemCallback = callback;
        return this;
    }

    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    public MenuItem setAlphabeticShortcut(char c) {
        char alphaChar = c;
        if (this.mShortcutAlphabeticChar == alphaChar) {
            return this;
        }
        this.mShortcutAlphabeticChar = Character.toLowerCase(alphaChar);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c, int i) {
        char alphaChar = c;
        int alphaModifiers = i;
        if (this.mShortcutAlphabeticChar == alphaChar && this.mShortcutAlphabeticModifiers == alphaModifiers) {
            return this;
        }
        this.mShortcutAlphabeticChar = Character.toLowerCase(alphaChar);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(alphaModifiers);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public int getAlphabeticModifiers() {
        return this.mShortcutAlphabeticModifiers;
    }

    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    public int getNumericModifiers() {
        return this.mShortcutNumericModifiers;
    }

    public MenuItem setNumericShortcut(char c) {
        char numericChar = c;
        if (this.mShortcutNumericChar == numericChar) {
            return this;
        }
        this.mShortcutNumericChar = numericChar;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setNumericShortcut(char c, int i) {
        char numericChar = c;
        int numericModifiers = i;
        if (this.mShortcutNumericChar == numericChar && this.mShortcutNumericModifiers == numericModifiers) {
            return this;
        }
        this.mShortcutNumericChar = numericChar;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(numericModifiers);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setShortcut(char numericChar, char alphaChar) {
        this.mShortcutNumericChar = numericChar;
        this.mShortcutAlphabeticChar = Character.toLowerCase(alphaChar);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setShortcut(char numericChar, char alphaChar, int numericModifiers, int alphaModifiers) {
        this.mShortcutNumericChar = numericChar;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(numericModifiers);
        this.mShortcutAlphabeticChar = Character.toLowerCase(alphaChar);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(alphaModifiers);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    /* access modifiers changed from: package-private */
    public char getShortcut() {
        return this.mMenu.isQwertyMode() ? this.mShortcutAlphabeticChar : this.mShortcutNumericChar;
    }

    /* access modifiers changed from: package-private */
    public String getShortcutLabel() {
        StringBuilder sb;
        int i;
        char shortcut = getShortcut();
        if (shortcut == 0) {
            return "";
        }
        Resources res = this.mMenu.getContext().getResources();
        new StringBuilder();
        StringBuilder sb2 = sb;
        if (ViewConfiguration.get(this.mMenu.getContext()).hasPermanentMenuKey()) {
            StringBuilder append = sb2.append(res.getString(C0425R.string.abc_prepend_shortcut_label));
        }
        if (this.mMenu.isQwertyMode()) {
            i = this.mShortcutAlphabeticModifiers;
        } else {
            i = this.mShortcutNumericModifiers;
        }
        int modifiers = i;
        appendModifier(sb2, modifiers, 65536, res.getString(C0425R.string.abc_menu_meta_shortcut_label));
        appendModifier(sb2, modifiers, 4096, res.getString(C0425R.string.abc_menu_ctrl_shortcut_label));
        appendModifier(sb2, modifiers, 2, res.getString(C0425R.string.abc_menu_alt_shortcut_label));
        appendModifier(sb2, modifiers, 1, res.getString(C0425R.string.abc_menu_shift_shortcut_label));
        appendModifier(sb2, modifiers, 4, res.getString(C0425R.string.abc_menu_sym_shortcut_label));
        appendModifier(sb2, modifiers, 8, res.getString(C0425R.string.abc_menu_function_shortcut_label));
        switch (shortcut) {
            case 8:
                StringBuilder append2 = sb2.append(res.getString(C0425R.string.abc_menu_delete_shortcut_label));
                break;
            case 10:
                StringBuilder append3 = sb2.append(res.getString(C0425R.string.abc_menu_enter_shortcut_label));
                break;
            case ' ':
                StringBuilder append4 = sb2.append(res.getString(C0425R.string.abc_menu_space_shortcut_label));
                break;
            default:
                StringBuilder append5 = sb2.append(shortcut);
                break;
        }
        return sb2.toString();
    }

    private static void appendModifier(StringBuilder sb, int modifiers, int i, String str) {
        StringBuilder sb2 = sb;
        int flag = i;
        String label = str;
        if ((modifiers & flag) == flag) {
            StringBuilder append = sb2.append(label);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldShowShortcut() {
        return this.mMenu.isShortcutsVisible() && getShortcut() != 0;
    }

    public SubMenu getSubMenu() {
        return this.mSubMenu;
    }

    public boolean hasSubMenu() {
        return this.mSubMenu != null;
    }

    public void setSubMenu(SubMenuBuilder subMenuBuilder) {
        SubMenuBuilder subMenu = subMenuBuilder;
        this.mSubMenu = subMenu;
        SubMenu headerTitle = subMenu.setHeaderTitle(getTitle());
    }

    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return this.mTitle;
    }

    /* access modifiers changed from: package-private */
    public CharSequence getTitleForItemView(MenuView.ItemView itemView) {
        CharSequence title;
        MenuView.ItemView itemView2 = itemView;
        if (itemView2 == null || !itemView2.prefersCondensedTitle()) {
            title = getTitle();
        } else {
            title = getTitleCondensed();
        }
        return title;
    }

    public MenuItem setTitle(CharSequence charSequence) {
        CharSequence title = charSequence;
        this.mTitle = title;
        this.mMenu.onItemsChanged(false);
        if (this.mSubMenu != null) {
            SubMenu headerTitle = this.mSubMenu.setHeaderTitle(title);
        }
        return this;
    }

    public MenuItem setTitle(int title) {
        return setTitle((CharSequence) this.mMenu.getContext().getString(title));
    }

    public CharSequence getTitleCondensed() {
        CharSequence ctitle = this.mTitleCondensed != null ? this.mTitleCondensed : this.mTitle;
        if (Build.VERSION.SDK_INT >= 18 || ctitle == null || (ctitle instanceof String)) {
            return ctitle;
        }
        return ctitle.toString();
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        CharSequence title = charSequence;
        this.mTitleCondensed = title;
        if (title == null) {
            CharSequence title2 = this.mTitle;
        }
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public Drawable getIcon() {
        if (this.mIconDrawable != null) {
            return applyIconTintIfNecessary(this.mIconDrawable);
        }
        if (this.mIconResId == 0) {
            return null;
        }
        Drawable icon = AppCompatResources.getDrawable(this.mMenu.getContext(), this.mIconResId);
        this.mIconResId = 0;
        this.mIconDrawable = icon;
        return applyIconTintIfNecessary(icon);
    }

    public MenuItem setIcon(Drawable icon) {
        this.mIconResId = 0;
        this.mIconDrawable = icon;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIcon(int iconResId) {
        this.mIconDrawable = null;
        this.mIconResId = iconResId;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIconTintList(@Nullable ColorStateList iconTintList) {
        this.mIconTintList = iconTintList;
        this.mHasIconTint = true;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public ColorStateList getIconTintList() {
        return this.mIconTintList;
    }

    public MenuItem setIconTintMode(PorterDuff.Mode iconTintMode) {
        this.mIconTintMode = iconTintMode;
        this.mHasIconTintMode = true;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.mIconTintMode;
    }

    private Drawable applyIconTintIfNecessary(Drawable drawable) {
        Drawable icon = drawable;
        if (icon != null && this.mNeedToApplyIconTint && (this.mHasIconTint || this.mHasIconTintMode)) {
            icon = DrawableCompat.wrap(icon).mutate();
            if (this.mHasIconTint) {
                DrawableCompat.setTintList(icon, this.mIconTintList);
            }
            if (this.mHasIconTintMode) {
                DrawableCompat.setTintMode(icon, this.mIconTintMode);
            }
            this.mNeedToApplyIconTint = false;
        }
        return icon;
    }

    public boolean isCheckable() {
        return (this.mFlags & 1) == 1;
    }

    public MenuItem setCheckable(boolean checkable) {
        int oldFlags = this.mFlags;
        this.mFlags = (this.mFlags & -2) | (checkable ? 1 : 0);
        if (oldFlags != this.mFlags) {
            this.mMenu.onItemsChanged(false);
        }
        return this;
    }

    public void setExclusiveCheckable(boolean exclusive) {
        this.mFlags = (this.mFlags & -5) | (exclusive ? 4 : 0);
    }

    public boolean isExclusiveCheckable() {
        return (this.mFlags & 4) != 0;
    }

    public boolean isChecked() {
        return (this.mFlags & 2) == 2;
    }

    public MenuItem setChecked(boolean z) {
        boolean checked = z;
        if ((this.mFlags & 4) != 0) {
            this.mMenu.setExclusiveItemChecked(this);
        } else {
            setCheckedInt(checked);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public void setCheckedInt(boolean checked) {
        int oldFlags = this.mFlags;
        this.mFlags = (this.mFlags & -3) | (checked ? 2 : 0);
        if (oldFlags != this.mFlags) {
            this.mMenu.onItemsChanged(false);
        }
    }

    public boolean isVisible() {
        if (this.mActionProvider == null || !this.mActionProvider.overridesItemVisibility()) {
            return (this.mFlags & 8) == 0;
        }
        return (this.mFlags & 8) == 0 && this.mActionProvider.isVisible();
    }

    /* access modifiers changed from: package-private */
    public boolean setVisibleInt(boolean shown) {
        int oldFlags = this.mFlags;
        this.mFlags = (this.mFlags & -9) | (shown ? 0 : 8);
        return oldFlags != this.mFlags;
    }

    public MenuItem setVisible(boolean shown) {
        if (setVisibleInt(shown)) {
            this.mMenu.onItemVisibleChanged(this);
        }
        return this;
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener clickListener) {
        this.mClickListener = clickListener;
        return this;
    }

    public String toString() {
        return this.mTitle != null ? this.mTitle.toString() : null;
    }

    /* access modifiers changed from: package-private */
    public void setMenuInfo(ContextMenu.ContextMenuInfo menuInfo) {
        ContextMenu.ContextMenuInfo contextMenuInfo = menuInfo;
        this.mMenuInfo = contextMenuInfo;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.mMenuInfo;
    }

    public void actionFormatChanged() {
        this.mMenu.onItemActionRequestChanged(this);
    }

    public boolean shouldShowIcon() {
        return this.mMenu.getOptionalIconsVisible();
    }

    public boolean isActionButton() {
        return (this.mFlags & 32) == 32;
    }

    public boolean requestsActionButton() {
        return (this.mShowAsAction & 1) == 1;
    }

    public boolean requiresActionButton() {
        return (this.mShowAsAction & 2) == 2;
    }

    public void setIsActionButton(boolean isActionButton) {
        if (isActionButton) {
            this.mFlags |= 32;
            return;
        }
        this.mFlags &= -33;
    }

    public boolean showsTextAsAction() {
        return (this.mShowAsAction & 4) == 4;
    }

    public void setShowAsAction(int i) {
        Throwable th;
        int actionEnum = i;
        switch (actionEnum & 3) {
            case 0:
            case 1:
            case 2:
                this.mShowAsAction = actionEnum;
                this.mMenu.onItemActionRequestChanged(this);
                return;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
                throw th2;
        }
    }

    public SupportMenuItem setActionView(View view) {
        View view2 = view;
        this.mActionView = view2;
        this.mActionProvider = null;
        if (view2 != null && view2.getId() == -1 && this.mId > 0) {
            view2.setId(this.mId);
        }
        this.mMenu.onItemActionRequestChanged(this);
        return this;
    }

    public SupportMenuItem setActionView(int resId) {
        ViewGroup viewGroup;
        Context context = this.mMenu.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        new LinearLayout(context);
        SupportMenuItem actionView = setActionView(inflater.inflate(resId, viewGroup, false));
        return this;
    }

    public View getActionView() {
        if (this.mActionView != null) {
            return this.mActionView;
        }
        if (this.mActionProvider == null) {
            return null;
        }
        this.mActionView = this.mActionProvider.onCreateActionView(this);
        return this.mActionView;
    }

    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        Throwable th;
        android.view.ActionProvider actionProvider2 = actionProvider;
        Throwable th2 = th;
        new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
        throw th2;
    }

    public android.view.ActionProvider getActionProvider() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
        throw th2;
    }

    public ActionProvider getSupportActionProvider() {
        return this.mActionProvider;
    }

    public SupportMenuItem setSupportActionProvider(ActionProvider actionProvider) {
        ActionProvider.VisibilityListener visibilityListener;
        ActionProvider actionProvider2 = actionProvider;
        if (this.mActionProvider != null) {
            this.mActionProvider.reset();
        }
        this.mActionView = null;
        this.mActionProvider = actionProvider2;
        this.mMenu.onItemsChanged(true);
        if (this.mActionProvider != null) {
            new ActionProvider.VisibilityListener(this) {
                final /* synthetic */ MenuItemImpl this$0;

                {
                    this.this$0 = this$0;
                }

                public void onActionProviderVisibilityChanged(boolean z) {
                    boolean z2 = z;
                    this.this$0.mMenu.onItemVisibleChanged(this.this$0);
                }
            };
            this.mActionProvider.setVisibilityListener(visibilityListener);
        }
        return this;
    }

    public SupportMenuItem setShowAsActionFlags(int actionEnum) {
        setShowAsAction(actionEnum);
        return this;
    }

    public boolean expandActionView() {
        if (!hasCollapsibleActionView()) {
            return false;
        }
        if (this.mOnActionExpandListener == null || this.mOnActionExpandListener.onMenuItemActionExpand(this)) {
            return this.mMenu.expandItemActionView(this);
        }
        return false;
    }

    public boolean collapseActionView() {
        if ((this.mShowAsAction & 8) == 0) {
            return false;
        }
        if (this.mActionView == null) {
            return true;
        }
        if (this.mOnActionExpandListener == null || this.mOnActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.mMenu.collapseItemActionView(this);
        }
        return false;
    }

    public boolean hasCollapsibleActionView() {
        if ((this.mShowAsAction & 8) == 0) {
            return false;
        }
        if (this.mActionView == null && this.mActionProvider != null) {
            this.mActionView = this.mActionProvider.onCreateActionView(this);
        }
        return this.mActionView != null;
    }

    public void setActionViewExpanded(boolean isExpanded) {
        this.mIsActionViewExpanded = isExpanded;
        this.mMenu.onItemsChanged(false);
    }

    public boolean isActionViewExpanded() {
        return this.mIsActionViewExpanded;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener listener) {
        this.mOnActionExpandListener = listener;
        return this;
    }

    public SupportMenuItem setContentDescription(CharSequence contentDescription) {
        this.mContentDescription = contentDescription;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    public SupportMenuItem setTooltipText(CharSequence tooltipText) {
        this.mTooltipText = tooltipText;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public CharSequence getTooltipText() {
        return this.mTooltipText;
    }
}
