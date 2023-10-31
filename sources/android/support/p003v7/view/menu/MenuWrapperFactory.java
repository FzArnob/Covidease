package android.support.p003v7.view.menu;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.internal.view.SupportMenuItem;
import android.support.p000v4.internal.view.SupportSubMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.view.menu.MenuWrapperFactory */
public final class MenuWrapperFactory {
    private MenuWrapperFactory() {
    }

    public static Menu wrapSupportMenu(Context context, SupportMenu supportMenu) {
        Menu menu;
        new MenuWrapperICS(context, supportMenu);
        return menu;
    }

    public static MenuItem wrapSupportMenuItem(Context context, SupportMenuItem supportMenuItem) {
        MenuItem menuItem;
        MenuItem menuItem2;
        Context context2 = context;
        SupportMenuItem supportMenuItem2 = supportMenuItem;
        if (Build.VERSION.SDK_INT >= 16) {
            new MenuItemWrapperJB(context2, supportMenuItem2);
            return menuItem2;
        }
        new MenuItemWrapperICS(context2, supportMenuItem2);
        return menuItem;
    }

    public static SubMenu wrapSupportSubMenu(Context context, SupportSubMenu supportSubMenu) {
        SubMenu subMenu;
        new SubMenuWrapperICS(context, supportSubMenu);
        return subMenu;
    }
}
