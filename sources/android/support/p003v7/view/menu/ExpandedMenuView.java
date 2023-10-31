package android.support.p003v7.view.menu;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.p003v7.view.menu.MenuBuilder;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.view.menu.ExpandedMenuView */
public final class ExpandedMenuView extends ListView implements MenuBuilder.ItemInvoker, MenuView, AdapterView.OnItemClickListener {
    private static final int[] TINT_ATTRS = {16842964, 16843049};
    private int mAnimations;
    private MenuBuilder mMenu;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ExpandedMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 16842868);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ExpandedMenuView(android.content.Context r11, android.util.AttributeSet r12, int r13) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r5 = r0
            r6 = r1
            r7 = r2
            r5.<init>(r6, r7)
            r5 = r0
            r6 = r0
            r5.setOnItemClickListener(r6)
            r5 = r1
            r6 = r2
            int[] r7 = TINT_ATTRS
            r8 = r3
            r9 = 0
            android.support.v7.widget.TintTypedArray r5 = android.support.p003v7.widget.TintTypedArray.obtainStyledAttributes(r5, r6, r7, r8, r9)
            r4 = r5
            r5 = r4
            r6 = 0
            boolean r5 = r5.hasValue(r6)
            if (r5 == 0) goto L_0x002c
            r5 = r0
            r6 = r4
            r7 = 0
            android.graphics.drawable.Drawable r6 = r6.getDrawable(r7)
            r5.setBackgroundDrawable(r6)
        L_0x002c:
            r5 = r4
            r6 = 1
            boolean r5 = r5.hasValue(r6)
            if (r5 == 0) goto L_0x003e
            r5 = r0
            r6 = r4
            r7 = 1
            android.graphics.drawable.Drawable r6 = r6.getDrawable(r7)
            r5.setDivider(r6)
        L_0x003e:
            r5 = r4
            r5.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.view.menu.ExpandedMenuView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void initialize(MenuBuilder menu) {
        MenuBuilder menuBuilder = menu;
        this.mMenu = menuBuilder;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    public boolean invokeItem(MenuItemImpl item) {
        return this.mMenu.performItemAction(item, 0);
    }

    public void onItemClick(AdapterView adapterView, View view, int position, long j) {
        AdapterView adapterView2 = adapterView;
        View view2 = view;
        long j2 = j;
        boolean invokeItem = invokeItem((MenuItemImpl) getAdapter().getItem(position));
    }

    public int getWindowAnimations() {
        return this.mAnimations;
    }
}
