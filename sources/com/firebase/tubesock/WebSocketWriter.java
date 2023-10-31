package com.firebase.tubesock;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class WebSocketWriter {
    private WritableByteChannel channel;
    private boolean closeSent = false;
    private final Thread innerThread;
    private BlockingQueue<ByteBuffer> pendingBuffers;
    private final Random random;
    private volatile boolean stop = false;
    private WebSocket websocket;

    WebSocketWriter(WebSocket websocket2, String threadBaseName, int clientId) {
        Random random2;
        Runnable runnable;
        StringBuilder sb;
        BlockingQueue<ByteBuffer> blockingQueue;
        new Random();
        this.random = random2;
        new Runnable(this) {
            final /* synthetic */ WebSocketWriter this$0;

            {
                this.this$0 = r5;
            }

            public void run() {
                this.this$0.runWriter();
            }
        };
        this.innerThread = WebSocket.getThreadFactory().newThread(runnable);
        ThreadInitializer intializer = WebSocket.getIntializer();
        Thread innerThread2 = getInnerThread();
        new StringBuilder();
        intializer.setName(innerThread2, sb.append(threadBaseName).append("Writer-").append(clientId).toString());
        this.websocket = websocket2;
        new LinkedBlockingQueue();
        this.pendingBuffers = blockingQueue;
    }

    /* access modifiers changed from: package-private */
    public void setOutput(OutputStream output) {
        WritableByteChannel newChannel = Channels.newChannel(output);
        this.channel = newChannel;
    }

    private ByteBuffer frameInBuffer(byte b, boolean z, byte[] bArr) throws IOException {
        byte opcode = b;
        boolean masking = z;
        byte[] data = bArr;
        int headerLength = 2;
        if (masking) {
            headerLength = 2 + 4;
        }
        int length = data.length;
        if (length >= 126) {
            if (length <= 65535) {
                headerLength += 2;
            } else {
                headerLength += 8;
            }
        }
        ByteBuffer frame = ByteBuffer.allocate(data.length + headerLength);
        ByteBuffer put = frame.put((byte) (Byte.MIN_VALUE | opcode));
        if (length < 126) {
            if (masking) {
                length = 128 | length;
            }
            ByteBuffer put2 = frame.put((byte) length);
        } else if (length <= 65535) {
            int length_field = 126;
            if (masking) {
                length_field = 128 | 126;
            }
            ByteBuffer put3 = frame.put((byte) length_field);
            ByteBuffer putShort = frame.putShort((short) length);
        } else {
            int length_field2 = 127;
            if (masking) {
                length_field2 = 128 | 127;
            }
            ByteBuffer put4 = frame.put((byte) length_field2);
            ByteBuffer putInt = frame.putInt(0);
            ByteBuffer putInt2 = frame.putInt(length);
        }
        if (masking) {
            byte[] mask = generateMask();
            ByteBuffer put5 = frame.put(mask);
            for (int i = 0; i < data.length; i++) {
                ByteBuffer put6 = frame.put((byte) (data[i] ^ mask[i % 4]));
            }
        }
        Buffer flip = frame.flip();
        return frame;
    }

    private byte[] generateMask() {
        byte[] mask = new byte[4];
        this.random.nextBytes(mask);
        return mask;
    }

    /* access modifiers changed from: package-private */
    public synchronized void send(byte b, boolean z, byte[] bArr) throws IOException {
        Throwable th;
        byte opcode = b;
        boolean masking = z;
        byte[] data = bArr;
        synchronized (this) {
            ByteBuffer frame = frameInBuffer(opcode, masking, data);
            if (!this.stop || (!this.closeSent && opcode == 8)) {
                if (opcode == 8) {
                    this.closeSent = true;
                }
                boolean add = this.pendingBuffers.add(frame);
            } else {
                Throwable th2 = th;
                new WebSocketException("Shouldn't be sending");
                throw th2;
            }
        }
    }

    private void writeMessage() throws InterruptedException, IOException {
        int write = this.channel.write(this.pendingBuffers.take());
    }

    /* access modifiers changed from: package-private */
    public void stopIt() {
        this.stop = true;
    }

    private void handleError(WebSocketException e) {
        this.websocket.handleReceiverError(e);
    }

    /* access modifiers changed from: private */
    public void runWriter() {
        WebSocketException webSocketException;
        while (!this.stop && !Thread.interrupted()) {
            try {
                writeMessage();
            } catch (IOException e) {
                new WebSocketException("IO Exception", e);
                handleError(webSocketException);
                return;
            } catch (InterruptedException e2) {
                InterruptedException interruptedException = e2;
                return;
            }
        }
        for (int i = 0; i < this.pendingBuffers.size(); i++) {
            writeMessage();
        }
    }

    /* access modifiers changed from: package-private */
    public Thread getInnerThread() {
        return this.innerThread;
    }
}
