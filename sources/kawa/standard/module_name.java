package kawa.standard;

import kawa.lang.Syntax;

public class module_name extends Syntax {
    public static final module_name module_name;

    public module_name() {
    }

    static {
        module_name module_name2;
        new module_name();
        module_name = module_name2;
        module_name.setName("module-name");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0179  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void scanForm(gnu.lists.Pair r24, gnu.expr.ScopeExp r25, kawa.lang.Translator r26) {
        /*
            r23 = this;
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r17 = r3
            java.lang.Object r17 = r17.getCdr()
            r6 = r17
            r17 = 0
            r7 = r17
        L_0x0014:
            r17 = r6
            r0 = r17
            boolean r0 = r0 instanceof kawa.lang.SyntaxForm
            r17 = r0
            if (r17 == 0) goto L_0x002d
            r17 = r6
            kawa.lang.SyntaxForm r17 = (kawa.lang.SyntaxForm) r17
            r7 = r17
            r17 = r7
            java.lang.Object r17 = r17.getDatum()
            r6 = r17
            goto L_0x0014
        L_0x002d:
            r17 = r6
            r0 = r17
            boolean r0 = r0 instanceof gnu.lists.Pair
            r17 = r0
            if (r17 == 0) goto L_0x005a
            r17 = r6
            gnu.lists.Pair r17 = (gnu.lists.Pair) r17
            java.lang.Object r17 = r17.getCar()
        L_0x003f:
            r8 = r17
        L_0x0041:
            r17 = r8
            r0 = r17
            boolean r0 = r0 instanceof kawa.lang.SyntaxForm
            r17 = r0
            if (r17 == 0) goto L_0x005d
            r17 = r8
            kawa.lang.SyntaxForm r17 = (kawa.lang.SyntaxForm) r17
            r7 = r17
            r17 = r7
            java.lang.Object r17 = r17.getDatum()
            r8 = r17
            goto L_0x0041
        L_0x005a:
            r17 = 0
            goto L_0x003f
        L_0x005d:
            r17 = 0
            r9 = r17
            r17 = 0
            r11 = r17
            r17 = 0
            r12 = r17
            r17 = r8
            r0 = r17
            boolean r0 = r0 instanceof gnu.lists.Pair
            r17 = r0
            if (r17 == 0) goto L_0x00ed
            r17 = r8
            gnu.lists.Pair r17 = (gnu.lists.Pair) r17
            r22 = r17
            r17 = r22
            r18 = r22
            r10 = r18
            java.lang.Object r17 = r17.getCar()
            java.lang.String r18 = "quote"
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x00ed
            r17 = r10
            java.lang.Object r17 = r17.getCdr()
            r8 = r17
            r17 = r8
            r0 = r17
            boolean r0 = r0 instanceof gnu.lists.Pair
            r17 = r0
            if (r17 == 0) goto L_0x00c4
            r17 = r8
            gnu.lists.Pair r17 = (gnu.lists.Pair) r17
            r22 = r17
            r17 = r22
            r18 = r22
            r10 = r18
            java.lang.Object r17 = r17.getCdr()
            gnu.lists.LList r18 = gnu.lists.LList.Empty
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x00c4
            r17 = r10
            java.lang.Object r17 = r17.getCar()
            r0 = r17
            boolean r0 = r0 instanceof java.lang.String
            r17 = r0
            if (r17 != 0) goto L_0x00e2
        L_0x00c4:
            java.lang.String r17 = "invalid quoted symbol for 'module-name'"
            r11 = r17
        L_0x00c9:
            r17 = r11
            if (r17 == 0) goto L_0x0179
            r17 = r5
            r0 = r17
            java.util.Stack r0 = r0.formStack
            r17 = r0
            r18 = r5
            r19 = r11
            gnu.expr.Expression r18 = r18.syntaxError(r19)
            boolean r17 = r17.add(r18)
        L_0x00e1:
            return
        L_0x00e2:
            r17 = r10
            java.lang.Object r17 = r17.getCar()
            java.lang.String r17 = (java.lang.String) r17
            r9 = r17
            goto L_0x00c9
        L_0x00ed:
            r17 = r8
            r0 = r17
            boolean r0 = r0 instanceof gnu.lists.FString
            r17 = r0
            if (r17 != 0) goto L_0x0101
            r17 = r8
            r0 = r17
            boolean r0 = r0 instanceof java.lang.String
            r17 = r0
            if (r17 == 0) goto L_0x010a
        L_0x0101:
            r17 = r8
            java.lang.String r17 = r17.toString()
            r9 = r17
            goto L_0x00c9
        L_0x010a:
            r17 = r8
            r0 = r17
            boolean r0 = r0 instanceof gnu.mapping.Symbol
            r17 = r0
            if (r17 == 0) goto L_0x0172
            r17 = r8
            java.lang.String r17 = r17.toString()
            r9 = r17
            r17 = r9
            int r17 = r17.length()
            r13 = r17
            r17 = r13
            r18 = 2
            r0 = r17
            r1 = r18
            if (r0 <= r1) goto L_0x0162
            r17 = r9
            r18 = 0
            char r17 = r17.charAt(r18)
            r18 = 60
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0162
            r17 = r9
            r18 = r13
            r19 = 1
            int r18 = r18 + -1
            char r17 = r17.charAt(r18)
            r18 = 62
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0162
            r17 = r9
            r18 = 1
            r19 = r13
            r20 = 1
            int r19 = r19 + -1
            java.lang.String r17 = r17.substring(r18, r19)
            r9 = r17
        L_0x0162:
            r17 = r5
            r18 = r8
            r19 = r7
            r20 = r4
            gnu.expr.Declaration r17 = r17.define(r18, r19, r20)
            r12 = r17
            goto L_0x00c9
        L_0x0172:
            java.lang.String r17 = "un-implemented expression in module-name"
            r11 = r17
            goto L_0x00c9
        L_0x0179:
            r17 = r9
            r18 = 46
            int r17 = r17.lastIndexOf(r18)
            r13 = r17
            r17 = r9
            r14 = r17
            r17 = r13
            if (r17 < 0) goto L_0x0228
            r17 = r5
            r18 = r9
            r19 = 0
            r20 = r13
            r21 = 1
            int r20 = r20 + 1
            java.lang.String r18 = r18.substring(r19, r20)
            r0 = r18
            r1 = r17
            r1.classPrefix = r0
        L_0x01a1:
            r17 = r5
            gnu.expr.ModuleExp r17 = r17.getModule()
            r15 = r17
            r17 = r5
            r0 = r17
            gnu.bytecode.ClassType r0 = r0.mainClass
            r17 = r0
            if (r17 != 0) goto L_0x0274
            r17 = r5
            gnu.bytecode.ClassType r18 = new gnu.bytecode.ClassType
            r22 = r18
            r18 = r22
            r19 = r22
            r20 = r14
            r19.<init>(r20)
            r0 = r18
            r1 = r17
            r1.mainClass = r0
        L_0x01c8:
            r17 = r15
            r18 = r5
            r0 = r18
            gnu.bytecode.ClassType r0 = r0.mainClass
            r18 = r0
            r17.setType(r18)
            r17 = r15
            r18 = r9
            r17.setName(r18)
            r17 = r12
            if (r17 == 0) goto L_0x0221
            r17 = r12
            gnu.expr.QuoteExp r18 = new gnu.expr.QuoteExp
            r22 = r18
            r18 = r22
            r19 = r22
            r20 = r5
            r0 = r20
            gnu.bytecode.ClassType r0 = r0.mainClass
            r20 = r0
            gnu.bytecode.ClassType r21 = gnu.expr.Compilation.typeClass
            r19.<init>(r20, r21)
            r17.noteValue(r18)
            r17 = r12
            r18 = 16793600(0x1004000, double:8.297141E-317)
            r17.setFlag(r18)
            r17 = r15
            r0 = r17
            gnu.expr.ScopeExp r0 = r0.outer
            r17 = r0
            if (r17 != 0) goto L_0x0213
            r17 = r12
            r18 = 2048(0x800, double:1.0118E-320)
            r17.setFlag(r18)
        L_0x0213:
            r17 = r12
            r18 = 1
            r17.setPrivate(r18)
            r17 = r12
            gnu.bytecode.ClassType r18 = gnu.expr.Compilation.typeClass
            r17.setType(r18)
        L_0x0221:
            r17 = r5
            r17.mustCompileHere()
            goto L_0x00e1
        L_0x0228:
            java.lang.StringBuilder r17 = new java.lang.StringBuilder
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r18 = r5
            r0 = r18
            java.lang.String r0 = r0.classPrefix
            r18 = r0
            java.lang.StringBuilder r17 = r17.append(r18)
            r18 = r9
            java.lang.StringBuilder r17 = r17.append(r18)
            java.lang.String r17 = r17.toString()
            r9 = r17
            java.lang.StringBuilder r17 = new java.lang.StringBuilder
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r18 = r5
            r0 = r18
            java.lang.String r0 = r0.classPrefix
            r18 = r0
            java.lang.StringBuilder r17 = r17.append(r18)
            r18 = r9
            java.lang.String r18 = gnu.expr.Compilation.mangleName(r18)
            java.lang.StringBuilder r17 = r17.append(r18)
            java.lang.String r17 = r17.toString()
            r14 = r17
            goto L_0x01a1
        L_0x0274:
            r17 = r5
            r0 = r17
            gnu.bytecode.ClassType r0 = r0.mainClass
            r17 = r0
            java.lang.String r17 = r17.getName()
            r16 = r17
            r17 = r16
            if (r17 != 0) goto L_0x0295
            r17 = r5
            r0 = r17
            gnu.bytecode.ClassType r0 = r0.mainClass
            r17 = r0
            r18 = r14
            r17.setName(r18)
            goto L_0x01c8
        L_0x0295:
            r17 = r16
            r18 = r14
            boolean r17 = r17.equals(r18)
            if (r17 != 0) goto L_0x01c8
            r17 = r5
            java.lang.StringBuilder r18 = new java.lang.StringBuilder
            r22 = r18
            r18 = r22
            r19 = r22
            r19.<init>()
            java.lang.String r19 = "duplicate module-name: old name: "
            java.lang.StringBuilder r18 = r18.append(r19)
            r19 = r16
            java.lang.StringBuilder r18 = r18.append(r19)
            java.lang.String r18 = r18.toString()
            gnu.expr.Expression r17 = r17.syntaxError(r18)
            goto L_0x01c8
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.module_name.scanForm(gnu.lists.Pair, gnu.expr.ScopeExp, kawa.lang.Translator):void");
    }
}
