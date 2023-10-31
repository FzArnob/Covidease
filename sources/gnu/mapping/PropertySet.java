package gnu.mapping;

public abstract class PropertySet implements Named {
    public static final Symbol nameKey = Namespace.EmptyNamespace.getSymbol("name");
    private Object[] properties;

    public PropertySet() {
    }

    public String getName() {
        Object symbol = getProperty(nameKey, (Object) null);
        return symbol == null ? null : symbol instanceof Symbol ? ((Symbol) symbol).getName() : symbol.toString();
    }

    public Object getSymbol() {
        return getProperty(nameKey, (Object) null);
    }

    public final void setSymbol(Object name) {
        setProperty(nameKey, name);
    }

    public final void setName(String name) {
        setProperty(nameKey, name);
    }

    public Object getProperty(Object obj, Object obj2) {
        Object key = obj;
        Object defaultValue = obj2;
        if (this.properties != null) {
            int i = this.properties.length;
            do {
                i -= 2;
                if (i >= 0) {
                }
            } while (this.properties[i] != key);
            return this.properties[i + 1];
        }
        return defaultValue;
    }

    public synchronized void setProperty(Object obj, Object obj2) {
        Object key = obj;
        Object value = obj2;
        synchronized (this) {
            this.properties = setProperty(this.properties, key, value);
        }
    }

    public static Object[] setProperty(Object[] objArr, Object obj, Object obj2) {
        int avail;
        Object[] properties2 = objArr;
        Object key = obj;
        Object value = obj2;
        Object[] props = properties2;
        if (props == null) {
            Object[] objArr2 = new Object[10];
            props = objArr2;
            properties2 = objArr2;
            avail = 0;
        } else {
            avail = -1;
            int i = props.length;
            while (true) {
                i -= 2;
                if (i >= 0) {
                    Object k = props[i];
                    if (k == key) {
                        Object obj3 = props[i + 1];
                        props[i + 1] = value;
                        return properties2;
                    } else if (k == null) {
                        avail = i;
                    }
                } else if (avail < 0) {
                    avail = props.length;
                    properties2 = new Object[(2 * avail)];
                    System.arraycopy(props, 0, properties2, 0, avail);
                    props = properties2;
                }
            }
        }
        props[avail] = key;
        props[avail + 1] = value;
        return properties2;
    }

    public Object removeProperty(Object obj) {
        Object key = obj;
        Object[] props = this.properties;
        if (props == null) {
            return null;
        }
        int i = props.length;
        do {
            i -= 2;
            if (i < 0) {
                return null;
            }
        } while (props[i] != key);
        Object old = props[i + 1];
        props[i] = null;
        props[i + 1] = null;
        return old;
    }
}
