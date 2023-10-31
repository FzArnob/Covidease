package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import com.google.appinventor.components.runtime.util.ITextToSpeech;
import java.util.HashMap;
import java.util.Locale;
import org.shaded.apache.http.HttpStatus;

public class InternalTextToSpeech implements ITextToSpeech {
    private static final String LOG_TAG = "InternalTTS";
    /* access modifiers changed from: private */
    public final Activity activity;
    /* access modifiers changed from: private */
    public final ITextToSpeech.TextToSpeechCallback callback;
    private volatile boolean isTtsInitialized;
    private Handler mHandler;
    private int nextUtteranceId = 1;
    private TextToSpeech tts;
    private int ttsMaxRetries = 20;
    private int ttsRetryDelay = HttpStatus.SC_INTERNAL_SERVER_ERROR;

    static /* synthetic */ boolean access$002(InternalTextToSpeech internalTextToSpeech, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        internalTextToSpeech.isTtsInitialized = z3;
        return z2;
    }

    public InternalTextToSpeech(Activity activity2, ITextToSpeech.TextToSpeechCallback textToSpeechCallback) {
        Handler handler;
        new Handler();
        this.mHandler = handler;
        this.activity = activity2;
        this.callback = textToSpeechCallback;
        initializeTts();
    }

    private void initializeTts() {
        TextToSpeech textToSpeech;
        TextToSpeech.OnInitListener onInitListener;
        if (this.tts == null) {
            int d = Log.d(LOG_TAG, "INTERNAL TTS is reinitializing");
            new TextToSpeech.OnInitListener(this) {
                private /* synthetic */ InternalTextToSpeech hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onInit(int i) {
                    if (i == 0) {
                        boolean access$002 = InternalTextToSpeech.access$002(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, true);
                    }
                }
            };
            new TextToSpeech(this.activity, onInitListener);
            this.tts = textToSpeech;
        }
    }

    public void speak(String str, Locale locale) {
        int d = Log.d(LOG_TAG, "Internal TTS got speak");
        speak(str, locale, 0);
    }

    public boolean isInitialized() {
        return this.isTtsInitialized;
    }

    /* access modifiers changed from: private */
    public void speak(String str, Locale locale, int i) {
        Runnable runnable;
        StringBuilder sb;
        TextToSpeech.OnUtteranceCompletedListener onUtteranceCompletedListener;
        HashMap hashMap;
        String str2 = str;
        Locale locale2 = locale;
        int i2 = i;
        int d = Log.d(LOG_TAG, "InternalTTS speak called, message = ".concat(String.valueOf(str2)));
        if (i2 > this.ttsMaxRetries) {
            int d2 = Log.d(LOG_TAG, "max number of speak retries exceeded: speak will fail");
            this.callback.onFailure();
        }
        if (this.isTtsInitialized) {
            new StringBuilder("TTS initialized after ");
            int d3 = Log.d(LOG_TAG, sb.append(i2).append(" retries.").toString());
            int language = this.tts.setLanguage(locale2);
            new TextToSpeech.OnUtteranceCompletedListener(this) {
                final /* synthetic */ InternalTextToSpeech hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onUtteranceCompleted(String str) {
                    Runnable runnable;
                    String str2 = str;
                    new Runnable(this) {
                        private /* synthetic */ C11492 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void run() {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.callback.onSuccess();
                        }
                    };
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.activity.runOnUiThread(runnable);
                }
            };
            int onUtteranceCompletedListener2 = this.tts.setOnUtteranceCompletedListener(onUtteranceCompletedListener);
            new HashMap();
            HashMap hashMap2 = hashMap;
            HashMap hashMap3 = hashMap2;
            HashMap hashMap4 = hashMap2;
            int i3 = this.nextUtteranceId;
            int i4 = i3 + 1;
            this.nextUtteranceId = i4;
            Object put = hashMap3.put("utteranceId", Integer.toString(i3));
            if (this.tts.speak(str2, 0, hashMap4) == -1) {
                int d4 = Log.d(LOG_TAG, "speak called and tts.speak result was an error");
                this.callback.onFailure();
                return;
            }
            return;
        }
        int d5 = Log.d(LOG_TAG, "speak called when TTS not initialized");
        final int i5 = i2;
        final String str3 = str2;
        final Locale locale3 = locale2;
        new Runnable(this) {
            private /* synthetic */ InternalTextToSpeech hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
            }

            public final void run() {
                StringBuilder sb;
                new StringBuilder("delaying call to speak.  Retries is: ");
                int d = Log.d(InternalTextToSpeech.LOG_TAG, sb.append(i5).append(" Message is: ").append(str3).toString());
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.speak(str3, locale3, i5 + 1);
            }
        };
        boolean postDelayed = this.mHandler.postDelayed(runnable, (long) this.ttsRetryDelay);
    }

    public void onStop() {
        int d = Log.d(LOG_TAG, "Internal TTS got onStop");
    }

    public void onDestroy() {
        int d = Log.d(LOG_TAG, "Internal TTS got onDestroy");
        if (this.tts != null) {
            this.tts.shutdown();
            this.isTtsInitialized = false;
            this.tts = null;
        }
    }

    public void onResume() {
        int d = Log.d(LOG_TAG, "Internal TTS got onResume");
        initializeTts();
    }

    public void setPitch(float f) {
        int pitch = this.tts.setPitch(f);
    }

    public void setSpeechRate(float f) {
        int speechRate = this.tts.setSpeechRate(f);
    }

    public int isLanguageAvailable(Locale locale) {
        return this.tts.isLanguageAvailable(locale);
    }
}
