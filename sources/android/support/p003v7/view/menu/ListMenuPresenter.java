package android.support.p003v7.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.view.menu.MenuPresenter;
import android.support.p003v7.view.menu.MenuView;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.view.menu.ListMenuPresenter */
public class ListMenuPresenter implements MenuPresenter, AdapterView.OnItemClickListener {
    private static final String TAG = "ListMenuPresenter";
    public static final String VIEWS_TAG = "android:menu:list";
    MenuAdapter mAdapter;
    private MenuPresenter.Callback mCallback;
    Context mContext;
    private int mId;
    LayoutInflater mInflater;
    int mItemIndexOffset;
    int mItemLayoutRes;
    MenuBuilder mMenu;
    ExpandedMenuView mMenuView;
    int mThemeRes;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ListMenuPresenter(Context context, int itemLayoutRes) {
        this(itemLayoutRes, 0);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    public ListMenuPresenter(int itemLayoutRes, int themeRes) {
        this.mItemLayoutRes = itemLayoutRes;
        this.mThemeRes = themeRes;
    }

    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        Context context2;
        Context context3 = context;
        MenuBuilder menu = menuBuilder;
        if (this.mThemeRes != 0) {
            new ContextThemeWrapper(context3, this.mThemeRes);
            this.mContext = context2;
            this.mInflater = LayoutInflater.from(this.mContext);
        } else if (this.mContext != null) {
            this.mContext = context3;
            if (this.mInflater == null) {
                this.mInflater = LayoutInflater.from(this.mContext);
            }
        }
        this.mMenu = menu;
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        MenuAdapter menuAdapter;
        ViewGroup root = viewGroup;
        if (this.mMenuView == null) {
            this.mMenuView = (ExpandedMenuView) this.mInflater.inflate(C0425R.layout.abc_expanded_menu_layout, root, false);
            if (this.mAdapter == null) {
                new MenuAdapter(this);
                this.mAdapter = menuAdapter;
            }
            this.mMenuView.setAdapter(this.mAdapter);
            this.mMenuView.setOnItemClickListener(this);
        }
        return this.mMenuView;
    }

    public ListAdapter getAdapter() {
        MenuAdapter menuAdapter;
        if (this.mAdapter == null) {
            new MenuAdapter(this);
            this.mAdapter = menuAdapter;
        }
        return this.mAdapter;
    }

    public void updateMenuView(boolean z) {
        boolean z2 = z;
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void setCallback(MenuPresenter.Callback cb) {
        MenuPresenter.Callback callback = cb;
        this.mCallback = callback;
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        MenuDialogHelper menuDialogHelper;
        SubMenuBuilder subMenu = subMenuBuilder;
        if (!subMenu.hasVisibleItems()) {
            return false;
        }
        new MenuDialogHelper(subMenu);
        menuDialogHelper.show((IBinder) null);
        if (this.mCallback != null) {
            boolean onOpenSubMenu = this.mCallback.onOpenSubMenu(subMenu);
        }
        return true;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        MenuBuilder menu = menuBuilder;
        boolean allMenusAreClosing = z;
        if (this.mCallback != null) {
            this.mCallback.onCloseMenu(menu, allMenusAreClosing);
        }
    }

    /* access modifiers changed from: package-private */
    public int getItemIndexOffset() {
        return this.mItemIndexOffset;
    }

    public void setItemIndexOffset(int offset) {
        this.mItemIndexOffset = offset;
        if (this.mMenuView != null) {
            updateMenuView(false);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long j) {
        AdapterView<?> adapterView2 = adapterView;
        View view2 = view;
        long j2 = j;
        boolean performItemAction = this.mMenu.performItemAction(this.mAdapter.getItem(position), this, 0);
    }

    public boolean flagActionItems() {
        return false;
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

    public void saveHierarchyState(Bundle bundle) {
        SparseArray sparseArray;
        Bundle outState = bundle;
        new SparseArray();
        SparseArray sparseArray2 = sparseArray;
        if (this.mMenuView != null) {
            this.mMenuView.saveHierarchyState(sparseArray2);
        }
        outState.putSparseParcelableArray(VIEWS_TAG, sparseArray2);
    }

    public void restoreHierarchyState(Bundle inState) {
        SparseArray<Parcelable> viewStates = inState.getSparseParcelableArray(VIEWS_TAG);
        if (viewStates != null) {
            this.mMenuView.restoreHierarchyState(viewStates);
        }
    }

    public void setId(int id) {
        int i = id;
        this.mId = i;
    }

    public int getId() {
        return this.mId;
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle;
        if (this.mMenuView == null) {
            return null;
        }
        new Bundle();
        Bundle state = bundle;
        saveHierarchyState(state);
        return state;
    }

    public void onRestoreInstanceState(Parcelable state) {
        restoreHierarchyState((Bundle) state);
    }

    /* renamed from: android.support.v7.view.menu.ListMenuPresenter$MenuAdapter */
    private class MenuAdapter extends BaseAdapter {
        private int mExpandedIndex = -1;
        final /* synthetic */ ListMenuPresenter this$0;

        public MenuAdapter(ListMenuPresenter listMenuPresenter) {
            this.this$0 = listMenuPresenter;
            findExpandedIndex();
        }

        public int getCount() {
            int count = this.this$0.mMenu.getNonActionItems().size() - this.this$0.mItemIndexOffset;
            if (this.mExpandedIndex < 0) {
                return count;
            }
            return count - 1;
        }

        public MenuItemImpl getItem(int position) {
            ArrayList<MenuItemImpl> items = this.this$0.mMenu.getNonActionItems();
            int position2 = position + this.this$0.mItemIndexOffset;
            if (this.mExpandedIndex >= 0 && position2 >= this.mExpandedIndex) {
                position2++;
            }
            return items.get(position2);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int position = i;
            View convertView = view;
            ViewGroup parent = viewGroup;
            if (convertView == null) {
                convertView = this.this$0.mInflater.inflate(this.this$0.mItemLayoutRes, parent, false);
            }
            ((MenuView.ItemView) convertView).initialize(getItem(position), 0);
            return convertView;
        }

        /* access modifiers changed from: package-private */
        public void findExpandedIndex() {
            MenuItemImpl expandedItem = this.this$0.mMenu.getExpandedItem();
            if (expandedItem != null) {
                ArrayList<MenuItemImpl> items = this.this$0.mMenu.getNonActionItems();
                int count = items.size();
                for (int i = 0; i < count; i++) {
                    if (items.get(i) == expandedItem) {
                        this.mExpandedIndex = i;
                        return;
                    }
                }
            }
            this.mExpandedIndex = -1;
        }

        public void notifyDataSetChanged() {
            findExpandedIndex();
            super.notifyDataSetChanged();
        }
    }
}
