package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import java.util.List;

/* compiled from: Setter */
class SetList extends Procedure2 {
    Type elementType;
    List list;

    public SetList(List list2) {
        this.list = list2;
    }

    public Object apply2(Object index, Object value) {
        Object obj = this.list.set(((Number) index).intValue(), value);
        return Values.empty;
    }
}
