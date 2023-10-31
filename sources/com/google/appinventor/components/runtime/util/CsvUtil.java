package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.repackaged.org.json.HTTP;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class CsvUtil {
    private CsvUtil() {
    }

    public static YailList fromCsvTable(String str) throws Exception {
        C1125a aVar;
        Reader reader;
        ArrayList arrayList;
        new StringReader(str);
        new C1125a(reader);
        C1125a aVar2 = aVar;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        while (aVar2.hasNext()) {
            boolean add = arrayList2.add(YailList.makeList((List) aVar2.next()));
        }
        return YailList.makeList((List) arrayList2);
    }

    public static YailList fromCsvRow(String str) throws Exception {
        C1125a aVar;
        Reader reader;
        Throwable th;
        Throwable th2;
        new StringReader(str);
        new C1125a(reader);
        C1125a aVar2 = aVar;
        C1125a aVar3 = aVar2;
        if (aVar2.hasNext()) {
            YailList makeList = YailList.makeList((List) aVar3.next());
            if (!aVar3.hasNext()) {
                return makeList;
            }
            Throwable th3 = th2;
            new IllegalArgumentException("CSV text has multiple rows. Expected just one row.");
            throw th3;
        }
        Throwable th4 = th;
        new IllegalArgumentException("CSV text cannot be parsed as a row.");
        throw th4;
    }

    public static String toCsvRow(YailList yailList) {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailList, sb2);
        return sb2.toString();
    }

    public static String toCsvTable(YailList yailList) {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        Object[] array = yailList.toArray();
        Object[] objArr = array;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((YailList) objArr[i], sb2);
            StringBuilder append = sb2.append(HTTP.CRLF);
        }
        return sb2.toString();
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(YailList yailList, StringBuilder sb) {
        StringBuilder sb2 = sb;
        String str = "";
        Object[] array = yailList.toArray();
        Object[] objArr = array;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            StringBuilder append = sb2.append(str).append("\"").append(objArr[i].toString().replaceAll("\"", "\"\"")).append("\"");
            str = ",";
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.util.CsvUtil$a */
    static class C1125a implements Iterator<List<String>> {
        private final Pattern B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = Pattern.compile("\"\"");
        private int BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE = -1;
        private long F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho;
        private int IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2;
        private boolean R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = true;
        private final Reader hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        private Exception f555hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        private final char[] f556hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new char[10240];
        private int roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T;
        private int rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs = -1;

        public C1125a(Reader reader) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = reader;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001f, code lost:
            if (wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(r3.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T) < r0.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2) goto L_0x0021;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean hasNext() {
            /*
                r4 = this;
                r0 = r4
                r1 = r0
                int r1 = r1.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2
                if (r1 != 0) goto L_0x000a
                r1 = r0
                r1.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ()
            L_0x000a:
                r1 = r0
                int r1 = r1.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T
                r2 = r0
                int r2 = r2.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2
                if (r1 < r2) goto L_0x0021
                r1 = r0
                r3 = r1
                r1 = r3
                r2 = r3
                int r2 = r2.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T
                int r1 = r1.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(r2)
                r2 = r0
                int r2 = r2.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2
                if (r1 >= r2) goto L_0x002b
            L_0x0021:
                r1 = r0
                boolean r1 = r1.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB()
                if (r1 == 0) goto L_0x002b
                r1 = 1
                r0 = r1
            L_0x002a:
                return r0
            L_0x002b:
                r1 = 0
                r0 = r1
                goto L_0x002a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.CsvUtil.C1125a.hasNext():boolean");
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x0068  */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x0089  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x00bb  */
        /* JADX WARNING: Removed duplicated region for block: B:3:0x0012  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0044  */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.util.ArrayList<java.lang.String> next() {
            /*
                r12 = this;
                r0 = r12
                java.util.ArrayList r4 = com.google.appinventor.components.runtime.collect.Lists.newArrayList()
                r1 = r4
            L_0x0006:
                r4 = r0
                char[] r4 = r4.f556hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                r5 = r0
                int r5 = r5.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T
                char r4 = r4[r5]
                r5 = 34
                if (r4 == r5) goto L_0x0089
                r4 = r1
                java.lang.String r5 = new java.lang.String
                r10 = r5
                r5 = r10
                r6 = r10
                r7 = r0
                char[] r7 = r7.f556hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                r8 = r0
                int r8 = r8.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T
                r9 = r0
                int r9 = r9.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE
                r6.<init>(r7, r8, r9)
                java.lang.String r5 = r5.trim()
                boolean r4 = r4.add(r5)
            L_0x002c:
                r4 = r0
                int r4 = r4.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs
                if (r4 <= 0) goto L_0x00bb
                r4 = r0
                char[] r4 = r4.f556hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                r5 = r0
                int r5 = r5.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T
                r6 = r0
                int r6 = r6.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs
                int r5 = r5 + r6
                r6 = 1
                int r5 = r5 + -1
                char r4 = r4[r5]
                r5 = 44
                if (r4 != r5) goto L_0x00bb
                r4 = 1
            L_0x0045:
                r2 = r4
                r4 = r0
                r10 = r4
                r4 = r10
                r5 = r10
                int r5 = r5.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T
                r6 = r0
                int r6 = r6.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs
                int r5 = r5 + r6
                r4.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T = r5
                r4 = r0
                r10 = r4
                r4 = r10
                r5 = r10
                r6 = -1
                r10 = r5
                r11 = r6
                r5 = r11
                r6 = r10
                r7 = r11
                r6.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE = r7
                r4.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs = r5
                r4 = r0
                int r4 = r4.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T
                r5 = r0
                int r5 = r5.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2
                if (r4 < r5) goto L_0x0077
                r4 = r0
                r10 = r4
                r4 = r10
                r5 = r10
                int r5 = r5.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T
                int r4 = r4.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(r5)
                r5 = r0
                int r5 = r5.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2
                if (r4 >= r5) goto L_0x00bd
            L_0x0077:
                r4 = 1
            L_0x0078:
                r3 = r4
                r4 = r2
                if (r4 == 0) goto L_0x0086
                r4 = r3
                if (r4 == 0) goto L_0x0086
                r4 = r0
                boolean r4 = r4.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB()
                if (r4 != 0) goto L_0x0006
            L_0x0086:
                r4 = r1
                r0 = r4
                return r0
            L_0x0089:
                java.lang.String r4 = new java.lang.String
                r10 = r4
                r4 = r10
                r5 = r10
                r6 = r0
                char[] r6 = r6.f556hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                r7 = r0
                int r7 = r7.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T
                r8 = 1
                int r7 = r7 + 1
                r8 = r0
                int r8 = r8.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE
                r9 = 2
                int r8 = r8 + -2
                r5.<init>(r6, r7, r8)
                r2 = r4
                r4 = r1
                r5 = r0
                java.util.regex.Pattern r5 = r5.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
                r6 = r2
                java.util.regex.Matcher r5 = r5.matcher(r6)
                java.lang.String r6 = "\""
                java.lang.String r5 = r5.replaceAll(r6)
                java.lang.String r5 = r5.trim()
                boolean r4 = r4.add(r5)
                goto L_0x002c
            L_0x00bb:
                r4 = 0
                goto L_0x0045
            L_0x00bd:
                r4 = 0
                goto L_0x0078
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.CsvUtil.C1125a.next():java.util.ArrayList");
        }

        private int wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(int i) {
            int i2 = i;
            if (this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T > 0) {
                i2 = vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(i2);
            }
            f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ();
            return i2;
        }

        private int vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(int i) {
            int i2 = i;
            int i3 = this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T;
            this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T = 0;
            int i4 = this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2 - i3;
            int i5 = i4;
            if (i4 > 0) {
                char[] cArr = this.f556hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                System.arraycopy(cArr, i3, cArr, 0, i5);
            }
            this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2 -= i3;
            this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho += (long) i3;
            return i2 - i3;
        }

        private void f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ() {
            int i = 10240 - this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2;
            while (this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe && i > 0) {
                try {
                    int read = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.read(this.f556hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2, i);
                    int i2 = read;
                    if (read == -1) {
                        this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = false;
                    } else {
                        this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2 += i2;
                        i -= i2;
                    }
                } catch (IOException e) {
                    this.f555hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = e;
                    this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = false;
                }
            }
        }

        private boolean Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB() {
            if (this.f556hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME[this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T] == '\"') {
                return vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T + 1);
            }
            return vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R(this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T);
        }

        /* renamed from: vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq  reason: collision with other method in class */
        private boolean m66vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(int i) {
            Exception exc;
            int i2 = i;
            while (true) {
                if (i2 >= this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2) {
                    int wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(i2);
                    i2 = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
                    if (wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou >= this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2) {
                        new IllegalArgumentException("Syntax Error. unclosed quoted cell");
                        this.f555hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = exc;
                        return false;
                    }
                }
                if (this.f556hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME[i2] == '\"') {
                    int hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO(i2 + 1);
                    i2 = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
                    if (hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO == this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2 || this.f556hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME[i2] != '\"') {
                        this.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE = i2 - this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T;
                    }
                }
                i2++;
            }
            this.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE = i2 - this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T;
            return hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO(i2);
        }

        /* renamed from: hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO  reason: collision with other method in class */
        private boolean m65hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO(int i) {
            Exception exc;
            int i2 = i;
            while (true) {
                if (i2 >= this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2) {
                    int wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(i2);
                    i2 = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
                    if (wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou >= this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2) {
                        this.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs = this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2 - this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T;
                        return true;
                    }
                }
                switch (this.f556hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME[i2]) {
                    case 9:
                    case ' ':
                        i2++;
                    case 10:
                    case ',':
                        this.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO(i2 + 1) - this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T;
                        return true;
                    case 13:
                        int hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO(i2 + 1);
                        this.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs = (this.f556hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME[hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO] == 10 ? hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO(hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO + 1) : hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO) - this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T;
                        return true;
                    default:
                        new IOException("Syntax Error: non-whitespace between closing quote and delimiter or end");
                        this.f555hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = exc;
                        return false;
                }
            }
        }

        private int hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO(int i) {
            int i2 = i;
            return i2 < this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2 ? i2 : wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(i2);
        }

        private boolean vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R(int i) {
            Exception exc;
            int i2 = i;
            while (true) {
                if (i2 >= this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2) {
                    int wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(i2);
                    i2 = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
                    if (wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou >= this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2) {
                        int i3 = this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2 - this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T;
                        int i4 = i3;
                        this.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE = i4;
                        this.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs = i3;
                        return true;
                    }
                }
                switch (this.f556hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME[i2]) {
                    case 10:
                    case ',':
                        this.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE = i2 - this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T;
                        this.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs = this.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE + 1;
                        return true;
                    case 13:
                        this.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE = i2 - this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T;
                        int hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO(i2 + 1);
                        this.rt5vY3dr7aqqLpGGqZnuBZtCcoybaeOtN6eJM7wVAxcn7hidZNH6rGtSnPCIECFs = (this.f556hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME[hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO] == 10 ? hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO(hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO + 1) : hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO) - this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T;
                        return true;
                    case '\"':
                        new IllegalArgumentException("Syntax Error: quote in unquoted cell");
                        this.f555hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = exc;
                        return false;
                    default:
                        i2++;
                }
            }
        }

        public final void remove() {
            Throwable th;
            Throwable th2 = th;
            new UnsupportedOperationException();
            throw th2;
        }
    }
}
