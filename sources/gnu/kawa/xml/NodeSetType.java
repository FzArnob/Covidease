package gnu.kawa.xml;

import gnu.bytecode.Type;
import gnu.kawa.reflect.OccurrenceType;

public class NodeSetType extends OccurrenceType {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NodeSetType(Type itemType) {
        super(itemType, 0, -1);
    }

    public static Type getInstance(Type base) {
        Type base2;
        new NodeSetType(base);
        return base2;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(super.toString()).append("node-set").toString();
    }
}
