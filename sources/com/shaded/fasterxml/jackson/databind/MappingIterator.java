package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.core.FormatSchema;
import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonToken;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MappingIterator<T> implements Iterator<T>, Closeable {
    protected static final MappingIterator<?> EMPTY_ITERATOR;
    protected final boolean _closeParser;
    protected final DeserializationContext _context;
    protected final JsonDeserializer<T> _deserializer;
    protected boolean _hasNextChecked;
    protected JsonParser _parser;
    protected final JavaType _type;
    protected final T _updatedValue;

    static {
        MappingIterator<?> mappingIterator;
        new MappingIterator<>((JavaType) null, (JsonParser) null, (DeserializationContext) null, (JsonDeserializer<?>) null, false, (Object) null);
        EMPTY_ITERATOR = mappingIterator;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    protected MappingIterator(JavaType javaType, JsonParser jsonParser, DeserializationContext deserializationContext, JsonDeserializer<?> jsonDeserializer) {
        this(javaType, jsonParser, deserializationContext, jsonDeserializer, true, (Object) null);
    }

    protected MappingIterator(JavaType javaType, JsonParser jsonParser, DeserializationContext deserializationContext, JsonDeserializer<?> jsonDeserializer, boolean z, Object obj) {
        JsonParser jsonParser2 = jsonParser;
        boolean z2 = z;
        Object obj2 = obj;
        this._type = javaType;
        this._parser = jsonParser2;
        this._context = deserializationContext;
        this._deserializer = jsonDeserializer;
        this._closeParser = z2;
        if (obj2 == null) {
            this._updatedValue = null;
        } else {
            this._updatedValue = obj2;
        }
        if (z2 && jsonParser2 != null && jsonParser2.getCurrentToken() == JsonToken.START_ARRAY) {
            jsonParser2.clearCurrentToken();
        }
    }

    protected static <T> MappingIterator<T> emptyIterator() {
        return EMPTY_ITERATOR;
    }

    public boolean hasNext() {
        Throwable th;
        Throwable th2;
        try {
            return hasNextValue();
        } catch (JsonMappingException e) {
            JsonMappingException jsonMappingException = e;
            Throwable th3 = th2;
            new RuntimeJsonMappingException(jsonMappingException.getMessage(), jsonMappingException);
            throw th3;
        } catch (IOException e2) {
            IOException iOException = e2;
            Throwable th4 = th;
            new RuntimeException(iOException.getMessage(), iOException);
            throw th4;
        }
    }

    public T next() {
        Throwable th;
        Throwable th2;
        try {
            return nextValue();
        } catch (JsonMappingException e) {
            JsonMappingException jsonMappingException = e;
            Throwable th3 = th2;
            new RuntimeJsonMappingException(jsonMappingException.getMessage(), jsonMappingException);
            throw th3;
        } catch (IOException e2) {
            IOException iOException = e2;
            Throwable th4 = th;
            new RuntimeException(iOException.getMessage(), iOException);
            throw th4;
        }
    }

    public void remove() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    public void close() throws IOException {
        if (this._parser != null) {
            this._parser.close();
        }
    }

    public boolean hasNextValue() throws IOException {
        JsonToken nextToken;
        if (this._parser == null) {
            return false;
        }
        if (!this._hasNextChecked) {
            JsonToken currentToken = this._parser.getCurrentToken();
            this._hasNextChecked = true;
            if (currentToken == null && ((nextToken = this._parser.nextToken()) == null || nextToken == JsonToken.END_ARRAY)) {
                JsonParser jsonParser = this._parser;
                this._parser = null;
                if (this._closeParser) {
                    jsonParser.close();
                }
                return false;
            }
        }
        return true;
    }

    public T nextValue() throws IOException {
        T t;
        Throwable th;
        Throwable th2;
        if (!this._hasNextChecked && !hasNextValue()) {
            Throwable th3 = th2;
            new NoSuchElementException();
            throw th3;
        } else if (this._parser == null) {
            Throwable th4 = th;
            new NoSuchElementException();
            throw th4;
        } else {
            this._hasNextChecked = false;
            if (this._updatedValue == null) {
                t = this._deserializer.deserialize(this._parser, this._context);
            } else {
                T deserialize = this._deserializer.deserialize(this._parser, this._context, this._updatedValue);
                t = this._updatedValue;
            }
            this._parser.clearCurrentToken();
            return t;
        }
    }

    public List<T> readAll() throws IOException {
        List list;
        new ArrayList();
        return readAll(list);
    }

    public List<T> readAll(List<T> list) throws IOException {
        List<T> list2 = list;
        while (hasNextValue()) {
            boolean add = list2.add(nextValue());
        }
        return list2;
    }

    public JsonParser getParser() {
        return this._parser;
    }

    public FormatSchema getParserSchema() {
        return this._parser.getSchema();
    }

    public JsonLocation getCurrentLocation() {
        return this._parser.getCurrentLocation();
    }
}
