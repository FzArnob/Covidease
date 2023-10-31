package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;

final class zag implements FastParser.zaa<BigInteger> {
    zag() {
    }

    public final /* synthetic */ Object zah(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        BufferedReader bufferedReader2 = bufferedReader;
        BufferedReader bufferedReader3 = bufferedReader2;
        return fastParser.zaf(bufferedReader2);
    }
}
