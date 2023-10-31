package gnu.mapping;

public class InheritingEnvironment extends SimpleEnvironment {
    int baseTimestamp;
    Environment[] inherited;
    Namespace[] namespaceMap;
    int numInherited;
    Object[] propertyMap;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InheritingEnvironment(String name, Environment environment) {
        super(name);
        Environment parent = environment;
        addParent(parent);
        if (parent instanceof SimpleEnvironment) {
            SimpleEnvironment simpleEnvironment = (SimpleEnvironment) parent;
            int i = simpleEnvironment.currentTimestamp + 1;
            int i2 = i;
            simpleEnvironment.currentTimestamp = i2;
            int timestamp = i;
            this.baseTimestamp = timestamp;
            this.currentTimestamp = timestamp;
        }
    }

    public final int getNumParents() {
        return this.numInherited;
    }

    public final Environment getParent(int index) {
        return this.inherited[index];
    }

    public void addParent(Environment environment) {
        Environment env = environment;
        if (this.numInherited == 0) {
            this.inherited = new Environment[4];
        } else if (this.numInherited <= this.inherited.length) {
            Environment[] newInherited = new Environment[(2 * this.numInherited)];
            System.arraycopy(this.inherited, 0, newInherited, 0, this.numInherited);
            this.inherited = newInherited;
        }
        this.inherited[this.numInherited] = env;
        this.numInherited++;
    }

    public NamedLocation lookupInherited(Symbol symbol, Object obj, int i) {
        Symbol name = symbol;
        Object property = obj;
        int hash = i;
        for (int i2 = 0; i2 < this.numInherited; i2++) {
            Symbol sym = name;
            Object prop = property;
            if (this.namespaceMap != null && this.namespaceMap.length > 2 * i2) {
                Namespace srcNamespace = this.namespaceMap[2 * i2];
                Namespace dstNamespace = this.namespaceMap[(2 * i2) + 1];
                if (!(srcNamespace == null && dstNamespace == null)) {
                    if (name.getNamespace() != dstNamespace) {
                        continue;
                    } else {
                        sym = Symbol.make(srcNamespace, name.getName());
                    }
                }
            }
            if (this.propertyMap != null && this.propertyMap.length > 2 * i2) {
                Object srcProperty = this.propertyMap[2 * i2];
                Object dstProperty = this.propertyMap[(2 * i2) + 1];
                if (!(srcProperty == null && dstProperty == null)) {
                    if (property != dstProperty) {
                        continue;
                    } else {
                        prop = srcProperty;
                    }
                }
            }
            NamedLocation loc = this.inherited[i2].lookup(sym, prop, hash);
            if (loc != null && loc.isBound() && (!(loc instanceof SharedLocation) || ((SharedLocation) loc).timestamp < this.baseTimestamp)) {
                return loc;
            }
        }
        return null;
    }

    public NamedLocation lookup(Symbol symbol, Object obj, int i) {
        Symbol name = symbol;
        Object property = obj;
        int hash = i;
        NamedLocation loc = super.lookup(name, property, hash);
        if (loc == null || !loc.isBound()) {
            return lookupInherited(name, property, hash);
        }
        return loc;
    }

    public synchronized NamedLocation getLocation(Symbol symbol, Object obj, int i, boolean z) {
        NamedLocation loc;
        NamedLocation addUnboundLocation;
        Symbol name = symbol;
        Object property = obj;
        int hash = i;
        boolean create = z;
        synchronized (this) {
            NamedLocation loc2 = lookupDirect(name, property, hash);
            if (loc2 == null || (!create && !loc2.isBound())) {
                if ((this.flags & 32) == 0 || !create) {
                    loc = lookupInherited(name, property, hash);
                } else {
                    loc = this.inherited[0].getLocation(name, property, hash, true);
                }
                if (loc == null) {
                    addUnboundLocation = create ? addUnboundLocation(name, property, hash) : null;
                } else if (create) {
                    NamedLocation xloc = addUnboundLocation(name, property, hash);
                    if ((this.flags & 1) == 0 && loc.isBound()) {
                        redefineError(name, property, xloc);
                    }
                    xloc.base = loc;
                    if (loc.value == IndirectableLocation.INDIRECT_FLUIDS) {
                        xloc.value = loc.value;
                    } else if ((this.flags & 16) != 0) {
                        xloc.value = IndirectableLocation.DIRECT_ON_SET;
                    } else {
                        xloc.value = null;
                    }
                    if (xloc instanceof SharedLocation) {
                        ((SharedLocation) xloc).timestamp = this.baseTimestamp;
                    }
                    addUnboundLocation = xloc;
                } else {
                    addUnboundLocation = loc;
                }
            } else {
                addUnboundLocation = loc2;
            }
        }
        return addUnboundLocation;
    }

