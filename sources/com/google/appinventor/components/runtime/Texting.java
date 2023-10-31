package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesBroadcastReceivers;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.annotations.androidmanifest.ActionElement;
import com.google.appinventor.components.annotations.androidmanifest.IntentFilterElement;
import com.google.appinventor.components.annotations.androidmanifest.ReceiverElement;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.OAuth2Helper;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import com.google.appinventor.components.runtime.util.SmsBroadcastReceiver;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import org.json.JSONException;
import org.json.JSONObject;
import org.shaded.apache.http.auth.AUTH;
import org.shaded.apache.http.cookie.C1496SM;

@DesignerComponent(category = ComponentCategory.SOCIAL, description = "<p>A component that will, when the <code>SendMessage</code> method is called, send the text message specified in the <code>Message</code> property to the phone number specified in the <code>PhoneNumber</code> property.</p> <p>If the <code>ReceivingEnabled</code> property is set to 1 messages will <b>not</b> be received. If <code>ReceivingEnabled</code> is set to 2 messages will be received only when the application is running. Finally if <code>ReceivingEnabled</code> is set to 3, messages will be received when the application is running <b>and</b> when the application is not running they will be queued and a notification displayed to the user.</p> <p>When a message arrives, the <code>MessageReceived</code> event is raised and provides the sending number and message.</p> <p> An app that includes this component will receive messages even when it is in the background (i.e. when it's not visible on the screen) and, moreso, even if the app is not running, so long as it's installed on the phone. If the phone receives a text message when the app is not in the foreground, the phone will show a notification in the notification bar.  Selecting the notification will bring up the app.  As an app developer, you'll probably want to give your users the ability to control ReceivingEnabled so that they can make the phone ignore text messages.</p> <p>If the GoogleVoiceEnabled property is true, messages can be sent over Wifi using Google Voice. This option requires that the user have a Google Voice account and that the mobile Voice app is installed on the phone. The Google Voice option works only on phones that support Android 2.0 (Eclair) or higher.</p> <p>To specify the phone number (e.g., 650-555-1212), set the <code>PhoneNumber</code> property to a Text string with the specified digits (e.g., 6505551212).  Dashes, dots, and parentheses may be included (e.g., (650)-555-1212) but will be ignored; spaces may not be included.</p> <p>Another way for an app to specify a phone number would be to include a <code>PhoneNumberPicker</code> component, which lets the users select a phone numbers from the ones stored in the the phone's contacts.</p>", iconName = "images/texting.png", nonVisible = true, version = 3)
@UsesLibraries(libraries = "google-api-client-beta.jar,google-api-client-android2-beta.jar,google-http-client-beta.jar,google-http-client-android2-beta.jar,google-http-client-android3-beta.jar,google-oauth-client-beta.jar,guava-14.0.1.jar")
@SimpleObject
@UsesBroadcastReceivers(receivers = {@ReceiverElement(intentFilters = {@IntentFilterElement(actionElements = {@ActionElement(name = "android.provider.Telephony.SMS_RECEIVED"), @ActionElement(name = "com.google.android.apps.googlevoice.SMS_RECEIVED")})}, name = "com.google.appinventor.components.runtime.util.SmsBroadcastReceiver")})
@UsesPermissions(permissionNames = "android.permission.RECEIVE_SMS, android.permission.SEND_SMS, com.google.android.apps.googlevoice.permission.RECEIVE_SMS, com.google.android.apps.googlevoice.permission.SEND_SMS, android.permission.ACCOUNT_MANAGER, android.permission.MANAGE_ACCOUNTS, android.permission.GET_ACCOUNTS, android.permission.USE_CREDENTIALS")
public class Texting extends AndroidNonvisibleComponent implements Component, OnPauseListener, OnResumeListener, OnStopListener, OnInitializeListener {
    private static final String CACHE_FILE = "textingmsgcache";
    public static final String GV_INTENT_FILTER = "com.google.android.apps.googlevoice.SMS_RECEIVED";
    public static final String GV_PACKAGE_NAME = "com.google.android.apps.googlevoice";
    private static final String GV_SERVICE = "grandcentral";
    public static final String GV_SMS_RECEIVED = "com.google.android.apps.googlevoice.SMS_RECEIVED";
    public static final String GV_SMS_SEND_URL = "https://www.google.com/voice/b/0/sms/send/";
    public static final String GV_URL = "https://www.google.com/voice/b/0/redirection/voice";
    private static final String MESSAGE_DELIMITER = "\u0001";
    public static final String MESSAGE_TAG = "com.google.android.apps.googlevoice.TEXT";
    public static final String META_DATA_SMS_KEY = "sms_handler_component";
    public static final String META_DATA_SMS_VALUE = "Texting";
    public static final String PHONE_NUMBER_TAG = "com.google.android.apps.googlevoice.PHONE_NUMBER";
    private static final String PREF_FILE = "TextingState";
    private static final String PREF_GVENABLED = "gvenabled";
    private static final String PREF_RCVENABLED = "receiving2";
    private static final String PREF_RCVENABLED_LEGACY = "receiving";
    private static final String SENT = "SMS_SENT";
    private static final int SERVER_TIMEOUT_MS = 30000;
    public static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    public static final String TAG = "Texting Component";
    public static final String TELEPHONY_INTENT_FILTER = "android.provider.Telephony.SMS_RECEIVED";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.A.B.C Safari/525.13";
    private static final String UTF8 = "UTF-8";
    /* access modifiers changed from: private */
    public static Activity activity;
    private static Object cacheLock;
    private static Component component;
    private static boolean isRunning;
    private static int messagesCached;
    private static int receivingEnabled = 2;
    /* access modifiers changed from: private */
    public String authToken;
    private ComponentContainer container;
    private boolean googleVoiceEnabled;
    /* access modifiers changed from: private */
    public C1044c gvHelper;
    private boolean havePermission = false;
    private boolean haveReceivePermission = false;
    private boolean isInitialized;
    /* access modifiers changed from: private */
    public String message;
    private Queue<String> pendingQueue;
    private String phoneNumber;
    private SmsManager smsManager;

    static /* synthetic */ boolean access$002(Texting texting, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        texting.havePermission = z3;
        return z2;
    }

    static /* synthetic */ boolean access$502(Texting texting, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        texting.haveReceivePermission = z3;
        return z2;
    }

