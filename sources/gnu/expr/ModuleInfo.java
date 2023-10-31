package gnu.expr;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.kawa.reflect.FieldLocation;
import gnu.kawa.util.AbstractWeakHashTable;
import gnu.mapping.Location;
import gnu.mapping.WrappedException;
import gnu.text.Path;
import java.io.IOException;
import java.net.URL;

public class ModuleInfo {
    static ClassToInfoMap mapClassToInfo;
    private String className;
    Compilation comp;
    ModuleInfo[] dependencies;
    ModuleExp exp;
    public long lastCheckedTime;
    public long lastModifiedTime;
    Class moduleClass;
    int numDependencies;
    Path sourceAbsPath;
    String sourceAbsPathname;
    public String sourcePath;
    String uri;

    public ModuleInfo() {
    }

    static {
        ClassToInfoMap classToInfoMap;
        new ClassToInfoMap();
        mapClassToInfo = classToInfoMap;
    }

    public String getNamespaceUri() {
        return this.uri;
    }

    public void setNamespaceUri(String uri2) {
        String str = uri2;
        this.uri = str;
    }

    public Compilation getCompilation() {
        return this.comp;
    }

    public void setCompilation(Compilation compilation) {
        Compilation comp2 = compilation;
        comp2.minfo = this;
        this.comp = comp2;
        ModuleExp mod = comp2.mainLambda;
        this.exp = mod;
        if (mod != null) {
            String fileName = mod.getFileName();
            this.sourcePath = fileName;
            this.sourceAbsPath = absPath(fileName);
        }
    }

    public void cleanupAfterCompilation() {
        if (this.comp != null) {
            this.comp.cleanupAfterCompilation();
        }
    }

    public static Path absPath(String path) {
        return Path.valueOf(path).getCanonical();
    }

    public Path getSourceAbsPath() {
        return this.sourceAbsPath;
    }

    public void setSourceAbsPath(Path path) {
        this.sourceAbsPath = path;
        this.sourceAbsPathname = null;
    }

    public String getSourceAbsPathname() {
        String str = this.sourceAbsPathname;
        if (str == null && this.sourceAbsPath != null) {
            str = this.sourceAbsPath.toString();
            this.sourceAbsPathname = str;
        }
        return str;
    }

    public synchronized void addDependency(ModuleInfo moduleInfo) {
        ModuleInfo dep = moduleInfo;
        synchronized (this) {
            if (this.dependencies == null) {
                this.dependencies = new ModuleInfo[8];
            } else if (this.numDependencies == this.dependencies.length) {
                ModuleInfo[] deps = new ModuleInfo[(2 * this.numDependencies)];
                System.arraycopy(this.dependencies, 0, deps, 0, this.numDependencies);
                this.dependencies = deps;
            }
            ModuleInfo[] moduleInfoArr = this.dependencies;
            int i = this.numDependencies;
            int i2 = i + 1;
            this.numDependencies = i2;
            moduleInfoArr[i] = dep;
        }
    }

    public synchronized ClassType getClassType() {
        ClassType make;
        synchronized (this) {
            if (this.moduleClass != null) {
                make = (ClassType) Type.make(this.moduleClass);
            } else if (this.comp == null || this.comp.mainClass == null) {
                make = ClassType.make(this.className);
            } else {
                make = this.comp.mainClass;
            }
        }
        return make;
    }

    public synchronized String getClassName() {
        String str;
        synchronized (this) {
            if (this.className == null) {
                if (this.moduleClass != null) {
                    this.className = this.moduleClass.getName();
                } else if (!(this.comp == null || this.comp.mainClass == null)) {
                    this.className = this.comp.mainClass.getName();
                }
            }
            str = this.className;
        }
        return str;
    }

    public void setClassName(String name) {
        String str = name;
        this.className = str;
    }

    public synchronized ModuleExp getModuleExp() {
        ModuleExp moduleExp;
        ModuleExp moduleExp2;
        synchronized (this) {
            ModuleExp m = this.exp;
            if (m == null) {
                if (this.comp != null) {
                    moduleExp = this.comp.mainLambda;
                } else {
                    ClassType ctype = ClassType.make(this.className);
                    new ModuleExp();
                    m = moduleExp2;
                    m.type = ctype;
                    m.setName(ctype.getName());
                    m.flags |= 524288;
                    m.info = this;
                    this.exp = m;
                }
            }
            moduleExp = m;
        }
        return moduleExp;
    }

