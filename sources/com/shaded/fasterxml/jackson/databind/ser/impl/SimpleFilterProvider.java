package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.shaded.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.shaded.fasterxml.jackson.databind.ser.FilterProvider;
import java.io.Serializable;
import java.util.Map;

public class SimpleFilterProvider extends FilterProvider implements Serializable {
    private static final long serialVersionUID = -2825494703774121220L;
    protected boolean _cfgFailOnUnknownId;
    protected BeanPropertyFilter _defaultFilter;
    protected final Map<String, BeanPropertyFilter> _filtersById;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SimpleFilterProvider() {
        /*
            r5 = this;
            r0 = r5
            r1 = r0
            java.util.HashMap r2 = new java.util.HashMap
            r4 = r2
            r2 = r4
            r3 = r4
            r3.<init>()
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider.<init>():void");
    }

    public SimpleFilterProvider(Map<String, BeanPropertyFilter> map) {
        this._cfgFailOnUnknownId = true;
        this._filtersById = map;
    }

    public SimpleFilterProvider setDefaultFilter(BeanPropertyFilter beanPropertyFilter) {
        this._defaultFilter = beanPropertyFilter;
        return this;
    }

    public BeanPropertyFilter getDefaultFilter() {
        return this._defaultFilter;
    }

    public SimpleFilterProvider setFailOnUnknownId(boolean z) {
        this._cfgFailOnUnknownId = z;
        return this;
    }

    public boolean willFailOnUnknownId() {
        return this._cfgFailOnUnknownId;
    }

    public SimpleFilterProvider addFilter(String str, BeanPropertyFilter beanPropertyFilter) {
        BeanPropertyFilter put = this._filtersById.put(str, beanPropertyFilter);
        return this;
    }

    public BeanPropertyFilter removeFilter(String str) {
        return this._filtersById.remove(str);
    }

    public BeanPropertyFilter findFilter(Object obj) {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        BeanPropertyFilter beanPropertyFilter = this._filtersById.get(obj2);
        if (beanPropertyFilter == null) {
            beanPropertyFilter = this._defaultFilter;
            if (beanPropertyFilter == null && this._cfgFailOnUnknownId) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("No filter configured with id '").append(obj2).append("' (type ").append(obj2.getClass().getName()).append(")").toString());
                throw th2;
            }
        }
        return beanPropertyFilter;
    }
}
