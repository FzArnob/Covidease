package gnu.expr;

import gnu.bytecode.ClassType;

public class PairClassType extends ClassType {
    public ClassType instanceType;
    Object staticLink;

    public PairClassType() {
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    PairClassType(java.lang.Class r6, java.lang.Class r7) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r0
            r4 = r1
            java.lang.String r4 = r4.getName()
            r3.<init>(r4)
            r3 = r0
            r4 = 1
            r3.setExisting(r4)
            r3 = r0
            r4 = r1
            r3.reflectClass = r4
            r3 = r1
            r4 = r0
            gnu.bytecode.Type.registerTypeForClass(r3, r4)
            r3 = r0
            r4 = r2
            gnu.bytecode.Type r4 = gnu.bytecode.Type.make(r4)
            gnu.bytecode.ClassType r4 = (gnu.bytecode.ClassType) r4
            r3.instanceType = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.PairClassType.<init>(java.lang.Class, java.lang.Class):void");
    }

    public static PairClassType make(Class reflectInterface, Class reflectInstanceClass) {
        PairClassType pairClassType;
        new PairClassType(reflectInterface, reflectInstanceClass);
        return pairClassType;
    }

    public static PairClassType make(Class reflectInterface, Class reflectInstanceClass, Object staticLink2) {
        PairClassType pairClassType;
        new PairClassType(reflectInterface, reflectInstanceClass);
        PairClassType type = pairClassType;
        type.staticLink = staticLink2;
        return type;
    }

    public Object getStaticLink() {
        return this.staticLink;
    }

    public static Object extractStaticLink(ClassType type) {
        return ((PairClassType) type).staticLink;
    }
}
