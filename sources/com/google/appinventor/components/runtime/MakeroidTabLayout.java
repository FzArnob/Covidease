package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.PaintUtil;
import java.util.ArrayList;

@SimpleObject
@DesignerComponent(category = ComponentCategory.NAVIGATION, description = "", iconName = "images/tabLayout.png", version = 1)
public class MakeroidTabLayout extends AndroidViewComponent implements OnOrientationChangeListener {
    private static final int DEFAULT_ACCENT_COLOR = PaintUtil.hexStringToInt(ComponentConstants.DEFAULT_ACCENT_COLOR);
    private static final int DEFAULT_PRIMARY_COLOR = PaintUtil.hexStringToInt("&HFF3F51B5");
    private ArrayList<String> B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private int MYUGxENNZgpsWEBTVSKDauXfXur6zyPKrPdlATl7m89YCcguzmIKP8wXNDkxMYaw = -16777216;
    private int OFXnSk7pjyu5TDlQcCs0Ee2Ss8ceD26gQ4XJfzIMtdlcKhGXQ2j7Mh9NsuvjNyC;
    private ComponentContainer container;
    private Context context;
    private int gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ = -1;
    /* access modifiers changed from: private */
    public TabLayout hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private LinearLayout mainLayout;
    private FrameLayout.LayoutParams wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    private int z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj = -16777216;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidTabLayout(com.google.appinventor.components.runtime.ComponentContainer r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = -1
            r2.gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ = r3
            r2 = r0
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2.MYUGxENNZgpsWEBTVSKDauXfXur6zyPKrPdlATl7m89YCcguzmIKP8wXNDkxMYaw = r3
            r2 = r0
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2.z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj = r3
            r2 = r0
            java.util.ArrayList r3 = new java.util.ArrayList
            r7 = r3
            r3 = r7
            r4 = r7
            r4.<init>()
            r2.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r1
            com.google.appinventor.components.runtime.Form r2 = r2.$form()
            r3 = r0
            r2.registerForOnOrientationChangeListener(r3)
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            android.widget.LinearLayout r3 = new android.widget.LinearLayout
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            android.content.Context r5 = r5.context
            r4.<init>(r5)
            r2.mainLayout = r3
            r2 = r0
            android.widget.LinearLayout r2 = r2.mainLayout
            r3 = 1
            r2.setOrientation(r3)
            r2 = r0
            android.support.design.widget.TabLayout r3 = new android.support.design.widget.TabLayout
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            android.content.Context r5 = r5.context
            r4.<init>(r5)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            android.widget.FrameLayout$LayoutParams r3 = new android.widget.FrameLayout$LayoutParams
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = -1
            r6 = -2
            r4.<init>(r5, r6)
            r2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = r3
            r2 = r0
            android.support.design.widget.TabLayout r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = 1
            r2.setTabGravity(r3)
            r2 = r0
            android.support.design.widget.TabLayout r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.MakeroidTabLayout$1 r3 = new com.google.appinventor.components.runtime.MakeroidTabLayout$1
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.addOnTabSelectedListener(r3)
            r2 = r0
            android.widget.LinearLayout r2 = r2.mainLayout
            r3 = r0
            android.support.design.widget.TabLayout r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = r0
            android.widget.FrameLayout$LayoutParams r4 = r4.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            r2.addView(r3, r4)
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r0
            int r3 = DEFAULT_PRIMARY_COLOR
            r2.TabsBackgroundColor(r3)
            r2 = r0
            r3 = -1
            r2.TabsIndicatorColor(r3)
            r2 = r0
            r3 = -1
            r2.TabsTextColor(r3)
            r2 = r0
            int r3 = DEFAULT_ACCENT_COLOR
            r2.TabsActiveTextColor(r3)
            r2 = r0
            r3 = 1
            r2.TabsMode(r3)
            r2 = r0
            r3 = -2
            r2.Width(r3)
            r2 = r0
            r3 = 1
            r2.Visible(r3)
            r2 = r1
            com.google.appinventor.components.runtime.Form r2 = r2.$form()
            r2.removeElevation()
            java.lang.String r2 = "Makeroid Tab Layout"
            java.lang.String r3 = "Makeroid Tab Layout Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidTabLayout.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public LinearLayout getView() {
        return this.mainLayout;
    }

    @SimpleProperty
    public void Width(int i) {
        int i2 = i;
        if (i2 == -1) {
            i2 = -2;
        }
        super.Width(i2);
    }

    public void HeightPercent(int i) {
    }

    public int Height() {
        return getView().getHeight();
    }

    public void Height(int i) {
        this.container.setChildHeight(this, i);
    }

