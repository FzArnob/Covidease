package gnu.xquery.lang;

import android.support.p000v4.media.session.PlaybackStateCompat;
import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplicationMainSupport;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleExp;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.functions.ConstantFunction0;
import gnu.kawa.reflect.ClassMethods;
import gnu.kawa.xml.XDataType;
import gnu.kawa.xml.XIntegerType;
import gnu.kawa.xml.XStringType;
import gnu.kawa.xml.XTimeType;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.InPort;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.xml.XMLPrinter;
import gnu.xquery.util.BooleanValue;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.Vector;
import kawa.standard.Scheme;

public class XQuery extends Language {
    public static final String DEFAULT_ELEMENT_PREFIX = null;
    public static final String DEFAULT_FUNCTION_PREFIX = "(functions)";
    public static final String KAWA_FUNCTION_NAMESPACE = "http://kawa.gnu.org/";
    public static final String LOCAL_NAMESPACE = "http://www.w3.org/2005/xquery-local-functions";
    public static final int PARSE_WITH_FOCUS = 65536;
    public static final String QEXO_FUNCTION_NAMESPACE = "http://qexo.gnu.org/";
    public static final String SCHEMA_INSTANCE_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";
    public static final String SCHEMA_NAMESPACE = "http://www.w3.org/2001/XMLSchema";
    public static final int VARIADIC_FUNCTION_NAMESPACE = -2;
    public static final String XHTML_NAMESPACE = "http://www.w3.org/1999/xhtml";
    public static final String XQUERY_FUNCTION_NAMESPACE = "http://www.w3.org/2005/xpath-functions";
    static boolean charIsInt = false;
    public static final Namespace[] defaultFunctionNamespacePath;
    static int envCounter = 0;
    public static Environment extensionsEnvEnv = Environment.getInstance(KAWA_FUNCTION_NAMESPACE);
    public static QuoteExp falseExp;
    public static final ConstantFunction0 falseFunction;
    public static final XQuery instance;
    public static final Namespace kawaFunctionNamespace = Namespace.valueOf(KAWA_FUNCTION_NAMESPACE);
    public static final Namespace qexoFunctionNamespace = Namespace.valueOf(QEXO_FUNCTION_NAMESPACE);
    public static QuoteExp trueExp;
    public static final ConstantFunction0 trueFunction;
    static Object[] typeMap;
    public static final Environment xqEnvironment = Environment.make(XQUERY_FUNCTION_NAMESPACE);
    public static final Namespace xqueryFunctionNamespace = Namespace.valueOf(XQUERY_FUNCTION_NAMESPACE);
    Namespace defaultNamespace = xqueryFunctionNamespace;

