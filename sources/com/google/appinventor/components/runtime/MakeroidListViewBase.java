package com.google.appinventor.components.runtime;

import android.content.Context;
import android.support.p003v7.widget.LinearLayoutManager;
import android.support.p003v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.runtime.util.DividerItemDecoration;
import com.google.appinventor.components.runtime.util.PaintUtil;

@SimpleObject
public abstract class MakeroidListViewBase extends AndroidViewComponent {
    public static final int DEFAULT_PRIMARY_TEXT_COLOR = PaintUtil.hexStringToInt(ComponentConstants.DEFAULT_PRIMARY_TEXT_COLOR);
    public static final int DEFAULT_SECONDARY_TEXT_COLOR = PaintUtil.hexStringToInt(ComponentConstants.DEFAULT_SECONDARY_TEXT_COLOR);
    public int backgroundColor = -1;
    public ComponentContainer container;
    public Context context;
    public int dividerColor = 0;
    public Form form;
    public boolean isCompanion = false;
    public final LinearLayout listViewLayout;
    public RecyclerView recyclerView;

    public abstract void click(int i);

    public abstract void longClick(int i);

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidListViewBase(com.google.appinventor.components.runtime.ComponentContainer r13, int r14) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = -1
            r3.backgroundColor = r4
            r3 = r0
            r4 = 0
            r3.dividerColor = r4
            r3 = r0
            r4 = 0
            r3.isCompanion = r4
            r3 = r0
            r4 = r1
            r3.container = r4
            r3 = r0
            r4 = r1
            android.app.Activity r4 = r4.$context()
            r3.context = r4
            r3 = r0
            r4 = r1
            com.google.appinventor.components.runtime.Form r4 = r4.$form()
            r3.form = r4
            r3 = r0
            android.widget.LinearLayout r4 = new android.widget.LinearLayout
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = r0
            android.content.Context r6 = r6.context
            r5.<init>(r6)
            r3.listViewLayout = r4
            r3 = r0
            android.widget.LinearLayout r3 = r3.listViewLayout
            r4 = 1
            r3.setOrientation(r4)
            r3 = r0
            android.support.v7.widget.RecyclerView r4 = new android.support.v7.widget.RecyclerView
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = r0
            android.content.Context r6 = r6.context
            r5.<init>(r6)
            r3.recyclerView = r4
            r3 = r0
            android.support.v7.widget.RecyclerView r3 = r3.recyclerView
            android.support.v7.widget.RecyclerView$LayoutParams r4 = new android.support.v7.widget.RecyclerView$LayoutParams
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = -1
            r7 = -1
            r5.<init>((int) r6, (int) r7)
            r3.setLayoutParams(r4)
            r3 = r0
            android.support.v7.widget.RecyclerView r3 = r3.recyclerView
            android.support.v7.widget.LinearLayoutManager r4 = new android.support.v7.widget.LinearLayoutManager
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = r0
            android.content.Context r6 = r6.context
            r7 = r2
            r8 = 0
            r5.<init>(r6, r7, r8)
            r3.setLayoutManager(r4)
            r3 = r0
            android.widget.LinearLayout r3 = r3.listViewLayout
            r4 = r0
            android.support.v7.widget.RecyclerView r4 = r4.recyclerView
            r3.addView(r4)
            r3 = r0
            android.widget.LinearLayout r3 = r3.listViewLayout
            r3.requestLayout()
            r3 = r1
            r4 = r0
            r3.$add(r4)
            r3 = r0
            android.support.v7.widget.RecyclerView r3 = r3.recyclerView
            com.google.appinventor.components.runtime.RecyclerItemClickListener r4 = new com.google.appinventor.components.runtime.RecyclerItemClickListener
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = r0
            android.content.Context r6 = r6.context
            r7 = r0
            android.support.v7.widget.RecyclerView r7 = r7.recyclerView
            com.google.appinventor.components.runtime.MakeroidListViewBase$1 r8 = new com.google.appinventor.components.runtime.MakeroidListViewBase$1
            r11 = r8
            r8 = r11
            r9 = r11
            r10 = r0
            r9.<init>(r10)
            r5.<init>(r6, r7, r8)
            r3.addOnItemTouchListener(r4)
            r3 = r0
            r4 = -2
            r3.Width(r4)
            r3 = r0
            r4 = -1
            r3.Height(r4)
            r3 = r0
            r4 = 0
            r3.BackgroundColor(r4)
            r3 = r0
            r4 = 0
            r3.DividerColor(r4)
            r3 = r0
            com.google.appinventor.components.runtime.Form r3 = r3.form
            boolean r3 = r3 instanceof com.google.appinventor.components.runtime.ReplForm
            if (r3 == 0) goto L_0x00be
            r3 = r0
            r4 = 1
            r3.isCompanion = r4
        L_0x00be:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidListViewBase.<init>(com.google.appinventor.components.runtime.ComponentContainer, int):void");
    }

    public void setRecyclerViewOrientation(int i) {
        RecyclerView.LayoutManager layoutManager;
        new LinearLayoutManager(this.context, i, false);
        this.recyclerView.setLayoutManager(layoutManager);
    }

    @DesignerProperty(defaultValue = "&H00FFFFFF", editorType = "color")
    @SimpleProperty(description = "Set the background color of the listview")
    public void BackgroundColor(int i) {
        int i2 = i;
        this.backgroundColor = i2;
        this.recyclerView.setBackgroundColor(i2 == 0 ? 16777215 : i2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Get the background color of the listview.")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color", propertyType = "advanced")
    @SimpleProperty(description = "Set the divider color of the listview")
    public void DividerColor(int i) {
        RecyclerView.ItemDecoration itemDecoration;
        int i2 = i;
        this.dividerColor = i2;
        new DividerItemDecoration(this.context, i2);
        this.recyclerView.addItemDecoration(itemDecoration);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Get the divider color of the listview.")
    public int DividerColor() {
        return this.dividerColor;
    }

    public View getView() {
        return this.listViewLayout;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Determines the height of the list on the view.")
    public void Height(int i) {
        super.Height(i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Determines the width of the list on the view.")
    public void Width(int i) {
        int i2 = i;
        if (i2 == -1) {
            i2 = -2;
        }
        super.Width(i2);
    }
}
