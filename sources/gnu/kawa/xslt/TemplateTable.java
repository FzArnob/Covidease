package gnu.kawa.xslt;

import gnu.mapping.Procedure;
import gnu.mapping.Symbol;

public class TemplateTable {
    static final TemplateTable nullModeTable;
    TemplateEntry entries;
    Symbol name;

    static {
        TemplateTable templateTable;
        new TemplateTable(XSLT.nullMode);
        nullModeTable = templateTable;
    }

    public TemplateTable(Symbol mode) {
        this.name = mode;
    }

    static TemplateTable getTemplateTable(Symbol name2) {
        if (name2 == XSLT.nullMode) {
            return nullModeTable;
        }
        return null;
    }

    public void enter(String pattern, double priority, Procedure procedure) {
        TemplateEntry templateEntry;
        new TemplateEntry();
        TemplateEntry entry = templateEntry;
        entry.procedure = procedure;
        entry.priority = priority;
        entry.pattern = pattern;
        entry.next = this.entries;
        this.entries = entry;
    }

    public Procedure find(String str) {
        String name2 = str;
        TemplateEntry templateEntry = this.entries;
        while (true) {
            TemplateEntry entry = templateEntry;
            if (entry == null) {
                return null;
            }
            if (entry.pattern.equals(name2)) {
                return entry.procedure;
            }
            templateEntry = entry.next;
        }
    }
}
