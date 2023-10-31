package gnu.expr;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Scope;
import gnu.bytecode.SwitchState;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.functions.Convert;
import gnu.kawa.lispexpr.LispReader;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.text.Lexer;
import gnu.text.Options;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Stack;
import java.util.Vector;
import java.util.jar.JarOutputStream;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.shaded.apache.http.HttpHost;

public class Compilation implements SourceLocator {
    public static final int BODY_PARSED = 4;
    public static final int CALL_WITH_CONSUMER = 2;
    public static final int CALL_WITH_CONTINUATIONS = 4;
    public static final int CALL_WITH_RETURN = 1;
    public static final int CALL_WITH_TAILCALLS = 3;
    public static final int CALL_WITH_UNSPECIFIED = 0;
    public static final int CLASS_WRITTEN = 14;
    public static final int COMPILED = 12;
    public static final int COMPILE_SETUP = 10;
    public static final int ERROR_SEEN = 100;
    public static final int MODULE_NONSTATIC = -1;
    public static final int MODULE_STATIC = 1;
    public static final int MODULE_STATIC_DEFAULT = 0;
    public static final int MODULE_STATIC_RUN = 2;
    public static final int PROLOG_PARSED = 2;
    public static final int PROLOG_PARSING = 1;
    public static final int RESOLVED = 6;
    public static final int WALKED = 8;
    public static Type[] apply0args = Type.typeArray0;
    public static Method apply0method = typeProcedure.addMethod("apply0", apply0args, (Type) typeObject, 17);
    public static Type[] apply1args = {typeObject};
    public static Method apply1method = typeProcedure.addMethod("apply1", apply1args, (Type) typeObject, 1);
    public static Type[] apply2args;
    public static Method apply2method = typeProcedure.addMethod("apply2", apply2args, (Type) typeObject, 1);
    public static Method apply3method;
    public static Method apply4method;
    private static Type[] applyCpsArgs = {typeCallContext};
    public static Method applyCpsMethod = typeProcedure.addMethod("apply", applyCpsArgs, (Type) Type.voidType, 1);
    public static Type[] applyNargs = {objArrayType};
    public static Method applyNmethod = typeProcedure.addMethod("applyN", applyNargs, (Type) typeObject, 1);
    public static Method[] applymethods;
    public static Field argsCallContextField = typeCallContext.getDeclaredField("values");
    private static Compilation chainUninitialized;
    static Method checkArgCountMethod = typeProcedure.addMethod("checkArgCount", new Type[]{typeProcedure, Type.intType}, (Type) Type.voidType, 9);
    public static String classPrefixDefault = "";
    private static final ThreadLocal<Compilation> current;
    public static boolean debugPrintExpr = false;
    public static boolean debugPrintFinalExpr;
    public static int defaultCallConvention;
    public static int defaultClassFileVersion = ClassType.JDK_1_5_VERSION;
    public static boolean emitSourceDebugExtAttr = true;
    public static final Field falseConstant = scmBooleanType.getDeclaredField("FALSE");
    public static boolean generateMainDefault = false;
    public static Method getCallContextInstanceMethod = typeCallContext.getDeclaredMethod("getInstance", 0);
    public static Method getCurrentEnvironmentMethod = typeEnvironment.addMethod("getCurrent", Type.typeArray0, (Type) typeEnvironment, 9);
    public static final Method getLocation1EnvironmentMethod = typeEnvironment.getDeclaredMethod("getLocation", 1);
    public static final Method getLocation2EnvironmentMethod;
    public static final Method getLocationMethod = typeLocation.addMethod("get", Type.typeArray0, (Type) Type.objectType, 1);
    public static final Method getProcedureBindingMethod = typeSymbol.addMethod("getProcedure", Type.typeArray0, (Type) typeProcedure, 1);
    public static final Method getSymbolProcedureMethod = typeLanguage.getDeclaredMethod("getSymbolProcedure", 1);
    public static final Method getSymbolValueMethod = typeLanguage.getDeclaredMethod("getSymbolValue", 1);
    public static boolean inlineOk = true;
    public static final Type[] int1Args = {Type.intType};
    public static ClassType javaStringType = typeString;
    static Method makeListMethod;
    public static int moduleStatic = 0;
    public static Field noArgsField = typeValues.getDeclaredField("noArgs");
    public static final ArrayType objArrayType = ArrayType.make((Type) typeObject);
    public static Options options;
    public static Field pcCallContextField = typeCallContext.getDeclaredField("pc");
    public static Field procCallContextField = typeCallContext.getDeclaredField("proc");
    public static ClassType scmBooleanType = ClassType.make("java.lang.Boolean");
    public static ClassType scmKeywordType = ClassType.make("gnu.expr.Keyword");
    public static ClassType scmListType = ClassType.make("gnu.lists.LList");
    public static ClassType scmSequenceType = ClassType.make("gnu.lists.Sequence");
    static final Method setNameMethod = typeProcedure.getDeclaredMethod("setName", 1);
    public static final Type[] string1Arg = {javaStringType};
    public static final Type[] sym1Arg = string1Arg;
    public static final Field trueConstant = scmBooleanType.getDeclaredField("TRUE");
    public static ClassType typeApplet = ClassType.make("java.applet.Applet");
    public static ClassType typeCallContext = ClassType.make("gnu.mapping.CallContext");
    public static ClassType typeClass = Type.javalangClassType;
    public static ClassType typeClassType = ClassType.make("gnu.bytecode.ClassType");
    public static final ClassType typeConsumer = ClassType.make("gnu.lists.Consumer");
    public static ClassType typeEnvironment = ClassType.make("gnu.mapping.Environment");
    public static ClassType typeLanguage = ClassType.make("gnu.expr.Language");
    public static ClassType typeLocation = ClassType.make("gnu.mapping.Location");
    public static ClassType typeMethodProc = ClassType.make("gnu.mapping.MethodProc");
    public static ClassType typeModuleBody = ClassType.make("gnu.expr.ModuleBody");
    public static ClassType typeModuleMethod = ClassType.make("gnu.expr.ModuleMethod");
    public static ClassType typeModuleWithContext = ClassType.make("gnu.expr.ModuleWithContext");
    public static ClassType typeObject = Type.objectType;
    public static ClassType typeObjectType = ClassType.make("gnu.bytecode.ObjectType");
    public static ClassType typePair = ClassType.make("gnu.lists.Pair");
    public static ClassType typeProcedure = ClassType.make("gnu.mapping.Procedure");
    public static ClassType typeProcedure0 = ClassType.make("gnu.mapping.Procedure0");
    public static ClassType typeProcedure1 = ClassType.make("gnu.mapping.Procedure1");
    public static ClassType typeProcedure2 = ClassType.make("gnu.mapping.Procedure2");
    public static ClassType typeProcedure3 = ClassType.make("gnu.mapping.Procedure3");
    public static ClassType typeProcedure4 = ClassType.make("gnu.mapping.Procedure4");
    public static ClassType[] typeProcedureArray;
    public static ClassType typeProcedureN = ClassType.make("gnu.mapping.ProcedureN");
    public static ClassType typeRunnable = ClassType.make("java.lang.Runnable");
    public static ClassType typeServlet = ClassType.make("gnu.kawa.servlet.KawaServlet");
    public static ClassType typeString = ClassType.make("java.lang.String");
    public static ClassType typeSymbol = ClassType.make("gnu.mapping.Symbol");
    public static ClassType typeType = ClassType.make("gnu.bytecode.Type");
    public static ClassType typeValues = ClassType.make("gnu.mapping.Values");
    public static Options.OptionInfo warnAsError = options.add("warn-as-error", 1, Boolean.FALSE, "Make all warnings into errors");
    public static Options.OptionInfo warnInvokeUnknownMethod = options.add("warn-invoke-unknown-method", 1, warnUnknownMember, "warn if invoke calls an unknown method (subsumed by warn-unknown-member)");
    public static Options.OptionInfo warnUndefinedVariable = options.add("warn-undefined-variable", 1, Boolean.TRUE, "warn if no compiler-visible binding for a variable");
    public static Options.OptionInfo warnUnknownMember = options.add("warn-unknown-member", 1, Boolean.TRUE, "warn if referencing an unknown method or field");
    Variable callContextVar;
    Variable callContextVarForInit;
    public String classPrefix;
    ClassType[] classes;
    Initializer clinitChain;
    Method clinitMethod;
    public ClassType curClass;
    public LambdaExp curLambda;
    public Options currentOptions;
    protected ScopeExp current_scope;
    public boolean explicit;
    public Stack<Expression> exprStack;
    Method forNameHelper;
    SwitchState fswitch;
    Field fswitchIndex;
    public boolean generateMain;
    public boolean immediate;
    private int keyUninitialized;
    int langOptions;
    protected Language language;
    public Lexer lexer;
    public NameLookup lexical;
    LitTable litTable;
    ArrayClassLoader loader;
    int localFieldIndex;
    public ClassType mainClass;
    public ModuleExp mainLambda;
    int maxSelectorValue;
    protected SourceMessages messages;
    public Method method;
    int method_counter;
    public ModuleInfo minfo;
    public ClassType moduleClass;
    Field moduleInstanceMainField;
    Variable moduleInstanceVar;
    public boolean mustCompile = ModuleExp.alwaysCompile;
    private Compilation nextUninitialized;
    int numClasses;
    boolean pedantic;
    public Stack<Object> pendingImports;
    private int state;
    public Variable thisDecl;

    public int getState() {
        return this.state;
    }

    public void setState(int state2) {
        int i = state2;
        this.state = i;
    }

    public boolean isPedantic() {
        return this.pedantic;
    }

    public void pushPendingImport(ModuleInfo moduleInfo, ScopeExp scopeExp, int i) {
        Expression expression;
        Stack<Object> stack;
        ModuleInfo info = moduleInfo;
        ScopeExp defs = scopeExp;
        int formSize = i;
        if (this.pendingImports == null) {
            new Stack<>();
            this.pendingImports = stack;
        }
        Object push = this.pendingImports.push(info);
        Object push2 = this.pendingImports.push(defs);
        new ReferenceExp((Object) null);
        Expression posExp = expression;
        posExp.setLine(this);
        Object push3 = this.pendingImports.push(posExp);
        Object push4 = this.pendingImports.push(Integer.valueOf(formSize));
    }

    static {
        Options options2;
        ThreadLocal<Compilation> threadLocal;
        new Options();
        options = options2;
        Type[] typeArr = new Type[2];
        typeArr[0] = typeSymbol;
        Type[] args = typeArr;
        args[1] = Type.objectType;
        getLocation2EnvironmentMethod = typeEnvironment.addMethod("getLocation", args, (Type) typeLocation, 17);
        Type[] typeArr2 = new Type[2];
        typeArr2[0] = objArrayType;
        Type[] args2 = typeArr2;
        args2[1] = Type.intType;
        makeListMethod = scmListType.addMethod("makeList", args2, (Type) scmListType, 9);
        Type[] typeArr3 = new Type[2];
        typeArr3[0] = typeObject;
        Type[] typeArr4 = typeArr3;
        typeArr4[1] = typeObject;
        apply2args = typeArr4;
        Type[] typeArr5 = new Type[3];
        typeArr5[0] = typeObject;
        Type[] typeArr6 = typeArr5;
        typeArr6[1] = typeObject;
        Type[] apply3args = typeArr6;
        apply3args[2] = typeObject;
        apply3method = typeProcedure.addMethod("apply3", apply3args, (Type) typeObject, 1);
        Type[] typeArr7 = new Type[4];
        typeArr7[0] = typeObject;
        Type[] typeArr8 = typeArr7;
        typeArr8[1] = typeObject;
        Type[] typeArr9 = typeArr8;
        typeArr9[2] = typeObject;
        Type[] apply4args = typeArr9;
        apply4args[3] = typeObject;
        apply4method = typeProcedure.addMethod("apply4", apply4args, (Type) typeObject, 1);
        Method[] methodArr = new Method[6];
        methodArr[0] = apply0method;
        Method[] methodArr2 = methodArr;
        methodArr2[1] = apply1method;
        Method[] methodArr3 = methodArr2;
        methodArr3[2] = apply2method;
        Method[] methodArr4 = methodArr3;
        methodArr4[3] = apply3method;
        Method[] methodArr5 = methodArr4;
        methodArr5[4] = apply4method;
        Method[] methodArr6 = methodArr5;
        methodArr6[5] = applyNmethod;
        applymethods = methodArr6;
        ClassType[] classTypeArr = new ClassType[5];
        classTypeArr[0] = typeProcedure0;
        ClassType[] classTypeArr2 = classTypeArr;
        classTypeArr2[1] = typeProcedure1;
        ClassType[] classTypeArr3 = classTypeArr2;
        classTypeArr3[2] = typeProcedure2;
        ClassType[] classTypeArr4 = classTypeArr3;
        classTypeArr4[3] = typeProcedure3;
        ClassType[] classTypeArr5 = classTypeArr4;
        classTypeArr5[4] = typeProcedure4;
        typeProcedureArray = classTypeArr5;
        new InheritableThreadLocal();
        current = threadLocal;
    }

    public boolean warnUndefinedVariable() {
        return this.currentOptions.getBoolean(warnUndefinedVariable);
    }

    public boolean warnUnknownMember() {
        return this.currentOptions.getBoolean(warnUnknownMember);
    }

    public boolean warnInvokeUnknownMethod() {
        return this.currentOptions.getBoolean(warnInvokeUnknownMethod);
    }

    public boolean warnAsError() {
        return this.currentOptions.getBoolean(warnAsError);
    }

    public final boolean getBooleanOption(String key, boolean defaultValue) {
        return this.currentOptions.getBoolean(key, defaultValue);
    }

    public final boolean getBooleanOption(String key) {
        return this.currentOptions.getBoolean(key);
    }

    public boolean usingCPStyle() {
        return defaultCallConvention == 4;
    }

    public boolean usingTailCalls() {
        return defaultCallConvention >= 3;
    }

    public final CodeAttr getCode() {
        return this.method.getCode();
    }

    public boolean generatingApplet() {
        return (this.langOptions & 16) != 0;
    }

    public boolean generatingServlet() {
        return (this.langOptions & 32) != 0;
    }

    public boolean sharedModuleDefs() {
        return (this.langOptions & 2) != 0;
    }

    public void setSharedModuleDefs(boolean shared) {
        if (shared) {
            this.langOptions |= 2;
            return;
        }
        this.langOptions &= -3;
    }

    public final ClassType getModuleType() {
        return defaultCallConvention >= 2 ? typeModuleWithContext : typeModuleBody;
    }

    public void compileConstant(Object obj) {
        Object value = obj;
        CodeAttr code = getCode();
        if (value == null) {
            code.emitPushNull();
        } else if (!(value instanceof String) || this.immediate) {
            code.emitGetStatic(compileConstantToField(value));
        } else {
            code.emitPushString((String) value);
        }
    }

    public Field compileConstantToField(Object value) {
        Literal literal = this.litTable.findLiteral(value);
        if (literal.field == null) {
            literal.assign(this.litTable);
        }
        return literal.field;
    }

