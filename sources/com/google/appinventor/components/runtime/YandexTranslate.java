package com.google.appinventor.components.runtime;

import android.app.Activity;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MEDIA, description = "Use this component to translate words and sentences between different languages. This component needs Internet access, as it will request translations to the Yandex.Translate service. Specify the source and target language in the form source-target using two letter language codes. So\"en-es\" will translate from English to Spanish while \"es-ru\" will translate from Spanish to Russian. If you leave out the source language, the service will attempt to detect the source language. So providing just \"es\" will attempt to detect the source language and translate it to Spanish.<p /> This component is powered by the Yandex translation service.  See http://api.yandex.com/translate/ for more information, including the list of available languages and the meanings of the language codes and status codes. <p />Note: Translation happens asynchronously in the background. When the translation is complete, the \"GotTranslation\" event is triggered.", iconName = "images/yandex.png", nonVisible = true, version = 2)
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public final class YandexTranslate extends AndroidNonvisibleComponent {
    public static final String YANDEX_TRANSLATE_SERVICE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=";
    private String KQG8OTRvHbMOByZu4oimrjMQImUXcBx4aj5wSGopdAdeTIpEFw8C79s3zi6NeXhR = "";
    private final Activity activity;

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(YandexTranslate yandexTranslate, String str, String str2) throws IOException, JSONException {
        StringBuilder sb;
        URL url;
        JSONObject jSONObject;
        Runnable runnable;
        YandexTranslate yandexTranslate2 = yandexTranslate;
        new StringBuilder(YANDEX_TRANSLATE_SERVICE_URL);
        new URL(sb.append(yandexTranslate2.KQG8OTRvHbMOByZu4oimrjMQImUXcBx4aj5wSGopdAdeTIpEFw8C79s3zi6NeXhR).append("&lang=").append(str).append("&text=").append(URLEncoder.encode(str2, "UTF-8")).toString());
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        HttpURLConnection httpURLConnection2 = httpURLConnection;
        if (httpURLConnection != null) {
            try {
                new JSONObject(getResponseContent(httpURLConnection2));
                JSONObject jSONObject2 = jSONObject;
                String string = jSONObject2.getString("code");
                final String str3 = string;
                final String str4 = (String) jSONObject2.getJSONArray(PropertyTypeConstants.PROPERTY_TYPE_TEXT).get(0);
                new Runnable(yandexTranslate2) {
                    private /* synthetic */ YandexTranslate hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                    }

                    public final void run() {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotTranslation(str3, str4);
                    }
                };
                yandexTranslate2.activity.runOnUiThread(runnable);
                httpURLConnection2.disconnect();
            } catch (Throwable th) {
                Throwable th2 = th;
                httpURLConnection2.disconnect();
                throw th2;
            }
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public YandexTranslate(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r2.setYandexTranslateTagline()
            r2 = r0
            java.lang.String r3 = ""
            r2.KQG8OTRvHbMOByZu4oimrjMQImUXcBx4aj5wSGopdAdeTIpEFw8C79s3zi6NeXhR = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.YandexTranslate.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "By providing a target language to translate to (for instance, 'es' for Spanish, 'en' for English, or 'ru' for Russian), and a word or sentence to translate, this method will request a translation to the Yandex.Translate service.\nOnce the text is translated by the external service, the event GotTranslation will be executed.\nNote: Yandex.Translate will attempt to detect the source language. You can also specify prepending it to the language translation. I.e., es-ru will specify Spanish to Russian translation.")
    public final void RequestTranslation(String str, String str2) {
        Runnable runnable;
        String str3 = str;
        String str4 = str2;
        if (this.KQG8OTRvHbMOByZu4oimrjMQImUXcBx4aj5wSGopdAdeTIpEFw8C79s3zi6NeXhR.equals("")) {
            this.form.dispatchErrorOccurredEvent(this, "RequestTranslation", ErrorMessages.ERROR_TRANSLATE_NO_KEY_FOUND, new Object[0]);
            return;
        }
        final String str5 = str3;
        final String str6 = str4;
        new Runnable(this) {
            private /* synthetic */ YandexTranslate hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                try {
                    YandexTranslate.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str5, str6);
                } catch (JSONException e) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "RequestTranslation", ErrorMessages.ERROR_TRANSLATE_JSON_RESPONSE, new Object[0]);
                } catch (Exception e2) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "RequestTranslation", ErrorMessages.ERROR_TRANSLATE_SERVICE_NOT_AVAILABLE, new Object[0]);
                }
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    /* JADX INFO: finally extract failed */
    private static String getResponseContent(HttpURLConnection httpURLConnection) throws IOException {
        InputStreamReader inputStreamReader;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        HttpURLConnection httpURLConnection2 = httpURLConnection;
        String contentEncoding = httpURLConnection2.getContentEncoding();
        String str = contentEncoding;
        if (contentEncoding == null) {
            str = "UTF-8";
        }
        new InputStreamReader(httpURLConnection2.getInputStream(), str);
        InputStreamReader inputStreamReader2 = inputStreamReader;
        try {
            int contentLength = httpURLConnection2.getContentLength();
            int i = contentLength;
            if (contentLength != -1) {
                sb2 = sb3;
                new StringBuilder(i);
            } else {
                sb2 = sb;
                new StringBuilder();
            }
            StringBuilder sb4 = sb2;
            char[] cArr = new char[1024];
            while (true) {
                int read = inputStreamReader2.read(cArr);
                int i2 = read;
                if (read != -1) {
                    StringBuilder append = sb4.append(cArr, 0, i2);
                } else {
                    String sb5 = sb4.toString();
                    inputStreamReader2.close();
                    return sb5;
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            inputStreamReader2.close();
            throw th2;
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public final void ApiKey(String str) {
        String str2 = str;
        this.KQG8OTRvHbMOByZu4oimrjMQImUXcBx4aj5wSGopdAdeTIpEFw8C79s3zi6NeXhR = str2;
    }

    @SimpleEvent(description = "Event triggered when the Yandex.Translate service returns the translated text. This event also provides a response code for error handling. If the responseCode is not 200, then something went wrong with the call, and the translation will not be available.")
    public final void GotTranslation(String str, String str2) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotTranslation", objArr2);
    }
}
