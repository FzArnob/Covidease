package android.support.p000v4.net;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;

/* renamed from: android.support.v4.net.DatagramSocketWrapper */
class DatagramSocketWrapper extends Socket {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    DatagramSocketWrapper(java.net.DatagramSocket r10, java.io.FileDescriptor r11) throws java.net.SocketException {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r0
            android.support.v4.net.DatagramSocketWrapper$DatagramSocketImplWrapper r4 = new android.support.v4.net.DatagramSocketWrapper$DatagramSocketImplWrapper
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r7 = r2
            r5.<init>(r6, r7)
            r3.<init>(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.net.DatagramSocketWrapper.<init>(java.net.DatagramSocket, java.io.FileDescriptor):void");
    }

    /* renamed from: android.support.v4.net.DatagramSocketWrapper$DatagramSocketImplWrapper */
    private static class DatagramSocketImplWrapper extends SocketImpl {
        DatagramSocketImplWrapper(DatagramSocket socket, FileDescriptor fd) {
            this.localport = socket.getLocalPort();
            this.fd = fd;
        }

        /* access modifiers changed from: protected */
        public void accept(SocketImpl socketImpl) throws IOException {
            Throwable th;
            SocketImpl socketImpl2 = socketImpl;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        /* access modifiers changed from: protected */
        public int available() throws IOException {
            Throwable th;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        /* access modifiers changed from: protected */
        public void bind(InetAddress inetAddress, int i) throws IOException {
            Throwable th;
            InetAddress inetAddress2 = inetAddress;
            int i2 = i;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        /* access modifiers changed from: protected */
        public void close() throws IOException {
            Throwable th;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        /* access modifiers changed from: protected */
        public void connect(String str, int i) throws IOException {
            Throwable th;
            String str2 = str;
            int i2 = i;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        /* access modifiers changed from: protected */
        public void connect(InetAddress inetAddress, int i) throws IOException {
            Throwable th;
            InetAddress inetAddress2 = inetAddress;
            int i2 = i;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        /* access modifiers changed from: protected */
        public void create(boolean z) throws IOException {
            Throwable th;
            boolean z2 = z;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        /* access modifiers changed from: protected */
        public InputStream getInputStream() throws IOException {
            Throwable th;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        /* access modifiers changed from: protected */
        public OutputStream getOutputStream() throws IOException {
            Throwable th;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        /* access modifiers changed from: protected */
        public void listen(int i) throws IOException {
            Throwable th;
            int i2 = i;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        /* access modifiers changed from: protected */
        public void connect(SocketAddress socketAddress, int i) throws IOException {
            Throwable th;
            SocketAddress socketAddress2 = socketAddress;
            int i2 = i;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        /* access modifiers changed from: protected */
        public void sendUrgentData(int i) throws IOException {
            Throwable th;
            int i2 = i;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        public Object getOption(int i) throws SocketException {
            Throwable th;
            int i2 = i;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }

        public void setOption(int i, Object obj) throws SocketException {
            Throwable th;
            int i2 = i;
            Object obj2 = obj;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }
    }
}
