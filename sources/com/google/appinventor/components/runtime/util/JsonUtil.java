package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import gnu.lists.FString;
import gnu.math.IntFraction;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonUtil {
    private static final String BINFILE_DIR = "/AppInventorBinaries";
    private static String LOG_TAG = "JsonUtil";

    private JsonUtil() {
    }

    public static List<String> getStringListFromJsonArray(JSONArray jSONArray) throws JSONException {
        List<String> list;
        JSONArray jSONArray2 = jSONArray;
        new ArrayList();
        List<String> list2 = list;
        for (int i = 0; i < jSONArray2.length(); i++) {
            boolean add = list2.add(jSONArray2.getString(i));
        }
        return list2;
    }

    @Deprecated
    public static List<Object> getListFromJsonArray(JSONArray jSONArray) throws JSONException {
        return getListFromJsonArray(jSONArray, false);
    }

    public static List<Object> getListFromJsonArray(JSONArray jSONArray, boolean z) throws JSONException {
        List<Object> list;
        JSONArray jSONArray2 = jSONArray;
        boolean z2 = z;
        new ArrayList();
        List<Object> list2 = list;
        for (int i = 0; i < jSONArray2.length(); i++) {
            boolean add = list2.add(convertJsonItem(jSONArray2.get(i), z2));
        }
        return list2;
    }

    public static List<Object> getListFromJsonObject(JSONObject jSONObject) throws JSONException {
        List<Object> list;
        List list2;
        List list3;
        JSONObject jSONObject2 = jSONObject;
        new ArrayList();
        List<Object> list4 = list;
        Iterator<String> keys = jSONObject2.keys();
        new ArrayList();
        List<String> list5 = list2;
        while (keys.hasNext()) {
            boolean add = list5.add(keys.next());
        }
        Collections.sort(list5);
        for (String str : list5) {
            new ArrayList();
            List list6 = list3;
            List list7 = list6;
            boolean add2 = list6.add(str);
            boolean add3 = list7.add(convertJsonItem(jSONObject2.get(str), false));
            boolean add4 = list4.add(list7);
        }
        return list4;
    }

    public static YailDictionary getDictionaryFromJsonObject(JSONObject jSONObject) throws JSONException {
        YailDictionary yailDictionary;
        TreeSet treeSet;
        JSONObject jSONObject2 = jSONObject;
        new YailDictionary();
        YailDictionary yailDictionary2 = yailDictionary;
        new TreeSet();
        TreeSet treeSet2 = treeSet;
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            boolean add = treeSet2.add(keys.next());
        }
        Iterator it = treeSet2.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            Object put = yailDictionary2.put(str, convertJsonItem(jSONObject2.get(str), true));
        }
        return yailDictionary2;
    }

    @Deprecated
    public static Object convertJsonItem(Object obj) throws JSONException {
        return convertJsonItem(obj, false);
    }

    public static Object convertJsonItem(Object obj, boolean z) throws JSONException {
        Object obj2 = obj;
        boolean z2 = z;
        if (obj2 == null) {
            return "null";
        }
        if (obj2 instanceof JSONObject) {
            if (z2) {
                return getDictionaryFromJsonObject((JSONObject) obj2);
            }
            return getListFromJsonObject((JSONObject) obj2);
        } else if (obj2 instanceof JSONArray) {
            List<Object> listFromJsonArray = getListFromJsonArray((JSONArray) obj2, z2);
            if (z2) {
                return YailList.makeList((List) listFromJsonArray);
            }
            return listFromJsonArray;
        } else if (obj2.equals(Boolean.FALSE) || ((obj2 instanceof String) && ((String) obj2).equalsIgnoreCase("false"))) {
            return Boolean.FALSE;
        } else {
            if (obj2.equals(Boolean.TRUE) || ((obj2 instanceof String) && ((String) obj2).equalsIgnoreCase("true"))) {
                return Boolean.TRUE;
            }
            if (obj2 instanceof Number) {
                return obj2;
            }
            return obj2.toString();
        }
    }

    public static String getJsonRepresentation(Object obj) throws JSONException {
        StringBuilder sb;
        StringBuilder sb2;
        Object[] objArr = obj;
        if (objArr == null || objArr.equals((Object) null)) {
            return "null";
        }
        if (objArr instanceof FString) {
            return JSONObject.quote(objArr.toString());
        }
        if (objArr instanceof YailList) {
            return ((YailList) objArr).toJSONString();
        }
        if (objArr instanceof IntFraction) {
            return JSONObject.numberToString(Double.valueOf(((IntFraction) objArr).doubleValue()));
        }
        if (objArr instanceof Number) {
            return JSONObject.numberToString((Number) objArr);
        }
        if (objArr instanceof Boolean) {
            return objArr.toString();
        }
        if (objArr instanceof List) {
            objArr = ((List) objArr).toArray();
        }
        if (objArr instanceof YailDictionary) {
            new StringBuilder();
            StringBuilder sb3 = sb2;
            YailDictionary yailDictionary = (YailDictionary) objArr;
            String str = "";
            StringBuilder append = sb3.append('{');
            for (Map.Entry entry : yailDictionary.entrySet()) {
                StringBuilder append2 = sb3.append(str);
                StringBuilder append3 = sb3.append(JSONObject.quote(entry.getKey().toString()));
                StringBuilder append4 = sb3.append(':');
                StringBuilder append5 = sb3.append(getJsonRepresentation(entry.getValue()));
                str = ",";
            }
            StringBuilder append6 = sb3.append('}');
            return sb3.toString();
        } else if (!objArr.getClass().isArray()) {
            return JSONObject.quote(objArr.toString());
        } else {
            new StringBuilder();
            StringBuilder sb4 = sb;
            StringBuilder sb5 = sb4;
            StringBuilder append7 = sb4.append("[");
            String str2 = "";
            Object[] objArr2 = (Object[]) objArr;
            Object[] objArr3 = objArr2;
            int length = objArr2.length;
            for (int i = 0; i < length; i++) {
                StringBuilder append8 = sb5.append(str2).append(getJsonRepresentation(objArr3[i]));
                str2 = ",";
            }
            StringBuilder append9 = sb5.append("]");
            return sb5.toString();
        }
    }

    @Deprecated
    public static Object getObjectFromJson(String str) throws JSONException {
        return getObjectFromJson(str, false);
    }

    public static Object getObjectFromJson(String str, boolean z) throws JSONException {
        JSONTokener jSONTokener;
        Throwable th;
        String str2 = str;
        boolean z2 = z;
        if (str2 == null || str2.equals("")) {
            return "";
        }
        new JSONTokener(str2);
        Object nextValue = jSONTokener.nextValue();
        Object obj = nextValue;
        if (nextValue == null || obj.equals(JSONObject.NULL)) {
            return null;
        }
        if ((obj instanceof String) || (obj instanceof Number) || (obj instanceof Boolean)) {
            return obj;
        }
        if (obj instanceof JSONArray) {
            return getListFromJsonArray((JSONArray) obj, z2);
        }
        if (!(obj instanceof JSONObject)) {
            Throwable th2 = th;
            new JSONException("Invalid JSON string.");
            throw th2;
        } else if (z2) {
            return getDictionaryFromJsonObject((JSONObject) obj);
        } else {
            return getListFromJsonObject((JSONObject) obj);
        }
    }

    public static String getJsonRepresentationIfValueFileName(Object obj) {
        Throwable th;
        List<String> list;
        JSONArray jSONArray;
        Object obj2 = obj;
        try {
            if (obj2 instanceof String) {
                new JSONArray((String) obj2);
                list = getStringListFromJsonArray(jSONArray);
            } else if (obj2 instanceof List) {
                list = (List) obj2;
            } else {
                Throwable th2 = th;
                new YailRuntimeError("getJsonRepresentationIfValueFileName called on unknown type", obj2.getClass().getName());
                throw th2;
            }
            if (list.size() != 2) {
                return null;
            }
            if (!list.get(0).startsWith(".")) {
                return null;
            }
            String writeFile = writeFile(list.get(1), list.get(0).substring(1));
            System.out.println("Filename Written: ".concat(String.valueOf(writeFile)));
            return getJsonRepresentation(writeFile.replace("file:/", "file:///"));
        } catch (JSONException e) {
            int e2 = Log.e(LOG_TAG, "JSONException", e);
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r4v24, types: [java.io.FileOutputStream] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String writeFile(java.lang.String r9, java.lang.String r10) {
        /*
            r0 = r9
            r1 = r10
            r4 = 0
            r2 = r4
            r4 = r1
            int r4 = r4.length()     // Catch:{ Exception -> 0x0023 }
            r5 = 3
            if (r4 == r5) goto L_0x0050
            r4 = r1
            int r4 = r4.length()     // Catch:{ Exception -> 0x0023 }
            r5 = 4
            if (r4 == r5) goto L_0x0050
            com.google.appinventor.components.runtime.errors.YailRuntimeError r4 = new com.google.appinventor.components.runtime.errors.YailRuntimeError     // Catch:{ Exception -> 0x0023 }
            r8 = r4
            r4 = r8
            r5 = r8
            java.lang.String r6 = "File Extension must be three or four characters"
            java.lang.String r7 = "Write Error"
            r5.<init>(r6, r7)     // Catch:{ Exception -> 0x0023 }
            throw r4     // Catch:{ Exception -> 0x0023 }
        L_0x0023:
            r4 = move-exception
            r0 = r4
            com.google.appinventor.components.runtime.errors.YailRuntimeError r4 = new com.google.appinventor.components.runtime.errors.YailRuntimeError     // Catch:{ all -> 0x0046 }
            r8 = r4
            r4 = r8
            r5 = r8
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0046 }
            r8 = r6
            r6 = r8
            r7 = r8
            r7.<init>()     // Catch:{ all -> 0x0046 }
            r7 = r0
            java.lang.String r7 = r7.getMessage()     // Catch:{ all -> 0x0046 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0046 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0046 }
            java.lang.String r7 = "Write"
            r5.<init>(r6, r7)     // Catch:{ all -> 0x0046 }
            throw r4     // Catch:{ all -> 0x0046 }
        L_0x0046:
            r4 = move-exception
            r0 = r4
            java.lang.String r4 = LOG_TAG
            r5 = r2
            com.google.appinventor.components.runtime.util.IOUtils.closeQuietly(r4, r5)
            r4 = r0
            throw r4
        L_0x0050:
            r4 = r0
            r5 = 0
            byte[] r4 = android.util.Base64.decode(r4, r5)     // Catch:{ Exception -> 0x0023 }
            r0 = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0023 }
            r8 = r4
            r4 = r8
            r5 = r8
            r5.<init>()     // Catch:{ Exception -> 0x0023 }
            java.io.File r5 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Exception -> 0x0023 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Exception -> 0x0023 }
            java.lang.String r5 = "/AppInventorBinaries"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Exception -> 0x0023 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0023 }
            r3 = r4
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x0023 }
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r3
            r5.<init>(r6)     // Catch:{ Exception -> 0x0023 }
            r8 = r4
            r4 = r8
            r5 = r8
            r3 = r5
            boolean r4 = r4.mkdirs()     // Catch:{ Exception -> 0x0023 }
            java.lang.String r4 = "BinFile"
            java.lang.String r5 = "."
            r6 = r1
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Exception -> 0x0023 }
            java.lang.String r5 = r5.concat(r6)     // Catch:{ Exception -> 0x0023 }
            r6 = r3
            java.io.File r4 = java.io.File.createTempFile(r4, r5, r6)     // Catch:{ Exception -> 0x0023 }
            r1 = r4
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0023 }
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r5.<init>(r6)     // Catch:{ Exception -> 0x0023 }
            r8 = r4
            r4 = r8
            r5 = r8
            r2 = r5
            r5 = r0
            r4.write(r5)     // Catch:{ Exception -> 0x0023 }
            r4 = r1
            java.net.URI r4 = r4.toURI()     // Catch:{ Exception -> 0x0023 }
            java.lang.String r4 = r4.toASCIIString()     // Catch:{ Exception -> 0x0023 }
            r0 = r4
            r4 = 20
            r5 = r3
            trimDirectory(r4, r5)     // Catch:{ Exception -> 0x0023 }
            java.lang.String r4 = LOG_TAG
            r5 = r2
            com.google.appinventor.components.runtime.util.IOUtils.closeQuietly(r4, r5)
            r4 = r0
            r0 = r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.JsonUtil.writeFile(java.lang.String, java.lang.String):java.lang.String");
    }

    private static void trimDirectory(int i, File file) {
        Comparator comparator;
        File[] listFiles = file.listFiles();
        File[] fileArr = listFiles;
        new Comparator<File>() {
            public final /* synthetic */ int compare(Object obj, Object obj2) {
                return Long.valueOf(((File) obj).lastModified()).compareTo(Long.valueOf(((File) obj2).lastModified()));
            }
        };
        Arrays.sort(listFiles, comparator);
        int length = fileArr.length - i;
        for (int i2 = 0; i2 < length; i2++) {
            boolean delete = fileArr[i2].delete();
        }
    }

    public static String GetJsonAsString(String str, String str2) {
        StringBuilder sb;
        String str3 = str2;
        new StringBuilder();
        return GetJsonAsString(str, str3, sb.append(str3).append(" not found").toString());
    }

    public static String GetJsonAsString(String str, String str2, String str3) {
        JSONObject jSONObject;
        String str4 = str2;
        String str5 = str3;
        try {
            new JSONObject(str);
            str5 = jSONObject.getString(str4);
        } catch (JSONException e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
        return str5;
    }

    public static boolean GetJsonAsBoolean(String str, String str2) {
        JSONObject jSONObject;
        String str3 = str2;
        boolean z = false;
        try {
            new JSONObject(str);
            z = jSONObject.getBoolean(str3);
        } catch (JSONException e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
        return z;
    }

    public static int GetJsonAsInt(String str, String str2) {
        JSONObject jSONObject;
        String str3 = str2;
        int i = 0;
        try {
            new JSONObject(str);
            i = jSONObject.getInt(str3);
        } catch (JSONException e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
        return i;
    }

    public static long GetJsonAsLong(String str, String str2) {
        JSONObject jSONObject;
        String str3 = str2;
        long j = 0;
        try {
            new JSONObject(str);
            j = jSONObject.getLong(str3);
        } catch (JSONException e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
        return j;
    }

    public static double GetJsonAsDouble(String str, String str2) {
        JSONObject jSONObject;
        String str3 = str2;
        double d = 0.0d;
        try {
            new JSONObject(str);
            d = jSONObject.getDouble(str3);
        } catch (JSONException e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
        return d;
    }

    public static String GetJsonFromArrayAsString(String str, String str2, int i) {
        StringBuilder sb;
        JSONArray jSONArray;
        String str3 = str;
        String str4 = str2;
        int i2 = i;
        new StringBuilder();
        String sb2 = sb.append(str4).append(" not found").toString();
        try {
            new JSONArray(str3);
            sb2 = jSONArray.getJSONObject(i2).getString(str4);
        } catch (JSONException e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
        return sb2;
    }
}
