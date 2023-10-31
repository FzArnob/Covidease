package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class SourceDebugExtAttr extends Attribute {
    int curFileIndex = -1;
    String curFileName;
    int curLineIndex = -1;
    byte[] data;
    private String defaultStratumId;
    int dlength;
    int fileCount;
    int[] fileIDs;
    String[] fileNames;
    int lineCount;
    int[] lines;
    int maxFileID;
    private String outputFileName;

    private int fixLine(int i, int i2) {
        int sourceLine = i;
        int index = i2;
        int sourceMin = this.lines[index];
        int repeat = this.lines[index + 2];
        if (sourceLine < sourceMin) {
            if (index > 0) {
                return -1;
            }
            this.lines[index] = sourceLine;
            this.lines[index + 2] = (((sourceMin + repeat) - 1) - sourceLine) + 1;
            this.lines[index + 3] = sourceLine;
            sourceMin = sourceLine;
        }
        int delta = this.lines[index + 3] - sourceMin;
        if (sourceLine < sourceMin + repeat) {
            return sourceLine + delta;
        }
        if (index != 5 * (this.lineCount - 1) && (index != 0 || sourceLine >= this.lines[8])) {
            return -1;
        }
        this.lines[index + 2] = (sourceLine - sourceMin) + 1;
        return sourceLine + delta;
    }

    /* access modifiers changed from: package-private */
    public int fixLine(int i) {
        int outputStartLine;
        int outLine;
        int outLine2;
        int sourceLine = i;
        if (this.curLineIndex >= 0 && (outLine2 = fixLine(sourceLine, this.curLineIndex)) >= 0) {
            return outLine2;
        }
        int i5 = 0;
        int findex = this.curFileIndex;
        int i2 = 0;
        while (i2 < this.lineCount) {
            if (i5 == this.curLineIndex || findex != this.lines[i5 + 1] || (outLine = fixLine(sourceLine, i5)) < 0) {
                i5 += 5;
                i2++;
            } else {
                this.curLineIndex = i5;
                return outLine;
            }
        }
        if (this.lines == null) {
            this.lines = new int[20];
        } else if (i5 >= this.lines.length) {
            int[] newLines = new int[(2 * i5)];
            System.arraycopy(this.lines, 0, newLines, 0, i5);
            this.lines = newLines;
        }
        int inputStartLine = sourceLine;
        if (i5 == 0) {
            outputStartLine = sourceLine;
        } else {
            outputStartLine = this.lines[(i5 - 5) + 3] + this.lines[(i5 - 5) + 2];
            if (i5 == 5 && outputStartLine < 10000) {
                outputStartLine = 10000;
            }
            sourceLine = outputStartLine;
        }
        this.lines[i5] = inputStartLine;
        this.lines[i5 + 1] = findex;
        this.lines[i5 + 2] = 1;
        this.lines[i5 + 3] = outputStartLine;
        this.lines[i5 + 4] = 1;
        this.curLineIndex = i5;
        this.lineCount++;
        return sourceLine;
    }

    /* access modifiers changed from: package-private */
    public void addFile(String str) {
        String fentry;
        StringBuilder sb;
        String fname = str;
        if (this.curFileName == fname) {
            return;
        }
        if (fname == null || !fname.equals(this.curFileName)) {
            this.curFileName = fname;
            String fname2 = SourceFileAttr.fixSourceFile(fname);
            int slash = fname2.lastIndexOf(47);
            if (slash >= 0) {
                String fpath = fname2;
                fname2 = fname2.substring(slash + 1);
                new StringBuilder();
                fentry = sb.append(fname2).append(10).append(fpath).toString();
            } else {
                fentry = fname2;
            }
            if (this.curFileIndex < 0 || !fentry.equals(this.fileNames[this.curFileIndex])) {
                int n = this.fileCount;
                int i = 0;
                while (i < n) {
                    if (i == this.curFileIndex || !fentry.equals(this.fileNames[i])) {
                        i++;
                    } else {
                        this.curFileIndex = i;
                        this.curLineIndex = -1;
                        return;
                    }
                }
                if (this.fileIDs == null) {
                    this.fileIDs = new int[5];
                    this.fileNames = new String[5];
                } else if (n >= this.fileIDs.length) {
                    int[] newIDs = new int[(2 * n)];
                    String[] newNames = new String[(2 * n)];
                    System.arraycopy(this.fileIDs, 0, newIDs, 0, n);
                    System.arraycopy(this.fileNames, 0, newNames, 0, n);
                    this.fileIDs = newIDs;
                    this.fileNames = newNames;
                }
                this.fileCount++;
                int id = this.maxFileID + 1;
                int i2 = id;
                this.maxFileID = i2;
                int id2 = id << 1;
                if (slash >= 0) {
                    id2++;
                }
                this.fileNames[n] = fentry;
                if (this.outputFileName == null) {
                    this.outputFileName = fname2;
                }
                this.fileIDs[n] = id2;
                this.curFileIndex = n;
                this.curLineIndex = -1;
            }
        }
    }

    public void addStratum(String name) {
        String str = name;
        this.defaultStratumId = str;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SourceDebugExtAttr(ClassType cl) {
        super("SourceDebugExtension");
        addToFrontOf(cl);
    }

    /* access modifiers changed from: package-private */
    public void nonAsteriskString(String str, StringBuffer stringBuffer) {
        String str2 = str;
        StringBuffer sbuf = stringBuffer;
        if (str2 == null || str2.length() == 0 || str2.charAt(0) == '*') {
            StringBuffer append = sbuf.append(' ');
        }
        StringBuffer append2 = sbuf.append(str2);
    }

    public void assignConstants(ClassType cl) {
        StringBuffer stringBuffer;
        String str;
        Throwable th;
        super.assignConstants(cl);
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append("SMAP\n");
        nonAsteriskString(this.outputFileName, sbuf);
        StringBuffer append2 = sbuf.append(10);
        if (this.defaultStratumId == null) {
            str = "Java";
        } else {
            str = this.defaultStratumId;
        }
        String stratum = str;
        nonAsteriskString(stratum, sbuf);
        StringBuffer append3 = sbuf.append(10);
        StringBuffer append4 = sbuf.append("*S ");
        StringBuffer append5 = sbuf.append(stratum);
        StringBuffer append6 = sbuf.append(10);
        StringBuffer append7 = sbuf.append("*F\n");
        for (int i = 0; i < this.fileCount; i++) {
            int id = this.fileIDs[i];
            boolean with_path = (id & 1) != 0;
            int id2 = id >> 1;
            if (with_path) {
                StringBuffer append8 = sbuf.append("+ ");
            }
            StringBuffer append9 = sbuf.append(id2);
            StringBuffer append10 = sbuf.append(' ');
            StringBuffer append11 = sbuf.append(this.fileNames[i]);
            StringBuffer append12 = sbuf.append(10);
        }
        if (this.lineCount > 0) {
            int prevFileID = 0;
            StringBuffer append13 = sbuf.append("*L\n");
            int i2 = 0;
            int i5 = 0;
            do {
                int inputStartLine = this.lines[i5];
                int lineFileID = this.fileIDs[this.lines[i5 + 1]] >> 1;
                int repeatCount = this.lines[i5 + 2];
                int outputStartLine = this.lines[i5 + 3];
                int outputLineIncrement = this.lines[i5 + 4];
                StringBuffer append14 = sbuf.append(inputStartLine);
                if (lineFileID != prevFileID) {
                    StringBuffer append15 = sbuf.append('#');
                    StringBuffer append16 = sbuf.append(lineFileID);
                    prevFileID = lineFileID;
                }
                if (repeatCount != 1) {
                    StringBuffer append17 = sbuf.append(',');
                    StringBuffer append18 = sbuf.append(repeatCount);
                }
                StringBuffer append19 = sbuf.append(':');
                StringBuffer append20 = sbuf.append(outputStartLine);
                if (outputLineIncrement != 1) {
                    StringBuffer append21 = sbuf.append(',');
                    StringBuffer append22 = sbuf.append(outputLineIncrement);
                }
                StringBuffer append23 = sbuf.append(10);
                i5 += 5;
                i2++;
            } while (i2 < this.lineCount);
        }
        StringBuffer append24 = sbuf.append("*E\n");
        try {
            this.data = sbuf.toString().getBytes("UTF-8");
            this.dlength = this.data.length;
        } catch (Exception e) {
            Exception ex = e;
            Throwable th2 = th;
            new RuntimeException(ex.toString());
            throw th2;
        }
    }

    public int getLength() {
        return this.dlength;
    }

    public void write(DataOutputStream dstr) throws IOException {
        dstr.write(this.data, 0, this.dlength);
    }

    public void print(ClassTypeWriter classTypeWriter) {
        String str;
        ClassTypeWriter dst = classTypeWriter;
        dst.print("Attribute \"");
        dst.print(getName());
        dst.print("\", length:");
        dst.println(this.dlength);
        ClassTypeWriter classTypeWriter2 = dst;
        try {
            new String(this.data, 0, this.dlength, "UTF-8");
            classTypeWriter2.print(str);
        } catch (Exception e) {
            dst.print("(Caught ");
            dst.print(e);
            dst.println(')');
        }
        if (this.dlength > 0 && this.data[this.dlength - 1] != 13 && this.data[this.dlength - 1] != 10) {
            dst.println();
        }
    }
}
