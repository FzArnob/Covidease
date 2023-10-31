package org.shaded.apache.http.impl.cookie;

import java.util.Collection;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.cookie.CookieSpec;
import org.shaded.apache.http.cookie.CookieSpecFactory;
import org.shaded.apache.http.cookie.params.CookieSpecPNames;
import org.shaded.apache.http.params.HttpParams;

@Immutable
public class BrowserCompatSpecFactory implements CookieSpecFactory {
    public BrowserCompatSpecFactory() {
    }

    public CookieSpec newInstance(HttpParams httpParams) {
        CookieSpec cookieSpec;
        CookieSpec cookieSpec2;
        HttpParams params = httpParams;
        if (params != null) {
            String[] patterns = null;
            Collection<?> param = (Collection) params.getParameter(CookieSpecPNames.DATE_PATTERNS);
            if (param != null) {
                patterns = (String[]) param.toArray(new String[param.size()]);
            }
            new BrowserCompatSpec(patterns);
            return cookieSpec2;
        }
        new BrowserCompatSpec();
        return cookieSpec;
    }
}
