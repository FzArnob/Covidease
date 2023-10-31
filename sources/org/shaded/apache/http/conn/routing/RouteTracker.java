package org.shaded.apache.http.conn.routing;

import java.net.InetAddress;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.conn.routing.RouteInfo;

@NotThreadSafe
public final class RouteTracker implements RouteInfo, Cloneable {
    private boolean connected;
    private RouteInfo.LayerType layered;
    private final InetAddress localAddress;
    private HttpHost[] proxyChain;
    private boolean secure;
    private final HttpHost targetHost;
    private RouteInfo.TunnelType tunnelled;

    public RouteTracker(HttpHost httpHost, InetAddress inetAddress) {
        Throwable th;
        HttpHost target = httpHost;
        InetAddress local = inetAddress;
        if (target == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Target host may not be null.");
            throw th2;
        }
        this.targetHost = target;
        this.localAddress = local;
        this.tunnelled = RouteInfo.TunnelType.PLAIN;
        this.layered = RouteInfo.LayerType.PLAIN;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RouteTracker(org.shaded.apache.http.conn.routing.HttpRoute r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            org.shaded.apache.http.HttpHost r3 = r3.getTargetHost()
            r4 = r1
            java.net.InetAddress r4 = r4.getLocalAddress()
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.conn.routing.RouteTracker.<init>(org.shaded.apache.http.conn.routing.HttpRoute):void");
    }

    public final void connectTarget(boolean z) {
        Throwable th;
        boolean secure2 = z;
        if (this.connected) {
            Throwable th2 = th;
            new IllegalStateException("Already connected.");
            throw th2;
        }
        this.connected = true;
        this.secure = secure2;
    }

    public final void connectProxy(HttpHost httpHost, boolean z) {
        Throwable th;
        Throwable th2;
        HttpHost proxy = httpHost;
        boolean secure2 = z;
        if (proxy == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Proxy host may not be null.");
            throw th3;
        } else if (this.connected) {
            Throwable th4 = th;
            new IllegalStateException("Already connected.");
            throw th4;
        } else {
            this.connected = true;
            HttpHost[] httpHostArr = new HttpHost[1];
            httpHostArr[0] = proxy;
            this.proxyChain = httpHostArr;
            this.secure = secure2;
        }
    }

    public final void tunnelTarget(boolean z) {
        Throwable th;
        Throwable th2;
        boolean secure2 = z;
        if (!this.connected) {
            Throwable th3 = th2;
            new IllegalStateException("No tunnel unless connected.");
            throw th3;
        } else if (this.proxyChain == null) {
            Throwable th4 = th;
            new IllegalStateException("No tunnel without proxy.");
            throw th4;
        } else {
            this.tunnelled = RouteInfo.TunnelType.TUNNELLED;
            this.secure = secure2;
        }
    }

    public final void tunnelProxy(HttpHost httpHost, boolean z) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        HttpHost proxy = httpHost;
        boolean secure2 = z;
        if (proxy == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Proxy host may not be null.");
            throw th4;
        } else if (!this.connected) {
            Throwable th5 = th2;
            new IllegalStateException("No tunnel unless connected.");
            throw th5;
        } else if (this.proxyChain == null) {
            Throwable th6 = th;
            new IllegalStateException("No proxy tunnel without proxy.");
            throw th6;
        } else {
            HttpHost[] proxies = new HttpHost[(this.proxyChain.length + 1)];
            System.arraycopy(this.proxyChain, 0, proxies, 0, this.proxyChain.length);
            proxies[proxies.length - 1] = proxy;
            this.proxyChain = proxies;
            this.secure = secure2;
        }
    }

    public final void layerProtocol(boolean z) {
        Throwable th;
        boolean secure2 = z;
        if (!this.connected) {
            Throwable th2 = th;
            new IllegalStateException("No layered protocol unless connected.");
            throw th2;
        }
        this.layered = RouteInfo.LayerType.LAYERED;
        this.secure = secure2;
    }

    public final HttpHost getTargetHost() {
        return this.targetHost;
    }

    public final InetAddress getLocalAddress() {
        return this.localAddress;
    }

    public final int getHopCount() {
        int hops = 0;
        if (this.connected) {
            if (this.proxyChain == null) {
                hops = 1;
            } else {
                hops = this.proxyChain.length + 1;
            }
        }
        return hops;
    }

