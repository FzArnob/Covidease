package gnu.expr;

import gnu.lists.Consumer;
import gnu.mapping.OutPort;
import gnu.mapping.SimpleSymbol;

public class Symbols {
    private static int gensym_counter;

    private Symbols() {
    }

    static synchronized int generateInt() {
        int i;
        synchronized (Symbols.class) {
            int i2 = gensym_counter + 1;
            i = i2;
            gensym_counter = i2;
        }
        return i;
    }

    public static final SimpleSymbol gentemp() {
        StringBuilder sb;
        new StringBuilder();
        return SimpleSymbol.valueOf(sb.append("GS.").append(Integer.toString(generateInt())).toString());
    }

    public static String make(String name) {
        return name.intern();
    }

    public static final String intern(String name) {
        return make(name);
    }

    public static void print(String str, Consumer consumer) {
        String name = str;
        Consumer out = consumer;
        if ((out instanceof OutPort) && ((OutPort) out).printReadable) {
            int len = name.length();
            for (int i = 0; i < len; i++) {
                char ch = name.charAt(i);
                if (!(Character.isLowerCase(ch) || ch == '!' || ch == '$' || ch == '%' || ch == '&' || ch == '*' || ch == '/' || ch == ':' || ch == '<' || ch == '=' || ch == '>' || ch == '?' || ch == '~' || ch == '_' || ch == '^' || (((ch == '+' || ch == '-') && (i > 0 || len == 1)) || ((Character.isDigit(ch) && i > 0) || (ch == '.' && (i == 0 || name.charAt(i - 1) == '.')))))) {
                    out.write(92);
                }
                out.write((int) ch);
            }
            return;
        }
        out.write(name);
    }
}
