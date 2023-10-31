package kawa;

import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.Future;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure0;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.text.FilePath;
import gnu.text.Path;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TelnetRepl extends Procedure0 {
    Language language;
    Socket socket;

    public TelnetRepl(Language language2, Socket socket2) {
        this.language = language2;
        this.socket = socket2;
    }

    /* JADX INFO: finally extract failed */
    public Object apply0() {
        try {
            boolean run = Shell.run(this.language, Environment.getCurrent());
            Values values = Values.empty;
            try {
                this.socket.close();
            } catch (IOException e) {
                IOException iOException = e;
            }
            return values;
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                this.socket.close();
            } catch (IOException e2) {
                IOException iOException2 = e2;
            }
            throw th2;
        }
    }

    public static void serve(Language language2, Socket socket2) throws IOException {
        Telnet telnet;
        OutPort outPort;
        TtyInPort in;
        Thread thread;
        Procedure procedure;
        Socket client = socket2;
        new Telnet(client, true);
        Telnet conn = telnet;
        OutputStream sout = conn.getOutputStream();
        InputStream sin = conn.getInputStream();
        new OutPort(sout, (Path) FilePath.valueOf("/dev/stdout"));
        OutPort out = outPort;
        new TtyInPort(sin, (Path) FilePath.valueOf("/dev/stdin"), out);
        new TelnetRepl(language2, client);
        new Future(procedure, in, out, out);
        thread.start();
    }
}
