package gnu.xquery.lang;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ResolveNames;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.functions.CompileNamedPart;
import gnu.kawa.functions.GetModuleClass;
import gnu.kawa.reflect.SingletonType;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.xml.MakeAttribute;
import gnu.kawa.xml.MakeElement;
import gnu.kawa.xml.NodeType;
import gnu.kawa.xml.XDataType;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.WrongArguments;
import gnu.xml.NamespaceBinding;
import gnu.xml.XMLFilter;
import gnu.xquery.util.NamedCollator;
import gnu.xquery.util.QNameUtils;
import kawa.standard.Scheme;

public class XQResolveNames extends ResolveNames {
    public static final int BASE_URI_BUILTIN = -11;
    public static final int CASTABLE_AS_BUILTIN = -34;
    public static final int CAST_AS_BUILTIN = -33;
    public static final int COLLECTION_BUILTIN = -8;
    public static final int COMPARE_BUILTIN = -4;
    public static final int DEEP_EQUAL_BUILTIN = -25;
    public static final int DEFAULT_COLLATION_BUILTIN = -29;
    public static final int DISTINCT_VALUES_BUILTIN = -5;
    public static final int DOC_AVAILABLE_BUILTIN = -10;
    public static final int DOC_BUILTIN = -9;
    public static final int HANDLE_EXTENSION_BUILTIN = -3;
    public static final int IDREF_BUILTIN = -31;
    public static final int ID_BUILTIN = -30;
    public static final int INDEX_OF_BUILTIN = -15;
    public static final int LANG_BUILTIN = -23;
    public static final int LAST_BUILTIN = -1;
    public static final int LOCAL_NAME_BUILTIN = -6;
    public static final int MAX_BUILTIN = -27;
    public static final int MIN_BUILTIN = -26;
    public static final int NAMESPACE_URI_BUILTIN = -7;
    public static final int NAME_BUILTIN = -24;
    public static final int NORMALIZE_SPACE_BUILTIN = -17;
    public static final int NUMBER_BUILTIN = -28;
    public static final int POSITION_BUILTIN = -2;
    public static final int RESOLVE_PREFIX_BUILTIN = -13;
    public static final int RESOLVE_URI_BUILTIN = -12;
    public static final int ROOT_BUILTIN = -32;
    public static final int STATIC_BASE_URI_BUILTIN = -14;
    public static final int STRING_BUILTIN = -16;
    public static final int UNORDERED_BUILTIN = -18;
    public static final int XS_QNAME_BUILTIN = -35;
    public static final int XS_QNAME_IGNORE_DEFAULT_BUILTIN = -36;
    public static final Declaration castAsDecl = makeBuiltin("(cast as)", -33);
    public static final Declaration castableAsDecl = makeBuiltin("(castable as)", -34);
    public static final Declaration handleExtensionDecl = makeBuiltin("(extension)", -3);
    public static final Declaration lastDecl = makeBuiltin("last", -1);
    public static final Declaration resolvePrefixDecl = makeBuiltin(Symbol.make(XQuery.SCHEMA_NAMESPACE, "(resolve-prefix)"), -13);
    public static final Declaration staticBaseUriDecl = makeBuiltin("static-base-uri", -14);
    public static final Declaration xsQNameDecl = makeBuiltin(Symbol.make(XQuery.SCHEMA_NAMESPACE, "QName"), -35);
    public static final Declaration xsQNameIgnoreDefaultDecl = makeBuiltin(Symbol.make(XQuery.SCHEMA_NAMESPACE, "(QName-ignore-default)"), -36);
    public Namespace[] functionNamespacePath;
    private Declaration moduleDecl;
    public XQParser parser;

    public static Declaration makeBuiltin(String name, int code) {
        return makeBuiltin(Symbol.make(XQuery.XQUERY_FUNCTION_NAMESPACE, name, "fn"), code);
    }

