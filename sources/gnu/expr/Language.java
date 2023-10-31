package gnu.expr;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.kawa.lispexpr.ClassNamespace;
import gnu.kawa.reflect.ClassMemberLocation;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.AbstractFormat;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.Convert;
import gnu.lists.FString;
import gnu.lists.PrintConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.InPort;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.NamedLocation;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import kawa.repl;
import org.shaded.apache.http.HttpStatus;

public abstract class Language {
    public static final int FUNCTION_NAMESPACE = 2;
    public static final int NAMESPACE_PREFIX_NAMESPACE = 4;
    public static final int PARSE_CURRENT_NAMES = 2;
    public static final int PARSE_EXPLICIT = 64;
    public static final int PARSE_FOR_APPLET = 16;
    public static final int PARSE_FOR_EVAL = 3;
    public static final int PARSE_FOR_SERVLET = 32;
    public static final int PARSE_IMMEDIATE = 1;
    public static final int PARSE_ONE_LINE = 4;
    public static final int PARSE_PROLOG = 8;
    public static final int VALUE_NAMESPACE = 1;
    protected static final InheritableThreadLocal<Language> current;
    static int envCounter;
    protected static int env_counter = 0;
    protected static Language global;
    static String[][] languages;
    public static boolean requirePedantic;
    protected Environment environ;
    protected Environment userEnv;

    public abstract Lexer getLexer(InPort inPort, SourceMessages sourceMessages);

    public abstract boolean parse(Compilation compilation, int i) throws IOException, SyntaxException;

    static {
        InheritableThreadLocal<Language> inheritableThreadLocal;
        new InheritableThreadLocal<>();
        current = inheritableThreadLocal;
        Environment.setGlobal(BuiltinEnvironment.getInstance());
        String[][] strArr = new String[8][];
        String[][] strArr2 = strArr;
        String[][] strArr3 = strArr;
        String[] strArr4 = new String[4];
        strArr4[0] = "scheme";
        String[] strArr5 = strArr4;
        strArr5[1] = ".scm";
        String[] strArr6 = strArr5;
        strArr6[2] = ".sc";
        String[] strArr7 = strArr6;
        strArr7[3] = "kawa.standard.Scheme";
        strArr3[0] = strArr7;
        String[][] strArr8 = strArr2;
        String[][] strArr9 = strArr8;
        String[][] strArr10 = strArr8;
        String[] strArr11 = new String[3];
        strArr11[0] = "krl";
        String[] strArr12 = strArr11;
        strArr12[1] = ".krl";
        String[] strArr13 = strArr12;
        strArr13[2] = "gnu.kawa.brl.BRL";
        strArr10[1] = strArr13;
        String[][] strArr14 = strArr9;
        String[][] strArr15 = strArr14;
        String[][] strArr16 = strArr14;
        String[] strArr17 = new String[3];
        strArr17[0] = "brl";
        String[] strArr18 = strArr17;
        strArr18[1] = ".brl";
        String[] strArr19 = strArr18;
        strArr19[2] = "gnu.kawa.brl.BRL";
        strArr16[2] = strArr19;
        String[][] strArr20 = strArr15;
        String[][] strArr21 = strArr20;
        String[][] strArr22 = strArr20;
        String[] strArr23 = new String[5];
        strArr23[0] = "emacs";
        String[] strArr24 = strArr23;
        strArr24[1] = "elisp";
        String[] strArr25 = strArr24;
        strArr25[2] = "emacs-lisp";
        String[] strArr26 = strArr25;
        strArr26[3] = ".el";
        String[] strArr27 = strArr26;
        strArr27[4] = "gnu.jemacs.lang.ELisp";
        strArr22[3] = strArr27;
        String[][] strArr28 = strArr21;
        String[][] strArr29 = strArr28;
        String[][] strArr30 = strArr28;
        String[] strArr31 = new String[5];
        strArr31[0] = "xquery";
        String[] strArr32 = strArr31;
        strArr32[1] = ".xquery";
        String[] strArr33 = strArr32;
        strArr33[2] = ".xq";
        String[] strArr34 = strArr33;
        strArr34[3] = ".xql";
        String[] strArr35 = strArr34;
        strArr35[4] = "gnu.xquery.lang.XQuery";
        strArr30[4] = strArr35;
        String[][] strArr36 = strArr29;
        String[][] strArr37 = strArr36;
        String[][] strArr38 = strArr36;
        String[] strArr39 = new String[3];
        strArr39[0] = "q2";
        String[] strArr40 = strArr39;
        strArr40[1] = ".q2";
        String[] strArr41 = strArr40;
        strArr41[2] = "gnu.q2.lang.Q2";
        strArr38[5] = strArr41;
        String[][] strArr42 = strArr37;
        String[][] strArr43 = strArr42;
        String[][] strArr44 = strArr42;
        String[] strArr45 = new String[4];
        strArr45[0] = "xslt";
        String[] strArr46 = strArr45;
        strArr46[1] = "xsl";
        String[] strArr47 = strArr46;
        strArr47[2] = ".xsl";
        String[] strArr48 = strArr47;
        strArr48[3] = "gnu.kawa.xslt.XSLT";
        strArr44[6] = strArr48;
        String[][] strArr49 = strArr43;
        String[][] strArr50 = strArr49;
        String[][] strArr51 = strArr49;
        String[] strArr52 = new String[8];
        strArr52[0] = "commonlisp";
        String[] strArr53 = strArr52;
        strArr53[1] = "common-lisp";
        String[] strArr54 = strArr53;
        strArr54[2] = "clisp";
        String[] strArr55 = strArr54;
        strArr55[3] = "lisp";
        String[] strArr56 = strArr55;
        strArr56[4] = ".lisp";
        String[] strArr57 = strArr56;
        strArr57[5] = ".lsp";
        String[] strArr58 = strArr57;
        strArr58[6] = ".cl";
        String[] strArr59 = strArr58;
        strArr59[7] = "gnu.commonlisp.lang.CommonLisp";
        strArr51[7] = strArr59;
        languages = strArr50;
    }

