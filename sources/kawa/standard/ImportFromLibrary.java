package kawa.standard;

import com.firebase.client.core.Constants;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleManager;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import java.io.PrintStream;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class ImportFromLibrary extends Syntax {
    private static final String BUILTIN = "";
    private static final String MISSING = null;
    static final String[][] SRFI97Map;
    public static final ImportFromLibrary instance;
    public String[] classPrefixPath;

    public ImportFromLibrary() {
        String[] strArr = new String[2];
        strArr[0] = "";
        String[] strArr2 = strArr;
        strArr2[1] = "kawa.lib.";
        this.classPrefixPath = strArr2;
    }

    static {
        ImportFromLibrary importFromLibrary;
        new ImportFromLibrary();
        instance = importFromLibrary;
        String[][] strArr = new String[48][];
        String[][] strArr2 = strArr;
        String[][] strArr3 = strArr;
        String[] strArr4 = new String[3];
        strArr4[0] = "1";
        String[] strArr5 = strArr4;
        strArr5[1] = "lists";
        String[] strArr6 = strArr5;
        strArr6[2] = "gnu.kawa.slib.srfi1";
        strArr3[0] = strArr6;
        String[][] strArr7 = strArr2;
        String[][] strArr8 = strArr7;
        String[][] strArr9 = strArr7;
        String[] strArr10 = new String[3];
        strArr10[0] = "2";
        String[] strArr11 = strArr10;
        strArr11[1] = "and-let*";
        String[] strArr12 = strArr11;
        strArr12[2] = "gnu.kawa.slib.srfi2";
        strArr9[1] = strArr12;
        String[][] strArr13 = strArr8;
        String[][] strArr14 = strArr13;
        String[][] strArr15 = strArr13;
        String[] strArr16 = new String[3];
        strArr16[0] = Constants.WIRE_PROTOCOL_VERSION;
        String[] strArr17 = strArr16;
        strArr17[1] = "let";
        String[] strArr18 = strArr17;
        strArr18[2] = MISSING;
        strArr15[2] = strArr18;
        String[][] strArr19 = strArr14;
        String[][] strArr20 = strArr19;
        String[][] strArr21 = strArr19;
        String[] strArr22 = new String[3];
        strArr22[0] = "6";
        String[] strArr23 = strArr22;
        strArr23[1] = "basic-string-ports";
        String[] strArr24 = strArr23;
        strArr24[2] = "";
        strArr21[3] = strArr24;
        String[][] strArr25 = strArr20;
        String[][] strArr26 = strArr25;
        String[][] strArr27 = strArr25;
        String[] strArr28 = new String[3];
        strArr28[0] = "8";
        String[] strArr29 = strArr28;
        strArr29[1] = "receive";
        String[] strArr30 = strArr29;
        strArr30[2] = "";
        strArr27[4] = strArr30;
        String[][] strArr31 = strArr26;
        String[][] strArr32 = strArr31;
        String[][] strArr33 = strArr31;
        String[] strArr34 = new String[3];
        strArr34[0] = "9";
        String[] strArr35 = strArr34;
        strArr35[1] = "records";
        String[] strArr36 = strArr35;
        strArr36[2] = "";
        strArr33[5] = strArr36;
        String[][] strArr37 = strArr32;
        String[][] strArr38 = strArr37;
        String[][] strArr39 = strArr37;
        String[] strArr40 = new String[3];
        strArr40[0] = "11";
        String[] strArr41 = strArr40;
        strArr41[1] = "let-values";
        String[] strArr42 = strArr41;
        strArr42[2] = "";
        strArr39[6] = strArr42;
        String[][] strArr43 = strArr38;
        String[][] strArr44 = strArr43;
        String[][] strArr45 = strArr43;
        String[] strArr46 = new String[3];
        strArr46[0] = "13";
        String[] strArr47 = strArr46;
        strArr47[1] = "strings";
        String[] strArr48 = strArr47;
        strArr48[2] = "gnu.kawa.slib.srfi13";
        strArr45[7] = strArr48;
        String[][] strArr49 = strArr44;
        String[][] strArr50 = strArr49;
        String[][] strArr51 = strArr49;
        String[] strArr52 = new String[3];
        strArr52[0] = "14";
        String[] strArr53 = strArr52;
        strArr53[1] = "char-sets";
        String[] strArr54 = strArr53;
        strArr54[2] = MISSING;
        strArr51[8] = strArr54;
        String[][] strArr55 = strArr50;
        String[][] strArr56 = strArr55;
        String[][] strArr57 = strArr55;
        String[] strArr58 = new String[3];
        strArr58[0] = "16";
        String[] strArr59 = strArr58;
        strArr59[1] = "case-lambda";
        String[] strArr60 = strArr59;
        strArr60[2] = "";
        strArr57[9] = strArr60;
        String[][] strArr61 = strArr56;
        String[][] strArr62 = strArr61;
        String[][] strArr63 = strArr61;
        String[] strArr64 = new String[3];
        strArr64[0] = "17";
        String[] strArr65 = strArr64;
        strArr65[1] = "generalized-set!";
        String[] strArr66 = strArr65;
        strArr66[2] = "";
        strArr63[10] = strArr66;
        String[][] strArr67 = strArr62;
        String[][] strArr68 = strArr67;
        String[][] strArr69 = strArr67;
        String[] strArr70 = new String[3];
        strArr70[0] = "18";
        String[] strArr71 = strArr70;
        strArr71[1] = "multithreading";
        String[] strArr72 = strArr71;
        strArr72[2] = MISSING;
        strArr69[11] = strArr72;
        String[][] strArr73 = strArr68;
        String[][] strArr74 = strArr73;
        String[][] strArr75 = strArr73;
        String[] strArr76 = new String[3];
        strArr76[0] = "19";
        String[] strArr77 = strArr76;
        strArr77[1] = "time";
        String[] strArr78 = strArr77;
        strArr78[2] = MISSING;
        strArr75[12] = strArr78;
        String[][] strArr79 = strArr74;
        String[][] strArr80 = strArr79;
        String[][] strArr81 = strArr79;
        String[] strArr82 = new String[3];
        strArr82[0] = "21";
        String[] strArr83 = strArr82;
        strArr83[1] = "real-time-multithreading";
        String[] strArr84 = strArr83;
        strArr84[2] = MISSING;
        strArr81[13] = strArr84;
        String[][] strArr85 = strArr80;
        String[][] strArr86 = strArr85;
        String[][] strArr87 = strArr85;
        String[] strArr88 = new String[3];
        strArr88[0] = "23";
        String[] strArr89 = strArr88;
        strArr89[1] = "error";
        String[] strArr90 = strArr89;
        strArr90[2] = "";
        strArr87[14] = strArr90;
        String[][] strArr91 = strArr86;
        String[][] strArr92 = strArr91;
        String[][] strArr93 = strArr91;
        String[] strArr94 = new String[3];
        strArr94[0] = "25";
        String[] strArr95 = strArr94;
        strArr95[1] = "multi-dimensional-arrays";
        String[] strArr96 = strArr95;
        strArr96[2] = "";
        strArr93[15] = strArr96;
        String[][] strArr97 = strArr92;
        String[][] strArr98 = strArr97;
        String[][] strArr99 = strArr97;
        String[] strArr100 = new String[3];
        strArr100[0] = "26";
        String[] strArr101 = strArr100;
        strArr101[1] = "cut";
        String[] strArr102 = strArr101;
        strArr102[2] = "";
        strArr99[16] = strArr102;
        String[][] strArr103 = strArr98;
        String[][] strArr104 = strArr103;
        String[][] strArr105 = strArr103;
        String[] strArr106 = new String[3];
        strArr106[0] = "27";
        String[] strArr107 = strArr106;
        strArr107[1] = "random-bits";
        String[] strArr108 = strArr107;
        strArr108[2] = MISSING;
        strArr105[17] = strArr108;
        String[][] strArr109 = strArr104;
        String[][] strArr110 = strArr109;
        String[][] strArr111 = strArr109;
        String[] strArr112 = new String[3];
        strArr112[0] = "28";
        String[] strArr113 = strArr112;
        strArr113[1] = "basic-format-strings";
        String[] strArr114 = strArr113;
        strArr114[2] = "";
        strArr111[18] = strArr114;
        String[][] strArr115 = strArr110;
        String[][] strArr116 = strArr115;
        String[][] strArr117 = strArr115;
        String[] strArr118 = new String[3];
        strArr118[0] = "29";
        String[] strArr119 = strArr118;
        strArr119[1] = "localization";
        String[] strArr120 = strArr119;
        strArr120[2] = MISSING;
        strArr117[19] = strArr120;
        String[][] strArr121 = strArr116;
        String[][] strArr122 = strArr121;
        String[][] strArr123 = strArr121;
        String[] strArr124 = new String[3];
        strArr124[0] = "31";
        String[] strArr125 = strArr124;
        strArr125[1] = "rec";
        String[] strArr126 = strArr125;
        strArr126[2] = MISSING;
        strArr123[20] = strArr126;
        String[][] strArr127 = strArr122;
        String[][] strArr128 = strArr127;
        String[][] strArr129 = strArr127;
        String[] strArr130 = new String[3];
        strArr130[0] = "38";
        String[] strArr131 = strArr130;
        strArr131[1] = "with-shared-structure";
        String[] strArr132 = strArr131;
        strArr132[2] = MISSING;
        strArr129[21] = strArr132;
        String[][] strArr133 = strArr128;
        String[][] strArr134 = strArr133;
        String[][] strArr135 = strArr133;
        String[] strArr136 = new String[3];
        strArr136[0] = "39";
        String[] strArr137 = strArr136;
        strArr137[1] = "parameters";
        String[] strArr138 = strArr137;
        strArr138[2] = "";
        strArr135[22] = strArr138;
        String[][] strArr139 = strArr134;
        String[][] strArr140 = strArr139;
        String[][] strArr141 = strArr139;
        String[] strArr142 = new String[3];
        strArr142[0] = "41";
        String[] strArr143 = strArr142;
        strArr143[1] = "streams";
        String[] strArr144 = strArr143;
        strArr144[2] = MISSING;
        strArr141[23] = strArr144;
        String[][] strArr145 = strArr140;
        String[][] strArr146 = strArr145;
        String[][] strArr147 = strArr145;
        String[] strArr148 = new String[3];
        strArr148[0] = "42";
        String[] strArr149 = strArr148;
        strArr149[1] = "eager-comprehensions";
        String[] strArr150 = strArr149;
        strArr150[2] = MISSING;
        strArr147[24] = strArr150;
        String[][] strArr151 = strArr146;
        String[][] strArr152 = strArr151;
        String[][] strArr153 = strArr151;
        String[] strArr154 = new String[3];
        strArr154[0] = "43";
        String[] strArr155 = strArr154;
        strArr155[1] = "vectors";
        String[] strArr156 = strArr155;
        strArr156[2] = MISSING;
        strArr153[25] = strArr156;
        String[][] strArr157 = strArr152;
        String[][] strArr158 = strArr157;
        String[][] strArr159 = strArr157;
        String[] strArr160 = new String[3];
        strArr160[0] = "44";
        String[] strArr161 = strArr160;
        strArr161[1] = "collections";
        String[] strArr162 = strArr161;
        strArr162[2] = MISSING;
        strArr159[26] = strArr162;
        String[][] strArr163 = strArr158;
        String[][] strArr164 = strArr163;
        String[][] strArr165 = strArr163;
        String[] strArr166 = new String[3];
        strArr166[0] = "45";
        String[] strArr167 = strArr166;
        strArr167[1] = "lazy";
        String[] strArr168 = strArr167;
        strArr168[2] = MISSING;
        strArr165[27] = strArr168;
        String[][] strArr169 = strArr164;
        String[][] strArr170 = strArr169;
        String[][] strArr171 = strArr169;
        String[] strArr172 = new String[3];
        strArr172[0] = "46";
        String[] strArr173 = strArr172;
        strArr173[1] = "syntax-rules";
        String[] strArr174 = strArr173;
        strArr174[2] = MISSING;
        strArr171[28] = strArr174;
        String[][] strArr175 = strArr170;
        String[][] strArr176 = strArr175;
        String[][] strArr177 = strArr175;
        String[] strArr178 = new String[3];
        strArr178[0] = "47";
        String[] strArr179 = strArr178;
        strArr179[1] = "arrays";
        String[] strArr180 = strArr179;
        strArr180[2] = MISSING;
        strArr177[29] = strArr180;
        String[][] strArr181 = strArr176;
        String[][] strArr182 = strArr181;
        String[][] strArr183 = strArr181;
        String[] strArr184 = new String[3];
        strArr184[0] = "48";
        String[] strArr185 = strArr184;
        strArr185[1] = "intermediate-format-strings";
        String[] strArr186 = strArr185;
        strArr186[2] = MISSING;
        strArr183[30] = strArr186;
        String[][] strArr187 = strArr182;
        String[][] strArr188 = strArr187;
        String[][] strArr189 = strArr187;
        String[] strArr190 = new String[3];
        strArr190[0] = "51";
        String[] strArr191 = strArr190;
        strArr191[1] = "rest-values";
        String[] strArr192 = strArr191;
        strArr192[2] = MISSING;
        strArr189[31] = strArr192;
        String[][] strArr193 = strArr188;
        String[][] strArr194 = strArr193;
        String[][] strArr195 = strArr193;
        String[] strArr196 = new String[3];
        strArr196[0] = "54";
        String[] strArr197 = strArr196;
        strArr197[1] = "cat";
        String[] strArr198 = strArr197;
        strArr198[2] = MISSING;
        strArr195[32] = strArr198;
        String[][] strArr199 = strArr194;
        String[][] strArr200 = strArr199;
        String[][] strArr201 = strArr199;
        String[] strArr202 = new String[3];
        strArr202[0] = "57";
        String[] strArr203 = strArr202;
        strArr203[1] = "records";
        String[] strArr204 = strArr203;
        strArr204[2] = MISSING;
        strArr201[33] = strArr204;
        String[][] strArr205 = strArr200;
        String[][] strArr206 = strArr205;
        String[][] strArr207 = strArr205;
        String[] strArr208 = new String[3];
        strArr208[0] = "59";
        String[] strArr209 = strArr208;
        strArr209[1] = "vicinities";
        String[] strArr210 = strArr209;
        strArr210[2] = MISSING;
        strArr207[34] = strArr210;
        String[][] strArr211 = strArr206;
        String[][] strArr212 = strArr211;
        String[][] strArr213 = strArr211;
        String[] strArr214 = new String[3];
        strArr214[0] = "60";
        String[] strArr215 = strArr214;
        strArr215[1] = "integer-bits";
        String[] strArr216 = strArr215;
        strArr216[2] = MISSING;
        strArr213[35] = strArr216;
        String[][] strArr217 = strArr212;
        String[][] strArr218 = strArr217;
        String[][] strArr219 = strArr217;
        String[] strArr220 = new String[3];
        strArr220[0] = "61";
        String[] strArr221 = strArr220;
        strArr221[1] = "cond";
        String[] strArr222 = strArr221;
        strArr222[2] = MISSING;
        strArr219[36] = strArr222;
        String[][] strArr223 = strArr218;
        String[][] strArr224 = strArr223;
        String[][] strArr225 = strArr223;
        String[] strArr226 = new String[3];
        strArr226[0] = "63";
        String[] strArr227 = strArr226;
        strArr227[1] = "arrays";
        String[] strArr228 = strArr227;
        strArr228[2] = MISSING;
        strArr225[37] = strArr228;
        String[][] strArr229 = strArr224;
        String[][] strArr230 = strArr229;
        String[][] strArr231 = strArr229;
        String[] strArr232 = new String[3];
        strArr232[0] = "64";
        String[] strArr233 = strArr232;
        strArr233[1] = "testing";
        String[] strArr234 = strArr233;
        strArr234[2] = "gnu.kawa.slib.testing";
        strArr231[38] = strArr234;
        String[][] strArr235 = strArr230;
        String[][] strArr236 = strArr235;
        String[][] strArr237 = strArr235;
        String[] strArr238 = new String[3];
        strArr238[0] = "66";
        String[] strArr239 = strArr238;
        strArr239[1] = "octet-vectors";
        String[] strArr240 = strArr239;
        strArr240[2] = MISSING;
        strArr237[39] = strArr240;
        String[][] strArr241 = strArr236;
        String[][] strArr242 = strArr241;
        String[][] strArr243 = strArr241;
        String[] strArr244 = new String[3];
        strArr244[0] = "67";
        String[] strArr245 = strArr244;
        strArr245[1] = "compare-procedures";
        String[] strArr246 = strArr245;
        strArr246[2] = MISSING;
        strArr243[40] = strArr246;
        String[][] strArr247 = strArr242;
        String[][] strArr248 = strArr247;
        String[][] strArr249 = strArr247;
        String[] strArr250 = new String[3];
        strArr250[0] = "69";
        String[] strArr251 = strArr250;
        strArr251[1] = "basic-hash-tables";
        String[] strArr252 = strArr251;
        strArr252[2] = "gnu.kawa.slib.srfi69";
        strArr249[41] = strArr252;
        String[][] strArr253 = strArr248;
        String[][] strArr254 = strArr253;
        String[][] strArr255 = strArr253;
        String[] strArr256 = new String[3];
        strArr256[0] = "71";
        String[] strArr257 = strArr256;
        strArr257[1] = "let";
        String[] strArr258 = strArr257;
        strArr258[2] = MISSING;
        strArr255[42] = strArr258;
        String[][] strArr259 = strArr254;
        String[][] strArr260 = strArr259;
        String[][] strArr261 = strArr259;
        String[] strArr262 = new String[3];
        strArr262[0] = "74";
        String[] strArr263 = strArr262;
        strArr263[1] = "blobs";
        String[] strArr264 = strArr263;
        strArr264[2] = MISSING;
        strArr261[43] = strArr264;
        String[][] strArr265 = strArr260;
        String[][] strArr266 = strArr265;
        String[][] strArr267 = strArr265;
        String[] strArr268 = new String[3];
        strArr268[0] = "78";
        String[] strArr269 = strArr268;
        strArr269[1] = "lightweight-testing";
        String[] strArr270 = strArr269;
        strArr270[2] = MISSING;
        strArr267[44] = strArr270;
        String[][] strArr271 = strArr266;
        String[][] strArr272 = strArr271;
        String[][] strArr273 = strArr271;
        String[] strArr274 = new String[3];
        strArr274[0] = "86";
        String[] strArr275 = strArr274;
        strArr275[1] = "mu-and-nu";
        String[] strArr276 = strArr275;
        strArr276[2] = MISSING;
        strArr273[45] = strArr276;
        String[][] strArr277 = strArr272;
        String[][] strArr278 = strArr277;
        String[][] strArr279 = strArr277;
        String[] strArr280 = new String[3];
        strArr280[0] = "87";
        String[] strArr281 = strArr280;
        strArr281[1] = "case";
        String[] strArr282 = strArr281;
        strArr282[2] = MISSING;
        strArr279[46] = strArr282;
        String[][] strArr283 = strArr278;
        String[][] strArr284 = strArr283;
        String[][] strArr285 = strArr283;
        String[] strArr286 = new String[3];
        strArr286[0] = "95";
        String[] strArr287 = strArr286;
        strArr287[1] = "sorting-and-merging";
        String[] strArr288 = strArr287;
        strArr288[2] = "kawa.lib.srfi95";
        strArr285[47] = strArr288;
        SRFI97Map = strArr284;
    }

    public boolean scanForDefinitions(Pair st, Vector vector, ScopeExp scopeExp, Translator translator) {
        StringBuffer stringBuffer;
        StringBuilder sb;
        StringBuilder sb2;
        String srfiName;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        StringBuilder sb7;
        StringBuilder sb8;
        Vector forms = vector;
        ScopeExp defs = scopeExp;
        Translator tr = translator;
        Procedure mapper = null;
        Object args = st.getCdr();
        if (!(args instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) args;
        Object libref = pair.getCar();
        if (LList.listLength(libref, false) <= 0) {
            tr.error('e', "expected <library reference> which must be a list");
            return false;
        }
        Object rest = pair.getCdr();
        if ((rest instanceof Pair) && (((Pair) rest).getCar() instanceof Procedure)) {
            mapper = (Procedure) ((Pair) rest).getCar();
        }
        Object versionSpec = null;
        String sourcePath = null;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        while (libref instanceof Pair) {
            Pair pair2 = (Pair) libref;
            Object car = pair2.getCar();
            Object cdr = pair2.getCdr();
            if (car instanceof Pair) {
                if (versionSpec != null) {
                    new StringBuilder();
                    tr.error('e', sb8.append("duplicate version reference - was ").append(versionSpec).toString());
                }
                versionSpec = car;
                PrintStream printStream = System.err;
                new StringBuilder();
                printStream.println(sb7.append("import version ").append(car).toString());
            } else if (car instanceof String) {
                if (cdr instanceof Pair) {
                    tr.error('e', "source specifier must be last elemnt in library reference");
                }
                sourcePath = (String) car;
            } else {
                if (sbuf.length() > 0) {
                    StringBuffer append = sbuf.append('.');
                }
                StringBuffer append2 = sbuf.append(Compilation.mangleNameIfNeeded(car.toString()));
            }
            libref = cdr;
        }
        ModuleInfo minfo = null;
        if (sourcePath != null) {
            minfo = require.lookupModuleFromSourcePath(sourcePath, defs);
            if (minfo == null) {
                new StringBuilder();
                tr.error('e', sb6.append("malformed URL: ").append(sourcePath).toString());
                return false;
            }
        }
        String lname = sbuf.toString();
        if (lname.startsWith("srfi.")) {
            String demangled = Compilation.demangleName(lname.substring(5));
            int dot = demangled.indexOf(46);
            if (dot < 0) {
                srfiName = null;
                dot = demangled.length();
            } else {
                srfiName = demangled.substring(dot + 1);
            }
            String srfiNumber = null;
            if (dot >= 2 || demangled.charAt(0) == ':') {
                int i = 1;
                while (true) {
                    if (i == dot) {
                        srfiNumber = demangled.substring(1, dot);
                        break;
                    } else if (Character.digit(demangled.charAt(i), 10) < 0) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (srfiNumber == null) {
                tr.error('e', "SRFI library reference must have the form: (srfi :NNN [name])");
                return false;
            }
            int srfiIndex = SRFI97Map.length;
            do {
                srfiIndex--;
                if (srfiIndex < 0) {
                    new StringBuilder();
                    tr.error('e', sb3.append("unknown SRFI number '").append(srfiNumber).append("' in SRFI library reference").toString());
                    return false;
                }
            } while (!SRFI97Map[srfiIndex][0].equals(srfiNumber));
            String srfiNameExpected = SRFI97Map[srfiIndex][1];
            String srfiClass = SRFI97Map[srfiIndex][2];
            if (srfiName != null && !srfiName.equals(srfiNameExpected)) {
                new StringBuilder();
                tr.error('w', sb5.append("the name of SRFI ").append(srfiNumber).append(" should be '").append(srfiNameExpected).append('\'').toString());
            }
            if (srfiClass == "") {
                return true;
            }
            if (srfiClass == MISSING) {
                new StringBuilder();
                tr.error('e', sb4.append("sorry - Kawa does not support SRFI ").append(srfiNumber).append(" (").append(srfiNameExpected).append(')').toString());
                return false;
            }
            lname = srfiClass;
        }
        int classPrefixPathLength = this.classPrefixPath.length;
        for (int i2 = 0; i2 < classPrefixPathLength; i2++) {
            new StringBuilder();
            try {
                minfo = ModuleManager.getInstance().findWithClassName(sb2.append(this.classPrefixPath[i2]).append(lname).toString());
            } catch (Exception e) {
                Exception exc = e;
            }
        }
        if (minfo == null) {
            new StringBuilder();
            tr.error('e', sb.append("unknown class ").append(lname).toString());
            return false;
        }
        boolean importDefinitions = require.importDefinitions((String) null, minfo, mapper, forms, defs, tr);
        return true;
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        Pair pair2 = pair;
        Translator translator2 = translator;
        return null;
    }
}