    public boolean inlineOk(Expression expression) {
        Expression proc = expression;
        if (proc instanceof LambdaExp) {
            LambdaExp lproc = (LambdaExp) proc;
            Declaration nameDecl = lproc.nameDecl;
            if (nameDecl == null || nameDecl.getSymbol() == null || !(nameDecl.context instanceof ModuleExp)) {
                return true;
            }
            if (this.immediate && nameDecl.isPublic() && !lproc.getFlag(2048) && (this.curLambda == null || lproc.topLevel() != this.curLambda.topLevel())) {
                return false;
            }
        }
        return inlineOk;
    }

    public boolean inlineOk(Procedure procedure) {
        Procedure proc = procedure;
        if (!this.immediate || !(proc instanceof ModuleMethod) || !(((ModuleMethod) proc).module.getClass().getClassLoader() instanceof ArrayClassLoader)) {
            return inlineOk;
        }
        return false;
    }

    public void compileConstant(Object obj, Target target) {
        Type make;
        StringBuffer stringBuffer;
        Object value = obj;
        Target target2 = target;
        if (!(target2 instanceof IgnoreTarget)) {
            if (value instanceof Values) {
                Object[] values = ((Values) value).getValues();
                int len = values.length;
                if (target2 instanceof ConsumerTarget) {
                    for (int i = 0; i < len; i++) {
                        compileConstant(values[i], target2);
                    }
                    return;
                }
            }
            if (target2 instanceof ConditionalTarget) {
                ConditionalTarget ctarg = (ConditionalTarget) target2;
                getCode().emitGoto(getLanguage().isTrue(value) ? ctarg.ifTrue : ctarg.ifFalse);
                return;
            }
            if (target2 instanceof StackTarget) {
                Type type = ((StackTarget) target2).getType();
                if (type instanceof PrimType) {
                    try {
                        String signature = type.getSignature();
                        CodeAttr code = getCode();
                        char sig1 = (signature == null || signature.length() != 1) ? ' ' : signature.charAt(0);
                        if (value instanceof Number) {
                            Number num = (Number) value;
                            switch (sig1) {
                                case 'B':
                                    code.emitPushInt(num.byteValue());
                                    return;
                                case 'D':
                                    code.emitPushDouble(num.doubleValue());
                                    return;
                                case 'F':
                                    code.emitPushFloat(num.floatValue());
                                    return;
                                case 'I':
                                    code.emitPushInt(num.intValue());
                                    return;
                                case 'J':
                                    code.emitPushLong(num.longValue());
                                    return;
                                case 'S':
                                    code.emitPushInt(num.shortValue());
                                    return;
                            }
                        }
                        if (sig1 == 'C') {
                            code.emitPushInt(((PrimType) type).charValue(value));
                            return;
                        } else if (sig1 == 'Z') {
                            code.emitPushInt(PrimType.booleanValue(value) ? 1 : 0);
                            return;
                        }
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                    }
                }
                if (type != typeClass || !(value instanceof ClassType)) {
                    try {
                        value = type.coerceFromObject(value);
                    } catch (Exception e2) {
                        Exception exc = e2;
                        new StringBuffer();
                        StringBuffer sbuf = stringBuffer;
                        if (value == Values.empty) {
                            StringBuffer append = sbuf.append("cannot convert void to ");
                        } else {
                            StringBuffer append2 = sbuf.append("cannot convert literal (of type ");
                            if (value == null) {
                                StringBuffer append3 = sbuf.append("<null>");
                            } else {
                                StringBuffer append4 = sbuf.append(value.getClass().getName());
                            }
                            StringBuffer append5 = sbuf.append(") to ");
                        }
                        StringBuffer append6 = sbuf.append(type.getName());
                        error('w', sbuf.toString());
                    }
                } else {
                    loadClassRef((ClassType) value);
                    return;
                }
            }
            compileConstant(value);
            Target target3 = target2;
            if (value == null) {
                make = target2.getType();
            } else {
                make = Type.make(value.getClass());
            }
            target3.compileFromStack(this, make);
        }
    }

    private void dumpInitializers(Initializer inits) {
        Initializer reverse = Initializer.reverse(inits);
        while (true) {
            Initializer init = reverse;
            if (init != null) {
                init.emit(this);
                reverse = init.next;
            } else {
                return;
            }
        }
    }

    public ClassType findNamedClass(String str) {
        String name = str;
        for (int i = 0; i < this.numClasses; i++) {
            if (name.equals(this.classes[i].getName())) {
                return this.classes[i];
            }
        }
        return null;
    }

    private static void putURLWords(String str, StringBuffer stringBuffer) {
        String name = str;
        StringBuffer sbuf = stringBuffer;
        int dot = name.indexOf(46);
        if (dot > 0) {
            putURLWords(name.substring(dot + 1), sbuf);
            StringBuffer append = sbuf.append('.');
            name = name.substring(0, dot);
        }
        StringBuffer append2 = sbuf.append(name);
    }

    public static String mangleURI(String str) {
        StringBuffer stringBuffer;
        int dot;
        int extLen;
        String name = str;
        boolean hasSlash = name.indexOf(47) >= 0;
        int len = name.length();
        if (len > 6 && name.startsWith("class:")) {
            return name.substring(6);
        }
        if (len > 5 && name.charAt(4) == ':' && name.substring(0, 4).equalsIgnoreCase(HttpHost.DEFAULT_SCHEME_NAME)) {
            name = name.substring(5);
            len -= 5;
            hasSlash = true;
        } else if (len > 4 && name.charAt(3) == ':' && name.substring(0, 3).equalsIgnoreCase("uri")) {
            name = name.substring(4);
            len -= 4;
        }
        int start = 0;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        while (true) {
            int slash = name.indexOf(47, start);
            int end = slash < 0 ? len : slash;
            boolean first = sbuf.length() == 0;
            if (first && hasSlash) {
                String host = name.substring(start, end);
                if (end - start > 4 && host.startsWith("www.")) {
                    host = host.substring(4);
                }
                putURLWords(host, sbuf);
            } else if (start != end) {
                if (!first) {
                    StringBuffer append = sbuf.append('.');
                }
                if (end == len && (dot = name.lastIndexOf(46, len)) > start + 1 && !first && ((extLen = len - dot) <= 4 || (extLen == 5 && name.endsWith("html")))) {
                    len -= extLen;
                    end = len;
                    name = name.substring(0, len);
                }
                StringBuffer append2 = sbuf.append(name.substring(start, end));
            }
            if (slash < 0) {
                return sbuf.toString();
            }
            start = slash + 1;
        }
    }

    public static String mangleName(String name) {
        return mangleName(name, -1);
    }

    public static String mangleNameIfNeeded(String str) {
        String name = str;
        if (name == null || isValidJavaName(name)) {
            return name;
        }
        return mangleName(name, 0);
    }

    public static boolean isValidJavaName(String str) {
        String name = str;
        int len = name.length();
        if (len == 0 || !Character.isJavaIdentifierStart(name.charAt(0))) {
            return false;
        }
        int i = len;
        do {
            i--;
            if (i <= 0) {
                return true;
            }
        } while (Character.isJavaIdentifierPart(name.charAt(i)));
        return false;
    }

    public static String mangleName(String name, boolean reversible) {
        return mangleName(name, reversible ? 1 : -1);
    }

    public static String mangleName(String str, int i) {
        StringBuffer stringBuffer;
        String name = str;
        int kind = i;
        boolean reversible = kind >= 0;
        int len = name.length();
        if (len == 6 && name.equals("*init*")) {
            return "<init>";
        }
        new StringBuffer(len);
        StringBuffer mangled = stringBuffer;
        boolean upcaseNext = false;
        int i2 = 0;
        while (i2 < len) {
            char ch = name.charAt(i2);
            if (upcaseNext) {
                ch = Character.toTitleCase(ch);
                upcaseNext = false;
            }
            if (Character.isDigit(ch)) {
                if (i2 == 0) {
                    StringBuffer append = mangled.append("$N");
                }
                StringBuffer append2 = mangled.append(ch);
            } else if (Character.isLetter(ch) || ch == '_') {
                StringBuffer append3 = mangled.append(ch);
            } else if (ch == '$') {
                StringBuffer append4 = mangled.append(kind > 1 ? "$$" : "$");
            } else {
                switch (ch) {
                    case '!':
                        StringBuffer append5 = mangled.append("$Ex");
                        break;
                    case '\"':
                        StringBuffer append6 = mangled.append("$Dq");
                        break;
                    case '#':
                        StringBuffer append7 = mangled.append("$Nm");
                        break;
                    case '%':
                        StringBuffer append8 = mangled.append("$Pc");
                        break;
                    case '&':
                        StringBuffer append9 = mangled.append("$Am");
                        break;
                    case '\'':
                        StringBuffer append10 = mangled.append("$Sq");
                        break;
                    case '(':
                        StringBuffer append11 = mangled.append("$LP");
                        break;
                    case ')':
                        StringBuffer append12 = mangled.append("$RP");
                        break;
                    case '*':
                        StringBuffer append13 = mangled.append("$St");
                        break;
                    case '+':
                        StringBuffer append14 = mangled.append("$Pl");
                        break;
                    case ',':
                        StringBuffer append15 = mangled.append("$Cm");
                        break;
                    case '-':
                        if (!reversible) {
                            char next = i2 + 1 < len ? name.charAt(i2 + 1) : 0;
                            if (next != '>') {
                                if (!Character.isLowerCase(next)) {
                                    StringBuffer append16 = mangled.append("$Mn");
                                    break;
                                }
                            } else {
                                StringBuffer append17 = mangled.append("$To$");
                                i2++;
                                break;
                            }
                        } else {
                            StringBuffer append18 = mangled.append("$Mn");
                            break;
                        }
                        break;
                    case '.':
                        StringBuffer append19 = mangled.append("$Dt");
                        break;
                    case '/':
                        StringBuffer append20 = mangled.append("$Sl");
                        break;
                    case ':':
                        StringBuffer append21 = mangled.append("$Cl");
                        break;
                    case ';':
                        StringBuffer append22 = mangled.append("$SC");
                        break;
                    case '<':
                        StringBuffer append23 = mangled.append("$Ls");
                        break;
                    case '=':
                        StringBuffer append24 = mangled.append("$Eq");
                        break;
                    case '>':
                        StringBuffer append25 = mangled.append("$Gr");
                        break;
                    case '?':
                        char first = mangled.length() > 0 ? mangled.charAt(0) : 0;
                        if (!reversible && i2 + 1 == len && Character.isLowerCase(first)) {
                            mangled.setCharAt(0, Character.toTitleCase(first));
                            StringBuffer insert = mangled.insert(0, "is");
                            break;
                        } else {
                            StringBuffer append26 = mangled.append("$Qu");
                            break;
                        }
                        break;
                    case '@':
                        StringBuffer append27 = mangled.append("$At");
                        break;
                    case '[':
                        StringBuffer append28 = mangled.append("$LB");
                        break;
                    case ']':
                        StringBuffer append29 = mangled.append("$RB");
                        break;
                    case '^':
                        StringBuffer append30 = mangled.append("$Up");
                        break;
                    case '{':
                        StringBuffer append31 = mangled.append("$LC");
                        break;
                    case '|':
                        StringBuffer append32 = mangled.append("$VB");
                        break;
                    case '}':
                        StringBuffer append33 = mangled.append("$RC");
                        break;
                    case '~':
                        StringBuffer append34 = mangled.append("$Tl");
                        break;
                    default:
                        StringBuffer append35 = mangled.append('$');
                        StringBuffer append36 = mangled.append(Character.forDigit((ch >> 12) & 15, 16));
                        StringBuffer append37 = mangled.append(Character.forDigit((ch >> 8) & 15, 16));
                        StringBuffer append38 = mangled.append(Character.forDigit((ch >> 4) & 15, 16));
                        StringBuffer append39 = mangled.append(Character.forDigit(ch & 15, 16));
                        break;
                }
                if (!reversible) {
                    upcaseNext = true;
                }
            }
            i2++;
        }
        String mname = mangled.toString();
        return mname.equals(name) ? name : mname;
    }

    public static char demangle2(char char1, char char2) {
        switch ((char1 << 16) | char2) {
            case 'm':
                return '&';
            case 't':
                return '@';
            case 'l':
                return ':';
            case 'm':
                return ',';
            case 'q':
                return '\"';
            case 't':
                return '.';
            case 'q':
                return '=';
            case 'x':
                return '!';
            case 'r':
                return '>';
            case 'B':
                return '[';
            case 'C':
                return '{';
            case 'P':
                return '(';
            case 's':
                return '<';
            case 'c':
                return '%';
            case 'n':
                return '-';
            case 'm':
                return '#';
            case 'c':
                return '%';
            case 'l':
                return '+';
            case 'u':
                return '?';
            case 'B':
                return ']';
            case 'C':
                return '}';
            case 'P':
                return ')';
            case 'C':
                return ';';
            case 'l':
                return '/';
            case 'q':
                return '\\';
            case 't':
                return '*';
            case 'l':
                return '~';
            case 'p':
                return '^';
            case 'B':
                return '|';
            default:
                return LispReader.TOKEN_ESCAPE_CHAR;
        }
    }

    public static String demangleName(String name) {
        return demangleName(name, false);
    }

    public static String demangleName(String str, boolean z) {
        StringBuffer stringBuffer;
        String name = str;
        boolean reversible = z;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        int len = name.length();
        boolean mangled = false;
        boolean predicate = false;
        boolean downCaseNext = false;
        int i = 0;
        while (i < len) {
            char ch = name.charAt(i);
            if (downCaseNext && !reversible) {
                ch = Character.toLowerCase(ch);
                downCaseNext = false;
            }
            if (!reversible && ch == 'i' && i == 0 && len > 2 && name.charAt(i + 1) == 's') {
                char charAt = name.charAt(i + 2);
                char d = charAt;
                if (!Character.isLowerCase(charAt)) {
                    mangled = true;
                    predicate = true;
                    i++;
                    if (Character.isUpperCase(d) || Character.isTitleCase(d)) {
                        StringBuffer append = sbuf.append(Character.toLowerCase(d));
                        i++;
                        i++;
                    } else {
                        i++;
                    }
                }
            }
            if (ch == '$' && i + 2 < len) {
                char c1 = name.charAt(i + 1);
                char c2 = name.charAt(i + 2);
                char d2 = demangle2(c1, c2);
                if (d2 != 65535) {
                    StringBuffer append2 = sbuf.append(d2);
                    i += 2;
                    mangled = true;
                    downCaseNext = true;
                } else if (c1 == 'T' && c2 == 'o' && i + 3 < len && name.charAt(i + 3) == '$') {
                    StringBuffer append3 = sbuf.append("->");
                    i += 3;
                    mangled = true;
                    downCaseNext = true;
                }
                i++;
            } else if (!reversible && i > 1 && ((Character.isUpperCase(ch) || Character.isTitleCase(ch)) && Character.isLowerCase(name.charAt(i - 1)))) {
                StringBuffer append4 = sbuf.append('-');
                mangled = true;
                ch = Character.toLowerCase(ch);
            }
            StringBuffer append5 = sbuf.append(ch);
            i++;
        }
        if (predicate) {
            StringBuffer append6 = sbuf.append('?');
        }
        return mangled ? sbuf.toString() : name;
    }

