package gnu.xquery.lang;

import android.support.p000v4.media.session.PlaybackStateCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.Component;
import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.LambdaExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.functions.Convert;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.kawa.reflect.InstanceOf;
import gnu.kawa.reflect.OccurrenceType;
import gnu.kawa.reflect.SingletonType;
import gnu.kawa.xml.DescendantOrSelfAxis;
import gnu.kawa.xml.ElementType;
import gnu.kawa.xml.MakeAttribute;
import gnu.kawa.xml.MakeElement;
import gnu.kawa.xml.MakeWithBaseUri;
import gnu.kawa.xml.NodeType;
import gnu.kawa.xml.ParentAxis;
import gnu.kawa.xml.ProcessingInstructionType;
import gnu.kawa.xml.XDataType;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.TtyInPort;
import gnu.mapping.WrappedException;
import gnu.math.IntNum;
import gnu.text.FilePath;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.Path;
import gnu.text.SourceError;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.text.URIPath;
import gnu.xml.NamespaceBinding;
import gnu.xml.TextUtils;
import gnu.xml.XName;
import gnu.xquery.util.CastableAs;
import gnu.xquery.util.NamedCollator;
import gnu.xquery.util.QNameUtils;
import gnu.xquery.util.RelativeStep;
import gnu.xquery.util.ValuesFilter;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
import org.shaded.apache.http.HttpStatus;
import org.shaded.apache.http.cookie.ClientCookie;

public class XQParser extends Lexer {
    static final int ARROW_TOKEN = 82;
    static final int ATTRIBUTE_TOKEN = 252;
    static final int AXIS_ANCESTOR = 0;
    static final int AXIS_ANCESTOR_OR_SELF = 1;
    static final int AXIS_ATTRIBUTE = 2;
    static final int AXIS_CHILD = 3;
    static final int AXIS_DESCENDANT = 4;
    static final int AXIS_DESCENDANT_OR_SELF = 5;
    static final int AXIS_FOLLOWING = 6;
    static final int AXIS_FOLLOWING_SIBLING = 7;
    static final int AXIS_NAMESPACE = 8;
    static final int AXIS_PARENT = 9;
    static final int AXIS_PRECEDING = 10;
    static final int AXIS_PRECEDING_SIBLING = 11;
    static final int AXIS_SELF = 12;
    static final int CASE_DOLLAR_TOKEN = 247;
    static final int COLON_COLON_TOKEN = 88;
    static final int COLON_EQUAL_TOKEN = 76;
    static final int COMMENT_TOKEN = 254;
    static final int COUNT_OP_AXIS = 13;
    static final char DECIMAL_TOKEN = '1';
    static final int DECLARE_BASE_URI_TOKEN = 66;
    static final int DECLARE_BOUNDARY_SPACE_TOKEN = 83;
    static final int DECLARE_CONSTRUCTION_TOKEN = 75;
    static final int DECLARE_COPY_NAMESPACES_TOKEN = 76;
    static final int DECLARE_FUNCTION_TOKEN = 80;
    static final int DECLARE_NAMESPACE_TOKEN = 78;
    static final int DECLARE_OPTION_TOKEN = 111;
    static final int DECLARE_ORDERING_TOKEN = 85;
    static final int DECLARE_VARIABLE_TOKEN = 86;
    static final int DEFAULT_COLLATION_TOKEN = 71;
    static final int DEFAULT_ELEMENT_TOKEN = 69;
    static final int DEFAULT_FUNCTION_TOKEN = 79;
    static final int DEFAULT_ORDER_TOKEN = 72;
    static final int DEFINE_QNAME_TOKEN = 87;
    static final int DOCUMENT_TOKEN = 256;
    static final int DOTDOT_TOKEN = 51;
    static final Symbol DOT_VARNAME = Symbol.makeUninterned("$dot$");
    static final char DOUBLE_TOKEN = '2';
    static final int ELEMENT_TOKEN = 251;
    static final int EOF_TOKEN = -1;
    static final int EOL_TOKEN = 10;
    static final int EVERY_DOLLAR_TOKEN = 246;
    static final int FNAME_TOKEN = 70;
    static final int FOR_DOLLAR_TOKEN = 243;
    static final int IF_LPAREN_TOKEN = 241;
    static final int IMPORT_MODULE_TOKEN = 73;
    static final int IMPORT_SCHEMA_TOKEN = 84;
    static final char INTEGER_TOKEN = '0';
    static final Symbol LAST_VARNAME = Symbol.makeUninterned("$last$");
    static final int LET_DOLLAR_TOKEN = 244;
    static final int MODULE_NAMESPACE_TOKEN = 77;
    static final int NCNAME_COLON_TOKEN = 67;
    static final int NCNAME_TOKEN = 65;
    static final int OP_ADD = 413;
    static final int OP_AND = 401;
    static final int OP_ATTRIBUTE = 236;
    static final int OP_AXIS_FIRST = 100;
    static final int OP_BASE = 400;
    static final int OP_CASTABLE_AS = 424;
    static final int OP_CAST_AS = 425;
    static final int OP_COMMENT = 232;
    static final int OP_DIV = 416;
    static final int OP_DOCUMENT = 234;
    static final int OP_ELEMENT = 235;
    static final int OP_EMPTY_SEQUENCE = 238;
    static final int OP_EQ = 426;
    static final int OP_EQU = 402;
    static final int OP_EXCEPT = 421;
    static final int OP_GE = 431;
    static final int OP_GEQ = 407;
    static final int OP_GRT = 405;
    static final int OP_GRTGRT = 410;
    static final int OP_GT = 430;
    static final int OP_IDIV = 417;
    static final int OP_INSTANCEOF = 422;
    static final int OP_INTERSECT = 420;
    static final int OP_IS = 408;
    static final int OP_ISNOT = 409;
    static final int OP_ITEM = 237;
    static final int OP_LE = 429;
    static final int OP_LEQ = 406;
    static final int OP_LSS = 404;
    static final int OP_LSSLSS = 411;
    static final int OP_LT = 428;
    static final int OP_MOD = 418;
    static final int OP_MUL = 415;
    static final int OP_NE = 427;
    static final int OP_NEQ = 403;
    static final int OP_NODE = 230;
    static final int OP_OR = 400;
    static final int OP_PI = 233;
    static final int OP_RANGE_TO = 412;
    static final int OP_SCHEMA_ATTRIBUTE = 239;
    static final int OP_SCHEMA_ELEMENT = 240;
    static final int OP_SUB = 414;
    static final int OP_TEXT = 231;
    static final int OP_TREAT_AS = 423;
    static final int OP_UNION = 419;
    static final int OP_WHERE = 196;
    static final int ORDERED_LBRACE_TOKEN = 249;
    static final int PI_TOKEN = 255;
    static final Symbol POSITION_VARNAME = Symbol.makeUninterned("$position$");
    static final int PRAGMA_START_TOKEN = 197;
    static final int QNAME_TOKEN = 81;
    static final int SLASHSLASH_TOKEN = 68;
    static final int SOME_DOLLAR_TOKEN = 245;
    static final int STRING_TOKEN = 34;
    static final int TEXT_TOKEN = 253;
    static final int TYPESWITCH_LPAREN_TOKEN = 242;
    static final int UNORDERED_LBRACE_TOKEN = 250;
    static final int VALIDATE_LBRACE_TOKEN = 248;
    static final int XQUERY_VERSION_TOKEN = 89;
    public static final String[] axisNames = new String[13];
    static NamespaceBinding builtinNamespaces;
    public static final CastableAs castableAs = CastableAs.castableAs;
    public static final QuoteExp getExternalFunction;
    public static final InstanceOf instanceOf;
    static final Expression makeCDATA = makeFunctionExp("gnu.kawa.xml.MakeCDATA", "makeCDATA");
    public static QuoteExp makeChildAxisStep;
    public static QuoteExp makeDescendantAxisStep;
    public static Expression makeText = makeFunctionExp("gnu.kawa.xml.MakeText", "makeText");
    static PrimProcedure proc_OccurrenceType_getInstance;
    public static final Convert treatAs = Convert.f63as;
    public static boolean warnHidePreviousDeclaration = false;
    public static boolean warnOldVersion = true;
    Path baseURI = null;
    boolean baseURIDeclarationSeen;
    boolean boundarySpaceDeclarationSeen;
    boolean boundarySpacePreserve;
    int commentCount;
    Compilation comp;
    boolean constructionModeDeclarationSeen;
    boolean constructionModeStrip;
    NamespaceBinding constructorNamespaces = NamespaceBinding.predefinedXML;
    boolean copyNamespacesDeclarationSeen;
    int copyNamespacesMode = 3;
    int curColumn;
    int curLine;
    int curToken;
    Object curValue;
    NamedCollator defaultCollator = null;
    String defaultElementNamespace = "";
    char defaultEmptyOrder = 'L';
    boolean emptyOrderDeclarationSeen;
    int enclosedExpressionsSeen;
    String errorIfComment;
    Declaration[] flworDecls;
    int flworDeclsCount;
    int flworDeclsFirst;
    public Namespace[] functionNamespacePath = XQuery.defaultFunctionNamespacePath;
    XQuery interpreter;
    String libraryModuleNamespace;
    boolean orderingModeSeen;
    boolean orderingModeUnordered;
    int parseContext;
    int parseCount;
    NamespaceBinding prologNamespaces;
    private int saveToken;
    private Object saveValue;
    boolean seenDeclaration;
    int seenLast;
    int seenPosition;
    private boolean warnedOldStyleKindTest;

    static {
        InstanceOf instanceOf2;
        PrimProcedure primProcedure;
        Object obj;
        Object obj2;
        NamespaceBinding ns;
        NamespaceBinding ns2;
        NamespaceBinding ns3;
        NamespaceBinding ns4;
        NamespaceBinding ns5;
        NamespaceBinding ns6;
        NamespaceBinding ns7;
        NamespaceBinding ns8;
        Object obj3;
        new InstanceOf(XQuery.getInstance(), "instance");
        instanceOf = instanceOf2;
        new PrimProcedure(ClassType.make("gnu.kawa.reflect.OccurrenceType").getDeclaredMethod("getInstance", 3));
        proc_OccurrenceType_getInstance = primProcedure;
        new PrimProcedure("gnu.kawa.xml.ChildAxis", "make", 1);
        makeChildAxisStep = QuoteExp.getInstance(obj);
        new PrimProcedure("gnu.kawa.xml.DescendantAxis", "make", 1);
        makeDescendantAxisStep = QuoteExp.getInstance(obj2);
        new NamespaceBinding("xml", NamespaceBinding.XML_NAMESPACE, NamespaceBinding.predefinedXML);
        new NamespaceBinding("xs", XQuery.SCHEMA_NAMESPACE, ns);
        new NamespaceBinding("xsi", XQuery.SCHEMA_INSTANCE_NAMESPACE, ns2);
        new NamespaceBinding("fn", XQuery.XQUERY_FUNCTION_NAMESPACE, ns3);
        new NamespaceBinding("html", "http://www.w3.org/1999/xhtml", ns4);
        new NamespaceBinding("kawa", XQuery.KAWA_FUNCTION_NAMESPACE, ns5);
        new NamespaceBinding("qexo", XQuery.QEXO_FUNCTION_NAMESPACE, ns6);
        new NamespaceBinding(ImagesContract.LOCAL, XQuery.LOCAL_NAMESPACE, ns7);
        builtinNamespaces = ns8;
        new PrimProcedure("gnu.xquery.lang.XQuery", "getExternal", 2);
        getExternalFunction = QuoteExp.getInstance(obj3);
        axisNames[0] = "ancestor";
        axisNames[1] = "ancestor-or-self";
        axisNames[2] = "attribute";
        axisNames[3] = "child";
        axisNames[4] = "descendant";
        axisNames[5] = "descendant-or-self";
        axisNames[6] = "following";
        axisNames[7] = "following-sibling";
        axisNames[8] = "namespace";
        axisNames[9] = "parent";
        axisNames[10] = "preceding";
        axisNames[11] = "preceding-sibling";
        axisNames[12] = "self";
    }

    public void setStaticBaseUri(String uri) {
        StringBuilder sb;
        try {
            this.baseURI = fixupStaticBaseUri(URIPath.valueOf(uri));
        } catch (Throwable th) {
            Throwable ex = th;
            if (ex instanceof WrappedException) {
                ex = ((WrappedException) ex).getCause();
            }
            new StringBuilder();
            error('e', sb.append("invalid URI: ").append(ex.getMessage()).toString());
        }
    }

    static Path fixupStaticBaseUri(Path path) {
        Path path2 = path.getAbsolute();
        if (path2 instanceof FilePath) {
            path2 = URIPath.valueOf(path2.toURI());
        }
        return path2;
    }

    public String getStaticBaseUri() {
        Path path = this.baseURI;
        if (path == null) {
            Object value = Environment.getCurrent().get(Symbol.make("", "base-uri"), (Object) null, (Object) null);
            if (value != null) {
                if (value instanceof Path) {
                    path = path;
                } else {
                    path = URIPath.valueOf(value.toString());
                }
            }
            if (path == null) {
                LineBufferedReader port = getPort();
                LineBufferedReader port2 = port;
                if (port != null) {
                    path = port2.getPath();
                    if ((path instanceof FilePath) && (!path.exists() || (port2 instanceof TtyInPort) || (port2 instanceof CharArrayInPort))) {
                        path = null;
                    }
                }
            }
            if (path == null) {
                path = Path.currentPath();
            }
            path = fixupStaticBaseUri(path);
            this.baseURI = path;
        }
        return path.toString();
    }

    public String resolveAgainstBaseUri(String str) {
        String uri = str;
        if (Path.uriSchemeSpecified(uri)) {
            return uri;
        }
        return Path.valueOf(getStaticBaseUri()).resolve(uri).toString();
    }

    /* access modifiers changed from: package-private */
    public final int skipSpace() throws IOException, SyntaxException {
        return skipSpace(true);
    }

    /* access modifiers changed from: package-private */
    public final int skipSpace(boolean z) throws IOException, SyntaxException {
        int ch;
        boolean verticalToo = z;
        while (true) {
            ch = read();
            if (ch != 40) {
                if (ch != 123) {
                    if (!verticalToo) {
                        if (!(ch == 32 || ch == 9)) {
                            break;
                        }
                    } else if (ch < 0 || !Character.isWhitespace((char) ch)) {
                        break;
                    }
                } else {
                    int ch2 = read();
                    if (ch2 != 45) {
                        unread(ch2);
                        return 123;
                    }
                    int ch3 = read();
                    if (ch3 != 45) {
                        unread(ch3);
                        unread(45);
                        return 123;
                    }
                    skipOldComment();
                }
            } else if (!checkNext(':')) {
                return 40;
            } else {
                skipComment();
            }
        }
        return ch;
    }

    /* access modifiers changed from: package-private */
    public final void skipToSemicolon() throws IOException {
        int next;
        do {
            next = read();
            if (next < 0 || next == 59) {
            }
            next = read();
            return;
        } while (next == 59);
    }

