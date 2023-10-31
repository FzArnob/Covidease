package gnu.bytecode;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;

public class ArrayClassLoader extends ClassLoader {
    Hashtable cmap;
    URL context;
    Hashtable map;

    public ArrayClassLoader() {
        Hashtable hashtable;
        Hashtable hashtable2;
        new Hashtable(100);
        this.map = hashtable;
        new Hashtable(100);
        this.cmap = hashtable2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ArrayClassLoader(ClassLoader parent) {
        super(parent);
        Hashtable hashtable;
        Hashtable hashtable2;
        new Hashtable(100);
        this.map = hashtable;
        new Hashtable(100);
        this.cmap = hashtable2;
    }

    public URL getResourceContext() {
        return this.context;
    }

    public void setResourceContext(URL context2) {
        URL url = context2;
        this.context = url;
    }

    public ArrayClassLoader(byte[][] bArr) {
        Hashtable hashtable;
        Hashtable hashtable2;
        StringBuilder sb;
        byte[][] classBytes = bArr;
        new Hashtable(100);
        this.map = hashtable;
        new Hashtable(100);
        this.cmap = hashtable2;
        int i = classBytes.length;
        while (true) {
            i--;
            if (i >= 0) {
                new StringBuilder();
                addClass(sb.append("lambda").append(i).toString(), classBytes[i]);
            } else {
                return;
            }
        }
    }

    public ArrayClassLoader(String[] strArr, byte[][] bArr) {
        Hashtable hashtable;
        Hashtable hashtable2;
        String[] classNames = strArr;
        byte[][] classBytes = bArr;
        new Hashtable(100);
        this.map = hashtable;
        new Hashtable(100);
        this.cmap = hashtable2;
        int i = classBytes.length;
        while (true) {
            i--;
            if (i >= 0) {
                addClass(classNames[i], classBytes[i]);
            } else {
                return;
            }
        }
    }

    public void addClass(Class cls) {
        Class clas = cls;
        Object put = this.cmap.put(clas.getName(), clas);
    }

    public void addClass(String name, byte[] bytes) {
        Object put = this.map.put(name, bytes);
    }

    public void addClass(ClassType classType) {
        ClassType ctype = classType;
        Object put = this.map.put(ctype.getName(), ctype);
    }

    public InputStream getResourceAsStream(String str) {
        InputStream inputStream;
        String name = str;
        InputStream in = super.getResourceAsStream(name);
        if (in == null && name.endsWith(".class")) {
            Object r = this.map.get(name.substring(0, name.length() - 6).replace('/', '.'));
            if (r instanceof byte[]) {
                new ByteArrayInputStream((byte[]) r);
                return inputStream;
            }
        }
        return in;
    }

    /* access modifiers changed from: protected */
    public URL findResource(String str) {
        URL url;
        String name = str;
        if (this.context != null) {
            try {
                new URL(this.context, name);
                URL url2 = url;
                url2.openConnection().connect();
                return url2;
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        }
        return super.findResource(name);
    }

    public Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class clas = loadClass(name);
        if (resolve) {
            resolveClass(clas);
        }
        return clas;
    }

    /* JADX INFO: finally extract failed */
    public Class loadClass(String str) throws ClassNotFoundException {
        Class clas;
        String name = str;
        Object r = this.cmap.get(name);
        if (r != null) {
            return (Class) r;
        }
        Object r2 = this.map.get(name);
        if (r2 instanceof ClassType) {
            ClassType ctype = (ClassType) r2;
            if (ctype.isExisting()) {
                r2 = ctype.reflectClass;
            } else {
                r2 = ctype.writeToArray();
            }
        }
        if (r2 instanceof byte[]) {
            synchronized (this) {
                try {
                    Object r3 = this.map.get(name);
                    if (r3 instanceof byte[]) {
                        byte[] bytes = (byte[]) r3;
                        clas = defineClass(name, bytes, 0, bytes.length);
                        Object put = this.cmap.put(name, clas);
                    } else {
                        clas = (Class) r3;
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    throw th2;
                }
            }
        } else if (r2 == null) {
            clas = getParent().loadClass(name);
        } else {
            clas = (Class) r2;
        }
        return clas;
    }

    public static Package getContextPackage(String str) {
        String cname = str;
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if (loader instanceof ArrayClassLoader) {
                return ((ArrayClassLoader) loader).getPackage(cname);
            }
        } catch (SecurityException e) {
            SecurityException securityException = e;
        }
        return Package.getPackage(cname);
    }
}
