package gnu.text;

import gnu.lists.Consumer;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class EnglishIntegerFormat extends NumberFormat {
    private static EnglishIntegerFormat cardinalEnglish;
    public static final String[] ones;
    public static final String[] onesth;
    private static EnglishIntegerFormat ordinalEnglish;
    public static final String[] power1000s;
    public static final String[] tens;
    public static final String[] tensth;
    public boolean ordinal;

    public EnglishIntegerFormat(boolean ordinal2) {
        this.ordinal = ordinal2;
    }

    public static EnglishIntegerFormat getInstance(boolean ordinal2) {
        EnglishIntegerFormat englishIntegerFormat;
        EnglishIntegerFormat englishIntegerFormat2;
        if (ordinal2) {
            if (ordinalEnglish == null) {
                new EnglishIntegerFormat(true);
                ordinalEnglish = englishIntegerFormat2;
            }
            return ordinalEnglish;
        }
        if (cardinalEnglish == null) {
            new EnglishIntegerFormat(false);
            cardinalEnglish = englishIntegerFormat;
        }
        return cardinalEnglish;
    }

    static {
        String[] strArr = new String[20];
        strArr[0] = null;
        String[] strArr2 = strArr;
        strArr2[1] = "one";
        String[] strArr3 = strArr2;
        strArr3[2] = "two";
        String[] strArr4 = strArr3;
        strArr4[3] = "three";
        String[] strArr5 = strArr4;
        strArr5[4] = "four";
        String[] strArr6 = strArr5;
        strArr6[5] = "five";
        String[] strArr7 = strArr6;
        strArr7[6] = "six";
        String[] strArr8 = strArr7;
        strArr8[7] = "seven";
        String[] strArr9 = strArr8;
        strArr9[8] = "eight";
        String[] strArr10 = strArr9;
        strArr10[9] = "nine";
        String[] strArr11 = strArr10;
        strArr11[10] = "ten";
        String[] strArr12 = strArr11;
        strArr12[11] = "eleven";
        String[] strArr13 = strArr12;
        strArr13[12] = "twelve";
        String[] strArr14 = strArr13;
        strArr14[13] = "thirteen";
        String[] strArr15 = strArr14;
        strArr15[14] = "fourteen";
        String[] strArr16 = strArr15;
        strArr16[15] = "fifteen";
        String[] strArr17 = strArr16;
        strArr17[16] = "sixteen";
        String[] strArr18 = strArr17;
        strArr18[17] = "seventeen";
        String[] strArr19 = strArr18;
        strArr19[18] = "eighteen";
        String[] strArr20 = strArr19;
        strArr20[19] = "nineteen";
        ones = strArr20;
        String[] strArr21 = new String[20];
        strArr21[0] = null;
        String[] strArr22 = strArr21;
        strArr22[1] = "first";
        String[] strArr23 = strArr22;
        strArr23[2] = "second";
        String[] strArr24 = strArr23;
        strArr24[3] = "third";
        String[] strArr25 = strArr24;
        strArr25[4] = "fourth";
        String[] strArr26 = strArr25;
        strArr26[5] = "fifth";
        String[] strArr27 = strArr26;
        strArr27[6] = "sixth";
        String[] strArr28 = strArr27;
        strArr28[7] = "seventh";
        String[] strArr29 = strArr28;
        strArr29[8] = "eighth";
        String[] strArr30 = strArr29;
        strArr30[9] = "ninth";
        String[] strArr31 = strArr30;
        strArr31[10] = "tenth";
        String[] strArr32 = strArr31;
        strArr32[11] = "eleventh";
        String[] strArr33 = strArr32;
        strArr33[12] = "twelveth";
        String[] strArr34 = strArr33;
        strArr34[13] = "thirteenth";
        String[] strArr35 = strArr34;
        strArr35[14] = "fourteenth";
        String[] strArr36 = strArr35;
        strArr36[15] = "fifteenth";
        String[] strArr37 = strArr36;
        strArr37[16] = "sixteenth";
        String[] strArr38 = strArr37;
        strArr38[17] = "seventeenth";
        String[] strArr39 = strArr38;
        strArr39[18] = "eighteenth";
        String[] strArr40 = strArr39;
        strArr40[19] = "nineteenth";
        onesth = strArr40;
        String[] strArr41 = new String[10];
        strArr41[0] = null;
        String[] strArr42 = strArr41;
        strArr42[1] = null;
        String[] strArr43 = strArr42;
        strArr43[2] = "twenty";
        String[] strArr44 = strArr43;
        strArr44[3] = "thirty";
        String[] strArr45 = strArr44;
        strArr45[4] = "forty";
        String[] strArr46 = strArr45;
        strArr46[5] = "fifty";
        String[] strArr47 = strArr46;
        strArr47[6] = "sixty";
        String[] strArr48 = strArr47;
        strArr48[7] = "seventy";
        String[] strArr49 = strArr48;
        strArr49[8] = "eighty";
        String[] strArr50 = strArr49;
        strArr50[9] = "ninety";
        tens = strArr50;
        String[] strArr51 = new String[10];
        strArr51[0] = null;
        String[] strArr52 = strArr51;
        strArr52[1] = null;
        String[] strArr53 = strArr52;
        strArr53[2] = "twentieth";
        String[] strArr54 = strArr53;
        strArr54[3] = "thirtieth";
        String[] strArr55 = strArr54;
        strArr55[4] = "fortieth";
        String[] strArr56 = strArr55;
        strArr56[5] = "fiftieth";
        String[] strArr57 = strArr56;
        strArr57[6] = "sixtieth";
        String[] strArr58 = strArr57;
        strArr58[7] = "seventieth";
        String[] strArr59 = strArr58;
        strArr59[8] = "eightieth";
        String[] strArr60 = strArr59;
        strArr60[9] = "ninetieth";
        tensth = strArr60;
        String[] strArr61 = new String[22];
        strArr61[0] = null;
        String[] strArr62 = strArr61;
        strArr62[1] = " thousand";
        String[] strArr63 = strArr62;
        strArr63[2] = " million";
        String[] strArr64 = strArr63;
        strArr64[3] = " billion";
        String[] strArr65 = strArr64;
        strArr65[4] = " trillion";
        String[] strArr66 = strArr65;
        strArr66[5] = " quadrillion";
        String[] strArr67 = strArr66;
        strArr67[6] = " quintillion";
        String[] strArr68 = strArr67;
        strArr68[7] = " sextillion";
        String[] strArr69 = strArr68;
        strArr69[8] = " septillion";
        String[] strArr70 = strArr69;
        strArr70[9] = " octillion";
        String[] strArr71 = strArr70;
        strArr71[10] = " nonillion";
        String[] strArr72 = strArr71;
        strArr72[11] = " decillion";
        String[] strArr73 = strArr72;
        strArr73[12] = " undecillion";
        String[] strArr74 = strArr73;
        strArr74[13] = " duodecillion";
        String[] strArr75 = strArr74;
        strArr75[14] = " tredecillion";
        String[] strArr76 = strArr75;
        strArr76[15] = " quattuordecillion";
        String[] strArr77 = strArr76;
        strArr77[16] = " quindecillion";
        String[] strArr78 = strArr77;
        strArr78[17] = " sexdecillion";
        String[] strArr79 = strArr78;
        strArr79[18] = " septendecillion";
        String[] strArr80 = strArr79;
        strArr80[19] = " octodecillion";
        String[] strArr81 = strArr80;
        strArr81[20] = " novemdecillion";
        String[] strArr82 = strArr81;
        strArr82[21] = " vigintillion";
        power1000s = strArr82;
    }

    /* access modifiers changed from: package-private */
    public void format999(StringBuffer stringBuffer, int i, boolean z) {
        StringBuffer sbuf = stringBuffer;
        int num = i;
        boolean ordinal2 = z;
        if (num >= 100) {
            int num100s = num / 100;
            num %= 100;
            if (num100s > 1) {
                StringBuffer append = sbuf.append(ones[num100s]);
                StringBuffer append2 = sbuf.append(' ');
            }
            StringBuffer append3 = sbuf.append("hundred");
            if (num > 0) {
                StringBuffer append4 = sbuf.append(' ');
            } else if (ordinal2) {
                StringBuffer append5 = sbuf.append("th");
            }
        }
        if (num >= 20) {
            int num10s = num / 10;
            num %= 10;
            StringBuffer append6 = sbuf.append(((!ordinal2 || num != 0) ? tens : tensth)[num10s]);
            if (num > 0) {
                StringBuffer append7 = sbuf.append('-');
            }
        }
        if (num > 0) {
            StringBuffer append8 = sbuf.append((ordinal2 ? onesth : ones)[num]);
        }
    }

    /* access modifiers changed from: package-private */
    public void format(StringBuffer stringBuffer, long j, int i, boolean z) {
        StringBuffer sbuf = stringBuffer;
        long num = j;
        int exp1000 = i;
        boolean ordinal2 = z;
        if (num >= 1000) {
            format(sbuf, num / 1000, exp1000 + 1, false);
            num %= 1000;
            if (num > 0) {
                StringBuffer append = sbuf.append(", ");
            } else if (ordinal2) {
                StringBuffer append2 = sbuf.append("th");
            }
        }
        if (num > 0) {
            format999(sbuf, (int) num, ordinal2 && exp1000 == 0);
            if (exp1000 >= power1000s.length) {
                StringBuffer append3 = sbuf.append(" times ten to the ");
                format(sbuf, (long) (exp1000 * 3), 0, true);
                StringBuffer append4 = sbuf.append(" power");
            } else if (exp1000 > 0) {
                StringBuffer append5 = sbuf.append(power1000s[exp1000]);
            }
        }
    }

    public void writeInt(int value, Consumer out) {
        writeLong((long) value, out);
    }

    public void writeLong(long value, Consumer out) {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        StringBuffer format = format(value, sbuf, (FieldPosition) null);
        out.write((CharSequence) sbuf, 0, sbuf.length());
    }

    public void writeObject(Object value, Consumer out) {
        writeLong(((Number) value).longValue(), out);
    }

    public void writeBoolean(boolean value, Consumer out) {
        writeLong(value ? 1 : 0, out);
    }

    public StringBuffer format(long j, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        long num = j;
        StringBuffer sbuf = stringBuffer;
        FieldPosition fpos = fieldPosition;
        if (num == 0) {
            StringBuffer append = sbuf.append(this.ordinal ? "zeroth" : "zero");
        } else {
            if (num < 0) {
                StringBuffer append2 = sbuf.append("minus ");
                num = -num;
            }
            format(sbuf, num, 0, this.ordinal);
        }
        if (fpos != null) {
        }
        return sbuf;
    }

    public StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        double num = d;
        StringBuffer sbuf = stringBuffer;
        FieldPosition fpos = fieldPosition;
        long inum = (long) num;
        if (((double) inum) == num) {
            return format(inum, sbuf, fpos);
        }
        StringBuffer append = sbuf.append(Double.toString(num));
        return sbuf;
    }

    public Number parse(String str, ParsePosition parsePosition) {
        Throwable th;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        Throwable th2 = th;
        new Error("EnglishIntegerFormat.parseObject - not implemented");
        throw th2;
    }
}
