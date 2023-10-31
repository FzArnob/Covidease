package com.google.appinventor.components.runtime.util;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.errors.DispatchableError;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import gnu.lists.FString;
import gnu.lists.LList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

public class YailDictionary extends LinkedHashMap<Object, Object> implements YailObject<YailList> {
    public static final Object ALL;

    static {
        Object obj;
        new Object() {
            public final String toString() {
                return "ALL_ITEMS";
            }
        };
        ALL = obj;
    }

    public YailDictionary() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public YailDictionary(Map<Object, Object> map) {
        super(map);
    }

    public static YailDictionary makeDictionary() {
        YailDictionary yailDictionary;
        YailDictionary yailDictionary2 = yailDictionary;
        new YailDictionary();
        return yailDictionary2;
    }

    public static YailDictionary makeDictionary(Map<Object, Object> map) {
        YailDictionary yailDictionary;
        new YailDictionary(map);
        return yailDictionary;
    }

    public static YailDictionary makeDictionary(Object... objArr) {
        YailDictionary yailDictionary;
        Throwable th;
        Object[] objArr2 = objArr;
        if (objArr2.length % 2 == 1) {
            Throwable th2 = th;
            new IllegalArgumentException("Expected an even number of key-value entries.");
            throw th2;
        }
        new YailDictionary();
        YailDictionary yailDictionary2 = yailDictionary;
        for (int i = 0; i < objArr2.length; i += 2) {
            Object put = yailDictionary2.put(objArr2[i], objArr2[i + 1]);
        }
        return yailDictionary2;
    }

