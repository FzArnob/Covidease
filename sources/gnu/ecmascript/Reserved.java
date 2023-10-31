package gnu.ecmascript;

import gnu.mapping.Procedure;

public class Reserved {
    public static final int BREAK_TOKEN = 35;
    public static final int CONTINUE_TOKEN = 34;
    public static final int ELSE_TOKEN = 38;
    public static final int FOR_TOKEN = 33;
    public static final int FUNCTION_TOKEN = 41;
    public static final int IF_TOKEN = 31;
    public static final int LESS_OP = 5;
    public static final int LSHIFT_OP = 4;
    public static final int MINUS_OP = 2;
    public static final int NEW_TOKEN = 39;
    public static final int PLUS_OP = 1;
    public static final int RETURN_TOKEN = 36;
    public static final int THIS_TOKEN = 40;
    public static final int TIMES_OP = 3;
    public static final int VAR_TOKEN = 30;
    public static final int WHILE_TOKEN = 32;
    public static final int WITH_TOKEN = 37;
    static final Reserved opBitAnd;
    static final Reserved opBitOr;
    static final Reserved opBitXor;
    static final Reserved opBoolAnd;
    static final Reserved opBoolOr;
    static final Reserved opDivide;
    static final Reserved opEqual;
    static final Reserved opGreater;
    static final Reserved opGreaterEqual;
    static final Reserved opLess;
    static final Reserved opLessEqual;
    static final Reserved opLshift;
    static final Reserved opMinus;
    static Reserved opMinusMinus;
    static final Reserved opNotEqual;
    static final Reserved opPlus;
    static Reserved opPlusPlus;
    static final Reserved opRemainder;
    static final Reserved opRshiftSigned;
    static final Reserved opRshiftUnsigned;
    static final Reserved opTimes;
    String name;
    int prio;
    Procedure proc;

    public Reserved(String name2, int prio2, Procedure proc2) {
        this.name = name2;
        this.prio = prio2;
        this.proc = proc2;
    }

    public Reserved(String name2, int prio2) {
        this.name = name2;
        this.prio = prio2;
    }

    public Reserved(String str, int prio2, int op) {
        Procedure procedure;
        String name2 = str;
        this.name = name2;
        this.prio = prio2;
        new BinaryOp(name2, op);
        this.proc = procedure;
    }

    static {
        Reserved reserved;
        Reserved reserved2;
        Reserved reserved3;
        Reserved reserved4;
        Reserved reserved5;
        Reserved reserved6;
        Reserved reserved7;
        Reserved reserved8;
        Reserved reserved9;
        Reserved reserved10;
        Reserved reserved11;
        Reserved reserved12;
        Reserved reserved13;
        Reserved reserved14;
        Reserved reserved15;
        Reserved reserved16;
        Reserved reserved17;
        Reserved reserved18;
        Reserved reserved19;
        new Reserved("||", 1, 0);
        opBoolOr = reserved;
        new Reserved("&&", 2, 0);
        opBoolAnd = reserved2;
        new Reserved("|", 3, 0);
        opBitOr = reserved3;
        new Reserved("^", 4, 0);
        opBitXor = reserved4;
        new Reserved("&", 5, 0);
        opBitAnd = reserved5;
        new Reserved("=", 6, 0);
        opEqual = reserved6;
        new Reserved("!=", 6, 0);
        opNotEqual = reserved7;
        new Reserved("<", 7, 5);
        opLess = reserved8;
        new Reserved(">", 7, 0);
        opGreater = reserved9;
        new Reserved("<=", 7, 0);
        opLessEqual = reserved10;
        new Reserved(">=", 7, 0);
        opGreaterEqual = reserved11;
        new Reserved("<<", 8, 4);
        opLshift = reserved12;
        new Reserved(">>", 8, 0);
        opRshiftSigned = reserved13;
        new Reserved(">>>", 8, 0);
        opRshiftUnsigned = reserved14;
        new Reserved("+", 9, 1);
        opPlus = reserved15;
        new Reserved("-", 9, 2);
        opMinus = reserved16;
        new Reserved("*", 10, 3);
        opTimes = reserved17;
        new Reserved("/", 10, 0);
        opDivide = reserved18;
        new Reserved("%", 10, 0);
        opRemainder = reserved19;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[Reserved \"").append(this.name).append("\" prio:").append(this.prio).append("]").toString();
    }

    public boolean isAssignmentOp() {
        return false;
    }
}
