package kawa.lib.rnrs;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.util.AbstractHashTable;
import gnu.kawa.util.HashNode;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lib.C1245lists;
import kawa.lib.kawa.hashtable;
import kawa.lib.misc;
import kawa.standard.Scheme;

/* compiled from: hashtables.scm */
public class hashtables extends ModuleBody {
    public static final hashtables $instance;
    static final SimpleSymbol Lit0;
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
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod equal$Mnhash;
    static final ModuleMethod hash$Mnby$Mnidentity;
    static final ModuleMethod hash$Mnfor$Mneqv;
    public static final ModuleMethod hashtable$Mnclear$Ex;
    public static final ModuleMethod hashtable$Mncontains$Qu;
    public static final ModuleMethod hashtable$Mncopy;
    public static final ModuleMethod hashtable$Mndelete$Ex;
    public static final ModuleMethod hashtable$Mnentries;
    public static final ModuleMethod hashtable$Mnequivalence$Mnfunction;
    public static final ModuleMethod hashtable$Mnhash$Mnfunction;
    public static final ModuleMethod hashtable$Mnkeys;
    public static final ModuleMethod hashtable$Mnmutable$Qu;
    public static final ModuleMethod hashtable$Mnref;
    public static final ModuleMethod hashtable$Mnset$Ex;
    public static final ModuleMethod hashtable$Mnsize;
    public static final ModuleMethod hashtable$Mnupdate$Ex;
    public static final ModuleMethod hashtable$Qu;
    public static final ModuleMethod make$Mneq$Mnhashtable;
    public static final ModuleMethod make$Mneqv$Mnhashtable;
    public static final ModuleMethod make$Mnhashtable;
    public static final ModuleMethod string$Mnci$Mnhash;
    public static final ModuleMethod string$Mnhash;
    public static final ModuleMethod symbol$Mnhash;

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
        SimpleSymbol simpleSymbol20;
        SimpleSymbol simpleSymbol21;
        SimpleSymbol simpleSymbol22;
        SimpleSymbol simpleSymbol23;
        hashtables hashtables;
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
        ModuleMethod moduleMethod23;
        new SimpleSymbol("symbol-hash");
        Lit22 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("string-ci-hash");
        Lit21 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("string-hash");
        Lit20 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("equal-hash");
        Lit19 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("hashtable-mutable?");
        Lit18 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("hashtable-hash-function");
        Lit17 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("hashtable-equivalence-function");
        Lit16 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("hashtable-entries");
        Lit15 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("hashtable-keys");
        Lit14 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("hashtable-clear!");
        Lit13 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("hashtable-copy");
        Lit12 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("hashtable-update!");
        Lit11 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("hashtable-contains?");
        Lit10 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("hashtable-delete!");
        Lit9 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("hashtable-set!");
        Lit8 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("hashtable-ref");
        Lit7 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("hashtable-size");
        Lit6 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("hashtable?");
        Lit5 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("make-hashtable");
        Lit4 = (SimpleSymbol) simpleSymbol19.readResolve();
        new SimpleSymbol("make-eqv-hashtable");
        Lit3 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("make-eq-hashtable");
        Lit2 = (SimpleSymbol) simpleSymbol21.readResolve();
        new SimpleSymbol("hash-for-eqv");
        Lit1 = (SimpleSymbol) simpleSymbol22.readResolve();
        new SimpleSymbol("hash-by-identity");
        Lit0 = (SimpleSymbol) simpleSymbol23.readResolve();
        new hashtables();
        $instance = hashtables;
        hashtables hashtables2 = $instance;
        new ModuleMethod(hashtables2, 1, Lit0, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hash$Mnby$Mnidentity = moduleMethod;
        new ModuleMethod(hashtables2, 2, Lit1, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hash$Mnfor$Mneqv = moduleMethod2;
        new ModuleMethod(hashtables2, 3, Lit2, 4096);
        make$Mneq$Mnhashtable = moduleMethod3;
        new ModuleMethod(hashtables2, 5, Lit3, 4096);
        make$Mneqv$Mnhashtable = moduleMethod4;
        new ModuleMethod(hashtables2, 7, Lit4, 12290);
        make$Mnhashtable = moduleMethod5;
        new ModuleMethod(hashtables2, 9, Lit5, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Qu = moduleMethod6;
        new ModuleMethod(hashtables2, 10, Lit6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnsize = moduleMethod7;
        new ModuleMethod(hashtables2, 11, Lit7, 12291);
        hashtable$Mnref = moduleMethod8;
        new ModuleMethod(hashtables2, 12, Lit8, 12291);
        hashtable$Mnset$Ex = moduleMethod9;
        new ModuleMethod(hashtables2, 13, Lit9, 8194);
        hashtable$Mndelete$Ex = moduleMethod10;
        new ModuleMethod(hashtables2, 14, Lit10, 8194);
        hashtable$Mncontains$Qu = moduleMethod11;
        new ModuleMethod(hashtables2, 15, Lit11, 16388);
        hashtable$Mnupdate$Ex = moduleMethod12;
        new ModuleMethod(hashtables2, 16, Lit12, 8193);
        hashtable$Mncopy = moduleMethod13;
        new ModuleMethod(hashtables2, 18, Lit13, 8193);
        hashtable$Mnclear$Ex = moduleMethod14;
        new ModuleMethod(hashtables2, 20, Lit14, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnkeys = moduleMethod15;
        new ModuleMethod(hashtables2, 21, Lit15, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnentries = moduleMethod16;
        new ModuleMethod(hashtables2, 22, Lit16, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnequivalence$Mnfunction = moduleMethod17;
        new ModuleMethod(hashtables2, 23, Lit17, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnhash$Mnfunction = moduleMethod18;
        new ModuleMethod(hashtables2, 24, Lit18, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnmutable$Qu = moduleMethod19;
        new ModuleMethod(hashtables2, 25, Lit19, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        equal$Mnhash = moduleMethod20;
        new ModuleMethod(hashtables2, 26, Lit20, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnhash = moduleMethod21;
        new ModuleMethod(hashtables2, 27, Lit21, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnci$Mnhash = moduleMethod22;
        new ModuleMethod(hashtables2, 28, Lit22, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mnhash = moduleMethod23;
        $instance.run();
    }

    public hashtables() {
        ModuleInfo.register(this);
    }

    public static void hashtableClear$Ex(hashtable.HashTable hashTable) {
        hashtableClear$Ex(hashTable, 64);
    }

    public static hashtable.HashTable hashtableCopy(hashtable.HashTable hashTable) {
        return hashtableCopy(hashTable, false);
    }

    public static hashtable.HashTable makeEqHashtable() {
        return makeEqHashtable(AbstractHashTable.DEFAULT_INITIAL_SIZE);
    }

    public static hashtable.HashTable makeEqvHashtable() {
        return makeEqvHashtable(AbstractHashTable.DEFAULT_INITIAL_SIZE);
    }

    public static hashtable.HashTable makeHashtable(Procedure procedure, Procedure procedure2) {
        return makeHashtable(procedure, procedure2, AbstractHashTable.DEFAULT_INITIAL_SIZE);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    static int hashByIdentity(Object obj) {
        return System.identityHashCode(obj);
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 1:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 2:
                callContext2.value1 = obj2;
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
            case 9:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 10:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 16:
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
            case 18:
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
            case 20:
                CallContext callContext6 = callContext2;
                Object obj9 = obj2;
                Object obj10 = obj9;
                if (!(obj9 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext6.value1 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 21:
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
            case 22:
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
            case 23:
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
            case 24:
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
            case 25:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 26:
                CallContext callContext11 = callContext2;
                Object obj19 = obj2;
                Object obj20 = obj19;
                if (!(obj19 instanceof CharSequence)) {
                    return -786431;
                }
                callContext11.value1 = obj20;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 27:
                CallContext callContext12 = callContext2;
                Object obj21 = obj2;
                Object obj22 = obj21;
                if (!(obj21 instanceof CharSequence)) {
                    return -786431;
                }
                callContext12.value1 = obj22;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 28:
                CallContext callContext13 = callContext2;
                Object obj23 = obj2;
                Object obj24 = obj23;
                if (!(obj23 instanceof Symbol)) {
                    return -786431;
                }
                callContext13.value1 = obj24;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    static int hashForEqv(Object obj) {
        return obj.hashCode();
    }

    public static hashtable.HashTable makeEqHashtable(int i) {
        hashtable.HashTable hashTable;
        int i2 = i;
        new hashtable.HashTable(Scheme.isEq, hash$Mnby$Mnidentity, AbstractHashTable.DEFAULT_INITIAL_SIZE);
        return hashTable;
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 3:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 5:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            default:
                return super.match0(moduleMethod2, callContext2);
        }
    }

    public static hashtable.HashTable makeEqvHashtable(int i) {
        hashtable.HashTable hashTable;
        int i2 = i;
        new hashtable.HashTable(Scheme.isEqv, hash$Mnfor$Mneqv, AbstractHashTable.DEFAULT_INITIAL_SIZE);
        return hashTable;
    }

    public Object apply0(ModuleMethod moduleMethod) {
        ModuleMethod moduleMethod2 = moduleMethod;
        switch (moduleMethod2.selector) {
            case 3:
                return makeEqHashtable();
            case 5:
                return makeEqvHashtable();
            default:
                return super.apply0(moduleMethod2);
        }
    }

    public static hashtable.HashTable makeHashtable(Procedure comparison, Procedure hash, int size) {
        hashtable.HashTable hashTable;
        new hashtable.HashTable(comparison, hash, size);
        return hashTable;
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 7:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (!(obj5 instanceof Procedure)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                CallContext callContext4 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (!(obj7 instanceof Procedure)) {
                    return -786430;
                }
                callContext4.value2 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 13:
                CallContext callContext5 = callContext2;
                Object obj9 = obj3;
                Object obj10 = obj9;
                if (!(obj9 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext5.value1 = obj10;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 14:
                CallContext callContext6 = callContext2;
                Object obj11 = obj3;
                Object obj12 = obj11;
                if (!(obj11 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext6.value1 = obj12;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 16:
                CallContext callContext7 = callContext2;
                Object obj13 = obj3;
                Object obj14 = obj13;
                if (!(obj13 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext7.value1 = obj14;
                CallContext callContext8 = callContext2;
                Object obj15 = obj4;
                Object obj16 = obj15;
                Object obj17 = obj15;
                if (1 == 0) {
                    return -786430;
                }
                callContext8.value2 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 18:
                CallContext callContext9 = callContext2;
                Object obj18 = obj3;
                Object obj19 = obj18;
                if (!(obj18 instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext9.value1 = obj19;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 7:
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
            case 11:
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
            case 12:
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
            default:
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
    }

    public static boolean isHashtable(Object obj) {
        return obj instanceof hashtable.HashTable;
    }

    public static int hashtableSize(hashtable.HashTable ht) {
        return ht.size();
    }

    public static Object hashtableRef(hashtable.HashTable ht, Object key, Object obj) {
        Object obj2 = obj;
        HashNode node = ht.getNode(key);
        return node == null ? obj2 : node.getValue();
    }

    public static void hashtableSet$Ex(hashtable.HashTable hashTable, Object key, Object value) {
        hashtable.HashTable ht = hashTable;
        hashtable.hashtableCheckMutable(ht);
        Object put = ht.put(key, value);
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        switch (moduleMethod2.selector) {
            case 7:
                try {
                    try {
                        try {
                            return makeHashtable((Procedure) obj4, (Procedure) obj5, ((Number) obj6).intValue());
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th6 = th5;
                            new WrongType(classCastException, "make-hashtable", 3, obj6);
                            throw th6;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th7 = th4;
                        new WrongType(classCastException2, "make-hashtable", 2, obj5);
                        throw th7;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th8 = th3;
                    new WrongType(classCastException3, "make-hashtable", 1, obj4);
                    throw th8;
                }
            case 11:
                try {
                    return hashtableRef((hashtable.HashTable) obj4, obj5, obj6);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th9 = th2;
                    new WrongType(classCastException4, "hashtable-ref", 1, obj4);
                    throw th9;
                }
            case 12:
                try {
                    hashtableSet$Ex((hashtable.HashTable) obj4, obj5, obj6);
                    return Values.empty;
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th10 = th;
                    new WrongType(classCastException5, "hashtable-set!", 1, obj4);
                    throw th10;
                }
            default:
                return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
    }

    public static void hashtableDelete$Ex(hashtable.HashTable hashTable, Object key) {
        hashtable.HashTable ht = hashTable;
        hashtable.hashtableCheckMutable(ht);
        Object remove = ht.remove(key);
    }

    public static boolean isHashtableContains(hashtable.HashTable ht, Object key) {
        return ((ht.getNode(key) == null ? 1 : 0) + 1) & true;
    }

    public static Object hashtableUpdate$Ex(hashtable.HashTable hashTable, Object obj, Procedure procedure, Object obj2) {
        Object value;
        hashtable.HashTable ht = hashTable;
        Object key = obj;
        Procedure proc = procedure;
        Object obj3 = obj2;
        hashtable.hashtableCheckMutable(ht);
        HashNode node = ht.getNode(key);
        if (node == null) {
            hashtableSet$Ex(ht, key, proc.apply1(obj3));
            value = Values.empty;
        } else {
            value = node.setValue(proc.apply1(node.getValue()));
        }
        return value;
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        Throwable th;
        Throwable th2;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        if (moduleMethod2.selector != 15) {
            return super.apply4(moduleMethod2, obj5, obj6, obj7, obj8);
        }
        try {
            try {
                return hashtableUpdate$Ex((hashtable.HashTable) obj5, obj6, (Procedure) obj7, obj8);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th3 = th2;
                new WrongType(classCastException, "hashtable-update!", 3, obj7);
                throw th3;
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "hashtable-update!", 1, obj5);
            throw th4;
        }
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 15) {
            return super.match4(moduleMethod2, obj5, obj6, obj7, obj8, callContext2);
        }
        CallContext callContext3 = callContext2;
        Object obj9 = obj5;
        Object obj10 = obj9;
        if (!(obj9 instanceof hashtable.HashTable)) {
            return -786431;
        }
        callContext3.value1 = obj10;
        callContext2.value2 = obj6;
        CallContext callContext4 = callContext2;
        Object obj11 = obj7;
        Object obj12 = obj11;
        if (!(obj11 instanceof Procedure)) {
            return -786429;
        }
        callContext4.value3 = obj12;
        callContext2.value4 = obj8;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 4;
        return 0;
    }

    public static hashtable.HashTable hashtableCopy(hashtable.HashTable ht, boolean mutable) {
        hashtable.HashTable ht2;
        new hashtable.HashTable(ht, mutable);
        return ht2;
    }

    public static void hashtableClear$Ex(hashtable.HashTable hashTable, int i) {
        hashtable.HashTable ht = hashTable;
        int i2 = i;
        hashtable.hashtableCheckMutable(ht);
        ht.clear();
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
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 7:
                try {
                    try {
                        return makeHashtable((Procedure) obj3, (Procedure) obj4);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th9 = th8;
                        new WrongType(classCastException, "make-hashtable", 2, obj4);
                        throw th9;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th10 = th7;
                    new WrongType(classCastException2, "make-hashtable", 1, obj3);
                    throw th10;
                }
            case 13:
                try {
                    hashtableDelete$Ex((hashtable.HashTable) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th11 = th6;
                    new WrongType(classCastException3, "hashtable-delete!", 1, obj3);
                    throw th11;
                }
            case 14:
                try {
                    return isHashtableContains((hashtable.HashTable) obj3, obj4) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th12 = th5;
                    new WrongType(classCastException4, "hashtable-contains?", 1, obj3);
                    throw th12;
                }
            case 16:
                try {
                    try {
                        return hashtableCopy((hashtable.HashTable) obj3, obj4 != Boolean.FALSE);
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th13 = th4;
                        new WrongType(classCastException5, "hashtable-copy", 2, obj4);
                        throw th13;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th14 = th3;
                    new WrongType(classCastException6, "hashtable-copy", 1, obj3);
                    throw th14;
                }
            case 18:
                try {
                    try {
                        hashtableClear$Ex((hashtable.HashTable) obj3, ((Number) obj4).intValue());
                        return Values.empty;
                    } catch (ClassCastException e7) {
                        ClassCastException classCastException7 = e7;
                        Throwable th15 = th2;
                        new WrongType(classCastException7, "hashtable-clear!", 2, obj4);
                        throw th15;
                    }
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th16 = th;
                    new WrongType(classCastException8, "hashtable-clear!", 1, obj3);
                    throw th16;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    public static FVector hashtableKeys(hashtable.HashTable ht) {
        return ht.keysVector();
    }

    public static Object hashtableEntries(hashtable.HashTable ht) {
        Pair pair = ht.entriesVectorPair();
        Object[] objArr = new Object[2];
        objArr[0] = C1245lists.car.apply1(pair);
        Object[] objArr2 = objArr;
        objArr2[1] = C1245lists.cdr.apply1(pair);
        return misc.values(objArr2);
    }

    public static Procedure hashtableEquivalenceFunction(hashtable.HashTable hashTable) {
        hashtable.HashTable ht = hashTable;
        return (Procedure) ht.equivalenceFunction.apply1(ht);
    }

    public static Object hashtableHashFunction(hashtable.HashTable hashTable) {
        Object obj;
        hashtable.HashTable ht = hashTable;
        Object hasher = ht.hashFunction.apply1(ht);
        Object x = Scheme.isEqv.apply2(hasher, hash$Mnby$Mnidentity);
        if (x == Boolean.FALSE ? Scheme.isEqv.apply2(hasher, hash$Mnfor$Mneqv) == Boolean.FALSE : x == Boolean.FALSE) {
            obj = hasher;
        } else {
            obj = Boolean.FALSE;
        }
        return obj;
    }

    public static Object isHashtableMutable(hashtable.HashTable ht) {
        return Scheme.applyToArgs.apply1(ht.mutable ? Boolean.TRUE : Boolean.FALSE);
    }

    public static int equalHash(Object key) {
        return key.hashCode();
    }

    public static int stringHash(CharSequence s) {
        return s.hashCode();
    }

    public static int stringCiHash(CharSequence s) {
        return s.toString().toLowerCase().hashCode();
    }

    public static int symbolHash(Symbol s) {
        return s.hashCode();
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
        Throwable th9;
        Throwable th10;
        Throwable th11;
        Throwable th12;
        Throwable th13;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return Integer.valueOf(hashByIdentity(obj2));
            case 2:
                return Integer.valueOf(hashForEqv(obj2));
            case 3:
                try {
                    return makeEqHashtable(((Number) obj2).intValue());
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th14 = th13;
                    new WrongType(classCastException, "make-eq-hashtable", 1, obj2);
                    throw th14;
                }
            case 5:
                try {
                    return makeEqvHashtable(((Number) obj2).intValue());
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th15 = th12;
                    new WrongType(classCastException2, "make-eqv-hashtable", 1, obj2);
                    throw th15;
                }
            case 9:
                return isHashtable(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 10:
                try {
                    return Integer.valueOf(hashtableSize((hashtable.HashTable) obj2));
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th16 = th11;
                    new WrongType(classCastException3, "hashtable-size", 1, obj2);
                    throw th16;
                }
            case 16:
                try {
                    return hashtableCopy((hashtable.HashTable) obj2);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th17 = th10;
                    new WrongType(classCastException4, "hashtable-copy", 1, obj2);
                    throw th17;
                }
            case 18:
                try {
                    hashtableClear$Ex((hashtable.HashTable) obj2);
                    return Values.empty;
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th18 = th9;
                    new WrongType(classCastException5, "hashtable-clear!", 1, obj2);
                    throw th18;
                }
            case 20:
                try {
                    return hashtableKeys((hashtable.HashTable) obj2);
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th19 = th8;
                    new WrongType(classCastException6, "hashtable-keys", 1, obj2);
                    throw th19;
                }
            case 21:
                try {
                    return hashtableEntries((hashtable.HashTable) obj2);
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th20 = th7;
                    new WrongType(classCastException7, "hashtable-entries", 1, obj2);
                    throw th20;
                }
            case 22:
                try {
                    return hashtableEquivalenceFunction((hashtable.HashTable) obj2);
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th21 = th6;
                    new WrongType(classCastException8, "hashtable-equivalence-function", 1, obj2);
                    throw th21;
                }
            case 23:
                try {
                    return hashtableHashFunction((hashtable.HashTable) obj2);
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th22 = th5;
                    new WrongType(classCastException9, "hashtable-hash-function", 1, obj2);
                    throw th22;
                }
            case 24:
                try {
                    return isHashtableMutable((hashtable.HashTable) obj2);
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th23 = th4;
                    new WrongType(classCastException10, "hashtable-mutable?", 1, obj2);
                    throw th23;
                }
            case 25:
                return Integer.valueOf(equalHash(obj2));
            case 26:
                try {
                    return Integer.valueOf(stringHash((CharSequence) obj2));
                } catch (ClassCastException e11) {
                    ClassCastException classCastException11 = e11;
                    Throwable th24 = th3;
                    new WrongType(classCastException11, "string-hash", 1, obj2);
                    throw th24;
                }
            case 27:
                try {
                    return Integer.valueOf(stringCiHash((CharSequence) obj2));
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th25 = th2;
                    new WrongType(classCastException12, "string-ci-hash", 1, obj2);
                    throw th25;
                }
            case 28:
                try {
                    return Integer.valueOf(symbolHash((Symbol) obj2));
                } catch (ClassCastException e13) {
                    ClassCastException classCastException13 = e13;
                    Throwable th26 = th;
                    new WrongType(classCastException13, "symbol-hash", 1, obj2);
                    throw th26;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }
}
