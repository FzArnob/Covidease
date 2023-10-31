package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularDynamicModel;
import com.google.appinventor.components.runtime.util.KodularDynamicUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DYNAMIC, description = "A component to create dynamic textboxes in Arrangements", helpUrl = "https://docs.kodular.io/components/dynamic/dynamic-textbox/", iconName = "images/textbox.png", nonVisible = true, version = 4)
public final class MakeroidDynamicTextBox extends AndroidNonvisibleComponent {
    private final String LOG_TAG = "KodularDynamicTextBox";
    private Context context;
    private Drawable hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    private List<KodularDynamicModel> kodularDynamicModelList;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidDynamicTextBox(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = "KodularDynamicTextBox"
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
            r2 = r1
            android.app.Activity r2 = r2.$context()
            com.google.appinventor.components.runtime.util.TextViewUtil.setContext(r2)
            android.widget.EditText r2 = new android.widget.EditText
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
            r2.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidDynamicTextBox.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Create a Dynamic TextBox")
    public final void CreateTextBox(int i, AndroidViewComponent androidViewComponent) {
        EditText editText;
        TextWatcher textWatcher;
        Object obj;
        int i2 = i;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        new EditText(this.context);
        EditText editText2 = editText;
        EditText editText3 = editText2;
        TextViewUtil.setFontTypeface(editText2, 0, false, false);
        TextViewUtil.setTextColor(editText3, -16777216);
        final int i3 = i2;
        new TextWatcher(this) {
            private /* synthetic */ MakeroidDynamicTextBox hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public final void afterTextChanged(Editable editable) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.OnTextChanged(i3, editable.toString());
            }
        };
        editText3.addTextChangedListener(textWatcher);
        new KodularDynamicModel(i2, editText3, androidViewComponent2);
        boolean add = this.kodularDynamicModelList.add(obj);
        ((LinearLayout) ((ViewGroup) androidViewComponent2.getView()).getChildAt(0)).addView(editText3);
    }

    @SimpleFunction(description = "Remove a textbox component with the given id.")
    public final void DeleteTextBox(int i) {
        int i2 = i;
        try {
            ((LinearLayout) ((ViewGroup) ((AndroidViewComponent) KodularDynamicUtil.getViewHolderById(i2, this.kodularDynamicModelList)).getView()).getChildAt(0)).removeView((EditText) KodularDynamicUtil.getObjectById(i2, this.kodularDynamicModelList));
            KodularDynamicUtil.removeItemById(i2, this.kodularDynamicModelList);
        } catch (Exception e) {
            int e2 = Log.e("KodularDynamicTextBox", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Returns the textbox referenced by its id.")
    public final KodularDynamicUtil.ComponentReturnHelper GetTextBoxById(int i) {
        KodularDynamicUtil.ComponentReturnHelper componentReturnHelper;
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        EditText editText2 = editText;
        if (editText == null) {
            return null;
        }
        new KodularDynamicUtil.ComponentReturnHelper(editText2);
        return componentReturnHelper;
    }

    @SimpleFunction(description = "Update the Background Color of a TextBox")
    public final void SetBackgroundColor(int i, int i2) {
        int i3 = i2;
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        EditText editText2 = editText;
        if (editText == null) {
            return;
        }
        if (i3 != 0) {
            TextViewUtil.setBackgroundColor(editText2, i3);
        } else {
            ViewUtil.setBackgroundDrawable(editText2, this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO);
        }
    }

    @SimpleFunction(description = "Update the Text Color of a TextBox")
    public final void SetTextColor(int i, int i2) {
        int i3 = i2;
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        EditText editText2 = editText;
        if (editText == null) {
            return;
        }
        if (i3 != 0) {
            TextViewUtil.setTextColor(editText2, i3);
        } else {
            TextViewUtil.setTextColor(editText2, -16777216);
        }
    }

    @SimpleFunction(description = "Update the Font of a TextBox")
    public final void SetFont(int i, boolean z, boolean z2) {
        boolean z3 = z;
        boolean z4 = z2;
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        EditText editText2 = editText;
        if (editText != null) {
            TextViewUtil.setFontTypeface(editText2, 0, z3, z4);
        }
    }

    @SimpleFunction(description = "Update the Width of a TextBox")
    public final void SetWidth(int i, int i2) {
        int i3 = i2;
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        EditText editText2 = editText;
        if (editText != null) {
            editText2.setWidth(i3);
        }
    }

    @SimpleFunction(description = "Get the Width of a TextBox")
    public final int GetWidth(int i) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        EditText editText2 = editText;
        if (editText != null) {
            return editText2.getWidth();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Height of a TextBox")
    public final void SetHeight(int i, int i2) {
        int i3 = i2;
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        EditText editText2 = editText;
        if (editText != null) {
            editText2.setHeight(i3);
        }
    }

    @SimpleFunction(description = "Get the Height of a TextBox")
    public final int GetHeight(int i) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        EditText editText2 = editText;
        if (editText != null) {
            return editText2.getHeight();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Enabled status of a TextBox")
    public final void SetEnabled(int i, boolean z) {
        boolean z2 = z;
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        EditText editText2 = editText;
        if (editText != null) {
            TextViewUtil.setEnabled(editText2, z2);
        }
    }

    @SimpleFunction(description = "Get the Enabled status of a TextBox")
    public final boolean GetEnabled(int i) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        EditText editText2 = editText;
        if (editText != null) {
            return TextViewUtil.isEnabled(editText2);
        }
        return false;
    }

    @SimpleFunction(description = "Update the Font Size of a TextBox")
    public final void SetFontSize(int i, float f) {
        float f2 = f;
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        EditText editText2 = editText;
        if (editText != null) {
            TextViewUtil.setFontSize(editText2, f2);
        }
    }

    @SimpleFunction(description = "Get the Font Size of a TextBox")
    public final float GetFontSize(int i) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        EditText editText2 = editText;
        if (editText != null) {
            return TextViewUtil.getFontSize(editText2, this.context);
        }
        return 0.0f;
    }

    @SimpleFunction(description = "Update the Text of a TextBox")
    public final void SetText(int i, String str) {
        String str2 = str;
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        EditText editText2 = editText;
        if (editText != null) {
            TextViewUtil.setText(editText2, str2);
        }
    }

    @SimpleFunction(description = "Get the Text of a TextBox")
    public final String GetText(int i) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        EditText editText2 = editText;
        if (editText != null) {
            return TextViewUtil.getText(editText2);
        }
        return "";
    }

    @SimpleFunction(description = "Update the Text Alignment of a TextBox. 0 = left, 1 = center and 2 = right.")
    public final void SetAlignment(int i, int i2) {
        int i3 = i2;
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        EditText editText2 = editText;
        if (editText != null) {
            TextViewUtil.setAlignment(editText2, i3, false);
        }
    }

    @SimpleEvent(description = "Trigger when the text of a Dynamic TextBox changes")
    public final void OnTextChanged(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnTextChanged", objArr2);
    }
}
