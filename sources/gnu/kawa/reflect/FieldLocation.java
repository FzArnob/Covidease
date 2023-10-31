package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ModuleInfo;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.WrappedException;
import kawa.lang.Syntax;

public class FieldLocation extends ClassMemberLocation {
    static final int CONSTANT = 4;
    static final int INDIRECT_LOCATION = 2;
    public static final int KIND_FLAGS_SET = 64;
    public static final int PROCEDURE = 16;
    static final int SETUP_DONE = 1;
    public static final int SYNTAX = 32;
    static final int VALUE_SET = 8;
    Declaration decl;
    private int flags;
    Object value;

    public boolean isIndirectLocation() {
        return (this.flags & 2) != 0;
    }

    public void setProcedure() {
        this.flags |= 84;
    }

    public void setSyntax() {
        this.flags |= 100;
    }

    /* access modifiers changed from: package-private */
    public void setKindFlags() {
        Field fld = getDeclaringClass().getDeclaredField(getMemberName());
        int fflags = fld.getModifiers();
        Type ftype = fld.getType();
        if (ftype.isSubtype(Compilation.typeLocation)) {
            this.flags |= 2;
        }
        if ((fflags & 16) != 0) {
            if ((this.flags & 2) == 0) {
                this.flags |= 4;
                if (ftype.isSubtype(Compilation.typeProcedure)) {
                    this.flags |= 16;
                }
                if ((ftype instanceof ClassType) && ((ClassType) ftype).isSubclass("kawa.lang.Syntax")) {
                    this.flags |= 32;
                }
            } else {
                Location loc = (Location) getFieldValue();
                if (loc instanceof FieldLocation) {
                    FieldLocation floc = (FieldLocation) loc;
                    if ((floc.flags & 64) == 0) {
                        floc.setKindFlags();
                    }
                    this.flags |= floc.flags & 52;
                    if ((floc.flags & 4) == 0) {
                        this.value = floc;
                        this.flags |= 8;
                    } else if ((floc.flags & 8) != 0) {
                        this.value = floc.value;
                        this.flags |= 8;
                    }
                } else if (loc.isConstant()) {
                    Object val = loc.get((Object) null);
                    if (val instanceof Procedure) {
                        this.flags |= 16;
                    }
                    if (val instanceof Syntax) {
                        this.flags |= 32;
                    }
                    this.flags |= 12;
                    this.value = val;
                }
            }
        }
        this.flags |= 64;
    }

