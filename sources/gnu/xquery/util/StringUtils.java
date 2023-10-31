package gnu.xquery.util;

import gnu.bytecode.Access;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XIntegerType;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import gnu.text.URIPath;
import gnu.xml.TextUtils;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static String ERROR_VALUE = "<error>";

    public StringUtils() {
    }

    static String coerceToString(Object obj, String str, int i, String str2) {
        Object arg = obj;
        String functionName = str;
        int iarg = i;
        String onEmpty = str2;
        if (arg instanceof KNode) {
            arg = KNode.atomicValue(arg);
        }
        if ((arg == Values.empty || arg == null) && onEmpty != ERROR_VALUE) {
            return onEmpty;
        }
        if ((arg instanceof UntypedAtomic) || (arg instanceof CharSequence) || (arg instanceof URI) || (arg instanceof Path)) {
            return arg.toString();
        }
        WrongType wrongType = r11;
        WrongType wrongType2 = new WrongType(functionName, iarg, arg, onEmpty == ERROR_VALUE ? "xs:string" : "xs:string?");
        throw wrongType;
    }

    public static Object lowerCase(Object node) {
        return coerceToString(node, "lower-case", 1, "").toLowerCase();
    }

    public static Object upperCase(Object node) {
        return coerceToString(node, "upper-case", 1, "").toUpperCase();
    }

    static double asDouble(Object obj) {
        Object value = obj;
        if (!(value instanceof Number)) {
            value = NumberValue.numberValue(value);
        }
        return ((Number) value).doubleValue();
    }

    public static Object substring(Object obj, Object start) {
        Object str = obj;
        double d1 = asDouble(start);
        if (Double.isNaN(d1)) {
            return "";
        }
        int i = (int) (d1 - 0.5d);
        if (i < 0) {
            i = 0;
        }
        String s = coerceToString(str, "substring", 1, "");
        int len = s.length();
        int offset = 0;
        while (true) {
            i--;
            if (i < 0) {
                return s.substring(offset);
            }
            if (offset >= len) {
                return "";
            }
            int i2 = offset;
            offset++;
            char ch = s.charAt(i2);
            if (ch >= 55296 && ch < 56320 && offset < len) {
                offset++;
            }
        }
    }

    public static Object substring(Object str, Object start, Object length) {
        String s = coerceToString(str, "substring", 1, "");
        int len = s.length();
        double d1 = Math.floor(asDouble(start) - 0.5d);
        double d2 = d1 + Math.floor(asDouble(length) + 0.5d);
        if (d1 <= 0.0d) {
            d1 = 0.0d;
        }
        if (d2 > ((double) len)) {
            d2 = (double) len;
        }
        if (d2 <= d1) {
            return "";
        }
        int i1 = (int) d1;
        int i2 = ((int) d2) - i1;
        int offset = 0;
        while (true) {
            i1--;
            if (i1 < 0) {
                int i12 = offset;
                while (true) {
                    i2--;
                    if (i2 < 0) {
                        return s.substring(i12, offset);
                    } else if (offset >= len) {
                        return "";
                    } else {
                        int i = offset;
                        int offset2 = offset + 1;
                        char ch = s.charAt(i);
                        if (ch >= 55296 && ch < 56320 && offset2 < len) {
                            offset2++;
                        }
                    }
                }
            } else if (offset >= len) {
                return "";
            } else {
                int i3 = offset;
                offset++;
                char ch2 = s.charAt(i3);
                if (ch2 >= 55296 && ch2 < 56320 && offset < len) {
                    offset++;
                }
            }
        }
    }

    public static Object stringLength(Object str) {
        String s = coerceToString(str, "string-length", 1, "");
        int slen = s.length();
        int len = 0;
        int i = 0;
        while (i < slen) {
            int i2 = i;
            i++;
            char ch = s.charAt(i2);
            if (ch >= 55296 && ch < 56320 && i < slen) {
                i++;
            }
            len++;
        }
        return IntNum.make(len);
    }

    public static Object substringBefore(Object str, Object find) {
        String s = coerceToString(str, "substring-before", 1, "");
        String f = coerceToString(find, "substring-before", 2, "");
        if (f.length() == 0) {
            return "";
        }
        int start = s.indexOf(f);
        return start >= 0 ? s.substring(0, start) : "";
    }

    public static Object substringAfter(Object str, Object find) {
        Object s = coerceToString(str, "substring-after", 1, "");
        String f = coerceToString(find, "substring-after", 2, "");
        int flen = f.length();
        if (flen == 0) {
            return s;
        }
        int start = s.indexOf(f);
        return start >= 0 ? s.substring(start + flen) : "";
    }

    public static Object translate(Object obj, Object map, Object obj2) {
        StringBuffer stringBuffer;
        Throwable th;
        Throwable th2;
        Object str = obj;
        Object trans = obj2;
        Object sv = coerceToString(str, "translate", 1, "");
        Object map2 = KNode.atomicValue(map);
        if ((map2 instanceof UntypedAtomic) || (map2 instanceof String)) {
            String m = map2.toString();
            int mlen = m.length();
            Object trans2 = KNode.atomicValue(trans);
            if ((trans2 instanceof UntypedAtomic) || (trans2 instanceof String)) {
                String t = trans2.toString();
                if (mlen == 0) {
                    return sv;
                }
                int slen = sv.length();
                new StringBuffer(slen);
                StringBuffer s = stringBuffer;
                int tlen = t.length();
                int i = 0;
                while (i < slen) {
                    int i2 = i;
                    i++;
                    char c1 = sv.charAt(i2);
                    char c2 = 0;
                    if (c1 >= 55296 && c1 < 56320 && i < slen) {
                        int i3 = i;
                        i++;
                        c2 = sv.charAt(i3);
                    }
                    int j = 0;
                    int mi = 0;
                    while (true) {
                        if (mi >= mlen) {
                            break;
                        }
                        int i4 = mi;
                        mi++;
                        char m1 = m.charAt(i4);
                        char m2 = 0;
                        if (m1 >= 55296 && m1 < 56320 && mi < mlen) {
                            int i5 = mi;
                            mi++;
                            m2 = m.charAt(i5);
                        }
                        if (m1 == c1 && m2 == c2) {
                            int ti = 0;
                            while (true) {
                                if (ti >= tlen) {
                                    break;
                                }
                                int i6 = ti;
                                ti++;
                                char t1 = t.charAt(i6);
                                char t2 = 0;
                                if (t1 >= 55296 && t1 < 56320 && ti < tlen) {
                                    int i7 = ti;
                                    ti++;
                                    t2 = t.charAt(i7);
                                }
                                if (j == 0) {
                                    c1 = t1;
                                    c2 = t2;
                                    break;
                                }
                                j--;
                            }
                        } else {
                            j++;
                        }
                    }
                    StringBuffer append = s.append(c1);
                    if (c2 != 0) {
                        StringBuffer append2 = s.append(c2);
                    }
                }
                return s.toString();
            }
            Throwable th3 = th;
            new WrongType("translate", 3, str, "xs:string");
            throw th3;
        }
        Throwable th4 = th2;
        new WrongType("translate", 2, str, "xs:string");
        throw th4;
    }

    public static Object stringPad(Object obj, Object padcount) {
        StringBuffer stringBuffer;
        Throwable th;
        Object str = obj;
        int count = ((Number) NumberValue.numberValue(padcount)).intValue();
        if (count > 0) {
            String sv = coerceToString(str, "string-pad", 1, "");
            new StringBuffer(count * sv.length());
            StringBuffer s = stringBuffer;
            for (int i = 0; i < count; i++) {
                StringBuffer append = s.append(sv);
            }
            return s.toString();
        } else if (count == 0) {
            return "";
        } else {
            Throwable th2 = th;
            new IndexOutOfBoundsException("Invalid string-pad count");
            throw th2;
        }
    }

    public static Object contains(Object str, Object contain) {
        return coerceToString(str, "contains", 1, "").indexOf(coerceToString(contain, "contains", 2, "")) < 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Object startsWith(Object str, Object with) {
        return coerceToString(str, "starts-with", 1, "").startsWith(coerceToString(with, "starts-with", 2, "")) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object endsWith(Object str, Object with) {
        return coerceToString(str, "ends-with", 1, "").endsWith(coerceToString(with, "ends-with", 2, "")) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object stringJoin(Object obj, Object join) {
        StringBuffer stringBuffer;
        Object strseq = obj;
        new StringBuffer();
        StringBuffer s = stringBuffer;
        String glue = coerceToString(join, "string-join", 2, ERROR_VALUE);
        int glen = glue.length();
        int index = 0;
        boolean started = false;
        while (true) {
            int nextIndex = Values.nextIndex(strseq, index);
            int next = nextIndex;
            if (nextIndex < 0) {
                return s.toString();
            }
            Object obj2 = Values.nextValue(strseq, index);
            if (obj2 != Values.empty) {
                if (started && glen > 0) {
                    StringBuffer append = s.append(glue);
                }
                StringBuffer append2 = s.append(TextUtils.stringValue(obj2));
                started = true;
                index = next;
            }
        }
    }

    public static String concat$V(Object arg1, Object arg2, Object[] objArr) {
        StringBuilder sb;
        Object[] args = objArr;
        String str1 = TextUtils.stringValue(SequenceUtils.coerceToZeroOrOne(arg1, "concat", 1));
        String str2 = TextUtils.stringValue(SequenceUtils.coerceToZeroOrOne(arg2, "concat", 2));
        new StringBuilder(str1);
        StringBuilder result = sb;
        StringBuilder append = result.append(str2);
        int count = args.length;
        for (int i = 0; i < count; i++) {
            StringBuilder append2 = result.append(TextUtils.stringValue(SequenceUtils.coerceToZeroOrOne(args[i], "concat", i + 2)));
        }
        return result.toString();
    }

    public static Object compare(Object obj, Object obj2, NamedCollator namedCollator) {
        Object val1 = obj;
        Object val2 = obj2;
        NamedCollator coll = namedCollator;
        if (val1 == Values.empty || val1 == null || val2 == Values.empty || val2 == null) {
            return Values.empty;
        }
        if (coll == null) {
            coll = NamedCollator.codepointCollation;
        }
        int ret = coll.compare(val1.toString(), val2.toString());
        return ret < 0 ? IntNum.minusOne() : ret > 0 ? IntNum.one() : IntNum.zero();
    }

    public static void stringToCodepoints$X(Object arg, CallContext ctx) {
        String str = coerceToString(arg, "string-to-codepoints", 1, "");
        int len = str.length();
        Consumer out = ctx.consumer;
        int i = 0;
        while (i < len) {
            int i2 = i;
            i++;
            int ch = str.charAt(i2);
            if (ch >= 55296 && ch < 56320 && i < len) {
                int i3 = i;
                i++;
                ch = ((ch - 55296) * 1024) + (str.charAt(i3) - 56320) + 65536;
            }
            out.writeInt(ch);
        }
    }

    private static void appendCodepoint(Object code, StringBuffer stringBuffer) {
        Throwable th;
        StringBuilder sb;
        StringBuffer sbuf = stringBuffer;
        int i = ((IntNum) XIntegerType.integerType.cast(code)).intValue();
        if (i <= 0 || (i > 55295 && (i < 57344 || ((i > 65533 && i < 65536) || i > 1114111)))) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("codepoints-to-string: ").append(i).append(" is not a valid XML character [FOCH0001]").toString());
            throw th2;
        }
        if (i >= 65536) {
            StringBuffer append = sbuf.append((char) (((i - 65536) >> 10) + 55296));
            i = (i & 1023) + 56320;
        }
        StringBuffer append2 = sbuf.append((char) i);
    }

    public static String codepointsToString(Object obj) {
        StringBuffer stringBuffer;
        Object arg = obj;
        if (arg == null) {
            return "";
        }
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        if (arg instanceof Values) {
            Values vals = (Values) arg;
            int ipos = vals.startPos();
            while (true) {
                int nextPos = vals.nextPos(ipos);
                ipos = nextPos;
                if (nextPos == 0) {
                    break;
                }
                appendCodepoint(vals.getPosPrevious(ipos), sbuf);
            }
        } else {
            appendCodepoint(arg, sbuf);
        }
        return sbuf.toString();
    }

    public static String encodeForUri(Object arg) {
        return URIPath.encodeForUri(coerceToString(arg, "encode-for-uri", 1, ""), 'U');
    }

    public static String iriToUri(Object arg) {
        return URIPath.encodeForUri(coerceToString(arg, "iri-to-uru", 1, ""), Access.INNERCLASS_CONTEXT);
    }

    public static String escapeHtmlUri(Object arg) {
        return URIPath.encodeForUri(coerceToString(arg, "escape-html-uri", 1, ""), 'H');
    }

    public static String normalizeSpace(Object arg) {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        String str = coerceToString(arg, "normalize-space", 1, "");
        int len = str.length();
        StringBuffer sbuf = null;
        int skipped = 0;
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (Character.isWhitespace(ch)) {
                if (sbuf == null && skipped == 0 && i > 0) {
                    new StringBuffer(str.substring(0, i));
                    sbuf = stringBuffer2;
                }
                skipped++;
            } else {
                if (skipped > 0) {
                    if (sbuf != null) {
                        StringBuffer append = sbuf.append(' ');
                    } else if (skipped > 1 || i == 1 || str.charAt(i - 1) != ' ') {
                        new StringBuffer();
                        sbuf = stringBuffer;
                    }
                    skipped = 0;
                }
                if (sbuf != null) {
                    StringBuffer append2 = sbuf.append(ch);
                }
            }
        }
        return sbuf != null ? sbuf.toString() : skipped > 0 ? "" : str;
    }

    public static Pattern makePattern(String str, String str2) {
        StringBuffer stringBuffer;
        Throwable th;
        StringBuffer stringBuffer2;
        String pattern = str;
        String flags = str2;
        int fl = 0;
        int i = flags.length();
        while (true) {
            i--;
            if (i >= 0) {
                switch (flags.charAt(i)) {
                    case 'i':
                        fl |= 66;
                        break;
                    case 'm':
                        fl |= 8;
                        break;
                    case 's':
                        fl |= 32;
                        break;
                    case 'x':
                        new StringBuffer();
                        StringBuffer sbuf = stringBuffer2;
                        int plen = pattern.length();
                        int inBracket = 0;
                        int j = 0;
                        while (j < plen) {
                            int i2 = j;
                            j++;
                            char pch = pattern.charAt(i2);
                            if (pch == '\\' && j < plen) {
                                StringBuffer append = sbuf.append(pch);
                                int i3 = j;
                                j++;
                                pch = pattern.charAt(i3);
                            } else if (pch == '[') {
                                inBracket++;
                            } else if (pch == ']') {
                                inBracket--;
                            } else if (inBracket == 0 && Character.isWhitespace(pch)) {
                            }
                            StringBuffer append2 = sbuf.append(pch);
                        }
                        pattern = sbuf.toString();
                        break;
                    default:
                        Throwable th2 = th;
                        new IllegalArgumentException("unknown 'replace' flag");
                        throw th2;
                }
            } else {
                if (pattern.indexOf("{Is") >= 0) {
                    new StringBuffer();
                    StringBuffer sbuf2 = stringBuffer;
                    int plen2 = pattern.length();
                    int j2 = 0;
                    while (j2 < plen2) {
                        int i4 = j2;
                        j2++;
                        char pch2 = pattern.charAt(i4);
                        if (pch2 != '\\' || j2 + 4 >= plen2) {
                            StringBuffer append3 = sbuf2.append(pch2);
                        } else {
                            StringBuffer append4 = sbuf2.append(pch2);
                            int i5 = j2;
                            j2++;
                            char pch3 = pattern.charAt(i5);
                            StringBuffer append5 = sbuf2.append(pch3);
                            if ((pch3 == 'p' || pch3 == 'P') && pattern.charAt(j2) == '{' && pattern.charAt(j2 + 1) == 'I' && pattern.charAt(j2 + 2) == 's') {
                                StringBuffer append6 = sbuf2.append('{');
                                StringBuffer append7 = sbuf2.append(Access.INNERCLASS_CONTEXT);
                                StringBuffer append8 = sbuf2.append('n');
                                j2 += 3;
                            }
                        }
                    }
                    pattern = sbuf2.toString();
                }
                return Pattern.compile(pattern, fl);
            }
        }
    }

    public static boolean matches(Object input, String pattern) {
        return matches(input, pattern, "");
    }

    public static boolean matches(Object arg, String pattern, String flags) {
        return makePattern(pattern, flags).matcher(coerceToString(arg, "matches", 1, "")).find();
    }

    public static String replace(Object input, String pattern, String replacement) {
        return replace(input, pattern, replacement, "");
    }

    public static String replace(Object arg, String str, String str2, String str3) {
        Throwable th;
        String pattern = str;
        String replacement = str2;
        String flags = str3;
        String str4 = coerceToString(arg, "replace", 1, "");
        int rlen = replacement.length();
        int i = 0;
        while (i < rlen) {
            int i2 = i;
            i++;
            char rch = replacement.charAt(i2);
            if (rch == '\\') {
                if (i < rch) {
                    int i3 = i;
                    i++;
                    char charAt = replacement.charAt(i3);
                    char rch2 = charAt;
                    if (!(charAt == '\\' || rch2 == '$')) {
                    }
                }
                Throwable th2 = th;
                new IllegalArgumentException("invalid replacement string [FORX0004]");
                throw th2;
            }
        }
        return makePattern(pattern, flags).matcher(str4).replaceAll(replacement);
    }

    public static void tokenize$X(Object arg, String pattern, CallContext ctx) {
        tokenize$X(arg, pattern, "", ctx);
    }

    public static void tokenize$X(Object arg, String pattern, String flags, CallContext ctx) {
        Throwable th;
        String str = coerceToString(arg, "tokenize", 1, "");
        Consumer out = ctx.consumer;
        Matcher matcher = makePattern(pattern, flags).matcher(str);
        if (str.length() != 0) {
            int start = 0;
            while (matcher.find()) {
                int end = matcher.start();
                out.writeObject(str.substring(start, end));
                start = matcher.end();
                if (start == end) {
                    Throwable th2 = th;
                    new IllegalArgumentException("pattern matches empty string");
                    throw th2;
                }
            }
            out.writeObject(str.substring(start));
        }
    }

    public static Object codepointEqual(Object arg1, Object arg2) {
        String str1 = coerceToString(arg1, "codepoint-equal", 1, (String) null);
        String str2 = coerceToString(arg2, "codepoint-equal", 2, (String) null);
        if (str1 == null || str2 == null) {
            return Values.empty;
        }
        return str1.equals(str2) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object normalizeUnicode(Object arg) {
        return normalizeUnicode(arg, "NFC");
    }

    public static Object normalizeUnicode(Object arg, String form) {
        Throwable th;
        Object str = coerceToString(arg, "normalize-unicode", 1, "");
        if ("".equals(form.trim().toUpperCase())) {
            return str;
        }
        Throwable th2 = th;
        new UnsupportedOperationException("normalize-unicode: unicode string normalization not available");
        throw th2;
    }
}
