package gnu.kawa.functions;

import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

/* compiled from: LispFormat */
class LispChoiceFormat extends ReportFormat {
    Format[] choices;
    boolean lastIsDefault;
    int param;
    boolean skipIfFalse;
    boolean testBoolean;

    LispChoiceFormat() {
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        Format fmt;
        Object[] args = objArr;
        int start = i;
        Writer dst = writer;
        FieldPosition fpos = fieldPosition;
        if (this.testBoolean) {
            fmt = this.choices[args[start] == Boolean.FALSE ? (char) 0 : 1];
            start++;
        } else if (!this.skipIfFalse) {
            int index = getParam(this.param, -1610612736, args, start);
            if (this.param == -1610612736) {
                start++;
            }
            if (index < 0 || index >= this.choices.length) {
                if (!this.lastIsDefault) {
                    return start;
                }
                index = this.choices.length - 1;
            }
            fmt = this.choices[index];
        } else if (args[start] == Boolean.FALSE) {
            return start + 1;
        } else {
            fmt = this.choices[0];
        }
        return ReportFormat.format(fmt, args, start, dst, fpos);
    }
}
