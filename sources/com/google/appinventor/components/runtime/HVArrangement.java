package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.p000v4.view.ViewCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.AlignmentUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.KodularHelper;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;

@SimpleObject
public class HVArrangement extends AndroidViewComponent implements Component, ComponentContainer {
    /* access modifiers changed from: private */
    public AlphaAnimation B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private boolean TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt;
    private AlignmentUtil alignmentSetter;
    private final Handler androidUIHandler;
    private boolean bM4TidozxzoY9OaFqMiIYPyvnCah6tSdq3o2XMkQik5CKYcFIbxeqf36lqbvGbmH;
    private int backgroundColor;
    private Drawable backgroundImageDrawable;
    private boolean clickable;
    private int horizontalAlignment;
    private final Activity hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Drawable f441hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private ViewGroup f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private AlphaAnimation f443hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String imagePath = "";
    private final int q0Zein2OQsQpQMJEzcCMbVGzaOYlGS7C9oJS7mmTl1ea6jnbwE6PEACmMQoD3fbj;
    private boolean scrollable = false;
    private boolean scrollbar;
    private int size;
    private int verticalAlignment;
    public final LinearLayout viewLayout;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HVArrangement(com.google.appinventor.components.runtime.ComponentContainer r13, int r14, boolean r15) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r0
            r5 = r1
            r4.<init>(r5)
            r4 = r0
            r5 = 0
            r4.scrollable = r5
            r4 = r0
            java.lang.String r5 = ""
            r4.imagePath = r5
            r4 = r0
            android.os.Handler r5 = new android.os.Handler
            r11 = r5
            r5 = r11
            r6 = r11
            r6.<init>()
            r4.androidUIHandler = r5
            r4 = r0
            r5 = 0
            r4.clickable = r5
            r4 = r0
            r5 = 1
            r4.scrollbar = r5
            r4 = r0
            r5 = 0
            r4.TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt = r5
            r4 = r0
            r5 = 0
            r4.bM4TidozxzoY9OaFqMiIYPyvnCah6tSdq3o2XMkQik5CKYcFIbxeqf36lqbvGbmH = r5
            r4 = r0
            r5 = 8
            r4.size = r5
            r4 = r0
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5
            r4 = r0
            r5 = r2
            r4.q0Zein2OQsQpQMJEzcCMbVGzaOYlGS7C9oJS7mmTl1ea6jnbwE6PEACmMQoD3fbj = r5
            r4 = r0
            r5 = r3
            r4.scrollable = r5
            r4 = r0
            com.google.appinventor.components.runtime.LinearLayout r5 = new com.google.appinventor.components.runtime.LinearLayout
            r11 = r5
            r5 = r11
            r6 = r11
            r7 = r0
            android.app.Activity r7 = r7.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r8 = r2
            r9 = 100
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r10 = 100
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r6.<init>(r7, r8, r9, r10)
            r4.viewLayout = r5
            r4 = r0
            com.google.appinventor.components.runtime.LinearLayout r4 = r4.viewLayout
            r5 = 0
            r4.setBaselineAligned(r5)
            r4 = r0
            com.google.appinventor.components.runtime.util.AlignmentUtil r5 = new com.google.appinventor.components.runtime.util.AlignmentUtil
            r11 = r5
            r5 = r11
            r6 = r11
            r7 = r0
            com.google.appinventor.components.runtime.LinearLayout r7 = r7.viewLayout
            r6.<init>(r7)
            r4.alignmentSetter = r5
            r4 = r0
            r5 = 1
            r4.horizontalAlignment = r5
            r4 = r0
            r5 = 1
            r4.verticalAlignment = r5
            r4 = r0
            com.google.appinventor.components.runtime.util.AlignmentUtil r4 = r4.alignmentSetter
            r5 = r0
            int r5 = r5.horizontalAlignment
            r4.setHorizontalAlignment(r5)
            r4 = r0
            com.google.appinventor.components.runtime.util.AlignmentUtil r4 = r4.alignmentSetter
            r5 = r0
            int r5 = r5.verticalAlignment
            r4.setVerticalAlignment(r5)
            r4 = r3
            if (r4 == 0) goto L_0x012b
            r4 = r2
            switch(r4) {
                case 0: goto L_0x0111;
                case 1: goto L_0x00f8;
                default: goto L_0x0094;
            }
        L_0x0094:
            r4 = r0
            r11 = r4
            r4 = r11
            r5 = r11
            android.app.Activity r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = r0
            int r6 = r6.size
            int r5 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r5, (int) r6)
            r4.size = r5
            r4 = r0
            android.view.ViewGroup r4 = r4.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            android.view.ViewGroup$LayoutParams r5 = new android.view.ViewGroup$LayoutParams
            r11 = r5
            r5 = r11
            r6 = r11
            r7 = 100
            r8 = 100
            r6.<init>(r7, r8)
            r4.setLayoutParams(r5)
            r4 = r0
            android.view.ViewGroup r4 = r4.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r5 = r0
            com.google.appinventor.components.runtime.LinearLayout r5 = r5.viewLayout
            android.view.ViewGroup r5 = r5.getLayoutManager()
            android.view.ViewGroup$LayoutParams r6 = new android.view.ViewGroup$LayoutParams
            r11 = r6
            r6 = r11
            r7 = r11
            r8 = -1
            r9 = -1
            r7.<init>(r8, r9)
            r4.addView(r5, r6)
            r4 = r0
            r11 = r4
            r4 = r11
            r5 = r11
            android.view.View r5 = r5.getView()
            android.graphics.drawable.Drawable r5 = r5.getBackground()
            r4.f441hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5
            r4 = r1
            r5 = r0
            r4.$add(r5)
            r4 = r0
            r5 = 0
            r4.BackgroundColor(r5)
            r4 = r0
            r4.setAnimation()
            r4 = r0
            r5 = 0
            r4.UseRoundCard(r5)
            r4 = r0
            r5 = 0
            r4.isCard(r5)
            r4 = r0
            r5 = 0
            r4.Clickable(r5)
            return
        L_0x00f8:
            java.lang.String r4 = "HVArrangement"
            java.lang.String r5 = "Setting up frameContainer = ScrollView()"
            int r4 = android.util.Log.d(r4, r5)
            r4 = r0
            android.widget.ScrollView r5 = new android.widget.ScrollView
            r11 = r5
            r5 = r11
            r6 = r11
            r7 = r0
            android.app.Activity r7 = r7.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6.<init>(r7)
            r4.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5
            goto L_0x0094
        L_0x0111:
            java.lang.String r4 = "HVArrangement"
            java.lang.String r5 = "Setting up frameContainer = HorizontalScrollView()"
            int r4 = android.util.Log.d(r4, r5)
            r4 = r0
            android.widget.HorizontalScrollView r5 = new android.widget.HorizontalScrollView
            r11 = r5
            r5 = r11
            r6 = r11
            r7 = r0
            android.app.Activity r7 = r7.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6.<init>(r7)
            r4.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5
            goto L_0x0094
        L_0x012b:
            java.lang.String r4 = "HVArrangement"
            java.lang.String r5 = "Setting up frameContainer = FrameLayout()"
            int r4 = android.util.Log.d(r4, r5)
            r4 = r0
            android.widget.FrameLayout r5 = new android.widget.FrameLayout
            r11 = r5
            r5 = r11
            r6 = r11
            r7 = r0
            android.app.Activity r7 = r7.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6.<init>(r7)
            r4.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5
            goto L_0x0094
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.HVArrangement.<init>(com.google.appinventor.components.runtime.ComponentContainer, int, boolean):void");
    }

    public Activity $context() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public Form $form() {
        return this.container.$form();
    }

    public void $add(AndroidViewComponent androidViewComponent) {
        this.viewLayout.add(androidViewComponent);
    }

    public void setChildWidth(AndroidViewComponent androidViewComponent, int i) {
        setChildWidth(androidViewComponent, i, 0);
    }

    public void setChildWidth(AndroidViewComponent androidViewComponent, int i, int i2) {
        StringBuilder sb;
        Runnable runnable;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        int i3 = i;
        int i4 = i2;
        int Width = this.container.$form().Width();
        int i5 = Width;
        if (Width == 0 && i4 < 2) {
            final AndroidViewComponent androidViewComponent3 = androidViewComponent2;
            final int i6 = i3;
            final int i7 = i4;
            new Runnable(this) {

                /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                private /* synthetic */ HVArrangement f444hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.f444hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
                }

                public final void run() {
                    int d = Log.d("HVArrangement", "(HVArrangement)Width not stable yet... trying again");
                    this.f444hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setChildWidth(androidViewComponent3, i6, i7 + 1);
                }
            };
            boolean postDelayed = this.androidUIHandler.postDelayed(runnable, 100);
        }
        if (i3 <= -1000) {
            new StringBuilder("HVArrangement.setChildWidth(): width = ");
            int d = Log.d("HVArrangement", sb.append(i3).append(" parent Width = ").append(i5).append(" child = ").append(androidViewComponent2).toString());
            i3 = (i5 * (-(i3 + 1000))) / 100;
        }
        androidViewComponent2.setLastWidth(i3);
        if (this.q0Zein2OQsQpQMJEzcCMbVGzaOYlGS7C9oJS7mmTl1ea6jnbwE6PEACmMQoD3fbj == 0) {
            ViewUtil.setChildWidthForHorizontalLayout(androidViewComponent2.getView(), i3);
        } else {
            ViewUtil.setChildWidthForVerticalLayout(androidViewComponent2.getView(), i3);
        }
    }

    public void setChildHeight(AndroidViewComponent androidViewComponent, int i) {
        Runnable runnable;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        int i2 = i;
        int Height = this.container.$form().Height();
        int i3 = Height;
        if (Height == 0) {
            final AndroidViewComponent androidViewComponent3 = androidViewComponent2;
            final int i4 = i2;
            new Runnable(this) {

                /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                private /* synthetic */ HVArrangement f445hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.f445hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                }

                public final void run() {
                    int d = Log.d("HVArrangement", "(HVArrangement)Height not stable yet... trying again");
                    this.f445hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setChildHeight(androidViewComponent3, i4);
                }
            };
            boolean postDelayed = this.androidUIHandler.postDelayed(runnable, 100);
        }
        if (i2 <= -1000) {
            i2 = (i3 * (-(i2 + 1000))) / 100;
        }
        androidViewComponent2.setLastHeight(i2);
        if (this.q0Zein2OQsQpQMJEzcCMbVGzaOYlGS7C9oJS7mmTl1ea6jnbwE6PEACmMQoD3fbj == 0) {
            ViewUtil.setChildHeightForHorizontalLayout(androidViewComponent2.getView(), i2);
        } else {
            ViewUtil.setChildHeightForVerticalLayout(androidViewComponent2.getView(), i2);
        }
    }

    @SimpleProperty
    public void Width(int i) {
        int i2 = i;
        super.Width(i2);
        if (i2 == -2) {
            if (this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME instanceof ScrollView) {
                ((ScrollView) this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setFillViewport(true);
            }
            if (this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME instanceof HorizontalScrollView) {
                ((HorizontalScrollView) this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setFillViewport(true);
            }
        }
    }

    @SimpleProperty
    public void WidthPercent(int i) {
        int i2 = i;
        super.WidthPercent(i2);
        if (i2 == 100) {
            if (this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME instanceof ScrollView) {
                ((ScrollView) this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setFillViewport(true);
            }
            if (this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME instanceof HorizontalScrollView) {
                ((HorizontalScrollView) this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setFillViewport(true);
            }
        }
    }

    @SimpleProperty
    public void Height(int i) {
        int i2 = i;
        super.Height(i2);
        if (i2 == -2) {
            if (this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME instanceof ScrollView) {
                ((ScrollView) this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setFillViewport(true);
            }
            if (this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME instanceof HorizontalScrollView) {
                ((HorizontalScrollView) this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setFillViewport(true);
            }
        }
    }

    @SimpleProperty
    public void HeightPercent(int i) {
        int i2 = i;
        super.HeightPercent(i2);
        if (i2 == 100) {
            if (this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME instanceof ScrollView) {
                ((ScrollView) this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setFillViewport(true);
            }
            if (this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME instanceof HorizontalScrollView) {
                ((HorizontalScrollView) this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setFillViewport(true);
            }
        }
    }

    public View getView() {
        return this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how contents of the arrangement are aligned  horizontally. The choices are: 1 = left aligned, 2 = right aligned,  3 = horizontally centered.  Alignment has no effect if the arrangement's width is automatic.")
    public int AlignHorizontal() {
        return this.horizontalAlignment;
    }

    @DesignerProperty(defaultValue = "1", editorType = "horizontal_alignment")
    @SimpleProperty
    public void AlignHorizontal(int i) {
        int i2 = i;
        try {
            this.alignmentSetter.setHorizontalAlignment(i2);
            this.horizontalAlignment = i2;
        } catch (IllegalArgumentException e) {
            this.container.$form().dispatchErrorOccurredEvent(this, "HorizontalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_HORIZONTAL_ALIGNMENT, Integer.valueOf(i2));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how the contents of the arrangement are aligned  vertically. The choices are: 1 = aligned at the top, 2 = vertically centered, 3 = aligned at the bottom.  Alignment has no effect if the arrangement's height is automatic.")
    public int AlignVertical() {
        return this.verticalAlignment;
    }

    @DesignerProperty(defaultValue = "1", editorType = "vertical_alignment")
    @SimpleProperty
    public void AlignVertical(int i) {
        int i2 = i;
        try {
            this.alignmentSetter.setVerticalAlignment(i2);
            this.verticalAlignment = i2;
        } catch (IllegalArgumentException e) {
            this.container.$form().dispatchErrorOccurredEvent(this, "VerticalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_VERTICAL_ALIGNMENT, Integer.valueOf(i2));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the component's background color")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&H00000000", editorType = "color")
    @SimpleProperty(description = "Specifies the component's background color. The background color will not be visible if an Image is being displayed.")
    public void BackgroundColor(int i) {
        this.backgroundColor = i;
        updateAppearance();
    }

    public void setAnimation() {
        AlphaAnimation alphaAnimation;
        AlphaAnimation alphaAnimation2;
        new AlphaAnimation(1.0f, 0.9f);
        this.f443hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = alphaAnimation;
        new AlphaAnimation(0.9f, 1.0f);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = alphaAnimation2;
        this.f443hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setDuration(450);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setDuration(450);
        this.f443hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setFillAfter(true);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setFillAfter(true);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If enabled the card layout will have then round corners.")
    public void UseRoundCard(boolean z) {
        boolean z2 = z;
        this.bM4TidozxzoY9OaFqMiIYPyvnCah6tSdq3o2XMkQik5CKYcFIbxeqf36lqbvGbmH = z2;
    }

    @SimpleProperty
    public boolean UseRoundCard() {
        return this.bM4TidozxzoY9OaFqMiIYPyvnCah6tSdq3o2XMkQik5CKYcFIbxeqf36lqbvGbmH;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If enabled the arrangement will be convert to a card view. You can detect clicks on it with the \"Click\" event.")
    public void isCard(boolean z) {
        int i;
        View.OnTouchListener onTouchListener;
        boolean z2 = z;
        this.TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt = z2;
        this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setClickable(this.clickable);
        if (z2) {
            this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(-1);
            ViewCompat.setElevation(this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 10.0f);
            if (this.backgroundColor == 16777215) {
                i = -1;
            } else {
                i = this.backgroundColor;
            }
            KodularHelper.setShape(this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.backgroundColor, i, this.bM4TidozxzoY9OaFqMiIYPyvnCah6tSdq3o2XMkQik5CKYcFIbxeqf36lqbvGbmH);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getLayoutParams();
            marginLayoutParams.setMargins(this.size, KodularUnitUtil.DpToPixels((Context) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 4), this.size, KodularUnitUtil.DpToPixels((Context) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 4));
            this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setLayoutParams(marginLayoutParams);
            this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPadding(this.size, this.size, this.size, this.size);
            new View.OnTouchListener(this) {
                private /* synthetic */ HVArrangement hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    View view2 = view;
                    MotionEvent motionEvent2 = motionEvent;
                    if (motionEvent2.getAction() == 0) {
                        if (HVArrangement.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                            HVArrangement.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).startAnimation(HVArrangement.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME));
                            HVArrangement.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).invalidate();
                        }
                    } else if ((motionEvent2.getAction() == 1 || motionEvent2.getAction() == 3) && HVArrangement.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                        HVArrangement.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).startAnimation(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
                        HVArrangement.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).invalidate();
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Click();
                    }
                    return true;
                }
            };
            this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnTouchListener(onTouchListener);
            return;
        }
        ViewCompat.setElevation(this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 0.0f);
        this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(this.backgroundColor);
        this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPadding(0, 0, 0, 0);
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getLayoutParams();
        marginLayoutParams2.setMargins(0, 0, 0, 0);
        this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setLayoutParams(marginLayoutParams2);
    }

    @SimpleProperty
    public boolean isCard() {
        return this.TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt;
    }

    @SimpleEvent(description = "Click listener event.")
    public void Click() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
    }

    @SimpleEvent(description = "Long click listener event.")
    public void LongClick() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LongClick", new Object[0]);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Set the component clickable or not clickable.")
    public void Clickable(boolean z) {
        View.OnClickListener onClickListener;
        View.OnLongClickListener onLongClickListener;
        boolean z2 = z;
        this.clickable = z2;
        this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setClickable(z2);
        if (this.clickable) {
            this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setClickable(true);
            ((FrameLayout) this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setForeground(getSelectedItemDrawable());
            new View.OnClickListener(this) {
                private /* synthetic */ HVArrangement hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onClick(View view) {
                    View view2 = view;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Click();
                }
            };
            this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnClickListener(onClickListener);
            new View.OnLongClickListener(this) {
                private /* synthetic */ HVArrangement hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final boolean onLongClick(View view) {
                    View view2 = view;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LongClick();
                    return true;
                }
            };
            this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnLongClickListener(onLongClickListener);
            return;
        }
        ((FrameLayout) this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setForeground((Drawable) null);
        this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnClickListener((View.OnClickListener) null);
        this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnLongClickListener((View.OnLongClickListener) null);
        this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setClickable(false);
    }

    @SimpleProperty
    public boolean Clickable() {
        return this.clickable;
    }

    public Drawable getSelectedItemDrawable() {
        int[] iArr = {16843534};
        TypedArray obtainStyledAttributes = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.obtainStyledAttributes(iArr);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Whether to display a scrollbar")
    public void Scrollbar(boolean z) {
        boolean z2 = z;
        if (this.q0Zein2OQsQpQMJEzcCMbVGzaOYlGS7C9oJS7mmTl1ea6jnbwE6PEACmMQoD3fbj == 1) {
            this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVerticalScrollBarEnabled(z2);
        } else if (this.q0Zein2OQsQpQMJEzcCMbVGzaOYlGS7C9oJS7mmTl1ea6jnbwE6PEACmMQoD3fbj == 0) {
            this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setHorizontalScrollBarEnabled(z2);
        }
        this.scrollbar = z2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean Scrollbar() {
        return this.scrollbar;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public String Image() {
        return this.imagePath;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty(description = "Specifies the path of the component's image.  If there is both an Image and a BackgroundColor, only the Image will be visible.")
    public void Image(String str) {
        String str2 = str;
        if (!str2.equals(this.imagePath) || this.backgroundImageDrawable == null) {
            this.imagePath = str2 == null ? "" : str2;
            this.backgroundImageDrawable = null;
            if (this.imagePath.length() > 0) {
                try {
                    this.backgroundImageDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), this.imagePath);
                } catch (Exception e) {
                }
            }
            updateAppearance();
        }
    }

    private void updateAppearance() {
        if (this.backgroundImageDrawable == null) {
            if (this.backgroundColor == 0) {
                ViewUtil.setBackgroundDrawable(this.viewLayout.getLayoutManager(), this.f441hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            } else {
                ViewUtil.setBackgroundDrawable(this.viewLayout.getLayoutManager(), (Drawable) null);
                this.viewLayout.getLayoutManager().setBackgroundColor(this.backgroundColor);
            }
            if (this.TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt) {
                KodularHelper.setShape(this.f442hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.backgroundColor, this.backgroundColor == 16777215 ? -1 : this.backgroundColor, this.bM4TidozxzoY9OaFqMiIYPyvnCah6tSdq3o2XMkQik5CKYcFIbxeqf36lqbvGbmH);
                return;
            }
            return;
        }
        ViewUtil.setBackgroundImage(this.viewLayout.getLayoutManager(), this.backgroundImageDrawable);
    }

    @Deprecated
    @SimpleProperty(description = "", userVisible = false)
    public void FullClickable(boolean z) {
    }
}
