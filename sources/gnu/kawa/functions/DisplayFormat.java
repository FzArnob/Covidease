package gnu.kawa.functions;

import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import gnu.bytecode.Access;
import gnu.expr.Keyword;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.AbstractFormat;
import gnu.lists.Array;
import gnu.lists.CharSeq;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.ConsumerWriter;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.SimpleVector;
import gnu.lists.Strings;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.Values;
import gnu.math.IntNum;
import gnu.math.RatNum;
import gnu.text.Char;
import gnu.text.Printable;
import gnu.xml.XMLPrinter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URI;
import java.util.regex.Pattern;

public class DisplayFormat extends AbstractFormat {
    public static final ThreadLocation outBase;
    public static final ThreadLocation outRadix;
    static Pattern r5rsIdentifierMinusInteriorColons = Pattern.compile("(([a-zA-Z]|[!$%&*/:<=>?^_~])([a-zA-Z]|[!$%&*/<=>?^_~]|[0-9]|([-+.@]))*[:]?)|([-+]|[.][.][.])");
    char language;
    boolean readable;

    static {
        ThreadLocation threadLocation;
        ThreadLocation threadLocation2;
        new ThreadLocation("out-base");
        outBase = threadLocation;
        outBase.setGlobal(IntNum.ten());
        new ThreadLocation("out-radix");
        outRadix = threadLocation2;
    }

    public DisplayFormat(boolean readable2, char language2) {
        this.readable = readable2;
        this.language = language2;
    }

    public static DisplayFormat getEmacsLispFormat(boolean readable2) {
        DisplayFormat displayFormat;
        new DisplayFormat(readable2, 'E');
        return displayFormat;
    }

    public static DisplayFormat getCommonLispFormat(boolean readable2) {
        DisplayFormat displayFormat;
        new DisplayFormat(readable2, Access.CLASS_CONTEXT);
        return displayFormat;
    }

    public static DisplayFormat getSchemeFormat(boolean readable2) {
        DisplayFormat displayFormat;
        new DisplayFormat(readable2, 'S');
        return displayFormat;
    }

    public boolean getReadableOutput() {
        return this.readable;
    }

    public void writeBoolean(boolean z, Consumer out) {
        boolean v = z;
        write(this.language == 'S' ? v ? "#t" : "#f" : v ? "t" : "nil", out);
    }

    public void write(int i, Consumer consumer) {
        int v = i;
        Consumer out = consumer;
        if (!getReadableOutput()) {
            Char.print(v, out);
        } else if (this.language != 'E' || v <= 32) {
            write(Char.toScmReadableString(v), out);
        } else {
            out.write(63);
            Char.print(v, out);
        }
    }

    public void writeList(LList lList, OutPort outPort) {
        Object value = lList;
        OutPort out = outPort;
        Object list = value;
        out.startLogicalBlock("(", false, ")");
        while (list instanceof Pair) {
            if (list != value) {
                out.writeSpaceFill();
            }
            Pair pair = (Pair) list;
            writeObject(pair.getCar(), out);
            list = pair.getCdr();
        }
        if (list != LList.Empty) {
            out.writeSpaceFill();
            out.write(". ");
            writeObject(LList.checkNonList(list), out);
        }
        out.endLogicalBlock(")");
    }

    public void writeObject(Object obj, Consumer consumer) {
        Object obj2 = obj;
        Consumer out = consumer;
        boolean space = false;
        if ((out instanceof OutPort) && !(obj2 instanceof UntypedAtomic) && !(obj2 instanceof Values) && (getReadableOutput() || (!(obj2 instanceof Char) && !(obj2 instanceof CharSequence) && !(obj2 instanceof Character)))) {
            ((OutPort) out).writeWordStart();
            space = true;
        }
        writeObjectRaw(obj2, out);
        if (space) {
            ((OutPort) out).writeWordEnd();
        }
    }

