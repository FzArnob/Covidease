package com.google.appinventor.components.runtime;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.runtime.util.KodularHelper;
import com.google.appinventor.components.runtime.util.ProgressHelper;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.lang.reflect.Field;

@SimpleObject
@UsesLibraries(libraries = "spinkit.jar")
public abstract class TextBoxBase extends AndroidViewComponent implements View.OnFocusChangeListener {
    private int PMWpqVMsyfTUgqkfqh3FKX4Q3IYlcybzKrBGEl7qtzs6HNVssJ63V430Ige89pyP;
    private boolean PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    private int backgroundColor;
    private int fontTypeface;
    private Drawable hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    private int ik13O8j78hPb27XeipAJHUcJmdHxYMJFw4gYbTGvLKIOs7SX1F7x61gocQ17TlNc = 0;
    private double rotationAngle = 0.0d;
    private boolean symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = true;
    private boolean tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE;
    private int textColor;
    private String uB4tu69UCQ2bAIAJLxBrazJ0pEJF4D6biU0hi2lcEJLr1A0KTbjBgSa8eZhiikvR;
    protected final EditText view;
    private int vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
    private int yHAbLPerXNK4pCwn5nqbt3OeUjDvQdxh29RmVa0moB4dUtgatbeaGoP5jClPUWFb = -16777216;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TextBoxBase(com.google.appinventor.components.runtime.ComponentContainer r9, android.widget.EditText r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = 0
            r3.rotationAngle = r4
            r3 = r0
            r4 = 0
            r3.ik13O8j78hPb27XeipAJHUcJmdHxYMJFw4gYbTGvLKIOs7SX1F7x61gocQ17TlNc = r4
            r3 = r0
            r4 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r3.yHAbLPerXNK4pCwn5nqbt3OeUjDvQdxh29RmVa0moB4dUtgatbeaGoP5jClPUWFb = r4
            r3 = r0
            r4 = 1
            r3.symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = r4
            r3 = r0
            r4 = r2
            r3.view = r4
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 24
            if (r3 < r4) goto L_0x0028
            r3 = r2
            com.google.appinventor.components.runtime.util.EclairUtil.disableSuggestions(r3)
        L_0x0028:
            r3 = r0
            android.widget.EditText r3 = r3.view
            r4 = r0
            r3.setOnFocusChangeListener(r4)
            r3 = r0
            android.widget.EditText r3 = r3.view
            com.google.appinventor.components.runtime.TextBoxBase$1 r4 = new com.google.appinventor.components.runtime.TextBoxBase$1
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = r0
            r5.<init>(r6)
            r3.addTextChangedListener(r4)
            r3 = r0
            r7 = r3
            r3 = r7
            r4 = r7
            android.widget.EditText r4 = r4.view
            android.graphics.drawable.Drawable r4 = r4.getBackground()
            r3.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = r4
            r3 = r1
            r4 = r0
            r3.$add(r4)
            r3 = r1
            r4 = r0
            r5 = 160(0xa0, float:2.24E-43)
            r3.setChildWidth(r4, r5)
            r3 = r0
            r4 = 0
            r3.TextAlignment(r4)
            r3 = r0
            r4 = 1
            r3.Enabled(r4)
            r3 = r0
            r4 = 0
            r3.fontTypeface = r4
            r3 = r1
            android.app.Activity r3 = r3.$context()
            com.google.appinventor.components.runtime.util.TextViewUtil.setContext(r3)
            r3 = r0
            android.widget.EditText r3 = r3.view
            r4 = r0
            int r4 = r4.fontTypeface
            r5 = r0
            boolean r5 = r5.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC
            r6 = r0
            boolean r6 = r6.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE
            com.google.appinventor.components.runtime.util.TextViewUtil.setFontTypeface(r3, r4, r5, r6)
            r3 = r0
            r4 = 1096810496(0x41600000, float:14.0)
            r3.FontSize(r4)
            r3 = r0
            java.lang.String r4 = ""
            r3.Hint(r4)
            r3 = r0
            java.lang.String r4 = ""
            r3.Text(r4)
            r3 = r0
            r4 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r3.CursorColor(r4)
            r3 = r0
            r4 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r3.TextColor(r4)
            r3 = r0
            r4 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r3.HintColor(r4)
            r3 = r0
            r4 = 0
            r3.RotationAngle(r4)
            r3 = r0
            r4 = 1
            r3.CursorVisible(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.TextBoxBase.<init>(com.google.appinventor.components.runtime.ComponentContainer, android.widget.EditText):void");
    }

    public View getView() {
        return this.view;
    }

    @Deprecated
    @SimpleProperty(description = "DEPRECATED since this feature is not working. Use 'Enabled' instead.")
    public void EnableCopyPaste(boolean z) {
    }

    @Deprecated
    @SimpleProperty
    public boolean EnableCopyPaste() {
        return false;
    }

    @Deprecated
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Please delete this block from your project.This block is deprecated and not longer supported.")
    public int LineColor() {
        return this.ik13O8j78hPb27XeipAJHUcJmdHxYMJFw4gYbTGvLKIOs7SX1F7x61gocQ17TlNc;
    }

    @Deprecated
    @SimpleProperty
    public void LineColor(int i) {
        int i2 = i;
        this.ik13O8j78hPb27XeipAJHUcJmdHxYMJFw4gYbTGvLKIOs7SX1F7x61gocQ17TlNc = i2;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color", propertyType = "advanced")
    @SimpleProperty
    public void CursorColor(int i) {
        int i2 = i;
        this.yHAbLPerXNK4pCwn5nqbt3OeUjDvQdxh29RmVa0moB4dUtgatbeaGoP5jClPUWFb = i2;
        int i3 = i2;
        EditText editText = this.view;
        try {
            Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
            declaredField.setAccessible(true);
            int i4 = declaredField.getInt(editText);
            Field declaredField2 = TextView.class.getDeclaredField("mEditor");
            declaredField2.setAccessible(true);
            Object obj = declaredField2.get(editText);
            Drawable drawable = ContextCompat.getDrawable(editText.getContext(), i4);
            Drawable drawable2 = drawable;
            if (drawable != null) {
                drawable2.setColorFilter(i3, PorterDuff.Mode.SRC_IN);
            }
            Drawable[] drawableArr = new Drawable[2];
            drawableArr[0] = drawable2;
            Drawable[] drawableArr2 = drawableArr;
            drawableArr2[1] = drawable2;
            Drawable[] drawableArr3 = drawableArr2;
            Field declaredField3 = obj.getClass().getDeclaredField("mCursorDrawable");
            declaredField3.setAccessible(true);
            declaredField3.set(obj, drawableArr3);
        } catch (Exception e) {
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of the cursor.")
    public int CursorColor() {
        return this.yHAbLPerXNK4pCwn5nqbt3OeUjDvQdxh29RmVa0moB4dUtgatbeaGoP5jClPUWFb;
    }

    @SimpleFunction(description = "Place a blurred shadow of text underneath the text, drawn with the specified x, y, radius, color (e.g. -11, 12, 13, black ")
    public void SetShadow(float f, float f2, float f3, int i) {
        KodularHelper.setShadow(this.view, f, f2, f3, i);
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "float", propertyType = "advanced")
    @SimpleProperty
    public void RotationAngle(double d) {
        double d2 = d;
        this.rotationAngle = d2;
        this.view.setRotation((float) d2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Sets the degrees that the textbox is rotated around the pivot point. Increasing values result in clockwise rotation.")
    public double RotationAngle() {
        return this.rotationAngle;
    }

    @SimpleEvent(description = "Event to detect text changes.")
    public void OnTextChanged() {
        EventDispatcher.dispatchEvent(this.view, this, "OnTextChanged", new Object[0]);
    }

    @SimpleProperty(description = "Returns the current text length as number.")
    public int TextLength() {
        return this.view.getText().length();
    }

    @SimpleFunction(description = "Set the cursor to the given position.")
    public void SetCursorAt(int i) {
        int i2 = i;
        if (this.view.getText().length() >= i2) {
            this.view.setSelection(i2);
        } else {
            this.view.setSelection(this.view.getText().length());
        }
    }

    @SimpleFunction(description = "Set the cursor to the end of the text.")
    public void SetCursorAtEnd() {
        this.view.setSelection(this.view.getText().length());
    }

    @SimpleProperty(description = "Get the current cursor position.")
    public int CurrentPosition() {
        return this.view.getSelectionStart();
    }

    @SimpleEvent
    public void GotFocus() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotFocus", new Object[0]);
    }

    @SimpleEvent
    public void LostFocus() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LostFocus", new Object[0]);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Whether the text should be left justified, centered, or right justified.  By default, text is left justified.", userVisible = false)
    public int TextAlignment() {
        return this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
    }

    @DesignerProperty(defaultValue = "0", editorType = "textalignment")
    @SimpleProperty(userVisible = false)
    public void TextAlignment(int i) {
        int i2 = i;
        this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = i2;
        TextViewUtil.setAlignment(this.view, i2, false);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The background color of the input box.  You can choose a color by name in the Designer or in the Blocks Editor.  The default background color is 'default' (shaded 3-D look).")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&H00000000", editorType = "color")
    @SimpleProperty
    public void BackgroundColor(int i) {
        int i2 = i;
        this.backgroundColor = i2;
        if (i2 != 0) {
            TextViewUtil.setBackgroundColor(this.view, i2);
        } else {
            ViewUtil.setBackgroundDrawable(this.view, this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the user can enter text into this input box.  By default, this is true.")
    public boolean Enabled() {
        return TextViewUtil.isEnabled(this.view);
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Enabled(boolean z) {
        TextViewUtil.setEnabled(this.view, z);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Whether the font for the text should be bold.  By default, it is not.", userVisible = false)
    public boolean FontBold() {
        return this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(userVisible = false)
    public void FontBold(boolean z) {
        boolean z2 = z;
        this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = z2;
        TextViewUtil.setFontTypeface(this.view, this.fontTypeface, z2, this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Whether the text should appear in italics.  By default, it does not.", userVisible = false)
    public boolean FontItalic() {
        return this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(userVisible = false)
    public void FontItalic(boolean z) {
        boolean z2 = z;
        this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = z2;
        TextViewUtil.setFontTypeface(this.view, this.fontTypeface, this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC, z2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The font size for the text.  By default, it is 14.0 points.")
    public float FontSize() {
        return TextViewUtil.getFontSize(this.view, this.container.$context());
    }

    @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float")
    @SimpleProperty
    public void FontSize(float f) {
        TextViewUtil.setFontSize(this.view, f);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The font for the text.  The value can be changed in the Designer.", userVisible = false)
    public int FontTypeface() {
        return this.fontTypeface;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void FontTypeface(int i) {
        this.fontTypeface = i;
        TextViewUtil.setFontTypeface(this.view, this.fontTypeface, this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC, this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Text that should appear faintly in the input box to provide a hint as to what the user should enter.  This can only be seen if the <code>Text</code> property is empty.")
    public String Hint() {
        return this.uB4tu69UCQ2bAIAJLxBrazJ0pEJF4D6biU0hi2lcEJLr1A0KTbjBgSa8eZhiikvR;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void Hint(String str) {
        String str2 = str;
        this.uB4tu69UCQ2bAIAJLxBrazJ0pEJF4D6biU0hi2lcEJLr1A0KTbjBgSa8eZhiikvR = str2;
        TextViewUtil.setHint(this.view, str2);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String Text() {
        return TextViewUtil.getText(this.view);
    }

    @DesignerProperty(defaultValue = "", editorType = "textArea")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The text in the input box, which can be set by the programmer in the Designer or Blocks Editor, or it can be entered by the user (unless the <code>Enabled</code> property is false).")
    public void Text(String str) {
        TextViewUtil.setText(this.view, str);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color for the text.  You can choose a color by name in the Designer or in the Blocks Editor.  The default text color is black.")
    public int TextColor() {
        return this.textColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty
    public void TextColor(int i) {
        int i2 = i;
        this.textColor = i2;
        if (i2 != 0) {
            TextViewUtil.setTextColor(this.view, i2);
        } else {
            TextViewUtil.setTextColor(this.view, -16777216);
        }
    }

    @SimpleFunction(description = "Sets the textbox active.")
    public void RequestFocus() {
        boolean requestFocus = this.view.requestFocus();
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public void FontTypefaceImport(String str) {
        String str2 = str;
        if (str2 != null) {
            TextViewUtil.setCustomFontTypeface(this.container.$form(), this.view, str2, this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC, this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE);
        }
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Set a custom hint text color.")
    public void HintColor(int i) {
        int i2 = i;
        this.PMWpqVMsyfTUgqkfqh3FKX4Q3IYlcybzKrBGEl7qtzs6HNVssJ63V430Ige89pyP = i2;
        if (i2 != 0) {
            TextViewUtil.setHintColor(this.view, i2);
        } else {
            TextViewUtil.setHintColor(this.view, -16777216);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Return the hint text color.")
    public int HintColor() {
        return this.PMWpqVMsyfTUgqkfqh3FKX4Q3IYlcybzKrBGEl7qtzs6HNVssJ63V430Ige89pyP;
    }

    public void onFocusChange(View view2, boolean z) {
        View view3 = view2;
        if (z) {
            GotFocus();
        } else {
            LostFocus();
        }
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "Makes the cursor visible (the default) or invisible.")
    public void CursorVisible(boolean z) {
        boolean z2 = z;
        this.symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = z2;
        this.view.setCursorVisible(z2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean CursorVisible() {
        return this.symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK;
    }

    @SimpleFunction(description = "Allows you to set animation style. Valid (case-insensitive) values are: ChasingDots, Circle, CubeGrid, DoubleBounce, FadingCircle, FoldingCube, Pulse, RotatingCircle, RotatingPlane, ThreeBounce, WanderingCubes, Wave. If invalid style is used, animation will be removed.Position can be: top, left, right, bottom. Size can be 100. ")
    public void AnimationStyle(String str, String str2, int i, int i2) {
        ProgressHelper.setButtonProgressAnimation(this.view, str, str2, i, i2);
        this.view.setVisibility(8);
        this.view.setVisibility(0);
        this.view.invalidate();
    }
}
