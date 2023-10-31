package com.bumptech.glide.load;

import com.bumptech.glide.load.engine.Resource;
import java.util.Arrays;
import java.util.Collection;

public class MultiTransformation<T> implements Transformation<T> {

    /* renamed from: id */
    private String f306id;
    private final Collection<? extends Transformation<T>> transformations;

    @SafeVarargs
    public MultiTransformation(Transformation<T>... transformationArr) {
        Throwable th;
        Transformation<T>[] transformations2 = transformationArr;
        if (transformations2.length < 1) {
            Throwable th2 = th;
            new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
            throw th2;
        }
        this.transformations = Arrays.asList(transformations2);
    }

    public MultiTransformation(Collection<? extends Transformation<T>> collection) {
        Throwable th;
        Collection<? extends Transformation<T>> transformationList = collection;
        if (transformationList.size() < 1) {
            Throwable th2 = th;
            new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
            throw th2;
        }
        this.transformations = transformationList;
    }

    public Resource<T> transform(Resource<T> resource, int i, int i2) {
        Resource<T> resource2 = resource;
        int outWidth = i;
        int outHeight = i2;
        Resource<T> previous = resource2;
        for (Transformation transform : this.transformations) {
            Resource<T> transformed = transform.transform(previous, outWidth, outHeight);
            if (previous != null && !previous.equals(resource2) && !previous.equals(transformed)) {
                previous.recycle();
            }
            previous = transformed;
        }
        return previous;
    }

    public String getId() {
        StringBuilder sb;
        if (this.f306id == null) {
            new StringBuilder();
            StringBuilder sb2 = sb;
            for (Transformation id : this.transformations) {
                StringBuilder append = sb2.append(id.getId());
            }
            this.f306id = sb2.toString();
        }
        return this.f306id;
    }
}
