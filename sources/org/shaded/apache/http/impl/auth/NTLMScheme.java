package org.shaded.apache.http.impl.auth;

import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.auth.AUTH;
import org.shaded.apache.http.auth.AuthenticationException;
import org.shaded.apache.http.auth.Credentials;
import org.shaded.apache.http.auth.InvalidCredentialsException;
import org.shaded.apache.http.auth.MalformedChallengeException;
import org.shaded.apache.http.auth.NTCredentials;
import org.shaded.apache.http.message.BufferedHeader;
import org.shaded.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class NTLMScheme extends AuthSchemeBase {
    private String challenge;
    private final NTLMEngine engine;
    private State state;

    enum State {
    }

    public NTLMScheme(NTLMEngine nTLMEngine) {
        Throwable th;
        NTLMEngine engine2 = nTLMEngine;
        if (engine2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("NTLM engine may not be null");
            throw th2;
        }
        this.engine = engine2;
        this.state = State.UNINITIATED;
        this.challenge = null;
    }

    public String getSchemeName() {
        return "ntlm";
    }

    public String getParameter(String str) {
        String str2 = str;
        return null;
    }

    public String getRealm() {
        return null;
    }

    public boolean isConnectionBased() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void parseChallenge(CharArrayBuffer buffer, int pos, int len) throws MalformedChallengeException {
        String challenge2 = buffer.substringTrimmed(pos, len);
        if (challenge2.length() == 0) {
            if (this.state == State.UNINITIATED) {
                this.state = State.CHALLENGE_RECEIVED;
            } else {
                this.state = State.FAILED;
            }
            this.challenge = null;
            return;
        }
        this.state = State.MSG_TYPE2_RECEVIED;
        this.challenge = challenge2;
    }

    public Header authenticate(Credentials credentials, HttpRequest httpRequest) throws AuthenticationException {
        Throwable th;
        StringBuilder sb;
        String response;
        CharArrayBuffer charArrayBuffer;
        Header header;
        Throwable th2;
        StringBuilder sb2;
        Credentials credentials2 = credentials;
        HttpRequest httpRequest2 = httpRequest;
        try {
            NTCredentials ntcredentials = (NTCredentials) credentials2;
            if (this.state == State.CHALLENGE_RECEIVED || this.state == State.FAILED) {
                response = this.engine.generateType1Msg(ntcredentials.getDomain(), ntcredentials.getWorkstation());
                this.state = State.MSG_TYPE1_GENERATED;
            } else if (this.state == State.MSG_TYPE2_RECEVIED) {
                response = this.engine.generateType3Msg(ntcredentials.getUserName(), ntcredentials.getPassword(), ntcredentials.getDomain(), ntcredentials.getWorkstation(), this.challenge);
                this.state = State.MSG_TYPE3_GENERATED;
            } else {
                Throwable th3 = th2;
                new StringBuilder();
                new AuthenticationException(sb2.append("Unexpected state: ").append(this.state).toString());
                throw th3;
            }
            new CharArrayBuffer(32);
            CharArrayBuffer buffer = charArrayBuffer;
            if (isProxy()) {
                buffer.append(AUTH.PROXY_AUTH_RESP);
            } else {
                buffer.append(AUTH.WWW_AUTH_RESP);
            }
            buffer.append(": NTLM ");
            buffer.append(response);
            new BufferedHeader(buffer);
            return header;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th4 = th;
            new StringBuilder();
            new InvalidCredentialsException(sb.append("Credentials cannot be used for NTLM authentication: ").append(credentials2.getClass().getName()).toString());
            throw th4;
        }
    }

    public boolean isComplete() {
        return this.state == State.MSG_TYPE3_GENERATED || this.state == State.FAILED;
    }
}
