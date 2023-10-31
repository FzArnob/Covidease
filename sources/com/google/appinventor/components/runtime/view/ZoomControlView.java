package com.google.appinventor.components.runtime.view;

import android.widget.Button;
import android.widget.LinearLayout;
import org.osmdroid.views.MapView;

public class ZoomControlView extends LinearLayout {
    /* access modifiers changed from: private */
    public final MapView B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private float ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ;
    private final Button vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
    private final Button wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ZoomControlView(org.osmdroid.views.MapView r14) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r3 = r0
            r4 = r1
            android.content.Context r4 = r4.getContext()
            r3.<init>(r4)
            r3 = r0
            r4 = r1
            android.content.Context r4 = r4.getContext()
            android.content.res.Resources r4 = r4.getResources()
            android.util.DisplayMetrics r4 = r4.getDisplayMetrics()
            float r4 = r4.density
            r3.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ = r4
            r3 = r0
            r4 = r1
            r3.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r4
            r3 = r0
            r4 = 1
            r3.setOrientation(r4)
            r3 = r0
            android.widget.Button r4 = new android.widget.Button
            r12 = r4
            r4 = r12
            r5 = r12
            r6 = r1
            android.content.Context r6 = r6.getContext()
            r5.<init>(r6)
            r3.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = r4
            r3 = r0
            android.widget.Button r4 = new android.widget.Button
            r12 = r4
            r4 = r12
            r5 = r12
            r6 = r1
            android.content.Context r6 = r6.getContext()
            r5.<init>(r6)
            r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = r4
            r3 = r0
            r12 = r3
            r3 = r12
            r4 = r12
            android.widget.Button r4 = r4.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            java.lang.String r5 = "+"
            r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(r4, r5)
            r3 = r0
            r12 = r3
            r3 = r12
            r4 = r12
            android.widget.Button r4 = r4.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            java.lang.String r5 = "−"
            r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(r4, r5)
            r3 = r0
            android.widget.Button r3 = r3.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            com.google.appinventor.components.runtime.view.ZoomControlView$1 r4 = new com.google.appinventor.components.runtime.view.ZoomControlView$1
            r12 = r4
            r4 = r12
            r5 = r12
            r6 = r0
            r5.<init>(r6)
            r3.setOnClickListener(r4)
            r3 = r0
            android.widget.Button r3 = r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            com.google.appinventor.components.runtime.view.ZoomControlView$2 r4 = new com.google.appinventor.components.runtime.view.ZoomControlView$2
            r12 = r4
            r4 = r12
            r5 = r12
            r6 = r0
            r5.<init>(r6)
            r3.setOnClickListener(r4)
            r3 = r0
            android.widget.Button r3 = r3.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            r4 = r0
            float r4 = r4.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ
            r1 = r4
            r4 = 1082130432(0x40800000, float:4.0)
            r5 = r1
            float r4 = r4 * r5
            int r4 = (int) r4
            r1 = r4
            android.graphics.drawable.ShapeDrawable r4 = new android.graphics.drawable.ShapeDrawable
            r12 = r4
            r4 = r12
            r5 = r12
            android.graphics.drawable.shapes.RoundRectShape r6 = new android.graphics.drawable.shapes.RoundRectShape
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = 8
            float[] r8 = new float[r8]
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 0
            r11 = r1
            float r11 = (float) r11
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 1
            r11 = r1
            float r11 = (float) r11
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 2
            r11 = r1
            float r11 = (float) r11
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 3
            r11 = r1
            float r11 = (float) r11
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 4
            r11 = 0
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 5
            r11 = 0
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 6
            r11 = 0
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 7
            r11 = 0
            r9[r10] = r11
            r9 = 0
            r10 = 0
            r7.<init>(r8, r9, r10)
            r5.<init>(r6)
            r12 = r4
            r4 = r12
            r5 = r12
            r1 = r5
            android.graphics.Paint r4 = r4.getPaint()
            r5 = -1
            r4.setColor(r5)
            r4 = r1
            com.google.appinventor.components.runtime.util.ViewUtil.setBackgroundDrawable(r3, r4)
            r3 = r0
            android.widget.Button r3 = r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r4 = r0
            float r4 = r4.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ
            r1 = r4
            r4 = 1082130432(0x40800000, float:4.0)
            r5 = r1
            float r4 = r4 * r5
            int r4 = (int) r4
            r1 = r4
            android.graphics.drawable.ShapeDrawable r4 = new android.graphics.drawable.ShapeDrawable
            r12 = r4
            r4 = r12
            r5 = r12
            android.graphics.drawable.shapes.RoundRectShape r6 = new android.graphics.drawable.shapes.RoundRectShape
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = 8
            float[] r8 = new float[r8]
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 0
            r11 = 0
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 1
            r11 = 0
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 2
            r11 = 0
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 3
            r11 = 0
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 4
            r11 = r1
            float r11 = (float) r11
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 5
            r11 = r1
            float r11 = (float) r11
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 6
            r11 = r1
            float r11 = (float) r11
            r9[r10] = r11
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 7
            r11 = r1
            float r11 = (float) r11
            r9[r10] = r11
            r9 = 0
            r10 = 0
            r7.<init>(r8, r9, r10)
            r5.<init>(r6)
            r12 = r4
            r4 = r12
            r5 = r12
            r1 = r5
            android.graphics.Paint r4 = r4.getPaint()
            r5 = -1
            r4.setColor(r5)
            r4 = r1
            com.google.appinventor.components.runtime.util.ViewUtil.setBackgroundDrawable(r3, r4)
            r3 = 2
            int[][] r3 = new int[r3][]
            r12 = r3
            r3 = r12
            r4 = r12
            r5 = 0
            r6 = 1
            int[] r6 = new int[r6]
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = 0
            r9 = -16842910(0xfffffffffefeff62, float:-1.6947497E38)
            r7[r8] = r9
            r4[r5] = r6
            r12 = r3
            r3 = r12
            r4 = r12
            r5 = 1
            r6 = 1
            int[] r6 = new int[r6]
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = 0
            r9 = 16842910(0x101009e, float:2.3694E-38)
            r7[r8] = r9
            r4[r5] = r6
            r1 = r3
            r3 = 2
            int[] r3 = new int[r3]
            r3 = {-3355444, -16777216} // fill-array
            r2 = r3
            r3 = r0
            android.widget.Button r3 = r3.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            android.content.res.ColorStateList r4 = new android.content.res.ColorStateList
            r12 = r4
            r4 = r12
            r5 = r12
            r6 = r1
            r7 = r2
            r5.<init>(r6, r7)
            r3.setTextColor(r4)
            r3 = r0
            android.widget.Button r3 = r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            android.content.res.ColorStateList r4 = new android.content.res.ColorStateList
            r12 = r4
            r4 = r12
            r5 = r12
            r6 = r1
            r7 = r2
            r5.<init>(r6, r7)
            r3.setTextColor(r4)
            r3 = r0
            r12 = r3
            r3 = r12
            r4 = r12
            android.widget.Button r4 = r4.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            r3.addView(r4)
            r3 = r0
            r12 = r3
            r3 = r12
            r4 = r12
            android.widget.Button r4 = r4.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r3.addView(r4)
            r3 = r0
            r4 = 1094713344(0x41400000, float:12.0)
            r5 = r0
            float r5 = r5.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ
            float r4 = r4 * r5
            int r4 = (int) r4
            r5 = 1094713344(0x41400000, float:12.0)
            r6 = r0
            float r6 = r6.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ
            float r5 = r5 * r6
            int r5 = (int) r5
            r6 = 0
            r7 = 0
            r3.setPadding(r4, r5, r6, r7)
            r3 = r0
            r3.updateButtons()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.view.ZoomControlView.<init>(org.osmdroid.views.MapView):void");
    }

    public final void updateButtons() {
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.setEnabled(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.canZoomIn());
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.setEnabled(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.canZoomOut());
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Button button, String str) {
        Button button2 = button;
        button2.setText(str);
        button2.setTextSize(22.0f);
        button2.setPadding(0, 0, 0, 0);
        button2.setWidth((int) (30.0f * this.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ));
        button2.setHeight((int) (30.0f * this.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ));
        button2.setSingleLine();
        button2.setGravity(17);
    }
}
