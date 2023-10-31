package kawa.lang;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.Special;
import gnu.kawa.functions.AppendValues;
import gnu.kawa.functions.CompileNamedPart;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.xml.MakeAttribute;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import gnu.xml.NamespaceBinding;
import java.util.Stack;
import java.util.Vector;
import kawa.standard.begin;
import kawa.standard.require;

public class Translator extends Compilation {
    private static Expression errorExp;
    public static final Declaration getNamedPartDecl;
    public LambdaExp curMethodLambda;
    public Macro currentMacroDefinition;
    Syntax currentSyntax;
    private Environment env = Environment.getCurrent();
    public int firstForm;
    public Stack formStack;
    Declaration macroContext;
    public Declaration matchArray;
    Vector notedAccess;
    public PatternScope patternScope;
    public Object pendingForm;
    PairWithPosition positionPair;
    Stack renamedAliasStack;
    public Declaration templateScopeDecl;
    public NamespaceBinding xmlElementNamespaces = NamespaceBinding.predefinedXML;

    /*  JADX ERROR: NullPointerException in pass: CodeShrinkVisitor
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.args.InsnArg.wrapInstruction(InsnArg.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.inline(CodeShrinkVisitor.java:146)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:71)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.visit(CodeShrinkVisitor.java:35)
        */
    static {
        /*
            java.lang.String r2 = "gnu.kawa.functions.GetNamedPart"
            r0 = r2
            java.lang.String r2 = "getNamedPart"
            r1 = r2
            r2 = r0
            r3 = r1
            gnu.expr.Declaration r2 = gnu.expr.Declaration.getDeclarationFromStatic(r2, r3)
            getNamedPartDecl = r2
            gnu.kawa.reflect.StaticFieldLocation r2 = gnu.kawa.lispexpr.LispLanguage.getNamedPartLocation
            gnu.expr.Declaration r3 = getNamedPartDecl
            r2.setDeclaration(r3)
            gnu.expr.ErrorExp r2 = new gnu.expr.ErrorExp
            r5 = r2
            r2 = r5
            r3 = r5
            java.lang.String r4 = "unknown syntax error"
            r3.<init>(r4)
            errorExp = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.Translator.<clinit>():void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Translator(Language language, SourceMessages messages, NameLookup lexical) {
        super(language, messages, lexical);
        Stack stack;
        new Stack();
        this.formStack = stack;
    }

    public final Environment getGlobalEnvironment() {
        return this.env;
    }

    public Expression parse(Object input) {
        return rewrite(input);
    }

    /* JADX INFO: finally extract failed */
    public final Expression rewrite_car(Pair pair, SyntaxForm syntaxForm) {
        Pair pair2 = pair;
        SyntaxForm syntax = syntaxForm;
        if (syntax == null || syntax.getScope() == this.current_scope || (pair2.getCar() instanceof SyntaxForm)) {
            return rewrite_car(pair2, false);
        }
        ScopeExp save_scope = this.current_scope;
        try {
            setCurrentScope(syntax.getScope());
            Expression rewrite_car = rewrite_car(pair2, false);
            setCurrentScope(save_scope);
            return rewrite_car;
        } catch (Throwable th) {
            Throwable th2 = th;
            setCurrentScope(save_scope);
            throw th2;
        }
    }

    public final Expression rewrite_car(Pair pair, boolean z) {
        Pair pair2 = pair;
        boolean function = z;
        Object car = pair2.getCar();
        if (pair2 instanceof PairWithPosition) {
            return rewrite_with_position(car, function, (PairWithPosition) pair2);
        }
        return rewrite(car, function);
    }

    public Syntax getCurrentSyntax() {
        return this.currentSyntax;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public Expression apply_rewrite(Syntax syntax, Pair form) {
        Syntax syntax2 = syntax;
        Expression expression = errorExp;
        Syntax saveSyntax = this.currentSyntax;
        this.currentSyntax = syntax2;
        try {
            Expression exp = syntax2.rewriteForm(form, this);
            this.currentSyntax = saveSyntax;
            return exp;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.currentSyntax = saveSyntax;
            throw th2;
        }
    }

    static ReferenceExp getOriginalRef(Declaration declaration) {
        Declaration decl = declaration;
        if (decl != null && decl.isAlias() && !decl.isIndirectBinding()) {
            Expression value = decl.getValue();
            if (value instanceof ReferenceExp) {
                return (ReferenceExp) value;
            }
        }
        return null;
    }

    public final boolean selfEvaluatingSymbol(Object obj) {
        return ((LispLanguage) getLanguage()).selfEvaluatingSymbol(obj);
    }

    public final boolean matches(Object form, String literal) {
        return matches(form, (SyntaxForm) null, literal);
    }

    public boolean matches(Object obj, SyntaxForm syntax, String str) {
        ReferenceExp rexp;
        Object form = obj;
        String literal = str;
        if (syntax != null) {
        }
        if (form instanceof SyntaxForm) {
            form = ((SyntaxForm) form).getDatum();
        }
        if ((form instanceof SimpleSymbol) && !selfEvaluatingSymbol(form) && (rexp = getOriginalRef(this.lexical.lookup(form, -1))) != null) {
            form = rexp.getSymbol();
        }
        return (form instanceof SimpleSymbol) && ((Symbol) form).getLocalPart() == literal;
    }

    public boolean matches(Object obj, SyntaxForm syntax, Symbol symbol) {
        ReferenceExp rexp;
        Object form = obj;
        Symbol literal = symbol;
        if (syntax != null) {
        }
        if (form instanceof SyntaxForm) {
            form = ((SyntaxForm) form).getDatum();
        }
        if ((form instanceof SimpleSymbol) && !selfEvaluatingSymbol(form) && (rexp = getOriginalRef(this.lexical.lookup(form, -1))) != null) {
            form = rexp.getSymbol();
        }
        return form == literal;
    }

    public Object matchQuoted(Pair pair) {
        Pair pair2 = pair;
        if (matches(pair2.getCar(), LispLanguage.quote_sym) && (pair2.getCdr() instanceof Pair)) {
            Pair pair3 = (Pair) pair2.getCdr();
            Pair pair4 = pair3;
            if (pair3.getCdr() == LList.Empty) {
                return pair4.getCar();
            }
        }
        return null;
    }

    public Declaration lookup(Object obj, int i) {
        Object name = obj;
        int namespace = i;
        Declaration decl = this.lexical.lookup(name, namespace);
        if (decl == null || !getLanguage().hasNamespace(decl, namespace)) {
            return currentModule().lookup(name, getLanguage(), namespace);
        }
        return decl;
    }

    public Declaration lookupGlobal(Object name) {
        return lookupGlobal(name, -1);
    }

    public Declaration lookupGlobal(Object obj, int namespace) {
        Object name = obj;
        ModuleExp module = currentModule();
        Declaration decl = module.lookup(name, getLanguage(), namespace);
        if (decl == null) {
            decl = module.getNoDefine(name);
            decl.setIndirectBinding(true);
        }
        return decl;
    }

    /* access modifiers changed from: package-private */
    public Syntax check_if_Syntax(Declaration declaration) {
        StringBuilder sb;
        Declaration decl = declaration;
        Declaration d = Declaration.followAliases(decl);
        Object obj = null;
        Expression dval = d.getValue();
        if (dval != null && d.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID)) {
            try {
                if (decl.getValue() instanceof ReferenceExp) {
                    Declaration context = ((ReferenceExp) decl.getValue()).contextDecl();
                    if (context != null) {
                        this.macroContext = context;
                    } else if (this.current_scope instanceof TemplateScope) {
                        this.macroContext = ((TemplateScope) this.current_scope).macroContext;
                    }
                } else if (this.current_scope instanceof TemplateScope) {
                    this.macroContext = ((TemplateScope) this.current_scope).macroContext;
                }
                obj = dval.eval(this.env);
            } catch (Throwable th) {
                th.printStackTrace();
                new StringBuilder();
                error('e', sb.append("unable to evaluate macro for ").append(decl.getSymbol()).toString());
            }
        } else if (decl.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID) && !decl.needsContext()) {
            obj = StaticFieldLocation.make(decl).get((Object) null);
        }
        return obj instanceof Syntax ? (Syntax) obj : null;
    }