    public static Language getDefaultLanguage() {
        Language lang = (Language) current.get();
        return lang != null ? lang : global;
    }

    public static void setCurrentLanguage(Language language) {
        current.set(language);
    }

    public static Language setSaveCurrent(Language language) {
        Language save = (Language) current.get();
        current.set(language);
        return save;
    }

    public static void restoreCurrent(Language saved) {
        current.set(saved);
    }

    public static String[][] getLanguages() {
        return languages;
    }

    public static void registerLanguage(String[] langMapping) {
        String[][] newLangs = new String[(languages.length + 1)][];
        System.arraycopy(languages, 0, newLangs, 0, languages.length);
        newLangs[newLangs.length - 1] = langMapping;
        languages = newLangs;
    }

    public static Language detect(InputStream inputStream) throws IOException {
        StringBuffer stringBuffer;
        int c;
        InputStream in = inputStream;
        if (!in.markSupported()) {
            return null;
        }
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        in.mark(200);
        while (sbuf.length() < 200 && (c = in.read()) >= 0 && c != 10 && c != 13) {
            StringBuffer append = sbuf.append((char) c);
        }
        in.reset();
        return detect(sbuf.toString());
    }

    public static Language detect(InPort inPort) throws IOException {
        StringBuffer stringBuffer;
        InPort port = inPort;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        port.mark(HttpStatus.SC_MULTIPLE_CHOICES);
        port.readLine(sbuf, 'P');
        port.reset();
        return detect(sbuf.toString());
    }

