package com.google.android.gms.common.server.response;

import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@ShowFirstParty
@KeepForSdk
public class FastParser<T extends FastJsonResponse> {
    private static final char[] zaqg = {'u', 'l', 'l'};
    private static final char[] zaqh = {'r', 'u', 'e'};
    private static final char[] zaqi = {'r', 'u', 'e', '\"'};
    private static final char[] zaqj = {'a', 'l', 's', 'e'};
    private static final char[] zaqk = {'a', 'l', 's', 'e', '\"'};
    private static final char[] zaql = {10};
    private static final zaa<Integer> zaqn;
    private static final zaa<Long> zaqo;
    private static final zaa<Float> zaqp;
    private static final zaa<Double> zaqq;
    private static final zaa<Boolean> zaqr;
    private static final zaa<String> zaqs;
    private static final zaa<BigInteger> zaqt;
    private static final zaa<BigDecimal> zaqu;
    private final char[] zaqb = new char[1];
    private final char[] zaqc = new char[32];
    private final char[] zaqd = new char[1024];
    private final StringBuilder zaqe;
    private final StringBuilder zaqf;
    private final Stack<Integer> zaqm;

    private interface zaa<O> {
        O zah(FastParser fastParser, BufferedReader bufferedReader) throws ParseException, IOException;
    }

    public FastParser() {
        StringBuilder sb;
        StringBuilder sb2;
        Stack<Integer> stack;
        new StringBuilder(32);
        this.zaqe = sb;
        new StringBuilder(1024);
        this.zaqf = sb2;
        new Stack<>();
        this.zaqm = stack;
    }

    @ShowFirstParty
    @KeepForSdk
    public static class ParseException extends Exception {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ParseException(String str) {
            super(str);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ParseException(String str, Throwable th) {
            super(str, th);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ParseException(Throwable th) {
            super(th);
        }
    }

    @KeepForSdk
    public void parse(InputStream inputStream, T t) throws ParseException {
        BufferedReader bufferedReader;
        Reader reader;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        StringBuilder sb;
        T t2 = t;
        new InputStreamReader(inputStream);
        new BufferedReader(reader, 1024);
        BufferedReader bufferedReader2 = bufferedReader;
        try {
            Integer push = this.zaqm.push(0);
            T t3 = t2;
            BufferedReader bufferedReader3 = bufferedReader2;
            char zaj = zaj(bufferedReader3);
            char c = zaj;
            switch (zaj) {
                case 0:
                    Throwable th5 = th2;
                    new ParseException("No data to parse");
                    throw th5;
                case '[':
                    Integer push2 = this.zaqm.push(5);
                    T t4 = t3;
                    BufferedReader bufferedReader4 = bufferedReader3;
                    Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t4.getFieldMappings();
                    Map<String, FastJsonResponse.Field<?, ?>> map = fieldMappings;
                    if (fieldMappings.size() == 1) {
                        FastJsonResponse.Field field = (FastJsonResponse.Field) map.entrySet().iterator().next().getValue();
                        t4.addConcreteTypeArrayInternal(field, field.zapv, zaa(bufferedReader4, (FastJsonResponse.Field<?, ?>) field));
                        break;
                    } else {
                        Throwable th6 = th3;
                        new ParseException("Object array response class must have a single Field");
                        throw th6;
                    }
                case '{':
                    Integer push3 = this.zaqm.push(1);
                    boolean zaa2 = zaa(bufferedReader3, (FastJsonResponse) t3);
                    break;
                default:
                    Throwable th7 = th4;
                    new StringBuilder(19);
                    new ParseException(sb.append("Unexpected token: ").append(c).toString());
                    throw th7;
            }
            zak(0);
            try {
                bufferedReader2.close();
            } catch (IOException e) {
                int w = Log.w("FastParser", "Failed to close reader while parsing.");
            }
        } catch (IOException e2) {
            IOException iOException = e2;
            Throwable th8 = th;
            new ParseException((Throwable) iOException);
            throw th8;
        } catch (Throwable th9) {
            Throwable th10 = th9;
            try {
                bufferedReader2.close();
            } catch (IOException e3) {
                int w2 = Log.w("FastParser", "Failed to close reader while parsing.");
            }
            throw th10;
        }
    }

