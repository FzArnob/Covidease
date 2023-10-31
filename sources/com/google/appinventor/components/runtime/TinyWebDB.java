package com.google.appinventor.components.runtime;

import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.AsyncCallbackPair;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.WebServiceUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

@SimpleObject
@DesignerComponent(category = ComponentCategory.STORAGE, description = "Non-visible component that communicates with a Web service to store and retrieve information.", iconName = "images/tinyWebDB.png", nonVisible = true, version = 2)
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class TinyWebDB extends AndroidNonvisibleComponent implements Component {
    private String CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV = "http://tinywebdb.builder.makeroid.io/";
    /* access modifiers changed from: private */
    public Handler androidUIHandler;

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(TinyWebDB tinyWebDB, String str, Object obj) {
        AsyncCallbackPair asyncCallbackPair;
        Throwable th;
        NameValuePair nameValuePair;
        NameValuePair nameValuePair2;
        String str2 = str;
        Object obj2 = obj;
        TinyWebDB tinyWebDB2 = tinyWebDB;
        new AsyncCallbackPair<String>(tinyWebDB2) {
            final /* synthetic */ TinyWebDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final /* synthetic */ void onSuccess(Object obj) {
                Runnable runnable;
                Object obj2 = obj;
                new Runnable(this) {
                    private /* synthetic */ C10482 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                    }

                    public final void run() {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ValueStored();
                    }
                };
                boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable);
            }

            public final void onFailure(String str) {
                Runnable runnable;
                final String str2 = str;
                new Runnable(this) {
                    private /* synthetic */ C10482 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                    }

                    public final void run() {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.WebServiceError(str2);
                    }
                };
                boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable);
            }
        };
        AsyncCallbackPair asyncCallbackPair2 = asyncCallbackPair;
        try {
            WebServiceUtil instance = WebServiceUtil.getInstance();
            String str3 = tinyWebDB2.CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV;
            NameValuePair[] nameValuePairArr = new NameValuePair[2];
            new BasicNameValuePair("tag", str2);
            nameValuePairArr[0] = nameValuePair;
            NameValuePair[] nameValuePairArr2 = nameValuePairArr;
            new BasicNameValuePair("value", JsonUtil.getJsonRepresentation(obj2));
            nameValuePairArr2[1] = nameValuePair2;
            instance.postCommand(str3, "storeavalue", Lists.newArrayList(nameValuePairArr2), asyncCallbackPair2);
        } catch (JSONException e) {
            Throwable th2 = th;
            new YailRuntimeError("Value failed to convert to JSON.", "JSON Creation Error.");
            throw th2;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TinyWebDB(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        Handler handler;
        new Handler();
        this.androidUIHandler = handler;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String ServiceURL() {
        return this.CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV;
    }

    @DesignerProperty(defaultValue = "http://tinywebdb.builder.makeroid.io", editorType = "string")
    @SimpleProperty(description = "Specifies the URL of the  Web service.")
    public void ServiceURL(String str) {
        String str2 = str;
        this.CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV = str2;
    }

    @SimpleFunction(description = "Asks the Web service to store the given value under the given tag.")
    public void StoreValue(String str, Object obj) {
        Runnable runnable;
        final String str2 = str;
        final Object obj2 = obj;
        new Runnable(this) {
            private /* synthetic */ TinyWebDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                TinyWebDB.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str2, obj2);
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    @SimpleEvent(description = "Event indicating that a StoreValue server request has succeeded.")
    public void ValueStored() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ValueStored", new Object[0]);
    }

    @SimpleFunction(description = "GetValue asks the Web service to get the value stored under the given tag.")
    public void GetValue(String str) {
        Runnable runnable;
        final String str2 = str;
        new Runnable(this) {
            private /* synthetic */ TinyWebDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                TinyWebDB.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str2);
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    @SimpleEvent(description = "Indicates that a GetValue server request has succeeded.")
    public void GotValue(String str, Object obj) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = obj;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotValue", objArr2);
    }

    @SimpleEvent(description = "Indicates that the communication with the Web service signaled an error.")
    public void WebServiceError(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "WebServiceError", str);
    }

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(TinyWebDB tinyWebDB, String str) {
        AsyncCallbackPair asyncCallbackPair;
        NameValuePair nameValuePair;
        TinyWebDB tinyWebDB2 = tinyWebDB;
        String str2 = str;
        final String str3 = str2;
        new AsyncCallbackPair<JSONArray>(tinyWebDB2) {
            final /* synthetic */ TinyWebDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final /* synthetic */ void onSuccess(Object obj) {
                Runnable runnable;
                Object objectFromJson;
                Runnable runnable2;
                Runnable runnable3;
                JSONArray jSONArray = (JSONArray) obj;
                if (jSONArray == null) {
                    new Runnable(this) {
                        private /* synthetic */ C10524 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void run() {
                            StringBuilder sb;
                            TinyWebDB tinyWebDB = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                            new StringBuilder("The Web server did not respond to the get value request for the tag ");
                            tinyWebDB.WebServiceError(sb.append(str3).append(".").toString());
                        }
                    };
                    boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable3);
                    return;
                }
                try {
                    String string = jSONArray.getString(1);
                    String string2 = jSONArray.getString(2);
                    String str = string2;
                    if (string2.length() == 0) {
                        objectFromJson = "";
                    } else {
                        objectFromJson = JsonUtil.getObjectFromJson(str, true);
                    }
                    final String str2 = string;
                    final Object obj2 = objectFromJson;
                    new Runnable(this) {
                        private /* synthetic */ C10524 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                        }

                        public final void run() {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotValue(str2, obj2);
                        }
                    };
                    boolean post2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable2);
                } catch (JSONException e) {
                    new Runnable(this) {
                        private /* synthetic */ C10524 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void run() {
                            StringBuilder sb;
                            TinyWebDB tinyWebDB = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                            new StringBuilder("The Web server returned a garbled value for the tag ");
                            tinyWebDB.WebServiceError(sb.append(str3).append(".").toString());
                        }
                    };
                    boolean post3 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable);
                }
            }

            public final void onFailure(String str) {
                Runnable runnable;
                final String str2 = str;
                new Runnable(this) {
                    private /* synthetic */ C10524 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                    }

                    public final void run() {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.WebServiceError(str2);
                    }
                };
                boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable);
            }
        };
        WebServiceUtil instance = WebServiceUtil.getInstance();
        String str4 = tinyWebDB2.CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV;
        new BasicNameValuePair("tag", str2);
        instance.postCommandReturningArray(str4, "getvalue", Lists.newArrayList(nameValuePair), asyncCallbackPair);
    }
}
