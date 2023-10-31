package com.google.appinventor.components.runtime.repackaged.org.json;

public class JSONStringer extends JSONWriter {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JSONStringer() {
        /*
            r5 = this;
            r0 = r5
            r1 = r0
            java.io.StringWriter r2 = new java.io.StringWriter
            r4 = r2
            r2 = r4
            r3 = r4
            r3.<init>()
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.JSONStringer.<init>():void");
    }

    public String toString() {
        return this.mode == 'd' ? this.writer.toString() : null;
    }
}
