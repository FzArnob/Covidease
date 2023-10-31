package gnu.kawa.functions;

import gnu.math.RealNum;
import gnu.text.EnglishIntegerFormat;
import gnu.text.RomanIntegerFormat;
import java.text.Format;

public class IntegerFormat extends gnu.text.IntegerFormat {
    private static IntegerFormat plainDecimalFormat;

    public IntegerFormat() {
    }

    public static IntegerFormat getInstance() {
        IntegerFormat integerFormat;
        if (plainDecimalFormat == null) {
            new IntegerFormat();
            plainDecimalFormat = integerFormat;
        }
        return plainDecimalFormat;
    }

    public static Format getInstance(int i, int i2, int i3, int i4, int i5, int i6) {
        IntegerFormat integerFormat;
        int base = i;
        int minWidth = i2;
        int padChar = i3;
        int commaChar = i4;
        int commaInterval = i5;
        int flags = i6;
        if (base == -1073741824) {
            if (padChar == -1073741824 && padChar == -1073741824 && commaChar == -1073741824 && commaInterval == -1073741824) {
                boolean seenColon = (flags & 1) != 0;
                if ((flags & 2) != 0) {
                    return RomanIntegerFormat.getInstance(seenColon);
                }
                return EnglishIntegerFormat.getInstance(seenColon);
            }
            base = 10;
        }
        if (minWidth == -1073741824) {
            minWidth = 1;
        }
        if (padChar == -1073741824) {
            padChar = 32;
        }
        if (commaChar == -1073741824) {
            commaChar = 44;
        }
        if (commaInterval == -1073741824) {
            commaInterval = 3;
        }
        if (base == 10 && minWidth == 1 && padChar == 32 && commaChar == 44 && commaInterval == 3 && flags == 0) {
            return getInstance();
        }
        new IntegerFormat();
        IntegerFormat fmt = integerFormat;
        fmt.base = base;
        fmt.minWidth = minWidth;
        fmt.padChar = padChar;
        fmt.commaChar = commaChar;
        fmt.commaInterval = commaInterval;
        fmt.flags = flags;
        return fmt;
    }

    public String convertToIntegerString(Object obj, int i) {
        Object arg = obj;
        int radix = i;
        if (arg instanceof RealNum) {
            return ((RealNum) arg).toExactInt(4).toString(radix);
        }
        return super.convertToIntegerString(arg, radix);
    }
}
