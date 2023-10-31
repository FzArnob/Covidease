package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.mapping.WrappedException;
import gnu.text.Path;
import gnu.text.URLPath;
import java.io.File;
import java.net.URL;

public class ModuleManager {
    public static final long LAST_MODIFIED_CACHE_TIME = 1000;
    static ModuleManager instance;
    private String compilationDirectory = "";
    public long lastModifiedCacheTime = 1000;
    ModuleInfo[] modules;
    int numModules;
    ModuleSet packageInfoChain;

    public ModuleManager() {
    }

    public void setCompilationDirectory(String str) {
        char sep;
        StringBuilder sb;
        String path = str;
        if (path == null) {
            path = "";
        }
        int plen = path.length();
        if (plen > 0 && path.charAt(plen - 1) != (sep = File.separatorChar)) {
            new StringBuilder();
            path = sb.append(path).append(sep).toString();
        }
        this.compilationDirectory = path;
    }

    public String getCompilationDirectory() {
        return this.compilationDirectory;
    }

    static {
        ModuleManager moduleManager;
        new ModuleManager();
        instance = moduleManager;
    }

    public static ModuleManager getInstance() {
        return instance;
    }

    public synchronized ModuleInfo getModule(int i) {
        ModuleInfo moduleInfo;
        int index = i;
        synchronized (this) {
            moduleInfo = index >= this.numModules ? null : this.modules[index];
        }
        return moduleInfo;
    }

    public synchronized ModuleInfo find(Compilation compilation) {
        ModuleInfo moduleInfo;
        Compilation comp = compilation;
        synchronized (this) {
            ModuleExp mexp = comp.getModule();
            ClassType ctype = mexp.classFor(comp);
            String fileName = mexp.getFileName();
            ModuleInfo info = findWithSourcePath(ModuleInfo.absPath(fileName), fileName);
            info.setClassName(ctype.getName());
            info.exp = mexp;
            comp.minfo = info;
            info.comp = comp;
            moduleInfo = info;
        }
        return moduleInfo;
    }

    private synchronized void add(ModuleInfo moduleInfo) {
        ModuleInfo info = moduleInfo;
        synchronized (this) {
            if (this.modules == null) {
                this.modules = new ModuleInfo[10];
            } else if (this.numModules == this.modules.length) {
                ModuleInfo[] tmp = new ModuleInfo[(2 * this.numModules)];
                System.arraycopy(this.modules, 0, tmp, 0, this.numModules);
                this.modules = tmp;
            }
            ModuleInfo[] moduleInfoArr = this.modules;
            int i = this.numModules;
            int i2 = i + 1;
            this.numModules = i2;
            moduleInfoArr[i] = info;
        }
    }

    public synchronized ModuleInfo searchWithClassName(String str) {
        ModuleInfo moduleInfo;
        String className = str;
        synchronized (this) {
            int i = this.numModules;
            while (true) {
                i--;
                if (i < 0) {
                    moduleInfo = null;
                    break;
                }
                ModuleInfo info = this.modules[i];
                if (className.equals(info.getClassName())) {
                    moduleInfo = info;
                    break;
                }
            }
        }
        return moduleInfo;
    }

    public static synchronized ModuleInfo findWithClass(Class cls) {
        ModuleInfo moduleInfo;
        ModuleInfo moduleInfo2;
        Class clas = cls;
        synchronized (ModuleManager.class) {
            ModuleInfo info = (ModuleInfo) ModuleInfo.mapClassToInfo.get(clas);
            if (info == null) {
                new ModuleInfo();
                info = moduleInfo2;
                info.setModuleClass(clas);
            }
            moduleInfo = info;
        }
        return moduleInfo;
    }

