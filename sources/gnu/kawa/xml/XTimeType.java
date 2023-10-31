package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.math.DateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class XTimeType extends XDataType {
    public static final XTimeType dateTimeType;
    public static final XTimeType dateType;
    private static TimeZone fixedTimeZone;
    public static final XTimeType gDayType;
    public static final XTimeType gMonthDayType;
    public static final XTimeType gMonthType;
    public static final XTimeType gYearMonthType;
    public static final XTimeType gYearType;
    public static final XTimeType timeType;
    static ClassType typeDateTime = ClassType.make("gnu.math.DateTime");

    static {
        XTimeType xTimeType;
        XTimeType xTimeType2;
        XTimeType xTimeType3;
        XTimeType xTimeType4;
        XTimeType xTimeType5;
        XTimeType xTimeType6;
        XTimeType xTimeType7;
        XTimeType xTimeType8;
        new XTimeType("dateTime", 20);
        dateTimeType = xTimeType;
        new XTimeType("date", 21);
        dateType = xTimeType2;
        new XTimeType("time", 22);
        timeType = xTimeType3;
        new XTimeType("gYearMonth", 23);
        gYearMonthType = xTimeType4;
        new XTimeType("gYear", 24);
        gYearType = xTimeType5;
        new XTimeType("gMonth", 27);
        gMonthType = xTimeType6;
        new XTimeType("gMonthDay", 25);
        gMonthDayType = xTimeType7;
        new XTimeType("gDay", 26);
        gDayType = xTimeType8;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    XTimeType(String name, int code) {
        super(name, typeDateTime, code);
    }

    static int components(int typeCode) {
        switch (typeCode) {
            case 20:
                return 126;
            case 21:
                return 14;
            case 22:
                return 112;
            case 23:
                return 6;
            case 24:
                return 2;
            case 25:
                return 12;
            case 26:
                return 8;
            case 27:
                return 4;
            case 28:
                return 126;
            case 29:
                return 6;
            case 30:
                return 120;
            default:
                return 0;
        }
    }

    public DateTime now() {
        DateTime dateTime;
        new DateTime(components(this.typeCode) | 128, (GregorianCalendar) Calendar.getInstance(fixedTimeZone()));
        return dateTime;
    }

    private static synchronized TimeZone fixedTimeZone() {
        TimeZone timeZone;
        synchronized (XTimeType.class) {
            if (fixedTimeZone == null) {
                fixedTimeZone = DateTime.minutesToTimeZone(TimeZone.getDefault().getRawOffset() / 60000);
            }
            timeZone = fixedTimeZone;
        }
        return timeZone;
    }

    public static DateTime parseDateTime(String value, int mask) {
        DateTime time = DateTime.parse(value, mask);
        if (time.isZoneUnspecified()) {
            time.setTimeZone(fixedTimeZone());
        }
        return time;
    }

    public Object valueOf(String value) {
        return parseDateTime(value, components(this.typeCode));
    }

    public boolean isInstance(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof DateTime)) {
            return false;
        }
        return components(this.typeCode) == ((DateTime) obj2).components();
    }
}
