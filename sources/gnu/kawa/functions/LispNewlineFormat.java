package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispNewlineFormat extends ReportFormat {
    static final String line_separator = System.getProperty("line.separator", "\n");
    int count;
    int kind;

    LispNewlineFormat() {
    }

    public static LispNewlineFormat getInstance(int count2, int kind2) {
        LispNewlineFormat lispNewlineFormat;
        new LispNewlineFormat();
        LispNewlineFormat fmt = lispNewlineFormat;
        fmt.count = count2;
        fmt.kind = kind2;
        return fmt;
    }

    public int format(Object[] args, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        int start = i;
        Writer dst = writer;
        FieldPosition fieldPosition2 = fieldPosition;
        int count2 = getParam(this.count, 1, args, start);
        if (this.count == -1610612736) {
            start++;
        }
        while (true) {
            count2--;
            if (count2 < 0) {
                return start;
            }
            printNewline(this.kind, dst);
        }
    }

    public static void printNewline(int i, Writer writer) throws IOException {
        int kind2 = i;
        Writer dst = writer;
        if ((dst instanceof OutPort) && kind2 != 76) {
            ((OutPort) dst).writeBreak(kind2);
        } else if (dst instanceof PrintWriter) {
            ((PrintWriter) dst).println();
        } else {
            dst.write(line_separator);
        }
    }
}
