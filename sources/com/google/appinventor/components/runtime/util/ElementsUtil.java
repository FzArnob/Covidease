package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.YailRuntimeError;

public class ElementsUtil {
    public ElementsUtil() {
    }

    public static YailList elementsFromString(String str) {
        YailList yailList;
        String str2 = str;
        new YailList();
        YailList yailList2 = yailList;
        if (str2.length() > 0) {
            yailList2 = YailList.makeList((Object[]) str2.split(" *, *"));
        }
        return yailList2;
    }

    public static YailList elements(YailList yailList, String str) {
        Throwable th;
        StringBuilder sb;
        YailList yailList2 = yailList;
        String str2 = str;
        String[] stringArray = yailList2.toStringArray();
        for (int i = 0; i < stringArray.length; i++) {
            if (!(stringArray[i] instanceof String)) {
                Throwable th2 = th;
                new StringBuilder("Items passed to ");
                new YailRuntimeError(sb.append(str2).append(" must be Strings").toString(), "Error");
                throw th2;
            }
        }
        return yailList2;
    }

    public static int selectionIndex(int i, YailList yailList) {
        int i2 = i;
        YailList yailList2 = yailList;
        if (i2 <= 0 || i2 > yailList2.size()) {
            return 0;
        }
        return i2;
    }

    public static String setSelectionFromIndex(int i, YailList yailList) {
        int i2 = i;
        YailList yailList2 = yailList;
        if (i2 == 0) {
            return "";
        }
        return yailList2.getString(i2 - 1);
    }

    public static int setSelectedIndexFromValue(String str, YailList yailList) {
        String str2 = str;
        YailList yailList2 = yailList;
        for (int i = 0; i < yailList2.size(); i++) {
            if (yailList2.getString(i).equals(str2)) {
                return i + 1;
            }
        }
        return 0;
    }
}
