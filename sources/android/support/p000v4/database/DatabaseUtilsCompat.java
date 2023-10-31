package android.support.p000v4.database;

import android.text.TextUtils;

@Deprecated
/* renamed from: android.support.v4.database.DatabaseUtilsCompat */
public final class DatabaseUtilsCompat {
    private DatabaseUtilsCompat() {
    }

    @Deprecated
    public static String concatenateWhere(String str, String str2) {
        StringBuilder sb;
        String a = str;
        String b = str2;
        if (TextUtils.isEmpty(a)) {
            return b;
        }
        if (TextUtils.isEmpty(b)) {
            return a;
        }
        new StringBuilder();
        return sb.append("(").append(a).append(") AND (").append(b).append(")").toString();
    }

    @Deprecated
    public static String[] appendSelectionArgs(String[] strArr, String[] strArr2) {
        String[] originalValues = strArr;
        String[] newValues = strArr2;
        if (originalValues == null || originalValues.length == 0) {
            return newValues;
        }
        String[] result = new String[(originalValues.length + newValues.length)];
        System.arraycopy(originalValues, 0, result, 0, originalValues.length);
        System.arraycopy(newValues, 0, result, originalValues.length, newValues.length);
        return result;
    }
}
