package kawa.lib.kawa;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.SetNamedPart;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.util.GeneralHashTable;
import gnu.kawa.util.HashNode;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lib.C1245lists;
import kawa.lib.misc;
import kawa.standard.thisRef;

/* compiled from: hashtable.scm */
public class hashtable extends ModuleBody {
    public static final Location $Prvt$do = StaticFieldLocation.make("kawa.lib.std_syntax", "do");
    public static final Class $Prvt$hashnode = HashNode.class;
    public static final Location $Prvt$let$St = StaticFieldLocation.make("kawa.lib.std_syntax", "let$St");
    public static final hashtable $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    public static final Class hashtable = HashTable.class;
    public static final ModuleMethod hashtable$Mncheck$Mnmutable;

    public hashtable() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        hashtable hashtable2;
        ModuleMethod moduleMethod;
        new SimpleSymbol("hashtable-check-mutable");
        Lit1 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("mutable");
        Lit0 = (SimpleSymbol) simpleSymbol2.readResolve();
        new hashtable();
        $instance = hashtable2;
        new ModuleMethod($instance, 1, Lit1, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mncheck$Mnmutable = moduleMethod;
        $instance.run();
    }

    /* compiled from: hashtable.scm */
    public class HashTable extends GeneralHashTable {
        public Procedure equivalenceFunction;
        public Procedure hashFunction;
        public boolean mutable;

        private void $finit$() {
            this.mutable = true;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public HashTable(Procedure procedure, Procedure procedure2, int i) {
            super(i);
            $finit$();
            this.equivalenceFunction = procedure;
            this.hashFunction = procedure2;
        }

        public HashTable(Procedure procedure, Procedure procedure2) {
            $finit$();
            this.equivalenceFunction = procedure;
            this.hashFunction = procedure2;
        }

        public HashTable(HashTable hashTable, boolean z) {
            HashTable hashTable2 = hashTable;
            $finit$();
            Invoke invoke = Invoke.invokeSpecial;
            Object[] objArr = new Object[5];
            objArr[0] = hashtable.hashtable;
            Object[] objArr2 = objArr;
            objArr2[1] = this;
            Object[] objArr3 = objArr2;
            objArr3[2] = hashTable2.equivalenceFunction.apply0();
            Object[] objArr4 = objArr3;
            objArr4[3] = hashTable2.hashFunction.apply0();
            Object[] objArr5 = objArr4;
            objArr5[4] = Integer.valueOf(hashTable2.size() + 100);
            Object applyN = invoke.applyN(objArr5);
            putAll(hashTable2);
            Object apply3 = SetNamedPart.setNamedPart.apply3(thisRef.thisSyntax, hashtable.Lit0, z ? Boolean.TRUE : Boolean.FALSE);
        }

        public int hash(Object key) {
            return ((Number) this.hashFunction.apply1(key)).intValue();
        }

        public boolean matches(Object value1, Object value2) {
            return this.equivalenceFunction.apply2(value1, value2) != Boolean.FALSE;
        }

        public void walk(Procedure procedure) {
            Throwable th;
            Procedure proc = procedure;
            Entry[] entryArr = this.table;
            Entry[] entryArr2 = entryArr;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                for (int i = table.length - 1; i >= 0; i--) {
                    HashNode hashNode = table[i];
                    while (true) {
                        HashNode node = hashNode;
                        if (node == null) {
                            break;
                        }
                        Object apply2 = proc.apply2(node.getKey(), node.getValue());
                        hashNode = getEntryNext(node);
                    }
                }
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "table", -2, (Object) entryArr2);
                throw th2;
            }
        }

