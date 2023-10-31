package kawa.standard;

import gnu.expr.Keyword;
import gnu.expr.ScopeExp;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.text.Options;
import java.util.Stack;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class with_compile_options extends Syntax {
    public static final with_compile_options with_compile_options;

    public with_compile_options() {
    }

    static {
        with_compile_options with_compile_options2;
        new with_compile_options();
        with_compile_options = with_compile_options2;
        with_compile_options.setName("with-compile-options");
    }

    public void scanForm(Pair pair, ScopeExp scopeExp, Translator translator) {
        Stack stack;
        Object obj;
        Pair form = pair;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        new Stack();
        Stack stack2 = stack;
        Object rest = getOptions(form.getCdr(), stack2, this, tr);
        if (rest != LList.Empty) {
            if (rest == form.getCdr()) {
                LList scanBody = tr.scanBody(rest, defs, false);
                return;
            }
            new Pair(stack2, tr.scanBody(rest, defs, true));
            tr.currentOptions.popOptionValues(stack2);
            boolean add = tr.formStack.add(Translator.makePair(form, form.getCar(), obj));
        }
    }

    /* JADX INFO: finally extract failed */
    public static Object getOptions(Object obj, Stack stack, Syntax syntax, Translator translator) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        Object form = obj;
        Stack stack2 = stack;
        Syntax command = syntax;
        Translator tr = translator;
        boolean seenKey = false;
        Options options = tr.currentOptions;
        SyntaxForm syntax2 = null;
        while (true) {
            if (form instanceof SyntaxForm) {
                syntax2 = (SyntaxForm) form;
                form = syntax2.getDatum();
            } else if (!(form instanceof Pair)) {
                break;
            } else {
                Pair pair = (Pair) form;
                Object pair_car = Translator.stripSyntax(pair.getCar());
                if (!(pair_car instanceof Keyword)) {
                    break;
                }
                String key = ((Keyword) pair_car).getName();
                seenKey = true;
                Object savePos = tr.pushPositionOf(pair);
                try {
                    Object form2 = pair.getCdr();
                    while (form2 instanceof SyntaxForm) {
                        syntax2 = (SyntaxForm) form2;
                        form2 = syntax2.getDatum();
                    }
                    if (!(form2 instanceof Pair)) {
                        new StringBuilder();
                        tr.error('e', sb.append("keyword ").append(key).append(" not followed by value").toString());
                        Object form3 = LList.Empty;
                        tr.popPositionOf(savePos);
                        return form3;
                    }
                    Pair pair2 = (Pair) form2;
                    Object value = Translator.stripSyntax(pair2.getCar());
                    form = pair2.getCdr();
                    Object oldValue = options.getLocal(key);
                    if (options.getInfo(key) == null) {
                        new StringBuilder();
                        tr.error('w', sb3.append("unknown compile option: ").append(key).toString());
                        tr.popPositionOf(savePos);
                    } else {
                        if (value instanceof FString) {
                            value = value.toString();
                        } else if (!(value instanceof Boolean)) {
                            if (!(value instanceof Number)) {
                                value = null;
                                new StringBuilder();
                                tr.error('e', sb2.append("invalid literal value for key ").append(key).toString());
                            }
                        }
                        options.set(key, value, tr.getMessages());
                        if (stack2 != null) {
                            Object push = stack2.push(key);
                            Object push2 = stack2.push(oldValue);
                            Object push3 = stack2.push(value);
                        }
                        tr.popPositionOf(savePos);
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    tr.popPositionOf(savePos);
                    throw th2;
                }
            }
        }
        if (!seenKey) {
            new StringBuilder();
            tr.error('e', sb4.append("no option keyword in ").append(command.getName()).toString());
        }
        return Translator.wrapSyntax(form, syntax2);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x006f A[SYNTHETIC, Splitter:B:14:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0044 A[Catch:{ all -> 0x008a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression rewriteForm(gnu.lists.Pair r19, kawa.lang.Translator r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r11 = r1
            java.lang.Object r11 = r11.getCdr()
            r5 = r11
            r11 = r5
            boolean r11 = r11 instanceof gnu.lists.Pair
            if (r11 == 0) goto L_0x0059
            r11 = r5
            gnu.lists.Pair r11 = (gnu.lists.Pair) r11
            r17 = r11
            r11 = r17
            r12 = r17
            r6 = r12
            java.lang.Object r11 = r11.getCar()
            boolean r11 = r11 instanceof java.util.Stack
            if (r11 == 0) goto L_0x0059
            r11 = r6
            java.lang.Object r11 = r11.getCar()
            java.util.Stack r11 = (java.util.Stack) r11
            r4 = r11
            r11 = r6
            java.lang.Object r11 = r11.getCdr()
            r3 = r11
            r11 = r2
            gnu.text.Options r11 = r11.currentOptions
            r12 = r4
            r11.pushOptionValues(r12)
        L_0x0038:
            r11 = r2
            r12 = r3
            gnu.expr.Expression r11 = r11.rewrite_body(r12)     // Catch:{ all -> 0x008a }
            r7 = r11
            r11 = r7
            boolean r11 = r11 instanceof gnu.expr.BeginExp     // Catch:{ all -> 0x008a }
            if (r11 == 0) goto L_0x006f
            r11 = r7
            gnu.expr.BeginExp r11 = (gnu.expr.BeginExp) r11     // Catch:{ all -> 0x008a }
            r8 = r11
        L_0x0048:
            r11 = r8
            r12 = r4
            r11.setCompileOptions(r12)     // Catch:{ all -> 0x008a }
            r11 = r8
            r9 = r11
            r11 = r2
            gnu.text.Options r11 = r11.currentOptions
            r12 = r4
            r11.popOptionValues(r12)
            r11 = r9
            r0 = r11
            return r0
        L_0x0059:
            java.util.Stack r11 = new java.util.Stack
            r17 = r11
            r11 = r17
            r12 = r17
            r12.<init>()
            r4 = r11
            r11 = r5
            r12 = r4
            r13 = r0
            r14 = r2
            java.lang.Object r11 = getOptions(r11, r12, r13, r14)
            r3 = r11
            goto L_0x0038
        L_0x006f:
            gnu.expr.BeginExp r11 = new gnu.expr.BeginExp     // Catch:{ all -> 0x008a }
            r17 = r11
            r11 = r17
            r12 = r17
            r13 = 1
            gnu.expr.Expression[] r13 = new gnu.expr.Expression[r13]     // Catch:{ all -> 0x008a }
            r17 = r13
            r13 = r17
            r14 = r17
            r15 = 0
            r16 = r7
            r14[r15] = r16     // Catch:{ all -> 0x008a }
            r12.<init>(r13)     // Catch:{ all -> 0x008a }
            r8 = r11
            goto L_0x0048
        L_0x008a:
            r11 = move-exception
            r10 = r11
            r11 = r2
            gnu.text.Options r11 = r11.currentOptions
            r12 = r4
            r11.popOptionValues(r12)
            r11 = r10
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.with_compile_options.rewriteForm(gnu.lists.Pair, kawa.lang.Translator):gnu.expr.Expression");
    }
}
