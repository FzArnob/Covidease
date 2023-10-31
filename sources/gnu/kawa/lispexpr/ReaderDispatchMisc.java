package gnu.kawa.lispexpr;

import gnu.bytecode.Access;
import gnu.bytecode.Type;
import gnu.expr.Keyword;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.util.GeneralHashTable;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.util.regex.Pattern;

public class ReaderDispatchMisc extends ReadTableEntry {
    private static ReaderDispatchMisc instance;
    protected int code;

    static {
        ReaderDispatchMisc readerDispatchMisc;
        new ReaderDispatchMisc();
        instance = readerDispatchMisc;
    }

    public static ReaderDispatchMisc getInstance() {
        return instance;
    }

    public ReaderDispatchMisc() {
        this.code = -1;
    }

    public ReaderDispatchMisc(int code2) {
        this.code = code2;
    }

    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        GeneralHashTable<Integer, Object> map;
        Object object;
        GeneralHashTable<Integer, Object> generalHashTable;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        String str;
        Object in = lexer;
        int ch = i;
        int count = i2;
        LispReader reader = (LispReader) in;
        char saveReadState = 0;
        if (this.code >= 0) {
            ch = this.code;
        }
        switch (ch) {
            case 33:
                return LispReader.readSpecial(reader);
            case 35:
                if ((in instanceof LispReader) && (map = ((LispReader) in).sharedStructureTable) != null && (object = map.get(Integer.valueOf(count), in)) != in) {
                    return object;
                }
                in.error("an unrecognized #n# back-reference was read");
                return Values.empty;
            case 44:
                if (reader.getPort().peek() == 40) {
                    Object readObject = reader.readObject();
                    Object list = readObject;
                    int listLength = LList.listLength(readObject, false);
                    int length = listLength;
                    if (listLength > 0 && (((Pair) list).getCar() instanceof Symbol)) {
                        String name = ((Pair) list).getCar().toString();
                        Object proc = ReadTable.getCurrent().getReaderCtor(name);
                        if (proc == null) {
                            new StringBuilder();
                            in.error(sb2.append("unknown reader constructor ").append(name).toString());
                        } else if ((proc instanceof Procedure) || (proc instanceof Type)) {
                            int length2 = length - 1;
                            int parg = proc instanceof Type ? 1 : 0;
                            Object[] args = new Object[(parg + length2)];
                            Object argList = ((Pair) list).getCdr();
                            for (int i3 = 0; i3 < length2; i3++) {
                                Pair pair = (Pair) argList;
                                args[parg + i3] = pair.getCar();
                                argList = pair.getCdr();
                            }
                            if (parg <= 0) {
                                return ((Procedure) proc).applyN(args);
                            }
                            try {
                                args[0] = proc;
                                return Invoke.make.applyN(args);
                            } catch (Throwable th) {
                                new StringBuilder();
                                in.error(sb.append("caught ").append(th).append(" applying reader constructor ").append(name).toString());
                            }
                        } else {
                            in.error("reader constructor must be procedure or type name");
                        }
                        return Boolean.FALSE;
                    }
                }
                in.error("a non-empty list starting with a symbol must follow #,");
                return Boolean.FALSE;
            case 47:
                return readRegex(in, ch, count);
            case 58:
                int startPos = reader.tokenBufferLength;
                reader.readToken(reader.read(), 'P', ReadTable.getCurrent());
                new String(reader.tokenBuffer, startPos, reader.tokenBufferLength - startPos);
                String name2 = str;
                reader.tokenBufferLength = startPos;
                return Keyword.make(name2.intern());
            case 59:
                LineBufferedReader port = reader.getPort();
                if (port instanceof InPort) {
                    saveReadState = ((InPort) port).readState;
                    ((InPort) port).readState = ';';
                }
                try {
                    Object readObject2 = reader.readObject();
                    if (port instanceof InPort) {
                        ((InPort) port).readState = saveReadState;
                    }
                    return Values.empty;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    if (port instanceof InPort) {
                        ((InPort) port).readState = saveReadState;
                    }
                    throw th3;
                }
            case 61:
                Object object2 = reader.readObject();
                if (in instanceof LispReader) {
                    LispReader lin = (LispReader) in;
                    GeneralHashTable<Integer, Object> map2 = lin.sharedStructureTable;
                    if (map2 == null) {
                        new GeneralHashTable<>();
                        map2 = generalHashTable;
                        lin.sharedStructureTable = map2;
                    }
                    Object put = map2.put(Integer.valueOf(count), object2);
                }
                return object2;
            case 66:
                return LispReader.readNumberWithRadix(0, reader, 2);
            case 68:
                return LispReader.readNumberWithRadix(0, reader, 10);
            case 69:
            case 73:
                reader.tokenBufferAppend(35);
                reader.tokenBufferAppend(ch);
                return LispReader.readNumberWithRadix(2, reader, 0);
            case 70:
                if (Character.isDigit((char) in.peek())) {
                    return LispReader.readSimpleVector(reader, Access.FIELD_CONTEXT);
                }
                return Boolean.FALSE;
            case 79:
                return LispReader.readNumberWithRadix(0, reader, 8);
            case 82:
                if (count > 36) {
                    new StringBuilder();
                    in.error(sb3.append("the radix ").append(count).append(" is too big (max is 36)").toString());
                    count = 36;
                }
                return LispReader.readNumberWithRadix(0, reader, count);
            case 83:
            case 85:
                return LispReader.readSimpleVector(reader, (char) ch);
            case 84:
                return Boolean.TRUE;
            case 88:
                return LispReader.readNumberWithRadix(0, reader, 16);
            case 92:
                return LispReader.readCharacter(reader);
            case 124:
                LineBufferedReader port2 = reader.getPort();
                if (port2 instanceof InPort) {
                    saveReadState = ((InPort) port2).readState;
                    ((InPort) port2).readState = '|';
                }
                try {
                    reader.readNestedComment('#', '|');
                    if (port2 instanceof InPort) {
                        ((InPort) port2).readState = saveReadState;
                    }
                    return Values.empty;
                } catch (Throwable th4) {
                    Throwable th5 = th4;
                    if (port2 instanceof InPort) {
                        ((InPort) port2).readState = saveReadState;
                    }
                    throw th5;
                }
            default:
                in.error("An invalid #-construct was read.");
                return Values.empty;
        }
    }

    /* JADX INFO: finally extract failed */
    public static Pattern readRegex(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        String str;
        StringBuilder sb;
        Lexer in = lexer;
        int ch = i;
        int i3 = i2;
        int startPos = in.tokenBufferLength;
        LineBufferedReader port = in.getPort();
        char saveReadState = 0;
        int flags = 0;
        if (port instanceof InPort) {
            saveReadState = ((InPort) port).readState;
            ((InPort) port).readState = '/';
        }
        while (true) {
            try {
                int c = port.read();
                if (c < 0) {
                    in.eofError("unexpected EOF in regex literal");
                }
                if (c == ch) {
                    break;
                }
                if (c == 92) {
                    c = port.read();
                    if ((c == 32 || c == 9 || c == 13 || c == 10) && (in instanceof LispReader)) {
                        c = ((LispReader) in).readEscape(c);
                        if (c == -2) {
                        }
                    }
                    if (c < 0) {
                        in.eofError("unexpected EOF in regex literal");
                    }
                    if (c != ch) {
                        in.tokenBufferAppend(92);
                    }
                }
                in.tokenBufferAppend(c);
            } catch (Throwable th) {
                Throwable th2 = th;
                in.tokenBufferLength = startPos;
                if (port instanceof InPort) {
                    ((InPort) port).readState = saveReadState;
                }
                throw th2;
            }
        }
        new String(in.tokenBuffer, startPos, in.tokenBufferLength - startPos);
        String pattern = str;
        while (true) {
            int c2 = in.peek();
            if (c2 != 105 && c2 != 73) {
                if (c2 != 115 && c2 != 83) {
                    if (c2 != 109 && c2 != 77) {
                        if (!Character.isLetter(c2)) {
                            break;
                        }
                        new StringBuilder();
                        in.error(sb.append("unrecognized regex option '").append((char) c2).append('\'').toString());
                    } else {
                        flags |= 8;
                    }
                } else {
                    flags |= 32;
                }
            } else {
                flags |= 66;
            }
            in.skip();
        }
        Pattern compile = Pattern.compile(pattern, flags);
        in.tokenBufferLength = startPos;
        if (port instanceof InPort) {
            ((InPort) port).readState = saveReadState;
        }
        return compile;
    }
}
