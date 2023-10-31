package android.support.p000v4.net;

import android.net.TrafficStats;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

/* renamed from: android.support.v4.net.TrafficStatsCompat */
public final class TrafficStatsCompat {
    @Deprecated
    public static void clearThreadStatsTag() {
        TrafficStats.clearThreadStatsTag();
    }

    @Deprecated
    public static int getThreadStatsTag() {
        return TrafficStats.getThreadStatsTag();
    }

    @Deprecated
    public static void incrementOperationCount(int operationCount) {
        TrafficStats.incrementOperationCount(operationCount);
    }

    @Deprecated
    public static void incrementOperationCount(int tag, int operationCount) {
        TrafficStats.incrementOperationCount(tag, operationCount);
    }

    @Deprecated
    public static void setThreadStatsTag(int tag) {
        TrafficStats.setThreadStatsTag(tag);
    }

    @Deprecated
    public static void tagSocket(Socket socket) throws SocketException {
        TrafficStats.tagSocket(socket);
    }

    @Deprecated
    public static void untagSocket(Socket socket) throws SocketException {
        TrafficStats.untagSocket(socket);
    }

    public static void tagDatagramSocket(@NonNull DatagramSocket datagramSocket) throws SocketException {
        Socket socket;
        DatagramSocket socket2 = datagramSocket;
        if (Build.VERSION.SDK_INT >= 24) {
            TrafficStats.tagDatagramSocket(socket2);
            return;
        }
        ParcelFileDescriptor pfd = ParcelFileDescriptor.fromDatagramSocket(socket2);
        new DatagramSocketWrapper(socket2, pfd.getFileDescriptor());
        TrafficStats.tagSocket(socket);
        int detachFd = pfd.detachFd();
    }

    public static void untagDatagramSocket(@NonNull DatagramSocket datagramSocket) throws SocketException {
        Socket socket;
        DatagramSocket socket2 = datagramSocket;
        if (Build.VERSION.SDK_INT >= 24) {
            TrafficStats.untagDatagramSocket(socket2);
            return;
        }
        ParcelFileDescriptor pfd = ParcelFileDescriptor.fromDatagramSocket(socket2);
        new DatagramSocketWrapper(socket2, pfd.getFileDescriptor());
        TrafficStats.untagSocket(socket);
        int detachFd = pfd.detachFd();
    }

    private TrafficStatsCompat() {
    }
}