    public synchronized ModuleExp setupModuleExp() {
        ClassType type;
        Class rclass;
        ModuleExp moduleExp;
        Expression expression;
        Throwable th;
        synchronized (this) {
            ModuleExp mod = getModuleExp();
            if ((mod.flags & 524288) == 0) {
                moduleExp = mod;
            } else {
                mod.setFlag(false, 524288);
                if (this.moduleClass != null) {
                    rclass = this.moduleClass;
                    type = (ClassType) Type.make(rclass);
                } else {
                    type = ClassType.make(this.className);
                    rclass = type.getReflectClass();
                }
                Object instance = null;
                Language language = Language.getDefaultLanguage();
                for (Field fld = type.getFields(); fld != null; fld = fld.getNext()) {
                    int flags = fld.getFlags();
                    if ((flags & 1) != 0) {
                        if ((flags & 8) == 0 && instance == null) {
                            try {
                                instance = getInstance();
                            } catch (Exception e) {
                                Exception ex = e;
                                Throwable th2 = th;
                                new WrappedException((Throwable) ex);
                                throw th2;
                            }
                        }
                        Object fvalue = rclass.getField(fld.getName()).get(instance);
                        Declaration fdecl = language.declFromField(mod, fvalue, fld);
                        if ((flags & 16) == 0 || ((fvalue instanceof Location) && !(fvalue instanceof FieldLocation))) {
                            fdecl.noteValue((Expression) null);
                        } else {
                            new QuoteExp(fvalue);
                            fdecl.noteValue(expression);
                        }
                    }
                }
                Declaration firstDecl = mod.firstDecl();
                while (true) {
                    Declaration fdecl2 = firstDecl;
                    if (fdecl2 == null) {
                        break;
                    }
                    makeDeclInModule2(mod, fdecl2);
                    firstDecl = fdecl2.nextDecl();
                }
                moduleExp = mod;
            }
        }
        return moduleExp;
    }

    public synchronized Class getModuleClass() throws ClassNotFoundException {
        Class cls;
        synchronized (this) {
            Class mclass = this.moduleClass;
            if (mclass != null) {
                cls = mclass;
            } else {
                Class mclass2 = ClassType.getContextClass(this.className);
                this.moduleClass = mclass2;
                cls = mclass2;
            }
        }
        return cls;
    }

    public Class getModuleClassRaw() {
        return this.moduleClass;
    }

    public void setModuleClass(Class cls) {
        Class clas = cls;
        this.moduleClass = clas;
        this.className = clas.getName();
        Object put = mapClassToInfo.put(clas, this);
    }

    public static ModuleInfo findFromInstance(Object instance) {
        return ModuleContext.getContext().findFromInstance(instance);
    }

    public static ModuleInfo find(ClassType classType) {
        ClassType type = classType;
        if (type.isExisting()) {
            try {
                return ModuleManager.findWithClass(type.getReflectClass());
            } catch (Exception e) {
                Exception exc = e;
            }
        }
        return ModuleManager.getInstance().findWithClassName(type.getName());
    }

    public static void register(Object instance) {
        ModuleContext.getContext().setInstance(instance);
    }

    public Object getInstance() {
        return ModuleContext.getContext().findInstance(this);
    }

    public Object getRunInstance() {
        Object inst = getInstance();
        if (inst instanceof Runnable) {
            ((Runnable) inst).run();
        }
        return inst;
    }

