package android.support.p003v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.view.ActionProvider;
import android.support.p000v4.view.GravityCompat;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.view.ActionBarPolicy;
import android.support.p003v7.view.menu.ActionMenuItemView;
import android.support.p003v7.view.menu.BaseMenuPresenter;
import android.support.p003v7.view.menu.MenuBuilder;
import android.support.p003v7.view.menu.MenuItemImpl;
import android.support.p003v7.view.menu.MenuPopupHelper;
import android.support.p003v7.view.menu.MenuPresenter;
import android.support.p003v7.view.menu.MenuView;
import android.support.p003v7.view.menu.ShowableListMenu;
import android.support.p003v7.view.menu.SubMenuBuilder;
import android.support.p003v7.widget.ActionMenuView;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* renamed from: android.support.v7.widget.ActionMenuPresenter */
class ActionMenuPresenter extends BaseMenuPresenter implements ActionProvider.SubUiVisibilityListener {
    private static final String TAG = "ActionMenuPresenter";
    private final SparseBooleanArray mActionButtonGroups;
    ActionButtonSubmenu mActionButtonPopup;
    private int mActionItemWidthLimit;
    private boolean mExpandedActionViewsExclusive;
    private int mMaxItems;
    private boolean mMaxItemsSet;
    private int mMinCellSize;
    int mOpenSubMenuId;
    OverflowMenuButton mOverflowButton;
    OverflowPopup mOverflowPopup;
    private Drawable mPendingOverflowIcon;
    private boolean mPendingOverflowIconSet;
    private ActionMenuPopupCallback mPopupCallback;
    final PopupPresenterCallback mPopupPresenterCallback;
    OpenOverflowRunnable mPostedOpenRunnable;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private View mScrapActionButtonView;
    private boolean mStrictWidthLimit;
    private int mWidthLimit;
    private boolean mWidthLimitSet;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ActionMenuPresenter(Context context) {
        super(context, C0425R.layout.abc_action_menu_layout, C0425R.layout.abc_action_menu_item_layout);
        SparseBooleanArray sparseBooleanArray;
        PopupPresenterCallback popupPresenterCallback;
        new SparseBooleanArray();
        this.mActionButtonGroups = sparseBooleanArray;
        new PopupPresenterCallback(this);
        this.mPopupPresenterCallback = popupPresenterCallback;
    }

    public void initForMenu(@NonNull Context context, @Nullable MenuBuilder menu) {
        OverflowMenuButton overflowMenuButton;
        Context context2 = context;
        super.initForMenu(context2, menu);
        Resources res = context2.getResources();
        ActionBarPolicy abp = ActionBarPolicy.get(context2);
        if (!this.mReserveOverflowSet) {
            this.mReserveOverflow = abp.showsOverflowMenuButton();
        }
        if (!this.mWidthLimitSet) {
            this.mWidthLimit = abp.getEmbeddedMenuWidthLimit();
        }
        if (!this.mMaxItemsSet) {
            this.mMaxItems = abp.getMaxActionButtons();
        }
        int width = this.mWidthLimit;
        if (this.mReserveOverflow) {
            if (this.mOverflowButton == null) {
                new OverflowMenuButton(this, this.mSystemContext);
                this.mOverflowButton = overflowMenuButton;
                if (this.mPendingOverflowIconSet) {
                    this.mOverflowButton.setImageDrawable(this.mPendingOverflowIcon);
                    this.mPendingOverflowIcon = null;
                    this.mPendingOverflowIconSet = false;
                }
                int spec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.mOverflowButton.measure(spec, spec);
            }
            width -= this.mOverflowButton.getMeasuredWidth();
        } else {
            this.mOverflowButton = null;
        }
        this.mActionItemWidthLimit = width;
        this.mMinCellSize = (int) (56.0f * res.getDisplayMetrics().density);
        this.mScrapActionButtonView = null;
    }

    public void onConfigurationChanged(Configuration configuration) {
        Configuration configuration2 = configuration;
        if (!this.mMaxItemsSet) {
            this.mMaxItems = ActionBarPolicy.get(this.mContext).getMaxActionButtons();
        }
        if (this.mMenu != null) {
            this.mMenu.onItemsChanged(true);
        }
    }

    public void setWidthLimit(int width, boolean strict) {
        this.mWidthLimit = width;
        this.mStrictWidthLimit = strict;
        this.mWidthLimitSet = true;
    }

