package gnu.expr;

import gnu.kawa.util.AbstractWeakHashTable;
import gnu.mapping.WrappedException;

public class ModuleContext {
    public static int IN_HTTP_SERVER = 1;
    public static int IN_SERVLET = 2;
    static ModuleContext global;
    int flags;
    ModuleManager manager;
    private ClassToInstanceMap table;

    static {
        ModuleContext moduleContext;
        new ModuleContext(ModuleManager.instance);
        global = moduleContext;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int flags2) {
        int i = flags2;
        this.flags = i;
    }

    public void addFlags(int flags2) {
        this.flags |= flags2;
    }

    public ModuleContext(ModuleManager manager2) {
        ClassToInstanceMap classToInstanceMap;
        new ClassToInstanceMap();
        this.table = classToInstanceMap;
        this.manager = manager2;
    }

    public static ModuleContext getContext() {
        return global;
    }

    public ModuleManager getManager() {
        return this.manager;
    }

    public synchronized Object findInstance(ModuleInfo moduleInfo) {
        Throwable th;
        StringBuilder sb;
        Object findInstance;
        ModuleInfo info = moduleInfo;
        synchronized (this) {
            try {
                findInstance = findInstance(info.getModuleClass());
            } catch (ClassNotFoundException e) {
                ClassNotFoundException ex = e;
                String cname = info.getClassName();
                Throwable th2 = th;
                new StringBuilder();
                new WrappedException(sb.append("cannot find module ").append(cname).toString(), ex);
                throw th2;
            }
        }
        return findInstance;
    }

    public synchronized Object searchInstance(Class cls) {
        Object obj;
        Class clas = cls;
        synchronized (this) {
            obj = this.table.get(clas);
        }
        return obj;
    }

    public synchronized Object findInstance(Class cls) {
        Object obj;
        Throwable th;
        StringBuilder sb;
        Class clas = cls;
        synchronized (this) {
            Object inst = this.table.get(clas);
            if (inst == null) {
                try {
                    inst = clas.getDeclaredField("$instance").get((Object) null);
                } catch (NoSuchFieldException e) {
                    NoSuchFieldException noSuchFieldException = e;
                    inst = clas.newInstance();
                } catch (Throwable th2) {
                    Throwable ex = th2;
                    Throwable th3 = th;
                    new StringBuilder();
                    new WrappedException(sb.append("exception while initializing module ").append(clas.getName()).toString(), ex);
                    throw th3;
                }
                setInstance(inst);
            }
            obj = inst;
        }
        return obj;
    }

    public synchronized void setInstance(Object obj) {
        Object instance = obj;
        synchronized (this) {
            Object put = this.table.put(instance.getClass(), instance);
        }
    }

    public ModuleInfo findFromInstance(Object obj) {
        Object instance = obj;
        Class instanceClass = instance.getClass();
        synchronized (this) {
            try {
                ModuleManager moduleManager = this.manager;
                ModuleInfo info = ModuleManager.findWithClass(instanceClass);
                setInstance(instance);
                ModuleInfo moduleInfo = info;
                return moduleInfo;
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public synchronized void clear() {
        synchronized (this) {
            this.table.clear();
        }
    }

    static class ClassToInstanceMap extends AbstractWeakHashTable<Class, Object> {
        ClassToInstanceMap() {
        }

        /* access modifiers changed from: protected */
        public Class getKeyFromValue(Object instance) {
            return instance.getClass();
        }

        /* access modifiers changed from: protected */
        public boolean matches(Class oldValue, Class newValue) {
            return oldValue == newValue;
        }
    }
}
