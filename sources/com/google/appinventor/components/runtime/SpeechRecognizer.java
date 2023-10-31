package com.google.appinventor.components.runtime;

import android.content.Intent;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MEDIA, description = "Component for using Voice Recognition to convert from speech to text", iconName = "images/speechRecognizer.png", nonVisible = true, version = 2)
public class SpeechRecognizer extends AndroidNonvisibleComponent implements ActivityResultListener, Component {
    private final ComponentContainer container;
    private String l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = "";
    private int requestCode;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SpeechRecognizer(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.SpeechRecognizer.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String Result() {
        return this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j;
    }

    @SimpleFunction
    public void GetText() {
        Intent intent;
        BeforeGettingText();
        new Intent("android.speech.action.RECOGNIZE_SPEECH");
        Intent intent2 = intent;
        Intent intent3 = intent2;
        Intent putExtra = intent2.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        Intent putExtra2 = intent3.putExtra("calling_package", this.container.$context().getPackageName());
        if (this.requestCode == 0) {
            this.requestCode = this.form.registerForActivityResult(this);
        }
        this.container.$context().startActivityForResult(intent3, this.requestCode);
    }

    public void resultReturned(int i, int i2, Intent intent) {
        int i3 = i2;
        Intent intent2 = intent;
        if (i == this.requestCode && i3 == -1) {
            if (intent2.hasExtra("android.speech.extra.RESULTS")) {
                this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = intent2.getExtras().getStringArrayList("android.speech.extra.RESULTS").get(0);
            } else {
                this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = "";
            }
            AfterGettingText(this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j);
        }
    }

    @SimpleEvent
    public void BeforeGettingText() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "BeforeGettingText", new Object[0]);
    }

    @SimpleEvent
    public void AfterGettingText(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterGettingText", str);
    }
}
