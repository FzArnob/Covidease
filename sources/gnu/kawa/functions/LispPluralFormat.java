package gnu.kawa.functions;

import gnu.math.IntNum;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispPluralFormat extends ReportFormat {
    boolean backup;

    /* renamed from: y */
    boolean f64y;

    LispPluralFormat() {
    }

    public static LispPluralFormat getInstance(boolean backup2, boolean y) {
        LispPluralFormat lispPluralFormat;
        new LispPluralFormat();
        LispPluralFormat fmt = lispPluralFormat;
        fmt.backup = backup2;
        fmt.f64y = y;
        return fmt;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        Object[] args = objArr;
        int start = i;
        Writer dst = writer;
        FieldPosition fieldPosition2 = fieldPosition;
        if (this.backup) {
            start--;
        }
        int i2 = start;
        int start2 = start + 1;
        boolean plural = args[i2] != IntNum.one();
        if (this.f64y) {
            print(dst, plural ? "ies" : "y");
        } else if (plural) {
            dst.write(115);
        }
        return start2;
    }
}
