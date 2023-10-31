package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import com.shaded.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnwrappedPropertyHandler {
    protected final List<SettableBeanProperty> _properties;

    public UnwrappedPropertyHandler() {
        List<SettableBeanProperty> list;
        new ArrayList();
        this._properties = list;
    }

    protected UnwrappedPropertyHandler(List<SettableBeanProperty> list) {
        this._properties = list;
    }

    public void addProperty(SettableBeanProperty settableBeanProperty) {
        boolean add = this._properties.add(settableBeanProperty);
    }

    public UnwrappedPropertyHandler renameAll(NameTransformer nameTransformer) {
        ArrayList arrayList;
        UnwrappedPropertyHandler unwrappedPropertyHandler;
        JsonDeserializer<Object> unwrappingDeserializer;
        NameTransformer nameTransformer2 = nameTransformer;
        new ArrayList(this._properties.size());
        ArrayList arrayList2 = arrayList;
        for (SettableBeanProperty next : this._properties) {
            SettableBeanProperty withName = next.withName(nameTransformer2.transform(next.getName()));
            JsonDeserializer<Object> valueDeserializer = withName.getValueDeserializer();
            if (!(valueDeserializer == null || (unwrappingDeserializer = valueDeserializer.unwrappingDeserializer(nameTransformer2)) == valueDeserializer)) {
                withName = withName.withValueDeserializer(unwrappingDeserializer);
            }
            boolean add = arrayList2.add(withName);
        }
        new UnwrappedPropertyHandler(arrayList2);
        return unwrappedPropertyHandler;
    }

    public Object processUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        TokenBuffer tokenBuffer2 = tokenBuffer;
        int size = this._properties.size();
        for (int i = 0; i < size; i++) {
            SettableBeanProperty settableBeanProperty = this._properties.get(i);
            JsonParser asParser = tokenBuffer2.asParser();
            JsonToken nextToken = asParser.nextToken();
            settableBeanProperty.deserializeAndSet(asParser, deserializationContext2, obj2);
        }
        return obj2;
    }
}
