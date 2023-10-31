package android.support.p000v4.text.util;

import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import java.util.Locale;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.text.util.FindAddress */
class FindAddress {
    private static final String HOUSE_COMPONENT = "(?:one|\\d+([a-z](?=[^a-z]|$)|st|nd|rd|th)?)";
    private static final String HOUSE_END = "(?=[,\"'\t                　\n\u000b\f\r  ]|$)";
    private static final String HOUSE_POST_DELIM = ",\"'\t                　\n\u000b\f\r  ";
    private static final String HOUSE_PRE_DELIM = ":,\"'\t                　\n\u000b\f\r  ";
    private static final int MAX_ADDRESS_LINES = 5;
    private static final int MAX_ADDRESS_WORDS = 14;
    private static final int MAX_LOCATION_NAME_DISTANCE = 5;
    private static final int MIN_ADDRESS_WORDS = 4;

    /* renamed from: NL */
    private static final String f26NL = "\n\u000b\f\r  ";

    /* renamed from: SP */
    private static final String f27SP = "\t                　";
    private static final String WORD_DELIM = ",*•\t                　\n\u000b\f\r  ";
    private static final String WORD_END = "(?=[,*•\t                　\n\u000b\f\r  ]|$)";

    /* renamed from: WS */
    private static final String f28WS = "\t                　\n\u000b\f\r  ";
    private static final int kMaxAddressNameWordLength = 25;
    private static final Pattern sHouseNumberRe = Pattern.compile("(?:one|\\d+([a-z](?=[^a-z]|$)|st|nd|rd|th)?)(?:-(?:one|\\d+([a-z](?=[^a-z]|$)|st|nd|rd|th)?))*(?=[,\"'\t                　\n\u000b\f\r  ]|$)", 2);
    private static final Pattern sLocationNameRe = Pattern.compile("(?:alley|annex|arcade|ave[.]?|avenue|alameda|bayou|beach|bend|bluffs?|bottom|boulevard|branch|bridge|brooks?|burgs?|bypass|broadway|camino|camp|canyon|cape|causeway|centers?|circles?|cliffs?|club|common|corners?|course|courts?|coves?|creek|crescent|crest|crossing|crossroad|curve|circulo|dale|dam|divide|drives?|estates?|expressway|extensions?|falls?|ferry|fields?|flats?|fords?|forest|forges?|forks?|fort|freeway|gardens?|gateway|glens?|greens?|groves?|harbors?|haven|heights|highway|hills?|hollow|inlet|islands?|isle|junctions?|keys?|knolls?|lakes?|land|landing|lane|lights?|loaf|locks?|lodge|loop|mall|manors?|meadows?|mews|mills?|mission|motorway|mount|mountains?|neck|orchard|oval|overpass|parks?|parkways?|pass|passage|path|pike|pines?|plains?|plaza|points?|ports?|prairie|privada|radial|ramp|ranch|rapids?|rd[.]?|rest|ridges?|river|roads?|route|row|rue|run|shoals?|shores?|skyway|springs?|spurs?|squares?|station|stravenue|stream|st[.]?|streets?|summit|speedway|terrace|throughway|trace|track|trafficway|trail|tunnel|turnpike|underpass|unions?|valleys?|viaduct|views?|villages?|ville|vista|walks?|wall|ways?|wells?|xing|xrd)(?=[,*•\t                　\n\u000b\f\r  ]|$)", 2);
    private static final Pattern sStateRe = Pattern.compile("(?:(ak|alaska)|(al|alabama)|(ar|arkansas)|(as|american[\t                　]+samoa)|(az|arizona)|(ca|california)|(co|colorado)|(ct|connecticut)|(dc|district[\t                　]+of[\t                　]+columbia)|(de|delaware)|(fl|florida)|(fm|federated[\t                　]+states[\t                　]+of[\t                　]+micronesia)|(ga|georgia)|(gu|guam)|(hi|hawaii)|(ia|iowa)|(id|idaho)|(il|illinois)|(in|indiana)|(ks|kansas)|(ky|kentucky)|(la|louisiana)|(ma|massachusetts)|(md|maryland)|(me|maine)|(mh|marshall[\t                　]+islands)|(mi|michigan)|(mn|minnesota)|(mo|missouri)|(mp|northern[\t                　]+mariana[\t                　]+islands)|(ms|mississippi)|(mt|montana)|(nc|north[\t                　]+carolina)|(nd|north[\t                　]+dakota)|(ne|nebraska)|(nh|new[\t                　]+hampshire)|(nj|new[\t                　]+jersey)|(nm|new[\t                　]+mexico)|(nv|nevada)|(ny|new[\t                　]+york)|(oh|ohio)|(ok|oklahoma)|(or|oregon)|(pa|pennsylvania)|(pr|puerto[\t                　]+rico)|(pw|palau)|(ri|rhode[\t                　]+island)|(sc|south[\t                　]+carolina)|(sd|south[\t                　]+dakota)|(tn|tennessee)|(tx|texas)|(ut|utah)|(va|virginia)|(vi|virgin[\t                　]+islands)|(vt|vermont)|(wa|washington)|(wi|wisconsin)|(wv|west[\t                　]+virginia)|(wy|wyoming))(?=[,*•\t                　\n\u000b\f\r  ]|$)", 2);
    private static final ZipRange[] sStateZipCodeRanges;
    private static final Pattern sSuffixedNumberRe = Pattern.compile("(\\d+)(st|nd|rd|th)", 2);
    private static final Pattern sWordRe = Pattern.compile("[^,*•\t                　\n\u000b\f\r  ]+(?=[,*•\t                　\n\u000b\f\r  ]|$)", 2);
    private static final Pattern sZipCodeRe = Pattern.compile("(?:\\d{5}(?:-\\d{4})?)(?=[,*•\t                　\n\u000b\f\r  ]|$)", 2);

