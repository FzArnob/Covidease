package org.shaded.apache.http.auth;

import org.shaded.apache.http.params.HttpParams;

public interface AuthSchemeFactory {
    AuthScheme newInstance(HttpParams httpParams);
}
