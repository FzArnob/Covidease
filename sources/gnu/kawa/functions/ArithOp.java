package gnu.kawa.functions;

import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.mapping.ProcedureN;
import gnu.math.IntNum;

public abstract class ArithOp extends ProcedureN {
    static final int ADD = 1;
    public static final int AND = 13;
    public static final int ASHIFT_GENERAL = 9;
    public static final int ASHIFT_LEFT = 10;
    public static final int ASHIFT_RIGHT = 11;
    public static final int DIVIDE_GENERIC = 4;
    public static final int DIVIDE_INEXACT = 5;
    public static final int IOR = 14;
    public static final int LSHIFT_RIGHT = 12;
    public static final int MODULO = 8;
    static final int MUL = 3;
    public static final int NOT = 16;
    public static final int QUOTIENT = 6;
    public static final int QUOTIENT_EXACT = 7;
    static final int SUB = 2;
    public static final int XOR = 15;

    /* renamed from: op */
    final int f61op;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ArithOp(String name, int op) {
        super(name);
        this.f61op = op;
    }

    public Object defaultResult() {
        return IntNum.zero();
    }

    public boolean isSideEffectFree() {
        return true;
    }

    public static int classify(Type type) {
        Type type2 = type;
        if (type2 instanceof PrimType) {
            char sig = type2.getSignature().charAt(0);
            if (sig == 'V' || sig == 'Z' || sig == 'C') {
                return 0;
            }
            if (sig == 'D' || sig == 'F') {
                return 3;
            }
            return 4;
        } else if (type2.isSubtype(Arithmetic.typeIntNum)) {
            return 4;
        } else {
            if (type2.isSubtype(Arithmetic.typeDFloNum)) {
                return 3;
            }
            if (type2.isSubtype(Arithmetic.typeRealNum)) {
                return 2;
            }
            if (type2.isSubtype(Arithmetic.typeNumeric)) {
                return 1;
            }
            return 0;
        }
    }
}
