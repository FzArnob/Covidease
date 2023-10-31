package gnu.lists;

import com.google.appinventor.components.runtime.util.Ev3Constants;

public class Convert {
    public static Convert instance;

    public Convert() {
    }

    static {
        Convert convert;
        new Convert();
        instance = convert;
    }

    public static Convert getInstance() {
        return instance;
    }

    public static void setInstance(Convert value) {
        instance = value;
    }

    public Object booleanToObject(boolean value) {
        return value ? Boolean.TRUE : Boolean.FALSE;
    }

    public boolean objectToBoolean(Object obj) {
        Object obj2 = obj;
        return !(obj2 instanceof Boolean) || ((Boolean) obj2).booleanValue();
    }

    public static Object toObject(boolean value) {
        return instance.booleanToObject(value);
    }

    public static boolean toBoolean(Object obj) {
        return instance.objectToBoolean(obj);
    }

    public Object charToObject(char ch) {
        Object obj;
        new Character(ch);
        return obj;
    }

    public char objectToChar(Object obj) {
        return ((Character) obj).charValue();
    }

    public static Object toObject(char ch) {
        return instance.charToObject(ch);
    }

    public static char toChar(Object obj) {
        return instance.objectToChar(obj);
    }

    public Object byteToObject(byte value) {
        Object obj;
        new Byte(value);
        return obj;
    }

    public byte objectToByte(Object obj) {
        return ((Number) obj).byteValue();
    }

    public static Object toObject(byte value) {
        return instance.byteToObject(value);
    }

    public static byte toByte(Object obj) {
        return instance.objectToByte(obj);
    }

    public Object byteToObjectUnsigned(byte value) {
        Object obj;
        new Integer(value & Ev3Constants.Opcode.TST);
        return obj;
    }

    public byte objectToByteUnsigned(Object obj) {
        return ((Number) obj).byteValue();
    }

    public static Object toObjectUnsigned(byte value) {
        return instance.byteToObjectUnsigned(value);
    }

    public static byte toByteUnsigned(Object obj) {
        return instance.objectToByteUnsigned(obj);
    }

    public Object shortToObject(short value) {
        Object obj;
        new Short(value);
        return obj;
    }

    public short objectToShort(Object obj) {
        return ((Number) obj).shortValue();
    }

    public static Object toObject(short value) {
        return instance.shortToObject(value);
    }

    public static short toShort(Object obj) {
        return instance.objectToShort(obj);
    }

    public Object shortToObjectUnsigned(short value) {
        Object obj;
        new Integer(value & 65535);
        return obj;
    }

    public short objectToShortUnsigned(Object obj) {
        return ((Number) obj).shortValue();
    }

    public static Object toObjectUnsigned(short value) {
        return instance.shortToObjectUnsigned(value);
    }

    public static short toShortUnsigned(Object obj) {
        return instance.objectToShortUnsigned(obj);
    }

    public Object intToObject(int value) {
        Object obj;
        new Integer(value);
        return obj;
    }

    public int objectToInt(Object obj) {
        return ((Number) obj).intValue();
    }

    public static Object toObject(int value) {
        return instance.intToObject(value);
    }

    public static int toInt(Object obj) {
        return instance.objectToInt(obj);
    }

    public Object intToObjectUnsigned(int i) {
        Object obj;
        Object obj2;
        int value = i;
        if (value >= 0) {
            new Integer(value);
            return obj2;
        }
        new Long(((long) value) & 4294967295L);
        return obj;
    }

    public int objectToIntUnsigned(Object obj) {
        return ((Number) obj).intValue();
    }

    public static Object toObjectUnsigned(int value) {
        return instance.intToObjectUnsigned(value);
    }

    public static int toIntUnsigned(Object obj) {
        return instance.objectToIntUnsigned(obj);
    }

    public Object longToObject(long value) {
        Object obj;
        new Long(value);
        return obj;
    }

    public long objectToLong(Object obj) {
        return ((Number) obj).longValue();
    }

    public static Object toObject(long value) {
        return instance.longToObject(value);
    }

    public static long toLong(Object obj) {
        return instance.objectToLong(obj);
    }

    public Object longToObjectUnsigned(long value) {
        Object obj;
        new Long(value);
        return obj;
    }

    public long objectToLongUnsigned(Object obj) {
        return ((Number) obj).longValue();
    }

    public static Object toObjectUnsigned(long value) {
        return instance.longToObjectUnsigned(value);
    }

    public static long toLongUnsigned(Object obj) {
        return instance.objectToLongUnsigned(obj);
    }

    public Object floatToObject(float value) {
        Object obj;
        new Float(value);
        return obj;
    }

    public float objectToFloat(Object obj) {
        return ((Number) obj).floatValue();
    }

    public static Object toObject(float value) {
        return instance.floatToObject(value);
    }

    public static float toFloat(Object obj) {
        return instance.objectToFloat(obj);
    }

    public Object doubleToObject(double value) {
        Object obj;
        new Double(value);
        return obj;
    }

    public double objectToDouble(Object obj) {
        return ((Number) obj).doubleValue();
    }

    public static Object toObject(double value) {
        return instance.doubleToObject(value);
    }

    public static double toDouble(Object obj) {
        return instance.objectToDouble(obj);
    }

    public static double parseDouble(String str) {
        return Double.parseDouble(str);
    }
}
