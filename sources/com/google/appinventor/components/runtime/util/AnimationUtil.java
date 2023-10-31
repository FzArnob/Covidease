package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

public final class AnimationUtil {
    private AnimationUtil() {
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(View view, boolean z, int i) {
        AnimationSet animationSet;
        TranslateAnimation translateAnimation;
        View view2 = view;
        int i2 = i;
        float f = z ? 1.0f : -1.0f;
        new AnimationSet(true);
        AnimationSet animationSet2 = animationSet;
        AnimationSet animationSet3 = animationSet2;
        animationSet2.setRepeatCount(-1);
        animationSet3.setRepeatMode(1);
        new TranslateAnimation(2, f * 0.7f, 2, f * -0.7f, 2, 0.0f, 2, 0.0f);
        TranslateAnimation translateAnimation2 = translateAnimation;
        TranslateAnimation translateAnimation3 = translateAnimation2;
        translateAnimation2.setStartOffset(0);
        translateAnimation3.setDuration((long) i2);
        translateAnimation3.setFillAfter(true);
        animationSet3.addAnimation(translateAnimation3);
        view2.startAnimation(animationSet3);
    }

    public static void ApplyAnimation(View view, String str) {
        View view2 = view;
        String str2 = str;
        if (str2.equals("ScrollRightSlow")) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view2, false, 8000);
        } else if (str2.equals("ScrollRight")) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view2, false, 4000);
        } else if (str2.equals("ScrollRightFast")) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view2, false, 1000);
        } else if (str2.equals("ScrollLeftSlow")) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view2, true, 8000);
        } else if (str2.equals("ScrollLeft")) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view2, true, 4000);
        } else if (str2.equals("ScrollLeftFast")) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view2, true, 1000);
        } else if (str2.equals("Stop")) {
            view2.clearAnimation();
        }
    }

    public static void ApplyOpenScreenAnimation(Activity activity, String str) {
        Activity activity2 = activity;
        String str2 = str;
        if (str2 != null) {
            if (Build.VERSION.SDK_INT <= 4) {
                int e = Log.e("AnimationUtil", "Screen animations are not available on android versions less than 2.0.");
                return;
            }
            int i = 0;
            int i2 = 0;
            if (str2.equalsIgnoreCase("fade")) {
                i = activity2.getResources().getIdentifier("fadein", "anim", activity2.getPackageName());
                i2 = activity2.getResources().getIdentifier("hold", "anim", activity2.getPackageName());
            } else if (str2.equalsIgnoreCase("zoom")) {
                i2 = activity2.getResources().getIdentifier("zoom_exit", "anim", activity2.getPackageName());
                i = activity2.getResources().getIdentifier("zoom_enter", "anim", activity2.getPackageName());
            } else if (str2.equalsIgnoreCase("slidehorizontal")) {
                i2 = activity2.getResources().getIdentifier("slide_exit", "anim", activity2.getPackageName());
                i = activity2.getResources().getIdentifier("slide_enter", "anim", activity2.getPackageName());
            } else if (str2.equalsIgnoreCase("slidevertical")) {
                i2 = activity2.getResources().getIdentifier("slide_v_exit", "anim", activity2.getPackageName());
                i = activity2.getResources().getIdentifier("slide_v_enter", "anim", activity2.getPackageName());
            } else if (!str2.equalsIgnoreCase("none")) {
                return;
            }
            EclairUtil.overridePendingTransitions(activity2, i, i2);
        }
    }

    public static void ApplyCloseScreenAnimation(Activity activity, String str) {
        Activity activity2 = activity;
        String str2 = str;
        if (str2 != null) {
            if (Build.VERSION.SDK_INT <= 4) {
                int e = Log.e("AnimationUtil", "Screen animations are not available on android versions less than 2.0.");
                return;
            }
            int i = 0;
            int i2 = 0;
            if (str2.equalsIgnoreCase("fade")) {
                i2 = activity2.getResources().getIdentifier("fadeout", "anim", activity2.getPackageName());
                i = activity2.getResources().getIdentifier("hold", "anim", activity2.getPackageName());
            } else if (str2.equalsIgnoreCase("zoom")) {
                i2 = activity2.getResources().getIdentifier("zoom_exit_reverse", "anim", activity2.getPackageName());
                i = activity2.getResources().getIdentifier("zoom_enter_reverse", "anim", activity2.getPackageName());
            } else if (str2.equalsIgnoreCase("slidehorizontal")) {
                i2 = activity2.getResources().getIdentifier("slide_exit_reverse", "anim", activity2.getPackageName());
                i = activity2.getResources().getIdentifier("slide_enter_reverse", "anim", activity2.getPackageName());
            } else if (str2.equalsIgnoreCase("slidevertical")) {
                i2 = activity2.getResources().getIdentifier("slide_v_exit_reverse", "anim", activity2.getPackageName());
                i = activity2.getResources().getIdentifier("slide_v_enter_reverse", "anim", activity2.getPackageName());
            } else if (!str2.equalsIgnoreCase("none")) {
                return;
            }
            EclairUtil.overridePendingTransitions(activity2, i, i2);
        }
    }
}
