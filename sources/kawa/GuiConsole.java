package kawa;

import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import kawa.ReplDocument;
import org.shaded.apache.http.HttpStatus;
import org.shaded.apache.http.protocol.HTTP;

public class GuiConsole extends JFrame implements ActionListener, ReplDocument.DocumentCloseListener {
    private static String CLOSE = HTTP.CONN_CLOSE;
    private static String EXIT = "Exit";
    private static String NEW = "New";
    private static String NEW_SHARED = "New (Shared)";
    private static String PURGE_MESSAGE = "Purge Buffer";
    static int window_number = 0;
    ReplDocument document;
    ReplPane pane;

    public static void main(String[] strArr) {
        Object obj;
        String[] args = strArr;
        repl.noConsole = false;
        int iArg = repl.processArgs(args, 0, args.length);
        repl.getLanguage();
        repl.setArgs(args, iArg);
        repl.checkInitFile();
        Object obj2 = obj;
        new GuiConsole();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GuiConsole() {
        this(Language.getDefaultLanguage(), Environment.getCurrent(), false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GuiConsole(ReplDocument doc) {
        super("Kawa");
        init(doc);
    }

    /* access modifiers changed from: package-private */
    public void init(ReplDocument doc) {
        ReplPane replPane;
        LayoutManager layoutManager;
        Component component;
        this.document = doc;
        this.document.addDocumentCloseListener(this);
        new ReplPane(this.document);
        this.pane = replPane;
        window_number++;
        new BorderLayout(0, 0);
        setLayout(layoutManager);
        new JScrollPane(this.pane);
        Component add = add("Center", component);
        setupMenus();
        setLocation(100 * window_number, 50 * window_number);
        setSize(700, HttpStatus.SC_INTERNAL_SERVER_ERROR);
        setVisible(true);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GuiConsole(Language language, Environment penvironment, boolean shared) {
        super("Kawa");
        ReplDocument replDocument;
        repl.getLanguage();
        new ReplDocument(language, penvironment, shared);
        init(replDocument);
    }

    public void closed(ReplDocument replDocument) {
        ReplDocument replDocument2 = replDocument;
        close();
    }

    /* access modifiers changed from: package-private */
    public void close() {
        this.document.removeDocumentCloseListener(this);
        dispose();
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setupMenus() {
        /*
            r10 = this;
            r0 = r10
            kawa.GuiConsole$1 r6 = new kawa.GuiConsole$1
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = r0
            r7.<init>(r8)
            r5 = r6
            java.awt.MenuBar r6 = new java.awt.MenuBar
            r9 = r6
            r6 = r9
            r7 = r9
            r7.<init>()
            r1 = r6
            java.awt.Menu r6 = new java.awt.Menu
            r9 = r6
            r6 = r9
            r7 = r9
            java.lang.String r8 = "File"
            r7.<init>(r8)
            r2 = r6
            java.awt.Menu r6 = new java.awt.Menu
            r9 = r6
            r6 = r9
            r7 = r9
            java.lang.String r8 = "Utilities"
            r7.<init>(r8)
            r3 = r6
            r6 = r1
            r7 = r2
            java.awt.Menu r6 = r6.add(r7)
            r6 = r1
            r7 = r3
            java.awt.Menu r6 = r6.add(r7)
            java.awt.MenuItem r6 = new java.awt.MenuItem
            r9 = r6
            r6 = r9
            r7 = r9
            java.lang.String r8 = NEW
            r7.<init>(r8)
            r4 = r6
            r6 = r4
            r7 = r0
            r6.addActionListener(r7)
            r6 = r2
            r7 = r4
            java.awt.MenuItem r6 = r6.add(r7)
            java.awt.MenuItem r6 = new java.awt.MenuItem
            r9 = r6
            r6 = r9
            r7 = r9
            java.lang.String r8 = NEW_SHARED
            r7.<init>(r8)
            r4 = r6
            r6 = r4
            r7 = r0
            r6.addActionListener(r7)
            r6 = r2
            r7 = r4
            java.awt.MenuItem r6 = r6.add(r7)
            java.awt.MenuItem r6 = new java.awt.MenuItem
            r9 = r6
            r6 = r9
            r7 = r9
            java.lang.String r8 = CLOSE
            r7.<init>(r8)
            r4 = r6
            r6 = r4
            r7 = r0
            r6.addActionListener(r7)
            r6 = r2
            r7 = r4
            java.awt.MenuItem r6 = r6.add(r7)
            java.awt.MenuItem r6 = new java.awt.MenuItem
            r9 = r6
            r6 = r9
            r7 = r9
            java.lang.String r8 = EXIT
            r7.<init>(r8)
            r4 = r6
            r6 = r4
            r7 = r0
            r6.addActionListener(r7)
            r6 = r0
            r7 = r5
            r6.addWindowListener(r7)
            r6 = r2
            r7 = r4
            java.awt.MenuItem r6 = r6.add(r7)
            java.awt.MenuItem r6 = new java.awt.MenuItem
            r9 = r6
            r6 = r9
            r7 = r9
            java.lang.String r8 = PURGE_MESSAGE
            r7.<init>(r8)
            r4 = r6
            r6 = r4
            r7 = r0
            r6.addActionListener(r7)
            r6 = r3
            r7 = r4
            java.awt.MenuItem r6 = r6.add(r7)
            r6 = r0
            r7 = r1
            r6.setMenuBar(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.GuiConsole.setupMenus():void");
    }

    public void actionPerformed(ActionEvent e) {
        StringBuilder sb;
        Object obj;
        Object obj2;
        String cmd = e.getActionCommand();
        if (cmd.equals(NEW)) {
            Object obj3 = obj2;
            new GuiConsole(this.document.language, Environment.getGlobal(), false);
        } else if (cmd.equals(NEW_SHARED)) {
            Object obj4 = obj;
            new GuiConsole(this.document.language, this.document.environment, true);
        } else if (cmd.equals(EXIT)) {
            System.exit(0);
        } else if (cmd.equals(CLOSE)) {
            close();
        } else if (cmd.equals(PURGE_MESSAGE)) {
            this.pane.document.deleteOldText();
        } else {
            OutPort outDefault = OutPort.outDefault();
            new StringBuilder();
            outDefault.println(sb.append("Unknown menu action: ").append(cmd).toString());
        }
    }
}
