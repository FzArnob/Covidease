package com.google.appinventor.components.runtime.repackaged.org.json;

import java.util.HashMap;

public class XMLTokener extends JSONTokener {
    public static final HashMap entity;

    static {
        HashMap hashMap;
        new HashMap(8);
        entity = hashMap;
        Object put = entity.put("amp", XML.AMP);
        Object put2 = entity.put("apos", XML.APOS);
        Object put3 = entity.put("gt", XML.f321GT);
        Object put4 = entity.put("lt", XML.f322LT);
        Object put5 = entity.put("quot", XML.QUOT);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public XMLTokener(String s) {
        super(s);
    }

    public String nextCDATA() throws JSONException {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        while (true) {
            char c = next();
            if (end()) {
                throw syntaxError("Unclosed CDATA");
            }
            StringBuffer append = sb.append(c);
            int i = sb.length() - 3;
            if (i >= 0 && sb.charAt(i) == ']' && sb.charAt(i + 1) == ']' && sb.charAt(i + 2) == '>') {
                sb.setLength(i);
                return sb.toString();
            }
        }
    }

    public Object nextContent() throws JSONException {
        char c;
        StringBuffer stringBuffer;
        do {
            c = next();
        } while (Character.isWhitespace(c));
        if (c == 0) {
            return null;
        }
        if (c == '<') {
            return XML.f322LT;
        }
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        while (c != '<' && c != 0) {
            if (c == '&') {
                StringBuffer append = sb.append(nextEntity(c));
            } else {
                StringBuffer append2 = sb.append(c);
            }
            c = next();
        }
        back();
        return sb.toString().trim();
    }

    public Object nextEntity(char c) throws JSONException {
        StringBuffer stringBuffer;
        char c2;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        Object stringBuffer4;
        char ampersand = c;
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        while (true) {
            c2 = next();
            if (!Character.isLetterOrDigit(c2) && c2 != '#') {
                break;
            }
            StringBuffer append = sb.append(Character.toLowerCase(c2));
        }
        if (c2 == ';') {
            String string = sb.toString();
            Object object = entity.get(string);
            if (object != null) {
                stringBuffer4 = object;
            } else {
                new StringBuffer();
                stringBuffer4 = stringBuffer3.append(ampersand).append(string).append(";").toString();
            }
            return stringBuffer4;
        }
        new StringBuffer();
        throw syntaxError(stringBuffer2.append("Missing ';' in XML entity: &").append(sb).toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0012 A[LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object nextMeta() throws com.google.appinventor.components.runtime.repackaged.org.json.JSONException {
        /*
            r5 = this;
            r0 = r5
        L_0x0001:
            r3 = r0
            char r3 = r3.next()
            r1 = r3
            r3 = r1
            boolean r3 = java.lang.Character.isWhitespace(r3)
            if (r3 != 0) goto L_0x0001
            r3 = r1
            switch(r3) {
                case 0: goto L_0x0023;
                case 33: goto L_0x003c;
                case 34: goto L_0x0044;
                case 39: goto L_0x0044;
                case 47: goto L_0x0034;
                case 60: goto L_0x002c;
                case 61: goto L_0x0038;
                case 62: goto L_0x0030;
                case 63: goto L_0x0040;
                default: goto L_0x0012;
            }
        L_0x0012:
            r3 = r0
            char r3 = r3.next()
            r1 = r3
            r3 = r1
            boolean r3 = java.lang.Character.isWhitespace(r3)
            if (r3 == 0) goto L_0x0060
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            r0 = r3
        L_0x0022:
            return r0
        L_0x0023:
            r3 = r0
            java.lang.String r4 = "Misshaped meta tag"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r3 = r3.syntaxError(r4)
            throw r3
        L_0x002c:
            java.lang.Character r3 = com.google.appinventor.components.runtime.repackaged.org.json.XML.f322LT
            r0 = r3
            goto L_0x0022
        L_0x0030:
            java.lang.Character r3 = com.google.appinventor.components.runtime.repackaged.org.json.XML.f321GT
            r0 = r3
            goto L_0x0022
        L_0x0034:
            java.lang.Character r3 = com.google.appinventor.components.runtime.repackaged.org.json.XML.SLASH
            r0 = r3
            goto L_0x0022
        L_0x0038:
            java.lang.Character r3 = com.google.appinventor.components.runtime.repackaged.org.json.XML.f320EQ
            r0 = r3
            goto L_0x0022
        L_0x003c:
            java.lang.Character r3 = com.google.appinventor.components.runtime.repackaged.org.json.XML.BANG
            r0 = r3
            goto L_0x0022
        L_0x0040:
            java.lang.Character r3 = com.google.appinventor.components.runtime.repackaged.org.json.XML.QUEST
            r0 = r3
            goto L_0x0022
        L_0x0044:
            r3 = r1
            r2 = r3
        L_0x0046:
            r3 = r0
            char r3 = r3.next()
            r1 = r3
            r3 = r1
            if (r3 != 0) goto L_0x0058
            r3 = r0
            java.lang.String r4 = "Unterminated string"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r3 = r3.syntaxError(r4)
            throw r3
        L_0x0058:
            r3 = r1
            r4 = r2
            if (r3 != r4) goto L_0x0046
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            r0 = r3
            goto L_0x0022
        L_0x0060:
            r3 = r1
            switch(r3) {
                case 0: goto L_0x0065;
                case 33: goto L_0x0065;
                case 34: goto L_0x0065;
                case 39: goto L_0x0065;
                case 47: goto L_0x0065;
                case 60: goto L_0x0065;
                case 61: goto L_0x0065;
                case 62: goto L_0x0065;
                case 63: goto L_0x0065;
                default: goto L_0x0064;
            }
        L_0x0064:
            goto L_0x0012
        L_0x0065:
            r3 = r0
            r3.back()
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            r0 = r3
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.XMLTokener.nextMeta():java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x001b A[LOOP_START, PHI: r1 
      PHI: (r1v2 'c' char) = (r1v0 'c' char), (r1v3 'c' char) binds: [B:5:0x0012, B:31:0x009c] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object nextToken() throws com.google.appinventor.components.runtime.repackaged.org.json.JSONException {
        /*
            r8 = this;
            r0 = r8
        L_0x0001:
            r4 = r0
            char r4 = r4.next()
            r1 = r4
            r4 = r1
            boolean r4 = java.lang.Character.isWhitespace(r4)
            if (r4 != 0) goto L_0x0001
            r4 = r1
            switch(r4) {
                case 0: goto L_0x0035;
                case 33: goto L_0x0053;
                case 34: goto L_0x005b;
                case 39: goto L_0x005b;
                case 47: goto L_0x004b;
                case 60: goto L_0x003e;
                case 61: goto L_0x004f;
                case 62: goto L_0x0047;
                case 63: goto L_0x0057;
                default: goto L_0x0012;
            }
        L_0x0012:
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r7 = r4
            r4 = r7
            r5 = r7
            r5.<init>()
            r3 = r4
        L_0x001b:
            r4 = r3
            r5 = r1
            java.lang.StringBuffer r4 = r4.append(r5)
            r4 = r0
            char r4 = r4.next()
            r1 = r4
            r4 = r1
            boolean r4 = java.lang.Character.isWhitespace(r4)
            if (r4 == 0) goto L_0x009b
            r4 = r3
            java.lang.String r4 = r4.toString()
            r0 = r4
        L_0x0034:
            return r0
        L_0x0035:
            r4 = r0
            java.lang.String r5 = "Misshaped element"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r4 = r4.syntaxError(r5)
            throw r4
        L_0x003e:
            r4 = r0
            java.lang.String r5 = "Misplaced '<'"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r4 = r4.syntaxError(r5)
            throw r4
        L_0x0047:
            java.lang.Character r4 = com.google.appinventor.components.runtime.repackaged.org.json.XML.f321GT
            r0 = r4
            goto L_0x0034
        L_0x004b:
            java.lang.Character r4 = com.google.appinventor.components.runtime.repackaged.org.json.XML.SLASH
            r0 = r4
            goto L_0x0034
        L_0x004f:
            java.lang.Character r4 = com.google.appinventor.components.runtime.repackaged.org.json.XML.f320EQ
            r0 = r4
            goto L_0x0034
        L_0x0053:
            java.lang.Character r4 = com.google.appinventor.components.runtime.repackaged.org.json.XML.BANG
            r0 = r4
            goto L_0x0034
        L_0x0057:
            java.lang.Character r4 = com.google.appinventor.components.runtime.repackaged.org.json.XML.QUEST
            r0 = r4
            goto L_0x0034
        L_0x005b:
            r4 = r1
            r2 = r4
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r7 = r4
            r4 = r7
            r5 = r7
            r5.<init>()
            r3 = r4
        L_0x0066:
            r4 = r0
            char r4 = r4.next()
            r1 = r4
            r4 = r1
            if (r4 != 0) goto L_0x0078
            r4 = r0
            java.lang.String r5 = "Unterminated string"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r4 = r4.syntaxError(r5)
            throw r4
        L_0x0078:
            r4 = r1
            r5 = r2
            if (r4 != r5) goto L_0x0083
            r4 = r3
            java.lang.String r4 = r4.toString()
            r0 = r4
            goto L_0x0034
        L_0x0083:
            r4 = r1
            r5 = 38
            if (r4 != r5) goto L_0x0094
            r4 = r3
            r5 = r0
            r6 = r1
            java.lang.Object r5 = r5.nextEntity(r6)
            java.lang.StringBuffer r4 = r4.append(r5)
            goto L_0x0066
        L_0x0094:
            r4 = r3
            r5 = r1
            java.lang.StringBuffer r4 = r4.append(r5)
            goto L_0x0066
        L_0x009b:
            r4 = r1
            switch(r4) {
                case 0: goto L_0x00a1;
                case 33: goto L_0x00a8;
                case 34: goto L_0x00b3;
                case 39: goto L_0x00b3;
                case 47: goto L_0x00a8;
                case 60: goto L_0x00b3;
                case 61: goto L_0x00a8;
                case 62: goto L_0x00a8;
                case 63: goto L_0x00a8;
                case 91: goto L_0x00a8;
                case 93: goto L_0x00a8;
                default: goto L_0x009f;
            }
        L_0x009f:
            goto L_0x001b
        L_0x00a1:
            r4 = r3
            java.lang.String r4 = r4.toString()
            r0 = r4
            goto L_0x0034
        L_0x00a8:
            r4 = r0
            r4.back()
            r4 = r3
            java.lang.String r4 = r4.toString()
            r0 = r4
            goto L_0x0034
        L_0x00b3:
            r4 = r0
            java.lang.String r5 = "Bad character in a name"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r4 = r4.syntaxError(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.XMLTokener.nextToken():java.lang.Object");
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 132 */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0050, code lost:
        if (r8 == false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0062, code lost:
        r6 = next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0069, code lost:
        if (r6 != 0) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006b, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0028, code lost:
        r4[r2] = r6;
        r2 = r2 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean skipPast(java.lang.String r13) throws com.google.appinventor.components.runtime.repackaged.org.json.JSONException {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r9 = 0
            r2 = r9
            r9 = r1
            int r9 = r9.length()
            r3 = r9
            r9 = r3
            char[] r9 = new char[r9]
            r4 = r9
            r9 = 0
            r5 = r9
        L_0x0010:
            r9 = r5
            r10 = r3
            if (r9 >= r10) goto L_0x0037
            r9 = r0
            char r9 = r9.next()
            r6 = r9
            r9 = r6
            if (r9 != 0) goto L_0x0020
            r9 = 0
            r0 = r9
        L_0x001f:
            return r0
        L_0x0020:
            r9 = r4
            r10 = r5
            r11 = r6
            r9[r10] = r11
            int r5 = r5 + 1
            goto L_0x0010
        L_0x0028:
            r9 = r4
            r10 = r2
            r11 = r6
            r9[r10] = r11
            int r2 = r2 + 1
            r9 = r2
            r10 = r3
            if (r9 < r10) goto L_0x0037
            r9 = r2
            r10 = r3
            int r9 = r9 - r10
            r2 = r9
        L_0x0037:
            r9 = r2
            r7 = r9
            r9 = 1
            r8 = r9
            r9 = 0
            r5 = r9
        L_0x003d:
            r9 = r5
            r10 = r3
            if (r9 >= r10) goto L_0x004f
            r9 = r4
            r10 = r7
            char r9 = r9[r10]
            r10 = r1
            r11 = r5
            char r10 = r10.charAt(r11)
            if (r9 == r10) goto L_0x0055
            r9 = 0
            r8 = r9
        L_0x004f:
            r9 = r8
            if (r9 == 0) goto L_0x0062
            r9 = 1
            r0 = r9
            goto L_0x001f
        L_0x0055:
            int r7 = r7 + 1
            r9 = r7
            r10 = r3
            if (r9 < r10) goto L_0x005f
            r9 = r7
            r10 = r3
            int r9 = r9 - r10
            r7 = r9
        L_0x005f:
            int r5 = r5 + 1
            goto L_0x003d
        L_0x0062:
            r9 = r0
            char r9 = r9.next()
            r6 = r9
            r9 = r6
            if (r9 != 0) goto L_0x0028
            r9 = 0
            r0 = r9
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.XMLTokener.skipPast(java.lang.String):boolean");
    }
}
