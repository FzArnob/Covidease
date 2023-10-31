package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.util.HashNode;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lib.C1245lists;
import kawa.lib.kawa.hashtable;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.rnrs.hashtables;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.standard.Scheme;

/* compiled from: srfi69.scm */
public class srfi69 extends ModuleBody {
    public static final srfi69 $instance;
    static final IntNum Lit0 = IntNum.make(64);
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod alist$Mn$Grhash$Mntable;
    public static final ModuleMethod hash;
    public static final ModuleMethod hash$Mnby$Mnidentity;
    public static final ModuleMethod hash$Mntable$Mn$Gralist;
    public static final ModuleMethod hash$Mntable$Mncopy;
    public static final Location hash$Mntable$Mndelete$Ex = StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mndelete$Ex");
    public static final ModuleMethod hash$Mntable$Mnequivalence$Mnfunction;
    public static final Location hash$Mntable$Mnexists$Qu = StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mncontains$Qu");
    public static final ModuleMethod hash$Mntable$Mnfold;
    public static final ModuleMethod hash$Mntable$Mnhash$Mnfunction;
    public static final ModuleMethod hash$Mntable$Mnkeys;
    public static final ModuleMethod hash$Mntable$Mnmerge$Ex;
    public static final ModuleMethod hash$Mntable$Mnref;
    public static final ModuleMethod hash$Mntable$Mnref$Sldefault;
    public static final Location hash$Mntable$Mnset$Ex = StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mnset$Ex");
    public static final Location hash$Mntable$Mnsize = StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mnsize");
    public static final ModuleMethod hash$Mntable$Mnupdate$Ex;
    public static final ModuleMethod hash$Mntable$Mnupdate$Ex$Sldefault;
    public static final ModuleMethod hash$Mntable$Mnvalues;
    public static final ModuleMethod hash$Mntable$Mnwalk;
    public static final Location hash$Mntable$Qu = StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Qu");
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn2;
    static final ModuleMethod lambda$Fn3;
    public static final ModuleMethod make$Mnhash$Mntable;
    public static final ModuleMethod string$Mnci$Mnhash;
    public static final ModuleMethod string$Mnhash;

    public srfi69() {
        ModuleInfo.register(this);
    }

    public static hashtable.HashTable alist$To$HashTable(Object obj) {
        return alist$To$HashTable(obj, Scheme.isEqual);
    }

    public static hashtable.HashTable alist$To$HashTable(Object obj, Object obj2) {
        Object obj3 = obj2;
        return alist$To$HashTable(obj, obj3, appropriateHashFunctionFor(obj3));
    }

    public static Object hash(Object obj) {
        return hash(obj, (IntNum) null);
    }

    public static Object hashByIdentity(Object obj) {
        return hashByIdentity(obj, (IntNum) null);
    }

    public static Object hashTableRef(hashtable.HashTable hashTable, Object obj) {
        return hashTableRef(hashTable, obj, Boolean.FALSE);
    }

    public static void hashTableUpdate$Ex(hashtable.HashTable hashTable, Object obj, Object obj2) {
        hashTableUpdate$Ex(hashTable, obj, obj2, Boolean.FALSE);
    }

    public static hashtable.HashTable makeHashTable() {
        return makeHashTable(Scheme.isEqual);
    }

    public static hashtable.HashTable makeHashTable(Procedure procedure) {
        Procedure procedure2 = procedure;
        return makeHashTable(procedure2, appropriateHashFunctionFor(procedure2), 64);
    }

    public static hashtable.HashTable makeHashTable(Procedure procedure, Procedure procedure2) {
        return makeHashTable(procedure, procedure2, 64);
    }

    public static Object stringCiHash(Object obj) {
        return stringCiHash(obj, (IntNum) null);
    }

    public static Object stringHash(CharSequence charSequence) {
        return stringHash(charSequence, (IntNum) null);
    }

    static Object symbolHash(Symbol symbol) {
        return symbolHash(symbol, (IntNum) null);
    }

