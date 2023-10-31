package gnu.math;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class FixedRealFormat extends Format {

    /* renamed from: d */
    private int f241d;

    /* renamed from: i */
    private int f242i;
    public boolean internalPad;
    public char overflowChar;
    public char padChar;
    public int scale;
    public boolean showPlus;
    public int width;

    public FixedRealFormat() {
    }

    public int getMaximumFractionDigits() {
        return this.f241d;
    }

    public int getMinimumIntegerDigits() {
        return this.f242i;
    }

    public void setMaximumFractionDigits(int d) {
        int i = d;
        this.f241d = i;
    }

    public void setMinimumIntegerDigits(int i) {
        int i2 = i;
        this.f242i = i2;
    }

    public void format(RealNum realNum, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        RealNum number = realNum;
        StringBuffer sbuf = stringBuffer;
        FieldPosition fpos = fieldPosition;
        if (number instanceof RatNum) {
            int maximumFractionDigits = getMaximumFractionDigits();
            int decimals = maximumFractionDigits;
            if (maximumFractionDigits >= 0) {
                RatNum ratnum = (RatNum) number;
                boolean negative = ratnum.isNegative();
                if (negative) {
                    ratnum = ratnum.rneg();
                }
                int oldSize = sbuf.length();
                int signLen = 1;
                if (negative) {
                    StringBuffer append = sbuf.append('-');
                } else if (this.showPlus) {
                    StringBuffer append2 = sbuf.append('+');
                } else {
                    signLen = 0;
                }
                String string = RealNum.toScaledInt(ratnum, decimals + this.scale).toString();
                StringBuffer append3 = sbuf.append(string);
                int length = string.length();
                format(sbuf, fpos, length, length - decimals, decimals, signLen, oldSize);
                return;
            }
        }
        StringBuffer format = format(number.doubleValue(), sbuf, fpos);
    }

    public StringBuffer format(long num, StringBuffer stringBuffer, FieldPosition fpos) {
        StringBuffer sbuf = stringBuffer;
        format((RealNum) IntNum.make(num), sbuf, fpos);
        return sbuf;
    }

    public StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        boolean negative;
        int decimals;
        char nextDigit;
        StringBuilder sb;
        double num = d;
        StringBuffer sbuf = stringBuffer;
        FieldPosition fpos = fieldPosition;
        if (Double.isNaN(num) || Double.isInfinite(num)) {
            return sbuf.append(num);
        }
        if (getMaximumFractionDigits() >= 0) {
            format((RealNum) DFloNum.toExact(num), sbuf, fpos);
        } else {
            if (num < 0.0d) {
                negative = true;
                num = -num;
            } else {
                negative = false;
            }
            int oldSize = sbuf.length();
            int signLen = 1;
            if (negative) {
                StringBuffer append = sbuf.append('-');
            } else {
                if (this.showPlus) {
                    StringBuffer append2 = sbuf.append('+');
                } else {
                    signLen = 0;
                }
            }
            String string = Double.toString(num);
            int cur_scale = this.scale;
            int seenE = string.indexOf(69);
            if (seenE >= 0) {
                int expStart = seenE + 1;
                if (string.charAt(expStart) == '+') {
                    expStart++;
                }
                cur_scale += Integer.parseInt(string.substring(expStart));
                string = string.substring(0, seenE);
            }
            int seenDot = string.indexOf(46);
            int length = string.length();
            if (seenDot >= 0) {
                cur_scale -= (length - seenDot) - 1;
                int length2 = length - 1;
                new StringBuilder();
                string = sb.append(string.substring(0, seenDot)).append(string.substring(seenDot + 1)).toString();
            }
            int i = string.length();
            int initial_zeros = 0;
            while (initial_zeros < i - 1 && string.charAt(initial_zeros) == '0') {
                initial_zeros++;
            }
            if (initial_zeros > 0) {
                string = string.substring(initial_zeros);
                i -= initial_zeros;
            }
            int digits = i + cur_scale;
            if (this.width > 0) {
                while (digits < 0) {
                    StringBuffer append3 = sbuf.append('0');
                    digits++;
                    i++;
                }
                decimals = ((this.width - signLen) - 1) - digits;
            } else {
                decimals = (i > 16 ? 16 : i) - digits;
            }
            if (decimals < 0) {
                decimals = 0;
            }
            StringBuffer append4 = sbuf.append(string);
            while (cur_scale > 0) {
                StringBuffer append5 = sbuf.append('0');
                cur_scale--;
                i++;
            }
            int digStart = oldSize + signLen;
            int digEnd = digStart + digits + decimals;
            int i2 = sbuf.length();
            if (digEnd >= i2) {
                digEnd = i2;
                nextDigit = '0';
            } else {
                nextDigit = sbuf.charAt(digEnd);
            }
            boolean addOne = nextDigit >= '5';
            char skip = addOne ? '9' : '0';
            while (digEnd > digStart + digits && sbuf.charAt(digEnd - 1) == skip) {
                digEnd--;
            }
            int length3 = digEnd - digStart;
            int decimals2 = length3 - digits;
            if (addOne && ExponentialFormat.addOne(sbuf, digStart, digEnd)) {
                digits++;
                decimals2 = 0;
                length3 = digits;
            }
            if (decimals2 == 0) {
                if (this.width <= 0 || signLen + digits + 1 < this.width) {
                    decimals2 = 1;
                    length3++;
                    StringBuffer insert = sbuf.insert(digStart + digits, '0');
                }
            }
            sbuf.setLength(digStart + length3);
            format(sbuf, fpos, length3, digits, decimals2, negative ? 1 : 0, oldSize);
        }
        return sbuf;
    }

    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        Object num = obj;
        StringBuffer sbuf = stringBuffer;
        FieldPosition fpos = fieldPosition;
        RealNum rnum = RealNum.asRealNumOrNull(num);
        if (rnum == null) {
            if (num instanceof Complex) {
                String str = num.toString();
                int padding = this.width - str.length();
                while (true) {
                    padding--;
                    if (padding >= 0) {
                        StringBuffer append = sbuf.append(' ');
                    } else {
                        StringBuffer append2 = sbuf.append(str);
                        return sbuf;
                    }
                }
            } else {
                rnum = (RealNum) num;
            }
        }
        return format(rnum.doubleValue(), sbuf, fpos);
    }

    private void format(StringBuffer stringBuffer, FieldPosition fieldPosition, int i, int i2, int i3, int i4, int i5) {
        int zero_digits;
        StringBuffer sbuf = stringBuffer;
        FieldPosition fieldPosition2 = fieldPosition;
        int length = i;
        int digits = i2;
        int decimals = i3;
        int signLen = i4;
        int oldSize = i5;
        int i6 = digits + decimals;
        int zero_digits2 = getMinimumIntegerDigits();
        if (digits < 0 || digits <= zero_digits2) {
            zero_digits = zero_digits2 - digits;
        } else {
            zero_digits = 0;
        }
        if (digits + zero_digits <= 0 && (this.width <= 0 || this.width > decimals + 1 + signLen)) {
            zero_digits++;
        }
        int padding = this.width - (((signLen + length) + zero_digits) + 1);
        int i7 = zero_digits;
        while (true) {
            i7--;
            if (i7 < 0) {
                break;
            }
            StringBuffer insert = sbuf.insert(oldSize + signLen, '0');
        }
        if (padding >= 0) {
            int i8 = oldSize;
            if (this.internalPad && signLen > 0) {
                i8++;
            }
            while (true) {
                padding--;
                if (padding < 0) {
                    break;
                }
                StringBuffer insert2 = sbuf.insert(i8, this.padChar);
            }
        } else if (this.overflowChar != 0) {
            sbuf.setLength(oldSize);
            this.f242i = this.width;
            while (true) {
                int i9 = this.f242i - 1;
                int i10 = i9;
                this.f242i = i9;
                if (i10 >= 0) {
                    StringBuffer append = sbuf.append(this.overflowChar);
                } else {
                    return;
                }
            }
        }
        StringBuffer insert3 = sbuf.insert(sbuf.length() - decimals, '.');
    }

    public Number parse(String str, ParsePosition parsePosition) {
        Throwable th;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        Throwable th2 = th;
        new Error("RealFixedFormat.parse - not implemented");
        throw th2;
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        Throwable th;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        Throwable th2 = th;
        new Error("RealFixedFormat.parseObject - not implemented");
        throw th2;
    }
}
