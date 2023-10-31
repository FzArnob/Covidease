package gnu.kawa.functions;

import gnu.bytecode.Access;
import gnu.bytecode.ClassType;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.kawa.reflect.ClassMethods;
import gnu.kawa.reflect.SlotGet;
import gnu.mapping.HasNamedParts;
import gnu.mapping.HasSetter;
import gnu.mapping.MethodProc;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Symbol;
import gnu.mapping.Values;

public class GetNamedPart extends Procedure2 implements HasSetter {
    public static final String CAST_METHOD_NAME = "@";
    public static final String CLASSTYPE_FOR = "<>";
    public static final String INSTANCEOF_METHOD_NAME = "instance?";
    public static final GetNamedPart getNamedPart;

    public GetNamedPart() {
    }

    static {
        GetNamedPart getNamedPart2;
        new GetNamedPart();
        getNamedPart = getNamedPart2;
        getNamedPart.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateGetNamedPart");
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        Symbol sym;
        Values values;
        Object container = obj;
        Object part = obj2;
        if (container instanceof Values) {
            Object[] values2 = ((Values) container).getValues();
            new Values();
            Values result = values;
            for (int i = 0; i < values2.length; i++) {
                Values.writeValues(apply2(values2[i], part), result);
            }
            return result.canonicalize();
        }
        if (part instanceof Symbol) {
            sym = (Symbol) part;
        } else {
            sym = Namespace.EmptyNamespace.getSymbol(part.toString().intern());
        }
        return getNamedPart(container, sym);
    }

    public static Object getTypePart(Type type, String str) throws Throwable {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Type type2 = type;
        String name = str;
        if (name.equals(CLASSTYPE_FOR)) {
            return type2;
        }
        if (type2 instanceof ObjectType) {
            if (name.equals(INSTANCEOF_METHOD_NAME)) {
                new NamedPart(type2, name, Access.INNERCLASS_CONTEXT);
                return obj4;
            } else if (name.equals(CAST_METHOD_NAME)) {
                new NamedPart(type2, name, Access.CLASS_CONTEXT);
                return obj3;
            } else if (name.equals("new")) {
                new NamedPart(type2, name, 'N');
                return obj2;
            } else if (name.equals(".length") || (name.length() > 1 && name.charAt(0) == '.' && (type2 instanceof ClassType))) {
                new NamedPart(type2, name, 'D');
                return obj;
            }
        }
        if (!(type2 instanceof ClassType)) {
            return getMemberPart(type2, name);
        }
        try {
            return SlotGet.staticField(type2, name);
        } catch (Throwable th) {
            Throwable th2 = th;
            return ClassMethods.apply(ClassMethods.classMethods, type2, name);
        }
    }

    public static Object getNamedPart(Object obj, Symbol symbol) throws Throwable {
        StringBuilder sb;
        Object container = obj;
        Symbol part = symbol;
        String name = part.getName();
        if (container instanceof HasNamedParts) {
            return ((HasNamedParts) container).get(name);
        }
        if (container instanceof Class) {
            container = Type.make((Class) container);
        }
        if (container instanceof Package) {
            try {
                String pname = ((Package) container).getName();
                new StringBuilder();
                return ClassType.getContextClass(sb.append(pname).append('.').append(name).toString());
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        }
        if (container instanceof Type) {
            return getTypePart((Type) container, name);
        }
        return getMemberPart(container, part.toString());
    }

    public static Object getMemberPart(Object obj, String str) throws Throwable {
        Throwable th;
        StringBuilder sb;
        Object container;
        Object container2 = obj;
        String name = str;
        try {
            return SlotGet.field(container2, name);
        } catch (Throwable th2) {
            Throwable th3 = th2;
            MethodProc methods = ClassMethods.apply((ClassType) ClassType.make(container2.getClass()), Compilation.mangleName(name), 0, Language.getDefaultLanguage());
            if (methods != null) {
                new NamedPart(container2, name, Access.METHOD_CONTEXT, methods);
                return container;
            }
            Throwable th4 = th;
            new StringBuilder();
            new RuntimeException(sb.append("no part '").append(name).append("' in ").append(container2).toString());
            throw th4;
        }
    }

    public Procedure getSetter() {
        return SetNamedPart.setNamedPart;
    }
}
