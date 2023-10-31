package com.google.appinventor.components.runtime;

import android.content.res.ColorStateList;
import android.support.p000v4.widget.CompoundButtonCompat;
import android.support.p003v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.CompoundButton;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularHelper;
import com.google.appinventor.components.runtime.util.TextViewUtil;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "Checkbox that raises an event when the user clicks on it. There are many properties affecting its appearance that can be set in the Designer or Blocks Editor.", helpUrl = "https://docs.kodular.io/components/user-interface/checkbox/", version = 6)
public final class CheckBox extends AndroidViewComponent implements View.OnClickListener, View.OnFocusChangeListener, CompoundButton.OnCheckedChangeListener {
    private boolean PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = false;
    private int backgroundColor = 16777215;
    private int fontTypeface;
    private final AppCompatCheckBox hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = -16777216;
    private boolean tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = false;
    private int textColor = -16777216;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CheckBox(com.google.appinventor.components.runtime.ComponentContainer r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = 16777215(0xffffff, float:2.3509886E-38)
            r2.backgroundColor = r3
            r2 = r0
            r3 = 0
            r2.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = r3
            r2 = r0
            r3 = 0
            r2.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = r3
            r2 = r0
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2.textColor = r3
            r2 = r0
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = r3
            r2 = r0
            android.support.v7.widget.AppCompatCheckBox r3 = new android.support.v7.widget.AppCompatCheckBox
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r4.<init>(r5)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            android.support.v7.widget.AppCompatCheckBox r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = r0
            r2.setOnFocusChangeListener(r3)
            r2 = r0
            android.support.v7.widget.AppCompatCheckBox r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = r0
            r2.setOnCheckedChangeListener(r3)
            r2 = r0
            android.support.v7.widget.AppCompatCheckBox r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = r0
            r2.setOnClickListener(r3)
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r0
            r3 = 16777215(0xffffff, float:2.3509886E-38)
            r2.BackgroundColor(r3)
            r2 = r0
            r3 = 1
            r2.Enabled(r3)
            r2 = r0
            r3 = 0
            r2.fontTypeface = r3
            r2 = r1
            android.app.Activity r2 = r2.$context()
            com.google.appinventor.components.runtime.util.TextViewUtil.setContext(r2)
            r2 = r0
            android.support.v7.widget.AppCompatCheckBox r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = r0
            int r3 = r3.fontTypeface
            r4 = r0
            boolean r4 = r4.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC
            r5 = r0
            boolean r5 = r5.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE
            com.google.appinventor.components.runtime.util.TextViewUtil.setFontTypeface(r2, r3, r4, r5)
            r2 = r0
            r3 = 1096810496(0x41600000, float:14.0)
            r2.FontSize(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.Text(r3)
            r2 = r0
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2.TextColor(r3)
            r2 = r0
            r3 = 0
            r2.Checked(r3)
            r2 = r0
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2.CheckboxColor(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.CheckBox.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public final View getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleEvent
    public final void Changed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Changed", new Object[0]);
    }

    @SimpleEvent
    public final void Click() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
    }

    @SimpleEvent
    public final void GotFocus() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotFocus", new Object[0]);
    }

    @SimpleEvent
    public final void LostFocus() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LostFocus", new Object[0]);
    }

    @SimpleFunction(description = "Place a blurred shadow of text underneath the text, drawn with the specified x, y, radius, color (e.g. -11, 12, 13, black ")
    public final void SetShadow(float f, float f2, float f3, int i) {
        KodularHelper.setShadow(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, f, f2, f3, i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public final int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&H00FFFFFF", editorType = "color")
    @SimpleProperty
    public final void BackgroundColor(int i) {
        int i2 = i;
        this.backgroundColor = i2;
        if (i2 != 0) {
            TextViewUtil.setBackgroundColor(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, i2);
        } else {
            TextViewUtil.setBackgroundColor(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 16777215);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final boolean Enabled() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isEnabled();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public final void Enabled(boolean z) {
        TextViewUtil.setEnabled(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, z);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final boolean FontBold() {
        return this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(userVisible = false)
    public final void FontBold(boolean z) {
        boolean z2 = z;
        this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = z2;
        TextViewUtil.setFontTypeface(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.fontTypeface, z2, this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final boolean FontItalic() {
        return this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(userVisible = false)
    public final void FontItalic(boolean z) {
        boolean z2 = z;
        this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = z2;
        TextViewUtil.setFontTypeface(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.fontTypeface, this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC, z2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public final float FontSize() {
        return TextViewUtil.getFontSize(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.container.$context());
    }

    @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float")
    @SimpleProperty
    public final void FontSize(float f) {
        TextViewUtil.setFontSize(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, f);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final int FontTypeface() {
        return this.fontTypeface;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public final void FontTypeface(int i) {
        this.fontTypeface = i;
        TextViewUtil.setFontTypeface(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.fontTypeface, this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC, this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public final String Text() {
        return TextViewUtil.getText(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
    }

    @DesignerProperty(defaultValue = "", editorType = "textArea")
    @SimpleProperty
    public final void Text(String str) {
        TextViewUtil.setText(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public final int TextColor() {
        return this.textColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty
    public final void TextColor(int i) {
        int i2 = i;
        this.textColor = i2;
        if (i2 != 0) {
            TextViewUtil.setTextColor(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, i2);
        } else {
            TextViewUtil.setTextColor(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, -16777216);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final boolean Checked() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isChecked();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public final void Checked(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setChecked(z);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        CompoundButton compoundButton2 = compoundButton;
        boolean z2 = z;
        Changed();
    }

    public final void onClick(View view) {
        View view2 = view;
        Click();
    }

    public final void onFocusChange(View view, boolean z) {
        View view2 = view;
        if (z) {
            GotFocus();
        } else {
            LostFocus();
        }
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Change the checkbox component color.")
    public final void CheckboxColor(int i) {
        int i2 = i;
        this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = i2;
        CompoundButtonCompat.setButtonTintList(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, ColorStateList.valueOf(i2));
    }

    @SimpleProperty(description = "Return the checkbox component color.")
    public final int CheckboxColor() {
        return this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j;
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public final void FontTypefaceImport(String str) {
        String str2 = str;
        if (str2 != null) {
            TextViewUtil.setCustomFontTypeface(this.container.$form(), this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str2, this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC, this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE);
        }
    }
}
