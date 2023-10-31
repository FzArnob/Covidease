package org.shaded.apache.http.conn.scheme;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public interface LayeredSocketFactory extends SocketFactory {
    Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException;
}