    public boolean isProcedureOrSyntax() {
        if ((this.flags & 64) == 0) {
            setKindFlags();
        }
        return (this.flags & 48) != 0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FieldLocation(Object instance, String cname, String fname) {
        super(instance, ClassType.make(cname), fname);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FieldLocation(Object instance, ClassType type, String mname) {
        super(instance, type, mname);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FieldLocation(java.lang.Object r7, java.lang.reflect.Field r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>(r4, r5)
            r3 = r0
            r4 = r2
            java.lang.Class r4 = r4.getDeclaringClass()
            gnu.bytecode.Type r4 = gnu.bytecode.Type.make(r4)
            gnu.bytecode.ClassType r4 = (gnu.bytecode.ClassType) r4
            r3.type = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.FieldLocation.<init>(java.lang.Object, java.lang.reflect.Field):void");
    }

    public void setDeclaration(Declaration decl2) {
        Declaration declaration = decl2;
        this.decl = declaration;
    }

    public Field getField() {
        return this.type.getDeclaredField(this.mname);
    }

    public Type getFType() {
        return this.type.getDeclaredField(this.mname).getType();
    }

    public synchronized Declaration getDeclaration() {
        Declaration declaration;
        Throwable th;
        StringBuilder sb;
        synchronized (this) {
            if ((this.flags & 64) == 0) {
                setKindFlags();
            }
            Declaration d = this.decl;
            if (d == null) {
                String fname = getMemberName();
                ClassType t = getDeclaringClass();
                if (t.getDeclaredField(fname) == null) {
                    declaration = null;
                } else {
                    d = ModuleInfo.find(t).getModuleExp().firstDecl();
                    while (d != null && (d.field == null || !d.field.getName().equals(fname))) {
                        d = d.nextDecl();
                    }
                    if (d == null) {
                        Throwable th2 = th;
                        new StringBuilder();
                        new RuntimeException(sb.append("no field found for ").append(this).toString());
                        throw th2;
                    }
                    this.decl = d;
                }
            }
            declaration = d;
        }
        return declaration;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void setup() {
        synchronized (this) {
            try {
                if ((this.flags & 1) != 0) {
                    return;
                }
                super.setup();
                if ((this.flags & 64) == 0) {
                    setKindFlags();
                }
                this.flags |= 1;
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public Object get(Object obj) {
        Object v;
        Object defaultValue = obj;
        try {
            setup();
            if ((this.flags & 8) != 0) {
                v = this.value;
                if ((this.flags & 4) != 0) {
                    return v;
                }
            } else {
                v = getFieldValue();
                if ((this.type.getDeclaredField(this.mname).getModifiers() & 16) != 0) {
                    this.flags |= 8;
                    if ((this.flags & 2) == 0) {
                        this.flags |= 4;
                    }
                    this.value = v;
                }
            }
            if ((this.flags & 2) != 0) {
                String unb = Location.UNBOUND;
                Location loc = (Location) v;
                v = loc.get(unb);
                if (v == unb) {
                    return defaultValue;
                }
                if (loc.isConstant()) {
                    this.flags |= 4;
                    this.value = v;
                }
            }
            return v;
        } catch (Throwable th) {
            Throwable th2 = th;
            return defaultValue;
        }
    }

    private Object getFieldValue() {
        super.setup();
        try {
            return this.rfield.get(this.instance);
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public void set(Object obj) {
        Object v;
        Object newValue = obj;
        setup();
        if ((this.flags & 2) == 0) {
            try {
                this.rfield.set(this.instance, newValue);
            } catch (Throwable th) {
                throw WrappedException.wrapIfNeeded(th);
            }
        } else {
            if ((this.flags & 8) != 0) {
                v = this.value;
            } else {
                this.flags |= 8;
                v = getFieldValue();
                this.value = v;
            }
            ((Location) v).set(newValue);
        }
    }

    public Object setWithSave(Object obj) {
        Object v;
        Object newValue = obj;
        if ((this.flags & 64) == 0) {
            setKindFlags();
        }
        if ((this.flags & 2) == 0) {
            return super.setWithSave(newValue);
        }
        if ((this.flags & 8) != 0) {
            v = this.value;
        } else {
            this.flags |= 8;
            v = getFieldValue();
            this.value = v;
        }
        return ((Location) v).setWithSave(newValue);
    }

    public void setRestore(Object obj) {
        Object oldValue = obj;
        if ((this.flags & 2) == 0) {
            super.setRestore(oldValue);
        } else {
            ((Location) this.value).setRestore(oldValue);
        }
    }

    public boolean isConstant() {
        Object v;
        if ((this.flags & 64) == 0) {
            setKindFlags();
        }
        if ((this.flags & 4) != 0) {
            return true;
        }
        if (!isIndirectLocation()) {
            return false;
        }
        if ((this.flags & 8) != 0) {
            v = this.value;
        } else {
            try {
                setup();
                v = getFieldValue();
                this.flags |= 8;
                this.value = v;
            } catch (Throwable th) {
                Throwable th2 = th;
                return false;
            }
        }
        return ((Location) v).isConstant();
    }

    public boolean isBound() {
        Object v;
        if ((this.flags & 64) == 0) {
            setKindFlags();
        }
        if ((this.flags & 4) != 0 || (this.flags & 2) == 0) {
            return true;
        }
        if ((this.flags & 8) != 0) {
            v = this.value;
        } else {
            try {
                setup();
                v = getFieldValue();
                this.flags |= 8;
                this.value = v;
            } catch (Throwable th) {
                Throwable th2 = th;
                return false;
            }
        }
        return ((Location) v).isBound();
    }

    public static FieldLocation make(Object instance, Declaration declaration) {
        FieldLocation fieldLocation;
        Declaration decl2 = declaration;
        Field fld = decl2.field;
        new FieldLocation(instance, fld.getDeclaringClass(), fld.getName());
        FieldLocation loc = fieldLocation;
        loc.setDeclaration(decl2);
        return loc;
    }

    public static FieldLocation make(Object instance, String cname, String fldName) {
        FieldLocation fieldLocation;
        new FieldLocation(instance, ClassType.make(cname), fldName);
        return fieldLocation;
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append("FieldLocation[");
        if (this.instance != null) {
            StringBuffer append2 = sbuf.append(this.instance);
            StringBuffer append3 = sbuf.append(' ');
        }
        StringBuffer append4 = sbuf.append(this.type == null ? "(null)" : this.type.getName());
        StringBuffer append5 = sbuf.append('.');
        StringBuffer append6 = sbuf.append(this.mname);
        StringBuffer append7 = sbuf.append(']');
        return sbuf.toString();
    }
}