    public Expression rewrite_pair(Pair pair, boolean z) {
        Stack stack;
        Expression expression;
        Expression expression2;
        StringBuilder sb;
        StringBuilder sb2;
        Symbol symbol;
        Pair p = pair;
        boolean function = z;
        Expression func = rewrite_car(p, true);
        if (func instanceof QuoteExp) {
            Object proc = func.valueIfConstant();
            if (proc instanceof Syntax) {
                return apply_rewrite((Syntax) proc, p);
            }
        }
        if (func instanceof ReferenceExp) {
            ReferenceExp ref = (ReferenceExp) func;
            Declaration decl = ref.getBinding();
            if (decl == null) {
                Object sym = ref.getSymbol();
                if (!(sym instanceof Symbol) || selfEvaluatingSymbol(sym)) {
                    symbol = this.env.getSymbol(sym.toString());
                } else {
                    symbol = (Symbol) sym;
                    String name = symbol.getName();
                }
                Object proc2 = this.env.get(symbol, getLanguage().hasSeparateFunctionNamespace() ? EnvironmentKey.FUNCTION : null, (Object) null);
                if (proc2 instanceof Syntax) {
                    return apply_rewrite((Syntax) proc2, p);
                }
                if (proc2 instanceof AutoloadProcedure) {
                    try {
                        Object proc3 = ((AutoloadProcedure) proc2).getLoaded();
                    } catch (RuntimeException e) {
                        RuntimeException runtimeException = e;
                    }
                }
            } else {
                Declaration saveContext = this.macroContext;
                Syntax syntax = check_if_Syntax(decl);
                if (syntax != null) {
                    Expression e2 = apply_rewrite(syntax, p);
                    this.macroContext = saveContext;
                    return e2;
                }
            }
            ref.setProcedureName(true);
            if (getLanguage().hasSeparateFunctionNamespace()) {
                func.setFlag(8);
            }
        }
        Object cdr = p.getCdr();
        int cdr_length = listLength(cdr);
        if (cdr_length == -1) {
            new StringBuilder();
            return syntaxError(sb2.append("circular list is not allowed after ").append(p.getCar()).toString());
        } else if (cdr_length < 0) {
            new StringBuilder();
            return syntaxError(sb.append("dotted list [").append(cdr).append("] is not allowed after ").append(p.getCar()).toString());
        } else {
            boolean mapKeywordsToAttributes = false;
            new Stack();
            Stack vec = stack;
            ScopeExp save_scope = this.current_scope;
            int i = 0;
            while (i < cdr_length) {
                if (cdr instanceof SyntaxForm) {
                    SyntaxForm sf = (SyntaxForm) cdr;
                    cdr = sf.getDatum();
                    setCurrentScope(sf.getScope());
                }
                Pair cdr_pair = (Pair) cdr;
                Expression arg = rewrite_car(cdr_pair, false);
                i++;
                if (mapKeywordsToAttributes) {
                    if ((i & 1) == 0) {
                        new ApplyExp((Procedure) MakeAttribute.makeAttribute, (Expression) vec.pop(), arg);
                        arg = expression2;
                    } else {
                        if (arg instanceof QuoteExp) {
                            Object value = ((QuoteExp) arg).getValue();
                            Object value2 = value;
                            if ((value instanceof Keyword) && i < cdr_length) {
                                new QuoteExp(((Keyword) value2).asSymbol());
                                arg = expression;
                            }
                        }
                        mapKeywordsToAttributes = false;
                    }
                }
                vec.addElement(arg);
                cdr = cdr_pair.getCdr();
            }
            Expression[] args = new Expression[vec.size()];
            vec.copyInto(args);
            if (save_scope != this.current_scope) {
                setCurrentScope(save_scope);
            }
            if (!(func instanceof ReferenceExp) || ((ReferenceExp) func).getBinding() != getNamedPartDecl) {
                return ((LispLanguage) getLanguage()).makeApply(func, args);
            }
            Expression part1 = args[0];
            Expression part2 = args[1];
            Symbol sym2 = namespaceResolve(part1, part2);
            if (sym2 != null) {
                return rewrite(sym2, function);
            }
            return CompileNamedPart.makeExp(part1, part2);
        }
    }