    static {
        XQuery xQuery;
        QuoteExp quoteExp;
        QuoteExp quoteExp2;
        ConstantFunction0 constantFunction0;
        ConstantFunction0 constantFunction02;
        Namespace[] namespaceArr = new Namespace[4];
        namespaceArr[0] = qexoFunctionNamespace;
        Namespace[] namespaceArr2 = namespaceArr;
        namespaceArr2[1] = xqueryFunctionNamespace;
        Namespace[] namespaceArr3 = namespaceArr2;
        namespaceArr3[2] = Namespace.EmptyNamespace;
        Namespace[] namespaceArr4 = namespaceArr3;
        namespaceArr4[3] = kawaFunctionNamespace;
        defaultFunctionNamespacePath = namespaceArr4;
        new XQuery();
        instance = xQuery;
        instance.initXQuery();
        new QuoteExp(Boolean.FALSE, XDataType.booleanType);
        falseExp = quoteExp;
        new QuoteExp(Boolean.TRUE, XDataType.booleanType);
        trueExp = quoteExp2;
        new ConstantFunction0("false", falseExp);
        falseFunction = constantFunction0;
        new ConstantFunction0("true", trueExp);
        trueFunction = constantFunction02;
        Object[] objArr = new Object[96];
        objArr[0] = PropertyTypeConstants.PROPERTY_TYPE_STRING;
        Object[] objArr2 = objArr;
        objArr2[1] = XDataType.stringType;
        Object[] objArr3 = objArr2;
        objArr3[2] = "untypedAtomic";
        Object[] objArr4 = objArr3;
        objArr4[3] = XDataType.untypedAtomicType;
        Object[] objArr5 = objArr4;
        objArr5[4] = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN;
        Object[] objArr6 = objArr5;
        objArr6[5] = XDataType.booleanType;
        Object[] objArr7 = objArr6;
        objArr7[6] = PropertyTypeConstants.PROPERTY_TYPE_INTEGER;
        Object[] objArr8 = objArr7;
        objArr8[7] = XIntegerType.integerType;
        Object[] objArr9 = objArr8;
        objArr9[8] = "long";
        Object[] objArr10 = objArr9;
        objArr10[9] = XIntegerType.longType;
        Object[] objArr11 = objArr10;
        objArr11[10] = "int";
        Object[] objArr12 = objArr11;
        objArr12[11] = XIntegerType.intType;
        Object[] objArr13 = objArr12;
        objArr13[12] = "short";
        Object[] objArr14 = objArr13;
        objArr14[13] = XIntegerType.shortType;
        Object[] objArr15 = objArr14;
        objArr15[14] = "byte";
        Object[] objArr16 = objArr15;
        objArr16[15] = XIntegerType.byteType;
        Object[] objArr17 = objArr16;
        objArr17[16] = "unsignedLong";
        Object[] objArr18 = objArr17;
        objArr18[17] = XIntegerType.unsignedLongType;
        Object[] objArr19 = objArr18;
        objArr19[18] = "unsignedInt";
        Object[] objArr20 = objArr19;
        objArr20[19] = XIntegerType.unsignedIntType;
        Object[] objArr21 = objArr20;
        objArr21[20] = "unsignedShort";
        Object[] objArr22 = objArr21;
        objArr22[21] = XIntegerType.unsignedShortType;
        Object[] objArr23 = objArr22;
        objArr23[22] = "unsignedByte";
        Object[] objArr24 = objArr23;
        objArr24[23] = XIntegerType.unsignedByteType;
        Object[] objArr25 = objArr24;
        objArr25[24] = "positiveInteger";
        Object[] objArr26 = objArr25;
        objArr26[25] = XIntegerType.positiveIntegerType;
        Object[] objArr27 = objArr26;
        objArr27[26] = "nonPositiveInteger";
        Object[] objArr28 = objArr27;
        objArr28[27] = XIntegerType.nonPositiveIntegerType;
        Object[] objArr29 = objArr28;
        objArr29[28] = "negativeInteger";
        Object[] objArr30 = objArr29;
        objArr30[29] = XIntegerType.negativeIntegerType;
        Object[] objArr31 = objArr30;
        objArr31[30] = "nonNegativeInteger";
        Object[] objArr32 = objArr31;
        objArr32[31] = XIntegerType.nonNegativeIntegerType;
        Object[] objArr33 = objArr32;
        objArr33[32] = "date";
        Object[] objArr34 = objArr33;
        objArr34[33] = XTimeType.dateType;
        Object[] objArr35 = objArr34;
        objArr35[34] = "dateTime";
        Object[] objArr36 = objArr35;
        objArr36[35] = XTimeType.dateTimeType;
        Object[] objArr37 = objArr36;
        objArr37[36] = "time";
        Object[] objArr38 = objArr37;
        objArr38[37] = XTimeType.timeType;
        Object[] objArr39 = objArr38;
        objArr39[38] = "duration";
        Object[] objArr40 = objArr39;
        objArr40[39] = XTimeType.durationType;
        Object[] objArr41 = objArr40;
        objArr41[40] = "yearMonthDuration";
        Object[] objArr42 = objArr41;
        objArr42[41] = XTimeType.yearMonthDurationType;
        Object[] objArr43 = objArr42;
        objArr43[42] = "dayTimeDuration";
        Object[] objArr44 = objArr43;
        objArr44[43] = XTimeType.dayTimeDurationType;
        Object[] objArr45 = objArr44;
        objArr45[44] = "gYearMonth";
        Object[] objArr46 = objArr45;
        objArr46[45] = XTimeType.gYearMonthType;
        Object[] objArr47 = objArr46;
        objArr47[46] = "gYear";
        Object[] objArr48 = objArr47;
        objArr48[47] = XTimeType.gYearType;
        Object[] objArr49 = objArr48;
        objArr49[48] = "gMonthDay";
        Object[] objArr50 = objArr49;
        objArr50[49] = XTimeType.gMonthDayType;
        Object[] objArr51 = objArr50;
        objArr51[50] = "gDay";
        Object[] objArr52 = objArr51;
        objArr52[51] = XTimeType.gDayType;
        Object[] objArr53 = objArr52;
        objArr53[52] = "gMonth";
        Object[] objArr54 = objArr53;
        objArr54[53] = XTimeType.gMonthType;
        Object[] objArr55 = objArr54;
        objArr55[54] = "decimal";
        Object[] objArr56 = objArr55;
        objArr56[55] = XDataType.decimalType;
        Object[] objArr57 = objArr56;
        objArr57[56] = PropertyTypeConstants.PROPERTY_TYPE_FLOAT;
        Object[] objArr58 = objArr57;
        objArr58[57] = XDataType.floatType;
        Object[] objArr59 = objArr58;
        objArr59[58] = "double";
        Object[] objArr60 = objArr59;
        objArr60[59] = XDataType.doubleType;
        Object[] objArr61 = objArr60;
        objArr61[60] = "anyURI";
        Object[] objArr62 = objArr61;
        objArr62[61] = XDataType.anyURIType;
        Object[] objArr63 = objArr62;
        objArr63[62] = "hexBinary";
        Object[] objArr64 = objArr63;
        objArr64[63] = XDataType.hexBinaryType;
        Object[] objArr65 = objArr64;
        objArr65[64] = "base64Binary";
        Object[] objArr66 = objArr65;
        objArr66[65] = XDataType.base64BinaryType;
        Object[] objArr67 = objArr66;
        objArr67[66] = "NOTATION";
        Object[] objArr68 = objArr67;
        objArr68[67] = XDataType.NotationType;
        Object[] objArr69 = objArr68;
        objArr69[68] = "QName";
        Object[] objArr70 = objArr69;
        objArr70[69] = "gnu.mapping.Symbol";
        Object[] objArr71 = objArr70;
        objArr71[70] = "normalizedString";
        Object[] objArr72 = objArr71;
        objArr72[71] = XStringType.normalizedStringType;
        Object[] objArr73 = objArr72;
        objArr73[72] = "token";
        Object[] objArr74 = objArr73;
        objArr74[73] = XStringType.tokenType;
        Object[] objArr75 = objArr74;
        objArr75[74] = "language";
        Object[] objArr76 = objArr75;
        objArr76[75] = XStringType.languageType;
        Object[] objArr77 = objArr76;
        objArr77[76] = "NMTOKEN";
        Object[] objArr78 = objArr77;
        objArr78[77] = XStringType.NMTOKENType;
        Object[] objArr79 = objArr78;
        objArr79[78] = "Name";
        Object[] objArr80 = objArr79;
        objArr80[79] = XStringType.NameType;
        Object[] objArr81 = objArr80;
        objArr81[80] = "NCName";
        Object[] objArr82 = objArr81;
        objArr82[81] = XStringType.NCNameType;
        Object[] objArr83 = objArr82;
        objArr83[82] = "ID";
        Object[] objArr84 = objArr83;
        objArr84[83] = XStringType.IDType;
        Object[] objArr85 = objArr84;
        objArr85[84] = "IDREF";
        Object[] objArr86 = objArr85;
        objArr86[85] = XStringType.IDREFType;
        Object[] objArr87 = objArr86;
        objArr87[86] = "ENTITY";
        Object[] objArr88 = objArr87;
        objArr88[87] = XStringType.ENTITYType;
        Object[] objArr89 = objArr88;
        objArr89[88] = "anyAtomicType";
        Object[] objArr90 = objArr89;
        objArr90[89] = XDataType.anyAtomicType;
        Object[] objArr91 = objArr90;
        objArr91[90] = "anySimpleType";
        Object[] objArr92 = objArr91;
        objArr92[91] = XDataType.anySimpleType;
        Object[] objArr93 = objArr92;
        objArr93[92] = "untyped";
        Object[] objArr94 = objArr93;
        objArr94[93] = XDataType.untypedType;
        Object[] objArr95 = objArr94;
        objArr95[94] = "anyType";
        Object[] objArr96 = objArr95;
        objArr96[95] = Type.objectType;
        typeMap = objArr96;
    }

