package com.google.appinventor.components.runtime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.card.MaterialCardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularDynamicModel;
import com.google.appinventor.components.runtime.util.KodularDynamicUtil;
import com.google.appinventor.components.runtime.util.KodularRippleDrawable;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DYNAMIC, description = "A component to create dynamic cardviews in Arrangements.", iconName = "images/cardview.png", nonVisible = true, version = 3)
public final class KodularDynamicCardView extends AndroidNonvisibleComponent {
    private int Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;
    private final String LOG_TAG = "KodularDynamicCardView";
    private int OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG;
    private float PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    private int UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO;
    private int YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1;
    private int ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T;
    private int backgroundColor;
    private Context context;
    private Form form;
    private boolean fullClick;
    private List<KodularDynamicModel> kodularDynamicModelList;
    private int muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN;
    private float oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS;
    private float yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularDynamicCardView(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = "KodularDynamicCardView"
            r2.LOG_TAG = r3
            r2 = r0
            java.util.ArrayList r3 = new java.util.ArrayList
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.kodularDynamicModelList = r3
            r2 = r0
            r3 = -1
            r2.backgroundColor = r3
            r2 = r0
            r3 = 1073741824(0x40000000, float:2.0)
            r2.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = r3
            r2 = r0
            r3 = 1073741824(0x40000000, float:2.0)
            r2.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = r3
            r2 = r0
            r3 = 0
            r2.fullClick = r3
            r2 = r0
            r3 = 8
            r2.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = r3
            r2 = r0
            r3 = 8
            r2.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = r3
            r2 = r0
            r3 = 8
            r2.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = r3
            r2 = r0
            r3 = 8
            r2.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = r3
            r2 = r0
            r3 = -3355444(0xffffffffffcccccc, float:NaN)
            r2.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = r3
            r2 = r0
            r3 = 1065353216(0x3f800000, float:1.0)
            r2.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = r3
            r2 = r0
            r3 = -3355444(0xffffffffffcccccc, float:NaN)
            r2.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularDynamicCardView.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Create a new card view component dynamically. Use for width/height '-1' for wrap content or '-2' for fill parent.")
    public final void CreateCardView(int i, AndroidViewComponent androidViewComponent, int i2, int i3) {
        KodularCardView kodularCardView;
        LinearLayout.LayoutParams layoutParams;
        LinearLayout linearLayout;
        ViewGroup.LayoutParams layoutParams2;
        Object obj;
        View.OnClickListener onClickListener;
        View.OnLongClickListener onLongClickListener;
        View.OnTouchListener onTouchListener;
        int i4 = i;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        int i5 = i2;
        int i6 = i3;
        try {
            KodularCardView kodularCardView2 = kodularCardView;
            new KodularCardView(this, this.context);
            KodularCardView kodularCardView3 = kodularCardView2;
            KodularCardView kodularCardView4 = kodularCardView3;
            KodularCardView kodularCardView5 = kodularCardView3;
            int i7 = i6;
            int i8 = i5;
            int DpToPixels = KodularUnitUtil.DpToPixels(this.context, i8);
            int DpToPixels2 = KodularUnitUtil.DpToPixels(this.context, i7);
            if (i8 == -1) {
                DpToPixels = -2;
            } else if (i8 == -2) {
                DpToPixels = -1;
            }
            if (i7 == -1) {
                DpToPixels2 = -2;
            } else if (i7 == -2) {
                DpToPixels2 = -1;
            }
            new LinearLayout.LayoutParams(DpToPixels, DpToPixels2);
            LinearLayout.LayoutParams layoutParams3 = layoutParams;
            int DpToPixels3 = KodularUnitUtil.DpToPixels(this.context, 8);
            int DpToPixels4 = KodularUnitUtil.DpToPixels(this.context, 4);
            layoutParams3.setMargins(DpToPixels3, DpToPixels4, DpToPixels3, DpToPixels4);
            kodularCardView4.setLayoutParams(layoutParams3);
            new LinearLayout(this.context);
            LinearLayout linearLayout2 = linearLayout;
            LinearLayout linearLayout3 = linearLayout2;
            linearLayout2.setOrientation(1);
            new LinearLayout.LayoutParams(-1, -1);
            linearLayout3.setLayoutParams(layoutParams2);
            new KodularDynamicModel(i4, kodularCardView5, androidViewComponent2, linearLayout3);
            boolean add = this.kodularDynamicModelList.add(obj);
            LinearLayout linearLayout4 = (LinearLayout) ((ViewGroup) androidViewComponent2.getView()).getChildAt(0);
            kodularCardView5.addView(linearLayout3);
            linearLayout4.addView(kodularCardView5);
            KodularCardView kodularCardView6 = kodularCardView5;
            final int i9 = i4;
            new View.OnClickListener(this) {
                private /* synthetic */ KodularDynamicCardView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void onClick(View view) {
                    View view2 = view;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Click(i9);
                }
            };
            kodularCardView6.setOnClickListener(onClickListener);
            final int i10 = i4;
            new View.OnLongClickListener(this) {
                private /* synthetic */ KodularDynamicCardView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final boolean onLongClick(View view) {
                    View view2 = view;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LongClick(i10);
                    return true;
                }
            };
            kodularCardView6.setOnLongClickListener(onLongClickListener);
            final int i11 = i4;
            new View.OnTouchListener(this) {
                private /* synthetic */ KodularDynamicCardView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                @SuppressLint({"ClickableViewAccessibility"})
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    View view2 = view;
                    MotionEvent motionEvent2 = motionEvent;
                    if (motionEvent2.getAction() == 0) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.TouchDown(i11);
                    } else if (motionEvent2.getAction() == 1 || motionEvent2.getAction() == 3) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.TouchUp(i11);
                    }
                    return false;
                }
            };
            kodularCardView6.setOnTouchListener(onTouchListener);
            KodularCardView kodularCardView7 = kodularCardView5;
            kodularCardView7.setClickable(true);
            kodularCardView7.setUseCompatPadding(true);
            kodularCardView7.setPreventCornerOverlap(false);
            kodularCardView7.setCardBackgroundColor(this.backgroundColor);
            kodularCardView7.setRadius(KodularUnitUtil.DpToPixels(this.context, this.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT));
            kodularCardView7.setCardElevation(KodularUnitUtil.DpToPixels(this.context, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS));
            kodularCardView7.setMaxCardElevation(KodularUnitUtil.DpToPixels(this.context, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS));
            kodularCardView7.fullClick = this.fullClick;
            kodularCardView7.setContentPadding(KodularUnitUtil.DpToPixels(this.context, this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG), KodularUnitUtil.DpToPixels(this.context, this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1), KodularUnitUtil.DpToPixels(this.context, this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO), KodularUnitUtil.DpToPixels(this.context, this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T));
            kodularCardView7.setStrokeColor(this.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN);
            kodularCardView7.setStrokeWidth((int) KodularUnitUtil.DpToPixels(this.context, this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC));
            KodularRippleDrawable.setRippleDrawable(kodularCardView7, this.backgroundColor, this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB);
            kodularCardView7.invalidate();
        } catch (Exception e) {
            int e2 = Log.e("KodularDynamicCardView", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Add a component into the card view component with the given id.")
    public final void AddComponentToCardView(int i, AndroidViewComponent androidViewComponent) {
        int i2 = i;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        try {
            KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i2, this.kodularDynamicModelList);
            LinearLayout linearLayout = (LinearLayout) KodularDynamicUtil.getChildViewHolderById(i2, this.kodularDynamicModelList);
            try {
                View view = androidViewComponent2.getView();
                View view2 = view;
                ((ViewGroup) view.getParent()).removeView(view2);
                linearLayout.addView(view2);
                kodularCardView.invalidate();
            } catch (Exception e) {
                int e2 = Log.e("KodularDynamicCardView", String.valueOf(e));
                try {
                    ViewGroup viewGroup = (ViewGroup) androidViewComponent2.getView();
                    ViewGroup viewGroup2 = viewGroup;
                    ((ViewGroup) viewGroup.getParent()).removeView(viewGroup2);
                    linearLayout.addView(viewGroup2);
                    kodularCardView.invalidate();
                } catch (Exception e3) {
                    int e4 = Log.e("KodularDynamicCardView", String.valueOf(e3));
                }
            }
        } catch (Exception e5) {
            int e6 = Log.e("KodularDynamicCardView", String.valueOf(e5));
        }
    }

    @SimpleFunction(description = "Remove a card view component with the given id.")
    public final void DeleteCardView(int i) {
        int i2 = i;
        try {
            ((LinearLayout) ((ViewGroup) ((AndroidViewComponent) KodularDynamicUtil.getViewHolderById(i2, this.kodularDynamicModelList)).getView()).getChildAt(0)).removeView((KodularCardView) KodularDynamicUtil.getObjectById(i2, this.kodularDynamicModelList));
            KodularDynamicUtil.removeItemById(i2, this.kodularDynamicModelList);
        } catch (Exception e) {
            int e2 = Log.e("KodularDynamicCardView", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Returns the card view referenced by its id.")
    public final KodularDynamicUtil.ComponentReturnHelper GetCardViewById(int i) {
        KodularDynamicUtil.ComponentReturnHelper componentReturnHelper;
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        KodularCardView kodularCardView2 = kodularCardView;
        if (kodularCardView == null) {
            return null;
        }
        new KodularDynamicUtil.ComponentReturnHelper(kodularCardView2);
        return componentReturnHelper;
    }

    @SimpleFunction(description = "Set the background color of a card view component.")
    public final void SetBackgroundColor(int i, int i2) {
        int i3 = i2;
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        KodularCardView kodularCardView2 = kodularCardView;
        if (kodularCardView != null) {
            kodularCardView2.setCardBackgroundColor(i3);
            kodularCardView2.invalidate();
        }
    }

    @SimpleFunction(description = "Get the background color of a card view component.")
    public final int GetBackgroundColor(int i) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        KodularCardView kodularCardView2 = kodularCardView;
        if (kodularCardView != null) {
            return kodularCardView2.getCardBackgroundColor().getDefaultColor();
        }
        return 0;
    }

    @SimpleFunction(description = "Set the corner radius of a card view component.")
    public final void SetCornerRadius(int i, float f) {
        float f2 = f;
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        KodularCardView kodularCardView2 = kodularCardView;
        if (kodularCardView != null) {
            kodularCardView2.setRadius(KodularUnitUtil.DpToPixels(this.context, f2));
        }
    }

    @SimpleFunction(description = "Get the corner radius of a card view component.")
    public final int GetCornerRadius(int i) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        KodularCardView kodularCardView2 = kodularCardView;
        if (kodularCardView != null) {
            return (int) kodularCardView2.getRadius();
        }
        return 0;
    }

    @SimpleFunction(description = "Set the elevation of a card view component.")
    public final void SetElevation(int i, float f) {
        float f2 = f;
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        KodularCardView kodularCardView2 = kodularCardView;
        if (kodularCardView != null) {
            kodularCardView2.setCardElevation(KodularUnitUtil.DpToPixels(this.context, f2));
            kodularCardView2.setMaxCardElevation(KodularUnitUtil.DpToPixels(this.context, f2));
        }
    }

    @SimpleFunction(description = "Get the elevation of a card view component.")
    public final int GetElevation(int i) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        KodularCardView kodularCardView2 = kodularCardView;
        if (kodularCardView != null) {
            return (int) kodularCardView2.getCardElevation();
        }
        return 0;
    }

    @SimpleFunction(description = "Set the content padding of a card view component.")
    public final void ContentPadding(int i, int i2, int i3, int i4, int i5) {
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        int i9 = i5;
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        KodularCardView kodularCardView2 = kodularCardView;
        if (kodularCardView != null) {
            kodularCardView2.setContentPadding(KodularUnitUtil.DpToPixels(this.context, i6), KodularUnitUtil.DpToPixels(this.context, i7), KodularUnitUtil.DpToPixels(this.context, i8), KodularUnitUtil.DpToPixels(this.context, i9));
        }
    }

    @SimpleFunction(description = "If set to true, the card will consume all click events. This means if you have added as example buttons into the card, then will the card consume the touch event on the button. And this means that the button would not be clickable, but only the entire card.")
    public final void FullClickable(int i, boolean z) {
        boolean z2 = z;
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        KodularCardView kodularCardView2 = kodularCardView;
        if (kodularCardView != null) {
            kodularCardView2.fullClick = z2;
        }
    }

    @SimpleFunction(description = "Set the width of a card view component.")
    public final void SetWidth(int i, int i2) {
        int i3 = i2;
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        KodularCardView kodularCardView2 = kodularCardView;
        if (kodularCardView != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) kodularCardView2.getLayoutParams();
            layoutParams.width = KodularUnitUtil.DpToPixels(this.context, i3);
            kodularCardView2.setLayoutParams(layoutParams);
            kodularCardView2.invalidate();
        }
    }

    @SimpleFunction(description = "Get the width of a card view component.")
    public final int GetWidth(int i) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        KodularCardView kodularCardView2 = kodularCardView;
        if (kodularCardView != null) {
            return kodularCardView2.getWidth();
        }
        return 0;
    }

    @SimpleFunction(description = "Set the height of a card view component.")
    public final void SetHeight(int i, int i2) {
        int i3 = i2;
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        KodularCardView kodularCardView2 = kodularCardView;
        if (kodularCardView != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) kodularCardView2.getLayoutParams();
            layoutParams.height = KodularUnitUtil.DpToPixels(this.context, i3);
            kodularCardView2.setLayoutParams(layoutParams);
            kodularCardView2.invalidate();
        }
    }

    @SimpleFunction(description = "Get the height of a card view component.")
    public final int GetHeight(int i) {
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        KodularCardView kodularCardView2 = kodularCardView;
        if (kodularCardView != null) {
            return kodularCardView2.getHeight();
        }
        return 0;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Specifies the cards's background color.")
    public final void BackgroundColor(int i) {
        int i2 = i;
        this.backgroundColor = i2;
    }

    @DesignerProperty(defaultValue = "2", editorType = "non_negative_float")
    @SimpleProperty(description = "The corner radius from the card view.")
    public final void CornerRadius(float f) {
        float f2 = f;
        this.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = f2;
    }

    @DesignerProperty(defaultValue = "2", editorType = "non_negative_float")
    @SimpleProperty(description = "The card view elevation value.")
    public final void Elevation(float f) {
        float f2 = f;
        this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = f2;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If set to true, the card will consume all click events. This means if you have added as example buttons into the card, then will the card consume the touch event on the button. And this means that the button would not be clickable, but only the entire card.")
    public final void FullClickable(boolean z) {
        boolean z2 = z;
        this.fullClick = z2;
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the left padding between the card's edges and the children of card view.")
    public final void ContentPaddingLeft(int i) {
        int i2 = i;
        this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = i2;
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the top padding between the card's edges and the children of card view.")
    public final void ContentPaddingTop(int i) {
        int i2 = i;
        this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = i2;
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the right padding between the card's edges and the children of card view.")
    public final void ContentPaddingRight(int i) {
        int i2 = i;
        this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = i2;
    }

    @DesignerProperty(defaultValue = "8", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the bottom padding between the card's edges and the children of card view.")
    public final void ContentPaddingBottom(int i) {
        int i2 = i;
        this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = i2;
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple click on a card view component with the specific id.")
    public final void Click(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Click", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple long click on a card view component with the specific id.")
    public final void LongClick(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LongClick", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple touch up on a card view component with the specific id.")
    public final void TouchUp(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TouchUp", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple touch down on a card view component with the specific id.")
    public final void TouchDown(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TouchDown", Integer.valueOf(i));
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color", propertyType = "advanced")
    @SimpleProperty(description = "Set the touch color also known as ripple color to a card view component.")
    public final void TouchColor(int i) {
        int i2 = i;
        if (i2 != 0) {
            this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = i2;
            return;
        }
        this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = Component.COLOR_LIGHT_GRAY;
    }

    @DesignerProperty(defaultValue = "1", editorType = "non_negative_float")
    @SimpleProperty(description = "The stroke width for the card view.")
    public final void StrokeWidth(float f) {
        float f2 = f;
        this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = f2;
    }

    @SimpleFunction(description = "Set the stroke width to a card view component with the specific id.")
    public final void StrokeWidth(int i, float f) {
        float f2 = f;
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        KodularCardView kodularCardView2 = kodularCardView;
        if (kodularCardView != null) {
            kodularCardView2.setStrokeWidth((int) KodularUnitUtil.DpToPixels(this.context, f2));
        }
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color")
    @SimpleProperty(description = "The stroke width for the card view.")
    public final void StrokeColor(int i) {
        int i2 = i;
        this.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = i2;
    }

    @SimpleFunction(description = "Set the stroke color to a card view component with the specific id.")
    public final void StrokeColor(int i, int i2) {
        int i3 = i2;
        KodularCardView kodularCardView = (KodularCardView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        KodularCardView kodularCardView2 = kodularCardView;
        if (kodularCardView != null) {
            kodularCardView2.setStrokeColor(i3);
        }
    }

    public class KodularCardView extends MaterialCardView {
        public boolean fullClick = false;
        private /* synthetic */ KodularDynamicCardView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public KodularCardView(KodularDynamicCardView kodularDynamicCardView, Context context) {
            super(context);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = kodularDynamicCardView;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public KodularCardView(KodularDynamicCardView kodularDynamicCardView, Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = kodularDynamicCardView;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public KodularCardView(KodularDynamicCardView kodularDynamicCardView, Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = kodularDynamicCardView;
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            MotionEvent motionEvent2 = motionEvent;
            return this.fullClick;
        }
    }
}
