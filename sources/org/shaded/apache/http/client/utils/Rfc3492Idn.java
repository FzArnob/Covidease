package org.shaded.apache.http.client.utils;

import java.util.StringTokenizer;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class Rfc3492Idn implements Idn {
    private static final String ACE_PREFIX = "xn--";
    private static final int base = 36;
    private static final int damp = 700;
    private static final char delimiter = '-';
    private static final int initial_bias = 72;
    private static final int initial_n = 128;
    private static final int skew = 38;
    private static final int tmax = 26;
    private static final int tmin = 1;

    public Rfc3492Idn() {
    }

    private int adapt(int i, int i2, boolean firsttime) {
        int delta;
        int delta2 = i;
        int numpoints = i2;
        if (firsttime) {
            delta = delta2 / damp;
        } else {
            delta = delta2 / 2;
        }
        int delta3 = delta + (delta / numpoints);
        int i3 = 0;
        while (true) {
            int k = i3;
            if (delta3 <= 455) {
                return k + ((36 * delta3) / (delta3 + 38));
            }
            delta3 /= 35;
            i3 = k + 36;
        }
    }

    private int digit(char c) {
        Throwable th;
        StringBuilder sb;
        char c2 = c;
        if (c2 >= 'A' && c2 <= 'Z') {
            return c2 - 'A';
        }
        if (c2 >= 'a' && c2 <= 'z') {
            return c2 - 'a';
        }
        if (c2 >= '0' && c2 <= '9') {
            return (c2 - '0') + 26;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("illegal digit: ").append(c2).toString());
        throw th2;
    }

    public String toUnicode(String str) {
        StringBuilder sb;
        StringTokenizer stringTokenizer;
        String punycode = str;
        new StringBuilder(punycode.length());
        StringBuilder unicode = sb;
        new StringTokenizer(punycode, ".");
        StringTokenizer tok = stringTokenizer;
        while (tok.hasMoreTokens()) {
            String t = tok.nextToken();
            if (unicode.length() > 0) {
                StringBuilder append = unicode.append('.');
            }
            if (t.startsWith(ACE_PREFIX)) {
                t = decode(t.substring(4));
            }
            StringBuilder append2 = unicode.append(t);
        }
        return unicode.toString();
    }

    /* access modifiers changed from: protected */
    public String decode(String str) {
        StringBuilder sb;
        int t;
        String input = str;
        int n = 128;
        int i = 0;
        int bias = 72;
        new StringBuilder(input.length());
        StringBuilder output = sb;
        int lastdelim = input.lastIndexOf(45);
        if (lastdelim != -1) {
            StringBuilder append = output.append(input.subSequence(0, lastdelim));
            input = input.substring(lastdelim + 1);
        }
        while (input.length() > 0) {
            int oldi = i;
            int w = 1;
            int k = 36;
            while (input.length() != 0) {
                char c = input.charAt(0);
                input = input.substring(1);
                int digit = digit(c);
                i += digit * w;
                if (k <= bias + 1) {
                    t = 1;
                } else if (k >= bias + 26) {
                    t = 26;
                } else {
                    t = k - bias;
                }
                if (digit < t) {
                    break;
                }
                w *= 36 - t;
                k += 36;
            }
            bias = adapt(i - oldi, output.length() + 1, oldi == 0);
            n += i / (output.length() + 1);
            int i2 = i % (output.length() + 1);
            StringBuilder insert = output.insert(i2, (char) n);
            i = i2 + 1;
        }
        return output.toString();
    }
}
