package android.support.p003v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.view.menu.MenuPresenter;
import android.support.p003v7.widget.MenuItemHoverListener;
import android.support.p003v7.widget.MenuPopupWindow;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v7.view.menu.CascadingMenuPopup */
final class CascadingMenuPopup extends MenuPopup implements MenuPresenter, View.OnKeyListener, PopupWindow.OnDismissListener {
    static final int HORIZ_POSITION_LEFT = 0;
    static final int HORIZ_POSITION_RIGHT = 1;
    private static final int ITEM_LAYOUT = C0425R.layout.abc_cascading_menu_item_layout;
    static final int SUBMENU_TIMEOUT_MS = 200;
    private View mAnchorView;
    private final View.OnAttachStateChangeListener mAttachStateChangeListener;
    private final Context mContext;
    private int mDropDownGravity = 0;
    private boolean mForceShowIcon;
    final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener;
    private boolean mHasXOffset;
    private boolean mHasYOffset;
    private int mLastPosition;
    private final MenuItemHoverListener mMenuItemHoverListener;
    private final int mMenuMaxWidth;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private final List<MenuBuilder> mPendingMenus;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;
    private int mRawDropDownGravity = 0;
    boolean mShouldCloseImmediately;
    private boolean mShowTitle;
    final List<CascadingMenuInfo> mShowingMenus;
    View mShownAnchorView;
    final Handler mSubMenuHoverHandler;
    ViewTreeObserver mTreeObserver;
    private int mXOffset;
    private int mYOffset;

    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v7.view.menu.CascadingMenuPopup$HorizPosition */
    public @interface HorizPosition {
    }

    public CascadingMenuPopup(@NonNull Context context, @NonNull View anchor, @AttrRes int popupStyleAttr, @StyleRes int popupStyleRes, boolean overflowOnly) {
        List<MenuBuilder> list;
        List<CascadingMenuInfo> list2;
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
        View.OnAttachStateChangeListener onAttachStateChangeListener;
        MenuItemHoverListener menuItemHoverListener;
        Handler handler;
        Context context2 = context;
        new ArrayList();
        this.mPendingMenus = list;
        new ArrayList();
        this.mShowingMenus = list2;
        new ViewTreeObserver.OnGlobalLayoutListener(this) {
            final /* synthetic */ CascadingMenuPopup this$0;

            {
                this.this$0 = this$0;
            }

            public void onGlobalLayout() {
                if (this.this$0.isShowing() && this.this$0.mShowingMenus.size() > 0 && !this.this$0.mShowingMenus.get(0).window.isModal()) {
                    View anchor = this.this$0.mShownAnchorView;
                    if (anchor == null || !anchor.isShown()) {
                        this.this$0.dismiss();
                        return;
                    }
                    for (CascadingMenuInfo info : this.this$0.mShowingMenus) {
                        info.window.show();
                    }
                }
            }
        };
        this.mGlobalLayoutListener = onGlobalLayoutListener;
        new View.OnAttachStateChangeListener(this) {
            final /* synthetic */ CascadingMenuPopup this$0;

            {
                this.this$0 = this$0;
            }

            public void onViewAttachedToWindow(View v) {
            }

            public void onViewDetachedFromWindow(View view) {
                View v = view;
                if (this.this$0.mTreeObserver != null) {
                    if (!this.this$0.mTreeObserver.isAlive()) {
                        this.this$0.mTreeObserver = v.getViewTreeObserver();
                    }
                    this.this$0.mTreeObserver.removeGlobalOnLayoutListener(this.this$0.mGlobalLayoutListener);
                }
                v.removeOnAttachStateChangeListener(this);
            }
        };
        this.mAttachStateChangeListener = onAttachStateChangeListener;
        new MenuItemHoverListener(this) {
            final /* synthetic */ CascadingMenuPopup this$0;

            {
                this.this$0 = this$0;
            }

            public void onItemHoverExit(@NonNull MenuBuilder menu, @NonNull MenuItem menuItem) {
                MenuItem menuItem2 = menuItem;
                this.this$0.mSubMenuHoverHandler.removeCallbacksAndMessages(menu);
            }

            public void onItemHoverEnter(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
                CascadingMenuInfo nextInfo;
                Runnable runnable;
                MenuBuilder menu = menuBuilder;
                MenuItem item = menuItem;
                this.this$0.mSubMenuHoverHandler.removeCallbacksAndMessages((Object) null);
                int menuIndex = -1;
                int i = 0;
                int count = this.this$0.mShowingMenus.size();
                while (true) {
                    if (i >= count) {
                        break;
                    } else if (menu == this.this$0.mShowingMenus.get(i).menu) {
                        menuIndex = i;
                        break;
                    } else {
                        i++;
                    }
                }
                if (menuIndex != -1) {
                    int nextIndex = menuIndex + 1;
                    if (nextIndex < this.this$0.mShowingMenus.size()) {
                        nextInfo = this.this$0.mShowingMenus.get(nextIndex);
                    } else {
                        nextInfo = null;
                    }
                    final CascadingMenuInfo cascadingMenuInfo = nextInfo;
                    final MenuItem menuItem2 = item;
                    final MenuBuilder menuBuilder2 = menu;
                    new Runnable(this) {
                        final /* synthetic */ C04503 this$1;

                        {
                            this.this$1 = this$1;
                        }

                        public void run() {
                            if (cascadingMenuInfo != null) {
                                this.this$1.this$0.mShouldCloseImmediately = true;
                                cascadingMenuInfo.menu.close(false);
                                this.this$1.this$0.mShouldCloseImmediately = false;
                            }
                            if (menuItem2.isEnabled() && menuItem2.hasSubMenu()) {
                                boolean performItemAction = menuBuilder2.performItemAction(menuItem2, 4);
                            }
                        }
                    };
                    boolean postAtTime = this.this$0.mSubMenuHoverHandler.postAtTime(runnable, menu, SystemClock.uptimeMillis() + 200);
                }
            }
        };
        this.mMenuItemHoverListener = menuItemHoverListener;
        this.mContext = context2;
        this.mAnchorView = anchor;
        this.mPopupStyleAttr = popupStyleAttr;
        this.mPopupStyleRes = popupStyleRes;
        this.mOverflowOnly = overflowOnly;
        this.mForceShowIcon = false;
        this.mLastPosition = getInitialMenuPosition();
        Resources res = context2.getResources();
        this.mMenuMaxWidth = Math.max(res.getDisplayMetrics().widthPixels / 2, res.getDimensionPixelSize(C0425R.dimen.abc_config_prefDialogWidth));
        new Handler();
        this.mSubMenuHoverHandler = handler;
    }

