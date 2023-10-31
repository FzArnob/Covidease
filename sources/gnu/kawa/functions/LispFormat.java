package gnu.kawa.functions;

import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.text.CompoundFormat;
import gnu.text.ReportFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Vector;

public class LispFormat extends CompoundFormat {
    public static final String paramFromCount = "<from count>";
    public static final String paramFromList = "<from list>";
    public static final String paramUnspecified = "<unspecified>";

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v320, resolved type: java.lang.Cloneable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v50, resolved type: gnu.kawa.functions.LispIterationFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v321, resolved type: java.lang.Cloneable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v51, resolved type: gnu.kawa.functions.LispIterationFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v322, resolved type: java.lang.Cloneable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v52, resolved type: gnu.kawa.functions.LispIterationFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v17, resolved type: gnu.kawa.functions.LispObjectFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v0, resolved type: java.lang.Cloneable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v381, resolved type: java.lang.Cloneable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v54, resolved type: gnu.kawa.functions.LispRealFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v382, resolved type: java.lang.Cloneable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v55, resolved type: gnu.kawa.functions.LispRealFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v383, resolved type: java.lang.Cloneable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v56, resolved type: gnu.kawa.functions.LispRealFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v384, resolved type: java.lang.Cloneable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v57, resolved type: gnu.kawa.functions.LispRealFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v385, resolved type: java.lang.Cloneable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v58, resolved type: gnu.kawa.functions.LispRealFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v387, resolved type: java.lang.Cloneable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v60, resolved type: gnu.kawa.functions.LispRealFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v388, resolved type: java.lang.Cloneable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v61, resolved type: gnu.kawa.functions.LispRealFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v389, resolved type: java.lang.Cloneable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v104, resolved type: gnu.kawa.functions.LispRealFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v392, resolved type: gnu.kawa.functions.LispRealFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v394, resolved type: java.lang.Cloneable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v62, resolved type: gnu.kawa.functions.LispRealFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v396, resolved type: java.lang.Cloneable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v64, resolved type: gnu.kawa.functions.LispRealFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v397, resolved type: java.lang.Cloneable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v65, resolved type: gnu.kawa.functions.LispRealFormat} */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x054a, code lost:
        throw r36;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0709, code lost:
        throw r36;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x098b, code lost:
        r36 = r44;
        new java.text.ParseException("saw ~; without matching ~[ or ~<", r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x099b, code lost:
        throw r36;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x09c0, code lost:
        throw r36;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LispFormat(char[] r46, int r47, int r48) throws java.text.ParseException {
        /*
            r45 = this;
            r2 = r45
            r3 = r46
            r4 = r47
            r5 = r48
            r36 = r2
            r37 = 0
            r38 = 0
            r36.<init>(r37, r38)
            r36 = -1
            r6 = r36
            r36 = 0
            r7 = r36
            java.lang.StringBuffer r36 = new java.lang.StringBuffer
            r44 = r36
            r36 = r44
            r37 = r44
            r38 = 100
            r37.<init>(r38)
            r8 = r36
            java.util.Stack r36 = new java.util.Stack
            r44 = r36
            r36 = r44
            r37 = r44
            r37.<init>()
            r9 = r36
            r36 = r4
            r37 = r5
            int r36 = r36 + r37
            r10 = r36
            r36 = r4
            r11 = r36
        L_0x0041:
            r36 = r11
            r37 = r10
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x0059
            r36 = r3
            r37 = r11
            char r36 = r36[r37]
            r37 = 126(0x7e, float:1.77E-43)
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x007b
        L_0x0059:
            r36 = r8
            int r36 = r36.length()
            if (r36 <= 0) goto L_0x007b
            r36 = r9
            gnu.text.LiteralFormat r37 = new gnu.text.LiteralFormat
            r44 = r37
            r37 = r44
            r38 = r44
            r39 = r8
            r38.<init>((java.lang.StringBuffer) r39)
            java.lang.Object r36 = r36.push(r37)
            r36 = r8
            r37 = 0
            r36.setLength(r37)
        L_0x007b:
            r36 = r11
            r37 = r10
            r0 = r36
            r1 = r37
            if (r0 < r1) goto L_0x009b
            r36 = r11
            r37 = r10
            r0 = r36
            r1 = r37
            if (r0 <= r1) goto L_0x0bf4
            java.lang.IndexOutOfBoundsException r36 = new java.lang.IndexOutOfBoundsException
            r44 = r36
            r36 = r44
            r37 = r44
            r37.<init>()
            throw r36
        L_0x009b:
            r36 = r3
            r37 = r11
            int r11 = r11 + 1
            char r36 = r36[r37]
            r12 = r36
            r36 = r12
            r37 = 126(0x7e, float:1.77E-43)
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x00b8
            r36 = r8
            r37 = r12
            java.lang.StringBuffer r36 = r36.append(r37)
            goto L_0x0041
        L_0x00b8:
            r36 = r9
            int r36 = r36.size()
            r13 = r36
            r36 = r3
            r37 = r11
            int r11 = r11 + 1
            char r36 = r36[r37]
            r12 = r36
        L_0x00ca:
            r36 = r12
            r37 = 35
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0112
            r36 = r9
            java.lang.String r37 = "<from count>"
            java.lang.Object r36 = r36.push(r37)
            r36 = r3
            r37 = r11
            int r11 = r11 + 1
            char r36 = r36[r37]
            r12 = r36
        L_0x00e7:
            r36 = r12
            r37 = 44
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x0212
        L_0x00f1:
            r36 = 0
            r14 = r36
            r36 = 0
            r15 = r36
        L_0x00f9:
            r36 = r12
            r37 = 58
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x021e
            r36 = 1
            r14 = r36
        L_0x0107:
            r36 = r3
            r37 = r11
            int r11 = r11 + 1
            char r36 = r36[r37]
            r12 = r36
            goto L_0x00f9
        L_0x0112:
            r36 = r12
            r37 = 118(0x76, float:1.65E-43)
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x0126
            r36 = r12
            r37 = 86
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x013a
        L_0x0126:
            r36 = r9
            java.lang.String r37 = "<from list>"
            java.lang.Object r36 = r36.push(r37)
            r36 = r3
            r37 = r11
            int r11 = r11 + 1
            char r36 = r36[r37]
            r12 = r36
            goto L_0x00e7
        L_0x013a:
            r36 = r12
            r37 = 45
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x014e
            r36 = r12
            r37 = 10
            int r36 = java.lang.Character.digit(r36, r37)
            if (r36 < 0) goto L_0x01d5
        L_0x014e:
            r36 = r12
            r37 = 45
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x01a5
            r36 = 1
        L_0x015a:
            r14 = r36
            r36 = r14
            if (r36 == 0) goto L_0x016a
            r36 = r3
            r37 = r11
            int r11 = r11 + 1
            char r36 = r36[r37]
            r12 = r36
        L_0x016a:
            r36 = 0
            r15 = r36
            r36 = r11
            r16 = r36
        L_0x0172:
            r36 = r12
            r37 = 10
            int r36 = java.lang.Character.digit(r36, r37)
            r17 = r36
            r36 = r17
            if (r36 >= 0) goto L_0x01a8
            r36 = r9
            r37 = r11
            r38 = r16
            int r37 = r37 - r38
            r38 = 8
            r0 = r37
            r1 = r38
            if (r0 >= r1) goto L_0x01c2
            r37 = r14
            if (r37 == 0) goto L_0x01bf
            r37 = r15
            r0 = r37
            int r0 = -r0
            r37 = r0
        L_0x019b:
            gnu.math.IntNum r37 = gnu.math.IntNum.make((int) r37)
        L_0x019f:
            java.lang.Object r36 = r36.push(r37)
            goto L_0x00e7
        L_0x01a5:
            r36 = 0
            goto L_0x015a
        L_0x01a8:
            r36 = 10
            r37 = r15
            int r36 = r36 * r37
            r37 = r17
            int r36 = r36 + r37
            r15 = r36
            r36 = r3
            r37 = r11
            int r11 = r11 + 1
            char r36 = r36[r37]
            r12 = r36
            goto L_0x0172
        L_0x01bf:
            r37 = r15
            goto L_0x019b
        L_0x01c2:
            r37 = r3
            r38 = r16
            r39 = r11
            r40 = r16
            int r39 = r39 - r40
            r40 = 10
            r41 = r14
            gnu.math.IntNum r37 = gnu.math.IntNum.valueOf(r37, r38, r39, r40, r41)
            goto L_0x019f
        L_0x01d5:
            r36 = r12
            r37 = 39
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x01fd
            r36 = r9
            r37 = r3
            r38 = r11
            int r11 = r11 + 1
            char r37 = r37[r38]
            gnu.text.Char r37 = gnu.text.Char.make(r37)
            java.lang.Object r36 = r36.push(r37)
            r36 = r3
            r37 = r11
            int r11 = r11 + 1
            char r36 = r36[r37]
            r12 = r36
            goto L_0x00e7
        L_0x01fd:
            r36 = r12
            r37 = 44
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x00f1
            r36 = r9
            java.lang.String r37 = "<unspecified>"
            java.lang.Object r36 = r36.push(r37)
            goto L_0x00e7
        L_0x0212:
            r36 = r3
            r37 = r11
            int r11 = r11 + 1
            char r36 = r36[r37]
            r12 = r36
            goto L_0x00ca
        L_0x021e:
            r36 = r12
            r37 = 64
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x022e
            r36 = 1
            r15 = r36
            goto L_0x0107
        L_0x022e:
            r36 = r12
            char r36 = java.lang.Character.toUpperCase(r36)
            r12 = r36
            r36 = r9
            int r36 = r36.size()
            r37 = r13
            int r36 = r36 - r37
            r16 = r36
            r36 = r12
            switch(r36) {
                case 10: goto L_0x0a67;
                case 33: goto L_0x0a97;
                case 36: goto L_0x033f;
                case 37: goto L_0x0bd0;
                case 38: goto L_0x0adc;
                case 40: goto L_0x04db;
                case 41: goto L_0x0526;
                case 42: goto L_0x04c0;
                case 59: goto L_0x08f3;
                case 60: goto L_0x066c;
                case 62: goto L_0x06e5;
                case 63: goto L_0x057a;
                case 65: goto L_0x042e;
                case 66: goto L_0x0271;
                case 67: goto L_0x049f;
                case 68: goto L_0x0271;
                case 69: goto L_0x033f;
                case 70: goto L_0x033f;
                case 71: goto L_0x033f;
                case 73: goto L_0x0af7;
                case 79: goto L_0x0271;
                case 80: goto L_0x0334;
                case 82: goto L_0x0271;
                case 83: goto L_0x042e;
                case 84: goto L_0x0a9f;
                case 87: goto L_0x042e;
                case 88: goto L_0x0271;
                case 89: goto L_0x042e;
                case 91: goto L_0x086f;
                case 93: goto L_0x099c;
                case 94: goto L_0x0a2c;
                case 95: goto L_0x0b1b;
                case 123: goto L_0x05ab;
                case 124: goto L_0x0b7f;
                case 125: goto L_0x05fd;
                case 126: goto L_0x0b71;
                default: goto L_0x0247;
            }
        L_0x0247:
            java.text.ParseException r36 = new java.text.ParseException
            r44 = r36
            r36 = r44
            r37 = r44
            java.lang.StringBuilder r38 = new java.lang.StringBuilder
            r44 = r38
            r38 = r44
            r39 = r44
            r39.<init>()
            java.lang.String r39 = "unrecognized format specifier ~"
            java.lang.StringBuilder r38 = r38.append(r39)
            r39 = r12
            java.lang.StringBuilder r38 = r38.append(r39)
            java.lang.String r38 = r38.toString()
            r39 = r11
            r37.<init>(r38, r39)
            throw r36
        L_0x0271:
            r36 = r13
            r25 = r36
            r36 = r12
            r37 = 82
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x02fe
            r36 = r9
            r37 = r25
            int r25 = r25 + 1
            int r36 = getParam(r36, r37)
            r26 = r36
        L_0x028b:
            r36 = r9
            r37 = r25
            int r36 = getParam(r36, r37)
            r18 = r36
            r36 = r9
            r37 = r25
            r38 = 1
            int r37 = r37 + 1
            int r36 = getParam(r36, r37)
            r19 = r36
            r36 = r9
            r37 = r25
            r38 = 2
            int r37 = r37 + 2
            int r36 = getParam(r36, r37)
            r27 = r36
            r36 = r9
            r37 = r25
            r38 = 3
            int r37 = r37 + 3
            int r36 = getParam(r36, r37)
            r28 = r36
            r36 = 0
            r29 = r36
            r36 = r14
            if (r36 == 0) goto L_0x02cf
            r36 = r29
            r37 = 1
            r36 = r36 | 1
            r29 = r36
        L_0x02cf:
            r36 = r15
            if (r36 == 0) goto L_0x02db
            r36 = r29
            r37 = 2
            r36 = r36 | 2
            r29 = r36
        L_0x02db:
            r36 = r26
            r37 = r18
            r38 = r19
            r39 = r27
            r40 = r28
            r41 = r29
            java.text.Format r36 = gnu.kawa.functions.IntegerFormat.getInstance(r36, r37, r38, r39, r40, r41)
            r17 = r36
        L_0x02ed:
            r36 = r9
            r37 = r13
            r36.setSize(r37)
            r36 = r9
            r37 = r17
            java.lang.Object r36 = r36.push(r37)
            goto L_0x0041
        L_0x02fe:
            r36 = r12
            r37 = 68
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x030e
            r36 = 10
            r26 = r36
            goto L_0x028b
        L_0x030e:
            r36 = r12
            r37 = 79
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x031e
            r36 = 8
            r26 = r36
            goto L_0x028b
        L_0x031e:
            r36 = r12
            r37 = 88
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x032e
            r36 = 16
            r26 = r36
            goto L_0x028b
        L_0x032e:
            r36 = 2
            r26 = r36
            goto L_0x028b
        L_0x0334:
            r36 = r14
            r37 = r15
            gnu.kawa.functions.LispPluralFormat r36 = gnu.kawa.functions.LispPluralFormat.getInstance(r36, r37)
            r17 = r36
            goto L_0x02ed
        L_0x033f:
            gnu.kawa.functions.LispRealFormat r36 = new gnu.kawa.functions.LispRealFormat
            r44 = r36
            r36 = r44
            r37 = r44
            r37.<init>()
            r30 = r36
            r36 = r30
            r37 = r12
            r0 = r37
            r1 = r36
            r1.f65op = r0
            r36 = r30
            r37 = r9
            r38 = r13
            int r37 = getParam(r37, r38)
            r0 = r37
            r1 = r36
            r1.arg1 = r0
            r36 = r30
            r37 = r9
            r38 = r13
            r39 = 1
            int r38 = r38 + 1
            int r37 = getParam(r37, r38)
            r0 = r37
            r1 = r36
            r1.arg2 = r0
            r36 = r30
            r37 = r9
            r38 = r13
            r39 = 2
            int r38 = r38 + 2
            int r37 = getParam(r37, r38)
            r0 = r37
            r1 = r36
            r1.arg3 = r0
            r36 = r30
            r37 = r9
            r38 = r13
            r39 = 3
            int r38 = r38 + 3
            int r37 = getParam(r37, r38)
            r0 = r37
            r1 = r36
            r1.arg4 = r0
            r36 = r12
            r37 = 36
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x03fc
            r36 = r30
            r37 = r9
            r38 = r13
            r39 = 4
            int r38 = r38 + 4
            int r37 = getParam(r37, r38)
            r0 = r37
            r1 = r36
            r1.arg5 = r0
            r36 = r12
            r37 = 69
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x03d4
            r36 = r12
            r37 = 71
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x03fc
        L_0x03d4:
            r36 = r30
            r37 = r9
            r38 = r13
            r39 = 5
            int r38 = r38 + 5
            int r37 = getParam(r37, r38)
            r0 = r37
            r1 = r36
            r1.arg6 = r0
            r36 = r30
            r37 = r9
            r38 = r13
            r39 = 6
            int r38 = r38 + 6
            int r37 = getParam(r37, r38)
            r0 = r37
            r1 = r36
            r1.arg7 = r0
        L_0x03fc:
            r36 = r30
            r37 = r15
            r0 = r37
            r1 = r36
            r1.showPlus = r0
            r36 = r30
            r37 = r14
            r0 = r37
            r1 = r36
            r1.internalPad = r0
            r36 = r30
            r0 = r36
            int r0 = r0.argsUsed
            r36 = r0
            if (r36 != 0) goto L_0x0428
            r36 = r30
            r37 = 0
            r38 = 0
            java.text.Format r36 = r36.resolve(r37, r38)
            r17 = r36
            goto L_0x02ed
        L_0x0428:
            r36 = r30
            r17 = r36
            goto L_0x02ed
        L_0x042e:
            r36 = r12
            r37 = 65
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x0499
            r36 = 1
        L_0x043a:
            gnu.kawa.functions.ObjectFormat r36 = gnu.kawa.functions.ObjectFormat.getInstance(r36)
            r17 = r36
            r36 = r16
            if (r36 <= 0) goto L_0x02ed
            r36 = r9
            r37 = r13
            int r36 = getParam(r36, r37)
            r18 = r36
            r36 = r9
            r37 = r13
            r38 = 1
            int r37 = r37 + 1
            int r36 = getParam(r36, r37)
            r31 = r36
            r36 = r9
            r37 = r13
            r38 = 2
            int r37 = r37 + 2
            int r36 = getParam(r36, r37)
            r32 = r36
            r36 = r9
            r37 = r13
            r38 = 3
            int r37 = r37 + 3
            int r36 = getParam(r36, r37)
            r19 = r36
            gnu.kawa.functions.LispObjectFormat r36 = new gnu.kawa.functions.LispObjectFormat
            r44 = r36
            r36 = r44
            r37 = r44
            r38 = r17
            gnu.text.ReportFormat r38 = (gnu.text.ReportFormat) r38
            r39 = r18
            r40 = r31
            r41 = r32
            r42 = r19
            r43 = r15
            if (r43 == 0) goto L_0x049c
            r43 = 0
        L_0x0492:
            r37.<init>(r38, r39, r40, r41, r42, r43)
            r17 = r36
            goto L_0x02ed
        L_0x0499:
            r36 = 0
            goto L_0x043a
        L_0x049c:
            r43 = 100
            goto L_0x0492
        L_0x049f:
            r36 = r16
            if (r36 <= 0) goto L_0x04bd
            r36 = r9
            r37 = r13
            int r36 = getParam(r36, r37)
        L_0x04ab:
            r20 = r36
            r36 = r20
            r37 = 1
            r38 = r15
            r39 = r14
            gnu.kawa.functions.LispCharacterFormat r36 = gnu.kawa.functions.LispCharacterFormat.getInstance(r36, r37, r38, r39)
            r17 = r36
            goto L_0x02ed
        L_0x04bd:
            r36 = -1610612736(0xffffffffa0000000, float:-1.0842022E-19)
            goto L_0x04ab
        L_0x04c0:
            gnu.kawa.functions.LispRepositionFormat r36 = new gnu.kawa.functions.LispRepositionFormat
            r44 = r36
            r36 = r44
            r37 = r44
            r38 = r9
            r39 = r13
            int r38 = getParam(r38, r39)
            r39 = r14
            r40 = r15
            r37.<init>(r38, r39, r40)
            r17 = r36
            goto L_0x02ed
        L_0x04db:
            r36 = r14
            if (r36 == 0) goto L_0x051c
            r36 = r15
            if (r36 == 0) goto L_0x0519
            r36 = 85
        L_0x04e5:
            r12 = r36
            gnu.text.CaseConvertFormat r36 = new gnu.text.CaseConvertFormat
            r44 = r36
            r36 = r44
            r37 = r44
            r38 = 0
            r39 = r12
            r37.<init>(r38, r39)
            r31 = r36
            r36 = r9
            r37 = r13
            r36.setSize(r37)
            r36 = r9
            r37 = r31
            java.lang.Object r36 = r36.push(r37)
            r36 = r9
            r37 = r6
            gnu.math.IntNum r37 = gnu.math.IntNum.make((int) r37)
            java.lang.Object r36 = r36.push(r37)
            r36 = r13
            r6 = r36
            goto L_0x0041
        L_0x0519:
            r36 = 67
            goto L_0x04e5
        L_0x051c:
            r36 = r15
            if (r36 == 0) goto L_0x0523
            r36 = 84
            goto L_0x04e5
        L_0x0523:
            r36 = 76
            goto L_0x04e5
        L_0x0526:
            r36 = r6
            if (r36 < 0) goto L_0x053a
            r36 = r9
            r37 = r6
            java.lang.Object r36 = r36.elementAt(r37)
            r0 = r36
            boolean r0 = r0 instanceof gnu.text.CaseConvertFormat
            r36 = r0
            if (r36 != 0) goto L_0x054b
        L_0x053a:
            java.text.ParseException r36 = new java.text.ParseException
            r44 = r36
            r36 = r44
            r37 = r44
            java.lang.String r38 = "saw ~) without matching ~("
            r39 = r11
            r37.<init>(r38, r39)
            throw r36
        L_0x054b:
            r36 = r9
            r37 = r6
            java.lang.Object r36 = r36.elementAt(r37)
            gnu.text.CaseConvertFormat r36 = (gnu.text.CaseConvertFormat) r36
            r31 = r36
            r36 = r31
            r37 = r9
            r38 = r6
            r39 = 2
            int r38 = r38 + 2
            r39 = r13
            java.text.Format r37 = popFormats(r37, r38, r39)
            r36.setBaseFormat(r37)
            r36 = r9
            java.lang.Object r36 = r36.pop()
            gnu.math.IntNum r36 = (gnu.math.IntNum) r36
            int r36 = r36.intValue()
            r6 = r36
            goto L_0x0041
        L_0x057a:
            gnu.kawa.functions.LispIterationFormat r36 = new gnu.kawa.functions.LispIterationFormat
            r44 = r36
            r36 = r44
            r37 = r44
            r37.<init>()
            r32 = r36
            r36 = r32
            r37 = r15
            r0 = r37
            r1 = r36
            r1.seenAt = r0
            r36 = r32
            r37 = 1
            r0 = r37
            r1 = r36
            r1.maxIterations = r0
            r36 = r32
            r37 = 1
            r0 = r37
            r1 = r36
            r1.atLeastOnce = r0
            r36 = r32
            r17 = r36
            goto L_0x02ed
        L_0x05ab:
            gnu.kawa.functions.LispIterationFormat r36 = new gnu.kawa.functions.LispIterationFormat
            r44 = r36
            r36 = r44
            r37 = r44
            r37.<init>()
            r32 = r36
            r36 = r32
            r37 = r15
            r0 = r37
            r1 = r36
            r1.seenAt = r0
            r36 = r32
            r37 = r14
            r0 = r37
            r1 = r36
            r1.seenColon = r0
            r36 = r32
            r37 = r9
            r38 = r13
            int r37 = getParam(r37, r38)
            r0 = r37
            r1 = r36
            r1.maxIterations = r0
            r36 = r9
            r37 = r13
            r36.setSize(r37)
            r36 = r9
            r37 = r32
            java.lang.Object r36 = r36.push(r37)
            r36 = r9
            r37 = r6
            gnu.math.IntNum r37 = gnu.math.IntNum.make((int) r37)
            java.lang.Object r36 = r36.push(r37)
            r36 = r13
            r6 = r36
            goto L_0x0041
        L_0x05fd:
            r36 = r6
            if (r36 < 0) goto L_0x0611
            r36 = r9
            r37 = r6
            java.lang.Object r36 = r36.elementAt(r37)
            r0 = r36
            boolean r0 = r0 instanceof gnu.kawa.functions.LispIterationFormat
            r36 = r0
            if (r36 != 0) goto L_0x0622
        L_0x0611:
            java.text.ParseException r36 = new java.text.ParseException
            r44 = r36
            r36 = r44
            r37 = r44
            java.lang.String r38 = "saw ~} without matching ~{"
            r39 = r11
            r37.<init>(r38, r39)
            throw r36
        L_0x0622:
            r36 = r9
            r37 = r6
            java.lang.Object r36 = r36.elementAt(r37)
            gnu.kawa.functions.LispIterationFormat r36 = (gnu.kawa.functions.LispIterationFormat) r36
            r32 = r36
            r36 = r32
            r37 = r14
            r0 = r37
            r1 = r36
            r1.atLeastOnce = r0
            r36 = r13
            r37 = r6
            r38 = 2
            int r37 = r37 + 2
            r0 = r36
            r1 = r37
            if (r0 <= r1) goto L_0x065c
            r36 = r32
            r37 = r9
            r38 = r6
            r39 = 2
            int r38 = r38 + 2
            r39 = r13
            java.text.Format r37 = popFormats(r37, r38, r39)
            r0 = r37
            r1 = r36
            r1.body = r0
        L_0x065c:
            r36 = r9
            java.lang.Object r36 = r36.pop()
            gnu.math.IntNum r36 = (gnu.math.IntNum) r36
            int r36 = r36.intValue()
            r6 = r36
            goto L_0x0041
        L_0x066c:
            gnu.kawa.functions.LispPrettyFormat r36 = new gnu.kawa.functions.LispPrettyFormat
            r44 = r36
            r36 = r44
            r37 = r44
            r37.<init>()
            r33 = r36
            r36 = r33
            r37 = r15
            r0 = r37
            r1 = r36
            r1.seenAt = r0
            r36 = r14
            if (r36 == 0) goto L_0x06ce
            r36 = r33
            java.lang.String r37 = "("
            r0 = r37
            r1 = r36
            r1.prefix = r0
            r36 = r33
            java.lang.String r37 = ")"
            r0 = r37
            r1 = r36
            r1.suffix = r0
        L_0x069d:
            r36 = r9
            r37 = r13
            r36.setSize(r37)
            r36 = r9
            r37 = r33
            java.lang.Object r36 = r36.push(r37)
            r36 = r9
            r37 = r6
            gnu.math.IntNum r37 = gnu.math.IntNum.make((int) r37)
            java.lang.Object r36 = r36.push(r37)
            r36 = r9
            r37 = r7
            gnu.math.IntNum r37 = gnu.math.IntNum.make((int) r37)
            java.lang.Object r36 = r36.push(r37)
            r36 = r13
            r6 = r36
            r36 = 0
            r7 = r36
            goto L_0x0041
        L_0x06ce:
            r36 = r33
            java.lang.String r37 = ""
            r0 = r37
            r1 = r36
            r1.prefix = r0
            r36 = r33
            java.lang.String r37 = ""
            r0 = r37
            r1 = r36
            r1.suffix = r0
            goto L_0x069d
        L_0x06e5:
            r36 = r6
            if (r36 < 0) goto L_0x06f9
            r36 = r9
            r37 = r6
            java.lang.Object r36 = r36.elementAt(r37)
            r0 = r36
            boolean r0 = r0 instanceof gnu.kawa.functions.LispPrettyFormat
            r36 = r0
            if (r36 != 0) goto L_0x070a
        L_0x06f9:
            java.text.ParseException r36 = new java.text.ParseException
            r44 = r36
            r36 = r44
            r37 = r44
            java.lang.String r38 = "saw ~> without matching ~<"
            r39 = r11
            r37.<init>(r38, r39)
            throw r36
        L_0x070a:
            r36 = r9
            r37 = r6
            r38 = 3
            int r37 = r37 + 3
            r38 = r7
            int r37 = r37 + r38
            r38 = r13
            java.text.Format r36 = popFormats(r36, r37, r38)
            r17 = r36
            r36 = r9
            r37 = r17
            java.lang.Object r36 = r36.push(r37)
            r36 = r9
            r37 = r6
            java.lang.Object r36 = r36.elementAt(r37)
            gnu.kawa.functions.LispPrettyFormat r36 = (gnu.kawa.functions.LispPrettyFormat) r36
            r33 = r36
            r36 = r33
            r37 = r9
            r38 = r6
            r39 = 3
            int r38 = r38 + 3
            r39 = r9
            int r39 = r39.size()
            java.text.Format[] r37 = getFormats(r37, r38, r39)
            r0 = r37
            r1 = r36
            r1.segments = r0
            r36 = r9
            r37 = r6
            r38 = 3
            int r37 = r37 + 3
            r36.setSize(r37)
            r36 = r9
            java.lang.Object r36 = r36.pop()
            gnu.math.IntNum r36 = (gnu.math.IntNum) r36
            int r36 = r36.intValue()
            r6 = r36
            r36 = r9
            java.lang.Object r36 = r36.pop()
            gnu.math.IntNum r36 = (gnu.math.IntNum) r36
            int r36 = r36.intValue()
            r6 = r36
            r36 = r14
            if (r36 == 0) goto L_0x085e
            r36 = r33
            r0 = r36
            java.text.Format[] r0 = r0.segments
            r36 = r0
            r0 = r36
            int r0 = r0.length
            r36 = r0
            r34 = r36
            r36 = r34
            r37 = 3
            r0 = r36
            r1 = r37
            if (r0 <= r1) goto L_0x07a1
            java.text.ParseException r36 = new java.text.ParseException
            r44 = r36
            r36 = r44
            r37 = r44
            java.lang.String r38 = "too many segments in Logical Block format"
            r39 = r11
            r37.<init>(r38, r39)
            throw r36
        L_0x07a1:
            r36 = r34
            r37 = 2
            r0 = r36
            r1 = r37
            if (r0 < r1) goto L_0x082d
            r36 = r33
            r0 = r36
            java.text.Format[] r0 = r0.segments
            r36 = r0
            r37 = 0
            r36 = r36[r37]
            r0 = r36
            boolean r0 = r0 instanceof gnu.text.LiteralFormat
            r36 = r0
            if (r36 != 0) goto L_0x07d0
            java.text.ParseException r36 = new java.text.ParseException
            r44 = r36
            r36 = r44
            r37 = r44
            java.lang.String r38 = "prefix segment is not literal"
            r39 = r11
            r37.<init>(r38, r39)
            throw r36
        L_0x07d0:
            r36 = r33
            r37 = r33
            r0 = r37
            java.text.Format[] r0 = r0.segments
            r37 = r0
            r38 = 0
            r37 = r37[r38]
            gnu.text.LiteralFormat r37 = (gnu.text.LiteralFormat) r37
            java.lang.String r37 = r37.content()
            r0 = r37
            r1 = r36
            r1.prefix = r0
            r36 = r33
            r37 = r33
            r0 = r37
            java.text.Format[] r0 = r0.segments
            r37 = r0
            r38 = 1
            r37 = r37[r38]
            r0 = r37
            r1 = r36
            r1.body = r0
        L_0x07fe:
            r36 = r34
            r37 = 3
            r0 = r36
            r1 = r37
            if (r0 < r1) goto L_0x085c
            r36 = r33
            r0 = r36
            java.text.Format[] r0 = r0.segments
            r36 = r0
            r37 = 2
            r36 = r36[r37]
            r0 = r36
            boolean r0 = r0 instanceof gnu.text.LiteralFormat
            r36 = r0
            if (r36 != 0) goto L_0x0842
            java.text.ParseException r36 = new java.text.ParseException
            r44 = r36
            r36 = r44
            r37 = r44
            java.lang.String r38 = "suffix segment is not literal"
            r39 = r11
            r37.<init>(r38, r39)
            throw r36
        L_0x082d:
            r36 = r33
            r37 = r33
            r0 = r37
            java.text.Format[] r0 = r0.segments
            r37 = r0
            r38 = 0
            r37 = r37[r38]
            r0 = r37
            r1 = r36
            r1.body = r0
            goto L_0x07fe
        L_0x0842:
            r36 = r33
            r37 = r33
            r0 = r37
            java.text.Format[] r0 = r0.segments
            r37 = r0
            r38 = 2
            r37 = r37[r38]
            gnu.text.LiteralFormat r37 = (gnu.text.LiteralFormat) r37
            java.lang.String r37 = r37.content()
            r0 = r37
            r1 = r36
            r1.suffix = r0
        L_0x085c:
            goto L_0x0041
        L_0x085e:
            java.text.ParseException r36 = new java.text.ParseException
            r44 = r36
            r36 = r44
            r37 = r44
            java.lang.String r38 = "not implemented: justfication i.e. ~<...~>"
            r39 = r11
            r37.<init>(r38, r39)
            throw r36
        L_0x086f:
            gnu.kawa.functions.LispChoiceFormat r36 = new gnu.kawa.functions.LispChoiceFormat
            r44 = r36
            r36 = r44
            r37 = r44
            r37.<init>()
            r34 = r36
            r36 = r34
            r37 = r9
            r38 = r13
            int r37 = getParam(r37, r38)
            r0 = r37
            r1 = r36
            r1.param = r0
            r36 = r34
            r0 = r36
            int r0 = r0.param
            r36 = r0
            r37 = -1073741824(0xffffffffc0000000, float:-2.0)
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x08a6
            r36 = r34
            r37 = -1610612736(0xffffffffa0000000, float:-1.0842022E-19)
            r0 = r37
            r1 = r36
            r1.param = r0
        L_0x08a6:
            r36 = r14
            if (r36 == 0) goto L_0x08b4
            r36 = r34
            r37 = 1
            r0 = r37
            r1 = r36
            r1.testBoolean = r0
        L_0x08b4:
            r36 = r15
            if (r36 == 0) goto L_0x08c2
            r36 = r34
            r37 = 1
            r0 = r37
            r1 = r36
            r1.skipIfFalse = r0
        L_0x08c2:
            r36 = r9
            r37 = r13
            r36.setSize(r37)
            r36 = r9
            r37 = r34
            java.lang.Object r36 = r36.push(r37)
            r36 = r9
            r37 = r6
            gnu.math.IntNum r37 = gnu.math.IntNum.make((int) r37)
            java.lang.Object r36 = r36.push(r37)
            r36 = r9
            r37 = r7
            gnu.math.IntNum r37 = gnu.math.IntNum.make((int) r37)
            java.lang.Object r36 = r36.push(r37)
            r36 = r13
            r6 = r36
            r36 = 0
            r7 = r36
            goto L_0x0041
        L_0x08f3:
            r36 = r6
            if (r36 < 0) goto L_0x098b
            r36 = r9
            r37 = r6
            java.lang.Object r36 = r36.elementAt(r37)
            r0 = r36
            boolean r0 = r0 instanceof gnu.kawa.functions.LispChoiceFormat
            r36 = r0
            if (r36 == 0) goto L_0x0941
            r36 = r9
            r37 = r6
            java.lang.Object r36 = r36.elementAt(r37)
            gnu.kawa.functions.LispChoiceFormat r36 = (gnu.kawa.functions.LispChoiceFormat) r36
            r34 = r36
            r36 = r14
            if (r36 == 0) goto L_0x0921
            r36 = r34
            r37 = 1
            r0 = r37
            r1 = r36
            r1.lastIsDefault = r0
        L_0x0921:
            r36 = r9
            r37 = r6
            r38 = 3
            int r37 = r37 + 3
            r38 = r7
            int r37 = r37 + r38
            r38 = r13
            java.text.Format r36 = popFormats(r36, r37, r38)
            r17 = r36
            r36 = r9
            r37 = r17
            java.lang.Object r36 = r36.push(r37)
            int r7 = r7 + 1
            goto L_0x0041
        L_0x0941:
            r36 = r9
            r37 = r6
            java.lang.Object r36 = r36.elementAt(r37)
            r0 = r36
            boolean r0 = r0 instanceof gnu.kawa.functions.LispPrettyFormat
            r36 = r0
            if (r36 == 0) goto L_0x098b
            r36 = r9
            r37 = r6
            java.lang.Object r36 = r36.elementAt(r37)
            gnu.kawa.functions.LispPrettyFormat r36 = (gnu.kawa.functions.LispPrettyFormat) r36
            r33 = r36
            r36 = r15
            if (r36 == 0) goto L_0x096b
            r36 = r33
            r37 = 1
            r0 = r37
            r1 = r36
            r1.perLine = r0
        L_0x096b:
            r36 = r9
            r37 = r6
            r38 = 3
            int r37 = r37 + 3
            r38 = r7
            int r37 = r37 + r38
            r38 = r13
            java.text.Format r36 = popFormats(r36, r37, r38)
            r17 = r36
            r36 = r9
            r37 = r17
            java.lang.Object r36 = r36.push(r37)
            int r7 = r7 + 1
            goto L_0x0041
        L_0x098b:
            java.text.ParseException r36 = new java.text.ParseException
            r44 = r36
            r36 = r44
            r37 = r44
            java.lang.String r38 = "saw ~; without matching ~[ or ~<"
            r39 = r11
            r37.<init>(r38, r39)
            throw r36
        L_0x099c:
            r36 = r6
            if (r36 < 0) goto L_0x09b0
            r36 = r9
            r37 = r6
            java.lang.Object r36 = r36.elementAt(r37)
            r0 = r36
            boolean r0 = r0 instanceof gnu.kawa.functions.LispChoiceFormat
            r36 = r0
            if (r36 != 0) goto L_0x09c1
        L_0x09b0:
            java.text.ParseException r36 = new java.text.ParseException
            r44 = r36
            r36 = r44
            r37 = r44
            java.lang.String r38 = "saw ~] without matching ~["
            r39 = r11
            r37.<init>(r38, r39)
            throw r36
        L_0x09c1:
            r36 = r9
            r37 = r6
            r38 = 3
            int r37 = r37 + 3
            r38 = r7
            int r37 = r37 + r38
            r38 = r13
            java.text.Format r36 = popFormats(r36, r37, r38)
            r17 = r36
            r36 = r9
            r37 = r17
            java.lang.Object r36 = r36.push(r37)
            r36 = r9
            r37 = r6
            java.lang.Object r36 = r36.elementAt(r37)
            gnu.kawa.functions.LispChoiceFormat r36 = (gnu.kawa.functions.LispChoiceFormat) r36
            r34 = r36
            r36 = r34
            r37 = r9
            r38 = r6
            r39 = 3
            int r38 = r38 + 3
            r39 = r9
            int r39 = r39.size()
            java.text.Format[] r37 = getFormats(r37, r38, r39)
            r0 = r37
            r1 = r36
            r1.choices = r0
            r36 = r9
            r37 = r6
            r38 = 3
            int r37 = r37 + 3
            r36.setSize(r37)
            r36 = r9
            java.lang.Object r36 = r36.pop()
            gnu.math.IntNum r36 = (gnu.math.IntNum) r36
            int r36 = r36.intValue()
            r7 = r36
            r36 = r9
            java.lang.Object r36 = r36.pop()
            gnu.math.IntNum r36 = (gnu.math.IntNum) r36
            int r36 = r36.intValue()
            r6 = r36
            goto L_0x0041
        L_0x0a2c:
            r36 = r9
            r37 = r13
            int r36 = getParam(r36, r37)
            r21 = r36
            r36 = r9
            r37 = r13
            r38 = 1
            int r37 = r37 + 1
            int r36 = getParam(r36, r37)
            r22 = r36
            r36 = r9
            r37 = r13
            r38 = 2
            int r37 = r37 + 2
            int r36 = getParam(r36, r37)
            r23 = r36
            gnu.kawa.functions.LispEscapeFormat r36 = new gnu.kawa.functions.LispEscapeFormat
            r44 = r36
            r36 = r44
            r37 = r44
            r38 = r21
            r39 = r22
            r40 = r23
            r37.<init>(r38, r39, r40)
            r17 = r36
            goto L_0x02ed
        L_0x0a67:
            r36 = r15
            if (r36 == 0) goto L_0x0a73
            r36 = r8
            r37 = r12
            java.lang.StringBuffer r36 = r36.append(r37)
        L_0x0a73:
            r36 = r14
            if (r36 != 0) goto L_0x0041
        L_0x0a77:
            r36 = r11
            r37 = r10
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x0041
            r36 = r3
            r37 = r11
            int r11 = r11 + 1
            char r36 = r36[r37]
            r12 = r36
            r36 = r12
            boolean r36 = java.lang.Character.isWhitespace(r36)
            if (r36 != 0) goto L_0x0a77
            int r11 = r11 + -1
            goto L_0x0041
        L_0x0a97:
            gnu.text.FlushFormat r36 = gnu.text.FlushFormat.getInstance()
            r17 = r36
            goto L_0x02ed
        L_0x0a9f:
            r36 = r9
            r37 = r13
            int r36 = getParam(r36, r37)
            r21 = r36
            r36 = r9
            r37 = r13
            r38 = 1
            int r37 = r37 + 1
            int r36 = getParam(r36, r37)
            r22 = r36
            r36 = r9
            r37 = r13
            r38 = 2
            int r37 = r37 + 2
            int r36 = getParam(r36, r37)
            r23 = r36
            gnu.kawa.functions.LispTabulateFormat r36 = new gnu.kawa.functions.LispTabulateFormat
            r44 = r36
            r36 = r44
            r37 = r44
            r38 = r21
            r39 = r22
            r40 = r23
            r41 = r15
            r37.<init>(r38, r39, r40, r41)
            r17 = r36
            goto L_0x02ed
        L_0x0adc:
            r36 = r9
            r37 = r13
            int r36 = getParam(r36, r37)
            r21 = r36
            gnu.kawa.functions.LispFreshlineFormat r36 = new gnu.kawa.functions.LispFreshlineFormat
            r44 = r36
            r36 = r44
            r37 = r44
            r38 = r21
            r37.<init>(r38)
            r17 = r36
            goto L_0x02ed
        L_0x0af7:
            r36 = r9
            r37 = r13
            int r36 = getParam(r36, r37)
            r21 = r36
            r36 = r21
            r37 = -1073741824(0xffffffffc0000000, float:-2.0)
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0b0f
            r36 = 0
            r21 = r36
        L_0x0b0f:
            r36 = r21
            r37 = r14
            gnu.kawa.functions.LispIndentFormat r36 = gnu.kawa.functions.LispIndentFormat.getInstance(r36, r37)
            r17 = r36
            goto L_0x02ed
        L_0x0b1b:
            r36 = r9
            r37 = r13
            int r36 = getParam(r36, r37)
            r21 = r36
            r36 = r21
            r37 = -1073741824(0xffffffffc0000000, float:-2.0)
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0b33
            r36 = 1
            r21 = r36
        L_0x0b33:
            r36 = r14
            if (r36 == 0) goto L_0x0b57
            r36 = r15
            if (r36 == 0) goto L_0x0b57
            r36 = 10
        L_0x0b3d:
            r20 = r36
            r36 = r15
            if (r36 == 0) goto L_0x0b5a
            r36 = r14
            if (r36 == 0) goto L_0x0b5a
            r36 = 82
            r35 = r36
        L_0x0b4b:
            r36 = r21
            r37 = r35
            gnu.kawa.functions.LispNewlineFormat r36 = gnu.kawa.functions.LispNewlineFormat.getInstance(r36, r37)
            r17 = r36
            goto L_0x02ed
        L_0x0b57:
            r36 = 32
            goto L_0x0b3d
        L_0x0b5a:
            r36 = r15
            if (r36 == 0) goto L_0x0b63
            r36 = 77
            r35 = r36
            goto L_0x0b4b
        L_0x0b63:
            r36 = r14
            if (r36 == 0) goto L_0x0b6c
            r36 = 70
            r35 = r36
            goto L_0x0b4b
        L_0x0b6c:
            r36 = 78
            r35 = r36
            goto L_0x0b4b
        L_0x0b71:
            r36 = r16
            if (r36 != 0) goto L_0x0b7f
            r36 = r8
            r37 = r12
            java.lang.StringBuffer r36 = r36.append(r37)
            goto L_0x0041
        L_0x0b7f:
            r36 = r9
            r37 = r13
            int r36 = getParam(r36, r37)
            r24 = r36
            r36 = r24
            r37 = -1073741824(0xffffffffc0000000, float:-2.0)
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0b97
            r36 = 1
            r24 = r36
        L_0x0b97:
            r36 = r9
            r37 = r13
            r38 = 1
            int r37 = r37 + 1
            int r36 = getParam(r36, r37)
            r20 = r36
            r36 = r20
            r37 = -1073741824(0xffffffffc0000000, float:-2.0)
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0bbd
            r36 = r12
            r37 = 124(0x7c, float:1.74E-43)
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0bcd
            r36 = 12
        L_0x0bbb:
            r20 = r36
        L_0x0bbd:
            r36 = r20
            r37 = r24
            r38 = 0
            r39 = 0
            gnu.kawa.functions.LispCharacterFormat r36 = gnu.kawa.functions.LispCharacterFormat.getInstance(r36, r37, r38, r39)
            r17 = r36
            goto L_0x02ed
        L_0x0bcd:
            r36 = 126(0x7e, float:1.77E-43)
            goto L_0x0bbb
        L_0x0bd0:
            r36 = r9
            r37 = r13
            int r36 = getParam(r36, r37)
            r24 = r36
            r36 = r24
            r37 = -1073741824(0xffffffffc0000000, float:-2.0)
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0be8
            r36 = 1
            r24 = r36
        L_0x0be8:
            r36 = r24
            r37 = 76
            gnu.kawa.functions.LispNewlineFormat r36 = gnu.kawa.functions.LispNewlineFormat.getInstance(r36, r37)
            r17 = r36
            goto L_0x02ed
        L_0x0bf4:
            r36 = r6
            if (r36 < 0) goto L_0x0c09
            java.text.ParseException r36 = new java.text.ParseException
            r44 = r36
            r36 = r44
            r37 = r44
            java.lang.String r38 = "missing ~] or ~}"
            r39 = r11
            r37.<init>(r38, r39)
            throw r36
        L_0x0c09:
            r36 = r2
            r37 = r9
            int r37 = r37.size()
            r0 = r37
            r1 = r36
            r1.length = r0
            r36 = r2
            r37 = r2
            r0 = r37
            int r0 = r0.length
            r37 = r0
            r0 = r37
            java.text.Format[] r0 = new java.text.Format[r0]
            r37 = r0
            r0 = r37
            r1 = r36
            r1.formats = r0
            r36 = r9
            r37 = r2
            r0 = r37
            java.text.Format[] r0 = r0.formats
            r37 = r0
            r36.copyInto(r37)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.LispFormat.<init>(char[], int, int):void");
    }

    static Format[] getFormats(Vector vector, int i, int i2) {
        Vector vector2 = vector;
        int start = i;
        int end = i2;
        Format[] f = new Format[(end - start)];
        for (int i3 = start; i3 < end; i3++) {
            f[i3 - start] = (Format) vector2.elementAt(i3);
        }
        return f;
    }

    static Format popFormats(Vector vector, int i, int i2) {
        Format format;
        Format f;
        Vector vector2 = vector;
        int start = i;
        int end = i2;
        if (end == start + 1) {
            f = (Format) vector2.elementAt(start);
        } else {
            new CompoundFormat(getFormats(vector2, start, end));
            f = format;
        }
        vector2.setSize(start);
        return f;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LispFormat(String str) throws ParseException {
        this(str.toCharArray());
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LispFormat(char[] r7) throws java.text.ParseException {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            r4 = 0
            r5 = r1
            int r5 = r5.length
            r2.<init>(r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.LispFormat.<init>(char[]):void");
    }

    public static int getParam(Vector vector, int i) {
        Vector vec = vector;
        int index = i;
        if (index >= vec.size()) {
            return -1073741824;
        }
        Object arg = vec.elementAt(index);
        if (arg == paramFromList) {
            return -1610612736;
        }
        if (arg == paramFromCount) {
            return ReportFormat.PARAM_FROM_COUNT;
        }
        if (arg == paramUnspecified) {
            return -1073741824;
        }
        return getParam(arg, -1073741824);
    }

    public static Object[] asArray(Object obj) {
        Object arg = obj;
        if (arg instanceof Object[]) {
            return (Object[]) arg;
        }
        if (!(arg instanceof Sequence)) {
            return null;
        }
        int count = ((Sequence) arg).size();
        Object[] arr = new Object[count];
        int i = 0;
        while (arg instanceof Pair) {
            Pair pair = (Pair) arg;
            int i2 = i;
            i++;
            arr[i2] = pair.getCar();
            arg = pair.getCdr();
        }
        if (i < count) {
            if (!(arg instanceof Sequence)) {
                return null;
            }
            int npairs = i;
            Sequence seq = (Sequence) arg;
            while (i < count) {
                arr[i] = seq.get(npairs + i);
                i++;
            }
        }
        return arr;
    }
}
