package com.google.appinventor.components.runtime.util;

public class BiggerFuture extends Thread {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BiggerFuture(gnu.mapping.Procedure r19, gnu.mapping.InPort r20, gnu.mapping.OutPort r21, gnu.mapping.OutPort r22, java.lang.String r23, long r24) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r22
            r5 = r23
            r6 = r24
            r8 = r0
            java.lang.ThreadGroup r9 = new java.lang.ThreadGroup
            r16 = r9
            r9 = r16
            r10 = r16
            java.lang.String r11 = "biggerthreads"
            r10.<init>(r11)
            gnu.mapping.RunnableClosure r10 = new gnu.mapping.RunnableClosure
            r16 = r10
            r10 = r16
            r11 = r16
            r12 = r1
            r13 = r2
            r14 = r3
            r15 = r4
            r11.<init>(r12, r13, r14, r15)
            r11 = r5
            r12 = r6
            r8.<init>(r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.BiggerFuture.<init>(gnu.mapping.Procedure, gnu.mapping.InPort, gnu.mapping.OutPort, gnu.mapping.OutPort, java.lang.String, long):void");
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer stringBuffer2 = stringBuffer;
        StringBuffer stringBuffer3 = stringBuffer2;
        StringBuffer append = stringBuffer2.append("#<future ");
        StringBuffer append2 = stringBuffer3.append(getName());
        StringBuffer append3 = stringBuffer3.append(">");
        return stringBuffer3.toString();
    }
}
