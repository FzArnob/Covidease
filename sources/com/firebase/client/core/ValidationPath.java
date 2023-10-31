package com.firebase.client.core;

import com.firebase.client.FirebaseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ValidationPath {
    public static final int MAX_PATH_DEPTH = 32;
    public static final int MAX_PATH_LENGTH_BYTES = 768;
    private int byteLength = 0;
    private final List<String> parts;

    private ValidationPath(Path path) throws FirebaseException {
        List<String> list;
        new ArrayList();
        this.parts = list;
        Iterator i$ = path.iterator();
        while (i$.hasNext()) {
            boolean add = this.parts.add(i$.next().asString());
        }
        this.byteLength = Math.max(1, this.parts.size());
        for (int i = 0; i < this.parts.size(); i++) {
            this.byteLength += utf8Bytes(this.parts.get(i));
        }
        checkValid();
    }

    public static void validateWithObject(Path path, Object value) throws FirebaseException {
        ValidationPath validationPath;
        new ValidationPath(path);
        validationPath.withObject(value);
    }

    private void withObject(Object obj) throws FirebaseException {
        Object value = obj;
        if (value instanceof Map) {
            Map<String, Object> mapValue = (Map) value;
            for (String key : mapValue.keySet()) {
                if (!key.startsWith(".")) {
                    push(key);
                    withObject(mapValue.get(key));
                    String pop = pop();
                }
            }
        } else if (value instanceof List) {
            List listValue = (List) value;
            for (int i = 0; i < listValue.size(); i++) {
                push(Integer.toString(i));
                withObject(listValue.get(i));
                String pop2 = pop();
            }
        }
    }

    private void push(String str) throws FirebaseException {
        String child = str;
        if (this.parts.size() > 0) {
            this.byteLength++;
        }
        boolean add = this.parts.add(child);
        this.byteLength += utf8Bytes(child);
        checkValid();
    }

    private String pop() {
        String last = this.parts.remove(this.parts.size() - 1);
        this.byteLength -= utf8Bytes(last);
        if (this.parts.size() > 0) {
            this.byteLength--;
        }
        return last;
    }

    private void checkValid() throws FirebaseException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        if (this.byteLength > 768) {
            Throwable th3 = th2;
            new StringBuilder();
            new FirebaseException(sb2.append("Data has a key path longer than 768 bytes (").append(this.byteLength).append(").").toString());
            throw th3;
        } else if (this.parts.size() > 32) {
            Throwable th4 = th;
            new StringBuilder();
            new FirebaseException(sb.append("Path specified exceeds the maximum depth that can be written (32) or object contains a cycle ").append(toErrorString()).toString());
            throw th4;
        }
    }

    private String toErrorString() {
        StringBuilder sb;
        if (this.parts.size() == 0) {
            return "";
        }
        new StringBuilder();
        return sb.append("in path '").append(joinStringList("/", this.parts)).append("'").toString();
    }

    private static String joinStringList(String str, List<String> list) {
        StringBuilder sb;
        String delimeter = str;
        List<String> parts2 = list;
        new StringBuilder();
        StringBuilder sb2 = sb;
        for (int i = 0; i < parts2.size(); i++) {
            if (i > 0) {
                StringBuilder append = sb2.append(delimeter);
            }
            StringBuilder append2 = sb2.append(parts2.get(i));
        }
        return sb2.toString();
    }

    private static int utf8Bytes(CharSequence charSequence) {
        CharSequence sequence = charSequence;
        int count = 0;
        int i = 0;
        int len = sequence.length();
        while (i < len) {
            char ch = sequence.charAt(i);
            if (ch <= 127) {
                count++;
            } else if (ch <= 2047) {
                count += 2;
            } else if (Character.isHighSurrogate(ch)) {
                count += 4;
                i++;
            } else {
                count += 3;
            }
            i++;
        }
        return count;
    }
}
