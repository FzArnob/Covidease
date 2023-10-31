package com.google.appinventor.components.runtime;

import android.content.Intent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.runtime.util.AnimationUtil;

@SimpleObject
public abstract class Picker extends ButtonBase implements ActivityResultListener {
    protected final ComponentContainer container;
    protected int requestCode;

    /* access modifiers changed from: protected */
    public abstract Intent getIntent();

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Picker(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            r2.container = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Picker.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public void click() {
        BeforePicking();
        if (this.requestCode == 0) {
            this.requestCode = this.container.$form().registerForActivityResult(this);
        }
        this.container.$context().startActivityForResult(getIntent(), this.requestCode);
        AnimationUtil.ApplyOpenScreenAnimation(this.container.$context(), this.container.$form().getOpenAnimType());
    }

    @SimpleFunction(description = "Opens the picker, as though the user clicked on it.")
    public void Open() {
        click();
    }

    @SimpleEvent
    public void BeforePicking() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "BeforePicking", new Object[0]);
    }

    @SimpleEvent
    public void AfterPicking(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterPicking", str);
    }
}
