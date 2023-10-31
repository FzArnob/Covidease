package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.p003v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import de.hdodenhof.circleimageview.CircleImageView;

@SimpleObject
@DesignerComponent(category = ComponentCategory.NAVIGATION, description = "Display a real Navigation Menu in the Side Menu", iconName = "images/makeroidSideMenuLayout.png", nonVisible = true, version = 2)
@UsesLibraries(libraries = "circleimageview.aar, circleimageview.jar, glide.jar")
public class MakeroidSideMenuLayout extends AndroidNonvisibleComponent {
    private AppCompatImageView B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private int[] f484B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private TextView Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;
    private int HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy = -16777216;
    private String I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2;
    private String Mn7MCs8byCcphc6u6vZkI1pXuzw5IvVJJPq0YTQ0xCW64cXQ1HYdJPP7QsOPqGr1;
    private String TcZoKXUijRhvOFm1gZ0ppEfXWxECqlUNNvncSPIfT3ZrTDcozo05OAb21mkMXciT;
    private int backgroundColor = -1;
    private ComponentContainer container;
    private Context context;
    private NavigationView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Menu f485hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private CircleImageView f486hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private int[][] f487hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean isCompanion = false;
    private int joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = Component.COLOR_PINK;
    private String pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok;
    private TextView sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt;
    private LinearLayout vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
    private int x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = Component.COLOR_GRAY;
    private boolean z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj = false;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidSideMenuLayout(com.google.appinventor.components.runtime.ComponentContainer r13) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r3 = r0
            r4 = r1
            com.google.appinventor.components.runtime.Form r4 = r4.$form()
            r3.<init>(r4)
            r3 = r0
            r4 = 0
            r3.isCompanion = r4
            r3 = r0
            r4 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r3.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy = r4
            r3 = r0
            r4 = -6381922(0xffffffffff9e9e9e, float:NaN)
            r3.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = r4
            r3 = r0
            r4 = -1565085(0xffffffffffe81e63, float:NaN)
            r3.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = r4
            r3 = r0
            r4 = -1
            r3.backgroundColor = r4
            r3 = r0
            r4 = 0
            r3.z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj = r4
            r3 = r0
            r4 = 3
            int[][] r4 = new int[r4][]
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = 0
            r7 = 1
            int[] r7 = new int[r7]
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = 0
            r10 = 16842912(0x10100a0, float:2.3694006E-38)
            r8[r9] = r10
            r5[r6] = r7
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = 1
            r7 = 1
            int[] r7 = new int[r7]
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = 0
            r10 = 16842910(0x101009e, float:2.3694E-38)
            r8[r9] = r10
            r5[r6] = r7
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = 2
            r7 = 1
            int[] r7 = new int[r7]
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = 0
            r10 = -16842910(0xfffffffffefeff62, float:-1.6947497E38)
            r8[r9] = r10
            r5[r6] = r7
            r3.f487hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            r3 = r0
            r4 = 3
            int[] r4 = new int[r4]
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = 0
            r7 = r0
            int r7 = r7.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN
            r5[r6] = r7
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = 1
            r7 = r0
            int r7 = r7.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy
            r5[r6] = r7
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = 2
            r7 = r0
            int r7 = r7.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc
            r5[r6] = r7
            r3.f484B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r4
            r3 = r0
            r4 = r1
            android.app.Activity r4 = r4.$context()
            r3.context = r4
            r3 = r0
            r4 = r1
            r3.container = r4
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            boolean r3 = r3 instanceof com.google.appinventor.components.runtime.ReplForm
            if (r3 == 0) goto L_0x009e
            r3 = r0
            r4 = 1
            r3.isCompanion = r4
        L_0x009e:
            r3 = r0
            android.support.design.widget.NavigationView r4 = new android.support.design.widget.NavigationView
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = r0
            android.content.Context r6 = r6.context
            r5.<init>(r6)
            r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            r3 = r0
            android.support.design.widget.NavigationView r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.MakeroidSideMenuLayout$1 r4 = new com.google.appinventor.components.runtime.MakeroidSideMenuLayout$1
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = r0
            r7 = r1
            r5.<init>(r6, r7)
            r3.setNavigationItemSelectedListener(r4)
            r3 = r0
            android.support.design.widget.NavigationView r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            android.content.res.ColorStateList r4 = new android.content.res.ColorStateList
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = r0
            int[][] r6 = r6.f487hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r7 = r0
            int[] r7 = r7.f484B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r5.<init>(r6, r7)
            r3.setItemTextColor(r4)
            r3 = r0
            android.support.design.widget.NavigationView r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            android.content.res.ColorStateList r4 = new android.content.res.ColorStateList
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = r0
            int[][] r6 = r6.f487hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r7 = r0
            int[] r7 = r7.f484B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r5.<init>(r6, r7)
            r3.setItemIconTintList(r4)
            r3 = r0
            android.support.design.widget.NavigationView r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = r0
            android.content.Context r4 = r4.context
            java.lang.String r5 = "navigation_item"
            android.graphics.drawable.Drawable r4 = com.google.appinventor.components.runtime.util.KodularResourcesUtil.getDrawable(r4, r5)
            r3.setItemBackground(r4)
            r3 = r0
            r11 = r3
            r3 = r11
            r4 = r11
            android.support.design.widget.NavigationView r4 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            android.view.Menu r4 = r4.getMenu()
            r3.f485hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            r3 = r0
            android.widget.LinearLayout r4 = new android.widget.LinearLayout
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = r0
            android.content.Context r6 = r6.context
            r7 = 0
            r8 = r0
            android.content.Context r8 = r8.context
            java.lang.String r9 = "ThemeOverlay.AppCompat.Dark"
            int r8 = com.google.appinventor.components.runtime.util.KodularResourcesUtil.getStyle(r8, r9)
            r5.<init>(r6, r7, r8)
            r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = r4
            r3 = r0
            android.widget.LinearLayout r3 = r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            android.widget.LinearLayout$LayoutParams r4 = new android.widget.LinearLayout$LayoutParams
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = -1
            r7 = r0
            android.content.Context r7 = r7.context
            r8 = 176(0xb0, float:2.47E-43)
            int r7 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r7, (int) r8)
            r5.<init>(r6, r7)
            r3.setLayoutParams(r4)
            r3 = r0
            android.widget.LinearLayout r3 = r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r4 = 80
            r3.setGravity(r4)
            r3 = r0
            android.content.Context r3 = r3.context
            r4 = 16
            int r3 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r3, (int) r4)
            r2 = r3
            r3 = r0
            android.widget.LinearLayout r3 = r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r4 = r2
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = r2
            r11 = r6
            r6 = r11
            r7 = r11
            r3.setPadding(r4, r5, r6, r7)
            r3 = r0
            android.widget.LinearLayout r3 = r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r4 = 1
            r3.setOrientation(r4)
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams
            r11 = r3
            r3 = r11
            r4 = r11
            r5 = r0
            android.content.Context r5 = r5.context
            r6 = 72
            int r5 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r5, (int) r6)
            r6 = r0
            android.content.Context r6 = r6.context
            r7 = 72
            int r6 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r6, (int) r7)
            r4.<init>(r5, r6)
            r2 = r3
            r3 = r0
            android.support.v7.widget.AppCompatImageView r4 = new android.support.v7.widget.AppCompatImageView
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = r0
            android.content.Context r6 = r6.context
            r5.<init>(r6)
            r3.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r4
            r3 = r0
            android.support.v7.widget.AppCompatImageView r3 = r3.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r4 = r2
            r3.setLayoutParams(r4)
            r3 = r0
            de.hdodenhof.circleimageview.CircleImageView r4 = new de.hdodenhof.circleimageview.CircleImageView
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = r0
            android.content.Context r6 = r6.context
            r5.<init>(r6)
            r3.f486hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            r3 = r0
            de.hdodenhof.circleimageview.CircleImageView r3 = r3.f486hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = r2
            r3.setLayoutParams(r4)
            r3 = r0
            android.widget.TextView r4 = new android.widget.TextView
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = r0
            android.content.Context r6 = r6.context
            r5.<init>(r6)
            r3.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = r4
            r3 = r0
            android.widget.TextView r3 = r3.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB
            android.widget.LinearLayout$LayoutParams r4 = new android.widget.LinearLayout$LayoutParams
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = -1
            r7 = -2
            r5.<init>(r6, r7)
            r3.setLayoutParams(r4)
            r3 = r0
            android.widget.TextView r3 = r3.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB
            r4 = 0
            r5 = r0
            android.content.Context r5 = r5.context
            r6 = 8
            int r5 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r5, (int) r6)
            r6 = 0
            r7 = 0
            r3.setPadding(r4, r5, r6, r7)
            r3 = r0
            android.widget.TextView r3 = r3.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB
            r4 = 5
            r5 = 1
            r6 = 0
            com.google.appinventor.components.runtime.util.TextViewUtil.setFontTypeface(r3, r4, r5, r6)
            r3 = r0
            android.widget.TextView r3 = r3.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB
            r4 = -1
            r3.setTextColor(r4)
            r3 = r0
            android.widget.TextView r3 = r3.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB
            r4 = 1096810496(0x41600000, float:14.0)
            r3.setTextSize(r4)
            r3 = r0
            android.widget.TextView r3 = r3.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB
            r4 = 1
            r3.setMaxLines(r4)
            r3 = r0
            android.widget.TextView r4 = new android.widget.TextView
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = r0
            android.content.Context r6 = r6.context
            r5.<init>(r6)
            r3.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = r4
            r3 = r0
            android.widget.TextView r3 = r3.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt
            android.widget.LinearLayout$LayoutParams r4 = new android.widget.LinearLayout$LayoutParams
            r11 = r4
            r4 = r11
            r5 = r11
            r6 = -1
            r7 = -2
            r5.<init>(r6, r7)
            r3.setLayoutParams(r4)
            r3 = r0
            android.widget.TextView r3 = r3.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt
            r4 = 5
            r5 = 0
            r6 = 0
            com.google.appinventor.components.runtime.util.TextViewUtil.setFontTypeface(r3, r4, r5, r6)
            r3 = r0
            android.widget.TextView r3 = r3.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt
            r4 = -1
            r3.setTextColor(r4)
            r3 = r0
            android.widget.TextView r3 = r3.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt
            r4 = 1096810496(0x41600000, float:14.0)
            r3.setTextSize(r4)
            r3 = r0
            android.widget.TextView r3 = r3.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt
            r4 = 1
            r3.setMaxLines(r4)
            r3 = r0
            android.widget.LinearLayout r3 = r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r4 = 0
            r3.setWeightSum(r4)
            r3 = r0
            android.widget.LinearLayout r3 = r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r4 = r0
            android.support.v7.widget.AppCompatImageView r4 = r4.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r3.addView(r4)
            r3 = r0
            android.widget.LinearLayout r3 = r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r4 = r0
            de.hdodenhof.circleimageview.CircleImageView r4 = r4.f486hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3.addView(r4)
            r3 = r0
            android.widget.LinearLayout r3 = r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r4 = r0
            android.widget.TextView r4 = r4.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB
            r3.addView(r4)
            r3 = r0
            android.widget.LinearLayout r3 = r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r4 = r0
            android.widget.TextView r4 = r4.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt
            r3.addView(r4)
            r3 = r0
            android.widget.LinearLayout r3 = r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r3.invalidate()
            r3 = r0
            android.support.design.widget.NavigationView r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = r0
            android.widget.LinearLayout r4 = r4.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r3.addHeaderView(r4)
            r3 = r0
            android.support.design.widget.NavigationView r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = 1
            r3.setFitsSystemWindows(r4)
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r4 = r0
            r3.SideMenuLayout(r4)
            r3 = r0
            r11 = r3
            r3 = r11
            r4 = r11
            int r4 = r4.backgroundColor
            r3.BackgroundColor(r4)
            r3 = r0
            r11 = r3
            r3 = r11
            r4 = r11
            boolean r4 = r4.z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj
            r3.CircleHeaderPicture(r4)
            r3 = r0
            r11 = r3
            r3 = r11
            r4 = r11
            int r4 = r4.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc
            r3.DisabledColor(r4)
            r3 = r0
            r11 = r3
            r3 = r11
            r4 = r11
            int r4 = r4.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy
            r3.EnabledColor(r4)
            r3 = r0
            r11 = r3
            r3 = r11
            r4 = r11
            java.lang.String r4 = r4.Mn7MCs8byCcphc6u6vZkI1pXuzw5IvVJJPq0YTQ0xCW64cXQ1HYdJPP7QsOPqGr1
            r3.HeaderBackgroundImage(r4)
            r3 = r0
            r11 = r3
            r3 = r11
            r4 = r11
            java.lang.String r4 = r4.I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2
            r3.HeaderPicture(r4)
            r3 = r0
            r11 = r3
            r3 = r11
            r4 = r11
            java.lang.String r4 = r4.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok
            r3.HeaderSubtitle(r4)
            r3 = r0
            r11 = r3
            r3 = r11
            r4 = r11
            java.lang.String r4 = r4.TcZoKXUijRhvOFm1gZ0ppEfXWxECqlUNNvncSPIfT3ZrTDcozo05OAb21mkMXciT
            r3.HeaderTitle(r4)
            r3 = r0
            r11 = r3
            r3 = r11
            r4 = r11
            int r4 = r4.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN
            r3.SelectedColor(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidSideMenuLayout.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty(description = "Sets the Header Picture of the Side Menu")
    public void HeaderPicture(String str) {
        String str2 = str;
        this.I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2 = str2;
        if (this.z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj) {
            KodularUtil.LoadPicture(this.context, str2 == null ? "" : str2, this.f486hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.isCompanion);
        } else {
            KodularUtil.LoadPicture(this.context, str2 == null ? "" : str2, this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, this.isCompanion);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the Header Picture of the Side Menu")
    public String HeaderPicture() {
        return this.I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Sets (or not) the header picture to use a circle shape")
    public void CircleHeaderPicture(boolean z) {
        boolean z2 = z;
        this.z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj = z2;
        if (z2) {
            this.f486hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(0);
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setVisibility(8);
            KodularUtil.LoadPicture(this.context, this.I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2 == null ? "" : this.I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2, this.f486hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.isCompanion);
            return;
        }
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setVisibility(0);
        this.f486hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(8);
        KodularUtil.LoadPicture(this.context, this.I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2 == null ? "" : this.I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2, this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, this.isCompanion);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns if the Header Picture is using a circular shape")
    public boolean CircleHeaderPicture() {
        return this.z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty(description = "Sets the Header Background Picture")
    public void HeaderBackgroundImage(String str) {
        String str2 = str;
        this.Mn7MCs8byCcphc6u6vZkI1pXuzw5IvVJJPq0YTQ0xCW64cXQ1HYdJPP7QsOPqGr1 = str2;
        try {
            this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.setBackground(MediaUtil.getBitmapDrawable(this.container.$form(), str2));
        } catch (Exception e) {
            int e2 = Log.e("MakeroidSideMenuLayout", String.valueOf(e));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Gets the Header Background Picture")
    public String HeaderBackgroundImage() {
        return this.Mn7MCs8byCcphc6u6vZkI1pXuzw5IvVJJPq0YTQ0xCW64cXQ1HYdJPP7QsOPqGr1;
    }

    @DesignerProperty(defaultValue = "", editorType = "text")
    @SimpleProperty(description = "Sets the Header Title")
    public void HeaderTitle(String str) {
        String str2 = str;
        this.TcZoKXUijRhvOFm1gZ0ppEfXWxECqlUNNvncSPIfT3ZrTDcozo05OAb21mkMXciT = str2;
        TextViewUtil.setText(this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB, str2 == null ? "" : str2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Gets the Header Title")
    public String HeaderTitle() {
        return this.TcZoKXUijRhvOFm1gZ0ppEfXWxECqlUNNvncSPIfT3ZrTDcozo05OAb21mkMXciT;
    }

    @DesignerProperty(defaultValue = "", editorType = "text")
    @SimpleProperty(description = "Sets the Header Subtitle")
    public void HeaderSubtitle(String str) {
        String str2 = str;
        this.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok = str2;
        TextViewUtil.setText(this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt, str2 == null ? "" : str2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Gets the Header Subtitle")
    public String HeaderSubtitle() {
        return this.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Sets the color of the enabled elements of the SideMenu")
    public void EnabledColor(int i) {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        this.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy = i;
        int[] iArr = new int[3];
        iArr[0] = this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN;
        int[] iArr2 = iArr;
        iArr2[1] = this.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy;
        int[] iArr3 = iArr2;
        iArr3[2] = this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc;
        this.f484B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = iArr3;
        new ColorStateList(this.f487hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.f484B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemTextColor(colorStateList);
        new ColorStateList(this.f487hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.f484B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemIconTintList(colorStateList2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the color of the enabled elements of the SideMenu")
    public int EnabledColor() {
        return this.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy;
    }

    @DesignerProperty(defaultValue = "&HFF9E9E9E", editorType = "color")
    @SimpleProperty(description = "Sets the color of the disabled elements of the SideMenu")
    public void DisabledColor(int i) {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = i;
        int[] iArr = new int[3];
        iArr[0] = this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN;
        int[] iArr2 = iArr;
        iArr2[1] = this.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy;
        int[] iArr3 = iArr2;
        iArr3[2] = this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc;
        this.f484B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = iArr3;
        new ColorStateList(this.f487hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.f484B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemTextColor(colorStateList);
        new ColorStateList(this.f487hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.f484B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemIconTintList(colorStateList2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the color of the disabled elements of the SideMenu")
    public int DisabledColor() {
        return this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc;
    }

    @DesignerProperty(defaultValue = "&HFFE81E63", editorType = "color")
    @SimpleProperty(description = "Sets the color of the selected element of the SideMenu")
    public void SelectedColor(int i) {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = i;
        int[] iArr = new int[3];
        iArr[0] = this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN;
        int[] iArr2 = iArr;
        iArr2[1] = this.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy;
        int[] iArr3 = iArr2;
        iArr3[2] = this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc;
        this.f484B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = iArr3;
        new ColorStateList(this.f487hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.f484B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemTextColor(colorStateList);
        new ColorStateList(this.f487hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.f484B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemIconTintList(colorStateList2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the color of the selected element of the SideMenu")
    public int SelectedColor() {
        return this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN;
    }

    @SimpleFunction(description = "Add an item to the Navigation Menu")
    public void AddItem(String str, String str2, boolean z, boolean z2, int i) {
        StringBuilder sb;
        String str3 = str2;
        boolean z3 = z;
        boolean z4 = z2;
        MenuItem add = this.f485hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.add(i, 0, 0, str);
        if (!str3.equals("")) {
            try {
                BitmapDrawable bitmapDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), str3);
                BitmapDrawable bitmapDrawable2 = bitmapDrawable;
                if (bitmapDrawable != null) {
                    MenuItem icon = add.setIcon(bitmapDrawable2);
                }
            } catch (Exception e) {
                new StringBuilder("Could not load Image - ");
                int e2 = Log.e("MakeroidSideMenuLayout", sb.append(e.getMessage()).toString());
            }
        }
        MenuItem enabled = add.setEnabled(z3);
        MenuItem checkable = add.setCheckable(true);
        MenuItem checked = add.setChecked(z4);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleFunction(description = "Update an item of the Navigation Menu")
    public void UpdateItem(String str, String str2, String str3, boolean z, boolean z2) {
        StringBuilder sb;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        boolean z3 = z;
        boolean z4 = z2;
        for (int i = 0; i < this.f485hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.size(); i++) {
            MenuItem item = this.f485hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getItem(i);
            MenuItem menuItem = item;
            if (item.getTitle() == str4) {
                MenuItem title = menuItem.setTitle(str5);
                if (str6.equals("")) {
                    MenuItem icon = menuItem.setIcon((Drawable) null);
                } else {
                    try {
                        BitmapDrawable bitmapDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), str6);
                        BitmapDrawable bitmapDrawable2 = bitmapDrawable;
                        if (bitmapDrawable != null) {
                            MenuItem icon2 = menuItem.setIcon(bitmapDrawable2);
                        }
                    } catch (Exception e) {
                        new StringBuilder("Could not load Image for update - ");
                        int e2 = Log.e("MakeroidSideMenuLayout", sb.append(e.getMessage()).toString());
                    }
                }
                MenuItem enabled = menuItem.setEnabled(z3);
                MenuItem checked = menuItem.setChecked(z4);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
            }
        }
    }

    @SimpleFunction(description = "Remove an item from the Navigation Menu")
    public void RemoveItem(String str) {
        String str2 = str;
        for (int i = 0; i < this.f485hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.size(); i++) {
            if (this.f485hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getItem(i).getTitle() == str2) {
                this.f485hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.removeItem(i);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
            }
        }
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Set the background color of the Sidemenu Layout")
    public void BackgroundColor(int i) {
        int i2 = i;
        this.backgroundColor = i2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(i2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Get the background color of the Sidemenu Layout")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @SimpleEvent(description = "Triggers when the user clicks on an item of the Navigation Menu")
    public void OnMenuItemClick(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnMenuItemClick", str);
    }

    public View getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }
}
