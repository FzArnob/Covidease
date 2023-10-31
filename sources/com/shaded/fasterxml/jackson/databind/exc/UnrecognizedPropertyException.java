package com.shaded.fasterxml.jackson.databind.exc;

import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class UnrecognizedPropertyException extends JsonMappingException {
    private static final int MAX_DESC_LENGTH = 200;
    private static final long serialVersionUID = 1;
    protected transient String _propertiesAsString;
    protected final Collection<Object> _propertyIds;
    protected final Class<?> _referringClass;
    protected final String _unrecognizedPropertyName;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnrecognizedPropertyException(String str, JsonLocation jsonLocation, Class<?> cls, String str2, Collection<Object> collection) {
        super(str, jsonLocation);
        this._referringClass = cls;
        this._unrecognizedPropertyName = str2;
        this._propertyIds = collection;
    }

    public static UnrecognizedPropertyException from(JsonParser jsonParser, Object obj, String str, Collection<Object> collection) {
        Class<?> cls;
        StringBuilder sb;
        UnrecognizedPropertyException unrecognizedPropertyException;
        Throwable th;
        JsonParser jsonParser2 = jsonParser;
        Object obj2 = obj;
        String str2 = str;
        Collection<Object> collection2 = collection;
        if (obj2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        if (obj2 instanceof Class) {
            cls = (Class) obj2;
        } else {
            cls = obj2.getClass();
        }
        new StringBuilder();
        new UnrecognizedPropertyException(sb.append("Unrecognized field \"").append(str2).append("\" (class ").append(cls.getName()).append("), not marked as ignorable").toString(), jsonParser2.getCurrentLocation(), cls, str2, collection2);
        UnrecognizedPropertyException unrecognizedPropertyException2 = unrecognizedPropertyException;
        unrecognizedPropertyException2.prependPath(obj2, str2);
        return unrecognizedPropertyException2;
    }

    public String getMessageSuffix() {
        StringBuilder sb;
        String str = this._propertiesAsString;
        if (str == null && this._propertyIds != null) {
            new StringBuilder(100);
            StringBuilder sb2 = sb;
            int size = this._propertyIds.size();
            if (size != 1) {
                StringBuilder append = sb2.append(" (").append(size).append(" known properties: ");
                Iterator<Object> it = this._propertyIds.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    StringBuilder append2 = sb2.append(", \"");
                    StringBuilder append3 = sb2.append(String.valueOf(it.next()));
                    StringBuilder append4 = sb2.append('\"');
                    if (sb2.length() > 200) {
                        StringBuilder append5 = sb2.append(" [truncated]");
                        break;
                    }
                }
            } else {
                StringBuilder append6 = sb2.append(" (one known property: \"");
                StringBuilder append7 = sb2.append(String.valueOf(this._propertyIds.iterator().next()));
                StringBuilder append8 = sb2.append('\"');
            }
            StringBuilder append9 = sb2.append("])");
            String sb3 = sb2.toString();
            str = sb3;
            this._propertiesAsString = sb3;
        }
        return str;
    }

    public Class<?> getReferringClass() {
        return this._referringClass;
    }

    public String getUnrecognizedPropertyName() {
        return this._unrecognizedPropertyName;
    }

    public Collection<Object> getKnownPropertyIds() {
        if (this._propertyIds == null) {
            return null;
        }
        return Collections.unmodifiableCollection(this._propertyIds);
    }
}