    @SimpleEvent(description = "The event returns the name or the position of the selected tab.")
    public void TabItemSelected(String str, int i) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(i);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TabItemSelected", objArr2);
    }

    @SimpleFunction(description = "Removes a before added tab. If you want to delete the first tab then use as position '1'.")
    public void RemoveTabAt(int i) {
        int i2 = i;
        if (i2 > 0) {
            try {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.removeTabAt(i2 - 1);
            } catch (Exception e) {
                int e2 = Log.e("Makeroid Tab Layout", String.valueOf(e));
            }
        } else {
            try {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.removeTabAt(0);
            } catch (Exception e3) {
                int e4 = Log.e("Makeroid Tab Layout", String.valueOf(e3));
            }
        }
    }

    @SimpleFunction(description = "Remove all tab's from tab layout.")
    public void RemoveAllTabs() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.removeAllTabs();
    }

    @SimpleFunction(description = "Returns the number of current added tab's.")
    public int CountTabs() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getTabCount();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Select a tab which is then the active tab.")
    public void SelectTab(int i) {
        Handler handler;
        Runnable runnable;
        int i2 = i;
        try {
            new Handler();
            final int i3 = i2;
            new Runnable(this) {
                private /* synthetic */ MakeroidTabLayout hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void run() {
                    try {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getTabAt(i3).select();
                    } catch (NullPointerException e) {
                        int e2 = Log.e("Makeroid Tab Layout", String.valueOf(e));
                    } catch (Exception e3) {
                        int e4 = Log.e("Makeroid Tab Layout", String.valueOf(e3));
                    }
                }
            };
            boolean postDelayed = handler.postDelayed(runnable, 100);
        } catch (Exception e) {
            int e2 = Log.e("Makeroid Tab Layout", String.valueOf(e));
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Get the current selected tab.")
    public int GetCurrentTab() {
        try {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getSelectedTabPosition();
        } catch (Exception e) {
            int e2 = Log.e("Makeroid Tab Layout", String.valueOf(e));
            return 0;
        }
    }

    @SimpleFunction(description = "Add a new tab to the tab layout. If you don't want a icon then let it empty.")
    public void AddNewTab(String str, String str2) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, str2, -1);
    }

    @SimpleFunction(description = "Add a new tab to the tab layout at the given position. If you don't want a icon then let it empty.")
    public void AddNewTabAt(String str, String str2, int i) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, str2, i);
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, String str2, int i) {
        StringBuilder sb;
        String str3 = str;
        String str4 = str2;
        int i2 = i;
        String str5 = str3.isEmpty() ? "Tab" : str3;
        TabLayout.Tab newTab = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.newTab();
        TabLayout.Tab tab = newTab;
        TabLayout.Tab text = newTab.setText((CharSequence) str5);
        if (!str4.isEmpty()) {
            try {
                TabLayout.Tab icon = tab.setIcon((Drawable) MediaUtil.getBitmapDrawable(this.container.$form(), str4));
            } catch (Exception e) {
                new StringBuilder();
                int d = Log.d("Makeroid Tab Layout", sb.append(e.getMessage()).toString());
            }
        }
        if (i2 != -1) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addTab(tab, i2 - 1);
        } else {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addTab(tab);
        }
    }

    @DesignerProperty(defaultValue = "&HFF3F51B5", editorType = "color")
    @SimpleProperty(description = "Specifies the tab's background color.")
    public void TabsBackgroundColor(int i) {
        int i2 = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(i2);
        this.OFXnSk7pjyu5TDlQcCs0Ee2Ss8ceD26gQ4XJfzIMtdlcKhGXQ2j7Mh9NsuvjNyC = i2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int TabsBackgroundColor() {
        return this.OFXnSk7pjyu5TDlQcCs0Ee2Ss8ceD26gQ4XJfzIMtdlcKhGXQ2j7Mh9NsuvjNyC;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Specifies the tab's indicator color.")
    public void TabsIndicatorColor(int i) {
        int i2 = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setSelectedTabIndicatorColor(i2);
        this.gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ = i2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int TabsIndicatorColor() {
        return this.gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Specifies the tab's text color for not selected tab's.")
    public void TabsTextColor(int i) {
        int i2 = i;
        this.MYUGxENNZgpsWEBTVSKDauXfXur6zyPKrPdlATl7m89YCcguzmIKP8wXNDkxMYaw = i2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabTextColors(i2, this.z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int TabsTextColor() {
        return this.MYUGxENNZgpsWEBTVSKDauXfXur6zyPKrPdlATl7m89YCcguzmIKP8wXNDkxMYaw;
    }

    @DesignerProperty(defaultValue = "&HFFFF4081", editorType = "color")
    @SimpleProperty(description = "Specifies the tab's text color for active tab's.")
    public void TabsActiveTextColor(int i) {
        int i2 = i;
        this.z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj = i2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabTextColors(this.MYUGxENNZgpsWEBTVSKDauXfXur6zyPKrPdlATl7m89YCcguzmIKP8wXNDkxMYaw, i2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int TabsActiveTextColor() {
        return this.MYUGxENNZgpsWEBTVSKDauXfXur6zyPKrPdlATl7m89YCcguzmIKP8wXNDkxMYaw;
    }

    @DesignerProperty(defaultValue = "1", editorType = "tabs_mode")
    @SimpleProperty(description = "Choose the mode used for the tab's. If no mode is specified, 'Scrollable' is taken as 'Default'. Use '0' for scrollable and '1' for fixed.")
    public void TabsMode(int i) {
        switch (i) {
            case 0:
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabMode(1);
                return;
            case 1:
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabMode(0);
                return;
            default:
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabMode(0);
                return;
        }
    }

    @SimpleProperty
    public int TabsMode() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getTabMode();
    }

    public void onOrientationChange() {
        if (this.mainLayout.getVisibility() == 0) {
            this.container.$form().removeElevation();
        } else {
            this.container.$form().addElevation();
        }
    }

    @DesignerProperty(defaultValue = "True", editorType = "visibility")
    @SimpleProperty(description = "Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.")
    public void Visible(boolean z) {
        boolean z2 = z;
        getView().setVisibility(z2 ? 0 : 8);
        if (z2) {
            this.container.$form().removeElevation();
        } else {
            this.container.$form().addElevation();
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean Visible() {
        return getView().getVisibility() == 0;
    }
}
