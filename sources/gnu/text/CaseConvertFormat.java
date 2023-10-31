package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

public class CaseConvertFormat extends ReportFormat {
    Format baseFormat;
    char code;

    public CaseConvertFormat(Format baseFormat2, char action) {
        this.baseFormat = baseFormat2;
        this.code = action;
    }

    public Format getBaseFormat() {
        return this.baseFormat;
    }

    public void setBaseFormat(Format baseFormat2) {
        Format format = baseFormat2;
        this.baseFormat = format;
    }

    public int format(Object[] args, int start, Writer writer, FieldPosition fpos) throws IOException {
        StringBuffer stringBuffer;
        char titleCase;
        Writer dst = writer;
        new StringBuffer(100);
        StringBuffer sbuf = stringBuffer;
        int result = format(this.baseFormat, args, start, sbuf, fpos);
        int len = sbuf.length();
        char prev = ' ';
        for (int i = 0; i < len; i++) {
            char ch = sbuf.charAt(i);
            if (this.code == 'U') {
                titleCase = Character.toUpperCase(ch);
            } else if (!(this.code == 'T' && i == 0) && (this.code != 'C' || Character.isLetterOrDigit(prev))) {
                titleCase = Character.toLowerCase(ch);
            } else {
                titleCase = Character.toTitleCase(ch);
            }
            char ch2 = titleCase;
            prev = ch2;
            dst.write(ch2);
        }
        return result;
    }
}
