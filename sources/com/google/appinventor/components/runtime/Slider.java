package com.google.appinventor.components.runtime;

import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MediaUtil;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "A Slider is a progress bar that adds a draggable thumb. You can touch the thumb and drag left or right to set the slider thumb position. As the Slider thumb is dragged, it will trigger the PositionChanged event, reporting the position of the Slider thumb. The reported position of the Slider thumb can be used to dynamically update another component attribute, such as the font size of a TextBox or the radius of a Ball.", version = 5)
public class Slider extends AndroidViewComponent implements SeekBar.OnSeekBarChangeListener {
    private LayerDrawable B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private float EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB;
    private int HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l = Component.COLOR_CYAN;
    private int R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = Component.COLOR_GRAY;
    private float W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa;
    /* access modifiers changed from: private */
    public boolean ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI;
    private int dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw = Component.COLOR_CYAN;
    private ClipDrawable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final SeekBar f524hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private float kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA;
    public final boolean referenceGetThumb;
    private double rotationAngle = 0.0d;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Slider(com.google.appinventor.components.runtime.ComponentContainer r11) {
        /*
            r10 = this;
            r1 = r10
            r2 = r11
            r3 = r1
            r4 = r2
            r3.<init>(r4)
            r3 = r1
            r4 = -16728877(0xffffffffff00bcd3, float:-1.7112161E38)
            r3.HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l = r4
            r3 = r1
            r4 = -16728877(0xffffffffff00bcd3, float:-1.7112161E38)
            r3.dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw = r4
            r3 = r1
            r4 = -6381922(0xffffffffff9e9e9e, float:NaN)
            r3.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = r4
            r3 = r1
            r4 = 0
            r3.rotationAngle = r4
            r3 = r1
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 16
            if (r4 < r5) goto L_0x00b2
            r4 = 1
        L_0x0026:
            r3.referenceGetThumb = r4
            r3 = r1
            android.widget.SeekBar r4 = new android.widget.SeekBar
            r9 = r4
            r4 = r9
            r5 = r9
            r6 = r2
            android.app.Activity r6 = r6.$context()
            r5.<init>(r6)
            r3.f524hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            r3 = r1
            r9 = r3
            r3 = r9
            r4 = r9
            android.widget.SeekBar r4 = r4.f524hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            android.graphics.drawable.Drawable r4 = r4.getProgressDrawable()
            android.graphics.drawable.Drawable r4 = r4.getCurrent()
            android.graphics.drawable.LayerDrawable r4 = (android.graphics.drawable.LayerDrawable) r4
            r3.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r4
            r3 = r1
            android.graphics.drawable.LayerDrawable r3 = r3.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r4 = 16908301(0x102000d, float:2.3877265E-38)
            android.graphics.drawable.Drawable r3 = r3.findDrawableByLayerId(r4)
            boolean r3 = r3 instanceof android.graphics.drawable.ScaleDrawable
            if (r3 == 0) goto L_0x00b5
            r3 = r1
            android.graphics.drawable.ClipDrawable r4 = new android.graphics.drawable.ClipDrawable
            r9 = r4
            r4 = r9
            r5 = r9
            r6 = r1
            android.graphics.drawable.LayerDrawable r6 = r6.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r7 = 16908301(0x102000d, float:2.3877265E-38)
            android.graphics.drawable.Drawable r6 = r6.findDrawableByLayerId(r7)
            android.graphics.drawable.ScaleDrawable r6 = (android.graphics.drawable.ScaleDrawable) r6
            android.graphics.drawable.Drawable r6 = r6.getDrawable()
            r7 = 3
            r8 = 1
            r5.<init>(r6, r7, r8)
            r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
        L_0x0075:
            r3 = r1
            r3.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud()
            r3 = r2
            r4 = r1
            r3.$add(r4)
            r3 = r1
            r4 = 1092616192(0x41200000, float:10.0)
            r3.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa = r4
            r3 = r1
            r4 = 1112014848(0x42480000, float:50.0)
            r3.kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA = r4
            r3 = r1
            r4 = 1106247680(0x41f00000, float:30.0)
            r3.EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB = r4
            r3 = r1
            r4 = 1
            r3.ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI = r4
            r3 = r1
            android.widget.SeekBar r3 = r3.f524hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = r1
            r3.setOnSeekBarChangeListener(r4)
            r3 = r1
            android.widget.SeekBar r3 = r3.f524hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4 = 100
            r3.setMax(r4)
            r3 = r1
            r4 = -16728877(0xffffffffff00bcd3, float:-1.7112161E38)
            r3.ThumbColor(r4)
            r3 = r1
            r4 = 0
            r3.RotationAngle(r4)
            r3 = r1
            r3.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0()
            return
        L_0x00b2:
            r4 = 0
            goto L_0x0026
        L_0x00b5:
            r3 = r1
            r9 = r3
            r3 = r9
            r4 = r9
            android.graphics.drawable.LayerDrawable r4 = r4.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r5 = 16908301(0x102000d, float:2.3877265E-38)
            android.graphics.drawable.Drawable r4 = r4.findDrawableByLayerId(r5)
            android.graphics.drawable.ClipDrawable r4 = (android.graphics.drawable.ClipDrawable) r4
            r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
            goto L_0x0075
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Slider.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    private void ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud() {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setColorFilter(this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe, PorterDuff.Mode.SRC);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setColorFilter(this.HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l, PorterDuff.Mode.SRC);
    }

    private void wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0() {
        this.f524hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setProgress((int) (((this.EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB - this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa) / (this.kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA - this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa)) * 100.0f));
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "float", propertyType = "advanced")
    @SimpleProperty
    public void RotationAngle(double d) {
        double d2 = d;
        this.rotationAngle = d2;
        this.f524hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRotation((float) d2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Sets the degrees that the slider is rotated around the pivot point. Increasing values result in clockwise rotation.")
    public double RotationAngle() {
        return this.rotationAngle;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Sets whether or not to display the slider thumb.", userVisible = true)
    public void ThumbEnabled(boolean z) {
        View.OnTouchListener onTouchListener;
        this.ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI = z;
        new View.OnTouchListener(this) {
            private /* synthetic */ Slider hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final boolean onTouch(View view, MotionEvent motionEvent) {
                View view2 = view;
                MotionEvent motionEvent2 = motionEvent;
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI) {
                    return false;
                }
                return true;
            }
        };
        this.f524hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnTouchListener(onTouchListener);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns whether or not the slider thumb is being be shown", userVisible = true)
    public boolean ThumbEnabled() {
        return this.ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI;
    }

    @DesignerProperty(defaultValue = "30.0", editorType = "float")
    @SimpleProperty(description = "Sets the position of the slider thumb. If this value is greater than MaxValue, then it will be set to same value as MaxValue. If this value is less than MinValue, then it will be set to same value as MinValue.", userVisible = true)
    public void ThumbPosition(float f) {
        this.EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB = Math.max(Math.min(f, this.kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA), this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa);
        wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0();
        PositionChanged(this.EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the position of slider thumb", userVisible = true)
    public float ThumbPosition() {
        return this.EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB;
    }

    @DesignerProperty(defaultValue = "10.0", editorType = "float")
    @SimpleProperty(description = "Sets the minimum value of slider.  Changing the minimum value also resets Thumbposition to be halfway between the (new) minimum and the maximum. If the new minimum is greater than the current maximum, then minimum and maximum will both be set to this value.  Setting MinValue resets the thumb position to halfway between MinValue and MaxValue and signals the PositionChanged event.", userVisible = true)
    public void MinValue(float f) {
        float f2 = f;
        this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa = f2;
        this.kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA = Math.max(f2, this.kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA);
        ThumbPosition((this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa + this.kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA) / 2.0f);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the value of slider min value.", userVisible = true)
    public float MinValue() {
        return this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa;
    }

    @DesignerProperty(defaultValue = "50.0", editorType = "float")
    @SimpleProperty(description = "Sets the maximum value of slider.  Changing the maximum value also resets Thumbposition to be halfway between the minimum and the (new) maximum. If the new maximum is less than the current minimum, then minimum and maximum will both be set to this value.  Setting MaxValue resets the thumb position to halfway between MinValue and MaxValue and signals the PositionChanged event.", userVisible = true)
    public void MaxValue(float f) {
        float f2 = f;
        this.kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA = f2;
        this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa = Math.min(f2, this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa);
        ThumbPosition((this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa + this.kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA) / 2.0f);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the slider max value.", userVisible = true)
    public float MaxValue() {
        return this.kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of slider to the left of the thumb.")
    public int ColorLeft() {
        return this.HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l;
    }

    @DesignerProperty(defaultValue = "&HFF00BCD3", editorType = "color")
    @SimpleProperty
    public void ColorLeft(int i) {
        this.HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l = i;
        ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of slider to the left of the thumb.")
    public int ColorRight() {
        return this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe;
    }

    @DesignerProperty(defaultValue = "&HFF9E9E9E", editorType = "color")
    @SimpleProperty
    public void ColorRight(int i) {
        this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = i;
        ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud();
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void ThumbImage(String str) {
        StringBuilder sb;
        try {
            BitmapDrawable bitmapDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), str);
            BitmapDrawable bitmapDrawable2 = bitmapDrawable;
            if (bitmapDrawable != null) {
                this.f524hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setThumb(bitmapDrawable2);
            }
        } catch (Exception e) {
            new StringBuilder();
            int d = Log.d("Slider", sb.append(e.getMessage()).toString());
        }
    }

    @DesignerProperty(defaultValue = "&HFF00BCD3", editorType = "color")
    @SimpleProperty(description = "The color of slider thumb. This block works only on devices with api >= 16.")
    public void ThumbColor(int i) {
        int i2 = i;
        this.dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw = i2;
        if (this.referenceGetThumb) {
            this.f524hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getThumb().mutate().setColorFilter(i2, PorterDuff.Mode.SRC_IN);
        } else {
            int d = Log.d("Slider", "The api level of the device is below 16. To use this option you need api >= 16.");
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Return the color of slider thumb.")
    public int ThumbColor() {
        return this.dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw;
    }

    public View getView() {
        return this.f524hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        SeekBar seekBar2 = seekBar;
        boolean z2 = z;
        this.EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB = (((this.kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA - this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa) * ((float) i)) / 100.0f) + this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa;
        PositionChanged(this.EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB);
    }

    @SimpleEvent
    public void PositionChanged(float f) {
        EventDispatcher.dispatchEvent(this.f524hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this, "PositionChanged", Float.valueOf(f));
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        SeekBar seekBar2 = seekBar;
        TouchDown();
    }

    @SimpleEvent(description = "Event will be invoked on a touch down.")
    public void TouchDown() {
        EventDispatcher.dispatchEvent(this.f524hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this, "TouchDown", new Object[0]);
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        SeekBar seekBar2 = seekBar;
        TouchUp();
    }

    @SimpleEvent(description = "Event will be invoked on a touch up.")
    public void TouchUp() {
        EventDispatcher.dispatchEvent(this.f524hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this, "TouchUp", new Object[0]);
    }

    public int Height() {
        return getView().getHeight();
    }

    public void Height(int i) {
        this.container.setChildHeight(this, i);
    }

    public void HeightPercent(int i) {
    }
}