    public static Language detect(String line) {
        Language lang;
        String str = line.trim();
        int k = str.indexOf("kawa:");
        if (k >= 0) {
            int i = k + 5;
            int j = i;
            while (j < str.length() && Character.isJavaIdentifierPart(str.charAt(j))) {
                j++;
            }
            if (j > i && (lang = getInstance(str.substring(i, j))) != null) {
                return lang;
            }
        }
        if (str.indexOf("-*- scheme -*-") >= 0) {
            return getInstance("scheme");
        }
        if (str.indexOf("-*- xquery -*-") >= 0) {
            return getInstance("xquery");
        }
        if (str.indexOf("-*- emacs-lisp -*-") >= 0) {
            return getInstance("elisp");
        }
        if (str.indexOf("-*- common-lisp -*-") >= 0 || str.indexOf("-*- lisp -*-") >= 0) {
            return getInstance("common-lisp");
        }
        if ((str.charAt(0) == '(' && str.charAt(1) == ':') || (str.length() >= 7 && str.substring(0, 7).equals("xquery "))) {
            return getInstance("xquery");
        }
        if (str.charAt(0) == ';' && str.charAt(1) == ';') {
            return getInstance("scheme");
        }
        return null;
    }

    public static Language getInstanceFromFilenameExtension(String str) {
        Language lang;
        String filename = str;
        int dot = filename.lastIndexOf(46);
        if (dot <= 0 || (lang = getInstance(filename.substring(dot))) == null) {
            return null;
        }
        return lang;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0042 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.expr.Language getInstance(java.lang.String r10) {
        /*
            r0 = r10
            java.lang.String[][] r8 = languages
            int r8 = r8.length
            r1 = r8
            r8 = 0
            r2 = r8
        L_0x0007:
            r8 = r2
            r9 = r1
            if (r8 >= r9) goto L_0x0045
            java.lang.String[][] r8 = languages
            r9 = r2
            r8 = r8[r9]
            r3 = r8
            r8 = r3
            int r8 = r8.length
            r9 = 1
            int r8 = r8 + -1
            r4 = r8
            r8 = r4
            r5 = r8
        L_0x0019:
            int r5 = r5 + -1
            r8 = r5
            if (r8 < 0) goto L_0x0042
            r8 = r0
            if (r8 == 0) goto L_0x002c
            r8 = r3
            r9 = r5
            r8 = r8[r9]
            r9 = r0
            boolean r8 = r8.equalsIgnoreCase(r9)
            if (r8 == 0) goto L_0x0019
        L_0x002c:
            r8 = r3
            r9 = r4
            r8 = r8[r9]     // Catch:{ ClassNotFoundException -> 0x0040 }
            java.lang.Class r8 = java.lang.Class.forName(r8)     // Catch:{ ClassNotFoundException -> 0x0040 }
            r6 = r8
            r8 = r3
            r9 = 0
            r8 = r8[r9]
            r9 = r6
            gnu.expr.Language r8 = getInstance(r8, r9)
            r0 = r8
        L_0x003f:
            return r0
        L_0x0040:
            r8 = move-exception
            r7 = r8
        L_0x0042:
            int r2 = r2 + 1
            goto L_0x0007
        L_0x0045:
            r8 = 0
            r0 = r8
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Language.getInstance(java.lang.String):gnu.expr.Language");
    }

    protected Language() {
        Convert.setInstance(KawaConvert.getInstance());
    }

    public static Language getInstance(String str, Class cls) {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        Method method;
        StringBuilder sb2;
        StringBuilder sb3;
        String langName = str;
        Class langClass = cls;
        try {
            Class[] args = new Class[0];
            try {
                new StringBuilder();
                String capitalizedName = sb2.append(Character.toTitleCase(langName.charAt(0))).append(langName.substring(1).toLowerCase()).toString();
                new StringBuilder();
                method = langClass.getDeclaredMethod(sb3.append("get").append(capitalizedName).append("Instance").toString(), args);
            } catch (Exception e) {
                Exception exc = e;
                method = langClass.getDeclaredMethod("getInstance", args);
            }
            return (Language) method.invoke((Object) null, Values.noArgs);
        } catch (Exception e2) {
            Throwable ex = e2;
            String langName2 = langClass.getName();
            if (ex instanceof InvocationTargetException) {
                th = ((InvocationTargetException) ex).getTargetException();
            } else {
                th = ex;
            }
            Throwable th3 = th2;
            new StringBuilder();
            new WrappedException(sb.append("getInstance for '").append(langName2).append("' failed").toString(), th);
            throw th3;
        }
    }

    public boolean isTrue(Object value) {
        return value != Boolean.FALSE;
    }

    public Object booleanObject(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }

    public Object noValue() {
        return Values.empty;
    }

    public boolean hasSeparateFunctionNamespace() {
        return false;
    }

    public final Environment getEnvironment() {
        return this.userEnv != null ? this.userEnv : Environment.getCurrent();
    }

    public final Environment getNewEnvironment() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder append = sb.append("environment-");
        int i = envCounter + 1;
        envCounter = i;
        return Environment.make(append.append(i).toString(), this.environ);
    }

    public Environment getLangEnvironment() {
        return this.environ;
    }

    public NamedLocation lookupBuiltin(Symbol name, Object property, int hash) {
        return this.environ == null ? null : this.environ.lookup(name, property, hash);
    }

    public void define(String sym, Object p) {
        this.environ.define(getSymbol(sym), (Object) null, p);
    }

    /* access modifiers changed from: protected */
    public void defAliasStFld(String name, String cname, String fname) {
        StaticFieldLocation define = StaticFieldLocation.define(this.environ, getSymbol(name), (Object) null, cname, fname);
    }

    /* access modifiers changed from: protected */
    public void defProcStFld(String str, String str2, String str3) {
        String name = str;
        String cname = str2;
        String fname = str3;
        StaticFieldLocation.define(this.environ, getSymbol(name), hasSeparateFunctionNamespace() ? EnvironmentKey.FUNCTION : null, cname, fname).setProcedure();
    }

    /* access modifiers changed from: protected */
    public void defProcStFld(String str, String cname) {
        String name = str;
        defProcStFld(name, cname, Compilation.mangleNameIfNeeded(name));
    }

    public final void defineFunction(Named named) {
        Named proc = named;
        Object name = proc.getSymbol();
        this.environ.define(name instanceof Symbol ? (Symbol) name : getSymbol(name.toString()), hasSeparateFunctionNamespace() ? EnvironmentKey.FUNCTION : null, proc);
    }

    public void defineFunction(String name, Object proc) {
        this.environ.define(getSymbol(name), hasSeparateFunctionNamespace() ? EnvironmentKey.FUNCTION : null, proc);
    }

    public Object getEnvPropertyFor(Field field, Object obj) {
        Field fld = field;
        Object obj2 = obj;
        if (!hasSeparateFunctionNamespace()) {
            return null;
        }
        if (Compilation.typeProcedure.getReflectClass().isAssignableFrom(fld.getType())) {
            return EnvironmentKey.FUNCTION;
        }
        return null;
    }

    public Object getEnvPropertyFor(Declaration declaration) {
        Declaration decl = declaration;
        if (!hasSeparateFunctionNamespace() || !decl.isProcedureDecl()) {
            return null;
        }
        return EnvironmentKey.FUNCTION;
    }

    public void loadClass(String str) throws ClassNotFoundException {
        Throwable th;
        StringBuilder sb;
        String name = str;
        try {
            try {
                Object inst = Class.forName(name).newInstance();
                ClassMemberLocation.defineAll(inst, this, Environment.getCurrent());
                if (inst instanceof ModuleBody) {
                    ((ModuleBody) inst).run();
                }
            } catch (Exception e) {
                Exception ex = e;
                Throwable th2 = th;
                new StringBuilder();
                new WrappedException(sb.append("cannot load ").append(name).toString(), ex);
                throw th2;
            }
        } catch (ClassNotFoundException e2) {
            throw e2;
        }
    }

    public Symbol getSymbol(String name) {
        return this.environ.getSymbol(name);
    }

    public Object lookup(String name) {
        return this.environ.get((Object) name);
    }

    public AbstractFormat getFormat(boolean z) {
        boolean z2 = z;
        return null;
    }

    public Consumer getOutputConsumer(Writer writer) {
        OutPort outPort;
        OutPort outPort2;
        Writer out = writer;
        if (out instanceof OutPort) {
            outPort2 = (OutPort) out;
        } else {
            outPort2 = outPort;
            new OutPort(out);
        }
        OutPort oport = outPort2;
        oport.objectFormat = getFormat(false);
        return oport;
    }

    public String getName() {
        String name = getClass().getName();
        int dot = name.lastIndexOf(46);
        if (dot >= 0) {
            name = name.substring(dot + 1);
        }
        return name;
    }

    public Compilation getCompilation(Lexer lexer, SourceMessages messages, NameLookup lexical) {
        Compilation compilation;
        Lexer lexer2 = lexer;
        new Compilation(this, messages, lexical);
        return compilation;
    }

    public final Compilation parse(InPort port, SourceMessages messages, int options) throws IOException, SyntaxException {
        return parse(getLexer(port, messages), options, (ModuleInfo) null);
    }

    public final Compilation parse(InPort port, SourceMessages messages, ModuleInfo info) throws IOException, SyntaxException {
        return parse(getLexer(port, messages), 8, info);
    }

    public final Compilation parse(InPort port, SourceMessages messages, int options, ModuleInfo info) throws IOException, SyntaxException {
        return parse(getLexer(port, messages), options, info);
    }

    public final Compilation parse(Lexer lexer, int i, ModuleInfo moduleInfo) throws IOException, SyntaxException {
        NameLookup nameLookup;
        NameLookup nameLookup2;
        Lexer lexer2 = lexer;
        int options = i;
        ModuleInfo info = moduleInfo;
        SourceMessages messages = lexer2.getMessages();
        if ((options & 2) != 0) {
            nameLookup2 = NameLookup.getInstance(getEnvironment(), this);
        } else {
            nameLookup2 = nameLookup;
            new NameLookup(this);
        }
        NameLookup lexical = nameLookup2;
        boolean immediate = (options & 1) != 0;
        Compilation tr = getCompilation(lexer2, messages, lexical);
        if (requirePedantic) {
            tr.pedantic = true;
        }
        if (!immediate) {
            tr.mustCompile = true;
        }
        tr.immediate = immediate;
        tr.langOptions = options;
        if ((options & 64) != 0) {
            tr.explicit = true;
        }
        if ((options & 8) != 0) {
            tr.setState(1);
        }
        ModuleExp pushNewModule = tr.pushNewModule(lexer2);
        if (info != null) {
            info.setCompilation(tr);
        }
        if (!parse(tr, options)) {
            return null;
        }
        if (tr.getState() == 1) {
            tr.setState(2);
        }
        return tr;
    }

    public void resolve(Compilation comp) {
    }

    public Type getTypeFor(Class clas) {
        return Type.make(clas);
    }

    public final Type getLangTypeFor(Type type) {
        Class clas;
        Type type2 = type;
        if (!type2.isExisting() || (clas = type2.getReflectClass()) == null) {
            return type2;
        }
        return getTypeFor(clas);
    }

    public String formatType(Type type) {
        return type.getName();
    }

    public static Type string2Type(String str) {
        Type t;
        String name = str;
        if (name.endsWith("[]")) {
            Type t2 = string2Type(name.substring(0, name.length() - 2));
            if (t2 == null) {
                return null;
            }
            t = ArrayType.make(t2);
        } else if (!Type.isValidJavaTypeName(name)) {
            return null;
        } else {
            t = Type.getType(name);
        }
        return t;
    }

    public Type getTypeFor(String name) {
        return string2Type(name);
    }

    public final Type getTypeFor(Object obj, boolean z) {
        String uri;
        Object spec = obj;
        boolean lenient = z;
        if (spec instanceof Type) {
            return (Type) spec;
        }
        if (spec instanceof Class) {
            return getTypeFor((Class) spec);
        }
        if (lenient && ((spec instanceof FString) || (spec instanceof String) || (((spec instanceof Symbol) && ((Symbol) spec).hasEmptyNamespace()) || (spec instanceof CharSeq)))) {
            return getTypeFor(spec.toString());
        }
        if (!(spec instanceof Namespace) || (uri = ((Namespace) spec).getName()) == null || !uri.startsWith("class:")) {
            return null;
        }
        return getLangTypeFor(string2Type(uri.substring(6)));
    }

    public final Type asType(Object obj) {
        Object spec = obj;
        Type type = getTypeFor(spec, true);
        return type == null ? (Type) spec : type;
    }

    public final Type getTypeFor(Expression exp) {
        return getTypeFor(exp, true);
    }

    public Type getTypeFor(Expression expression, boolean z) {
        Expression exp = expression;
        boolean lenient = z;
        if (exp instanceof QuoteExp) {
            Object value = ((QuoteExp) exp).getValue();
            if (value instanceof Type) {
                return (Type) value;
            }
            if (value instanceof Class) {
                return Type.make((Class) value);
            }
            return getTypeFor(value, lenient);
        }
        if (exp instanceof ReferenceExp) {
            ReferenceExp rexp = (ReferenceExp) exp;
            Declaration decl = Declaration.followAliases(rexp.getBinding());
            String name = rexp.getName();
            if (decl != null) {
                Expression exp2 = decl.getValue();
                if ((exp2 instanceof QuoteExp) && decl.getFlag(16384) && !decl.isIndirectBinding()) {
                    return getTypeFor(((QuoteExp) exp2).getValue(), lenient);
                } else if ((exp2 instanceof ClassExp) || (exp2 instanceof ModuleExp)) {
                    decl.setCanRead(true);
                    return ((LambdaExp) exp2).getClassType();
                } else if (decl.isAlias() && (exp2 instanceof QuoteExp)) {
                    Object val = ((QuoteExp) exp2).getValue();
                    if (val instanceof Location) {
                        Location loc = (Location) val;
                        if (loc.isBound()) {
                            return getTypeFor(loc.get(), lenient);
                        }
                        if (!(loc instanceof Named)) {
                            return null;
                        }
                        name = ((Named) loc).getName();
                    }
                } else if (!decl.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH)) {
                    return getTypeFor(exp2, lenient);
                }
            }
            Object val2 = getEnvironment().get((Object) name);
            if (val2 instanceof Type) {
                return (Type) val2;
            }
            if (val2 instanceof ClassNamespace) {
                return ((ClassNamespace) val2).getClassType();
            }
            int len = name.length();
            if (len > 2 && name.charAt(0) == '<' && name.charAt(len - 1) == '>') {
                return getTypeFor(name.substring(1, len - 1));
            }
        } else if ((exp instanceof ClassExp) || (exp instanceof ModuleExp)) {
            return ((LambdaExp) exp).getClassType();
        }
        return null;
    }

    public static Type unionType(Type type, Type type2) {
        Type t1 = type;
        Type t2 = type2;
        if (t1 == Type.toStringType) {
            t1 = Type.javalangStringType;
        }
        if (t2 == Type.toStringType) {
            t2 = Type.javalangStringType;
        }
        if (t1 == t2) {
            return t1;
        }
        if (!(t1 instanceof PrimType) || !(t2 instanceof PrimType)) {
            return Type.objectType;
        }
        char sig1 = t1.getSignature().charAt(0);
        char sig2 = t2.getSignature().charAt(0);
        if (sig1 == sig2) {
            return t1;
        }
        if ((sig1 == 'B' || sig1 == 'S' || sig1 == 'I') && (sig2 == 'I' || sig2 == 'J')) {
            return t2;
        }
        if ((sig2 == 'B' || sig2 == 'S' || sig2 == 'I') && (sig1 == 'I' || sig1 == 'J')) {
            return t1;
        }
        if (sig1 == 'F' && sig2 == 'D') {
            return t2;
        }
        if (sig2 == 'F' && sig1 == 'D') {
            return t1;
        }
        return Type.objectType;
    }

    public Declaration declFromField(ModuleExp moduleExp, Object obj, gnu.bytecode.Field field) {
        String intern;
        ModuleExp mod = moduleExp;
        Object fvalue = obj;
        gnu.bytecode.Field fld = field;
        String fname = fld.getName();
        Type ftype = fld.getType();
        boolean isAlias = ftype.isSubtype(Compilation.typeLocation);
        boolean externalAccess = false;
        boolean isFinal = (fld.getModifiers() & 16) != 0;
        boolean endsWith = fname.endsWith("$instance");
        boolean isImportedInstance = endsWith;
        if (endsWith) {
            intern = fname;
        } else if (!isFinal || !(fvalue instanceof Named)) {
            if (fname.startsWith(Declaration.PRIVATE_PREFIX)) {
                externalAccess = true;
                fname = fname.substring(Declaration.PRIVATE_PREFIX.length());
            }
            intern = Compilation.demangleName(fname, true).intern();
        } else {
            intern = ((Named) fvalue).getSymbol();
        }
        if (intern instanceof String) {
            String uri = mod.getNamespaceUri();
            String sname = intern;
            if (uri == null) {
                intern = SimpleSymbol.valueOf(sname);
            } else {
                intern = Symbol.make(uri, sname);
            }
        }
        Declaration fdecl = mod.addDeclaration(intern, isAlias ? Type.objectType : getTypeFor(ftype.getReflectClass()));
        boolean isStatic = (fld.getModifiers() & 8) != 0;
        if (isAlias) {
            fdecl.setIndirectBinding(true);
            if ((ftype instanceof ClassType) && ((ClassType) ftype).isSubclass("gnu.mapping.ThreadLocation")) {
                fdecl.setFlag(268435456);
            }
        } else if (isFinal && (ftype instanceof ClassType)) {
            if (ftype.isSubtype(Compilation.typeProcedure)) {
                fdecl.setProcedureDecl(true);
            } else if (((ClassType) ftype).isSubclass("gnu.mapping.Namespace")) {
                fdecl.setFlag(PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE);
            }
        }
        if (isStatic) {
            fdecl.setFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH);
        }
        fdecl.field = fld;
        if (isFinal && !isAlias) {
            fdecl.setFlag(16384);
        }
        if (isImportedInstance) {
            fdecl.setFlag(1073741824);
        }
        fdecl.setSimple(false);
        if (externalAccess) {
            fdecl.setFlag(524320);
        }
        return fdecl;
    }

