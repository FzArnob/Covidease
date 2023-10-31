package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.expr.Declaration;
import gnu.mapping.Environment;
import gnu.mapping.NamedLocation;
import gnu.mapping.Symbol;
import java.lang.reflect.Field;
import kawa.lang.Macro;

public class StaticFieldLocation extends FieldLocation {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StaticFieldLocation(String cname, String fname) {
        super((Object) null, ClassType.make(cname), fname);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StaticFieldLocation(ClassType type, String mname) {
        super((Object) null, type, mname);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StaticFieldLocation(Field field) {
        super((Object) null, field);
    }

    public Object get(Object defaultValue) {
        Object val = super.get(defaultValue);
        if (val instanceof Macro) {
            Declaration declaration = getDeclaration();
        }
        return val;
    }

    public static StaticFieldLocation define(Environment environ, Symbol sym, Object property, String cname, String fname) {
        StaticFieldLocation staticFieldLocation;
        new StaticFieldLocation(cname, fname);
        StaticFieldLocation loc = staticFieldLocation;
        NamedLocation addLocation = environ.addLocation(sym, property, loc);
        return loc;
    }

    public static StaticFieldLocation make(Declaration declaration) {
        StaticFieldLocation staticFieldLocation;
        Declaration decl = declaration;
        gnu.bytecode.Field fld = decl.field;
        new StaticFieldLocation(fld.getDeclaringClass(), fld.getName());
        StaticFieldLocation loc = staticFieldLocation;
        loc.setDeclaration(decl);
        return loc;
    }

    public static StaticFieldLocation make(String cname, String fldName) {
        StaticFieldLocation staticFieldLocation;
        new StaticFieldLocation(cname, fldName);
        return staticFieldLocation;
    }
}
