package com.google.appinventor.components.runtime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.AppInvHTTPD;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.RetValManager;
import com.google.appinventor.components.runtime.util.WebRTCNativeMgr;
import dalvik.system.DexClassLoader;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kawa.standard.Scheme;

public class ReplForm extends Form {
    /* access modifiers changed from: package-private */
    public static final String LOG_TAG = ReplForm.class.getSimpleName();
    private static final String REPL_ASSET_DIR;
    public static ReplForm topform;
    private static final String wfWoJD9s05zGciiq11jj7TXqW1AmJIE9BV68DWBBkLZSv9NayRoTG3XJb9WJMlyD;
    private boolean I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2 = false;
    private boolean Mn7MCs8byCcphc6u6vZkI1pXuzw5IvVJJPq0YTQ0xCW64cXQ1HYdJPP7QsOPqGr1 = false;
    String TDuNe2Upxoi7WX7QZc6R8eUnFPyyDFw3hP7z3w6U8jMxQwJRQM03zSa9HzWXRv1 = null;
    private List<String> f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ;
    private boolean hlRRzlkTvaLJKT7xudtgqrNpSapzuwbeZZHKJdIwcwGUTFYejftgk4y9qZgGh2f0 = false;
    private SchemeInterface hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private AppInvHTTPD f519hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private WebRTCNativeMgr f520hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    Object sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = null;
    private boolean showOptionsMenu = true;

    static {
        StringBuilder sb;
        StringBuilder sb2;
        new StringBuilder();
        REPL_ASSET_DIR = sb.append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/Makeroid/assets/").toString();
        new StringBuilder();
        wfWoJD9s05zGciiq11jj7TXqW1AmJIE9BV68DWBBkLZSv9NayRoTG3XJb9WJMlyD = sb2.append(REPL_ASSET_DIR).append("external_comps/").toString();
    }

