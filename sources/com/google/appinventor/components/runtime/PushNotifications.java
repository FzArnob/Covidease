package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
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
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.YailList;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import org.shaded.apache.http.auth.AUTH;
import org.shaded.apache.http.client.methods.HttpPost;
import org.shaded.apache.http.protocol.HTTP;

@DesignerComponent(category = ComponentCategory.SOCIAL, description = "Push Notifications component powered by OneSignal", iconName = "images/pushNotifications.png", nonVisible = true, version = 6)
@UsesLibraries(libraries = "OneSignal.jar, OneSignal.aar,play-services-base.aar, play-services-base.jar,play-services-basement.aar, play-services-basement.jar,play-services-gcm.aar, play-services-gcm.jar,play-services-iid.aar, play-services-iid.jar,play-services-location.aar, play-services-location.jar,play-services-places-placereport.aar, play-services-places-placereport.jar,play-services-stats.aar, play-services-stats.jar,play-services-tasks.aar, play-services-tasks.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET,com.google.android.c2dm.permission.RECEIVE,android.permission.WAKE_LOCK,android.permission.VIBRATE,android.permission.ACCESS_NETWORK_STATE,android.permission.RECEIVE_BOOT_COMPLETED,android.permission.READ_APP_BADGE,com.sec.android.provider.badge.permission.READ,com.sec.android.provider.badge.permission.WRITE,com.htc.launcher.permission.READ_SETTINGS,com.htc.launcher.permission.UPDATE_SHORTCUT,com.sonyericsson.home.permission.BROADCAST_BADGE,com.sonymobile.home.permission.PROVIDER_INSERT_BADGE,com.anddoes.launcher.permission.UPDATE_COUNT,com.majeur.launcher.permission.UPDATE_BADGE,com.huawei.android.launcher.permission.CHANGE_BADGE,com.huawei.android.launcher.permission.READ_SETTINGS,com.huawei.android.launcher.permission.WRITE_SETTINGS,com.oppo.launcher.permission.READ_SETTINGS,com.oppo.launcher.permission.WRITE_SETTINGS,me.everything.badger.permission.BADGE_COUNT_READ,me.everything.badger.permission.BADGE_COUNT_WRITE")
public final class PushNotifications extends AndroidNonvisibleComponent implements Component {
    private Activity activity;
    private String appId = "";
    private ComponentContainer container;
    private Context context;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PushNotifications(com.google.appinventor.components.runtime.ComponentContainer r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.appId = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            java.lang.String r2 = "Push Notifications"
            java.lang.String r3 = "Initialized"
            int r2 = android.util.Log.d(r2, r3)
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            android.content.Context r2 = r2.context
            com.onesignal.OneSignal$Builder r2 = com.onesignal.OneSignal.startInit(r2)
            r3 = 0
            com.onesignal.OneSignal$Builder r2 = r2.autoPromptLocation(r3)
            r3 = 1
            com.onesignal.OneSignal$Builder r2 = r2.unsubscribeWhenNotificationsAreDisabled(r3)
            com.google.appinventor.components.runtime.PushNotifications$b r3 = new com.google.appinventor.components.runtime.PushNotifications$b
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r6 = 0
            r4.<init>(r5, r6)
            com.onesignal.OneSignal$Builder r2 = r2.setNotificationReceivedHandler(r3)
            com.google.appinventor.components.runtime.PushNotifications$a r3 = new com.google.appinventor.components.runtime.PushNotifications$a
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r6 = 0
            r4.<init>(r5, r6)
            com.onesignal.OneSignal$Builder r2 = r2.setNotificationOpenedHandler(r3)
            r2.init()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.PushNotifications.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set here your One Signal App ID", userVisible = false)
    public final void OneSignalAppId(String str) {
        String str2 = str;
        this.appId = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Get Permission Status")
    public final boolean GetPermissionStatus() {
        return OneSignal.getPermissionSubscriptionState().getPermissionStatus().getEnabled();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Get the subscription Status")
    public final boolean GetSubscriptionStatus() {
        try {
            return OneSignal.getPermissionSubscriptionState().getSubscriptionStatus().getSubscribed();
        } catch (Exception e) {
            return false;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Get the User ID. If there is no user id it will return '-1'.")
    public final String GetUserId() {
        try {
            OSPermissionSubscriptionState permissionSubscriptionState = OneSignal.getPermissionSubscriptionState();
            OSPermissionSubscriptionState oSPermissionSubscriptionState = permissionSubscriptionState;
            if (permissionSubscriptionState.getSubscriptionStatus().getUserId() == null) {
                return "-1";
            }
            return oSPermissionSubscriptionState.getSubscriptionStatus().getUserId();
        } catch (Exception e) {
            return "-1";
        }
    }

    @SimpleProperty(description = "If you want to subscribe then set it to true.")
    public final void SetSubscription(boolean z) {
        OneSignal.setSubscription(z);
    }

    @SimpleFunction(description = "Clear All Notifications.")
    public final void ClearAllNotifications() {
        OneSignal.clearOneSignalNotifications();
    }

    @SimpleProperty(description = "If you want to enable the log then set it to true.")
    public final void EnableLog(boolean z) {
        if (z) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.setLogLevel(log_level, log_level);
            return;
        }
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.NONE);
    }

    @SimpleProperty(description = "Enable Vibration.")
    public final void EnableVibration(boolean z) {
        OneSignal.enableVibrate(z);
    }

    @SimpleProperty(description = "Enable Sound.")
    public final void EnableSound(boolean z) {
        OneSignal.enableSound(z);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Get the email subscription user id.")
    public final String GetEmailSubscriptionUserId() {
        String emailUserId = OneSignal.getPermissionSubscriptionState().getEmailSubscriptionStatus().getEmailUserId();
        String str = emailUserId;
        if (emailUserId != null) {
            return str;
        }
        return "";
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Get the email subscription email address.")
    public final String GetEmailSubscriptionEmailAddress() {
        String emailAddress = OneSignal.getPermissionSubscriptionState().getEmailSubscriptionStatus().getEmailAddress();
        String str = emailAddress;
        if (emailAddress != null) {
            return str;
        }
        return "";
    }

    @SimpleFunction(description = "Tag a user based on an app event of your choosing so later you can create segments in to target these users.")
    public final void SendTag(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        if (!str4.isEmpty()) {
            OneSignal.sendTag(str3, str4);
        }
    }

    @SimpleFunction(description = "Deletes a single tag that was previously set on a user.")
    public final void DeleteTag(String str) {
        OneSignal.deleteTag(str);
    }

    @SimpleFunction(description = "Get a list of available tags.")
    public final void GetAvailableTags() {
        Runnable runnable;
        new Runnable(this) {
            final /* synthetic */ PushNotifications hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                OneSignal.GetTagsHandler getTagsHandler;
                new OneSignal.GetTagsHandler(this) {
                    private /* synthetic */ C09971 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                    }

                    public final void tagsAvailable(JSONObject jSONObject) {
                        StringBuilder sb;
                        JSONObject jSONObject2 = jSONObject;
                        if (jSONObject2 == null) {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotAvailableTags("{}", YailList.makeEmptyList());
                            return;
                        }
                        Iterator<String> keys = jSONObject2.keys();
                        int i = 0;
                        String[] strArr = new String[jSONObject2.length()];
                        while (keys.hasNext()) {
                            int i2 = i;
                            i++;
                            strArr[i2] = keys.next();
                        }
                        PushNotifications pushNotifications = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                        new StringBuilder();
                        pushNotifications.GotAvailableTags(sb.append(jSONObject2.toString()).toString(), YailList.makeList((Object[]) strArr));
                    }
                };
                OneSignal.getTags(getTagsHandler);
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    @SimpleFunction(description = "Get value for tag.")
    public final void GetValue(String str, String str2) {
        Runnable runnable;
        final String str3 = str;
        final String str4 = str2;
        new Runnable(this) {
            final /* synthetic */ PushNotifications hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                OneSignal.GetTagsHandler getTagsHandler;
                new OneSignal.GetTagsHandler(this) {
                    private /* synthetic */ C09992 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                    }

                    public final void tagsAvailable(JSONObject jSONObject) {
                        String str;
                        JSONObject jSONObject2 = jSONObject;
                        if (jSONObject2 == null) {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotValue(str3, str4);
                            return;
                        }
                        try {
                            str = jSONObject2.getString(str3);
                        } catch (JSONException e) {
                            str = str4;
                        }
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotValue(str3, str);
                    }
                };
                OneSignal.getTags(getTagsHandler);
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    @SimpleEvent(description = "Event to detect available one signal tags.")
    public final void GotAvailableTags(String str, YailList yailList) {
        Runnable runnable;
        final String str2 = str;
        final YailList yailList2 = yailList;
        new Runnable(this) {
            private /* synthetic */ PushNotifications hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                PushNotifications pushNotifications = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                Object[] objArr = new Object[2];
                objArr[0] = str2;
                Object[] objArr2 = objArr;
                objArr2[1] = yailList2;
                boolean dispatchEvent = EventDispatcher.dispatchEvent(pushNotifications, "GotAvailableTags", objArr2);
            }
        };
        this.activity.runOnUiThread(runnable);
    }

    @SimpleEvent(description = "Event to receive value for a tag.")
    public final void GotValue(String str, String str2) {
        Runnable runnable;
        final String str3 = str;
        final String str4 = str2;
        new Runnable(this) {
            private /* synthetic */ PushNotifications hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                PushNotifications pushNotifications = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                Object[] objArr = new Object[2];
                objArr[0] = str3;
                Object[] objArr2 = objArr;
                objArr2[1] = str4;
                boolean dispatchEvent = EventDispatcher.dispatchEvent(pushNotifications, "GotValue", objArr2);
            }
        };
        this.activity.runOnUiThread(runnable);
    }

    /* renamed from: com.google.appinventor.components.runtime.PushNotifications$b */
    class C1005b implements OneSignal.NotificationReceivedHandler {
        private /* synthetic */ PushNotifications hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        private C1005b(PushNotifications pushNotifications) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = pushNotifications;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C1005b(PushNotifications pushNotifications, byte b) {
            this(pushNotifications);
            byte b2 = b;
        }

        public final void notificationReceived(OSNotification oSNotification) {
            OSNotification oSNotification2 = oSNotification;
            String str = oSNotification2.payload.notificationID;
            String str2 = oSNotification2.payload.title;
            String str3 = oSNotification2.payload.body;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.NotificationReceived(str == null ? "" : str, str2 == null ? "" : str2, str3 == null ? "" : str3);
        }
    }

    @SimpleEvent(description = "User received a notification.")
    public final void NotificationReceived(String str, String str2, String str3) {
        Object[] objArr = new Object[3];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        Object[] objArr3 = objArr2;
        objArr3[2] = str3;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "NotificationReceived", objArr3);
    }

    /* renamed from: com.google.appinventor.components.runtime.PushNotifications$a */
    class C1004a implements OneSignal.NotificationOpenedHandler {
        private /* synthetic */ PushNotifications hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        private C1004a(PushNotifications pushNotifications) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = pushNotifications;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C1004a(PushNotifications pushNotifications, byte b) {
            this(pushNotifications);
            byte b2 = b;
        }

        public final void notificationOpened(OSNotificationOpenResult oSNotificationOpenResult) {
            OSNotificationOpenResult oSNotificationOpenResult2 = oSNotificationOpenResult;
            OSNotificationAction.ActionType actionType = oSNotificationOpenResult2.action.type;
            String str = oSNotificationOpenResult2.notification.payload.notificationID;
            String str2 = oSNotificationOpenResult2.notification.payload.title;
            String str3 = oSNotificationOpenResult2.notification.payload.body;
            if (actionType == OSNotificationAction.ActionType.ActionTaken) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.NotificationOpened(str == null ? "" : str, str2 == null ? "" : str2, str3 == null ? "" : str3);
            }
        }
    }

    @SimpleEvent(description = "User opened a notification.")
    public final void NotificationOpened(String str, String str2, String str3) {
        Object[] objArr = new Object[3];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        Object[] objArr3 = objArr2;
        objArr3[2] = str3;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "NotificationOpened", objArr3);
    }

    @SimpleFunction(description = "Send a message to all users. The message and your REST Api Key can not be empty! You will find your REST Api Key in your OneSignal account settings.")
    public final void SendMessage(String str, String str2, String str3) {
        C10035 r11;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        new AsyncTask<Integer, Void, Boolean>(this) {
            private /* synthetic */ PushNotifications hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
            }

            /* access modifiers changed from: protected */
            public final /* synthetic */ Object doInBackground(Object[] objArr) {
                Object[] objArr2 = objArr;
                return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T();
            }

            /* access modifiers changed from: protected */
            public final /* synthetic */ void onPostExecute(Object obj) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.SendMessageDone(((Boolean) obj).booleanValue());
            }

            private Boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T() {
                try {
                    return Boolean.valueOf(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str4, str5, str6));
                } catch (Exception e) {
                    int e2 = Log.e("Push Notifications", String.valueOf(e));
                    return Boolean.FALSE;
                }
            }
        };
        AsyncTask execute = r11.execute(new Integer[0]);
    }

    @SimpleEvent(description = "Returns true if your message was send with success out of your app to all users.")
    public final void SendMessageDone(boolean z) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "SendMessageDone", Boolean.valueOf(z));
    }

    /* access modifiers changed from: private */
    public boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str, String str2, String str3) {
        URL url;
        StringBuilder sb;
        byte[] bytes;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        HttpURLConnection httpURLConnection = null;
        try {
            new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
            httpURLConnection = httpURLConnection2;
            httpURLConnection2.setUseCaches(false);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty(HTTP.CONTENT_TYPE, "application/json; charset=UTF-8");
            httpURLConnection.setRequestProperty(AUTH.WWW_AUTH_RESP, "Basic ".concat(String.valueOf(str6)));
            httpURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
            new StringBuilder("{\"app_id\": \"");
            String sb2 = sb.append(this.appId).append("\",\"included_segments\": [\"All\"],\"headings\": {\"en\": \"").append(str4).append("\"},\"contents\": {\"en\": \"").append(str5).append("\"}}").toString();
            if (Build.VERSION.SDK_INT >= 19) {
                bytes = sb2.getBytes(StandardCharsets.UTF_8);
            } else {
                bytes = sb2.getBytes("UTF-8");
            }
            httpURLConnection.setFixedLengthStreamingMode(bytes.length);
            httpURLConnection.getOutputStream().write(bytes);
            boolean z = httpURLConnection.getResponseCode() == 200;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return z;
        } catch (Exception e) {
            int e2 = Log.e("Push Notifications", String.valueOf(e));
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return false;
        } catch (Throwable th) {
            Throwable th2 = th;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th2;
        }
    }
}
