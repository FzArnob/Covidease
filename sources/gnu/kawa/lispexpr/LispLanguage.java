package gnu.kawa.lispexpr;

import gnu.bytecode.Field;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.NameLookup;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.InPort;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public abstract class LispLanguage extends Language {
    public static final Symbol bracket_apply_sym = Namespace.EmptyNamespace.getSymbol("$bracket-apply$");
    public static final Symbol bracket_list_sym = Namespace.EmptyNamespace.getSymbol("$bracket-list$");
    public static StaticFieldLocation getNamedPartLocation = null;
    public static final Symbol lookup_sym = Namespace.EmptyNamespace.getSymbol("$lookup$");
    public static final String quasiquote_sym = "quasiquote";
    public static final String quote_sym = "quote";
    public static final String unquote_sym = "unquote";
    public static final String unquotesplicing_sym = "unquote-splicing";
    public ReadTable defaultReadTable = createReadTable();

    public abstract ReadTable createReadTable();

    public LispLanguage() {
    }

    static {
        StaticFieldLocation staticFieldLocation;
        new StaticFieldLocation("gnu.kawa.functions.GetNamedPart", "getNamedPart");
        getNamedPartLocation = staticFieldLocation;
        getNamedPartLocation.setProcedure();
    }

    public Lexer getLexer(InPort inp, SourceMessages messages) {
        Lexer lexer;
        new LispReader(inp, messages);
        return lexer;
    }

    public Compilation getCompilation(Lexer lexer, SourceMessages messages, NameLookup lexical) {
        Compilation compilation;
        Lexer lexer2 = lexer;
        new Translator(this, messages, lexical);
        return compilation;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0065, code lost:
        if (r3.getMessages().seenErrors() == false) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0067, code lost:
        r10 = r7.peek();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006e, code lost:
        if (r10 < 0) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0073, code lost:
        if (r10 == 13) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0078, code lost:
        if (r10 != 10) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r7.skip();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean parse(gnu.expr.Compilation r17, int r18) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r12 = r1
            kawa.lang.Translator r12 = (kawa.lang.Translator) r12
            r3 = r12
            r12 = r3
            gnu.text.Lexer r12 = r12.lexer
            r4 = r12
            r12 = r3
            gnu.expr.ModuleExp r12 = r12.mainLambda
            r5 = r12
            gnu.mapping.Values r12 = new gnu.mapping.Values
            r15 = r12
            r12 = r15
            r13 = r15
            r13.<init>()
            r6 = r12
            r12 = r4
            gnu.kawa.lispexpr.LispReader r12 = (gnu.kawa.lispexpr.LispReader) r12
            r7 = r12
            r12 = r3
            gnu.expr.Compilation r12 = gnu.expr.Compilation.setSaveCurrent(r12)
            r8 = r12
            r12 = r3
            java.lang.Object r12 = r12.pendingForm     // Catch:{ all -> 0x00c5 }
            if (r12 == 0) goto L_0x0036
            r12 = r3
            r13 = r3
            java.lang.Object r13 = r13.pendingForm     // Catch:{ all -> 0x00c5 }
            r14 = r5
            r12.scanForm(r13, r14)     // Catch:{ all -> 0x00c5 }
            r12 = r3
            r13 = 0
            r12.pendingForm = r13     // Catch:{ all -> 0x00c5 }
        L_0x0036:
            r12 = r7
            java.lang.Object r12 = r12.readCommand()     // Catch:{ all -> 0x00c5 }
            r9 = r12
            r12 = r9
            java.lang.Object r13 = gnu.lists.Sequence.eofValue     // Catch:{ all -> 0x00c5 }
            if (r12 != r13) goto L_0x0050
            r12 = r2
            r13 = 4
            r12 = r12 & 4
            if (r12 == 0) goto L_0x007a
            r12 = 0
            r10 = r12
            r12 = r8
            gnu.expr.Compilation.restoreCurrent(r12)
            r12 = r10
            r0 = r12
        L_0x004f:
            return r0
        L_0x0050:
            r12 = r3
            r13 = r9
            r14 = r5
            r12.scanForm(r13, r14)     // Catch:{ all -> 0x00c5 }
            r12 = r2
            r13 = 4
            r12 = r12 & 4
            if (r12 == 0) goto L_0x00ab
            r12 = r3
            gnu.text.SourceMessages r12 = r12.getMessages()     // Catch:{ all -> 0x00c5 }
            boolean r12 = r12.seenErrors()     // Catch:{ all -> 0x00c5 }
            if (r12 == 0) goto L_0x007a
        L_0x0067:
            r12 = r7
            int r12 = r12.peek()     // Catch:{ all -> 0x00c5 }
            r10 = r12
            r12 = r10
            if (r12 < 0) goto L_0x007a
            r12 = r10
            r13 = 13
            if (r12 == r13) goto L_0x007a
            r12 = r10
            r13 = 10
            if (r12 != r13) goto L_0x00a6
        L_0x007a:
            r12 = r4
            int r12 = r12.peek()     // Catch:{ all -> 0x00c5 }
            r13 = 41
            if (r12 != r13) goto L_0x008a
            r12 = r4
            java.lang.String r13 = "An unexpected close paren was read."
            r12.fatal(r13)     // Catch:{ all -> 0x00c5 }
        L_0x008a:
            r12 = r3
            r13 = r5
            r12.finishModule(r13)     // Catch:{ all -> 0x00c5 }
            r12 = r2
            r13 = 8
            r12 = r12 & 8
            if (r12 != 0) goto L_0x009a
            r12 = r3
            r13 = 0
            r12.firstForm = r13     // Catch:{ all -> 0x00c5 }
        L_0x009a:
            r12 = r3
            r13 = 4
            r12.setState(r13)     // Catch:{ all -> 0x00c5 }
            r12 = r8
            gnu.expr.Compilation.restoreCurrent(r12)
            r12 = 1
            r0 = r12
            goto L_0x004f
        L_0x00a6:
            r12 = r7
            r12.skip()     // Catch:{ all -> 0x00c5 }
            goto L_0x0067
        L_0x00ab:
            r12 = r2
            r13 = 8
            r12 = r12 & 8
            if (r12 == 0) goto L_0x00c3
            r12 = r3
            int r12 = r12.getState()     // Catch:{ all -> 0x00c5 }
            r13 = 2
            if (r12 < r13) goto L_0x00c3
            r12 = 1
            r10 = r12
            r12 = r8
            gnu.expr.Compilation.restoreCurrent(r12)
            r12 = r10
            r0 = r12
            goto L_0x004f
        L_0x00c3:
            goto L_0x0036
        L_0x00c5:
            r12 = move-exception
            r11 = r12
            r12 = r8
            gnu.expr.Compilation.restoreCurrent(r12)
            r12 = r11
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispLanguage.parse(gnu.expr.Compilation, int):boolean");
    }

    public void resolve(Compilation comp) {
        Translator tr = (Translator) comp;
        tr.resolveModule(tr.getModule());
    }

    public Declaration declFromField(ModuleExp mod, Object obj, Field field) {
        Object fvalue = obj;
        Field fld = field;
        Declaration fdecl = super.declFromField(mod, fvalue, fld);
        if (((fld.getModifiers() & 16) != 0) && (fvalue instanceof Syntax)) {
            fdecl.setSyntax();
        }
        return fdecl;
    }

    /* access modifiers changed from: protected */
    public void defSntxStFld(String name, String cname, String fname) {
        StaticFieldLocation.define(this.environ, this.environ.getSymbol(name), hasSeparateFunctionNamespace() ? EnvironmentKey.FUNCTION : null, cname, fname).setSyntax();
    }

    /* access modifiers changed from: protected */
    public void defSntxStFld(String str, String cname) {
        String name = str;
        defSntxStFld(name, cname, Compilation.mangleNameIfNeeded(name));
    }

    public Expression makeBody(Expression[] exps) {
        Expression expression;
        new BeginExp(exps);
        return expression;
    }

    public Expression makeApply(Expression func, Expression[] args) {
        Expression expression;
        new ApplyExp(func, args);
        return expression;
    }

    public boolean selfEvaluatingSymbol(Object obj) {
        return obj instanceof Keyword;
    }

    public static Symbol langSymbolToSymbol(Object sym) {
        return ((LispLanguage) Language.getDefaultLanguage()).fromLangSymbol(sym);
    }

    /* access modifiers changed from: protected */
    public Symbol fromLangSymbol(Object obj) {
        Object sym = obj;
        if (sym instanceof String) {
            return getSymbol((String) sym);
        }
        return (Symbol) sym;
    }

    public Expression checkDefaultBinding(Symbol symbol, Translator translator) {
        Symbol symbol2 = symbol;
        Translator translator2 = translator;
        return null;
    }
}
