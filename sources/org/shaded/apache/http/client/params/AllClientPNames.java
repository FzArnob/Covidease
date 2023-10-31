package org.shaded.apache.http.client.params;

import org.shaded.apache.http.auth.params.AuthPNames;
import org.shaded.apache.http.conn.params.ConnConnectionPNames;
import org.shaded.apache.http.conn.params.ConnManagerPNames;
import org.shaded.apache.http.conn.params.ConnRoutePNames;
import org.shaded.apache.http.cookie.params.CookieSpecPNames;
import org.shaded.apache.http.params.CoreConnectionPNames;
import org.shaded.apache.http.params.CoreProtocolPNames;

public interface AllClientPNames extends CoreConnectionPNames, CoreProtocolPNames, ClientPNames, AuthPNames, CookieSpecPNames, ConnConnectionPNames, ConnManagerPNames, ConnRoutePNames {
}
