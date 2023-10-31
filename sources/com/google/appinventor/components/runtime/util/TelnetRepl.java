package com.google.appinventor.components.runtime.util;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.Log;
import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
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
import kawa.Shell;
import kawa.Telnet;
import kawa.TelnetInputStream;
import kawa.TelnetOutputStream;

public class TelnetRepl extends Procedure0 {
    private Language B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private Socket f574B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    public TelnetRepl(Language language, Socket socket) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = language;
        this.f574B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = socket;
    }

    public Object apply0() {
        StringBuilder sb;
        Thread currentThread = Thread.currentThread();
        Thread thread = currentThread;
        if (currentThread.getContextClassLoader() == null) {
            thread.setContextClassLoader(Telnet.class.getClassLoader());
        }
        try {
            boolean run = Shell.run(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, Environment.getCurrent());
            Values values = Values.empty;
            try {
                this.f574B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.close();
            } catch (IOException e) {
            }
            return values;
        } catch (RuntimeException e2) {
            RuntimeException runtimeException = e2;
            new StringBuilder("Repl is exiting with error ");
            int d = Log.d("TelnetRepl", sb.append(runtimeException.getMessage()).toString());
            runtimeException.printStackTrace();
            throw runtimeException;
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                this.f574B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.close();
            } catch (IOException e3) {
            }
            throw th2;
        }
    }

    public static Thread serve(Language language, Socket socket) throws IOException {
        Telnet telnet;
        OutPort outPort;
        InPort inPort;
        Thread thread;
        Procedure procedure;
        Socket socket2 = socket;
        new Telnet(socket2, true);
        Telnet telnet2 = telnet;
        TelnetOutputStream outputStream = telnet2.getOutputStream();
        TelnetInputStream inputStream = telnet2.getInputStream();
        new OutPort((OutputStream) outputStream, (Path) FilePath.valueOf("/dev/stdout"));
        OutPort outPort2 = outPort;
        new TtyInPort((InputStream) inputStream, (Path) FilePath.valueOf("/dev/stdin"), outPort2);
        InPort inPort2 = inPort;
        Thread thread2 = thread;
        new TelnetRepl(language, socket2);
        OutPort outPort3 = outPort2;
        new BiggerFuture(procedure, inPort2, outPort3, outPort3, "Telnet Repl Thread", PlaybackStateCompat.ACTION_SET_REPEAT_MODE);
        Thread thread3 = thread2;
        thread3.start();
        return thread3;
    }
}
