package com.google.appinventor.components.runtime;

import android.graphics.Rect;
import android.view.View;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.Pulse;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.github.ybq.android.spinkit.style.RotatingPlane;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.runtime.util.ProgressHelper;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "", version = 4)
@UsesLibraries(libraries = "spinkit.jar")
public final class ProgressBar extends AndroidViewComponent {
    private final android.widget.ProgressBar B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private String WrlQqhIKmKuB9b4JVzpHXmCJrSlbkHSNA5ubvsLC9K31KYZzZrethddcAVSmF8Zp = ComponentConstants.DEFAULT_PROGRESSBAR_ANIMATION;
    private int backgroundColor;
    private Rect hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Sprite f515hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = Component.COLOR_CYAN_DAK;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ProgressBar(com.google.appinventor.components.runtime.ComponentContainer r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = -16741493(0xffffffffff008b8b, float:-1.7086573E38)
            r2.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = r3
            r2 = r0
            java.lang.String r3 = "Wave"
            r2.WrlQqhIKmKuB9b4JVzpHXmCJrSlbkHSNA5ubvsLC9K31KYZzZrethddcAVSmF8Zp = r3
            r2 = r0
            android.widget.ProgressBar r3 = new android.widget.ProgressBar
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r4.<init>(r5)
            r2.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r3
            r2 = r0
            r3 = -16741493(0xffffffffff008b8b, float:-1.7086573E38)
            r2.Color(r3)
            r2 = r0
            r6 = r2
            r2 = r6
            r3 = r6
            java.lang.String r3 = r3.WrlQqhIKmKuB9b4JVzpHXmCJrSlbkHSNA5ubvsLC9K31KYZzZrethddcAVSmF8Zp
            r2.AnimationStyle(r3)
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r0
            r3 = 16777215(0xffffff, float:2.3509886E-38)
            r2.BackgroundColor(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.ProgressBar.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public final int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&H00FFFFFF", editorType = "color")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Sets the background color of this component.")
    public final void BackgroundColor(int i) {
        int i2 = i;
        this.backgroundColor = i2;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setBackgroundColor(i2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public final int Color() {
        return this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;
    }

    @DesignerProperty(defaultValue = "&HFF008b8b", editorType = "color")
    @SimpleProperty(description = "Sets the color of the component")
    public final void Color(int i) {
        int i2 = i;
        if (this.f515hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = i2;
            this.f515hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setColor(i2);
        }
    }

    @SimpleFunction(description = "Gets style names of all possible animations.")
    public final YailList GetAnimationStyleNames() {
        return YailList.makeList((List) ProgressHelper.getAnimationStyles());
    }

    @DesignerProperty(defaultValue = "Wave", editorType = "progress_options")
    @SimpleProperty(description = "Allows you to set animation style. Valid (case-insensitive) values are: ChasingDots, Circle, CubeGrid, DoubleBounce, FadingCircle, FoldingCube, Pulse, RotatingCircle, RotatingPlane, ThreeBounce, WanderingCubes, Wave. If invalid style is used, Wave animation will be used.")
    public final void AnimationStyle(String str) {
        this.f515hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = getSprite(str);
        this.f515hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.start();
        this.f515hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setColor(this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setIndeterminateDrawable(this.f515hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = this.f515hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBounds();
        }
        this.f515hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBounds(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.left, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.top, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.right, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.bottom);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.invalidateDrawable(this.f515hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
    }

    public final Sprite getSprite(String str) {
        Sprite sprite;
        Sprite sprite2;
        Sprite sprite3;
        Sprite sprite4;
        Sprite sprite5;
        Sprite sprite6;
        Sprite sprite7;
        Sprite sprite8;
        Sprite sprite9;
        Sprite sprite10;
        Sprite sprite11;
        Sprite sprite12;
        Sprite sprite13;
        String str2 = str;
        this.WrlQqhIKmKuB9b4JVzpHXmCJrSlbkHSNA5ubvsLC9K31KYZzZrethddcAVSmF8Zp = str2;
        String replaceAll = str2.toLowerCase().replaceAll("\\s", "");
        boolean z = true;
        switch (replaceAll.hashCode()) {
            case -1360216880:
                if (replaceAll.equals("circle")) {
                    z = true;
                    break;
                }
                break;
            case -817913340:
                if (replaceAll.equals("rotatingplane")) {
                    z = true;
                    break;
                }
                break;
            case -741786634:
                if (replaceAll.equals("foldingcube")) {
                    z = true;
                    break;
                }
                break;
            case 3642105:
                if (replaceAll.equals("wave")) {
                    z = true;
                    break;
                }
                break;
            case 40036904:
                if (replaceAll.equals("rotatingcircle")) {
                    z = true;
                    break;
                }
                break;
            case 105393403:
                if (replaceAll.equals("cubegrid")) {
                    z = true;
                    break;
                }
                break;
            case 107027353:
                if (replaceAll.equals("pulse")) {
                    z = true;
                    break;
                }
                break;
            case 509233141:
                if (replaceAll.equals("chasingdots")) {
                    z = false;
                    break;
                }
                break;
            case 1143631270:
                if (replaceAll.equals("threebounce")) {
                    z = true;
                    break;
                }
                break;
            case 1217522153:
                if (replaceAll.equals("wanderingcubes")) {
                    z = true;
                    break;
                }
                break;
            case 1384198729:
                if (replaceAll.equals("fadingcircle")) {
                    z = true;
                    break;
                }
                break;
            case 1471386009:
                if (replaceAll.equals("doublebounce")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                new ChasingDots();
                return sprite12;
            case true:
                new Circle();
                return sprite11;
            case true:
                new CubeGrid();
                return sprite10;
            case true:
                new DoubleBounce();
                return sprite9;
            case true:
                new FadingCircle();
                return sprite8;
            case true:
                new FoldingCube();
                return sprite7;
            case true:
                new Pulse();
                return sprite6;
            case true:
                new RotatingCircle();
                return sprite5;
            case true:
                new RotatingPlane();
                return sprite4;
            case true:
                new ThreeBounce();
                return sprite3;
            case true:
                new WanderingCubes();
                return sprite2;
            case true:
                new Wave();
                return sprite;
            default:
                this.WrlQqhIKmKuB9b4JVzpHXmCJrSlbkHSNA5ubvsLC9K31KYZzZrethddcAVSmF8Zp = ComponentConstants.DEFAULT_PROGRESSBAR_ANIMATION;
                new Wave();
                return sprite13;
        }
    }

    @SimpleProperty(description = "Gets current animation style")
    public final String AnimationStyle() {
        return this.WrlQqhIKmKuB9b4JVzpHXmCJrSlbkHSNA5ubvsLC9K31KYZzZrethddcAVSmF8Zp;
    }

    @SimpleProperty
    public final void Height(int i) {
        super.Height(i);
        moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0();
    }

    @SimpleProperty
    public final void Width(int i) {
        super.Width(i);
        moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0();
    }

    private void moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0() {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = this.f515hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBounds();
            this.f515hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBounds(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.left, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.top, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.right, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.bottom);
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.invalidateDrawable(this.f515hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        } catch (Exception e) {
        }
    }

    public final View getView() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }
}
