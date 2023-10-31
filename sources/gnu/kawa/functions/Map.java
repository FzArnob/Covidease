package gnu.kawa.functions;

import gnu.expr.Declaration;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;

public class Map extends ProcedureN {
    final Declaration applyFieldDecl;
    final ApplyToArgs applyToArgs;
    boolean collect;
    final IsEq isEq;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Map(boolean r9, gnu.kawa.functions.ApplyToArgs r10, gnu.expr.Declaration r11, gnu.kawa.functions.IsEq r12) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r0
            r6 = r1
            if (r6 == 0) goto L_0x0029
            java.lang.String r6 = "map"
        L_0x000c:
            r5.<init>(r6)
            r5 = r0
            r6 = r1
            r5.collect = r6
            r5 = r0
            r6 = r2
            r5.applyToArgs = r6
            r5 = r0
            r6 = r3
            r5.applyFieldDecl = r6
            r5 = r0
            r6 = r4
            r5.isEq = r6
            r5 = r0
            gnu.mapping.Symbol r6 = gnu.mapping.Procedure.validateApplyKey
            java.lang.String r7 = "gnu.kawa.functions.CompileMisc:validateApplyMap"
            r5.setProperty(r6, r7)
            return
        L_0x0029:
            java.lang.String r6 = "for-each"
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.Map.<init>(boolean, gnu.kawa.functions.ApplyToArgs, gnu.expr.Declaration, gnu.kawa.functions.IsEq):void");
    }

    public static Object map1(Procedure procedure, Object obj) throws Throwable {
        Pair pair;
        Procedure proc = procedure;
        Object list = obj;
        Pair result = LList.Empty;
        Pair last = null;
        while (list != LList.Empty) {
            Pair pair2 = (Pair) list;
            new Pair(proc.apply1(pair2.getCar()), LList.Empty);
            Pair new_pair = pair;
            if (last == null) {
                result = new_pair;
            } else {
                last.setCdr(new_pair);
            }
            last = new_pair;
            list = pair2.getCdr();
        }
        return result;
    }

    public static void forEach1(Procedure procedure, Object obj) throws Throwable {
        Procedure proc = procedure;
        Object list = obj;
        while (list != LList.Empty) {
            Pair pair = (Pair) list;
            Object apply1 = proc.apply1(pair.getCar());
            list = pair.getCdr();
        }
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        Object arg1 = obj;
        Object arg2 = obj2;
        if (arg1 instanceof Procedure) {
            Procedure proc = (Procedure) arg1;
            if (this.collect) {
                return map1(proc, arg2);
            }
            forEach1(proc, arg2);
            return Values.empty;
        }
        Object[] objArr = new Object[2];
        objArr[0] = arg1;
        Object[] objArr2 = objArr;
        objArr2[1] = arg2;
        return applyN(objArr2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: gnu.kawa.functions.ApplyToArgs} */
    /* JADX WARNING: type inference failed for: r12v23, types: [gnu.lists.Pair] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object applyN(java.lang.Object[] r19) throws java.lang.Throwable {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r12 = r1
            int r12 = r12.length
            r13 = 1
            int r12 = r12 + -1
            r2 = r12
            r12 = r2
            r13 = 1
            if (r12 != r13) goto L_0x003b
            r12 = r1
            r13 = 0
            r12 = r12[r13]
            boolean r12 = r12 instanceof gnu.mapping.Procedure
            if (r12 == 0) goto L_0x003b
            r12 = r1
            r13 = 0
            r12 = r12[r13]
            gnu.mapping.Procedure r12 = (gnu.mapping.Procedure) r12
            gnu.mapping.Procedure r12 = (gnu.mapping.Procedure) r12
            r3 = r12
            r12 = r0
            boolean r12 = r12.collect
            if (r12 == 0) goto L_0x002f
            r12 = r3
            r13 = r1
            r14 = 1
            r13 = r13[r14]
            java.lang.Object r12 = map1(r12, r13)
            r0 = r12
        L_0x002e:
            return r0
        L_0x002f:
            r12 = r3
            r13 = r1
            r14 = 1
            r13 = r13[r14]
            forEach1(r12, r13)
            gnu.mapping.Values r12 = gnu.mapping.Values.empty
            r0 = r12
            goto L_0x002e
        L_0x003b:
            r12 = 0
            r4 = r12
            r12 = r0
            boolean r12 = r12.collect
            if (r12 == 0) goto L_0x007a
            gnu.lists.LList r12 = gnu.lists.LList.Empty
            r3 = r12
        L_0x0045:
            r12 = r2
            java.lang.Object[] r12 = new java.lang.Object[r12]
            r5 = r12
            r12 = r1
            r13 = 1
            r14 = r5
            r15 = 0
            r16 = r2
            java.lang.System.arraycopy(r12, r13, r14, r15, r16)
            r12 = r1
            r13 = 0
            r12 = r12[r13]
            boolean r12 = r12 instanceof gnu.mapping.Procedure
            if (r12 == 0) goto L_0x007e
            r12 = 0
            r7 = r12
            r12 = r2
            java.lang.Object[] r12 = new java.lang.Object[r12]
            r8 = r12
            r12 = r1
            r13 = 0
            r12 = r12[r13]
            gnu.mapping.Procedure r12 = (gnu.mapping.Procedure) r12
            r6 = r12
        L_0x0067:
            r12 = 0
            r9 = r12
        L_0x0069:
            r12 = r9
            r13 = r2
            if (r12 >= r13) goto L_0x00af
            r12 = r5
            r13 = r9
            r12 = r12[r13]
            r10 = r12
            r12 = r10
            gnu.lists.LList r13 = gnu.lists.LList.Empty
            if (r12 != r13) goto L_0x0094
            r12 = r3
            r0 = r12
            goto L_0x002e
        L_0x007a:
            gnu.mapping.Values r12 = gnu.mapping.Values.empty
            r3 = r12
            goto L_0x0045
        L_0x007e:
            r12 = 1
            r7 = r12
            r12 = r2
            r13 = 1
            int r12 = r12 + 1
            java.lang.Object[] r12 = new java.lang.Object[r12]
            r8 = r12
            r12 = r8
            r13 = 0
            r14 = r1
            r15 = 0
            r14 = r14[r15]
            r12[r13] = r14
            r12 = r0
            gnu.kawa.functions.ApplyToArgs r12 = r12.applyToArgs
            r6 = r12
            goto L_0x0067
        L_0x0094:
            r12 = r10
            gnu.lists.Pair r12 = (gnu.lists.Pair) r12
            r11 = r12
            r12 = r8
            r13 = r7
            r14 = r9
            int r13 = r13 + r14
            r14 = r11
            java.lang.Object r14 = r14.getCar()
            r12[r13] = r14
            r12 = r5
            r13 = r9
            r14 = r11
            java.lang.Object r14 = r14.getCdr()
            r12[r13] = r14
            int r9 = r9 + 1
            goto L_0x0069
        L_0x00af:
            r12 = r6
            r13 = r8
            java.lang.Object r12 = r12.applyN(r13)
            r9 = r12
            r12 = r0
            boolean r12 = r12.collect
            if (r12 == 0) goto L_0x00d1
            gnu.lists.Pair r12 = new gnu.lists.Pair
            r17 = r12
            r12 = r17
            r13 = r17
            r14 = r9
            gnu.lists.LList r15 = gnu.lists.LList.Empty
            r13.<init>(r14, r15)
            r10 = r12
            r12 = r4
            if (r12 != 0) goto L_0x00d2
            r12 = r10
            r3 = r12
        L_0x00cf:
            r12 = r10
            r4 = r12
        L_0x00d1:
            goto L_0x0067
        L_0x00d2:
            r12 = r4
            r13 = r10
            r12.setCdr(r13)
            goto L_0x00cf
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.Map.applyN(java.lang.Object[]):java.lang.Object");
    }
}
