package gnu.kawa.sax;

import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.SeqPosition;
import gnu.mapping.Symbol;
import gnu.text.Char;
import gnu.xml.XName;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class ContentConsumer implements Consumer {
    String attrLocalName;
    String attrQName;
    String attrURI;
    AttributesImpl attributes;
    char[] chBuffer;
    int inStartTag;
    String[] names = new String[15];
    int nesting = 0;
    ContentHandler out;
    StringBuilder strBuffer;

    public ContentConsumer() {
        AttributesImpl attributesImpl;
        StringBuilder sb;
        new AttributesImpl();
        this.attributes = attributesImpl;
        new StringBuilder(200);
        this.strBuffer = sb;
    }

    public ContentConsumer(ContentHandler handler) {
        AttributesImpl attributesImpl;
        StringBuilder sb;
        new AttributesImpl();
        this.attributes = attributesImpl;
        new StringBuilder(200);
        this.strBuffer = sb;
        this.out = handler;
    }

    public void error(String method, SAXException ex) {
        Throwable th;
        StringBuilder sb;
        Throwable th2 = th;
        new StringBuilder();
        new RuntimeException(sb.append("caught ").append(ex).append(" in ").append(method).toString());
        throw th2;
    }

    public void endStartTag() {
        if (this.inStartTag == 1) {
            int i = 3 * (this.nesting - 1);
            try {
                this.out.startElement(this.names[i], this.names[i + 1], this.names[i + 2], this.attributes);
            } catch (SAXException e) {
                error("startElement", e);
            }
            this.attributes.clear();
            this.inStartTag = 0;
        }
    }

    public void startElement(Object obj) {
        String namespaceURI;
        String localName;
        Object type = obj;
        if (this.inStartTag == 1) {
            endStartTag();
        }
        flushStrBuffer();
        int i = 3 * this.nesting;
        if (i >= this.names.length) {
            String[] tmp = new String[(2 * i)];
            System.arraycopy(this.names, 0, tmp, 0, i);
            this.names = tmp;
        }
        if (type instanceof Symbol) {
            Symbol sym = (Symbol) type;
            namespaceURI = sym.getNamespaceURI();
            localName = sym.getLocalName();
        } else if (type instanceof XName) {
            XName sym2 = (XName) type;
            namespaceURI = sym2.getNamespaceURI();
            localName = sym2.getLocalName();
        } else {
            namespaceURI = "";
            localName = type.toString();
        }
        this.names[i] = namespaceURI;
        this.names[i + 1] = localName;
        this.names[i + 2] = type.toString();
        this.inStartTag = 1;
        this.nesting++;
    }

    public void startAttribute(Object obj) {
        Object attrType = obj;
        this.attrURI = ((Symbol) attrType).getNamespaceURI();
        this.attrLocalName = ((Symbol) attrType).getLocalName();
        this.attrQName = attrType.toString();
        this.inStartTag = 2;
    }

    public void endAttribute() {
        this.attributes.addAttribute(this.attrURI, this.attrLocalName, this.attrQName, "CDATA", this.strBuffer.toString());
        this.strBuffer.setLength(0);
        this.inStartTag = 1;
    }

    public void startDocument() {
        try {
            this.out.startDocument();
        } catch (SAXException e) {
            error("startDocument", e);
        }
    }

    public void endDocument() {
        try {
            this.out.endDocument();
        } catch (SAXException e) {
            error("endDocument", e);
        }
    }

    public void endElement() {
        endStartTag();
        flushStrBuffer();
        this.nesting--;
        int i = 3 * this.nesting;
        try {
            this.out.endElement(this.names[i], this.names[i + 1], this.names[i + 2]);
        } catch (SAXException e) {
            error("endElement", e);
        }
        this.names[i] = null;
        this.names[i + 1] = null;
        this.names[i + 2] = null;
    }

    /* access modifiers changed from: package-private */
    public void flushStrBuffer() {
        if (this.strBuffer.length() > 0) {
            if (this.chBuffer == null) {
                this.chBuffer = new char[200];
            }
            try {
                int slen = this.strBuffer.length();
                int i = 0;
                while (true) {
                    int start = i;
                    int len = slen - start;
                    if (len <= 0) {
                        this.strBuffer.setLength(0);
                        return;
                    }
                    if (len > this.chBuffer.length) {
                        len = this.chBuffer.length;
                    }
                    this.strBuffer.getChars(start, start + len, this.chBuffer, start);
                    this.out.characters(this.chBuffer, 0, len);
                    i = start + len;
                }
            } catch (SAXException e) {
                error("characters", e);
            }
        }
    }

    public void write(char[] cArr, int i, int i2) {
        char[] buf = cArr;
        int off = i;
        int len = i2;
        if (this.inStartTag == 1) {
            endStartTag();
        }
        if (this.inStartTag == 2) {
            StringBuilder append = this.strBuffer.append(buf, off, len);
            return;
        }
        flushStrBuffer();
        try {
            this.out.characters(buf, off, len);
        } catch (SAXException e) {
            error("characters", e);
        }
    }

    public void write(int i) {
        int v = i;
        if (this.inStartTag == 1) {
            endStartTag();
        }
        if (v >= 65536) {
            StringBuilder append = this.strBuffer.append((char) (((v - 65536) >> 10) + 55296));
            v = (v & 1023) + 56320;
        }
        StringBuilder append2 = this.strBuffer.append((char) v);
    }

    public void write(String str) {
        String v = str;
        if (this.inStartTag == 1) {
            endStartTag();
        }
        StringBuilder append = this.strBuffer.append(v);
    }

    public void write(CharSequence charSequence, int i, int i2) {
        CharSequence str = charSequence;
        int start = i;
        int end = i2;
        if (this.inStartTag == 1) {
            endStartTag();
        }
        StringBuilder append = this.strBuffer.append(str, start, end);
    }

    public ContentConsumer append(char c) {
        write((int) c);
        return this;
    }

    public ContentConsumer append(CharSequence charSequence) {
        CharSequence csq = charSequence;
        if (csq == null) {
            csq = "null";
        }
        write(csq, 0, csq.length());
        return this;
    }

    public ContentConsumer append(CharSequence charSequence, int i, int i2) {
        CharSequence csq = charSequence;
        int start = i;
        int end = i2;
        if (csq == null) {
            csq = "null";
        }
        write(csq, start, end);
        return this;
    }

    public void writeObject(Object obj) {
        Object v = obj;
        if (v instanceof Consumable) {
            ((Consumable) v).consume(this);
        } else if (v instanceof SeqPosition) {
            SeqPosition pos = (SeqPosition) v;
            boolean consumeNext = pos.sequence.consumeNext(pos.ipos, this);
        } else if (v instanceof Char) {
            ((Char) v).print(this);
        } else {
            write(v == null ? "(null)" : v.toString());
        }
    }

    public void writeBoolean(boolean z) {
        boolean v = z;
        if (this.inStartTag == 1) {
            endStartTag();
        }
        StringBuilder append = this.strBuffer.append(v);
    }

    public void writeLong(long j) {
        long v = j;
        if (this.inStartTag == 1) {
            endStartTag();
        }
        StringBuilder append = this.strBuffer.append(v);
    }

    public void writeInt(int i) {
        int v = i;
        if (this.inStartTag == 1) {
            endStartTag();
        }
        StringBuilder append = this.strBuffer.append(v);
    }

    public void writeFloat(float f) {
        float v = f;
        if (this.inStartTag == 1) {
            endStartTag();
        }
        StringBuilder append = this.strBuffer.append(v);
    }

    public void writeDouble(double d) {
        double v = d;
        if (this.inStartTag == 1) {
            endStartTag();
        }
        StringBuilder append = this.strBuffer.append(v);
    }

    public void finalize() {
        flushStrBuffer();
    }

    public boolean ignoring() {
        return false;
    }

    public void setContentHandler(ContentHandler handler) {
        ContentHandler contentHandler = handler;
        this.out = contentHandler;
    }

    public ContentHandler getContentHandler() {
        return this.out;
    }
}
