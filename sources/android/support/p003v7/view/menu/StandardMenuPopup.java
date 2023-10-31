package android.support.p003v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.p000v4.view.ViewCompat;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.view.menu.MenuPresenter;
import android.support.p003v7.widget.MenuPopupWindow;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

/* renamed from: android.support.v7.view.menu.StandardMenuPopup */
final class StandardMenuPopup extends MenuPopup implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, MenuPresenter, View.OnKeyListener {
    private static final int ITEM_LAYOUT = C0425R.layout.abc_popup_menu_item_layout;
    private final MenuAdapter mAdapter;
    private View mAnchorView;
    private final View.OnAttachStateChangeListener mAttachStateChangeListener;
    private int mContentWidth;
    private final Context mContext;
    private int mDropDownGravity = 0;
    final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener;
    private boolean mHasContentWidth;
    private final MenuBuilder mMenu;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    final MenuPopupWindow mPopup;
    private final int mPopupMaxWidth;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;
    private boolean mShowTitle;
    View mShownAnchorView;
    ViewTreeObserver mTreeObserver;
    private boolean mWasDismissed;

    public StandardMenuPopup(Context context, MenuBuilder menuBuilder, View anchorView, int popupStyleAttr, int popupStyleRes, boolean overflowOnly) {
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
        View.OnAttachStateChangeListener onAttachStateChangeListener;
        MenuAdapter menuAdapter;
        MenuPopupWindow menuPopupWindow;
        Context context2 = context;
        MenuBuilder menu = menuBuilder;
        new ViewTreeObserver.OnGlobalLayoutListener(this) {
            final /* synthetic */ StandardMenuPopup this$0;

            {
                this.this$0 = this$0;
            }

            public void onGlobalLayout() {
                if (this.this$0.isShowing() && !this.this$0.mPopup.isModal()) {
                    View anchor = this.this$0.mShownAnchorView;
                    if (anchor == null || !anchor.isShown()) {
                        this.this$0.dismiss();
                    } else {
                        this.this$0.mPopup.show();
                    }
                }
            }
        };
        this.mGlobalLayoutListener = onGlobalLayoutListener;
        new View.OnAttachStateChangeListener(this) {
            final /* synthetic */ StandardMenuPopup this$0;

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
        this.mContext = context2;
        this.mMenu = menu;
        this.mOverflowOnly = overflowOnly;
        new MenuAdapter(menu, LayoutInflater.from(context2), this.mOverflowOnly, ITEM_LAYOUT);
        this.mAdapter = menuAdapter;
        this.mPopupStyleAttr = popupStyleAttr;
        this.mPopupStyleRes = popupStyleRes;
        Resources res = context2.getResources();
        this.mPopupMaxWidth = Math.max(res.getDisplayMetrics().widthPixels / 2, res.getDimensionPixelSize(C0425R.dimen.abc_config_prefDialogWidth));
        this.mAnchorView = anchorView;
        new MenuPopupWindow(this.mContext, (AttributeSet) null, this.mPopupStyleAttr, this.mPopupStyleRes);
        this.mPopup = menuPopupWindow;
        menu.addMenuPresenter(this, context2);
    }

    public void setForceShowIcon(boolean forceShow) {
        this.mAdapter.setForceShowIcon(forceShow);
    }

    public void setGravity(int gravity) {
        int i = gravity;
        this.mDropDownGravity = i;
    }

    private boolean tryShow() {
        if (isShowing()) {
            return true;
        }
        if (this.mWasDismissed || this.mAnchorView == null) {
            return false;
        }
        this.mShownAnchorView = this.mAnchorView;
        this.mPopup.setOnDismissListener(this);
        this.mPopup.setOnItemClickListener(this);
        this.mPopup.setModal(true);
        View anchor = this.mShownAnchorView;
        boolean addGlobalListener = this.mTreeObserver == null;
        this.mTreeObserver = anchor.getViewTreeObserver();
        if (addGlobalListener) {
            this.mTreeObserver.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
        }
        anchor.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
        this.mPopup.setAnchorView(anchor);
        this.mPopup.setDropDownGravity(this.mDropDownGravity);
        if (!this.mHasContentWidth) {
            this.mContentWidth = measureIndividualMenuWidth(this.mAdapter, (ViewGroup) null, this.mContext, this.mPopupMaxWidth);
            this.mHasContentWidth = true;
        }
        this.mPopup.setContentWidth(this.mContentWidth);
        this.mPopup.setInputMethodMode(2);
        this.mPopup.setEpicenterBounds(getEpicenterBounds());
        this.mPopup.show();
        ListView listView = this.mPopup.getListView();
        listView.setOnKeyListener(this);
        if (this.mShowTitle && this.mMenu.getHeaderTitle() != null) {
            FrameLayout titleItemView = (FrameLayout) LayoutInflater.from(this.mContext).inflate(C0425R.layout.abc_popup_menu_header_item_layout, listView, false);
            TextView titleView = (TextView) titleItemView.findViewById(16908310);
            if (titleView != null) {
                titleView.setText(this.mMenu.getHeaderTitle());
            }
            titleItemView.setEnabled(false);
            listView.addHeaderView(titleItemView, (Object) null, false);
        }
        this.mPopup.setAdapter(this.mAdapter);
        this.mPopup.show();
        return true;
    }

    public void show() {
        Throwable th;
        if (!tryShow()) {
            Throwable th2 = th;
            new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
            throw th2;
        }
    }

    public void dismiss() {
        if (isShowing()) {
            this.mPopup.dismiss();
        }
    }

    public void addMenu(MenuBuilder menu) {
    }

    public boolean isShowing() {
        return !this.mWasDismissed && this.mPopup.isShowing();
    }

    public void onDismiss() {
        this.mWasDismissed = true;
        this.mMenu.close();
        if (this.mTreeObserver != null) {
            if (!this.mTreeObserver.isAlive()) {
                this.mTreeObserver = this.mShownAnchorView.getViewTreeObserver();
            }
            this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
            this.mTreeObserver = null;
        }
        this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss();
        }
    }