    private final boolean zaa(BufferedReader bufferedReader, FastJsonResponse fastJsonResponse) throws ParseException, IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        HashMap hashMap;
        HashMap hashMap2;
        Throwable th7;
        String str;
        String str2;
        String str3;
        String str4;
        Throwable th8;
        StringBuilder sb2;
        Throwable th9;
        StringBuilder sb3;
        BufferedReader bufferedReader2 = bufferedReader;
        FastJsonResponse fastJsonResponse2 = fastJsonResponse;
        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse2.getFieldMappings();
        String zaa2 = zaa(bufferedReader2);
        String str5 = zaa2;
        if (zaa2 == null) {
            zak(1);
            return false;
        }
        while (str5 != null) {
            FastJsonResponse.Field field = fieldMappings.get(str5);
            FastJsonResponse.Field field2 = field;
            if (field == null) {
                str5 = zab(bufferedReader2);
            } else {
                Integer push = this.zaqm.push(4);
                switch (field2.zapr) {
                    case 0:
                        if (!field2.zaps) {
                            fastJsonResponse2.zaa(field2, zad(bufferedReader2));
                            break;
                        } else {
                            fastJsonResponse2.zaa(field2, zaa(bufferedReader2, zaqn));
                            break;
                        }
                    case 1:
                        if (!field2.zaps) {
                            fastJsonResponse2.zaa(field2, zaf(bufferedReader2));
                            break;
                        } else {
                            fastJsonResponse2.zab(field2, zaa(bufferedReader2, zaqt));
                            break;
                        }
                    case 2:
                        if (!field2.zaps) {
                            fastJsonResponse2.zaa(field2, zae(bufferedReader2));
                            break;
                        } else {
                            fastJsonResponse2.zac(field2, zaa(bufferedReader2, zaqo));
                            break;
                        }
                    case 3:
                        if (!field2.zaps) {
                            fastJsonResponse2.zaa(field2, zag(bufferedReader2));
                            break;
                        } else {
                            fastJsonResponse2.zad(field2, zaa(bufferedReader2, zaqp));
                            break;
                        }
                    case 4:
                        if (!field2.zaps) {
                            fastJsonResponse2.zaa(field2, zah(bufferedReader2));
                            break;
                        } else {
                            fastJsonResponse2.zae(field2, zaa(bufferedReader2, zaqq));
                            break;
                        }
                    case 5:
                        if (!field2.zaps) {
                            fastJsonResponse2.zaa(field2, zai(bufferedReader2));
                            break;
                        } else {
                            fastJsonResponse2.zaf(field2, zaa(bufferedReader2, zaqu));
                            break;
                        }
                    case 6:
                        if (!field2.zaps) {
                            fastJsonResponse2.zaa(field2, zaa(bufferedReader2, false));
                            break;
                        } else {
                            fastJsonResponse2.zag(field2, zaa(bufferedReader2, zaqr));
                            break;
                        }
                    case 7:
                        if (!field2.zaps) {
                            fastJsonResponse2.zaa(field2, zac(bufferedReader2));
                            break;
                        } else {
                            fastJsonResponse2.zah(field2, zaa(bufferedReader2, zaqs));
                            break;
                        }
                    case 8:
                        fastJsonResponse2.zaa(field2, Base64Utils.decode(zaa(bufferedReader2, this.zaqd, this.zaqf, zaql)));
                        break;
                    case 9:
                        fastJsonResponse2.zaa(field2, Base64Utils.decodeUrlSafe(zaa(bufferedReader2, this.zaqd, this.zaqf, zaql)));
                        break;
                    case 10:
                        FastJsonResponse fastJsonResponse3 = fastJsonResponse2;
                        FastJsonResponse.Field field3 = field2;
                        BufferedReader bufferedReader3 = bufferedReader2;
                        char zaj = zaj(bufferedReader3);
                        char c = zaj;
                        if (zaj != 'n') {
                            if (c == '{') {
                                Integer push2 = this.zaqm.push(1);
                                new HashMap();
                                HashMap hashMap3 = hashMap;
                                while (true) {
                                    switch (zaj(bufferedReader3)) {
                                        case 0:
                                            Throwable th10 = th7;
                                            new ParseException("Unexpected EOF");
                                            throw th10;
                                        case '\"':
                                            String zab = zab(bufferedReader3, this.zaqc, this.zaqe, (char[]) null);
                                            if (zaj(bufferedReader3) == ':') {
                                                if (zaj(bufferedReader3) == '\"') {
                                                    Object put = hashMap3.put(zab, zab(bufferedReader3, this.zaqc, this.zaqe, (char[]) null));
                                                    char zaj2 = zaj(bufferedReader3);
                                                    char c2 = zaj2;
                                                    if (zaj2 == ',') {
                                                        break;
                                                    } else if (c2 == '}') {
                                                        zak(1);
                                                        hashMap2 = hashMap3;
                                                        break;
                                                    } else {
                                                        Throwable th11 = th8;
                                                        new StringBuilder(48);
                                                        new ParseException(sb2.append("Unexpected character while parsing string map: ").append(c2).toString());
                                                        throw th11;
                                                    }
                                                } else {
                                                    ParseException parseException = r25;
                                                    String valueOf = String.valueOf(zab);
                                                    String str6 = valueOf;
                                                    if (valueOf.length() != 0) {
                                                        str4 = "Expected String value for key ".concat(str6);
                                                    } else {
                                                        str4 = str3;
                                                        new String("Expected String value for key ");
                                                    }
                                                    ParseException parseException2 = new ParseException(str4);
                                                    throw parseException;
                                                }
                                            } else {
                                                ParseException parseException3 = r25;
                                                String valueOf2 = String.valueOf(zab);
                                                String str7 = valueOf2;
                                                if (valueOf2.length() != 0) {
                                                    str2 = "No map value found for key ".concat(str7);
                                                } else {
                                                    str2 = str;
                                                    new String("No map value found for key ");
                                                }
                                                ParseException parseException4 = new ParseException(str2);
                                                throw parseException3;
                                            }
                                        case '}':
                                            zak(1);
                                            hashMap2 = hashMap3;
                                            break;
                                    }
                                }
                            } else {
                                Throwable th12 = th6;
                                new ParseException("Expected start of a map object");
                                throw th12;
                            }
                        } else {
                            zab(bufferedReader3, zaqg);
                            hashMap2 = null;
                        }
                        fastJsonResponse3.zaa(field3, (Map<String, String>) hashMap2);
                        break;
                    case 11:
                        if (field2.zaps) {
                            char zaj3 = zaj(bufferedReader2);
                            char c3 = zaj3;
                            if (zaj3 != 'n') {
                                Integer push3 = this.zaqm.push(5);
                                if (c3 == '[') {
                                    fastJsonResponse2.addConcreteTypeArrayInternal(field2, field2.zapv, zaa(bufferedReader2, (FastJsonResponse.Field<?, ?>) field2));
                                    break;
                                } else {
                                    Throwable th13 = th5;
                                    new ParseException("Expected array start");
                                    throw th13;
                                }
                            } else {
                                zab(bufferedReader2, zaqg);
                                fastJsonResponse2.addConcreteTypeArrayInternal(field2, field2.zapv, (ArrayList) null);
                                break;
                            }
                        } else {
                            char zaj4 = zaj(bufferedReader2);
                            char c4 = zaj4;
                            if (zaj4 == 'n') {
                                zab(bufferedReader2, zaqg);
                                fastJsonResponse2.addConcreteTypeInternal(field2, field2.zapv, null);
                                break;
                            } else {
                                Integer push4 = this.zaqm.push(1);
                                if (c4 != '{') {
                                    Throwable th14 = th2;
                                    new ParseException("Expected start of object");
                                    throw th14;
                                }
                                try {
                                    FastJsonResponse zacp = field2.zacp();
                                    boolean zaa3 = zaa(bufferedReader2, zacp);
                                    fastJsonResponse2.addConcreteTypeInternal(field2, field2.zapv, zacp);
                                    break;
                                } catch (InstantiationException e) {
                                    InstantiationException instantiationException = e;
                                    Throwable th15 = th4;
                                    new ParseException("Error instantiating inner object", instantiationException);
                                    throw th15;
                                } catch (IllegalAccessException e2) {
                                    IllegalAccessException illegalAccessException = e2;
                                    Throwable th16 = th3;
                                    new ParseException("Error instantiating inner object", illegalAccessException);
                                    throw th16;
                                }
                            }
                        }
                    default:
                        Throwable th17 = th;
                        int i = field2.zapr;
                        new StringBuilder(30);
                        new ParseException(sb.append("Invalid field type ").append(i).toString());
                        throw th17;
                }
                zak(4);
                zak(2);
                char zaj5 = zaj(bufferedReader2);
                char c5 = zaj5;
                switch (zaj5) {
                    case ',':
                        str5 = zaa(bufferedReader2);
                        break;
                    case '}':
                        str5 = null;
                        break;
                    default:
                        Throwable th18 = th9;
                        new StringBuilder(55);
                        new ParseException(sb3.append("Expected end of object or field separator, but found: ").append(c5).toString());
                        throw th18;
                }
            }
        }
        zak(1);
        return true;
    }

