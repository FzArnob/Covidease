package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Filter;
import gnu.bytecode.ObjectType;

/* compiled from: ClassMethods */
class MethodFilter implements Filter {
    ClassType caller;
    int modifiers;
    int modmask;
    String name;
    int nlen;
    ObjectType receiver;

    public MethodFilter(String str, int modifiers2, int modmask2, ClassType caller2, ObjectType receiver2) {
        String name2 = str;
        this.name = name2;
        this.nlen = name2.length();
        this.modifiers = modifiers2;
        this.modmask = modmask2;
        this.caller = caller2;
        this.receiver = receiver2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0067, code lost:
        if (r6 != 'X') goto L_0x0069;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean select(java.lang.Object r14) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r8 = r1
            gnu.bytecode.Method r8 = (gnu.bytecode.Method) r8
            r2 = r8
            r8 = r2
            java.lang.String r8 = r8.getName()
            r3 = r8
            r8 = r2
            int r8 = r8.getModifiers()
            r4 = r8
            r8 = r4
            r9 = r0
            int r9 = r9.modmask
            r8 = r8 & r9
            r9 = r0
            int r9 = r9.modifiers
            if (r8 != r9) goto L_0x002d
            r8 = r4
            r9 = 4096(0x1000, float:5.74E-42)
            r8 = r8 & 4096(0x1000, float:5.74E-42)
            if (r8 != 0) goto L_0x002d
            r8 = r3
            r9 = r0
            java.lang.String r9 = r9.name
            boolean r8 = r8.startsWith(r9)
            if (r8 != 0) goto L_0x0030
        L_0x002d:
            r8 = 0
            r0 = r8
        L_0x002f:
            return r0
        L_0x0030:
            r8 = r3
            int r8 = r8.length()
            r5 = r8
            r8 = r5
            r9 = r0
            int r9 = r9.nlen
            if (r8 == r9) goto L_0x007f
            r8 = r5
            r9 = r0
            int r9 = r9.nlen
            r10 = 2
            int r9 = r9 + 2
            if (r8 != r9) goto L_0x0069
            r8 = r3
            r9 = r0
            int r9 = r9.nlen
            char r8 = r8.charAt(r9)
            r9 = 36
            if (r8 != r9) goto L_0x0069
            r8 = r3
            r9 = r0
            int r9 = r9.nlen
            r10 = 1
            int r9 = r9 + 1
            char r8 = r8.charAt(r9)
            r12 = r8
            r8 = r12
            r9 = r12
            r6 = r9
            r9 = 86
            if (r8 == r9) goto L_0x007f
            r8 = r6
            r9 = 88
            if (r8 == r9) goto L_0x007f
        L_0x0069:
            r8 = r5
            r9 = r0
            int r9 = r9.nlen
            r10 = 4
            int r9 = r9 + 4
            if (r8 != r9) goto L_0x007c
            r8 = r3
            java.lang.String r9 = "$V$X"
            boolean r8 = r8.endsWith(r9)
            if (r8 != 0) goto L_0x007f
        L_0x007c:
            r8 = 0
            r0 = r8
            goto L_0x002f
        L_0x007f:
            r8 = r0
            gnu.bytecode.ObjectType r8 = r8.receiver
            boolean r8 = r8 instanceof gnu.bytecode.ClassType
            if (r8 == 0) goto L_0x00a6
            r8 = r0
            gnu.bytecode.ObjectType r8 = r8.receiver
            gnu.bytecode.ClassType r8 = (gnu.bytecode.ClassType) r8
        L_0x008b:
            r7 = r8
            r8 = r0
            gnu.bytecode.ClassType r8 = r8.caller
            if (r8 == 0) goto L_0x00a3
            r8 = r0
            gnu.bytecode.ClassType r8 = r8.caller
            r9 = r7
            r10 = r0
            gnu.bytecode.ObjectType r10 = r10.receiver
            r11 = r2
            int r11 = r11.getModifiers()
            boolean r8 = r8.isAccessible(r9, r10, r11)
            if (r8 == 0) goto L_0x00ac
        L_0x00a3:
            r8 = 1
        L_0x00a4:
            r0 = r8
            goto L_0x002f
        L_0x00a6:
            r8 = r2
            gnu.bytecode.ClassType r8 = r8.getDeclaringClass()
            goto L_0x008b
        L_0x00ac:
            r8 = 0
            goto L_0x00a4
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.MethodFilter.select(java.lang.Object):boolean");
    }
}