    public void writeObjectRaw(Object obj, Consumer consumer) {
        String asString;
        StringBuilder sb;
        Writer writer;
        Writer wout;
        XMLPrinter xMLPrinter;
        StringBuilder sb2;
        String sb3;
        String start;
        String end;
        Object obj2 = obj;
        Consumer out = consumer;
        if (obj2 instanceof Boolean) {
            writeBoolean(((Boolean) obj2).booleanValue(), out);
        } else if (obj2 instanceof Char) {
            write(((Char) obj2).intValue(), out);
        } else if (obj2 instanceof Character) {
            write(((Character) obj2).charValue(), out);
        } else if (obj2 instanceof Symbol) {
            Symbol sym = (Symbol) obj2;
            if (sym.getNamespace() == XmlNamespace.HTML) {
                write("html:", out);
                write(sym.getLocalPart(), out);
                return;
            }
            writeSymbol(sym, out, this.readable);
        } else if ((obj2 instanceof URI) && getReadableOutput() && (out instanceof PrintWriter)) {
            write("#,(URI ", out);
            Strings.printQuoted(obj2.toString(), (PrintWriter) out, 1);
            out.write(41);
        } else if (obj2 instanceof CharSequence) {
            CharSequence str = (CharSequence) obj2;
            if (getReadableOutput() && (out instanceof PrintWriter)) {
                Strings.printQuoted(str, (PrintWriter) out, 1);
            } else if (obj2 instanceof String) {
                out.write((String) obj2);
            } else if (obj2 instanceof CharSeq) {
                CharSeq seq = (CharSeq) obj2;
                seq.consume(0, seq.size(), out);
            } else {
                int len = str.length();
                for (int i = 0; i < len; i++) {
                    out.write((int) str.charAt(i));
                }
            }
        } else if ((obj2 instanceof LList) && (out instanceof OutPort)) {
            writeList((LList) obj2, (OutPort) out);
        } else if (obj2 instanceof SimpleVector) {
            SimpleVector vec = (SimpleVector) obj2;
            String tag = vec.getTag();
            if (this.language == 'E') {
                start = "[";
                end = "]";
            } else {
                if (tag == null) {
                    sb3 = "#(";
                } else {
                    new StringBuilder();
                    sb3 = sb2.append("#").append(tag).append("(").toString();
                }
                start = sb3;
                end = ")";
            }
            if (out instanceof OutPort) {
                ((OutPort) out).startLogicalBlock(start, false, end);
            } else {
                write(start, out);
            }
            int endpos = vec.size() << 1;
            for (int ipos = 0; ipos < endpos; ipos += 2) {
                if (ipos > 0 && (out instanceof OutPort)) {
                    ((OutPort) out).writeSpaceFill();
                }
                if (!vec.consumeNext(ipos, out)) {
                    break;
                }
            }
            if (out instanceof OutPort) {
                ((OutPort) out).endLogicalBlock(end);
            } else {
                write(end, out);
            }
        } else if (obj2 instanceof Array) {
            int write = write((Array) obj2, 0, 0, out);
        } else if (obj2 instanceof KNode) {
            if (getReadableOutput()) {
                write("#", out);
            }
            if (out instanceof Writer) {
                wout = (Writer) out;
            } else {
                wout = writer;
                new ConsumerWriter(out);
            }
            new XMLPrinter(wout);
            XMLPrinter xout = xMLPrinter;
            xout.writeObject(obj2);
            xout.closeThis();
        } else if (obj2 == Values.empty && getReadableOutput()) {
            write("#!void", out);
        } else if (obj2 instanceof Consumable) {
            ((Consumable) obj2).consume(out);
        } else if (obj2 instanceof Printable) {
            ((Printable) obj2).print(out);
        } else if (obj2 instanceof RatNum) {
            int b = 10;
            boolean showRadix = false;
            Object base = outBase.get((Object) null);
            Object printRadix = outRadix.get((Object) null);
            if (printRadix != null && (printRadix == Boolean.TRUE || "yes".equals(printRadix.toString()))) {
                showRadix = true;
            }
            if (base instanceof Number) {
                b = ((IntNum) base).intValue();
            } else if (base != null) {
                b = Integer.parseInt(base.toString());
            }
            String asString2 = ((RatNum) obj2).toString(b);
            if (showRadix) {
                if (b == 16) {
                    write("#x", out);
                } else if (b == 8) {
                    write("#o", out);
                } else if (b == 2) {
                    write("#b", out);
                } else if (b != 10 || !(obj2 instanceof IntNum)) {
                    new StringBuilder();
                    write(sb.append("#").append(base).append("r").toString(), out);
                }
            }
            write(asString2, out);
            if (showRadix && b == 10 && (obj2 instanceof IntNum)) {
                write(".", out);
            }
        } else if (!(obj2 instanceof Enum) || !getReadableOutput()) {
            if (obj2 == null) {
                asString = null;
            } else if (obj2.getClass().isArray()) {
                int len2 = java.lang.reflect.Array.getLength(obj2);
                if (out instanceof OutPort) {
                    ((OutPort) out).startLogicalBlock("[", false, "]");
                } else {
                    write("[", out);
                }
                for (int i2 = 0; i2 < len2; i2++) {
                    if (i2 > 0) {
                        write(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, out);
                        if (out instanceof OutPort) {
                            ((OutPort) out).writeBreakFill();
                        }
                    }
                    writeObject(java.lang.reflect.Array.get(obj2, i2), out);
                }
                if (out instanceof OutPort) {
                    ((OutPort) out).endLogicalBlock("]");
                    return;
                } else {
                    write("]", out);
                    return;
                }
            } else {
                asString = obj2.toString();
            }
            if (asString == null) {
                write("#!null", out);
            } else {
                write(asString, out);
            }
        } else {
            write(obj2.getClass().getName(), out);
            write(":", out);
            write(((Enum) obj2).name(), out);
        }
    }

