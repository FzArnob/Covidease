package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularDynamicModel;
import com.google.appinventor.components.runtime.util.KodularDynamicUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DYNAMIC, description = "A component to create dynamic buttons in Arrangements", iconName = "images/button.png", nonVisible = true, version = 4)
public final class MakeroidDynamicButton extends AndroidNonvisibleComponent {
    private final String LOG_TAG = "MakeroidDynamicButton";
    private Context context;
    private ColorStateList hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Drawable f477hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private List<KodularDynamicModel> kodularDynamicModelList;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidDynamicButton(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.util.ArrayList r3 = new java.util.ArrayList
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.kodularDynamicModelList = r3
            r2 = r0
            java.lang.String r3 = "MakeroidDynamicButton"
            r2.LOG_TAG = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            android.widget.Button r2 = new android.widget.Button
            r5 = r2
            r2 = r5
            r3 = r5
            r4 = r0
            android.content.Context r4 = r4.context
            r3.<init>(r4)
            r1 = r2
            r2 = r0
            r3 = r1
            android.graphics.drawable.Drawable r3 = r3.getBackground()
            r2.f477hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            r3 = r1
            android.content.res.ColorStateList r3 = r3.getTextColors()
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidDynamicButton.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Create a Dynamic Button.")
    public final void CreateButton(int i, AndroidViewComponent androidViewComponent) {
        Button button;
        ViewGroup.LayoutParams layoutParams;
        View.OnClickListener onClickListener;
        View.OnLongClickListener onLongClickListener;
        Object obj;
        int i2 = i;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        new Button(this.context);
        Button button2 = button;
        Button button3 = button2;
        new LinearLayout.LayoutParams(-2, -2);
        button2.setLayoutParams(layoutParams);
        button3.setAllCaps(false);
        TextViewUtil.setFontTypeface(button3, 0, false, false);
        final int i3 = i2;
        new View.OnClickListener(this) {
            private /* synthetic */ MakeroidDynamicButton hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onClick(View view) {
                View view2 = view;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ButtonClick(i3);
            }
        };
        button3.setOnClickListener(onClickListener);
        final int i4 = i2;
        new View.OnLongClickListener(this) {
            private /* synthetic */ MakeroidDynamicButton hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final boolean onLongClick(View view) {
                View view2 = view;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ButtonLongClick(i4);
                return true;
            }
        };
        button3.setOnLongClickListener(onLongClickListener);
        new KodularDynamicModel(i2, button3, androidViewComponent2);
        boolean add = this.kodularDynamicModelList.add(obj);
        ((LinearLayout) ((ViewGroup) androidViewComponent2.getView()).getChildAt(0)).addView(button3);
    }

    @SimpleFunction(description = "Delete a Dynamic Button.")
    public final void DeleteButtonNew(int i) {
        int i2 = i;
        try {
            ((LinearLayout) ((ViewGroup) ((AndroidViewComponent) KodularDynamicUtil.getViewHolderById(i2, this.kodularDynamicModelList)).getView()).getChildAt(0)).removeView((Button) KodularDynamicUtil.getObjectById(i2, this.kodularDynamicModelList));
            KodularDynamicUtil.removeItemById(i2, this.kodularDynamicModelList);
        } catch (Exception e) {
            int e2 = Log.e("MakeroidDynamicButton", String.valueOf(e));
        }
    }

    @Deprecated
    @SimpleFunction(description = "This block is DEPRECATED! Please use instead the 'Delete Button' block without arrangement parameter.")
    public final void DeleteButton(int i, HVArrangement hVArrangement) {
        int i2 = i;
        HVArrangement hVArrangement2 = hVArrangement;
        try {
            ((LinearLayout) ((ViewGroup) hVArrangement2.getView()).getChildAt(0)).removeView((Button) KodularDynamicUtil.getObjectById(i2, this.kodularDynamicModelList));
            KodularDynamicUtil.removeItemById(i2, this.kodularDynamicModelList);
        } catch (Exception e) {
            int e2 = Log.e("MakeroidDynamicButton", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Returns the button referenced by its id.")
    public final KodularDynamicUtil.ComponentReturnHelper GetButtonById(int i) {
        KodularDynamicUtil.ComponentReturnHelper componentReturnHelper;
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Button button2 = button;
        if (button == null) {
            return null;
        }
        new KodularDynamicUtil.ComponentReturnHelper(button2);
        return componentReturnHelper;
    }

    @SimpleFunction(description = "Update the Background Color of a button.")
    public final void SetBackgroundColor(int i, int i2) {
        int i3 = i2;
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Button button2 = button;
        if (button != null) {
            button2.getBackground().setColorFilter(i3, PorterDuff.Mode.SRC_ATOP);
        }
    }

    @SimpleFunction(description = "Update the Text Color of a button.")
    public final void SetTextColor(int i, int i2) {
        int i3 = i2;
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Button button2 = button;
        if (button == null) {
            return;
        }
        if (i3 != 0) {
            TextViewUtil.setTextColor(button2, i3);
        } else {
            TextViewUtil.setTextColors(button2, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        }
    }

    @SimpleFunction(description = "Update the Font of a button.")
    public final void SetFont(int i, boolean z, boolean z2) {
        boolean z3 = z;
        boolean z4 = z2;
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Button button2 = button;
        if (button != null) {
            TextViewUtil.setFontTypeface(button2, 0, z3, z4);
        }
    }

    @SimpleFunction(description = "Update the Width of a button.")
    public final void SetWidth(int i, int i2) {
        int i3 = i2;
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Button button2 = button;
        if (button != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button2.getLayoutParams();
            layoutParams.width = i3;
            button2.setLayoutParams(layoutParams);
            button2.invalidate();
        }
    }

    @SimpleFunction(description = "Get the Width of a button.")
    public final int GetWidth(int i) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Button button2 = button;
        if (button != null) {
            return button2.getWidth();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Height of a button.")
    public final void SetHeight(int i, int i2) {
        int i3 = i2;
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Button button2 = button;
        if (button != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button2.getLayoutParams();
            layoutParams.height = i3;
            button2.setLayoutParams(layoutParams);
            button2.invalidate();
        }
    }

    @SimpleFunction(description = "Get the Height of a button.")
    public final int GetHeight(int i) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Button button2 = button;
        if (button != null) {
            return button2.getHeight();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Enabled status of a button.")
    public final void SetEnabled(int i, boolean z) {
        boolean z2 = z;
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Button button2 = button;
        if (button != null) {
            TextViewUtil.setEnabled(button2, z2);
        }
    }

    @SimpleFunction(description = "Get the Enabled status of a button.")
    public final boolean GetEnabled(int i) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Button button2 = button;
        if (button != null) {
            return TextViewUtil.isEnabled(button2);
        }
        return false;
    }

    @SimpleFunction(description = "Update the Font Size of a button.")
    public final void SetFontSize(int i, float f) {
        float f2 = f;
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Button button2 = button;
        if (button != null) {
            TextViewUtil.setFontSize(button2, f2);
        }
    }

    @SimpleFunction(description = "Get the Font Size of a button.")
    public final float GetFontSize(int i) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Button button2 = button;
        if (button != null) {
            return TextViewUtil.getFontSize(button2, this.context);
        }
        return 0.0f;
    }

    @SimpleFunction(description = "Update the Text of a button.")
    public final void SetText(int i, String str) {
        String str2 = str;
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Button button2 = button;
        if (button != null) {
            button2.setText(TextViewUtil.fromHtml(str2));
        }
    }

    @SimpleFunction(description = "Get the Text of a button.")
    public final String GetText(int i) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Button button2 = button;
        if (button != null) {
            return TextViewUtil.getText(button2);
        }
        return "";
    }

    @SimpleEvent(description = "Trigger when a Dynamic Button is clicked.")
    public final void ButtonClick(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ButtonClick", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Trigger when a Dynamic Button is long clicked.")
    public final void ButtonLongClick(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ButtonLongClick", Integer.valueOf(i));
    }
}