    /* access modifiers changed from: package-private */
    public final void skipOldComment() throws IOException, SyntaxException {
        int seenDashes = 0;
        int startLine = getLineNumber() + 1;
        int startColumn = getColumnNumber() - 2;
        warnOldVersion("use (: :) instead of old-style comment {-- --}");
        while (true) {
            int ch = read();
            if (ch == 45) {
                seenDashes++;
            } else if (ch == 125 && seenDashes >= 2) {
                return;
            } else {
                if (ch < 0) {
                    this.curLine = startLine;
                    this.curColumn = startColumn;
                    eofError("non-terminated comment starting here");
                } else {
                    seenDashes = 0;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void skipComment() throws IOException, SyntaxException {
        this.commentCount++;
        int startLine = getLineNumber() + 1;
        int startColumn = getColumnNumber() - 1;
        if (this.errorIfComment != null) {
            this.curLine = startLine;
            this.curColumn = startColumn;
            error('e', this.errorIfComment);
        }
        int prev = 0;
        int commentNesting = 0;
        char saveReadState = pushNesting(':');
        while (true) {
            int ch = read();
            if (ch == 58) {
                if (prev == 40) {
                    commentNesting++;
                    ch = 0;
                }
            } else if (ch == 41 && prev == 58) {
                if (commentNesting == 0) {
                    popNesting(saveReadState);
                    return;
                }
                commentNesting--;
            } else if (ch < 0) {
                this.curLine = startLine;
                this.curColumn = startColumn;
                eofError("non-terminated comment starting here");
            }
            prev = ch;
        }
    }

    /* access modifiers changed from: package-private */
    public final int peekNonSpace(String str) throws IOException, SyntaxException {
        String message = str;
        int ch = skipSpace();
        if (ch < 0) {
            eofError(message);
        }
        unread(ch);
        return ch;
    }

    public void mark() throws IOException {
        super.mark();
        this.saveToken = this.curToken;
        this.saveValue = this.curValue;
    }

    public void reset() throws IOException {
        this.curToken = this.saveToken;
        this.curValue = this.saveValue;
        super.reset();
    }

    private int setToken(int i, int width) {
        int token = i;
        this.curToken = token;
        this.curLine = this.port.getLineNumber() + 1;
        this.curColumn = (this.port.getColumnNumber() + 1) - width;
        return token;
    }

    /* access modifiers changed from: package-private */
    public void checkSeparator(char ch) {
        if (XName.isNameStart(ch)) {
            error('e', "missing separator", "XPST0003");
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getRawToken() throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r11 = this;
            r0 = r11
        L_0x0001:
            r6 = r0
            int r6 = r6.readUnicodeChar()
            r1 = r6
            r6 = r1
            if (r6 >= 0) goto L_0x0013
            r6 = r0
            r7 = -1
            r8 = 0
            int r6 = r6.setToken(r7, r8)
            r0 = r6
        L_0x0012:
            return r0
        L_0x0013:
            r6 = r1
            r7 = 10
            if (r6 == r7) goto L_0x001d
            r6 = r1
            r7 = 13
            if (r6 != r7) goto L_0x002c
        L_0x001d:
            r6 = r0
            int r6 = r6.nesting
            if (r6 > 0) goto L_0x0001
            r6 = r0
            r7 = 10
            r8 = 0
            int r6 = r6.setToken(r7, r8)
            r0 = r6
            goto L_0x0012
        L_0x002c:
            r6 = r1
            r7 = 40
            if (r6 != r7) goto L_0x005c
            r6 = r0
            r7 = 58
            boolean r6 = r6.checkNext(r7)
            if (r6 == 0) goto L_0x003f
            r6 = r0
            r6.skipComment()
            goto L_0x0001
        L_0x003f:
            r6 = r0
            r7 = 35
            boolean r6 = r6.checkNext(r7)
            if (r6 == 0) goto L_0x0052
            r6 = r0
            r7 = 197(0xc5, float:2.76E-43)
            r8 = 2
            int r6 = r6.setToken(r7, r8)
            r0 = r6
            goto L_0x0012
        L_0x0052:
            r6 = r0
            r7 = 40
            r8 = 1
            int r6 = r6.setToken(r7, r8)
            r0 = r6
            goto L_0x0012
        L_0x005c:
            r6 = r1
            r7 = 123(0x7b, float:1.72E-43)
            if (r6 != r7) goto L_0x0097
            r6 = r0
            r7 = 45
            boolean r6 = r6.checkNext(r7)
            if (r6 != 0) goto L_0x0074
            r6 = r0
            r7 = 123(0x7b, float:1.72E-43)
            r8 = 1
            int r6 = r6.setToken(r7, r8)
            r0 = r6
            goto L_0x0012
        L_0x0074:
            r6 = r0
            int r6 = r6.read()
            r1 = r6
            r6 = r1
            r7 = 45
            if (r6 == r7) goto L_0x0091
            r6 = r0
            r6.unread()
            r6 = r0
            r6.unread()
            r6 = r0
            r7 = 123(0x7b, float:1.72E-43)
            r8 = 1
            int r6 = r6.setToken(r7, r8)
            r0 = r6
            goto L_0x0012
        L_0x0091:
            r6 = r0
            r6.skipOldComment()
            goto L_0x0001
        L_0x0097:
            r6 = r1
            r7 = 32
            if (r6 == r7) goto L_0x0001
            r6 = r1
            r7 = 9
            if (r6 == r7) goto L_0x0001
            r6 = r0
            r7 = 0
            r6.tokenBufferLength = r7
            r6 = r0
            r7 = r0
            gnu.text.LineBufferedReader r7 = r7.port
            int r7 = r7.getLineNumber()
            r8 = 1
            int r7 = r7 + 1
            r6.curLine = r7
            r6 = r0
            r7 = r0
            gnu.text.LineBufferedReader r7 = r7.port
            int r7 = r7.getColumnNumber()
            r6.curColumn = r7
            r6 = r1
            char r6 = (char) r6
            r2 = r6
            r6 = r2
            switch(r6) {
                case 33: goto L_0x0165;
                case 34: goto L_0x01c8;
                case 36: goto L_0x013a;
                case 39: goto L_0x01c8;
                case 41: goto L_0x013a;
                case 42: goto L_0x0159;
                case 43: goto L_0x015d;
                case 44: goto L_0x013a;
                case 45: goto L_0x0161;
                case 47: goto L_0x0172;
                case 58: goto L_0x013b;
                case 59: goto L_0x013a;
                case 60: goto L_0x01ab;
                case 61: goto L_0x017f;
                case 62: goto L_0x018f;
                case 63: goto L_0x013a;
                case 64: goto L_0x013a;
                case 91: goto L_0x013a;
                case 93: goto L_0x013a;
                case 124: goto L_0x0155;
                case 125: goto L_0x013a;
                default: goto L_0x00c3;
            }
        L_0x00c3:
            r6 = r2
            boolean r6 = java.lang.Character.isDigit(r6)
            if (r6 != 0) goto L_0x00db
            r6 = r2
            r7 = 46
            if (r6 != r7) goto L_0x0268
            r6 = r0
            int r6 = r6.peek()
            char r6 = (char) r6
            boolean r6 = java.lang.Character.isDigit(r6)
            if (r6 == 0) goto L_0x0268
        L_0x00db:
            r6 = r2
            r7 = 46
            if (r6 != r7) goto L_0x020e
            r6 = 1
        L_0x00e1:
            r4 = r6
        L_0x00e2:
            r6 = r0
            r7 = r2
            r6.tokenBufferAppend(r7)
            r6 = r0
            int r6 = r6.read()
            r1 = r6
            r6 = r1
            if (r6 >= 0) goto L_0x0211
        L_0x00f0:
            r6 = r1
            r7 = 101(0x65, float:1.42E-43)
            if (r6 == r7) goto L_0x00fa
            r6 = r1
            r7 = 69
            if (r6 != r7) goto L_0x024f
        L_0x00fa:
            r6 = r0
            r7 = r1
            char r7 = (char) r7
            r6.tokenBufferAppend(r7)
            r6 = r0
            int r6 = r6.read()
            r1 = r6
            r6 = r1
            r7 = 43
            if (r6 == r7) goto L_0x0110
            r6 = r1
            r7 = 45
            if (r6 != r7) goto L_0x011b
        L_0x0110:
            r6 = r0
            r7 = r1
            r6.tokenBufferAppend(r7)
            r6 = r0
            int r6 = r6.read()
            r1 = r6
        L_0x011b:
            r6 = 0
            r5 = r6
        L_0x011d:
            r6 = r1
            if (r6 >= 0) goto L_0x022b
        L_0x0120:
            r6 = r5
            if (r6 != 0) goto L_0x012f
            r6 = r0
            r7 = 101(0x65, float:1.42E-43)
            java.lang.String r8 = "no digits following exponent"
            java.lang.String r9 = "XPST0003"
            r6.error(r7, r8, r9)
        L_0x012f:
            r6 = 50
            r2 = r6
        L_0x0132:
            r6 = r0
            r7 = r2
            r6.curToken = r7
            r6 = r2
            r0 = r6
            goto L_0x0012
        L_0x013a:
            goto L_0x0132
        L_0x013b:
            r6 = r0
            r7 = 61
            boolean r6 = r6.checkNext(r7)
            if (r6 == 0) goto L_0x0148
            r6 = 76
            r2 = r6
            goto L_0x0132
        L_0x0148:
            r6 = r0
            r7 = 58
            boolean r6 = r6.checkNext(r7)
            if (r6 == 0) goto L_0x0132
            r6 = 88
            r2 = r6
            goto L_0x0132
        L_0x0155:
            r6 = 419(0x1a3, float:5.87E-43)
            r2 = r6
            goto L_0x0132
        L_0x0159:
            r6 = 415(0x19f, float:5.82E-43)
            r2 = r6
            goto L_0x0132
        L_0x015d:
            r6 = 413(0x19d, float:5.79E-43)
            r2 = r6
            goto L_0x0132
        L_0x0161:
            r6 = 414(0x19e, float:5.8E-43)
            r2 = r6
            goto L_0x0132
        L_0x0165:
            r6 = r0
            r7 = 61
            boolean r6 = r6.checkNext(r7)
            if (r6 == 0) goto L_0x0132
            r6 = 403(0x193, float:5.65E-43)
            r2 = r6
            goto L_0x0132
        L_0x0172:
            r6 = r0
            r7 = 47
            boolean r6 = r6.checkNext(r7)
            if (r6 == 0) goto L_0x0132
            r6 = 68
            r2 = r6
            goto L_0x0132
        L_0x017f:
            r6 = r0
            r7 = 62
            boolean r6 = r6.checkNext(r7)
            if (r6 == 0) goto L_0x018b
            r6 = 82
            r2 = r6
        L_0x018b:
            r6 = 402(0x192, float:5.63E-43)
            r2 = r6
            goto L_0x0132
        L_0x018f:
            r6 = r0
            r7 = 61
            boolean r6 = r6.checkNext(r7)
            if (r6 == 0) goto L_0x019c
            r6 = 407(0x197, float:5.7E-43)
        L_0x019a:
            r2 = r6
            goto L_0x0132
        L_0x019c:
            r6 = r0
            r7 = 62
            boolean r6 = r6.checkNext(r7)
            if (r6 == 0) goto L_0x01a8
            r6 = 410(0x19a, float:5.75E-43)
            goto L_0x019a
        L_0x01a8:
            r6 = 405(0x195, float:5.68E-43)
            goto L_0x019a
        L_0x01ab:
            r6 = r0
            r7 = 61
            boolean r6 = r6.checkNext(r7)
            if (r6 == 0) goto L_0x01b9
            r6 = 406(0x196, float:5.69E-43)
        L_0x01b6:
            r2 = r6
            goto L_0x0132
        L_0x01b9:
            r6 = r0
            r7 = 60
            boolean r6 = r6.checkNext(r7)
            if (r6 == 0) goto L_0x01c5
            r6 = 411(0x19b, float:5.76E-43)
            goto L_0x01b6
        L_0x01c5:
            r6 = 404(0x194, float:5.66E-43)
            goto L_0x01b6
        L_0x01c8:
            r6 = r0
            r7 = r1
            char r7 = (char) r7
            char r6 = r6.pushNesting(r7)
            r3 = r6
        L_0x01d0:
            r6 = r0
            int r6 = r6.readUnicodeChar()
            r1 = r6
            r6 = r1
            if (r6 >= 0) goto L_0x01e0
            r6 = r0
            java.lang.String r7 = "unexpected end-of-file in string starting here"
            r6.eofError(r7)
        L_0x01e0:
            r6 = r1
            r7 = 38
            if (r6 != r7) goto L_0x01ea
            r6 = r0
            r6.parseEntityOrCharRef()
            goto L_0x01d0
        L_0x01ea:
            r6 = r2
            r7 = r1
            if (r6 != r7) goto L_0x0208
            r6 = r0
            int r6 = r6.peek()
            r1 = r6
            r6 = r2
            r7 = r1
            if (r6 == r7) goto L_0x0202
            r6 = r0
            r7 = r3
            r6.popNesting(r7)
            r6 = 34
            r2 = r6
            goto L_0x0132
        L_0x0202:
            r6 = r0
            int r6 = r6.read()
            r1 = r6
        L_0x0208:
            r6 = r0
            r7 = r1
            r6.tokenBufferAppend(r7)
            goto L_0x01d0
        L_0x020e:
            r6 = 0
            goto L_0x00e1
        L_0x0211:
            r6 = r1
            char r6 = (char) r6
            r2 = r6
            r6 = r2
            r7 = 46
            if (r6 != r7) goto L_0x0222
            r6 = r4
            if (r6 == 0) goto L_0x021e
            goto L_0x00f0
        L_0x021e:
            r6 = 1
            r4 = r6
            goto L_0x00e2
        L_0x0222:
            r6 = r2
            boolean r6 = java.lang.Character.isDigit(r6)
            if (r6 != 0) goto L_0x00e2
            goto L_0x00f0
        L_0x022b:
            r6 = r1
            char r6 = (char) r6
            r2 = r6
            r6 = r2
            boolean r6 = java.lang.Character.isDigit(r6)
            if (r6 != 0) goto L_0x0240
            r6 = r0
            r7 = r2
            r6.checkSeparator(r7)
            r6 = r0
            r6.unread()
            goto L_0x0120
        L_0x0240:
            r6 = r0
            r7 = r2
            r6.tokenBufferAppend(r7)
            r6 = r0
            int r6 = r6.read()
            r1 = r6
            int r5 = r5 + 1
            goto L_0x011d
        L_0x024f:
            r6 = r4
            if (r6 == 0) goto L_0x0265
            r6 = 49
        L_0x0254:
            r2 = r6
            r6 = r1
            if (r6 < 0) goto L_0x0132
            r6 = r0
            r7 = r1
            char r7 = (char) r7
            r6.checkSeparator(r7)
            r6 = r0
            r7 = r1
            r6.unread(r7)
            goto L_0x0132
        L_0x0265:
            r6 = 48
            goto L_0x0254
        L_0x0268:
            r6 = r2
            r7 = 46
            if (r6 != r7) goto L_0x027b
            r6 = r0
            r7 = 46
            boolean r6 = r6.checkNext(r7)
            if (r6 == 0) goto L_0x0132
            r6 = 51
            r2 = r6
            goto L_0x0132
        L_0x027b:
            r6 = r2
            boolean r6 = gnu.xml.XName.isNameStart(r6)
            if (r6 == 0) goto L_0x02f9
        L_0x0282:
            r6 = r0
            r7 = r2
            r6.tokenBufferAppend(r7)
            r6 = r0
            int r6 = r6.read()
            r1 = r6
            r6 = r1
            char r6 = (char) r6
            r2 = r6
            r6 = r2
            boolean r6 = gnu.xml.XName.isNamePart(r6)
            if (r6 != 0) goto L_0x0282
            r6 = r1
            if (r6 >= 0) goto L_0x029f
            r6 = 65
            r2 = r6
            goto L_0x0132
        L_0x029f:
            r6 = r1
            r7 = 58
            if (r6 == r7) goto L_0x02ae
            r6 = 65
            r2 = r6
        L_0x02a7:
            r6 = r0
            r7 = r1
            r6.unread(r7)
            goto L_0x0132
        L_0x02ae:
            r6 = r0
            int r6 = r6.read()
            r1 = r6
            r6 = r1
            if (r6 >= 0) goto L_0x02be
            r6 = r0
            java.lang.String r7 = "unexpected end-of-file after NAME ':'"
            r6.eofError(r7)
        L_0x02be:
            r6 = r1
            char r6 = (char) r6
            r2 = r6
            r6 = r2
            boolean r6 = gnu.xml.XName.isNameStart(r6)
            if (r6 == 0) goto L_0x02e7
            r6 = r0
            r7 = 58
            r6.tokenBufferAppend(r7)
        L_0x02ce:
            r6 = r0
            r7 = r2
            r6.tokenBufferAppend(r7)
            r6 = r0
            int r6 = r6.read()
            r1 = r6
            r6 = r1
            char r6 = (char) r6
            r2 = r6
            r6 = r2
            boolean r6 = gnu.xml.XName.isNamePart(r6)
            if (r6 != 0) goto L_0x02ce
            r6 = 81
            r2 = r6
            goto L_0x02a7
        L_0x02e7:
            r6 = r2
            r7 = 61
            if (r6 != r7) goto L_0x02f5
            r6 = r0
            r7 = r2
            r6.unread(r7)
            r6 = 65
            r2 = r6
            goto L_0x02a7
        L_0x02f5:
            r6 = 67
            r2 = r6
            goto L_0x02a7
        L_0x02f9:
            r6 = r2
            r7 = 32
            if (r6 < r7) goto L_0x0328
            r6 = r2
            r7 = 127(0x7f, float:1.78E-43)
            if (r6 >= r7) goto L_0x0328
            r6 = r0
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r10 = r7
            r7 = r10
            r8 = r10
            r8.<init>()
            java.lang.String r8 = "invalid character '"
            java.lang.StringBuilder r7 = r7.append(r8)
            r8 = r2
            java.lang.StringBuilder r7 = r7.append(r8)
            r8 = 39
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            gnu.expr.Expression r6 = r6.syntaxError(r7)
            goto L_0x0132
        L_0x0328:
            r6 = r0
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r10 = r7
            r7 = r10
            r8 = r10
            r8.<init>()
            java.lang.String r8 = "invalid character '\\u"
            java.lang.StringBuilder r7 = r7.append(r8)
            r8 = r2
            java.lang.String r8 = java.lang.Integer.toHexString(r8)
            java.lang.StringBuilder r7 = r7.append(r8)
            r8 = 39
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            gnu.expr.Expression r6 = r6.syntaxError(r7)
            goto L_0x0132
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQParser.getRawToken():int");
    }

    public void getDelimited(String str) throws IOException, SyntaxException {
        StringBuilder sb;
        String delimiter = str;
        if (!readDelimited(delimiter)) {
            new StringBuilder();
            eofError(sb.append("unexpected end-of-file looking for '").append(delimiter).append('\'').toString());
        }
    }

    public void appendNamedEntity(String name) {
        StringBuilder sb;
        String name2 = name.intern();
        char ch = '?';
        if (name2 == "lt") {
            ch = '<';
        } else if (name2 == "gt") {
            ch = '>';
        } else if (name2 == "amp") {
            ch = '&';
        } else if (name2 == "quot") {
            ch = '\"';
        } else if (name2 == "apos") {
            ch = '\'';
        } else {
            new StringBuilder();
            error(sb.append("unknown enity reference: '").append(name2).append("'").toString());
        }
        tokenBufferAppend(ch);
    }

    /* access modifiers changed from: package-private */
    public boolean match(String str, String str2, boolean z) throws IOException, SyntaxException {
        StringBuilder sb;
        String word1 = str;
        String word2 = str2;
        boolean force = z;
        if (match(word1)) {
            mark();
            int rawToken = getRawToken();
            if (match(word2)) {
                reset();
                int rawToken2 = getRawToken();
                return true;
            }
            reset();
            if (force) {
                new StringBuilder();
                error('e', sb.append("'").append(word1).append("' must be followed by '").append(word2).append("'").toString(), "XPST0003");
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int peekOperator() throws IOException, SyntaxException {
        while (this.curToken == 10) {
            if (this.nesting == 0) {
                return 10;
            }
            int rawToken = getRawToken();
        }
        if (this.curToken == 65) {
            switch (this.tokenBufferLength) {
                case 2:
                    char c1 = this.tokenBuffer[0];
                    char c2 = this.tokenBuffer[1];
                    if (c1 != 'o' || c2 != 'r') {
                        if (c1 != 't' || c2 != 'o') {
                            if (c1 != 'i' || c2 != 's') {
                                if (c1 != 'e' || c2 != 'q') {
                                    if (c1 != 'n' || c2 != 'e') {
                                        if (c1 != 'g') {
                                            if (c1 == 'l') {
                                                if (c2 != 'e') {
                                                    if (c2 == 't') {
                                                        this.curToken = OP_LT;
                                                        break;
                                                    }
                                                } else {
                                                    this.curToken = OP_LE;
                                                    break;
                                                }
                                            }
                                        } else if (c2 != 'e') {
                                            if (c2 == 't') {
                                                this.curToken = OP_GT;
                                                break;
                                            }
                                        } else {
                                            this.curToken = OP_GE;
                                            break;
                                        }
                                    } else {
                                        this.curToken = OP_NE;
                                        break;
                                    }
                                } else {
                                    this.curToken = OP_EQ;
                                    break;
                                }
                            } else {
                                this.curToken = 408;
                                break;
                            }
                        } else {
                            this.curToken = 412;
                            break;
                        }
                    } else {
                        this.curToken = HttpStatus.SC_BAD_REQUEST;
                        break;
                    }
                    break;
                case 3:
                    char c12 = this.tokenBuffer[0];
                    char c22 = this.tokenBuffer[1];
                    char c3 = this.tokenBuffer[2];
                    if (c12 != 'a') {
                        if (c12 != 'm') {
                            if (c12 == 'd' && c22 == 'i' && c3 == 'v') {
                                this.curToken = 416;
                                break;
                            }
                        } else {
                            if (c22 == 'u' && c3 == 'l') {
                                this.curToken = 415;
                            }
                            if (c22 == 'o' && c3 == 'd') {
                                this.curToken = 418;
                                break;
                            }
                        }
                    } else if (c22 == 'n' && c3 == 'd') {
                        this.curToken = 401;
                        break;
                    }
                case 4:
                    if (!match("idiv")) {
                        if (match("cast", "as", true)) {
                            this.curToken = OP_CAST_AS;
                            break;
                        }
                    } else {
                        this.curToken = 417;
                        break;
                    }
                    break;
                case 5:
                    if (!match("where")) {
                        if (!match("isnot")) {
                            if (!match("union")) {
                                if (match("treat", "as", true)) {
                                    this.curToken = 423;
                                    break;
                                }
                            } else {
                                this.curToken = 419;
                                break;
                            }
                        } else {
                            this.curToken = 409;
                            break;
                        }
                    } else {
                        this.curToken = 196;
                        break;
                    }
                    break;
                case 6:
                    if (match("except")) {
                        this.curToken = OP_EXCEPT;
                        break;
                    }
                    break;
                case 8:
                    if (!match("instance", "of", true)) {
                        if (match("castable", "as", true)) {
                            this.curToken = 424;
                            break;
                        }
                    } else {
                        this.curToken = 422;
                        break;
                    }
                    break;
                case 9:
                    if (match("intersect")) {
                        this.curToken = 420;
                        break;
                    }
                    break;
                case 10:
                    if (match("instanceof")) {
                        warnOldVersion("use 'instanceof of' (two words) instead of 'instanceof'");
                        this.curToken = 422;
                        break;
                    }
                    break;
            }
        }
        return this.curToken;
    }

    private boolean lookingAt(String word0, String str) throws IOException, SyntaxException {
        String word1 = str;
        if (!word0.equals(this.curValue)) {
            return false;
        }
        int i = 0;
        int len = word1.length();
        while (true) {
            int ch = read();
            if (i != len) {
                if (ch < 0) {
                    break;
                }
                int i2 = i;
                i++;
                if (ch != word1.charAt(i2)) {
                    break;
                }
            } else if (ch < 0) {
                return true;
            } else {
                if (!XName.isNamePart((char) ch)) {
                    unread();
                    return true;
                }
                i++;
            }
        }
        int skip = this.port.skip(-i);
        return false;
    }

    /* access modifiers changed from: package-private */
    public int getAxis() {
        String str;
        StringBuilder sb;
        new String(this.tokenBuffer, 0, this.tokenBufferLength);
        String name = str.intern();
        int i = 13;
        do {
            i--;
            if (i < 0 || axisNames[i] == name) {
            }
            i--;
            break;
        } while (axisNames[i] == name);
        if (i < 0 || i == 8) {
            new StringBuilder();
            error('e', sb.append("unknown axis name '").append(name).append('\'').toString(), "XPST0003");
            i = 3;
        }
        return (char) (100 + i);
    }

    /* access modifiers changed from: package-private */
    public int peekOperand() throws IOException, SyntaxException {
        String name;
        while (this.curToken == 10) {
            int rawToken = getRawToken();
        }
        if (this.curToken == 65 || this.curToken == 81) {
            int next = skipSpace(this.nesting != 0);
            switch (this.tokenBuffer[0]) {
                case 'a':
                    if (match("attribute")) {
                        if (next == 40) {
                            int i = OP_ATTRIBUTE;
                            int i2 = i;
                            this.curToken = i2;
                            return i;
                        } else if (next == 123 || XName.isNameStart((char) next)) {
                            unread();
                            this.curToken = 252;
                            return 252;
                        }
                    }
                    break;
                case 'c':
                    if (match(ClientCookie.COMMENT_ATTR)) {
                        if (next == 40) {
                            int i3 = OP_COMMENT;
                            int i4 = i3;
                            this.curToken = i4;
                            return i3;
                        } else if (next == 123) {
                            unread();
                            this.curToken = 254;
                            return 254;
                        }
                    }
                    break;
                case 'd':
                    if (next == 123 && match("document")) {
                        unread();
                        this.curToken = 256;
                        return 256;
                    } else if (next == 40 && match("document-node")) {
                        int i5 = OP_DOCUMENT;
                        int i6 = i5;
                        this.curToken = i6;
                        return i5;
                    }
                    break;
                case 'e':
                    if (match("element")) {
                        if (next == 40) {
                            int i7 = OP_ELEMENT;
                            int i8 = i7;
                            this.curToken = i8;
                            return i7;
                        } else if (next == 123 || XName.isNameStart((char) next)) {
                            unread();
                            this.curToken = 251;
                            return 251;
                        }
                    } else if (next == 40 && match("empty-sequence")) {
                        int i9 = OP_EMPTY_SEQUENCE;
                        int i10 = i9;
                        this.curToken = i10;
                        return i9;
                    } else if (next == 36 && match("every")) {
                        int i11 = EVERY_DOLLAR_TOKEN;
                        int i12 = i11;
                        this.curToken = i12;
                        return i11;
                    }
                    break;
                case 'f':
                    if (next == 36 && match("for")) {
                        int i13 = FOR_DOLLAR_TOKEN;
                        int i14 = i13;
                        this.curToken = i14;
                        return i13;
                    }
                case 'i':
                    if (next == 40 && match("if")) {
                        this.curToken = 241;
                        return 241;
                    } else if (next == 40 && match("item")) {
                        int i15 = OP_ITEM;
                        int i16 = i15;
                        this.curToken = i16;
                        return i15;
                    }
                    break;
                case 'l':
                    if (next == 36 && match("let")) {
                        int i17 = LET_DOLLAR_TOKEN;
                        int i18 = i17;
                        this.curToken = i18;
                        return i17;
                    }
                case 'n':
                    if (next == 40 && match("node")) {
                        int i19 = OP_NODE;
                        int i20 = i19;
                        this.curToken = i20;
                        return i19;
                    }
                case 'o':
                    if (next == 123 && match("ordered")) {
                        int i21 = ORDERED_LBRACE_TOKEN;
                        int i22 = i21;
                        this.curToken = i22;
                        return i21;
                    }
                case 'p':
                    if (match("processing-instruction")) {
                        if (next == 40) {
                            int i23 = OP_PI;
                            int i24 = i23;
                            this.curToken = i24;
                            return i23;
                        } else if (next == 123 || XName.isNameStart((char) next)) {
                            unread();
                            this.curToken = 255;
                            return 255;
                        }
                    }
                    break;
                case 's':
                    if (next == 36 && match("some")) {
                        int i25 = SOME_DOLLAR_TOKEN;
                        int i26 = i25;
                        this.curToken = i26;
                        return i25;
                    } else if (next == 40 && match("schema-attribute")) {
                        int i27 = OP_SCHEMA_ATTRIBUTE;
                        int i28 = i27;
                        this.curToken = i28;
                        return i27;
                    } else if (next == 40 && match("schema-element")) {
                        int i29 = OP_SCHEMA_ELEMENT;
                        int i30 = i29;
                        this.curToken = i30;
                        return i29;
                    }
                    break;
                case 't':
                    if (match(PropertyTypeConstants.PROPERTY_TYPE_TEXT)) {
                        if (next == 40) {
                            int i31 = OP_TEXT;
                            int i32 = i31;
                            this.curToken = i32;
                            return i31;
                        } else if (next == 123) {
                            unread();
                            this.curToken = 253;
                            return 253;
                        }
                    }
                    if (next == 40 && match("typeswitch")) {
                        this.curToken = 242;
                        return 242;
                    }
                case 'u':
                    if (next == 123 && match("unordered")) {
                        this.curToken = 250;
                        return 250;
                    }
                case 'v':
                    if (next == 123 && match("validate")) {
                        this.curToken = 248;
                        return 248;
                    }
            }
            if (next == 40 && peek() != 58) {
                this.curToken = 70;
                return 70;
            } else if (next == 58 && peek() == 58) {
                int axis = getAxis();
                int i33 = axis;
                this.curToken = i33;
                return axis;
            } else {
                new String(this.tokenBuffer, 0, this.tokenBufferLength);
                this.curValue = name;
                switch (next) {
                    case 98:
                        if (lookingAt("declare", "ase-uri")) {
                            this.curToken = 66;
                            return 66;
                        } else if (lookingAt("declare", "oundary-space")) {
                            this.curToken = 83;
                            return 83;
                        }
                        break;
                    case 99:
                        if (lookingAt("declare", "onstruction")) {
                            this.curToken = 75;
                            return 75;
                        } else if (lookingAt("declare", "opy-namespaces")) {
                            this.curToken = 76;
                            return 76;
                        }
                        break;
                    case 100:
                        if (lookingAt("declare", "efault")) {
                            int rawToken2 = getRawToken();
                            if (match("function")) {
                                this.curToken = 79;
                                return 79;
                            } else if (match("element")) {
                                this.curToken = 69;
                                return 69;
                            } else if (match("collation")) {
                                this.curToken = 71;
                                return 71;
                            } else if (match("order")) {
                                this.curToken = 72;
                                return 72;
                            } else {
                                error("unrecognized/unimplemented 'declare default'");
                                skipToSemicolon();
                                return peekOperand();
                            }
                        }
                        break;
                    case 101:
                        break;
                    case 102:
                        if (lookingAt("declare", "unction")) {
                            this.curToken = 80;
                            return 80;
                        } else if (lookingAt("define", "unction")) {
                            warnOldVersion("replace 'define function' by 'declare function'");
                            this.curToken = 80;
                            return 80;
                        } else if (lookingAt("default", "unction")) {
                            warnOldVersion("replace 'default function' by 'declare default function namespace'");
                            this.curToken = 79;
                            return 79;
                        }
                        break;
                    case 109:
                        if (lookingAt("import", "odule")) {
                            this.curToken = 73;
                            return 73;
                        }
                        break;
                    case 110:
                        if (lookingAt("declare", "amespace")) {
                            this.curToken = 78;
                            return 78;
                        } else if (lookingAt("default", "amespace")) {
                            warnOldVersion("replace 'default namespace' by 'declare default element namespace'");
                            this.curToken = 69;
                            return 69;
                        } else if (lookingAt("module", "amespace")) {
                            this.curToken = 77;
                            return 77;
                        }
                        break;
                    case 111:
                        if (lookingAt("declare", "rdering")) {
                            this.curToken = 85;
                            return 85;
                        } else if (lookingAt("declare", "ption")) {
                            this.curToken = 111;
                            return 111;
                        }
                        break;
                    case 115:
                        if (lookingAt("import", "chema")) {
                            this.curToken = 84;
                            return 84;
                        }
                        break;
                    case 118:
                        if (lookingAt("declare", "ariable")) {
                            this.curToken = 86;
                            return 86;
                        } else if (lookingAt("define", "ariable")) {
                            warnOldVersion("replace 'define variable' by 'declare variable'");
                            this.curToken = 86;
                            return 86;
                        } else if (lookingAt("xquery", "ersion")) {
                            this.curToken = 89;
                            return 89;
                        }
                        break;
                    case 120:
                        if (lookingAt("declare", "mlspace")) {
                            warnOldVersion("replace 'define xmlspace' by 'declare boundary-space'");
                            this.curToken = 83;
                            return 83;
                        }
                        break;
                }
                if (lookingAt("default", "lement")) {
                    warnOldVersion("replace 'default element' by 'declare default element namespace'");
                    this.curToken = 69;
                    return 69;
                }
                if (next >= 0) {
                    unread();
                    if (XName.isNameStart((char) next) && this.curValue.equals("define")) {
                        int rawToken3 = getRawToken();
                        this.curToken = 87;
                    }
                }
                return this.curToken;
            }
        } else {
            if (this.curToken == 67) {
                int next2 = read();
                if (next2 == 58) {
                    this.curToken = getAxis();
                } else {
                    unread(next2);
                }
            }
            return this.curToken;
        }
    }

    /* access modifiers changed from: package-private */
    public void checkAllowedNamespaceDeclaration(String str, String uri, boolean z) {
        String prefix = str;
        boolean inConstructor = z;
        boolean xmlPrefix = "xml".equals(prefix);
        if (NamespaceBinding.XML_NAMESPACE.equals(uri)) {
            if (!xmlPrefix || !inConstructor) {
                error('e', "namespace uri cannot be the same as the prefined xml namespace", "XQST0070");
            }
        } else if (xmlPrefix || "xmlns".equals(prefix)) {
            error('e', "namespace prefix cannot be 'xml' or 'xmlns'", "XQST0070");
        }
    }

    /* access modifiers changed from: package-private */
    public void pushNamespace(String str, String str2) {
        NamespaceBinding namespaceBinding;
        String prefix = str;
        String uri = str2;
        if (uri.length() == 0) {
            uri = null;
        }
        new NamespaceBinding(prefix, uri, this.prologNamespaces);
        this.prologNamespaces = namespaceBinding;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public XQParser(InPort port, SourceMessages messages, XQuery interp) {
        super(port, messages);
        this.interpreter = interp;
        this.nesting = 1;
        this.prologNamespaces = builtinNamespaces;
    }

    public void setInteractive(boolean z) {
        boolean v = z;
        if (this.interactive != v) {
            if (v) {
                this.nesting--;
            } else {
                this.nesting++;
            }
        }
        this.interactive = v;
    }

    private static final int priority(int opcode) {
        switch (opcode) {
            case HttpStatus.SC_BAD_REQUEST /*400*/:
                return 1;
            case 401:
                return 2;
            case 402:
            case 403:
            case 404:
            case 405:
            case 406:
            case 407:
            case 408:
            case 409:
            case 410:
            case 411:
            case OP_EQ /*426*/:
            case OP_NE /*427*/:
            case OP_LT /*428*/:
            case OP_LE /*429*/:
            case OP_GT /*430*/:
            case OP_GE /*431*/:
                return 3;
            case 412:
                return 4;
            case 413:
            case 414:
                return 5;
            case 415:
            case 416:
            case 417:
            case 418:
                return 6;
            case 419:
                return 7;
            case 420:
            case OP_EXCEPT /*421*/:
                return 8;
            case 422:
                return 9;
            case 423:
                return 10;
            case 424:
                return 11;
            case OP_CAST_AS /*425*/:
                return 12;
            default:
                return 0;
        }
    }

    static Expression makeBinary(Expression func, Expression exp1, Expression exp2) {
        Expression func2;
        new ApplyExp(func, exp1, exp2);
        return func2;
    }

    static Expression makeExprSequence(Expression exp1, Expression exp2) {
        return makeBinary(makeFunctionExp("gnu.kawa.functions.AppendValues", "appendValues"), exp1, exp2);
    }

    /* access modifiers changed from: package-private */
    public Expression makeBinary(int i, Expression expression, Expression expression2) throws IOException, SyntaxException {
        Expression func;
        StringBuilder sb;
        int op = i;
        Expression exp1 = expression;
        Expression exp2 = expression2;
        switch (op) {
            case 402:
                func = makeFunctionExp("gnu.xquery.util.Compare", "=");
                break;
            case 403:
                func = makeFunctionExp("gnu.xquery.util.Compare", "!=");
                break;
            case 404:
                func = makeFunctionExp("gnu.xquery.util.Compare", "<");
                break;
            case 405:
                func = makeFunctionExp("gnu.xquery.util.Compare", ">");
                break;
            case 406:
                func = makeFunctionExp("gnu.xquery.util.Compare", "<=");
                break;
            case 407:
                func = makeFunctionExp("gnu.xquery.util.Compare", ">=");
                break;
            case 408:
                func = makeFunctionExp("gnu.kawa.xml.NodeCompare", "$Eq", "is");
                break;
            case 409:
                func = makeFunctionExp("gnu.kawa.xml.NodeCompare", "$Ne", "isnot");
                break;
            case 410:
                func = makeFunctionExp("gnu.kawa.xml.NodeCompare", "$Gr", ">>");
                break;
            case 411:
                func = makeFunctionExp("gnu.kawa.xml.NodeCompare", "$Ls", "<<");
                break;
            case 412:
                func = makeFunctionExp("gnu.xquery.util.IntegerRange", "integerRange");
                break;
            case 413:
                func = makeFunctionExp("gnu.xquery.util.ArithOp", Component.DEFAULT_VALUE_FAB_ICON_NAME, "+");
                break;
            case 414:
                func = makeFunctionExp("gnu.xquery.util.ArithOp", "sub", "-");
                break;
            case 415:
                func = makeFunctionExp("gnu.xquery.util.ArithOp", "mul", "*");
                break;
            case 416:
                func = makeFunctionExp("gnu.xquery.util.ArithOp", "div", "div");
                break;
            case 417:
                func = makeFunctionExp("gnu.xquery.util.ArithOp", "idiv", "idiv");
                break;
            case 418:
                func = makeFunctionExp("gnu.xquery.util.ArithOp", "mod", "mod");
                break;
            case 419:
                func = makeFunctionExp("gnu.kawa.xml.UnionNodes", "unionNodes");
                break;
            case 420:
                func = makeFunctionExp("gnu.kawa.xml.IntersectNodes", "intersectNodes");
                break;
            case OP_EXCEPT /*421*/:
                func = makeFunctionExp("gnu.kawa.xml.IntersectNodes", "exceptNodes");
                break;
            case OP_EQ /*426*/:
                func = makeFunctionExp("gnu.xquery.util.Compare", "valEq", "eq");
                break;
            case OP_NE /*427*/:
                func = makeFunctionExp("gnu.xquery.util.Compare", "valNe", "ne");
                break;
            case OP_LT /*428*/:
                func = makeFunctionExp("gnu.xquery.util.Compare", "valLt", "lt");
                break;
            case OP_LE /*429*/:
                func = makeFunctionExp("gnu.xquery.util.Compare", "valLe", "le");
                break;
            case OP_GT /*430*/:
                func = makeFunctionExp("gnu.xquery.util.Compare", "valGt", "gt");
                break;
            case OP_GE /*431*/:
                func = makeFunctionExp("gnu.xquery.util.Compare", "valGe", "ge");
                break;
            default:
                new StringBuilder();
                return syntaxError(sb.append("unimplemented binary op: ").append(op).toString());
        }
        return makeBinary(func, exp1, exp2);
    }

    private void parseSimpleKindType() throws IOException, SyntaxException {
        int rawToken = getRawToken();
        if (this.curToken == 41) {
            int rawToken2 = getRawToken();
        } else {
            error("expected ')'");
        }
    }

    public Expression parseNamedNodeType(boolean z) throws IOException, SyntaxException {
        Expression qname;
        boolean attribute = z;
        Expression tname = null;
        int rawToken = getRawToken();
        if (this.curToken == 41) {
            qname = QuoteExp.getInstance(ElementType.MATCH_ANY_QNAME);
            int rawToken2 = getRawToken();
        } else {
            if (this.curToken == 81 || this.curToken == 65) {
                qname = parseNameTest(attribute);
            } else {
                if (this.curToken != 415) {
                    Expression syntaxError = syntaxError("expected QName or *");
                }
                qname = QuoteExp.getInstance(ElementType.MATCH_ANY_QNAME);
            }
            int rawToken3 = getRawToken();
            if (this.curToken == 44) {
                int rawToken4 = getRawToken();
                if (this.curToken == 81 || this.curToken == 65) {
                    tname = parseDataType();
                } else {
                    Expression syntaxError2 = syntaxError("expected QName");
                }
            }
            if (this.curToken == 41) {
                int rawToken5 = getRawToken();
            } else {
                error("expected ')' after element");
            }
        }
        return makeNamedNodeType(attribute, qname, tname);
    }

    static Expression makeNamedNodeType(boolean attribute, Expression expression, Expression expression2) {
        ApplyExp applyExp;
        Expression expression3;
        Expression qname = expression;
        Expression tname = expression2;
        Expression[] expressionArr = new Expression[2];
        new ApplyExp(ClassType.make(attribute ? "gnu.kawa.xml.AttributeType" : "gnu.kawa.xml.ElementType").getDeclaredMethod("make", 1), qname);
        ApplyExp elt = applyExp;
        elt.setFlag(4);
        if (tname == null) {
            return elt;
        }
        new BeginExp(tname, elt);
        return expression3;
    }

    private void warnOldStyleKindTest() {
        if (!this.warnedOldStyleKindTest) {
            error('w', "old-style KindTest - first one here");
            this.warnedOldStyleKindTest = true;
        }
    }

    public Expression parseOptionalTypeDeclaration() throws IOException, SyntaxException {
        if (!match("as")) {
            return null;
        }
        int rawToken = getRawToken();
        return parseDataType();
    }

    public Expression parseDataType() throws IOException, SyntaxException {
        int min;
        int max;
        ApplyExp applyExp;
        Expression etype = parseItemType();
        if (etype == null) {
            if (this.curToken != OP_EMPTY_SEQUENCE) {
                return syntaxError("bad syntax - expected DataType");
            }
            parseSimpleKindType();
            if (this.curToken == 63 || this.curToken == 413 || this.curToken == 415) {
                int rawToken = getRawToken();
                return syntaxError("occurrence-indicator meaningless after empty-sequence()");
            }
            etype = QuoteExp.getInstance(OccurrenceType.emptySequenceType);
            min = 0;
            max = 0;
        } else if (this.curToken == 63) {
            min = 0;
            max = 1;
        } else if (this.curToken == 413) {
            min = 1;
            max = -1;
        } else if (this.curToken == 415) {
            min = 0;
            max = -1;
        } else {
            min = 1;
            max = 1;
        }
        if (this.parseContext == 67 && max != 1) {
            return syntaxError("type to 'cast as' or 'castable as' must be a 'SingleType'");
        }
        if (min == max) {
            return etype;
        }
        int rawToken2 = getRawToken();
        Expression[] expressionArr = new Expression[3];
        expressionArr[0] = etype;
        Expression[] expressionArr2 = expressionArr;
        expressionArr2[1] = QuoteExp.getInstance(IntNum.make(min));
        Expression[] args = expressionArr2;
        args[2] = QuoteExp.getInstance(IntNum.make(max));
        new ApplyExp((Procedure) proc_OccurrenceType_getInstance, args);
        ApplyExp otype = applyExp;
        otype.setFlag(4);
        return otype;
    }

    public Expression parseMaybeKindTest() throws IOException, SyntaxException {
        Type type;
        String str;
        switch (this.curToken) {
            case OP_NODE /*230*/:
                parseSimpleKindType();
                type = NodeType.anyNodeTest;
                break;
            case OP_TEXT /*231*/:
                parseSimpleKindType();
                type = NodeType.textNodeTest;
                break;
            case OP_COMMENT /*232*/:
                parseSimpleKindType();
                type = NodeType.commentNodeTest;
                break;
            case OP_PI /*233*/:
                int rawToken = getRawToken();
                String piTarget = null;
                if (this.curToken == 65 || this.curToken == 34) {
                    new String(this.tokenBuffer, 0, this.tokenBufferLength);
                    piTarget = str;
                    int rawToken2 = getRawToken();
                }
                if (this.curToken == 41) {
                    int rawToken3 = getRawToken();
                } else {
                    error("expected ')'");
                }
                type = ProcessingInstructionType.getInstance(piTarget);
                break;
            case OP_DOCUMENT /*234*/:
                parseSimpleKindType();
                type = NodeType.documentNodeTest;
                break;
            case OP_ELEMENT /*235*/:
            case OP_ATTRIBUTE /*236*/:
                return parseNamedNodeType(this.curToken == OP_ATTRIBUTE);
            default:
                return null;
        }
        return QuoteExp.getInstance(type);
    }

    public Expression parseItemType() throws IOException, SyntaxException {
        String tname;
        ReferenceExp referenceExp;
        Type type;
        int peekOperand = peekOperand();
        Expression etype = parseMaybeKindTest();
        if (etype != null) {
            if (this.parseContext != 67) {
                return etype;
            }
            type = XDataType.anyAtomicType;
        } else if (this.curToken == OP_ITEM) {
            parseSimpleKindType();
            type = SingletonType.getInstance();
        } else if (this.curToken != 65 && this.curToken != 81) {
            return null;
        } else {
            new String(this.tokenBuffer, 0, this.tokenBufferLength);
            new ReferenceExp((Object) tname);
            ReferenceExp rexp = referenceExp;
            rexp.setFlag(16);
            maybeSetLine((Expression) rexp, this.curLine, this.curColumn);
            int rawToken = getRawToken();
            return rexp;
        }
        return QuoteExp.getInstance(type);
    }

    /* access modifiers changed from: package-private */
    public Object parseURILiteral() throws IOException, SyntaxException {
        String str;
        int rawToken = getRawToken();
        if (this.curToken != 34) {
            return declError("expected a URILiteral");
        }
        new String(this.tokenBuffer, 0, this.tokenBufferLength);
        return TextUtils.replaceWhitespace(str, true);
    }

    /* access modifiers changed from: package-private */
    public Expression parseExpr() throws IOException, SyntaxException {
        return parseExprSingle();
    }

    /* access modifiers changed from: package-private */
    public final Expression parseExprSingle() throws IOException, SyntaxException {
        int i = this.curLine;
        int i2 = this.curColumn;
        int peekOperand = peekOperand();
        switch (this.curToken) {
            case 241:
                return parseIfExpr();
            case 242:
                return parseTypeSwitch();
            case FOR_DOLLAR_TOKEN /*243*/:
                return parseFLWRExpression(true);
            case LET_DOLLAR_TOKEN /*244*/:
                return parseFLWRExpression(false);
            case SOME_DOLLAR_TOKEN /*245*/:
                return parseQuantifiedExpr(false);
            case EVERY_DOLLAR_TOKEN /*246*/:
                return parseQuantifiedExpr(true);
            default:
                return parseBinaryExpr(priority(HttpStatus.SC_BAD_REQUEST));
        }
    }

    /* access modifiers changed from: package-private */
    public Expression parseBinaryExpr(int i) throws IOException, SyntaxException {
        Expression exp;
        Expression expression;
        Expression expression2;
        Expression expression3;
        Expression makeFunctionExp;
        Expression expression4;
        Expression expression5;
        Expression expression6;
        int prio = i;
        Expression parseUnaryExpr = parseUnaryExpr();
        while (true) {
            exp = parseUnaryExpr;
            int token = peekOperator();
            if (token != 10 && (token != 404 || peek() != 47)) {
                int tokPriority = priority(token);
                if (tokPriority < prio) {
                    return exp;
                }
                char saveReadState = pushNesting('%');
                int rawToken = getRawToken();
                popNesting(saveReadState);
                if (token >= 422 && token <= OP_CAST_AS) {
                    if (token == OP_CAST_AS || token == 424) {
                        this.parseContext = 67;
                    }
                    Expression type = parseDataType();
                    this.parseContext = 0;
                    Expression[] args = new Expression[2];
                    switch (token) {
                        case 422:
                            args[0] = exp;
                            args[1] = type;
                            makeFunctionExp = makeFunctionExp("gnu.xquery.lang.XQParser", "instanceOf");
                            break;
                        case 423:
                            args[0] = type;
                            args[1] = exp;
                            makeFunctionExp = makeFunctionExp("gnu.xquery.lang.XQParser", "treatAs");
                            break;
                        case 424:
                            args[0] = exp;
                            args[1] = type;
                            makeFunctionExp = expression4;
                            new ReferenceExp(XQResolveNames.castableAsDecl);
                            break;
                        default:
                            args[0] = type;
                            args[1] = exp;
                            makeFunctionExp = expression6;
                            new ReferenceExp(XQResolveNames.castAsDecl);
                            break;
                    }
                    Expression func = makeFunctionExp;
                    parseUnaryExpr = expression5;
                    new ApplyExp(func, args);
                } else if (token == 422) {
                    Expression[] expressionArr = new Expression[2];
                    expressionArr[0] = exp;
                    Expression[] args2 = expressionArr;
                    args2[1] = parseDataType();
                    parseUnaryExpr = expression3;
                    new ApplyExp(makeFunctionExp("gnu.xquery.lang.XQParser", "instanceOf"), args2);
                } else {
                    Expression exp2 = parseBinaryExpr(tokPriority + 1);
                    if (token == 401) {
                        parseUnaryExpr = expression2;
                        new IfExp(booleanValue(exp), booleanValue(exp2), XQuery.falseExp);
                    } else if (token == 400) {
                        parseUnaryExpr = expression;
                        new IfExp(booleanValue(exp), XQuery.trueExp, booleanValue(exp2));
                    } else {
                        parseUnaryExpr = makeBinary(token, exp, exp2);
                    }
                }
            }
        }
        return exp;
    }

    /* access modifiers changed from: package-private */
    public Expression parseUnaryExpr() throws IOException, SyntaxException {
        Expression exp;
        Expression expression;
        if (this.curToken == 414 || this.curToken == 413) {
            int op = this.curToken;
            int rawToken = getRawToken();
            new ApplyExp(makeFunctionExp("gnu.xquery.util.ArithOp", op == 413 ? "plus" : "minus", op == 413 ? "+" : "-"), parseUnaryExpr());
            exp = expression;
        } else {
            exp = parseUnionExpr();
        }
        return exp;
    }

    /* access modifiers changed from: package-private */
    public Expression parseUnionExpr() throws IOException, SyntaxException {
        Expression parseIntersectExceptExpr = parseIntersectExceptExpr();
        while (true) {
            Expression exp = parseIntersectExceptExpr;
            int op = peekOperator();
            if (op != 419) {
                return exp;
            }
            int rawToken = getRawToken();
            parseIntersectExceptExpr = makeBinary(op, exp, parseIntersectExceptExpr());
        }
    }

    /* access modifiers changed from: package-private */
    public Expression parseIntersectExceptExpr() throws IOException, SyntaxException {
        Expression parsePathExpr = parsePathExpr();
        while (true) {
            Expression exp = parsePathExpr;
            int op = peekOperator();
            if (op != 420 && op != OP_EXCEPT) {
                return exp;
            }
            int rawToken = getRawToken();
            parsePathExpr = makeBinary(op, exp, parsePathExpr());
        }
    }

    /* access modifiers changed from: package-private */
    public Expression parsePathExpr() throws IOException, SyntaxException {
        Expression step1;
        Expression expression;
        Expression dot;
        Expression expression2;
        if (this.curToken == 47 || this.curToken == 68) {
            Declaration dotDecl = this.comp.lexical.lookup((Object) DOT_VARNAME, false);
            if (dotDecl == null) {
                dot = syntaxError("context item is undefined", "XPDY0002");
            } else {
                new ReferenceExp(DOT_VARNAME, dotDecl);
                dot = expression;
            }
            new ApplyExp(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("rootDocument", 1), dot);
            step1 = expression2;
            int next = skipSpace(this.nesting != 0);
            unread(next);
            if (next < 0 || next == 41 || next == 125) {
                int rawToken = getRawToken();
                return step1;
            }
        } else {
            step1 = parseStepExpr();
        }
        return parseRelativePathExpr(step1);
    }

    /* access modifiers changed from: package-private */
    public Expression parseNameTest(boolean z) throws IOException, SyntaxException {
        String str;
        String str2;
        Expression expression;
        String str3;
        Object obj;
        Expression expression2;
        Expression expression3;
        Expression expression4;
        ApplyExp applyExp;
        Object obj2;
        String str4;
        boolean attribute = z;
        String local = null;
        String prefix = null;
        if (this.curToken == 81) {
            int colon = this.tokenBufferLength;
            do {
                colon--;
            } while (this.tokenBuffer[colon] != ':');
            new String(this.tokenBuffer, 0, colon);
            prefix = obj2;
            int colon2 = colon + 1;
            new String(this.tokenBuffer, colon2, this.tokenBufferLength - colon2);
            local = str4;
        } else if (this.curToken == 415) {
            int next = read();
            String local2 = "";
            if (next != 58) {
                unread(next);
            } else {
                int next2 = read();
                if (next2 < 0) {
                    eofError("unexpected end-of-file after '*:'");
                }
                if (XName.isNameStart((char) next2)) {
                    unread();
                    int rawToken = getRawToken();
                    if (this.curToken != 65) {
                        Expression syntaxError = syntaxError("invalid name test");
                    } else {
                        new String(this.tokenBuffer, 0, this.tokenBufferLength);
                        local2 = str3.intern();
                    }
                } else if (next2 != 42) {
                    Expression syntaxError2 = syntaxError("missing local-name after '*:'");
                }
            }
            new Symbol((Namespace) null, local2);
            return QuoteExp.getInstance(obj);
        } else if (this.curToken == 65) {
            new String(this.tokenBuffer, 0, this.tokenBufferLength);
            local = str2;
            if (attribute) {
                new QuoteExp(Namespace.EmptyNamespace.getSymbol(local.intern()));
                return expression;
            }
            prefix = null;
        } else if (this.curToken == 67) {
            new String(this.tokenBuffer, 0, this.tokenBufferLength);
            prefix = str;
            if (read() != 42) {
                Expression syntaxError3 = syntaxError("invalid characters after 'NCName:'");
            }
            local = "";
        }
        if (prefix != null) {
            prefix = prefix.intern();
        }
        Expression[] args = new Expression[3];
        new ReferenceExp(XQResolveNames.resolvePrefixDecl);
        new ApplyExp(expression3, QuoteExp.getInstance(prefix));
        args[0] = expression2;
        Expression[] expressionArr = args;
        QuoteExp quoteExp = r15;
        QuoteExp quoteExp2 = new QuoteExp(local == null ? "" : local);
        expressionArr[1] = quoteExp;
        new QuoteExp(prefix);
        args[2] = expression4;
        new ApplyExp(Compilation.typeSymbol.getDeclaredMethod("make", 3), args);
        ApplyExp make = applyExp;
        make.setFlag(4);
        return make;
    }

    /* access modifiers changed from: package-private */
    public Expression parseNodeTest(int i) throws IOException, SyntaxException {
        StringBuilder sb;
        Expression expression;
        Expression dot;
        Expression makeAxisStep;
        ApplyExp applyExp;
        Expression expression2;
        String axisName;
        Object obj;
        StringBuilder sb2;
        Throwable th;
        int axis = i;
        int peekOperand = peekOperand();
        Expression[] args = new Expression[1];
        Expression etype = parseMaybeKindTest();
        if (etype != null) {
            args[0] = etype;
        } else if (this.curToken == 65 || this.curToken == 81 || this.curToken == 67 || this.curToken == 415) {
            args[0] = makeNamedNodeType(axis == 2, parseNameTest(axis == 2), (Expression) null);
        } else if (axis < 0) {
            return null;
        } else {
            new StringBuilder();
            return syntaxError(sb.append("unsupported axis '").append(axisNames[axis]).append("::'").toString());
        }
        Declaration dotDecl = this.comp.lexical.lookup((Object) DOT_VARNAME, false);
        if (dotDecl == null) {
            dot = syntaxError("node test when context item is undefined", "XPDY0002");
        } else {
            new ReferenceExp(DOT_VARNAME, dotDecl);
            dot = expression;
        }
        if (etype == null) {
            int rawToken = getRawToken();
        }
        if (axis == 3 || axis == -1) {
            makeAxisStep = makeChildAxisStep;
        } else if (axis == 4) {
            makeAxisStep = makeDescendantAxisStep;
        } else {
            switch (axis) {
                case 0:
                    axisName = "Ancestor";
                    break;
                case 1:
                    axisName = "AncestorOrSelf";
                    break;
                case 2:
                    axisName = "Attribute";
                    break;
                case 5:
                    axisName = "DescendantOrSelf";
                    break;
                case 6:
                    axisName = "Following";
                    break;
                case 7:
                    axisName = "FollowingSibling";
                    break;
                case 9:
                    axisName = "Parent";
                    break;
                case 10:
                    axisName = "Preceding";
                    break;
                case 11:
                    axisName = "PrecedingSibling";
                    break;
                case 12:
                    axisName = "Self";
                    break;
                default:
                    Throwable th2 = th;
                    new Error();
                    throw th2;
            }
            new StringBuilder();
            new PrimProcedure(sb2.append("gnu.kawa.xml.").append(axisName).append("Axis").toString(), "make", 1);
            makeAxisStep = QuoteExp.getInstance(obj);
        }
        new ApplyExp(makeAxisStep, args);
        ApplyExp mkAxis = applyExp;
        mkAxis.setFlag(4);
        Expression expression3 = expression2;
        new ApplyExp((Expression) mkAxis, dot);
        return expression3;
    }

    /* access modifiers changed from: package-private */
    public Expression parseRelativePathExpr(Expression expression) throws IOException, SyntaxException {
        LambdaExp lambdaExp;
        Expression expression2;
        Expression expression3;
        Expression dot;
        Expression expression4;
        Expression exp = expression;
        Expression beforeSlashSlash = null;
        while (true) {
            if (this.curToken != 47 && this.curToken != 68) {
                return exp;
            }
            boolean descendants = this.curToken == 68;
            new LambdaExp(3);
            LambdaExp lexp = lambdaExp;
            Declaration dotDecl = lexp.addDeclaration((Object) DOT_VARNAME);
            dotDecl.setFlag(PlaybackStateCompat.ACTION_SET_REPEAT_MODE);
            dotDecl.setType(NodeType.anyNodeTest);
            dotDecl.noteValue((Expression) null);
            Declaration addDeclaration = lexp.addDeclaration(POSITION_VARNAME, LangPrimType.intType);
            Declaration addDeclaration2 = lexp.addDeclaration(LAST_VARNAME, LangPrimType.intType);
            this.comp.push((ScopeExp) lexp);
            if (descendants) {
                this.curToken = 47;
                new ReferenceExp(DOT_VARNAME, dotDecl);
                new ApplyExp((Procedure) DescendantOrSelfAxis.anyNode, dot);
                lexp.body = expression4;
                expression2 = exp;
            } else {
                int rawToken = getRawToken();
                Expression exp2 = parseStepExpr();
                if (beforeSlashSlash != null && (exp2 instanceof ApplyExp)) {
                    Expression function = ((ApplyExp) exp2).getFunction();
                    Expression func = function;
                    if (function instanceof ApplyExp) {
                        ApplyExp applyExp = (ApplyExp) func;
                        ApplyExp aexp = applyExp;
                        if (applyExp.getFunction() == makeChildAxisStep) {
                            aexp.setFunction(makeDescendantAxisStep);
                            exp = beforeSlashSlash;
                        }
                    }
                }
                lexp.body = exp2;
                expression2 = null;
            }
            beforeSlashSlash = expression2;
            this.comp.pop(lexp);
            Expression[] expressionArr = new Expression[2];
            expressionArr[0] = exp;
            Expression[] args = expressionArr;
            args[1] = lexp;
            new ApplyExp((Procedure) RelativeStep.relativeStep, args);
            exp = expression3;
        }
    }

    /* access modifiers changed from: package-private */
    public Expression parseStepExpr() throws IOException, SyntaxException {
        Expression expression;
        Expression exp;
        int i;
        Expression expression2;
        Expression unqualifiedStep;
        if (this.curToken == 46 || this.curToken == 51) {
            int axis = this.curToken == 46 ? 12 : 9;
            int rawToken = getRawToken();
            Declaration dotDecl = this.comp.lexical.lookup((Object) DOT_VARNAME, false);
            if (dotDecl == null) {
                exp = syntaxError("context item is undefined", "XPDY0002");
            } else {
                new ReferenceExp(DOT_VARNAME, dotDecl);
                exp = expression;
            }
            if (axis == 9) {
                new ApplyExp((Procedure) ParentAxis.make(NodeType.anyNodeTest), exp);
                exp = expression2;
            }
            Expression expression3 = exp;
            if (axis == 12) {
                i = -1;
            } else {
                i = axis;
            }
            return parseStepQualifiers(expression3, i);
        }
        int axis2 = peekOperand() - 100;
        if (axis2 >= 0 && axis2 < 13) {
            int rawToken2 = getRawToken();
            unqualifiedStep = parseNodeTest(axis2);
        } else if (this.curToken == 64) {
            int rawToken3 = getRawToken();
            axis2 = 2;
            unqualifiedStep = parseNodeTest(2);
        } else if (this.curToken == OP_ATTRIBUTE) {
            axis2 = 2;
            unqualifiedStep = parseNodeTest(2);
        } else {
            unqualifiedStep = parseNodeTest(-1);
            if (unqualifiedStep != null) {
                axis2 = 3;
            } else {
                axis2 = -1;
                unqualifiedStep = parsePrimaryExpr();
            }
        }
        return parseStepQualifiers(unqualifiedStep, axis2);
    }

    /* access modifiers changed from: package-private */
    public Expression parseStepQualifiers(Expression expression, int i) throws IOException, SyntaxException {
        LambdaExp lambdaExp;
        Procedure procedure;
        Expression expression2;
        Expression exp = expression;
        int axis = i;
        while (this.curToken == 91) {
            int startLine = getLineNumber() + 1;
            int startColumn = getColumnNumber() + 1;
            int i2 = this.seenPosition;
            int i3 = this.seenLast;
            int rawToken = getRawToken();
            new LambdaExp(3);
            LambdaExp lexp = lambdaExp;
            maybeSetLine((Expression) lexp, startLine, startColumn);
            Declaration dot = lexp.addDeclaration((Object) DOT_VARNAME);
            if (axis >= 0) {
                dot.setType(NodeType.anyNodeTest);
            } else {
                dot.setType(SingletonType.getInstance());
            }
            Declaration addDeclaration = lexp.addDeclaration(POSITION_VARNAME, Type.intType);
            Declaration addDeclaration2 = lexp.addDeclaration(LAST_VARNAME, Type.intType);
            this.comp.push((ScopeExp) lexp);
            dot.noteValue((Expression) null);
            Expression cond = parseExprSequence(93, false);
            if (this.curToken == -1) {
                eofError("missing ']' - unexpected end-of-file");
            }
            if (axis < 0) {
                procedure = ValuesFilter.exprFilter;
            } else if (axis == 0 || axis == 1 || axis == 9 || axis == 10 || axis == 11) {
                procedure = ValuesFilter.reverseFilter;
            } else {
                procedure = ValuesFilter.forwardFilter;
            }
            Procedure valuesFilter = procedure;
            maybeSetLine(cond, startLine, startColumn);
            this.comp.pop(lexp);
            lexp.body = cond;
            int rawToken2 = getRawToken();
            Expression[] expressionArr = new Expression[2];
            expressionArr[0] = exp;
            Expression[] args = expressionArr;
            args[1] = lexp;
            new ApplyExp(valuesFilter, args);
            exp = expression2;
        }
        return exp;
    }

    /* access modifiers changed from: package-private */
    public Expression parsePrimaryExpr() throws IOException, SyntaxException {
        Expression exp = parseMaybePrimaryExpr();
        if (exp != null) {
            return exp;
        }
        Expression exp2 = syntaxError("missing expression");
        if (this.curToken != -1) {
            int rawToken = getRawToken();
        }
        return exp2;
    }

    /* access modifiers changed from: package-private */
    public void parseEntityOrCharRef() throws IOException, SyntaxException {
        String ref;
        int base;
        StringBuilder sb;
        int digit;
        int next = read();
        if (next == 35) {
            int next2 = read();
            if (next2 == 120) {
                base = 16;
                next2 = read();
            } else {
                base = 10;
            }
            int value = 0;
            while (next2 >= 0 && (digit = Character.digit((char) next2, base)) >= 0 && value < 134217728) {
                value = (value * base) + digit;
                next2 = read();
            }
            if (next2 != 59) {
                unread();
                error("invalid character reference");
            } else if ((value <= 0 || value > 55295) && ((value < 57344 || value > 65533) && (value < 65536 || value > 1114111))) {
                new StringBuilder();
                error('e', sb.append("invalid character value ").append(value).toString(), "XQST0090");
            } else {
                tokenBufferAppend(value);
            }
        } else {
            int saveLength = this.tokenBufferLength;
            while (next >= 0) {
                char ch = (char) next;
                if (!XName.isNamePart(ch)) {
                    break;
                }
                tokenBufferAppend(ch);
                next = read();
            }
            if (next != 59) {
                unread();
                error("invalid entity reference");
                return;
            }
            new String(this.tokenBuffer, saveLength, this.tokenBufferLength - saveLength);
            this.tokenBufferLength = saveLength;
            appendNamedEntity(ref);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x00ad A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01d7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parseContent(char r23, java.util.Vector r24) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r22 = this;
            r1 = r22
            r2 = r23
            r3 = r24
            r15 = r1
            r16 = 0
            r0 = r16
            r15.tokenBufferLength = r0
            r15 = r3
            int r15 = r15.size()
            r4 = r15
            r15 = r4
            r16 = 1
            int r15 = r15 + -1
            r5 = r15
            r15 = r1
            boolean r15 = r15.boundarySpacePreserve
            if (r15 != 0) goto L_0x00ae
            r15 = r2
            r16 = 60
            r0 = r16
            if (r15 != r0) goto L_0x00ae
            r15 = 1
        L_0x0026:
            r6 = r15
            r15 = r6
            r7 = r15
        L_0x0029:
            r15 = r1
            int r15 = r15.read()
            r8 = r15
            r15 = r8
            r16 = r2
            r0 = r16
            if (r15 != r0) goto L_0x012b
            r15 = r2
            r16 = 60
            r0 = r16
            if (r15 != r0) goto L_0x011a
            r15 = r1
            int r15 = r15.read()
            r8 = r15
            r15 = 0
            r9 = r15
            r15 = r1
            int r15 = r15.tokenBufferLength
            if (r15 <= 0) goto L_0x0093
            java.lang.String r15 = new java.lang.String
            r21 = r15
            r15 = r21
            r16 = r21
            r17 = r1
            r0 = r17
            char[] r0 = r0.tokenBuffer
            r17 = r0
            r18 = 0
            r19 = r1
            r0 = r19
            int r0 = r0.tokenBufferLength
            r19 = r0
            r16.<init>(r17, r18, r19)
            r10 = r15
            r15 = 1
            gnu.expr.Expression[] r15 = new gnu.expr.Expression[r15]
            r21 = r15
            r15 = r21
            r16 = r21
            r17 = 0
            gnu.expr.QuoteExp r18 = new gnu.expr.QuoteExp
            r21 = r18
            r18 = r21
            r19 = r21
            r20 = r10
            r19.<init>(r20)
            r16[r17] = r18
            r11 = r15
            gnu.expr.ApplyExp r15 = new gnu.expr.ApplyExp
            r21 = r15
            r15 = r21
            r16 = r21
            gnu.expr.Expression r17 = makeText
            r18 = r11
            r16.<init>((gnu.expr.Expression) r17, (gnu.expr.Expression[]) r18)
            r9 = r15
        L_0x0093:
            r15 = r1
            r16 = 0
            r0 = r16
            r15.tokenBufferLength = r0
            r15 = r8
            r16 = 47
            r0 = r16
            if (r15 != r0) goto L_0x00b1
            r15 = r9
            if (r15 == 0) goto L_0x00ad
            r15 = r7
            if (r15 != 0) goto L_0x00ad
            r15 = r3
            r16 = r9
            r15.addElement(r16)
        L_0x00ad:
            return
        L_0x00ae:
            r15 = 0
            goto L_0x0026
        L_0x00b1:
            r15 = r1
            r16 = r8
            r17 = 1
            gnu.expr.Expression r15 = r15.parseXMLConstructor(r16, r17)
            r10 = r15
            r15 = 0
            r11 = r15
            r15 = 0
            r12 = r15
            r15 = r10
            boolean r15 = r15 instanceof gnu.expr.ApplyExp
            if (r15 == 0) goto L_0x00ef
            r15 = r10
            gnu.expr.ApplyExp r15 = (gnu.expr.ApplyExp) r15
            r13 = r15
            r15 = r13
            gnu.expr.Expression r15 = r15.getFunction()
            gnu.expr.Expression r16 = makeCDATA
            r0 = r16
            if (r15 != r0) goto L_0x00ef
            r15 = 1
            r11 = r15
            r15 = r13
            r16 = 0
            gnu.expr.Expression r15 = r15.getArg(r16)
            java.lang.Object r15 = r15.valueIfConstant()
            java.lang.String r15 = (java.lang.String) r15
            r14 = r15
            r15 = r14
            if (r15 == 0) goto L_0x0115
            r15 = r14
            int r15 = r15.length()
            if (r15 != 0) goto L_0x0115
            r15 = 1
        L_0x00ee:
            r12 = r15
        L_0x00ef:
            r15 = r9
            if (r15 == 0) goto L_0x00fe
            r15 = r7
            if (r15 == 0) goto L_0x00f8
            r15 = r11
            if (r15 == 0) goto L_0x00fe
        L_0x00f8:
            r15 = r3
            r16 = r9
            r15.addElement(r16)
        L_0x00fe:
            r15 = r11
            if (r15 == 0) goto L_0x0117
            r15 = 0
            r7 = r15
        L_0x0103:
            r15 = r12
            if (r15 != 0) goto L_0x010c
            r15 = r3
            r16 = r10
            r15.addElement(r16)
        L_0x010c:
            r15 = r1
            r16 = 0
            r0 = r16
            r15.tokenBufferLength = r0
            goto L_0x0029
        L_0x0115:
            r15 = 0
            goto L_0x00ee
        L_0x0117:
            r15 = r6
            r7 = r15
            goto L_0x0103
        L_0x011a:
            r15 = r1
            r16 = r2
            boolean r15 = r15.checkNext(r16)
            if (r15 == 0) goto L_0x012b
            r15 = r1
            r16 = r2
            r15.tokenBufferAppend(r16)
            goto L_0x0029
        L_0x012b:
            r15 = r8
            r16 = r2
            r0 = r16
            if (r15 == r0) goto L_0x013c
            r15 = r8
            if (r15 < 0) goto L_0x013c
            r15 = r8
            r16 = 123(0x7b, float:1.72E-43)
            r0 = r16
            if (r15 != r0) goto L_0x021a
        L_0x013c:
            r15 = r8
            r16 = 123(0x7b, float:1.72E-43)
            r0 = r16
            if (r15 != r0) goto L_0x015a
            r15 = r1
            int r15 = r15.read()
        L_0x0148:
            r9 = r15
            r15 = r9
            r16 = 123(0x7b, float:1.72E-43)
            r0 = r16
            if (r15 != r0) goto L_0x015c
            r15 = r1
            r16 = 123(0x7b, float:1.72E-43)
            r15.tokenBufferAppend(r16)
            r15 = 0
            r7 = r15
            goto L_0x0029
        L_0x015a:
            r15 = -1
            goto L_0x0148
        L_0x015c:
            r15 = r1
            int r15 = r15.tokenBufferLength
            if (r15 <= 0) goto L_0x01c0
            r15 = r7
            if (r15 != 0) goto L_0x01c0
            java.lang.String r15 = new java.lang.String
            r21 = r15
            r15 = r21
            r16 = r21
            r17 = r1
            r0 = r17
            char[] r0 = r0.tokenBuffer
            r17 = r0
            r18 = 0
            r19 = r1
            r0 = r19
            int r0 = r0.tokenBufferLength
            r19 = r0
            r16.<init>(r17, r18, r19)
            r10 = r15
        L_0x0182:
            r15 = 1
            gnu.expr.Expression[] r15 = new gnu.expr.Expression[r15]
            r21 = r15
            r15 = r21
            r16 = r21
            r17 = 0
            gnu.expr.QuoteExp r18 = new gnu.expr.QuoteExp
            r21 = r18
            r18 = r21
            r19 = r21
            r20 = r10
            r19.<init>(r20)
            r16[r17] = r18
            r11 = r15
            r15 = r3
            gnu.expr.ApplyExp r16 = new gnu.expr.ApplyExp
            r21 = r16
            r16 = r21
            r17 = r21
            gnu.expr.Expression r18 = makeText
            r19 = r11
            r17.<init>((gnu.expr.Expression) r18, (gnu.expr.Expression[]) r19)
            r15.addElement(r16)
        L_0x01b0:
            r15 = r1
            r16 = 0
            r0 = r16
            r15.tokenBufferLength = r0
            r15 = r8
            r16 = r2
            r0 = r16
            if (r15 != r0) goto L_0x01d7
            goto L_0x00ad
        L_0x01c0:
            r15 = r8
            r16 = 123(0x7b, float:1.72E-43)
            r0 = r16
            if (r15 != r0) goto L_0x01b0
            r15 = r5
            r16 = r3
            int r16 = r16.size()
            r0 = r16
            if (r15 != r0) goto L_0x01b0
            java.lang.String r15 = ""
            r10 = r15
            goto L_0x0182
        L_0x01d7:
            r15 = r8
            if (r15 >= 0) goto L_0x01e3
            r15 = r1
            java.lang.String r16 = "unexpected end-of-file"
            r15.eofError(r16)
        L_0x01e1:
            goto L_0x0029
        L_0x01e3:
            r15 = r1
            r16 = r9
            r15.unread(r16)
            r15 = r1
            r21 = r15
            r15 = r21
            r16 = r21
            r0 = r16
            int r0 = r0.enclosedExpressionsSeen
            r16 = r0
            r17 = 1
            int r16 = r16 + 1
            r0 = r16
            r15.enclosedExpressionsSeen = r0
            r15 = r1
            gnu.expr.Expression r15 = r15.parseEnclosedExpr()
            r10 = r15
            r15 = r3
            r16 = r10
            r15.addElement(r16)
            r15 = r1
            r16 = 0
            r0 = r16
            r15.tokenBufferLength = r0
            r15 = r3
            int r15 = r15.size()
            r5 = r15
            r15 = r6
            r7 = r15
            goto L_0x01e1
        L_0x021a:
            r15 = r8
            r16 = 125(0x7d, float:1.75E-43)
            r0 = r16
            if (r15 != r0) goto L_0x0245
            r15 = r1
            int r15 = r15.read()
            r8 = r15
            r15 = r8
            r16 = 125(0x7d, float:1.75E-43)
            r0 = r16
            if (r15 != r0) goto L_0x0237
            r15 = r1
            r16 = 125(0x7d, float:1.75E-43)
            r15.tokenBufferAppend(r16)
            r15 = 0
            r7 = r15
            goto L_0x01e1
        L_0x0237:
            r15 = r1
            java.lang.String r16 = "unexpected '}' in element content"
            r15.error(r16)
            r15 = r1
            r16 = r8
            r15.unread(r16)
            goto L_0x01e1
        L_0x0245:
            r15 = r8
            r16 = 38
            r0 = r16
            if (r15 != r0) goto L_0x0253
            r15 = r1
            r15.parseEntityOrCharRef()
            r15 = 0
            r7 = r15
            goto L_0x01e1
        L_0x0253:
            r15 = r2
            r16 = 60
            r0 = r16
            if (r15 == r0) goto L_0x0272
            r15 = r8
            r16 = 9
            r0 = r16
            if (r15 == r0) goto L_0x026f
            r15 = r8
            r16 = 10
            r0 = r16
            if (r15 == r0) goto L_0x026f
            r15 = r8
            r16 = 13
            r0 = r16
            if (r15 != r0) goto L_0x0272
        L_0x026f:
            r15 = 32
            r8 = r15
        L_0x0272:
            r15 = r8
            r16 = 60
            r0 = r16
            if (r15 != r0) goto L_0x0282
            r15 = r1
            r16 = 101(0x65, float:1.42E-43)
            java.lang.String r17 = "'<' must be quoted in a direct attribute value"
            r15.error(r16, r17)
        L_0x0282:
            r15 = r7
            if (r15 == 0) goto L_0x028c
            r15 = r8
            char r15 = (char) r15
            boolean r15 = java.lang.Character.isWhitespace(r15)
            r7 = r15
        L_0x028c:
            r15 = r1
            r16 = r8
            r0 = r16
            char r0 = (char) r0
            r16 = r0
            r15.tokenBufferAppend(r16)
            goto L_0x01e1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQParser.parseContent(char, java.util.Vector):void");
    }

    /* access modifiers changed from: package-private */
    public Expression parseEnclosedExpr() throws IOException, SyntaxException {
        Expression exp;
        String saveErrorIfComment = this.errorIfComment;
        this.errorIfComment = null;
        char saveReadState = pushNesting('{');
        int peekNonSpace = peekNonSpace("unexpected end-of-file after '{'");
        int startLine = getLineNumber() + 1;
        int startColumn = getColumnNumber() + 1;
        int rawToken = getRawToken();
        Expression parseExpr = parseExpr();
        while (true) {
            exp = parseExpr;
            if (this.curToken == 125) {
                break;
            } else if (this.curToken == -1 || this.curToken == 41 || this.curToken == 93) {
                exp = syntaxError("missing '}'");
            } else {
                if (this.curToken != 44) {
                    exp = syntaxError("missing '}' or ','");
                } else {
                    int rawToken2 = getRawToken();
                }
                parseExpr = makeExprSequence(exp, parseExpr());
            }
        }
        maybeSetLine(exp, startLine, startColumn);
        popNesting(saveReadState);
        this.errorIfComment = saveErrorIfComment;
        return exp;
    }

    public static Expression booleanValue(Expression exp) {
        Expression exp2;
        Expression string = makeFunctionExp("gnu.xquery.util.BooleanValue", "booleanValue");
        new ApplyExp(string, exp);
        return exp2;
    }

    /* access modifiers changed from: package-private */
    public Expression parseXMLConstructor(int i, boolean z) throws IOException, SyntaxException {
        Expression exp;
        String str;
        String str2;
        Expression expression;
        Expression expression2;
        Expression expression3;
        Expression expression4;
        Object obj;
        Expression expression5;
        Expression expression6;
        Object obj2;
        Expression expression7;
        int next = i;
        boolean inElementContent = z;
        if (next == 33) {
            int next2 = read();
            if (next2 == 45 && peek() == 45) {
                skip();
                getDelimited("-->");
                boolean bad = false;
                int i2 = this.tokenBufferLength;
                boolean z2 = true;
                while (true) {
                    boolean sawHyphen = z2;
                    i2--;
                    if (i2 >= 0) {
                        boolean curHyphen = this.tokenBuffer[i2] == '-';
                        if (sawHyphen && curHyphen) {
                            bad = true;
                            break;
                        }
                        z2 = curHyphen;
                    } else {
                        break;
                    }
                }
                if (bad) {
                    exp = syntaxError("consecutive or final hyphen in XML comment");
                } else {
                    new String(this.tokenBuffer, 0, this.tokenBufferLength);
                    new QuoteExp(obj2);
                    new ApplyExp(makeFunctionExp("gnu.kawa.xml.CommentConstructor", "commentConstructor"), expression6);
                    exp = expression7;
                }
            } else if (next2 == 91 && read() == 67 && read() == 68 && read() == 65 && read() == 84 && read() == 65 && read() == 91) {
                if (!inElementContent) {
                    error('e', "CDATA section must be in element content");
                }
                getDelimited("]]>");
                new String(this.tokenBuffer, 0, this.tokenBufferLength);
                new QuoteExp(obj);
                new ApplyExp(makeCDATA, expression4);
                exp = expression5;
            } else {
                exp = syntaxError("'<!' must be followed by '--' or '[CDATA['");
            }
        } else if (next == 63) {
            int next3 = peek();
            if (next3 < 0 || !XName.isNameStart((char) next3) || getRawToken() != 65) {
                Expression syntaxError = syntaxError("missing target after '<?'");
            }
            new String(this.tokenBuffer, 0, this.tokenBufferLength);
            String target = str;
            int nspaces = 0;
            while (true) {
                int ch = read();
                if (ch < 0) {
                    break;
                } else if (!Character.isWhitespace((char) ch)) {
                    unread();
                    break;
                } else {
                    nspaces++;
                }
            }
            getDelimited("?>");
            if (nspaces == 0 && this.tokenBufferLength > 0) {
                Expression syntaxError2 = syntaxError("target must be followed by space or '?>'");
            }
            new String(this.tokenBuffer, 0, this.tokenBufferLength);
            String content = str2;
            Expression[] expressionArr = new Expression[2];
            new QuoteExp(target);
            expressionArr[0] = expression;
            Expression[] args = expressionArr;
            new QuoteExp(content);
            args[1] = expression2;
            new ApplyExp(makeFunctionExp("gnu.kawa.xml.MakeProcInst", "makeProcInst"), args);
            exp = expression3;
        } else if (next < 0 || !XName.isNameStart((char) next)) {
            exp = syntaxError("expected QName after '<'");
        } else {
            unread(next);
            int rawToken = getRawToken();
            char saveReadState = pushNesting('<');
            exp = parseElementConstructor();
            if (!inElementContent) {
                exp = wrapWithBaseUri(exp);
            }
            popNesting(saveReadState);
        }
        return exp;
    }

    static ApplyExp castQName(Expression expression, boolean element) {
        ApplyExp applyExp;
        Expression expression2;
        Expression value = expression;
        ApplyExp applyExp2 = applyExp;
        new ReferenceExp(element ? XQResolveNames.xsQNameDecl : XQResolveNames.xsQNameIgnoreDefaultDecl);
        new ApplyExp(expression2, value);
        return applyExp2;
    }

    /* access modifiers changed from: package-private */
    public Expression parseElementConstructor() throws IOException, SyntaxException {
        String str;
        Vector vector;
        Expression expression;
        int ch;
        MakeElement makeElement;
        Expression result;
        Expression expression2;
        String str2;
        StringBuilder sb;
        String str3;
        Expression expression3;
        ApplyExp applyExp;
        String str4;
        StringBuilder sb2;
        String sb3;
        new String(this.tokenBuffer, 0, this.tokenBufferLength);
        String startTag = str;
        new Vector();
        Vector vec = vector;
        new QuoteExp(startTag);
        vec.addElement(castQName(expression, true));
        this.errorIfComment = "comment not allowed in element start tag";
        NamespaceBinding nsBindings = null;
        while (true) {
            boolean sawSpace = false;
            ch = read();
            while (ch >= 0 && Character.isWhitespace((char) ch)) {
                ch = read();
                sawSpace = true;
            }
            if (ch < 0 || ch == 62 || ch == 47) {
                break;
            }
            if (!sawSpace) {
                Expression syntaxError = syntaxError("missing space before attribute");
            }
            unread(ch);
            int rawToken = getRawToken();
            int vecSize = vec.size();
            if (this.curToken != 65) {
                if (this.curToken != 81) {
                    break;
                }
            }
            new String(this.tokenBuffer, 0, this.tokenBufferLength);
            String attrName = str3;
            int startLine = getLineNumber() + 1;
            int startColumn = (getColumnNumber() + 1) - this.tokenBufferLength;
            String definingNamespace = null;
            if (this.curToken == 65) {
                if (attrName.equals("xmlns")) {
                    definingNamespace = "";
                }
            } else if (attrName.startsWith("xmlns:")) {
                definingNamespace = attrName.substring(6).intern();
            }
            Expression makeAttr = definingNamespace != null ? null : MakeAttribute.makeAttributeExp;
            new QuoteExp(attrName);
            vec.addElement(castQName(expression3, false));
            if (skipSpace() != 61) {
                this.errorIfComment = null;
                return syntaxError("missing '=' after attribute");
            }
            int ch2 = skipSpace();
            int enclosedExpressionsStart = this.enclosedExpressionsSeen;
            if (ch2 == 123) {
                warnOldVersion("enclosed attribute value expression should be quoted");
                vec.addElement(parseEnclosedExpr());
            } else {
                parseContent((char) ch2, vec);
            }
            int n = vec.size() - vecSize;
            if (definingNamespace != null) {
                String ns = "";
                if (n == 1) {
                    ns = "";
                } else {
                    if (this.enclosedExpressionsSeen > enclosedExpressionsStart) {
                        Expression syntaxError2 = syntaxError("enclosed expression not allowed in namespace declaration");
                    } else {
                        Object x = vec.elementAt(vecSize + 1);
                        if (x instanceof ApplyExp) {
                            ApplyExp applyExp2 = (ApplyExp) x;
                            ApplyExp ax = applyExp2;
                            if (applyExp2.getFunction() == makeText) {
                                x = ax.getArg(0);
                            }
                        }
                        ns = ((QuoteExp) x).getValue().toString().intern();
                    }
                }
                vec.setSize(vecSize);
                checkAllowedNamespaceDeclaration(definingNamespace, ns, true);
                if (definingNamespace == "") {
                    definingNamespace = null;
                }
                NamespaceBinding namespaceBinding = nsBindings;
                while (true) {
                    NamespaceBinding nsb = namespaceBinding;
                    if (nsb == null) {
                        break;
                    } else if (nsb.getPrefix() == definingNamespace) {
                        if (definingNamespace == null) {
                            sb3 = "duplicate default namespace declaration";
                        } else {
                            new StringBuilder();
                            sb3 = sb2.append("duplicate namespace prefix '").append(definingNamespace).append('\'').toString();
                        }
                        error('e', sb3, "XQST0071");
                    } else {
                        namespaceBinding = nsb.getNext();
                    }
                }
                NamespaceBinding namespaceBinding2 = r25;
                String str5 = definingNamespace;
                if (ns == "") {
                    str4 = null;
                } else {
                    str4 = ns;
                }
                NamespaceBinding namespaceBinding3 = new NamespaceBinding(str5, str4, nsBindings);
                nsBindings = namespaceBinding2;
            } else {
                Expression[] args = new Expression[n];
                int i = n;
                while (true) {
                    i--;
                    if (i < 0) {
                        break;
                    }
                    args[i] = (Expression) vec.elementAt(vecSize + i);
                }
                vec.setSize(vecSize);
                new ApplyExp(makeAttr, args);
                ApplyExp aexp = applyExp;
                maybeSetLine((Expression) aexp, startLine, startColumn);
                vec.addElement(aexp);
            }
        }
        this.errorIfComment = null;
        boolean empty = false;
        if (ch == 47) {
            ch = read();
            if (ch == 62) {
                empty = true;
            } else {
                unread(ch);
            }
        }
        if (!empty) {
            if (ch != 62) {
                return syntaxError("missing '>' after start element");
            }
            parseContent('<', vec);
            int ch3 = peek();
            if (ch3 >= 0) {
                if (!XName.isNameStart((char) ch3)) {
                    return syntaxError("invalid tag syntax after '</'");
                }
                int rawToken2 = getRawToken();
                new String(this.tokenBuffer, 0, this.tokenBufferLength);
                String tag = str2;
                if (!tag.equals(startTag)) {
                    new StringBuilder();
                    return syntaxError(sb.append("'<").append(startTag).append(">' closed by '</").append(tag).append(">'").toString());
                }
                this.errorIfComment = "comment not allowed in element end tag";
                ch3 = skipSpace();
                this.errorIfComment = null;
            }
            if (ch3 != 62) {
                return syntaxError("missing '>' after end element");
            }
        }
        Expression[] args2 = new Expression[vec.size()];
        vec.copyInto(args2);
        new MakeElement();
        MakeElement mkElement = makeElement;
        mkElement.copyNamespacesMode = this.copyNamespacesMode;
        mkElement.setNamespaceNodes(nsBindings);
        new QuoteExp(mkElement);
        new ApplyExp(expression2, args2);
        return result;
    }

    /* access modifiers changed from: package-private */
    public Expression wrapWithBaseUri(Expression expression) {
        ApplyExp applyExp;
        Expression expression2;
        Expression expression3;
        Expression exp = expression;
        if (getStaticBaseUri() == null) {
            return exp;
        }
        ApplyExp applyExp2 = applyExp;
        MakeWithBaseUri makeWithBaseUri = MakeWithBaseUri.makeWithBaseUri;
        Expression[] expressionArr = new Expression[2];
        new ReferenceExp(XQResolveNames.staticBaseUriDecl);
        new ApplyExp(expression3, Expression.noExpressions);
        expressionArr[0] = expression2;
        Expression[] expressionArr2 = expressionArr;
        expressionArr2[1] = exp;
        new ApplyExp((Procedure) makeWithBaseUri, expressionArr2);
        return applyExp2.setLine(exp);
    }

    /* access modifiers changed from: package-private */
    public Expression parseParenExpr() throws IOException, SyntaxException {
        int rawToken = getRawToken();
        char saveReadState = pushNesting('(');
        Expression exp = parseExprSequence(41, true);
        popNesting(saveReadState);
        if (this.curToken == -1) {
            eofError("missing ')' - unexpected end-of-file");
        }
        return exp;
    }

    /* access modifiers changed from: package-private */
    public Expression parseExprSequence(int i, boolean z) throws IOException, SyntaxException {
        int rightToken = i;
        boolean optional = z;
        if (this.curToken == rightToken || this.curToken == -1) {
            if (!optional) {
                Expression syntaxError = syntaxError("missing expression");
            }
            return QuoteExp.voidExp;
        }
        Expression exp = null;
        while (true) {
            Expression exp1 = parseExprSingle();
            exp = exp == null ? exp1 : makeExprSequence(exp, exp1);
            if (this.curToken != rightToken && this.curToken != -1) {
                if (this.nesting == 0 && this.curToken == 10) {
                    return exp;
                }
                if (this.curToken != 44) {
                    return syntaxError(rightToken == 41 ? "expected ')'" : "confused by syntax error");
                }
                int rawToken = getRawToken();
            }
        }
        return exp;
    }

    /* access modifiers changed from: package-private */
    public Expression parseTypeSwitch() throws IOException, SyntaxException {
        Vector vector;
        Expression expression;
        LambdaExp lambdaExp;
        Declaration declaration;
        Declaration decl;
        Declaration declaration2;
        Declaration decl2;
        LambdaExp lambdaExp2;
        char save = pushNesting('t');
        Expression selector = parseParenExpr();
        int rawToken = getRawToken();
        new Vector();
        Vector vec = vector;
        vec.addElement(selector);
        while (match("case")) {
            char pushNesting = pushNesting('c');
            int rawToken2 = getRawToken();
            if (this.curToken == 36) {
                decl2 = parseVariableDeclaration();
                if (decl2 == null) {
                    return syntaxError("missing Variable after '$'");
                }
                int rawToken3 = getRawToken();
                if (match("as")) {
                    int rawToken4 = getRawToken();
                } else {
                    error('e', "missing 'as'");
                }
            } else {
                new Declaration((Object) "(arg)");
                decl2 = declaration2;
            }
            decl2.setTypeExp(parseDataType());
            popNesting('t');
            new LambdaExp(1);
            LambdaExp lexp = lambdaExp2;
            lexp.addDeclaration(decl2);
            if (match("return")) {
                int rawToken5 = getRawToken();
            } else {
                error("missing 'return' after 'case'");
            }
            this.comp.push((ScopeExp) lexp);
            char pushNesting2 = pushNesting('r');
            lexp.body = parseExpr();
            popNesting('t');
            this.comp.pop(lexp);
            vec.addElement(lexp);
        }
        if (match("default")) {
            new LambdaExp(1);
            LambdaExp lexp2 = lambdaExp;
            int rawToken6 = getRawToken();
            if (this.curToken == 36) {
                decl = parseVariableDeclaration();
                if (decl == null) {
                    return syntaxError("missing Variable after '$'");
                }
                int rawToken7 = getRawToken();
            } else {
                new Declaration((Object) "(arg)");
                decl = declaration;
            }
            lexp2.addDeclaration(decl);
            if (match("return")) {
                int rawToken8 = getRawToken();
            } else {
                error("missing 'return' after 'default'");
            }
            this.comp.push((ScopeExp) lexp2);
            lexp2.body = parseExpr();
            this.comp.pop(lexp2);
            vec.addElement(lexp2);
        } else {
            error(this.comp.isPedantic() ? 'e' : 'w', "no 'default' clause in 'typeswitch'", "XPST0003");
        }
        popNesting(save);
        Expression[] args = new Expression[vec.size()];
        vec.copyInto(args);
        new ApplyExp(makeFunctionExp("gnu.kawa.reflect.TypeSwitch", "typeSwitch"), args);
        return expression;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0581  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x060e  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0612  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x011c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x02df A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x02b2  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x056e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression parseMaybePrimaryExpr() throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r29 = this;
            r2 = r29
            r21 = r2
            r0 = r21
            int r0 = r0.curLine
            r21 = r0
            r3 = r21
            r21 = r2
            r0 = r21
            int r0 = r0.curColumn
            r21 = r0
            r4 = r21
            r21 = r2
            int r21 = r21.peekOperand()
            r5 = r21
            r21 = r5
            switch(r21) {
                case 34: goto L_0x02fe;
                case 36: goto L_0x03e0;
                case 40: goto L_0x0028;
                case 48: goto L_0x032e;
                case 49: goto L_0x0357;
                case 50: goto L_0x0357;
                case 70: goto L_0x0421;
                case 123: goto L_0x01e9;
                case 197: goto L_0x003b;
                case 249: goto L_0x07e3;
                case 250: goto L_0x07e3;
                case 251: goto L_0x0505;
                case 252: goto L_0x0505;
                case 253: goto L_0x0505;
                case 254: goto L_0x0505;
                case 255: goto L_0x0505;
                case 256: goto L_0x0505;
                case 404: goto L_0x01fc;
                default: goto L_0x0023;
            }
        L_0x0023:
            r21 = 0
            r2 = r21
        L_0x0027:
            return r2
        L_0x0028:
            r21 = r2
            gnu.expr.Expression r21 = r21.parseParenExpr()
            r6 = r21
        L_0x0030:
            r21 = r2
            int r21 = r21.getRawToken()
            r21 = r6
            r2 = r21
            goto L_0x0027
        L_0x003b:
            java.util.Stack r21 = new java.util.Stack
            r28 = r21
            r21 = r28
            r22 = r28
            r22.<init>()
            r12 = r21
        L_0x0048:
            r21 = r2
            int r21 = r21.getRawToken()
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 81
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x00f7
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 65
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x00f7
            r21 = r2
            java.lang.String r22 = "missing pragma name"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r13 = r21
        L_0x0079:
            r21 = r12
            r22 = r13
            java.lang.Object r21 = r21.push(r22)
            java.lang.StringBuffer r21 = new java.lang.StringBuffer
            r28 = r21
            r21 = r28
            r22 = r28
            r22.<init>()
            r14 = r21
            r21 = -1
            r16 = r21
        L_0x0092:
            r21 = r2
            int r21 = r21.read()
            r15 = r21
            int r16 = r16 + 1
            r21 = r15
            if (r21 < 0) goto L_0x00ad
            r21 = r15
            r0 = r21
            char r0 = (char) r0
            r21 = r0
            boolean r21 = java.lang.Character.isWhitespace(r21)
            if (r21 != 0) goto L_0x0092
        L_0x00ad:
            r21 = r15
            r22 = 35
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x00c5
            r21 = r2
            int r21 = r21.peek()
            r22 = 41
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x011c
        L_0x00c5:
            r21 = r15
            if (r21 >= 0) goto L_0x00d1
            r21 = r2
            java.lang.String r22 = "pragma ended by end-of-file"
            r21.eofError(r22)
        L_0x00d1:
            r21 = r16
            if (r21 != 0) goto L_0x00dd
            r21 = r2
            java.lang.String r22 = "missing space between pragma and extension content"
            r21.error(r22)
        L_0x00dd:
            r21 = 1
            r16 = r21
            r21 = r14
            r22 = r15
            r0 = r22
            char r0 = (char) r0
            r22 = r0
            java.lang.StringBuffer r21 = r21.append(r22)
            r21 = r2
            int r21 = r21.read()
            r15 = r21
            goto L_0x00ad
        L_0x00f7:
            java.lang.String r21 = new java.lang.String
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = r2
            r0 = r23
            char[] r0 = r0.tokenBuffer
            r23 = r0
            r24 = 0
            r25 = r2
            r0 = r25
            int r0 = r0.tokenBufferLength
            r25 = r0
            r22.<init>(r23, r24, r25)
            gnu.expr.QuoteExp r21 = gnu.expr.QuoteExp.getInstance(r21)
            r13 = r21
            goto L_0x0079
        L_0x011c:
            r21 = r2
            int r21 = r21.read()
            r21 = r12
            r22 = r14
            java.lang.String r22 = r22.toString()
            gnu.expr.QuoteExp r22 = gnu.expr.QuoteExp.getInstance(r22)
            java.lang.Object r21 = r21.push(r22)
            r21 = r2
            int r21 = r21.getRawToken()
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 197(0xc5, float:2.76E-43)
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x01da
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 123(0x7b, float:1.72E-43)
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x01dc
            r21 = r2
            int r21 = r21.getRawToken()
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 125(0x7d, float:1.75E-43)
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x01a7
            r21 = r2
            r22 = 123(0x7b, float:1.72E-43)
            char r21 = r21.pushNesting(r22)
            r13 = r21
            r21 = r12
            r22 = r2
            r23 = 125(0x7d, float:1.75E-43)
            r24 = 0
            gnu.expr.Expression r22 = r22.parseExprSequence(r23, r24)
            java.lang.Object r21 = r21.push(r22)
            r21 = r2
            r22 = r13
            r21.popNesting(r22)
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = -1
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x01a7
            r21 = r2
            java.lang.String r22 = "missing '}' - unexpected end-of-file"
            r21.eofError(r22)
        L_0x01a7:
            r21 = r12
            int r21 = r21.size()
            r0 = r21
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r21 = r0
            r11 = r21
            r21 = r12
            r22 = r11
            r21.copyInto(r22)
            gnu.expr.ApplyExp r21 = new gnu.expr.ApplyExp
            r28 = r21
            r21 = r28
            r22 = r28
            gnu.expr.ReferenceExp r23 = new gnu.expr.ReferenceExp
            r28 = r23
            r23 = r28
            r24 = r28
            gnu.expr.Declaration r25 = gnu.xquery.lang.XQResolveNames.handleExtensionDecl
            r24.<init>((gnu.expr.Declaration) r25)
            r24 = r11
            r22.<init>((gnu.expr.Expression) r23, (gnu.expr.Expression[]) r24)
            r6 = r21
            goto L_0x0030
        L_0x01da:
            goto L_0x0048
        L_0x01dc:
            r21 = r2
            java.lang.String r22 = "missing '{' after pragma"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r6 = r21
            goto L_0x0030
        L_0x01e9:
            r21 = r2
            java.lang.String r22 = "saw unexpected '{' - assume you meant '('"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r6 = r21
            r21 = r2
            gnu.expr.Expression r21 = r21.parseEnclosedExpr()
            goto L_0x0030
        L_0x01fc:
            r21 = r2
            int r21 = r21.read()
            r13 = r21
            r21 = r13
            r22 = 47
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x02e5
            r21 = r2
            int r21 = r21.getRawToken()
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 65
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x0244
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 81
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x0244
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 67
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x02d9
        L_0x0244:
            java.lang.StringBuilder r21 = new java.lang.StringBuilder
            r28 = r21
            r21 = r28
            r22 = r28
            r22.<init>()
            java.lang.String r22 = "saw end tag '</"
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r22 = new java.lang.String
            r28 = r22
            r22 = r28
            r23 = r28
            r24 = r2
            r0 = r24
            char[] r0 = r0.tokenBuffer
            r24 = r0
            r25 = 0
            r26 = r2
            r0 = r26
            int r0 = r0.tokenBufferLength
            r26 = r0
            r23.<init>(r24, r25, r26)
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r22 = ">' not in an element constructor"
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r21 = r21.toString()
            r14 = r21
        L_0x0284:
            r21 = r2
            r22 = r3
            r0 = r22
            r1 = r21
            r1.curLine = r0
            r21 = r2
            r22 = r4
            r0 = r22
            r1 = r21
            r1.curColumn = r0
            r21 = r2
            r22 = r14
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r6 = r21
        L_0x02a2:
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 405(0x195, float:5.68E-43)
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x02df
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = -1
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x02df
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 10
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x02df
            r21 = r2
            int r21 = r21.getRawToken()
            goto L_0x02a2
        L_0x02d9:
            java.lang.String r21 = "saw end tag '</' not in an element constructor"
            r14 = r21
            goto L_0x0284
        L_0x02df:
            r21 = r6
            r2 = r21
            goto L_0x0027
        L_0x02e5:
            r21 = r2
            r22 = r13
            r23 = 0
            gnu.expr.Expression r21 = r21.parseXMLConstructor(r22, r23)
            r6 = r21
            r21 = r2
            r22 = r6
            r23 = r3
            r24 = r4
            r21.maybeSetLine((gnu.expr.Expression) r22, (int) r23, (int) r24)
            goto L_0x0030
        L_0x02fe:
            gnu.expr.QuoteExp r21 = new gnu.expr.QuoteExp
            r28 = r21
            r21 = r28
            r22 = r28
            java.lang.String r23 = new java.lang.String
            r28 = r23
            r23 = r28
            r24 = r28
            r25 = r2
            r0 = r25
            char[] r0 = r0.tokenBuffer
            r25 = r0
            r26 = 0
            r27 = r2
            r0 = r27
            int r0 = r0.tokenBufferLength
            r27 = r0
            r24.<init>(r25, r26, r27)
            java.lang.String r23 = r23.intern()
            r22.<init>(r23)
            r6 = r21
            goto L_0x0030
        L_0x032e:
            gnu.expr.QuoteExp r21 = new gnu.expr.QuoteExp
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = r2
            r0 = r23
            char[] r0 = r0.tokenBuffer
            r23 = r0
            r24 = 0
            r25 = r2
            r0 = r25
            int r0 = r0.tokenBufferLength
            r25 = r0
            r26 = 10
            r27 = 0
            gnu.math.IntNum r23 = gnu.math.IntNum.valueOf(r23, r24, r25, r26, r27)
            r22.<init>(r23)
            r6 = r21
            goto L_0x0030
        L_0x0357:
            java.lang.String r21 = new java.lang.String
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = r2
            r0 = r23
            char[] r0 = r0.tokenBuffer
            r23 = r0
            r24 = 0
            r25 = r2
            r0 = r25
            int r0 = r0.tokenBufferLength
            r25 = r0
            r22.<init>(r23, r24, r25)
            r14 = r21
            r21 = r5
            r22 = 49
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x03a0
            java.math.BigDecimal r21 = new java.math.BigDecimal     // Catch:{ Throwable -> 0x03b0 }
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = r14
            r22.<init>(r23)     // Catch:{ Throwable -> 0x03b0 }
            r15 = r21
        L_0x038f:
            gnu.expr.QuoteExp r21 = new gnu.expr.QuoteExp     // Catch:{ Throwable -> 0x03b0 }
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = r15
            r22.<init>(r23)     // Catch:{ Throwable -> 0x03b0 }
            r6 = r21
            goto L_0x0030
        L_0x03a0:
            java.lang.Double r21 = new java.lang.Double     // Catch:{ Throwable -> 0x03b0 }
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = r14
            r22.<init>(r23)     // Catch:{ Throwable -> 0x03b0 }
            r15 = r21
            goto L_0x038f
        L_0x03b0:
            r21 = move-exception
            r15 = r21
            r21 = r2
            java.lang.StringBuilder r22 = new java.lang.StringBuilder
            r28 = r22
            r22 = r28
            r23 = r28
            r23.<init>()
            java.lang.String r23 = "invalid decimal literal: '"
            java.lang.StringBuilder r22 = r22.append(r23)
            r23 = r14
            java.lang.StringBuilder r22 = r22.append(r23)
            java.lang.String r23 = "'"
            java.lang.StringBuilder r22 = r22.append(r23)
            java.lang.String r22 = r22.toString()
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r6 = r21
            goto L_0x0030
        L_0x03e0:
            r21 = r2
            java.lang.Object r21 = r21.parseVariable()
            r15 = r21
            r21 = r15
            if (r21 != 0) goto L_0x03f9
            r21 = r2
            java.lang.String r22 = "missing Variable"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r2 = r21
            goto L_0x0027
        L_0x03f9:
            gnu.expr.ReferenceExp r21 = new gnu.expr.ReferenceExp
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = r15
            r22.<init>((java.lang.Object) r23)
            r6 = r21
            r21 = r2
            r22 = r6
            r23 = r2
            r0 = r23
            int r0 = r0.curLine
            r23 = r0
            r24 = r2
            r0 = r24
            int r0 = r0.curColumn
            r24 = r0
            r21.maybeSetLine((gnu.expr.Expression) r22, (int) r23, (int) r24)
            goto L_0x0030
        L_0x0421:
            java.lang.String r21 = new java.lang.String
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = r2
            r0 = r23
            char[] r0 = r0.tokenBuffer
            r23 = r0
            r24 = 0
            r25 = r2
            r0 = r25
            int r0 = r0.tokenBufferLength
            r25 = r0
            r22.<init>(r23, r24, r25)
            r15 = r21
            r21 = r2
            r22 = 40
            char r21 = r21.pushNesting(r22)
            r16 = r21
            r21 = r2
            int r21 = r21.getRawToken()
            java.util.Vector r21 = new java.util.Vector
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = 10
            r22.<init>(r23)
            r10 = r21
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 41
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x048e
        L_0x046f:
            r21 = r2
            gnu.expr.Expression r21 = r21.parseExpr()
            r17 = r21
            r21 = r10
            r22 = r17
            r21.addElement(r22)
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 41
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x04e0
        L_0x048e:
            r21 = r10
            int r21 = r21.size()
            r0 = r21
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r21 = r0
            r11 = r21
            r21 = r10
            r22 = r11
            r21.copyInto(r22)
            gnu.expr.ReferenceExp r21 = new gnu.expr.ReferenceExp
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = r15
            r24 = 0
            r22.<init>(r23, r24)
            r17 = r21
            r21 = r17
            r22 = 1
            r21.setProcedureName(r22)
            gnu.expr.ApplyExp r21 = new gnu.expr.ApplyExp
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = r17
            r24 = r11
            r22.<init>((gnu.expr.Expression) r23, (gnu.expr.Expression[]) r24)
            r6 = r21
            r21 = r2
            r22 = r6
            r23 = r3
            r24 = r4
            r21.maybeSetLine((gnu.expr.Expression) r22, (int) r23, (int) r24)
            r21 = r2
            r22 = r16
            r21.popNesting(r22)
            goto L_0x0030
        L_0x04e0:
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 44
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x04fd
            r21 = r2
            java.lang.String r22 = "missing ')' after function call"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r2 = r21
            goto L_0x0027
        L_0x04fd:
            r21 = r2
            int r21 = r21.getRawToken()
            goto L_0x046f
        L_0x0505:
            r21 = r2
            int r21 = r21.getRawToken()
            java.util.Vector r21 = new java.util.Vector
            r28 = r21
            r21 = r28
            r22 = r28
            r22.<init>()
            r10 = r21
            r21 = r5
            r22 = 251(0xfb, float:3.52E-43)
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x052c
            r21 = r5
            r22 = 252(0xfc, float:3.53E-43)
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0617
        L_0x052c:
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 65
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x054c
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 81
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x05e7
        L_0x054c:
            r21 = r2
            r22 = r5
            r23 = 251(0xfb, float:3.52E-43)
            r0 = r22
            r1 = r23
            if (r0 == r1) goto L_0x05e3
            r22 = 1
        L_0x055a:
            gnu.expr.Expression r21 = r21.parseNameTest(r22)
            r19 = r21
        L_0x0560:
            r21 = r10
            r22 = r19
            r23 = r5
            r24 = 251(0xfb, float:3.52E-43)
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x060e
            r23 = 1
        L_0x0570:
            gnu.expr.ApplyExp r22 = castQName(r22, r23)
            r21.addElement(r22)
            r21 = r5
            r22 = 251(0xfb, float:3.52E-43)
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0612
            gnu.kawa.xml.MakeElement r21 = new gnu.kawa.xml.MakeElement
            r28 = r21
            r21 = r28
            r22 = r28
            r22.<init>()
            r20 = r21
            r21 = r20
            r22 = r2
            r0 = r22
            int r0 = r0.copyNamespacesMode
            r22 = r0
            r0 = r22
            r1 = r21
            r1.copyNamespacesMode = r0
            gnu.expr.QuoteExp r21 = new gnu.expr.QuoteExp
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = r20
            r22.<init>(r23)
            r18 = r21
        L_0x05ad:
            r21 = r2
            int r21 = r21.getRawToken()
        L_0x05b3:
            r21 = r2
            r22 = 123(0x7b, float:1.72E-43)
            char r21 = r21.pushNesting(r22)
            r19 = r21
            r21 = r2
            java.lang.String r22 = "unexpected end-of-file after '{'"
            int r21 = r21.peekNonSpace(r22)
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 123(0x7b, float:1.72E-43)
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x06f1
            r21 = r2
            java.lang.String r22 = "missing '{'"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r2 = r21
            goto L_0x0027
        L_0x05e3:
            r22 = 0
            goto L_0x055a
        L_0x05e7:
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 123(0x7b, float:1.72E-43)
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0601
            r21 = r2
            gnu.expr.Expression r21 = r21.parseEnclosedExpr()
            r19 = r21
            goto L_0x0560
        L_0x0601:
            r21 = r2
            java.lang.String r22 = "missing element/attribute name"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r2 = r21
            goto L_0x0027
        L_0x060e:
            r23 = 0
            goto L_0x0570
        L_0x0612:
            gnu.expr.QuoteExp r21 = gnu.kawa.xml.MakeAttribute.makeAttributeExp
            r18 = r21
            goto L_0x05ad
        L_0x0617:
            r21 = r5
            r22 = 256(0x100, float:3.59E-43)
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x062e
            java.lang.String r21 = "gnu.kawa.xml.DocumentConstructor"
            java.lang.String r22 = "documentConstructor"
            gnu.expr.Expression r21 = makeFunctionExp(r21, r22)
            r18 = r21
            goto L_0x05b3
        L_0x062e:
            r21 = r5
            r22 = 254(0xfe, float:3.56E-43)
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0646
            java.lang.String r21 = "gnu.kawa.xml.CommentConstructor"
            java.lang.String r22 = "commentConstructor"
            gnu.expr.Expression r21 = makeFunctionExp(r21, r22)
            r18 = r21
            goto L_0x05b3
        L_0x0646:
            r21 = r5
            r22 = 255(0xff, float:3.57E-43)
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x06e3
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 65
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x06a9
            gnu.expr.QuoteExp r21 = new gnu.expr.QuoteExp
            r28 = r21
            r21 = r28
            r22 = r28
            java.lang.String r23 = new java.lang.String
            r28 = r23
            r23 = r28
            r24 = r28
            r25 = r2
            r0 = r25
            char[] r0 = r0.tokenBuffer
            r25 = r0
            r26 = 0
            r27 = r2
            r0 = r27
            int r0 = r0.tokenBufferLength
            r27 = r0
            r24.<init>(r25, r26, r27)
            java.lang.String r23 = r23.intern()
            r22.<init>(r23)
            r19 = r21
        L_0x068e:
            r21 = r10
            r22 = r19
            r21.addElement(r22)
            java.lang.String r21 = "gnu.kawa.xml.MakeProcInst"
            java.lang.String r22 = "makeProcInst"
            gnu.expr.Expression r21 = makeFunctionExp(r21, r22)
            r18 = r21
            r21 = r2
            int r21 = r21.getRawToken()
            goto L_0x05b3
        L_0x06a9:
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 123(0x7b, float:1.72E-43)
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x06c2
            r21 = r2
            gnu.expr.Expression r21 = r21.parseEnclosedExpr()
            r19 = r21
            goto L_0x068e
        L_0x06c2:
            r21 = r2
            java.lang.String r22 = "expected NCName or '{' after 'processing-instruction'"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r19 = r21
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 81
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x068e
            r21 = r19
            r2 = r21
            goto L_0x0027
        L_0x06e3:
            java.lang.String r21 = "gnu.kawa.xml.MakeText"
            java.lang.String r22 = "makeText"
            gnu.expr.Expression r21 = makeFunctionExp(r21, r22)
            r18 = r21
            goto L_0x05b3
        L_0x06f1:
            r21 = r2
            int r21 = r21.getRawToken()
            r21 = r5
            r22 = 253(0xfd, float:3.55E-43)
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x0715
            r21 = r5
            r22 = 254(0xfe, float:3.56E-43)
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x0715
            r21 = r5
            r22 = 255(0xff, float:3.57E-43)
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0755
        L_0x0715:
            r21 = r10
            r22 = r2
            r23 = 125(0x7d, float:1.75E-43)
            r24 = r5
            r25 = 255(0xff, float:3.57E-43)
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x0752
            r24 = 1
        L_0x0727:
            gnu.expr.Expression r22 = r22.parseExprSequence(r23, r24)
            r21.addElement(r22)
        L_0x072e:
            r21 = r2
            r22 = r19
            r21.popNesting(r22)
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 125(0x7d, float:1.75E-43)
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x0792
            r21 = r2
            java.lang.String r22 = "missing '}'"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r2 = r21
            goto L_0x0027
        L_0x0752:
            r24 = 0
            goto L_0x0727
        L_0x0755:
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 125(0x7d, float:1.75E-43)
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x072e
            r21 = r10
            r22 = r2
            gnu.expr.Expression r22 = r22.parseExpr()
            r21.addElement(r22)
        L_0x0770:
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 44
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x072e
            r21 = r2
            int r21 = r21.getRawToken()
            r21 = r10
            r22 = r2
            gnu.expr.Expression r22 = r22.parseExpr()
            r21.addElement(r22)
            goto L_0x0770
        L_0x0792:
            r21 = r10
            int r21 = r21.size()
            r0 = r21
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r21 = r0
            r11 = r21
            r21 = r10
            r22 = r11
            r21.copyInto(r22)
            gnu.expr.ApplyExp r21 = new gnu.expr.ApplyExp
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = r18
            r24 = r11
            r22.<init>((gnu.expr.Expression) r23, (gnu.expr.Expression[]) r24)
            r6 = r21
            r21 = r2
            r22 = r6
            r23 = r3
            r24 = r4
            r21.maybeSetLine((gnu.expr.Expression) r22, (int) r23, (int) r24)
            r21 = r5
            r22 = 256(0x100, float:3.59E-43)
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x07d7
            r21 = r5
            r22 = 251(0xfb, float:3.52E-43)
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0030
        L_0x07d7:
            r21 = r2
            r22 = r6
            gnu.expr.Expression r21 = r21.wrapWithBaseUri(r22)
            r6 = r21
            goto L_0x0030
        L_0x07e3:
            r21 = r2
            int r21 = r21.getRawToken()
            r21 = r2
            r22 = 125(0x7d, float:1.75E-43)
            r23 = 0
            gnu.expr.Expression r21 = r21.parseExprSequence(r22, r23)
            r6 = r21
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQParser.parseMaybePrimaryExpr():gnu.expr.Expression");
    }

    public Expression parseIfExpr() throws IOException, SyntaxException {
        Expression expression;
        char saveReadState1 = pushNesting('i');
        int rawToken = getRawToken();
        char saveReadState2 = pushNesting('(');
        Expression cond = parseExprSequence(41, false);
        popNesting(saveReadState2);
        if (this.curToken == -1) {
            eofError("missing ')' - unexpected end-of-file");
        }
        int rawToken2 = getRawToken();
        if (!match("then")) {
            Expression syntaxError = syntaxError("missing 'then'");
        } else {
            int rawToken3 = getRawToken();
        }
        Expression thenPart = parseExpr();
        if (!match("else")) {
            Expression syntaxError2 = syntaxError("missing 'else'");
        } else {
            int rawToken4 = getRawToken();
        }
        popNesting(saveReadState1);
        new IfExp(booleanValue(cond), thenPart, parseExpr());
        return expression;
    }

    public boolean match(String str) {
        String word = str;
        if (this.curToken != 65) {
            return false;
        }
        int len = word.length();
        if (this.tokenBufferLength != len) {
            return false;
        }
        int i = len;
        do {
            i--;
            if (i < 0) {
                return true;
            }
        } while (word.charAt(i) == this.tokenBuffer[i]);
        return false;
    }

    public Object parseVariable() throws IOException, SyntaxException {
        String str;
        if (this.curToken == 36) {
            int rawToken = getRawToken();
        } else {
            Expression syntaxError = syntaxError("missing '$' before variable name");
        }
        new String(this.tokenBuffer, 0, this.tokenBufferLength);
        String str2 = str;
        if (this.curToken == 81) {
            return str2;
        }
        if (this.curToken == 65) {
            return Namespace.EmptyNamespace.getSymbol(str2.intern());
        }
        return null;
    }

    public Declaration parseVariableDeclaration() throws IOException, SyntaxException {
        Declaration declaration;
        Object name = parseVariable();
        if (name == null) {
            return null;
        }
        new Declaration(name);
        Declaration decl = declaration;
        maybeSetLine(decl, getLineNumber() + 1, (getColumnNumber() + 1) - this.tokenBufferLength);
        return decl;
    }

    public Expression parseFLWRExpression(boolean z) throws IOException, SyntaxException {
        Stack stack;
        LambdaExp lambdaExp;
        StringBuilder sb;
        Object obj;
        LambdaExp lambdaExp2;
        Expression expression;
        StringBuilder sb2;
        boolean isFor = z;
        int flworDeclsSave = this.flworDeclsFirst;
        this.flworDeclsFirst = this.flworDeclsCount;
        Expression exp = parseFLWRInner(isFor);
        if (match("order")) {
            char saveNesting = pushNesting(isFor ? 'f' : 'l');
            int rawToken = getRawToken();
            if (match("by")) {
                int rawToken2 = getRawToken();
            } else {
                error("missing 'by' following 'order'");
            }
            new Stack();
            Stack specs = stack;
            while (true) {
                boolean descending = false;
                char emptyOrder = this.defaultEmptyOrder;
                new LambdaExp(this.flworDeclsCount - this.flworDeclsFirst);
                LambdaExp lexp = lambdaExp;
                for (int i = this.flworDeclsFirst; i < this.flworDeclsCount; i++) {
                    Declaration addDeclaration = lexp.addDeclaration(this.flworDecls[i].getSymbol());
                }
                this.comp.push((ScopeExp) lexp);
                lexp.body = parseExprSingle();
                this.comp.pop(lexp);
                Object push = specs.push(lexp);
                if (match("ascending")) {
                    int rawToken3 = getRawToken();
                } else if (match("descending")) {
                    int rawToken4 = getRawToken();
                    descending = true;
                }
                if (match("empty")) {
                    int rawToken5 = getRawToken();
                    if (match("greatest")) {
                        int rawToken6 = getRawToken();
                        emptyOrder = 'G';
                    } else if (match("least")) {
                        int rawToken7 = getRawToken();
                        emptyOrder = 'L';
                    } else {
                        error("'empty' sequence order must be 'greatest' or 'least'");
                    }
                }
                Stack stack2 = specs;
                QuoteExp quoteExp = r17;
                StringBuilder sb3 = sb;
                new StringBuilder();
                QuoteExp quoteExp2 = new QuoteExp(sb3.append(descending ? "D" : "A").append(emptyOrder).toString());
                Object push2 = stack2.push(quoteExp);
                NamedCollator collation = this.defaultCollator;
                if (match("collation")) {
                    Object uri = parseURILiteral();
                    if (uri instanceof String) {
                        try {
                            collation = NamedCollator.make(resolveAgainstBaseUri((String) uri));
                        } catch (Exception e) {
                            Exception exc = e;
                            new StringBuilder();
                            error('e', sb2.append("unknown collation '").append(uri).append("'").toString(), "XQST0076");
                        }
                    }
                    int rawToken8 = getRawToken();
                }
                new QuoteExp(collation);
                Object push3 = specs.push(obj);
                if (this.curToken != 44) {
                    break;
                }
                int rawToken9 = getRawToken();
            }
            if (!match("return")) {
                return syntaxError("expected 'return' clause");
            }
            int rawToken10 = getRawToken();
            new LambdaExp(this.flworDeclsCount - this.flworDeclsFirst);
            LambdaExp lexp2 = lambdaExp2;
            for (int i2 = this.flworDeclsFirst; i2 < this.flworDeclsCount; i2++) {
                Declaration addDeclaration2 = lexp2.addDeclaration(this.flworDecls[i2].getSymbol());
            }
            popNesting(saveNesting);
            this.comp.push((ScopeExp) lexp2);
            lexp2.body = parseExprSingle();
            this.comp.pop(lexp2);
            int i3 = specs.size();
            Expression[] args = new Expression[(2 + i3)];
            args[0] = exp;
            args[1] = lexp2;
            for (int i4 = 0; i4 < i3; i4++) {
                args[2 + i4] = (Expression) specs.elementAt(i4);
            }
            new ApplyExp(makeFunctionExp("gnu.xquery.util.OrderedMap", "orderedMap"), args);
            return expression;
        }
        this.flworDeclsCount = this.flworDeclsFirst;
        this.flworDeclsFirst = flworDeclsSave;
        return exp;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v10, resolved type: gnu.expr.LetExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v69, resolved type: gnu.expr.LambdaExp} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression parseFLWRInner(boolean r31) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r30 = this;
            r2 = r30
            r3 = r31
            r21 = r2
            r22 = r3
            if (r22 == 0) goto L_0x0051
            r22 = 102(0x66, float:1.43E-43)
        L_0x000c:
            char r21 = r21.pushNesting(r22)
            r4 = r21
            r21 = r2
            r22 = 36
            r0 = r22
            r1 = r21
            r1.curToken = r0
            r21 = r2
            gnu.expr.Declaration r21 = r21.parseVariableDeclaration()
            r5 = r21
            r21 = r5
            if (r21 != 0) goto L_0x0054
            r21 = r2
            java.lang.StringBuilder r22 = new java.lang.StringBuilder
            r28 = r22
            r22 = r28
            r23 = r28
            r23.<init>()
            java.lang.String r23 = "missing Variable - saw "
            java.lang.StringBuilder r22 = r22.append(r23)
            r23 = r2
            java.lang.String r23 = r23.tokenString()
            java.lang.StringBuilder r22 = r22.append(r23)
            java.lang.String r22 = r22.toString()
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r2 = r21
        L_0x0050:
            return r2
        L_0x0051:
            r22 = 108(0x6c, float:1.51E-43)
            goto L_0x000c
        L_0x0054:
            r21 = r2
            r0 = r21
            gnu.expr.Declaration[] r0 = r0.flworDecls
            r21 = r0
            if (r21 != 0) goto L_0x01d7
            r21 = r2
            r22 = 8
            r0 = r22
            gnu.expr.Declaration[] r0 = new gnu.expr.Declaration[r0]
            r22 = r0
            r0 = r22
            r1 = r21
            r1.flworDecls = r0
        L_0x006e:
            r21 = r2
            r0 = r21
            gnu.expr.Declaration[] r0 = r0.flworDecls
            r21 = r0
            r22 = r2
            r28 = r22
            r22 = r28
            r23 = r28
            r0 = r23
            int r0 = r0.flworDeclsCount
            r23 = r0
            r28 = r22
            r29 = r23
            r22 = r29
            r23 = r28
            r24 = r29
            r25 = 1
            int r24 = r24 + 1
            r0 = r24
            r1 = r23
            r1.flworDeclsCount = r0
            r23 = r5
            r21[r22] = r23
            r21 = r2
            int r21 = r21.getRawToken()
            r21 = r2
            gnu.expr.Expression r21 = r21.parseOptionalTypeDeclaration()
            r6 = r21
            r21 = 1
            r0 = r21
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r21 = r0
            r8 = r21
            r21 = 0
            r9 = r21
            r21 = r3
            if (r21 == 0) goto L_0x0250
            r21 = r2
            java.lang.String r22 = "at"
            boolean r21 = r21.match(r22)
            r10 = r21
            gnu.expr.LambdaExp r21 = new gnu.expr.LambdaExp
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = r10
            if (r23 == 0) goto L_0x022b
            r23 = 2
        L_0x00d5:
            r22.<init>((int) r23)
            r11 = r21
            r21 = r10
            if (r21 == 0) goto L_0x010f
            r21 = r2
            int r21 = r21.getRawToken()
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 36
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0102
            r21 = r2
            gnu.expr.Declaration r21 = r21.parseVariableDeclaration()
            r9 = r21
            r21 = r2
            int r21 = r21.getRawToken()
        L_0x0102:
            r21 = r9
            if (r21 != 0) goto L_0x010f
            r21 = r2
            java.lang.String r22 = "missing Variable after 'at'"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
        L_0x010f:
            r21 = r11
            r7 = r21
            r21 = r2
            java.lang.String r22 = "in"
            boolean r21 = r21.match(r22)
            if (r21 == 0) goto L_0x022f
            r21 = r2
            int r21 = r21.getRawToken()
        L_0x0124:
            r21 = r8
            r22 = 0
            r23 = r2
            gnu.expr.Expression r23 = r23.parseExprSingle()
            r21[r22] = r23
            r21 = r6
            if (r21 == 0) goto L_0x014a
            r21 = r3
            if (r21 != 0) goto L_0x014a
            r21 = r8
            r22 = 0
            r23 = r8
            r24 = 0
            r23 = r23[r24]
            r24 = r6
            gnu.expr.ApplyExp r23 = gnu.expr.Compilation.makeCoercion((gnu.expr.Expression) r23, (gnu.expr.Expression) r24)
            r21[r22] = r23
        L_0x014a:
            r21 = r2
            r22 = r4
            r21.popNesting(r22)
            r21 = r2
            r0 = r21
            gnu.expr.Compilation r0 = r0.comp
            r21 = r0
            r22 = r7
            r21.push((gnu.expr.ScopeExp) r22)
            r21 = r7
            r22 = r5
            r21.addDeclaration((gnu.expr.Declaration) r22)
            r21 = r6
            if (r21 == 0) goto L_0x0170
            r21 = r5
            r22 = r6
            r21.setTypeExp(r22)
        L_0x0170:
            r21 = r3
            if (r21 == 0) goto L_0x0183
            r21 = r5
            r22 = 0
            r21.noteValue(r22)
            r21 = r5
            r22 = 262144(0x40000, double:1.295163E-318)
            r21.setFlag(r22)
        L_0x0183:
            r21 = r9
            if (r21 == 0) goto L_0x01a4
            r21 = r7
            r22 = r9
            r21.addDeclaration((gnu.expr.Declaration) r22)
            r21 = r9
            gnu.bytecode.PrimType r22 = gnu.kawa.lispexpr.LangPrimType.intType
            r21.setType(r22)
            r21 = r9
            r22 = 0
            r21.noteValue(r22)
            r21 = r9
            r22 = 262144(0x40000, double:1.295163E-318)
            r21.setFlag(r22)
        L_0x01a4:
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 44
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0312
            r21 = r2
            int r21 = r21.getRawToken()
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 36
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x0296
            r21 = r2
            java.lang.String r22 = "missing $NAME after ','"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r2 = r21
            goto L_0x0050
        L_0x01d7:
            r21 = r2
            r0 = r21
            int r0 = r0.flworDeclsCount
            r21 = r0
            r22 = r2
            r0 = r22
            gnu.expr.Declaration[] r0 = r0.flworDecls
            r22 = r0
            r0 = r22
            int r0 = r0.length
            r22 = r0
            r0 = r21
            r1 = r22
            if (r0 < r1) goto L_0x006e
            r21 = 2
            r22 = r2
            r0 = r22
            int r0 = r0.flworDeclsCount
            r22 = r0
            int r21 = r21 * r22
            r0 = r21
            gnu.expr.Declaration[] r0 = new gnu.expr.Declaration[r0]
            r21 = r0
            r6 = r21
            r21 = r2
            r0 = r21
            gnu.expr.Declaration[] r0 = r0.flworDecls
            r21 = r0
            r22 = 0
            r23 = r6
            r24 = 0
            r25 = r2
            r0 = r25
            int r0 = r0.flworDeclsCount
            r25 = r0
            java.lang.System.arraycopy(r21, r22, r23, r24, r25)
            r21 = r2
            r22 = r6
            r0 = r22
            r1 = r21
            r1.flworDecls = r0
            goto L_0x006e
        L_0x022b:
            r23 = 1
            goto L_0x00d5
        L_0x022f:
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 76
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0245
            r21 = r2
            int r21 = r21.getRawToken()
        L_0x0245:
            r21 = r2
            java.lang.String r22 = "missing 'in' in 'for' clause"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            goto L_0x0124
        L_0x0250:
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 76
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x027b
            r21 = r2
            int r21 = r21.getRawToken()
        L_0x0266:
            gnu.expr.LetExp r21 = new gnu.expr.LetExp
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = r8
            r22.<init>(r23)
            r10 = r21
            r21 = r10
            r7 = r21
            goto L_0x0124
        L_0x027b:
            r21 = r2
            java.lang.String r22 = "in"
            boolean r21 = r21.match(r22)
            if (r21 == 0) goto L_0x028c
            r21 = r2
            int r21 = r21.getRawToken()
        L_0x028c:
            r21 = r2
            java.lang.String r22 = "missing ':=' in 'let' clause"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            goto L_0x0266
        L_0x0296:
            r21 = r2
            r22 = r3
            gnu.expr.Expression r21 = r21.parseFLWRInner(r22)
            r10 = r21
        L_0x02a0:
            r21 = r2
            r0 = r21
            gnu.expr.Compilation r0 = r0.comp
            r21 = r0
            r22 = r7
            r21.pop(r22)
            r21 = r3
            if (r21 == 0) goto L_0x0505
            r21 = r7
            gnu.expr.LambdaExp r21 = (gnu.expr.LambdaExp) r21
            r11 = r21
            r21 = r11
            r22 = r10
            r0 = r22
            r1 = r21
            r1.body = r0
            r21 = 2
            r0 = r21
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r21 = r0
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = 0
            r24 = r7
            r22[r23] = r24
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = 1
            r24 = r8
            r25 = 0
            r24 = r24[r25]
            r22[r23] = r24
            r12 = r21
            gnu.expr.ApplyExp r21 = new gnu.expr.ApplyExp
            r28 = r21
            r21 = r28
            r22 = r28
            java.lang.String r23 = "gnu.kawa.functions.ValuesMap"
            r24 = r11
            r0 = r24
            int r0 = r0.min_args
            r24 = r0
            r25 = 1
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x0500
            java.lang.String r24 = "valuesMap"
        L_0x0305:
            gnu.expr.Expression r23 = makeFunctionExp(r23, r24)
            r24 = r12
            r22.<init>((gnu.expr.Expression) r23, (gnu.expr.Expression[]) r24)
            r2 = r21
            goto L_0x0050
        L_0x0312:
            r21 = r2
            java.lang.String r22 = "for"
            boolean r21 = r21.match(r22)
            if (r21 == 0) goto L_0x034c
            r21 = r2
            int r21 = r21.getRawToken()
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 36
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x0340
            r21 = r2
            java.lang.String r22 = "missing $NAME after 'for'"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r2 = r21
            goto L_0x0050
        L_0x0340:
            r21 = r2
            r22 = 1
            gnu.expr.Expression r21 = r21.parseFLWRInner(r22)
            r10 = r21
            goto L_0x02a0
        L_0x034c:
            r21 = r2
            java.lang.String r22 = "let"
            boolean r21 = r21.match(r22)
            if (r21 == 0) goto L_0x0386
            r21 = r2
            int r21 = r21.getRawToken()
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 36
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x037a
            r21 = r2
            java.lang.String r22 = "missing $NAME after 'let'"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r2 = r21
            goto L_0x0050
        L_0x037a:
            r21 = r2
            r22 = 0
            gnu.expr.Expression r21 = r21.parseFLWRInner(r22)
            r10 = r21
            goto L_0x02a0
        L_0x0386:
            r21 = r2
            r22 = 119(0x77, float:1.67E-43)
            char r21 = r21.pushNesting(r22)
            r12 = r21
            r21 = r2
            r0 = r21
            int r0 = r0.curToken
            r21 = r0
            r22 = 196(0xc4, float:2.75E-43)
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x040b
            r21 = r2
            int r21 = r21.getRawToken()
            r21 = r2
            gnu.expr.Expression r21 = r21.parseExprSingle()
            r11 = r21
        L_0x03ae:
            r21 = r2
            r22 = r12
            r21.popNesting(r22)
            r21 = r2
            java.lang.String r22 = "stable"
            boolean r21 = r21.match(r22)
            r13 = r21
            r21 = r13
            if (r21 == 0) goto L_0x03ca
            r21 = r2
            int r21 = r21.getRawToken()
        L_0x03ca:
            r21 = r2
            java.lang.String r22 = "return"
            boolean r21 = r21.match(r22)
            r14 = r21
            r21 = r2
            java.lang.String r22 = "order"
            boolean r21 = r21.match(r22)
            r15 = r21
            r21 = r14
            if (r21 != 0) goto L_0x0424
            r21 = r15
            if (r21 != 0) goto L_0x0424
            r21 = r2
            java.lang.String r22 = "let"
            boolean r21 = r21.match(r22)
            if (r21 != 0) goto L_0x0424
            r21 = r2
            java.lang.String r22 = "for"
            boolean r21 = r21.match(r22)
            if (r21 != 0) goto L_0x0424
            r21 = r2
            java.lang.String r22 = "missing 'return' clause"
            gnu.expr.Expression r21 = r21.syntaxError(r22)
            r2 = r21
            goto L_0x0050
        L_0x040b:
            r21 = r2
            java.lang.String r22 = "where"
            boolean r21 = r21.match(r22)
            if (r21 == 0) goto L_0x041f
            r21 = r2
            gnu.expr.Expression r21 = r21.parseExprSingle()
            r11 = r21
            goto L_0x03ae
        L_0x041f:
            r21 = 0
            r11 = r21
            goto L_0x03ae
        L_0x0424:
            r21 = r15
            if (r21 != 0) goto L_0x0431
            r21 = r2
            java.lang.String r22 = "unexpected eof-of-file after 'return'"
            int r21 = r21.peekNonSpace(r22)
        L_0x0431:
            r21 = r2
            int r21 = r21.getLineNumber()
            r22 = 1
            int r21 = r21 + 1
            r16 = r21
            r21 = r2
            int r21 = r21.getColumnNumber()
            r22 = 1
            int r21 = r21 + 1
            r17 = r21
            r21 = r14
            if (r21 == 0) goto L_0x0453
            r21 = r2
            int r21 = r21.getRawToken()
        L_0x0453:
            r21 = r15
            if (r21 == 0) goto L_0x04f7
            r21 = r2
            r0 = r21
            int r0 = r0.flworDeclsCount
            r21 = r0
            r22 = r2
            r0 = r22
            int r0 = r0.flworDeclsFirst
            r22 = r0
            int r21 = r21 - r22
            r18 = r21
            r21 = r18
            r0 = r21
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r21 = r0
            r19 = r21
            r21 = 0
            r20 = r21
        L_0x0479:
            r21 = r20
            r22 = r18
            r0 = r21
            r1 = r22
            if (r0 >= r1) goto L_0x04ad
            r21 = r19
            r22 = r20
            gnu.expr.ReferenceExp r23 = new gnu.expr.ReferenceExp
            r28 = r23
            r23 = r28
            r24 = r28
            r25 = r2
            r0 = r25
            gnu.expr.Declaration[] r0 = r0.flworDecls
            r25 = r0
            r26 = r2
            r0 = r26
            int r0 = r0.flworDeclsFirst
            r26 = r0
            r27 = r20
            int r26 = r26 + r27
            r25 = r25[r26]
            r24.<init>((gnu.expr.Declaration) r25)
            r21[r22] = r23
            int r20 = r20 + 1
            goto L_0x0479
        L_0x04ad:
            gnu.expr.ApplyExp r21 = new gnu.expr.ApplyExp
            r28 = r21
            r21 = r28
            r22 = r28
            gnu.expr.PrimProcedure r23 = new gnu.expr.PrimProcedure
            r28 = r23
            r23 = r28
            r24 = r28
            java.lang.String r25 = "gnu.xquery.util.OrderedMap"
            java.lang.String r26 = "makeTuple$V"
            r27 = 1
            r24.<init>((java.lang.String) r25, (java.lang.String) r26, (int) r27)
            r24 = r19
            r22.<init>((gnu.mapping.Procedure) r23, (gnu.expr.Expression[]) r24)
            r10 = r21
        L_0x04cf:
            r21 = r11
            if (r21 == 0) goto L_0x04ea
            gnu.expr.IfExp r21 = new gnu.expr.IfExp
            r28 = r21
            r21 = r28
            r22 = r28
            r23 = r11
            gnu.expr.Expression r23 = booleanValue(r23)
            r24 = r10
            gnu.expr.QuoteExp r25 = gnu.expr.QuoteExp.voidExp
            r22.<init>(r23, r24, r25)
            r10 = r21
        L_0x04ea:
            r21 = r2
            r22 = r10
            r23 = r16
            r24 = r17
            r21.maybeSetLine((gnu.expr.Expression) r22, (int) r23, (int) r24)
            goto L_0x02a0
        L_0x04f7:
            r21 = r2
            gnu.expr.Expression r21 = r21.parseExprSingle()
            r10 = r21
            goto L_0x04cf
        L_0x0500:
            java.lang.String r24 = "valuesMapWithPos"
            goto L_0x0305
        L_0x0505:
            r21 = r7
            gnu.expr.LetExp r21 = (gnu.expr.LetExp) r21
            r22 = r10
            r21.setBody(r22)
            r21 = r7
            r2 = r21
            goto L_0x0050
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQParser.parseFLWRInner(boolean):gnu.expr.Expression");
    }

    public Expression parseQuantifiedExpr(boolean z) throws IOException, SyntaxException {
        LambdaExp lambdaExp;
        Expression body;
        String str;
        StringBuilder sb;
        boolean isEvery = z;
        char saveNesting = pushNesting(isEvery ? 'e' : 's');
        this.curToken = 36;
        Declaration decl = parseVariableDeclaration();
        if (decl == null) {
            new StringBuilder();
            return syntaxError(sb.append("missing Variable token:").append(this.curToken).toString());
        }
        int rawToken = getRawToken();
        new LambdaExp(1);
        LambdaExp lexp = lambdaExp;
        lexp.addDeclaration(decl);
        decl.noteValue((Expression) null);
        decl.setFlag(PlaybackStateCompat.ACTION_SET_REPEAT_MODE);
        decl.setTypeExp(parseOptionalTypeDeclaration());
        if (match("in")) {
            int rawToken2 = getRawToken();
        } else {
            if (this.curToken == 76) {
                int rawToken3 = getRawToken();
            }
            Expression syntaxError = syntaxError("missing 'in' in QuantifiedExpr");
        }
        Expression[] inits = {parseExprSingle()};
        popNesting(saveNesting);
        this.comp.push((ScopeExp) lexp);
        if (this.curToken == 44) {
            int rawToken4 = getRawToken();
            if (this.curToken != 36) {
                return syntaxError("missing $NAME after ','");
            }
            body = parseQuantifiedExpr(isEvery);
        } else {
            boolean sawSatisfies = match("satisfies");
            if (!sawSatisfies && !match("every") && !match("some")) {
                return syntaxError("missing 'satisfies' clause");
            }
            int peekNonSpace = peekNonSpace("unexpected eof-of-file after 'satisfies'");
            int bodyLine = getLineNumber() + 1;
            int bodyColumn = getColumnNumber() + 1;
            if (sawSatisfies) {
                int rawToken5 = getRawToken();
            }
            body = parseExprSingle();
            maybeSetLine(body, bodyLine, bodyColumn);
        }
        this.comp.pop(lexp);
        lexp.body = body;
        Expression[] expressionArr = new Expression[2];
        expressionArr[0] = lexp;
        Expression[] expressionArr2 = expressionArr;
        expressionArr2[1] = inits[0];
        Expression[] args = expressionArr2;
        ApplyExp applyExp = r16;
        if (isEvery) {
            str = "every";
        } else {
            str = "some";
        }
        ApplyExp applyExp2 = new ApplyExp(makeFunctionExp("gnu.xquery.util.ValuesEvery", str), args);
        return applyExp;
    }

    public Expression parseFunctionDefinition(int i, int i2) throws IOException, SyntaxException {
        String str;
        LambdaExp lambdaExp;
        SetExp setExp;
        Expression err;
        StringBuilder sb;
        StringBuilder sb2;
        int declLine = i;
        int declColumn = i2;
        if (this.curToken != 81 && this.curToken != 65) {
            return syntaxError("missing function name");
        }
        new String(this.tokenBuffer, 0, this.tokenBufferLength);
        String name = str;
        getMessages().setLine(this.port.getName(), this.curLine, this.curColumn);
        Symbol sym = namespaceResolve(name, true);
        String uri = sym.getNamespaceURI();
        if (uri == NamespaceBinding.XML_NAMESPACE || uri == XQuery.SCHEMA_NAMESPACE || uri == XQuery.SCHEMA_INSTANCE_NAMESPACE || uri == XQuery.XQUERY_FUNCTION_NAMESPACE) {
            new StringBuilder();
            error('e', sb2.append("cannot declare function in standard namespace '").append(uri).append('\'').toString(), "XQST0045");
        } else if (uri == "") {
            error(this.comp.isPedantic() ? 'e' : 'w', "cannot declare function in empty namespace", "XQST0060");
        } else if (!(this.libraryModuleNamespace == null || uri == this.libraryModuleNamespace || (XQuery.LOCAL_NAMESPACE.equals(uri) && !this.comp.isPedantic()))) {
            error('e', "function not in namespace of library module", "XQST0048");
        }
        int rawToken = getRawToken();
        if (this.curToken != 40) {
            new StringBuilder();
            return syntaxError(sb.append("missing parameter list:").append(this.curToken).toString());
        }
        int rawToken2 = getRawToken();
        new LambdaExp();
        LambdaExp lexp = lambdaExp;
        maybeSetLine((Expression) lexp, declLine, declColumn);
        lexp.setName(name);
        Declaration decl = this.comp.currentScope().addDeclaration((Object) sym);
        if (this.comp.isStatic()) {
            decl.setFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH);
        }
        lexp.setFlag(2048);
        decl.setCanRead(true);
        decl.setProcedureDecl(true);
        maybeSetLine(decl, declLine, declColumn);
        this.comp.push((ScopeExp) lexp);
        if (this.curToken != 41) {
            loop0:
            while (true) {
                Declaration param = parseVariableDeclaration();
                if (param == null) {
                    error("missing parameter name");
                } else {
                    lexp.addDeclaration(param);
                    int rawToken3 = getRawToken();
                    lexp.min_args++;
                    lexp.max_args++;
                    param.setTypeExp(parseOptionalTypeDeclaration());
                }
                if (this.curToken == 41) {
                    break;
                } else if (this.curToken != 44) {
                    err = syntaxError("missing ',' in parameter list");
                    do {
                        int rawToken4 = getRawToken();
                        if (this.curToken >= 0 && this.curToken != 59 && this.curToken != 59) {
                            if (this.curToken == 41) {
                                break loop0;
                            }
                        }
                    } while (this.curToken != 44);
                } else {
                    int rawToken5 = getRawToken();
                }
            }
            return err;
        }
        int rawToken6 = getRawToken();
        Expression retType = parseOptionalTypeDeclaration();
        lexp.body = parseEnclosedExpr();
        this.comp.pop(lexp);
        if (retType != null) {
            lexp.setCoercedReturnValue(retType, this.interpreter);
        }
        new SetExp(decl, (Expression) lexp);
        SetExp sexp = setExp;
        sexp.setDefining(true);
        decl.noteValue(lexp);
        return sexp;
    }

    public Object readObject() throws IOException, SyntaxException {
        return parse((Compilation) null);
    }

    /* access modifiers changed from: protected */
    public Symbol namespaceResolve(String str, boolean z) {
        StringBuilder sb;
        StringBuilder sb2;
        String name = str;
        boolean function = z;
        int colon = name.indexOf(58);
        String prefix = colon >= 0 ? name.substring(0, colon).intern() : function ? XQuery.DEFAULT_FUNCTION_PREFIX : XQuery.DEFAULT_ELEMENT_PREFIX;
        String uri = QNameUtils.lookupPrefix(prefix, this.constructorNamespaces, this.prologNamespaces);
        if (uri == null) {
            if (colon < 0) {
                uri = "";
            } else if (!this.comp.isPedantic()) {
                try {
                    Class<?> cls = Class.forName(prefix);
                    new StringBuilder();
                    uri = sb.append("class:").append(prefix).toString();
                } catch (Exception e) {
                    Exception exc = e;
                    uri = null;
                }
            }
            if (uri == null) {
                SourceMessages messages = getMessages();
                new StringBuilder();
                messages.error('e', sb2.append("unknown namespace prefix '").append(prefix).append("'").toString(), "XPST0081");
                uri = "(unknown namespace)";
            }
        }
        return Symbol.make(uri, colon < 0 ? name : name.substring(colon + 1), prefix);
    }

    /* access modifiers changed from: package-private */
    public void parseSeparator() throws IOException, SyntaxException {
        int startLine = this.port.getLineNumber() + 1;
        int startColumn = this.port.getColumnNumber() + 1;
        int next = skipSpace(this.nesting != 0);
        if (next != 59) {
            if (warnOldVersion && next != 10) {
                this.curLine = startLine;
                this.curColumn = startColumn;
                error('w', "missing ';' after declaration");
            }
            if (next >= 0) {
                unread(next);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x00a4, code lost:
        if (r40 != 47) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x0af0, code lost:
        if (r3.curToken == 76) goto L_0x0af2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0335, code lost:
        if (r3.curToken == 76) goto L_0x0337;
     */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x049a  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x04ee  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x048e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression parse(gnu.expr.Compilation r42) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r41 = this;
            r3 = r41
            r4 = r42
            r33 = r3
            r34 = r4
            r0 = r34
            r1 = r33
            r1.comp = r0
            r33 = r3
            int r33 = r33.skipSpace()
            r5 = r33
            r33 = r5
            if (r33 >= 0) goto L_0x001f
            r33 = 0
            r3 = r33
        L_0x001e:
            return r3
        L_0x001f:
            r33 = r3
            r40 = r33
            r33 = r40
            r34 = r40
            r0 = r34
            int r0 = r0.parseCount
            r34 = r0
            r35 = 1
            int r34 = r34 + 1
            r0 = r34
            r1 = r33
            r1.parseCount = r0
            r33 = r3
            r34 = r5
            r33.unread(r34)
            r33 = r3
            int r33 = r33.getLineNumber()
            r34 = 1
            int r33 = r33 + 1
            r6 = r33
            r33 = r3
            int r33 = r33.getColumnNumber()
            r34 = 1
            int r33 = r33 + 1
            r7 = r33
            r33 = r5
            r34 = 35
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x00cf
            r33 = r6
            r34 = 1
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x00cf
            r33 = r7
            r34 = 1
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x00cf
            r33 = r3
            int r33 = r33.read()
            r33 = r3
            int r33 = r33.read()
            r40 = r33
            r33 = r40
            r34 = r40
            r5 = r34
            r34 = 33
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x00a6
            r33 = r3
            int r33 = r33.read()
            r40 = r33
            r33 = r40
            r34 = r40
            r5 = r34
            r34 = 47
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x00ae
        L_0x00a6:
            r33 = r3
            java.lang.String r34 = "'#' is only allowed in initial '#!/PROGRAM'"
            r33.error(r34)
        L_0x00ae:
            r33 = r5
            r34 = 13
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x00cf
            r33 = r5
            r34 = 10
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x00cf
            r33 = r5
            if (r33 < 0) goto L_0x00cf
            r33 = r3
            int r33 = r33.read()
            r5 = r33
            goto L_0x00ae
        L_0x00cf:
            r33 = r3
            int r33 = r33.getRawToken()
            r34 = -1
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x00e3
            r33 = 0
            r3 = r33
            goto L_0x001e
        L_0x00e3:
            r33 = r3
            int r33 = r33.peekOperand()
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 65
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x0124
            java.lang.String r33 = "namespace"
            r34 = r3
            r0 = r34
            java.lang.Object r0 = r0.curValue
            r34 = r0
            java.lang.String r34 = (java.lang.String) r34
            boolean r33 = r33.equals(r34)
            if (r33 == 0) goto L_0x0124
            boolean r33 = warnOldVersion
            if (r33 == 0) goto L_0x011a
            r33 = r3
            r34 = 119(0x77, float:1.67E-43)
            java.lang.String r35 = "use 'declare namespace' instead of 'namespace'"
            r33.error(r34, r35)
        L_0x011a:
            r33 = r3
            r34 = 78
            r0 = r34
            r1 = r33
            r1.curToken = r0
        L_0x0124:
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            switch(r33) {
                case 66: goto L_0x11cc;
                case 69: goto L_0x0a7f;
                case 71: goto L_0x09e0;
                case 72: goto L_0x0e20;
                case 73: goto L_0x06b4;
                case 75: goto L_0x0ca1;
                case 76: goto L_0x0d14;
                case 77: goto L_0x045a;
                case 78: goto L_0x045a;
                case 79: goto L_0x0a7f;
                case 80: goto L_0x01d5;
                case 83: goto L_0x0bf2;
                case 84: goto L_0x06a9;
                case 85: goto L_0x0f9c;
                case 86: goto L_0x0239;
                case 87: goto L_0x017a;
                case 89: goto L_0x100f;
                case 111: goto L_0x0eb2;
                default: goto L_0x012f;
            }
        L_0x012f:
            r33 = r3
            r34 = -1
            r35 = 1
            gnu.expr.Expression r33 = r33.parseExprSequence(r34, r35)
            r15 = r33
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 10
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x0152
            r33 = r3
            r34 = 10
            r33.unread(r34)
        L_0x0152:
            r33 = r3
            r34 = r15
            r35 = r6
            r36 = r7
            r33.maybeSetLine((gnu.expr.Expression) r34, (int) r35, (int) r36)
            r33 = r3
            r0 = r33
            java.lang.String r0 = r0.libraryModuleNamespace
            r33 = r0
            if (r33 == 0) goto L_0x0174
            r33 = r3
            r34 = 101(0x65, float:1.42E-43)
            java.lang.String r35 = "query body in a library module"
            java.lang.String r36 = "XPST0003"
            r33.error(r34, r35, r36)
        L_0x0174:
            r33 = r15
            r3 = r33
            goto L_0x001e
        L_0x017a:
            r33 = r3
            int r33 = r33.getLineNumber()
            r34 = 1
            int r33 = r33 + 1
            r8 = r33
            r33 = r3
            int r33 = r33.getColumnNumber()
            r34 = 1
            int r33 = r33 + 1
            r9 = r33
            r33 = r3
            java.lang.String r34 = "unexpected end-of-file after 'define QName'"
            int r33 = r33.peekNonSpace(r34)
            r10 = r33
            r33 = r10
            r34 = 40
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x01c8
            r33 = r3
            java.lang.String r34 = "'missing 'function' after 'define'"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r33 = r3
            r34 = 65
            r0 = r34
            r1 = r33
            r1.curToken = r0
            r33 = r3
            r34 = r8
            r35 = r9
            gnu.expr.Expression r33 = r33.parseFunctionDefinition(r34, r35)
            r3 = r33
            goto L_0x001e
        L_0x01c8:
            r33 = r3
            java.lang.String r34 = "missing keyword after 'define'"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x01d5:
            r33 = r3
            int r33 = r33.getLineNumber()
            r34 = 1
            int r33 = r33 + 1
            r8 = r33
            r33 = r3
            int r33 = r33.getColumnNumber()
            r34 = 1
            int r33 = r33 + 1
            r9 = r33
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            java.lang.String r34 = "unexpected end-of-file after 'define function'"
            int r33 = r33.peekNonSpace(r34)
            r33 = r3
            r34 = 100
            char r33 = r33.pushNesting(r34)
            r16 = r33
            r33 = r3
            r34 = r8
            r35 = r9
            gnu.expr.Expression r33 = r33.parseFunctionDefinition(r34, r35)
            r15 = r33
            r33 = r3
            r34 = r16
            r33.popNesting(r34)
            r33 = r3
            r33.parseSeparator()
            r33 = r3
            r34 = r15
            r35 = r6
            r36 = r7
            r33.maybeSetLine((gnu.expr.Expression) r34, (int) r35, (int) r36)
            r33 = r3
            r34 = 1
            r0 = r34
            r1 = r33
            r1.seenDeclaration = r0
            r33 = r15
            r3 = r33
            goto L_0x001e
        L_0x0239:
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            gnu.expr.Declaration r33 = r33.parseVariableDeclaration()
            r11 = r33
            r33 = r11
            if (r33 != 0) goto L_0x0258
            r33 = r3
            java.lang.String r34 = "missing Variable"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x0258:
            r33 = r11
            java.lang.Object r33 = r33.getSymbol()
            r17 = r33
            r33 = r17
            r0 = r33
            boolean r0 = r0 instanceof java.lang.String
            r33 = r0
            if (r33 == 0) goto L_0x02a0
            r33 = r3
            gnu.text.SourceMessages r33 = r33.getMessages()
            r34 = r3
            r0 = r34
            gnu.text.LineBufferedReader r0 = r0.port
            r34 = r0
            java.lang.String r34 = r34.getName()
            r35 = r3
            r0 = r35
            int r0 = r0.curLine
            r35 = r0
            r36 = r3
            r0 = r36
            int r0 = r0.curColumn
            r36 = r0
            r33.setLine(r34, r35, r36)
            r33 = r11
            r34 = r3
            r35 = r17
            java.lang.String r35 = (java.lang.String) r35
            r36 = 0
            gnu.mapping.Symbol r34 = r34.namespaceResolve(r35, r36)
            r33.setSymbol(r34)
        L_0x02a0:
            r33 = r3
            r0 = r33
            java.lang.String r0 = r0.libraryModuleNamespace
            r33 = r0
            if (r33 == 0) goto L_0x02e8
            r33 = r11
            java.lang.Object r33 = r33.getSymbol()
            gnu.mapping.Symbol r33 = (gnu.mapping.Symbol) r33
            java.lang.String r33 = r33.getNamespaceURI()
            r13 = r33
            r33 = r13
            r34 = r3
            r0 = r34
            java.lang.String r0 = r0.libraryModuleNamespace
            r34 = r0
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x02e8
            java.lang.String r33 = "http://www.w3.org/2005/xquery-local-functions"
            r34 = r13
            boolean r33 = r33.equals(r34)
            if (r33 == 0) goto L_0x02db
            r33 = r4
            boolean r33 = r33.isPedantic()
            if (r33 == 0) goto L_0x02e8
        L_0x02db:
            r33 = r3
            r34 = 101(0x65, float:1.42E-43)
            java.lang.String r35 = "variable not in namespace of library module"
            java.lang.String r36 = "XQST0048"
            r33.error(r34, r35, r36)
        L_0x02e8:
            r33 = r4
            gnu.expr.ScopeExp r33 = r33.currentScope()
            r34 = r11
            r33.addDeclaration((gnu.expr.Declaration) r34)
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            gnu.expr.Expression r33 = r33.parseOptionalTypeDeclaration()
            r18 = r33
            r33 = r11
            r34 = 1
            r33.setCanRead(r34)
            r33 = r11
            r34 = 16384(0x4000, double:8.0948E-320)
            r33.setFlag(r34)
            r33 = 0
            r19 = r33
            r33 = 0
            r20 = r33
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 402(0x192, float:5.63E-43)
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x0337
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 76
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x0359
        L_0x0337:
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 402(0x192, float:5.63E-43)
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x034f
            r33 = r3
            java.lang.String r34 = "declare variable contains '=' instead of ':='"
            r33.error(r34)
        L_0x034f:
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = 1
            r20 = r33
        L_0x0359:
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 123(0x7b, float:1.72E-43)
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x03b8
            r33 = r3
            java.lang.String r34 = "obsolete '{' in variable declaration"
            r33.warnOldVersion(r34)
            r33 = r3
            gnu.expr.Expression r33 = r33.parseEnclosedExpr()
            r19 = r33
            r33 = r3
            r33.parseSeparator()
        L_0x037e:
            r33 = r18
            if (r33 == 0) goto L_0x038c
            r33 = r19
            r34 = r18
            gnu.expr.ApplyExp r33 = gnu.expr.Compilation.makeCoercion((gnu.expr.Expression) r33, (gnu.expr.Expression) r34)
            r19 = r33
        L_0x038c:
            r33 = r11
            r34 = r19
            r33.noteValue(r34)
            r33 = r11
            r34 = r19
            gnu.expr.SetExp r33 = gnu.expr.SetExp.makeDefinition((gnu.expr.Declaration) r33, (gnu.expr.Expression) r34)
            r15 = r33
            r33 = r3
            r34 = r15
            r35 = r6
            r36 = r7
            r33.maybeSetLine((gnu.expr.Expression) r34, (int) r35, (int) r36)
            r33 = r3
            r34 = 1
            r0 = r34
            r1 = r33
            r1.seenDeclaration = r0
            r33 = r15
            r3 = r33
            goto L_0x001e
        L_0x03b8:
            r33 = r3
            java.lang.String r34 = "external"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0431
            r33 = 2
            r0 = r33
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r33 = r0
            r40 = r33
            r33 = r40
            r34 = r40
            r35 = 0
            gnu.expr.QuoteExp r36 = new gnu.expr.QuoteExp
            r40 = r36
            r36 = r40
            r37 = r40
            r38 = r11
            java.lang.Object r38 = r38.getSymbol()
            r37.<init>(r38)
            r37 = 0
            gnu.expr.ApplyExp r36 = castQName(r36, r37)
            r34[r35] = r36
            r40 = r33
            r33 = r40
            r34 = r40
            r35 = 1
            r36 = r18
            if (r36 != 0) goto L_0x042e
            gnu.expr.QuoteExp r36 = gnu.expr.QuoteExp.nullExp
        L_0x03fa:
            r34[r35] = r36
            r21 = r33
            gnu.expr.ApplyExp r33 = new gnu.expr.ApplyExp
            r40 = r33
            r33 = r40
            r34 = r40
            gnu.expr.QuoteExp r35 = getExternalFunction
            r36 = r21
            r34.<init>((gnu.expr.Expression) r35, (gnu.expr.Expression[]) r36)
            r19 = r33
            r33 = r3
            r34 = r19
            r35 = r3
            r0 = r35
            int r0 = r0.curLine
            r35 = r0
            r36 = r3
            r0 = r36
            int r0 = r0.curColumn
            r36 = r0
            r33.maybeSetLine((gnu.expr.Expression) r34, (int) r35, (int) r36)
            r33 = r3
            int r33 = r33.getRawToken()
            goto L_0x037e
        L_0x042e:
            r36 = r18
            goto L_0x03fa
        L_0x0431:
            r33 = r3
            gnu.expr.Expression r33 = r33.parseExpr()
            r19 = r33
            r33 = 0
            r21 = r33
            r33 = r20
            if (r33 == 0) goto L_0x0445
            r33 = r19
            if (r33 != 0) goto L_0x0450
        L_0x0445:
            r33 = r3
            java.lang.String r34 = "expected ':= init' or 'external'"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r21 = r33
        L_0x0450:
            r33 = r19
            if (r33 != 0) goto L_0x037e
            r33 = r21
            r19 = r33
            goto L_0x037e
        L_0x045a:
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r21 = r33
            r33 = r21
            r34 = 77
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x04cf
            r33 = r3
            r0 = r33
            java.lang.String r0 = r0.libraryModuleNamespace
            r33 = r0
            if (r33 == 0) goto L_0x04cf
            r33 = r3
            r34 = 101(0x65, float:1.42E-43)
            java.lang.String r35 = "duplicate module declaration"
            r33.error(r34, r35)
        L_0x0482:
            r33 = r3
            r34 = r3
            r0 = r34
            int r0 = r0.nesting
            r34 = r0
            if (r34 == 0) goto L_0x04ee
            r34 = 1
        L_0x0490:
            int r33 = r33.skipSpace(r34)
            r10 = r33
            r33 = r10
            if (r33 < 0) goto L_0x06a9
            r33 = r3
            r33.unread()
            r33 = r10
            r0 = r33
            char r0 = (char) r0
            r33 = r0
            boolean r33 = gnu.xml.XName.isNameStart(r33)
            if (r33 == 0) goto L_0x06a9
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 65
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x04f1
            r33 = r3
            java.lang.String r34 = "missing namespace prefix"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x04cf:
            r33 = r3
            r0 = r33
            boolean r0 = r0.seenDeclaration
            r33 = r0
            if (r33 == 0) goto L_0x0482
            r33 = r3
            r0 = r33
            boolean r0 = r0.interactive
            r33 = r0
            if (r33 != 0) goto L_0x0482
            r33 = r3
            r34 = 101(0x65, float:1.42E-43)
            java.lang.String r35 = "namespace declared after function/variable/option"
            r33.error(r34, r35)
            goto L_0x0482
        L_0x04ee:
            r34 = 0
            goto L_0x0490
        L_0x04f1:
            java.lang.String r33 = new java.lang.String
            r40 = r33
            r33 = r40
            r34 = r40
            r35 = r3
            r0 = r35
            char[] r0 = r0.tokenBuffer
            r35 = r0
            r36 = 0
            r37 = r3
            r0 = r37
            int r0 = r0.tokenBufferLength
            r37 = r0
            r34.<init>(r35, r36, r37)
            r12 = r33
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 402(0x192, float:5.63E-43)
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x0533
            r33 = r3
            java.lang.String r34 = "missing '=' in namespace declaration"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x0533:
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 34
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x0556
            r33 = r3
            java.lang.String r34 = "missing uri in namespace declaration"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x0556:
            java.lang.String r33 = new java.lang.String
            r40 = r33
            r33 = r40
            r34 = r40
            r35 = r3
            r0 = r35
            char[] r0 = r0.tokenBuffer
            r35 = r0
            r36 = 0
            r37 = r3
            r0 = r37
            int r0 = r0.tokenBufferLength
            r37 = r0
            r34.<init>(r35, r36, r37)
            java.lang.String r33 = r33.intern()
            r13 = r33
            r33 = r12
            java.lang.String r33 = r33.intern()
            r12 = r33
            r33 = r3
            r0 = r33
            gnu.xml.NamespaceBinding r0 = r0.prologNamespaces
            r33 = r0
            r22 = r33
        L_0x058b:
            r33 = r22
            gnu.xml.NamespaceBinding r34 = builtinNamespaces
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x05d0
            r33 = r22
            java.lang.String r33 = r33.getPrefix()
            r34 = r12
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x068f
            r33 = r3
            r34 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r35 = new java.lang.StringBuilder
            r40 = r35
            r35 = r40
            r36 = r40
            r36.<init>()
            java.lang.String r36 = "duplicate declarations for the same namespace prefix '"
            java.lang.StringBuilder r35 = r35.append(r36)
            r36 = r12
            java.lang.StringBuilder r35 = r35.append(r36)
            java.lang.String r36 = "'"
            java.lang.StringBuilder r35 = r35.append(r36)
            java.lang.String r35 = r35.toString()
            java.lang.String r36 = "XQST0033"
            r33.error(r34, r35, r36)
        L_0x05d0:
            r33 = r3
            r34 = r12
            r35 = r13
            r33.pushNamespace(r34, r35)
            r33 = r3
            r34 = r12
            r35 = r13
            r36 = 0
            r33.checkAllowedNamespaceDeclaration(r34, r35, r36)
            r33 = r3
            r33.parseSeparator()
            r33 = r21
            r34 = 77
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x06a3
            r33 = r4
            gnu.expr.ModuleExp r33 = r33.getModule()
            r22 = r33
            java.lang.StringBuilder r33 = new java.lang.StringBuilder
            r40 = r33
            r33 = r40
            r34 = r40
            r34.<init>()
            r34 = r13
            java.lang.String r34 = gnu.expr.Compilation.mangleURI(r34)
            java.lang.StringBuilder r33 = r33.append(r34)
            r34 = 46
            java.lang.StringBuilder r33 = r33.append(r34)
            r34 = r22
            java.lang.String r34 = r34.getFileName()
            java.lang.String r34 = gnu.xquery.lang.XQuery.makeClassName(r34)
            java.lang.StringBuilder r33 = r33.append(r34)
            java.lang.String r33 = r33.toString()
            r23 = r33
            r33 = r22
            r34 = r23
            r33.setName(r34)
            r33 = r4
            gnu.bytecode.ClassType r34 = new gnu.bytecode.ClassType
            r40 = r34
            r34 = r40
            r35 = r40
            r36 = r23
            r35.<init>(r36)
            r0 = r34
            r1 = r33
            r1.mainClass = r0
            r33 = r22
            r34 = r4
            r0 = r34
            gnu.bytecode.ClassType r0 = r0.mainClass
            r34 = r0
            r33.setType(r34)
            gnu.expr.ModuleManager r33 = gnu.expr.ModuleManager.getInstance()
            r24 = r33
            r33 = r24
            r34 = r4
            gnu.expr.ModuleInfo r33 = r33.find(r34)
            r25 = r33
            r33 = r25
            r34 = r13
            r33.setNamespaceUri(r34)
            r33 = r22
            r34 = r4
            r0 = r34
            gnu.bytecode.ClassType r0 = r0.mainClass
            r34 = r0
            r33.setType(r34)
            r33 = r13
            int r33 = r33.length()
            if (r33 != 0) goto L_0x0699
            r33 = r3
            java.lang.String r34 = "zero-length module namespace"
            java.lang.String r35 = "XQST0088"
            gnu.expr.Expression r33 = r33.syntaxError(r34, r35)
            r3 = r33
            goto L_0x001e
        L_0x068f:
            r33 = r22
            gnu.xml.NamespaceBinding r33 = r33.getNext()
            r22 = r33
            goto L_0x058b
        L_0x0699:
            r33 = r3
            r34 = r13
            r0 = r34
            r1 = r33
            r1.libraryModuleNamespace = r0
        L_0x06a3:
            gnu.expr.QuoteExp r33 = gnu.expr.QuoteExp.voidExp
            r3 = r33
            goto L_0x001e
        L_0x06a9:
            r33 = r3
            java.lang.String r34 = "'import schema' not implemented"
            java.lang.String r35 = "XQST0009"
            r33.fatal(r34, r35)
        L_0x06b4:
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = 0
            r12 = r33
            r33 = r3
            java.lang.String r34 = "namespace"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0734
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 65
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x06ec
            r33 = r3
            java.lang.String r34 = "missing namespace prefix"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x06ec:
            java.lang.String r33 = new java.lang.String
            r40 = r33
            r33 = r40
            r34 = r40
            r35 = r3
            r0 = r35
            char[] r0 = r0.tokenBuffer
            r35 = r0
            r36 = 0
            r37 = r3
            r0 = r37
            int r0 = r0.tokenBufferLength
            r37 = r0
            r34.<init>(r35, r36, r37)
            r12 = r33
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 402(0x192, float:5.63E-43)
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x072e
            r33 = r3
            java.lang.String r34 = "missing '=' in namespace declaration"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x072e:
            r33 = r3
            int r33 = r33.getRawToken()
        L_0x0734:
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 34
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x0751
            r33 = r3
            java.lang.String r34 = "missing uri in namespace declaration"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x0751:
            r33 = r3
            r0 = r33
            int r0 = r0.tokenBufferLength
            r33 = r0
            if (r33 != 0) goto L_0x076b
            r33 = r3
            java.lang.String r34 = "zero-length target namespace"
            java.lang.String r35 = "XQST0088"
            gnu.expr.Expression r33 = r33.syntaxError(r34, r35)
            r3 = r33
            goto L_0x001e
        L_0x076b:
            java.lang.String r33 = new java.lang.String
            r40 = r33
            r33 = r40
            r34 = r40
            r35 = r3
            r0 = r35
            char[] r0 = r0.tokenBuffer
            r35 = r0
            r36 = 0
            r37 = r3
            r0 = r37
            int r0 = r0.tokenBufferLength
            r37 = r0
            r34.<init>(r35, r36, r37)
            java.lang.String r33 = r33.intern()
            r13 = r33
            r33 = r12
            if (r33 == 0) goto L_0x07aa
            r33 = r3
            r34 = r12
            r35 = r13
            r36 = 0
            r33.checkAllowedNamespaceDeclaration(r34, r35, r36)
            r33 = r3
            r34 = r12
            java.lang.String r34 = r34.intern()
            r35 = r13
            r33.pushNamespace(r34, r35)
        L_0x07aa:
            r33 = r3
            int r33 = r33.getRawToken()
            gnu.expr.ModuleManager r33 = gnu.expr.ModuleManager.getInstance()
            r34 = r4
            gnu.expr.ModuleInfo r33 = r33.find(r34)
            r33 = r4
            gnu.expr.ModuleExp r33 = r33.getModule()
            r23 = r33
            java.util.Vector r33 = new java.util.Vector
            r40 = r33
            r33 = r40
            r34 = r40
            r34.<init>()
            r24 = r33
            r33 = r13
            java.lang.String r33 = gnu.expr.Compilation.mangleURI(r33)
            r25 = r33
            r33 = r4
            r34 = r3
            r0 = r34
            gnu.text.LineBufferedReader r0 = r0.port
            r34 = r0
            java.lang.String r34 = r34.getName()
            r35 = r6
            r36 = r7
            r33.setLine(r34, r35, r36)
            r33 = r3
            java.lang.String r34 = "at"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0919
        L_0x07f7:
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 34
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x081a
            r33 = r3
            java.lang.String r34 = "missing module location"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x081a:
            java.lang.String r33 = new java.lang.String
            r40 = r33
            r33 = r40
            r34 = r40
            r35 = r3
            r0 = r35
            char[] r0 = r0.tokenBuffer
            r35 = r0
            r36 = 0
            r37 = r3
            r0 = r37
            int r0 = r0.tokenBufferLength
            r37 = r0
            r34.<init>(r35, r36, r37)
            r22 = r33
            java.lang.StringBuilder r33 = new java.lang.StringBuilder
            r40 = r33
            r33 = r40
            r34 = r40
            r34.<init>()
            r34 = r13
            java.lang.String r34 = gnu.expr.Compilation.mangleURI(r34)
            java.lang.StringBuilder r33 = r33.append(r34)
            r34 = 46
            java.lang.StringBuilder r33 = r33.append(r34)
            r34 = r22
            java.lang.String r34 = gnu.xquery.lang.XQuery.makeClassName(r34)
            java.lang.StringBuilder r33 = r33.append(r34)
            java.lang.String r33 = r33.toString()
            r26 = r33
            r33 = r22
            r34 = r23
            gnu.expr.ModuleInfo r33 = kawa.standard.require.lookupModuleFromSourcePath(r33, r34)
            r27 = r33
            r33 = r27
            if (r33 != 0) goto L_0x0895
            r33 = r4
            r34 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r35 = new java.lang.StringBuilder
            r40 = r35
            r35 = r40
            r36 = r40
            r36.<init>()
            java.lang.String r36 = "malformed URL: "
            java.lang.StringBuilder r35 = r35.append(r36)
            r36 = r22
            java.lang.StringBuilder r35 = r35.append(r36)
            java.lang.String r35 = r35.toString()
            r33.error(r34, r35)
        L_0x0895:
            r33 = r26
            r34 = r27
            r35 = 0
            r36 = r24
            r37 = r23
            r38 = r4
            boolean r33 = kawa.standard.require.importDefinitions(r33, r34, r35, r36, r37, r38)
            r33 = r3
            r34 = r3
            r0 = r34
            int r0 = r0.nesting
            r34 = r0
            if (r34 == 0) goto L_0x0914
            r34 = 1
        L_0x08b3:
            int r33 = r33.skipSpace(r34)
            r10 = r33
            r33 = r10
            r34 = 44
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x0917
            r33 = r3
            r34 = r10
            r33.unread(r34)
            r33 = r3
            r33.parseSeparator()
        L_0x08cf:
            r33 = r4
            r0 = r33
            java.util.Stack<java.lang.Object> r0 = r0.pendingImports
            r33 = r0
            if (r33 == 0) goto L_0x08f4
            r33 = r4
            r0 = r33
            java.util.Stack<java.lang.Object> r0 = r0.pendingImports
            r33 = r0
            int r33 = r33.size()
            if (r33 <= 0) goto L_0x08f4
            r33 = r3
            r34 = 101(0x65, float:1.42E-43)
            java.lang.String r35 = "module import forms a cycle"
            java.lang.String r36 = "XQST0073"
            r33.error(r34, r35, r36)
        L_0x08f4:
            r33 = r24
            int r33 = r33.size()
            r0 = r33
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r33 = r0
            r26 = r33
            r33 = r24
            r34 = r26
            java.lang.Object[] r33 = r33.toArray(r34)
            r33 = r26
            gnu.expr.Expression r33 = gnu.expr.BeginExp.canonicalize((gnu.expr.Expression[]) r33)
            r3 = r33
            goto L_0x001e
        L_0x0914:
            r34 = 0
            goto L_0x08b3
        L_0x0917:
            goto L_0x07f7
        L_0x0919:
            gnu.expr.ModuleManager r33 = gnu.expr.ModuleManager.getInstance()
            r26 = r33
            r33 = 0
            r27 = r33
            r33 = r26
            r34 = r25
            r33.loadPackageInfo(r34)     // Catch:{ ClassNotFoundException -> 0x097e, Throwable -> 0x0982 }
        L_0x092a:
            r33 = 0
            r28 = r33
        L_0x092e:
            r33 = r26
            r34 = r28
            gnu.expr.ModuleInfo r33 = r33.getModule(r34)
            r29 = r33
            r33 = r29
            if (r33 != 0) goto L_0x09b7
            r33 = r27
            if (r33 != 0) goto L_0x0963
            r33 = r3
            r34 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r35 = new java.lang.StringBuilder
            r40 = r35
            r35 = r40
            r36 = r40
            r36.<init>()
            java.lang.String r36 = "no module found for "
            java.lang.StringBuilder r35 = r35.append(r36)
            r36 = r13
            java.lang.StringBuilder r35 = r35.append(r36)
            java.lang.String r35 = r35.toString()
            r33.error(r34, r35)
        L_0x0963:
            r33 = 0
            r22 = r33
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 59
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x08cf
            r33 = r3
            r33.parseSeparator()
            goto L_0x08cf
        L_0x097e:
            r33 = move-exception
            r28 = r33
            goto L_0x092a
        L_0x0982:
            r33 = move-exception
            r28 = r33
            r33 = r3
            r34 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r35 = new java.lang.StringBuilder
            r40 = r35
            r35 = r40
            r36 = r40
            r36.<init>()
            java.lang.String r36 = "error loading map for "
            java.lang.StringBuilder r35 = r35.append(r36)
            r36 = r13
            java.lang.StringBuilder r35 = r35.append(r36)
            java.lang.String r36 = " - "
            java.lang.StringBuilder r35 = r35.append(r36)
            r36 = r28
            java.lang.StringBuilder r35 = r35.append(r36)
            java.lang.String r35 = r35.toString()
            r33.error(r34, r35)
            goto L_0x092a
        L_0x09b7:
            r33 = r13
            r34 = r29
            java.lang.String r34 = r34.getNamespaceUri()
            boolean r33 = r33.equals(r34)
            if (r33 != 0) goto L_0x09c9
        L_0x09c5:
            int r28 = r28 + 1
            goto L_0x092e
        L_0x09c9:
            int r27 = r27 + 1
            r33 = r29
            java.lang.String r33 = r33.getClassName()
            r34 = r29
            r35 = 0
            r36 = r24
            r37 = r23
            r38 = r4
            boolean r33 = kawa.standard.require.importDefinitions(r33, r34, r35, r36, r37, r38)
            goto L_0x09c5
        L_0x09e0:
            r33 = r3
            r0 = r33
            gnu.xquery.util.NamedCollator r0 = r0.defaultCollator
            r33 = r0
            if (r33 == 0) goto L_0x0a01
            r33 = r3
            r0 = r33
            boolean r0 = r0.interactive
            r33 = r0
            if (r33 != 0) goto L_0x0a01
            r33 = r3
            r34 = 101(0x65, float:1.42E-43)
            java.lang.String r35 = "duplicate default collation declaration"
            java.lang.String r36 = "XQST0038"
            r33.error(r34, r35, r36)
        L_0x0a01:
            r33 = r3
            java.lang.Object r33 = r33.parseURILiteral()
            r14 = r33
            r33 = r14
            r0 = r33
            boolean r0 = r0 instanceof gnu.expr.Expression
            r33 = r0
            if (r33 == 0) goto L_0x0a1b
            r33 = r14
            gnu.expr.Expression r33 = (gnu.expr.Expression) r33
            r3 = r33
            goto L_0x001e
        L_0x0a1b:
            r33 = r14
            java.lang.String r33 = (java.lang.String) r33
            r27 = r33
            r33 = r3
            r34 = r27
            java.lang.String r33 = r33.resolveAgainstBaseUri(r34)     // Catch:{ Exception -> 0x0a44 }
            r27 = r33
            r33 = r3
            r34 = r27
            gnu.xquery.util.NamedCollator r34 = gnu.xquery.util.NamedCollator.make(r34)     // Catch:{ Exception -> 0x0a44 }
            r0 = r34
            r1 = r33
            r1.defaultCollator = r0     // Catch:{ Exception -> 0x0a44 }
        L_0x0a39:
            r33 = r3
            r33.parseSeparator()
            gnu.expr.QuoteExp r33 = gnu.expr.QuoteExp.voidExp
            r3 = r33
            goto L_0x001e
        L_0x0a44:
            r33 = move-exception
            r28 = r33
            r33 = r3
            gnu.xquery.util.NamedCollator r34 = gnu.xquery.util.NamedCollator.codepointCollation
            r0 = r34
            r1 = r33
            r1.defaultCollator = r0
            r33 = r3
            r34 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r35 = new java.lang.StringBuilder
            r40 = r35
            r35 = r40
            r36 = r40
            r36.<init>()
            java.lang.String r36 = "unknown collation '"
            java.lang.StringBuilder r35 = r35.append(r36)
            r36 = r27
            java.lang.StringBuilder r35 = r35.append(r36)
            java.lang.String r36 = "'"
            java.lang.StringBuilder r35 = r35.append(r36)
            java.lang.String r35 = r35.toString()
            java.lang.String r36 = "XQST0038"
            r33.error(r34, r35, r36)
            goto L_0x0a39
        L_0x0a7f:
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 79
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x0b1d
            r33 = 1
        L_0x0a91:
            r28 = r33
            r33 = r28
            if (r33 == 0) goto L_0x0b21
            java.lang.String r33 = "(functions)"
        L_0x0a9a:
            r12 = r33
            r33 = r3
            r0 = r33
            gnu.xml.NamespaceBinding r0 = r0.prologNamespaces
            r33 = r0
            r34 = r12
            gnu.xml.NamespaceBinding r35 = builtinNamespaces
            java.lang.String r33 = r33.resolve(r34, r35)
            if (r33 == 0) goto L_0x0b25
            r33 = r3
            r34 = 101(0x65, float:1.42E-43)
            java.lang.String r35 = "duplicate default namespace declaration"
            java.lang.String r36 = "XQST0066"
            r33.error(r34, r35, r36)
        L_0x0abb:
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            java.lang.String r34 = "namespace"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0b45
            r33 = r3
            int r33 = r33.getRawToken()
        L_0x0ad2:
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 402(0x192, float:5.63E-43)
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x0af2
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 76
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x0b00
        L_0x0af2:
            r33 = r3
            java.lang.String r34 = "extra '=' in default namespace declaration"
            r33.warnOldVersion(r34)
            r33 = r3
            int r33 = r33.getRawToken()
        L_0x0b00:
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 34
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x0b7f
            r33 = r3
            java.lang.String r34 = "missing namespace uri"
            gnu.expr.Expression r33 = r33.declError(r34)
            r3 = r33
            goto L_0x001e
        L_0x0b1d:
            r33 = 0
            goto L_0x0a91
        L_0x0b21:
            java.lang.String r33 = gnu.xquery.lang.XQuery.DEFAULT_ELEMENT_PREFIX
            goto L_0x0a9a
        L_0x0b25:
            r33 = r3
            r0 = r33
            boolean r0 = r0.seenDeclaration
            r33 = r0
            if (r33 == 0) goto L_0x0abb
            r33 = r3
            r0 = r33
            boolean r0 = r0.interactive
            r33 = r0
            if (r33 != 0) goto L_0x0abb
            r33 = r3
            r34 = 101(0x65, float:1.42E-43)
            java.lang.String r35 = "default namespace declared after function/variable/option"
            r33.error(r34, r35)
            goto L_0x0abb
        L_0x0b45:
            java.lang.String r33 = "expected 'namespace' keyword"
            r29 = r33
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 34
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x0b76
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 402(0x192, float:5.63E-43)
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x0b76
            r33 = r3
            r34 = r29
            gnu.expr.Expression r33 = r33.declError(r34)
            r3 = r33
            goto L_0x001e
        L_0x0b76:
            r33 = r3
            r34 = r29
            r33.warnOldVersion(r34)
            goto L_0x0ad2
        L_0x0b7f:
            java.lang.String r33 = new java.lang.String
            r40 = r33
            r33 = r40
            r34 = r40
            r35 = r3
            r0 = r35
            char[] r0 = r0.tokenBuffer
            r35 = r0
            r36 = 0
            r37 = r3
            r0 = r37
            int r0 = r0.tokenBufferLength
            r37 = r0
            r34.<init>(r35, r36, r37)
            java.lang.String r33 = r33.intern()
            r13 = r33
            r33 = r28
            if (r33 == 0) goto L_0x0be7
            r33 = r3
            r34 = 1
            r0 = r34
            gnu.mapping.Namespace[] r0 = new gnu.mapping.Namespace[r0]
            r34 = r0
            r0 = r34
            r1 = r33
            r1.functionNamespacePath = r0
            r33 = r3
            r0 = r33
            gnu.mapping.Namespace[] r0 = r0.functionNamespacePath
            r33 = r0
            r34 = 0
            r35 = r13
            gnu.mapping.Namespace r35 = gnu.mapping.Namespace.valueOf(r35)
            r33[r34] = r35
        L_0x0bc8:
            r33 = r3
            r34 = r12
            r35 = r13
            r33.pushNamespace(r34, r35)
            r33 = r3
            r34 = r12
            r35 = r13
            r36 = 0
            r33.checkAllowedNamespaceDeclaration(r34, r35, r36)
            r33 = r3
            r33.parseSeparator()
            gnu.expr.QuoteExp r33 = gnu.expr.QuoteExp.voidExp
            r3 = r33
            goto L_0x001e
        L_0x0be7:
            r33 = r3
            r34 = r13
            r0 = r34
            r1 = r33
            r1.defaultElementNamespace = r0
            goto L_0x0bc8
        L_0x0bf2:
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 402(0x192, float:5.63E-43)
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x0c16
            r33 = r3
            java.lang.String r34 = "obsolate '=' in boundary-space declaration"
            r33.warnOldVersion(r34)
            r33 = r3
            int r33 = r33.getRawToken()
        L_0x0c16:
            r33 = r3
            r0 = r33
            boolean r0 = r0.boundarySpaceDeclarationSeen
            r33 = r0
            if (r33 == 0) goto L_0x0c36
            r33 = r3
            r0 = r33
            boolean r0 = r0.interactive
            r33 = r0
            if (r33 != 0) goto L_0x0c36
            r33 = r3
            java.lang.String r34 = "duplicate 'declare boundary-space' seen"
            java.lang.String r35 = "XQST0068"
            gnu.expr.Expression r33 = r33.syntaxError(r34, r35)
        L_0x0c36:
            r33 = r3
            r34 = 1
            r0 = r34
            r1 = r33
            r1.boundarySpaceDeclarationSeen = r0
            r33 = r3
            java.lang.String r34 = "preserve"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0c60
            r33 = r3
            r34 = 1
            r0 = r34
            r1 = r33
            r1.boundarySpacePreserve = r0
        L_0x0c55:
            r33 = r3
            r33.parseSeparator()
            gnu.expr.QuoteExp r33 = gnu.expr.QuoteExp.voidExp
            r3 = r33
            goto L_0x001e
        L_0x0c60:
            r33 = r3
            java.lang.String r34 = "strip"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0c76
            r33 = r3
            r34 = 0
            r0 = r34
            r1 = r33
            r1.boundarySpacePreserve = r0
            goto L_0x0c55
        L_0x0c76:
            r33 = r3
            java.lang.String r34 = "skip"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0c94
            r33 = r3
            java.lang.String r34 = "update: declare boundary-space skip -> strip"
            r33.warnOldVersion(r34)
            r33 = r3
            r34 = 0
            r0 = r34
            r1 = r33
            r1.boundarySpacePreserve = r0
            goto L_0x0c55
        L_0x0c94:
            r33 = r3
            java.lang.String r34 = "boundary-space declaration must be preserve or strip"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x0ca1:
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            r0 = r33
            boolean r0 = r0.constructionModeDeclarationSeen
            r33 = r0
            if (r33 == 0) goto L_0x0cc7
            r33 = r3
            r0 = r33
            boolean r0 = r0.interactive
            r33 = r0
            if (r33 != 0) goto L_0x0cc7
            r33 = r3
            java.lang.String r34 = "duplicate 'declare construction' seen"
            java.lang.String r35 = "XQST0067"
            gnu.expr.Expression r33 = r33.syntaxError(r34, r35)
        L_0x0cc7:
            r33 = r3
            r34 = 1
            r0 = r34
            r1 = r33
            r1.constructionModeDeclarationSeen = r0
            r33 = r3
            java.lang.String r34 = "strip"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0cf1
            r33 = r3
            r34 = 1
            r0 = r34
            r1 = r33
            r1.constructionModeStrip = r0
        L_0x0ce6:
            r33 = r3
            r33.parseSeparator()
            gnu.expr.QuoteExp r33 = gnu.expr.QuoteExp.voidExp
            r3 = r33
            goto L_0x001e
        L_0x0cf1:
            r33 = r3
            java.lang.String r34 = "preserve"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0d07
            r33 = r3
            r34 = 0
            r0 = r34
            r1 = r33
            r1.constructionModeStrip = r0
            goto L_0x0ce6
        L_0x0d07:
            r33 = r3
            java.lang.String r34 = "construction declaration must be strip or preserve"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x0d14:
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            r0 = r33
            boolean r0 = r0.copyNamespacesDeclarationSeen
            r33 = r0
            if (r33 == 0) goto L_0x0d3a
            r33 = r3
            r0 = r33
            boolean r0 = r0.interactive
            r33 = r0
            if (r33 != 0) goto L_0x0d3a
            r33 = r3
            java.lang.String r34 = "duplicate 'declare copy-namespaces' seen"
            java.lang.String r35 = "XQST0055"
            gnu.expr.Expression r33 = r33.syntaxError(r34, r35)
        L_0x0d3a:
            r33 = r3
            r34 = 1
            r0 = r34
            r1 = r33
            r1.copyNamespacesDeclarationSeen = r0
            r33 = r3
            java.lang.String r34 = "preserve"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0d8a
            r33 = r3
            r40 = r33
            r33 = r40
            r34 = r40
            r0 = r34
            int r0 = r0.copyNamespacesMode
            r34 = r0
            r35 = 1
            r34 = r34 | 1
            r0 = r34
            r1 = r33
            r1.copyNamespacesMode = r0
        L_0x0d67:
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 44
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x0dbb
            r33 = r3
            java.lang.String r34 = "missing ',' in copy-namespaces declaration"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x0d8a:
            r33 = r3
            java.lang.String r34 = "no-preserve"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0dae
            r33 = r3
            r40 = r33
            r33 = r40
            r34 = r40
            r0 = r34
            int r0 = r0.copyNamespacesMode
            r34 = r0
            r35 = -2
            r34 = r34 & -2
            r0 = r34
            r1 = r33
            r1.copyNamespacesMode = r0
            goto L_0x0d67
        L_0x0dae:
            r33 = r3
            java.lang.String r34 = "expected 'preserve' or 'no-preserve' after 'declare copy-namespaces'"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x0dbb:
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            java.lang.String r34 = "inherit"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0def
            r33 = r3
            r40 = r33
            r33 = r40
            r34 = r40
            r0 = r34
            int r0 = r0.copyNamespacesMode
            r34 = r0
            r35 = 2
            r34 = r34 | 2
            r0 = r34
            r1 = r33
            r1.copyNamespacesMode = r0
        L_0x0de4:
            r33 = r3
            r33.parseSeparator()
            gnu.expr.QuoteExp r33 = gnu.expr.QuoteExp.voidExp
            r3 = r33
            goto L_0x001e
        L_0x0def:
            r33 = r3
            java.lang.String r34 = "no-inherit"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0e13
            r33 = r3
            r40 = r33
            r33 = r40
            r34 = r40
            r0 = r34
            int r0 = r0.copyNamespacesMode
            r34 = r0
            r35 = -3
            r34 = r34 & -3
            r0 = r34
            r1 = r33
            r1.copyNamespacesMode = r0
            goto L_0x0de4
        L_0x0e13:
            r33 = r3
            java.lang.String r34 = "expected 'inherit' or 'no-inherit' in copy-namespaces declaration"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x0e20:
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            java.lang.String r34 = "empty"
            boolean r33 = r33.match(r34)
            r29 = r33
            r33 = r3
            r0 = r33
            boolean r0 = r0.emptyOrderDeclarationSeen
            r33 = r0
            if (r33 == 0) goto L_0x0e51
            r33 = r3
            r0 = r33
            boolean r0 = r0.interactive
            r33 = r0
            if (r33 != 0) goto L_0x0e51
            r33 = r3
            java.lang.String r34 = "duplicate 'declare default empty order' seen"
            java.lang.String r35 = "XQST0069"
            gnu.expr.Expression r33 = r33.syntaxError(r34, r35)
        L_0x0e51:
            r33 = r3
            r34 = 1
            r0 = r34
            r1 = r33
            r1.emptyOrderDeclarationSeen = r0
            r33 = r29
            if (r33 == 0) goto L_0x0e85
            r33 = r3
            int r33 = r33.getRawToken()
        L_0x0e65:
            r33 = r3
            java.lang.String r34 = "greatest"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0e8f
            r33 = r3
            r34 = 71
            r0 = r34
            r1 = r33
            r1.defaultEmptyOrder = r0
        L_0x0e7a:
            r33 = r3
            r33.parseSeparator()
            gnu.expr.QuoteExp r33 = gnu.expr.QuoteExp.voidExp
            r3 = r33
            goto L_0x001e
        L_0x0e85:
            r33 = r3
            java.lang.String r34 = "expected 'empty greatest' or 'empty least'"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            goto L_0x0e65
        L_0x0e8f:
            r33 = r3
            java.lang.String r34 = "least"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0ea5
            r33 = r3
            r34 = 76
            r0 = r34
            r1 = r33
            r1.defaultEmptyOrder = r0
            goto L_0x0e7a
        L_0x0ea5:
            r33 = r3
            java.lang.String r34 = "expected 'empty greatest' or 'empty least'"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x0eb2:
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 81
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x0ee6
            r33 = r3
            java.lang.String r34 = "expected QName after 'declare option'"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
        L_0x0ed1:
            r33 = r3
            r33.parseSeparator()
            r33 = r3
            r34 = 1
            r0 = r34
            r1 = r33
            r1.seenDeclaration = r0
            gnu.expr.QuoteExp r33 = gnu.expr.QuoteExp.voidExp
            r3 = r33
            goto L_0x001e
        L_0x0ee6:
            java.lang.String r33 = new java.lang.String
            r40 = r33
            r33 = r40
            r34 = r40
            r35 = r3
            r0 = r35
            char[] r0 = r0.tokenBuffer
            r35 = r0
            r36 = 0
            r37 = r3
            r0 = r37
            int r0 = r0.tokenBufferLength
            r37 = r0
            r34.<init>(r35, r36, r37)
            r30 = r33
            r33 = r3
            gnu.text.SourceMessages r33 = r33.getMessages()
            r34 = r3
            r0 = r34
            gnu.text.LineBufferedReader r0 = r0.port
            r34 = r0
            java.lang.String r34 = r34.getName()
            r35 = r3
            r0 = r35
            int r0 = r0.curLine
            r35 = r0
            r36 = r3
            r0 = r36
            int r0 = r0.curColumn
            r36 = r0
            r33.setLine(r34, r35, r36)
            r33 = r3
            r34 = r30
            r35 = 0
            gnu.mapping.Symbol r33 = r33.namespaceResolve(r34, r35)
            r31 = r33
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 34
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x0f76
            r33 = r3
            java.lang.StringBuilder r34 = new java.lang.StringBuilder
            r40 = r34
            r34 = r40
            r35 = r40
            r35.<init>()
            java.lang.String r35 = "expected string literal after 'declare option "
            java.lang.StringBuilder r34 = r34.append(r35)
            r35 = r30
            java.lang.StringBuilder r34 = r34.append(r35)
            r35 = 39
            java.lang.StringBuilder r34 = r34.append(r35)
            java.lang.String r34 = r34.toString()
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            goto L_0x0ed1
        L_0x0f76:
            r33 = r3
            r34 = r31
            java.lang.String r35 = new java.lang.String
            r40 = r35
            r35 = r40
            r36 = r40
            r37 = r3
            r0 = r37
            char[] r0 = r0.tokenBuffer
            r37 = r0
            r38 = 0
            r39 = r3
            r0 = r39
            int r0 = r0.tokenBufferLength
            r39 = r0
            r36.<init>(r37, r38, r39)
            r33.handleOption(r34, r35)
            goto L_0x0ed1
        L_0x0f9c:
            r33 = r3
            r0 = r33
            boolean r0 = r0.orderingModeSeen
            r33 = r0
            if (r33 == 0) goto L_0x0fbc
            r33 = r3
            r0 = r33
            boolean r0 = r0.interactive
            r33 = r0
            if (r33 != 0) goto L_0x0fbc
            r33 = r3
            java.lang.String r34 = "duplicate 'declare ordering' seen"
            java.lang.String r35 = "XQST0065"
            gnu.expr.Expression r33 = r33.syntaxError(r34, r35)
        L_0x0fbc:
            r33 = r3
            r34 = 1
            r0 = r34
            r1 = r33
            r1.orderingModeSeen = r0
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            java.lang.String r34 = "ordered"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x0fec
            r33 = r3
            r34 = 0
            r0 = r34
            r1 = r33
            r1.orderingModeUnordered = r0
        L_0x0fe1:
            r33 = r3
            r33.parseSeparator()
            gnu.expr.QuoteExp r33 = gnu.expr.QuoteExp.voidExp
            r3 = r33
            goto L_0x001e
        L_0x0fec:
            r33 = r3
            java.lang.String r34 = "unordered"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x1002
            r33 = r3
            r34 = 1
            r0 = r34
            r1 = r33
            r1.orderingModeUnordered = r0
            goto L_0x0fe1
        L_0x1002:
            r33 = r3
            java.lang.String r34 = "ordering declaration must be ordered or unordered"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x100f:
            r33 = r3
            r0 = r33
            int r0 = r0.parseCount
            r33 = r0
            r34 = 1
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x10c3
            r33 = r3
            r34 = 101(0x65, float:1.42E-43)
            java.lang.String r35 = "'xquery version' does not start module"
            r33.error(r34, r35)
        L_0x1029:
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 34
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x10d9
            java.lang.String r33 = new java.lang.String
            r40 = r33
            r33 = r40
            r34 = r40
            r35 = r3
            r0 = r35
            char[] r0 = r0.tokenBuffer
            r35 = r0
            r36 = 0
            r37 = r3
            r0 = r37
            int r0 = r0.tokenBufferLength
            r37 = r0
            r34.<init>(r35, r36, r37)
            r30 = r33
            r33 = r30
            java.lang.String r34 = "1.0"
            boolean r33 = r33.equals(r34)
            if (r33 != 0) goto L_0x108f
            r33 = r3
            r34 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r35 = new java.lang.StringBuilder
            r40 = r35
            r35 = r40
            r36 = r40
            r36.<init>()
            java.lang.String r36 = "unrecognized xquery version "
            java.lang.StringBuilder r35 = r35.append(r36)
            r36 = r30
            java.lang.StringBuilder r35 = r35.append(r36)
            java.lang.String r35 = r35.toString()
            java.lang.String r36 = "XQST0031"
            r33.error(r34, r35, r36)
        L_0x108f:
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            java.lang.String r34 = "encoding"
            boolean r33 = r33.match(r34)
            if (r33 == 0) goto L_0x11ad
            r33 = r3
            int r33 = r33.getRawToken()
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 34
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x10e6
            r33 = r3
            java.lang.String r34 = "invalid encoding specification"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x10c3:
            r33 = r3
            r0 = r33
            int r0 = r0.commentCount
            r33 = r0
            if (r33 <= 0) goto L_0x1029
            r33 = r3
            r34 = 119(0x77, float:1.67E-43)
            java.lang.String r35 = "comments should not precede 'xquery version'"
            r33.error(r34, r35)
            goto L_0x1029
        L_0x10d9:
            r33 = r3
            java.lang.String r34 = "missing version string after 'xquery version'"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
            r3 = r33
            goto L_0x001e
        L_0x10e6:
            java.lang.String r33 = new java.lang.String
            r40 = r33
            r33 = r40
            r34 = r40
            r35 = r3
            r0 = r35
            char[] r0 = r0.tokenBuffer
            r35 = r0
            r36 = 0
            r37 = r3
            r0 = r37
            int r0 = r0.tokenBufferLength
            r37 = r0
            r34.<init>(r35, r36, r37)
            r30 = r33
            r33 = r3
            r0 = r33
            int r0 = r0.tokenBufferLength
            r33 = r0
            r31 = r33
            r33 = r31
            if (r33 != 0) goto L_0x1158
            r33 = 1
        L_0x1115:
            r32 = r33
        L_0x1117:
            int r31 = r31 + -1
            r33 = r31
            if (r33 < 0) goto L_0x1196
            r33 = r32
            if (r33 != 0) goto L_0x1196
            r33 = r3
            r0 = r33
            char[] r0 = r0.tokenBuffer
            r33 = r0
            r34 = r31
            char r33 = r33[r34]
            r5 = r33
            r33 = r5
            r34 = 65
            r0 = r33
            r1 = r34
            if (r0 < r1) goto L_0x1143
            r33 = r5
            r34 = 90
            r0 = r33
            r1 = r34
            if (r0 <= r1) goto L_0x1117
        L_0x1143:
            r33 = r5
            r34 = 97
            r0 = r33
            r1 = r34
            if (r0 < r1) goto L_0x115b
            r33 = r5
            r34 = 122(0x7a, float:1.71E-43)
            r0 = r33
            r1 = r34
            if (r0 > r1) goto L_0x115b
            goto L_0x1117
        L_0x1158:
            r33 = 0
            goto L_0x1115
        L_0x115b:
            r33 = r31
            if (r33 == 0) goto L_0x1191
            r33 = r5
            r34 = 48
            r0 = r33
            r1 = r34
            if (r0 < r1) goto L_0x1173
            r33 = r5
            r34 = 57
            r0 = r33
            r1 = r34
            if (r0 <= r1) goto L_0x1117
        L_0x1173:
            r33 = r5
            r34 = 46
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x1117
            r33 = r5
            r34 = 95
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x1117
            r33 = r5
            r34 = 45
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x1117
        L_0x1191:
            r33 = 1
            r32 = r33
            goto L_0x1117
        L_0x1196:
            r33 = r32
            if (r33 == 0) goto L_0x11a7
            r33 = r3
            r34 = 101(0x65, float:1.42E-43)
            java.lang.String r35 = "invalid encoding name syntax"
            java.lang.String r36 = "XQST0087"
            r33.error(r34, r35, r36)
        L_0x11a7:
            r33 = r3
            int r33 = r33.getRawToken()
        L_0x11ad:
            r33 = r3
            r0 = r33
            int r0 = r0.curToken
            r33 = r0
            r34 = 59
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x11c6
            r33 = r3
            java.lang.String r34 = "missing ';'"
            gnu.expr.Expression r33 = r33.syntaxError(r34)
        L_0x11c6:
            gnu.expr.QuoteExp r33 = gnu.expr.QuoteExp.voidExp
            r3 = r33
            goto L_0x001e
        L_0x11cc:
            r33 = r3
            r0 = r33
            boolean r0 = r0.baseURIDeclarationSeen
            r33 = r0
            if (r33 == 0) goto L_0x11ec
            r33 = r3
            r0 = r33
            boolean r0 = r0.interactive
            r33 = r0
            if (r33 != 0) goto L_0x11ec
            r33 = r3
            java.lang.String r34 = "duplicate 'declare base-uri' seen"
            java.lang.String r35 = "XQST0032"
            gnu.expr.Expression r33 = r33.syntaxError(r34, r35)
        L_0x11ec:
            r33 = r3
            r34 = 1
            r0 = r34
            r1 = r33
            r1.baseURIDeclarationSeen = r0
            r33 = r3
            java.lang.Object r33 = r33.parseURILiteral()
            r14 = r33
            r33 = r14
            r0 = r33
            boolean r0 = r0 instanceof gnu.expr.Expression
            r33 = r0
            if (r33 == 0) goto L_0x1210
            r33 = r14
            gnu.expr.Expression r33 = (gnu.expr.Expression) r33
            r3 = r33
            goto L_0x001e
        L_0x1210:
            r33 = r3
            r33.parseSeparator()
            r33 = r3
            r34 = r14
            java.lang.String r34 = (java.lang.String) r34
            r33.setStaticBaseUri(r34)
            gnu.expr.QuoteExp r33 = gnu.expr.QuoteExp.voidExp
            r3 = r33
            goto L_0x001e
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQParser.parse(gnu.expr.Compilation):gnu.expr.Expression");
    }

    public void handleOption(Symbol name, String value) {
    }

    public static Expression makeFunctionExp(String className, String str) {
        String name = str;
        return makeFunctionExp(className, Compilation.mangleNameIfNeeded(name), name);
    }

    public static Expression makeFunctionExp(String className, String fieldName, String str) {
        Expression expression;
        String name = str;
        new ReferenceExp(name, Declaration.getDeclarationValueFromStatic(className, fieldName, name));
        return expression;
    }

    /* access modifiers changed from: package-private */
    public String tokenString() {
        String str;
        StringBuilder sb;
        String str2;
        StringBuffer stringBuffer;
        StringBuilder sb2;
        StringBuilder sb3;
        switch (this.curToken) {
            case -1:
                return "<EOF>";
            case 34:
                new StringBuffer();
                StringBuffer sbuf = stringBuffer;
                StringBuffer append = sbuf.append('\"');
                for (int i = 0; i < this.tokenBufferLength; i++) {
                    char ch = this.tokenBuffer[i];
                    if (ch == '\"') {
                        StringBuffer append2 = sbuf.append('\"');
                    }
                    StringBuffer append3 = sbuf.append(ch);
                }
                StringBuffer append4 = sbuf.append('\"');
                return sbuf.toString();
            case 65:
            case 81:
                new String(this.tokenBuffer, 0, this.tokenBufferLength);
                return str;
            case 70:
                new StringBuilder();
                new String(this.tokenBuffer, 0, this.tokenBufferLength);
                return sb.append(str2).append(" + '('").toString();
            default:
                if (this.curToken >= 100 && this.curToken - 100 < 13) {
                    new StringBuilder();
                    return sb3.append(axisNames[this.curToken - 100]).append("::-axis(").append(this.curToken).append(")").toString();
                } else if (this.curToken < 32 || this.curToken >= 127) {
                    return Integer.toString(this.curToken);
                } else {
                    new StringBuilder();
                    return sb2.append(Integer.toString(this.curToken)).append("='").append((char) this.curToken).append("'").toString();
                }
        }
    }

    public void error(char severity, String message, String code) {
        SourceError sourceError;
        SourceMessages messages = getMessages();
        new SourceError(severity, this.port.getName(), this.curLine, this.curColumn, message);
        SourceError err = sourceError;
        err.code = code;
        messages.error(err);
    }

    public void error(char severity, String message) {
        error(severity, message, (String) null);
    }

    public Expression declError(String str) throws IOException, SyntaxException {
        Expression expression;
        String message = str;
        if (this.interactive) {
            return syntaxError(message);
        }
        error(message);
        while (this.curToken != 59 && this.curToken != -1) {
            int rawToken = getRawToken();
        }
        new ErrorExp(message);
        return expression;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0030 A[EDGE_INSN: B:16:0x0030->B:5:0x0030 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression syntaxError(java.lang.String r10, java.lang.String r11) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r4 = r0
            r5 = 101(0x65, float:1.42E-43)
            r6 = r1
            r7 = r2
            r4.error(r5, r6, r7)
            r4 = r0
            boolean r4 = r4.interactive
            if (r4 == 0) goto L_0x004f
            r4 = r0
            r5 = 0
            r4.curToken = r5
            r4 = r0
            r5 = 0
            r4.curValue = r5
            r4 = r0
            r5 = 0
            r4.nesting = r5
            r4 = r0
            gnu.text.LineBufferedReader r4 = r4.getPort()
            gnu.mapping.InPort r4 = (gnu.mapping.InPort) r4
            r5 = 10
            r4.readState = r5
        L_0x0027:
            r4 = r0
            int r4 = r4.read()
            r3 = r4
            r4 = r3
            if (r4 >= 0) goto L_0x003e
        L_0x0030:
            gnu.text.SyntaxException r4 = new gnu.text.SyntaxException
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r0
            gnu.text.SourceMessages r6 = r6.getMessages()
            r5.<init>(r6)
            throw r4
        L_0x003e:
            r4 = r3
            r5 = 13
            if (r4 == r5) goto L_0x0048
            r4 = r3
            r5 = 10
            if (r4 != r5) goto L_0x004e
        L_0x0048:
            r4 = r0
            r5 = r3
            r4.unread(r5)
            goto L_0x0030
        L_0x004e:
            goto L_0x0027
        L_0x004f:
            gnu.expr.ErrorExp r4 = new gnu.expr.ErrorExp
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r5.<init>(r6)
            r0 = r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQParser.syntaxError(java.lang.String, java.lang.String):gnu.expr.Expression");
    }

    public Expression syntaxError(String message) throws IOException, SyntaxException {
        return syntaxError(message, "XPST0003");
    }

    public void eofError(String msg) throws SyntaxException {
        fatal(msg, "XPST0003");
    }

    public void fatal(String msg, String code) throws SyntaxException {
        SourceError sourceError;
        Throwable th;
        SourceMessages messages = getMessages();
        new SourceError('f', this.port.getName(), this.curLine, this.curColumn, msg);
        SourceError err = sourceError;
        err.code = code;
        messages.error(err);
        Throwable th2 = th;
        new SyntaxException(messages);
        throw th2;
    }

    /* access modifiers changed from: package-private */
    public void warnOldVersion(String str) {
        String message = str;
        if (warnOldVersion || this.comp.isPedantic()) {
            error(this.comp.isPedantic() ? 'e' : 'w', message);
        }
    }

    public void maybeSetLine(Expression expression, int i, int i2) {
        Expression exp = expression;
        int line = i;
        int column = i2;
        String file = getName();
        if (file != null && exp.getFileName() == null && !(exp instanceof QuoteExp)) {
            exp.setFile(file);
            exp.setLine(line, column);
        }
    }

    public void maybeSetLine(Declaration declaration, int i, int i2) {
        Declaration decl = declaration;
        int line = i;
        int column = i2;
        String file = getName();
        if (file != null) {
            decl.setFile(file);
            decl.setLine(line, column);
        }
    }
}
