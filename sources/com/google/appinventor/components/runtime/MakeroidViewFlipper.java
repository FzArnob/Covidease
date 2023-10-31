package com.google.appinventor.components.runtime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.YailList;

@SimpleObject
@DesignerComponent(category = ComponentCategory.VIEWS, description = "", iconName = "images/viewFlipper.png", version = 1)
public class MakeroidViewFlipper extends AndroidViewComponent implements View.OnTouchListener {
    private int backgroundColor;
    private ComponentContainer container;
    private Context context;
    private ViewFlipper hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int interval;
    private boolean swipeable;

    /* JADX WARNING: Illegal instructions before constructor call */
    @android.annotation.SuppressLint({"ClickableViewAccessibility"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidViewFlipper(com.google.appinventor.components.runtime.ComponentContainer r11) {
        /*
            r10 = this;
            r1 = r10
            r2 = r11
            r5 = r1
            r6 = r2
            com.google.appinventor.components.runtime.Form r6 = r6.$form()
            r5.<init>(r6)
            r5 = r1
            r6 = r2
            r5.container = r6
            r5 = r1
            r6 = r2
            android.app.Activity r6 = r6.$context()
            r5.context = r6
            r5 = r1
            android.widget.ViewFlipper r6 = new android.widget.ViewFlipper
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = r1
            android.content.Context r8 = r8.context
            r7.<init>(r8)
            r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6
            r5 = r1
            android.widget.ViewFlipper r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = r1
            r5.setOnTouchListener(r6)
            r5 = r1
            android.content.Context r5 = r5.context
            r6 = 17432576(0x10a0000, float:2.5346597E-38)
            android.view.animation.Animation r5 = android.view.animation.AnimationUtils.loadAnimation(r5, r6)
            r3 = r5
            r5 = r1
            android.content.Context r5 = r5.context
            r6 = 17432577(0x10a0001, float:2.53466E-38)
            android.view.animation.Animation r5 = android.view.animation.AnimationUtils.loadAnimation(r5, r6)
            r4 = r5
            r5 = r3
            r6 = 200(0xc8, double:9.9E-322)
            r5.setDuration(r6)
            r5 = r4
            r6 = 200(0xc8, double:9.9E-322)
            r5.setDuration(r6)
            r5 = r1
            android.widget.ViewFlipper r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = r3
            r5.setInAnimation(r6)
            r5 = r1
            android.widget.ViewFlipper r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = r4
            r5.setOutAnimation(r6)
            r5 = r2
            r6 = r1
            r5.$add(r6)
            r5 = r1
            java.lang.String r6 = ""
            r5.AddImagesFromString(r6)
            r5 = r1
            r6 = 0
            r5.BackgroundColor(r6)
            r5 = r1
            r6 = 1000(0x3e8, float:1.401E-42)
            r5.FlipInterval(r6)
            r5 = r1
            r6 = 1
            r5.Swipeable(r6)
            java.lang.String r5 = "Makeroid View Flipper"
            java.lang.String r6 = "Makeroid View Flipper Created"
            int r5 = android.util.Log.d(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidViewFlipper.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public ViewFlipper getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleProperty
    public void Width(int i) {
        int i2 = i;
        if (i2 == -1) {
            i2 = -2;
        }
        super.Width(i2);
    }

    @SimpleProperty
    public void Height(int i) {
        int i2 = i;
        if (i2 == -1) {
            i2 = -2;
        }
        super.Height(i2);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        View view2 = view;
        MotionEvent motionEvent2 = motionEvent;
        switch (motionEvent2.getActionMasked()) {
            case 1:
                float x = motionEvent2.getX();
                if (0.0f < x) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showNext();
                }
                if (0.0f > x) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showPrevious();
                    break;
                }
                break;
        }
        return this.swipeable;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If set to true you can swipe with your fingers through views.")
    public void Swipeable(boolean z) {
        boolean z2 = z;
        this.swipeable = z2;
    }

    @SimpleProperty
    public boolean Swipeable() {
        return this.swipeable;
    }

    @DesignerProperty(defaultValue = "1000", editorType = "non_negative_integer")
    @SimpleProperty(description = "How long to wait before flipping to the next view in milliseconds.")
    public void FlipInterval(int i) {
        int i2 = i;
        this.interval = i2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setFlipInterval(i2);
    }

    @SimpleProperty
    public int FlipInterval() {
        return this.interval;
    }

    @SimpleFunction(description = "Start a timer to cycle through child views.")
    public void StartFlipping() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.startFlipping();
    }

    @SimpleFunction(description = "No more flips.")
    public void StopFlipping() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stopFlipping();
    }

    @SimpleFunction(description = "Show the previous view.")
    public void ShowPrevious() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showPrevious();
    }

    @SimpleFunction(description = "Show the next view.")
    public void ShowNext() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showNext();
    }

    @SimpleFunction(description = "Returns true if the child views are flipping.")
    public void isFlipping() {
        boolean isFlipping = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isFlipping();
    }

    @SimpleFunction(description = "Add a component to the view flipper. The first added component will be the first visible component on the screen.")
    public void AddComponentToView(AndroidViewComponent androidViewComponent) {
        try {
            View view = androidViewComponent.getView();
            View view2 = view;
            ((ViewGroup) view.getParent()).removeView(view2);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addView(view2);
        } catch (Exception e) {
            int e2 = Log.e("Makeroid View Flipper", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Use this function if you try to create a image view flipper. Please use a 'make a list' block.")
    public void AddImagesToView(YailList yailList) {
        ViewGroup.LayoutParams layoutParams;
        StringBuilder sb;
        ImageView imageView;
        YailList yailList2 = yailList;
        String[] stringArray = yailList2.toStringArray();
        new LinearLayout.LayoutParams(-1, -1);
        ViewGroup.LayoutParams layoutParams2 = layoutParams;
        int i = 0;
        while (i < yailList2.size()) {
            try {
                new ImageView(this.context);
                ImageView imageView2 = imageView;
                imageView2.setImageDrawable(MediaUtil.getBitmapDrawable(this.container.$form(), stringArray[i]));
                imageView2.setAdjustViewBounds(true);
                imageView2.setLayoutParams(layoutParams2);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addView(imageView2);
                i++;
            } catch (Exception e) {
                new StringBuilder("Unable to load ");
                int e2 = Log.e("Makeroid View Flipper", sb.append(stringArray[i]).toString());
                return;
            }
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "textArea")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Use this function if you try to create a image view flipper. Use images separated by commas such as: Image1.png,Image2.png,Image3.png.")
    public void AddImagesFromString(String str) {
        String str2 = str;
        if (str2.length() > 0) {
            AddImagesToView(YailList.makeList((Object[]) str2.split(" *, *")));
        }
    }

    @DesignerProperty(defaultValue = "&H00000000", editorType = "color")
    @SimpleProperty(description = "Specifies the view flippers background color.")
    public void BackgroundColor(int i) {
        int i2 = i;
        this.backgroundColor = i2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(i2);
    }

    @SimpleProperty
    public int BackgroundColor() {
        return this.backgroundColor;
    }
}
