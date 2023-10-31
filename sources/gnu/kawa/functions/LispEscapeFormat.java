package gnu.kawa.functions;

import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispEscapeFormat extends ReportFormat {
    public static final int ESCAPE_ALL = 242;
    public static final int ESCAPE_NORMAL = 241;
    public static final LispEscapeFormat alwaysTerminate;
    boolean escapeAll;
    int param1;
    int param2;
    int param3;

    static {
        LispEscapeFormat lispEscapeFormat;
        new LispEscapeFormat(0, -1073741824);
        alwaysTerminate = lispEscapeFormat;
    }

    public LispEscapeFormat(int param12, int param22) {
        this.param1 = param12;
        this.param2 = param22;
        this.param3 = -1073741824;
    }

    public LispEscapeFormat(int param12, int param22, int param32) {
        this.param1 = param12;
        this.param2 = param22;
        this.param3 = param32;
    }

    static Numeric getParam(int i, Object[] objArr, int i2) {
        Numeric numeric;
        Numeric numeric2;
        Numeric numeric3;
        Numeric numeric4;
        int param = i;
        Object[] args = objArr;
        int start = i2;
        if (param == -1342177280) {
            return IntNum.make(args.length - start);
        }
        if (param != -1610612736) {
            return IntNum.make(param);
        }
        Object arg = args[start];
        if (arg instanceof Numeric) {
            return (Numeric) arg;
        }
        if (arg instanceof Number) {
            if (!(arg instanceof Float) && !(arg instanceof Double)) {
                return IntNum.make(((Number) arg).longValue());
            }
            new DFloNum(((Number) arg).doubleValue());
            return numeric4;
        } else if (arg instanceof Char) {
            new IntNum(((Char) arg).intValue());
            return numeric3;
        } else if (arg instanceof Character) {
            new IntNum(((Character) arg).charValue());
            return numeric2;
        } else {
            new DFloNum(Double.NaN);
            return numeric;
        }
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        boolean do_terminate;
        boolean z;
        Object[] args = objArr;
        int start = i;
        Writer writer2 = writer;
        FieldPosition fieldPosition2 = fieldPosition;
        int i2 = start;
        if (this.param1 == -1073741824) {
            if (start == args.length) {
                z = true;
            } else {
                z = false;
            }
            do_terminate = z;
        } else if (this.param2 == -1073741824 && this.param1 == 0) {
            do_terminate = true;
        } else {
            Numeric arg1 = getParam(this.param1, args, start);
            if (this.param1 == -1610612736) {
                start++;
            }
            if (this.param2 == -1073741824) {
                do_terminate = arg1.isZero();
            } else {
                Numeric arg2 = getParam(this.param2, args, start);
                if (this.param2 == -1610612736) {
                    start++;
                }
                if (this.param3 == -1073741824) {
                    do_terminate = arg1.equals(arg2);
                } else {
                    Numeric arg3 = getParam(this.param3, args, start);
                    if (this.param3 == -1610612736) {
                        start++;
                    }
                    do_terminate = arg2.geq(arg1) && arg3.geq(arg2);
                }
            }
        }
        return result(!do_terminate ? 0 : this.escapeAll ? ESCAPE_ALL : ESCAPE_NORMAL, start);
    }
}
