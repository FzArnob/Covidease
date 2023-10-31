package gnu.kawa.util;

import java.io.File;
import java.io.PrintStream;

public class FixupHtmlToc {
    static FileInfo[] argFiles;

    public FixupHtmlToc() {
    }

    public static void main(String[] strArr) {
        StringBuilder sb;
        File file;
        String[] args = strArr;
        try {
            argFiles = new FileInfo[args.length];
            for (int i = 0; i < args.length; i++) {
                new File(args[i]);
                FileInfo info = FileInfo.find(file);
                info.writeNeeded = true;
                argFiles[i] = info;
            }
            for (int i2 = 0; i2 < args.length; i2++) {
                argFiles[i2].scan();
                argFiles[i2].write();
            }
        } catch (Throwable th) {
            Throwable ex = th;
            PrintStream printStream = System.err;
            new StringBuilder();
            printStream.println(sb.append("caught ").append(ex).toString());
            ex.printStackTrace();
        }
    }
}
