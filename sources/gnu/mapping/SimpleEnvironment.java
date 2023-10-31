package gnu.mapping;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Set;

public class SimpleEnvironment extends Environment {
    int currentTimestamp;
    int log2Size;
    private int mask;
    int num_bindings;
    NamedLocation sharedTail;
    NamedLocation[] table;

    public int size() {
        return this.num_bindings;
    }

    public static Location getCurrentLocation(String name) {
        return getCurrent().getLocation((Object) name, true);
    }

    public static Object lookup_global(Symbol symbol) throws UnboundLocationException {
        Throwable th;
        Symbol name = symbol;
        Location binding = getCurrent().lookup(name);
        if (binding != null) {
            return binding.get();
        }
        Throwable th2 = th;
        new UnboundLocationException((Object) name);
        throw th2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SimpleEnvironment() {
        this(64);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SimpleEnvironment(String name) {
        this();
        setName(name);
    }

    public SimpleEnvironment(int i) {
        NamedLocation namedLocation;
        int capacity = i;
        this.log2Size = 4;
        while (capacity > (1 << this.log2Size)) {
            this.log2Size++;
        }
        int capacity2 = 1 << this.log2Size;
        this.table = new NamedLocation[capacity2];
        this.mask = capacity2 - 1;
        new PlainLocation((Symbol) null, (Object) null, this);
        this.sharedTail = namedLocation;
    }

    public NamedLocation lookup(Symbol name, Object property, int hash) {
        return lookupDirect(name, property, hash);
    }

    public NamedLocation lookupDirect(Symbol symbol, Object obj, int hash) {
        Symbol name = symbol;
        Object property = obj;
        NamedLocation namedLocation = this.table[hash & this.mask];
        while (true) {
            NamedLocation loc = namedLocation;
            if (loc == null) {
                return null;
            }
            if (loc.matches(name, property)) {
                return loc;
            }
            namedLocation = loc.next;
        }
    }

    public synchronized NamedLocation getLocation(Symbol symbol, Object obj, int i, boolean z) {
        NamedLocation addUnboundLocation;
        Symbol name = symbol;
        Object property = obj;
        int hash = i;
        boolean create = z;
        synchronized (this) {
            NamedLocation loc = lookup(name, property, hash);
            if (loc != null) {
                addUnboundLocation = loc;
            } else if (!create) {
                addUnboundLocation = null;
            } else {
                addUnboundLocation = addUnboundLocation(name, property, hash);
            }
        }
        return addUnboundLocation;
    }

    /* access modifiers changed from: protected */
    public NamedLocation addUnboundLocation(Symbol name, Object property, int hash) {
        NamedLocation loc = newEntry(name, property, hash & this.mask);
        loc.base = null;
        loc.value = Location.UNBOUND;
        return loc;
    }

    public void put(Symbol symbol, Object property, Object obj) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Symbol key = symbol;
        Object newValue = obj;
        Location loc = getLocation(key, property, (this.flags & 4) != 0);
        if (loc == null) {
            Throwable th3 = th2;
            new UnboundLocationException((Object) key);
            throw th3;
        } else if (loc.isConstant()) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("attempt to modify read-only location: ").append(key).append(" in ").append(this).append(" loc:").append(loc).toString());
            throw th4;
        } else {
            loc.set(newValue);
        }
    }

    /* access modifiers changed from: package-private */
    public NamedLocation newLocation(Symbol symbol, Object obj) {
        NamedLocation namedLocation;
        NamedLocation namedLocation2;
        Symbol name = symbol;
        Object property = obj;
        if ((this.flags & 8) != 0) {
            new SharedLocation(name, property, this.currentTimestamp);
            return namedLocation2;
        }
        new PlainLocation(name, property);
        return namedLocation;
    }

    /* access modifiers changed from: package-private */
    public NamedLocation newEntry(Symbol name, Object property, int i) {
        int index = i;
        NamedLocation loc = newLocation(name, property);
        NamedLocation first = this.table[index];
        loc.next = first == null ? this.sharedTail : first;
        this.table[index] = loc;
        this.num_bindings++;
        if (this.num_bindings >= this.table.length) {
            rehash();
        }
        return loc;
    }

    public NamedLocation define(Symbol symbol, Object obj, int hash, Object obj2) {
        Symbol sym = symbol;
        Object property = obj;
        Object newValue = obj2;
        int index = hash & this.mask;
        NamedLocation namedLocation = this.table[index];
        while (true) {
            NamedLocation loc = namedLocation;
            if (loc == null) {
                NamedLocation loc2 = newEntry(sym, property, index);
                loc2.set(newValue);
                return loc2;
            } else if (loc.matches(sym, property)) {
                if (!loc.isBound() ? !getCanRedefine() : !getCanDefine()) {
                    redefineError(sym, property, loc);
                }
                loc.base = null;
                loc.value = newValue;
                return loc;
            } else {
                namedLocation = loc.next;
            }
        }
    }

    public void define(Symbol symbol, Object obj, Object newValue) {
        Symbol sym = symbol;
        Object property = obj;
        NamedLocation define = define(sym, property, sym.hashCode() ^ System.identityHashCode(property), newValue);
    }

    /* access modifiers changed from: protected */
    public void redefineError(Symbol name, Object obj, Location location) {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        Location location2 = location;
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("prohibited define/redefine of ").append(name).append(" in ").append(this).toString());
        throw th2;
    }

    public NamedLocation addLocation(Symbol symbol, Object obj, Location loc) {
        Symbol name = symbol;
        Object property = obj;
        return addLocation(name, property, name.hashCode() ^ System.identityHashCode(property), loc);
    }

    /* access modifiers changed from: package-private */
    public NamedLocation addLocation(Symbol symbol, Object obj, int i, Location location) {
        Symbol name = symbol;
        Object property = obj;
        int hash = i;
        Location loc = location;
        if ((loc instanceof ThreadLocation) && ((ThreadLocation) loc).property == property) {
            loc = ((ThreadLocation) loc).getLocation();
        }
        NamedLocation nloc = lookupDirect(name, property, hash);
        if (loc == nloc) {
            return nloc;
        }
        boolean bound = nloc != null;
        if (!bound) {
            nloc = addUnboundLocation(name, property, hash);
        }
        if ((this.flags & 3) != 3) {
            if (bound) {
                bound = nloc.isBound();
            }
            if (!bound ? !((this.flags & 1) != 0 || !loc.isBound()) : (this.flags & 2) == 0) {
                redefineError(name, property, nloc);
            }
        }
        if ((this.flags & 32) != 0) {
            nloc.base = ((SimpleEnvironment) ((InheritingEnvironment) this).getParent(0)).addLocation(name, property, hash, loc);
        } else {
            nloc.base = loc;
        }
        nloc.value = IndirectableLocation.INDIRECT_FLUIDS;
        return nloc;
    }

    /* access modifiers changed from: package-private */
    public void rehash() {
        NamedLocation[] oldTable = this.table;
        int oldCapacity = oldTable.length;
        int newCapacity = 2 * oldCapacity;
        NamedLocation[] newTable = new NamedLocation[newCapacity];
        int newMask = newCapacity - 1;
        int i = oldCapacity;
        while (true) {
            i--;
            if (i >= 0) {
                NamedLocation namedLocation = oldTable[i];
                while (true) {
                    NamedLocation element = namedLocation;
                    if (element != null && element != this.sharedTail) {
                        NamedLocation next = element.next;
                        int j = (element.name.hashCode() ^ System.identityHashCode(element.property)) & newMask;
                        NamedLocation head = newTable[j];
                        if (head == null) {
                            head = this.sharedTail;
                        }
                        element.next = head;
                        newTable[j] = element;
                        namedLocation = next;
                    }
                }
            } else {
                this.table = newTable;
                this.log2Size++;
                this.mask = newMask;
                return;
            }
        }
    }

    public Location unlink(Symbol symbol, Object obj, int hash) {
        Symbol symbol2 = symbol;
        Object property = obj;
        int index = hash & this.mask;
        NamedLocation prev = null;
        NamedLocation namedLocation = this.table[index];
        while (true) {
            NamedLocation loc = namedLocation;
            if (loc == null) {
                return null;
            }
            NamedLocation next = loc.next;
            if (loc.matches(symbol2, property)) {
                if (!getCanRedefine()) {
                    redefineError(symbol2, property, loc);
                }
                if (prev == null) {
                    this.table[index] = next;
                } else {
                    prev.next = loc;
                }
                this.num_bindings--;
                return loc;
            }
            prev = loc;
            namedLocation = next;
        }
    }

    public LocationEnumeration enumerateLocations() {
        LocationEnumeration locationEnumeration;
        new LocationEnumeration(this.table, 1 << this.log2Size);
        LocationEnumeration it = locationEnumeration;
        it.env = this;
        return it;
    }

    public LocationEnumeration enumerateAllLocations() {
        return enumerateLocations();
    }

    /* access modifiers changed from: protected */
    public boolean hasMoreElements(LocationEnumeration locationEnumeration) {
        LocationEnumeration it = locationEnumeration;
        while (true) {
            if (it.nextLoc == null) {
                it.prevLoc = null;
                LocationEnumeration locationEnumeration2 = it;
                int i = locationEnumeration2.index - 1;
                int i2 = i;
                locationEnumeration2.index = i;
                if (i2 < 0) {
                    return false;
                }
                it.nextLoc = it.bindings[it.index];
                if (it.nextLoc == null) {
                    continue;
                }
            }
            if (it.nextLoc.name != null) {
                return true;
            }
            it.nextLoc = it.nextLoc.next;
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getSymbol());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setSymbol(in.readObject());
    }

    public Object readResolve() throws ObjectStreamException {
        String name = getName();
        Environment env = (Environment) envTable.get(name);
        if (env != null) {
            return env;
        }
        Object put = envTable.put(name, this);
        return this;
    }

    public Set entrySet() {
        Set set;
        new EnvironmentMappings(this);
        return set;
    }

    public String toStringVerbose() {
        StringBuffer stringBuffer;
        StringBuilder sb;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        toStringBase(sbuf);
        new StringBuilder();
        return sb.append("#<environment ").append(getName()).append(" num:").append(this.num_bindings).append(" ts:").append(this.currentTimestamp).append(sbuf).append('>').toString();
    }

    /* access modifiers changed from: protected */
    public void toStringBase(StringBuffer sbuf) {
    }
}
