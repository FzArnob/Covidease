package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.INTERNAL, description = "<p>A box for entering passwords.  This is the same as the ordinary <code>TextBox</code> component except this does not display the characters typed by the user.</p><p>The value of the text in the box can be found or set through the <code>Text</code> property. If blank, the <code>Hint</code> property, which appears as faint text in the box, can provide the user with guidance as to what to type.</p> <p>Text boxes are usually used with the <code>Button</code> component, with the user clicking on the button when text entry is complete.</p>", version = 6)
public final class PasswordTextBox extends TextBoxBase {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PasswordTextBox(com.google.appinventor.components.runtime.ComponentContainer r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            android.support.v7.widget.AppCompatEditText r4 = new android.support.v7.widget.AppCompatEditText
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = r1
            android.app.Activity r6 = r6.$context()
            r5.<init>(r6)
            r2.<init>(r3, r4)
            r2 = r0
            android.widget.EditText r2 = r2.view
            r3 = 128(0x80, float:1.794E-43)
            r2.setRawInputType(r3)
            r2 = r0
            android.widget.EditText r2 = r2.view
            r3 = 1
            r2.setSingleLine(r3)
            r2 = r0
            android.widget.EditText r2 = r2.view
            android.text.method.PasswordTransformationMethod r3 = new android.text.method.PasswordTransformationMethod
            r7 = r3
            r3 = r7
            r4 = r7
            r4.<init>()
            r2.setTransformationMethod(r3)
            r2 = r0
            android.widget.EditText r2 = r2.view
            r3 = 6
            r2.setImeOptions(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.PasswordTextBox.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }
}
