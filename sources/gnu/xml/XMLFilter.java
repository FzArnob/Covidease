package gnu.xml;

import gnu.expr.Keyword;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.AbstractSequence;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.lists.UnescapedData;
import gnu.lists.XConsumer;
import gnu.mapping.Symbol;
import gnu.text.Char;
import gnu.text.LineBufferedReader;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import java.util.List;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class XMLFilter implements DocumentHandler, ContentHandler, SourceLocator, XConsumer, PositionConsumer {
    public static final int COPY_NAMESPACES_INHERIT = 2;
    public static final int COPY_NAMESPACES_PRESERVE = 1;
    private static final int SAW_KEYWORD = 1;
    private static final int SAW_WORD = 2;
    int attrCount = -1;
    String attrLocalName;
    String attrPrefix;
    Consumer base;
    public transient int copyNamespacesMode = 1;
    String currentNamespacePrefix;
    protected int ignoringLevel;

    /* renamed from: in */
    LineBufferedReader f249in;
    boolean inStartTag;
    SourceLocator locator;
    MappingInfo[] mappingTable = new MappingInfo[128];
    int mappingTableMask = (this.mappingTable.length - 1);
    private SourceMessages messages;
    boolean mismatchReported;
    NamespaceBinding namespaceBindings;
    public boolean namespacePrefixes = false;
    protected int nesting;
    public Consumer out;
    int previous = 0;
    int[] startIndexes = null;
    protected int stringizingElementNesting = -1;
    protected int stringizingLevel;
    TreeList tlist;
    Object[] workStack;

    public void setSourceLocator(LineBufferedReader in) {
        this.f249in = in;
        this.locator = this;
    }

    public void setSourceLocator(SourceLocator locator2) {
        SourceLocator sourceLocator = locator2;
        this.locator = sourceLocator;
    }

    public void setMessages(SourceMessages messages2) {
        SourceMessages sourceMessages = messages2;
        this.messages = sourceMessages;
    }

    public NamespaceBinding findNamespaceBinding(String str, String str2, NamespaceBinding namespaceBinding) {
        int hashCode;
        MappingInfo mappingInfo;
        NamespaceBinding namespaces;
        String prefix = str;
        String uri = str2;
        NamespaceBinding oldBindings = namespaceBinding;
        if (uri == null) {
            hashCode = 0;
        } else {
            hashCode = uri.hashCode();
        }
        int hash = hashCode;
        if (prefix != null) {
            hash ^= prefix.hashCode();
        }
        int bucket = hash & this.mappingTableMask;
        MappingInfo mappingInfo2 = this.mappingTable[bucket];
        while (true) {
            MappingInfo info = mappingInfo2;
            if (info == null) {
                new MappingInfo();
                MappingInfo info2 = mappingInfo;
                info2.nextInBucket = this.mappingTable[bucket];
                this.mappingTable[bucket] = info2;
                info2.tagHash = hash;
                info2.prefix = prefix;
                info2.local = uri;
                info2.uri = uri;
                if (uri == "") {
                    uri = null;
                }
                new NamespaceBinding(prefix, uri, oldBindings);
                info2.namespaces = namespaces;
                return info2.namespaces;
            }
            if (info.tagHash == hash && info.prefix == prefix) {
                NamespaceBinding namespaceBinding2 = info.namespaces;
                NamespaceBinding namespaces2 = namespaceBinding2;
                if (namespaceBinding2 != null && namespaces2.getNext() == this.namespaceBindings && namespaces2.getPrefix() == prefix && info.uri == uri) {
                    return info.namespaces;
                }
            }
            mappingInfo2 = info.nextInBucket;
        }
    }

    public MappingInfo lookupNamespaceBinding(String str, char[] cArr, int i, int i2, int i3, NamespaceBinding namespaceBinding) {
        int hashCode;
        MappingInfo mappingInfo;
        String str2;
        NamespaceBinding namespaces;
        String prefix = str;
        char[] uriChars = cArr;
        int uriStart = i;
        int uriLength = i2;
        int uriHash = i3;
        NamespaceBinding oldBindings = namespaceBinding;
        if (prefix == null) {
            hashCode = uriHash;
        } else {
            hashCode = prefix.hashCode() ^ uriHash;
        }
        int hash = hashCode;
        int bucket = hash & this.mappingTableMask;
        MappingInfo mappingInfo2 = this.mappingTable[bucket];
        while (true) {
            MappingInfo info = mappingInfo2;
            if (info == null) {
                new MappingInfo();
                MappingInfo info2 = mappingInfo;
                info2.nextInBucket = this.mappingTable[bucket];
                this.mappingTable[bucket] = info2;
                new String(uriChars, uriStart, uriLength);
                String uri = str2.intern();
                info2.tagHash = hash;
                info2.prefix = prefix;
                info2.local = uri;
                info2.uri = uri;
                if (uri == "") {
                    uri = null;
                }
                new NamespaceBinding(prefix, uri, oldBindings);
                info2.namespaces = namespaces;
                return info2;
            }
            if (info.tagHash == hash && info.prefix == prefix) {
                NamespaceBinding namespaceBinding2 = info.namespaces;
                NamespaceBinding namespaces2 = namespaceBinding2;
                if (namespaceBinding2 != null && namespaces2.getNext() == this.namespaceBindings && namespaces2.getPrefix() == prefix && MappingInfo.equals(info.uri, uriChars, uriStart, uriLength)) {
                    return info;
                }
            }
            mappingInfo2 = info.nextInBucket;
        }
    }

    public void endAttribute() {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        if (this.attrLocalName != null) {
            if (this.previous == 1) {
                this.previous = 0;
                return;
            }
            if (this.stringizingElementNesting >= 0) {
                this.ignoringLevel--;
            }
            int i = this.stringizingLevel - 1;
            int i2 = i;
            this.stringizingLevel = i;
            if (i2 == 0) {
                if (this.attrLocalName == "id" && this.attrPrefix == "xml") {
                    int valStart = this.startIndexes[this.attrCount - 1] + 5;
                    int valEnd = this.tlist.gapStart;
                    char[] data = this.tlist.data;
                    int i3 = valStart;
                    while (true) {
                        if (i3 >= valEnd) {
                            break;
                        }
                        int i4 = i3;
                        i3++;
                        char datum = data[i4];
                        if ((datum & LispReader.TOKEN_ESCAPE_CHAR) > 40959 || datum == 9 || datum == 13 || datum == 10 || (datum == ' ' && (i3 == valEnd || data[i3] == ' '))) {
                            new StringBuffer();
                            StringBuffer sbuf = stringBuffer2;
                            this.tlist.stringValue(valStart, valEnd, sbuf);
                            this.tlist.gapStart = valStart;
                            this.tlist.write(TextUtils.replaceWhitespace(sbuf.toString(), true));
                        }
                    }
                    new StringBuffer();
                    StringBuffer sbuf2 = stringBuffer2;
                    this.tlist.stringValue(valStart, valEnd, sbuf2);
                    this.tlist.gapStart = valStart;
                    this.tlist.write(TextUtils.replaceWhitespace(sbuf2.toString(), true));
                }
                this.attrLocalName = null;
                this.attrPrefix = null;
                if (this.currentNamespacePrefix == null || this.namespacePrefixes) {
                    this.tlist.endAttribute();
                }
                if (this.currentNamespacePrefix != null) {
                    int attrStart = this.startIndexes[this.attrCount - 1];
                    int uriStart = attrStart;
                    int uriEnd = this.tlist.gapStart;
                    int uriLength = uriEnd - uriStart;
                    char[] data2 = this.tlist.data;
                    int uriHash = 0;
                    int i5 = uriStart;
                    while (true) {
                        if (i5 >= uriEnd) {
                            break;
                        }
                        char datum2 = data2[i5];
                        if ((datum2 & LispReader.TOKEN_ESCAPE_CHAR) > 40959) {
                            new StringBuffer();
                            StringBuffer sbuf3 = stringBuffer;
                            this.tlist.stringValue(uriStart, uriEnd, sbuf3);
                            uriHash = sbuf3.hashCode();
                            uriStart = 0;
                            int uriEnd2 = sbuf3.length();
                            uriLength = uriEnd2;
                            data2 = new char[sbuf3.length()];
                            sbuf3.getChars(0, uriEnd2, data2, 0);
                            break;
                        }
                        uriHash = (31 * uriHash) + datum2;
                        i5++;
                    }
                    this.tlist.gapStart = attrStart;
                    this.namespaceBindings = lookupNamespaceBinding(this.currentNamespacePrefix == "" ? null : this.currentNamespacePrefix, data2, uriStart, uriLength, uriHash, this.namespaceBindings).namespaces;
                    this.currentNamespacePrefix = null;
                }
            }
        }
    }

    private String resolve(String str, boolean isAttribute) {
        StringBuilder sb;
        String prefix = str;
        if (isAttribute && prefix == null) {
            return "";
        }
        String uri = this.namespaceBindings.resolve(prefix);
        if (uri != null) {
            return uri;
        }
        if (prefix != null) {
            new StringBuilder();
            error('e', sb.append("unknown namespace prefix '").append(prefix).append('\'').toString());
        }
        return "";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: gnu.lists.TreeList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: gnu.lists.TreeList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v50, resolved type: gnu.mapping.Symbol} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v52, resolved type: gnu.xml.XName} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v53, resolved type: gnu.mapping.Symbol} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v103, resolved type: gnu.xml.XName} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v2, resolved type: gnu.mapping.Symbol} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v30, resolved type: gnu.xml.MappingInfo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v35, resolved type: gnu.lists.TreeList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: gnu.xml.MappingInfo} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x05ea, code lost:
        if (r2.tlist.objects[r13] != r12) goto L_0x05ec;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void closeStartTag() {
        /*
            r23 = this;
            r2 = r23
            r17 = r2
            r0 = r17
            int r0 = r0.attrCount
            r17 = r0
            if (r17 < 0) goto L_0x0016
            r17 = r2
            r0 = r17
            int r0 = r0.stringizingLevel
            r17 = r0
            if (r17 <= 0) goto L_0x0017
        L_0x0016:
            return
        L_0x0017:
            r17 = r2
            r18 = 0
            r0 = r18
            r1 = r17
            r1.inStartTag = r0
            r17 = r2
            r18 = 0
            r0 = r18
            r1 = r17
            r1.previous = r0
            r17 = r2
            r0 = r17
            java.lang.String r0 = r0.attrLocalName
            r17 = r0
            if (r17 == 0) goto L_0x003a
            r17 = r2
            r17.endAttribute()
        L_0x003a:
            r17 = r2
            r0 = r17
            int r0 = r0.nesting
            r17 = r0
            if (r17 != 0) goto L_0x00cf
            gnu.xml.NamespaceBinding r17 = gnu.xml.NamespaceBinding.predefinedXML
        L_0x0046:
            r3 = r17
            r17 = r2
            r0 = r17
            gnu.xml.NamespaceBinding r0 = r0.namespaceBindings
            r17 = r0
            r4 = r17
            r17 = 0
            r5 = r17
        L_0x0056:
            r17 = r5
            r18 = r2
            r0 = r18
            int r0 = r0.attrCount
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 > r1) goto L_0x0210
            r17 = r2
            r0 = r17
            java.lang.Object[] r0 = r0.workStack
            r17 = r0
            r18 = r2
            r0 = r18
            int r0 = r0.nesting
            r18 = r0
            r19 = r5
            int r18 = r18 + r19
            r19 = 1
            int r18 = r18 + -1
            r17 = r17[r18]
            r6 = r17
            r17 = r6
            r0 = r17
            boolean r0 = r0 instanceof gnu.mapping.Symbol
            r17 = r0
            if (r17 == 0) goto L_0x00cc
            r17 = r6
            gnu.mapping.Symbol r17 = (gnu.mapping.Symbol) r17
            r7 = r17
            r17 = r7
            java.lang.String r17 = r17.getPrefix()
            r8 = r17
            r17 = r8
            java.lang.String r18 = ""
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x00a9
            r17 = 0
            r8 = r17
        L_0x00a9:
            r17 = r7
            java.lang.String r17 = r17.getNamespaceURI()
            r9 = r17
            r17 = r9
            java.lang.String r18 = ""
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x00c0
            r17 = 0
            r9 = r17
        L_0x00c0:
            r17 = r5
            if (r17 <= 0) goto L_0x00e9
            r17 = r8
            if (r17 != 0) goto L_0x00e9
            r17 = r9
            if (r17 != 0) goto L_0x00e9
        L_0x00cc:
            int r5 = r5 + 1
            goto L_0x0056
        L_0x00cf:
            r17 = r2
            r0 = r17
            java.lang.Object[] r0 = r0.workStack
            r17 = r0
            r18 = r2
            r0 = r18
            int r0 = r0.nesting
            r18 = r0
            r19 = 2
            int r18 = r18 + -2
            r17 = r17[r18]
            gnu.xml.NamespaceBinding r17 = (gnu.xml.NamespaceBinding) r17
            goto L_0x0046
        L_0x00e9:
            r17 = 0
            r10 = r17
            r17 = r4
            r11 = r17
        L_0x00f1:
            r17 = r11
            r18 = r3
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x00ff
            r17 = 1
            r10 = r17
        L_0x00ff:
            r17 = r11
            if (r17 != 0) goto L_0x011a
            r17 = r8
            if (r17 != 0) goto L_0x010b
            r17 = r9
            if (r17 == 0) goto L_0x00cc
        L_0x010b:
            r17 = r2
            r18 = r8
            r19 = r9
            r20 = r4
            gnu.xml.NamespaceBinding r17 = r17.findNamespaceBinding(r18, r19, r20)
            r4 = r17
            goto L_0x00cc
        L_0x011a:
            r17 = r11
            r0 = r17
            java.lang.String r0 = r0.prefix
            r17 = r0
            r18 = r8
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0204
            r17 = r11
            r0 = r17
            java.lang.String r0 = r0.uri
            r17 = r0
            r18 = r9
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x00cc
            r17 = r10
            if (r17 == 0) goto L_0x014d
            r17 = r2
            r18 = r8
            r19 = r9
            r20 = r4
            gnu.xml.NamespaceBinding r17 = r17.findNamespaceBinding(r18, r19, r20)
            r4 = r17
            goto L_0x00cc
        L_0x014d:
            r17 = r4
            r13 = r17
        L_0x0151:
            r17 = r13
            if (r17 != 0) goto L_0x01cd
            r17 = 1
            r14 = r17
        L_0x0159:
            java.lang.StringBuilder r17 = new java.lang.StringBuilder
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            java.lang.String r18 = "_ns_"
            java.lang.StringBuilder r17 = r17.append(r18)
            r18 = r14
            java.lang.StringBuilder r17 = r17.append(r18)
            java.lang.String r17 = r17.toString()
            java.lang.String r17 = r17.intern()
            r12 = r17
            r17 = r4
            r18 = r12
            java.lang.String r17 = r17.resolve(r18)
            if (r17 != 0) goto L_0x01ca
        L_0x0185:
            r17 = r2
            r18 = r12
            r19 = r9
            r20 = r4
            gnu.xml.NamespaceBinding r17 = r17.findNamespaceBinding(r18, r19, r20)
            r4 = r17
            r17 = r7
            java.lang.String r17 = r17.getLocalName()
            r13 = r17
            r17 = r9
            if (r17 != 0) goto L_0x01a4
            java.lang.String r17 = ""
            r9 = r17
        L_0x01a4:
            r17 = r2
            r0 = r17
            java.lang.Object[] r0 = r0.workStack
            r17 = r0
            r18 = r2
            r0 = r18
            int r0 = r0.nesting
            r18 = r0
            r19 = r5
            int r18 = r18 + r19
            r19 = 1
            int r18 = r18 + -1
            r19 = r9
            r20 = r13
            r21 = r12
            gnu.mapping.Symbol r19 = gnu.mapping.Symbol.make(r19, r20, r21)
            r17[r18] = r19
            goto L_0x00cc
        L_0x01ca:
            int r14 = r14 + 1
            goto L_0x0159
        L_0x01cd:
            r17 = r13
            r0 = r17
            java.lang.String r0 = r0.uri
            r17 = r0
            r18 = r9
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x01f8
            r17 = r13
            r0 = r17
            java.lang.String r0 = r0.prefix
            r17 = r0
            r12 = r17
            r17 = r4
            r18 = r12
            java.lang.String r17 = r17.resolve(r18)
            r18 = r9
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x01f8
            goto L_0x0185
        L_0x01f8:
            r17 = r13
            r0 = r17
            gnu.xml.NamespaceBinding r0 = r0.next
            r17 = r0
            r13 = r17
            goto L_0x0151
        L_0x0204:
            r17 = r11
            r0 = r17
            gnu.xml.NamespaceBinding r0 = r0.next
            r17 = r0
            r11 = r17
            goto L_0x00f1
        L_0x0210:
            r17 = 0
            r5 = r17
        L_0x0214:
            r17 = r5
            r18 = r2
            r0 = r18
            int r0 = r0.attrCount
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 > r1) goto L_0x0715
            r17 = r2
            r0 = r17
            java.lang.Object[] r0 = r0.workStack
            r17 = r0
            r18 = r2
            r0 = r18
            int r0 = r0.nesting
            r18 = r0
            r19 = r5
            int r18 = r18 + r19
            r19 = 1
            int r18 = r18 + -1
            r17 = r17[r18]
            r6 = r17
            r17 = 0
            r8 = r17
            r17 = r6
            r0 = r17
            boolean r0 = r0 instanceof gnu.xml.MappingInfo
            r17 = r0
            if (r17 != 0) goto L_0x0264
            r17 = r2
            r0 = r17
            gnu.lists.Consumer r0 = r0.out
            r17 = r0
            r18 = r2
            r0 = r18
            gnu.lists.TreeList r0 = r0.tlist
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x056c
        L_0x0264:
            r17 = r6
            r0 = r17
            boolean r0 = r0 instanceof gnu.xml.MappingInfo
            r17 = r0
            if (r17 == 0) goto L_0x0445
            r17 = r6
            gnu.xml.MappingInfo r17 = (gnu.xml.MappingInfo) r17
            r7 = r17
            r17 = r7
            r0 = r17
            java.lang.String r0 = r0.prefix
            r17 = r0
            r9 = r17
            r17 = r7
            r0 = r17
            java.lang.String r0 = r0.local
            r17 = r0
            r11 = r17
            r17 = r5
            if (r17 <= 0) goto L_0x0430
            r17 = r9
            if (r17 != 0) goto L_0x029b
            r17 = r11
            java.lang.String r18 = "xmlns"
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x02a6
        L_0x029b:
            r17 = r9
            java.lang.String r18 = "xmlns"
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0430
        L_0x02a6:
            r17 = 1
            r8 = r17
            java.lang.String r17 = "(namespace-node)"
            r10 = r17
        L_0x02af:
            r17 = r7
            r0 = r17
            int r0 = r0.tagHash
            r17 = r0
            r12 = r17
            r17 = r12
            r18 = r2
            r0 = r18
            int r0 = r0.mappingTableMask
            r18 = r0
            r17 = r17 & r18
            r13 = r17
            r17 = r2
            r0 = r17
            gnu.xml.MappingInfo[] r0 = r0.mappingTable
            r17 = r0
            r18 = r13
            r17 = r17[r18]
            r7 = r17
            r17 = 0
            r14 = r17
        L_0x02d9:
            r17 = r7
            if (r17 != 0) goto L_0x0473
            r17 = r14
            r7 = r17
            gnu.xml.MappingInfo r17 = new gnu.xml.MappingInfo
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r7 = r17
            r17 = r7
            r18 = r12
            r0 = r18
            r1 = r17
            r1.tagHash = r0
            r17 = r7
            r18 = r9
            r0 = r18
            r1 = r17
            r1.prefix = r0
            r17 = r7
            r18 = r11
            r0 = r18
            r1 = r17
            r1.local = r0
            r17 = r7
            r18 = r2
            r0 = r18
            gnu.xml.MappingInfo[] r0 = r0.mappingTable
            r18 = r0
            r19 = r13
            r18 = r18[r19]
            r0 = r18
            r1 = r17
            r1.nextInBucket = r0
            r17 = r2
            r0 = r17
            gnu.xml.MappingInfo[] r0 = r0.mappingTable
            r17 = r0
            r18 = r13
            r19 = r7
            r17[r18] = r19
            r17 = r7
            r18 = r10
            r0 = r18
            r1 = r17
            r1.uri = r0
            r17 = r7
            r18 = r10
            r19 = r11
            r20 = r9
            gnu.mapping.Symbol r18 = gnu.mapping.Symbol.make(r18, r19, r20)
            r0 = r18
            r1 = r17
            r1.qname = r0
            r17 = r5
            if (r17 != 0) goto L_0x037d
            gnu.xml.XName r17 = new gnu.xml.XName
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r7
            r0 = r19
            gnu.mapping.Symbol r0 = r0.qname
            r19 = r0
            r20 = r4
            r18.<init>(r19, r20)
            r16 = r17
            r17 = r16
            r15 = r17
            r17 = r7
            r18 = r16
            r0 = r18
            r1 = r17
            r1.type = r0
            r17 = r7
            r18 = r4
            r0 = r18
            r1 = r17
            r1.namespaces = r0
        L_0x037d:
            r17 = r2
            r0 = r17
            java.lang.Object[] r0 = r0.workStack
            r17 = r0
            r18 = r2
            r0 = r18
            int r0 = r0.nesting
            r18 = r0
            r19 = r5
            int r18 = r18 + r19
            r19 = 1
            int r18 = r18 + -1
            r19 = r7
            r17[r18] = r19
        L_0x0399:
            r17 = 1
            r12 = r17
        L_0x039d:
            r17 = r12
            r18 = r5
            r0 = r17
            r1 = r18
            if (r0 >= r1) goto L_0x05a0
            r17 = r2
            r0 = r17
            java.lang.Object[] r0 = r0.workStack
            r17 = r0
            r18 = r2
            r0 = r18
            int r0 = r0.nesting
            r18 = r0
            r19 = r12
            int r18 = r18 + r19
            r19 = 1
            int r18 = r18 + -1
            r17 = r17[r18]
            r13 = r17
            r17 = r13
            r0 = r17
            boolean r0 = r0 instanceof gnu.mapping.Symbol
            r17 = r0
            if (r17 == 0) goto L_0x0588
            r17 = r13
            gnu.mapping.Symbol r17 = (gnu.mapping.Symbol) r17
            r14 = r17
        L_0x03d3:
            r17 = r11
            r18 = r14
            java.lang.String r18 = r18.getLocalPart()
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x042c
            r17 = r10
            r18 = r14
            java.lang.String r18 = r18.getNamespaceURI()
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x042c
            r17 = r2
            r0 = r17
            java.lang.Object[] r0 = r0.workStack
            r17 = r0
            r18 = r2
            r0 = r18
            int r0 = r0.nesting
            r18 = r0
            r19 = 1
            int r18 = r18 + -1
            r17 = r17[r18]
            r15 = r17
            r17 = r15
            r0 = r17
            boolean r0 = r0 instanceof gnu.xml.MappingInfo
            r17 = r0
            if (r17 == 0) goto L_0x041d
            r17 = r15
            gnu.xml.MappingInfo r17 = (gnu.xml.MappingInfo) r17
            r0 = r17
            gnu.mapping.Symbol r0 = r0.qname
            r17 = r0
            r15 = r17
        L_0x041d:
            r17 = r2
            r18 = 101(0x65, float:1.42E-43)
            r19 = r14
            r20 = r15
            java.lang.String r19 = duplicateAttributeMessage(r19, r20)
            r17.error(r18, r19)
        L_0x042c:
            int r12 = r12 + 1
            goto L_0x039d
        L_0x0430:
            r17 = r2
            r18 = r9
            r19 = r5
            if (r19 <= 0) goto L_0x0442
            r19 = 1
        L_0x043a:
            java.lang.String r17 = r17.resolve(r18, r19)
            r10 = r17
            goto L_0x02af
        L_0x0442:
            r19 = 0
            goto L_0x043a
        L_0x0445:
            r17 = r6
            gnu.mapping.Symbol r17 = (gnu.mapping.Symbol) r17
            r12 = r17
            r17 = r2
            r18 = r12
            gnu.xml.MappingInfo r17 = r17.lookupTag(r18)
            r7 = r17
            r17 = r7
            r0 = r17
            java.lang.String r0 = r0.prefix
            r17 = r0
            r9 = r17
            r17 = r7
            r0 = r17
            java.lang.String r0 = r0.local
            r17 = r0
            r11 = r17
            r17 = r12
            java.lang.String r17 = r17.getNamespaceURI()
            r10 = r17
            goto L_0x02af
        L_0x0473:
            r17 = r7
            r0 = r17
            int r0 = r0.tagHash
            r17 = r0
            r18 = r12
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0536
            r17 = r7
            r0 = r17
            java.lang.String r0 = r0.local
            r17 = r0
            r18 = r11
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0536
            r17 = r7
            r0 = r17
            java.lang.String r0 = r0.prefix
            r17 = r0
            r18 = r9
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0536
            r17 = r7
            r0 = r17
            java.lang.String r0 = r0.uri
            r17 = r0
            if (r17 != 0) goto L_0x0526
            r17 = r7
            r18 = r10
            r0 = r18
            r1 = r17
            r1.uri = r0
            r17 = r7
            r18 = r10
            r19 = r11
            r20 = r9
            gnu.mapping.Symbol r18 = gnu.mapping.Symbol.make(r18, r19, r20)
            r0 = r18
            r1 = r17
            r1.qname = r0
        L_0x04c9:
            r17 = r5
            if (r17 != 0) goto L_0x0560
            r17 = r7
            r0 = r17
            gnu.xml.NamespaceBinding r0 = r0.namespaces
            r17 = r0
            r18 = r4
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x04e7
            r17 = r7
            r0 = r17
            gnu.xml.NamespaceBinding r0 = r0.namespaces
            r17 = r0
            if (r17 != 0) goto L_0x0536
        L_0x04e7:
            r17 = r7
            r0 = r17
            gnu.xml.XName r0 = r0.type
            r17 = r0
            r15 = r17
            r17 = r7
            r18 = r4
            r0 = r18
            r1 = r17
            r1.namespaces = r0
            r17 = r15
            if (r17 != 0) goto L_0x037d
            gnu.xml.XName r17 = new gnu.xml.XName
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r7
            r0 = r19
            gnu.mapping.Symbol r0 = r0.qname
            r19 = r0
            r20 = r4
            r18.<init>(r19, r20)
            r16 = r17
            r17 = r16
            r15 = r17
            r17 = r7
            r18 = r16
            r0 = r18
            r1 = r17
            r1.type = r0
            goto L_0x037d
        L_0x0526:
            r17 = r7
            r0 = r17
            java.lang.String r0 = r0.uri
            r17 = r0
            r18 = r10
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x0542
        L_0x0536:
            r17 = r7
            r0 = r17
            gnu.xml.MappingInfo r0 = r0.nextInBucket
            r17 = r0
            r7 = r17
            goto L_0x02d9
        L_0x0542:
            r17 = r7
            r0 = r17
            gnu.mapping.Symbol r0 = r0.qname
            r17 = r0
            if (r17 != 0) goto L_0x04c9
            r17 = r7
            r18 = r10
            r19 = r11
            r20 = r9
            gnu.mapping.Symbol r18 = gnu.mapping.Symbol.make(r18, r19, r20)
            r0 = r18
            r1 = r17
            r1.qname = r0
            goto L_0x04c9
        L_0x0560:
            r17 = r7
            r0 = r17
            gnu.mapping.Symbol r0 = r0.qname
            r17 = r0
            r15 = r17
            goto L_0x037d
        L_0x056c:
            r17 = r6
            gnu.mapping.Symbol r17 = (gnu.mapping.Symbol) r17
            r12 = r17
            r17 = r12
            java.lang.String r17 = r17.getNamespaceURI()
            r10 = r17
            r17 = r12
            java.lang.String r17 = r17.getLocalName()
            r11 = r17
            r17 = 0
            r7 = r17
            goto L_0x0399
        L_0x0588:
            r17 = r13
            r0 = r17
            boolean r0 = r0 instanceof gnu.xml.MappingInfo
            r17 = r0
            if (r17 == 0) goto L_0x042c
            r17 = r13
            gnu.xml.MappingInfo r17 = (gnu.xml.MappingInfo) r17
            r0 = r17
            gnu.mapping.Symbol r0 = r0.qname
            r17 = r0
            r14 = r17
            goto L_0x03d3
        L_0x05a0:
            r17 = r2
            r0 = r17
            gnu.lists.Consumer r0 = r0.out
            r17 = r0
            r18 = r2
            r0 = r18
            gnu.lists.TreeList r0 = r0.tlist
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x065e
            r17 = r5
            if (r17 != 0) goto L_0x0629
            r17 = r7
            r0 = r17
            gnu.xml.XName r0 = r0.type
            r17 = r0
        L_0x05c2:
            r12 = r17
            r17 = r7
            r0 = r17
            int r0 = r0.index
            r17 = r0
            r13 = r17
            r17 = r13
            if (r17 <= 0) goto L_0x05ec
            r17 = r2
            r0 = r17
            gnu.lists.TreeList r0 = r0.tlist
            r17 = r0
            r0 = r17
            java.lang.Object[] r0 = r0.objects
            r17 = r0
            r18 = r13
            r17 = r17[r18]
            r18 = r12
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x0606
        L_0x05ec:
            r17 = r2
            r0 = r17
            gnu.lists.TreeList r0 = r0.tlist
            r17 = r0
            r18 = r12
            int r17 = r17.find(r18)
            r13 = r17
            r17 = r7
            r18 = r13
            r0 = r18
            r1 = r17
            r1.index = r0
        L_0x0606:
            r17 = r5
            if (r17 != 0) goto L_0x0632
            r17 = r2
            r0 = r17
            gnu.lists.TreeList r0 = r0.tlist
            r17 = r0
            r18 = r2
            r0 = r18
            gnu.lists.TreeList r0 = r0.tlist
            r18 = r0
            r0 = r18
            int r0 = r0.gapEnd
            r18 = r0
            r19 = r13
            r17.setElementName(r18, r19)
        L_0x0625:
            int r5 = r5 + 1
            goto L_0x0214
        L_0x0629:
            r17 = r7
            r0 = r17
            gnu.mapping.Symbol r0 = r0.qname
            r17 = r0
            goto L_0x05c2
        L_0x0632:
            r17 = r8
            if (r17 == 0) goto L_0x0640
            r17 = r2
            r0 = r17
            boolean r0 = r0.namespacePrefixes
            r17 = r0
            if (r17 == 0) goto L_0x0625
        L_0x0640:
            r17 = r2
            r0 = r17
            gnu.lists.TreeList r0 = r0.tlist
            r17 = r0
            r18 = r2
            r0 = r18
            int[] r0 = r0.startIndexes
            r18 = r0
            r19 = r5
            r20 = 1
            int r19 = r19 + -1
            r18 = r18[r19]
            r19 = r13
            r17.setAttributeName(r18, r19)
            goto L_0x0625
        L_0x065e:
            r17 = r7
            if (r17 != 0) goto L_0x0678
            r17 = r6
        L_0x0664:
            r12 = r17
            r17 = r5
            if (r17 != 0) goto L_0x068e
            r17 = r2
            r0 = r17
            gnu.lists.Consumer r0 = r0.out
            r17 = r0
            r18 = r12
            r17.startElement(r18)
            goto L_0x0625
        L_0x0678:
            r17 = r5
            if (r17 != 0) goto L_0x0685
            r17 = r7
            r0 = r17
            gnu.xml.XName r0 = r0.type
            r17 = r0
            goto L_0x0664
        L_0x0685:
            r17 = r7
            r0 = r17
            gnu.mapping.Symbol r0 = r0.qname
            r17 = r0
            goto L_0x0664
        L_0x068e:
            r17 = r8
            if (r17 == 0) goto L_0x069c
            r17 = r2
            r0 = r17
            boolean r0 = r0.namespacePrefixes
            r17 = r0
            if (r17 == 0) goto L_0x0625
        L_0x069c:
            r17 = r2
            r0 = r17
            gnu.lists.Consumer r0 = r0.out
            r17 = r0
            r18 = r12
            r17.startAttribute(r18)
            r17 = r2
            r0 = r17
            int[] r0 = r0.startIndexes
            r17 = r0
            r18 = r5
            r19 = 1
            int r18 = r18 + -1
            r17 = r17[r18]
            r13 = r17
            r17 = r5
            r18 = r2
            r0 = r18
            int r0 = r0.attrCount
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 >= r1) goto L_0x0706
            r17 = r2
            r0 = r17
            int[] r0 = r0.startIndexes
            r17 = r0
            r18 = r5
            r17 = r17[r18]
        L_0x06d7:
            r14 = r17
            r17 = r2
            r0 = r17
            gnu.lists.TreeList r0 = r0.tlist
            r17 = r0
            r18 = r13
            r19 = 5
            int r18 = r18 + 5
            r19 = r14
            r20 = 1
            int r19 = r19 + -1
            r20 = r2
            r0 = r20
            gnu.lists.Consumer r0 = r0.out
            r20 = r0
            int r17 = r17.consumeIRange(r18, r19, r20)
            r17 = r2
            r0 = r17
            gnu.lists.Consumer r0 = r0.out
            r17 = r0
            r17.endAttribute()
            goto L_0x0625
        L_0x0706:
            r17 = r2
            r0 = r17
            gnu.lists.TreeList r0 = r0.tlist
            r17 = r0
            r0 = r17
            int r0 = r0.gapStart
            r17 = r0
            goto L_0x06d7
        L_0x0715:
            r17 = r2
            r0 = r17
            gnu.lists.Consumer r0 = r0.out
            r17 = r0
            r0 = r17
            boolean r0 = r0 instanceof gnu.kawa.sax.ContentConsumer
            r17 = r0
            if (r17 == 0) goto L_0x0732
            r17 = r2
            r0 = r17
            gnu.lists.Consumer r0 = r0.out
            r17 = r0
            gnu.kawa.sax.ContentConsumer r17 = (gnu.kawa.sax.ContentConsumer) r17
            r17.endStartTag()
        L_0x0732:
            r17 = 1
            r5 = r17
        L_0x0736:
            r17 = r5
            r18 = r2
            r0 = r18
            int r0 = r0.attrCount
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 > r1) goto L_0x0765
            r17 = r2
            r0 = r17
            java.lang.Object[] r0 = r0.workStack
            r17 = r0
            r18 = r2
            r0 = r18
            int r0 = r0.nesting
            r18 = r0
            r19 = r5
            int r18 = r18 + r19
            r19 = 1
            int r18 = r18 + -1
            r19 = 0
            r17[r18] = r19
            int r5 = r5 + 1
            goto L_0x0736
        L_0x0765:
            r17 = r2
            r0 = r17
            gnu.lists.Consumer r0 = r0.out
            r17 = r0
            r18 = r2
            r0 = r18
            gnu.lists.TreeList r0 = r0.tlist
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x0796
            r17 = r2
            r18 = r2
            r0 = r18
            gnu.lists.Consumer r0 = r0.out
            r18 = r0
            r0 = r18
            r1 = r17
            r1.base = r0
            r17 = r2
            r0 = r17
            gnu.lists.TreeList r0 = r0.tlist
            r17 = r0
            r17.clear()
        L_0x0796:
            r17 = r2
            r18 = -1
            r0 = r18
            r1 = r17
            r1.attrCount = r0
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xml.XMLFilter.closeStartTag():void");
    }

    /* access modifiers changed from: protected */
    public boolean checkWriteAtomic() {
        this.previous = 0;
        if (this.ignoringLevel > 0) {
            return false;
        }
        closeStartTag();
        return true;
    }

    public void write(int i) {
        int v = i;
        if (checkWriteAtomic()) {
            this.base.write(v);
        }
    }

    public void writeBoolean(boolean z) {
        boolean v = z;
        if (checkWriteAtomic()) {
            this.base.writeBoolean(v);
        }
    }

    public void writeFloat(float f) {
        float v = f;
        if (checkWriteAtomic()) {
            this.base.writeFloat(v);
        }
    }

    public void writeDouble(double d) {
        double v = d;
        if (checkWriteAtomic()) {
            this.base.writeDouble(v);
        }
    }

    public void writeInt(int i) {
        int v = i;
        if (checkWriteAtomic()) {
            this.base.writeInt(v);
        }
    }

    public void writeLong(long j) {
        long v = j;
        if (checkWriteAtomic()) {
            this.base.writeLong(v);
        }
    }

    public void writeDocumentUri(Object obj) {
        Object uri = obj;
        if (this.nesting == 2 && (this.base instanceof TreeList)) {
            ((TreeList) this.base).writeDocumentUri(uri);
        }
    }

    public void consume(SeqPosition seqPosition) {
        SeqPosition position = seqPosition;
        writePosition(position.sequence, position.ipos);
    }

    public void writePosition(AbstractSequence abstractSequence, int i) {
        AbstractSequence seq = abstractSequence;
        int ipos = i;
        if (this.ignoringLevel <= 0) {
            if (this.stringizingLevel > 0 && this.previous == 2) {
                if (this.stringizingElementNesting < 0) {
                    write(32);
                }
                this.previous = 0;
            }
            boolean consumeNext = seq.consumeNext(ipos, this);
            if (this.stringizingLevel > 0 && this.stringizingElementNesting < 0) {
                this.previous = 2;
            }
        }
    }

    public void writeObject(Object obj) {
        Object v = obj;
        if (this.ignoringLevel <= 0) {
            if (v instanceof SeqPosition) {
                SeqPosition pos = (SeqPosition) v;
                writePosition(pos.sequence, pos.getPos());
            } else if (v instanceof TreeList) {
                ((TreeList) v).consume((Consumer) this);
            } else if ((v instanceof List) && !(v instanceof CharSeq)) {
                int i = 0;
                for (Object writeObject : (List) v) {
                    writeObject(writeObject);
                    i++;
                }
            } else if (v instanceof Keyword) {
                startAttribute(((Keyword) v).asSymbol());
                this.previous = 1;
            } else {
                closeStartTag();
                if (v instanceof UnescapedData) {
                    this.base.writeObject(v);
                    this.previous = 0;
                    return;
                }
                if (this.previous == 2) {
                    write(32);
                }
                TextUtils.textValue(v, this);
                this.previous = 2;
            }
        }
    }

    public XMLFilter(Consumer consumer) {
        TreeList treeList;
        Consumer out2 = consumer;
        this.base = out2;
        this.out = out2;
        if (out2 instanceof NodeTree) {
            this.tlist = (NodeTree) out2;
        } else {
            new TreeList();
            this.tlist = treeList;
        }
        this.namespaceBindings = NamespaceBinding.predefinedXML;
    }

    public void write(char[] cArr, int i, int i2) {
        char[] data = cArr;
        int start = i;
        int length = i2;
        if (length == 0) {
            writeJoiner();
        } else if (checkWriteAtomic()) {
            this.base.write(data, start, length);
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
        if (length == 0) {
            writeJoiner();
        } else if (checkWriteAtomic()) {
            this.base.write(str, start, length);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean inElement() {
        int i = this.nesting;
        while (i > 0 && this.workStack[i - 1] == null) {
            i -= 2;
        }
        return i != 0;
    }

    public void linefeedFromParser() {
        if (inElement() && checkWriteAtomic()) {
            this.base.write(10);
        }
    }

    public void textFromParser(char[] cArr, int i, int i2) {
        char[] data = cArr;
        int start = i;
        int length = i2;
        if (!inElement()) {
            for (int i3 = 0; i3 != length; i3++) {
                if (!Character.isWhitespace(data[start + i3])) {
                    error('e', "text at document level");
                    return;
                }
            }
        } else if (length > 0 && checkWriteAtomic()) {
            this.base.write(data, start, length);
        }
    }

    /* access modifiers changed from: protected */
    public void writeJoiner() {
        this.previous = 0;
        if (this.ignoringLevel == 0) {
            ((TreeList) this.base).writeJoiner();
        }
    }

    public void writeCDATA(char[] cArr, int i, int i2) {
        char[] data = cArr;
        int start = i;
        int length = i2;
        if (!checkWriteAtomic()) {
            return;
        }
        if (this.base instanceof XConsumer) {
            ((XConsumer) this.base).writeCDATA(data, start, length);
        } else {
            write(data, start, length);
        }
    }

    /* access modifiers changed from: protected */
    public void startElementCommon() {
        closeStartTag();
        if (this.stringizingLevel == 0) {
            ensureSpaceInWorkStack(this.nesting);
            this.workStack[this.nesting] = this.namespaceBindings;
            this.tlist.startElement(0);
            this.base = this.tlist;
            this.attrCount = 0;
        } else {
            if (this.previous == 2 && this.stringizingElementNesting < 0) {
                write(32);
            }
            this.previous = 0;
            if (this.stringizingElementNesting < 0) {
                this.stringizingElementNesting = this.nesting;
            }
        }
        this.nesting += 2;
    }

    public void emitStartElement(char[] data, int start, int count) {
        closeStartTag();
        MappingInfo info = lookupTag(data, start, count);
        startElementCommon();
        ensureSpaceInWorkStack(this.nesting - 1);
        this.workStack[this.nesting - 1] = info;
    }

    public void startElement(Object obj) {
        NamespaceBinding inherited;
        NamespaceBinding namespaceBinding;
        Object type = obj;
        startElementCommon();
        if (this.stringizingLevel == 0) {
            ensureSpaceInWorkStack(this.nesting - 1);
            this.workStack[this.nesting - 1] = type;
            if (this.copyNamespacesMode == 0) {
                this.namespaceBindings = NamespaceBinding.predefinedXML;
            } else if (this.copyNamespacesMode == 1 || this.nesting == 2) {
                this.namespaceBindings = type instanceof XName ? ((XName) type).getNamespaceNodes() : NamespaceBinding.predefinedXML;
            } else {
                int i = 2;
                while (true) {
                    if (i == this.nesting) {
                        inherited = null;
                        break;
                    } else if (this.workStack[i + 1] != null) {
                        inherited = (NamespaceBinding) this.workStack[i];
                        break;
                    } else {
                        i += 2;
                    }
                }
                if (inherited == null) {
                    if (type instanceof XName) {
                        namespaceBinding = ((XName) type).getNamespaceNodes();
                    } else {
                        namespaceBinding = NamespaceBinding.predefinedXML;
                    }
                    this.namespaceBindings = namespaceBinding;
                } else if (this.copyNamespacesMode == 2) {
                    this.namespaceBindings = inherited;
                } else if (type instanceof XName) {
                    NamespaceBinding preserved = ((XName) type).getNamespaceNodes();
                    if (NamespaceBinding.commonAncestor(inherited, preserved) == inherited) {
                        this.namespaceBindings = preserved;
                    } else {
                        this.namespaceBindings = mergeHelper(inherited, preserved);
                    }
                } else {
                    this.namespaceBindings = inherited;
                }
            }
        }
    }

    private NamespaceBinding mergeHelper(NamespaceBinding namespaceBinding, NamespaceBinding namespaceBinding2) {
        NamespaceBinding list = namespaceBinding;
        NamespaceBinding node = namespaceBinding2;
        if (node == NamespaceBinding.predefinedXML) {
            return list;
        }
        NamespaceBinding list2 = mergeHelper(list, node.next);
        String uri = node.uri;
        if (list2 == null) {
            if (uri == null) {
                return list2;
            }
            list2 = NamespaceBinding.predefinedXML;
        }
        String prefix = node.prefix;
        String found = list2.resolve(prefix);
        if (found != null ? found.equals(uri) : uri == null) {
            return list2;
        }
        return findNamespaceBinding(prefix, uri, list2);
    }

    private boolean startAttributeCommon() {
        if (this.stringizingElementNesting >= 0) {
            this.ignoringLevel++;
        }
        int i = this.stringizingLevel;
        int i2 = i;
        this.stringizingLevel = i + 1;
        if (i2 > 0) {
            return false;
        }
        if (this.attrCount < 0) {
            this.attrCount = 0;
        }
        ensureSpaceInWorkStack(this.nesting + this.attrCount);
        ensureSpaceInStartIndexes(this.attrCount);
        this.startIndexes[this.attrCount] = this.tlist.gapStart;
        this.attrCount++;
        return true;
    }

    public void startAttribute(Object obj) {
        StringBuilder sb;
        Object attrType = obj;
        this.previous = 0;
        if (attrType instanceof Symbol) {
            Symbol sym = (Symbol) attrType;
            String local = sym.getLocalPart();
            this.attrLocalName = local;
            this.attrPrefix = sym.getPrefix();
            String uri = sym.getNamespaceURI();
            if (uri == "http://www.w3.org/2000/xmlns/" || (uri == "" && local == "xmlns")) {
                error('e', "arttribute name cannot be 'xmlns' or in xmlns namespace");
            }
        }
        if (this.nesting == 2 && this.workStack[1] == null) {
            error('e', "attribute not allowed at document level");
        }
        if (this.attrCount < 0 && this.nesting > 0) {
            new StringBuilder();
            error('e', sb.append("attribute '").append(attrType).append("' follows non-attribute content").toString());
        }
        if (startAttributeCommon()) {
            this.workStack[(this.nesting + this.attrCount) - 1] = attrType;
            if (this.nesting == 0) {
                this.base.startAttribute(attrType);
            } else {
                this.tlist.startAttribute(0);
            }
        }
    }

    public void emitStartAttribute(char[] cArr, int i, int i2) {
        char[] data = cArr;
        int start = i;
        int count = i2;
        if (this.attrLocalName != null) {
            endAttribute();
        }
        if (startAttributeCommon()) {
            MappingInfo info = lookupTag(data, start, count);
            this.workStack[(this.nesting + this.attrCount) - 1] = info;
            String prefix = info.prefix;
            String local = info.local;
            this.attrLocalName = local;
            this.attrPrefix = prefix;
            if (prefix != null) {
                if (prefix == "xmlns") {
                    this.currentNamespacePrefix = local;
                }
            } else if (local == "xmlns" && prefix == null) {
                this.currentNamespacePrefix = "";
            }
            if (this.currentNamespacePrefix == null || this.namespacePrefixes) {
                this.tlist.startAttribute(0);
            }
        }
    }

    public void emitEndAttributes() {
        if (this.attrLocalName != null) {
            endAttribute();
        }
        closeStartTag();
    }

    public void emitEndElement(char[] cArr, int i, int i2) {
        StringBuffer stringBuffer;
        char[] data = cArr;
        int start = i;
        int length = i2;
        if (this.attrLocalName != null) {
            error('e', "unclosed attribute");
            endAttribute();
        }
        if (!inElement()) {
            error('e', "unmatched end element");
            return;
        }
        if (data != null) {
            MappingInfo info = lookupTag(data, start, length);
            Object old = this.workStack[this.nesting - 1];
            if ((old instanceof MappingInfo) && !this.mismatchReported) {
                MappingInfo mold = (MappingInfo) old;
                if (!(info.local == mold.local && info.prefix == mold.prefix)) {
                    new StringBuffer("</");
                    StringBuffer sbuf = stringBuffer;
                    StringBuffer append = sbuf.append(data, start, length);
                    StringBuffer append2 = sbuf.append("> matching <");
                    String oldPrefix = mold.prefix;
                    if (oldPrefix != null) {
                        StringBuffer append3 = sbuf.append(oldPrefix);
                        StringBuffer append4 = sbuf.append(':');
                    }
                    StringBuffer append5 = sbuf.append(mold.local);
                    StringBuffer append6 = sbuf.append('>');
                    error('e', sbuf.toString());
                    this.mismatchReported = true;
                }
            }
        }
        closeStartTag();
        if (this.nesting > 0) {
            endElement();
        }
    }

    public void endElement() {
        closeStartTag();
        this.nesting -= 2;
        this.previous = 0;
        if (this.stringizingLevel == 0) {
            this.namespaceBindings = (NamespaceBinding) this.workStack[this.nesting];
            this.workStack[this.nesting] = null;
            this.workStack[this.nesting + 1] = null;
            this.base.endElement();
        } else if (this.stringizingElementNesting == this.nesting) {
            this.stringizingElementNesting = -1;
            this.previous = 2;
        }
    }

    public void emitEntityReference(char[] cArr, int i, int i2) {
        char[] name = cArr;
        int start = i;
        int length = i2;
        char c0 = name[start];
        char ch = '?';
        if (length == 2 && name[start + 1] == 't') {
            if (c0 == 'l') {
                ch = '<';
            } else if (c0 == 'g') {
                ch = '>';
            }
        } else if (length == 3) {
            if (c0 == 'a' && name[start + 1] == 'm' && name[start + 2] == 'p') {
                ch = '&';
            }
        } else if (length == 4) {
            char c1 = name[start + 1];
            char c2 = name[start + 2];
            char c3 = name[start + 3];
            if (c0 == 'q' && c1 == 'u' && c2 == 'o' && c3 == 't') {
                ch = '\"';
            } else if (c0 == 'a' && c1 == 'p' && c2 == 'o' && c3 == 's') {
                ch = '\'';
            }
        }
        write((int) ch);
    }

    public void emitCharacterReference(int i, char[] cArr, int i2, int i3) {
        int value = i;
        char[] cArr2 = cArr;
        int i4 = i2;
        int i5 = i3;
        if (value >= 65536) {
            Char.print(value, this);
        } else {
            write(value);
        }
    }

    /* access modifiers changed from: protected */
    public void checkValidComment(char[] cArr, int i, int length) {
        char[] chars = cArr;
        int offset = i;
        int i2 = length;
        boolean z = true;
        while (true) {
            boolean sawHyphen = z;
            i2--;
            if (i2 >= 0) {
                boolean curHyphen = chars[offset + i2] == '-';
                if (!sawHyphen || !curHyphen) {
                    z = curHyphen;
                } else {
                    error('e', "consecutive or final hyphen in XML comment");
                    return;
                }
            } else {
                return;
            }
        }
    }

    public void writeComment(char[] cArr, int i, int i2) {
        char[] chars = cArr;
        int start = i;
        int length = i2;
        checkValidComment(chars, start, length);
        commentFromParser(chars, start, length);
    }

    public void commentFromParser(char[] cArr, int i, int i2) {
        char[] chars = cArr;
        int start = i;
        int length = i2;
        if (this.stringizingLevel == 0) {
            closeStartTag();
            if (this.base instanceof XConsumer) {
                ((XConsumer) this.base).writeComment(chars, start, length);
            }
        } else if (this.stringizingElementNesting < 0) {
            this.base.write(chars, start, length);
        }
    }

    public void writeProcessingInstruction(String target, char[] cArr, int i, int i2) {
        StringBuilder sb;
        char[] content = cArr;
        int offset = i;
        int length = i2;
        String target2 = TextUtils.replaceWhitespace(target, true);
        int i3 = offset + length;
        while (true) {
            i3--;
            if (i3 < offset) {
                break;
            }
            char ch = content[i3];
            while (true) {
                if (ch != '>') {
                    break;
                }
                i3--;
                if (i3 < offset) {
                    break;
                }
                ch = content[i3];
                if (ch == '?') {
                    error('e', "'?>' is not allowed in a processing-instruction");
                    break;
                }
            }
        }
        if ("xml".equalsIgnoreCase(target2)) {
            error('e', "processing-instruction target may not be 'xml' (ignoring case)");
        }
        if (!XName.isNCName(target2)) {
            new StringBuilder();
            error('e', sb.append("processing-instruction target '").append(target2).append("' is not a valid Name").toString());
        }
        processingInstructionCommon(target2, content, offset, length);
    }

    /* access modifiers changed from: package-private */
    public void processingInstructionCommon(String str, char[] cArr, int i, int i2) {
        String target = str;
        char[] content = cArr;
        int offset = i;
        int length = i2;
        if (this.stringizingLevel == 0) {
            closeStartTag();
            if (this.base instanceof XConsumer) {
                ((XConsumer) this.base).writeProcessingInstruction(target, content, offset, length);
            }
        } else if (this.stringizingElementNesting < 0) {
            this.base.write(content, offset, length);
        }
    }

    public void processingInstructionFromParser(char[] cArr, int i, int i2, int i3, int i4) {
        String target;
        char[] buffer = cArr;
        int tstart = i;
        int tlength = i2;
        int dstart = i3;
        int dlength = i4;
        if (tlength != 3 || inElement() || buffer[tstart] != 'x' || buffer[tstart + 1] != 'm' || buffer[tstart + 2] != 'l') {
            new String(buffer, tstart, tlength);
            processingInstructionCommon(target, buffer, dstart, dlength);
        }
    }

    public void startDocument() {
        closeStartTag();
        if (this.stringizingLevel > 0) {
            writeJoiner();
            return;
        }
        if (this.nesting == 0) {
            this.base.startDocument();
        } else {
            writeJoiner();
        }
        ensureSpaceInWorkStack(this.nesting);
        this.workStack[this.nesting] = this.namespaceBindings;
        this.workStack[this.nesting + 1] = null;
        this.nesting += 2;
    }

    public void endDocument() {
        if (this.stringizingLevel > 0) {
            writeJoiner();
            return;
        }
        this.nesting -= 2;
        this.namespaceBindings = (NamespaceBinding) this.workStack[this.nesting];
        this.workStack[this.nesting] = null;
        this.workStack[this.nesting + 1] = null;
        if (this.nesting == 0) {
            this.base.endDocument();
        } else {
            writeJoiner();
        }
    }

    public void emitDoctypeDecl(char[] buffer, int target, int tlength, int data, int dlength) {
    }

    public void beginEntity(Object obj) {
        Object baseUri = obj;
        if (this.base instanceof XConsumer) {
            ((XConsumer) this.base).beginEntity(baseUri);
        }
    }

    public void endEntity() {
        if (this.base instanceof XConsumer) {
            ((XConsumer) this.base).endEntity();
        }
    }

    public XMLFilter append(char c) {
        write((int) c);
        return this;
    }

    public XMLFilter append(CharSequence charSequence) {
        CharSequence csq = charSequence;
        if (csq == null) {
            csq = "null";
        }
        XMLFilter append = append(csq, 0, csq.length());
        return this;
    }

    public XMLFilter append(CharSequence charSequence, int i, int i2) {
        CharSequence csq = charSequence;
        int start = i;
        int end = i2;
        if (csq == null) {
            csq = "null";
        }
        write(csq, start, end - start);
        return this;
    }

    /* access modifiers changed from: package-private */
    public MappingInfo lookupTag(Symbol symbol) {
        MappingInfo mappingInfo;
        Symbol qname = symbol;
        String local = qname.getLocalPart();
        String prefix = qname.getPrefix();
        if (prefix == "") {
            prefix = null;
        }
        String uri = qname.getNamespaceURI();
        int hash = MappingInfo.hash(prefix, local);
        int index = hash & this.mappingTableMask;
        MappingInfo first = this.mappingTable[index];
        MappingInfo mappingInfo2 = first;
        while (true) {
            MappingInfo info = mappingInfo2;
            if (info == null) {
                new MappingInfo();
                MappingInfo info2 = mappingInfo;
                info2.qname = qname;
                info2.prefix = prefix;
                info2.uri = uri;
                info2.local = local;
                info2.tagHash = hash;
                info2.nextInBucket = first;
                this.mappingTable[index] = first;
                return info2;
            } else if (qname == info.qname) {
                return info;
            } else {
                if (local == info.local && info.qname == null && ((uri == info.uri || info.uri == null) && prefix == info.prefix)) {
                    info.uri = uri;
                    info.qname = qname;
                    return info;
                }
                mappingInfo2 = info.nextInBucket;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public MappingInfo lookupTag(char[] cArr, int i, int i2) {
        MappingInfo mappingInfo;
        String str;
        String str2;
        String str3;
        int i3;
        char[] data = cArr;
        int start = i;
        int length = i2;
        int hash = 0;
        int prefixHash = 0;
        int colon = -1;
        for (int i4 = 0; i4 < length; i4++) {
            char ch = data[start + i4];
            if (ch != ':' || colon >= 0) {
                i3 = (31 * hash) + ch;
            } else {
                colon = i4;
                prefixHash = hash;
                i3 = 0;
            }
            hash = i3;
        }
        int hash2 = prefixHash ^ hash;
        int index = hash2 & this.mappingTableMask;
        MappingInfo first = this.mappingTable[index];
        MappingInfo mappingInfo2 = first;
        while (true) {
            MappingInfo info = mappingInfo2;
            if (info == null) {
                new MappingInfo();
                MappingInfo info2 = mappingInfo;
                info2.tagHash = hash2;
                if (colon >= 0) {
                    new String(data, start, colon);
                    info2.prefix = str2.intern();
                    int colon2 = colon + 1;
                    new String(data, start + colon2, length - colon2);
                    info2.local = str3.intern();
                } else {
                    info2.prefix = null;
                    new String(data, start, length);
                    info2.local = str.intern();
                }
                info2.nextInBucket = first;
                this.mappingTable[index] = first;
                return info2;
            } else if (hash2 == info.tagHash && info.match(data, start, length)) {
                return info;
            } else {
                mappingInfo2 = info.nextInBucket;
            }
        }
    }

    private void ensureSpaceInWorkStack(int i) {
        int oldSize = i;
        if (this.workStack == null) {
            this.workStack = new Object[20];
        } else if (oldSize >= this.workStack.length) {
            Object[] tmpn = new Object[(2 * this.workStack.length)];
            System.arraycopy(this.workStack, 0, tmpn, 0, oldSize);
            this.workStack = tmpn;
        }
    }

    private void ensureSpaceInStartIndexes(int i) {
        int oldSize = i;
        if (this.startIndexes == null) {
            this.startIndexes = new int[20];
        } else if (oldSize >= this.startIndexes.length) {
            int[] tmpn = new int[(2 * this.startIndexes.length)];
            System.arraycopy(this.startIndexes, 0, tmpn, 0, oldSize);
            this.startIndexes = tmpn;
        }
    }

    public static String duplicateAttributeMessage(Symbol symbol, Object obj) {
        StringBuffer stringBuffer;
        Symbol attrSymbol = symbol;
        Object elementName = obj;
        new StringBuffer("duplicate attribute: ");
        StringBuffer sbuf = stringBuffer;
        String uri = attrSymbol.getNamespaceURI();
        if (uri != null && uri.length() > 0) {
            StringBuffer append = sbuf.append('{');
            StringBuffer append2 = sbuf.append('}');
            StringBuffer append3 = sbuf.append(uri);
        }
        StringBuffer append4 = sbuf.append(attrSymbol.getLocalPart());
        if (elementName != null) {
            StringBuffer append5 = sbuf.append(" in <");
            StringBuffer append6 = sbuf.append(elementName);
            StringBuffer append7 = sbuf.append('>');
        }
        return sbuf.toString();
    }

    public void error(char c, String str) {
        Throwable th;
        char severity = c;
        String message = str;
        if (this.messages == null) {
            Throwable th2 = th;
            new RuntimeException(message);
            throw th2;
        } else if (this.locator != null) {
            this.messages.error(severity, this.locator, message);
        } else {
            this.messages.error(severity, message);
        }
    }

    public boolean ignoring() {
        return this.ignoringLevel > 0;
    }

    public void setDocumentLocator(Locator locator2) {
        Locator locator3 = locator2;
        if (locator3 instanceof SourceLocator) {
            this.locator = (SourceLocator) locator3;
        }
    }

    public void startElement(String namespaceURI, String localName, String str, Attributes attributes) {
        String str2 = str;
        Attributes atts = attributes;
        startElement(Symbol.make(namespaceURI, localName));
        int numAttributes = atts.getLength();
        for (int i = 0; i < numAttributes; i++) {
            startAttribute(Symbol.make(atts.getURI(i), atts.getLocalName(i)));
            write(atts.getValue(i));
            endAttribute();
        }
    }

    public void endElement(String str, String str2, String str3) {
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        endElement();
    }

    public void startElement(String name, AttributeList attributeList) {
        AttributeList atts = attributeList;
        startElement(name.intern());
        int attrLength = atts.getLength();
        for (int i = 0; i < attrLength; i++) {
            String name2 = atts.getName(i).intern();
            String type = atts.getType(i);
            String value = atts.getValue(i);
            startAttribute(name2);
            write(value);
            endAttribute();
        }
    }

    public void endElement(String str) throws SAXException {
        String str2 = str;
        endElement();
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        write(ch, start, length);
    }

    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        write(ch, start, length);
    }

    public void processingInstruction(String target, String data) {
        char[] chars = data.toCharArray();
        processingInstructionCommon(target, chars, 0, chars.length);
    }

    public void startPrefixMapping(String prefix, String uri) {
        this.namespaceBindings = findNamespaceBinding(prefix.intern(), uri.intern(), this.namespaceBindings);
    }

    public void endPrefixMapping(String str) {
        String str2 = str;
        this.namespaceBindings = this.namespaceBindings.getNext();
    }

    public void skippedEntity(String name) {
    }

    public String getPublicId() {
        return null;
    }

    public String getSystemId() {
        return this.f249in == null ? null : this.f249in.getName();
    }

    public String getFileName() {
        return this.f249in == null ? null : this.f249in.getName();
    }

    public int getLineNumber() {
        if (this.f249in == null) {
            return -1;
        }
        int line = this.f249in.getLineNumber();
        return line < 0 ? -1 : line + 1;
    }

    public int getColumnNumber() {
        int i;
        if (this.f249in != null) {
            int columnNumber = this.f249in.getColumnNumber();
            int col = columnNumber;
            if (columnNumber > 0) {
                i = col;
                return i;
            }
        }
        i = -1;
        return i;
    }

    public boolean isStableSourceLocation() {
        return false;
    }
}
