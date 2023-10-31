package gnu.mapping;

import gnu.text.SourceLocator;

public class UnboundLocationException extends RuntimeException {
    int column;
    String filename;
    int line;
    Location location;
    public Object symbol;

    public UnboundLocationException() {
    }

    public UnboundLocationException(Object symbol2) {
        this.symbol = symbol2;
    }

    public UnboundLocationException(Object symbol2, String filename2, int line2, int column2) {
        this.symbol = symbol2;
        this.filename = filename2;
        this.line = line2;
        this.column = column2;
    }

    public UnboundLocationException(Object symbol2, SourceLocator sourceLocator) {
        SourceLocator location2 = sourceLocator;
        this.symbol = symbol2;
        if (location2 != null) {
            this.filename = location2.getFileName();
            this.line = location2.getLineNumber();
            this.column = location2.getColumnNumber();
        }
    }

    public UnboundLocationException(Location loc) {
        this.location = loc;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnboundLocationException(Object symbol2, String message) {
        super(message);
        this.symbol = symbol2;
    }

    public void setLine(String filename2, int line2, int column2) {
        this.filename = filename2;
        this.line = line2;
        this.column = column2;
    }

    public String getMessage() {
        StringBuffer stringBuffer;
        Symbol keySymbol;
        String msg = super.getMessage();
        if (msg != null) {
            return msg;
        }
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        if (this.filename != null || this.line > 0) {
            if (this.filename != null) {
                StringBuffer append = sbuf.append(this.filename);
            }
            if (this.line >= 0) {
                StringBuffer append2 = sbuf.append(':');
                StringBuffer append3 = sbuf.append(this.line);
                if (this.column > 0) {
                    StringBuffer append4 = sbuf.append(':');
                    StringBuffer append5 = sbuf.append(this.column);
                }
            }
            StringBuffer append6 = sbuf.append(": ");
        }
        if (this.location == null) {
            keySymbol = null;
        } else {
            keySymbol = this.location.getKeySymbol();
        }
        Symbol name = keySymbol;
        if (name != null) {
            StringBuffer append7 = sbuf.append("unbound location ");
            StringBuffer append8 = sbuf.append(name);
            Object property = this.location.getKeyProperty();
            if (property != null) {
                StringBuffer append9 = sbuf.append(" (property ");
                StringBuffer append10 = sbuf.append(property);
                StringBuffer append11 = sbuf.append(')');
            }
        } else if (this.symbol != null) {
            StringBuffer append12 = sbuf.append("unbound location ");
            StringBuffer append13 = sbuf.append(this.symbol);
        } else {
            StringBuffer append14 = sbuf.append("unbound location");
        }
        return sbuf.toString();
    }

    public String toString() {
        return getMessage();
    }
}
