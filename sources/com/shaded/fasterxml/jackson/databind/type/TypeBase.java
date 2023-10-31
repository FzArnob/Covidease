package com.shaded.fasterxml.jackson.databind.type;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializable;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import gnu.bytecode.Access;
import java.io.IOException;

public abstract class TypeBase extends JavaType implements JsonSerializable {
    private static final long serialVersionUID = -3581199092426900829L;
    volatile transient String _canonicalName;

    /* access modifiers changed from: protected */
    public abstract String buildCanonicalName();

    public abstract StringBuilder getErasedSignature(StringBuilder sb);

    public abstract StringBuilder getGenericSignature(StringBuilder sb);

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    protected TypeBase(Class<?> cls, int i, Object obj, Object obj2) {
        this(cls, i, obj, obj2, false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected TypeBase(Class<?> cls, int i, Object obj, Object obj2, boolean z) {
        super(cls, i, obj, obj2, z);
    }

    public String toCanonical() {
        String str = this._canonicalName;
        if (str == null) {
            str = buildCanonicalName();
        }
        return str;
    }

    public <T> T getValueHandler() {
        return this._valueHandler;
    }

    public <T> T getTypeHandler() {
        return this._typeHandler;
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        TypeSerializer typeSerializer2 = typeSerializer;
        typeSerializer2.writeTypePrefixForScalar(this, jsonGenerator2);
        serialize(jsonGenerator2, serializerProvider);
        typeSerializer2.writeTypeSuffixForScalar(this, jsonGenerator2);
    }

    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        jsonGenerator.writeString(toCanonical());
    }

    protected static StringBuilder _classSignature(Class<?> cls, StringBuilder sb, boolean z) {
        Throwable th;
        StringBuilder sb2;
        Class<?> cls2 = cls;
        StringBuilder sb3 = sb;
        boolean z2 = z;
        if (!cls2.isPrimitive()) {
            StringBuilder append = sb3.append('L');
            String name = cls2.getName();
            int length = name.length();
            for (int i = 0; i < length; i++) {
                char charAt = name.charAt(i);
                if (charAt == '.') {
                    charAt = '/';
                }
                StringBuilder append2 = sb3.append(charAt);
            }
            if (z2) {
                StringBuilder append3 = sb3.append(';');
            }
        } else if (cls2 == Boolean.TYPE) {
            StringBuilder append4 = sb3.append('Z');
        } else if (cls2 == Byte.TYPE) {
            StringBuilder append5 = sb3.append('B');
        } else if (cls2 == Short.TYPE) {
            StringBuilder append6 = sb3.append('S');
        } else if (cls2 == Character.TYPE) {
            StringBuilder append7 = sb3.append(Access.CLASS_CONTEXT);
        } else if (cls2 == Integer.TYPE) {
            StringBuilder append8 = sb3.append(Access.INNERCLASS_CONTEXT);
        } else if (cls2 == Long.TYPE) {
            StringBuilder append9 = sb3.append('J');
        } else if (cls2 == Float.TYPE) {
            StringBuilder append10 = sb3.append(Access.FIELD_CONTEXT);
        } else if (cls2 == Double.TYPE) {
            StringBuilder append11 = sb3.append('D');
        } else if (cls2 == Void.TYPE) {
            StringBuilder append12 = sb3.append('V');
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb2.append("Unrecognized primitive type: ").append(cls2.getName()).toString());
            throw th2;
        }
        return sb3;
    }
}