    public String generateClassName(String hint) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        String hint2 = mangleName(hint, true);
        if (this.mainClass != null) {
            new StringBuilder();
            hint2 = sb3.append(this.mainClass.getName()).append('$').append(hint2).toString();
        } else if (this.classPrefix != null) {
            new StringBuilder();
            hint2 = sb.append(this.classPrefix).append(hint2).toString();
        }
        if (findNamedClass(hint2) == null) {
            return hint2;
        }
        int i = 0;
        while (true) {
            new StringBuilder();
            String new_hint = sb2.append(hint2).append(i).toString();
            if (findNamedClass(new_hint) == null) {
                return new_hint;
            }
            i++;
        }
    }

    public Compilation(Language language2, SourceMessages messages2, NameLookup lexical2) {
        Options options2;
        new Options(options);
        this.currentOptions = options2;
        this.generateMain = generateMainDefault;
        this.classPrefix = classPrefixDefault;
        this.language = language2;
        this.messages = messages2;
        this.lexical = lexical2;
    }

    public void walkModule(ModuleExp moduleExp) {
        StringBuilder sb;
        ModuleExp mexp = moduleExp;
        if (debugPrintExpr) {
            OutPort dout = OutPort.errDefault();
            new StringBuilder();
            dout.println(sb.append("[Module:").append(mexp.getName()).toString());
            mexp.print(dout);
            dout.println(']');
            dout.flush();
        }
        Expression inlineCalls = InlineCalls.inlineCalls(mexp, this);
        PushApply.pushApply(mexp);
        ChainLambdas.chainLambdas(mexp, this);
        FindTailCalls.findTailCalls(mexp, this);
    }

    public void outputClass(String str) throws IOException {
        StringBuilder sb;
        File file;
        File file2;
        String directory = str;
        char dirSep = File.separatorChar;
        for (int iClass = 0; iClass < this.numClasses; iClass++) {
            ClassType clas = this.classes[iClass];
            new StringBuilder();
            String out_name = sb.append(directory).append(clas.getName().replace('.', dirSep)).append(".class").toString();
            new File(out_name);
            String parent = file.getParent();
            if (parent != null) {
                new File(parent);
                boolean mkdirs = file2.mkdirs();
            }
            clas.writeToFile(out_name);
        }
        this.minfo.cleanupAfterCompilation();
    }

    public void cleanupAfterCompilation() {
        for (int iClass = 0; iClass < this.numClasses; iClass++) {
            this.classes[iClass].cleanupAfterCompilation();
        }
        this.classes = null;
        this.minfo.comp = null;
        if (this.minfo.exp != null) {
            this.minfo.exp.body = null;
        }
        this.mainLambda.body = null;
        this.mainLambda = null;
        if (!this.immediate) {
            this.litTable = null;
        }
    }

    public void compileToArchive(ModuleExp moduleExp, String str) throws IOException {
        StringBuilder sb;
        boolean makeJar;
        File file;
        ZipOutputStream zipOutputStream;
        OutputStream outputStream;
        ZipOutputStream zout;
        CRC32 crc32;
        ZipEntry zipEntry;
        StringBuilder sb2;
        ZipOutputStream zipOutputStream2;
        OutputStream outputStream2;
        ModuleExp moduleExp2 = moduleExp;
        String fname = str;
        if (fname.endsWith(".zip")) {
            makeJar = false;
        } else if (fname.endsWith(".jar")) {
            makeJar = true;
        } else {
            new StringBuilder();
            fname = sb.append(fname).append(".zip").toString();
            makeJar = false;
        }
        process(12);
        new File(fname);
        File zar_file = file;
        if (zar_file.exists()) {
            boolean delete = zar_file.delete();
        }
        if (makeJar) {
            new FileOutputStream(zar_file);
            new JarOutputStream(outputStream2);
            zout = zipOutputStream2;
        } else {
            new FileOutputStream(zar_file);
            new ZipOutputStream(outputStream);
            zout = zipOutputStream;
        }
        byte[][] classBytes = new byte[this.numClasses][];
        new CRC32();
        CRC32 zcrc = crc32;
        for (int iClass = 0; iClass < this.numClasses; iClass++) {
            ClassType clas = this.classes[iClass];
            classBytes[iClass] = clas.writeToArray();
            new StringBuilder();
            new ZipEntry(sb2.append(clas.getName().replace('.', '/')).append(".class").toString());
            ZipEntry zent = zipEntry;
            zent.setSize((long) classBytes[iClass].length);
            zcrc.reset();
            zcrc.update(classBytes[iClass], 0, classBytes[iClass].length);
            zent.setCrc(zcrc.getValue());
            zout.putNextEntry(zent);
            zout.write(classBytes[iClass]);
        }
        zout.close();
    }

    private void registerClass(ClassType classType) {
        ClassType new_class = classType;
        if (this.classes == null) {
            this.classes = new ClassType[20];
        } else if (this.numClasses >= this.classes.length) {
            ClassType[] new_classes = new ClassType[(2 * this.classes.length)];
            System.arraycopy(this.classes, 0, new_classes, 0, this.numClasses);
            this.classes = new_classes;
        }
        new_class.addModifiers(new_class.isInterface() ? 1 : 33);
        if (new_class == this.mainClass && this.numClasses > 0) {
            new_class = this.classes[0];
            this.classes[0] = this.mainClass;
        }
        ClassType[] classTypeArr = this.classes;
        int i = this.numClasses;
        int i2 = i + 1;
        this.numClasses = i2;
        classTypeArr[i] = new_class;
    }

    public void addClass(ClassType classType) {
        ClassType new_class = classType;
        if (this.mainLambda.filename != null) {
            if (emitSourceDebugExtAttr) {
                new_class.setStratum(getLanguage().getName());
            }
            new_class.setSourceFile(this.mainLambda.filename);
        }
        registerClass(new_class);
        new_class.setClassfileVersion(defaultClassFileVersion);
    }

    public boolean makeRunnable() {
        return !generatingServlet() && !generatingApplet() && !getModule().staticInitRun();
    }

    public void addMainClass(ModuleExp moduleExp) {
        ModuleExp module = moduleExp;
        this.mainClass = module.classFor(this);
        ClassType type = this.mainClass;
        ClassType[] interfaces = module.getInterfaces();
        if (interfaces != null) {
            type.setInterfaces(interfaces);
        }
        ClassType sup = module.getSuperType();
        if (sup == null) {
            if (generatingApplet()) {
                sup = typeApplet;
            } else if (generatingServlet()) {
                sup = typeServlet;
            } else {
                sup = getModuleType();
            }
        }
        if (makeRunnable()) {
            type.addInterface(typeRunnable);
        }
        type.setSuper(sup);
        module.type = type;
        addClass(type);
        Method constructor = getConstructor(this.mainClass, module);
    }

    public final Method getConstructor(LambdaExp lambdaExp) {
        LambdaExp lexp = lambdaExp;
        return getConstructor(lexp.getHeapFrameType(), lexp);
    }

    public static final Method getConstructor(ClassType classType, LambdaExp lambdaExp) {
        Type[] args;
        ClassType clas = classType;
        LambdaExp lexp = lambdaExp;
        Method meth = clas.getDeclaredMethod("<init>", 0);
        if (meth != null) {
            return meth;
        }
        if (!(lexp instanceof ClassExp) || lexp.staticLinkField == null) {
            args = apply0args;
        } else {
            args = new Type[1];
            args[0] = lexp.staticLinkField.getType();
        }
        return clas.addMethod("<init>", 1, args, (Type) Type.voidType);
    }

    public final void generateConstructor(LambdaExp lambdaExp) {
        LambdaExp lexp = lambdaExp;
        generateConstructor(lexp.getHeapFrameType(), lexp);
    }

    public final void generateConstructor(ClassType classType, LambdaExp lambdaExp) {
        Vector vector;
        LambdaExp lambdaExp2;
        ClassType clas = classType;
        LambdaExp lexp = lambdaExp;
        Method save_method = this.method;
        Variable callContextSave = this.callContextVar;
        this.callContextVar = null;
        ClassType save_class = this.curClass;
        this.curClass = clas;
        Method constructor_method = getConstructor(clas, lexp);
        clas.constructor = constructor_method;
        this.method = constructor_method;
        CodeAttr code = constructor_method.startCode();
        if ((lexp instanceof ClassExp) && lexp.staticLinkField != null) {
            code.emitPushThis();
            code.emitLoad(code.getCurrentScope().getVariable(1));
            code.emitPutField(lexp.staticLinkField);
        }
        ClassExp.invokeDefaultSuperConstructor(clas.getSuperclass(), this, lexp);
        if (!(this.curClass != this.mainClass || this.minfo == null || this.minfo.sourcePath == null)) {
            code.emitPushThis();
            code.emitInvokeStatic(ClassType.make("gnu.expr.ModuleInfo").getDeclaredMethod("register", 1));
        }
        if (!(lexp == null || lexp.initChain == null)) {
            LambdaExp save = this.curLambda;
            new LambdaExp();
            this.curLambda = lambdaExp2;
            this.curLambda.closureEnv = code.getArg(0);
            this.curLambda.outer = save;
            while (true) {
                Initializer initializer = lexp.initChain;
                Initializer init = initializer;
                if (initializer == null) {
                    break;
                }
                lexp.initChain = null;
                dumpInitializers(init);
            }
            this.curLambda = save;
        }
        if (lexp instanceof ClassExp) {
            new Vector(10);
            callInitMethods(((ClassExp) lexp).getCompiledClassType(this), vector);
        }
        code.emitReturn();
        this.method = save_method;
        this.curClass = save_class;
        this.callContextVar = callContextSave;
    }

    /* access modifiers changed from: package-private */
    public void callInitMethods(ClassType classType, Vector<ClassType> vector) {
        StringBuilder sb;
        ClassType clas = classType;
        Vector<ClassType> seen = vector;
        if (clas != null) {
            String name = clas.getName();
            if (!"java.lang.Object".equals(name)) {
                int i = seen.size();
                do {
                    i--;
                    if (i < 0) {
                        seen.addElement(clas);
                        ClassType[] interfaces = clas.getInterfaces();
                        if (interfaces != null) {
                            int n = interfaces.length;
                            for (int i2 = 0; i2 < n; i2++) {
                                callInitMethods(interfaces[i2], seen);
                            }
                        }
                        int clEnvArgs = 1;
                        if (!clas.isInterface()) {
                            clEnvArgs = 0;
                        } else if (clas instanceof PairClassType) {
                            clas = ((PairClassType) clas).instanceType;
                        } else {
                            try {
                                new StringBuilder();
                                clas = (ClassType) Type.make(Class.forName(sb.append(clas.getName()).append("$class").toString()));
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                return;
                            }
                        }
                        Method meth = clas.getDeclaredMethod("$finit$", clEnvArgs);
                        if (meth != null) {
                            CodeAttr code = getCode();
                            code.emitPushThis();
                            code.emitInvoke(meth);
                            return;
                        }
                        return;
                    }
                } while (seen.elementAt(i).getName() != name);
            }
        }
    }

    public void generateMatchMethods(LambdaExp lambdaExp) {
        int methodIndex;
        int i;
        StringBuilder sb;
        Label label;
        Label label2;
        Target target;
        StringBuilder sb2;
        LambdaExp lexp = lambdaExp;
        int numApplyMethods = lexp.applyMethods == null ? 0 : lexp.applyMethods.size();
        if (numApplyMethods != 0) {
            Method save_method = this.method;
            ClassType save_class = this.curClass;
            ClassType procType = typeModuleMethod;
            this.curClass = lexp.getHeapFrameType();
            if (!this.curClass.getSuperclass().isSubtype(typeModuleBody)) {
                this.curClass = this.moduleClass;
            }
            CodeAttr code = null;
            int i2 = 0;
            while (i2 <= 5) {
                boolean needThisMatch = false;
                SwitchState aswitch = null;
                String mname = null;
                Type[] matchArgs = null;
                int j = numApplyMethods;
                while (true) {
                    j--;
                    if (j < 0) {
                        break;
                    }
                    LambdaExp source = (LambdaExp) lexp.applyMethods.elementAt(j);
                    int numMethods = source.primMethods.length;
                    boolean varArgs = source.max_args < 0 || source.max_args >= source.min_args + numMethods;
                    if (i2 < 5) {
                        methodIndex = i2 - source.min_args;
                        if (methodIndex >= 0 && methodIndex < numMethods) {
                            if (methodIndex != numMethods - 1 || !varArgs) {
                            }
                        }
                    } else {
                        int methodIndex2 = 5 - source.min_args;
                        if (methodIndex2 <= 0 || numMethods > methodIndex2 || varArgs) {
                            methodIndex = numMethods - 1;
                        }
                    }
                    if (!needThisMatch) {
                        if (i2 < 5) {
                            new StringBuilder();
                            mname = sb2.append("match").append(i2).toString();
                            matchArgs = new Type[(i2 + 2)];
                            for (int k = i2; k >= 0; k--) {
                                matchArgs[k + 1] = typeObject;
                            }
                            matchArgs[i2 + 1] = typeCallContext;
                        } else {
                            mname = "matchN";
                            matchArgs = new Type[3];
                            matchArgs[1] = objArrayType;
                            matchArgs[2] = typeCallContext;
                        }
                        matchArgs[0] = procType;
                        this.method = this.curClass.addMethod(mname, matchArgs, (Type) Type.intType, 1);
                        code = this.method.startCode();
                        code.emitLoad(code.getArg(1));
                        code.emitGetField(procType.getField("selector"));
                        aswitch = code.startSwitch();
                        needThisMatch = true;
                    }
                    boolean addCase = aswitch.addCase(source.getSelectorValue(this), code);
                    int line = source.getLineNumber();
                    if (line > 0) {
                        code.putLineNumber(source.getFileName(), line);
                    }
                    CodeAttr codeAttr = code;
                    if (i2 == 5) {
                        i = 3;
                    } else {
                        i = i2 + 2;
                    }
                    Variable ctxVar = codeAttr.getArg(i);
                    if (i2 < 5) {
                        Declaration var = source.firstDecl();
                        for (int k2 = 1; k2 <= i2; k2++) {
                            code.emitLoad(ctxVar);
                            code.emitLoad(code.getArg(k2 + 1));
                            Type ptype = var.getType();
                            if (ptype != Type.objectType) {
                                if (ptype instanceof TypeValue) {
                                    new Label(code);
                                    Label trueLabel = label;
                                    new Label(code);
                                    Label falseLabel = label2;
                                    new ConditionalTarget(trueLabel, falseLabel, getLanguage());
                                    code.emitDup();
                                    ((TypeValue) ptype).emitIsInstance((Variable) null, this, target);
                                    falseLabel.define(code);
                                    code.emitPushInt(-786432 | k2);
                                    code.emitReturn();
                                    trueLabel.define(code);
                                } else if (!(!(ptype instanceof ClassType) || ptype == Type.objectType || ptype == Type.toStringType)) {
                                    code.emitDup();
                                    ptype.emitIsInstance(code);
                                    code.emitIfIntEqZero();
                                    code.emitPushInt(-786432 | k2);
                                    code.emitReturn();
                                    code.emitFi();
                                }
                            }
                            ClassType classType = typeCallContext;
                            new StringBuilder();
                            code.emitPutField(classType.getField(sb.append("value").append(k2).toString()));
                            var = var.nextDecl();
                        }
                    } else {
                        code.emitLoad(ctxVar);
                        code.emitLoad(code.getArg(2));
                        code.emitPutField(typeCallContext.getField("values"));
                    }
                    code.emitLoad(ctxVar);
                    if (defaultCallConvention < 2) {
                        code.emitLoad(code.getArg(1));
                    } else {
                        code.emitLoad(code.getArg(0));
                    }
                    code.emitPutField(procCallContextField);
                    code.emitLoad(ctxVar);
                    if (defaultCallConvention >= 2) {
                        code.emitPushInt(source.getSelectorValue(this) + methodIndex);
                    } else {
                        code.emitPushInt(i2);
                    }
                    code.emitPutField(pcCallContextField);
                    code.emitPushInt(0);
                    code.emitReturn();
                }
                if (needThisMatch) {
                    aswitch.addDefault(code);
                    int nargs = (i2 > 4 ? 2 : i2 + 1) + 1;
                    for (int k3 = 0; k3 <= nargs; k3++) {
                        code.emitLoad(code.getArg(k3));
                    }
                    code.emitInvokeSpecial(typeModuleBody.getDeclaredMethod(mname, matchArgs.length));
                    code.emitReturn();
                    aswitch.finish(code);
                }
                i2++;
            }
            this.method = save_method;
            this.curClass = save_class;
        }
    }

    public void generateApplyMethodsWithContext(LambdaExp lambdaExp) {
        int i;
        Throwable th;
        StringBuilder sb;
        StringBuilder sb2;
        LambdaExp lexp = lambdaExp;
        int numApplyMethods = lexp.applyMethods == null ? 0 : lexp.applyMethods.size();
        if (numApplyMethods != 0) {
            ClassType save_class = this.curClass;
            this.curClass = lexp.getHeapFrameType();
            if (!this.curClass.getSuperclass().isSubtype(typeModuleWithContext)) {
                this.curClass = this.moduleClass;
            }
            ClassType classType = typeModuleMethod;
            Method save_method = this.method;
            this.method = this.curClass.addMethod("apply", new Type[]{typeCallContext}, (Type) Type.voidType, 1);
            CodeAttr code = this.method.startCode();
            Variable ctxVar = code.getArg(1);
            code.emitLoad(ctxVar);
            code.emitGetField(pcCallContextField);
            SwitchState aswitch = code.startSwitch();
            for (int j = 0; j < numApplyMethods; j++) {
                LambdaExp source = (LambdaExp) lexp.applyMethods.elementAt(j);
                Method[] primMethods = source.primMethods;
                int numMethods = primMethods.length;
                int i2 = 0;
                while (i2 < numMethods) {
                    boolean varArgs = i2 == numMethods + -1 && (source.max_args < 0 || source.max_args >= source.min_args + numMethods);
                    int methodIndex = i2;
                    boolean addCase = aswitch.addCase(source.getSelectorValue(this) + i2, code);
                    SourceLocator saveLoc1 = this.messages.swapSourceLocator(source);
                    int line = source.getLineNumber();
                    if (line > 0) {
                        code.putLineNumber(source.getFileName(), line);
                    }
                    Method primMethod = primMethods[methodIndex];
                    Type[] primArgTypes = primMethod.getParameterTypes();
                    int singleArgs = source.min_args + methodIndex;
                    Variable counter = null;
                    int pendingIfEnds = 0;
                    if (i2 > 4 && numMethods > 1) {
                        counter = code.addLocal(Type.intType);
                        code.emitLoad(ctxVar);
                        code.emitInvoke(typeCallContext.getDeclaredMethod("getArgCount", 0));
                        if (source.min_args != 0) {
                            code.emitPushInt(source.min_args);
                            code.emitSub(Type.intType);
                        }
                        code.emitStore(counter);
                    }
                    int needsThis = primMethod.getStaticFlag() ? 0 : 1;
                    if (singleArgs + (varArgs ? 2 : 1) < primArgTypes.length) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    int explicitFrameArg = i;
                    if (needsThis + explicitFrameArg > 0) {
                        code.emitPushThis();
                        if (this.curClass == this.moduleClass && this.mainClass != this.moduleClass) {
                            code.emitGetField(this.moduleInstanceMainField);
                        }
                    }
                    Declaration var = source.firstDecl();
                    if (var != null && var.isThisParameter()) {
                        var = var.nextDecl();
                    }
                    for (int k = 0; k < singleArgs; k++) {
                        if (counter != null && k >= source.min_args) {
                            code.emitLoad(counter);
                            code.emitIfIntLEqZero();
                            code.emitLoad(ctxVar);
                            code.emitInvoke(primMethods[k - source.min_args]);
                            code.emitElse();
                            pendingIfEnds++;
                            code.emitInc(counter, -1);
                        }
                        code.emitLoad(ctxVar);
                        if (k > 4 || varArgs || source.max_args > 4) {
                            code.emitGetField(typeCallContext.getDeclaredField("values"));
                            code.emitPushInt(k);
                            code.emitArrayLoad(Type.objectType);
                        } else {
                            ClassType classType2 = typeCallContext;
                            new StringBuilder();
                            code.emitGetField(classType2.getDeclaredField(sb2.append("value").append(k + 1).toString()));
                        }
                        Type ptype = var.getType();
                        if (ptype != Type.objectType) {
                            SourceLocator saveLoc2 = this.messages.swapSourceLocator(var);
                            CheckedTarget.emitCheckedCoerce(this, source, k + 1, ptype);
                            SourceLocator swapSourceLocator = this.messages.swapSourceLocator(saveLoc2);
                        }
                        var = var.nextDecl();
                    }
                    if (varArgs) {
                        Type lastArgType = primArgTypes[explicitFrameArg + singleArgs];
                        if (lastArgType instanceof ArrayType) {
                            varArgsToArray(source, singleArgs, counter, lastArgType, ctxVar);
                        } else if ("gnu.lists.LList".equals(lastArgType.getName())) {
                            code.emitLoad(ctxVar);
                            code.emitPushInt(singleArgs);
                            code.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getRestArgsList", 1));
                        } else if (lastArgType == typeCallContext) {
                            code.emitLoad(ctxVar);
                        } else {
                            Throwable th2 = th;
                            new StringBuilder();
                            new RuntimeException(sb.append("unsupported #!rest type:").append(lastArgType).toString());
                            throw th2;
                        }
                    }
                    code.emitLoad(ctxVar);
                    code.emitInvoke(primMethod);
                    while (true) {
                        pendingIfEnds--;
                        if (pendingIfEnds < 0) {
                            break;
                        }
                        code.emitFi();
                    }
                    if (defaultCallConvention < 2) {
                        Target.pushObject.compileFromStack(this, source.getReturnType());
                    }
                    SourceLocator swapSourceLocator2 = this.messages.swapSourceLocator(saveLoc1);
                    code.emitReturn();
                    i2++;
                }
            }
            aswitch.addDefault(code);
            code.emitInvokeStatic(typeModuleMethod.getDeclaredMethod("applyError", 0));
            code.emitReturn();
            aswitch.finish(code);
            this.method = save_method;
            this.curClass = save_class;
        }
    }

    public void generateApplyMethodsWithoutContext(LambdaExp lambdaExp) {
        int methodIndex;
        int i;
        Throwable th;
        StringBuilder sb;
        StringBuilder sb2;
        LambdaExp lexp = lambdaExp;
        int numApplyMethods = lexp.applyMethods == null ? 0 : lexp.applyMethods.size();
        if (numApplyMethods != 0) {
            ClassType save_class = this.curClass;
            this.curClass = lexp.getHeapFrameType();
            ClassType procType = typeModuleMethod;
            if (!this.curClass.getSuperclass().isSubtype(typeModuleBody)) {
                this.curClass = this.moduleClass;
            }
            Method save_method = this.method;
            CodeAttr code = null;
            int i2 = defaultCallConvention >= 2 ? 5 : 0;
            while (i2 < 6) {
                boolean needThisApply = false;
                SwitchState aswitch = null;
                String mname = null;
                Type[] applyArgs = null;
                for (int j = 0; j < numApplyMethods; j++) {
                    LambdaExp source = (LambdaExp) lexp.applyMethods.elementAt(j);
                    Method[] primMethods = source.primMethods;
                    int numMethods = primMethods.length;
                    boolean varArgs = source.max_args < 0 || source.max_args >= source.min_args + numMethods;
                    boolean skipThisProc = false;
                    if (i2 < 5) {
                        methodIndex = i2 - source.min_args;
                        if (methodIndex < 0 || methodIndex >= numMethods || (methodIndex == numMethods - 1 && varArgs)) {
                            skipThisProc = true;
                        }
                        numMethods = 1;
                        varArgs = false;
                    } else {
                        int methodIndex2 = 5 - source.min_args;
                        if (methodIndex2 > 0 && numMethods <= methodIndex2 && !varArgs) {
                            skipThisProc = true;
                        }
                        methodIndex = numMethods - 1;
                    }
                    if (!skipThisProc) {
                        if (!needThisApply) {
                            if (i2 < 5) {
                                new StringBuilder();
                                mname = sb2.append("apply").append(i2).toString();
                                applyArgs = new Type[(i2 + 1)];
                                for (int k = i2; k > 0; k--) {
                                    applyArgs[k] = typeObject;
                                }
                            } else {
                                mname = "applyN";
                                applyArgs = new Type[2];
                                applyArgs[1] = objArrayType;
                            }
                            applyArgs[0] = procType;
                            this.method = this.curClass.addMethod(mname, applyArgs, defaultCallConvention >= 2 ? Type.voidType : Type.objectType, 1);
                            code = this.method.startCode();
                            code.emitLoad(code.getArg(1));
                            code.emitGetField(procType.getField("selector"));
                            aswitch = code.startSwitch();
                            needThisApply = true;
                        }
                        boolean addCase = aswitch.addCase(source.getSelectorValue(this), code);
                        SourceLocator saveLoc1 = this.messages.swapSourceLocator(source);
                        int line = source.getLineNumber();
                        if (line > 0) {
                            code.putLineNumber(source.getFileName(), line);
                        }
                        Method primMethod = primMethods[methodIndex];
                        Type[] primArgTypes = primMethod.getParameterTypes();
                        int singleArgs = source.min_args + methodIndex;
                        Variable counter = null;
                        int pendingIfEnds = 0;
                        if (i2 > 4 && numMethods > 1) {
                            counter = code.addLocal(Type.intType);
                            code.emitLoad(code.getArg(2));
                            code.emitArrayLength();
                            if (source.min_args != 0) {
                                code.emitPushInt(source.min_args);
                                code.emitSub(Type.intType);
                            }
                            code.emitStore(counter);
                        }
                        int needsThis = primMethod.getStaticFlag() ? 0 : 1;
                        if (singleArgs + (varArgs ? 1 : 0) < primArgTypes.length) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        int explicitFrameArg = i;
                        if (needsThis + explicitFrameArg > 0) {
                            code.emitPushThis();
                            if (this.curClass == this.moduleClass && this.mainClass != this.moduleClass) {
                                code.emitGetField(this.moduleInstanceMainField);
                            }
                        }
                        Declaration var = source.firstDecl();
                        if (var != null && var.isThisParameter()) {
                            var = var.nextDecl();
                        }
                        for (int k2 = 0; k2 < singleArgs; k2++) {
                            if (counter != null && k2 >= source.min_args) {
                                code.emitLoad(counter);
                                code.emitIfIntLEqZero();
                                code.emitInvoke(primMethods[k2 - source.min_args]);
                                code.emitElse();
                                pendingIfEnds++;
                                code.emitInc(counter, -1);
                            }
                            Variable pvar = null;
                            if (i2 <= 4) {
                                pvar = code.getArg(k2 + 2);
                                code.emitLoad(pvar);
                            } else {
                                code.emitLoad(code.getArg(2));
                                code.emitPushInt(k2);
                                code.emitArrayLoad(Type.objectType);
                            }
                            Type ptype = var.getType();
                            if (ptype != Type.objectType) {
                                SourceLocator saveLoc2 = this.messages.swapSourceLocator(var);
                                CheckedTarget.emitCheckedCoerce(this, source, k2 + 1, ptype, pvar);
                                SourceLocator swapSourceLocator = this.messages.swapSourceLocator(saveLoc2);
                            }
                            var = var.nextDecl();
                        }
                        if (varArgs) {
                            Type lastArgType = primArgTypes[explicitFrameArg + singleArgs];
                            if (lastArgType instanceof ArrayType) {
                                varArgsToArray(source, singleArgs, counter, lastArgType, (Variable) null);
                            } else if ("gnu.lists.LList".equals(lastArgType.getName())) {
                                code.emitLoad(code.getArg(2));
                                code.emitPushInt(singleArgs);
                                code.emitInvokeStatic(makeListMethod);
                            } else if (lastArgType == typeCallContext) {
                                code.emitLoad(code.getArg(2));
                            } else {
                                Throwable th2 = th;
                                new StringBuilder();
                                new RuntimeException(sb.append("unsupported #!rest type:").append(lastArgType).toString());
                                throw th2;
                            }
                        }
                        code.emitInvoke(primMethod);
                        while (true) {
                            pendingIfEnds--;
                            if (pendingIfEnds < 0) {
                                break;
                            }
                            code.emitFi();
                        }
                        if (defaultCallConvention < 2) {
                            Target.pushObject.compileFromStack(this, source.getReturnType());
                        }
                        SourceLocator swapSourceLocator2 = this.messages.swapSourceLocator(saveLoc1);
                        code.emitReturn();
                    }
                }
                if (needThisApply) {
                    aswitch.addDefault(code);
                    if (defaultCallConvention >= 2) {
                        code.emitInvokeStatic(typeModuleMethod.getDeclaredMethod("applyError", 0));
                    } else {
                        int nargs = (i2 > 4 ? 2 : i2 + 1) + 1;
                        for (int k3 = 0; k3 < nargs; k3++) {
                            code.emitLoad(code.getArg(k3));
                        }
                        code.emitInvokeSpecial(typeModuleBody.getDeclaredMethod(mname, applyArgs));
                    }
                    code.emitReturn();
                    aswitch.finish(code);
                }
                i2++;
            }
            this.method = save_method;
            this.curClass = save_class;
        }
    }

    private void varArgsToArray(LambdaExp lambdaExp, int i, Variable variable, Type lastArgType, Variable variable2) {
        Label label;
        Label label2;
        LambdaExp source = lambdaExp;
        int singleArgs = i;
        Variable counter = variable;
        Variable ctxVar = variable2;
        CodeAttr code = getCode();
        Type elType = ((ArrayType) lastArgType).getComponentType();
        boolean mustConvert = !"java.lang.Object".equals(elType.getName());
        if (ctxVar != null && !mustConvert) {
            code.emitLoad(ctxVar);
            code.emitPushInt(singleArgs);
            code.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getRestArgsArray", 1));
        } else if (singleArgs != 0 || mustConvert) {
            Scope pushScope = code.pushScope();
            if (counter == null) {
                counter = code.addLocal(Type.intType);
                if (ctxVar != null) {
                    code.emitLoad(ctxVar);
                    code.emitInvoke(typeCallContext.getDeclaredMethod("getArgCount", 0));
                } else {
                    code.emitLoad(code.getArg(2));
                    code.emitArrayLength();
                }
                if (singleArgs != 0) {
                    code.emitPushInt(singleArgs);
                    code.emitSub(Type.intType);
                }
                code.emitStore(counter);
            }
            code.emitLoad(counter);
            code.emitNewArray(elType.getImplementationType());
            new Label(code);
            Label testLabel = label;
            new Label(code);
            Label loopTopLabel = label2;
            loopTopLabel.setTypes(code);
            code.emitGoto(testLabel);
            loopTopLabel.define(code);
            code.emitDup(1);
            code.emitLoad(counter);
            if (ctxVar != null) {
                code.emitLoad(ctxVar);
            } else {
                code.emitLoad(code.getArg(2));
            }
            code.emitLoad(counter);
            if (singleArgs != 0) {
                code.emitPushInt(singleArgs);
                code.emitAdd(Type.intType);
            }
            if (ctxVar != null) {
                code.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getArgAsObject", 1));
            } else {
                code.emitArrayLoad(Type.objectType);
            }
            if (mustConvert) {
                CheckedTarget.emitCheckedCoerce(this, source, source.getName(), 0, elType, (Variable) null);
            }
            code.emitArrayStore(elType);
            testLabel.define(code);
            code.emitInc(counter, -1);
            code.emitLoad(counter);
            code.emitGotoIfIntGeZero(loopTopLabel);
            Scope popScope = code.popScope();
        } else {
            code.emitLoad(code.getArg(2));
        }
    }

    private Method startClassInit() {
        Method registerMethod;
        this.method = this.curClass.addMethod("<clinit>", apply0args, (Type) Type.voidType, 9);
        CodeAttr code = this.method.startCode();
        if ((this.generateMain || generatingApplet() || generatingServlet()) && (registerMethod = ((ClassType) Type.make(getLanguage().getClass())).getDeclaredMethod("registerEnvironment", 0)) != null) {
            code.emitInvokeStatic(registerMethod);
        }
        return this.method;
    }

    public void process(int i) {
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        ArrayClassLoader arrayClassLoader;
        LitTable litTable2;
        int wantedState = i;
        Compilation saveCompilation = setSaveCurrent(this);
        try {
            ModuleExp mexp = getModule();
            if (wantedState >= 4 && getState() < 3) {
                setState(3);
                boolean parse = this.language.parse(this, 0);
                this.lexer.close();
                this.lexer = null;
                setState(this.messages.seenErrors() ? 100 : 4);
                if (this.pendingImports != null) {
                    restoreCurrent(saveCompilation);
                    return;
                }
            }
            if (wantedState >= 6) {
                if (getState() < 6) {
                    addMainClass(mexp);
                    this.language.resolve(this);
                    setState(this.messages.seenErrors() ? 100 : 6);
                }
            }
            if (!this.explicit && !this.immediate && this.minfo.checkCurrent(ModuleManager.getInstance(), System.currentTimeMillis())) {
                this.minfo.cleanupAfterCompilation();
                setState(14);
            }
            if (wantedState >= 8 && getState() < 8) {
                walkModule(mexp);
                setState(this.messages.seenErrors() ? 100 : 8);
            }
            if (wantedState >= 10 && getState() < 10) {
                new LitTable(this);
                this.litTable = litTable2;
                mexp.setCanRead(true);
                FindCapturedVars.findCapturedVars(mexp, this);
                mexp.allocFields(this);
                mexp.allocChildMethods(this);
                setState(this.messages.seenErrors() ? 100 : 10);
            }
            if (wantedState >= 12 && getState() < 12) {
                if (this.immediate) {
                    new ArrayClassLoader(ObjectType.getContextClassLoader());
                    this.loader = arrayClassLoader;
                }
                generateBytecode();
                setState(this.messages.seenErrors() ? 100 : 12);
            }
            if (wantedState >= 14 && getState() < 14) {
                outputClass(ModuleManager.getInstance().getCompilationDirectory());
                setState(14);
            }
            restoreCurrent(saveCompilation);
        } catch (SyntaxException e) {
            SyntaxException ex = e;
            setState(100);
            if (ex.getMessages() != getMessages()) {
                Throwable th2 = th;
                new StringBuilder();
                new RuntimeException(sb2.append("confussing syntax error: ").append(ex).toString());
                throw th2;
            }
            restoreCurrent(saveCompilation);
        } catch (IOException e2) {
            IOException ex2 = e2;
            ex2.printStackTrace();
            new StringBuilder();
            error('f', sb.append("caught ").append(ex2).toString());
            setState(100);
            restoreCurrent(saveCompilation);
        } catch (Throwable th3) {
            Throwable th4 = th3;
            restoreCurrent(saveCompilation);
            throw th4;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x05a0, code lost:
        if (r2.callContextVar != null) goto L_0x05a2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void generateBytecode() {
        /*
            r41 = this;
            r2 = r41
            r34 = r2
            gnu.expr.ModuleExp r34 = r34.getModule()
            r3 = r34
            boolean r34 = debugPrintFinalExpr
            if (r34 == 0) goto L_0x006a
            gnu.mapping.OutPort r34 = gnu.mapping.OutPort.errDefault()
            r4 = r34
            r34 = r4
            java.lang.StringBuilder r35 = new java.lang.StringBuilder
            r40 = r35
            r35 = r40
            r36 = r40
            r36.<init>()
            java.lang.String r36 = "[Compiling final "
            java.lang.StringBuilder r35 = r35.append(r36)
            r36 = r3
            java.lang.String r36 = r36.getName()
            java.lang.StringBuilder r35 = r35.append(r36)
            java.lang.String r36 = " to "
            java.lang.StringBuilder r35 = r35.append(r36)
            r36 = r2
            r0 = r36
            gnu.bytecode.ClassType r0 = r0.mainClass
            r36 = r0
            java.lang.String r36 = r36.getName()
            java.lang.StringBuilder r35 = r35.append(r36)
            java.lang.String r36 = ":"
            java.lang.StringBuilder r35 = r35.append(r36)
            java.lang.String r35 = r35.toString()
            r34.println(r35)
            r34 = r3
            r35 = r4
            r34.print(r35)
            r34 = r4
            r35 = 93
            r34.println(r35)
            r34 = r4
            r34.flush()
        L_0x006a:
            r34 = r2
            gnu.bytecode.ClassType r34 = r34.getModuleType()
            r4 = r34
            r34 = r2
            r0 = r34
            gnu.bytecode.ClassType r0 = r0.mainClass
            r34 = r0
            gnu.bytecode.ClassType r34 = r34.getSuperclass()
            r35 = r4
            boolean r34 = r34.isSubtype(r35)
            if (r34 == 0) goto L_0x031f
            r34 = r2
            r35 = r2
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.mainClass
            r35 = r0
            r0 = r35
            r1 = r34
            r1.moduleClass = r0
        L_0x0096:
            r34 = r2
            r35 = r3
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.type
            r35 = r0
            r0 = r35
            r1 = r34
            r1.curClass = r0
            r34 = r2
            r0 = r34
            gnu.expr.LambdaExp r0 = r0.curLambda
            r34 = r0
            r6 = r34
            r34 = r2
            r35 = r3
            r0 = r35
            r1 = r34
            r1.curLambda = r0
            r34 = r3
            boolean r34 = r34.isHandlingTailCalls()
            if (r34 == 0) goto L_0x0366
            r34 = 1
            r5 = r34
            r34 = 1
            r0 = r34
            gnu.bytecode.Type[] r0 = new gnu.bytecode.Type[r0]
            r34 = r0
            r7 = r34
            r34 = r7
            r35 = 0
            gnu.bytecode.ClassType r36 = typeCallContext
            r34[r35] = r36
        L_0x00d8:
            r34 = r3
            r0 = r34
            gnu.bytecode.Variable r0 = r0.heapFrame
            r34 = r0
            r9 = r34
            r34 = r3
            boolean r34 = r34.isStatic()
            r10 = r34
            r34 = r2
            r0 = r34
            gnu.bytecode.ClassType r0 = r0.curClass
            r34 = r0
            java.lang.String r35 = "run"
            r36 = r7
            gnu.bytecode.PrimType r37 = gnu.bytecode.Type.voidType
            r38 = 17
            gnu.bytecode.Method r34 = r34.addMethod((java.lang.String) r35, (gnu.bytecode.Type[]) r36, (gnu.bytecode.Type) r37, (int) r38)
            r11 = r34
            r34 = r2
            r35 = r11
            r0 = r35
            r1 = r34
            r1.method = r0
            r34 = r2
            r0 = r34
            gnu.bytecode.Method r0 = r0.method
            r34 = r0
            r34.initCode()
            r34 = r2
            gnu.bytecode.CodeAttr r34 = r34.getCode()
            r8 = r34
            r34 = r2
            r35 = r2
            r0 = r35
            gnu.bytecode.Method r0 = r0.method
            r35 = r0
            boolean r35 = r35.getStaticFlag()
            if (r35 == 0) goto L_0x03d6
            r35 = 0
        L_0x0130:
            r0 = r35
            r1 = r34
            r1.thisDecl = r0
            r34 = r3
            r35 = r3
            r0 = r35
            gnu.bytecode.Variable r0 = r0.thisVariable
            r35 = r0
            r0 = r35
            r1 = r34
            r1.closureEnv = r0
            r34 = r3
            r35 = r3
            boolean r35 = r35.isStatic()
            if (r35 == 0) goto L_0x03e6
            r35 = 0
        L_0x0152:
            r0 = r35
            r1 = r34
            r1.heapFrame = r0
            r34 = r3
            r35 = r2
            r34.allocChildClasses(r35)
            r34 = r3
            boolean r34 = r34.isHandlingTailCalls()
            if (r34 != 0) goto L_0x016f
            r34 = r2
            boolean r34 = r34.usingCPStyle()
            if (r34 == 0) goto L_0x01ad
        L_0x016f:
            r34 = r2
            gnu.bytecode.Variable r35 = new gnu.bytecode.Variable
            r40 = r35
            r35 = r40
            r36 = r40
            java.lang.String r37 = "$ctx"
            gnu.bytecode.ClassType r38 = typeCallContext
            r36.<init>(r37, r38)
            r0 = r35
            r1 = r34
            r1.callContextVar = r0
            r34 = r3
            gnu.bytecode.Scope r34 = r34.getVarScope()
            r35 = r2
            r0 = r35
            gnu.bytecode.Variable r0 = r0.thisDecl
            r35 = r0
            r36 = r2
            r0 = r36
            gnu.bytecode.Variable r0 = r0.callContextVar
            r36 = r0
            r34.addVariableAfter(r35, r36)
            r34 = r2
            r0 = r34
            gnu.bytecode.Variable r0 = r0.callContextVar
            r34 = r0
            r35 = 1
            r34.setParameter(r35)
        L_0x01ad:
            r34 = r3
            int r34 = r34.getLineNumber()
            r12 = r34
            r34 = r12
            if (r34 <= 0) goto L_0x01c6
            r34 = r8
            r35 = r3
            java.lang.String r35 = r35.getFileName()
            r36 = r12
            r34.putLineNumber(r35, r36)
        L_0x01c6:
            r34 = r3
            r35 = r2
            r34.allocParameters(r35)
            r34 = r3
            r35 = r2
            r34.enterFunction(r35)
            r34 = r2
            boolean r34 = r34.usingCPStyle()
            if (r34 == 0) goto L_0x0206
            r34 = r2
            r34.loadCallContext()
            r34 = r8
            gnu.bytecode.Field r35 = pcCallContextField
            r34.emitGetField(r35)
            r34 = r2
            r35 = r8
            gnu.bytecode.SwitchState r35 = r35.startSwitch()
            r0 = r35
            r1 = r34
            r1.fswitch = r0
            r34 = r2
            r0 = r34
            gnu.bytecode.SwitchState r0 = r0.fswitch
            r34 = r0
            r35 = 0
            r36 = r8
            boolean r34 = r34.addCase(r35, r36)
        L_0x0206:
            r34 = r3
            r35 = r2
            r34.compileBody(r35)
            r34 = r3
            r35 = r2
            r34.compileEnd(r35)
            r34 = 0
            r13 = r34
            r34 = 0
            r14 = r34
            r34 = 0
            r15 = r34
            r34 = r2
            r0 = r34
            gnu.bytecode.ClassType r0 = r0.curClass
            r34 = r0
            r35 = r2
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.mainClass
            r35 = r0
            r0 = r34
            r1 = r35
            if (r0 != r1) goto L_0x055c
            r34 = r2
            r0 = r34
            gnu.bytecode.Method r0 = r0.method
            r34 = r0
            r16 = r34
            r34 = r2
            r0 = r34
            gnu.bytecode.Variable r0 = r0.callContextVar
            r34 = r0
            r17 = r34
            r34 = r2
            r35 = 0
            r0 = r35
            r1 = r34
            r1.callContextVar = r0
            r34 = r2
            gnu.bytecode.Method r34 = r34.startClassInit()
            r15 = r34
            r34 = r2
            r35 = r15
            r0 = r35
            r1 = r34
            r1.clinitMethod = r0
            r34 = r2
            gnu.bytecode.CodeAttr r34 = r34.getCode()
            r8 = r34
            gnu.bytecode.Label r34 = new gnu.bytecode.Label
            r40 = r34
            r34 = r40
            r35 = r40
            r36 = r8
            r35.<init>((gnu.bytecode.CodeAttr) r36)
            r13 = r34
            gnu.bytecode.Label r34 = new gnu.bytecode.Label
            r40 = r34
            r34 = r40
            r35 = r40
            r36 = r8
            r35.<init>((gnu.bytecode.CodeAttr) r36)
            r14 = r34
            r34 = r8
            r35 = r14
            r36 = r13
            r34.fixupChain(r35, r36)
            r34 = r10
            if (r34 == 0) goto L_0x02fb
            r34 = r2
            r35 = r3
            r34.generateConstructor(r35)
            r34 = r8
            r35 = r2
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.moduleClass
            r35 = r0
            r34.emitNew(r35)
            r34 = r8
            r35 = r2
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.moduleClass
            r35 = r0
            r34.emitDup((gnu.bytecode.Type) r35)
            r34 = r8
            r35 = r2
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.moduleClass
            r35 = r0
            r0 = r35
            gnu.bytecode.Method r0 = r0.constructor
            r35 = r0
            r34.emitInvokeSpecial(r35)
            r34 = r2
            r35 = r2
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.moduleClass
            r35 = r0
            java.lang.String r36 = "$instance"
            r37 = r2
            r0 = r37
            gnu.bytecode.ClassType r0 = r0.moduleClass
            r37 = r0
            r38 = 25
            gnu.bytecode.Field r35 = r35.addField(r36, r37, r38)
            r0 = r35
            r1 = r34
            r1.moduleInstanceMainField = r0
            r34 = r8
            r35 = r2
            r0 = r35
            gnu.bytecode.Field r0 = r0.moduleInstanceMainField
            r35 = r0
            r34.emitPutStatic(r35)
        L_0x02fb:
            r34 = r2
            r0 = r34
            gnu.expr.Initializer r0 = r0.clinitChain
            r34 = r0
            r40 = r34
            r34 = r40
            r35 = r40
            r18 = r35
            if (r34 == 0) goto L_0x03f0
            r34 = r2
            r35 = 0
            r0 = r35
            r1 = r34
            r1.clinitChain = r0
            r34 = r2
            r35 = r18
            r34.dumpInitializers(r35)
            goto L_0x02fb
        L_0x031f:
            r34 = r2
            gnu.bytecode.ClassType r35 = new gnu.bytecode.ClassType
            r40 = r35
            r35 = r40
            r36 = r40
            r37 = r2
            java.lang.String r38 = "frame"
            java.lang.String r37 = r37.generateClassName(r38)
            r36.<init>(r37)
            r0 = r35
            r1 = r34
            r1.moduleClass = r0
            r34 = r2
            r0 = r34
            gnu.bytecode.ClassType r0 = r0.moduleClass
            r34 = r0
            r35 = r4
            r34.setSuper((gnu.bytecode.ClassType) r35)
            r34 = r2
            r35 = r2
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.moduleClass
            r35 = r0
            r34.addClass(r35)
            r34 = r2
            r35 = r2
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.moduleClass
            r35 = r0
            r36 = 0
            r34.generateConstructor(r35, r36)
            goto L_0x0096
        L_0x0366:
            r34 = r3
            r0 = r34
            int r0 = r0.min_args
            r34 = r0
            r35 = r3
            r0 = r35
            int r0 = r0.max_args
            r35 = r0
            r0 = r34
            r1 = r35
            if (r0 != r1) goto L_0x038c
            r34 = r3
            r0 = r34
            int r0 = r0.min_args
            r34 = r0
            r35 = 4
            r0 = r34
            r1 = r35
            if (r0 <= r1) goto L_0x03af
        L_0x038c:
            r34 = 1
            r5 = r34
            r34 = 1
            r0 = r34
            gnu.bytecode.Type[] r0 = new gnu.bytecode.Type[r0]
            r34 = r0
            r7 = r34
            r34 = r7
            r35 = 0
            gnu.bytecode.ArrayType r36 = new gnu.bytecode.ArrayType
            r40 = r36
            r36 = r40
            r37 = r40
            gnu.bytecode.ClassType r38 = typeObject
            r37.<init>(r38)
            r34[r35] = r36
            goto L_0x00d8
        L_0x03af:
            r34 = r3
            r0 = r34
            int r0 = r0.min_args
            r34 = r0
            r5 = r34
            r34 = r5
            r0 = r34
            gnu.bytecode.Type[] r0 = new gnu.bytecode.Type[r0]
            r34 = r0
            r7 = r34
            r34 = r5
            r8 = r34
        L_0x03c7:
            int r8 = r8 + -1
            r34 = r8
            if (r34 < 0) goto L_0x00d8
            r34 = r7
            r35 = r8
            gnu.bytecode.ClassType r36 = typeObject
            r34[r35] = r36
            goto L_0x03c7
        L_0x03d6:
            r35 = r3
            r36 = r3
            r0 = r36
            gnu.bytecode.ClassType r0 = r0.type
            r36 = r0
            gnu.bytecode.Variable r35 = r35.declareThis(r36)
            goto L_0x0130
        L_0x03e6:
            r35 = r3
            r0 = r35
            gnu.bytecode.Variable r0 = r0.thisVariable
            r35 = r0
            goto L_0x0152
        L_0x03f0:
            r34 = r3
            boolean r34 = r34.staticInitRun()
            if (r34 == 0) goto L_0x0415
            r34 = r8
            r35 = r2
            r0 = r35
            gnu.bytecode.Field r0 = r0.moduleInstanceMainField
            r35 = r0
            r34.emitGetStatic(r35)
            r34 = r8
            gnu.bytecode.ClassType r35 = typeModuleBody
            java.lang.String r36 = "run"
            r37 = 0
            gnu.bytecode.Method r35 = r35.getDeclaredMethod((java.lang.String) r36, (int) r37)
            r34.emitInvoke(r35)
        L_0x0415:
            r34 = r8
            r34.emitReturn()
            r34 = r2
            r0 = r34
            gnu.bytecode.ClassType r0 = r0.moduleClass
            r34 = r0
            r35 = r2
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.mainClass
            r35 = r0
            r0 = r34
            r1 = r35
            if (r0 == r1) goto L_0x0548
            r34 = r10
            if (r34 != 0) goto L_0x0548
            r34 = r2
            r0 = r34
            boolean r0 = r0.generateMain
            r34 = r0
            if (r34 != 0) goto L_0x0548
            r34 = r2
            r0 = r34
            boolean r0 = r0.immediate
            r34 = r0
            if (r34 != 0) goto L_0x0548
            r34 = r2
            r35 = r2
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.curClass
            r35 = r0
            java.lang.String r36 = "run"
            r37 = 1
            gnu.bytecode.Type[] r38 = gnu.bytecode.Type.typeArray0
            gnu.bytecode.PrimType r39 = gnu.bytecode.Type.voidType
            gnu.bytecode.Method r35 = r35.addMethod((java.lang.String) r36, (int) r37, (gnu.bytecode.Type[]) r38, (gnu.bytecode.Type) r39)
            r0 = r35
            r1 = r34
            r1.method = r0
            r34 = r2
            r0 = r34
            gnu.bytecode.Method r0 = r0.method
            r34 = r0
            gnu.bytecode.CodeAttr r34 = r34.startCode()
            r8 = r34
            r34 = r8
            gnu.bytecode.ClassType r35 = typeCallContext
            gnu.bytecode.Variable r34 = r34.addLocal(r35)
            r19 = r34
            r34 = r8
            gnu.bytecode.ClassType r35 = typeConsumer
            gnu.bytecode.Variable r34 = r34.addLocal(r35)
            r20 = r34
            r34 = r8
            gnu.bytecode.ClassType r35 = gnu.bytecode.Type.javalangThrowableType
            gnu.bytecode.Variable r34 = r34.addLocal(r35)
            r21 = r34
            r34 = r8
            gnu.bytecode.Method r35 = getCallContextInstanceMethod
            r34.emitInvokeStatic(r35)
            r34 = r8
            r35 = r19
            r34.emitStore(r35)
            gnu.bytecode.ClassType r34 = typeCallContext
            java.lang.String r35 = "consumer"
            gnu.bytecode.Field r34 = r34.getDeclaredField(r35)
            r22 = r34
            r34 = r8
            r35 = r19
            r34.emitLoad(r35)
            r34 = r8
            r35 = r22
            r34.emitGetField(r35)
            r34 = r8
            r35 = r20
            r34.emitStore(r35)
            r34 = r8
            r35 = r19
            r34.emitLoad(r35)
            r34 = r8
            java.lang.String r35 = "gnu.lists.VoidConsumer"
            gnu.bytecode.ClassType r35 = gnu.bytecode.ClassType.make(r35)
            java.lang.String r36 = "instance"
            gnu.bytecode.Field r35 = r35.getDeclaredField(r36)
            r34.emitGetStatic(r35)
            r34 = r8
            r35 = r22
            r34.emitPutField(r35)
            r34 = r8
            r35 = 0
            gnu.bytecode.PrimType r36 = gnu.bytecode.Type.voidType
            r34.emitTryStart(r35, r36)
            r34 = r8
            r34.emitPushThis()
            r34 = r8
            r35 = r19
            r34.emitLoad(r35)
            r34 = r8
            r35 = r16
            r34.emitInvokeVirtual(r35)
            r34 = r8
            r34.emitPushNull()
            r34 = r8
            r35 = r21
            r34.emitStore(r35)
            r34 = r8
            r34.emitTryEnd()
            r34 = r8
            r35 = r21
            r34.emitCatchStart(r35)
            r34 = r8
            r34.emitCatchEnd()
            r34 = r8
            r34.emitTryCatchEnd()
            r34 = r8
            r35 = r19
            r34.emitLoad(r35)
            r34 = r8
            r35 = r21
            r34.emitLoad(r35)
            r34 = r8
            r35 = r20
            r34.emitLoad(r35)
            r34 = r8
            gnu.bytecode.ClassType r35 = typeModuleBody
            java.lang.String r36 = "runCleanup"
            r37 = 3
            gnu.bytecode.Method r35 = r35.getDeclaredMethod((java.lang.String) r36, (int) r37)
            r34.emitInvokeStatic(r35)
            r34 = r8
            r34.emitReturn()
        L_0x0548:
            r34 = r2
            r35 = r16
            r0 = r35
            r1 = r34
            r1.method = r0
            r34 = r2
            r35 = r17
            r0 = r35
            r1 = r34
            r1.callContextVar = r0
        L_0x055c:
            r34 = r3
            r35 = r2
            r34.generateApplyMethods(r35)
            r34 = r2
            r35 = r6
            r0 = r35
            r1 = r34
            r1.curLambda = r0
            r34 = r3
            r35 = r9
            r0 = r35
            r1 = r34
            r1.heapFrame = r0
            r34 = r2
            boolean r34 = r34.usingCPStyle()
            if (r34 == 0) goto L_0x0594
            r34 = r2
            gnu.bytecode.CodeAttr r34 = r34.getCode()
            r8 = r34
            r34 = r2
            r0 = r34
            gnu.bytecode.SwitchState r0 = r0.fswitch
            r34 = r0
            r35 = r8
            r34.finish(r35)
        L_0x0594:
            r34 = r13
            if (r34 != 0) goto L_0x05a2
            r34 = r2
            r0 = r34
            gnu.bytecode.Variable r0 = r0.callContextVar
            r34 = r0
            if (r34 == 0) goto L_0x061d
        L_0x05a2:
            r34 = r2
            r35 = r15
            r0 = r35
            r1 = r34
            r1.method = r0
            r34 = r2
            gnu.bytecode.CodeAttr r34 = r34.getCode()
            r8 = r34
            gnu.bytecode.Label r34 = new gnu.bytecode.Label
            r40 = r34
            r34 = r40
            r35 = r40
            r36 = r8
            r35.<init>((gnu.bytecode.CodeAttr) r36)
            r16 = r34
            r34 = r8
            r35 = r13
            r36 = r16
            r34.fixupChain(r35, r36)
            r34 = r2
            r0 = r34
            gnu.bytecode.Variable r0 = r0.callContextVarForInit
            r34 = r0
            if (r34 == 0) goto L_0x05ea
            r34 = r8
            gnu.bytecode.Method r35 = getCallContextInstanceMethod
            r34.emitInvokeStatic(r35)
            r34 = r8
            r35 = r2
            r0 = r35
            gnu.bytecode.Variable r0 = r0.callContextVarForInit
            r35 = r0
            r34.emitStore(r35)
        L_0x05ea:
            r34 = r2
            r0 = r34
            boolean r0 = r0.immediate     // Catch:{ Throwable -> 0x0858 }
            r34 = r0
            if (r34 == 0) goto L_0x084b
            r34 = r8
            r35 = r2
            int r35 = registerForImmediateLiterals(r35)     // Catch:{ Throwable -> 0x0858 }
            r34.emitPushInt(r35)     // Catch:{ Throwable -> 0x0858 }
            r34 = r8
            java.lang.String r35 = "gnu.expr.Compilation"
            gnu.bytecode.ClassType r35 = gnu.bytecode.ClassType.make(r35)     // Catch:{ Throwable -> 0x0858 }
            java.lang.String r36 = "setupLiterals"
            r37 = 1
            gnu.bytecode.Method r35 = r35.getDeclaredMethod((java.lang.String) r36, (int) r37)     // Catch:{ Throwable -> 0x0858 }
            r34.emitInvokeStatic(r35)     // Catch:{ Throwable -> 0x0858 }
        L_0x0614:
            r34 = r8
            r35 = r16
            r36 = r14
            r34.fixupChain(r35, r36)
        L_0x061d:
            r34 = r2
            r0 = r34
            boolean r0 = r0.generateMain
            r34 = r0
            if (r34 == 0) goto L_0x06f7
            r34 = r2
            r0 = r34
            gnu.bytecode.ClassType r0 = r0.curClass
            r34 = r0
            r35 = r2
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.mainClass
            r35 = r0
            r0 = r34
            r1 = r35
            if (r0 != r1) goto L_0x06f7
            r34 = 1
            r0 = r34
            gnu.bytecode.Type[] r0 = new gnu.bytecode.Type[r0]
            r34 = r0
            r40 = r34
            r34 = r40
            r35 = r40
            r36 = 0
            gnu.bytecode.ArrayType r37 = new gnu.bytecode.ArrayType
            r40 = r37
            r37 = r40
            r38 = r40
            gnu.bytecode.ClassType r39 = javaStringType
            r38.<init>(r39)
            r35[r36] = r37
            r16 = r34
            r34 = r2
            r35 = r2
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.curClass
            r35 = r0
            java.lang.String r36 = "main"
            r37 = 9
            r38 = r16
            gnu.bytecode.PrimType r39 = gnu.bytecode.Type.voidType
            gnu.bytecode.Method r35 = r35.addMethod((java.lang.String) r36, (int) r37, (gnu.bytecode.Type[]) r38, (gnu.bytecode.Type) r39)
            r0 = r35
            r1 = r34
            r1.method = r0
            r34 = r2
            r0 = r34
            gnu.bytecode.Method r0 = r0.method
            r34 = r0
            gnu.bytecode.CodeAttr r34 = r34.startCode()
            r8 = r34
            java.lang.String r34 = kawa.Shell.defaultFormatName
            if (r34 == 0) goto L_0x06a9
            r34 = r8
            java.lang.String r35 = kawa.Shell.defaultFormatName
            r34.emitPushString(r35)
            r34 = r8
            java.lang.String r35 = "kawa.Shell"
            gnu.bytecode.ClassType r35 = gnu.bytecode.ClassType.make(r35)
            java.lang.String r36 = "setDefaultFormat"
            r37 = 1
            gnu.bytecode.Method r35 = r35.getDeclaredMethod((java.lang.String) r36, (int) r37)
            r34.emitInvokeStatic(r35)
        L_0x06a9:
            r34 = r8
            r35 = r8
            r36 = 0
            gnu.bytecode.Variable r35 = r35.getArg(r36)
            r34.emitLoad(r35)
            r34 = r8
            java.lang.String r35 = "gnu.expr.ApplicationMainSupport"
            gnu.bytecode.ClassType r35 = gnu.bytecode.ClassType.make(r35)
            java.lang.String r36 = "processArgs"
            r37 = 1
            gnu.bytecode.Method r35 = r35.getDeclaredMethod((java.lang.String) r36, (int) r37)
            r34.emitInvokeStatic(r35)
            r34 = r2
            r0 = r34
            gnu.bytecode.Field r0 = r0.moduleInstanceMainField
            r34 = r0
            if (r34 == 0) goto L_0x0880
            r34 = r8
            r35 = r2
            r0 = r35
            gnu.bytecode.Field r0 = r0.moduleInstanceMainField
            r35 = r0
            r34.emitGetStatic(r35)
        L_0x06e2:
            r34 = r8
            gnu.bytecode.ClassType r35 = typeModuleBody
            java.lang.String r36 = "runAsMain"
            r37 = 0
            gnu.bytecode.Method r35 = r35.getDeclaredMethod((java.lang.String) r36, (int) r37)
            r34.emitInvokeVirtual(r35)
            r34 = r8
            r34.emitReturn()
        L_0x06f7:
            r34 = r2
            r0 = r34
            gnu.expr.ModuleInfo r0 = r0.minfo
            r34 = r0
            if (r34 == 0) goto L_0x0a15
            r34 = r2
            r0 = r34
            gnu.expr.ModuleInfo r0 = r0.minfo
            r34 = r0
            java.lang.String r34 = r34.getNamespaceUri()
            r40 = r34
            r34 = r40
            r35 = r40
            r16 = r35
            if (r34 == 0) goto L_0x0a15
            gnu.expr.ModuleManager r34 = gnu.expr.ModuleManager.getInstance()
            r17 = r34
            r34 = r2
            r0 = r34
            gnu.bytecode.ClassType r0 = r0.mainClass
            r34 = r0
            java.lang.String r34 = r34.getName()
            r18 = r34
            r34 = r18
            r35 = 46
            int r34 = r34.lastIndexOf(r35)
            r19 = r34
            r34 = r19
            if (r34 >= 0) goto L_0x08af
            java.lang.String r34 = ""
            r18 = r34
        L_0x073e:
            gnu.bytecode.ClassType r34 = new gnu.bytecode.ClassType
            r40 = r34
            r34 = r40
            r35 = r40
            java.lang.StringBuilder r36 = new java.lang.StringBuilder
            r40 = r36
            r36 = r40
            r37 = r40
            r37.<init>()
            r37 = r18
            java.lang.StringBuilder r36 = r36.append(r37)
            java.lang.String r37 = "$ModulesMap$"
            java.lang.StringBuilder r36 = r36.append(r37)
            java.lang.String r36 = r36.toString()
            r35.<init>(r36)
            r20 = r34
            java.lang.String r34 = "gnu.expr.ModuleSet"
            gnu.bytecode.ClassType r34 = gnu.bytecode.ClassType.make(r34)
            r21 = r34
            r34 = r20
            r35 = r21
            r34.setSuper((gnu.bytecode.ClassType) r35)
            r34 = r2
            r35 = r20
            r34.registerClass(r35)
            r34 = r2
            r35 = r20
            java.lang.String r36 = "<init>"
            r37 = 1
            gnu.bytecode.Type[] r38 = apply0args
            gnu.bytecode.PrimType r39 = gnu.bytecode.Type.voidType
            gnu.bytecode.Method r35 = r35.addMethod((java.lang.String) r36, (int) r37, (gnu.bytecode.Type[]) r38, (gnu.bytecode.Type) r39)
            r0 = r35
            r1 = r34
            r1.method = r0
            r34 = r21
            java.lang.String r35 = "<init>"
            r36 = 1
            gnu.bytecode.Type[] r37 = apply0args
            gnu.bytecode.PrimType r38 = gnu.bytecode.Type.voidType
            gnu.bytecode.Method r34 = r34.addMethod((java.lang.String) r35, (int) r36, (gnu.bytecode.Type[]) r37, (gnu.bytecode.Type) r38)
            r22 = r34
            r34 = r2
            r0 = r34
            gnu.bytecode.Method r0 = r0.method
            r34 = r0
            gnu.bytecode.CodeAttr r34 = r34.startCode()
            r8 = r34
            r34 = r8
            r34.emitPushThis()
            r34 = r8
            r35 = r22
            r34.emitInvokeSpecial(r35)
            r34 = r8
            r34.emitReturn()
            java.lang.String r34 = "gnu.expr.ModuleManager"
            gnu.bytecode.ClassType r34 = gnu.bytecode.ClassType.make(r34)
            r23 = r34
            r34 = 1
            r0 = r34
            gnu.bytecode.Type[] r0 = new gnu.bytecode.Type[r0]
            r34 = r0
            r40 = r34
            r34 = r40
            r35 = r40
            r36 = 0
            r37 = r23
            r35[r36] = r37
            r24 = r34
            r34 = r2
            r35 = r20
            java.lang.String r36 = "register"
            r37 = r24
            gnu.bytecode.PrimType r38 = gnu.bytecode.Type.voidType
            r39 = 1
            gnu.bytecode.Method r35 = r35.addMethod((java.lang.String) r36, (gnu.bytecode.Type[]) r37, (gnu.bytecode.Type) r38, (int) r39)
            r0 = r35
            r1 = r34
            r1.method = r0
            r34 = r2
            r0 = r34
            gnu.bytecode.Method r0 = r0.method
            r34 = r0
            gnu.bytecode.CodeAttr r34 = r34.startCode()
            r8 = r34
            r34 = r23
            java.lang.String r35 = "register"
            r36 = 3
            gnu.bytecode.Method r34 = r34.getDeclaredMethod((java.lang.String) r35, (int) r36)
            r25 = r34
            r34 = r17
            r0 = r34
            int r0 = r0.numModules
            r34 = r0
            r26 = r34
        L_0x0820:
            int r26 = r26 + -1
            r34 = r26
            if (r34 < 0) goto L_0x0a10
            r34 = r17
            r0 = r34
            gnu.expr.ModuleInfo[] r0 = r0.modules
            r34 = r0
            r35 = r26
            r34 = r34[r35]
            r27 = r34
            r34 = r27
            java.lang.String r34 = r34.getClassName()
            r28 = r34
            r34 = r28
            if (r34 == 0) goto L_0x0820
            r34 = r28
            r35 = r18
            boolean r34 = r34.startsWith(r35)
            if (r34 != 0) goto L_0x090c
            goto L_0x0820
        L_0x084b:
            r34 = r2
            r0 = r34
            gnu.expr.LitTable r0 = r0.litTable     // Catch:{ Throwable -> 0x0858 }
            r34 = r0
            r34.emit()     // Catch:{ Throwable -> 0x0858 }
            goto L_0x0614
        L_0x0858:
            r34 = move-exception
            r17 = r34
            r34 = r2
            r35 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r36 = new java.lang.StringBuilder
            r40 = r36
            r36 = r40
            r37 = r40
            r37.<init>()
            java.lang.String r37 = "Literals: Internal error:"
            java.lang.StringBuilder r36 = r36.append(r37)
            r37 = r17
            java.lang.StringBuilder r36 = r36.append(r37)
            java.lang.String r36 = r36.toString()
            r34.error(r35, r36)
            goto L_0x0614
        L_0x0880:
            r34 = r8
            r35 = r2
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.curClass
            r35 = r0
            r34.emitNew(r35)
            r34 = r8
            r35 = r2
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.curClass
            r35 = r0
            r34.emitDup((gnu.bytecode.Type) r35)
            r34 = r8
            r35 = r2
            r0 = r35
            gnu.bytecode.ClassType r0 = r0.curClass
            r35 = r0
            r0 = r35
            gnu.bytecode.Method r0 = r0.constructor
            r35 = r0
            r34.emitInvokeSpecial(r35)
            goto L_0x06e2
        L_0x08af:
            r34 = r18
            r35 = 0
            r36 = r19
            java.lang.String r34 = r34.substring(r35, r36)
            r20 = r34
            r34 = r17
            r35 = r20
            r34.loadPackageInfo(r35)     // Catch:{ ClassNotFoundException -> 0x08d4, Throwable -> 0x08d8 }
        L_0x08c2:
            r34 = r18
            r35 = 0
            r36 = r19
            r37 = 1
            int r36 = r36 + 1
            java.lang.String r34 = r34.substring(r35, r36)
            r18 = r34
            goto L_0x073e
        L_0x08d4:
            r34 = move-exception
            r21 = r34
            goto L_0x08c2
        L_0x08d8:
            r34 = move-exception
            r21 = r34
            r34 = r2
            r35 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r36 = new java.lang.StringBuilder
            r40 = r36
            r36 = r40
            r37 = r40
            r37.<init>()
            java.lang.String r37 = "error loading map for "
            java.lang.StringBuilder r36 = r36.append(r37)
            r37 = r20
            java.lang.StringBuilder r36 = r36.append(r37)
            java.lang.String r37 = " - "
            java.lang.StringBuilder r36 = r36.append(r37)
            r37 = r21
            java.lang.StringBuilder r36 = r36.append(r37)
            java.lang.String r36 = r36.toString()
            r34.error(r35, r36)
            goto L_0x08c2
        L_0x090c:
            r34 = r27
            r0 = r34
            java.lang.String r0 = r0.sourcePath
            r34 = r0
            r29 = r34
            r34 = r27
            java.lang.String r34 = r34.getNamespaceUri()
            r30 = r34
            r34 = r8
            r35 = r8
            r36 = 1
            gnu.bytecode.Variable r35 = r35.getArg(r36)
            r34.emitLoad(r35)
            r34 = r2
            r35 = r28
            r34.compileConstant(r35)
            r34 = r29
            gnu.text.Path r34 = gnu.text.Path.valueOf(r34)
            boolean r34 = r34.isAbsolute()
            if (r34 != 0) goto L_0x09c6
            char r34 = java.io.File.separatorChar     // Catch:{ Throwable -> 0x09dd }
            r31 = r34
            r34 = r17
            java.lang.String r34 = r34.getCompilationDirectory()     // Catch:{ Throwable -> 0x09dd }
            r32 = r34
            java.lang.StringBuilder r34 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x09dd }
            r40 = r34
            r34 = r40
            r35 = r40
            r35.<init>()     // Catch:{ Throwable -> 0x09dd }
            r35 = r32
            java.lang.StringBuilder r34 = r34.append(r35)     // Catch:{ Throwable -> 0x09dd }
            r35 = r18
            r36 = 46
            r37 = r31
            java.lang.String r35 = r35.replace(r36, r37)     // Catch:{ Throwable -> 0x09dd }
            java.lang.StringBuilder r34 = r34.append(r35)     // Catch:{ Throwable -> 0x09dd }
            java.lang.String r34 = r34.toString()     // Catch:{ Throwable -> 0x09dd }
            r32 = r34
            r34 = r32
            java.net.URL r34 = gnu.text.Path.toURL(r34)     // Catch:{ Throwable -> 0x09dd }
            java.lang.String r34 = r34.toString()     // Catch:{ Throwable -> 0x09dd }
            r32 = r34
            r34 = r32
            int r34 = r34.length()     // Catch:{ Throwable -> 0x09dd }
            r33 = r34
            r34 = r33
            if (r34 <= 0) goto L_0x09b8
            r34 = r32
            r35 = r33
            r36 = 1
            int r35 = r35 + -1
            char r34 = r34.charAt(r35)     // Catch:{ Throwable -> 0x09dd }
            r35 = r31
            r0 = r34
            r1 = r35
            if (r0 == r1) goto L_0x09b8
            java.lang.StringBuilder r34 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x09dd }
            r40 = r34
            r34 = r40
            r35 = r40
            r35.<init>()     // Catch:{ Throwable -> 0x09dd }
            r35 = r32
            java.lang.StringBuilder r34 = r34.append(r35)     // Catch:{ Throwable -> 0x09dd }
            r35 = r31
            java.lang.StringBuilder r34 = r34.append(r35)     // Catch:{ Throwable -> 0x09dd }
            java.lang.String r34 = r34.toString()     // Catch:{ Throwable -> 0x09dd }
            r32 = r34
        L_0x09b8:
            r34 = r27
            java.lang.String r34 = r34.getSourceAbsPathname()     // Catch:{ Throwable -> 0x09dd }
            r35 = r32
            java.lang.String r34 = gnu.text.Path.relativize(r34, r35)     // Catch:{ Throwable -> 0x09dd }
            r29 = r34
        L_0x09c6:
            r34 = r2
            r35 = r29
            r34.compileConstant(r35)
            r34 = r2
            r35 = r30
            r34.compileConstant(r35)
            r34 = r8
            r35 = r25
            r34.emitInvokeVirtual(r35)
            goto L_0x0820
        L_0x09dd:
            r34 = move-exception
            r31 = r34
            gnu.mapping.WrappedException r34 = new gnu.mapping.WrappedException
            r40 = r34
            r34 = r40
            r35 = r40
            java.lang.StringBuilder r36 = new java.lang.StringBuilder
            r40 = r36
            r36 = r40
            r37 = r40
            r37.<init>()
            java.lang.String r37 = "exception while fixing up '"
            java.lang.StringBuilder r36 = r36.append(r37)
            r37 = r29
            java.lang.StringBuilder r36 = r36.append(r37)
            r37 = 39
            java.lang.StringBuilder r36 = r36.append(r37)
            java.lang.String r36 = r36.toString()
            r37 = r31
            r35.<init>(r36, r37)
            throw r34
        L_0x0a10:
            r34 = r8
            r34.emitReturn()
        L_0x0a15:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Compilation.generateBytecode():void");
    }

    public Field allocLocalField(Type type, String str) {
        StringBuilder sb;
        Type type2 = type;
        String name = str;
        if (name == null) {
            new StringBuilder();
            StringBuilder append = sb.append("tmp_");
            int i = this.localFieldIndex + 1;
            int i2 = i;
            this.localFieldIndex = i2;
            name = append.append(i).toString();
        }
        return this.curClass.addField(name, type2, 0);
    }

    public final void loadCallContext() {
        Variable variable;
        Variable variable2;
        CodeAttr code = getCode();
        if (this.callContextVar != null && !this.callContextVar.dead()) {
            code.emitLoad(this.callContextVar);
        } else if (this.method == this.clinitMethod) {
            new Variable("$ctx", typeCallContext);
            this.callContextVar = variable2;
            boolean reserveLocal = this.callContextVar.reserveLocal(code.getMaxLocals(), code);
            code.emitLoad(this.callContextVar);
            this.callContextVarForInit = this.callContextVar;
        } else {
            code.emitInvokeStatic(getCallContextInstanceMethod);
            code.emitDup();
            new Variable("$ctx", typeCallContext);
            this.callContextVar = variable;
            code.getCurrentScope().addVariable(code, this.callContextVar);
            code.emitStore(this.callContextVar);
        }
    }

    public void freeLocalField(Field field) {
    }

    public Expression parse(Object obj) {
        Throwable th;
        Object obj2 = obj;
        Throwable th2 = th;
        new Error("unimeplemented parse");
        throw th2;
    }

    public Language getLanguage() {
        return this.language;
    }

    public LambdaExp currentLambda() {
        return this.current_scope.currentLambda();
    }

    public final ModuleExp getModule() {
        return this.mainLambda;
    }

    public void setModule(ModuleExp mexp) {
        ModuleExp moduleExp = mexp;
        this.mainLambda = moduleExp;
    }

    public boolean isStatic() {
        return this.mainLambda.isStatic();
    }

    public ModuleExp currentModule() {
        return this.current_scope.currentModule();
    }

    public void mustCompileHere() {
        if (this.mustCompile || ModuleExp.compilerAvailable) {
            this.mustCompile = true;
        } else {
            error('w', "this expression claimed that it must be compiled, but compiler is unavailable");
        }
    }

    public ScopeExp currentScope() {
        return this.current_scope;
    }

    public void setCurrentScope(ScopeExp scopeExp) {
        ScopeExp scope = scopeExp;
        int scope_nesting = ScopeExp.nesting(scope);
        int current_nesting = ScopeExp.nesting(this.current_scope);
        while (current_nesting > scope_nesting) {
            pop(this.current_scope);
            current_nesting--;
        }
        ScopeExp sc = scope;
        while (scope_nesting > current_nesting) {
            sc = sc.outer;
            scope_nesting--;
        }
        while (sc != this.current_scope) {
            pop(this.current_scope);
            sc = sc.outer;
        }
        pushChain(scope, sc);
    }

    /* access modifiers changed from: package-private */
    public void pushChain(ScopeExp scopeExp, ScopeExp scopeExp2) {
        ScopeExp scope = scopeExp;
        ScopeExp limit = scopeExp2;
        if (scope != limit) {
            pushChain(scope.outer, limit);
            pushScope(scope);
            this.lexical.push(scope);
        }
    }

    public ModuleExp pushNewModule(Lexer lexer2) {
        Lexer lexer3 = lexer2;
        this.lexer = lexer3;
        return pushNewModule(lexer3.getName());
    }

    public ModuleExp pushNewModule(String str) {
        ModuleExp moduleExp;
        ModuleInfo moduleInfo;
        String filename = str;
        new ModuleExp();
        ModuleExp module = moduleExp;
        if (filename != null) {
            module.setFile(filename);
        }
        if (generatingApplet() || generatingServlet()) {
            module.setFlag(131072);
        }
        if (this.immediate) {
            module.setFlag(1048576);
            new ModuleInfo();
            moduleInfo.setCompilation(this);
        }
        this.mainLambda = module;
        push((ScopeExp) module);
        return module;
    }

    public void push(ScopeExp scopeExp) {
        ScopeExp scope = scopeExp;
        pushScope(scope);
        this.lexical.push(scope);
    }

    public final void pushScope(ScopeExp scopeExp) {
        ScopeExp scope = scopeExp;
        if (!this.mustCompile && (scope.mustCompile() || (ModuleExp.compilerAvailable && (scope instanceof LambdaExp) && !(scope instanceof ModuleExp)))) {
            mustCompileHere();
        }
        scope.outer = this.current_scope;
        this.current_scope = scope;
    }

    public void pop(ScopeExp scopeExp) {
        ScopeExp scope = scopeExp;
        this.lexical.pop(scope);
        this.current_scope = scope.outer;
    }

    public final void pop() {
        pop(this.current_scope);
    }

    public void push(Declaration decl) {
        this.lexical.push(decl);
    }

    public Declaration lookup(Object name, int namespace) {
        return this.lexical.lookup(name, namespace);
    }

    public void usedClass(Type type) {
        Type type2 = type;
        while (type2 instanceof ArrayType) {
            type2 = ((ArrayType) type2).getComponentType();
        }
        if (this.immediate && (type2 instanceof ClassType)) {
            this.loader.addClass((ClassType) type2);
        }
    }

    public SourceMessages getMessages() {
        return this.messages;
    }

    public void setMessages(SourceMessages messages2) {
        SourceMessages sourceMessages = messages2;
        this.messages = sourceMessages;
    }

    public void error(char c, String str, SourceLocator sourceLocator) {
        char severity = c;
        String message = str;
        SourceLocator location = sourceLocator;
        String file = location.getFileName();
        int line = location.getLineNumber();
        int column = location.getColumnNumber();
        if (file == null || line <= 0) {
            file = getFileName();
            line = getLineNumber();
            column = getColumnNumber();
        }
        if (severity == 'w' && warnAsError()) {
            severity = 'e';
        }
        this.messages.error(severity, file, line, column, message);
    }

    public void error(char c, String str) {
        char severity = c;
        String message = str;
        if (severity == 'w' && warnAsError()) {
            severity = 'e';
        }
        this.messages.error(severity, (SourceLocator) this, message);
    }

    public void error(char severity, Declaration declaration, String msg1, String msg2) {
        StringBuilder sb;
        Declaration decl = declaration;
        new StringBuilder();
        error(severity, sb.append(msg1).append(decl.getName()).append(msg2).toString(), (String) null, decl);
    }

    public void error(char c, String str, String str2, Declaration declaration) {
        char severity = c;
        String message = str;
        String code = str2;
        Declaration decl = declaration;
        if (severity == 'w' && warnAsError()) {
            severity = 'e';
        }
        String filename = getFileName();
        int line = getLineNumber();
        int column = getColumnNumber();
        int decl_line = decl.getLineNumber();
        if (decl_line > 0) {
            filename = decl.getFileName();
            line = decl_line;
            column = decl.getColumnNumber();
        }
        this.messages.error(severity, filename, line, column, message, code);
    }

    public Expression syntaxError(String str) {
        Expression expression;
        String message = str;
        error('e', message);
        new ErrorExp(message);
        return expression;
    }

    public final int getLineNumber() {
        return this.messages.getLineNumber();
    }

    public final int getColumnNumber() {
        return this.messages.getColumnNumber();
    }

    public final String getFileName() {
        return this.messages.getFileName();
    }

    public String getPublicId() {
        return this.messages.getPublicId();
    }

    public String getSystemId() {
        return this.messages.getSystemId();
    }

    public boolean isStableSourceLocation() {
        return false;
    }

    public void setFile(String filename) {
        this.messages.setFile(filename);
    }

    public void setLine(int line) {
        this.messages.setLine(line);
    }

    public void setColumn(int column) {
        this.messages.setColumn(column);
    }

    public final void setLine(Expression position) {
        this.messages.setLocation(position);
    }

    public void setLine(Object obj) {
        Object location = obj;
        if (location instanceof SourceLocator) {
            this.messages.setLocation((SourceLocator) location);
        }
    }

    public final void setLocation(SourceLocator position) {
        this.messages.setLocation(position);
    }

    public void setLine(String filename, int line, int column) {
        this.messages.setLine(filename, line, column);
    }

    public void letStart() {
        ScopeExp scopeExp;
        new LetExp((Expression[]) null);
        pushScope(scopeExp);
    }

    public Declaration letVariable(Object name, Type type, Expression init) {
        Declaration decl = ((LetExp) this.current_scope).addDeclaration(name, type);
        decl.noteValue(init);
        return decl;
    }

    public void letEnter() {
        LetExp let = (LetExp) this.current_scope;
        Expression[] inits = new Expression[let.countDecls()];
        int i = 0;
        Declaration firstDecl = let.firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl != null) {
                int i2 = i;
                i++;
                inits[i2] = decl.getValue();
                firstDecl = decl.nextDecl();
            } else {
                let.inits = inits;
                this.lexical.push((ScopeExp) let);
                return;
            }
        }
    }

    public LetExp letDone(Expression body) {
        LetExp let = (LetExp) this.current_scope;
        let.body = body;
        pop(let);
        return let;
    }

    private void checkLoop() {
        Throwable th;
        if (((LambdaExp) this.current_scope).getName() != "%do%loop") {
            Throwable th2 = th;
            new Error("internal error - bad loop state");
            throw th2;
        }
    }

    public void loopStart() {
        LambdaExp lambdaExp;
        LetExp letExp;
        new LambdaExp();
        LambdaExp loopLambda = lambdaExp;
        new LetExp(new Expression[]{loopLambda});
        LetExp let = letExp;
        String fname = "%do%loop";
        let.addDeclaration((Object) fname).noteValue(loopLambda);
        loopLambda.setName(fname);
        let.outer = this.current_scope;
        loopLambda.outer = let;
        this.current_scope = loopLambda;
    }

    public Declaration loopVariable(Object name, Type type, Expression expression) {
        Stack<Expression> stack;
        Expression init = expression;
        checkLoop();
        LambdaExp loopLambda = (LambdaExp) this.current_scope;
        Declaration decl = loopLambda.addDeclaration(name, type);
        if (this.exprStack == null) {
            new Stack<>();
            this.exprStack = stack;
        }
        Expression push = this.exprStack.push(init);
        loopLambda.min_args++;
        return decl;
    }

    public void loopEnter() {
        Expression expression;
        Expression expression2;
        checkLoop();
        LambdaExp loopLambda = (LambdaExp) this.current_scope;
        int ninits = loopLambda.min_args;
        loopLambda.max_args = ninits;
        Expression[] inits = new Expression[ninits];
        int i = ninits;
        while (true) {
            i--;
            if (i >= 0) {
                inits[i] = this.exprStack.pop();
            } else {
                LetExp let = (LetExp) loopLambda.outer;
                new ReferenceExp(let.firstDecl());
                new ApplyExp(expression2, inits);
                let.setBody(expression);
                this.lexical.push((ScopeExp) loopLambda);
                return;
            }
        }
    }

    public void loopCond(Expression cond) {
        checkLoop();
        Expression push = this.exprStack.push(cond);
    }

    public void loopBody(Expression body) {
        Expression expression = body;
        ((LambdaExp) this.current_scope).body = expression;
    }

    public Expression loopRepeat(Expression[] exps) {
        Expression recurse;
        Expression expression;
        Expression expression2;
        Expression expression3;
        LambdaExp loopLambda = (LambdaExp) this.current_scope;
        ScopeExp let = loopLambda.outer;
        Declaration fdecl = let.firstDecl();
        Expression cond = this.exprStack.pop();
        new ReferenceExp(fdecl);
        new ApplyExp(expression, exps);
        new BeginExp(loopLambda.body, recurse);
        new IfExp(cond, expression3, QuoteExp.voidExp);
        loopLambda.body = expression2;
        this.lexical.pop((ScopeExp) loopLambda);
        this.current_scope = let.outer;
        return let;
    }

    public Expression loopRepeat() {
        return loopRepeat(Expression.noExpressions);
    }

    public Expression loopRepeat(Expression exp) {
        return loopRepeat(new Expression[]{exp});
    }

    public static ApplyExp makeCoercion(Expression value, Expression type) {
        QuoteExp quoteExp;
        ApplyExp applyExp;
        new QuoteExp(Convert.getInstance());
        QuoteExp c = quoteExp;
        new ApplyExp((Expression) c, type, value);
        return applyExp;
    }

    public static Expression makeCoercion(Expression value, Type type) {
        Expression expression;
        new QuoteExp(type);
        return makeCoercion(value, expression);
    }

    public void loadClassRef(ObjectType objectType) {
        ObjectType clas = objectType;
        CodeAttr code = getCode();
        if (this.curClass.getClassfileVersion() >= 3211264) {
            code.emitPushClass(clas);
        } else if (clas != this.mainClass || !this.mainLambda.isStatic() || this.moduleInstanceMainField == null) {
            code.emitPushString(clas instanceof ClassType ? clas.getName() : clas.getInternalName().replace('/', '.'));
            code.emitInvokeStatic(getForNameHelper());
        } else {
            code.emitGetStatic(this.moduleInstanceMainField);
            code.emitInvokeVirtual(Type.objectType.getDeclaredMethod("getClass", 0));
        }
    }

    public Method getForNameHelper() {
        if (this.forNameHelper == null) {
            Method save_method = this.method;
            this.method = this.curClass.addMethod("class$", 9, string1Arg, (Type) typeClass);
            this.forNameHelper = this.method;
            CodeAttr code = this.method.startCode();
            code.emitLoad(code.getArg(0));
            code.emitPushInt(0);
            code.emitPushString(this.mainClass.getName());
            code.emitInvokeStatic(typeClass.getDeclaredMethod("forName", 1));
            code.emitInvokeVirtual(typeClass.getDeclaredMethod("getClassLoader", 0));
            code.emitInvokeStatic(typeClass.getDeclaredMethod("forName", 3));
            code.emitReturn();
            this.method = save_method;
        }
        return this.forNameHelper;
    }

    public Object resolve(Object obj, boolean z) {
        Symbol symbol;
        Object name = obj;
        boolean function = z;
        Environment env = Environment.getCurrent();
        if (name instanceof String) {
            symbol = env.defaultNamespace().lookup((String) name);
        } else {
            symbol = (Symbol) name;
        }
        if (symbol == null) {
            return null;
        }
        if (!function || !getLanguage().hasSeparateFunctionNamespace()) {
            return env.get((EnvironmentKey) symbol, (Object) null);
        }
        return env.getFunction(symbol, (Object) null);
    }

    public static void setupLiterals(int key) {
        Throwable th;
        Compilation comp = findForImmediateLiterals(key);
        try {
            Class clas = comp.loader.loadClass(comp.mainClass.getName());
            for (Literal init = comp.litTable.literalsChain; init != null; init = init.next) {
                clas.getDeclaredField(init.field.getName()).set((Object) null, init.value);
            }
            comp.litTable = null;
        } catch (Throwable th2) {
            Throwable ex = th2;
            Throwable th3 = th;
            new WrappedException("internal error", ex);
            throw th3;
        }
    }

    public static synchronized int registerForImmediateLiterals(Compilation compilation) {
        int i;
        Compilation comp = compilation;
        synchronized (Compilation.class) {
            int i2 = 0;
            for (Compilation c = chainUninitialized; c != null; c = c.nextUninitialized) {
                if (i2 <= c.keyUninitialized) {
                    i2 = c.keyUninitialized + 1;
                }
            }
            comp.keyUninitialized = i2;
            comp.nextUninitialized = chainUninitialized;
            chainUninitialized = comp;
            i = i2;
        }
        return i;
    }

    public static synchronized Compilation findForImmediateLiterals(int i) {
        Compilation comp;
        Compilation next;
        Compilation compilation;
        int key = i;
        synchronized (Compilation.class) {
            Compilation prev = null;
            Compilation compilation2 = chainUninitialized;
            while (true) {
                comp = compilation2;
                next = comp.nextUninitialized;
                if (comp.keyUninitialized == key) {
                    break;
                }
                prev = comp;
                compilation2 = next;
            }
            if (prev == null) {
                chainUninitialized = next;
            } else {
                prev.nextUninitialized = next;
            }
            comp.nextUninitialized = null;
            compilation = comp;
        }
        return compilation;
    }

    public static Compilation getCurrent() {
        return current.get();
    }

    public static void setCurrent(Compilation comp) {
        current.set(comp);
    }

    public static Compilation setSaveCurrent(Compilation comp) {
        Compilation save = current.get();
        current.set(comp);
        return save;
    }

    public static void restoreCurrent(Compilation saved) {
        current.set(saved);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("<compilation ").append(this.mainLambda).append(">").toString();
    }
}