    public void setForceShowIcon(boolean forceShow) {
        boolean z = forceShow;
        this.mForceShowIcon = z;
    }

    private MenuPopupWindow createPopupWindow() {
        MenuPopupWindow menuPopupWindow;
        new MenuPopupWindow(this.mContext, (AttributeSet) null, this.mPopupStyleAttr, this.mPopupStyleRes);
        MenuPopupWindow popupWindow = menuPopupWindow;
        popupWindow.setHoverListener(this.mMenuItemHoverListener);
        popupWindow.setOnItemClickListener(this);
        popupWindow.setOnDismissListener(this);
        popupWindow.setAnchorView(this.mAnchorView);
        popupWindow.setDropDownGravity(this.mDropDownGravity);
        popupWindow.setModal(true);
        popupWindow.setInputMethodMode(2);
        return popupWindow;
    }

    public void show() {
        if (!isShowing()) {
            for (MenuBuilder menu : this.mPendingMenus) {
                showMenu(menu);
            }
            this.mPendingMenus.clear();
            this.mShownAnchorView = this.mAnchorView;
            if (this.mShownAnchorView != null) {
                boolean addGlobalListener = this.mTreeObserver == null;
                this.mTreeObserver = this.mShownAnchorView.getViewTreeObserver();
                if (addGlobalListener) {
                    this.mTreeObserver.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
                }
                this.mShownAnchorView.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
            }
        }
    }

    public void dismiss() {
        int length = this.mShowingMenus.size();
        if (length > 0) {
            CascadingMenuInfo[] addedMenus = (CascadingMenuInfo[]) this.mShowingMenus.toArray(new CascadingMenuInfo[length]);
            for (int i = length - 1; i >= 0; i--) {
                CascadingMenuInfo info = addedMenus[i];
                if (info.window.isShowing()) {
                    info.window.dismiss();
                }
            }
        }
    }

