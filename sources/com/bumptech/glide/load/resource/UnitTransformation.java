package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;

public class UnitTransformation<T> implements Transformation<T> {
    private static final Transformation<?> TRANSFORMATION;

    public UnitTransformation() {
    }

    static {
        Transformation<?> transformation;
        new UnitTransformation();
        TRANSFORMATION = transformation;
    }

    public static <T> UnitTransformation<T> get() {
        return (UnitTransformation) TRANSFORMATION;
    }

    public Resource<T> transform(Resource<T> resource, int i, int i2) {
        int i3 = i;
        int i4 = i2;
        return resource;
    }

    public String getId() {
        return "";
    }
}
