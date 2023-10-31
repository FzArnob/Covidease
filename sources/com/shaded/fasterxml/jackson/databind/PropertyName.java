package com.shaded.fasterxml.jackson.databind;

import java.io.Serializable;

public class PropertyName implements Serializable {
    public static final PropertyName NO_NAME;
    public static final PropertyName USE_DEFAULT;
    private static final String _NO_NAME = "#disabled";
    private static final String _USE_DEFAULT = "";
    private static final long serialVersionUID = 7930806520033045126L;
    protected final String _namespace;
    protected final String _simpleName;

    static {
        PropertyName propertyName;
        PropertyName propertyName2;
        String str;
        new PropertyName("", (String) null);
        USE_DEFAULT = propertyName;
        new String(_NO_NAME);
        new PropertyName(str, (String) null);
        NO_NAME = propertyName2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PropertyName(String str) {
        this(str, (String) null);
    }

    public PropertyName(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        this._simpleName = str3 == null ? "" : str3;
        this._namespace = str4;
    }

    /* access modifiers changed from: protected */
    public Object readResolve() {
        if (this._simpleName == null || "".equals(this._simpleName)) {
            return USE_DEFAULT;
        }
        if (this._simpleName.equals(_NO_NAME)) {
            return NO_NAME;
        }
        return this;
    }

    public static PropertyName construct(String str, String str2) {
        PropertyName propertyName;
        String str3 = str;
        String str4 = str2;
        if (str3 == null) {
            str3 = "";
        }
        if (str4 == null && str3.length() == 0) {
            return USE_DEFAULT;
        }
        new PropertyName(str3, str4);
        return propertyName;
    }

    public PropertyName withSimpleName(String str) {
        PropertyName propertyName;
        String str2 = str;
        if (str2 == null) {
            str2 = "";
        }
        if (str2.equals(this._simpleName)) {
            return this;
        }
        new PropertyName(str2, this._namespace);
        return propertyName;
    }

    public PropertyName withNamespace(String str) {
        PropertyName propertyName;
        String str2 = str;
        if (str2 == null) {
            if (this._namespace == null) {
                return this;
            }
        } else if (str2.equals(this._namespace)) {
            return this;
        }
        new PropertyName(this._simpleName, str2);
        return propertyName;
    }

    public String getSimpleName() {
        return this._simpleName;
    }

    public String getNamespace() {
        return this._namespace;
    }

    public boolean hasSimpleName() {
        return this._simpleName.length() > 0;
    }

    public boolean hasNamespace() {
        return this._namespace != null;
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
            com.shaded.fasterxml.jackson.databind.PropertyName r3 = (com.shaded.fasterxml.jackson.databind.PropertyName) r3
            r2 = r3
            r3 = r0
            java.lang.String r3 = r3._simpleName
            if (r3 != 0) goto L_0x002f
            r3 = r2
            java.lang.String r3 = r3._simpleName
            if (r3 == 0) goto L_0x003e
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x002f:
            r3 = r0
            java.lang.String r3 = r3._simpleName
            r4 = r2
            java.lang.String r4 = r4._simpleName
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x003e
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x003e:
            r3 = r0
            java.lang.String r3 = r3._namespace
            if (r3 != 0) goto L_0x004e
            r3 = 0
            r4 = r2
            java.lang.String r4 = r4._namespace
            if (r3 != r4) goto L_0x004c
            r3 = 1
        L_0x004a:
            r0 = r3
            goto L_0x0008
        L_0x004c:
            r3 = 0
            goto L_0x004a
        L_0x004e:
            r3 = r0
            java.lang.String r3 = r3._namespace
            r4 = r2
            java.lang.String r4 = r4._namespace
            boolean r3 = r3.equals(r4)
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.PropertyName.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        if (this._namespace == null) {
            return this._simpleName.hashCode();
        }
        return this._namespace.hashCode() ^ this._simpleName.hashCode();
    }

    public String toString() {
        StringBuilder sb;
        if (this._namespace == null) {
            return this._simpleName;
        }
        new StringBuilder();
        return sb.append("{").append(this._namespace).append("}").append(this._simpleName).toString();
    }
}