    public ReplForm() {
        SchemeInterface schemeInterface;
        new SchemeInterface(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = schemeInterface;
        topform = this;
    }

    public class SchemeInterface {
        final /* synthetic */ ReplForm hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        Language f521hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Scheme.getInstance("scheme");

        public SchemeInterface(ReplForm replForm) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = replForm;
            ModuleExp.mustNeverCompile();
        }

        public void eval(String str) {
            Runnable runnable;
            final String str2 = str;
            new Runnable(this) {
                private /* synthetic */ SchemeInterface B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

                {
                    this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r6;
                }

                public final void run() {
                    try {
                        SchemeInterface.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE();
                        if (str2.equals("#DONE#")) {
                            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.finish();
                        } else {
                            Object eval = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.f521hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.eval(str2);
                        }
                    } catch (Throwable th) {
                        int e = Log.e(ReplForm.LOG_TAG, "Exception in scheme processing", th);
                    }
                }
            };
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(runnable);
        }

        static /* synthetic */ void tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE() {
            ClassLoader contextClassLoader = Looper.getMainLooper().getThread().getContextClassLoader();
            Thread currentThread = Thread.currentThread();
            Thread thread = currentThread;
            if (currentThread.getContextClassLoader() != contextClassLoader) {
                thread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    public void onCreate(Bundle bundle) {
        List<String> list;
        super.onCreate(bundle);
        int d = Log.d(LOG_TAG, "onCreate");
        new ArrayList();
        this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ = list;
        processExtras(getIntent(), false);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.f519hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.f519hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stop();
            this.f519hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        }
        finish();
        System.exit(0);
    }

    /* access modifiers changed from: protected */
    public void startNewForm(String str, Object obj) {
        String str2 = str;
        Object obj2 = obj;
        if (obj2 != null) {
            this.startupValue = jsonEncodeForForm(obj2, "open another screen with start value");
        }
        RetValManager.pushScreen(str2, obj2);
    }

    public void setFormName(String str) {
        String str2 = str;
        String str3 = str2;
        this.formName = str3;
        int d = Log.d(LOG_TAG, "formName is now ".concat(String.valueOf(str2)));
    }

    /* access modifiers changed from: protected */
    public void closeForm(Intent intent) {
        Intent intent2 = intent;
        RetValManager.popScreen("Not Yet");
    }

    /* access modifiers changed from: protected */
    public void setResult(Object obj) {
        Object obj2 = obj;
        int d = Log.d(LOG_TAG, "setResult: ".concat(String.valueOf(obj2)));
        this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = obj2;
        this.TDuNe2Upxoi7WX7QZc6R8eUnFPyyDFw3hP7z3w6U8jMxQwJRQM03zSa9HzWXRv1 = this.formName;
    }

    /* access modifiers changed from: protected */
    public void closeApplicationFromBlocks() {
        Runnable runnable;
        new Runnable(this) {
            private /* synthetic */ ReplForm hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                Toast.makeText(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Closing forms is not currently supported during development.", 1).show();
            }
        };
        runOnUiThread(runnable);
    }

    public String getAssetPath(String str) {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(REPL_ASSET_DIR).append(str).toString();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        boolean onCreateOptionsMenu = super.onCreateOptionsMenu(menu);
        return this.showOptionsMenu;
    }

    @SimpleProperty(userVisible = false)
    public void ShowOptionsMenu(boolean z) {
        boolean z2 = z;
        this.showOptionsMenu = z2;
        super.ShowOptionsMenu(z2);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        Intent intent2 = intent;
        super.onNewIntent(intent2);
        int d = Log.d(LOG_TAG, "onNewIntent Called");
        processExtras(intent2, true);
    }

    /* access modifiers changed from: protected */
    public void processExtras(Intent intent, boolean z) {
        StringBuilder sb;
        boolean z2 = z;
        Bundle extras = intent.getExtras();
        Bundle bundle = extras;
        if (extras != null) {
            int d = Log.d(LOG_TAG, "extras: ".concat(String.valueOf(bundle)));
            for (String append : bundle.keySet()) {
                String str = LOG_TAG;
                new StringBuilder("Extra Key: ");
                int d2 = Log.d(str, sb.append(append).toString());
            }
        }
        if (bundle != null && bundle.getBoolean("rundirect")) {
            int d3 = Log.d(LOG_TAG, "processExtras rundirect is true and restart is ".concat(String.valueOf(z2)));
            this.Mn7MCs8byCcphc6u6vZkI1pXuzw5IvVJJPq0YTQ0xCW64cXQ1HYdJPP7QsOPqGr1 = true;
            this.I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2 = true;
            if (z2) {
                clear();
                if (this.f519hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
                    this.f519hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.resetSeq();
                    return;
                }
                startHTTPD(true);
                AppInvHTTPD.setHmacKey("emulator");
            }
        }
    }

    public boolean isDirect() {
        return this.Mn7MCs8byCcphc6u6vZkI1pXuzw5IvVJJPq0YTQ0xCW64cXQ1HYdJPP7QsOPqGr1;
    }

    public void setIsUSBrepl() {
        this.hlRRzlkTvaLJKT7xudtgqrNpSapzuwbeZZHKJdIwcwGUTFYejftgk4y9qZgGh2f0 = true;
    }

    public void startHTTPD(boolean z) {
        StringBuilder sb;
        File file;
        AppInvHTTPD appInvHTTPD;
        File file2;
        boolean z2 = z;
        try {
            if (this.f519hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
                new File(REPL_ASSET_DIR);
                File file3 = file;
                File file4 = file3;
                if (!file3.exists()) {
                    boolean mkdirs = file4.mkdirs();
                }
                new File(REPL_ASSET_DIR);
                new AppInvHTTPD(8001, file2, z2, this);
                this.f519hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = appInvHTTPD;
                int i = Log.i(LOG_TAG, "started AppInvHTTPD");
            }
        } catch (IOException e) {
            IOException iOException = e;
            String str = LOG_TAG;
            new StringBuilder("Setting up NanoHTTPD: ");
            int e2 = Log.e(str, sb.append(iOException.toString()).toString());
        }
    }

    public boolean isAssetsLoaded() {
        return this.I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2;
    }

    public void setAssetsLoaded() {
        this.I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2 = true;
    }

    public void loadComponents(List<String> list) {
        Set set;
        File file;
        File file2;
        boolean z;
        StringBuilder sb;
        ClassLoader classLoader;
        File file3;
        StringBuilder sb2;
        File file4;
        StringBuilder sb3;
        StringBuilder sb4;
        new HashSet(list);
        Set set2 = set;
        File dir = activeForm.$context().getDir("componentDexs", 0);
        new File(wfWoJD9s05zGciiq11jj7TXqW1AmJIE9BV68DWBBkLZSv9NayRoTG3XJb9WJMlyD);
        File file5 = file;
        new File(wfWoJD9s05zGciiq11jj7TXqW1AmJIE9BV68DWBBkLZSv9NayRoTG3XJb9WJMlyD);
        File file6 = file2;
        File file7 = file6;
        if (!file6.exists()) {
            z = file7.mkdirs();
        } else {
            z = true;
        }
        if (!z) {
            int d = Log.d(LOG_TAG, "Unable to create components directory");
            Object[] objArr = new Object[3];
            objArr[0] = 1;
            Object[] objArr2 = objArr;
            objArr2[1] = FusiontablesControl.APP_NAME;
            Object[] objArr3 = objArr2;
            objArr3[2] = "Unable to create component directory.";
            dispatchErrorOccurredEventDialog(this, "loadComponents", ErrorMessages.ERROR_EXTENSION_ERROR, objArr3);
            return;
        }
        ClassLoader classLoader2 = ReplForm.class.getClassLoader();
        new StringBuilder();
        StringBuilder sb5 = sb;
        this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ.clear();
        File[] listFiles = file5.listFiles();
        File[] fileArr = listFiles;
        int length = listFiles.length;
        for (int i = 0; i < length; i++) {
            File file8 = fileArr[i];
            File file9 = file8;
            if (file8.isDirectory() && set2.contains(file9.getName())) {
                new StringBuilder();
                new File(sb2.append(file9.getPath()).append(File.separator).append("classes.jar").toString());
                new StringBuilder();
                new File(sb3.append(file9.getPath()).append(File.separator).append(file9.getName()).append(".jar").toString());
                File file10 = file4;
                boolean renameTo = file3.renameTo(file10);
                if (file10.exists() && !this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ.contains(file10.getName())) {
                    String str = LOG_TAG;
                    new StringBuilder("Loading component dex ");
                    int d2 = Log.d(str, sb4.append(file10.getAbsolutePath()).toString());
                    boolean add = this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ.add(file10.getName());
                    StringBuilder append = sb5.append(File.pathSeparatorChar);
                    StringBuilder append2 = sb5.append(file10.getAbsolutePath());
                }
            }
        }
        new DexClassLoader(sb5.substring(1), dir.getAbsolutePath(), (String) null, classLoader2);
        ClassLoader classLoader3 = classLoader;
        Thread.currentThread().setContextClassLoader(classLoader3);
        int d3 = Log.d(LOG_TAG, Thread.currentThread().toString());
        int d4 = Log.d(LOG_TAG, Looper.getMainLooper().getThread().toString());
        Looper.getMainLooper().getThread().setContextClassLoader(classLoader3);
    }

    public static void returnRetvals(String str) {
        String str2 = str;
        ReplForm replForm = (ReplForm) activeForm;
        int d = Log.d(LOG_TAG, "returnRetvals: ".concat(String.valueOf(str2)));
        replForm.sendToCompanion(str2);
    }

    public void sendToCompanion(String str) {
        String str2 = str;
        if (this.f520hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            int i = Log.i(LOG_TAG, "No WebRTCNativeMgr!");
        } else {
            this.f520hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.send(str2);
        }
    }

    public void setWebRTCMgr(WebRTCNativeMgr webRTCNativeMgr) {
        WebRTCNativeMgr webRTCNativeMgr2 = webRTCNativeMgr;
        this.f520hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = webRTCNativeMgr2;
    }

    public void evalScheme(String str) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.eval(str);
    }

    public String getAssetPathForExtension(Component component, String str) throws FileNotFoundException {
        Throwable th;
        File file;
        StringBuilder sb;
        StringBuilder sb2;
        File file2;
        StringBuilder sb3;
        Component component2 = component;
        String str2 = str;
        SimpleObject simpleObject = (SimpleObject) component2.getClass().getAnnotation(SimpleObject.class);
        SimpleObject simpleObject2 = simpleObject;
        if (simpleObject != null && !simpleObject2.external()) {
            return Form.ASSETS_PREFIX.concat(String.valueOf(str2));
        }
        String name = component2.getClass().getName();
        String str3 = null;
        while (true) {
            if (!name.contains(".")) {
                break;
            }
            new StringBuilder();
            new File(sb3.append(wfWoJD9s05zGciiq11jj7TXqW1AmJIE9BV68DWBBkLZSv9NayRoTG3XJb9WJMlyD).append(name).append("/assets").toString());
            File file3 = file2;
            File file4 = file3;
            if (file3.exists() && file4.isDirectory()) {
                str3 = file4.getAbsolutePath();
                break;
            }
            name = name.substring(0, name.lastIndexOf(46));
        }
        if (str3 != null) {
            new File(str3, str2);
            File file5 = file;
            String str4 = LOG_TAG;
            new StringBuilder("result = ");
            int d = Log.d(str4, sb.append(file5.getAbsolutePath()).toString());
            if (file5.exists()) {
                new StringBuilder("file://");
                return sb2.append(file5.getAbsolutePath()).toString();
            }
        }
        Throwable th2 = th;
        new FileNotFoundException();
        throw th2;
    }
}
