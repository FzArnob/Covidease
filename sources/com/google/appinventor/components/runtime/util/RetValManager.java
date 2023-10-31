package com.google.appinventor.components.runtime.util;

import android.support.p000v4.app.NotificationCompat;
import android.util.Log;
import com.google.appinventor.components.runtime.ReplForm;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RetValManager {
    private static ArrayList<JSONObject> qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
    private static final Object semaphore;

    static {
        Object obj;
        ArrayList<JSONObject> arrayList;
        new Object();
        semaphore = obj;
        new ArrayList<>(10);
        qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = arrayList;
    }

    private RetValManager() {
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void appendReturnValue(java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            r0 = r9
            r1 = r10
            r2 = r11
            java.lang.Object r5 = semaphore
            r8 = r5
            r5 = r8
            r6 = r8
            r3 = r6
            monitor-enter(r5)
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ all -> 0x006c }
            r8 = r5
            r5 = r8
            r6 = r8
            r6.<init>()     // Catch:{ all -> 0x006c }
            r4 = r5
            r5 = r4
            java.lang.String r6 = "status"
            r7 = r1
            org.json.JSONObject r5 = r5.put(r6, r7)     // Catch:{ JSONException -> 0x0053 }
            r5 = r4
            java.lang.String r6 = "type"
            java.lang.String r7 = "return"
            org.json.JSONObject r5 = r5.put(r6, r7)     // Catch:{ JSONException -> 0x0053 }
            r5 = r4
            java.lang.String r6 = "value"
            r7 = r2
            org.json.JSONObject r5 = r5.put(r6, r7)     // Catch:{ JSONException -> 0x0053 }
            r5 = r4
            java.lang.String r6 = "blockid"
            r7 = r0
            org.json.JSONObject r5 = r5.put(r6, r7)     // Catch:{ JSONException -> 0x0053 }
            java.util.ArrayList<org.json.JSONObject> r5 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x006c }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x006c }
            r0 = r5
            java.util.ArrayList<org.json.JSONObject> r5 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x006c }
            r6 = r4
            boolean r5 = r5.add(r6)     // Catch:{ all -> 0x006c }
            boolean r5 = com.google.appinventor.components.runtime.PhoneStatus.getUseWebRTC()     // Catch:{ all -> 0x006c }
            if (r5 == 0) goto L_0x0063
            n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv()     // Catch:{ all -> 0x006c }
        L_0x0050:
            r5 = r3
            monitor-exit(r5)     // Catch:{ all -> 0x006c }
        L_0x0052:
            return
        L_0x0053:
            r5 = move-exception
            r0 = r5
            java.lang.String r5 = "RetValManager"
            java.lang.String r6 = "Error building retval"
            r7 = r0
            int r5 = android.util.Log.e(r5, r6, r7)     // Catch:{ all -> 0x006c }
            r5 = r3
            monitor-exit(r5)     // Catch:{ all -> 0x006c }
            goto L_0x0052
        L_0x0063:
            r5 = r0
            if (r5 == 0) goto L_0x0050
            java.lang.Object r5 = semaphore     // Catch:{ all -> 0x006c }
            r5.notifyAll()     // Catch:{ all -> 0x006c }
            goto L_0x0050
        L_0x006c:
            r5 = move-exception
            r0 = r5
            r5 = r3
            monitor-exit(r5)     // Catch:{ all -> 0x006c }
            r5 = r0
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.RetValManager.appendReturnValue(java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void sendError(java.lang.String r7) {
        /*
            r0 = r7
            java.lang.Object r3 = semaphore
            r6 = r3
            r3 = r6
            r4 = r6
            r1 = r4
            monitor-enter(r3)
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ all -> 0x0063 }
            r6 = r3
            r3 = r6
            r4 = r6
            r4.<init>()     // Catch:{ all -> 0x0063 }
            r2 = r3
            r3 = r2
            java.lang.String r4 = "status"
            java.lang.String r5 = "OK"
            org.json.JSONObject r3 = r3.put(r4, r5)     // Catch:{ JSONException -> 0x004a }
            r3 = r2
            java.lang.String r4 = "type"
            java.lang.String r5 = "error"
            org.json.JSONObject r3 = r3.put(r4, r5)     // Catch:{ JSONException -> 0x004a }
            r3 = r2
            java.lang.String r4 = "value"
            r5 = r0
            org.json.JSONObject r3 = r3.put(r4, r5)     // Catch:{ JSONException -> 0x004a }
            java.util.ArrayList<org.json.JSONObject> r3 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x0063 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0063 }
            r0 = r3
            java.util.ArrayList<org.json.JSONObject> r3 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x0063 }
            r4 = r2
            boolean r3 = r3.add(r4)     // Catch:{ all -> 0x0063 }
            boolean r3 = com.google.appinventor.components.runtime.PhoneStatus.getUseWebRTC()     // Catch:{ all -> 0x0063 }
            if (r3 == 0) goto L_0x005a
            n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv()     // Catch:{ all -> 0x0063 }
        L_0x0047:
            r3 = r1
            monitor-exit(r3)     // Catch:{ all -> 0x0063 }
        L_0x0049:
            return
        L_0x004a:
            r3 = move-exception
            r0 = r3
            java.lang.String r3 = "RetValManager"
            java.lang.String r4 = "Error building retval"
            r5 = r0
            int r3 = android.util.Log.e(r3, r4, r5)     // Catch:{ all -> 0x0063 }
            r3 = r1
            monitor-exit(r3)     // Catch:{ all -> 0x0063 }
            goto L_0x0049
        L_0x005a:
            r3 = r0
            if (r3 == 0) goto L_0x0047
            java.lang.Object r3 = semaphore     // Catch:{ all -> 0x0063 }
            r3.notifyAll()     // Catch:{ all -> 0x0063 }
            goto L_0x0047
        L_0x0063:
            r3 = move-exception
            r0 = r3
            r3 = r1
            monitor-exit(r3)     // Catch:{ all -> 0x0063 }
            r3 = r0
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.RetValManager.sendError(java.lang.String):void");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void pushScreen(java.lang.String r8, java.lang.Object r9) {
        /*
            r0 = r8
            r1 = r9
            java.lang.Object r4 = semaphore
            r7 = r4
            r4 = r7
            r5 = r7
            r2 = r5
            monitor-enter(r4)
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ all -> 0x0074 }
            r7 = r4
            r4 = r7
            r5 = r7
            r5.<init>()     // Catch:{ all -> 0x0074 }
            r3 = r4
            r4 = r3
            java.lang.String r5 = "status"
            java.lang.String r6 = "OK"
            org.json.JSONObject r4 = r4.put(r5, r6)     // Catch:{ JSONException -> 0x005b }
            r4 = r3
            java.lang.String r5 = "type"
            java.lang.String r6 = "pushScreen"
            org.json.JSONObject r4 = r4.put(r5, r6)     // Catch:{ JSONException -> 0x005b }
            r4 = r3
            java.lang.String r5 = "screen"
            r6 = r0
            org.json.JSONObject r4 = r4.put(r5, r6)     // Catch:{ JSONException -> 0x005b }
            r4 = r1
            if (r4 == 0) goto L_0x0041
            r4 = r3
            java.lang.String r5 = "value"
            r6 = r1
            java.lang.String r6 = r6.toString()     // Catch:{ JSONException -> 0x005b }
            org.json.JSONObject r4 = r4.put(r5, r6)     // Catch:{ JSONException -> 0x005b }
        L_0x0041:
            java.util.ArrayList<org.json.JSONObject> r4 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x0074 }
            boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x0074 }
            r0 = r4
            java.util.ArrayList<org.json.JSONObject> r4 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x0074 }
            r5 = r3
            boolean r4 = r4.add(r5)     // Catch:{ all -> 0x0074 }
            boolean r4 = com.google.appinventor.components.runtime.PhoneStatus.getUseWebRTC()     // Catch:{ all -> 0x0074 }
            if (r4 == 0) goto L_0x006b
            n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv()     // Catch:{ all -> 0x0074 }
        L_0x0058:
            r4 = r2
            monitor-exit(r4)     // Catch:{ all -> 0x0074 }
        L_0x005a:
            return
        L_0x005b:
            r4 = move-exception
            r0 = r4
            java.lang.String r4 = "RetValManager"
            java.lang.String r5 = "Error building retval"
            r6 = r0
            int r4 = android.util.Log.e(r4, r5, r6)     // Catch:{ all -> 0x0074 }
            r4 = r2
            monitor-exit(r4)     // Catch:{ all -> 0x0074 }
            goto L_0x005a
        L_0x006b:
            r4 = r0
            if (r4 == 0) goto L_0x0058
            java.lang.Object r4 = semaphore     // Catch:{ all -> 0x0074 }
            r4.notifyAll()     // Catch:{ all -> 0x0074 }
            goto L_0x0058
        L_0x0074:
            r4 = move-exception
            r0 = r4
            r4 = r2
            monitor-exit(r4)     // Catch:{ all -> 0x0074 }
            r4 = r0
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.RetValManager.pushScreen(java.lang.String, java.lang.Object):void");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void popScreen(java.lang.String r7) {
        /*
            r0 = r7
            java.lang.Object r3 = semaphore
            r6 = r3
            r3 = r6
            r4 = r6
            r1 = r4
            monitor-enter(r3)
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ all -> 0x006a }
            r6 = r3
            r3 = r6
            r4 = r6
            r4.<init>()     // Catch:{ all -> 0x006a }
            r2 = r3
            r3 = r2
            java.lang.String r4 = "status"
            java.lang.String r5 = "OK"
            org.json.JSONObject r3 = r3.put(r4, r5)     // Catch:{ JSONException -> 0x0051 }
            r3 = r2
            java.lang.String r4 = "type"
            java.lang.String r5 = "popScreen"
            org.json.JSONObject r3 = r3.put(r4, r5)     // Catch:{ JSONException -> 0x0051 }
            r3 = r0
            if (r3 == 0) goto L_0x0037
            r3 = r2
            java.lang.String r4 = "value"
            r5 = r0
            java.lang.String r5 = r5.toString()     // Catch:{ JSONException -> 0x0051 }
            org.json.JSONObject r3 = r3.put(r4, r5)     // Catch:{ JSONException -> 0x0051 }
        L_0x0037:
            java.util.ArrayList<org.json.JSONObject> r3 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x006a }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x006a }
            r0 = r3
            java.util.ArrayList<org.json.JSONObject> r3 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x006a }
            r4 = r2
            boolean r3 = r3.add(r4)     // Catch:{ all -> 0x006a }
            boolean r3 = com.google.appinventor.components.runtime.PhoneStatus.getUseWebRTC()     // Catch:{ all -> 0x006a }
            if (r3 == 0) goto L_0x0061
            n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv()     // Catch:{ all -> 0x006a }
        L_0x004e:
            r3 = r1
            monitor-exit(r3)     // Catch:{ all -> 0x006a }
        L_0x0050:
            return
        L_0x0051:
            r3 = move-exception
            r0 = r3
            java.lang.String r3 = "RetValManager"
            java.lang.String r4 = "Error building retval"
            r5 = r0
            int r3 = android.util.Log.e(r3, r4, r5)     // Catch:{ all -> 0x006a }
            r3 = r1
            monitor-exit(r3)     // Catch:{ all -> 0x006a }
            goto L_0x0050
        L_0x0061:
            r3 = r0
            if (r3 == 0) goto L_0x004e
            java.lang.Object r3 = semaphore     // Catch:{ all -> 0x006a }
            r3.notifyAll()     // Catch:{ all -> 0x006a }
            goto L_0x004e
        L_0x006a:
            r3 = move-exception
            r0 = r3
            r3 = r1
            monitor-exit(r3)     // Catch:{ all -> 0x006a }
            r3 = r0
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.RetValManager.popScreen(java.lang.String):void");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void assetTransferred(java.lang.String r7) {
        /*
            r0 = r7
            java.lang.Object r3 = semaphore
            r6 = r3
            r3 = r6
            r4 = r6
            r1 = r4
            monitor-enter(r3)
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ all -> 0x006a }
            r6 = r3
            r3 = r6
            r4 = r6
            r4.<init>()     // Catch:{ all -> 0x006a }
            r2 = r3
            r3 = r2
            java.lang.String r4 = "status"
            java.lang.String r5 = "OK"
            org.json.JSONObject r3 = r3.put(r4, r5)     // Catch:{ JSONException -> 0x0051 }
            r3 = r2
            java.lang.String r4 = "type"
            java.lang.String r5 = "assetTransferred"
            org.json.JSONObject r3 = r3.put(r4, r5)     // Catch:{ JSONException -> 0x0051 }
            r3 = r0
            if (r3 == 0) goto L_0x0037
            r3 = r2
            java.lang.String r4 = "value"
            r5 = r0
            java.lang.String r5 = r5.toString()     // Catch:{ JSONException -> 0x0051 }
            org.json.JSONObject r3 = r3.put(r4, r5)     // Catch:{ JSONException -> 0x0051 }
        L_0x0037:
            java.util.ArrayList<org.json.JSONObject> r3 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x006a }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x006a }
            r0 = r3
            java.util.ArrayList<org.json.JSONObject> r3 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x006a }
            r4 = r2
            boolean r3 = r3.add(r4)     // Catch:{ all -> 0x006a }
            boolean r3 = com.google.appinventor.components.runtime.PhoneStatus.getUseWebRTC()     // Catch:{ all -> 0x006a }
            if (r3 == 0) goto L_0x0061
            n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv()     // Catch:{ all -> 0x006a }
        L_0x004e:
            r3 = r1
            monitor-exit(r3)     // Catch:{ all -> 0x006a }
        L_0x0050:
            return
        L_0x0051:
            r3 = move-exception
            r0 = r3
            java.lang.String r3 = "RetValManager"
            java.lang.String r4 = "Error building retval"
            r5 = r0
            int r3 = android.util.Log.e(r3, r4, r5)     // Catch:{ all -> 0x006a }
            r3 = r1
            monitor-exit(r3)     // Catch:{ all -> 0x006a }
            goto L_0x0050
        L_0x0061:
            r3 = r0
            if (r3 == 0) goto L_0x004e
            java.lang.Object r3 = semaphore     // Catch:{ all -> 0x006a }
            r3.notifyAll()     // Catch:{ all -> 0x006a }
            goto L_0x004e
        L_0x006a:
            r3 = move-exception
            r0 = r3
            r3 = r1
            monitor-exit(r3)     // Catch:{ all -> 0x006a }
            r3 = r0
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.RetValManager.assetTransferred(java.lang.String):void");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void extensionsLoaded() {
        /*
            java.lang.Object r3 = semaphore
            r6 = r3
            r3 = r6
            r4 = r6
            r0 = r4
            monitor-enter(r3)
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ all -> 0x0059 }
            r6 = r3
            r3 = r6
            r4 = r6
            r4.<init>()     // Catch:{ all -> 0x0059 }
            r1 = r3
            r3 = r1
            java.lang.String r4 = "status"
            java.lang.String r5 = "OK"
            org.json.JSONObject r3 = r3.put(r4, r5)     // Catch:{ JSONException -> 0x0040 }
            r3 = r1
            java.lang.String r4 = "type"
            java.lang.String r5 = "extensionsLoaded"
            org.json.JSONObject r3 = r3.put(r4, r5)     // Catch:{ JSONException -> 0x0040 }
            java.util.ArrayList<org.json.JSONObject> r3 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x0059 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0059 }
            r2 = r3
            java.util.ArrayList<org.json.JSONObject> r3 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x0059 }
            r4 = r1
            boolean r3 = r3.add(r4)     // Catch:{ all -> 0x0059 }
            boolean r3 = com.google.appinventor.components.runtime.PhoneStatus.getUseWebRTC()     // Catch:{ all -> 0x0059 }
            if (r3 == 0) goto L_0x0050
            n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv()     // Catch:{ all -> 0x0059 }
        L_0x003d:
            r3 = r0
            monitor-exit(r3)     // Catch:{ all -> 0x0059 }
        L_0x003f:
            return
        L_0x0040:
            r3 = move-exception
            r2 = r3
            java.lang.String r3 = "RetValManager"
            java.lang.String r4 = "Error building retval"
            r5 = r2
            int r3 = android.util.Log.e(r3, r4, r5)     // Catch:{ all -> 0x0059 }
            r3 = r0
            monitor-exit(r3)     // Catch:{ all -> 0x0059 }
            goto L_0x003f
        L_0x0050:
            r3 = r2
            if (r3 == 0) goto L_0x003d
            java.lang.Object r3 = semaphore     // Catch:{ all -> 0x0059 }
            r3.notifyAll()     // Catch:{ all -> 0x0059 }
            goto L_0x003d
        L_0x0059:
            r3 = move-exception
            r1 = r3
            r3 = r0
            monitor-exit(r3)     // Catch:{ all -> 0x0059 }
            r3 = r1
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.RetValManager.extensionsLoaded():void");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String fetch(boolean r9) {
        /*
            r0 = r9
            long r4 = java.lang.System.currentTimeMillis()
            r1 = r4
            java.lang.Object r4 = semaphore
            r8 = r4
            r4 = r8
            r5 = r8
            r3 = r5
            monitor-enter(r4)
        L_0x000d:
            java.util.ArrayList<org.json.JSONObject> r4 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x0078 }
            boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x0078 }
            if (r4 == 0) goto L_0x002e
            r4 = r0
            if (r4 == 0) goto L_0x002e
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0078 }
            r6 = r1
            long r4 = r4 - r6
            r6 = 9900(0x26ac, double:4.8912E-320)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 > 0) goto L_0x002e
            java.lang.Object r4 = semaphore     // Catch:{ InterruptedException -> 0x002c }
            r5 = 10000(0x2710, double:4.9407E-320)
            r4.wait(r5)     // Catch:{ InterruptedException -> 0x002c }
            goto L_0x000d
        L_0x002c:
            r4 = move-exception
            goto L_0x000d
        L_0x002e:
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ all -> 0x0078 }
            r8 = r4
            r4 = r8
            r5 = r8
            java.util.ArrayList<org.json.JSONObject> r6 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x0078 }
            r5.<init>(r6)     // Catch:{ all -> 0x0078 }
            r0 = r4
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ all -> 0x0078 }
            r8 = r4
            r4 = r8
            r5 = r8
            r5.<init>()     // Catch:{ all -> 0x0078 }
            r1 = r4
            r4 = r1
            java.lang.String r5 = "status"
            java.lang.String r6 = "OK"
            org.json.JSONObject r4 = r4.put(r5, r6)     // Catch:{ JSONException -> 0x0064 }
            r4 = r1
            java.lang.String r5 = "values"
            r6 = r0
            org.json.JSONObject r4 = r4.put(r5, r6)     // Catch:{ JSONException -> 0x0064 }
            java.util.ArrayList<org.json.JSONObject> r4 = qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE     // Catch:{ all -> 0x0078 }
            r4.clear()     // Catch:{ all -> 0x0078 }
            r4 = r1
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0078 }
            r5 = r3
            monitor-exit(r5)     // Catch:{ all -> 0x0078 }
            r0 = r4
        L_0x0063:
            return r0
        L_0x0064:
            r4 = move-exception
            r0 = r4
            java.lang.String r4 = "RetValManager"
            java.lang.String r5 = "Error fetching retvals"
            r6 = r0
            int r4 = android.util.Log.e(r4, r5, r6)     // Catch:{ all -> 0x0078 }
            java.lang.String r4 = "{\"status\" : \"BAD\", \"message\" : \"Failure in RetValManager\"}"
            r5 = r3
            monitor-exit(r5)     // Catch:{ all -> 0x0078 }
            r0 = r4
            goto L_0x0063
        L_0x0078:
            r4 = move-exception
            r0 = r4
            r4 = r3
            monitor-exit(r4)     // Catch:{ all -> 0x0078 }
            r4 = r0
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.RetValManager.fetch(boolean):java.lang.String");
    }

    private static void n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv() {
        JSONObject jSONObject;
        Object obj;
        try {
            new JSONObject();
            JSONObject jSONObject2 = jSONObject;
            JSONObject jSONObject3 = jSONObject2;
            JSONObject put = jSONObject2.put(NotificationCompat.CATEGORY_STATUS, "OK");
            new JSONArray(qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE);
            JSONObject put2 = jSONObject3.put("values", obj);
            ReplForm.returnRetvals(jSONObject3.toString());
            qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.clear();
        } catch (JSONException e) {
            int e2 = Log.e("RetValManager", "Error building retval", e);
        }
    }
}
