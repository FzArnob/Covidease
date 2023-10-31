package gnu.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipLoader extends ClassLoader {
    private Vector<Object> loadedClasses;
    int size = 0;
    ZipFile zar;
    private String zipname;

    public ZipLoader(String str) throws IOException {
        ZipFile zipFile;
        Vector<Object> vector;
        String name = str;
        this.zipname = name;
        new ZipFile(name);
        this.zar = zipFile;
        Enumeration e = this.zar.entries();
        while (e.hasMoreElements()) {
            if (!((ZipEntry) e.nextElement()).isDirectory()) {
                this.size++;
            }
        }
        new Vector<>(this.size);
        this.loadedClasses = vector;
    }

    public Class loadClass(String str, boolean z) throws ClassNotFoundException {
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        DataInputStream dataInputStream;
        Class clas;
        Throwable th2;
        StringBuilder sb3;
        Throwable th3;
        StringBuilder sb4;
        ZipFile zipFile;
        String name = str;
        boolean resolve = z;
        int index = this.loadedClasses.indexOf(name);
        if (index >= 0) {
            clas = (Class) this.loadedClasses.elementAt(index + 1);
        } else if (this.zar == null && this.loadedClasses.size() == 2 * this.size) {
            clas = Class.forName(name);
        } else {
            boolean reopened = false;
            new StringBuilder();
            String member_name = sb.append(name.replace('.', '/')).append(".class").toString();
            if (this.zar == null) {
                try {
                    new ZipFile(this.zipname);
                    this.zar = zipFile;
                    reopened = true;
                } catch (IOException e) {
                    IOException ex = e;
                    Throwable th4 = th3;
                    new StringBuilder();
                    new ClassNotFoundException(sb4.append("IOException while loading ").append(member_name).append(" from ziparchive \"").append(name).append("\": ").append(ex.toString()).toString());
                    throw th4;
                }
            }
            ZipEntry member = this.zar.getEntry(member_name);
            if (member == null) {
                if (reopened) {
                    try {
                        close();
                    } catch (IOException e2) {
                        IOException iOException = e2;
                        Throwable th5 = th2;
                        new StringBuilder();
                        new RuntimeException(sb3.append("failed to close \"").append(this.zipname).append("\"").toString());
                        throw th5;
                    }
                }
                clas = Class.forName(name);
            } else {
                try {
                    int member_size = (int) member.getSize();
                    byte[] bytes = new byte[member_size];
                    new DataInputStream(this.zar.getInputStream(member));
                    dataInputStream.readFully(bytes);
                    clas = defineClass(name, bytes, 0, member_size);
                    this.loadedClasses.addElement(name);
                    this.loadedClasses.addElement(clas);
                    if (2 * this.size == this.loadedClasses.size()) {
                        close();
                    }
                } catch (IOException e3) {
                    IOException ex2 = e3;
                    Throwable th6 = th;
                    new StringBuilder();
                    new ClassNotFoundException(sb2.append("IOException while loading ").append(member_name).append(" from ziparchive \"").append(name).append("\": ").append(ex2.toString()).toString());
                    throw th6;
                }
            }
        }
        if (resolve) {
            resolveClass(clas);
        }
        return clas;
    }

    public Class loadAllClasses() throws IOException {
        DataInputStream dataInputStream;
        Enumeration e = this.zar.entries();
        Class mainClass = null;
        while (e.hasMoreElements()) {
            ZipEntry member = (ZipEntry) e.nextElement();
            String name = member.getName().replace('/', '.');
            String name2 = name.substring(0, name.length() - "/class".length());
            int member_size = (int) member.getSize();
            byte[] bytes = new byte[member_size];
            new DataInputStream(this.zar.getInputStream(member));
            dataInputStream.readFully(bytes);
            Class clas = defineClass(name2, bytes, 0, member_size);
            if (mainClass == null) {
                mainClass = clas;
            }
            this.loadedClasses.addElement(name2);
            this.loadedClasses.addElement(clas);
        }
        close();
        return mainClass;
    }

    public void close() throws IOException {
        if (this.zar != null) {
            this.zar.close();
        }
        this.zar = null;
    }
}
