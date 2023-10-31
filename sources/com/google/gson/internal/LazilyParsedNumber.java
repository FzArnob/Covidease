package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.math.BigDecimal;

public final class LazilyParsedNumber extends Number {
    private final String value;

    public LazilyParsedNumber(String value2) {
        this.value = value2;
    }

    public int intValue() {
        BigDecimal bigDecimal;
        try {
            return Integer.parseInt(this.value);
        } catch (NumberFormatException e) {
            NumberFormatException numberFormatException = e;
            try {
                return (int) Long.parseLong(this.value);
            } catch (NumberFormatException e2) {
                NumberFormatException numberFormatException2 = e2;
                new BigDecimal(this.value);
                return bigDecimal.intValue();
            }
        }
    }

    public long longValue() {
        BigDecimal bigDecimal;
        try {
            return Long.parseLong(this.value);
        } catch (NumberFormatException e) {
            NumberFormatException numberFormatException = e;
            new BigDecimal(this.value);
            return bigDecimal.longValue();
        }
    }

    public float floatValue() {
        return Float.parseFloat(this.value);
    }

    public double doubleValue() {
        return Double.parseDouble(this.value);
    }

    public String toString() {
        return this.value;
    }

    private Object writeReplace() throws ObjectStreamException {
        Object obj;
        new BigDecimal(this.value);
        return obj;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (this == obj2) {
            return true;
        }
        if (!(obj2 instanceof LazilyParsedNumber)) {
            return false;
        }
        LazilyParsedNumber other = (LazilyParsedNumber) obj2;
        return this.value == other.value || this.value.equals(other.value);
    }
}