    /* renamed from: android.support.v4.text.util.FindAddress$ZipRange */
    private static class ZipRange {
        int mException1;
        int mException2;
        int mHigh;
        int mLow;

        ZipRange(int low, int high, int i, int i2) {
            int exception1 = i;
            int i3 = i2;
            this.mLow = low;
            this.mHigh = high;
            this.mException1 = exception1;
            this.mException2 = exception1;
        }

        /* access modifiers changed from: package-private */
        public boolean matches(String zipCode) {
            int prefix = Integer.parseInt(zipCode.substring(0, 2));
            return (this.mLow <= prefix && prefix <= this.mHigh) || prefix == this.mException1 || prefix == this.mException2;
        }
    }

    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            r0 = 59
            android.support.v4.text.util.FindAddress$ZipRange[] r0 = new android.support.p000v4.text.util.FindAddress.ZipRange[r0]
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 0
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 99
            r6 = 99
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 1
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 35
            r6 = 36
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 2
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 71
            r6 = 72
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 3
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 96
            r6 = 96
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 4
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 85
            r6 = 86
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 5
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 90
            r6 = 96
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 6
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 80
            r6 = 81
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 7
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 6
            r6 = 6
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 8
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 20
            r6 = 20
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 9
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 19
            r6 = 19
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 10
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 32
            r6 = 34
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 11
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 96
            r6 = 96
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 12
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 30
            r6 = 31
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 13
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 96
            r6 = 96
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 14
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 96
            r6 = 96
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 15
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 50
            r6 = 52
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 16
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 83
            r6 = 83
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 17
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 60
            r6 = 62
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 18
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 46
            r6 = 47
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 19
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 66
            r6 = 67
            r7 = 73
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 20
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 40
            r6 = 42
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 21
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 70
            r6 = 71
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 22
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 1
            r6 = 2
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 23
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 20
            r6 = 21
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 24
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 3
            r6 = 4
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 25
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 96
            r6 = 96
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 26
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 48
            r6 = 49
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 27
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 55
            r6 = 56
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 28
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 63
            r6 = 65
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 29
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 96
            r6 = 96
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 30
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 38
            r6 = 39
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 31
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 55
            r6 = 56
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 32
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 27
            r6 = 28
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 33
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 58
            r6 = 58
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 34
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 68
            r6 = 69
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 35
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 3
            r6 = 4
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 36
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 7
            r6 = 8
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 37
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 87
            r6 = 88
            r7 = 86
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 38
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 88
            r6 = 89
            r7 = 96
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 39
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 10
            r6 = 14
            r7 = 0
            r8 = 6
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 40
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 43
            r6 = 45
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 41
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 73
            r6 = 74
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 42
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 97
            r6 = 97
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 43
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 15
            r6 = 19
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 44
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 6
            r6 = 6
            r7 = 0
            r8 = 9
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 45
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 96
            r6 = 96
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 46
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 2
            r6 = 2
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 47
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 29
            r6 = 29
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 48
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 57
            r6 = 57
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 49
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 37
            r6 = 38
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 50
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 75
            r6 = 79
            r7 = 87
            r8 = 88
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 51
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 84
            r6 = 84
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 52
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 22
            r6 = 24
            r7 = 20
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 53
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 6
            r6 = 9
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 54
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 5
            r6 = 5
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 55
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 98
            r6 = 99
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 56
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 53
            r6 = 54
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 57
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 24
            r6 = 26
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            r9 = r0
            r0 = r9
            r1 = r9
            r2 = 58
            android.support.v4.text.util.FindAddress$ZipRange r3 = new android.support.v4.text.util.FindAddress$ZipRange
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 82
            r6 = 83
            r7 = -1
            r8 = -1
            r4.<init>(r5, r6, r7, r8)
            r1[r2] = r3
            sStateZipCodeRanges = r0
            java.lang.String r0 = "[^,*•\t                　\n\u000b\f\r  ]+(?=[,*•\t                　\n\u000b\f\r  ]|$)"
            r1 = 2
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0, r1)
            sWordRe = r0
            java.lang.String r0 = "(?:one|\\d+([a-z](?=[^a-z]|$)|st|nd|rd|th)?)(?:-(?:one|\\d+([a-z](?=[^a-z]|$)|st|nd|rd|th)?))*(?=[,\"'\t                　\n\u000b\f\r  ]|$)"
            r1 = 2
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0, r1)
            sHouseNumberRe = r0
            java.lang.String r0 = "(?:(ak|alaska)|(al|alabama)|(ar|arkansas)|(as|american[\t                　]+samoa)|(az|arizona)|(ca|california)|(co|colorado)|(ct|connecticut)|(dc|district[\t                　]+of[\t                　]+columbia)|(de|delaware)|(fl|florida)|(fm|federated[\t                　]+states[\t                　]+of[\t                　]+micronesia)|(ga|georgia)|(gu|guam)|(hi|hawaii)|(ia|iowa)|(id|idaho)|(il|illinois)|(in|indiana)|(ks|kansas)|(ky|kentucky)|(la|louisiana)|(ma|massachusetts)|(md|maryland)|(me|maine)|(mh|marshall[\t                　]+islands)|(mi|michigan)|(mn|minnesota)|(mo|missouri)|(mp|northern[\t                　]+mariana[\t                　]+islands)|(ms|mississippi)|(mt|montana)|(nc|north[\t                　]+carolina)|(nd|north[\t                　]+dakota)|(ne|nebraska)|(nh|new[\t                　]+hampshire)|(nj|new[\t                　]+jersey)|(nm|new[\t                　]+mexico)|(nv|nevada)|(ny|new[\t                　]+york)|(oh|ohio)|(ok|oklahoma)|(or|oregon)|(pa|pennsylvania)|(pr|puerto[\t                　]+rico)|(pw|palau)|(ri|rhode[\t                　]+island)|(sc|south[\t                　]+carolina)|(sd|south[\t                　]+dakota)|(tn|tennessee)|(tx|texas)|(ut|utah)|(va|virginia)|(vi|virgin[\t                　]+islands)|(vt|vermont)|(wa|washington)|(wi|wisconsin)|(wv|west[\t                　]+virginia)|(wy|wyoming))(?=[,*•\t                　\n\u000b\f\r  ]|$)"
            r1 = 2
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0, r1)
            sStateRe = r0
            java.lang.String r0 = "(?:alley|annex|arcade|ave[.]?|avenue|alameda|bayou|beach|bend|bluffs?|bottom|boulevard|branch|bridge|brooks?|burgs?|bypass|broadway|camino|camp|canyon|cape|causeway|centers?|circles?|cliffs?|club|common|corners?|course|courts?|coves?|creek|crescent|crest|crossing|crossroad|curve|circulo|dale|dam|divide|drives?|estates?|expressway|extensions?|falls?|ferry|fields?|flats?|fords?|forest|forges?|forks?|fort|freeway|gardens?|gateway|glens?|greens?|groves?|harbors?|haven|heights|highway|hills?|hollow|inlet|islands?|isle|junctions?|keys?|knolls?|lakes?|land|landing|lane|lights?|loaf|locks?|lodge|loop|mall|manors?|meadows?|mews|mills?|mission|motorway|mount|mountains?|neck|orchard|oval|overpass|parks?|parkways?|pass|passage|path|pike|pines?|plains?|plaza|points?|ports?|prairie|privada|radial|ramp|ranch|rapids?|rd[.]?|rest|ridges?|river|roads?|route|row|rue|run|shoals?|shores?|skyway|springs?|spurs?|squares?|station|stravenue|stream|st[.]?|streets?|summit|speedway|terrace|throughway|trace|track|trafficway|trail|tunnel|turnpike|underpass|unions?|valleys?|viaduct|views?|villages?|ville|vista|walks?|wall|ways?|wells?|xing|xrd)(?=[,*•\t                　\n\u000b\f\r  ]|$)"
            r1 = 2
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0, r1)
            sLocationNameRe = r0
            java.lang.String r0 = "(\\d+)(st|nd|rd|th)"
            r1 = 2
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0, r1)
            sSuffixedNumberRe = r0
            java.lang.String r0 = "(?:\\d{5}(?:-\\d{4})?)(?=[,*•\t                　\n\u000b\f\r  ]|$)"
            r1 = 2
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0, r1)
            sZipCodeRe = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.text.util.FindAddress.<clinit>():void");
    }

    private static boolean checkHouseNumber(String str) {
        String houseNumber = str;
        int digitCount = 0;
        for (int i = 0; i < houseNumber.length(); i++) {
            if (Character.isDigit(houseNumber.charAt(i))) {
                digitCount++;
            }
        }
        if (digitCount > 5) {
            return false;
        }
        Matcher suffixMatcher = sSuffixedNumberRe.matcher(houseNumber);
        if (!suffixMatcher.find()) {
            return true;
        }
        int num = Integer.parseInt(suffixMatcher.group(1));
        if (num == 0) {
            return false;
        }
        String suffix = suffixMatcher.group(2).toLowerCase(Locale.getDefault());
        switch (num % 10) {
            case 1:
                return suffix.equals(num % 100 == 11 ? "th" : "st");
            case 2:
                return suffix.equals(num % 100 == 12 ? "th" : "nd");
            case 3:
                return suffix.equals(num % 100 == 13 ? "th" : "rd");
            default:
                return suffix.equals("th");
        }
    }

    @VisibleForTesting
    public static MatchResult matchHouseNumber(String str, int i) {
        String content = str;
        int offset = i;
        if (offset > 0 && HOUSE_PRE_DELIM.indexOf(content.charAt(offset - 1)) == -1) {
            return null;
        }
        Matcher matcher = sHouseNumberRe.matcher(content).region(offset, content.length());
        if (matcher.lookingAt()) {
            MatchResult matchResult = matcher.toMatchResult();
            if (checkHouseNumber(matchResult.group(0))) {
                return matchResult;
            }
        }
        return null;
    }

    @VisibleForTesting
    public static MatchResult matchState(String str, int i) {
        String content = str;
        int offset = i;
        if (offset > 0 && WORD_DELIM.indexOf(content.charAt(offset - 1)) == -1) {
            return null;
        }
        Matcher stateMatcher = sStateRe.matcher(content).region(offset, content.length());
        return stateMatcher.lookingAt() ? stateMatcher.toMatchResult() : null;
    }

    private static boolean isValidZipCode(String str, MatchResult matchResult) {
        String zipCode = str;
        MatchResult stateMatch = matchResult;
        if (stateMatch == null) {
            return false;
        }
        int stateIndex = stateMatch.groupCount();
        while (stateIndex > 0) {
            int i = stateIndex;
            stateIndex--;
            if (stateMatch.group(i) != null) {
                break;
            }
        }
        return sZipCodeRe.matcher(zipCode).matches() && sStateZipCodeRanges[stateIndex].matches(zipCode);
    }

    @VisibleForTesting
    public static boolean isValidZipCode(String zipCode, String state) {
        return isValidZipCode(zipCode, matchState(state, 0));
    }

    @VisibleForTesting
    public static boolean isValidZipCode(String zipCode) {
        return sZipCodeRe.matcher(zipCode).matches();
    }

    @VisibleForTesting
    public static boolean isValidLocationName(String location) {
        return sLocationNameRe.matcher(location).matches();
    }

    private static int attemptMatch(String str, MatchResult houseNumberMatch) {
        MatchResult stateMatch;
        String content = str;
        int restartPos = -1;
        int nonZipMatch = -1;
        int it = houseNumberMatch.end();
        int numLines = 1;
        boolean consecutiveHouseNumbers = true;
        boolean foundLocationName = false;
        int wordCount = 1;
        String lastWord = "";
        Matcher matcher = sWordRe.matcher(content);
        while (true) {
            if (it < content.length()) {
                if (matcher.find(it)) {
                    if (matcher.end() - matcher.start() <= 25) {
                        while (it < matcher.start()) {
                            int i = it;
                            it++;
                            if (f26NL.indexOf(content.charAt(i)) != -1) {
                                numLines++;
                            }
                        }
                        if (numLines <= 5) {
                            wordCount++;
                            if (wordCount > 14) {
                                break;
                            }
                            if (matchHouseNumber(content, it) == null) {
                                consecutiveHouseNumbers = false;
                                if (!isValidLocationName(matcher.group(0))) {
                                    if (wordCount != 5 || foundLocationName) {
                                        if (foundLocationName && wordCount > 4 && (stateMatch = matchState(content, it)) != null) {
                                            if (lastWord.equals("et") && stateMatch.group(0).equals("al")) {
                                                it = stateMatch.end();
                                                break;
                                            }
                                            Matcher zipMatcher = sWordRe.matcher(content);
                                            if (!zipMatcher.find(stateMatch.end())) {
                                                nonZipMatch = stateMatch.end();
                                            } else if (isValidZipCode(zipMatcher.group(0), stateMatch)) {
                                                return zipMatcher.end();
                                            }
                                        }
                                    } else {
                                        it = matcher.end();
                                        break;
                                    }
                                } else {
                                    foundLocationName = true;
                                }
                            } else if (consecutiveHouseNumbers && numLines > 1) {
                                return -it;
                            } else {
                                if (restartPos == -1) {
                                    restartPos = it;
                                }
                            }
                            lastWord = matcher.group(0);
                            it = matcher.end();
                        } else {
                            break;
                        }
                    } else {
                        return -matcher.end();
                    }
                } else {
                    return -content.length();
                }
            } else {
                break;
            }
        }
        if (nonZipMatch > 0) {
            return nonZipMatch;
        }
        return -(restartPos > 0 ? restartPos : it);
    }

    static String findAddress(String str) {
        String content = str;
        Matcher houseNumberMatcher = sHouseNumberRe.matcher(content);
        int start = 0;
        while (true) {
            if (!houseNumberMatcher.find(start)) {
                return null;
            }
            if (checkHouseNumber(houseNumberMatcher.group(0))) {
                int start2 = houseNumberMatcher.start();
                int end = attemptMatch(content, houseNumberMatcher);
                if (end > 0) {
                    return content.substring(start2, end);
                }
                start = -end;
            } else {
                start = houseNumberMatcher.end();
            }
        }
    }

    private FindAddress() {
    }
}
