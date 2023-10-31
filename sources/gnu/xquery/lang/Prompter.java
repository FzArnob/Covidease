package gnu.xquery.lang;

import gnu.mapping.InPort;
import gnu.mapping.Procedure1;

/* compiled from: XQuery */
class Prompter extends Procedure1 {
    Prompter() {
    }

    public Object apply1(Object arg) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        InPort port = (InPort) arg;
        int line = port.getLineNumber() + 1;
        char state = port.readState;
        if (state == 10) {
            state = ' ';
        }
        if (state == '<') {
            new StringBuilder();
            return sb3.append("<!--").append(line).append("-->").toString();
        } else if (state == ':') {
            new StringBuilder();
            return sb2.append("-(:").append(line).append("c:) ").toString();
        } else {
            new StringBuilder();
            return sb.append("(: ").append(line).append(state).append(":) ").toString();
        }
    }
}
