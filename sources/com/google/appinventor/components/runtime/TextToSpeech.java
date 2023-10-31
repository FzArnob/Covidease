package com.google.appinventor.components.runtime;

import android.os.Build;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.ITextToSpeech;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MEDIA, description = "The TestToSpeech component speaks a given text aloud.  You can set the pitch and the rate of speech. <p>You can also set a language by supplying a language code.  This changes the pronounciation of words, not the actual language spoken.  For example, setting the language to French and speaking English text will sound like someone speaking English (en) with a French accent.</p> <p>You can also specify a country by supplying a country code. This can affect the pronounciation.  For example, British English (GBR) will sound different from US English (USA).  Not every country code will affect every language.</p> <p>The languages and countries available depend on the particular device, and can be listed with the AvailableLanguages and AvailableCountries properties.</p>", iconName = "images/textToSpeech.png", nonVisible = true, version = 5)
public class TextToSpeech extends AndroidNonvisibleComponent implements Component, OnDestroyListener, OnResumeListener, OnStopListener {
    private static final Map<String, Locale> mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = Maps.newHashMap();
    private static final Map<String, Locale> sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = Maps.newHashMap();
    private float DmQGLROFyZ9Eo0RSsJcpZNxJZjgcsPDfYPi3awNwmyyErT71sGU5mvgG4PDW3yL = 1.0f;
    private YailList KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    private String NR00JNNlW621wVdOuGmKXPtjBpGajdPcd2Ky3026Pmc1Ihub1KfdGuuwHN0dv78V;
    private String R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe;
    private ArrayList<String> hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    private final ITextToSpeech hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private float iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = 1.0f;
    private YailList l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j;
    private String nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE;
    private boolean nrk4qDbuonwjAUi7HPDytbtabfk6KLkf1OM4Z1o9O6dUiFPBfNVpTU1RRHKJKimM;
    private String peQa36WzmOGDKVPxAtWvq1pN83yXrV2fw2e1QvLyEngIwdojN2EAkDoWXzH8bj7w;
    private boolean q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4 = false;
    private ArrayList<String> vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;

    static /* synthetic */ boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(TextToSpeech textToSpeech, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        textToSpeech.q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4 = z3;
        return z2;
    }

