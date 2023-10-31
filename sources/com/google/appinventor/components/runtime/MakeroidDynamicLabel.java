package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularDynamicModel;
import com.google.appinventor.components.runtime.util.KodularDynamicUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DYNAMIC, description = "A component to create dynamic labels in Arrangements", iconName = "images/label.png", nonVisible = true, version = 3)
public final class MakeroidDynamicLabel extends AndroidNonvisibleComponent {
    private final String LOG_TAG = "KodularDynamicLabel";
    private Context context;
    private List<KodularDynamicModel> kodularDynamicModelList;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidDynamicLabel(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = "KodularDynamicLabel"
            r2.LOG_TAG = r3
            r2 = r0
            java.util.ArrayList r3 = new java.util.ArrayList
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.kodularDynamicModelList = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidDynamicLabel.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Create a Dynamic Label")
    public final void CreateLabel(int i, AndroidViewComponent androidViewComponent) {
        TextView textView;
        Object obj;
        int i2 = i;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        try {
            new TextView(this.context);
            TextView textView2 = textView;
            TextView textView3 = textView2;
            TextViewUtil.setFontTypeface(textView2, 0, false, false);
            TextViewUtil.setTextColor(textView3, -16777216);
            new KodularDynamicModel(i2, textView3, androidViewComponent2);
            boolean add = this.kodularDynamicModelList.add(obj);
            ((LinearLayout) ((ViewGroup) androidViewComponent2.getView()).getChildAt(0)).addView(textView3);
        } catch (Exception e) {
            int e2 = Log.e("KodularDynamicLabel", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Remove a label component with the given id.")
    public final void DeleteLabel(int i) {
        int i2 = i;
        try {
            ((LinearLayout) ((ViewGroup) ((AndroidViewComponent) KodularDynamicUtil.getViewHolderById(i2, this.kodularDynamicModelList)).getView()).getChildAt(0)).removeView((TextView) KodularDynamicUtil.getObjectById(i2, this.kodularDynamicModelList));
            KodularDynamicUtil.removeItemById(i2, this.kodularDynamicModelList);
        } catch (Exception e) {
            int e2 = Log.e("KodularDynamicLabel", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Returns the label referenced by its id.")
    public final KodularDynamicUtil.ComponentReturnHelper GetLabelById(int i) {
        KodularDynamicUtil.ComponentReturnHelper componentReturnHelper;
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        TextView textView2 = textView;
        if (textView == null) {
            return null;
        }
        new KodularDynamicUtil.ComponentReturnHelper(textView2);
        return componentReturnHelper;
    }

    @SimpleFunction(description = "Update the Background Color of a Label")
    public final void SetBackgroundColor(int i, int i2) {
        int i3 = i2;
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        TextView textView2 = textView;
        if (textView == null) {
            return;
        }
        if (i3 != 0) {
            TextViewUtil.setBackgroundColor(textView2, i3);
        } else {
            TextViewUtil.setBackgroundColor(textView2, 16777215);
        }
    }

    @SimpleFunction(description = "Update the Text Color of a Label")
    public final void SetTextColor(int i, int i2) {
        int i3 = i2;
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        TextView textView2 = textView;
        if (textView == null) {
            return;
        }
        if (i3 != 0) {
            TextViewUtil.setTextColor(textView2, i3);
        } else {
            TextViewUtil.setTextColor(textView2, -16777216);
        }
    }

    @SimpleFunction(description = "Update the Font of a Label")
    public final void SetFont(int i, boolean z, boolean z2) {
        boolean z3 = z;
        boolean z4 = z2;
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        TextView textView2 = textView;
        if (textView != null) {
            TextViewUtil.setFontTypeface(textView2, 0, z3, z4);
        }
    }

    @SimpleFunction(description = "Update the Width of a Label")
    public final void SetWidth(int i, int i2) {
        int i3 = i2;
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        TextView textView2 = textView;
        if (textView != null) {
            textView2.setWidth(i3);
        }
    }

    @SimpleFunction(description = "Get the Width of a Label")
    public final int GetWidth(int i) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        TextView textView2 = textView;
        if (textView != null) {
            return textView2.getWidth();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Height of a Label")
    public final void SetHeight(int i, int i2) {
        int i3 = i2;
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        TextView textView2 = textView;
        if (textView != null) {
            textView2.setHeight(i3);
        }
    }

    @SimpleFunction(description = "Get the Height of a Label")
    public final int GetHeight(int i) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        TextView textView2 = textView;
        if (textView != null) {
            return textView2.getHeight();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Font Size of a Label")
    public final void SetFontSize(int i, float f) {
        float f2 = f;
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        TextView textView2 = textView;
        if (textView != null) {
            TextViewUtil.setFontSize(textView2, f2);
        }
    }

    @SimpleFunction(description = "Get the Font Size of a Label")
    public final float GetFontSize(int i) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        TextView textView2 = textView;
        if (textView != null) {
            return TextViewUtil.getFontSize(textView2, this.context);
        }
        return 0.0f;
    }

    @SimpleFunction(description = "Update the Text of a Label")
    public final void SetText(int i, String str, boolean z) {
        String str2 = str;
        boolean z2 = z;
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        TextView textView2 = textView;
        if (textView == null) {
            return;
        }
        if (z2) {
            TextViewUtil.setTextHTML(textView2, str2);
        } else {
            TextViewUtil.setText(textView2, str2);
        }
    }

    @SimpleFunction(description = "Get the Text of a Label")
    public final String GetText(int i) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        TextView textView2 = textView;
        if (textView != null) {
            return TextViewUtil.getText(textView2);
        }
        return "";
    }

    @SimpleFunction(description = "Update the Text Alignment of a Label. 0 = left, 1 = center and 2 = right.")
    public final void SetAlignment(int i, int i2) {
        int i3 = i2;
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        TextView textView2 = textView;
        if (textView != null) {
            TextViewUtil.setAlignment(textView2, i3, false);
        }
    }

    @SimpleProperty(description = "Left alignment (0)")
    public final int AlignmentLeft() {
        return 0;
    }

    @SimpleProperty(description = "Center alignment (1)")
    public final int AlignmentCenter() {
        return 1;
    }

    @SimpleProperty(description = "Right alignment (2)")
    public final int AlignmentRight() {
        return 2;
    }
}
