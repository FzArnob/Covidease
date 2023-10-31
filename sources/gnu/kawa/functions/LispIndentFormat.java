package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispIndentFormat extends ReportFormat {
    int columns;
    boolean current;

    LispIndentFormat() {
    }

    public static LispIndentFormat getInstance(int columns2, boolean current2) {
        LispIndentFormat lispIndentFormat;
        new LispIndentFormat();
        LispIndentFormat fmt = lispIndentFormat;
        fmt.columns = columns2;
        fmt.current = current2;
        return fmt;
    }

    public int format(Object[] args, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        int start = i;
        Writer dst = writer;
        FieldPosition fieldPosition2 = fieldPosition;
        int columns2 = getParam(this.columns, 0, args, start);
        if (this.columns == -1610612736) {
            start++;
        }
        if (dst instanceof OutPort) {
            ((OutPort) dst).setIndentation(columns2, this.current);
        }
        return start;
    }
}
