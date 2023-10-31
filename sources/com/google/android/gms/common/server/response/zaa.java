package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.io.IOException;

final class zaa implements FastParser.zaa<Integer> {
    zaa() {
    }

    public final /* synthetic */ Object zah(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        BufferedReader bufferedReader2 = bufferedReader;
        BufferedReader bufferedReader3 = bufferedReader2;
        return Integer.valueOf(fastParser.zad(bufferedReader2));
    }
}