    public ModuleInfo findWithClassName(String str) {
        String className = str;
        ModuleInfo info = searchWithClassName(className);
        if (info != null) {
            return info;
        }
        try {
            return findWithClass(ClassType.getContextClass(className));
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    private synchronized ModuleInfo searchWithAbsSourcePath(String str) {
        ModuleInfo moduleInfo;
        String sourcePath = str;
        synchronized (this) {
            int i = this.numModules;
            while (true) {
                i--;
                if (i < 0) {
                    moduleInfo = null;
                    break;
                }
                ModuleInfo info = this.modules[i];
                if (sourcePath.equals(info.getSourceAbsPathname())) {
                    moduleInfo = info;
                    break;
                }
            }
        }
        return moduleInfo;
    }

    public synchronized ModuleInfo findWithSourcePath(Path path, String str) {
        ModuleInfo moduleInfo;
        ModuleInfo moduleInfo2;
        Path sourceAbsPath = path;
        String sourcePath = str;
        synchronized (this) {
            String sourceAbsPathname = sourceAbsPath.toString();
            ModuleInfo info = searchWithAbsSourcePath(sourceAbsPathname);
            if (info == null) {
                new ModuleInfo();
                info = moduleInfo2;
                info.sourcePath = sourcePath;
                info.sourceAbsPath = sourceAbsPath;
                info.sourceAbsPathname = sourceAbsPathname;
                add(info);
            }
            moduleInfo = info;
        }
        return moduleInfo;
    }

    public synchronized ModuleInfo findWithSourcePath(String str) {
        ModuleInfo findWithSourcePath;
        String sourcePath = str;
        synchronized (this) {
            findWithSourcePath = findWithSourcePath(ModuleInfo.absPath(sourcePath), sourcePath);
        }
        return findWithSourcePath;
    }

    public synchronized ModuleInfo findWithURL(URL url) {
        ModuleInfo findWithSourcePath;
        URL url2 = url;
        synchronized (this) {
            findWithSourcePath = findWithSourcePath(URLPath.valueOf(url2), url2.toExternalForm());
        }
        return findWithSourcePath;
    }

    public synchronized void register(String str, String str2, String str3) {
        ModuleInfo moduleInfo;
        StringBuilder sb;
        String moduleClass = str;
        String moduleSource = str2;
        String moduleUri = str3;
        synchronized (this) {
            if (searchWithClassName(moduleClass) == null) {
                Path sourcePath = Path.valueOf(moduleSource);
                String sourceAbsPathname = sourcePath.getCanonical().toString();
                if (searchWithAbsSourcePath(sourceAbsPathname) == null) {
                    new ModuleInfo();
                    ModuleInfo info = moduleInfo;
                    if (sourcePath.isAbsolute()) {
                        info.sourceAbsPath = sourcePath;
                        info.sourceAbsPathname = sourceAbsPathname;
                    } else {
                        try {
                            Class setClass = this.packageInfoChain.getClass();
                            new StringBuilder();
                            Path sourceAbsPath = URLPath.valueOf(setClass.getClassLoader().getResource(sb.append(setClass.getName().replace('.', '/')).append(".class").toString())).resolve(moduleSource);
                            info.sourceAbsPath = sourceAbsPath;
                            info.sourceAbsPathname = sourceAbsPath.toString();
                        } catch (Throwable th) {
                            Throwable th2 = th;
                        }
                    }
                    info.setClassName(moduleClass);
                    info.sourcePath = moduleSource;
                    info.uri = moduleUri;
                    add(info);
                }
            }
        }
    }

    public synchronized void loadPackageInfo(String str) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        StringBuilder sb;
        String packageName = str;
        synchronized (this) {
            new StringBuilder();
            String moduleSetClassName = sb.append(packageName).append(".").append(ModuleSet.MODULES_MAP).toString();
            for (ModuleSet set = this.packageInfoChain; set != null; set = set.next) {
                if (set.getClass().getName().equals(moduleSetClassName)) {
                }
            }
            ModuleSet instance2 = (ModuleSet) Class.forName(moduleSetClassName).newInstance();
            instance2.next = this.packageInfoChain;
            this.packageInfoChain = instance2;
            instance2.register(this);
        }
    }

    public synchronized void clear() {
        synchronized (this) {
            ModuleSet set = this.packageInfoChain;
            while (set != null) {
                ModuleSet next = set.next;
                set.next = null;
                set = next;
            }
            this.packageInfoChain = null;
            this.modules = null;
            this.numModules = 0;
        }
    }
}
