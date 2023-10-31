package com.shaded.fasterxml.jackson.databind.jsontype;

import java.io.Serializable;

public final class NamedType implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Class<?> _class;
    protected final int _hashCode;
    protected String _name;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NamedType(Class<?> cls) {
        this(cls, (String) null);
    }

    public NamedType(Class<?> cls, String str) {
        Class<?> cls2 = cls;
        this._class = cls2;
        this._hashCode = cls2.getName().hashCode();
        setName(str);
    }

    public Class<?> getType() {
        return this._class;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String str) {
        String str2 = str;
        this._name = (str2 == null || str2.length() == 0) ? null : str2;
    }

    public boolean hasName() {
        return this._name != null;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r1
            r3 = r0
            if (r2 != r3) goto L_0x0009
            r2 = 1
            r0 = r2
        L_0x0008:
            return r0
        L_0x0009:
            r2 = r1
            if (r2 != 0) goto L_0x000f
            r2 = 0
            r0 = r2
            goto L_0x0008
        L_0x000f:
            r2 = r1
            java.lang.Class r2 = r2.getClass()
            r3 = r0
            java.lang.Class r3 = r3.getClass()
            if (r2 == r3) goto L_0x001e
            r2 = 0
            r0 = r2
            goto L_0x0008
        L_0x001e:
            r2 = r0
            java.lang.Class<?> r2 = r2._class
            r3 = r1
            com.shaded.fasterxml.jackson.databind.jsontype.NamedType r3 = (com.shaded.fasterxml.jackson.databind.jsontype.NamedType) r3
            java.lang.Class<?> r3 = r3._class
            if (r2 != r3) goto L_0x002b
            r2 = 1
        L_0x0029:
            r0 = r2
            goto L_0x0008
        L_0x002b:
            r2 = 0
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.jsontype.NamedType.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return this._hashCode;
    }

    public String toString() {
        StringBuilder sb;
        StringBuilder sb2;
        String sb3;
        new StringBuilder();
        StringBuilder append = sb.append("[NamedType, class ").append(this._class.getName()).append(", name: ");
        if (this._name == null) {
            sb3 = "null";
        } else {
            new StringBuilder();
            sb3 = sb2.append("'").append(this._name).append("'").toString();
        }
        return append.append(sb3).append("]").toString();
    }
}