    static void makeDeclInModule2(ModuleExp moduleExp, Declaration declaration) {
        ReferenceExp referenceExp;
        ModuleExp mod = moduleExp;
        Declaration fdecl = declaration;
        Object fvalue = fdecl.getConstantValue();
        if (fvalue instanceof FieldLocation) {
            FieldLocation floc = (FieldLocation) fvalue;
            Declaration vdecl = floc.getDeclaration();
            new ReferenceExp(vdecl);
            ReferenceExp fref = referenceExp;
            fdecl.setAlias(true);
            fref.setDontDereference(true);
            fdecl.setValue(fref);
            if (vdecl.isProcedureDecl()) {
                fdecl.setProcedureDecl(true);
            }
            if (vdecl.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID)) {
                fdecl.setSyntax();
            }
            if (!fdecl.getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH)) {
                String vname = floc.getDeclaringClass().getName();
                Declaration firstDecl = mod.firstDecl();
                while (true) {
                    Declaration xdecl = firstDecl;
                    if (xdecl == null) {
                        return;
                    }
                    if (!vname.equals(xdecl.getType().getName()) || !xdecl.getFlag(1073741824)) {
                        firstDecl = xdecl.nextDecl();
                    } else {
                        fref.setContextDecl(xdecl);
                        return;
                    }
                }
            }
        }
    }

    public int getState() {
        return this.comp == null ? 14 : this.comp.getState();
    }

    public void loadByStages(int i) {
        int wantedState = i;
        if (getState() + 1 < wantedState) {
            loadByStages(wantedState - 2);
            int state = getState();
            if (state < wantedState) {
                this.comp.setState(state + 1);
                int ndeps = this.numDependencies;
                for (int idep = 0; idep < ndeps; idep++) {
                    this.dependencies[idep].loadByStages(wantedState);
                }
                int state2 = getState();
                if (state2 < wantedState) {
                    this.comp.setState(state2 & -2);
                    this.comp.process(wantedState);
                }
            }
        }
    }

    public boolean loadEager(int i) {
        int wantedState = i;
        if (this.comp == null && this.className != null) {
            return false;
        }
        int state = getState();
        if (state >= wantedState) {
            return true;
        }
        if ((state & 1) != 0) {
            return false;
        }
        this.comp.setState(state + 1);
        int ndeps = this.numDependencies;
        for (int idep = 0; idep < ndeps; idep++) {
            if (!this.dependencies[idep].loadEager(wantedState)) {
                if (getState() == state + 1) {
                    this.comp.setState(state);
                }
                return false;
            }
        }
        if (getState() == state + 1) {
            this.comp.setState(state);
        }
        this.comp.process(wantedState);
        return getState() == wantedState;
    }

    public void clearClass() {
        this.moduleClass = null;
        this.numDependencies = 0;
        this.dependencies = null;
    }

    public boolean checkCurrent(ModuleManager moduleManager, long j) {
        StringBuilder sb;
        ModuleManager manager = moduleManager;
        long now = j;
        if (this.sourceAbsPath == null) {
            return true;
        }
        if (this.lastCheckedTime + manager.lastModifiedCacheTime >= now) {
            return this.moduleClass != null;
        }
        long lastModifiedTime2 = this.sourceAbsPath.getLastModified();
        long oldModifiedTime = this.lastModifiedTime;
        this.lastModifiedTime = lastModifiedTime2;
        this.lastCheckedTime = now;
        if (this.className == null) {
            return false;
        }
        if (this.moduleClass == null) {
            try {
                this.moduleClass = ClassType.getContextClass(this.className);
            } catch (ClassNotFoundException e) {
                ClassNotFoundException classNotFoundException = e;
                return false;
            }
        }
        if (oldModifiedTime == 0 && this.moduleClass != null) {
            String classFilename = this.className;
            int dot = classFilename.lastIndexOf(46);
            if (dot >= 0) {
                classFilename = classFilename.substring(dot + 1);
            }
            new StringBuilder();
            URL resource = this.moduleClass.getResource(sb.append(classFilename).append(".class").toString());
            if (resource != null) {
                try {
                    oldModifiedTime = resource.openConnection().getLastModified();
                } catch (IOException e2) {
                    IOException iOException = e2;
                    resource = null;
                }
            }
            if (resource == null) {
                return true;
            }
        }
        if (lastModifiedTime2 > oldModifiedTime) {
            this.moduleClass = null;
            return false;
        }
        int i = this.numDependencies;
        while (true) {
            i--;
            if (i < 0) {
                return true;
            }
            ModuleInfo dep = this.dependencies[i];
            if (dep.comp == null && !dep.checkCurrent(manager, now)) {
                this.moduleClass = null;
                return false;
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append("ModuleInfo[");
        if (this.moduleClass != null) {
            StringBuffer append2 = sbuf.append("class: ");
            StringBuffer append3 = sbuf.append(this.moduleClass);
        } else if (this.className != null) {
            StringBuffer append4 = sbuf.append("class-name: ");
            StringBuffer append5 = sbuf.append(this.className);
        }
        StringBuffer append6 = sbuf.append(']');
        return sbuf.toString();
    }

    static class ClassToInfoMap extends AbstractWeakHashTable<Class, ModuleInfo> {
        ClassToInfoMap() {
        }

        /* access modifiers changed from: protected */
        public Class getKeyFromValue(ModuleInfo minfo) {
            return minfo.moduleClass;
        }

        /* access modifiers changed from: protected */
        public boolean matches(Class oldValue, Class newValue) {
            return oldValue == newValue;
        }
    }
}
