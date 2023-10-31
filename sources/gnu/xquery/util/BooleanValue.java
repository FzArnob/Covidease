package gnu.xquery.util;

import gnu.kawa.xml.UntypedAtomic;
import gnu.lists.SeqPosition;
import gnu.lists.Sequence;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.Numeric;
import gnu.math.RealNum;
import gnu.text.Path;

public class BooleanValue extends Procedure1 {
    public static final BooleanValue booleanValue;

    static {
        BooleanValue booleanValue2;
        new BooleanValue("boolean-value");
        booleanValue = booleanValue2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BooleanValue(String name) {
        super(name);
        setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateBooleanValue");
    }

    public static boolean booleanValue(Object obj) {
        Throwable th;
        Object value = obj;
        if (value instanceof Boolean) {
            return ((Boolean) value).booleanValue();
        }
        if ((value instanceof Number) && ((value instanceof RealNum) || !(value instanceof Numeric))) {
            double d = ((Number) value).doubleValue();
            return d != 0.0d && !Double.isNaN(d);
        } else if (value instanceof SeqPosition) {
            return true;
        } else {
            if ((value instanceof String) || (value instanceof Path) || (value instanceof UntypedAtomic)) {
                return value.toString().length() > 0;
            }
            if (value instanceof Values) {
                Values values = (Values) value;
                Object value1 = values.getPosNext(0);
                if (value1 == Sequence.eofValue) {
                    return false;
                }
                if (values.nextDataIndex(0) < 0) {
                    return booleanValue(value1);
                }
                if (value1 instanceof SeqPosition) {
                    return true;
                }
            }
            Throwable th2 = th;
            new WrongType("fn:boolean", 1, value, "boolean-convertible-value");
            throw th2;
        }
    }

    public static boolean not(Object value) {
        return !booleanValue(value);
    }

    public Object apply1(Object arg) {
        return booleanValue(arg) ? Boolean.TRUE : Boolean.FALSE;
    }
}
