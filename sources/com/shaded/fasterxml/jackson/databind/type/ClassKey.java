package com.shaded.fasterxml.jackson.databind.type;

import java.io.Serializable;

public final class ClassKey implements Comparable<ClassKey>, Serializable {
    private static final long serialVersionUID = 1;
    private Class<?> _class;
    private String _className;
    private int _hashCode;

    public ClassKey() {
        this._class = null;
        this._className = null;
        this._hashCode = 0;
    }

    public ClassKey(Class<?> cls) {
        Class<?> cls2 = cls;
        this._class = cls2;
        this._className = cls2.getName();
        this._hashCode = this._className.hashCode();
    }

    public void reset(Class<?> cls) {
        Class<?> cls2 = cls;
        this._class = cls2;
        this._className = cls2.getName();
        this._hashCode = this._className.hashCode();
    }

    public int compareTo(ClassKey classKey) {
        return this._className.compareTo(classKey._className);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r1
            r4 = r0
            if (r3 != r4) goto L_0x0009
            r3 = 1
            r0 = r3
        L_0x0008:
            return r0
        L_0x0009:
            r3 = r1
            if (r3 != 0) goto L_0x000f
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x000f:
            r3 = r1
            java.lang.Class r3 = r3.getClass()
            r4 = r0
            java.lang.Class r4 = r4.getClass()
            if (r3 == r4) goto L_0x001e
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x001e:
            r3 = r1
            com.shaded.fasterxml.jackson.databind.type.ClassKey r3 = (com.shaded.fasterxml.jackson.databind.type.ClassKey) r3
            r2 = r3
            r3 = r2
            java.lang.Class<?> r3 = r3._class
            r4 = r0
            java.lang.Class<?> r4 = r4._class
            if (r3 != r4) goto L_0x002d
            r3 = 1
        L_0x002b:
            r0 = r3
            goto L_0x0008
        L_0x002d:
            r3 = 0
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.type.ClassKey.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return this._hashCode;
    }

    public String toString() {
        return this._className;
    }
}