    public void setReserveOverflow(boolean reserveOverflow) {
        this.mReserveOverflow = reserveOverflow;
        this.mReserveOverflowSet = true;
    }

    public void setItemLimit(int itemCount) {
        this.mMaxItems = itemCount;
        this.mMaxItemsSet = true;
    }

    public void setExpandedActionViewsExclusive(boolean isExclusive) {
        boolean z = isExclusive;
        this.mExpandedActionViewsExclusive = z;
    }

    public void setOverflowIcon(Drawable drawable) {
        Drawable icon = drawable;
        if (this.mOverflowButton != null) {
            this.mOverflowButton.setImageDrawable(icon);
            return;
        }
        this.mPendingOverflowIconSet = true;
        this.mPendingOverflowIcon = icon;
    }

    public Drawable getOverflowIcon() {
        if (this.mOverflowButton != null) {
            return this.mOverflowButton.getDrawable();
        }
        if (this.mPendingOverflowIconSet) {
            return this.mPendingOverflowIcon;
        }
        return null;
    }

    public MenuView getMenuView(ViewGroup root) {
        MenuView oldMenuView = this.mMenuView;
        MenuView result = super.getMenuView(root);
        if (oldMenuView != result) {
            ((ActionMenuView) result).setPresenter(this);
        }
        return result;
    }

    public View getItemView(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        MenuItemImpl item = menuItemImpl;
        View convertView = view;
        ViewGroup parent = viewGroup;
        View actionView = item.getActionView();
        if (actionView == null || item.hasCollapsibleActionView()) {
            actionView = super.getItemView(item, convertView, parent);
        }
        actionView.setVisibility(item.isActionViewExpanded() ? 8 : 0);
        ActionMenuView menuParent = (ActionMenuView) parent;
        ViewGroup.LayoutParams lp = actionView.getLayoutParams();
        if (!menuParent.checkLayoutParams(lp)) {
            actionView.setLayoutParams(menuParent.generateLayoutParams(lp));
        }
        return actionView;
    }

    public void bindItemView(MenuItemImpl item, MenuView.ItemView itemView) {
        ActionMenuPopupCallback actionMenuPopupCallback;
        MenuView.ItemView itemView2 = itemView;
        itemView2.initialize(item, 0);
        ActionMenuItemView actionItemView = (ActionMenuItemView) itemView2;
        actionItemView.setItemInvoker((ActionMenuView) this.mMenuView);
        if (this.mPopupCallback == null) {
            new ActionMenuPopupCallback(this);
            this.mPopupCallback = actionMenuPopupCallback;
        }
        actionItemView.setPopupCallback(this.mPopupCallback);
    }

    public boolean shouldIncludeItem(int i, MenuItemImpl item) {
        int i2 = i;
        return item.isActionButton();
    }

    public void updateMenuView(boolean cleared) {
        OverflowMenuButton overflowMenuButton;
        super.updateMenuView(cleared);
        ((View) this.mMenuView).requestLayout();
        if (this.mMenu != null) {
            ArrayList<MenuItemImpl> actionItems = this.mMenu.getActionItems();
            int count = actionItems.size();
            for (int i = 0; i < count; i++) {
                ActionProvider provider = actionItems.get(i).getSupportActionProvider();
                if (provider != null) {
                    provider.setSubUiVisibilityListener(this);
                }
            }
        }
        ArrayList<MenuItemImpl> nonActionItems = this.mMenu != null ? this.mMenu.getNonActionItems() : null;
        boolean hasOverflow = false;
        if (this.mReserveOverflow && nonActionItems != null) {
            int count2 = nonActionItems.size();
            if (count2 == 1) {
                hasOverflow = !nonActionItems.get(0).isActionViewExpanded();
            } else {
                hasOverflow = count2 > 0;
            }
        }
        if (hasOverflow) {
            if (this.mOverflowButton == null) {
                new OverflowMenuButton(this, this.mSystemContext);
                this.mOverflowButton = overflowMenuButton;
            }
            ViewGroup parent = (ViewGroup) this.mOverflowButton.getParent();
            if (parent != this.mMenuView) {
                if (parent != null) {
                    parent.removeView(this.mOverflowButton);
                }
                ActionMenuView menuView = (ActionMenuView) this.mMenuView;
                menuView.addView(this.mOverflowButton, menuView.generateOverflowButtonLayoutParams());
            }
        } else if (this.mOverflowButton != null && this.mOverflowButton.getParent() == this.mMenuView) {
            ((ViewGroup) this.mMenuView).removeView(this.mOverflowButton);
        }
        ((ActionMenuView) this.mMenuView).setOverflowReserved(this.mReserveOverflow);
    }

