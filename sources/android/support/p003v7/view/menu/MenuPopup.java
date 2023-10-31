package android.support.p003v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* renamed from: android.support.v7.view.menu.MenuPopup */
abstract class MenuPopup implements ShowableListMenu, MenuPresenter, AdapterView.OnItemClickListener {
    private Rect mEpicenterBounds;

    public abstract void addMenu(MenuBuilder menuBuilder);

    public abstract void setAnchorView(View view);

    public abstract void setForceShowIcon(boolean z);

    public abstract void setGravity(int i);

    public abstract void setHorizontalOffset(int i);

    public abstract void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener);

    public abstract void setShowTitle(boolean z);

    public abstract void setVerticalOffset(int i);

    MenuPopup() {
    }

    public void setEpicenterBounds(Rect bounds) {
        Rect rect = bounds;
        this.mEpicenterBounds = rect;
    }

    public Rect getEpicenterBounds() {
        return this.mEpicenterBounds;
    }

    public void initForMenu(@NonNull Context context, @Nullable MenuBuilder menu) {
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        Throwable th;
        ViewGroup viewGroup2 = viewGroup;
        Throwable th2 = th;
        new UnsupportedOperationException("MenuPopups manage their own views");
        throw th2;
    }

    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        MenuBuilder menuBuilder2 = menuBuilder;
        MenuItemImpl menuItemImpl2 = menuItemImpl;
        return false;
    }

    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        MenuBuilder menuBuilder2 = menuBuilder;
        MenuItemImpl menuItemImpl2 = menuItemImpl;
        return false;
    }

    public int getId() {
        return 0;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long j) {
        View view2 = view;
        long j2 = j;
        ListAdapter outerAdapter = (ListAdapter) parent.getAdapter();
        boolean performItemAction = toMenuAdapter(outerAdapter).mAdapterMenu.performItemAction((MenuItem) outerAdapter.getItem(position), this, closeMenuOnSubMenuOpened() ? 0 : 4);
    }

    protected static int measureIndividualMenuWidth(ListAdapter listAdapter, ViewGroup viewGroup, Context context, int i) {
        ViewGroup viewGroup2;
        ListAdapter adapter = listAdapter;
        ViewGroup parent = viewGroup;
        Context context2 = context;
        int maxAllowedWidth = i;
        int maxWidth = 0;
        View itemView = null;
        int itemType = 0;
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = adapter.getCount();
        for (int i2 = 0; i2 < count; i2++) {
            int positionType = adapter.getItemViewType(i2);
            if (positionType != itemType) {
                itemType = positionType;
                itemView = null;
            }
            if (parent == null) {
                new FrameLayout(context2);
                parent = viewGroup2;
            }
            itemView = adapter.getView(i2, itemView, parent);
            itemView.measure(widthMeasureSpec, heightMeasureSpec);
            int itemWidth = itemView.getMeasuredWidth();
            if (itemWidth >= maxAllowedWidth) {
                return maxAllowedWidth;
            }
            if (itemWidth > maxWidth) {
                maxWidth = itemWidth;
            }
        }
        return maxWidth;
    }

    protected static MenuAdapter toMenuAdapter(ListAdapter listAdapter) {
        ListAdapter adapter = listAdapter;
        if (adapter instanceof HeaderViewListAdapter) {
            return (MenuAdapter) ((HeaderViewListAdapter) adapter).getWrappedAdapter();
        }
        return (MenuAdapter) adapter;
    }

    protected static boolean shouldPreserveIconSpacing(MenuBuilder menuBuilder) {
        MenuBuilder menu = menuBuilder;
        boolean preserveIconSpacing = false;
        int count = menu.size();
        int i = 0;
        while (true) {
            if (i >= count) {
                break;
            }
            MenuItem childItem = menu.getItem(i);
            if (childItem.isVisible() && childItem.getIcon() != null) {
                preserveIconSpacing = true;
                break;
            }
            i++;
        }
        return preserveIconSpacing;
    }

    /* access modifiers changed from: protected */
    public boolean closeMenuOnSubMenuOpened() {
        return true;
    }
}