    public final HttpHost getHopTarget(int i) {
        HttpHost result;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        int hop = i;
        if (hop < 0) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Hop index must not be negative: ").append(hop).toString());
            throw th3;
        }
        int hopcount = getHopCount();
        if (hop >= hopcount) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Hop index ").append(hop).append(" exceeds tracked route length ").append(hopcount).append(".").toString());
            throw th4;
        }
        if (hop < hopcount - 1) {
            result = this.proxyChain[hop];
        } else {
            result = this.targetHost;
        }
        return result;
    }

    public final HttpHost getProxyHost() {
        return this.proxyChain == null ? null : this.proxyChain[0];
    }

    public final boolean isConnected() {
        return this.connected;
    }

    public final RouteInfo.TunnelType getTunnelType() {
        return this.tunnelled;
    }

    public final boolean isTunnelled() {
        return this.tunnelled == RouteInfo.TunnelType.TUNNELLED;
    }

    public final RouteInfo.LayerType getLayerType() {
        return this.layered;
    }

    public final boolean isLayered() {
        return this.layered == RouteInfo.LayerType.LAYERED;
    }

    public final boolean isSecure() {
        return this.secure;
    }

    public final HttpRoute toRoute() {
        HttpRoute httpRoute;
        HttpRoute httpRoute2;
        if (!this.connected) {
            httpRoute2 = null;
        } else {
            httpRoute2 = httpRoute;
            new HttpRoute(this.targetHost, this.localAddress, this.proxyChain, this.secure, this.tunnelled, this.layered);
        }
        return httpRoute2;
    }

    public final boolean equals(Object obj) {
        Object o = obj;
        if (o == this) {
            return true;
        }
        if (!(o instanceof RouteTracker)) {
            return false;
        }
        RouteTracker that = (RouteTracker) o;
        boolean equal = this.targetHost.equals(that.targetHost) & (this.localAddress == that.localAddress || (this.localAddress != null && this.localAddress.equals(that.localAddress))) & (this.proxyChain == that.proxyChain || !(this.proxyChain == null || that.proxyChain == null || this.proxyChain.length != that.proxyChain.length)) & (this.connected == that.connected && this.secure == that.secure && this.tunnelled == that.tunnelled && this.layered == that.layered);
        if (equal && this.proxyChain != null) {
            int i = 0;
            while (equal && i < this.proxyChain.length) {
                equal = this.proxyChain[i].equals(that.proxyChain[i]);
                i++;
            }
        }
        return equal;
    }

    public final int hashCode() {
        int hc;
        int hc2 = this.targetHost.hashCode();
        if (this.localAddress != null) {
            hc2 ^= this.localAddress.hashCode();
        }
        if (this.proxyChain != null) {
            hc ^= this.proxyChain.length;
            for (int i = 0; i < this.proxyChain.length; i++) {
                hc ^= this.proxyChain[i].hashCode();
            }
        }
        if (this.connected) {
            hc ^= 286331153;
        }
        if (this.secure) {
            hc ^= 572662306;
        }
        return (hc ^ this.tunnelled.hashCode()) ^ this.layered.hashCode();
    }

    public final String toString() {
        StringBuilder sb;
        new StringBuilder(50 + (getHopCount() * 30));
        StringBuilder cab = sb;
        StringBuilder append = cab.append("RouteTracker[");
        if (this.localAddress != null) {
            StringBuilder append2 = cab.append(this.localAddress);
            StringBuilder append3 = cab.append("->");
        }
        StringBuilder append4 = cab.append('{');
        if (this.connected) {
            StringBuilder append5 = cab.append('c');
        }
        if (this.tunnelled == RouteInfo.TunnelType.TUNNELLED) {
            StringBuilder append6 = cab.append('t');
        }
        if (this.layered == RouteInfo.LayerType.LAYERED) {
            StringBuilder append7 = cab.append('l');
        }
        if (this.secure) {
            StringBuilder append8 = cab.append('s');
        }
        StringBuilder append9 = cab.append("}->");
        if (this.proxyChain != null) {
            for (int i = 0; i < this.proxyChain.length; i++) {
                StringBuilder append10 = cab.append(this.proxyChain[i]);
                StringBuilder append11 = cab.append("->");
            }
        }
        StringBuilder append12 = cab.append(this.targetHost);
        StringBuilder append13 = cab.append(']');
        return cab.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
