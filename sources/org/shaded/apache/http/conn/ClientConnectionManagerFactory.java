package org.shaded.apache.http.conn;

import org.shaded.apache.http.conn.scheme.SchemeRegistry;
import org.shaded.apache.http.params.HttpParams;

public interface ClientConnectionManagerFactory {
    ClientConnectionManager newInstance(HttpParams httpParams, SchemeRegistry schemeRegistry);
}
