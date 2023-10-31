package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.JsonUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;

@SimpleObject
@DesignerComponent(category = ComponentCategory.STORAGE, description = "TinyDB is a non-visible component that stores data for an app. <p> Apps created with App Inventor are initialized each time they run: If an app sets the value of a variable and the user then quits the app, the value of that variable will not be remembered the next time the app is run. In contrast, TinyDB is a <em> persistent </em> data store for the app, that is, the data stored there will be available each time the app is run. An example might be a game that saves the high score and retrieves it each time the game is played. </<p> <p> Data items are strings stored under <em>tags</em> . To store a data item, you specify the tag it should be stored under.  Subsequently, you can retrieve the data that was stored under a given tag. </p><p> There is only one data store per app. Even if you have multiple TinyDB components, they will use the same data store. To get the effect of separate stores, use different keys. Also each app has its own data store. You cannot use TinyDB to pass data between two different apps on the phone, although you <em>can</em> use TinyDb to shares data between the different screens of a multi-screen app. </p> <p>When you are developing apps using the AI Companion, all the apps using that companion will share the same TinyDb.  That sharing will disappear once the apps are packaged.  But, during development, you should be careful to clear the TinyDb each time you start working on a new app.</p>", iconName = "images/tinyDB.png", nonVisible = true, version = 2)
public class TinyDB extends AndroidNonvisibleComponent implements Component, Deleteable {
    public static final String DEFAULT_NAMESPACE = "TinyDB1";
    private String UT8jc0jwpnlGoVnBZm9Vqfc7zQxpOFTo7zW0ZxKDjXZZsrQ7LsG7hsoko7RBNNg5;
    private Context context;
    private SharedPreferences hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TinyDB(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            java.lang.String r3 = "TinyDB1"
            r2.Namespace(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.TinyDB.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "TinyDB1", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Namespace for storing data.")
    public void Namespace(String str) {
        String str2 = str;
        this.UT8jc0jwpnlGoVnBZm9Vqfc7zQxpOFTo7zW0ZxKDjXZZsrQ7LsG7hsoko7RBNNg5 = str2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = this.context.getSharedPreferences(str2, 0);
    }

    @SimpleProperty(description = "Namespace for storing data.")
    public String Namespace() {
        return this.UT8jc0jwpnlGoVnBZm9Vqfc7zQxpOFTo7zW0ZxKDjXZZsrQ7LsG7hsoko7RBNNg5;
    }

    @SimpleFunction
    public void StoreValue(String str, Object obj) {
        Throwable th;
        SharedPreferences.Editor edit = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.edit();
        try {
            SharedPreferences.Editor putString = edit.putString(str, JsonUtil.getJsonRepresentation(obj));
            boolean commit = edit.commit();
        } catch (JSONException e) {
            Throwable th2 = th;
            new YailRuntimeError("Value failed to convert to JSON.", "JSON Creation Error.");
            throw th2;
        }
    }

    @SimpleFunction
    public Object GetValue(String str, Object obj) {
        Throwable th;
        Object obj2 = obj;
        try {
            String string = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getString(str, "");
            return string.length() == 0 ? obj2 : JsonUtil.getObjectFromJson(string, true);
        } catch (JSONException e) {
            Throwable th2 = th;
            new YailRuntimeError("Value failed to convert from JSON.", "JSON Creation Error.");
            throw th2;
        }
    }

    @SimpleFunction
    public Object GetTags() {
        List list;
        new ArrayList();
        List list2 = list;
        boolean addAll = list2.addAll(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getAll().keySet());
        Collections.sort(list2);
        return list2;
    }

    @SimpleFunction
    public void ClearAll() {
        SharedPreferences.Editor edit = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.edit();
        SharedPreferences.Editor clear = edit.clear();
        boolean commit = edit.commit();
    }

    @SimpleFunction
    public void ClearTag(String str) {
        SharedPreferences.Editor edit = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.edit();
        SharedPreferences.Editor remove = edit.remove(str);
        boolean commit = edit.commit();
    }

    public void onDelete() {
        SharedPreferences.Editor edit = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.edit();
        SharedPreferences.Editor clear = edit.clear();
        boolean commit = edit.commit();
    }
}
