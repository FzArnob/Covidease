package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.airbnb.lottie.LottieAnimationView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.ANIMATION, description = "A new component ", iconName = "images/makeroidLottie.png", nonVisible = false, version = 2)
@UsesLibraries(libraries = "lottie.aar, lottie.jar")
public final class KodularLottie extends AndroidViewComponent {
    private String XBLVE91Zp5kvRRn2aG2d0scvywAN7zOQf8gZkaueK9XPU163NLSIe4vWoTpXMo6u = "";
    /* access modifiers changed from: private */
    public boolean clickable = false;
    private ComponentContainer componentContainer;
    private Context context;
    private boolean f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z = true;
    private LottieAnimationView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean isCompanion = false;
    private float moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = 1.0f;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularLottie(com.google.appinventor.components.runtime.ComponentContainer r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.XBLVE91Zp5kvRRn2aG2d0scvywAN7zOQf8gZkaueK9XPU163NLSIe4vWoTpXMo6u = r3
            r2 = r0
            r3 = 1065353216(0x3f800000, float:1.0)
            r2.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = r3
            r2 = r0
            r3 = 1
            r2.f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z = r3
            r2 = r0
            r3 = 0
            r2.clickable = r3
            r2 = r0
            r3 = 0
            r2.isCompanion = r3
            r2 = r0
            r3 = r1
            r2.componentContainer = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r1
            com.google.appinventor.components.runtime.Form r2 = r2.$form()
            boolean r2 = r2 instanceof com.google.appinventor.components.runtime.ReplForm
            if (r2 == 0) goto L_0x0037
            r2 = r0
            r3 = 1
            r2.isCompanion = r3
        L_0x0037:
            r2 = r0
            com.airbnb.lottie.LottieAnimationView r3 = new com.airbnb.lottie.LottieAnimationView
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            android.content.Context r5 = r5.context
            r4.<init>(r5)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.airbnb.lottie.LottieAnimationView r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            android.view.ViewGroup$LayoutParams r3 = new android.view.ViewGroup$LayoutParams
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = -1
            r6 = -1
            r4.<init>(r5, r6)
            r2.setLayoutParams(r3)
            r2 = r0
            com.airbnb.lottie.LottieAnimationView r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.KodularLottie$1 r3 = new com.google.appinventor.components.runtime.KodularLottie$1
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.addAnimatorListener(r3)
            r2 = r0
            com.airbnb.lottie.LottieAnimationView r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.KodularLottie$2 r3 = new com.google.appinventor.components.runtime.KodularLottie$2
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setOnClickListener(r3)
            r2 = r0
            com.airbnb.lottie.LottieAnimationView r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.KodularLottie$3 r3 = new com.google.appinventor.components.runtime.KodularLottie$3
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setOnLongClickListener(r3)
            r2 = r0
            com.airbnb.lottie.LottieAnimationView r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r2.playAnimation()
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r0
            r3 = -1
            r2.Height(r3)
            r2 = r0
            r3 = -1
            r2.Width(r3)
            r2 = r0
            r7 = r2
            r2 = r7
            r3 = r7
            java.lang.String r3 = r3.XBLVE91Zp5kvRRn2aG2d0scvywAN7zOQf8gZkaueK9XPU163NLSIe4vWoTpXMo6u
            r2.Source(r3)
            r2 = r0
            r7 = r2
            r2 = r7
            r3 = r7
            boolean r3 = r3.f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z
            r2.LoopAnimation(r3)
            r2 = r0
            r7 = r2
            r2 = r7
            r3 = r7
            float r3 = r3.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0
            r2.AnimationSpeed(r3)
            r2 = r0
            r3 = 1
            r2.Clickable(r3)
            java.lang.String r2 = "Lottie"
            java.lang.String r3 = "Lottie Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularLottie.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(editorType = "asset")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Set the file or url of the file you want to load.")
    public final void Source(String str) {
        Notifier notifier;
        String str2 = str;
        this.XBLVE91Zp5kvRRn2aG2d0scvywAN7zOQf8gZkaueK9XPU163NLSIe4vWoTpXMo6u = str2;
        try {
            if (str2.isEmpty()) {
                return;
            }
            if (str2.startsWith("http://") || str2.startsWith("https://")) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAnimationFromUrl(str2);
            } else if (this.isCompanion) {
                try {
                    new Notifier(this.componentContainer);
                    notifier.ShowMessageDialog("Error: Unfortunately the Kodular companion is not currently able to show a live preview of the source file as Lotties are not visible in emulators (such as the companion). Please export your project as an apk and test it on a real device.", "Companion error", "OK");
                } catch (Exception e) {
                    int e2 = Log.e("Lottie", String.valueOf(e));
                }
            } else {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAnimation(str2);
            }
        } catch (Exception e3) {
            int e4 = Log.e("Lottie", String.valueOf(e3));
        }
    }

    @SimpleProperty(description = "Get the file or url of the file that you loaded.")
    public final String Source() {
        return this.XBLVE91Zp5kvRRn2aG2d0scvywAN7zOQf8gZkaueK9XPU163NLSIe4vWoTpXMo6u;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Set to true if you want to loop the animation.")
    public final void LoopAnimation(boolean z) {
        boolean z2 = z;
        this.f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z = z2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRepeatCount(z2 ? -1 : 0);
    }

    @SimpleProperty(description = "Get whether the animation loops.")
    public final boolean LoopAnimation() {
        return this.f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z;
    }

    @DesignerProperty(defaultValue = "1", editorType = "float")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Set the speed of the animation.")
    public final void AnimationSpeed(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setSpeed(f);
    }

    @SimpleProperty(description = "Get the speed of the animation.")
    public final float AnimationSpeed() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getSpeed();
    }

    @SimpleFunction(description = "Set the frame where you want the animation to start.")
    public final void StartFrame(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMinFrame(i);
    }

    @SimpleFunction(description = "Set the frame where you want the animation to end.")
    public final void EndFrame(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxFrame(i);
    }

    @SimpleFunction(description = "Set the frame where you want the animation to be now.")
    public final void SetCurrentFrame(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setFrame(i);
    }

    @SimpleFunction(description = "Get the frame where the animation is now.")
    public final int GetCurrentFrame() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getFrame();
    }

    @SimpleFunction(description = "Get the count of frames in the animation.")
    public final int GetFrameCount() {
        return (int) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMaxFrame();
    }

    @SimpleFunction(description = "Resume the animation on the Lottie component.")
    public final void ResumeAnimation() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.resumeAnimation();
    }

    @SimpleFunction(description = "Pause the animation on the Lottie component.")
    public final void PauseAnimation() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.pauseAnimation();
    }

    @SimpleFunction(description = "Start the animation on the Lottie component.")
    public final void StartAnimation() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.playAnimation();
    }

    @SimpleFunction(description = "Check if the Lottie component is animating.")
    public final boolean IsAnimating() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isAnimating();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Set the component clickable or not clickable.")
    public final void Clickable(boolean z) {
        boolean z2 = z;
        this.clickable = z2;
    }

    @SimpleProperty(description = "Returns true if the component is clickable.")
    public final boolean Clickable() {
        return this.clickable;
    }

    @SimpleEvent(description = "Triggers when the components was clicked.")
    public final void Click() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
    }

    @SimpleEvent(description = "Triggers when the components was long clicked.")
    public final void LongClick() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LongClick", new Object[0]);
    }

    @SimpleEvent(description = "Triggers when the animation starts.")
    public final void AnimationStart() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AnimationStart", new Object[0]);
    }

    @SimpleEvent(description = "Triggers when the animation ends.")
    public final void AnimationEnd() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AnimationEnd", new Object[0]);
    }

    @SimpleEvent(description = "Triggers when the animation repeated.")
    public final void AnimationRepeat() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AnimationRepeat", new Object[0]);
    }

    public final View getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }
}
