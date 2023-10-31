package kawa;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Telnet implements Runnable {

    /* renamed from: DO */
    public static final int f261DO = 253;
    public static final int DONT = 254;
    public static final int ECHO = 1;
    static final int EOF = 236;
    static final int IAC = 255;

    /* renamed from: IP */
    static final int f262IP = 244;
    static final int LINEMODE = 34;
    static final int NAWS = 31;
    static final int NOP = 241;
    static final int OPTION_NO = 0;
    static final int OPTION_WANTNO = 1;
    static final int OPTION_WANTNO_OPPOSITE = 2;
    static final int OPTION_WANTYES = 3;
    static final int OPTION_WANTYES_OPPOSITE = 4;
    static final int OPTION_YES = 5;

    /* renamed from: SB */
    static final int f263SB = 250;

    /* renamed from: SE */
    static final int f264SE = 240;
    public static final int SUPPRESS_GO_AHEAD = 3;

    /* renamed from: TM */
    static final int f265TM = 6;
    static final int TTYPE = 24;
    public static final int WILL = 251;
    public static final int WONT = 252;

    /* renamed from: in */
    TelnetInputStream f266in;
    boolean isServer;
    final byte[] optionsState = new byte[256];
    TelnetOutputStream out;
    final byte preferredLineMode = 3;
    InputStream sin;
    OutputStream sout;
    public byte[] terminalType;
    public short windowHeight;
    public short windowWidth;

    public TelnetInputStream getInputStream() {
        return this.f266in;
    }

    public TelnetOutputStream getOutputStream() {
        return this.out;
    }

    /* access modifiers changed from: package-private */
    public boolean change(int i, int i2) {
        int command = i;
        int option = i2;
        if (option == 6) {
            return true;
        }
        if (this.isServer && option == 31) {
            return true;
        }
        if (this.isServer && command == 251 && option == 34) {
            try {
                this.out.writeSubCommand(34, new byte[]{1, 3});
            } catch (IOException e) {
                IOException iOException = e;
            }
            return true;
        } else if (this.isServer && command == 251 && option == 24) {
            try {
                this.out.writeSubCommand(option, new byte[]{1});
            } catch (IOException e2) {
                IOException iOException2 = e2;
            }
            return true;
        } else {
            if (!this.isServer && option == 1) {
                if (command == 253) {
                    return false;
                }
                if (command == 251) {
                    return true;
                }
            }
            return false;
        }
    }

    public void subCommand(byte[] bArr, int off, int i) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        String str;
        byte[] buf = bArr;
        int len = i;
        switch (buf[off]) {
            case 24:
                byte[] type = new byte[(len - 1)];
                System.arraycopy(buf, 1, type, 0, len - 1);
                this.terminalType = type;
                PrintStream printStream = System.err;
                new StringBuilder();
                new String(type);
                printStream.println(sb3.append("terminal type: '").append(str).append("'").toString());
                return;
            case 31:
                if (len == 5) {
                    this.windowWidth = (short) ((buf[1] << 8) + (buf[2] & Ev3Constants.Opcode.TST));
                    this.windowHeight = (short) ((buf[3] << 8) + (buf[4] & Ev3Constants.Opcode.TST));
                    return;
                }
                return;
            case 34:
                PrintStream printStream2 = System.err;
                new StringBuilder();
                printStream2.println(sb.append("SBCommand LINEMODE ").append(buf[1]).append(" len:").append(len).toString());
                if (buf[1] == 3) {
                    for (int i2 = 2; i2 + 2 < len; i2 += 3) {
                        PrintStream printStream3 = System.err;
                        new StringBuilder();
                        printStream3.println(sb2.append("  ").append(buf[i2]).append(",").append(buf[i2 + 1]).append(",").append(buf[i2 + 2]).toString());
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void handle(int i, int i2) throws IOException {
        byte state;
        int command = i;
        int option = i2;
        boolean otherSide = command < 253;
        boolean wantOn = (command & 1) != 0;
        byte state2 = this.optionsState[option];
        if (otherSide) {
            state2 = (byte) (state2 >> 3);
        }
        switch ((state2 >> 3) & 7) {
            case 0:
                if (wantOn) {
                    if (!change(command, option)) {
                        this.out.writeCommand(otherSide ? DONT : WONT, option);
                        break;
                    } else {
                        state2 = 5;
                        this.out.writeCommand(otherSide ? f261DO : WILL, option);
                        break;
                    }
                } else {
                    return;
                }
            case 1:
                state2 = 0;
                break;
            case 2:
                state2 = 3;
                this.out.writeCommand(otherSide ? f261DO : WILL, option);
                break;
            case 3:
                if (!wantOn) {
                    state2 = 0;
                    break;
                } else {
                    state2 = 5;
                    boolean change = change(command, option);
                    break;
                }
            case 4:
                if (!wantOn) {
                    state2 = 0;
                    break;
                } else {
                    state2 = 1;
                    this.out.writeCommand(otherSide ? DONT : WONT, option);
                    break;
                }
            case 5:
                if (!wantOn) {
                    state2 = 0;
                    boolean change2 = change(command, option);
                    this.out.writeCommand(otherSide ? DONT : WONT, option);
                    break;
                } else {
                    return;
                }
        }
        if (otherSide) {
            state = (byte) ((this.optionsState[option] & 199) | (state2 << 3));
        } else {
            state = (byte) ((this.optionsState[option] & 248) | state2);
        }
        this.optionsState[option] = state;
    }

    public void request(int i, int i2) throws IOException {
        byte state;
        int command = i;
        int option = i2;
        boolean otherSide = command >= 253;
        boolean wantOn = (command & 1) != 0;
        byte state2 = this.optionsState[option];
        if (otherSide) {
            state2 = (byte) (state2 >> 3);
        }
        switch (state2 & 7) {
            case 0:
                if (wantOn) {
                    state2 = 3;
                    this.out.writeCommand(command, option);
                    break;
                }
                break;
            case 1:
                if (wantOn) {
                    state2 = 2;
                    break;
                }
                break;
            case 2:
                if (!wantOn) {
                    state2 = 1;
                    break;
                }
                break;
            case 3:
                if (!wantOn) {
                    state2 = 4;
                    break;
                }
                break;
            case 4:
                break;
            case 5:
                if (!wantOn) {
                    state2 = 1;
                    this.out.writeCommand(command, option);
                    break;
                }
                break;
        }
        if (wantOn) {
            state2 = 3;
        }
        if (otherSide) {
            state = (byte) ((this.optionsState[option] & 199) | (state2 << 3));
        } else {
            state = (byte) ((this.optionsState[option] & 248) | state2);
        }
        this.optionsState[option] = state;
    }

    static void usage() {
        System.err.println("Usage:  [java] kawa.Telnet HOST [PORT#]");
        System.exit(-1);
    }

    public static void main(String[] strArr) {
        Socket socket;
        Telnet telnet;
        Thread thread;
        String[] args = strArr;
        if (args.length == 0) {
            usage();
        }
        String host = args[0];
        int port = 23;
        if (args.length > 1) {
            port = Integer.parseInt(args[1]);
        }
        try {
            new Socket(host, port);
            new Telnet(socket, false);
            Telnet telnet2 = telnet;
            TelnetOutputStream tout = telnet2.getOutputStream();
            new Thread(telnet2);
            Thread t = thread;
            t.setPriority(Thread.currentThread().getPriority() + 1);
            t.start();
            byte[] buffer = new byte[1024];
            while (true) {
                int ch = System.in.read();
                if (ch < 0) {
                    t.stop();
                    return;
                }
                buffer[0] = (byte) ch;
                int avail = System.in.available();
                if (avail > 0) {
                    if (avail > buffer.length - 1) {
                        avail = buffer.length - 1;
                    }
                    avail = System.in.read(buffer, 1, avail);
                }
                tout.write(buffer, 0, avail + 1);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public Telnet(Socket socket, boolean isServer2) throws IOException {
        TelnetOutputStream telnetOutputStream;
        TelnetInputStream telnetInputStream;
        Socket socket2 = socket;
        this.sin = socket2.getInputStream();
        this.sout = socket2.getOutputStream();
        new TelnetOutputStream(this.sout);
        this.out = telnetOutputStream;
        new TelnetInputStream(this.sin, this);
        this.f266in = telnetInputStream;
        this.isServer = isServer2;
    }

    public void run() {
        try {
            TelnetInputStream tin = getInputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int ch = tin.read();
                if (ch >= 0) {
                    buffer[0] = (byte) ch;
                    int avail = tin.available();
                    if (avail > 0) {
                        if (avail > buffer.length - 1) {
                            avail = buffer.length - 1;
                        }
                        avail = tin.read(buffer, 1, avail);
                    }
                    System.out.write(buffer, 0, avail + 1);
                } else {
                    return;
                }
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(-1);
        }
    }
}