    private final String zaa(BufferedReader bufferedReader) throws ParseException, IOException {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        BufferedReader bufferedReader2 = bufferedReader;
        Integer push = this.zaqm.push(2);
        char zaj = zaj(bufferedReader2);
        char c = zaj;
        switch (zaj) {
            case '\"':
                Integer push2 = this.zaqm.push(3);
                String zab = zab(bufferedReader2, this.zaqc, this.zaqe, (char[]) null);
                zak(3);
                if (zaj(bufferedReader2) == ':') {
                    return zab;
                }
                Throwable th3 = th;
                new ParseException("Expected key/value separator");
                throw th3;
            case ']':
                zak(2);
                zak(1);
                zak(5);
                return null;
            case '}':
                zak(2);
                return null;
            default:
                Throwable th4 = th2;
                new StringBuilder(19);
                new ParseException(sb.append("Unexpected token: ").append(c).toString());
                throw th4;
        }
    }

    private final String zab(BufferedReader bufferedReader) throws ParseException, IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        boolean z;
        Throwable th4;
        StringBuilder sb;
        Throwable th5;
        char c;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        StringBuilder sb2;
        BufferedReader bufferedReader2 = bufferedReader;
        bufferedReader2.mark(1024);
        switch (zaj(bufferedReader2)) {
            case '\"':
                boolean z2 = false;
                if (bufferedReader2.read(this.zaqb) == -1) {
                    Throwable th9 = th7;
                    new ParseException("Unexpected EOF while parsing string");
                    throw th9;
                }
                char c2 = this.zaqb[0];
                do {
                    if (c2 == '\"' && !z2) {
                        break;
                    } else {
                        if (c2 == '\\') {
                            z2 = !z2;
                        } else {
                            z2 = false;
                        }
                        if (bufferedReader2.read(this.zaqb) == -1) {
                            Throwable th10 = th5;
                            new ParseException("Unexpected EOF while parsing string");
                            throw th10;
                        }
                        c = this.zaqb[0];
                        c2 = c;
                    }
                } while (!Character.isISOControl(c));
                Throwable th11 = th6;
                new ParseException("Unexpected control character while reading string");
                throw th11;
            case ',':
                Throwable th12 = th;
                new ParseException("Missing value");
                throw th12;
            case '[':
                Integer push = this.zaqm.push(5);
                bufferedReader2.mark(32);
                if (zaj(bufferedReader2) != ']') {
                    bufferedReader2.reset();
                    boolean z3 = false;
                    boolean z4 = false;
                    int i = 1;
                    while (i > 0) {
                        char zaj = zaj(bufferedReader2);
                        char c3 = zaj;
                        if (zaj == 0) {
                            Throwable th13 = th2;
                            new ParseException("Unexpected EOF while parsing array");
                            throw th13;
                        } else if (Character.isISOControl(c3)) {
                            Throwable th14 = th3;
                            new ParseException("Unexpected control character while reading array");
                            throw th14;
                        } else {
                            if (c3 == '\"' && !z3) {
                                z4 = !z4;
                            }
                            if (c3 == '[' && !z4) {
                                i++;
                            }
                            if (c3 == ']' && !z4) {
                                i--;
                            }
                            if (c3 != '\\' || !z4) {
                                z3 = false;
                            } else {
                                if (!z3) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                z3 = z;
                            }
                        }
                    }
                    zak(5);
                    break;
                } else {
                    zak(5);
                    break;
                }
            case '{':
                Integer push2 = this.zaqm.push(1);
                bufferedReader2.mark(32);
                char zaj2 = zaj(bufferedReader2);
                char c4 = zaj2;
                if (zaj2 == '}') {
                    zak(1);
                    break;
                } else if (c4 == '\"') {
                    bufferedReader2.reset();
                    String zaa2 = zaa(bufferedReader2);
                    do {
                    } while (zab(bufferedReader2) != null);
                    zak(1);
                    break;
                } else {
                    Throwable th15 = th4;
                    new StringBuilder(18);
                    new ParseException(sb.append("Unexpected token ").append(c4).toString());
                    throw th15;
                }
            default:
                bufferedReader2.reset();
                int zaa3 = zaa(bufferedReader2, this.zaqd);
                break;
        }
        char zaj3 = zaj(bufferedReader2);
        char c5 = zaj3;
        switch (zaj3) {
            case ',':
                zak(2);
                return zaa(bufferedReader2);
            case '}':
                zak(2);
                return null;
            default:
                Throwable th16 = th8;
                new StringBuilder(18);
                new ParseException(sb2.append("Unexpected token ").append(c5).toString());
                throw th16;
        }
    }

