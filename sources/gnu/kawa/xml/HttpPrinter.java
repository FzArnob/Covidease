package gnu.kawa.xml;

import com.google.appinventor.components.runtime.util.NanoHTTPD;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.FilterConsumer;
import gnu.lists.UnescapedData;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.xml.XMLPrinter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

public class HttpPrinter extends FilterConsumer {
    Object currentHeader;
    private int elementNesting;
    Vector headers;
    protected OutputStream ostream;
    protected String sawContentType;
    StringBuilder sbuf;
    private int seenStartDocument;
    boolean seenXmlHeader;
    OutPort writer;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HttpPrinter(OutputStream out) {
        super((Consumer) null);
        Vector vector;
        StringBuilder sb;
        new Vector();
        this.headers = vector;
        new StringBuilder(100);
        this.sbuf = sb;
        this.ostream = out;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HttpPrinter(OutPort out) {
        super((Consumer) null);
        Vector vector;
        StringBuilder sb;
        new Vector();
        this.headers = vector;
        new StringBuilder(100);
        this.sbuf = sb;
        this.writer = out;
    }

    public static HttpPrinter make(OutPort out) {
        HttpPrinter httpPrinter;
        new HttpPrinter(out);
        return httpPrinter;
    }

    private void writeRaw(String str) throws IOException {
        String str2 = str;
        if (this.writer != null) {
            this.writer.write(str2);
            return;
        }
        int len = str2.length();
        for (int i = 0; i < len; i++) {
            this.ostream.write((byte) str2.charAt(i));
        }
    }

    /* access modifiers changed from: protected */
    public void beforeNode() {
        if (this.sawContentType == null) {
            addHeader("Content-type", NanoHTTPD.MIME_XML);
        }
        beginData();
    }

    public void printHeader(String label, String value) throws IOException {
        writeRaw(label);
        writeRaw(": ");
        writeRaw(value);
        writeRaw("\n");
    }

    public void printHeaders() throws IOException {
        int num = this.headers.size();
        for (int i = 0; i < num; i += 2) {
            printHeader(this.headers.elementAt(i).toString(), this.headers.elementAt(i + 1).toString());
        }
        writeRaw("\n");
    }

    public void addHeader(String str, String str2) {
        String label = str;
        String value = str2;
        if (label.equalsIgnoreCase("Content-type")) {
            this.sawContentType = value;
        }
        this.headers.addElement(label);
        this.headers.addElement(value);
    }

    public void startAttribute(Object obj) {
        Object attrType = obj;
        if (this.base == null) {
            this.currentHeader = attrType;
            return;
        }
        this.base.startAttribute(attrType);
    }

    public void endAttribute() {
        if (this.currentHeader != null) {
            addHeader(this.currentHeader.toString(), this.sbuf.toString());
            this.sbuf.setLength(0);
            this.currentHeader = null;
            return;
        }
        this.base.endAttribute();
    }

    public void beginData() {
        Throwable th;
        OutPort outPort;
        if (this.base == null) {
            if (this.sawContentType == null) {
                addHeader("Content-type", "text/plain");
            }
            if (this.writer == null) {
                new OutPort(this.ostream);
                this.writer = outPort;
            }
            String style = null;
            if (NanoHTTPD.MIME_HTML.equalsIgnoreCase(this.sawContentType)) {
                style = "html";
            } else if ("application/xhtml+xml".equalsIgnoreCase(this.sawContentType)) {
                style = "xhtml";
            } else if ("text/plain".equalsIgnoreCase(this.sawContentType)) {
                style = "plain";
            }
            this.base = XMLPrinter.make(this.writer, style);
            if (this.seenStartDocument == 0) {
                this.base.startDocument();
                this.seenStartDocument = 1;
            }
            try {
                printHeaders();
            } catch (Throwable th2) {
                Throwable ex = th2;
                Throwable th3 = th;
                new RuntimeException(ex.toString());
                throw th3;
            }
        }
        Consumer append = append((CharSequence) this.sbuf);
        this.sbuf.setLength(0);
    }

    public void startElement(Object obj) {
        Object type = obj;
        if (this.sawContentType == null) {
            addHeader("Content-type", !this.seenXmlHeader ? NanoHTTPD.MIME_HTML : (!(type instanceof Symbol) || !"html".equals(((Symbol) type).getLocalPart())) ? NanoHTTPD.MIME_XML : "text/xhtml");
        }
        beginData();
        this.base.startElement(type);
        this.elementNesting++;
    }

    public void endElement() {
        super.endElement();
        this.elementNesting--;
        if (this.elementNesting == 0 && this.seenStartDocument == 1) {
            endDocument();
        }
    }

    public void writeObject(Object obj) {
        Object v = obj;
        if (!(v instanceof Consumable) || (v instanceof UnescapedData)) {
            beginData();
            super.writeObject(v);
            return;
        }
        ((Consumable) v).consume(this);
    }

    public void write(CharSequence charSequence, int i, int i2) {
        CharSequence str = charSequence;
        int start = i;
        int length = i2;
        if (this.base == null) {
            StringBuilder append = this.sbuf.append(str, start, start + length);
        } else {
            this.base.write(str, start, length);
        }
    }

    public void write(char[] cArr, int i, int i2) {
        char[] buf = cArr;
        int off = i;
        int len = i2;
        if (this.base == null) {
            StringBuilder append = this.sbuf.append(buf, off, len);
        } else {
            this.base.write(buf, off, len);
        }
    }

    public void startDocument() {
        if (this.base != null) {
            this.base.startDocument();
        }
        this.seenStartDocument = 2;
    }

    public void endDocument() {
        if (this.base != null) {
            this.base.endDocument();
        }
        try {
            if (this.sawContentType == null) {
                addHeader("Content-type", "text/plain");
            }
            if (this.sbuf.length() > 0) {
                String str = this.sbuf.toString();
                this.sbuf.setLength(0);
                if (this.writer != null) {
                    this.writer.write(str);
                } else {
                    this.ostream.write(str.getBytes());
                }
            }
            if (this.writer != null) {
                this.writer.close();
            }
            if (this.ostream != null) {
                this.ostream.flush();
            }
        } catch (Throwable th) {
            Throwable th2 = th;
        }
    }

    public boolean reset(boolean headersAlso) {
        if (headersAlso) {
            this.headers.clear();
            this.sawContentType = null;
            this.currentHeader = null;
            this.elementNesting = 0;
        }
        this.sbuf.setLength(0);
        this.base = null;
        boolean ok = true;
        if (this.ostream != null) {
            ok = this.writer == null;
            this.writer = null;
        }
        return ok;
    }
}
