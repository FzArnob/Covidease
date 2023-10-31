package org.shaded.apache.http.cookie.params;

import java.util.Collection;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.params.HttpAbstractParamBean;
import org.shaded.apache.http.params.HttpParams;

@NotThreadSafe
public class CookieSpecParamBean extends HttpAbstractParamBean {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CookieSpecParamBean(HttpParams params) {
        super(params);
    }

    public void setDatePatterns(Collection<String> patterns) {
        HttpParams parameter = this.params.setParameter(CookieSpecPNames.DATE_PATTERNS, patterns);
    }

    public void setSingleHeader(boolean singleHeader) {
        HttpParams booleanParameter = this.params.setBooleanParameter(CookieSpecPNames.SINGLE_COOKIE_HEADER, singleHeader);
    }
}
