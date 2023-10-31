package android.support.p003v7.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.support.p003v7.app.AlertDialog;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.view.menu.MenuPresenter;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/* renamed from: android.support.v7.view.menu.MenuDialogHelper */
class MenuDialogHelper implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, MenuPresenter.Callback {
    private AlertDialog mDialog;
    private MenuBuilder mMenu;
    ListMenuPresenter mPresenter;
    private MenuPresenter.Callback mPresenterCallback;

    public MenuDialogHelper(MenuBuilder menu) {
        this.mMenu = menu;
    }

    public void show(IBinder iBinder) {
        AlertDialog.Builder builder;
        ListMenuPresenter listMenuPresenter;
        IBinder windowToken = iBinder;
        MenuBuilder menu = this.mMenu;
        new AlertDialog.Builder(menu.getContext());
        AlertDialog.Builder builder2 = builder;
        new ListMenuPresenter(builder2.getContext(), C0425R.layout.abc_list_menu_item_layout);
        this.mPresenter = listMenuPresenter;
        this.mPresenter.setCallback(this);
        this.mMenu.addMenuPresenter(this.mPresenter);
        AlertDialog.Builder adapter = builder2.setAdapter(this.mPresenter.getAdapter(), this);
        View headerView = menu.getHeaderView();
        if (headerView != null) {
            AlertDialog.Builder customTitle = builder2.setCustomTitle(headerView);
        } else {
            AlertDialog.Builder title = builder2.setIcon(menu.getHeaderIcon()).setTitle(menu.getHeaderTitle());
        }
        AlertDialog.Builder onKeyListener = builder2.setOnKeyListener(this);
        this.mDialog = builder2.create();
        this.mDialog.setOnDismissListener(this);
        WindowManager.LayoutParams lp = this.mDialog.getWindow().getAttributes();
        lp.type = 1003;
        if (windowToken != null) {
            lp.token = windowToken;
        }
        lp.flags |= 131072;
        this.mDialog.show();
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window win;
        View decor;
        KeyEvent.DispatcherState ds;
        View decor2;
        KeyEvent.DispatcherState ds2;
        DialogInterface dialog = dialogInterface;
        int keyCode = i;
        KeyEvent event = keyEvent;
        if (keyCode == 82 || keyCode == 4) {
            if (event.getAction() == 0 && event.getRepeatCount() == 0) {
                Window win2 = this.mDialog.getWindow();
                if (!(win2 == null || (decor2 = win2.getDecorView()) == null || (ds2 = decor2.getKeyDispatcherState()) == null)) {
                    ds2.startTracking(event, this);
                    return true;
                }
            } else if (event.getAction() == 1 && !event.isCanceled() && (win = this.mDialog.getWindow()) != null && (decor = win.getDecorView()) != null && (ds = decor.getKeyDispatcherState()) != null && ds.isTracking(event)) {
                this.mMenu.close(true);
                dialog.dismiss();
                return true;
            }
        }
        return this.mMenu.performShortcut(keyCode, event, 0);
    }

    public void setPresenterCallback(MenuPresenter.Callback cb) {
        MenuPresenter.Callback callback = cb;
        this.mPresenterCallback = callback;
    }

    public void dismiss() {
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        DialogInterface dialogInterface2 = dialogInterface;
        this.mPresenter.onCloseMenu(this.mMenu, true);
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        MenuBuilder menu = menuBuilder;
        boolean allMenusAreClosing = z;
        if (allMenusAreClosing || menu == this.mMenu) {
            dismiss();
        }
        if (this.mPresenterCallback != null) {
            this.mPresenterCallback.onCloseMenu(menu, allMenusAreClosing);
        }
    }

    public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
        MenuBuilder subMenu = menuBuilder;
        if (this.mPresenterCallback != null) {
            return this.mPresenterCallback.onOpenSubMenu(subMenu);
        }
        return false;
    }

    public void onClick(DialogInterface dialogInterface, int which) {
        DialogInterface dialogInterface2 = dialogInterface;
        boolean performItemAction = this.mMenu.performItemAction((MenuItemImpl) this.mPresenter.getAdapter().getItem(which), 0);
    }
}