    public LocationEnumeration enumerateAllLocations() {
        LocationEnumeration locationEnumeration;
        new LocationEnumeration(this.table, 1 << this.log2Size);
        LocationEnumeration it = locationEnumeration;
        it.env = this;
        if (this.inherited != null && this.inherited.length > 0) {
            it.inherited = this.inherited[0].enumerateAllLocations();
            it.index = 0;
        }
        return it;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001a, code lost:
        r1.prevLoc = null;
        r1.nextLoc = r1.inherited.nextLoc;
        r6 = r1;
        r7 = r6.index + 1;
        r3 = r7;
        r5 = r7;
        r6.index = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0039, code lost:
        if (r3 != r0.numInherited) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x003b, code lost:
        r1.inherited = null;
        r1.bindings = r0.table;
        r1.index = 1 << r0.log2Size;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasMoreElements(gnu.mapping.LocationEnumeration r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r3 = r1
            gnu.mapping.LocationEnumeration r3 = r3.inherited
            if (r3 == 0) goto L_0x004d
        L_0x0007:
            r3 = r1
            gnu.mapping.NamedLocation r3 = r3.nextLoc
            r2 = r3
        L_0x000b:
            r3 = r1
            gnu.mapping.LocationEnumeration r3 = r3.inherited
            r4 = r2
            r3.nextLoc = r4
            r3 = r1
            gnu.mapping.LocationEnumeration r3 = r3.inherited
            boolean r3 = r3.hasMoreElements()
            if (r3 != 0) goto L_0x0055
            r3 = r1
            r4 = 0
            r3.prevLoc = r4
            r3 = r1
            r4 = r1
            gnu.mapping.LocationEnumeration r4 = r4.inherited
            gnu.mapping.NamedLocation r4 = r4.nextLoc
            r3.nextLoc = r4
            r3 = r1
            r6 = r3
            r3 = r6
            r4 = r6
            int r4 = r4.index
            r5 = 1
            int r4 = r4 + 1
            r6 = r3
            r7 = r4
            r3 = r7
            r4 = r6
            r5 = r7
            r4.index = r5
            r4 = r0
            int r4 = r4.numInherited
            if (r3 != r4) goto L_0x0075
            r3 = r1
            r4 = 0
            r3.inherited = r4
            r3 = r1
            r4 = r0
            gnu.mapping.NamedLocation[] r4 = r4.table
            r3.bindings = r4
            r3 = r1
            r4 = 1
            r5 = r0
            int r5 = r5.log2Size
            int r4 = r4 << r5
            r3.index = r4
        L_0x004d:
            r3 = r0
            r4 = r1
            boolean r3 = super.hasMoreElements(r4)
            r0 = r3
        L_0x0054:
            return r0
        L_0x0055:
            r3 = r1
            gnu.mapping.LocationEnumeration r3 = r3.inherited
            gnu.mapping.NamedLocation r3 = r3.nextLoc
            r2 = r3
            r3 = r0
            r4 = r2
            gnu.mapping.Symbol r4 = r4.name
            r5 = r2
            java.lang.Object r5 = r5.property
            gnu.mapping.Location r3 = r3.lookup(r4, r5)
            r4 = r2
            if (r3 != r4) goto L_0x0070
            r3 = r1
            r4 = r2
            r3.nextLoc = r4
            r3 = 1
            r0 = r3
            goto L_0x0054
        L_0x0070:
            r3 = r2
            gnu.mapping.NamedLocation r3 = r3.next
            r2 = r3
            goto L_0x000b
        L_0x0075:
            r3 = r1
            r4 = r0
            gnu.mapping.Environment[] r4 = r4.inherited
            r5 = r1
            int r5 = r5.index
            r4 = r4[r5]
            gnu.mapping.LocationEnumeration r4 = r4.enumerateAllLocations()
            r3.inherited = r4
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.InheritingEnvironment.hasMoreElements(gnu.mapping.LocationEnumeration):boolean");
    }

    /* access modifiers changed from: protected */
    public void toStringBase(StringBuffer stringBuffer) {
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append(" baseTs:");
        StringBuffer append2 = sbuf.append(this.baseTimestamp);
        for (int i = 0; i < this.numInherited; i++) {
            StringBuffer append3 = sbuf.append(" base:");
            StringBuffer append4 = sbuf.append(this.inherited[i].toStringVerbose());
        }
    }
}
