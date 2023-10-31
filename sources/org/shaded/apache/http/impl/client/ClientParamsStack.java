package org.shaded.apache.http.impl.client;

import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.params.AbstractHttpParams;
import org.shaded.apache.http.params.HttpParams;

@NotThreadSafe
public class ClientParamsStack extends AbstractHttpParams {
    protected final HttpParams applicationParams;
    protected final HttpParams clientParams;
    protected final HttpParams overrideParams;
    protected final HttpParams requestParams;

    public ClientParamsStack(HttpParams aparams, HttpParams cparams, HttpParams rparams, HttpParams oparams) {
        this.applicationParams = aparams;
        this.clientParams = cparams;
        this.requestParams = rparams;
        this.overrideParams = oparams;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ClientParamsStack(org.shaded.apache.http.impl.client.ClientParamsStack r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            org.shaded.apache.http.params.HttpParams r3 = r3.getApplicationParams()
            r4 = r1
            org.shaded.apache.http.params.HttpParams r4 = r4.getClientParams()
            r5 = r1
            org.shaded.apache.http.params.HttpParams r5 = r5.getRequestParams()
            r6 = r1
            org.shaded.apache.http.params.HttpParams r6 = r6.getOverrideParams()
            r2.<init>(r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.client.ClientParamsStack.<init>(org.shaded.apache.http.impl.client.ClientParamsStack):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ClientParamsStack(org.shaded.apache.http.impl.client.ClientParamsStack r12, org.shaded.apache.http.params.HttpParams r13, org.shaded.apache.http.params.HttpParams r14, org.shaded.apache.http.params.HttpParams r15, org.shaded.apache.http.params.HttpParams r16) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r15
            r5 = r16
            r6 = r0
            r7 = r2
            if (r7 == 0) goto L_0x001c
            r7 = r2
        L_0x000c:
            r8 = r3
            if (r8 == 0) goto L_0x0022
            r8 = r3
        L_0x0010:
            r9 = r4
            if (r9 == 0) goto L_0x0028
            r9 = r4
        L_0x0014:
            r10 = r5
            if (r10 == 0) goto L_0x002e
            r10 = r5
        L_0x0018:
            r6.<init>(r7, r8, r9, r10)
            return
        L_0x001c:
            r7 = r1
            org.shaded.apache.http.params.HttpParams r7 = r7.getApplicationParams()
            goto L_0x000c
        L_0x0022:
            r8 = r1
            org.shaded.apache.http.params.HttpParams r8 = r8.getClientParams()
            goto L_0x0010
        L_0x0028:
            r9 = r1
            org.shaded.apache.http.params.HttpParams r9 = r9.getRequestParams()
            goto L_0x0014
        L_0x002e:
            r10 = r1
            org.shaded.apache.http.params.HttpParams r10 = r10.getOverrideParams()
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.client.ClientParamsStack.<init>(org.shaded.apache.http.impl.client.ClientParamsStack, org.shaded.apache.http.params.HttpParams, org.shaded.apache.http.params.HttpParams, org.shaded.apache.http.params.HttpParams, org.shaded.apache.http.params.HttpParams):void");
    }

    public final HttpParams getApplicationParams() {
        return this.applicationParams;
    }

    public final HttpParams getClientParams() {
        return this.clientParams;
    }

    public final HttpParams getRequestParams() {
        return this.requestParams;
    }

    public final HttpParams getOverrideParams() {
        return this.overrideParams;
    }

    public Object getParameter(String str) {
        Throwable th;
        String name = str;
        if (name == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Parameter name must not be null.");
            throw th2;
        }
        Object result = null;
        if (this.overrideParams != null) {
            result = this.overrideParams.getParameter(name);
        }
        if (result == null && this.requestParams != null) {
            result = this.requestParams.getParameter(name);
        }
        if (result == null && this.clientParams != null) {
            result = this.clientParams.getParameter(name);
        }
        if (result == null && this.applicationParams != null) {
            result = this.applicationParams.getParameter(name);
        }
        return result;
    }

    public HttpParams setParameter(String str, Object obj) throws UnsupportedOperationException {
        Throwable th;
        String str2 = str;
        Object obj2 = obj;
        Throwable th2 = th;
        new UnsupportedOperationException("Setting parameters in a stack is not supported.");
        throw th2;
    }

    public boolean removeParameter(String str) {
        Throwable th;
        String str2 = str;
        Throwable th2 = th;
        new UnsupportedOperationException("Removing parameters in a stack is not supported.");
        throw th2;
    }

    public HttpParams copy() {
        return this;
    }
}
