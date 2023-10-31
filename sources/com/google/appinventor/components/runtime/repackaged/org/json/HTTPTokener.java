package com.google.appinventor.components.runtime.repackaged.org.json;

public class HTTPTokener extends JSONTokener {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HTTPTokener(String string) {
        super(string);
    }

    public String nextToken() throws JSONException {
        StringBuffer stringBuffer;
        char c;
        new StringBuffer();
        StringBuffer sb = stringBuffer;
        do {
            c = next();
        } while (Character.isWhitespace(c));
        if (c == '\"' || c == '\'') {
            char q = c;
            while (true) {
                char c2 = next();
                if (c2 < ' ') {
                    throw syntaxError("Unterminated string.");
                } else if (c2 == q) {
                    return sb.toString();
                } else {
                    StringBuffer append = sb.append(c2);
                }
            }
        } else {
            while (c != 0 && !Character.isWhitespace(c)) {
                StringBuffer append2 = sb.append(c);
                c = next();
            }
            return sb.toString();
        }
    }
}
