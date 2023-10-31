package gnu.mapping;

public class ThreadLocation extends NamedLocation implements Named {
    public static final String ANONYMOUS;
    static int counter;
    static SimpleEnvironment env;
    SharedLocation global;
    private int hash;
    private ThreadLocal<NamedLocation> thLocal;

    private static synchronized int nextCounter() {
        int i;
        synchronized (ThreadLocation.class) {
            int i2 = counter + 1;
            i = i2;
            counter = i2;
        }
        return i;
    }

    static {
        String str;
        new String("(dynamic)");
        ANONYMOUS = str;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ThreadLocation() {
        /*
            r5 = this;
            r0 = r5
            r1 = r0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r4 = r2
            r2 = r4
            r3 = r4
            r3.<init>()
            java.lang.String r3 = "param#"
            java.lang.StringBuilder r2 = r2.append(r3)
            int r3 = nextCounter()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>((java.lang.String) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.ThreadLocation.<init>():void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ThreadLocation(String name) {
        super(Symbol.makeUninterned(name), ANONYMOUS);
        ThreadLocal<NamedLocation> threadLocal;
        SharedLocation sharedLocation;
        new InheritingLocation(this);
        this.thLocal = threadLocal;
        new SharedLocation(this.name, (Object) null, 0);
        this.global = sharedLocation;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private ThreadLocation(gnu.mapping.Symbol r11) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r3 = r0
            r4 = r1
            java.lang.String r5 = ANONYMOUS
            r3.<init>(r4, r5)
            r3 = r0
            gnu.mapping.ThreadLocation$InheritingLocation r4 = new gnu.mapping.ThreadLocation$InheritingLocation
            r9 = r4
            r4 = r9
            r5 = r9
            r6 = r0
            r5.<init>(r6)
            r3.thLocal = r4
            r3 = r1
            if (r3 != 0) goto L_0x002d
            r3 = 0
        L_0x0019:
            r2 = r3
            r3 = r0
            gnu.mapping.SharedLocation r4 = new gnu.mapping.SharedLocation
            r9 = r4
            r4 = r9
            r5 = r9
            r6 = r2
            gnu.mapping.Symbol r6 = gnu.mapping.Symbol.makeUninterned(r6)
            r7 = 0
            r8 = 0
            r5.<init>(r6, r7, r8)
            r3.global = r4
            return
        L_0x002d:
            r3 = r1
            java.lang.String r3 = r3.toString()
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.ThreadLocation.<init>(gnu.mapping.Symbol):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ThreadLocation(gnu.mapping.Symbol r8, java.lang.Object r9, gnu.mapping.SharedLocation r10) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r0
            r5 = r1
            r6 = r2
            r4.<init>(r5, r6)
            r4 = r0
            r5 = r1
            int r5 = r5.hashCode()
            r6 = r2
            int r6 = java.lang.System.identityHashCode(r6)
            r5 = r5 ^ r6
            r4.hash = r5
            r4 = r0
            r5 = r3
            r4.global = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.ThreadLocation.<init>(gnu.mapping.Symbol, java.lang.Object, gnu.mapping.SharedLocation):void");
    }

    public static ThreadLocation makeAnonymous(String name) {
        ThreadLocation threadLocation;
        new ThreadLocation(name);
        return threadLocation;
    }

    public static ThreadLocation makeAnonymous(Symbol name) {
        ThreadLocation threadLocation;
        new ThreadLocation(name);
        return threadLocation;
    }

    public void setGlobal(Object obj) {
        SharedLocation sharedLocation;
        Object value = obj;
        synchronized (this) {
            try {
                if (this.global == null) {
                    new SharedLocation(this.name, (Object) null, 0);
                    this.global = sharedLocation;
                }
                this.global.set(value);
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public NamedLocation getLocation() {
        NamedLocation namedLocation;
        if (this.property != ANONYMOUS) {
            return Environment.getCurrent().getLocation(this.name, this.property, this.hash, true);
        }
        NamedLocation entry = this.thLocal.get();
        if (entry == null) {
            new SharedLocation(this.name, this.property, 0);
            entry = namedLocation;
            if (this.global != null) {
                entry.setBase(this.global);
            }
            this.thLocal.set(entry);
        }
        return entry;
    }

    public Object get(Object defaultValue) {
        return getLocation().get(defaultValue);
    }

    public void set(Object value) {
        getLocation().set(value);
    }

    public Object setWithSave(Object newValue) {
        return getLocation().setWithSave(newValue);
    }

    public void setRestore(Object oldValue) {
        getLocation().setRestore(oldValue);
    }

    public String getName() {
        return this.name == null ? null : this.name.toString();
    }

    public Object getSymbol() {
        if (this.name != null && this.property == ANONYMOUS && this.global.getKeySymbol() == this.name) {
            return this.name.toString();
        }
        return this.name;
    }

    public void setName(String str) {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new RuntimeException("setName not allowed");
        throw th2;
    }

    public static synchronized ThreadLocation getInstance(Symbol symbol, Object obj) {
        ThreadLocation threadLocation;
        ThreadLocation threadLocation2;
        SimpleEnvironment simpleEnvironment;
        Symbol name = symbol;
        Object property = obj;
        synchronized (ThreadLocation.class) {
            if (env == null) {
                new SimpleEnvironment("[thread-locations]");
                env = simpleEnvironment;
            }
            IndirectableLocation loc = (IndirectableLocation) env.getLocation(name, property);
            if (loc.base != null) {
                threadLocation2 = (ThreadLocation) loc.base;
            } else {
                new ThreadLocation(name, property, (SharedLocation) null);
                ThreadLocation tloc = threadLocation;
                loc.base = tloc;
                threadLocation2 = tloc;
            }
        }
        return threadLocation2;
    }

    public class InheritingLocation extends InheritableThreadLocal<NamedLocation> {
        final /* synthetic */ ThreadLocation this$0;

        public InheritingLocation(ThreadLocation threadLocation) {
            this.this$0 = threadLocation;
        }

        /* access modifiers changed from: protected */
        public SharedLocation childValue(NamedLocation namedLocation) {
            SharedLocation sharedLocation;
            NamedLocation namedLocation2;
            Throwable th;
            NamedLocation parentValue = namedLocation;
            if (this.this$0.property != ThreadLocation.ANONYMOUS) {
                Throwable th2 = th;
                new Error();
                throw th2;
            }
            if (parentValue == null) {
                parentValue = (SharedLocation) this.this$0.getLocation();
            }
            NamedLocation nloc = parentValue;
            if (nloc.base == null) {
                new SharedLocation(this.this$0.name, this.this$0.property, 0);
                NamedLocation sloc = namedLocation2;
                sloc.value = nloc.value;
                nloc.base = sloc;
                nloc.value = null;
                nloc = sloc;
            }
            new SharedLocation(this.this$0.name, this.this$0.property, 0);
            SharedLocation sloc2 = sharedLocation;
            sloc2.value = null;
            sloc2.base = nloc;
            return sloc2;
        }
    }
}
