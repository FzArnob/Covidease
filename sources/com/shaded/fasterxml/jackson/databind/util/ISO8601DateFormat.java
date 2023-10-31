package com.shaded.fasterxml.jackson.databind.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ISO8601DateFormat extends DateFormat {
    private static Calendar CALENDAR = null;
    private static NumberFormat NUMBER_FORMAT = null;
    private static final long serialVersionUID = 1;

    static {
        Calendar calendar;
        NumberFormat numberFormat;
        new GregorianCalendar();
        CALENDAR = calendar;
        new DecimalFormat();
        NUMBER_FORMAT = numberFormat;
    }

    public ISO8601DateFormat() {
        this.numberFormat = NUMBER_FORMAT;
        this.calendar = CALENDAR;
    }

    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        StringBuffer stringBuffer2 = stringBuffer;
        FieldPosition fieldPosition2 = fieldPosition;
        StringBuffer append = stringBuffer2.append(ISO8601Utils.format(date));
        return stringBuffer2;
    }

    public Date parse(String str, ParsePosition parsePosition) {
        String str2 = str;
        parsePosition.setIndex(str2.length());
        return ISO8601Utils.parse(str2);
    }

    public Object clone() {
        return this;
    }
}