    public int getNamespaceOf(Declaration declaration) {
        Declaration declaration2 = declaration;
        return 1;
    }

    public boolean hasNamespace(Declaration decl, int namespace) {
        return (getNamespaceOf(decl) & namespace) != 0;
    }

    public void emitPushBoolean(boolean value, CodeAttr code) {
        code.emitGetStatic(value ? Compilation.trueConstant : Compilation.falseConstant);
    }

    public void emitCoerceToBoolean(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        emitPushBoolean(false, code);
        code.emitIfNEq();
        code.emitPushInt(1);
        code.emitElse();
        code.emitPushInt(0);
        code.emitFi();
    }

    public Object coerceFromObject(Class clas, Object obj) {
        return getTypeFor(clas).coerceFromObject(obj);
    }

    public Object coerceToObject(Class clas, Object obj) {
        return getTypeFor(clas).coerceToObject(obj);
    }

    public static synchronized void setDefaults(Language language) {
        Language lang = language;
        synchronized (Language.class) {
            setCurrentLanguage(lang);
            global = lang;
            if (Environment.getGlobal() == BuiltinEnvironment.getInstance()) {
                Environment.setGlobal(Environment.getCurrent());
            }
        }
    }

    public Procedure getPrompter() {
        Procedure procedure;
        Object property = null;
        if (hasSeparateFunctionNamespace()) {
            property = EnvironmentKey.FUNCTION;
        }
        Procedure prompter = (Procedure) getEnvironment().get(getSymbol("default-prompter"), property, (Object) null);
        if (prompter != null) {
            return prompter;
        }
        new SimplePrompter();
        return procedure;
    }

