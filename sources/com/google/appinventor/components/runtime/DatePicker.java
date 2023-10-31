package com.google.appinventor.components.runtime;

import android.app.DatePickerDialog;
import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.Dates;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "<p>A button that, when clicked on, launches a popup dialog to allow the user to select a date.</p>", version = 8)
public class DatePicker extends ButtonBase {
    /* access modifiers changed from: private */
    public int F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho;
    /* access modifiers changed from: private */
    public int KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    private int LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn;
    private Handler androidUIHandler;
    private Form form;
    private boolean gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R = false;
    private DatePickerDialog.OnDateSetListener hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private DatePickerDialog f367hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Calendar f368hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    /* access modifiers changed from: private */
    public int vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE;
    private String[] vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;

    static /* synthetic */ int B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DatePicker datePicker, int i) {
        int i2 = i;
        int i3 = i2;
        datePicker.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = i3;
        return i2;
    }

    static /* synthetic */ int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DatePicker datePicker, int i) {
        int i2 = i;
        int i3 = i2;
        datePicker.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = i3;
        return i2;
    }

    static /* synthetic */ Calendar hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DatePicker datePicker, Calendar calendar) {
        Calendar calendar2 = calendar;
        Calendar calendar3 = calendar2;
        datePicker.f368hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = calendar3;
        return calendar2;
    }

    static /* synthetic */ int vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(DatePicker datePicker, int i) {
        int i2 = i;
        int i3 = i2;
        datePicker.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE = i3;
        return i2;
    }

    static /* synthetic */ int wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DatePicker datePicker, int i) {
        int i2 = i;
        int i3 = i2;
        datePicker.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = i3;
        return i2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DatePicker(com.google.appinventor.components.runtime.ComponentContainer r12) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            java.text.DateFormatSymbols r3 = new java.text.DateFormatSymbols
            r10 = r3
            r3 = r10
            r4 = r10
            r4.<init>()
            java.lang.String[] r3 = r3.getMonths()
            r2.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = r3
            r2 = r0
            r3 = 0
            r2.gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R = r3
            r2 = r0
            com.google.appinventor.components.runtime.DatePicker$1 r3 = new com.google.appinventor.components.runtime.DatePicker$1
            r10 = r3
            r3 = r10
            r4 = r10
            r5 = r0
            r4.<init>(r5)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            java.util.Calendar r2 = java.util.Calendar.getInstance()
            r1 = r2
            r2 = r0
            r3 = r1
            r4 = 1
            int r3 = r3.get(r4)
            r2.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = r3
            r2 = r0
            r3 = r1
            r4 = 2
            int r3 = r3.get(r4)
            r2.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = r3
            r2 = r0
            r10 = r2
            r2 = r10
            r3 = r10
            int r3 = r3.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn
            r4 = 1
            int r3 = r3 + 1
            r2.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = r3
            r2 = r0
            r3 = r1
            r4 = 5
            int r3 = r3.get(r4)
            r2.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE = r3
            r2 = r0
            r10 = r2
            r2 = r10
            r3 = r10
            int r3 = r3.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH
            r4 = r0
            int r4 = r4.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho
            r5 = r0
            int r5 = r5.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE
            java.util.Calendar r3 = com.google.appinventor.components.runtime.util.Dates.DateInstant(r3, r4, r5)
            r2.f368hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            android.app.DatePickerDialog r3 = new android.app.DatePickerDialog
            r10 = r3
            r3 = r10
            r4 = r10
            r5 = r0
            com.google.appinventor.components.runtime.ComponentContainer r5 = r5.container
            android.app.Activity r5 = r5.$context()
            r6 = r0
            android.app.DatePickerDialog$OnDateSetListener r6 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r7 = r0
            int r7 = r7.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH
            r8 = r0
            int r8 = r8.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn
            r9 = r0
            int r9 = r9.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE
            r4.<init>(r5, r6, r7, r8, r9)
            r2.f367hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            android.os.Handler r3 = new android.os.Handler
            r10 = r3
            r3 = r10
            r4 = r10
            r4.<init>()
            r2.androidUIHandler = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.DatePicker.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "the Year that was last picked using the DatePicker")
    public int Year() {
        return this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "the number of the Month that was last picked using the DatePicker. Note that months start in 1 = January, 12 = December.")
    public int Month() {
        return this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the name of the Month that was last picked using the DatePicker, in textual format.")
    public String MonthInText() {
        return this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq[this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn];
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "the Day of the month that was last picked using the DatePicker.")
    public int Day() {
        return this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "the instant of the date that was last picked using the DatePicker.")
    public Calendar Instant() {
        return this.f368hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleFunction(description = "Allows the user to set the date to be displayed when the date picker opens.\nValid values for the month field are 1-12 and 1-31 for the day field.\n")
    public void SetDateToDisplay(int i, int i2, int i3) {
        GregorianCalendar gregorianCalendar;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        int i7 = i5 - 1;
        try {
            new GregorianCalendar(i4, i7, i6);
            GregorianCalendar gregorianCalendar2 = gregorianCalendar;
            gregorianCalendar2.setLenient(false);
            Date time = gregorianCalendar2.getTime();
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "SetDateToDisplay", ErrorMessages.ERROR_ILLEGAL_DATE, new Object[0]);
        }
        this.f367hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.updateDate(i4, i7, i6);
        this.f368hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Dates.DateInstant(i4, i5, i6);
        this.gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R = true;
    }

    @SimpleFunction(description = "Allows the user to set the date from the instant to be displayed when the date picker opens.")
    public void SetDateToDisplayFromInstant(Calendar calendar) {
        Calendar calendar2 = calendar;
        int Year = Dates.Year(calendar2);
        int Month = Dates.Month(calendar2);
        int Day = Dates.Day(calendar2);
        this.f367hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.updateDate(Year, Month, Day);
        Calendar DateInstant = Dates.DateInstant(Year, Month, Day);
        this.gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R = true;
    }

    @SimpleFunction(description = "Launches the DatePicker popup.")
    public void LaunchPicker() {
        click();
    }

    public void click() {
        if (!this.gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R) {
            Calendar instance = Calendar.getInstance();
            Calendar calendar = instance;
            int i = instance.get(1);
            int i2 = calendar.get(2);
            int i3 = calendar.get(5);
            this.f367hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.updateDate(i, i2, i3);
            this.f368hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Dates.DateInstant(i, i2 + 1, i3);
        } else {
            this.gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R = false;
        }
        this.f367hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.show();
    }

    @SimpleEvent(description = "Event that runs after the user chooses a Date in the dialog")
    public void AfterDateSet() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterDateSet", new Object[0]);
    }
}
