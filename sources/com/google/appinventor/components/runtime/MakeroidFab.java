package com.google.appinventor.components.runtime;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.HtmlEntities;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.kodular.fabextension.FloatingActionButtonExtension;
import org.shaded.apache.http.HttpStatus;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "write in ode", helpUrl = "https://docs.kodular.io/components/user-interface/floating-action-button/", iconName = "images/fab.png", nonVisible = true, version = 2)
@UsesLibraries(libraries = "fabextension.aar, fabextension.jar")
public class MakeroidFab extends AndroidNonvisibleComponent implements View.OnClickListener, View.OnLongClickListener, Component, OnOrientationChangeListener {
    private Drawable B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private FrameLayout.LayoutParams f480B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private String f481B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = Component.DEFAULT_VALUE_FAB_ICON_NAME;
    private float BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = 360.0f;
    private int J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = 16;
    private float KZERK4FJjgj00YJ12FkBt8g0XGe7fRlOujzm0QMQzA20gzGqez6qkZCId3aKJaes = 0.0f;
    private int MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0 = 0;
    private int YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le = -1;
    private Activity activity;
    private int bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b = 16;
    private int backgroundColor = -14575886;
    private Context context;
    private int e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT = -1;
    private Form form;
    private Bitmap hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    private FloatingActionButton hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private FloatingActionButtonExtension f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw = HttpStatus.SC_MULTIPLE_CHOICES;
    private float oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = 10.0f;
    private String pA4gj2pijkqCsrKJCWMJXnTZqsBbwNoQGf3YiS6sfVTNefcGmCrEoGjUFuLXwlZr = "";
    private boolean q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5 = true;
    private boolean seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz = true;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidFab(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 16
            r2.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b = r3
            r2 = r0
            r3 = 16
            r2.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = r3
            r2 = r0
            r3 = -14575886(0xffffffffff2196f2, float:-2.1478946E38)
            r2.backgroundColor = r3
            r2 = r0
            r3 = -1
            r2.YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le = r3
            r2 = r0
            r3 = 1092616192(0x41200000, float:10.0)
            r2.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = r3
            r2 = r0
            r3 = 1
            r2.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz = r3
            r2 = r0
            r3 = 1
            r2.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5 = r3
            r2 = r0
            r3 = 300(0x12c, float:4.2E-43)
            r2.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw = r3
            r2 = r0
            r3 = 0
            r2.KZERK4FJjgj00YJ12FkBt8g0XGe7fRlOujzm0QMQzA20gzGqez6qkZCId3aKJaes = r3
            r2 = r0
            r3 = 1135869952(0x43b40000, float:360.0)
            r2.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = r3
            r2 = r0
            r3 = 0
            r2.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0 = r3
            r2 = r0
            java.lang.String r3 = "add"
            r2.f481B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r3
            r2 = r0
            r3 = -1
            r2.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.pA4gj2pijkqCsrKJCWMJXnTZqsBbwNoQGf3YiS6sfVTNefcGmCrEoGjUFuLXwlZr = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            com.kodular.fabextension.FloatingActionButtonExtension r3 = new com.kodular.fabextension.FloatingActionButtonExtension
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            r2.Initialize()
            r2 = r1
            com.google.appinventor.components.runtime.Form r2 = r2.$form()
            r3 = r0
            r2.registerForOnOrientationChangeListener(r3)
            java.lang.String r2 = "Makeroid Fab"
            java.lang.String r3 = "Makeroid Fab Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidFab.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    private void Initialize() {
        FloatingActionButton floatingActionButton;
        FrameLayout.LayoutParams layoutParams;
        Runnable runnable;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            try {
                ViewGroup viewGroup = (ViewGroup) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getParent();
                ViewGroup viewGroup2 = viewGroup;
                if (viewGroup != null) {
                    viewGroup2.removeView(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                }
            } catch (Exception e) {
                int e2 = Log.e("Makeroid Fab", String.valueOf(e));
                return;
            }
        }
        new FloatingActionButton(this.context);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = floatingActionButton;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setSize(this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0);
        new FrameLayout.LayoutParams(-2, -2);
        this.f480B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = layoutParams;
        this.f480B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setMargins(0, 0, KodularUnitUtil.DpToPixels((Context) this.activity, this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU), KodularUnitUtil.DpToPixels((Context) this.activity, this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b));
        this.f480B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.gravity = 8388693;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setLayoutParams(this.f480B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnClickListener(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnLongClickListener(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRippleColor(this.YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundTintList(ColorStateList.valueOf(this.backgroundColor));
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCompatElevation(this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS);
        IconName(this.f481B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        if (this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setImageBitmap(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO);
        }
        if (this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setImageDrawable(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        }
        if (this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.show();
        } else {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hide();
        }
        this.activity.addContentView(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.f480B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        new Runnable(this) {
            private /* synthetic */ MakeroidFab hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                MakeroidFab.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setFloatingActionButton(MakeroidFab.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME));
            }
        };
        boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.post(runnable);
    }

    public void onClick(View view) {
        View view2 = view;
        if (this.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5) {
            F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho();
        }
        Click();
    }

    public boolean onLongClick(View view) {
        View view2 = view;
        LongClick();
        return true;
    }

    public void onOrientationChange() {
        Initialize();
        if (this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidateOnOrientationChange();
        }
    }

    public View getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleEvent(description = "Event to detect that the fab button was clicked.")
    public void Click() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that the fab button was long clicked.")
    public void LongClick() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LongClick", new Object[0]);
    }

    @DesignerProperty(defaultValue = "16", editorType = "non_negative_integer")
    @SimpleProperty
    public void MarginRight(int i) {
        int i2 = i;
        this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = i2;
        this.f480B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setMargins(0, 0, KodularUnitUtil.DpToPixels((Context) this.activity, i2), KodularUnitUtil.DpToPixels((Context) this.activity, this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b));
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setLayoutParams(this.f480B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns right margin")
    public int MarginRight() {
        return this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU;
    }

    @DesignerProperty(defaultValue = "16", editorType = "non_negative_integer")
    @SimpleProperty
    public void MarginBottom(int i) {
        int i2 = i;
        this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b = i2;
        this.f480B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setMargins(0, 0, KodularUnitUtil.DpToPixels((Context) this.activity, this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU), KodularUnitUtil.DpToPixels((Context) this.activity, i2));
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setLayoutParams(this.f480B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the fab's bottom margin.")
    public int MarginBottom() {
        return this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b;
    }

    @DesignerProperty(defaultValue = "1", editorType = "size")
    @SimpleProperty(description = "Sets the size of the button. Use '1' for normal, '2' for mini or '3' for auto size based on the screen size.")
    public void Size(int i) {
        int i2 = i;
        if (i2 == 1) {
            this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0 = 0;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setSize(0);
        } else if (i2 == 2) {
            this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0 = 1;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setSize(1);
        } else {
            this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0 = -1;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setSize(-1);
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int Size() {
        return this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0;
    }

    @DesignerProperty(defaultValue = "&HFF2196F2", editorType = "color")
    @SimpleProperty
    public void BackgroundColor(int i) {
        int i2 = i;
        this.backgroundColor = i2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundTintList(ColorStateList.valueOf(i2));
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the fab's background color.")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color", propertyType = "advanced")
    @SimpleProperty
    public void RippleColor(int i) {
        int i2 = i;
        this.YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le = i2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRippleColor(i2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the fab's ripple color.")
    public int RippleColor() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getRippleColor();
    }

    @DesignerProperty(defaultValue = "10", editorType = "non_negative_float")
    @SimpleProperty
    public void Elevation(float f) {
        this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = f;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCompatElevation(KodularUnitUtil.DpToPixels(this.context, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS));
    }

    @SimpleProperty
    public float Elevation() {
        return this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Visible(boolean z) {
        boolean z2 = z;
        if (z2) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.show();
        } else {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hide();
        }
        this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns true if the fab button is visible.")
    public boolean Visible() {
        return this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty
    public void RotationOnClick(boolean z) {
        boolean z2 = z;
        this.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5 = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns true if the fab will rotate on click.")
    public boolean RotationOnClick() {
        return this.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5;
    }

    @DesignerProperty(defaultValue = "300", editorType = "integer", propertyType = "advanced")
    @SimpleProperty
    public void RotationDuration(int i) {
        int i2 = i;
        this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw = i2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the fab's rotation duration in milliseconds.")
    public int RotationDuration() {
        return this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw;
    }

    @DesignerProperty(defaultValue = "0", editorType = "float", propertyType = "advanced")
    @SimpleProperty
    public void RotationStartDegrees(float f) {
        float f2 = f;
        this.KZERK4FJjgj00YJ12FkBt8g0XGe7fRlOujzm0QMQzA20gzGqez6qkZCId3aKJaes = f2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the fab's rotation start degrees.")
    public float RotationStartDegrees() {
        return this.KZERK4FJjgj00YJ12FkBt8g0XGe7fRlOujzm0QMQzA20gzGqez6qkZCId3aKJaes;
    }

    @DesignerProperty(defaultValue = "360", editorType = "float", propertyType = "advanced")
    @SimpleProperty
    public void RotationEndDegrees(float f) {
        float f2 = f;
        this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = f2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the fab's rotation end degrees.")
    public float RotationEndDegrees() {
        return this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS;
    }

    @SimpleFunction(description = "Starts a rotation animation. You can use the 'Rotation Duration','Rotation Start Degrees' and 'Rotation End Degrees' to define the animation. This block will work too if the 'Rotation On Click' property is disabled.")
    public void StartRotationAnimation() {
        F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho();
    }

    @SimpleFunction(description = "Shows the fab button.")
    public void Show() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.show();
        this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz = true;
    }

    @SimpleFunction(description = "Hides the fab button.")
    public void Hide() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hide();
        this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz = false;
    }

    @DesignerProperty(editorType = "image_asset")
    @SimpleProperty
    public void Icon(String str) {
        String str2 = str;
        if (str2 != null && !str2.isEmpty()) {
            try {
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = MediaUtil.getBitmapDrawable(this.form, str2);
                this.pA4gj2pijkqCsrKJCWMJXnTZqsBbwNoQGf3YiS6sfVTNefcGmCrEoGjUFuLXwlZr = str2;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setImageDrawable(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
            } catch (Exception e) {
                int e2 = Log.e("Makeroid Fab", "Unable to load ".concat(String.valueOf(str2)));
                this.pA4gj2pijkqCsrKJCWMJXnTZqsBbwNoQGf3YiS6sfVTNefcGmCrEoGjUFuLXwlZr = "";
            }
        }
    }

    @SimpleProperty(description = "The path for the used image in FAB.")
    public String Icon() {
        return this.pA4gj2pijkqCsrKJCWMJXnTZqsBbwNoQGf3YiS6sfVTNefcGmCrEoGjUFuLXwlZr;
    }

    @DesignerProperty(defaultValue = "add", editorType = "string")
    @SimpleProperty
    public void IconName(String str) {
        this.f481B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = str;
        KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Use a 'Material' icon for the fab without uploading a image resource into your project. You can find the icon name here at https://material.io/resources/icons")
    public String IconName() {
        return this.f481B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public void IconColor(int i) {
        this.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT = i;
        KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH();
    }

    @SimpleProperty(description = "The color for the material icon.")
    public int IconColor() {
        return this.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT;
    }

    private Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, int i) {
        Paint paint;
        Canvas canvas;
        new Paint(1);
        Paint paint2 = paint;
        Paint paint3 = paint2;
        paint2.setTextSize(75.0f);
        paint3.setColor(i);
        paint3.setTextAlign(Paint.Align.CENTER);
        TextViewUtil.setContext(this.context);
        TextViewUtil.setFontTypefaceCanvas(paint3, 7, false, false);
        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = Bitmap.createBitmap(KodularUnitUtil.DpToPixels((Context) this.activity, 30), KodularUnitUtil.DpToPixels((Context) this.activity, 30), Bitmap.Config.ARGB_8888);
        new Canvas(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO);
        Canvas canvas2 = canvas;
        Canvas canvas3 = canvas2;
        canvas3.drawText(HtmlEntities.decodeHtmlText(str), (float) (canvas2.getWidth() / 2), (float) ((int) (((float) (canvas3.getHeight() / 2)) - ((paint3.descent() + paint3.ascent()) / 2.0f))), paint3);
        return this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    }

    private void KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH() {
        if (!this.f481B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.isEmpty()) {
            try {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setImageBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f481B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, this.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT));
            } catch (Exception e) {
                int e2 = Log.e("Makeroid Fab", String.valueOf(e));
            }
        }
    }

    private void F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho() {
        FloatingActionButton floatingActionButton = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        float[] fArr = new float[2];
        fArr[0] = this.KZERK4FJjgj00YJ12FkBt8g0XGe7fRlOujzm0QMQzA20gzGqez6qkZCId3aKJaes;
        float[] fArr2 = fArr;
        fArr2[1] = this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS;
        ObjectAnimator.ofFloat(floatingActionButton, "rotation", fArr2).setDuration((long) this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw).start();
    }

    @SimpleFunction(description = "Show a new text message near to the fab with the given properties. You can do the changes with the properties in the advanded category.")
    public void ShowTextMessage() {
        this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showText();
    }

    @SimpleFunction(description = "Hides text message.")
    public void HideTextMessage() {
        this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hideText();
    }

    @DesignerProperty(editorType = "string", propertyType = "advanced")
    @SimpleProperty(description = "Set the text message.")
    public void TextMessageText(String str) {
        this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setText(str);
    }

    @SimpleProperty(description = "Get the text message.")
    public String TextMessageText() {
        return this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getText();
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color", propertyType = "advanced")
    @SimpleProperty(description = "Set the text message color.")
    public void TextMessageTextColor(int i) {
        this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTextColor(i);
    }

    @SimpleProperty(description = "Get the text message color.")
    public int TextMessageTextColor() {
        return this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getTextColor();
    }

    @DesignerProperty(defaultValue = "&HFF444444", editorType = "color", propertyType = "advanced")
    @SimpleProperty(description = "Set the text message background color.")
    public void TextMessageBackgroundColor(int i) {
        this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(i);
    }

    @SimpleProperty(description = "Get the text message background color.")
    public int TextMessageBackgroundColor() {
        return this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBackgroundColor();
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_float", propertyType = "advanced")
    @SimpleProperty(description = "Set the text message corner radius.")
    public void TextMessageCornerRadius(float f) {
        float f2 = f;
        this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.cornerRadius = f2;
    }

    @SimpleProperty(description = "Get the text message corner radius.")
    public float TextMessageCornerRadius() {
        return this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.cornerRadius;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "Set the text message to the left side of the floating button. If false the text will be on the right side.")
    public void ShowTextMessageOnLeftSide(boolean z) {
        this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setShowTextOnLeftSide(z);
    }

    @SimpleProperty(description = "Return true if the text message is on the left side.")
    public boolean ShowTextMessageOnLeftSide() {
        return this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isShowTextOnLeftSide();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "Hide the text message when clicking on the message")
    public void HideTextMessageOnTextClick(boolean z) {
        this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setHideTextOnClick(z);
    }

    @SimpleProperty(description = "Returns true if the text will hide when clicking on it.")
    public boolean HideTextMessageOnTextClick() {
        return this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isHideTextOnClick();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "Hide the text message after a long click on the text message.")
    public void HideTextMessageOnTextLongClick(boolean z) {
        this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setHideTextOnLongClick(z);
    }

    @SimpleProperty(description = "Returns true if the text will go hidden after a long click on it.")
    public boolean HideTextMessageOnTextLongClick() {
        return this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isHideTextOnLongClick();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "If true the FAB click listener will be invoked on a text message click.")
    public void CallFabClickOnTextMessageClick(boolean z) {
        this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCallFabClickOnTextClick(z);
    }

    @SimpleProperty(description = "Returns true if the FAB click listener will be invoked on a text click.")
    public boolean CallFabClickOnTextMessageClick() {
        return this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isCallFabClickOnTextClick();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "If true the FAB long click listener will be invoked on a text message long click.")
    public void CallFabLongClickOnTextMessageLongClick(boolean z) {
        this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCallFabLongClickOnTextLongClick(z);
    }

    @SimpleProperty(description = "Returns true if the FAB long click listener will be invoked on a text long click.")
    public boolean CallFabLongClickOnTextMessageLongClick() {
        return this.f482hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isCallFabLongClickOnTextLongClick();
    }
}
