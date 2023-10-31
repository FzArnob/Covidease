package gnu.mapping;

public class WrongArguments extends IllegalArgumentException {
    public int number;
    Procedure proc;
    public String procname;
    public String usage;

    public static String checkArgCount(Procedure procedure, int i) {
        Procedure proc2 = procedure;
        int argCount = i;
        int num = proc2.numArgs();
        int min = num & 4095;
        int max = num >> 12;
        String pname = proc2.getName();
        if (pname == null) {
            pname = proc2.getClass().getName();
        }
        return checkArgCount(pname, min, max, argCount);
    }

    public static String checkArgCount(String str, int i, int i2, int i3) {
        boolean tooMany;
        StringBuffer stringBuffer;
        String pname = str;
        int min = i;
        int max = i2;
        int argCount = i3;
        if (argCount < min) {
            tooMany = false;
        } else if (max < 0 || argCount <= max) {
            return null;
        } else {
            tooMany = true;
        }
        new StringBuffer(100);
        StringBuffer buf = stringBuffer;
        StringBuffer append = buf.append("call to ");
        if (pname == null) {
            StringBuffer append2 = buf.append("unnamed procedure");
        } else {
            StringBuffer append3 = buf.append('\'');
            StringBuffer append4 = buf.append(pname);
            StringBuffer append5 = buf.append('\'');
        }
        StringBuffer append6 = buf.append(tooMany ? " has too many" : " has too few");
        StringBuffer append7 = buf.append(" arguments (");
        StringBuffer append8 = buf.append(argCount);
        if (min == max) {
            StringBuffer append9 = buf.append("; must be ");
            StringBuffer append10 = buf.append(min);
        } else {
            StringBuffer append11 = buf.append("; min=");
            StringBuffer append12 = buf.append(min);
            if (max >= 0) {
                StringBuffer append13 = buf.append(", max=");
                StringBuffer append14 = buf.append(max);
            }
        }
        StringBuffer append15 = buf.append(')');
        return buf.toString();
    }

    public String getMessage() {
        String msg;
        if (this.proc == null || (msg = checkArgCount(this.proc, this.number)) == null) {
            return super.getMessage();
        }
        return msg;
    }

    public WrongArguments(Procedure proc2, int argCount) {
        this.proc = proc2;
        this.number = argCount;
    }

    public WrongArguments(String name, int n, String u) {
        this.procname = name;
        this.number = n;
        this.usage = u;
    }
}
