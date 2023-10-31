package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.NamedLocation;
import gnu.mapping.Symbol;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrappedException;
import java.lang.reflect.Field;

public abstract class ClassMemberLocation extends Location {
    Object instance;
    String mname;
    Field rfield;
    ClassType type;

    public final Object getInstance() {
        return this.instance;
    }

    public final void setInstance(Object obj) {
        Object obj2 = obj;
        this.instance = obj2;
    }

    public ClassMemberLocation(Object instance2, ClassType type2, String mname2) {
        this.instance = instance2;
        this.type = type2;
        this.mname = mname2;
    }

    public ClassMemberLocation(Object instance2, Class clas, String mname2) {
        this.instance = instance2;
        this.type = (ClassType) Type.make(clas);
        this.mname = mname2;
    }

    public ClassMemberLocation(Object instance2, Field field) {
        Field field2 = field;
        this.instance = instance2;
        this.rfield = field2;
        this.mname = field2.getName();
    }

    public String getMemberName() {
        return this.mname;
    }

    public ClassType getDeclaringClass() {
        return this.type;
    }

    /* access modifiers changed from: package-private */
    public void setup() {
        RuntimeException runtimeException;
        StringBuilder sb;
        RuntimeException runtimeException2;
        StringBuilder sb2;
        if (this.rfield == null) {
            try {
                try {
                    this.rfield = this.type.getReflectClass().getField(this.mname);
                } catch (NoSuchFieldException e) {
                    new StringBuilder();
                    new UnboundLocationException((Object) null, sb2.append("Unbound location  - no field ").append(this.mname).append(" in ").append(this.type.getName()).toString());
                    RuntimeException uex = runtimeException2;
                    Throwable initCause = uex.initCause(e);
                    throw uex;
                }
            } catch (RuntimeException e2) {
                RuntimeException ex = e2;
                new StringBuilder();
                new UnboundLocationException((Object) null, sb.append("Unbound location - ").append(ex.toString()).toString());
                RuntimeException uex2 = runtimeException;
                Throwable initCause2 = uex2.initCause(ex);
                throw uex2;
            }
        }
    }

    public Field getRField() {
        Field rfld = this.rfield;
        if (rfld == null) {
            try {
                rfld = this.type.getReflectClass().getField(this.mname);
                this.rfield = rfld;
            } catch (Exception e) {
                Exception exc = e;
                return null;
            }
        }
        return rfld;
    }

    public Class getRClass() {
        Field rfld = this.rfield;
        if (rfld != null) {
            return rfld.getDeclaringClass();
        }
        try {
            return this.type.getReflectClass();
        } catch (Exception e) {
            Exception exc = e;
            return null;
        }
    }

    public Object get(Object obj) {
        Object defaultValue = obj;
        Field rfld = getRField();
        if (rfld == null) {
            return defaultValue;
        }
        try {
            return rfld.get(this.instance);
        } catch (IllegalAccessException e) {
            throw WrappedException.wrapIfNeeded(e);
        }
    }

    public boolean isConstant() {
        return (getRField() == null || (this.rfield.getModifiers() & 16) == 0) ? false : true;
    }

    public boolean isBound() {
        return getRField() != null;
    }

    public void set(Object obj) {
        Object value = obj;
        setup();
        try {
            this.rfield.set(this.instance, value);
        } catch (IllegalAccessException e) {
            throw WrappedException.wrapIfNeeded(e);
        }
    }

    public static void define(Object obj, Field field, String str, Language language, Environment environment) throws IllegalAccessException {
        Symbol sym;
        Location location;
        Location loc;
        Location location2;
        Object instance2 = obj;
        Field rfield2 = field;
        String uri = str;
        Language language2 = language;
        Environment env = environment;
        Object fvalue = rfield2.get(instance2);
        Type ftype = Type.make(rfield2.getType());
        boolean isAlias = ftype.isSubtype(Compilation.typeLocation);
        boolean isSubtype = ftype.isSubtype(Compilation.typeProcedure);
        int rModifiers = rfield2.getModifiers();
        boolean isFinal = (rModifiers & 16) != 0;
        Object fdname = (!isFinal || !(fvalue instanceof Named) || isAlias) ? Compilation.demangleName(rfield2.getName(), true) : ((Named) fvalue).getSymbol();
        if (fdname instanceof Symbol) {
            sym = (Symbol) fdname;
        } else {
            sym = Symbol.make(uri == null ? "" : uri, fdname.toString().intern());
        }
        Object property = null;
        if (!isAlias || !isFinal) {
            if (isFinal) {
                property = language2.getEnvPropertyFor(rfield2, fvalue);
            }
            if ((rModifiers & 8) != 0) {
                new StaticFieldLocation(rfield2);
                loc = location2;
            } else {
                new FieldLocation(instance2, rfield2);
                loc = location;
            }
        } else {
            loc = (Location) fvalue;
        }
        NamedLocation addLocation = env.addLocation(sym, property, loc);
    }

    public static void defineAll(Object obj, Language language, Environment environment) throws IllegalAccessException {
        Object instance2 = obj;
        Language language2 = language;
        Environment env = environment;
        Field[] fields = instance2.getClass().getFields();
        int i = fields.length;
        while (true) {
            i--;
            if (i >= 0) {
                Field field = fields[i];
                String fname = field.getName();
                if (!fname.startsWith(Declaration.PRIVATE_PREFIX) && !fname.endsWith("$instance")) {
                    define(instance2, field, (String) null, language2, env);
                }
            } else {
                return;
            }
        }
    }
}
