package gnu.kawa.functions;

import gnu.text.Char;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispCharacterFormat extends ReportFormat {
    int charVal;
    int count;
    boolean seenAt;
    boolean seenColon;

    LispCharacterFormat() {
    }

    public static LispCharacterFormat getInstance(int charVal2, int count2, boolean seenAt2, boolean seenColon2) {
        LispCharacterFormat lispCharacterFormat;
        new LispCharacterFormat();
        LispCharacterFormat fmt = lispCharacterFormat;
        fmt.count = count2;
        fmt.charVal = charVal2;
        fmt.seenAt = seenAt2;
        fmt.seenColon = seenColon2;
        return fmt;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        Object[] args = objArr;
        int start = i;
        Writer dst = writer;
        FieldPosition fieldPosition2 = fieldPosition;
        int count2 = getParam(this.count, 1, args, start);
        if (this.count == -1610612736) {
            start++;
        }
        int charVal2 = getParam(this.charVal, '?', args, start);
        if (this.charVal == -1610612736) {
            start++;
        }
        while (true) {
            count2--;
            if (count2 < 0) {
                return start;
            }
            printChar(charVal2, this.seenAt, this.seenColon, dst);
        }
    }

    public static void printChar(int i, boolean seenAt2, boolean z, Writer writer) throws IOException {
        int ch = i;
        boolean seenColon2 = z;
        Writer dst = writer;
        if (seenAt2) {
            print(dst, Char.toScmReadableString(ch));
        } else if (!seenColon2) {
            dst.write(ch);
        } else if (ch < 32) {
            dst.write(94);
            dst.write(ch + 64);
        } else if (ch >= 127) {
            print(dst, "#\\x");
            print(dst, Integer.toString(ch, 16));
        } else {
            dst.write(ch);
        }
    }
}
