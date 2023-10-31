package gnu.kawa.functions;

import gnu.mapping.Procedure1;
import gnu.text.LineBufferedReader;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.text.ParseException;

public class ParseFormat extends Procedure1 {
    public static final int PARAM_FROM_LIST = -1610612736;
    public static final int PARAM_UNSPECIFIED = -1073741824;
    public static final int SEEN_HASH = 16;
    public static final int SEEN_MINUS = 1;
    public static final int SEEN_PLUS = 2;
    public static final int SEEN_SPACE = 4;
    public static final int SEEN_ZERO = 8;
    public static final ParseFormat parseFormat;
    boolean emacsStyle = true;

    static {
        ParseFormat parseFormat2;
        new ParseFormat(false);
        parseFormat = parseFormat2;
    }

    public ParseFormat(boolean emacsStyle2) {
        this.emacsStyle = emacsStyle2;
    }

    public ReportFormat parseFormat(LineBufferedReader fmt) throws ParseException, IOException {
        return parseFormat(fmt, this.emacsStyle ? '?' : '~');
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: gnu.kawa.functions.ObjectFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: gnu.kawa.functions.ObjectFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v11, resolved type: gnu.kawa.functions.ObjectFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: gnu.kawa.functions.ObjectFormat} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.text.ReportFormat parseFormat(gnu.text.LineBufferedReader r26, char r27) throws java.text.ParseException, java.io.IOException {
        /*
            r2 = r26
            r3 = r27
            java.lang.StringBuffer r19 = new java.lang.StringBuffer
            r25 = r19
            r19 = r25
            r20 = r25
            r21 = 100
            r20.<init>(r21)
            r4 = r19
            r19 = 0
            r5 = r19
            java.util.Vector r19 = new java.util.Vector
            r25 = r19
            r19 = r25
            r20 = r25
            r20.<init>()
            r6 = r19
        L_0x0024:
            r19 = r2
            int r19 = r19.read()
            r8 = r19
            r19 = r8
            if (r19 < 0) goto L_0x0068
            r19 = r8
            r20 = r3
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0048
            r19 = r4
            r20 = r8
            r0 = r20
            char r0 = (char) r0
            r20 = r0
            java.lang.StringBuffer r19 = r19.append(r20)
            goto L_0x0024
        L_0x0048:
            r19 = r2
            int r19 = r19.read()
            r8 = r19
            r19 = r8
            r20 = r3
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0068
            r19 = r4
            r20 = r8
            r0 = r20
            char r0 = (char) r0
            r20 = r0
            java.lang.StringBuffer r19 = r19.append(r20)
            goto L_0x0024
        L_0x0068:
            r19 = r4
            int r19 = r19.length()
            r9 = r19
            r19 = r9
            if (r19 <= 0) goto L_0x00a4
            r19 = r9
            r0 = r19
            char[] r0 = new char[r0]
            r19 = r0
            r10 = r19
            r19 = r4
            r20 = 0
            r21 = r9
            r22 = r10
            r23 = 0
            r19.getChars(r20, r21, r22, r23)
            r19 = r4
            r20 = 0
            r19.setLength(r20)
            r19 = r6
            gnu.text.LiteralFormat r20 = new gnu.text.LiteralFormat
            r25 = r20
            r20 = r25
            r21 = r25
            r22 = r10
            r21.<init>((char[]) r22)
            r19.addElement(r20)
        L_0x00a4:
            r19 = r8
            if (r19 >= 0) goto L_0x00d5
            r19 = r6
            int r19 = r19.size()
            r8 = r19
            r19 = r8
            r20 = 1
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x03b1
            r19 = r6
            r20 = 0
            java.lang.Object r19 = r19.elementAt(r20)
            r9 = r19
            r19 = r9
            r0 = r19
            boolean r0 = r0 instanceof gnu.text.ReportFormat
            r19 = r0
            if (r19 == 0) goto L_0x03b1
            r19 = r9
            gnu.text.ReportFormat r19 = (gnu.text.ReportFormat) r19
            r2 = r19
        L_0x00d4:
            return r2
        L_0x00d5:
            r19 = r8
            r20 = 36
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0134
            r19 = r2
            int r19 = r19.read()
            r8 = r19
            r19 = r8
            r0 = r19
            char r0 = (char) r0
            r19 = r0
            r20 = 10
            int r19 = java.lang.Character.digit(r19, r20)
            r5 = r19
            r19 = r5
            if (r19 >= 0) goto L_0x0117
            java.text.ParseException r19 = new java.text.ParseException
            r25 = r19
            r19 = r25
            r20 = r25
            java.lang.String r21 = "missing number (position) after '%$'"
            r22 = -1
            r20.<init>(r21, r22)
            throw r19
        L_0x010b:
            r19 = 10
            r20 = r5
            int r19 = r19 * r20
            r20 = r10
            int r19 = r19 + r20
            r5 = r19
        L_0x0117:
            r19 = r2
            int r19 = r19.read()
            r8 = r19
            r19 = r8
            r0 = r19
            char r0 = (char) r0
            r19 = r0
            r20 = 10
            int r19 = java.lang.Character.digit(r19, r20)
            r10 = r19
            r19 = r10
            if (r19 >= 0) goto L_0x010b
            int r5 = r5 + -1
        L_0x0134:
            r19 = 0
            r11 = r19
        L_0x0138:
            r19 = r8
            r0 = r19
            char r0 = (char) r0
            r19 = r0
            switch(r19) {
                case 32: goto L_0x01e5;
                case 35: goto L_0x01f7;
                case 43: goto L_0x01dc;
                case 45: goto L_0x01ca;
                case 48: goto L_0x01ee;
                default: goto L_0x0142;
            }
        L_0x0142:
            r19 = -1073741824(0xffffffffc0000000, float:-2.0)
            r12 = r19
            r19 = r8
            r0 = r19
            char r0 = (char) r0
            r19 = r0
            r20 = 10
            int r19 = java.lang.Character.digit(r19, r20)
            r10 = r19
            r19 = r10
            if (r19 < 0) goto L_0x020e
            r19 = r10
            r12 = r19
        L_0x015d:
            r19 = r2
            int r19 = r19.read()
            r8 = r19
            r19 = r8
            r0 = r19
            char r0 = (char) r0
            r19 = r0
            r20 = 10
            int r19 = java.lang.Character.digit(r19, r20)
            r10 = r19
            r19 = r10
            if (r19 >= 0) goto L_0x0200
        L_0x0178:
            r19 = -1073741824(0xffffffffc0000000, float:-2.0)
            r13 = r19
            r19 = r8
            r20 = 46
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0194
            r19 = r8
            r20 = 42
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x021e
            r19 = -1610612736(0xffffffffa0000000, float:-1.0842022E-19)
            r13 = r19
        L_0x0194:
            r19 = r8
            switch(r19) {
                case 83: goto L_0x024c;
                case 88: goto L_0x02a6;
                case 100: goto L_0x02a6;
                case 101: goto L_0x0386;
                case 102: goto L_0x0386;
                case 103: goto L_0x0386;
                case 105: goto L_0x02a6;
                case 111: goto L_0x02a6;
                case 115: goto L_0x024c;
                case 120: goto L_0x02a6;
                default: goto L_0x0199;
            }
        L_0x0199:
            java.text.ParseException r19 = new java.text.ParseException
            r25 = r19
            r19 = r25
            r20 = r25
            java.lang.StringBuilder r21 = new java.lang.StringBuilder
            r25 = r21
            r21 = r25
            r22 = r25
            r22.<init>()
            java.lang.String r22 = "unknown format character '"
            java.lang.StringBuilder r21 = r21.append(r22)
            r22 = r8
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r22 = "'"
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r21 = r21.toString()
            r22 = -1
            r20.<init>(r21, r22)
            throw r19
        L_0x01ca:
            r19 = r11
            r20 = 1
            r19 = r19 | 1
            r11 = r19
        L_0x01d2:
            r19 = r2
            int r19 = r19.read()
            r8 = r19
            goto L_0x0138
        L_0x01dc:
            r19 = r11
            r20 = 2
            r19 = r19 | 2
            r11 = r19
            goto L_0x01d2
        L_0x01e5:
            r19 = r11
            r20 = 4
            r19 = r19 | 4
            r11 = r19
            goto L_0x01d2
        L_0x01ee:
            r19 = r11
            r20 = 8
            r19 = r19 | 8
            r11 = r19
            goto L_0x01d2
        L_0x01f7:
            r19 = r11
            r20 = 16
            r19 = r19 | 16
            r11 = r19
            goto L_0x01d2
        L_0x0200:
            r19 = 10
            r20 = r12
            int r19 = r19 * r20
            r20 = r10
            int r19 = r19 + r20
            r12 = r19
            goto L_0x015d
        L_0x020e:
            r19 = r8
            r20 = 42
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0178
            r19 = -1610612736(0xffffffffa0000000, float:-1.0842022E-19)
            r12 = r19
            goto L_0x0178
        L_0x021e:
            r19 = 0
            r13 = r19
        L_0x0222:
            r19 = r2
            int r19 = r19.read()
            r8 = r19
            r19 = r8
            r0 = r19
            char r0 = (char) r0
            r19 = r0
            r20 = 10
            int r19 = java.lang.Character.digit(r19, r20)
            r10 = r19
            r19 = r10
            if (r19 >= 0) goto L_0x023f
            goto L_0x0194
        L_0x023f:
            r19 = 10
            r20 = r13
            int r19 = r19 * r20
            r20 = r10
            int r19 = r19 + r20
            r13 = r19
            goto L_0x0222
        L_0x024c:
            gnu.kawa.functions.ObjectFormat r19 = new gnu.kawa.functions.ObjectFormat
            r25 = r19
            r19 = r25
            r20 = r25
            r21 = r8
            r22 = 83
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x02a3
            r21 = 1
        L_0x0260:
            r22 = r13
            r20.<init>(r21, r22)
            r7 = r19
        L_0x0267:
            r19 = r12
            if (r19 <= 0) goto L_0x0298
            r19 = r11
            r20 = 8
            r19 = r19 & 8
            if (r19 == 0) goto L_0x0397
            r19 = 48
        L_0x0275:
            r14 = r19
            r19 = r11
            r20 = 1
            r19 = r19 & 1
            if (r19 == 0) goto L_0x039b
            r19 = 100
            r15 = r19
        L_0x0283:
            gnu.text.PadFormat r19 = new gnu.text.PadFormat
            r25 = r19
            r19 = r25
            r20 = r25
            r21 = r7
            r22 = r12
            r23 = r14
            r24 = r15
            r20.<init>(r21, r22, r23, r24)
            r7 = r19
        L_0x0298:
            r19 = r6
            r20 = r7
            r19.addElement(r20)
            int r5 = r5 + 1
            goto L_0x0024
        L_0x02a3:
            r21 = 0
            goto L_0x0260
        L_0x02a6:
            r19 = 0
            r15 = r19
            r19 = r8
            r20 = 100
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x02be
            r19 = r8
            r20 = 105(0x69, float:1.47E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x034a
        L_0x02be:
            r19 = 10
            r14 = r19
        L_0x02c2:
            r19 = 0
            r16 = r19
            r19 = 0
            r17 = r19
            r19 = r11
            r20 = 9
            r19 = r19 & 9
            r20 = 8
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x036e
            r19 = 48
        L_0x02da:
            r18 = r19
            r19 = r11
            r20 = 16
            r19 = r19 & 16
            if (r19 == 0) goto L_0x02ec
            r19 = r15
            r20 = 8
            r19 = r19 | 8
            r15 = r19
        L_0x02ec:
            r19 = r11
            r20 = 2
            r19 = r19 & 2
            if (r19 == 0) goto L_0x02fc
            r19 = r15
            r20 = 2
            r19 = r19 | 2
            r15 = r19
        L_0x02fc:
            r19 = r11
            r20 = 1
            r19 = r19 & 1
            if (r19 == 0) goto L_0x030c
            r19 = r15
            r20 = 16
            r19 = r19 | 16
            r15 = r19
        L_0x030c:
            r19 = r11
            r20 = 4
            r19 = r19 & 4
            if (r19 == 0) goto L_0x031c
            r19 = r15
            r20 = 4
            r19 = r19 | 4
            r15 = r19
        L_0x031c:
            r19 = r13
            r20 = -1073741824(0xffffffffc0000000, float:-2.0)
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0372
            r19 = r11
            r20 = -9
            r19 = r19 & -9
            r11 = r19
            r19 = r15
            r20 = 64
            r19 = r19 | 64
            r15 = r19
            r19 = r14
            r20 = r13
            r21 = 48
            r22 = -1073741824(0xffffffffc0000000, float:-2.0)
            r23 = -1073741824(0xffffffffc0000000, float:-2.0)
            r24 = r15
            java.text.Format r19 = gnu.kawa.functions.IntegerFormat.getInstance(r19, r20, r21, r22, r23, r24)
            r7 = r19
            goto L_0x0267
        L_0x034a:
            r19 = r8
            r20 = 111(0x6f, float:1.56E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x035a
            r19 = 8
            r14 = r19
            goto L_0x02c2
        L_0x035a:
            r19 = 16
            r14 = r19
            r19 = r8
            r20 = 88
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x02c2
            r19 = 32
            r15 = r19
            goto L_0x02c2
        L_0x036e:
            r19 = 32
            goto L_0x02da
        L_0x0372:
            r19 = r14
            r20 = r12
            r21 = r18
            r22 = -1073741824(0xffffffffc0000000, float:-2.0)
            r23 = -1073741824(0xffffffffc0000000, float:-2.0)
            r24 = r15
            java.text.Format r19 = gnu.kawa.functions.IntegerFormat.getInstance(r19, r20, r21, r22, r23, r24)
            r7 = r19
            goto L_0x0267
        L_0x0386:
            gnu.kawa.functions.ObjectFormat r19 = new gnu.kawa.functions.ObjectFormat
            r25 = r19
            r19 = r25
            r20 = r25
            r21 = 0
            r20.<init>(r21)
            r7 = r19
            goto L_0x0267
        L_0x0397:
            r19 = 32
            goto L_0x0275
        L_0x039b:
            r19 = r14
            r20 = 48
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x03ab
            r19 = -1
            r15 = r19
            goto L_0x0283
        L_0x03ab:
            r19 = 0
            r15 = r19
            goto L_0x0283
        L_0x03b1:
            r19 = r8
            r0 = r19
            java.text.Format[] r0 = new java.text.Format[r0]
            r19 = r0
            r9 = r19
            r19 = r6
            r20 = r9
            r19.copyInto(r20)
            gnu.text.CompoundFormat r19 = new gnu.text.CompoundFormat
            r25 = r19
            r19 = r25
            r20 = r25
            r21 = r9
            r20.<init>(r21)
            r2 = r19
            goto L_0x00d4
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.ParseFormat.parseFormat(gnu.text.LineBufferedReader, char):gnu.text.ReportFormat");
    }

    public Object apply1(Object arg) {
        return asFormat(arg, this.emacsStyle ? '?' : '~');
    }

    /* JADX WARNING: type inference failed for: r5v18, types: [gnu.mapping.InPort] */
    /* JADX WARNING: type inference failed for: r5v21, types: [gnu.mapping.InPort] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.text.ReportFormat asFormat(java.lang.Object r10, char r11) {
        /*
            r0 = r10
            r1 = r11
            r5 = r0
            boolean r5 = r5 instanceof gnu.text.ReportFormat     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            if (r5 == 0) goto L_0x000c
            r5 = r0
            gnu.text.ReportFormat r5 = (gnu.text.ReportFormat) r5     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            r0 = r5
        L_0x000b:
            return r0
        L_0x000c:
            r5 = r1
            r6 = 126(0x7e, float:1.77E-43)
            if (r5 != r6) goto L_0x0020
            gnu.kawa.functions.LispFormat r5 = new gnu.kawa.functions.LispFormat     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = r0
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            r6.<init>((java.lang.String) r7)     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            r0 = r5
            goto L_0x000b
        L_0x0020:
            r5 = r0
            boolean r5 = r5 instanceof gnu.lists.FString     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            if (r5 == 0) goto L_0x0046
            r5 = r0
            gnu.lists.FString r5 = (gnu.lists.FString) r5     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            r3 = r5
            gnu.mapping.CharArrayInPort r5 = new gnu.mapping.CharArrayInPort     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = r3
            char[] r7 = r7.data     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            r8 = r3
            int r8 = r8.size     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            r6.<init>(r7, r8)     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            r2 = r5
        L_0x0038:
            r5 = r2
            r6 = r1
            gnu.text.ReportFormat r5 = parseFormat(r5, r6)     // Catch:{ all -> 0x0055 }
            r3 = r5
            r5 = r2
            r5.close()     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            r5 = r3
            r0 = r5
            goto L_0x000b
        L_0x0046:
            gnu.mapping.CharArrayInPort r5 = new gnu.mapping.CharArrayInPort     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = r0
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            r6.<init>((java.lang.String) r7)     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            r2 = r5
            goto L_0x0038
        L_0x0055:
            r5 = move-exception
            r4 = r5
            r5 = r2
            r5.close()     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
            r5 = r4
            throw r5     // Catch:{ IOException -> 0x005d, ParseException -> 0x0087, IndexOutOfBoundsException -> 0x00b1 }
        L_0x005d:
            r5 = move-exception
            r2 = r5
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            r9 = r5
            r5 = r9
            r6 = r9
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r9 = r7
            r7 = r9
            r8 = r9
            r8.<init>()
            java.lang.String r8 = "Error parsing format ("
            java.lang.StringBuilder r7 = r7.append(r8)
            r8 = r2
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = ")"
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r5
        L_0x0087:
            r5 = move-exception
            r2 = r5
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            r9 = r5
            r5 = r9
            r6 = r9
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r9 = r7
            r7 = r9
            r8 = r9
            r8.<init>()
            java.lang.String r8 = "Invalid format ("
            java.lang.StringBuilder r7 = r7.append(r8)
            r8 = r2
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = ")"
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r5
        L_0x00b1:
            r5 = move-exception
            r2 = r5
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            r9 = r5
            r5 = r9
            r6 = r9
            java.lang.String r7 = "End while parsing format"
            r6.<init>(r7)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.ParseFormat.asFormat(java.lang.Object, char):gnu.text.ReportFormat");
    }
}
