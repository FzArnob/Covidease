package org.shaded.apache.http.conn.routing;

import java.net.InetAddress;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.conn.routing.RouteInfo;

@Immutable
public final class HttpRoute implements RouteInfo, Cloneable {
    private static final HttpHost[] EMPTY_HTTP_HOST_ARRAY = new HttpHost[0];
    private final RouteInfo.LayerType layered;
    private final InetAddress localAddress;
    private final HttpHost[] proxyChain;
    private final boolean secure;
    private final HttpHost targetHost;
    private final RouteInfo.TunnelType tunnelled;

    private HttpRoute(InetAddress inetAddress, HttpHost httpHost, HttpHost[] httpHostArr, boolean z, RouteInfo.TunnelType tunnelType, RouteInfo.LayerType layerType) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        InetAddress local = inetAddress;
        HttpHost target = httpHost;
        HttpHost[] proxies = httpHostArr;
        boolean secure2 = z;
        RouteInfo.TunnelType tunnelled2 = tunnelType;
        RouteInfo.LayerType layered2 = layerType;
        if (target == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Target host may not be null.");
            throw th4;
        } else if (proxies == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("Proxies may not be null.");
            throw th5;
        } else if (tunnelled2 == RouteInfo.TunnelType.TUNNELLED && proxies.length == 0) {
            Throwable th6 = th;
            new IllegalArgumentException("Proxy required if tunnelled.");
            throw th6;
        } else {
            tunnelled2 = tunnelled2 == null ? RouteInfo.TunnelType.PLAIN : tunnelled2;
            layered2 = layered2 == null ? RouteInfo.LayerType.PLAIN : layered2;
            this.targetHost = target;
            this.localAddress = local;
            this.proxyChain = proxies;
            this.secure = secure2;
            this.tunnelled = tunnelled2;
            this.layered = layered2;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HttpRoute(HttpHost target, InetAddress local, HttpHost[] proxies, boolean secure2, RouteInfo.TunnelType tunnelled2, RouteInfo.LayerType layered2) {
        this(local, target, toChain(proxies), secure2, tunnelled2, layered2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HttpRoute(HttpHost target, InetAddress local, HttpHost proxy, boolean secure2, RouteInfo.TunnelType tunnelled2, RouteInfo.LayerType layered2) {
        this(local, target, toChain(proxy), secure2, tunnelled2, layered2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HttpRoute(HttpHost target, InetAddress local, boolean secure2) {
        this(local, target, EMPTY_HTTP_HOST_ARRAY, secure2, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HttpRoute(HttpHost target) {
        this((InetAddress) null, target, EMPTY_HTTP_HOST_ARRAY, false, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HttpRoute(org.shaded.apache.http.HttpHost r14, java.net.InetAddress r15, org.shaded.apache.http.HttpHost r16, boolean r17) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r0
            r6 = r2
            r7 = r1
            r8 = r3
            org.shaded.apache.http.HttpHost[] r8 = toChain((org.shaded.apache.http.HttpHost) r8)
            r9 = r4
            r10 = r4
            if (r10 == 0) goto L_0x002c
            org.shaded.apache.http.conn.routing.RouteInfo$TunnelType r10 = org.shaded.apache.http.conn.routing.RouteInfo.TunnelType.TUNNELLED
        L_0x0015:
            r11 = r4
            if (r11 == 0) goto L_0x002f
            org.shaded.apache.http.conn.routing.RouteInfo$LayerType r11 = org.shaded.apache.http.conn.routing.RouteInfo.LayerType.LAYERED
        L_0x001a:
            r5.<init>((java.net.InetAddress) r6, (org.shaded.apache.http.HttpHost) r7, (org.shaded.apache.http.HttpHost[]) r8, (boolean) r9, (org.shaded.apache.http.conn.routing.RouteInfo.TunnelType) r10, (org.shaded.apache.http.conn.routing.RouteInfo.LayerType) r11)
            r5 = r3
            if (r5 != 0) goto L_0x0032
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            r12 = r5
            r5 = r12
            r6 = r12
            java.lang.String r7 = "Proxy host may not be null."
            r6.<init>(r7)
            throw r5
        L_0x002c:
            org.shaded.apache.http.conn.routing.RouteInfo$TunnelType r10 = org.shaded.apache.http.conn.routing.RouteInfo.TunnelType.PLAIN
            goto L_0x0015
        L_0x002f:
            org.shaded.apache.http.conn.routing.RouteInfo$LayerType r11 = org.shaded.apache.http.conn.routing.RouteInfo.LayerType.PLAIN
            goto L_0x001a
        L_0x0032:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.conn.routing.HttpRoute.<init>(org.shaded.apache.http.HttpHost, java.net.InetAddress, org.shaded.apache.http.HttpHost, boolean):void");
    }

    private static HttpHost[] toChain(HttpHost httpHost) {
        HttpHost proxy = httpHost;
        if (proxy == null) {
            return EMPTY_HTTP_HOST_ARRAY;
        }
        return new HttpHost[]{proxy};
    }

    private static HttpHost[] toChain(HttpHost[] httpHostArr) {
        Throwable th;
        HttpHost[] proxies = httpHostArr;
        if (proxies == null || proxies.length < 1) {
            return EMPTY_HTTP_HOST_ARRAY;
        }
        HttpHost[] arr$ = proxies;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            if (arr$[i$] == null) {
                Throwable th2 = th;
                new IllegalArgumentException("Proxy chain may not contain null elements.");
                throw th2;
            }
        }
        HttpHost[] arr$2 = new HttpHost[proxies.length];
        System.arraycopy(proxies, 0, arr$2, 0, proxies.length);
        return arr$2;
    }

    public final HttpHost getTargetHost() {
        return this.targetHost;
    }

    public final InetAddress getLocalAddress() {
        return this.localAddress;
    }

    public final int getHopCount() {
        return this.proxyChain.length + 1;
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
            new IllegalArgumentException(sb.append("Hop index ").append(hop).append(" exceeds route length ").append(hopcount).toString());
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
        return this.proxyChain.length == 0 ? null : this.proxyChain[0];
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

    public final boolean equals(Object obj) {
        Object o = obj;
        if (o == this) {
            return true;
        }
        if (!(o instanceof HttpRoute)) {
            return false;
        }
        HttpRoute that = (HttpRoute) o;
        boolean equal = this.targetHost.equals(that.targetHost) & (this.localAddress == that.localAddress || (this.localAddress != null && this.localAddress.equals(that.localAddress))) & (this.proxyChain == that.proxyChain || this.proxyChain.length == that.proxyChain.length) & (this.secure == that.secure && this.tunnelled == that.tunnelled && this.layered == that.layered);
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
        int hc = this.targetHost.hashCode();
        if (this.localAddress != null) {
            hc ^= this.localAddress.hashCode();
        }
        int hc2 = hc ^ this.proxyChain.length;
        HttpHost[] arr$ = this.proxyChain;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            hc2 ^= arr$[i$].hashCode();
        }
        if (this.secure) {
            hc2 ^= 286331153;
        }
        return (hc2 ^ this.tunnelled.hashCode()) ^ this.layered.hashCode();
    }

    public final String toString() {
        StringBuilder sb;
        new StringBuilder(50 + (getHopCount() * 30));
        StringBuilder cab = sb;
        StringBuilder append = cab.append("HttpRoute[");
        if (this.localAddress != null) {
            StringBuilder append2 = cab.append(this.localAddress);
            StringBuilder append3 = cab.append("->");
        }
        StringBuilder append4 = cab.append('{');
        if (this.tunnelled == RouteInfo.TunnelType.TUNNELLED) {
            StringBuilder append5 = cab.append('t');
        }
        if (this.layered == RouteInfo.LayerType.LAYERED) {
            StringBuilder append6 = cab.append('l');
        }
        if (this.secure) {
            StringBuilder append7 = cab.append('s');
        }
        StringBuilder append8 = cab.append("}->");
        HttpHost[] arr$ = this.proxyChain;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            StringBuilder append9 = cab.append(arr$[i$]);
            StringBuilder append10 = cab.append("->");
        }
        StringBuilder append11 = cab.append(this.targetHost);
        StringBuilder append12 = cab.append(']');
        return cab.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
