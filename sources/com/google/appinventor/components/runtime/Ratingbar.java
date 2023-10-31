package com.google.appinventor.components.runtime;

import android.graphics.drawable.LayerDrawable;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.widget.RatingBar;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "", helpUrl = "https://docs.kodular.io/components/user-interface/rating-bar/", iconName = "images/ratingbar.png", version = 1)
public final class Ratingbar extends AndroidViewComponent implements RatingBar.OnRatingBarChangeListener {
    private int TVKenNjujur1C1Ft9Gj8dhchvJBwuJV9GDuQOmGg2gZVCkxzGoaa0a88A5IZ9COq = Component.COLOR_GRAY;
    private LayerDrawable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final RatingBar f516hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int wfbsnc19ruRPyBpriU11i0zXW81wrBgGRVM2BOD65kRILLKDr3mBxnYSQKLd5kkO = Component.COLOR_RED;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Ratingbar(com.google.appinventor.components.runtime.ComponentContainer r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = -834762(0xfffffffffff34336, float:NaN)
            r2.wfbsnc19ruRPyBpriU11i0zXW81wrBgGRVM2BOD65kRILLKDr3mBxnYSQKLd5kkO = r3
            r2 = r0
            r3 = -6381922(0xffffffffff9e9e9e, float:NaN)
            r2.TVKenNjujur1C1Ft9Gj8dhchvJBwuJV9GDuQOmGg2gZVCkxzGoaa0a88A5IZ9COq = r3
            r2 = r0
            android.widget.RatingBar r3 = new android.widget.RatingBar
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r4.<init>(r5)
            r2.f516hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            android.widget.RatingBar r2 = r2.f516hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = r0
            r2.setOnRatingBarChangeListener(r3)
            r2 = r0
            r6 = r2
            r2 = r6
            r3 = r6
            android.widget.RatingBar r3 = r3.f516hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            android.graphics.drawable.Drawable r3 = r3.getProgressDrawable()
            android.graphics.drawable.LayerDrawable r3 = (android.graphics.drawable.LayerDrawable) r3
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r0
            r3 = 5
            r2.SetNumberOfStars(r3)
            r2 = r0
            r3 = 0
            r2.IsIndicator(r3)
            r2 = r0
            r3 = 1056964608(0x3f000000, float:0.5)
            r2.SetStepSize(r3)
            r2 = r0
            r3 = -834762(0xfffffffffff34336, float:NaN)
            r2.StarColor(r3)
            r2 = r0
            r3 = -6381922(0xffffffffff9e9e9e, float:NaN)
            r2.BackgroundColor(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Ratingbar.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public final RatingBar getView() {
        return this.f516hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public final void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
        RatingBar ratingBar2 = ratingBar;
        boolean z2 = z;
        Changed(f);
    }

    @SimpleEvent(description = "Event invoked when the rating has been changed.")
    public final void Changed(float f) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Changed", Float.valueOf(f));
    }

    @DesignerProperty(defaultValue = "5", editorType = "string")
    @SimpleProperty(description = "Sets the number of stars to show.")
    public final void SetNumberOfStars(int i) {
        this.f516hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setNumStars(i);
    }

    @SimpleProperty(description = "Returns the number of stars shown.")
    public final int GetNumberOfStars() {
        return this.f516hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getNumStars();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Whether this rating bar should only be an indicator (thus non-changeable by the user).")
    public final void IsIndicator(boolean z) {
        this.f516hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setIsIndicator(z);
    }

    @SimpleProperty(description = "Sets the rating (the number of stars filled).")
    public final void SetRating(float f) {
        this.f516hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRating(f);
    }

    @SimpleProperty(description = "Gets the current rating (number of stars filled).")
    public final float GetRating() {
        return this.f516hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getRating();
    }

    @DesignerProperty(defaultValue = "&HFFF34336", editorType = "color")
    @SimpleProperty(description = "Change the color of the star.")
    public final void StarColor(int i) {
        int i2 = i;
        this.wfbsnc19ruRPyBpriU11i0zXW81wrBgGRVM2BOD65kRILLKDr3mBxnYSQKLd5kkO = i2;
        DrawableCompat.setTint(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDrawable(2), i2);
    }

    @SimpleProperty(description = "Return the background color of the star.")
    public final int StarColor() {
        return this.wfbsnc19ruRPyBpriU11i0zXW81wrBgGRVM2BOD65kRILLKDr3mBxnYSQKLd5kkO;
    }

    @DesignerProperty(defaultValue = "&HFF9E9E9E", editorType = "color")
    @SimpleProperty(description = "Change the background color of the star.")
    public final void BackgroundColor(int i) {
        int i2 = i;
        this.TVKenNjujur1C1Ft9Gj8dhchvJBwuJV9GDuQOmGg2gZVCkxzGoaa0a88A5IZ9COq = i2;
        DrawableCompat.setTint(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDrawable(0), i2);
    }

    @SimpleProperty(description = "Return the background color of the star.")
    public final int BackgroundColor() {
        return this.TVKenNjujur1C1Ft9Gj8dhchvJBwuJV9GDuQOmGg2gZVCkxzGoaa0a88A5IZ9COq;
    }

    @DesignerProperty(defaultValue = ".5", editorType = "float")
    @SimpleProperty(description = "Sets the step size (granularity) of this rating bar.")
    public final void SetStepSize(float f) {
        this.f516hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStepSize(f);
    }

    @SimpleProperty(description = "Gets the step size (granularity) of this rating bar.")
    public final float GetStepSize() {
        return this.f516hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStepSize();
    }

    public final int Height() {
        return getView().getHeight();
    }

    public final void Height(int i) {
        this.container.setChildHeight(this, i);
    }

    public final int Width() {
        return getView().getWidth();
    }

    public final void Width(int i) {
        this.container.setChildWidth(this, i);
    }

    public final void HeightPercent(int i) {
    }

    public final void WidthPercent(int i) {
    }
}
