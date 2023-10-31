package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.databind.util.Annotations;
import java.lang.annotation.Annotation;
import java.util.HashMap;

public final class AnnotationMap implements Annotations {
    protected HashMap<Class<? extends Annotation>, Annotation> _annotations;

    public AnnotationMap() {
    }

    private AnnotationMap(HashMap<Class<? extends Annotation>, Annotation> hashMap) {
        this._annotations = hashMap;
    }

    public <A extends Annotation> A get(Class<A> cls) {
        Class<A> cls2 = cls;
        if (this._annotations == null) {
            return null;
        }
        return (Annotation) this._annotations.get(cls2);
    }

    public static AnnotationMap merge(AnnotationMap annotationMap, AnnotationMap annotationMap2) {
        HashMap hashMap;
        AnnotationMap annotationMap3;
        AnnotationMap annotationMap4 = annotationMap;
        AnnotationMap annotationMap5 = annotationMap2;
        if (annotationMap4 == null || annotationMap4._annotations == null || annotationMap4._annotations.isEmpty()) {
            return annotationMap5;
        }
        if (annotationMap5 == null || annotationMap5._annotations == null || annotationMap5._annotations.isEmpty()) {
            return annotationMap4;
        }
        new HashMap();
        HashMap hashMap2 = hashMap;
        for (Annotation next : annotationMap5._annotations.values()) {
            Object put = hashMap2.put(next.annotationType(), next);
        }
        for (Annotation next2 : annotationMap4._annotations.values()) {
            Object put2 = hashMap2.put(next2.annotationType(), next2);
        }
        new AnnotationMap(hashMap2);
        return annotationMap3;
    }

    public int size() {
        return this._annotations == null ? 0 : this._annotations.size();
    }

    public void addIfNotPresent(Annotation annotation) {
        Annotation annotation2 = annotation;
        if (this._annotations == null || !this._annotations.containsKey(annotation2.annotationType())) {
            _add(annotation2);
        }
    }

    public void add(Annotation annotation) {
        _add(annotation);
    }

    public String toString() {
        if (this._annotations == null) {
            return "[null]";
        }
        return this._annotations.toString();
    }

    /* access modifiers changed from: protected */
    public final void _add(Annotation annotation) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap;
        Annotation annotation2 = annotation;
        if (this._annotations == null) {
            new HashMap<>();
            this._annotations = hashMap;
        }
        Annotation put = this._annotations.put(annotation2.annotationType(), annotation2);
    }
}
