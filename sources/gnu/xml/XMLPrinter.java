package gnu.xml;

import gnu.expr.Keyword;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumable;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.UnescapedData;
import gnu.lists.XConsumer;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import gnu.math.DFloNum;
import gnu.math.RealNum;
import gnu.text.Char;
import gnu.text.PrettyWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;

public class XMLPrinter extends OutPort implements PositionConsumer, XConsumer {
    private static final int COMMENT = -5;
    private static final int ELEMENT_END = -4;
    private static final int ELEMENT_START = -3;
    static final String HtmlEmptyTags = "/area/base/basefont/br/col/frame/hr/img/input/isindex/link/meta/para/";
    private static final int KEYWORD = -6;
    private static final int PROC_INST = -7;
    private static final int WORD = -2;
    public static final ThreadLocation doctypePublic;
    public static final ThreadLocation doctypeSystem;
    public static final ThreadLocation indentLoc;
    boolean canonicalize = true;
    public boolean canonicalizeCDATA;
    Object[] elementNameStack = new Object[20];
    int elementNesting;
    public boolean escapeNonAscii = true;
    public boolean escapeText = true;
    boolean inAttribute = false;
    int inComment;
    boolean inDocument;
    boolean inStartTag = false;
    public boolean indentAttributes;
    boolean isHtml = false;
    boolean isHtmlOrXhtml = false;
    NamespaceBinding namespaceBindings = NamespaceBinding.predefinedXML;
    NamespaceBinding[] namespaceSaveStack = new NamespaceBinding[20];
    boolean needXMLdecl = false;
    int prev = 32;
    public int printIndent = -1;
    boolean printXMLdecl = false;
    char savedHighSurrogate;
    public boolean strict;
    Object style;
    boolean undeclareNamespaces = false;
    public int useEmptyElementTag = 2;

    public void setPrintXMLdecl(boolean value) {
        boolean z = value;
        this.printXMLdecl = z;
    }

