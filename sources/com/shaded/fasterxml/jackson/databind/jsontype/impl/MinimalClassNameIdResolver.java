package com.shaded.fasterxml.jackson.databind.jsontype.impl;

import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.databind.JavaType;

public class MinimalClassNameIdResolver extends ClassNameIdResolver {
    protected final String _basePackageName;
    protected final String _basePackagePrefix;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MinimalClassNameIdResolver(com.shaded.fasterxml.jackson.databind.JavaType r11, com.shaded.fasterxml.jackson.databind.type.TypeFactory r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r5 = r0
            r6 = r1
            r7 = r2
            r5.<init>(r6, r7)
            r5 = r1
            java.lang.Class r5 = r5.getRawClass()
            java.lang.String r5 = r5.getName()
            r3 = r5
            r5 = r3
            r6 = 46
            int r5 = r5.lastIndexOf(r6)
            r4 = r5
            r5 = r4
            if (r5 >= 0) goto L_0x002b
            r5 = r0
            java.lang.String r6 = ""
            r5._basePackageName = r6
            r5 = r0
            java.lang.String r6 = "."
            r5._basePackagePrefix = r6
        L_0x002a:
            return
        L_0x002b:
            r5 = r0
            r6 = r3
            r7 = 0
            r8 = r4
            r9 = 1
            int r8 = r8 + 1
            java.lang.String r6 = r6.substring(r7, r8)
            r5._basePackagePrefix = r6
            r5 = r0
            r6 = r3
            r7 = 0
            r8 = r4
            java.lang.String r6 = r6.substring(r7, r8)
            r5._basePackageName = r6
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.jsontype.impl.MinimalClassNameIdResolver.<init>(com.shaded.fasterxml.jackson.databind.JavaType, com.shaded.fasterxml.jackson.databind.type.TypeFactory):void");
    }

    public JsonTypeInfo.C1434Id getMechanism() {
        return JsonTypeInfo.C1434Id.MINIMAL_CLASS;
    }

    public String idFromValue(Object obj) {
        String name = obj.getClass().getName();
        if (name.startsWith(this._basePackagePrefix)) {
            return name.substring(this._basePackagePrefix.length() - 1);
        }
        return name;
    }

    public JavaType typeFromId(String str) {
        StringBuilder sb;
        String str2 = str;
        if (str2.startsWith(".")) {
            new StringBuilder(str2.length() + this._basePackageName.length());
            StringBuilder sb2 = sb;
            if (this._basePackageName.length() == 0) {
                StringBuilder append = sb2.append(str2.substring(1));
            } else {
                StringBuilder append2 = sb2.append(this._basePackageName).append(str2);
            }
            str2 = sb2.toString();
        }
        return super.typeFromId(str2);
    }
}
