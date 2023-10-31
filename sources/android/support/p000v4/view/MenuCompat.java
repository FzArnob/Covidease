package android.support.p000v4.view;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.p000v4.internal.view.SupportMenu;
import android.view.Menu;
import android.view.MenuItem;

/* renamed from: android.support.v4.view.MenuCompat */
public final class MenuCompat {
    @Deprecated
    public static void setShowAsAction(MenuItem item, int actionEnum) {
        item.setShowAsAction(actionEnum);
    }

    @SuppressLint({"NewApi"})
    public static void setGroupDividerEnabled(Menu menu, boolean z) {
        Menu menu2 = menu;
        boolean enabled = z;
        if (menu2 instanceof SupportMenu) {
            ((SupportMenu) menu2).setGroupDividerEnabled(enabled);
        } else if (Build.VERSION.SDK_INT >= 28) {
            menu2.setGroupDividerEnabled(enabled);
        }
    }

    private MenuCompat() {
    }
}
