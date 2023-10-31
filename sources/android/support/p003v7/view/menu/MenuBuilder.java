package android.support.p003v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.view.ActionProvider;
import android.support.p000v4.view.ViewConfigurationCompat;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.view.menu.MenuBuilder */
public class MenuBuilder implements SupportMenu {
    private static final String ACTION_VIEW_STATES_KEY = "android:menu:actionviewstates";
    private static final String EXPANDED_ACTION_VIEW_ID = "android:menu:expandedactionview";
    private static final String PRESENTER_KEY = "android:menu:presenters";
    private static final String TAG = "MenuBuilder";
    private static final int[] sCategoryToOrder = {1, 4, 5, 3, 2, 0};
    private ArrayList<MenuItemImpl> mActionItems;
    private Callback mCallback;
    private final Context mContext;
    private ContextMenu.ContextMenuInfo mCurrentMenuInfo;
    private int mDefaultShowAsAction = 0;
    private MenuItemImpl mExpandedItem;
    private SparseArray<Parcelable> mFrozenViewStates;
    private boolean mGroupDividerEnabled;
    Drawable mHeaderIcon;
    CharSequence mHeaderTitle;
    View mHeaderView;
    private boolean mIsActionItemsStale;
    private boolean mIsClosing = false;
    private boolean mIsVisibleItemsStale;
    private ArrayList<MenuItemImpl> mItems;
    private boolean mItemsChangedWhileDispatchPrevented = false;
    private ArrayList<MenuItemImpl> mNonActionItems;
    private boolean mOptionalIconsVisible = false;
    private boolean mOverrideVisibleItems;
    private CopyOnWriteArrayList<WeakReference<MenuPresenter>> mPresenters;
    private boolean mPreventDispatchingItemsChanged = false;
    private boolean mQwertyMode;
    private final Resources mResources;
    private boolean mShortcutsVisible;
    private boolean mStructureChangedWhileDispatchPrevented = false;
    private ArrayList<MenuItemImpl> mTempShortcutItemList;
    private ArrayList<MenuItemImpl> mVisibleItems;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* renamed from: android.support.v7.view.menu.MenuBuilder$Callback */
    public interface Callback {
        boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem);

        void onMenuModeChange(MenuBuilder menuBuilder);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* renamed from: android.support.v7.view.menu.MenuBuilder$ItemInvoker */
    public interface ItemInvoker {
        boolean invokeItem(MenuItemImpl menuItemImpl);
    }

    public MenuBuilder(Context context) {
        ArrayList<MenuItemImpl> arrayList;
        CopyOnWriteArrayList<WeakReference<MenuPresenter>> copyOnWriteArrayList;
        ArrayList<MenuItemImpl> arrayList2;
        ArrayList<MenuItemImpl> arrayList3;
        ArrayList<MenuItemImpl> arrayList4;
        ArrayList<MenuItemImpl> arrayList5;
        Context context2 = context;
        new ArrayList<>();
        this.mTempShortcutItemList = arrayList;
        new CopyOnWriteArrayList<>();
        this.mPresenters = copyOnWriteArrayList;
        this.mGroupDividerEnabled = false;
        this.mContext = context2;
        this.mResources = context2.getResources();
        new ArrayList<>();
        this.mItems = arrayList2;
        new ArrayList<>();
        this.mVisibleItems = arrayList3;
        this.mIsVisibleItemsStale = true;
        new ArrayList<>();
        this.mActionItems = arrayList4;
        new ArrayList<>();
        this.mNonActionItems = arrayList5;
        this.mIsActionItemsStale = true;
        setShortcutsVisibleInner(true);
    }

    public MenuBuilder setDefaultShowAsAction(int defaultShowAsAction) {
        this.mDefaultShowAsAction = defaultShowAsAction;
        return this;
    }

    public void addMenuPresenter(MenuPresenter presenter) {
        addMenuPresenter(presenter, this.mContext);
    }

    public void addMenuPresenter(MenuPresenter menuPresenter, Context menuContext) {
        Object obj;
        MenuPresenter presenter = menuPresenter;
        new WeakReference(presenter);
        boolean add = this.mPresenters.add(obj);
        presenter.initForMenu(menuContext, this);
        this.mIsActionItemsStale = true;
    }