    static {
        ThreadLocation threadLocation;
        ThreadLocation threadLocation2;
        ThreadLocation threadLocation3;
        new ThreadLocation("doctype-system");
        doctypeSystem = threadLocation;
        new ThreadLocation("doctype-public");
        doctypePublic = threadLocation2;
        new ThreadLocation("xml-indent");
        indentLoc = threadLocation3;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public XMLPrinter(OutPort out, boolean autoFlush) {
        super(out, autoFlush);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public XMLPrinter(Writer out, boolean autoFlush) {
        super(out, autoFlush);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XMLPrinter(java.io.OutputStream r9, boolean r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            java.io.OutputStreamWriter r4 = new java.io.OutputStreamWriter
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = r1
            r5.<init>(r6)
            r5 = 1
            r6 = r2
            r3.<init>((java.io.Writer) r4, (boolean) r5, (boolean) r6)
            r3 = r0
            r4 = -1
            r3.printIndent = r4
            r3 = r0
            r4 = 0
            r3.printXMLdecl = r4
            r3 = r0
            r4 = 0
            r3.inAttribute = r4
            r3 = r0
            r4 = 0
            r3.inStartTag = r4
            r3 = r0
            r4 = 0
            r3.needXMLdecl = r4
            r3 = r0
            r4 = 1
            r3.canonicalize = r4
            r3 = r0
            r4 = 2
            r3.useEmptyElementTag = r4
            r3 = r0
            r4 = 1
            r3.escapeText = r4
            r3 = r0
            r4 = 1
            r3.escapeNonAscii = r4
            r3 = r0
            r4 = 0
            r3.isHtml = r4
            r3 = r0
            r4 = 0
            r3.isHtmlOrXhtml = r4
            r3 = r0
            r4 = 0
            r3.undeclareNamespaces = r4
            r3 = r0
            gnu.xml.NamespaceBinding r4 = gnu.xml.NamespaceBinding.predefinedXML
            r3.namespaceBindings = r4
            r3 = r0
            r4 = 20
            gnu.xml.NamespaceBinding[] r4 = new gnu.xml.NamespaceBinding[r4]
            r3.namespaceSaveStack = r4
            r3 = r0
            r4 = 20
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r3.elementNameStack = r4
            r3 = r0
            r4 = 32
            r3.prev = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xml.XMLPrinter.<init>(java.io.OutputStream, boolean):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public XMLPrinter(Writer out) {
        super(out);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XMLPrinter(java.io.OutputStream r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            java.io.OutputStreamWriter r3 = new java.io.OutputStreamWriter
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            r4.<init>(r5)
            r4 = 0
            r5 = 0
            r2.<init>((java.io.Writer) r3, (boolean) r4, (boolean) r5)
            r2 = r0
            r3 = -1
            r2.printIndent = r3
            r2 = r0
            r3 = 0
            r2.printXMLdecl = r3
            r2 = r0
            r3 = 0
            r2.inAttribute = r3
            r2 = r0
            r3 = 0
            r2.inStartTag = r3
            r2 = r0
            r3 = 0
            r2.needXMLdecl = r3
            r2 = r0
            r3 = 1
            r2.canonicalize = r3
            r2 = r0
            r3 = 2
            r2.useEmptyElementTag = r3
            r2 = r0
            r3 = 1
            r2.escapeText = r3
            r2 = r0
            r3 = 1
            r2.escapeNonAscii = r3
            r2 = r0
            r3 = 0
            r2.isHtml = r3
            r2 = r0
            r3 = 0
            r2.isHtmlOrXhtml = r3
            r2 = r0
            r3 = 0
            r2.undeclareNamespaces = r3
            r2 = r0
            gnu.xml.NamespaceBinding r3 = gnu.xml.NamespaceBinding.predefinedXML
            r2.namespaceBindings = r3
            r2 = r0
            r3 = 20
            gnu.xml.NamespaceBinding[] r3 = new gnu.xml.NamespaceBinding[r3]
            r2.namespaceSaveStack = r3
            r2 = r0
            r3 = 20
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r2.elementNameStack = r3
            r2 = r0
            r3 = 32
            r2.prev = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xml.XMLPrinter.<init>(java.io.OutputStream):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XMLPrinter(java.io.OutputStream r10, gnu.text.Path r11) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r0
            java.io.OutputStreamWriter r4 = new java.io.OutputStreamWriter
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r5.<init>(r6)
            r5 = 1
            r6 = 0
            r7 = r2
            r3.<init>(r4, r5, r6, r7)
            r3 = r0
            r4 = -1
            r3.printIndent = r4
            r3 = r0
            r4 = 0
            r3.printXMLdecl = r4
            r3 = r0
            r4 = 0
            r3.inAttribute = r4
            r3 = r0
            r4 = 0
            r3.inStartTag = r4
            r3 = r0
            r4 = 0
            r3.needXMLdecl = r4
            r3 = r0
            r4 = 1
            r3.canonicalize = r4
            r3 = r0
            r4 = 2
            r3.useEmptyElementTag = r4
            r3 = r0
            r4 = 1
            r3.escapeText = r4
            r3 = r0
            r4 = 1
            r3.escapeNonAscii = r4
            r3 = r0
            r4 = 0
            r3.isHtml = r4
            r3 = r0
            r4 = 0
            r3.isHtmlOrXhtml = r4
            r3 = r0
            r4 = 0
            r3.undeclareNamespaces = r4
            r3 = r0
            gnu.xml.NamespaceBinding r4 = gnu.xml.NamespaceBinding.predefinedXML
            r3.namespaceBindings = r4
            r3 = r0
            r4 = 20
            gnu.xml.NamespaceBinding[] r4 = new gnu.xml.NamespaceBinding[r4]
            r3.namespaceSaveStack = r4
            r3 = r0
            r4 = 20
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r3.elementNameStack = r4
            r3 = r0
            r4 = 32
            r3.prev = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xml.XMLPrinter.<init>(java.io.OutputStream, gnu.text.Path):void");
    }

    public static XMLPrinter make(OutPort out, Object style2) {
        XMLPrinter xMLPrinter;
        new XMLPrinter(out, true);
        XMLPrinter xout = xMLPrinter;
        xout.setStyle(style2);
        return xout;
    }

    public static String toString(Object value) {
        StringWriter stringWriter;
        XMLPrinter xMLPrinter;
        new StringWriter();
        StringWriter stringWriter2 = stringWriter;
        new XMLPrinter((Writer) stringWriter2);
        xMLPrinter.writeObject(value);
        return stringWriter2.toString();
    }

    public void setStyle(Object obj) {
        Object style2 = obj;
        this.style = style2;
        this.useEmptyElementTag = this.canonicalize ? 0 : 1;
        if ("html".equals(style2)) {
            this.isHtml = true;
            this.isHtmlOrXhtml = true;
            this.useEmptyElementTag = 2;
            if (this.namespaceBindings == NamespaceBinding.predefinedXML) {
                this.namespaceBindings = XmlNamespace.HTML_BINDINGS;
            }
        } else if (this.namespaceBindings == XmlNamespace.HTML_BINDINGS) {
            this.namespaceBindings = NamespaceBinding.predefinedXML;
        }
        if ("xhtml".equals(style2)) {
            this.isHtmlOrXhtml = true;
            this.useEmptyElementTag = 2;
        }
        if ("plain".equals(style2)) {
            this.escapeText = false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean mustHexEscape(int i) {
        int v = i;
        return (v >= 127 && (v <= 159 || this.escapeNonAscii)) || v == 8232 || (v < 32 && (this.inAttribute || !(v == 9 || v == 10)));
    }

    public void write(int i) {
        StringBuilder sb;
        int v = i;
        closeTag();
        if (this.printIndent >= 0 && (v == 13 || v == 10)) {
            if (!(v == 10 && this.prev == 13)) {
                writeBreak(82);
            }
            if (this.inComment > 0) {
                this.inComment = 1;
            }
        } else if (!this.escapeText) {
            this.bout.write(v);
            this.prev = v;
        } else if (this.inComment > 0) {
            if (v != 45) {
                this.inComment = 1;
            } else if (this.inComment == 1) {
                this.inComment = 2;
            } else {
                this.bout.write(32);
            }
            super.write(v);
        } else {
            this.prev = 59;
            if (v == 60 && (!this.isHtml || !this.inAttribute)) {
                this.bout.write("&lt;");
            } else if (v == 62) {
                this.bout.write("&gt;");
            } else if (v == 38) {
                this.bout.write("&amp;");
            } else if (v == 34 && this.inAttribute) {
                this.bout.write("&quot;");
            } else if (mustHexEscape(v)) {
                int i2 = v;
                if (v >= 55296) {
                    if (v < 56320) {
                        this.savedHighSurrogate = (char) v;
                        return;
                    } else if (v < 57344) {
                        i2 = ((this.savedHighSurrogate - 55296) * 1024) + (i2 - 56320) + 65536;
                        this.savedHighSurrogate = 0;
                    }
                }
                PrettyWriter prettyWriter = this.bout;
                new StringBuilder();
                prettyWriter.write(sb.append("&#x").append(Integer.toHexString(i2).toUpperCase()).append(";").toString());
            } else {
                this.bout.write(v);
                this.prev = v;
            }
        }
    }

    private void startWord() {
        closeTag();
        writeWordStart();
    }

    public void writeBoolean(boolean v) {
        startWord();
        super.print(v);
        writeWordEnd();
    }

    /* access modifiers changed from: protected */
    public void startNumber() {
        startWord();
    }

    /* access modifiers changed from: protected */
    public void endNumber() {
        writeWordEnd();
    }

    public void closeTag() {
        if (this.inStartTag && !this.inAttribute) {
            if (this.printIndent >= 0 && this.indentAttributes) {
                endLogicalBlock("");
            }
            this.bout.write(62);
            this.inStartTag = false;
            this.prev = -3;
        } else if (this.needXMLdecl) {
            this.bout.write("<?xml version=\"1.0\"?>\n");
            if (this.printIndent >= 0) {
                startLogicalBlock("", "", 2);
            }
            this.needXMLdecl = false;
            this.prev = 62;
        }
    }

    /* access modifiers changed from: package-private */
    public void setIndentMode() {
        Object xmlIndent = indentLoc.get((Object) null);
        String indent = xmlIndent == null ? null : xmlIndent.toString();
        if (indent == null) {
            this.printIndent = -1;
        } else if (indent.equals("pretty")) {
            this.printIndent = 0;
        } else if (indent.equals("always") || indent.equals("yes")) {
            this.printIndent = 1;
        } else {
            this.printIndent = -1;
        }
    }

    public void startDocument() {
        if (this.printXMLdecl) {
            this.needXMLdecl = true;
        }
        setIndentMode();
        this.inDocument = true;
        if (this.printIndent >= 0 && !this.needXMLdecl) {
            startLogicalBlock("", "", 2);
        }
    }

    public void endDocument() {
        this.inDocument = false;
        if (this.printIndent >= 0) {
            endLogicalBlock("");
        }
        freshLine();
    }

    public void beginEntity(Object base) {
    }

    public void endEntity() {
    }

    /* access modifiers changed from: protected */
    public void writeQName(Object obj) {
        Object name = obj;
        if (name instanceof Symbol) {
            Symbol sname = (Symbol) name;
            String prefix = sname.getPrefix();
            if (prefix != null && prefix.length() > 0) {
                this.bout.write(prefix);
                this.bout.write(58);
            }
            this.bout.write(sname.getLocalPart());
            return;
        }
        this.bout.write(name == null ? "{null name}" : (String) name);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0115, code lost:
        if (r2.prev == -5) goto L_0x0117;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startElement(java.lang.Object r25) {
        /*
            r24 = this;
            r2 = r24
            r3 = r25
            r17 = r2
            r17.closeTag()
            r17 = r2
            r0 = r17
            int r0 = r0.elementNesting
            r17 = r0
            if (r17 != 0) goto L_0x00dd
            r17 = r2
            r0 = r17
            boolean r0 = r0.inDocument
            r17 = r0
            if (r17 != 0) goto L_0x0022
            r17 = r2
            r17.setIndentMode()
        L_0x0022:
            r17 = r2
            r0 = r17
            int r0 = r0.prev
            r17 = r0
            r18 = -7
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0039
            r17 = r2
            r18 = 10
            r17.write(r18)
        L_0x0039:
            gnu.mapping.ThreadLocation r17 = doctypeSystem
            r18 = 0
            java.lang.Object r17 = r17.get(r18)
            r4 = r17
            r17 = r4
            if (r17 == 0) goto L_0x00dd
            r17 = r4
            java.lang.String r17 = r17.toString()
            r5 = r17
            r17 = r5
            int r17 = r17.length()
            if (r17 <= 0) goto L_0x00dd
            gnu.mapping.ThreadLocation r17 = doctypePublic
            r18 = 0
            java.lang.Object r17 = r17.get(r18)
            r6 = r17
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            java.lang.String r18 = "<!DOCTYPE "
            r17.write((java.lang.String) r18)
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            r18 = r3
            java.lang.String r18 = r18.toString()
            r17.write((java.lang.String) r18)
            r17 = r6
            if (r17 != 0) goto L_0x024d
            r17 = 0
        L_0x0086:
            r7 = r17
            r17 = r7
            if (r17 == 0) goto L_0x0255
            r17 = r7
            int r17 = r17.length()
            if (r17 <= 0) goto L_0x0255
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            java.lang.String r18 = " PUBLIC \""
            r17.write((java.lang.String) r18)
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            r18 = r7
            r17.write((java.lang.String) r18)
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            java.lang.String r18 = "\" \""
            r17.write((java.lang.String) r18)
        L_0x00bd:
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            r18 = r5
            r17.write((java.lang.String) r18)
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            java.lang.String r18 = "\">"
            r17.write((java.lang.String) r18)
            r17 = r2
            r17.println()
        L_0x00dd:
            r17 = r2
            r0 = r17
            int r0 = r0.printIndent
            r17 = r0
            if (r17 < 0) goto L_0x0135
            r17 = r2
            r0 = r17
            int r0 = r0.prev
            r17 = r0
            r18 = -3
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x0117
            r17 = r2
            r0 = r17
            int r0 = r0.prev
            r17 = r0
            r18 = -4
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x0117
            r17 = r2
            r0 = r17
            int r0 = r0.prev
            r17 = r0
            r18 = -5
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0128
        L_0x0117:
            r17 = r2
            r18 = r2
            r0 = r18
            int r0 = r0.printIndent
            r18 = r0
            if (r18 <= 0) goto L_0x0265
            r18 = 82
        L_0x0125:
            r17.writeBreak(r18)
        L_0x0128:
            r17 = r2
            java.lang.String r18 = ""
            java.lang.String r19 = ""
            r20 = 2
            r17.startLogicalBlock((java.lang.String) r18, (java.lang.String) r19, (int) r20)
        L_0x0135:
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            r18 = 60
            r17.write((int) r18)
            r17 = r2
            r18 = r3
            r17.writeQName(r18)
            r17 = r2
            r0 = r17
            int r0 = r0.printIndent
            r17 = r0
            if (r17 < 0) goto L_0x016a
            r17 = r2
            r0 = r17
            boolean r0 = r0.indentAttributes
            r17 = r0
            if (r17 == 0) goto L_0x016a
            r17 = r2
            java.lang.String r18 = ""
            java.lang.String r19 = ""
            r20 = 2
            r17.startLogicalBlock((java.lang.String) r18, (java.lang.String) r19, (int) r20)
        L_0x016a:
            r17 = r2
            r0 = r17
            java.lang.Object[] r0 = r0.elementNameStack
            r17 = r0
            r18 = r2
            r0 = r18
            int r0 = r0.elementNesting
            r18 = r0
            r19 = r3
            r17[r18] = r19
            r17 = 0
            r4 = r17
            r17 = r2
            r0 = r17
            gnu.xml.NamespaceBinding[] r0 = r0.namespaceSaveStack
            r17 = r0
            r18 = r2
            r22 = r18
            r18 = r22
            r19 = r22
            r0 = r19
            int r0 = r0.elementNesting
            r19 = r0
            r22 = r18
            r23 = r19
            r18 = r23
            r19 = r22
            r20 = r23
            r21 = 1
            int r20 = r20 + 1
            r0 = r20
            r1 = r19
            r1.elementNesting = r0
            r19 = r2
            r0 = r19
            gnu.xml.NamespaceBinding r0 = r0.namespaceBindings
            r19 = r0
            r17[r18] = r19
            r17 = r3
            r0 = r17
            boolean r0 = r0 instanceof gnu.xml.XName
            r17 = r0
            if (r17 == 0) goto L_0x0414
            r17 = r3
            gnu.xml.XName r17 = (gnu.xml.XName) r17
            r0 = r17
            gnu.xml.NamespaceBinding r0 = r0.namespaceNodes
            r17 = r0
            r4 = r17
            r17 = r4
            r18 = r2
            r0 = r18
            gnu.xml.NamespaceBinding r0 = r0.namespaceBindings
            r18 = r0
            gnu.xml.NamespaceBinding r17 = gnu.xml.NamespaceBinding.commonAncestor(r17, r18)
            r5 = r17
            r17 = r4
            if (r17 != 0) goto L_0x0269
            r17 = 0
        L_0x01e2:
            r6 = r17
            r17 = r6
            r0 = r17
            gnu.xml.NamespaceBinding[] r0 = new gnu.xml.NamespaceBinding[r0]
            r17 = r0
            r7 = r17
            r17 = 0
            r8 = r17
            r17 = r2
            r0 = r17
            boolean r0 = r0.canonicalize
            r17 = r0
            r9 = r17
            r17 = r4
            r10 = r17
        L_0x0200:
            r17 = r10
            r18 = r5
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x02af
            r17 = r8
            r11 = r17
            r17 = 0
            r12 = r17
            r17 = r10
            java.lang.String r17 = r17.getUri()
            r13 = r17
            r17 = r10
            java.lang.String r17 = r17.getPrefix()
            r14 = r17
        L_0x0222:
            int r11 = r11 + -1
            r17 = r11
            if (r17 < 0) goto L_0x027c
            r17 = r7
            r18 = r11
            r17 = r17[r18]
            r15 = r17
            r17 = r15
            java.lang.String r17 = r17.getPrefix()
            r16 = r17
            r17 = r14
            r18 = r16
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0273
        L_0x0242:
            r17 = r10
            r0 = r17
            gnu.xml.NamespaceBinding r0 = r0.next
            r17 = r0
            r10 = r17
            goto L_0x0200
        L_0x024d:
            r17 = r6
            java.lang.String r17 = r17.toString()
            goto L_0x0086
        L_0x0255:
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            java.lang.String r18 = " SYSTEM \""
            r17.write((java.lang.String) r18)
            goto L_0x00bd
        L_0x0265:
            r18 = 78
            goto L_0x0125
        L_0x0269:
            r17 = r4
            r18 = r5
            int r17 = r17.count(r18)
            goto L_0x01e2
        L_0x0273:
            r17 = r9
            if (r17 != 0) goto L_0x0278
            goto L_0x0222
        L_0x0278:
            r17 = r14
            if (r17 != 0) goto L_0x028d
        L_0x027c:
            r17 = r9
            if (r17 == 0) goto L_0x02aa
            int r11 = r11 + 1
        L_0x0282:
            r17 = r7
            r18 = r11
            r19 = r10
            r17[r18] = r19
            int r8 = r8 + 1
            goto L_0x0242
        L_0x028d:
            r17 = r16
            if (r17 == 0) goto L_0x029c
            r17 = r14
            r18 = r16
            int r17 = r17.compareTo(r18)
            if (r17 > 0) goto L_0x029c
            goto L_0x027c
        L_0x029c:
            r17 = r7
            r18 = r11
            r19 = 1
            int r18 = r18 + 1
            r19 = r15
            r17[r18] = r19
            goto L_0x0222
        L_0x02aa:
            r17 = r8
            r11 = r17
            goto L_0x0282
        L_0x02af:
            r17 = r8
            r6 = r17
            r17 = r6
            r8 = r17
        L_0x02b7:
            int r8 = r8 + -1
            r17 = r8
            if (r17 < 0) goto L_0x037a
            r17 = r7
            r18 = r8
            r17 = r17[r18]
            r10 = r17
            r17 = r10
            r0 = r17
            java.lang.String r0 = r0.prefix
            r17 = r0
            r11 = r17
            r17 = r10
            r0 = r17
            java.lang.String r0 = r0.uri
            r17 = r0
            r12 = r17
            r17 = r12
            r18 = r2
            r0 = r18
            gnu.xml.NamespaceBinding r0 = r0.namespaceBindings
            r18 = r0
            r19 = r11
            java.lang.String r18 = r18.resolve(r19)
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x02f0
            goto L_0x02b7
        L_0x02f0:
            r17 = r12
            if (r17 != 0) goto L_0x0303
            r17 = r11
            if (r17 == 0) goto L_0x0303
            r17 = r2
            r0 = r17
            boolean r0 = r0.undeclareNamespaces
            r17 = r0
            if (r17 != 0) goto L_0x0303
            goto L_0x02b7
        L_0x0303:
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            r18 = 32
            r17.write((int) r18)
            r17 = r11
            if (r17 != 0) goto L_0x035e
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            java.lang.String r18 = "xmlns"
            r17.write((java.lang.String) r18)
        L_0x0322:
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            java.lang.String r18 = "=\""
            r17.write((java.lang.String) r18)
            r17 = r2
            r18 = 1
            r0 = r18
            r1 = r17
            r1.inAttribute = r0
            r17 = r12
            if (r17 == 0) goto L_0x0345
            r17 = r2
            r18 = r12
            r17.write((java.lang.String) r18)
        L_0x0345:
            r17 = r2
            r18 = 0
            r0 = r18
            r1 = r17
            r1.inAttribute = r0
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            r18 = 34
            r17.write((int) r18)
            goto L_0x02b7
        L_0x035e:
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            java.lang.String r18 = "xmlns:"
            r17.write((java.lang.String) r18)
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            r18 = r11
            r17.write((java.lang.String) r18)
            goto L_0x0322
        L_0x037a:
            r17 = r2
            r0 = r17
            boolean r0 = r0.undeclareNamespaces
            r17 = r0
            if (r17 == 0) goto L_0x040a
            r17 = r2
            r0 = r17
            gnu.xml.NamespaceBinding r0 = r0.namespaceBindings
            r17 = r0
            r10 = r17
        L_0x038e:
            r17 = r10
            r18 = r5
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x040a
            r17 = r10
            r0 = r17
            java.lang.String r0 = r0.prefix
            r17 = r0
            r11 = r17
            r17 = r10
            r0 = r17
            java.lang.String r0 = r0.uri
            r17 = r0
            if (r17 == 0) goto L_0x03e3
            r17 = r4
            r18 = r11
            java.lang.String r17 = r17.resolve(r18)
            if (r17 != 0) goto L_0x03e3
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            r18 = 32
            r17.write((int) r18)
            r17 = r11
            if (r17 != 0) goto L_0x03ee
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            java.lang.String r18 = "xmlns"
            r17.write((java.lang.String) r18)
        L_0x03d5:
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            java.lang.String r18 = "=\"\""
            r17.write((java.lang.String) r18)
        L_0x03e3:
            r17 = r10
            r0 = r17
            gnu.xml.NamespaceBinding r0 = r0.next
            r17 = r0
            r10 = r17
            goto L_0x038e
        L_0x03ee:
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            java.lang.String r18 = "xmlns:"
            r17.write((java.lang.String) r18)
            r17 = r2
            r0 = r17
            gnu.text.PrettyWriter r0 = r0.bout
            r17 = r0
            r18 = r11
            r17.write((java.lang.String) r18)
            goto L_0x03d5
        L_0x040a:
            r17 = r2
            r18 = r4
            r0 = r18
            r1 = r17
            r1.namespaceBindings = r0
        L_0x0414:
            r17 = r2
            r0 = r17
            int r0 = r0.elementNesting
            r17 = r0
            r18 = r2
            r0 = r18
            gnu.xml.NamespaceBinding[] r0 = r0.namespaceSaveStack
            r18 = r0
            r0 = r18
            int r0 = r0.length
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 < r1) goto L_0x049d
            r17 = 2
            r18 = r2
            r0 = r18
            int r0 = r0.elementNesting
            r18 = r0
            int r17 = r17 * r18
            r0 = r17
            gnu.xml.NamespaceBinding[] r0 = new gnu.xml.NamespaceBinding[r0]
            r17 = r0
            r5 = r17
            r17 = r2
            r0 = r17
            gnu.xml.NamespaceBinding[] r0 = r0.namespaceSaveStack
            r17 = r0
            r18 = 0
            r19 = r5
            r20 = 0
            r21 = r2
            r0 = r21
            int r0 = r0.elementNesting
            r21 = r0
            java.lang.System.arraycopy(r17, r18, r19, r20, r21)
            r17 = r2
            r18 = r5
            r0 = r18
            r1 = r17
            r1.namespaceSaveStack = r0
            r17 = 2
            r18 = r2
            r0 = r18
            int r0 = r0.elementNesting
            r18 = r0
            int r17 = r17 * r18
            r0 = r17
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r17 = r0
            r6 = r17
            r17 = r2
            r0 = r17
            java.lang.Object[] r0 = r0.elementNameStack
            r17 = r0
            r18 = 0
            r19 = r6
            r20 = 0
            r21 = r2
            r0 = r21
            int r0 = r0.elementNesting
            r21 = r0
            java.lang.System.arraycopy(r17, r18, r19, r20, r21)
            r17 = r2
            r18 = r6
            r0 = r18
            r1 = r17
            r1.elementNameStack = r0
        L_0x049d:
            r17 = r2
            r18 = 1
            r0 = r18
            r1 = r17
            r1.inStartTag = r0
            r17 = r2
            r18 = r3
            java.lang.String r17 = r17.getHtmlTag(r18)
            r5 = r17
            java.lang.String r17 = "script"
            r18 = r5
            boolean r17 = r17.equals(r18)
            if (r17 != 0) goto L_0x04c7
            java.lang.String r17 = "style"
            r18 = r5
            boolean r17 = r17.equals(r18)
            if (r17 == 0) goto L_0x04d1
        L_0x04c7:
            r17 = r2
            r18 = 0
            r0 = r18
            r1 = r17
            r1.escapeText = r0
        L_0x04d1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xml.XMLPrinter.startElement(java.lang.Object):void");
    }

    public static boolean isHtmlEmptyElementTag(String str) {
        String name = str;
        int index = HtmlEmptyTags.indexOf(name);
        return index > 0 && HtmlEmptyTags.charAt(index + -1) == '/' && HtmlEmptyTags.charAt(index + name.length()) == '/';
    }

    /* access modifiers changed from: protected */
    public String getHtmlTag(Object obj) {
        Object type = obj;
        if (type instanceof Symbol) {
            Symbol sym = (Symbol) type;
            String uri = sym.getNamespaceURI();
            if (uri == "http://www.w3.org/1999/xhtml" || (this.isHtmlOrXhtml && uri == "")) {
                return sym.getLocalPart();
            }
        } else if (this.isHtmlOrXhtml) {
            return type.toString();
        }
        return null;
    }

    public void endElement() {
        StringBuilder sb;
        StringBuilder sb2;
        if (this.useEmptyElementTag == 0) {
            closeTag();
        }
        Object type = this.elementNameStack[this.elementNesting - 1];
        String typeName = getHtmlTag(type);
        if (this.inStartTag) {
            if (this.printIndent >= 0 && this.indentAttributes) {
                endLogicalBlock("");
            }
            String end = null;
            boolean isEmpty = typeName != null && isHtmlEmptyElementTag(typeName);
            if ((this.useEmptyElementTag == 0 || (typeName != null && !isEmpty)) && (type instanceof Symbol)) {
                Symbol sym = (Symbol) type;
                String prefix = sym.getPrefix();
                String uri = sym.getNamespaceURI();
                String local = sym.getLocalName();
                if (prefix != "") {
                    new StringBuilder();
                    end = sb2.append("></").append(prefix).append(":").append(local).append(">").toString();
                } else if (uri == "" || uri == null) {
                    new StringBuilder();
                    end = sb.append("></").append(local).append(">").toString();
                }
            }
            if (end == null) {
                end = (!isEmpty || !this.isHtml) ? this.useEmptyElementTag == 2 ? " />" : "/>" : ">";
            }
            this.bout.write(end);
            this.inStartTag = false;
        } else {
            if (this.printIndent >= 0) {
                setIndentation(0, false);
                if (this.prev == -4) {
                    writeBreak(this.printIndent > 0 ? 82 : 78);
                }
            }
            this.bout.write("</");
            writeQName(type);
            this.bout.write(">");
        }
        if (this.printIndent >= 0) {
            endLogicalBlock("");
        }
        this.prev = -4;
        if (typeName != null && !this.escapeText && ("script".equals(typeName) || "style".equals(typeName))) {
            this.escapeText = true;
        }
        NamespaceBinding[] namespaceBindingArr = this.namespaceSaveStack;
        int i = this.elementNesting - 1;
        this.elementNesting = i;
        this.namespaceBindings = namespaceBindingArr[i];
        this.namespaceSaveStack[this.elementNesting] = null;
        this.elementNameStack[this.elementNesting] = null;
    }

    public void startAttribute(Object obj) {
        Object attrType = obj;
        if (!this.inStartTag && this.strict) {
            error("attribute not in element", "SENR0001");
        }
        if (this.inAttribute) {
            this.bout.write(34);
        }
        this.inAttribute = true;
        this.bout.write(32);
        if (this.printIndent >= 0) {
            writeBreakFill();
        }
        this.bout.write(attrType.toString());
        this.bout.write("=\"");
        this.prev = 32;
    }

    public void endAttribute() {
        if (this.inAttribute) {
            if (this.prev != -6) {
                this.bout.write(34);
                this.inAttribute = false;
            }
            this.prev = 32;
        }
    }

    public void writeDouble(double d) {
        startWord();
        this.bout.write(formatDouble(d));
    }

    public void writeFloat(float f) {
        startWord();
        this.bout.write(formatFloat(f));
    }

    public static String formatDouble(double d) {
        double d2 = d;
        if (Double.isNaN(d2)) {
            return "NaN";
        }
        boolean neg = d2 < 0.0d;
        if (Double.isInfinite(d2)) {
            return neg ? "-INF" : "INF";
        }
        double dabs = neg ? -d2 : d2;
        String dstr = Double.toString(d2);
        if ((dabs >= 1000000.0d || dabs < 1.0E-6d) && dabs != 0.0d) {
            return RealNum.toStringScientific(dstr);
        }
        return formatDecimal(RealNum.toStringDecimal(dstr));
    }

    public static String formatFloat(float f) {
        float f2 = f;
        if (Float.isNaN(f2)) {
            return "NaN";
        }
        boolean neg = f2 < 0.0f;
        if (Float.isInfinite(f2)) {
            return neg ? "-INF" : "INF";
        }
        float fabs = neg ? -f2 : f2;
        String fstr = Float.toString(f2);
        if ((fabs >= 1000000.0f || ((double) fabs) < 1.0E-6d) && ((double) fabs) != 0.0d) {
            return RealNum.toStringScientific(fstr);
        }
        return formatDecimal(RealNum.toStringDecimal(fstr));
    }

    public static String formatDecimal(BigDecimal dec) {
        return formatDecimal(dec.toPlainString());
    }

    static String formatDecimal(String str) {
        char ch;
        String str2 = str;
        if (str2.indexOf(46) < 0) {
            return str2;
        }
        int len = str2.length();
        int pos = len;
        do {
            pos--;
            ch = str2.charAt(pos);
        } while (ch == '0');
        if (ch != '.') {
            pos++;
        }
        return pos == len ? str2 : str2.substring(0, pos);
    }

    public void print(Object obj) {
        Object v = obj;
        if (v instanceof BigDecimal) {
            v = formatDecimal((BigDecimal) v);
        } else if ((v instanceof Double) || (v instanceof DFloNum)) {
            v = formatDouble(((Number) v).doubleValue());
        } else if (v instanceof Float) {
            v = formatFloat(((Float) v).floatValue());
        }
        write(v == null ? "(null)" : v.toString());
    }

    public void writeObject(Object obj) {
        Object v = obj;
        if (v instanceof SeqPosition) {
            this.bout.clearWordEnd();
            SeqPosition pos = (SeqPosition) v;
            boolean consumeNext = pos.sequence.consumeNext(pos.ipos, this);
            if (pos.sequence instanceof NodeTree) {
                this.prev = 45;
            }
        } else if ((v instanceof Consumable) && !(v instanceof UnescapedData)) {
            ((Consumable) v).consume(this);
        } else if (v instanceof Keyword) {
            startAttribute(((Keyword) v).getName());
            this.prev = -6;
        } else {
            closeTag();
            if (v instanceof UnescapedData) {
                this.bout.clearWordEnd();
                this.bout.write(((UnescapedData) v).getData());
                this.prev = 45;
            } else if (v instanceof Char) {
                Char.print(((Char) v).intValue(), this);
            } else {
                startWord();
                this.prev = 32;
                print(v);
                writeWordEnd();
                this.prev = -2;
            }
        }
    }

    public boolean ignoring() {
        return false;
    }

    public void write(String str, int i, int i2) {
        String str2 = str;
        int start = i;
        int length = i2;
        if (length > 0) {
            closeTag();
            int limit = start + length;
            int count = 0;
            while (start < limit) {
                int i3 = start;
                start++;
                char c = str2.charAt(i3);
                if (mustHexEscape(c) || (this.inComment <= 0 ? c == '<' || c == '>' || c == '&' || (this.inAttribute && (c == '\"' || c < ' ')) : c == '-' || this.inComment == 2)) {
                    if (count > 0) {
                        this.bout.write(str2, (start - 1) - count, count);
                    }
                    write(c);
                    count = 0;
                } else {
                    count++;
                }
            }
            if (count > 0) {
                this.bout.write(str2, limit - count, count);
            }
        }
        this.prev = 45;
    }

    public void write(char[] cArr, int i, int i2) {
        char[] buf = cArr;
        int off = i;
        int len = i2;
        if (len > 0) {
            closeTag();
            int limit = off + len;
            int count = 0;
            while (off < limit) {
                int i3 = off;
                off++;
                char c = buf[i3];
                if (mustHexEscape(c) || (this.inComment <= 0 ? c == '<' || c == '>' || c == '&' || (this.inAttribute && (c == '\"' || c < ' ')) : c == '-' || this.inComment == 2)) {
                    if (count > 0) {
                        this.bout.write(buf, (off - 1) - count, count);
                    }
                    write(c);
                    count = 0;
                } else {
                    count++;
                }
            }
            if (count > 0) {
                this.bout.write(buf, limit - count, count);
            }
        }
        this.prev = 45;
    }

    public void writePosition(AbstractSequence seq, int ipos) {
        boolean consumeNext = seq.consumeNext(ipos, this);
    }

    public void writeBaseUri(Object uri) {
    }

    public void beginComment() {
        closeTag();
        if (this.printIndent >= 0 && (this.prev == -3 || this.prev == -4 || this.prev == -5)) {
            writeBreak(this.printIndent > 0 ? 82 : 78);
        }
        this.bout.write("<!--");
        this.inComment = 1;
    }

    public void endComment() {
        this.bout.write("-->");
        this.prev = -5;
        this.inComment = 0;
    }

    public void writeComment(String chars) {
        beginComment();
        write(chars);
        endComment();
    }

    public void writeComment(char[] chars, int offset, int length) {
        beginComment();
        write(chars, offset, length);
        endComment();
    }

    public void writeCDATA(char[] cArr, int i, int i2) {
        char[] chars = cArr;
        int offset = i;
        int length = i2;
        if (this.canonicalizeCDATA) {
            write(chars, offset, length);
            return;
        }
        closeTag();
        this.bout.write("<![CDATA[");
        int limit = offset + length;
        int i3 = offset;
        while (i3 < limit - 2) {
            if (chars[i3] == ']' && chars[i3 + 1] == ']' && chars[i3 + 2] == '>') {
                if (i3 > offset) {
                    this.bout.write(chars, offset, i3 - offset);
                }
                print("]]]><![CDATA[]>");
                offset = i3 + 3;
                length = limit - offset;
                i3 += 2;
            }
            i3++;
        }
        this.bout.write(chars, offset, length);
        this.bout.write("]]>");
        this.prev = 62;
    }

    public void writeProcessingInstruction(String str, char[] cArr, int i, int i2) {
        String target = str;
        char[] content = cArr;
        int offset = i;
        int length = i2;
        if ("xml".equals(target)) {
            this.needXMLdecl = false;
        }
        closeTag();
        this.bout.write("<?");
        print(target);
        print(' ');
        this.bout.write(content, offset, length);
        this.bout.write("?>");
        this.prev = -7;
    }

    public void consume(SeqPosition seqPosition) {
        SeqPosition position = seqPosition;
        boolean consumeNext = position.sequence.consumeNext(position.ipos, this);
    }

    public void error(String msg, String code) {
        Throwable th;
        StringBuilder sb;
        Throwable th2 = th;
        new StringBuilder();
        new RuntimeException(sb.append("serialization error: ").append(msg).append(" [").append(code).append(']').toString());
        throw th2;
    }
}
