package gnu.expr;

import gnu.bytecode.Field;
import gnu.bytecode.Type;

public class Literal {
    static final int CYCLIC = 4;
    static final int EMITTED = 8;
    static final int WRITING = 1;
    static final int WRITTEN = 2;
    public static final Literal nullLiteral;
    Type[] argTypes;
    Object[] argValues;
    public Field field;
    public int flags;
    int index;
    Literal next;
    public Type type;
    Object value;

    static {
        Literal literal;
        new Literal((Object) null, (Type) Type.nullType);
        nullLiteral = literal;
    }

    public final Object getValue() {
        return this.value;
    }

    /* access modifiers changed from: package-private */
    public void assign(LitTable litTable) {
        assign((String) null, litTable);
    }

    /* access modifiers changed from: package-private */
    public void assign(String str, LitTable litTable) {
        StringBuilder sb;
        String name = str;
        LitTable litTable2 = litTable;
        int flags2 = litTable2.comp.immediate ? 9 : 24;
        if (name == null) {
            LitTable litTable3 = litTable2;
            int i = litTable3.literalsCount;
            litTable3.literalsCount = i + 1;
            this.index = i;
            new StringBuilder();
            name = sb.append("Lit").append(this.index).toString();
        } else {
            flags2 |= 1;
        }
        assign(litTable2.mainClass.addField(name, this.type, flags2), litTable2);
    }

    /* access modifiers changed from: package-private */
    public void assign(Field field2, LitTable litTable) {
        LitTable litTable2 = litTable;
        this.next = litTable2.literalsChain;
        litTable2.literalsChain = this;
        this.field = field2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Literal(Object value2, LitTable litTable) {
        this(value2, (String) null, litTable);
    }

    public Literal(Object obj, String name, LitTable litTable) {
        Object value2 = obj;
        LitTable litTable2 = litTable;
        this.value = value2;
        Object put = litTable2.literalTable.put(value2, this);
        this.type = Type.make(value2.getClass());
        assign(name, litTable2);
    }

    public Literal(Object obj, Field field2, LitTable litTable) {
        Object value2 = obj;
        Field field3 = field2;
        this.value = value2;
        Object put = litTable.literalTable.put(value2, this);
        this.field = field3;
        this.type = field3.getType();
        this.flags = 10;
    }

    public Literal(Object obj, Type type2, LitTable litTable) {
        Object value2 = obj;
        this.value = value2;
        Object put = litTable.literalTable.put(value2, this);
        this.type = type2;
    }

    Literal(Object value2, Type type2) {
        this.value = value2;
        this.type = type2;
    }
}
