package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.math.IntNum;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.json.JSONException;

public class YailList extends Pair implements YailObject {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public YailList() {
        super(YailConstants.YAIL_HEADER, LList.Empty);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private YailList(Object obj) {
        super(YailConstants.YAIL_HEADER, obj);
    }

    public static YailList makeEmptyList() {
        YailList yailList;
        YailList yailList2 = yailList;
        new YailList();
        return yailList2;
    }

    public static YailList makeList(Object[] objArr) {
        YailList yailList;
        new YailList(Pair.makeList(objArr, 0));
        return yailList;
    }

    public static YailList makeList(List list) {
        YailList yailList;
        new YailList(Pair.makeList(list));
        return yailList;
    }

    public static YailList makeList(Collection collection) {
        List list;
        YailList yailList;
        new ArrayList(collection);
        new YailList(Pair.makeList(list));
        return yailList;
    }

    public static YailList makeList(Set set) {
        List list;
        YailList yailList;
        new ArrayList(set);
        new YailList(Pair.makeList(list));
        return yailList;
    }

    public Object[] toArray() {
        Throwable th;
        if (this.cdr instanceof Pair) {
            return ((Pair) this.cdr).toArray();
        }
        if (this.cdr instanceof LList) {
            return ((LList) this.cdr).toArray();
        }
        Throwable th2 = th;
        new YailRuntimeError("YailList cannot be represented as an array", "YailList Error.");
        throw th2;
    }

    public String[] toStringArray() {
        int size = size();
        int i = size;
        String[] strArr = new String[size];
        for (int i2 = 1; i2 <= i; i2++) {
            strArr[i2 - 1] = YailListElementToString(get(i2));
        }
        return strArr;
    }

    public static String YailListElementToString(Object obj) {
        Object obj2 = obj;
        if (obj2 instanceof IntNum) {
            return ((IntNum) obj2).toString(10);
        }
        if (obj2 instanceof Long) {
            return Long.toString(((Long) obj2).longValue());
        }
        if (Number.class.isInstance(obj2)) {
            return YailNumberToString.format(((Number) obj2).doubleValue());
        }
        return String.valueOf(obj2);
    }

    public String toJSONString() {
        Throwable th;
        StringBuilder sb;
        try {
            new StringBuilder();
            StringBuilder sb2 = sb;
            String str = "";
            StringBuilder append = sb2.append('[');
            int size = size();
            for (int i = 1; i <= size; i++) {
                StringBuilder append2 = sb2.append(str).append(JsonUtil.getJsonRepresentation(get(i)));
                str = ",";
            }
            StringBuilder append3 = sb2.append(']');
            return sb2.toString();
        } catch (JSONException e) {
            Throwable th2 = th;
            new YailRuntimeError("List failed to convert to JSON.", "JSON Creation Error.");
            throw th2;
        }
    }

    public int size() {
        return super.size() - 1;
    }

    public String toString() {
        Throwable th;
        if (this.cdr instanceof Pair) {
            return ((Pair) this.cdr).toString();
        }
        if (this.cdr instanceof LList) {
            return ((LList) this.cdr).toString();
        }
        Throwable th2 = th;
        new RuntimeException("YailList cannot be represented as a String");
        throw th2;
    }

    public String getString(int i) {
        return get(i + 1).toString();
    }

    public Object getObject(int i) {
        return get(i + 1);
    }
}
