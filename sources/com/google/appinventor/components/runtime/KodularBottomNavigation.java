package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.PaintUtil;

@SimpleObject
@DesignerComponent(category = ComponentCategory.NAVIGATION, description = "A component that create a Bottom Navigation Menu in the app", iconName = "images/bottomNavigation.png", nonVisible = false, version = 1)
public final class KodularBottomNavigation extends AndroidViewComponent {
    private int[] B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private int JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = PaintUtil.hexStringToInt("&HFF6E6E6E");
    private int backgroundColor;
    private Context context;
    private Form form;
    private BottomNavigationView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private int[][] f451hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = PaintUtil.hexStringToInt("&HFF3F51B5");

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularBottomNavigation(com.google.appinventor.components.runtime.ComponentContainer r12) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = "&HFF6E6E6E"
            int r3 = com.google.appinventor.components.runtime.util.PaintUtil.hexStringToInt(r3)
            r2.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = r3
            r2 = r0
            java.lang.String r3 = "&HFF3F51B5"
            int r3 = com.google.appinventor.components.runtime.util.PaintUtil.hexStringToInt(r3)
            r2.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = r3
            r2 = r0
            r3 = 2
            int[][] r3 = new int[r3][]
            r10 = r3
            r3 = r10
            r4 = r10
            r5 = 0
            r6 = 1
            int[] r6 = new int[r6]
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = 0
            r9 = 16842912(0x10100a0, float:2.3694006E-38)
            r7[r8] = r9
            r4[r5] = r6
            r10 = r3
            r3 = r10
            r4 = r10
            r5 = 1
            r6 = 1
            int[] r6 = new int[r6]
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = 0
            r9 = 16842910(0x101009e, float:2.3694E-38)
            r7[r8] = r9
            r4[r5] = r6
            r2.f451hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            r3 = 2
            int[] r3 = new int[r3]
            r10 = r3
            r3 = r10
            r4 = r10
            r5 = 0
            r6 = r0
            int r6 = r6.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN
            r4[r5] = r6
            r10 = r3
            r3 = r10
            r4 = r10
            r5 = 1
            r6 = r0
            int r6 = r6.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd
            r4[r5] = r6
            r2.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            android.support.design.widget.BottomNavigationView r3 = new android.support.design.widget.BottomNavigationView
            r10 = r3
            r3 = r10
            r4 = r10
            r5 = r0
            android.content.Context r5 = r5.context
            r4.<init>(r5)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r0
            android.support.design.widget.BottomNavigationView r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.KodularBottomNavigation$1 r3 = new com.google.appinventor.components.runtime.KodularBottomNavigation$1
            r10 = r3
            r3 = r10
            r4 = r10
            r5 = r0
            r4.<init>(r5)
            r2.setOnNavigationItemSelectedListener(r3)
            r2 = r0
            r3 = -2
            r2.Width(r3)
            r2 = r0
            r3 = -1
            r2.Height(r3)
            r2 = r0
            r3 = 0
            r2.BackgroundColor(r3)
            r2 = r0
            r2.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularBottomNavigation.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Set the background color of the Bottom Navigation Menu")
    public final void BackgroundColor(int i) {
        int i2 = i;
        this.backgroundColor = i2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(i2 == 0 ? -1 : i2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Get the background color of the Bottom Navigation Menu.")
    public final int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFF3F51B5", editorType = "color")
    @SimpleProperty(description = "Set the color of the selected item of the Bottom Navigation Menu")
    public final void SelectedColor(int i) {
        this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = i;
        mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Get the color of the selected item of the Bottom Navigation Menu")
    public final int SelectedColor() {
        return this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN;
    }

    @DesignerProperty(defaultValue = "&HFF6E6E6E", editorType = "color")
    @SimpleProperty(description = "Set the color of the unselected items of the Bottom Navigation Menu")
    public final void UnselectedColor(int i) {
        this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = i;
        mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Get the color of the unselected items of the Bottom Navigation Menu")
    public final int UnselectedColor() {
        return this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd;
    }

    @SimpleFunction(description = "Add an item to the bottom menu")
    public final void AddItem(int i, String str, String str2) {
        int i2 = i;
        String str3 = str;
        String str4 = str2;
        try {
            if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMenu().size() < 5) {
                MenuItem icon = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMenu().add(0, i2, 0, str3).setIcon(MediaUtil.getBitmapDrawable(this.form, str4));
            }
        } catch (Exception e) {
        }
    }

    @SimpleFunction(description = "Update an item of the bottom menu")
    public final void UpdateItem(int i, String str, String str2) {
        try {
            MenuItem icon = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMenu().findItem(i).setTitle(str).setIcon(MediaUtil.getBitmapDrawable(this.form, str2));
        } catch (Exception e) {
        }
    }

    @SimpleFunction(description = "Remove an item from the bottom menu")
    public final void RemoveItem(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMenu().removeItem(i);
    }

    @SimpleFunction(description = "Remove all items from the bottom menu")
    public final void RemoveAllItems() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMenu().clear();
    }

    @SimpleFunction(description = "Select an item from the bottom menu")
    public final void SelectItem(int i) {
        MenuItem checked = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMenu().findItem(i).setChecked(true);
    }

    @SimpleEvent(description = "Event triggers when an item was selected.")
    public final void ItemSelected(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ItemSelected", objArr2);
    }

    public final View getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    private void mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        int[] iArr = new int[2];
        iArr[0] = this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN;
        int[] iArr2 = iArr;
        iArr2[1] = this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = iArr2;
        new ColorStateList(this.f451hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemTextColor(colorStateList);
        new ColorStateList(this.f451hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemIconTintList(colorStateList2);
    }

    public final int Height() {
        return getView().getHeight();
    }

    public final void Height(int i) {
        this.container.setChildHeight(this, i);
    }

    public final int Width() {
        return getView().getWidth();
    }

    public final void Width(int i) {
        this.container.setChildWidth(this, i);
    }

    public final void HeightPercent(int i) {
    }

    public final void WidthPercent(int i) {
    }
}
