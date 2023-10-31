package gnu.mapping;

import gnu.lists.LList;
import gnu.lists.Pair;

public class PropertyLocation extends Location {
    Pair pair;

    public PropertyLocation() {
    }

    public final Object get(Object obj) {
        Object obj2 = obj;
        return this.pair.getCar();
    }

    public boolean isBound() {
        return true;
    }

    public final void set(Object newValue) {
        this.pair.setCar(newValue);
    }

    public static Object getPropertyList(Object symbol, Environment env) {
        return env.get(Symbol.PLIST, symbol, LList.Empty);
    }

    public static Object getPropertyList(Object symbol) {
        return Environment.getCurrent().get(Symbol.PLIST, symbol, LList.Empty);
    }

    /* JADX INFO: finally extract failed */
    public static void setPropertyList(Object obj, Object obj2, Environment environment) {
        PropertyLocation propertyLocation;
        PropertyLocation ploc;
        Object symbol = obj;
        Object plist = obj2;
        Environment env = environment;
        Environment environment2 = env;
        Environment environment3 = environment2;
        synchronized (environment2) {
            try {
                Location lloc = env.lookup(Symbol.PLIST, symbol);
                if (symbol instanceof Symbol) {
                    Symbol sym = (Symbol) symbol;
                    Object p = lloc.get(LList.Empty);
                    while (p instanceof Pair) {
                        Pair pair2 = (Pair) p;
                        Object property = pair2.getCar();
                        if (plistGet(plist, property, (Object) null) != null) {
                            Object remove = env.remove(sym, property);
                        }
                        p = ((Pair) pair2.getCdr()).getCdr();
                    }
                    Object p2 = plist;
                    while (p2 instanceof Pair) {
                        Pair pair3 = (Pair) p2;
                        Object property2 = pair3.getCar();
                        Location loc = env.lookup(sym, property2);
                        if (loc != null) {
                            Location base = loc.getBase();
                            Location loc2 = base;
                            if (base instanceof PropertyLocation) {
                                ploc = (PropertyLocation) loc2;
                                Pair valuePair = (Pair) pair3.getCdr();
                                ploc.pair = valuePair;
                                p2 = valuePair.getCdr();
                            }
                        }
                        new PropertyLocation();
                        ploc = propertyLocation;
                        NamedLocation addLocation = env.addLocation(sym, property2, ploc);
                        Pair valuePair2 = (Pair) pair3.getCdr();
                        ploc.pair = valuePair2;
                        p2 = valuePair2.getCdr();
                    }
                }
                lloc.set(plist);
            } catch (Throwable th) {
                Throwable th2 = th;
                Environment environment4 = environment3;
                throw th2;
            }
        }
    }

    public static void setPropertyList(Object symbol, Object plist) {
        setPropertyList(symbol, plist, Environment.getCurrent());
    }

    public static Object getProperty(Object obj, Object obj2, Object obj3, Environment environment) {
        Object symbol = obj;
        Object property = obj2;
        Object defaultValue = obj3;
        Environment env = environment;
        if (!(symbol instanceof Symbol)) {
            if (!(symbol instanceof String)) {
                return plistGet(env.get(Symbol.PLIST, symbol, LList.Empty), property, defaultValue);
            }
            symbol = Namespace.getDefaultSymbol((String) symbol);
        }
        return env.get((Symbol) symbol, property, defaultValue);
    }

    public static Object getProperty(Object symbol, Object property, Object defaultValue) {
        return getProperty(symbol, property, defaultValue, Environment.getCurrent());
    }

    public static void putProperty(Object obj, Object obj2, Object obj3, Environment environment) {
        Pair pair2;
        Object plist;
        PropertyLocation propertyLocation;
        Object symbol = obj;
        Object property = obj2;
        Object newValue = obj3;
        Environment env = environment;
        if (!(symbol instanceof Symbol)) {
            if (symbol instanceof String) {
                symbol = Namespace.getDefaultSymbol((String) symbol);
            } else {
                Location lloc = env.getLocation(Symbol.PLIST, symbol);
                lloc.set(plistPut(lloc.get(LList.Empty), property, newValue));
                return;
            }
        }
        Location loc = env.lookup((Symbol) symbol, property);
        if (loc != null) {
            Location base = loc.getBase();
            Location loc2 = base;
            if (base instanceof PropertyLocation) {
                ((PropertyLocation) loc2).set(newValue);
                return;
            }
        }
        Location lloc2 = env.getLocation(Symbol.PLIST, symbol);
        new Pair(newValue, lloc2.get(LList.Empty));
        Pair pair3 = pair2;
        new Pair(property, pair3);
        lloc2.set(plist);
        new PropertyLocation();
        PropertyLocation ploc = propertyLocation;
        ploc.pair = pair3;
        NamedLocation addLocation = env.addLocation((Symbol) symbol, property, ploc);
    }

    public static void putProperty(Object symbol, Object property, Object newValue) {
        putProperty(symbol, property, newValue, Environment.getCurrent());
    }

    public static boolean removeProperty(Object obj, Object obj2, Environment environment) {
        Object symbol = obj;
        Object property = obj2;
        Environment env = environment;
        Location ploc = env.lookup(Symbol.PLIST, symbol);
        if (ploc == null) {
            return false;
        }
        Object plist = ploc.get(LList.Empty);
        if (!(plist instanceof Pair)) {
            return false;
        }
        Pair pair2 = (Pair) plist;
        Pair prev = null;
        while (pair2.getCar() != property) {
            Object next = pair2.getCdr();
            if (!(next instanceof Pair)) {
                return false;
            }
            prev = pair2;
            pair2 = (Pair) next;
        }
        Object tail = ((Pair) pair2.getCdr()).getCdr();
        if (prev == null) {
            ploc.set(tail);
        } else {
            prev.setCdr(tail);
        }
        if (symbol instanceof Symbol) {
            Object remove = env.remove((Symbol) symbol, property);
        }
        return true;
    }

    public static boolean removeProperty(Object symbol, Object property) {
        return removeProperty(symbol, property, Environment.getCurrent());
    }

    public static Object plistGet(Object obj, Object obj2, Object obj3) {
        Object plist = obj;
        Object prop = obj2;
        Object dfault = obj3;
        while (plist instanceof Pair) {
            Pair pair2 = (Pair) plist;
            if (pair2.getCar() == prop) {
                return ((Pair) pair2.getCdr()).getCar();
            }
        }
        return dfault;
    }

    public static Object plistPut(Object obj, Object obj2, Object obj3) {
        Object plist;
        Object obj4;
        Object plist2 = obj;
        Object prop = obj2;
        Object value = obj3;
        Object obj5 = plist2;
        while (true) {
            Object p = obj5;
            if (p instanceof Pair) {
                Pair pair2 = (Pair) p;
                Pair next = (Pair) pair2.getCdr();
                if (pair2.getCar() == prop) {
                    next.setCar(value);
                    return plist2;
                }
                obj5 = next.getCdr();
            } else {
                new Pair(value, plist2);
                new Pair(prop, obj4);
                return plist;
            }
        }
    }

    public static Object plistRemove(Object obj, Object obj2) {
        Object plist = obj;
        Object prop = obj2;
        Pair prev = null;
        Object p = plist;
        while (p instanceof Pair) {
            Pair pair2 = (Pair) p;
            Pair next = (Pair) pair2.getCdr();
            p = next.getCdr();
            if (pair2.getCar() != prop) {
                prev = next;
            } else if (prev == null) {
                return p;
            } else {
                prev.setCdr(p);
                return plist;
            }
        }
        return plist;
    }
}
