package org.shaded.apache.http.cookie;

import org.shaded.apache.http.params.HttpParams;

public interface CookieSpecFactory {
    CookieSpec newInstance(HttpParams httpParams);
}
