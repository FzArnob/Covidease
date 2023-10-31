package gnu.xml;

import gnu.kawa.xml.KNode;
import gnu.lists.Consumer;
import gnu.lists.TreeList;
import gnu.mapping.Values;
import gnu.math.DFloNum;
import java.math.BigDecimal;

public class TextUtils {
    public TextUtils() {
    }

    public static String asString(Object obj) {
        StringBuffer stringBuffer;
        Throwable th;
        Object node = obj;
        if (node == Values.empty || node == null) {
            return "";
        }
        if (node instanceof Values) {
            Throwable th2 = th;
            new ClassCastException();
            throw th2;
        }
        new StringBuffer(100);
        StringBuffer sbuf = stringBuffer;
        stringValue(node, sbuf);
        return sbuf.toString();
    }

    public static String stringValue(Object obj) {
        StringBuffer stringBuffer;
        Object node = obj;
        new StringBuffer(100);
        StringBuffer sbuf = stringBuffer;
        if (node instanceof Values) {
            TreeList tlist = (TreeList) node;
            int i = 0;
            while (true) {
                int index = i;
                int kind = tlist.getNextKind(index);
                if (kind == 0) {
                    break;
                }
                if (kind == 32) {
                    stringValue(tlist.getPosNext(index), sbuf);
                } else {
                    int stringValue = tlist.stringValue(tlist.posToDataIndex(index), sbuf);
                }
                i = tlist.nextPos(index);
            }
        } else {
            stringValue(node, sbuf);
        }
        return sbuf.toString();
    }

    public static void stringValue(Object obj, StringBuffer stringBuffer) {
        Object node = obj;
        StringBuffer sbuf = stringBuffer;
        if (node instanceof KNode) {
            KNode pos = (KNode) node;
            NodeTree tlist = (NodeTree) pos.sequence;
            int stringValue = tlist.stringValue(tlist.posToDataIndex(pos.ipos), sbuf);
            return;
        }
        if (node instanceof BigDecimal) {
            node = XMLPrinter.formatDecimal((BigDecimal) node);
        } else if ((node instanceof Double) || (node instanceof DFloNum)) {
            node = XMLPrinter.formatDouble(((Number) node).doubleValue());
        } else if (node instanceof Float) {
            node = XMLPrinter.formatFloat(((Number) node).floatValue());
        }
        if (node != null && node != Values.empty) {
            StringBuffer append = sbuf.append(node);
        }
    }

    public static void textValue(Object obj, Consumer consumer) {
        StringBuffer stringBuffer;
        String str;
        Object arg = obj;
        Consumer out = consumer;
        if (arg == null) {
            return;
        }
        if (!(arg instanceof Values) || !((Values) arg).isEmpty()) {
            if (arg instanceof String) {
                str = (String) arg;
            } else {
                new StringBuffer();
                StringBuffer sbuf = stringBuffer;
                if (arg instanceof Values) {
                    Object[] vals = ((Values) arg).getValues();
                    for (int i = 0; i < vals.length; i++) {
                        if (i > 0) {
                            StringBuffer append = sbuf.append(' ');
                        }
                        stringValue(vals[i], sbuf);
                    }
                } else {
                    stringValue(arg, sbuf);
                }
                str = sbuf.toString();
            }
            out.write(str);
        }
    }

    public static String replaceWhitespace(String str, boolean z) {
        int i;
        StringBuilder sb;
        String str2 = str;
        boolean collapse = z;
        StringBuilder sbuf = null;
        int len = str2.length();
        int prevSpace = collapse ? 1 : 0;
        int i2 = 0;
        while (i2 < len) {
            int i3 = i2;
            i2++;
            char ch = str2.charAt(i3);
            if (ch == ' ') {
                i = 1;
            } else {
                i = (ch == 9 || ch == 13 || ch == 10) ? 2 : 0;
            }
            int isSpace = i;
            if (sbuf == null && (isSpace == 2 || ((isSpace == 1 && prevSpace > 0 && collapse) || (isSpace == 1 && i2 == len && collapse)))) {
                new StringBuilder();
                sbuf = sb;
                int k = prevSpace > 0 ? i2 - 2 : i2 - 1;
                for (int j = 0; j < k; j++) {
                    StringBuilder append = sbuf.append(str2.charAt(j));
                }
                ch = ' ';
            }
            if (collapse) {
                if (prevSpace > 0 && isSpace == 0) {
                    if (sbuf != null && sbuf.length() > 0) {
                        StringBuilder append2 = sbuf.append(' ');
                    }
                    prevSpace = 0;
                } else if (isSpace == 2 || (isSpace == 1 && prevSpace > 0)) {
                    prevSpace = 2;
                } else if (isSpace > 0) {
                    prevSpace = 1;
                } else {
                    prevSpace = 0;
                }
                if (prevSpace > 0) {
                }
            }
            if (sbuf != null) {
                StringBuilder append3 = sbuf.append(ch);
            }
        }
        if (sbuf != null) {
            return sbuf.toString();
        }
        return str2;
    }
}
