package gnu.kawa.functions;

import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispRepositionFormat extends ReportFormat {
    boolean absolute;
    boolean backwards;
    int count;

    public LispRepositionFormat(int count2, boolean backwards2, boolean absolute2) {
        this.count = count2;
        this.backwards = backwards2;
        this.absolute = absolute2;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        Object[] args = objArr;
        int start = i;
        Writer writer2 = writer;
        FieldPosition fieldPosition2 = fieldPosition;
        int count2 = getParam(this.count, this.absolute ? 0 : 1, args, start);
        if (!this.absolute) {
            if (this.backwards) {
                count2 = -count2;
            }
            count2 += start;
        }
        return count2 < 0 ? 0 : count2 > args.length ? args.length : count2;
    }
}