    public final Object eval(String string) throws Throwable {
        InPort inPort;
        new CharArrayInPort(string);
        return eval(inPort);
    }

    public final Object eval(Reader reader) throws Throwable {
        InPort inPort;
        InPort inPort2;
        Reader in = reader;
        if (in instanceof InPort) {
            inPort2 = (InPort) in;
        } else {
            inPort2 = inPort;
            new InPort(in);
        }
        return eval(inPort2);
    }

    public final Object eval(InPort port) throws Throwable {
        CallContext ctx = CallContext.getInstance();
        int oldIndex = ctx.startFromContext();
        try {
            eval(port, ctx);
            return ctx.getFromContext(oldIndex);
        } catch (Throwable th) {
            Throwable ex = th;
            ctx.cleanupFromContext(oldIndex);
            throw ex;
        }
    }

    public final void eval(String string, Writer out) throws Throwable {
        Reader reader;
        new CharArrayInPort(string);
        eval(reader, out);
    }

    public final void eval(String string, PrintConsumer out) throws Throwable {
        eval(string, getOutputConsumer(out));
    }

    public final void eval(String string, Consumer out) throws Throwable {
        Reader reader;
        new CharArrayInPort(string);
        eval(reader, out);
    }

    public final void eval(Reader in, Writer out) throws Throwable {
        eval(in, getOutputConsumer(out));
    }

