package org.shaded.apache.http.client;

import java.util.Map;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.auth.AuthScheme;
import org.shaded.apache.http.auth.AuthenticationException;
import org.shaded.apache.http.auth.MalformedChallengeException;
import org.shaded.apache.http.protocol.HttpContext;

public interface AuthenticationHandler {
    Map<String, Header> getChallenges(HttpResponse httpResponse, HttpContext httpContext) throws MalformedChallengeException;

    boolean isAuthenticationRequested(HttpResponse httpResponse, HttpContext httpContext);

    AuthScheme selectScheme(Map<String, Header> map, HttpResponse httpResponse, HttpContext httpContext) throws AuthenticationException;
}
