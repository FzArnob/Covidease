package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.p000v4.content.FileProvider;
import android.util.Log;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.ReplForm;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.shaded.apache.http.client.methods.HttpGet;
import org.shaded.apache.http.cookie.C1496SM;

public class AssetFetcher {
    /* access modifiers changed from: private */
    public static final String LOG_TAG = AssetFetcher.class.getSimpleName();
    private static final String REPL_ASSET_DIR;
    private static ExecutorService background = Executors.newSingleThreadExecutor();
    private static volatile boolean inError = false;
    private static final Object semaphore;

    static {
        StringBuilder sb;
        Object obj;
        new StringBuilder();
        REPL_ASSET_DIR = sb.append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/Makeroid/").toString();
        new Object();
        semaphore = obj;
    }

    private AssetFetcher() {
    }

    public static void fetchAssets(String str, String str2, String str3, String str4) {
        Runnable runnable;
        final String str5 = str3;
        final String str6 = str2;
        final String str7 = str4;
        final String str8 = str;
        new Runnable() {
            public final void run() {
                StringBuilder sb;
                new StringBuilder();
                if (AssetFetcher.getFile(sb.append(str5).append("/ode/download/file/").append(str6).append("/").append(str7).toString(), str8, str7, 0) != null) {
                    RetValManager.assetTransferred(str7);
                }
            }
        };
        Future<?> submit = background.submit(runnable);
    }

    public static void upgradeCompanion(String str, String str2) {
        Runnable runnable;
        final String str3 = str2;
        final String str4 = str;
        new Runnable() {
            public final void run() {
                Intent intent;
                StringBuilder sb;
                String[] split = str3.split("/", 0);
                File access$000 = AssetFetcher.getFile(str3, str4, split[split.length - 1], 0);
                File file = access$000;
                if (access$000 != null) {
                    try {
                        Form activeForm = Form.getActiveForm();
                        new Intent("android.intent.action.VIEW");
                        Intent intent2 = intent;
                        String packageName = activeForm.$context().getPackageName();
                        Activity $context = activeForm.$context();
                        new StringBuilder();
                        Intent dataAndType = intent2.setDataAndType(FileProvider.getUriForFile($context, sb.append(packageName).append(".provider").toString(), file), "application/vnd.android.package-archive");
                        Intent flags = intent2.setFlags(1);
                        activeForm.startActivity(intent2);
                    } catch (Exception e) {
                        int e2 = Log.e(AssetFetcher.LOG_TAG, "ERROR_UNABLE_TO_GET", e);
                        RetValManager.sendError("Unable to Install new Companion Package.");
                    }
                }
            }
        };
        Future<?> submit = background.submit(runnable);
    }

    public static void loadExtensions(String str) {
        JSONArray jSONArray;
        List list;
        String str2 = str;
        int d = Log.d(LOG_TAG, "loadExtensions called jsonString = ".concat(String.valueOf(str2)));
        try {
            ReplForm replForm = (ReplForm) Form.getActiveForm();
            new JSONArray(str2);
            JSONArray jSONArray2 = jSONArray;
            new ArrayList();
            List list2 = list;
            if (jSONArray2.length() == 0) {
                int d2 = Log.d(LOG_TAG, "loadExtensions: No Extensions");
                RetValManager.extensionsLoaded();
                return;
            }
            int i = 0;
            while (i < jSONArray2.length()) {
                String optString = jSONArray2.optString(i);
                String str3 = optString;
                if (optString != null) {
                    int d3 = Log.d(LOG_TAG, "loadExtensions, extensionName = ".concat(String.valueOf(str3)));
                    boolean add = list2.add(str3);
                    i++;
                } else {
                    int e = Log.e(LOG_TAG, "extensionName was null");
                    return;
                }
            }
            try {
                replForm.loadComponents(list2);
                RetValManager.extensionsLoaded();
            } catch (Exception e2) {
                int e3 = Log.e(LOG_TAG, "Error in form.loadComponents", e2);
            }
        } catch (JSONException e4) {
            int e5 = Log.e(LOG_TAG, "JSON Exception parsing extension string", e4);
        }
    }

    /* access modifiers changed from: private */
    public static File getFile(String str, String str2, String str3, int i) {
        Runnable runnable;
        URL url;
        StringBuilder sb;
        File file;
        StringBuilder sb2;
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        OutputStream outputStream;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        int i2 = i;
        while (i2 <= 1) {
            boolean z = false;
            File file2 = null;
            try {
                new URL(str4);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                HttpURLConnection httpURLConnection2 = httpURLConnection;
                if (httpURLConnection != null) {
                    httpURLConnection2.setRequestMethod(HttpGet.METHOD_NAME);
                    httpURLConnection2.addRequestProperty(C1496SM.COOKIE, "Kodular = ".concat(String.valueOf(str5)));
                    int responseCode = httpURLConnection2.getResponseCode();
                    String str7 = LOG_TAG;
                    new StringBuilder("asset = ");
                    int d = Log.d(str7, sb.append(str6).append(" responseCode = ").append(responseCode).toString());
                    new StringBuilder();
                    new File(sb2.append(REPL_ASSET_DIR).append(str6).toString());
                    File file3 = file;
                    file2 = file3;
                    File parentFile = file3.getParentFile();
                    File file4 = parentFile;
                    if (!parentFile.exists()) {
                        boolean mkdirs = file4.mkdirs();
                    }
                    new BufferedInputStream(httpURLConnection2.getInputStream(), 4096);
                    BufferedInputStream bufferedInputStream2 = bufferedInputStream;
                    BufferedOutputStream bufferedOutputStream2 = bufferedOutputStream;
                    new FileOutputStream(file2);
                    new BufferedOutputStream(outputStream, 4096);
                    BufferedOutputStream bufferedOutputStream3 = bufferedOutputStream2;
                    while (true) {
                        try {
                            int read = bufferedInputStream2.read();
                            int i3 = read;
                            if (read == -1) {
                                break;
                            }
                            bufferedOutputStream3.write(i3);
                        } catch (IOException e) {
                            int e2 = Log.e(LOG_TAG, "copying assets", e);
                            z = true;
                            bufferedOutputStream3.close();
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            bufferedOutputStream3.close();
                            throw th2;
                        }
                    }
                    bufferedOutputStream3.flush();
                    bufferedOutputStream3.close();
                    httpURLConnection2.disconnect();
                } else {
                    z = true;
                }
                if (z) {
                    return getFile(str4, str5, str6, i2 + 1);
                }
                return file2;
            } catch (Exception e3) {
                int e4 = Log.e(LOG_TAG, "Exception while fetching ".concat(String.valueOf(str4)));
                i2++;
                str6 = str6;
                str5 = str5;
                str4 = str4;
            }
        }
        Object obj = semaphore;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (inError) {
                    return null;
                }
                inError = true;
                final String str8 = str4;
                new Runnable() {
                    public final void run() {
                        StringBuilder sb;
                        Form activeForm = Form.getActiveForm();
                        new StringBuilder("Unable to load file: ");
                        RuntimeErrorAlert.alert(activeForm, sb.append(str8).append("\n\nMake sure you have allowed Kodular Companion to access your phone's storage.").toString(), "Error!", "End Application");
                    }
                };
                Form.getActiveForm().runOnUiThread(runnable);
                return null;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                Object obj3 = obj2;
                throw th4;
            }
        }
    }
}
