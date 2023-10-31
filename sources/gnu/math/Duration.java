package gnu.math;

import gnu.bytecode.Access;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Duration extends Quantity implements Externalizable {
    int months;
    int nanos;
    long seconds;
    public Unit unit;

    public Duration() {
    }

    public static Duration make(int months2, long seconds2, int nanos2, Unit unit2) {
        Duration duration;
        new Duration();
        Duration d = duration;
        d.months = months2;
        d.seconds = seconds2;
        d.nanos = nanos2;
        d.unit = unit2;
        return d;
    }

    public static Duration makeMonths(int months2) {
        Duration duration;
        new Duration();
        Duration d = duration;
        d.unit = Unit.month;
        d.months = months2;
        return d;
    }

    public static Duration makeMinutes(int minutes) {
        Duration duration;
        new Duration();
        Duration d = duration;
        d.unit = Unit.second;
        d.seconds = (long) (60 * minutes);
        return d;
    }

    public static Duration parse(String str, Unit unit2) {
        Throwable th;
        StringBuilder sb;
        String str2 = str;
        Unit unit3 = unit2;
        Duration d = valueOf(str2, unit3);
        if (d != null) {
            return d;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("not a valid ").append(unit3.getName()).append(" duration: '").append(str2).append("'").toString());
        throw th2;
    }

    public static Duration parseDuration(String str) {
        return parse(str, Unit.duration);
    }

    public static Duration parseYearMonthDuration(String str) {
        return parse(str, Unit.month);
    }

    public static Duration parseDayTimeDuration(String str) {
        return parse(str, Unit.second);
    }

    public static Duration valueOf(String str, Unit unit2) {
        boolean negative;
        int pos;
        Duration duration;
        Unit unit3 = unit2;
        String str2 = str.trim();
        int pos2 = 0;
        int len = str2.length();
        if (0 >= len || str2.charAt(0) != '-') {
            negative = false;
        } else {
            negative = true;
            pos2 = 0 + 1;
        }
        if (pos2 + 1 >= len || str2.charAt(pos2) != 'P') {
            return null;
        }
        int months2 = 0;
        int nanos2 = 0;
        long seconds2 = 0;
        long part = scanPart(str2, pos2 + 1);
        int pos3 = ((int) part) >> 16;
        char ch = (char) ((int) part);
        if (unit3 == Unit.second && (ch == 'Y' || ch == 'M')) {
            return null;
        }
        if (ch == 'Y') {
            months2 = 12 * ((int) (part >> 32));
            pos3 = ((int) part) >> 16;
            part = scanPart(str2, pos3);
            ch = (char) ((int) part);
        }
        if (ch == 'M') {
            months2 = (int) (((long) months2) + (part >> 32));
            pos3 = ((int) part) >> 16;
            part = scanPart(str2, pos3);
            ch = (char) ((int) part);
        }
        if (unit3 == Unit.month && pos3 != len) {
            return null;
        }
        if (ch == 'D') {
            if (unit3 == Unit.month) {
                return null;
            }
            seconds2 = 86400 * ((long) ((int) (part >> 32)));
            pos3 = ((int) part) >> 16;
            part = scanPart(str2, pos3);
        }
        if (part != ((long) (pos << 16))) {
            return null;
        }
        if (pos != len) {
            if (str2.charAt(pos) == 'T') {
                pos++;
                if (pos != len) {
                    if (unit3 == Unit.month) {
                        return null;
                    }
                    long part2 = scanPart(str2, pos);
                    char ch2 = (char) ((int) part2);
                    if (ch2 == 'H') {
                        seconds2 += (long) (3600 * ((int) (part2 >> 32)));
                        pos = ((int) part2) >> 16;
                        part2 = scanPart(str2, pos);
                        ch2 = (char) ((int) part2);
                    }
                    if (ch2 == 'M') {
                        seconds2 += (long) (60 * ((int) (part2 >> 32)));
                        pos = ((int) part2) >> 16;
                        part2 = scanPart(str2, pos);
                        ch2 = (char) ((int) part2);
                    }
                    if (ch2 == 'S' || ch2 == '.') {
                        seconds2 += (long) ((int) (part2 >> 32));
                        pos = ((int) part2) >> 16;
                    }
                    if (ch2 == '.' && pos + 1 < len && Character.digit(str2.charAt(pos), 10) >= 0) {
                        int nfrac = 0;
                        while (pos < len) {
                            int i = pos;
                            pos++;
                            ch2 = str2.charAt(i);
                            int dig = Character.digit(ch2, 10);
                            if (dig < 0) {
                                break;
                            }
                            if (nfrac < 9) {
                                nanos2 = (10 * nanos2) + dig;
                            } else if (nfrac == 9 && dig >= 5) {
                                nanos2++;
                            }
                            nfrac++;
                        }
                        while (true) {
                            int i2 = nfrac;
                            nfrac++;
                            if (i2 >= 9) {
                                break;
                            }
                            nanos2 = 10 * nanos2;
                        }
                        if (ch2 != 'S') {
                            return null;
                        }
                    }
                }
            }
            return null;
        }
        if (pos != len) {
            return null;
        }
        new Duration();
        Duration d = duration;
        if (negative) {
            months2 = -months2;
            seconds2 = -seconds2;
            nanos2 = -nanos2;
        }
        d.months = months2;
        d.seconds = seconds2;
        d.nanos = nanos2;
        d.unit = unit3;
        return d;
    }

    public Numeric add(Object obj, int i) {
        Throwable th;
        Object y = obj;
        int k = i;
        if (y instanceof Duration) {
            return add(this, (Duration) y, k);
        }
        if ((y instanceof DateTime) && k == 1) {
            return DateTime.add((DateTime) y, this, 1);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public Numeric mul(Object obj) {
        Object y = obj;
        if (y instanceof RealNum) {
            return times(this, ((RealNum) y).doubleValue());
        }
        return ((Numeric) y).mulReversed(this);
    }

    public Numeric mulReversed(Numeric numeric) {
        Throwable th;
        Numeric x = numeric;
        if (x instanceof RealNum) {
            return times(this, ((RealNum) x).doubleValue());
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public static double div(Duration duration, Duration duration2) {
        Throwable th;
        Throwable th2;
        Duration dur1 = duration;
        Duration dur2 = duration2;
        int months1 = dur1.months;
        int months2 = dur2.months;
        double sec1 = ((double) dur1.seconds) + (((double) dur1.nanos) * 1.0E-9d);
        double sec2 = ((double) dur2.seconds) + (((double) dur1.nanos) * 1.0E-9d);
        if (months2 == 0 && sec2 == 0.0d) {
            Throwable th3 = th2;
            new ArithmeticException("divide duration by zero");
            throw th3;
        }
        if (months2 == 0) {
            if (months1 == 0) {
                return sec1 / sec2;
            }
        } else if (sec2 == 0.0d && sec1 == 0.0d) {
            return ((double) months1) / ((double) months2);
        }
        Throwable th4 = th;
        new ArithmeticException("divide of incompatible durations");
        throw th4;
    }

    public Numeric div(Object obj) {
        Numeric numeric;
        Throwable th;
        Object y = obj;
        if (y instanceof RealNum) {
            double dy = ((RealNum) y).doubleValue();
            if (dy != 0.0d && !Double.isNaN(dy)) {
                return times(this, 1.0d / dy);
            }
            Throwable th2 = th;
            new ArithmeticException("divide of duration by 0 or NaN");
            throw th2;
        } else if (!(y instanceof Duration)) {
            return ((Numeric) y).divReversed(this);
        } else {
            new DFloNum(div(this, (Duration) y));
            return numeric;
        }
    }

    public static Duration add(Duration duration, Duration duration2, int i) {
        Duration duration3;
        Throwable th;
        Duration x = duration;
        Duration y = duration2;
        int k = i;
        long months2 = ((long) x.months) + (((long) k) * ((long) y.months));
        long nanos2 = (x.seconds * 1000000000) + ((long) x.nanos) + (((long) k) * ((y.seconds * 1000000000) + ((long) y.nanos)));
        new Duration();
        Duration d = duration3;
        d.months = (int) months2;
        d.seconds = (long) ((int) (nanos2 / 1000000000));
        d.nanos = (int) (nanos2 % 1000000000);
        if (x.unit != y.unit || x.unit == Unit.duration) {
            Throwable th2 = th;
            new ArithmeticException("cannot add these duration types");
            throw th2;
        }
        d.unit = x.unit;
        return d;
    }

    public static Duration times(Duration duration, double d) {
        Throwable th;
        Duration duration2;
        Throwable th2;
        Duration x = duration;
        double y = d;
        if (x.unit == Unit.duration) {
            Throwable th3 = th2;
            new IllegalArgumentException("cannot multiply general duration");
            throw th3;
        }
        double months2 = ((double) x.months) * y;
        if (Double.isInfinite(months2) || Double.isNaN(months2)) {
            Throwable th4 = th;
            new ArithmeticException("overflow/NaN when multiplying a duration");
            throw th4;
        }
        double nanos2 = ((double) ((x.seconds * 1000000000) + ((long) x.nanos))) * y;
        new Duration();
        Duration d2 = duration2;
        d2.months = (int) Math.floor(months2 + 0.5d);
        d2.seconds = (long) ((int) (nanos2 / 1.0E9d));
        d2.nanos = (int) (nanos2 % 1.0E9d);
        d2.unit = x.unit;
        return d2;
    }

    public static int compare(Duration duration, Duration duration2) {
        Duration x = duration;
        Duration y = duration2;
        long months2 = ((long) x.months) - ((long) y.months);
        long nanos2 = ((x.seconds * 1000000000) + ((long) x.nanos)) - ((y.seconds * 1000000000) + ((long) y.nanos));
        if (months2 < 0 && nanos2 <= 0) {
            return -1;
        }
        if (months2 > 0 && nanos2 >= 0) {
            return 1;
        }
        if (months2 != 0) {
            return -2;
        }
        return nanos2 < 0 ? -1 : nanos2 > 0 ? 1 : 0;
    }

    public int compare(Object obj) {
        Throwable th;
        Object obj2 = obj;
        if (obj2 instanceof Duration) {
            return compare(this, (Duration) obj2);
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        int m = this.months;
        long s = this.seconds;
        int n = this.nanos;
        if (m < 0 || s < 0 || n < 0) {
            m = -m;
            s = -s;
            n = -n;
            StringBuffer append = sbuf.append('-');
        }
        StringBuffer append2 = sbuf.append('P');
        int y = m / 12;
        if (y != 0) {
            StringBuffer append3 = sbuf.append(y);
            StringBuffer append4 = sbuf.append('Y');
            m -= y * 12;
        }
        if (m != 0) {
            StringBuffer append5 = sbuf.append(m);
            StringBuffer append6 = sbuf.append(Access.METHOD_CONTEXT);
        }
        long d = s / 86400;
        if (d != 0) {
            StringBuffer append7 = sbuf.append(d);
            StringBuffer append8 = sbuf.append('D');
            s -= 86400 * d;
        }
        if (s != 0 || n != 0) {
            StringBuffer append9 = sbuf.append('T');
            long hr = s / 3600;
            if (hr != 0) {
                StringBuffer append10 = sbuf.append(hr);
                StringBuffer append11 = sbuf.append('H');
                s -= 3600 * hr;
            }
            long mn = s / 60;
            if (mn != 0) {
                StringBuffer append12 = sbuf.append(mn);
                StringBuffer append13 = sbuf.append(Access.METHOD_CONTEXT);
                s -= 60 * mn;
            }
            if (!(s == 0 && n == 0)) {
                StringBuffer append14 = sbuf.append(s);
                appendNanoSeconds(n, sbuf);
                StringBuffer append15 = sbuf.append('S');
            }
        } else if (sbuf.length() == 1) {
            StringBuffer append16 = sbuf.append(this.unit == Unit.month ? "0M" : "T0S");
        }
        return sbuf.toString();
    }

    static void appendNanoSeconds(int i, StringBuffer stringBuffer) {
        int nanoSeconds = i;
        StringBuffer sbuf = stringBuffer;
        if (nanoSeconds != 0) {
            StringBuffer append = sbuf.append('.');
            int pos = sbuf.length();
            StringBuffer append2 = sbuf.append(nanoSeconds);
            int pad = (pos + 9) - sbuf.length();
            while (true) {
                pad--;
                if (pad < 0) {
                    break;
                }
                StringBuffer insert = sbuf.insert(pos, '0');
            }
            int len = pos + 9;
            do {
                len--;
            } while (sbuf.charAt(len) == '0');
            sbuf.setLength(len + 1);
        }
    }

    private static long scanPart(String str, int i) {
        String str2 = str;
        int start = i;
        int i2 = start;
        long val = -1;
        int len = str2.length();
        while (i2 < len) {
            char ch = str2.charAt(i2);
            i2++;
            int dig = Character.digit(ch, 10);
            if (dig >= 0) {
                val = val < 0 ? (long) dig : (10 * val) + ((long) dig);
                if (val > 2147483647L) {
                    return -1;
                }
            } else if (val < 0) {
                return (long) (start << 16);
            } else {
                return (val << 32) | ((long) (i2 << 16)) | ((long) ch);
            }
        }
        return val < 0 ? (long) (start << 16) : -1;
    }

    public int getYears() {
        return this.months / 12;
    }

    public int getMonths() {
        return this.months % 12;
    }

    public int getDays() {
        return (int) (this.seconds / 86400);
    }

    public int getHours() {
        return (int) ((this.seconds / 3600) % 24);
    }

    public int getMinutes() {
        return (int) ((this.seconds / 60) % 60);
    }

    public int getSecondsOnly() {
        return (int) (this.seconds % 60);
    }

    public int getNanoSecondsOnly() {
        return this.nanos;
    }

    public int getTotalMonths() {
        return this.months;
    }

    public long getTotalSeconds() {
        return this.seconds;
    }

    public long getTotalMinutes() {
        return this.seconds / 60;
    }

    public long getNanoSeconds() {
        return (this.seconds * 1000000000) + ((long) this.nanos);
    }

    public boolean isZero() {
        return this.months == 0 && this.seconds == 0 && this.nanos == 0;
    }

    public boolean isExact() {
        return false;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeInt(this.months);
        out.writeLong(this.seconds);
        out.writeInt(this.nanos);
        out.writeObject(this.unit);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.months = in.readInt();
        this.seconds = in.readLong();
        this.nanos = in.readInt();
        this.unit = (Unit) in.readObject();
    }

    public Unit unit() {
        return this.unit;
    }

    public Complex number() {
        Throwable th;
        Throwable th2 = th;
        new Error("number needs to be implemented!");
        throw th2;
    }

    public int hashCode() {
        return (this.months ^ ((int) this.seconds)) ^ this.nanos;
    }

    public static boolean equals(Duration duration, Duration duration2) {
        Duration x = duration;
        Duration y = duration2;
        return x.months == y.months && x.seconds == y.seconds && x.nanos == y.nanos;
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (obj2 == null || !(obj2 instanceof Duration)) {
            return false;
        }
        return equals(this, (Duration) obj2);
    }
}
