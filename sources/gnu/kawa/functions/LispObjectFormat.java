package gnu.kawa.functions;

import gnu.text.PadFormat;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispObjectFormat extends ReportFormat {
    ReportFormat base;
    int colInc;
    int minPad;
    int minWidth;
    int padChar;
    int where;

    public LispObjectFormat(ReportFormat base2, int minWidth2, int colInc2, int minPad2, int padChar2, int where2) {
        this.base = base2;
        this.minWidth = minWidth2;
        this.colInc = colInc2;
        this.minPad = minPad2;
        this.padChar = padChar2;
        this.where = where2;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        Object[] args = objArr;
        int start = i;
        Writer dst = writer;
        FieldPosition fpos = fieldPosition;
        int minWidth2 = getParam(this.minWidth, 0, args, start);
        if (this.minWidth == -1610612736) {
            start++;
        }
        int colInc2 = getParam(this.colInc, 1, args, start);
        if (this.colInc == -1610612736) {
            start++;
        }
        int minPad2 = getParam(this.minPad, 0, args, start);
        if (this.minPad == -1610612736) {
            start++;
        }
        char padChar2 = getParam(this.padChar, ' ', args, start);
        if (this.padChar == -1610612736) {
            start++;
        }
        return PadFormat.format(this.base, args, start, dst, padChar2, minWidth2, colInc2, minPad2, this.where, fpos);
    }
}
