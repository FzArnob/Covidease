package android.support.design.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.p003v7.view.menu.MenuBuilder;
import android.support.p003v7.view.menu.MenuItemImpl;
import android.support.p003v7.view.menu.MenuPresenter;
import android.support.p003v7.view.menu.MenuView;
import android.support.p003v7.view.menu.SubMenuBuilder;
import android.view.ViewGroup;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class BottomNavigationPresenter implements MenuPresenter {

    /* renamed from: id */
    private int f17id;
    private MenuBuilder menu;
    private BottomNavigationMenuView menuView;
    private boolean updateSuspended = false;

    public BottomNavigationPresenter() {
    }

    public void setBottomNavigationMenuView(BottomNavigationMenuView menuView2) {
        BottomNavigationMenuView bottomNavigationMenuView = menuView2;
        this.menuView = bottomNavigationMenuView;
    }

    public void initForMenu(Context context, MenuBuilder menu2) {
        Context context2 = context;
        this.menu = menu2;
        this.menuView.initialize(this.menu);
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        ViewGroup viewGroup2 = viewGroup;
        return this.menuView;
    }

    public void updateMenuView(boolean z) {
        boolean cleared = z;
        if (!this.updateSuspended) {
            if (cleared) {
                this.menuView.buildMenuView();
            } else {
                this.menuView.updateMenuView();
            }
        }
    }

    public void setCallback(MenuPresenter.Callback cb) {
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        return false;
    }

    public void onCloseMenu(MenuBuilder menu2, boolean allMenusAreClosing) {
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

    public void setId(int id) {
        int i = id;
        this.f17id = i;
    }

    public int getId() {
        return this.f17id;
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        new SavedState();
        SavedState savedState2 = savedState;
        savedState2.selectedItemId = this.menuView.getSelectedItemId();
        return savedState2;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable state = parcelable;
        if (state instanceof SavedState) {
            this.menuView.tryRestoreSelectedItemId(((SavedState) state).selectedItemId);
        }
    }

    public void setUpdateSuspended(boolean updateSuspended2) {
        boolean z = updateSuspended2;
        this.updateSuspended = z;
    }

    static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR;
        int selectedItemId;

        SavedState() {
        }

        SavedState(Parcel in) {
            this.selectedItemId = in.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(@NonNull Parcel out, int i) {
            int i2 = i;
            out.writeInt(this.selectedItemId);
        }

        static {
            Parcelable.Creator<SavedState> creator;
            new Parcelable.Creator<SavedState>() {
                public SavedState createFromParcel(Parcel in) {
                    SavedState savedState;
                    new SavedState(in);
                    return savedState;
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
            CREATOR = creator;
        }
    }
}
