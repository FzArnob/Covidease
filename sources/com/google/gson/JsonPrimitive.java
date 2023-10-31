package com.google.gson;

import com.google.gson.internal.C$Gson$Preconditions;
import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class JsonPrimitive extends JsonElement {
    private static final Class<?>[] PRIMITIVE_TYPES;
    private Object value;

    static {
        Class<?>[] clsArr = new Class[16];
        clsArr[0] = Integer.TYPE;
        Class<?>[] clsArr2 = clsArr;
        clsArr2[1] = Long.TYPE;
        Class<?>[] clsArr3 = clsArr2;
        clsArr3[2] = Short.TYPE;
        Class<?>[] clsArr4 = clsArr3;
        clsArr4[3] = Float.TYPE;
        Class<?>[] clsArr5 = clsArr4;
        clsArr5[4] = Double.TYPE;
        Class<?>[] clsArr6 = clsArr5;
        clsArr6[5] = Byte.TYPE;
        Class<?>[] clsArr7 = clsArr6;
        clsArr7[6] = Boolean.TYPE;
        Class<?>[] clsArr8 = clsArr7;
        clsArr8[7] = Character.TYPE;
        Class<?>[] clsArr9 = clsArr8;
        clsArr9[8] = Integer.class;
        Class<?>[] clsArr10 = clsArr9;
        clsArr10[9] = Long.class;
        Class<?>[] clsArr11 = clsArr10;
        clsArr11[10] = Short.class;
        Class<?>[] clsArr12 = clsArr11;
        clsArr12[11] = Float.class;
        Class<?>[] clsArr13 = clsArr12;
        clsArr13[12] = Double.class;
        Class<?>[] clsArr14 = clsArr13;
        clsArr14[13] = Byte.class;
        Class<?>[] clsArr15 = clsArr14;
        clsArr15[14] = Boolean.class;
        Class<?>[] clsArr16 = clsArr15;
        clsArr16[15] = Character.class;
        PRIMITIVE_TYPES = clsArr16;
    }

    public JsonPrimitive(Boolean bool) {
        setValue(bool);
    }

    public JsonPrimitive(Number number) {
        setValue(number);
    }

    public JsonPrimitive(String string) {
        setValue(string);
    }

    public JsonPrimitive(Character c) {
        setValue(c);
    }

    JsonPrimitive(Object primitive) {
        setValue(primitive);
    }

    public JsonPrimitive deepCopy() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public void setValue(Object obj) {
        Object primitive = obj;
        if (primitive instanceof Character) {
            this.value = String.valueOf(((Character) primitive).charValue());
            return;
        }
        C$Gson$Preconditions.checkArgument((primitive instanceof Number) || isPrimitiveOrString(primitive));
        this.value = primitive;
    }

    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    /* access modifiers changed from: package-private */
    public Boolean getAsBooleanWrapper() {
        return (Boolean) this.value;
    }

    public boolean getAsBoolean() {
        if (isBoolean()) {
            return getAsBooleanWrapper().booleanValue();
        }
        return Boolean.parseBoolean(getAsString());
    }

    public boolean isNumber() {
        return this.value instanceof Number;
    }

    public Number getAsNumber() {
        Number number;
        Number number2;
        if (this.value instanceof String) {
            number = number2;
            new LazilyParsedNumber((String) this.value);
        } else {
            number = (Number) this.value;
        }
        return number;
    }

    public boolean isString() {
        return this.value instanceof String;
    }

    public String getAsString() {
        if (isNumber()) {
            return getAsNumber().toString();
        }
        if (isBoolean()) {
            return getAsBooleanWrapper().toString();
        }
        return (String) this.value;
    }

    public double getAsDouble() {
        return isNumber() ? getAsNumber().doubleValue() : Double.parseDouble(getAsString());
    }

    public BigDecimal getAsBigDecimal() {
        BigDecimal bigDecimal;
        BigDecimal bigDecimal2;
        if (this.value instanceof BigDecimal) {
            bigDecimal2 = (BigDecimal) this.value;
        } else {
            bigDecimal2 = bigDecimal;
            new BigDecimal(this.value.toString());
        }
        return bigDecimal2;
    }

    public BigInteger getAsBigInteger() {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        if (this.value instanceof BigInteger) {
            bigInteger2 = (BigInteger) this.value;
        } else {
            bigInteger2 = bigInteger;
            new BigInteger(this.value.toString());
        }
        return bigInteger2;
    }

    public float getAsFloat() {
        return isNumber() ? getAsNumber().floatValue() : Float.parseFloat(getAsString());
    }

    public long getAsLong() {
        return isNumber() ? getAsNumber().longValue() : Long.parseLong(getAsString());
    }

    public short getAsShort() {
        return isNumber() ? getAsNumber().shortValue() : Short.parseShort(getAsString());
    }

    public int getAsInt() {
        return isNumber() ? getAsNumber().intValue() : Integer.parseInt(getAsString());
    }

    public byte getAsByte() {
        return isNumber() ? getAsNumber().byteValue() : Byte.parseByte(getAsString());
    }

    public char getAsCharacter() {
        return getAsString().charAt(0);
    }

    private static boolean isPrimitiveOrString(Object obj) {
        Object target = obj;
        if (target instanceof String) {
            return true;
        }
        Class<?> classOfPrimitive = target.getClass();
        Class<?>[] clsArr = PRIMITIVE_TYPES;
        int length = clsArr.length;
        for (int i = 0; i < length; i++) {
            if (clsArr[i].isAssignableFrom(classOfPrimitive)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.value == null) {
            return 31;
        }
        if (isIntegral(this)) {
            long value2 = getAsNumber().longValue();
            return (int) (value2 ^ (value2 >>> 32));
        } else if (!(this.value instanceof Number)) {
            return this.value.hashCode();
        } else {
            long value3 = Double.doubleToLongBits(getAsNumber().doubleValue());
            return (int) (value3 ^ (value3 >>> 32));
        }
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (this == obj2) {
            return true;
        }
        if (obj2 == null || getClass() != obj2.getClass()) {
            return false;
        }
        JsonPrimitive other = (JsonPrimitive) obj2;
        if (this.value == null) {
            return other.value == null;
        } else if (isIntegral(this) && isIntegral(other)) {
            return getAsNumber().longValue() == other.getAsNumber().longValue();
        } else if (!(this.value instanceof Number) || !(other.value instanceof Number)) {
            return this.value.equals(other.value);
        } else {
            double a = getAsNumber().doubleValue();
            double b = other.getAsNumber().doubleValue();
            return a == b || (Double.isNaN(a) && Double.isNaN(b));
        }
    }

    private static boolean isIntegral(JsonPrimitive jsonPrimitive) {
        JsonPrimitive primitive = jsonPrimitive;
        if (!(primitive.value instanceof Number)) {
            return false;
        }
        Number number = (Number) primitive.value;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }
}
