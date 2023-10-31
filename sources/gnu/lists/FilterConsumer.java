package gnu.lists;

public class FilterConsumer implements XConsumer {
    protected Object attributeType;
    protected Consumer base;
    protected boolean inAttribute;
    protected boolean skipping;

    public FilterConsumer(Consumer base2) {
        this.base = base2;
    }

    /* access modifiers changed from: protected */
    public void beforeContent() {
    }

    /* access modifiers changed from: protected */
    public void beforeNode() {
    }

    public void write(int i) {
        int v = i;
        beforeContent();
        if (!this.skipping) {
            this.base.write(v);
        }
    }

    public void writeBoolean(boolean z) {
        boolean v = z;
        beforeContent();
        if (!this.skipping) {
            this.base.writeBoolean(v);
        }
    }

    public void writeFloat(float f) {
        float v = f;
        beforeContent();
        if (!this.skipping) {
            this.base.writeFloat(v);
        }
    }

    public void writeDouble(double d) {
        double v = d;
        beforeContent();
        if (!this.skipping) {
            this.base.writeDouble(v);
        }
    }

    public void writeInt(int i) {
        int v = i;
        beforeContent();
        if (!this.skipping) {
            this.base.writeInt(v);
        }
    }

    public void writeLong(long j) {
        long v = j;
        beforeContent();
        if (!this.skipping) {
            this.base.writeLong(v);
        }
    }

    public void startDocument() {
        if (!this.skipping) {
            this.base.startDocument();
        }
    }

    public void endDocument() {
        if (!this.skipping) {
            this.base.endDocument();
        }
    }

    public void startElement(Object obj) {
        Object type = obj;
        if (!this.skipping) {
            beforeNode();
            this.base.startElement(type);
        }
    }

    public void endElement() {
        if (!this.skipping) {
            this.base.endElement();
        }
    }

    public void startAttribute(Object obj) {
        Object attrType = obj;
        this.attributeType = attrType;
        this.inAttribute = true;
        if (!this.skipping) {
            beforeNode();
            this.base.startAttribute(attrType);
        }
    }

    public void endAttribute() {
        if (!this.skipping) {
            this.base.endAttribute();
        }
        this.inAttribute = false;
    }

    public void writeComment(char[] cArr, int i, int i2) {
        char[] chars = cArr;
        int offset = i;
        int length = i2;
        if (!this.skipping) {
            beforeNode();
            if (this.base instanceof XConsumer) {
                ((XConsumer) this.base).writeComment(chars, offset, length);
            }
        }
    }

    public void writeProcessingInstruction(String str, char[] cArr, int i, int i2) {
        String target = str;
        char[] content = cArr;
        int offset = i;
        int length = i2;
        if (!this.skipping) {
            beforeNode();
            if (this.base instanceof XConsumer) {
                ((XConsumer) this.base).writeProcessingInstruction(target, content, offset, length);
            }
        }
    }

    public void writeCDATA(char[] cArr, int i, int i2) {
        char[] chars = cArr;
        int offset = i;
        int length = i2;
        beforeContent();
        if (this.skipping) {
            return;
        }
        if (this.base instanceof XConsumer) {
            ((XConsumer) this.base).writeCDATA(chars, offset, length);
        } else {
            this.base.write(chars, offset, length);
        }
    }

    public void beginEntity(Object obj) {
        Object baseUri = obj;
        if (!this.skipping) {
            beforeNode();
            if (this.base instanceof XConsumer) {
                ((XConsumer) this.base).beginEntity(baseUri);
            }
        }
    }

    public void endEntity() {
        if (!this.skipping && (this.base instanceof XConsumer)) {
            ((XConsumer) this.base).endEntity();
        }
    }

    public void writeObject(Object obj) {
        Object v = obj;
        beforeContent();
        if (!this.skipping) {
            this.base.writeObject(v);
        }
    }

    public boolean ignoring() {
        return this.base.ignoring();
    }

    public void write(char[] cArr, int i, int i2) {
        char[] buf = cArr;
        int off = i;
        int len = i2;
        beforeContent();
        if (!this.skipping) {
            this.base.write(buf, off, len);
        }
    }

    public void write(String str) {
        String str2 = str;
        write((CharSequence) str2, 0, str2.length());
    }

    public void write(CharSequence charSequence, int i, int i2) {
        CharSequence str = charSequence;
        int start = i;
        int length = i2;
        beforeContent();
        if (!this.skipping) {
            this.base.write(str, start, length);
        }
    }

    public Consumer append(char c) {
        write((int) c);
        return this;
    }

    public Consumer append(CharSequence charSequence) {
        CharSequence csq = charSequence;
        if (csq == null) {
            csq = "null";
        }
        write(csq, 0, csq.length());
        return this;
    }

    public Consumer append(CharSequence charSequence, int i, int i2) {
        CharSequence csq = charSequence;
        int start = i;
        int end = i2;
        if (csq == null) {
            csq = "null";
        }
        write(csq, start, end - start);
        return this;
    }
}