    public static Declaration makeBuiltin(Symbol name, int code) {
        Declaration declaration;
        new Declaration((Object) name);
        Declaration decl = declaration;
        decl.setProcedureDecl(true);
        decl.setCode(code);
        return decl;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public XQResolveNames() {
        this((Compilation) null);
    }

    /* access modifiers changed from: package-private */
    public void pushBuiltin(String name, int code) {
        this.lookup.push(makeBuiltin(name, code));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public XQResolveNames(Compilation comp) {
        super(comp);
        this.functionNamespacePath = XQuery.defaultFunctionNamespacePath;
        this.lookup.push(lastDecl);
        this.lookup.push(xsQNameDecl);
        this.lookup.push(staticBaseUriDecl);
        pushBuiltin("position", -2);
        pushBuiltin("compare", -4);
        pushBuiltin("distinct-values", -5);
        pushBuiltin("local-name", -6);
        pushBuiltin("name", -24);
        pushBuiltin("namespace-uri", -7);
        pushBuiltin("root", -32);
        pushBuiltin("base-uri", -11);
        pushBuiltin("lang", -23);
        pushBuiltin("resolve-uri", -12);
        pushBuiltin("collection", -8);
        pushBuiltin("doc", -9);
        pushBuiltin("document", -9);
        pushBuiltin("doc-available", -10);
        pushBuiltin("index-of", -15);
        pushBuiltin(PropertyTypeConstants.PROPERTY_TYPE_STRING, -16);
        pushBuiltin("normalize-space", -17);
        pushBuiltin("unordered", -18);
        pushBuiltin("deep-equal", -25);
        pushBuiltin("min", -26);
        pushBuiltin("max", -27);
        pushBuiltin("number", -28);
        pushBuiltin("default-collation", -29);
        pushBuiltin("id", -30);
        pushBuiltin("idref", -31);
    }

    /* access modifiers changed from: protected */
    public void push(ScopeExp exp) {
        Declaration firstDecl = exp.firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl != null) {
                push(decl);
                firstDecl = decl.nextDecl();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void push(Declaration declaration) {
        Declaration decl = declaration;
        Compilation comp = getCompilation();
        Object name = decl.getSymbol();
        boolean function = decl.isProcedureDecl();
        if (name instanceof String) {
            if (decl.getLineNumber() <= 0 || comp == null) {
                name = this.parser.namespaceResolve((String) name, function);
            } else {
                String saveFilename = comp.getFileName();
                int saveLine = comp.getLineNumber();
                int saveColumn = comp.getColumnNumber();
                comp.setLocation(decl);
                name = this.parser.namespaceResolve((String) name, function);
                comp.setLine(saveFilename, saveLine, saveColumn);
            }
            if (name != null) {
                decl.setName(name);
            } else {
                return;
            }
        }
        Declaration old = this.lookup.lookup(name, XQuery.instance.getNamespaceOf(decl));
        if (old != null) {
            if (decl.context == old.context) {
                ScopeExp.duplicateDeclarationError(old, decl, comp);
            } else if (XQParser.warnHidePreviousDeclaration && (!(name instanceof Symbol) || ((Symbol) name).getNamespace() != null)) {
                comp.error('w', decl, "declaration ", " hides previous declaration");
            }
        }
        this.lookup.push(decl);
    }

    /* access modifiers changed from: package-private */
    public Declaration flookup(Symbol symbol) {
        Declaration decl;
        Symbol sym = symbol;
        Location loc = XQuery.xqEnvironment.lookup(sym, EnvironmentKey.FUNCTION);
        if (loc == null) {
            return null;
        }
        Location loc2 = loc.getBase();
        if ((loc2 instanceof StaticFieldLocation) && (decl = ((StaticFieldLocation) loc2).getDeclaration()) != null) {
            return decl;
        }
        Object val = loc2.get((Object) null);
        if (val != null) {
            return procToDecl(sym, val);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Expression visitReferenceExp(ReferenceExp exp, Void voidR) {
        Void voidR2 = voidR;
        return visitReferenceExp(exp, (ApplyExp) null);
    }

    /* access modifiers changed from: protected */
    public Expression visitReferenceExp(ReferenceExp referenceExp, ApplyExp applyExp) {
        Symbol sym;
        QuoteExp quoteExp;
        String mname;
        Expression expression;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        ReferenceExp exp = referenceExp;
        ApplyExp call = applyExp;
        if (exp.getBinding() == null) {
            Object symbol = exp.getSymbol();
            boolean needFunction = exp.isProcedureName();
            boolean needType = exp.getFlag(16);
            int namespace = call == null ? 1 : XQuery.namespaceForFunctions(call.getArgCount());
            Declaration decl = this.lookup.lookup(symbol, namespace);
            if (decl == null) {
                if (symbol instanceof Symbol) {
                    Symbol symbol2 = (Symbol) symbol;
                    Symbol sym2 = symbol2;
                    if ("".equals(symbol2.getNamespaceURI())) {
                        String name = sym2.getLocalName();
                        if ("request".equals(name)) {
                            mname = "getCurrentRequest";
                        } else if ("response".equals(name)) {
                            mname = "getCurrentResponse";
                        } else {
                            mname = null;
                        }
                        if (mname != null) {
                            new ApplyExp(ClassType.make("gnu.kawa.servlet.ServletRequestContext").getDeclaredMethod(mname, 0), Expression.noExpressions);
                            return expression;
                        }
                    }
                }
                if (symbol instanceof Symbol) {
                    decl = flookup((Symbol) symbol);
                } else {
                    String name2 = (String) symbol;
                    if (name2.indexOf(58) < 0) {
                        name2 = name2.intern();
                        if (needFunction) {
                            for (int i = 0; i < this.functionNamespacePath.length; i++) {
                                Symbol sym3 = this.functionNamespacePath[i].getSymbol(name2);
                                decl = this.lookup.lookup((Object) sym3, namespace);
                                if (decl != null) {
                                    break;
                                }
                                decl = flookup(sym3);
                                if (decl != null) {
                                    break;
                                }
                            }
                        }
                    }
                    if (decl == null && (sym = this.parser.namespaceResolve(name2, needFunction)) != null) {
                        decl = this.lookup.lookup((Object) sym, namespace);
                        if (decl == null && (needFunction || needType)) {
                            String uri = sym.getNamespaceURI();
                            Type type = null;
                            if (XQuery.SCHEMA_NAMESPACE.equals(uri)) {
                                type = XQuery.getStandardType(sym.getName());
                            } else if (needType && uri == "" && !getCompilation().isPedantic()) {
                                type = Scheme.string2Type(sym.getName());
                            }
                            if (type != null) {
                                new QuoteExp(type);
                                return quoteExp.setLine((Expression) exp);
                            } else if (uri != null && uri.length() > 6 && uri.startsWith("class:")) {
                                return CompileNamedPart.makeExp((Type) ClassType.make(uri.substring(6)), sym.getName());
                            } else {
                                decl = flookup(sym);
                            }
                        }
                    }
                }
            }
            if (decl != null) {
                exp.setBinding(decl);
            } else if (needFunction) {
                new StringBuilder();
                error('e', sb3.append("unknown function ").append(symbol).toString());
            } else if (needType) {
                new StringBuilder();
                this.messages.error('e', exp, sb2.append("unknown type ").append(symbol).toString(), "XPST0051");
            } else {
                new StringBuilder();
                this.messages.error('e', exp, sb.append("unknown variable $").append(symbol).toString(), "XPST0008");
            }
        }
        return exp;
    }

    /* access modifiers changed from: protected */
    public Expression visitSetExp(SetExp setExp, Void ignored) {
        SetExp exp = setExp;
        Expression result = super.visitSetExp(exp, ignored);
        Declaration decl = exp.getBinding();
        if (decl != null && !getCompilation().immediate) {
            Object symbol = decl.getSymbol();
            Object name = symbol;
            if ((symbol instanceof Symbol) && XQuery.LOCAL_NAMESPACE.equals(((Symbol) name).getNamespaceURI())) {
                Expression newValue = exp.getNewValue();
                Expression new_value = newValue;
                if (!(newValue instanceof ApplyExp) || ((ApplyExp) new_value).getFunction() != XQParser.getExternalFunction) {
                    decl.setFlag(16777216);
                    decl.setPrivate(true);
                }
            }
        }
        return result;
    }

    private Expression visitStatements(Expression expression) {
        Expression exp = expression;
        if (exp instanceof BeginExp) {
            BeginExp bbody = (BeginExp) exp;
            Expression[] exps = bbody.getExpressions();
            int nexps = bbody.getExpressionCount();
            for (int i = 0; i < nexps; i++) {
                exps[i] = visitStatements(exps[i]);
            }
        } else if (exp instanceof SetExp) {
            Declaration decl = this.moduleDecl;
            SetExp sexp = (SetExp) exp;
            exp = visitSetExp(sexp, (Void) null);
            if (sexp.isDefining() && sexp.getBinding() == decl) {
                if (!decl.isProcedureDecl()) {
                    push(decl);
                }
                decl = decl.nextDecl();
            }
            this.moduleDecl = decl;
        } else {
            exp = (Expression) visit(exp, null);
        }
        return exp;
    }

    public void resolveModule(ModuleExp moduleExp) {
        ModuleExp exp = moduleExp;
        this.currentLambda = exp;
        Declaration firstDecl = exp.firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl == null) {
                break;
            }
            if (decl.isProcedureDecl()) {
                push(decl);
            }
            firstDecl = decl.nextDecl();
        }
        this.moduleDecl = exp.firstDecl();
        exp.body = visitStatements(exp.body);
        Declaration firstDecl2 = exp.firstDecl();
        while (true) {
            Declaration decl2 = firstDecl2;
            if (decl2 != null) {
                if (decl2.getSymbol() != null) {
                    this.lookup.removeSubsumed(decl2);
                }
                firstDecl2 = decl2.nextDecl();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Expression getCollator(Expression[] expressionArr, int i) {
        QuoteExp quoteExp;
        QuoteExp quoteExp2;
        Expression expression;
        Expression[] args = expressionArr;
        int argno = i;
        if (args == null || args.length <= argno) {
            NamedCollator coll = this.parser.defaultCollator;
            if (coll == null) {
                quoteExp2 = QuoteExp.nullExp;
            } else {
                quoteExp2 = quoteExp;
                new QuoteExp(coll);
            }
            return quoteExp2;
        }
        Expression expression2 = expression;
        new ApplyExp(ClassType.make("gnu.xquery.util.NamedCollator").getDeclaredMethod("find", 1), args[argno]);
        return expression2;
    }

    /* access modifiers changed from: package-private */
    public Expression withCollator(Method method, Expression[] args, String name, int minArgs) {
        Expression expression;
        Object obj;
        new PrimProcedure(method);
        new QuoteExp(obj);
        return withCollator(expression, args, name, minArgs);
    }

    /* access modifiers changed from: package-private */
    public Expression withCollator(Expression expression, Expression[] expressionArr, String name, int i) {
        Expression expression2;
        Expression function = expression;
        Expression[] args = expressionArr;
        int minArgs = i;
        String err = WrongArguments.checkArgCount(name, minArgs, minArgs + 1, args.length);
        if (err != null) {
            return getCompilation().syntaxError(err);
        }
        Expression[] xargs = new Expression[(minArgs + 1)];
        System.arraycopy(args, 0, xargs, 0, minArgs);
        xargs[minArgs] = getCollator(args, minArgs);
        new ApplyExp(function, xargs);
        return expression2;
    }

    /* access modifiers changed from: package-private */
    public Expression withContext(Method method, Expression[] expressionArr, String str, int i) {
        Expression expression;
        Expression expression2;
        StringBuilder sb;
        Expression expression3;
        Method method2 = method;
        Expression[] args = expressionArr;
        String name = str;
        int minArgs = i;
        String err = WrongArguments.checkArgCount(name, minArgs, minArgs + 1, args.length);
        if (err != null) {
            return getCompilation().syntaxError(err);
        }
        if (args.length == minArgs) {
            Expression[] xargs = new Expression[(minArgs + 1)];
            System.arraycopy(args, 0, xargs, 0, minArgs);
            Declaration dot = this.lookup.lookup((Object) XQParser.DOT_VARNAME, false);
            if (dot == null) {
                new StringBuilder();
                String message = sb.append("undefined context for ").append(name).toString();
                this.messages.error('e', message, "XPDY0002");
                new ErrorExp(message);
                return expression3;
            }
            new ReferenceExp(dot);
            xargs[minArgs] = expression2;
            args = xargs;
        }
        new ApplyExp(method2, args);
        return expression;
    }

    private Expression checkArgCount(Expression[] args, Declaration decl, int min, int max) {
        StringBuilder sb;
        new StringBuilder();
        String err = WrongArguments.checkArgCount(sb.append("fn:").append(decl.getName()).toString(), min, max, args.length);
        if (err == null) {
            return null;
        }
        return getCompilation().syntaxError(err);
    }

    /* access modifiers changed from: protected */
    public Expression visitApplyExp(ApplyExp applyExp, Void voidR) {
        Expression func;
        ApplyExp app;
        Symbol sym;
        Expression expression;
        Declaration decl;
        Expression expression2;
        Expression expression3;
        String mname;
        ApplyExp applyExp2;
        ApplyExp applyExp3;
        Expression expression4;
        Expression expression5;
        PrimProcedure pproc;
        ApplyExp applyExp4;
        Expression expression6;
        StringBuilder sb;
        Expression expression7;
        Expression expression8;
        ApplyExp applyExp5;
        Expression expression9;
        NamespaceBinding namespaceBinding;
        Expression func2;
        ApplyExp applyExp6;
        Expression expression10;
        StringBuilder sb2;
        ApplyExp exp = applyExp;
        Void ignored = voidR;
        Expression func3 = exp.getFunction();
        NamespaceBinding namespaceSave = this.parser.constructorNamespaces;
        Object proc = exp.getFunctionValue();
        if (proc instanceof MakeElement) {
            MakeElement mk = (MakeElement) proc;
            NamespaceBinding nschain = NamespaceBinding.nconc(mk.getNamespaceNodes(), namespaceSave);
            mk.setNamespaceNodes(nschain);
            this.parser.constructorNamespaces = nschain;
        }
        if (func3 instanceof ReferenceExp) {
            func = visitReferenceExp((ReferenceExp) func3, exp);
        } else {
            func = (Expression) visit(func3, ignored);
        }
        exp.setFunction(func);
        Expression[] visitExps = visitExps(exp.getArgs(), ignored);
        this.parser.constructorNamespaces = namespaceSave;
        Expression func4 = exp.getFunction();
        if ((func4 instanceof ReferenceExp) && (decl = ((ReferenceExp) func4).getBinding()) != null) {
            int code = decl.getCode();
            int code2 = code;
            if (code < 0) {
                switch (code2) {
                    case XS_QNAME_IGNORE_DEFAULT_BUILTIN /*-36*/:
                    case XS_QNAME_BUILTIN /*-35*/:
                        Expression[] args = exp.getArgs();
                        Expression checkArgCount = checkArgCount(args, decl, 1, 1);
                        Expression err = checkArgCount;
                        if (checkArgCount != null) {
                            return err;
                        }
                        NamespaceBinding constructorNamespaces = this.parser.constructorNamespaces;
                        if (code2 == -36) {
                            new NamespaceBinding((String) null, "", constructorNamespaces);
                            constructorNamespaces = namespaceBinding;
                        }
                        if (args[0] instanceof QuoteExp) {
                            try {
                                Expression expression11 = expression9;
                                new QuoteExp(QNameUtils.resolveQName(((QuoteExp) args[0]).getValue(), constructorNamespaces, this.parser.prologNamespaces));
                                return expression11;
                            } catch (RuntimeException e) {
                                return getCompilation().syntaxError(e.getMessage());
                            }
                        } else {
                            Expression[] expressionArr = new Expression[3];
                            expressionArr[0] = args[0];
                            Expression[] expressionArr2 = expressionArr;
                            new QuoteExp(constructorNamespaces);
                            expressionArr2[1] = expression7;
                            Expression[] xargs = expressionArr2;
                            new QuoteExp(this.parser.prologNamespaces);
                            xargs[2] = expression8;
                            new ApplyExp(ClassType.make("gnu.xquery.util.QNameUtils").getDeclaredMethod("resolveQName", 3), xargs);
                            ApplyExp app2 = applyExp5;
                            app2.setFlag(4);
                            return app2;
                        }
                    case CASTABLE_AS_BUILTIN /*-34*/:
                    case CAST_AS_BUILTIN /*-33*/:
                        Expression[] args2 = exp.getArgs();
                        Expression texp = args2[code2 == -33 ? (char) 0 : 1];
                        Expression qexp = texp;
                        if (texp instanceof ApplyExp) {
                            ApplyExp taexp = (ApplyExp) texp;
                            if (taexp.getFunction().valueIfConstant() == XQParser.proc_OccurrenceType_getInstance) {
                                qexp = taexp.getArg(0);
                            }
                        }
                        Object value = qexp.valueIfConstant();
                        String msg = null;
                        if (value == SingletonType.getInstance()) {
                            msg = "type to 'cast as' or 'castable as' must be atomic";
                        } else if (value == XDataType.anyAtomicType) {
                            msg = "type to 'cast as' or 'castable as' cannot be anyAtomicType";
                        } else if (value == XDataType.anySimpleType) {
                            msg = "type to 'cast as' or 'castable as' cannot be anySimpleType";
                        } else if (value == XDataType.untypedType) {
                            msg = "type to 'cast as' or 'castable as' cannot be untyped";
                        } else if (value == XDataType.NotationType) {
                            msg = "type to 'cast as' or 'castable as' cannot be NOTATION";
                        }
                        if (msg != null) {
                            this.messages.error('e', texp, msg, "XPST0080");
                        }
                        boolean toQName = value == Compilation.typeSymbol && !(texp instanceof ApplyExp);
                        if (code2 == -33) {
                            if (toQName) {
                                return visitApplyExp(XQParser.castQName(args2[1], true), ignored);
                            }
                            func2 = XQParser.makeFunctionExp("gnu.xquery.util.CastAs", "castAs");
                        } else if (!toQName || !(args2[0] instanceof QuoteExp)) {
                            func2 = XQParser.makeFunctionExp("gnu.xquery.lang.XQParser", "castableAs");
                        } else {
                            try {
                                Object resolveQName = QNameUtils.resolveQName(((QuoteExp) args2[0]).getValue(), this.parser.constructorNamespaces, this.parser.prologNamespaces);
                                return XQuery.trueExp;
                            } catch (RuntimeException e2) {
                                RuntimeException runtimeException = e2;
                                return XQuery.falseExp;
                            }
                        }
                        new ApplyExp(func2, args2);
                        return applyExp6.setLine((Expression) exp);
                    case ROOT_BUILTIN /*-32*/:
                        return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("root", 1), exp.getArgs(), "fn:root", 0);
                    case IDREF_BUILTIN /*-31*/:
                        return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("idref", 2), exp.getArgs(), "fn:idref", 1);
                    case ID_BUILTIN /*-30*/:
                        return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("id$X", 3), exp.getArgs(), "fn:id", 1);
                    case DEFAULT_COLLATION_BUILTIN /*-29*/:
                        Expression checkArgCount2 = checkArgCount(exp.getArgs(), decl, 0, 0);
                        Expression err2 = checkArgCount2;
                        if (checkArgCount2 != null) {
                            return err2;
                        }
                        NamedCollator coll = this.parser.defaultCollator;
                        return QuoteExp.getInstance(coll != null ? coll.getName() : NamedCollator.UNICODE_CODEPOINT_COLLATION);
                    case NUMBER_BUILTIN /*-28*/:
                        return withContext(ClassType.make("gnu.xquery.util.NumberValue").getDeclaredMethod("numberValue", 1), exp.getArgs(), "fn:number", 0);
                    case MAX_BUILTIN /*-27*/:
                        return withCollator(ClassType.make("gnu.xquery.util.MinMax").getDeclaredMethod("max", 2), exp.getArgs(), "fn:max", 1);
                    case MIN_BUILTIN /*-26*/:
                        return withCollator(ClassType.make("gnu.xquery.util.MinMax").getDeclaredMethod("min", 2), exp.getArgs(), "fn:min", 1);
                    case -25:
                        return withCollator(ClassType.make("gnu.xquery.util.SequenceUtils").getDeclaredMethod("deepEqual", 3), exp.getArgs(), "fn:deep-equal", 2);
                    case -24:
                        return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("name", 1), exp.getArgs(), "fn:name", 0);
                    case -23:
                        return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("lang", 2), exp.getArgs(), "fn:lang", 1);
                    case -18:
                        Expression[] args3 = exp.getArgs();
                        Expression checkArgCount3 = checkArgCount(args3, decl, 1, 1);
                        Expression err3 = checkArgCount3;
                        if (checkArgCount3 != null) {
                            return err3;
                        }
                        return args3[0];
                    case -17:
                        return withContext(ClassType.make("gnu.xquery.util.StringUtils").getDeclaredMethod("normalizeSpace", 1), exp.getArgs(), "fn:normalize-space", 0);
                    case -16:
                        return withContext(ClassType.make("gnu.xml.TextUtils").getDeclaredMethod("asString", 1), exp.getArgs(), "fn:string", 0);
                    case -15:
                        return withCollator(ClassType.make("gnu.xquery.util.SequenceUtils").getDeclaredMethod("indexOf$X", 4), exp.getArgs(), "fn:index-of", 2);
                    case -14:
                        Expression checkArgCount4 = checkArgCount(exp.getArgs(), decl, 0, 0);
                        Expression err4 = checkArgCount4;
                        if (checkArgCount4 != null) {
                            return err4;
                        }
                        return getBaseUriExpr();
                    case -13:
                        Expression[] args4 = exp.getArgs();
                        Expression checkArgCount5 = checkArgCount(args4, decl, 1, 1);
                        Expression err5 = checkArgCount5;
                        if (checkArgCount5 != null) {
                            return err5;
                        }
                        if (args4[0] instanceof QuoteExp) {
                            Object val = ((QuoteExp) args4[0]).getValue();
                            String prefix = val == null ? null : val.toString();
                            String val2 = QNameUtils.lookupPrefix(prefix, this.parser.constructorNamespaces, this.parser.prologNamespaces);
                            if (val2 == null) {
                                Compilation compilation = getCompilation();
                                new StringBuilder();
                                return compilation.syntaxError(sb.append("unknown namespace prefix '").append(prefix).append("'").toString());
                            }
                            new QuoteExp(val2);
                            return expression6;
                        }
                        Expression[] expressionArr3 = new Expression[3];
                        expressionArr3[0] = args4[0];
                        Expression[] expressionArr4 = expressionArr3;
                        new QuoteExp(this.parser.constructorNamespaces);
                        expressionArr4[1] = expression4;
                        Expression[] xargs2 = expressionArr4;
                        new QuoteExp(this.parser.prologNamespaces);
                        xargs2[2] = expression5;
                        new PrimProcedure(ClassType.make("gnu.xquery.util.QNameUtils").getDeclaredMethod("resolvePrefix", 3));
                        new ApplyExp((Procedure) pproc, xargs2);
                        ApplyExp app3 = applyExp4;
                        app3.setFlag(4);
                        return app3;
                    case -12:
                        Expression[] args5 = exp.getArgs();
                        Expression checkArgCount6 = checkArgCount(args5, decl, 1, 2);
                        Expression err6 = checkArgCount6;
                        if (checkArgCount6 != null) {
                            return err6;
                        }
                        Expression[] margs = new Expression[2];
                        margs[0] = args5[0];
                        if (args5.length == 1) {
                            margs[1] = getBaseUriExpr();
                        } else {
                            margs[1] = args5[1];
                        }
                        new ApplyExp(ClassType.make("gnu.xquery.util.QNameUtils").getDeclaredMethod("resolveURI", 2), margs);
                        return expression3;
                    case -11:
                        return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("baseUri", 1), exp.getArgs(), "fn:base-uri", 0);
                    case -10:
                    case -9:
                        Expression[] args6 = exp.getArgs();
                        ClassType cl = ClassType.make("gnu.xquery.util.NodeUtils");
                        if (code2 == -9) {
                            mname = "docCached";
                            if (XQParser.warnOldVersion && "document".equals(decl.getName())) {
                                getCompilation().error('w', "replace 'document' by 'doc'");
                            }
                        } else {
                            mname = "availableCached";
                        }
                        Method meth = cl.getDeclaredMethod(mname, 2);
                        Expression checkArgCount7 = checkArgCount(args6, decl, 1, 1);
                        Expression err7 = checkArgCount7;
                        if (checkArgCount7 != null) {
                            return err7;
                        }
                        Expression base = getBaseUriExpr();
                        ApplyExp applyExp7 = applyExp2;
                        Expression[] expressionArr5 = new Expression[2];
                        expressionArr5[0] = args6[0];
                        Expression[] expressionArr6 = expressionArr5;
                        expressionArr6[1] = base;
                        new ApplyExp(meth, expressionArr6);
                        ApplyExp aexp = applyExp7;
                        if (code2 == -9) {
                            aexp.setType(NodeType.documentNodeTest);
                        } else {
                            aexp.setType(XDataType.booleanType);
                        }
                        return aexp;
                    case -8:
                        Expression[] args7 = exp.getArgs();
                        Method meth2 = ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("collection", 2);
                        Expression checkArgCount8 = checkArgCount(args7, decl, 0, 1);
                        Expression err8 = checkArgCount8;
                        if (checkArgCount8 != null) {
                            return err8;
                        }
                        Expression base2 = getBaseUriExpr();
                        ApplyExp applyExp8 = applyExp3;
                        Expression[] expressionArr7 = new Expression[2];
                        expressionArr7[0] = args7.length > 0 ? args7[0] : QuoteExp.voidExp;
                        Expression[] expressionArr8 = expressionArr7;
                        expressionArr8[1] = base2;
                        new ApplyExp(meth2, expressionArr8);
                        ApplyExp aexp2 = applyExp8;
                        aexp2.setType(NodeType.documentNodeTest);
                        return aexp2;
                    case -7:
                        return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("namespaceURI", 1), exp.getArgs(), "fn:namespace-uri", 0);
                    case -6:
                        return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("localName", 1), exp.getArgs(), "fn:local-name", 0);
                    case -5:
                        return withCollator(ClassType.make("gnu.xquery.util.DistinctValues").getDeclaredMethod("distinctValues$X", 3), exp.getArgs(), "fn:distinct-values", 1);
                    case -4:
                        return withCollator(ClassType.make("gnu.xquery.util.StringUtils").getDeclaredMethod("compare", 3), exp.getArgs(), "fn:compare", 2);
                    case -3:
                        Compilation comp = getCompilation();
                        Expression[] args8 = exp.getArgs();
                        int i = 0;
                        while (i < args8.length - 1) {
                            Symbol psymbol = this.parser.namespaceResolve((String) ((QuoteExp) args8[i]).getValue(), false);
                            if (psymbol != null) {
                                if (psymbol.getNamespaceURI().length() == 0) {
                                    comp.error('e', "pragma name cannot be in the empty namespace");
                                } else {
                                    Expression replacement = checkPragma(psymbol, args8[i + 1]);
                                    if (replacement != null) {
                                        return replacement;
                                    }
                                }
                            }
                            i += 2;
                        }
                        if (i < args8.length) {
                            return args8[args8.length - 1];
                        }
                        String msg2 = "no recognized pragma or default in extension expression";
                        getMessages().error('e', msg2, "XQST0079");
                        new ErrorExp(msg2);
                        return expression2;
                    case -2:
                    case -1:
                        Symbol sym2 = code2 == -1 ? XQParser.LAST_VARNAME : XQParser.POSITION_VARNAME;
                        Declaration decl2 = this.lookup.lookup((Object) sym2, false);
                        if (decl2 == null) {
                            new StringBuilder();
                            error('e', sb2.append("undefined context for ").append(sym2.getName()).toString());
                        } else {
                            decl2.setCanRead(true);
                        }
                        new ReferenceExp(sym2, decl2);
                        return expression10;
                }
            }
        }
        Object proc2 = exp.getFunctionValue();
        if (proc2 instanceof Type) {
            Expression[] args9 = exp.getArgs();
            if (args9.length != 1) {
                this.messages.error('e', "type constructor requires a single argument");
                return exp;
            }
            Expression expression12 = expression;
            Expression makeFunctionExp = XQParser.makeFunctionExp("gnu.xquery.util.CastAs", "castAs");
            Expression[] expressionArr9 = new Expression[2];
            expressionArr9[0] = exp.getFunction();
            Expression[] expressionArr10 = expressionArr9;
            expressionArr10[1] = args9[0];
            new ApplyExp(makeFunctionExp, expressionArr10);
            return expression12;
        }
        if (proc2 instanceof MakeElement) {
            MakeElement make = (MakeElement) proc2;
            NamespaceBinding nsBindings = make.getNamespaceNodes();
            Symbol tag = make.tag;
            if (tag == null) {
                tag = MakeElement.getTagName(exp);
            }
            NamespaceBinding nsBindings2 = maybeAddNamespace(tag, false, nsBindings);
            Expression[] args10 = exp.getArgs();
            Symbol[] attrSyms = new Symbol[args10.length];
            int nattrSyms = 0;
            for (int i2 = 0; i2 < args10.length; i2++) {
                Expression arg = args10[i2];
                if ((arg instanceof ApplyExp) && (app = (ApplyExp) arg).getFunction() == MakeAttribute.makeAttributeExp && (sym = MakeElement.getTagName(app)) != null) {
                    for (int j = 0; j != nattrSyms; j++) {
                        if (sym.equals(attrSyms[j])) {
                            getCompilation().setLine((Expression) app);
                            Symbol elementSym = MakeElement.getTagName(exp);
                            this.messages.error('e', XMLFilter.duplicateAttributeMessage(sym, elementSym == null ? null : elementSym.toString()), "XQST0040");
                        }
                    }
                    int i3 = nattrSyms;
                    nattrSyms++;
                    attrSyms[i3] = sym;
                    nsBindings2 = maybeAddNamespace(sym, true, nsBindings2);
                }
            }
            if (nsBindings2 != null) {
                make.setNamespaceNodes(nsBindings2);
            }
        }
        return exp;
    }

    public Expression checkPragma(Symbol symbol, Expression expression) {
        Symbol symbol2 = symbol;
        Expression expression2 = expression;
        return null;
    }

    /* access modifiers changed from: package-private */
    public Expression getBaseUriExpr() {
        Compilation comp = getCompilation();
        String staticBaseUri = this.parser.getStaticBaseUri();
        if (staticBaseUri != null) {
            return QuoteExp.getInstance(staticBaseUri);
        }
        return GetModuleClass.getModuleClassURI(comp);
    }

    static NamespaceBinding maybeAddNamespace(Symbol symbol, boolean z, NamespaceBinding namespaceBinding) {
        Symbol qname = symbol;
        boolean isAttribute = z;
        NamespaceBinding bindings = namespaceBinding;
        if (qname == null) {
            return bindings;
        }
        String prefix = qname.getPrefix();
        String uri = qname.getNamespaceURI();
        if (prefix == "") {
            prefix = null;
        }
        if (uri == "") {
            uri = null;
        }
        if (isAttribute && prefix == null && uri == null) {
            return bindings;
        }
        return NamespaceBinding.maybeAdd(prefix, uri, bindings);
    }

    static Declaration procToDecl(Object symbol, Object val) {
        Declaration declaration;
        Expression expression;
        new Declaration(symbol);
        Declaration decl = declaration;
        decl.setProcedureDecl(true);
        new QuoteExp(val);
        decl.noteValue(expression);
        decl.setFlag(16384);
        return decl;
    }
}
