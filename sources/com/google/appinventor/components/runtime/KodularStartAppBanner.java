package com.google.appinventor.components.runtime;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularAdsCommission;
import com.google.appinventor.components.runtime.util.KodularAdsUtil;
import com.google.appinventor.components.runtime.util.KodularContentProtection;
import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.ads.banner.BannerListener;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import org.shaded.apache.http.client.methods.HttpPost;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "Component for displaying banner ads from StartApp", iconName = "images/kodularStartAppBanner.png", nonVisible = false, version = 1)
@UsesLibraries(libraries = "startapp-ads.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE, android.permission.ACCESS_WIFI_STATE")
public final class KodularStartAppBanner extends AndroidViewComponent implements BannerListener {
    private static final String LOG_TAG = "StartApp Banner";
    private Activity activity;
    private String appId;
    /* access modifiers changed from: private */
    public Banner bannerView;
    private ComponentContainer container;
    /* access modifiers changed from: private */
    public Form form;
    /* access modifiers changed from: private */
    public boolean isAddedToContainer = false;
    private boolean isInitialized = false;
    private KodularAdsCommission kodularAdsCommission;
    private KodularContentProtection kodularContentProtection;
    LinearLayout mainLayout;
    private StartAppAd startAppAd;

    static /* synthetic */ boolean access$002(KodularStartAppBanner kodularStartAppBanner, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        kodularStartAppBanner.isAddedToContainer = z3;
        return z2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularStartAppBanner(com.google.appinventor.components.runtime.ComponentContainer r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.isInitialized = r3
            r2 = r0
            r3 = 0
            r2.isAddedToContainer = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.KodularAdsCommission r3 = new com.google.appinventor.components.runtime.util.KodularAdsCommission
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r6 = r0
            com.google.appinventor.components.runtime.Form r6 = r6.form
            r4.<init>(r5, r6)
            r2.kodularAdsCommission = r3
            r2 = r0
            android.widget.LinearLayout r3 = new android.widget.LinearLayout
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r4.<init>(r5)
            r2.mainLayout = r3
            r2 = r0
            android.widget.LinearLayout r2 = r2.mainLayout
            r3 = 1
            r2.setGravity(r3)
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r0
            com.google.appinventor.components.runtime.util.KodularContentProtection r3 = new com.google.appinventor.components.runtime.util.KodularContentProtection
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            android.app.Activity r5 = r5.activity
            r4.<init>(r5)
            r2.kodularContentProtection = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.KodularContentProtection r2 = r2.kodularContentProtection
            com.google.appinventor.components.runtime.KodularStartAppBanner$1 r3 = new com.google.appinventor.components.runtime.KodularStartAppBanner$1
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.setOnValidationResultListener(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularStartAppBanner.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    /* access modifiers changed from: private */
    public void littleBannerHelper(boolean z) {
        Banner banner;
        ViewGroup.LayoutParams layoutParams;
        if (z) {
            this.mainLayout.removeView(this.bannerView);
        }
        new Banner(this.container.$context(), this);
        this.bannerView = banner;
        new LinearLayout.LayoutParams(-1, -2);
        this.mainLayout.addView(this.bannerView, layoutParams);
    }

    @DesignerProperty(defaultValue = "True", editorType = "visibility")
    @SimpleProperty(description = "Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.")
    public final void Visible(boolean z) {
        boolean z2 = z;
        if (this.bannerView != null) {
            if (z2) {
                this.bannerView.showBanner();
            } else {
                this.bannerView.hideBanner();
            }
            super.Visible(z2);
        }
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(userVisible = false)
    public final void AppId(String str) {
        String str2 = str;
        this.appId = str2;
    }

    @SimpleFunction(description = "Load a new StartApp Banner ad.")
    public final void LoadAd() {
        this.kodularContentProtection.startContentValidation(this.form.getAppId());
    }

    @SimpleEvent(description = "Called when an ad request failed to load.")
    public final void OnFailedToReceiveAd(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnFailedToReceiveAd", str);
    }

    @SimpleEvent(description = "Called when an ad request succeeds.")
    public final void OnReceiveAd() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnReceiveAd", new Object[0]);
    }

    @SimpleEvent(description = "Indicates that the user has clicked on the banner ad.")
    public final void AdClicked() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdClicked", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public final void AdFailedToLoad(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AdFailedToLoad", objArr2);
    }

    /* access modifiers changed from: private */
    public void initStartAppSdk() {
        Random random;
        StringBuilder sb;
        new Random();
        if (random.nextFloat() <= this.kodularAdsCommission.getCommision(Component.COMMISSION_BANNER_ADS_NETWORK_DEFAULT, "banner")) {
            StartAppSDK.init(this.activity, KodularAdsUtil.STARTAPP_APP_ID, false);
        } else {
            StartAppSDK.init(this.activity, "".equals(this.appId) ? KodularAdsUtil.STARTAPP_APP_ID : this.appId, false);
            new StringBuilder("Initializing Startapp SDK wit APP ID");
            int i = Log.i(LOG_TAG, sb.append(this.appId).toString());
        }
        StartAppAd.disableSplash();
        StartAppSDK.setUserConsent(this.container.$context(), "pas", System.currentTimeMillis(), false);
    }

    @SuppressLint({"StaticFieldLeak"})
    private void sendStartAppBannerLogs(String str) {
        C08102 r6;
        final String str2 = str;
        new AsyncTask<Integer, Void, Void>(this) {
            private /* synthetic */ KodularStartAppBanner hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            /* access modifiers changed from: protected */
            public final /* synthetic */ Object doInBackground(Object[] objArr) {
                Object[] objArr2 = objArr;
                return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
            }

            private Void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
                URL url;
                Uri.Builder builder;
                BufferedWriter bufferedWriter;
                Writer writer;
                BufferedWriter bufferedWriter2;
                StringBuilder sb;
                StringBuilder sb2;
                BufferedWriter bufferedWriter3;
                Writer writer2;
                HttpURLConnection httpURLConnection = null;
                try {
                    new URL("https://account.kodular.io/api/startapp");
                    HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
                    httpURLConnection = httpURLConnection2;
                    httpURLConnection2.setRequestMethod(HttpPost.METHOD_NAME);
                    new Uri.Builder();
                    Uri.Builder builder2 = builder;
                    Uri.Builder builder3 = builder2;
                    Uri.Builder appendQueryParameter = builder2.appendQueryParameter("i", this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.getAppId());
                    Uri.Builder appendQueryParameter2 = builder3.appendQueryParameter("p", this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.getPackageName());
                    Uri.Builder appendQueryParameter3 = builder3.appendQueryParameter("s", str2);
                    String encodedQuery = builder3.build().getEncodedQuery();
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    if (Build.VERSION.SDK_INT >= 19) {
                        new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                        new BufferedWriter(writer2);
                        bufferedWriter2 = bufferedWriter3;
                    } else {
                        BufferedWriter bufferedWriter4 = bufferedWriter;
                        new OutputStreamWriter(outputStream, "UTF-8");
                        new BufferedWriter(writer);
                        bufferedWriter2 = bufferedWriter4;
                    }
                    bufferedWriter2.write(encodedQuery);
                    bufferedWriter2.flush();
                    bufferedWriter2.close();
                    outputStream.close();
                    new StringBuilder("Response code:");
                    int i = Log.i(KodularStartAppBanner.LOG_TAG, sb.append(httpURLConnection.getResponseCode()).toString());
                    new StringBuilder("Response message:");
                    int i2 = Log.i(KodularStartAppBanner.LOG_TAG, sb2.append(httpURLConnection.getResponseMessage()).toString());
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } catch (Exception e) {
                    int e2 = Log.e(KodularStartAppBanner.LOG_TAG, String.valueOf(e));
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th2;
                }
                return null;
            }

            /* access modifiers changed from: protected */
            public final /* synthetic */ void onPostExecute(Object obj) {
                Object obj2 = obj;
                int i = Log.i(KodularStartAppBanner.LOG_TAG, "Log was send");
            }
        };
        AsyncTask execute = r6.execute(new Integer[0]);
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

    public final View getView() {
        return this.mainLayout;
    }

    public final void onReceiveAd(View view) {
        View view2 = view;
        OnReceiveAd();
    }

    public final void onFailedToReceiveAd(View view) {
        View view2 = view;
        OnFailedToReceiveAd("Cannot load banner ad");
    }

    public final void onClick(View view) {
        View view2 = view;
        AdClicked();
    }
}
