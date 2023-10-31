package gnu.mapping;

import java.util.Hashtable;

public abstract class Environment extends PropertySet {
    static final int CAN_DEFINE = 1;
    static final int CAN_IMPLICITLY_DEFINE = 4;
    static final int CAN_REDEFINE = 2;
    static final int DIRECT_INHERITED_ON_SET = 16;
    public static final int INDIRECT_DEFINES = 32;
    static final int THREAD_SAFE = 8;
    protected static final InheritedLocal curEnvironment;
    static final Hashtable envTable;
    static Environment global;
    int flags = 23;

    public abstract NamedLocation addLocation(Symbol symbol, Object obj, Location location);

    public abstract void define(Symbol symbol, Object obj, Object obj2);

    public abstract LocationEnumeration enumerateAllLocations();

    public abstract LocationEnumeration enumerateLocations();

    public abstract NamedLocation getLocation(Symbol symbol, Object obj, int i, boolean z);

    /* access modifiers changed from: protected */
    public abstract boolean hasMoreElements(LocationEnumeration locationEnumeration);

    public abstract NamedLocation lookup(Symbol symbol, Object obj, int i);

    public Environment() {
    }

    public static void setGlobal(Environment env) {
        global = env;
    }

    public static Environment getGlobal() {
        return global;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlag(boolean setting, int i) {
        int flag = i;
        if (setting) {
            this.flags |= flag;
            return;
        }
        this.flags &= flag ^ -1;
    }

    public boolean getCanDefine() {
        return (this.flags & 1) != 0;
    }

    public void setCanDefine(boolean canDefine) {
        if (canDefine) {
            this.flags |= 1;
            return;
        }
        this.flags &= -2;
    }

    public boolean getCanRedefine() {
        return (this.flags & 2) != 0;
    }

    public void setCanRedefine(boolean canRedefine) {
        if (canRedefine) {
            this.flags |= 2;
            return;
        }
        this.flags &= -3;
    }

    public final boolean isLocked() {
        return (this.flags & 3) == 0;
    }

    public void setLocked() {
        this.flags &= -8;
    }

    public final void setIndirectDefines() {
        this.flags |= 32;
        ((InheritingEnvironment) this).baseTimestamp = Integer.MAX_VALUE;
    }

    public final Location getLocation(Symbol key, Object property) {
        return getLocation(key, property, true);
    }

    public final Location getLocation(Symbol key) {
        return getLocation(key, (Object) null, true);
    }

    public final Location lookup(Symbol key, Object property) {
        return getLocation(key, property, false);
    }

    public final Location lookup(Symbol key) {
        return getLocation(key, (Object) null, false);
    }

    public final NamedLocation getLocation(Symbol symbol, Object obj, boolean create) {
        Symbol name = symbol;
        Object property = obj;
        return getLocation(name, property, name.hashCode() ^ System.identityHashCode(property), create);
    }

    public final Location getLocation(Object obj, boolean z) {
        Object key = obj;
        boolean create = z;
        Object property = null;
        if (key instanceof EnvironmentKey) {
            EnvironmentKey k = (EnvironmentKey) key;
            key = k.getKeySymbol();
            property = k.getKeyProperty();
        }
        return getLocation(key instanceof Symbol ? (Symbol) key : getSymbol((String) key), property, create);
    }

    public boolean isBound(Symbol key, Object property) {
        Location loc = lookup(key, property);
        if (loc == null) {
            return false;
        }
        return loc.isBound();
    }

    public final boolean isBound(Symbol key) {
        return isBound(key, (Object) null);
    }

    public final boolean containsKey(Object obj) {
        Object key = obj;
        Object property = null;
        if (key instanceof EnvironmentKey) {
            EnvironmentKey k = (EnvironmentKey) key;
            key = k.getKeySymbol();
            property = k.getKeyProperty();
        }
        return isBound(key instanceof Symbol ? (Symbol) key : getSymbol((String) key), property);
    }

    public final Object getChecked(String str) {
        Throwable th;
        StringBuilder sb;
        String name = str;
        Object value = get(name, (Object) Location.UNBOUND);
        if (value != Location.UNBOUND) {
            return value;
        }
        Throwable th2 = th;
        new StringBuilder();
        new UnboundLocationException((Object) sb.append(name).append(" in ").append(this).toString());
        throw th2;
    }

    public Object get(Symbol key, Object property, Object obj) {
        Object defaultValue = obj;
        Location loc = lookup(key, property);
        if (loc == null) {
            return defaultValue;
        }
        return loc.get(defaultValue);
    }

    public final Object get(EnvironmentKey environmentKey, Object defaultValue) {
        EnvironmentKey key = environmentKey;
        return get(key.getKeySymbol(), key.getKeyProperty(), defaultValue);
    }

    public final Object get(String key, Object defaultValue) {
        return get(getSymbol(key), (Object) null, defaultValue);
    }

    public Object get(Symbol symbol) {
        Throwable th;
        Symbol sym = symbol;
        String unb = Location.UNBOUND;
        Object val = get(sym, (Object) null, unb);
        if (val != unb) {
            return val;
        }
        Throwable th2 = th;
        new UnboundLocationException((Object) sym);
        throw th2;
    }

    public final Object getFunction(Symbol key, Object defaultValue) {
        return get(key, EnvironmentKey.FUNCTION, defaultValue);
    }

    public final Object getFunction(Symbol symbol) {
        Throwable th;
        Symbol sym = symbol;
        String unb = Location.UNBOUND;
        Object val = get(sym, EnvironmentKey.FUNCTION, unb);
        if (val != unb) {
            return val;
        }
        Throwable th2 = th;
        new UnboundLocationException((Object) sym);
        throw th2;
    }

    public final Object get(Object obj) {
        Object key = obj;
        Object property = null;
        if (key instanceof EnvironmentKey) {
            EnvironmentKey k = (EnvironmentKey) key;
            key = k.getKeySymbol();
            property = k.getKeyProperty();
        }
        return get(key instanceof Symbol ? (Symbol) key : getSymbol((String) key), property, (Object) null);
    }

    public void put(Symbol symbol, Object obj, Object obj2) {
        Symbol key = symbol;
        Object property = obj;
        Object newValue = obj2;
        Location loc = getLocation(key, property);
        if (loc.isConstant()) {
            define(key, property, newValue);
        } else {
            loc.set(newValue);
        }
    }

    public final void put(Symbol key, Object newValue) {
        put(key, (Object) null, newValue);
    }

    public final Object put(Object key, Object newValue) {
        Location loc = getLocation(key, true);
        Object oldValue = loc.get((Object) null);
        loc.set(newValue);
        return oldValue;
    }

    public final void putFunction(Symbol key, Object newValue) {
        put(key, EnvironmentKey.FUNCTION, newValue);
    }

    public final Object put(String key, Object value) {
        return put((Object) key, value);
    }

    public Location unlink(Symbol symbol, Object obj, int i) {
        Throwable th;
        Symbol symbol2 = symbol;
        Object obj2 = obj;
        int i2 = i;
        Throwable th2 = th;
        new RuntimeException("unsupported operation: unlink (aka undefine)");
        throw th2;
    }

    public Object remove(Symbol key, Object property, int hash) {
        Location loc = unlink(key, property, hash);
        if (loc == null) {
            return null;
        }
        Object value = loc.get((Object) null);
        loc.undefine();
        return value;
    }

    public final Object remove(EnvironmentKey environmentKey) {
        EnvironmentKey key = environmentKey;
        Symbol symbol = key.getKeySymbol();
        Object property = key.getKeyProperty();
        return remove(symbol, property, symbol.hashCode() ^ System.identityHashCode(property));
    }

    public final Object remove(Symbol symbol, Object obj) {
        Symbol symbol2 = symbol;
        Object property = obj;
        return remove(symbol2, property, symbol2.hashCode() ^ System.identityHashCode(property));
    }

    public final void remove(Symbol symbol) {
        Symbol sym = symbol;
        Object remove = remove(sym, (Object) null, sym.hashCode());
    }

    public final void removeFunction(Symbol sym) {
        Object remove = remove(sym, EnvironmentKey.FUNCTION);
    }

    public final Object remove(Object obj) {
        Object key = obj;
        if (key instanceof EnvironmentKey) {
            EnvironmentKey k = (EnvironmentKey) key;
            return remove(k.getKeySymbol(), k.getKeyProperty());
        }
        Symbol symbol = key instanceof Symbol ? (Symbol) key : getSymbol((String) key);
        return remove(symbol, (Object) null, symbol.hashCode() ^ System.identityHashCode((Object) null));
    }

    public Namespace defaultNamespace() {
        return Namespace.getDefault();
    }

    public Symbol getSymbol(String name) {
        return defaultNamespace().getSymbol(name);
    }

    static {
        Hashtable hashtable;
        InheritedLocal inheritedLocal;
        new Hashtable(50);
        envTable = hashtable;
        new InheritedLocal();
        curEnvironment = inheritedLocal;
    }

    public static Environment getInstance(String str) {
        Environment environment;
        String name = str;
        if (name == null) {
            name = "";
        }
        Hashtable hashtable = envTable;
        Hashtable hashtable2 = hashtable;
        synchronized (hashtable) {
            try {
                Environment env = (Environment) envTable.get(name);
                if (env != null) {
                    Environment environment2 = env;
                    return environment2;
                }
                new SimpleEnvironment();
                Environment env2 = environment;
                env2.setName(name);
                Object put = envTable.put(name, env2);
                Environment environment3 = env2;
                return environment3;
            } catch (Throwable th) {
                Throwable th2 = th;
                Hashtable hashtable3 = hashtable2;
                throw th2;
            }
        }
    }

    public static Environment current() {
        return getCurrent();
    }

    public static Environment getCurrent() {
        Environment env = (Environment) curEnvironment.get();
        if (env == null) {
            env = make(Thread.currentThread().getName(), global);
            env.flags |= 8;
            curEnvironment.set(env);
        }
        return env;
    }

    public static void setCurrent(Environment env) {
        curEnvironment.set(env);
    }

    public static Environment setSaveCurrent(Environment env) {
        Environment save = (Environment) curEnvironment.get();
        curEnvironment.set(env);
        return save;
    }

    public static void restoreCurrent(Environment saved) {
        curEnvironment.set(saved);
    }

    public static Environment user() {
        return getCurrent();
    }

    public final void addLocation(NamedLocation namedLocation) {
        NamedLocation loc = namedLocation;
        NamedLocation addLocation = addLocation(loc.getKeySymbol(), loc.getKeyProperty(), loc);
    }

    public final void addLocation(EnvironmentKey environmentKey, Location loc) {
        EnvironmentKey key = environmentKey;
        NamedLocation addLocation = addLocation(key.getKeySymbol(), key.getKeyProperty(), loc);
    }

    public static SimpleEnvironment make() {
        SimpleEnvironment simpleEnvironment;
        SimpleEnvironment simpleEnvironment2 = simpleEnvironment;
        new SimpleEnvironment();
        return simpleEnvironment2;
    }

    public static SimpleEnvironment make(String name) {
        SimpleEnvironment simpleEnvironment;
        new SimpleEnvironment(name);
        return simpleEnvironment;
    }

    public static InheritingEnvironment make(String name, Environment parent) {
        InheritingEnvironment inheritingEnvironment;
        new InheritingEnvironment(name, parent);
        return inheritingEnvironment;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("#<environment ").append(getName()).append('>').toString();
    }

    public String toStringVerbose() {
        return toString();
    }

    /* access modifiers changed from: package-private */
    public SimpleEnvironment cloneForThread() {
        InheritingEnvironment inheritingEnvironment;
        NamedLocation namedLocation;
        new InheritingEnvironment((String) null, this);
        InheritingEnvironment env = inheritingEnvironment;
        if (this instanceof InheritingEnvironment) {
            InheritingEnvironment p = (InheritingEnvironment) this;
            int numInherited = p.numInherited;
            env.numInherited = numInherited;
            env.inherited = new Environment[numInherited];
            for (int i = 0; i < numInherited; i++) {
                env.inherited[i] = p.inherited[i];
            }
        }
        LocationEnumeration e = enumerateLocations();
        while (e.hasMoreElements()) {
            Location loc = e.nextLocation();
            Symbol name = loc.getKeySymbol();
            Object property = loc.getKeyProperty();
            if (name != null && (loc instanceof NamedLocation)) {
                NamedLocation nloc = (NamedLocation) loc;
                if (nloc.base == null) {
                    new SharedLocation(name, property, 0);
                    NamedLocation sloc = namedLocation;
                    sloc.value = nloc.value;
                    nloc.base = sloc;
                    nloc.value = null;
                    nloc = sloc;
                }
                env.addUnboundLocation(name, property, name.hashCode() ^ System.identityHashCode(property)).base = nloc;
            }
        }
        return env;
    }

    static class InheritedLocal extends InheritableThreadLocal<Environment> {
        InheritedLocal() {
        }

        /* access modifiers changed from: protected */
        public Environment childValue(Environment environment) {
            Environment parentValue = environment;
            if (parentValue == null) {
                parentValue = Environment.getCurrent();
            }
            SimpleEnvironment env = parentValue.cloneForThread();
            env.flags |= 8;
            env.flags &= -17;
            return env;
        }
    }
}
