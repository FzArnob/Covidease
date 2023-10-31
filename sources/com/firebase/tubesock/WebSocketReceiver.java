package com.firebase.tubesock;

import com.firebase.tubesock.MessageBuilderFactory;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

class WebSocketReceiver {
    private WebSocketEventHandler eventHandler = null;
    private DataInputStream input = null;
    private byte[] inputHeader = new byte[112];
    private MessageBuilderFactory.Builder pendingBuilder;
    private volatile boolean stop = false;
    private WebSocket websocket = null;

    WebSocketReceiver(WebSocket websocket2) {
        this.websocket = websocket2;
    }

    /* access modifiers changed from: package-private */
    public void setInput(DataInputStream input2) {
        DataInputStream dataInputStream = input2;
        this.input = dataInputStream;
    }

    /* access modifiers changed from: package-private */
    public void run() {
        WebSocketException webSocketException;
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        this.eventHandler = this.websocket.getEventHandler();
        while (!this.stop) {
            try {
                int offset = 0 + read(this.inputHeader, 0, 1);
                boolean fin = (this.inputHeader[0] & 128) != 0;
                if ((this.inputHeader[0] & Ev3Constants.Opcode.JR_NEQ8) != 0) {
                    Throwable th3 = th;
                    new WebSocketException("Invalid frame received");
                    throw th3;
                }
                byte opcode = (byte) (this.inputHeader[0] & 15);
                int offset2 = offset + read(this.inputHeader, offset, 1);
                byte length = this.inputHeader[1];
                long payload_length = 0;
                if (length < 126) {
                    payload_length = (long) length;
                } else if (length == 126) {
                    int offset3 = offset2 + read(this.inputHeader, offset2, 2);
                    payload_length = (long) (((255 & this.inputHeader[2]) << 8) | (255 & this.inputHeader[3]));
                } else if (length == Byte.MAX_VALUE) {
                    payload_length = parseLong(this.inputHeader, (offset2 + read(this.inputHeader, offset2, 8)) - 8);
                }
                byte[] payload = new byte[((int) payload_length)];
                int read = read(payload, 0, (int) payload_length);
                if (opcode == 8) {
                    this.websocket.onCloseOpReceived();
                } else if (opcode != 10) {
                    if (opcode == 1 || opcode == 2 || opcode == 9 || opcode == 0) {
                        appendBytes(fin, opcode, payload);
                    } else {
                        Throwable th4 = th2;
                        new StringBuilder();
                        new WebSocketException(sb.append("Unsupported opcode: ").append(opcode).toString());
                        throw th4;
                    }
                }
            } catch (SocketTimeoutException e) {
                SocketTimeoutException socketTimeoutException = e;
            } catch (IOException e2) {
                new WebSocketException("IO Error", e2);
                handleError(webSocketException);
            } catch (WebSocketException e3) {
                handleError(e3);
            }
        }
    }

    private void appendBytes(boolean z, byte b, byte[] bArr) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        boolean fin = z;
        byte opcode = b;
        byte[] data = bArr;
        if (opcode == 9) {
            if (fin) {
                handlePing(data);
                return;
            }
            Throwable th6 = th5;
            new WebSocketException("PING must not fragment across frames");
            throw th6;
        } else if (this.pendingBuilder != null && opcode != 0) {
            Throwable th7 = th4;
            new WebSocketException("Failed to continue outstanding frame");
            throw th7;
        } else if (this.pendingBuilder == null && opcode == 0) {
            Throwable th8 = th3;
            new WebSocketException("Received continuing frame, but there's nothing to continue");
            throw th8;
        } else {
            if (this.pendingBuilder == null) {
                this.pendingBuilder = MessageBuilderFactory.builder(opcode);
            }
            if (!this.pendingBuilder.appendBytes(data)) {
                Throwable th9 = th2;
                new WebSocketException("Failed to decode frame");
                throw th9;
            } else if (fin) {
                WebSocketMessage message = this.pendingBuilder.toMessage();
                this.pendingBuilder = null;
                if (message == null) {
                    Throwable th10 = th;
                    new WebSocketException("Failed to decode whole message");
                    throw th10;
                }
                this.eventHandler.onMessage(message);
            }
        }
    }

    private void handlePing(byte[] bArr) {
        Throwable th;
        byte[] payload = bArr;
        if (payload.length <= 125) {
            this.websocket.pong(payload);
            return;
        }
        Throwable th2 = th;
        new WebSocketException("PING frame too long");
        throw th2;
    }

    private long parseLong(byte[] bArr, int i) {
        byte[] buffer = bArr;
        int offset = i;
        return (((long) buffer[offset + 0]) << 56) + (((long) (buffer[offset + 1] & Ev3Constants.Opcode.TST)) << 48) + (((long) (buffer[offset + 2] & Ev3Constants.Opcode.TST)) << 40) + (((long) (buffer[offset + 3] & Ev3Constants.Opcode.TST)) << 32) + (((long) (buffer[offset + 4] & Ev3Constants.Opcode.TST)) << 24) + ((long) ((buffer[offset + 5] & Ev3Constants.Opcode.TST) << 16)) + ((long) ((buffer[offset + 6] & Ev3Constants.Opcode.TST) << 8)) + ((long) ((buffer[offset + 7] & Ev3Constants.Opcode.TST) << 0));
    }

    private int read(byte[] buffer, int offset, int i) throws IOException {
        int length = i;
        this.input.readFully(buffer, offset, length);
        return length;
    }

    /* access modifiers changed from: package-private */
    public void stopit() {
        this.stop = true;
    }

    /* access modifiers changed from: package-private */
    public boolean isRunning() {
        return !this.stop;
    }

    private void handleError(WebSocketException e) {
        stopit();
        this.websocket.handleReceiverError(e);
    }
}