    public void eval(Reader reader, Consumer consumer) throws Throwable {
        InPort inPort;
        InPort inPort2;
        Reader in = reader;
        Consumer out = consumer;
        if (in instanceof InPort) {
            inPort2 = (InPort) in;
        } else {
            inPort2 = inPort;
            new InPort(in);
        }
        InPort port = inPort2;
        CallContext ctx = CallContext.getInstance();
        Consumer save = ctx.consumer;
        try {
            ctx.consumer = out;
            eval(port, ctx);
            ctx.consumer = save;
        } catch (Throwable th) {
            Throwable th2 = th;
            ctx.consumer = save;
            throw th2;
        }
    }

    public void eval(InPort port, CallContext callContext) throws Throwable {
        SourceMessages sourceMessages;
        Throwable th;
        StringBuilder sb;
        CallContext ctx = callContext;
        new SourceMessages();
        SourceMessages messages = sourceMessages;
        Language saveLang = setSaveCurrent(this);
        try {
            boolean evalModule = ModuleExp.evalModule(getEnvironment(), ctx, parse(port, messages, 3), (URL) null, (OutPort) null);
            restoreCurrent(saveLang);
            if (messages.seenErrors()) {
                Throwable th2 = th;
                new StringBuilder();
                new RuntimeException(sb.append("invalid syntax in eval form:\n").append(messages.toString(20)).toString());
                throw th2;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            restoreCurrent(saveLang);
            throw th4;
        }
    }

    public void runAsApplication(String[] args) {
        setDefaults(this);
        repl.main(args);
    }
}
