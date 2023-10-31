package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import com.firebase.client.ChildEventListener;
import com.firebase.client.Config;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;
import com.firebase.client.ValueEventListener;
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
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;

@DesignerComponent(category = ComponentCategory.GOOGLE, description = "Non-visible component that communicates with Firebase to store and retrieve information.", designerHelpDescription = "Non-visible component that communicates with a Firebase to store and retrieve information.", helpUrl = "https://docs.kodular.io/components/google/firebase-database/", iconName = "images/firebaseDB.png", nonVisible = true, version = 4)
@UsesLibraries(libraries = "firebase.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class FirebaseDB extends AndroidNonvisibleComponent implements Component {
    private static final String LOG_TAG = "Firebase";
    private static boolean isInitialized = false;
    private static boolean persist = false;
    private final Activity activity;
    /* access modifiers changed from: private */
    public Handler androidUIHandler;
    private Firebase.AuthStateListener authListener;
    private ChildEventListener childListener;
    private String defaultURL = null;
    private String developerBucket;
    /* access modifiers changed from: private */
    public String firebaseToken;
    private String firebaseURL = null;
    /* access modifiers changed from: private */
    public Firebase myFirebase;
    private String projectBucket;
    private boolean useDefault = true;

    /* renamed from: com.google.appinventor.components.runtime.FirebaseDB$a */
    static class C0685a {
        String ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e;
        Object vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;

        private C0685a() {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0685a(byte b) {
            this();
            byte b2 = b;
        }
    }

    static abstract class Transactional {
        final Object arg1;
        final Object arg2;
        final C0685a retv;

        /* access modifiers changed from: package-private */
        public abstract Transaction.Result run(MutableData mutableData);

        Transactional(Object obj, Object obj2, C0685a aVar) {
            this.arg1 = obj;
            this.arg2 = obj2;
            this.retv = aVar;
        }

        /* access modifiers changed from: package-private */
        public C0685a getResult() {
            return this.retv;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FirebaseDB(com.google.appinventor.components.runtime.ComponentContainer r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.firebaseURL = r3
            r2 = r0
            r3 = 0
            r2.defaultURL = r3
            r2 = r0
            r3 = 1
            r2.useDefault = r3
            r2 = r0
            android.os.Handler r3 = new android.os.Handler
            r6 = r3
            r3 = r6
            r4 = r6
            r4.<init>()
            r2.androidUIHandler = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            android.app.Activity r2 = r2.activity
            com.firebase.client.Firebase.setAndroidContext(r2)
            r2 = r0
            java.lang.String r3 = ""
            r2.developerBucket = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.projectBucket = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.firebaseToken = r3
            r2 = r0
            com.google.appinventor.components.runtime.FirebaseDB$1 r3 = new com.google.appinventor.components.runtime.FirebaseDB$1
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            r4.<init>(r5)
            r2.childListener = r3
            r2 = r0
            com.google.appinventor.components.runtime.FirebaseDB$2 r3 = new com.google.appinventor.components.runtime.FirebaseDB$2
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            r4.<init>(r5)
            r2.authListener = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.FirebaseDB.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public void Initialize() {
        int i = Log.i(LOG_TAG, "Initalize called!");
        isInitialized = true;
        resetListener();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets the URL for this FirebaseDB.")
    public String FirebaseURL() {
        if (this.useDefault) {
            return "DEFAULT";
        }
        return this.firebaseURL;
    }

    @DesignerProperty(defaultValue = "DEFAULT", editorType = "FirbaseURL")
    @SimpleProperty(description = "Sets the URL for this FirebaseDB.")
    public void FirebaseURL(String str) {
        StringBuilder sb;
        String str2 = str;
        if (!str2.equals("DEFAULT")) {
            this.useDefault = false;
            new StringBuilder();
            String sb2 = sb.append(str2).append(str2.endsWith("/") ? "" : "/").toString();
            if (!this.firebaseURL.equals(sb2)) {
                this.firebaseURL = sb2;
                this.useDefault = false;
                resetListener();
                return;
            }
            return;
        }
        if (!this.useDefault) {
            this.useDefault = true;
            if (this.defaultURL == null) {
                int d = Log.d(LOG_TAG, "FirebaseURL called before DefaultURL (should not happen!)");
                return;
            }
        }
        this.firebaseURL = this.defaultURL;
        resetListener();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String DeveloperBucket() {
        return this.developerBucket;
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty
    public void DeveloperBucket(String str) {
        this.developerBucket = str;
        resetListener();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets the ProjectBucket for this FirebaseDB.")
    public String ProjectBucket() {
        return this.projectBucket;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Sets the ProjectBucket for this FirebaseDB.")
    public void ProjectBucket(String str) {
        String str2 = str;
        if (!this.projectBucket.equals(str2)) {
            this.projectBucket = str2;
            resetListener();
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the FirebaseToken from this FirebaseDB.")
    public String FirebaseToken() {
        return this.firebaseToken;
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(description = "Sets the FirebaseToken for this FirebaseDB.")
    public void FirebaseToken(String str) {
        this.firebaseToken = str;
        resetListener();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If true, variables will retain their values when off-line and the App exits. Values will be uploaded to Firebase the next time the App is run while connected to the network. This is useful for applications which will gather data while not connected to the network. Note: AppendValue and RemoveFirst will not work correctly when off-line, they require a network connection.<br/><br/> <i>Note</i>: If you set Persist on any Firebase component, on any screen, it makes all Firebase components on all screens persistent. This is a limitation of the low level Firebase library. Also be aware that if you want to set persist to true, you should do so before connecting the Companion for incremental development.", userVisible = false)
    public void Persist(boolean z) {
        Throwable th;
        boolean z2 = z;
        int i = Log.i(LOG_TAG, "Persist Called: Value = ".concat(String.valueOf(z2)));
        if (persist == z2) {
            return;
        }
        if (isInitialized) {
            Throwable th2 = th;
            new RuntimeException("You cannot change the Persist value of Firebase after Application Initialization, this includes the Companion");
            throw th2;
        }
        Config defaultConfig = Firebase.getDefaultConfig();
        defaultConfig.setPersistenceEnabled(z2);
        Firebase.setDefaultConfig(defaultConfig);
        persist = z2;
        resetListener();
    }

    private void resetListener() {
        if (isInitialized) {
            if (this.myFirebase != null) {
                this.myFirebase.removeEventListener(this.childListener);
                this.myFirebase.removeAuthStateListener(this.authListener);
            }
            this.myFirebase = null;
            connectFirebase();
        }
    }

    @SimpleFunction(description = "Remove the tag from Firebase")
    public void ClearTag(String str) {
        String str2 = str;
        if (this.myFirebase != null) {
            this.myFirebase.child(str2).removeValue();
        }
    }

    @SimpleFunction
    public void StoreValue(String str, Object obj) {
        String str2 = str;
        String str3 = obj;
        if (str3 != null) {
            try {
                str3 = JsonUtil.getJsonRepresentation(str3);
            } catch (JSONException e) {
                int e2 = Log.e(LOG_TAG, String.valueOf(e));
                str3 = "Value failed to convert from JSON. JSON Creation Error.";
            }
        }
        if (this.myFirebase != null) {
            this.myFirebase.child(str2).setValue(str3);
        }
    }

    @SimpleFunction
    public void GetValue(String str, Object obj) {
        ValueEventListener valueEventListener;
        String str2 = str;
        Object obj2 = obj;
        if (this.myFirebase != null) {
            final Object obj3 = obj2;
            final String str3 = str2;
            new ValueEventListener(this) {
                final /* synthetic */ FirebaseDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                }

                public final void onDataChange(DataSnapshot dataSnapshot) {
                    AtomicReference atomicReference;
                    StringBuilder sb;
                    Runnable runnable;
                    DataSnapshot dataSnapshot2 = dataSnapshot;
                    new AtomicReference();
                    AtomicReference atomicReference2 = atomicReference;
                    try {
                        if (dataSnapshot2.exists()) {
                            atomicReference2.set(dataSnapshot2.getValue());
                        } else {
                            atomicReference2.set(JsonUtil.getJsonRepresentation(obj3));
                        }
                        final AtomicReference atomicReference3 = atomicReference2;
                        new Runnable(this) {
                            private /* synthetic */ C06743 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                            {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                            }

                            public final void run() {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotValue(str3, atomicReference3.get());
                            }
                        };
                        boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable);
                    } catch (JSONException e) {
                        JSONException jSONException = e;
                        int e2 = Log.e(FirebaseDB.LOG_TAG, String.valueOf(jSONException));
                        FirebaseDB firebaseDB = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                        new StringBuilder();
                        firebaseDB.FirebaseError(sb.append(jSONException.getMessage()).toString());
                    }
                }

                public final void onCancelled(FirebaseError firebaseError) {
                    Runnable runnable;
                    final FirebaseError firebaseError2 = firebaseError;
                    new Runnable(this) {

                        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                        private /* synthetic */ C06743 f392hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.f392hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                        }

                        public final void run() {
                            this.f392hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.FirebaseError(firebaseError2.getMessage());
                        }
                    };
                    boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable);
                }
            };
            this.myFirebase.child(str2).addListenerForSingleValueEvent(valueEventListener);
            return;
        }
        FirebaseError("Can not call 'Get Value' if the firebase object is NULL.");
    }

    @SimpleEvent
    public void GotValue(String str, Object obj) {
        StringBuilder sb;
        String str2 = str;
        Object obj2 = obj;
        if (obj2 != null) {
            try {
                if (obj2 instanceof String) {
                    obj2 = JsonUtil.getObjectFromJson((String) obj2, true);
                }
            } catch (JSONException e) {
                int e2 = Log.e(LOG_TAG, String.valueOf(e));
                obj2 = "Value failed to convert from JSON. JSON Retrieval Error.";
            }
        }
        try {
            Object[] objArr = new Object[2];
            objArr[0] = str2;
            Object[] objArr2 = objArr;
            objArr2[1] = obj2;
            boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotValue", objArr2);
        } catch (Exception e3) {
            Exception exc = e3;
            int e4 = Log.e(LOG_TAG, String.valueOf(exc));
            new StringBuilder();
            FirebaseError(sb.append(exc.getMessage()).toString());
        }
    }

    @SimpleEvent
    public void DataChanged(String str, Object obj) {
        String str2 = str;
        Object obj2 = obj;
        if (obj2 != null) {
            try {
                if (obj2 instanceof String) {
                    obj2 = JsonUtil.getObjectFromJson((String) obj2, true);
                }
            } catch (JSONException e) {
                int e2 = Log.e(LOG_TAG, String.valueOf(e));
                obj2 = "Value failed to convert from JSON. JSON Retrieval Error.";
            }
        }
        Object[] objArr = new Object[2];
        objArr[0] = str2;
        Object[] objArr2 = objArr;
        objArr2[1] = obj2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "DataChanged", objArr2);
    }

    @SimpleEvent
    public void FirebaseError(String str) {
        String str2 = str;
        int e = Log.e(LOG_TAG, str2);
        if (!EventDispatcher.dispatchEvent(this, "FirebaseError", str2)) {
            try {
                Notifier.oneButtonAlert(this.form, str2, "FirebaseError", "Continue");
            } catch (Exception e2) {
                int e3 = Log.e(LOG_TAG, String.valueOf(e2));
            }
        }
    }

    private void connectFirebase() {
        StringBuilder sb;
        Firebase firebase;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        Firebase firebase2;
        StringBuilder sb6;
        if (this.useDefault) {
            try {
                new StringBuilder();
                new Firebase(sb6.append(this.firebaseURL).append("developers/").append(this.developerBucket).append(this.projectBucket).toString());
                this.myFirebase = firebase2;
            } catch (Exception e) {
                new StringBuilder();
                FirebaseError(sb5.append(e.getMessage()).toString());
                return;
            }
        } else {
            try {
                new StringBuilder();
                new Firebase(sb2.append(this.firebaseURL).append(this.projectBucket).toString());
                this.myFirebase = firebase;
            } catch (Exception e2) {
                new StringBuilder();
                FirebaseError(sb.append(e2.getMessage()).toString());
                return;
            }
        }
        try {
            if (this.myFirebase != null) {
                ChildEventListener addChildEventListener = this.myFirebase.addChildEventListener(this.childListener);
            }
            try {
                if (this.myFirebase != null) {
                    Firebase.AuthStateListener addAuthStateListener = this.myFirebase.addAuthStateListener(this.authListener);
                }
            } catch (Exception e3) {
                new StringBuilder();
                FirebaseError(sb4.append(e3.getMessage()).toString());
            }
        } catch (Exception e4) {
            new StringBuilder();
            FirebaseError(sb3.append(e4.getMessage()).toString());
        }
    }

    @SimpleFunction(description = "If you are having difficulty with the Companion and you are switching between different Firebase accounts, you may need to use this function to clear internal Firebase caches. You can just use the \"Do It\" function on this block in the blocks editor. Note: You should not normally need to use this block as part of an application.")
    public void Unauthenticate() {
        if (this.myFirebase == null) {
            connectFirebase();
        }
        this.myFirebase.unauth();
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public void DefaultURL(String str) {
        this.defaultURL = str;
        if (this.useDefault) {
            this.firebaseURL = this.defaultURL;
            resetListener();
        }
    }

    @SimpleFunction(description = "Return the first element of a list and atomically remove it. If two devices use this function simultaneously, one will get the first element and the the other will get the second element, or an error if there is no available element. When the element is available, the \"FirstRemoved\" event will be triggered.")
    public void RemoveFirst(String str) {
        C0685a aVar;
        Transactional transactional;
        Runnable runnable;
        new C0685a((byte) 0);
        C0685a aVar2 = aVar;
        Firebase child = this.myFirebase.child(str);
        Transactional transactional2 = transactional;
        C0685a aVar3 = aVar2;
        final C0685a aVar4 = aVar3;
        new Transactional(this, aVar3) {

            /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
            private /* synthetic */ FirebaseDB f393hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.f393hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r9;
            }

            /* access modifiers changed from: package-private */
            public final Transaction.Result run(MutableData mutableData) {
                MutableData mutableData2 = mutableData;
                Object value = mutableData2.getValue();
                Object obj = value;
                if (value == null) {
                    aVar4.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e = "Previous value was empty.";
                    return Transaction.abort();
                }
                try {
                    if (obj instanceof String) {
                        Object objectFromJson = JsonUtil.getObjectFromJson((String) obj, true);
                        if (!(objectFromJson instanceof List)) {
                            aVar4.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e = "You can only remove elements from a list.";
                            return Transaction.abort();
                        } else if (((List) objectFromJson).isEmpty()) {
                            aVar4.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e = "The list was empty";
                            return Transaction.abort();
                        } else {
                            aVar4.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = ((List) objectFromJson).remove(0);
                            try {
                                mutableData2.setValue(JsonUtil.getJsonRepresentation(YailList.makeList((List) objectFromJson)));
                                return Transaction.success(mutableData2);
                            } catch (JSONException e) {
                                aVar4.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e = "Could not convert value to JSON.";
                                return Transaction.abort();
                            }
                        }
                    } else {
                        aVar4.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e = "Invalid JSON object in database (shouldn't happen!)";
                        return Transaction.abort();
                    }
                } catch (JSONException e2) {
                    aVar4.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e = "Invalid JSON object in database (shouldn't happen!)";
                    return Transaction.abort();
                }
            }
        };
        final C0685a aVar5 = aVar2;
        new Runnable(this) {

            /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
            private /* synthetic */ FirebaseDB f394hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.f394hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                this.f394hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.FirstRemoved(aVar5.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R);
            }
        };
        firebaseTransaction(transactional2, child, runnable);
    }

    @SimpleFunction(description = "Get the list of tags for this application. When complete a \"TagList\" event will be triggered with the list of known tags.")
    public void GetTagList() {
        ValueEventListener valueEventListener;
        new ValueEventListener(this) {
            final /* synthetic */ FirebaseDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onDataChange(DataSnapshot dataSnapshot) {
                List list;
                Runnable runnable;
                Object value = dataSnapshot.getValue();
                Object obj = value;
                if (value instanceof HashMap) {
                    new ArrayList(((HashMap) obj).keySet());
                    final List list2 = list;
                    new Runnable(this) {
                        private /* synthetic */ C06796 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                        }

                        public final void run() {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.TagList(list2);
                        }
                    };
                    boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable);
                }
            }

            public final void onCancelled(FirebaseError firebaseError) {
            }
        };
        this.myFirebase.child("").addListenerForSingleValueEvent(valueEventListener);
    }

    @SimpleFunction(description = "Take the database online")
    public void GoOnline() {
        if (this.myFirebase != null) {
            Firebase.goOnline();
        } else {
            connectFirebase();
        }
    }

    @SimpleFunction(description = "Take the database offline")
    public void GoOffline() {
        if (this.myFirebase != null) {
            Firebase.goOffline();
        }
    }

    @SimpleEvent(description = "Event triggered when we have received the list of known tags. Used with the \"GetTagList\" Function.")
    public void TagList(List list) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TagList", list);
    }

    @SimpleEvent(description = "Event triggered by the \"RemoveFirst\" function. The argument \"value\" is the object that was the first in the list, and which is now removed.")
    public void FirstRemoved(Object obj) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "FirstRemoved", obj);
    }

    @SimpleFunction(description = "Append a value to the end of a list atomically. If two devices use this function simultaneously, both will be appended and no data lost.")
    public void AppendValue(String str, Object obj) {
        C0685a aVar;
        Transactional transactional;
        new C0685a((byte) 0);
        Firebase child = this.myFirebase.child(str);
        Transactional transactional2 = transactional;
        C0685a aVar2 = aVar;
        final C0685a aVar3 = aVar2;
        final Object obj2 = obj;
        new Transactional(this, aVar2) {

            /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
            private /* synthetic */ FirebaseDB f395hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.f395hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r10;
            }

            /* access modifiers changed from: package-private */
            public final Transaction.Result run(MutableData mutableData) {
                MutableData mutableData2 = mutableData;
                Object value = mutableData2.getValue();
                Object obj = value;
                if (value == null) {
                    aVar3.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e = "Previous value was empty.";
                    return Transaction.abort();
                }
                try {
                    if (obj instanceof String) {
                        Object objectFromJson = JsonUtil.getObjectFromJson((String) obj, true);
                        if (objectFromJson instanceof List) {
                            boolean add = ((List) objectFromJson).add(obj2);
                            try {
                                mutableData2.setValue(JsonUtil.getJsonRepresentation((List) objectFromJson));
                                return Transaction.success(mutableData2);
                            } catch (JSONException e) {
                                aVar3.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e = "Could not convert value to JSON.";
                                return Transaction.abort();
                            }
                        } else {
                            aVar3.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e = "You can only append to a list.";
                            return Transaction.abort();
                        }
                    } else {
                        aVar3.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e = "Invalid JSON object in database (shouldn't happen!)";
                        return Transaction.abort();
                    }
                } catch (JSONException e2) {
                    aVar3.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e = "Invalid JSON object in database (shouldn't happen!)";
                    return Transaction.abort();
                }
            }
        };
        firebaseTransaction(transactional2, child, (Runnable) null);
    }

    private void firebaseTransaction(Transactional transactional, Firebase firebase, Runnable runnable) {
        Transaction.Handler handler;
        Transactional transactional2 = transactional;
        final Transactional transactional3 = transactional2;
        final C0685a result = transactional2.getResult();
        final Runnable runnable2 = runnable;
        new Transaction.Handler(this) {

            /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
            final /* synthetic */ FirebaseDB f397hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.f397hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
            }

            public final Transaction.Result doTransaction(MutableData mutableData) {
                return transactional3.run(mutableData);
            }

            public final void onComplete(FirebaseError firebaseError, boolean z, DataSnapshot dataSnapshot) {
                Runnable runnable;
                Runnable runnable2;
                FirebaseError firebaseError2 = firebaseError;
                boolean z2 = z;
                DataSnapshot dataSnapshot2 = dataSnapshot;
                if (firebaseError2 != null) {
                    final FirebaseError firebaseError3 = firebaseError2;
                    new Runnable(this) {
                        private /* synthetic */ C06828 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                        }

                        public final void run() {
                            StringBuilder sb;
                            StringBuilder sb2;
                            new StringBuilder("AppendValue(onComplete): firebase: ");
                            int i = Log.i(FirebaseDB.LOG_TAG, sb.append(firebaseError3.getMessage()).toString());
                            new StringBuilder("AppendValue(onComplete): result.err: ");
                            int i2 = Log.i(FirebaseDB.LOG_TAG, sb2.append(result.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e).toString());
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.f397hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.FirebaseError(firebaseError3.getMessage());
                        }
                    };
                    boolean post = this.f397hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable2);
                } else if (!z2) {
                    new Runnable(this) {
                        private /* synthetic */ C06828 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void run() {
                            StringBuilder sb;
                            new StringBuilder("AppendValue(!committed): result.err: ");
                            int i = Log.i(FirebaseDB.LOG_TAG, sb.append(result.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e).toString());
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.f397hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.FirebaseError(result.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e);
                        }
                    };
                    boolean post2 = this.f397hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable);
                } else if (runnable2 != null) {
                    boolean post3 = this.f397hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable2);
                }
            }
        };
        firebase.runTransaction(handler);
    }
}