    public boolean onKey(View view, int i, KeyEvent event) {
        View view2 = view;
        int keyCode = i;
        if (event.getAction() != 1 || keyCode != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    private int getInitialMenuPosition() {
        return ViewCompat.getLayoutDirection(this.mAnchorView) == 1 ? 0 : 1;
    }

    private int getNextMenuPosition(int i) {
        Rect rect;
        int nextMenuWidth = i;
        ListView lastListView = this.mShowingMenus.get(this.mShowingMenus.size() - 1).getListView();
        int[] screenLocation = new int[2];
        lastListView.getLocationOnScreen(screenLocation);
        new Rect();
        Rect displayFrame = rect;
        this.mShownAnchorView.getWindowVisibleDisplayFrame(displayFrame);
        if (this.mLastPosition == 1) {
            if (screenLocation[0] + lastListView.getWidth() + nextMenuWidth > displayFrame.right) {
                return 0;
            }
            return 1;
        } else if (screenLocation[0] - nextMenuWidth < 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void addMenu(MenuBuilder menuBuilder) {
        MenuBuilder menu = menuBuilder;
        menu.addMenuPresenter(this, this.mContext);
        if (isShowing()) {
            showMenu(menu);
        } else {
            boolean add = this.mPendingMenus.add(menu);
        }
    }

    private void showMenu(@NonNull MenuBuilder menuBuilder) {
        MenuAdapter menuAdapter;
        CascadingMenuInfo parentInfo;
        View parentView;
        Object obj;
        int parentOffsetX;
        int parentOffsetY;
        int x;
        MenuBuilder menu = menuBuilder;
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        new MenuAdapter(menu, inflater, this.mOverflowOnly, ITEM_LAYOUT);
        MenuAdapter adapter = menuAdapter;
        if (!isShowing() && this.mForceShowIcon) {
            adapter.setForceShowIcon(true);
        } else if (isShowing()) {
            adapter.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(menu));
        }
        int menuWidth = measureIndividualMenuWidth(adapter, (ViewGroup) null, this.mContext, this.mMenuMaxWidth);
        MenuPopupWindow popupWindow = createPopupWindow();
        popupWindow.setAdapter(adapter);
        popupWindow.setContentWidth(menuWidth);
        popupWindow.setDropDownGravity(this.mDropDownGravity);
        if (this.mShowingMenus.size() > 0) {
            parentInfo = this.mShowingMenus.get(this.mShowingMenus.size() - 1);
            parentView = findParentViewForSubmenu(parentInfo, menu);
        } else {
            parentInfo = null;
            parentView = null;
        }
        if (parentView != null) {
            popupWindow.setTouchModal(false);
            popupWindow.setEnterTransition((Object) null);
            int nextMenuPosition = getNextMenuPosition(menuWidth);
            boolean showOnRight = nextMenuPosition == 1;
            this.mLastPosition = nextMenuPosition;
            if (Build.VERSION.SDK_INT >= 26) {
                popupWindow.setAnchorView(parentView);
                parentOffsetX = 0;
                parentOffsetY = 0;
            } else {
                int[] anchorScreenLocation = new int[2];
                this.mAnchorView.getLocationOnScreen(anchorScreenLocation);
                int[] parentViewScreenLocation = new int[2];
                parentView.getLocationOnScreen(parentViewScreenLocation);
                if ((this.mDropDownGravity & 7) == 5) {
                    int[] iArr = anchorScreenLocation;
                    iArr[0] = iArr[0] + this.mAnchorView.getWidth();
                    int[] iArr2 = parentViewScreenLocation;
                    iArr2[0] = iArr2[0] + parentView.getWidth();
                }
                parentOffsetX = parentViewScreenLocation[0] - anchorScreenLocation[0];
                parentOffsetY = parentViewScreenLocation[1] - anchorScreenLocation[1];
            }
            if ((this.mDropDownGravity & 5) == 5) {
                if (showOnRight) {
                    x = parentOffsetX + menuWidth;
                } else {
                    x = parentOffsetX - parentView.getWidth();
                }
            } else if (showOnRight) {
                x = parentOffsetX + parentView.getWidth();
            } else {
                x = parentOffsetX - menuWidth;
            }
            popupWindow.setHorizontalOffset(x);
            popupWindow.setOverlapAnchor(true);
            popupWindow.setVerticalOffset(parentOffsetY);
        } else {
            if (this.mHasXOffset) {
                popupWindow.setHorizontalOffset(this.mXOffset);
            }
            if (this.mHasYOffset) {
                popupWindow.setVerticalOffset(this.mYOffset);
            }
            popupWindow.setEpicenterBounds(getEpicenterBounds());
        }
        new CascadingMenuInfo(popupWindow, menu, this.mLastPosition);
        boolean add = this.mShowingMenus.add(obj);
        popupWindow.show();
        ListView listView = popupWindow.getListView();
        listView.setOnKeyListener(this);
        if (parentInfo == null && this.mShowTitle && menu.getHeaderTitle() != null) {
            FrameLayout titleItemView = (FrameLayout) inflater.inflate(C0425R.layout.abc_popup_menu_header_item_layout, listView, false);
            TextView titleView = (TextView) titleItemView.findViewById(16908310);
            titleItemView.setEnabled(false);
            titleView.setText(menu.getHeaderTitle());
            listView.addHeaderView(titleItemView, (Object) null, false);
            popupWindow.show();
        }
    }

    private MenuItem findMenuItemForSubmenu(@NonNull MenuBuilder menuBuilder, @NonNull MenuBuilder menuBuilder2) {
        MenuBuilder parent = menuBuilder;
        MenuBuilder submenu = menuBuilder2;
        int count = parent.size();
        for (int i = 0; i < count; i++) {
            MenuItem item = parent.getItem(i);
            if (item.hasSubMenu() && submenu == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    @Nullable
    private View findParentViewForSubmenu(@NonNull CascadingMenuInfo cascadingMenuInfo, @NonNull MenuBuilder submenu) {
        int headersCount;
        MenuAdapter menuAdapter;
        CascadingMenuInfo parentInfo = cascadingMenuInfo;
        MenuItem owner = findMenuItemForSubmenu(parentInfo.menu, submenu);
        if (owner == null) {
            return null;
        }
        ListView listView = parentInfo.getListView();
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerAdapter = (HeaderViewListAdapter) listAdapter;
            headersCount = headerAdapter.getHeadersCount();
            menuAdapter = (MenuAdapter) headerAdapter.getWrappedAdapter();
        } else {
            headersCount = 0;
            menuAdapter = (MenuAdapter) listAdapter;
        }
        int ownerPosition = -1;
        int i = 0;
        int count = menuAdapter.getCount();
        while (true) {
            if (i >= count) {
                break;
            } else if (owner == menuAdapter.getItem(i)) {
                ownerPosition = i;
                break;
            } else {
                i++;
            }
        }
        if (ownerPosition == -1) {
            return null;
        }
        int ownerViewPosition = (ownerPosition + headersCount) - listView.getFirstVisiblePosition();
        if (ownerViewPosition < 0 || ownerViewPosition >= listView.getChildCount()) {
            return null;
        }
        return listView.getChildAt(ownerViewPosition);
    }

    public boolean isShowing() {
        return this.mShowingMenus.size() > 0 && this.mShowingMenus.get(0).window.isShowing();
    }

    public void onDismiss() {
        CascadingMenuInfo dismissedInfo = null;
        int i = 0;
        int count = this.mShowingMenus.size();
        while (true) {
            if (i >= count) {
                break;
            }
            CascadingMenuInfo info = this.mShowingMenus.get(i);
            if (!info.window.isShowing()) {
                dismissedInfo = info;
                break;
            }
            i++;
        }
        if (dismissedInfo != null) {
            dismissedInfo.menu.close(false);
        }
    }

    public void updateMenuView(boolean z) {
        boolean z2 = z;
        for (CascadingMenuInfo info : this.mShowingMenus) {
            toMenuAdapter(info.getListView().getAdapter()).notifyDataSetChanged();
        }
    }

    public void setCallback(MenuPresenter.Callback cb) {
        MenuPresenter.Callback callback = cb;
        this.mPresenterCallback = callback;
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        SubMenuBuilder subMenu = subMenuBuilder;
        for (CascadingMenuInfo info : this.mShowingMenus) {
            if (subMenu == info.menu) {
                boolean requestFocus = info.getListView().requestFocus();
                return true;
            }
        }
        if (!subMenu.hasVisibleItems()) {
            return false;
        }
        addMenu(subMenu);
        if (this.mPresenterCallback != null) {
            boolean onOpenSubMenu = this.mPresenterCallback.onOpenSubMenu(subMenu);
        }
        return true;
    }

    private int findIndexOfAddedMenu(@NonNull MenuBuilder menuBuilder) {
        MenuBuilder menu = menuBuilder;
        int count = this.mShowingMenus.size();
        for (int i = 0; i < count; i++) {
            if (menu == this.mShowingMenus.get(i).menu) {
                return i;
            }
        }
        return -1;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        MenuBuilder menu = menuBuilder;
        boolean allMenusAreClosing = z;
        int menuIndex = findIndexOfAddedMenu(menu);
        if (menuIndex >= 0) {
            int nextMenuIndex = menuIndex + 1;
            if (nextMenuIndex < this.mShowingMenus.size()) {
                this.mShowingMenus.get(nextMenuIndex).menu.close(false);
            }
            CascadingMenuInfo info = this.mShowingMenus.remove(menuIndex);
            info.menu.removeMenuPresenter(this);
            if (this.mShouldCloseImmediately) {
                info.window.setExitTransition((Object) null);
                info.window.setAnimationStyle(0);
            }
            info.window.dismiss();
            int count = this.mShowingMenus.size();
            if (count > 0) {
                this.mLastPosition = this.mShowingMenus.get(count - 1).position;
            } else {
                this.mLastPosition = getInitialMenuPosition();
            }
            if (count == 0) {
                dismiss();
                if (this.mPresenterCallback != null) {
                    this.mPresenterCallback.onCloseMenu(menu, true);
                }
                if (this.mTreeObserver != null) {
                    if (this.mTreeObserver.isAlive()) {
                        this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
                    }
                    this.mTreeObserver = null;
                }
                this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
                this.mOnDismissListener.onDismiss();
            } else if (allMenusAreClosing) {
                this.mShowingMenus.get(0).menu.close(false);
            }
        }
    }

    public boolean flagActionItems() {
        return false;
    }

    public Parcelable onSaveInstanceState() {
        return null;
    }

    public void onRestoreInstanceState(Parcelable state) {
    }

    public void setGravity(int i) {
        int dropDownGravity = i;
        if (this.mRawDropDownGravity != dropDownGravity) {
            this.mRawDropDownGravity = dropDownGravity;
            this.mDropDownGravity = GravityCompat.getAbsoluteGravity(dropDownGravity, ViewCompat.getLayoutDirection(this.mAnchorView));
        }
    }

    public void setAnchorView(@NonNull View view) {
        View anchor = view;
        if (this.mAnchorView != anchor) {
            this.mAnchorView = anchor;
            this.mDropDownGravity = GravityCompat.getAbsoluteGravity(this.mRawDropDownGravity, ViewCompat.getLayoutDirection(this.mAnchorView));
        }
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener listener) {
        PopupWindow.OnDismissListener onDismissListener = listener;
        this.mOnDismissListener = onDismissListener;
    }

    public ListView getListView() {
        ListView listView;
        if (this.mShowingMenus.isEmpty()) {
            listView = null;
        } else {
            listView = this.mShowingMenus.get(this.mShowingMenus.size() - 1).getListView();
        }
        return listView;
    }

    public void setHorizontalOffset(int x) {
        this.mHasXOffset = true;
        this.mXOffset = x;
    }

    public void setVerticalOffset(int y) {
        this.mHasYOffset = true;
        this.mYOffset = y;
    }

    public void setShowTitle(boolean showTitle) {
        boolean z = showTitle;
        this.mShowTitle = z;
    }

    /* access modifiers changed from: protected */
    public boolean closeMenuOnSubMenuOpened() {
        return false;
    }

    /* renamed from: android.support.v7.view.menu.CascadingMenuPopup$CascadingMenuInfo */
    private static class CascadingMenuInfo {
        public final MenuBuilder menu;
        public final int position;
        public final MenuPopupWindow window;

        public CascadingMenuInfo(@NonNull MenuPopupWindow window2, @NonNull MenuBuilder menu2, int position2) {
            this.window = window2;
            this.menu = menu2;
            this.position = position2;
        }

        public ListView getListView() {
            return this.window.getListView();
        }
    }
}