    /* access modifiers changed from: package-private */
    public int write(Array array, int i, int i2, Consumer consumer) {
        StringBuilder sb;
        String sb2;
        int write;
        Array array2 = array;
        int index = i;
        int level = i2;
        Consumer out = consumer;
        int rank = array2.rank();
        int count = 0;
        if (level > 0) {
            sb2 = "(";
        } else if (rank == 1) {
            sb2 = "#(";
        } else {
            new StringBuilder();
            sb2 = sb.append("#").append(rank).append("a(").toString();
        }
        String start = sb2;
        if (out instanceof OutPort) {
            ((OutPort) out).startLogicalBlock(start, false, ")");
        } else {
            write(start, out);
        }
        if (rank > 0) {
            int size = array2.getSize(level);
            int level2 = level + 1;
            for (int i3 = 0; i3 < size; i3++) {
                if (i3 > 0) {
                    write(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, out);
                    if (out instanceof OutPort) {
                        ((OutPort) out).writeBreakFill();
                    }
                }
                if (level2 == rank) {
                    writeObject(array2.getRowMajor(index), out);
                    write = 1;
                } else {
                    write = write(array2, index, level2, out);
                }
                int step = write;
                index += step;
                count += step;
            }
        }
        if (out instanceof OutPort) {
            ((OutPort) out).endLogicalBlock(")");
        } else {
            write(")", out);
        }
        return count;
    }

    /* access modifiers changed from: package-private */
    public void writeSymbol(Symbol symbol, Consumer consumer, boolean z) {
        Symbol sym = symbol;
        Consumer out = consumer;
        boolean readable2 = z;
        String prefix = sym.getPrefix();
        Namespace namespace = sym.getNamespace();
        String uri = namespace == null ? null : namespace.getName();
        boolean hasUri = uri != null && uri.length() > 0;
        boolean hasPrefix = prefix != null && prefix.length() > 0;
        boolean suffixColon = false;
        if (namespace == Keyword.keywordNamespace) {
            if (this.language == 'C' || this.language == 'E') {
                out.write(58);
            } else {
                suffixColon = true;
            }
        } else if (hasPrefix || hasUri) {
            if (hasPrefix) {
                writeSymbol(prefix, out, readable2);
            }
            if (hasUri && (readable2 || !hasPrefix)) {
                out.write(123);
                out.write(uri);
                out.write(125);
            }
            out.write(58);
        }
        writeSymbol(sym.getName(), out, readable2);
        if (suffixColon) {
            out.write(58);
        }
    }

    /* access modifiers changed from: package-private */
    public void writeSymbol(String str, Consumer consumer, boolean readable2) {
        String sym = str;
        Consumer out = consumer;
        if (!readable2 || r5rsIdentifierMinusInteriorColons.matcher(sym).matches()) {
            write(sym, out);
            return;
        }
        int len = sym.length();
        if (len == 0) {
            write("||", out);
            return;
        }
        boolean inVerticalBars = false;
        for (int i = 0; i < len; i++) {
            char ch = sym.charAt(i);
            if (ch == '|') {
                write(inVerticalBars ? "|\\" : "\\", out);
                inVerticalBars = false;
            } else if (!inVerticalBars) {
                out.write(124);
                inVerticalBars = true;
            }
            out.write((int) ch);
        }
        if (inVerticalBars) {
            out.write(124);
        }
    }
}
