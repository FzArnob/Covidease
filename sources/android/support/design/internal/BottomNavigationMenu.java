package android.support.design.internal;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.p003v7.view.menu.MenuBuilder;
import android.support.p003v7.view.menu.MenuItemImpl;
import android.view.MenuItem;
import android.view.SubMenu;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class BottomNavigationMenu extends MenuBuilder {
    public static final int MAX_ITEM_COUNT = 5;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BottomNavigationMenu(Context context) {
        super(context);
    }

    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        Throwable th;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        CharSequence charSequence2 = charSequence;
        Throwable th2 = th;
        new UnsupportedOperationException("BottomNavigationView does not support submenus");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public MenuItem addInternal(int i, int i2, int i3, CharSequence charSequence) {
        Throwable th;
        int group = i;
        int id = i2;
        int categoryOrder = i3;
        CharSequence title = charSequence;
        if (size() + 1 > 5) {
            Throwable th2 = th;
            new IllegalArgumentException("Maximum number of items supported by BottomNavigationView is 5. Limit can be checked with BottomNavigationView#getMaxItemCount()");
            throw th2;
        }
        stopDispatchingItemsChanged();
        MenuItem item = super.addInternal(group, id, categoryOrder, title);
        if (item instanceof MenuItemImpl) {
            ((MenuItemImpl) item).setExclusiveCheckable(true);
        }
        startDispatchingItemsChanged();
        return item;
    }
}