    public void removeMenuPresenter(MenuPresenter menuPresenter) {
        MenuPresenter presenter = menuPresenter;
        Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
        while (it.hasNext()) {
            WeakReference<MenuPresenter> ref = it.next();
            MenuPresenter item = (MenuPresenter) ref.get();
            if (item == null || item == presenter) {
                boolean remove = this.mPresenters.remove(ref);
            }
        }
    }

    private void dispatchPresenterUpdate(boolean z) {
        boolean cleared = z;
        if (!this.mPresenters.isEmpty()) {
            stopDispatchingItemsChanged();
            Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
            while (it.hasNext()) {
                WeakReference<MenuPresenter> ref = it.next();
                MenuPresenter presenter = (MenuPresenter) ref.get();
                if (presenter == null) {
                    boolean remove = this.mPresenters.remove(ref);
                } else {
                    presenter.updateMenuView(cleared);
                }
            }
            startDispatchingItemsChanged();
        }
    }

    private boolean dispatchSubMenuSelected(SubMenuBuilder subMenuBuilder, MenuPresenter menuPresenter) {
        SubMenuBuilder subMenu = subMenuBuilder;
        MenuPresenter preferredPresenter = menuPresenter;
        if (this.mPresenters.isEmpty()) {
            return false;
        }
        boolean result = false;
        if (preferredPresenter != null) {
            result = preferredPresenter.onSubMenuSelected(subMenu);
        }
        Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
        while (it.hasNext()) {
            WeakReference<MenuPresenter> ref = it.next();
            MenuPresenter presenter = (MenuPresenter) ref.get();
            if (presenter == null) {
                boolean remove = this.mPresenters.remove(ref);
            } else if (!result) {
                result = presenter.onSubMenuSelected(subMenu);
            }
        }
        return result;
    }

    private void dispatchSaveInstanceState(Bundle bundle) {
        SparseArray sparseArray;
        Parcelable state;
        Bundle outState = bundle;
        if (!this.mPresenters.isEmpty()) {
            new SparseArray();
            SparseArray sparseArray2 = sparseArray;
            Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
            while (it.hasNext()) {
                WeakReference<MenuPresenter> ref = it.next();
                MenuPresenter presenter = (MenuPresenter) ref.get();
                if (presenter == null) {
                    boolean remove = this.mPresenters.remove(ref);
                } else {
                    int id = presenter.getId();
                    if (id > 0 && (state = presenter.onSaveInstanceState()) != null) {
                        sparseArray2.put(id, state);
                    }
                }
            }
            outState.putSparseParcelableArray(PRESENTER_KEY, sparseArray2);
        }
    }

    private void dispatchRestoreInstanceState(Bundle state) {
        Parcelable parcel;
        SparseArray<Parcelable> presenterStates = state.getSparseParcelableArray(PRESENTER_KEY);
        if (presenterStates != null && !this.mPresenters.isEmpty()) {
            Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
            while (it.hasNext()) {
                WeakReference<MenuPresenter> ref = it.next();
                MenuPresenter presenter = (MenuPresenter) ref.get();
                if (presenter == null) {
                    boolean remove = this.mPresenters.remove(ref);
                } else {
                    int id = presenter.getId();
                    if (id > 0 && (parcel = presenterStates.get(id)) != null) {
                        presenter.onRestoreInstanceState(parcel);
                    }
                }
            }
        }
    }

    public void savePresenterStates(Bundle outState) {
        dispatchSaveInstanceState(outState);
    }

    public void restorePresenterStates(Bundle state) {
        dispatchRestoreInstanceState(state);
    }

