package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.kawa.reflect.SlotSet;
import gnu.mapping.Environment;
import gnu.mapping.HasSetter;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure3;
import gnu.mapping.Symbol;
import gnu.mapping.Values;

public class SetNamedPart extends Procedure3 implements HasSetter {
    public static final SetNamedPart setNamedPart;

    public SetNamedPart() {
    }

    static {
        SetNamedPart setNamedPart2;
        new SetNamedPart();
        setNamedPart = setNamedPart2;
        setNamedPart.setName("setNamedPart");
        setNamedPart.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateSetNamedPart");
    }

    public Object apply3(Object obj, Object obj2, Object obj3) {
        Object container = obj;
        Object part = obj2;
        Object value = obj3;
        if (container instanceof Namespace) {
            Namespace ns = (Namespace) container;
            String uri = ns.getName();
            if (uri.startsWith("class:")) {
                container = ClassType.make(uri.substring(6));
            } else {
                Symbol sym = ns.getSymbol(part.toString());
                Environment current = Environment.getCurrent();
                Environment.getCurrent().put(sym, value);
                return Values.empty;
            }
        }
        if (container instanceof Class) {
            container = (ClassType) Type.make((Class) container);
        }
        if (container instanceof ClassType) {
            try {
                SlotSet.setStaticField(container, part.toString(), value);
                return Values.empty;
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        }
        SlotSet.setField(container, part.toString(), value);
        return Values.empty;
    }
}
