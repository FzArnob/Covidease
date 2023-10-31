package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.io.IOException;

final class zab implements FastParser.zaa<Long> {
    zab() {
    }

    public final /* synthetic */ Object zah(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        BufferedReader bufferedReader2 = bufferedReader;
        BufferedReader bufferedReader3 = bufferedReader2;
        return Long.valueOf(fastParser.zae(bufferedReader2));
    }
}
