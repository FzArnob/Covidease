package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispFreshlineFormat extends ReportFormat {
    int count;

    public LispFreshlineFormat(int count2) {
        this.count = count2;
    }

    public int format(Object[] args, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        int start = i;
        Writer dst = writer;
        FieldPosition fieldPosition2 = fieldPosition;
        int count2 = getParam(this.count, 1, args, start);
        if (this.count == -1610612736) {
            start++;
        }
        if (count2 > 0) {
            if (dst instanceof OutPort) {
                ((OutPort) dst).freshLine();
                count2--;
            }
            while (true) {
                count2--;
                if (count2 < 0) {
                    break;
                }
                dst.write(10);
            }
        }
        return start;
    }
}
