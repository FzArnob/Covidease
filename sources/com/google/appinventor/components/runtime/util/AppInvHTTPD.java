package com.google.appinventor.components.runtime.util;

import android.os.Handler;
import android.support.p000v4.app.NotificationCompat;
import android.util.Log;
import com.google.appinventor.components.runtime.ReplForm;
import com.google.appinventor.components.runtime.util.NanoHTTPD;
import gnu.expr.Language;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import kawa.standard.Scheme;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppInvHTTPD extends NanoHTTPD {
    private static byte[] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private static int xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT;
    private final Handler androidUIHandler;
    /* access modifiers changed from: private */
    public ReplForm form;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Language f553hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Scheme.getInstance("scheme");
    private boolean secure;
    private File vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppInvHTTPD(int r10, java.io.File r11, boolean r12, com.google.appinventor.components.runtime.ReplForm r13) throws java.io.IOException {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r0
            r6 = r1
            r7 = r2
            r5.<init>(r6, r7)
            r5 = r0
            android.os.Handler r6 = new android.os.Handler
            r8 = r6
            r6 = r8
            r7 = r8
            r7.<init>()
            r5.androidUIHandler = r6
            r5 = r0
            r6 = r2
            r5.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = r6
            r5 = r0
            java.lang.String r6 = "scheme"
            gnu.expr.Language r6 = kawa.standard.Scheme.getInstance(r6)
            r5.f553hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6
            r5 = r0
            r6 = r4
            r5.form = r6
            r5 = r0
            r6 = r3
            r5.secure = r6
            gnu.expr.ModuleExp.mustNeverCompile()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.AppInvHTTPD.<init>(int, java.io.File, boolean, com.google.appinventor.components.runtime.ReplForm):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: java.lang.Appendable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v57, resolved type: java.lang.StringBuffer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: java.lang.Appendable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v324, resolved type: java.lang.StringBuffer} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.appinventor.components.runtime.util.NanoHTTPD.Response serve(java.lang.String r24, java.lang.String r25, java.util.Properties r26, java.util.Properties r27, java.util.Properties r28, java.net.Socket r29) {
        /*
            r23 = this;
            r1 = r23
            r2 = r24
            r3 = r25
            r4 = r26
            r5 = r27
            r6 = r28
            r7 = r29
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r22 = r15
            r15 = r22
            r16 = r22
            r16.<init>()
            r16 = r3
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r16 = " '"
            java.lang.StringBuilder r15 = r15.append(r16)
            r16 = r2
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r16 = "' "
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r15 = r15.toString()
            int r14 = android.util.Log.d(r14, r15)
            r14 = r1
            boolean r14 = r14.secure
            if (r14 == 0) goto L_0x00e5
            r14 = r7
            java.net.InetAddress r14 = r14.getInetAddress()
            java.lang.String r14 = r14.getHostAddress()
            r22 = r14
            r14 = r22
            r15 = r22
            r8 = r15
            java.lang.String r15 = "127.0.0.1"
            boolean r14 = r14.equals(r15)
            if (r14 != 0) goto L_0x00e5
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r22 = r15
            r15 = r22
            r16 = r22
            java.lang.String r17 = "Debug: hostAddress = "
            r16.<init>(r17)
            r16 = r8
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r16 = " while in secure mode, closing connection."
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r15 = r15.toString()
            int r14 = android.util.Log.d(r14, r15)
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "application/json"
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r22 = r19
            r19 = r22
            r20 = r22
            java.lang.String r21 = "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid Source Location "
            r20.<init>(r21)
            r20 = r8
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r20 = "\"}"
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r22 = r14
            r14 = r22
            r15 = r22
            r2 = r15
            java.lang.String r15 = "Access-Control-Allow-Origin"
            java.lang.String r16 = "*"
            r14.addHeader(r15, r16)
            r14 = r2
            java.lang.String r15 = "Access-Control-Allow-Headers"
            java.lang.String r16 = "origin, content-type"
            r14.addHeader(r15, r16)
            r14 = r2
            java.lang.String r15 = "Access-Control-Allow-Methods"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r2
            java.lang.String r15 = "Allow"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r2
            r1 = r14
        L_0x00e4:
            return r1
        L_0x00e5:
            r14 = r3
            java.lang.String r15 = "OPTIONS"
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x0186
            r14 = r4
            java.util.Enumeration r14 = r14.propertyNames()
            r7 = r14
        L_0x00f5:
            r14 = r7
            boolean r14 = r14.hasMoreElements()
            if (r14 == 0) goto L_0x013e
            r14 = r7
            java.lang.Object r14 = r14.nextElement()
            java.lang.String r14 = (java.lang.String) r14
            r8 = r14
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r22 = r15
            r15 = r22
            r16 = r22
            java.lang.String r17 = "  HDR: '"
            r16.<init>(r17)
            r16 = r8
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r16 = "' = '"
            java.lang.StringBuilder r15 = r15.append(r16)
            r16 = r4
            r17 = r8
            java.lang.String r16 = r16.getProperty(r17)
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r16 = "'"
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r15 = r15.toString()
            int r14 = android.util.Log.d(r14, r15)
            goto L_0x00f5
        L_0x013e:
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "text/plain"
            java.lang.String r19 = "OK"
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r22 = r14
            r14 = r22
            r15 = r22
            r8 = r15
            java.lang.String r15 = "Access-Control-Allow-Origin"
            java.lang.String r16 = "*"
            r14.addHeader(r15, r16)
            r14 = r8
            java.lang.String r15 = "Access-Control-Allow-Headers"
            java.lang.String r16 = "origin, content-type"
            r14.addHeader(r15, r16)
            r14 = r8
            java.lang.String r15 = "Access-Control-Allow-Methods"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r8
            java.lang.String r15 = "Allow"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r8
            r1 = r14
            goto L_0x00e4
        L_0x0186:
            r14 = r2
            java.lang.String r15 = "/_newblocks"
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x052f
            android.os.Looper r14 = android.os.Looper.getMainLooper()
            java.lang.Thread r14 = r14.getThread()
            java.lang.ClassLoader r14 = r14.getContextClassLoader()
            r3 = r14
            java.lang.Thread r14 = java.lang.Thread.currentThread()
            r22 = r14
            r14 = r22
            r15 = r22
            r2 = r15
            java.lang.ClassLoader r14 = r14.getContextClassLoader()
            r15 = r3
            if (r14 == r15) goto L_0x01b4
            r14 = r2
            r15 = r3
            r14.setContextClassLoader(r15)
        L_0x01b4:
            r14 = r5
            java.lang.String r15 = "seq"
            java.lang.String r16 = "0"
            java.lang.String r14 = r14.getProperty(r15, r16)
            r22 = r14
            r14 = r22
            r15 = r22
            r7 = r15
            int r14 = java.lang.Integer.parseInt(r14)
            r8 = r14
            r14 = r5
            java.lang.String r15 = "blockid"
            java.lang.String r14 = r14.getProperty(r15)
            r2 = r14
            r14 = r5
            java.lang.String r15 = "code"
            java.lang.String r14 = r14.getProperty(r15)
            r3 = r14
            r14 = r5
            java.lang.String r15 = "mac"
            java.lang.String r16 = "no key provided"
            java.lang.String r14 = r14.getProperty(r15, r16)
            r4 = r14
            r14 = r3
            r6 = r14
            byte[] r14 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            if (r14 == 0) goto L_0x04a8
            java.lang.String r14 = "HmacSHA1"
            javax.crypto.Mac r14 = javax.crypto.Mac.getInstance(r14)     // Catch:{ Exception -> 0x0358 }
            r9 = r14
            javax.crypto.spec.SecretKeySpec r14 = new javax.crypto.spec.SecretKeySpec     // Catch:{ Exception -> 0x0358 }
            r22 = r14
            r14 = r22
            r15 = r22
            byte[] r16 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ Exception -> 0x0358 }
            java.lang.String r17 = "RAW"
            r15.<init>(r16, r17)     // Catch:{ Exception -> 0x0358 }
            r5 = r14
            r14 = r9
            r15 = r5
            r14.init(r15)     // Catch:{ Exception -> 0x0358 }
            r14 = r9
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0358 }
            r22 = r15
            r15 = r22
            r16 = r22
            r16.<init>()     // Catch:{ Exception -> 0x0358 }
            r16 = r3
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x0358 }
            r16 = r7
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x0358 }
            r16 = r2
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x0358 }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x0358 }
            byte[] r15 = r15.getBytes()     // Catch:{ Exception -> 0x0358 }
            byte[] r14 = r14.doFinal(r15)     // Catch:{ Exception -> 0x0358 }
            r10 = r14
            java.lang.StringBuffer r14 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0358 }
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r10
            r0 = r16
            int r0 = r0.length     // Catch:{ Exception -> 0x0358 }
            r16 = r0
            r17 = 1
            int r16 = r16 << 1
            r15.<init>(r16)     // Catch:{ Exception -> 0x0358 }
            r11 = r14
            java.util.Formatter r14 = new java.util.Formatter     // Catch:{ Exception -> 0x0358 }
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r11
            r15.<init>(r16)     // Catch:{ Exception -> 0x0358 }
            r12 = r14
            r14 = r10
            r22 = r14
            r14 = r22
            r15 = r22
            r5 = r15
            int r14 = r14.length     // Catch:{ Exception -> 0x0358 }
            r9 = r14
            r14 = 0
            r10 = r14
        L_0x0269:
            r14 = r10
            r15 = r9
            if (r14 >= r15) goto L_0x0295
            r14 = r5
            r15 = r10
            byte r14 = r14[r15]     // Catch:{ Exception -> 0x0358 }
            r13 = r14
            r14 = r12
            java.lang.String r15 = "%02x"
            r16 = 1
            r0 = r16
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x0358 }
            r16 = r0
            r22 = r16
            r16 = r22
            r17 = r22
            r18 = 0
            r19 = r13
            java.lang.Byte r19 = java.lang.Byte.valueOf(r19)     // Catch:{ Exception -> 0x0358 }
            r17[r18] = r19     // Catch:{ Exception -> 0x0358 }
            java.util.Formatter r14 = r14.format(r15, r16)     // Catch:{ Exception -> 0x0358 }
            int r10 = r10 + 1
            goto L_0x0269
        L_0x0295:
            r14 = r11
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x0358 }
            r5 = r14
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "Incoming Mac = "
            r16 = r4
            java.lang.String r16 = java.lang.String.valueOf(r16)
            java.lang.String r15 = r15.concat(r16)
            int r14 = android.util.Log.d(r14, r15)
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "Computed Mac = "
            r16 = r5
            java.lang.String r16 = java.lang.String.valueOf(r16)
            java.lang.String r15 = r15.concat(r16)
            int r14 = android.util.Log.d(r14, r15)
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "Incoming seq = "
            r16 = r7
            java.lang.String r16 = java.lang.String.valueOf(r16)
            java.lang.String r15 = r15.concat(r16)
            int r14 = android.util.Log.d(r14, r15)
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r22 = r15
            r15 = r22
            r16 = r22
            java.lang.String r17 = "Computed seq = "
            r16.<init>(r17)
            int r16 = xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r15 = r15.toString()
            int r14 = android.util.Log.d(r14, r15)
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "blockid = "
            r16 = r2
            java.lang.String r16 = java.lang.String.valueOf(r16)
            java.lang.String r15 = r15.concat(r16)
            int r14 = android.util.Log.d(r14, r15)
            r14 = r4
            r15 = r5
            boolean r14 = r14.equals(r15)
            if (r14 != 0) goto L_0x03a2
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "Hmac does not match"
            int r14 = android.util.Log.e(r14, r15)
            r14 = r1
            com.google.appinventor.components.runtime.ReplForm r14 = r14.form
            r15 = r1
            com.google.appinventor.components.runtime.ReplForm r15 = r15.form
            java.lang.String r16 = "AppInvHTTPD"
            r17 = 1801(0x709, float:2.524E-42)
            r18 = 1
            r0 = r18
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r18 = r0
            r22 = r18
            r18 = r22
            r19 = r22
            r20 = 0
            java.lang.String r21 = "Invalid HMAC"
            r19[r20] = r21
            r14.dispatchErrorOccurredEvent(r15, r16, r17, r18)
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "application/json"
            java.lang.String r19 = "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid MAC\"}"
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r1 = r14
            goto L_0x00e4
        L_0x0358:
            r14 = move-exception
            r9 = r14
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "Error working with hmac"
            r16 = r9
            int r14 = android.util.Log.e(r14, r15, r16)
            r14 = r1
            com.google.appinventor.components.runtime.ReplForm r14 = r14.form
            r15 = r1
            com.google.appinventor.components.runtime.ReplForm r15 = r15.form
            java.lang.String r16 = "AppInvHTTPD"
            r17 = 1801(0x709, float:2.524E-42)
            r18 = 1
            r0 = r18
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r18 = r0
            r22 = r18
            r18 = r22
            r19 = r22
            r20 = 0
            java.lang.String r21 = "Exception working on HMAC"
            r19[r20] = r21
            r14.dispatchErrorOccurredEvent(r15, r16, r17, r18)
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "text/plain"
            java.lang.String r19 = "NOT"
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r1 = r14
            goto L_0x00e4
        L_0x03a2:
            int r14 = xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT
            r15 = r8
            if (r14 == r15) goto L_0x03f6
            int r14 = xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT
            r15 = r8
            r16 = 1
            int r15 = r15 + 1
            if (r14 == r15) goto L_0x03f6
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "Seq does not match"
            int r14 = android.util.Log.e(r14, r15)
            r14 = r1
            com.google.appinventor.components.runtime.ReplForm r14 = r14.form
            r15 = r1
            com.google.appinventor.components.runtime.ReplForm r15 = r15.form
            java.lang.String r16 = "AppInvHTTPD"
            r17 = 1801(0x709, float:2.524E-42)
            r18 = 1
            r0 = r18
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r18 = r0
            r22 = r18
            r18 = r22
            r19 = r22
            r20 = 0
            java.lang.String r21 = "Invalid Seq"
            r19[r20] = r21
            r14.dispatchErrorOccurredEvent(r15, r16, r17, r18)
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "application/json"
            java.lang.String r19 = "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid Seq\"}"
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r1 = r14
            goto L_0x00e4
        L_0x03f6:
            int r14 = xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT
            r15 = r8
            r16 = 1
            int r15 = r15 + 1
            if (r14 != r15) goto L_0x0409
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "Seq Fixup Invoked"
            int r14 = android.util.Log.e(r14, r15)
        L_0x0409:
            r14 = r8
            r15 = 1
            int r14 = r14 + 1
            xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT = r14
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r22 = r14
            r14 = r22
            r15 = r22
            java.lang.String r16 = "(begin (require <com.google.youngandroid.runtime>) (process-repl-input "
            r15.<init>(r16)
            r15 = r2
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = " (begin "
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r3
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = " )))"
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            r3 = r14
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "To Eval: "
            r16 = r3
            java.lang.String r16 = java.lang.String.valueOf(r16)
            java.lang.String r15 = r15.concat(r16)
            int r14 = android.util.Log.d(r14, r15)
            r14 = r6
            java.lang.String r15 = "#f"
            boolean r14 = r14.equals(r15)     // Catch:{ Throwable -> 0x04f8 }
            if (r14 == 0) goto L_0x04ee
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "Skipping evaluation of #f"
            int r14 = android.util.Log.e(r14, r15)     // Catch:{ Throwable -> 0x04f8 }
        L_0x0462:
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response     // Catch:{ Throwable -> 0x04f8 }
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "application/json"
            r19 = 0
            java.lang.String r19 = com.google.appinventor.components.runtime.util.RetValManager.fetch(r19)     // Catch:{ Throwable -> 0x04f8 }
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)     // Catch:{ Throwable -> 0x04f8 }
            r9 = r14
        L_0x047c:
            r14 = r9
            java.lang.String r15 = "Access-Control-Allow-Origin"
            java.lang.String r16 = "*"
            r14.addHeader(r15, r16)
            r14 = r9
            java.lang.String r15 = "Access-Control-Allow-Headers"
            java.lang.String r16 = "origin, content-type"
            r14.addHeader(r15, r16)
            r14 = r9
            java.lang.String r15 = "Access-Control-Allow-Methods"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r9
            java.lang.String r15 = "Allow"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r9
            r1 = r14
            goto L_0x00e4
        L_0x04a8:
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "No HMAC Key"
            int r14 = android.util.Log.e(r14, r15)
            r14 = r1
            com.google.appinventor.components.runtime.ReplForm r14 = r14.form
            r15 = r1
            com.google.appinventor.components.runtime.ReplForm r15 = r15.form
            java.lang.String r16 = "AppInvHTTPD"
            r17 = 1801(0x709, float:2.524E-42)
            r18 = 1
            r0 = r18
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r18 = r0
            r22 = r18
            r18 = r22
            r19 = r22
            r20 = 0
            java.lang.String r21 = "No HMAC Key"
            r19[r20] = r21
            r14.dispatchErrorOccurredEvent(r15, r16, r17, r18)
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "application/json"
            java.lang.String r19 = "{\"status\" : \"BAD\", \"message\" : \"Security Error: No HMAC Key\"}"
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r1 = r14
            goto L_0x00e4
        L_0x04ee:
            r14 = r1
            gnu.expr.Language r14 = r14.f553hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ Throwable -> 0x04f8 }
            r15 = r3
            java.lang.Object r14 = r14.eval((java.lang.String) r15)     // Catch:{ Throwable -> 0x04f8 }
            goto L_0x0462
        L_0x04f8:
            r14 = move-exception
            r5 = r14
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "newblocks: Scheme Failure"
            r16 = r5
            int r14 = android.util.Log.e(r14, r15, r16)
            r14 = r2
            java.lang.String r15 = "BAD"
            r16 = r5
            java.lang.String r16 = r16.toString()
            com.google.appinventor.components.runtime.util.RetValManager.appendReturnValue(r14, r15, r16)
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "application/json"
            r19 = 0
            java.lang.String r19 = com.google.appinventor.components.runtime.util.RetValManager.fetch(r19)
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r9 = r14
            goto L_0x047c
        L_0x052f:
            r14 = r2
            java.lang.String r15 = "/_values"
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x0584
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "application/json"
            r19 = 1
            java.lang.String r19 = com.google.appinventor.components.runtime.util.RetValManager.fetch(r19)
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r22 = r14
            r14 = r22
            r15 = r22
            r7 = r15
            java.lang.String r15 = "Access-Control-Allow-Origin"
            java.lang.String r16 = "*"
            r14.addHeader(r15, r16)
            r14 = r7
            java.lang.String r15 = "Access-Control-Allow-Headers"
            java.lang.String r16 = "origin, content-type"
            r14.addHeader(r15, r16)
            r14 = r7
            java.lang.String r15 = "Access-Control-Allow-Methods"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r7
            java.lang.String r15 = "Allow"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r7
            r1 = r14
            goto L_0x00e4
        L_0x0584:
            r14 = r2
            java.lang.String r15 = "/_getversion"
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x0684
            r14 = r1
            com.google.appinventor.components.runtime.ReplForm r14 = r14.form     // Catch:{ NameNotFoundException -> 0x0668 }
            java.lang.String r14 = r14.getPackageName()     // Catch:{ NameNotFoundException -> 0x0668 }
            r8 = r14
            r14 = r1
            com.google.appinventor.components.runtime.ReplForm r14 = r14.form     // Catch:{ NameNotFoundException -> 0x0668 }
            android.content.pm.PackageManager r14 = r14.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0668 }
            r15 = r8
            r16 = 0
            android.content.pm.PackageInfo r14 = r14.getPackageInfo(r15, r16)     // Catch:{ NameNotFoundException -> 0x0668 }
            r2 = r14
            int r14 = android.os.Build.VERSION.SDK_INT     // Catch:{ NameNotFoundException -> 0x0668 }
            r15 = 5
            if (r14 < r15) goto L_0x0662
            java.lang.String r14 = "io.makeroid.companion"
            r15 = r1
            com.google.appinventor.components.runtime.ReplForm r15 = r15.form     // Catch:{ NameNotFoundException -> 0x0668 }
            java.lang.String r14 = com.google.appinventor.components.runtime.util.EclairUtil.getInstallerPackageName(r14, r15)     // Catch:{ NameNotFoundException -> 0x0668 }
            r3 = r14
        L_0x05b5:
            r14 = r2
            java.lang.String r14 = r14.versionName     // Catch:{ NameNotFoundException -> 0x0668 }
            r4 = r14
            r14 = r3
            if (r14 != 0) goto L_0x05c0
            java.lang.String r14 = "Not Known"
            r3 = r14
        L_0x05c0:
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response     // Catch:{ NameNotFoundException -> 0x0668 }
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "application/json"
            java.lang.StringBuilder r19 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x0668 }
            r22 = r19
            r19 = r22
            r20 = r22
            java.lang.String r21 = "{\"version\" : \""
            r20.<init>(r21)     // Catch:{ NameNotFoundException -> 0x0668 }
            r20 = r4
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ NameNotFoundException -> 0x0668 }
            java.lang.String r20 = "\", \"fingerprint\" : \""
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ NameNotFoundException -> 0x0668 }
            java.lang.String r20 = android.os.Build.FINGERPRINT     // Catch:{ NameNotFoundException -> 0x0668 }
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ NameNotFoundException -> 0x0668 }
            java.lang.String r20 = "\", \"installer\" : \""
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ NameNotFoundException -> 0x0668 }
            r20 = r3
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ NameNotFoundException -> 0x0668 }
            java.lang.String r20 = "\", \"package\" : \""
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ NameNotFoundException -> 0x0668 }
            r20 = r8
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ NameNotFoundException -> 0x0668 }
            java.lang.String r20 = "\", \"fqcn\" : true }"
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ NameNotFoundException -> 0x0668 }
            java.lang.String r19 = r19.toString()     // Catch:{ NameNotFoundException -> 0x0668 }
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)     // Catch:{ NameNotFoundException -> 0x0668 }
            r7 = r14
        L_0x061a:
            r14 = r7
            java.lang.String r15 = "Access-Control-Allow-Origin"
            java.lang.String r16 = "*"
            r14.addHeader(r15, r16)
            r14 = r7
            java.lang.String r15 = "Access-Control-Allow-Headers"
            java.lang.String r16 = "origin, content-type"
            r14.addHeader(r15, r16)
            r14 = r7
            java.lang.String r15 = "Access-Control-Allow-Methods"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r7
            java.lang.String r15 = "Allow"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r1
            boolean r14 = r14.secure
            if (r14 == 0) goto L_0x065e
            r14 = 1
            xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT = r14
            r14 = r1
            android.os.Handler r14 = r14.androidUIHandler
            com.google.appinventor.components.runtime.util.AppInvHTTPD$1 r15 = new com.google.appinventor.components.runtime.util.AppInvHTTPD$1
            r22 = r15
            r15 = r22
            r16 = r22
            r17 = r1
            r16.<init>(r17)
            boolean r14 = r14.post(r15)
        L_0x065e:
            r14 = r7
            r1 = r14
            goto L_0x00e4
        L_0x0662:
            java.lang.String r14 = "Not Known"
            r3 = r14
            goto L_0x05b5
        L_0x0668:
            r14 = move-exception
            r14.printStackTrace()
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "application/json"
            java.lang.String r19 = "{\"verison\" : \"Unknown\""
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r7 = r14
            goto L_0x061a
        L_0x0684:
            r14 = r2
            java.lang.String r15 = "/_update"
            boolean r14 = r14.equals(r15)
            if (r14 != 0) goto L_0x0698
            r14 = r2
            java.lang.String r15 = "/_install"
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x0909
        L_0x0698:
            r14 = r5
            java.lang.String r15 = "url"
            java.lang.String r16 = ""
            java.lang.String r14 = r14.getProperty(r15, r16)
            r7 = r14
            r14 = r5
            java.lang.String r15 = "mac"
            java.lang.String r16 = ""
            java.lang.String r14 = r14.getProperty(r15, r16)
            r8 = r14
            r14 = r7
            java.lang.String r15 = ""
            boolean r14 = r14.equals(r15)
            if (r14 != 0) goto L_0x08c1
            byte[] r14 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            if (r14 == 0) goto L_0x08c1
            r14 = r8
            java.lang.String r15 = ""
            boolean r14 = r14.equals(r15)
            if (r14 != 0) goto L_0x08c1
            javax.crypto.spec.SecretKeySpec r14 = new javax.crypto.spec.SecretKeySpec     // Catch:{ Exception -> 0x07f7 }
            r22 = r14
            r14 = r22
            r15 = r22
            byte[] r16 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ Exception -> 0x07f7 }
            java.lang.String r17 = "RAW"
            r15.<init>(r16, r17)     // Catch:{ Exception -> 0x07f7 }
            r3 = r14
            java.lang.String r14 = "HmacSHA1"
            javax.crypto.Mac r14 = javax.crypto.Mac.getInstance(r14)     // Catch:{ Exception -> 0x07f7 }
            r22 = r14
            r14 = r22
            r15 = r22
            r4 = r15
            r15 = r3
            r14.init(r15)     // Catch:{ Exception -> 0x07f7 }
            r14 = r4
            r15 = r7
            byte[] r15 = r15.getBytes()     // Catch:{ Exception -> 0x07f7 }
            byte[] r14 = r14.doFinal(r15)     // Catch:{ Exception -> 0x07f7 }
            r5 = r14
            java.lang.StringBuffer r14 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x07f7 }
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r5
            r0 = r16
            int r0 = r0.length     // Catch:{ Exception -> 0x07f7 }
            r16 = r0
            r17 = 1
            int r16 = r16 << 1
            r15.<init>(r16)     // Catch:{ Exception -> 0x07f7 }
            r6 = r14
            java.util.Formatter r14 = new java.util.Formatter     // Catch:{ Exception -> 0x07f7 }
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r6
            r15.<init>(r16)     // Catch:{ Exception -> 0x07f7 }
            r9 = r14
            r14 = r5
            int r14 = r14.length     // Catch:{ Exception -> 0x07f7 }
            r10 = r14
            r14 = 0
            r11 = r14
        L_0x0720:
            r14 = r11
            r15 = r10
            if (r14 >= r15) goto L_0x074c
            r14 = r5
            r15 = r11
            byte r14 = r14[r15]     // Catch:{ Exception -> 0x07f7 }
            r12 = r14
            r14 = r9
            java.lang.String r15 = "%02x"
            r16 = 1
            r0 = r16
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x07f7 }
            r16 = r0
            r22 = r16
            r16 = r22
            r17 = r22
            r18 = 0
            r19 = r12
            java.lang.Byte r19 = java.lang.Byte.valueOf(r19)     // Catch:{ Exception -> 0x07f7 }
            r17[r18] = r19     // Catch:{ Exception -> 0x07f7 }
            java.util.Formatter r14 = r14.format(r15, r16)     // Catch:{ Exception -> 0x07f7 }
            int r11 = r11 + 1
            goto L_0x0720
        L_0x074c:
            r14 = r6
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x07f7 }
            r2 = r14
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "Incoming Mac (update) = "
            r16 = r8
            java.lang.String r16 = java.lang.String.valueOf(r16)
            java.lang.String r15 = r15.concat(r16)
            int r14 = android.util.Log.d(r14, r15)
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "Computed Mac (update) = "
            r16 = r2
            java.lang.String r16 = java.lang.String.valueOf(r16)
            java.lang.String r15 = r15.concat(r16)
            int r14 = android.util.Log.d(r14, r15)
            r14 = r8
            r15 = r2
            boolean r14 = r14.equals(r15)
            if (r14 != 0) goto L_0x0870
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "Hmac does not match"
            int r14 = android.util.Log.e(r14, r15)
            r14 = r1
            com.google.appinventor.components.runtime.ReplForm r14 = r14.form
            r15 = r1
            com.google.appinventor.components.runtime.ReplForm r15 = r15.form
            java.lang.String r16 = "AppInvHTTPD"
            r17 = 1801(0x709, float:2.524E-42)
            r18 = 1
            r0 = r18
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r18 = r0
            r22 = r18
            r18 = r22
            r19 = r22
            r20 = 0
            java.lang.String r21 = "Invalid HMAC (update)"
            r19[r20] = r21
            r14.dispatchErrorOccurredEvent(r15, r16, r17, r18)
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "application/json"
            java.lang.String r19 = "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid MAC\"}"
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r22 = r14
            r14 = r22
            r15 = r22
            r3 = r15
            java.lang.String r15 = "Access-Control-Allow-Origin"
            java.lang.String r16 = "*"
            r14.addHeader(r15, r16)
            r14 = r3
            java.lang.String r15 = "Access-Control-Allow-Headers"
            java.lang.String r16 = "origin, content-type"
            r14.addHeader(r15, r16)
            r14 = r3
            java.lang.String r15 = "Access-Control-Allow-Methods"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r3
            java.lang.String r15 = "Allow"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r3
            r1 = r14
            goto L_0x00e4
        L_0x07f7:
            r14 = move-exception
            r3 = r14
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "Error verifying update"
            r16 = r3
            int r14 = android.util.Log.e(r14, r15, r16)
            r14 = r1
            com.google.appinventor.components.runtime.ReplForm r14 = r14.form
            r15 = r1
            com.google.appinventor.components.runtime.ReplForm r15 = r15.form
            java.lang.String r16 = "AppInvHTTPD"
            r17 = 1801(0x709, float:2.524E-42)
            r18 = 1
            r0 = r18
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r18 = r0
            r22 = r18
            r18 = r22
            r19 = r22
            r20 = 0
            java.lang.String r21 = "Exception working on HMAC for update"
            r19[r20] = r21
            r14.dispatchErrorOccurredEvent(r15, r16, r17, r18)
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "application/json"
            java.lang.String r19 = "{\"status\" : \"BAD\", \"message\" : \"Security Error: Exception processing MAC\"}"
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r22 = r14
            r14 = r22
            r15 = r22
            r4 = r15
            java.lang.String r15 = "Access-Control-Allow-Origin"
            java.lang.String r16 = "*"
            r14.addHeader(r15, r16)
            r14 = r4
            java.lang.String r15 = "Access-Control-Allow-Headers"
            java.lang.String r16 = "origin, content-type"
            r14.addHeader(r15, r16)
            r14 = r4
            java.lang.String r15 = "Access-Control-Allow-Methods"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r4
            java.lang.String r15 = "Allow"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r4
            r1 = r14
            goto L_0x00e4
        L_0x0870:
            r14 = r1
            r15 = r7
            r3 = r15
            com.google.appinventor.components.runtime.ReplForm r14 = r14.form
            r15 = r3
            com.google.appinventor.components.runtime.util.PackageInstaller.doPackageInstall(r14, r15)
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "application/json"
            java.lang.String r19 = "{\"status\" : \"OK\", \"message\" : \"Update Should Happen\"}"
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r22 = r14
            r14 = r22
            r15 = r22
            r3 = r15
            java.lang.String r15 = "Access-Control-Allow-Origin"
            java.lang.String r16 = "*"
            r14.addHeader(r15, r16)
            r14 = r3
            java.lang.String r15 = "Access-Control-Allow-Headers"
            java.lang.String r16 = "origin, content-type"
            r14.addHeader(r15, r16)
            r14 = r3
            java.lang.String r15 = "Access-Control-Allow-Methods"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r3
            java.lang.String r15 = "Allow"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r3
            r1 = r14
            goto L_0x00e4
        L_0x08c1:
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "application/json"
            java.lang.String r19 = "{\"status\" : \"BAD\", \"message\" : \"Missing Parameters\"}"
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r22 = r14
            r14 = r22
            r15 = r22
            r3 = r15
            java.lang.String r15 = "Access-Control-Allow-Origin"
            java.lang.String r16 = "*"
            r14.addHeader(r15, r16)
            r14 = r3
            java.lang.String r15 = "Access-Control-Allow-Headers"
            java.lang.String r16 = "origin, content-type"
            r14.addHeader(r15, r16)
            r14 = r3
            java.lang.String r15 = "Access-Control-Allow-Methods"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r3
            java.lang.String r15 = "Allow"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r3
            r1 = r14
            goto L_0x00e4
        L_0x0909:
            r14 = r2
            java.lang.String r15 = "/_package"
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x0a0d
            r14 = r5
            java.lang.String r15 = "package"
            r16 = 0
            java.lang.String r14 = r14.getProperty(r15, r16)
            r22 = r14
            r14 = r22
            r15 = r22
            r8 = r15
            if (r14 != 0) goto L_0x093f
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "text/plain"
            java.lang.String r19 = "NOT OK"
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r1 = r14
            goto L_0x00e4
        L_0x093f:
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r22 = r15
            r15 = r22
            r16 = r22
            r16.<init>()
            r16 = r1
            r0 = r16
            java.io.File r0 = r0.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r16 = r0
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r16 = "/"
            java.lang.StringBuilder r15 = r15.append(r16)
            r16 = r8
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r15 = r15.toString()
            int r14 = android.util.Log.d(r14, r15)
            android.content.Intent r14 = new android.content.Intent
            r22 = r14
            r14 = r22
            r15 = r22
            java.lang.String r16 = "android.intent.action.VIEW"
            r15.<init>(r16)
            r2 = r14
            java.io.File r14 = new java.io.File
            r22 = r14
            r14 = r22
            r15 = r22
            java.lang.StringBuilder r16 = new java.lang.StringBuilder
            r22 = r16
            r16 = r22
            r17 = r22
            r17.<init>()
            r17 = r1
            r0 = r17
            java.io.File r0 = r0.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r17 = r0
            java.lang.StringBuilder r16 = r16.append(r17)
            java.lang.String r17 = "/"
            java.lang.StringBuilder r16 = r16.append(r17)
            r17 = r8
            java.lang.StringBuilder r16 = r16.append(r17)
            java.lang.String r16 = r16.toString()
            r15.<init>(r16)
            android.net.Uri r14 = android.net.Uri.fromFile(r14)
            r3 = r14
            r14 = r2
            r15 = r3
            java.lang.String r16 = "application/vnd.android.package-archive"
            android.content.Intent r14 = r14.setDataAndType(r15, r16)
            r14 = r1
            com.google.appinventor.components.runtime.ReplForm r14 = r14.form
            r15 = r2
            r14.startActivity(r15)
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "text/plain"
            java.lang.String r19 = "OK"
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r22 = r14
            r14 = r22
            r15 = r22
            r7 = r15
            java.lang.String r15 = "Access-Control-Allow-Origin"
            java.lang.String r16 = "*"
            r14.addHeader(r15, r16)
            r14 = r7
            java.lang.String r15 = "Access-Control-Allow-Headers"
            java.lang.String r16 = "origin, content-type"
            r14.addHeader(r15, r16)
            r14 = r7
            java.lang.String r15 = "Access-Control-Allow-Methods"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r7
            java.lang.String r15 = "Allow"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r7
            r1 = r14
            goto L_0x00e4
        L_0x0a0d:
            r14 = r2
            java.lang.String r15 = "/_extensions"
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x0a20
            r14 = r1
            r15 = r5
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = r14.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.util.Properties) r15)
            r1 = r14
            goto L_0x00e4
        L_0x0a20:
            r14 = r3
            java.lang.String r15 = "PUT"
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x0bb2
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
            r7 = r14
            r14 = r6
            java.lang.String r15 = "content"
            r16 = 0
            java.lang.String r14 = r14.getProperty(r15, r16)
            r22 = r14
            r14 = r22
            r15 = r22
            r8 = r15
            if (r14 == 0) goto L_0x0b5c
            java.io.File r14 = new java.io.File
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r8
            r15.<init>(r16)
            r2 = r14
            r14 = r5
            java.lang.String r15 = "filename"
            r16 = 0
            java.lang.String r14 = r14.getProperty(r15, r16)
            r22 = r14
            r14 = r22
            r15 = r22
            r3 = r15
            if (r14 == 0) goto L_0x0a95
            r14 = r3
            java.lang.String r15 = ".."
            boolean r14 = r14.startsWith(r15)
            if (r14 != 0) goto L_0x0a7f
            r14 = r3
            java.lang.String r15 = ".."
            boolean r14 = r14.endsWith(r15)
            if (r14 != 0) goto L_0x0a7f
            r14 = r3
            java.lang.String r15 = "../"
            int r14 = r14.indexOf(r15)
            if (r14 < 0) goto L_0x0a95
        L_0x0a7f:
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = " Ignoring invalid filename: "
            r16 = r3
            java.lang.String r16 = java.lang.String.valueOf(r16)
            java.lang.String r15 = r15.concat(r16)
            int r14 = android.util.Log.d(r14, r15)
            r14 = 0
            r3 = r14
        L_0x0a95:
            r14 = r3
            if (r14 == 0) goto L_0x0b49
            java.io.File r14 = new java.io.File
            r22 = r14
            r14 = r22
            r15 = r22
            java.lang.StringBuilder r16 = new java.lang.StringBuilder
            r22 = r16
            r16 = r22
            r17 = r22
            r17.<init>()
            r17 = r1
            r0 = r17
            java.io.File r0 = r0.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r17 = r0
            java.lang.StringBuilder r16 = r16.append(r17)
            java.lang.String r17 = "/"
            java.lang.StringBuilder r16 = r16.append(r17)
            r17 = r3
            java.lang.StringBuilder r16 = r16.append(r17)
            java.lang.String r16 = r16.toString()
            r15.<init>(r16)
            r22 = r14
            r14 = r22
            r15 = r22
            r4 = r15
            java.io.File r14 = r14.getParentFile()
            r22 = r14
            r14 = r22
            r15 = r22
            r5 = r15
            boolean r14 = r14.exists()
            if (r14 != 0) goto L_0x0ae8
            r14 = r5
            boolean r14 = r14.mkdirs()
        L_0x0ae8:
            r14 = r2
            r15 = r4
            boolean r14 = r14.renameTo(r15)
            if (r14 != 0) goto L_0x0afa
            r14 = r2
            r15 = r4
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(r14, r15)
            r14 = r2
            boolean r14 = r14.delete()
        L_0x0afa:
            r14 = r7
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x0b6a
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "text/plain"
            java.lang.String r19 = "NOTOK"
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r22 = r14
            r14 = r22
            r15 = r22
            r2 = r15
            java.lang.String r15 = "Access-Control-Allow-Origin"
            java.lang.String r16 = "*"
            r14.addHeader(r15, r16)
            r14 = r2
            java.lang.String r15 = "Access-Control-Allow-Headers"
            java.lang.String r16 = "origin, content-type"
            r14.addHeader(r15, r16)
            r14 = r2
            java.lang.String r15 = "Access-Control-Allow-Methods"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r2
            java.lang.String r15 = "Allow"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r2
            r1 = r14
            goto L_0x00e4
        L_0x0b49:
            r14 = r2
            boolean r14 = r14.delete()
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "Received content without a file name!"
            int r14 = android.util.Log.e(r14, r15)
            java.lang.Boolean r14 = java.lang.Boolean.TRUE
            r7 = r14
            goto L_0x0afa
        L_0x0b5c:
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = "Received PUT without content."
            int r14 = android.util.Log.e(r14, r15)
            java.lang.Boolean r14 = java.lang.Boolean.TRUE
            r7 = r14
            goto L_0x0afa
        L_0x0b6a:
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "text/plain"
            java.lang.String r19 = "OK"
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r22 = r14
            r14 = r22
            r15 = r22
            r2 = r15
            java.lang.String r15 = "Access-Control-Allow-Origin"
            java.lang.String r16 = "*"
            r14.addHeader(r15, r16)
            r14 = r2
            java.lang.String r15 = "Access-Control-Allow-Headers"
            java.lang.String r16 = "origin, content-type"
            r14.addHeader(r15, r16)
            r14 = r2
            java.lang.String r15 = "Access-Control-Allow-Methods"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r2
            java.lang.String r15 = "Allow"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r2
            r1 = r14
            goto L_0x00e4
        L_0x0bb2:
            r14 = r4
            java.util.Enumeration r14 = r14.propertyNames()
            r7 = r14
        L_0x0bb8:
            r14 = r7
            boolean r14 = r14.hasMoreElements()
            if (r14 == 0) goto L_0x0c01
            r14 = r7
            java.lang.Object r14 = r14.nextElement()
            java.lang.String r14 = (java.lang.String) r14
            r8 = r14
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r22 = r15
            r15 = r22
            r16 = r22
            java.lang.String r17 = "  HDR: '"
            r16.<init>(r17)
            r16 = r8
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r16 = "' = '"
            java.lang.StringBuilder r15 = r15.append(r16)
            r16 = r4
            r17 = r8
            java.lang.String r16 = r16.getProperty(r17)
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r16 = "'"
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r15 = r15.toString()
            int r14 = android.util.Log.d(r14, r15)
            goto L_0x0bb8
        L_0x0c01:
            r14 = r5
            java.util.Enumeration r14 = r14.propertyNames()
            r7 = r14
        L_0x0c07:
            r14 = r7
            boolean r14 = r14.hasMoreElements()
            if (r14 == 0) goto L_0x0c50
            r14 = r7
            java.lang.Object r14 = r14.nextElement()
            java.lang.String r14 = (java.lang.String) r14
            r8 = r14
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r22 = r15
            r15 = r22
            r16 = r22
            java.lang.String r17 = "  PRM: '"
            r16.<init>(r17)
            r16 = r8
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r16 = "' = '"
            java.lang.StringBuilder r15 = r15.append(r16)
            r16 = r5
            r17 = r8
            java.lang.String r16 = r16.getProperty(r17)
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r16 = "'"
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r15 = r15.toString()
            int r14 = android.util.Log.d(r14, r15)
            goto L_0x0c07
        L_0x0c50:
            r14 = r6
            java.util.Enumeration r14 = r14.propertyNames()
            r22 = r14
            r14 = r22
            r15 = r22
            r7 = r15
            boolean r14 = r14.hasMoreElements()
            if (r14 == 0) goto L_0x0d8a
            r14 = r7
            java.lang.Object r14 = r14.nextElement()
            java.lang.String r14 = (java.lang.String) r14
            r8 = r14
            r14 = r6
            r15 = r8
            java.lang.String r14 = r14.getProperty(r15)
            r2 = r14
            r14 = r5
            r15 = r8
            java.lang.String r14 = r14.getProperty(r15)
            r22 = r14
            r14 = r22
            r15 = r22
            r3 = r15
            java.lang.String r15 = ".."
            boolean r14 = r14.startsWith(r15)
            if (r14 != 0) goto L_0x0c9b
            r14 = r3
            java.lang.String r15 = ".."
            boolean r14 = r14.endsWith(r15)
            if (r14 != 0) goto L_0x0c9b
            r14 = r3
            java.lang.String r15 = "../"
            int r14 = r14.indexOf(r15)
            if (r14 < 0) goto L_0x0cb1
        L_0x0c9b:
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.String r15 = " Ignoring invalid filename: "
            r16 = r3
            java.lang.String r16 = java.lang.String.valueOf(r16)
            java.lang.String r15 = r15.concat(r16)
            int r14 = android.util.Log.d(r14, r15)
            r14 = 0
            r3 = r14
        L_0x0cb1:
            java.io.File r14 = new java.io.File
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r2
            r15.<init>(r16)
            r4 = r14
            r14 = r3
            if (r14 != 0) goto L_0x0d42
            r14 = r4
            boolean r14 = r14.delete()
        L_0x0cc7:
            java.lang.String r14 = "AppInvHTTPD"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r22 = r15
            r15 = r22
            r16 = r22
            java.lang.String r17 = " UPLOADED: '"
            r16.<init>(r17)
            r16 = r3
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r16 = "' was at '"
            java.lang.StringBuilder r15 = r15.append(r16)
            r16 = r2
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r16 = "'"
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r15 = r15.toString()
            int r14 = android.util.Log.d(r14, r15)
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r22 = r14
            r14 = r22
            r15 = r22
            r16 = r1
            java.lang.String r17 = "200 OK"
            java.lang.String r18 = "text/plain"
            java.lang.String r19 = "OK"
            r15.<init>((com.google.appinventor.components.runtime.util.NanoHTTPD) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19)
            r22 = r14
            r14 = r22
            r15 = r22
            r5 = r15
            java.lang.String r15 = "Access-Control-Allow-Origin"
            java.lang.String r16 = "*"
            r14.addHeader(r15, r16)
            r14 = r5
            java.lang.String r15 = "Access-Control-Allow-Headers"
            java.lang.String r16 = "origin, content-type"
            r14.addHeader(r15, r16)
            r14 = r5
            java.lang.String r15 = "Access-Control-Allow-Methods"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r5
            java.lang.String r15 = "Allow"
            java.lang.String r16 = "POST,OPTIONS,GET,HEAD,PUT"
            r14.addHeader(r15, r16)
            r14 = r5
            r1 = r14
            goto L_0x00e4
        L_0x0d42:
            java.io.File r14 = new java.io.File
            r22 = r14
            r14 = r22
            r15 = r22
            java.lang.StringBuilder r16 = new java.lang.StringBuilder
            r22 = r16
            r16 = r22
            r17 = r22
            r17.<init>()
            r17 = r1
            r0 = r17
            java.io.File r0 = r0.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r17 = r0
            java.lang.StringBuilder r16 = r16.append(r17)
            java.lang.String r17 = "/"
            java.lang.StringBuilder r16 = r16.append(r17)
            r17 = r3
            java.lang.StringBuilder r16 = r16.append(r17)
            java.lang.String r16 = r16.toString()
            r15.<init>(r16)
            r5 = r14
            r14 = r4
            r15 = r5
            boolean r14 = r14.renameTo(r15)
            if (r14 != 0) goto L_0x0cc7
            r14 = r4
            r15 = r5
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(r14, r15)
            r14 = r4
            boolean r14 = r14.delete()
            goto L_0x0cc7
        L_0x0d8a:
            r14 = r1
            r15 = r2
            r16 = r4
            r17 = r1
            r0 = r17
            java.io.File r0 = r0.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r17 = r0
            r18 = 1
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r14 = r14.serveFile(r15, r16, r17, r18)
            r1 = r14
            goto L_0x00e4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.AppInvHTTPD.serve(java.lang.String, java.lang.String, java.util.Properties, java.util.Properties, java.util.Properties, java.net.Socket):com.google.appinventor.components.runtime.util.NanoHTTPD$Response");
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(File file, File file2) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        File file3 = file2;
        try {
            new FileInputStream(file);
            FileInputStream fileInputStream2 = fileInputStream;
            new FileOutputStream(file3);
            FileOutputStream fileOutputStream2 = fileOutputStream;
            byte[] bArr = new byte[32768];
            while (true) {
                int read = fileInputStream2.read(bArr);
                int i = read;
                if (read > 0) {
                    fileOutputStream2.write(bArr, 0, i);
                } else {
                    fileInputStream2.close();
                    fileOutputStream2.close();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private NanoHTTPD.Response hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Properties properties) {
        JSONArray jSONArray;
        List list;
        NanoHTTPD.Response response;
        try {
            new JSONArray(properties.getProperty("extensions", "[]"));
            JSONArray jSONArray2 = jSONArray;
            new ArrayList();
            List list2 = list;
            for (int i = 0; i < jSONArray2.length(); i++) {
                String optString = jSONArray2.optString(i);
                String str = optString;
                if (optString == null) {
                    return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Invalid JSON content at index ".concat(String.valueOf(i)));
                }
                boolean add = list2.add(str);
            }
            try {
                this.form.loadComponents(list2);
                new NanoHTTPD.Response((NanoHTTPD) this, NanoHTTPD.HTTP_OK, "text/plain", "OK");
                return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(response);
            } catch (Exception e) {
                return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Throwable) e);
            }
        } catch (JSONException e2) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Throwable) e2);
        }
    }

    private NanoHTTPD.Response hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
        JSONObject jSONObject;
        NanoHTTPD.Response response;
        String str2 = str;
        new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        try {
            JSONObject put = jSONObject2.put(NotificationCompat.CATEGORY_STATUS, "BAD");
            JSONObject put2 = jSONObject2.put("message", str2);
        } catch (JSONException e) {
            int wtf = Log.wtf("AppInvHTTPD", "Unable to write basic JSON content", e);
        }
        new NanoHTTPD.Response((NanoHTTPD) this, NanoHTTPD.HTTP_OK, "application/json", jSONObject2.toString());
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(response);
    }

    private NanoHTTPD.Response hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Throwable th) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(th.toString());
    }

    private static NanoHTTPD.Response hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(NanoHTTPD.Response response) {
        NanoHTTPD.Response response2 = response;
        response2.addHeader("Access-Control-Allow-Origin", "*");
        response2.addHeader("Access-Control-Allow-Headers", "origin, content-type");
        response2.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
        response2.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
        return response2;
    }

    public static void setHmacKey(String str) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = str.getBytes();
        xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT = 1;
    }

    public void resetSeq() {
        xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT = 1;
    }
}
