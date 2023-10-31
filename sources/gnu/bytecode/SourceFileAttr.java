package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class SourceFileAttr extends Attribute {
    String filename;
    int filename_index;

    public String getSourceFile() {
        return this.filename;
    }

    public void setSourceFile(String filename2) {
        this.filename = filename2;
        this.filename_index = 0;
    }

    public static String fixSourceFile(String str) {
        char fsep0;
        String fname = str;
        String fsep = System.getProperty("file.separator", "/");
        if (!(fsep == null || fsep.length() != 1 || (fsep0 = fsep.charAt(0)) == '/')) {
            fname = fname.replace(fsep0, '/');
        }
        return fname;
    }

    public static void setSourceFile(ClassType classType, String str) {
        SourceFileAttr sattr;
        ClassType cl = classType;
        String filename2 = str;
        Attribute attr = Attribute.get(cl, "SourceFile");
        if (attr == null || !(attr instanceof SourceFileAttr)) {
            new SourceFileAttr(filename2);
            sattr.addToFrontOf(cl);
            return;
        }
        ((SourceFileAttr) attr).setSourceFile(filename2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SourceFileAttr(String filename2) {
        super("SourceFile");
        this.filename = filename2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SourceFileAttr(int i, ClassType ctype) {
        super("SourceFile");
        int index = i;
        this.filename = ((CpoolUtf8) ctype.constants.getForced(index, 1)).string;
        this.filename_index = index;
    }

    public void assignConstants(ClassType classType) {
        ClassType cl = classType;
        super.assignConstants(cl);
        if (this.filename_index == 0) {
            this.filename_index = cl.getConstants().addUtf8(this.filename).getIndex();
        }
    }

    public final int getLength() {
        return 2;
    }

    public void write(DataOutputStream dstr) throws IOException {
        dstr.writeShort(this.filename_index);
    }

    public void print(ClassTypeWriter classTypeWriter) {
        ClassTypeWriter dst = classTypeWriter;
        dst.print("Attribute \"");
        dst.print(getName());
        dst.print("\", length:");
        dst.print(getLength());
        dst.print(", ");
        dst.printOptionalIndex(this.filename_index);
        dst.print('\"');
        dst.print(getSourceFile());
        dst.println('\"');
    }
}