    public boolean filterLeftoverView(ViewGroup viewGroup, int i) {
        ViewGroup parent = viewGroup;
        int childIndex = i;
        if (parent.getChildAt(childIndex) == this.mOverflowButton) {
            return false;
        }
        return super.filterLeftoverView(parent, childIndex);
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        SubMenuBuilder topSubMenu;
        ActionButtonSubmenu actionButtonSubmenu;
        SubMenuBuilder subMenu = subMenuBuilder;
        if (!subMenu.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenu;
        while (true) {
            topSubMenu = subMenuBuilder2;
            if (topSubMenu.getParentMenu() == this.mMenu) {
                break;
            }
            subMenuBuilder2 = (SubMenuBuilder) topSubMenu.getParentMenu();
        }
        View anchor = findViewForItem(topSubMenu.getItem());
        if (anchor == null) {
            return false;
        }
        this.mOpenSubMenuId = subMenu.getItem().getItemId();
        boolean preserveIconSpacing = false;
        int count = subMenu.size();
        int i = 0;
        while (true) {
            if (i >= count) {
                break;
            }
            MenuItem childItem = subMenu.getItem(i);
            if (childItem.isVisible() && childItem.getIcon() != null) {
                preserveIconSpacing = true;
                break;
            }
            i++;
        }
        new ActionButtonSubmenu(this, this.mContext, subMenu, anchor);
        this.mActionButtonPopup = actionButtonSubmenu;
        this.mActionButtonPopup.setForceShowIcon(preserveIconSpacing);
        this.mActionButtonPopup.show();
        boolean onSubMenuSelected = super.onSubMenuSelected(subMenu);
        return true;
    }

    private View findViewForItem(MenuItem menuItem) {
        MenuItem item = menuItem;
        ViewGroup parent = (ViewGroup) this.mMenuView;
        if (parent == null) {
            return null;
        }
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            if ((child instanceof MenuView.ItemView) && ((MenuView.ItemView) child).getItemData() == item) {
                return child;
            }
        }
        return null;
    }

    public boolean showOverflowMenu() {
        OverflowPopup popup;
        OpenOverflowRunnable openOverflowRunnable;
        if (!this.mReserveOverflow || isOverflowMenuShowing() || this.mMenu == null || this.mMenuView == null || this.mPostedOpenRunnable != null || this.mMenu.getNonActionItems().isEmpty()) {
            return false;
        }
        new OverflowPopup(this, this.mContext, this.mMenu, this.mOverflowButton, true);
        new OpenOverflowRunnable(this, popup);
        this.mPostedOpenRunnable = openOverflowRunnable;
        boolean post = ((View) this.mMenuView).post(this.mPostedOpenRunnable);
        boolean onSubMenuSelected = super.onSubMenuSelected((SubMenuBuilder) null);
        return true;
    }

    public boolean hideOverflowMenu() {
        if (this.mPostedOpenRunnable == null || this.mMenuView == null) {
            MenuPopupHelper popup = this.mOverflowPopup;
            if (popup == null) {
                return false;
            }
            popup.dismiss();
            return true;
        }
        boolean removeCallbacks = ((View) this.mMenuView).removeCallbacks(this.mPostedOpenRunnable);
        this.mPostedOpenRunnable = null;
        return true;
    }

    public boolean dismissPopupMenus() {
        return hideOverflowMenu() | hideSubMenus();
    }

    public boolean hideSubMenus() {
        if (this.mActionButtonPopup == null) {
            return false;
        }
        this.mActionButtonPopup.dismiss();
        return true;
    }

    public boolean isOverflowMenuShowing() {
        return this.mOverflowPopup != null && this.mOverflowPopup.isShowing();
    }

