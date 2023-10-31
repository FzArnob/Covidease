package com.google.appinventor.components.runtime;

import android.content.Context;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.kofigyan.stateprogressbar.StateProgressBar;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "in ode", iconName = "images/stateProgressBar.png", nonVisible = false, version = 1)
@UsesLibraries(libraries = "stateProgressBar.aar, stateProgressBar.jar")
public final class KodularStateProgressBar extends AndroidViewComponent {
    private Context context;
    private final StateProgressBar hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularStateProgressBar(com.google.appinventor.components.runtime.ComponentContainer r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            com.kofigyan.stateprogressbar.StateProgressBar r3 = new com.kofigyan.stateprogressbar.StateProgressBar
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            android.content.Context r5 = r5.context
            r4.<init>(r5)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.kofigyan.stateprogressbar.StateProgressBar r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.KodularStateProgressBar$1 r3 = new com.google.appinventor.components.runtime.KodularStateProgressBar$1
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            r4.<init>(r5)
            r2.setOnStateItemClickListener(r3)
            r2 = r0
            com.kofigyan.stateprogressbar.StateProgressBar r2 = r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.KodularStateProgressBar$2 r3 = new com.google.appinventor.components.runtime.KodularStateProgressBar$2
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            r4.<init>(r5)
            r2.setOnErrorListener(r3)
            r2 = r0
            android.content.Context r2 = r2.context
            com.google.appinventor.components.runtime.util.TextViewUtil.setContext(r2)
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r0
            java.lang.String r3 = "Item 1, Item 2, Item 3, Item 4, Item 5"
            r2.ElementsFromString(r3)
            r2 = r0
            r3 = 0
            r2.StateDescriptionTypeface(r3)
            r2 = r0
            r3 = 0
            r2.StateNumberTypeface(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.StateDescriptionTypefaceImport(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.StateNumberTypefaceImport(r3)
            r2 = r0
            r3 = 0
            r2.StateNumberIsDescending(r3)
            r2 = r0
            r3 = 1
            r2.JustifyMultilineDescription(r3)
            r2 = r0
            r3 = 1
            r2.AnimationToCurrentState(r3)
            r2 = r0
            r3 = 2
            r2.MaxDescriptionLine(r3)
            r2 = r0
            r3 = 5
            r2.MaxStateNumber(r3)
            r2 = r0
            r3 = 1
            r2.CurrentStateNumber(r3)
            r2 = r0
            r3 = 250(0xfa, float:3.5E-43)
            r2.AnimationDuration(r3)
            r2 = r0
            r3 = 1109393408(0x42200000, float:40.0)
            r2.StateProgressBarHeight(r3)
            r2 = r0
            r3 = 1101004800(0x41a00000, float:20.0)
            r2.StateNumberTextSize(r3)
            r2 = r0
            r3 = 1092616192(0x41200000, float:10.0)
            r2.StateLineThickness(r3)
            r2 = r0
            r3 = 1092616192(0x41200000, float:10.0)
            r2.DescriptionTopSpaceIncrementer(r3)
            r2 = r0
            r3 = 1099956224(0x41900000, float:18.0)
            r2.StateDescriptionSize(r3)
            r2 = r0
            r3 = 1084227584(0x40a00000, float:5.0)
            r2.DescriptionLinesSpacing(r3)
            r2 = r0
            r3 = -11751600(0xffffffffff4caf50, float:-2.7207279E38)
            r2.ForegroundColor(r3)
            r2 = r0
            r3 = -12303292(0xffffffffff444444, float:-2.6088314E38)
            r2.BackgroundColor(r3)
            r2 = r0
            r3 = -1
            r2.StateNumberForegroundColor(r3)
            r2 = r0
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2.StateNumberBackgroundColor(r3)
            r2 = r0
            r3 = -11751600(0xffffffffff4caf50, float:-2.7207279E38)
            r2.CurrentStateDescriptionColor(r3)
            r2 = r0
            r3 = -12303292(0xffffffffff444444, float:-2.6088314E38)
            r2.StateDescriptionColor(r3)
            java.lang.String r2 = "Kodular StateProgress"
            java.lang.String r3 = "Kodular StateProgressBar Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularStateProgressBar.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public final View getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleEvent(description = "Event invoked when a state item was clicked.")
    public final void StateItemClick(int i, boolean z) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = Boolean.valueOf(z);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "StateItemClick", objArr2);
    }

    @SimpleEvent(description = "Event invoked when a error occurred.")
    public final void ErrorOccurred(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ErrorOccurred", str);
    }

    @SimpleProperty(description = "If set to true all states are completed.")
    public final void AllStatesCompleted(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAllStatesCompleted(z);
    }

    @SimpleProperty(description = "Returns true if 'All States Completed' is enabled.")
    public final boolean AllStatesCompleted() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isAllStatesCompletedEnabled();
    }

