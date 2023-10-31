package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.Language;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import java.lang.reflect.Array;

/* compiled from: Setter */
class SetArray extends Procedure2 {
    Object array;
    Type elementType;

    public SetArray(Object obj, Language language) {
        Object array2 = obj;
        this.elementType = language.getTypeFor(array2.getClass().getComponentType());
        this.array = array2;
    }

    public Object apply2(Object index, Object value) {
        Array.set(this.array, ((Number) index).intValue(), this.elementType.coerceFromObject(value));
        return Values.empty;
    }
}
