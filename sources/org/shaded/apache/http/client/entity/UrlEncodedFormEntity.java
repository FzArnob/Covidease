package org.shaded.apache.http.client.entity;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.shaded.apache.http.NameValuePair;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.entity.StringEntity;

@NotThreadSafe
public class UrlEncodedFormEntity extends StringEntity {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public UrlEncodedFormEntity(java.util.List<? extends org.shaded.apache.http.NameValuePair> r8, java.lang.String r9) throws java.io.UnsupportedEncodingException {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r0
            r4 = r1
            r5 = r2
            java.lang.String r4 = org.shaded.apache.http.client.utils.URLEncodedUtils.format(r4, r5)
            r5 = r2
            r3.<init>(r4, r5)
            r3 = r0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r6 = r4
            r4 = r6
            r5 = r6
            r5.<init>()
            java.lang.String r5 = "application/x-www-form-urlencoded; charset="
            java.lang.StringBuilder r4 = r4.append(r5)
            r5 = r2
            if (r5 == 0) goto L_0x002e
            r5 = r2
        L_0x0022:
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.setContentType((java.lang.String) r4)
            return
        L_0x002e:
            java.lang.String r5 = "ISO-8859-1"
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.client.entity.UrlEncodedFormEntity.<init>(java.util.List, java.lang.String):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public UrlEncodedFormEntity(List<? extends NameValuePair> parameters) throws UnsupportedEncodingException {
        this(parameters, "ISO-8859-1");
    }
}
