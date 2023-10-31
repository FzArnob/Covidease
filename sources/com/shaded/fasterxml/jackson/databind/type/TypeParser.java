package com.shaded.fasterxml.jackson.databind.type;

import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TypeParser implements Serializable {
    private static final long serialVersionUID = 1;
    protected final TypeFactory _factory;

    public TypeParser(TypeFactory typeFactory) {
        this._factory = typeFactory;
    }

    public JavaType parse(String str) throws IllegalArgumentException {
        MyTokenizer myTokenizer;
        new MyTokenizer(str.trim());
        MyTokenizer myTokenizer2 = myTokenizer;
        JavaType parseType = parseType(myTokenizer2);
        if (!myTokenizer2.hasMoreTokens()) {
            return parseType;
        }
        throw _problem(myTokenizer2, "Unexpected tokens after complete type");
    }

    /* access modifiers changed from: protected */
    public JavaType parseType(MyTokenizer myTokenizer) throws IllegalArgumentException {
        MyTokenizer myTokenizer2 = myTokenizer;
        if (!myTokenizer2.hasMoreTokens()) {
            throw _problem(myTokenizer2, "Unexpected end-of-string");
        }
        Class<?> findClass = findClass(myTokenizer2.nextToken(), myTokenizer2);
        if (myTokenizer2.hasMoreTokens()) {
            String nextToken = myTokenizer2.nextToken();
            if ("<".equals(nextToken)) {
                return this._factory._fromParameterizedClass(findClass, parseTypes(myTokenizer2));
            }
            myTokenizer2.pushBack(nextToken);
        }
        return this._factory._fromClass(findClass, (TypeBindings) null);
    }

    /* access modifiers changed from: protected */
    public List<JavaType> parseTypes(MyTokenizer myTokenizer) throws IllegalArgumentException {
        ArrayList arrayList;
        StringBuilder sb;
        MyTokenizer myTokenizer2 = myTokenizer;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        while (myTokenizer2.hasMoreTokens()) {
            boolean add = arrayList2.add(parseType(myTokenizer2));
            if (!myTokenizer2.hasMoreTokens()) {
                break;
            }
            String nextToken = myTokenizer2.nextToken();
            if (">".equals(nextToken)) {
                return arrayList2;
            }
            if (!",".equals(nextToken)) {
                new StringBuilder();
                throw _problem(myTokenizer2, sb.append("Unexpected token '").append(nextToken).append("', expected ',' or '>')").toString());
            }
        }
        throw _problem(myTokenizer2, "Unexpected end-of-string");
    }

    /* access modifiers changed from: protected */
    public Class<?> findClass(String str, MyTokenizer myTokenizer) {
        StringBuilder sb;
        String str2 = str;
        MyTokenizer myTokenizer2 = myTokenizer;
        try {
            return ClassUtil.findClass(str2);
        } catch (Exception e) {
            Exception exc = e;
            if (exc instanceof RuntimeException) {
                throw ((RuntimeException) exc);
            }
            new StringBuilder();
            throw _problem(myTokenizer2, sb.append("Can not locate class '").append(str2).append("', problem: ").append(exc.getMessage()).toString());
        }
    }

    /* access modifiers changed from: protected */
    public IllegalArgumentException _problem(MyTokenizer myTokenizer, String str) {
        IllegalArgumentException illegalArgumentException;
        StringBuilder sb;
        MyTokenizer myTokenizer2 = myTokenizer;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Failed to parse type '").append(myTokenizer2.getAllInput()).append("' (remaining: '").append(myTokenizer2.getRemainingInput()).append("'): ").append(str).toString());
        return illegalArgumentException;
    }

    static final class MyTokenizer extends StringTokenizer {
        protected int _index;
        protected final String _input;
        protected String _pushbackToken;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public MyTokenizer(java.lang.String r7) {
            /*
                r6 = this;
                r0 = r6
                r1 = r7
                r2 = r0
                r3 = r1
                java.lang.String r4 = "<,>"
                r5 = 1
                r2.<init>(r3, r4, r5)
                r2 = r0
                r3 = r1
                r2._input = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.type.TypeParser.MyTokenizer.<init>(java.lang.String):void");
        }

        public boolean hasMoreTokens() {
            return this._pushbackToken != null || super.hasMoreTokens();
        }

        public String nextToken() {
            String nextToken;
            if (this._pushbackToken != null) {
                nextToken = this._pushbackToken;
                this._pushbackToken = null;
            } else {
                nextToken = super.nextToken();
            }
            this._index += nextToken.length();
            return nextToken;
        }

        public void pushBack(String str) {
            String str2 = str;
            this._pushbackToken = str2;
            this._index -= str2.length();
        }

        public String getAllInput() {
            return this._input;
        }

        public String getUsedInput() {
            return this._input.substring(0, this._index);
        }

        public String getRemainingInput() {
            return this._input.substring(this._index);
        }
    }
}
