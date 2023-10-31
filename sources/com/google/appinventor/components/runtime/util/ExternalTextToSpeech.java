package com.google.appinventor.components.runtime.util;

import android.content.Intent;
import com.google.appinventor.components.runtime.ActivityResultListener;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.util.ITextToSpeech;
import gnu.expr.Declaration;
import java.util.Locale;

public class ExternalTextToSpeech implements ActivityResultListener, ITextToSpeech {
    private final ITextToSpeech.TextToSpeechCallback callback;
    private final ComponentContainer container;
    private int requestCode;

    public ExternalTextToSpeech(ComponentContainer componentContainer, ITextToSpeech.TextToSpeechCallback textToSpeechCallback) {
        this.container = componentContainer;
        this.callback = textToSpeechCallback;
    }

    public void speak(String str, Locale locale) {
        Intent intent;
        Locale locale2 = locale;
        new Intent("com.google.tts.makeBagel");
        Intent intent2 = intent;
        Intent intent3 = intent2;
        Intent flags = intent2.setFlags(131072);
        Intent flags2 = intent3.setFlags(8388608);
        Intent flags3 = intent3.setFlags(Declaration.MODULE_REFERENCE);
        Intent putExtra = intent3.putExtra("message", str);
        Intent putExtra2 = intent3.putExtra("language", locale2.getISO3Language());
        Intent putExtra3 = intent3.putExtra("country", locale2.getISO3Country());
        if (this.requestCode == 0) {
            this.requestCode = this.container.$form().registerForActivityResult(this);
        }
        this.container.$context().startActivityForResult(intent3, this.requestCode);
    }

    public void onDestroy() {
    }

    public void onStop() {
    }

    public void onResume() {
    }

    public void setPitch(float f) {
    }

    public void setSpeechRate(float f) {
    }

    public void resultReturned(int i, int i2, Intent intent) {
        Intent intent2 = intent;
        if (i == this.requestCode && i2 == -1) {
            this.callback.onSuccess();
        } else {
            this.callback.onFailure();
        }
    }

    public int isLanguageAvailable(Locale locale) {
        Locale locale2 = locale;
        return -1;
    }

    public boolean isInitialized() {
        return true;
    }
}
