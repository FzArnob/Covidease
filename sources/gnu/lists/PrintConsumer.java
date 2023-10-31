package gnu.lists;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class PrintConsumer extends PrintWriter implements Appendable, Consumer {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PrintConsumer(gnu.lists.Consumer r9, boolean r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            r4 = r1
            boolean r4 = r4 instanceof java.io.Writer
            if (r4 == 0) goto L_0x0011
            r4 = r1
            java.io.Writer r4 = (java.io.Writer) r4
        L_0x000c:
            r5 = r2
            r3.<init>(r4, r5)
            return
        L_0x0011:
            gnu.lists.ConsumerWriter r4 = new gnu.lists.ConsumerWriter
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = r1
            r5.<init>(r6)
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.PrintConsumer.<init>(gnu.lists.Consumer, boolean):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PrintConsumer(OutputStream out, boolean autoFlush) {
        super(out, autoFlush);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PrintConsumer(Writer out, boolean autoFlush) {
        super(out, autoFlush);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PrintConsumer(Writer out) {
        super(out);
    }

    /* access modifiers changed from: protected */
    public void startNumber() {
    }

    /* access modifiers changed from: protected */
    public void endNumber() {
    }

    public PrintConsumer append(char c) {
        print(c);
        return this;
    }

    public PrintConsumer append(CharSequence charSequence) {
        CharSequence csq = charSequence;
        if (csq == null) {
            csq = "null";
        }
        PrintConsumer append = append(csq, 0, csq.length());
        return this;
    }

    public PrintConsumer append(CharSequence charSequence, int i, int i2) {
        CharSequence csq = charSequence;
        int start = i;
        int end = i2;
        if (csq == null) {
            csq = "null";
        }
        for (int i3 = start; i3 < end; i3++) {
            PrintConsumer append = append(csq.charAt(i3));
        }
        return this;
    }

    public void write(CharSequence charSequence, int i, int i2) {
        CharSequence csq = charSequence;
        int start = i;
        int end = i2;
        if (csq instanceof String) {
            write((String) csq, start, end);
            return;
        }
        for (int i3 = start; i3 < end; i3++) {
            write((int) csq.charAt(i3));
        }
    }

    public void writeBoolean(boolean v) {
        print(v);
    }

    public void writeFloat(float v) {
        startNumber();
        print(v);
        endNumber();
    }

    public void writeDouble(double v) {
        startNumber();
        print(v);
        endNumber();
    }

    public void writeInt(int v) {
        startNumber();
        print(v);
        endNumber();
    }

    public void writeLong(long v) {
        startNumber();
        print(v);
        endNumber();
    }

    public void startDocument() {
    }

    public void endDocument() {
    }

    public void startElement(Object type) {
    }

    public void endElement() {
    }

    public void startAttribute(Object attrType) {
    }

    public void endAttribute() {
    }

    public void writeObject(Object v) {
        print(v);
    }

    public boolean ignoring() {
        return false;
    }
}
