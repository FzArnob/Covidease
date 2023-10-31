package org.shaded.apache.http.conn.routing;

import java.net.InetAddress;
import org.shaded.apache.http.HttpHost;

public interface RouteInfo {

    public enum LayerType {
    }

    public enum TunnelType {
    }

    int getHopCount();

    HttpHost getHopTarget(int i);

    LayerType getLayerType();

    InetAddress getLocalAddress();

    HttpHost getProxyHost();

    HttpHost getTargetHost();

    TunnelType getTunnelType();

    boolean isLayered();

    boolean isSecure();

    boolean isTunnelled();
}
