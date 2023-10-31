package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

public class FlushFormat extends ReportFormat {
    private static FlushFormat flush;

    public FlushFormat() {
    }

    public static FlushFormat getInstance() {
        FlushFormat flushFormat;
        if (flush == null) {
            new FlushFormat();
            flush = flushFormat;
        }
        return flush;
    }

    public int format(Object[] objArr, int start, Writer dst, FieldPosition fieldPosition) throws IOException {
        Object[] objArr2 = objArr;
        FieldPosition fieldPosition2 = fieldPosition;
        dst.flush();
        return start;
    }
}
