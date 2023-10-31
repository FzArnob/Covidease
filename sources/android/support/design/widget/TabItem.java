package android.support.design.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class TabItem extends View {
    public final int customLayout;
    public final Drawable icon;
    public final CharSequence text;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TabItem(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TabItem(android.content.Context r9, android.util.AttributeSet r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r4 = r0
            r5 = r1
            r6 = r2
            r4.<init>(r5, r6)
            r4 = r1
            r5 = r2
            int[] r6 = android.support.design.C0064R.styleable.TabItem
            android.support.v7.widget.TintTypedArray r4 = android.support.p003v7.widget.TintTypedArray.obtainStyledAttributes((android.content.Context) r4, (android.util.AttributeSet) r5, (int[]) r6)
            r3 = r4
            r4 = r0
            r5 = r3
            int r6 = android.support.design.C0064R.styleable.TabItem_android_text
            java.lang.CharSequence r5 = r5.getText(r6)
            r4.text = r5
            r4 = r0
            r5 = r3
            int r6 = android.support.design.C0064R.styleable.TabItem_android_icon
            android.graphics.drawable.Drawable r5 = r5.getDrawable(r6)
            r4.icon = r5
            r4 = r0
            r5 = r3
            int r6 = android.support.design.C0064R.styleable.TabItem_android_layout
            r7 = 0
            int r5 = r5.getResourceId(r6, r7)
            r4.customLayout = r5
            r4 = r3
            r4.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.TabItem.<init>(android.content.Context, android.util.AttributeSet):void");
    }
}
