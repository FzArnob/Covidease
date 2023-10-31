package com.shaded.fasterxml.jackson.databind.introspect;

import java.lang.reflect.Constructor;

public final class MemberKey {
    static final Class<?>[] NO_CLASSES = new Class[0];
    final Class<?>[] _argTypes;
    final String _name;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MemberKey(java.lang.reflect.Method r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            java.lang.String r3 = r3.getName()
            r4 = r1
            java.lang.Class[] r4 = r4.getParameterTypes()
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.introspect.MemberKey.<init>(java.lang.reflect.Method):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MemberKey(Constructor<?> constructor) {
        this("", constructor.getParameterTypes());
    }

    public MemberKey(String str, Class<?>[] clsArr) {
        Class<?>[] clsArr2 = clsArr;
        this._name = str;
        this._argTypes = clsArr2 == null ? NO_CLASSES : clsArr2;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(this._name).append("(").append(this._argTypes.length).append("-args)").toString();
    }

    public int hashCode() {
        return this._name.hashCode() + this._argTypes.length;
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r11) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r8 = r1
            r9 = r0
            if (r8 != r9) goto L_0x0009
            r8 = 1
            r0 = r8
        L_0x0008:
            return r0
        L_0x0009:
            r8 = r1
            if (r8 != 0) goto L_0x000f
            r8 = 0
            r0 = r8
            goto L_0x0008
        L_0x000f:
            r8 = r1
            java.lang.Class r8 = r8.getClass()
            r9 = r0
            java.lang.Class r9 = r9.getClass()
            if (r8 == r9) goto L_0x001e
            r8 = 0
            r0 = r8
            goto L_0x0008
        L_0x001e:
            r8 = r1
            com.shaded.fasterxml.jackson.databind.introspect.MemberKey r8 = (com.shaded.fasterxml.jackson.databind.introspect.MemberKey) r8
            r2 = r8
            r8 = r0
            java.lang.String r8 = r8._name
            r9 = r2
            java.lang.String r9 = r9._name
            boolean r8 = r8.equals(r9)
            if (r8 != 0) goto L_0x0031
            r8 = 0
            r0 = r8
            goto L_0x0008
        L_0x0031:
            r8 = r2
            java.lang.Class<?>[] r8 = r8._argTypes
            r3 = r8
            r8 = r0
            java.lang.Class<?>[] r8 = r8._argTypes
            int r8 = r8.length
            r4 = r8
            r8 = r3
            int r8 = r8.length
            r9 = r4
            if (r8 == r9) goto L_0x0042
            r8 = 0
            r0 = r8
            goto L_0x0008
        L_0x0042:
            r8 = 0
            r5 = r8
        L_0x0044:
            r8 = r5
            r9 = r4
            if (r8 >= r9) goto L_0x006f
            r8 = r3
            r9 = r5
            r8 = r8[r9]
            r6 = r8
            r8 = r0
            java.lang.Class<?>[] r8 = r8._argTypes
            r9 = r5
            r8 = r8[r9]
            r7 = r8
            r8 = r6
            r9 = r7
            if (r8 != r9) goto L_0x005b
        L_0x0058:
            int r5 = r5 + 1
            goto L_0x0044
        L_0x005b:
            r8 = r6
            r9 = r7
            boolean r8 = r8.isAssignableFrom(r9)
            if (r8 != 0) goto L_0x0058
            r8 = r7
            r9 = r6
            boolean r8 = r8.isAssignableFrom(r9)
            if (r8 == 0) goto L_0x006c
            goto L_0x0058
        L_0x006c:
            r8 = 0
            r0 = r8
            goto L_0x0008
        L_0x006f:
            r8 = 1
            r0 = r8
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.introspect.MemberKey.equals(java.lang.Object):boolean");
    }
}
