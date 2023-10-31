package org.shaded.apache.http.client.params;

import java.util.Collection;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.conn.ClientConnectionManagerFactory;
import org.shaded.apache.http.params.HttpAbstractParamBean;
import org.shaded.apache.http.params.HttpParams;

@NotThreadSafe
public class ClientParamBean extends HttpAbstractParamBean {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClientParamBean(HttpParams params) {
        super(params);
    }

    public void setConnectionManagerFactoryClassName(String factory) {
        HttpParams parameter = this.params.setParameter(ClientPNames.CONNECTION_MANAGER_FACTORY_CLASS_NAME, factory);
    }

    @Deprecated
    public void setConnectionManagerFactory(ClientConnectionManagerFactory factory) {
        HttpParams parameter = this.params.setParameter(ClientPNames.CONNECTION_MANAGER_FACTORY, factory);
    }

    public void setHandleRedirects(boolean handle) {
        HttpParams booleanParameter = this.params.setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, handle);
    }

    public void setRejectRelativeRedirect(boolean reject) {
        HttpParams booleanParameter = this.params.setBooleanParameter(ClientPNames.REJECT_RELATIVE_REDIRECT, reject);
    }

    public void setMaxRedirects(int maxRedirects) {
        HttpParams intParameter = this.params.setIntParameter(ClientPNames.MAX_REDIRECTS, maxRedirects);
    }

    public void setAllowCircularRedirects(boolean allow) {
        HttpParams booleanParameter = this.params.setBooleanParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, allow);
    }

    public void setHandleAuthentication(boolean handle) {
        HttpParams booleanParameter = this.params.setBooleanParameter(ClientPNames.HANDLE_AUTHENTICATION, handle);
    }

    public void setCookiePolicy(String policy) {
        HttpParams parameter = this.params.setParameter(ClientPNames.COOKIE_POLICY, policy);
    }

    public void setVirtualHost(HttpHost host) {
        HttpParams parameter = this.params.setParameter(ClientPNames.VIRTUAL_HOST, host);
    }

    public void setDefaultHeaders(Collection<Header> headers) {
        HttpParams parameter = this.params.setParameter(ClientPNames.DEFAULT_HEADERS, headers);
    }

    public void setDefaultHost(HttpHost host) {
        HttpParams parameter = this.params.setParameter(ClientPNames.DEFAULT_HOST, host);
    }
}
