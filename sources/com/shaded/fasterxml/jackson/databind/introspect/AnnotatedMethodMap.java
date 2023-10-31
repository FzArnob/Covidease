package com.shaded.fasterxml.jackson.databind.introspect;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;

public final class AnnotatedMethodMap implements Iterable<AnnotatedMethod> {
    protected LinkedHashMap<MemberKey, AnnotatedMethod> _methods;

    public AnnotatedMethodMap() {
    }

    public void add(AnnotatedMethod annotatedMethod) {
        Object obj;
        LinkedHashMap<MemberKey, AnnotatedMethod> linkedHashMap;
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        if (this._methods == null) {
            new LinkedHashMap<>();
            this._methods = linkedHashMap;
        }
        new MemberKey(annotatedMethod2.getAnnotated());
        Object put = this._methods.put(obj, annotatedMethod2);
    }

    public AnnotatedMethod remove(AnnotatedMethod annotatedMethod) {
        return remove(annotatedMethod.getAnnotated());
    }

    public AnnotatedMethod remove(Method method) {
        Object obj;
        Method method2 = method;
        if (this._methods == null) {
            return null;
        }
        new MemberKey(method2);
        return (AnnotatedMethod) this._methods.remove(obj);
    }

    public boolean isEmpty() {
        return this._methods == null || this._methods.size() == 0;
    }

    public int size() {
        return this._methods == null ? 0 : this._methods.size();
    }

    public AnnotatedMethod find(String str, Class<?>[] clsArr) {
        Object obj;
        String str2 = str;
        Class<?>[] clsArr2 = clsArr;
        if (this._methods == null) {
            return null;
        }
        new MemberKey(str2, clsArr2);
        return this._methods.get(obj);
    }

    public AnnotatedMethod find(Method method) {
        Object obj;
        Method method2 = method;
        if (this._methods == null) {
            return null;
        }
        new MemberKey(method2);
        return this._methods.get(obj);
    }

    public Iterator<AnnotatedMethod> iterator() {
        if (this._methods != null) {
            return this._methods.values().iterator();
        }
        return Collections.emptyList().iterator();
    }
}
