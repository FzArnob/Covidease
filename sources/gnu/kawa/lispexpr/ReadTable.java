package gnu.kawa.lispexpr;

import gnu.bytecode.Type;
import gnu.expr.Language;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.util.RangeTable;
import gnu.mapping.Environment;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.ThreadLocation;

public class ReadTable extends RangeTable {
    public static final int CONSTITUENT = 2;
    public static final int ILLEGAL = 0;
    public static final int MULTIPLE_ESCAPE = 4;
    public static final int NON_TERMINATING_MACRO = 6;
    public static final int SINGLE_ESCAPE = 3;
    public static final int TERMINATING_MACRO = 5;
    public static final int WHITESPACE = 1;
    static final ThreadLocation current;
    public static int defaultBracketMode = -1;
    Environment ctorTable = null;
    protected boolean finalColonIsKeyword;
    protected boolean hexEscapeAfterBackslash = true;
    protected boolean initialColonIsKeyword;
    public char postfixLookupOperator = LispReader.TOKEN_ESCAPE_CHAR;

    static {
        ThreadLocation threadLocation;
        new ThreadLocation("read-table");
        current = threadLocation;
    }

    public void setInitialColonIsKeyword(boolean whenInitial) {
        boolean z = whenInitial;
        this.initialColonIsKeyword = z;
    }

    public void setFinalColonIsKeyword(boolean whenFinal) {
        boolean z = whenFinal;
        this.finalColonIsKeyword = z;
    }

    public ReadTable() {
    }

    public void initialize() {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        ReadTableEntry entry = ReadTableEntry.getWhitespaceInstance();
        set(32, entry);
        set(9, entry);
        set(10, entry);
        set(13, entry);
        set(12, entry);
        set(124, ReadTableEntry.getMultipleEscapeInstance());
        set(92, ReadTableEntry.getSingleEscapeInstance());
        set(48, 57, ReadTableEntry.getDigitInstance());
        ReadTableEntry entry2 = ReadTableEntry.getConstituentInstance();
        set(97, 122, entry2);
        set(65, 90, entry2);
        set(33, entry2);
        set(36, entry2);
        set(37, entry2);
        set(38, entry2);
        set(42, entry2);
        set(43, entry2);
        set(45, entry2);
        set(46, entry2);
        set(47, entry2);
        set(61, entry2);
        set(62, entry2);
        set(63, entry2);
        set(64, entry2);
        set(94, entry2);
        set(95, entry2);
        set(123, ReadTableEntry.brace);
        set(126, entry2);
        set(127, entry2);
        set(8, entry2);
        new ReaderColon();
        set(58, obj);
        new ReaderString();
        set(34, obj2);
        set(35, ReaderDispatch.create(this));
        set(59, ReaderIgnoreRestOfLine.getInstance());
        set(40, ReaderParens.getInstance('(', ')'));
        new ReaderQuote(makeSymbol(LispLanguage.quote_sym));
        set(39, obj3);
        new ReaderQuote(makeSymbol(LispLanguage.quasiquote_sym));
        set(96, obj4);
        new ReaderQuote(makeSymbol(LispLanguage.unquote_sym), '@', makeSymbol(LispLanguage.unquotesplicing_sym));
        set(44, obj5);
        setBracketMode();
    }

    public static ReadTable createInitial() {
        ReadTable readTable;
        new ReadTable();
        ReadTable tab = readTable;
        tab.initialize();
        return tab;
    }

    public void setBracketMode(int i) {
        Object obj;
        int mode = i;
        if (mode <= 0) {
            ReadTableEntry token = ReadTableEntry.getConstituentInstance();
            set(60, token);
            if (mode < 0) {
                set(91, token);
                set(93, token);
            }
        } else {
            new ReaderTypespec();
            set(60, obj);
        }
        if (mode >= 0) {
            set(91, ReaderParens.getInstance('[', ']'));
            remove(93);
        }
    }

    public void setBracketMode() {
        setBracketMode(defaultBracketMode);
    }

    /* access modifiers changed from: package-private */
    public void initCtorTable() {
        if (this.ctorTable == null) {
            this.ctorTable = Environment.make();
        }
    }

    public synchronized void putReaderCtor(String str, Procedure procedure) {
        String key = str;
        Procedure proc = procedure;
        synchronized (this) {
            initCtorTable();
            Object put = this.ctorTable.put(key, (Object) proc);
        }
    }

    public synchronized void putReaderCtor(String str, Type type) {
        String key = str;
        Type type2 = type;
        synchronized (this) {
            initCtorTable();
            Object put = this.ctorTable.put(key, (Object) type2);
        }
    }

    public synchronized void putReaderCtorFld(String str, String str2, String str3) {
        String key = str;
        String cname = str2;
        String fname = str3;
        synchronized (this) {
            initCtorTable();
            StaticFieldLocation define = StaticFieldLocation.define(this.ctorTable, this.ctorTable.getSymbol(key), (Object) null, cname, fname);
        }
    }

    public synchronized Object getReaderCtor(String str) {
        Object obj;
        String key = str;
        synchronized (this) {
            initCtorTable();
            obj = this.ctorTable.get(key, (Object) null);
        }
        return obj;
    }

    public static ReadTable getCurrent() {
        ReadTable table = (ReadTable) current.get((Object) null);
        if (table == null) {
            Language language = Language.getDefaultLanguage();
            if (language instanceof LispLanguage) {
                table = ((LispLanguage) language).defaultReadTable;
            } else {
                table = createInitial();
            }
            current.set(table);
        }
        return table;
    }

    public static void setCurrent(ReadTable rt) {
        current.set(rt);
    }

    public ReadTableEntry lookup(int i) {
        int ch = i;
        ReadTableEntry entry = (ReadTableEntry) lookup(ch, (Object) null);
        if (entry == null && ch >= 0 && ch < 65536) {
            if (Character.isDigit((char) ch)) {
                entry = (ReadTableEntry) lookup(48, (Object) null);
            } else if (Character.isLowerCase((char) ch)) {
                entry = (ReadTableEntry) lookup(97, (Object) null);
            } else if (Character.isLetter((char) ch)) {
                entry = (ReadTableEntry) lookup(65, (Object) null);
            } else if (Character.isWhitespace((char) ch)) {
                entry = (ReadTableEntry) lookup(32, (Object) null);
            }
            if (entry == null && ch >= 128) {
                entry = ReadTableEntry.getConstituentInstance();
            }
            if (entry == null) {
                entry = ReadTableEntry.getIllegalInstance();
            }
        }
        return entry;
    }

    /* access modifiers changed from: protected */
    public Object makeSymbol(String name) {
        return Namespace.EmptyNamespace.getSymbol(name.intern());
    }
}