        public Object fold(Procedure procedure, Object obj) {
            Throwable th;
            Procedure proc = procedure;
            Object acc = obj;
            Entry[] entryArr = this.table;
            Entry[] entryArr2 = entryArr;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                for (int i = table.length - 1; i >= 0; i--) {
                    HashNode hashNode = table[i];
                    while (true) {
                        HashNode node = hashNode;
                        if (node == null) {
                            break;
                        }
                        acc = proc.apply3(node.getKey(), node.getValue(), acc);
                        hashNode = getEntryNext(node);
                    }
                }
                return acc;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "table", -2, (Object) entryArr2);
                throw th2;
            }
        }

        public FVector keysVector() {
            FVector fVector;
            Throwable th;
            new FVector();
            FVector v = fVector;
            Entry[] entryArr = this.table;
            Entry[] entryArr2 = entryArr;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                for (int i = table.length - 1; i >= 0; i--) {
                    HashNode hashNode = table[i];
                    while (true) {
                        HashNode node = hashNode;
                        if (node == null) {
                            break;
                        }
                        boolean add = v.add(node.getKey());
                        hashNode = getEntryNext(node);
                    }
                }
                return v;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "table", -2, (Object) entryArr2);
                throw th2;
            }
        }

        public Pair entriesVectorPair() {
            FVector fVector;
            FVector fVector2;
            Throwable th;
            new FVector();
            new FVector();
            FVector vals = fVector2;
            FVector keys = fVector;
            Entry[] entryArr = this.table;
            Entry[] entryArr2 = entryArr;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                for (int i = table.length - 1; i >= 0; i--) {
                    HashNode hashNode = table[i];
                    while (true) {
                        HashNode node = hashNode;
                        if (node == null) {
                            break;
                        }
                        boolean add = keys.add(node.getKey());
                        boolean add2 = vals.add(node.getValue());
                        hashNode = getEntryNext(node);
                    }
                }
                return C1245lists.cons(keys, vals);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "table", -2, (Object) entryArr2);
                throw th2;
            }
        }

        public Object toAlist() {
            Throwable th;
            Pair result = LList.Empty;
            Entry[] entryArr = this.table;
            Entry[] entryArr2 = entryArr;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                for (int i = table.length - 1; i >= 0; i--) {
                    HashNode hashNode = table[i];
                    while (true) {
                        HashNode node = hashNode;
                        if (node == null) {
                            break;
                        }
                        result = C1245lists.cons(C1245lists.cons(node.getKey(), node.getValue()), result);
                        hashNode = getEntryNext(node);
                    }
                }
                return result;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "table", -2, (Object) entryArr2);
                throw th2;
            }
        }

        public LList toNodeList() {
            Throwable th;
            Pair result = LList.Empty;
            Entry[] entryArr = this.table;
            Entry[] entryArr2 = entryArr;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                for (int i = table.length - 1; i >= 0; i--) {
                    HashNode hashNode = table[i];
                    while (true) {
                        HashNode node = hashNode;
                        if (node == null) {
                            break;
                        }
                        result = C1245lists.cons(node, result);
                        hashNode = getEntryNext(node);
                    }
                }
                return result;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "table", -2, (Object) entryArr2);
                throw th2;
            }
        }

        public HashNode[] toNodeArray() {
            Throwable th;
            HashNode[] result = new HashNode[size()];
            int i = 0;
            Entry[] entryArr = this.table;
            Entry[] entryArr2 = entryArr;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                for (int i2 = table.length - 1; i2 >= 0; i2--) {
                    HashNode hashNode = table[i2];
                    while (true) {
                        HashNode node = hashNode;
                        if (node == null) {
                            break;
                        }
                        result[i] = node;
                        i++;
                        hashNode = getEntryNext(node);
                    }
                }
                return result;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "table", -2, (Object) entryArr2);
                throw th2;
            }
        }

        public void putAll(HashTable hashTable) {
            Throwable th;
            HashTable other = hashTable;
            Entry[] entryArr = other.table;
            Entry[] entryArr2 = entryArr;
            try {
                HashNode[] table = (HashNode[]) entryArr;
                for (int i = table.length - 1; i >= 0; i--) {
                    HashNode hashNode = table[i];
                    while (true) {
                        HashNode node = hashNode;
                        if (node == null) {
                            break;
                        }
                        Object put = put(node.getKey(), node.getValue());
                        hashNode = other.getEntryNext(node);
                    }
                }
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "table", -2, (Object) entryArr2);
                throw th2;
            }
        }

        public Object clone() {
            HashTable hashTable;
            new HashTable(this, true);
            return hashTable;
        }
    }

    public static void hashtableCheckMutable(HashTable ht) {
        if (!ht.mutable) {
            Object error$V = misc.error$V("cannot modify non-mutable hashtable", new Object[0]);
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        if (moduleMethod2.selector != 1) {
            return super.apply1(moduleMethod2, obj2);
        }
        try {
            hashtableCheckMutable((HashTable) obj2);
            return Values.empty;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "hashtable-check-mutable", 1, obj2);
            throw th2;
        }
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 1) {
            return super.match1(moduleMethod2, obj2, callContext2);
        }
        CallContext callContext3 = callContext2;
        Object obj3 = obj2;
        Object obj4 = obj3;
        if (!(obj3 instanceof HashTable)) {
            return -786431;
        }
        callContext3.value1 = obj4;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 1;
        return 0;
    }
}
