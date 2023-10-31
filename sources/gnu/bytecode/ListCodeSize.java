package gnu.bytecode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class ListCodeSize {
    public ListCodeSize() {
    }

    public static void usage() {
        System.err.println("Usage: class methodname ...");
        System.exit(-1);
    }

    static void print(Method method) {
        Method method2 = method;
        System.out.print(method2);
        CodeAttr code = method2.getCode();
        if (code == null) {
            System.out.print(": no code");
        } else {
            System.out.print(": ");
            System.out.print(code.getPC());
            System.out.print(" bytes");
        }
        System.out.println();
    }

    public static final void main(String[] strArr) {
        StringBuilder sb;
        InputStream inp;
        ClassType classType;
        Object obj;
        StringBuffer stringBuffer;
        String[] args = strArr;
        if (args.length == 0) {
            usage();
        }
        String filename = args[0];
        try {
            new FileInputStream(filename);
            new ClassType();
            ClassType ctype = classType;
            Object obj2 = obj;
            new ClassFileInput(ctype, inp);
            if (args.length == 1) {
                for (Method method = ctype.getMethods(); method != null; method = method.getNext()) {
                    print(method);
                }
                return;
            }
            for (int i = 1; i < args.length; i++) {
                Method methods = ctype.getMethods();
                while (true) {
                    Method method2 = methods;
                    if (method2 == null) {
                        break;
                    }
                    new StringBuffer();
                    StringBuffer sbuf = stringBuffer;
                    StringBuffer append = sbuf.append(method2.getName());
                    method2.listParameters(sbuf);
                    StringBuffer append2 = sbuf.append(method2.getReturnType().getName());
                    if (sbuf.toString().startsWith(args[i])) {
                        print(method2);
                    }
                    methods = method2.getNext();
                }
            }
        } catch (FileNotFoundException e) {
            FileNotFoundException fileNotFoundException = e;
            PrintStream printStream = System.err;
            new StringBuilder();
            printStream.println(sb.append("File ").append(filename).append(" not found").toString());
            System.exit(-1);
        } catch (IOException e2) {
            IOException e3 = e2;
            System.err.println(e3);
            e3.printStackTrace();
            System.exit(-1);
        }
    }
}