    static Object vectorHash(Object obj) {
        return vectorHash(obj, (IntNum) null);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        SimpleSymbol simpleSymbol4;
        SimpleSymbol simpleSymbol5;
        SimpleSymbol simpleSymbol6;
        SimpleSymbol simpleSymbol7;
        SimpleSymbol simpleSymbol8;
        SimpleSymbol simpleSymbol9;
        SimpleSymbol simpleSymbol10;
        SimpleSymbol simpleSymbol11;
        SimpleSymbol simpleSymbol12;
        SimpleSymbol simpleSymbol13;
        SimpleSymbol simpleSymbol14;
        SimpleSymbol simpleSymbol15;
        SimpleSymbol simpleSymbol16;
        SimpleSymbol simpleSymbol17;
        SimpleSymbol simpleSymbol18;
        SimpleSymbol simpleSymbol19;
        srfi69 srfi69;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        ModuleMethod moduleMethod4;
        ModuleMethod moduleMethod5;
        ModuleMethod moduleMethod6;
        ModuleMethod moduleMethod7;
        ModuleMethod moduleMethod8;
        ModuleMethod moduleMethod9;
        ModuleMethod moduleMethod10;
        ModuleMethod moduleMethod11;
        ModuleMethod moduleMethod12;
        ModuleMethod moduleMethod13;
        ModuleMethod moduleMethod14;
        ModuleMethod moduleMethod15;
        ModuleMethod moduleMethod16;
        ModuleMethod moduleMethod17;
        ModuleMethod moduleMethod18;
        ModuleMethod moduleMethod19;
        ModuleMethod moduleMethod20;
        ModuleMethod moduleMethod21;
        ModuleMethod moduleMethod22;
        new SimpleSymbol("hash-table-values");
        Lit19 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("hash-table-keys");
        Lit18 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("hash-table-merge!");
        Lit17 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("hash-table-copy");
        Lit16 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("hash-table->alist");
        Lit15 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("alist->hash-table");
        Lit14 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("hash-table-fold");
        Lit13 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("hash-table-walk");
        Lit12 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("hash-table-update!/default");
        Lit11 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("hash-table-update!");
        Lit10 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("hash-table-ref/default");
        Lit9 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("hash-table-ref");
        Lit8 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("make-hash-table");
        Lit7 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("hash-table-hash-function");
        Lit6 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("hash-table-equivalence-function");
        Lit5 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("hash-by-identity");
        Lit4 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("hash");
        Lit3 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("string-ci-hash");
        Lit2 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("string-hash");
        Lit1 = (SimpleSymbol) simpleSymbol19.readResolve();
        new srfi69();
        $instance = srfi69;
        srfi69 srfi692 = $instance;
        new ModuleMethod(srfi692, 1, Lit1, 8193);
        string$Mnhash = moduleMethod;
        new ModuleMethod(srfi692, 3, Lit2, 8193);
        string$Mnci$Mnhash = moduleMethod2;
        new ModuleMethod(srfi692, 5, Lit3, 8193);
        hash = moduleMethod3;
        new ModuleMethod(srfi692, 7, Lit4, 8193);
        hash$Mnby$Mnidentity = moduleMethod4;
        new ModuleMethod(srfi692, 9, Lit5, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hash$Mntable$Mnequivalence$Mnfunction = moduleMethod5;
        new ModuleMethod(srfi692, 10, Lit6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hash$Mntable$Mnhash$Mnfunction = moduleMethod6;
        new ModuleMethod(srfi692, 11, Lit7, 12288);
        make$Mnhash$Mntable = moduleMethod7;
        new ModuleMethod(srfi692, 15, Lit8, 12290);
        hash$Mntable$Mnref = moduleMethod8;
        new ModuleMethod(srfi692, 17, Lit9, 12291);
        hash$Mntable$Mnref$Sldefault = moduleMethod9;
        new ModuleMethod(srfi692, 18, Lit10, 16387);
        hash$Mntable$Mnupdate$Ex = moduleMethod10;
        new ModuleMethod(srfi692, 20, Lit11, 16388);
        hash$Mntable$Mnupdate$Ex$Sldefault = moduleMethod11;
        new ModuleMethod(srfi692, 21, Lit12, 8194);
        hash$Mntable$Mnwalk = moduleMethod12;
        new ModuleMethod(srfi692, 22, Lit13, 12291);
        hash$Mntable$Mnfold = moduleMethod13;
        new ModuleMethod(srfi692, 23, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod23 = moduleMethod14;
        moduleMethod23.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi69.scm:166");
        lambda$Fn1 = moduleMethod23;
        new ModuleMethod(srfi692, 24, Lit14, 16385);
        alist$Mn$Grhash$Mntable = moduleMethod15;
        new ModuleMethod(srfi692, 28, Lit15, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hash$Mntable$Mn$Gralist = moduleMethod16;
        new ModuleMethod(srfi692, 29, Lit16, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hash$Mntable$Mncopy = moduleMethod17;
        new ModuleMethod(srfi692, 30, Lit17, 8194);
        hash$Mntable$Mnmerge$Ex = moduleMethod18;
        new ModuleMethod(srfi692, 31, (Object) null, 12291);
        ModuleMethod moduleMethod24 = moduleMethod19;
        moduleMethod24.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi69.scm:183");
        lambda$Fn2 = moduleMethod24;
        new ModuleMethod(srfi692, 32, Lit18, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hash$Mntable$Mnkeys = moduleMethod20;
        new ModuleMethod(srfi692, 33, (Object) null, 12291);
        ModuleMethod moduleMethod25 = moduleMethod21;
        moduleMethod25.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi69.scm:186");
        lambda$Fn3 = moduleMethod25;
        new ModuleMethod(srfi692, 34, Lit19, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hash$Mntable$Mnvalues = moduleMethod22;
        $instance.run();
    }

    public static Object stringHash(CharSequence s, IntNum intNum) {
        IntNum bound = intNum;
        int h = s.hashCode();
        return bound == null ? Integer.valueOf(h) : IntNum.modulo(IntNum.make(h), bound);
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 1:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof CharSequence)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 3:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 5:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 7:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 9:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (!(obj5 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 10:
                CallContext callContext5 = callContext2;
                Object obj7 = obj2;
                Object obj8 = obj7;
                if (!(obj7 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext5.value1 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 11:
                CallContext callContext6 = callContext2;
                Object obj9 = obj2;
                Object obj10 = obj9;
                if (!(obj9 instanceof Procedure)) {
                    return -786431;
                }
                callContext6.value1 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 23:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 24:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 28:
                CallContext callContext7 = callContext2;
                Object obj11 = obj2;
                Object obj12 = obj11;
                if (!(obj11 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext7.value1 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 29:
                CallContext callContext8 = callContext2;
                Object obj13 = obj2;
                Object obj14 = obj13;
                if (!(obj13 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext8.value1 = obj14;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 32:
                CallContext callContext9 = callContext2;
                Object obj15 = obj2;
                Object obj16 = obj15;
                if (!(obj15 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext9.value1 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 34:
                CallContext callContext10 = callContext2;
                Object obj17 = obj2;
                Object obj18 = obj17;
                if (!(obj17 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext10.value1 = obj18;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 1:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (!(obj5 instanceof CharSequence)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                CallContext callContext4 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (IntNum.asIntNumOrNull(obj7) == null) {
                    return -786430;
                }
                callContext4.value2 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 3:
                callContext2.value1 = obj3;
                CallContext callContext5 = callContext2;
                Object obj9 = obj4;
                Object obj10 = obj9;
                if (IntNum.asIntNumOrNull(obj9) == null) {
                    return -786430;
                }
                callContext5.value2 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 5:
                callContext2.value1 = obj3;
                CallContext callContext6 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (IntNum.asIntNumOrNull(obj11) == null) {
                    return -786430;
                }
                callContext6.value2 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 7:
                callContext2.value1 = obj3;
                CallContext callContext7 = callContext2;
                Object obj13 = obj4;
                Object obj14 = obj13;
                if (IntNum.asIntNumOrNull(obj13) == null) {
                    return -786430;
                }
                callContext7.value2 = obj14;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 11:
                CallContext callContext8 = callContext2;
                Object obj15 = obj3;
                Object obj16 = obj15;
                if (!(obj15 instanceof Procedure)) {
                    return -786431;
                }
                callContext8.value1 = obj16;
                CallContext callContext9 = callContext2;
                Object obj17 = obj4;
                Object obj18 = obj17;
                if (!(obj17 instanceof Procedure)) {
                    return -786430;
                }
                callContext9.value2 = obj18;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 15:
                CallContext callContext10 = callContext2;
                Object obj19 = obj3;
                Object obj20 = obj19;
                if (!(obj19 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext10.value1 = obj20;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 21:
                CallContext callContext11 = callContext2;
                Object obj21 = obj3;
                Object obj22 = obj21;
                if (!(obj21 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext11.value1 = obj22;
                CallContext callContext12 = callContext2;
                Object obj23 = obj4;
                Object obj24 = obj23;
                if (!(obj23 instanceof Procedure)) {
                    return -786430;
                }
                callContext12.value2 = obj24;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 24:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 30:
                CallContext callContext13 = callContext2;
                Object obj25 = obj3;
                Object obj26 = obj25;
                if (!(obj25 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext13.value1 = obj26;
                CallContext callContext14 = callContext2;
                Object obj27 = obj4;
                Object obj28 = obj27;
                if (!(obj27 instanceof hashtable.HashTable)) {
                    return -786430;
                }
                callContext14.value2 = obj28;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static Object stringCiHash(Object s, IntNum intNum) {
        IntNum bound = intNum;
        int h = s.toString().toLowerCase().hashCode();
        return bound == null ? Integer.valueOf(h) : IntNum.modulo(IntNum.make(h), bound);
    }

    static Object symbolHash(Symbol s, IntNum intNum) {
        IntNum bound = intNum;
        int h = s.hashCode();
        return bound == null ? Integer.valueOf(h) : IntNum.modulo(IntNum.make(h), bound);
    }

    public static Object hash(Object obj, IntNum intNum) {
        Object obj2 = obj;
        IntNum bound = intNum;
        int h = obj2 == null ? 0 : obj2.hashCode();
        return bound == null ? Integer.valueOf(h) : IntNum.modulo(IntNum.make(h), bound);
    }

    public static Object hashByIdentity(Object obj, IntNum intNum) {
        IntNum bound = intNum;
        int h = System.identityHashCode(obj);
        return bound == null ? Integer.valueOf(h) : IntNum.modulo(IntNum.make(h), bound);
    }

    static Object vectorHash(Object v, IntNum intNum) {
        IntNum bound = intNum;
        int h = v.hashCode();
        return bound == null ? Integer.valueOf(h) : IntNum.modulo(IntNum.make(h), bound);
    }

    public static Procedure hashTableEquivalenceFunction(hashtable.HashTable hash$Mntable) {
        return hash$Mntable.equivalenceFunction;
    }

    public static Procedure hashTableHashFunction(hashtable.HashTable hash$Mntable) {
        return hash$Mntable.hashFunction;
    }

    static Procedure appropriateHashFunctionFor(Object obj) {
        Procedure procedure;
        Object comparison = obj;
        boolean x = comparison == Scheme.isEq;
        Object x2 = x ? hash$Mnby$Mnidentity : x ? Boolean.TRUE : Boolean.FALSE;
        if (x2 != Boolean.FALSE) {
            procedure = (Procedure) x2;
        } else {
            boolean x3 = comparison == strings.string$Eq$Qu;
            Object x4 = x3 ? string$Mnhash : x3 ? Boolean.TRUE : Boolean.FALSE;
            if (x4 != Boolean.FALSE) {
                procedure = (Procedure) x4;
            } else {
                boolean x5 = comparison == unicode.string$Mnci$Eq$Qu;
                Object x6 = x5 ? string$Mnci$Mnhash : x5 ? Boolean.TRUE : Boolean.FALSE;
                procedure = x6 != Boolean.FALSE ? (Procedure) x6 : hash;
            }
        }
        return procedure;
    }

    public static hashtable.HashTable makeHashTable(Procedure comparison, Procedure hash2, int size) {
        hashtable.HashTable hashTable;
        new hashtable.HashTable(comparison, hash2, size);
        return hashTable;
    }

    public Object apply0(ModuleMethod moduleMethod) {
        ModuleMethod moduleMethod2 = moduleMethod;
        return moduleMethod2.selector == 11 ? makeHashTable() : super.apply0(moduleMethod2);
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 11) {
            return super.match0(moduleMethod2, callContext2);
        }
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 0;
        return 0;
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 11:
                CallContext callContext3 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (!(obj7 instanceof Procedure)) {
                    return -786431;
                }
                callContext3.value1 = obj8;
                CallContext callContext4 = callContext2;
                Object obj9 = obj5;
                Object obj10 = obj9;
                if (!(obj9 instanceof Procedure)) {
                    return -786430;
                }
                callContext4.value2 = obj10;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 15:
                CallContext callContext5 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (!(obj11 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext5.value1 = obj12;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 17:
                CallContext callContext6 = callContext2;
                Object obj13 = obj4;
                Object obj14 = obj13;
                if (!(obj13 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext6.value1 = obj14;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 18:
                CallContext callContext7 = callContext2;
                Object obj15 = obj4;
                Object obj16 = obj15;
                if (!(obj15 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext7.value1 = obj16;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 22:
                CallContext callContext8 = callContext2;
                Object obj17 = obj4;
                Object obj18 = obj17;
                if (!(obj17 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext8.value1 = obj18;
                CallContext callContext9 = callContext2;
                Object obj19 = obj5;
                Object obj20 = obj19;
                if (!(obj19 instanceof Procedure)) {
                    return -786430;
                }
                callContext9.value2 = obj20;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 24:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 31:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 33:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
    }

    public static Object hashTableRef(hashtable.HashTable hash$Mntable, Object obj, Object obj2) {
        Object value;
        Object key = obj;
        Object obj3 = obj2;
        HashNode node = hash$Mntable.getNode(key);
        if (node != null) {
            value = node.getValue();
        } else if (obj3 != Boolean.FALSE) {
            value = Scheme.applyToArgs.apply1(obj3);
        } else {
            value = misc.error$V("hash-table-ref: no value associated with", new Object[]{key});
        }
        return value;
    }

    public static Object hashTableRef$SlDefault(hashtable.HashTable hash$Mntable, Object key, Object obj) {
        return hash$Mntable.get(key, obj);
    }

    public static void hashTableUpdate$Ex(hashtable.HashTable hashTable, Object obj, Object obj2, Object obj3) {
        hashtable.HashTable hash$Mntable = hashTable;
        Object key = obj;
        Object function = obj2;
        Object thunk = obj3;
        hashtable.hashtableCheckMutable(hash$Mntable);
        HashNode node = hash$Mntable.getNode(key);
        if (node != null) {
            Object value = node.setValue(Scheme.applyToArgs.apply2(function, node.getValue()));
        } else if (thunk != Boolean.FALSE) {
            hashtables.hashtableSet$Ex(hash$Mntable, key, Scheme.applyToArgs.apply2(function, Scheme.applyToArgs.apply1(thunk)));
        } else {
            Object error$V = misc.error$V("hash-table-update!: no value exists for key", new Object[]{key});
        }
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 18:
                CallContext callContext3 = callContext2;
                Object obj9 = obj5;
                Object obj10 = obj9;
                if (!(obj9 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext3.value1 = obj10;
                callContext2.value2 = obj6;
                callContext2.value3 = obj7;
                callContext2.value4 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            case 20:
                CallContext callContext4 = callContext2;
                Object obj11 = obj5;
                Object obj12 = obj11;
                if (!(obj11 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext4.value1 = obj12;
                callContext2.value2 = obj6;
                callContext2.value3 = obj7;
                callContext2.value4 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            case 24:
                callContext2.value1 = obj5;
                callContext2.value2 = obj6;
                callContext2.value3 = obj7;
                callContext2.value4 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            default:
                return super.match4(moduleMethod2, obj5, obj6, obj7, obj8, callContext2);
        }
    }

    public static void hashTableUpdate$Ex$SlDefault(hashtable.HashTable hashTable, Object obj, Object obj2, Object obj3) {
        hashtable.HashTable hash$Mntable = hashTable;
        Object key = obj;
        Object function = obj2;
        Object obj4 = obj3;
        hashtable.hashtableCheckMutable(hash$Mntable);
        HashNode node = hash$Mntable.getNode(key);
        if (node == null) {
            hashtables.hashtableSet$Ex(hash$Mntable, key, Scheme.applyToArgs.apply2(function, obj4));
        } else {
            Object value = node.setValue(Scheme.applyToArgs.apply2(function, node.getValue()));
        }
    }

    public static void hashTableWalk(hashtable.HashTable hash$Mntable, Procedure proc) {
        hash$Mntable.walk(proc);
    }

    public static Object hashTableFold(hashtable.HashTable hash$Mntable, Procedure proc, Object acc) {
        return hash$Mntable.fold(proc, acc);
    }

    public static hashtable.HashTable alist$To$HashTable(Object obj, Object comparison, Object hash2, Object size) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Object alist = obj;
        Object obj2 = comparison;
        Object obj3 = obj2;
        try {
            Procedure procedure = (Procedure) obj2;
            Object obj4 = hash2;
            Object obj5 = obj4;
            try {
                Procedure procedure2 = (Procedure) obj4;
                Object obj6 = size;
                Object obj7 = obj6;
                try {
                    hashtable.HashTable hash$Mntable = makeHashTable(procedure, procedure2, ((Number) obj6).intValue());
                    Object obj8 = alist;
                    while (true) {
                        Object arg0 = obj8;
                        if (arg0 == LList.Empty) {
                            return hash$Mntable;
                        }
                        Object obj9 = arg0;
                        Object obj10 = obj9;
                        try {
                            Pair arg02 = (Pair) obj9;
                            Object elem = arg02.getCar();
                            hashTableUpdate$Ex$SlDefault(hash$Mntable, C1245lists.car.apply1(elem), lambda$Fn1, C1245lists.cdr.apply1(elem));
                            obj8 = arg02.getCdr();
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th5 = th4;
                            new WrongType(classCastException, "arg0", -2, obj10);
                            throw th5;
                        }
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th6 = th3;
                    new WrongType(classCastException2, "make-hash-table", 2, obj7);
                    throw th6;
                }
            } catch (ClassCastException e3) {
                ClassCastException classCastException3 = e3;
                Throwable th7 = th2;
                new WrongType(classCastException3, "make-hash-table", 1, obj5);
                throw th7;
            }
        } catch (ClassCastException e4) {
            ClassCastException classCastException4 = e4;
            Throwable th8 = th;
            new WrongType(classCastException4, "make-hash-table", 0, obj3);
            throw th8;
        }
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        Throwable th;
        Throwable th2;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        switch (moduleMethod2.selector) {
            case 18:
                try {
                    hashTableUpdate$Ex((hashtable.HashTable) obj5, obj6, obj7, obj8);
                    return Values.empty;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "hash-table-update!", 1, obj5);
                    throw th3;
                }
            case 20:
                try {
                    hashTableUpdate$Ex$SlDefault((hashtable.HashTable) obj5, obj6, obj7, obj8);
                    return Values.empty;
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "hash-table-update!/default", 1, obj5);
                    throw th4;
                }
            case 24:
                return alist$To$HashTable(obj5, obj6, obj7, obj8);
            default:
                return super.apply4(moduleMethod2, obj5, obj6, obj7, obj8);
        }
    }

    public static hashtable.HashTable alist$To$HashTable(Object obj, Object obj2, Object obj3) {
        Throwable th;
        Object obj4 = obj;
        Object obj5 = obj4;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object[] objArr = new Object[2];
        objArr[0] = Lit0;
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr2;
        Object[] objArr4 = objArr2;
        Object obj8 = obj4;
        Object obj9 = obj8;
        try {
            objArr4[1] = Integer.valueOf(2 * C1245lists.length((LList) obj8));
            return alist$To$HashTable(obj5, obj6, obj7, numbers.max(objArr3));
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "length", 1, obj9);
            throw th2;
        }
    }

    static Object lambda1(Object x) {
        return x;
    }

    public static Object hashTable$To$Alist(hashtable.HashTable hash$Mntable) {
        return hash$Mntable.toAlist();
    }

    public static hashtable.HashTable hashTableCopy(hashtable.HashTable hash$Mntable) {
        hashtable.HashTable hash$Mntable2;
        new hashtable.HashTable(hash$Mntable, true);
        return hash$Mntable2;
    }

    public static void hashTableMerge$Ex(hashtable.HashTable hash$Mntable1, hashtable.HashTable hash$Mntable2) {
        hash$Mntable1.putAll(hash$Mntable2);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Throwable th9;
        Throwable th10;
        Throwable th11;
        Throwable th12;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 1:
                try {
                    try {
                        return stringHash((CharSequence) obj3, LangObjType.coerceIntNum(obj4));
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th13 = th12;
                        new WrongType(classCastException, "string-hash", 2, obj4);
                        throw th13;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th14 = th11;
                    new WrongType(classCastException2, "string-hash", 1, obj3);
                    throw th14;
                }
            case 3:
                try {
                    return stringCiHash(obj3, LangObjType.coerceIntNum(obj4));
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th15 = th10;
                    new WrongType(classCastException3, "string-ci-hash", 2, obj4);
                    throw th15;
                }
            case 5:
                try {
                    return hash(obj3, LangObjType.coerceIntNum(obj4));
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th16 = th9;
                    new WrongType(classCastException4, "hash", 2, obj4);
                    throw th16;
                }
            case 7:
                try {
                    return hashByIdentity(obj3, LangObjType.coerceIntNum(obj4));
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th17 = th8;
                    new WrongType(classCastException5, "hash-by-identity", 2, obj4);
                    throw th17;
                }
            case 11:
                try {
                    try {
                        return makeHashTable((Procedure) obj3, (Procedure) obj4);
                    } catch (ClassCastException e6) {
                        ClassCastException classCastException6 = e6;
                        Throwable th18 = th7;
                        new WrongType(classCastException6, "make-hash-table", 2, obj4);
                        throw th18;
                    }
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th19 = th6;
                    new WrongType(classCastException7, "make-hash-table", 1, obj3);
                    throw th19;
                }
            case 15:
                try {
                    return hashTableRef((hashtable.HashTable) obj3, obj4);
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th20 = th5;
                    new WrongType(classCastException8, "hash-table-ref", 1, obj3);
                    throw th20;
                }
            case 21:
                try {
                    try {
                        hashTableWalk((hashtable.HashTable) obj3, (Procedure) obj4);
                        return Values.empty;
                    } catch (ClassCastException e9) {
                        ClassCastException classCastException9 = e9;
                        Throwable th21 = th4;
                        new WrongType(classCastException9, "hash-table-walk", 2, obj4);
                        throw th21;
                    }
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th22 = th3;
                    new WrongType(classCastException10, "hash-table-walk", 1, obj3);
                    throw th22;
                }
            case 24:
                return alist$To$HashTable(obj3, obj4);
            case 30:
                try {
                    try {
                        hashTableMerge$Ex((hashtable.HashTable) obj3, (hashtable.HashTable) obj4);
                        return Values.empty;
                    } catch (ClassCastException e11) {
                        ClassCastException classCastException11 = e11;
                        Throwable th23 = th2;
                        new WrongType(classCastException11, "hash-table-merge!", 2, obj4);
                        throw th23;
                    }
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th24 = th;
                    new WrongType(classCastException12, "hash-table-merge!", 1, obj3);
                    throw th24;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    public static Object hashTableKeys(hashtable.HashTable hash$Mntable) {
        return hashTableFold(hash$Mntable, lambda$Fn2, LList.Empty);
    }

    static Pair lambda2(Object key, Object obj, Object acc) {
        Object obj2 = obj;
        return C1245lists.cons(key, acc);
    }

    public static Object hashTableValues(hashtable.HashTable hash$Mntable) {
        return hashTableFold(hash$Mntable, lambda$Fn3, LList.Empty);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                try {
                    return stringHash((CharSequence) obj2);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th9 = th8;
                    new WrongType(classCastException, "string-hash", 1, obj2);
                    throw th9;
                }
            case 3:
                return stringCiHash(obj2);
            case 5:
                return hash(obj2);
            case 7:
                return hashByIdentity(obj2);
            case 9:
                try {
                    return hashTableEquivalenceFunction((hashtable.HashTable) obj2);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th10 = th7;
                    new WrongType(classCastException2, "hash-table-equivalence-function", 1, obj2);
                    throw th10;
                }
            case 10:
                try {
                    return hashTableHashFunction((hashtable.HashTable) obj2);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th11 = th6;
                    new WrongType(classCastException3, "hash-table-hash-function", 1, obj2);
                    throw th11;
                }
            case 11:
                try {
                    return makeHashTable((Procedure) obj2);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th12 = th5;
                    new WrongType(classCastException4, "make-hash-table", 1, obj2);
                    throw th12;
                }
            case 23:
                return lambda1(obj2);
            case 24:
                return alist$To$HashTable(obj2);
            case 28:
                try {
                    return hashTable$To$Alist((hashtable.HashTable) obj2);
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th13 = th4;
                    new WrongType(classCastException5, "hash-table->alist", 1, obj2);
                    throw th13;
                }
            case 29:
                try {
                    return hashTableCopy((hashtable.HashTable) obj2);
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th14 = th3;
                    new WrongType(classCastException6, "hash-table-copy", 1, obj2);
                    throw th14;
                }
            case 32:
                try {
                    return hashTableKeys((hashtable.HashTable) obj2);
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th15 = th2;
                    new WrongType(classCastException7, "hash-table-keys", 1, obj2);
                    throw th15;
                }
            case 34:
                try {
                    return hashTableValues((hashtable.HashTable) obj2);
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th16 = th;
                    new WrongType(classCastException8, "hash-table-values", 1, obj2);
                    throw th16;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    static Pair lambda3(Object obj, Object val, Object acc) {
        Object obj2 = obj;
        return C1245lists.cons(val, acc);
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        switch (moduleMethod2.selector) {
            case 11:
                try {
                    try {
                        try {
                            return makeHashTable((Procedure) obj4, (Procedure) obj5, ((Number) obj6).intValue());
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th9 = th8;
                            new WrongType(classCastException, "make-hash-table", 3, obj6);
                            throw th9;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th10 = th7;
                        new WrongType(classCastException2, "make-hash-table", 2, obj5);
                        throw th10;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th11 = th6;
                    new WrongType(classCastException3, "make-hash-table", 1, obj4);
                    throw th11;
                }
            case 15:
                try {
                    return hashTableRef((hashtable.HashTable) obj4, obj5, obj6);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th12 = th5;
                    new WrongType(classCastException4, "hash-table-ref", 1, obj4);
                    throw th12;
                }
            case 17:
                try {
                    return hashTableRef$SlDefault((hashtable.HashTable) obj4, obj5, obj6);
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th13 = th4;
                    new WrongType(classCastException5, "hash-table-ref/default", 1, obj4);
                    throw th13;
                }
            case 18:
                try {
                    hashTableUpdate$Ex((hashtable.HashTable) obj4, obj5, obj6);
                    return Values.empty;
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th14 = th3;
                    new WrongType(classCastException6, "hash-table-update!", 1, obj4);
                    throw th14;
                }
            case 22:
                try {
                    try {
                        return hashTableFold((hashtable.HashTable) obj4, (Procedure) obj5, obj6);
                    } catch (ClassCastException e7) {
                        ClassCastException classCastException7 = e7;
                        Throwable th15 = th2;
                        new WrongType(classCastException7, "hash-table-fold", 2, obj5);
                        throw th15;
                    }
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th16 = th;
                    new WrongType(classCastException8, "hash-table-fold", 1, obj4);
                    throw th16;
                }
            case 24:
                return alist$To$HashTable(obj4, obj5, obj6);
            case 31:
                return lambda2(obj4, obj5, obj6);
            case 33:
                return lambda3(obj4, obj5, obj6);
            default:
                return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
    }
}
