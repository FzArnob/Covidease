package com.google.appinventor.components.runtime.repackaged.org.json;

import java.util.Iterator;

public class JSONML {
    public JSONML() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01eb, code lost:
        throw r0.syntaxError("Reserved attribute.");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object parse(com.google.appinventor.components.runtime.repackaged.org.json.XMLTokener r16, boolean r17, com.google.appinventor.components.runtime.repackaged.org.json.JSONArray r18) throws com.google.appinventor.components.runtime.repackaged.org.json.JSONException {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            r11 = 0
            r3 = r11
            r11 = 0
            r4 = r11
            r11 = 0
            r5 = r11
            r11 = 0
            r6 = r11
        L_0x000e:
            r11 = r0
            boolean r11 = r11.more()
            if (r11 != 0) goto L_0x001e
            r11 = r0
            java.lang.String r12 = "Bad XML"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r11 = r11.syntaxError(r12)
            throw r11
        L_0x001e:
            r11 = r0
            java.lang.Object r11 = r11.nextContent()
            r7 = r11
            r11 = r7
            java.lang.Character r12 = com.google.appinventor.components.runtime.repackaged.org.json.XML.f322LT
            if (r11 != r12) goto L_0x02ac
            r11 = r0
            java.lang.Object r11 = r11.nextToken()
            r7 = r11
            r11 = r7
            boolean r11 = r11 instanceof java.lang.Character
            if (r11 == 0) goto L_0x0120
            r11 = r7
            java.lang.Character r12 = com.google.appinventor.components.runtime.repackaged.org.json.XML.SLASH
            if (r11 != r12) goto L_0x0081
            r11 = r0
            java.lang.Object r11 = r11.nextToken()
            r7 = r11
            r11 = r7
            boolean r11 = r11 instanceof java.lang.String
            if (r11 != 0) goto L_0x006c
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r11 = new com.google.appinventor.components.runtime.repackaged.org.json.JSONException
            r15 = r11
            r11 = r15
            r12 = r15
            java.lang.StringBuffer r13 = new java.lang.StringBuffer
            r15 = r13
            r13 = r15
            r14 = r15
            r14.<init>()
            java.lang.String r14 = "Expected a closing name instead of '"
            java.lang.StringBuffer r13 = r13.append(r14)
            r14 = r7
            java.lang.StringBuffer r13 = r13.append(r14)
            java.lang.String r14 = "'."
            java.lang.StringBuffer r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            r12.<init>((java.lang.String) r13)
            throw r11
        L_0x006c:
            r11 = r0
            java.lang.Object r11 = r11.nextToken()
            java.lang.Character r12 = com.google.appinventor.components.runtime.repackaged.org.json.XML.f321GT
            if (r11 == r12) goto L_0x007e
            r11 = r0
            java.lang.String r12 = "Misshaped close tag"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r11 = r11.syntaxError(r12)
            throw r11
        L_0x007e:
            r11 = r7
            r0 = r11
        L_0x0080:
            return r0
        L_0x0081:
            r11 = r7
            java.lang.Character r12 = com.google.appinventor.components.runtime.repackaged.org.json.XML.BANG
            if (r11 != r12) goto L_0x0108
            r11 = r0
            char r11 = r11.next()
            r8 = r11
            r11 = r8
            r12 = 45
            if (r11 != r12) goto L_0x00aa
            r11 = r0
            char r11 = r11.next()
            r12 = 45
            if (r11 != r12) goto L_0x00a4
            r11 = r0
            java.lang.String r12 = "-->"
            boolean r11 = r11.skipPast(r12)
            goto L_0x000e
        L_0x00a4:
            r11 = r0
            r11.back()
            goto L_0x000e
        L_0x00aa:
            r11 = r8
            r12 = 91
            if (r11 != r12) goto L_0x00e0
            r11 = r0
            java.lang.Object r11 = r11.nextToken()
            r7 = r11
            r11 = r7
            java.lang.String r12 = "CDATA"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x00d7
            r11 = r0
            char r11 = r11.next()
            r12 = 91
            if (r11 != r12) goto L_0x00d7
            r11 = r2
            if (r11 == 0) goto L_0x000e
            r11 = r2
            r12 = r0
            java.lang.String r12 = r12.nextCDATA()
            com.google.appinventor.components.runtime.repackaged.org.json.JSONArray r11 = r11.put((java.lang.Object) r12)
            goto L_0x000e
        L_0x00d7:
            r11 = r0
            java.lang.String r12 = "Expected 'CDATA['"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r11 = r11.syntaxError(r12)
            throw r11
        L_0x00e0:
            r11 = 1
            r9 = r11
        L_0x00e2:
            r11 = r0
            java.lang.Object r11 = r11.nextMeta()
            r7 = r11
            r11 = r7
            if (r11 != 0) goto L_0x00f4
            r11 = r0
            java.lang.String r12 = "Missing '>' after '<!'."
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r11 = r11.syntaxError(r12)
            throw r11
        L_0x00f4:
            r11 = r7
            java.lang.Character r12 = com.google.appinventor.components.runtime.repackaged.org.json.XML.f322LT
            if (r11 != r12) goto L_0x0100
            int r9 = r9 + 1
        L_0x00fb:
            r11 = r9
            if (r11 > 0) goto L_0x00e2
            goto L_0x000e
        L_0x0100:
            r11 = r7
            java.lang.Character r12 = com.google.appinventor.components.runtime.repackaged.org.json.XML.f321GT
            if (r11 != r12) goto L_0x00fb
            int r9 = r9 + -1
            goto L_0x00fb
        L_0x0108:
            r11 = r7
            java.lang.Character r12 = com.google.appinventor.components.runtime.repackaged.org.json.XML.QUEST
            if (r11 != r12) goto L_0x0117
            r11 = r0
            java.lang.String r12 = "?>"
            boolean r11 = r11.skipPast(r12)
            goto L_0x000e
        L_0x0117:
            r11 = r0
            java.lang.String r12 = "Misshaped tag"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r11 = r11.syntaxError(r12)
            throw r11
        L_0x0120:
            r11 = r7
            boolean r11 = r11 instanceof java.lang.String
            if (r11 != 0) goto L_0x014a
            r11 = r0
            java.lang.StringBuffer r12 = new java.lang.StringBuffer
            r15 = r12
            r12 = r15
            r13 = r15
            r13.<init>()
            java.lang.String r13 = "Bad tagName '"
            java.lang.StringBuffer r12 = r12.append(r13)
            r13 = r7
            java.lang.StringBuffer r12 = r12.append(r13)
            java.lang.String r13 = "'."
            java.lang.StringBuffer r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r11 = r11.syntaxError(r12)
            throw r11
        L_0x014a:
            r11 = r7
            java.lang.String r11 = (java.lang.String) r11
            r6 = r11
            com.google.appinventor.components.runtime.repackaged.org.json.JSONArray r11 = new com.google.appinventor.components.runtime.repackaged.org.json.JSONArray
            r15 = r11
            r11 = r15
            r12 = r15
            r12.<init>()
            r4 = r11
            com.google.appinventor.components.runtime.repackaged.org.json.JSONObject r11 = new com.google.appinventor.components.runtime.repackaged.org.json.JSONObject
            r15 = r11
            r11 = r15
            r12 = r15
            r12.<init>()
            r5 = r11
            r11 = r1
            if (r11 == 0) goto L_0x0189
            r11 = r4
            r12 = r6
            com.google.appinventor.components.runtime.repackaged.org.json.JSONArray r11 = r11.put((java.lang.Object) r12)
            r11 = r2
            if (r11 == 0) goto L_0x0172
            r11 = r2
            r12 = r4
            com.google.appinventor.components.runtime.repackaged.org.json.JSONArray r11 = r11.put((java.lang.Object) r12)
        L_0x0172:
            r11 = 0
            r7 = r11
        L_0x0174:
            r11 = r7
            if (r11 != 0) goto L_0x017d
            r11 = r0
            java.lang.Object r11 = r11.nextToken()
            r7 = r11
        L_0x017d:
            r11 = r7
            if (r11 != 0) goto L_0x019c
            r11 = r0
            java.lang.String r12 = "Misshaped tag"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r11 = r11.syntaxError(r12)
            throw r11
        L_0x0189:
            r11 = r5
            java.lang.String r12 = "tagName"
            r13 = r6
            com.google.appinventor.components.runtime.repackaged.org.json.JSONObject r11 = r11.put((java.lang.String) r12, (java.lang.Object) r13)
            r11 = r2
            if (r11 == 0) goto L_0x0172
            r11 = r2
            r12 = r5
            com.google.appinventor.components.runtime.repackaged.org.json.JSONArray r11 = r11.put((java.lang.Object) r12)
            goto L_0x0172
        L_0x019c:
            r11 = r7
            boolean r11 = r11 instanceof java.lang.String
            if (r11 != 0) goto L_0x01c8
            r11 = r1
            if (r11 == 0) goto L_0x01b1
            r11 = r5
            int r11 = r11.length()
            if (r11 <= 0) goto L_0x01b1
            r11 = r4
            r12 = r5
            com.google.appinventor.components.runtime.repackaged.org.json.JSONArray r11 = r11.put((java.lang.Object) r12)
        L_0x01b1:
            r11 = r7
            java.lang.Character r12 = com.google.appinventor.components.runtime.repackaged.org.json.XML.SLASH
            if (r11 != r12) goto L_0x0235
            r11 = r0
            java.lang.Object r11 = r11.nextToken()
            java.lang.Character r12 = com.google.appinventor.components.runtime.repackaged.org.json.XML.f321GT
            if (r11 == r12) goto L_0x0227
            r11 = r0
            java.lang.String r12 = "Misshaped tag"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r11 = r11.syntaxError(r12)
            throw r11
        L_0x01c8:
            r11 = r7
            java.lang.String r11 = (java.lang.String) r11
            r10 = r11
            r11 = r1
            if (r11 != 0) goto L_0x01ec
            java.lang.String r11 = "tagName"
            r12 = r10
            boolean r11 = r11.equals(r12)
            if (r11 != 0) goto L_0x01e3
            java.lang.String r11 = "childNode"
            r12 = r10
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x01ec
        L_0x01e3:
            r11 = r0
            java.lang.String r12 = "Reserved attribute."
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r11 = r11.syntaxError(r12)
            throw r11
        L_0x01ec:
            r11 = r0
            java.lang.Object r11 = r11.nextToken()
            r7 = r11
            r11 = r7
            java.lang.Character r12 = com.google.appinventor.components.runtime.repackaged.org.json.XML.f320EQ
            if (r11 != r12) goto L_0x021c
            r11 = r0
            java.lang.Object r11 = r11.nextToken()
            r7 = r11
            r11 = r7
            boolean r11 = r11 instanceof java.lang.String
            if (r11 != 0) goto L_0x020b
            r11 = r0
            java.lang.String r12 = "Missing value"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r11 = r11.syntaxError(r12)
            throw r11
        L_0x020b:
            r11 = r5
            r12 = r10
            r13 = r7
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r13 = com.google.appinventor.components.runtime.repackaged.org.json.XML.stringToValue(r13)
            com.google.appinventor.components.runtime.repackaged.org.json.JSONObject r11 = r11.accumulate(r12, r13)
            r11 = 0
            r7 = r11
            goto L_0x0174
        L_0x021c:
            r11 = r5
            r12 = r10
            java.lang.String r13 = ""
            com.google.appinventor.components.runtime.repackaged.org.json.JSONObject r11 = r11.accumulate(r12, r13)
            goto L_0x0174
        L_0x0227:
            r11 = r2
            if (r11 != 0) goto L_0x000e
            r11 = r1
            if (r11 == 0) goto L_0x0231
            r11 = r4
            r0 = r11
            goto L_0x0080
        L_0x0231:
            r11 = r5
            r0 = r11
            goto L_0x0080
        L_0x0235:
            r11 = r7
            java.lang.Character r12 = com.google.appinventor.components.runtime.repackaged.org.json.XML.f321GT
            if (r11 == r12) goto L_0x0243
            r11 = r0
            java.lang.String r12 = "Misshaped tag"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r11 = r11.syntaxError(r12)
            throw r11
        L_0x0243:
            r11 = r0
            r12 = r1
            r13 = r4
            java.lang.Object r11 = parse(r11, r12, r13)
            java.lang.String r11 = (java.lang.String) r11
            r3 = r11
            r11 = r3
            if (r11 == 0) goto L_0x000e
            r11 = r3
            r12 = r6
            boolean r11 = r11.equals(r12)
            if (r11 != 0) goto L_0x0289
            r11 = r0
            java.lang.StringBuffer r12 = new java.lang.StringBuffer
            r15 = r12
            r12 = r15
            r13 = r15
            r13.<init>()
            java.lang.String r13 = "Mismatched '"
            java.lang.StringBuffer r12 = r12.append(r13)
            r13 = r6
            java.lang.StringBuffer r12 = r12.append(r13)
            java.lang.String r13 = "' and '"
            java.lang.StringBuffer r12 = r12.append(r13)
            r13 = r3
            java.lang.StringBuffer r12 = r12.append(r13)
            java.lang.String r13 = "'"
            java.lang.StringBuffer r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r11 = r11.syntaxError(r12)
            throw r11
        L_0x0289:
            r11 = 0
            r6 = r11
            r11 = r1
            if (r11 != 0) goto L_0x029e
            r11 = r4
            int r11 = r11.length()
            if (r11 <= 0) goto L_0x029e
            r11 = r5
            java.lang.String r12 = "childNodes"
            r13 = r4
            com.google.appinventor.components.runtime.repackaged.org.json.JSONObject r11 = r11.put((java.lang.String) r12, (java.lang.Object) r13)
        L_0x029e:
            r11 = r2
            if (r11 != 0) goto L_0x000e
            r11 = r1
            if (r11 == 0) goto L_0x02a8
            r11 = r4
            r0 = r11
            goto L_0x0080
        L_0x02a8:
            r11 = r5
            r0 = r11
            goto L_0x0080
        L_0x02ac:
            r11 = r2
            if (r11 == 0) goto L_0x000e
            r11 = r2
            r12 = r7
            boolean r12 = r12 instanceof java.lang.String
            if (r12 == 0) goto L_0x02c2
            r12 = r7
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r12 = com.google.appinventor.components.runtime.repackaged.org.json.XML.stringToValue(r12)
        L_0x02bc:
            com.google.appinventor.components.runtime.repackaged.org.json.JSONArray r11 = r11.put((java.lang.Object) r12)
            goto L_0x000e
        L_0x02c2:
            r12 = r7
            goto L_0x02bc
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.JSONML.parse(com.google.appinventor.components.runtime.repackaged.org.json.XMLTokener, boolean, com.google.appinventor.components.runtime.repackaged.org.json.JSONArray):java.lang.Object");
    }