    public void updateMenuView(boolean z) {
        boolean z2 = z;
        this.mHasContentWidth = false;
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void setCallback(MenuPresenter.Callback cb) {
        MenuPresenter.Callback callback = cb;
        this.mPresenterCallback = callback;
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        MenuPopupHelper menuPopupHelper;
        SubMenuBuilder subMenu = subMenuBuilder;
        if (subMenu.hasVisibleItems()) {
            new MenuPopupHelper(this.mContext, subMenu, this.mShownAnchorView, this.mOverflowOnly, this.mPopupStyleAttr, this.mPopupStyleRes);
            MenuPopupHelper subPopup = menuPopupHelper;
            subPopup.setPresenterCallback(this.mPresenterCallback);
            subPopup.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(subMenu));
            subPopup.setOnDismissListener(this.mOnDismissListener);
            this.mOnDismissListener = null;
            this.mMenu.close(false);
            int horizontalOffset = this.mPopup.getHorizontalOffset();
            int verticalOffset = this.mPopup.getVerticalOffset();
            if ((Gravity.getAbsoluteGravity(this.mDropDownGravity, ViewCompat.getLayoutDirection(this.mAnchorView)) & 7) == 5) {
                horizontalOffset += this.mAnchorView.getWidth();
            }
            if (subPopup.tryShow(horizontalOffset, verticalOffset)) {
                if (this.mPresenterCallback != null) {
                    boolean onOpenSubMenu = this.mPresenterCallback.onOpenSubMenu(subMenu);
                }
                return true;
            }
        }
        return false;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        MenuBuilder menu = menuBuilder;
        boolean allMenusAreClosing = z;
        if (menu == this.mMenu) {
            dismiss();
            if (this.mPresenterCallback != null) {
                this.mPresenterCallback.onCloseMenu(menu, allMenusAreClosing);
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

    public void setAnchorView(View anchor) {
        View view = anchor;
        this.mAnchorView = view;
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

    public void setOnDismissListener(PopupWindow.OnDismissListener listener) {
        PopupWindow.OnDismissListener onDismissListener = listener;
        this.mOnDismissListener = onDismissListener;
    }

    public ListView getListView() {
        return this.mPopup.getListView();
    }

    public void setHorizontalOffset(int x) {
        this.mPopup.setHorizontalOffset(x);
    }

    public void setVerticalOffset(int y) {
        this.mPopup.setVerticalOffset(y);
    }

    public void setShowTitle(boolean showTitle) {
        boolean z = showTitle;
        this.mShowTitle = z;
    }
}