    public void saveActionViewStates(Bundle bundle) {
        SparseArray sparseArray;
        Bundle outStates = bundle;
        SparseArray sparseArray2 = null;
        int itemCount = size();
        for (int i = 0; i < itemCount; i++) {
            MenuItem item = getItem(i);
            View v = item.getActionView();
            if (!(v == null || v.getId() == -1)) {
                if (sparseArray2 == null) {
                    new SparseArray();
                    sparseArray2 = sparseArray;
                }
                v.saveHierarchyState(sparseArray2);
                if (item.isActionViewExpanded()) {
                    outStates.putInt(EXPANDED_ACTION_VIEW_ID, item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((SubMenuBuilder) item.getSubMenu()).saveActionViewStates(outStates);
            }
        }
        if (sparseArray2 != null) {
            outStates.putSparseParcelableArray(getActionViewStatesKey(), sparseArray2);
        }
    }

    public void restoreActionViewStates(Bundle bundle) {
        MenuItem itemToExpand;
        Bundle states = bundle;
        if (states != null) {
            SparseArray sparseParcelableArray = states.getSparseParcelableArray(getActionViewStatesKey());
            int itemCount = size();
            for (int i = 0; i < itemCount; i++) {
                MenuItem item = getItem(i);
                View v = item.getActionView();
                if (!(v == null || v.getId() == -1)) {
                    v.restoreHierarchyState(sparseParcelableArray);
                }
                if (item.hasSubMenu()) {
                    ((SubMenuBuilder) item.getSubMenu()).restoreActionViewStates(states);
                }
            }
            int i2 = states.getInt(EXPANDED_ACTION_VIEW_ID);
            if (i2 > 0 && (itemToExpand = findItem(i2)) != null) {
                boolean expandActionView = itemToExpand.expandActionView();
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getActionViewStatesKey() {
        return ACTION_VIEW_STATES_KEY;
    }

    public void setCallback(Callback cb) {
        Callback callback = cb;
        this.mCallback = callback;
    }

    /* access modifiers changed from: protected */
    public MenuItem addInternal(int group, int id, int i, CharSequence title) {
        int categoryOrder = i;
        int ordering = getOrdering(categoryOrder);
        MenuItemImpl item = createNewMenuItem(group, id, categoryOrder, ordering, title, this.mDefaultShowAsAction);
        if (this.mCurrentMenuInfo != null) {
            item.setMenuInfo(this.mCurrentMenuInfo);
        }
        this.mItems.add(findInsertIndex(this.mItems, ordering), item);
        onItemsChanged(true);
        return item;
    }

    private MenuItemImpl createNewMenuItem(int group, int id, int categoryOrder, int ordering, CharSequence title, int defaultShowAsAction) {
        MenuItemImpl menuItemImpl;
        new MenuItemImpl(this, group, id, categoryOrder, ordering, title, defaultShowAsAction);
        return menuItemImpl;
    }

    public MenuItem add(CharSequence title) {
        return addInternal(0, 0, 0, title);
    }

    public MenuItem add(int titleRes) {
        return addInternal(0, 0, 0, this.mResources.getString(titleRes));
    }

    public MenuItem add(int group, int id, int categoryOrder, CharSequence title) {
        return addInternal(group, id, categoryOrder, title);
    }

    public MenuItem add(int group, int id, int categoryOrder, int title) {
        return addInternal(group, id, categoryOrder, this.mResources.getString(title));
    }

    public SubMenu addSubMenu(CharSequence title) {
        return addSubMenu(0, 0, 0, title);
    }

    public SubMenu addSubMenu(int titleRes) {
        return addSubMenu(0, 0, 0, (CharSequence) this.mResources.getString(titleRes));
    }

    public SubMenu addSubMenu(int group, int id, int categoryOrder, CharSequence title) {
        SubMenuBuilder subMenuBuilder;
        MenuItemImpl item = (MenuItemImpl) addInternal(group, id, categoryOrder, title);
        new SubMenuBuilder(this.mContext, this, item);
        SubMenuBuilder subMenu = subMenuBuilder;
        item.setSubMenu(subMenu);
        return subMenu;
    }

    public SubMenu addSubMenu(int group, int id, int categoryOrder, int title) {
        return addSubMenu(group, id, categoryOrder, (CharSequence) this.mResources.getString(title));
    }

    public void setGroupDividerEnabled(boolean enabled) {
        boolean z = enabled;
        this.mGroupDividerEnabled = z;
    }

    public boolean isGroupDividerEnabled() {
        return this.mGroupDividerEnabled;
    }

    public int addIntentOptions(int i, int i2, int i3, ComponentName caller, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        Intent intent2;
        ComponentName componentName;
        int group = i;
        int id = i2;
        int categoryOrder = i3;
        Intent[] specifics = intentArr;
        Intent intent3 = intent;
        int flags = i4;
        MenuItem[] outSpecificItems = menuItemArr;
        PackageManager pm = this.mContext.getPackageManager();
        List<ResolveInfo> lri = pm.queryIntentActivityOptions(caller, specifics, intent3, 0);
        int N = lri != null ? lri.size() : 0;
        if ((flags & 1) == 0) {
            removeGroup(group);
        }
        for (int i5 = 0; i5 < N; i5++) {
            ResolveInfo ri = lri.get(i5);
            Intent intent4 = r24;
            if (ri.specificIndex < 0) {
                intent2 = intent3;
            } else {
                intent2 = specifics[ri.specificIndex];
            }
            Intent intent5 = new Intent(intent2);
            Intent rintent = intent4;
            new ComponentName(ri.activityInfo.applicationInfo.packageName, ri.activityInfo.name);
            Intent component = rintent.setComponent(componentName);
            MenuItem item = add(group, id, categoryOrder, ri.loadLabel(pm)).setIcon(ri.loadIcon(pm)).setIntent(rintent);
            if (outSpecificItems != null && ri.specificIndex >= 0) {
                outSpecificItems[ri.specificIndex] = item;
            }
        }
        return N;
    }

    public void removeItem(int id) {
        removeItemAtInt(findItemIndex(id), true);
    }

    public void removeGroup(int i) {
        int group = i;
        int i2 = findGroupIndex(group);
        if (i2 >= 0) {
            int maxRemovable = this.mItems.size() - i2;
            int numRemoved = 0;
            while (true) {
                int i3 = numRemoved;
                numRemoved++;
                if (i3 >= maxRemovable || this.mItems.get(i2).getGroupId() != group) {
                    onItemsChanged(true);
                } else {
                    removeItemAtInt(i2, false);
                }
            }
            onItemsChanged(true);
        }
    }

    private void removeItemAtInt(int i, boolean z) {
        int index = i;
        boolean updateChildrenOnMenuViews = z;
        if (index >= 0 && index < this.mItems.size()) {
            MenuItemImpl remove = this.mItems.remove(index);
            if (updateChildrenOnMenuViews) {
                onItemsChanged(true);
            }
        }
    }

    public void removeItemAt(int index) {
        removeItemAtInt(index, true);
    }

    public void clearAll() {
        this.mPreventDispatchingItemsChanged = true;
        clear();
        clearHeader();
        this.mPresenters.clear();
        this.mPreventDispatchingItemsChanged = false;
        this.mItemsChangedWhileDispatchPrevented = false;
        this.mStructureChangedWhileDispatchPrevented = false;
        onItemsChanged(true);
    }

    public void clear() {
        if (this.mExpandedItem != null) {
            boolean collapseItemActionView = collapseItemActionView(this.mExpandedItem);
        }
        this.mItems.clear();
        onItemsChanged(true);
    }

    /* access modifiers changed from: package-private */
    public void setExclusiveItemChecked(MenuItem menuItem) {
        MenuItem item = menuItem;
        int group = item.getGroupId();
        int N = this.mItems.size();
        stopDispatchingItemsChanged();
        for (int i = 0; i < N; i++) {
            MenuItemImpl curItem = this.mItems.get(i);
            if (curItem.getGroupId() == group && curItem.isExclusiveCheckable() && curItem.isCheckable()) {
                curItem.setCheckedInt(curItem == item);
            }
        }
        startDispatchingItemsChanged();
    }

    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int group = i;
        boolean checkable = z;
        boolean exclusive = z2;
        int N = this.mItems.size();
        for (int i2 = 0; i2 < N; i2++) {
            MenuItemImpl item = this.mItems.get(i2);
            if (item.getGroupId() == group) {
                item.setExclusiveCheckable(exclusive);
                MenuItem checkable2 = item.setCheckable(checkable);
            }
        }
    }

    public void setGroupVisible(int i, boolean z) {
        int group = i;
        boolean visible = z;
        int N = this.mItems.size();
        boolean changedAtLeastOneItem = false;
        for (int i2 = 0; i2 < N; i2++) {
            MenuItemImpl item = this.mItems.get(i2);
            if (item.getGroupId() == group && item.setVisibleInt(visible)) {
                changedAtLeastOneItem = true;
            }
        }
        if (changedAtLeastOneItem) {
            onItemsChanged(true);
        }
    }

    public void setGroupEnabled(int i, boolean z) {
        int group = i;
        boolean enabled = z;
        int N = this.mItems.size();
        for (int i2 = 0; i2 < N; i2++) {
            MenuItemImpl item = this.mItems.get(i2);
            if (item.getGroupId() == group) {
                MenuItem enabled2 = item.setEnabled(enabled);
            }
        }
    }

    public boolean hasVisibleItems() {
        if (this.mOverrideVisibleItems) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.mItems.get(i).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public MenuItem findItem(int i) {
        MenuItem possibleItem;
        int id = i;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl item = this.mItems.get(i2);
            if (item.getItemId() == id) {
                return item;
            }
            if (item.hasSubMenu() && (possibleItem = item.getSubMenu().findItem(id)) != null) {
                return possibleItem;
            }
        }
        return null;
    }

    public int findItemIndex(int i) {
        int id = i;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.mItems.get(i2).getItemId() == id) {
                return i2;
            }
        }
        return -1;
    }

    public int findGroupIndex(int group) {
        return findGroupIndex(group, 0);
    }

    public int findGroupIndex(int i, int i2) {
        int group = i;
        int start = i2;
        int size = size();
        if (start < 0) {
            start = 0;
        }
        for (int i3 = start; i3 < size; i3++) {
            if (this.mItems.get(i3).getGroupId() == group) {
                return i3;
            }
        }
        return -1;
    }

    public int size() {
        return this.mItems.size();
    }

    public MenuItem getItem(int index) {
        return this.mItems.get(index);
    }

    public boolean isShortcutKey(int keyCode, KeyEvent event) {
        return findItemWithShortcutForKey(keyCode, event) != null;
    }

    public void setQwertyMode(boolean isQwerty) {
        this.mQwertyMode = isQwerty;
        onItemsChanged(false);
    }

    private static int getOrdering(int i) {
        Throwable th;
        int categoryOrder = i;
        int index = (categoryOrder & SupportMenu.CATEGORY_MASK) >> 16;
        if (index >= 0 && index < sCategoryToOrder.length) {
            return (sCategoryToOrder[index] << 16) | (categoryOrder & 65535);
        }
        Throwable th2 = th;
        new IllegalArgumentException("order does not contain a valid category.");
        throw th2;
    }

    /* access modifiers changed from: package-private */
    public boolean isQwertyMode() {
        return this.mQwertyMode;
    }

    public void setShortcutsVisible(boolean z) {
        boolean shortcutsVisible = z;
        if (this.mShortcutsVisible != shortcutsVisible) {
            setShortcutsVisibleInner(shortcutsVisible);
            onItemsChanged(false);
        }
    }

    private void setShortcutsVisibleInner(boolean shortcutsVisible) {
        this.mShortcutsVisible = shortcutsVisible && this.mResources.getConfiguration().keyboard != 1 && ViewConfigurationCompat.shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration.get(this.mContext), this.mContext);
    }

    public boolean isShortcutsVisible() {
        return this.mShortcutsVisible;
    }

    /* access modifiers changed from: package-private */
    public Resources getResources() {
        return this.mResources;
    }

    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: package-private */
    public boolean dispatchMenuItemSelected(MenuBuilder menu, MenuItem item) {
        return this.mCallback != null && this.mCallback.onMenuItemSelected(menu, item);
    }

    public void changeMenuMode() {
        if (this.mCallback != null) {
            this.mCallback.onMenuModeChange(this);
        }
    }

    private static int findInsertIndex(ArrayList<MenuItemImpl> arrayList, int i) {
        ArrayList<MenuItemImpl> items = arrayList;
        int ordering = i;
        for (int i2 = items.size() - 1; i2 >= 0; i2--) {
            if (items.get(i2).getOrdering() <= ordering) {
                return i2 + 1;
            }
        }
        return 0;
    }

    public boolean performShortcut(int keyCode, KeyEvent event, int i) {
        int flags = i;
        MenuItemImpl item = findItemWithShortcutForKey(keyCode, event);
        boolean handled = false;
        if (item != null) {
            handled = performItemAction(item, flags);
        }
        if ((flags & 2) != 0) {
            close(true);
        }
        return handled;
    }

    /* access modifiers changed from: package-private */
    public void findItemsWithShortcutForKey(List<MenuItemImpl> list, int i, KeyEvent keyEvent) {
        KeyCharacterMap.KeyData keyData;
        List<MenuItemImpl> items = list;
        int keyCode = i;
        KeyEvent event = keyEvent;
        boolean qwerty = isQwertyMode();
        int modifierState = event.getModifiers();
        new KeyCharacterMap.KeyData();
        KeyCharacterMap.KeyData possibleChars = keyData;
        if (event.getKeyData(possibleChars) || keyCode == 67) {
            int N = this.mItems.size();
            for (int i2 = 0; i2 < N; i2++) {
                MenuItemImpl item = this.mItems.get(i2);
                if (item.hasSubMenu()) {
                    ((MenuBuilder) item.getSubMenu()).findItemsWithShortcutForKey(items, keyCode, event);
                }
                char shortcutChar = qwerty ? item.getAlphabeticShortcut() : item.getNumericShortcut();
                if (((modifierState & SupportMenu.SUPPORTED_MODIFIERS_MASK) == ((qwerty ? item.getAlphabeticModifiers() : item.getNumericModifiers()) & SupportMenu.SUPPORTED_MODIFIERS_MASK)) && shortcutChar != 0 && ((shortcutChar == possibleChars.meta[0] || shortcutChar == possibleChars.meta[2] || (qwerty && shortcutChar == 8 && keyCode == 67)) && item.isEnabled())) {
                    boolean add = items.add(item);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public MenuItemImpl findItemWithShortcutForKey(int i, KeyEvent keyEvent) {
        KeyCharacterMap.KeyData keyData;
        char numericShortcut;
        int keyCode = i;
        KeyEvent event = keyEvent;
        ArrayList<MenuItemImpl> items = this.mTempShortcutItemList;
        items.clear();
        findItemsWithShortcutForKey(items, keyCode, event);
        if (items.isEmpty()) {
            return null;
        }
        int metaState = event.getMetaState();
        new KeyCharacterMap.KeyData();
        KeyCharacterMap.KeyData possibleChars = keyData;
        boolean keyData2 = event.getKeyData(possibleChars);
        int size = items.size();
        if (size == 1) {
            return items.get(0);
        }
        boolean qwerty = isQwertyMode();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl item = items.get(i2);
            if (qwerty) {
                numericShortcut = item.getAlphabeticShortcut();
            } else {
                numericShortcut = item.getNumericShortcut();
            }
            char shortcutChar = numericShortcut;
            if ((shortcutChar == possibleChars.meta[0] && (metaState & 2) == 0) || ((shortcutChar == possibleChars.meta[2] && (metaState & 2) != 0) || (qwerty && shortcutChar == 8 && keyCode == 67))) {
                return item;
            }
        }
        return null;
    }

    public boolean performIdentifierAction(int id, int flags) {
        return performItemAction(findItem(id), flags);
    }

    public boolean performItemAction(MenuItem item, int flags) {
        return performItemAction(item, (MenuPresenter) null, flags);
    }

    public boolean performItemAction(MenuItem item, MenuPresenter menuPresenter, int i) {
        SubMenuBuilder subMenuBuilder;
        MenuPresenter preferredPresenter = menuPresenter;
        int flags = i;
        MenuItemImpl itemImpl = (MenuItemImpl) item;
        if (itemImpl == null || !itemImpl.isEnabled()) {
            return false;
        }
        boolean invoked = itemImpl.invoke();
        ActionProvider provider = itemImpl.getSupportActionProvider();
        boolean providerHasSubMenu = provider != null && provider.hasSubMenu();
        if (itemImpl.hasCollapsibleActionView()) {
            invoked |= itemImpl.expandActionView();
            if (invoked) {
                close(true);
            }
        } else if (itemImpl.hasSubMenu() || providerHasSubMenu) {
            if ((flags & 4) == 0) {
                close(false);
            }
            if (!itemImpl.hasSubMenu()) {
                new SubMenuBuilder(getContext(), this, itemImpl);
                itemImpl.setSubMenu(subMenuBuilder);
            }
            SubMenuBuilder subMenu = (SubMenuBuilder) itemImpl.getSubMenu();
            if (providerHasSubMenu) {
                provider.onPrepareSubMenu(subMenu);
            }
            invoked |= dispatchSubMenuSelected(subMenu, preferredPresenter);
            if (!invoked) {
                close(true);
            }
        } else if ((flags & 1) == 0) {
            close(true);
        }
        return invoked;
    }

    public final void close(boolean z) {
        boolean closeAllMenus = z;
        if (!this.mIsClosing) {
            this.mIsClosing = true;
            Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
            while (it.hasNext()) {
                WeakReference<MenuPresenter> ref = it.next();
                MenuPresenter presenter = (MenuPresenter) ref.get();
                if (presenter == null) {
                    boolean remove = this.mPresenters.remove(ref);
                } else {
                    presenter.onCloseMenu(this, closeAllMenus);
                }
            }
            this.mIsClosing = false;
        }
    }

    public void close() {
        close(true);
    }

    public void onItemsChanged(boolean z) {
        boolean structureChanged = z;
        if (!this.mPreventDispatchingItemsChanged) {
            if (structureChanged) {
                this.mIsVisibleItemsStale = true;
                this.mIsActionItemsStale = true;
            }
            dispatchPresenterUpdate(structureChanged);
            return;
        }
        this.mItemsChangedWhileDispatchPrevented = true;
        if (structureChanged) {
            this.mStructureChangedWhileDispatchPrevented = true;
        }
    }

    public void stopDispatchingItemsChanged() {
        if (!this.mPreventDispatchingItemsChanged) {
            this.mPreventDispatchingItemsChanged = true;
            this.mItemsChangedWhileDispatchPrevented = false;
            this.mStructureChangedWhileDispatchPrevented = false;
        }
    }

    public void startDispatchingItemsChanged() {
        this.mPreventDispatchingItemsChanged = false;
        if (this.mItemsChangedWhileDispatchPrevented) {
            this.mItemsChangedWhileDispatchPrevented = false;
            onItemsChanged(this.mStructureChangedWhileDispatchPrevented);
        }
    }

    /* access modifiers changed from: package-private */
    public void onItemVisibleChanged(MenuItemImpl menuItemImpl) {
        MenuItemImpl menuItemImpl2 = menuItemImpl;
        this.mIsVisibleItemsStale = true;
        onItemsChanged(true);
    }

    /* access modifiers changed from: package-private */
    public void onItemActionRequestChanged(MenuItemImpl menuItemImpl) {
        MenuItemImpl menuItemImpl2 = menuItemImpl;
        this.mIsActionItemsStale = true;
        onItemsChanged(true);
    }

    @NonNull
    public ArrayList<MenuItemImpl> getVisibleItems() {
        if (!this.mIsVisibleItemsStale) {
            return this.mVisibleItems;
        }
        this.mVisibleItems.clear();
        int itemsSize = this.mItems.size();
        for (int i = 0; i < itemsSize; i++) {
            MenuItemImpl item = this.mItems.get(i);
            if (item.isVisible()) {
                boolean add = this.mVisibleItems.add(item);
            }
        }
        this.mIsVisibleItemsStale = false;
        this.mIsActionItemsStale = true;
        return this.mVisibleItems;
    }

    public void flagActionItems() {
        ArrayList<MenuItemImpl> visibleItems = getVisibleItems();
        if (this.mIsActionItemsStale) {
            boolean flagged = false;
            Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
            while (it.hasNext()) {
                WeakReference<MenuPresenter> ref = it.next();
                MenuPresenter presenter = (MenuPresenter) ref.get();
                if (presenter == null) {
                    boolean remove = this.mPresenters.remove(ref);
                } else {
                    flagged |= presenter.flagActionItems();
                }
            }
            if (flagged) {
                this.mActionItems.clear();
                this.mNonActionItems.clear();
                int itemsSize = visibleItems.size();
                for (int i = 0; i < itemsSize; i++) {
                    MenuItemImpl item = visibleItems.get(i);
                    if (item.isActionButton()) {
                        boolean add = this.mActionItems.add(item);
                    } else {
                        boolean add2 = this.mNonActionItems.add(item);
                    }
                }
            } else {
                this.mActionItems.clear();
                this.mNonActionItems.clear();
                boolean addAll = this.mNonActionItems.addAll(getVisibleItems());
            }
            this.mIsActionItemsStale = false;
        }
    }

    public ArrayList<MenuItemImpl> getActionItems() {
        flagActionItems();
        return this.mActionItems;
    }

    public ArrayList<MenuItemImpl> getNonActionItems() {
        flagActionItems();
        return this.mNonActionItems;
    }

    public void clearHeader() {
        this.mHeaderIcon = null;
        this.mHeaderTitle = null;
        this.mHeaderView = null;
        onItemsChanged(false);
    }

    private void setHeaderInternal(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        int titleRes = i;
        CharSequence title = charSequence;
        int iconRes = i2;
        Drawable icon = drawable;
        View view2 = view;
        Resources r = getResources();
        if (view2 != null) {
            this.mHeaderView = view2;
            this.mHeaderTitle = null;
            this.mHeaderIcon = null;
        } else {
            if (titleRes > 0) {
                this.mHeaderTitle = r.getText(titleRes);
            } else if (title != null) {
                this.mHeaderTitle = title;
            }
            if (iconRes > 0) {
                this.mHeaderIcon = ContextCompat.getDrawable(getContext(), iconRes);
            } else if (icon != null) {
                this.mHeaderIcon = icon;
            }
            this.mHeaderView = null;
        }
        onItemsChanged(false);
    }

    /* access modifiers changed from: protected */
    public MenuBuilder setHeaderTitleInt(CharSequence title) {
        setHeaderInternal(0, title, 0, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder setHeaderTitleInt(int titleRes) {
        setHeaderInternal(titleRes, (CharSequence) null, 0, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder setHeaderIconInt(Drawable icon) {
        setHeaderInternal(0, (CharSequence) null, 0, icon, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder setHeaderIconInt(int iconRes) {
        setHeaderInternal(0, (CharSequence) null, iconRes, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder setHeaderViewInt(View view) {
        setHeaderInternal(0, (CharSequence) null, 0, (Drawable) null, view);
        return this;
    }

    public CharSequence getHeaderTitle() {
        return this.mHeaderTitle;
    }

    public Drawable getHeaderIcon() {
        return this.mHeaderIcon;
    }

    public View getHeaderView() {
        return this.mHeaderView;
    }

    public MenuBuilder getRootMenu() {
        return this;
    }

    public void setCurrentMenuInfo(ContextMenu.ContextMenuInfo menuInfo) {
        ContextMenu.ContextMenuInfo contextMenuInfo = menuInfo;
        this.mCurrentMenuInfo = contextMenuInfo;
    }

    public void setOptionalIconsVisible(boolean visible) {
        boolean z = visible;
        this.mOptionalIconsVisible = z;
    }

    /* access modifiers changed from: package-private */
    public boolean getOptionalIconsVisible() {
        return this.mOptionalIconsVisible;
    }

    public boolean expandItemActionView(MenuItemImpl menuItemImpl) {
        MenuItemImpl item = menuItemImpl;
        if (this.mPresenters.isEmpty()) {
            return false;
        }
        boolean expanded = false;
        stopDispatchingItemsChanged();
        Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
        while (it.hasNext()) {
            WeakReference<MenuPresenter> ref = it.next();
            MenuPresenter presenter = (MenuPresenter) ref.get();
            if (presenter == null) {
                boolean remove = this.mPresenters.remove(ref);
            } else {
                boolean expandItemActionView = presenter.expandItemActionView(this, item);
                expanded = expandItemActionView;
                if (expandItemActionView) {
                    break;
                }
            }
        }
        startDispatchingItemsChanged();
        if (expanded) {
            this.mExpandedItem = item;
        }
        return expanded;
    }

    public boolean collapseItemActionView(MenuItemImpl menuItemImpl) {
        MenuItemImpl item = menuItemImpl;
        if (this.mPresenters.isEmpty() || this.mExpandedItem != item) {
            return false;
        }
        boolean collapsed = false;
        stopDispatchingItemsChanged();
        Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
        while (it.hasNext()) {
            WeakReference<MenuPresenter> ref = it.next();
            MenuPresenter presenter = (MenuPresenter) ref.get();
            if (presenter == null) {
                boolean remove = this.mPresenters.remove(ref);
            } else {
                boolean collapseItemActionView = presenter.collapseItemActionView(this, item);
                collapsed = collapseItemActionView;
                if (collapseItemActionView) {
                    break;
                }
            }
        }
        startDispatchingItemsChanged();
        if (collapsed) {
            this.mExpandedItem = null;
        }
        return collapsed;
    }

    public MenuItemImpl getExpandedItem() {
        return this.mExpandedItem;
    }

    public void setOverrideVisibleItems(boolean override) {
        boolean z = override;
        this.mOverrideVisibleItems = z;
    }
}
