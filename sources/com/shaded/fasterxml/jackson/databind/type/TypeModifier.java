package com.shaded.fasterxml.jackson.databind.type;

import com.shaded.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.Type;

public abstract class TypeModifier {
    public abstract JavaType modifyType(JavaType javaType, Type type, TypeBindings typeBindings, TypeFactory typeFactory);

    public TypeModifier() {
    }
}