    public static YailDictionary makeDictionary(List<YailList> list) {
        Map map;
        YailDictionary yailDictionary;
        new LinkedHashMap();
        Map map2 = map;
        for (YailList next : list) {
            Object object = next.getObject(0);
            Object object2 = next.getObject(1);
            Object obj = object2;
            if (!(object2 instanceof YailList)) {
                Object put = map2.put(object, obj);
            } else if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((YailList) obj).booleanValue()) {
                Object put2 = map2.put(object, alistToDict((YailList) obj));
            } else {
                Object put3 = map2.put(object, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((YailList) obj));
            }
        }
        new YailDictionary(map2);
        return yailDictionary;
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private static Boolean m73hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(YailList yailList) {
        boolean z = false;
        Iterator it = ((LList) yailList.getCdr()).iterator();
        while (it.hasNext()) {
            Object next = it.next();
            Object obj = next;
            if (!(next instanceof YailList)) {
                return Boolean.FALSE;
            }
            if (((YailList) obj).size() != 2) {
                return Boolean.FALSE;
            }
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public static YailDictionary alistToDict(YailList yailList) {
        LinkedHashMap linkedHashMap;
        YailDictionary yailDictionary;
        new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        Iterator it = ((LList) yailList.getCdr()).iterator();
        while (it.hasNext()) {
            YailList yailList2 = (YailList) it.next();
            Object object = yailList2.getObject(0);
            Object object2 = yailList2.getObject(1);
            Object obj = object2;
            if ((object2 instanceof YailList) && hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((YailList) obj).booleanValue()) {
                Object put = linkedHashMap2.put(object, alistToDict((YailList) obj));
            } else if (obj instanceof YailList) {
                Object put2 = linkedHashMap2.put(object, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((YailList) obj));
            } else {
                Object put3 = linkedHashMap2.put(object, obj);
            }
        }
        new YailDictionary(linkedHashMap2);
        return yailDictionary;
    }

    private static YailList hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(YailList yailList) {
        YailList yailList2 = yailList;
        Object[] objArr = new Object[yailList2.size()];
        int i = 0;
        Iterator it = yailList2.iterator();
        Iterator it2 = it;
        Object next = it.next();
        boolean z = false;
        while (it2.hasNext()) {
            Object next2 = it2.next();
            Object obj = next2;
            if (!(next2 instanceof YailList)) {
                objArr[i] = obj;
            } else if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((YailList) obj).booleanValue()) {
                objArr[i] = alistToDict((YailList) obj);
                z = true;
            } else {
                objArr[i] = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((YailList) obj);
                if (objArr[i] != obj) {
                    z = true;
                }
            }
            i++;
        }
        if (z) {
            return YailList.makeList(objArr);
        }
        return yailList2;
    }

    public static YailList dictToAlist(YailDictionary yailDictionary) {
        List list;
        new ArrayList();
        List list2 = list;
        for (Map.Entry entry : yailDictionary.entrySet()) {
            Object[] objArr = new Object[2];
            objArr[0] = entry.getKey();
            Object[] objArr2 = objArr;
            objArr2[1] = entry.getValue();
            boolean add = list2.add(YailList.makeList(objArr2));
        }
        return YailList.makeList(list2);
    }

    public void setPair(YailList yailList) {
        YailList yailList2 = yailList;
        Object put = put(yailList2.getObject(0), yailList2.getObject(1));
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private static Object m74hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(List<?> list, Object obj) {
        Throwable th;
        Throwable th2;
        List<?> list2 = list;
        Object obj2 = obj;
        int i = list2 instanceof YailList ? 0 : 1;
        try {
            if (obj2 instanceof FString) {
                return list2.get(Integer.parseInt(obj2.toString()) - i);
            }
            if (obj2 instanceof String) {
                return list2.get(Integer.parseInt((String) obj2) - i);
            }
            if (obj2 instanceof Number) {
                return list2.get(((Number) obj2).intValue() - i);
            }
            return null;
        } catch (NumberFormatException e) {
            int w = Log.w("YailDictionary", "Unable to parse key as integer: ".concat(String.valueOf(obj2)), e);
            Throwable th3 = th2;
            new YailRuntimeError("Unable to parse key as integer: ".concat(String.valueOf(obj2)), "NumberParseException");
            throw th3;
        } catch (IndexOutOfBoundsException e2) {
            int w2 = Log.w("YailDictionary", "Requested too large of an index: ".concat(String.valueOf(obj2)), e2);
            Throwable th4 = th;
            new YailRuntimeError("Requested too large of an index: ".concat(String.valueOf(obj2)), "IndexOutOfBoundsException");
            throw th4;
        }
    }

    public Object getObjectAtKeyPath(List<?> list) {
        Object obj = this;
        for (Object next : list) {
            if (obj instanceof Map) {
                obj = ((Map) obj).get(next);
            } else if ((obj instanceof YailList) && hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((YailList) obj).booleanValue()) {
                obj = alistToDict((YailList) obj).get(next);
            } else if (!(obj instanceof List)) {
                return null;
            } else {
                obj = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((List<?>) (List) obj, (Object) next);
            }
        }
        return obj;
    }

    private static Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(YailList yailList, Object obj) {
        Object obj2 = obj;
        Iterator it = ((LList) yailList.getCdr()).iterator();
        while (it.hasNext()) {
            Object next = it.next();
            Object obj3 = next;
            if (!(next instanceof YailList)) {
                return null;
            }
            if (((YailList) obj3).getObject(0).equals(obj2)) {
                return ((YailList) obj3).getObject(1);
            }
        }
        return null;
    }

    private static <T> List<Object> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Object obj, List<T> list, List<Object> list2) {
        Collection<Object> emptyList;
        ArrayList arrayList;
        Object obj2 = obj;
        List<T> list3 = list;
        List<Object> list4 = list2;
        if (list3.isEmpty()) {
            if (obj2 != null) {
                boolean add = list4.add(obj2);
            }
            return list4;
        } else if (obj2 == null) {
            return list4;
        } else {
            T t = list3.get(0);
            List<T> subList = list3.subList(1, list3.size());
            if (t == ALL) {
                if (obj2 instanceof Map) {
                    emptyList = ((Map) obj2).values();
                } else if (obj2 instanceof List) {
                    List list5 = (List) obj2;
                    List list6 = list5;
                    if (!(list5 instanceof YailList)) {
                        emptyList = list6;
                    } else if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((YailList) list6).booleanValue()) {
                        new ArrayList();
                        ArrayList arrayList2 = arrayList;
                        Iterator it = ((LList) ((YailList) list6).getCdr()).iterator();
                        while (it.hasNext()) {
                            boolean add2 = arrayList2.add(((YailList) it.next()).getObject(1));
                        }
                        emptyList = arrayList2;
                    } else {
                        emptyList = (Collection) ((YailList) list6).getCdr();
                    }
                } else {
                    emptyList = Collections.emptyList();
                }
                for (Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME : emptyList) {
                    List<Object> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, subList, list4);
                }
            } else if (obj2 instanceof Map) {
                List<Object> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(((Map) obj2).get(t), subList, list4);
            } else if ((obj2 instanceof YailList) && hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((YailList) obj2).booleanValue()) {
                Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME4 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((YailList) obj2, (Object) t);
                Object obj3 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME4;
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME4 != null) {
                    List<Object> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME5 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj3, subList, list4);
                }
            } else if (obj2 instanceof List) {
                try {
                    List<Object> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME6 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(((List) obj2).get(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((List<?>) (List) obj2, (Object) t)), subList, list4);
                } catch (Exception e) {
                }
            }
            return list4;
        }
    }

    public static <T> List<Object> walkKeyPath(YailObject<?> yailObject, List<T> list) {
        List list2;
        new ArrayList();
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailObject, list, list2);
    }

    private static int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(List<?> list, Object obj) {
        Throwable th;
        int parseInt;
        Throwable th2;
        Throwable th3;
        List<?> list2 = list;
        Object obj2 = obj;
        int i = list2 instanceof YailList ? 0 : 1;
        if (obj2 instanceof Number) {
            parseInt = ((Number) obj2).intValue();
        } else {
            try {
                parseInt = Integer.parseInt(obj2.toString());
            } catch (NumberFormatException e) {
                Throwable th4 = th;
                new DispatchableError(ErrorMessages.ERROR_NUMBER_FORMAT_EXCEPTION, obj2.toString());
                throw th4;
            }
        }
        int i2 = parseInt - i;
        int i3 = i2;
        if (i2 >= 0 && i3 < (list2.size() + 1) - i) {
            return i3;
        }
        try {
            Throwable th5 = th3;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(i3 + i);
            Object[] objArr2 = objArr;
            objArr2[1] = JsonUtil.getJsonRepresentation(list2);
            new DispatchableError(ErrorMessages.ERROR_INDEX_MISSING_IN_LIST, objArr2);
            throw th5;
        } catch (JSONException e2) {
            JSONException jSONException = e2;
            int e3 = Log.e("YailDictionary", "Unable to serialize object as JSON", jSONException);
            Throwable th6 = th2;
            new YailRuntimeError(jSONException.getMessage(), "JSON Error");
            throw th6;
        }
    }

    public void setValueForKeyPath(List<?> list, Object obj) {
        Throwable th;
        String simpleName;
        Object obj2;
        List<?> list2 = list;
        Object obj3 = obj;
        Object obj4 = this;
        Iterator<?> it = list2.iterator();
        if (!list2.isEmpty()) {
            while (it.hasNext()) {
                Object next = it.next();
                if (it.hasNext()) {
                    Object obj5 = obj4;
                    Object obj6 = next;
                    Object obj7 = obj5;
                    Object obj8 = obj7;
                    if (obj7 instanceof YailDictionary) {
                        obj2 = ((YailDictionary) obj8).get(obj6);
                    } else if (obj8 instanceof List) {
                        obj2 = ((List) obj8).get(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((List<?>) (List) obj8, (Object) obj6));
                    } else {
                        DispatchableError dispatchableError = r12;
                        Object[] objArr = new Object[1];
                        Object[] objArr2 = objArr;
                        Object[] objArr3 = objArr;
                        if (obj8 == null) {
                            simpleName = "null";
                        } else {
                            simpleName = obj8.getClass().getSimpleName();
                        }
                        objArr3[0] = simpleName;
                        DispatchableError dispatchableError2 = new DispatchableError(ErrorMessages.ERROR_INVALID_VALUE_IN_PATH, objArr2);
                        throw dispatchableError;
                    }
                    obj4 = obj2;
                } else if (obj4 instanceof YailDictionary) {
                    Object put = ((YailDictionary) obj4).put(next, obj3);
                } else if (obj4 instanceof YailList) {
                    ((LList) obj4).getIterator(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((List<?>) (List) obj4, (Object) next)).set(obj3);
                } else if (obj4 instanceof List) {
                    Object obj9 = ((List) obj4).set(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((List<?>) (List) obj4, (Object) next), obj3);
                } else {
                    Throwable th2 = th;
                    new DispatchableError(ErrorMessages.ERROR_INVALID_VALUE_IN_PATH);
                    throw th2;
                }
            }
        }
    }

    public boolean containsKey(Object obj) {
        Object obj2 = obj;
        if (obj2 instanceof FString) {
            return super.containsKey(obj2.toString());
        }
        return super.containsKey(obj2);
    }

    public boolean containsValue(Object obj) {
        Object obj2 = obj;
        if (obj2 instanceof FString) {
            return super.containsValue(obj2.toString());
        }
        return super.containsValue(obj2);
    }

    public Object get(Object obj) {
        Object obj2 = obj;
        if (obj2 instanceof FString) {
            return super.get(obj2.toString());
        }
        return super.get(obj2);
    }

    public Object put(Object obj, Object obj2) {
        String str = obj;
        String str2 = obj2;
        if (str instanceof FString) {
            str = str.toString();
        }
        if (str2 instanceof FString) {
            str2 = str2.toString();
        }
        return super.put(str, str2);
    }

    public Object remove(Object obj) {
        Object obj2 = obj;
        if (obj2 instanceof FString) {
            return super.remove(obj2.toString());
        }
        return super.remove(obj2);
    }

    public String toString() {
        Throwable th;
        try {
            return JsonUtil.getJsonRepresentation(this);
        } catch (JSONException e) {
            JSONException jSONException = e;
            Throwable th2 = th;
            new YailRuntimeError(jSONException.getMessage(), "JSON Error");
            throw th2;
        }
    }

    public Object getObject(int i) {
        Throwable th;
        Throwable th2;
        int i2 = i;
        if (i2 < 0 || i2 >= size()) {
            Throwable th3 = th;
            new IndexOutOfBoundsException();
            throw th3;
        }
        for (Map.Entry entry : entrySet()) {
            if (i2 == 0) {
                Object[] objArr = new Object[2];
                objArr[0] = entry.getKey();
                Object[] objArr2 = objArr;
                objArr2[1] = entry.getValue();
                return Lists.newArrayList(objArr2);
            }
            i2--;
        }
        Throwable th4 = th2;
        new IndexOutOfBoundsException();
        throw th4;
    }

    @NonNull
    public Iterator<YailList> iterator() {
        Iterator<YailList> it;
        new C1194a(entrySet().iterator());
        return it;
    }

    /* renamed from: com.google.appinventor.components.runtime.util.YailDictionary$a */
    static class C1194a implements Iterator<YailList> {
        private Iterator<Map.Entry<Object, Object>> B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

        public final /* synthetic */ Object next() {
            Map.Entry next = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.next();
            Object[] objArr = new Object[2];
            objArr[0] = next.getKey();
            Object[] objArr2 = objArr;
            objArr2[1] = next.getValue();
            return YailList.makeList(objArr2);
        }

        C1194a(Iterator<Map.Entry<Object, Object>> it) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = it;
        }

        public final boolean hasNext() {
            return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.hasNext();
        }

        public final void remove() {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.remove();
        }
    }
}
