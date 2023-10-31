package gnu.lists;

import java.io.PrintWriter;

public class Strings {
    public Strings() {
    }

    public static void makeUpperCase(CharSeq charSeq) {
        CharSeq str = charSeq;
        int i = str.length();
        while (true) {
            i--;
            if (i >= 0) {
                str.setCharAt(i, Character.toUpperCase(str.charAt(i)));
            } else {
                return;
            }
        }
    }

    public static void makeLowerCase(CharSeq charSeq) {
        CharSeq str = charSeq;
        int i = str.length();
        while (true) {
            i--;
            if (i >= 0) {
                str.setCharAt(i, Character.toLowerCase(str.charAt(i)));
            } else {
                return;
            }
        }
    }

    public static void makeCapitalize(CharSeq charSeq) {
        char lowerCase;
        CharSeq str = charSeq;
        char prev = ' ';
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (!Character.isLetterOrDigit(prev)) {
                lowerCase = Character.toTitleCase(ch);
            } else {
                lowerCase = Character.toLowerCase(ch);
            }
            char ch2 = lowerCase;
            str.setCharAt(i, ch2);
            prev = ch2;
        }
    }

    public static void printQuoted(CharSequence charSequence, PrintWriter printWriter, int i) {
        CharSequence str = charSequence;
        PrintWriter ps = printWriter;
        int escapes = i;
        int len = str.length();
        ps.print('\"');
        for (int i2 = 0; i2 < len; i2++) {
            char ch = str.charAt(i2);
            if (ch == '\\' || ch == '\"') {
                ps.print('\\');
            } else if (escapes > 0) {
                if (ch == 10) {
                    ps.print("\\n");
                } else if (ch == 13) {
                    ps.print("\\r");
                } else if (ch == 9) {
                    ps.print("\\t");
                } else if (ch == 7) {
                    ps.print("\\a");
                } else if (ch == 8) {
                    ps.print("\\b");
                } else if (ch == 11) {
                    ps.print("\\v");
                } else if (ch == 12) {
                    ps.print("\\f");
                }
            }
            ps.print(ch);
        }
        ps.print('\"');
    }
}