    public Namespace namespaceResolvePrefix(Expression expression) {
        Object obj;
        Expression context = expression;
        if (context instanceof ReferenceExp) {
            ReferenceExp rexp = (ReferenceExp) context;
            Declaration decl = rexp.getBinding();
            if (decl == null || decl.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH)) {
                Object rsym = rexp.getSymbol();
                obj = this.env.get((EnvironmentKey) rsym instanceof Symbol ? (Symbol) rsym : this.env.getSymbol(rsym.toString()), (Object) null);
            } else if (decl.isNamespaceDecl()) {
                obj = decl.getConstantValue();
            } else {
                obj = null;
            }
            if (obj instanceof Namespace) {
                Namespace ns = (Namespace) obj;
                String uri = ns.getName();
                if (uri == null || !uri.startsWith("class:")) {
                    return ns;
                }
                return null;
            }
        }
        return null;
    }

    public Symbol namespaceResolve(Namespace namespace, Expression expression) {
        Namespace ns = namespace;
        Expression member = expression;
        if (ns == null || !(member instanceof QuoteExp)) {
            return null;
        }
        return ns.getSymbol(((QuoteExp) member).getValue().toString().intern());
    }

    public Symbol namespaceResolve(Expression context, Expression member) {
        return namespaceResolve(namespaceResolvePrefix(context), member);
    }

    public static Object stripSyntax(Object obj) {
        Object obj2 = obj;
        while (obj2 instanceof SyntaxForm) {
            obj2 = ((SyntaxForm) obj2).getDatum();
        }
        return obj2;
    }

    public static Object safeCar(Object obj) {
        Object obj2 = obj;
        while (obj2 instanceof SyntaxForm) {
            obj2 = ((SyntaxForm) obj2).getDatum();
        }
        if (!(obj2 instanceof Pair)) {
            return null;
        }
        return stripSyntax(((Pair) obj2).getCar());
    }

    public static Object safeCdr(Object obj) {
        Object obj2 = obj;
        while (obj2 instanceof SyntaxForm) {
            obj2 = ((SyntaxForm) obj2).getDatum();
        }
        if (!(obj2 instanceof Pair)) {
            return null;
        }
        return stripSyntax(((Pair) obj2).getCdr());
    }

    public static int listLength(Object obj) {
        Object next;
        Object obj2 = obj;
        int n = 0;
        Object slow = obj2;
        Object fast = obj2;
        while (true) {
            if (fast instanceof SyntaxForm) {
                fast = ((SyntaxForm) fast).getDatum();
            } else {
                while (slow instanceof SyntaxForm) {
                    slow = ((SyntaxForm) slow).getDatum();
                }
                if (fast == LList.Empty) {
                    return n;
                }
                if (!(fast instanceof Pair)) {
                    return -1 - n;
                }
                int n2 = n + 1;
                Object cdr = ((Pair) fast).getCdr();
                while (true) {
                    next = cdr;
                    if (!(next instanceof SyntaxForm)) {
                        break;
                    }
                    cdr = ((SyntaxForm) next).getDatum();
                }
                if (next == LList.Empty) {
                    return n2;
                }
                if (!(next instanceof Pair)) {
                    return -1 - n2;
                }
                slow = ((Pair) slow).getCdr();
                fast = ((Pair) next).getCdr();
                n = n2 + 1;
                if (fast == slow) {
                    return Integer.MIN_VALUE;
                }
            }
        }
    }

    public void rewriteInBody(Object obj) {
        Object exp = obj;
        if (exp instanceof SyntaxForm) {
            SyntaxForm sf = (SyntaxForm) exp;
            ScopeExp save_scope = this.current_scope;
            try {
                setCurrentScope(sf.getScope());
                rewriteInBody(sf.getDatum());
                setCurrentScope(save_scope);
            } catch (Throwable th) {
                Throwable th2 = th;
                setCurrentScope(save_scope);
                throw th2;
            }
        } else if (exp instanceof Values) {
            Object[] vals = ((Values) exp).getValues();
            for (int i = 0; i < vals.length; i++) {
                rewriteInBody(vals[i]);
            }
        } else {
            boolean add = this.formStack.add(rewrite(exp, false));
        }
    }

    public Expression rewrite(Object exp) {
        return rewrite(exp, false);
    }

    public Object namespaceResolve(Object obj) {
        Object name = obj;
        if (!(name instanceof SimpleSymbol) && (name instanceof Pair)) {
            Pair pair = (Pair) name;
            Pair p = pair;
            if (safeCar(pair) == LispLanguage.lookup_sym && (p.getCdr() instanceof Pair)) {
                Pair pair2 = (Pair) p.getCdr();
                Pair p2 = pair2;
                if (pair2.getCdr() instanceof Pair) {
                    Expression part1 = rewrite(p2.getCar());
                    Expression part2 = rewrite(((Pair) p2.getCdr()).getCar());
                    Symbol sym = namespaceResolve(part1, part2);
                    if (sym != null) {
                        return sym;
                    }
                    String combinedName = CompileNamedPart.combineName(part1, part2);
                    if (combinedName != null) {
                        return Namespace.EmptyNamespace.getSymbol(combinedName);
                    }
                }
            }
        }
        return name;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0406, code lost:
        if ((r16 instanceof gnu.bytecode.ArrayClassLoader) == false) goto L_0x0396;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression rewrite(java.lang.Object r24, boolean r25) {
        /*
            r23 = this;
            r2 = r23
            r3 = r24
            r4 = r25
            r17 = r3
            r0 = r17
            boolean r0 = r0 instanceof kawa.lang.SyntaxForm
            r17 = r0
            if (r17 == 0) goto L_0x0058
            r17 = r3
            kawa.lang.SyntaxForm r17 = (kawa.lang.SyntaxForm) r17
            r5 = r17
            r17 = r2
            r0 = r17
            gnu.expr.ScopeExp r0 = r0.current_scope
            r17 = r0
            r6 = r17
            r17 = r2
            r18 = r5
            kawa.lang.TemplateScope r18 = r18.getScope()     // Catch:{ all -> 0x004b }
            r17.setCurrentScope(r18)     // Catch:{ all -> 0x004b }
            r17 = r2
            r18 = r5
            java.lang.Object r18 = r18.getDatum()     // Catch:{ all -> 0x004b }
            r19 = r4
            gnu.expr.Expression r17 = r17.rewrite(r18, r19)     // Catch:{ all -> 0x004b }
            r7 = r17
            r17 = r7
            r8 = r17
            r17 = r2
            r18 = r6
            r17.setCurrentScope(r18)
            r17 = r8
            r2 = r17
        L_0x004a:
            return r2
        L_0x004b:
            r17 = move-exception
            r9 = r17
            r17 = r2
            r18 = r6
            r17.setCurrentScope(r18)
            r17 = r9
            throw r17
        L_0x0058:
            r17 = r3
            r0 = r17
            boolean r0 = r0 instanceof gnu.lists.PairWithPosition
            r17 = r0
            if (r17 == 0) goto L_0x0073
            r17 = r2
            r18 = r3
            r19 = r4
            r20 = r3
            gnu.lists.PairWithPosition r20 = (gnu.lists.PairWithPosition) r20
            gnu.expr.Expression r17 = r17.rewrite_with_position(r18, r19, r20)
            r2 = r17
            goto L_0x004a
        L_0x0073:
            r17 = r3
            r0 = r17
            boolean r0 = r0 instanceof gnu.lists.Pair
            r17 = r0
            if (r17 == 0) goto L_0x008c
            r17 = r2
            r18 = r3
            gnu.lists.Pair r18 = (gnu.lists.Pair) r18
            r19 = r4
            gnu.expr.Expression r17 = r17.rewrite_pair(r18, r19)
            r2 = r17
            goto L_0x004a
        L_0x008c:
            r17 = r3
            r0 = r17
            boolean r0 = r0 instanceof gnu.mapping.Symbol
            r17 = r0
            if (r17 == 0) goto L_0x04ea
            r17 = r2
            r18 = r3
            boolean r17 = r17.selfEvaluatingSymbol(r18)
            if (r17 != 0) goto L_0x04ea
            r17 = r2
            r0 = r17
            gnu.expr.NameLookup r0 = r0.lexical
            r17 = r0
            r18 = r3
            r19 = r4
            gnu.expr.Declaration r17 = r17.lookup((java.lang.Object) r18, (boolean) r19)
            r5 = r17
            r17 = 0
            r6 = r17
            r17 = r2
            r0 = r17
            gnu.expr.ScopeExp r0 = r0.current_scope
            r17 = r0
            r7 = r17
            r17 = r5
            if (r17 != 0) goto L_0x01b4
            r17 = -1
        L_0x00c6:
            r8 = r17
            r17 = r3
            r0 = r17
            boolean r0 = r0 instanceof gnu.mapping.Symbol
            r17 = r0
            if (r17 == 0) goto L_0x01c2
            r17 = r3
            gnu.mapping.Symbol r17 = (gnu.mapping.Symbol) r17
            boolean r17 = r17.hasEmptyNamespace()
            if (r17 == 0) goto L_0x01c2
            r17 = r3
            java.lang.String r17 = r17.toString()
            r9 = r17
        L_0x00e4:
            r17 = r7
            if (r17 == 0) goto L_0x0120
            r17 = r7
            r0 = r17
            boolean r0 = r0 instanceof gnu.expr.LambdaExp
            r17 = r0
            if (r17 == 0) goto L_0x024f
            r17 = r7
            r0 = r17
            gnu.expr.ScopeExp r0 = r0.outer
            r17 = r0
            r0 = r17
            boolean r0 = r0 instanceof gnu.expr.ClassExp
            r17 = r0
            if (r17 == 0) goto L_0x024f
            r17 = r7
            gnu.expr.LambdaExp r17 = (gnu.expr.LambdaExp) r17
            boolean r17 = r17.isClassMethod()
            if (r17 == 0) goto L_0x024f
            r17 = r8
            r18 = r7
            r0 = r18
            gnu.expr.ScopeExp r0 = r0.outer
            r18 = r0
            int r18 = gnu.expr.ScopeExp.nesting(r18)
            r0 = r17
            r1 = r18
            if (r0 < r1) goto L_0x01cc
        L_0x0120:
            r17 = r5
            if (r17 == 0) goto L_0x02a6
            r17 = r5
            java.lang.Object r17 = r17.getSymbol()
            r10 = r17
            r17 = 0
            r3 = r17
            r17 = r5
            gnu.expr.ReferenceExp r17 = getOriginalRef(r17)
            r11 = r17
            r17 = r11
            if (r17 == 0) goto L_0x0154
            r17 = r11
            gnu.expr.Declaration r17 = r17.getBinding()
            r5 = r17
            r17 = r5
            if (r17 != 0) goto L_0x0154
            r17 = r11
            java.lang.Object r17 = r17.getSymbol()
            r3 = r17
            r17 = r3
            r10 = r17
        L_0x0154:
            r17 = r3
            gnu.mapping.Symbol r17 = (gnu.mapping.Symbol) r17
            r11 = r17
            r17 = r2
            gnu.expr.Language r17 = r17.getLanguage()
            boolean r17 = r17.hasSeparateFunctionNamespace()
            r12 = r17
            r17 = r5
            if (r17 == 0) goto L_0x031e
            r17 = r2
            r0 = r17
            gnu.expr.ScopeExp r0 = r0.current_scope
            r17 = r0
            r0 = r17
            boolean r0 = r0 instanceof kawa.lang.TemplateScope
            r17 = r0
            if (r17 == 0) goto L_0x02ac
            r17 = r5
            boolean r17 = r17.needsContext()
            if (r17 == 0) goto L_0x02ac
            r17 = r2
            r0 = r17
            gnu.expr.ScopeExp r0 = r0.current_scope
            r17 = r0
            kawa.lang.TemplateScope r17 = (kawa.lang.TemplateScope) r17
            r0 = r17
            gnu.expr.Declaration r0 = r0.macroContext
            r17 = r0
            r6 = r17
        L_0x0194:
            r17 = r5
            if (r17 == 0) goto L_0x04b6
            r17 = r4
            if (r17 != 0) goto L_0x0477
            r17 = r5
            java.lang.Object r17 = r17.getConstantValue()
            r0 = r17
            boolean r0 = r0 instanceof kawa.standard.object
            r17 = r0
            if (r17 == 0) goto L_0x0477
            java.lang.Class<java.lang.Object> r17 = java.lang.Object.class
            gnu.expr.QuoteExp r17 = gnu.expr.QuoteExp.getInstance(r17)
            r2 = r17
            goto L_0x004a
        L_0x01b4:
            r17 = r5
            r0 = r17
            gnu.expr.ScopeExp r0 = r0.context
            r17 = r0
            int r17 = gnu.expr.ScopeExp.nesting(r17)
            goto L_0x00c6
        L_0x01c2:
            r17 = 0
            r9 = r17
            r17 = 0
            r7 = r17
            goto L_0x00e4
        L_0x01cc:
            r17 = r7
            gnu.expr.LambdaExp r17 = (gnu.expr.LambdaExp) r17
            r10 = r17
            r17 = r7
            r0 = r17
            gnu.expr.ScopeExp r0 = r0.outer
            r17 = r0
            gnu.expr.ClassExp r17 = (gnu.expr.ClassExp) r17
            r11 = r17
            r17 = r11
            gnu.bytecode.ClassType r17 = r17.getClassType()
            r12 = r17
            r17 = r12
            r18 = r9
            r19 = r12
            gnu.bytecode.Member r17 = gnu.kawa.reflect.SlotGet.lookupMember(r17, r18, r19)
            r13 = r17
            r17 = r10
            r18 = r11
            r0 = r18
            gnu.expr.LambdaExp r0 = r0.clinitMethod
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x0220
            r17 = r10
            r18 = r11
            r0 = r18
            gnu.expr.LambdaExp r0 = r0.initMethod
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x025b
            r17 = r10
            r0 = r17
            gnu.expr.Declaration r0 = r0.nameDecl
            r17 = r0
            boolean r17 = r17.isStatic()
            if (r17 == 0) goto L_0x025b
        L_0x0220:
            r17 = 1
        L_0x0222:
            r14 = r17
            r17 = r13
            if (r17 != 0) goto L_0x0261
            r17 = r14
            if (r17 == 0) goto L_0x025e
            r17 = 83
        L_0x022e:
            r15 = r17
            r17 = r12
            r18 = r9
            r19 = r15
            r20 = r12
            r21 = r2
            r0 = r21
            gnu.expr.Language r0 = r0.language
            r21 = r0
            gnu.expr.PrimProcedure[] r17 = gnu.kawa.reflect.ClassMethods.getMethods(r17, r18, r19, r20, r21)
            r16 = r17
            r17 = r16
            r0 = r17
            int r0 = r0.length
            r17 = r0
            if (r17 != 0) goto L_0x0261
        L_0x024f:
            r17 = r7
            r0 = r17
            gnu.expr.ScopeExp r0 = r0.outer
            r17 = r0
            r7 = r17
            goto L_0x00e4
        L_0x025b:
            r17 = 0
            goto L_0x0222
        L_0x025e:
            r17 = 86
            goto L_0x022e
        L_0x0261:
            r17 = r14
            if (r17 == 0) goto L_0x0292
            gnu.expr.ReferenceExp r17 = new gnu.expr.ReferenceExp
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r10
            r0 = r19
            gnu.expr.ScopeExp r0 = r0.outer
            r19 = r0
            gnu.expr.ClassExp r19 = (gnu.expr.ClassExp) r19
            r0 = r19
            gnu.expr.Declaration r0 = r0.nameDecl
            r19 = r0
            r18.<init>((gnu.expr.Declaration) r19)
            r15 = r17
        L_0x0282:
            r17 = r15
            r18 = r9
            gnu.expr.QuoteExp r18 = gnu.expr.QuoteExp.getInstance(r18)
            gnu.expr.Expression r17 = gnu.kawa.functions.CompileNamedPart.makeExp((gnu.expr.Expression) r17, (gnu.expr.Expression) r18)
            r2 = r17
            goto L_0x004a
        L_0x0292:
            gnu.expr.ThisExp r17 = new gnu.expr.ThisExp
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r10
            gnu.expr.Declaration r19 = r19.firstDecl()
            r18.<init>((gnu.expr.Declaration) r19)
            r15 = r17
            goto L_0x0282
        L_0x02a6:
            r17 = r3
            r10 = r17
            goto L_0x0154
        L_0x02ac:
            r17 = r5
            r18 = 1048576(0x100000, double:5.180654E-318)
            boolean r17 = r17.getFlag(r18)
            if (r17 == 0) goto L_0x0194
            r17 = r5
            boolean r17 = r17.isStatic()
            if (r17 != 0) goto L_0x0194
            r17 = r2
            gnu.expr.ScopeExp r17 = r17.currentScope()
            r7 = r17
        L_0x02c7:
            r17 = r7
            if (r17 != 0) goto L_0x02f3
            java.lang.Error r17 = new java.lang.Error
            r22 = r17
            r17 = r22
            r18 = r22
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r22 = r19
            r19 = r22
            r20 = r22
            r20.<init>()
            java.lang.String r20 = "internal error: missing "
            java.lang.StringBuilder r19 = r19.append(r20)
            r20 = r5
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            r18.<init>(r19)
            throw r17
        L_0x02f3:
            r17 = r7
            r0 = r17
            gnu.expr.ScopeExp r0 = r0.outer
            r17 = r0
            r18 = r5
            r0 = r18
            gnu.expr.ScopeExp r0 = r0.context
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0313
            r17 = r7
            gnu.expr.Declaration r17 = r17.firstDecl()
            r6 = r17
            goto L_0x0194
        L_0x0313:
            r17 = r7
            r0 = r17
            gnu.expr.ScopeExp r0 = r0.outer
            r17 = r0
            r7 = r17
            goto L_0x02c7
        L_0x031e:
            r17 = r2
            r0 = r17
            gnu.mapping.Environment r0 = r0.env
            r17 = r0
            r18 = r11
            r19 = r4
            if (r19 == 0) goto L_0x0398
            r19 = r12
            if (r19 == 0) goto L_0x0398
            java.lang.Object r19 = gnu.mapping.EnvironmentKey.FUNCTION
        L_0x0332:
            gnu.mapping.Location r17 = r17.lookup(r18, r19)
            r13 = r17
            r17 = r13
            if (r17 == 0) goto L_0x0344
            r17 = r13
            gnu.mapping.Location r17 = r17.getBase()
            r13 = r17
        L_0x0344:
            r17 = r13
            r0 = r17
            boolean r0 = r0 instanceof gnu.kawa.reflect.FieldLocation
            r17 = r0
            if (r17 == 0) goto L_0x044f
            r17 = r13
            gnu.kawa.reflect.FieldLocation r17 = (gnu.kawa.reflect.FieldLocation) r17
            r14 = r17
            r17 = r14
            gnu.expr.Declaration r17 = r17.getDeclaration()     // Catch:{ Throwable -> 0x0412 }
            r5 = r17
            r17 = r2
            r18 = 0
            boolean r17 = r17.inlineOk((gnu.expr.Expression) r18)     // Catch:{ Throwable -> 0x0412 }
            if (r17 != 0) goto L_0x039b
            r17 = r5
            gnu.expr.Declaration r18 = getNamedPartDecl     // Catch:{ Throwable -> 0x0412 }
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x039b
            java.lang.String r17 = "objectSyntax"
            r18 = r14
            java.lang.String r18 = r18.getMemberName()     // Catch:{ Throwable -> 0x0412 }
            boolean r17 = r17.equals(r18)     // Catch:{ Throwable -> 0x0412 }
            if (r17 == 0) goto L_0x0392
            java.lang.String r17 = "kawa.standard.object"
            r18 = r14
            gnu.bytecode.ClassType r18 = r18.getDeclaringClass()     // Catch:{ Throwable -> 0x0412 }
            java.lang.String r18 = r18.getName()     // Catch:{ Throwable -> 0x0412 }
            boolean r17 = r17.equals(r18)     // Catch:{ Throwable -> 0x0412 }
            if (r17 != 0) goto L_0x039b
        L_0x0392:
            r17 = 0
            r5 = r17
        L_0x0396:
            goto L_0x0194
        L_0x0398:
            r19 = 0
            goto L_0x0332
        L_0x039b:
            r17 = r2
            r0 = r17
            boolean r0 = r0.immediate     // Catch:{ Throwable -> 0x0412 }
            r17 = r0
            if (r17 == 0) goto L_0x03d4
            r17 = r5
            boolean r17 = r17.isStatic()     // Catch:{ Throwable -> 0x0412 }
            if (r17 != 0) goto L_0x0396
            gnu.expr.Declaration r17 = new gnu.expr.Declaration     // Catch:{ Throwable -> 0x0412 }
            r22 = r17
            r17 = r22
            r18 = r22
            java.lang.String r19 = "(module-instance)"
            r18.<init>((java.lang.Object) r19)     // Catch:{ Throwable -> 0x0412 }
            r6 = r17
            r17 = r6
            gnu.expr.QuoteExp r18 = new gnu.expr.QuoteExp     // Catch:{ Throwable -> 0x0412 }
            r22 = r18
            r18 = r22
            r19 = r22
            r20 = r14
            java.lang.Object r20 = r20.getInstance()     // Catch:{ Throwable -> 0x0412 }
            r19.<init>(r20)     // Catch:{ Throwable -> 0x0412 }
            r17.setValue(r18)     // Catch:{ Throwable -> 0x0412 }
            goto L_0x0396
        L_0x03d4:
            r17 = r5
            boolean r17 = r17.isStatic()     // Catch:{ Throwable -> 0x0412 }
            if (r17 == 0) goto L_0x040d
            r17 = r14
            java.lang.Class r17 = r17.getRClass()     // Catch:{ Throwable -> 0x0412 }
            r15 = r17
            r17 = r15
            if (r17 == 0) goto L_0x0408
            r17 = r15
            java.lang.ClassLoader r17 = r17.getClassLoader()     // Catch:{ Throwable -> 0x0412 }
            r22 = r17
            r17 = r22
            r18 = r22
            r16 = r18
            r0 = r17
            boolean r0 = r0 instanceof gnu.bytecode.ZipLoader     // Catch:{ Throwable -> 0x0412 }
            r17 = r0
            if (r17 != 0) goto L_0x0408
            r17 = r16
            r0 = r17
            boolean r0 = r0 instanceof gnu.bytecode.ArrayClassLoader     // Catch:{ Throwable -> 0x0412 }
            r17 = r0
            if (r17 == 0) goto L_0x040c
        L_0x0408:
            r17 = 0
            r5 = r17
        L_0x040c:
            goto L_0x0396
        L_0x040d:
            r17 = 0
            r5 = r17
            goto L_0x0396
        L_0x0412:
            r17 = move-exception
            r15 = r17
            r17 = r2
            r18 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r22 = r19
            r19 = r22
            r20 = r22
            r20.<init>()
            java.lang.String r20 = "exception loading '"
            java.lang.StringBuilder r19 = r19.append(r20)
            r20 = r3
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r20 = "' - "
            java.lang.StringBuilder r19 = r19.append(r20)
            r20 = r15
            java.lang.String r20 = r20.getMessage()
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            r17.error(r18, r19)
            r17 = 0
            r5 = r17
            goto L_0x0396
        L_0x044f:
            r17 = r13
            if (r17 == 0) goto L_0x045b
            r17 = r13
            boolean r17 = r17.isBound()
            if (r17 != 0) goto L_0x0194
        L_0x045b:
            r17 = r2
            gnu.expr.Language r17 = r17.getLanguage()
            gnu.kawa.lispexpr.LispLanguage r17 = (gnu.kawa.lispexpr.LispLanguage) r17
            r18 = r11
            r19 = r2
            gnu.expr.Expression r17 = r17.checkDefaultBinding(r18, r19)
            r14 = r17
            r17 = r14
            if (r17 == 0) goto L_0x0194
            r17 = r14
            r2 = r17
            goto L_0x004a
        L_0x0477:
            r17 = r5
            gnu.expr.ScopeExp r17 = r17.getContext()
            r0 = r17
            boolean r0 = r0 instanceof kawa.lang.PatternScope
            r17 = r0
            if (r17 == 0) goto L_0x04b6
            r17 = r2
            java.lang.StringBuilder r18 = new java.lang.StringBuilder
            r22 = r18
            r18 = r22
            r19 = r22
            r19.<init>()
            java.lang.String r19 = "reference to pattern variable "
            java.lang.StringBuilder r18 = r18.append(r19)
            r19 = r5
            java.lang.String r19 = r19.getName()
            java.lang.StringBuilder r18 = r18.append(r19)
            java.lang.String r19 = " outside syntax template"
            java.lang.StringBuilder r18 = r18.append(r19)
            java.lang.String r18 = r18.toString()
            gnu.expr.Expression r17 = r17.syntaxError(r18)
            r2 = r17
            goto L_0x004a
        L_0x04b6:
            gnu.expr.ReferenceExp r17 = new gnu.expr.ReferenceExp
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r10
            r20 = r5
            r18.<init>(r19, r20)
            r13 = r17
            r17 = r13
            r18 = r6
            r17.setContextDecl(r18)
            r17 = r13
            r18 = r2
            r17.setLine((gnu.expr.Compilation) r18)
            r17 = r4
            if (r17 == 0) goto L_0x04e4
            r17 = r12
            if (r17 == 0) goto L_0x04e4
            r17 = r13
            r18 = 8
            r17.setFlag(r18)
        L_0x04e4:
            r17 = r13
            r2 = r17
            goto L_0x004a
        L_0x04ea:
            r17 = r3
            r0 = r17
            boolean r0 = r0 instanceof gnu.expr.LangExp
            r17 = r0
            if (r17 == 0) goto L_0x0508
            r17 = r2
            r18 = r3
            gnu.expr.LangExp r18 = (gnu.expr.LangExp) r18
            java.lang.Object r18 = r18.getLangValue()
            r19 = r4
            gnu.expr.Expression r17 = r17.rewrite(r18, r19)
            r2 = r17
            goto L_0x004a
        L_0x0508:
            r17 = r3
            r0 = r17
            boolean r0 = r0 instanceof gnu.expr.Expression
            r17 = r0
            if (r17 == 0) goto L_0x051a
            r17 = r3
            gnu.expr.Expression r17 = (gnu.expr.Expression) r17
            r2 = r17
            goto L_0x004a
        L_0x051a:
            r17 = r3
            gnu.expr.Special r18 = gnu.expr.Special.abstractSpecial
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x052a
            gnu.expr.QuoteExp r17 = gnu.expr.QuoteExp.abstractExp
            r2 = r17
            goto L_0x004a
        L_0x052a:
            r17 = r3
            r18 = r2
            java.lang.Object r17 = kawa.lang.Quote.quote(r17, r18)
            r18 = r2
            gnu.expr.QuoteExp r17 = gnu.expr.QuoteExp.getInstance(r17, r18)
            r2 = r17
            goto L_0x004a
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.Translator.rewrite(java.lang.Object, boolean):gnu.expr.Expression");
    }

    public static void setLine(Expression expression, Object obj) {
        Expression exp = expression;
        Object location = obj;
        if (location instanceof SourceLocator) {
            exp.setLocation((SourceLocator) location);
        }
    }

    public static void setLine(Declaration declaration, Object obj) {
        Declaration decl = declaration;
        Object location = obj;
        if (location instanceof SourceLocator) {
            decl.setLocation((SourceLocator) location);
        }
    }

    public Object pushPositionOf(Object obj) {
        PairWithPosition saved;
        PairWithPosition pairWithPosition;
        Object pair = obj;
        if (pair instanceof SyntaxForm) {
            pair = ((SyntaxForm) pair).getDatum();
        }
        if (!(pair instanceof PairWithPosition)) {
            return null;
        }
        PairWithPosition ppair = (PairWithPosition) pair;
        if (this.positionPair != null && this.positionPair.getFileName() == getFileName() && this.positionPair.getLineNumber() == getLineNumber() && this.positionPair.getColumnNumber() == getColumnNumber()) {
            saved = this.positionPair;
        } else {
            new PairWithPosition(this, Special.eof, this.positionPair);
            saved = pairWithPosition;
        }
        setLine(pair);
        this.positionPair = ppair;
        return saved;
    }

    public void popPositionOf(Object obj) {
        Object saved = obj;
        if (saved != null) {
            setLine(saved);
            this.positionPair = (PairWithPosition) saved;
            if (this.positionPair.getCar() == Special.eof) {
                this.positionPair = (PairWithPosition) this.positionPair.getCdr();
            }
        }
    }

    public void setLineOf(Expression expression) {
        Expression exp = expression;
        if (!(exp instanceof QuoteExp)) {
            exp.setLocation(this);
        }
    }

    /* JADX INFO: finally extract failed */
    public Type exp2Type(Pair pair) {
        StringBuilder sb;
        Pair typeSpecPair = pair;
        Object saved = pushPositionOf(typeSpecPair);
        try {
            Expression texp = InlineCalls.inlineCalls(rewrite_car(typeSpecPair, false), this);
            if (texp instanceof ErrorExp) {
                popPositionOf(saved);
                return null;
            }
            Type type = getLanguage().getTypeFor(texp);
            if (type == null) {
                try {
                    Object t = texp.eval(this.env);
                    if (t instanceof Class) {
                        type = Type.make((Class) t);
                    } else if (t instanceof Type) {
                        type = (Type) t;
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                }
            }
            if (type == null) {
                if (texp instanceof ReferenceExp) {
                    new StringBuilder();
                    error('e', sb.append("unknown type name '").append(((ReferenceExp) texp).getName()).append('\'').toString());
                } else {
                    error('e', "invalid type spec (must be \"type\" or 'type or <type>)");
                }
                ClassType classType = Type.pointer_type;
                popPositionOf(saved);
                return classType;
            }
            Type type2 = type;
            popPositionOf(saved);
            return type2;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            popPositionOf(saved);
            throw th4;
        }
    }

    public Expression rewrite_with_position(Object obj, boolean z, PairWithPosition pairWithPosition) {
        Expression result;
        Object exp = obj;
        boolean function = z;
        PairWithPosition pair = pairWithPosition;
        Object saved = pushPositionOf(pair);
        if (exp == pair) {
            try {
                result = rewrite_pair(pair, function);
            } catch (Throwable th) {
                Throwable th2 = th;
                popPositionOf(saved);
                throw th2;
            }
        } else {
            result = rewrite(exp, function);
        }
        setLineOf(result);
        popPositionOf(saved);
        return result;
    }

    public static Object wrapSyntax(Object obj, SyntaxForm syntaxForm) {
        Object form = obj;
        SyntaxForm syntax = syntaxForm;
        if (syntax == null || (form instanceof Expression)) {
            return form;
        }
        return SyntaxForms.fromDatumIfNeeded(form, syntax);
    }

    public Object popForms(int i) {
        Object obj;
        Object obj2;
        int first = i;
        int last = this.formStack.size();
        if (last == first) {
            return Values.empty;
        }
        if (last == first + 1) {
            obj2 = this.formStack.elementAt(first);
        } else {
            new Values();
            Object obj3 = obj;
            for (int i2 = first; i2 < last; i2++) {
                obj3.writeObject(this.formStack.elementAt(i2));
            }
            obj2 = obj3;
        }
        this.formStack.setSize(first);
        return obj2;
    }

    public void scanForm(Object obj, ScopeExp scopeExp) {
        Object st = obj;
        ScopeExp defs = scopeExp;
        if (st instanceof SyntaxForm) {
            SyntaxForm sf = (SyntaxForm) st;
            ScopeExp save_scope = currentScope();
            try {
                setCurrentScope(sf.getScope());
                int first = this.formStack.size();
                scanForm(sf.getDatum(), defs);
                boolean add = this.formStack.add(wrapSyntax(popForms(first), sf));
                setCurrentScope(save_scope);
            } catch (Throwable th) {
                Throwable th2 = th;
                setCurrentScope(save_scope);
                throw th2;
            }
        } else {
            if (st instanceof Values) {
                if (st == Values.empty) {
                    st = QuoteExp.voidExp;
                } else {
                    Object[] vals = ((Values) st).getValues();
                    for (int i = 0; i < vals.length; i++) {
                        scanForm(vals[i], defs);
                    }
                    return;
                }
            }
            if (st instanceof Pair) {
                Pair st_pair = (Pair) st;
                Declaration saveContext = this.macroContext;
                Syntax syntax = null;
                ScopeExp savedScope = this.current_scope;
                Object savedPosition = pushPositionOf(st);
                if ((st instanceof SourceLocator) && defs.getLineNumber() < 0) {
                    defs.setLocation((SourceLocator) st);
                }
                try {
                    Object obj2 = st_pair.getCar();
                    if (obj2 instanceof SyntaxForm) {
                        SyntaxForm sf2 = (SyntaxForm) st_pair.getCar();
                        setCurrentScope(sf2.getScope());
                        obj2 = sf2.getDatum();
                    }
                    if (obj2 instanceof Pair) {
                        Pair pair = (Pair) obj2;
                        Pair p = pair;
                        if (pair.getCar() == LispLanguage.lookup_sym && (p.getCdr() instanceof Pair)) {
                            Pair pair2 = (Pair) p.getCdr();
                            Pair p2 = pair2;
                            if (pair2.getCdr() instanceof Pair) {
                                Expression part1 = rewrite(p2.getCar());
                                Expression part2 = rewrite(((Pair) p2.getCdr()).getCar());
                                Object value1 = part1.valueIfConstant();
                                Object value2 = part2.valueIfConstant();
                                if (!(value1 instanceof Class) || !(value2 instanceof Symbol)) {
                                    obj2 = namespaceResolve(part1, part2);
                                } else {
                                    try {
                                        obj2 = GetNamedPart.getNamedPart(value1, (Symbol) value2);
                                        if (obj2 instanceof Syntax) {
                                            syntax = (Syntax) obj2;
                                        }
                                    } catch (Throwable th3) {
                                        Throwable th4 = th3;
                                        obj2 = null;
                                    }
                                }
                            }
                        }
                    }
                    if ((obj2 instanceof Symbol) && !selfEvaluatingSymbol(obj2)) {
                        Expression func = rewrite(obj2, true);
                        if (func instanceof ReferenceExp) {
                            Declaration decl = ((ReferenceExp) func).getBinding();
                            if (decl != null) {
                                syntax = check_if_Syntax(decl);
                            } else {
                                Object obj3 = resolve(obj2, true);
                                if (obj3 instanceof Syntax) {
                                    syntax = (Syntax) obj3;
                                }
                            }
                        }
                    } else if (obj2 == begin.begin) {
                        syntax = (Syntax) obj2;
                    }
                    if (savedScope != this.current_scope) {
                        setCurrentScope(savedScope);
                    }
                    popPositionOf(savedPosition);
                    if (syntax != null) {
                        String save_filename = getFileName();
                        int save_line = getLineNumber();
                        int save_column = getColumnNumber();
                        try {
                            setLine((Object) st_pair);
                            syntax.scanForm(st_pair, defs, this);
                            this.macroContext = saveContext;
                            setLine(save_filename, save_line, save_column);
                            return;
                        } catch (Throwable th5) {
                            Throwable th6 = th5;
                            this.macroContext = saveContext;
                            setLine(save_filename, save_line, save_column);
                            throw th6;
                        }
                    }
                } catch (Throwable th7) {
                    Throwable th8 = th7;
                    if (savedScope != this.current_scope) {
                        setCurrentScope(savedScope);
                    }
                    popPositionOf(savedPosition);
                    throw th8;
                }
            }
            boolean add2 = this.formStack.add(st);
        }
    }

    /* JADX INFO: finally extract failed */
    public LList scanBody(Object obj, ScopeExp scopeExp, boolean z) {
        Object obj2;
        Object body = obj;
        ScopeExp defs = scopeExp;
        boolean makeList = z;
        LList list = makeList ? LList.Empty : null;
        LList lastPair = null;
        while (true) {
            if (body != LList.Empty) {
                if (!(body instanceof SyntaxForm)) {
                    if (!(body instanceof Pair)) {
                        boolean add = this.formStack.add(syntaxError("body is not a proper list"));
                        break;
                    }
                    Pair pair = (Pair) body;
                    int first = this.formStack.size();
                    scanForm(pair.getCar(), defs);
                    if (getState() == 2) {
                        if (pair.getCar() != this.pendingForm) {
                            pair = makePair(pair, this.pendingForm, pair.getCdr());
                        }
                        new Pair(begin.begin, pair);
                        this.pendingForm = obj2;
                        return LList.Empty;
                    }
                    int fsize = this.formStack.size();
                    if (makeList) {
                        for (int i = first; i < fsize; i++) {
                            LList npair = makePair(pair, this.formStack.elementAt(i), LList.Empty);
                            if (lastPair == null) {
                                list = npair;
                            } else {
                                lastPair.setCdrBackdoor(npair);
                            }
                            lastPair = npair;
                        }
                        this.formStack.setSize(first);
                    }
                    body = pair.getCdr();
                } else {
                    SyntaxForm sf = (SyntaxForm) body;
                    ScopeExp save_scope = this.current_scope;
                    try {
                        setCurrentScope(sf.getScope());
                        int first2 = this.formStack.size();
                        LList f = scanBody(sf.getDatum(), defs, makeList);
                        if (makeList) {
                            LList f2 = (LList) SyntaxForms.fromDatumIfNeeded(f, sf);
                            if (lastPair == null) {
                                LList lList = f2;
                                setCurrentScope(save_scope);
                                return lList;
                            }
                            lastPair.setCdrBackdoor(f2);
                            LList lList2 = list;
                            setCurrentScope(save_scope);
                            return lList2;
                        }
                        boolean add2 = this.formStack.add(wrapSyntax(popForms(first2), sf));
                        setCurrentScope(save_scope);
                        return null;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        setCurrentScope(save_scope);
                        throw th2;
                    }
                }
            } else {
                break;
            }
        }
        return list;
    }

    public static Pair makePair(Pair pair, Object obj, Object obj2) {
        Pair pair2;
        Pair pair3;
        Pair pair4 = pair;
        Object car = obj;
        Object cdr = obj2;
        if (pair4 instanceof PairWithPosition) {
            new PairWithPosition((PairWithPosition) pair4, car, cdr);
            return pair3;
        }
        new Pair(car, cdr);
        return pair2;
    }

    /* JADX INFO: finally extract failed */
    public Expression rewrite_body(Object obj) {
        LetExp letExp;
        Object exp = obj;
        Object saved = pushPositionOf(exp);
        new LetExp((Expression[]) null);
        LetExp defs = letExp;
        int first = this.formStack.size();
        defs.outer = this.current_scope;
        this.current_scope = defs;
        try {
            LList list = scanBody(exp, defs, true);
            if (list.isEmpty()) {
                boolean add = this.formStack.add(syntaxError("body with no expressions"));
            }
            int ndecls = defs.countNonDynamicDecls();
            if (ndecls != 0) {
                Expression[] inits = new Expression[ndecls];
                int i = ndecls;
                while (true) {
                    i--;
                    if (i < 0) {
                        break;
                    }
                    inits[i] = QuoteExp.undefined_exp;
                }
                defs.inits = inits;
            }
            rewriteBody(list);
            Expression body = makeBody(first, (ScopeExp) null);
            setLineOf(body);
            if (ndecls == 0) {
                Expression expression = body;
                pop(defs);
                popPositionOf(saved);
                return expression;
            }
            defs.body = body;
            setLineOf(defs);
            LetExp letExp2 = defs;
            pop(defs);
            popPositionOf(saved);
            return letExp2;
        } catch (Throwable th) {
            Throwable th2 = th;
            pop(defs);
            popPositionOf(saved);
            throw th2;
        }
    }

    private void rewriteBody(LList lList) {
        LList forms = lList;
        while (forms != LList.Empty) {
            Pair pair = (Pair) forms;
            Object saved = pushPositionOf(pair);
            try {
                rewriteInBody(pair.getCar());
                popPositionOf(saved);
                forms = (LList) pair.getCdr();
            } catch (Throwable th) {
                Throwable th2 = th;
                popPositionOf(saved);
                throw th2;
            }
        }
    }

    private Expression makeBody(int i, ScopeExp scopeExp) {
        Expression expression;
        int first = i;
        ScopeExp scope = scopeExp;
        int nforms = this.formStack.size() - first;
        if (nforms == 0) {
            return QuoteExp.voidExp;
        }
        if (nforms == 1) {
            return (Expression) this.formStack.pop();
        }
        Expression[] exps = new Expression[nforms];
        for (int i2 = 0; i2 < nforms; i2++) {
            exps[i2] = (Expression) this.formStack.elementAt(first + i2);
        }
        this.formStack.setSize(first);
        if (!(scope instanceof ModuleExp)) {
            return ((LispLanguage) getLanguage()).makeBody(exps);
        }
        new ApplyExp((Procedure) AppendValues.appendValues, exps);
        return expression;
    }

    public void noteAccess(Object obj, ScopeExp scopeExp) {
        Vector vector;
        Object name = obj;
        ScopeExp scope = scopeExp;
        if (this.notedAccess == null) {
            new Vector();
            this.notedAccess = vector;
        }
        this.notedAccess.addElement(name);
        this.notedAccess.addElement(scope);
    }

    public void processAccesses() {
        if (this.notedAccess != null) {
            int sz = this.notedAccess.size();
            ScopeExp saveScope = this.current_scope;
            for (int i = 0; i < sz; i += 2) {
                Object name = this.notedAccess.elementAt(i);
                ScopeExp scope = (ScopeExp) this.notedAccess.elementAt(i + 1);
                if (this.current_scope != scope) {
                    setCurrentScope(scope);
                }
                Declaration decl = this.lexical.lookup(name, -1);
                if (decl != null && !decl.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH)) {
                    decl.getContext().currentLambda().capture(decl);
                    decl.setCanRead(true);
                    decl.setSimple(false);
                    decl.setFlag(PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED);
                }
            }
            if (this.current_scope != saveScope) {
                setCurrentScope(saveScope);
            }
        }
    }

    public void finishModule(ModuleExp moduleExp) {
        String msg2;
        ModuleExp mexp = moduleExp;
        boolean moduleStatic = mexp.isStatic();
        Declaration firstDecl = mexp.firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl != null) {
                if (decl.getFlag(512)) {
                    String msg1 = "'";
                    if (decl.getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) {
                        msg2 = "' exported but never defined";
                    } else {
                        msg2 = decl.getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) ? "' declared static but never defined" : "' declared but never defined";
                    }
                    error('e', decl, msg1, msg2);
                }
                if (mexp.getFlag(16384) || (this.generateMain && !this.immediate)) {
                    if (!decl.getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) {
                        decl.setPrivate(true);
                    } else if (decl.isPrivate()) {
                        if (decl.getFlag(16777216)) {
                            error('e', decl, "'", "' is declared both private and exported");
                        }
                        decl.setPrivate(false);
                    }
                }
                if (moduleStatic) {
                    decl.setFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH);
                } else if ((mexp.getFlag(65536) && !decl.getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH)) || Compilation.moduleStatic < 0 || mexp.getFlag(131072)) {
                    decl.setFlag(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
                }
                firstDecl = decl.nextDecl();
            } else {
                return;
            }
        }
    }

    static void vectorReverse(Vector vector, int i, int i2) {
        Vector vec = vector;
        int start = i;
        int count = i2;
        int j = count / 2;
        int last = (start + count) - 1;
        for (int i3 = 0; i3 < j; i3++) {
            Object tmp = vec.elementAt(start + i3);
            vec.setElementAt(vec.elementAt(last - i3), start + i3);
            vec.setElementAt(tmp, last - i3);
        }
    }

    public void resolveModule(ModuleExp moduleExp) {
        int size;
        Expression expression;
        ModuleExp mexp = moduleExp;
        if (this.pendingImports == null) {
            size = 0;
        } else {
            size = this.pendingImports.size();
        }
        int numPending = size;
        int i = 0;
        while (i < numPending) {
            int i2 = i;
            int i3 = i + 1;
            ModuleInfo info = (ModuleInfo) this.pendingImports.elementAt(i2);
            int i4 = i3;
            int i5 = i3 + 1;
            ScopeExp defs = (ScopeExp) this.pendingImports.elementAt(i4);
            int i6 = i5;
            int i7 = i5 + 1;
            Expression posExp = (Expression) this.pendingImports.elementAt(i6);
            int i8 = i7;
            i = i7 + 1;
            Integer savedSize = (Integer) this.pendingImports.elementAt(i8);
            if (mexp == defs) {
                new ReferenceExp((Object) null);
                Expression savePos = expression;
                savePos.setLine((Compilation) this);
                setLine(posExp);
                int beforeSize = this.formStack.size();
                boolean importDefinitions = require.importDefinitions((String) null, info, (Procedure) null, this.formStack, defs, this);
                int desiredPosition = savedSize.intValue();
                if (savedSize.intValue() != beforeSize) {
                    int curSize = this.formStack.size();
                    vectorReverse(this.formStack, desiredPosition, beforeSize - desiredPosition);
                    vectorReverse(this.formStack, beforeSize, curSize - beforeSize);
                    vectorReverse(this.formStack, desiredPosition, curSize - desiredPosition);
                }
                setLine(savePos);
            }
        }
        this.pendingImports = null;
        processAccesses();
        setModule(mexp);
        Compilation save_comp = Compilation.setSaveCurrent(this);
        try {
            rewriteInBody(popForms(this.firstForm));
            mexp.body = makeBody(this.firstForm, mexp);
            if (!this.immediate) {
                this.lexical.pop((ScopeExp) mexp);
            }
            Compilation.restoreCurrent(save_comp);
        } catch (Throwable th) {
            Throwable th2 = th;
            Compilation.restoreCurrent(save_comp);
            throw th2;
        }
    }

    public Declaration makeRenamedAlias(Declaration declaration, ScopeExp scopeExp) {
        Declaration decl = declaration;
        ScopeExp templateScope = scopeExp;
        if (templateScope == null) {
            return decl;
        }
        return makeRenamedAlias(decl.getSymbol(), decl, templateScope);
    }

    public Declaration makeRenamedAlias(Object name, Declaration decl, ScopeExp templateScope) {
        Declaration declaration;
        ReferenceExp referenceExp;
        new Declaration(name);
        Declaration alias = declaration;
        alias.setAlias(true);
        alias.setPrivate(true);
        alias.context = templateScope;
        new ReferenceExp(decl);
        ReferenceExp ref = referenceExp;
        ref.setDontDereference(true);
        alias.noteValue(ref);
        return alias;
    }

    public void pushRenamedAlias(Declaration declaration) {
        Stack stack;
        Declaration alias = declaration;
        Declaration decl = getOriginalRef(alias).getBinding();
        ScopeExp templateScope = alias.context;
        decl.setSymbol((Object) null);
        Declaration old = templateScope.lookup(decl.getSymbol());
        if (old != null) {
            templateScope.remove(old);
        }
        templateScope.addDeclaration(alias);
        if (this.renamedAliasStack == null) {
            new Stack();
            this.renamedAliasStack = stack;
        }
        Object push = this.renamedAliasStack.push(old);
        Object push2 = this.renamedAliasStack.push(alias);
        Object push3 = this.renamedAliasStack.push(templateScope);
    }

    public void popRenamedAlias(int i) {
        int count = i;
        while (true) {
            count--;
            if (count >= 0) {
                ScopeExp templateScope = (ScopeExp) this.renamedAliasStack.pop();
                Declaration alias = (Declaration) this.renamedAliasStack.pop();
                getOriginalRef(alias).getBinding().setSymbol(alias.getSymbol());
                templateScope.remove(alias);
                Object old = this.renamedAliasStack.pop();
                if (old != null) {
                    templateScope.addDeclaration((Declaration) old);
                }
            } else {
                return;
            }
        }
    }

    public Declaration define(Object obj, SyntaxForm syntaxForm, ScopeExp scopeExp) {
        Object declName;
        Object obj2;
        Object name = obj;
        SyntaxForm nameSyntax = syntaxForm;
        ScopeExp defs = scopeExp;
        boolean aliasNeeded = (nameSyntax == null || nameSyntax.getScope() == currentScope()) ? false : true;
        if (aliasNeeded) {
            declName = obj2;
            new String(name.toString());
        } else {
            declName = name;
        }
        Declaration decl = defs.getDefine(declName, 'w', this);
        if (aliasNeeded) {
            nameSyntax.getScope().addDeclaration(makeRenamedAlias(name, decl, nameSyntax.getScope()));
        }
        push(decl);
        return decl;
    }
}