    public boolean hasSeparateFunctionNamespace() {
        return true;
    }

    public static Numeric asNumber(Object obj) {
        Object arg = obj;
        if (arg instanceof Char) {
            return IntNum.make(((Char) arg).intValue());
        }
        return (Numeric) arg;
    }

    public static char asChar(Object obj) {
        int i;
        Throwable th;
        Object x = obj;
        if (x instanceof Char) {
            return ((Char) x).charValue();
        }
        if (x instanceof Numeric) {
            i = ((Numeric) x).intValue();
        } else {
            i = -1;
        }
        if (i >= 0 && i <= 65535) {
            return (char) i;
        }
        Throwable th2 = th;
        new ClassCastException("not a character value");
        throw th2;
    }

    public boolean isTrue(Object value) {
        return BooleanValue.booleanValue(value);
    }

    public Lexer getLexer(InPort inp, SourceMessages messages) {
        Lexer lexer;
        new XQParser(inp, messages, this);
        return lexer;
    }

    public Compilation getCompilation(Lexer lexer, SourceMessages messages, NameLookup lexical) {
        Compilation compilation;
        Lexer lexer2 = lexer;
        new Compilation(this, messages, lexical);
        return compilation;
    }

    public boolean parse(Compilation compilation, int i) throws IOException, SyntaxException {
        Vector vector;
        Expression expression;
        LambdaExp lambdaExp;
        XQResolveNames xQResolveNames;
        Compilation tr = compilation;
        int options = i;
        ModuleExp mexp = tr.mainLambda;
        Compilation.defaultCallConvention = 2;
        tr.mustCompileHere();
        XQParser parser = (XQParser) tr.lexer;
        if (parser.isInteractive()) {
            Expression sexp = parser.parse(tr);
            if (sexp == null) {
                return false;
            }
            mexp.body = sexp;
        } else if ((options & 65536) != 0) {
            new LambdaExp(3);
            LambdaExp lexp = lambdaExp;
            Declaration dotDecl = lexp.addDeclaration((Object) XQParser.DOT_VARNAME);
            dotDecl.setFlag(PlaybackStateCompat.ACTION_SET_REPEAT_MODE);
            dotDecl.noteValue((Expression) null);
            Declaration addDeclaration = lexp.addDeclaration(XQParser.POSITION_VARNAME, Type.intType);
            Declaration addDeclaration2 = lexp.addDeclaration(XQParser.LAST_VARNAME, Type.intType);
            tr.push((ScopeExp) lexp);
            lexp.body = parser.parse(tr);
            tr.pop(lexp);
            mexp.body = lexp;
        } else {
            new Vector(10);
            Vector exps = vector;
            Expression sexp2 = mexp.body;
            if (sexp2 instanceof BeginExp) {
                BeginExp bexp = (BeginExp) sexp2;
                int blen = bexp.getExpressionCount();
                Expression[] bexps = bexp.getExpressions();
                for (int i2 = 0; i2 < blen; i2++) {
                    exps.addElement(bexps[i2]);
                }
            } else if (!(sexp2 == null || sexp2 == QuoteExp.voidExp)) {
                exps.addElement(sexp2);
            }
            while (true) {
                Expression sexp3 = parser.parse(tr);
                if (sexp3 == null) {
                    break;
                }
                exps.addElement(sexp3);
            }
            if (parser.parseCount != 0 || parser.isInteractive()) {
                int nexps = exps.size();
                if (nexps == 0) {
                    mexp.body = QuoteExp.voidExp;
                } else if (nexps == 1) {
                    mexp.body = (Expression) exps.elementAt(0);
                } else {
                    Expression[] arr = new Expression[nexps];
                    exps.copyInto(arr);
                    new BeginExp(arr);
                    mexp.body = expression;
                }
            } else {
                parser.error('e', "empty module", "XPST0003");
                return false;
            }
        }
        tr.pop(mexp);
        new XQResolveNames(tr);
        XQResolveNames resolver = xQResolveNames;
        resolver.functionNamespacePath = parser.functionNamespacePath;
        resolver.parser = parser;
        resolver.resolveModule(mexp);
        tr.setState(4);
        return true;
    }

