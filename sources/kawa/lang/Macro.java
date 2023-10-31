package kawa.lang;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Macro extends Syntax implements Printable, Externalizable {
    private ScopeExp capturedScope;
    public Object expander;
    private boolean hygienic = true;
    Object instance;

    public ScopeExp getCapturedScope() {
        if (this.capturedScope == null) {
            if (this.instance instanceof ModuleExp) {
                this.capturedScope = (ModuleExp) this.instance;
            } else if (this.instance != null) {
                this.capturedScope = ModuleInfo.findFromInstance(this.instance).getModuleExp();
            }
        }
        return this.capturedScope;
    }

    public void setCapturedScope(ScopeExp scope) {
        ScopeExp scopeExp = scope;
        this.capturedScope = scopeExp;
    }

    public static Macro make(Declaration declaration) {
        Macro macro;
        Declaration decl = declaration;
        new Macro(decl.getSymbol());
        Macro mac = macro;
        decl.setSyntax();
        mac.capturedScope = decl.context;
        return mac;
    }

    public static Macro makeNonHygienic(Object name, Procedure expander2) {
        Macro macro;
        new Macro(name, expander2);
        Macro mac = macro;
        mac.hygienic = false;
        return mac;
    }

    public static Macro makeNonHygienic(Object name, Procedure expander2, Object instance2) {
        Macro macro;
        new Macro(name, expander2);
        Macro mac = macro;
        mac.hygienic = false;
        mac.instance = instance2;
        return mac;
    }

    public static Macro make(Object name, Procedure expander2) {
        Macro mac;
        new Macro(name, expander2);
        return mac;
    }

    public static Macro make(Object name, Procedure expander2, Object instance2) {
        Macro macro;
        new Macro(name, expander2);
        Macro mac = macro;
        mac.instance = instance2;
        return mac;
    }

    public final boolean isHygienic() {
        return this.hygienic;
    }

    public final void setHygienic(boolean hygienic2) {
        boolean z = hygienic2;
        this.hygienic = z;
    }

    public Macro() {
    }

    public Macro(Macro macro) {
        Macro old = macro;
        this.name = old.name;
        this.expander = old.expander;
        this.hygienic = old.hygienic;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Macro(Object name, Procedure expander2) {
        super(name);
        Object obj;
        new QuoteExp(expander2);
        this.expander = obj;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Macro(Object name) {
        super(name);
    }

    public Expression rewriteForm(Pair form, Translator translator) {
        Translator tr = translator;
        return tr.rewrite(expand(form, tr));
    }

    public Expression rewriteForm(Object form, Translator translator) {
        Translator tr = translator;
        return tr.rewrite(expand(form, tr));
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("#<macro ").append(getName()).append('>').toString();
    }

    public void print(Consumer consumer) {
        Consumer out = consumer;
        out.write("#<macro ");
        out.write(getName());
        out.write(62);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object expand(java.lang.Object r17, kawa.lang.Translator r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r10 = r0
            java.lang.Object r10 = r10.expander     // Catch:{ Throwable -> 0x0080 }
            r4 = r10
            r10 = r4
            boolean r10 = r10 instanceof gnu.mapping.Procedure     // Catch:{ Throwable -> 0x0080 }
            if (r10 == 0) goto L_0x004c
            r10 = r4
            boolean r10 = r10 instanceof gnu.expr.Expression     // Catch:{ Throwable -> 0x0080 }
            if (r10 != 0) goto L_0x004c
            r10 = r4
            gnu.mapping.Procedure r10 = (gnu.mapping.Procedure) r10     // Catch:{ Throwable -> 0x0080 }
            r3 = r10
        L_0x0018:
            r10 = r0
            boolean r10 = r10.hygienic     // Catch:{ Throwable -> 0x0080 }
            if (r10 != 0) goto L_0x010e
            r10 = r1
            r11 = r2
            java.lang.Object r10 = kawa.lang.Quote.quote(r10, r11)     // Catch:{ Throwable -> 0x0080 }
            r1 = r10
            r10 = r1
            int r10 = kawa.lang.Translator.listLength(r10)     // Catch:{ Throwable -> 0x0080 }
            r6 = r10
            r10 = r6
            if (r10 > 0) goto L_0x00b1
            r10 = r2
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0080 }
            r15 = r11
            r11 = r15
            r12 = r15
            r12.<init>()     // Catch:{ Throwable -> 0x0080 }
            java.lang.String r12 = "invalid macro argument list to "
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ Throwable -> 0x0080 }
            r12 = r0
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ Throwable -> 0x0080 }
            java.lang.String r11 = r11.toString()     // Catch:{ Throwable -> 0x0080 }
            gnu.expr.Expression r10 = r10.syntaxError(r11)     // Catch:{ Throwable -> 0x0080 }
            r0 = r10
        L_0x004b:
            return r0
        L_0x004c:
            r10 = r4
            boolean r10 = r10 instanceof gnu.expr.Expression     // Catch:{ Throwable -> 0x0080 }
            if (r10 != 0) goto L_0x0068
            r10 = r2
            kawa.lang.Macro r10 = r10.currentMacroDefinition     // Catch:{ Throwable -> 0x0080 }
            r5 = r10
            r10 = r2
            r11 = r0
            r10.currentMacroDefinition = r11     // Catch:{ Throwable -> 0x0080 }
            r10 = r2
            r11 = r4
            gnu.expr.Expression r10 = r10.rewrite(r11)     // Catch:{ all -> 0x0078 }
            r4 = r10
            r10 = r0
            r11 = r4
            r10.expander = r11     // Catch:{ all -> 0x0078 }
            r10 = r2
            r11 = r5
            r10.currentMacroDefinition = r11     // Catch:{ Throwable -> 0x0080 }
        L_0x0068:
            r10 = r4
            gnu.expr.Expression r10 = (gnu.expr.Expression) r10     // Catch:{ Throwable -> 0x0080 }
            r11 = r2
            gnu.mapping.Environment r11 = r11.getGlobalEnvironment()     // Catch:{ Throwable -> 0x0080 }
            java.lang.Object r10 = r10.eval((gnu.mapping.Environment) r11)     // Catch:{ Throwable -> 0x0080 }
            gnu.mapping.Procedure r10 = (gnu.mapping.Procedure) r10     // Catch:{ Throwable -> 0x0080 }
            r3 = r10
            goto L_0x0018
        L_0x0078:
            r10 = move-exception
            r6 = r10
            r10 = r2
            r11 = r5
            r10.currentMacroDefinition = r11     // Catch:{ Throwable -> 0x0080 }
            r10 = r6
            throw r10     // Catch:{ Throwable -> 0x0080 }
        L_0x0080:
            r10 = move-exception
            r3 = r10
            r10 = r2
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r15 = r11
            r11 = r15
            r12 = r15
            r12.<init>()
            java.lang.String r12 = "evaluating syntax transformer '"
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r0
            java.lang.String r12 = r12.getName()
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = "' threw "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r3
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            gnu.expr.Expression r10 = r10.syntaxError(r11)
            r0 = r10
            goto L_0x004b
        L_0x00b1:
            r10 = r6
            r11 = 1
            int r10 = r10 + -1
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ Throwable -> 0x0080 }
            r7 = r10
            r10 = 0
            r8 = r10
        L_0x00ba:
            r10 = r8
            r11 = r6
            if (r10 >= r11) goto L_0x00da
            r10 = r1
            gnu.lists.Pair r10 = (gnu.lists.Pair) r10     // Catch:{ Throwable -> 0x0080 }
            r9 = r10
            r10 = r8
            if (r10 <= 0) goto L_0x00d1
            r10 = r7
            r11 = r8
            r12 = 1
            int r11 = r11 + -1
            r12 = r9
            java.lang.Object r12 = r12.getCar()     // Catch:{ Throwable -> 0x0080 }
            r10[r11] = r12     // Catch:{ Throwable -> 0x0080 }
        L_0x00d1:
            r10 = r9
            java.lang.Object r10 = r10.getCdr()     // Catch:{ Throwable -> 0x0080 }
            r1 = r10
            int r8 = r8 + 1
            goto L_0x00ba
        L_0x00da:
            r10 = r3
            r11 = r7
            java.lang.Object r10 = r10.applyN(r11)     // Catch:{ Throwable -> 0x0080 }
            r5 = r10
        L_0x00e1:
            r10 = r1
            boolean r10 = r10 instanceof gnu.lists.PairWithPosition     // Catch:{ Throwable -> 0x0080 }
            if (r10 == 0) goto L_0x010a
            r10 = r5
            boolean r10 = r10 instanceof gnu.lists.Pair     // Catch:{ Throwable -> 0x0080 }
            if (r10 == 0) goto L_0x010a
            r10 = r5
            boolean r10 = r10 instanceof gnu.lists.PairWithPosition     // Catch:{ Throwable -> 0x0080 }
            if (r10 != 0) goto L_0x010a
            r10 = r5
            gnu.lists.Pair r10 = (gnu.lists.Pair) r10     // Catch:{ Throwable -> 0x0080 }
            r6 = r10
            gnu.lists.PairWithPosition r10 = new gnu.lists.PairWithPosition     // Catch:{ Throwable -> 0x0080 }
            r15 = r10
            r10 = r15
            r11 = r15
            r12 = r1
            gnu.lists.PairWithPosition r12 = (gnu.lists.PairWithPosition) r12     // Catch:{ Throwable -> 0x0080 }
            r13 = r6
            java.lang.Object r13 = r13.getCar()     // Catch:{ Throwable -> 0x0080 }
            r14 = r6
            java.lang.Object r14 = r14.getCdr()     // Catch:{ Throwable -> 0x0080 }
            r11.<init>(r12, r13, r14)     // Catch:{ Throwable -> 0x0080 }
            r5 = r10
        L_0x010a:
            r10 = r5
            r0 = r10
            goto L_0x004b
        L_0x010e:
            r10 = r3
            r11 = r1
            java.lang.Object r10 = r10.apply1(r11)     // Catch:{ Throwable -> 0x0080 }
            r5 = r10
            goto L_0x00e1
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.Macro.expand(java.lang.Object, kawa.lang.Translator):java.lang.Object");
    }

    public void scanForm(Pair pair, ScopeExp scopeExp, Translator translator) {
        Pair st = pair;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        String save_filename = tr.getFileName();
        int save_line = tr.getLineNumber();
        int save_column = tr.getColumnNumber();
        Syntax saveSyntax = tr.currentSyntax;
        try {
            tr.setLine((Object) st);
            tr.currentSyntax = this;
            tr.scanForm(expand(st, tr), defs);
            tr.setLine(save_filename, save_line, save_column);
            tr.currentSyntax = saveSyntax;
        } catch (Throwable th) {
            Throwable th2 = th;
            tr.setLine(save_filename, save_line, save_column);
            tr.currentSyntax = saveSyntax;
            throw th2;
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeObject(getName());
        out.writeObject(((QuoteExp) this.expander).getValue());
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        Object obj;
        ObjectInput in = objectInput;
        setName((String) in.readObject());
        new QuoteExp(in.readObject());
        this.expander = obj;
    }
}
