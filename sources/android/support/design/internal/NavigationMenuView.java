package android.support.design.internal;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.p003v7.view.menu.MenuBuilder;
import android.support.p003v7.view.menu.MenuView;
import android.support.p003v7.widget.RecyclerView;
import android.util.AttributeSet;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class NavigationMenuView extends RecyclerView implements MenuView {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NavigationMenuView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NavigationMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NavigationMenuView(android.content.Context r12, android.util.AttributeSet r13, int r14) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = r3
            r4.<init>(r5, r6, r7)
            r4 = r0
            android.support.v7.widget.LinearLayoutManager r5 = new android.support.v7.widget.LinearLayoutManager
            r10 = r5
            r5 = r10
            r6 = r10
            r7 = r1
            r8 = 1
            r9 = 0
            r6.<init>(r7, r8, r9)
            r4.setLayoutManager(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.internal.NavigationMenuView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void initialize(MenuBuilder menu) {
    }

    public int getWindowAnimations() {
        return 0;
    }
}
