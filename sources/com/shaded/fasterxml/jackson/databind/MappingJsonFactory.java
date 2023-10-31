package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.core.JsonFactory;
import com.shaded.fasterxml.jackson.core.format.InputAccessor;
import com.shaded.fasterxml.jackson.core.format.MatchStrength;
import java.io.IOException;

public class MappingJsonFactory extends JsonFactory {
    private static final long serialVersionUID = -6744103724013275513L;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MappingJsonFactory() {
        this((ObjectMapper) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MappingJsonFactory(com.shaded.fasterxml.jackson.databind.ObjectMapper r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r1
            if (r2 != 0) goto L_0x0018
            r2 = r0
            com.shaded.fasterxml.jackson.databind.ObjectMapper r3 = new com.shaded.fasterxml.jackson.databind.ObjectMapper
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            r4.<init>((com.shaded.fasterxml.jackson.core.JsonFactory) r5)
            com.shaded.fasterxml.jackson.core.JsonFactory r2 = r2.setCodec(r3)
        L_0x0018:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.MappingJsonFactory.<init>(com.shaded.fasterxml.jackson.databind.ObjectMapper):void");
    }

    public final ObjectMapper getCodec() {
        return (ObjectMapper) this._objectCodec;
    }

    public JsonFactory copy() {
        JsonFactory jsonFactory;
        _checkInvalidCopy(MappingJsonFactory.class);
        new MappingJsonFactory((ObjectMapper) null);
        return jsonFactory;
    }

    public String getFormatName() {
        return JsonFactory.FORMAT_NAME_JSON;
    }

    public MatchStrength hasFormat(InputAccessor inputAccessor) throws IOException {
        InputAccessor inputAccessor2 = inputAccessor;
        if (getClass() == MappingJsonFactory.class) {
            return hasJSONFormat(inputAccessor2);
        }
        return null;
    }
}