    /* access modifiers changed from: private */
    public final String zac(BufferedReader bufferedReader) throws ParseException, IOException {
        return zaa(bufferedReader, this.zaqc, this.zaqe, (char[]) null);
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <O> java.util.ArrayList<O> zaa(java.io.BufferedReader r11, com.google.android.gms.common.server.response.FastParser.zaa<O> r12) throws com.google.android.gms.common.server.response.FastParser.ParseException, java.io.IOException {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r5 = r0
            r6 = r1
            char r5 = r5.zaj(r6)
            r9 = r5
            r5 = r9
            r6 = r9
            r3 = r6
            r6 = 110(0x6e, float:1.54E-43)
            if (r5 != r6) goto L_0x001b
            r5 = r0
            r6 = r1
            char[] r7 = zaqg
            r5.zab((java.io.BufferedReader) r6, (char[]) r7)
            r5 = 0
            r0 = r5
        L_0x001a:
            return r0
        L_0x001b:
            r5 = r3
            r6 = 91
            if (r5 == r6) goto L_0x002c
            com.google.android.gms.common.server.response.FastParser$ParseException r5 = new com.google.android.gms.common.server.response.FastParser$ParseException
            r9 = r5
            r5 = r9
            r6 = r9
            java.lang.String r7 = "Expected start of array"
            r6.<init>((java.lang.String) r7)
            throw r5
        L_0x002c:
            r5 = r0
            java.util.Stack<java.lang.Integer> r5 = r5.zaqm
            r6 = 5
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r5 = r5.push(r6)
            java.util.ArrayList r5 = new java.util.ArrayList
            r9 = r5
            r5 = r9
            r6 = r9
            r6.<init>()
            r4 = r5
        L_0x0041:
            r5 = r1
            r6 = 1024(0x400, float:1.435E-42)
            r5.mark(r6)
            r5 = r0
            r6 = r1
            char r5 = r5.zaj(r6)
            switch(r5) {
                case 0: goto L_0x006a;
                case 44: goto L_0x0061;
                case 93: goto L_0x0062;
                default: goto L_0x0050;
            }
        L_0x0050:
            r5 = r1
            r5.reset()
            r5 = r4
            r6 = r2
            r7 = r0
            r8 = r1
            java.lang.Object r6 = r6.zah(r7, r8)
            boolean r5 = r5.add(r6)
            goto L_0x0041
        L_0x0061:
            goto L_0x0041
        L_0x0062:
            r5 = r0
            r6 = 5
            r5.zak(r6)
            r5 = r4
            r0 = r5
            goto L_0x001a
        L_0x006a:
            com.google.android.gms.common.server.response.FastParser$ParseException r5 = new com.google.android.gms.common.server.response.FastParser$ParseException
            r9 = r5
            r5 = r9
            r6 = r9
            java.lang.String r7 = "Unexpected EOF"
            r6.<init>((java.lang.String) r7)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastParser.zaa(java.io.BufferedReader, com.google.android.gms.common.server.response.FastParser$zaa):java.util.ArrayList");
    }

    private final String zaa(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        Throwable th;
        BufferedReader bufferedReader2 = bufferedReader;
        char[] cArr3 = cArr;
        StringBuilder sb2 = sb;
        char[] cArr4 = cArr2;
        switch (zaj(bufferedReader2)) {
            case '\"':
                return zab(bufferedReader2, cArr3, sb2, cArr4);
            case 'n':
                zab(bufferedReader2, zaqg);
                return null;
            default:
                Throwable th2 = th;
                new ParseException("Expected string");
                throw th2;
        }
    }

    private static String zab(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        Throwable th;
        boolean z;
        Throwable th2;
        BufferedReader bufferedReader2 = bufferedReader;
        char[] cArr3 = cArr;
        StringBuilder sb2 = sb;
        char[] cArr4 = cArr2;
        sb2.setLength(0);
        boolean z2 = false;
        boolean z3 = false;
        bufferedReader2.mark(cArr3.length);
        while (true) {
            int read = bufferedReader2.read(cArr3);
            int i = read;
            if (read != -1) {
                int i2 = 0;
                while (i2 < i) {
                    char c = cArr3[i2];
                    char c2 = c;
                    if (Character.isISOControl(c)) {
                        char c3 = c2;
                        char[] cArr5 = cArr4;
                        char[] cArr6 = cArr5;
                        if (cArr5 != null) {
                            int i3 = 0;
                            while (true) {
                                if (i3 >= cArr6.length) {
                                    break;
                                } else if (cArr6[i3] == c3) {
                                    z = true;
                                    break;
                                } else {
                                    i3++;
                                }
                            }
                        }
                        z = false;
                        if (!z) {
                            Throwable th3 = th2;
                            new ParseException("Unexpected control character while reading string");
                            throw th3;
                        }
                    }
                    if (c2 != '\"' || z2) {
                        if (c2 == '\\') {
                            z2 = !z2;
                            z3 = true;
                        } else {
                            z2 = false;
                        }
                        i2++;
                    } else {
                        StringBuilder append = sb2.append(cArr3, 0, i2);
                        bufferedReader2.reset();
                        long skip = bufferedReader2.skip((long) (i2 + 1));
                        if (z3) {
                            return JsonUtils.unescapeString(sb2.toString());
                        }
                        return sb2.toString();
                    }
                }
                StringBuilder append2 = sb2.append(cArr3, 0, i);
                bufferedReader2.mark(cArr3.length);
            } else {
                Throwable th4 = th;
                new ParseException("Unexpected EOF while parsing string");
                throw th4;
            }
        }
    }

    /* access modifiers changed from: private */
    public final int zad(BufferedReader bufferedReader) throws ParseException, IOException {
        Throwable th;
        int i;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        int zaa2 = zaa(bufferedReader, this.zaqd);
        int i2 = zaa2;
        if (zaa2 == 0) {
            return 0;
        }
        int i3 = i2;
        char[] cArr = this.zaqd;
        int i4 = 0;
        boolean z = false;
        int i5 = 0;
        if (i3 > 0) {
            if (cArr[0] == '-') {
                z = true;
                i = Integer.MIN_VALUE;
                i5 = 0 + 1;
            } else {
                i = -2147483647;
            }
            if (i5 < i3) {
                int i6 = i5;
                i5++;
                int digit = Character.digit(cArr[i6], 10);
                int i7 = digit;
                if (digit < 0) {
                    Throwable th7 = th6;
                    new ParseException("Unexpected non-digit character");
                    throw th7;
                }
                i4 = -i7;
            }
            while (i5 < i3) {
                int i8 = i5;
                i5++;
                int digit2 = Character.digit(cArr[i8], 10);
                int i9 = digit2;
                if (digit2 < 0) {
                    Throwable th8 = th3;
                    new ParseException("Unexpected non-digit character");
                    throw th8;
                } else if (i4 < -214748364) {
                    Throwable th9 = th4;
                    new ParseException("Number too large");
                    throw th9;
                } else {
                    int i10 = i4 * 10;
                    int i11 = i10;
                    if (i10 < i + i9) {
                        Throwable th10 = th5;
                        new ParseException("Number too large");
                        throw th10;
                    }
                    i4 = i11 - i9;
                }
            }
            if (!z) {
                return -i4;
            }
            if (i5 > 1) {
                return i4;
            }
            Throwable th11 = th2;
            new ParseException("No digits to parse");
            throw th11;
        }
        Throwable th12 = th;
        new ParseException("No number to parse");
        throw th12;
    }

    /* access modifiers changed from: private */
    public final long zae(BufferedReader bufferedReader) throws ParseException, IOException {
        Throwable th;
        long j;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        int zaa2 = zaa(bufferedReader, this.zaqd);
        int i = zaa2;
        if (zaa2 == 0) {
            return 0;
        }
        int i2 = i;
        char[] cArr = this.zaqd;
        long j2 = 0;
        boolean z = false;
        int i3 = 0;
        if (i2 > 0) {
            if (cArr[0] == '-') {
                z = true;
                j = Long.MIN_VALUE;
                i3 = 0 + 1;
            } else {
                j = -9223372036854775807L;
            }
            if (i3 < i2) {
                int i4 = i3;
                i3++;
                int digit = Character.digit(cArr[i4], 10);
                int i5 = digit;
                if (digit < 0) {
                    Throwable th7 = th6;
                    new ParseException("Unexpected non-digit character");
                    throw th7;
                }
                j2 = (long) (-i5);
            }
            while (i3 < i2) {
                int i6 = i3;
                i3++;
                int digit2 = Character.digit(cArr[i6], 10);
                int i7 = digit2;
                if (digit2 < 0) {
                    Throwable th8 = th3;
                    new ParseException("Unexpected non-digit character");
                    throw th8;
                } else if (j2 < -922337203685477580L) {
                    Throwable th9 = th4;
                    new ParseException("Number too large");
                    throw th9;
                } else {
                    long j3 = j2 * 10;
                    long j4 = j3;
                    if (j3 < j + ((long) i7)) {
                        Throwable th10 = th5;
                        new ParseException("Number too large");
                        throw th10;
                    }
                    j2 = j4 - ((long) i7);
                }
            }
            if (!z) {
                return -j2;
            }
            if (i3 > 1) {
                return j2;
            }
            Throwable th11 = th2;
            new ParseException("No digits to parse");
            throw th11;
        }
        Throwable th12 = th;
        new ParseException("No number to parse");
        throw th12;
    }

    /* access modifiers changed from: private */
    public final BigInteger zaf(BufferedReader bufferedReader) throws ParseException, IOException {
        BigInteger bigInteger;
        String str;
        int zaa2 = zaa(bufferedReader, this.zaqd);
        int i = zaa2;
        if (zaa2 == 0) {
            return null;
        }
        new String(this.zaqd, 0, i);
        new BigInteger(str);
        return bigInteger;
    }

    private final boolean zaa(BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        FastParser fastParser = this;
        BufferedReader bufferedReader2 = bufferedReader;
        boolean z2 = z;
        while (true) {
            char zaj = fastParser.zaj(bufferedReader2);
            char c = zaj;
            switch (zaj) {
                case '\"':
                    if (z2) {
                        Throwable th3 = th2;
                        new ParseException("No boolean value found in string");
                        throw th3;
                    }
                    z2 = true;
                    bufferedReader2 = bufferedReader2;
                    fastParser = fastParser;
                case 'f':
                    fastParser.zab(bufferedReader2, z2 ? zaqk : zaqj);
                    return false;
                case 'n':
                    fastParser.zab(bufferedReader2, zaqg);
                    return false;
                case 't':
                    fastParser.zab(bufferedReader2, z2 ? zaqi : zaqh);
                    return true;
                default:
                    Throwable th4 = th;
                    new StringBuilder(19);
                    new ParseException(sb.append("Unexpected token: ").append(c).toString());
                    throw th4;
            }
        }
    }

    /* access modifiers changed from: private */
    public final float zag(BufferedReader bufferedReader) throws ParseException, IOException {
        String str;
        int zaa2 = zaa(bufferedReader, this.zaqd);
        int i = zaa2;
        if (zaa2 == 0) {
            return 0.0f;
        }
        new String(this.zaqd, 0, i);
        return Float.parseFloat(str);
    }

    /* access modifiers changed from: private */
    public final double zah(BufferedReader bufferedReader) throws ParseException, IOException {
        String str;
        int zaa2 = zaa(bufferedReader, this.zaqd);
        int i = zaa2;
        if (zaa2 == 0) {
            return 0.0d;
        }
        new String(this.zaqd, 0, i);
        return Double.parseDouble(str);
    }

    /* access modifiers changed from: private */
    public final BigDecimal zai(BufferedReader bufferedReader) throws ParseException, IOException {
        BigDecimal bigDecimal;
        String str;
        int zaa2 = zaa(bufferedReader, this.zaqd);
        int i = zaa2;
        if (zaa2 == 0) {
            return null;
        }
        new String(this.zaqd, 0, i);
        new BigDecimal(str);
        return bigDecimal;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <T extends com.google.android.gms.common.server.response.FastJsonResponse> java.util.ArrayList<T> zaa(java.io.BufferedReader r14, com.google.android.gms.common.server.response.FastJsonResponse.Field<?, ?> r15) throws com.google.android.gms.common.server.response.FastParser.ParseException, java.io.IOException {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            java.util.ArrayList r6 = new java.util.ArrayList
            r11 = r6
            r6 = r11
            r7 = r11
            r7.<init>()
            r3 = r6
            r6 = r0
            r7 = r1
            char r6 = r6.zaj(r7)
            r11 = r6
            r6 = r11
            r7 = r11
            r4 = r7
            switch(r6) {
                case 93: goto L_0x0044;
                case 110: goto L_0x00a5;
                case 123: goto L_0x004c;
                default: goto L_0x0019;
            }
        L_0x0019:
            com.google.android.gms.common.server.response.FastParser$ParseException r6 = new com.google.android.gms.common.server.response.FastParser$ParseException
            r11 = r6
            r6 = r11
            r7 = r11
            r8 = r4
            r5 = r8
            r8 = 19
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r11 = r8
            r12 = r9
            r8 = r12
            r9 = r11
            r10 = r12
            r11 = r9
            r12 = r10
            r9 = r12
            r10 = r11
            r9.<init>(r10)
            java.lang.String r9 = "Unexpected token: "
            java.lang.StringBuilder r8 = r8.append(r9)
            r9 = r5
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>((java.lang.String) r8)
            throw r6
        L_0x0044:
            r6 = r0
            r7 = 5
            r6.zak(r7)
            r6 = r3
            r0 = r6
        L_0x004b:
            return r0
        L_0x004c:
            r6 = r0
            java.util.Stack<java.lang.Integer> r6 = r6.zaqm
            r7 = 1
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Object r6 = r6.push(r7)
        L_0x0058:
            r6 = r2
            com.google.android.gms.common.server.response.FastJsonResponse r6 = r6.zacp()     // Catch:{ InstantiationException -> 0x00b7, IllegalAccessException -> 0x00c6 }
            r5 = r6
            r6 = r0
            r7 = r1
            r8 = r5
            boolean r6 = r6.zaa((java.io.BufferedReader) r7, (com.google.android.gms.common.server.response.FastJsonResponse) r8)     // Catch:{ InstantiationException -> 0x00b7, IllegalAccessException -> 0x00c6 }
            if (r6 == 0) goto L_0x00b4
            r6 = r3
            r7 = r5
            boolean r6 = r6.add(r7)     // Catch:{ InstantiationException -> 0x00b7, IllegalAccessException -> 0x00c6 }
            r6 = r0
            r7 = r1
            char r6 = r6.zaj(r7)
            r11 = r6
            r6 = r11
            r7 = r11
            r4 = r7
            switch(r6) {
                case 44: goto L_0x00d5;
                case 93: goto L_0x00f9;
                default: goto L_0x007a;
            }
        L_0x007a:
            com.google.android.gms.common.server.response.FastParser$ParseException r6 = new com.google.android.gms.common.server.response.FastParser$ParseException
            r11 = r6
            r6 = r11
            r7 = r11
            r8 = r4
            r5 = r8
            r8 = 19
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r11 = r8
            r12 = r9
            r8 = r12
            r9 = r11
            r10 = r12
            r11 = r9
            r12 = r10
            r9 = r12
            r10 = r11
            r9.<init>(r10)
            java.lang.String r9 = "Unexpected token: "
            java.lang.StringBuilder r8 = r8.append(r9)
            r9 = r5
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>((java.lang.String) r8)
            throw r6
        L_0x00a5:
            r6 = r0
            r7 = r1
            char[] r8 = zaqg
            r6.zab((java.io.BufferedReader) r7, (char[]) r8)
            r6 = r0
            r7 = 5
            r6.zak(r7)
            r6 = 0
            r0 = r6
            goto L_0x004b
        L_0x00b4:
            r6 = r3
            r0 = r6
            goto L_0x004b
        L_0x00b7:
            r6 = move-exception
            r5 = r6
            com.google.android.gms.common.server.response.FastParser$ParseException r6 = new com.google.android.gms.common.server.response.FastParser$ParseException
            r11 = r6
            r6 = r11
            r7 = r11
            java.lang.String r8 = "Error instantiating inner object"
            r9 = r5
            r7.<init>(r8, r9)
            throw r6
        L_0x00c6:
            r6 = move-exception
            r5 = r6
            com.google.android.gms.common.server.response.FastParser$ParseException r6 = new com.google.android.gms.common.server.response.FastParser$ParseException
            r11 = r6
            r6 = r11
            r7 = r11
            java.lang.String r8 = "Error instantiating inner object"
            r9 = r5
            r7.<init>(r8, r9)
            throw r6
        L_0x00d5:
            r6 = r0
            r7 = r1
            char r6 = r6.zaj(r7)
            r7 = 123(0x7b, float:1.72E-43)
            if (r6 == r7) goto L_0x00eb
            com.google.android.gms.common.server.response.FastParser$ParseException r6 = new com.google.android.gms.common.server.response.FastParser$ParseException
            r11 = r6
            r6 = r11
            r7 = r11
            java.lang.String r8 = "Expected start of next object in array"
            r7.<init>((java.lang.String) r8)
            throw r6
        L_0x00eb:
            r6 = r0
            java.util.Stack<java.lang.Integer> r6 = r6.zaqm
            r7 = 1
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Object r6 = r6.push(r7)
            goto L_0x0058
        L_0x00f9:
            r6 = r0
            r7 = 5
            r6.zak(r7)
            r6 = r3
            r0 = r6
            goto L_0x004b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastParser.zaa(java.io.BufferedReader, com.google.android.gms.common.server.response.FastJsonResponse$Field):java.util.ArrayList");
    }

    private final char zaj(BufferedReader bufferedReader) throws ParseException, IOException {
        BufferedReader bufferedReader2 = bufferedReader;
        if (bufferedReader2.read(this.zaqb) == -1) {
            return 0;
        }
        while (Character.isWhitespace(this.zaqb[0])) {
            if (bufferedReader2.read(this.zaqb) == -1) {
                return 0;
            }
        }
        return this.zaqb[0];
    }

    private final int zaa(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i;
        Throwable th;
        Throwable th2;
        Throwable th3;
        boolean z;
        Throwable th4;
        Throwable th5;
        BufferedReader bufferedReader2 = bufferedReader;
        char[] cArr2 = cArr;
        char zaj = zaj(bufferedReader2);
        char c = zaj;
        if (zaj == 0) {
            Throwable th6 = th5;
            new ParseException("Unexpected EOF");
            throw th6;
        } else if (c == ',') {
            Throwable th7 = th4;
            new ParseException("Missing value");
            throw th7;
        } else if (c == 'n') {
            zab(bufferedReader2, zaqg);
            return 0;
        } else {
            bufferedReader2.mark(1024);
            int i2 = 1;
            if (c == '\"') {
                i = 0;
                boolean z2 = false;
                while (i < cArr2.length && bufferedReader2.read(cArr2, i, 1) != -1) {
                    char c2 = cArr2[i];
                    char c3 = c2;
                    if (Character.isISOControl(c2)) {
                        Throwable th8 = th3;
                        new ParseException("Unexpected control character while reading string");
                        throw th8;
                    } else if (c3 != '\"' || z2) {
                        if (c3 == '\\') {
                            z = !z2;
                        } else {
                            z = false;
                        }
                        z2 = z;
                        i++;
                    } else {
                        bufferedReader2.reset();
                        long skip = bufferedReader2.skip((long) (i + 1));
                        return i;
                    }
                }
            } else {
                cArr2[0] = c;
                while (i < cArr2.length && bufferedReader2.read(cArr2, i, 1) != -1) {
                    if (cArr2[i] == '}' || cArr2[i] == ',' || Character.isWhitespace(cArr2[i]) || cArr2[i] == ']') {
                        bufferedReader2.reset();
                        long skip2 = bufferedReader2.skip((long) (i - 1));
                        cArr2[i] = 0;
                        return i;
                    }
                    i2 = i + 1;
                }
            }
            if (i == cArr2.length) {
                Throwable th9 = th2;
                new ParseException("Absurdly long value");
                throw th9;
            }
            Throwable th10 = th;
            new ParseException("Unexpected EOF");
            throw th10;
        }
    }

    private final void zab(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        Throwable th;
        Throwable th2;
        BufferedReader bufferedReader2 = bufferedReader;
        char[] cArr2 = cArr;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < cArr2.length) {
                int read = bufferedReader2.read(this.zaqc, 0, cArr2.length - i2);
                int i3 = read;
                if (read == -1) {
                    Throwable th3 = th;
                    new ParseException("Unexpected EOF");
                    throw th3;
                }
                for (int i4 = 0; i4 < i3; i4++) {
                    if (cArr2[i4 + i2] != this.zaqc[i4]) {
                        Throwable th4 = th2;
                        new ParseException("Unexpected character");
                        throw th4;
                    }
                }
                i = i2 + i3;
            } else {
                return;
            }
        }
    }

