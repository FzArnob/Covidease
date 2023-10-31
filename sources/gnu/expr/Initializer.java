package gnu.expr;

import gnu.bytecode.Field;

public abstract class Initializer {
    public Field field;
    Initializer next;

    public abstract void emit(Compilation compilation);

    public Initializer() {
    }

    public static Initializer reverse(Initializer initializer) {
        Initializer list = initializer;
        Initializer prev = null;
        while (list != null) {
            Initializer next2 = list.next;
            list.next = prev;
            prev = list;
            list = next2;
        }
        return prev;
    }

    public void reportError(String message, Compilation comp) {
        StringBuilder sb;
        new StringBuilder();
        comp.error('e', sb.append(message).append("field ").append(this.field).toString());
    }
}
