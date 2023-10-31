package android.support.p003v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.p003v7.view.menu.ListMenuItemView;
import android.support.p003v7.view.menu.MenuAdapter;
import android.support.p003v7.view.menu.MenuBuilder;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.MenuPopupWindow */
public class MenuPopupWindow extends ListPopupWindow implements MenuItemHoverListener {
    private static final String TAG = "MenuPopupWindow";
    private static Method sSetTouchModalMethod;
    private MenuItemHoverListener mHoverListener;

    static {
        Class<PopupWindow> cls = PopupWindow.class;
        try {
            sSetTouchModalMethod = cls.getDeclaredMethod("setTouchModal", new Class[]{Boolean.TYPE});
        } catch (NoSuchMethodException e) {
            NoSuchMethodException noSuchMethodException = e;
            int i = Log.i(TAG, "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MenuPopupWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /* access modifiers changed from: package-private */
    public DropDownListView createDropDownListView(Context context, boolean hijackFocus) {
        MenuDropDownListView menuDropDownListView;
        new MenuDropDownListView(context, hijackFocus);
        MenuDropDownListView view = menuDropDownListView;
        view.setHoverListener(this);
        return view;
    }

    public void setEnterTransition(Object obj) {
        Object enterTransition = obj;
        if (Build.VERSION.SDK_INT >= 23) {
            this.mPopup.setEnterTransition((Transition) enterTransition);
        }
    }

    public void setExitTransition(Object obj) {
        Object exitTransition = obj;
        if (Build.VERSION.SDK_INT >= 23) {
            this.mPopup.setExitTransition((Transition) exitTransition);
        }
    }

    public void setHoverListener(MenuItemHoverListener hoverListener) {
        MenuItemHoverListener menuItemHoverListener = hoverListener;
        this.mHoverListener = menuItemHoverListener;
    }

    public void setTouchModal(boolean z) {
        boolean touchModal = z;
        if (sSetTouchModalMethod != null) {
            try {
                Object invoke = sSetTouchModalMethod.invoke(this.mPopup, new Object[]{Boolean.valueOf(touchModal)});
            } catch (Exception e) {
                Exception exc = e;
                int i = Log.i(TAG, "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    public void onItemHoverEnter(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
        MenuBuilder menu = menuBuilder;
        MenuItem item = menuItem;
        if (this.mHoverListener != null) {
            this.mHoverListener.onItemHoverEnter(menu, item);
        }
    }

    public void onItemHoverExit(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
        MenuBuilder menu = menuBuilder;
        MenuItem item = menuItem;
        if (this.mHoverListener != null) {
            this.mHoverListener.onItemHoverExit(menu, item);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* renamed from: android.support.v7.widget.MenuPopupWindow$MenuDropDownListView */
    public static class MenuDropDownListView extends DropDownListView {
        final int mAdvanceKey;
        private MenuItemHoverListener mHoverListener;
        private MenuItem mHoveredMenuItem;
        final int mRetreatKey;

        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        public /* bridge */ /* synthetic */ int lookForSelectablePosition(int i, boolean z) {
            return super.lookForSelectablePosition(i, z);
        }

        public /* bridge */ /* synthetic */ int measureHeightOfChildrenCompat(int i, int i2, int i3, int i4, int i5) {
            return super.measureHeightOfChildrenCompat(i, i2, i3, i4, i5);
        }

        public /* bridge */ /* synthetic */ boolean onForwardedEvent(MotionEvent motionEvent, int i) {
            return super.onForwardedEvent(motionEvent, i);
        }

        public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public MenuDropDownListView(android.content.Context r9, boolean r10) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r2 = r10
                r5 = r0
                r6 = r1
                r7 = r2
                r5.<init>(r6, r7)
                r5 = r1
                android.content.res.Resources r5 = r5.getResources()
                r3 = r5
                r5 = r3
                android.content.res.Configuration r5 = r5.getConfiguration()
                r4 = r5
                int r5 = android.os.Build.VERSION.SDK_INT
                r6 = 17
                if (r5 < r6) goto L_0x002e
                r5 = 1
                r6 = r4
                int r6 = r6.getLayoutDirection()
                if (r5 != r6) goto L_0x002e
                r5 = r0
                r6 = 21
                r5.mAdvanceKey = r6
                r5 = r0
                r6 = 22
                r5.mRetreatKey = r6
            L_0x002d:
                return
            L_0x002e:
                r5 = r0
                r6 = 22
                r5.mAdvanceKey = r6
                r5 = r0
                r6 = 21
                r5.mRetreatKey = r6
                goto L_0x002d
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.MenuPopupWindow.MenuDropDownListView.<init>(android.content.Context, boolean):void");
        }

        public void setHoverListener(MenuItemHoverListener hoverListener) {
            MenuItemHoverListener menuItemHoverListener = hoverListener;
            this.mHoverListener = menuItemHoverListener;
        }

        public void clearSelection() {
            setSelection(-1);
        }

        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            int keyCode = i;
            KeyEvent event = keyEvent;
            ListMenuItemView selectedItem = (ListMenuItemView) getSelectedView();
            if (selectedItem != null && keyCode == this.mAdvanceKey) {
                if (selectedItem.isEnabled() && selectedItem.getItemData().hasSubMenu()) {
                    boolean performItemClick = performItemClick(selectedItem, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            } else if (selectedItem == null || keyCode != this.mRetreatKey) {
                return super.onKeyDown(keyCode, event);
            } else {
                setSelection(-1);
                ((MenuAdapter) getAdapter()).getAdapterMenu().close(false);
                return true;
            }
        }

        public boolean onHoverEvent(MotionEvent motionEvent) {
            int headersCount;
            MenuAdapter menuAdapter;
            int position;
            int itemPosition;
            MotionEvent ev = motionEvent;
            if (this.mHoverListener != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerAdapter = (HeaderViewListAdapter) adapter;
                    headersCount = headerAdapter.getHeadersCount();
                    menuAdapter = (MenuAdapter) headerAdapter.getWrappedAdapter();
                } else {
                    headersCount = 0;
                    menuAdapter = (MenuAdapter) adapter;
                }
                MenuItem menuItem = null;
                if (ev.getAction() != 10 && (position = pointToPosition((int) ev.getX(), (int) ev.getY())) != -1 && (itemPosition = position - headersCount) >= 0 && itemPosition < menuAdapter.getCount()) {
                    menuItem = menuAdapter.getItem(itemPosition);
                }
                MenuItem oldMenuItem = this.mHoveredMenuItem;
                if (oldMenuItem != menuItem) {
                    MenuBuilder menu = menuAdapter.getAdapterMenu();
                    if (oldMenuItem != null) {
                        this.mHoverListener.onItemHoverExit(menu, oldMenuItem);
                    }
                    this.mHoveredMenuItem = menuItem;
                    if (menuItem != null) {
                        this.mHoverListener.onItemHoverEnter(menu, menuItem);
                    }
                }
            }
            return super.onHoverEvent(ev);
        }
    }
}
