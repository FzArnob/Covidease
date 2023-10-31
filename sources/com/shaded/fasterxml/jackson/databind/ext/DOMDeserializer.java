package com.shaded.fasterxml.jackson.databind.ext;

import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import java.io.Reader;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public abstract class DOMDeserializer<T> extends FromStringDeserializer<T> {
    private static final DocumentBuilderFactory _parserFactory = DocumentBuilderFactory.newInstance();
    private static final long serialVersionUID = 1;

    public abstract T _deserialize(String str, DeserializationContext deserializationContext);

    static {
        _parserFactory.setNamespaceAware(true);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DOMDeserializer(Class<T> cls) {
        super(cls);
    }

    /* access modifiers changed from: protected */
    public final Document parse(String str) throws IllegalArgumentException {
        Throwable th;
        StringBuilder sb;
        InputSource inputSource;
        Reader reader;
        try {
            new StringReader(str);
            new InputSource(reader);
            return _parserFactory.newDocumentBuilder().parse(inputSource);
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Failed to parse JSON String as XML: ").append(exc.getMessage()).toString(), exc);
            throw th2;
        }
    }

    public static class NodeDeserializer extends DOMDeserializer<Node> {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public NodeDeserializer() {
            super(Node.class);
        }

        public Node _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return parse(str);
        }
    }

    public static class DocumentDeserializer extends DOMDeserializer<Document> {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DocumentDeserializer() {
            super(Document.class);
        }

        public Document _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return parse(str);
        }
    }
}
