package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JsonMappingException extends JsonProcessingException {
    static final int MAX_REFS_TO_LIST = 1000;
    private static final long serialVersionUID = 1;
    protected LinkedList<Reference> _path;

    public static class Reference implements Serializable {
        private static final long serialVersionUID = 1;
        protected String _fieldName;
        protected Object _from;
        protected int _index = -1;

        protected Reference() {
        }

        public Reference(Object obj) {
            this._from = obj;
        }

        public Reference(Object obj, String str) {
            Throwable th;
            String str2 = str;
            this._from = obj;
            if (str2 == null) {
                Throwable th2 = th;
                new NullPointerException("Can not pass null fieldName");
                throw th2;
            }
            this._fieldName = str2;
        }

        public Reference(Object obj, int i) {
            this._from = obj;
            this._index = i;
        }

        public void setFrom(Object obj) {
            Object obj2 = obj;
            this._from = obj2;
        }

        public void setFieldName(String str) {
            String str2 = str;
            this._fieldName = str2;
        }

        public void setIndex(int i) {
            int i2 = i;
            this._index = i2;
        }

        public Object getFrom() {
            return this._from;
        }

        public String getFieldName() {
            return this._fieldName;
        }

        public int getIndex() {
            return this._index;
        }

        public String toString() {
            StringBuilder sb;
            Class<?> cls;
            new StringBuilder();
            StringBuilder sb2 = sb;
            if (this._from instanceof Class) {
                cls = (Class) this._from;
            } else {
                cls = this._from.getClass();
            }
            Class<?> cls2 = cls;
            Package packageR = cls2.getPackage();
            if (packageR != null) {
                StringBuilder append = sb2.append(packageR.getName());
                StringBuilder append2 = sb2.append('.');
            }
            StringBuilder append3 = sb2.append(cls2.getSimpleName());
            StringBuilder append4 = sb2.append('[');
            if (this._fieldName != null) {
                StringBuilder append5 = sb2.append('\"');
                StringBuilder append6 = sb2.append(this._fieldName);
                StringBuilder append7 = sb2.append('\"');
            } else if (this._index >= 0) {
                StringBuilder append8 = sb2.append(this._index);
            } else {
                StringBuilder append9 = sb2.append('?');
            }
            StringBuilder append10 = sb2.append(']');
            return sb2.toString();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonMappingException(String str) {
        super(str);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonMappingException(String str, Throwable th) {
        super(str, th);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonMappingException(String str, JsonLocation jsonLocation) {
        super(str, jsonLocation);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonMappingException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str, jsonLocation, th);
    }

    public static JsonMappingException from(JsonParser jsonParser, String str) {
        JsonParser jsonParser2 = jsonParser;
        JsonMappingException jsonMappingException = r6;
        JsonMappingException jsonMappingException2 = new JsonMappingException(str, jsonParser2 == null ? null : jsonParser2.getTokenLocation());
        return jsonMappingException;
    }

    public static JsonMappingException from(JsonParser jsonParser, String str, Throwable th) {
        JsonParser jsonParser2 = jsonParser;
        JsonMappingException jsonMappingException = r8;
        JsonMappingException jsonMappingException2 = new JsonMappingException(str, jsonParser2 == null ? null : jsonParser2.getTokenLocation(), th);
        return jsonMappingException;
    }

    public static JsonMappingException fromUnexpectedIOE(IOException iOException) {
        JsonMappingException jsonMappingException;
        StringBuilder sb;
        IOException iOException2 = iOException;
        new StringBuilder();
        new JsonMappingException(sb.append("Unexpected IOException (of type ").append(iOException2.getClass().getName()).append("): ").append(iOException2.getMessage()).toString(), (JsonLocation) null, iOException2);
        return jsonMappingException;
    }

    public static JsonMappingException wrapWithPath(Throwable th, Object obj, String str) {
        Reference reference;
        new Reference(obj, str);
        return wrapWithPath(th, reference);
    }

    public static JsonMappingException wrapWithPath(Throwable th, Object obj, int i) {
        Reference reference;
        new Reference(obj, i);
        return wrapWithPath(th, reference);
    }

    public static JsonMappingException wrapWithPath(Throwable th, Reference reference) {
        JsonMappingException jsonMappingException;
        JsonMappingException jsonMappingException2;
        StringBuilder sb;
        Throwable th2 = th;
        Reference reference2 = reference;
        if (th2 instanceof JsonMappingException) {
            jsonMappingException2 = (JsonMappingException) th2;
        } else {
            String message = th2.getMessage();
            if (message == null || message.length() == 0) {
                new StringBuilder();
                message = sb.append("(was ").append(th2.getClass().getName()).append(")").toString();
            }
            new JsonMappingException(message, (JsonLocation) null, th2);
            jsonMappingException2 = jsonMappingException;
        }
        jsonMappingException2.prependPath(reference2);
        return jsonMappingException2;
    }

    public List<Reference> getPath() {
        if (this._path == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(this._path);
    }

    public String getPathReference() {
        StringBuilder sb;
        new StringBuilder();
        return getPathReference(sb).toString();
    }

    public StringBuilder getPathReference(StringBuilder sb) {
        StringBuilder sb2 = sb;
        _appendPathDesc(sb2);
        return sb2;
    }

    public void prependPath(Object obj, String str) {
        Reference reference;
        new Reference(obj, str);
        prependPath(reference);
    }

    public void prependPath(Object obj, int i) {
        Reference reference;
        new Reference(obj, i);
        prependPath(reference);
    }

    public void prependPath(Reference reference) {
        LinkedList<Reference> linkedList;
        Reference reference2 = reference;
        if (this._path == null) {
            new LinkedList<>();
            this._path = linkedList;
        }
        if (this._path.size() < 1000) {
            this._path.addFirst(reference2);
        }
    }

    public String getLocalizedMessage() {
        return _buildMessage();
    }

    public String getMessage() {
        return _buildMessage();
    }

    /* access modifiers changed from: protected */
    public String _buildMessage() {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        String message = super.getMessage();
        if (this._path == null) {
            return message;
        }
        if (message == null) {
            sb2 = sb3;
            new StringBuilder();
        } else {
            sb2 = sb;
            new StringBuilder(message);
        }
        StringBuilder sb4 = sb2;
        StringBuilder append = sb4.append(" (through reference chain: ");
        StringBuilder pathReference = getPathReference(sb4);
        StringBuilder append2 = pathReference.append(')');
        return pathReference.toString();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(getClass().getName()).append(": ").append(getMessage()).toString();
    }

    /* access modifiers changed from: protected */
    public void _appendPathDesc(StringBuilder sb) {
        StringBuilder sb2 = sb;
        if (this._path != null) {
            Iterator it = this._path.iterator();
            while (it.hasNext()) {
                StringBuilder append = sb2.append(((Reference) it.next()).toString());
                if (it.hasNext()) {
                    StringBuilder append2 = sb2.append("->");
                }
            }
        }
    }
}