    static {
        Locale[] availableLocales = Locale.getAvailableLocales();
        Locale[] localeArr = availableLocales;
        int length = availableLocales.length;
        for (int i = 0; i < length; i++) {
            Locale locale = localeArr[i];
            try {
                String iSO3Country = locale.getISO3Country();
                String str = iSO3Country;
                if (iSO3Country.length() > 0) {
                    Locale put = mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT.put(str, locale);
                }
            } catch (MissingResourceException e) {
            }
            try {
                String iSO3Language = locale.getISO3Language();
                String str2 = iSO3Language;
                if (iSO3Language.length() > 0) {
                    Locale put2 = sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt.put(str2, locale);
                }
            } catch (MissingResourceException e2) {
            }
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TextToSpeech(com.google.appinventor.components.runtime.ComponentContainer r11) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r4 = r0
            r5 = r1
            com.google.appinventor.components.runtime.Form r5 = r5.$form()
            r4.<init>(r5)
            r4 = r0
            r5 = 1065353216(0x3f800000, float:1.0)
            r4.DmQGLROFyZ9Eo0RSsJcpZNxJZjgcsPDfYPi3awNwmyyErT71sGU5mvgG4PDW3yL = r5
            r4 = r0
            r5 = 1065353216(0x3f800000, float:1.0)
            r4.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = r5
            r4 = r0
            r5 = 0
            r4.q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4 = r5
            r4 = r0
            java.lang.String r5 = ""
            r4.Language(r5)
            r4 = r0
            java.lang.String r5 = ""
            r4.Country(r5)
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 4
            if (r4 >= r5) goto L_0x00be
            r4 = 1
        L_0x002d:
            r2 = r4
            java.lang.String r4 = "TextToSpeech"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r9 = r5
            r5 = r9
            r6 = r9
            java.lang.String r7 = "Using "
            r6.<init>(r7)
            r6 = r2
            if (r6 == 0) goto L_0x00c1
            java.lang.String r6 = "external"
        L_0x0042:
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = " TTS library."
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            int r4 = android.util.Log.v(r4, r5)
            com.google.appinventor.components.runtime.TextToSpeech$1 r4 = new com.google.appinventor.components.runtime.TextToSpeech$1
            r9 = r4
            r4 = r9
            r5 = r9
            r6 = r0
            r5.<init>(r6)
            r3 = r4
            r4 = r0
            r5 = r2
            if (r5 == 0) goto L_0x00c6
            com.google.appinventor.components.runtime.util.ExternalTextToSpeech r5 = new com.google.appinventor.components.runtime.util.ExternalTextToSpeech
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = r1
            r8 = r3
            r6.<init>(r7, r8)
        L_0x006d:
            r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5
            r4 = r0
            com.google.appinventor.components.runtime.Form r4 = r4.form
            r5 = r0
            r4.registerForOnStop(r5)
            r4 = r0
            com.google.appinventor.components.runtime.Form r4 = r4.form
            r5 = r0
            r4.registerForOnResume(r5)
            r4 = r0
            com.google.appinventor.components.runtime.Form r4 = r4.form
            r5 = r0
            r4.registerForOnDestroy(r5)
            r4 = r0
            com.google.appinventor.components.runtime.Form r4 = r4.form
            r5 = 3
            r4.setVolumeControlStream(r5)
            r4 = r0
            r5 = 0
            r4.nrk4qDbuonwjAUi7HPDytbtabfk6KLkf1OM4Z1o9O6dUiFPBfNVpTU1RRHKJKimM = r5
            r4 = r0
            java.util.ArrayList r5 = new java.util.ArrayList
            r9 = r5
            r5 = r9
            r6 = r9
            r6.<init>()
            r4.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = r5
            r4 = r0
            java.util.ArrayList r5 = new java.util.ArrayList
            r9 = r5
            r5 = r9
            r6 = r9
            r6.<init>()
            r4.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = r5
            r4 = r0
            r9 = r4
            r4 = r9
            r5 = r9
            java.util.ArrayList<java.lang.String> r5 = r5.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO
            com.google.appinventor.components.runtime.util.YailList r5 = com.google.appinventor.components.runtime.util.YailList.makeList((java.util.List) r5)
            r4.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = r5
            r4 = r0
            r9 = r4
            r4 = r9
            r5 = r9
            java.util.ArrayList<java.lang.String> r5 = r5.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R
            com.google.appinventor.components.runtime.util.YailList r5 = com.google.appinventor.components.runtime.util.YailList.makeList((java.util.List) r5)
            r4.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = r5
            return
        L_0x00be:
            r4 = 0
            goto L_0x002d
        L_0x00c1:
            java.lang.String r6 = "internal"
            goto L_0x0042
        L_0x00c6:
            com.google.appinventor.components.runtime.util.InternalTextToSpeech r5 = new com.google.appinventor.components.runtime.util.InternalTextToSpeech
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = r1
            android.app.Activity r7 = r7.$context()
            r8 = r3
            r6.<init>(r7, r8)
            goto L_0x006d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.TextToSpeech.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Result() {
        return this.q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4;
    }

    @DesignerProperty(defaultValue = "", editorType = "languages")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the language for TextToSpeech. This changes the way that words are pronounced, not the actual language that is spoken.  For example setting the language to and speaking English text with sound like someone speaking English with a French accent.")
    public void Language(String str) {
        Locale locale;
        Locale locale2;
        Locale locale3;
        String str2 = str;
        switch (str2.length()) {
            case 2:
                new Locale(str2);
                locale2 = locale;
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = locale2.getLanguage();
                break;
            case 3:
                Locale locale4 = sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt.get(str2);
                Locale locale5 = locale4;
                if (locale4 == null) {
                    locale5 = sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt.get(str2.toLowerCase(Locale.ENGLISH));
                }
                if (locale5 == null) {
                    locale3 = Locale.getDefault();
                } else {
                    locale3 = locale5;
                }
                locale2 = locale3;
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = locale2.getISO3Language();
                break;
            default:
                locale2 = Locale.getDefault();
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = locale2.getLanguage();
                break;
        }
        this.NR00JNNlW621wVdOuGmKXPtjBpGajdPcd2Ky3026Pmc1Ihub1KfdGuuwHN0dv78V = locale2.getLanguage();
    }

    @DesignerProperty(defaultValue = "1.0", editorType = "float")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the Pitch for TextToSpeech The values should be between 0 and 2 where lower values lower the tone of synthesized voice and greater values raise it.")
    public void Pitch(float f) {
        float f2 = f;
        if (f2 < 0.0f || f2 > 2.0f) {
            int i = Log.i("TextToSpeech", "Pitch value should be between 0 and 2, but user specified: ".concat(String.valueOf(f2)));
            return;
        }
        this.DmQGLROFyZ9Eo0RSsJcpZNxJZjgcsPDfYPi3awNwmyyErT71sGU5mvgG4PDW3yL = f2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPitch(f2 == 0.0f ? 0.1f : f2);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns current value of Pitch")
    public float Pitch() {
        return this.DmQGLROFyZ9Eo0RSsJcpZNxJZjgcsPDfYPi3awNwmyyErT71sGU5mvgG4PDW3yL;
    }

    @DesignerProperty(defaultValue = "1.0", editorType = "float")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the SpeechRate for TextToSpeech. The values should be between 0 and 2 where lower values slow down the pitch and greater values accelerate it.")
    public void SpeechRate(float f) {
        float f2 = f;
        if (f2 < 0.0f || f2 > 2.0f) {
            int i = Log.i("TextToSpeech", "speechRate value should be between 0 and 2, but user specified: ".concat(String.valueOf(f2)));
            return;
        }
        this.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = f2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setSpeechRate(f2 == 0.0f ? 0.1f : f2);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns current value of SpeechRate")
    public float SpeechRate() {
        return this.iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm;
    }

    @SimpleProperty
    public String Language() {
        return this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe;
    }

    @DesignerProperty(defaultValue = "", editorType = "countries")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Country code to use for speech generation.  This can affect the pronounciation.  For example, British English (GBR) will sound different from US English (USA).  Not every country code will affect every language.")
    public void Country(String str) {
        Locale locale;
        Locale locale2;
        Locale locale3;
        String str2 = str;
        switch (str2.length()) {
            case 2:
                new Locale(str2);
                locale2 = locale;
                this.nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE = locale2.getCountry();
                break;
            case 3:
                Locale locale4 = mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT.get(str2);
                Locale locale5 = locale4;
                if (locale4 == null) {
                    locale5 = mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT.get(str2.toUpperCase(Locale.ENGLISH));
                }
                if (locale5 == null) {
                    locale3 = Locale.getDefault();
                } else {
                    locale3 = locale5;
                }
                locale2 = locale3;
                this.nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE = locale2.getISO3Country();
                break;
            default:
                locale2 = Locale.getDefault();
                this.nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE = locale2.getCountry();
                break;
        }
        this.peQa36WzmOGDKVPxAtWvq1pN83yXrV2fw2e1QvLyEngIwdojN2EAkDoWXzH8bj7w = locale2.getCountry();
    }

    @SimpleProperty
    public String Country() {
        return this.nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE;
    }

    @SimpleProperty(description = "List of the languages available on this device for use with TextToSpeech.  Check the Android developer documentation under supported languages to find the meanings of these abbreviations.")
    public YailList AvailableLanguages() {
        prepareLanguageAndCountryProperties();
        return this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j;
    }

    @SimpleProperty(description = "List of the country codes available on this device for use with TextToSpeech.  Check the Android developer documentation under supported languages to find the meanings of these abbreviations.")
    public YailList AvailableCountries() {
        prepareLanguageAndCountryProperties();
        return this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    }

    public void prepareLanguageAndCountryProperties() {
        if (this.nrk4qDbuonwjAUi7HPDytbtabfk6KLkf1OM4Z1o9O6dUiFPBfNVpTU1RRHKJKimM) {
            return;
        }
        if (!this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isInitialized()) {
            this.form.dispatchErrorOccurredEvent(this, "TextToSpeech", ErrorMessages.ERROR_TTS_NOT_READY, new Object[0]);
            Speak("");
            return;
        }
        if (Build.VERSION.SDK_INT >= 4) {
            Locale[] availableLocales = Locale.getAvailableLocales();
            Locale[] localeArr = availableLocales;
            int length = availableLocales.length;
            for (int i = 0; i < length; i++) {
                Locale locale = localeArr[i];
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isLanguageAvailable(locale) != -2) {
                    String language = locale.getLanguage();
                    String iSO3Country = locale.getISO3Country();
                    if (!language.equals("") && !this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.contains(language)) {
                        boolean add = this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.add(language);
                    }
                    if (!iSO3Country.equals("") && !this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.contains(iSO3Country)) {
                        boolean add2 = this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.add(iSO3Country);
                    }
                }
            }
            Collections.sort(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO);
            Collections.sort(this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R);
            this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = YailList.makeList((List) this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO);
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = YailList.makeList((List) this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R);
        }
        this.nrk4qDbuonwjAUi7HPDytbtabfk6KLkf1OM4Z1o9O6dUiFPBfNVpTU1RRHKJKimM = true;
    }

    @SimpleFunction
    public void Speak(String str) {
        Locale locale;
        BeforeSpeaking();
        new Locale(this.NR00JNNlW621wVdOuGmKXPtjBpGajdPcd2Ky3026Pmc1Ihub1KfdGuuwHN0dv78V, this.peQa36WzmOGDKVPxAtWvq1pN83yXrV2fw2e1QvLyEngIwdojN2EAkDoWXzH8bj7w);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.speak(str, locale);
    }

    @SimpleEvent
    public void BeforeSpeaking() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "BeforeSpeaking", new Object[0]);
    }

    @SimpleEvent
    public void AfterSpeaking(boolean z) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterSpeaking", Boolean.valueOf(z));
    }

    public void onStop() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onStop();
    }

    public void onResume() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onResume();
    }

    public void onDestroy() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onDestroy();
    }
}
