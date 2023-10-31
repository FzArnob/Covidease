package com.shaded.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;

public class ReadableObjectId {

    /* renamed from: id */
    public final Object f287id;
    public Object item;

    public ReadableObjectId(Object obj) {
        this.f287id = obj;
    }

    public void bindItem(Object obj) throws IOException {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        if (this.item != null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Already had POJO for id (").append(this.f287id.getClass().getName()).append(") [").append(this.f287id).append("]").toString());
            throw th2;
        }
        this.item = obj2;
    }
}
