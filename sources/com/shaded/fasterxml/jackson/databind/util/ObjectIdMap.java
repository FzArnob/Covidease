package com.shaded.fasterxml.jackson.databind.util;

import java.util.IdentityHashMap;

public class ObjectIdMap extends IdentityHashMap<Object, Object> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ObjectIdMap() {
        super(16);
    }

    public Object findId(Object obj) {
        return get(obj);
    }

    public void insertId(Object obj, Object obj2) {
        Object put = put(obj, obj2);
    }
}
