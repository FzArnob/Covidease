package com.google.appinventor.components.runtime;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", helpUrl = "https://docs.kodular.io/components/drawing-and-animation/animation-util/", iconName = "images/animationUtil.png", nonVisible = true, version = 1)
public class MakeroidAnimationUtilities extends AndroidNonvisibleComponent implements Component {
    private float J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = 0.0f;
    private Context context;
    private boolean dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = false;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidAnimationUtilities(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = r3
            r2 = r0
            r3 = 0
            r2.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            java.lang.String r2 = "Makeroid Animation Utilities"
            java.lang.String r3 = "Makeroid Animation Utilities Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidAnimationUtilities.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleEvent(description = "This event is triggered when there was a error catched. Possible values for the error code and method: 1 'GetLeftPosition', 2 'GetTopPosition', 3 'GetRightPosition', 4 'GetBottomPosition', 5 'GetXPosition', 6 'GetYPosition', 7 'Rotation', 8 'BounceHorizontal', 9 'BounceVertical', 10 'OvershootHorizontal', 11 'OvershootVertical', 12 'Zoom'. The error message will return you the error reason.")
    public void Error(int i, String str, String str2) {
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        Object[] objArr3 = objArr2;
        objArr3[2] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Error", objArr3);
    }

    @SimpleFunction(description = "Returns the left position of a component. It will return '-9999' if there was a error.")
    public int GetLeftPosition(AndroidViewComponent androidViewComponent) {
        StringBuilder sb;
        StringBuilder sb2;
        try {
            return androidViewComponent.getView().getLeft();
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            int e2 = Log.e("Makeroid Animation Utilities", sb.append(exc.getMessage()).toString());
            new StringBuilder();
            Error(1, sb2.append(exc.getMessage()).toString(), "GetLeftPosition");
            return -9999;
        }
    }

    @SimpleFunction(description = "Returns the top position of a component. It will return '-9999' if there was a error.")
    public int GetTopPosition(AndroidViewComponent androidViewComponent) {
        StringBuilder sb;
        StringBuilder sb2;
        try {
            return androidViewComponent.getView().getTop();
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            int e2 = Log.e("Makeroid Animation Utilities", sb.append(exc.getMessage()).toString());
            new StringBuilder();
            Error(2, sb2.append(exc.getMessage()).toString(), "GetTopPosition");
            return -9999;
        }
    }

    @SimpleFunction(description = "Returns the right position of a component. It will return '-9999' if there was a error.")
    public int GetRightPosition(AndroidViewComponent androidViewComponent) {
        StringBuilder sb;
        StringBuilder sb2;
        try {
            return androidViewComponent.getView().getRight();
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            int e2 = Log.e("Makeroid Animation Utilities", sb.append(exc.getMessage()).toString());
            new StringBuilder();
            Error(3, sb2.append(exc.getMessage()).toString(), "GetRightPosition");
            return -9999;
        }
    }

    @SimpleFunction(description = "Returns the bottom position of a component. It will return '-9999' if there was a error.")
    public int GetBottomPosition(AndroidViewComponent androidViewComponent) {
        StringBuilder sb;
        StringBuilder sb2;
        try {
            return androidViewComponent.getView().getBottom();
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            int e2 = Log.e("Makeroid Animation Utilities", sb.append(exc.getMessage()).toString());
            new StringBuilder();
            Error(4, sb2.append(exc.getMessage()).toString(), "GetBottomPosition");
            return -9999;
        }
    }

    @SimpleFunction(description = "Returns the x position of a component. It will return '-9999' if there was a error.")
    public int GetXPosition(AndroidViewComponent androidViewComponent) {
        StringBuilder sb;
        StringBuilder sb2;
        try {
            return (int) androidViewComponent.getView().getX();
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            int e2 = Log.e("Makeroid Animation Utilities", sb.append(exc.getMessage()).toString());
            new StringBuilder();
            Error(5, sb2.append(exc.getMessage()).toString(), "GetXPosition");
            return -9999;
        }
    }

    @SimpleFunction(description = "Returns the y position of a component. It will return '-9999' if there was a error.")
    public int GetYPosition(AndroidViewComponent androidViewComponent) {
        StringBuilder sb;
        StringBuilder sb2;
        try {
            return (int) androidViewComponent.getView().getY();
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            int e2 = Log.e("Makeroid Animation Utilities", sb.append(exc.getMessage()).toString());
            new StringBuilder();
            Error(6, sb2.append(exc.getMessage()).toString(), "GetYPosition");
            return -9999;
        }
    }

    @SimpleFunction(description = "Start a rotation on any component. Use as example in 'rotation Start Degrees' 0, in 'rotation End Degrees' 360 and in 'duration' 300 (millisecond) to run a clockwise, 360 degress animation. You can also use negative numbers for the degress.")
    public void Rotation(AndroidViewComponent androidViewComponent, float f, float f2, int i) {
        StringBuilder sb;
        StringBuilder sb2;
        float f3 = f;
        float f4 = f2;
        int i2 = i;
        try {
            View view = androidViewComponent.getView();
            this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = false;
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, "rotation", f3, f4, i2);
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            int e2 = Log.e("Makeroid Animation Utilities", sb.append(exc.getMessage()).toString());
            new StringBuilder();
            Error(7, sb2.append(exc.getMessage()).toString(), "Rotation");
        }
    }

