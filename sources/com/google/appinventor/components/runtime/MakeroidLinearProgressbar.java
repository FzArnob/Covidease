package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.Log;
import android.widget.ProgressBar;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "", version = 1)
public final class MakeroidLinearProgressbar extends AndroidViewComponent {
    private int bEgrYPbd5peKqyXwAOBm3whGEG8qvODU2pBvqaxE9h7HUpHdWqfLhYhcIZ9UxgZV = -14575886;
    private Context context;
    private ProgressBar hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int uylNXPbffZHriucQ1SAwUkLE9dP8WxwaWe5GnEXLipwyDpy1sV8NkWFXe3foYSTl = -14575886;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidLinearProgressbar(com.google.appinventor.components.runtime.ComponentContainer r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = -14575886(0xffffffffff2196f2, float:-2.1478946E38)
            r2.bEgrYPbd5peKqyXwAOBm3whGEG8qvODU2pBvqaxE9h7HUpHdWqfLhYhcIZ9UxgZV = r3
            r2 = r0
            r3 = -14575886(0xffffffffff2196f2, float:-2.1478946E38)
            r2.uylNXPbffZHriucQ1SAwUkLE9dP8WxwaWe5GnEXLipwyDpy1sV8NkWFXe3foYSTl = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            android.widget.ProgressBar r3 = new android.widget.ProgressBar
            r8 = r3
            r3 = r8
            r4 = r8
            r5 = r0
            android.content.Context r5 = r5.context
            r6 = 0
            r7 = 16842872(0x1010078, float:2.3693894E-38)
            r4.<init>(r5, r6, r7)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r0
            r3 = 0
            r2.Minimum(r3)
            r2 = r0
            r3 = 100
            r2.Maximum(r3)
            r2 = r0
            r3 = -14575886(0xffffffffff2196f2, float:-2.1478946E38)
            r2.ProgressColor(r3)
            r2 = r0
            r3 = -14575886(0xffffffffff2196f2, float:-2.1478946E38)
            r2.IndeterminateColor(r3)
            r2 = r0
            r3 = 1
            r2.Indeterminate(r3)
            r2 = r0
            r3 = -2
            r2.Width(r3)
            java.lang.String r2 = "Makeroid Progress bar"
            java.lang.String r3 = "Makeroid Progress bar created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidLinearProgressbar.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public final ProgressBar getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public final int Height() {
        return getView().getHeight();
    }

    public final void Height(int i) {
        this.container.setChildHeight(this, i);
    }

    public final void HeightPercent(int i) {
    }

    @DesignerProperty(defaultValue = "0", editorType = "integer")
    @SimpleProperty(description = "Set the lower range of the progress bar to min. This function works only for devices with API >= 26")
    public final void Minimum(int i) {
        StringBuilder sb;
        int i2 = i;
        if (Build.VERSION.SDK_INT >= 26) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMin(i2);
            int i3 = Log.i("Makeroid Progress bar", "setMin = ".concat(String.valueOf(i2)));
            return;
        }
        new StringBuilder("setMin of progress bar is not possible. API is ");
        int i4 = Log.i("Makeroid Progress bar", sb.append(Build.VERSION.SDK_INT).toString());
    }

    @SimpleProperty
    public final int Minimum() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMin();
        }
        return 0;
    }

    @DesignerProperty(defaultValue = "100", editorType = "integer")
    @SimpleProperty(description = "Set the upper range of the progress bar max.")
    public final void Maximum(int i) {
        int i2 = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMax(i2);
        int i3 = Log.i("Makeroid Progress bar", "setMax = ".concat(String.valueOf(i2)));
    }

    @SimpleProperty
    public final int Maximum() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMax();
    }

    @SimpleProperty(description = "Sets the current progress to the specified value. Does not do anything if the progress bar is in indeterminate mode.")
    public final void Progress(int i) {
        int i2 = i;
        if (Build.VERSION.SDK_INT >= 24) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setProgress(i2, true);
        } else {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setProgress(i2);
        }
        ProgressChanged(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getProgress());
    }

    @SimpleProperty(description = "Get the progress bar's current level of progress.")
    public final int Progress() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getProgress();
    }

    @SimpleFunction(description = "Increase the progress bar's progress by the specified amount.")
    public final void IncrementProgressBy(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.incrementProgressBy(i);
        ProgressChanged(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getProgress());
    }

    @DesignerProperty(defaultValue = "&HFF2196F2", editorType = "color")
    @SimpleProperty(description = "Change the progress color of the progress bar.")
    public final void ProgressColor(int i) {
        int i2 = i;
        this.bEgrYPbd5peKqyXwAOBm3whGEG8qvODU2pBvqaxE9h7HUpHdWqfLhYhcIZ9UxgZV = i2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getProgressDrawable().setColorFilter(i2, PorterDuff.Mode.SRC_IN);
        int i3 = Log.i("Makeroid Progress bar", "Progress Color = ".concat(String.valueOf(i2)));
    }

    @SimpleProperty
    public final int ProgressColor() {
        return this.bEgrYPbd5peKqyXwAOBm3whGEG8qvODU2pBvqaxE9h7HUpHdWqfLhYhcIZ9UxgZV;
    }

    @DesignerProperty(defaultValue = "&HFF2196F2", editorType = "color")
    @SimpleProperty(description = "Change the indeterminate color of the progress bar.")
    public final void IndeterminateColor(int i) {
        int i2 = i;
        this.uylNXPbffZHriucQ1SAwUkLE9dP8WxwaWe5GnEXLipwyDpy1sV8NkWFXe3foYSTl = i2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getIndeterminateDrawable().setColorFilter(i2, PorterDuff.Mode.SRC_IN);
        int i3 = Log.i("Makeroid Progress bar", "Indeterminate Color = ".concat(String.valueOf(i2)));
    }

    @SimpleProperty
    public final int IndeterminateColor() {
        return this.uylNXPbffZHriucQ1SAwUkLE9dP8WxwaWe5GnEXLipwyDpy1sV8NkWFXe3foYSTl;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Change the indeterminate mode for this progress bar. In indeterminate mode, the progress is ignored and the progress bar shows an infinite animation instead.")
    public final void Indeterminate(boolean z) {
        boolean z2 = z;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setIndeterminate(z2);
        int i = Log.i("Makeroid Progress bar", "Indeterminate is: ".concat(String.valueOf(z2)));
    }

    @SimpleProperty(description = "Indicate whether this progress bar is in indeterminate mode.")
    public final boolean Indeterminate() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isIndeterminate();
    }

    @SimpleEvent(description = "Event that indicates that the progress of the progress bar has been changed. Returns the current progress value. If \"Indeterminate\" is set to true, then it returns \"0\".")
    public final void ProgressChanged(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ProgressChanged", Integer.valueOf(i));
    }
}