    @DesignerProperty(defaultValue = "Item 1, Item 2, Item 3, Item 4, Item 5", editorType = "textArea")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The State Progress Bar elements specified as a string with the items separated by commas such as: Item 1, Item 2, Item 3, Item 4, Item 5. Each word before the comma will be an element in the list. You can add maximum 5 items.")
    public final void ElementsFromString(String str) {
        String str2 = str;
        if (str2 != null && !str2.isEmpty()) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateDescriptionData(str2.split(","));
        }
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If set to true the state number is in descending order.")
    public final void StateNumberIsDescending(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateNumberIsDescending(z);
    }

    @SimpleProperty(description = "Returns true if the state number order is descending.")
    public final boolean StateNumberIsDescending() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateNumberIsDescending();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "If set to true justify multiline description is enabled.")
    public final void JustifyMultilineDescription(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setJustifyMultilineDescription(z);
    }

    @SimpleProperty(description = "Returns true if the state number order is descending.")
    public final boolean JustifyMultilineDescription() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isDescriptionMultiline();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "If set to true the states will use a animation when they changed.")
    public final void AnimationToCurrentState(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.enableAnimationToCurrentState(z);
    }

    @SimpleProperty(description = "Returns true if the state number order is descending.")
    public final boolean AnimationToCurrentState() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isEnableAnimationToCurrentState();
    }

    @DesignerProperty(defaultValue = "250", editorType = "integer")
    @SimpleProperty(description = "Set the animation duration in milliseconds. Example:  use 1000 for 1 second.")
    public final void AnimationDuration(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAnimationDuration(i);
    }

    @SimpleProperty(description = "Returns the animation duration in milliseconds.")
    public final int AnimationDuration() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getAnimationDuration();
    }

    @DesignerProperty(defaultValue = "2", editorType = "integer")
    @SimpleProperty(description = "Set the maximum description line.")
    public final void MaxDescriptionLine(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxDescriptionLine(i);
    }

    @SimpleProperty(description = "Returns the maximum description line.")
    public final int MaxDescriptionLine() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMaxDescriptionLine();
    }

    @DesignerProperty(defaultValue = "5", editorType = "state_number")
    @SimpleProperty(description = "Set the maximum state number.")
    public final void MaxStateNumber(int i) {
        int i2 = i;
        if (i2 == 1) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxStateNumber(StateProgressBar.StateNumber.ONE);
        } else if (i2 == 2) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxStateNumber(StateProgressBar.StateNumber.TWO);
        } else if (i2 == 3) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxStateNumber(StateProgressBar.StateNumber.THREE);
        } else if (i2 == 4) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxStateNumber(StateProgressBar.StateNumber.FOUR);
        } else {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxStateNumber(StateProgressBar.StateNumber.FIVE);
        }
    }

    @SimpleProperty(description = "Returns the maximum state number.")
    public final int MaxStateNumber() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMaxStateNumber();
    }

    @DesignerProperty(defaultValue = "1", editorType = "state_number")
    @SimpleProperty(description = "Set the current state number.")
    public final void CurrentStateNumber(int i) {
        int i2 = i;
        int maxStateNumber = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMaxStateNumber();
        if (i2 != 1 || i2 > maxStateNumber) {
            if (i2 == 2 && i2 <= maxStateNumber) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                return;
            } else if (i2 == 3 && i2 <= maxStateNumber) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                return;
            } else if (i2 == 4 && i2 <= maxStateNumber) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                return;
            } else if (i2 == 5 && i2 <= maxStateNumber) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCurrentStateNumber(StateProgressBar.StateNumber.FIVE);
                return;
            }
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
    }

    @SimpleProperty(description = "Returns the current state number.")
    public final int CurrentStateNumber() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCurrentStateNumber();
    }

    @DesignerProperty(defaultValue = "40.0", editorType = "non_negative_float")
    @SimpleProperty(description = "Set the state progress bar height.")
    public final void StateProgressBarHeight(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateSize(f);
    }

    @SimpleProperty(description = "Returns the state progress bar height.")
    public final float StateProgressBarHeight() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateSize();
    }

    @DesignerProperty(defaultValue = "20.0", editorType = "non_negative_float")
    @SimpleProperty(description = "Set the state number text size.")
    public final void StateNumberTextSize(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateNumberTextSize(f);
    }

    @SimpleProperty(description = "Returns the state number text size.")
    public final float StateNumberTextSize() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateNumberTextSize();
    }

    @DesignerProperty(defaultValue = "10.0", editorType = "non_negative_float")
    @SimpleProperty(description = "Set the state line thickness.")
    public final void StateLineThickness(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateLineThickness(f);
    }

    @SimpleProperty(description = "Returns the state line thickness.")
    public final float StateLineThickness() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateLineThickness();
    }

    @DesignerProperty(defaultValue = "10.0", editorType = "non_negative_float")
    @SimpleProperty(description = "Set the description top space incrementer.")
    public final void DescriptionTopSpaceIncrementer(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setDescriptionTopSpaceIncrementer(f);
    }

    @SimpleProperty(description = "Returns the description top space incrementer.")
    public final float DescriptionTopSpaceIncrementer() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDescriptionTopSpaceIncrementer();
    }

    @DesignerProperty(defaultValue = "18.0", editorType = "non_negative_float")
    @SimpleProperty(description = "Set the state description size.")
    public final void StateDescriptionSize(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateDescriptionSize(f);
    }

    @SimpleProperty(description = "Returns the state description size.")
    public final float StateDescriptionSize() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateDescriptionSize();
    }

    @DesignerProperty(defaultValue = "5.0", editorType = "non_negative_float")
    @SimpleProperty(description = "Set the description lines spacing.")
    public final void DescriptionLinesSpacing(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setDescriptionLinesSpacing(f);
    }

    @SimpleProperty(description = "Returns the description lines spacing.")
    public final float DescriptionLinesSpacing() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDescriptionLinesSpacing();
    }

    @DesignerProperty(defaultValue = "&HFF4CAF50", editorType = "color")
    @SimpleProperty(description = "Change the foreground color.")
    public final void ForegroundColor(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setForegroundColor(i);
    }

    @SimpleProperty(description = "Returns the foreground color.")
    public final int ForegroundColor() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getForegroundColor();
    }

    @DesignerProperty(defaultValue = "&HFF444444", editorType = "color")
    @SimpleProperty(description = "Change the background color.")
    public final void BackgroundColor(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(i);
    }

    @SimpleProperty(description = "Returns the background color.")
    public final int BackgroundColor() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBackgroundColor();
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Change the state number foreground color.")
    public final void StateNumberForegroundColor(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateNumberForegroundColor(i);
    }

    @SimpleProperty(description = "Returns the state number foreground color.")
    public final int StateNumberForegroundColor() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateNumberForegroundColor();
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Change the state number background color.")
    public final void StateNumberBackgroundColor(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateNumberBackgroundColor(i);
    }

    @SimpleProperty(description = "Returns the state number background color.")
    public final int StateNumberBackgroundColor() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateNumberBackgroundColor();
    }

    @DesignerProperty(defaultValue = "&HFF4CAF50", editorType = "color")
    @SimpleProperty(description = "Change the current state description color.")
    public final void CurrentStateDescriptionColor(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCurrentStateDescriptionColor(i);
    }

    @SimpleProperty(description = "Returns the current state description color.")
    public final int CurrentStateDescriptionColor() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCurrentStateDescriptionColor();
    }

    @DesignerProperty(defaultValue = "&HFF444444", editorType = "color")
    @SimpleProperty(description = "Change the state description color.")
    public final void StateDescriptionColor(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateDescriptionColor(i);
    }

    @SimpleProperty(description = "Returns the state description color.")
    public final int StateDescriptionColor() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateDescriptionColor();
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public final void StateDescriptionTypeface(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateDescriptionTypefaceFile(TextViewUtil.getTitleBarTypeFace(i));
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font for state description typeface.")
    public final void StateDescriptionTypefaceImport(String str) {
        String str2 = str;
        if (str2 != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateDescriptionTypefaceFile(TextViewUtil.getTitleBarCustomTypeFace(this.container.$form(), str2));
        }
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public final void StateNumberTypeface(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateNumberTypefaceFile(TextViewUtil.getTitleBarTypeFace(i));
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font for state number typeface.")
    public final void StateNumberTypefaceImport(String str) {
        String str2 = str;
        if (str2 != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateNumberTypefaceFile(TextViewUtil.getTitleBarCustomTypeFace(this.container.$form(), str2));
        }
    }
}
