package kawa.lang;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrappedException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Vector;

public class Record {
    public Record() {
    }

    public String getTypeName() {
        return getClass().getName();
    }

    public static boolean isRecord(Object obj) {
        return obj instanceof Record;
    }

    public int hashCode() {
        Field[] fields = getClass().getFields();
        int hash = 12345;
        for (int i = 0; i < fields.length; i++) {
            try {
                Object value = fields[i].get(this);
                if (value != null) {
                    hash ^= value.hashCode();
                }
            } catch (IllegalAccessException e) {
                IllegalAccessException illegalAccessException = e;
            }
        }
        return hash;
    }

    static Field getField(Class clas, String str) throws NoSuchFieldException {
        Throwable th;
        String fname = str;
        gnu.bytecode.Field fields = ((ClassType) Type.make(clas)).getFields();
        while (true) {
            gnu.bytecode.Field fld = fields;
            if (fld == null) {
                Throwable th2 = th;
                new NoSuchFieldException();
                throw th2;
            } else if ((fld.getModifiers() & 9) == 1 && fld.getSourceName().equals(fname)) {
                return fld.getReflectField();
            } else {
                fields = fld.getNext();
            }
        }
    }

    public Object get(String str, Object obj) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        String fname = str;
        Object obj2 = obj;
        Class clas = getClass();
        try {
            return getField(clas, fname).get(this);
        } catch (NoSuchFieldException e) {
            NoSuchFieldException noSuchFieldException = e;
            Throwable th3 = th2;
            new StringBuilder();
            new GenericError(sb2.append("no such field ").append(fname).append(" in ").append(clas.getName()).toString());
            throw th3;
        } catch (IllegalAccessException e2) {
            IllegalAccessException illegalAccessException = e2;
            Throwable th4 = th;
            new StringBuilder();
            new GenericError(sb.append("illegal access for field ").append(fname).toString());
            throw th4;
        }
    }

    public Object put(String fname, Object value) {
        return set1(this, fname, value);
    }

    public static Object set1(Object obj, String str, Object obj2) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Object record = obj;
        String fname = str;
        Object value = obj2;
        Class clas = record.getClass();
        try {
            Field fld = getField(clas, fname);
            Object old = fld.get(record);
            fld.set(record, value);
            return old;
        } catch (NoSuchFieldException e) {
            NoSuchFieldException noSuchFieldException = e;
            Throwable th3 = th2;
            new StringBuilder();
            new GenericError(sb2.append("no such field ").append(fname).append(" in ").append(clas.getName()).toString());
            throw th3;
        } catch (IllegalAccessException e2) {
            IllegalAccessException illegalAccessException = e2;
            Throwable th4 = th;
            new StringBuilder();
            new GenericError(sb.append("illegal access for field ").append(fname).toString());
            throw th4;
        }
    }

    public boolean equals(Object obj) {
        Throwable th;
        Object obj2 = obj;
        if (this == obj2) {
            return true;
        }
        Class thisClass = getClass();
        if (obj2 == null || obj2.getClass() != thisClass) {
            return false;
        }
        gnu.bytecode.Field fields = ((ClassType) Type.make(thisClass)).getFields();
        while (true) {
            gnu.bytecode.Field fld = fields;
            if (fld == null) {
                return true;
            }
            if ((fld.getModifiers() & 9) == 1) {
                try {
                    Field field = fld.getReflectField();
                    if (!field.get(this).equals(field.get(obj2))) {
                        return false;
                    }
                } catch (Exception e) {
                    Exception ex = e;
                    Throwable th2 = th;
                    new WrappedException((Throwable) ex);
                    throw th2;
                }
            }
            fields = fld.getNext();
        }
    }

    public String toString() {
        StringBuffer stringBuffer;
        Object obj;
        new StringBuffer(200);
        StringBuffer buf = stringBuffer;
        StringBuffer append = buf.append("#<");
        StringBuffer append2 = buf.append(getTypeName());
        gnu.bytecode.Field fields = ((ClassType) Type.make(getClass())).getFields();
        while (true) {
            gnu.bytecode.Field fld = fields;
            if (fld != null) {
                if ((fld.getModifiers() & 9) == 1) {
                    try {
                        obj = fld.getReflectField().get(this);
                    } catch (Exception e) {
                        Exception exc = e;
                        obj = "#<illegal-access>";
                    }
                    StringBuffer append3 = buf.append(' ');
                    StringBuffer append4 = buf.append(fld.getSourceName());
                    StringBuffer append5 = buf.append(": ");
                    StringBuffer append6 = buf.append(obj);
                }
                fields = fld.getNext();
            } else {
                StringBuffer append7 = buf.append(">");
                return buf.toString();
            }
        }
    }

    public void print(PrintWriter ps) {
        ps.print(toString());
    }

    public static ClassType makeRecordType(String str, LList lList) {
        ClassType classType;
        ArrayClassLoader loader;
        Throwable th;
        String name = str;
        LList fnames = lList;
        ClassType superClass = ClassType.make("kawa.lang.Record");
        String mangledName = Compilation.mangleNameIfNeeded(name);
        new ClassType(mangledName);
        ClassType clas = classType;
        clas.setSuper(superClass);
        clas.setModifiers(33);
        Method constructor = clas.addMethod("<init>", Type.typeArray0, (Type) Type.voidType, 1);
        Method superConstructor = superClass.addMethod("<init>", Type.typeArray0, (Type) Type.voidType, 1);
        CodeAttr code = constructor.startCode();
        code.emitPushThis();
        code.emitInvokeSpecial(superConstructor);
        code.emitReturn();
        if (!name.equals(mangledName)) {
            CodeAttr code2 = clas.addMethod("getTypeName", Type.typeArray0, (Type) Compilation.typeString, 1).startCode();
            code2.emitPushString(name);
            code2.emitReturn();
        }
        while (fnames != LList.Empty) {
            Pair pair = (Pair) fnames;
            String fname = pair.getCar().toString();
            clas.addField(Compilation.mangleNameIfNeeded(fname), Type.pointer_type, 1).setSourceName(fname.intern());
            fnames = (LList) pair.getCdr();
        }
        new ArrayClassLoader(new String[]{mangledName}, new byte[][]{clas.writeToArray()});
        try {
            Type.registerTypeForClass(loader.loadClass(mangledName), clas);
            return clas;
        } catch (ClassNotFoundException e) {
            ClassNotFoundException ex = e;
            Throwable th2 = th;
            new InternalError(ex.toString());
            throw th2;
        }
    }

    public static LList typeFieldNames(Class clas) {
        Vector vector;
        LList lList;
        LList list = LList.Empty;
        new Vector(100);
        Vector vec = vector;
        for (gnu.bytecode.Field field = ((ClassType) Type.make(clas)).getFields(); field != null; field = field.getNext()) {
            if ((field.getModifiers() & 9) == 1) {
                vec.addElement(SimpleSymbol.valueOf(field.getSourceName()));
            }
        }
        int i = vec.size();
        while (true) {
            i--;
            if (i < 0) {
                return list;
            }
            new Pair(vec.elementAt(i), list);
            list = lList;
        }
    }

    public static LList typeFieldNames(ClassType ctype) {
        return typeFieldNames(ctype.getReflectClass());
    }
}