    public boolean isOverflowMenuShowPending() {
        return this.mPostedOpenRunnable != null || isOverflowMenuShowing();
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    /* JADX WARNING: Removed duplicated region for block: B:65:0x020d  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0295  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x02a2  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x02d2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean flagActionItems() {
        /*
            r29 = this;
            r2 = r29
            r23 = r2
            r0 = r23
            android.support.v7.view.menu.MenuBuilder r0 = r0.mMenu
            r23 = r0
            if (r23 == 0) goto L_0x0099
            r23 = r2
            r0 = r23
            android.support.v7.view.menu.MenuBuilder r0 = r0.mMenu
            r23 = r0
            java.util.ArrayList r23 = r23.getVisibleItems()
            r3 = r23
            r23 = r3
            int r23 = r23.size()
            r4 = r23
        L_0x0022:
            r23 = r2
            r0 = r23
            int r0 = r0.mMaxItems
            r23 = r0
            r5 = r23
            r23 = r2
            r0 = r23
            int r0 = r0.mActionItemWidthLimit
            r23 = r0
            r6 = r23
            r23 = 0
            r24 = 0
            int r23 = android.view.View.MeasureSpec.makeMeasureSpec(r23, r24)
            r7 = r23
            r23 = r2
            r0 = r23
            android.support.v7.view.menu.MenuView r0 = r0.mMenuView
            r23 = r0
            android.view.ViewGroup r23 = (android.view.ViewGroup) r23
            r8 = r23
            r23 = 0
            r9 = r23
            r23 = 0
            r10 = r23
            r23 = 0
            r11 = r23
            r23 = 0
            r12 = r23
            r23 = 0
            r13 = r23
        L_0x0060:
            r23 = r13
            r24 = r4
            r0 = r23
            r1 = r24
            if (r0 >= r1) goto L_0x00b2
            r23 = r3
            r24 = r13
            java.lang.Object r23 = r23.get(r24)
            android.support.v7.view.menu.MenuItemImpl r23 = (android.support.p003v7.view.menu.MenuItemImpl) r23
            r14 = r23
            r23 = r14
            boolean r23 = r23.requiresActionButton()
            if (r23 == 0) goto L_0x00a2
            int r9 = r9 + 1
        L_0x0080:
            r23 = r2
            r0 = r23
            boolean r0 = r0.mExpandedActionViewsExclusive
            r23 = r0
            if (r23 == 0) goto L_0x0096
            r23 = r14
            boolean r23 = r23.isActionViewExpanded()
            if (r23 == 0) goto L_0x0096
            r23 = 0
            r5 = r23
        L_0x0096:
            int r13 = r13 + 1
            goto L_0x0060
        L_0x0099:
            r23 = 0
            r3 = r23
            r23 = 0
            r4 = r23
            goto L_0x0022
        L_0x00a2:
            r23 = r14
            boolean r23 = r23.requestsActionButton()
            if (r23 == 0) goto L_0x00ad
            int r10 = r10 + 1
            goto L_0x0080
        L_0x00ad:
            r23 = 1
            r12 = r23
            goto L_0x0080
        L_0x00b2:
            r23 = r2
            r0 = r23
            boolean r0 = r0.mReserveOverflow
            r23 = r0
            if (r23 == 0) goto L_0x00d0
            r23 = r12
            if (r23 != 0) goto L_0x00ce
            r23 = r9
            r24 = r10
            int r23 = r23 + r24
            r24 = r5
            r0 = r23
            r1 = r24
            if (r0 <= r1) goto L_0x00d0
        L_0x00ce:
            int r5 = r5 + -1
        L_0x00d0:
            r23 = r5
            r24 = r9
            int r23 = r23 - r24
            r5 = r23
            r23 = r2
            r0 = r23
            android.util.SparseBooleanArray r0 = r0.mActionButtonGroups
            r23 = r0
            r13 = r23
            r23 = r13
            r23.clear()
            r23 = 0
            r14 = r23
            r23 = 0
            r15 = r23
            r23 = r2
            r0 = r23
            boolean r0 = r0.mStrictWidthLimit
            r23 = r0
            if (r23 == 0) goto L_0x0127
            r23 = r6
            r24 = r2
            r0 = r24
            int r0 = r0.mMinCellSize
            r24 = r0
            int r23 = r23 / r24
            r15 = r23
            r23 = r6
            r24 = r2
            r0 = r24
            int r0 = r0.mMinCellSize
            r24 = r0
            int r23 = r23 % r24
            r16 = r23
            r23 = r2
            r0 = r23
            int r0 = r0.mMinCellSize
            r23 = r0
            r24 = r16
            r25 = r15
            int r24 = r24 / r25
            int r23 = r23 + r24
            r14 = r23
        L_0x0127:
            r23 = 0
            r16 = r23
        L_0x012b:
            r23 = r16
            r24 = r4
            r0 = r23
            r1 = r24
            if (r0 >= r1) goto L_0x0324
            r23 = r3
            r24 = r16
            java.lang.Object r23 = r23.get(r24)
            android.support.v7.view.menu.MenuItemImpl r23 = (android.support.p003v7.view.menu.MenuItemImpl) r23
            r17 = r23
            r23 = r17
            boolean r23 = r23.requiresActionButton()
            if (r23 == 0) goto L_0x01d1
            r23 = r2
            r24 = r17
            r25 = r2
            r0 = r25
            android.view.View r0 = r0.mScrapActionButtonView
            r25 = r0
            r26 = r8
            android.view.View r23 = r23.getItemView(r24, r25, r26)
            r18 = r23
            r23 = r2
            r0 = r23
            android.view.View r0 = r0.mScrapActionButtonView
            r23 = r0
            if (r23 != 0) goto L_0x0171
            r23 = r2
            r24 = r18
            r0 = r24
            r1 = r23
            r1.mScrapActionButtonView = r0
        L_0x0171:
            r23 = r2
            r0 = r23
            boolean r0 = r0.mStrictWidthLimit
            r23 = r0
            if (r23 == 0) goto L_0x01c7
            r23 = r15
            r24 = r18
            r25 = r14
            r26 = r15
            r27 = r7
            r28 = 0
            int r24 = android.support.p003v7.widget.ActionMenuView.measureChildForCells(r24, r25, r26, r27, r28)
            int r23 = r23 - r24
            r15 = r23
        L_0x018f:
            r23 = r18
            int r23 = r23.getMeasuredWidth()
            r19 = r23
            r23 = r6
            r24 = r19
            int r23 = r23 - r24
            r6 = r23
            r23 = r11
            if (r23 != 0) goto L_0x01a7
            r23 = r19
            r11 = r23
        L_0x01a7:
            r23 = r17
            int r23 = r23.getGroupId()
            r20 = r23
            r23 = r20
            if (r23 == 0) goto L_0x01bc
            r23 = r13
            r24 = r20
            r25 = 1
            r23.put(r24, r25)
        L_0x01bc:
            r23 = r17
            r24 = 1
            r23.setIsActionButton(r24)
        L_0x01c3:
            int r16 = r16 + 1
            goto L_0x012b
        L_0x01c7:
            r23 = r18
            r24 = r7
            r25 = r7
            r23.measure(r24, r25)
            goto L_0x018f
        L_0x01d1:
            r23 = r17
            boolean r23 = r23.requestsActionButton()
            if (r23 == 0) goto L_0x031b
            r23 = r17
            int r23 = r23.getGroupId()
            r18 = r23
            r23 = r13
            r24 = r18
            boolean r23 = r23.get(r24)
            r19 = r23
            r23 = r5
            if (r23 > 0) goto L_0x01f3
            r23 = r19
            if (r23 == 0) goto L_0x02ad
        L_0x01f3:
            r23 = r6
            if (r23 <= 0) goto L_0x02ad
            r23 = r2
            r0 = r23
            boolean r0 = r0.mStrictWidthLimit
            r23 = r0
            if (r23 == 0) goto L_0x0205
            r23 = r15
            if (r23 <= 0) goto L_0x02ad
        L_0x0205:
            r23 = 1
        L_0x0207:
            r20 = r23
            r23 = r20
            if (r23 == 0) goto L_0x028d
            r23 = r2
            r24 = r17
            r25 = r2
            r0 = r25
            android.view.View r0 = r0.mScrapActionButtonView
            r25 = r0
            r26 = r8
            android.view.View r23 = r23.getItemView(r24, r25, r26)
            r21 = r23
            r23 = r2
            r0 = r23
            android.view.View r0 = r0.mScrapActionButtonView
            r23 = r0
            if (r23 != 0) goto L_0x0235
            r23 = r2
            r24 = r21
            r0 = r24
            r1 = r23
            r1.mScrapActionButtonView = r0
        L_0x0235:
            r23 = r2
            r0 = r23
            boolean r0 = r0.mStrictWidthLimit
            r23 = r0
            if (r23 == 0) goto L_0x02b1
            r23 = r21
            r24 = r14
            r25 = r15
            r26 = r7
            r27 = 0
            int r23 = android.support.p003v7.widget.ActionMenuView.measureChildForCells(r23, r24, r25, r26, r27)
            r22 = r23
            r23 = r15
            r24 = r22
            int r23 = r23 - r24
            r15 = r23
            r23 = r22
            if (r23 != 0) goto L_0x025f
            r23 = 0
            r20 = r23
        L_0x025f:
            r23 = r21
            int r23 = r23.getMeasuredWidth()
            r22 = r23
            r23 = r6
            r24 = r22
            int r23 = r23 - r24
            r6 = r23
            r23 = r11
            if (r23 != 0) goto L_0x0277
            r23 = r22
            r11 = r23
        L_0x0277:
            r23 = r2
            r0 = r23
            boolean r0 = r0.mStrictWidthLimit
            r23 = r0
            if (r23 == 0) goto L_0x02be
            r23 = r20
            r24 = r6
            if (r24 < 0) goto L_0x02bb
            r24 = 1
        L_0x0289:
            r23 = r23 & r24
            r20 = r23
        L_0x028d:
            r23 = r20
            if (r23 == 0) goto L_0x02d2
            r23 = r18
            if (r23 == 0) goto L_0x02d2
            r23 = r13
            r24 = r18
            r25 = 1
            r23.put(r24, r25)
        L_0x029e:
            r23 = r20
            if (r23 == 0) goto L_0x02a4
            int r5 = r5 + -1
        L_0x02a4:
            r23 = r17
            r24 = r20
            r23.setIsActionButton(r24)
            goto L_0x01c3
        L_0x02ad:
            r23 = 0
            goto L_0x0207
        L_0x02b1:
            r23 = r21
            r24 = r7
            r25 = r7
            r23.measure(r24, r25)
            goto L_0x025f
        L_0x02bb:
            r24 = 0
            goto L_0x0289
        L_0x02be:
            r23 = r20
            r24 = r6
            r25 = r11
            int r24 = r24 + r25
            if (r24 <= 0) goto L_0x02cf
            r24 = 1
        L_0x02ca:
            r23 = r23 & r24
            r20 = r23
            goto L_0x028d
        L_0x02cf:
            r24 = 0
            goto L_0x02ca
        L_0x02d2:
            r23 = r19
            if (r23 == 0) goto L_0x029e
            r23 = r13
            r24 = r18
            r25 = 0
            r23.put(r24, r25)
            r23 = 0
            r21 = r23
        L_0x02e3:
            r23 = r21
            r24 = r16
            r0 = r23
            r1 = r24
            if (r0 >= r1) goto L_0x029e
            r23 = r3
            r24 = r21
            java.lang.Object r23 = r23.get(r24)
            android.support.v7.view.menu.MenuItemImpl r23 = (android.support.p003v7.view.menu.MenuItemImpl) r23
            r22 = r23
            r23 = r22
            int r23 = r23.getGroupId()
            r24 = r18
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x0318
            r23 = r22
            boolean r23 = r23.isActionButton()
            if (r23 == 0) goto L_0x0311
            int r5 = r5 + 1
        L_0x0311:
            r23 = r22
            r24 = 0
            r23.setIsActionButton(r24)
        L_0x0318:
            int r21 = r21 + 1
            goto L_0x02e3
        L_0x031b:
            r23 = r17
            r24 = 0
            r23.setIsActionButton(r24)
            goto L_0x01c3
        L_0x0324:
            r23 = 1
            r2 = r23
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.ActionMenuPresenter.flagActionItems():boolean");
    }

    public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
        boolean dismissPopupMenus = dismissPopupMenus();
        super.onCloseMenu(menu, allMenusAreClosing);
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        new SavedState();
        SavedState state = savedState;
        state.openSubMenuId = this.mOpenSubMenuId;
        return state;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem item;
        Parcelable state = parcelable;
        if (state instanceof SavedState) {
            SavedState saved = (SavedState) state;
            if (saved.openSubMenuId > 0 && (item = this.mMenu.findItem(saved.openSubMenuId)) != null) {
                boolean onSubMenuSelected = onSubMenuSelected((SubMenuBuilder) item.getSubMenu());
            }
        }
    }

    public void onSubUiVisibilityChanged(boolean isVisible) {
        if (isVisible) {
            boolean onSubMenuSelected = super.onSubMenuSelected((SubMenuBuilder) null);
        } else if (this.mMenu != null) {
            this.mMenu.close(false);
        }
    }

    public void setMenuView(ActionMenuView actionMenuView) {
        ActionMenuView menuView = actionMenuView;
        this.mMenuView = menuView;
        menuView.initialize(this.mMenu);
    }

    /* renamed from: android.support.v7.widget.ActionMenuPresenter$SavedState */
    private static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR;
        public int openSubMenuId;

        SavedState() {
        }

        SavedState(Parcel in) {
            this.openSubMenuId = in.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int i) {
            int i2 = i;
            dest.writeInt(this.openSubMenuId);
        }

        static {
            Parcelable.Creator<SavedState> creator;
            new Parcelable.Creator<SavedState>() {
                public SavedState createFromParcel(Parcel in) {
                    SavedState savedState;
                    new SavedState(in);
                    return savedState;
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
            CREATOR = creator;
        }
    }

    /* renamed from: android.support.v7.widget.ActionMenuPresenter$OverflowMenuButton */
    private class OverflowMenuButton extends AppCompatImageView implements ActionMenuView.ActionMenuChildView {
        private final float[] mTempPts = new float[2];
        final /* synthetic */ ActionMenuPresenter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public OverflowMenuButton(ActionMenuPresenter actionMenuPresenter, Context context) {
            super(context, (AttributeSet) null, C0425R.attr.actionOverflowButtonStyle);
            View.OnTouchListener onTouchListener;
            ActionMenuPresenter actionMenuPresenter2 = actionMenuPresenter;
            this.this$0 = actionMenuPresenter2;
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            TooltipCompat.setTooltipText(this, getContentDescription());
            final ActionMenuPresenter actionMenuPresenter3 = actionMenuPresenter2;
            new ForwardingListener(this, this) {
                final /* synthetic */ OverflowMenuButton this$1;

                {
                    this.this$1 = this$1;
                }

                public ShowableListMenu getPopup() {
                    if (this.this$1.this$0.mOverflowPopup == null) {
                        return null;
                    }
                    return this.this$1.this$0.mOverflowPopup.getPopup();
                }

                public boolean onForwardingStarted() {
                    boolean showOverflowMenu = this.this$1.this$0.showOverflowMenu();
                    return true;
                }

                public boolean onForwardingStopped() {
                    if (this.this$1.this$0.mPostedOpenRunnable != null) {
                        return false;
                    }
                    boolean hideOverflowMenu = this.this$1.this$0.hideOverflowMenu();
                    return true;
                }
            };
            setOnTouchListener(onTouchListener);
        }

        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            boolean showOverflowMenu = this.this$0.showOverflowMenu();
            return true;
        }

        public boolean needsDividerBefore() {
            return false;
        }

        public boolean needsDividerAfter() {
            return false;
        }

        /* access modifiers changed from: protected */
        public boolean setFrame(int l, int t, int r, int b) {
            boolean changed = super.setFrame(l, t, r, b);
            Drawable d = getDrawable();
            Drawable bg = getBackground();
            if (!(d == null || bg == null)) {
                int width = getWidth();
                int height = getHeight();
                int halfEdge = Math.max(width, height) / 2;
                int offsetX = getPaddingLeft() - getPaddingRight();
                int centerX = (width + offsetX) / 2;
                int centerY = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                DrawableCompat.setHotspotBounds(bg, centerX - halfEdge, centerY - halfEdge, centerX + halfEdge, centerY + halfEdge);
            }
            return changed;
        }
    }

    /* renamed from: android.support.v7.widget.ActionMenuPresenter$OverflowPopup */
    private class OverflowPopup extends MenuPopupHelper {
        final /* synthetic */ ActionMenuPresenter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public OverflowPopup(ActionMenuPresenter actionMenuPresenter, Context context, MenuBuilder menu, View anchorView, boolean overflowOnly) {
            super(context, menu, anchorView, overflowOnly, C0425R.attr.actionOverflowMenuStyle);
            ActionMenuPresenter actionMenuPresenter2 = actionMenuPresenter;
            this.this$0 = actionMenuPresenter2;
            setGravity(GravityCompat.END);
            setPresenterCallback(actionMenuPresenter2.mPopupPresenterCallback);
        }

        /* access modifiers changed from: protected */
        public void onDismiss() {
            if (this.this$0.mMenu != null) {
                this.this$0.mMenu.close();
            }
            this.this$0.mOverflowPopup = null;
            super.onDismiss();
        }
    }

    /* renamed from: android.support.v7.widget.ActionMenuPresenter$ActionButtonSubmenu */
    private class ActionButtonSubmenu extends MenuPopupHelper {
        final /* synthetic */ ActionMenuPresenter this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public ActionButtonSubmenu(android.support.p003v7.widget.ActionMenuPresenter r13, android.content.Context r14, android.support.p003v7.view.menu.SubMenuBuilder r15, android.view.View r16) {
            /*
                r12 = this;
                r0 = r12
                r1 = r13
                r2 = r14
                r3 = r15
                r4 = r16
                r6 = r0
                r7 = r1
                r6.this$0 = r7
                r6 = r0
                r7 = r2
                r8 = r3
                r9 = r4
                r10 = 0
                int r11 = android.support.p003v7.appcompat.C0425R.attr.actionOverflowMenuStyle
                r6.<init>(r7, r8, r9, r10, r11)
                r6 = r3
                android.view.MenuItem r6 = r6.getItem()
                android.support.v7.view.menu.MenuItemImpl r6 = (android.support.p003v7.view.menu.MenuItemImpl) r6
                r5 = r6
                r6 = r5
                boolean r6 = r6.isActionButton()
                if (r6 != 0) goto L_0x0033
                r6 = r0
                r7 = r1
                android.support.v7.widget.ActionMenuPresenter$OverflowMenuButton r7 = r7.mOverflowButton
                if (r7 != 0) goto L_0x003b
                r7 = r1
                android.support.v7.view.menu.MenuView r7 = r7.mMenuView
                android.view.View r7 = (android.view.View) r7
            L_0x0030:
                r6.setAnchorView(r7)
            L_0x0033:
                r6 = r0
                r7 = r1
                android.support.v7.widget.ActionMenuPresenter$PopupPresenterCallback r7 = r7.mPopupPresenterCallback
                r6.setPresenterCallback(r7)
                return
            L_0x003b:
                r7 = r1
                android.support.v7.widget.ActionMenuPresenter$OverflowMenuButton r7 = r7.mOverflowButton
                goto L_0x0030
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.ActionMenuPresenter.ActionButtonSubmenu.<init>(android.support.v7.widget.ActionMenuPresenter, android.content.Context, android.support.v7.view.menu.SubMenuBuilder, android.view.View):void");
        }

        /* access modifiers changed from: protected */
        public void onDismiss() {
            this.this$0.mActionButtonPopup = null;
            this.this$0.mOpenSubMenuId = 0;
            super.onDismiss();
        }
    }

    /* renamed from: android.support.v7.widget.ActionMenuPresenter$PopupPresenterCallback */
    private class PopupPresenterCallback implements MenuPresenter.Callback {
        final /* synthetic */ ActionMenuPresenter this$0;

        PopupPresenterCallback(ActionMenuPresenter actionMenuPresenter) {
            this.this$0 = actionMenuPresenter;
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            MenuBuilder subMenu = menuBuilder;
            if (subMenu == null) {
                return false;
            }
            this.this$0.mOpenSubMenuId = ((SubMenuBuilder) subMenu).getItem().getItemId();
            MenuPresenter.Callback cb = this.this$0.getCallback();
            return cb != null ? cb.onOpenSubMenu(subMenu) : false;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            MenuBuilder menu = menuBuilder;
            boolean allMenusAreClosing = z;
            if (menu instanceof SubMenuBuilder) {
                menu.getRootMenu().close(false);
            }
            MenuPresenter.Callback cb = this.this$0.getCallback();
            if (cb != null) {
                cb.onCloseMenu(menu, allMenusAreClosing);
            }
        }
    }

    /* renamed from: android.support.v7.widget.ActionMenuPresenter$OpenOverflowRunnable */
    private class OpenOverflowRunnable implements Runnable {
        private OverflowPopup mPopup;
        final /* synthetic */ ActionMenuPresenter this$0;

        public OpenOverflowRunnable(ActionMenuPresenter actionMenuPresenter, OverflowPopup popup) {
            this.this$0 = actionMenuPresenter;
            this.mPopup = popup;
        }

        public void run() {
            if (this.this$0.mMenu != null) {
                this.this$0.mMenu.changeMenuMode();
            }
            View menuView = (View) this.this$0.mMenuView;
            if (!(menuView == null || menuView.getWindowToken() == null || !this.mPopup.tryShow())) {
                this.this$0.mOverflowPopup = this.mPopup;
            }
            this.this$0.mPostedOpenRunnable = null;
        }
    }

    /* renamed from: android.support.v7.widget.ActionMenuPresenter$ActionMenuPopupCallback */
    private class ActionMenuPopupCallback extends ActionMenuItemView.PopupCallback {
        final /* synthetic */ ActionMenuPresenter this$0;

        ActionMenuPopupCallback(ActionMenuPresenter actionMenuPresenter) {
            this.this$0 = actionMenuPresenter;
        }

        public ShowableListMenu getPopup() {
            return this.this$0.mActionButtonPopup != null ? this.this$0.mActionButtonPopup.getPopup() : null;
        }
    }
}