    private final void zak(int i) throws ParseException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        int i2 = i;
        if (this.zaqm.isEmpty()) {
            Throwable th3 = th2;
            new StringBuilder(46);
            new ParseException(sb2.append("Expected state ").append(i2).append(" but had empty stack").toString());
            throw th3;
        }
        int intValue = this.zaqm.pop().intValue();
        int i3 = intValue;
        if (intValue != i2) {
            Throwable th4 = th;
            new StringBuilder(46);
            new ParseException(sb.append("Expected state ").append(i2).append(" but had ").append(i3).toString());
            throw th4;
        }
    }

    static /* synthetic */ boolean zaa(FastParser fastParser, BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        boolean z2 = z;
        return fastParser.zaa(bufferedReader, false);
    }

    static {
        zaa<Integer> zaa2;
        zaa<Long> zaa3;
        zaa<Float> zaa4;
        zaa<Double> zaa5;
        zaa<Boolean> zaa6;
        zaa<String> zaa7;
        zaa<BigInteger> zaa8;
        zaa<BigDecimal> zaa9;
        new zaa();
        zaqn = zaa2;
        new zab();
        zaqo = zaa3;
        new zac();
        zaqp = zaa4;
        new zad();
        zaqq = zaa5;
        new zae();
        zaqr = zaa6;
        new zaf();
        zaqs = zaa7;
        new zag();
        zaqt = zaa8;
        new zah();
        zaqu = zaa9;
    }
}
