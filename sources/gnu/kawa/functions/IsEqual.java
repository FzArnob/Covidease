package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.mapping.Procedure2;

public class IsEqual extends Procedure2 {
    Language language;

    public IsEqual(Language language2, String name) {
        this.language = language2;
        setName(name);
    }

    public static boolean numberEquals(Number number, Number number2) {
        Number num1 = number;
        Number num2 = number2;
        boolean exact1 = Arithmetic.isExact(num1);
        boolean exact2 = Arithmetic.isExact(num2);
        if (exact1 && exact2) {
            return NumberCompare.$Eq(num1, num2);
        }
        return exact1 == exact2 && num1.equals(num2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:95:?, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean apply(java.lang.Object r11, java.lang.Object r12) {
        /*
            r0 = r11
            r1 = r12
            r8 = r0
            r9 = r1
            if (r8 != r9) goto L_0x0009
            r8 = 1
            r0 = r8
        L_0x0008:
            return r0
        L_0x0009:
            r8 = r0
            if (r8 == 0) goto L_0x000f
            r8 = r1
            if (r8 != 0) goto L_0x0012
        L_0x000f:
            r8 = 0
            r0 = r8
            goto L_0x0008
        L_0x0012:
            r8 = r0
            boolean r8 = r8 instanceof java.lang.Number
            if (r8 == 0) goto L_0x0028
            r8 = r1
            boolean r8 = r8 instanceof java.lang.Number
            if (r8 == 0) goto L_0x0028
            r8 = r0
            java.lang.Number r8 = (java.lang.Number) r8
            r9 = r1
            java.lang.Number r9 = (java.lang.Number) r9
            boolean r8 = numberEquals(r8, r9)
            r0 = r8
            goto L_0x0008
        L_0x0028:
            r8 = r0
            boolean r8 = r8 instanceof java.lang.CharSequence
            if (r8 == 0) goto L_0x006b
            r8 = r1
            boolean r8 = r8 instanceof java.lang.CharSequence
            if (r8 != 0) goto L_0x0035
            r8 = 0
            r0 = r8
            goto L_0x0008
        L_0x0035:
            r8 = r0
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r2 = r8
            r8 = r1
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r3 = r8
            r8 = r2
            int r8 = r8.length()
            r4 = r8
            r8 = r3
            int r8 = r8.length()
            r5 = r8
            r8 = r4
            r9 = r5
            if (r8 == r9) goto L_0x0050
            r8 = 0
            r0 = r8
            goto L_0x0008
        L_0x0050:
            r8 = r4
            r6 = r8
        L_0x0052:
            int r6 = r6 + -1
            r8 = r6
            if (r8 < 0) goto L_0x0068
            r8 = r2
            r9 = r6
            char r8 = r8.charAt(r9)
            r9 = r3
            r10 = r6
            char r9 = r9.charAt(r10)
            if (r8 == r9) goto L_0x0052
            r8 = 0
            r0 = r8
            goto L_0x0008
        L_0x0068:
            r8 = 1
            r0 = r8
            goto L_0x0008
        L_0x006b:
            r8 = r0
            boolean r8 = r8 instanceof gnu.lists.FVector
            if (r8 == 0) goto L_0x00b8
            r8 = r1
            boolean r8 = r8 instanceof gnu.lists.FVector
            if (r8 != 0) goto L_0x0078
            r8 = 0
            r0 = r8
            goto L_0x0008
        L_0x0078:
            r8 = r0
            gnu.lists.FVector r8 = (gnu.lists.FVector) r8
            r2 = r8
            r8 = r1
            gnu.lists.FVector r8 = (gnu.lists.FVector) r8
            r3 = r8
            r8 = r2
            int r8 = r8.size
            r4 = r8
            r8 = r3
            java.lang.Object[] r8 = r8.data
            if (r8 == 0) goto L_0x008f
            r8 = r3
            int r8 = r8.size
            r9 = r4
            if (r8 == r9) goto L_0x0093
        L_0x008f:
            r8 = 0
            r0 = r8
            goto L_0x0008
        L_0x0093:
            r8 = r2
            java.lang.Object[] r8 = r8.data
            r5 = r8
            r8 = r3
            java.lang.Object[] r8 = r8.data
            r6 = r8
            r8 = r4
            r7 = r8
        L_0x009d:
            int r7 = r7 + -1
            r8 = r7
            if (r8 < 0) goto L_0x00b4
            r8 = r5
            r9 = r7
            r8 = r8[r9]
            r9 = r6
            r10 = r7
            r9 = r9[r10]
            boolean r8 = apply(r8, r9)
            if (r8 != 0) goto L_0x009d
            r8 = 0
            r0 = r8
            goto L_0x0008
        L_0x00b4:
            r8 = 1
            r0 = r8
            goto L_0x0008
        L_0x00b8:
            r8 = r0
            boolean r8 = r8 instanceof gnu.lists.LList
            if (r8 == 0) goto L_0x0125
            r8 = r0
            boolean r8 = r8 instanceof gnu.lists.Pair
            if (r8 == 0) goto L_0x00c7
            r8 = r1
            boolean r8 = r8 instanceof gnu.lists.Pair
            if (r8 != 0) goto L_0x00cb
        L_0x00c7:
            r8 = 0
            r0 = r8
            goto L_0x0008
        L_0x00cb:
            r8 = r0
            gnu.lists.Pair r8 = (gnu.lists.Pair) r8
            r2 = r8
            r8 = r1
            gnu.lists.Pair r8 = (gnu.lists.Pair) r8
            r3 = r8
        L_0x00d3:
            r8 = r2
            java.lang.Object r8 = r8.getCar()
            r4 = r8
            r8 = r3
            java.lang.Object r8 = r8.getCar()
            r5 = r8
            r8 = r4
            r9 = r5
            boolean r8 = apply(r8, r9)
            if (r8 != 0) goto L_0x00eb
            r8 = 0
            r0 = r8
            goto L_0x0008
        L_0x00eb:
            r8 = r2
            java.lang.Object r8 = r8.getCdr()
            r4 = r8
            r8 = r3
            java.lang.Object r8 = r8.getCdr()
            r5 = r8
            r8 = r4
            r9 = r5
            if (r8 != r9) goto L_0x00ff
            r8 = 1
            r0 = r8
            goto L_0x0008
        L_0x00ff:
            r8 = r4
            if (r8 == 0) goto L_0x0105
            r8 = r5
            if (r8 != 0) goto L_0x0109
        L_0x0105:
            r8 = 0
            r0 = r8
            goto L_0x0008
        L_0x0109:
            r8 = r4
            boolean r8 = r8 instanceof gnu.lists.Pair
            if (r8 == 0) goto L_0x0113
            r8 = r5
            boolean r8 = r8 instanceof gnu.lists.Pair
            if (r8 != 0) goto L_0x011c
        L_0x0113:
            r8 = r4
            r9 = r5
            boolean r8 = apply(r8, r9)
            r0 = r8
            goto L_0x0008
        L_0x011c:
            r8 = r4
            gnu.lists.Pair r8 = (gnu.lists.Pair) r8
            r2 = r8
            r8 = r5
            gnu.lists.Pair r8 = (gnu.lists.Pair) r8
            r3 = r8
            goto L_0x00d3
        L_0x0125:
            r8 = r0
            r9 = r1
            boolean r8 = r8.equals(r9)
            r0 = r8
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.IsEqual.apply(java.lang.Object, java.lang.Object):boolean");
    }

    public Object apply2(Object arg1, Object arg2) {
        return this.language.booleanObject(apply(arg1, arg2));
    }
}
