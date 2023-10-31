package com.google.appinventor.components.runtime;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.design.card.MaterialCardView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AlignmentUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.KodularRippleDrawable;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;

@SimpleObject
@DesignerComponent(category = ComponentCategory.LAYOUT_GENERAL, description = "write in ode", version = 4)
public class MakeroidCardView extends AndroidViewComponent implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener, ComponentContainer {
    private int Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = Component.COLOR_LIGHT_GRAY;
    private int OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = 8;
    private float PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = 0.0f;
    private int UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = 8;
    private int YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = 8;
    private int ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = 8;
    private final Activity activity;
    private AlignmentUtil alignmentSetter;
    private final Handler androidUIHandler;
    private int backgroundColor = -1;
    private ComponentContainer container;
    private Context context;
    private int horizontalAlignment = 1;
    private final CardViewHelper hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private final ViewGroup mainLayout;
    private int muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = Component.COLOR_LIGHT_GRAY;
    private float oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = 2.0f;
    private int verticalAlignment = 1;
    private final LinearLayout viewLayout;
    private boolean x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = false;
    private float yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = 2.0f;

    /* JADX WARNING: Illegal instructions before constructor call */
    @android.annotation.SuppressLint({"ClickableViewAccessibility"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidCardView(com.google.appinventor.components.runtime.ComponentContainer r14) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r5 = r0
            r6 = r1
            r5.<init>(r6)
            r5 = r0
            android.os.Handler r6 = new android.os.Handler
            r12 = r6
            r6 = r12
            r7 = r12
            r7.<init>()
            r5.androidUIHandler = r6
            r5 = r0
            r6 = -1
            r5.backgroundColor = r6
            r5 = r0
            r6 = 1073741824(0x40000000, float:2.0)
            r5.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = r6
            r5 = r0
            r6 = 1073741824(0x40000000, float:2.0)
            r5.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = r6
            r5 = r0
            r6 = 8
            r5.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = r6
            r5 = r0
            r6 = 8
            r5.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = r6
            r5 = r0
            r6 = 8
            r5.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = r6
            r5 = r0
            r6 = 8
            r5.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = r6
            r5 = r0
            r6 = -3355444(0xffffffffffcccccc, float:NaN)
            r5.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = r6
            r5 = r0
            r6 = 0
            r5.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = r6
            r5 = r0
            r6 = -3355444(0xffffffffffcccccc, float:NaN)
            r5.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = r6
            r5 = r0
            r6 = 0
            r5.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = r6
            r5 = r0
            r6 = 1
            r5.verticalAlignment = r6
            r5 = r0
            r6 = 1
            r5.horizontalAlignment = r6
            r5 = r0
            r6 = r1
            r5.container = r6
            r5 = r0
            r6 = r1
            android.app.Activity r6 = r6.$context()
            r5.context = r6
            r5 = r0
            r6 = r1
            android.app.Activity r6 = r6.$context()
            r5.activity = r6
            r5 = r0
            com.google.appinventor.components.runtime.MakeroidCardView$CardViewHelper r6 = new com.google.appinventor.components.runtime.MakeroidCardView$CardViewHelper
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = r0
            android.content.Context r8 = r8.context
            r7.<init>(r8)
            r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6
            r5 = r0
            com.google.appinventor.components.runtime.MakeroidCardView$CardViewHelper r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = 1
            r5.setClickable(r6)
            r5 = r0
            com.google.appinventor.components.runtime.MakeroidCardView$CardViewHelper r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = 1
            r5.setLongClickable(r6)
            r5 = r0
            com.google.appinventor.components.runtime.MakeroidCardView$CardViewHelper r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = 1
            r5.setFocusable(r6)
            r5 = r0
            com.google.appinventor.components.runtime.MakeroidCardView$CardViewHelper r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = r0
            r5.setOnClickListener(r6)
            r5 = r0
            com.google.appinventor.components.runtime.MakeroidCardView$CardViewHelper r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = r0
            r5.setOnLongClickListener(r6)
            r5 = r0
            com.google.appinventor.components.runtime.MakeroidCardView$CardViewHelper r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = r0
            r5.setOnTouchListener(r6)
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams
            r12 = r5
            r5 = r12
            r6 = r12
            r7 = -1
            r8 = -1
            r6.<init>(r7, r8)
            r2 = r5
            r5 = r0
            android.content.Context r5 = r5.context
            r6 = 8
            int r5 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r5, (int) r6)
            r3 = r5
            r5 = r0
            android.content.Context r5 = r5.context
            r6 = 4
            int r5 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r5, (int) r6)
            r4 = r5
            r5 = r2
            r6 = r3
            r7 = r4
            r8 = r3
            r9 = r4
            r5.setMargins(r6, r7, r8, r9)
            r5 = r0
            com.google.appinventor.components.runtime.MakeroidCardView$CardViewHelper r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = r2
            r5.setLayoutParams(r6)
            r5 = r0
            com.google.appinventor.components.runtime.MakeroidCardView$CardViewHelper r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = 1
            r5.setUseCompatPadding(r6)
            r5 = r0
            com.google.appinventor.components.runtime.MakeroidCardView$CardViewHelper r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = 0
            r5.setPreventCornerOverlap(r6)
            r5 = r0
            com.google.appinventor.components.runtime.LinearLayout r6 = new com.google.appinventor.components.runtime.LinearLayout
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = r0
            android.content.Context r8 = r8.context
            r9 = 1
            r10 = 100
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r11 = 100
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r7.<init>(r8, r9, r10, r11)
            r5.viewLayout = r6
            r5 = r0
            com.google.appinventor.components.runtime.LinearLayout r5 = r5.viewLayout
            r6 = 0
            r5.setBaselineAligned(r6)
            r5 = r0
            com.google.appinventor.components.runtime.util.AlignmentUtil r6 = new com.google.appinventor.components.runtime.util.AlignmentUtil
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = r0
            com.google.appinventor.components.runtime.LinearLayout r8 = r8.viewLayout
            r7.<init>(r8)
            r5.alignmentSetter = r6
            r5 = r0
            com.google.appinventor.components.runtime.util.AlignmentUtil r5 = r5.alignmentSetter
            r6 = r0
            int r6 = r6.horizontalAlignment
            r5.setHorizontalAlignment(r6)
            r5 = r0
            com.google.appinventor.components.runtime.util.AlignmentUtil r5 = r5.alignmentSetter
            r6 = r0
            int r6 = r6.verticalAlignment
            r5.setVerticalAlignment(r6)
            r5 = r0
            com.google.appinventor.components.runtime.MakeroidCardView$CardViewHelper r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = r0
            com.google.appinventor.components.runtime.LinearLayout r6 = r6.viewLayout
            android.view.ViewGroup r6 = r6.getLayoutManager()
            android.view.ViewGroup$LayoutParams r7 = new android.view.ViewGroup$LayoutParams
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = -1
            r10 = -1
            r8.<init>(r9, r10)
            r5.addView(r6, r7)
            r5 = r0
            android.widget.FrameLayout r6 = new android.widget.FrameLayout
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = r0
            android.content.Context r8 = r8.context
            r7.<init>(r8)
            r5.mainLayout = r6
            r5 = r0
            android.view.ViewGroup r5 = r5.mainLayout
            android.view.ViewGroup$LayoutParams r6 = new android.view.ViewGroup$LayoutParams
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = 100
            r9 = 100
            r7.<init>(r8, r9)
            r5.setLayoutParams(r6)
            r5 = r0
            android.view.ViewGroup r5 = r5.mainLayout
            r6 = r0
            com.google.appinventor.components.runtime.MakeroidCardView$CardViewHelper r6 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r5.addView(r6)
            r5 = r1
            r6 = r0
            r5.$add(r6)
            r5 = r0
            r12 = r5
            r5 = r12
            r6 = r12
            int r6 = r6.backgroundColor
            r5.BackgroundColor(r6)
            r5 = r0
            r5.UpdateCard()
            r5 = r0
            r12 = r5
            r5 = r12
            r6 = r12
            float r6 = r6.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT
            r5.CornerRadius(r6)
            r5 = r0
            r12 = r5
            r5 = r12
            r6 = r12
            float r6 = r6.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS
            r5.Elevation(r6)
            r5 = r0
            r12 = r5
            r5 = r12
            r6 = r12
            boolean r6 = r6.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc
            r5.FullClickable(r6)
            r5 = r0
            r12 = r5
            r5 = r12
            r6 = r12
            float r6 = r6.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC
            r5.StrokeWidth(r6)
            r5 = r0
            r12 = r5
            r5 = r12
            r6 = r12
            int r6 = r6.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN
            r5.StrokeColor(r6)
            r5 = r0
            r12 = r5
            r5 = r12
            r6 = r12
            int r6 = r6.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB
            r5.TouchColor(r6)
            java.lang.String r5 = "Card View"
            java.lang.String r6 = "Card View Created"
            int r5 = android.util.Log.d(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidCardView.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public View getView() {
        return this.mainLayout;
    }

    public Activity $context() {
        return this.activity;
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

    public LinearLayout getViewLayout() {
        return this.viewLayout;
    }

    public CardViewHelper getViewHelper() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public void setChildWidth(AndroidViewComponent androidViewComponent, int i, int i2) {
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
                private /* synthetic */ MakeroidCardView f475hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.f475hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
                }

                public final void run() {
                    this.f475hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setChildWidth(androidViewComponent3, i6, i7 + 1);
                }
            };
            boolean postDelayed = this.androidUIHandler.postDelayed(runnable, 100);
        }
        if (i3 <= -1000) {
            i3 = (i5 * (-(i3 + 1000))) / 100;
        }
        androidViewComponent2.setLastWidth(i3);
        ViewUtil.setChildWidthForVerticalLayout(androidViewComponent2.getView(), i3);
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
                private /* synthetic */ MakeroidCardView f476hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.f476hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                }

                public final void run() {
                    this.f476hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setChildHeight(androidViewComponent3, i4);
                }
            };
            boolean postDelayed = this.androidUIHandler.postDelayed(runnable, 100);
        }
        if (i2 <= -1000) {
            i2 = (i3 * (-(i2 + 1000))) / 100;
        }
        androidViewComponent2.setLastHeight(i2);
        ViewUtil.setChildHeightForVerticalLayout(androidViewComponent2.getView(), i2);
    }

    public void onClick(View view) {
        View view2 = view;
        Click();
    }

    public boolean onLongClick(View view) {
        View view2 = view;
        LongClick();
        return true;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        View view2 = view;
        MotionEvent motionEvent2 = motionEvent;
        if (motionEvent2.getAction() == 0) {
            TouchDown();
        } else if (motionEvent2.getAction() == 1 || motionEvent2.getAction() == 3) {
            TouchUp();
        }
        return false;
    }

    public void UpdateCard() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setContentPadding(KodularUnitUtil.DpToPixels(this.context, this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG), KodularUnitUtil.DpToPixels(this.context, this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1), KodularUnitUtil.DpToPixels(this.context, this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO), KodularUnitUtil.DpToPixels(this.context, this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T));
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple \"Click\".")
    public void Click() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple \"Long click\".")
    public void LongClick() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LongClick", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple touch up on the card.")
    public void TouchUp() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TouchUp", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple touch down on the card.")
    public void TouchDown() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TouchDown", new Object[0]);
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Specifies the card view background color.")
    public void BackgroundColor(int i) {
        int i2 = i;
        this.backgroundColor = i2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCardBackgroundColor(i2);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @DesignerProperty(defaultValue = "2", editorType = "non_negative_float")
    @SimpleProperty(description = "The corner radius from the card view.")
    public void CornerRadius(float f) {
        float f2 = f;
        this.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = f2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRadius(KodularUnitUtil.DpToPixels(this.context, f2));
    }

    @DesignerProperty(defaultValue = "2", editorType = "non_negative_float")
    @SimpleProperty(description = "The card view elevation value.")
    public void Elevation(float f) {
        float f2 = f;
        this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = f2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCardElevation(KodularUnitUtil.DpToPixels(this.context, f2));
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxCardElevation(KodularUnitUtil.DpToPixels(this.context, f2));
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the left padding between the card view edges and the children of card view.")
    public void ContentPaddingLeft(int i) {
        this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = i;
        UpdateCard();
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the top padding between the card view edges and the children of card view.")
    public void ContentPaddingTop(int i) {
        this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = i;
        UpdateCard();
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the right padding between the card view edges and the children of card view.")
    public void ContentPaddingRight(int i) {
        this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = i;
        UpdateCard();
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the bottom padding between the card view edges and the children of CardView.")
    public void ContentPaddingBottom(int i) {
        this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = i;
        UpdateCard();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If set to true, the card will consume all click events. This means if you have added as example buttons into the card, then will the card consume the touch event on the button. And this means that the button would not be clickable, but only the entire card.")
    public void FullClickable(boolean z) {
        boolean z2 = z;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.fullClick = z2;
        this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = z2;
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color", propertyType = "advanced")
    @SimpleProperty(description = "Set the touch color also known as ripple color to the card view component.", userVisible = false)
    public void TouchColor(int i) {
        int i2 = i;
        if (i2 != 0) {
            this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = i2;
        } else {
            this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = Component.COLOR_LIGHT_GRAY;
        }
        KodularRippleDrawable.setRippleDrawable(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.backgroundColor, i2);
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_float")
    @SimpleProperty(description = "The stroke width for the card view.")
    public void StrokeWidth(float f) {
        float f2 = f;
        this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = f2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStrokeWidth((int) KodularUnitUtil.DpToPixels(this.context, f2));
    }

    @SimpleProperty(description = "Returns the stroke width from the card view.")
    public float StrokeWidth() {
        return this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color")
    @SimpleProperty(description = "The stroke color for the card view.")
    public void StrokeColor(int i) {
        int i2 = i;
        this.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = i2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStrokeColor(i2);
    }

    @SimpleProperty(description = "Returns the stroke color from the card view.")
    public int StrokeColor() {
        return this.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN;
    }

    @SimpleProperty
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @SimpleProperty
    public float CornerRadius() {
        return this.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT;
    }

    @SimpleProperty
    public float Elevation() {
        return this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS;
    }

    @SimpleProperty
    public int ContentPaddingLeft() {
        return this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG;
    }

    @SimpleProperty
    public int ContentPaddingTop() {
        return this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1;
    }

    @SimpleProperty
    public int ContentPaddingRight() {
        return this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO;
    }

    @SimpleProperty
    public int ContentPaddingBottom() {
        return this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T;
    }

    @SimpleProperty
    public boolean FullClickable() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.fullClick;
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

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how contents of the arrangement are aligned  horizontally. The choices are: 1 = left aligned, 2 = right aligned,  3 = horizontally centered.  Alignment has no effect if the arrangement's width is automatic.")
    public int AlignHorizontal() {
        return this.horizontalAlignment;
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

    public static class CardViewHelper extends MaterialCardView {
        public boolean fullClick = false;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CardViewHelper(Context context) {
            super(context);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CardViewHelper(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CardViewHelper(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            MotionEvent motionEvent2 = motionEvent;
            return this.fullClick;
        }
    }
}
