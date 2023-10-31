package gnu.kawa.functions;

import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import java.text.BreakIterator;

public class UnicodeUtils {

    /* renamed from: Cc */
    static final Symbol f67Cc;

    /* renamed from: Cf */
    static final Symbol f68Cf;

    /* renamed from: Cn */
    static final Symbol f69Cn;

    /* renamed from: Co */
    static final Symbol f70Co;

    /* renamed from: Cs */
    static final Symbol f71Cs;

    /* renamed from: Ll */
    static final Symbol f72Ll;

    /* renamed from: Lm */
    static final Symbol f73Lm;

    /* renamed from: Lo */
    static final Symbol f74Lo;

    /* renamed from: Lt */
    static final Symbol f75Lt;

    /* renamed from: Lu */
    static final Symbol f76Lu;

    /* renamed from: Mc */
    static final Symbol f77Mc;

    /* renamed from: Me */
    static final Symbol f78Me;

    /* renamed from: Mn */
    static final Symbol f79Mn;

    /* renamed from: Nd */
    static final Symbol f80Nd;

    /* renamed from: Nl */
    static final Symbol f81Nl;

    /* renamed from: No */
    static final Symbol f82No;

    /* renamed from: Pc */
    static final Symbol f83Pc;

    /* renamed from: Pd */
    static final Symbol f84Pd;

    /* renamed from: Pe */
    static final Symbol f85Pe;

    /* renamed from: Pf */
    static final Symbol f86Pf;

    /* renamed from: Pi */
    static final Symbol f87Pi;

    /* renamed from: Po */
    static final Symbol f88Po;

    /* renamed from: Ps */
    static final Symbol f89Ps;

    /* renamed from: Sc */
    static final Symbol f90Sc;

    /* renamed from: Sk */
    static final Symbol f91Sk;

    /* renamed from: Sm */
    static final Symbol f92Sm;

    /* renamed from: So */
    static final Symbol f93So;

    /* renamed from: Zl */
    static final Symbol f94Zl;

    /* renamed from: Zp */
    static final Symbol f95Zp;

    /* renamed from: Zs */
    static final Symbol f96Zs;

    public UnicodeUtils() {
    }

    public static boolean isWhitespace(int i) {
        int ch = i;
        if (ch == 32 || (ch >= 9 && ch <= 13)) {
            return true;
        }
        if (ch < 133) {
            return false;
        }
        if (ch == 133 || ch == 160 || ch == 5760 || ch == 6158) {
            return true;
        }
        if (ch < 8192 || ch > 12288) {
            return false;
        }
        return ch <= 8202 || ch == 8232 || ch == 8233 || ch == 8239 || ch == 8287 || ch == 12288;
    }

    public static String capitalize(String str) {
        StringBuilder sb;
        String str2 = str;
        new StringBuilder();
        StringBuilder sbuf = sb;
        BreakIterator wb = BreakIterator.getWordInstance();
        wb.setText(str2);
        int start = wb.first();
        int next = wb.next();
        while (true) {
            int end = next;
            if (end == -1) {
                return sbuf.toString();
            }
            boolean isWord = false;
            int p = start;
            while (true) {
                if (p >= end) {
                    break;
                } else if (Character.isLetter(str2.codePointAt(p))) {
                    isWord = true;
                    break;
                } else {
                    p++;
                }
            }
            if (!isWord) {
                StringBuilder append = sbuf.append(str2, start, end);
            } else {
                StringBuilder append2 = sbuf.append(Character.toTitleCase(str2.charAt(start)));
                StringBuilder append3 = sbuf.append(str2.substring(start + 1, end).toLowerCase());
            }
            start = end;
            next = wb.next();
        }
    }

    public static String foldCase(CharSequence charSequence) {
        StringBuilder sb;
        CharSequence str = charSequence;
        int len = str.length();
        if (len == 0) {
            return "";
        }
        StringBuilder sbuf = null;
        int start = 0;
        int i = 0;
        while (true) {
            int ch = i == len ? -1 : str.charAt(i);
            boolean sigma = ch == 931 || ch == 963 || ch == 962;
            if (ch < 0 || ch == 304 || ch == 305 || sigma) {
                if (sbuf == null && ch >= 0) {
                    new StringBuilder();
                    sbuf = sb;
                }
                if (i > start) {
                    String converted = str.subSequence(start, i).toString().toUpperCase().toLowerCase();
                    if (sbuf == null) {
                        return converted;
                    }
                    StringBuilder append = sbuf.append(converted);
                }
                if (ch < 0) {
                    return sbuf.toString();
                }
                if (sigma) {
                    ch = 963;
                }
                StringBuilder append2 = sbuf.append((char) ch);
                start = i + 1;
            }
            i++;
        }
    }

    public static Symbol generalCategory(int ch) {
        switch (Character.getType(ch)) {
            case 1:
                return f76Lu;
            case 2:
                return f72Ll;
            case 3:
                return f75Lt;
            case 4:
                return f73Lm;
            case 5:
                return f74Lo;
            case 6:
                return f79Mn;
            case 7:
                return f78Me;
            case 8:
                return f77Mc;
            case 9:
                return f80Nd;
            case 10:
                return f81Nl;
            case 11:
                return f82No;
            case 12:
                return f96Zs;
            case 13:
                return f94Zl;
            case 14:
                return f95Zp;
            case 15:
                return f67Cc;
            case 16:
                return f68Cf;
            case 18:
                return f70Co;
            case 19:
                return f71Cs;
            case 20:
                return f84Pd;
            case 21:
                return f89Ps;
            case 22:
                return f85Pe;
            case 23:
                return f83Pc;
            case 24:
                return f88Po;
            case 25:
                return f92Sm;
            case 26:
                return f90Sc;
            case 27:
                return f91Sk;
            case 28:
                return f93So;
            case 29:
                return f87Pi;
            case 30:
                return f86Pf;
            default:
                return f69Cn;
        }
    }

    static {
        Namespace empty = Namespace.EmptyNamespace;
        f77Mc = empty.getSymbol("Mc");
        f83Pc = empty.getSymbol("Pc");
        f67Cc = empty.getSymbol("Cc");
        f90Sc = empty.getSymbol("Sc");
        f84Pd = empty.getSymbol("Pd");
        f80Nd = empty.getSymbol("Nd");
        f78Me = empty.getSymbol("Me");
        f85Pe = empty.getSymbol("Pe");
        f86Pf = empty.getSymbol("Pf");
        f68Cf = empty.getSymbol("Cf");
        f87Pi = empty.getSymbol("Pi");
        f81Nl = empty.getSymbol("Nl");
        f94Zl = empty.getSymbol("Zl");
        f72Ll = empty.getSymbol("Ll");
        f92Sm = empty.getSymbol("Sm");
        f73Lm = empty.getSymbol("Lm");
        f91Sk = empty.getSymbol("Sk");
        f79Mn = empty.getSymbol("Mn");
        f74Lo = empty.getSymbol("Lo");
        f82No = empty.getSymbol("No");
        f88Po = empty.getSymbol("Po");
        f93So = empty.getSymbol("So");
        f95Zp = empty.getSymbol("Zp");
        f70Co = empty.getSymbol("Co");
        f96Zs = empty.getSymbol("Zs");
        f89Ps = empty.getSymbol("Ps");
        f71Cs = empty.getSymbol("Cs");
        f75Lt = empty.getSymbol("Lt");
        f69Cn = empty.getSymbol("Cn");
        f76Lu = empty.getSymbol("Lu");
    }
}
