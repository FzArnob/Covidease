package com.google.appinventor.components.runtime.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;
import com.google.appinventor.components.runtime.util.IOUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/* renamed from: com.google.appinventor.components.runtime.multidex.a */
final class C1113a {
    private static Method hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public static boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Context context, ApplicationInfo applicationInfo) {
        File file;
        Context context2 = context;
        new File(applicationInfo.sourceDir);
        File file2 = file;
        try {
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context2, file2, B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(file2))) {
                return true;
            }
        } catch (IOException e) {
        }
        return false;
    }

    static List<File> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Context context, ApplicationInfo applicationInfo, File file, boolean z) throws IOException {
        StringBuilder sb;
        File file2;
        List<File> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
        StringBuilder sb2;
        Context context2 = context;
        ApplicationInfo applicationInfo2 = applicationInfo;
        File file3 = file;
        boolean z2 = z;
        new StringBuilder("MultiDexExtractor.load(");
        int i = Log.i("MultiDex", sb.append(applicationInfo2.sourceDir).append(", ").append(z2).append(")").toString());
        new File(applicationInfo2.sourceDir);
        File file4 = file2;
        File file5 = file4;
        long B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(file4);
        if (z2 || hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context2, file5, B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T)) {
            int i2 = Log.i("MultiDex", "Detected that extraction must be performed.");
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(file5, file3);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context2, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(file5), B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.size() + 1);
        } else {
            try {
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context2, file5, file3);
            } catch (IOException e) {
                int w = Log.w("MultiDex", "Failed to reload existing extracted secondary dex files, falling back to fresh extraction", e);
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(file5, file3);
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context2, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(file5), B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.size() + 1);
            }
        }
        new StringBuilder("load found ");
        int i3 = Log.i("MultiDex", sb2.append(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.size()).append(" secondary dex files").toString());
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
    }

    private static List<File> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Context context, File file, File file2) throws IOException {
        StringBuilder sb;
        List<File> list;
        StringBuilder sb2;
        File file3;
        Throwable th;
        StringBuilder sb3;
        Throwable th2;
        File file4 = file2;
        int i = Log.i("MultiDex", "loading existing secondary dex files");
        new StringBuilder();
        String sb4 = sb.append(file.getName()).append(".classes").toString();
        int i2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context).getInt("dex.number", 1);
        new ArrayList(i2);
        List<File> list2 = list;
        int i3 = 2;
        while (i3 <= i2) {
            new StringBuilder();
            new File(file4, sb2.append(sb4).append(i3).append(".zip").toString());
            File file5 = file3;
            File file6 = file5;
            if (file5.isFile()) {
                boolean add = list2.add(file6);
                if (!hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(file6)) {
                    int i4 = Log.i("MultiDex", "Invalid zip file: ".concat(String.valueOf(file6)));
                    Throwable th3 = th2;
                    new IOException("Invalid ZIP file.");
                    throw th3;
                }
                i3++;
            } else {
                Throwable th4 = th;
                new StringBuilder("Missing extracted secondary dex file '");
                new IOException(sb3.append(file6.getPath()).append("'").toString());
                throw th4;
            }
        }
        return list2;
    }

    private static boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Context context, File file, long j) {
        long j2 = j;
        SharedPreferences hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context);
        SharedPreferences sharedPreferences = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.getLong("timestamp", -1) == hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(file) && sharedPreferences.getLong("crc", -1) == j2) {
            return false;
        }
        return true;
    }

    private static long hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(File file) {
        long lastModified = file.lastModified();
        long j = lastModified;
        if (lastModified == -1) {
            j--;
        }
        return j;
    }

    private static long B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(File file) throws IOException {
        long B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = C1115b.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(file);
        long j = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        if (B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T == -1) {
            j--;
        }
        return j;
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v17, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v48, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v23, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v26, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v53, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v55, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v29, resolved type: java.io.File} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<java.io.File> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(java.io.File r16, java.io.File r17) throws java.io.IOException {
        /*
            r1 = r16
            r2 = r17
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r15 = r10
            r10 = r15
            r11 = r15
            r11.<init>()
            r11 = r1
            java.lang.String r11 = r11.getName()
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r11 = ".classes"
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            r3 = r10
            r10 = r2
            r11 = r3
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.io.File) r10, (java.lang.String) r11)
            java.util.ArrayList r10 = new java.util.ArrayList
            r15 = r10
            r10 = r15
            r11 = r15
            r11.<init>()
            r4 = r10
            java.util.zip.ZipFile r10 = new java.util.zip.ZipFile
            r15 = r10
            r10 = r15
            r11 = r15
            r12 = r1
            r11.<init>(r12)
            r1 = r10
            r10 = 2
            r5 = r10
            r10 = r1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0161 }
            r15 = r11
            r11 = r15
            r12 = r15
            java.lang.String r13 = "classes2.dex"
            r12.<init>(r13)     // Catch:{ all -> 0x0161 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0161 }
            java.util.zip.ZipEntry r10 = r10.getEntry(r11)     // Catch:{ all -> 0x0161 }
            r6 = r10
        L_0x0050:
            r10 = r6
            if (r10 == 0) goto L_0x018e
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0161 }
            r15 = r10
            r10 = r15
            r11 = r15
            r11.<init>()     // Catch:{ all -> 0x0161 }
            r11 = r3
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0161 }
            r11 = r5
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0161 }
            java.lang.String r11 = ".zip"
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0161 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0161 }
            r7 = r10
            java.io.File r10 = new java.io.File     // Catch:{ all -> 0x0161 }
            r15 = r10
            r10 = r15
            r11 = r15
            r12 = r2
            r13 = r7
            r11.<init>(r12, r13)     // Catch:{ all -> 0x0161 }
            r7 = r10
            r10 = r4
            r11 = r7
            boolean r10 = r10.add(r11)     // Catch:{ all -> 0x0161 }
            java.lang.String r10 = "MultiDex"
            java.lang.String r11 = "Extraction is needed for file "
            r12 = r7
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x0161 }
            java.lang.String r11 = r11.concat(r12)     // Catch:{ all -> 0x0161 }
            int r10 = android.util.Log.i(r10, r11)     // Catch:{ all -> 0x0161 }
            r10 = 0
            r8 = r10
            r10 = 0
            r9 = r10
        L_0x0099:
            r10 = r8
            r11 = 3
            if (r10 >= r11) goto L_0x012a
            r10 = r9
            if (r10 != 0) goto L_0x012a
            int r8 = r8 + 1
            r10 = r1
            r11 = r6
            r12 = r7
            r13 = r3
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.util.zip.ZipFile) r10, (java.util.zip.ZipEntry) r11, (java.io.File) r12, (java.lang.String) r13)     // Catch:{ all -> 0x0161 }
            r10 = r7
            boolean r10 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.io.File) r10)     // Catch:{ all -> 0x0161 }
            r9 = r10
            java.lang.String r10 = "MultiDex"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0161 }
            r15 = r11
            r11 = r15
            r12 = r15
            java.lang.String r13 = "Extraction "
            r12.<init>(r13)     // Catch:{ all -> 0x0161 }
            r12 = r9
            if (r12 == 0) goto L_0x0126
            java.lang.String r12 = "success"
        L_0x00c3:
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x0161 }
            java.lang.String r12 = " - length "
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x0161 }
            r12 = r7
            java.lang.String r12 = r12.getAbsolutePath()     // Catch:{ all -> 0x0161 }
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x0161 }
            java.lang.String r12 = ": "
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x0161 }
            r12 = r7
            long r12 = r12.length()     // Catch:{ all -> 0x0161 }
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x0161 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0161 }
            int r10 = android.util.Log.i(r10, r11)     // Catch:{ all -> 0x0161 }
            r10 = r9
            if (r10 != 0) goto L_0x0099
            r10 = r7
            boolean r10 = r10.delete()     // Catch:{ all -> 0x0161 }
            r10 = r7
            boolean r10 = r10.exists()     // Catch:{ all -> 0x0161 }
            if (r10 == 0) goto L_0x0099
            java.lang.String r10 = "MultiDex"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0161 }
            r15 = r11
            r11 = r15
            r12 = r15
            java.lang.String r13 = "Failed to delete corrupted secondary dex '"
            r12.<init>(r13)     // Catch:{ all -> 0x0161 }
            r12 = r7
            java.lang.String r12 = r12.getPath()     // Catch:{ all -> 0x0161 }
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x0161 }
            java.lang.String r12 = "'"
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x0161 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0161 }
            int r10 = android.util.Log.w(r10, r11)     // Catch:{ all -> 0x0161 }
            goto L_0x0099
        L_0x0126:
            java.lang.String r12 = "failed"
            goto L_0x00c3
        L_0x012a:
            r10 = r9
            if (r10 != 0) goto L_0x0169
            java.io.IOException r10 = new java.io.IOException     // Catch:{ all -> 0x0161 }
            r15 = r10
            r10 = r15
            r11 = r15
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0161 }
            r15 = r12
            r12 = r15
            r13 = r15
            java.lang.String r14 = "Could not create zip file "
            r13.<init>(r14)     // Catch:{ all -> 0x0161 }
            r13 = r7
            java.lang.String r13 = r13.getAbsolutePath()     // Catch:{ all -> 0x0161 }
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ all -> 0x0161 }
            java.lang.String r13 = " for secondary dex ("
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ all -> 0x0161 }
            r13 = r5
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ all -> 0x0161 }
            java.lang.String r13 = ")"
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ all -> 0x0161 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0161 }
            r11.<init>(r12)     // Catch:{ all -> 0x0161 }
            throw r10     // Catch:{ all -> 0x0161 }
        L_0x0161:
            r10 = move-exception
            r2 = r10
            r10 = r1
            r10.close()     // Catch:{ IOException -> 0x01a3 }
        L_0x0167:
            r10 = r2
            throw r10
        L_0x0169:
            int r5 = r5 + 1
            r10 = r1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0161 }
            r15 = r11
            r11 = r15
            r12 = r15
            java.lang.String r13 = "classes"
            r12.<init>(r13)     // Catch:{ all -> 0x0161 }
            r12 = r5
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x0161 }
            java.lang.String r12 = ".dex"
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x0161 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0161 }
            java.util.zip.ZipEntry r10 = r10.getEntry(r11)     // Catch:{ all -> 0x0161 }
            r6 = r10
            goto L_0x0050
        L_0x018e:
            r10 = r1
            r10.close()     // Catch:{ IOException -> 0x0195 }
        L_0x0192:
            r10 = r4
            r1 = r10
            return r1
        L_0x0195:
            r10 = move-exception
            r5 = r10
            java.lang.String r10 = "MultiDex"
            java.lang.String r11 = "Failed to close resource"
            r12 = r5
            int r10 = android.util.Log.w(r10, r11, r12)
            goto L_0x0192
        L_0x01a3:
            r10 = move-exception
            r1 = r10
            java.lang.String r10 = "MultiDex"
            java.lang.String r11 = "Failed to close resource"
            r12 = r1
            int r10 = android.util.Log.w(r10, r11, r12)
            goto L_0x0167
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.multidex.C1113a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(java.io.File, java.io.File):java.util.List");
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Context context, long j, long j2, int i) {
        SharedPreferences.Editor edit = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context).edit();
        SharedPreferences.Editor editor = edit;
        SharedPreferences.Editor putLong = edit.putLong("timestamp", j);
        SharedPreferences.Editor putLong2 = editor.putLong("crc", j2);
        SharedPreferences.Editor putInt = editor.putInt("dex.number", i);
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(editor);
    }

    private static SharedPreferences hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Context context) {
        return context.getSharedPreferences("multidex.version", Build.VERSION.SDK_INT < 11 ? 0 : 4);
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(File file, String str) throws IOException {
        FileFilter fileFilter;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        Throwable th;
        StringBuilder sb5;
        File file2 = file;
        String str2 = str;
        boolean mkdirs = file2.mkdirs();
        if (!file2.isDirectory()) {
            Throwable th2 = th;
            new StringBuilder("Failed to create dex directory ");
            new IOException(sb5.append(file2.getPath()).toString());
            throw th2;
        }
        final String str3 = str2;
        new FileFilter() {
            public final boolean accept(File file) {
                return !file.getName().startsWith(str3);
            }
        };
        File[] listFiles = file2.listFiles(fileFilter);
        File[] fileArr = listFiles;
        if (listFiles == null) {
            new StringBuilder("Failed to list secondary dex dir content (");
            int w = Log.w("MultiDex", sb4.append(file2.getPath()).append(").").toString());
            return;
        }
        File[] fileArr2 = fileArr;
        File[] fileArr3 = fileArr2;
        int length = fileArr2.length;
        for (int i = 0; i < length; i++) {
            File file3 = fileArr3[i];
            new StringBuilder("Trying to delete old file ");
            int i2 = Log.i("MultiDex", sb.append(file3.getPath()).append(" of size ").append(file3.length()).toString());
            if (!file3.delete()) {
                new StringBuilder("Failed to delete old file ");
                int w2 = Log.w("MultiDex", sb3.append(file3.getPath()).toString());
            } else {
                new StringBuilder("Deleted old file ");
                int i3 = Log.i("MultiDex", sb2.append(file3.getPath()).toString());
            }
        }
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(ZipFile zipFile, ZipEntry zipEntry, File file, String str) throws IOException, FileNotFoundException {
        StringBuilder sb;
        ZipOutputStream zipOutputStream;
        OutputStream outputStream;
        OutputStream outputStream2;
        ZipOutputStream zipOutputStream2;
        ZipEntry zipEntry2;
        StringBuilder sb2;
        Throwable th;
        StringBuilder sb3;
        ZipEntry zipEntry3 = zipEntry;
        File file2 = file;
        InputStream inputStream = zipFile.getInputStream(zipEntry3);
        File createTempFile = File.createTempFile(str, ".zip", file2.getParentFile());
        new StringBuilder("Extracting ");
        int i = Log.i("MultiDex", sb.append(createTempFile.getPath()).toString());
        try {
            ZipOutputStream zipOutputStream3 = zipOutputStream;
            new FileOutputStream(createTempFile);
            new BufferedOutputStream(outputStream2);
            new ZipOutputStream(outputStream);
            zipOutputStream2 = zipOutputStream3;
            new ZipEntry("classes.dex");
            ZipEntry zipEntry4 = zipEntry2;
            zipEntry4.setTime(zipEntry3.getTime());
            zipOutputStream2.putNextEntry(zipEntry4);
            byte[] bArr = new byte[16384];
            for (int read = inputStream.read(bArr); read != -1; read = inputStream.read(bArr)) {
                zipOutputStream2.write(bArr, 0, read);
            }
            zipOutputStream2.closeEntry();
            zipOutputStream2.close();
            new StringBuilder("Renaming to ");
            int i2 = Log.i("MultiDex", sb2.append(file2.getPath()).toString());
            if (!createTempFile.renameTo(file2)) {
                Throwable th2 = th;
                new StringBuilder("Failed to rename \"");
                new IOException(sb3.append(createTempFile.getAbsolutePath()).append("\" to \"").append(file2.getAbsolutePath()).append("\"").toString());
                throw th2;
            }
            IOUtils.closeQuietly("MultiDex", inputStream);
            boolean delete = createTempFile.delete();
        } catch (Throwable th3) {
            Throwable th4 = th3;
            IOUtils.closeQuietly("MultiDex", inputStream);
            boolean delete2 = createTempFile.delete();
            throw th4;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        new java.lang.StringBuilder("Failed to close zip file: ");
        r2 = android.util.Log.w("MultiDex", r6.append(r0.getAbsolutePath()).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        new java.lang.StringBuilder("File ");
        r2 = android.util.Log.w("MultiDex", r6.append(r0.getAbsolutePath()).append(" is not a valid zip file.").toString(), r2);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0035 A[ExcHandler: ZipException (r2v3 'e' java.util.zip.ZipException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean m64hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(java.io.File r7) {
        /*
            r0 = r7
            java.util.zip.ZipFile r2 = new java.util.zip.ZipFile     // Catch:{ ZipException -> 0x0035, IOException -> 0x005f }
            r6 = r2
            r2 = r6
            r3 = r6
            r4 = r0
            r3.<init>(r4)     // Catch:{ ZipException -> 0x0035, IOException -> 0x005f }
            r1 = r2
            r2 = r1
            r2.close()     // Catch:{ IOException -> 0x0012, ZipException -> 0x0035 }
            r2 = 1
            r0 = r2
        L_0x0011:
            return r0
        L_0x0012:
            r2 = move-exception
            java.lang.String r2 = "MultiDex"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ ZipException -> 0x0035, IOException -> 0x005f }
            r6 = r3
            r3 = r6
            r4 = r6
            java.lang.String r5 = "Failed to close zip file: "
            r4.<init>(r5)     // Catch:{ ZipException -> 0x0035, IOException -> 0x005f }
            r4 = r0
            java.lang.String r4 = r4.getAbsolutePath()     // Catch:{ ZipException -> 0x0035, IOException -> 0x005f }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ ZipException -> 0x0035, IOException -> 0x005f }
            java.lang.String r3 = r3.toString()     // Catch:{ ZipException -> 0x0035, IOException -> 0x005f }
            int r2 = android.util.Log.w(r2, r3)     // Catch:{ ZipException -> 0x0035, IOException -> 0x005f }
        L_0x0032:
            r2 = 0
            r0 = r2
            goto L_0x0011
        L_0x0035:
            r2 = move-exception
            r1 = r2
            java.lang.String r2 = "MultiDex"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r6 = r3
            r3 = r6
            r4 = r6
            java.lang.String r5 = "File "
            r4.<init>(r5)
            r4 = r0
            java.lang.String r4 = r4.getAbsolutePath()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = " is not a valid zip file."
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r4 = r1
            int r2 = android.util.Log.w(r2, r3, r4)
            goto L_0x0032
        L_0x005f:
            r2 = move-exception
            r1 = r2
            java.lang.String r2 = "MultiDex"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r6 = r3
            r3 = r6
            r4 = r6
            java.lang.String r5 = "Got an IOException trying to open zip file: "
            r4.<init>(r5)
            r4 = r0
            java.lang.String r4 = r4.getAbsolutePath()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r4 = r1
            int r2 = android.util.Log.w(r2, r3, r4)
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.multidex.C1113a.m64hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(java.io.File):boolean");
    }

    static {
        try {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = SharedPreferences.Editor.class.getMethod("apply", new Class[0]);
        } catch (NoSuchMethodException e) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        }
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(SharedPreferences.Editor editor) {
        SharedPreferences.Editor editor2 = editor;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            try {
                Object invoke = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invoke(editor2, new Object[0]);
                return;
            } catch (IllegalAccessException | InvocationTargetException e) {
            }
        }
        boolean commit = editor2.commit();
    }
}
