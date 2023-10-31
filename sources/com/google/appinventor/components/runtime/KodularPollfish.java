package com.google.appinventor.components.runtime;

import android.content.SharedPreferences;
import android.support.p000v4.app.NotificationCompat;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.ComponentConstants;
import com.pollfish.classes.SurveyInfo;
import com.pollfish.interfaces.PollfishClosedListener;
import com.pollfish.interfaces.PollfishCompletedSurveyListener;
import com.pollfish.interfaces.PollfishOpenedListener;
import com.pollfish.interfaces.PollfishReceivedSurveyListener;
import com.pollfish.interfaces.PollfishSurveyNotAvailableListener;
import com.pollfish.interfaces.PollfishUserNotEligibleListener;
import com.pollfish.interfaces.PollfishUserRejectedSurveyListener;
import com.pollfish.main.PollFish;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.shaded.apache.http.client.methods.HttpPut;

@DesignerComponent(category = ComponentCategory.MONETIZATION_GENERAL, iconName = "images/pollfish.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "pollfish.jar, pollfish.aar,play-services-base.jar, play-services-base.aar,play-services-ads-identifier.jar, play-services-ads-identifier.aar,play-services-basement.jar, play-services-basement.aar,")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public final class KodularPollfish extends AndroidNonvisibleComponent implements Component, OnResumeListener, PollfishClosedListener, PollfishCompletedSurveyListener, PollfishOpenedListener, PollfishReceivedSurveyListener, PollfishSurveyNotAvailableListener, PollfishUserNotEligibleListener, PollfishUserRejectedSurveyListener {
    private String ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio = ComponentConstants.DEFAULT_POLLFISH_MODE;
    private ComponentContainer container;
    private String rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A = "";
    private boolean testMode = true;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularPollfish(com.google.appinventor.components.runtime.ComponentContainer r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A = r3
            r2 = r0
            r3 = 1
            r2.testMode = r3
            r2 = r0
            java.lang.String r3 = "Single Survey"
            r2.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio = r3
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r3 = r0
            r2.registerForOnResume(r3)
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = 1
            r2.TestMode(r3)
            r2 = r0
            r4 = r2
            r2 = r4
            r3 = r4
            java.lang.String r3 = r3.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio
            r2.SurveyMode(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularPollfish.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Show Survey")
    public final void ShowSurvey() {
        PollFish.show();
    }

    @SimpleProperty(description = "Returns value of Pollfish API Key")
    public final String APIKey() {
        return this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public final void APIKey(String str) {
        Thread thread;
        Runnable runnable;
        String str2 = str;
        if (!this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A.equals(str2)) {
            if (!(this.form instanceof ReplForm)) {
                String str3 = str2;
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME().getString(str3, (String) null) == null) {
                    final String str4 = str3;
                    new Runnable(this) {
                        private /* synthetic */ KodularPollfish hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                        }

                        public final void run() {
                            StringBuilder sb;
                            URL url;
                            StringBuilder sb2;
                            BufferedReader bufferedReader;
                            Reader reader;
                            StringBuilder sb3;
                            JSONObject jSONObject;
                            StringBuilder sb4;
                            try {
                                new StringBuilder("https://api.creator.kodular.io/pollfish/v1?appid=");
                                new URL(sb2.append(str4).toString());
                                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                                HttpURLConnection httpURLConnection2 = httpURLConnection;
                                httpURLConnection.setRequestMethod(HttpPut.METHOD_NAME);
                                if (httpURLConnection2.getResponseCode() == 200) {
                                    new InputStreamReader(httpURLConnection2.getInputStream());
                                    new BufferedReader(reader);
                                    BufferedReader bufferedReader2 = bufferedReader;
                                    new StringBuilder();
                                    StringBuilder sb5 = sb3;
                                    while (true) {
                                        String readLine = bufferedReader2.readLine();
                                        String str = readLine;
                                        if (readLine == null) {
                                            break;
                                        }
                                        StringBuilder append = sb5.append(str);
                                    }
                                    new JSONObject(sb5.toString());
                                    JSONObject jSONObject2 = jSONObject;
                                    JSONObject jSONObject3 = jSONObject2;
                                    if (jSONObject2.getBoolean("success")) {
                                        JSONObject jSONObject4 = jSONObject3.getJSONObject("data");
                                        JSONObject jSONObject5 = jSONObject4;
                                        if (jSONObject4 != null) {
                                            String string = jSONObject5.getString(NotificationCompat.CATEGORY_STATUS);
                                            String string2 = jSONObject5.getString("message");
                                            SharedPreferences.Editor edit = KodularPollfish.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).edit();
                                            String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = KodularPollfish.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                                            new StringBuilder();
                                            SharedPreferences.Editor putString = edit.putString(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2, sb4.append(string.equals("ok")).toString());
                                            boolean commit = edit.commit();
                                        }
                                    }
                                }
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                }
                            } catch (Exception e) {
                                Exception exc = e;
                                new StringBuilder();
                                int e2 = Log.e("Pollfish", sb.append(exc.getMessage()).toString());
                                if (0 != 0) {
                                    HttpURLConnection httpURLConnection3 = null;
                                    httpURLConnection3.disconnect();
                                }
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                if (0 != 0) {
                                    HttpURLConnection httpURLConnection4 = null;
                                    httpURLConnection4.disconnect();
                                }
                                throw th2;
                            }
                        }
                    };
                    new Thread(runnable);
                    thread.start();
                }
            }
            this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A = str2;
            l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j();
        }
    }

    private SharedPreferences hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
        return this.container.$context().getSharedPreferences(ComponentConstants.KODULAR_PREF, 0);
    }

    @DesignerProperty(defaultValue = "Single Survey", editorType = "pollfish_options")
    public final void SurveyMode(String str) {
        String str2 = str;
        if (!this.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio.equals(str2)) {
            this.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio = str2;
            l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j();
        }
    }

    @SimpleProperty(description = "Survey mode. It can either be Single Survey or Survey Offerwall")
    public final String SurveyMode() {
        return this.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If you want to test the component then set this property to true and before publishing to Play Store, set back to false.")
    public final void TestMode(boolean z) {
        boolean z2 = z;
        if (this.testMode != z2) {
            this.testMode = z2;
            l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j();
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final boolean TestMode() {
        return this.testMode;
    }

    private void l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j() {
        PollFish.ParamsBuilder paramsBuilder;
        PollFish.ParamsBuilder paramsBuilder2 = paramsBuilder;
        new PollFish.ParamsBuilder(this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A);
        PollFish.initWith(this.container.$context(), paramsBuilder2.releaseMode(!this.testMode).rewardMode(true).offerWallMode(SurveyMode().toLowerCase().contains("offerwall")).pollfishCompletedSurveyListener(this).pollfishReceivedSurveyListener(this).pollfishOpenedListener(this).pollfishClosedListener(this).pollfishSurveyNotAvailableListener(this).pollfishUserNotEligibleListener(this).pollfishUserRejectedSurveyListener(this).build());
    }

    public final void onResume() {
        l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j();
    }

    @SimpleEvent(description = "Called when Pollfish Survey was closed.")
    public final void SurveyClosed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "SurveyClosed", new Object[0]);
    }

    @SimpleEvent(description = "Called when Pollfish survey was completed. surveyInfo is CSV list containing CPA, LOI, IR. Empty for Survey Offerwall.")
    public final void SurveyCompleted(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "SurveyCompleted", str);
    }

    @SimpleEvent(description = "Called when Survey was opened.")
    public final void SurveyOpened() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "SurveyOpened", new Object[0]);
    }

    @SimpleEvent(description = "Called when device receives survey and user can be prompted to start the survey.surveyInfo is CSV list containing CPA, LOI, IR. Empty for Survey Offerwall.")
    public final void SurveyReceived(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "SurveyReceived", str);
    }

    @SimpleEvent(description = "Called when Pollfish Survey was not available.")
    public final void SurveyNotAvailable() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "SurveyNotAvailable", new Object[0]);
    }

    @SimpleEvent(description = "Called when Pollfish user was not eligible.")
    public final void PollfishUserNotEligible() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "PollfishUserNotEligible", new Object[0]);
    }

    @SimpleEvent(description = "Called when Pollfish user was rejected.")
    public final void PollfishUserRejected() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "PollfishUserRejected", new Object[0]);
    }

    public final void onPollfishClosed() {
        SurveyClosed();
    }

    public final void onPollfishSurveyCompleted(SurveyInfo surveyInfo) {
        SurveyCompleted(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(surveyInfo));
    }

    public final void onPollfishOpened() {
        SurveyOpened();
    }

    public final void onPollfishSurveyReceived(SurveyInfo surveyInfo) {
        SurveyReceived(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(surveyInfo));
    }

    private static String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(SurveyInfo surveyInfo) {
        StringBuilder sb;
        SurveyInfo surveyInfo2 = surveyInfo;
        if (surveyInfo2 == null) {
            return "";
        }
        new StringBuilder("CPA=");
        return sb.append(surveyInfo2.getSurveyCPA()).append(",LOI=").append(surveyInfo2.getSurveyLOI()).append(",IR=").append(surveyInfo2.getSurveyIR()).append(",Reward Name=").append(surveyInfo2.getRewardName()).append(",Reward Value=").append(surveyInfo2.getRewardValue()).toString();
    }

    public final void onPollfishSurveyNotAvailable() {
        SurveyNotAvailable();
    }

    public final void onUserNotEligible() {
        PollfishUserNotEligible();
    }

    public final void onUserRejectedSurvey() {
        PollfishUserRejected();
    }
}
