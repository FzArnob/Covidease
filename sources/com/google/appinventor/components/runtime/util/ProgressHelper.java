package com.google.appinventor.components.runtime.util;

import android.graphics.drawable.Drawable;
import android.widget.TextView;
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
import java.util.Arrays;
import java.util.List;

public class ProgressHelper {
    public ProgressHelper() {
    }

    public static void setButtonProgressAnimation(TextView textView, String str, String str2, int i, int i2) {
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
        TextView textView2 = textView;
        String str3 = str2;
        int i3 = i;
        int i4 = i2;
        String replaceAll = str.toLowerCase().replaceAll("\\s", "");
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
                sprite2 = sprite13;
                break;
            case true:
                new Circle();
                sprite2 = sprite12;
                break;
            case true:
                new CubeGrid();
                sprite2 = sprite11;
                break;
            case true:
                new DoubleBounce();
                sprite2 = sprite10;
                break;
            case true:
                new FadingCircle();
                sprite2 = sprite9;
                break;
            case true:
                new FoldingCube();
                sprite2 = sprite8;
                break;
            case true:
                new Pulse();
                sprite2 = sprite7;
                break;
            case true:
                new RotatingCircle();
                sprite2 = sprite6;
                break;
            case true:
                new RotatingPlane();
                sprite2 = sprite5;
                break;
            case true:
                new ThreeBounce();
                sprite2 = sprite4;
                break;
            case true:
                new WanderingCubes();
                sprite2 = sprite3;
                break;
            case true:
                new Wave();
                sprite2 = sprite;
                break;
            default:
                sprite2 = null;
                break;
        }
        if (sprite2 != null) {
            int i5 = i3;
            sprite2.setBounds(0, 0, i5, i5);
            sprite2.start();
            sprite2.setColor(i4);
        }
        String replaceAll2 = str3.toLowerCase().replaceAll("\\s", "");
        boolean z2 = true;
        switch (replaceAll2.hashCode()) {
            case -1383228885:
                if (replaceAll2.equals("bottom")) {
                    z2 = true;
                    break;
                }
                break;
            case 115029:
                if (replaceAll2.equals("top")) {
                    z2 = false;
                    break;
                }
                break;
            case 108511772:
                if (replaceAll2.equals("right")) {
                    z2 = true;
                    break;
                }
                break;
        }
        switch (z2) {
            case false:
                textView2.setCompoundDrawables((Drawable) null, sprite2, (Drawable) null, (Drawable) null);
                return;
            case true:
                textView2.setCompoundDrawables((Drawable) null, (Drawable) null, sprite2, (Drawable) null);
                return;
            case true:
                textView2.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, sprite2);
                return;
            default:
                textView2.setCompoundDrawables(sprite2, (Drawable) null, (Drawable) null, (Drawable) null);
                return;
        }
    }

    public static List<String> getAnimationStyles() {
        return Arrays.asList("ChasingDots,Circle,CubeGrid,DoubleBounce,FadingCircle,FoldingCube,Pulse,RotatingCircle,RotatingPlane,ThreeBounce,WanderingCubes,Wave".split("\\s*,\\s*"));
    }
}