    public void resolve(Compilation comp) {
    }

    public static int namespaceForFunctions(int argCount) {
        return (argCount << 2) | 2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0048, code lost:
        r4 = (gnu.mapping.Procedure) r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getNamespaceOf(gnu.expr.Declaration r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r7 = r1
            boolean r7 = r7.isProcedureDecl()
            if (r7 == 0) goto L_0x0077
            r7 = r1
            int r7 = r7.getCode()
            if (r7 >= 0) goto L_0x0013
            r7 = -2
            r0 = r7
        L_0x0012:
            return r0
        L_0x0013:
            r7 = r1
            gnu.expr.Expression r7 = r7.getValue()
            r2 = r7
            r7 = r2
            boolean r7 = r7 instanceof gnu.expr.LambdaExp
            if (r7 == 0) goto L_0x0036
            r7 = r2
            gnu.expr.LambdaExp r7 = (gnu.expr.LambdaExp) r7
            r3 = r7
            r7 = r3
            int r7 = r7.min_args
            r8 = r3
            int r8 = r8.max_args
            if (r7 != r8) goto L_0x0033
            r7 = r3
            int r7 = r7.min_args
            int r7 = namespaceForFunctions(r7)
            r0 = r7
            goto L_0x0012
        L_0x0033:
            r7 = -2
            r0 = r7
            goto L_0x0012
        L_0x0036:
            r7 = r2
            boolean r7 = r7 instanceof gnu.expr.QuoteExp
            if (r7 == 0) goto L_0x0064
            r7 = r2
            gnu.expr.QuoteExp r7 = (gnu.expr.QuoteExp) r7
            java.lang.Object r7 = r7.getValue()
            r3 = r7
            r7 = r3
            boolean r7 = r7 instanceof gnu.mapping.Procedure
            if (r7 == 0) goto L_0x0063
            r7 = r3
            gnu.mapping.Procedure r7 = (gnu.mapping.Procedure) r7
            r4 = r7
            r7 = r4
            int r7 = r7.minArgs()
            r5 = r7
            r7 = r4
            int r7 = r7.maxArgs()
            r6 = r7
            r7 = r5
            r8 = r6
            if (r7 != r8) goto L_0x0063
            r7 = r5
            int r7 = namespaceForFunctions(r7)
            r0 = r7
            goto L_0x0012
        L_0x0063:
            goto L_0x0033
        L_0x0064:
            r7 = r2
            boolean r7 = r7 instanceof gnu.expr.ReferenceExp
            if (r7 == 0) goto L_0x0033
            r7 = r0
            r8 = r2
            gnu.expr.ReferenceExp r8 = (gnu.expr.ReferenceExp) r8
            gnu.expr.Declaration r8 = r8.getBinding()
            int r7 = r7.getNamespaceOf(r8)
            r0 = r7
            goto L_0x0012
        L_0x0077:
            r7 = 1
            r0 = r7
            goto L_0x0012
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQuery.getNamespaceOf(gnu.expr.Declaration):int");
    }

    public boolean hasNamespace(Declaration decl, int i) {
        int namespace = i;
        int dnspace = getNamespaceOf(decl);
        return dnspace == namespace || (dnspace == -2 && (namespace & 2) != 0) || (namespace == -2 && (dnspace & 2) != 0);
    }

    public Symbol getSymbol(String name) {
        return Symbol.make(this.defaultNamespace, name);
    }

    public void define(String name, Object obj) {
        Object value = obj;
        this.environ.define(Symbol.make(this.defaultNamespace, name), value instanceof Procedure ? EnvironmentKey.FUNCTION : null, value);
    }

    /* access modifiers changed from: protected */
    public void define_method(String name, String cname, String mname) {
        Symbol sym = Symbol.make(this.defaultNamespace, name);
        Procedure proc = ClassMethods.apply(ClassType.make(cname), mname, 0, this);
        proc.setSymbol(sym);
        this.environ.define(sym, EnvironmentKey.FUNCTION, proc);
    }

    public String getName() {
        return "XQuery";
    }

    public void applyWithFocus(Procedure proc, Object item, int position, int size, Consumer out) throws Throwable {
        CallContext ctx = CallContext.getInstance();
        proc.check3(item, IntNum.make(position), IntNum.make(size), ctx);
        Consumer save = ctx.consumer;
        try {
            ctx.consumer = out;
            ctx.runUntilDone();
            ctx.consumer = save;
        } catch (Throwable th) {
            Throwable th2 = th;
            ctx.consumer = save;
            throw th2;
        }
    }

    public Object applyWithFocus(Procedure proc, Object item, int position, int i) throws Throwable {
        int size = i;
        CallContext ctx = CallContext.getInstance();
        int oldIndex = ctx.startFromContext();
        try {
            proc.check3(item, IntNum.make(position), IntNum.make(size), ctx);
            return ctx.getFromContext(oldIndex);
        } catch (Throwable th) {
            Throwable ex = th;
            ctx.cleanupFromContext(oldIndex);
            throw ex;
        }
    }

    public void applyWithFocus(Procedure procedure, Object obj, Consumer out) throws Throwable {
        Procedure proc = procedure;
        Object values = obj;
        CallContext ctx = CallContext.getInstance();
        Consumer save = ctx.consumer;
        try {
            ctx.consumer = out;
            applyWithFocus$X(proc, values, ctx);
            ctx.consumer = save;
        } catch (Throwable th) {
            Throwable th2 = th;
            ctx.consumer = save;
            throw th2;
        }
    }

    public Object applyWithFocus(Procedure proc, Object values) throws Throwable {
        CallContext ctx = CallContext.getInstance();
        int oldIndex = ctx.startFromContext();
        try {
            applyWithFocus$X(proc, values, ctx);
            return ctx.getFromContext(oldIndex);
        } catch (Throwable th) {
            Throwable ex = th;
            ctx.cleanupFromContext(oldIndex);
            throw ex;
        }
    }

    public void applyWithFocus$X(Procedure procedure, Object obj, CallContext callContext) throws Throwable {
        Procedure proc = procedure;
        Object values = obj;
        CallContext ctx = callContext;
        if (values instanceof Values) {
            Values v = (Values) values;
            int count = v.size();
            if (count != 0) {
                int ipos = 0;
                IntNum size = IntNum.make(count);
                int i = 1;
                while (true) {
                    proc.check3(v.getPosNext(ipos), IntNum.make(i), size, ctx);
                    ctx.runUntilDone();
                    if (i != count) {
                        ipos = v.nextPos(ipos);
                        i++;
                    } else {
                        return;
                    }
                }
            }
        } else {
            IntNum one = IntNum.one();
            proc.check3(values, one, one, ctx);
            ctx.runUntilDone();
        }
    }

    public Procedure evalToFocusProc(String expr) throws Throwable {
        SourceMessages sourceMessages;
        Reader reader;
        Throwable th;
        StringBuilder sb;
        new SourceMessages();
        SourceMessages messages = sourceMessages;
        new CharArrayInPort(expr);
        Procedure proc = evalToFocusProc(reader, messages);
        if (!messages.seenErrors()) {
            return proc;
        }
        Throwable th2 = th;
        new StringBuilder();
        new RuntimeException(sb.append("invalid syntax in eval form:\n").append(messages.toString(20)).toString());
        throw th2;
    }

    public Procedure evalToFocusProc(Reader reader, SourceMessages sourceMessages) throws Throwable {
        InPort inPort;
        InPort port;
        Reader in = reader;
        SourceMessages messages = sourceMessages;
        if (in instanceof InPort) {
            port = (InPort) in;
        } else {
            port = inPort;
            new InPort(in);
        }
        Compilation comp = parse(port, messages, 65537);
        CallContext ctx = CallContext.getInstance();
        int oldIndex = ctx.startFromContext();
        try {
            boolean evalModule = ModuleExp.evalModule(Environment.getCurrent(), ctx, comp, (URL) null, (OutPort) null);
            return (Procedure) ctx.getFromContext(oldIndex);
        } catch (Throwable th) {
            Throwable ex = th;
            ctx.cleanupFromContext(oldIndex);
            throw ex;
        }
    }

    public void evalWithFocus(Reader in, SourceMessages messages, Object values, Consumer out) throws Throwable {
        applyWithFocus(evalToFocusProc(in, messages), values, out);
    }

    public Object evalWithFocus(String expr, Object values) throws Throwable {
        return applyWithFocus(evalToFocusProc(expr), values);
    }

    public Object evalWithFocus(String expr, Object item, int position, int size) throws Throwable {
        return applyWithFocus(evalToFocusProc(expr), item, position, size);
    }

    public void evalWithFocus(Reader in, SourceMessages messages, Object item, int position, int size, Consumer out) throws Throwable {
        applyWithFocus(evalToFocusProc(in, messages), item, position, size, out);
    }

    public void eval_with_focus$X(String expr, Object values, CallContext ctx) throws Throwable {
        applyWithFocus$X(evalToFocusProc(expr), values, ctx);
    }

    public void eval_with_focus$X(String expr, Object item, int position, int size, CallContext ctx) throws Throwable {
        evalToFocusProc(expr).check3(item, IntNum.make(position), IntNum.make(size), ctx);
    }

    public XQuery() {
        this.environ = xqEnvironment;
    }

    private void initXQuery() {
        ModuleBody.setMainPrintValues(true);
        defProcStFld("unescaped-data", "gnu.kawa.xml.MakeUnescapedData", "unescapedData");
        defProcStFld("item-at", "gnu.xquery.util.ItemAt", "itemAt");
        defProcStFld("count", "gnu.kawa.functions.CountValues", "countValues");
        define_method("sum", "gnu.xquery.util.Reduce", "sum");
        defProcStFld("avg", "gnu.xquery.util.Average", "avg");
        defProcStFld("sublist", "gnu.xquery.util.SubList", "subList");
        defProcStFld("subsequence", "gnu.xquery.util.SubList", "subList");
        define_method("empty", "gnu.xquery.util.SequenceUtils", "isEmptySequence");
        define_method("exists", "gnu.xquery.util.SequenceUtils", "exists");
        define_method("insert-before", "gnu.xquery.util.SequenceUtils", "insertBefore$X");
        define_method("remove", "gnu.xquery.util.SequenceUtils", "remove$X");
        define_method("reverse", "gnu.xquery.util.SequenceUtils", "reverse$X");
        defProcStFld("false", "gnu.xquery.lang.XQuery", "falseFunction");
        defProcStFld("true", "gnu.xquery.lang.XQuery", "trueFunction");
        defProcStFld(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, "gnu.xquery.util.BooleanValue", "booleanValue");
        define_method("trace", "gnu.xquery.util.Debug", "trace");
        define_method("error", "gnu.xquery.util.XQException", "error");
        defProcStFld("write-to", "gnu.kawa.xml.WriteTo", "writeTo");
        defProcStFld("write-to-if-changed", "gnu.kawa.xml.WriteTo", "writeToIfChanged");
        defProcStFld("iterator-items", "gnu.kawa.xml.IteratorItems", "iteratorItems");
        defProcStFld("list-items", "gnu.kawa.xml.ListItems", "listItems");
        define_method("node-name", "gnu.xquery.util.NodeUtils", "nodeName");
        define_method("nilled", "gnu.xquery.util.NodeUtils", "nilled");
        define_method("data", "gnu.xquery.util.NodeUtils", "data$X");
        define_method("lower-case", "gnu.xquery.util.StringUtils", "lowerCase");
        define_method("upper-case", "gnu.xquery.util.StringUtils", "upperCase");
        define_method("substring", "gnu.xquery.util.StringUtils", "substring");
        define_method("string-length", "gnu.xquery.util.StringUtils", "stringLength");
        define_method("substring-before", "gnu.xquery.util.StringUtils", "substringBefore");
        define_method("substring-after", "gnu.xquery.util.StringUtils", "substringAfter");
        define_method("translate", "gnu.xquery.util.StringUtils", "translate");
        define_method("encode-for-uri", "gnu.xquery.util.StringUtils", "encodeForUri");
        define_method("iri-to-uri", "gnu.xquery.util.StringUtils", "iriToUri");
        define_method("escape-html-uri", "gnu.xquery.util.StringUtils", "escapeHtmlUri");
        define_method("contains", "gnu.xquery.util.StringUtils", "contains");
        define_method("starts-with", "gnu.xquery.util.StringUtils", "startsWith");
        define_method("ends-with", "gnu.xquery.util.StringUtils", "endsWith");
        define_method("codepoint-equal", "gnu.xquery.util.StringUtils", "codepointEqual");
        define_method("normalize-unicode", "gnu.xquery.util.StringUtils", "normalizeUnicode");
        define_method("string-join", "gnu.xquery.util.StringUtils", "stringJoin");
        define_method("concat", "gnu.xquery.util.StringUtils", "concat$V");
        define_method("matches", "gnu.xquery.util.StringUtils", "matches");
        define_method("replace", "gnu.xquery.util.StringUtils", "replace");
        define_method("tokenize", "gnu.xquery.util.StringUtils", "tokenize$X");
        define_method("string-to-codepoints", "gnu.xquery.util.StringUtils", "stringToCodepoints$X");
        define_method("codepoints-to-string", "gnu.xquery.util.StringUtils", "codepointsToString");
        define_method("abs", "gnu.xquery.util.NumberValue", "abs");
        define_method("floor", "gnu.xquery.util.NumberValue", "floor");
        define_method("ceiling", "gnu.xquery.util.NumberValue", "ceiling");
        define_method("round", "gnu.xquery.util.NumberValue", "round");
        define_method("round-half-to-even", "gnu.xquery.util.NumberValue", "roundHalfToEven");
        define_method("QName", "gnu.xquery.util.QNameUtils", "makeQName");
        define_method("resolve-QName", "gnu.xquery.util.QNameUtils", "resolveQNameUsingElement");
        define_method("prefix-from-QName", "gnu.xquery.util.QNameUtils", "prefixFromQName");
        define_method("local-name-from-QName", "gnu.xquery.util.QNameUtils", "localNameFromQName");
        define_method("namespace-uri-from-QName", "gnu.xquery.util.QNameUtils", "namespaceURIFromQName");
        define_method("namespace-uri-for-prefix", "gnu.xquery.util.QNameUtils", "namespaceURIForPrefix");
        define_method("in-scope-prefixes", "gnu.xquery.util.NodeUtils", "inScopePrefixes$X");
        define_method("document-uri", "gnu.xquery.util.NodeUtils", "documentUri");
        define_method("years-from-duration", "gnu.xquery.util.TimeUtils", "yearsFromDuration");
        define_method("months-from-duration", "gnu.xquery.util.TimeUtils", "monthsFromDuration");
        define_method("days-from-duration", "gnu.xquery.util.TimeUtils", "daysFromDuration");
        define_method("hours-from-duration", "gnu.xquery.util.TimeUtils", "hoursFromDuration");
        define_method("minutes-from-duration", "gnu.xquery.util.TimeUtils", "minutesFromDuration");
        define_method("seconds-from-duration", "gnu.xquery.util.TimeUtils", "secondsFromDuration");
        define_method("year-from-dateTime", "gnu.xquery.util.TimeUtils", "yearFromDateTime");
        define_method("month-from-dateTime", "gnu.xquery.util.TimeUtils", "monthFromDateTime");
        define_method("day-from-dateTime", "gnu.xquery.util.TimeUtils", "dayFromDateTime");
        define_method("hours-from-dateTime", "gnu.xquery.util.TimeUtils", "hoursFromDateTime");
        define_method("minutes-from-dateTime", "gnu.xquery.util.TimeUtils", "minutesFromDateTime");
        define_method("seconds-from-dateTime", "gnu.xquery.util.TimeUtils", "secondsFromDateTime");
        define_method("timezone-from-dateTime", "gnu.xquery.util.TimeUtils", "timezoneFromDateTime");
        define_method("year-from-date", "gnu.xquery.util.TimeUtils", "yearFromDate");
        define_method("month-from-date", "gnu.xquery.util.TimeUtils", "monthFromDate");
        define_method("day-from-date", "gnu.xquery.util.TimeUtils", "dayFromDate");
        define_method("timezone-from-date", "gnu.xquery.util.TimeUtils", "timezoneFromDate");
        define_method("hours-from-time", "gnu.xquery.util.TimeUtils", "hoursFromTime");
        define_method("minutes-from-time", "gnu.xquery.util.TimeUtils", "minutesFromTime");
        define_method("seconds-from-time", "gnu.xquery.util.TimeUtils", "secondsFromTime");
        define_method("timezone-from-time", "gnu.xquery.util.TimeUtils", "timezoneFromTime");
        define_method("adjust-dateTime-to-timezone", "gnu.xquery.util.TimeUtils", "adjustDateTimeToTimezone");
        define_method("adjust-date-to-timezone", "gnu.xquery.util.TimeUtils", "adjustDateToTimezone");
        define_method("adjust-time-to-timezone", "gnu.xquery.util.TimeUtils", "adjustTimeToTimezone");
        define_method("dateTime", "gnu.xquery.util.TimeUtils", "dateTime");
        define_method("current-dateTime", "gnu.xquery.util.TimeUtils", "currentDateTime");
        define_method("current-date", "gnu.xquery.util.TimeUtils", "currentDate");
        define_method("current-time", "gnu.xquery.util.TimeUtils", "currentTime");
        define_method("implicit-timezone", "gnu.xquery.util.TimeUtils", "implicitTimezone");
        define_method("zero-or-one", "gnu.xquery.util.SequenceUtils", "zeroOrOne");
        define_method("one-or-more", "gnu.xquery.util.SequenceUtils", "oneOrMore");
        define_method("exactly-one", "gnu.xquery.util.SequenceUtils", "exactlyOne");
        defProcStFld("distinct-nodes", "gnu.kawa.xml.SortNodes", "sortNodes");
        defProcStFld("children", "gnu.kawa.xml.Children", "children");
        define_method("not", "gnu.xquery.util.BooleanValue", "not");
        this.defaultNamespace = qexoFunctionNamespace;
        defProcStFld("response-header", "gnu.kawa.servlet.HTTP");
        defProcStFld("response-content-type", "gnu.kawa.servlet.HTTP");
        defProcStFld("response-status", "gnu.kawa.servlet.HTTP");
        defProcStFld("error-response", "gnu.kawa.servlet.HTTP");
        defProcStFld("current-servlet", "gnu.kawa.servlet.HTTP");
        defProcStFld("current-servlet-context", "gnu.kawa.servlet.HTTP");
        defProcStFld("current-servlet-config", "gnu.kawa.servlet.HTTP");
        defProcStFld("servlet-context-realpath", "gnu.kawa.servlet.HTTP");
        defProcStFld("get-response", "gnu.kawa.servlet.HTTP");
        defProcStFld("get-request", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-method", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-uri", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-url", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-path-info", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-path-translated", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-servlet-path", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-query-string", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-parameter", "gnu.kawa.servlet.HTTP");
        defProcStFld("request-parameters", "gnu.kawa.servlet.HTTP");
        this.defaultNamespace = xqueryFunctionNamespace;
    }

    public static XQuery getInstance() {
        return instance;
    }

    public static void registerEnvironment() {
        ApplicationMainSupport.processCommandLinePropertyAssignments = true;
        Language.setDefaults(instance);
    }

    public Consumer getOutputConsumer(Writer out) {
        Consumer consumer;
        new XMLPrinter(out, false);
        return consumer;
    }

    public static Type getStandardType(String str) {
        String name = str;
        int i = typeMap.length;
        do {
            i -= 2;
            if (i < 0) {
                return null;
            }
        } while (!typeMap[i].equals(name));
        Object t = typeMap[i + 1];
        if (t instanceof String) {
            return Scheme.string2Type((String) t);
        }
        return (Type) t;
    }

    public Type getTypeFor(String str) {
        String name = str;
        Type t = getStandardType(name.startsWith("xs:") ? name.substring(3) : name.startsWith("xdt:") ? name.substring(4) : name);
        return t != null ? t : Scheme.string2Type(name);
    }

    public String formatType(Type type) {
        Type type2 = type;
        String tname = type2.getName();
        if ("gnu.math.IntNum".equals(tname)) {
            return "xs:integer";
        }
        if ("java.lang.String".equals(tname) || "java.lang.CharSequence".equals(tname)) {
            return "xs:string";
        }
        return type2.toString();
    }

    public Type getTypeFor(Class cls) {
        Class clas = cls;
        if (clas.isPrimitive()) {
            String name = clas.getName();
            if (name.equals(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)) {
                return XDataType.booleanType;
            }
            return Scheme.getNamedType(name);
        }
        if (!clas.isArray()) {
            String name2 = clas.getName();
            if (name2.equals("java.lang.String")) {
                return XDataType.stringStringType;
            }
            if (name2.equals("gnu.kawa.xml.UntypedAtomic")) {
                return XDataType.untypedAtomicType;
            }
            if (name2.equals("java.lang.Boolean")) {
                return XDataType.booleanType;
            }
            if (name2.equals("java.lang.Float")) {
                return XDataType.floatType;
            }
            if (name2.equals("java.lang.Double")) {
                return XDataType.doubleType;
            }
            if (name2.equals("java.math.BigDecimal")) {
                return XDataType.decimalType;
            }
            if (name2.equals("gnu.math.Duration")) {
                return XDataType.durationType;
            }
            if (name2.equals("gnu.text.Path")) {
                return XDataType.anyURIType;
            }
        }
        return Type.make(clas);
    }

    public Procedure getPrompter() {
        Procedure procedure;
        new Prompter();
        return procedure;
    }

    static void mangle(String str, int i, int i2, StringBuffer stringBuffer, char c) {
        boolean wordStart;
        String name = str;
        int start = i;
        int length = i2;
        StringBuffer sbuf = stringBuffer;
        char mode = c;
        char prev = 'P';
        int outStart = sbuf.length();
        int i3 = 0;
        while (i3 < length) {
            char ch = name.charAt(start + i3);
            i3++;
            if (Character.isUpperCase(ch)) {
                wordStart = prev != 'U' || (i3 < length && Character.isLowerCase(name.charAt(start + i3)));
                prev = 'U';
            } else if (Character.isLowerCase(ch)) {
                wordStart = (prev == 'L' && prev == 'U') ? false : true;
                prev = 'L';
            } else if (Character.isLetter(ch)) {
                wordStart = prev != 'O';
                prev = 'O';
            } else if (Character.isDigit(ch)) {
                wordStart = prev != 'D';
                prev = 'D';
            } else if (Character.isJavaIdentifierPart(ch)) {
                wordStart = (prev == 'D' || prev == 'M') ? false : true;
                prev = 'M';
            } else {
                prev = 'P';
            }
            if (wordStart || mode == '_') {
                if (wordStart && mode == '_' && sbuf.length() > outStart) {
                    StringBuffer append = sbuf.append('_');
                }
                ch = Character.toUpperCase(ch);
            }
            StringBuffer append2 = sbuf.append(ch);
        }
    }

    public static String mangle(String str) {
        StringBuffer stringBuffer;
        String name = str;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        mangle(name, 0, name.length(), sbuf, 'U');
        return sbuf.toString();
    }

    public static String makeClassName(String source) {
        String source2 = source.replace(File.separatorChar, '/');
        int sl = source2.lastIndexOf(47);
        if (sl >= 0) {
            source2 = source2.substring(sl + 1);
        }
        int dot = source2.lastIndexOf(46);
        if (dot >= 0) {
            source2 = source2.substring(0, dot);
        }
        return Compilation.mangleNameIfNeeded(source2);
    }

    public static Object getExternal(Symbol symbol, Object obj) {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        Symbol name = symbol;
        Object type = obj;
        Environment env = Environment.getCurrent();
        Object value = env.get(name, (Object) null, (Object) null);
        if (value == null) {
            value = env.get(Symbol.makeWithUnknownNamespace(name.getLocalName(), name.getPrefix()), (Object) null, (Object) null);
        }
        if (value == null) {
            Throwable th3 = th2;
            new StringBuilder();
            new RuntimeException(sb.append("unbound external ").append(name).toString());
            throw th3;
        } else if (type == null) {
            return value;
        } else {
            if (type instanceof XDataType) {
                return ((XDataType) type).cast(value);
            }
            if (type instanceof ClassType) {
                String cname = ((ClassType) type).getName();
                if ("gnu.math.IntNum".equals(cname)) {
                    return IntNum.valueOf(value.toString());
                }
                if ("gnu.math.RealNum".equals(cname)) {
                    return DFloNum.make(Double.parseDouble(value.toString()));
                }
            }
            try {
                return ((Type) type).coerceFromObject(value);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th4 = th;
                new WrongType(name.toString(), -2, value, type.toString());
                throw th4;
            }
        }
    }
}
