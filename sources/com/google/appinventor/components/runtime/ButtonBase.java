package com.google.appinventor.components.runtime;

import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.runtime.util.KodularHelper;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.ProgressHelper;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;

@SimpleObject
@UsesLibraries(libraries = "spinkit.jar")
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public abstract class ButtonBase extends AndroidViewComponent implements View.OnClickListener, View.OnFocusChangeListener, View.OnLongClickListener, View.OnTouchListener {
    private static final float[] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = {10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f};
    private static int mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = 0;
    private static int sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = 0;
    private boolean ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = true;
    private AlphaAnimation B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private Button f345B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private int Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = Component.COLOR_GRAY;
    private boolean PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = false;
    private int backgroundColor = Component.COLOR_DARK_GRAY;
    private Drawable backgroundImageDrawable;
    private Context context;
    private int fontTypeface = 0;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private ColorStateList f346hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Drawable f347hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private AlphaAnimation f348hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final Button f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String imagePath = "";
    private boolean moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = true;
    private int qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = 0;
    private double rotationAngle = 0.0d;
    private boolean tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = false;
    private int textColor = -1;
    private int vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = 1;
    private boolean wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0;

    public abstract void click();

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ButtonBase(com.google.appinventor.components.runtime.ComponentContainer r10) {
        /*
            r9 = this;
            r1 = r9
            r2 = r10
            r3 = r1
            r4 = r2
            r3.<init>(r4)
            r3 = r1
            r4 = 1
            r3.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = r4
            r3 = r1
            r4 = -12303292(0xffffffffff444444, float:-2.6088314E38)
            r3.backgroundColor = r4
            r3 = r1
            r4 = 0
            r3.fontTypeface = r4
            r3 = r1
            r4 = 0
            r3.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = r4
            r3 = r1
            r4 = 1
            r3.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = r4
            r3 = r1
            r4 = 0
            r3.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = r4
            r3 = r1
            r4 = -1
            r3.textColor = r4
            r3 = r1
            r4 = 0
            r3.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = r4
            r3 = r1
            r4 = -6381922(0xffffffffff9e9e9e, float:NaN)
            r3.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = r4
            r3 = r1
            java.lang.String r4 = ""
            r3.imagePath = r4
            r3 = r1
            r4 = 1
            r3.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = r4
            r3 = r1
            r4 = 0
            r3.rotationAngle = r4
            r3 = r1
            r4 = r2
            android.app.Activity r4 = r4.$context()
            r3.context = r4
            r3 = r1
            android.widget.Button r4 = new android.widget.Button
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            android.content.Context r6 = r6.context
            r5.<init>(r6)
            r3.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            r3 = r1
            android.widget.Button r3 = r3.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = 0
            r3.setAllCaps(r4)
            r3 = r1
            r8 = r3
            r3 = r8
            r4 = r8
            android.widget.Button r4 = r4.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            android.graphics.drawable.Drawable r4 = r4.getBackground()
            r3.f347hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            r3 = r1
            r8 = r3
            r3 = r8
            r4 = r8
            android.widget.Button r4 = r4.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            android.content.res.ColorStateList r4 = r4.getTextColors()
            r3.f346hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            r3 = r1
            android.widget.Button r3 = r3.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            int r3 = com.google.appinventor.components.runtime.util.KitkatUtil.getMinWidth(r3)
            sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = r3
            r3 = r1
            android.widget.Button r3 = r3.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            int r3 = com.google.appinventor.components.runtime.util.KitkatUtil.getMinHeight(r3)
            mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = r3
            r3 = r1
            r8 = r3
            r3 = r8
            r4 = r8
            android.widget.Button r4 = r4.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3.f345B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r4
            r3 = r1
            android.view.animation.AlphaAnimation r4 = new android.view.animation.AlphaAnimation
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = 1065353216(0x3f800000, float:1.0)
            r7 = 1063675494(0x3f666666, float:0.9)
            r5.<init>(r6, r7)
            r3.f348hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            r3 = r1
            android.view.animation.AlphaAnimation r3 = r3.f348hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = 350(0x15e, double:1.73E-321)
            r3.setDuration(r4)
            r3 = r1
            android.view.animation.AlphaAnimation r3 = r3.f348hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = 1
            r3.setFillAfter(r4)
            r3 = r1
            android.view.animation.AlphaAnimation r4 = new android.view.animation.AlphaAnimation
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = 1063675494(0x3f666666, float:0.9)
            r7 = 1065353216(0x3f800000, float:1.0)
            r5.<init>(r6, r7)
            r3.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r4
            r3 = r1
            android.view.animation.AlphaAnimation r3 = r3.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r4 = 350(0x15e, double:1.73E-321)
            r3.setDuration(r4)
            r3 = r1
            android.view.animation.AlphaAnimation r3 = r3.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r4 = 1
            r3.setFillAfter(r4)
            r3 = r2
            r4 = r1
            r3.$add(r4)
            r3 = r1
            android.widget.Button r3 = r3.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = r1
            r3.setOnClickListener(r4)
            r3 = r1
            android.widget.Button r3 = r3.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = r1
            r3.setOnFocusChangeListener(r4)
            r3 = r1
            android.widget.Button r3 = r3.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = r1
            r3.setOnLongClickListener(r4)
            r3 = r1
            android.widget.Button r3 = r3.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = r1
            r3.setOnTouchListener(r4)
            r3 = r1
            r4 = 1
            r3.TextAlignment(r4)
            r3 = r1
            r4 = -12303292(0xffffffffff444444, float:-2.6088314E38)
            r3.BackgroundColor(r4)
            r3 = r1
            java.lang.String r4 = ""
            r3.Image(r4)
            r3 = r1
            r4 = 1
            r3.Enabled(r4)
            r3 = r1
            android.content.Context r3 = r3.context
            com.google.appinventor.components.runtime.util.TextViewUtil.setContext(r3)
            r3 = r1
            android.widget.Button r3 = r3.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = r1
            int r4 = r4.fontTypeface
            r5 = r1
            boolean r5 = r5.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC
            r6 = r1
            boolean r6 = r6.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE
            com.google.appinventor.components.runtime.util.TextViewUtil.setFontTypeface(r3, r4, r5, r6)
            r3 = r1
            r4 = 1096810496(0x41600000, float:14.0)
            r3.FontSize(r4)
            r3 = r1
            java.lang.String r4 = ""
            r3.Text(r4)
            r3 = r1
            r4 = -1
            r3.TextColor(r4)
            r3 = r1
            r4 = 0
            r3.Shape(r4)
            r3 = r1
            r4 = -3355444(0xffffffffffcccccc, float:NaN)
            r3.TouchColor(r4)
            r3 = r1
            r4 = 0
            r3.RotationAngle(r4)
            r3 = r1
            r4 = 1
            r3.BorderShadow(r4)
            r3 = r1
            r4 = 0
            r3.FontBold(r4)
            r3 = r1
            r4 = 0
            r3.FontItalic(r4)
            r3 = r1
            r4 = 0
            r3.FontTypeface(r4)
            r3 = r1
            java.lang.String r4 = ""
            r3.FontTypefaceImport(r4)
            r3 = r1
            r4 = -1
            r3.Width(r4)
            r3 = r1
            r4 = -1
            r3.Height(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.ButtonBase.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        View view2 = view;
        MotionEvent motionEvent2 = motionEvent;
        if (Build.VERSION.SDK_INT >= 21 && ShowFeedback() && this.backgroundImageDrawable == null) {
            KodularHelper.prepareRippleDrawable(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, motionEvent2, this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB);
        }
        if (motionEvent2.getAction() == 0) {
            if (ShowFeedback()) {
                if (Build.VERSION.SDK_INT < 21) {
                    view2.startAnimation(this.f348hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                    view2.invalidate();
                } else if (this.backgroundImageDrawable != null) {
                    view2.startAnimation(this.f348hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                    view2.invalidate();
                }
            }
            try {
                TouchDown();
            } catch (Exception e) {
                int e2 = Log.e("ButtonBase", String.valueOf(e));
            }
        } else if (motionEvent2.getAction() == 1 || motionEvent2.getAction() == 3) {
            if (ShowFeedback()) {
                if (Build.VERSION.SDK_INT < 21) {
                    view2.startAnimation(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
                    view2.invalidate();
                } else if (this.backgroundImageDrawable != null) {
                    view2.startAnimation(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
                    view2.invalidate();
                }
            }
            try {
                TouchUp();
            } catch (Exception e3) {
                int e4 = Log.e("ButtonBase", String.valueOf(e3));
            }
        }
        return false;
    }

    public View getView() {
        return this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleFunction(description = "Place a blurred shadow of text underneath the text, drawn with the specified x, y, radius, color (e.g. -11, 12, 13, black.")
    public void SetShadow(float f, float f2, float f3, int i) {
        KodularHelper.setShadow(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, f, f2, f3, i);
    }

    @SimpleFunction(description = "Allows you to set animation style. Valid (case-insensitive) values are: ChasingDots, Circle, CubeGrid, DoubleBounce, FadingCircle, FoldingCube, Pulse, RotatingCircle, RotatingPlane, ThreeBounce, WanderingCubes, Wave. If invalid style is used, animation will be removed.Position can be: top, left, right, bottom. Size can be 100. ")
    public void AnimationStyle(String str, String str2, int i, int i2) {
        ProgressHelper.setButtonProgressAnimation(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str, str2, i, i2);
        this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(8);
        this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(0);
        this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "float", propertyType = "advanced")
    @SimpleProperty
    public void RotationAngle(double d) {
        this.rotationAngle = d;
        this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRotation((float) this.rotationAngle);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Sets the degrees that the button is rotated around the pivot point. Increasing values result in clockwise rotation.")
    public double RotationAngle() {
        return this.rotationAngle;
    }

    @SimpleEvent(description = "Indicates that the button was pressed down.")
    public void TouchDown() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TouchDown", new Object[0]);
    }

    @SimpleEvent(description = "Indicates that a button has been released.")
    public void TouchUp() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TouchUp", new Object[0]);
    }

    @SimpleEvent(description = "Indicates the cursor moved over the button so it is now possible to click it.")
    public void GotFocus() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotFocus", new Object[0]);
    }

    @SimpleEvent(description = "Indicates the cursor moved away from the button so it is now no longer possible to click it.")
    public void LostFocus() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LostFocus", new Object[0]);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Left, center, or right.", userVisible = false)
    public int TextAlignment() {
        return this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
    }

    @DesignerProperty(defaultValue = "1", editorType = "textalignment")
    @SimpleProperty(userVisible = false)
    public void TextAlignment(int i) {
        this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = i;
        TextViewUtil.setAlignment(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R, true);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public int Shape() {
        return this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
    }

    @DesignerProperty(defaultValue = "0", editorType = "button_shape")
    @SimpleProperty(description = "Specifies the button's shape (default, rounded, rectangular, oval). The shape will not be visible if an Image is being displayed.", userVisible = false)
    public void Shape(int i) {
        this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = i;
        updateAppearance();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Image to display on button.")
    public String Image() {
        return this.imagePath;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty(description = "Specifies the path of the button's image.  If there is both an Image and a BackgroundColor, only the Image will be visible.")
    public void Image(String str) {
        StringBuilder sb;
        String str2 = str;
        if (!str2.equals(this.imagePath) || this.backgroundImageDrawable == null) {
            this.imagePath = str2 == null ? "" : str2;
            this.backgroundImageDrawable = null;
            if (this.imagePath.length() > 0) {
                try {
                    this.backgroundImageDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), this.imagePath);
                } catch (Exception e) {
                    new StringBuilder("Unable to load ");
                    int e2 = Log.e("ButtonBase", sb.append(this.imagePath).toString());
                }
            }
            updateAppearance();
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the button's background color")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFF444444", editorType = "color")
    @SimpleProperty(description = "Specifies the button's background color. The background color will not be visible if an Image is being displayed.")
    public void BackgroundColor(int i) {
        this.backgroundColor = i;
        updateAppearance();
    }

    private void updateAppearance() {
        if (this.backgroundImageDrawable == null) {
            if (this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE == 0) {
                if (this.backgroundColor == -12303292 || this.backgroundColor == 0) {
                    this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackground(this.f347hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                } else if (this.backgroundColor == 16777215 || this.backgroundColor == 16777215) {
                    this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackground((Drawable) null);
                    this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackground(this.f347hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                    this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBackground().setAlpha(0);
                } else {
                    this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackground((Drawable) null);
                    this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackground(this.f347hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                    this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBackground().setColorFilter(this.backgroundColor, PorterDuff.Mode.SRC_ATOP);
                }
                this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
            } else {
                hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO();
            }
            TextViewUtil.setMinSize(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt, mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT);
            return;
        }
        this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackground(this.backgroundImageDrawable);
        this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
        TextViewUtil.setMinSize(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 0, 0);
    }

    private void hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO() {
        ShapeDrawable shapeDrawable;
        int i;
        Shape shape;
        Shape shape2;
        Shape shape3;
        Throwable th;
        new ShapeDrawable();
        ShapeDrawable shapeDrawable2 = shapeDrawable;
        if (this.backgroundColor == -12303292 || this.backgroundColor == 0) {
            i = Color.parseColor("#5a595b");
        } else {
            i = this.backgroundColor;
        }
        shapeDrawable2.getPaint().setColor(i);
        switch (this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE) {
            case 1:
                new RoundRectShape(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, (RectF) null, (float[]) null);
                shapeDrawable2.setShape(shape3);
                break;
            case 2:
                new RectShape();
                shapeDrawable2.setShape(shape2);
                break;
            case 3:
                new OvalShape();
                shapeDrawable2.setShape(shape);
                break;
            default:
                Throwable th2 = th;
                new IllegalArgumentException();
                throw th2;
        }
        if (Build.VERSION.SDK_INT < 21) {
            ViewUtil.setBackgroundDrawable(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, shapeDrawable2);
            setMargin();
        } else if (ShowFeedback()) {
            KodularHelper.setRippleDrawable(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, shapeDrawable2, this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB);
            setMargin();
        } else {
            ViewUtil.setBackgroundDrawable(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, shapeDrawable2);
            setMargin();
        }
    }

    public void setMargin() {
        KodularHelper.setMarginToButton(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.context, 4, 2, 4, 2);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If set, user can tap check box to cause action.")
    public boolean Enabled() {
        return this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isEnabled();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Enabled(boolean z) {
        this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setEnabled(z);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "If set, button text is displayed in bold.")
    public boolean FontBold() {
        return this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void FontBold(boolean z) {
        this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = z;
        TextViewUtil.setFontTypeface(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.fontTypeface, this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC, this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE);
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "Specifies if a visual feedback should be shown for a button that as an image as background.")
    public void ShowFeedback(boolean z) {
        boolean z2 = z;
        this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = z2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the button's visual feedback state")
    public boolean ShowFeedback() {
        return this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "If set, button text is displayed in italics.")
    public boolean FontItalic() {
        return this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void FontItalic(boolean z) {
        this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = z;
        TextViewUtil.setFontTypeface(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.fontTypeface, this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC, this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Point size for button text.")
    public float FontSize() {
        return TextViewUtil.getFontSize(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.context);
    }

    @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void FontSize(float f) {
        TextViewUtil.setFontSize(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, f);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Font family for button text.", userVisible = false)
    public int FontTypeface() {
        return this.fontTypeface;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void FontTypeface(int i) {
        this.fontTypeface = i;
        TextViewUtil.setFontTypeface(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.fontTypeface, this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC, this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Text to display on button.")
    public String Text() {
        return this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getText().toString();
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty
    public void Text(String str) {
        String str2 = str;
        if (this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0) {
            TextViewUtil.setTextHTML(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str2);
        } else {
            TextViewUtil.setText(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str2);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "If true, then this button will show html text else it will show plain text. Note: Not all HTML is supported.")
    public boolean HTMLFormat() {
        return this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void HTMLFormat(boolean z) {
        this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0 = z;
        Text(Text());
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Color for button text.")
    public int TextColor() {
        return this.textColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public void TextColor(int i) {
        int i2 = i;
        this.textColor = i2;
        if (i2 != 0) {
            this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTextColor(i2);
        } else {
            this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTextColor(this.f346hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public void FontTypefaceImport(String str) {
        String str2 = str;
        if (str2 != null) {
            TextViewUtil.setCustomFontTypeface(this.container.$form(), this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str2, this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC, this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE);
        }
    }

    @SimpleFunction(description = "Perform a button click as function.")
    public void ButtonClick() {
        boolean performClick = this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.performClick();
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color", propertyType = "advanced")
    @SimpleProperty(description = "Set the buttons touch color.")
    public void TouchColor(int i) {
        int i2 = i;
        if (i2 != 0) {
            this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = i2;
            return;
        }
        this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = Component.COLOR_LIGHT_GRAY;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the buttons touch color.")
    public int TouchColor() {
        return this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void BorderShadow(boolean z) {
        boolean z2 = z;
        if (Build.VERSION.SDK_INT < 21) {
            int i = Log.i("ButtonBase", "Can not change button border shadows property. The api level of the device is <= 21.");
        } else if (z2) {
            try {
                if (this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateListAnimator() == null) {
                    this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateListAnimator(this.f345B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getStateListAnimator());
                    this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
                    this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = true;
                }
            } catch (Exception e) {
                int e2 = Log.e("ButtonBase", String.valueOf(e));
            }
        } else {
            try {
                this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateListAnimator((StateListAnimator) null);
                this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
                this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = false;
            } catch (Exception e3) {
                int e4 = Log.e("ButtonBase", String.valueOf(e3));
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns true if the button have a outside border shadow on click.")
    public boolean BorderShadow() {
        return this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud;
    }

    public boolean longClick() {
        return false;
    }

    public void onClick(View view) {
        View view2 = view;
        click();
    }

    public void onFocusChange(View view, boolean z) {
        View view2 = view;
        if (z) {
            GotFocus();
        } else {
            LostFocus();
        }
    }

    public boolean onLongClick(View view) {
        View view2 = view;
        return longClick();
    }

    @SimpleFunction(description = "Show an image on the given position near to the button. You can use following words for the position: 'Left', 'Right', 'Top' or 'Bottom'. Use the padding to add space between the icon and text.")
    public void WithIconFromPicture(String str, String str2, int i, int i2, int i3) {
        String str3 = str;
        String str4 = str2;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        try {
            BitmapDrawable bitmapDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), str4);
            if (i5 == 0) {
                i5 = 100;
            }
            if (i6 == 0) {
                i6 = 100;
            }
            bitmapDrawable.setBounds(0, 0, i5, i6);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str3, bitmapDrawable, i4);
        } catch (Exception e) {
            int e2 = Log.e("ButtonBase", "Unable to load ".concat(String.valueOf(str4)));
        }
    }

    @SimpleFunction(description = "Show an image on the given position near to the button. You can use following words for the position: 'Left', 'Right', 'Top' or 'Bottom'. Use the padding to add space between the icon and text. Use a 'Material' icon as the button icon without uploading a image resource into your project. You can find the icon name here at https://material.io/resources/icons")
    public void WithIconFromMaterialFont(String str, String str2, int i, int i2, float f) {
        Drawable drawable;
        String str3 = str;
        String str4 = str2;
        int i3 = i;
        int i4 = i2;
        float f2 = f;
        if (f2 == 0.0f) {
            f2 = 80.0f;
        }
        int i5 = (int) f2;
        try {
            new BitmapDrawable(KodularHelper.textToBitmap(this.context, "material", str4, i3, f2));
            Drawable drawable2 = drawable;
            Drawable drawable3 = drawable2;
            Drawable drawable4 = drawable2;
            int i6 = i5;
            drawable3.setBounds(0, 0, i6, i6);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str3, drawable4, i4);
        } catch (Exception e) {
            int e2 = Log.e("ButtonBase", "Unable to load the material icon font.");
        }
    }

    @SimpleFunction(description = "Show an image on the given position near to the button. You can use following words for the position: 'Left', 'Right', 'Top' or 'Bottom'. Use the padding to add space between the icon and text. Use a 'FontAwesome' icon as the button icon without uploading a image resource into your project. You can find the icon code here at https://fontawesome.com/cheatsheet Use as example for a heart icon just 'f004' or '&#xf004;'")
    public void WithIconFromFontAwesome(String str, String str2, int i, int i2, float f) {
        Drawable drawable;
        StringBuilder sb;
        String str3 = str;
        String str4 = str2;
        int i3 = i;
        int i4 = i2;
        float f2 = f;
        if (f2 == 0.0f) {
            f2 = 80.0f;
        }
        int i5 = (int) f2;
        try {
            if (!str4.startsWith("&#x")) {
                str4 = "&#x".concat(String.valueOf(str4));
            }
            if (!str4.endsWith(";")) {
                new StringBuilder();
                str4 = sb.append(str4).append(";").toString();
            }
            new BitmapDrawable(KodularHelper.textToBitmap(this.context, "font_awesome", str4, i3, f2));
            Drawable drawable2 = drawable;
            Drawable drawable3 = drawable2;
            Drawable drawable4 = drawable2;
            int i6 = i5;
            drawable3.setBounds(0, 0, i6, i6);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f349hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str3, drawable4, i4);
        } catch (Exception e) {
            int e2 = Log.e("ButtonBase", "Unable to load the material icon font.");
        }
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Button button, String str, Drawable drawable, int i) {
        Button button2 = button;
        String str2 = str;
        Drawable drawable2 = drawable;
        int i2 = i;
        if (str2.equalsIgnoreCase("Left")) {
            button2.setCompoundDrawables(drawable2, (Drawable) null, (Drawable) null, (Drawable) null);
            button2.setCompoundDrawablePadding(i2);
        } else if (str2.equalsIgnoreCase("Top")) {
            button2.setCompoundDrawables((Drawable) null, drawable2, (Drawable) null, (Drawable) null);
            button2.setCompoundDrawablePadding(i2);
        } else if (str2.equalsIgnoreCase("Right")) {
            button2.setCompoundDrawables((Drawable) null, (Drawable) null, drawable2, (Drawable) null);
            button2.setCompoundDrawablePadding(i2);
        } else if (str2.equalsIgnoreCase("Bottom")) {
            button2.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, drawable2);
            button2.setCompoundDrawablePadding(i2);
        }
    }
}