    static /* synthetic */ String access$602(Texting texting, String str) {
        String str2 = str;
        String str3 = str2;
        texting.authToken = str3;
        return str2;
    }

    static /* synthetic */ C1044c access$802(Texting texting, C1044c cVar) {
        C1044c cVar2 = cVar;
        C1044c cVar3 = cVar2;
        texting.gvHelper = cVar3;
        return cVar2;
    }

    static {
        Object obj;
        new Object();
        cacheLock = obj;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Texting(com.google.appinventor.components.runtime.ComponentContainer r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r3 = r0
            r4 = r1
            com.google.appinventor.components.runtime.Form r4 = r4.$form()
            r3.<init>(r4)
            r3 = r0
            java.util.concurrent.ConcurrentLinkedQueue r4 = new java.util.concurrent.ConcurrentLinkedQueue
            r7 = r4
            r4 = r7
            r5 = r7
            r5.<init>()
            r3.pendingQueue = r4
            r3 = r0
            r4 = 0
            r3.havePermission = r4
            r3 = r0
            r4 = 0
            r3.haveReceivePermission = r4
            java.lang.String r3 = "Texting Component"
            java.lang.String r4 = "Texting constructor"
            int r3 = android.util.Log.d(r3, r4)
            r3 = r0
            r4 = r1
            r3.container = r4
            r3 = r0
            component = r3
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r7 = r3
            r3 = r7
            r4 = r7
            activity = r4
            java.lang.String r4 = "TextingState"
            r5 = 0
            android.content.SharedPreferences r3 = r3.getSharedPreferences(r4, r5)
            r7 = r3
            r3 = r7
            r4 = r7
            r2 = r4
            if (r3 == 0) goto L_0x00f0
            r3 = r2
            java.lang.String r4 = "receiving2"
            r5 = -1
            int r3 = r3.getInt(r4, r5)
            r7 = r3
            r3 = r7
            r4 = r7
            receivingEnabled = r4
            r4 = -1
            if (r3 != r4) goto L_0x0066
            r3 = r2
            java.lang.String r4 = "receiving"
            r5 = 1
            boolean r3 = r3.getBoolean(r4, r5)
            if (r3 == 0) goto L_0x00eb
            r3 = 2
            receivingEnabled = r3
        L_0x0066:
            r3 = r0
            r4 = r2
            java.lang.String r5 = "gvenabled"
            r6 = 0
            boolean r4 = r4.getBoolean(r5, r6)
            r3.googleVoiceEnabled = r4
            java.lang.String r3 = "Texting Component"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r7 = r4
            r4 = r7
            r5 = r7
            java.lang.String r6 = "Starting with receiving Enabled="
            r5.<init>(r6)
            int r5 = receivingEnabled
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " GV enabled="
            java.lang.StringBuilder r4 = r4.append(r5)
            r5 = r0
            boolean r5 = r5.googleVoiceEnabled
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            int r3 = android.util.Log.i(r3, r4)
        L_0x009c:
            r3 = r0
            boolean r3 = r3.googleVoiceEnabled
            if (r3 == 0) goto L_0x00b1
            com.google.appinventor.components.runtime.Texting$a r3 = new com.google.appinventor.components.runtime.Texting$a
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r4 = 0
            java.lang.Void[] r4 = new java.lang.Void[r4]
            android.os.AsyncTask r3 = r3.execute(r4)
        L_0x00b1:
            r3 = r0
            android.telephony.SmsManager r4 = android.telephony.SmsManager.getDefault()
            r3.smsManager = r4
            r3 = r0
            java.lang.String r4 = ""
            r3.PhoneNumber(r4)
            r3 = r0
            r4 = 0
            r3.isInitialized = r4
            r3 = 0
            isRunning = r3
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r4 = r0
            r3.registerForOnInitialize(r4)
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r4 = r0
            r3.registerForOnResume(r4)
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r4 = r0
            r3.registerForOnPause(r4)
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r4 = r0
            r3.registerForOnStop(r4)
            return
        L_0x00eb:
            r3 = 1
            receivingEnabled = r3
            goto L_0x0066
        L_0x00f0:
            r3 = 2
            receivingEnabled = r3
            r3 = r0
            r4 = 0
            r3.googleVoiceEnabled = r4
            goto L_0x009c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Texting.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public void onInitialize() {
        int i = Log.i(TAG, "onInitialize()");
        this.isInitialized = true;
        isRunning = true;
        processCachedMessages();
        ((NotificationManager) activity.getSystemService("notification")).cancel(SmsBroadcastReceiver.NOTIFICATION_ID);
    }

    public void Initialize() {
        if (receivingEnabled > 1 && !this.haveReceivePermission) {
            requestReceiveSmsPermission("Initialize");
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public void PhoneNumber(String str) {
        String str2 = str;
        int i = Log.i(TAG, "PhoneNumber set: ".concat(String.valueOf(str2)));
        String str3 = str2;
        this.phoneNumber = str3;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The number that the message will be sent to when the SendMessage method is called. The number is a text string with the specified digits (e.g., 6505551212).  Dashes, dots, and parentheses may be included (e.g., (650)-555-1212) but will be ignored; spaces should not be included.")
    public String PhoneNumber() {
        return this.phoneNumber;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The message that will be sent when the SendMessage method is called.")
    public void Message(String str) {
        String str2 = str;
        int i = Log.i(TAG, "Message set: ".concat(String.valueOf(str2)));
        String str3 = str2;
        this.message = str3;
    }

    @SimpleProperty
    public String Message() {
        return this.message;
    }

    @SimpleFunction
    public void SendMessage() {
        StringBuilder sb;
        C1043b bVar;
        StringBuilder sb2;
        StringBuilder sb3;
        C1042a aVar;
        new StringBuilder("Sending message ");
        int i = Log.i(TAG, sb.append(this.message).append(" to ").append(this.phoneNumber).toString());
        String str = this.phoneNumber;
        String str2 = this.message;
        if (!this.googleVoiceEnabled) {
            int i2 = Log.i(TAG, "Sending via SMS");
            sendViaSms("SendMessage");
        } else if (this.authToken == null) {
            new StringBuilder("Need to get an authToken -- enqueing ");
            int i3 = Log.i(TAG, sb2.append(str).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(str2).toString());
            Queue<String> queue = this.pendingQueue;
            new StringBuilder();
            if (!queue.offer(sb3.append(str).append(":::").append(str2).toString())) {
                Toast.makeText(activity, "Pending message queue full. Can't send message", 0).show();
            } else if (this.pendingQueue.size() == 1) {
                new C1042a(this);
                AsyncTask execute = aVar.execute(new Void[0]);
            }
        } else {
            int i4 = Log.i(TAG, "Creating AsyncSendMessage");
            C1043b bVar2 = bVar;
            new C1043b(this);
            String[] strArr = new String[2];
            strArr[0] = str;
            String[] strArr2 = strArr;
            strArr2[1] = str2;
            AsyncTask execute2 = bVar2.execute(strArr2);
        }
    }

    /* access modifiers changed from: private */
    public void processPendingQueue() {
        StringBuilder sb;
        C1043b bVar;
        while (this.pendingQueue.size() != 0) {
            String remove = this.pendingQueue.remove();
            String str = remove;
            String substring = remove.substring(0, str.indexOf(":::"));
            String str2 = str;
            String substring2 = str2.substring(str2.indexOf(":::") + 3);
            new StringBuilder("Sending queued message ");
            int i = Log.i(TAG, sb.append(substring).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(substring2).toString());
            C1043b bVar2 = bVar;
            new C1043b(this);
            String[] strArr = new String[2];
            strArr[0] = substring;
            String[] strArr2 = strArr;
            strArr2[1] = substring2;
            AsyncTask execute = bVar2.execute(strArr2);
        }
    }

    /* JADX INFO: finally extract failed */
    @SimpleEvent
    public static void MessageReceived(String str, String str2) {
        StringBuilder sb;
        String str3 = str;
        String str4 = str2;
        if (receivingEnabled > 1) {
            new StringBuilder("MessageReceived from ");
            int i = Log.i(TAG, sb.append(str3).append(":").append(str4).toString());
            Component component2 = component;
            Object[] objArr = new Object[2];
            objArr[0] = str3;
            Object[] objArr2 = objArr;
            objArr2[1] = str4;
            if (EventDispatcher.dispatchEvent(component2, "MessageReceived", objArr2)) {
                int i2 = Log.i(TAG, "Dispatch successful");
                return;
            }
            int i3 = Log.i(TAG, "Dispatch failed, caching");
            Object obj = cacheLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    addMessageToCache(activity, str3, str4);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true, then SendMessage will attempt to send messages over Wifi using Google Voice.  This requires that the Google Voice app must be installed and set up on the phone or tablet, with a Google Voice account.  If GoogleVoiceEnabled is false, the device must have phone and texting service in order to send or receive messages with this component.")
    public boolean GoogleVoiceEnabled() {
        return this.googleVoiceEnabled;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void GoogleVoiceEnabled(boolean z) {
        boolean z2 = z;
        if (Build.VERSION.SDK_INT >= 5) {
            this.googleVoiceEnabled = z2;
            SharedPreferences.Editor edit = activity.getSharedPreferences(PREF_FILE, 0).edit();
            SharedPreferences.Editor putBoolean = edit.putBoolean(PREF_GVENABLED, z2);
            boolean commit = edit.commit();
            return;
        }
        Toast.makeText(activity, "Sorry, your phone's system does not support this option.", 1).show();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If set to 1 (OFF) no messages will be received.  If set to 2 (FOREGROUND) or3 (ALWAYS) the component will respond to messages if it is running. If the app is not running then the message will be discarded if set to 2 (FOREGROUND). If set to 3 (ALWAYS) and the app is not running the phone will show a notification.  Selecting the notification will bring up the app and signal the MessageReceived event.  Messages received when the app is dormant will be queued, and so several MessageReceived events might appear when the app awakens.  As an app developer, it would be a good idea to give your users control over this property, so they can make their phones ignore text messages when your app is installed.")
    public int ReceivingEnabled() {
        return receivingEnabled;
    }

    @DesignerProperty(defaultValue = "2", editorType = "text_receiving")
    @SimpleProperty
    public void ReceivingEnabled(int i) {
        int i2 = i;
        if (i2 <= 0 || i2 > 3) {
            this.container.$form().dispatchErrorOccurredEvent(this, META_DATA_SMS_VALUE, ErrorMessages.ERROR_BAD_VALUE_FOR_TEXT_RECEIVING, Integer.valueOf(i2));
            return;
        }
        receivingEnabled = i2;
        SharedPreferences.Editor edit = activity.getSharedPreferences(PREF_FILE, 0).edit();
        SharedPreferences.Editor editor = edit;
        SharedPreferences.Editor putInt = edit.putInt(PREF_RCVENABLED, i2);
        SharedPreferences.Editor remove = editor.remove(PREF_RCVENABLED_LEGACY);
        boolean commit = editor.commit();
        if (i2 > 1 && !this.haveReceivePermission) {
            requestReceiveSmsPermission("ReceivingEnabled");
        }
    }

    public static int isReceivingEnabled(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences sharedPreferences2 = sharedPreferences;
        int i = sharedPreferences.getInt(PREF_RCVENABLED, -1);
        int i2 = i;
        if (i != -1) {
            return i2;
        }
        if (sharedPreferences2.getBoolean(PREF_RCVENABLED_LEGACY, true)) {
            return 2;
        }
        return 1;
    }

    public static SmsMessage[] getMessagesFromIntent(Intent intent) {
        Object[] objArr = (Object[]) intent.getSerializableExtra("pdus");
        Object[] objArr2 = objArr;
        byte[][] bArr = new byte[objArr.length][];
        for (int i = 0; i < objArr2.length; i++) {
            bArr[i] = (byte[]) objArr2[i];
        }
        byte[][] bArr2 = new byte[bArr.length][];
        byte[][] bArr3 = bArr2;
        int length = bArr2.length;
        int i2 = length;
        SmsMessage[] smsMessageArr = new SmsMessage[length];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr3[i3] = bArr[i3];
            smsMessageArr[i3] = SmsMessage.createFromPdu(bArr3[i3]);
        }
        return smsMessageArr;
    }

    /* JADX INFO: finally extract failed */
    private void processCachedMessages() {
        StringBuilder sb;
        StringBuilder sb2;
        Object obj = cacheLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                String[] retrieveCachedMessages = retrieveCachedMessages();
                if (retrieveCachedMessages != null) {
                    new StringBuilder("processing ");
                    int i = Log.i(TAG, sb.append(retrieveCachedMessages.length).append(" cached messages ").toString());
                    for (int i2 = 0; i2 < retrieveCachedMessages.length; i2++) {
                        String str = retrieveCachedMessages[i2];
                        new StringBuilder("Message + ");
                        int i3 = Log.i(TAG, sb2.append(i2).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(str).toString());
                        int indexOf = str.indexOf(":");
                        if (receivingEnabled > 1 && indexOf != -1) {
                            MessageReceived(str.substring(0, indexOf), str.substring(indexOf + 1));
                        }
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String[] retrieveCachedMessages() {
        /*
            r6 = this;
            r0 = r6
            java.lang.String r2 = "Texting Component"
            java.lang.String r3 = "Retrieving cached messages"
            int r2 = android.util.Log.i(r2, r3)
            java.lang.String r2 = "textingmsgcache"
            byte[] r2 = com.google.appinventor.components.runtime.util.FileUtil.readFile(r2)     // Catch:{ FileNotFoundException -> 0x0046, Exception -> 0x0054 }
            r1 = r2
            java.lang.String r2 = new java.lang.String     // Catch:{ FileNotFoundException -> 0x0046, Exception -> 0x0054 }
            r5 = r2
            r2 = r5
            r3 = r5
            r4 = r1
            r3.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0046, Exception -> 0x0054 }
            r1 = r2
            android.app.Activity r2 = activity     // Catch:{ FileNotFoundException -> 0x0046, Exception -> 0x0054 }
            java.lang.String r3 = "textingmsgcache"
            boolean r2 = r2.deleteFile(r3)     // Catch:{ FileNotFoundException -> 0x0046, Exception -> 0x0054 }
            r2 = 0
            messagesCached = r2     // Catch:{ FileNotFoundException -> 0x0046, Exception -> 0x0054 }
            java.lang.String r2 = "Texting Component"
            java.lang.String r3 = "Retrieved cache "
            r4 = r1
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ FileNotFoundException -> 0x0046, Exception -> 0x0054 }
            java.lang.String r3 = r3.concat(r4)     // Catch:{ FileNotFoundException -> 0x0046, Exception -> 0x0054 }
            int r2 = android.util.Log.i(r2, r3)     // Catch:{ FileNotFoundException -> 0x0046, Exception -> 0x0054 }
            r2 = r1
            java.lang.String r3 = "\u0001"
            java.lang.String[] r2 = r2.split(r3)
            r0 = r2
        L_0x0045:
            return r0
        L_0x0046:
            r2 = move-exception
            java.lang.String r2 = "Texting Component"
            java.lang.String r3 = "No Cache file found -- this is not (usually) an error"
            int r2 = android.util.Log.e(r2, r3)
            r2 = 0
            r0 = r2
            goto L_0x0045
        L_0x0054:
            r2 = move-exception
            java.lang.String r2 = "Texting Component"
            java.lang.String r3 = "I/O Error reading from cache file"
            int r2 = android.util.Log.e(r2, r3)
            r2 = 0
            r0 = r2
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Texting.retrieveCachedMessages():java.lang.String[]");
    }

    public static boolean isRunning() {
        return isRunning;
    }

    public static int getCachedMsgCount() {
        return messagesCached;
    }

    public void onResume() {
        int i = Log.i(TAG, "onResume()");
        isRunning = true;
        if (this.isInitialized) {
            processCachedMessages();
            ((NotificationManager) activity.getSystemService("notification")).cancel(SmsBroadcastReceiver.NOTIFICATION_ID);
        }
    }

    public void onPause() {
        int i = Log.i(TAG, "onPause()");
        isRunning = false;
    }

    public static void handledReceivedMessage(Context context, String str, String str2) {
        Context context2 = context;
        String str3 = str;
        String str4 = str2;
        if (isRunning) {
            MessageReceived(str3, str4);
            return;
        }
        Object obj = cacheLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                addMessageToCache(context2, str3, str4);
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    private static void addMessageToCache(Context context, String str, String str2) {
        StringBuilder sb;
        Context context2 = context;
        String str3 = str;
        String str4 = str2;
        try {
            new StringBuilder();
            String sb2 = sb.append(str3).append(":").append(str4).append(MESSAGE_DELIMITER).toString();
            int i = Log.i(TAG, "Caching ".concat(String.valueOf(sb2)));
            FileOutputStream openFileOutput = context2.openFileOutput(CACHE_FILE, 32768);
            openFileOutput.write(sb2.getBytes());
            openFileOutput.close();
            messagesCached++;
            int i2 = Log.i(TAG, "Cached ".concat(String.valueOf(sb2)));
        } catch (FileNotFoundException e) {
            int e2 = Log.e(TAG, "File not found error writing to cache file");
        } catch (Exception e3) {
            int e4 = Log.e(TAG, "I/O Error writing to cache file");
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.Texting$c */
    class C1044c {
        private /* synthetic */ Texting B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        private final int Sj3UMCQcT7f46E8U4TVavenawPElr174psURarVHJJBTqMXe22hIekct3fzxe7Vm = 5;
        private String T3TEUSUxjrJVucuujQ9pjstrX3UcxHV1L9cOGs7CI5pfz9hjmRfdL7GRyW8ebdVq;
        private int ZBOPOXf20XZKrN6ycbQhEDPC2OsW2QCGfMHApJMYjAVGCpHVQNyA3eb1TXx8tY2I;
        private String ZFwQoaMsdRwNqOTkWG5sFNIKnTcrKyb4dIRsDmVa68cFIA9m1jUiaqOHngvgXrvD;
        private String authToken;
        private CookieManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        boolean isInitialized;

        public C1044c(Texting texting, String str) {
            CookieManager cookieManager;
            StringBuilder sb;
            StringBuilder sb2;
            Throwable th;
            StringBuilder sb3;
            Throwable th2;
            StringBuilder sb4;
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = texting;
            new CookieManager();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = cookieManager;
            int i = Log.i(Texting.TAG, "Creating GV Util");
            this.authToken = str;
            try {
                int i2 = Log.i(Texting.TAG, "getGeneral()");
                this.T3TEUSUxjrJVucuujQ9pjstrX3UcxHV1L9cOGs7CI5pfz9hjmRfdL7GRyW8ebdVq = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE(Texting.GV_URL);
                new StringBuilder("general = ");
                int i3 = Log.i(Texting.TAG, sb2.append(this.T3TEUSUxjrJVucuujQ9pjstrX3UcxHV1L9cOGs7CI5pfz9hjmRfdL7GRyW8ebdVq).toString());
                int i4 = Log.i(Texting.TAG, "setRNRSEE()");
                if (this.T3TEUSUxjrJVucuujQ9pjstrX3UcxHV1L9cOGs7CI5pfz9hjmRfdL7GRyW8ebdVq == null) {
                    int i5 = Log.i(Texting.TAG, "setRNRSEE(): Answer was null!");
                    Throwable th3 = th;
                    new IOException("setRNRSEE(): Answer was null!");
                    throw th3;
                } else if (this.T3TEUSUxjrJVucuujQ9pjstrX3UcxHV1L9cOGs7CI5pfz9hjmRfdL7GRyW8ebdVq.contains("'_rnr_se': '")) {
                    this.ZFwQoaMsdRwNqOTkWG5sFNIKnTcrKyb4dIRsDmVa68cFIA9m1jUiaqOHngvgXrvD = this.T3TEUSUxjrJVucuujQ9pjstrX3UcxHV1L9cOGs7CI5pfz9hjmRfdL7GRyW8ebdVq.split("'_rnr_se': '", 2)[1].split("',", 2)[0];
                    int i6 = Log.i(Texting.TAG, "Successfully Received rnr_se.");
                    this.isInitialized = true;
                } else {
                    new StringBuilder("Answer did not contain rnr_se! ");
                    int i7 = Log.i(Texting.TAG, sb3.append(this.T3TEUSUxjrJVucuujQ9pjstrX3UcxHV1L9cOGs7CI5pfz9hjmRfdL7GRyW8ebdVq).toString());
                    Throwable th4 = th2;
                    new StringBuilder("Answer did not contain rnr_se! ");
                    new IOException(sb4.append(this.T3TEUSUxjrJVucuujQ9pjstrX3UcxHV1L9cOGs7CI5pfz9hjmRfdL7GRyW8ebdVq).toString());
                    throw th4;
                }
            } catch (Exception e) {
                new StringBuilder();
                int e2 = Log.e(Texting.TAG, sb.append(e.getMessage()).toString());
            }
        }

        /* access modifiers changed from: package-private */
        public final String vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R(String str) {
            StringBuilder sb;
            StringBuilder sb2;
            StringBuilder sb3;
            URL url;
            StringBuilder sb4;
            OutputStreamWriter outputStreamWriter;
            BufferedReader bufferedReader;
            Reader reader;
            Throwable th;
            String str2 = str;
            int i = Log.i(Texting.TAG, "sendGvSms()");
            new StringBuilder();
            StringBuilder sb5 = sb;
            try {
                new StringBuilder();
                String sb6 = sb3.append(str2).append("&").append(URLEncoder.encode("_rnr_se", "UTF-8")).append("=").append(URLEncoder.encode(this.ZFwQoaMsdRwNqOTkWG5sFNIKnTcrKyb4dIRsDmVa68cFIA9m1jUiaqOHngvgXrvD, "UTF-8")).toString();
                int i2 = Log.i(Texting.TAG, "smsData = ".concat(String.valueOf(sb6)));
                new URL(Texting.GV_SMS_SEND_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                HttpURLConnection httpURLConnection2 = httpURLConnection;
                new StringBuilder("GoogleLogin auth=");
                httpURLConnection.setRequestProperty(AUTH.WWW_AUTH_RESP, sb4.append(this.authToken).toString());
                httpURLConnection2.setRequestProperty("User-agent", Texting.USER_AGENT);
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(httpURLConnection2);
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setConnectTimeout(Texting.SERVER_TIMEOUT_MS);
                int i3 = Log.i(Texting.TAG, "sms request = ".concat(String.valueOf(httpURLConnection2)));
                new OutputStreamWriter(httpURLConnection2.getOutputStream());
                OutputStreamWriter outputStreamWriter2 = outputStreamWriter;
                OutputStreamWriter outputStreamWriter3 = outputStreamWriter2;
                outputStreamWriter2.write(sb6);
                outputStreamWriter3.flush();
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(httpURLConnection2);
                new InputStreamReader(httpURLConnection2.getInputStream());
                new BufferedReader(reader);
                BufferedReader bufferedReader2 = bufferedReader;
                while (true) {
                    String readLine = bufferedReader2.readLine();
                    String str3 = readLine;
                    if (readLine == null) {
                        break;
                    }
                    StringBuilder append = sb5.append(str3);
                    StringBuilder append2 = sb5.append("\n");
                }
                int i4 = Log.i(Texting.TAG, "sendGvSms:  Sent SMS, response = ".concat(String.valueOf(sb5)));
                outputStreamWriter3.close();
                bufferedReader2.close();
                if (sb5.length() != 0) {
                    return sb5.toString();
                }
                Throwable th2 = th;
                new IOException("No Response Data Received.");
                throw th2;
            } catch (Exception e) {
                Exception exc = e;
                new StringBuilder("IO Error on Send ");
                int i5 = Log.i(Texting.TAG, sb2.append(exc.getMessage()).toString(), exc);
                return "IO Error Message not sent";
            }
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(HttpURLConnection httpURLConnection) {
            HttpURLConnection httpURLConnection2 = httpURLConnection;
            if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCookieStore().getCookies().size() > 0) {
                httpURLConnection2.setRequestProperty(C1496SM.COOKIE, TextUtils.join(";", this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCookieStore().getCookies()));
            }
        }

        private void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(HttpURLConnection httpURLConnection) {
            List list = (List) httpURLConnection.getHeaderFields().get(C1496SM.SET_COOKIE);
            List<String> list2 = list;
            if (list != null) {
                for (String parse : list2) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCookieStore().add((URI) null, HttpCookie.parse(parse).get(0));
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:32:0x01f7, code lost:
            throw r7;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.String qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE(java.lang.String r14) throws java.io.IOException {
            /*
                r13 = this;
                r0 = r13
                r1 = r14
            L_0x0002:
                java.net.URL r7 = new java.net.URL
                r12 = r7
                r7 = r12
                r8 = r12
                r9 = r1
                r8.<init>(r9)
                java.net.URLConnection r7 = r7.openConnection()
                java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7
                r2 = r7
                r7 = 0
                r3 = r7
                r7 = r2
                java.lang.String r8 = "Authorization"
                java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00cb }
                r12 = r9
                r9 = r12
                r10 = r12
                java.lang.String r11 = "GoogleLogin auth="
                r10.<init>(r11)     // Catch:{ Exception -> 0x00cb }
                r10 = r0
                java.lang.String r10 = r10.authToken     // Catch:{ Exception -> 0x00cb }
                java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ Exception -> 0x00cb }
                java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x00cb }
                r7.setRequestProperty(r8, r9)     // Catch:{ Exception -> 0x00cb }
                r7 = r2
                java.lang.String r8 = "User-agent"
                java.lang.String r9 = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.A.B.C Safari/525.13"
                r7.setRequestProperty(r8, r9)     // Catch:{ Exception -> 0x00cb }
                r7 = r2
                r8 = 0
                r7.setInstanceFollowRedirects(r8)     // Catch:{ Exception -> 0x00cb }
                r7 = r0
                r8 = r2
                r7.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(r8)     // Catch:{ Exception -> 0x00cb }
                r7 = r2
                r7.connect()     // Catch:{ Exception -> 0x00cb }
                r7 = r2
                int r7 = r7.getResponseCode()     // Catch:{ Exception -> 0x00cb }
                r3 = r7
                java.lang.String r7 = "Texting Component"
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00cb }
                r12 = r8
                r8 = r12
                r9 = r12
                r9.<init>()     // Catch:{ Exception -> 0x00cb }
                r9 = r1
                java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x00cb }
                java.lang.String r9 = " - "
                java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x00cb }
                r9 = r2
                java.lang.String r9 = r9.getResponseMessage()     // Catch:{ Exception -> 0x00cb }
                java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x00cb }
                java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x00cb }
                int r7 = android.util.Log.i(r7, r8)     // Catch:{ Exception -> 0x00cb }
                r7 = r0
                r8 = r2
                r7.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(r8)
                r7 = r3
                r8 = 200(0xc8, float:2.8E-43)
                if (r7 != r8) goto L_0x0109
                r7 = r2
                java.io.InputStream r7 = r7.getInputStream()
                r4 = r7
            L_0x0087:
                r7 = r0
                r8 = 0
                r7.ZBOPOXf20XZKrN6ycbQhEDPC2OsW2QCGfMHApJMYjAVGCpHVQNyA3eb1TXx8tY2I = r8
                r7 = r4
                if (r7 != 0) goto L_0x0200
                java.io.IOException r7 = new java.io.IOException
                r12 = r7
                r7 = r12
                r8 = r12
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                r12 = r9
                r9 = r12
                r10 = r12
                r10.<init>()
                r10 = r1
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r10 = " : "
                java.lang.StringBuilder r9 = r9.append(r10)
                r10 = r2
                java.lang.String r10 = r10.getResponseMessage()
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r10 = "("
                java.lang.StringBuilder r9 = r9.append(r10)
                r10 = r3
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r10 = ") : InputStream was null : exiting."
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r9 = r9.toString()
                r8.<init>(r9)
                throw r7
            L_0x00cb:
                r7 = move-exception
                java.io.IOException r7 = new java.io.IOException
                r12 = r7
                r7 = r12
                r8 = r12
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                r12 = r9
                r9 = r12
                r10 = r12
                r10.<init>()
                r10 = r1
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r10 = " : "
                java.lang.StringBuilder r9 = r9.append(r10)
                r10 = r2
                java.lang.String r10 = r10.getResponseMessage()
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r10 = "("
                java.lang.StringBuilder r9 = r9.append(r10)
                r10 = r3
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r10 = ") : IO Error."
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r9 = r9.toString()
                r8.<init>(r9)
                throw r7
            L_0x0109:
                r7 = r3
                r8 = 301(0x12d, float:4.22E-43)
                if (r7 == r8) goto L_0x011d
                r7 = r3
                r8 = 302(0x12e, float:4.23E-43)
                if (r7 == r8) goto L_0x011d
                r7 = r3
                r8 = 303(0x12f, float:4.25E-43)
                if (r7 == r8) goto L_0x011d
                r7 = r3
                r8 = 307(0x133, float:4.3E-43)
                if (r7 != r8) goto L_0x01f8
            L_0x011d:
                r7 = r0
                r12 = r7
                r7 = r12
                r8 = r12
                int r8 = r8.ZBOPOXf20XZKrN6ycbQhEDPC2OsW2QCGfMHApJMYjAVGCpHVQNyA3eb1TXx8tY2I
                r9 = 1
                int r8 = r8 + 1
                r7.ZBOPOXf20XZKrN6ycbQhEDPC2OsW2QCGfMHApJMYjAVGCpHVQNyA3eb1TXx8tY2I = r8
                r7 = r0
                int r7 = r7.ZBOPOXf20XZKrN6ycbQhEDPC2OsW2QCGfMHApJMYjAVGCpHVQNyA3eb1TXx8tY2I
                r8 = 5
                if (r7 <= r8) goto L_0x016f
                r7 = r0
                r8 = 0
                r7.ZBOPOXf20XZKrN6ycbQhEDPC2OsW2QCGfMHApJMYjAVGCpHVQNyA3eb1TXx8tY2I = r8
                java.io.IOException r7 = new java.io.IOException
                r12 = r7
                r7 = r12
                r8 = r12
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                r12 = r9
                r9 = r12
                r10 = r12
                r10.<init>()
                r10 = r1
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r10 = " : "
                java.lang.StringBuilder r9 = r9.append(r10)
                r10 = r2
                java.lang.String r10 = r10.getResponseMessage()
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r10 = "("
                java.lang.StringBuilder r9 = r9.append(r10)
                r10 = r3
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r10 = ") : Too many redirects. exiting."
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r9 = r9.toString()
                r8.<init>(r9)
                throw r7
            L_0x016f:
                r7 = r2
                java.lang.String r8 = "Location"
                java.lang.String r7 = r7.getHeaderField(r8)
                r12 = r7
                r7 = r12
                r8 = r12
                r4 = r8
                if (r7 == 0) goto L_0x01bb
                r7 = r4
                java.lang.String r8 = ""
                boolean r7 = r7.equals(r8)
                if (r7 != 0) goto L_0x01bb
                java.io.PrintStream r7 = java.lang.System.out
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                r12 = r8
                r8 = r12
                r9 = r12
                r9.<init>()
                r9 = r1
                java.lang.StringBuilder r8 = r8.append(r9)
                java.lang.String r9 = " - "
                java.lang.StringBuilder r8 = r8.append(r9)
                r9 = r3
                java.lang.StringBuilder r8 = r8.append(r9)
                java.lang.String r9 = " - new URL: "
                java.lang.StringBuilder r8 = r8.append(r9)
                r9 = r4
                java.lang.StringBuilder r8 = r8.append(r9)
                java.lang.String r8 = r8.toString()
                r7.println(r8)
                r7 = r0
                r8 = r4
                r1 = r8
                r0 = r7
                goto L_0x0002
            L_0x01bb:
                java.io.IOException r7 = new java.io.IOException
                r12 = r7
                r7 = r12
                r8 = r12
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                r12 = r9
                r9 = r12
                r10 = r12
                r10.<init>()
                r10 = r1
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r10 = " : "
                java.lang.StringBuilder r9 = r9.append(r10)
                r10 = r2
                java.lang.String r10 = r10.getResponseMessage()
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r10 = "("
                java.lang.StringBuilder r9 = r9.append(r10)
                r10 = r3
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r10 = ") : Received moved answer but no Location. exiting."
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r9 = r9.toString()
                r8.<init>(r9)
                throw r7
            L_0x01f8:
                r7 = r2
                java.io.InputStream r7 = r7.getErrorStream()
                r4 = r7
                goto L_0x0087
            L_0x0200:
                java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0251 }
                r12 = r7
                r7 = r12
                r8 = r12
                java.io.InputStreamReader r9 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0251 }
                r12 = r9
                r9 = r12
                r10 = r12
                r11 = r4
                r10.<init>(r11)     // Catch:{ Exception -> 0x0251 }
                r8.<init>(r9)     // Catch:{ Exception -> 0x0251 }
                r4 = r7
                java.lang.StringBuffer r7 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0251 }
                r12 = r7
                r7 = r12
                r8 = r12
                r8.<init>()     // Catch:{ Exception -> 0x0251 }
                r5 = r7
            L_0x021b:
                r7 = r4
                java.lang.String r7 = r7.readLine()     // Catch:{ Exception -> 0x0251 }
                r12 = r7
                r7 = r12
                r8 = r12
                r6 = r8
                if (r7 == 0) goto L_0x0244
                r7 = r5
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0251 }
                r12 = r8
                r8 = r12
                r9 = r12
                r9.<init>()     // Catch:{ Exception -> 0x0251 }
                r9 = r6
                java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x0251 }
                java.lang.String r9 = "\n\r"
                java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x0251 }
                java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0251 }
                java.lang.StringBuffer r7 = r7.append(r8)     // Catch:{ Exception -> 0x0251 }
                goto L_0x021b
            L_0x0244:
                r7 = r4
                r7.close()     // Catch:{ Exception -> 0x0251 }
                r7 = r5
                java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0251 }
                r4 = r7
                r7 = r4
                r0 = r7
                return r0
            L_0x0251:
                r7 = move-exception
                r4 = r7
                java.io.IOException r7 = new java.io.IOException
                r12 = r7
                r7 = r12
                r8 = r12
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                r12 = r9
                r9 = r12
                r10 = r12
                r10.<init>()
                r10 = r1
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r10 = " - "
                java.lang.StringBuilder r9 = r9.append(r10)
                r10 = r2
                java.lang.String r10 = r10.getResponseMessage()
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r10 = "("
                java.lang.StringBuilder r9 = r9.append(r10)
                r10 = r3
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r10 = ") - "
                java.lang.StringBuilder r9 = r9.append(r10)
                r10 = r4
                java.lang.String r10 = r10.getMessage()
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r9 = r9.toString()
                r8.<init>(r9)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Texting.C1044c.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE(java.lang.String):java.lang.String");
        }
    }

    /* access modifiers changed from: private */
    public synchronized void handleSentMessage(Context context, BroadcastReceiver broadcastReceiver, int i, String str) {
        Context context2 = context;
        BroadcastReceiver broadcastReceiver2 = broadcastReceiver;
        int i2 = i;
        String str2 = str;
        synchronized (this) {
            switch (i2) {
                case -1:
                    int i3 = Log.i(TAG, "Received OK, msg:".concat(String.valueOf(str2)));
                    Toast.makeText(activity, "Message sent", 0).show();
                    break;
                case 1:
                    int e = Log.e(TAG, "Received generic failure, msg:".concat(String.valueOf(str2)));
                    Toast.makeText(activity, "Generic failure: message not sent", 0).show();
                    break;
                case 2:
                    int e2 = Log.e(TAG, "Received radio off error, msg:".concat(String.valueOf(str2)));
                    Toast.makeText(activity, "Could not send SMS message: radio off.", 1).show();
                    break;
                case 3:
                    int e3 = Log.e(TAG, "Received null PDU error, msg:".concat(String.valueOf(str2)));
                    Toast.makeText(activity, "Received null PDU error. Message not sent.", 0).show();
                    break;
                case 4:
                    int e4 = Log.e(TAG, "Received no service error, msg:".concat(String.valueOf(str2)));
                    Toast.makeText(activity, "No Sms service available. Message not sent.", 0).show();
                    break;
            }
        }
    }

    /* access modifiers changed from: private */
    public void sendViaSms(String str) {
        ArrayList arrayList;
        BroadcastReceiver broadcastReceiver;
        IntentFilter intentFilter;
        Intent intent;
        Runnable runnable;
        String str2 = str;
        int i = Log.i(TAG, "Sending via built-in Sms");
        if (!this.havePermission) {
            Form $form = this.container.$form();
            final Form form = $form;
            final String str3 = str2;
            new Runnable(this) {

                /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
                private /* synthetic */ Texting f532B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

                {
                    this.f532B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r8;
                }

                public final void run() {
                    PermissionResultHandler permissionResultHandler;
                    new PermissionResultHandler(this) {
                        private /* synthetic */ C10371 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void HandlePermissionResponse(String str, boolean z) {
                            String str2 = str;
                            if (z) {
                                boolean access$002 = Texting.access$002(this, true);
                                this.sendViaSms(str3);
                                return;
                            }
                            form.dispatchPermissionDeniedEvent((Component) this, str3, "android.permission.SEND_SMS");
                        }
                    };
                    form.askPermission("android.permission.SEND_SMS", permissionResultHandler);
                }
            };
            $form.runOnUiThread(runnable);
            return;
        }
        ArrayList<String> divideMessage = this.smsManager.divideMessage(this.message);
        ArrayList<String> arrayList2 = divideMessage;
        int size = divideMessage.size();
        new ArrayList();
        ArrayList arrayList3 = arrayList;
        for (int i2 = 0; i2 < size; i2++) {
            new Intent(SENT);
            boolean add = arrayList3.add(PendingIntent.getBroadcast(activity, 0, intent, 0));
        }
        new BroadcastReceiver(this) {
            private /* synthetic */ Texting B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

            {
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r5;
            }

            public final synchronized void onReceive(Context context, Intent intent) {
                StringBuilder sb;
                StringBuilder sb2;
                Context context2 = context;
                Intent intent2 = intent;
                synchronized (this) {
                    try {
                        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.handleSentMessage(context2, (BroadcastReceiver) null, getResultCode(), this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.message);
                        Texting.activity.unregisterReceiver(this);
                    } catch (Exception e) {
                        Exception exc = e;
                        new StringBuilder("Error in onReceive for msgId ");
                        int e2 = Log.e("BroadcastReceiver", sb.append(intent2.getAction()).toString());
                        new StringBuilder();
                        int e3 = Log.e("BroadcastReceiver", sb2.append(exc.getMessage()).toString());
                    }
                }
                return;
            }
        };
        new IntentFilter(SENT);
        Intent registerReceiver = activity.registerReceiver(broadcastReceiver, intentFilter);
        this.smsManager.sendMultipartTextMessage(this.phoneNumber, (String) null, arrayList2, arrayList3, (ArrayList) null);
    }

    private void requestReceiveSmsPermission(String str) {
        Runnable runnable;
        final String str2 = str;
        new Runnable(this) {
            final /* synthetic */ Texting B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

            {
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r6;
            }

            public final void run() {
                PermissionResultHandler permissionResultHandler;
                new PermissionResultHandler(this) {
                    private /* synthetic */ C10403 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                    }

                    public final void HandlePermissionResponse(String str, boolean z) {
                        String str2 = str;
                        if (z) {
                            boolean access$502 = Texting.access$502(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, true);
                        } else {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, str2, "android.permission.RECEIVE_SMS");
                        }
                    }
                };
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.form.askPermission("android.permission.RECEIVE_SMS", permissionResultHandler);
            }
        };
        this.form.runOnUiThread(runnable);
    }

    /* renamed from: com.google.appinventor.components.runtime.Texting$a */
    class C1042a extends AsyncTask<Void, Void, String> {
        private /* synthetic */ Texting B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

        C1042a(Texting texting) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = texting;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            int i = Log.i(Texting.TAG, "authToken = ".concat(String.valueOf(str)));
            String access$602 = Texting.access$602(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, str);
            Toast.makeText(Texting.activity, "Finished authentication", 0).show();
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.processPendingQueue();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            OAuth2Helper oAuth2Helper;
            Object[] objArr2 = objArr;
            int i = Log.i(Texting.TAG, "Authenticating");
            new OAuth2Helper();
            return oAuth2Helper.getRefreshedAuthToken(Texting.activity, Texting.GV_SERVICE);
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.Texting$b */
    class C1043b extends AsyncTask<String, Void, String> {
        private /* synthetic */ Texting B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

        C1043b(Texting texting) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = texting;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            StringBuilder sb;
            JSONObject jSONObject;
            String str = (String) obj;
            String str2 = str;
            super.onPostExecute(str);
            boolean z = false;
            int i = 0;
            try {
                new JSONObject(str2);
                JSONObject jSONObject2 = jSONObject;
                z = jSONObject2.getBoolean("ok");
                i = jSONObject2.getJSONObject("data").getInt("code");
            } catch (JSONException e) {
                new StringBuilder();
                int e2 = Log.e(Texting.TAG, sb.append(e.getMessage()).toString());
            }
            if (z) {
                Toast.makeText(Texting.activity, "Message sent", 0).show();
            } else if (i == 58) {
                Toast.makeText(Texting.activity, "Errcode 58: SMS limit reached", 0).show();
            } else if (str2.contains("IO Error")) {
                Toast.makeText(Texting.activity, str2, 0).show();
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        public String doInBackground(String... strArr) {
            StringBuilder sb;
            StringBuilder sb2;
            StringBuilder sb3;
            C1044c cVar;
            String[] strArr2 = strArr;
            String str = strArr2[0];
            String str2 = strArr2[1];
            String str3 = "";
            new StringBuilder("Async sending phoneNumber = ");
            int i = Log.i(Texting.TAG, sb.append(str).append(" message = ").append(str2).toString());
            try {
                new StringBuilder();
                String sb4 = sb3.append(URLEncoder.encode("phoneNumber", "UTF-8")).append("=").append(URLEncoder.encode(str, "UTF-8")).append("&").append(URLEncoder.encode(PropertyTypeConstants.PROPERTY_TYPE_TEXT, "UTF-8")).append("=").append(URLEncoder.encode(str2, "UTF-8")).toString();
                if (this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.gvHelper == null) {
                    new C1044c(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.authToken);
                    C1044c access$802 = Texting.access$802(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, cVar);
                }
                if (!this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.gvHelper.isInitialized) {
                    return "IO Error: unable to create GvHelper";
                }
                str3 = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.gvHelper.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R(sb4);
                int i2 = Log.i(Texting.TAG, "Sent SMS, response = ".concat(String.valueOf(str3)));
                return str3;
            } catch (Exception e) {
                new StringBuilder();
                int e2 = Log.e(Texting.TAG, sb2.append(e.getMessage()).toString());
            }
        }
    }

    public void onStop() {
        SharedPreferences.Editor edit = activity.getSharedPreferences(PREF_FILE, 0).edit();
        SharedPreferences.Editor editor = edit;
        SharedPreferences.Editor putInt = edit.putInt(PREF_RCVENABLED, receivingEnabled);
        SharedPreferences.Editor putBoolean = editor.putBoolean(PREF_GVENABLED, this.googleVoiceEnabled);
        boolean commit = editor.commit();
    }
}
