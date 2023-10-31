package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;

public class StdDelegatingDeserializer<T> extends StdDeserializer<T> implements ContextualDeserializer, ResolvableDeserializer {
    private static final long serialVersionUID = 1;
    protected final Converter<Object, T> _converter;
    protected final JsonDeserializer<Object> _delegateDeserializer;
    protected final JavaType _delegateType;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StdDelegatingDeserializer(Converter<?, T> converter) {
        super((Class<?>) Object.class);
        this._converter = converter;
        this._delegateType = null;
        this._delegateDeserializer = null;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public StdDelegatingDeserializer(com.shaded.fasterxml.jackson.databind.util.Converter<java.lang.Object, T> r7, com.shaded.fasterxml.jackson.databind.JavaType r8, com.shaded.fasterxml.jackson.databind.JsonDeserializer<?> r9) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r0
            r5 = r2
            r4.<init>((com.shaded.fasterxml.jackson.databind.JavaType) r5)
            r4 = r0
            r5 = r1
            r4._converter = r5
            r4 = r0
            r5 = r2
            r4._delegateType = r5
            r4 = r0
            r5 = r3
            r4._delegateDeserializer = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer.<init>(com.shaded.fasterxml.jackson.databind.util.Converter, com.shaded.fasterxml.jackson.databind.JavaType, com.shaded.fasterxml.jackson.databind.JsonDeserializer):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.shaded.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer<T> withDelegate(com.shaded.fasterxml.jackson.databind.util.Converter<java.lang.Object, T> r11, com.shaded.fasterxml.jackson.databind.JavaType r12, com.shaded.fasterxml.jackson.databind.JsonDeserializer<?> r13) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r0
            java.lang.Class r4 = r4.getClass()
            java.lang.Class<com.shaded.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer> r5 = com.shaded.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer.class
            if (r4 == r5) goto L_0x003d
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            r9 = r4
            r4 = r9
            r5 = r9
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r9 = r6
            r6 = r9
            r7 = r9
            r7.<init>()
            java.lang.String r7 = "Sub-class "
            java.lang.StringBuilder r6 = r6.append(r7)
            r7 = r0
            java.lang.Class r7 = r7.getClass()
            java.lang.String r7 = r7.getName()
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = " must override 'withDelegate'"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r4
        L_0x003d:
            com.shaded.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer r4 = new com.shaded.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer
            r9 = r4
            r4 = r9
            r5 = r9
            r6 = r1
            r7 = r2
            r8 = r3
            r5.<init>(r6, r7, r8)
            r0 = r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer.withDelegate(com.shaded.fasterxml.jackson.databind.util.Converter, com.shaded.fasterxml.jackson.databind.JavaType, com.shaded.fasterxml.jackson.databind.JsonDeserializer):com.shaded.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer");
    }

    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._delegateDeserializer != null && (this._delegateDeserializer instanceof ResolvableDeserializer)) {
            ((ResolvableDeserializer) this._delegateDeserializer).resolve(deserializationContext2);
        }
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<?> createContextual;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanProperty beanProperty2 = beanProperty;
        if (this._delegateDeserializer == null) {
            JavaType inputType = this._converter.getInputType(deserializationContext2.getTypeFactory());
            return withDelegate(this._converter, inputType, deserializationContext2.findContextualValueDeserializer(inputType, beanProperty2));
        } else if ((this._delegateDeserializer instanceof ContextualDeserializer) && (createContextual = ((ContextualDeserializer) this._delegateDeserializer).createContextual(deserializationContext2, beanProperty2)) != this._delegateDeserializer) {
            return withDelegate(this._converter, this._delegateType, createContextual);
        } else {
            return this;
        }
    }

    public JsonDeserializer<?> getDelegatee() {
        return this._delegateDeserializer;
    }

    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Object deserialize = this._delegateDeserializer.deserialize(jsonParser, deserializationContext);
        if (deserialize == null) {
            return null;
        }
        return convertValue(deserialize);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        Object deserializeWithType = this._delegateDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
        if (deserializeWithType == null) {
            return null;
        }
        return convertValue(deserializeWithType);
    }

    /* access modifiers changed from: protected */
    public T convertValue(Object obj) {
        return this._converter.convert(obj);
    }
}