    public static JSONArray toJSONArray(String string) throws JSONException {
        XMLTokener xMLTokener;
        new XMLTokener(string);
        return toJSONArray(xMLTokener);
    }

    public static JSONArray toJSONArray(XMLTokener x) throws JSONException {
        return (JSONArray) parse(x, true, (JSONArray) null);
    }

    public static JSONObject toJSONObject(XMLTokener x) throws JSONException {
        return (JSONObject) parse(x, false, (JSONArray) null);
    }

    public static JSONObject toJSONObject(String string) throws JSONException {
        XMLTokener xMLTokener;
        new XMLTokener(string);
        return toJSONObject(xMLTokener);
    }

    public static String toString(JSONArray jSONArray) throws JSONException {
        StringBuffer stringBuffer;
        int i;
        JSONArray ja = jSONArray;
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        String tagName = ja.getString(0);
        XML.noSpace(tagName);
        String tagName2 = XML.escape(tagName);
        StringBuffer append = sb.append('<');
        StringBuffer append2 = sb.append(tagName2);
        Object object = ja.opt(1);
        if (object instanceof JSONObject) {
            i = 2;
            JSONObject jo = (JSONObject) object;
            Iterator keys = jo.keys();
            while (keys.hasNext()) {
                String key = keys.next().toString();
                XML.noSpace(key);
                String value = jo.optString(key);
                if (value != null) {
                    StringBuffer append3 = sb.append(' ');
                    StringBuffer append4 = sb.append(XML.escape(key));
                    StringBuffer append5 = sb.append('=');
                    StringBuffer append6 = sb.append('\"');
                    StringBuffer append7 = sb.append(XML.escape(value));
                    StringBuffer append8 = sb.append('\"');
                }
            }
        } else {
            i = 1;
        }
        int length = ja.length();
        if (i >= length) {
            StringBuffer append9 = sb.append('/');
            StringBuffer append10 = sb.append('>');
        } else {
            StringBuffer append11 = sb.append('>');
            do {
                Object object2 = ja.get(i);
                i++;
                if (object2 != null) {
                    if (object2 instanceof String) {
                        StringBuffer append12 = sb.append(XML.escape(object2.toString()));
                    } else if (object2 instanceof JSONObject) {
                        StringBuffer append13 = sb.append(toString((JSONObject) object2));
                    } else if (object2 instanceof JSONArray) {
                        StringBuffer append14 = sb.append(toString((JSONArray) object2));
                    }
                }
            } while (i < length);
            StringBuffer append15 = sb.append('<');
            StringBuffer append16 = sb.append('/');
            StringBuffer append17 = sb.append(tagName2);
            StringBuffer append18 = sb.append('>');
        }
        return sb.toString();
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        StringBuffer stringBuffer;
        JSONObject jo = jSONObject;
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        String tagName = jo.optString("tagName");
        if (tagName == null) {
            return XML.escape(jo.toString());
        }
        XML.noSpace(tagName);
        String tagName2 = XML.escape(tagName);
        StringBuffer append = sb.append('<');
        StringBuffer append2 = sb.append(tagName2);
        Iterator keys = jo.keys();
        while (keys.hasNext()) {
            String key = keys.next().toString();
            if (!"tagName".equals(key) && !"childNodes".equals(key)) {
                XML.noSpace(key);
                String value = jo.optString(key);
                if (value != null) {
                    StringBuffer append3 = sb.append(' ');
                    StringBuffer append4 = sb.append(XML.escape(key));
                    StringBuffer append5 = sb.append('=');
                    StringBuffer append6 = sb.append('\"');
                    StringBuffer append7 = sb.append(XML.escape(value));
                    StringBuffer append8 = sb.append('\"');
                }
            }
        }
        JSONArray ja = jo.optJSONArray("childNodes");
        if (ja == null) {
            StringBuffer append9 = sb.append('/');
            StringBuffer append10 = sb.append('>');
        } else {
            StringBuffer append11 = sb.append('>');
            int length = ja.length();
            for (int i = 0; i < length; i++) {
                Object object = ja.get(i);
                if (object != null) {
                    if (object instanceof String) {
                        StringBuffer append12 = sb.append(XML.escape(object.toString()));
                    } else if (object instanceof JSONObject) {
                        StringBuffer append13 = sb.append(toString((JSONObject) object));
                    } else if (object instanceof JSONArray) {
                        StringBuffer append14 = sb.append(toString((JSONArray) object));
                    } else {
                        StringBuffer append15 = sb.append(object.toString());
                    }
                }
            }
            StringBuffer append16 = sb.append('<');
            StringBuffer append17 = sb.append('/');
            StringBuffer append18 = sb.append(tagName2);
            StringBuffer append19 = sb.append('>');
        }
        return sb.toString();
    }
}
