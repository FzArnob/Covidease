package android.support.design.internal;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.p003v7.view.menu.MenuBuilder;
import android.support.p003v7.view.menu.MenuItemImpl;
import android.support.p003v7.view.menu.SubMenuBuilder;
import android.view.SubMenu;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class NavigationMenu extends MenuBuilder {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NavigationMenu(Context context) {
        super(context);
    }

    public SubMenu addSubMenu(int group, int id, int categoryOrder, CharSequence title) {
        SubMenuBuilder subMenuBuilder;
        MenuItemImpl item = (MenuItemImpl) addInternal(group, id, categoryOrder, title);
        new NavigationSubMenu(getContext(), this, item);
        SubMenuBuilder subMenu = subMenuBuilder;
        item.setSubMenu(subMenu);
        return subMenu;
    }
}
