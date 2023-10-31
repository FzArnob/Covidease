package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.design.C0064R;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.WindowInsetsCompat;
import android.support.p003v7.view.menu.MenuBuilder;
import android.support.p003v7.view.menu.MenuItemImpl;
import android.support.p003v7.view.menu.MenuPresenter;
import android.support.p003v7.view.menu.MenuView;
import android.support.p003v7.view.menu.SubMenuBuilder;
import android.support.p003v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class NavigationMenuPresenter implements MenuPresenter {
    private static final String STATE_ADAPTER = "android:menu:adapter";
    private static final String STATE_HEADER = "android:menu:header";
    private static final String STATE_HIERARCHY = "android:menu:list";
    NavigationMenuAdapter adapter;
    private MenuPresenter.Callback callback;
    LinearLayout headerLayout;
    ColorStateList iconTintList;

    /* renamed from: id */
    private int f18id;
    Drawable itemBackground;
    int itemHorizontalPadding;
    int itemIconPadding;
    LayoutInflater layoutInflater;
    MenuBuilder menu;
    private NavigationMenuView menuView;
    final View.OnClickListener onClickListener;
    int paddingSeparator;
    private int paddingTopDefault;
    int textAppearance;
    boolean textAppearanceSet;
    ColorStateList textColor;

    private interface NavigationMenuItem {
    }

    public NavigationMenuPresenter() {
        View.OnClickListener onClickListener2;
        new View.OnClickListener(this) {
            final /* synthetic */ NavigationMenuPresenter this$0;

            {
                this.this$0 = this$0;
            }

            public void onClick(View v) {
                this.this$0.setUpdateSuspended(true);
                MenuItemImpl item = ((NavigationMenuItemView) v).getItemData();
                boolean result = this.this$0.menu.performItemAction(item, this.this$0, 0);
                if (item != null && item.isCheckable() && result) {
                    this.this$0.adapter.setCheckedItem(item);
                }
                this.this$0.setUpdateSuspended(false);
                this.this$0.updateMenuView(false);
            }
        };
        this.onClickListener = onClickListener2;
    }

    public void initForMenu(Context context, MenuBuilder menu2) {
        Context context2 = context;
        this.layoutInflater = LayoutInflater.from(context2);
        this.menu = menu2;
        this.paddingSeparator = context2.getResources().getDimensionPixelOffset(C0064R.dimen.design_navigation_separator_vertical_padding);
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        NavigationMenuAdapter navigationMenuAdapter;
        ViewGroup root = viewGroup;
        if (this.menuView == null) {
            this.menuView = (NavigationMenuView) this.layoutInflater.inflate(C0064R.layout.design_navigation_menu, root, false);
            if (this.adapter == null) {
                new NavigationMenuAdapter(this);
                this.adapter = navigationMenuAdapter;
            }
            this.headerLayout = (LinearLayout) this.layoutInflater.inflate(C0064R.layout.design_navigation_item_header, this.menuView, false);
            this.menuView.setAdapter(this.adapter);
        }
        return this.menuView;
    }

    public void updateMenuView(boolean z) {
        boolean z2 = z;
        if (this.adapter != null) {
            this.adapter.update();
        }
    }

    public void setCallback(MenuPresenter.Callback cb) {
        MenuPresenter.Callback callback2 = cb;
        this.callback = callback2;
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        return false;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        MenuBuilder menu2 = menuBuilder;
        boolean allMenusAreClosing = z;
        if (this.callback != null) {
            this.callback.onCloseMenu(menu2, allMenusAreClosing);
        }
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

    public int getId() {
        return this.f18id;
    }

    public void setId(int id) {
        int i = id;
        this.f18id = i;
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle;
        SparseArray sparseArray;
        SparseArray sparseArray2;
        new Bundle();
        Bundle state = bundle;
        if (this.menuView != null) {
            new SparseArray();
            SparseArray sparseArray3 = sparseArray2;
            this.menuView.saveHierarchyState(sparseArray3);
            state.putSparseParcelableArray("android:menu:list", sparseArray3);
        }
        if (this.adapter != null) {
            state.putBundle(STATE_ADAPTER, this.adapter.createInstanceState());
        }
        if (this.headerLayout != null) {
            new SparseArray();
            SparseArray sparseArray4 = sparseArray;
            this.headerLayout.saveHierarchyState(sparseArray4);
            state.putSparseParcelableArray(STATE_HEADER, sparseArray4);
        }
        return state;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2 = parcelable;
        if (parcelable2 instanceof Bundle) {
            Bundle state = (Bundle) parcelable2;
            SparseArray<Parcelable> hierarchy = state.getSparseParcelableArray("android:menu:list");
            if (hierarchy != null) {
                this.menuView.restoreHierarchyState(hierarchy);
            }
            Bundle adapterState = state.getBundle(STATE_ADAPTER);
            if (adapterState != null) {
                this.adapter.restoreInstanceState(adapterState);
            }
            SparseArray<Parcelable> header = state.getSparseParcelableArray(STATE_HEADER);
            if (header != null) {
                this.headerLayout.restoreHierarchyState(header);
            }
        }
    }

    public void setCheckedItem(@NonNull MenuItemImpl item) {
        this.adapter.setCheckedItem(item);
    }

    @Nullable
    public MenuItemImpl getCheckedItem() {
        return this.adapter.getCheckedItem();
    }

    public View inflateHeaderView(@LayoutRes int res) {
        View view = this.layoutInflater.inflate(res, this.headerLayout, false);
        addHeaderView(view);
        return view;
    }

    public void addHeaderView(@NonNull View view) {
        this.headerLayout.addView(view);
        this.menuView.setPadding(0, 0, 0, this.menuView.getPaddingBottom());
    }

    public void removeHeaderView(@NonNull View view) {
        this.headerLayout.removeView(view);
        if (this.headerLayout.getChildCount() == 0) {
            this.menuView.setPadding(0, this.paddingTopDefault, 0, this.menuView.getPaddingBottom());
        }
    }

    public int getHeaderCount() {
        return this.headerLayout.getChildCount();
    }

    public View getHeaderView(int index) {
        return this.headerLayout.getChildAt(index);
    }

    @Nullable
    public ColorStateList getItemTintList() {
        return this.iconTintList;
    }

    public void setItemIconTintList(@Nullable ColorStateList tint) {
        this.iconTintList = tint;
        updateMenuView(false);
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.textColor;
    }

    public void setItemTextColor(@Nullable ColorStateList textColor2) {
        this.textColor = textColor2;
        updateMenuView(false);
    }

    public void setItemTextAppearance(@StyleRes int resId) {
        this.textAppearance = resId;
        this.textAppearanceSet = true;
        updateMenuView(false);
    }

    @Nullable
    public Drawable getItemBackground() {
        return this.itemBackground;
    }

    public void setItemBackground(@Nullable Drawable itemBackground2) {
        this.itemBackground = itemBackground2;
        updateMenuView(false);
    }

    public int getItemHorizontalPadding() {
        return this.itemHorizontalPadding;
    }

    public void setItemHorizontalPadding(int itemHorizontalPadding2) {
        this.itemHorizontalPadding = itemHorizontalPadding2;
        updateMenuView(false);
    }

    public int getItemIconPadding() {
        return this.itemIconPadding;
    }

    public void setItemIconPadding(int itemIconPadding2) {
        this.itemIconPadding = itemIconPadding2;
        updateMenuView(false);
    }

    public void setUpdateSuspended(boolean z) {
        boolean updateSuspended = z;
        if (this.adapter != null) {
            this.adapter.setUpdateSuspended(updateSuspended);
        }
    }

    public void dispatchApplyWindowInsets(WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat insets = windowInsetsCompat;
        int top = insets.getSystemWindowInsetTop();
        if (this.paddingTopDefault != top) {
            this.paddingTopDefault = top;
            if (this.headerLayout.getChildCount() == 0) {
                this.menuView.setPadding(0, this.paddingTopDefault, 0, this.menuView.getPaddingBottom());
            }
        }
        WindowInsetsCompat dispatchApplyWindowInsets = ViewCompat.dispatchApplyWindowInsets(this.headerLayout, insets);
    }

    private static abstract class ViewHolder extends RecyclerView.ViewHolder {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static class NormalViewHolder extends ViewHolder {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public NormalViewHolder(LayoutInflater inflater, ViewGroup parent, View.OnClickListener listener) {
            super(inflater.inflate(C0064R.layout.design_navigation_item, parent, false));
            this.itemView.setOnClickListener(listener);
        }
    }

    private static class SubheaderViewHolder extends ViewHolder {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SubheaderViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(C0064R.layout.design_navigation_item_subheader, parent, false));
        }
    }

    private static class SeparatorViewHolder extends ViewHolder {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SeparatorViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(C0064R.layout.design_navigation_item_separator, parent, false));
        }
    }

    private static class HeaderViewHolder extends ViewHolder {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class NavigationMenuAdapter extends RecyclerView.Adapter<ViewHolder> {
        private static final String STATE_ACTION_VIEWS = "android:menu:action_views";
        private static final String STATE_CHECKED_ITEM = "android:menu:checked";
        private static final int VIEW_TYPE_HEADER = 3;
        private static final int VIEW_TYPE_NORMAL = 0;
        private static final int VIEW_TYPE_SEPARATOR = 2;
        private static final int VIEW_TYPE_SUBHEADER = 1;
        private MenuItemImpl checkedItem;
        private final ArrayList<NavigationMenuItem> items;
        final /* synthetic */ NavigationMenuPresenter this$0;
        private boolean updateSuspended;

        NavigationMenuAdapter(NavigationMenuPresenter navigationMenuPresenter) {
            ArrayList<NavigationMenuItem> arrayList;
            this.this$0 = navigationMenuPresenter;
            new ArrayList<>();
            this.items = arrayList;
            prepareMenuItems();
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public int getItemCount() {
            return this.items.size();
        }

        public int getItemViewType(int position) {
            Throwable th;
            NavigationMenuItem item = this.items.get(position);
            if (item instanceof NavigationMenuSeparatorItem) {
                return 2;
            }
            if (item instanceof NavigationMenuHeaderItem) {
                return 3;
            }
            if (!(item instanceof NavigationMenuTextItem)) {
                Throwable th2 = th;
                new RuntimeException("Unknown item type.");
                throw th2;
            } else if (((NavigationMenuTextItem) item).getMenuItem().hasSubMenu()) {
                return 1;
            } else {
                return 0;
            }
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            ViewHolder viewHolder;
            ViewHolder viewHolder2;
            ViewHolder viewHolder3;
            ViewHolder viewHolder4;
            ViewGroup parent = viewGroup;
            switch (viewType) {
                case 0:
                    new NormalViewHolder(this.this$0.layoutInflater, parent, this.this$0.onClickListener);
                    return viewHolder4;
                case 1:
                    new SubheaderViewHolder(this.this$0.layoutInflater, parent);
                    return viewHolder3;
                case 2:
                    new SeparatorViewHolder(this.this$0.layoutInflater, parent);
                    return viewHolder2;
                case 3:
                    new HeaderViewHolder(this.this$0.headerLayout);
                    return viewHolder;
                default:
                    return null;
            }
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            ViewHolder holder = viewHolder;
            int position = i;
            switch (getItemViewType(position)) {
                case 0:
                    NavigationMenuItemView itemView = (NavigationMenuItemView) holder.itemView;
                    itemView.setIconTintList(this.this$0.iconTintList);
                    if (this.this$0.textAppearanceSet) {
                        itemView.setTextAppearance(this.this$0.textAppearance);
                    }
                    if (this.this$0.textColor != null) {
                        itemView.setTextColor(this.this$0.textColor);
                    }
                    ViewCompat.setBackground(itemView, this.this$0.itemBackground != null ? this.this$0.itemBackground.getConstantState().newDrawable() : null);
                    NavigationMenuTextItem item = (NavigationMenuTextItem) this.items.get(position);
                    itemView.setNeedsEmptyIcon(item.needsEmptyIcon);
                    itemView.setHorizontalPadding(this.this$0.itemHorizontalPadding);
                    itemView.setIconPadding(this.this$0.itemIconPadding);
                    itemView.initialize(item.getMenuItem(), 0);
                    return;
                case 1:
                    ((TextView) holder.itemView).setText(((NavigationMenuTextItem) this.items.get(position)).getMenuItem().getTitle());
                    return;
                case 2:
                    NavigationMenuSeparatorItem item2 = (NavigationMenuSeparatorItem) this.items.get(position);
                    holder.itemView.setPadding(0, item2.getPaddingTop(), 0, item2.getPaddingBottom());
                    return;
                default:
                    return;
            }
        }

        public void onViewRecycled(ViewHolder viewHolder) {
            ViewHolder holder = viewHolder;
            if (holder instanceof NormalViewHolder) {
                ((NavigationMenuItemView) holder.itemView).recycle();
            }
        }

        public void update() {
            prepareMenuItems();
            notifyDataSetChanged();
        }

        private void prepareMenuItems() {
            Object obj;
            NavigationMenuTextItem navigationMenuTextItem;
            Object obj2;
            Object obj3;
            Object obj4;
            Object obj5;
            if (!this.updateSuspended) {
                this.updateSuspended = true;
                this.items.clear();
                new NavigationMenuHeaderItem();
                boolean add = this.items.add(obj);
                int currentGroupId = -1;
                int currentGroupStart = 0;
                boolean currentGroupHasIcon = false;
                int totalSize = this.this$0.menu.getVisibleItems().size();
                for (int i = 0; i < totalSize; i++) {
                    MenuItemImpl item = this.this$0.menu.getVisibleItems().get(i);
                    if (item.isChecked()) {
                        setCheckedItem(item);
                    }
                    if (item.isCheckable()) {
                        item.setExclusiveCheckable(false);
                    }
                    if (item.hasSubMenu()) {
                        SubMenu subMenu = item.getSubMenu();
                        if (subMenu.hasVisibleItems()) {
                            if (i != 0) {
                                new NavigationMenuSeparatorItem(this.this$0.paddingSeparator, 0);
                                boolean add2 = this.items.add(obj5);
                            }
                            new NavigationMenuTextItem(item);
                            boolean add3 = this.items.add(obj3);
                            boolean subMenuHasIcon = false;
                            int subMenuStart = this.items.size();
                            int size = subMenu.size();
                            for (int j = 0; j < size; j++) {
                                MenuItemImpl subMenuItem = (MenuItemImpl) subMenu.getItem(j);
                                if (subMenuItem.isVisible()) {
                                    if (!subMenuHasIcon && subMenuItem.getIcon() != null) {
                                        subMenuHasIcon = true;
                                    }
                                    if (subMenuItem.isCheckable()) {
                                        subMenuItem.setExclusiveCheckable(false);
                                    }
                                    if (item.isChecked()) {
                                        setCheckedItem(item);
                                    }
                                    new NavigationMenuTextItem(subMenuItem);
                                    boolean add4 = this.items.add(obj4);
                                }
                            }
                            if (subMenuHasIcon) {
                                appendTransparentIconIfMissing(subMenuStart, this.items.size());
                            }
                        }
                    } else {
                        int groupId = item.getGroupId();
                        if (groupId != currentGroupId) {
                            currentGroupStart = this.items.size();
                            currentGroupHasIcon = item.getIcon() != null;
                            if (i != 0) {
                                currentGroupStart++;
                                new NavigationMenuSeparatorItem(this.this$0.paddingSeparator, this.this$0.paddingSeparator);
                                boolean add5 = this.items.add(obj2);
                            }
                        } else if (!currentGroupHasIcon && item.getIcon() != null) {
                            currentGroupHasIcon = true;
                            appendTransparentIconIfMissing(currentGroupStart, this.items.size());
                        }
                        new NavigationMenuTextItem(item);
                        NavigationMenuTextItem textItem = navigationMenuTextItem;
                        textItem.needsEmptyIcon = currentGroupHasIcon;
                        boolean add6 = this.items.add(textItem);
                        currentGroupId = groupId;
                    }
                }
                this.updateSuspended = false;
            }
        }

        private void appendTransparentIconIfMissing(int startIndex, int i) {
            int endIndex = i;
            for (int i2 = startIndex; i2 < endIndex; i2++) {
                ((NavigationMenuTextItem) this.items.get(i2)).needsEmptyIcon = true;
            }
        }

        public void setCheckedItem(MenuItemImpl menuItemImpl) {
            MenuItemImpl checkedItem2 = menuItemImpl;
            if (this.checkedItem != checkedItem2 && checkedItem2.isCheckable()) {
                if (this.checkedItem != null) {
                    MenuItem checked = this.checkedItem.setChecked(false);
                }
                this.checkedItem = checkedItem2;
                MenuItem checked2 = checkedItem2.setChecked(true);
            }
        }

        public MenuItemImpl getCheckedItem() {
            return this.checkedItem;
        }

        public Bundle createInstanceState() {
            Bundle bundle;
            SparseArray sparseArray;
            ParcelableSparseArray parcelableSparseArray;
            new Bundle();
            Bundle state = bundle;
            if (this.checkedItem != null) {
                state.putInt(STATE_CHECKED_ITEM, this.checkedItem.getItemId());
            }
            new SparseArray();
            SparseArray sparseArray2 = sparseArray;
            int size = this.items.size();
            for (int i = 0; i < size; i++) {
                NavigationMenuItem navigationMenuItem = this.items.get(i);
                if (navigationMenuItem instanceof NavigationMenuTextItem) {
                    MenuItemImpl item = ((NavigationMenuTextItem) navigationMenuItem).getMenuItem();
                    View actionView = item != null ? item.getActionView() : null;
                    if (actionView != null) {
                        new ParcelableSparseArray();
                        ParcelableSparseArray container = parcelableSparseArray;
                        actionView.saveHierarchyState(container);
                        sparseArray2.put(item.getItemId(), container);
                    }
                }
            }
            state.putSparseParcelableArray(STATE_ACTION_VIEWS, sparseArray2);
            return state;
        }

        public void restoreInstanceState(Bundle bundle) {
            MenuItemImpl item;
            View actionView;
            ParcelableSparseArray container;
            MenuItemImpl menuItem;
            Bundle state = bundle;
            int checkedItem2 = state.getInt(STATE_CHECKED_ITEM, 0);
            if (checkedItem2 != 0) {
                this.updateSuspended = true;
                int i = 0;
                int size = this.items.size();
                while (true) {
                    if (i >= size) {
                        break;
                    }
                    NavigationMenuItem item2 = this.items.get(i);
                    if ((item2 instanceof NavigationMenuTextItem) && (menuItem = ((NavigationMenuTextItem) item2).getMenuItem()) != null && menuItem.getItemId() == checkedItem2) {
                        setCheckedItem(menuItem);
                        break;
                    }
                    i++;
                }
                this.updateSuspended = false;
                prepareMenuItems();
            }
            SparseArray<ParcelableSparseArray> actionViewStates = state.getSparseParcelableArray(STATE_ACTION_VIEWS);
            if (actionViewStates != null) {
                int size2 = this.items.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    NavigationMenuItem navigationMenuItem = this.items.get(i2);
                    if (!(!(navigationMenuItem instanceof NavigationMenuTextItem) || (item = ((NavigationMenuTextItem) navigationMenuItem).getMenuItem()) == null || (actionView = item.getActionView()) == null || (container = actionViewStates.get(item.getItemId())) == null)) {
                        actionView.restoreHierarchyState(container);
                    }
                }
            }
        }

        public void setUpdateSuspended(boolean updateSuspended2) {
            boolean z = updateSuspended2;
            this.updateSuspended = z;
        }
    }

    private static class NavigationMenuTextItem implements NavigationMenuItem {
        private final MenuItemImpl menuItem;
        boolean needsEmptyIcon;

        NavigationMenuTextItem(MenuItemImpl item) {
            this.menuItem = item;
        }

        public MenuItemImpl getMenuItem() {
            return this.menuItem;
        }
    }

    private static class NavigationMenuSeparatorItem implements NavigationMenuItem {
        private final int paddingBottom;
        private final int paddingTop;

        public NavigationMenuSeparatorItem(int paddingTop2, int paddingBottom2) {
            this.paddingTop = paddingTop2;
            this.paddingBottom = paddingBottom2;
        }

        public int getPaddingTop() {
            return this.paddingTop;
        }

        public int getPaddingBottom() {
            return this.paddingBottom;
        }
    }

    private static class NavigationMenuHeaderItem implements NavigationMenuItem {
        NavigationMenuHeaderItem() {
        }
    }
}
