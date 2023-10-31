package gnu.ecmascript;

import gnu.mapping.InPort;
import gnu.mapping.Procedure1;

class Prompter extends Procedure1 {
    Prompter() {
    }

    /* access modifiers changed from: package-private */
    public String prompt(InPort port) {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("(EcmaScript:").append(port.getLineNumber() + 1).append(") ").toString();
    }

    public Object apply1(Object arg) {
        return prompt((InPort) arg);
    }
}
