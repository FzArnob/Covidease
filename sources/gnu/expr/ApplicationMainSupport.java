package gnu.expr;

import gnu.lists.FString;
import gnu.lists.FVector;
import gnu.mapping.Environment;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import java.io.PrintStream;

public class ApplicationMainSupport {
    public static String[] commandLineArgArray;
    public static FVector commandLineArguments;
    public static boolean processCommandLinePropertyAssignments;
    static String[][] propertyFields;

    public ApplicationMainSupport() {
    }

    public static void processSetProperties() {
        String[] args = commandLineArgArray;
        if (args == null) {
            processCommandLinePropertyAssignments = true;
            return;
        }
        int iarg = 0;
        while (iarg < args.length && processSetProperty(args[iarg])) {
            iarg++;
        }
        if (iarg != 0) {
            setArgs(args, iarg);
        }
    }

    public static void processArgs(String[] strArr) {
        String[] args = strArr;
        int iarg = 0;
        if (processCommandLinePropertyAssignments) {
            while (iarg < args.length && processSetProperty(args[iarg])) {
                iarg++;
            }
        }
        setArgs(args, iarg);
    }

    public static void setArgs(String[] strArr, int i) {
        FVector fVector;
        Object obj;
        String[] args = strArr;
        int arg_start = i;
        int nargs = args.length - arg_start;
        Object[] array = new Object[nargs];
        if (arg_start == 0) {
            commandLineArgArray = args;
        } else {
            String[] strings = new String[nargs];
            int i2 = nargs;
            while (true) {
                i2--;
                if (i2 < 0) {
                    break;
                }
                strings[i2] = args[i2 + arg_start];
            }
            commandLineArgArray = strings;
        }
        int i3 = nargs;
        while (true) {
            i3--;
            if (i3 >= 0) {
                new FString(args[i3 + arg_start]);
                array[i3] = obj;
            } else {
                new FVector(array);
                commandLineArguments = fVector;
                Object put = Environment.getCurrent().put("command-line-arguments", (Object) commandLineArguments);
                return;
            }
        }
    }

    public static boolean processSetProperty(String str) {
        StringBuilder sb;
        String arg = str;
        int ci = arg.indexOf(61);
        if (ci <= 0) {
            return false;
        }
        String key = arg.substring(0, ci);
        String value = arg.substring(ci + 1);
        int i = 0;
        while (true) {
            String[] propertyField = propertyFields[i];
            if (propertyField == null) {
                break;
            } else if (key.equals(propertyField[0])) {
                String cname = propertyField[1];
                String fname = propertyField[2];
                try {
                    ((ThreadLocation) Class.forName(cname).getDeclaredField(fname).get((Object) null)).setGlobal(value);
                    break;
                } catch (Throwable th) {
                    Throwable ex = th;
                    PrintStream printStream = System.err;
                    new StringBuilder();
                    printStream.println(sb.append("error setting property ").append(key).append(" field ").append(cname).append('.').append(fname).append(": ").append(ex).toString());
                    System.exit(-1);
                }
            } else {
                i++;
            }
        }
        Symbol symbol = Symbol.parse(key);
        Language defaultLanguage = Language.getDefaultLanguage();
        Environment.getCurrent().define(symbol, (Object) null, value);
        return true;
    }

    static {
        String[][] strArr = new String[10][];
        String[][] strArr2 = strArr;
        String[][] strArr3 = strArr;
        String[] strArr4 = new String[3];
        strArr4[0] = "out:doctype-system";
        String[] strArr5 = strArr4;
        strArr5[1] = "gnu.xml.XMLPrinter";
        String[] strArr6 = strArr5;
        strArr6[2] = "doctypeSystem";
        strArr3[0] = strArr6;
        String[][] strArr7 = strArr2;
        String[][] strArr8 = strArr7;
        String[][] strArr9 = strArr7;
        String[] strArr10 = new String[3];
        strArr10[0] = "out:doctype-public";
        String[] strArr11 = strArr10;
        strArr11[1] = "gnu.xml.XMLPrinter";
        String[] strArr12 = strArr11;
        strArr12[2] = "doctypePublic";
        strArr9[1] = strArr12;
        String[][] strArr13 = strArr8;
        String[][] strArr14 = strArr13;
        String[][] strArr15 = strArr13;
        String[] strArr16 = new String[3];
        strArr16[0] = "out:base";
        String[] strArr17 = strArr16;
        strArr17[1] = "gnu.kawa.functions.DisplayFormat";
        String[] strArr18 = strArr17;
        strArr18[2] = "outBase";
        strArr15[2] = strArr18;
        String[][] strArr19 = strArr14;
        String[][] strArr20 = strArr19;
        String[][] strArr21 = strArr19;
        String[] strArr22 = new String[3];
        strArr22[0] = "out:radix";
        String[] strArr23 = strArr22;
        strArr23[1] = "gnu.kawa.functions.DisplayFormat";
        String[] strArr24 = strArr23;
        strArr24[2] = "outRadix";
        strArr21[3] = strArr24;
        String[][] strArr25 = strArr20;
        String[][] strArr26 = strArr25;
        String[][] strArr27 = strArr25;
        String[] strArr28 = new String[3];
        strArr28[0] = "out:line-length";
        String[] strArr29 = strArr28;
        strArr29[1] = "gnu.text.PrettyWriter";
        String[] strArr30 = strArr29;
        strArr30[2] = "lineLengthLoc";
        strArr27[4] = strArr30;
        String[][] strArr31 = strArr26;
        String[][] strArr32 = strArr31;
        String[][] strArr33 = strArr31;
        String[] strArr34 = new String[3];
        strArr34[0] = "out:right-margin";
        String[] strArr35 = strArr34;
        strArr35[1] = "gnu.text.PrettyWriter";
        String[] strArr36 = strArr35;
        strArr36[2] = "lineLengthLoc";
        strArr33[5] = strArr36;
        String[][] strArr37 = strArr32;
        String[][] strArr38 = strArr37;
        String[][] strArr39 = strArr37;
        String[] strArr40 = new String[3];
        strArr40[0] = "out:miser-width";
        String[] strArr41 = strArr40;
        strArr41[1] = "gnu.text.PrettyWriter";
        String[] strArr42 = strArr41;
        strArr42[2] = "miserWidthLoc";
        strArr39[6] = strArr42;
        String[][] strArr43 = strArr38;
        String[][] strArr44 = strArr43;
        String[][] strArr45 = strArr43;
        String[] strArr46 = new String[3];
        strArr46[0] = "out:xml-indent";
        String[] strArr47 = strArr46;
        strArr47[1] = "gnu.xml.XMLPrinter";
        String[] strArr48 = strArr47;
        strArr48[2] = "indentLoc";
        strArr45[7] = strArr48;
        String[][] strArr49 = strArr44;
        String[][] strArr50 = strArr49;
        String[][] strArr51 = strArr49;
        String[] strArr52 = new String[3];
        strArr52[0] = "display:toolkit";
        String[] strArr53 = strArr52;
        strArr53[1] = "gnu.kawa.models.Display";
        String[] strArr54 = strArr53;
        strArr54[2] = "myDisplay";
        strArr51[8] = strArr54;
        String[][] strArr55 = strArr50;
        strArr55[9] = null;
        propertyFields = strArr55;
    }
}
