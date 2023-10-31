package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.Collection;

public abstract class DelegatingDeserializer extends StdDeserializer<Object> implements ContextualDeserializer, ResolvableDeserializer {
    private static final long serialVersionUID = 1;
    protected final JsonDeserializer<?> _delegatee;

    /* access modifiers changed from: protected */
    public abstract JsonDeserializer<?> newDelegatingInstance(JsonDeserializer<?> jsonDeserializer);

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DelegatingDeserializer(com.shaded.fasterxml.jackson.databind.JsonDeserializer<?> r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            java.lang.Class r3 = _figureType(r3)
            r2.<init>((java.lang.Class<?>) r3)
            r2 = r0
            r3 = r1
            r2._delegatee = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.DelegatingDeserializer.<init>(com.shaded.fasterxml.jackson.databind.JsonDeserializer):void");
    }

    private static Class<?> _figureType(JsonDeserializer<?> jsonDeserializer) {
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        if (jsonDeserializer2 instanceof StdDeserializer) {
            return ((StdDeserializer) jsonDeserializer2).getValueClass();
        }
        return Object.class;
    }

    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._delegatee instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) this._delegatee).resolve(deserializationContext2);
        }
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanProperty beanProperty2 = beanProperty;
        JsonDeserializer<?> jsonDeserializer = this._delegatee;
        if (jsonDeserializer instanceof ContextualDeserializer) {
            jsonDeserializer = ((ContextualDeserializer) jsonDeserializer).createContextual(deserializationContext2, beanProperty2);
        }
        return _createContextual(deserializationContext2, beanProperty2, jsonDeserializer);
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty, JsonDeserializer<?> jsonDeserializer) {
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanProperty beanProperty2 = beanProperty;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        if (jsonDeserializer2 != this._delegatee) {
            return newDelegatingInstance(jsonDeserializer2);
        }
        return this;
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return this._delegatee.deserialize(jsonParser, deserializationContext);
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        return this._delegatee.deserialize(jsonParser, deserializationContext, obj);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return this._delegatee.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
    }

    public JsonDeserializer<?> replaceDelegatee(JsonDeserializer<?> jsonDeserializer) {
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        if (jsonDeserializer2 != this._delegatee) {
            return newDelegatingInstance(jsonDeserializer2);
        }
        return this;
    }

    public Object getNullValue() {
        return this._delegatee.getNullValue();
    }

    public Object getEmptyValue() {
        return this._delegatee.getEmptyValue();
    }

    public Collection<Object> getKnownPropertyNames() {
        return this._delegatee.getKnownPropertyNames();
    }

    public boolean isCachable() {
        return false;
    }

    public ObjectIdReader getObjectIdReader() {
        return this._delegatee.getObjectIdReader();
    }

    public JsonDeserializer<?> getDelegatee() {
        return this._delegatee;
    }
}