    @SimpleFunction(description = "Start a horizontal bounce animation. The duration is set in millisecond. Use as example for 1 second '1000'.")
    public void BounceHorizontal(AndroidViewComponent androidViewComponent, float f, float f2, int i) {
        StringBuilder sb;
        StringBuilder sb2;
        float f3 = f;
        float f4 = f2;
        int i2 = i;
        try {
            View view = androidViewComponent.getView();
            this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = true;
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, "translationX", f3, f4, i2);
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            int e2 = Log.e("Makeroid Animation Utilities", sb.append(exc.getMessage()).toString());
            new StringBuilder();
            Error(8, sb2.append(exc.getMessage()).toString(), "BounceHorizontal");
        }
    }

    @SimpleFunction(description = "Start a vertical bounce animation. The duration is set in millisecond. Use as example for 1 second '1000'.")
    public void BounceVertical(AndroidViewComponent androidViewComponent, float f, float f2, int i) {
        StringBuilder sb;
        StringBuilder sb2;
        float f3 = f;
        float f4 = f2;
        int i2 = i;
        try {
            View view = androidViewComponent.getView();
            this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = true;
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, "translationY", f3, f4, i2);
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            int e2 = Log.e("Makeroid Animation Utilities", sb.append(exc.getMessage()).toString());
            new StringBuilder();
            Error(9, sb2.append(exc.getMessage()).toString(), "BounceVertical");
        }
    }

    @SimpleFunction(description = "Start a horizontal overshoot animation. If 'tension' is set to 0 you will not see a overshoot animation. Then you will see just a simple deceleration animation. The duration is set in millisecond. Use as example for 1 second '1000'.")
    public void OvershootHorizontal(AndroidViewComponent androidViewComponent, float f, float f2, int i, float f3) {
        StringBuilder sb;
        StringBuilder sb2;
        float f4 = f;
        float f5 = f2;
        int i2 = i;
        float f6 = f3;
        try {
            View view = androidViewComponent.getView();
            this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = f6;
            this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = false;
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, "translationX", f4, f5, i2);
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            int e2 = Log.e("Makeroid Animation Utilities", sb.append(exc.getMessage()).toString());
            new StringBuilder();
            Error(10, sb2.append(exc.getMessage()).toString(), "OvershootHorizontal");
        }
    }

    @SimpleFunction(description = "Start a vertical overshoot animation. If 'tension' is set to 0 you will not see a overshoot animation. Then you will see just a simple deceleration animation. The duration is set in millisecond. Use as example for 1 second '1000'.")
    public void OvershootVertical(AndroidViewComponent androidViewComponent, float f, float f2, int i, float f3) {
        StringBuilder sb;
        StringBuilder sb2;
        float f4 = f;
        float f5 = f2;
        int i2 = i;
        float f6 = f3;
        try {
            View view = androidViewComponent.getView();
            this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = f6;
            this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = false;
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, "translationY", f4, f5, i2);
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            int e2 = Log.e("Makeroid Animation Utilities", sb.append(exc.getMessage()).toString());
            new StringBuilder();
            Error(11, sb2.append(exc.getMessage()).toString(), "OvershootVertical");
        }
    }

    @SimpleFunction(description = "Start a zoom animation.  'tension' is set to 0 you will not see a overshoot animation. Then you will see just a simple deceleration animation. The duration is set in millisecond. Use as example for 1 second '1000'.")
    public void Zoom(AndroidViewComponent androidViewComponent, float f, float f2, int i) {
        StringBuilder sb;
        StringBuilder sb2;
        Animation animation;
        float f3 = f2;
        int i2 = i;
        try {
            float f4 = f;
            View view = androidViewComponent.getView();
            new ScaleAnimation(f4, f3, f4, f3, 1, 0.5f, 1, 0.5f);
            Animation animation2 = animation;
            Animation animation3 = animation2;
            animation2.setFillAfter(true);
            animation3.setDuration((long) i2);
            view.startAnimation(animation3);
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            int e2 = Log.e("Makeroid Animation Utilities", sb.append(exc.getMessage()).toString());
            new StringBuilder();
            Error(12, sb2.append(exc.getMessage()).toString(), "Zoom");
        }
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(View view, String str, float f, float f2, int i) {
        TimeInterpolator timeInterpolator;
        TimeInterpolator timeInterpolator2;
        String str2 = str;
        int i2 = i;
        float[] fArr = new float[2];
        fArr[0] = f;
        float[] fArr2 = fArr;
        fArr2[1] = f2;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, str2, fArr2);
        if (!str2.equals("rotation")) {
            if (this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR) {
                new BounceInterpolator();
                ofFloat.setInterpolator(timeInterpolator2);
            } else {
                new OvershootInterpolator(this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU);
                ofFloat.setInterpolator(timeInterpolator);
            }
        }
        ObjectAnimator duration = ofFloat.setDuration((long) i2);
        ofFloat.start();
    }
}
